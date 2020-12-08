package com.definesys.rt.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//map 遍历
public class EntrySetUtils {

    public static void showMapAndValue(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();

        while(it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " = " + value);

        }
    }
}
