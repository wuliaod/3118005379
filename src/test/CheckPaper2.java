package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

public class CheckPaper2
{
public static void main(String[] args) throws IOException
{
	float k=0;
	File file1=new File(args[0]);
	File file2=new File(args[1]);
	String[] s1 = getdata(file1);                     //原版
	String[] s2 = getdata(file2);                      //盗版
	for(int i=0;i<s2.length;i++){
		for(int j=0;j<s1.length;j++){
			int distance = minEditDistance(s1[j], s2[i]);
			if(distance<5){
				k=k+1;
				break;
				//System.out.println(distance);
			}
		}
	}
	OutputStream out =new FileOutputStream(args[2]);
	Float d=k/s1.length;
	byte[] bytes = d.toString().getBytes();
	out.write(bytes);
	out.flush();
	
	System.out.println("重复率："+k/s1.length);
	
}

//将文章按标点符号分句
public static String[] getdata(File f) throws IOException{
	InputStream in=new FileInputStream(f);
	byte[] b=new byte[in.available()];
	in.read(b);
	String s =new String(b,"UTF-8");
	String s1 = Html2Text(s);
	String temp1=s1.toString().trim().replaceAll("\\s*", "");// 临时变量, 存储s没有去除标点符号的内容(只是去除空格)
	String[] after = temp1.split("[?!。，]");
	return after;
}



public static int minEditDistance(String word1, String word2) {
    /**
     * 判空,word1为空,取Word2的长度,反之,亦然
     */
    if (word1.length() == 0 || word2.length() == 0) {
        return word1.length() == 0 ? word2.length() : word1.length();
    }
    //初始化矩阵
    int[][] arr = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i <= word1.length(); i++) {
        arr[i][0] = i;
    }
    for (int j = 0; j <= word2.length(); j++) {
        arr[0][j] = j;
    }
    /**
     * 填充矩阵
     */
    for (int i = 1; i <= word1.length(); i++) {
        for (int j = 1; j <= word2.length(); j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                //相等时temp为0
                arr[i][j] = arr[i - 1][j - 1];
            } else {
                //不相等时,temp为1
                int replace = arr[i - 1][j - 1] + 1;
                int insert = arr[i - 1][j] + 1;
                int delete = arr[i][j - 1] + 1;
                int min = Math.min(replace, insert);
                min = Math.min(min, delete);
                arr[i][j] = min;
            }
        }
    }
//    for (int i = 0; i < arr.length; i++) {
//        for (int j = 0; j < arr[i].length; j++) {
//            System.out.print(arr[i][j] + " ");
//        }
//        System.out.println();
//    }
    return arr[word1.length()][word2.length()];
}

//去除HTML标签
public static String Html2Text(String inputString){
	 
    String htmlStr = inputString; //含html标签的字符串
    String textStr ="";
    java.util.regex.Pattern p_script;
    java.util.regex.Matcher m_script;
    java.util.regex.Pattern p_style;
    java.util.regex.Matcher m_style;
    java.util.regex.Pattern p_special;
    java.util.regex.Matcher m_special;
    java.util.regex.Pattern p_html;
    java.util.regex.Matcher m_html;

    try{
        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
        String regEx_special = "\\&[a-zA-Z]{1,10};";// 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp

        p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        p_special = Pattern.compile(regEx_special,Pattern.CASE_INSENSITIVE);
        m_special = p_special.matcher(htmlStr);
        htmlStr = m_special.replaceAll(""); //过滤style标签


        p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签
        htmlStr = htmlStr.replace("  ", "");
        htmlStr = htmlStr.replace("\n", "");
        htmlStr = htmlStr.replace("\t", "");
        textStr = htmlStr.trim();
        textStr = htmlStr;
    }catch(Exception e){

    }
    return textStr;//返回文本字符串
}



}
