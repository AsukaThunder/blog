package com.ford.blog.controller;

import lombok.Data;

/**
 * @ClassName: OperationStatus
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 6:17
 **/
@Data
public class OperationStatus {
    private String status;
    private String msg;
    public OperationStatus() {
    }

    public OperationStatus(String status, String msg) {

        this.status = status;
        this.msg = msg;
    }
}
