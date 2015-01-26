import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;

	 



public class MainMenuAd extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	Object[][] data = new Object[100][4];
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public String id,name,tickets,strname;
	public int count=0,rownum,addId,addTicket;
	public int[] ticketCount=new int[10];
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	
	
	{try
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://127.0.0.1:3306/test";
		String connectionUser = "root";
		String connectionPassword = "blue";
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
		connectionPassword);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM concert");
		while (rs.next())
		{
				id = rs.getString("id");
				name = rs.getString("name");
				tickets = rs.getString("tickets");
				
				
				data[count][0]=id;
				data[count][1]=name;
				data[count][2]=tickets;
				ticketCount[count]=Integer.valueOf(tickets);
				count++;
		}
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	finally
	{
		try { if (rs != null) rs.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		try { if (stmt != null) stmt.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		try { if (conn != null) conn.close(); } catch (SQLException e) {
			e.printStackTrace(); }
	}
	addId=count;
	}

	
	public MainMenuAd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				rownum=Integer.valueOf(textField.getText());
				 
				 String str2 = Integer.toString(rownum);
				 
				 
				 
				 try
					{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						String connectionUrl = "jdbc:mysql://127.0.0.1:3306/test";
						String connectionUser = "root";
						String connectionPassword = "blue";
						conn = DriverManager.getConnection(connectionUrl, connectionUser,
						connectionPassword);
						stmt = conn.createStatement();
						stmt.executeUpdate("delete from concert where id ="+str2+" limit 1");
						rs = stmt.executeQuery("SELECT * FROM concert");
						count=0;
						
						while (rs.next())
						{
							id = rs.getString("id");
							name = rs.getString("name");
							tickets = rs.getString("tickets");
							
							
							
                            
							
							if (Integer.valueOf(id)>rownum){
								int a=Integer.valueOf(id)-1;
							data[count][0]=Integer.toString(a);
							
							
							 String str1=Integer.toString(a);
							
							stmt.executeUpdate("update concert set id="+str1+"  where id ="+id+"");}
							else{data[count][0]=id;}
							
							data[count][1]=name;
							data[count][2]=tickets;
							
							count++;
						}
						
						
					}
					catch (Exception e1)
					{
					e1.printStackTrace();
					}
					finally
					{
						try { if (rs != null) rs.close(); } catch (SQLException e1) {
							e1.printStackTrace(); }
						try { if (stmt != null) stmt.close(); } catch (SQLException e1) {
							e1.printStackTrace(); }
						try { if (conn != null) conn.close(); } catch (SQLException e1) {
							e1.printStackTrace(); }
						
					}
				 
				JOptionPane.showMessageDialog(new JFrame(),"Deleting", "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
				
				data[count][0]=null;
				data[count][1]=null;
				data[count][2]=null;
				table_1.repaint();
				rownum=0;
				addId=count;
			
			}
		});
		
		btnNewButton.setBounds(390, 229, 89, 23);
		contentPane.add(btnNewButton);
		
		 String[] columns = new String[] {
		            "Id", "Name", "Last Name"
		        };
				
		        table_1 = new JTable(data,columns){
		        	public boolean isCellEditable(int row, int column)
		    	    {
		    	      return false;//This causes all cells to be not editable
		    	    }
		        };
				table_1.setBounds(10, 96, 410, 78);
				contentPane.add(table_1);
				
				JLabel lblConcerts = new JLabel("Concerts");
				lblConcerts.setBounds(193, 33, 67, 14);
				contentPane.add(lblConcerts);
				
				JButton btnLogout = new JButton("Log Out");
				btnLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						Login frame3 = new Login();
						frame3.setVisible(true);
						frame3.setTitle("Login");
						
					}
				});
				btnLogout.setBounds(390, 263, 89, 23);
				contentPane.add(btnLogout);
				
				 
				    
				    textField = new JTextField();
				    textField.setBounds(238, 230, 86, 20);
				    contentPane.add(textField);
				    textField.setColumns(10);
				    
				    JLabel lblEnterId = new JLabel("Enter Id:");
				    lblEnterId.setBounds(193, 233, 46, 14);
				    contentPane.add(lblEnterId);
				    
				    JLabel label = new JLabel("--->");
				    label.setBounds(334, 233, 46, 14);
				    contentPane.add(label);
				    
				    JButton btnAdd = new JButton("Add");
				    btnAdd.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent e) {
				    		
				    		
				    		strname=textField_1.getText();
				    		addTicket=Integer.valueOf(textField_2.getText());
				    		try
						   {
				    		Class.forName("com.mysql.jdbc.Driver").newInstance();
							String connectionUrl = "jdbc:mysql://127.0.0.1:3306/test";
							String connectionUser = "root";
							String connectionPassword = "blue";
							conn = DriverManager.getConnection(connectionUrl, connectionUser,
							connectionPassword);
							stmt = conn.createStatement();
							stmt.executeUpdate("insert into concert values("+Integer.toString(addId+1)+",'"+strname+"',"+Integer.toString(addTicket)+");");
				    		
						   
				    		
				    		rs = stmt.executeQuery("SELECT * FROM concert");
							addId++;
							count=0;
							while (rs.next())
								while (rs.next())
								{
										id = rs.getString("id");
										name = rs.getString("name");
										tickets = rs.getString("tickets");
										
										
										data[count][0]=id;
										data[count][1]=name;
										data[count][2]=tickets;
										
										count++;
								}
							}
								catch (Exception e1)
								{
								e1.printStackTrace();
								}
								finally
								{
									try { if (rs != null) rs.close(); } catch (SQLException e1) {
										e1.printStackTrace(); }
									try { if (stmt != null) stmt.close(); } catch (SQLException e1) {
										e1.printStackTrace(); }
									try { if (conn != null) conn.close(); } catch (SQLException e1) {
										e1.printStackTrace(); }
									
								}
				    		table_1.repaint();
				    		
				    	}
				    });
				    btnAdd.setBounds(390, 195, 89, 23);
				    contentPane.add(btnAdd);
				    
				    textField_1 = new JTextField();
				    textField_1.setBounds(88, 196, 86, 20);
				    contentPane.add(textField_1);
				    textField_1.setColumns(10);
				    
				    textField_2 = new JTextField();
				    textField_2.setBounds(238, 196, 86, 20);
				    contentPane.add(textField_2);
				    textField_2.setColumns(10);
				    
				    JLabel label_1 = new JLabel("--->");
				    label_1.setBounds(334, 199, 46, 14);
				    contentPane.add(label_1);
				    
				    JLabel lblBandName = new JLabel("Band Name:");
				    lblBandName.setBounds(21, 199, 68, 14);
				    contentPane.add(lblBandName);
				    
				    JLabel lblTickets = new JLabel("Tickets:");
				    lblTickets.setBounds(193, 199, 46, 14);
				    contentPane.add(lblTickets);
				    
				    JButton btnRefresh = new JButton("Refresh");
				    btnRefresh.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent arg0) {
				    		
				    		
				    		{try
				    		{
				    			Class.forName("com.mysql.jdbc.Driver").newInstance();
				    			String connectionUrl = "jdbc:mysql://127.0.0.1:3306/test";
				    			String connectionUser = "root";
				    			String connectionPassword = "blue";
				    			conn = DriverManager.getConnection(connectionUrl, connectionUser,
				    			connectionPassword);
				    			stmt = conn.createStatement();
				    			rs = stmt.executeQuery("SELECT * FROM concert");
				    			count=0;
				    			while (rs.next())
				    			{
				    					id = rs.getString("id");
				    					name = rs.getString("name");
				    					tickets = rs.getString("tickets");
				    					
				    					
				    					data[count][0]=id;
				    					data[count][1]=name;
				    					data[count][2]=tickets;
				    					ticketCount[count]=Integer.valueOf(tickets);
				    					count++;
				    			}
				    		}
				    		catch (Exception e)
				    		{
				    		e.printStackTrace();
				    		}
				    		finally
				    		{
				    			try { if (rs != null) rs.close(); } catch (SQLException e) {
				    				e.printStackTrace(); }
				    			try { if (stmt != null) stmt.close(); } catch (SQLException e) {
				    				e.printStackTrace(); }
				    			try { if (conn != null) conn.close(); } catch (SQLException e) {
				    				e.printStackTrace(); }
				    		}
				    		table_1.repaint();
				    		
				    	}
				    	}
				    });
				    btnRefresh.setBounds(235, 263, 89, 23);
				    contentPane.add(btnRefresh);
	}
	
	
}
