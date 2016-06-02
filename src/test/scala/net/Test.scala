package net

import org.scalatest._
import org.scalatest.prop.Checkers
import org.scalacheck.Gen
import org.scalacheck.Gen._

object Test {
  def fooOrBar: Gen[String] = oneOf("foo", "bar")

  def f: Gen[String] = for {
    a <- Gen.const("")
    b <- fooOrBar
  } yield s"$a$b"

}

class Test extends FunSuite with Checkers {

  import Test._

  test("generated String should equal 'foo' or 'bar'") {
    check( org.scalacheck.Prop.forAll(fooOrBar) { x: String =>
      println("x:" + x)
      (x == "foo" || x == "bar") && x.length == 3
    })
  }

  test("generated f should end with 'foo' or 'bar'") {
    check( org.scalacheck.Prop.forAll(f) { x: String =>
      println("x:" + x)
      x.endsWith("foo") || x.endsWith("bar")
    })
  }

}
