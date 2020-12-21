package com.zwx.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 
 *  敏感词过滤 工具类   -- 【匹配度高，可以使用】
 *  《高效精准》敏感字&词过滤：http://blog.csdn.net/hubiao_0618/article/details/45076871
 * @author hubiao 
 * @version 0.1 
 * @CreateDate 2015年4月16日 15:28:32 
 */
@Configuration
public class SensitiveWord {

    private StringBuilder replaceAll;//初始化
    private String encoding = "UTF-8";  
    private String replceStr = "*";  
    private int replceSize = 500;  
    private String fileName = "CensorWords.txt";  
    private static List<String> arrayList;
    private static Set<String> sensitiveWordSet;//包含的敏感词列表，过滤掉重复项
    private static List<String> sensitiveWordList;//包含的敏感词列表，包括重复项，统计次数
    
    /** 
     * 文件要求路径在src或resource下，默认文件名为CensorWords.txt 
     * @param fileName 词库文件名(含后缀) 
     */  
    public SensitiveWord(String fileName)  
    {  
        this.fileName = fileName;  
    }  
      
    /** 
     * @param replceStr 敏感词被转换的字符 
     * @param replceSize 初始转义容量 
     */  
    public SensitiveWord(String replceStr,int replceSize)  
    {  
        this.replceStr = fileName;  
        this.replceSize = replceSize;  
    }  
      
    public SensitiveWord()  
    {  
    }  
    
