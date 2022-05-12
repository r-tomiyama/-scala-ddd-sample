package domainService

import domainModel.user.{IUserRepository, User, UserId, UserName}
import org.scalatest.FlatSpec

import scala.util.{Failure, Try}

class UserServiceSpec extends FlatSpec {

  def userServiceForTest(
      findUser: Option[User] = None,
      saveUser: Try[User] = Failure(new RuntimeException(""))
  ): UserService = {
    class UserRepositoryImplForTest extends IUserRepository {
      override def find(name: UserName): Option[User] = findUser
      override def find(userId: UserId): Option[User] = findUser
      override def save(user: User): Try[User] = saveUser
      override def delete(user: User): Try[Unit] = Failure(new RuntimeException)
    }
    new UserService(new UserRepositoryImplForTest())
  }

  "exist" should "trueを返す" in {
    val targetUser = User("id", "なまえ")
    val service = userServiceForTest(findUser = Some(targetUser))
    assert(service.exist(targetUser))
  }

  it should "falseを返す" in {
    val targetUser = User("id", "なまえ")
    val service = userServiceForTest()
    assert(!service.exist(targetUser))
  }

}
