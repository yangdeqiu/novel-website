package cn.book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * redis集群配置类
 * @author tarena
 *
 */
@Configuration
@ConfigurationProperties(prefix = "redis.cluster")
public class JedisClusterConfig {

	private List<String> nodes;
	private Integer maxTotal;
	private Integer minIdle;
	private Integer maxIdle;
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
	public Integer getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}
	public Integer getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}
	public Integer getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}
	
	@Bean
	public JedisCluster initJedisCluster(){
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		for(String node : nodes){
			set.add(new HostAndPort(node.split(":")[0],Integer.parseInt(node.split(":")[1])));
		}
		GenericObjectPoolConfig config =
				new GenericObjectPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		return new JedisCluster(set,config);
	}
}
