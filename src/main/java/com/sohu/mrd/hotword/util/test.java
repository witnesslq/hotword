package com.sohu.mrd.hotword.util;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by yonghongli on 2017/1/16.
 */
public class test {
    public static JedisPool jedisPool = null;


public  static void main(String[] args){
     Jedis jedis=null;
    try {
        jedis =jedisPool.getResource();
     //     jedis.hget("3-CRRUYP-20170117-100442","num");
//        jedis.del("2-JHEMVG-20170117-100559");
//       jedis.del("3-CRRUYP-20170117-100442");
//        jedis.del("1-PVPRUM-20170116-114743");
//        jedis.del("1-YEFUUN-20170116-151639");
//        jedis.del("1-DOBLEY-20170116-172543");
        System.out.println(jedis.hget("4-FYVXCU-20170221-185610","num"));

    }catch(Exception e){
      e.printStackTrace();
    }finally {
        //回收jedis实例
        if (jedis != null){
            jedis.close();
        }
    }

}

     static
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinEvictableIdleTimeMillis(60000);

        config.setTimeBetweenEvictionRunsMillis(30000);


        jedisPool =new JedisPool(config, "10.10.93.181", 6100,20000);

    }
}
