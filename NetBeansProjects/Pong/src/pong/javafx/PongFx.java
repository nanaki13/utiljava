/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package pong.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pong.Context;
import pong.PongEngine;

public class PongFx extends Application {

    private static MainFrame mainFrame;
    private Group root;

    public static MainFrame getMainFrame() {
        return mainFrame;
    }
    private Timeline timeline;
    
    @Override 
    public void start(Stage stage) {
//        Config.initialize();
        root = new Group();
//        mainFrame = new MainFrame(root);
        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setWidth(Context._WIDTH);
        stage.setHeight(Context._HEIGHT);
        Scene scene = new Scene(root);
        scene.setFill(Color.BLUE);
        
        stage.setScene(scene);
        PongEngine engine = new PongEngine();
        
//        Group group = new Group();
//        parent.getChildren().add(group);
        final Plateau plateau = new Plateau();
        root.getChildren().add(plateau);
         timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(ConfigFx.ANIMATION_TIME, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                engine.nextState(Context._WIDTH, Context._HEIGHT);
                plateau.moveBall(Context.pcBall);
                plateau.moveBarD(Context.pcBarDroite);
                plateau.moveBarG(Context.pcBarGauche);
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();
//        timeline.
//        mainFrame.changeState(MainFrame.SPLASH);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public class MainFrame {
        // Instance of scene root node
//        private Group root;
//
//        // Instance of splash (if exists)
//        private Splash splash;
//
//        // Instance of level (if exists)
//        private Level level;
//
//        // Number of lifes
//        private int lifeCount;
//
//        // Current score
//        private int score;
//
//        private MainFrame(Group root) {
//            this.root = root;
//        }
//
//        public int getState() {
//            return state;
//        }
//
//        public int getScore() {
//            return score;
//        }
//
//        public void setScore(int score) {
//            this.score = score;
//        }
//
//        public int getLifeCount() {
//            return lifeCount;
//        }
//
//        public void increaseLives() {
//            lifeCount = Math.min(lifeCount + 1, Config.MAX_LIVES);
//        }
//
//        public void decreaseLives() {
//            lifeCount--;
//        }
//
//        // Initializes game (lifes, scores etc)
//        public void startGame() {
//            lifeCount = 3;
//            score = 0;
//            changeState(1);
//        }
//
//        // Current state of the game. The next values are available
//        // 0 - Splash
//        public static final int SPLASH = 0;
//        // 1..Level.LEVEL_COUNT - Level
//        private int state = SPLASH;
//
//        public void changeState(int newState) {
//            this.state = newState;
//            if (splash != null) {
//                splash.stop();
//            }
//            if (level != null) {
//                level.stop();
//            }
//            if (state < 1 || state > LevelData.getLevelsCount()) {
//                root.getChildren().remove(level);
//                level = null;
//                splash = new Splash();
//                root.getChildren().add(splash);
//                splash.start();
//            } else {
//                root.getChildren().remove(splash);
//                splash = null;
//                level = new Level(state);
//                root.getChildren().add(level);
//                level.start();
//            }
//        }
    }

}

