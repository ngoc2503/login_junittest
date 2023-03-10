package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.FirstLoginController;
import model.Password;
import model.User;

public class TestFirstLogin {
	FirstLoginController fController;
	@Before
	public void setUp() throws Exception {
		fController = new FirstLoginController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testControFlowFirstLogin_InputOldPassInvalid_Return1() {
		Password pas = new Password("123", null, null);
		User us3 = new User("3456789012345678", "ngoc1234");
		int expect = 1;
		assertEquals(expect, fController.controlFlowFirstLogin(pas, us3));
	}
	
	@Test
	public void testControFlowFirstLogin_InputNewPassInvalid_Return2() {
		Password pas = new Password("ngoc1234", "123", null);
		User us3 = new User("3456789012345678", "ngoc1234");
		int expect = 2;
		assertEquals(expect, fController.controlFlowFirstLogin(pas, us3));
	}

	@Test
	public void testControFlowFirstLogin_InputConfirmPassNotMatch_Return3() {
		Password pas = new Password("ngoc1234", "funix123", "123");
		User us3 = new User("3456789012345678", "ngoc1234");
		int expect = 3;
		assertEquals(expect, fController.controlFlowFirstLogin(pas, us3));
	}
	
	@Test
	public void testControFlowFirstLogin_InputPasswordValid_Return4() {
		Password pas = new Password("ngoc1234", "funix123", "funix123");
		User us3 = new User("3456789012345678", "ngoc1234");
		int expect = 4;
		assertEquals(expect, fController.controlFlowFirstLogin(pas, us3));
	}
}
