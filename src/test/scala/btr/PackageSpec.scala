package io.github.neelsmith.btreporter

import org.scalatest.FlatSpec



class PackageSpec extends FlatSpec {

  "The package object" should "convert date-time library's integers to a month name" in {
    assert (monthName(1) == "January")
  }
}
