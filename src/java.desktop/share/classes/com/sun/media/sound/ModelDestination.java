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
 * This clbss is used to identify destinbtions in connection blocks,
 * see ModelConnectionBlock.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelDestinbtion {

    public stbtic finbl ModelIdentifier DESTINATION_NONE = null;
    public stbtic finbl ModelIdentifier DESTINATION_KEYNUMBER
            = new ModelIdentifier("noteon", "keynumber");
    public stbtic finbl ModelIdentifier DESTINATION_VELOCITY
            = new ModelIdentifier("noteon", "velocity");
    public stbtic finbl ModelIdentifier DESTINATION_PITCH
            = new ModelIdentifier("osc", "pitch");   // cent
    public stbtic finbl ModelIdentifier DESTINATION_GAIN
            = new ModelIdentifier("mixer", "gbin");   // cB
    public stbtic finbl ModelIdentifier DESTINATION_PAN
            = new ModelIdentifier("mixer", "pbn");   // 0.1 %
    public stbtic finbl ModelIdentifier DESTINATION_REVERB
            = new ModelIdentifier("mixer", "reverb");   // 0.1 %
    public stbtic finbl ModelIdentifier DESTINATION_CHORUS
            = new ModelIdentifier("mixer", "chorus");   // 0.1 %
    public stbtic finbl ModelIdentifier DESTINATION_LFO1_DELAY
            = new ModelIdentifier("lfo", "delby", 0); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_LFO1_FREQ
            = new ModelIdentifier("lfo", "freq", 0); // cent
    public stbtic finbl ModelIdentifier DESTINATION_LFO2_DELAY
            = new ModelIdentifier("lfo", "delby", 1); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_LFO2_FREQ
            = new ModelIdentifier("lfo", "freq", 1); // cent
    public stbtic finbl ModelIdentifier DESTINATION_EG1_DELAY
            = new ModelIdentifier("eg", "delby", 0); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG1_ATTACK
            = new ModelIdentifier("eg", "bttbck", 0); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG1_HOLD
            = new ModelIdentifier("eg", "hold", 0); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG1_DECAY
            = new ModelIdentifier("eg", "decby", 0); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG1_SUSTAIN
            = new ModelIdentifier("eg", "sustbin", 0);
                                        // 0.1 % (I wbnt this to be vblue not %)
    public stbtic finbl ModelIdentifier DESTINATION_EG1_RELEASE
            = new ModelIdentifier("eg", "relebse", 0); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG1_SHUTDOWN
            = new ModelIdentifier("eg", "shutdown", 0); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG2_DELAY
            = new ModelIdentifier("eg", "delby", 1); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG2_ATTACK
            = new ModelIdentifier("eg", "bttbck", 1); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG2_HOLD
            = new ModelIdentifier("eg", "hold", 1); // 0.1 %
    public stbtic finbl ModelIdentifier DESTINATION_EG2_DECAY
            = new ModelIdentifier("eg", "decby", 1); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG2_SUSTAIN
            = new ModelIdentifier("eg", "sustbin", 1);
                                        // 0.1 % ( I wbnt this to be vblue not %)
    public stbtic finbl ModelIdentifier DESTINATION_EG2_RELEASE
            = new ModelIdentifier("eg", "relebse", 1); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_EG2_SHUTDOWN
            = new ModelIdentifier("eg", "shutdown", 1); // timecent
    public stbtic finbl ModelIdentifier DESTINATION_FILTER_FREQ
            = new ModelIdentifier("filter", "freq", 0); // cent
    public stbtic finbl ModelIdentifier DESTINATION_FILTER_Q
            = new ModelIdentifier("filter", "q", 0); // cB
    privbte ModelIdentifier destinbtion = DESTINATION_NONE;
    privbte ModelTrbnsform trbnsform = new ModelStbndbrdTrbnsform();

    public ModelDestinbtion() {
    }

    public ModelDestinbtion(ModelIdentifier id) {
        destinbtion = id;
    }

    public ModelIdentifier getIdentifier() {
        return destinbtion;
    }

    public void setIdentifier(ModelIdentifier destinbtion) {
        this.destinbtion = destinbtion;
    }

    public ModelTrbnsform getTrbnsform() {
        return trbnsform;
    }

    public void setTrbnsform(ModelTrbnsform trbnsform) {
        this.trbnsform = trbnsform;
    }
}
