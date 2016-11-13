package kr.kina.listener;



import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Tomcat ���ǻ��� üũ : Session�� �����ǰ�, �ı��� �� Ȯ���ϱ� ���� Ŭ���� 
 */
public class HttpSessionListenerCheck implements HttpSessionListener{

	private static final Logger log = LoggerFactory.getLogger(HttpSessionListenerCheck.class);
	private static int sessionCount = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		sessionCount++;
		log.info("Session ID : ".concat(se.getSession().getId()) + " --- Count : " + sessionCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		sessionCount--;
		log.info("Session ID : ".concat(se.getSession().getId()) + " --- Count : " + sessionCount);
	}
}
