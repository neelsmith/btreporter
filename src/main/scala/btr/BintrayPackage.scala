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
  followers_count: Int,
  created: String,
  website_url: String,
  //rating: String,
  issue_tracker_url: String,
  linked_to_repos:  List[String],
  permissions:  List[String],
  versions: List[String],
  latest_version: String,
  updated: String,
  rating_count:  Int,
  system_ids: List[String],
  vcs_url: String,
  maturity: String
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


  /** Compose markdown string for download link. */
  def dlLink: String = {
    s"[ ![Download](https://api.bintray.com/packages/${owner}/${repo}/${name}/images/download.svg) ](https://bintray.com/${owner}/${repo}/${name}/_latestVersion)"
  }


  /** Format key information as a row of a table in markdown.
  */
  def markdownRow: String = {
    s"| `${name}` | **${latest_version}** | ${monthName(updateDT.getMonthOfYear)} ${updateDT.getDayOfMonth}, ${updateDT.getYear} | ${desc} | [${vcs_url}](${vcs_url}) | ${dlLink} |"
  }


}
