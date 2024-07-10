package com.ilimitech.webapp.realstate.frontend.xapp;//package com.ilimitech.webapp.realstate.infraestructure.xapp;
//
//import com.twitter.clientlib.ApiClient;
//import com.twitter.clientlib.TwitterCredentialsBearer;
//import com.twitter.clientlib.TwitterCredentialsOAuth2;
//import com.twitter.clientlib.api.TweetsApi;
//import com.twitter.clientlib.api.TwitterApi;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TwitterConfig {
//
//    @Value("${twitter.apiKey}")
//    private String apiKey;
//
//    @Value("${twitter.apiSecretKey}")
//    private String apiSecretKey;
//
//    @Value("${twitter.accessToken}")
//    private String accessToken;
//
//    @Value("${twitter.accessTokenSecret}")
//    private String accessTokenSecret;
//
//    @Value("${twitter.bearerToken}")
//    private String bearerToken;
//
////        @Bean
////    public TwitterApi twitterClient() {
////        return new TwitterApi(new TwitterCredentialsOAuth2(apiKey, apiSecretKey, accessToken, accessTokenSecret));
////    }
//    @Bean
//    public TwitterApi apiClient() {
//        TwitterApi apiInstance = new TwitterApi(new TwitterCredentialsOAuth2(apiKey, apiSecretKey, accessToken, accessTokenSecret));
//        TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(bearerToken);
//        apiInstance.setTwitterCredentials(credentials);
//    }
//}
