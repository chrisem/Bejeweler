package com.cemsoft.bejeweler.domain

import org.scalatest.FunSuite
import Colour._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameBoardTest extends FunSuite {
  
  test("GameBoard can be created from strings") {
    val boardStr = 
      """RGBY
      |WOPR
      |PGGB
      |OWYR""".stripMargin
      
    val board = GameBoard.createfromString(boardStr)
    val expectedBoard = Vector(Vector(Gem(Red), Gem(Green), Gem(Blue), Gem(Yellow)),
    						   Vector(Gem(White), Gem(Orange), Gem(Purple), Gem(Red)),
    						   Vector(Gem(Purple), Gem(Green), Gem(Green), Gem(Blue)),
    						   Vector(Gem(Orange), Gem(White), Gem(Yellow), Gem(Red)))
    assert(board === expectedBoard)
  }

}