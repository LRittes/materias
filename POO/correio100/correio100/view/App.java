package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import data.dataSource.interfaceDB.UserDB;
import data.dataSource.local.DB;
import data.dataSource.local.UserDBImp;
import domain.entity.Email;
import domain.entity.User;
import view.controller.Sistema;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPasswordField;

public class App extends JFrame {

	private JPanel painel;
	private JTextField textFieldNewEmailTo;
	private JTextField textFieldSubjectNewEmail;
	private JTextField textFieldEmailLogin;
	private JTextField textFieldEmailRegister;
	private JTextField textFieldUserNameRegister;
	private JPasswordField passwordFieldLogin;
	private JPasswordField passwordFieldRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
	public App() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		painel = new JPanel();
		painel.setBackground(new Color(255, 255, 255));
		painel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		setContentPane(painel);
		painel.setLayout(null);

		Sistema controller = new Sistema();

		JPanel mainRoute = new JPanel();
		mainRoute.setBounds(0, 0, 600, 450);
		painel.add(mainRoute);
		mainRoute.setLayout(new CardLayout(0, 0));

		JPanel loginPage = new JPanel();
		loginPage.setBackground(new Color(255, 255, 255));
		mainRoute.add(loginPage, "name_4299642073911");
		loginPage.setLayout(null);

		JPanel routePage = new JPanel();
		routePage.setBounds(new Rectangle(0, 0, 600, 450));
		mainRoute.add(routePage, "name_4299633132206");
		routePage.setLayout(null);

		JButton btnNewEmail = new JButton("Novo Email");
		btnNewEmail.setForeground(Color.WHITE);
		btnNewEmail.setFocusable(false);
		btnNewEmail.setFocusTraversalPolicyProvider(true);
		btnNewEmail.setBackground(new Color(47, 128, 246));
		btnNewEmail.setBounds(10, 71, 160, 47);
		routePage.add(btnNewEmail);

		JButton btnInbox = new JButton("Caixa de Entrada");
		btnInbox.setForeground(new Color(46, 52, 54));
		btnInbox.setFocusable(false);
		btnInbox.setFocusTraversalPolicyProvider(true);
		btnInbox.setBackground(new Color(203, 203, 203));
		btnInbox.setBounds(10, 130, 160, 47);
		routePage.add(btnInbox);

		JPanel viewRoute = new JPanel();
		viewRoute.setBounds(193, 0, 395, 395);
		routePage.add(viewRoute);
		viewRoute.setLayout(new CardLayout(0, 0));

		JPanel newEmailPage = new JPanel();
		viewRoute.add(newEmailPage, "name_887989786040");
		newEmailPage.setLayout(null);

		JPanel emailField = new JPanel();
		emailField.setBounds(12, 12, 371, 49);
		emailField.setBackground(new Color(255, 255, 255));
		newEmailPage.add(emailField);
		emailField.setLayout(null);

		JLabel lblPara = new JLabel("Para:");
		lblPara.setBounds(12, 18, 38, 15);
		emailField.add(lblPara);

		textFieldNewEmailTo = new JTextField();
		textFieldNewEmailTo.setForeground(new Color(0, 0, 0));
		textFieldNewEmailTo.setBounds(55, 12, 304, 27);
		emailField.add(textFieldNewEmailTo);
		textFieldNewEmailTo.setColumns(10);

		JPanel subjectFieldNewEmail = new JPanel();
		subjectFieldNewEmail.setBounds(12, 73, 371, 49);
		subjectFieldNewEmail.setLayout(null);
		subjectFieldNewEmail.setBackground(Color.WHITE);
		newEmailPage.add(subjectFieldNewEmail);

		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setBounds(12, 18, 73, 15);
		subjectFieldNewEmail.add(lblAssunto);

		textFieldSubjectNewEmail = new JTextField();
		textFieldSubjectNewEmail.setForeground(new Color(0, 0, 0));
		textFieldSubjectNewEmail.setColumns(10);
		textFieldSubjectNewEmail.setBounds(81, 12, 273, 27);
		subjectFieldNewEmail.add(textFieldSubjectNewEmail);

		JTextArea textAreaNewEmail = new JTextArea();
		textAreaNewEmail.setBounds(12, 134, 371, 209);
		newEmailPage.add(textAreaNewEmail);

		JButton btnSendEmail = new JButton("Enviar");
		btnSendEmail.setForeground(new Color(255, 255, 255));
		btnSendEmail.setBackground(new Color(94, 195, 60));
		btnSendEmail.setBounds(266, 355, 117, 25);
		newEmailPage.add(btnSendEmail);

