package mainPackage;

import java.util.List;
import twitter4j.User;

public class Tweet {
    private String id, language, date, content, location, inReplyTo;
    private List<String> hashtags;
    private User user;
    
    public Tweet(String id, User user, String language, String date, String content, String location, List<String> hashtags, String inReplyTo){
        this.id = id;
        this.user = user;
        this.language = language;
        this.date = date;
        this.content = content;
        this.location = location;
        this.hashtags = hashtags; 
        this.inReplyTo = inReplyTo;
    }

    protected String getInReplyTo() {
        return inReplyTo;
    }

    protected String getId() {
        return id;
    }

    protected User getUser() {
        return user;
    }

    protected String getLanguage() {
        return language;
    }

    protected String getDate() {
        return date;
    }

    protected String getContent() {
        return content;
    }
    
    protected String getLocation(){
        return location;
    }

    protected List<String> getHashtags() {
        return hashtags;
    }
    
    protected void printValues(){
        System.out.println("User: " + user);
        System.out.println("Language: " +language);
        System.out.println("Date: " + date);
        System.out.println("Content: " + content);
        System.out.println("Location: " + location);
        
        System.out.println("Hashtags:");
        for(String hashtag: hashtags){
            System.out.println(hashtag);
        }
    }
}
