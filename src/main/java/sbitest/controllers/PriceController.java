package sbitest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sbitest.entity.db.ViewPrice;
import sbitest.service.GetpriceService;

@Controller
public class PriceController {

    @Autowired
    private GetpriceService getpriceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model){
        List<ViewPrice> list = getpriceService.findPrcie();
        model.addAttribute("pricelist", list);
        return "pricelist";
    }
}
