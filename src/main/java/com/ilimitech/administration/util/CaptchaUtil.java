package com.ilimitech.administration.util;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

public class CaptchaUtil {

    //Creating Captcha Object
    public static Captcha createCaptcha(Integer width, Integer height) {

        return new Captcha.Builder(width, height)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(), new DefaultWordRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
    }

    //Converting to binary String
    public static String encodeCaptcha(Captcha captcha) {
        String image = null;
        try {
            ByteArrayOutputStream bos= new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(),"jpg", bos);
            byte[] byteArray= Base64.getEncoder().encode(bos.toByteArray());
            image = new String(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public static <T> void getCaptcha(T obj) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        try {
            Method setHiddenCaptcha = obj.getClass().getMethod("setHiddenCaptcha", String.class);
            Method setCaptcha = obj.getClass().getMethod("setCaptcha", String.class);
            Method setRealCaptcha = obj.getClass().getMethod("setRealCaptcha", String.class);

            setHiddenCaptcha.invoke(obj, captcha.getAnswer());
            setCaptcha.invoke(obj, ""); // value entered by the User
            setRealCaptcha.invoke(obj, CaptchaUtil.encodeCaptcha(captcha));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error setting captcha properties", e);
        }
    }
}