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
    val btPackage = btRepo.bintrayPackage("xcite")
    val expectedYear = 2017
    assert(btPackage.createdDT.getYear == expectedYear)
  }

  it should "construct BintrayPackage objects from a Vector of package names" in  {
    val pkgNames = Vector("xcite", "dse")
    val pkgs = btRepo.bintrayPackages(pkgNames)

    for (i <- 0 until pkgNames.size) {
      assert( pkgNames(i) == pkgs(i).name )
    }
  }



}
