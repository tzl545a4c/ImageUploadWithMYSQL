package B1_SystemManager;

import WebServer.EnableFunciton.WebServer;

public class B1_2_SystemEnable {

	public B1_2_SystemEnable() {
		
	}
	
	public void doEnable() {
		new WebServer().start();
	}
}
