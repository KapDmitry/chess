package chesspiece;

import board.ChessBoard;

public class Horse extends ChessPiece {

    public Horse(String color) {
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

        int dLine = Math.abs(toLine - line);
        int dColumn = Math.abs(toColumn - column);

        boolean isValidMove = (dLine == 2 && dColumn == 1) || (dLine == 1 && dColumn == 2);

        if (!isValidMove) {
            return false;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

        if (targetPiece != null) {
            return !this.color.equals(targetPiece.getColor());
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}

