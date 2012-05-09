 package org.orecraft.budjet;

import java.text.NumberFormat;

/**
 * The "Account" class includes all methods for a bank account.  
 * This includes functions for deposit, withdraw, and reset, and 
 * several others.
 * @author AidanBrady
 *
 */

public class Account {
	protected double balance;
	protected String firstName;
	protected String lastName;
	protected int withdrawAmount;
	protected int depositAmount;
	
	/**
	 * Creates a bank account with the following parameters:
	 * @param first - User's first name
	 * @param last - User's last name
	 * @param initial - User's starting money
	 */
	
	public Account(String first, String last, double initial, int depositNumber, int withdrawNumber)
	{
		firstName = first;
		lastName = last;
		balance = initial;
		depositAmount = depositNumber;
		withdrawAmount = withdrawNumber;
	}
	
	/**
	 * Changes an account's first and last name, and returns them in order.
	 * @param newFirst
	 * @param newLast
	 * @return "newFirst newLast"
	 */
	
	public String editName(String newFirst, String newLast)
	{
		firstName = newFirst;
		lastName = newLast;
		return firstName + " " + lastName;
	}
	
	/**
	 * Deposits an entered amount into the user's bank account.
	 * @param amount
	 * @return new balance
	 */
	
	public double deposit(double amount)
	{
		balance = balance + amount;
		depositAmount++;
		return balance;
	}
	
	/**
	 * Returns first name
	 * @return firstName
	 */
	
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Returns last name
	 * @return lastName
	 */
	
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Resets a user's balance.
	 * @return new balance
	 */
	
	public double reset()
	{
		balance = 0;
		return balance;
	}
	
	public void resetStats()
	{
		withdrawAmount = 0;
		depositAmount = 0;
	}
	
	public void setBalance(double newBalance)
	{
		balance = newBalance;
	}
	
	/**
	 * Withdraws an entered amount from the user's bank account.
	 * @param amount
	 * @return new balance
	 */
		
	public double withdraw(double amount)
	{
		balance = balance - amount;
		withdrawAmount++;
		return balance;
	}
	
	/**
	 * Returns total withdraw amount.
	 * @return withdrawAmount
	 */
	
	public int getTotalWithdraws()
	{
		return withdrawAmount;
	}
	
	/**
	 * Returns total deposit amount.
	 * @return depositAmount
	 */
	
	public int getTotalDeposits()
	{
		return depositAmount;
	}
	
	public void setDepositAmount(int newAmount)
	{
		depositAmount = newAmount;
	}
	
	public void setWithdrawAmount(int newAmount)
	{
		withdrawAmount = newAmount;
	}
	
	/**
	 * Returns a user's balance.
	 * @return user's balance
	 */
	
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * Uses CurrencyInstance, returns name and balance.
	 * 
	 */
	
	public String toString()
	{
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		return firstName + " " + lastName + ", " + fmt.format(balance);
	}
}
