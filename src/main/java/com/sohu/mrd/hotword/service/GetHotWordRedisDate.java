package com.sohu.mrd.hotword.service;

import com.sohu.mrd.framework.redis.client.CodisRedisClient;
import com.sohu.mrd.framework.redis.client.codis.JedisResourcePool;
import com.sohu.mrd.hotword.util.SegmentKit;
import com.sohu.mrd.hotword.util.SimpleTopNTool;
import com.sohu.mrd.hotword.util.WordFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yonghongli on 2016/8/3.
 */
public class GetHotWordRedisDate {

    private static JedisResourcePool jedisPool = null;
    private static Jedis jedis = null;
    private static final String clusterName = "mrd_redis_1";

    static {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMinEvictableIdleTimeMillis(60000);

        config.setTimeBetweenEvictionRunsMillis(30000);
        jedisPool = CodisRedisClient.getJedisPool(clusterName, config, 0, "test");


    }

    public static Map<String, Integer> getGetRedisDate(String time, int topN) {
        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        try {
            jedis = jedisPool.getResource();
            String key = CodisRedisClient.mkKey("hotword_" + time, "search-hot-word");
            Map<String, String> h = jedis.hgetAll(key);
            if (h == null) {
                return null;
            }
            SimpleTopNTool utl = new SimpleTopNTool(topN);
            for (Map.Entry<String, String> s : h.entrySet()) {
                utl.addElement(new SimpleTopNTool.SortElement(Integer.valueOf(s.getValue()), s.getKey()));
            }

            for (SimpleTopNTool.SortElement ele : utl.getTopN()) {
                result.put(ele.getVal().toString().replace(",","").replaceAll("[\\pP¡®¡¯¡°¡±| \t|¡¡]", ""), (int) ele.getCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return result;

    }

    public static void main(String[] args) throws ParseException {
        //  System.out.println(DateUtils.getDateTimeList("2016-12-0818", "2016-12-0819"));
        //  Map<String,String> h= GetHotWordRedisDate.getGetRedisDate("2016-08-03 15","2016-08-03 17",10);
       Map<String, Integer> h = GetHotWordRedisDate.getGetRedisDate("2017-03-1611", 10);
        System.out.println(h.keySet());

//        for (Map.Entry<String, Integer> kw : h.entrySet()) {
//            System.out.println(kw.getKey() + ":" + kw.getValue());
//        }
    }



}

