package com.dzk.hotfix.sysversion;

import android.content.Context;
import android.util.Log;

import com.dzk.hotfix.utils.ShareReflectUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jackie
 * @date 2020/12/5
 */
public class V19 {
    private static final String TAG = "V19";
    public static void install(Context context, ClassLoader classLoader, File file){
//        File pathchFile = new File("/sdcard/patch.dex");
        if (context == null || classLoader == null || file == null){
            Log.e(TAG, context == null ? "context is null": classLoader == null ? "classloader is null" : "file is null");
            return;
        }

        Field pathListField = null;
        try {
            pathListField = ShareReflectUtils.findField(classLoader,"pathList");
            Object dexPathList = pathListField.get(classLoader);
            //DexPathList
            Field dexElementsField = ShareReflectUtils.findField(dexPathList,"dexElements");
            Log.d("EnjoyFix", "dexPathList = " + dexPathList);
            Object[] oldElements = (Object[]) dexElementsField.get(dexPathList);
            Log.d("EnjoyFix", "elements.length= " + oldElements.length);
            Method makeDexElementsMethod = ShareReflectUtils.findMethod(dexPathList,"makeDexElements",
                    List.class,File.class,List.class);
            List<File> fileList = new ArrayList<>();
            fileList.add(file);
            final List<IOException> suppressedExceptionList = new ArrayList<IOException>();
            Object[] fixElements = (Object[]) makeDexElementsMethod.invoke(null, fileList, context.getCacheDir(), suppressedExceptionList, context.getClassLoader());
            Log.d("EnjoyFix", "fixElements.length= " + fixElements.length);
            Object[] newElements = (Object[]) Array.newInstance(oldElements.getClass().getComponentType(),
                    oldElements.length+fixElements.length);
            System.arraycopy(fixElements,0,newElements,0,fixElements.length);
            System.arraycopy(oldElements,0,newElements,fixElements.length,oldElements.length);
            dexElementsField.set(dexPathList,newElements);
            Log.d("EnjoyFix", "newElements.length= " + newElements.length);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
} 
