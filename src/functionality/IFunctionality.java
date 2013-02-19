package functionality;

import data.IData.Operator;

public interface IFunctionality {

	Operator getOperator(int id) throws FunctionalityException;
	Operator[] getOperators();
	Operator createOperator(String name, String cpr, String password) throws FunctionalityException;
	void renameOperator(int id, String newName) throws FunctionalityException;
	void changeOperatorPassword(int id, String oldPassword, String newPassword) throws FunctionalityException;
	
	public class FunctionalityException extends Exception {
	
		public FunctionalityException(String message) {
			super(message);
		}
		
	}

}