package com.zjc.hustoj.core.utils;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author david
 * @date: 2021/03/23/3:34 下午
 */
public class BeanUtils {

    public static <T> T copyProperties(Object orig, T dest){
        if (dest == null || orig == null) return dest;

        if (orig instanceof Map) {
            Map map = ((Map)orig);
            for(Object o:map.keySet()){
                setProperties(dest, (String)o, map.get(o));
            }
        } else {
            Class<?> clazz = orig.getClass();
            while(clazz.getName().equals(Object.class.getName())==false){
                for(Field origField:clazz.getDeclaredFields()){
                    Object value = null;
                    try {
                        origField.setAccessible(true);
                        value = origField.get(orig);
                    } catch (IllegalArgumentException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                    setProperties(dest, origField.getName(), value);
                }
                clazz = clazz.getSuperclass();
            }
        }
        return dest;
    }

    public static void setProperties(Object bean, String properiesName, Object value){
        if(value==null)return;
        Class<?> clazz = bean.getClass();
        while(clazz.getName().equals(Object.class.getName())==false){
            for(Field field:clazz.getDeclaredFields()){
                if(field.getName().equals(properiesName)){
                    try {
                        if(field.getType().isInstance(value)){
                            field.setAccessible(true);
                            field.set(bean, value);
                        }else if(field.getType().isPrimitive() && field.getType().hashCode()==value.getClass().hashCode()){
                            field.setAccessible(true);
                            field.set(bean, value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    public static <T> T copyProperties(Object orig, Class<T> clazz){
        if(orig==null)return null;
        T dest = null;
        try {
            dest = clazz.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
        return copyProperties(orig, dest);
    }

    public static  <T> List<T> convertList(List origList, Class<T> clazz){
        List<T> list = Lists.newArrayList();
        for (Object orig : origList){
            T dest = copyProperties(orig, clazz);
            list.add(dest);
        }
        return list;
    }
}