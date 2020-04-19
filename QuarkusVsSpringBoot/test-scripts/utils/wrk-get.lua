-- wrk report to output CSV

local report = os.getenv('WRK_REPORT') or 'report.csv'
local output = assert(io.open(report, 'w+'))

local labels = {
	'latency_min', 'latency_max','latency_mean', 'latency_stdev',
	'latency_p50', 'latency_p90', 'latency_p95', 'latency_p99',
	'rps', 'duration', 'requests', 'bytes',
	'errors_connect',
	'errors_read',
	'errors_write',
	'errors_status',
	'errors_imeout'
}

done = function(summary, latency)
	local errors = summary.errors
	output:write(table.concat(labels, ','))
	output:write('\n')
	output:write(table.concat({
		latency.min/1000, latency.max/1000, latency.mean/1000, latency.stdev,
		latency:percentile(50)/1000, latency:percentile(90)/1000, latency:percentile(95)/1000, latency:percentile(99)/1000,
		summary.requests/summary.duration*1000*1000, summary.duration, summary.requests, summary.bytes,
		errors.connect, errors.read, errors.write, errors.status, errors.timeout,
	},','))
	output:write('\n')
end
