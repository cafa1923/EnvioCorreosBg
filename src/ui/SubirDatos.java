package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import utilidades.Archivo;
import utilidades.ArchivoCc;
import utilidades.ArchivoSocketLabs;
import utilidades.ArchivoTarjeta;

import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubirDatos extends JFrame {

	private JPanel contentPane;
	private JTable tableResumen;
	private  JTextField txtRutaArchivos;
	private JRadioButton rdbtnTarjeta;
	private JRadioButton rdbtnCtaCte;
	private JRadioButton rdbtnSocket;
	private Archivo archivo;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubirDatos frame = new SubirDatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	static PropertyChangeListener event=new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("myProperty")) {
                System.out.println("La propiedad myProperty ha cambiado su valor a: " + evt.getNewValue());
            }
        }
    };
    
	class procesar extends Thread {		
		public void run() {
			if(rdbtnTarjeta.isSelected()) {
				archivo =new ArchivoTarjeta("","","");
				archivo.addPropertyChangeListener(event);
				
			}
			if(rdbtnCtaCte.isSelected()) {
				archivo = new ArchivoCc();
				archivo.addPropertyChangeListener(event);
			}
			if(rdbtnSocket.isSelected()) {
				archivo = new ArchivoSocketLabs();
				archivo.addPropertyChangeListener(event);
	
			}
			
		}
		
	}
	public SubirDatos() {
		//this.archivo = _archivo;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 517);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Config");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Licencia");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tipo Archivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 42);
		contentPane.add(panel);
		
		rdbtnTarjeta = new JRadioButton("Tarjetas");
		rdbtnTarjeta.setSelected(true);
		
		rdbtnCtaCte = new JRadioButton("Cuentas Corrientes");
		
		rdbtnSocket = new JRadioButton("SocketLabs");
		ButtonGroup grupoBotones = new ButtonGroup();
		grupoBotones.add(rdbtnTarjeta);
		grupoBotones.add(rdbtnSocket);
		grupoBotones.add(rdbtnCtaCte);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(rdbtnTarjeta)
					.addGap(38)
					.addComponent(rdbtnCtaCte)
					.addGap(28)
					.addComponent(rdbtnSocket)
					.addGap(73))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(rdbtnTarjeta, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addComponent(rdbtnCtaCte, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addComponent(rdbtnSocket, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Carga Archivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 56, 414, 49);
		contentPane.add(panel_1);
		
		JButton btnSelectArchivo = new JButton("...");
		txtRutaArchivos = new JTextField();
		txtRutaArchivos.setColumns(10);
		btnSelectArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();        
		        //FileFilter filtro = new FileNameExtensionFilter("Archivos Txt (.txt)", "txt"); 
		        //fileChooser.setFileFilter(filtro);
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        fileChooser.showOpenDialog(fileChooser);
		        txtRutaArchivos.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtRutaArchivos, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnSelectArchivo))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectArchivo)
						.addComponent(txtRutaArchivos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos Corte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 110, 414, 51);
		contentPane.add(panel_2);
		
		JComboBox comboCorte = new JComboBox();
		
		JComboBox comboMes = new JComboBox();
		comboMes.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
		
		JComboBox comboAnio = new JComboBox();
		comboAnio.setModel(new DefaultComboBoxModel(new String[] {"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboCorte, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(comboMes, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(comboAnio, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboCorte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Resumen ", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 206, 414, 239);
		contentPane.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{206, 2, 0};
		gbl_panel_3.rowHeights = new int[]{2, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panel_3.add(scrollPane, gbc_scrollPane);
		
		tableResumen = new JTable();
		tableResumen.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Corte", "Mes", "A\u00F1o", "Resumen", "Fallidos"
			}
		));
		GridBagConstraints gbc_tableResumen = new GridBagConstraints();
		gbc_tableResumen.insets = new Insets(0, 0, 0, 5);
		gbc_tableResumen.fill = GridBagConstraints.BOTH;
		gbc_tableResumen.gridx = 0;
		gbc_tableResumen.gridy = 1;
		panel_3.add(tableResumen, gbc_tableResumen);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(10, 172, 89, 23);
		contentPane.add(btnProcesar);
		
		JLabel lblMensaje = new JLabel("......");
		lblMensaje.setBounds(109, 181, 46, 14);
		contentPane.add(lblMensaje);
	}
}
