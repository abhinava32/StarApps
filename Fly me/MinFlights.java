//save this file with the name MinFlights.java and run it with JDK
//We have to return the minimum number of flights.
//Some assumptions for constraints:
//      1. Fuel available in a plane can not be negative
//      2. There must be atleast one airport
//      3. Number of airports are in integer range of o to 2^31-1 and similar for amount of fuel

//First approach: Recursively solving using memoization.
//This approach has a time complexity of O(n) and space complexity of O(n)

//Second Approach: Dynamic Programming.
////This approach has a time complexity of O(n^2) and space complexity of O(n)

import java.util.Arrays;
import java.util.ArrayList;

class MinFlights {
    
    public static int minFlightsDP(int[] fuel){
        int[] memo = new int[fuel.length];
        for(int i = 1; i<memo.length;i++){
            int minFlights = Integer.MAX_VALUE;
            for(int j = 0; j<i;j++){
                if(fuel[j] == -1){
                    continue;
                }
                if(i-j <= fuel[j] && memo[j] != -1 && memo[j]<minFlights){
                    minFlights = memo[j];
                }
            }
            if(minFlights == Integer.MAX_VALUE){
                memo[i] = -1;
                
            }
            else{
                memo[i] = minFlights + 1;
            }
            
        }
        return memo[memo.length-1];
        
    }
    
    //recursive function to calculate minimum number of flights
    public static int minNumberOfPlanes(int[] fuel, int currentAirport, int[] memo){
        //special case
        if(fuel.length == 1){
            return 0;
        }
        
        //base case, if we reach destination
        if(currentAirport == fuel.length - 1){
            return memo[currentAirport] = 1;
        }
        
        //if we go beyond destination or if fuel gets empty. less likey to happen but written it for safety.
        if(currentAirport >= fuel.length || fuel[currentAirport] <= 0){
            return memo[currentAirport] = -1;
        }
        
        //if the result is already present at this same input
        if(memo[currentAirport] != -2){
            return memo[currentAirport];
        }
        
        //initialised temp answer to the problem
        int min = Integer.MAX_VALUE;
        
        int j = 1;
        for(int i = currentAirport + 1; i < fuel.length && fuel[currentAirport] - j >= 0; i++){
            int result = minNumberOfPlanes(fuel, i, memo); //recursively calling every possible destination 
            
            //and comparing their returned results.
            if(result != -1 && result < min){
                min = result;
            }
            j++; //next destination airport number
        }
        
        //if we did not get any result from the above exploration
        if(min == Integer.MAX_VALUE){
            return memo[currentAirport] = -1;
        }
        
        //since we do not want to count 1st airport in the result
        if(currentAirport == 0){
            return memo[currentAirport] = min; 
        }
        
        //final result
        return memo[currentAirport] = min + 1;
    }
    
    
    public static void main(String[] args) {
        
        //test cases:
        ArrayList<int[]> testCases = new ArrayList<>();
        testCases.add(new int[]{2,1,1});
        testCases.add(new int[]{1});
        testCases.add(new int[]{0,0,0,0,1});
        testCases.add(new int[]{0,1,1});
        testCases.add(new int[]{1, 6, 3, 4, 5, 0, 0, 0, 6});
        testCases.add(new int[]{2,1,2,3,1});
        testCases.add(new int[]{3,6,0,1,4,0,0,0,0});
        testCases.add(new int[]{3,6,0,1,4,0,0,0});
        // add test cases by copying above or editing above cases
        
        // First Approach by recursion
        System.out.println("*********First Approach by Recursion********** \n");
        for(int i = 0; i<testCases.size(); i++){
            int[] fuel = testCases.get(i);
            System.out.println("solving test case number "+(i+1));
            int[] memo = new int[fuel.length];
            Arrays.fill(memo, -2);
            System.out.println("Minimum number of flight required: " + minNumberOfPlanes(fuel, 0, memo)+"\n");
        }
        
        //Second Approach by Dynamic 
        System.out.println("*********Second Approach by DP********** \n");
        for(int i = 0; i<testCases.size(); i++){
            int[] fuel = testCases.get(i);
            System.out.println("solving test case number "+(i+1));

            System.out.println("Minimum number of flight required: " + minFlightsDP(fuel)+"\n");
        }
        
        
    }
}
