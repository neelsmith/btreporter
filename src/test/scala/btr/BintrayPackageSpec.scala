package io.github.neelsmith.btreporter

import org.scalatest.FlatSpec
import net.liftweb.json._


class BintrayPackageSpec extends FlatSpec {

val xciteJSON = """
{
  "name":"xcite",
  "repo":"maven",
  "owner":"neelsmith",
  "desc":"A cross-platform library for semantic manipulation of scholarly references expressed in URN notation.",
  "labels":[],
  "attribute_names":[
    "maturity"
  ],
  "licenses":[
    "GPL-3.0"
  ],
  "custom_licenses":[],
  "followers_count":0,
  "created":"2017-01-28T18:48:39.767Z",
  "website_url":"",
  "issue_tracker_url":"https://github.com/cite-architecture/xcite/issues",
  "linked_to_repos":[
    "jcenter"
  ],
  "permissions":[],
  "versions":[
    "3.6.0",
    "3.5.0",
    "3.4.0",
    "3.3.0",
    "3.2.2",
    "3.2.1",
    "3.2.0",
    "3.1.0",
    "3.0.1",
    "3.0.0",
    "2.7.1",
    "2.7.0",
    "2.6.0",
    "2.5.1",
    "2.5.0",
    "2.4.0",
    "2.3.2",
    "2.3.1",
    "2.3.0",
    "2.2.3",
    "2.2.2",
    "2.2.1",
    "2.2.0",
    "2.1.0",
    "2.0.1",
    "2.0.0",
    "1.4.0",
    "1.3.0",
    "1.2.0",
    "1.1.0",
    "1.0.0"
  ],
  "latest_version":"3.6.0",
  "updated":"2018-07-13T23:09:33.417Z",
  "rating_count":0,
  "system_ids":[
    "edu.holycross.shot:xcite_sjs0.6_2.12",
    "edu.holycross.shot:xcite_2.12",
    "edu.holycross.shot:xcite_2.11",
    "edu.holycross.shot:xcite_sjs0.6_2.11",
    "edu.holycross.shot.cite:xcite_sjs0.6_2.11",
    "edu.holycross.shot.cite:xcite_2.11",
    "edu.holycross.shot.cite:xcite_sjs0.6_2.12",
    "edu.holycross.shot.cite:xcite_2.12",
    "edu.holycross.shot.cite:xcite_2.10",
    "edu.holycross.shot.cite:xcite_sjs0.6_2.10"
  ],
  "vcs_url":"https://github.com/cite-architecture/xcite.git",
  "maturity":"Development"
}
"""
  implicit val formats = DefaultFormats
  val parsed = parse  (xciteJSON)
  val extracted =   parsed.extract[BintrayPackage]


  "A BintrayPackage" should "have a name property" in {
    assert (extracted.versionName == "xcite")
  }

  it should "have a nullable description property" in {
    val expected = "A cross-platform library for semantic manipulation of scholarly references expressed in URN notation."
    assert(extracted.desc == expected)
  }

}
