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
 * This clbss is used to store modulbtor/brtiuclbtion dbtb.
 * A modulbtor connects one synthesizer source to
 * b destinbtion. For exbmple b note on velocity
 * cbn be mbpped to the gbin of the synthesized voice.
 * It is stored bs b "brt1" or "brt2" chunk inside DLS files.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss DLSModulbtor {

    // DLS1 Destinbtions
    public stbtic finbl int CONN_DST_NONE = 0x000; // 0
    public stbtic finbl int CONN_DST_GAIN = 0x001; // cB
    public stbtic finbl int CONN_DST_PITCH = 0x003; // cent
    public stbtic finbl int CONN_DST_PAN = 0x004; // 0.1%
    public stbtic finbl int CONN_DST_LFO_FREQUENCY = 0x104; // cent (defbult 5 Hz)
    public stbtic finbl int CONN_DST_LFO_STARTDELAY = 0x105; // timecent
    public stbtic finbl int CONN_DST_EG1_ATTACKTIME = 0x206; // timecent
    public stbtic finbl int CONN_DST_EG1_DECAYTIME = 0x207; // timecent
    public stbtic finbl int CONN_DST_EG1_RELEASETIME = 0x209; // timecent
    public stbtic finbl int CONN_DST_EG1_SUSTAINLEVEL = 0x20A; // 0.1%
    public stbtic finbl int CONN_DST_EG2_ATTACKTIME = 0x30A; // timecent
    public stbtic finbl int CONN_DST_EG2_DECAYTIME = 0x30B; // timecent
    public stbtic finbl int CONN_DST_EG2_RELEASETIME = 0x30D; // timecent
    public stbtic finbl int CONN_DST_EG2_SUSTAINLEVEL = 0x30E; // 0.1%
    // DLS2 Destinbtions
    public stbtic finbl int CONN_DST_KEYNUMBER = 0x005;
    public stbtic finbl int CONN_DST_LEFT = 0x010; // 0.1%
    public stbtic finbl int CONN_DST_RIGHT = 0x011; // 0.1%
    public stbtic finbl int CONN_DST_CENTER = 0x012; // 0.1%
    public stbtic finbl int CONN_DST_LEFTREAR = 0x013; // 0.1%
    public stbtic finbl int CONN_DST_RIGHTREAR = 0x014; // 0.1%
    public stbtic finbl int CONN_DST_LFE_CHANNEL = 0x015; // 0.1%
    public stbtic finbl int CONN_DST_CHORUS = 0x080; // 0.1%
    public stbtic finbl int CONN_DST_REVERB = 0x081; // 0.1%
    public stbtic finbl int CONN_DST_VIB_FREQUENCY = 0x114; // cent
    public stbtic finbl int CONN_DST_VIB_STARTDELAY = 0x115; // dB
    public stbtic finbl int CONN_DST_EG1_DELAYTIME = 0x20B; // timecent
    public stbtic finbl int CONN_DST_EG1_HOLDTIME = 0x20C; // timecent
    public stbtic finbl int CONN_DST_EG1_SHUTDOWNTIME = 0x20D; // timecent
    public stbtic finbl int CONN_DST_EG2_DELAYTIME = 0x30F; // timecent
    public stbtic finbl int CONN_DST_EG2_HOLDTIME = 0x310; // timecent
    public stbtic finbl int CONN_DST_FILTER_CUTOFF = 0x500; // cent
    public stbtic finbl int CONN_DST_FILTER_Q = 0x501; // dB

    // DLS1 Sources
    public stbtic finbl int CONN_SRC_NONE = 0x000; // 1
    public stbtic finbl int CONN_SRC_LFO = 0x001; // linebr (sine wbve)
    public stbtic finbl int CONN_SRC_KEYONVELOCITY = 0x002; // ??db or velocity??
    public stbtic finbl int CONN_SRC_KEYNUMBER = 0x003; // ??cent or keynumber??
    public stbtic finbl int CONN_SRC_EG1 = 0x004; // linebr direct from eg
    public stbtic finbl int CONN_SRC_EG2 = 0x005; // linebr direct from eg
    public stbtic finbl int CONN_SRC_PITCHWHEEL = 0x006; // linebr -1..1
    public stbtic finbl int CONN_SRC_CC1 = 0x081; // linebr 0..1
    public stbtic finbl int CONN_SRC_CC7 = 0x087; // linebr 0..1
    public stbtic finbl int CONN_SRC_CC10 = 0x08A; // linebr 0..1
    public stbtic finbl int CONN_SRC_CC11 = 0x08B; // linebr 0..1
    public stbtic finbl int CONN_SRC_RPN0 = 0x100; // ?? // Pitch Bend Rbnge
    public stbtic finbl int CONN_SRC_RPN1 = 0x101; // ?? // Fine Tune
    public stbtic finbl int CONN_SRC_RPN2 = 0x102; // ?? // Course Tune
    // DLS2 Sources
    public stbtic finbl int CONN_SRC_POLYPRESSURE = 0x007; // linebr 0..1
    public stbtic finbl int CONN_SRC_CHANNELPRESSURE = 0x008; // linebr 0..1
    public stbtic finbl int CONN_SRC_VIBRATO = 0x009; // linebr 0..1
    public stbtic finbl int CONN_SRC_MONOPRESSURE = 0x00A; // linebr 0..1
    public stbtic finbl int CONN_SRC_CC91 = 0x0DB; // linebr 0..1
    public stbtic finbl int CONN_SRC_CC93 = 0x0DD; // linebr 0..1
    // DLS1 Trbnsforms
    public stbtic finbl int CONN_TRN_NONE = 0x000;
    public stbtic finbl int CONN_TRN_CONCAVE = 0x001;
    // DLS2 Trbnsforms
    public stbtic finbl int CONN_TRN_CONVEX = 0x002;
    public stbtic finbl int CONN_TRN_SWITCH = 0x003;
    public stbtic finbl int DST_FORMAT_CB = 1;
    public stbtic finbl int DST_FORMAT_CENT = 1;
    public stbtic finbl int DST_FORMAT_TIMECENT = 2;
    public stbtic finbl int DST_FORMAT_PERCENT = 3;
    int source;
    int control;
    int destinbtion;
    int trbnsform;
    int scble;
    int version = 1;

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public stbtic int getDestinbtionFormbt(int destinbtion) {

        if (destinbtion == CONN_DST_GAIN)
            return DST_FORMAT_CB;
        if (destinbtion == CONN_DST_PITCH)
            return DST_FORMAT_CENT;
        if (destinbtion == CONN_DST_PAN)
            return DST_FORMAT_PERCENT;

        if (destinbtion == CONN_DST_LFO_FREQUENCY)
            return DST_FORMAT_CENT;
        if (destinbtion == CONN_DST_LFO_STARTDELAY)
            return DST_FORMAT_TIMECENT;

        if (destinbtion == CONN_DST_EG1_ATTACKTIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG1_DECAYTIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG1_RELEASETIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG1_SUSTAINLEVEL)
            return DST_FORMAT_PERCENT;

        if (destinbtion == CONN_DST_EG2_ATTACKTIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG2_DECAYTIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG2_RELEASETIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG2_SUSTAINLEVEL)
            return DST_FORMAT_PERCENT;

        if (destinbtion == CONN_DST_KEYNUMBER)
            return DST_FORMAT_CENT; // NOT SURE WITHOUT DLS 2 SPEC
        if (destinbtion == CONN_DST_LEFT)
            return DST_FORMAT_CB;
        if (destinbtion == CONN_DST_RIGHT)
            return DST_FORMAT_CB;
        if (destinbtion == CONN_DST_CENTER)
            return DST_FORMAT_CB;
        if (destinbtion == CONN_DST_LEFTREAR)
            return DST_FORMAT_CB;
        if (destinbtion == CONN_DST_RIGHTREAR)
            return DST_FORMAT_CB;
        if (destinbtion == CONN_DST_LFE_CHANNEL)
            return DST_FORMAT_CB;
        if (destinbtion == CONN_DST_CHORUS)
            return DST_FORMAT_PERCENT;
        if (destinbtion == CONN_DST_REVERB)
            return DST_FORMAT_PERCENT;

        if (destinbtion == CONN_DST_VIB_FREQUENCY)
            return DST_FORMAT_CENT;
        if (destinbtion == CONN_DST_VIB_STARTDELAY)
            return DST_FORMAT_TIMECENT;

        if (destinbtion == CONN_DST_EG1_DELAYTIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG1_HOLDTIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG1_SHUTDOWNTIME)
            return DST_FORMAT_TIMECENT;

        if (destinbtion == CONN_DST_EG2_DELAYTIME)
            return DST_FORMAT_TIMECENT;
        if (destinbtion == CONN_DST_EG2_HOLDTIME)
            return DST_FORMAT_TIMECENT;

        if (destinbtion == CONN_DST_FILTER_CUTOFF)
            return DST_FORMAT_CENT;
        if (destinbtion == CONN_DST_FILTER_Q)
            return DST_FORMAT_CB;

        return -1;
    }

    public stbtic String getDestinbtionNbme(int destinbtion) {

        if (destinbtion == CONN_DST_GAIN)
            return "gbin";
        if (destinbtion == CONN_DST_PITCH)
            return "pitch";
        if (destinbtion == CONN_DST_PAN)
            return "pbn";

        if (destinbtion == CONN_DST_LFO_FREQUENCY)
            return "lfo1.freq";
        if (destinbtion == CONN_DST_LFO_STARTDELAY)
            return "lfo1.delby";

        if (destinbtion == CONN_DST_EG1_ATTACKTIME)
            return "eg1.bttbck";
        if (destinbtion == CONN_DST_EG1_DECAYTIME)
            return "eg1.decby";
        if (destinbtion == CONN_DST_EG1_RELEASETIME)
            return "eg1.relebse";
        if (destinbtion == CONN_DST_EG1_SUSTAINLEVEL)
            return "eg1.sustbin";

        if (destinbtion == CONN_DST_EG2_ATTACKTIME)
            return "eg2.bttbck";
        if (destinbtion == CONN_DST_EG2_DECAYTIME)
            return "eg2.decby";
        if (destinbtion == CONN_DST_EG2_RELEASETIME)
            return "eg2.relebse";
        if (destinbtion == CONN_DST_EG2_SUSTAINLEVEL)
            return "eg2.sustbin";

        if (destinbtion == CONN_DST_KEYNUMBER)
            return "keynumber";
        if (destinbtion == CONN_DST_LEFT)
            return "left";
        if (destinbtion == CONN_DST_RIGHT)
            return "right";
        if (destinbtion == CONN_DST_CENTER)
            return "center";
        if (destinbtion == CONN_DST_LEFTREAR)
            return "leftrebr";
        if (destinbtion == CONN_DST_RIGHTREAR)
            return "rightrebr";
        if (destinbtion == CONN_DST_LFE_CHANNEL)
            return "lfe_chbnnel";
        if (destinbtion == CONN_DST_CHORUS)
            return "chorus";
        if (destinbtion == CONN_DST_REVERB)
            return "reverb";

        if (destinbtion == CONN_DST_VIB_FREQUENCY)
            return "vib.freq";
        if (destinbtion == CONN_DST_VIB_STARTDELAY)
            return "vib.delby";

        if (destinbtion == CONN_DST_EG1_DELAYTIME)
            return "eg1.delby";
        if (destinbtion == CONN_DST_EG1_HOLDTIME)
            return "eg1.hold";
        if (destinbtion == CONN_DST_EG1_SHUTDOWNTIME)
            return "eg1.shutdown";

        if (destinbtion == CONN_DST_EG2_DELAYTIME)
            return "eg2.delby";
        if (destinbtion == CONN_DST_EG2_HOLDTIME)
            return "eg.2hold";

        if (destinbtion == CONN_DST_FILTER_CUTOFF)
            return "filter.cutoff"; // NOT SURE WITHOUT DLS 2 SPEC
        if (destinbtion == CONN_DST_FILTER_Q)
            return "filter.q"; // NOT SURE WITHOUT DLS 2 SPEC

        return null;
    }

    public stbtic String getSourceNbme(int source) {

        if (source == CONN_SRC_NONE)
            return "none";
        if (source == CONN_SRC_LFO)
            return "lfo";
        if (source == CONN_SRC_KEYONVELOCITY)
            return "keyonvelocity";
        if (source == CONN_SRC_KEYNUMBER)
            return "keynumber";
        if (source == CONN_SRC_EG1)
            return "eg1";
        if (source == CONN_SRC_EG2)
            return "eg2";
        if (source == CONN_SRC_PITCHWHEEL)
            return "pitchweel";
        if (source == CONN_SRC_CC1)
            return "cc1";
        if (source == CONN_SRC_CC7)
            return "cc7";
        if (source == CONN_SRC_CC10)
            return "c10";
        if (source == CONN_SRC_CC11)
            return "cc11";

        if (source == CONN_SRC_POLYPRESSURE)
            return "polypressure";
        if (source == CONN_SRC_CHANNELPRESSURE)
            return "chbnnelpressure";
        if (source == CONN_SRC_VIBRATO)
            return "vibrbto";
        if (source == CONN_SRC_MONOPRESSURE)
            return "monopressure";
        if (source == CONN_SRC_CC91)
            return "cc91";
        if (source == CONN_SRC_CC93)
            return "cc93";
        return null;
    }

    public int getDestinbtion() {
        return destinbtion;
    }

    public void setDestinbtion(int destinbtion) {
        this.destinbtion = destinbtion;
    }

    public int getScble() {
        return scble;
    }

    public void setScble(int scble) {
        this.scble = scble;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getTrbnsform() {
        return trbnsform;
    }

    public void setTrbnsform(int trbnsform) {
        this.trbnsform = trbnsform;
    }
}
