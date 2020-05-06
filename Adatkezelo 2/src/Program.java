import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Program extends JFrame {
	
	private JPanel kezdõablak;
	private Adatbaziscsatlakozas adatbazis = new Adatbaziscsatlakozas();
	private EmpTM adatok;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception kivetel) {
					kivetel.printStackTrace();
				}
			}
		});
	}

	public Program() {  //kezdõablak
		adatbazis.Regisztracio();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		kezdõablak = new JPanel();
		kezdõablak.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(kezdõablak);
		kezdõablak.setLayout(null);
		
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adatbazis.Csatlakozas();
				adatok = adatbazis.Adatolvasas();
				adatbazis.Szetcsatlakozas();
				Listazas lista = new Listazas(Program.this, adatok);
				lista.setVisible(true);			
			}
		});
		btnLista.setBounds(10, 11, 146, 23);
		kezdõablak.add(btnLista);
		
		JButton btnUjAdatsor = new JButton("\u00DAj adatsor");
		btnUjAdatsor.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent kivetel){
			Ujadat uj = new Ujadat();
			uj.setVisible(true);
			}	
		});
		btnUjAdatsor.setBounds(10, 45, 146, 23);
		kezdõablak.add(btnUjAdatsor);
		
		JButton btnTrls = new JButton("T\u00F6rl\u00E9s");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adatbazis.Csatlakozas();
				adatok = adatbazis.Adatolvasas();
				adatbazis.Szetcsatlakozas();
				Adattorles torol = new Adattorles(Program.this, adatok);
				torol.setVisible(true);	
			}
		});
		btnTrls.setBounds(10, 79, 146, 23);
		kezdõablak.add(btnTrls);

	JButton btnModositas = new JButton("Teniszez\u0151 m\u00F3dos\u00EDt\u00E1sa");
		btnModositas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adatbazis.Csatlakozas();
				adatok= adatbazis.Adatolvasas();
				adatbazis.Szetcsatlakozas();
				Modositas em = new Modositas(Program.this, adatok);
				em.setVisible(true);
			}
		});
		btnModositas.setBounds(10, 113, 146, 23);
		kezdõablak.add(btnModositas);	
		
		Object teniszezok[] = {"jel","azonosito", "nev", "szulido", "nemzetiseg", "ranglistapont"};
		adatok = new EmpTM(teniszezok, 0);
		
	}
	
}
