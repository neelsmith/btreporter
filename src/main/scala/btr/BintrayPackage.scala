package io.github.neelsmith.btreporter

import com.github.nscala_time.time._
import org.joda.time._


/** Bintray documentation of a package.
*
* @param name Package name (a version identifier).
* @param desc Fuller description of the package.
* @param owner Name of package owner.
* @param repo Name of bintray repository of package.
* @param labels List of labels for package.
* @param created Date package was created, as ISO 8601 String.
* @param updated Date package was last modified, as ISO 8601 String.
* @param versions Ordered list of version identifiers.
*/
case class BintrayPackage(
  name: String,
  repo: String,
  owner: String,
  desc: String,
  labels:  List[String],
  attribute_names : List[String],
  licenses: List[String],
  //followers_count: String,
  created: String,
  //website_url: String,
  //rating: String,
  issue_tracker_url: String,
  linked_to_repos:  List[String],
  permissions:  List[String],
  versions: List[String],
  latest_version: String,
  rating_count:  Int,
  system_ids: List[String],
  updated: String //,
  //vcs_url: String
  //attributes:  Map[String, Any]

  ) {

  /** Date of last modification of package as a  DateTime object.  */
  def updateDT = {
    Instant.parse(updated).toDateTime()
  }


  /** Date of package's creation as a  DateTime object.  */
  def createdDT = {
    Instant.parse(created).toDateTime()
  }


  /** Scala Vector  of package's versions. */
  def v = versions.toVector

  /** */
  def latestVersion = versions.head
  def versionName = name



  def formatRow(btp: BintrayPackage): String = {
    val github = btp.issue_tracker_url.replaceAll("/issues", "")

    s"| ${btp.name} | **${btp.latest_version}** | ${monthName(btp.updateDT.getMonthOfYear)}, ${btp.updateDT.getYear} | ${btp.desc} | [${github}](${github}) |"
  }

  /*def description: Option[String] = {
    desc masc {
      case null => None
      case _ => Some(desc)
    }
  }*/
}
