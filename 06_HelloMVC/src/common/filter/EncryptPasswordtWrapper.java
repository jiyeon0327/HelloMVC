package common.filter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class EncryptPasswordtWrapper extends HttpServletRequestWrapper {
	//기본생성자
	public EncryptPasswordtWrapper(HttpServletRequest request) {
		super(request);
	}
	//암호화를 하기 위해 client가 보낸 데이터를 불러오는 getParameter 메소드를 오버라이딩 처리한다.
	@Override
	public String getParameter(String key) {
		String value="";
		if(key!=null && (key.equals("password")||key.equals("cPw")||key.equals("nPw"))) {
			value=getEncryptPw(super.getParameter(key));
			System.out.println(value);
			
		}else {
			value=super.getParameter(key);
		}
		return value;
	}
	private static String getEncryptPw(String pw) {
		//해쉬 알고리즘 이용함. sha512방식으로 암호화 처리
		//암호화 처리하기 위해서는 java api에서 기본적으로 제공하는 암호화 처리 객체가
		//있으면 =>MessageDigest
		//암호화는 비트단위(컴퓨터 처리단위는 바이트 단위)로 이루어진다.
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("SHA-512");
			//암호화 
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] bytes;
		try {
		//비트처리하기 위해서 바이트단위를 쪼개기
			bytes=pw.getBytes("UTF-8");
			md.update(bytes);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		String encPw=Base64.getEncoder().encodeToString(md.digest());//암호화된 값 불러오기=>불러온걸 string으로 출력
			return encPw;
	
	}
	
}