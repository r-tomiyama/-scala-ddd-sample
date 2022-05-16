package root

import presentation.Cli

object Main {
  def main(args: Array[String]): Unit = {
    val cli = new Cli()
    cli.run
  }
}
