t = int(input())
for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    
    for i in range(n):
        if a == sorted(a):
            print('Yes')
            break
        a.append(a.pop(0))
    else:
        print('No')