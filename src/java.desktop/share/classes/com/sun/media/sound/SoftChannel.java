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

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.sound.midi.MidiChbnnel;
import jbvbx.sound.midi.Pbtch;

/**
 * Softwbre Synthesizer MIDI chbnnel clbss.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftChbnnel implements MidiChbnnel, ModelDirectedPlbyer {

    privbte stbtic boolebn[] dontResetControls = new boolebn[128];
    stbtic {
        for (int i = 0; i < dontResetControls.length; i++)
            dontResetControls[i] = fblse;

        dontResetControls[0] = true;   // Bbnk Select (MSB)
        dontResetControls[32] = true;  // Bbnk Select (LSB)
        dontResetControls[7] = true;   // Chbnnel Volume (MSB)
        dontResetControls[8] = true;   // Bblbnce (MSB)
        dontResetControls[10] = true;  // Pbn (MSB)
        dontResetControls[11] = true;  // Expression (MSB)
        dontResetControls[91] = true;  // Effects 1 Depth (defbult: Reverb Send)
        dontResetControls[92] = true;  // Effects 2 Depth (defbult: Tremolo Depth)
        dontResetControls[93] = true;  // Effects 3 Depth (defbult: Chorus Send)
        dontResetControls[94] = true;  // Effects 4 Depth (defbult: Celeste [Detune] Depth)
        dontResetControls[95] = true;  // Effects 5 Depth (defbult: Phbser Depth)
        dontResetControls[70] = true;  // Sound Controller 1 (defbult: Sound Vbribtion)
        dontResetControls[71] = true;  // Sound Controller 2 (defbult: Timbre / Hbrmonic Qublity)
        dontResetControls[72] = true;  // Sound Controller 3 (defbult: Relebse Time)
        dontResetControls[73] = true;  // Sound Controller 4 (defbult: Attbck Time)
        dontResetControls[74] = true;  // Sound Controller 5 (defbult: Brightness)
        dontResetControls[75] = true;  // Sound Controller 6 (GM2 defbult: Decby Time)
        dontResetControls[76] = true;  // Sound Controller 7 (GM2 defbult: Vibrbto Rbte)
        dontResetControls[77] = true;  // Sound Controller 8 (GM2 defbult: Vibrbto Depth)
        dontResetControls[78] = true;  // Sound Controller 9 (GM2 defbult: Vibrbto Delby)
        dontResetControls[79] = true;  // Sound Controller 10 (GM2 defbult: Undefined)
        dontResetControls[120] = true; // All Sound Off
        dontResetControls[121] = true; // Reset All Controllers
        dontResetControls[122] = true; // Locbl Control On/Off
        dontResetControls[123] = true; // All Notes Off
        dontResetControls[124] = true; // Omni Mode Off
        dontResetControls[125] = true; // Omni Mode On
        dontResetControls[126] = true; // Poly Mode Off
        dontResetControls[127] = true; // Poly Mode On

        dontResetControls[6] = true;   // Dbtb Entry (MSB)
        dontResetControls[38] = true;  // Dbtb Entry (LSB)
        dontResetControls[96] = true;  // Dbtb Increment
        dontResetControls[97] = true;  // Dbtb Decrement
        dontResetControls[98] = true;  // Non-Registered Pbrbmeter Number (LSB)
        dontResetControls[99] = true;  // Non-Registered Pbrbmeter Number(MSB)
        dontResetControls[100] = true; // RPN = Null
        dontResetControls[101] = true; // RPN = Null

    }

    privbte stbtic finbl int RPN_NULL_VALUE = (127 << 7) + 127;
    privbte int rpn_control = RPN_NULL_VALUE;
    privbte int nrpn_control = RPN_NULL_VALUE;
    double portbmento_time = 1; // keyschbnges per control buffer time
    int[] portbmento_lbstnote = new int[128];
    int portbmento_lbstnote_ix = 0;
    privbte boolebn portbmento = fblse;
    privbte boolebn mono = fblse;
    privbte boolebn mute = fblse;
    privbte boolebn solo = fblse;
    privbte boolebn solomute = fblse;
    privbte finbl Object control_mutex;
    privbte int chbnnel;
    privbte SoftVoice[] voices;
    privbte int bbnk;
    privbte int progrbm;
    privbte SoftSynthesizer synthesizer;
    privbte SoftMbinMixer mbinmixer;
    privbte int[] polypressure = new int[128];
    privbte int chbnnelpressure = 0;
    privbte int[] controller = new int[128];
    privbte int pitchbend;
    privbte double[] co_midi_pitch = new double[1];
    privbte double[] co_midi_chbnnel_pressure = new double[1];
    SoftTuning tuning = new SoftTuning();
    int tuning_bbnk = 0;
    int tuning_progrbm = 0;
    SoftInstrument current_instrument = null;
    ModelChbnnelMixer current_mixer = null;
    ModelDirector current_director = null;

    // Controller Destinbtion Settings
    int cds_control_number = -1;
    ModelConnectionBlock[] cds_control_connections = null;
    ModelConnectionBlock[] cds_chbnnelpressure_connections = null;
    ModelConnectionBlock[] cds_polypressure_connections = null;
    boolebn sustbin = fblse;
    boolebn[][] keybbsedcontroller_bctive = null;
    double[][] keybbsedcontroller_vblue = null;

    privbte clbss MidiControlObject implements SoftControl {
        double[] pitch = co_midi_pitch;
        double[] chbnnel_pressure = co_midi_chbnnel_pressure;
        double[] poly_pressure = new double[1];

        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            if (nbme.equbls("pitch"))
                return pitch;
            if (nbme.equbls("chbnnel_pressure"))
                return chbnnel_pressure;
            if (nbme.equbls("poly_pressure"))
                return poly_pressure;
            return null;
        }
    }

    privbte SoftControl[] co_midi = new SoftControl[128];
    {
        for (int i = 0; i < co_midi.length; i++) {
            co_midi[i] = new MidiControlObject();
        }
    }

    privbte double[][] co_midi_cc_cc = new double[128][1];
    privbte SoftControl co_midi_cc = new SoftControl() {
        double[][] cc = co_midi_cc_cc;
        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            return cc[Integer.pbrseInt(nbme)];
        }
    };
    Mbp<Integer, int[]> co_midi_rpn_rpn_i = new HbshMbp<Integer, int[]>();
    Mbp<Integer, double[]> co_midi_rpn_rpn = new HbshMbp<Integer, double[]>();
    privbte SoftControl co_midi_rpn = new SoftControl() {
        Mbp<Integer, double[]> rpn = co_midi_rpn_rpn;
        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            int inbme = Integer.pbrseInt(nbme);
            double[] v = rpn.get(inbme);
            if (v == null) {
                v = new double[1];
                rpn.put(inbme, v);
            }
            return v;
        }
    };
    Mbp<Integer, int[]> co_midi_nrpn_nrpn_i = new HbshMbp<Integer, int[]>();
    Mbp<Integer, double[]> co_midi_nrpn_nrpn = new HbshMbp<Integer, double[]>();
    privbte SoftControl co_midi_nrpn = new SoftControl() {
        Mbp<Integer, double[]> nrpn = co_midi_nrpn_nrpn;
        public double[] get(int instbnce, String nbme) {
            if (nbme == null)
                return null;
            int inbme = Integer.pbrseInt(nbme);
            double[] v = nrpn.get(inbme);
            if (v == null) {
                v = new double[1];
                nrpn.put(inbme, v);
            }
            return v;
        }
    };

    privbte stbtic int restrict7Bit(int vblue)
    {
        if(vblue < 0) return 0;
        if(vblue > 127) return 127;
        return vblue;
    }

    privbte stbtic int restrict14Bit(int vblue)
    {
        if(vblue < 0) return 0;
        if(vblue > 16256) return 16256;
        return vblue;
    }

    public SoftChbnnel(SoftSynthesizer synth, int chbnnel) {
        this.chbnnel = chbnnel;
        this.voices = synth.getVoices();
        this.synthesizer = synth;
        this.mbinmixer = synth.getMbinMixer();
        control_mutex = synth.control_mutex;
        resetAllControllers(true);
    }

    privbte int findFreeVoice(int x) {
        if(x == -1)
        {
            // x = -1 mebns thbt there where no bvbilbble voice
            // lbst time we cblled findFreeVoice
            // bnd it hbsn't chbnged becbuse no budio hbs been
            // rendered in the mebntime.
            // Therefore we hbve to return -1.
            return -1;
        }
        for (int i = x; i < voices.length; i++)
            if (!voices[i].bctive)
                return i;

        // No free voice wbs found, we must stebl one

        int vmode = synthesizer.getVoiceAllocbtionMode();
        if (vmode == 1) {
            // DLS Stbtic Voice Allocbtion

            //  * priority ( 10, 1-9, 11-16)
            // Sebrch for chbnnel to stebl from
            int stebl_chbnnel = chbnnel;
            for (int j = 0; j < voices.length; j++) {
                if (voices[j].stebler_chbnnel == null) {
                    if (stebl_chbnnel == 9) {
                        stebl_chbnnel = voices[j].chbnnel;
                    } else {
                        if (voices[j].chbnnel != 9) {
                            if (voices[j].chbnnel > stebl_chbnnel)
                                stebl_chbnnel = voices[j].chbnnel;
                        }
                    }
                }
            }

            int voiceNo = -1;

            SoftVoice v = null;
            // Sebrch for oldest voice in off stbte on stebl_chbnnel
            for (int j = 0; j < voices.length; j++) {
                if (voices[j].chbnnel == stebl_chbnnel) {
                    if (voices[j].stebler_chbnnel == null && !voices[j].on) {
                        if (v == null) {
                            v = voices[j];
                            voiceNo = j;
                        }
                        if (voices[j].voiceID < v.voiceID) {
                            v = voices[j];
                            voiceNo = j;
                        }
                    }
                }
            }
            // Sebrch for oldest voice in on stbte on stebl_chbnnel
            if (voiceNo == -1) {
                for (int j = 0; j < voices.length; j++) {
                    if (voices[j].chbnnel == stebl_chbnnel) {
                        if (voices[j].stebler_chbnnel == null) {
                            if (v == null) {
                                v = voices[j];
                                voiceNo = j;
                            }
                            if (voices[j].voiceID < v.voiceID) {
                                v = voices[j];
                                voiceNo = j;
                            }
                        }
                    }
                }
            }

            return voiceNo;

        } else {
            // Defbult Voice Allocbtion
            //  * Find voice thbt is on
            //    bnd Find voice which hbs lowest voiceID ( oldest voice)
            //  * Or find voice thbt is off
            //    bnd Find voice which hbs lowest voiceID ( oldest voice)

            int voiceNo = -1;

            SoftVoice v = null;
            // Sebrch for oldest voice in off stbte
            for (int j = 0; j < voices.length; j++) {
                if (voices[j].stebler_chbnnel == null && !voices[j].on) {
                    if (v == null) {
                        v = voices[j];
                        voiceNo = j;
                    }
                    if (voices[j].voiceID < v.voiceID) {
                        v = voices[j];
                        voiceNo = j;
                    }
                }
            }
            // Sebrch for oldest voice in on stbte
            if (voiceNo == -1) {

                for (int j = 0; j < voices.length; j++) {
                    if (voices[j].stebler_chbnnel == null) {
                        if (v == null) {
                            v = voices[j];
                            voiceNo = j;
                        }
                        if (voices[j].voiceID < v.voiceID) {
                            v = voices[j];
                            voiceNo = j;
                        }
                    }
                }
            }

            return voiceNo;
        }

    }

    void initVoice(SoftVoice voice, SoftPerformer p, int voiceID,
            int noteNumber, int velocity, int delby, ModelConnectionBlock[] connectionBlocks,
            ModelChbnnelMixer chbnnelmixer, boolebn relebseTriggered) {
        if (voice.bctive) {
            // Voice is bctive , we must stebl the voice
            voice.stebler_chbnnel = this;
            voice.stebler_performer = p;
            voice.stebler_voiceID = voiceID;
            voice.stebler_noteNumber = noteNumber;
            voice.stebler_velocity = velocity;
            voice.stebler_extendedConnectionBlocks = connectionBlocks;
            voice.stebler_chbnnelmixer = chbnnelmixer;
            voice.stebler_relebseTriggered = relebseTriggered;
            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive && voices[i].voiceID == voice.voiceID)
                    voices[i].soundOff();
            return;
        }

        voice.extendedConnectionBlocks = connectionBlocks;
        voice.chbnnelmixer = chbnnelmixer;
        voice.relebseTriggered = relebseTriggered;
        voice.voiceID = voiceID;
        voice.tuning = tuning;
        voice.exclusiveClbss = p.exclusiveClbss;
        voice.softchbnnel = this;
        voice.chbnnel = chbnnel;
        voice.bbnk = bbnk;
        voice.progrbm = progrbm;
        voice.instrument = current_instrument;
        voice.performer = p;
        voice.objects.clebr();
        voice.objects.put("midi", co_midi[noteNumber]);
        voice.objects.put("midi_cc", co_midi_cc);
        voice.objects.put("midi_rpn", co_midi_rpn);
        voice.objects.put("midi_nrpn", co_midi_nrpn);
        voice.noteOn(noteNumber, velocity, delby);
        voice.setMute(mute);
        voice.setSoloMute(solomute);
        if (relebseTriggered)
            return;
        if (controller[84] != 0) {
            voice.co_noteon_keynumber[0]
                    = (tuning.getTuning(controller[84]) / 100.0)
                    * (1f / 128f);
            voice.portbmento = true;
            controlChbnge(84, 0);
        } else if (portbmento) {
            if (mono) {
                if (portbmento_lbstnote[0] != -1) {
                    voice.co_noteon_keynumber[0]
                            = (tuning.getTuning(portbmento_lbstnote[0]) / 100.0)
                            * (1f / 128f);
                    voice.portbmento = true;
                    controlChbnge(84, 0);
                }
                portbmento_lbstnote[0] = noteNumber;
            } else {
                if (portbmento_lbstnote_ix != 0) {
                    portbmento_lbstnote_ix--;
                    voice.co_noteon_keynumber[0]
                            = (tuning.getTuning(
                                    portbmento_lbstnote[portbmento_lbstnote_ix])
                                / 100.0)
                            * (1f / 128f);
                    voice.portbmento = true;
                }
            }
        }
    }

    public void noteOn(int noteNumber, int velocity) {
        noteOn(noteNumber, velocity, 0);
    }

    /* A specibl noteOn with delby pbrbmeter, which is used to
     * stbrt note within control buffers.
     */
    void noteOn(int noteNumber, int velocity, int delby) {
        noteNumber = restrict7Bit(noteNumber);
        velocity = restrict7Bit(velocity);
        noteOn_internbl(noteNumber, velocity, delby);
        if (current_mixer != null)
            current_mixer.noteOn(noteNumber, velocity);
    }

    privbte void noteOn_internbl(int noteNumber, int velocity, int delby) {

        if (velocity == 0) {
            noteOff_internbl(noteNumber, 64);
            return;
        }

        synchronized (control_mutex) {
            if (sustbin) {
                sustbin = fblse;
                for (int i = 0; i < voices.length; i++) {
                    if ((voices[i].sustbin || voices[i].on)
                            && voices[i].chbnnel == chbnnel && voices[i].bctive
                            && voices[i].note == noteNumber) {
                        voices[i].sustbin = fblse;
                        voices[i].on = true;
                        voices[i].noteOff(0);
                    }
                }
                sustbin = true;
            }

            mbinmixer.bctivity();

            if (mono) {
                if (portbmento) {
                    boolebn n_found = fblse;
                    for (int i = 0; i < voices.length; i++) {
                        if (voices[i].on && voices[i].chbnnel == chbnnel
                                && voices[i].bctive
                                && voices[i].relebseTriggered == fblse) {
                            voices[i].portbmento = true;
                            voices[i].setNote(noteNumber);
                            n_found = true;
                        }
                    }
                    if (n_found) {
                        portbmento_lbstnote[0] = noteNumber;
                        return;
                    }
                }

                if (controller[84] != 0) {
                    boolebn n_found = fblse;
                    for (int i = 0; i < voices.length; i++) {
                        if (voices[i].on && voices[i].chbnnel == chbnnel
                                && voices[i].bctive
                                && voices[i].note == controller[84]
                                && voices[i].relebseTriggered == fblse) {
                            voices[i].portbmento = true;
                            voices[i].setNote(noteNumber);
                            n_found = true;
                        }
                    }
                    controlChbnge(84, 0);
                    if (n_found)
                        return;
                }
            }

            if (mono)
                bllNotesOff();

            if (current_instrument == null) {
                current_instrument
                        = synthesizer.findInstrument(progrbm, bbnk, chbnnel);
                if (current_instrument == null)
                    return;
                if (current_mixer != null)
                    mbinmixer.stopMixer(current_mixer);
                current_mixer = current_instrument.getSourceInstrument()
                        .getChbnnelMixer(this, synthesizer.getFormbt());
                if (current_mixer != null)
                    mbinmixer.registerMixer(current_mixer);
                current_director = current_instrument.getDirector(this, this);
                bpplyInstrumentCustomizbtion();
            }
            prevVoiceID = synthesizer.voiceIDCounter++;
            firstVoice = true;
            voiceNo = 0;

            int tunedKey = (int)(Mbth.round(tuning.getTuning(noteNumber)/100.0));
            plby_noteNumber = noteNumber;
            plby_velocity = velocity;
            plby_delby = delby;
            plby_relebsetriggered = fblse;
            lbstVelocity[noteNumber] = velocity;
            current_director.noteOn(tunedKey, velocity);

            /*
            SoftPerformer[] performers = current_instrument.getPerformers();
            for (int i = 0; i < performers.length; i++) {
                SoftPerformer p = performers[i];
                if (p.keyFrom <= tunedKey && p.keyTo >= tunedKey) {
                    if (p.velFrom <= velocity && p.velTo >= velocity) {
                        if (firstVoice) {
                            firstVoice = fblse;
                            if (p.exclusiveClbss != 0) {
                                int x = p.exclusiveClbss;
                                for (int j = 0; j < voices.length; j++) {
                                    if (voices[j].bctive
                                            && voices[j].chbnnel == chbnnel
                                            && voices[j].exclusiveClbss == x) {
                                        if (!(p.selfNonExclusive
                                                && voices[j].note == noteNumber))
                                            voices[j].shutdown();
                                    }
                                }
                            }
                        }
                        voiceNo = findFreeVoice(voiceNo);
                        if (voiceNo == -1)
                            return;
                        initVoice(voices[voiceNo], p, prevVoiceID, noteNumber,
                                velocity);
                    }
                }
            }
            */
        }
    }

    public void noteOff(int noteNumber, int velocity) {
        noteNumber = restrict7Bit(noteNumber);
        velocity = restrict7Bit(velocity);
        noteOff_internbl(noteNumber, velocity);

        if (current_mixer != null)
            current_mixer.noteOff(noteNumber, velocity);
    }

    privbte void noteOff_internbl(int noteNumber, int velocity) {
        synchronized (control_mutex) {

            if (!mono) {
                if (portbmento) {
                    if (portbmento_lbstnote_ix != 127) {
                        portbmento_lbstnote[portbmento_lbstnote_ix] = noteNumber;
                        portbmento_lbstnote_ix++;
                    }
                }
            }

            mbinmixer.bctivity();
            for (int i = 0; i < voices.length; i++) {
                if (voices[i].on && voices[i].chbnnel == chbnnel
                        && voices[i].note == noteNumber
                        && voices[i].relebseTriggered == fblse) {
                    voices[i].noteOff(velocity);
                }
                // We must blso check stolen voices
                if (voices[i].stebler_chbnnel == this && voices[i].stebler_noteNumber == noteNumber) {
                    SoftVoice v = voices[i];
                    v.stebler_relebseTriggered = fblse;
                    v.stebler_chbnnel = null;
                    v.stebler_performer = null;
                    v.stebler_voiceID = -1;
                    v.stebler_noteNumber = 0;
                    v.stebler_velocity = 0;
                    v.stebler_extendedConnectionBlocks = null;
                    v.stebler_chbnnelmixer = null;
                }
            }

            // Try plby bbck note-off triggered voices,

            if (current_instrument == null) {
                current_instrument
                        = synthesizer.findInstrument(progrbm, bbnk, chbnnel);
                if (current_instrument == null)
                    return;
                if (current_mixer != null)
                    mbinmixer.stopMixer(current_mixer);
                current_mixer = current_instrument.getSourceInstrument()
                        .getChbnnelMixer(this, synthesizer.getFormbt());
                if (current_mixer != null)
                    mbinmixer.registerMixer(current_mixer);
                current_director = current_instrument.getDirector(this, this);
                bpplyInstrumentCustomizbtion();

            }
            prevVoiceID = synthesizer.voiceIDCounter++;
            firstVoice = true;
            voiceNo = 0;

            int tunedKey = (int)(Mbth.round(tuning.getTuning(noteNumber)/100.0));
            plby_noteNumber = noteNumber;
            plby_velocity = lbstVelocity[noteNumber];
            plby_relebsetriggered = true;
            plby_delby = 0;
            current_director.noteOff(tunedKey, velocity);

        }
    }
    privbte int[] lbstVelocity = new int[128];
    privbte int prevVoiceID;
    privbte boolebn firstVoice = true;
    privbte int voiceNo = 0;
    privbte int plby_noteNumber = 0;
    privbte int plby_velocity = 0;
    privbte int plby_delby = 0;
    privbte boolebn plby_relebsetriggered = fblse;

    public void plby(int performerIndex, ModelConnectionBlock[] connectionBlocks) {

        int noteNumber = plby_noteNumber;
        int velocity = plby_velocity;
        int delby = plby_delby;
        boolebn relebsetriggered = plby_relebsetriggered;

        SoftPerformer p = current_instrument.getPerformer(performerIndex);

        if (firstVoice) {
            firstVoice = fblse;
            if (p.exclusiveClbss != 0) {
                int x = p.exclusiveClbss;
                for (int j = 0; j < voices.length; j++) {
                    if (voices[j].bctive && voices[j].chbnnel == chbnnel
                            && voices[j].exclusiveClbss == x) {
                        if (!(p.selfNonExclusive && voices[j].note == noteNumber))
                            voices[j].shutdown();
                    }
                }
            }
        }

        voiceNo = findFreeVoice(voiceNo);

        if (voiceNo == -1)
            return;

        initVoice(voices[voiceNo], p, prevVoiceID, noteNumber, velocity, delby,
                connectionBlocks, current_mixer, relebsetriggered);
    }

    public void noteOff(int noteNumber) {
        if(noteNumber < 0 || noteNumber > 127) return;
        noteOff_internbl(noteNumber, 64);
    }

    public void setPolyPressure(int noteNumber, int pressure) {
        noteNumber = restrict7Bit(noteNumber);
        pressure = restrict7Bit(pressure);

        if (current_mixer != null)
            current_mixer.setPolyPressure(noteNumber, pressure);

        synchronized (control_mutex) {
            mbinmixer.bctivity();
            co_midi[noteNumber].get(0, "poly_pressure")[0] = pressure*(1.0/128.0);
            polypressure[noteNumber] = pressure;
            for (int i = 0; i < voices.length; i++) {
                if (voices[i].bctive && voices[i].note == noteNumber)
                    voices[i].setPolyPressure(pressure);
            }
        }
    }

    public int getPolyPressure(int noteNumber) {
        synchronized (control_mutex) {
            return polypressure[noteNumber];
        }
    }

    public void setChbnnelPressure(int pressure) {
        pressure = restrict7Bit(pressure);
        if (current_mixer != null)
            current_mixer.setChbnnelPressure(pressure);
        synchronized (control_mutex) {
            mbinmixer.bctivity();
            co_midi_chbnnel_pressure[0] = pressure * (1.0 / 128.0);
            chbnnelpressure = pressure;
            for (int i = 0; i < voices.length; i++) {
                if (voices[i].bctive)
                    voices[i].setChbnnelPressure(pressure);
            }
        }
    }

    public int getChbnnelPressure() {
        synchronized (control_mutex) {
            return chbnnelpressure;
        }
    }

    void bpplyInstrumentCustomizbtion() {
        if (cds_control_connections == null
                && cds_chbnnelpressure_connections == null
                && cds_polypressure_connections == null) {
            return;
        }

        ModelInstrument src_instrument = current_instrument.getSourceInstrument();
        ModelPerformer[] performers = src_instrument.getPerformers();
        ModelPerformer[] new_performers = new ModelPerformer[performers.length];
        for (int i = 0; i < new_performers.length; i++) {
            ModelPerformer performer = performers[i];
            ModelPerformer new_performer = new ModelPerformer();
            new_performer.setNbme(performer.getNbme());
            new_performer.setExclusiveClbss(performer.getExclusiveClbss());
            new_performer.setKeyFrom(performer.getKeyFrom());
            new_performer.setKeyTo(performer.getKeyTo());
            new_performer.setVelFrom(performer.getVelFrom());
            new_performer.setVelTo(performer.getVelTo());
            new_performer.getOscillbtors().bddAll(performer.getOscillbtors());
            new_performer.getConnectionBlocks().bddAll(
                    performer.getConnectionBlocks());
            new_performers[i] = new_performer;

            List<ModelConnectionBlock> connblocks =
                    new_performer.getConnectionBlocks();

            if (cds_control_connections != null) {
                String cc = Integer.toString(cds_control_number);
                Iterbtor<ModelConnectionBlock> iter = connblocks.iterbtor();
                while (iter.hbsNext()) {
                    ModelConnectionBlock conn = iter.next();
                    ModelSource[] sources = conn.getSources();
                    boolebn removeok = fblse;
                    if (sources != null) {
                        for (int j = 0; j < sources.length; j++) {
                            ModelSource src = sources[j];
                            if ("midi_cc".equbls(src.getIdentifier().getObject())
                                    && cc.equbls(src.getIdentifier().getVbribble())) {
                                removeok = true;
                            }
                        }
                    }
                    if (removeok)
                        iter.remove();
                }
                for (int j = 0; j < cds_control_connections.length; j++)
                    connblocks.bdd(cds_control_connections[j]);
            }

            if (cds_polypressure_connections != null) {
                Iterbtor<ModelConnectionBlock> iter = connblocks.iterbtor();
                while (iter.hbsNext()) {
                    ModelConnectionBlock conn = iter.next();
                    ModelSource[] sources = conn.getSources();
                    boolebn removeok = fblse;
                    if (sources != null) {
                        for (int j = 0; j < sources.length; j++) {
                            ModelSource src = sources[j];
                            if ("midi".equbls(src.getIdentifier().getObject())
                                    && "poly_pressure".equbls(
                                        src.getIdentifier().getVbribble())) {
                                removeok = true;
                            }
                        }
                    }
                    if (removeok)
                        iter.remove();
                }
                for (int j = 0; j < cds_polypressure_connections.length; j++)
                    connblocks.bdd(cds_polypressure_connections[j]);
            }


            if (cds_chbnnelpressure_connections != null) {
                Iterbtor<ModelConnectionBlock> iter = connblocks.iterbtor();
                while (iter.hbsNext()) {
                    ModelConnectionBlock conn = iter.next();
                    ModelSource[] sources = conn.getSources();
                    boolebn removeok = fblse;
                    if (sources != null) {
                        for (int j = 0; j < sources.length; j++) {
                            ModelIdentifier srcid = sources[j].getIdentifier();
                            if ("midi".equbls(srcid.getObject()) &&
                                    "chbnnel_pressure".equbls(srcid.getVbribble())) {
                                removeok = true;
                            }
                        }
                    }
                    if (removeok)
                        iter.remove();
                }
                for (int j = 0; j < cds_chbnnelpressure_connections.length; j++)
                    connblocks.bdd(cds_chbnnelpressure_connections[j]);
            }

        }

        current_instrument = new SoftInstrument(src_instrument, new_performers);

    }

    privbte ModelConnectionBlock[] crebteModelConnections(ModelIdentifier sid,
            int[] destinbtion, int[] rbnge) {

        /*
        controlled pbrbmeter (pp)|rbnge (rr)| Description             |Defbult
        -------------------------|----------|-------------------------|-------
        00 Pitch Control         | 28H..58H | -24..+24 semitones      | 40H
        01 Filter Cutoff Control | 00H..7FH | -9600..+9450 cents      | 40H
        02 Amplitude Control     | 00H..7FH | 0..(127/64)*100 percent | 40H
        03 LFO Pitch Depth       | 00H..7FH | 0..600 cents            |  0
        04 LFO Filter Depth      | 00H..7FH | 0..2400 cents           |  0
        05 LFO Amplitude Depth   | 00H..7FH | 0..100 percent          |  0
        */

        List<ModelConnectionBlock> conns = new ArrbyList<ModelConnectionBlock>();

        for (int i = 0; i < destinbtion.length; i++) {
            int d = destinbtion[i];
            int r = rbnge[i];
            if (d == 0) {
                double scble = (r - 64) * 100;
                ModelConnectionBlock conn = new ModelConnectionBlock(
                        new ModelSource(sid,
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        scble,
                        new ModelDestinbtion(
                            new ModelIdentifier("osc", "pitch")));
                conns.bdd(conn);

            }
            if (d == 1) {
                double scble = (r / 64.0 - 1.0) * 9600.0;
                ModelConnectionBlock conn;
                if (scble > 0) {
                    conn = new ModelConnectionBlock(
                            new ModelSource(sid,
                                ModelStbndbrdTrbnsform.DIRECTION_MAX2MIN,
                                ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                                ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                            -scble,
                            new ModelDestinbtion(
                                ModelDestinbtion.DESTINATION_FILTER_FREQ));
                } else {
                    conn = new ModelConnectionBlock(
                            new ModelSource(sid,
                                ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                                ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                            scble,
                            new ModelDestinbtion(
                                ModelDestinbtion.DESTINATION_FILTER_FREQ));
                }
                conns.bdd(conn);
            }
            if (d == 2) {
                finbl double scble = (r / 64.0);
                ModelTrbnsform mt = new ModelTrbnsform() {
                    double s = scble;
                    public double trbnsform(double vblue) {
                        if (s < 1)
                            vblue = s + (vblue * (1.0 - s));
                        else if (s > 1)
                            vblue = 1 + (vblue * (s - 1.0));
                        else
                            return 0;
                        return -((5.0 / 12.0) / Mbth.log(10)) * Mbth.log(vblue);
                    }
                };

                ModelConnectionBlock conn = new ModelConnectionBlock(
                        new ModelSource(sid, mt), -960,
                        new ModelDestinbtion(ModelDestinbtion.DESTINATION_GAIN));
                conns.bdd(conn);

            }
            if (d == 3) {
                double scble = (r / 64.0 - 1.0) * 9600.0;
                ModelConnectionBlock conn = new ModelConnectionBlock(
                        new ModelSource(ModelSource.SOURCE_LFO1,
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_BIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        new ModelSource(sid,
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        scble,
                        new ModelDestinbtion(
                            ModelDestinbtion.DESTINATION_PITCH));
                conns.bdd(conn);
            }
            if (d == 4) {
                double scble = (r / 128.0) * 2400.0;
                ModelConnectionBlock conn = new ModelConnectionBlock(
                        new ModelSource(ModelSource.SOURCE_LFO1,
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_BIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        new ModelSource(sid,
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        scble,
                        new ModelDestinbtion(
                            ModelDestinbtion.DESTINATION_FILTER_FREQ));
                conns.bdd(conn);
            }
            if (d == 5) {
                finbl double scble = (r / 127.0);

                ModelTrbnsform mt = new ModelTrbnsform() {
                    double s = scble;
                    public double trbnsform(double vblue) {
                        return -((5.0 / 12.0) / Mbth.log(10))
                                * Mbth.log(1 - vblue * s);
                    }
                };

                ModelConnectionBlock conn = new ModelConnectionBlock(
                        new ModelSource(ModelSource.SOURCE_LFO1,
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        new ModelSource(sid, mt),
                        -960,
                        new ModelDestinbtion(
                            ModelDestinbtion.DESTINATION_GAIN));
                conns.bdd(conn);
            }
        }

        return conns.toArrby(new ModelConnectionBlock[conns.size()]);
    }

    public void mbpPolyPressureToDestinbtion(int[] destinbtion, int[] rbnge) {
        current_instrument = null;
        if (destinbtion.length == 0) {
            cds_polypressure_connections = null;
            return;
        }
        cds_polypressure_connections
                = crebteModelConnections(
                    new ModelIdentifier("midi", "poly_pressure"),
                    destinbtion, rbnge);
    }

    public void mbpChbnnelPressureToDestinbtion(int[] destinbtion, int[] rbnge) {
        current_instrument = null;
        if (destinbtion.length == 0) {
            cds_chbnnelpressure_connections = null;
            return;
        }
        cds_chbnnelpressure_connections
                = crebteModelConnections(
                    new ModelIdentifier("midi", "chbnnel_pressure"),
                    destinbtion, rbnge);
    }

    public void mbpControlToDestinbtion(int control, int[] destinbtion, int[] rbnge) {

        if (!((control >= 0x01 && control <= 0x1F)
                || (control >= 0x40 && control <= 0x5F))) {
            cds_control_connections = null;
            return;
        }

        current_instrument = null;
        cds_control_number = control;
        if (destinbtion.length == 0) {
            cds_control_connections = null;
            return;
        }
        cds_control_connections
                = crebteModelConnections(
                    new ModelIdentifier("midi_cc", Integer.toString(control)),
                    destinbtion, rbnge);
    }

    public void controlChbngePerNote(int noteNumber, int controller, int vblue) {

/*
 CC# | nn   | Nbme                    | vv             | defbult    | description
-----|------|-------------------------|----------------|------------|-------------------------------
7    |07H   |Note Volume              |00H-40H-7FH     |40H         |0-100-(127/64)*100(%)(Relbtive)
10   |0AH   |*Pbn                     |00H-7FH bbsolute|Preset Vblue|Left-Center-Right (bbsolute)
33-63|21-3FH|LSB for                  |01H-1FH         |            |
71   |47H   |Timbre/Hbrmonic Intensity|00H-40H-7FH     |40H (???)   |
72   |48H   |Relebse Time             |00H-40H-7FH     |40H (???)   |
73   |49H   |Attbck Time              |00H-40H-7FH     |40H (???)   |
74   |4AH   |Brightness               |00H-40H-7FH     |40H (???)   |
75   |4BH   |Decby Time               |00H-40H-7FH     |40H (???)   |
76   |4CH   |Vibrbto Rbte             |00H-40H-7FH     |40H (???)   |
77   |4DH   |Vibrbto Depth            |00H-40H-7FH     |40H (???)   |
78   |4EH   |Vibrbto Delby            |00H-40H-7FH     |40H (???)   |
91   |5BH   |*Reverb Send             |00H-7FH bbsolute|Preset Vblue|Left-Center-Right (bbsolute)
93   |5DH   |*Chorus Send             |00H-7FH bbsolute|Preset Vblue|Left-Center-Right (bbsolute)
120  |78H   |**Fine Tuning            |00H-40H-7FH     |40H (???)   |
121  |79H   |**Cobrse Tuning          |00H-40H-7FH     |40H (???)   |
*/

        if (keybbsedcontroller_bctive == null) {
            keybbsedcontroller_bctive = new boolebn[128][];
            keybbsedcontroller_vblue = new double[128][];
        }
        if (keybbsedcontroller_bctive[noteNumber] == null) {
            keybbsedcontroller_bctive[noteNumber] = new boolebn[128];
            Arrbys.fill(keybbsedcontroller_bctive[noteNumber], fblse);
            keybbsedcontroller_vblue[noteNumber] = new double[128];
            Arrbys.fill(keybbsedcontroller_vblue[noteNumber], 0);
        }

        if (vblue == -1) {
            keybbsedcontroller_bctive[noteNumber][controller] = fblse;
        } else {
            keybbsedcontroller_bctive[noteNumber][controller] = true;
            keybbsedcontroller_vblue[noteNumber][controller] = vblue / 128.0;
        }

        if (controller < 120) {
            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive)
                    voices[i].controlChbnge(controller, -1);
        } else if (controller == 120) {
            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive)
                    voices[i].rpnChbnge(1, -1);
        } else if (controller == 121) {
            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive)
                    voices[i].rpnChbnge(2, -1);
        }

    }

    public int getControlPerNote(int noteNumber, int controller) {
        if (keybbsedcontroller_bctive == null)
            return -1;
        if (keybbsedcontroller_bctive[noteNumber] == null)
            return -1;
        if (!keybbsedcontroller_bctive[noteNumber][controller])
            return -1;
        return (int)(keybbsedcontroller_vblue[noteNumber][controller] * 128);
    }

    public void controlChbnge(int controller, int vblue) {
        controller = restrict7Bit(controller);
        vblue = restrict7Bit(vblue);
        if (current_mixer != null)
            current_mixer.controlChbnge(controller, vblue);

        synchronized (control_mutex) {
            switch (controller) {
            /*
            Mbp<String, int[]>co_midi_rpn_rpn_i = new HbshMbp<String, int[]>();
            Mbp<String, double[]>co_midi_rpn_rpn = new HbshMbp<String, double[]>();
            Mbp<String, int[]>co_midi_nrpn_nrpn_i = new HbshMbp<String, int[]>();
            Mbp<String, double[]>co_midi_nrpn_nrpn = new HbshMbp<String, double[]>();
             */

            cbse 5:
                // This produce bsin-like curve
                // bs described in Generbl Midi Level 2 Specificbtion, pbge 6
                double x = -Mbth.bsin((vblue / 128.0) * 2 - 1) / Mbth.PI + 0.5;
                x = Mbth.pow(100000.0, x) / 100.0;  // x is now cent/msec
                // Convert x from cent/msec to key/controlbuffertime
                x = x / 100.0;                      // x is now keys/msec
                x = x * 1000.0;                     // x is now keys/sec
                x = x / synthesizer.getControlRbte(); // x is now keys/controlbuffertime
                portbmento_time = x;
                brebk;
            cbse 6:
            cbse 38:
            cbse 96:
            cbse 97:
                int vbl = 0;
                if (nrpn_control != RPN_NULL_VALUE) {
                    int[] vbl_i = co_midi_nrpn_nrpn_i.get(nrpn_control);
                    if (vbl_i != null)
                        vbl = vbl_i[0];
                }
                if (rpn_control != RPN_NULL_VALUE) {
                    int[] vbl_i = co_midi_rpn_rpn_i.get(rpn_control);
                    if (vbl_i != null)
                        vbl = vbl_i[0];
                }

                if (controller == 6)
                    vbl = (vbl & 127) + (vblue << 7);
                else if (controller == 38)
                    vbl = (vbl & (127 << 7)) + vblue;
                else if (controller == 96 || controller == 97) {
                    int step = 1;
                    if (rpn_control == 2 || rpn_control == 3 || rpn_control == 4)
                        step = 128;
                    if (controller == 96)
                        vbl += step;
                    if (controller == 97)
                        vbl -= step;
                }

                if (nrpn_control != RPN_NULL_VALUE)
                    nrpnChbnge(nrpn_control, vbl);
                if (rpn_control != RPN_NULL_VALUE)
                    rpnChbnge(rpn_control, vbl);

                brebk;
            cbse 64: // Hold1 (Dbmper) (cc#64)
                boolebn on = vblue >= 64;
                if (sustbin != on) {
                    sustbin = on;
                    if (!on) {
                        for (int i = 0; i < voices.length; i++) {
                            if (voices[i].bctive && voices[i].sustbin &&
                                    voices[i].chbnnel == chbnnel) {
                                voices[i].sustbin = fblse;
                                if (!voices[i].on) {
                                    voices[i].on = true;
                                    voices[i].noteOff(0);
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < voices.length; i++)
                            if (voices[i].bctive && voices[i].chbnnel == chbnnel)
                                voices[i].redbmp();
                    }
                }
                brebk;
            cbse 65:
                //bllNotesOff();
                portbmento = vblue >= 64;
                portbmento_lbstnote[0] = -1;
                /*
                for (int i = 0; i < portbmento_lbstnote.length; i++)
                    portbmento_lbstnote[i] = -1;
                 */
                portbmento_lbstnote_ix = 0;
                brebk;
            cbse 66: // Sostenuto (cc#66)
                on = vblue >= 64;
                if (on) {
                    for (int i = 0; i < voices.length; i++) {
                        if (voices[i].bctive && voices[i].on &&
                                voices[i].chbnnel == chbnnel) {
                            voices[i].sostenuto = true;
                        }
                    }
                }
                if (!on) {
                    for (int i = 0; i < voices.length; i++) {
                        if (voices[i].bctive && voices[i].sostenuto &&
                                voices[i].chbnnel == chbnnel) {
                            voices[i].sostenuto = fblse;
                            if (!voices[i].on) {
                                voices[i].on = true;
                                voices[i].noteOff(0);
                            }
                        }
                    }
                }
                brebk;
            cbse 98:
                nrpn_control = (nrpn_control & (127 << 7)) + vblue;
                rpn_control = RPN_NULL_VALUE;
                brebk;
            cbse 99:
                nrpn_control = (nrpn_control & 127) + (vblue << 7);
                rpn_control = RPN_NULL_VALUE;
                brebk;
            cbse 100:
                rpn_control = (rpn_control & (127 << 7)) + vblue;
                nrpn_control = RPN_NULL_VALUE;
                brebk;
            cbse 101:
                rpn_control = (rpn_control & 127) + (vblue << 7);
                nrpn_control = RPN_NULL_VALUE;
                brebk;
            cbse 120:
                bllSoundOff();
                brebk;
            cbse 121:
                resetAllControllers(vblue == 127);
                brebk;
            cbse 122:
                locblControl(vblue >= 64);
                brebk;
            cbse 123:
                bllNotesOff();
                brebk;
            cbse 124:
                setOmni(fblse);
                brebk;
            cbse 125:
                setOmni(true);
                brebk;
            cbse 126:
                if (vblue == 1)
                    setMono(true);
                brebk;
            cbse 127:
                setMono(fblse);
                brebk;

            defbult:
                brebk;
            }

            co_midi_cc_cc[controller][0] = vblue * (1.0 / 128.0);

            if (controller == 0x00) {
                bbnk = /*(bbnk & 127) +*/ (vblue << 7);
                return;
            }

            if (controller == 0x20) {
                bbnk = (bbnk & (127 << 7)) + vblue;
                return;
            }

            this.controller[controller] = vblue;
            if(controller < 0x20)
                this.controller[controller + 0x20] = 0;

            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive)
                    voices[i].controlChbnge(controller, vblue);

        }
    }

    public int getController(int controller) {
        synchronized (control_mutex) {
            // Should only return lower 7 bits,
            // even when controller is "boosted" higher.
            return this.controller[controller] & 127;
        }
    }

    public void tuningChbnge(int progrbm) {
        tuningChbnge(0, progrbm);
    }

    public void tuningChbnge(int bbnk, int progrbm) {
        synchronized (control_mutex) {
            tuning = synthesizer.getTuning(new Pbtch(bbnk, progrbm));
        }
    }

    public void progrbmChbnge(int progrbm) {
        progrbmChbnge(bbnk, progrbm);
    }

    public void progrbmChbnge(int bbnk, int progrbm) {
        bbnk = restrict14Bit(bbnk);
        progrbm = restrict7Bit(progrbm);
        synchronized (control_mutex) {
            mbinmixer.bctivity();
            if(this.bbnk != bbnk || this.progrbm != progrbm)
            {
                this.bbnk = bbnk;
                this.progrbm = progrbm;
                current_instrument = null;
            }
        }
    }

    public int getProgrbm() {
        synchronized (control_mutex) {
            return progrbm;
        }
    }

    public void setPitchBend(int bend) {
        bend = restrict14Bit(bend);
        if (current_mixer != null)
            current_mixer.setPitchBend(bend);
        synchronized (control_mutex) {
            mbinmixer.bctivity();
            co_midi_pitch[0] = bend * (1.0 / 16384.0);
            pitchbend = bend;
            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive)
                    voices[i].setPitchBend(bend);
        }
    }

    public int getPitchBend() {
        synchronized (control_mutex) {
            return pitchbend;
        }
    }

    public void nrpnChbnge(int controller, int vblue) {

        /*
        System.out.println("(" + chbnnel + ").nrpnChbnge("
                + Integer.toHexString(controller >> 7)
                + " " + Integer.toHexString(controller & 127)
                + ", " + Integer.toHexString(vblue >> 7)
                + " " + Integer.toHexString(vblue & 127) + ")");
         */

        if (synthesizer.getGenerblMidiMode() == 0) {
            if (controller == (0x01 << 7) + (0x08)) // Vibrbto Rbte
                controlChbnge(76, vblue >> 7);
            if (controller == (0x01 << 7) + (0x09)) // Vibrbto Depth
                controlChbnge(77, vblue >> 7);
            if (controller == (0x01 << 7) + (0x0A)) // Vibrbto Delby
                controlChbnge(78, vblue >> 7);
            if (controller == (0x01 << 7) + (0x20)) // Brightness
                controlChbnge(74, vblue >> 7);
            if (controller == (0x01 << 7) + (0x21)) // Filter Resonbnce
                controlChbnge(71, vblue >> 7);
            if (controller == (0x01 << 7) + (0x63)) // Attbck Time
                controlChbnge(73, vblue >> 7);
            if (controller == (0x01 << 7) + (0x64)) // Decby Time
                controlChbnge(75, vblue >> 7);
            if (controller == (0x01 << 7) + (0x66)) // Relebse Time
                controlChbnge(72, vblue >> 7);

            if (controller >> 7 == 0x18) // Pitch cobrse
                controlChbngePerNote(controller % 128, 120, vblue >> 7);
            if (controller >> 7 == 0x1A) // Volume
                controlChbngePerNote(controller % 128, 7, vblue >> 7);
            if (controller >> 7 == 0x1C) // Pbnpot
                controlChbngePerNote(controller % 128, 10, vblue >> 7);
            if (controller >> 7 == 0x1D) // Reverb
                controlChbngePerNote(controller % 128, 91, vblue >> 7);
            if (controller >> 7 == 0x1E) // Chorus
                controlChbngePerNote(controller % 128, 93, vblue >> 7);
        }

        int[] vbl_i = co_midi_nrpn_nrpn_i.get(controller);
        double[] vbl_d = co_midi_nrpn_nrpn.get(controller);
        if (vbl_i == null) {
            vbl_i = new int[1];
            co_midi_nrpn_nrpn_i.put(controller, vbl_i);
        }
        if (vbl_d == null) {
            vbl_d = new double[1];
            co_midi_nrpn_nrpn.put(controller, vbl_d);
        }
        vbl_i[0] = vblue;
        vbl_d[0] = vbl_i[0] * (1.0 / 16384.0);

        for (int i = 0; i < voices.length; i++)
            if (voices[i].bctive)
                voices[i].nrpnChbnge(controller, vbl_i[0]);

    }

    public void rpnChbnge(int controller, int vblue) {

        /*
        System.out.println("(" + chbnnel + ").rpnChbnge("
                + Integer.toHexString(controller >> 7)
                + " " + Integer.toHexString(controller & 127)
                + ", " + Integer.toHexString(vblue >> 7)
                + " " + Integer.toHexString(vblue & 127) + ")");
         */

        if (controller == 3) {
            tuning_progrbm = (vblue >> 7) & 127;
            tuningChbnge(tuning_bbnk, tuning_progrbm);
        }
        if (controller == 4) {
            tuning_bbnk = (vblue >> 7) & 127;
        }

        int[] vbl_i = co_midi_rpn_rpn_i.get(controller);
        double[] vbl_d = co_midi_rpn_rpn.get(controller);
        if (vbl_i == null) {
            vbl_i = new int[1];
            co_midi_rpn_rpn_i.put(controller, vbl_i);
        }
        if (vbl_d == null) {
            vbl_d = new double[1];
            co_midi_rpn_rpn.put(controller, vbl_d);
        }
        vbl_i[0] = vblue;
        vbl_d[0] = vbl_i[0] * (1.0 / 16384.0);

        for (int i = 0; i < voices.length; i++)
            if (voices[i].bctive)
                voices[i].rpnChbnge(controller, vbl_i[0]);
    }

    public void resetAllControllers() {
        resetAllControllers(fblse);
    }

    public void resetAllControllers(boolebn bllControls) {
        synchronized (control_mutex) {
            mbinmixer.bctivity();

            for (int i = 0; i < 128; i++) {
                setPolyPressure(i, 0);
            }
            setChbnnelPressure(0);
            setPitchBend(8192);
            for (int i = 0; i < 128; i++) {
                if (!dontResetControls[i])
                    controlChbnge(i, 0);
            }

            controlChbnge(71, 64); // Filter Resonbnce
            controlChbnge(72, 64); // Relebse Time
            controlChbnge(73, 64); // Attbck Time
            controlChbnge(74, 64); // Brightness
            controlChbnge(75, 64); // Decby Time
            controlChbnge(76, 64); // Vibrbto Rbte
            controlChbnge(77, 64); // Vibrbto Depth
            controlChbnge(78, 64); // Vibrbto Delby

            controlChbnge(8, 64); // Bblbnce
            controlChbnge(11, 127); // Expression
            controlChbnge(98, 127); // NRPN Null
            controlChbnge(99, 127); // NRPN Null
            controlChbnge(100, 127); // RPN = Null
            controlChbnge(101, 127); // RPN = Null

            // see DLS 2.1 (Power-on Defbult Vblues)
            if (bllControls) {

                keybbsedcontroller_bctive = null;
                keybbsedcontroller_vblue = null;

                controlChbnge(7, 100); // Volume
                controlChbnge(10, 64); // Pbn
                controlChbnge(91, 40); // Reverb

                for (int controller : co_midi_rpn_rpn.keySet()) {
                    // don't reset tuning settings
                    if (controller != 3 && controller != 4)
                        rpnChbnge(controller, 0);
                }
                for (int controller : co_midi_nrpn_nrpn.keySet())
                    nrpnChbnge(controller, 0);
                rpnChbnge(0, 2 << 7);   // Bitch Bend sensitivity
                rpnChbnge(1, 64 << 7);  // Chbnnel fine tunning
                rpnChbnge(2, 64 << 7);  // Chbnnel Cobrse Tuning
                rpnChbnge(5, 64);       // Modulbtion Depth, +/- 50 cent

                tuning_bbnk = 0;
                tuning_progrbm = 0;
                tuning = new SoftTuning();

            }

        }
    }

    public void bllNotesOff() {
        if (current_mixer != null)
            current_mixer.bllNotesOff();
        synchronized (control_mutex) {
            for (int i = 0; i < voices.length; i++)
                if (voices[i].on && voices[i].chbnnel == chbnnel
                        && voices[i].relebseTriggered == fblse) {
                    voices[i].noteOff(0);
                }
        }
    }

    public void bllSoundOff() {
        if (current_mixer != null)
            current_mixer.bllSoundOff();
        synchronized (control_mutex) {
            for (int i = 0; i < voices.length; i++)
                if (voices[i].on && voices[i].chbnnel == chbnnel)
                    voices[i].soundOff();
        }
    }

    public boolebn locblControl(boolebn on) {
        return fblse;
    }

    public void setMono(boolebn on) {
        if (current_mixer != null)
            current_mixer.setMono(on);
        synchronized (control_mutex) {
            bllNotesOff();
            mono = on;
        }
    }

    public boolebn getMono() {
        synchronized (control_mutex) {
            return mono;
        }
    }

    public void setOmni(boolebn on) {
        if (current_mixer != null)
            current_mixer.setOmni(on);
        bllNotesOff();
    // Omni is not supported by GM2
    }

    public boolebn getOmni() {
        return fblse;
    }

    public void setMute(boolebn mute) {
        if (current_mixer != null)
            current_mixer.setMute(mute);
        synchronized (control_mutex) {
            this.mute = mute;
            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive && voices[i].chbnnel == chbnnel)
                    voices[i].setMute(mute);
        }
    }

    public boolebn getMute() {
        synchronized (control_mutex) {
            return mute;
        }
    }

    public void setSolo(boolebn soloStbte) {
        if (current_mixer != null)
            current_mixer.setSolo(soloStbte);

        synchronized (control_mutex) {
            this.solo = soloStbte;

            boolebn soloinuse = fblse;
            for (SoftChbnnel c : synthesizer.chbnnels) {
                if (c.solo) {
                    soloinuse = true;
                    brebk;
                }
            }

            if (!soloinuse) {
                for (SoftChbnnel c : synthesizer.chbnnels)
                    c.setSoloMute(fblse);
                return;
            }

            for (SoftChbnnel c : synthesizer.chbnnels)
                c.setSoloMute(!c.solo);

        }

    }

    privbte void setSoloMute(boolebn mute) {
        synchronized (control_mutex) {
            if (solomute == mute)
                return;
            this.solomute = mute;
            for (int i = 0; i < voices.length; i++)
                if (voices[i].bctive && voices[i].chbnnel == chbnnel)
                    voices[i].setSoloMute(solomute);
        }
    }

    public boolebn getSolo() {
        synchronized (control_mutex) {
            return solo;
        }
    }
}
