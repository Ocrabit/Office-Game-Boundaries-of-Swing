package Office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Main.GamePanel;


public class SalesPanel extends JPanel{
    //Layout Managers
    GridBagLayout layout = new GridBagLayout();

    //Listeners
    MousePanelClicked panelClicked;

    //Daddy parent panel
    GamePanel gamePanel;

    //Two main panels
    protected JPanel hirePanel = new JPanel();
    protected JPanel managePanel = new JPanel();

    //Office.ItemContainer Objects
    private final ItemContainer sampleIC = new ItemContainer();
    protected int[] initialCapacities = {2,1,1}; //[0] = Sales | [1] = Acc | [2] = HR
    protected ArrayList<ArrayList<ItemContainer>> slots = new ArrayList<>(3);
    public InfoPanel infoPanel;
    public AvailableEmployees availEmployPanel;

    //Employees
    protected ArrayList<Employee> employees = new ArrayList<>();
    protected ArrayList<Employee> hiredEmployees = new ArrayList<>();
    protected ArrayList<Employee> availableEmployees = new ArrayList<>();
    protected ItemContainer[] emptySampleSlots = {(new ItemContainer()), (new ItemContainer()), (new ItemContainer())};

    protected SelectAdvertisement sa;
    protected Responders re;
    protected SelectionTab st1;
    protected SelectionTab st2;

    protected Dimension hireSize;
    protected Dimension manageSize;
    protected Dimension originalFrame = new Dimension(256,768);

    public SalesPanel() {
        new Employee(this);
        st1 = new SelectionTab();
        st1.setThis(this);
        st2 = new SelectionTab();
        st2.setThis(this);
        re = new Responders();
        sa = new SelectAdvertisement(re,this);
        sa.AdvertisementThreadStart();

        panelClicked = new MousePanelClicked(this);

        setSize(originalFrame);
        setPreferredSize(originalFrame);
        setVisible(true);
        setBackground(Color.lightGray);
        hirePanel.setBackground(Color.lightGray);
        managePanel.setBackground(Color.lightGray);

        initializeEmployees();
        initializeHirePanel();
        initializeManagePanel();

        validate();
    }

    private void initializeEmployees() {
        employees.add(new SalesEmployee("Jimh", 62, 0.65f));
        employees.add(new SalesEmployee("Dwigt", 94, 0.8f));
        employees.add(new AccountingEmployee("Agela", 0.1f, 0.15f));
        employees.add(new HREmployee("Tobs", 0.3f, -0.1f));
        hireEmployee(employees.get(0));
        hireEmployee(employees.get(1));
        hireEmployee(employees.get(2));
        hireEmployee(employees.get(3));
    }

    //Integrate with advertisement
    public void hireEmployee(Employee employee) {
        hiredEmployees.add(employee);
        availableEmployees.add(employee);
    }

    public void makeEmployeeAvailable(Employee employee) {
        availableEmployees.add(employee);
    }

    public void switchPanel(boolean hirePanelBool) {
        if(!hirePanelBool) {
            hirePanel.setEnabled(false);
            hirePanel.setVisible(false);
            managePanel.setEnabled(true);
            managePanel.setVisible(true);
        } else {
            managePanel.setEnabled(false);
            managePanel.setVisible(false);
            hirePanel.setEnabled(true);
            hirePanel.setVisible(true);
        }
        revalidate();
    }

    public void updatePanel() {
        if(hirePanel.isVisible()) {
            hirePanel.setVisible(false);
            hirePanel.setVisible(true);
        } else if (managePanel.isVisible()){
            managePanel.setVisible(false);
            managePanel.setVisible(true);
        }
        revalidate();
        if(gamePanel != null)
            gamePanel.pack();
    }

    private void initializeHirePanel(){
        hirePanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        //Add Office.SelectionTab to hirePanel
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.NORTH;
        hirePanel.add(st1, c);

        //Add Office.SelectAdvertisement to hirePanel
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 1;
        c.weighty = 1;
        hirePanel.add(sa, c);

        //Add Office.Responders to hirePanel
        c.gridy = 3;
        c.weightx = 0.9;
        c.fill = GridBagConstraints.VERTICAL;
        hirePanel.add(re, c);
        c.weightx = 1;
        hireSize = new Dimension(256 + getInsets().right + getInsets().left, (int) (st1.getPreferredSize().getHeight()
                + sa.getPreferredSize().getHeight()
                + re.getPreferredSize().getHeight())
                + getInsets().top + getInsets().bottom);

        //Add hirePanel to frame
        add(hirePanel);
        hirePanel.setSize(hireSize);
        setPreferredSize(new Dimension((int) hireSize.getWidth(), hireSize.height + 26));

        setVisible(true);
    }

    private void initializeManagePanel(){
        managePanel.setLayout(layout);
        createEmptySlots();
        updateManagePanelSlots();
        add(managePanel);
    }

