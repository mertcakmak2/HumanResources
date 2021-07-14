package com.company.hrms.Core.Utilities.Result;

public class PaginationDataResult<T> extends DataResult<T> {

    private T data;
    private int totalData;

    public PaginationDataResult(T data, String message, int totalData) {
        super(data, true ,message);
        this.totalData = totalData;
    }

    public int getTotalData() {
        return totalData;
    }
}
