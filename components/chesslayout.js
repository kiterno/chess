
import React, { useState } from 'react';
import { View, StyleSheet, TouchableOpacity } from 'react-native';

const ChessBoard = () => {
  const [board, setBoard] = useState([
    ['R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'],
    ['P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'],
    ['', '', '', '', '', '', '', ''],
    ['', '', '', '', '', '', '', ''],
    ['', '', '', '', '', '', '', ''],
    ['', '', '', '', '', '', '', ''],
    ['p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'],
    ['r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'],
  ]);

  const handleSquarePress = (rowIndex, colIndex) => {
    // TODO: handle square press logic
  };

  return (
    <View style={styles.container}>
      {board.map((row, rowIndex) => (
        <View style={styles.row} key={rowIndex}>
          {row.map((square, colIndex) => (
            <TouchableOpacity
              style={[
                styles.square,
                (rowIndex + colIndex) % 2 === 0 ? styles.whiteSquare : styles.blackSquare,
              ]}
              key={colIndex}
              onPress={() => handleSquarePress(rowIndex, colIndex)}
            >
              {/* display the piece image here */}
            </TouchableOpacity>
          ))}
        </View>
      ))}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'column',
  },
  row: {
    flexDirection: 'row',
  },
  square: {
    width: '12.5%',
    aspectRatio: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  whiteSquare: {
    backgroundColor: '#fff',
  },
  blackSquare: {
    backgroundColor: '#555',
  },
});

export default ChessBoard;
