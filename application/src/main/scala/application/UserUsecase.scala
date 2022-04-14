package application

import com.softwaremill.macwire.wire
import domainModel.User
import domainService.UserService
import infrastructure.{UserRepository, UserRepositoryImpl}

import scala.util.{Failure, Success, Try}

object UserUsecase extends UserUsecaseModule

trait UserUsecaseModule {
  lazy val userRepository: UserRepository = wire[UserRepositoryImpl]
  lazy val userService: UserService = wire[UserService]

  def create(name: String): Try[dto.User] =
    (for {
      user <- User.from(name)
      _ <- if (userService.exist(user)) Failure(new RuntimeException(""))
      else Success(())
      _ <- userRepository.save(user)
    } yield user).map(dto.User.from)
  // TODO:  取得・更新・削除を作る
}
