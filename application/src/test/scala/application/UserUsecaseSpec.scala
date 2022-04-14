package application

import domainModel.User
import infrastructure.UserRepository
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

import scala.util.{Failure, Try}

class UserUsecaseSpec extends FlatSpec {

  def userUsecaseForTest(
      findUser: Option[User] = None,
      saveUser: Try[User] = Failure(new RuntimeException(""))
  ): UserUsecaseModule = {
    class UserRepositoryImplForTest extends UserRepository {
      def find(user: User) = findUser
      def save(user: User) = saveUser
    }
    object UserUsecaseForTest extends UserUsecaseModule {
      override lazy val userRepository: UserRepository =
        new UserRepositoryImplForTest()
    }

    UserUsecaseForTest
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
    assert(userUsecaseForTest().create("なまえ") === "ユーザ作れません")
  }

}
