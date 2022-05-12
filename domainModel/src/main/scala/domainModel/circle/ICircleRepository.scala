package domainModel.circle

import scala.util.Try

trait ICircleRepository {

  def save(circle: Circle): Try[Unit]

  def find(id: CircleId): Option[Circle]

  def find(name: CircleName): Option[Circle]

}
