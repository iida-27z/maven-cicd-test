package io.github.test.app.presentation.controller;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.servlet.RequestDispatcher;

@WebMvcTest(CustomErrorController.class)
public class CustomErrorControllerIT {
        @Autowired
        private MockMvc mockMvc;

        @Test
        @Tag("integration")
        void getErrorPage_with404StatusCode_shouldReturnErrorView() throws Exception {
                // arrange
                String requestPath = "/error";
                int statusCode = 404;

                // act & assert
                String expected = "pages/error/404";
                mockMvc.perform(
                                MockMvcRequestBuilders.get(requestPath).requestAttr(RequestDispatcher.ERROR_STATUS_CODE,
                                                statusCode))
                                .andExpect(MockMvcResultMatchers.view().name(expected));
        }

        @SuppressWarnings("null")
        @ParameterizedTest
        @CsvSource({
                        "400, pages/error/4xx, 400",
                        "499, pages/error/4xx, 499",
                        "500, pages/error/5xx, 500",
                        "599, pages/error/5xx, 599",
        })
        @Tag("integration")
        void getErrorPage_withStatusCode_shouldReturnCorrectView(int statusCode, String expectedViewName,
                        Integer expected) throws Exception {
                // arrange
                String requestPath = "/error";
                String attributeName = "statusCode";

                // act & assert
                mockMvc.perform(
                                MockMvcRequestBuilders.get(requestPath).requestAttr(RequestDispatcher.ERROR_STATUS_CODE,
                                                statusCode))
                                .andExpect(MockMvcResultMatchers.view().name(expectedViewName))
                                .andExpect(MockMvcResultMatchers.model().attribute(attributeName, expected.toString()));
        }

        @SuppressWarnings("null")
        @ParameterizedTest
        @CsvSource({
                        "399, pages/error/error",
                        "600, pages/error/error",
        })
        @Tag("integration")
        void getErrorPage_withoutValidStatusCode_shouldReturnErrorView(int invalidStatusCode, String expectedViewName)
                        throws Exception {
                // arrange
                String requestPath = "/error";

                // act & assert
                mockMvc.perform(
                                MockMvcRequestBuilders.get(requestPath).requestAttr(RequestDispatcher.ERROR_STATUS_CODE,
                                                invalidStatusCode))
                                .andExpect(MockMvcResultMatchers.view().name(expectedViewName));
        }

        @Test
        @Tag("integration")
        void getErrorPage_withNotIntStatusCode_shouldReturnErrorView()
                        throws Exception {
                // arrange
                String requestPath = "/error";
                String invalidStatusCode = "ABC";

                // act & assert
                String expectedViewName = "pages/error/error";
                mockMvc.perform(
                                MockMvcRequestBuilders.get(requestPath).requestAttr(RequestDispatcher.ERROR_STATUS_CODE,
                                                invalidStatusCode))
                                .andExpect(MockMvcResultMatchers.view().name(expectedViewName));
        }

        @Test
        @Tag("integration")
        void getErrorPage_withoutStatusCode_shouldReturnErrorView() throws Exception {
                // arrange
                String requestPath = "/error";

                // act & assert
                String expectedViewName = "pages/error/error";
                mockMvc.perform(MockMvcRequestBuilders.get(requestPath))
                                .andExpect(MockMvcResultMatchers.view().name(expectedViewName));
        }

        @SuppressWarnings("null")
        @ParameterizedTest
        @CsvSource({
                        "403, Forbidden, Forbidden",
                        "500, Internal Server Error, Internal Server Error",
        })
        @Tag("integration")
        void getErrorPage_withErrorMessage_shouldReturnErrorViewWithMessage(int statusCode, String errorMessage,
                        String expected) throws Exception {
                // arrange
                String requestPath = "/error";
                String attributeName = "errorMessage";

                // act & assert
                mockMvc.perform(
                                MockMvcRequestBuilders.get(requestPath)
                                                .requestAttr(RequestDispatcher.ERROR_MESSAGE, errorMessage)
                                                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, statusCode))
                                .andExpect(MockMvcResultMatchers.model().attribute(attributeName, expected));
        }

        @Test
        @Tag("integration")
        void getErrorPage_withoutErrorMessage_shouldReturnErrorViewWithDefaultMessage() throws Exception {
                // arrange
                String requestPath = "/error";
                int statusCode = 500;
                String attributeName = "errorMessage";

                // act & assert
                String expected = "不明なエラーが発生しました";
                mockMvc.perform(
                                MockMvcRequestBuilders.get(requestPath).requestAttr(RequestDispatcher.ERROR_STATUS_CODE,
                                                statusCode))
                                .andExpect(MockMvcResultMatchers.model().attribute(attributeName, expected));
        }
}
