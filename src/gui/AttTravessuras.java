package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Choice;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;

import negocios.classesBasicas.Produto;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;

import fachada.GemialidadesLoja;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AttTravessuras extends JFrame {
	private final JLabel label_5 = new JLabel("");
	private JTextField textNome;
	private JTextField textCodigo;
	private JTextField textPre;
	private JTextField textDes;
	private static GemialidadesLoja loja;
	private static Travessuras produto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttTravessuras frame = new AttTravessuras(loja, produto);
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
	public AttTravessuras(final GemialidadesLoja loja, Travessuras produto) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 551, 391);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNome.setForeground(Color.ORANGE);
		lblNome.setBounds(10, 38, 76, 24);
		getContentPane().add(lblNome);
		
		JLabel lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCodigo.setForeground(Color.ORANGE);
		lblCodigo.setBounds(10, 87, 76, 24);
		getContentPane().add(lblCodigo);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o: ");
		lblDescrio.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDescrio.setForeground(Color.ORANGE);
		lblDescrio.setBounds(10, 138, 76, 24);
		getContentPane().add(lblDescrio);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o: ");
		lblPreo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPreo.setForeground(Color.ORANGE);
		lblPreo.setBounds(282, 87, 76, 24);
		getContentPane().add(lblPreo);
		
		JLabel lblCensura = new JLabel("Censura: ");
		lblCensura.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCensura.setForeground(Color.ORANGE);
		lblCensura.setBounds(10, 194, 76, 24);
		getContentPane().add(lblCensura);
		
		textNome = new JTextField();
		textNome.setBounds(82, 39, 417, 24);
		textNome.setText(produto.getNome());
		getContentPane().add(textNome);
		textNome.setColumns(10);
		
		textCodigo = new JTextField();
		textCodigo.setColumns(10);
		textCodigo.setBounds(82, 88, 163, 24);
		getContentPane().add(textCodigo);
		textCodigo.setText(produto.getCodigo());
		
		textPre = new JTextField();
		textPre.setColumns(10);
		textPre.setBounds(336, 88, 163, 24);
		textPre.setText(String.valueOf(produto.getPreco()));
		getContentPane().add(textPre);
		
		textDes = new JTextField();
		textDes.setColumns(10);
		textDes.setBounds(82, 141, 417, 24);
		getContentPane().add(textDes);
		textDes.setText(produto.getDescricao());
		
		JLabel lblPericulosidade = new JLabel("Periculosidade");
		lblPericulosidade.setForeground(Color.ORANGE);
		lblPericulosidade.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPericulosidade.setBounds(175, 200, 109, 24);
		getContentPane().add(lblPericulosidade);
		
		final Choice choiceCensura = new Choice();
		for(int i = 0; i < 19; i++){
		choiceCensura.add(Integer.toString(i));
		}
		choiceCensura.setBounds(82, 198, 68, 20);
		getContentPane().add(choiceCensura);
		
		
		final Choice choicePeri = new Choice();
		for(int i = 3; i < 11; i++){
			choicePeri.add(Integer.toString(i));
		}
		choicePeri.setBounds(282, 204, 68, 20);
		getContentPane().add(choicePeri);
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizarTrav(textNome.getText(), textCodigo.getText(), textDes.getText(), Double.parseDouble(choicePeri.getSelectedItem()), Integer.parseInt(choiceCensura.getSelectedItem()), Double.parseDouble(textPre.getText()));
				if(rootPaneCheckingEnabled){
					JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
					
				}else{
					JOptionPane.showMessageDialog(null, "Ocorreu algum problema!");
				}
			}
		});
		btnAtualizar.setBounds(175, 286, 163, 24);
		getContentPane().add(btnAtualizar);
		label_5.setIcon(new ImageIcon("images\\Fundo.jpg"));
		label_5.setBounds(0, 0, 535, 362);
		getContentPane().add(label_5);
		}

	
	public boolean AtualizarTrav(String nome, String codigo, String descricao, double periculosidade, int censura, double preço){
		Produto produto = new Travessuras(nome, codigo, descricao, periculosidade, censura, preço);
		try {
			GemialidadesLoja.getInstance().atualizarProduto(codigo, produto);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return rootPaneCheckingEnabled;
	}
}
