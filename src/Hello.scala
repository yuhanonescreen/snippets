import com.twitter.scalding.Args


object Hello {

  def main(args: Array[String]): Unit = {  
     println("job 0 started")
     
     val map : Map[String, List[String]] = Map("input" -> List[String]("/home/yuhan/hello.txt"),"output" -> List[String]("/tmp/output4.txt") ) 
     val args2 = new Args( map )  
     val t = new Tutorial0( args2 )
     t.run( com.twitter.scalding.Mode.mode )
     
     println("job 0 completed")
     
     println("job 4 started")
     val input = args2("input")
     
     
     val t2 = new Tutorial4( args2 )
     t2.run( com.twitter.scalding.Mode.mode )
     
     println("job 4 completed")
  }

}