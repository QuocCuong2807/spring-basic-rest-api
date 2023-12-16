package com.springteam.TechProduct.dto;

import com.springteam.TechProduct.entity.Product;

import java.util.List;

public class ProductResponeDTO {

    private List<Product> data;

    private int pageNumber;

    private int pageSize;

    private long totalElement;

    private boolean isFirstPage;
    private boolean isLastPage;

    //constructor

    public ProductResponeDTO(){}

    public ProductResponeDTO(List<Product> data, int pageNumber, int pageSize,
                             long totalElement, boolean isFirstPage, boolean isLastPage) {
        this.data = data;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElement = totalElement;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
    }

    //getter & setter


    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    //to string

    @Override
    public String toString() {
        return "ProductResponeDTO{" +
                "data=" + data +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalElement=" + totalElement +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                '}';
    }
}
