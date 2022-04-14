package domainService

import domainModel.{User, UserId, UserName}
import infrastructure.UserRepository
import org.scalatest.FlatSpec

import scala.util.{Failure, Try}

class UserServiceSpec extends FlatSpec {

  def userServiceForTest(
      findUser: Option[User] = None,
      saveUser: Try[User] = Failure(new RuntimeException(""))
  ): UserService = {
    class UserRepositoryImplForTest extends UserRepository {
      override def find(name: UserName): Option[User] = findUser
      override def find(userId: UserId): Option[User] = findUser
      override def save(user: User): Try[User] = saveUser
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
