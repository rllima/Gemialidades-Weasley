package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import fachada.*;
import negocios.classesBasicas.*;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.SenhaIncoretaException;

public class TelaLogin extends JFrame {

	private GemialidadesLoja loja;

	private JPanel telaLogin;
	private JTextField tf_ID;
	private JPasswordField tf_Senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TelaLogin frame = new TelaLogin();
				frame.setVisible(true);
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 482);
		telaLogin = new JPanel();
		telaLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(telaLogin);
		telaLogin.setLayout(null);

		tf_ID = new JTextField();
		tf_ID.setBounds(252, 174, 121, 29);
		telaLogin.add(tf_ID);
		tf_ID.setColumns(10);

		tf_Senha = new JPasswordField();
		tf_Senha.setBounds(252, 214, 121, 29);
		telaLogin.add(tf_Senha);
		tf_Senha.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(223, 181, 19, 14);
		telaLogin.add(lblId);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(212, 221, 30, 14);
		telaLogin.add(lblSenha);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(login(tf_ID.getText(), tf_Senha.getPassword().toString())) {
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(260, 270, 105, 42);
		telaLogin.add(btnNewButton);

		JLabel lblNovoPorAqui = new JLabel("Novo por aqui? Cadastre-se abaixo!");
		lblNovoPorAqui.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNovoPorAqui.setBounds(199, 367, 228, 17);
		telaLogin.add(lblNovoPorAqui);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(259, 392, 107, 29);
		telaLogin.add(btnCadastrar);

		try {
			this.loja = new GemialidadesLoja();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public boolean login(String id, String senha) {
		boolean resposta = false;
		try {
			resposta = loja.login(id, senha);
		} catch (ClienteNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (SenhaIncoretaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return resposta;
	}
}
