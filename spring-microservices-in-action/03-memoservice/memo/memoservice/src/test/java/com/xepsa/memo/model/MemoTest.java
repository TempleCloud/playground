package com.xepsa.memo.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
public class MemoTest {

    @Test
    public void testNewMemo() {

        final String id = UUID.randomUUID().toString();
        final String userId = UUID.randomUUID().toString();
        final String title = "the title";
        final String message = "some message";

        Memo memo = new Memo().
                withId(id).
                withUserId(userId).
                withTitle(title).
                withMessage(message);

        Assert.assertEquals(id, memo.getId());
        Assert.assertEquals(userId, memo.getUserId());
        Assert.assertEquals(title, memo.getTitle());
        Assert.assertEquals(message, memo.getMessage());

    }



}
