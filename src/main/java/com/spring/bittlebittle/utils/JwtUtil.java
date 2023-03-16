package com.spring.bittlebittle.utils;

import com.google.gson.Gson;
import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.UserJwt;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


@Configuration
@PropertySource("classpath:config/application.properties")
public class JwtUtil {


    private Logger log = LogManager.getLogger("case3");

    @Value("${secretKey}")
    private String SECRETKEY;

    private final static long ACCESS_TOKEN_EXPTIME = 1000*30*1;

    private final static long REFRESH_TOKEN_EXPTIME = 1000*60*60*24*14;

    @Autowired
    private Gson gson;

    @Autowired
    private UserService service;

//    public String createJwt(String subject, Long expTime) {
//
//        if(expTime <= 0) {
//            throw new RuntimeException("留뚮즺 �떆媛꾩씠 0蹂대떎 而ㅼ빞 �븳�떎");
//        }
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
//        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
//        String token = Jwts.builder()
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPTIME))
//                .signWith(signingKey)
//                .compact();
//
//        return token;
//    }

    public String createAccessJwt(String subject) {
        if(ACCESS_TOKEN_EXPTIME <= 0) {
            throw new RuntimeException("留뚮즺 �떆媛꾩씠 0蹂대떎 而ㅼ빞 �븳�떎");
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPTIME))
                .signWith(signatureAlgorithm,signingKey)
                .compact();

    }

    public String createRefreshJwt(String subject) {
        if(REFRESH_TOKEN_EXPTIME <= 0) {
            throw new RuntimeException("留뚮즺 �떆媛꾩씠 0蹂대떎 而ㅼ빞 �븳�떎");
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        String refreshToken = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPTIME))
                .signWith(signatureAlgorithm,signingKey)
                .compact();

        // subject 瑜� 湲곕컲�쑝濡� userJwt 媛� 議댁옱�븯�뒗吏� 議고쉶
        UserJwt userJwt = service.getUserJwtBySubject(UserJwt.builder()
                .subject(subject).build());
        // �샊�떆�씪�룄 userJwt �뿉 userNo �씠 議댁옱�븳�떎硫� �뾽�뜲�씠�듃瑜� �빐以��떎.
        if(userJwt != null) {
            userJwt = service.editUserJwt(UserJwt.builder()
                    .userJwtIdx(userJwt.getUserJwtIdx())
                    .refreshToken(refreshToken)
                    .build());
            log.debug("db �닔�젙");
        } else {
            userJwt = service.createUserJwt(UserJwt.builder()
                    .subject(subject)
                    .refreshToken(refreshToken)
                    .build());
            log.debug("db �깮�꽦");
        }
        // db�뿉 議댁옱�븯吏� �븡�뒗�떎硫� userJwt �뀒�씠釉붿뿉 refresh token �깮�꽦
        return userJwt.getUserJwtIdx();
    }

    // 濡쒓렇�븘�썐�쓣 �쐞�빐 access token 留뚮즺 諛� db �뿉 ���옣�맂 refresh �궘�젣
    public boolean removeRefreshJwt(String jwt, UserJwt userJwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                    .parseClaimsJws(jwt)
                    .getBody();
            claims.setExpiration(new Date(System.currentTimeMillis()));
            log.debug("access token�쓽 �쑀�슚湲곌컙 留뚮즺 蹂�寃�");
            if (service.removeUserJwt(userJwt) == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.debug("access token 湲곌컙 留뚮즺");
            return false;
        }
    }

    // �뼱�꽭�뒪 �넗�겙 �뿤�뜑 �꽕�젙
    public void setHeaderAccessToken(HttpHeaders headers, String accessJwt) {
        headers.set("Authorization", "bearer "+ accessJwt);
    }

    // 由ы봽�젅�떆 �넗�겙 �뿤�뜑 �꽕�젙
//    public void setHeaderRefreshToken(HttpHeaders headers, String refreshJwt) {
//        headers.set("RefreshToken", "bearer "+ refreshJwt);
//    }

    // DB 由ы봽�젅�떆 �넗�겙�쓽 idx 媛� �뿤�뜑 �꽕�젙
    public void setHeaderRefreshToken(HttpHeaders headers, String userJwtIdx) {
        headers.set("RefreshTokenIdx", userJwtIdx);
    }

//    public boolean registerRefreshToken(UserAuthentication userAuthentication) {
//        return service.registerJwtWithIdx(userAuthentication);
//    }

    // Request�쓽 Header�뿉�꽌 AccessToken 媛믪쓣 媛��졇�샃�땲�떎. "Authorization" : "token'
    public String resolveAccessToken(HttpEntity entity) {
        if(entity.getHeaders().get("Authorization") != null ) {
            log.debug(entity.getHeaders().get("Authorization").toString().replaceAll("\\[", "").replaceAll("\\]", "").substring(7));
            return entity.getHeaders().get("Authorization").toString().replaceAll("\\[", "").replaceAll("\\]", "").substring(7);
        }
        return null;
    }

    // Request�쓽 Header�뿉�꽌 RefreshToken�쓽 idx 媛믪쓣 媛��졇�샃�땲�떎. "RefreshToken" : "db�쓽 idx 媛�'
    public String resolveRefreshToken(HttpEntity entity) {
        if (entity.getHeaders().get("RefreshTokenIdx") != null) {
            log.debug(entity.getHeaders().get("RefreshTokenIdx").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
            return entity.getHeaders().get("RefreshTokenIdx").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        } return null;
    }

    // payload �뿉 ���옣�맂 id 議고쉶
    public String getSubject(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }


    // �넗�겙 �쑀�슚�꽦 寃��궗
    public boolean validateToken(String jwt, UserJwt userJwt) {
        try {
            // access token �쑀�슚�꽦 寃��궗
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                    .parseClaimsJws(jwt)
                    .getBody();
            log.debug("�쑀�슚�븳 access token �엯�땲�떎.");
            return !claims.getExpiration().before(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            log.debug("留뚮즺�맂 access token �엯�땲�떎.");
            return validateRefreshToken(jwt, userJwt);
        }
    }

    public boolean validateRefreshToken(String jwt, UserJwt userJwt) {
        // refresh token �쑀�슚�꽦 寃��궗
        // idx �쓣 媛�吏�怨� db�뿉 ���옣�맂 refresh token �쓣 媛��졇�삩 �뮘
        String refreshToken = service.getUserJwt(userJwt).getRefreshToken();
        // 媛��졇�삩 refresh token �쓣 �떎�떆 寃�利�
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                .parseClaimsJws(refreshToken)
                .getBody();
        try {
            log.debug("�쑀�슚�븳 refresh token �엯�땲�떎.");
            return !claims.getExpiration().before(new Date(System.currentTimeMillis()));
        } catch (Exception e2) {
            log.debug("留뚮즺�맂 refresh token �엯�땲�떎.");
            return false;
        }
    }
}
