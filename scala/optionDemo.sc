def someOp: Option[Int] = Some(11)

def nextOp(n: Int): Option[Int] =
  if (n < 10) Some(n + 1)
  else None

def otherOp(n: Int): Option[Int] =
  Some(n * 2)

val result =
  someOp
  .flatMap(n => nextOp(n))
  .flatMap(n => otherOp(n))

// syntactic sugar:
for (a <- someOp;
     b <- nextOp(a);
     c <- otherOp(b)) yield c



