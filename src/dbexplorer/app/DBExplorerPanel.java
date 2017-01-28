package dbexplorer.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RENT on 2017-01-28.
 */
public class DBExplorerPanel extends JPanel implements ActionListener{
    DataTable dataTable;
    ExplorerPanel explorerPanel;

    public DBExplorerPanel() {
        super(new BorderLayout());

        dataTable = new DataTable();
        //dataTable.fillTableWithDummyData("");

        explorerPanel = new ExplorerPanel();

        explorerPanel.tablesCombo.addActionListener(this);
        //explorerPanel.schemasCombo.addActionListener(this);


        add(explorerPanel, BorderLayout.LINE_START);
        add(dataTable, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> Combos = (JComboBox<String>) e.getSource();
        //System.out.println("klikniety " + Combos.getSelectedItem());
        dataTable.fillTable((String) Combos.getSelectedItem());
    }
}
