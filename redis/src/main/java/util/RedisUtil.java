package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	private static JedisPool pool = null;  //redis连接池

	static {  
		createJedisPool();  
	}  

	/** 
	 * init jedis pool 
	 */  
	private static void createJedisPool(){
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
		pool = new JedisPool( config, ConfigUtil.REDIS_IP, ConfigUtil.REDIS_PORT, ConfigUtil.REDIS_SSL ); 		
	}


	/** 
	 * get jedis pool 
	 * @return 
	 */  
	public static JedisPool getPool(){  
		if(pool == null){  
			createJedisPool();  
		}  
		return pool;  
	}  

	/**
	 * get jedis instance
	 * @return
	 */
	public static Jedis getJedis(){  
		return getPool().getResource();   
	}  

	/**
	 * return jedis instance
	 * @return
	 */
	public static void returnJedis(Jedis jedis){  
		getPool().returnResource(jedis);
	}  

}
