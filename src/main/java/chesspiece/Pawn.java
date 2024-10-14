package chesspiece;

import chesspiece.ChessPiece;
import board.ChessBoard;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = this.color.equals("White") ? 1 : -1;

        int dLine = toLine - line;
        int dColumn = Math.abs(toColumn - column);

        if (dColumn == 0) {
            if (dLine == direction && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }

            if ((this.color.equals("White") && line == 1) || (this.color.equals("Black") && line == 6)) {
                if (dLine == 2 * direction && chessBoard.board[toLine][toColumn] == null
                        && chessBoard.board[line + direction][column] == null) {
                    return true;
                }
            }
        }

        if (dColumn == 1 && dLine == direction) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece != null && !this.color.equals(targetPiece.getColor())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
