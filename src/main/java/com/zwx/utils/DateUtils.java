package com.zwx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author coderZWX
 * @date 2020-11-06 21:14
 */
public class DateUtils {

    /**
     * 将date类型的数据转换成相应格式的字符串
     * @param date 需要被转换的date类型数据
     * @return 返回转换好的日期字符串
     */
    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    /**
     * 将字符串类型的数据转换为date类型的日期数据
     * @param string 需要转换的字符串
     * @param format 转换的格式
     * @return 返回转换好的date数据
     */
    public static Date stringToDate(String string,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(string);
    }

}
