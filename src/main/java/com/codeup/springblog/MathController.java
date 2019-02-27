package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{first}/{second}")
    @ResponseBody
    public String add(@PathVariable double first, @PathVariable double second){

        return "" + (first + second);
    }
    @GetMapping("/subtract/{first}/{second}")
    @ResponseBody
    public String subtract(@PathVariable double first, @PathVariable double second){

        return "" + (first - second);
    }

    @GetMapping("/divide/{first}/{second}")
    @ResponseBody
    public String divide(@PathVariable double first, @PathVariable double second){
        if(second == 0)
            return "0";

        return "" + (first / second);
    }

    @GetMapping("/multiply/{first}/{second}")
    @ResponseBody
    public String multiply(@PathVariable double first, @PathVariable double second){
        return "" + (first * second);
    }

    @GetMapping("/roll-dice")
    @ResponseBody
    public String roll(){
        return "" +  ((int)(Math.ceil(Math.random() * 6)));
    }

    @GetMapping("/roll-dice/{guess}")
    @ResponseBody
    public String rollGuess(@PathVariable int guess){
        int roll = Integer.parseInt(roll());

        if(roll == guess){
            return "Generated of a die roll: " + roll + "\nYour guess was correct!!";
        }
        return "Generated of a die roll: " + roll + "\nYour guess: " + guess;
    }

}
