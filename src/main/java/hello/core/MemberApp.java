package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member curry = new Member(1L, "Curry", Grade.VIP);
        /* 회원가입 */
        memberService.join(curry);

        /* 조회 */
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + curry.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
