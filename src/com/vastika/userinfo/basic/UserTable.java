package com.vastika.userinfo.basic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.vastika.userinfo.dao.UserDao;
import com.vastika.userinfo.dao.UserDaoImpl;
import com.vastika.userinfo.model.User;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class UserTable extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	UserDao userDao = new UserDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserTable frame = new UserTable();
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
	public UserTable() {
		setTitle("User Info Table");
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 382);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		table = new JTable();
		String[] columns = { "Id", "Username", "Password", "Email", "Gender", "Hobbies", "Nationality", "Dob" };
		DefaultTableModel tablemodel = new DefaultTableModel(columns, 0);
		table.setModel(tablemodel);
		loadDataInTable();
		contentPane.setLayout(null);
		contentPane.add(table);

		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(10, 16, 619, 260);
		contentPane.add(scrollpane);

		JButton newBtn = new JButton("new");
		newBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserForm userForm = new UserForm();
				userForm.setVisible(true);
				dispose();
			}
		});
		newBtn.setBounds(125, 292, 115, 29);
		contentPane.add(newBtn);

		JButton editBtn = new JButton("Edit");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserForm userForm = new UserForm();

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					Object id = tableModel.getValueAt(selectedRow, 1);
					Object username = tableModel.getValueAt(selectedRow, 1);
					Object password = tableModel.getValueAt(selectedRow, 1);
					Object email = tableModel.getValueAt(selectedRow, 1);
					Object gender = tableModel.getValueAt(selectedRow, 1);
					Object dob = tableModel.getValueAt(selectedRow, 1);
					Object hobbies = tableModel.getValueAt(selectedRow, 1);
					Object nationality = tableModel.getValueAt(selectedRow, 1);

					try {
						SimpleDateFormat sf = new SimpleDateFormat("yyyy- MM--dd");
						Date date = sf.parse(dob.toString());
						userForm.dateChooser.setDate(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}

			}
		});
		editBtn.setBounds(293, 292, 115, 29);
		contentPane.add(editBtn);

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int deleteConfirm = JOptionPane.showConfirmDialog(new UserTable(),
							"Are you sure you want to delete?", "Delete!!!", JOptionPane.YES_NO_OPTION);

					if (deleteConfirm == 0) {
						System.out.println(deleteConfirm);
						Object id = tablemodel.getValueAt(selectedRow, 0);
						userDao.deleteUserInfo(Integer.parseInt(id.toString()));
						loadDataInTable();
					}

				} else {
					JOptionPane.showMessageDialog(new UserTable(), "please select any row");
				}
			}
		});
		deleteBtn.setBounds(495, 292, 115, 29);
		contentPane.add(deleteBtn);

	}

	public void loadDataInTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		List<User> userlist = userDao.getAllUserInfo();
		for (User u : userlist) {
			tableModel.addRow(new Object[] { u.getId(), u.getUsername(), u.getPassword(), u.getEmail(), u.getGender(),
					u.getHobbies(), u.getNationality(), u.getDob() });
		}

	}
}
