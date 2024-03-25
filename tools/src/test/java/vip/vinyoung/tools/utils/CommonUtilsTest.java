package vip.vinyoung.tools.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommonUtilsTest {
    @Test
    public void testUuid() {
        String uuid = CommonUtils.uuid();
        Assertions.assertNotNull(uuid);
    }
}
