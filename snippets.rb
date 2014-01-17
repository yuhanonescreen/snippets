while true do
  puts "hi"
  if 1 > 0
    break
   end
end


['a','b','c'].each_with_index do |v,i|
  puts v + ' - ' + i.to_s
end

3.times do
  puts 'hi'
end


def ab(a, b)
  return ab(a-4, b*5) if a>0
  b+1 + a
end
