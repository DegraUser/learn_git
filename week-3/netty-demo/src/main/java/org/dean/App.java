package org.dean;

import io.netty.bootstrap.Bootstrap;
import org.dean.Util;
import org.dean.NettyHttpServer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        System.out.println( "Hello World!" );
	Util u = new Util();
	u.Print();

        Map<String, Class> map = new HashMap<>();
        map.put("8", NettyHttpServer.class);

        String id = (null == args || args.length == 0) ? "1" : args[0];
        System.out.println( "id: " + id );
        Class clazz = map.get(id);
        if( null == clazz ) {
            System.out.println("No class for id: " + id);
        }

        try {
            Method method = clazz.getDeclaredMethod("main", new Class[]{String[].class});
            method.invoke(null, new Object[]{new String[]{}});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