    /** 
     * @param str 将要被过滤信息 
     * @return 过滤后的信息 
     */  
    public String filterInfo(String str)  
    {  	sensitiveWordSet = new HashSet<String>();
    	sensitiveWordList= new ArrayList<>();
        StringBuilder buffer = new StringBuilder(str);
        //将存放敏感词起止下标的map初始化大小为（敏感词个数+1）*0.75
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());  
        //用于指向一个敏感词
        String temp;
        //从敏感词集合中挨个拿到敏感词，查找传入的字符串中是否含有
        for (String s : arrayList) {
            //获取当前敏感词
            temp = s;
            //初始化开始检索的位置——即从头开始查找是否有敏感词
            int findIndexSize = 0;
            for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1; ) {
                //System.out.println("###replace="+temp);
                //进入循环说明找到了敏感词
                findIndexSize = start + temp.length();//将下一次检索的起点设置为已找到的敏感词的位置
                Integer mapStart = hash.get(start);//在map中查看是否在当前位置已经有敏感词了
                //如果没找到，或者找到了涵盖范围没这次广的就记录这个敏感词（比如"你妈死了"和"你妈死"都是敏感词，先找到了"你妈死",后找到了"你妈死了",只记录"你妈死了"）
                if (mapStart == null || findIndexSize > mapStart) {
                    //将敏感词的起始下标和结束下标记录进map
                    hash.put(start, findIndexSize);
                    //System.out.println("###敏感词："+buffer.substring(start, findIndexSize));
                }
            }
        }
        //将所有的敏感词起点坐标放进集合遍历，找到敏感词记录下来，然后将它替换为设置好的替换符号默认为*
        Collection<Integer> values = hash.keySet();  
        for(Integer startIndex : values)  
        {
            //根据起点拿到终点坐标
            Integer endIndex = hash.get(startIndex);  
            //获取敏感词，并加入列表，用来统计数量
            String sensitive = buffer.substring(startIndex, endIndex);
            //System.out.println("###敏感词："+sensitive);
            if (!sensitive.contains("*")) {//添加敏感词到集合
                //去重版
            	sensitiveWordSet.add(sensitive);
            	//不去重版
            	sensitiveWordList.add(sensitive);
			}
            //将敏感词替换为替换符号——默认*
            buffer.replace(startIndex, endIndex, replaceAll.substring(0,endIndex-startIndex));
        }
        //清空敏感词索引集合
        hash.clear();
        //返回过滤了敏感词的字符串
        return buffer.toString();  
    }  
    /**
     *   初始化敏感词库
     */  
    public void InitializationWork() {
        replaceAll = new StringBuilder(replceSize);  
        for(int x=0;x < replceSize;x++)  
        {  
            replaceAll.append(replceStr);  
        }
        //加载词库
        arrayList = new ArrayList<String>();  
        InputStreamReader read = null;  
        BufferedReader bufferedReader = null;  
        try {  
            read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(fileName),encoding);  
            bufferedReader = new BufferedReader(read);  
            for(String txt = null;(txt = bufferedReader.readLine()) != null;){  
                if(!arrayList.contains(txt))  
                    arrayList.add(txt);  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                if(null != bufferedReader)  
                bufferedReader.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                if(null != read)  
                read.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }

    public static Set<String> getSensitiveWordSet() {
        return sensitiveWordSet;
    }

    public static void setSensitiveWordSet(Set<String> sensitiveWordSet) {
        SensitiveWord.sensitiveWordSet = sensitiveWordSet;
    }

    public static List<String> getSensitiveWordList() {
        return sensitiveWordList;
    }

    public static void setSensitiveWordList(List<String> sensitiveWordList) {
        SensitiveWord.sensitiveWordList = sensitiveWordList;
    }

    public StringBuilder getReplaceAll() {
        return replaceAll;  
    }  
    public void setReplaceAll(StringBuilder replaceAll) {  
        this.replaceAll = replaceAll;  
    }  
    public String getReplceStr() {  
        return replceStr;  
    }  
    public void setReplceStr(String replceStr) {  
        this.replceStr = replceStr;  
    }  
    public int getReplceSize() {  
        return replceSize;  
    }  
    public void setReplceSize(int replceSize) {  
        this.replceSize = replceSize;  
    }  
    public String getFileName() {  
        return fileName;  
    }  
    public void setFileName(String fileName) {  
        this.fileName = fileName;  
    }  
    public List<String> getArrayList() {  
        return arrayList;  
    }  
    public void setArrayList(List<String> arrayList) {  
        this.arrayList = arrayList;  
    }  
    public String getEncoding() {  
        return encoding;  
    }  
    public void setEncoding(String encoding) {  
        this.encoding = encoding;  
    }

    @Bean(name = "sensitive")
    public SensitiveWord bean(){
        SensitiveWord sw = new SensitiveWord("CensorWords.txt");
        sw.InitializationWork();
        return sw;
    }

    //测试用的主方法
    /*public static void main(String[] args){
    	long startNumer = System.currentTimeMillis();
		SensitiveWord sw = new SensitiveWord("CensorWords.txt");  
	    sw.InitializationWork();
	    System.out.println("敏感词的数量：" + arrayList.size());
	    String str = "太多的伤yuming感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
	            + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
	            + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"  
	            + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20哀2015/4/16 20152015/4/16乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"  
	            + "关, 人, 流, 电, 发, 情, 太, 限, 法轮功, 个人, 经, 色, 许, 公, 动, 地, 方, 基, 在, 上, 红, 强, 自杀指南, 制, 卡, 三级片, 一, 夜, 多, 手机, 于, 自，"  
	            + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
	    System.out.println("被检测字符串长度:"+str.length());  
	    str = sw.filterInfo(str);  
	    long endNumber = System.currentTimeMillis();  
	    System.out.println("语句中包含敏感词的个数为：" + sensitiveWordSet.size() + "。包含：" + sensitiveWordSet);
	    System.out.println("语句中包含敏感词的个数为：" + sensitiveWordList.size() + "。包含：" + sensitiveWordList);
	    System.out.println("总共耗时:"+(endNumber-startNumer)+"ms");  
	    System.out.println("替换后的字符串为:\n"+str);  
	}*/

    /*public static void main(String[] args) {
        SensitiveWord sw = (SensitiveWord) BeanUtils.getBean("sensitive");
        String str = "太多的伤yuming感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20哀2015/4/16 20152015/4/16乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "关, 人, 流, 电, 发, 情, 太, 限, 法轮功, 个人, 经, 色, 许, 公, 动, 地, 方, 基, 在, 上, 红, 强, 自杀指南, 制, 卡, 三级片, 一, 夜, 多, 手机, 于, 自，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("被检测字符串长度:"+str.length());
        str = sw.filterInfo(str);
        System.out.println("替换后的字符串为:\n"+str);
    }*/

}  

