package com.dzk.hotfix.utils;

import android.util.Log;

/**
 * @author jackie
 * @date 2020/12/2
 */
public class Utils {
    public static void init(){
        throw new RuntimeException("发生运行时异常");
    }

   /* public static void init(){
        Log.d("jackie","bug已经被修复了");
    }*/
} 
