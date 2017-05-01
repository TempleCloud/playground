package com.xepsa.memo.controllers;

import com.xepsa.memo.model.Memo;
import com.xepsa.memo.services.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="v1/user/{userId}/memo")
public class MemoServiceController {
    
    @Autowired
    private MemoService memoService;

    @RequestMapping(value="/{memoId}", method = RequestMethod.GET)
    public Memo getMemo(@PathVariable("memoId") String memoId) {
        return memoService.getMemo(memoId);
    }

    @RequestMapping(value="{memoId}",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createMemo(@RequestBody Memo memo) {
        memoService.saveMemo(memo);
        return String.format("This is the post");
    }

    @RequestMapping(value="{memoId}",method = RequestMethod.PUT)
    public String updateMemo(@PathVariable("memoId") String memoId) {
        return String.format("This is the put");
    }


    @RequestMapping(value="{memoId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteMemo(@PathVariable("memoId") String memoId) {
        return String.format("This is the Delete");
    }
}
