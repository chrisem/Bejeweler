package com.cemsoft.bejeweler.main

import com.cemsoft.bejeweler.capture.GameBoardCapturerParams

object Bejeweler extends App {
  private val topLeftCoord = (645, 164)
  private val cellHeight = 72
  private val cellWidth = 73
  private val noOfCells = 8
  private val patchSize = 20

  val captureParams = GameBoardCapturerParams(topLeftCoord, cellHeight, cellWidth, noOfCells, patchSize)

}