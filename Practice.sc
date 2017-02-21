def sum(f: Int => Int)(x: Int, y: Int): Int = {
  def loop(accum: Int, x: Int): Int = {
    if (x > y) accum else loop(accum + f(x), x + 1)
  }
  loop(0, x)
}

sum(x => x)(0, 10)
sum(x => x * x)(0, 10)
sum(x => x * x * x)(0, 10)

  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
x.add( y.neg ).add( z.neg)
x.sub(y).sub(z)


class Rational(x: Int, y: Int) {
  def numer = x

  def denom = y

  def add(that: Rational) =
    new Rational(numer * that.denom + that.numer * denom,
      denom * that.denom)

  def neg =
    new Rational(-numer, denom)

  def sub(that : Rational ) =
    add(that.neg)

  override def toString =
    numer + "/" + denom
}

trait Expr
case class Number(n : Int ) extends Expr
case class Sum(x : Expr, y : Expr) extends Expr
case class Var(name : String) extends Expr
case class Pro(x : Expr, y : Expr) extends Expr

def show( e : Expr ) : String = e match {
  case Number(n) => n.toString
  case Sum(x, y) => show(x) + " + " + show(y)
  case Var(name) => name
  case Pro(x, y) => showWithParentheses(x) + " * " + showWithParentheses(y)
}

def showWithParentheses(e : Expr ) : String = e match {
  case Sum(_, _) => "(" + show(e) + ")"
  case e => show(e)
}

show(Pro(Number(1), Number(2)))
show(Pro(Number(1), Sum(Number(2), Var("x"))))