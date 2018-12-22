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
  * package names and of previously accumluated package options.
  *
  * @param pkgNames Names of packages to report on.
  * @param accumulated Previously accumulated [[BintrayPackage]] options.
  */
  @tailrec final def btPackageVector(pkgNames: Vector[String], accumulated: Vector[Option[BintrayPackage]]): Vector[Option[BintrayPackage]] = {
    if (pkgNames.isEmpty) {
      accumulated
    } else {
      val headPackage =  bintrayPackage(pkgNames.head)
      btPackageVector(pkgNames.tail, accumulated :+ headPackage)
    }
  }

  /** Recursively build up a Vector of [[BintrayPackage]] options given a Vector of
  * package names.
  *
  * @param pkgs Vector of package names to report on.
  */
  def bintrayPackages(pkgs: Vector[String]): Vector[Option[BintrayPackage]] =  {
    btPackageVector(pkgs, Vector.empty[Option[BintrayPackage]])
  }


  def markdownHeader: String = {
    val headerLabels = Vector("Package", "Current version", "Published", "Summary", "Github repository", "Binary download")
    val row1 = "| " + headerLabels.mkString(" | ") + " |\n"
    val row2vals = for (i <- 0 until headerLabels.size) yield { ":--------|"}
    row1 + "| " + row2vals.mkString + " |\n"
  }


  def mdForPackages(pkgs : Vector[BintrayPackage]): String = {
    val rows = for (p <- pkgs) yield {
      p.markdownRow
    }
    "\n" + markdownHeader + rows.mkString("\n") + "\n\n"
  }

  def markdownTable (pkgNames: Vector[String]) : String = {
    val pkgs = bintrayPackages(pkgNames).flatten
    if (pkgs.size < 1) {
      "\n\nNo packages found for names " + pkgNames.mkString(", ")
    } else {
      mdForPackages(pkgs)
    }
  }

  def markdownTableByDate(pkgNames: Vector[String]) : String = {
    val pkgs = packagesByDate(pkgNames)
    if (pkgs.size < 1) {
      "\n\nNo packages found for names " + pkgNames.mkString(", ")
    } else {
      mdForPackages(pkgs)
    }
  }


  /** Given a list of package names, order a Vector of [[BintrayPackage]]s by their last modified date, with most recent first.  Bad names are silently ignored.  To get a full Vector of `Option[BintrayPackage]`s, use the [[bintrayPackages]] function.
  *
  *  @param pkgNames Names of packages to collect.
  */
  def packagesByDate(pkgNames: Vector[String]): Vector[BintrayPackage] =  {
    def pkgs = bintrayPackages(pkgNames).flatten
    pkgs.sortWith( _.updateDT.getMillis > _.updateDT.getMillis)
  }



  /** Pretty print JSON string for a given package's data.
  *
  * @param pkg Name of package to debug.
  */
  def debugJson(pkg: String): String = {
    val pkgJson = Source.fromURL(packageBase + pkg).mkString
    prettyRender(parse(pkgJson))
  }

}
