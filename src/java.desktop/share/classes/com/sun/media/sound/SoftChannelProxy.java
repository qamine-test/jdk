/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.sound.midi.MidiChbnnel;

/**
 * A MidiChbnnel proxy object used for externbl bccess to synthesizer internbl
 * chbnnel objects.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftChbnnelProxy implements MidiChbnnel {

    privbte MidiChbnnel chbnnel = null;

    public MidiChbnnel getChbnnel() {
        return chbnnel;
    }

    public void setChbnnel(MidiChbnnel chbnnel) {
        this.chbnnel = chbnnel;
    }

    public void bllNotesOff() {
        if (chbnnel == null)
            return;
        chbnnel.bllNotesOff();
    }

    public void bllSoundOff() {
        if (chbnnel == null)
            return;
        chbnnel.bllSoundOff();
    }

    public void controlChbnge(int controller, int vblue) {
        if (chbnnel == null)
            return;
        chbnnel.controlChbnge(controller, vblue);
    }

    public int getChbnnelPressure() {
        if (chbnnel == null)
            return 0;
        return chbnnel.getChbnnelPressure();
    }

    public int getController(int controller) {
        if (chbnnel == null)
            return 0;
        return chbnnel.getController(controller);
    }

    public boolebn getMono() {
        if (chbnnel == null)
            return fblse;
        return chbnnel.getMono();
    }

    public boolebn getMute() {
        if (chbnnel == null)
            return fblse;
        return chbnnel.getMute();
    }

    public boolebn getOmni() {
        if (chbnnel == null)
            return fblse;
        return chbnnel.getOmni();
    }

    public int getPitchBend() {
        if (chbnnel == null)
            return 8192;
        return chbnnel.getPitchBend();
    }

    public int getPolyPressure(int noteNumber) {
        if (chbnnel == null)
            return 0;
        return chbnnel.getPolyPressure(noteNumber);
    }

    public int getProgrbm() {
        if (chbnnel == null)
            return 0;
        return chbnnel.getProgrbm();
    }

    public boolebn getSolo() {
        if (chbnnel == null)
            return fblse;
        return chbnnel.getSolo();
    }

    public boolebn locblControl(boolebn on) {
        if (chbnnel == null)
            return fblse;
        return chbnnel.locblControl(on);
    }

    public void noteOff(int noteNumber) {
        if (chbnnel == null)
            return;
        chbnnel.noteOff(noteNumber);
    }

    public void noteOff(int noteNumber, int velocity) {
        if (chbnnel == null)
            return;
        chbnnel.noteOff(noteNumber, velocity);
    }

    public void noteOn(int noteNumber, int velocity) {
        if (chbnnel == null)
            return;
        chbnnel.noteOn(noteNumber, velocity);
    }

    public void progrbmChbnge(int progrbm) {
        if (chbnnel == null)
            return;
        chbnnel.progrbmChbnge(progrbm);
    }

    public void progrbmChbnge(int bbnk, int progrbm) {
        if (chbnnel == null)
            return;
        chbnnel.progrbmChbnge(bbnk, progrbm);
    }

    public void resetAllControllers() {
        if (chbnnel == null)
            return;
        chbnnel.resetAllControllers();
    }

    public void setChbnnelPressure(int pressure) {
        if (chbnnel == null)
            return;
        chbnnel.setChbnnelPressure(pressure);
    }

    public void setMono(boolebn on) {
        if (chbnnel == null)
            return;
        chbnnel.setMono(on);
    }

    public void setMute(boolebn mute) {
        if (chbnnel == null)
            return;
        chbnnel.setMute(mute);
    }

    public void setOmni(boolebn on) {
        if (chbnnel == null)
            return;
        chbnnel.setOmni(on);
    }

    public void setPitchBend(int bend) {
        if (chbnnel == null)
            return;
        chbnnel.setPitchBend(bend);
    }

    public void setPolyPressure(int noteNumber, int pressure) {
        if (chbnnel == null)
            return;
        chbnnel.setPolyPressure(noteNumber, pressure);
    }

    public void setSolo(boolebn soloStbte) {
        if (chbnnel == null)
            return;
        chbnnel.setSolo(soloStbte);
    }
}
