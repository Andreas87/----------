import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	

	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(95, 69, 63, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(168, 66, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(168, 128, 86, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlobalVar.user=textField.getText();
				GlobalVar.pass=textField_1.getText();
				
				if(GlobalVar.user.equals(GlobalVar.user1)&&GlobalVar.pass.equals(GlobalVar.pass1)){
								JOptionPane.showMessageDialog(new JFrame(),"Correct!", "Dialog",
						        JOptionPane.INFORMATION_MESSAGE);
								
								setVisible(false);
								MainMenuAd frame2 = new MainMenuAd();
								frame2.setVisible(true);
								frame2.setTitle("Administrator Mode");
					}
				else if(GlobalVar.user.equals(GlobalVar.user2)&&GlobalVar.pass.equals(GlobalVar.pass2)){
								JOptionPane.showMessageDialog(new JFrame(),"Correct!", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
								
								setVisible(false);
								MainMenu frame1 = new MainMenu();
								frame1.setVisible(true);
								frame1.setTitle("Main Menu");
					
							
								
				}
				
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Wrong!", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
			   }
		}
		});
		
		contentPane.add(btnLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(95, 100, 63, 14);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(168, 97, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(168, 162, 86, 23);
		contentPane.add(btnNewButton);
	}
	
	
}
