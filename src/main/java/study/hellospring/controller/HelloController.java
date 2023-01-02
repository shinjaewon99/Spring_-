package study.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {


    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // templates에서 key값 data를 찾아 value로 변환

        return "hello";
    }

    @GetMapping("hello-mvc") //@RequestParam("가져올 데이터의 이름") [데이터타입][가져온데이터를 담을 변수]
    // EX : localhost:8080/hello-mvc?name(데이터이름)=123 -> template의 value값 hello123이 반환
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // view 없이 문자 그대로 반환된다. (model 사용 X)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
