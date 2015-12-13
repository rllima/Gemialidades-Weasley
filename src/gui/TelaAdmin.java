package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.GemialidadesLoja;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JInternalFrame;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Endereco;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;

import java.awt.Panel;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Choice;

public class TelaAdmin extends JFrame {
    private static GemialidadesLoja loja;

	private JPanel jPanel1;
	private JTextField tf_RmvProdutoID;
	private JTextField tf_RmvClienteID;
	private JTextField tf_AttClienteID;
	private JTextField tf_AttProdID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdmin frame = new TelaAdmin();
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
	public TelaAdmin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 466);
		jPanel1 = new JPanel();
		jPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jPanel1);
		jPanel1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("images\\topo.png"));
		label.setBounds(205, 11, 214, 65);
		jPanel1.add(label);
		
		final Panel panel = new Panel();
		panel.setBounds(233, 100, 358, 235);
		jPanel1.add(panel);
		panel.setVisible(false);
		panel.setLayout(new CardLayout(0, 0));
		
		final Panel panel_Escolha = new Panel();
		panel_Escolha.setLayout(null);
		panel_Escolha.setBackground(new Color(222, 184, 135));
		panel.add(panel_Escolha, "name_84949386013763");
		
		final Panel panel_EscolhaA = new Panel();
		panel_EscolhaA.setLayout(null);
		panel_EscolhaA.setBackground(new Color(222, 184, 135));
		panel.add(panel_EscolhaA, "name_93495989090976");
		
		JButton button_1 = new JButton("Guloseimas");
		button_1.setBounds(11, 172, 164, 39);
		panel_EscolhaA.add(button_1);
		
		JButton button_2 = new JButton("Travessuras");
		button_2.setBounds(182, 172, 164, 39);
		panel_EscolhaA.add(button_2);
		
		JLabel label_19 = new JLabel("");
		label_19.setBounds(0, 0, 357, 235);
		panel_EscolhaA.add(label_19);
		
		
		
		final Panel panel_Trav = new Panel();
		panel_Trav.setLayout(null);
		panel_Trav.setBackground(new Color(222, 184, 135));
		panel.add(panel_Trav, "name_84949404525996");
		
		final TextField tf_NomeT = new TextField();
		tf_NomeT.setBounds(86, 10, 240, 22);
		panel_Trav.add(tf_NomeT);
		
		Label label_8 = new Label("Nome");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Arial", Font.BOLD, 13));
		label_8.setBounds(10, 10, 76, 24);
		panel_Trav.add(label_8);
		
		final TextField tf_CodigoT = new TextField();
		tf_CodigoT.setBounds(86, 49, 240, 22);
		panel_Trav.add(tf_CodigoT);
		
		Label label_9 = new Label("C\u00F3digo");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Arial", Font.BOLD, 13));
		label_9.setBounds(10, 47, 76, 24);
		panel_Trav.add(label_9);
		
		final TextField tf_DesT = new TextField();
		tf_DesT.setBounds(86, 86, 240, 22);
		panel_Trav.add(tf_DesT);
		
		Label label_10 = new Label("Descri\u00E7\u00E3o");
		label_10.setForeground(Color.WHITE);
		label_10.setFont(new Font("Arial", Font.BOLD, 13));
		label_10.setBounds(10, 84, 76, 24);
		panel_Trav.add(label_10);
		
		final TextField tf_PrecoT = new TextField();
		tf_PrecoT.setBounds(86, 124, 240, 22);
		panel_Trav.add(tf_PrecoT);
		
		final Choice tf_Censura = new Choice();
		
		for(int i = 0; i < 19; i++){
		tf_Censura.add(Integer.toString(i));
		}
		tf_Censura.setBounds(85, 161, 68, 22);
		panel_Trav.add(tf_Censura);
		
		final Choice tf_Peri = new Choice();
		for(int i = 3; i < 11; i++){
			tf_Peri.add(Integer.toString(i));
		}
		tf_Peri.setBounds(263, 161, 68, 20);
		panel_Trav.add(tf_Peri);
		
		Label label_11 = new Label("Pre\u00E7o");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Arial", Font.BOLD, 13));
		label_11.setBounds(10, 122, 76, 24);
		panel_Trav.add(label_11);
		
		Label label_Periculosidade = new Label("Periculosidade");
		label_Periculosidade.setForeground(Color.WHITE);
		label_Periculosidade.setFont(new Font("Arial", Font.BOLD, 13));
		label_Periculosidade.setBounds(159, 161, 101, 24);
		panel_Trav.add(label_Periculosidade);
		
		Label label_12 = new Label("Censura");
		label_12.setForeground(Color.WHITE);
		label_12.setFont(new Font("Arial", Font.BOLD, 13));
		label_12.setBounds(10, 159, 76, 24);
		panel_Trav.add(label_12);
		
		JButton button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarTrav(tf_NomeT.getText(), tf_CodigoT.getText(), tf_DesT.getText(), Double.parseDouble(tf_Peri.getSelectedItem()), Integer.parseInt(tf_Censura.getSelectedItem()), Double.parseDouble(tf_PrecoT.getText()));
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
			}
		});
		button.setBounds(127, 205, 104, 23);
		panel_Trav.add(button);
		
		JLabel label_13 = new JLabel("");
		label_13.setIcon(new ImageIcon("C:\\Users\\rlo\\git\\gemialidades-weasley1\\images\\Mapa-do-Maroto1.jpg"));
		label_13.setBounds(0, 0, 357, 235);
		panel_Trav.add(label_13);
		
		
		final Panel panel_CadastroGul = new Panel();
		panel.add(panel_CadastroGul, "name_84949424298320");
		panel_CadastroGul.setBackground(new Color(222, 184, 135));
		panel_CadastroGul.setLayout(null);
		
		JButton btnTravessuras = new JButton("Travessuras");
		btnTravessuras.setIcon(new ImageIcon("images\\botton.png"));
		btnTravessuras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				// Limpando
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				// Adicionando novo panel
				panel.add(panel_Trav);
				panel.repaint();
				panel.revalidate();
			}
		});
		JButton btnGuloseimas = new JButton("Guloseimas");
		btnGuloseimas.setIcon(new ImageIcon("images\\botton.png"));
		btnGuloseimas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				// Limpando
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				// Adicionando novo panel
				panel.add(panel_CadastroGul);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnGuloseimas.setBounds(11, 172, 164, 39);
		panel_Escolha.add(btnGuloseimas);
		
		btnTravessuras.setBounds(182, 172, 164, 39);
		panel_Escolha.add(btnTravessuras);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rlo\\git\\gemialidades-weasley1\\images\\Mapa-do-Maroto1.jpg"));
		lblNewLabel.setBounds(0, 0, 357, 235);
		panel_Escolha.add(lblNewLabel);
		final TextField tf_NomeCad = new TextField();
		tf_NomeCad.setBounds(86, 10, 240, 22);
		panel_CadastroGul.add(tf_NomeCad);
		
		Label label_1 = new Label("Nome");
		label_1.setBounds(10, 10, 76, 24);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Arial", Font.BOLD, 13));
		panel_CadastroGul.add(label_1);
		
		final TextField tf_CodCad = new TextField();
		tf_CodCad.setBounds(86, 49, 240, 22);
		panel_CadastroGul.add(tf_CodCad);
		
		Label label_2 = new Label("C\u00F3digo");
		label_2.setBounds(10, 47, 76, 24);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Arial", Font.BOLD, 13));
		panel_CadastroGul.add(label_2);
		
		final TextField tf_DescCad = new TextField();
		tf_DescCad.setBounds(86, 86, 240, 22);
		panel_CadastroGul.add(tf_DescCad);
		
		Label label_3 = new Label("Descri\u00E7\u00E3o");
		label_3.setBounds(10, 84, 76, 24);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Arial", Font.BOLD, 13));
		panel_CadastroGul.add(label_3);
		
		final TextField tf_PrecoCad = new TextField();
		tf_PrecoCad.setBounds(86, 124, 240, 22);
		panel_CadastroGul.add(tf_PrecoCad);
		
		final TextField tf_SaborCad = new TextField();
		tf_SaborCad.setBounds(85, 161, 240, 22);
		panel_CadastroGul.add(tf_SaborCad);
		
				Label label_4 = new Label("Pre\u00E7o");
				label_4.setBounds(10, 122, 76, 24);
				label_4.setForeground(Color.WHITE);
				label_4.setFont(new Font("Arial", Font.BOLD, 13));
				panel_CadastroGul.add(label_4);
				
				Label label_5 = new Label("Sabor");
				label_5.setForeground(Color.WHITE);
				label_5.setFont(new Font("Arial", Font.BOLD, 13));
				label_5.setBounds(10, 159, 76, 24);
				panel_CadastroGul.add(label_5);
				
				
				
				JButton btnCadastrarG = new JButton("Cadastrar");
				btnCadastrarG.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cadastrarGuloseima(tf_NomeCad.getText(), tf_CodCad.getText(), tf_DescCad.getText(), tf_SaborCad.getText(), Double.parseDouble(tf_PrecoCad.getText()));
					}
				});
				btnCadastrarG.setBounds(127, 205, 104, 23);
				panel_CadastroGul.add(btnCadastrarG);
				
				JLabel label_14 = new JLabel("");
				label_14.setIcon(new ImageIcon("C:\\Users\\rlo\\git\\gemialidades-weasley1\\images\\Mapa-do-Maroto1.jpg"));
				label_14.setBounds(0, 0, 357, 235);
				panel_CadastroGul.add(label_14);
		
		final Panel panel_AttProduto = new Panel();
		panel_AttProduto.setLayout(null);
		panel_AttProduto.setBackground(new Color(222, 184, 135));
		panel.add(panel_AttProduto, "name_84949443447977");
		
		tf_AttProdID = new JTextField();
		tf_AttProdID.setColumns(10);
		tf_AttProdID.setBounds(123, 100, 128, 20);
		panel_AttProduto.add(tf_AttProdID);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setFont(new Font("Arial", Font.PLAIN, 13));
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(91, 103, 22, 14);
		panel_AttProduto.add(lblId);
		
		JLabel lblDigiteAbaixoO_3 = new JLabel("Digite abaixo o ID do produto que deseja atualizar");
		lblDigiteAbaixoO_3.setForeground(new Color(255, 255, 255));
		lblDigiteAbaixoO_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDigiteAbaixoO_3.setBounds(31, 29, 301, 20);
		panel_AttProduto.add(lblDigiteAbaixoO_3);
		
		JButton btnAttProd = new JButton("Atualizar");
		btnAttProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//jfgjhfhfd
				
			}
		});
		btnAttProd.setBounds(141, 175, 89, 23);
		panel_AttProduto.add(btnAttProd);
		
		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon("C:\\Users\\rlo\\git\\gemialidades-weasley1\\images\\Mapa-do-Maroto1.jpg"));
		label_15.setBounds(0, 0, 357, 235);
		panel_AttProduto.add(label_15);
		final Panel panel_AttCliente = new Panel();
		panel_AttCliente.setLayout(null);
		panel_AttCliente.setBackground(new Color(222, 184, 135));
		panel.add(panel_AttCliente, "name_84949463829495");
		
		tf_AttClienteID = new JTextField();
		tf_AttClienteID.setColumns(10);
		tf_AttClienteID.setBounds(123, 100, 128, 20);
		panel_AttCliente.add(tf_AttClienteID);
		
		JLabel label_7 = new JLabel("ID");
		label_7.setForeground(new Color(255, 255, 255));
		label_7.setFont(new Font("Arial", Font.PLAIN, 13));
		label_7.setBounds(91, 103, 22, 14);
		panel_AttCliente.add(label_7);
		
		JLabel lblDigiteAbaixoO_2 = new JLabel("Digite abaixo o ID do cliente que deseja atualizar");
		lblDigiteAbaixoO_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDigiteAbaixoO_2.setForeground(new Color(255, 255, 255));
		lblDigiteAbaixoO_2.setBounds(31, 32, 301, 20);
		panel_AttCliente.add(lblDigiteAbaixoO_2);
		Endereco endereco = new Endereco("Hogsmeade", "Rua dos Alfeneiros","4","00000000", "Onde o vento faz a curva");
		try {
			GemialidadesLoja.getInstance().inserirCliente(new Cliente("Rodrigo", "15", endereco, "552", "552"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClienteJaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnAttClien = new JButton("Atualizar");
		btnAttClien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    Cliente cliente = null;
				try {
					cliente = GemialidadesLoja.getInstance().procurarCliente(tf_AttClienteID.getText());
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (ClienteNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (EmptyListException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				TelaAtualizarCliente tela = new TelaAtualizarCliente(loja, cliente);
				tela.setVisible(true);
			}
		});
		btnAttClien.setBounds(141, 175, 89, 23);
		panel_AttCliente.add(btnAttClien);
		
		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon("C:\\Users\\rlo\\git\\gemialidades-weasley1\\images\\Mapa-do-Maroto1.jpg"));
		label_16.setBounds(0, 0, 357, 235);
		panel_AttCliente.add(label_16);
		
		final Panel panel_RmvCliente = new Panel();
		panel_RmvCliente.setLayout(null);
		panel_RmvCliente.setBackground(new Color(222, 184, 135));
		panel.add(panel_RmvCliente, "name_84949484452251");
		
		tf_RmvClienteID = new JTextField();
		tf_RmvClienteID.setColumns(10);
		tf_RmvClienteID.setBounds(123, 100, 128, 20);
		panel_RmvCliente.add(tf_RmvClienteID);
		
		JLabel label_6 = new JLabel("ID");
		label_6.setFont(new Font("Arial", Font.PLAIN, 13));
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setBounds(91, 103, 22, 14);
		panel_RmvCliente.add(label_6);
		
		JLabel lblDigiteAbaixoO_1 = new JLabel("Digite abaixo o ID do cliente que deseja remover");
		lblDigiteAbaixoO_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDigiteAbaixoO_1.setForeground(new Color(255, 255, 255));
		lblDigiteAbaixoO_1.setBounds(31, 29, 301, 20);
		panel_RmvCliente.add(lblDigiteAbaixoO_1);
		
		JButton btnRemoverClien = new JButton("Remover");
		btnRemoverClien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removerCli(tf_RmvClienteID.getText());
				
			}
		});
		btnRemoverClien.setBounds(141, 175, 89, 23);
		panel_RmvCliente.add(btnRemoverClien);
		
		JLabel label_17 = new JLabel("");
		label_17.setIcon(new ImageIcon("C:\\Users\\rlo\\git\\gemialidades-weasley1\\images\\Mapa-do-Maroto1.jpg"));
		label_17.setBounds(0, 0, 357, 235);
		panel_RmvCliente.add(label_17);
		
		final Panel panel_RmvProd = new Panel();
		panel_RmvProd.setLayout(null);
		panel_RmvProd.setBackground(new Color(222, 184, 135));
		panel.add(panel_RmvProd, "name_84949505404467");
		
		tf_RmvProdutoID = new JTextField();
		tf_RmvProdutoID.setBounds(123, 100, 128, 20);
		panel_RmvProd.add(tf_RmvProdutoID);
		tf_RmvProdutoID.setColumns(10);
		
		JLabel lblNome = new JLabel("ID");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(91, 103, 22, 14);
		panel_RmvProd.add(lblNome);
		
		JLabel lblDigiteAbaixoO = new JLabel("Digite abaixo o ID do produto que deseja remover");
		lblDigiteAbaixoO.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDigiteAbaixoO.setForeground(new Color(255, 255, 255));
		lblDigiteAbaixoO.setBounds(31, 29, 301, 20);
		panel_RmvProd.add(lblDigiteAbaixoO);
		
		JButton btnRemoverProd = new JButton("Remover");
		btnRemoverProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removerProd(tf_RmvProdutoID.getText());
			}
		});
		btnRemoverProd.setBounds(141, 175, 89, 23);
		panel_RmvProd.add(btnRemoverProd);
		
		JLabel label_18 = new JLabel("");
		label_18.setIcon(new ImageIcon("C:\\Users\\rlo\\git\\gemialidades-weasley1\\images\\Mapa-do-Maroto1.jpg"));
		label_18.setBounds(0, 0, 357, 235);
		panel_RmvProd.add(label_18);
		
		JButton btnRemoverProdutos = new JButton("Remover Produtos");
		btnRemoverProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				// Limpando
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				// Adicionando novo panel
				panel.add(panel_RmvProd);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnRemoverProdutos.setBounds(10, 157, 180, 23);
		jPanel1.add(btnRemoverProdutos);
		
		JButton btnRemoverCliente = new JButton("Remover Cliente");
		btnRemoverCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				// Limpando
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				// Adicionando novo panel
				panel.add(panel_RmvCliente);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnRemoverCliente.setBounds(10, 190, 180, 23);
		jPanel1.add(btnRemoverCliente);
		
		JButton btnAtualizarCliente = new JButton("Atualizar Cliente");
		btnAtualizarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				// Limpando
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				// Adicionando novo panel
				panel.add(panel_AttCliente);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnAtualizarCliente.setBounds(10, 223, 180, 23);
		jPanel1.add(btnAtualizarCliente);
		
		JButton btnAtualizarProduto = new JButton("Atualizar Produto");
		btnAtualizarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				// Limpando
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				// Adicionando novo panel
				panel.add(panel_AttProduto);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnAtualizarProduto.setBounds(11, 257, 180, 23);
		jPanel1.add(btnAtualizarProduto);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBounds(10, 289, 180, 23);
		jPanel1.add(btnEstoque);
		
		JButton btnGerarRelatorios = new JButton("Gerar Relatorios");
		btnGerarRelatorios.setBounds(10, 321, 180, 23);
		jPanel1.add(btnGerarRelatorios);
		
		JButton btnCadastrarProdutos = new JButton("Cadastrar Produtos");
		btnCadastrarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				// Limpando
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				// Adicionando novo panel
				panel.add(panel_Escolha);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnCadastrarProdutos.setBounds(10, 123, 180, 23);
		jPanel1.add(btnCadastrarProdutos);
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 624, 428);
		lblBackground.setIcon(new ImageIcon("images\\Fundo.jpg"));
		jPanel1.add(lblBackground);
	}
	
	public void cadastrarGuloseima(String nome, String codigo, String descricao, String sabor, double preco) {
		Produto produto = new Guloseimas (nome, codigo, descricao, sabor, preco);
		try {
			GemialidadesLoja.getInstance().cadastrarProduto(produto);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoJaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	public void cadastrarTrav(String nome, String codigo, String descricao, double periculosidade, int censura, double preço){
		Produto produto = new Travessuras(nome, codigo, descricao, periculosidade, censura, preço);
		try {
			GemialidadesLoja.getInstance().cadastrarProduto(produto);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoJaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	public void removerProd(String codigo){
		try {
			GemialidadesLoja.getInstance().removerProduto(codigo);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());	
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());

		}
	}
	public void removerCli(String id){
		try {
			GemialidadesLoja.getInstance().removerCliente(id);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ClienteNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
