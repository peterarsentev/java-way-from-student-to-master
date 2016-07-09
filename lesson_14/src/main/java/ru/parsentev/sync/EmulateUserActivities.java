package ru.parsentev.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.clinic.ClinicUI;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.05.2016
 */
public class EmulateUserActivities extends Thread {

	private final ClinicUI ui;

	public EmulateUserActivities(ClinicUI ui) {
		this.ui = ui;
	}

	@Override
	public void run() {
		ui.show();
	}
}