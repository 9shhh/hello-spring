package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 클래스에서 @Controller 어노테이션이 선언되어 있으면, 스프링 컨테이너가 뜰 때 해당 클래스의 객체를 생성(MemberController 객체)하고 스프링 컨테이너에 넣어 관리한다.
 * 스프링 컨테이너에서 등록된 객체를 관리하게 되면, 스프링 컨테이너로 부터 받아서 쓰도록 해야한다.
 */

@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * @Autowired 어노테이션이 기입된 생성자는 스프링이 연관된 객체를 찾아 넣어준다.
     * 생성자에 @Autowired 어노테이션이 선언되어 있으므로, 스프링 컨테이너에 있는 MemberService 객체를 가져와 연결 시켜준다. (의존성 주입)
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
