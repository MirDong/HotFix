package com.dzk.hotfix;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dalvik.system.BaseDexClassLoader;

/**
 * @author jackie
 * @date 2020/12/2
 */
public class EnjoyFix {

    public static void hotFix(Context context){
        ClassLoader pathClassLoader = context.getClassLoader();
//        Class<BaseDexClassLoader> baseDexClassLoaderClass = BaseDexClassLoader.class;

        try {
            Class<?> baseDexClassLoaderClass = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = baseDexClassLoaderClass.getDeclaredField("pathList");
            pathListField.setAccessible(true);
            Object dexPathList = pathListField.get(pathClassLoader);

            //DexPathList
//            Class dexPathClass = dexPathList.getClass();
            Class<?> dexPathClass = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathClass.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);

            Log.d("EnjoyFix", "dexPathList = " + dexPathList);
            Object[] oldElements = (Object[]) dexElementsField.get(dexPathList);
            Log.d("EnjoyFix", "elements.length= " + oldElements.length);
            Method makeDexElementsMethod = dexPathClass.getDeclaredMethod("makeDexElements", List.class,File.class,List.class,ClassLoader.class);
            makeDexElementsMethod.setAccessible(true);
            File pathchFile = new File("/sdcard/patch.dex");
            List<File> fileList = new ArrayList<>();
            fileList.add(pathchFile);
            File odexFile = new File(context.getCacheDir().getAbsolutePath());
            final List<IOException> suppressedExceptionList = new ArrayList<IOException>();
            Object[] fixElements = (Object[]) makeDexElementsMethod.invoke(null, fileList, odexFile, suppressedExceptionList, context.getClassLoader());
            Log.d("EnjoyFix", "fixElements.length= " + fixElements.length);
            Object[] newElement = (Object[]) Array.newInstance(oldElements.getClass().getComponentType(),
                    oldElements.length+fixElements.length);
            System.arraycopy(fixElements,0,newElement,0,fixElements.length);
            System.arraycopy(oldElements,0,newElement,fixElements.length,oldElements.length);
            dexElementsField.set(dexPathList,newElement);
            Log.d("EnjoyFix", "newElement.length= " + newElement.length);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
} 
