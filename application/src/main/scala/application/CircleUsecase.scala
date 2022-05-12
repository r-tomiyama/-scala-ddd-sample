package application

import application.UserUsecase.userRepository
import domainModel.circle.{Circle, CircleId, ICircleRepository}
import domainModel.user.UserId
import domainService.CircleService

import scala.util.{Failure, Success, Try}

object CircleUsecase extends CircleUsecaseModule

trait CircleUsecaseModule {
  lazy val circleRepository: ICircleRepository = ???
  lazy val circleService: CircleService = ???

  def create(userId: String, circleName: String): Try[Unit] =
    for {
      userId <- UserId.from(userId)
      user <- userRepository.find(userId) match {
        case Some(u) => Success(u)
        case None    => Failure(new RuntimeException("存在しないユーザーID"))
      }
      circle <- Circle.from(circleName, user.id)
      _ <- if (circleService.exist(circle))
        Failure(new RuntimeException("すでに利用されている名前"))
      else
        Success(())
      result <- circleRepository.save(circle)
    } yield result

  def join(userId: String, circleId: String): Try[Unit] =
    for {
      userId <- UserId.from(userId)
      user <- userRepository.find(userId) match {
        case Some(u) => Success(u)
        case None    => Failure(new RuntimeException("存在しないユーザーID"))
      }
      circle <- circleRepository.find(CircleId(circleId)) match {
        case Some(c) => Success(c)
        case None    => Failure(new RuntimeException("存在しないユーザーID"))
      }
      updatedCircle <- circle.addUser(user.id)
      result <- circleRepository.save(updatedCircle)
    } yield result
}
