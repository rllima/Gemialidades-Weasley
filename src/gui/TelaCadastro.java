
package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Endereco;
import negocios.exceptions.ClienteJaExisteException;
import fachada.GemialidadesLoja;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tf_CadID;
	private JTextField tf_Logradouro;
	private JTextField tf_CEP;
	private JTextField tf_Numero;
	private JTextField tf_Complemento;
	private JPasswordField passwordField;
	private static GemialidadesLoja loja;
	private JTextField tf_Nome;
	private JTextField tf_IDADE;
	private JTextField tf_Cidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro(loja);
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
	public TelaCadastro(GemialidadesLoja loja) {
		this.loja = loja;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(262, 374, 101, 30);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Endereco endereco = new Endereco(tf_Cidade.getText(), tf_Logradouro.getText(), tf_Numero.getText(), tf_CEP.getText(), tf_Complemento.getText());
				if(cadastrar( tf_Nome.getText(), tf_IDADE.getText(), endereco, tf_CadID.getText(), passwordField.getText())){
					JOptionPane.showMessageDialog(null, "Cadastro Efetuado com sucesso");         //Não é essa a caixa que eu queria, vê se tu consegue por so a caixa da menssagem mesmo!
					setVisible(false);

				}		
			}


		}
				);

		contentPane.add(btnCadastrar);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setForeground(Color.ORANGE);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(10, 259, 36, 14);
		contentPane.add(lblId);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setForeground(Color.ORANGE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(10, 290, 36, 14);
		contentPane.add(lblSenha);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setForeground(Color.ORANGE);
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEndereo.setBounds(440, 106, 89, 23);
		contentPane.add(lblEndereo);

		JLabel lblDados = new JLabel("Dados Pessoais");
		lblDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblDados.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDados.setForeground(Color.ORANGE);
		lblDados.setBounds(26, 107, 217, 20);
		contentPane.add(lblDados);

		JLabel lblCidade = new JLabel("Cidade\r\n");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCidade.setForeground(Color.ORANGE);
		lblCidade.setBackground(Color.ORANGE);
		lblCidade.setBounds(276, 194, 96, 17);
		contentPane.add(lblCidade);

		JLabel lblLogradouro = new JLabel("Endere\u00E7o");
		lblLogradouro.setForeground(Color.ORANGE);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogradouro.setBounds(276, 227, 96, 17);
		contentPane.add(lblLogradouro);

		JLabel lblCEP = new JLabel("CEP");
		lblCEP.setForeground(Color.ORANGE);
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCEP.setBounds(276, 259, 96, 14);
		contentPane.add(lblCEP);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setForeground(Color.ORANGE);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumero.setBounds(276, 290, 96, 14);
		contentPane.add(lblNumero);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setForeground(Color.ORANGE);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComplemento.setBounds(276, 315, 96, 14);
		contentPane.add(lblComplemento);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setForeground(Color.ORANGE);
		lblNome.setBounds(10, 193, 36, 14);
		contentPane.add(lblNome);

		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIdade.setForeground(Color.ORANGE);
		lblIdade.setBounds(10, 228, 36, 14);
		contentPane.add(lblIdade);

		tf_CadID = new JTextField();
		tf_CadID.setBounds(56, 256, 109, 20);
		contentPane.add(tf_CadID);
		tf_CadID.setColumns(10);

		tf_Logradouro = new JTextField();
		tf_Logradouro.setBounds(379, 226, 217, 20);
		contentPane.add(tf_Logradouro);
		tf_Logradouro.setColumns(10);

		tf_CEP = new JTextField();
		tf_CEP.setBounds(379, 257, 150, 20);
		contentPane.add(tf_CEP);
		tf_CEP.setColumns(10);

		tf_Numero = new JTextField();
		tf_Numero.setBounds(379, 284, 86, 20);
		contentPane.add(tf_Numero);
		tf_Numero.setColumns(10);

		tf_Complemento = new JTextField();
		tf_Complemento.setBounds(379, 313, 217, 20);
		contentPane.add(tf_Complemento);
		tf_Complemento.setColumns(10);

		tf_Nome = new JTextField();
		tf_Nome.setBounds(56, 191, 177, 20);
		contentPane.add(tf_Nome);
		tf_Nome.setColumns(10);

		tf_IDADE = new JTextField();
		tf_IDADE.setBounds(56, 222, 61, 20);
		contentPane.add(tf_IDADE);
		tf_IDADE.setColumns(10);

		tf_Cidade = new JTextField();
		tf_Cidade.setBounds(379, 191, 217, 20);
		contentPane.add(tf_Cidade);
		tf_Cidade.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(56, 287, 109, 20);
		contentPane.add(passwordField);

		JLabel lblbastao4 = new JLabel("");
		lblbastao4.setIcon(new ImageIcon("images\\2.2.png"));
		lblbastao4.setBounds(0, 113, 61, 16);
		contentPane.add(lblbastao4);

		JLabel lblbastao2 = new JLabel("");
		lblbastao2.setIcon(new ImageIcon("images\\1.1.png"));
		lblbastao2.setBounds(205, 115, 96, 14);
		contentPane.add(lblbastao2);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("images\\equerda.png"));
		label_1.setBounds(342, 113, 88, 14);
		contentPane.add(label_1);

		JLabel lblbastao1 = new JLabel("");
		lblbastao1.setIcon(new ImageIcon("images\\direita.png"));
		lblbastao1.setBounds(526, 113, 90, 14);
		contentPane.add(lblbastao1);

		JLabel lblCoruja = new JLabel("");
		lblCoruja.setIcon(new ImageIcon("images\\topo.png"));
		lblCoruja.setBounds(208, 11, 224, 65);
		contentPane.add(lblCoruja);

		JLabel lblBackground = new JLabel("");
		lblBackground.setBackground(Color.GRAY);
		lblBackground.setIcon(new ImageIcon("images\\Fundo.jpg"));
		lblBackground.setBounds(0, 0, 626, 443);
		contentPane.add(lblBackground);


	}
	public Endereco cadastrarEndereco(String cidade, String logradouro, String numero, String cep, String complemento){
		Endereco endereco = new Endereco(cidade, logradouro, numero, cep, complemento);
		return endereco;

	}
	public boolean cadastrar(String nome, String idade, Endereco endereco, String id, String senha) {
		try {
			GemialidadesLoja.getInstance().inserirCliente(new Cliente( nome, idade, endereco,id, senha));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ClienteJaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} 

		return rootPaneCheckingEnabled;
	}
}
