package com.cemsoft.bejeweler.capture

import com.cemsoft.bejeweler.domain.Colour._

trait ColourConverter {
  def convert(rgb: (Int, Int, Int)): Colour
}