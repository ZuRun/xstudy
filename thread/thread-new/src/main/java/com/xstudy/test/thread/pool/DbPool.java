package com.xstudy.test.thread.pool;

import java.util.LinkedList;

/**
 * @author zurun
 * @date 2019/6/30 20:41:23
 */
public class DbPool {
    private final LinkedList<DbConnection> pool = new LinkedList<>();

    public DbPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            pool.addLast(new DbConnection(this, i));
        }
    }

    protected void releaseConnection(DbConnection connection) {
        if (connection != null) {
            synchronized (pool) {
                System.out.println("release:" + connection.getNum());
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public DbConnection fetchConnection() {
        synchronized (pool) {
            while (pool.isEmpty()) {
                try {
                    pool.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return pool.removeFirst();
        }
    }
}
