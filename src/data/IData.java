package data;

public interface IData {

	Operator getOperator(int id) throws DataException;
	Operator[] getOperators();
	void createOperator(Operator operator);
	void updateOperator(Operator operator);
	
	public class Operator {
	
		private int id;          // Indenfor intervallet 11-99.
		private String name;     // Maksimalt 20 karakterer.
		private String cpr;
		private String password; // Ukrypteret.
		
		public Operator(int id, String name, String cpr, String password) {
			this.id = id;
			this.name = name;
			this.cpr = cpr;
			this.password = password;
		}
	
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCpr() {
			return cpr;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
	
	public class DataException extends Exception {
	
		public DataException(String message) {
			super(message);
		}
		
	}
	
}