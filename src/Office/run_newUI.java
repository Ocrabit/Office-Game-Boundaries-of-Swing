package Office;

import java.awt.*;
import java.util.Scanner;
public class run_newUI implements Runnable{
    public SalesPanel frame;
    static Frame f;
    public run_newUI(SalesPanel salesPanel){
        frame = salesPanel;
        debugThreadStart();
    }

    Thread debugThread;
    public void debugThreadStart(){
        debugThread = new Thread(this);
        debugThread.start();
    }
    @Override
    public void run() {
        Scanner input = new Scanner(System.in);

        while(debugThread != null) {
            System.out.println("Type 1 for size of frame, 2 for size of hire panel, 3 for size of manage panel, 4, for frame, 5 to end");
            int num = input.nextInt();
            if(num == 1){
                System.out.println(frame.getSize());
                System.out.println(frame.getPreferredSize());
            } else if (num == 2) {
                System.out.println(frame.hirePanel.getSize());
                System.out.println(frame.hirePanel.getPreferredSize());
            } else if (num == 3){
                System.out.println(frame.managePanel.getSize());
                System.out.println(frame.managePanel.getPreferredSize());
            } else if (num == 4) {
                System.out.println(f.getSize());
                System.out.println(f.getPreferredSize());
            } else {
                debugThread.interrupt();
            }

        }
    }

    public static void main(String[] args) {
        f = new Frame();
        f.setLayout(new GridBagLayout());
        f.setResizable(true);
        SalesPanel s= new SalesPanel();
        run_newUI run = new run_newUI(s);
        GridBagConstraints c = new GridBagConstraints();
        f.add(s,c);
        f.setVisible(true);
        f.pack();

    }
}
