package com.xepsa.memo.services;

import com.xepsa.memo.model.Memo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemoService {

    public Memo getMemo(String MemoId){
        return new Memo()
                .withId(MemoId)
                .withUserId(UUID.randomUUID().toString())
                .withMessage("Some Message");
    }

    public void saveMemo(Memo Memo) {

    }

    public void updateMemo(Memo Memo) {

    }

    public void deleteMemo(Memo Memo) {

    }

}
