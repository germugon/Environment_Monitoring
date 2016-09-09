package redis;

import redis.clients.jedis.Jedis;
import util.RedisUtil;

public class RedisTest {

	public static void main(String[] args) {
		Jedis jedis = RedisUtil.getJedis();
		jedis.set("key1", "value1");
		System.out.println(jedis.get("key1")); 
	}
}
