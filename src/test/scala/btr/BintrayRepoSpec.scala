package io.github.neelsmith.btreporter

import org.scalatest.FlatSpec
import net.liftweb.json._


class BintrayRepoSpec extends FlatSpec {

  val owner = "neelsmith"
  val repo = "maven"
  val btRepo = BintrayRepo(owner, repo)

  "A BintrayRepo" should "have a name property" in {
    assert (btRepo.owner == owner)
  }

  it should "have a repo property" in {
    assert (btRepo.repo == repo)
  }

  // depends on successful network retrieval...
  it should "construct a BintrayPackage object for a package name" in {
    val btPackage = btRepo.bintrayPackage("xcite").get
    val expectedYear = 2017
    assert(btPackage.createdDT.getYear == expectedYear)
  }

  it should "handle bad package names with a None" in {
    val btPackageOption = btRepo.bintrayPackage("bogus")
    assert(btPackageOption == None)
  }


  it should "construct BintrayPackage objects from a Vector of package names" in  {
    val pkgNames = Vector("xcite", "dse")
    val pkgs = btRepo.bintrayPackages(pkgNames)

    for (i <- 0 until pkgNames.size) {
      assert( pkgNames(i) == pkgs(i).get.name )
    }
  }

  it should "compose a markdown table for a list of package names" in {
    val pList = Vector("xcite", "nada")
    val tab = btRepo.markdownTable(pList)


    //val expected = "\n| Package | Current version | Published | Summary | Github repository | Binary download |\n| :--------|:--------|:--------|:--------|:--------|:--------| \n| `xcite` | **3.6.0** | July, 2018 | A cross-platform library for semantic manipulation of scholarly references expressed in URN notation. | [https://github.com/cite-architecture/xcite.git](https://github.com/cite-architecture/xcite.git) | [ ![Download](https://api.bintray.com/packages/neelsmith/maven/xcite/images/download.svg) ](https://bintray.com/neelsmith/maven/xcite/_latestVersion) |"

  val rows = tab.split("\n").toVector
  val expectedRows = 4
  assert(rows.size == expectedRows)

  // drop blank header line:
  val data = rows.tail
  val expectedLabels = "| Package | Current version | Published | Summary | Github repository | Binary download |"
  assert(data.head == expectedLabels)
  val xciteData = data(2).split("\\|").toVector
  val col1expected = " `xcite` "
  assert(xciteData(1) == col1expected)

  }

  /*it should "support pretty-printing JSON for debugging" in {
    val pkg = "xcite"
    println("DEBUG:\n" + btRepo.debugJson(pkg) )
  }*/


}
