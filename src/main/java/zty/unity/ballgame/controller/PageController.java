package zty.unity.ballgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tianyi
 * @date 2018-12-24 12:01
 */
@Controller
public class PageController {

    @GetMapping("/index1")
    public String toIndex1(){
        return "index1";
    }

    @GetMapping("/index2")
    public String toIndex2(){
        return "index2";
    }

    @GetMapping("/topic")
    public String toTopic(){
        return "topic";
    }

}
