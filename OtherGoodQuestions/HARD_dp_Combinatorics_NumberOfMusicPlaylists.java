package striversSheet.OtherGoodQuestions;

import java.util.Arrays;

//https://youtu.be/xRqkSe5pPSw -> BEST VIDEO EXPLANATION. + SEE QUES NOTES.[DP]

//https://leetcode.com/problems/number-of-music-playlists/
public class HARD_dp_Combinatorics_NumberOfMusicPlaylists {

    int mod = (int)(1e9+7);
    int n, goal, k;
    long[][] dp;
    public int numMusicPlaylists(int n, int goal, int k) {
        this.n = n;
        this.goal = goal;
        this.k = k;
        dp = new long[n+1][goal+1];//dp[used][pos]
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }

        return (int)makeCombinations(0, 0);
    }

    //note whenever chances of val overflowing int and ques says do 1e9+7 mod, take the rec functin to be LONG
    private long makeCombinations(int used, int pos) {
        //BC
        if (n<used)//when used songs are greater than Given song, it doesnt make sense. Hence 0 ways.
            return 0;

        if (pos == goal) {//when complete playlist length reached
            if (n!=used) return 0;//i.e. all songs weren't used hence return 0 ways

            return 1;
        }


        //Memo
        if (dp[used][pos]!=-1)
            return dp[used][pos];


        /*
            There are 2 options for "pos":
                1. play a used song. -> ways to choose a used song = (used-k)C(1) = (used-k) -> This can be negative, So when it is, Use 0. Hence Math.max used
                2. play a new song. -> Ways to choose a new song = (n-used)C(1) = (n-used)
         */

        long takingUsedSong = (Math.max(used-k, 0)*makeCombinations(used, pos+1))%mod;      //total ways[to fill playlist upto pos] when we take an already used song
        long takingNewSong = ((n-used)*makeCombinations(used+1, pos+1))%mod;           //total ways[to fill playlist upto pos] when we take a new song.

        long totalWaysToFillPlaylistUptoPOS = takingUsedSong + takingNewSong; //WHY +? -> Because we can fill the pos by either a new OR a used song.
                                                                             //HENCE  OR ==== +
        return dp[used][pos] = totalWaysToFillPlaylistUptoPOS;
    }
}
