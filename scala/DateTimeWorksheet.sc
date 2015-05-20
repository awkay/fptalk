import org.joda.time.LocalDate

def weeksForMonth(month: Int, year: Int) = {
  val priorSunday = new LocalDate()
    .monthOfYear().setCopy(month)
    .year().setCopy(year)
    .dayOfMonth().setCopy(1)
    .dayOfWeek().setCopy(7)
    .plusDays(-7)

  val dates =
    Stream.iterate(priorSunday)(d => d.plusDays(1))

  def weekContainsThisMonth(weekDates: Iterable[LocalDate]) =
    weekDates.exists(dt => dt.getMonthOfYear == month)

  dates.
    grouped(7).
    dropWhile(!weekContainsThisMonth(_)).
    takeWhile(weekContainsThisMonth)
}

weeksForMonth(9, 2015).toList.map(_.force)

//for(wk <- weeks;
//dt <- wk)
//Console.println(dt)

