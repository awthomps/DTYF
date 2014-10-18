package dont.touch.your.friends.controller;

import de.hardcode.jxinput.Button;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputButtonEventListener;
import de.hardcode.jxinput.event.JXInputEventManager;

public class ControllerButtonListener implements JXInputButtonEventListener {

	public ControllerButtonListener(Button button) {
		JXInputEventManager.addListener(this, button);
	}

	@Override
	public void changed(JXInputButtonEvent ev) {
		// TODO Auto-generated method stub
		
	}

}
