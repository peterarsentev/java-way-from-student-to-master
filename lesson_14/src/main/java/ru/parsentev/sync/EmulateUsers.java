package ru.parsentev.sync;


import ru.parsentev.clinic.ClinicUI;
import ru.parsentev.clinic.IClinic;
import ru.parsentev.clinic.actions.Action;
import ru.parsentev.clinic.actions.CreateClientAction;
import ru.parsentev.clinic.actions.ShowAction;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.05.2016
 */
public class EmulateUsers {
	public static void main(String[] args) {
		new EmulateUsers().startActivities();
	}

	public void startActivities() {
		IClinic clinic = new SynchClinic();
		new EmulateUserActivities(
				this.build(
						clinic,
						new String[] {"0", "yes"},
						new ShowAction()
				)
		).start();
		new EmulateUserActivities(
				this.build(
						clinic,
						new String[] {"1", "Petr", "yes"},
						new CreateClientAction()
				)
		).start();
	}

	public ClinicUI build(IClinic clinic, String[] anwers, Action ... actions) {
		ClinicUI ui = new ClinicUI(
				clinic,
				new StubInput(anwers)
		);
		for (Action action : actions) {
			ui.loadAction(action);
		}
		return ui;
	}
}
