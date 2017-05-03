package com.xepsa.memo.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xepsa.memo.config.ServiceConfig;
import com.xepsa.memo.model.Memo;
import com.xepsa.memo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RefreshScope
public class MemoService {

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    ServiceConfig config;

    @HystrixCommand
    public Memo getMemo(String memoId) {
        simulateIntermittentLongRunningInvocation();
        // Memo memo = memoRepository.findById(memoId);
        Memo memo = mockMemo();
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


    private Memo mockMemo() {
        final String id = UUID.randomUUID().toString();
        final String userId = UUID.randomUUID().toString();
        final String title = "the title";
        final String message = "some message";
        return new Memo().
                withId(id).
                withUserId(userId).
                withTitle(title).
                withMessage(message);
    }

    private void simulateIntermittentLongRunningInvocation() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum==3) {
            sleep();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
