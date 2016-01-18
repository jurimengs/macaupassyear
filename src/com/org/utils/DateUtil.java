/**
 * Copyright : http://www.sandpay.com.cn , 2007-2014
 * Project : lms
 * $Id$
 * $Revision$
 * Last Changed by SJ at 2014��3��4�� ����4:07:24
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * SJ         2014��3��4��        Initailized
 */
package com.org.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ʱ�乤����
 * 
 */
public class DateUtil
{
    /**
     * yyyyMM
     */
    public static final String yyyyMM     = "yyyyMM";
    /**
     * yyyyMMdd
     */
    public static final String yyyyMMdd = "yyyyMMdd";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
     * ���ϵͳ��ǰʱ��
     * 
     * @return
     */
    public static String getCurrentDateString()
    {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_DATE);// �������ڸ�ʽ
        return df.format(new Date());
    }

//    public static String dateFormat(String date)
//    {
//        return date.replaceAll("-", "");
//    }
    
    public static String getDateStringByFormat(String format){
        SimpleDateFormat df = new SimpleDateFormat(format);// �������ڸ�ʽ
        return df.format(new Date());
    }

    /**
     * 
     * @return yyyyMMdd
     */
    public static String getCurrentShortDateStr()
    {
        SimpleDateFormat df = new SimpleDateFormat(yyyyMMdd);// �������ڸ�ʽ
        return df.format(new Date());
    }

    /**
     * ��ǰ����ǰ��һ����
     * Ĭ�ϸ�ʽ: yyyyMMdd
     * @return
     */
    public static String getBeforeMonth()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);// ��ʽ������
        Calendar calendar = Calendar.getInstance();// ��������
        calendar.setTime(date);// ���õ�ǰ����
        calendar.add(Calendar.MONTH, -1);// �·ݼ�һ
        return sdf.format(calendar.getTime());
    }

    /**
     * ��ǰʱ��ǰ��n����
     * 
     * @param s
     * @return
     */
    public static String getBeforeMonth(int s, String format)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);// ��ʽ������
        Calendar calendar = Calendar.getInstance();// ��������
        calendar.setTime(date);// ���õ�ǰ����
        calendar.add(Calendar.MONTH, -s);// �·ݼ�һ
        return sdf.format(calendar.getTime());
    }

    /**
     * ��ñ������һ��
     * 
     * @return
     */
    public static String getLastDateByMonth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
        return format.format(calendar.getTime());
    }

    /**
     * ���ָ���·ݵ�һ��
     * 
     * @return
     */
    public static String getFirstDateByMonth(String date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strFormatToDate(date, yyyyMM));
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
        return format.format(calendar.getTime());
    }

    /**
     * �ַ���תDate
     * 
     * @param date
     * @return
     */
    public static Date strFormatToDate(String date, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try
        {
            return sdf.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getBeforeWeek()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);// ��ʽ������
        Calendar calendar = Calendar.getInstance();// ��������
        calendar.setTime(date);// ���õ�ǰ����
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        return sdf.format(calendar.getTime());
    }

    public static List<String> getBeforeWeekList()
    {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 7; i++)
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);// ��ʽ������
            Calendar calendar = Calendar.getInstance();// ��������
            calendar.setTime(date);// ���õ�ǰ����
            calendar.add(Calendar.DATE, -i);
            list.add(sdf.format(calendar.getTime()));
        }
        return list;
    }

    public static List<String> getBeforeMonthList()
    {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 6; i++)
        {
            list.add(getBeforeMonth(-i, yyyyMM));
        }
        return list;
    }

    public static void main(String[] args)
    {
        List<String> list = getBeforeMonthList();
        for (int i = 0; i < list.size(); i++)
        {

            System.out.println(list.get(i));
        }
        System.out.println(DateUtil.getFirstDateByMonth(DateUtil.getBeforeMonth(-6, yyyyMM)));
    }
}
