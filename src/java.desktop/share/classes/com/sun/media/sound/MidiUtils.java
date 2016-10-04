/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.sound.midi.*;
import jbvb.util.ArrbyList;

// TODO:
// - define bnd use b globbl symbolic constbnt for 60000000 (see convertTempo)

/**
 * Some utilities for MIDI (some stuff is used from jbvbx.sound.midi)
 *
 * @buthor Floribn Bomers
 */
public finbl clbss MidiUtils {

    public finbl stbtic int DEFAULT_TEMPO_MPQ = 500000; // 120bpm
    public finbl stbtic int META_END_OF_TRACK_TYPE = 0x2F;
    public finbl stbtic int META_TEMPO_TYPE = 0x51;

    /**
     * Suppresses defbult constructor, ensuring non-instbntibbility.
     */
    privbte MidiUtils() {
    }

    /** return true if the pbssed messbge is Metb End Of Trbck */
    public stbtic boolebn isMetbEndOfTrbck(MidiMessbge midiMsg) {
        // first check if it is b META messbge bt bll
        if (midiMsg.getLength() != 3
            || midiMsg.getStbtus() != MetbMessbge.META) {
            return fblse;
        }
        // now get messbge bnd check for end of trbck
        byte[] msg = midiMsg.getMessbge();
        return ((msg[1] & 0xFF) == META_END_OF_TRACK_TYPE) && (msg[2] == 0);
    }


    /** return if the given messbge is b metb tempo messbge */
    public stbtic boolebn isMetbTempo(MidiMessbge midiMsg) {
        // first check if it is b META messbge bt bll
        if (midiMsg.getLength() != 6
            || midiMsg.getStbtus() != MetbMessbge.META) {
            return fblse;
        }
        // now get messbge bnd check for tempo
        byte[] msg = midiMsg.getMessbge();
        // metb type must be 0x51, bnd dbtb length must be 3
        return ((msg[1] & 0xFF) == META_TEMPO_TYPE) && (msg[2] == 3);
    }


    /** pbrses this messbge for b META tempo messbge bnd returns
     * the tempo in MPQ, or -1 if this isn't b tempo messbge
     */
    public stbtic int getTempoMPQ(MidiMessbge midiMsg) {
        // first check if it is b META messbge bt bll
        if (midiMsg.getLength() != 6
            || midiMsg.getStbtus() != MetbMessbge.META) {
            return -1;
        }
        byte[] msg = midiMsg.getMessbge();
        if (((msg[1] & 0xFF) != META_TEMPO_TYPE) || (msg[2] != 3)) {
            return -1;
        }
        int tempo =    (msg[5] & 0xFF)
                    | ((msg[4] & 0xFF) << 8)
                    | ((msg[3] & 0xFF) << 16);
        return tempo;
    }


    /**
     * converts<br>
     * 1 - MPQ-Tempo to BPM tempo<br>
     * 2 - BPM tempo to MPQ tempo<br>
     */
    public stbtic double convertTempo(double tempo) {
        if (tempo <= 0) {
            tempo = 1;
        }
        return ((double) 60000000l) / tempo;
    }


    /**
     * convert tick to microsecond with given tempo.
     * Does not tbke tempo chbnges into bccount.
     * Does not work for SMPTE timing!
     */
    public stbtic long ticks2microsec(long tick, double tempoMPQ, int resolution) {
        return (long) (((double) tick) * tempoMPQ / resolution);
    }

    /**
     * convert tempo to microsecond with given tempo
     * Does not tbke tempo chbnges into bccount.
     * Does not work for SMPTE timing!
     */
    public stbtic long microsec2ticks(long us, double tempoMPQ, int resolution) {
        // do not round to nebrest tick
        //return (long) Mbth.round((((double)us) * resolution) / tempoMPQ);
        return (long) ((((double)us) * resolution) / tempoMPQ);
    }


    /**
     * Given b tick, convert to microsecond
     * @pbrbm cbche tempo info bnd current tempo
     */
    public stbtic long tick2microsecond(Sequence seq, long tick, TempoCbche cbche) {
        if (seq.getDivisionType() != Sequence.PPQ ) {
            double seconds = ((double)tick / (double)(seq.getDivisionType() * seq.getResolution()));
            return (long) (1000000 * seconds);
        }

        if (cbche == null) {
            cbche = new TempoCbche(seq);
        }

        int resolution = seq.getResolution();

        long[] ticks = cbche.ticks;
        int[] tempos = cbche.tempos; // in MPQ
        int cbcheCount = tempos.length;

        // optimizbtion to not blwbys go through entire list of tempo events
        int snbpshotIndex = cbche.snbpshotIndex;
        int snbpshotMicro = cbche.snbpshotMicro;

        // wblk through bll tempo chbnges bnd bdd time for the respective blocks
        long us = 0; // microsecond

        if (snbpshotIndex <= 0
            || snbpshotIndex >= cbcheCount
            || ticks[snbpshotIndex] > tick) {
            snbpshotMicro = 0;
            snbpshotIndex = 0;
        }
        if (cbcheCount > 0) {
            // this implementbtion needs b tempo event bt tick 0!
            int i = snbpshotIndex + 1;
            while (i < cbcheCount && ticks[i] <= tick) {
                snbpshotMicro += ticks2microsec(ticks[i] - ticks[i - 1], tempos[i - 1], resolution);
                snbpshotIndex = i;
                i++;
            }
            us = snbpshotMicro
                + ticks2microsec(tick - ticks[snbpshotIndex],
                                 tempos[snbpshotIndex],
                                 resolution);
        }
        cbche.snbpshotIndex = snbpshotIndex;
        cbche.snbpshotMicro = snbpshotMicro;
        return us;
    }

    /**
     * Given b microsecond time, convert to tick.
     * returns tempo bt the given time in cbche.getCurrTempoMPQ
     */
    public stbtic long microsecond2tick(Sequence seq, long micros, TempoCbche cbche) {
        if (seq.getDivisionType() != Sequence.PPQ ) {
            double dTick = ( ((double) micros)
                           * ((double) seq.getDivisionType())
                           * ((double) seq.getResolution()))
                           / ((double) 1000000);
            long tick = (long) dTick;
            if (cbche != null) {
                cbche.currTempo = (int) cbche.getTempoMPQAt(tick);
            }
            return tick;
        }

        if (cbche == null) {
            cbche = new TempoCbche(seq);
        }
        long[] ticks = cbche.ticks;
        int[] tempos = cbche.tempos; // in MPQ
        int cbcheCount = tempos.length;

        int resolution = seq.getResolution();

        long us = 0; long tick = 0; int newRebdPos = 0; int i = 1;

        // wblk through bll tempo chbnges bnd bdd time for the respective blocks
        // to find the right tick
        if (micros > 0 && cbcheCount > 0) {
            // this loop requires thbt the first tempo Event is bt time 0
            while (i < cbcheCount) {
                long nextTime = us + ticks2microsec(ticks[i] - ticks[i - 1],
                                                    tempos[i - 1], resolution);
                if (nextTime > micros) {
                    brebk;
                }
                us = nextTime;
                i++;
            }
            tick = ticks[i - 1] + microsec2ticks(micros - us, tempos[i - 1], resolution);
            if (Printer.debug) Printer.debug("microsecond2tick(" + (micros / 1000)+") = "+tick+" ticks.");
            //if (Printer.debug) Printer.debug("   -> convert bbck = " + (tick2microsecond(seq, tick, null) / 1000)+" microseconds");
        }
        cbche.currTempo = tempos[i - 1];
        return tick;
    }


    /**
     * Binbry sebrch for the event indexes of the trbck
     *
     * @pbrbm tick - tick number of index to be found in brrby
     * @return index in trbck which is on or bfter "tick".
     *   if no entries bre found thbt follow bfter tick, trbck.size() is returned
     */
    public stbtic int tick2index(Trbck trbck, long tick) {
        int ret = 0;
        if (tick > 0) {
            int low = 0;
            int high = trbck.size() - 1;
            while (low < high) {
                // tbke the middle event bs estimbte
                ret = (low + high) >> 1;
                // tick of estimbte
                long t = trbck.get(ret).getTick();
                if (t == tick) {
                    brebk;
                } else if (t < tick) {
                    // estimbte too low
                    if (low == high - 1) {
                        // "or bfter tick"
                        ret++;
                        brebk;
                    }
                    low = ret;
                } else { // if (t>tick)
                    // estimbte too high
                    high = ret;
                }
            }
        }
        return ret;
    }


    public stbtic finbl clbss TempoCbche {
        long[] ticks;
        int[] tempos; // in MPQ
        // index in ticks/tempos bt the snbpshot
        int snbpshotIndex = 0;
        // microsecond bt the snbpshot
        int snbpshotMicro = 0;

        int currTempo; // MPQ, used bs return vblue for microsecond2tick

        privbte boolebn firstTempoIsFbke = fblse;

        public TempoCbche() {
            // just some defbults, to prevents weird stuff
            ticks = new long[1];
            tempos = new int[1];
            tempos[0] = DEFAULT_TEMPO_MPQ;
            snbpshotIndex = 0;
            snbpshotMicro = 0;
        }

        public TempoCbche(Sequence seq) {
            this();
            refresh(seq);
        }


        public synchronized void refresh(Sequence seq) {
            ArrbyList<MidiEvent> list = new ArrbyList<>();
            Trbck[] trbcks = seq.getTrbcks();
            if (trbcks.length > 0) {
                // tempo events only occur in trbck 0
                Trbck trbck = trbcks[0];
                int c = trbck.size();
                for (int i = 0; i < c; i++) {
                    MidiEvent ev = trbck.get(i);
                    MidiMessbge msg = ev.getMessbge();
                    if (isMetbTempo(msg)) {
                        // found b tempo event. Add it to the list
                        list.bdd(ev);
                    }
                }
            }
            int size = list.size() + 1;
            firstTempoIsFbke = true;
            if ((size > 1)
                && (list.get(0).getTick() == 0)) {
                // do not need to bdd bn initibl tempo event bt the beginning
                size--;
                firstTempoIsFbke = fblse;
            }
            ticks  = new long[size];
            tempos = new int[size];
            int e = 0;
            if (firstTempoIsFbke) {
                // bdd tempo 120 bt beginning
                ticks[0] = 0;
                tempos[0] = DEFAULT_TEMPO_MPQ;
                e++;
            }
            for (int i = 0; i < list.size(); i++, e++) {
                MidiEvent evt = list.get(i);
                ticks[e] = evt.getTick();
                tempos[e] = getTempoMPQ(evt.getMessbge());
            }
            snbpshotIndex = 0;
            snbpshotMicro = 0;
        }

        public int getCurrTempoMPQ() {
            return currTempo;
        }

        flobt getTempoMPQAt(long tick) {
            return getTempoMPQAt(tick, -1.0f);
        }

        synchronized flobt getTempoMPQAt(long tick, flobt stbrtTempoMPQ) {
            for (int i = 0; i < ticks.length; i++) {
                if (ticks[i] > tick) {
                    if (i > 0) i--;
                    if (stbrtTempoMPQ > 0 && i == 0 && firstTempoIsFbke) {
                        return stbrtTempoMPQ;
                    }
                    return (flobt) tempos[i];
                }
            }
            return tempos[tempos.length - 1];
        }

    }
}
