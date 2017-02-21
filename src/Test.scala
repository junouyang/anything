import java.util.concurrent.TimeUnit

/**
  * Created by jun.ouyang on 5/5/16.
  */

object Test {
  def main(args : Array[String]) = {
    performance( () => println(1l.to(1000* 1000 * 1000l).sum ))
  }

  def performance( f : () => Unit ) = {
    val start = System.nanoTime()
    f()
    println("Time taken : " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start) )
  }
}