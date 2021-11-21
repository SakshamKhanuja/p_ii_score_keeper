package com.project.score_keeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Indicates Team A is currently batting.
    private ImageView statusTeamA;

    // Indicates Team B is currently batting.
    private ImageView statusTeamB;

    // Resets the match.
    private Button reset;

    // Indicates that the balling Team has thrown a valid delivery.
    private Button deliveryValid;

    /*
     * Indicates that the balling Team has thrown a "WIDE" delivery, resulting in:
     *
     * 1. Extra 1 Run awarded to the batting Team.
     * 2. Current delivery is NOT counted.
     * 3. If the batting person gets OUT on a wide delivery, both above points are still taken
     * into account.
     */
    private Button deliveryWide;

    /*
     * Indicates that the balling Team has thrown a "No Ball", resulting in:
     *
     * 1. Extra 1 Run awarded to the batting Team.
     * 2. Current delivery is NOT counted.
     * 3. Batting person is considered OUT only he/she was initial OUT for running between the
     *  wickets.
     */
    private Button deliveryNo;

    // Pressing this Button adds 0 run to the current Batting Team.
    private Button runZero;

    // Pressing this Button adds 1 run to the current Batting Team.
    private Button runOne;

    // Pressing this Button adds 2 run to the current Batting Team.
    private Button runTwo;

    // Pressing this Button adds 3 run to the current Batting Team.
    private Button runThree;

    // Pressing this Button adds 4 run to the current Batting Team.
    private Button runFour;

    // Pressing this Button adds 5 run to the current Batting Team.
    private Button runFive;

    // Pressing this Button adds 6 run to the current Batting Team.
    private Button runSix;

    // Pressing this Button adds 1 wicket (person Batting is OUT) to the current Batting Team.
    private Button wicket;

    // Provides user to pick a Team that bats first.
    private LinearLayout linearLayoutQuestion;

    // Shows the total runs scored by Team A.
    private TextView scoreTeamATextView;

    // Shows how many wickets Team A has lost.
    private TextView wicketsTeamATextView;

    // Shows the total runs scored by Team A.
    private TextView scoreTeamBTextView;

    // Shows how many wickets Team A has lost.
    private TextView wicketsTeamBTextView;

    // Shows the match progress.
    private TextView overCountTeamATextView;

    // Shows the match progress.
    private TextView overCountTeamBTextView;

    // Shows the number of wide balls delivered by Team B to Team A.
    private TextView wideBallTeamATextView;

    // Shows the number of wide balls delivered by Team A to Team B.
    private TextView wideBallTeamBTextView;

    // Shows the number of No balls delivered by Team B to Team A.
    private TextView noBallTeamATextView;

    // Shows the number of No balls delivered by Team A to Team B.
    private TextView noBallTeamBTextView;

    // Shows the number of deliveries left for Team A to bat on.
    private TextView ballsLeftTeamATextView;

    // Shows the number of deliveries left for Team B to bat on.
    private TextView ballsLeftTeamBTextView;

    // Contains info. regarding the result of the match.
    private LinearLayout winningContainerLayout;

    // Shows which Team has won the match.
    private TextView winningTeamTextView;

    // Scoreboard

    /*
     * Represents which Team is currently batting:
     * 0 - Error / User hasn't chosen any team
     * 1 - Team A
     * 2 - Team B
     */
    private int battingTeam = 0;

    // Represents how many deliveries per innings.
    private int totalDeliveries = 24;

    // Default score for Team A.
    private int scoreTeamA = 0;

    // Default score for Team B.
    private int scoreTeamB = 0;

    // Number of balls delivered in the current over (0 - 5).
    private int ballCount = 0;

    // Current Over.
    private int overCount = 0;

    // Number of Team A wickets scored by Team B.
    private int wicketsTeamA = 0;

    // Number of Team B wickets scored by Team A.
    private int wicketsTeamB = 0;

    // Number of wide balls delivered by Team B to Team A.
    private int wideBallTeamA = 0;

    // Number of wide balls delivered by Team A to Team B.
    private int wideBallTeamB = 0;

    // Number of no balls delivered by Team B to Team A.
    private int noBallTeamA = 0;

    // Number of no balls delivered by Team A to Team B.
    private int noBallTeamB = 0;

    private int maxWickets = 4;
    private int maxOvers = 6;
    private boolean teamAAllOut = false;
    private boolean teamBAllOut = false;
    private int ballsLeftTeamA = 24;
    private int ballsLeftTeamB = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing all Views.
        initializeViews();
    }

    /**
     * Initializes all Views.
     */
    private void initializeViews() {

        // Initializing score TextViews of Team A and Team B
        scoreTeamATextView = findViewById(R.id.runs_scored_team_a);
        scoreTeamBTextView = findViewById(R.id.runs_scored_team_b);

        // Initializing wicket TextViews of Team A and Team B
        wicketsTeamATextView = findViewById(R.id.wickets_team_a);
        wicketsTeamBTextView = findViewById(R.id.wickets_team_b);

        // Initializing over count counter for Team A and Team B
        overCountTeamATextView = findViewById(R.id.over_count_team_a);
        overCountTeamBTextView = findViewById(R.id.over_count_team_b);

        // Initializing balls left counter for Team A and Team B
        ballsLeftTeamATextView = findViewById(R.id.balls_left_a);
        ballsLeftTeamBTextView = findViewById(R.id.balls_left_b);

        // Initializing wide count counter for Team A and Team B
        wideBallTeamATextView = findViewById(R.id.wide_ball_team_a);
        wideBallTeamBTextView = findViewById(R.id.wide_ball_team_b);

        // Initializing no ball counter for Team A and Team B.
        noBallTeamATextView = findViewById(R.id.no_ball_team_a);
        noBallTeamBTextView = findViewById(R.id.no_ball_team_b);

        // Initializing Team Picker and its contents.
        linearLayoutQuestion = findViewById(R.id.linear_layout_question_container);
        statusTeamA = findViewById(R.id.image_view_status_team_A);
        statusTeamB = findViewById(R.id.image_view_status_team_B);

        // Initializing Reset Button.
        reset = findViewById(R.id.button_reset);

        // Initializing all Delivery type Buttons.
        deliveryValid = findViewById(R.id.button_delivery_valid);
        deliveryNo = findViewById(R.id.button_delivery_no);
        deliveryWide = findViewById(R.id.button_delivery_wide);

        // Initializing all Run Buttons.
        runZero = findViewById(R.id.button_run_zero);
        runOne = findViewById(R.id.button_run_one);
        runTwo = findViewById(R.id.button_run_two);
        runThree = findViewById(R.id.button_run_three);
        runFour = findViewById(R.id.button_run_four);
        runFive = findViewById(R.id.button_run_five);
        runSix = findViewById(R.id.button_run_six);

        // Initializing Wicket Button.
        wicket = findViewById(R.id.button_wicket);

        // Initializing winning Views.
        winningContainerLayout = findViewById(R.id.winner_container);
        winningTeamTextView = findViewById(R.id.winner_team_text_view);
    }

    /**
     * Initial setup for Score Keeper.
     */
    private void setupViews() {
        setTeamStatus();
        enableDeliveryDisableRun();
    }

    /**
     * Enables all Delivery Buttons and disables all Runs Scored Buttons.
     */
    private void enableDeliveryDisableRun() {
        enableDisableViews(true, R.drawable.ripple_delivery, deliveryValid, deliveryWide,
                deliveryNo);
        enableDisableViews(false, R.drawable.shape_runs_disabled, runZero, runOne, runTwo,
                runThree, runFour, runFive, runSix, wicket);
    }

    /**
     * A valid delivery was thrown by the baller.
     *
     * @param view is the clicked "Valid" Button.
     */
    public void validDeliveryWasThrown(View view) {

        updateOverCount();

        boolean isLastBall = countdownDelivery();

        enableDisableViews(false, R.drawable.shape_delivery_selected_valid, deliveryValid);
        enableDisableViews(true, R.drawable.ripple_runs_enabled, runZero, runOne, runTwo,
                runThree, runFour, runFive, runSix);
        enableDisableViews(true, R.drawable.ripple_wicket, wicket);
        enableDisableViews(false, R.drawable.shape_rectangle_outline, deliveryWide,
                deliveryNo);
    }

    /**
     * Decrements the number of balls left by 1.
     *
     * @return true if all balls are delivered, otherwise false.
     */
    private boolean countdownDelivery() {
        if (battingTeam == 1) {
            ballsLeftTeamATextView.setText(String.valueOf(--ballsLeftTeamA));
            return (ballsLeftTeamA == 0);
        } else {
            ballsLeftTeamBTextView.setText(String.valueOf(--ballsLeftTeamB));
            return (ballsLeftTeamB == 0);
        }
    }

    /**
     * Updates the over count.
     */
    private void updateOverCount() {
        StringBuilder builder = new StringBuilder();

        if (ballCount < 5) {
            // 0.1, 0.2, 0.3, 0.4, 0.5
            ballCount += 1;

            // Update over and ball count
            if (battingTeam == 1) {
                overCountTeamATextView.setText(builder.append(overCount).append(".").append(ballCount)
                        .toString());
            } else {
                overCountTeamBTextView.setText(builder.append(overCount).append(".").append(ballCount)
                        .toString());
            }

        }
        // Checks if this is last ball of the over.
        else if (ballCount == 5) {
            overCount++;
            ballCount = 0;

            // Update over count
            if (battingTeam == 1) {
                overCountTeamATextView.setText(builder.append(overCount).toString());
            } else {
                overCountTeamBTextView.setText(builder.append(overCount).toString());
            }
        }
    }

    /**
     * Batting Team scored no run.
     *
     * @param view is the clicked "0" Button.
     */
    public void addZero(View view) {
        updateScoreBoard(0);
    }

    /**
     * Batting Team scored a single run.
     *
     * @param view is the clicked "1" Button.
     */
    public void addOne(View view) {
        updateScoreBoard(1);
    }

    /**
     * Batting Team scored double runs.
     *
     * @param view is the clicked "2" Button.
     */
    public void addTwo(View view) {
        updateScoreBoard(2);
    }

    /**
     * Batting Team scored three runs.
     *
     * @param view is the clicked "3" Button.
     */
    public void addThree(View view) {
        updateScoreBoard(3);
    }

    /**
     * Batting Team scored a four.
     *
     * @param view is the clicked "4" Button.
     */
    public void addFour(View view) {
        updateScoreBoard(4);
    }

    /**
     * Batting Team scored five runs.
     *
     * @param view is the clicked "5" Button.
     */
    public void addFive(View view) {
        updateScoreBoard(5);
    }

    /**
     * Batting Team scored a six.
     *
     * @param view is the clicked "6" Button.
     */
    public void addSix(View view) {
        updateScoreBoard(6);
    }

    /**
     * Shows the user who won the match.
     */
    private void setWinningTeam() {
        // Show the winning layout.
        winningContainerLayout.setVisibility(View.VISIBLE);

        // Check if Team A has won the match.
        if (scoreTeamA > scoreTeamB) {
            // Team A WON!
            winningTeamTextView.setText(getString(R.string.labelTeamA));
        } else {
            // Team B WON!
            winningTeamTextView.setText(getString(R.string.labelTeamB));
        }
    }

    /**
     * Updates the wicket count of the batting Team.
     *
     * @param view is the clicked "Wicket" Button.
     */
    public void updateWicket(View view) {

        enableDeliveryDisableRun();

        if (battingTeam == 1) {

            // Team A lost its wicket.

            wicketsTeamATextView.setText(String.valueOf(++wicketsTeamA));
            if (wicketsTeamA == maxWickets) {

                // Team A has lost ALL wickets.

                // Check if Team B has batted already.
                if (scoreTeamB != 0 || wicketsTeamB != 0 || ballsLeftTeamB != 24) {

                    setWinningTeam();

                } else {
                    // Team B will bat now.
                    battingTeam = 2;

                    // Reset values for var. ballCount and var. over count.
                    ballCount = 0;
                    overCount = 0;

                    setupViews();
                }
            }
        } else {

            // Team B lost its wicket.

            wicketsTeamBTextView.setText(String.valueOf(++wicketsTeamB));
            if (wicketsTeamB == maxWickets) {

                // Team B has lost ALL wickets.

                // Check if Team A has batted already.
                if (scoreTeamA != 0 || wicketsTeamA != 0 || ballsLeftTeamA != 24) {

                    setWinningTeam();

                } else {
                    // Team A will bat now.
                    battingTeam = 1;

                    // Reset values for var. ballCount and var. over count.
                    ballCount = 0;
                    overCount = 0;

                    setupViews();
                }
            }
        }
    }

    /**
     * Adds runs to the scoreboard of the current batting Team.
     *
     * @param runs is the total runs scored in a delivery.
     */
    private void updateScoreBoard(int runs) {
        enableDeliveryDisableRun();
        addRuns(runs);
    }

    /**
     * Adds run to the current Batting Team.
     *
     * @param runs scored in a single delivery.
     */
    private void addRuns(int runs) {
        if (battingTeam == 1) {
            scoreTeamA += runs;
            scoreTeamATextView.setText(String.valueOf(scoreTeamA));
        } else {
            scoreTeamB += runs;
            scoreTeamBTextView.setText(String.valueOf(scoreTeamB));
        }
    }

    /**
     * A wide delivery was thrown by the baller. Extra run is rewarded to the batting team and,
     * the delivery is not counted.
     *
     * @param view is the clicked "Wide" Button.
     */
    public void wideDeliveryWasThrown(View view) {

        // Adding 1 run to the batting team.
        addRuns(1);

        if (battingTeam == 1) {
            wideBallTeamATextView.setText(String.valueOf(++wideBallTeamA));
        } else {
            wideBallTeamBTextView.setText(String.valueOf(++wideBallTeamB));
        }

        enableDisableViews(false, R.drawable.shape_delivery_selected_wide, deliveryWide);
        enableDisableViews(true, R.drawable.ripple_runs_enabled, runZero, runOne, runTwo,
                runThree, runFour, runFive);
        enableDisableViews(false, R.drawable.shape_runs_disabled, runSix);
        enableDisableViews(true, R.drawable.ripple_wicket, wicket);
        enableDisableViews(false, R.drawable.shape_rectangle_outline, deliveryValid,
                deliveryNo);
    }

    /**
     * A no delivery was thrown by the baller. Extra run is rewarded to the batting team and,
     * the delivery is not counted.
     * <p>
     * TODO 1 : Complete this function documentation.
     *
     * @param view is the clicked "Wide" Button.
     */
    public void noBallWasThrown(View view) {

        // Adding 1 run to the batting team.
        addRuns(1);

        if (battingTeam == 1) {
            noBallTeamATextView.setText(String.valueOf(++noBallTeamA));
        } else {
            noBallTeamBTextView.setText(String.valueOf(++noBallTeamB));
        }

        enableDisableViews(false, R.drawable.shape_delivery_selected_no, deliveryNo);
        enableDisableViews(true, R.drawable.ripple_runs_enabled, runZero, runOne, runTwo,
                runThree, runFour, runFive, runSix);
        enableDisableViews(false, R.drawable.shape_rectangle_outline, deliveryWide,
                deliveryValid);
        enableDisableViews(false, R.drawable.shape_runs_disabled, wicket);
    }

    /**
     * Indicates which Team is currently batting.
     */
    private void setTeamStatus() {
        if (battingTeam == 1) {
            // Shows only Team A is batting.
            statusTeamB.setVisibility(View.INVISIBLE);
            statusTeamA.setVisibility(View.VISIBLE);

        } else {
            // Shows only Team A is batting.
            statusTeamA.setVisibility(View.INVISIBLE);
            statusTeamB.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Sets Team A to bat first.
     *
     * @param view is the clicked "Team A" Button.
     */
    public void chooseTeamA(View view) {
        battingTeam = 1;

        // Shows the Score Keeper
        showScoreKeeper();

        // Show the Reset Button
        reset.setVisibility(View.VISIBLE);
        setupViews();
    }

    /**
     * Shows the Score Keeper.
     */
    private void showScoreKeeper() {
        linearLayoutQuestion.setVisibility(View.GONE);
    }

    /**
     * Sets Team B to bat first.
     *
     * @param view is the clicked "Team B" Button.
     */
    public void chooseTeamB(View view) {
        battingTeam = 2;

        // Shows the Score Keeper
        showScoreKeeper();

        // Show the Reset Button
        reset.setVisibility(View.VISIBLE);
        setupViews();
    }

    /**
     * Enables or Disables Buttons on the Score Keeper dashboard based on the type of delivery.
     *
     * @param buttons              one or more Buttons.
     * @param enable               should be true if you want the button to get clicked,
     *                             otherwise false.
     * @param backgroundResourceID is the resource ID for a drawable which will be set as the
     *                             background to all passed Buttons.
     */
    private void enableDisableViews(boolean enable, int backgroundResourceID, Button... buttons) {
        for (Button button : buttons) {
            button.setEnabled(enable);
            button.setBackground(AppCompatResources.getDrawable(this, backgroundResourceID));
            if (enable) {
                button.setTextColor(ContextCompat.getColor(this, R.color.colorType));
            } else {
                button.setTextColor(ContextCompat.getColor(this, R.color.colorDisabled));
            }
        }
    }
}