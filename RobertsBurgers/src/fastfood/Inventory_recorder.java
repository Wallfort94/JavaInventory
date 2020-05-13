package fastfood;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.sql.*; 
import com.healthmarketscience.jackcess.*;
import com.healthmarketscience.jackcess.util.ImportUtil;

import net.proteanit.sql.DbUtils;
import net.ucanaccess.converters.TypesMap.AccessType;
import net.ucanaccess.ext.FunctionType;
import net.ucanaccess.jdbc.*;
import net.ucanaccess.jdbc.UcanaccessDriver;
import oracle.net.aso.s;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Inventory_recorder extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextArea txItemCode ;
	private JTextArea txWasteNetItem ;
	private JTextArea txWeightperItem ;
	private JTextArea txProductavailable ;
	private JTextArea txExpirationdate ;
	private JTextArea txNetTotalperItem ;
	private JTextArea txDateReceived ;
	private JTextArea txWasteMeat ;
	private JTextArea txInventoryBy ;
	private JTextArea txInventoryDate ;
	private JTextArea txBurgerPrice ;
	private JTextArea txBurgerSold ;
	private JTextArea txWasteProduce ;
	private JTextField txtQuantity;
	private JTable table;
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//getDBData() ;
		Logger();
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory_recorder frame = new Inventory_recorder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inventory_recorder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1450, 800);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 951, 78);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 13));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Rob's Burgers Inventory System");
		lblNewLabel_1.setBounds(78, 10, 814, 58);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 54));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 10, 951, 557);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 13));
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Decription:");
		lblNewLabel.setBounds(22, 87, 158, 24);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNetTotal = new JLabel("Quantity( per unit)");
		lblNetTotal.setBounds(331, 76, 192, 47);
		panel_2.add(lblNetTotal);
		lblNetTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		final JComboBox CmboItem = new JComboBox();
		CmboItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//String Itemchoice = (String) CmboItem.getSelectedItem();
			//System.out.println(Itemchoice);
			}
		});
		CmboItem.setModel(new DefaultComboBoxModel(new String[] {"Items", "Burgers", "Lettuce", "Onion", "Mustard", "ketchup", "buns", "Bacon", "fries", "peanut oil", "Tomatoe"}));
		CmboItem.setBounds(190, 91, 121, 21);
		panel_2.add(CmboItem);
		
		JLabel lblInventoryDoneBy = new JLabel("Inventory done by:");
		lblInventoryDoneBy.setBounds(22, 139, 189, 24);
		panel_2.add(lblInventoryDoneBy);
		lblInventoryDoneBy.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		final JTextArea txInventoryBy_1 = new JTextArea();
		txInventoryBy_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		txInventoryBy_1.setBounds(190, 133, 149, 36);
		panel_2.add(txInventoryBy_1);
		//Submit Button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(200, 179, 189, 47);
		panel_2.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Submit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to Submit", "Inventory System",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
						
						
				}
				
				  String user      = "root";
				  String password  = "Lady#2019";
				  String databaseName = "userinformation";
				  String url       = "jdbc:mysql://localhost:3306/"+databaseName;
				  Connection connection = null;
				  try {
			            // The newInstance() call is a work around for some
			            // broken Java implementations
					  int enumName=CmboItem.getSelectedIndex();
					  String InventoryQuantity= txtQuantity.getText();
					  String InventoryName = txInventoryBy_1.getText();
					  
			            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			    		connection = DriverManager.getConnection(url,user,password);
						 PreparedStatement ps = connection.prepareStatement("UPDATE `robsinventory`.`inventory` SET `Quantity` = '"+InventoryQuantity+"', `Inventorydoneby` = '"+InventoryName+"' WHERE (`ID` ='"+enumName+"');");
						 
						 int status = ps.executeUpdate();
						  if (status!= 0 ) {
							  System.out.println(" Database was connected");
							  System.out.println(" Record was Inserted");
						  }
			        }catch (Exception ex) {
			            // handle the error
			        }
			 
				/*String Amount = txtQuantity.getText();
				String Itemchoice = (String) CmboItem.getSelectedItem();
				String ID = "1";
				System.out.println("Inventory Submitted");
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String url =  "jdbc:ucanaccess://C://Users//ShaneW//Documents//Rasmussen_classes//java_class//RobsDB.accdb";
				Connection con = null;
				try {
					con = DriverManager.getConnection(url);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Statement s = con.createStatement();
					String Query = " Insert into Robsburgers(ID,Item,Quantity) Values('"+ID+"','"+Itemchoice+"','"+Amount+"') ";
					s.executeUpdate(Query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				*/
				
				
			}
		});
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 24));
		//Exit Button
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(676, 179, 189, 47);
		panel_2.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Inventory System",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
						System.exit(0);
				}
				
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		txtQuantity = new JTextField();
		txtQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtQuantity.setFont(new Font("Arial", Font.PLAIN, 12));
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setText("Quantity");
		txtQuantity.setBounds(488, 90, 112, 24);
		panel_2.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JButton btnreport = new JButton("Report");
		btnreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String user      = "root";
				  String password  = "Lady#2019";
				  String databaseName = "robsinventory";
				  String url       = "jdbc:mysql://localhost:3306/"+databaseName;
				  Connection connection = null;
				  try {
			            // The newInstance() call is a work around for some
			            // broken Java implementations
					 // int enumName=CmboItem.getSelectedIndex();
					 // String InventoryQuantity= txtQuantity.getText();
					 // String InventoryName = txInventoryBy_1.getText();
					  
			            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			    		connection = DriverManager.getConnection(url,user,password);
			    		String sql = "SELECT*FROM inventory";
						 PreparedStatement ps = connection.prepareStatement(sql);
						 
						 ResultSet rs = ps.executeQuery(sql);
						 table.setModel(DbUtils.resultSetToTableModel(rs));
						 while(rs.next())
							{
							 
								//int x=1;
								
								//String name =rs.getString(x);
								//System.out.println(name);
								//x++;
								System.out.println(rs.getString(1)+ "\t\t\t" +rs.getString(2)+ "\t\t\t"+
										rs.getString(3)+"\t\t\t"+ rs.getString(4)+"\t\t\t");
								
							}
				  }catch(Exception e1) {
					  
				  }
				  
				  
			}	  
		
		});
		btnreport.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnreport.setBounds(441, 179, 189, 47);
		panel_2.add(btnreport);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 266, 548, 243);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	// Database access
	public static void getDBData() throws SQLException, ClassNotFoundException {
	      Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String url =  "jdbc:ucanaccess://C://Users//ShaneW//Documents//Rasmussen_classes//java_class//RobsDB.accdb";
		
		Connection  con = DriverManager.getConnection(url);
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("Select  * from Robsburgers");
		
			while (rs.next()) 
			{
			System.out.println(rs.getString(1)+ "\t\t\t" +rs.getString(2)+ "\t\t\t"+
			rs.getString(3)+"\t\t\t"+ rs.getString(4)+"\t\t\t"+ rs.getString(5));
			}
		}
	
	public static void Logger() {
	final Logger Logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		//Logger.getGlobal().info("Hello World");
		Test.test();
		LogManager.getLogManager().reset();
		Logr.setLevel(Level.ALL);
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.SEVERE);
		FileHandler fh;
		try {
			fh = new FileHandler("MyLogger.log");
			fh.setLevel(Level.FINE);
			Logr.addHandler(fh);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			Logr.log(Level.SEVERE, "File Logger not Working", e);
			e.printStackTrace();
		}
		
		//hi
		Logr.log(Level.SEVERE, " My first log");
		/*
		 * SEVERE
		 * WARNING
		 * INFOR
		 * CONFIG
		 * FINE
		 * FINER
		 * FINEST
		 */
	}
	public static class Test {
		private final static Logger Logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			static void test() {
				Logr.info("I'm from a different class this is a test");	
			
		}	

	}
}
