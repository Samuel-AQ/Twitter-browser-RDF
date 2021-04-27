package mainPackage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Derivation;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.SKOS;
import org.apache.jena.vocabulary.VCARD;

public class RDF {

    private Model schemaModel, data;
    private List<Tweet> tweets;

    public RDF(String schemaPath, List<Tweet> tweets) {
        this.schemaModel = RDFDataMgr.loadModel(schemaPath);
        this.tweets = tweets;
        data = ModelFactory.createDefaultModel();
    }
    
    public Model getData() {
        return data;
    }

    protected void makeGraph() throws IOException {
        // Prefixes
        String exPrefix = "http://www.example.org/";
        data.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
        data.setNsPrefix("vcard", "http://www.w3.org/2001/vcard-rdf/3.0#");
        data.setNsPrefix("skos", "http://www.w3.org/2004/02/skos/core#");
        data.setNsPrefix("ex", "http://example.org/");
        
        for(Tweet tweet : tweets){
            // Tweet info
            Resource tweetResource = data.createResource(exPrefix + "tweet#" + tweet.getId());
            
            tweetResource.addProperty(DC.contributor, "user#" + tweet.getUser().getId());
            tweetResource.addProperty(DC.language, "language#" + tweet.getLanguage());
            for(String hashtag : tweet.getHashtags()){
                // Creates hashtag resource
                Resource hashtagResource = data.createResource(exPrefix + "hashtag#" + hashtag);
                hashtagResource.addProperty(SKOS.altLabel, hashtag);
                
                // Adds hashtag to tweet
                tweetResource.addProperty(DC.subject, "hashtag#" + hashtag);
            }
            tweetResource.addProperty(DC.date, tweet.getDate());
            tweetResource.addProperty(DC.description, tweet.getContent());
            
            String replyId = tweet.getInReplyTo();
            if (!replyId.equals("-1")){
                tweetResource.addProperty(DC.source, replyId);
            }
            
            // User info
            Resource userResource = data.createResource(exPrefix + "user#" + tweet.getUser().getId());
            
            userResource.addProperty(DC.creator, tweet.getUser().getName());
            userResource.addProperty(VCARD.Locality, tweet.getUser().getLocation());
            
            // Language info
            Resource languageResource = data.createResource(exPrefix + "language#" + tweet.getLanguage());
            
            languageResource.addProperty(SKOS.prefLabel, tweet.getLanguage());  
            languageResource.addProperty(SKOS.altLabel, getFullLanguage(tweet.getLanguage()));
        }
    }
    
    /**
     * 
     * @param language
     * @return full language name based on ISO 639-1 if it's one of the most spoken ones
     */
    private String getFullLanguage(String language){
        switch(language){
            case "es":
                return "Spanish";
            case "en":
                return "English";
            case "fr":
                return "French";
            case "de":
                return "German";
            case "zh":
                return "Chinese";
            case "ja":
                return "Japanese";
            case "el":
                return "Greek";
            case "ar":
                return "Arabic";
            case "pt":
                return "Portuguese";
            case "it":
                return "Italian";
            default:
                return language;
        }
    }

    protected void runInference() {
        InfModel inference = ModelFactory.createRDFSModel(schemaModel, data);

        validateData(inference);
        showInference(inference);
    }

    protected void showInference(InfModel inference) {
        StmtIterator iterator = inference.listStatements();

        while (iterator.hasNext()) {
            Statement triplet = iterator.nextStatement();

            System.out.println("--> " + triplet.toString());
        }
    }

    private void validateData(InfModel inference) {
        ValidityReport validation = inference.validate();

        if (!validation.isValid()) {
            Iterator reports = validation.getReports();
            System.out.println("Inconsistencies:");
            while (reports.hasNext()) {
                Object report = reports.next();
                System.out.println(report.toString());
            }
        }
    }

    private void showDeductions(InfModel inference) {
        StmtIterator statements = inference.listStatements();

        while (statements.hasNext()) {
            Statement triplet = statements.next();

            Iterator<Derivation> derivations = inference.getDerivation(triplet);
            if (!derivations.hasNext()) {
                System.out.println("Not inferred triplet: " + PrintUtil.print(triplet));
            } else {
                while (derivations.hasNext()) {
                    Derivation derivation = derivations.next();
                    System.out.println("Derivation for the triplet: "
                            + PrintUtil.print(triplet)
                            + "\n"
                            + derivation.toString());
                }
            }
        }
    }
}
