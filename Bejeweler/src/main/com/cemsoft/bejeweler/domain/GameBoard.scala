package com.cemsoft.bejeweler.domain


trait GameBoard {
	type Board = Vector[Vector[Gem]]
	val board: Board
}

case class GameBoardImpl(val board: Vector[Vector[Gem]]) extends GameBoard

object GameBoard {
  def createfromString(board: String) = {
    Vector(board.split("\n").map(rowStr => Vector(rowStr: _*).map(gemChar => Gem(Colour.fromChar(gemChar)))): _*)
  }
}
