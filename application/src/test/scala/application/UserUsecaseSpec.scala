package application

import domainService.UserService
import infrastructure.UserRepository
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class UserUsecaseSpec extends FlatSpec {

  val userRepository = new UserRepository
  val userService = new UserService(userRepository)
  val userUsecase = new UserUsecase(userService, userRepository)

  "create" should "ユーザーを作成し、成功メッセージを返す" in {
    assert(userUsecase.create("なまえ") === "ユーザ作ったよ")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す （ユーザー情報が不正の場合）" in {
    assert(userUsecase.create("なま") === "ユーザ作れません")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す（すでに存在するユーザーの場合）" in {
    assert(userUsecase.create("すでにある名前") === "ユーザ作れません")
  }

}
