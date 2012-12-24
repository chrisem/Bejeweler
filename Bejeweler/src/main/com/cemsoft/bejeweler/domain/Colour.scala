package com.cemsoft.bejeweler.domain

sealed class Colour(shortName: Char, val lab: (Double, Double, Double)) {
  override def toString = shortName.toString
}

object Red extends Colour('R', (62.32, 59.67, 36.72))
object Orange extends Colour('O', (69.07, 32.76, 74.69))
object Blue extends Colour('B', (49.68, 3.77, -61.37))
object Green extends Colour('G', (70.18, -62.04, 79.95))
object White extends Colour('W', (88.47, 0.00, 0.00))
object Purple extends Colour('P', (49.35, 76.71, -54.55))
object Yellow extends Colour('Y', (78.84, -13.52, 85.43))
object Unknown extends Colour('?', (99999, 99999, 99999))

object Colour {

  private val charMap = Map('R' -> Red,
    'O' -> Orange,
    'B' -> Blue,
    'G' -> Green,
    'W' -> White,
    'P' -> Purple,
    'Y' -> Yellow,
    '?' -> Unknown)

  private val colours = charMap.values.toList

  def fromChar(c: Char) = charMap(c)

  def findClosest(lab: (Double, Double, Double)): Colour = {
    def closeness(colour: Colour) = scala.Math.sqrt(Math.pow(colour.lab._1 - lab._1, 2) + Math.pow(colour.lab._2 - lab._2, 2) + Math.pow(colour.lab._3 - lab._3, 2))
    val colour = colours.map(colour => (colour, closeness(colour))).sortBy(_._2).head._1
    println("From: " + lab + " found: " + colour)
    colour
  }
}

