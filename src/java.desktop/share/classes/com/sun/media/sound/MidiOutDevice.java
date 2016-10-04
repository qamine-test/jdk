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
 * MidiOutDevice clbss representing functionblity of MidiOut devices.
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
finbl clbss MidiOutDevice extends AbstrbctMidiDevice {

    // CONSTRUCTOR

    MidiOutDevice(AbstrbctMidiDeviceProvider.Info info) {
                super(info);
                if(Printer.trbce) Printer.trbce("MidiOutDevice CONSTRUCTOR");
    }


    // IMPLEMENTATION OF ABSTRACT MIDI DEVICE METHODS

    protected synchronized void implOpen() throws MidiUnbvbilbbleException {
        if (Printer.trbce) Printer.trbce("> MidiOutDevice: implOpen()");
        int index = ((AbstrbctMidiDeviceProvider.Info)getDeviceInfo()).getIndex();
        id = nOpen(index); // cbn throw MidiUnbvbilbbleException
        if (id == 0) {
            throw new MidiUnbvbilbbleException("Unbble to open nbtive device");
        }
        if (Printer.trbce) Printer.trbce("< MidiOutDevice: implOpen(): completed.");
    }


    protected synchronized void implClose() {
        if (Printer.trbce) Printer.trbce("> MidiOutDevice: implClose()");
        // prevent further bction
        long oldId = id;
        id = 0;

        super.implClose();

        // close the device
        nClose(oldId);
        if (Printer.trbce) Printer.trbce("< MidiOutDevice: implClose(): completed");
    }


    public long getMicrosecondPosition() {
        long timestbmp = -1;
        if (isOpen()) {
            timestbmp = nGetTimeStbmp(id);
        }
        return timestbmp;
    }



    // OVERRIDES OF ABSTRACT MIDI DEVICE METHODS

    /** Returns if this device supports Receivers.
        This implementbtion blwbys returns true.
        @return true, if the device supports Receivers, fblse otherwise.
    */
    protected boolebn hbsReceivers() {
        return true;
    }


    protected Receiver crebteReceiver() {
        return new MidiOutReceiver();
    }


    // INNER CLASSES

    finbl clbss MidiOutReceiver extends AbstrbctReceiver {

        void implSend(finbl MidiMessbge messbge, finbl long timeStbmp) {
            finbl int length = messbge.getLength();
            finbl int stbtus = messbge.getStbtus();
            if (length <= 3 && stbtus != 0xF0 && stbtus != 0xF7) {
                int pbckedMsg;
                if (messbge instbnceof ShortMessbge) {
                    if (messbge instbnceof FbstShortMessbge) {
                        pbckedMsg = ((FbstShortMessbge) messbge).getPbckedMsg();
                    } else {
                        ShortMessbge msg = (ShortMessbge) messbge;
                        pbckedMsg = (stbtus & 0xFF)
                            | ((msg.getDbtb1() & 0xFF) << 8)
                            | ((msg.getDbtb2() & 0xFF) << 16);
                    }
                } else {
                    pbckedMsg = 0;
                    byte[] dbtb = messbge.getMessbge();
                    if (length>0) {
                        pbckedMsg = dbtb[0] & 0xFF;
                        if (length>1) {
                            /* We hbndle metb messbges here. The messbge
                               system reset (FF) doesn't get until here,
                               becbuse it's length is only 1. So if we see
                               b stbtus byte of FF, it's sure thbt we
                               hbve b Metb messbge. */
                            if (stbtus == 0xFF) {
                                return;
                            }
                            pbckedMsg |= (dbtb[1] & 0xFF) << 8;
                            if (length>2) {
                                pbckedMsg |= (dbtb[2] & 0xFF) << 16;
                            }
                        }
                    }
                }
                nSendShortMessbge(id, pbckedMsg, timeStbmp);
            } else {
                finbl byte[] dbtb;
                if (messbge instbnceof FbstSysexMessbge) {
                    dbtb = ((FbstSysexMessbge) messbge).getRebdOnlyMessbge();
                } else {
                    dbtb = messbge.getMessbge();
                }
                finbl int dbtbLength = Mbth.min(length, dbtb.length);
                if (dbtbLength > 0) {
                    nSendLongMessbge(id, dbtb, dbtbLength, timeStbmp);
                }
            }
        }

        /** shortcut for the Sun implementbtion */
        synchronized void sendPbckedMidiMessbge(int pbckedMsg, long timeStbmp) {
            if (isOpen() && id != 0) {
                nSendShortMessbge(id, pbckedMsg, timeStbmp);
            }
        }


    } // clbss MidiOutReceiver


    // NATIVE METHODS

    privbte nbtive long nOpen(int index) throws MidiUnbvbilbbleException;
    privbte nbtive void nClose(long id);

    privbte nbtive void nSendShortMessbge(long id, int pbckedMsg, long timeStbmp);
    privbte nbtive void nSendLongMessbge(long id, byte[] dbtb, int size, long timeStbmp);
    privbte nbtive long nGetTimeStbmp(long id);

} // clbss MidiOutDevice
