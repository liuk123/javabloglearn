package com.lk.fishblog.controller.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Getter
@Setter
@ToString
public class NewAmountRequest {
    @NotNull
    private Long value;

    public NewAmountRequest(String json) throws IOException{
        NewAmountRequest param = new ObjectMapper().readValue(json, NewAmountRequest.class);
        this.value = param.getValue();
    }
    public NewAmountRequest() {}
}
