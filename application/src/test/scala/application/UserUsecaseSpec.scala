package application

import domainModel.{User, UserId}
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
      override def find(name: String): Option[User] = findUser
      override def find(userId: UserId): Option[User] = findUser
      override def save(user: User): Try[User] = saveUser
    }
    object UserUsecaseForTest extends UserUsecaseModule {
      override lazy val userRepository: UserRepository =
        new UserRepositoryImplForTest()
    }

    UserUsecaseForTest
  }

  "create" should "ユーザーを作成し、ユーザー情報を返す" in {
    assert(UserUsecase.create("なまえ") == Success(dto.User("id", "なまえ")))
  }

  it should "ユーザーを作成せず、失敗を返す （ユーザー情報が不正の場合）" in {
    assert(UserUsecase.create("なま").isFailure)
  }

  it should "ユーザーを作成せず、失敗を返す（すでに存在するユーザーの場合）" in {
    assert(UserUsecase.create("すでにある名前").isFailure)
  }

  it should "ユーザーを作成せず、失敗を返す（DB保存の失敗）" in {
    assert(userUsecaseForTest().create("なまえ").isFailure)
  }

  "find" should "ユーザーを検索し、ユーザー情報を返す" in {
    assert(UserUsecase.find("1") == Success(dto.User("1", "すでにある名前")))
  }

  "find" should "ユーザーを検索せず、失敗を返す" in {
    assert(UserUsecase.find("").isFailure)
  }

}
