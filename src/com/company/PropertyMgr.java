package com.company;

import java.io.IOException;
import java.util.Properties;

/*第一次使用PropertyMgr的时候，把它load到内存的时候，它会自动去硬盘上找到config文件，
并把config文件的内容放到props里面*/
public class PropertyMgr {
    //Properties里存的是很多的key value这样的键值对，它提供了方法可以直接被访问
    static Properties props = new Properties();
    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(props == null) return null;
        return props.get(key);
    }

}
