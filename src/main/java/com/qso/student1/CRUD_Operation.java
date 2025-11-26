package com.qso.student1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUD_Operation {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
 
		String url = "jdbc:postgresql://localhost:5432/student1";
		String user = "postgres";
		String password = "root";
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);

			while (true) {

				System.out.println(
						"Press 1 For Insert Data\nPress 2 For Update the Data\nPress 3 For Fetch the Data\nPress 4 For Delete the Data\nPress 5 For Exit ");
				System.out.println("Choose your Choice");
				int choice = scn.nextInt();
				String sql = null;
				switch (choice) {
				case 1:
					sql = "INSERT INTO student_deatails VALUES(?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);

					System.out.println("Enter Your ID");
					preparedStatement.setInt(1, scn.nextInt());

					System.out.println("Enter Your NAME");
					preparedStatement.setString(2, scn.next());

					System.out.println("Enter Your AGE");
					preparedStatement.setInt(3, scn.nextInt());

					System.out.println("Enter Your MARKS");
					preparedStatement.setInt(4, scn.nextInt());
					System.out.println("Student Record is Stored Successfully");

					preparedStatement.execute();
					break;
				case 2:
					sql = "UPDATE student_deatails set age = ? WHERE id = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					System.out.println("Enter ID to Update");
					statement.setInt(2, scn.nextInt());
					System.out.println("Enter Age to Update");
					statement.setInt(1, scn.nextInt());

					statement.executeUpdate();
					System.out.println("Age Updated");
					break;
				case 3:

					sql = "SELECT * FROM student_deatails";
					Statement stm = connection.createStatement();
					ResultSet resultSet = stm.executeQuery(sql);

					while (resultSet.next()) {
						System.out.println("\n--- Student Records ---");
						System.out.println(
								resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + resultSet.getInt(3) + " | " + resultSet.getInt(4));
					}
					break;
				case 4:
					sql = "DELETE FROM student_deatails WHERE id = ?";

					PreparedStatement ps = connection.prepareStatement(sql);
					System.out.println("Enter ID to Delete");
					ps.setInt(1, scn.nextInt());

					ps.executeUpdate();
					System.out.println("Record Deleted Successfully");

					break;
				case 5:
					System.out.println("Closing connection...");
					connection.close();
					scn.close();
					System.out.println("Exited Successfully Done!");
					return; // ✔ exit the main method safely

				default:
					System.out.println("Invalid Choice");
				}

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
