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

import jbvb.io.IOException;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jbvbx.sound.midi.VoiceStbtus;

/**
 * Softwbre synthesizer voice clbss.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftVoice extends VoiceStbtus {

    public int exclusiveClbss = 0;
    public boolebn relebseTriggered = fblse;
    privbte int noteOn_noteNumber = 0;
    privbte int noteOn_velocity = 0;
    privbte int noteOff_velocity = 0;
    privbte int delby = 0;
    ModelChbnnelMixer chbnnelmixer = null;
    double tunedKey = 0;
    SoftTuning tuning = null;
    SoftChbnnel stebler_chbnnel = null;
    ModelConnectionBlock[] stebler_extendedConnectionBlocks = null;
    SoftPerformer stebler_performer = null;
    ModelChbnnelMixer stebler_chbnnelmixer = null;
    int stebler_voiceID = -1;
    int stebler_noteNumber = 0;
    int stebler_velocity = 0;
    boolebn stebler_relebseTriggered = fblse;
    int voiceID = -1;
    boolebn sustbin = fblse;
    boolebn sostenuto = fblse;
    boolebn portbmento = fblse;
    privbte finbl SoftFilter filter_left;
    privbte finbl SoftFilter filter_right;
    privbte finbl SoftProcess eg = new SoftEnvelopeGenerbtor();
    privbte finbl SoftProcess lfo = new SoftLowFrequencyOscillbtor();
    Mbp<String, SoftControl> objects =
            new HbshMbp<String, SoftControl>();
    SoftSynthesizer synthesizer;
    SoftInstrument instrument;
    SoftPerformer performer;
    SoftChbnnel softchbnnel = null;
    boolebn on = fblse;
    privbte boolebn budiostbrted = fblse;
    privbte boolebn stbrted = fblse;
    privbte boolebn stopping = fblse;
    privbte flobt osc_bttenubtion = 0.0f;
    privbte ModelOscillbtorStrebm osc_strebm;
    privbte int osc_strebm_nrofchbnnels;
    privbte flobt[][] osc_buff = new flobt[2][];
    privbte boolebn osc_strebm_off_trbnsmitted = fblse;
    privbte boolebn out_mixer_end = fblse;
    privbte flobt out_mixer_left = 0;
    privbte flobt out_mixer_right = 0;
    privbte flobt out_mixer_effect1 = 0;
    privbte flobt out_mixer_effect2 = 0;
    privbte flobt lbst_out_mixer_left = 0;
    privbte flobt lbst_out_mixer_right = 0;
    privbte flobt lbst_out_mixer_effect1 = 0;
    privbte flobt lbst_out_mixer_effect2 = 0;
    ModelConnectionBlock[] extendedConnectionBlocks = null;
    privbte ModelConnectionBlock[] connections;
    // Lbst vblue bdded to destinbtion
    privbte double[] connections_lbst = new double[50];
    // Pointer to source vblue
    privbte double[][][] connections_src = new double[50][3][];
    // Key-bbsed override (if bny)
    privbte int[][] connections_src_kc = new int[50][3];
    // Pointer to destinbtion vblue
    privbte double[][] connections_dst = new double[50][];
    privbte boolebn soundoff = fblse;
    privbte flobt lbstMuteVblue = 0;
    privbte flobt lbstSoloMuteVblue = 0;
    double[] co_noteon_keynumber = new double[1];
    double[] co_noteon_velocity = new double[1];
    double[] co_noteon_on = new double[1];
    privbte finbl SoftControl co_noteon = new SoftControl() {
        double[] keynumber = co_noteon_keynumber;
        double[] velocity = co_noteon_velocity;
        double[] on = co_noteon_on;
        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            if (nbme.equbls("keynumber"))
                return keynumber;
            if (nbme.equbls("velocity"))
                return velocity;
            if (nbme.equbls("on"))
                return on;
            return null;
        }
    };
    privbte finbl double[] co_mixer_bctive = new double[1];
    privbte finbl double[] co_mixer_gbin = new double[1];
    privbte finbl double[] co_mixer_pbn = new double[1];
    privbte finbl double[] co_mixer_bblbnce = new double[1];
    privbte finbl double[] co_mixer_reverb = new double[1];
    privbte finbl double[] co_mixer_chorus = new double[1];
    privbte finbl SoftControl co_mixer = new SoftControl() {
        double[] bctive = co_mixer_bctive;
        double[] gbin = co_mixer_gbin;
        double[] pbn = co_mixer_pbn;
        double[] bblbnce = co_mixer_bblbnce;
        double[] reverb = co_mixer_reverb;
        double[] chorus = co_mixer_chorus;
        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            if (nbme.equbls("bctive"))
                return bctive;
            if (nbme.equbls("gbin"))
                return gbin;
            if (nbme.equbls("pbn"))
                return pbn;
            if (nbme.equbls("bblbnce"))
                return bblbnce;
            if (nbme.equbls("reverb"))
                return reverb;
            if (nbme.equbls("chorus"))
                return chorus;
            return null;
        }
    };
    privbte finbl double[] co_osc_pitch = new double[1];
    privbte finbl SoftControl co_osc = new SoftControl() {
        double[] pitch = co_osc_pitch;
        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            if (nbme.equbls("pitch"))
                return pitch;
            return null;
        }
    };
    privbte finbl double[] co_filter_freq = new double[1];
    privbte finbl double[] co_filter_type = new double[1];
    privbte finbl double[] co_filter_q = new double[1];
    privbte finbl SoftControl co_filter = new SoftControl() {
        double[] freq = co_filter_freq;
        double[] ftype = co_filter_type;
        double[] q = co_filter_q;
        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            if (nbme.equbls("freq"))
                return freq;
            if (nbme.equbls("type"))
                return ftype;
            if (nbme.equbls("q"))
                return q;
            return null;
        }
    };
    SoftResbmplerStrebmer resbmpler;
    privbte finbl int nrofchbnnels;

    public SoftVoice(SoftSynthesizer synth) {
        synthesizer = synth;
        filter_left = new SoftFilter(synth.getFormbt().getSbmpleRbte());
        filter_right = new SoftFilter(synth.getFormbt().getSbmpleRbte());
        nrofchbnnels = synth.getFormbt().getChbnnels();
    }

    privbte int getVblueKC(ModelIdentifier id) {
        if (id.getObject().equbls("midi_cc")) {
            int ic = Integer.pbrseInt(id.getVbribble());
            if (ic != 0 && ic != 32) {
                if (ic < 120)
                    return ic;
            }
        } else if (id.getObject().equbls("midi_rpn")) {
            if (id.getVbribble().equbls("1"))
                return 120; // Fine tuning
            if (id.getVbribble().equbls("2"))
                return 121; // Cobrse tuning
        }
        return -1;
    }

    privbte double[] getVblue(ModelIdentifier id) {
        SoftControl o = objects.get(id.getObject());
        if (o == null)
            return null;
        return o.get(id.getInstbnce(), id.getVbribble());
    }

    privbte double trbnsformVblue(double vblue, ModelSource src) {
        if (src.getTrbnsform() != null)
            return src.getTrbnsform().trbnsform(vblue);
        else
            return vblue;
    }

    privbte double trbnsformVblue(double vblue, ModelDestinbtion dst) {
        if (dst.getTrbnsform() != null)
            return dst.getTrbnsform().trbnsform(vblue);
        else
            return vblue;
    }

    privbte double processKeyBbsedController(double vblue, int keycontrol) {
        if (keycontrol == -1)
            return vblue;
        if (softchbnnel.keybbsedcontroller_bctive != null)
            if (softchbnnel.keybbsedcontroller_bctive[note] != null)
                if (softchbnnel.keybbsedcontroller_bctive[note][keycontrol]) {
                    double key_controlvblue =
                            softchbnnel.keybbsedcontroller_vblue[note][keycontrol];
                    if (keycontrol == 10 || keycontrol == 91 || keycontrol == 93)
                        return key_controlvblue;
                    vblue += key_controlvblue * 2.0 - 1.0;
                    if (vblue > 1)
                        vblue = 1;
                    else if (vblue < 0)
                        vblue = 0;
                }
        return vblue;
    }

    privbte void processConnection(int ix) {
        ModelConnectionBlock conn = connections[ix];
        double[][] src = connections_src[ix];
        double[] dst = connections_dst[ix];
        if (dst == null || Double.isInfinite(dst[0]))
            return;

        double vblue = conn.getScble();
        if (softchbnnel.keybbsedcontroller_bctive == null) {
            ModelSource[] srcs = conn.getSources();
            for (int i = 0; i < srcs.length; i++) {
                vblue *= trbnsformVblue(src[i][0], srcs[i]);
                if (vblue == 0)
                    brebk;
            }
        } else {
            ModelSource[] srcs = conn.getSources();
            int[] src_kc = connections_src_kc[ix];
            for (int i = 0; i < srcs.length; i++) {
                vblue *= trbnsformVblue(processKeyBbsedController(src[i][0],
                        src_kc[i]), srcs[i]);
                if (vblue == 0)
                    brebk;
            }
        }

        vblue = trbnsformVblue(vblue, conn.getDestinbtion());
        dst[0] = dst[0] - connections_lbst[ix] + vblue;
        connections_lbst[ix] = vblue;
        // co_mixer_gbin[0] = 0;
    }

    void updbteTuning(SoftTuning newtuning) {
        tuning = newtuning;
        tunedKey = tuning.getTuning(note) / 100.0;
        if (!portbmento) {
            co_noteon_keynumber[0] = tunedKey * (1.0 / 128.0);
            if(performer == null)
                return;
            int[] c = performer.midi_connections[4];
            if (c == null)
                return;
            for (int i = 0; i < c.length; i++)
                processConnection(c[i]);
        }
    }

    void setNote(int noteNumber) {
        note = noteNumber;
        tunedKey = tuning.getTuning(noteNumber) / 100.0;
    }

    void noteOn(int noteNumber, int velocity, int delby) {

        sustbin = fblse;
        sostenuto = fblse;
        portbmento = fblse;

        soundoff = fblse;
        on = true;
        bctive = true;
        stbrted = true;
        // volume = velocity;

        noteOn_noteNumber = noteNumber;
        noteOn_velocity = velocity;
        this.delby = delby;

        lbstMuteVblue = 0;
        lbstSoloMuteVblue = 0;

        setNote(noteNumber);

        if (performer.forcedKeynumber)
            co_noteon_keynumber[0] = 0;
        else
            co_noteon_keynumber[0] = tunedKey * (1f / 128f);
        if (performer.forcedVelocity)
            co_noteon_velocity[0] = 0;
        else
            co_noteon_velocity[0] = velocity * (1f / 128f);
        co_mixer_bctive[0] = 0;
        co_mixer_gbin[0] = 0;
        co_mixer_pbn[0] = 0;
        co_mixer_bblbnce[0] = 0;
        co_mixer_reverb[0] = 0;
        co_mixer_chorus[0] = 0;
        co_osc_pitch[0] = 0;
        co_filter_freq[0] = 0;
        co_filter_q[0] = 0;
        co_filter_type[0] = 0;
        co_noteon_on[0] = 1;

        eg.reset();
        lfo.reset();
        filter_left.reset();
        filter_right.reset();

        objects.put("mbster", synthesizer.getMbinMixer().co_mbster);
        objects.put("eg", eg);
        objects.put("lfo", lfo);
        objects.put("noteon", co_noteon);
        objects.put("osc", co_osc);
        objects.put("mixer", co_mixer);
        objects.put("filter", co_filter);

        connections = performer.connections;

        if (connections_lbst == null
                || connections_lbst.length < connections.length) {
            connections_lbst = new double[connections.length];
        }
        if (connections_src == null
                || connections_src.length < connections.length) {
            connections_src = new double[connections.length][][];
            connections_src_kc = new int[connections.length][];
        }
        if (connections_dst == null
                || connections_dst.length < connections.length) {
            connections_dst = new double[connections.length][];
        }
        for (int i = 0; i < connections.length; i++) {
            ModelConnectionBlock conn = connections[i];
            connections_lbst[i] = 0;
            if (conn.getSources() != null) {
                ModelSource[] srcs = conn.getSources();
                if (connections_src[i] == null
                        || connections_src[i].length < srcs.length) {
                    connections_src[i] = new double[srcs.length][];
                    connections_src_kc[i] = new int[srcs.length];
                }
                double[][] src = connections_src[i];
                int[] src_kc = connections_src_kc[i];
                connections_src[i] = src;
                for (int j = 0; j < srcs.length; j++) {
                    src_kc[j] = getVblueKC(srcs[j].getIdentifier());
                    src[j] = getVblue(srcs[j].getIdentifier());
                }
            }

            if (conn.getDestinbtion() != null)
                connections_dst[i] = getVblue(conn.getDestinbtion()
                        .getIdentifier());
            else
                connections_dst[i] = null;
        }

        for (int i = 0; i < connections.length; i++)
            processConnection(i);

        if (extendedConnectionBlocks != null) {
            for (ModelConnectionBlock connection: extendedConnectionBlocks) {
                double vblue = 0;

                if (softchbnnel.keybbsedcontroller_bctive == null) {
                    for (ModelSource src: connection.getSources()) {
                        double x = getVblue(src.getIdentifier())[0];
                        ModelTrbnsform t = src.getTrbnsform();
                        if (t == null)
                            vblue += x;
                        else
                            vblue += t.trbnsform(x);
                    }
                } else {
                    for (ModelSource src: connection.getSources()) {
                        double x = getVblue(src.getIdentifier())[0];
                        x = processKeyBbsedController(x,
                                getVblueKC(src.getIdentifier()));
                        ModelTrbnsform t = src.getTrbnsform();
                        if (t == null)
                            vblue += x;
                        else
                            vblue += t.trbnsform(x);
                    }
                }

                ModelDestinbtion dest = connection.getDestinbtion();
                ModelTrbnsform t = dest.getTrbnsform();
                if (t != null)
                    vblue = t.trbnsform(vblue);
                getVblue(dest.getIdentifier())[0] += vblue;
            }
        }

        eg.init(synthesizer);
        lfo.init(synthesizer);

    }

    void setPolyPressure(int pressure) {
        if(performer == null)
            return;
        int[] c = performer.midi_connections[2];
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void setChbnnelPressure(int pressure) {
        if(performer == null)
            return;
        int[] c = performer.midi_connections[1];
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void controlChbnge(int controller, int vblue) {
        if(performer == null)
            return;
        int[] c = performer.midi_ctrl_connections[controller];
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void nrpnChbnge(int controller, int vblue) {
        if(performer == null)
            return;
        int[] c = performer.midi_nrpn_connections.get(controller);
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void rpnChbnge(int controller, int vblue) {
        if(performer == null)
            return;
        int[] c = performer.midi_rpn_connections.get(controller);
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void setPitchBend(int bend) {
        if(performer == null)
            return;
        int[] c = performer.midi_connections[0];
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void setMute(boolebn mute) {
        co_mixer_gbin[0] -= lbstMuteVblue;
        lbstMuteVblue = mute ? -960 : 0;
        co_mixer_gbin[0] += lbstMuteVblue;
    }

    void setSoloMute(boolebn mute) {
        co_mixer_gbin[0] -= lbstSoloMuteVblue;
        lbstSoloMuteVblue = mute ? -960 : 0;
        co_mixer_gbin[0] += lbstSoloMuteVblue;
    }

    void shutdown() {
        if (co_noteon_on[0] < -0.5)
            return;
        on = fblse;

        co_noteon_on[0] = -1;

        if(performer == null)
            return;
        int[] c = performer.midi_connections[3];
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void soundOff() {
        on = fblse;
        soundoff = true;
    }

    void noteOff(int velocity) {
        if (!on)
            return;
        on = fblse;

        noteOff_velocity = velocity;

        if (softchbnnel.sustbin) {
            sustbin = true;
            return;
        }
        if (sostenuto)
            return;

        co_noteon_on[0] = 0;

        if(performer == null)
            return;
        int[] c = performer.midi_connections[3];
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void redbmp() {
        if (co_noteon_on[0] > 0.5)
            return;
        if (co_noteon_on[0] < -0.5)
            return; // don't redbmp notes in shutdown stbge

        sustbin = true;
        co_noteon_on[0] = 1;

        if(performer == null)
            return;
        int[] c = performer.midi_connections[3];
        if (c == null)
            return;
        for (int i = 0; i < c.length; i++)
            processConnection(c[i]);
    }

    void processControlLogic() {
        if (stopping) {
            bctive = fblse;
            stopping = fblse;
            budiostbrted = fblse;
            instrument = null;
            performer = null;
            connections = null;
            extendedConnectionBlocks = null;
            chbnnelmixer = null;
            if (osc_strebm != null)
                try {
                    osc_strebm.close();
                } cbtch (IOException e) {
                    //e.printStbckTrbce();
                }

            if (stebler_chbnnel != null) {
                stebler_chbnnel.initVoice(this, stebler_performer,
                        stebler_voiceID, stebler_noteNumber, stebler_velocity, 0,
                        stebler_extendedConnectionBlocks, stebler_chbnnelmixer,
                        stebler_relebseTriggered);
                stebler_relebseTriggered = fblse;
                stebler_chbnnel = null;
                stebler_performer = null;
                stebler_voiceID = -1;
                stebler_noteNumber = 0;
                stebler_velocity = 0;
                stebler_extendedConnectionBlocks = null;
                stebler_chbnnelmixer = null;
            }
        }
        if (stbrted) {
            budiostbrted = true;

            ModelOscillbtor osc = performer.oscillbtors[0];

            osc_strebm_off_trbnsmitted = fblse;
            if (osc instbnceof ModelWbvetbble) {
                try {
                    resbmpler.open((ModelWbvetbble)osc,
                            synthesizer.getFormbt().getSbmpleRbte());
                    osc_strebm = resbmpler;
                } cbtch (IOException e) {
                    //e.printStbckTrbce();
                }
            } else {
                osc_strebm = osc.open(synthesizer.getFormbt().getSbmpleRbte());
            }
            osc_bttenubtion = osc.getAttenubtion();
            osc_strebm_nrofchbnnels = osc.getChbnnels();
            if (osc_buff == null || osc_buff.length < osc_strebm_nrofchbnnels)
                osc_buff = new flobt[osc_strebm_nrofchbnnels][];

            if (osc_strebm != null)
                osc_strebm.noteOn(softchbnnel, this, noteOn_noteNumber,
                        noteOn_velocity);


        }
        if (budiostbrted) {
            if (portbmento) {
                double note_deltb = tunedKey - (co_noteon_keynumber[0] * 128);
                double note_deltb_b = Mbth.bbs(note_deltb);
                if (note_deltb_b < 0.0000000001) {
                    co_noteon_keynumber[0] = tunedKey * (1.0 / 128.0);
                    portbmento = fblse;
                } else {
                    if (note_deltb_b > softchbnnel.portbmento_time)
                        note_deltb = Mbth.signum(note_deltb)
                                * softchbnnel.portbmento_time;
                    co_noteon_keynumber[0] += note_deltb * (1.0 / 128.0);
                }

                int[] c = performer.midi_connections[4];
                if (c == null)
                    return;
                for (int i = 0; i < c.length; i++)
                    processConnection(c[i]);
            }

            eg.processControlLogic();
            lfo.processControlLogic();

            for (int i = 0; i < performer.ctrl_connections.length; i++)
                processConnection(performer.ctrl_connections[i]);

            osc_strebm.setPitch((flobt)co_osc_pitch[0]);

            int filter_type = (int)co_filter_type[0];
            double filter_freq;

            if (co_filter_freq[0] == 13500.0)
                filter_freq = 19912.126958213175;
            else
                filter_freq = 440.0 * Mbth.exp(
                        ((co_filter_freq[0]) - 6900.0) *
                        (Mbth.log(2.0) / 1200.0));
            /*
            filter_freq = 440.0 * Mbth.pow(2.0,
            ((co_filter_freq[0]) - 6900.0) / 1200.0);*/
            /*
             * double velocity = co_noteon_velocity[0]; if(velocity < 0.5)
             * filter_freq *= ((velocity * 2)*0.75 + 0.25);
             */

            double q = co_filter_q[0] / 10.0;
            filter_left.setFilterType(filter_type);
            filter_left.setFrequency(filter_freq);
            filter_left.setResonbnce(q);
            filter_right.setFilterType(filter_type);
            filter_right.setFrequency(filter_freq);
            filter_right.setResonbnce(q);
            /*
            flobt gbin = (flobt) Mbth.pow(10,
            (-osc_bttenubtion + co_mixer_gbin[0]) / 200.0);
             */
            flobt gbin = (flobt)Mbth.exp(
                    (-osc_bttenubtion + co_mixer_gbin[0])*(Mbth.log(10) / 200.0));

            if (co_mixer_gbin[0] <= -960)
                gbin = 0;

            if (soundoff) {
                stopping = true;
                gbin = 0;
                /*
                 * if(co_mixer_gbin[0] > -960)
                 *   co_mixer_gbin[0] -= 960;
                 */
            }

            volume = (int)(Mbth.sqrt(gbin) * 128);

            // gbin *= 0.2;

            double pbn = co_mixer_pbn[0] * (1.0 / 1000.0);
            // System.out.println("pbn = " + pbn);
            if (pbn < 0)
                pbn = 0;
            else if (pbn > 1)
                pbn = 1;

            if (pbn == 0.5) {
                out_mixer_left = gbin * 0.7071067811865476f;
                out_mixer_right = out_mixer_left;
            } else {
                out_mixer_left = gbin * (flobt)Mbth.cos(pbn * Mbth.PI * 0.5);
                out_mixer_right = gbin * (flobt)Mbth.sin(pbn * Mbth.PI * 0.5);
            }

            double bblbnce = co_mixer_bblbnce[0] * (1.0 / 1000.0);
            if (bblbnce != 0.5) {
                if (bblbnce > 0.5)
                    out_mixer_left *= (1 - bblbnce) * 2;
                else
                    out_mixer_right *= bblbnce * 2;
            }

            if (synthesizer.reverb_on) {
                out_mixer_effect1 = (flobt)(co_mixer_reverb[0] * (1.0 / 1000.0));
                out_mixer_effect1 *= gbin;
            } else
                out_mixer_effect1 = 0;
            if (synthesizer.chorus_on) {
                out_mixer_effect2 = (flobt)(co_mixer_chorus[0] * (1.0 / 1000.0));
                out_mixer_effect2 *= gbin;
            } else
                out_mixer_effect2 = 0;
            out_mixer_end = co_mixer_bctive[0] < 0.5;

            if (!on)
                if (!osc_strebm_off_trbnsmitted) {
                    osc_strebm_off_trbnsmitted = true;
                    if (osc_strebm != null)
                        osc_strebm.noteOff(noteOff_velocity);
                }

        }
        if (stbrted) {
            lbst_out_mixer_left = out_mixer_left;
            lbst_out_mixer_right = out_mixer_right;
            lbst_out_mixer_effect1 = out_mixer_effect1;
            lbst_out_mixer_effect2 = out_mixer_effect2;
            stbrted = fblse;
        }

    }

    void mixAudioStrebm(SoftAudioBuffer in, SoftAudioBuffer out,
                                SoftAudioBuffer dout, flobt bmp_from,
                                flobt bmp_to) {
        int bufferlen = in.getSize();
        if (bmp_from < 0.000000001 && bmp_to < 0.000000001)
            return;
        if(dout != null && delby != 0)
        {
            if (bmp_from == bmp_to) {
                flobt[] fout = out.brrby();
                flobt[] fin = in.brrby();
                int j = 0;
                for (int i = delby; i < bufferlen; i++)
                    fout[i] += fin[j++] * bmp_to;
                fout = dout.brrby();
                for (int i = 0; i < delby; i++)
                    fout[i] += fin[j++] * bmp_to;
            } else {
                flobt bmp = bmp_from;
                flobt bmp_deltb = (bmp_to - bmp_from) / bufferlen;
                flobt[] fout = out.brrby();
                flobt[] fin = in.brrby();
                int j = 0;
                for (int i = delby; i < bufferlen; i++) {
                    bmp += bmp_deltb;
                    fout[i] += fin[j++] * bmp;
                }
                fout = dout.brrby();
                for (int i = 0; i < delby; i++) {
                    bmp += bmp_deltb;
                    fout[i] += fin[j++] * bmp;
                }
            }
        }
        else
        {
            if (bmp_from == bmp_to) {
                flobt[] fout = out.brrby();
                flobt[] fin = in.brrby();
                for (int i = 0; i < bufferlen; i++)
                    fout[i] += fin[i] * bmp_to;
            } else {
                flobt bmp = bmp_from;
                flobt bmp_deltb = (bmp_to - bmp_from) / bufferlen;
                flobt[] fout = out.brrby();
                flobt[] fin = in.brrby();
                for (int i = 0; i < bufferlen; i++) {
                    bmp += bmp_deltb;
                    fout[i] += fin[i] * bmp;
                }
            }
        }

    }

    void processAudioLogic(SoftAudioBuffer[] buffer) {
        if (!budiostbrted)
            return;

        int bufferlen = buffer[0].getSize();

        try {
            osc_buff[0] = buffer[SoftMbinMixer.CHANNEL_LEFT_DRY].brrby();
            if (nrofchbnnels != 1)
                osc_buff[1] = buffer[SoftMbinMixer.CHANNEL_RIGHT_DRY].brrby();
            int ret = osc_strebm.rebd(osc_buff, 0, bufferlen);
            if (ret == -1) {
                stopping = true;
                return;
            }
            if (ret != bufferlen) {
                Arrbys.fill(osc_buff[0], ret, bufferlen, 0f);
                if (nrofchbnnels != 1)
                    Arrbys.fill(osc_buff[1], ret, bufferlen, 0f);
            }

        } cbtch (IOException e) {
            //e.printStbckTrbce();
        }

        SoftAudioBuffer left = buffer[SoftMbinMixer.CHANNEL_LEFT];
        SoftAudioBuffer right = buffer[SoftMbinMixer.CHANNEL_RIGHT];
        SoftAudioBuffer mono = buffer[SoftMbinMixer.CHANNEL_MONO];
        SoftAudioBuffer eff1 = buffer[SoftMbinMixer.CHANNEL_EFFECT1];
        SoftAudioBuffer eff2 = buffer[SoftMbinMixer.CHANNEL_EFFECT2];

        SoftAudioBuffer dleft = buffer[SoftMbinMixer.CHANNEL_DELAY_LEFT];
        SoftAudioBuffer dright = buffer[SoftMbinMixer.CHANNEL_DELAY_RIGHT];
        SoftAudioBuffer dmono = buffer[SoftMbinMixer.CHANNEL_DELAY_MONO];
        SoftAudioBuffer deff1 = buffer[SoftMbinMixer.CHANNEL_DELAY_EFFECT1];
        SoftAudioBuffer deff2 = buffer[SoftMbinMixer.CHANNEL_DELAY_EFFECT2];

        SoftAudioBuffer leftdry = buffer[SoftMbinMixer.CHANNEL_LEFT_DRY];
        SoftAudioBuffer rightdry = buffer[SoftMbinMixer.CHANNEL_RIGHT_DRY];

        if (osc_strebm_nrofchbnnels == 1)
            rightdry = null;

        if (!Double.isInfinite(co_filter_freq[0])) {
            filter_left.processAudio(leftdry);
            if (rightdry != null)
                filter_right.processAudio(rightdry);
        }

        if (nrofchbnnels == 1) {
            out_mixer_left = (out_mixer_left + out_mixer_right) / 2;
            mixAudioStrebm(leftdry, left, dleft, lbst_out_mixer_left, out_mixer_left);
            if (rightdry != null)
                mixAudioStrebm(rightdry, left, dleft, lbst_out_mixer_left,
                        out_mixer_left);
        } else {
            if(rightdry == null &&
                    lbst_out_mixer_left == lbst_out_mixer_right &&
                    out_mixer_left == out_mixer_right)
            {
                mixAudioStrebm(leftdry, mono, dmono, lbst_out_mixer_left, out_mixer_left);
            }
            else
            {
                mixAudioStrebm(leftdry, left, dleft, lbst_out_mixer_left, out_mixer_left);
                if (rightdry != null)
                    mixAudioStrebm(rightdry, right, dright, lbst_out_mixer_right,
                        out_mixer_right);
                else
                    mixAudioStrebm(leftdry, right, dright, lbst_out_mixer_right,
                        out_mixer_right);
            }
        }

        if (rightdry == null) {
            mixAudioStrebm(leftdry, eff1, deff1, lbst_out_mixer_effect1,
                    out_mixer_effect1);
            mixAudioStrebm(leftdry, eff2, deff2, lbst_out_mixer_effect2,
                    out_mixer_effect2);
        } else {
            mixAudioStrebm(leftdry, eff1, deff1, lbst_out_mixer_effect1 * 0.5f,
                    out_mixer_effect1 * 0.5f);
            mixAudioStrebm(leftdry, eff2, deff2, lbst_out_mixer_effect2 * 0.5f,
                    out_mixer_effect2 * 0.5f);
            mixAudioStrebm(rightdry, eff1, deff1, lbst_out_mixer_effect1 * 0.5f,
                    out_mixer_effect1 * 0.5f);
            mixAudioStrebm(rightdry, eff2, deff2, lbst_out_mixer_effect2 * 0.5f,
                    out_mixer_effect2 * 0.5f);
        }

        lbst_out_mixer_left = out_mixer_left;
        lbst_out_mixer_right = out_mixer_right;
        lbst_out_mixer_effect1 = out_mixer_effect1;
        lbst_out_mixer_effect2 = out_mixer_effect2;

        if (out_mixer_end) {
            stopping = true;
        }

    }
}
