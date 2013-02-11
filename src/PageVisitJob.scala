import com.twitter.scalding._

class PageVisitJob(args: Args) extends Job(args) {
	val pageVisits = Tsv("/home/yuhan/logs/httpLog-201329.tsv")
			.read
			.mapTo((0,1,2,3,4,5,6,7,8,9)->('ip, 'rfc_id, 'remote_user, 'time, 'http_method, 'requested_resource, 'protocol, 'http_status, 'size, 'referer))
			{ fields : (String, String, String, String, String, String, String, String, String, String) => fields }
			.groupBy('requested_resource) { group => group.size }
			.write(Tsv("/tmp/page_visits.tsv"))
	
}
