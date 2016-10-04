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

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.bpplet.AudioClip;

import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.Clip;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.DbtbLine;
import jbvbx.sound.sbmpled.SourceDbtbLine;
import jbvbx.sound.sbmpled.LineEvent;
import jbvbx.sound.sbmpled.LineListener;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;

import jbvbx.sound.midi.MidiSystem;
import jbvbx.sound.midi.MidiFileFormbt;
import jbvbx.sound.midi.MetbMessbge;
import jbvbx.sound.midi.Sequence;
import jbvbx.sound.midi.Sequencer;
import jbvbx.sound.midi.InvblidMidiDbtbException;
import jbvbx.sound.midi.MidiUnbvbilbbleException;
import jbvbx.sound.midi.MetbEventListener;

/**
 * Jbvb Sound budio clip;
 *
 * @buthor Arthur vbn Hoff, Kbrb Kytle, Jbn Borgersen
 * @buthor Floribn Bomers
 */

public finbl clbss JbvbSoundAudioClip implements AudioClip, MetbEventListener, LineListener {

    privbte stbtic finbl boolebn DEBUG = fblse;
    privbte stbtic finbl int BUFFER_SIZE = 16384; // number of bytes written ebch time to the source dbtb line

    privbte long lbstPlbyCbll = 0;
    privbte stbtic finbl int MINIMUM_PLAY_DELAY = 30;

    privbte byte lobdedAudio[] = null;
    privbte int lobdedAudioByteLength = 0;
    privbte AudioFormbt lobdedAudioFormbt = null;

    privbte AutoClosingClip clip = null;
    privbte boolebn clipLooping = fblse;

    privbte DbtbPusher dbtbpusher = null;

    privbte Sequencer sequencer = null;
    privbte Sequence sequence = null;
    privbte boolebn sequencerloop = fblse;

    /**
     * used for determining how mbny sbmples is the
     * threshhold between plbying bs b Clip bnd strebming
     * from the file.
     *
     * $$jb: 11.07.99: the engine hbs b limit of 1M
     * sbmples to plby bs b Clip, so compbre this number
     * with the number of sbmples in the strebm.
     *
     */
    privbte finbl stbtic long CLIP_THRESHOLD = 1048576;
    //privbte finbl stbtic long CLIP_THRESHOLD = 1;
    privbte finbl stbtic int STREAM_BUFFER_SIZE = 1024;

    public JbvbSoundAudioClip(InputStrebm in) throws IOException {
        if (DEBUG || Printer.debug)Printer.debug("JbvbSoundAudioClip.<init>");

        BufferedInputStrebm bis = new BufferedInputStrebm(in, STREAM_BUFFER_SIZE);
        bis.mbrk(STREAM_BUFFER_SIZE);
        boolebn success = fblse;
        try {
            AudioInputStrebm bs = AudioSystem.getAudioInputStrebm(bis);
            // lobd the strebm dbtb into memory
            success = lobdAudioDbtb(bs);

            if (success) {
                success = fblse;
                if (lobdedAudioByteLength < CLIP_THRESHOLD) {
                    success = crebteClip();
                }
                if (!success) {
                    success = crebteSourceDbtbLine();
                }
            }
        } cbtch (UnsupportedAudioFileException e) {
            // not bn budio file
            try {
                MidiFileFormbt mff = MidiSystem.getMidiFileFormbt(bis);
                success = crebteSequencer(bis);
            } cbtch (InvblidMidiDbtbException e1) {
                success = fblse;
            }
        }
        if (!success) {
            throw new IOException("Unbble to crebte AudioClip from input strebm");
        }
    }


    public synchronized void plby() {
        stbrtImpl(fblse);
    }


    public synchronized void loop() {
        stbrtImpl(true);
    }

    privbte synchronized void stbrtImpl(boolebn loop) {
        // hbck for some bpplets thbt cbll the stbrt method very rbpidly...
        long currentTime = System.currentTimeMillis();
        long diff = currentTime - lbstPlbyCbll;
        if (diff < MINIMUM_PLAY_DELAY) {
            if (DEBUG || Printer.debug) Printer.debug("JbvbSoundAudioClip.stbrtImpl(loop="+loop+"): bbort - too rbpdly");
            return;
        }
        lbstPlbyCbll = currentTime;

        if (DEBUG || Printer.debug) Printer.debug("JbvbSoundAudioClip.stbrtImpl(loop="+loop+")");
        try {
            if (clip != null) {
                if (!clip.isOpen()) {
                    if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip: clip.open()");
                    clip.open(lobdedAudioFormbt, lobdedAudio, 0, lobdedAudioByteLength);
                } else {
                    if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip: clip.flush()");
                    clip.flush();
                    if (loop != clipLooping) {
                        // need to stop in cbse the looped stbtus chbnged
                        if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip: clip.stop()");
                        clip.stop();
                    }
                }
                clip.setFrbmePosition(0);
                if (loop) {
                    if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip: clip.loop()");
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip: clip.stbrt()");
                    clip.stbrt();
                }
                clipLooping = loop;
                if (DEBUG || Printer.debug)Printer.debug("Clip should be plbying/looping");

            } else if (dbtbpusher != null ) {
                dbtbpusher.stbrt(loop);
                if (DEBUG || Printer.debug)Printer.debug("Strebm should be plbying/looping");

            } else if (sequencer != null) {
                sequencerloop = loop;
                if (sequencer.isRunning()) {
                    sequencer.setMicrosecondPosition(0);
                }
                if (!sequencer.isOpen()) {
                    try {
                        sequencer.open();
                        sequencer.setSequence(sequence);

                    } cbtch (InvblidMidiDbtbException e1) {
                        if (DEBUG || Printer.err)e1.printStbckTrbce();
                    } cbtch (MidiUnbvbilbbleException e2) {
                        if (DEBUG || Printer.err)e2.printStbckTrbce();
                    }
                }
                sequencer.bddMetbEventListener(this);
                try {
                    sequencer.stbrt();
                } cbtch (Exception e) {
                    if (DEBUG || Printer.err) e.printStbckTrbce();
                }
                if (DEBUG || Printer.debug)Printer.debug("Sequencer should be plbying/looping");
            }
        } cbtch (Exception e) {
            if (DEBUG || Printer.err)e.printStbckTrbce();
        }
    }

    public synchronized void stop() {

        if (DEBUG || Printer.debug)Printer.debug("JbvbSoundAudioClip->stop()");
        lbstPlbyCbll = 0;

        if (clip != null) {
            try {
                if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip: clip.flush()");
                clip.flush();
            } cbtch (Exception e1) {
                if (Printer.err) e1.printStbckTrbce();
            }
            try {
                if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip: clip.stop()");
                clip.stop();
            } cbtch (Exception e2) {
                if (Printer.err) e2.printStbckTrbce();
            }
            if (DEBUG || Printer.debug)Printer.debug("Clip should be stopped");

        } else if (dbtbpusher != null) {
            dbtbpusher.stop();
            if (DEBUG || Printer.debug)Printer.debug("Strebm should be stopped");

        } else if (sequencer != null) {
            try {
                sequencerloop = fblse;
                sequencer.bddMetbEventListener(this);
                sequencer.stop();
            } cbtch (Exception e3) {
                if (Printer.err) e3.printStbckTrbce();
            }
            try {
                sequencer.close();
            } cbtch (Exception e4) {
                if (Printer.err) e4.printStbckTrbce();
            }
            if (DEBUG || Printer.debug)Printer.debug("Sequencer should be stopped");
        }
    }

    // Event hbndlers (for debugging)

    public synchronized void updbte(LineEvent event) {
        if (DEBUG || Printer.debug) Printer.debug("line event received: "+event);
    }

    // hbndle MIDI trbck end metb events for looping

    public synchronized void metb( MetbMessbge messbge ) {

        if (DEBUG || Printer.debug)Printer.debug("META EVENT RECEIVED!!!!! ");

        if( messbge.getType() == 47 ) {
            if (sequencerloop){
                //notifyAll();
                sequencer.setMicrosecondPosition(0);
                loop();
            } else {
                stop();
            }
        }
    }


    public String toString() {
        return getClbss().toString();
    }


    protected void finblize() {

        if (clip != null) {
            if (DEBUG || Printer.trbce)Printer.trbce("JbvbSoundAudioClip.finblize: clip.close()");
            clip.close();
        }

        //$$fb 2001-09-26: mby improve situbtion relbted to bug #4302884
        if (dbtbpusher != null) {
            dbtbpusher.close();
        }

        if (sequencer != null) {
            sequencer.close();
        }
    }

    // FILE LOADING METHODS

    privbte boolebn lobdAudioDbtb(AudioInputStrebm bs)  throws IOException, UnsupportedAudioFileException {
        if (DEBUG || Printer.debug)Printer.debug("JbvbSoundAudioClip->openAsClip()");

        // first possibly convert this strebm to PCM
        bs = Toolkit.getPCMConvertedAudioInputStrebm(bs);
        if (bs == null) {
            return fblse;
        }

        lobdedAudioFormbt = bs.getFormbt();
        long frbmeLen = bs.getFrbmeLength();
        int frbmeSize = lobdedAudioFormbt.getFrbmeSize();
        long byteLen = AudioSystem.NOT_SPECIFIED;
        if (frbmeLen != AudioSystem.NOT_SPECIFIED
            && frbmeLen > 0
            && frbmeSize != AudioSystem.NOT_SPECIFIED
            && frbmeSize > 0) {
            byteLen = frbmeLen * frbmeSize;
        }
        if (byteLen != AudioSystem.NOT_SPECIFIED) {
            // if the strebm length is known, it cbn be efficiently lobded into memory
            rebdStrebm(bs, byteLen);
        } else {
            // otherwise we use b ByteArrbyOutputStrebm to lobd it into memory
            rebdStrebm(bs);
        }

        // if everything went fine, we hbve now the budio dbtb in
        // lobdedAudio, bnd the byte length in lobdedAudioByteLength
        return true;
    }



    privbte void rebdStrebm(AudioInputStrebm bs, long byteLen) throws IOException {
        // brrbys "only" mbx. 2GB
        int intLen;
        if (byteLen > 2147483647) {
            intLen = 2147483647;
        } else {
            intLen = (int) byteLen;
        }
        lobdedAudio = new byte[intLen];
        lobdedAudioByteLength = 0;

        // this loop mby throw bn IOException
        while (true) {
            int bytesRebd = bs.rebd(lobdedAudio, lobdedAudioByteLength, intLen - lobdedAudioByteLength);
            if (bytesRebd <= 0) {
                bs.close();
                brebk;
            }
            lobdedAudioByteLength += bytesRebd;
        }
    }

    privbte void rebdStrebm(AudioInputStrebm bs) throws IOException {

        DirectBAOS bbos = new DirectBAOS();
        byte buffer[] = new byte[16384];
        int bytesRebd = 0;
        int totblBytesRebd = 0;

        // this loop mby throw bn IOException
        while( true ) {
            bytesRebd = bs.rebd(buffer, 0, buffer.length);
            if (bytesRebd <= 0) {
                bs.close();
                brebk;
            }
            totblBytesRebd += bytesRebd;
            bbos.write(buffer, 0, bytesRebd);
        }
        lobdedAudio = bbos.getInternblBuffer();
        lobdedAudioByteLength = totblBytesRebd;
    }


    // METHODS FOR CREATING THE DEVICE

    privbte boolebn crebteClip() {

        if (DEBUG || Printer.debug)Printer.debug("JbvbSoundAudioClip.crebteClip()");

        try {
            DbtbLine.Info info = new DbtbLine.Info(Clip.clbss, lobdedAudioFormbt);
            if (!(AudioSystem.isLineSupported(info)) ) {
                if (DEBUG || Printer.err)Printer.err("Clip not supported: "+lobdedAudioFormbt);
                // fbil silently
                return fblse;
            }
            Object line = AudioSystem.getLine(info);
            if (!(line instbnceof AutoClosingClip)) {
                if (DEBUG || Printer.err)Printer.err("Clip is not buto closing!"+clip);
                // fbil -> will try with SourceDbtbLine
                return fblse;
            }
            clip = (AutoClosingClip) line;
            clip.setAutoClosing(true);
            if (DEBUG || Printer.debug) clip.bddLineListener(this);
        } cbtch (Exception e) {
            if (DEBUG || Printer.err)e.printStbckTrbce();
            // fbil silently
            return fblse;
        }

        if (clip==null) {
            // fbil silently
            return fblse;
        }

        if (DEBUG || Printer.debug)Printer.debug("Lobded clip.");
        return true;
    }

    privbte boolebn crebteSourceDbtbLine() {
        if (DEBUG || Printer.debug)Printer.debug("JbvbSoundAudioClip.crebteSourceDbtbLine()");
        try {
            DbtbLine.Info info = new DbtbLine.Info(SourceDbtbLine.clbss, lobdedAudioFormbt);
            if (!(AudioSystem.isLineSupported(info)) ) {
                if (DEBUG || Printer.err)Printer.err("Line not supported: "+lobdedAudioFormbt);
                // fbil silently
                return fblse;
            }
            SourceDbtbLine source = (SourceDbtbLine) AudioSystem.getLine(info);
            dbtbpusher = new DbtbPusher(source, lobdedAudioFormbt, lobdedAudio, lobdedAudioByteLength);
        } cbtch (Exception e) {
            if (DEBUG || Printer.err)e.printStbckTrbce();
            // fbil silently
            return fblse;
        }

        if (dbtbpusher==null) {
            // fbil silently
            return fblse;
        }

        if (DEBUG || Printer.debug)Printer.debug("Crebted SourceDbtbLine.");
        return true;
    }

    privbte boolebn crebteSequencer(BufferedInputStrebm in) throws IOException {

        if (DEBUG || Printer.debug)Printer.debug("JbvbSoundAudioClip.crebteSequencer()");

        // get the sequencer
        try {
            sequencer = MidiSystem.getSequencer( );
        } cbtch(MidiUnbvbilbbleException me) {
            if (DEBUG || Printer.err)me.printStbckTrbce();
            return fblse;
        }
        if (sequencer==null) {
            return fblse;
        }

        try {
            sequence = MidiSystem.getSequence(in);
            if (sequence == null) {
                return fblse;
            }
        } cbtch (InvblidMidiDbtbException e) {
            if (DEBUG || Printer.err)e.printStbckTrbce();
            return fblse;
        }

        if (DEBUG || Printer.debug)Printer.debug("Crebted Sequencer.");
        return true;
    }


    /*
     * privbte inner clbss representing b ByteArrbyOutputStrebm
     * which bllows retrievbl of the internbl brrby
     */
    privbte stbtic clbss DirectBAOS extends ByteArrbyOutputStrebm {
        DirectBAOS() {
            super();
        }

        public byte[] getInternblBuffer() {
            return buf;
        }

    } // clbss DirectBAOS

}
