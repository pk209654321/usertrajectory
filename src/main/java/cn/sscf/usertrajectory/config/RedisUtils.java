package cn.sscf.usertrajectory.config;


import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 获取自增序列, 不设置失效时间.
     * @param key
     * @return
     */
    public Long incr(String key) {
        return incr(key, null);
    }

    /**
    * 获取自增序列.
    * @param key
    * @param liveTime
    * @return
    */
    public Long incr(String key, Long liveTime) {
        RedisAtomicLong atomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = atomicLong.getAndIncrement();

        if ((null == increment || increment.longValue() == 0) && (liveTime != null && liveTime > 0)) {//初始设置过期时间
            atomicLong.expire(liveTime, TimeUnit.SECONDS);
        }

        return increment;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 存入数据到sorted set
     * @param key
     * @param event
     */
    public boolean zSet(String key, Object value, Double score) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.add(key, value, score);
    }

    /**
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Long sAdd(String key, Object value) {
        SetOperations sOps = redisTemplate.opsForSet();
        return sOps.add(key, value);
    }

    /**
    *
    * @param key
    * @param value
    * @param score
    * @return
    */
    public Object sPop(String key) {
        SetOperations sOps = redisTemplate.opsForSet();
        return sOps.pop(key);
    }

    /**
    *
    * @param key
    * @param value
    * @param score
    * @return
    */
    public Set sMembers(String key) {
        SetOperations sOps = redisTemplate.opsForSet();
        return sOps.members(key);
    }

    /**
    *
    * @param key
    * @param value
    * @param score
    * @return
    */
    public Long sSize(String key) {
        SetOperations sOps = redisTemplate.opsForSet();
        return sOps.size(key);
    }

    /**
     * pop 对象.
     * @param key
     * @param clazz
     * @return
     */
    public <T> T sPop(String key, Class<T> clazz) {
        T t = null;
        SetOperations sOps = redisTemplate.opsForSet();
        String str = (String) sOps.pop(key);
        if (StringUtils.isNotBlank(str)) {
            t = JSON.parseObject(str, clazz);
        }
        return t;
    }

    /**
     * 取出sorted set中的元素
     * @param key
     * @param event
     */
    public Set<TypedTuple<Object>> zGetSet(String key, long start, long end) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.rangeWithScores(key, start, end);
    }

    /**
     * 从高到低的排序集中获取元组的集合
     * @param key
     * @param event
     */
    public Set<Object> reverseRange(String key, long start, long end) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.reverseRange(key, start, end);
    }

    /**
     *删除sorted set中的元素
     * @param key
     * @param event
     */
    public void removeSortedSet(String key, Object... values) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.remove(key, values);
    }

    /**
     * hash 设置.
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public void hSet(String key, String hashKey, String value) {
        HashOperations hOps = redisTemplate.opsForHash();
        hOps.put(key, hashKey, value);
    }

    /**
     * 获取hash 值.
     * @param key
     * @param hashKey
     * @return
     */
    public String hGet(String key, String hashKey) {
        HashOperations hOps = redisTemplate.opsForHash();
        String str = (String) hOps.get(key, hashKey);
        return str;
    }

    /**
     * 获取hash对象.
     * @param key
     * @param hashKey
     * @param clazz
     * @return
     */
    public <T> T hGet(String key, String hashKey, Class<T> clazz) {
        T t = null;
        String str = hGet(key, hashKey);
        if (StringUtils.isNotBlank(str)) {
            t = JSON.parseObject(str, clazz);
        }
        return t;
    }

    /**
     * 删除hashkey.
     * @param key
     * @param hashKey
     * @return
     */
    public Long hDel(String key, String hashKey) {
        HashOperations hOps = redisTemplate.opsForHash();
        return hOps.delete(key, hashKey);
    }

    /**
     * hash 条数.
     * @param key
     * @return
     */
    public Long hSize(String key) {
        HashOperations hOps = redisTemplate.opsForHash();
        return hOps.size(key);
    }

    /**
     * 创建锁.<br>
     * 默认不等待.
     * @param key
     * @param timeout 锁失效时间
     * @param unit 单位
     * @return
     */
    public RedisLock lock(String key, long timeout, TimeUnit unit) {
        long millis = unit.toMillis(timeout);
        return lock(key, 0, (int) millis);
    }

    /**
     * 创建锁.
     * @param key
     * @param timeoutMsecs 锁等待时间
     * @param expireMsecs 锁超时时间
     * @return
     */
    public RedisLock lock(String key, int timeoutMsecs, int expireMsecs) {
        return new RedisLock(redisTemplate, key, timeoutMsecs, expireMsecs);
    }

    /**
     * 设置失效时间.
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }
}
