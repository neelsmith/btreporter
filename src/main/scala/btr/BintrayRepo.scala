package io.github.neelsmith.btreporter
import scala.io.Source
import net.liftweb.json._


case class BintrayRepo(owner: String, repo: String) {

  /** Base URL for packages */
  def packageBase = s"https://api.bintray.com/packages/${owner}/${repo}/"

  def bintrayPackage(pkg: String): BintrayPackage = {
    implicit val formats = DefaultFormats
    val pkgJson = Source.fromURL(packageBase + pkg).mkString
    val parsed = parse (pkgJson)
    parsed.extract[BintrayPackage]
  }


  def btPackageVector(pkgNames: Vector[String], accumulated: Vector[BintrayPackage]): Vector[BintrayPackage] = {
    if (pkgNames.isEmpty) {
      accumulated
    } else {
      val headPackage =  bintrayPackage(pkgNames.head)
      btPackageVector(pkgNames.tail, accumulated :+ headPackage)
    }
  }


  def bintrayPackages(pkgs: Vector[String]): Vector[BintrayPackage] =  {
    btPackageVector(pkgs, Vector.empty[BintrayPackage])
  }

}
