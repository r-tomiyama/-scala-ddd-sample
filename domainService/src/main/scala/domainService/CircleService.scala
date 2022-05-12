package domainService

import domainModel.circle.{Circle, ICircleRepository}

class CircleService(val circleRepository: ICircleRepository) {
  def exist(circle: Circle): Boolean =
    circleRepository.find(circle.name).nonEmpty
}
