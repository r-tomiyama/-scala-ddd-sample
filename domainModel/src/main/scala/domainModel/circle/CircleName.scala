package domainModel.circle

case class CircleName private (value: String)

object CircleName {
  def apply(value: String): CircleName = {
    require(value.length >= 3 && value.length <= 20)
    new CircleName(value)
  }
}
