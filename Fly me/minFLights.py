'''
We have to return the minimum number of flights.
Some assumptions for constraints:
      1. Fuel available in a plane can not be negative
      2. There must be atleast one airport
      3. Number of airports are in integer range of o to 2^31-1 and similar for amount of fuel

First approach: Recursively solving using memoization.
This approach has a time complexity of O(n) and space complexity of O(n)

Second Approach: Dynamic Programming.
This approach has a time complexity of O(n^2) and space complexity of O(n)
'''


import math

def min_flights_dp(fuel):
    memo = [0] * len(fuel) #initialising memo array
    
    for i in range(1, len(memo)):
        min_flights = math.inf
        for j in range(i):
            if fuel[j] == -1:
                continue
            if i - j <= fuel[j] and memo[j] != -1 and memo[j] < min_flights:
                min_flights = memo[j]
        if min_flights == math.inf:
            memo[i] = -1
        else:
            memo[i] = min_flights + 1
    return memo[len(memo) - 1]


def min_number_of_planes(fuel, current_airport, memo):

    #specail case
    if len(fuel) == 1:
        return 0
    
    #base case, if we reach destination
    if current_airport == len(fuel) - 1:
        memo[current_airport] = 1
        return memo[current_airport]
    
    #if we go beyond destination or if fuel gets empty. less likey to happen but written it for safety.
    if current_airport >= len(fuel) or fuel[current_airport] <= 0:
        memo[current_airport] = -1
        return memo[current_airport]
    
    #if the result is already present at this same input
    if memo[current_airport] != -2:
        return memo[current_airport]
    
    # initialised temp answer to the problem
    min_planes = math.inf

    j = 1
    for i in range(current_airport + 1, len(fuel)):
        if fuel[current_airport] - j < 0:
            break
        result = min_number_of_planes(fuel, i, memo) #recursively calling every possible destination 

        # and comparing their returned results.
        if result != -1 and result < min_planes:
            min_planes = result

        j += 1 #/next destination airport number

    #if we did not get any result from the above exploration
    if min_planes == math.inf:
        memo[current_airport] = -1

    #since we do not want to count 1st airport in the result
    elif current_airport == 0:
        memo[current_airport] = min_planes
    else:
        memo[current_airport] = min_planes + 1

    #final result
    return memo[current_airport]


def main():
    # Test cases
    test_cases = [
        [2, 1, 1],
        [1],
        [0, 0, 0, 0, 1],
        [0, 1, 1],
        [1, 6, 3, 4, 5, 0, 0, 0, 6],
        [2, 1, 2, 3, 1],
        [3, 6, 0, 1, 4, 0, 0, 0, 0]
    ]

    # First Approach by Recursion
    print("*********First Approach by Recursion********** \n")
    for i, fuel in enumerate(test_cases):
        print("solving test case number", i + 1)
        memo = [-2] * len(fuel)
        print("Minimum number of flight required:", min_number_of_planes(fuel, 0, memo), "\n")

    # Second Approach by Dynamic Programming
    print("*********Second Approach by DP********** \n")
    for i, fuel in enumerate(test_cases):
        print("solving test case number", i + 1)
        print("Minimum number of flight required:", min_flights_dp(fuel), "\n")


if __name__ == "__main__":
    main()
