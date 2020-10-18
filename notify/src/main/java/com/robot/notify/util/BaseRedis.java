package com.robot.notify.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author robot
 * @date 2019/12/9 17:29
 */
@Service
public class BaseRedis {

    @Resource
    private JedisPool jedisPool;

    private static Logger logger = LoggerFactory.getLogger(BaseRedis.class);

    /**
     * list set
     *
     * @param key
     * @param str
     */
    public void set(String key, String str) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, str);
        } catch (Exception e) {
            logger.error("redis set error", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * list set expire
     *
     * @param key
     * @param str
     * @param time
     */
    public void set(String key, String str, int time) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, str);
            jedis.expire(key, time);
        } catch (Exception e) {
            logger.error("redis set error", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * list get
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("redis get error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * list del
     *
     * @param key
     * @return
     */
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("redis del error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * list set
     *
     * @param key
     * @param value
     * @param nxxx
     * @param expx
     * @param time
     * @return
     */
    public String setnx(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key, value, nxxx, expx, time);
        } catch (Exception e) {
            logger.error("redis setnx error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    /**
     * incr
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.incr(key);
        } catch (Exception e) {
            logger.error("redis incr error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    /**
     * decr
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.decr(key);
        } catch (Exception e) {
            logger.error("redis decr error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * hash set
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("redis hset error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean hexists(final String key, final String field){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hexists(key, field);
        } catch (Exception e) {
            logger.error("redis hexists error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * hash get
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hget(key, field);
        } catch (Exception e) {
            logger.error("redis hget error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public Long hdel(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hdel(key, field);
        } catch (Exception e) {
            logger.error("redis hget error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Map<String, String> hgetAll(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            logger.error("redis hgetAll error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public String hmset(String key, Map<String, String> hash) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hmset(key, hash);
        } catch (Exception e) {
            logger.error("redis hmset error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("redis exists error", e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


}
