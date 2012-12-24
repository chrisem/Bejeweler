package com.cemsoft.bejeweler.capture

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import java.io.File
import javax.imageio.ImageIO
import org.scalatest.mock.EasyMockSugar
import org.easymock.EasyMock._
import java.awt.Robot
import org.easymock.EasyMock

@RunWith(classOf[JUnitRunner])
class GameBoardCapturerTest extends FunSuite with EasyMockSugar {
  val topLeftCoord = (645, 164)
  val cellHeight = 72
  val cellWidth = 73
  val noOfCells = 8
  val patchSize = 20
  val captureParams = GameBoardCapturerParams(topLeftCoord, cellHeight, cellWidth, noOfCells, patchSize)
  val mockScreenGrabber = createMock(classOf[Robot])

  test("Test board capture") {
    val cur = new File(".")
    println(cur.getAbsolutePath())
    println(cur.getCanonicalPath())
    val imgFile = new File("src/test/META-INF/example.png")
    val img = ImageIO.read(imgFile)
    val capturer = new GameBoardCapturerImpl(captureParams, screenGrabber = mockScreenGrabber)

    expecting {
      mockScreenGrabber.createScreenCapture(EasyMock.anyObject()).andReturn(img)
    }

    whenExecuting(mockScreenGrabber) {
      val board = capturer.capture
      println(board)
    }
  }

}