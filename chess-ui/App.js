import React, { useState } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';

const CHESS_BOARD_SIZE = 8;

export default function App() {
  const [board, setBoard] = useState(getInitialBoard());
  var arrayOfColsRef=["a","b","c","d","e","f","g","h"]
  function getInitialBoard() {
   const board = [];
   for (let i=0;i<8;i++){
      board.push([])
      for(let j=0;j<8;j++){
         board[i].push({"piece":null})
      }
   }
   // Set up the pawns
   for (let col = 0; col < CHESS_BOARD_SIZE; col++) {
     board[1][col].piece = '♙';
     board[6][col].piece = '♟︎';
   }
 
   // Set up the rooks
   board[0][0].piece = '♖';
   board[0][7].piece = '♖';
   board[7][0].piece = '♜';
   board[7][7].piece = '♜';
 
   // Set up the knights
   board[0][1].piece = '♘';
   board[0][6].piece = '♘';
   board[7][1].piece = '♞';
   board[7][6].piece = '♞';
 
   // Set up the bishops
   board[0][2].piece = '♗';
   board[0][5].piece = '♗';
   board[7][2].piece = '♝';
   board[7][5].piece = '♝';
 
   // Set up the queens
   board[0][3].piece = '♕';
   board[7][3].piece = '♛';
 
   // Set up the kings
   board[0][4].piece = '♔';
   board[7][4].piece = '♚';
 
   // Fill in the rest of the board with empty squares
   for (let row = 2; row < 6; row++) {
     for (let col = 0; col < CHESS_BOARD_SIZE; col++) {
       board[row][col].piece = null;
     }
   }
 
   return board;
 }

  function renderSquare(square,row,col) {
    return (
      <TouchableOpacity style={styles.square} key={`${square.row}${square.col}`}>
        {square.piece && <Text className={`${arrayOfColsRef[col]}`+`${row}`} style={styles.piece}>{square.piece}</Text>}
      </TouchableOpacity>
    );
  }

  function renderBoard() {
    const boardRows = [];

    for (let row = 0; row < CHESS_BOARD_SIZE; row++) {
      const currentRow = [];

      for (let col = 0; col < CHESS_BOARD_SIZE; col++) {
        currentRow.push(renderSquare(board[row][col],row,col));
      }

      boardRows.push(
        <View style={styles.boardRow} key={row}>
          {currentRow}
        </View>
      );
    }

    return boardRows;
  }

  return <View style={styles.container}>{renderBoard()}</View>;
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  boardRow: {
    flexDirection: 'row',
  },
  square: {
    width: 40,
    height: 40,
    backgroundColor: 'grey',
    justifyContent: 'center',
    alignItems: 'center',
    border:".2px solid black"
  },
  piece: {
    fontSize: 24,
    color: 'white',
  },
});