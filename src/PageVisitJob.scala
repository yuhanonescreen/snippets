import com.twitter.scalding._

class PageVisitJob(args: Args) extends Job(args) {
  /*
	val pageVisitCounts = Tsv("/home/yuhan/output/httpLog-201329.tsv")
			.read
			.mapTo((0,1,2,3,4,5,6,7,8,9)->('ip, 'rfc_id, 'remote_user, 'time, 'http_method, 'requested_resource, 'protocol, 'http_status, 'size, 'referer))
			{ fields : (String, String, String, String, String, String, String, String, String, String) => fields }
			.groupBy('requested_resource) { group => group.size }
			.write(Tsv("/tmp/page_visits.tsv"))
			* 
			*/
	val pageVisitIps = Tsv("/home/yuhan/output/httpLog-201329.tsv")
			.read
			.mapTo((0,1,2,3,4,5,6,7,8,9)->('ip, 'rfc_id, 'remote_user, 'time, 'http_method, 'requested_resource, 'protocol, 'http_status, 'size, 'referer))
			{ fields : (String, String, String, String, String, String, String, String, String, String) => fields }
			.groupBy('requested_resource, 'ip) { group => group.size}
			.write(Tsv("/tmp/page_visit_ips.tsv"))
	
	val badPages = Tsv("/home/yuhan/bad_visit.tsv")
			.read
			.mapTo((0,1)->('request, 'count))
			{ fields : (String, Long) => fields }
			.filter('count) { count : (Long) => count < 10}
			.joinWithSmaller('request-> 'requested_resource, pageVisitIps)
			.project('request, 'ip)
			.write(Tsv("/tmp/bad_ips_request"))
			.groupBy( 'ip ) { group => group.size }
			.project('ip)
			.write(Tsv("/tmp/bad_ips"))
}
