package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // 화면에 전달할 데이터 정의 (k/v 형식)
        model.addAttribute("data", "hello!!");

        // 스프링 부트에서 resources/templates의 hello.html을 찾아서 렌더링 함.(Thymeleaf 템플릿 엔진 처리 포함)
        return "hello";
    }
}
