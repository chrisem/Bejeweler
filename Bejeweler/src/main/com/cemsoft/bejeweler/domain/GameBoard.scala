package com.cemsoft.bejeweler.domain

trait GameBoard {
  type Board = Vector[Vector[Gem]]
  val board: Board
}

case class GameBoardImpl(val board: Vector[Vector[Gem]]) extends GameBoard {
  override def toString = board.map(vg => vg.map(_.colour)).map(_.mkString("|")).mkString("\n")
}

object GameBoard {
  def createfromString(board: String) = {
    GameBoardImpl(Vector(board.split("\n").map(rowStr => Vector(rowStr: _*).map(gemChar => Gem(Colour.fromChar(gemChar)))): _*))
  }
}
