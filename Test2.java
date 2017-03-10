import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;

public class Test2 extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("FindWord");
        Group root = new Group();

        final TextArea textArea = TextAreaBuilder.create()
                .prefWidth(400)
                .wrapText(true)
                .build();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("noborder-scroll-pane");
        scrollPane.setContent(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(180);

        Button knopka = new Button();
        knopka.setText("Поиск:");
        knopka.setTranslateX(300);
        knopka.setTranslateY(5);
        knopka.setPrefSize(90, 30);

        TextField name = new TextField();
        name.setPromptText("Введите ваше слово:");
        name.setTranslateY(210);
        name.setTranslateX(5);


        TextField puti_k_failu = new TextField();
        puti_k_failu.setPromptText("Путь к файлу:");
        puti_k_failu.setTranslateY(0);
        puti_k_failu.setTranslateX(222);
        puti_k_failu.setVisible(false);


        Button buttonLoad = new Button("Загрузить текст:");
        buttonLoad.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                // фильтрирукт только txt.file
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                //Show save file dialog
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    textArea.setText(readFile(file));
                    String abspath=file.getAbsolutePath();
                    puti_k_failu.setText(abspath);
                }
            }

        });

        VBox vBox = VBoxBuilder.create()
                .children(buttonLoad, scrollPane, knopka)
                .build();


        knopka.setOnAction(event -> {
            try {
                go_program(name.getText(), puti_k_failu.getText());

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Такого файла не существует !");
                e.printStackTrace();
            }
        });


        root.getChildren().addAll(vBox, name, puti_k_failu);
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }


    public String readFile(File file) {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            String maintext;
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((maintext = bufferedReader.readLine()) != null) {
                stringBuffer.append(maintext);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return stringBuffer.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {

        launch(args);
    }

    public static void go_program(String text, String puti) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(puti));
        Cod path = new Cod();
        int z = path.function(scan, text);
        JOptionPane.showMessageDialog(null, "Количество совпадений:" + z);
        //System.exit(0);
    }
}