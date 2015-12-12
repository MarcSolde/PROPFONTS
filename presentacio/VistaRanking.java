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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VistaRanking extends SuperVista {

	private JPanel contentPane;
	private BestTime bt;
	private JButton btnBTAll;
	private JScrollPane scrollPane;
	private JTable table;
	private CtrlPresentacio cp;
	private JButton btnBTUser;
	private JButton btnBTTamKK;
	private JButton btnMostSolved;
	private JButton btnNewButton;
	private JSpinner spinner;
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
	private void atras() {
		this.hacerInvisible();
		//cp.llamarMenu();
	}
	 public void inicializarComponentes(){
		    
		 	inicializar_frameVista();
		 	frameVista.getContentPane().add(contentPane);
		 	
	}
	/**
	 * Create the frame.
	 */
	
	public VistaRanking(CtrlPresentacio ctrlPresentacio) {
		cp = ctrlPresentacio;
		setDefaultCloseOperation(frameVista.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		inicializarComponentes();
		
		btnBTAll = new JButton("Best Time");
		
		
		scrollPane = new JScrollPane();
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atras();
			}

			
		});
		
		btnBTUser = new JButton("Els Meus Best Time");
		btnBTUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//XSTUB METHOD
			}
		});
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(3, 3, 9, 1));
		
		btnBTTamKK = new JButton("Best Time del Tamany");
		btnBTTamKK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
			    model.setRowCount(0);
				//System.out.print("AAAAA");
				ArrayList<String> ar= new ArrayList<String>();
				int x = (Integer)spinner.getValue();
				ar = cp.obtener_BT_Tam(x);
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
		
		btnMostSolved = new JButton("Most Solved");
		
		btnNewButton = new JButton("Els Meu Most Solved");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAtras))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnMostSolved)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnBTUser)
										.addGap(47))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnBTTamKK)
										.addGap(32)))
								.addComponent(btnBTAll)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addComponent(btnNewButton)))
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAtras)
						.addComponent(btnBTAll))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBTUser)
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBTTamKK)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addComponent(btnMostSolved)
							.addGap(4)
							.addComponent(btnNewButton)))
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
	    //rdbtnMostSolved.addActionListener(this);
	    
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		btnBTAll.addActionListener(new ActionListener() {
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
