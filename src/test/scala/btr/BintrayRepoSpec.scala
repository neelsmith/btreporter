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


  it should "support pretty-printing JSON for debugging" in {
    val pkg = "xcite"
    println("DEBUG:\n" + btRepo.debugJson(pkg) )
  }


}
