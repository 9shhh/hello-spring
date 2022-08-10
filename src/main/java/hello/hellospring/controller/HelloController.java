package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // 화면에 전달할 데이터 정의 (k/v 형식)
        model.addAttribute("data", "hello!!");

        // 스프링 부트에서 resources/templates의 hello.html을 찾아서 렌더링 함.(Thymeleaf 템플릿 엔진 처리 포함)
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 여기서 ResponseBody의 의미는 html의 body를 의미하는 것이 아니라, http의 header와 body 부의 body를 의미함. 이 body의 데이터를 직접 정의 및 제공하겠다는 의미.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        // json 방식으로 리턴됨.
        return hello;
    }

    static class Hello {
        private String name;
        // getter, setter
        // java bean 표준 방식, property 접근 방식 으로 불리우는 형태
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
