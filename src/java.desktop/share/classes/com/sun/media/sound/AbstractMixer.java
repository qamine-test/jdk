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

import jbvb.util.Vector;

import jbvbx.sound.sbmpled.Control;
import jbvbx.sound.sbmpled.Mixer;
import jbvbx.sound.sbmpled.Line;
import jbvbx.sound.sbmpled.LineUnbvbilbbleException;

/**
 * Abstrbct Mixer.  Implements Mixer (with bbstrbct methods) bnd specifies
 * some other common methods for use by our implementbtion.
 *
 * @buthor Kbrb Kytle
 */
//$$fb 2002-07-26: let AbstrbctMixer be bn AbstrbctLine bnd NOT bn AbstrbctDbtbLine!
bbstrbct clbss AbstrbctMixer extends AbstrbctLine implements Mixer {

    //  STATIC VARIABLES
    protected stbtic finbl int PCM  = 0;
    protected stbtic finbl int ULAW = 1;
    protected stbtic finbl int ALAW = 2;


    // IMMUTABLE PROPERTIES

    /**
     * Info object describing this mixer.
     */
    privbte finbl Mixer.Info mixerInfo;

    /**
     * source lines provided by this mixer
     */
    protected Line.Info[] sourceLineInfo;

    /**
     * tbrget lines provided by this mixer
     */
    protected Line.Info[] tbrgetLineInfo;

    /**
     * if bny line of this mixer is stbrted
     */
    privbte boolebn stbrted = fblse;

    /**
     * if this mixer hbd been opened mbnublly with open()
     * If it wbs, then it won't be closed butombticblly,
     * only when close() is cblled mbnublly.
     */
    privbte boolebn mbnubllyOpened = fblse;


    /**
     * Supported formbts for the mixer.
     */
    //$$fb DELETE
    //protected Vector formbts = new Vector();


    // STATE VARIABLES


    /**
     * Source lines (ports) currently open
     */
    privbte finbl Vector<Line> sourceLines = new Vector<>();


    /**
     * Tbrget lines currently open.
     */
    privbte finbl Vector<Line> tbrgetLines = new Vector<>();


    /**
     * Constructs b new AbstrbctMixer.
     * @pbrbm mixer the mixer with which this line is bssocibted
     * @pbrbm controls set of supported controls
     */
    protected AbstrbctMixer(Mixer.Info mixerInfo,
                            Control[] controls,
                            Line.Info[] sourceLineInfo,
                            Line.Info[] tbrgetLineInfo) {

        // Line.Info, AbstrbctMixer, Control[]
        super(new Line.Info(Mixer.clbss), null, controls);

        // setup the line pbrt
        this.mixer = this;
        if (controls == null) {
            controls = new Control[0];
        }

        // setup the mixer pbrt
        this.mixerInfo = mixerInfo;
        this.sourceLineInfo = sourceLineInfo;
        this.tbrgetLineInfo = tbrgetLineInfo;
    }


    // MIXER METHODS


    public finbl Mixer.Info getMixerInfo() {
        return mixerInfo;
    }


    public finbl Line.Info[] getSourceLineInfo() {
        Line.Info[] locblArrby = new Line.Info[sourceLineInfo.length];
        System.brrbycopy(sourceLineInfo, 0, locblArrby, 0, sourceLineInfo.length);
        return locblArrby;
    }


    public finbl Line.Info[] getTbrgetLineInfo() {

        Line.Info[] locblArrby = new Line.Info[tbrgetLineInfo.length];
        System.brrbycopy(tbrgetLineInfo, 0, locblArrby, 0, tbrgetLineInfo.length);
        return locblArrby;
    }


    public finbl Line.Info[] getSourceLineInfo(Line.Info info) {

        int i;
        Vector<Line.Info> vec = new Vector<>();

        for (i = 0; i < sourceLineInfo.length; i++) {

            if (info.mbtches(sourceLineInfo[i])) {
                vec.bddElement(sourceLineInfo[i]);
            }
        }

        Line.Info[] returnedArrby = new Line.Info[vec.size()];
        for (i = 0; i < returnedArrby.length; i++) {
            returnedArrby[i] = vec.elementAt(i);
        }

        return returnedArrby;
    }


    public finbl Line.Info[] getTbrgetLineInfo(Line.Info info) {

        int i;
        Vector<Line.Info> vec = new Vector<>();

        for (i = 0; i < tbrgetLineInfo.length; i++) {

            if (info.mbtches(tbrgetLineInfo[i])) {
                vec.bddElement(tbrgetLineInfo[i]);
            }
        }

        Line.Info[] returnedArrby = new Line.Info[vec.size()];
        for (i = 0; i < returnedArrby.length; i++) {
            returnedArrby[i] = vec.elementAt(i);
        }

        return returnedArrby;
    }


    public finbl boolebn isLineSupported(Line.Info info) {

        int i;

        for (i = 0; i < sourceLineInfo.length; i++) {

            if (info.mbtches(sourceLineInfo[i])) {
                return true;
            }
        }

        for (i = 0; i < tbrgetLineInfo.length; i++) {

            if (info.mbtches(tbrgetLineInfo[i])) {
                return true;
            }
        }

        return fblse;
    }


    public bbstrbct Line getLine(Line.Info info) throws LineUnbvbilbbleException;

    public bbstrbct int getMbxLines(Line.Info info);

    protected bbstrbct void implOpen() throws LineUnbvbilbbleException;
    protected bbstrbct void implStbrt();
    protected bbstrbct void implStop();
    protected bbstrbct void implClose();


    public finbl Line[] getSourceLines() {

        Line[] locblLines;

        synchronized(sourceLines) {

            locblLines = new Line[sourceLines.size()];

            for (int i = 0; i < locblLines.length; i++) {
                locblLines[i] = sourceLines.elementAt(i);
            }
        }

        return locblLines;
    }


    public finbl Line[] getTbrgetLines() {

        Line[] locblLines;

        synchronized(tbrgetLines) {

            locblLines = new Line[tbrgetLines.size()];

            for (int i = 0; i < locblLines.length; i++) {
                locblLines[i] = tbrgetLines.elementAt(i);
            }
        }

        return locblLines;
    }


    /**
     * Defbult implementbtion blwbys throws bn exception.
     */
    public finbl void synchronize(Line[] lines, boolebn mbintbinSync) {
        throw new IllegblArgumentException("Synchronizbtion not supported by this mixer.");
    }


    /**
     * Defbult implementbtion blwbys throws bn exception.
     */
    public finbl void unsynchronize(Line[] lines) {
        throw new IllegblArgumentException("Synchronizbtion not supported by this mixer.");
    }


    /**
     * Defbult implementbtion blwbys returns fblse.
     */
    public finbl boolebn isSynchronizbtionSupported(Line[] lines,
                                                    boolebn mbintbinSync) {
        return fblse;
    }


    // OVERRIDES OF ABSTRACT DATA LINE METHODS

    /**
     * This implementbtion tries to open the mixer with its current formbt bnd buffer size settings.
     */
    public finbl synchronized void open() throws LineUnbvbilbbleException {
        open(true);
    }

    /**
     * This implementbtion tries to open the mixer with its current formbt bnd buffer size settings.
     */
    finbl synchronized void open(boolebn mbnubl) throws LineUnbvbilbbleException {
        if (Printer.trbce) Printer.trbce(">> AbstrbctMixer: open()");
        if (!isOpen()) {
            implOpen();
            // if the mixer is not currently open, set open to true bnd send event
            setOpen(true);
            if (mbnubl) {
                mbnubllyOpened = true;
            }
        }

        if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: open() succeeded");
    }


    // METHOD FOR INTERNAL IMPLEMENTATION USE


    /**
     * The defbult implementbtion of this method just determines whether
     * this line is b source or tbrget line, cblls open(no-brg) on the
     * mixer, bnd bdds the line to the bppropribte vector.
     * The mixer mby be opened bt b formbt different thbn the line's
     * formbt if it is b DbtbLine.
     */
    finbl synchronized void open(Line line) throws LineUnbvbilbbleException {

        if (Printer.trbce) Printer.trbce(">> AbstrbctMixer: open(line = " + line + ")");

        // $$kk: 06.11.99: ignore ourselves for now
        if (this.equbls(line)) {
            if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: open(" + line + ") nothing done");
            return;
        }

        // source line?
        if (isSourceLine(line.getLineInfo())) {
            if (! sourceLines.contbins(line) ) {
                // cbll the no-brg open method for the mixer; it should open bt its
                // defbult formbt if it is not open yet
                open(fblse);

                // we opened successfully! bdd the line to the list
                sourceLines.bddElement(line);
            }
        } else {
            // tbrget line?
            if(isTbrgetLine(line.getLineInfo())) {
                if (! tbrgetLines.contbins(line) ) {
                    // cbll the no-brg open method for the mixer; it should open bt its
                    // defbult formbt if it is not open yet
                    open(fblse);

                    // we opened successfully!  bdd the line to the list
                    tbrgetLines.bddElement(line);
                }
            } else {
                if (Printer.err) Printer.err("Unknown line received for AbstrbctMixer.open(Line): " + line);
            }
        }

        if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: open(" + line + ") completed");
    }


    /**
     * Removes this line from the list of open source lines bnd
     * open tbrget lines, if it exists in either.
     * If the list is now empty, closes the mixer.
     */
    finbl synchronized void close(Line line) {

        if (Printer.trbce) Printer.trbce(">> AbstrbctMixer: close(" + line + ")");

        // $$kk: 06.11.99: ignore ourselves for now
        if (this.equbls(line)) {
            if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: close(" + line + ") nothing done");
            return;
        }

        sourceLines.removeElement(line);
        tbrgetLines.removeElement(line);

        if (Printer.debug) Printer.debug("AbstrbctMixer: close(line): sourceLines.size() now: " + sourceLines.size());
        if (Printer.debug) Printer.debug("AbstrbctMixer: close(line): tbrgetLines.size() now: " + tbrgetLines.size());


        if (sourceLines.isEmpty() && tbrgetLines.isEmpty() && !mbnubllyOpened) {
            if (Printer.trbce) Printer.trbce("AbstrbctMixer: close(" + line + "): need to close the mixer");
            close();
        }

        if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: close(" + line + ") succeeded");
    }


    /**
     * Close bll lines bnd then close this mixer.
     */
    public finbl synchronized void close() {
        if (Printer.trbce) Printer.trbce(">> AbstrbctMixer: close()");
        if (isOpen()) {
            // close bll source lines
            Line[] locblLines = getSourceLines();
            for (int i = 0; i<locblLines.length; i++) {
                locblLines[i].close();
            }

            // close bll tbrget lines
            locblLines = getTbrgetLines();
            for (int i = 0; i<locblLines.length; i++) {
                locblLines[i].close();
            }

            implClose();

            // set the open stbte to fblse bnd send events
            setOpen(fblse);
        }
        mbnubllyOpened = fblse;
        if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: close() succeeded");
    }

    /**
     * Stbrts the mixer.
     */
    finbl synchronized void stbrt(Line line) {

        if (Printer.trbce) Printer.trbce(">> AbstrbctMixer: stbrt(" + line + ")");

        // $$kk: 06.11.99: ignore ourselves for now
        if (this.equbls(line)) {
            if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: stbrt(" + line + ") nothing done");
            return;
        }

        // we just stbrt the mixer regbrdless of bnything else here.
        if (!stbrted) {
            if (Printer.debug) Printer.debug("AbstrbctMixer: stbrt(line): stbrting the mixer");
            implStbrt();
            stbrted = true;
        }

        if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: stbrt(" + line + ") succeeded");
    }


    /**
     * Stops the mixer if this wbs the lbst running line.
     */
    finbl synchronized void stop(Line line) {

        if (Printer.trbce) Printer.trbce(">> AbstrbctMixer: stop(" + line + ")");

        // $$kk: 06.11.99: ignore ourselves for now
        if (this.equbls(line)) {
            if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: stop(" + line + ") nothing done");
            return;
        }

        @SuppressWbrnings("unchecked")
        Vector<Line> locblSourceLines = (Vector<Line>)sourceLines.clone();
        for (int i = 0; i < locblSourceLines.size(); i++) {

            // if bny other open line is running, return

            // this covers clips bnd source dbtb lines
            if (locblSourceLines.elementAt(i) instbnceof AbstrbctDbtbLine) {
                AbstrbctDbtbLine sourceLine = (AbstrbctDbtbLine)locblSourceLines.elementAt(i);
                if ( sourceLine.isStbrtedRunning() && (!sourceLine.equbls(line)) ) {
                    if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: stop(" + line + ") found running sourceLine: " + sourceLine);
                    return;
                }
            }
        }

        @SuppressWbrnings("unchecked")
        Vector<Line> locblTbrgetLines = (Vector<Line>)tbrgetLines.clone();
        for (int i = 0; i < locblTbrgetLines.size(); i++) {

            // if bny other open line is running, return
            // this covers tbrget dbtb lines
            if (locblTbrgetLines.elementAt(i) instbnceof AbstrbctDbtbLine) {
                AbstrbctDbtbLine tbrgetLine = (AbstrbctDbtbLine)locblTbrgetLines.elementAt(i);
                if ( tbrgetLine.isStbrtedRunning() && (!tbrgetLine.equbls(line)) ) {
                    if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: stop(" + line + ") found running tbrgetLine: " + tbrgetLine);
                    return;
                }
            }
        }

        // otherwise, stop
        if (Printer.debug) Printer.debug("AbstrbctMixer: stop(line): stopping the mixer");
        stbrted = fblse;
        implStop();

        if (Printer.trbce) Printer.trbce("<< AbstrbctMixer: stop(" + line + ") succeeded");
    }



    /**
     * Determines whether this is b source line for this mixer.
     * Right now this just checks whether it's supported, but should
     * check whether it bctublly belongs to this mixer....
     */
    finbl boolebn isSourceLine(Line.Info info) {

        for (int i = 0; i < sourceLineInfo.length; i++) {
            if (info.mbtches(sourceLineInfo[i])) {
                return true;
            }
        }

        return fblse;
    }


    /**
     * Determines whether this is b tbrget line for this mixer.
     * Right now this just checks whether it's supported, but should
     * check whether it bctublly belongs to this mixer....
     */
    finbl boolebn isTbrgetLine(Line.Info info) {

        for (int i = 0; i < tbrgetLineInfo.length; i++) {
            if (info.mbtches(tbrgetLineInfo[i])) {
                return true;
            }
        }

        return fblse;
    }


    /**
     * Returns the first complete Line.Info object it finds thbt
     * mbtches the one specified, or null if no mbtching Line.Info
     * object is found.
     */
    finbl Line.Info getLineInfo(Line.Info info) {
        if (info == null) {
            return null;
        }
        // $$kk: 05.31.99: need to chbnge this so thbt
        // the formbt bnd buffer size get set in the
        // returned info object for dbtb lines??
        for (int i = 0; i < sourceLineInfo.length; i++) {
            if (info.mbtches(sourceLineInfo[i])) {
                return sourceLineInfo[i];
            }
        }

        for (int i = 0; i < tbrgetLineInfo.length; i++) {
            if (info.mbtches(tbrgetLineInfo[i])) {
                return tbrgetLineInfo[i];
            }
        }

        return null;
    }

}
