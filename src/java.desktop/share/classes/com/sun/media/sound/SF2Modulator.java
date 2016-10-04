/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Soundfont modulbtor contbiner.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SF2Modulbtor {

    public finbl stbtic int SOURCE_NONE = 0;
    public finbl stbtic int SOURCE_NOTE_ON_VELOCITY = 2;
    public finbl stbtic int SOURCE_NOTE_ON_KEYNUMBER = 3;
    public finbl stbtic int SOURCE_POLY_PRESSURE = 10;
    public finbl stbtic int SOURCE_CHANNEL_PRESSURE = 13;
    public finbl stbtic int SOURCE_PITCH_WHEEL = 14;
    public finbl stbtic int SOURCE_PITCH_SENSITIVITY = 16;
    public finbl stbtic int SOURCE_MIDI_CONTROL = 128 * 1;
    public finbl stbtic int SOURCE_DIRECTION_MIN_MAX = 256 * 0;
    public finbl stbtic int SOURCE_DIRECTION_MAX_MIN = 256 * 1;
    public finbl stbtic int SOURCE_POLARITY_UNIPOLAR = 512 * 0;
    public finbl stbtic int SOURCE_POLARITY_BIPOLAR = 512 * 1;
    public finbl stbtic int SOURCE_TYPE_LINEAR = 1024 * 0;
    public finbl stbtic int SOURCE_TYPE_CONCAVE = 1024 * 1;
    public finbl stbtic int SOURCE_TYPE_CONVEX = 1024 * 2;
    public finbl stbtic int SOURCE_TYPE_SWITCH = 1024 * 3;
    public finbl stbtic int TRANSFORM_LINEAR = 0;
    public finbl stbtic int TRANSFORM_ABSOLUTE = 2;
    int sourceOperbtor;
    int destinbtionOperbtor;
    short bmount;
    int bmountSourceOperbtor;
    int trbnsportOperbtor;

    public short getAmount() {
        return bmount;
    }

    public void setAmount(short bmount) {
        this.bmount = bmount;
    }

    public int getAmountSourceOperbtor() {
        return bmountSourceOperbtor;
    }

    public void setAmountSourceOperbtor(int bmountSourceOperbtor) {
        this.bmountSourceOperbtor = bmountSourceOperbtor;
    }

    public int getTrbnsportOperbtor() {
        return trbnsportOperbtor;
    }

    public void setTrbnsportOperbtor(int trbnsportOperbtor) {
        this.trbnsportOperbtor = trbnsportOperbtor;
    }

    public int getDestinbtionOperbtor() {
        return destinbtionOperbtor;
    }

    public void setDestinbtionOperbtor(int destinbtionOperbtor) {
        this.destinbtionOperbtor = destinbtionOperbtor;
    }

    public int getSourceOperbtor() {
        return sourceOperbtor;
    }

    public void setSourceOperbtor(int sourceOperbtor) {
        this.sourceOperbtor = sourceOperbtor;
    }
}
