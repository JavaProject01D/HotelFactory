package group5.hotel.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.*;
import dw317.hotel.data.interfaces.ReservationDAO;
import group5.hotel.business.*;
import group5.hotel.data.ObjectSerializedList;
import group5.hotel.data.ReservationListDB;

import java.awt.event.*;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class GUIViewController extends JFrame implements Observer{

	private JPanel contentPane;
	private JPanel resultPanel;
	private JTextArea resultText;
	private JPanel getEmailPanel;
	private JTextField email;
	private Hotel model;
	ReservationDAO reservations;

	public GUIViewController(Hotel model) {
		
		this.model = model;
		this.model.addObserver(this);
		
		setResizable(false);
		setTitle("Dawson Hotel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(getTitlePanel(), BorderLayout.NORTH);
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);

		this.resultPanel.setVisible(true);
		this.setVisible(true);
		
		reservations  = new ReservationListDB(new ObjectSerializedList	("dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.ser",
				 "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\reservations.ser", 
				 "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.ser"));
	}

	private JPanel getTitlePanel() {
		JPanel titlePanel = new JPanel();
		JLabel lblDawsonHotelInfo = new JLabel("Dawson Hotel - Information Desk");
		lblDawsonHotelInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		titlePanel.add(lblDawsonHotelInfo);
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return titlePanel;
	}
	/**
	 * This private method is the listener to the "Customer Info" button. When it is clicked, it calls the methods 
	 * which will display the desired information. 
	 * @author Zahraa
	 *
	 */

	private class CustomerInfoButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			try {
				String inputedEmail = email.getText();
				model.findCustomer(inputedEmail);	
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog
				(GUIViewController.this, "Invalid email!",
						"Alert", JOptionPane.ERROR_MESSAGE);
			} catch (NonExistingCustomerException nec) {			
				JOptionPane.showMessageDialog
				(GUIViewController.this, nec.getMessage(),
						"Alert", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * This private method is the listener to the "Reservation Info" button. When it is clicked, it calls the methods 
	 * which will display the desired information. 
	 * @author Zahraa
	 *
	 */
	
	private class ReservationInfoButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			try {
				String inputedEmail = email.getText();
				Customer cust = model.findCustomer(inputedEmail);	
				model.findReservations(cust);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog
				(GUIViewController.this, "Invalid email!",
						"Alert", JOptionPane.ERROR_MESSAGE);
			} catch (NonExistingCustomerException nec) {			
				JOptionPane.showMessageDialog
				(GUIViewController.this, nec.getMessage(),
						"Alert", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * 	/**
	 * This private method is the listener to the "Exit" button. When it is clicked, it closes the application.
	 * @author Zahraa Horeibi
	 *
	 */

	private class ExitButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
	
	private JPanel getCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel dataEntry = new JPanel();
		centerPanel.add(dataEntry);

		getEmailPanel = new JPanel();
		dataEntry.add(getEmailPanel);
		getEmailPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblEmail = new JLabel("Enter email address: ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		getEmailPanel.add(lblEmail, BorderLayout.WEST);

		email = new JTextField();
		getEmailPanel.add(email);
		email.setColumns(25);

		dataEntry.add(getButtonPanel());

		resultPanel = new JPanel();
		centerPanel.add(resultPanel);

		resultText = new JTextArea();
		resultPanel.add(resultText);

		return centerPanel;
	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton custInfo = new JButton("Customer Info");
		buttonPanel.add(custInfo);
		custInfo.addActionListener(new CustomerInfoButton());
		
		JButton resInfo = new JButton("Reservation Info");
		buttonPanel.add(resInfo);
		resInfo.addActionListener(new ReservationInfoButton());

		return buttonPanel;
	}

	private JPanel getBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		bottomPanel.add(panel);

		JButton exit = new JButton("Exit");
		panel.add(exit);
		exit.addActionListener(new ExitButton());
		return bottomPanel;
	}

	/**
	 * This method is an update method which is responsible to display the desired information that the customer asks for. 
	 * It notifies all observers every time there is a modification.
	 * @author Zahraa Horeibi
	 * @param o 
	 * @param arg
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
	
		String displayCard;
		String displayData = null;
		
		if (arg instanceof Customer) {
			Customer cust = (Customer) arg;
			if (!cust.getCreditCard().isPresent()) 
				displayCard = " None.";
			 	else {
			 		displayCard = cust.getCreditCard().get().getType() 
			 							+ " " + cust.getCreditCard().get().getNumber();
			 	}
			displayData = ("Customer Information: \nName: " + cust.getName().getFullName() + " at " + cust.getEmail() + 
							"\nCredit card on file: " + displayCard);
			resultText.setText(displayData);
			
			o.notifyObservers();
		}
	
		if(arg instanceof ArrayList<?>){
			ArrayList<Reservation> list = (ArrayList<Reservation>) arg;
			
			if(list.size() != 0){
				displayData = "Reservation detail: ";
				displayData += ("\nName: " + list.get(0).getCustomer().getName().getFullName() +
							" at " + list.get(0).getCustomer().getEmail());
				displayData += "\nReservation(s): ";
				
				for(Reservation item : list){
					displayData += ("\nRoom: " + item.getRoom().getRoomNumber() 
									+ "\nCheck in date: " + item.getCheckInDate() 
									+ " for " + ChronoUnit.DAYS.between(item.getCheckInDate(), item.getCheckOutDate())
									+ " days left.");
										
				}
				
				resultText.setText(displayData);
				
				
			}
			o.notifyObservers();
		}
		
		
	}

}
