package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private Board board = new Board(1, 1);
    private List<Tile> hand = new ArrayList<Tile>();

    public Tile generateRandomTile() {
        Random random = new Random();
        List<Side> list = new ArrayList<Side>();
        for (int i = 0; i < 6; i++) {
            list.add(new Side(random.nextInt(5)));
        }
        Tile tile = new Tile(list);
        return tile;
    }

    public void initHand() {
        for (int i = 0; i < 15; i++) {
            Tile tile = this.generateRandomTile();
            hand.add(tile);
        }
    }

    public String handToString() {
        String returnstring = "[";
        for (int i = 0; i < this.hand.size(); i++) {
            returnstring += this.hand.get(i).toString();
            returnstring += "\n";
        }
        returnstring += "]";
        return returnstring;
    }

    public void placeTileFromHand(int x, int y) {
        board.addTile(hand.get(hand.size() - 1), x, y);
        hand.remove(hand.size() - 1);
    }

    public void initBoard() {
        board.addTile(generateRandomTile(), 0, 0);
    }

    public Board getBoard() {
        return this.board;
    }

    public List<Tile> getHand() {
        return this.hand;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.initHand();
        main.initBoard();
        System.out.println(main.getHand().get(14));
        main.placeTileFromHand(2, 0);
        System.out.println(main.getBoard().getTile(2, 0));
    }
}
