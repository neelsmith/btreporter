# Scripting with `btreporter`


You need these imports and implicits:

```tut:silent
import net.liftweb.json._
import io.github.neelsmith.btreporter._
import scala.io.Source

implicit val formats = DefaultFormats
```

Instantiate a repository as a `BintrayRepo` object:

```tut:silent
val owner = "neelsmith"
val repo = "maven"
val btRepo = BintrayRepo(owner, repo)
```


Get a bunch of package objects (`BintrayPackage`s) back for a vector of requested package names:


```tut:silent
val pkgNames = Vector("xcite", "citeobj", "ohco2", "greek")
val pkgs =  btRepo.bintrayPackages(pkgNames).flatten
```

Sort your packages alphabetically by name and get a markdown table for the list:

```tut
val rows = pkgs.sortWith(_.name < _.name).map(_.markdownRow)
btRepo.markdownTableHeader + rows.mkString("\n")
```

To sort by last update, you can compare packages' `updateDT` value.  Since `updateDT` is a `joda-time` date, one easy way to do this is just a numeric comparison on their milliseconds value:

```tut
val lastUpdated = pkgs.sortWith(_.updateDT.getMillis < _.updateDT.getMillis).map(_.markdownRow)
btRepo.markdownTableHeader + rows.mkString("\n")
```
