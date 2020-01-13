package engine;

import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;

public class Test {
    public static void main(String[] args) {
        ChessController controller = new Board();
        ChessView view = new GUIView(controller);
        controller.start(view);

        controller.newGame();

//        // Test white Pawn normal move
//        System.out.println("Move white Pawn from one case : " + controller.move(5, 1, 5, 2));
//
//        // Test black Pawn first two cases move
//        System.out.println("Move black Pawn from two cases : " + controller.move(1, 6, 1, 4));
//
//        // Test white Pawn invalid two cases move
//        System.out.println("Move white Pawn not on first move from two cases : " + controller.move(5, 1, 5, 4));
//
//        // Move white Pawn from two cases
//        System.out.println("Move white Pawn from two cases : " + controller.move(6, 1, 6, 3));
//
//        // Test black Pawn normal move
//        System.out.println("Move black Pawn from one case : " + controller.move(2, 6, 2, 5));
//
//        // Test white knight move
//        System.out.println("Move white Knight : " + controller.move(6, 0, 7, 2));
//
//        // Test black knight move
//        System.out.println("Move black Knight : " + controller.move(1, 7, 0, 5));
//
//        // Test white bishop move
//        System.out.println("Move white bishop : " + controller.move(5, 0, 6, 1));
//
//        // Test black bishop move
//        System.out.println("Move black bishop : " + controller.move(2, 7, 1, 6));
//
//        // Test white small castling
//        System.out.println("Test small castling : " + controller.move(4, 0, 7, 0));
//
//        // Move black queen
//        System.out.println("Move black queen diagonal : " + controller.move(3, 7, 0, 4));
//
//        // Move white rook left
//        System.out.println("Move white rook left : " + controller.move(5, 0, 4, 0));
//
//        // Test black big castling
//        System.out.println("Move black big castling : " + controller.move(4, 7, 0, 7));
//
//        //Move pawns leading to En Passant
//        controller.move(6, 3, 6, 4);
//        controller.move(5, 6, 5, 4);
//        System.out.println("En Passant : " + controller.move(6, 4, 5, 5));
//
//        //Move black queen and white king leading to check
//        controller.move(0, 4, 1, 3);
//        controller.move(6, 0, 5, 1);
//        controller.move(1, 3, 0, 3);
//        controller.move(5, 1, 4, 2);
//        controller.move(0, 3, 0, 2);
//        System.out.println("White king is now in check.");
//        System.out.println("Move white King still in check pos : " + controller.move(4, 2, 3, 2));
//        System.out.println("Move a different Piece while King in check : " + controller.move(7, 2, 6, 4));
//        System.out.println("Move a Pawn to block the black queen : " + controller.move(2, 1, 2, 2));
//
//        //Black pawn eat white pawn
//        System.out.println("Eat a white Pawn with a black Pawn : " + controller.move(4, 6, 5, 5));
    }
}
