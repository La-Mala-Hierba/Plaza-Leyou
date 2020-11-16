package com.leyou.goods.service.impl;

import com.leyou.common.utils.ThreadUtils;
import com.leyou.goods.service.GoodsHtmlService;
import com.leyou.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * static web page
 */
@Service
public class GoodsHtmlServiceImpl implements GoodsHtmlService {

    @Autowired
    private TemplateEngine engine;

    @Autowired
    private GoodsService goodsService;

    /**
     * crear una página de web static con spuId
     *
     * @param spuId
     */
    @Override
    public void createHtml(Long spuId) {
        //create context of Thymeleaf
        Context context = new Context();

        //set datas in context
        context.setVariables(this.goodsService.loadData(spuId));

        //create outputstream to save static page in local

        PrintWriter printWriter = null;
        try {
            File file = new File("D:\\IdeaProjects\\plazaLeyou\\utils\\nginx-1.14.0\\html\\item\\" + spuId + ".html");
            printWriter = new PrintWriter(file);
            this.engine.process("item", context, printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            //close the printwriter
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }

    /**
     * create thread to proceed static web page
     *
     * @param spuId
     */
    public void asyncExcute(Long spuId) {
        ThreadUtils.execute(() -> createHtml(spuId));
        /*ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                createHtml(spuId);
            }
        });*/
    }

    /**
     * Eliminar un static web page
     * @param spuId
     */
    @Override
    public void deleteHtml(Long spuId) {
        File file = new File("D:\\IdeaProjects\\plazaLeyou\\utils\\nginx-1.14.0\\html\\item\\" + spuId + ".html");
        file.deleteOnExit();
    }

}
