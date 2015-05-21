import org.joda.time.LocalDate

// Curried function
def datesContainMonth: Int => Traversable[LocalDate] => Boolean =
  month => dates =>
    dates.exists(dt => dt.getMonthOfYear == month)

def weeksForMonth(month: Int, year: Int) = {
  val priorSunday = new LocalDate() // Java API
    .monthOfYear().setCopy(month)
    .year().setCopy(year)
    .dayOfMonth().setCopy(1)
    .dayOfWeek().setCopy(7)
    .plusDays(-7)

  // partial application...makes a function from a function
  val weekContainsThisMonth = datesContainMonth(month)

  // lazy infinite stream of dates starting from prior sunday
  val dates = Stream.iterate(priorSunday)(d => d.plusDays(1))

  dates.
    grouped(7).
    dropWhile(!weekContainsThisMonth(_)).
    takeWhile(weekContainsThisMonth)
}

val weeks = weeksForMonth(9, 2015)

for (wk <- weeks) {
  Console.println()
  for (dt <- wk)
    Console.print(dt.toString("MM/dd") + " ")
}

