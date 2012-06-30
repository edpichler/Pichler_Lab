package test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {

	@Test
	public void test() {
		ClassPathXmlApplicationContext ct = new ClassPathXmlApplicationContext(
				new String[] { "classpath:/spring/applicationContext.xml" });
		
	}

}
