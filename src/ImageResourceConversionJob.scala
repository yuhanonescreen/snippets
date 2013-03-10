import com.twitter.scalding._
import scala.io._
import scala.util.parsing.json._

class ImageResourceConversionJob(args: Args) extends Job(args) {
  
	val image_resource_files = Tsv("/home/yuhan/sql/imagefilelist.txt")
			.read
			.mapTo((0,1)->('name, 'file))
			{ fields : (String, String) => fields }
	
	val image_links = image_resource_files
	.flatMap('file -> 'url) { file: String => {
		 val text = Source.fromFile( file ).mkString
		 JSON.parseFull( text ) match {
		   case None => None
		   case Some(list: List[String]) => list
		 }
		}
	  }
	.discard('file)
	.write(Tsv("/tmp/image_links.tsv"))
			
	image_resource_files
	.discard('file)
	.unique('name)
	.write(Tsv("/tmp/image_resource_names.tsv") )
	
	// load the resources with ids (based on the new rows in from mysql
	val image_resource = Tsv("/home/yuhan/sql/image_resources.tsv")
			.read
			.mapTo((0,1)->('resource_id, 'name))
			{ fields: (String, String) => fields }
			//.write( Tsv("/tmp/image_resources.tsv") )
			
		.joinWithSmaller('name -> 'name, image_links)
		.discard('name)
		.write(Tsv("/tmp/image_links_joined.tsv"))
}