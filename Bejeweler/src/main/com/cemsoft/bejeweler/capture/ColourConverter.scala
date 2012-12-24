package com.cemsoft.bejeweler.capture

import com.cemsoft.bejeweler.domain.Colour

trait ColourConverter {
  def convert(rgb: (Double, Double, Double)): Colour
}

class ColourConverterImpl extends ColourConverter {
  private val converter = new RGBToLABConverter

  def convert(rgb: (Double, Double, Double)) = Colour.findClosest(converter.convert(rgb))
}

