package common.policy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		
		File newFile=null;//새로생성한 파일명칭
		//무조건 한번 실행되게 만들기-->왜 이걸로해?중복값이 있는지 확인하기 위해서 
		do {
			//날짜(시간)+임의의 값 이렇게 rename할것임
			long currentTime = System.currentTimeMillis();
			//시간으로 또 나누기
			SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int rndNum = (int)(Math.random()*1000);
			
			//파일명 처리하기->oldfile에서 다른걸로
			//ji.png라면 ji만 rename처리하고 png만 따로 보관해야함
			String oldName = oldFile.getName();
			//substring방식으로 .png만 따로 자르기 가능~~~~~하지만 안전하게 하기 위해서 -1이 나오면 null값이 될수 있으니까 미리 방지하도록 쓰기
			
			//얘는 아래와 다르게 split방식으로 한것
//			String[] t = oldName.split(".");
//			ext = t[t.length-1];
			int point= oldName.lastIndexOf(".");
			String ext="";
			if(point>-1) {
				ext = oldName.substring(point);
			}
			//새파일 생성
			String newName = sf.format(new Date(currentTime))+"_" + rndNum + ext;			
			newFile = new File(oldFile.getParent(),newName);//여기는 파일경로
			//getParent():부모, oldFile의 폴더  , 그냥 파일 경로라고 생각하면 편함 /그럼 oldFile은 자식
			
			
		}while(!createNew(newFile));//true아님 false나옴
		//!(true)=>결과는 false/!(false)=>결과는 true!
		//반복문에서 false나오면 나감 
		//true로 나올때만 반복문 한번 더 돈다
		
		return newFile;
		
	}
	private boolean createNew(File newFile) {
		try {
			return newFile.createNewFile();
		}catch(IOException e) {
			return false;//예외가 발생하면 실패한것,생성안하는것(덮어쓰기안함)
		}
	}

}
