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
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.SenhaIncoretaException;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

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
				try {
					GemialidadesLoja.getInstance().inserirCliente((new Cliente("Wealey", "21", null, "Admin", "Admin")));
				} catch (ClienteJaExisteException e2) {
					e2.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 482);
		telaLogin = new JPanel();
		telaLogin.setForeground(new Color(210, 105, 30));
		telaLogin.setBackground(new Color(255, 140, 0));
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
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(203, 181, 19, 14);
		telaLogin.add(lblId);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBackground(Color.WHITE);
		lblSenha.setBounds(203, 221, 55, 14);
		telaLogin.add(lblSenha);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBorder(UIManager.getBorder("TextPane.border"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_ID.getText().equalsIgnoreCase("Admin") && tf_Senha.getText().equalsIgnoreCase("Admin")){
					TelaAdmin telaAdmin = new TelaAdmin();
					telaAdmin.setVisible(true);
				}else if(login(tf_ID.getText(), tf_Senha.getText())) {
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setVisible(true);
					}
				}
			
		});
		btnNewButton.setBounds(260, 270, 105, 29);
		telaLogin.add(btnNewButton);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro(loja);
				telaCadastro.setVisible(true);
			}
		});
		btnCadastrar.setBorder(UIManager.getBorder("TextPane.border"));
		btnCadastrar.setBounds(259, 392, 107, 19);
		telaLogin.add(btnCadastrar);

		JLabel lblNovoPorAqui = new JLabel("Novo por aqui? Cadastre-se abaixo!");
		lblNovoPorAqui.setBackground(Color.white);
		lblNovoPorAqui.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		lblNovoPorAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoPorAqui.setForeground(Color.WHITE);
		lblNovoPorAqui.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNovoPorAqui.setBounds(187, 362, 250, 19);
		telaLogin.add(lblNovoPorAqui);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images\\logo.png"));
		lblNewLabel_1.setBounds(128, 11, 378, 146);
		telaLogin.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images\\Fundo.jpg"));
		lblNewLabel.setBounds(0, 0, 626, 444);
		telaLogin.add(lblNewLabel);

		try {
			this.loja = GemialidadesLoja.getInstance();
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
