package com.sungjun.admin.member;

import com.sungjun.common.member.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/")
    public Member get() {
        return new Member("sungjun", "sungjun@gmail.com");
    }
}
