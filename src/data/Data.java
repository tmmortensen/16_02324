package data;

import java.util.ArrayList;

public class Data implements IData {

	private ArrayList<Operator> operators = new ArrayList<Operator>();
		
	@Override
	public Operator getOperator(int id) throws DataException {
		for (Operator operator : operators) {
			if (operator.getId() == id) {
				return operator;
			}
		}
		throw new DataException("Cannot find operator with id " + id + ".");
	}
	@Override
	public Operator[] getOperators() {
		return operators.toArray(new Operator[operators.size()]);
	}
	@Override
	public void createOperator(Operator operator) {
		operators.add(operator);
	}
	@Override
	public void updateOperator(Operator operator) {
		// Ingen handling påkrævet.
	}

}