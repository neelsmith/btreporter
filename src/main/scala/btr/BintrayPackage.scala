package io.github.neelsmith.btreporter

import com.github.nscala_time.time._
import org.joda.time._


case class BintrayPackage(name: String, repo: String, desc: String, created: String, latest_version: String, updated: String, issue_tracker_url: String, versions: List[String]) {

  // create DateTime object from "updated" String
  def dt = {
    Instant.parse(updated).toDateTime()
  }

  def v = versions.toVector
}

case class BintrayVersion(name: String, updated: String) {
  def dt = {
    Instant.parse(updated).toDateTime()
  }
}
