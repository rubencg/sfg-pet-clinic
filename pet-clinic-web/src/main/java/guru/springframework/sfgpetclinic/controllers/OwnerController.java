package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(@Autowired OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/index", "/index.html"})
    public String Index(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
