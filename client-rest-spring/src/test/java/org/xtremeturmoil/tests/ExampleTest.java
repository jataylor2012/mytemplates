package org.xtremeturmoil.tests;

import org.junit.Assert;
import org.junit.Test;
import org.xtremeturmoil.model.Example;
import org.xtremeturmoil.service.ExampleService;

public class ExampleTest {

	@Test
	public void testStringIsCorrect() {
		ExampleService service = new ExampleService();
		Example example = service.test();
		Assert.assertTrue(example.getTest().equals("This is a test."));
	}
}
