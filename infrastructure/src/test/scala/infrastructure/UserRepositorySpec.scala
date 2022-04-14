package infrastructure

import domainModel.{User, UserId}
import org.scalatest.FlatSpec

class UserRepositorySpec extends FlatSpec {

  val userRepository = new UserRepositoryImpl

  "find" should "一致するユーザーを返す（名前検索）" in {
    val user = User("1", "すでにある名前")
    assert(userRepository.find(user.name.value) === Some(user))
  }

  it should "一致するユーザーを返す（ID検索）" in {
    val user = User("1", "すでにある名前")
    assert(userRepository.find(UserId("1")) === Some(user))
  }

  it should "Noneを返す（名前検索）" in {
    val user = User("id", "なまえ")
    assert(userRepository.find(user.name.value) === None)
  }

  it should "Noneを返す（ID検索）" in {
    val user = User("2", "なまえ")
    assert(userRepository.find(UserId("2")) === None)
  }

}
