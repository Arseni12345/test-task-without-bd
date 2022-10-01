package com.example.testtaskwithoutbd.ui.first_test;

import com.example.testtaskwithoutbd.service.SubstringService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Route("/substring")
public class FirstTest extends VerticalLayout {
    private final SubstringService substringService;

    private final TextArea substringsArea;

    private final TextArea sourceStringsArea;

    private final TextArea result;

    private final Button go, mainPage;

    private final Label title;

    private final HorizontalLayout horizontalLayout;

    @Autowired
    public FirstTest(SubstringService subStringService) {
        this.substringService = subStringService;
        title = new Label("Operation with string");
        substringsArea = new TextArea("Type substrings");
        sourceStringsArea = new TextArea("Type strings");
        substringsArea.setPlaceholder("Type something");
        sourceStringsArea.setPlaceholder("Type something");
        result = new TextArea("Result");
        result.setReadOnly(true);

        go = new Button("Go");
        mainPage = new Button("Home");
        go.addClickListener(e -> result.setValue(processStrings(substringsArea.getValue(), sourceStringsArea.getValue())));
        mainPage.addClickListener(e->UI.getCurrent().navigate("/"));

        horizontalLayout = new HorizontalLayout(go, mainPage);
        add(title, substringsArea, sourceStringsArea, result, horizontalLayout);
    }

    private String processStrings(String substrings, String sourceStrings){
        List<String> substringsList = new LinkedList<>(Arrays.asList(substrings.split(" ")));
        List<String> sourceStringsList = new LinkedList<>(Arrays.asList(sourceStrings.split(" ")));

        List<String> result = this.substringService.substrings(substringsList, sourceStringsList);
        StringBuilder builder = new StringBuilder();
        for(String s: result){
            builder.append(s);
            builder.append(' ');
        }

        return builder.toString();
    }
}
