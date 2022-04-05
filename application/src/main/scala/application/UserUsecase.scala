package application

import com.softwaremill.macwire.wire

import domainModel.User
import domainService.UserService
import infrastructure.UserRepository

object UserUsecase extends UserUsecaseModule

trait UserUsecaseModule {
  lazy val userRepository: UserRepository = wire[UserRepository]
  lazy val userService: UserService = wire[UserService]

  def create(name: String): String = {
    val user = User.from(name)
    user.fold(_ => "ユーザ作れません", user => {
      if (userService.exist(user)) {
        userRepository.save(user).fold(_ => "ユーザ作れません", _ => "ユーザ作ったよ")
      } else {
        "ユーザ作れません"
      }
    })
  }
  // TODO:  取得・更新・削除を作る
}
