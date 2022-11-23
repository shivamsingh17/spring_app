package com.stackroute.moneynmonetary.otpservice.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
        @Autowired
        private JavaMailSender mailSender;

        private static final Integer EXPIRE_MINS = 5;
        private final LoadingCache<String, Integer> otpCache;
        @Value("${spring.mail.username}") private String sender;

        public OtpService(){
                super();
                otpCache = CacheBuilder.newBuilder().
                        expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                        .build(new CacheLoader<String, Integer>() {
                                public Integer load(String key) {
                                        return 0;
                                }
                        });
        }

        public void sendOtp(String receiver, String msg, String subject) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(sender);
                message.setTo(receiver);
                message.setText(msg);
                message.setSubject(subject);
                mailSender.send(message);
        }

        //key here will be customerid
        public int generateOtp(String key) {
                String numbers = "0123456789";
                // Using random method
                Random random_method = new Random();
                StringBuilder otp = new StringBuilder(new String(""));
                for (int i = 0; i < 6; i++)
                {
                        // Use of charAt() method : to get character value
                        // Use of nextInt() as it is scanning the value as int
                        otp.append(numbers.charAt(random_method.nextInt(numbers.length())));
                }
                int otpInt=Integer.parseInt(otp.toString());
                otpCache.put(key, otpInt);
                return otpInt;
        }

        //This method is used to return the OPT number against Key->Key values is username
        public int getOtp(String key){
                try{
                        return otpCache.get(key);
                }catch (Exception e){
                        return 0;
                }
        }


        //This method is used to clear the OTP catched already
        public void clearOTP(String key){
                otpCache.invalidate(key);
        }
}
