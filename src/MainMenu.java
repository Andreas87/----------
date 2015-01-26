import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JRadioButton;
import javax.swing.*;
import javax.swing.JTable;


public class MainMenu extends JFrame {

	private JPanel contentPane;
	public String id,name,tickets;
	public int count=0,rownum=-2;
	Object[][] data = new Object[100][4];
	private JTable table_1;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JRadioButton radio4;
	private JRadioButton radio5;
	
	
	public int[] ticketCount=new int[10];
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	private JButton btnRefresh;
	
	



	{	
		try
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
	}



	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tickets");
		lblNewLabel.setBounds(199, 11, 114, 53);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Print");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if (radio1.isSelected() == true) rownum=0;
				 if (radio2.isSelected() == true) rownum=1;
				 if (radio3.isSelected() == true) rownum=2;
				 if (radio4.isSelected() == true) rownum=3;
				 if (radio5.isSelected() == true) rownum=4;
				 
				 ticketCount[rownum]--;
				 String str1 = Integer.toString(ticketCount[rownum]);
				 String str2 = Integer.toString(rownum+1);
				   
					try
					{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						String connectionUrl = "jdbc:mysql://127.0.0.1:3306/test";
						String connectionUser = "root";
						String connectionPassword = "blue";
						conn = DriverManager.getConnection(connectionUrl, connectionUser,
						connectionPassword);
						stmt = conn.createStatement();
						stmt.executeUpdate("UPDATE concert SET tickets="+str1+" WHERE id="+str2+"" );
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
				 
				JOptionPane.showMessageDialog(new JFrame(),"Print!", "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
				table_1.repaint();
				rownum=0;
				
			}
		});
		
		btnNewButton.setBounds(335, 275, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		//headers for the table
        String[] columns = new String[] {
            "Id", "Name", "Last Name"
        };
		
        table_1 = new JTable(data,columns){
        	public boolean isCellEditable(int row, int column)
    	    {
    	      return false;//This causes all cells to be not editable
    	    }
        };
		table_1.setBounds(10, 96, 359, 78);
		contentPane.add(table_1);
		
		
		
		radio1 = new JRadioButton("");
		radio1.setBounds(375, 92, 109, 21);
		contentPane.add(radio1);
		
		radio2 = new JRadioButton("");
		radio2.setBounds(375, 108, 109, 23);
		contentPane.add(radio2);
		
		radio3 = new JRadioButton("");
		radio3.setBounds(375, 126, 109, 23);
		contentPane.add(radio3);
		
		radio4 = new JRadioButton("");
		radio4.setBounds(375, 147, 109, 15);
		contentPane.add(radio4);
		
		 radio5 = new JRadioButton("");
		 radio5.setBounds(375, 165, 109, 15);
		 contentPane.add(radio5);
		
		 ButtonGroup group = new ButtonGroup();
		    group.add(radio1);
		    group.add(radio2);
		    group.add(radio3);
		    group.add(radio4);
		    group.add(radio5);
		    
		    JButton btnLogOut = new JButton("Log Out");
		    btnLogOut.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		setVisible(false);
					Login frame4 = new Login();
					frame4.setVisible(true);
					frame4.setTitle("Login");
		    	}
		    });
		    btnLogOut.setBounds(335, 321, 89, 23);
		    contentPane.add(btnLogOut);
		    
		    btnRefresh = new JButton("Refresh");
		    btnRefresh.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		
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
		    	
		   
		    	}
		    });
		    btnRefresh.setBounds(236, 275, 89, 23);
		    contentPane.add(btnRefresh);
		  
		    
		    
		    
		    
		   
	}
	
}
