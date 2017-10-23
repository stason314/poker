package com.chessapp.solution.Figures;

import com.chessapp.solution.ChessBoard;
import com.chessapp.solution.ChessPosition;
import com.chessapp.solution.enums.ChessColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav on 20.10.2017.
 */
public class Pawn extends Figure {

    ChessPosition chessPosition;

    public Pawn(ChessColor chessColor) {
        color = chessColor;
        x = 0;
        y = 0;
        chessPosition = new ChessPosition(x, y);
    }

    @Override
    public List<ChessPosition> move(ChessBoard chessBoard) {
        List<ChessPosition> moveList = new ArrayList<>();
        if (color == ChessColor.WHITE){
            for (Pawn pawn: chessBoard.pawnsBlack){
                if (y - 1 != pawn.y && x == pawn.x){
                    moveList.add(new ChessPosition(x, y - 1));
                }
                if (y - 2 != pawn.y && x == pawn.x){
                    moveList.add(new ChessPosition(x, y - 2));
                }
            }
        }


        for (int i = 0; i < moveList.size(); i++){
                if (moveList.get(i).y == y){
                   moveList.remove(i);
                }
                if (moveList.get(i).y > 7){
                    moveList.remove(i);
                    moveList.remove(i);
                }
                if (moveList.get(i).y - 1 < 0){
                    moveList.remove(i);
                }
        }


        return moveList;
    }

    @Override
    public void draw(Graphics2D g,int x, int y) {
        Color black = new Color(0);
        Color white = new Color(255, 2, 0);
        if (color == color.BLACK){
            g.setColor(black);
        }
        if (color == color.WHITE){
            g.setColor(white);
        }

        g.drawString("P", x + 25, y + 25);
    }

    @Override
    public void update() {
        int randYB = 1 +(int) (Math.random() * 3);
        int randYW = 1 +(int) (Math.random() * 3);

        if (color == color.BLACK){
            y += 1;
        }
        if (color == color.WHITE){
            y -= 5;
        }
        if (y >= 7){
            y = 7;
        }
        if (y <= 0){
            y = 0;
        }

    }
}
