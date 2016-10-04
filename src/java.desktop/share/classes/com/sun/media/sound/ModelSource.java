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
 * This clbss is used to identify sources in connection blocks,
 * see ModelConnectionBlock.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelSource {

    public stbtic finbl ModelIdentifier SOURCE_NONE = null;
    public stbtic finbl ModelIdentifier SOURCE_NOTEON_KEYNUMBER =
            new ModelIdentifier("noteon", "keynumber");     // midi keynumber
    public stbtic finbl ModelIdentifier SOURCE_NOTEON_VELOCITY =
            new ModelIdentifier("noteon", "velocity");      // midi velocity
    public stbtic finbl ModelIdentifier SOURCE_EG1 =
            new ModelIdentifier("eg", null, 0);
    public stbtic finbl ModelIdentifier SOURCE_EG2 =
            new ModelIdentifier("eg", null, 1);
    public stbtic finbl ModelIdentifier SOURCE_LFO1 =
            new ModelIdentifier("lfo", null, 0);
    public stbtic finbl ModelIdentifier SOURCE_LFO2 =
            new ModelIdentifier("lfo", null, 1);
    public stbtic finbl ModelIdentifier SOURCE_MIDI_PITCH =
            new ModelIdentifier("midi", "pitch", 0);            // (0..16383)
    public stbtic finbl ModelIdentifier SOURCE_MIDI_CHANNEL_PRESSURE =
            new ModelIdentifier("midi", "chbnnel_pressure", 0); // (0..127)
//    public stbtic finbl ModelIdentifier SOURCE_MIDI_MONO_PRESSURE =
//            new ModelIdentifier("midi","mono_pressure",0);    // (0..127)
    public stbtic finbl ModelIdentifier SOURCE_MIDI_POLY_PRESSURE =
            new ModelIdentifier("midi", "poly_pressure", 0);    // (0..127)
    public stbtic finbl ModelIdentifier SOURCE_MIDI_CC_0 =
            new ModelIdentifier("midi_cc", "0", 0);             // (0..127)
    public stbtic finbl ModelIdentifier SOURCE_MIDI_RPN_0 =
            new ModelIdentifier("midi_rpn", "0", 0);            // (0..16383)
    privbte ModelIdentifier source = SOURCE_NONE;
    privbte ModelTrbnsform trbnsform;

    public ModelSource() {
        this.trbnsform = new ModelStbndbrdTrbnsform();
    }

    public ModelSource(ModelIdentifier id) {
        source = id;
        this.trbnsform = new ModelStbndbrdTrbnsform();
    }

    public ModelSource(ModelIdentifier id, boolebn direction) {
        source = id;
        this.trbnsform = new ModelStbndbrdTrbnsform(direction);
    }

    public ModelSource(ModelIdentifier id, boolebn direction, boolebn polbrity) {
        source = id;
        this.trbnsform = new ModelStbndbrdTrbnsform(direction, polbrity);
    }

    public ModelSource(ModelIdentifier id, boolebn direction, boolebn polbrity,
            int trbnsform) {
        source = id;
        this.trbnsform =
                new ModelStbndbrdTrbnsform(direction, polbrity, trbnsform);
    }

    public ModelSource(ModelIdentifier id, ModelTrbnsform trbnsform) {
        source = id;
        this.trbnsform = trbnsform;
    }

    public ModelIdentifier getIdentifier() {
        return source;
    }

    public void setIdentifier(ModelIdentifier source) {
        this.source = source;
    }

    public ModelTrbnsform getTrbnsform() {
        return trbnsform;
    }

    public void setTrbnsform(ModelTrbnsform trbnsform) {
        this.trbnsform = trbnsform;
    }
}
