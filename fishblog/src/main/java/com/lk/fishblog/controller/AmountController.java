package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewAmountRequest;
import com.lk.fishblog.model.Amount;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.AmountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/amount")
@Slf4j
@CacheConfig(cacheNames = "AmountCache")
public class AmountController {
    @Autowired
    AmountService amountService;
    /**
     * 获取金额
     * @param
     * @return
     */
    @GetMapping(path="/")
    @Cacheable
    public ResultSet getAmount(@RequestParam String code){
        Amount n = amountService.findByName(code);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n.getAmount());
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet save(@RequestBody @Valid NewAmountRequest newAmountRequest, Authentication authentication){
            User user = (User) authentication.getPrincipal();
            String key = "user_" + user.getId();
            Amount amount = amountService.findByName(key);
            Amount c;
            if(amount!=null){// 更新
                if(amount.getAmount() + newAmountRequest.getValue() < 0){
                    return new ResultSet(ResultSet.RESULT_CODE_TRUE, "余额不足", amount.getAmount());
                }
                c  = amountService.save(amount.getId(),amount.getName(),amount.getAmount() + newAmountRequest.getValue());
            }else{ // 新建
                if(newAmountRequest.getValue() <= 0){
                    return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加失败", null);
                }
                c = amountService.save(null,key,newAmountRequest.getValue());
            }

        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",c.getAmount());
    }

    @DeleteMapping(path = "/")
    public ResultSet delById(@RequestParam Long id){
        amountService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
