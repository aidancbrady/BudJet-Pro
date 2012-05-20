package org.orecraft.budjet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

public class PropertyLoader {
	
	public void refreshProperties(String newFirst, String newLast, double newBalance, int newDepositAmount, int newWithdrawAmount)
	{
		Properties properties = new Properties();
		try {
			FileSystemView fw = FileSystemView.getFileSystemView();
			File folder = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet");
			File file = new File(fw.getHomeDirectory(), "/Library/Application Support/BudJet/BudJetData.txt");
			if(folder.exists() && file.exists())
			{
				FileOutputStream fileoutputstream = new FileOutputStream(file);
				properties.setProperty("firstName", newFirst);
				properties.setProperty("lastName", newLast);
				properties.setProperty("balance", Double.toString(newBalance));
				properties.setProperty("depositAmount", Integer.toString(newDepositAmount));
				properties.setProperty("withdrawAmount", Integer.toString(newWithdrawAmount));
				properties.store(fileoutputstream, "BudJet Pro Database");
				fileoutputstream.close();
				System.out.println("Data has been updated.");
			}
			else if(folder.exists() && !file.exists())
			{
				file.createNewFile();
				System.out.println("Created database.");
				FileOutputStream fileoutputstream = new FileOutputStream(file);
				properties.setProperty("firstName", "User");
				properties.setProperty("lastName", "");
				properties.setProperty("balance", Double.toString(0.00));
				properties.setProperty("depositAmount", Integer.toString(0));
				properties.setProperty("withdrawAmount", Integer.toString(0));
				properties.store(fileoutputstream, "Main BudJet data.");
				fileoutputstream.close();
			}
			else if(!folder.exists() && !file.exists())
			{
				folder.mkdir();
				System.out.println("Created directory.");
				file.createNewFile();
				System.out.println("Created database.");
				FileOutputStream fileoutputstream = new FileOutputStream(file);
				properties.setProperty("firstName", "User");
				properties.setProperty("lastName", "");
				properties.setProperty("balance", Double.toString(0.00));
				properties.setProperty("depositAmount", Integer.toString(0));
				properties.setProperty("withdrawAmount", Integer.toString(0));
				properties.store(fileoutputstream, "Main BudJet data.");
				fileoutputstream.close();
			}
			else {
				System.out.println("An error occured.");
			}
		}
		catch (IOException e)
		{
			System.err.println("An error occured while updating database.");
		}
	}
}
