package B1_SystemManager;

import CallFunction.ScenesIDCheck;
import CallFunction.SceneTypeCheck;
import CallFunction.SceneTypeIDCheck;

public class B1_1_SystemInitail {

	public B1_1_SystemInitail() {
		
	}
	
	public void doInitial() {
		new ScenesIDCheck().doCheck();
		new SceneTypeCheck().doCheck();
		new SceneTypeIDCheck().doCheck();
	}
}
