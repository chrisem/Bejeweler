package com.cemsoft.bejeweler.capture

import java.awt.color.ColorSpace
import java.lang.Math

class RGBToLABConverter {

  def convert(rgb: (Double, Double, Double)) = toLAB(toXYZ(rgb))

  private def toXYZ(rgb: (Double, Double, Double)) = {
    def calc(value: Double) = {
      val ratio = value / 255.0
      val newValue = if (ratio > 0.04045) Math.pow((ratio + 0.055) / 1.055, 2.4) else ratio / 12.92
      newValue * 100
    }

    val v = (calc(rgb._1), calc(rgb._2), calc(rgb._3))

    val x = v._1 * 0.4124 + v._2 * 0.3576 + v._3 * 0.1805
    val y = v._1 * 0.2126 + v._2 * 0.7152 + v._3 * 0.0722
    val z = v._1 * 0.0193 + v._2 * 0.1192 + v._3 * 0.9505

    (x, y, z)
  }

  private def toLAB(xyz: (Double, Double, Double)) = {
    val refX = 95.047
    val refY = 100.000
    val refZ = 108.883

    val varX = xyz._1 / refX
    val varY = xyz._2 / refY
    val varZ = xyz._3 / refZ

    def calc(value: Double) = if (value > 0.008856) Math.pow(value, (1.0 / 3.0)) else (7.787 * value) + (16 / 116)

    val v = (calc(varX), calc(varY), calc(varZ))

    val l = (116 * v._2) - 16
    val a = 500 * (v._1 - v._2)
    val b = 200 * (v._2 - v._3)

    (l, a, b)
  }

}