package domainModel.circle

import domainModel.user.{IUserRepository}

class CircleFullSpecification(val userRepository: IUserRepository) {
  def isSatisfiedBy(circle: Circle): Boolean = {
    val users = userRepository.find(circle.memberIds)
    val premiumUserNumber = 0 // TODO: usersからプレミアムユーザ数を数える
    val circleUpperLimit = if (premiumUserNumber < 10) 30 else 50
    circle.memberIds.size >= circleUpperLimit
  }
}
