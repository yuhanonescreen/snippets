file_name = ENV['inputfile'] || 'sum_sample.in'
puts file_name
lines = File.read( file_name ).split("\n")
num_lines = lines.shift.to_i

results = lines.collect do |l|
  pair = l.split(' ')
  pair[0].to_i + pair[1].to_i
end

out_filename = 'outfile'
outfile = File.open(out_filename, 'w')
results.each_with_index do |r,i|
  outfile.puts("Case ##{i+1}")
  outfile.puts(r)
end
outfile.close

puts( File.read(out_filename))

