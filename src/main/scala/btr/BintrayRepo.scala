package io.github.neelsmith.btreporter
import scala.io.Source
import net.liftweb.json._

import scala.annotation.tailrec
/** Class for working with a Bintray repository.
*
* @param owner Name of owner.
* @param repo Name of repository.
*/
case class BintrayRepo(owner: String, repo: String) {

  /** Base URL for packages */
  def packageBase = s"https://api.bintray.com/packages/${owner}/${repo}/"


  /** Create a [[BintrayPackage]] option for a named package.
  *  If the package name is invalid or the package JSON cannot be parsed,
  * return None; otherwise, return Some[BintrayPackage].
  *
  * @param pkg Name of package in this repository.
  */
  def bintrayPackage(pkg: String): Option[BintrayPackage] = {
    implicit val formats = DefaultFormats
    try {
      val pkgJson = Source.fromURL(packageBase + pkg).mkString
      val parsed = parse (pkgJson)
      val bp = parsed.extract[BintrayPackage]
      Some(bp)

    } catch {
      case t : Throwable => {
        println("Failed to build package:  " + t)
        None
      }
    }
  }


  /** Recursively build up a Vector of [[BintrayPackage]] options given a Vector of
  * package names.
  *
  * @param pkgNames Names of packages to report on.
  * @param accumulated.
  */
  @tailrec final def btPackageVector(pkgNames: Vector[String], accumulated: Vector[Option[BintrayPackage]]): Vector[Option[BintrayPackage]] = {
    if (pkgNames.isEmpty) {
      accumulated
    } else {
      val headPackage =  bintrayPackage(pkgNames.head)
      btPackageVector(pkgNames.tail, accumulated :+ headPackage)
    }
  }


  def bintrayPackages(pkgs: Vector[String]): Vector[Option[BintrayPackage]] =  {
    btPackageVector(pkgs, Vector.empty[Option[BintrayPackage]])
  }

}
