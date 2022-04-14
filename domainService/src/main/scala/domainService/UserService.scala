package domainService

import domainModel.User
import infrastructure.UserRepository

class UserService(val userRepository: UserRepository) {
  def exist(user: User): Boolean =
    userRepository.find(user.name.value).nonEmpty

}
