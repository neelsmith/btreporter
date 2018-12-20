package io.github.neelsmith.btreporter

import org.scalatest.FlatSpec



class PackageSpec extends FlatSpec {

  "The package object" should "convert date-time library's integers to a month name" in {
    assert (monthName(1) == "January")
    assert (monthName(2) == "February")
    assert (monthName(3) == "March")
    assert (monthName(4) == "April")
    assert (monthName(5) == "May")
    assert (monthName(6) == "June")
    assert (monthName(7) == "July")
    assert (monthName(8) == "August")
    assert (monthName(9) == "September")
    assert (monthName(10) == "October")
    assert (monthName(11) == "November")
    assert (monthName(12) == "December")
  }

  it should "fail if index is greater than 12" in {
    try {
      monthName(13)
      fail("Should not have accepted index of 13")
    } catch {
      case iae: IllegalArgumentException => assert(true)
      case t: Throwable => {
        println("Should have found IllegalArgumentException, but got: " + t)
        throw t
      }
    }
  }

  it should "fail if index is less than 1" in {
    try {
      monthName(0)
      fail("Should not have accepted index of 0")
    } catch {
      case iae: IllegalArgumentException => assert(true)
      case t: Throwable => {
        println("Should have found IllegalArgumentException, but got: " + t)
        throw t
      }
    }
  }
}
