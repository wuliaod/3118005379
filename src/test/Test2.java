package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Test2
{
public static void main(String[] args) throws IOException
{
	File file1=new File("D:\\桌面\\test(1)\\test\\orig.txt");
	File file2=new File("D:\\桌面\\test(1)\\test\\orig_0.8_del.txt");
	String[] getdata = getdata(file2);
	
	for (int i = 0; i < getdata.length; i++)
	{
		System.out.println(getdata[i]);
	}
	
}
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
