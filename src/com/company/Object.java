package com.company;

// Klasse for alle objeketer -> Alle extender fra denne
/*
 * Litt av grunnen er at vi da kan iterate over alle objektene som exstender denne gjennom et object array
 */

import com.jogamp.opengl.GL2;

import java.io.Serializable;

public class Object implements Serializable{

    boolean enable = true; // Om fysikk skal beregnes for dette objektet


    Object() {
    }

    void draw(GL2 gl) {

    }

    void update(Scene scene) {


    }

    void addForce(Vector v) {

    }

}
