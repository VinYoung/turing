package vip.vinyoung.tools.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vip.vinyoung.cache.service.CacheHelper;
import vip.vinyoung.tools.bean.jwt.JwtTokenBasic;
import vip.vinyoung.tools.config.ErrorConstants;
import vip.vinyoung.tools.enums.ParameterEnum;
import vip.vinyoung.tools.exception.AuthLoginException;
import vip.vinyoung.tools.exception.ServiceException;
import vip.vinyoung.tools.service.Log;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * token工具类
 *
 * @since 2024-03-25
 * @author Vin.Young
 */
@Slf4j
@Service
public class TokenUtils implements Log {
    /**
     * token在redis的过期时间，固定为4个小时
     */
    public static final long TOKEN_VALIDITY_TIME = TimeUnit.HOURS.toSeconds(4);

    /**
     * 超级账号ID
     */
    @Value("${system.internal.admin.id}")
    private String INTERNAL_ADMIN_ID;

    /**
     * JWT公钥
     */
    @Value("${system.internal.secret}")
    private String SYSTEM_SECRET;

    @Resource
    private CacheHelper cacheHelper;

    @Override
    public Logger getLogger() {
        return log;
    }

    @PostConstruct
    public void init() {
        initInternalToken();
    }

    /**
     * 获取用户token, 有效期默认4个小时
     * token目前仅包含userID的信息
     * <br>
     *
     * @author wangyunshu
     */
    public String createToken(JwtTokenBasic basic) {
        debug("创建token: {}", basic.getSubject());
        String token = Jwts.builder()
            .setSubject(basic.getSubject())
            .setId(CommonUtils.uuid())
            .signWith(SignatureAlgorithm.HS512, SYSTEM_SECRET)
            .setExpiration(new Date(TOKEN_VALIDITY_TIME))
            .compact();
        long expiration = basic.getExpiration();
        cacheHelper.put(ParameterEnum.USER.name(), token, String.valueOf(expiration), expiration);
        return token;
    }

    /**
     * 解析token
     * <br>
     *
     * @author wangyunshu
     */
    public JwtTokenBasic parseToken(String token) {
        debug("解析token: {}", token);
        String expiration = cacheHelper.get(ParameterEnum.USER.name(), token, String.class);
        if (StringUtils.isEmpty(expiration)) {
            debug("token已过期", token);
            throw new AuthLoginException(ErrorConstants.ACCOUNT_CERTIFICATION);
        } else {
            debug("token剩余有效时间: {}", expiration);
        }
        try {
            Claims body = Jwts.parser().setSigningKey(SYSTEM_SECRET).parseClaimsJws(token).getBody();
            debug("刷新token失效时间: {}", token);

            // 每次请求后刷新token失效时间
            cacheHelper.put(ParameterEnum.USER.name(), token, expiration, Long.parseLong(expiration));
            return new JwtTokenBasic(body);
        } catch (Exception ex) {
            throw new ServiceException(ErrorConstants.ACCOUNT_001, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 初始化系统token
     * <br>
     * 如果有则使用, 如果没有则重新生成
     *
     * @author wangyunshu
     */
    private String initInternalToken() {
        // 获取当前系统op_token
        debug("初始化系统token >>>");
        RLock lock = cacheHelper.getLock(ParameterEnum.SYSTEM_OP_TOKEN_LOCK.name());
        try {
            lock.lock();
            // 如果已经初始化则不重新生成
            String token = cacheHelper.get(ParameterEnum.USER.name(), ParameterEnum.SYSTEM_OP_TOKEN.name(),
                String.class);
            if (StringUtils.isNotEmpty(token)) {
                debug("初始化完成 <<< {}", token);
                return token;
            }
            // 未初始化, 重新生成token
            debug("重新生成系统token ...");
            token = createToken(new JwtTokenBasic().setSubject(INTERNAL_ADMIN_ID));
            cacheHelper.put(ParameterEnum.USER.name(), ParameterEnum.SYSTEM_OP_TOKEN.name(), token,
                TOKEN_VALIDITY_TIME);
            debug("初始化完成 <<< {}", token);
            return token;
        } finally {
            cacheHelper.unLock(lock);
        }
    }
}
