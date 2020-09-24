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
	String[] s1 = getdata(file1);                     //ԭ��
	String[] s2 = getdata(file2);                      //����
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
	
	System.out.println("�ظ��ʣ�"+k/s1.length);
	
}

//�����°������ŷ־�
public static String[] getdata(File f) throws IOException{
	InputStream in=new FileInputStream(f);
	byte[] b=new byte[in.available()];
	in.read(b);
	String s =new String(b,"UTF-8");
	String s1 = Html2Text(s);
	String temp1=s1.toString().trim().replaceAll("\\s*", "");// ��ʱ����, �洢sû��ȥ�������ŵ�����(ֻ��ȥ���ո�)
	String[] after = temp1.split("[?!����]");
	return after;
}



public static int minEditDistance(String word1, String word2) {
    /**
     * �п�,word1Ϊ��,ȡWord2�ĳ���,��֮,��Ȼ
     */
    if (word1.length() == 0 || word2.length() == 0) {
        return word1.length() == 0 ? word2.length() : word1.length();
    }
    //��ʼ������
    int[][] arr = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i <= word1.length(); i++) {
        arr[i][0] = i;
    }
    for (int j = 0; j <= word2.length(); j++) {
        arr[0][j] = j;
    }
    /**
     * ������
     */
    for (int i = 1; i <= word1.length(); i++) {
        for (int j = 1; j <= word2.length(); j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                //���ʱtempΪ0
                arr[i][j] = arr[i - 1][j - 1];
            } else {
                //�����ʱ,tempΪ1
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

//ȥ��HTML��ǩ
public static String Html2Text(String inputString){
	 
    String htmlStr = inputString; //��html��ǩ���ַ���
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
        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script> }
        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style> }
        String regEx_html = "<[^>]+>"; //����HTML��ǩ��������ʽ
        String regEx_special = "\\&[a-zA-Z]{1,10};";// ����һЩ�����ַ���������ʽ �磺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp

        p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //����script��ǩ

        p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //����style��ǩ

        p_special = Pattern.compile(regEx_special,Pattern.CASE_INSENSITIVE);
        m_special = p_special.matcher(htmlStr);
        htmlStr = m_special.replaceAll(""); //����style��ǩ


        p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //����html��ǩ
        htmlStr = htmlStr.replace("  ", "");
        htmlStr = htmlStr.replace("\n", "");
        htmlStr = htmlStr.replace("\t", "");
        textStr = htmlStr.trim();
        textStr = htmlStr;
    }catch(Exception e){

    }
    return textStr;//�����ı��ַ���
}



}
