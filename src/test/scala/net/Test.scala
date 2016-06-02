package net

import org.scalatest._
import org.scalatest.prop.Checkers
import org.scalacheck.Gen
import org.scalacheck.Gen._

object Test {
  def fooOrBar: Gen[String] = oneOf("foo", "bar")
}

class Test extends FunSuite with Checkers {

  import Test.fooOrBar

  test("generated String should equal 'foo' or 'bar'") {
    check( org.scalacheck.Prop.forAll(fooOrBar) { x: String =>
      println("x: " + x)
      x == "foo" || x == "bar"
    })
  }

}
