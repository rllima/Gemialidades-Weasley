package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tf_CadID;
	private JTextField tf_CadSenha;
	private JTextField tf_Logradouro;
	private JTextField tf_CEP;
	private JTextField tf_Numero;
	private JTextField tf_Complemento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(262, 374, 101, 30);
		contentPane.add(btnCadastrar);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.ORANGE);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(90, 191, 22, 14);
		contentPane.add(lblId);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.ORANGE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(76, 224, 36, 14);
		contentPane.add(lblSenha);
		
		tf_CadID = new JTextField();
		tf_CadID.setBounds(122, 189, 101, 20);
		contentPane.add(tf_CadID);
		tf_CadID.setColumns(10);
		
		tf_CadSenha = new JTextField();
		tf_CadSenha.setBounds(122, 222, 101, 20);
		contentPane.add(tf_CadSenha);
		tf_CadSenha.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setForeground(Color.ORANGE);
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEndereo.setBounds(440, 106, 89, 23);
		contentPane.add(lblEndereo);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setForeground(Color.ORANGE);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogradouro.setBounds(301, 192, 71, 17);
		contentPane.add(lblLogradouro);
		
		JLabel lblCEP = new JLabel("CEP");
		lblCEP.setForeground(Color.ORANGE);
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCEP.setBounds(342, 228, 30, 14);
		contentPane.add(lblCEP);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setForeground(Color.ORANGE);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumero.setBounds(316, 259, 56, 14);
		contentPane.add(lblNumero);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setForeground(Color.ORANGE);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComplemento.setBounds(286, 290, 89, 14);
		contentPane.add(lblComplemento);
		
		tf_Logradouro = new JTextField();
		tf_Logradouro.setBounds(382, 189, 217, 20);
		contentPane.add(tf_Logradouro);
		tf_Logradouro.setColumns(10);
		
		tf_CEP = new JTextField();
		tf_CEP.setBounds(382, 222, 150, 20);
		contentPane.add(tf_CEP);
		tf_CEP.setColumns(10);
		
		tf_Numero = new JTextField();
		tf_Numero.setBounds(382, 253, 86, 20);
		contentPane.add(tf_Numero);
		tf_Numero.setColumns(10);
		
		tf_Complemento = new JTextField();
		tf_Complemento.setBounds(382, 284, 217, 20);
		contentPane.add(tf_Complemento);
		tf_Complemento.setColumns(10);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBackground(Color.GRAY);
		lblBackground.setIcon(new ImageIcon("images\\Fundo.jpg"));
		lblBackground.setBounds(0, 0, 626, 443);
		contentPane.add(lblBackground);
		
	
	}
}
