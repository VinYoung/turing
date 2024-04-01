package vip.vinyoung.tools.utils;

import org.apache.commons.lang3.StringUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class SecurityUtils {
    /**
     * 对密码进行加密，加密方式为先base64后进行sha256计算
     *
     * @param password 密码
     * @return 加密结果
     */
    public static String passwordEncrypt(String password) {
        if (StringUtils.isEmpty(password)) {
            return StringUtils.EMPTY;
        }
        return calculateSha256(base64(password));
    }

    /**
     * base64编码
     * @param str 字符串
     *
     * @return
     */
    public static String base64(String str) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String calculateSha256(String input) {
        if (StringUtils.isEmpty(input)) {
            return StringUtils.EMPTY;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // 将字节数组转换为十六进制字符串表示
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }
}
