package io.github.neelsmith.btreporter

import org.scalatest.FlatSpec
import net.liftweb.json._


class BintrayPackageSpec extends FlatSpec {

val xciteJSON = """
{
  "name":"3.6.0",
  "desc":null,
  "package":"xcite",
  "repo":"maven",
  "owner":"neelsmith",
  "labels":[],
  "published":true,
  "attribute_names":[
    "scalas"
  ],
  "created":"2018-07-13T23:07:57.678Z",
  "updated":"2018-07-13T23:09:33.381Z",
  "released":"",
  "ordinal":31.0,
  "github_release_notes_file":null,
  "github_use_tag_release_notes":false,
  "vcs_tag":null,
  "rating_count":0
}
"""
  implicit val formats = DefaultFormats
  val parsed = parse  (xciteJSON)
  val extracted =   parsed.extract[BintrayPackage]


  "A BintrayPackage" should "have a name property" in {
    assert (extracted.versionName == "3.6.0")
  }

  it should "have a nullable description property" in  {
    assert(extracted.desc == null)
  }

}
