package com.xstudy.test.thread.pool;

/**
 * @author zurun
 * @date 2019/6/30 20:42:35
 */
public class DbConnection {

    private final int num;
    private final DbPool dbPool;

    public DbConnection(DbPool dbPool, int num) {
        this.num = num;
        this.dbPool = dbPool;
    }

    public int getNum() {
        return num;
    }

    public void release() {
        dbPool.releaseConnection(this);
    }


}
