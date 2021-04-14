package com.zjc.hustoj.auth.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zjc.hustoj.auth.utils.TokenProvider;
import com.zjc.hustoj.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author David Hsiang
 * @date 2021/04/06/10:03 下午
 */
@Controller
@RequestMapping("/auth")
public class KaptchaController extends BaseController {

    @Resource
    private DefaultKaptcha captchaProducer;

    @Resource
    public TokenProvider tokenProvider;


    @GetMapping("/kaptcha")
    public void defaultKaptcha(HttpServletResponse httpServletResponse) throws Exception {
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();

        String verifyCode = captchaProducer.createText();
        String verifyCodeToken = tokenProvider.createVerifyCodeToken(verifyCode);
        BufferedImage challenge = captchaProducer.createImage(verifyCode);
        ImageIO.write(challenge, "jpg", imgOutputStream);

        byte[] captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader(AUTHORIZATION_HEADER, verifyCodeToken);
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
