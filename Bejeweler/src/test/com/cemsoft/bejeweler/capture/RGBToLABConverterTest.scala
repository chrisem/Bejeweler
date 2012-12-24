package com.cemsoft.bejeweler.capture

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import java.awt.Color

@RunWith(classOf[JUnitRunner])
class RGBToLABConverterTest extends FunSuite {

  val converter = new RGBToLABConverter

  test("Black RGB to LAB conversion") {
    val rgb = (0.0, 117.0, 216.0)
    val lab = converter.convert(rgb)
    println(lab)
  }
}