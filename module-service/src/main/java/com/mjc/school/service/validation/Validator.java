//package com.mjc.school.service.validation;
//
//import com.mjc.school.service.dto.AuthorDtoRequest;
//import com.mjc.school.service.dto.NewsDtoRequest;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Validator {
//    public void validateId(Long id) {
//        if (id == null || id < 1L) {
//            throw new ValidateException("ID of news is incorrect");
//        }
//    }
//
//    public void validateTitle(String title) {
//        if (title.length() < 5) {
//            throw new ValidateException("Length title of news less than 5");
//        }
//        if (title.length() > 30) {
//            throw new ValidateException("Length title of news grate than 30");
//        }
//    }
//
//    public void validateContent(String content) {
//        if (content.length() < 5) {
//            throw new ValidateException("Length content of news less than 5");
//        }
//        if (content.length() > 255) {
//            throw new ValidateException("Length content of news grate than 255");
//        }
//    }
//
//    public void validateAuthorId(Long authorId) {
//        if (authorId == null || authorId < 1L) {
//            throw new ValidateException("Author`s ID is incorrect");
//
//        }
//    }
//
//    public void validateAuthorName(String name) {
//        if (name.length() < 3) {
//            throw new ValidateException("Length author`s name less than 3");
//        }
//        if (name.length() > 15) {
//            throw new ValidateException("Length author`s name grate than 15");
//        }
//    }
//
//    public void validateNewsReq(NewsDtoRequest newsDtoRequest) {
//        validateId(newsDtoRequest.getId());
//        validateTitle(newsDtoRequest.getTitle());
//        validateContent(newsDtoRequest.getContent());
//        validateAuthorId(newsDtoRequest.getAuthorId());
//
//
//    }
//
//    public void validateAuthorReq(AuthorDtoRequest authorDtoRequest) {
//        validateAuthorId(authorDtoRequest.getId());
//        validateAuthorName(authorDtoRequest.getName());
//    }
//
//
//}
