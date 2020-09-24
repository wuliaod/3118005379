package test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Test1
{

@SuppressWarnings({ "unused", "null" })
public static void main(String[] args) throws IOException
{
	float j=0;//用于存储相同的字
	File file1=new File("D:\\桌面\\test(1)\\test\\orig.txt");
	ArrayList<String> list1 = subsection(file1);//原版
	File file2=new File("D:\\桌面\\test(1)\\test\\orig_0.8_add.txt");
	ArrayList<String> list2 = subsection(file2); //需论文查重的
	Iterator<String> it1 = list1.iterator();
	Iterator<String> it2 = list2.iterator();

	String next2=null;
	String next1=null;
//	for(int i=0;i<list1.size();i++){
//		
//		if(it1.hasNext()&&it2.hasNext()){
//		 next2=it2.next();
//		 next1 = it1.next();
//		}
//		int a=0;
//		for(int t=0;t<150;t++){
//			String s=next2.substring(a,a+2);
//			if(next1.contains(s)){
//				j=j+2;
//			}
//			a=a+2;
//		}
//		
//	}
	if(it1.hasNext()&&it2.hasNext()){
		 next2=it2.next();
		 next1 = it1.next();
		}
	int a=0;
	for(int t=0;t<1680;t++){
		String s=next2.substring(a,a+5);
		if(next1.contains(s)){
			j=j+5;
		}
		a=a+5;
	}
	
	
	
	float size = list1.size();
	float t=j/8400;
	System.out.println(String.format("%.2f", t));
	System.out.println(j);
	
	
}


public static ArrayList<String> subsection(File f) throws IOException{
	ArrayList<String> list=new ArrayList<String>();            //段落切分
	
	InputStream in=new FileInputStream(f);
	byte[] b=new byte[in.available()];
	in.read(b);
	String s =new String(b,"UTF-8");
	String temp1=s.toString().trim().replaceAll("\\s*", "");// 临时变量, 存储s没有去除标点符号的内容(只是去除空格)
	String temp2=temp1.replaceAll("[\\pP\\p{Punct}]", "");// 临时变量, 存储s去除空格的内容并且取出标点符号的内容
//	int j=temp2.length()/300;
//	int c=0;                                               //c用于循环的
//	for(int i=0;i<j;i++){
//		String a=temp2.substring(c, c+300);                  //每段300个字
//		list.add(a);
//		c=c+300;
//	}
//	String d=temp2.substring(c,temp2.length()-1);//d是存储末尾不足三百字的内容
//	list.add(d);
//	Iterator<String> iterator = list.iterator();
//	while(iterator.hasNext()){
//		String next=iterator.next();
//		System.out.println(next);
//	}
	list.add(temp2);
	return list;
}
}
