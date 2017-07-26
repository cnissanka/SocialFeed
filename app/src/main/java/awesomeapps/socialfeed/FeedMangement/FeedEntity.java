package awesomeapps.socialfeed.FeedMangement;

/**
 * Created by codeguy on 2/14/16.
 */
public class FeedEntity {
    private int feedid;
    private String Username;
    private String feedSummary;

    public FeedEntity (String username, String feedsummary, int i){
        this.Username = username;
        this.feedSummary = feedsummary;
        this.feedid = i;
    }

    public void setUsername (String username){
        this.Username = username;
    }

    public void setFeedid (int i){
        this.feedid = i;
    }

    public int getFeedid (){
        return this.feedid;
    }

    public void setFeedSummary (String feedSummary){
        this.feedSummary = feedSummary;
    }

    public String getUsername (){
        return this.Username;
    }

    public String getFeedSummary(){
        return this.feedSummary;
    }


}
