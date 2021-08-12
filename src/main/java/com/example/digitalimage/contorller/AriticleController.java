package com.example.digitalimage.contorller;


import com.example.digitalimage.model.entity.ArticleAndComment;
import com.example.digitalimage.service.AriticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class AriticleController {

    @Autowired
    AriticleService ariticleService;

    @GetMapping("showArtCom")
    public ArticleAndComment getArticleAndComment(@RequestParam Integer id){
        System.out.println(id);
        return this.ariticleService.getDetail(id);
    }

}

