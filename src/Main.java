import userInterface.UserInterface;
import data.Data;
import functionality.Functionality;

public class Main {

	public static void main(String[] args) {
		new UserInterface(new Functionality(new Data()), System.in);
	}

}