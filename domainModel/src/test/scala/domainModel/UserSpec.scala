package domainModel

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class UserSpec extends FlatSpec {

  "apply" should "ユーザーを作成する" in {
    val user = User(UserId("id"), UserName("なまえ"))
    val newUser = User("id", "なまえ")
    assert(user === newUser)
  }

  "from" should "ユーザーを作成する" in {
    val newUser = User.from("なまえ")
    assert(newUser.isSuccess)
  }

  it should "ユーザーに失敗する" in {
    val newUser = User.from("なま")
    assert(newUser.isFailure)
  }

  "changeName" should "名前を変更したユーザーを返す" in {
    val user = User(UserId("id"), UserName("なまえ"))
    val newUser = user.changeName("updatedなまえ")
    assert(newUser.isSuccess)
    assert(newUser.get.name.value == "updatedなまえ")
  }

}
