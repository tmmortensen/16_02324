package functionality;

import data.IData;
import data.IData.DataException;
import data.IData.Operator;

public class Functionality implements IFunctionality {

	private IData data;
	
	public Functionality(IData data) {
		this.data = data;
	}
	
	public Operator getOperator(int id) throws FunctionalityException {
		try {
			return data.getOperator(id);
		} catch (DataException dataException) {
			throw new FunctionalityException(dataException.getMessage());
		}
	}
	public Operator[] getOperators() {
		return data.getOperators();
	}
	public Operator createOperator(String name, String cpr, String password) throws FunctionalityException {
		validateName(name);
		validateCpr(cpr);
		validatePassword(password);
		Operator operator = new Operator(findFirstAvailableId(), name, cpr, password);
		data.createOperator(operator);
		return operator;
	}
	public void renameOperator(int id, String newName) throws FunctionalityException {
		validateName(newName);
		try {
			data.getOperator(id).setName(newName);
		} catch (DataException dataException) {
			throw new FunctionalityException(dataException.getMessage());
		}
	}
	public void changeOperatorPassword(int id, String oldPassword, String newPassword) throws FunctionalityException {
		validatePassword(newPassword);
		try {
			Operator operator = data.getOperator(id);
			if (!operator.getPassword().equals(oldPassword)) {
				throw new FunctionalityException("Old password is incorrect.");
			}
			operator.setPassword(newPassword);
		} catch (DataException dataException) {
			throw new FunctionalityException(dataException.getMessage());
		}
	}
	
	private int findFirstAvailableId() throws FunctionalityException {
		int id = 11;
		Operator[] operators = data.getOperators();
		Loop:
		while (true) {
			for (Operator operator : operators) {
				if (operator.getId() == id) {
					if (id == 99) {
						throw new FunctionalityException("All ids are taken.");
					}
					id++;
					continue Loop;
				}
			}
			return id;
		}
	}
	private void validateName(String name) throws FunctionalityException {
		if (name.length() < 1 || name.length() > 20) {
			throw new FunctionalityException("Name is invalid.");
		}
	}
	private void validateCpr(String cpr) throws FunctionalityException {
		if (!cpr.matches("^[0-9]{6}-[0-9]{4}$")) {
			throw new FunctionalityException("CPR is invalid.");
		}
	}
	private void validatePassword(String password) throws FunctionalityException {
		// ...
		//throw new FunctionalityException("Password is invalid.");
	}

}