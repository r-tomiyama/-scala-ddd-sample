package application

import com.softwaremill.macwire.wire
import domainModel.{User, UserId, UserName}
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
      _ <- if (userService.exist(user))
        Failure(new RuntimeException("すでに利用されている名前"))
      else Success(())
      _ <- userRepository.save(user)
    } yield user).map(dto.User.from)

  def find(id: String): Try[dto.User] =
    for {
      userId <- UserId.from(id)
      user <- userRepository.find(userId) match {
        case Some(u) => Success(dto.User.from(u))
        case None    => Failure(new RuntimeException("存在しないID"))
      }
    } yield user

  def update(id: String, name: String): Try[dto.User] =
    (for {
      userId <- UserId.from(id)
      user <- userRepository.find(userId) match {
        case Some(u) => Success(u)
        case None    => Failure(new RuntimeException("存在しないID"))
      }
      newUser <- user.changeName(name)
      _ <- if (userService.exist(newUser))
        Failure(new RuntimeException("すでに利用されている名前"))
      else Success(())
      _ <- userRepository.save(newUser)
    } yield newUser).map(dto.User.from)

  // TODO:  削除を作る
}
