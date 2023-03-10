package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.FirstLoginController;
import model.UserAnswer;

public class TestAnswerUser {
	FirstLoginController fController;

	@Before
	public void setUp() throws Exception {
		fController = new FirstLoginController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAnsIsEmpty_InputInvalid_ReturnTrue() {
		UserAnswer ans = new UserAnswer(null, null, null);
		assertTrue(fController.ansIsEmpty(ans));	
	}
	
	@Test
	public void testAnsIsEmpty_InputAns1NotEmpty_ReturnFalse() {
		UserAnswer ans = new UserAnswer("answer question 1", null, null);
		assertFalse(fController.ansIsEmpty(ans));
	}
	
	@Test
	public void testAnsIsEmpty_InputAns2NotEmpty_ReturnFalse() {
		UserAnswer ans = new UserAnswer(null, "answer question 2", null);
		assertFalse(fController.ansIsEmpty(ans));
	}
	
	@Test
	public void testAnsIsEmpty_InputAns3NotEmpty_ReturnFalse() {
		UserAnswer ans = new UserAnswer(null, null, "answer question 3");
		assertFalse(fController.ansIsEmpty(ans));
	}

}
