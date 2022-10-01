package com.example.testtaskwithoutbd.ui.second_test;

import com.example.testtaskwithoutbd.service.MagicSquareService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Route("/magic-square")
public class MagicSquare extends VerticalLayout {

    private final Label label;

    private final HorizontalLayout horizontalLayout1, horizontalLayout2, horizontalLayout3, horizontalLayoutButtons;

    private final TextField el11, el12, el13, el21, el22, el23, el31, el32, el33;

    private final Button go, home;

    private final MagicSquareService magicSquareService;

    private final List<List<TextField>> textFields;

    private final TextField cost;

    private final Dialog exceptionWindow;

    @Autowired
    public MagicSquare(MagicSquareService magicSquareService){
        this.magicSquareService = magicSquareService;
        label = new Label("Magic Square");
        cost = new TextField("Cost");
        cost.setReadOnly(true);
        el11 = new TextField();
        el12 = new TextField();
        el13 = new TextField();

        el21 = new TextField();
        el22 = new TextField();
        el23 = new TextField();

        el31 = new TextField();
        el32 = new TextField();
        el33 = new TextField();

        textFields = new LinkedList<>();
        textFields.add(new LinkedList<>(Arrays.asList(el11, el12, el13)));
        textFields.add(new LinkedList<>(Arrays.asList(el21, el22, el23)));
        textFields.add(new LinkedList<>(Arrays.asList(el31, el32, el33)));

        exceptionWindow = new Dialog(new Label("Illegal format!!!"));
        exceptionWindow.setModal(false);
        exceptionWindow.setHeaderTitle("Error");
        exceptionWindow.setResizable(false);
        Button ok = new Button("Ok");
        ok.addClickListener(e-> {
            for (List<TextField> textFieldList : textFields) {
                for (TextField textField : textFieldList) {
                    textField.setValue("");
                }
            }
            exceptionWindow.close();
        });
        exceptionWindow.add(ok);

        horizontalLayout1 = new HorizontalLayout(el11, el12, el13);
        horizontalLayout2 = new HorizontalLayout(el21, el22, el23);
        horizontalLayout3 = new HorizontalLayout(el31, el32, el33);

        go = new Button("Go");
        home = new Button("Home");
        home.addClickListener(e-> UI.getCurrent().navigate("/"));
        go.addClickListener(e-> showMagicSquare());
        horizontalLayoutButtons = new HorizontalLayout(go, home);

        add(label, horizontalLayout1, horizontalLayout2, horizontalLayout3, cost, horizontalLayoutButtons);
    }

    private void showMagicSquare(){
        try {
            List<List<Integer>> initialSquare = new LinkedList<>();
            initialSquare.add(Arrays.asList(Integer.parseInt(el11.getValue()), Integer.parseInt(el12.getValue()), Integer.parseInt(el13.getValue())));
            initialSquare.add(Arrays.asList(Integer.parseInt(el21.getValue()), Integer.parseInt(el22.getValue()), Integer.parseInt(el23.getValue())));
            initialSquare.add(Arrays.asList(Integer.parseInt(el31.getValue()), Integer.parseInt(el32.getValue()), Integer.parseInt(el33.getValue())));

            initialSquare = magicSquareService.generateMagicSquare(initialSquare);

            for(int i  = 0; i < textFields.size(); ++i){
                for(int j = 0; j < textFields.size(); ++j){
                    textFields.get(i).get(j).setValue(initialSquare.get(i).get(j).toString());
                }
            }
            this.cost.setValue(initialSquare.get(initialSquare.size() - 1).get(0).toString());
        }catch (NumberFormatException e){
            exceptionWindow.open();
        }
    }
}
