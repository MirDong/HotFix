package com.dzk.hotfix.utils;

import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author jackie
 * @date 2020/12/5
 */
public class ShareReflectUtils {
    public static Field findField(Object instance,String filedName) throws NoSuchFieldException {
        Objects.requireNonNull(instance,filedName + "reflect require instance not null");
        for (Class<?> clazz = instance.getClass();clazz != null;clazz = clazz.getSuperclass()){
            try {
                Field field = clazz.getDeclaredField(filedName);
                if (!field.isAccessible()){
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                //ignore this and search next
                //没有找到就接着从父类里面找
            }

        }
        throw new NoSuchFieldException("name " + filedName + " no found in " + instance.getClass());
    }

    public static Method findMethod(Object instance,String methodName,Class<?>... parameterTypes) throws NoSuchMethodException {
        Objects.requireNonNull(instance,methodName + "reflect require instance not null");
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()){
            try {
                Method method = clazz.getDeclaredMethod(methodName,parameterTypes);
                if (!method.isAccessible()){
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                //ignore and search next
            }
        }
        throw new NoSuchMethodException(methodName + " with parameter "
                + Arrays.asList(parameterTypes) + " not found in "
                + instance.getClass());
    }
} 
