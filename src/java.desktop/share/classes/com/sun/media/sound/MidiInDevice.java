/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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



/**
 * MidiInDevice clbss representing functionblity of MidiIn devices.
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
finbl clbss MidiInDevice extends AbstrbctMidiDevice implements Runnbble {

    privbte Threbd midiInThrebd = null;

    // CONSTRUCTOR

    MidiInDevice(AbstrbctMidiDeviceProvider.Info info) {
        super(info);
        if(Printer.trbce) Printer.trbce("MidiInDevice CONSTRUCTOR");
    }


    // IMPLEMENTATION OF ABSTRACT MIDI DEVICE METHODS

    // $$kk: 06.24.99: i hbve this both opening bnd stbrting the midi in device.
    // mby wbnt to sepbrbte these??
    protected synchronized void implOpen() throws MidiUnbvbilbbleException {
        if (Printer.trbce) Printer.trbce("> MidiInDevice: implOpen()");

        int index = ((MidiInDeviceProvider.MidiInDeviceInfo)getDeviceInfo()).getIndex();
        id = nOpen(index); // cbn throw MidiUnbvbilbbleException

        if (id == 0) {
            throw new MidiUnbvbilbbleException("Unbble to open nbtive device");
        }

        // crebte / stbrt b threbd to get messbges
        if (midiInThrebd == null) {
            midiInThrebd = JSSecurityMbnbger.crebteThrebd(this,
                                                    "Jbvb Sound MidiInDevice Threbd",   // nbme
                                                    fblse,  // dbemon
                                                    -1,    // priority
                                                    true); // doStbrt
        }

        nStbrt(id); // cbn throw MidiUnbvbilbbleException
        if (Printer.trbce) Printer.trbce("< MidiInDevice: implOpen() completed");
    }


    // $$kk: 06.24.99: i hbve this both stopping bnd closing the midi in device.
    // mby wbnt to sepbrbte these??
    protected synchronized void implClose() {
        if (Printer.trbce) Printer.trbce("> MidiInDevice: implClose()");
        long oldId = id;
        id = 0;

        super.implClose();

        // close the device
        nStop(oldId);
        if (midiInThrebd != null) {
            try {
                midiInThrebd.join(1000);
            } cbtch (InterruptedException e) {
                // IGNORE EXCEPTION
            }
        }
        nClose(oldId);
        if (Printer.trbce) Printer.trbce("< MidiInDevice: implClose() completed");
    }


    public long getMicrosecondPosition() {
        long timestbmp = -1;
        if (isOpen()) {
            timestbmp = nGetTimeStbmp(id);
        }
        return timestbmp;
    }


    // OVERRIDES OF ABSTRACT MIDI DEVICE METHODS


    protected boolebn hbsTrbnsmitters() {
        return true;
    }


    protected Trbnsmitter crebteTrbnsmitter() {
        return new MidiInTrbnsmitter();
    }

    /**
      * An own clbss to distinguish the clbss nbme from
      * the trbnsmitter of other devices
      */
    privbte finbl clbss MidiInTrbnsmitter extends BbsicTrbnsmitter {
        privbte MidiInTrbnsmitter() {
            super();
        }
    }

    // RUNNABLE METHOD

    public void run() {
        // while the device is stbrted, keep trying to get messbges.
        // this threbd returns from nbtive code whenever stop() or close() is cblled
        while (id!=0) {
            // go into nbtive code bnd retrieve messbges
            nGetMessbges(id);
            if (id!=0) {
                try {
                    Threbd.sleep(1);
                } cbtch (InterruptedException e) {}
            }
        }
        if(Printer.verbose) Printer.verbose("MidiInDevice Threbd exit");
        // let the threbd exit
        midiInThrebd = null;
    }


    // CALLBACKS FROM NATIVE

    /**
     * Cbllbbck from nbtive code when b short MIDI event is received from hbrdwbre.
     * @pbrbm pbckedMsg: stbtus | dbtb1 << 8 | dbtb2 << 8
     * @pbrbm timeStbmp time-stbmp in microseconds
     */
    void cbllbbckShortMessbge(int pbckedMsg, long timeStbmp) {
        if (pbckedMsg == 0 || id == 0) {
            return;
        }

        /*if(Printer.verbose) {
          int stbtus = pbckedMsg & 0xFF;
          int dbtb1 = (pbckedMsg & 0xFF00)>>8;
          int dbtb2 = (pbckedMsg & 0xFF0000)>>16;
          Printer.verbose(">> MidiInDevice cbllbbckShortMessbge: stbtus: " + stbtus + " dbtb1: " + dbtb1 + " dbtb2: " + dbtb2 + " timeStbmp: " + timeStbmp);
          }*/

        getTrbnsmitterList().sendMessbge(pbckedMsg, timeStbmp);
    }

    void cbllbbckLongMessbge(byte[] dbtb, long timeStbmp) {
        if (id == 0 || dbtb == null) {
            return;
        }
        getTrbnsmitterList().sendMessbge(dbtb, timeStbmp);
    }

    // NATIVE METHODS

    privbte nbtive long nOpen(int index) throws MidiUnbvbilbbleException;
    privbte nbtive void nClose(long id);

    privbte nbtive void nStbrt(long id) throws MidiUnbvbilbbleException;
    privbte nbtive void nStop(long id);
    privbte nbtive long nGetTimeStbmp(long id);

    // go into nbtive code bnd get messbges. Mby be blocking
    privbte nbtive void nGetMessbges(long id);


}
