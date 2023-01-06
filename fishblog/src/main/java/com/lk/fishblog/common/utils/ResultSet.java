package com.lk.fishblog.common.utils;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultSet implements Serializable {
    public static final int RESULT_CODE_FALSE = 0;
    public static final int RESULT_CODE_TRUE = 1;
    public static final int RESULT_CODE_ERROR = -1;

    public Integer resultCode = RESULT_CODE_TRUE;
    public String resultMsg;
    private Object data;

    public ResultSet() {
    }

    public ResultSet(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResultSet(int resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
}
