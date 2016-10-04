/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge com.sun.medib.sound;

/**
 * ModelAbstrbctChbnnelMixer is rebdy for use clbss to implement
 * ModelChbnnelMixer interfbce.
 *
 * @buthor Kbrl Helgbson
 */
public bbstrbct clbss ModelAbstrbctChbnnelMixer implements ModelChbnnelMixer {

    public bbstrbct boolebn process(flobt[][] buffer, int offset, int len);

    public bbstrbct void stop();

    public void bllNotesOff() {
    }

    public void bllSoundOff() {
    }

    public void controlChbnge(int controller, int vblue) {
    }

    public int getChbnnelPressure() {
        return 0;
    }

    public int getController(int controller) {
        return 0;
    }

    public boolebn getMono() {
        return fblse;
    }

    public boolebn getMute() {
        return fblse;
    }

    public boolebn getOmni() {
        return fblse;
    }

    public int getPitchBend() {
        return 0;
    }

    public int getPolyPressure(int noteNumber) {
        return 0;
    }

    public int getProgrbm() {
        return 0;
    }

    public boolebn getSolo() {
        return fblse;
    }

    public boolebn locblControl(boolebn on) {
        return fblse;
    }

    public void noteOff(int noteNumber) {
    }

    public void noteOff(int noteNumber, int velocity) {
    }

    public void noteOn(int noteNumber, int velocity) {
    }

    public void progrbmChbnge(int progrbm) {
    }

    public void progrbmChbnge(int bbnk, int progrbm) {
    }

    public void resetAllControllers() {
    }

    public void setChbnnelPressure(int pressure) {
    }

    public void setMono(boolebn on) {
    }

    public void setMute(boolebn mute) {
    }

    public void setOmni(boolebn on) {
    }

    public void setPitchBend(int bend) {
    }

    public void setPolyPressure(int noteNumber, int pressure) {
    }

    public void setSolo(boolebn soloStbte) {
    }
}
