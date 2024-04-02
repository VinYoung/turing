package vip.vinyoung.tools.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.vinyoung.cache.impl.RedisHelper;
import vip.vinyoung.cache.service.CacheHelper;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class CacheHelperConfig {
    private String host;

    private String port;

    @Bean
    public CacheHelper getCacheHelper() {
        RedissonClient redissonClient = redissonClient();
        return new RedisHelper(redissonClient);
    }

    private RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setConnectionPoolSize(1000); // 设置连接池大小
        return Redisson.create(config);
    }
}
