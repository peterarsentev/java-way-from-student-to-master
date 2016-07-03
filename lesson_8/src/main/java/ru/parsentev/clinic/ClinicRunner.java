package ru.parsentev.clinic;

import ru.parsentev.clinic.actions.CreatePetAction;
import ru.parsentev.clinic.actions.CreateClientAction;
import ru.parsentev.clinic.actions.DeleteClientAction;
import ru.parsentev.clinic.actions.DeletePetAction;
import ru.parsentev.clinic.actions.SearchClientAction;
import ru.parsentev.clinic.actions.SearchPetAction;
import ru.parsentev.clinic.actions.ShowAction;
import ru.parsentev.clinic.actions.UpdateClientAction;
import ru.parsentev.clinic.actions.UpdatePetAction;
import ru.parsentev.interact.ConsoleIO;
import ru.parsentev.interact.Validator;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * ����� ��� ������� �������.
 * @author parsentev
 * @since 11.08.2015
 */
public class ClinicRunner {
    public static void main(String[] args) {
        final ClinicUI ui = new ClinicUI(
                new Clinic(),
                new Validator(
                        new ConsoleIO(
                                new Scanner(System.in),
                                System.out
                        )
                )
        );
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
