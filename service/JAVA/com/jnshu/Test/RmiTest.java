package com.jnshu.Test;

import com.jnshu.mapper.IHello;
import com.jnshu.service.impl.HelloImpl;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiTest {
    public static void main(String args[]){
        try {
            //创建一个远程对象
            IHello rhello = new HelloImpl();
            //生成远程对象注册表Registry的实例，并指定端口为8888（默认是1099）
            LocateRegistry.createRegistry(8888);

            //把远程对象注册到RMI注册服务器上 并命名为Rhello
            //绑定URL的标准为: rmi://host:port/name(协议可以省略，下面两种写法都可以）
            Naming.bind("rmi://127.0.0.1:8888/Rhello",rhello);

            System.out.println(">>INFO:远程Ihello对象绑定成功");
        }catch (RemoteException e){
            System.out.println("创建远程对象发生异常");
            e.printStackTrace();
        }catch (AlreadyBoundException e){
            System.out.println("发生重复绑定异常");
            e.printStackTrace();
        }catch (MalformedURLException e){
            System.out.println("发生URL异常");
            e.printStackTrace();
        }
    }
}
