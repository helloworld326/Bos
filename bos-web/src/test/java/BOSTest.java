import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fly.bos.service.IUseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class BOSTest {
	
	@Autowired
	private IUseService iUserService;

	@Test
	public void test(){
		iUserService.editPassword("1", "123456");
	}
}
