package tripadvisor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WebsiteGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static JTextPane textPane;
	
	
	private static String address;
	private static String date;
	private static String content;
	private static boolean store, retrieve;
	
	WebsiteCrawler webCrawler = new WebsiteCrawler();
	WaybackMachine m = new WaybackMachine();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebsiteGUI window = new WebsiteGUI();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WebsiteGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 739, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 30, 479, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(499, 30, 214, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 136, 703, 283);
		frame.getContentPane().add(textPane);
		textPane.setEditable(false);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 11, 141, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(499, 11, 46, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblWebsite = new JLabel("Website:");
		lblWebsite.setBounds(10, 111, 129, 14);
		frame.getContentPane().add(lblWebsite);
		
		JButton btnStore = new JButton("Store");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebsiteGUI.store = true;
				WebsiteGUI.address = textField.getText();
				WebsiteGUI.date = textField_1.getText();
				
				m.store(WebsiteGUI.address, WebsiteGUI.date, webCrawler);
				
				textPane.setText("added");
			}
		});
		btnStore.setBounds(10, 77, 89, 23);
		frame.getContentPane().add(btnStore);
		
		JButton btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebsiteGUI.retrieve = true;
				WebsiteGUI.address = textField.getText();
				WebsiteGUI.date = textField_1.getText();
				
				WebsiteGUI.content = m.retrieve(WebsiteGUI.address, WebsiteGUI.date);
				textPane.setText(content);
			}
		});
		btnRetrieve.setBounds(109, 77, 89, 23);
		frame.getContentPane().add(btnRetrieve);
		
		
		
	}
}
