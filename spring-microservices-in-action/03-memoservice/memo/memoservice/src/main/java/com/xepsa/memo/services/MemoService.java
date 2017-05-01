package com.xepsa.memo.services;

import com.xepsa.memo.config.ServiceConfig;
import com.xepsa.memo.model.Memo;
import com.xepsa.memo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RefreshScope
public class MemoService {

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    ServiceConfig config;


    public Memo getMemo(String memoId){
        Memo memo = memoRepository.findById(memoId);
        return memo.withExtraInfo(config.getExampleProperty());
    }

    public void saveMemo(Memo memo) {
        memo.withId(UUID.randomUUID().toString());
        memoRepository.save(memo);
    }

    public void updateMemo(Memo memo) {
        memoRepository.save(memo);
    }

    public void deleteMemo(Memo memo) {
        memoRepository.delete(memo.getId());
    }

}
