package com.Eddy.Zk_01;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
* 创建时间：2017年6月23日 下午8:16:37
* 项目名称：Zk_01
* @author Eddy Cui
* @since JDK 1.7.0_21
* 文件名称：Hello_zk1.java
* 类说明：
*/
public class Hello_zk1 extends Base_zk{
	
	private static final Logger logger = Logger.getLogger(Hello_zk1.class);
	
	private final static String PATH ="/Eddy";
	
	private ZooKeeper zoo =null;
	
	

	public ZooKeeper getZoo() {
		return zoo;
	}

	public void setZoo(ZooKeeper zoo) {
		this.zoo = zoo;
	}

	@Override
	public ZooKeeper getZk() throws IOException {
		
		zoo = new ZooKeeper(CONNECTSTRING, SESSIONTIMEOUT, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return zoo;
	}

	public String createZk(String data,CreateMode createMode) throws IOException, KeeperException, InterruptedException {

		String create = zoo.create(PATH, data.getBytes(),Ids.OPEN_ACL_UNSAFE, createMode);
		
		return create;
		
	}
	
	public Stat setZk(String data,Integer version) throws IOException, KeeperException, InterruptedException{
		
		
		Stat setData = zoo.setData(PATH, data.getBytes(), version);
		
		
		return setData;
	}
	
	public String getZk(Stat setData) throws IOException, KeeperException, InterruptedException{
		
		byte[] data = zoo.getData(PATH,false,setData);
		
		String zk = new String(data);
		
		return zk;
	}
	
	public void deleteZk(Integer version) throws IOException, KeeperException, InterruptedException{
		
		zoo.delete(PATH, version);
		
	}
	
	public void stopZk() throws IOException, KeeperException, InterruptedException{
		
		if(zoo!=null){
		
			zoo.close();
			
		}
		
	}
	
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		Hello_zk1 a = new Hello_zk1();
		
		ZooKeeper zk = a.getZk();
		
		a.setZoo(zk);
		
		Stat exists = zk.exists(PATH, false);
		
		if(exists == null){
			
			String createZk = a.createZk("test", CreateMode.PERSISTENT);
			
			logger.info("create new path**********"+createZk);
			
			
		}else{
			
			logger.info("already haven it");
			
		}
		
		a.stopZk();
		
	}

}
