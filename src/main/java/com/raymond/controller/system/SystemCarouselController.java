package com.raymond.controller.system;

import com.raymond.service.system.SystemCarouselService;
import com.raymond.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system/carousel")
public class SystemCarouselController {

    @Autowired
    private SystemCarouselService systemCarouselService;

    @GetMapping("/image_names")
    public AjaxResult imageNames() {
        return AjaxResult.success(this.systemCarouselService.findAll());
    }

}
