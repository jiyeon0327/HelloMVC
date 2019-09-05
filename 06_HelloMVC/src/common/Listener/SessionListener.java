package common.Listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	private int count=0;
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	//아래는 로그인을 하지 않더라도 자동으로 세션이 생성됨.
//     	System.out.println("세션생성: "+ count);
//     	count++;
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
//         System.out.println("세션종료");
//         count--;
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	//로그인을 했을 때야 세션이 생성되도록 할수 있게 만들기
    	 count++;//증가시키기
    	System.out.println("세션생성: "+ count);
        
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    		//아래는 로그아웃을 했을 때 세션삭제처리를 함
    		count--;
        	System.out.println("세션삭제: "+count);
        	//세션을 종료하더라도 다시 redirect로 넘어가서 세션이 생성됨...
        	//settimeout시점까지 삭제가 안됨(창을 꺼버리면 왓슨이 알지 못해 정확하게 알수 없음,이럴때 이벤트처리)
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}