		JPanel inboxPage = new JPanel();
		viewRoute.add(inboxPage, "name_888002535061");
		inboxPage.setLayout(null);

		JButton btnExitButton = new JButton("Sair");
		btnExitButton.setForeground(new Color(255, 255, 255));
		btnExitButton.setFont(new Font("Dialog", Font.BOLD, 8));
		btnExitButton.setBackground(new Color(239, 41, 41));
		btnExitButton.setBounds(24, 366, 56, 24);
		routePage.add(btnExitButton);

		JLabel lblNoMembro = new JLabel("Não é membro ?");
		lblNoMembro.setBounds(236, 316, 84, 25);
		loginPage.add(lblNoMembro);
		lblNoMembro.setFont(new Font("Dialog", Font.BOLD, 8));

		JButton btnToRegisterPage = new JButton("Se cadastre aqui");
		btnToRegisterPage.setBounds(299, 316, 94, 25);
		loginPage.add(btnToRegisterPage);
		btnToRegisterPage.setBorderPainted(false);
		btnToRegisterPage.setBackground(new Color(255, 255, 255));
		btnToRegisterPage.setForeground(new Color(47, 128, 246));
		btnToRegisterPage.setFont(new Font("Dialog", Font.BOLD, 8));

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(226, 263, 160, 47);
		loginPage.add(btnEntrar);
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setFocusable(false);
		btnEntrar.setFocusTraversalPolicyProvider(true);
		btnEntrar.setBackground(new Color(47, 128, 246));

		JLabel lblPasswordLogin = new JLabel("Senha:");
		lblPasswordLogin.setBounds(138, 206, 51, 25);
		loginPage.add(lblPasswordLogin);

		textFieldEmailLogin = new JTextField();
		textFieldEmailLogin.setBounds(138, 169, 314, 25);
		loginPage.add(textFieldEmailLogin);
		textFieldEmailLogin.setColumns(10);

		JLabel lblEmailLogin = new JLabel("Email:");
		lblEmailLogin.setBounds(138, 135, 51, 25);
		loginPage.add(lblEmailLogin);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(222, 37, 181, 95);
		loginPage.add(lblLogin);
		lblLogin.setFont(new Font("Garuda", Font.PLAIN, 50));
		lblLogin.setMaximumSize(new Dimension(100, 50));
		lblLogin.setMinimumSize(new Dimension(100, 50));

		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setBounds(138, 226, 314, 25);
		loginPage.add(passwordFieldLogin);

		JPanel registerPage = new JPanel();
		registerPage.setLayout(null);
		registerPage.setBackground(Color.WHITE);
		mainRoute.add(registerPage, "name_7130036103286");

		JLabel lblJaMembro = new JLabel("Já é membro ?");
		lblJaMembro.setFont(new Font("Dialog", Font.BOLD, 8));
		lblJaMembro.setBounds(237, 353, 63, 25);
		registerPage.add(lblJaMembro);

		JButton btnToLoginPage = new JButton("Fazer login aqui");
		btnToLoginPage.setForeground(new Color(47, 128, 246));
		btnToLoginPage.setFont(new Font("Dialog", Font.BOLD, 8));
		btnToLoginPage.setBorderPainted(false);
		btnToLoginPage.setBackground(Color.WHITE);
		btnToLoginPage.setBounds(266, 353, 110, 25);
		registerPage.add(btnToLoginPage);

		JButton btnRegister = new JButton("Cadastrar");
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFocusable(false);
		btnRegister.setBackground(new Color(47, 128, 246));
		btnRegister.setBounds(216, 304, 160, 47);
		registerPage.add(btnRegister);

		JLabel lblpasswordRegister = new JLabel("Senha:");
		lblpasswordRegister.setBounds(138, 233, 51, 25);
		registerPage.add(lblpasswordRegister);

		textFieldEmailRegister = new JTextField();
		textFieldEmailRegister.setColumns(10);
		textFieldEmailRegister.setBounds(138, 208, 314, 25);
		registerPage.add(textFieldEmailRegister);

		JLabel lblemailRegister = new JLabel("Email:");
		lblemailRegister.setBounds(138, 183, 51, 25);
		registerPage.add(lblemailRegister);

		JLabel lblCadastrar = new JLabel("CADASTRAR");
		lblCadastrar.setMinimumSize(new Dimension(100, 50));
		lblCadastrar.setMaximumSize(new Dimension(100, 50));
		lblCadastrar.setFont(new Font("Garuda", Font.PLAIN, 50));
		lblCadastrar.setBounds(138, 34, 339, 95);
		registerPage.add(lblCadastrar);

