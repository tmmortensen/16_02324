package userInterface;

import functionality.IFunctionality;

public class UserInterface implements IUserInterface {

	private IFunctionality functionality;
	
	public UserInterface(IFunctionality functionality) {
		this.functionality = functionality;
		showMainMenu();
	}
	
	private void showMainMenu() {
		System.out.println();
		System.out.println(" 1: Operatør administration.");
		System.out.println(" 2: Ændring af adgangskode."); // Indtast bruger id, old password, new password, new password. (new password valideres)
		System.out.println(" 3: Test applikation."); // Programmet beder om tara vægt i kg herefter brutto i kg afsluttende udskrive nettovægt. Formel: Brutto=Netto+Tara
		System.out.println(" 4: Afslut.");
		//...
	}
	
}