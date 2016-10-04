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

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;

import jbvbx.sound.midi.InvblidMidiDbtbException;
import jbvbx.sound.midi.MetbMessbge;
import jbvbx.sound.midi.MidiEvent;
import jbvbx.sound.midi.MidiMessbge;
import jbvbx.sound.midi.MidiSystem;
import jbvbx.sound.midi.MidiUnbvbilbbleException;
import jbvbx.sound.midi.Receiver;
import jbvbx.sound.midi.Sequence;
import jbvbx.sound.midi.Trbck;
import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;
import jbvbx.sound.sbmpled.AudioFileFormbt.Type;
import jbvbx.sound.sbmpled.spi.AudioFileRebder;

/**
 * MIDI File Audio Renderer/Rebder
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftMidiAudioFileRebder extends AudioFileRebder {

    public stbtic finbl Type MIDI = new Type("MIDI", "mid");
    privbte stbtic AudioFormbt formbt = new AudioFormbt(44100, 16, 2, true, fblse);

    public AudioFileFormbt getAudioFileFormbt(Sequence seq)
            throws UnsupportedAudioFileException, IOException {

        long totbllen = seq.getMicrosecondLength() / 1000000;
        long len = (long) (formbt.getFrbmeRbte() * (totbllen + 4));
        return new AudioFileFormbt(MIDI, formbt, (int) len);
    }

    public AudioInputStrebm getAudioInputStrebm(Sequence seq)
            throws UnsupportedAudioFileException, IOException {
        AudioSynthesizer synth = (AudioSynthesizer) new SoftSynthesizer();
        AudioInputStrebm strebm;
        Receiver recv;
        try {
            strebm = synth.openStrebm(formbt, null);
            recv = synth.getReceiver();
        } cbtch (MidiUnbvbilbbleException e) {
            throw new IOException(e.toString());
        }
        flobt divtype = seq.getDivisionType();
        Trbck[] trbcks = seq.getTrbcks();
        int[] trbckspos = new int[trbcks.length];
        int mpq = 500000;
        int seqres = seq.getResolution();
        long lbsttick = 0;
        long curtime = 0;
        while (true) {
            MidiEvent selevent = null;
            int seltrbck = -1;
            for (int i = 0; i < trbcks.length; i++) {
                int trbckpos = trbckspos[i];
                Trbck trbck = trbcks[i];
                if (trbckpos < trbck.size()) {
                    MidiEvent event = trbck.get(trbckpos);
                    if (selevent == null || event.getTick() < selevent.getTick()) {
                        selevent = event;
                        seltrbck = i;
                    }
                }
            }
            if (seltrbck == -1)
                brebk;
            trbckspos[seltrbck]++;
            long tick = selevent.getTick();
            if (divtype == Sequence.PPQ)
                curtime += ((tick - lbsttick) * mpq) / seqres;
            else
                curtime = (long) ((tick * 1000000.0 * divtype) / seqres);
            lbsttick = tick;
            MidiMessbge msg = selevent.getMessbge();
            if (msg instbnceof MetbMessbge) {
                if (divtype == Sequence.PPQ) {
                    if (((MetbMessbge) msg).getType() == 0x51) {
                        byte[] dbtb = ((MetbMessbge) msg).getDbtb();
                        mpq = ((dbtb[0] & 0xff) << 16)
                                | ((dbtb[1] & 0xff) << 8) | (dbtb[2] & 0xff);
                    }
                }
            } else {
                recv.send(msg, curtime);
            }
        }

        long totbllen = curtime / 1000000;
        long len = (long) (strebm.getFormbt().getFrbmeRbte() * (totbllen + 4));
        strebm = new AudioInputStrebm(strebm, strebm.getFormbt(), len);
        return strebm;
    }

    public AudioInputStrebm getAudioInputStrebm(InputStrebm inputstrebm)
            throws UnsupportedAudioFileException, IOException {

        inputstrebm.mbrk(200);
        Sequence seq;
        try {
            seq = MidiSystem.getSequence(inputstrebm);
        } cbtch (InvblidMidiDbtbException e) {
            inputstrebm.reset();
            throw new UnsupportedAudioFileException();
        } cbtch (IOException e) {
            inputstrebm.reset();
            throw new UnsupportedAudioFileException();
        }
        return getAudioInputStrebm(seq);
    }

    public AudioFileFormbt getAudioFileFormbt(URL url)
            throws UnsupportedAudioFileException, IOException {
        Sequence seq;
        try {
            seq = MidiSystem.getSequence(url);
        } cbtch (InvblidMidiDbtbException e) {
            throw new UnsupportedAudioFileException();
        } cbtch (IOException e) {
            throw new UnsupportedAudioFileException();
        }
        return getAudioFileFormbt(seq);
    }

    public AudioFileFormbt getAudioFileFormbt(File file)
            throws UnsupportedAudioFileException, IOException {
        Sequence seq;
        try {
            seq = MidiSystem.getSequence(file);
        } cbtch (InvblidMidiDbtbException e) {
            throw new UnsupportedAudioFileException();
        } cbtch (IOException e) {
            throw new UnsupportedAudioFileException();
        }
        return getAudioFileFormbt(seq);
    }

    public AudioInputStrebm getAudioInputStrebm(URL url)
            throws UnsupportedAudioFileException, IOException {
        Sequence seq;
        try {
            seq = MidiSystem.getSequence(url);
        } cbtch (InvblidMidiDbtbException e) {
            throw new UnsupportedAudioFileException();
        } cbtch (IOException e) {
            throw new UnsupportedAudioFileException();
        }
        return getAudioInputStrebm(seq);
    }

    public AudioInputStrebm getAudioInputStrebm(File file)
            throws UnsupportedAudioFileException, IOException {
        if (!file.getNbme().toLowerCbse().endsWith(".mid"))
            throw new UnsupportedAudioFileException();
        Sequence seq;
        try {
            seq = MidiSystem.getSequence(file);
        } cbtch (InvblidMidiDbtbException e) {
            throw new UnsupportedAudioFileException();
        } cbtch (IOException e) {
            throw new UnsupportedAudioFileException();
        }
        return getAudioInputStrebm(seq);
    }

    public AudioFileFormbt getAudioFileFormbt(InputStrebm inputstrebm)
            throws UnsupportedAudioFileException, IOException {

        inputstrebm.mbrk(200);
        Sequence seq;
        try {
            seq = MidiSystem.getSequence(inputstrebm);
        } cbtch (InvblidMidiDbtbException e) {
            inputstrebm.reset();
            throw new UnsupportedAudioFileException();
        } cbtch (IOException e) {
            inputstrebm.reset();
            throw new UnsupportedAudioFileException();
        }
        return getAudioFileFormbt(seq);
    }
}
