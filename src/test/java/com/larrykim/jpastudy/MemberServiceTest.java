package com.larrykim.jpastudy;

import com.larrykim.jpastudy.domain.Member;
import com.larrykim.jpastudy.repository.MemberRepository;
import com.larrykim.jpastudy.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //Given
        Member member = new Member();
        member.setName("kim");

        //When
        Long saveId = memberService.join(member);

        //Then
        System.out.println("진행중");
        assertEquals(member, memberRepository.findOne(saveId));
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_테스트() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        System.out.println("");
        System.out.println("1@@@@@@@@@@@@@@@1@@@@@@@@@@@@@@@@");
        System.out.println("");
        //when
        memberService.join(member1);
        memberService.join(member2);

        System.out.println("");
        System.out.println("2@2@@@@@@@@@@@@@2@@@@@@@@@@@@@@@@@");
        System.out.println("");
        //then
        fail("예외 발생");
        System.out.println("");
        System.out.println("3@@@@@@@@@@@@@@@@3@@@@@@@@@@@@@@@");
        System.out.println("");
    }
}