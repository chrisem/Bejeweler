
package com.cemsoft.bejeweler.capture

import com.cemsoft.bejeweler.domain.GameBoard
import java.awt.Robot
import java.awt.Rectangle
import java.awt.image.BufferedImage
import com.cemsoft.bejeweler.domain.Gem
import com.cemsoft.bejeweler.domain.GameBoardImpl

trait GameBoardCapturer {
  def capture: GameBoard
}

class GameBoardCapturerImpl(params: GameBoardCapturerParams, colourConverter: ColourConverter = new ColourConverterImpl, screenGrabber: Robot = new Robot()) {
  type RGB = (Int, Int, Int)

  private val screenArea = new Rectangle(params.topLeft._1,
    params.topLeft._2, params.cellWidth * params.noOfCells, params.cellHeight * params.noOfCells)

  def capture = {
    val screenshot = screenGrabber.createScreenCapture(screenArea);
    val sampleAreas = gemCenters.map(gemSamplePixels)
    val sampleAreasAverageRGB = sampleAreas.map(sampleArea =>
      sampleArea.map(pixel => javaRGBToRGB(pixelRGB(screenshot, pixel)))).map(averageRGB)
    val gems = Vector() ++ (Vector() ++ sampleAreasAverageRGB.map(colourConverter.convert).map(Gem(_))).grouped(params.noOfCells)
    GameBoardImpl(gems)
  }

  private def pixelRGB(image: BufferedImage, coord: (Int, Int)) = image.getRGB(coord._1, coord._2)

  private def averageRGB(rgbs: List[RGB]): (Double, Double, Double) = {
    val size = rgbs.size.asInstanceOf[Double]
    val totals = rgbs.foldLeft((0, 0, 0))((z: (Int, Int, Int), t: (Int, Int, Int)) => (z._1 + t._1, z._2 + t._2, z._3 + t._3))
    (totals._1 / size, totals._2 / size, totals._3 / size)
  }

  private def javaRGBToRGB(rgb: Int): RGB = {
    val red = (rgb >> 16) & 0xFF
    val green = (rgb >> 8) & 0xFF
    val blue = rgb & 0xFF
    (red, green, blue)
  }

  private val gemCenters = for {
    y <- 1 to params.noOfCells
    x <- 1 to params.noOfCells
  } yield ((params.cellWidth * x) - (params.cellWidth / 2),
    (params.cellHeight * y) - (params.cellHeight / 2))

  private def gemSamplePixels(gemCenter: (Int, Int)) = {
    val (centerX, centerY) = gemCenter
    val halfSampleSize = params.sampleAreaSize / 2
    for {
      x <- ((centerX - halfSampleSize) to (centerX + halfSampleSize)).toList
      y <- (centerY - halfSampleSize) to (centerY + halfSampleSize)
    } yield (x, y)
  }
}

case class GameBoardCapturerParams(topLeft: (Int, Int), cellHeight: Int, cellWidth: Int, noOfCells: Int, sampleAreaSize: Int)