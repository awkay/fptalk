import org.joda.time.LocalDate

def weeksForMonth(month: Int, year: Int) = {
  val firstDay = new LocalDate().
    monthOfYear().setCopy(12).
    year().setCopy(year-1).
    dayOfWeek().setCopy(7)


  val dates =
    Stream.iterate(firstDay)(d => d.plusDays(1))

  def weekContainsThisMonth(weekDates: Iterable[LocalDate]) =
    weekDates.exists(dt => dt.getMonthOfYear == month)

  dates.
    grouped(7).
    dropWhile(!weekContainsThisMonth(_)).
    takeWhile(weekContainsThisMonth)
}

weeksForMonth(3, 2015).toList.map(_.force)

//for(wk <- weeks;
    //dt <- wk)
  //Console.println(dt)

