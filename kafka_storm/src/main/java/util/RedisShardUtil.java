package util;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Sharding机制，克服内存限制，但降低了数据存储和访问效率
 * @author ZW
 *
 */
public class RedisShardUtil {  

	public static ShardedJedisPool pool = null;  

	static {  
		createJedisPool();  
	}  

	/** 
	 * init jedis pool 
	 */  
	private static void createJedisPool(){  

		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();  
		String[] IPs = ConfigUtil.REDIS_IP.split(",");   

		for( int i = 0; i < IPs.length; i++ ) {  
			JedisShardInfo jedisShardInfo = new JedisShardInfo( ConfigUtil.REDIS_SCHEME + 
					IPs[i] + ":" + ConfigUtil.REDIS_PORT + "/" + ConfigUtil.REDIS_DB );
//			JedisShardInfo jedisShardInfo = new JedisShardInfo( IPs[i], ConfigUtils.REDIS_PORT );  
			shards.add( jedisShardInfo );  
		}  

		//连接池配置信息  
		JedisPoolConfig config = new JedisPoolConfig();  
		config.setMaxTotal(ConfigUtil.REDIS_MAXTOTAL);  
		config.setMaxIdle(ConfigUtil.REDIS_MAXIDLE);  
		config.setMinIdle(ConfigUtil.REDIS_MINIDLE); 
		config.setMaxWaitMillis(ConfigUtil.REDIS_MAXWAITMILLIS);  
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		config.setTestWhileIdle(true);

		//创建连接池  
		pool = new ShardedJedisPool(config, shards); 
	}  

	/** 
	 * get jedis pool 
	 * @return 
	 */  
	public static ShardedJedisPool getShardedPool(){  
		if(pool == null){  
			createJedisPool();  
		}  
		return pool;  
	}  

	/**
	 * get jedis instance
	 * @return
	 */
	public static ShardedJedis getShardedJedis(){  
		return getShardedPool().getResource();   
	}  

	/**
	 * return jedis instance
	 * @return
	 */
	public static void returnShardedJedis(ShardedJedis jedis){  
		getShardedPool().returnResource(jedis);
	}  
}  


