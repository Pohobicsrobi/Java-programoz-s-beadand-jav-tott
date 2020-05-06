import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Ujadat extends JDialog {
	private JTextField azonosito;
	private JTextField nev;
	private JTextField szulido;
	private JTextField nemzetiseg;
	private JTextField ranglistapont;
	private Adatbaziscsatlakozas adatbazis = new Adatbaziscsatlakozas();
		
	public Ujadat() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblazonosito = new JLabel("azonosit\u00F3:");
		lblazonosito.setBounds(38, 30, 77, 14);
		getContentPane().add(lblazonosito);
		
		JLabel lblnev = new JLabel("N\u00E9v:");
		lblnev.setBounds(38, 71, 77, 14);
		getContentPane().add(lblnev);
		
		JLabel lblszulido = new JLabel("Sz\u00FClet\u00E9si id\u0151:");
		lblszulido.setBounds(38, 105, 77, 14);
		getContentPane().add(lblszulido);
		
		JLabel lblnemzetiseg = new JLabel("nemzetis\u00E9g:");
		lblnemzetiseg.setBounds(38, 146, 77, 14);
		getContentPane().add(lblnemzetiseg);
		
		JLabel lblranglistapont = new JLabel("ranglistapont:");
		lblranglistapont.setBounds(38, 187, 77, 14);
		getContentPane().add(lblranglistapont);
		
		azonosito = new JTextField();
		azonosito.setBounds(161, 27, 86, 20);
		getContentPane().add(azonosito);
		azonosito.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(161, 65, 86, 20);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		szulido = new JTextField();
		szulido.setBounds(161, 102, 86, 20);
		getContentPane().add(szulido);
		szulido.setColumns(10);
		
		nemzetiseg = new JTextField();
		nemzetiseg.setBounds(161, 143, 86, 20);
		getContentPane().add(nemzetiseg);
		nemzetiseg.setColumns(10);
		
		ranglistapont = new JTextField();
		ranglistapont.setBounds(161, 184, 86, 20);
		getContentPane().add(ranglistapont);
		ranglistapont.setColumns(10);
		
				
		JButton btnBeszr = new JButton("Besz\u00FAr");
		btnBeszr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adatellenorzes c = new Adatellenorzes();
					if (c.goodInt(azonosito, "azonosito"))
					if (c.filled(nev, "nev"))
					if (c.jodatum(szulido, "szulido"))
					if (c.filled(nemzetiseg, "nemzetiseg"))
					if (c.goodInt(ranglistapont, "ranglistapont")) {
						adatbazis.Csatlakozas();
						adatbazis.ujteniszezo(mezo(azonosito), mezo(nev), mezo(szulido), mezo(nemzetiseg), mezo(ranglistapont));
						adatbazis.Szetcsatlakozas();
								}
			}
		});
		btnBeszr.setBounds(183, 227, 89, 23);
		getContentPane().add(btnBeszr);
	}
	
	public String mezo(JTextField jomezo){
		return jomezo.getText();
	
	}
}
