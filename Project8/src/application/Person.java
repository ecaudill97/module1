package application;

import java.sql.*;

public class Person {

	// constructors

	// class variables

	private String firstName;

	public Person(int id, String firstName, String lastName, int age, long ssn, long creditCard) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.id = id;
		this.ssn = ssn;
		this.creditCard = creditCard;
	}

	private String lastName;
	private int age;
	private int id;
	private long ssn;
	private long creditCard;

	// SQL methods

	public static void insertPerson(Person person) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String sql = "INSERT INTO PERSONS (ID,FIRST,LAST,AGE,SSN,CC) " + "VALUES (" + person.getId() + ",'"
					+ person.getFirstName() + "','" + person.getLastName() + "'," + person.getAge() + ","
					+ person.getSsn() + "," + person.getCreditCard() + ")";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

	public static void showAll() {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONS;");

			while (rs.next()) {
				int id = rs.getInt("ID");
				String first = rs.getString("FIRST");
				String last = rs.getString("LAST");
				int age = rs.getInt("AGE");
				long ssn = rs.getLong("SSN");
				long cc = rs.getLong("CC");

				System.out.println("ID = " + id);
				System.out.println("FIRST = " + first);
				System.out.println("LAST = " + last);
				System.out.println("AGE = " + age);
				System.out.println("SSN = " + ssn);
				System.out.println("CC = " + cc);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public static void selectPerson(int personID) {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONS WHERE ID =" + personID + ";");

			while (rs.next()) {
				int id = rs.getInt("ID");
				String first = rs.getString("FIRST");
				String last = rs.getString("LAST");
				int age = rs.getInt("AGE");
				long ssn = rs.getLong("SSN");
				long cc = rs.getLong("CC");

				System.out.println("ID = " + id);
				System.out.println("FIRST = " + first);
				System.out.println("LAST = " + last);
				System.out.println("AGE = " + age);
				System.out.println("SSN = " + ssn);
				System.out.println("CC = " + cc);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	public static void deletePerson(String firstN, String lastN) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "DELETE from PERSONS where FIRST ='" + firstN +"' AND LAST ='"+lastN+ "';";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

	// setters and getters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public long getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
