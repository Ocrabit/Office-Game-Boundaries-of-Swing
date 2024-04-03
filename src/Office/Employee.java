package Office;

public class Employee implements Runnable{
    static protected SalesPanel mainPanel;
    static protected boolean levelUpActive = false;
    protected String name;
    protected int level = 1;
    protected float costTime = 0.002f; //in hours
    protected int costTimeSeconds; //in seconds
    protected float costMoney = 50f;
    protected Thread levelingUpThread;
    protected InfoPanel infoPanel;
    protected ItemContainer attachedIC;

    //must call new employee with panel attached first for level up to work
    public Employee(SalesPanel mainPanel){
        Employee.mainPanel = mainPanel;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getCostTime(){
        return String.format("%.1f",costTime) + "h";
    }

    public String getCostMoney(){
        return String.format("%.2f",costMoney);
    }

    private void LevelUp() {
        this.level++;
        this.costTime+=.5F;
        this.costMoney*=2.5f;
    }

    public void startLevelUp(){
        levelUpActive = true;
        levelingUpThread = new Thread(this);
        costTimeSeconds = (int)(costTime*60*60);
        infoPanel.setProgressCapacity(costTimeSeconds); //sets total seconds of upgrade
        levelingUpThread.start();
        infoPanel.createAndShowUI();
    }

    double secondCount;
    int totalSeconds = 0;
    int FPS = 60;

    @Override
    public void run() {
        double drawInterval = (double) 1000000000/FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;

        while(levelingUpThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                secondCount += drawInterval * (1 + (1 - 1 / delta));
                if(secondCount >= 1000000000L) {
                    totalSeconds++;
                    //updates the percentage on Office.InfoPanel if it exists
                    if(infoPanel != null) {
                        infoPanel.updateProgressBar(totalSeconds);
                    }
                    secondCount-=1000000000L;
                }
                if(totalSeconds >= costTimeSeconds) {
                    //end thread and call level up
                    levelingUpThread = null;
                    levelUpActive = false;
                    attachedIC = null; //
                    LevelUp();
                    if(infoPanel != null) {
                        infoPanel.createAndShowUI();
                    }
                    mainPanel.updateManagePanelSlot(attachedIC);

                }
                delta--;
            }
        }
    }

    public String getPercentTillLeveled(){
        if(levelingUpThread!=null) {
            return String.format("%.2f", ((float) totalSeconds / costTimeSeconds)) + "%";
        } else {
            return "100%";
        }
    }

    public boolean isLeveling(){
        return levelingUpThread!=null;
    }

    public void infoCreated(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        if(levelUpActive) {
            infoPanel.setProgressCapacity(costTimeSeconds);
        }
    }

    public void infoRemoved() {
        //this.infoPanel.employee = this;
        this.infoPanel = null;
        System.out.println("info Removed");
    }
}
class AccountingEmployee extends Employee {
    private float timePercent;  //72
    private float clientPercent;

    public AccountingEmployee(String name, float timePercent, float clientPercent) {
        this.name = name;
        this.timePercent = timePercent;
        this.clientPercent = clientPercent;
    }

    public float getTimePercent() {
        return timePercent;
    }

    public float getClientPercent() {
        return clientPercent;
    }

    public void setTimePercent(float timePercent) {
        this.timePercent = timePercent;
    }

    public void setClientPercent(float clientPercent) {
        this.clientPercent = clientPercent;
    }
}

class HREmployee extends Employee{
    private float moneyPercent;
    private float timePercent;

    public HREmployee(String name, float moneyPercent, float timePercent) {
        this.name = name;
        this.moneyPercent = moneyPercent;
        this.timePercent = timePercent;
    }

    public float getMoneyPercent() {
        return moneyPercent;
    }

    public float getTimePercent() {
        return timePercent;
    }

    public void setMoneyPercent(float moneyPercent) {
        this.moneyPercent = moneyPercent;
    }

    public void setTimePercent(float timePercent) {
        this.timePercent = timePercent;
    }
}

class SalesEmployee extends Employee{
    private int totalSeconds;  //72
    private float percentChance;

    public SalesEmployee(String name, int totalSeconds, float percentChance) {
        this.name = name;
        this.totalSeconds = totalSeconds;
        this.percentChance = percentChance;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    public float getPercentChance() {
        return percentChance;
    }

    public void setPercentChance(int percentChance) {
        this.percentChance = percentChance;
    }

    public void setTotalSeconds(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }


}

