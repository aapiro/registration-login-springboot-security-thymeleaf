package com.ilimitech.webapp.realstate.frontend.xapp;//package com.ilimitech.webapp.realstate.infraestructure.xapp;
//
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.TwitterFactory;
//import twitter4j.auth.AccessToken;
//
//public class Xapp {
//    public static void main(String[] args) {
//        // Configurar las claves de API y tokens de acceso
//        String consumerKey = "YOUR_CONSUMER_KEY";
//        String consumerSecret = "YOUR_CONSUMER_SECRET";
//        String accessToken = "YOUR_ACCESS_TOKEN";
//        String accessTokenSecret = "YOUR_ACCESS_TOKEN_SECRET";
//
//        // Crear instancia de Twitter
//        Twitter twitter = new TwitterFactory().getInstance();
//        twitter.setOAuthConsumer(consumerKey, consumerSecret);
//        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
//
//        try {
//            // Publicar un tweet
//            twitter.updateStatus("¡Hola, mundo! Este es un tweet desde mi aplicación Java.");
//            System.out.println("Tweet publicado con éxito.");
//        } catch (TwitterException e) {
//            e.printStackTrace();
//        }
//    }
//}
