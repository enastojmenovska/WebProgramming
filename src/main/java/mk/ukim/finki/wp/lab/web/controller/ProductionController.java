package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productions")
public class ProductionController {
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public String getProductuonsPage(Model model) {
        model.addAttribute("bodyContent", "productions");
        model.addAttribute("productions", productionService.findAll());
        return "master-template";
    }
}
