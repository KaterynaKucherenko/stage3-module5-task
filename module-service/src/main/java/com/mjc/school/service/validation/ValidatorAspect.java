//package com.mjc.school.service.validation;
//
//import com.mjc.school.service.dto.AuthorDtoRequest;
//import com.mjc.school.service.dto.NewsDtoRequest;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class ValidatorAspect {
//    private Validator validator;
//
//    public ValidatorAspect(Validator validator) {
//        this.validator = validator;
//    }
//
//    @Before("@annotation(ValidateNews) && args(newsDtoRequest)")
//    public void valNewsRequest(NewsDtoRequest newsDtoRequest) {
//        validator.validateNewsReq(newsDtoRequest);
//    }
//
//
//    @Before("@annotation(ValidateAuthor) && args(authorDtoRequest)")
//    public void valAuthorRequest(AuthorDtoRequest authorDtoRequest) {
//        validator.validateAuthorReq(authorDtoRequest);
//    }
//
//
//}