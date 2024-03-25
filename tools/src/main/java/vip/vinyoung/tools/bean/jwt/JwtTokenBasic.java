package vip.vinyoung.tools.bean.jwt;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.experimental.Accessors;
import vip.vinyoung.tools.utils.TokenUtils;
import java.util.concurrent.TimeUnit;

/**
 * jwt token类
 *
 * @author wangyunshu
 */
@Data
@Accessors(chain = true)
public class JwtTokenBasic {
    private String subject;

    private long expiration = TokenUtils.TOKEN_VALIDITY_TIME; // 过期时间

    public JwtTokenBasic() {

    }

    public JwtTokenBasic(Claims claims) {
        this.subject = claims.getSubject();
    }

    public JwtTokenBasic setExpiration(long time, TimeUnit unit) {
        this.expiration = unit.toSeconds(time);
        return this;
    }
}
