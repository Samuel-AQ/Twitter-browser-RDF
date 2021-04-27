package mainPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class mainFrame extends javax.swing.JFrame {

    private Twitter twitterCredentials;

    public mainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        hashtagField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        wordsField = new javax.swing.JTextField();
        resultsNumberSlider = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        outputFormatField = new javax.swing.JComboBox<>();
        connectButton = new javax.swing.JButton();
        outputPane = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Hashtag");

        hashtagField.setText("#");

        jLabel3.setText("Words");

        resultsNumberSlider.setMajorTickSpacing(10);
        resultsNumberSlider.setMaximum(50);
        resultsNumberSlider.setMinorTickSpacing(10);
        resultsNumberSlider.setPaintLabels(true);
        resultsNumberSlider.setPaintTicks(true);
        resultsNumberSlider.setSnapToTicks(true);
        resultsNumberSlider.setValue(10);
        resultsNumberSlider.setValueIsAdjusting(true);

        jLabel4.setText("Number of results");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hashtagField)
                    .addComponent(wordsField)
                    .addComponent(resultsNumberSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 82, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wordsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hashtagField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultsNumberSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Output format");

        outputFormatField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Turtle", "RDF/XML" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(outputFormatField, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outputFormatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        connectButton.setBackground(new java.awt.Color(0, 153, 204));
        connectButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        connectButton.setForeground(new java.awt.Color(255, 255, 255));
        connectButton.setText("Choose credentials file");
        connectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                connectButtonMouseClicked(evt);
            }
        });

        outputPane.setBackground(new java.awt.Color(255, 255, 255));

        outputArea.setEditable(false);
        outputArea.setBackground(new java.awt.Color(255, 255, 255));
        outputArea.setColumns(20);
        outputArea.setForeground(new java.awt.Color(0, 0, 0));
        outputArea.setRows(5);
        outputPane.setViewportView(outputArea);

        saveButton.setBackground(new java.awt.Color(0, 153, 204));
        saveButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("OUTPUT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(outputPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(connectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(248, 248, 248)))))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outputPane, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        if (twitterCredentials != null && resultsNumberSlider.getValue() > 0) {
            if (checkFields()) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Please, remember to fill all the fields and use the '#' when needed",
                        "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    String searchContent = wordsField.getText()
                            + " "
                            + hashtagField.getText();
                    List<Tweet> tweets = searchTweets(twitterCredentials, searchContent);

                    String schemaPath = "data/RDFFiles/schema.ttl";
                    runRDF(schemaPath, tweets);
                } catch (TwitterException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Please, remember to connect to Twitter before making a query",
                    "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_saveButtonMouseClicked

    private void connectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectButtonMouseClicked
        try {
            twitterCredentials = getTwitterInstance();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_connectButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    private boolean checkFields() {
        boolean fieldsAreWrong = hashtagField.getText().equals("") && wordsField.getText().equals("");

        // Checks the hashtag field format
        if (!hashtagField.getText().equals("")) {
            String[] hashtags = hashtagField.getText().split(" ");
            for (String hashtag : hashtags) {
                if (!hashtag.contains("#") || hashtag.length() == 1) {
                    fieldsAreWrong = true;
                }
            }
        }

        return fieldsAreWrong;
    }

    private Twitter getTwitterInstance() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) != -1) {
            File credentialsFile = fileChooser.getSelectedFile();
            Scanner reader = new Scanner(credentialsFile);

            Map<String, String> credentials = new HashMap<String, String>();
            credentials.put("Consumer key", reader.next());
            credentials.put("Consumer secret", reader.next());
            credentials.put("Access token", reader.next());
            credentials.put("Access token secret", reader.next());

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(credentials.get("Consumer key"))
                    .setOAuthConsumerSecret(credentials.get("Consumer secret"))
                    .setOAuthAccessToken(credentials.get("Access token"))
                    .setOAuthAccessTokenSecret(credentials.get("Access token secret"));
            TwitterFactory tf = new TwitterFactory(cb.build());
            
            // Credentials control
            try {
                Twitter credentialsData = tf.getInstance();
                User user = credentialsData.verifyCredentials();
                return credentialsData;
            } catch (TwitterException e){
                JOptionPane.showMessageDialog(new JFrame(),
                            "The credentials you uploaded are not correct",
                            "Dialog",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private List<Tweet> searchTweets(Twitter twitter, String searchTerm) throws TwitterException {
        List<Tweet> tweets = new ArrayList<Tweet>();
        Query query = new Query(searchTerm);
        query.setCount(resultsNumberSlider.getValue());
        QueryResult result = twitter.search(query);

        for (twitter4j.Status status : result.getTweets()) {
            String id = status.getId() + "";
            User user = status.getUser();
            String language = (status.getLang() != null) ? status.getLang() : "";
            String date = (status.getCreatedAt() != null) ? status.getCreatedAt().toString() : "";
            String content = (!status.getText().equals("")) ? status.getText() : "";
            String place = (status.getPlace() != null) ? status.getPlace().getName() : "";
            List<String> hashtags = new ArrayList<String>();
            String inReplyTo = status.getInReplyToStatusId() + "";

            for (HashtagEntity hashtag : status.getHashtagEntities()) {
                hashtags.add(hashtag.getText());
            }

            Tweet tweet = new Tweet(id, user, language, date, content, place, hashtags, inReplyTo);
            tweets.add(tweet);
        }

        for (Tweet tweet : tweets) {
            tweet.printValues();
        }

        return tweets;
    }

    private void runRDF(String schemaPath, List<Tweet> tweets) throws IOException {
        String outputFormat = outputFormatField.getSelectedItem().toString();
        RDF rdf = new RDF(schemaPath, tweets);
        rdf.makeGraph();
        rdf.runInference();
        
        // Format control
        if (outputFormat.equals("Turtle")) {
            writeOutput("data/RDFFiles/data.ttl", "Turtle", rdf);
        } else {
            writeOutput("data/RDFFiles/data.xml", "RDF/XML", rdf);
        }
    }

    private void writeOutput(String filePath, String format, RDF rdf) throws FileNotFoundException, IOException {
        File outputFile = new File(filePath);
        OutputStream out = new FileOutputStream(outputFile);

        // Writes output to file
        switch (format) {
            case "Turtle":
                RDFDataMgr.write(out, rdf.getData(), RDFFormat.TURTLE_PRETTY);
                break;
            case "RDF/XML":
                RDFDataMgr.write(out, rdf.getData(), RDFFormat.RDFXML);
                break;
        }

        // Writes output to GUI
        String newLine = "\n";
        outputArea.setText("OUTPUT IN " + format.toUpperCase() + " FORMAT" + newLine
                + "Search content: " + wordsField.getText() + " " + hashtagField.getText() + newLine
                + "#####################################################################" + newLine + newLine);

        BufferedReader bf = new BufferedReader(new FileReader(outputFile));
        String line;
        while ((line = bf.readLine()) != null) {
            outputArea.append(line + newLine);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JTextField hashtagField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JComboBox<String> outputFormatField;
    private javax.swing.JScrollPane outputPane;
    private javax.swing.JSlider resultsNumberSlider;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField wordsField;
    // End of variables declaration//GEN-END:variables

}
