package application

import domainModel.user.{User, UserId, UserName, IUserRepository}
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

import scala.util.{Failure, Success, Try}

class UserUsecaseSpec extends FlatSpec {

  def userUsecaseForTest(
      findUser: Option[User] = None,
      saveUser: Try[User] = Failure(new RuntimeException("")),
      deleteResult: Try[Unit] = Failure(new RuntimeException(""))
  ): UserUsecaseModule = {
    class UserRepositoryImplForTest extends IUserRepository {
      override def find(name: UserName): Option[User] = findUser
      override def find(userId: UserId): Option[User] = findUser
      override def find(userIds: List[UserId]): List[User] = List()
      override def save(user: User): Try[User] = saveUser
      override def delete(user: User): Try[Unit] = deleteResult
    }
    object UserUsecaseForTest extends UserUsecaseModule {
      override lazy val userRepository: IUserRepository =
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

  it should "ユーザーを検索せず、失敗を返す" in {
    assert(UserUsecase.find("").isFailure)
  }

  "update" should "ユーザーを名更新し、ユーザー情報を返す" in {
    assert(UserUsecase.update("1", "新しい名前") == Success(dto.User("1", "新しい名前")))
  }

  it should "ユーザーを更新せず、失敗を返す（存在しないID）" in {
    assert(UserUsecase.update("999", "新しい名前").isFailure)
  }

  it should "ユーザーを更新せず、失敗を返す（すでに存在するユーザー名の場合）" in {
    assert(UserUsecase.update("1", "すでにある名前").isFailure)
  }

  it should "ユーザーを更新せず、失敗を返す（不正な名前）" in {
    assert(UserUsecase.update("1", "なま").isFailure)
  }

  "delete" should "ユーザーを削除し、成功を返す" in {
    assert(UserUsecase.delete("1").isSuccess)
  }

  it should "ユーザーを削除せず、失敗を返す（存在しないユーザー）" in {
    assert(userUsecaseForTest().delete("1").isFailure)
  }

}
