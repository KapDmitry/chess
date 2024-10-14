package chesspiece;
import board.ChessBoard;

public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    private boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int deltaX = Integer.compare(toLine, line);
        int deltaY = Integer.compare(toColumn, column);

        int currentLine = line + deltaX;
        int currentColumn = column + deltaY;

        while (currentLine != toLine || currentColumn != toColumn) {
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

        boolean isStraightMove = (line == toLine || column == toColumn);
        boolean isDiagonalMove = (Math.abs(toLine - line) == Math.abs(toColumn - column));

        if (!isStraightMove && !isDiagonalMove) {
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
        return "Q";
    }
}

