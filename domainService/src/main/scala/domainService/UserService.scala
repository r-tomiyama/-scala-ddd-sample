package domainService

import domainModel.user.{IUserRepository, User}

class UserService(val userRepository: IUserRepository) {
  def exist(user: User): Boolean =
    userRepository.find(user.name).nonEmpty

}
