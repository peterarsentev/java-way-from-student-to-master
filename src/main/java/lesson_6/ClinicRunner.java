package lesson_6;

import lesson_6.actions.CreatePetAction;
import lesson_6.actions.CreateClientAction;
import lesson_6.actions.DeleteClientAction;
import lesson_6.actions.DeletePetAction;
import lesson_6.actions.SearchClientAction;
import lesson_6.actions.SearchPetAction;
import lesson_6.actions.ShowAction;
import lesson_6.actions.UpdateClientAction;
import lesson_6.actions.UpdatePetAction;

/**
 * Класс для запуска клиники.
 * @author parsentev
 * @since 11.08.2015
 */
public class ClinicRunner {
	public static void main(String[] args) {
		final ClinicUI ui = new ClinicUI();
		ui.loadAction(new ShowAction());
		ui.loadAction(new CreateClientAction());
		ui.loadAction(new CreatePetAction());
		ui.loadAction(new UpdateClientAction());
		ui.loadAction(new UpdatePetAction());
		ui.loadAction(new DeleteClientAction());
		ui.loadAction(new DeletePetAction());
		ui.loadAction(new SearchClientAction());
		ui.loadAction(new SearchPetAction());
		ui.show();
	}
}
