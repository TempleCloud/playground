package com.xepsa.user.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    @Test
    public void testNewUser() {

        final String id = UUID.randomUUID().toString();
        final String title = "Mr";
        final String givenName = "Temple";
        final String familyName = "Cloud";

        User user = new User().
                withId(id).
                withTitle(title).
                withGivenName(givenName).
                withFamilyName(familyName);

        Assert.assertEquals(id, user.getId());
        Assert.assertEquals(title, user.getTitle());
        Assert.assertEquals(givenName, user.getGivenName());
        Assert.assertEquals(familyName, user.getFamilyName());
    }



}
