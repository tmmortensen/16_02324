package data;

import java.util.ArrayList;

public class Data implements IData {

	private ArrayList<Operator> operators = new ArrayList<Operator>();
	
	public Operator getOperator(int id) throws DataException {
		for (Operator operator : operators) {
			if (operator.getId() == id) {
				return operator;
			}
		}
		throw new DataException("Cannot find operator with id " + id + ".");
	}
	public Operator[] getOperators() {
		return (Operator[]) operators.toArray();
	}
	public void createOperator(Operator operator) {
		operators.add(operator);
	}
	public void updateOperator(Operator operator) {
		// Ingen handling påkrævet.
	}

}