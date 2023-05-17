package com.example.webflux.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class DataDto {
    @Getter
    public static class Post{
        private String value;
        private String value2;
    }

    @Getter
    public static class Patch{
        @Setter
        private Long dataId;
        private String value;
        private String value2;
    }

    @Builder
    @Setter
    public static class Response{
        private Long dataId;
        private String value;
        private String value2;
    }
}
