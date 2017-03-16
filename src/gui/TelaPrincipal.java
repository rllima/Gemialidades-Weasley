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
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;
import java.awt.CardLayout;
import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import java.awt.Label;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;

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
		setBounds(100, 100, 622, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel_Principal = new JPanel();
		panel_Principal.setBounds(0, 0, 626, 444);
		contentPane.add(panel_Principal);
		panel_Principal.setLayout(new CardLayout(0, 0));
		
		final JPanel panel_Inicio = new JPanel();
		panel_Principal.add(panel_Inicio, "name_8389093730324");
		panel_Inicio.setLayout(null);
		
		final JPanel panel_Pedidos = new JPanel();
		panel_Principal.add(panel_Pedidos, "name_8874782706953");
		panel_Pedidos.setLayout(null);
		
		final JList<String> listMeusPedidos = new JList<String>();
		listMeusPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMeusPedidos.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		listMeusPedidos.setBounds(80, 98, 465, 119);
		panel_Pedidos.add(listMeusPedidos);
		
		final Cliente cliente = procurarCliente(idCliente);
		
		JButton btnMeusPedidos = new JButton("Meus Pedidos");
		btnMeusPedidos.setBorder(UIManager.getBorder("ToggleButton.border"));
		btnMeusPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Principal.setVisible(true);
				// Limpando
				panel_Principal.removeAll();
				panel_Principal.repaint();
				panel_Principal.revalidate();
				// Adicionando novo panel
				panel_Principal.add(panel_Pedidos);
				panel_Principal.repaint();
				panel_Principal.revalidate();
				
				DefaultListModel<String> dlm = new DefaultListModel<String>();
				String[] entregas = cliente.getEntregas();
				for(int i = 0; i < entregas.length; i++) {
					dlm.addElement(entregas[i]);
				}
				listMeusPedidos.setModel(dlm);
				
			}
		});
		btnMeusPedidos.setBounds(36, 234, 129, 44);
		panel_Inicio.add(btnMeusPedidos);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_Principal.setVisible(true);
				// Limpando
				panel_Principal.removeAll();
				panel_Principal.repaint();
				panel_Principal.revalidate();
				// Adicionando novo panel
				panel_Principal.add(panel_Inicio);
				panel_Principal.repaint();
				panel_Principal.revalidate();
			}
		});
		btnNewButton.setBounds(10, 405, 73, 28);
		panel_Pedidos.add(btnNewButton);
		
		final Label lblDetalhes = new Label("");
		lblDetalhes.setAlignment(Label.CENTER);
		lblDetalhes.setBackground(Color.WHITE);
		lblDetalhes.setBounds(150, 299, 325, 126);
		panel_Pedidos.add(lblDetalhes);
		
		Label label = new Label("Escolha um item abaixo e clique em \r\n\"Exibir Detalhes\" para ver os detalhes \r\nde sua entrega.");
		label.setBackground(Color.ORANGE);
		label.setBounds(80, 70, 465, 22);
		panel_Pedidos.add(label);
		
		final JPanel panel_Compras = new JPanel();
		panel_Principal.add(panel_Compras, "name_8363944213279");
		panel_Compras.setLayout(null);
		
		JButton btnAcessarLoja = new JButton("Acessar Loja");
		btnAcessarLoja.setBorder(UIManager.getBorder("ToggleButton.border"));
		btnAcessarLoja.setBounds(36, 158, 129, 44);
		panel_Inicio.add(btnAcessarLoja);
		
		JButton btnVerPedido = new JButton("Exibir Detalhes");
		btnVerPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entrega = listMeusPedidos.getSelectedValue();
				String isEnviada;
				if(isEnviada(entrega)) {
					isEnviada = "Enviada";
				} else {
					isEnviada = "Ainda não enviada";
				}
				Entrega aux = procurarEntrega(entrega);
				
				String detalhes = "\n\n Status da entrega: " + isEnviada;
				
				lblDetalhes.setText(detalhes);
			}
		});
		btnVerPedido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVerPedido.setBounds(246, 240, 133, 34);
		panel_Pedidos.add(btnVerPedido);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/topo.png")));
		label_1.setBounds(203, 11, 220, 53);
		panel_Pedidos.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/equerda.png")));
		label_2.setBounds(108, 250, 133, 14);
		panel_Pedidos.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/direita.png")));
		label_3.setBounds(425, 250, 145, 14);
		panel_Pedidos.add(label_3);
		
		JLabel bg_Pedidos = new JLabel("");
		bg_Pedidos.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Fundo.jpg")));
		bg_Pedidos.setBorder(new LineBorder(new Color(0, 0, 0)));
		bg_Pedidos.setBounds(0, 0, 626, 444);
		panel_Pedidos.add(bg_Pedidos);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/logo.png")));
		lblNewLabel.setBounds(181, 103, 382, 262);
		panel_Inicio.add(lblNewLabel);
		
		JLabel gb = new JLabel("");
		gb.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/juro.gif")));
		gb.setBorder(null);
		gb.setBounds(0, 0, 605, 444);
		panel_Inicio.add(gb);
		btnAcessarLoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Principal.setVisible(true);
				// Limpando
				panel_Principal.removeAll();
				panel_Principal.repaint();
				panel_Principal.revalidate();
				// Adicionando novo panel
				panel_Principal.add(panel_Compras);
				panel_Principal.repaint();
				panel_Principal.revalidate();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 286, 604, 107);
		panel_Compras.add(scrollPane);	
		
		final JList<String> list_Produtos = new JList<String>();
		scrollPane.setViewportView(list_Produtos);
		list_Produtos.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 130, 604, 107);
		panel_Compras.add(scrollPane_1);
		
		final JList<String> list_Carrinho = new JList<String>();
		scrollPane_1.setViewportView(list_Carrinho);
		list_Carrinho.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JButton btnAddCarrinho = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho.setBounds(237, 241, 148, 34);
		panel_Compras.add(btnAddCarrinho);
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
		
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setBounds(237, 399, 148, 34);
		panel_Compras.add(btnFinalizarCompra);
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
		
				JButton btnGet = new JButton("Carregar Produtos");
				btnGet.setBounds(10, 96, 148, 23);
				panel_Compras.add(btnGet);
				
				JButton btnVoltar = new JButton("Voltar");
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel_Principal.setVisible(true);
						// Limpando
						panel_Principal.removeAll();
						panel_Principal.repaint();
						panel_Principal.revalidate();
						// Adicionando novo panel
						panel_Principal.add(panel_Inicio);
						panel_Principal.repaint();
						panel_Principal.revalidate();
					}
				});
				btnVoltar.setBounds(10, 11, 89, 23);
				panel_Compras.add(btnVoltar);
				
				JLabel label_4 = new JLabel("");
				label_4.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/topo.png")));
				label_4.setBounds(208, 11, 207, 74);
				panel_Compras.add(label_4);
				
				JLabel label_5 = new JLabel("");
				label_5.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/equerda.png")));
				label_5.setBounds(129, 248, 99, 14);
				panel_Compras.add(label_5);
				
				JLabel label_6 = new JLabel("");
				label_6.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/direita.png")));
				label_6.setBounds(395, 248, 99, 14);
				panel_Compras.add(label_6);
				
				JLabel label_7 = new JLabel("");
				label_7.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/equerda.png")));
				label_7.setBounds(128, 409, 99, 14);
				panel_Compras.add(label_7);
				
				JLabel label_8 = new JLabel("");
				label_8.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/direita.png")));
				label_8.setBounds(395, 409, 99, 14);
				panel_Compras.add(label_8);
				
				JLabel bg_Compras = new JLabel("");
				bg_Compras.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Fundo.jpg")));
				bg_Compras.setBorder(new LineBorder(new Color(0, 0, 0)));
				bg_Compras.setBounds(0, 0, 604, 444);
				panel_Compras.add(bg_Compras);
				
				JLabel background = new JLabel("");
				panel_Principal.add(background, "name_9518700264771");
				background.setBorder(new LineBorder(new Color(0, 0, 0)));
				background.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Fundo.jpg")));
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
	
	public Iterator<Entrega> getIteratorEntPendentes() {
		Iterator<Entrega> itr = null;
		try {
			itr = GemialidadesLoja.getInstance().getIteratorEntPendentes();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return itr;
	}
	
	public Iterator<Entrega> getIteratorEntEnviadas() {
		Iterator<Entrega> itr = null;
		try {
			itr = GemialidadesLoja.getInstance().getIteratorEntEnviadas();
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
	
	public Cliente procurarCliente(String id) {
		Cliente resposta = null;
		try {
			resposta = GemialidadesLoja.getInstance().procurarCliente(id);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ClienteNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return resposta;
	}
	
	public Produto procurarProduto(String id) {
		Produto resposta = null;
		try {
			resposta = GemialidadesLoja.getInstance().procurarProduto(id);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return resposta;
	}
	
	public Entrega procurarEntrega(String id) {
		Entrega resposta = null;
		try {
			resposta = GemialidadesLoja.getInstance().procurarEntrega(id);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EntregaNaoEncontradaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return resposta;
	}
	
	public boolean isEnviada(String entregaId) {
		Iterator<Entrega> itr = this.getIteratorEntEnviadas();
		while(itr.hasNext()) {
			if(itr.next().getId().equals(entregaId)) {
				return true;
			}
		}
		return false;
	}
}
