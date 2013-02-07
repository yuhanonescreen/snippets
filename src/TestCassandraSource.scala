import com.twitter.scalding.Args


object TestCassandraSource {

  def main(args: Array[String]): Unit = {  
     println("job 0 started")
     
     val map : Map[String, List[String]] = Map("input" -> List[String]("/home/local/OFFICE/yzhang/scalding/tutorial/data/hello.txt"),
    		 									"output" -> List[String]("/tmp/output4.txt") ) 
     
     val args2 = new Args( map )  
     val t = new CassandraJob0( args2 )
     t.run( com.twitter.scalding.Mode.mode )
     
     println("cassandra job completed")
     
  }

}