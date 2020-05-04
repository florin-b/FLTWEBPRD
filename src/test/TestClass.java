package test;

import model.SendSmsClient;

public class TestClass {

	public static void main(String[] args) {

		try {
			
			
			SendSmsClient smsClient = new SendSmsClient();


			smsClient.sendSms("0742290177", "Salutari de la Arabesque!");
			
			
			
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
