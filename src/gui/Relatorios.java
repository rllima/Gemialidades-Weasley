package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.repositorios.Iterator;
import fachada.GemialidadesLoja;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.EmptyListException;
import sun.security.action.GetIntegerAction;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

public class Relatorios extends JFrame {

	private JPanel contentPane;
	private final JLabel label = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios frame = new Relatorios();
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
	public Relatorios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 30, 506, 231);
		contentPane.add(scrollPane);
		
		final JList list = new JList();
		list.setBounds(24, 251, 506, -251);
		scrollPane.setViewportView(list);
		
		JButton btnEntregasPendentes = new JButton("Entregas Pendentes");
		btnEntregasPendentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dlm = new DefaultListModel();
				Iterator<Entrega> itr;
				try {
					itr = GemialidadesLoja.getInstance().getIteratorEntPendentes();
					while(itr.hasNext()) {
						dlm.addElement(itr.next());
					}
					FileOutputStream rel = new FileOutputStream("RelatorioProdutosPendetes.txt");
					ObjectOutputStream rl = new ObjectOutputStream(rel);
					   
	            while ( itr.hasNext()){
	                	rl.writeObject(itr.next().toString() + "\n");
	                }
	            
					rel.close();
					rl.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EmptyListException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				list.setModel(dlm);
			}
		});
		btnEntregasPendentes.setBounds(8, 272, 257, 23);
		contentPane.add(btnEntregasPendentes);
		
		JButton btnEntregasEnviadas = new JButton("Entregas Enviadas");
		btnEntregasEnviadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dlm = new DefaultListModel();
				Iterator<Entrega> itr;
				try {
					itr = GemialidadesLoja.getInstance().getIteratorEntEnviadas();
					while(itr.hasNext()) {
						dlm.addElement(itr.next());
					}
					FileOutputStream rel = new FileOutputStream("RelatorioProdutosEnviadas.txt");
					ObjectOutputStream rl = new ObjectOutputStream(rel);
					   
	            while ( itr.hasNext()){
	                	rl.writeObject(itr.next().toString() + "\n");
	                }
	            
					rel.close();
					rl.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EmptyListException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				list.setModel(dlm);
			}
		});
		btnEntregasEnviadas.setBounds(273, 272, 257, 23);
		contentPane.add(btnEntregasEnviadas);
		label.setIcon(new ImageIcon("images\\Fundo.jpg"));
		label.setBounds(0, 0, 540, 354);
		contentPane.add(label);
	}
}
