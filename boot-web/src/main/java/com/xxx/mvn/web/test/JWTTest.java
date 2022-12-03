package com.xxx.mvn.web.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:TuoTuo
 * @createDate:2022/11/29 9:08
 * @description:
 * 1.Header     {   "alg": "HS256", "typ": "JWT" }
 *          JWT头是一个描述JWT元数据的JSON对象
 *          alg属性表示签名使用的算法，默认为HMAC SHA256（写为HS256）；
 *          typ属性表示令牌的类型，JWT令牌统一写为JWT。最后，使用Base64 URL算法将上述JSON对象转换为字符串保存
 * 2.Payload
 *          有效载荷部分，是JWT的主体内容部分，也是一个JSON对象，包含需要传递的数据。 JWT指定七个默认字段供选择
 *          iss：发行人
 *          exp：到期时间
 *          sub：主题
 *          aud：用户
 *          nbf：在此之前不可用
 *          iat：发布时间
 *          jti：JWT ID用于标识该JWT
 *          这些预定义的字段并不要求强制使用。除以上默认字段外，我们还可以自定义私有字段，一般会把包含用户信息的数据放到payload中
 *          默认情况下JWT是未加密的，因为只是采用base64算法，拿到JWT字符串后可以转换回原本的JSON数据，
 *          任何人都可以解读其内容，因此不要构建隐私信息字段，比如用户的密码一定不能保存到JWT中，以防止信息泄露。
 *          JWT只是适合在网络中传输一些非敏感的信息
 * 3.Signature
 *          默认情况下为HMAC SHA256
 *  Header.Payload.Signature
 *
 */
public class JWTTest {
    /**
     * JWT 加密
     */
    @Test
    public void testJWT(){
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg","HS256");
        headerClaims.put("typ","JWT");
        Map<String, Object> payloadClaims = new HashMap<>();
        payloadClaims.put("userId","23");
        payloadClaims.put("sub","1234567890");

        Calendar calendar = Calendar.getInstance();
        Date iatTime =  calendar.getTime();
        calendar.add(Calendar.HOUR,5);
        Date date = calendar.getTime();


        String token = JWT.create()
                .withHeader(headerClaims)
                .withPayload(payloadClaims)
                .withClaim("iat",iatTime)
                .withClaim("aa","555")
//                .withExpiresAt(date)
                .sign(Algorithm.HMAC256("!34ADAS"));
        System.out.println(token);
        //如果携带了 超时时间每次生成的均不一样
    }

    @Test
    public void testJWTRequire(){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("!34ADAS")).build();
        String token  = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhYSI6IjU1NSIsInN1YiI6IjEyMzQ1Njc4OTAiLCJ1c2VySWQiOiIyMyIsImlhdCI6MTY2OTcyMjE5Nn0.e5uY5YghzZiKJDuNDW-nL5--LXeoaW18-1GkkW6u7qo";
        DecodedJWT jwt  = verifier.verify(token);
        String header = jwt.getHeader();
        String payload = jwt.getPayload();
        String signature = jwt.getSignature();
        System.out.println(header);
        System.out.println(payload);
        System.out.println(signature);
        String subject = jwt.getSubject();
        System.out.println("----"+subject);

        Date expiresAt = jwt.getExpiresAt();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        System.out.println(sdf.format(expiresAt));

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(payload);
        String payloadString = new String(decode);
        System.out.println(payloadString);

        Claim iat = jwt.getClaim("iat");
        System.out.println(iat.asDate());
        Claim userId = jwt.getClaim("userId");
        Claim exp = jwt.getClaim("exp");
        Claim exaap = jwt.getClaim("aa");
        System.out.println(userId.asString());
        System.out.println(exp.asDate());
        System.out.println(exaap.asString());
    }
}
