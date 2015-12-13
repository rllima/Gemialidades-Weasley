package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.EtchedBorder;

import fachada.GemialidadesLoja;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import dados.repositorios.Iterator;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Endereco;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import negocios.exceptions.ProdutoNaoEncontradoException;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private static String idCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal(idCliente);
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
	public TelaPrincipal(final String idCliente) {
		this.idCliente = idCliente;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 606, 109);
		contentPane.add(scrollPane);
		
		final JList<String> list_Produtos = new JList<String>();
		scrollPane.setViewportView(list_Produtos);
		list_Produtos.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 277, 606, 109);
		contentPane.add(scrollPane_1);

		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel<String> dlm = new DefaultListModel<String>();
				Iterator<Produto> itr = getIteratorProd();
				while(itr.hasNext()) {
					dlm.addElement(itr.next().getNome());
				}
				list_Produtos.setModel(dlm);
			}
		});
		
		final JList<String> list_Carrinho = new JList<String>();
		scrollPane_1.setViewportView(list_Carrinho);
		list_Carrinho.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JButton btnAddCarrinho = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho.addActionListener(new ActionListener() {
			DefaultListModel<String> dlmCarrinho = new DefaultListModel<String>();
			public void actionPerformed(ActionEvent e) {
				dlmCarrinho.addElement(list_Produtos.getSelectedValue());
				DefaultListModel<String> dlm = (DefaultListModel<String>) list_Produtos.getModel();
				dlm.removeElement(list_Produtos.getSelectedValue());
				list_Produtos.setModel(dlm);
				list_Carrinho.setModel(dlmCarrinho);
			}
		});
		btnAddCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddCarrinho.setBounds(239, 186, 148, 23);
		contentPane.add(btnAddCarrinho);
		
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int qtde = list_Carrinho.getModel().getSize();
				for(int i = 0; i < qtde; i++) {
					String nome = (String) list_Carrinho.getModel().getElementAt(i);
					String idProduto = procurarProdNome(nome).getCodigo();
					vender(idProduto, idCliente);
				}
				 DefaultListModel<String> listModel = (DefaultListModel<String>) list_Carrinho.getModel();
			     listModel.removeAllElements();
			     list_Carrinho.setModel(listModel);
			}
		});
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnFinalizarCompra.setBounds(247, 399, 133, 34);
		contentPane.add(btnFinalizarCompra);
		
		btnGet.setBounds(10, 405, 89, 23);
		contentPane.add(btnGet);
		
		JLabel background = new JLabel("");
		background.setBorder(new LineBorder(new Color(0, 0, 0)));
		background.setIcon(new ImageIcon("C:\\Users\\lfs\\git\\gemialidades-weasley\\images\\Fundo.jpg"));
		background.setBounds(0, 0, 626, 444);
		contentPane.add(background);
	}
	
	public Iterator<Produto> getIteratorProd() {
		Iterator<Produto> itr = null;
		try {
			itr = GemialidadesLoja.getInstance().getIteratorProduto();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return itr;
	}
	
	public Produto procurarProdNome(String nome) {
		Produto produto = null;
		try {
			produto = GemialidadesLoja.getInstance().procurarProdNome(nome);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return produto;
	}
	
	public void vender(String idProduto, String idCliente) {
		try {
			GemialidadesLoja.getInstance().vender(idProduto, idCliente);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EntregaJaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EntregaNaoEncontradaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ClienteNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
