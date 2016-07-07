package ru.parsentev.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.clinic.ClinicUI;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.05.2016
 */
public class EmulateUserActivities extends Thread {
	private static final Logger log = LoggerFactory.getLogger(EmulateUserActivities.class);

	private final ClinicUI ui;

	public EmulateUserActivities(ClinicUI ui) {
		this.ui = ui;
	}

	@Override
	public void run() {
		ui.show();
	}
}