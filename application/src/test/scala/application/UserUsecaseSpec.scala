package application

import domainModel.User
import infrastructure.UserRepository
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

import scala.util.{Failure, Success, Try}

class UserUsecaseSpec extends FlatSpec {

  def userUsecaseForTest(
      findUser: Option[User] = None,
      saveUser: Try[User] = Failure(new RuntimeException(""))
  ): UserUsecaseModule = {
    class UserRepositoryImplForTest extends UserRepository {
      override def find(user: User): Option[User] = findUser
      override def save(user: User): Try[User] = saveUser
    }
    object UserUsecaseForTest extends UserUsecaseModule {
      override lazy val userRepository: UserRepository =
        new UserRepositoryImplForTest()
    }

    UserUsecaseForTest
  }

  "create" should "ユーザーを作成し、成功メッセージを返す" in {
    assert(UserUsecase.create("なまえ") == Success(dto.User("id", "なまえ")))
  }

  it should "ユーザーを作成せず、エラーメッセージを返す （ユーザー情報が不正の場合）" in {
    assert(UserUsecase.create("なま").isFailure)
  }

  it should "ユーザーを作成せず、エラーメッセージを返す（すでに存在するユーザーの場合）" in {
    assert(UserUsecase.create("すでにある名前").isFailure)
  }

  it should "ユーザーを作成せず、エラーメッセージを返す（DB保存の失敗）" in {
    assert(userUsecaseForTest().create("なまえ").isFailure)
  }

}
