package cn.book.search.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="es")
public class TransportConfig {
	private List<String> nodes;
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
	//定义一个初始化transportclient的方法
	@Bean
	public TransportClient initTclient(){
		TransportClient client=new 
		PreBuiltTransportClient(Settings.EMPTY);
		for(String node :nodes){
			try {
				String host = node.split(":")[0];
				int port = Integer.parseInt(node.split(":")[1]);
				InetAddress address = InetAddress.getByName(host);
				client.addTransportAddress(new InetSocketTransportAddress(address,port));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return client;
}
}