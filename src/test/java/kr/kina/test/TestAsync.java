package kr.kina.test;

import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

public class TestAsync {

	@Async
	@Test
	public void testAsync() throws Exception {
		
		
		System.out.println("Start ---- ");
		
		try{
			System.out.println("��ٸ�����..");
			Thread.sleep(5000);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("End ---- ");
	
	}
	
}
