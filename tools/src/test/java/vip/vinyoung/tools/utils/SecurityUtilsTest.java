package vip.vinyoung.tools.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SecurityUtilsTest {
    @Test
    public void testPasswordEncrypt() {
        String pwd = SecurityUtils.passwordEncrypt("aaaaa");
        System.out.println(pwd);
        Assertions.assertNotNull(pwd);
    }
}
