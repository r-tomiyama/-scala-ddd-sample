package application

import domainModel.User
import domainService.UserService
import infrastructure.UserRepository

class UserUsecase(
    val userService: UserService,
    val userRepository: UserRepository
) {
  def create(name: String): String = {
    val user = User.from(name)
    user.fold(_ => "ユーザ作れません", user => {
      if (userService.exist(user)) {
        userRepository.save(user)
        "ユーザ作ったよ"
      } else {
        "ユーザ作れません"
      }
    })
  }
  // TODO:  取得・更新・削除を作る
}

object UserUsecase {
  def apply(): UserUsecase = {
    val repository = new UserRepository
    new UserUsecase(new UserService(repository), repository)
  }
}
