package com.Eddy.Zk_01;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;

/**
* 创建时间：2017年6月23日 下午8:19:03
* 项目名称：Zk_01
* @author Eddy Cui
* @since JDK 1.7.0_21
* 文件名称：Base_zk.java
* 类说明：
*/
public abstract class Base_zk {

	final static String CONNECTSTRING = "192.168.140.128:2192,192.168.140.128:2193,192.168.140.128:2191";
	
	final static int SESSIONTIMEOUT = 50*1000;
	
	public abstract  ZooKeeper getZk() throws IOException;
	
	
}
