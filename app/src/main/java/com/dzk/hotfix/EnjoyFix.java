package com.dzk.hotfix;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.dzk.hotfix.sysversion.V19;
import com.dzk.hotfix.sysversion.V23;
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
 * @date 2020/12/2
 */
public class EnjoyFix {

    public static void installDex(Context context,File file){
        ClassLoader pathClassLoader = context.getClassLoader();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            V23.install(context,pathClassLoader,file);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            V19.install(context,pathClassLoader,file);
        }
    }
} 
