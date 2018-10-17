package pl.tmaj.hsbc;

import org.springframework.http.HttpHeaders;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.security.MessageDigest.getInstance;

public class Utils {

    public static String calculateUserId(HttpHeaders headers) {
        String input = headers.get("user-agent").toString() + headers.get("accept-language").toString();
        String output = "NO_USER";
        try {
            MessageDigest md5 = getInstance("MD5");
            md5.update(UTF_8.encode(input));
            output = String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return output;
    }
}
