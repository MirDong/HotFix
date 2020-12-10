package com.dzk.hotfix;

import android.support.annotation.IntDef;
import android.support.annotation.IntRange;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jackie
 * @date 2020/12/5
 */
public class Person {
    public static final int LANCE = 1;
    public static final int ALVIN = 2;
    int teacher;
    public void setPerson(@Teacher int teacher) {
        this.teacher = teacher;
    }

    void test(){
        setPerson(33);
    }
    @IntRange
    @IntDef(value = {LANCE,ALVIN})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Teacher {
    }

}
