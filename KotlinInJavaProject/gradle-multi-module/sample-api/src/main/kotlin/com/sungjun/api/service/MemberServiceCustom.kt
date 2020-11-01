package com.sungjun.api.service

import com.sungjun.common.member.Member
import com.sungjun.common.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberServiceCustom(private val memberRepository: MemberRepository) {
    fun singup(member: Member) = memberRepository.save(member).id
}
