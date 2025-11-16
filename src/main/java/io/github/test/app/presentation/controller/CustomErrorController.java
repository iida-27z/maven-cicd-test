package io.github.test.app.presentation.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    @RequestMapping
    public String getErrorPage(Model model, HttpServletRequest request) {
        // ステータスコードを取得
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = (status instanceof Integer) ? (Integer) status : 0;

        // エラーメッセージを取得
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        String messageString = (message instanceof String) ? (String) message : "不明なエラーが発生しました";

        // ステータスコードに応じて使用するビューを変更
        String resultPage = "pages/error/error";
        if (statusCode == 404) {
            resultPage = "pages/error/404";
        } else if (400 <= statusCode && statusCode < 500) {
            model.addAttribute("statusCode", String.valueOf(statusCode));
            model.addAttribute("errorMessage", String.valueOf(messageString));
            resultPage = "pages/error/4xx";
        } else if (500 <= statusCode && statusCode < 600) {
            model.addAttribute("statusCode", String.valueOf(statusCode));
            model.addAttribute("errorMessage", String.valueOf(messageString));
            resultPage = "pages/error/5xx";
        }
        return resultPage;
    }
}
