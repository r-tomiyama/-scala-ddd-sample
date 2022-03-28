package application

import domainModel.User
import domainService.UserService
import infrastructure.UserRepository

class UserUsecase(
    val userService: UserService,
    val userRepository: UserRepository
) {
  def create(name: String): Unit = {
    val user = User(name)

    if (userService.exist(user)) {
      userRepository.save(user)
      println("ユーザ作ったよ")
    } else {
      println("ユーザ作れません")
    }
  }
  // TODO:  取得・更新・削除を作る
}

object UserUsecase {
  def apply(): UserUsecase = {
    val repository = new UserRepository
    new UserUsecase(new UserService(repository), repository)
  }
}
