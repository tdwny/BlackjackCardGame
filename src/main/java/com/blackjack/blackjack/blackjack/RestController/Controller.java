package com.blackjack.blackjack.blackjack.RestController;

import com.blackjack.blackjack.blackjack.gameSequence.classes.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blackjack")
public class Controller {



    @GetMapping
    public String getRequest(){

        return "Get blackjack";

    }

}
