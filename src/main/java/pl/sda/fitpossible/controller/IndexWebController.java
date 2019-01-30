package pl.sda.fitpossible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexWebController {

   @GetMapping("/")
   public String getIndex (){ return "index";}
}
