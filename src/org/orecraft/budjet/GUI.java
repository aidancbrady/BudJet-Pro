package org.orecraft.budjet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI
{
	
	private JButton mainPanelButton, logPanelButton;
	private JPanel panel;
	private MainPanel mainPanel = new MainPanel();
	private LogPanel logPanel = new LogPanel();
	
	public GUI() {
		JFrame frame = new JFrame("BudJet Pro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setBackground(Color.GRAY);
		
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(1, 2));
		menuPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		menuPanel.setBackground(Color.LIGHT_GRAY);
		mainPanelButton = new JButton("Main View");
		logPanelButton = new JButton("Log View");
		panel.add(menuPanel);
		mainPanelButton.setEnabled(false);
		mainPanelButton.addActionListener(new MainPanelListener());
		logPanelButton.addActionListener(new LogPanelListener());
		menuPanel.add(mainPanelButton);
		menuPanel.add(logPanelButton);
		logPanel.setVisible(false);
		panel.add(mainPanel);
		panel.add(logPanel);
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private class MainPanelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			logPanelButton.setEnabled(true);
			mainPanelButton.setEnabled(false);
			mainPanel.setVisible(true);
			logPanel.setVisible(false);
		}
	}
	
	private class LogPanelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			mainPanelButton.setEnabled(true);
			logPanelButton.setEnabled(false);
			logPanel.setVisible(true);
			mainPanel.setVisible(false);
			logPanel.refreshDisplayedBalance();
			logPanel.refreshLogReader();
		}
	}
}