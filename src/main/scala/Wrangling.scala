import edu.umass.cs.CSV

object Wrangling{

val births = CSV.fromFile("ssa-births.csv")//births
val CDC = CSV.fromFile("cdc-life-expectancy.csv")//life expectancy
val g = births.filter((record: List[String]) => record(2) == "F").map(x=> x(1)).flatten

def yearIs(data: List[List[String]], n: Int): List[List[String]] = 
{
	data.filter((record: List[String]) => record(0).toInt == n)
}

def yearGT(data: List[List[String]], bound: Int): List[List[String ]] =
{
	data.filter((record: List[String]) => record(0).toInt > bound)
}

def yearLT(data: List[List[String]], bound: Int): List[List[String ]] =
{
	data.filter((record: List[String]) => record(0).toInt < bound)
}

def onlyName(data: List[List[String]], name: String ): List[List[String ]] =
{
	data.filter((record: List[String]) => record(1) == name)
}

def mostPopular(data: List[List[String ]]): (String , Int) =
{
	val ls = data.map(x => x(1) -> x(3).toInt)
	//println("1: " + ls)
	val groupedMap = ls.groupBy(x => x._1)
	//println("2:" + groupedMap)
	val mapLstInts = groupedMap.map(x => x._1 -> x._2.map(y => y._2) )//convert to (string,int)
	//println("3: " + mapLstInts)
	val totals = mapLstInts.map(x => x._1 -> x._2.fold(0)( (a,i) => a + i.toInt ) )
	//println("4: " + totals)
	val t = totals.maxBy(i => i._2)
	//println(t)
	t
}

def count(data: List[List[String ]]): Int = 
{
	val nums = data.map(x => x(3).toInt)
	val total = nums.fold(0)( (a,i) => a + i )
	total
}

def countGirlsAndBoys(data: List[List[String ]]): (Int , Int) =
{
	val numG = data.filter((record: List[String]) => record(2) == "F")
	val numB = data.filter((record: List[String]) => record(2) == "M")
	(count(numG), count(numB))
}
//Test list for genderNeutralNames
val duplicateNameTestList = ("one","M") :: ("two","F") :: ("two","F") :: Nil

//Helper function for genderNeutralNames
def duplicateName(s: String, lst: Set[(String,String)]): Boolean =
{
	lst.filter(x => x._1 == s).size > 1
}

def genderNeutralNames(data: List[List[String ]]): Set[String] =
{
	val s = data.map(x => (x(1)-> x(2))).toSet
	//println("1: " + s)
	val gnn = s.filter(x => duplicateName(x._1, s))
	//println("2: " + gnn)
	gnn.map(x=> x._1)
}

//helper function for expectedAlive
def getExpectancy(year: Int, gender: String): Int = 
{
	//cdc: year,m,f
	val ls = CDC.filter((record: List[String]) => record(0).toInt == year)(0)
	//println("2: " + ls)
	 gender match {
	  	case "F" => ls(2).toInt
	  	case "M" => ls(1).toInt
	  }
	 
}

def expectedAlive(gender: String , birthYear: Int , currentYear: Int): Boolean =
{
	gender match {
		case "F" =>
		{
			val age = currentYear - birthYear
			0 <= (getExpectancy(currentYear,"F") - age)//returns true if alive  
		}
		case "M" =>
		{
			val age = currentYear - birthYear
			0 <= (getExpectancy(currentYear,"M") - age)//returns true if alive  
		}
		//case _ => //throw new Exception   
	}


}

//Assume the amount of people alive is = 0 before 1930.
def estimatePopulation(data: List[List[String]], year: Int): Int =
{
	//println(": " + )
	val newdData = yearLT(yearGT(data, 1929), year)
	//println("1: " + newdData)
	val g = newdData.groupBy(x => x(0))
	//println("2: " + g)
	val ytogn = g.map(x => x._2.map(y=> ( y(0), y(2),  y(3) ) ) )
	//println("3: " + ytogn)
	val fltt = ytogn.flatten
	val par = fltt.partition(x => x._2 == "F")//x._1 == F
	// println("4: " + par)
	val fem = par._1
	val mal = par._2
	
	val femAlive = fem.filter(((x) => expectedAlive(x._2, x._1.toInt, year) ) )//yb,g,num
	val malAlive = mal.filter(((x) => expectedAlive(x._2, x._1.toInt, year) ) )
	//println("filtered guys: " + malAlive)
	//println("filtered girls: " + femAlive)

	val numF = femAlive.map(x => x._3.toInt)
	val numM = malAlive.map(x => x._3.toInt)
	//println("numM: " + numM)
	//println("numF: " + numF)
	val totalF = numF.fold(0)((a,i) => a + i)
	val totalM = numM.fold(0)((a,i) => a + i)
	val total = totalF + totalM
	total
}

}//End wrangling