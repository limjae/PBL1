package limjae.week2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController {

    @GetMapping("/")
    public ModelAndView mainPage(Model model) {
        System.out.println("/ redirecting");
        ModelAndView mainMV = new ModelAndView();
        mainMV.setViewName("main");
        return mainMV;
    }

    @GetMapping("/write")
    public ModelAndView writePage() {
        System.out.println("/write redirecting");
        ModelAndView writeMV = new ModelAndView();
        writeMV.setViewName("write");
        return writeMV;
    }

    @GetMapping("/edit")
    public ModelAndView editPage() {
        System.out.println("/edit redirecting");
        ModelAndView editMV = new ModelAndView();
        editMV.setViewName("edit");
        return editMV;
    }

    @GetMapping("/details/{postingSeq}")
    public ModelAndView detailPage(@PathVariable(name = "postingSeq") String postingSeq) {
        System.out.println("/details/id redirecting");
        ModelAndView detailMV = new ModelAndView();
        detailMV.setViewName("detail");
        detailMV.addObject("postingSeq", postingSeq);
        return detailMV;
    }

}