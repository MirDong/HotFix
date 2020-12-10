package com.dzk.patch.hack;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jackie
 * @date 2020/12/7
 */
public class Test {
    public static void main(String[] args) {
        ByteArrayOutputStream baos = null;
        InputStream inputStream = null;
        try {
            Process process = Runtime.getRuntime().exec("adb --version");
            process.waitFor();
            inputStream = process.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len;
            while((len = inputStream.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!= null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("begin print");
            System.out.println(new String(baos.toByteArray()));
            if (baos!= null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
} 