    public void updateManagePanelSlots() {
        managePanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();

        //add top selection tab
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridy = 0;
        c.weighty = 1;
        managePanel.add(st2, c);

        //add slots to panel
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(2,0,2,0);
        c.gridy = 1;
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                c.gridy++;
                managePanel.add(slots.get(i).get(j),c);
        }}

        manageSize = new Dimension(256, (int) (st2.getPreferredSize().getHeight() +
                (sampleIC.getPreferredSize().getHeight()+2)*
                        (slots.getFirst().size() + slots.get(1).size() + slots.getLast().size()))
                + getInsets().top + getInsets().bottom);

        managePanel.setPreferredSize(manageSize);
        managePanel.setEnabled(false);
        managePanel.setVisible(false);
    }

    public void updateManagePanelSlot(ItemContainer ic){
        for (int i = 1; i < managePanel.getComponentCount(); i++) {
            if(managePanel.getComponent(i).equals(ic)) {
                System.out.println("found: " + i);

                if(managePanel.getComponent(i) instanceof ItemContainer) {
                    ((ItemContainer) managePanel.getComponent(i)).emptyPanel();
                    ((ItemContainer) managePanel.getComponent(i)).fillContainer(ic.getEmployee(), 1);

                } else {System.out.println("Error occurred managePanel is not instanceof Office.ItemContainer | updating manage slot");}

                revalidate();
                repaint();
                break;
            }
        }
    }

    public void fillSlot(ItemContainer baseIc, ItemContainer newIc) { //called from available employees when you click the send button
        for (int i = 1; i < managePanel.getComponentCount(); i++) {
            if(managePanel.getComponent(i).equals(baseIc)) {
                System.out.println("found: " + i);

                if(managePanel.getComponent(i) instanceof ItemContainer) {
                    ((ItemContainer) managePanel.getComponent(i)).emptyPanel();
                    ((ItemContainer) managePanel.getComponent(i)).fillContainer(newIc.getEmployee(), 1);
                    availableEmployees.remove(newIc.getEmployee());

                } else {System.out.println("Error occurred managePanel is not instanceof Office.ItemContainer");}

                revalidate();
                repaint();
                break;
            }
        }
    }

    public void emptySlot(ItemContainer ic){
        Employee employee = ic.getEmployee();
        int officeNum = 3; //assume error
        if(employee instanceof SalesEmployee) {officeNum = 0;}
        else if(employee instanceof AccountingEmployee) {officeNum = 1;}
        else if(employee instanceof HREmployee) {officeNum = 2;}
        else {System.out.println("ERROR IN DETERMINING EMPLOYEE OFFICE | empty slot");}

        for (int i = 1; i < managePanel.getComponentCount(); i++) {
            if(managePanel.getComponent(i).equals(ic)) {
                System.out.println("found: " + i);

                if(managePanel.getComponent(i) instanceof ItemContainer) {
                    ((ItemContainer) managePanel.getComponent(i)).emptyPanel();
                    ((ItemContainer) managePanel.getComponent(i)).emptyLabel(officeNum);
                    availableEmployees.add(ic.getEmployee());

                } else {System.out.println("Error occurred managePanel is not instanceof Office.ItemContainer");}

                revalidate();
                repaint();
                break;
            }
        }
    }

    private void createEmptySlots() {
        for (int i = 0; i < initialCapacities.length; i++) {
            ArrayList<ItemContainer> slot1D = new ArrayList<>();
            for (int j = 0; j < initialCapacities[i]; j++) {
                slot1D.add(new ItemContainer());
                slot1D.get(j).emptyLabel(i);
                slot1D.get(j).addMouseListener(panelClicked);
            }
            slots.add(slot1D);
        }

        //set the sample slots
        emptySampleSlots[0].emptyLabel(0);
        emptySampleSlots[0].addMouseListener(panelClicked);
        emptySampleSlots[1].emptyLabel(1);
        emptySampleSlots[1].addMouseListener(panelClicked);
        emptySampleSlots[2].emptyLabel(2);
        emptySampleSlots[2].addMouseListener(panelClicked);
    }


    public void checkClickedContainers(JPanel panel){
        for (int i = 1; i < managePanel.getComponentCount(); i++) {
            if(managePanel.getComponent(i).equals(panel)) {
                if(((ItemContainer) managePanel.getComponent(i)).isEmpty()) {
                    System.out.println("Empty Slot Clicked");
                    if (availEmployPanel != null) {
                        availEmployPanel.dispose();
                    }
                    availEmployPanel = new AvailableEmployees((ItemContainer) managePanel.getComponent(i), availableEmployees, this);
                    break;

                } else {
                    System.out.println("Filled Slot Clicked");
                    if (infoPanel != null) {
                        infoPanel.safeDispose();
                    }
                    infoPanel = new InfoPanel((ItemContainer) managePanel.getComponent(i), true, this);
                    break;
                }
            }
        }
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frame f = new Frame();
                SalesPanel salesPanel = new SalesPanel();
                f.setLocationRelativeTo(null);
                f.add(salesPanel);
                f.setVisible(true);
                f.pack();
            }
        });
    }
}

class MousePanelClicked extends MouseAdapter{
    SalesPanel salesPanel;
    public MousePanelClicked(SalesPanel salesPanel) {
        this.salesPanel = salesPanel;
    }

    public void mouseReleased(MouseEvent event) {
        Object source = event.getSource();
        if(source instanceof JPanel panelPressed){
            salesPanel.checkClickedContainers(panelPressed);
        }
    }
}
