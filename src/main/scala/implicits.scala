import java.nio.file.Paths
import java.nio.file.Path
import java.nio.file.Files
import java.io.BufferedOutputStream
import java.nio.file.StandardOpenOption

import  java.time.LocalDate

object PathImplicits{
val tFile = "C:"/"cygwin"/"home"/"Liam"/"brooke"/"test.txt"

implicit class RichFileIO(p: Path)
{
	
def write(str: String)
{
val b = str.getBytes//bytes
Files.write(p,b)
}

def read(): String =
{
val b = Files.readAllBytes(p)//bytes
val cArray = b.map(x => x.toChar)
val s = cArray.mkString("")
//println(s)
s
}

def append(str: String)
{
	val fileStr = p.read()
	val newStr = fileStr + str
	p.write(newStr)
}

}//;

implicit  class  RichStr(p: String)
{
def /(other: String): Path = {
val k  = Paths.get(p,other)
k
}

def /(other: Path): Path = {
val k  = Paths.get(p).resolve(other)
k
}
}//;

implicit  class  RichPath(p: Path)
{
def /(other: Path): java.nio.file.Path = {
val k  = p.resolve(other)
k
}

def /(other: String): Path = {
val k  = p.resolve(other)
k
}
}//;

}

object DateImplicits{

class Days(val num: Int)
class Months(val num: Int)
class Years(val num: Int)

implicit class richDates(dt: LocalDate)
{
	def +(days: Days): LocalDate =
	{
		dt.plusDays(days.num)
	}
	def +(months: Months): LocalDate =
	{
		dt.plusMonths(months.num)
	}
	def +(years: Years): LocalDate =
	{
		dt.plusYears(years.num)
	}
}

implicit class richToDates(d: Int)
{
def days() = {
	new Days(d)
}
def months() = {
	new Months(d)	
}
def years() = {
	new Years(d)
}

def jan(arg: Int) = {
LocalDate.of(arg, 1, d)	
}

def feb(arg: Int) = {
LocalDate.of(arg, 2, d)	
}

def mar(arg: Int) = {
LocalDate.of(arg, 3, d)	
}

def apr(arg: Int) = {
LocalDate.of(arg, 4, d)
}

def may(arg: Int) = {
LocalDate.of(arg, 5, d)
}

def jun(arg: Int) = {
LocalDate.of(arg, 6, d)
}

def jul(arg: Int) = {
LocalDate.of(arg, 7, d)
}

def aug(arg: Int) = {
LocalDate.of(arg, 8, d)
}

def sep(arg: Int) = {
LocalDate.of(arg, 9, d)
}

def oct(arg: Int) = {
LocalDate.of(arg,10 , d)
}

def nov(arg: Int) = {
LocalDate.of(arg, 11, d)
}
def dec(arg: Int) = {
LocalDate.of(arg, 12, d)
}
//========================
val curYear= java.time.LocalDate.now.getYear()

def jan() = {
LocalDate.of(curYear, 1, d)	
}

def feb() = {
LocalDate.of(curYear, 2, d)	
}

def mar() = {
LocalDate.of(curYear, 3, d)	
}

def apr() = {
LocalDate.of(curYear, 4, d)
}

def may() = {
LocalDate.of(curYear, 5, d)
}

def jun() = {
LocalDate.of(curYear, 6, d)
}

def jul() = {
LocalDate.of(curYear, 7, d)
}

def aug() = {
LocalDate.of(curYear, 8, d)
}

def sep() = {
LocalDate.of(curYear, 9, d)
}

def oct() = {
LocalDate.of(curYear,10 , d)
}

def nov() = {
LocalDate.of(curYear, 11, d)
}
def dec() = {
LocalDate.of(curYear, 12, d)
}
//========================



}//;

}