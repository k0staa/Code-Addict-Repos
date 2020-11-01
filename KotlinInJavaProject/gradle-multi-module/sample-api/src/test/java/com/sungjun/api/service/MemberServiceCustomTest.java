package com.sungjun.api.service;

import com.sungjun.common.member.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceCustomTest {

    @Autowired
    private MemberServiceCustom memberServiceCustom;

    @Test
    public void save() {
        Member member = new Member("sungjun", "sungjun@gmail.com");
        Long id = memberServiceCustom.singup(member);
        assertThat(id, is(1l));
    }

}