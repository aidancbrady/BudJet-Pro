package org.orecraft.budjet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.filechooser.FileSystemView;

public class LogFileHandler {
	
	public void addDepositLog(double amount, String log)
	{
		FileSystemView fw = FileSystemView.getFileSystemView();
		File folder = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet");
		File file = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet/BudJetLog.txt");
		if(folder.exists() && file.exists())
		{
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
				out.append("[" + getDate() + "] + $" + amount + "0 [" + log + "]");
				out.newLine();
				System.out.println("Log file updated.");
				out.flush();
				out.close();
			} catch (IOException e) {
				System.err.println("An error occured while updating the log file.");
			}
		}
		else if(folder.exists() && !file.exists())
		{
			try {
				file.createNewFile();
				System.out.println("'BudJetLog.txt' file created.");
				BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
				out.append("[" + getDate() + "] + $" + amount + "0 [" + log + "]");
				out.newLine();
				System.out.println("Log file updated.");
				out.flush();
				out.close();
			} catch (IOException e) {
				System.err.println("An error occured while updating the log file.");
			}
		}
		else if(!folder.exists() && !file.exists())
		{
			try {
				folder.mkdir();
				System.out.println("Directory created.");
				file.createNewFile();
				System.out.println("'BudJetLog.txt' file created.");
				BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
				out.append("[" + getDate() + "] + $" + amount + "0 [" + log + "]");
				out.newLine();
				System.out.println("Log file updated.");
				out.flush();
				out.close();
			} catch (IOException e) {
				System.err.println("An error occured while updating the log file.");
			}
		}
		else {
			System.out.println("An error occured.");
		}
	}
	
	public void addWithdrawLog(double amount, String log)
	{
		FileSystemView fw = FileSystemView.getFileSystemView();
		File folder = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet");
		File file = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet/BudJetLog.txt");
		if(folder.exists() && file.exists())
		{
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
				out.append("[" + getDate() + "] - $" + amount + "0 [" + log + "]");
				out.newLine();
				System.out.println("Log file updated.");
				out.flush();
				out.close();
			} catch (IOException e) {
				System.err.println("An error occured while updating the log file.");
			}
		}
		else if(folder.exists() && !file.exists())
		{
			try {
				file.createNewFile();
				System.out.println("'BudJetLog.txt' file created.");
				BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
				out.append("[" + getDate() + "] - $" + amount + "0 [" + log + "]");
				out.newLine();
				System.out.println("Log file updated.");
				out.flush();
				out.close();
			} catch (IOException e) {
				System.err.println("An error occured while updating the log file.");
			}
		}
		else if(!folder.exists() && !file.exists())
		{
			try {
				folder.mkdir();
				System.out.println("Directory created.");
				file.createNewFile();
				System.out.println("'BudJetLog.txt' file created.");
				BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
				out.append("[" + getDate() + "] - $" + amount + "0 [" + log + "]");
				out.newLine();
				System.out.println("Log file updated.");
				out.flush();
				out.close();
			} catch (IOException e) {
				System.err.println("An error occured while updating the log file.");
			}
		}
		else {
			System.out.println("An error occured.");
		}
	}
	
	public void resetLogFile()
	{
		try {
			FileSystemView fw = FileSystemView.getFileSystemView();
			File folder = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet");
			File file = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet/BudJetLog.txt");
			if(folder.exists() && file.exists())
			{
				file.delete();
				file.createNewFile();
			}
			else if(folder.exists() && !file.exists())
			{
				file.createNewFile();
				System.out.println("Log file created.");
			}
			else if(!folder.exists() && !file.exists())
			{
				folder.mkdir();
				file.createNewFile();
				System.out.println("Directory and log file created.");
			}
			else {
				System.out.println("An error occured.");
			}
		}
		catch (IOException e) {
			System.err.println("An error occured when resetting log file.");
		}
	}
	
	public String getDate()
	{
		String date = "";
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yy");
		Calendar calendar = Calendar.getInstance();
		date = dateformat.format(calendar.getTime());
		return date;
	}
}
