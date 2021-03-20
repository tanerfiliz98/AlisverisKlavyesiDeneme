package alisverisKlavyesi;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Collections;

public class Keyboard implements ActionListener  
{
	private JFrame jForm;
	private Robot robot;
	private JPanel buttonPanel;
	private JButton numButton[];
	private ArrayList<Integer> number;
	private int[][] numButtonLocation;
	
	
	public Keyboard() 
	{
		try 
		{
			robot=new Robot();
		} 
		catch (AWTException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jForm =new JFrame();
		jForm.setAlwaysOnTop(true);
		jForm.setSize(200, 200);
		jForm.setLocationRelativeTo(null);
		jForm.setLayout(null);
		jForm.getContentPane().setBackground(new java.awt.Color(32, 32, 32));
		jForm.setFocusableWindowState(false);
		jForm.addWindowListener(new WindowAdapter() 
		{
			@Override
			//Form kapat�l�rken tu�lar�n b�rak�lmas�n� sa�l�yor
			public void windowClosing(WindowEvent e) 
			{
				for (int i = 0; i < 10; i++)
				{
					robot.keyRelease(numButton[i].getText().charAt(0));
				}
				
			    System.exit(0);
			 }
			 
		  }
		);
		
		//buttonlar�n oldu�u paneli ekliyor ve formu g�z�k�r yap�yor
		jForm.add(btnPanel());
		jForm.setVisible(true);
		
		//panelde olu�an buttonlar�n lokasyonlar�n� al�yor
		numButtonLocation = new int[2][10];
		number=new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) 
		{
			numButtonLocation[0][i]=numButton[i].getLocation().x;
			numButtonLocation[1][i]=numButton[i].getLocation().y;
			number.add(i);
		}
		
		
	}
	
	
	//paneli olu�turup i�ine button olu�turuyor
	private JPanel btnPanel() 
	{
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new java.awt.Color(32, 32, 32));
		numButton = new JButton[13];
		for (int i = 0; i < 10; i++) 
		{
			//yeni bir button olu�turup panele ekliyor
			numButton[i] = new JButton(String.valueOf(i));
			numButton[i].setBackground(Color.GRAY);
			numButton[i].setForeground(Color.WHITE);
			numButton[i].addActionListener(this);
			buttonPanel.add(numButton[i]);
		}

		buttonPanel.setBounds(15, 15, 153, 125);
		return buttonPanel;
	}
	
	
	//buttonlar�n yerlerlini de�i�tiriyor
	private void shuffle() 
	{
		Collections.shuffle(number);
		 
		 for (int i = 0; i < 10; i++) 
		 {
			 numButton[i].setLocation(numButtonLocation[0][number.get(i)], numButtonLocation[1][number.get(i)]);
		 }
	}
	
	
	//buttona bas�l�nca ne yap�ca�� ile ilgili fonksiyon
	public void actionPerformed(ActionEvent arg0) 
	{
		for (int i = 0; i < 10; i++) 
		{
			if (arg0.getSource()==numButton[i]) 
			{
				robot.keyPress(numButton[i].getText().charAt(0));
				
				shuffle();
			}
		}
		
	}
	
	
	public static void main(String[] args) 
	{
		new Keyboard();
	}
}
