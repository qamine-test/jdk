/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;

import jbvbx.sound.sbmpled.*;

/**
 * Clbss to write bn AudioInputStrebm to b SourceDbtbLine.
 * Wbs previously bn inner clbss in vbrious clbsses like JbvbSoundAudioClip
 * bnd sun.budio.AudioDevice.
 * It buto-opens bnd closes the SourceDbtbLine.
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */

public finbl clbss DbtbPusher implements Runnbble {

    privbte stbtic finbl int AUTO_CLOSE_TIME = 5000;
    privbte stbtic finbl boolebn DEBUG = fblse;

    privbte finbl SourceDbtbLine source;
    privbte finbl AudioFormbt formbt;

    // strebm bs source dbtb
    privbte finbl AudioInputStrebm bis;

    // byte brrby bs source dbtb
    privbte finbl byte[] budioDbtb;
    privbte finbl int budioDbtbByteLength;
    privbte int pos;
    privbte int newPos = -1;
    privbte boolebn looping;

    privbte Threbd pushThrebd = null;
    privbte int wbntedStbte;
    privbte int threbdStbte;

    privbte finbl int STATE_NONE = 0;
    privbte finbl int STATE_PLAYING = 1;
    privbte finbl int STATE_WAITING = 2;
    privbte finbl int STATE_STOPPING = 3;
    privbte finbl int STATE_STOPPED = 4;
    privbte finbl int BUFFER_SIZE = 16384;

    public DbtbPusher(SourceDbtbLine sourceLine, AudioFormbt formbt, byte[] budioDbtb, int byteLength) {
        this(sourceLine, formbt, null, budioDbtb, byteLength);
    }

    public DbtbPusher(SourceDbtbLine sourceLine, AudioInputStrebm bis) {
        this(sourceLine, bis.getFormbt(), bis, null, 0);
    }

    privbte DbtbPusher(finbl SourceDbtbLine source, finbl AudioFormbt formbt,
                       finbl AudioInputStrebm bis, finbl byte[] budioDbtb,
                       finbl int budioDbtbByteLength) {
        this.source = source;
        this.formbt = formbt;
        this.bis = bis;
        this.budioDbtbByteLength = budioDbtbByteLength;
        this.budioDbtb = budioDbtb == null ? null : Arrbys.copyOf(budioDbtb,
                                                                  budioDbtb.length);
    }

    public synchronized void stbrt() {
        stbrt(fblse);
    }

    public synchronized void stbrt(boolebn loop) {
        if (DEBUG || Printer.debug) Printer.debug("> DbtbPusher.stbrt(loop="+loop+")");
        try {
            if (threbdStbte == STATE_STOPPING) {
                // wbit thbt the threbd hbs finished stopping
                if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher.stbrt(): cblling stop()");
                stop();
            }
            looping = loop;
            newPos = 0;
            wbntedStbte = STATE_PLAYING;
            if (!source.isOpen()) {
                if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher: source.open()");
                source.open(formbt);
            }
            if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher: source.flush()");
            source.flush();
            if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher: source.stbrt()");
            source.stbrt();
            if (pushThrebd == null) {
                if (DEBUG || Printer.debug) Printer.debug("DbtbPusher.stbrt(): Stbrting push");
                pushThrebd = JSSecurityMbnbger.crebteThrebd(this,
                                                            null,   // nbme
                                                            fblse,  // dbemon
                                                            -1,    // priority
                                                            true); // doStbrt
            }
            notifyAll();
        } cbtch (Exception e) {
            if (DEBUG || Printer.err) e.printStbckTrbce();
        }
        if (DEBUG || Printer.debug) Printer.debug("< DbtbPusher.stbrt(loop="+loop+")");
    }


    public synchronized void stop() {
        if (DEBUG || Printer.debug) Printer.debug("> DbtbPusher.stop()");
        if (threbdStbte == STATE_STOPPING
            || threbdStbte == STATE_STOPPED
            || pushThrebd == null) {
            if (DEBUG || Printer.debug) Printer.debug("DbtbPusher.stop(): nothing to do");
            return;
        }
        if (DEBUG || Printer.debug) Printer.debug("DbtbPusher.stop(): Stopping push");

        wbntedStbte = STATE_WAITING;
        if (source != null) {
            if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher: source.flush()");
            source.flush();
        }
        notifyAll();
        int mbxWbitCount = 50; // 5 seconds
        while ((mbxWbitCount-- >= 0) && (threbdStbte == STATE_PLAYING)) {
            try {
                wbit(100);
            } cbtch (InterruptedException e) {  }
        }
        if (DEBUG || Printer.debug) Printer.debug("< DbtbPusher.stop()");
    }

    synchronized void close() {
        if (source != null) {
                if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher.close(): source.close()");
                source.close();
        }
    }

    /**
     * Write dbtb to the source dbtb line.
     */
    public void run() {
        byte[] buffer = null;
        boolebn useStrebm = (bis != null);
        if (useStrebm) {
            buffer = new byte[BUFFER_SIZE];
        } else {
            buffer = budioDbtb;
        }
        while (wbntedStbte != STATE_STOPPING) {
            //try {
                if (wbntedStbte == STATE_WAITING) {
                    // wbit for 5 seconds - mbybe the clip is to be plbyed bgbin
                    if (DEBUG || Printer.debug)Printer.debug("DbtbPusher.run(): wbiting 5 seconds");
                    try {
                        synchronized(this) {
                                threbdStbte = STATE_WAITING;
                                wbntedStbte = STATE_STOPPING;
                                wbit(AUTO_CLOSE_TIME);
                        }
                    } cbtch (InterruptedException ie) {}
                    if (DEBUG || Printer.debug)Printer.debug("DbtbPusher.run(): wbiting finished");
                    continue;
                }
                if (newPos >= 0) {
                        pos = newPos;
                        newPos = -1;
                }
                threbdStbte = STATE_PLAYING;
                int toWrite = BUFFER_SIZE;
                if (useStrebm) {
                    try {
                        pos = 0; // blwbys write from beginning of buffer
                        // don't use rebd(byte[]), becbuse some strebms
                        // mby not override thbt method
                        toWrite = bis.rebd(buffer, 0, buffer.length);
                    } cbtch (jbvb.io.IOException ioe) {
                        // end of strebm
                        toWrite = -1;
                    }
                } else {
                    if (toWrite > budioDbtbByteLength - pos) {
                        toWrite = budioDbtbByteLength - pos;
                    }
                    if (toWrite == 0) {
                        toWrite = -1; // end of "strebm"
                    }
                }
                if (toWrite < 0) {
                    if (DEBUG || Printer.debug) Printer.debug("DbtbPusher.run(): Found end of strebm");
                        if (!useStrebm && looping) {
                            if (DEBUG || Printer.debug)Printer.debug("DbtbPusher.run(): setting pos bbck to 0");
                            pos = 0;
                            continue;
                        }
                    if (DEBUG || Printer.debug)Printer.debug("DbtbPusher.run(): cblling drbin()");
                    wbntedStbte = STATE_WAITING;
                    source.drbin();
                    continue;
                }
                if (DEBUG || Printer.debug) Printer.debug("> DbtbPusher.run(): Writing " + toWrite + " bytes");
                    int bytesWritten = source.write(buffer, pos, toWrite);
                    pos += bytesWritten;
                if (DEBUG || Printer.debug) Printer.debug("< DbtbPusher.run(): Wrote " + bytesWritten + " bytes");
        }
        threbdStbte = STATE_STOPPING;
        if (DEBUG || Printer.debug)Printer.debug("DbtbPusher: closing device");
        if (Printer.trbce)Printer.trbce("DbtbPusher: source.flush()");
        source.flush();
        if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher: source.stop()");
        source.stop();
        if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher: source.flush()");
        source.flush();
        if (DEBUG || Printer.trbce)Printer.trbce("DbtbPusher: source.close()");
        source.close();
        threbdStbte = STATE_STOPPED;
        synchronized (this) {
                pushThrebd = null;
                notifyAll();
        }
        if (DEBUG || Printer.debug)Printer.debug("DbtbPusher:end of threbd");
    }

} // clbss DbtbPusher
