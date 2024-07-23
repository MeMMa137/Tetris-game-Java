package com.PD2.Tetris.App;

public class scoreEstimate {
    
    private int[] scoresPoolSpeed1 = {0, 100, 300, 600, 1000};
    private int[] scoresPoolSpeed2 = {0, 200, 500, 800, 1500};
    private int[] scoresPoolSpeed3 = {0, 300, 800, 1300, 2000};
    private int scorePoolLength = scoresPoolSpeed1.length;

    private int speed = 600;
    private int totalScore = 0;
    private int totalLine = 0;

    private Timer gameTimer = new Timer();

    public int getTotalScore() {
        return totalScore;
    }

    public int getTotalLine() {
        return totalLine;
    }

    public int getSpeed(){
        return speed;
    }

    public void resetScore() {
        totalScore = 0;
        totalLine = 0;
    }
    public void updateScore(int lines, int mode) {
        //bonus
        if(lines >= 1){
            if((gameTimer.getTime() / 1000 < 10) && (totalScore > 0)){
                totalScore = totalScore + (10-(int)(gameTimer.getTime()/1000))*60;
            }
            else if((gameTimer.getTime() / 1000 < 20) && (totalScore > 0)){
                totalScore = totalScore + (20-(int)(gameTimer.getTime()/1000))*16;
            }
            else if((gameTimer.getTime() / 1000 < 30) && (totalScore > 0)){
                totalScore = totalScore + (30-(int)(gameTimer.getTime()/1000))*5;
            }
            gameTimer.resetTimer();
            gameTimer.startTimer();
        }

        if(mode == 0){
            if (lines >= 0 && lines < scorePoolLength) {
                if((totalScore < 500)){
                    totalScore += scoresPoolSpeed1[lines];
                    totalLine += lines;
                    speed = 600-totalScore/10;
                }
                else if((totalScore >= 500 && totalScore < 2000)){
                    totalScore += scoresPoolSpeed2[lines];
                    totalLine += lines;
                    speed = 550 - totalScore/10;
                }
                else if((totalScore >= 2000 && totalScore < 2500)){
                    totalScore += scoresPoolSpeed3[lines];
                    totalLine += lines;
                    speed = 550 - totalScore/10;
                    if(speed <= 250){
                        speed = 250;
                    }
                }
                if(totalScore >= 2500){
                    totalScore = totalScore - 2;
                }
                if (lines >= 0 && lines < scorePoolLength) {
                    if(totalScore > 2500 && lines > 0){
                        totalScore = totalScore + 2;
                    }
        
                    if((totalScore >= 2500 && totalScore < 3500)){
                        totalScore += scoresPoolSpeed1[lines];
                        totalLine += lines;
                        speed = 550 - totalScore/15;
                    }
                    else if((totalScore >= 3500 && totalScore < 4500)){
                        totalScore += scoresPoolSpeed2[lines];
                        totalLine += lines;
                        speed = 550 - totalScore/15;
                        if(speed <= 200){
                            speed = 200;
                        }
                    }
                    else if((totalScore >= 4500 && totalScore < 5500)){
                        totalScore += scoresPoolSpeed3[lines];
                        totalLine += lines;
                        speed = 530 - totalScore/10;
                        if(speed <= 200){
                            speed = 200;
                        }
                    }
                }
               
                if(totalScore >= 5500){
                    speed = (int)(Math.random()*(500 - 300 + 1)) + 300;
                    if (lines >= 0 && lines < scorePoolLength) {
                        if((speed >= 400)){
                            totalScore += scoresPoolSpeed1[lines];
                            totalLine += lines;
                        }
                        else if(speed >= 350 && speed < 400){
                            totalScore += scoresPoolSpeed2[lines];
                            totalLine += lines;
                        }
                        else if(speed < 350){
                            totalScore += scoresPoolSpeed3[lines];
                            totalLine += lines;
                        }
                    }
                }
            }
        }
       
    }
}
