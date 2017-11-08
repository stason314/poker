package com.chessapp.solution.Figures;

import com.chessapp.solution.ChessBoard;
import com.chessapp.solution.ChessPosition;
import com.chessapp.solution.enums.ChessColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav on 05.11.2017.
 */
public class Queen extends Figure {

    public Queen(ChessColor chessColor) {
        color = chessColor;
    }

    @Override
    public List<ChessPosition> move(ChessBoard chessBoard) {
        List<ChessPosition> moveList = new ArrayList<>();
        List<Figure> allFig = new ArrayList<>();
        allFig.addAll(chessBoard.figuresBlack);
        allFig.addAll(chessBoard.figuresWhite);

        if (color == ChessColor.WHITE){
            ChessPosition y1 = new ChessPosition(x, y - 1);
            ChessPosition y2 = new ChessPosition(x, y - 2);

            moveList.add(y1);
            moveList.add(y2);

            for (Figure figure : allFig) {
                if ((y - 1 == figure.y && x == figure.x) || y - 1 < 0){
                    moveList.remove(y1);
                }
                if ((y - 2 == figure.y && x == figure.x) || y - 2 < 0) {
                    moveList.remove(y2);
                }
            }
        }


        if (color == ChessColor.BLACK) {
            ChessPosition y1 = new ChessPosition(x, y + 1);
            ChessPosition y2 = new ChessPosition(x, y + 2);

            moveList.add(y1);
            moveList.add(y2);

            for (Figure figure : allFig) {
                if ((y + 1 == figure.y && x == figure.x) || y + 1 > 7){
                    moveList.remove(y1);
                }
                if ((y + 2 == figure.y && x == figure.x) || y + 2 > 7) {
                    moveList.remove(y2);
                }
            }
        }
        return moveList;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        try {
            if (color == ChessColor.WHITE){
                image = ImageIO.read(new File("img/whiteQueen.png"));
            }
            if (color == ChessColor.BLACK){
                image = ImageIO.read(new File("img/blackQueen.png"));
            }

        }catch (IOException e){

        }
        g.drawImage(image, x , y , null);
    }
}