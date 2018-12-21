package io.github.neelsmith.btreporter

import com.github.nscala_time.time._
import org.joda.time._


case class BintrayPackage(
  name: String,
  desc: String,
  //package: String,
  owner: String,
  repo: String,
  labels:  List[String],
  //published: Boolean,
  created: String,
  updated: String,
  //released: String,

  versions: List[String]

  //ordinal: BigDecimal,

  //issue_tracker_url: String, versions: List[String]
  ) {

  // create DateTime object from "updated" String
  def updateDT = {
    Instant.parse(updated).toDateTime()
  }
  
  def createdDT = {
    Instant.parse(created).toDateTime()
  }

  def v = versions.toVector
  def latestVersion = versions.head
  def versionName = name

  /*def description: Option[String] = {
    desc masc {
      case null => None
      case _ => Some(desc)
    }
  }*/
}
