package org.orecraft.budjet;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

public class LogPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea logTextField;
	
	private JLabel currentBalanceLabel;
	
	private JButton resetLogButton;
	
	private double currentBalance;

	public LogPanel() {
		
		setBorder(BorderFactory.createRaisedBevelBorder());
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(3, 1));
		
		JPanel logPanel = new JPanel();
		logPanel.setBorder(BorderFactory.createTitledBorder("Transaction Logs"));
		logPanel.setBackground(Color.LIGHT_GRAY);
		logPanel.setLayout(new GridLayout(2, 1));
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		currentBalanceLabel = new JLabel("Current Balance: " + fmt.format(currentBalance));
		
		resetLogButton = new JButton("Reset Log File");
		resetLogButton.addActionListener(new ResetLogButtonListener());
		
		JPanel resetLogPanel = new JPanel();
		resetLogPanel.setBackground(Color.LIGHT_GRAY);
		resetLogPanel.add(resetLogButton);
		
		logTextField = new JTextArea(20, 30);
		logTextField.setBackground(Color.LIGHT_GRAY);
		logTextField.setEditable(false);
		
		logPanel.add(logTextField);
		logPanel.add(currentBalanceLabel);
		
		add(logPanel);
		add(resetLogPanel);
		
		FileSystemView fw = FileSystemView.getFileSystemView();
		File folder = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet");
		File file = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet/BudJetLog.txt");
		if(folder.exists() && file.exists())
		{
			try {
				Scanner scan = new Scanner(file);
				String log = "";
				while(scan.hasNext())
					log += scan.nextLine() + "\n";
				logTextField.setText(log);
			} 
			catch (IOException e) 
			{
				System.err.println("An error occured when reading from the log file.");
				e.printStackTrace();
			}
		}
		else if(folder.exists() && !file.exists()) {
			try {
				file.createNewFile();
				System.out.println("Log file created.");
			} catch (IOException e) {
				System.out.println("An error occured.");
				e.printStackTrace();
			}
		}
		else if(!folder.exists() && !file.exists()) {
			try {
				folder.mkdir();
				file.createNewFile();
				System.out.println("Directory and log file created.");
			}
			catch(IOException e) {
				System.out.println("An error occured.");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("An error occured.");
		}
	}
	
	public void refreshLogReader()
	{
		FileSystemView fw = FileSystemView.getFileSystemView();
		File folder = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet");
		File file = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet/BudJetLog.txt");
		if(folder.exists() && file.exists())
		{
			try {
				Scanner scan = new Scanner(file);
				String log = "";
				while(scan.hasNext())
					log += scan.nextLine() + "\n";
				logTextField.setText(log);
			} 
			catch (IOException e) 
			{
				System.err.println("An error occured when reading from the log file.");
				e.printStackTrace();
			}
		}
		else if(folder.exists() && !file.exists()) {
			try {
				file.createNewFile();
				System.out.println("Log file created.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!folder.exists() && !file.exists()) {
			try {
				folder.mkdir();
				file.createNewFile();
				System.out.println("Directory and log file created.");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("An error occured.");
		}
	}
	
	private class ResetLogButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event)
		{
			new LogFileHandler().resetLogFile();
			refreshLogReader();
			System.out.println("Log file reset.");
		}
	}
	
	public void refreshDisplayedBalance()
	{
		currentBalanceLabel.setText("Current Balance: " + MainPanel.currentBalance.getText());
	}
}
