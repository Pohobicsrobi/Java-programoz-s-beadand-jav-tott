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


public class Adattorles extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private EmpTM adatok;
	private Adatellenorzes c = new Adatellenorzes();
	private Adatbaziscsatlakozas adatbazis = new Adatbaziscsatlakozas();
	
	public Adattorles(JFrame f, EmpTM beolvasottadat) {
		super(f, "Teniszezõk törlése", true);
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
		
		JButton btnTrls = new JButton("T\u00F6rl\u00E9s");
		btnTrls.setBounds(153, 302, 116, 23);
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, Jel=0, x=0;
				for(x = 0; x<adatok.getRowCount();x++)
					if ((boolean)adatok.getValueAt(x,0)) {db++; Jel=x;}
					if (db==0) c.uzenet("Nincs kijelölve törlendõ teniszezõ!", 0);
					if (db>1) c.uzenet("Több teniszezõ van kijelölve! (Egyszerre csak egy törölhetõ)", 0); 
					if (db==1) {
						String azonosito = adatok.getValueAt(Jel, 1).toString();
						adatbazis.Csatlakozas();
						adatbazis.teniszezotorles(azonosito);
						adatbazis.Szetcsatlakozas();
						adatok.removeRow(Jel);
						c.uzenet("A teniszezõ törölve",1);
					}
			}
		});
		contentPanel.add(btnTrls);
		//}
		
		
		

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
}
