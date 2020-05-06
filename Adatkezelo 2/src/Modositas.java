import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;


public class Modositas extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private EmpTM adatok;
	private Adatellenorzes c = new Adatellenorzes();
	private Adatbaziscsatlakozas adatbazis = new Adatbaziscsatlakozas();
	private JTextField azonosito;
	private JTextField nev;
	private JTextField szulido;
	private JTextField nemzetiseg;
	private JTextField ranglistapont;
	private JButton btnmodositas;
	
	public Modositas(JFrame f, EmpTM beolvasottadat) {
		super(f, "Teniszezõk módosítása", true);
		adatok = beolvasottadat;
		
		setBounds(100, 100, 679, 405); 
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		//{		
		JButton btnBezar2 = new JButton("Bez\u00E1r");
		btnBezar2.setBounds(390, 302, 89, 23);
		btnBezar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		contentPanel.add(btnBezar2);	
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 643, 158);
		contentPanel.add(scrollPane);
				
		table = new JTable(adatok);
		scrollPane.setViewportView(table);
		
		btnmodositas = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnmodositas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, Jel=0, x=0;
				for(x = 0; x<adatok.getRowCount();x++)
					if ((boolean)adatok.getValueAt(x,0)) {db++; Jel=x;}
					if (db==0) c.uzenet("Nincs kijelölve módosítandó teniszezõ!", 0);
					if (db>1) c.uzenet("Több teniszezõ van kijelölve! (Egyszerre csak egy módosíthat)", 0); 
					if (db==1) {
						if (kitoltott_mezok_szama() >0) {
							boolean ok= true;
							if (c.filled(azonosito)) ok= c.goodInt(azonosito,"Azonosító");
							if (ok && c.filled(ranglistapont)) ok = c.goodInt(ranglistapont, "Közösköltség");
							if(ok) {
								String mkod = adatok.getValueAt(Jel, 1).toString();
								adatbazis.Csatlakozas();
								if(c.filled(nev)) adatbazis.teniszezomodositas(mkod, "nev", c.mezoellenorzes(nev));
								if(c.filled(szulido)) adatbazis.teniszezomodositas(mkod, "szulido", c.mezoellenorzes(szulido));
								if(c.filled(nemzetiseg)) adatbazis.teniszezomodositas(mkod, "nemzetiseg", c.mezoellenorzes(nemzetiseg));
								if(c.filled(ranglistapont)) adatbazis.teniszezomodositas(mkod, "ranglistapont", c.mezoellenorzes(ranglistapont));
								if(c.filled(azonosito)) adatbazis.teniszezomodositas(mkod, "azonosito", c.mezoellenorzes(azonosito));
								adatbazis.Szetcsatlakozas();
								
								if (c.filled(azonosito)) adatok.setValueAt(c.stringToInt(c.mezoellenorzes(azonosito)), Jel, 1);
								if (c.filled(nev)) adatok.setValueAt(c.mezoellenorzes(nev), Jel, 2);
								if (c.filled(szulido)) adatok.setValueAt(c.mezoellenorzes(szulido), Jel, 3);
								if (c.filled(nemzetiseg)) adatok.setValueAt(c.mezoellenorzes(nemzetiseg), Jel, 4);
								if (c.filled(ranglistapont)) adatok.setValueAt(c.mezoellenorzes(ranglistapont), Jel, 5);
								c.uzenet("A teniszezõ Módosítva",1);
								Mezokitorles(Jel);
							}
							else {
								c.uzenet("Nincs kitöltve egy módosító mezezõ sem!",0);
							}
						}
					}
			}
		});
		contentPanel.add(btnmodositas);
		btnmodositas.setBounds(153, 302, 116, 23);
		
		azonosito = new JTextField();
		azonosito.setBounds(78, 225, 68, 20);
		contentPanel.add(azonosito);
		azonosito.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(153, 225, 124, 20);
		contentPanel.add(nev);
		nev.setColumns(10);
		
		szulido = new JTextField();
		szulido.setBounds(287, 225, 139, 20);
		contentPanel.add(szulido);
		szulido.setColumns(10);
		
		nemzetiseg = new JTextField();
		nemzetiseg.setBounds(436, 225, 128, 20);
		contentPanel.add(nemzetiseg);
		nemzetiseg.setColumns(10);
		
		ranglistapont = new JTextField();
		ranglistapont.setBounds(574, 225, 68, 20);
		contentPanel.add(ranglistapont);
		ranglistapont.setColumns(10);

			TableColumn oszlop = null;
			for (int i=0; i<6; i++){
				oszlop = table.getColumnModel().getColumn(i);
				if (i==0 || i==1 || i==5)oszlop.setPreferredWidth(30);
				else {oszlop.setPreferredWidth(100);
			}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> trs =	(TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
}
			
}
	public int kitoltott_mezok_szama() {
		int szamlalo=0;
		if (c.filled(azonosito)) szamlalo++;
		if (c.filled(nev)) szamlalo++;
		if (c.filled(szulido)) szamlalo++;
		if (c.filled(nemzetiseg)) szamlalo++;
		if (c.filled(ranglistapont)) szamlalo++;
		return szamlalo;
	}	
	
	public void Mezokitorles (int i){
		azonosito.setText("");
		nev.setText("");
		szulido.setText("");
		nemzetiseg.setText("");
		ranglistapont.setText("");
		adatok.setValueAt(false, i, 0);
	}
}
