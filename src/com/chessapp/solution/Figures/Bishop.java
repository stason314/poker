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
public class Bishop extends Figure {

    double eval[][] = {
            { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0},
            { -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0},
            { -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0},
            { -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0},
            { -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0},
            { -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0},
            { -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0},
            { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0},
    };

    int defaultWeight;

    public Bishop(ChessColor chessColor) {
        color = chessColor;
        weight = 30;
        defaultWeight =weight;

        if (chessColor == ChessColor.BLACK){
            for (int i = 0; i < eval.length; i++){
                for (int j = 0; j < eval.length / 2; j++){
                    double tmp = eval[i][j];
                    eval[i][j] = eval[eval.length - i - 1][eval.length - j - 1];
                    eval[eval.length - i - 1][eval.length - j - 1] = tmp;
                }
            }
        }
    }

    @Override
    public List<ChessPosition> move(ChessBoard chessBoard) {
        List<ChessPosition> moveList = new ArrayList<>();

        int countXm = x - 1;
        for (int countY = y + 1; countY < 8; countY++){
            if (countXm >= 0){
                if (chessBoard.chessTiles[countXm][countY].figure != null){
                    if (chessBoard.chessTiles[countXm][countY].figure.color != color){
                        moveList.add(new ChessPosition(countXm, countY, this));
                    }
                    break;
                }
                if (chessBoard.chessTiles[countXm][countY].figure == null){
                    moveList.add(new ChessPosition(countXm, countY, this));
                }
                countXm--;
            }

        }
        int countXp = x + 1;
        for (int countY = y + 1; countY < 8; countY++){

            if (countXp < 8){
                if (chessBoard.chessTiles[countXp][countY].figure != null){
                    if (chessBoard.chessTiles[countXp][countY].figure.color != color){
                        moveList.add(new ChessPosition(countXp, countY, this));
                    }
                    break;
                }
                if (chessBoard.chessTiles[countXp][countY].figure == null){
                    moveList.add(new ChessPosition(countXp, countY, this));
                }
                countXp++;
            }
        }

        countXm = x - 1;
        for (int countY = y - 1; countY >= 0; countY--){

            if (countXm >= 0){
                if (chessBoard.chessTiles[countXm][countY].figure != null){
                    if (chessBoard.chessTiles[countXm][countY].figure.color != color){
                        moveList.add(new ChessPosition(countXm, countY, this));
                    }
                    break;
                }
                if (chessBoard.chessTiles[countXm][countY].figure == null){
                    moveList.add(new ChessPosition(countXm, countY, this));
                }
                countXm--;
            }
        }
        countXp = x + 1;
        for (int countY = y - 1; countY >= 0; countY--){

            if (countXp < 8){
                if (chessBoard.chessTiles[countXp][countY].figure != null){
                    if (chessBoard.chessTiles[countXp][countY].figure.color != color){
                        moveList.add(new ChessPosition(countXp, countY, this));
                    }
                    break;
                }
                if (chessBoard.chessTiles[countXp][countY].figure == null){
                    moveList.add(new ChessPosition(countXp, countY, this));
                }

                countXp++;
            }
        }
        return moveList;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        try {
            if (color == ChessColor.WHITE){
                image = ImageIO.read(new File("img/whiteBishop.png"));
            }
            if (color == ChessColor.BLACK){
                image = ImageIO.read(new File("img/blackBishop.png"));
            }

        }catch (IOException e){

        }
        g.drawImage(image, x , y , null);
    }

    @Override
    public void step(ChessPosition chessPosition) {
        savePos = new ChessPosition(x, y, this);
        x = chessPosition.x;
        y = chessPosition.y;
        weight = defaultWeight + (int)eval[x][y];

    }

    @Override
    public void undo() {
        step(savePos);
    }
}
