import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.io.IOException
import java.awt.Color
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

import edu.umass.cs.CSV

object popDense{
import PathImplicits._

val births = CSV.fromFile("ssa-births.csv")//births

//file for tif file
val fbig = new File("C:\\Users\\Liam\\Desktop\\gpw-v4-population-density-adjusted-to-2015-unwpp-country-totals_2015.tif")
val f = new File("C:\\Users\\Liam\\Desktop\\densemap.tif")
val ftest = new File("C:\\Users\\Liam\\Desktop\\test.tif")
val img = ImageIO.read(ftest)

def t(w:Int, h:Int) = 
{

      val pixels = Array[byte]((DataBufferByte) image.getRaster().getDataBuffer()).getData();
      val width = image.getWidth();
      val height = image.getHeight();
      val boolean hasAlphaChannel = image.getAlphaRaster() != null;
}

def getDense(w: Double, h: Double):Int =
{
	//val pix = (bufferedImage.getRaster().getDataBuffer().asInstanceOf[DataBufferByte]).getData
	val c = new Color(img.getRGB(w.toInt, h.toInt))
	//byte[] pixels = ((DataBufferByte) BufferedImage.getRaster().getDataBuffer()).getData()
	println(c)
1
}

// def recColors(w: Int, h: Int,cols: List[(Color)], lst: List[(Color,(Int,Int))], img: BufferedImage): List[(Color,(Int,Int))]=
// {
// 	h < 0 match {
// 		case true => lst
// 		case false => 
// 		if(w < 0)
// 		{
// 			recColors(img.getWidth-1, h - 1,cols ,lst, img)
// 		}
// 		else
// 		{
// 			val c = new Color(img.getRGB(w, h));
// 		    if(!cols.contains(c))
// 		    {
// 		    	//println(w + " " + h + "COLOR: " + c)
// 		    	val pix = (c,(w,h))
// 		    	//println(c)
// 		    	recColors(w-1,h,c::cols,pix::lst,img) 
// 		    }
// 		    else
// 		    {
// 		    	recColors(w-1,h,cols,lst,img)
// 		    }
// 		}
// 	}
// }

// def getColors(f: File): List[(Color,(Int,Int))] =
// {
// 	val slice = ImageIO.read(f)
// 	val w = slice.getWidth
//  	val h = slice.getHeight
//  	recColors(w-1,h-1,Nil,Nil,slice)
// }





}
// def getFiles(dir: String):List[File] = {
//   val d = new File(dir)
//   if (d.exists && d.isDirectory) {
//     d.listFiles.filter(_.isFile).toList
//   } else {
//     List[File]()
//   }
// }

// def getSubDir(dir: String): Array[String] = {
// 	val names = (new File(dir)).listFiles.filter(_.isDirectory).map(_.getName)
// 	names.map(x=> dir + "\\" + x)
// }

// def getPngs(path: String): Array[File] = //hand the path of blocks
// {
// 	val subD = getSubDir(path)
// 	val files = subD.map(x=>getFiles(x)).flatten
// 	val pngs = files.filter(f => """.*\.png$""".r.findFirstIn(f.getName).isDefined)
// 	pngs
// }

// def recColors(w: Int, h: Int,cols: List[(Color)], lst: List[(Color,(Int,Int))], img: BufferedImage): List[(Color,(Int,Int))]=
// {
// 	h < 0 match {
// 		case true => lst
// 		case false => 
// 		if(w < 0)
// 		{
// 			recColors(img.getWidth-1, h - 1,cols ,lst, img)
// 		}
// 		else
// 		{
// 			val c = new Color(img.getRGB(w, h));
// 		    if(!cols.contains(c))
// 		    {
// 		    	//println(w + " " + h + "COLOR: "+c)
// 		    	val pix = (c,(w,h))
// 		    	//println(c)
// 		    	recColors(w-1,h,c::cols,pix::lst,img) 
// 		    }
// 		    else
// 		    {
// 		    	recColors(w-1,h,cols,lst,img)
// 		    }
// 		}
// 	}
// }

// def getColors(f: File): List[(Color,(Int,Int))] =
// {
// 	val slice = ImageIO.read(f)
// 	val w = slice.getWidth
//  val h = slice.getHeight
//  recColors(w-1,h-1,Nil,Nil,slice)
// }

// def writePos(file: Path, colors: List[(Color,(Int,Int))])//writes a list of colors to a file
// {
// //val colorLoc = colors.map(x => (x._1.getRed.toString + " " + x._1.getGreen.toString + " " + x._1.getBlue.toString + " " + x._2._1.toString + " " + x._2._2.toString) )
// val colorLoc = colors.map(x => ( toLabel(x._1.getRed, x._1.getGreen, x._1.getBlue).toString + " " + x._2._1.toString + " " + x._2._2.toString) )
// val toWrite = colorLoc.map(x=>x.toString + "\n")
// //colors.foreach(c=>file.append(toWrite))
// file.write(toWrite.mkString)
// }

// def makeLabelDirs(pngs: Array[File]): Array[File] =
// {
//  val strs = pngs.map(x=>x.toString)
//  val newStrs = strs.map(x=>x.replaceAll("out_segmentation","labels")).map(y=>y.replaceAll("out_segm","label")).map(p=>p.replaceAll(".png",".txt"))
//  val files = newStrs.map(x=>new File(x))
//  files.map(x=>x.mkdirs)
//  files
// }

// def mkLabelDir(png: File): File =
// {
//  val str = png.toString
//  val newStr = str.replaceAll("out_segmentation","labels").replaceAll("out_segm","label").replaceAll(".png",".txt")
//  println(newStr)
//  val f = new File(newStr)
//  (new File(f.getParent)).mkdir
//  //f.mkdir
//  f
// }

// def fromLabel(label: Int): (Int,Int,Int) =//not working
// {
// println("THIS METHOD IS NOT WORKING FOR BGR YET ONLY RGB")
// val r = ((Math.pow(256,3)+label) / 65536).toInt
// val g = (((Math.pow(256,3)+label) / 256) % 256).toInt
// val b = ((Math.pow(256,3)+label) % 256).toInt
// (r,g,b)
// }

// def toLabel(r: Int, g: Int, b: Int): Int = //0 4 241 == 1265
// {//labels are encoded as BGR not RGB 
// 	val dB = b;
// 	val dBG = b + (g << 8);
// 	val dBGR = dBG + (r << 16);
// 	dBGR
// }

// def makeLabels(path: String)
// {
// 	val pngs = getPngs(path)
// 	println("pngs found.")
// 	val sz = pngs.size
// 	var i=0
// 	val pngColors = pngs.map(x => {
// 		println(sz -i)
// 		i+=1
// 		(mkLabelDir(x),getColors(x))
// 	})

// 	pngColors.foreach(x=>mkLabelDir(x._1))
// 	println("Directories created.")
// 	//val txtPaths = makeLabelDirs(pngs)
// 	println("please wait while colors are written.")
// 	pngColors.foreach(x=>writePos(Paths.get(x._1.getPath),x._2))
// 	println("COMPLETE!")
// }

// }