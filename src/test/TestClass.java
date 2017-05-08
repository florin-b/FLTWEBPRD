package test;

import utils.MailOperations;

public class TestClass {

	public static void main(String[] args) {

		try {
			
			System.out.println("123");
			
			MailOperations.sendMail("from flota web");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
