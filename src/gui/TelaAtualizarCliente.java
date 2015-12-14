
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
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EmptyListException;
import fachada.GemialidadesLoja;

public class TelaAtualizarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField tf_LogradouroA;
	private JTextField tf_CEPA;
	private JTextField tf_NumeroA;
	private JTextField tf_ComplementoA;
	private JPasswordField passwordFieldA;
	private static GemialidadesLoja loja;
	private JTextField tf_NomeA;
	private JTextField tf_IDADEA;
	private JTextField tf_CidadeA;
	private static Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAtualizarCliente frame = new TelaAtualizarCliente(loja, cliente);
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
	public TelaAtualizarCliente(GemialidadesLoja loja, final Cliente cliente) {
		this.loja = loja;
		this.cliente = cliente;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCadastrar = new JButton("Atualizar");
		btnCadastrar.setBounds(262, 374, 101, 30);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = cliente.getId();
				Endereco endereco = new Endereco(tf_CidadeA.getText(), tf_LogradouroA.getText(), tf_NumeroA.getText(), tf_CEPA.getText(), tf_ComplementoA.getText());
					atualizar(tf_NomeA.getText(), tf_IDADEA.getText(), endereco, id, passwordFieldA.getText());
					JOptionPane.showMessageDialog(null, "Atualização efetuada com sucesso");       
					setVisible(false);

			}}
				);

		contentPane.add(btnCadastrar);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setForeground(Color.ORANGE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(10, 259, 36, 14);
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

		tf_LogradouroA = new JTextField();
		tf_LogradouroA.setBounds(379, 226, 217, 20);
		contentPane.add(tf_LogradouroA);
		tf_LogradouroA.setColumns(10);
		tf_LogradouroA.setText(cliente.getEndereco().getLogradouro());

		tf_CEPA = new JTextField();
		tf_CEPA.setBounds(379, 257, 150, 20);
		contentPane.add(tf_CEPA);
		tf_CEPA.setColumns(10);
		tf_CEPA.setText(cliente.getEndereco().getCep());

		tf_NumeroA = new JTextField();
		tf_NumeroA.setBounds(379, 284, 86, 20);
		contentPane.add(tf_NumeroA);
		tf_NumeroA.setColumns(10);
		tf_NumeroA.setText(cliente.getEndereco().getNumero());

		tf_ComplementoA = new JTextField();
		tf_ComplementoA.setBounds(379, 313, 217, 20);
		contentPane.add(tf_ComplementoA);
		tf_ComplementoA.setColumns(10);
		tf_ComplementoA.setText(cliente.getEndereco().getComplemento());

		tf_CidadeA = new JTextField();
		tf_CidadeA.setBounds(379, 191, 217, 20);
		contentPane.add(tf_CidadeA);
		tf_CidadeA.setColumns(10);
		tf_CidadeA.setText(cliente.getEndereco().getCidade());
		
		tf_NomeA = new JTextField();
		tf_NomeA.setBounds(56, 191, 177, 20);
		contentPane.add(tf_NomeA);
		tf_NomeA.setColumns(10);
		tf_NomeA.setText(cliente.getNome());

		tf_IDADEA = new JTextField();
		tf_IDADEA.setBounds(56, 222, 61, 20);
		contentPane.add(tf_IDADEA);
		tf_IDADEA.setColumns(10);
		tf_IDADEA.setText(cliente.getIdade());


		passwordFieldA = new JPasswordField();
		passwordFieldA.setBounds(56, 257, 109, 20);
		contentPane.add(passwordFieldA);
		passwordFieldA.setText(cliente.getSenha());

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
	public boolean atualizar(String nome, String idade, Endereco endereco, String id, String senha) {
		Cliente cliente = new Cliente( nome, idade, endereco, id, senha);
		
		try {
			GemialidadesLoja.getInstance().atualizarCliente(id,cliente);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ClienteNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return rootPaneCheckingEnabled;
	}
}
