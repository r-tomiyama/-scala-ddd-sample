package infrastructure

import domainModel.User
import org.scalatest.FlatSpec

class UserRepositorySpec extends FlatSpec {

  val userRepository = new UserRepository

  "find" should "一致するユーザーを返す" in {
    val user = User("id", "なまえ")
    assert(userRepository.find(user) === Some(user))
  }

  it should "Noneを返す" in {
    val user = User("id", "なまえ")
    assert(userRepository.find(user) === None)
  }

}
