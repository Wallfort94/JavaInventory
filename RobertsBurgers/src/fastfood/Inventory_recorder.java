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


import net.ucanaccess.converters.TypesMap.AccessType;
import net.ucanaccess.ext.FunctionType;
import net.ucanaccess.jdbc.*;
import net.ucanaccess.jdbc.UcanaccessDriver;
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
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		getDBData() ;
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
		panel_2.setBounds(10, 10, 951, 241);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 13));
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Decription:");
		lblNewLabel.setBounds(22, 87, 158, 24);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDayReceived = new JLabel("Day received");
		lblDayReceived.setBounds(695, 87, 115, 24);
		panel_2.add(lblDayReceived);
		lblDayReceived.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblCostPerLb = new JLabel("Cost per lb");
		lblCostPerLb.setBounds(22, 113, 183, 24);
		panel_2.add(lblCostPerLb);
		lblCostPerLb.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblWastelb = new JLabel("Waste Net (lb)");
		lblWastelb.setBounds(22, 178, 158, 24);
		panel_2.add(lblWastelb);
		lblWastelb.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblWeightlb = new JLabel("Weight per item(lb)");
		lblWeightlb.setBounds(22, 133, 168, 24);
		panel_2.add(lblWeightlb);
		lblWeightlb.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNetTotal = new JLabel("Net total (lb) per item");
		lblNetTotal.setBounds(618, 167, 192, 47);
		panel_2.add(lblNetTotal);
		lblNetTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblEstimateDateOf = new JLabel("Estimate date of expiration");
		lblEstimateDateOf.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEstimateDateOf.setBounds(566, 133, 244, 24);
		panel_2.add(lblEstimateDateOf);
		
		JComboBox CmboItem = new JComboBox();
		CmboItem.setModel(new DefaultComboBoxModel(new String[] {"Items", "Burgers", "Onion", "Lettuce", "Tomatoe", "Bacon", "ketchup", "Mustard", "Mayonaise"}));
		CmboItem.setBounds(190, 91, 121, 21);
		panel_2.add(CmboItem);
		
		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblItemCode.setBounds(340, 87, 115, 24);
		panel_2.add(lblItemCode);
		
		JTextArea txWasteNetItem = new JTextArea();
		txWasteNetItem.setBounds(192, 180, 105, 36);
		panel_2.add(txWasteNetItem);
		
		JTextArea txWeightperItem = new JTextArea();
		txWeightperItem.setBounds(192, 135, 105, 36);
		panel_2.add(txWeightperItem);
		
		JTextArea txExpirationdate = new JTextArea();
		txExpirationdate.setBounds(820, 135, 105, 36);
		panel_2.add(txExpirationdate);
		
		JTextArea txItemCode = new JTextArea();
		txItemCode.setBounds(441, 89, 105, 36);
		panel_2.add(txItemCode);
		
		JTextArea txNetTotalperItem = new JTextArea();
		txNetTotalperItem.setBounds(820, 180, 105, 36);
		panel_2.add(txNetTotalperItem);
		
		JTextArea txDateReceived = new JTextArea();
		txDateReceived.setBounds(820, 89, 105, 36);
		panel_2.add(txDateReceived);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(10, 250, 475, 509);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 13));
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblWasteMeatlb = new JLabel("Waste Meat (lb)");
		lblWasteMeatlb.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWasteMeatlb.setBounds(42, 23, 149, 24);
		panel_1_1.add(lblWasteMeatlb);
		
		JLabel lblWasteProducelb = new JLabel("Waste produce (lb)");
		lblWasteProducelb.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWasteProducelb.setBounds(36, 75, 165, 24);
		panel_1_1.add(lblWasteProducelb);
		
		JTextArea txWasteMeat = new JTextArea();
		txWasteMeat.setBounds(211, 23, 149, 36);
		panel_1_1.add(txWasteMeat);
		
		JTextArea txWasteProduce = new JTextArea();
		txWasteProduce.setBounds(211, 75, 149, 36);
		panel_1_1.add(txWasteProduce);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(470, 250, 475, 506);
		panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0), 13));
		contentPane.add(panel_3_1);
		panel_3_1.setLayout(null);
		
		JLabel lblProductUsedFor = new JLabel("Product used for sale (lb)");
		lblProductUsedFor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductUsedFor.setBounds(22, 32, 240, 47);
		panel_3_1.add(lblProductUsedFor);
		//Submit Button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Submit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to Submit", "Inventory System",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
						System.out.println("Inventory Submitted");
				}
				
			}
		});
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnSubmit.setBounds(22, 436, 189, 47);
		panel_3_1.add(btnSubmit);
		
		JLabel lblAmountOfBurgers = new JLabel("Amount of Burgers sold(unit)");
		lblAmountOfBurgers.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAmountOfBurgers.setBounds(22, 100, 240, 47);
		panel_3_1.add(lblAmountOfBurgers);
		
		JLabel lblPricePerBurger = new JLabel("Price per burger");
		lblPricePerBurger.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPricePerBurger.setBounds(22, 146, 240, 47);
		panel_3_1.add(lblPricePerBurger);
		
		JTextArea txProductavailable = new JTextArea();
		txProductavailable.setBounds(294, 43, 105, 36);
		panel_3_1.add(txProductavailable);
		
		JTextArea txBurgersold = new JTextArea();
		txBurgersold.setBounds(294, 113, 105, 36);
		panel_3_1.add(txBurgersold);
		
		JTextArea txBurgerPrice = new JTextArea();
		txBurgerPrice.setBounds(294, 159, 105, 36);
		panel_3_1.add(txBurgerPrice);
		
		JLabel lblInventoryDoneBy = new JLabel("Inventory done by:");
		lblInventoryDoneBy.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInventoryDoneBy.setBounds(22, 275, 189, 24);
		panel_3_1.add(lblInventoryDoneBy);
		
		JLabel lblInventoryDateBy = new JLabel("Inventory date by:");
		lblInventoryDateBy.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInventoryDateBy.setBounds(22, 361, 189, 24);
		panel_3_1.add(lblInventoryDateBy);
		
		JTextArea txInventoryBy = new JTextArea();
		txInventoryBy.setBounds(201, 277, 149, 36);
		panel_3_1.add(txInventoryBy);
		
		JTextArea txInventoryDate = new JTextArea();
		txInventoryDate.setBounds(201, 361, 149, 36);
		panel_3_1.add(txInventoryDate);
		//Exit Button
		JButton btnExit = new JButton("Exit");
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
		btnExit.setBounds(244, 436, 189, 47);
		panel_3_1.add(btnExit);
	}
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
				Logr.info("I'm from a different class");	
		}	

	}

}
