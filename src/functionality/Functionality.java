package functionality;

import data.IData;
import data.IData.DataException;
import data.IData.Operator;

public class Functionality implements IFunctionality {

	private IData data;
	
	public Functionality(IData data) {
		this.data = data;
		// --------------------------------------------------------------------------
		// Hard coded system administrator account.
		data.createOperator(new Operator(10, "sysadmin", "", ">02324it!<"));
		// Hard coded test user accounts.
		data.createOperator(new Operator(12, "Peter", "020287-3727", "123445er"));
		data.createOperator(new Operator(13, "Mark", "230285-1123", "123456wri"));
		data.createOperator(new Operator(14, "Nikoline", "240591-3286", "123456kh"));
		data.createOperator(new Operator(15, "Tobias", "270785-1237", "123456khu"));
		data.createOperator(new Operator(16, "Juli", "010190-1123", "123456hku"));
		// --------------------------------------------------------------------------
	}

	@Override
	public Operator getOperator(int id) throws FunctionalityException {
		try {
			return data.getOperator(id);
		} catch (DataException dataException) {
			throw new FunctionalityException(dataException.getMessage());
		}
	}
	@Override
	public Operator[] getOperators() {
		return data.getOperators();
	}
	@Override
	public Operator createOperator(String name, String cpr, String password) throws FunctionalityException {
		validateName(name);
		validateCpr(cpr);
		validatePassword(password);
		Operator operator = new Operator(findFirstAvailableId(), name, cpr, password);
		data.createOperator(operator);
		return operator;
	}
	@Override
	public void renameOperator(int id, String newName) throws FunctionalityException {
		validateName(newName);
		try {
			data.getOperator(id).setName(newName);
		} catch (DataException dataException) {
			throw new FunctionalityException(dataException.getMessage());
		}
	}
	@Override
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
		if (!name.matches("^.{1,20}$")) {
			throw new FunctionalityException("Name is invalid.");
		}
	}
	private void validateCpr(String cpr) throws FunctionalityException {
		if (!cpr.matches("^[0-9]{6}-[0-9]{4}$")) {
			throw new FunctionalityException("CPR is invalid.");
		}
	}
	private void validatePassword(String password) throws FunctionalityException {
		int categories = 0;
		if (password.matches("[A-Z]")) categories++;
		if (password.matches("[a-z]")) categories++;
		if (password.matches("[0-9]")) categories++;
		if (password.matches("[\\.\\-_+!?=]")) categories++;
		if (categories < 3 || !password.matches("^.{6,}$")) {
			throw new FunctionalityException("Password is invalid.");
		}
	}

}