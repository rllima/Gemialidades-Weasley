package gui;

import java.awt.BorderLayout;
import negocios.classesBasicas.*;
import negocios.exceptions.EmptyListException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import dados.repositorios.Iterator;
import fachada.GemialidadesLoja;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Estoque extends JFrame {

	private JPanel contentPane;
	private static GemialidadesLoja loja;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque frame = new Estoque(loja);
					GemialidadesLoja.getInstance().cadastrarProduto(new Travessuras("Orelhas Extensíveis", "001","Saber de todos os Segredos",3.0, 8, 5.0));
					GemialidadesLoja.getInstance().cadastrarProduto(new Travessuras("Orelhas Extensíveis", "002","Saber de todos os Segredos",3.0, 8, 5.0));
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
	public Estoque(GemialidadesLoja loja) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 54, 453, 172);
		contentPane.add(scrollPane);
		
		final JList list = new JList();
		list.setBounds(103, 0, 457, -173);
		scrollPane.setViewportView(list);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dlm = new DefaultListModel();
				Iterator<Produto> itr = getIteratorProd();
				while(itr.hasNext()) {
					dlm.addElement(itr.next().getNome());
				}
			
				list.setModel(dlm);
			}
		});
		btnEstoque.setBounds(207, 237, 141, 23);
		contentPane.add(btnEstoque);
		
		JButton btnRelatrio = new JButton("Relat\u00F3rio");
		btnRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream rel = new FileOutputStream("RelatorioEstoque.txt");
					ObjectOutputStream rl = new ObjectOutputStream(rel);
					Iterator produto = GemialidadesLoja.getInstance().getIteratorProduto();     
	            while ( produto.hasNext()){
	                	rl.writeObject(produto.next().toString() + "\n");
	                }
	            
					rel.close();
					rl.close();
					JOptionPane.showMessageDialog(null, "Relatorio gerado com sucesso!");
					
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btnRelatrio.setBounds(207, 269, 141, 23);
		contentPane.add(btnRelatrio);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images\\Fundo.jpg"));
		lblNewLabel.setBounds(0, 0, 554, 303);
		contentPane.add(lblNewLabel);
		
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

}

