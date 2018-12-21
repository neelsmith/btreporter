package io.github.neelsmith

package object btreporter {

  
  // 1-origin index of months, as in date-time libs.
  // Will die a miserable death if index is not in 1..12
  def monthName(index: Int): String = {
    require(1 to 12 contains index, s"Index ${index} out of range: must be value in 1-12.")
    val months = Vector (
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December"
    )
    months(index - 1)
  }
}
