package com.baosight.sggk.util;

import java.io.*;

public class FileUtil {
	/**
     * @param original  要拷贝的文件或文件夹
     * @param target    拷贝到目标
     */
    public static  void copy(File original, File target) {
        if (original.exists()) {
            if (original.isDirectory()) {//判断源文件是否是文件夹
                File dir = new File(target, original.getName());
                if (!dir.exists()) {
                    dir.mkdir();//在目标文件夹下创建一个和源文件夹目录结构一样的文件夹
                }
                File[] files = original.listFiles();//遍历源文件夹下的子集
                for (File file : files) {
                    copy(file, dir);//递归
                }
            } else {//是文件就写入到和源文件目录结构一样的文件中
                copyfile(original,new File(target,original.getName()));
            }
        }
    }

    public static  void copyfile(File original, File target) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(original);
            outputStream = new FileOutputStream(target,true);
            byte bytes[] = new byte[1024 * 20];
            int length = -1;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
