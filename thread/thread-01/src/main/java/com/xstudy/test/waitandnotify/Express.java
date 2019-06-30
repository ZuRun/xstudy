package com.xstudy.test.waitandnotify;

/**
 * @author zurun
 * @date 2018/9/1 22:44:05
 */
public class Express {
    public final static String CITY = "hefei";
    private final int WAIT_KM = 100;
    /**
     * 距离
     */
    private int km;
    /**
     * 快递到达的地点
     */
    private String site;

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    public synchronized void changeKm(int km) {
        this.km = km;
        notifyAll();
    }

    public synchronized void changeSite(String site) {
        this.site = site;
        notifyAll();
    }

    public synchronized void waitKm() {
        while (checkKm()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" check km Tread[" + Thread.currentThread().getName() + "] is be notified");
        }
        System.out.println("the km is " + this.km + ", I will changeDB");
    }

    private boolean checkKm() {
        System.out.println("checkKm Thread[" + Thread.currentThread().getName() + "] km:" + km);
        return km <= WAIT_KM;
    }

}
