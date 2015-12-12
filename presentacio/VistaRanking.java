package presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

import domini.classes.*;
import domini.controladores.CtrlPresentacio;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class VistaRanking extends SuperVista {

	private JPanel contentPane;
	private BestTime bt;
	private JButton btnOk;
	private JScrollPane scrollPane;
	private JTable table;
	private CtrlPresentacio cp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//BestTime BT = new BestTime();
					//BT.setValue(1, "S", 4);
					//BT.setValue(2, "S", 4);
					//BT.setValue(3, "S", 4);
					//VistaRanking frame = new VistaRanking( );
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public void inicializarComponentes(){
		    
		 	inicializar_frameVista();
		 	frameVista.add(contentPane);
		 	
	}
	/**
	 * Create the frame.
	 */
	
	public VistaRanking(CtrlPresentacio ctrlPresentacio) {
		cp = ctrlPresentacio;
		setDefaultCloseOperation(frameVista.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		inicializarComponentes();
		
		btnOk = new JButton("OK");
		
		
		scrollPane = new JScrollPane();
		
		JRadioButton rdbtnBestTime = new JRadioButton("Best Time");
		
		JRadioButton rdbtnMostSolved = new JRadioButton("Most Solved");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnBestTime)
							.addGap(18)
							.addComponent(rdbtnMostSolved)
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
							.addComponent(btnOk)
							.addGap(114))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(206, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(rdbtnBestTime)
						.addComponent(rdbtnMostSolved))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tamany KK", "Temps(s)", "Usuari"
			}
		));
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnBestTime);
	    group.add(rdbtnMostSolved);
	    //rdbtnMostSolved.addActionListener(this);
	    
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
			    model.setRowCount(0);
				//System.out.print("AAAAA");
				ArrayList<String> ar= new ArrayList<String>();
				ar = cp.obtener_BT();
				System.out.println("SIZE: "+ar.size());
				for (int i = 0; i < ar.size(); i+= 3) {
					String s = new String();
					String s1 = new String();
					String s2 = new String();
					s = ar.get(i);
					s1 = ar.get(i+1);
					s2 = ar.get(i+2);
					//DefaultTableModel model = (DefaultTableModel) table.getModel();
			        model.addRow(new Object[]{s, s1, s2});
			        
				}
			}
		});
	}
}
