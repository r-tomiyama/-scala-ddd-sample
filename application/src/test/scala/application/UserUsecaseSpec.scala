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
  private lazy val userRepositoryMock: UserRepository = mock(classOf[UserRepository])
  private lazy val userServiceMock: UserService = mock(classOf[UserService])

  private val userUsecaseModule: UserUsecaseModule = new UserUsecaseModule {
    override lazy val userRepository = userRepositoryMock
    override lazy val userService = userServiceMock
  }

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
    // TODO: mockうまくいっていない
    when(userRepositoryMock.save(any[User])).thenReturn(Failure(new RuntimeException))
    assert(userUsecaseModule.create("なまえ") === "ユーザ作れません")
  }

}
