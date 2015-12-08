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

import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.exceptions.ProdutoJaExisteException;

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

public class TelaAdmin extends JFrame {
    private static GemialidadesLoja loja;

	private JPanel jPanel1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 466);
		jPanel1 = new JPanel();
		jPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jPanel1);
		jPanel1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("images\\topo.png"));
		label.setBounds(10, 11, 214, 65);
		jPanel1.add(label);
		
		final Panel panel = new Panel();
		panel.setBounds(233, 100, 358, 235);
		jPanel1.add(panel);
		panel.setVisible(false);
		panel.setLayout(null);
		
		final Panel panel_CadastroGul = new Panel();
		panel_CadastroGul.setBounds(0, 0, 358, 235);
		panel.add(panel_CadastroGul);
		panel_CadastroGul.setBackground(new Color(222, 184, 135));
		panel_CadastroGul.setLayout(null);
		
		final TextField tf_NomeCad = new TextField();
		tf_NomeCad.setBounds(86, 10, 240, 22);
		panel_CadastroGul.add(tf_NomeCad);
		
		Label label_1 = new Label("Nome");
		label_1.setBounds(29, 10, 51, 24);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Arial", Font.BOLD, 13));
		panel_CadastroGul.add(label_1);
		
		final TextField tf_CodCad = new TextField();
		tf_CodCad.setBounds(86, 49, 240, 22);
		panel_CadastroGul.add(tf_CodCad);
		
		Label label_2 = new Label("C\u00F3digo");
		label_2.setBounds(10, 47, 59, 24);
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
		label_4.setBounds(22, 122, 51, 24);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Arial", Font.BOLD, 13));
		panel_CadastroGul.add(label_4);
		
		Label label_5 = new Label("Sabor");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Arial", Font.BOLD, 13));
		label_5.setBounds(22, 159, 51, 24);
		panel_CadastroGul.add(label_5);
		
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarGuloseima(tf_NomeCad.getText(), tf_CodCad.getText(), tf_DescCad.getText(), tf_SaborCad.getText(), Double.parseDouble(tf_PrecoCad.getText()));
			}
		});
		btnCadastrar.setBounds(142, 205, 89, 23);
		panel_CadastroGul.add(btnCadastrar);
		Panel panel_AttCliente = new Panel();
		panel_AttCliente.setBounds(-10241, -10171, 358, 164);
		panel.add(panel_AttCliente);
		panel_AttCliente.setBackground(Color.CYAN);
		panel_AttCliente.setLayout(null);
		
		JButton btnRemoverProdutos = new JButton("Remover Produtos");
		btnRemoverProdutos.setBounds(10, 157, 180, 23);
		jPanel1.add(btnRemoverProdutos);
		
		JButton btnRemoverCliente = new JButton("Remover Cliente");
		btnRemoverCliente.setBounds(10, 191, 180, 23);
		jPanel1.add(btnRemoverCliente);
		
		JButton btnAtualizarCliente = new JButton("Atualizar Cliente");
		btnAtualizarCliente.setBounds(10, 225, 180, 23);
		jPanel1.add(btnAtualizarCliente);
		
		JButton btnNewButton = new JButton("Estoque");
		btnNewButton.setBounds(10, 259, 180, 23);
		jPanel1.add(btnNewButton);
		
		JButton btnGerarRelatorios = new JButton("Gerar Relatorios");
		btnGerarRelatorios.setBounds(10, 293, 180, 23);
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
				panel.add(panel_CadastroGul);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnCadastrarProdutos.setBounds(10, 123, 180, 23);
		jPanel1.add(btnCadastrarProdutos);
		
		
		JLabel lblBackground = new JLabel("New label");
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
}
