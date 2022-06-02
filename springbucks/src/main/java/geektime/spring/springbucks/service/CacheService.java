package geektime.spring.springbucks.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CacheService {
    @Resource
    private RedisTemplate<Object, String> redisTemplate;

    /**
     * 添加key
     */
    public <K, V> void add(K key, V value) {
        try {
            if (value != null) {
                redisTemplate
                        .opsForValue()
                        .set(key, JSONObject.toJSONString(value));
            }
        } catch (Exception e) {
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    /**
     * 获得
     */
    public <K> String get(K key) {
        String value;
        try {
            value = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            throw new RuntimeException("从redis缓存中获取缓存数据失败");
        }
        return value;
    }

    /**
     * 删除key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
