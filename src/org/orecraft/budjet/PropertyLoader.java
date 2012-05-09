package org.orecraft.budjet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
	
	public void refreshProperties(String newFirst, String newLast, double newBalance, int newDepositAmount, int newWithdrawAmount)
	{
		Properties properties = new Properties();
		try {
			File file = new File("BudJet.txt");
			if(file.exists())
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
			else {
				file.delete();
				file.createNewFile();
				FileOutputStream fileoutputstream = new FileOutputStream(file);
				properties.setProperty("firstName", "User");
				properties.setProperty("lastName", "");
				properties.setProperty("balance", Double.toString(0.00));
				properties.setProperty("depositAmount", Integer.toString(0));
				properties.setProperty("withdrawAmount", Integer.toString(0));
				properties.store(fileoutputstream, "Main BudJet data.");
				fileoutputstream.close();
				System.out.println("BudJet.txt file has been created.");
			}
		}
		catch (IOException e)
		{
			System.out.println("An error occured while updating database.");
		}
	}
}
