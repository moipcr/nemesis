package com.nemesis.moises.autoseleccionador;

import java.util.LinkedList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Moises on 26/12/2014.
 */
public class TwitterClass {

    boolean login(String user) {

        return true;
    }

    List getTimeline(String hashtag) {
        List lista_nueva = new LinkedList();
        //Twitter Conf.
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("kZMSftQUM0kJA0yuCU4P8CPv9")
                .setOAuthConsumerSecret("hrfcrd1nTUx8TRyUm2TFLMDimtjbczmNVxldfbePqAXXUX2pGz")
                .setOAuthAccessToken("1883959296-P65T0kwIHt6kx86LtzVyalzzZuXaxZawdjFwtZh")
                .setOAuthAccessTokenSecret("t2ZhAOkLxLeDf0EtxI5SxjaIrvJzNRsMBArDvy1BfY0B1");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();


        List<Status> statuses = null;
        try {

            QueryResult result = twitter.search(new Query("#"+hashtag));
            statuses = result.getTweets();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println("Showing home timeline.");

        for (Status status : statuses) {

           lista_nueva.add(status.getUser().getName());

        }
        return lista_nueva;

    }
}