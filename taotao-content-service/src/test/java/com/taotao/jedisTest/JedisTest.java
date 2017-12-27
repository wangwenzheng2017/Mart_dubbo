package com.taotao.jedisTest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.content.service.JedisClient;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	//连接单机版
	@Test
	public void jedisTest() throws Exception{
		//Jedis jedis = new Jedis("192.168.8.130",6379);
		
		//jedisPool
		JedisPool jedisPool = new JedisPool("192.168.8.130",6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("test", "qqqqq");
		String str = jedis.get("test");
		
		//String str = jedis.get("hello");
		System.out.println(str);
		jedis.close();
		jedisPool.close();
	}
	//连接集群版
	@Test
	public void jedisClusterTest() throws Exception{
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.8.130", 7001));
		nodes.add(new HostAndPort("192.168.8.130", 7002));
		nodes.add(new HostAndPort("192.168.8.130", 7003));
		nodes.add(new HostAndPort("192.168.8.130", 7004));
		nodes.add(new HostAndPort("192.168.8.130", 7005));
		nodes.add(new HostAndPort("192.168.8.130", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("hello", "100");
		String str = jedisCluster.get("hello");
		System.out.println(str);
		jedisCluster.close();
		
	}
	//整合spring
	@Test
	public void jedisClusterBySpringTest() throws Exception{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("qqq", "wwww");
		String str = jedisClient.get("qqq");
		System.out.println(str);
		
	
	}
	
	
}
