package com.mjc.school.service.dto;




import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public record CommentDtoRequest(
        @NotNull
        @Size(min = 5, max = 255)
        String content
) {


}
