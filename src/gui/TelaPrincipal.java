package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
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
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 606, 109);
		contentPane.add(scrollPane);
		
		final JList list_Produtos = new JList();
		scrollPane.setViewportView(list_Produtos);
		list_Produtos.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 277, 606, 109);
		contentPane.add(scrollPane_1);
		
		final JList list_Carrinho = new JList();
		scrollPane_1.setViewportView(list_Carrinho);
		list_Carrinho.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnFinalizarCompra.setBounds(247, 399, 133, 34);
		contentPane.add(btnFinalizarCompra);
		
		JButton btnAddCarrinho = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho.addActionListener(new ActionListener() {
			DefaultListModel dlm2 = new DefaultListModel();
			public void actionPerformed(ActionEvent e) {
				dlm2.addElement(list_Produtos.getSelectedValue());
				list_Carrinho.setModel(dlm2);
			}
		});
		btnAddCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddCarrinho.setBounds(239, 186, 148, 23);
		contentPane.add(btnAddCarrinho);
		
		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dlm = new DefaultListModel();
				// Percorrer o rep Produtos e adicionar um a um na lista;
				for(int i = 0; i < 110; i++) {
					dlm.addElement("Produto "+i);
				}
				list_Produtos.setModel(dlm);
			}
		});
		btnGet.setBounds(10, 405, 89, 23);
		contentPane.add(btnGet);
		
		JLabel background = new JLabel("");
		background.setBorder(new LineBorder(new Color(0, 0, 0)));
		background.setIcon(new ImageIcon("C:\\Users\\lfs\\git\\gemialidades-weasley\\images\\Fundo.jpg"));
		background.setBounds(0, 0, 626, 444);
		contentPane.add(background);
	}
}
