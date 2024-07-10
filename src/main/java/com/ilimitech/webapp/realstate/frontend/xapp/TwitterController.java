package com.ilimitech.webapp.realstate.frontend.xapp;//package com.ilimitech.webapp.realstate.infraestructure.xapp;
//
//import com.twitter.clientlib.ApiClient;
//import com.twitter.clientlib.ApiException;
//import com.twitter.clientlib.api.TweetsApi;
//import com.twitter.clientlib.model.TweetCreateRequest;
//import com.twitter.clientlib.model.TweetCreateResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
////import org.jetbrains.annotations.NotNull;
//@Controller
//public class TwitterController {
//
//
//    private final ApiClient apiClient;
//
//    @Autowired
//    public TwitterController(ApiClient apiClient) {
//        this.apiClient = apiClient;
//    }
//
//    @GetMapping("/twitter")
//    public String index() {
//        return "dashboard/applications/twitter";
//    }
//    @PostMapping("/tweet")
//    public String postTweet(@RequestParam("tweet") String tweetContent, Model model) {
//        try {
//            TweetsApi tweetsApi = new TweetsApi();
//            TweetCreateRequest request = new TweetCreateRequest();
//            request.setText(tweetContent);
//            TweetCreateResponse response = tweetsApi.createTweet(request).execute();
//            if (response != null && response.getData() != null) {
//                model.addAttribute("message", "Tweet publicado con éxito: " + response.getData().getText());
//            } else {
//                model.addAttribute("message", "Error al publicar el tweet: respuesta vacía.");
//            }
//        } catch (ApiException e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Error al publicar el tweet: " + e.getResponseBody());
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Error al publicar el tweet: " + e.getMessage());
//        }
//        return "dashboard/applications/twitter";
//    }
////    @PostMapping("/tweet")
////    public String postTweet(@RequestParam("tweet") String tweetContent, Model model) {
////        Set<String> tweetFields = new HashSet<>();
////        tweetFields.add("author_id");
////        tweetFields.add("id");
////        tweetFields.add("created_at");
////        try {
////            Get2TweetsIdResponse result = twitterClient.tweets().findTweetById("20")
////                    .tweetFields(tweetFieldstwitterClient
////                    .execute();
////            TweetV2.TweetData tweet = twitterClient.postTweet(tweetContent);
////            model.addAttribute("message", "Tweet publicado con éxito: " + tweet.getText());
////        } catch (Exception e) {
////            e.printStackTrace();
////            model.addAttribute("message", "Error al publicar el tweet: " + e.getMessage());
////        }
////        return "index";
////    }
////    @PostMapping("/tweet")
////    public String postTweet(@RequestParam("tweet") String tweetContent, Model model) {
////        try {
////            Tweet tweet = twitterClient.postTweet(tweetContent);
////            model.addAttribute("message", "Tweet publicado con éxito: " + tweet.getText());
////        } catch (Exception e) {
////            e.printStackTrace();
////            model.addAttribute("message", "Error al publicar el tweet: " + e.getMessage());
////        }
////        return "dashboard/applications/twitter";
////    }
//}
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import twitter4j.Twitter;
////import twitter4j.TwitterException;
////import twitter4j.TwitterFactory;
////import twitter4j.auth.AccessToken;
////
////@Controller
////public class TwitterController {
////
////    @GetMapping("/twitter")
////    public String index() {
////        return "dashboard/applications/twitter";
////    }
////
////    @PostMapping("/tweet")
////    public String postTweet(@RequestParam("tweet") String tweet, Model model) {
////        Twitter twitter = getTwitter();
////
////        try {
////            twitter.updateStatus(tweet);
////            model.addAttribute("message", "Tweet publicado con éxito.");
////        } catch (TwitterException e) {
////            e.printStackTrace();
////            model.addAttribute("message", "Error al publicar el tweet.");
////        }
////
////        return "dashboard/applications/twitter";
////    }
////
////    private static @NotNull Twitter getTwitter() {
////        String consumerKey = "qlB9O7quWffHwNlfgzRA4TZOk";
////        String consumerSecret = "qBBZXk7PliFW7zRLb64wZVTw8KaXI8AHvgnI1xJVAHXeOeVrQ3";
////        String accessToken = "1733595001-QB2aWZKCJRMDDYOopjtrcRnYbypOZ4s663zajbc";
////        String accessTokenSecret = "Stx0qni3EcEEo0PiphTMqx1P7F0CqtLwJ76TJroTM4KwY";
////
////        Twitter twitter = new TwitterFactory().getInstance();
////        twitter.setOAuthConsumer(consumerKey, consumerSecret);
////        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
////        return twitter;
////    }
////}
