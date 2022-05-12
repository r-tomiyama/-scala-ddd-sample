package domainModel.user

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class UserNameSpec extends FlatSpec {

  "apply" should "例外を投げる（文字数不足）" in {
    an[RuntimeException] should be thrownBy UserName("なま")
  }

  it should "例外を投げる（文字数オーバー）" in {
    an[RuntimeException] should be thrownBy UserName("123456789012345678901")
  }

}
