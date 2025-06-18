package com.fif.formsederhana;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.util.Clients;

public class MyViewModel {

	@Command
	public void submit() {
		Clients.showNotification("Success", Clients.NOTIFICATION_TYPE_INFO, null, "bottom_center", 3000);
	}
}