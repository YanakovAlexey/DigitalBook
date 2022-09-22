package com.example.application.backEnd.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadBookModel {

    @Min(value = 6, message = "Заголовок должен содержать минимум 6 симоволов")
    private String title;
    @NotBlank(message = "Fill fields description and author")
    private String description, author;
    private String genre;
    private String bookPath;
    private String thumbnailPath;
}