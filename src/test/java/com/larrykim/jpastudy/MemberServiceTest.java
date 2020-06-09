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

//@RunWith -> JUnit으로 작성한 테스트 케이스를 스프링 프레임워크와 통합
//@SpringBootTest -> 테스트에 사용할 ApplicationContext를 쉽게 생성하고 조작
//@SpringBootTest -> 기능은 반드시 @RunWith(SpringRunner.class)와 함께 사용
//@SpringBootTest -> 기존 스프링 테스트에서 사용하던 @ContextConfiguration의 발전된 기능
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
        assertEquals(member, memberRepository.findOne(saveId));
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_테스트() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //then
        //위 지정한 예외가 발생해야 테스트가 성공
        fail("예외 발생");
    }
}