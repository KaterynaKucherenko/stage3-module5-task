package com.mjc.school.service.exceptions;

public enum ErrorCodes {
NO_NEWS_WITH_PROVIDED_ID("01001", "News with this id: a% does not exist."),
    NO_AUTHOR_WITH_PROVIDED_ID("01002", "Author with this id: a% does not exist."),
    NO_TAG_WITH_PROVIDED_ID("01003", "Tag with this id: a% does not exist."),
    NO_COMMENT_WITH_PROVIDED_ID("01004", "Comment with this id: a% does not exist."),
    NO_AUTHOR_FOR_NEWS_ID("01005", "Author for news id: a% does not exist."),
    NO_TAGS_FOR_NEWS_ID("01006", "Tags for news id: a% do not exist."),
    NO_COMMENTS_FOR_NEWS_ID("01007", "Comments for news id: a% do not exist.");
private final String errorMessage;

ErrorCodes (String errorCode, String errMessage){
    this.errorMessage="ERROR_CODE: " + errorCode + "ERROR_MESSAGE: " + errMessage;
}
public String getErrorMessage(){
    return errorMessage;
}

}
