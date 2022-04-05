package application

import domainModel.User
import infrastructure.UserRepository

import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

import scala.util.Failure

class UserUsecaseSpec extends FlatSpec with UserUsecaseModule {

  override lazy val userRepository: UserRepository = mock(
    classOf[UserRepository]
  )

  "create" should "ユーザーを作成し、成功メッセージを返す" in {
    assert(UserUsecase.create("なまえ") === "ユーザ作ったよ")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す （ユーザー情報が不正の場合）" in {
    assert(UserUsecase.create("なま") === "ユーザ作れません")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す（すでに存在するユーザーの場合）" in {
    assert(UserUsecase.create("すでにある名前") === "ユーザ作れません")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す（DB保存の失敗）" in {
    // TODO: mockうまくいっていない
    when(userRepository.save(any[User])).thenReturn(Failure(new Exception))
    assert(UserUsecase.create("なまえ") === "ユーザ作れません")
  }

}
