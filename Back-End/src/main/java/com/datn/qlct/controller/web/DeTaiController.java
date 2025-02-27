package com.datn.qlct.controller.web;

import com.datn.qlct.dto.DeTaiDTO;
import com.datn.qlct.service.Impl.DeTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "deTaiControllerOfWeb")
public class DeTaiController {
    @Autowired
    private DeTaiService deTaiService;

    @RequestMapping(value = "/cac-de-tai", method = RequestMethod.GET)
    public ModelAndView deTaiList(@RequestParam(value = "tendetai", required = false) String tenDeTai) {
        ModelAndView mav = new ModelAndView("/de-tai/list");
        // List<DeTaiDTO> listDeTai = deTaiService.findAll(tenDeTai);
        // mav.addObject(listDeTai);
        return mav;
    }
}
