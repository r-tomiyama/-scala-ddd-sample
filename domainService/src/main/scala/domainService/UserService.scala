package domainService

import domainModel.user.User
import infrastructure.UserRepository

class UserService(val userRepository: UserRepository) {
  def exist(user: User): Boolean =
    userRepository.find(user.name).nonEmpty

}
