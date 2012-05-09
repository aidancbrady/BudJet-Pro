package org.orecraft.budjet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI
{
	//Declarations
	private String firstName, lastName;
	private double balance;
	private int depositAmount;
	private int withdrawAmount;
	
	private NumberFormat fmt = NumberFormat.getCurrencyInstance();
	
	private JFrame frame;
	
	private JPanel panel, balancePanel, transactionPanel, editNamesPanel, editNamesFull, 
		viewStatsFull, editNamesTitlePanel, viewStatsTitlePanel, editBothNames, viewBothStats,
		balancePanelTopTop, balancePanelTop, balancePanelBottom, confirmNameEditPanel, depositHere,
		depositPanel, editFirstName, editLastName, firstNameInputPanel, lastNameInputPanel,
		resetStatsButtonPanel, viewDepositsCompleted, withdrawHere, withdrawPanel, viewWithdrawsCompleted;
	private Account acct = new Account("", "", 0.00, 0, 0);
	private JLabel welcomeUser, userName, balanceLabel, currentBalance, editNamesTitle, 
		withdrawLabel, depositLabel, viewStatsTitle, depositsCompletedLabel, withdrawsCompletedLabel;
	private JTextField withdrawInput, editFirstNameField, editLastNameField, depositInput;
	private JButton resetButton, confirmWithdraw, confirmDeposit, confirmNameEditButton,
		resetStatsButton;
	
	
	public GUI()
	{
		getProperties();
		//Set up the main window.
		frame = new JFrame("BudJet Pro");
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Window's main frame. All other frames are nested in panel's frame.
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setBackground(Color.GRAY);
		
		//First main panel, displays user's name and current balance.
		balancePanel = new JPanel();
		balancePanel.setLayout(new GridLayout(3, 1));
		balancePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		balancePanel.setBackground(Color.LIGHT_GRAY);
		panel.add(balancePanel);
		
		//Welcome panel
		balancePanelTopTop = new JPanel();
		balancePanel.add(balancePanelTopTop);
		
		//Current balance panel
		balancePanelTop = new JPanel();
		balancePanel.add(balancePanelTop);
		
		//Reset balance panel
		balancePanelBottom = new JPanel();
		balancePanel.add(balancePanelBottom);
		
		//Extra junk for setting up labels and panels
		welcomeUser = new JLabel("Welcome,");
		userName = new JLabel(firstName + " " + lastName);
		userName.setFont(new Font("Arial", Font.BOLD, 14));
		balanceLabel = new JLabel("Current Balance: ");
		currentBalance = new JLabel(fmt.format(balance));
		currentBalance.setFont(new Font("Arial", Font.BOLD, 14));
		balancePanelTopTop.add(welcomeUser);
		balancePanelTopTop.add(userName);
		balancePanelTop.add(balanceLabel);
		balancePanelTop.add(currentBalance);
		
		//Second main panel, lets you withdraw and deposit money
		transactionPanel = new JPanel();
		transactionPanel.setLayout(new GridLayout(1, 2));
		panel.add(transactionPanel);
		
		//Third main panel, lets you reset your name and view stats
		editNamesPanel = new JPanel();
		editNamesPanel.setLayout(new GridLayout(1, 2));
		panel.add(editNamesPanel);
		
		//Third panel's name editing panel
		editNamesFull = new JPanel();
		editNamesFull.setLayout(new GridLayout(3, 1));
		editNamesFull.setBorder(BorderFactory.createRaisedBevelBorder());
		editNamesPanel.add(editNamesFull);
		
		//Third panel's stat viewing panel
		viewStatsFull = new JPanel();
		viewStatsFull.setLayout(new GridLayout(3, 1));
		viewStatsFull.setBorder(BorderFactory.createRaisedBevelBorder());
		editNamesPanel.add(viewStatsFull);
		
		//Edit names title panel
		editNamesTitlePanel = new JPanel();
		editNamesTitle = new JLabel("Edit Name");
		editNamesTitle.setFont(new Font("Arial", Font.BOLD, 14));
		editNamesTitlePanel.add(editNamesTitle);
		editNamesFull.add(editNamesTitlePanel);
		
		//View stats title panel
		viewStatsTitlePanel = new JPanel();
		viewStatsTitle = new JLabel("Statistics");
		viewStatsTitle.setFont(new Font("Arial", Font.BOLD, 14));
		viewStatsTitlePanel.add(viewStatsTitle);
		viewStatsFull.add(viewStatsTitlePanel);
		
		//Nested name editing panel
		editBothNames = new JPanel();
		editBothNames.setLayout(new GridLayout(1, 2));
		editNamesFull.add(editBothNames);
		
		//Nested stat viewing panel
		viewBothStats = new JPanel();
		viewBothStats.setLayout(new GridLayout(1, 2));
		viewStatsFull.add(viewBothStats);
		
		//First name editing panel
		editFirstName = new JPanel();
		editFirstName.setBorder(BorderFactory.createTitledBorder("First Name:"));
		editBothNames.add(editFirstName);
		
		//Deposits completed viewing panel
		viewDepositsCompleted = new JPanel();
		viewDepositsCompleted.setBorder(BorderFactory.createTitledBorder("Deposits Completed:"));
		viewBothStats.add(viewDepositsCompleted);
		
		//Last name editing panel
		editLastName = new JPanel();
		editLastName.setBorder(BorderFactory.createTitledBorder("Last Name:"));
		editBothNames.add(editLastName);
		
		//Withdraws completed viewing panel
		viewWithdrawsCompleted = new JPanel();
		viewWithdrawsCompleted.setBorder(BorderFactory.createTitledBorder("Withdraws Completed"));
		viewBothStats.add(viewWithdrawsCompleted);
		
		//Set up panels and text for name editor
		editFirstNameField = new JTextField(12);
		editLastNameField = new JTextField(12);
		firstNameInputPanel = new JPanel();
		lastNameInputPanel = new JPanel();
		editFirstName.add(firstNameInputPanel);
		editLastName.add(lastNameInputPanel);
		firstNameInputPanel.add(editFirstNameField);
		lastNameInputPanel.add(editLastNameField);
		
		//Set up panels and text for stat viewer
		depositsCompletedLabel = new JLabel("" + depositAmount);
		withdrawsCompletedLabel = new JLabel("" + withdrawAmount);
		viewDepositsCompleted.add(depositsCompletedLabel);
		viewWithdrawsCompleted.add(withdrawsCompletedLabel);
		
		//Button panel for name editor
		confirmNameEditPanel = new JPanel();
		confirmNameEditButton = new JButton("Confirm");
		confirmNameEditButton.addActionListener(new NameListener());
		confirmNameEditPanel.add(confirmNameEditButton);
		editNamesFull.add(confirmNameEditPanel);
		
		//Button panel for stat viewer
		resetStatsButtonPanel = new JPanel();
		resetStatsButton = new JButton("Reset");
		resetStatsButton.addActionListener(new ResetStatsListener());
		resetStatsButtonPanel.add(resetStatsButton);
		viewStatsFull.add(resetStatsButtonPanel);
		
		//Main deposit panel for transaction panel
		depositPanel = new JPanel();
		depositPanel.setLayout(new GridLayout(2, 1));
		depositPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		transactionPanel.add(depositPanel);
		
		//Main withdraw panel for transaction panel
		withdrawPanel = new JPanel();
		withdrawPanel.setLayout(new GridLayout(2, 1));
		withdrawPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		transactionPanel.add(withdrawPanel);
		
		//Nested panels for simpler depositing+withdrawing panels
		depositHere = new JPanel();
		depositPanel.add(depositHere);
		withdrawHere = new JPanel();
		withdrawPanel.add(withdrawHere);
		
		//Set up labels and text for transactions
		depositLabel = new JLabel("Deposit Money:");	
		withdrawLabel = new JLabel("Withdraw Money:");
		depositHere.add(depositLabel);
		withdrawHere.add(withdrawLabel);
		depositInput = new JTextField(8);
		withdrawInput = new JTextField(8);
		withdrawHere.add(withdrawInput);
		depositHere.add(depositInput);
		
		//Set up buttons for transactions and resetting balance
		confirmDeposit = new JButton("Deposit");
		confirmDeposit.addActionListener(new DepositListener());
		confirmWithdraw = new JButton("Withdraw");
		confirmWithdraw.addActionListener(new WithdrawListener());
		resetButton = new JButton("Reset Balance");
		resetButton.addActionListener(new ResetListener());
		depositPanel.add(confirmDeposit);
		withdrawPanel.add(confirmWithdraw);
		balancePanelBottom.add(resetButton);
		
		//Set it all up!
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private class DepositListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			try {
				NumberFormat fmt = NumberFormat.getCurrencyInstance();
				String text = depositInput.getText().replace("$", "").replace(",", "");
				double deposit = Double.parseDouble(text);
				if(deposit < 0)
				{
					JOptionPane.showMessageDialog(null, "You can't deposit a negative amount.");
					depositInput.setText("");
				}
				else {
					acct.deposit(deposit);
					new PropertyLoader().refreshProperties(acct.getFirstName(), acct.getLastName(), acct.getBalance(), acct.getTotalDeposits(), acct.getTotalWithdraws());
					currentBalance.setText("" + fmt.format(acct.getBalance()));
					depositInput.setText("");
					depositsCompletedLabel.setText("" + acct.getTotalDeposits());
				}
			} catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "The text field includes unsupported characters. Remove them and try again.");
				depositInput.setText("");
			}
		}
		
	}
	
	private class WithdrawListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			try {
				NumberFormat fmt = NumberFormat.getCurrencyInstance();
				String text = withdrawInput.getText().replace("$", "").replace(",", "");
				double withdrawal = Double.parseDouble(text);
				if((acct.getBalance() - withdrawal) < 0)
				{
					JOptionPane.showMessageDialog(null, "This withdrawal would result in your balance being negative.");
					withdrawInput.setText("");
				}
				else if(withdrawal < 0)
				{
					JOptionPane.showMessageDialog(null, "You can't withdraw a negative amount.");
					withdrawInput.setText("");
				}
				else {
					acct.withdraw(Double.parseDouble(text));
					new PropertyLoader().refreshProperties(acct.getFirstName(), acct.getLastName(), acct.getBalance(), acct.getTotalDeposits(), acct.getTotalWithdraws());
					currentBalance.setText("" + fmt.format(acct.getBalance()));
					withdrawInput.setText("");
					withdrawsCompletedLabel.setText("" + acct.getTotalWithdraws());
				}
			} catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "The text field includes unsupported characters. Remove them and try again.");
				withdrawInput.setText("");
			}
		}
	}
	
	private class NameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			String first = editFirstNameField.getText();
			String last = editLastNameField.getText();
			if(first.equals("") && last.equals("")) {
				JOptionPane.showMessageDialog(null, "You must enter a name.");
			}
			else if(first.equals("")) {
				first = acct.getFirstName();
				acct.editName(first, last);
				new PropertyLoader().refreshProperties(first, last, acct.getBalance(), acct.getTotalDeposits(), acct.getTotalWithdraws());
				userName.setText(first + " " + last);
				editFirstNameField.setText("");
				editLastNameField.setText("");
				JOptionPane.showMessageDialog(null, "Your new name has been set, " + first + " " + last + ".");
			}
			else if(last.equals("")) {
				last = acct.getLastName();
				acct.editName(first, last);
				new PropertyLoader().refreshProperties(first, last, acct.getBalance(), acct.getTotalDeposits(), acct.getTotalWithdraws());
				userName.setText(first + " " + last);
				editFirstNameField.setText("");
				editLastNameField.setText("");
				JOptionPane.showMessageDialog(null, "Your new name has been set, " + first + " " + last + ".");
			}
			else {
				acct.editName(first, last);
				new PropertyLoader().refreshProperties(first, last, acct.getBalance(), acct.getTotalDeposits(), acct.getTotalWithdraws());
				userName.setText(first + " " + last);
				editFirstNameField.setText("");
				editLastNameField.setText("");
				JOptionPane.showMessageDialog(null, "Your new name has been set, " + first + " " + last + ".");
			}
		}
	}
	
	
	private class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			NumberFormat fmt = NumberFormat.getCurrencyInstance();
			acct.reset();
			new PropertyLoader().refreshProperties(acct.getFirstName(), acct.getLastName(), acct.getBalance(), acct.getTotalDeposits(), acct.getTotalWithdraws());
			currentBalance.setText(fmt.format(acct.getBalance()));
			JOptionPane.showMessageDialog(null, "Your balance has been reset.");
		}
	}
	
	
	private class ResetStatsListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			acct.resetStats();
			new PropertyLoader().refreshProperties(acct.getFirstName(), acct.getLastName(), acct.getBalance(), acct.getTotalDeposits(), acct.getTotalWithdraws());
			int depositAmount = acct.getTotalDeposits();
			int withdrawAmount = acct.getTotalWithdraws();
			depositsCompletedLabel.setText("" + depositAmount);
			withdrawsCompletedLabel.setText("" + withdrawAmount);
			JOptionPane.showMessageDialog(null, "Statistics have been reset.");
		}
	}
	
	public void getProperties()
	{
		Properties properties = new Properties();
		try {
			File file = new File("BudJet.txt");
			if(file.exists())
			{
				properties.load(new FileInputStream("BudJet.txt"));
				firstName = properties.getProperty("firstName");
				lastName = properties.getProperty("lastName");
				balance = Double.parseDouble(properties.getProperty("balance"));
				depositAmount = Integer.parseInt(properties.getProperty("depositAmount"));
				withdrawAmount = Integer.parseInt(properties.getProperty("withdrawAmount"));
				acct.editName(firstName, lastName);
				acct.setBalance(balance);
				acct.setDepositAmount(depositAmount);
				acct.setWithdrawAmount(withdrawAmount);
				System.out.println("Data loaded.");
			}
			else {
				file.createNewFile();
				FileOutputStream fileoutputstream = new FileOutputStream(file);
				properties.setProperty("firstName", "User");
				properties.setProperty("lastName", "");
				properties.setProperty("balance", Double.toString(0.0));
				properties.setProperty("depositAmount", Integer.toString(0));
				properties.setProperty("withdrawAmount", Integer.toString(0));
				properties.store(fileoutputstream, "Main BudJet data.");
				fileoutputstream.close();
				acct.editName("User", "");
				firstName = "User";
				lastName = "";
				System.out.println("BudJet.txt file has been created.");
			}
		}
		catch (IOException e)
		{
			System.out.println("An error occured when accessing database.");
		}
	}
}