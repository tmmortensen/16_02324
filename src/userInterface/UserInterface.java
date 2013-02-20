package userInterface;

import java.io.InputStream;
import java.util.Scanner;

import data.IData.Operator;

import functionality.IFunctionality;
import functionality.IFunctionality.FunctionalityException;

public class UserInterface implements IUserInterface {

	private IFunctionality functionality;
	private Scanner scanner;
	
	public UserInterface(IFunctionality functionality, InputStream stream) {
		this.functionality = functionality;
		scanner = new Scanner(stream);
		showMainMenu();
	}
	
	private void showMainMenu() {
		loop:
		while (true) {
			System.out.println();
			System.out.println(" 1: Operatør administration.");
			System.out.println(" 2: Ændring af adgangskode."); // Indtast bruger id, old password, new password, new password. (new password valideres)
			System.out.println(" 3: Test applikation."); // Programmet beder om tara vægt i kg herefter brutto i kg afsluttende udskrive nettovægt. Formel: Brutto=Netto+Tara
			System.out.println(" 4: Afslut.");
			System.out.println();
			switch (readInt("", 1, 4)) {
				case 1:
					showAdminLogin();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break loop;
			}
		}
	}
	private void showAdminLogin() {
		String response;
		while (true) {
			System.out.println();
			response = readString("Administrator adgangskode");
			if (response.equals(" ")) {
				break;
			}
			try {
				// ------------------------------------------------------
				// Hard coded system administrator account id.
				if (functionality.verifyOperatorPassword(10, response)) {
					showAdminOptions();
					break;
				} else {
					System.out.println();
					System.out.println(" Adgangskoden er forkert. Tryk mellemrum efterfuldt af enter for at vende tilbage til forrige menu.");
				}
				// ------------------------------------------------------
			} catch (FunctionalityException e) {
				System.out.println();
				System.out.println(" Fejl: " + e.getMessage());
			}
		}
	}
	private void showAdminOptions() {
		loop:
		while (true) {
			System.out.println();
			System.out.println(" 1: Opret operatør.");
			System.out.println(" 2: Tilbage til forrige menu.");
			System.out.println();
			switch (readInt("", 1, 2)) {
				case 1:
					showCreateOperator();
					break;
				case 2:
					break loop;
			}
		}
	}
	private void showCreateOperator() {
		String name;
		String cpr;
		String password;
		while (true) {
			System.out.println();
			name = readString("Operatør navn");
			if (name.equals(" ")) {
				break;
			}
			cpr = readString("Operatør CPR");
			if (cpr.equals(" ")) {
				break;
			}
			password = readString("Operatør adgangskode");
			if (password.equals(" ")) {
				break;
			}
			try {
				functionality.createOperator(name, cpr, password);
				System.out.println();
				System.out.println(" Operatøren blev oprettet.");
				break;
			} catch (FunctionalityException e) {
				System.out.println();
				System.out.println(" Fejl: " + e.getMessage());
			}
		}
	}
	
	private int readInt(String message, int minimum, int maximum) {
		if (message.length() > 0) {
			System.out.print(" " + message + ": ");
		} else {
			System.out.print(" ");
		}
		int response;
		while (true) {
			try {
				response = scanner.nextInt();
				if (response < minimum) {
					System.out.print(" Tallet er mindre end " + minimum + ". Prøv igen: ");
				} else if (response > maximum) {
					System.out.print(" Tallet er større end " + maximum + ". Prøv igen: ");
				} else {
					return response;
				}
			} catch(Exception e) {
				scanner.skip(".*");
				System.out.print(" Ugyldigt tal. Prøv igen: ");
			}
		}
	}
	private String readString(String message) {
		if (message.length() > 0) {
			System.out.print(" " + message + ": ");
		} else {
			System.out.print(" ");
		}
		String response;
		while (true) {
			response = scanner.nextLine();
			if (response.length() > 0) {
				return response;
			}
		}
	}
	
}