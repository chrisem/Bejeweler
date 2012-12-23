package com.cemsoft.bejeweler.domain

object Colour extends Enumeration {
  type Colour = Value
  val Red = Value("R")
  val Orange = Value("V")
  val Blue = Value("B")
  val Green = Value("G")
  val White = Value("W")
  val Purple = Value("P")
  val Yellow = Value("Y")
  val Unknown = Value("?")
  
  val charMap = Map('R' -> Red,
                    'O' -> Orange,
                    'B' -> Blue,
                    'G' -> Green,
                    'W' -> White,
                    'P' -> Purple,
                    'Y' -> Yellow,
                    '?' -> Unknown)
                    
  def fromChar(c: Char) = charMap(c)
}

