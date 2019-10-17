package com.vastika.userinfo.basic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.vastika.userinfo.dao.UserDao;
import com.vastika.userinfo.dao.UserDaoImpl;
import com.vastika.userinfo.model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNationality;
	private JLabel lblHobbies;
	private JLabel lblGender;
	private JLabel lblDob;

	public JTextField unameField;
	public JTextField emailField;
	public JPasswordField passwordField;
	public JRadioButton femaleRadioBtn;
	public JRadioButton maleRadioBtn;
	public JCheckBox playChBox;
	public JCheckBox readChkBox;
	public JComboBox<String> nationalityCombo;
	public JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public UserForm() {
		setTitle("User Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(63, 161, 202, 38);
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		unameField = new JTextField();
		unameField.setBounds(312, 161, 235, 38);
		contentPane.add(unameField);
		unameField.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(63, 229, 202, 38);
		lblEmail.setForeground(Color.CYAN);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblEmail);

		emailField = new JTextField();
		emailField.setBounds(312, 231, 235, 38);
		emailField.setColumns(10);
		contentPane.add(emailField);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(63, 296, 173, 38);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.CYAN);
		contentPane.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(312, 302, 235, 38);
		contentPane.add(passwordField);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(63, 362, 202, 38);
		lblGender.setForeground(Color.CYAN);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblGender);

		femaleRadioBtn = new JRadioButton("Female");
		femaleRadioBtn.setBounds(312, 369, 90, 29);
		femaleRadioBtn.setSelected(true);
		femaleRadioBtn.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(femaleRadioBtn);

		maleRadioBtn = new JRadioButton("Male");
		maleRadioBtn.setBounds(430, 369, 117, 29);
		maleRadioBtn.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(maleRadioBtn);

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleRadioBtn);
		genderGroup.add(femaleRadioBtn);

		lblNationality = new JLabel("Nationality");
		lblNationality.setBounds(63, 426, 202, 38);
		lblNationality.setForeground(Color.CYAN);
		lblNationality.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNationality);

		nationalityCombo = new JComboBox<>();
		nationalityCombo.setBounds(312, 434, 235, 38);
		nationalityCombo.setModel(
				new DefaultComboBoxModel<>(new String[] { "Nepali ", "American", "Indian", "Japanese", "Chinese" }));
		contentPane.add(nationalityCombo);

		lblHobbies = new JLabel("Hobbies");
		lblHobbies.setBounds(63, 494, 202, 38);
		lblHobbies.setForeground(Color.CYAN);
		lblHobbies.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblHobbies);

		playChBox = new JCheckBox("Playing");
		playChBox.setBounds(312, 501, 103, 29);
		contentPane.add(playChBox);

		readChkBox = new JCheckBox("Reading");
		readChkBox.setBounds(444, 501, 103, 29);
		contentPane.add(readChkBox);

		lblDob = new JLabel("DOB");
		lblDob.setBounds(63, 559, 202, 38);
		lblDob.setForeground(Color.CYAN);
		lblDob.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblDob);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(312, 569, 235, 38);
		contentPane.add(dateChooser);

		JButton submitBtn = new JButton("Submit");
		submitBtn.setBounds(432, 649, 115, 29);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				UserDao userDao = new UserDaoImpl();
				User user = new User();
				user.setUsername(unameField.getText());
				user.setPassword(new String(passwordField.getPassword()));
				user.setEmail(emailField.getText());

				if (maleRadioBtn.isSelected()) {
					user.setGender(maleRadioBtn.getText());

				} else {
					user.setGender(femaleRadioBtn.getText());

				}

				String hobbies = "";
				if (readChkBox.isSelected()) {
					hobbies = hobbies + readChkBox.getText() + ",";
				}

				if (playChBox.isSelected()) {
					hobbies = hobbies + playChBox.getText() + ",";
				}
				user.setHobbies(hobbies);

				user.setNationality((String) nationalityCombo.getSelectedItem());
				user.setDob(dateChooser.getDate());
				String userId = idLbl.getText();
				if(
						userId==null || userId.isEmpty()) {
					int saved = userDao.saveUser(user);
					if(saved >=1) {
						JOptionPane.showMessageDialog(new UserForm(), "User info isaved successfully!!", "Saved!!!", 
								JOptionPane.PLAIN_MESSAGE);
						UserTable userTable = new UserTable();
						userTable.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(new UserForm(), "Error in DB!!", "Error!!!", 
								JOptionPane.ERROR_MESSAGE);
					}

					
				}else {
					int updated = userDao.saveUser(user);
					if(updated >= 1) {
						
					}
				}
				
				
			}

		});
		submitBtn.setForeground(Color.ORANGE);
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(submitBtn);
	}
}
