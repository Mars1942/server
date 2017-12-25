package com.ut.business.common;

public class PageParam {

    private static final int MIN_SIZE = 10;
    private int page = 1;
    private int size = MIN_SIZE;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
