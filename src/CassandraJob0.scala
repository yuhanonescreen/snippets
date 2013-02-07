import com.twitter.scalding._
import com.sonar.scalding.cassandra.CassandraSource
import org.pingles.cascading.cassandra.WideRowScheme

class CassandraJob0(args : Args) extends Job(args) {


  //val input = TextLine("/home/local/OFFICE/yzhang/scalding/tutorial/data/hello.txt")
  val output = TextLine("/tmp/output_00.txt")
  
  val input = new CassandraSource( "localhost", 9160, "test", "test", new WideRowScheme() )

  input.read.write(output)

}