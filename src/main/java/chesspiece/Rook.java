package chesspiece;
import board.ChessBoard;

public class Rook extends ChessPiece {

    public Rook(String color) {
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

        if (line != toLine && column != toColumn) {
            return false;
        }

        if (!isPathClear(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

        boolean canMove = true;
        if (targetPiece != null) {
            canMove = !this.color.equals(targetPiece.getColor());
        }

        if (canMove) {
            this.check = false;
        }

        return canMove;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}

