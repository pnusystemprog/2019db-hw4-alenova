package hw4_db;
import java.sql.*; 
import java.util.*;

public class MarzhanAlenova_201624632 {
	public static void main(String args[]) { 
		try{ 
			int operation;
			Scanner input = new Scanner(System.in);
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); 

			Locale.setDefault(Locale.ENGLISH);
			
			Connection connection = DriverManager.getConnection( 
	             "jdbc:oracle:thin:@Qwerty:1521:XE", "sys", "password"); 
	  
			if (connection != null)              
				System.out.println("Successfully Connected!");             
			else            
				System.out.println("Connection Failed!"); 
			
			int lifeTime = 0;
			while(lifeTime == 0) {
				System.out.println("Choose the number of operation that you want to prefer: \n"); 
				System.out.println("1. Inserting the data into table. \n");
				System.out.println("2. Displaying the data from table. \n");
				System.out.println("3. Displaying the result of JOIN SQL processing. \n");
				System.out.println("4. No operation left, close the connection. \n");
				
				operation = Integer.parseInt(input.next());
				
				if(operation == 1) {
					int num_table;
					System.out.println("Enter the Table number:\n");
					System.out.println("1. EMP\n");
					System.out.println("2. DEPT\n");
					num_table = input.nextInt();
					
					if(num_table == 1) {
						String Fname, Minit, Lname, Bdate, Sex, Address;
						int Ssn, Salary, Super_ssn, Dno;
						
						System.out.println("Enter employee's first name?\n");
						Fname = input.next();
						System.out.println("Enter employee's minit?\n");
						Minit = input.next();
						System.out.println("Enter employee's last name?\n");
						Lname = input.next();
						System.out.println("Enter the employee's ssn?\n");
						Ssn = input.nextInt();
						System.out.println("Enter employee's birthday date? In the form mm/dd/yyyy!\n");
						Bdate = input.next();
						input.nextLine();
						System.out.println("Enter employee's address?\n");
						Address = input.nextLine();
						System.out.println("Enter the employee's gender (only first capital letter)?\n");
						Sex = input.next();
						System.out.println("Enter the employee's salary?\n");
						Salary = input.nextInt();
						System.out.println("Enter the employee's Super_ssn?\n");
						Super_ssn = input.nextInt();
						System.out.println("Enter the employee's department number?\n");
						Dno = input.nextInt();
						
						String sql1 = "INSERT INTO EMP(Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES(?,?,?,?,TO_DATE(?, 'mm/dd/yyyy'),?,?,?,?,?)";
						
						PreparedStatement pstmt1 = connection.prepareStatement(sql1);
						
				        pstmt1.setString(1, Fname);
				        pstmt1.setString(2, Minit);
				        pstmt1.setString(3, Lname);
				        pstmt1.setInt(4, Ssn);
				        pstmt1.setString(5, Bdate);
				        pstmt1.setString(6, Address);
				        pstmt1.setString(7, Sex);
				        pstmt1.setInt(8, Salary);
				        pstmt1.setInt(9, Super_ssn);
				        pstmt1.setInt(10, Dno);
				        pstmt1.executeUpdate();
				        
				        System.out.println("Insertion into EMP table succesfully done!");
					}
					
					else {
						String Dname, Mgr_ssn_date;
						int Dnumber, Mgr_ssn;
						
						System.out.println("Enter the department's name?\n");
						Dname = input.next();
						System.out.println("Enter the department's number?\n");
						Dnumber = input.nextInt();
						System.out.println("Enter the department manager's ssn?\n");
						Mgr_ssn = input.nextInt();
						System.out.println("Enter the department's Mgr_ssn_date? In the form mm/dd/yyyy!\n");
						Mgr_ssn_date = input.next();
						
						String sql2 = "INSERT INTO DEPT(Dname, Dnumber, Mgr_ssn, Mgr_ssn_date) VALUES(?,?,?,TO_DATE(?, 'mm/dd/yyyy'))";
						PreparedStatement pstmt2 = connection.prepareStatement(sql2);
				        pstmt2.setString(1, Dname);
				        pstmt2.setInt(2, Dnumber);
				        pstmt2.setInt(3, Mgr_ssn);
				        pstmt2.setString(4, Mgr_ssn_date);
				        pstmt2.executeUpdate();
				        
				        System.out.println("Insertion into DEPT table succesfully done!");
					}
				}
				else if(operation == 2) {
					int num_table;
					
					System.out.println("Enter the table number:\n");
					System.out.println("1. EMP\n");
					System.out.println("2. DEPT\n");
					
					num_table = input.nextInt();
					
					if(num_table == 1) {
						int numOfRows = 0;
						Statement stmt1 = connection.createStatement();
						ResultSet resultSet1 = stmt1.executeQuery("SELECT Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno FROM EMP");
						
						while(resultSet1.next()) {
							numOfRows = numOfRows + 1;
							System.out.println(resultSet1.getString("Fname"));
							System.out.println(resultSet1.getString("Minit"));
							System.out.println(resultSet1.getString("Lname"));
							System.out.println(resultSet1.getInt("Ssn"));
							System.out.println(resultSet1.getString("Bdate"));
							System.out.println(resultSet1.getString("Address"));
							System.out.println(resultSet1.getString("Sex"));
							System.out.println(resultSet1.getString("Salary"));
							System.out.println(resultSet1.getInt("Super_ssn"));
							System.out.println(resultSet1.getInt("Dno"));
							System.out.println("\n");
							System.out.println("1 row data displayed.");
							System.out.println("\n");
						}
						System.out.println("Displaying the EMP Table data is done!");
						System.out.println("In total, " + numOfRows + " rows displayed.");
					}
					
					
					else if(num_table == 2) {
						int numOfRows = 0;
						Statement stmt2 = connection.createStatement();
						ResultSet resultSet2 = stmt2.executeQuery("SELECT Dname,Dnumber,Mgr_ssn,Mgr_ssn_date FROM DEPT");
						
						while(resultSet2.next()) {
							numOfRows = numOfRows + 1;
							System.out.println(resultSet2.getString("Dname"));
							System.out.println(resultSet2.getInt("Dnumber"));
							System.out.println(resultSet2.getInt("Mgr_ssn"));
							System.out.println(resultSet2.getString("Mgr_ssn_date"));
							
							System.out.println("\n");
							System.out.println("1 row data displayed.");
							System.out.println("\n");
						}
						System.out.println("Displaying the DEPT Table data is done!");
						System.out.println("In total, " + numOfRows + " rows displayed.");
					}
				}
				else if(operation == 3) {
					int colNum = 0, colPos = 1;
					input.nextLine();
					System.out.println("Please, enter the SQL JOIN query that you want to execute. All in one line!\n");
					String sqlll = input.nextLine();
					
					String colName = sqlll.split(" ")[colPos];
					String from = "FROM";
					String from_1 = "from";
					String from_2 = "From";
					
					while((!colName.equals(from)) && (!colName.equals(from_1)) && (!colName.equals(from_2))) {
						colNum = colNum + 1;
						colPos = colPos + 1;
						colName = sqlll.split(" ")[colPos];
					}
					
					Statement stmt = connection.createStatement();
					ResultSet resultset = stmt.executeQuery(sqlll);
					
					while(resultset.next()) {
						for(int i = 1; i <= colNum; i++) {
							if((sqlll.split(" ")[i].equals("*"))) {
								System.out.println("Fname: ");
								System.out.println(resultset.getString("Fname"));
								System.out.println("Minit: ");
								System.out.println(resultset.getString("Minit"));
								System.out.println("Lname: ");
								System.out.println(resultset.getString("Lname"));
								System.out.println("Ssn: ");
								System.out.println(resultset.getInt("Ssn"));
								System.out.println("Bdate: ");
								System.out.println(resultset.getString("Bdate"));
								System.out.println("Sex: ");
								System.out.println(resultset.getString("Sex"));
								System.out.println("Salary: ");
								System.out.println(resultset.getInt("Salary"));
								System.out.println("Super_ssn: ");
								System.out.println(resultset.getInt("Super_ssn"));
								System.out.println("Dno: ");
								System.out.println(resultset.getInt("Dno"));
								System.out.println("Dname: ");
								System.out.println(resultset.getString("Dname"));
								System.out.println("Dnumber ");
								System.out.println(resultset.getInt("Dnumber"));
								System.out.println("Mgr_ssn: ");
								System.out.println(resultset.getInt("Mgr_ssn"));
								System.out.println("Mgr_ssn_date: ");
								System.out.println(resultset.getString("Mgr_ssn_date"));
							}
							
							if((sqlll.split(" ")[i].equals("Fname")) || (sqlll.split(" ")[i].equals("Fname,"))) {
								System.out.println("Fname: ");
								System.out.println(resultset.getString("Fname"));
							}
							if((sqlll.split(" ")[i].equals("Minit")) || (sqlll.split(" ")[i].equals("Minit,"))) {
								System.out.println("Minit: ");
								System.out.println(resultset.getString("Minit"));
							}
							if((sqlll.split(" ")[i].equals("Lname")) || (sqlll.split(" ")[i].equals("Lname,"))) {
								System.out.println("Lname: ");
								System.out.println(resultset.getString("Lname"));
							}
							if((sqlll.split(" ")[i].equals("Ssn")) || (sqlll.split(" ")[i].equals("Ssn,"))) {
								System.out.println("Ssn: ");
								System.out.println(resultset.getInt("Ssn"));
							}
							if((sqlll.split(" ")[i].equals("Bdate")) || (sqlll.split(" ")[i].equals("Bdate,"))) {
								System.out.println("Bdate: ");
								System.out.println(resultset.getString("Bdate"));
							}
							if((sqlll.split(" ")[i].equals("Address")) || (sqlll.split(" ")[i].equals("Address,"))) {
								System.out.println("Address: ");
								System.out.println(resultset.getString("Address"));
							}
							if((sqlll.split(" ")[i].equals("Sex")) || (sqlll.split(" ")[i].equals("Sex,"))) {
								System.out.println("Sex: ");
								System.out.println(resultset.getString("Sex"));
							}
							if((sqlll.split(" ")[i].equals("Salary")) || (sqlll.split(" ")[i].equals("Salary,"))) {
								System.out.println("Salary: ");
								System.out.println(resultset.getInt("Salary"));
							}
							if((sqlll.split(" ")[i].equals("Super_ssn")) || (sqlll.split(" ")[i].equals("Super_ssn,"))) {
								System.out.println("Super_ssn: ");
								System.out.println(resultset.getInt("Super_ssn"));
							}
							if((sqlll.split(" ")[i].equals("Dno")) || (sqlll.split(" ")[i].equals("Dno,")) ) {
								System.out.println("Dno: ");
								System.out.println(resultset.getInt("Dno"));
							}
							
							if((sqlll.split(" ")[i].equals("Dname")) || (sqlll.split(" ")[i].equals("Dname,"))) {
								System.out.println("Dname: ");
								System.out.println(resultset.getString("Dname"));
							}
							if((sqlll.split(" ")[i].equals("Dnumber")) || (sqlll.split(" ")[i].equals("Dnumber,"))) {
								System.out.println("Dnumber ");
								System.out.println(resultset.getInt("Dnumber"));
							}
							if((sqlll.split(" ")[i].equals("Mgr_ssn")) || (sqlll.split(" ")[i].equals("Mgr_ssn,"))) {
								System.out.println("Mgr_ssn: ");
								System.out.println(resultset.getInt("Mgr_ssn"));
							}
							if((sqlll.split(" ")[i].equals("Mgr_ssn_date")) || (sqlll.split(" ")[i].equals("Mgr_ssn_date,")) ) {
								System.out.println("Mgr_ssn_date: ");
								System.out.println(resultset.getString("Mgr_ssn_date"));
							}
							System.out.println("\n");
						}
					}
				}
				else if(operation == 4) {
					connection.close(); 
					System.out.println("Connection is closed!");
					lifeTime = 1;
				}
			}
	        } 
	        catch(Exception e) { 
	            System.out.println(e); 
	        } 
	} 
}
