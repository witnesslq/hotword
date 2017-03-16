package com.sohu.mrd.hotword.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by yonghongli on 2016/8/4.
 */
public class RedisPool {
    private static final Logger log = LoggerFactory.getLogger(RedisPool.class);
    private final JedisPool pool;

    public RedisPool(String address, int port) {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, address, port, 5000);
    }

    public JedisPool getPool() {
        return pool;
    }

    public static void main(String[] args) {

        try {
            JedisPool pool = new RedisPool("10.10.76.76", 6381).getPool();
            Jedis jedis = pool.getResource();


            Set s = jedis.keys("mipush_*");
            Iterator it = s.iterator();


            while (it.hasNext()) {
                String key = (String) it.next();
                String value = jedis.get(key);
                System.out.println(key + value);
            }
            pool.returnResource(jedis);
        }catch (Exception e){

        }
    }
}
