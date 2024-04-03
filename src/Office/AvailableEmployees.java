package Office;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;


public class AvailableEmployees extends JFrame {

    GridBagLayout gbl = new GridBagLayout();
    BorderLayout bl = new BorderLayout();
    JPanel panel = new JPanel();
    public InfoPanel infoPanel;
    SalesPanel motherPanel;

    private final ItemContainer baseItemContainer;
    private final String officeName;
    private final Class<?> employeeClass;


    public AvailableEmployees(ItemContainer ic, ArrayList<Employee> employees, SalesPanel motherPanel) {
        baseItemContainer = ic;
        officeName = ic.getOfficeName();
        if(officeName.equals("Sales")) {employeeClass = SalesEmployee.class;}
        else if (officeName.equals("Accounting")) {employeeClass = AccountingEmployee.class;}
        else {employeeClass = HREmployee.class;}

        this.motherPanel = motherPanel;

        setLayout(bl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        getContentPane().setBackground(Color.BLACK);

        panel.setLayout(gbl);
        createAndShowUI(employees);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
        revalidate();
        pack();
        setLocation((int) (ic.getLocationOnScreen().getX()-getWidth()-2), (int) ic.getLocationOnScreen().getY());
    }

    public void createAndShowUI(ArrayList<Employee> employees) {
        //copy the same format as the way you lay out panels for managePanel
        GridBagConstraints c = new GridBagConstraints();
        panel.setBackground(Color.lightGray);

        //Close Button
        JButton closeButton = new JButton("X");
        //ActionListener
        closeButton.addActionListener(e -> disposeAll());
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.8;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        panel.add(closeButton, c);

        c.gridx = 1;
        JLabel titleLabel = new JLabel("Available Employees");
        panel.add(titleLabel, c);


        //Creating the list of employees
        c.insets = new Insets(0,2,2,2);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        //c.insets = new Insets(2, 0, 2, 0);
        c.gridy = 1; //edit this
        c.gridx = 0;
        c.gridwidth = 2;

        boolean noEmployeesOfClass = true;
        for (int i = 0; i < employees.size(); i++) {
            if (employeeClass.isInstance(employees.get(i))) {
                noEmployeesOfClass = false;
                c.gridy++;
                ItemContainer tempIc = new ItemContainer();
                tempIc.fillContainer(employees.get(i), 0);

                //add button listeners
                tempIc.employButton.addActionListener(e -> {
                    motherPanel.fillSlot(baseItemContainer, tempIc);
                    disposeAll();
                });

                tempIc.infoButton.addActionListener(e -> {
                    if (infoPanel != null) {
                        infoPanel.safeDispose();
                        infoPanel = null;
                    }
                    infoPanel = new InfoPanel(tempIc);
                    infoPanel.availEmployPanel = this;
                });
                panel.add(tempIc, c);
            }
        }
        if(noEmployeesOfClass) {
            c.gridy++;
            JPanel noEmployeesPanel= new JPanel(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();
            g.fill = GridBagConstraints.BOTH;
            JLabel message = new JLabel("No " + officeName + " Employees");
            message.setHorizontalAlignment(SwingConstants.CENTER);
            noEmployeesPanel.setPreferredSize(new Dimension(240,60));
            noEmployeesPanel.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
            noEmployeesPanel.add(message, g);
            panel.add(noEmployeesPanel, c);
        }

        panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));


    }

    public void disposeAll(){
        System.out.println("Disposing AE Panel");
        if(infoPanel != null)
            infoPanel.dispose();
        dispose();
    }
}
