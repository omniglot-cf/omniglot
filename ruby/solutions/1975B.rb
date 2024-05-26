t = gets.to_i

while t > 0 do
  n = gets.to_i
  arr = gets.split.map(&:to_i)
  
  small = arr[0]

  for i in 0..(n-1) do
     if (arr[i] < small)
        small = arr[i]
     end
  end
  
  arr_2 = []
  for i in 0..(n-1) do
     if (arr[i] % small > 0)
        arr_2.push(arr[i])
     end
  end
  
  if (arr_2.length == 0)
    puts "Yes"
  else
  m = arr_2.length
  small_2 = arr_2[0]

  for i in 0..(m-1) do
     if (arr_2[i] < small_2)
        small_2 = arr_2[i]
     end
  end
  
  arr_3 = []
  for i in 0..(m-1) do
     if (arr_2[i] % small_2 > 0)
        arr_3.push(arr_2[i])
     end
  end

  if (arr_3.length == 0)
    puts "Yes"
  else
    puts "No"
end
  end

  t -= 1
end
