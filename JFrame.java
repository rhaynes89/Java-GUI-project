/* Name: Richard Haynes III
 * Course: CNT 4714 - Summer 2020
 * Assignment title: Project 1 - Event-driven Enterprise Simulation
 * Date: Thursday 05/14/2020
 */


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.*;
import java.text.DecimalFormat;
import java.lang.*;
import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class JFrame extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField numItemsForOrder;
	private JTextField bookId;
	private JTextField numOfBooks;
	private JTextField bookInfo;
	private JTextField subtotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Global Variables that need to be accessed in multiple actionListeners
	Double grandTotal = 0.0;
	DecimalFormat df = new DecimalFormat("#.00");
	int numberOfItems = 1;//for GUI values
	ArrayList<String> finalInfo = new ArrayList<String>();
	/**
	 * Create the frame.
	 */
	public JFrame() {
		
		setTitle("Book Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter number of items in this order:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNewLabel.setBounds(51, 30, 214, 14);
		contentPane.add(lblNewLabel);
		
		numItemsForOrder = new JTextField();
		numItemsForOrder.setBounds(268, 28, 452, 20);
		contentPane.add(numItemsForOrder);
		numItemsForOrder.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Book ID for item #" + numberOfItems + ":");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(104, 61, 161, 14);
		contentPane.add(lblNewLabel_1);
		
		bookId = new JTextField();
		bookId.setBounds(268, 59, 452, 20);
		contentPane.add(bookId);
		bookId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter quantity for item #" + numberOfItems + ":");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(104, 92, 154, 14);
		contentPane.add(lblNewLabel_2);
		
		numOfBooks = new JTextField();
		numOfBooks.setBounds(268, 90, 452, 20);
		contentPane.add(numOfBooks);
		numOfBooks.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Item #" + numberOfItems + " info:");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(181, 123, 77, 14);
		contentPane.add(lblNewLabel_3);
		
		bookInfo = new JTextField();
		bookInfo.setBounds(268, 121, 452, 20);
		contentPane.add(bookInfo);
		bookInfo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Order subtotal for " + (numberOfItems - 1) + " item(s):");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 11));
		lblNewLabel_4.setBounds(73, 154, 189, 14);
		contentPane.add(lblNewLabel_4);
		
		subtotal = new JTextField();
		subtotal.setBounds(268, 152, 452, 20);
		contentPane.add(subtotal);
		subtotal.setColumns(10);
		
		//button declarations
		JButton processBttn = new JButton("Process Item #" + numberOfItems);
		JButton viewOrderBttn = new JButton("View Order");
		JButton confirmBttn = new JButton("Confirm Item #" + numberOfItems);
		JButton finishOrderBttn = new JButton("Finish Order");
		JButton newOrderBttn = new JButton("New Order");
		JButton exitBttn = new JButton("Exit");
		
		
		//JButton processBttn = new JButton("Process Item #1");
		processBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 int bID = Integer.parseInt(bookId.getText());//gets info from bookID entry in GUI
				 String qOfBooks = numOfBooks.getText();//num of certain book
				 String numItems = numItemsForOrder.getText();//size of order
						 
					try
					{

						HashMap<Integer, String> map = new HashMap<>();
						File input = new File("inventory.txt");
						Scanner sC = new Scanner (new FileInputStream(input));
						
						while(sC.hasNext())
						{
							String bookData = sC.nextLine();
							String[] seg = bookData.split(",");
							map.put(Integer.parseInt(seg[0]), bookData);
						}
						sC.close();
						
						/*
						System.out.println(" " + segments[0]);
						System.out.println(" " + segments[1]);
						System.out.println(" " + segments[2]);
						*/
						
						//Prints full book info into text box in GUI
						if(map.containsKey(bID))
						{
							String t = map.get(bID);
							String[] segments = t.split(",");
							segments = map.get(bID).split(",");
							double bookPrice = Double.parseDouble(segments[2].trim());
							double total = 0.0, subtot = 0.0;
							double discountedAmount = 0.0;
							String percentage = "";
							int arrListCounter = 0;
							//this is for finding the percentage of the discount based on quantity of books (same) purchased
							if(Integer.parseInt(qOfBooks) >= 1 && Integer.parseInt(qOfBooks) <= 4)
							{
								percentage = "0";
								subtot = bookPrice * Integer.parseInt(qOfBooks);
							}
							else if(Integer.parseInt(qOfBooks) >= 5 && Integer.parseInt(qOfBooks) <= 9)
							{
								percentage = "10";
								total = bookPrice * Integer.parseInt(qOfBooks);
								discountedAmount = total / 10;
								subtot = total - discountedAmount;
							}
							else if(Integer.parseInt(qOfBooks) >= 10 && Integer.parseInt(qOfBooks) <= 15)
							{
								percentage = "15";
								total = bookPrice * Integer.parseInt(qOfBooks);
								discountedAmount = (total * 15) / 100;
								subtot = total - discountedAmount;
							}
							else if(Integer.parseInt(qOfBooks) > 15)
							{
								percentage = "20";
								total = bookPrice * Integer.parseInt(qOfBooks);
								discountedAmount = (total * 2) / 10;
								subtot = total - discountedAmount;
							}
							String temp = segments[0] + segments[1] + segments[2] + " " + Integer.parseInt(qOfBooks) + " " + percentage + "%"+ " $" + df.format(subtot);
							bookInfo.setText(temp);
							grandTotal += subtot;
							finalInfo.add(arrListCounter, temp);
							//testing to see what is getting stored inside the ArrayLIst
							//System.out.println(" " + finalInfo.get(arrListCounter));
							arrListCounter++;
							
							//Testing Size of finalInfo ArrayList
							//System.out.println("Order Size: " + finalInfo.size());
							
							/*
							System.out.println("Size of order: " + numItems);
							System.out.println("Number of books: " + Integer.parseInt(qOfBooks));
							System.out.println("Discounted Amount: $" + df.format(discountedAmount));//not working
							System.out.println("total: $" + df.format(total));
							System.out.println("Grand total: $" + df.format(grandTotal));
							*/
						}
						else
						{
							//shows user that the bookID is invalid
							String message, prompt;
							Button Ok;
							message = "Book ID " + bID + " not in file";
							JOptionPane.showMessageDialog(null, message);
							bookId.setText("");
							numOfBooks.setText("");
							//bookInfo.setText("Book is not in the database."); this line was for testing only
							return;
						}
						
					}
					catch(FileNotFoundException exp)
					{
						//bookInfo.setText("Book is not in the database.");
						return;
					}
					confirmBttn.setEnabled(true);
					processBttn.setEnabled(false);
				}
			});
			processBttn.setBounds(10, 227, 135, 23);
			contentPane.add(processBttn);
		
		//JButton confirmBttn = new JButton("Confirm Item #1");
		confirmBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String numItems = numItemsForOrder.getText();//size of order
				subtotal.setText("$" + df.format(grandTotal));
				
				String message, prompt;
				Button Ok;
				message = "Item #" + numberOfItems + " accepted";
				JOptionPane.showMessageDialog(null, message);
				numberOfItems++;
				bookId.setText("");
				numOfBooks.setText("");
			    processBttn.setText("Process Item #" + numberOfItems);
				confirmBttn.setText("Confirm Item #" + numberOfItems);
				lblNewLabel_1.setText("Enter Book ID for item #" + numberOfItems + ":");
				lblNewLabel_2.setText("Enter quantity for item #" + numberOfItems + ":");
				lblNewLabel_3.setText("Item #" + (numberOfItems - 1) + " info:");
				lblNewLabel_4.setText("Order subtotal for " + (numberOfItems - 1) + " item(s):");
				numItemsForOrder.setEnabled(false);	
				
				if(numberOfItems > Integer.parseInt(numItems))
				{
					//changes labels/buttons/textfields options once order is full.
					lblNewLabel_1.setText(" ");
					lblNewLabel_2.setText(" ");
					processBttn.setEnabled(false);
				    viewOrderBttn.setEnabled(true);
					finishOrderBttn.setEnabled(true);
					exitBttn.setEnabled(true);
					confirmBttn.setEnabled(false);
					newOrderBttn.setEnabled(true);
					bookId.setEnabled(false);
					numOfBooks.setEnabled(false);
					processBttn.setText("Process Item #" + (numberOfItems - 1));
					confirmBttn.setText("Confirm Item #" + (numberOfItems - 1));
				}
				else
				{
					//setting buttons to proper state
					processBttn.setEnabled(true);
					viewOrderBttn.setEnabled(true);
					finishOrderBttn.setEnabled(true);
					exitBttn.setEnabled(true);
					confirmBttn.setEnabled(false);
					newOrderBttn.setEnabled(true);
				}
			}
		 });
		confirmBttn.setEnabled(false);
		confirmBttn.setBounds(155, 227, 135, 23);
		contentPane.add(confirmBttn);
		
		//JButton viewOrderBttn = new JButton("View Order");
		viewOrderBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int counter;
				String message = " ";
				String message2 = "Order Overview";
				for(counter = 0; counter < finalInfo.size(); counter++)
				{
					message += (counter + 1) + ". " + finalInfo.get(counter) + " \n";
				}
				JOptionPane.showMessageDialog(null, message, message2, 1);
				Button Ok;
				
				
				processBttn.setEnabled(false);
				viewOrderBttn.setEnabled(true);
				finishOrderBttn.setEnabled(true);
				exitBttn.setEnabled(true);
				confirmBttn.setEnabled(false);
				newOrderBttn.setEnabled(true);
			}
		});
		viewOrderBttn.setEnabled(false);
		viewOrderBttn.setBounds(300, 227, 104, 23);
		contentPane.add(viewOrderBttn);
		
		//JButton finishOrderBttn = new JButton("Finish Order");
		finishOrderBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				double tax = (grandTotal * 6) / 100; 
				double orderTotal = grandTotal + tax;
				String numItems = numItemsForOrder.getText();
				String orderOutput = " ";
				int counter;
				
				LocalDateTime transactionID = LocalDateTime.now();
				DateTimeFormatter timeFormatTxt = DateTimeFormatter.ofPattern("ddMMyyyyhhmm");
				DateTimeFormatter timeFormatGUI = DateTimeFormatter.ofPattern("M/dd/yy hh:mm:ss");
				String txtTimeStamp = transactionID.format(timeFormatTxt);
				String GUITimeStamp = transactionID.format(timeFormatGUI);
			
				/* Test lines for date formatting
				System.out.println("Time stamp: " + txtTimeStamp);
				System.out.println("GUI Time stamp: " + GUITimeStamp + " PM EDT");
				*/
				
				//GUI for Final Order
				FinalOrderGUI fog = new FinalOrderGUI();
				fog.setVisible(true);
				fog.getDate().setText("" + GUITimeStamp + " PM EDT");
				fog.getItems().setText("" + numItems);
				for(counter = 0; counter < finalInfo.size(); counter++)
				{
					orderOutput += (counter + 1) + ". " + finalInfo.get(counter) + " \n";
				}
				fog.getOrderInfo().setText(orderOutput);
				fog.getOrderSubtotal().setText("$" + df.format(grandTotal));
				fog.getTaxRT().setText("6%");
				fog.getTxAmount().setText("$" + df.format(tax));
				fog.getFinalPrice().setText("$" + df.format(orderTotal));
				
				//writing/creating the file
				try
				{
					File outputFile = new File("transactions.txt");
					if(outputFile.createNewFile())
					{
						FileWriter newWriter = new FileWriter("transactions.txt");
						for(counter = 0; counter < finalInfo.size(); counter++)
						{
							newWriter.write("" + txtTimeStamp + ", " + finalInfo.get(counter) + ", "
						    + GUITimeStamp.replace(" ", ", ") + " PM EDT \n");
						}
						newWriter.close();
					}
					else
					{
						try(FileWriter appendFile = new FileWriter("transactions.txt", true);
							BufferedWriter bw = new BufferedWriter(appendFile);
							PrintWriter appendTxt = new PrintWriter(bw))
						{
							for(counter = 0; counter < finalInfo.size(); counter++)
							{
								appendTxt.println("" + txtTimeStamp + ", " + finalInfo.get(counter) + ", "
										+ GUITimeStamp.replace(" ", ", ") + " PM EDT");
							}
						}
						catch(IOException appendFile)
						{
							appendFile.printStackTrace();
						}
					}
				}
				catch(IOException outputFile)
				{
					outputFile.printStackTrace();
				}
				
				
				
			}
		});
		finishOrderBttn.setEnabled(false);
		finishOrderBttn.setBounds(414, 227, 104, 23);
		contentPane.add(finishOrderBttn);
		
		//JButton newOrderBttn = new JButton("New Order");
		newOrderBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				numberOfItems = 1;
				//clears labels text fields
				numItemsForOrder.setText(" ");
				bookId.setText("");
				bookInfo.setText("");
				numOfBooks.setText("");
				subtotal.setText("");
				
				//setting buttons back to beginning state
				processBttn.setText("Process Item #" + 1);
				confirmBttn.setText("Confirm Item #" + 1);
				
				//set labels to beginning state
				lblNewLabel_1.setText("Enter Book ID for item #" + 1 + ":");
				lblNewLabel_2.setText("Enter quantity for item #" + 1 + ":");
				lblNewLabel_3.setText("Item #" + 1 + " info:");
				lblNewLabel_4.setText("Order subtotal for " + 0 + " item(s):");
				
				//set text fields to beginning state
				numItemsForOrder.setEnabled(true);
				bookId.setEnabled(true);
				bookInfo.setEnabled(true);
				numOfBooks.setEnabled(true);
				subtotal.setEnabled(true);
				
				
				//changing all buttons to proper state
				viewOrderBttn.setEnabled(false);
				confirmBttn.setEnabled(false);
				finishOrderBttn.setEnabled(false);
				exitBttn.setEnabled(true);
				processBttn.setEnabled(true);
				newOrderBttn.setEnabled(true);
				
				//clears ArrayList
				finalInfo.clear();
				
			}
		});
		newOrderBttn.setBounds(528, 227, 98, 23);
		contentPane.add(newOrderBttn);
		
		//JButton exitBttn = new JButton("Exit");
		exitBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		exitBttn.setBounds(636, 227, 89, 23);
		contentPane.add(exitBttn);
	}
}
