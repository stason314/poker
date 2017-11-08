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
public class King extends Figure {

    public King(ChessColor chessColor) {
        color = chessColor;
    }

    @Override
    public List<ChessPosition> move(ChessBoard chessBoard) {
        List<ChessPosition> moveList = new ArrayList<>();
        List<Figure> allFig = new ArrayList<>();
        allFig.addAll(chessBoard.figuresBlack);
        allFig.addAll(chessBoard.figuresWhite);


        ChessPosition y1 = new ChessPosition(x, y - 1);
        ChessPosition y2 = new ChessPosition(x, y + 1);
        ChessPosition x1 = new ChessPosition(x - 1, y);
        ChessPosition x2 = new ChessPosition(x + 1, y);


        moveList.add(y1);
        moveList.add(y2);
        moveList.add(x1);
        moveList.add(x2);

        for (Figure figure : allFig) {
            if ((y - 1 == figure.y && x == figure.x) || y - 1 < 0){
                moveList.remove(y1);
            }
            if ((y + 1 == figure.y && x == figure.x) || y + 1 > 7){
                moveList.remove(y2);
            }
            if ((x - 1 == figure.x && y == figure.y) || x - 1 < 0){
                moveList.remove(x1);
            }
            if ((x + 1 == figure.x && y == figure.y) || x + 1 > 7){
                moveList.remove(x2);
            }
        }



        return moveList;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        try {
            if (color == ChessColor.WHITE){
                image = ImageIO.read(new File("img/whiteKing.png"));
            }
            if (color == ChessColor.BLACK){
                image = ImageIO.read(new File("img/blackKing.png"));
            }

        }catch (IOException e){

        }
        g.drawImage(image, x , y , null);
    }
}
