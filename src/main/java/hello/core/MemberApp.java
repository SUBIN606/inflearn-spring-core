package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /* --- Spring 으로 전환! --- */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // AppConfig에 있는 환경 설정 정보를 가져온다 (스프링 컨테이너로 관리하도록)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // name은 기본적으로 메서드명이 된다

        Member curry = new Member(1L, "Curry", Grade.VIP);

        /* 회원가입 */
        memberService.join(curry);

        /* 조회 */
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + curry.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
