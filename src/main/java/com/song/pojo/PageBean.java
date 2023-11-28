package com.song.pojo;

import java.util.List;

/**
 * @author Administrator
 */
public class PageBean<T> {
    private int totalCout;
    private List<T> rows;


    public PageBean() {
    }

    public PageBean(int totalCout, List<T> rows) {
        this.totalCout = totalCout;
        this.rows = rows;
    }

    /**
     * 获取
     * @return totalCout
     */
    public int getTotalCout() {
        return totalCout;
    }

    /**
     * 设置
     * @param totalCout
     */
    public void setTotalCout(int totalCout) {
        this.totalCout = totalCout;
    }

    /**
     * 获取
     * @return rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * 设置
     * @param rows
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{totalCout = " + totalCout + ", rows = " + rows + "}";
    }
}
