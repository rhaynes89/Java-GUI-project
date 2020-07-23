/* Name: Richard Haynes III
 * Course: CNT 4714 - Summer 2020
 * Assignment title: Project 1 - Event-driven Enterprise Simulation
 * Date: Thursday 05/22/2020
 */




import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinalOrderGUI extends JFrame {

	private JPanel contentPane;
	private JTextField bookList;
	private JTextField date;
	private JTextField items;
	private JTextField orderSubtotal;
	private JTextField price;
	private JTextField finalPrice;
	private JTextField txAmount;
	private JTextField textField_2;
	private JTextField orderInfo;
	private JTextField taxRT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalOrderGUI frame = new FinalOrderGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinalOrderGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setBounds(10, 32, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number of line Items:");
		lblNewLabel_1.setBounds(10, 57, 121, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Item# / ID / Title / Price / Qty / Disc % / Subtotal:");
		lblNewLabel_2.setBounds(10, 82, 273, 14);
		contentPane.add(lblNewLabel_2);
		
		setDate(new JTextField());
		getDate().setEditable(false);
		getDate().setBorder(null);
		getDate().setBackground(SystemColor.menu);
		getDate().setBounds(43, 29, 201, 20);
		contentPane.add(getDate());
		getDate().setColumns(10);
		
		setItems(new JTextField());
		getItems().setEditable(false);
		getItems().setBorder(null);
		getItems().setBackground(SystemColor.menu);
		getItems().setBounds(141, 54, 103, 20);
		contentPane.add(getItems());
		getItems().setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Order subtotal: ");
		lblNewLabel_3.setBounds(10, 325, 103, 14);
		contentPane.add(lblNewLabel_3);
		
		setOrderSubtotal(new JTextField());
		getOrderSubtotal().setEditable(false);
		getOrderSubtotal().setBorder(null);
		getOrderSubtotal().setBackground(SystemColor.menu);
		getOrderSubtotal().setBounds(107, 322, 121, 20);
		contentPane.add(getOrderSubtotal());
		getOrderSubtotal().setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Tax rate: 6%");
		lblNewLabel_4.setBounds(10, 350, 77, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tax amount:");
		lblNewLabel_5.setBounds(10, 375, 77, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Order total:");
		lblNewLabel_6.setBounds(10, 400, 77, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Thank you for shopping!");
		lblNewLabel_7.setBounds(171, 460, 156, 14);
		contentPane.add(lblNewLabel_7);
		
		price = new JTextField();
		price.setBorder(null);
		price.setBackground(SystemColor.menu);
		price.setBounds(97, 322, 86, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		setFinalPrice(new JTextField());
		getFinalPrice().setBackground(SystemColor.menu);
		getFinalPrice().setBorder(null);
		getFinalPrice().setBounds(107, 397, 102, 20);
		contentPane.add(getFinalPrice());
		getFinalPrice().setColumns(10);
		
		setTxAmount(new JTextField());
		getTxAmount().setEditable(false);
		getTxAmount().setBorder(null);
		getTxAmount().setBackground(SystemColor.menu);
		getTxAmount().setBounds(107, 372, 131, 20);
		contentPane.add(getTxAmount());
		getTxAmount().setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBorder(null);
		textField_2.setBackground(SystemColor.menu);
		textField_2.setBounds(123, 397, 105, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(194, 485, 89, 23);
		contentPane.add(btnNewButton);
		
		setOrderInfo(new JTextField());
		getOrderInfo().setBorder(null);
		getOrderInfo().setBackground(SystemColor.menu);
		getOrderInfo().setBounds(10, 107, 453, 202);
		contentPane.add(getOrderInfo());
		getOrderInfo().setColumns(10);
		
		setTaxRT(new JTextField());
		getTaxRT().setBorder(null);
		getTaxRT().setBackground(SystemColor.menu);
		getTaxRT().setBounds(107, 347, 86, 20);
		contentPane.add(getTaxRT());
		getTaxRT().setColumns(10);
	}

	public JTextField getDate() {
		return date;
	}

	public void setDate(JTextField date) {
		this.date = date;
	}

	public JTextField getItems() {
		return items;
	}

	public void setItems(JTextField items) {
		this.items = items;
	}

	public JTextField getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(JTextField orderInfo) {
		this.orderInfo = orderInfo;
	}

	public JTextField getOrderSubtotal() {
		return orderSubtotal;
	}

	public void setOrderSubtotal(JTextField orderSubtotal) {
		this.orderSubtotal = orderSubtotal;
	}

	public JTextField getTaxRT() {
		return taxRT;
	}

	public void setTaxRT(JTextField taxRT) {
		this.taxRT = taxRT;
	}

	public JTextField getTxAmount() {
		return txAmount;
	}

	public void setTxAmount(JTextField txAmount) {
		this.txAmount = txAmount;
	}

	public JTextField getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(JTextField finalPrice) {
		this.finalPrice = finalPrice;
	}
}
