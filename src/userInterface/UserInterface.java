package userInterface;

import functionality.IFunctionality;

public class UserInterface implements IUserInterface {

	private IFunctionality functionality;
	
	public UserInterface(IFunctionality functionality) {
		this.functionality = functionality;
	}
	
}