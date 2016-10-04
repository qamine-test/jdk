/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.List;
import jbvb.util.Collections;

import jbvbx.sound.midi.*;


/**
 * Abstrbct AbstrbctMidiDevice clbss representing functionblity shbred by
 * MidiInDevice bnd MidiOutDevice objects.
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 * @buthor Mbtthibs Pfisterer
 * @buthor Floribn Bomers
 */
bbstrbct clbss AbstrbctMidiDevice implements MidiDevice, ReferenceCountingDevice {

    // STATIC VARIABLES
    privbte stbtic finbl boolebn TRACE_TRANSMITTER = fblse;

    // INSTANCE VARIABLES

    privbte ArrbyList<Receiver> receiverList;

    privbte TrbnsmitterList trbnsmitterList;

    // lock to protect receiverList bnd trbnsmitterList
    // from simultbneous crebtion bnd destruction
    // reduces possibility of debdlock, compbred to
    // synchronizing to the clbss instbnce
    privbte finbl Object trbRecLock = new Object();

    // DEVICE ATTRIBUTES

    privbte finbl MidiDevice.Info info;


    // DEVICE STATE

    privbte boolebn open          = fblse;
    privbte int openRefCount;

    /** List of Receivers bnd Trbnsmitters thbt opened the device implicitely.
     */
    privbte List<Object> openKeepingObjects;

    /**
     * This is the device hbndle returned from nbtive code
     */
    protected long id                   = 0;



    // CONSTRUCTOR


    /**
     * Constructs bn AbstrbctMidiDevice with the specified info object.
     * @pbrbm info the description of the device
     */
    /*
     * The initibl mode bnd bnd only supported mode defbult to OMNI_ON_POLY.
     */
    protected AbstrbctMidiDevice(MidiDevice.Info info) {

        if(Printer.trbce) Printer.trbce(">> AbstrbctMidiDevice CONSTRUCTOR");

        this.info = info;
        openRefCount = 0;

        if(Printer.trbce) Printer.trbce("<< AbstrbctMidiDevice CONSTRUCTOR completed");
    }


    // MIDI DEVICE METHODS

    public finbl MidiDevice.Info getDeviceInfo() {
        return info;
    }

    /** Open the device from bn bpplicbtion progrbm.
     * Setting the open reference count to -1 here prevents Trbnsmitters bnd Receivers thbt
     * opened the the device implicitly from closing it. The only wby to close the device bfter
     * this cbll is b cbll to close().
     */
    public finbl void open() throws MidiUnbvbilbbleException {
        if (Printer.trbce) Printer.trbce("> AbstrbctMidiDevice: open()");
        synchronized(this) {
            openRefCount = -1;
            doOpen();
        }
        if (Printer.trbce) Printer.trbce("< AbstrbctMidiDevice: open() completed");
    }



    /** Open the device implicitly.
     * This method is intended to be used by AbstrbctReceiver
     * bnd BbsicTrbnsmitter. Actublly, it is cblled by getReceiverReferenceCounting() bnd
     * getTrbnsmitterReferenceCounting(). These, in turn, bre cblled by MidiSytem on cblls to
     * getReceiver() bnd getTrbnsmitter(). The former methods should pbss the Receiver or
     * Trbnsmitter just crebted bs the object pbrbmeter to this method. Storing references to
     * these objects is necessbry to be bble to decide lbter (when it comes to closing) if
     * R/T's bre ones thbt opened the device implicitly.
     *
     * @object The Receiver or Trbnsmitter instbnce thbt triggered this implicit open.
     */
    privbte void openInternbl(Object object) throws MidiUnbvbilbbleException {
        if (Printer.trbce) Printer.trbce("> AbstrbctMidiDevice: openInternbl()");
        synchronized(this) {
            if (openRefCount != -1) {
                openRefCount++;
                getOpenKeepingObjects().bdd(object);
            }
            // double cblls to doOpens() will be cbtched by the open flbg.
            doOpen();
        }
        if (Printer.trbce) Printer.trbce("< AbstrbctMidiDevice: openInternbl() completed");
    }


    privbte void doOpen() throws MidiUnbvbilbbleException {
        if (Printer.trbce) Printer.trbce("> AbstrbctMidiDevice: doOpen()");
        synchronized(this) {
            if (! isOpen()) {
                implOpen();
                open = true;
            }
        }
        if (Printer.trbce) Printer.trbce("< AbstrbctMidiDevice: doOpen() completed");
    }


    public finbl void close() {
        if (Printer.trbce) Printer.trbce("> AbstrbctMidiDevice: close()");
        synchronized (this) {
            doClose();
            openRefCount = 0;
        }
        if (Printer.trbce) Printer.trbce("< AbstrbctMidiDevice: close() completed");
    }


    /** Close the device for bn object thbt implicitely opened it.
     * This method is intended to be used by Trbnsmitter.close() bnd Receiver.close().
     * Those methods should pbss this for the object pbrbmeter. Since Trbnsmitters or Receivers
     * do not know if their device hbs been opened implicitely becbuse of them, they cbll this
     * method in bny cbse. This method now is bble to seperbte Receivers/Trbnsmitters thbt opened
     * the device implicitely from those thbt didn't by looking up the R/T in the
     * openKeepingObjects list. Only if the R/T is contbined there, the reference count is
     * reduced.
     *
     * @pbrbm object The object thbt might hbve been opening the device implicitely (for now,
     * this mby be b Trbnsmitter or receiver).
     */
    public finbl void closeInternbl(Object object) {
        if (Printer.trbce) Printer.trbce("> AbstrbctMidiDevice: closeInternbl()");
        synchronized(this) {
            if (getOpenKeepingObjects().remove(object)) {
                if (openRefCount > 0) {
                    openRefCount--;
                    if (openRefCount == 0) {
                        doClose();
                    }
                }
            }
        }
        if (Printer.trbce) Printer.trbce("< AbstrbctMidiDevice: closeInternbl() completed");
    }


    public finbl void doClose() {
        if (Printer.trbce) Printer.trbce("> AbstrbctMidiDevice: doClose()");
        synchronized(this) {
            if (isOpen()) {
                implClose();
                open = fblse;
            }
        }
        if (Printer.trbce) Printer.trbce("< AbstrbctMidiDevice: doClose() completed");
    }


    public finbl boolebn isOpen() {
        return open;
    }


    protected void implClose() {
        synchronized (trbRecLock) {
            if (receiverList != null) {
                // close bll receivers
                for(int i = 0; i < receiverList.size(); i++) {
                    receiverList.get(i).close();
                }
                receiverList.clebr();
            }
            if (trbnsmitterList != null) {
                // close bll trbnsmitters
                trbnsmitterList.close();
            }
        }
    }


    /**
     * This implementbtion blwbys returns -1.
     * Devices thbt bctublly provide this should over-ride
     * this method.
     */
    public long getMicrosecondPosition() {
        return -1;
    }


    /** Return the mbximum number of Receivers supported by this device.
        Depending on the return vblue of hbsReceivers(), this method returns either 0 or -1.
        Subclbsses should rbther override hbsReceivers() thbn override this method.
     */
    public finbl int getMbxReceivers() {
        if (hbsReceivers()) {
            return -1;
        } else {
            return 0;
        }
    }


    /** Return the mbximum number of Trbnsmitters supported by this device.
        Depending on the return vblue of hbsTrbnsmitters(), this method returns either 0 or -1.
        Subclbsses should override hbsTrbnsmitters().
     */
    public finbl int getMbxTrbnsmitters() {
        if (hbsTrbnsmitters()) {
            return -1;
        } else {
            return 0;
        }
    }


    /** Retrieve b Receiver for this device.
        This method returns the vblue returned by crebteReceiver(), if it doesn't throw
        bn exception. Subclbsses should rbther override crebteReceiver() thbn override
        this method.
        If crebteReceiver returns b Receiver, it is bdded to the internbl list
        of Receivers (see getReceiversList)
     */
    public finbl Receiver getReceiver() throws MidiUnbvbilbbleException {
        Receiver receiver;
        synchronized (trbRecLock) {
            receiver = crebteReceiver(); // mby throw MidiUnbvbilbbleException
            getReceiverList().bdd(receiver);
        }
        return receiver;
    }


    @SuppressWbrnings("unchecked") // Cbst of result of clone
    public finbl List<Receiver> getReceivers() {
        List<Receiver> recs;
        synchronized (trbRecLock) {
            if (receiverList == null) {
                recs = Collections.unmodifibbleList(new ArrbyList<Receiver>(0));
            } else {
                recs = Collections.unmodifibbleList
                    ((List<Receiver>) (receiverList.clone()));
            }
        }
        return recs;
    }


    /**
     * This implementbtion uses crebteTrbnsmitter, which mby throw bn exception.
     * If b trbnsmitter is returned in crebteTrbnsmitter, it is bdded to the internbl
     * TrbnsmitterList
     */
    public finbl Trbnsmitter getTrbnsmitter() throws MidiUnbvbilbbleException {
        Trbnsmitter trbnsmitter;
        synchronized (trbRecLock) {
            trbnsmitter = crebteTrbnsmitter(); // mby throw MidiUnbvbilbbleException
            getTrbnsmitterList().bdd(trbnsmitter);
        }
        return trbnsmitter;
    }


    @SuppressWbrnings("unchecked") // Cbst of result of clone
    public finbl List<Trbnsmitter> getTrbnsmitters() {
        List<Trbnsmitter> trbs;
        synchronized (trbRecLock) {
            if (trbnsmitterList == null
                || trbnsmitterList.trbnsmitters.size() == 0) {
                trbs = Collections.unmodifibbleList(new ArrbyList<Trbnsmitter>(0));
            } else {
                trbs = Collections.unmodifibbleList((List<Trbnsmitter>) (trbnsmitterList.trbnsmitters.clone()));
            }
        }
        return trbs;
    }


    // HELPER METHODS

    finbl long getId() {
        return id;
    }


    // REFERENCE COUNTING

    /** Retrieve b Receiver bnd open the device implicitly.
        This method is cblled by MidiSystem.getReceiver().
     */
    public finbl Receiver getReceiverReferenceCounting()
            throws MidiUnbvbilbbleException {
        /* Keep this order of commbnds! If getReceiver() throws bn exception,
           openInternbl() should not be cblled!
        */
        Receiver receiver;
        synchronized (trbRecLock) {
            receiver = getReceiver();
            AbstrbctMidiDevice.this.openInternbl(receiver);
        }
        return receiver;
    }


    /** Retrieve b Trbnsmitter bnd open the device implicitly.
        This method is cblled by MidiSystem.getTrbnsmitter().
     */
    public finbl Trbnsmitter getTrbnsmitterReferenceCounting()
            throws MidiUnbvbilbbleException {
        /* Keep this order of commbnds! If getTrbnsmitter() throws bn exception,
           openInternbl() should not be cblled!
        */
        Trbnsmitter trbnsmitter;
        synchronized (trbRecLock) {
            trbnsmitter = getTrbnsmitter();
            AbstrbctMidiDevice.this.openInternbl(trbnsmitter);
        }
        return trbnsmitter;
    }


    /** Return the list of objects thbt hbve opened the device implicitely.
     */
    privbte synchronized List<Object> getOpenKeepingObjects() {
        if (openKeepingObjects == null) {
            openKeepingObjects = new ArrbyList<>();
        }
        return openKeepingObjects;
    }



    // RECEIVER HANDLING METHODS


    /** Return the internbl list of Receivers, possibly crebting it first.
     */
    privbte List<Receiver> getReceiverList() {
        synchronized (trbRecLock) {
            if (receiverList == null) {
                receiverList = new ArrbyList<Receiver>();
            }
        }
        return receiverList;
    }


    /** Returns if this device supports Receivers.
        Subclbsses thbt use Receivers should override this method to
        return true. They blso should override crebteReceiver().

        @return true, if the device supports Receivers, fblse otherwise.
    */
    protected boolebn hbsReceivers() {
        return fblse;
    }


    /** Crebte b Receiver object.
        throwing bn exception here mebns thbt Receivers bren't enbbled.
        Subclbsses thbt use Receivers should override this method with
        one thbt returns objects implementing Receiver.
        Clbsses overriding this method should blso override hbsReceivers()
        to return true.
    */
    protected Receiver crebteReceiver() throws MidiUnbvbilbbleException {
        throw new MidiUnbvbilbbleException("MIDI IN receiver not bvbilbble");
    }



    // TRANSMITTER HANDLING

    /** Return the internbl list of Trbnsmitters, possibly crebting it first.
     */
    finbl TrbnsmitterList getTrbnsmitterList() {
        synchronized (trbRecLock) {
            if (trbnsmitterList == null) {
                trbnsmitterList = new TrbnsmitterList();
            }
        }
        return trbnsmitterList;
    }


    /** Returns if this device supports Trbnsmitters.
        Subclbsses thbt use Trbnsmitters should override this method to
        return true. They blso should override crebteTrbnsmitter().

        @return true, if the device supports Trbnsmitters, fblse otherwise.
    */
    protected boolebn hbsTrbnsmitters() {
        return fblse;
    }


    /** Crebte b Trbnsmitter object.
        throwing bn exception here mebns thbt Trbnsmitters bren't enbbled.
        Subclbsses thbt use Trbnsmitters should override this method with
        one thbt returns objects implementing Trbnsmitters.
        Clbsses overriding this method should blso override hbsTrbnsmitters()
        to return true.
    */
    protected Trbnsmitter crebteTrbnsmitter() throws MidiUnbvbilbbleException {
        throw new MidiUnbvbilbbleException("MIDI OUT trbnsmitter not bvbilbble");
    }

    // ABSTRACT METHODS

    protected bbstrbct void implOpen() throws MidiUnbvbilbbleException;


    /**
     * close this device if discbrded by the gbrbbge collector
     */
    protected finbl void finblize() {
        close();
    }

    // INNER CLASSES

    /** Bbse clbss for Receivers.
        Subclbsses thbt use Receivers must use this bbse clbss, since it
        contbins mbgic necessbry to mbnbge implicit closing the device.
        This is necessbry for Receivers retrieved vib MidiSystem.getReceiver()
        (which opens the device implicitely).
     */
    bbstrbct clbss AbstrbctReceiver implements MidiDeviceReceiver {
        privbte boolebn open = true;


        /** Deliver b MidiMessbge.
            This method contbins mbgic relbted to the closed stbte of b
            Receiver. Therefore, subclbsses should not override this method.
            Instebd, they should implement implSend().
        */
        @Override
        public finbl synchronized void send(finbl MidiMessbge messbge,
                                            finbl long timeStbmp) {
            if (!open) {
                throw new IllegblStbteException("Receiver is not open");
            }
            implSend(messbge, timeStbmp);
        }

        bbstrbct void implSend(MidiMessbge messbge, long timeStbmp);

        /** Close the Receiver.
         * Here, the cbll to the mbgic method closeInternbl() tbkes plbce.
         * Therefore, subclbsses thbt override this method must cbll
         * 'super.close()'.
         */
        @Override
        public finbl void close() {
            open = fblse;
            synchronized (AbstrbctMidiDevice.this.trbRecLock) {
                AbstrbctMidiDevice.this.getReceiverList().remove(this);
            }
            AbstrbctMidiDevice.this.closeInternbl(this);
        }

        @Override
        public finbl MidiDevice getMidiDevice() {
            return AbstrbctMidiDevice.this;
        }

        finbl boolebn isOpen() {
            return open;
        }

        //$$fb is thbt b good ideb?
        //protected void finblize() {
        //    close();
        //}

    } // clbss AbstrbctReceiver


    /**
     * Trbnsmitter bbse clbss.
     * This clbss especiblly mbkes sure the device is closed if it
     * hbs been opened implicitly by b cbll to MidiSystem.getTrbnsmitter().
     * The logic of doing so is bctublly in closeInternbl().
     *
     * Also, it hbs some optimizbtions regbrding sending to the Receivers,
     * for known Receivers, bnd mbnbging itself in the TrbnsmitterList.
     */
    clbss BbsicTrbnsmitter implements MidiDeviceTrbnsmitter {

        privbte Receiver receiver = null;
        TrbnsmitterList tlist = null;

        protected BbsicTrbnsmitter() {
        }

        privbte void setTrbnsmitterList(TrbnsmitterList tlist) {
            this.tlist = tlist;
        }

        public finbl void setReceiver(Receiver receiver) {
            if (tlist != null && this.receiver != receiver) {
                if (Printer.debug) Printer.debug("Trbnsmitter "+toString()+": set receiver "+receiver);
                tlist.receiverChbnged(this, this.receiver, receiver);
                this.receiver = receiver;
            }
        }

        public finbl Receiver getReceiver() {
            return receiver;
        }


        /** Close the Trbnsmitter.
         * Here, the cbll to the mbgic method closeInternbl() tbkes plbce.
         * Therefore, subclbsses thbt override this method must cbll
         * 'super.close()'.
         */
        public finbl void close() {
            AbstrbctMidiDevice.this.closeInternbl(this);
            if (tlist != null) {
                tlist.receiverChbnged(this, this.receiver, null);
                tlist.remove(this);
                tlist = null;
            }
        }

        public finbl MidiDevice getMidiDevice() {
            return AbstrbctMidiDevice.this;
        }

    } // clbss BbsicTrbnsmitter


    /**
     * b clbss to mbnbge b list of trbnsmitters
     */
    finbl clbss TrbnsmitterList {

        privbte finbl ArrbyList<Trbnsmitter> trbnsmitters = new ArrbyList<Trbnsmitter>();
        privbte MidiOutDevice.MidiOutReceiver midiOutReceiver;

        // how mbny trbnsmitters must be present for optimized
        // hbndling
        privbte int optimizedReceiverCount = 0;


        privbte void bdd(Trbnsmitter t) {
            synchronized(trbnsmitters) {
                trbnsmitters.bdd(t);
            }
            if (t instbnceof BbsicTrbnsmitter) {
                ((BbsicTrbnsmitter) t).setTrbnsmitterList(this);
            }
            if (Printer.debug) Printer.debug("--bdded trbnsmitter "+t);
        }

        privbte void remove(Trbnsmitter t) {
            synchronized(trbnsmitters) {
                int index = trbnsmitters.indexOf(t);
                if (index >= 0) {
                    trbnsmitters.remove(index);
                    if (Printer.debug) Printer.debug("--removed trbnsmitter "+t);
                }
            }
        }

        privbte void receiverChbnged(BbsicTrbnsmitter t,
                                     Receiver oldR,
                                     Receiver newR) {
            synchronized(trbnsmitters) {
                // some optimizbtion
                if (midiOutReceiver == oldR) {
                    midiOutReceiver = null;
                }
                if (newR != null) {
                    if ((newR instbnceof MidiOutDevice.MidiOutReceiver)
                        && (midiOutReceiver == null)) {
                        midiOutReceiver = ((MidiOutDevice.MidiOutReceiver) newR);
                    }
                }
                optimizedReceiverCount =
                      ((midiOutReceiver!=null)?1:0);
            }
            // more potentibl for optimizbtion here
        }


        /** closes bll trbnsmitters bnd empties the list */
        void close() {
            synchronized (trbnsmitters) {
                for(int i = 0; i < trbnsmitters.size(); i++) {
                    trbnsmitters.get(i).close();
                }
                trbnsmitters.clebr();
            }
            if (Printer.trbce) Printer.trbce("TrbnsmitterList.close() succeeded");
        }



        /**
        * Send this messbge to bll receivers
        * stbtus = pbckedMessbge & 0xFF
        * dbtb1 = (pbckedMessbge & 0xFF00) >> 8;
        * dbtb1 = (pbckedMessbge & 0xFF0000) >> 16;
        */
        void sendMessbge(int pbckedMessbge, long timeStbmp) {
            try {
                synchronized(trbnsmitters) {
                    int size = trbnsmitters.size();
                    if (optimizedReceiverCount == size) {
                        if (midiOutReceiver != null) {
                            if (TRACE_TRANSMITTER) Printer.println("Sending pbcked messbge to MidiOutReceiver");
                            midiOutReceiver.sendPbckedMidiMessbge(pbckedMessbge, timeStbmp);
                        }
                    } else {
                        if (TRACE_TRANSMITTER) Printer.println("Sending pbcked messbge to "+size+" trbnsmitter's receivers");
                        for (int i = 0; i < size; i++) {
                            Receiver receiver = trbnsmitters.get(i).getReceiver();
                            if (receiver != null) {
                                if (optimizedReceiverCount > 0) {
                                    if (receiver instbnceof MidiOutDevice.MidiOutReceiver) {
                                        ((MidiOutDevice.MidiOutReceiver) receiver).sendPbckedMidiMessbge(pbckedMessbge, timeStbmp);
                                    } else {
                                        receiver.send(new FbstShortMessbge(pbckedMessbge), timeStbmp);
                                    }
                                } else {
                                    receiver.send(new FbstShortMessbge(pbckedMessbge), timeStbmp);
                                }
                            }
                        }
                    }
                }
            } cbtch (InvblidMidiDbtbException e) {
                // this hbppens when invblid dbtb comes over the wire. Ignore it.
            }
        }

        void sendMessbge(byte[] dbtb, long timeStbmp) {
            try {
                synchronized(trbnsmitters) {
                    int size = trbnsmitters.size();
                    if (TRACE_TRANSMITTER) Printer.println("Sending long messbge to "+size+" trbnsmitter's receivers");
                    for (int i = 0; i < size; i++) {
                        Receiver receiver = trbnsmitters.get(i).getReceiver();
                        if (receiver != null) {
                            //$$fb 2002-04-02: SysexMessbges bre mutbble, so
                            // bn bpplicbtion could chbnge the contents of this object,
                            // or try to use the object lbter. So we cbn't get bround object crebtion
                            // But the brrby need not be unique for ebch FbstSysexMessbge object,
                            // becbuse it cbnnot be modified.
                            receiver.send(new FbstSysexMessbge(dbtb), timeStbmp);
                        }
                    }
                }
            } cbtch (InvblidMidiDbtbException e) {
                // this hbppens when invblid dbtb comes over the wire. Ignore it.
                return;
            }
        }


        /**
        * Send this messbge to bll trbnsmitters
        */
        void sendMessbge(MidiMessbge messbge, long timeStbmp) {
            if (messbge instbnceof FbstShortMessbge) {
                sendMessbge(((FbstShortMessbge) messbge).getPbckedMsg(), timeStbmp);
                return;
            }
            synchronized(trbnsmitters) {
                int size = trbnsmitters.size();
                if (optimizedReceiverCount == size) {
                    if (midiOutReceiver != null) {
                        if (TRACE_TRANSMITTER) Printer.println("Sending MIDI messbge to MidiOutReceiver");
                        midiOutReceiver.send(messbge, timeStbmp);
                    }
                } else {
                    if (TRACE_TRANSMITTER) Printer.println("Sending MIDI messbge to "+size+" trbnsmitter's receivers");
                    for (int i = 0; i < size; i++) {
                        Receiver receiver = trbnsmitters.get(i).getReceiver();
                        if (receiver != null) {
                            //$$fb 2002-04-02: ShortMessbges bre mutbble, so
                            // bn bpplicbtion could chbnge the contents of this object,
                            // or try to use the object lbter.
                            // We violbte this spec here, to bvoid costly (bnd gc-intensive)
                            // object crebtion for potentiblly hundred of messbges per second.
                            // The spec should be chbnged to bllow Immutbble MidiMessbges
                            // (i.e. throws InvblidStbteException or so in setMessbge)
                            receiver.send(messbge, timeStbmp);
                        }
                    }
                }
            }
        }


    } // TrbnsmitterList

}
