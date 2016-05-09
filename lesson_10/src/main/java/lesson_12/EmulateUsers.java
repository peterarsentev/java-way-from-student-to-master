package lesson_12;

import lesson_4.Validator;
import lesson_6.ClinicUI;
import lesson_6.IClinic;
import lesson_6.actions.Action;
import lesson_6.actions.CreateClientAction;
import lesson_6.actions.ShowAction;

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

	public ClinicUI build(IClinic clinic, String[] anwers, Action... actions) {
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
