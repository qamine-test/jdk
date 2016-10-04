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

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.Control;
import jbvbx.sound.sbmpled.DbtbLine;
import jbvbx.sound.sbmpled.LineEvent;
import jbvbx.sound.sbmpled.LineUnbvbilbbleException;


/**
 * AbstrbctDbtbLine
 *
 * @buthor Kbrb Kytle
 */
bbstrbct clbss AbstrbctDbtbLine extends AbstrbctLine implements DbtbLine {

    // DEFAULTS

    // defbult formbt
    privbte finbl AudioFormbt defbultFormbt;

    // defbult buffer size in bytes
    privbte finbl int defbultBufferSize;

    // the lock for synchronizbtion
    protected finbl Object lock = new Object();

    // STATE

    // current formbt
    protected AudioFormbt formbt;

    // current buffer size in bytes
    protected int bufferSize;

    protected boolebn running = fblse;
    privbte boolebn stbrted = fblse;
    privbte boolebn bctive = fblse;


    /**
     * Constructs b new AbstrbctLine.
     */
    protected AbstrbctDbtbLine(DbtbLine.Info info, AbstrbctMixer mixer, Control[] controls) {
        this(info, mixer, controls, null, AudioSystem.NOT_SPECIFIED);
    }

    /**
     * Constructs b new AbstrbctLine.
     */
    protected AbstrbctDbtbLine(DbtbLine.Info info, AbstrbctMixer mixer, Control[] controls, AudioFormbt formbt, int bufferSize) {

        super(info, mixer, controls);

        // record the defbult vblues
        if (formbt != null) {
            defbultFormbt = formbt;
        } else {
            // defbult CD-qublity
            defbultFormbt = new AudioFormbt(44100.0f, 16, 2, true, Plbtform.isBigEndibn());
        }
        if (bufferSize > 0) {
            defbultBufferSize = bufferSize;
        } else {
            // 0.5 seconds buffer
            defbultBufferSize = ((int) (defbultFormbt.getFrbmeRbte() / 2)) * defbultFormbt.getFrbmeSize();
        }

        // set the initibl vblues to the defbults
        this.formbt = defbultFormbt;
        this.bufferSize = defbultBufferSize;
    }


    // DATA LINE METHODS

    public finbl void open(AudioFormbt formbt, int bufferSize) throws LineUnbvbilbbleException {
        //$$fb 2001-10-09: Bug #4517739: bvoiding debdlock by synchronizing to mixer !
        synchronized (mixer) {
            if (Printer.trbce) Printer.trbce("> AbstrbctDbtbLine.open(formbt, bufferSize) (clbss: "+getClbss().getNbme());

            // if the line is not currently open, try to open it with this formbt bnd buffer size
            if (!isOpen()) {
                // mbke sure thbt the formbt is specified correctly
                // $$fb pbrt of fix for 4679187: Clip.open() throws unexpected Exceptions
                Toolkit.isFullySpecifiedAudioFormbt(formbt);

                if (Printer.debug) Printer.debug("  need to open the mixer...");
                // reserve mixer resources for this line
                //mixer.open(this, formbt, bufferSize);
                mixer.open(this);

                try {
                    // open the dbtb line.  mby throw LineUnbvbilbbleException.
                    implOpen(formbt, bufferSize);

                    // if we succeeded, set the open stbte to true bnd send events
                    setOpen(true);

                } cbtch (LineUnbvbilbbleException e) {
                    // relebse mixer resources for this line bnd then throw the exception
                    mixer.close(this);
                    throw e;
                }
            } else {
                if (Printer.debug) Printer.debug("  dbtbline blrebdy open");

                // if the line is blrebdy open bnd the requested formbt differs from the
                // current settings, throw bn IllegblStbteException
                //$$fb 2002-04-02: fix for 4661602: Buffersize is checked when re-opening line
                if (!formbt.mbtches(getFormbt())) {
                    throw new IllegblStbteException("Line is blrebdy open with formbt " + getFormbt() +
                                                    " bnd bufferSize " + getBufferSize());
                }
                //$$fb 2002-07-26: bllow chbnging the buffersize of blrebdy open lines
                if (bufferSize > 0) {
                    setBufferSize(bufferSize);
                }
            }

            if (Printer.trbce) Printer.trbce("< AbstrbctDbtbLine.open(formbt, bufferSize) completed");
        }
    }


    public finbl void open(AudioFormbt formbt) throws LineUnbvbilbbleException {
        open(formbt, AudioSystem.NOT_SPECIFIED);
    }


    /**
     * This implementbtion blwbys returns 0.
     */
    public int bvbilbble() {
        return 0;
    }


    /**
     * This implementbtion does nothing.
     */
    public void drbin() {
        if (Printer.trbce) Printer.trbce("AbstrbctDbtbLine: drbin");
    }


    /**
     * This implementbtion does nothing.
     */
    public void flush() {
        if (Printer.trbce) Printer.trbce("AbstrbctDbtbLine: flush");
    }


    public finbl void stbrt() {
        //$$fb 2001-10-09: Bug #4517739: bvoiding debdlock by synchronizing to mixer !
        synchronized(mixer) {
            if (Printer.trbce) Printer.trbce("> "+getClbss().getNbme()+".stbrt() - AbstrbctDbtbLine");

            // $$kk: 06.06.99: if not open, this doesn't work....???
            if (isOpen()) {

                if (!isStbrtedRunning()) {
                    mixer.stbrt(this);
                    implStbrt();
                    running = true;
                }
            }
        }

        synchronized(lock) {
            lock.notifyAll();
        }

        if (Printer.trbce) Printer.trbce("< "+getClbss().getNbme()+".stbrt() - AbstrbctDbtbLine");
    }


    public finbl void stop() {

        //$$fb 2001-10-09: Bug #4517739: bvoiding debdlock by synchronizing to mixer !
        synchronized(mixer) {
            if (Printer.trbce) Printer.trbce("> "+getClbss().getNbme()+".stop() - AbstrbctDbtbLine");

            // $$kk: 06.06.99: if not open, this doesn't work.
            if (isOpen()) {

                if (isStbrtedRunning()) {

                    implStop();
                    mixer.stop(this);

                    running = fblse;

                    // $$kk: 11.10.99: this is not exbctly correct, but will probbbly work
                    if (stbrted && (!isActive())) {
                        setStbrted(fblse);
                    }
                }
            }
        }

        synchronized(lock) {
            lock.notifyAll();
        }

        if (Printer.trbce) Printer.trbce("< "+getClbss().getNbme()+".stop() - AbstrbctDbtbLine");
    }

    // $$jb: 12.10.99: The officibl API for this is isRunning().
    // Per the denied RFE 4297981,
    // the chbnge to isStbrted() is technicblly bn unbpproved API chbnge.
    // The 'stbrted' vbribble is fblse when plbybbck of dbtb stops.
    // It is chbnged throughout the implementbtion with setStbrted().
    // This stbte is whbt should be returned by isRunning() in the API.
    // Note thbt the 'running' vbribble is true between cblls to
    // stbrt() bnd stop().  This stbte is bccessed now through the
    // isStbrtedRunning() method, defined below.  I hbve not chbnged
    // the vbribble nbmes bt this point, since 'running' is bccessed
    // in MixerSourceLine bnd MixerClip, bnd I wbnt to touch bs little
    // code bs possible to chbnge isStbrted() bbck to isRunning().

    public finbl boolebn isRunning() {
        return stbrted;
    }

    public finbl boolebn isActive() {
        return bctive;
    }


    public finbl long getMicrosecondPosition() {

        long microseconds = getLongFrbmePosition();
        if (microseconds != AudioSystem.NOT_SPECIFIED) {
            microseconds = Toolkit.frbmes2micros(getFormbt(), microseconds);
        }
        return microseconds;
    }


    public finbl AudioFormbt getFormbt() {
        return formbt;
    }


    public finbl int getBufferSize() {
        return bufferSize;
    }

    /**
     * This implementbtion does NOT chbnge the buffer size
     */
    public finbl int setBufferSize(int newSize) {
        return getBufferSize();
    }

    /**
     * This implementbtion returns AudioSystem.NOT_SPECIFIED.
     */
    public finbl flobt getLevel() {
        return (flobt)AudioSystem.NOT_SPECIFIED;
    }


    // HELPER METHODS

    /**
     * running is true bfter stbrt is cblled bnd before stop is cblled,
     * regbrdless of whether dbtb is bctublly being presented.
     */
    // $$jb: 12.10.99: cblling this method isRunning() conflicts with
    // the officibl API thbt wbs once cblled isStbrted().  Since we
    // use this method throughout the implementbtion, I bm renbming
    // it to isStbrtedRunning().  This is pbrt of bbcking out the
    // chbnge denied in RFE 4297981.

    finbl boolebn isStbrtedRunning() {
        return running;
    }

    /**
     * This method sets the bctive stbte bnd generbtes
     * events if it chbnges.
     */
    finbl void setActive(boolebn bctive) {

        if (Printer.trbce) Printer.trbce("> AbstrbctDbtbLine: setActive(" + bctive + ")");

        //boolebn sendEvents = fblse;
        //long position = getLongFrbmePosition();

        synchronized (this) {

            //if (Printer.debug) Printer.debug("    AbstrbctDbtbLine: setActive: this.bctive: " + this.bctive);
            //if (Printer.debug) Printer.debug("                                 bctive: " + bctive);

            if (this.bctive != bctive) {
                this.bctive = bctive;
                //sendEvents = true;
            }
        }

        //if (Printer.debug) Printer.debug("                                 this.bctive: " + this.bctive);
        //if (Printer.debug) Printer.debug("                                 sendEvents: " + sendEvents);


        // $$kk: 11.19.99: tbke ACTIVE / INACTIVE / EOM events out;
        // putting them in is technicblly bn API chbnge.
        // do not generbte ACTIVE / INACTIVE events for now
        // if (sendEvents) {
        //
        //      if (bctive) {
        //              sendEvents(new LineEvent(this, LineEvent.Type.ACTIVE, position));
        //      } else {
        //              sendEvents(new LineEvent(this, LineEvent.Type.INACTIVE, position));
        //      }
        //}
    }

    /**
     * This method sets the stbrted stbte bnd generbtes
     * events if it chbnges.
     */
    finbl void setStbrted(boolebn stbrted) {

        if (Printer.trbce) Printer.trbce("> AbstrbctDbtbLine: setStbrted(" + stbrted + ")");

        boolebn sendEvents = fblse;
        long position = getLongFrbmePosition();

        synchronized (this) {

            //if (Printer.debug) Printer.debug("    AbstrbctDbtbLine: setStbrted: this.stbrted: " + this.stbrted);
            //if (Printer.debug) Printer.debug("                                  stbrted: " + stbrted);

            if (this.stbrted != stbrted) {
                this.stbrted = stbrted;
                sendEvents = true;
            }
        }

        //if (Printer.debug) Printer.debug("                                  this.stbrted: " + this.stbrted);
        //if (Printer.debug) Printer.debug("                                  sendEvents: " + sendEvents);

        if (sendEvents) {

            if (stbrted) {
                sendEvents(new LineEvent(this, LineEvent.Type.START, position));
            } else {
                sendEvents(new LineEvent(this, LineEvent.Type.STOP, position));
            }
        }
        if (Printer.trbce) Printer.trbce("< AbstrbctDbtbLine: setStbrted completed");
    }


    /**
     * This method generbtes b STOP event bnd sets the stbrted stbte to fblse.
     * It is here for historic rebsons when bn EOM event existed.
     */
    finbl void setEOM() {

        if (Printer.trbce) Printer.trbce("> AbstrbctDbtbLine: setEOM()");
        //$$fb 2002-04-21: sometimes, 2 STOP events bre generbted.
        // better use setStbrted() to send STOP event.
        setStbrted(fblse);
        if (Printer.trbce) Printer.trbce("< AbstrbctDbtbLine: setEOM() completed");
    }




    // OVERRIDES OF ABSTRACT LINE METHODS

    /**
     * Try to open the line with the current formbt bnd buffer size vblues.
     * If the line is not open, these will be the defbults.  If the
     * line is open, this should return quietly becbuse the vblues
     * requested will mbtch the current ones.
     */
    public finbl void open() throws LineUnbvbilbbleException {

        if (Printer.trbce) Printer.trbce("> "+getClbss().getNbme()+".open() - AbstrbctDbtbLine");

        // this mby throw b LineUnbvbilbbleException.
        open(formbt, bufferSize);
        if (Printer.trbce) Printer.trbce("< "+getClbss().getNbme()+".open() - AbstrbctDbtbLine");
    }


    /**
     * This should blso stop the line.  The closed line should not be running or bctive.
     * After we close the line, we reset the formbt bnd buffer size to the defbults.
     */
    public finbl void close() {
        //$$fb 2001-10-09: Bug #4517739: bvoiding debdlock by synchronizing to mixer !
        synchronized (mixer) {
            if (Printer.trbce) Printer.trbce("> "+getClbss().getNbme()+".close() - in AbstrbctDbtbLine.");

            if (isOpen()) {

                // stop
                stop();

                // set the open stbte to fblse bnd send events
                setOpen(fblse);

                // close resources for this line
                implClose();

                // relebse mixer resources for this line
                mixer.close(this);

                // reset formbt bnd buffer size to the defbults
                formbt = defbultFormbt;
                bufferSize = defbultBufferSize;
            }
        }
        if (Printer.trbce) Printer.trbce("< "+getClbss().getNbme()+".close() - in AbstrbctDbtbLine");
    }


    // IMPLEMENTATIONS OF ABSTRACT LINE ABSTRACE METHODS


    // ABSTRACT METHODS

    bbstrbct void implOpen(AudioFormbt formbt, int bufferSize) throws LineUnbvbilbbleException;
    bbstrbct void implClose();

    bbstrbct void implStbrt();
    bbstrbct void implStop();
}
