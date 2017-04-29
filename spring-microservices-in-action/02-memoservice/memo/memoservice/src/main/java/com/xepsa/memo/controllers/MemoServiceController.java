package com.xepsa.memo.controllers;

import com.xepsa.memo.model.Memo;
import com.xepsa.memo.services.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping(value="v1/user/{userId}/memo")
public class MemoServiceController {
    
    @Autowired
    private MemoService memoService;

    @RequestMapping(value="/{memoId}", method = RequestMethod.GET)
    public Memo getMemo(@PathVariable("userId") String userId,
                        @PathVariable("memoId") String memoId) {

//        return new Memo()
//            .withId(memoId)
//            .withUserId(userId)
//            .withMessage("Some Message");

        return memoService.getMemo(memoId);

    }

    @RequestMapping(value="{memoId}",method = RequestMethod.PUT)
    public String updateMemo(@PathVariable("memoId") String memoId) {
        return String.format("This is the put");
    }

    @RequestMapping(value="{memoId}",method = RequestMethod.POST)
    public String saveMemo(@PathVariable("memoId") String memoId) {
        return String.format("This is the post");
    }

    @RequestMapping(value="{memoId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteMemo(@PathVariable("memoId") String memoId) {
        return String.format("This is the Delete");
    }
}
