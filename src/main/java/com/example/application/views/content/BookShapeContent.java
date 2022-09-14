package com.example.application.views.content;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import java.awt.*;

@Route("shapes")
public class BookShapeContent extends Div {

  @Autowired
  public BookShapeContent() {
    
  }

//  private Image constructUI() {
//    var image = new Image(book.getBookImg(), book.getTitle() + " cover");
//    image.setHeight("70px");
//    image.addClickListener(event -> System.out.println("Должна быть ссылка на книгу"));
//    return image;
//  }
}