		passwordFieldRegister = new JPasswordField();
		passwordFieldRegister.setBounds(138, 257, 314, 25);
		registerPage.add(passwordFieldRegister);

		JLabel lblUserNameRegister = new JLabel("Name: ");
		lblUserNameRegister.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserNameRegister.setBounds(126, 128, 51, 25);
		registerPage.add(lblUserNameRegister);

		textFieldUserNameRegister = new JTextField();
		textFieldUserNameRegister.setColumns(10);
		textFieldUserNameRegister.setBounds(138, 152, 314, 25);
		registerPage.add(textFieldUserNameRegister);

		// Ação Botão Entrar no Login Page
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textFieldEmailLogin.getText();
				String password = String.valueOf(passwordFieldLogin.getPassword());
				textFieldEmailLogin.setText("");
				passwordFieldLogin.setText("");

				try {
					controller.login(email, password);

					if (!controller.getUser().equals(null)) {
						JLabel lblWelcome = new JLabel(controller.findUserController(email).getName());

						lblWelcome.setFont(new Font("Dialog", Font.BOLD, 24));
						lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
						lblWelcome.setBounds(25, 12, 145, 37);
						routePage.add(lblWelcome);

						mainRoute.removeAll();
						mainRoute.repaint();
						mainRoute.revalidate();
						mainRoute.add(routePage);
						mainRoute.repaint();
						mainRoute.revalidate();
					}

				} catch (Exception err) {

					JOptionPane.showMessageDialog(null, "Usuário inválido!");

					// err.printStackTrace();

				}

			}
		});

		// Ação Botão ir para Register Page
		btnToRegisterPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainRoute.removeAll();
				mainRoute.repaint();
				mainRoute.revalidate();
				mainRoute.add(registerPage);
				mainRoute.repaint();
				mainRoute.revalidate();
			}
		});

		// Ação Botão cadastrar no Register Page
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textFieldEmailRegister.getText();
				String userName = textFieldUserNameRegister.getText();
				String password = String.valueOf(passwordFieldRegister.getPassword());

				textFieldEmailRegister.setText("");
				passwordFieldRegister.setText("");
				textFieldUserNameRegister.setText("");

				if (email.contains("@")) {
					try {
						controller.register(userName, password, email);

						mainRoute.removeAll();
						mainRoute.repaint();
						mainRoute.revalidate();
						mainRoute.add(loginPage);
						mainRoute.repaint();
						mainRoute.revalidate();
					} catch (Exception err) {
						System.out.println(err);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Email inválido!");
				}

			}
		});

		// Ação Botão ir para Login Page
		btnToLoginPage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainRoute.removeAll();
				mainRoute.repaint();
				mainRoute.revalidate();
				mainRoute.add(loginPage);
				mainRoute.repaint();
				mainRoute.revalidate();
			}
		});

		// Ação Botão Novo Email
		btnNewEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRoute.removeAll();
				viewRoute.repaint();
				viewRoute.revalidate();
				viewRoute.add(newEmailPage);
				viewRoute.repaint();
				viewRoute.revalidate();
			}
		});

		// Ação Botão Caixa de entrada
		btnInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int i = 0;
					for (Email email : controller.getUser().getEmails()) {

						JPanel responderEmailPage = new JPanel();
						responderEmailPage.setLayout(null);
						viewRoute.add(responderEmailPage, "name_1682630940146");

						JPanel emailFieldDe = new JPanel();
						emailFieldDe.setLayout(null);
						emailFieldDe.setBackground(Color.WHITE);
						emailFieldDe.setBounds(12, 12, 371, 49);
						responderEmailPage.add(emailFieldDe);

						JPanel subjectFieldOneEmail = new JPanel();
						subjectFieldOneEmail.setLayout(null);
						subjectFieldOneEmail.setBackground(Color.WHITE);
						subjectFieldOneEmail.setForeground(new Color(0, 0, 0));
						subjectFieldOneEmail.setBounds(12, 73, 371, 49);
						responderEmailPage.add(subjectFieldOneEmail);

						JLabel lblAssuntoOneEmail = new JLabel("Assunto:");
						lblAssuntoOneEmail.setBounds(12, 18, 73, 15);
						subjectFieldOneEmail.add(lblAssuntoOneEmail);

						JLabel textFieldOneEmail = new JLabel(email.getSubject());
						textFieldOneEmail.setForeground(new Color(0, 0, 0));
						textFieldOneEmail.setBounds(81, 12, 273, 27);
						subjectFieldOneEmail.add(textFieldOneEmail);

						JButton btnResponderEmail = new JButton("Responder Email");
						btnResponderEmail.setForeground(Color.WHITE);
						btnResponderEmail.setBackground(new Color(94, 195, 60));
						btnResponderEmail.setBounds(182, 355, 201, 25);
						responderEmailPage.add(btnResponderEmail);

						JPanel emailCardInboxPage = new JPanel();
						emailCardInboxPage.setBounds(12, 12 + i * 53, 373, 43);
						emailCardInboxPage.setBackground(new Color(203, 203, 203));
						inboxPage.add(emailCardInboxPage);
						emailCardInboxPage.setLayout(null);

						JLabel lblUserNameInboxPage = new JLabel(email.getAuthor());
						lblUserNameInboxPage.setBounds(12, 12, 72, 15);
						emailCardInboxPage.add(lblUserNameInboxPage);

						JLabel lblDe = new JLabel("De: " + email.getAuthor());
						lblDe.setBounds(12, 18, 347, 15);
						emailFieldDe.add(lblDe);

						JLabel lblSubjectInboxPage = new JLabel(email.getSubject());
						lblSubjectInboxPage.setBounds(142, 12, 70, 15);
						emailCardInboxPage.add(lblSubjectInboxPage);

						JButton btnDel = new JButton("del");
						btnDel.setForeground(new Color(243, 243, 243));
						btnDel.setBackground(new Color(249, 107, 107));
						btnDel.setMargin(new Insets(2, 2, 2, 2));
						btnDel.setFont(new Font("Dialog", Font.PLAIN, 8));
						btnDel.setBounds(320, 8, 25, 25);
						emailCardInboxPage.add(btnDel);

						JButton btnShowEmail = new JButton("ver");
						btnShowEmail.setFont(new Font("Dialog", Font.BOLD, 8));
						btnShowEmail.setBackground(new Color(138, 226, 52));
						btnShowEmail.setForeground(new Color(255, 255, 255));
						btnShowEmail.setBounds(272, 8, 46, 25);
						emailCardInboxPage.add(btnShowEmail);

						JTextPane txtpnAEmailReceived = new JTextPane();
						txtpnAEmailReceived.setText(email.getMessage());
						txtpnAEmailReceived.setBounds(12, 134, 371, 209);
						responderEmailPage.add(txtpnAEmailReceived);

						// Ação Botão Ver em Caixa de entrada
						btnShowEmail.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								viewRoute.removeAll();
								viewRoute.repaint();
								viewRoute.revalidate();
								viewRoute.add(responderEmailPage);
								viewRoute.repaint();
								viewRoute.revalidate();
							}
						});

						// Ação Botão Del em Caixa de entrada
						btnDel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {

									controller.excluirEmail(email.getId());
									controller.login(controller.getUser().getEmail(),
											controller.getUser().getPassword());

									inboxPage.removeAll();
									inboxPage.repaint();
									inboxPage.revalidate();
									btnInbox.doClick();

								} catch (Exception err) {
									err.printStackTrace();
								}

							}
						});

						// Ação Botão Responder Email em Caixa de entrada
						btnResponderEmail.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								viewRoute.removeAll();
								viewRoute.repaint();
								viewRoute.revalidate();
								viewRoute.add(newEmailPage);
								viewRoute.repaint();
								viewRoute.revalidate();
							}
						});
						i++;
					}
				} catch (Exception err) {
					err.printStackTrace();

				}

				viewRoute.removeAll();
				viewRoute.repaint();
				viewRoute.revalidate();
				viewRoute.add(inboxPage);
				viewRoute.repaint();
				viewRoute.revalidate();
			}
		});

		// Ação Botão Sair
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();

				inboxPage.removeAll();
				inboxPage.repaint();
				inboxPage.revalidate();

				viewRoute.removeAll();
				viewRoute.repaint();
				viewRoute.revalidate();
				viewRoute.add(inboxPage);
				viewRoute.repaint();
				viewRoute.revalidate();

				routePage.remove(routePage.findComponentAt(25, 12));
				routePage.repaint();
				routePage.revalidate();

				mainRoute.removeAll();
				mainRoute.repaint();
				mainRoute.revalidate();
				mainRoute.add(loginPage);
				mainRoute.repaint();
				mainRoute.revalidate();
			}
		});

		// Ação Botão enviar Email
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String subject = textFieldSubjectNewEmail.getText();
				String menssage = textAreaNewEmail.getText();
				String receiver = textFieldNewEmailTo.getText();
				textFieldSubjectNewEmail.setText("");
				textAreaNewEmail.setText("");
				textFieldNewEmailTo.setText("");

				try {

					controller.enviarEmail(receiver, menssage, subject);
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Não há destinatário!");
					// err.printStackTrace();
				}

			}
		});
	}
}