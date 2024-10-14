package chesspiece;
import board.ChessBoard;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }


    private boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int deltaX = (toLine - line) > 0 ? 1 : -1;
        int deltaY = (toColumn - column) > 0 ? 1 : -1;
        int currentLine = line + deltaX;
        int currentColumn = column + deltaY;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += deltaX;
            currentColumn += deltaY;
        }

        return true;
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

        if (dLine != dColumn) {
            return false;
        }

        if (!isPathClear(chessBoard, line, column, toLine, toColumn)) {
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
        return "B"; // Символ слона
    }
}

