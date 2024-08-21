package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        return "hello"; // resources/templates/hello.html;
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam ("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-mvc") // -> 거의 안씀
    @ResponseBody
    public String helloString(@RequestParam ("name") String name){
        return "hello-template" + name;
    }

//    핵심
    @GetMapping("hello-api")    // JSON 방식으로 데이터를 반환. 거의 다 JSON 방식으로 반환
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    
    // 자바 빈즈
    static class Hello{

        private String name;
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
