package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import controller.LoginController;
import dao.UserDao;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLogin {

	LoginController lgController;
	private static UserDao usDao = new UserDao();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usDao.setIsFileUtil(new FileUtilMock());
	}
	
	@Before
	public void setUp() throws Exception {
		lgController = new LoginController();
	
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// userId null
	@Test
	public void testGetFlagLogin_TestCase1() {
		String userId = null;
		String password = null;
		int expect = 1;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}

	// userId invalid
	@Test
	public void testGetFlagLogin_TestCase2() {
		String userId = "1234567";
		String password = null;
		int expect = 1;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
	// user not exists
	@Test
	public void testGetFlagLogin_TestCase3() {
		String userId = "1234567890098765";
		String password = null;
		int expect = 2;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
	// user login first time
	@Test
	public void testGetFlagLogin_TestCase4() {
		String userId = "1234567890123456";
		String password = "12345678";
		int expect = 5;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
	// user login more than one
	@Test
	public void testGetFlagLogin_TestCase5() {
		String userId = "2345678901234567";
		String password = "ngocngoc";
		int expect = 6;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
	// user login fail one time
	@Test
	public void testGetFlagLogin_TestCase6() {
		String userId = "1234567890123456";
		String password = "123";
		int expect = 4;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
	// user login fail two times
	@Test
	public void testGetFlagLogin_TestCase7() {
		String userId = "1234567890123456";
		String password = "123";
		int expect = 4;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
	// user login fail three time -> user blocked
	@Test
	public void testGetFlagLogin_TestCase8() {
		String userId = "1234567890123456";
		String password = "123";
		int expect = 3;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
	// user blocked
	@Test
	public void testGetFlagLogin_TestCase9() {
		String userId = "1234567890123456";
		String password = "123";
		int expect = 3;
		assertEquals(expect, lgController.getFlagLogin(usDao, userId, password));
	}
	
}
