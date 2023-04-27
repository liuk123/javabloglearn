package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewReplyRequest;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.Reply;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/reply")
@Slf4j
public class ReplyController {
    @Autowired
    ReplyService replyService;


//    @GetMapping(path="/{id}")
//    public ResultSet getById(@PathVariable Long id){
//        Reply a = replyService.findById(id);
//        log.info("Reply {}:", a);
//        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", a);
//    }

    @GetMapping(path="/")
    public PageInfo<Reply> getComment(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) Long id){
        Page<Reply> c = this.replyService.findByCommentId(id, pageIndex, pageSize);
        PageInfo<Reply> page = new PageInfo<>(c);
        return page;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewReplyRequest r, Authentication authentication){
        User fu = (User) authentication.getPrincipal();
        User tu = new User(r.getToUserId(), r.getToUsername());
        Comment c = new Comment(r.getCommentId());
        Reply reply = replyService.save(r.getContent(), c, new User(fu.getId(),fu.getUsername(),fu.getAvatar()), tu);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功", reply);
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        replyService.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

}
