package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import fachada.GemialidadesLoja;

import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AttGuloseima extends JFrame {
	
	private static Guloseimas produto;
	private static GemialidadesLoja loja;
	private JTextField tfNome;
	private JTextField tf_Descricao;
	private JTextField tfPreco;
	private JTextField tfSabor;
	private JTextField tfCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttGuloseima frame = new AttGuloseima(loja, produto);
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
	public AttGuloseima(GemialidadesLoja loja,  final Produto produto) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 357);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCodigo.setForeground(Color.ORANGE);
		lblCodigo.setBounds(30, 94, 74, 25);
		getContentPane().add(lblCodigo);
		
		JLabel label = new JLabel("Nome:");
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		label.setBounds(30, 35, 74, 25);
		getContentPane().add(label);
		
		tfNome = new JTextField();
		tfNome.setBounds(95, 36, 386, 25);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		tfNome.setText(produto.getNome());
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o: ");
		lblDescrio.setForeground(Color.ORANGE);
		lblDescrio.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDescrio.setBounds(30, 151, 74, 25);
		getContentPane().add(lblDescrio);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o: ");
		lblPreo.setForeground(Color.ORANGE);
		lblPreo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPreo.setBounds(202, 94, 74, 25);
		getContentPane().add(lblPreo);
		
		JLabel lblSabor = new JLabel("Sabor: ");
		lblSabor.setForeground(Color.ORANGE);
		lblSabor.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSabor.setBounds(30, 207, 74, 25);
		getContentPane().add(lblSabor);
		
		tf_Descricao = new JTextField();
		tf_Descricao.setColumns(10);
		tf_Descricao.setBounds(95, 152, 386, 25);
		tf_Descricao.setText(produto.getDescricao());
		getContentPane().add(tf_Descricao);
		
		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBounds(251, 95, 86, 25);
		tfPreco.setText(String.valueOf(produto.getPreco()));
		getContentPane().add(tfPreco);
		
		tfSabor = new JTextField();
		tfSabor.setColumns(10);
		tfSabor.setBounds(95, 208, 386, 25);
		tfSabor.setText((((Guloseimas) produto).getSabor()));
		getContentPane().add(tfSabor);
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(95, 94, 86, 25);
		tfCodigo.setText(produto.getCodigo());
		getContentPane().add(tfCodigo);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizarGuloseima(tfNome.getText(), tfCodigo.getText(), tf_Descricao.getText(), tfSabor.getText(), Double.parseDouble(tfPreco.getText()));
				if(rootPaneCheckingEnabled){
					JOptionPane.showMessageDialog(null,"Atualização efetuado com sucesso!");
				}
				else{
					JOptionPane.showMessageDialog(null,"Algo deu errado!");
				}
			}
		});
		btnAtualizar.setBounds(206, 270, 131, 25);
		getContentPane().add(btnAtualizar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images\\Fundo.jpg"));
		lblNewLabel.setBounds(0, 0, 491, 328);
		getContentPane().add(lblNewLabel);
		

	}
	public boolean AtualizarGuloseima(String nome, String codigo, String descricao, String sabor, double preco) {
		Produto produto = new Guloseimas (nome, codigo, descricao, sabor, preco);
		try {
			GemialidadesLoja.getInstance().atualizarProduto(produto.getCodigo(), produto);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		return rootPaneCheckingEnabled;
	}
}
