package com.example.testtaskwithoutbd.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("home")
@Route("/")
public class MainWindow extends HorizontalLayout {

    private final Button substring, magicSquare;

    public MainWindow(){
        substring = new Button("Substring");
        magicSquare = new Button("Magic Square");
        magicSquare.addClickListener(e -> UI.getCurrent().navigate("/magic-square"));
        substring.addClickListener(e -> UI.getCurrent().navigate("/substring"));
        add(substring, magicSquare);
    }
}
