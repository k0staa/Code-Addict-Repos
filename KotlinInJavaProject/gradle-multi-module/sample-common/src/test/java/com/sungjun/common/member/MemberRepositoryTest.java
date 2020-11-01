package com.sungjun.common.member;

import com.sungjun.common.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void add() {
        memberRepository.save(new Member("sungjun", "sungjun@gmail.com"));
        Member saved = memberRepository.findById(1l).get();
        assertThat(saved.getName(), is("sungjun"));
    }

}