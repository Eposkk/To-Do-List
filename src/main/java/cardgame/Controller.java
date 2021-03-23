package cardgame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller {

    public Button dealHand;
    public Button checkHand;
    public Pane pane;
    public HBox hBox;

    public Text flush;
    public Text cardsOfHearts;
    public Text queenOfSpades;
    public Text sumOfTheFaces;
    public TextField numberOfCards;
    public Pane pane1;
    public HBox hBoxQueens;

    private ArrayList<PlayingCard> hand;
    private final char[] suit={'S','H','D','C'};


    @FXML
    private void dealHand() throws IOException {
        hBox.getChildren().clear();
        pane1.getChildren().clear();
        DeckOfCards cards = new DeckOfCards();
        this.hand = cards.dealHand(Integer.parseInt(numberOfCards.getText()));
        ArrayList<Image> images = getImages();
        for (int i=0;i<images.size();i++){
            ImageView imageView = new ImageView(images.get(i));
            imageView.setX(i*100+10);
            imageView.setY(10);
            imageView.setFitWidth(80);
            imageView.setFitHeight(123);
            hBox.getChildren().add(imageView);
        }
    }

    @FXML
    private void checkHand() throws IOException {
        if (checkFlush()){
            flush.setText("Yes");
        }else {flush.setText("No");}

        sumOfTheFaces.setText(String.valueOf(getSum()));
        ArrayList<Integer> face = getFaces();
        cardsOfHearts.setText(String.valueOf(Collections.frequency(face,1)));
        queenOfSpades.setText("No");
        ArrayList<Image> images = getImagesHearts();
        hBoxQueens.getChildren().clear();
        for (int i=0;i<images.size();i++){
            ImageView imageView = new ImageView(images.get(i));
            imageView.setX(i*100+10);
            imageView.setY(10);
            imageView.setFitWidth(80);
            imageView.setFitHeight(123);
            hBoxQueens.getChildren().add(imageView);
        }
    }

    private ArrayList<Image> getImages() throws FileNotFoundException {
        ArrayList<Image> images=new ArrayList<>();
        for (PlayingCard cards:hand){
            Image image=new Image(new FileInputStream("src/main/resources/PNG/"+cards.getFace()+cards.getSuit()+".png"));
            images.add(image);
        }
        return images;
    }

    private ArrayList<Image> getImagesHearts() throws FileNotFoundException{
        ArrayList<Image> images=new ArrayList<>();
        ArrayList<PlayingCard> cardsOnlyHearts = (ArrayList<PlayingCard>)hand.stream().filter(p -> p.getSuit()=='H').collect(Collectors.toList());
        for (PlayingCard cards:cardsOnlyHearts){
            Image image=new Image(new FileInputStream("src/main/resources/PNG/"+cards.getFace()+cards.getSuit()+".png"));
            images.add(image);
        }
        return images;
    }

    private ArrayList<Integer> getFaces(){
        ArrayList<Integer> face = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            for (int j=0;j<suit.length;j++){
                if (hand.get(i).getSuit()==suit[j]){
                    face.add(j);
                }
            }
        }
        return face;
    }

    private boolean checkFlush() {
        ArrayList<Integer> face = getFaces();
        for (int i=0; i<suit.length;i++){
            if (Collections.frequency(face,i)>=5){
                return true;
            }
        }
        return false;
    }


    private int getSum(){
        int sum=0;
        for (PlayingCard cards:hand){
            sum+=cards.getFace();
        }
        return sum;
    }
}

