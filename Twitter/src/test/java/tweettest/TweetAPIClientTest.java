package tweettest;


import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }
    @Test(enabled =true)
    public void testGetUserTimeTweet(){
        String tweet="Joe Biden more pro police than Trump.";
        ValidatableResponse response= this.tweetAPIClient.getUserTimeTweet(1308991499418431493l);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled =true)
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="RestAPI Team @ 2"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
       String actualTweet= response.extract().body().path("text");
       Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = false)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
       // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(401);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
      response= this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was unsuccessful
       response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }

    @Test(enabled = false)
    public void testDelete(){
        String tweet="We are learning RestAPI Automation62a555d6-9492-4e0a-8b37-492873ba169a";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1305390241839230976l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testRetweet(){
        String retweet="RT @BlakeDontCrack: Joe Biden more pro police than Trump.";
        ValidatableResponse response=this.tweetAPIClient.createRetweet(1308991499418431493l);
        // Verify that the tweet was successfully retweeted
       response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }

    @Test(enabled = true)
    public void testUnReTweet(){
        String tweet="Joe Biden more pro police than Trump.";
        ValidatableResponse response=this.tweetAPIClient.unReTweet(1308991499418431493l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test(enabled = true)
    public void testShowTweetID(){
        String tweet="RestAPI Team @ 2";
        ValidatableResponse response=this.tweetAPIClient.showTweetID(1308829567298285568l);
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testReadTweetID(){
        String expectedTweet="RestAPI Team @ 2";
        ValidatableResponse response=this.tweetAPIClient.readTweet(1308829567298285568l);
// Verify that the tweet was successfully deleted
        System.out.println(response.extract().body().asString());
       response.statusCode(200);
       String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(expectedTweet,actualTweet);
    }
    @Test(enabled = true)
    public void testGetStatusLookUp(){
        String expectedTweet="Let’s get me elected, just for the hell of it";
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(1235809247977324544l);
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
//        String actualTweet=response.extract().body().path("text");
//        Assert.assertEquals(expectedTweet,actualTweet);
    }
    @Test(enabled = true)
    public void FavoritesTweetID(){
        String tweet="RestAPI Team @ 29df68de3-42d8-421f-984f-3a72245fa35b";
        ValidatableResponse response=this.tweetAPIClient.createFavoritesTweet(1308827868026277888l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void unLikeFavoritesTweet(){
        String tweet="RestAPI Team @ 29df68de3-42d8-421f-984f-3a72245fa35b";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1308827868026277888l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void TestCreateTweetFavoritesWithInvalidEndPoint(){
        String tweet="RestAPI Team @ 2";
        ValidatableResponse response=this.tweetAPIClient.createTweetWithWrongFavoritesInvalidEndPoint(1308829567298285568l);
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }
    @Test(enabled = true)
    public void testGetRetweets(){
        String tweet="Our GREAT RALLY tonight in Pennsylvania. Tremendous energy! #MAGA https://t.co/kL29rJe6sh";
        ValidatableResponse response=this.tweetAPIClient.getRetweets(1308594096005697541l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testFavoriteListTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweet("MOHAMMADKISLAM7");
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200, actualCode);
    }
    @Test(enabled = true)
    public void testFavoriteListTweetWithInvalidEndpoint(){
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweetWithInvalidEndPoint("MOHAMMADKISLAM7");
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200, actualCode);
    }

    @Test(enabled = true)
    public void testGetHomeTimeLineTweets(){
        ValidatableResponse response=this.tweetAPIClient.getTimeLineTweet();
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200,actualCode);
    }
    @Test(enabled = true)
    public void testGetTimeLineTweetWithInvalidEndPoint(){
        ValidatableResponse response=this.tweetAPIClient.getTimeLineTweetWithInvalidEndPoint();
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }

}
