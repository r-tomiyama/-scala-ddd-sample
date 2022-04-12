package application

import domainModel.User
import domainService.UserService
import infrastructure.UserRepository
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

import scala.util.Failure

class UserUsecaseSpec extends FlatSpec {
  val userRepositoryMock: UserRepository = mock(classOf[UserRepository])

  trait UserUsecaseModuleForTest extends UserUsecaseModule {
    override lazy val userRepository: UserRepository = userRepositoryMock
    override lazy val userService: UserService = mock(classOf[UserService])
  }

  object UserUsecaseForTest extends UserUsecaseModuleForTest

  "create" should "ユーザーを作成し、成功メッセージを返す" in {
    assert(UserUsecase.create("なまえ") == "ユーザ作ったよ")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す （ユーザー情報が不正の場合）" in {
    assert(UserUsecase.create("なま") == "ユーザ作れません")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す（すでに存在するユーザーの場合）" in {
    assert(UserUsecase.create("すでにある名前") == "ユーザ作れません")
  }

  it should "ユーザーを作成せず、エラーメッセージを返す（DB保存の失敗）" in {
    when(userRepositoryMock.save(any[User])).thenReturn(Failure(new RuntimeException))
    assert(UserUsecaseForTest.create("なまえ") === "ユーザ作れません")
  }

}
