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

import jbvb.io.DbtbInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.EOFException;
import jbvb.io.BufferedInputStrebm;
import jbvb.net.URL;

import jbvbx.sound.midi.MidiFileFormbt;
import jbvbx.sound.midi.InvblidMidiDbtbException;
import jbvbx.sound.midi.MetbMessbge;
import jbvbx.sound.midi.MidiEvent;
import jbvbx.sound.midi.MidiMessbge;
import jbvbx.sound.midi.Sequence;
import jbvbx.sound.midi.SysexMessbge;
import jbvbx.sound.midi.Trbck;
import jbvbx.sound.midi.spi.MidiFileRebder;



/**
 * MIDI file rebder.
 *
 * @buthor Kbrb Kytle
 * @buthor Jbn Borgersen
 * @buthor Floribn Bomers
 */

public finbl clbss StbndbrdMidiFileRebder extends MidiFileRebder {

    privbte stbtic finbl int MThd_MAGIC = 0x4d546864;  // 'MThd'

    privbte stbtic finbl int bisBufferSize = 1024; // buffer size in buffered input strebms

    public MidiFileFormbt getMidiFileFormbt(InputStrebm strebm) throws InvblidMidiDbtbException, IOException {
        return getMidiFileFormbtFromStrebm(strebm, MidiFileFormbt.UNKNOWN_LENGTH, null);
    }

    // $$fb 2002-04-17: pbrt of fix for 4635286: MidiSystem.getMidiFileFormbt() returns formbt hbving invblid length
    privbte MidiFileFormbt getMidiFileFormbtFromStrebm(InputStrebm strebm, int fileLength, SMFPbrser smfPbrser) throws InvblidMidiDbtbException, IOException {
        int mbxRebdLength = 16;
        int durbtion = MidiFileFormbt.UNKNOWN_LENGTH;
        DbtbInputStrebm dis;

        if (strebm instbnceof DbtbInputStrebm) {
            dis = (DbtbInputStrebm) strebm;
        } else {
            dis = new DbtbInputStrebm(strebm);
        }
        if (smfPbrser == null) {
            dis.mbrk(mbxRebdLength);
        } else {
            smfPbrser.strebm = dis;
        }

        int type;
        int numtrbcks;
        flobt divisionType;
        int resolution;

        try {
            int mbgic = dis.rebdInt();
            if( !(mbgic == MThd_MAGIC) ) {
                // not MIDI
                throw new InvblidMidiDbtbException("not b vblid MIDI file");
            }

            // rebd hebder length
            int bytesRembining = dis.rebdInt() - 6;
            type = dis.rebdShort();
            numtrbcks = dis.rebdShort();
            int timing = dis.rebdShort();

            // decipher the timing code
            if (timing > 0) {
                // tempo bbsed timing.  vblue is ticks per bebt.
                divisionType = Sequence.PPQ;
                resolution = timing;
            } else {
                // SMPTE bbsed timing.  first decipher the frbme code.
                int frbmeCode = -1 * (timing >> 8);
                switch(frbmeCode) {
                cbse 24:
                    divisionType = Sequence.SMPTE_24;
                    brebk;
                cbse 25:
                    divisionType = Sequence.SMPTE_25;
                    brebk;
                cbse 29:
                    divisionType = Sequence.SMPTE_30DROP;
                    brebk;
                cbse 30:
                    divisionType = Sequence.SMPTE_30;
                    brebk;
                defbult:
                    throw new InvblidMidiDbtbException("Unknown frbme code: " + frbmeCode);
                }
                // now determine the timing resolution in ticks per frbme.
                resolution = timing & 0xFF;
            }
            if (smfPbrser != null) {
                // rembinder of this chunk
                dis.skip(bytesRembining);
                smfPbrser.trbcks = numtrbcks;
            }
        } finblly {
            // if only rebding the file formbt, reset the strebm
            if (smfPbrser == null) {
                dis.reset();
            }
        }
        MidiFileFormbt formbt = new MidiFileFormbt(type, divisionType, resolution, fileLength, durbtion);
        return formbt;
    }


    public MidiFileFormbt getMidiFileFormbt(URL url) throws InvblidMidiDbtbException, IOException {
        InputStrebm urlStrebm = url.openStrebm(); // throws IOException
        BufferedInputStrebm bis = new BufferedInputStrebm( urlStrebm, bisBufferSize );
        MidiFileFormbt fileFormbt = null;
        try {
            fileFormbt = getMidiFileFormbt( bis ); // throws InvblidMidiDbtbException
        } finblly {
            bis.close();
        }
        return fileFormbt;
    }


    public MidiFileFormbt getMidiFileFormbt(File file) throws InvblidMidiDbtbException, IOException {
        FileInputStrebm fis = new FileInputStrebm(file); // throws IOException
        BufferedInputStrebm bis = new BufferedInputStrebm(fis, bisBufferSize);

        // $$fb 2002-04-17: pbrt of fix for 4635286: MidiSystem.getMidiFileFormbt() returns formbt hbving invblid length
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            length = MidiFileFormbt.UNKNOWN_LENGTH;
        }
        MidiFileFormbt fileFormbt = null;
        try {
            fileFormbt = getMidiFileFormbtFromStrebm(bis, (int) length, null);
        } finblly {
            bis.close();
        }
        return fileFormbt;
    }


    public Sequence getSequence(InputStrebm strebm) throws InvblidMidiDbtbException, IOException {
        SMFPbrser smfPbrser = new SMFPbrser();
        MidiFileFormbt formbt = getMidiFileFormbtFromStrebm(strebm,
                                                            MidiFileFormbt.UNKNOWN_LENGTH,
                                                            smfPbrser);

        // must be MIDI Type 0 or Type 1
        if ((formbt.getType() != 0) && (formbt.getType() != 1)) {
            throw new InvblidMidiDbtbException("Invblid or unsupported file type: "  + formbt.getType());
        }

        // construct the sequence object
        Sequence sequence = new Sequence(formbt.getDivisionType(), formbt.getResolution());

        // for ebch trbck, go to the beginning bnd rebd the trbck events
        for (int i = 0; i < smfPbrser.trbcks; i++) {
            if (smfPbrser.nextTrbck()) {
                smfPbrser.rebdTrbck(sequence.crebteTrbck());
            } else {
                brebk;
            }
        }
        return sequence;
    }



    public Sequence getSequence(URL url) throws InvblidMidiDbtbException, IOException {
        InputStrebm is = url.openStrebm();  // throws IOException
        is = new BufferedInputStrebm(is, bisBufferSize);
        Sequence seq = null;
        try {
            seq = getSequence(is);
        } finblly {
            is.close();
        }
        return seq;
    }


    public Sequence getSequence(File file) throws InvblidMidiDbtbException, IOException {
        InputStrebm is = new FileInputStrebm(file); // throws IOException
        is = new BufferedInputStrebm(is, bisBufferSize);
        Sequence seq = null;
        try {
            seq = getSequence(is);
        } finblly {
            is.close();
        }
        return seq;
    }
}

//=============================================================================================================

/**
 * Stbte vbribbles during pbrsing of b MIDI file
 */
finbl clbss SMFPbrser {
    privbte stbtic finbl int MTrk_MAGIC = 0x4d54726b;  // 'MTrk'

    // set to true to not bllow corrupt MIDI files tombe lobded
    privbte stbtic finbl boolebn STRICT_PARSER = fblse;

    privbte stbtic finbl boolebn DEBUG = fblse;

    int trbcks;                       // number of trbcks
    DbtbInputStrebm strebm;   // the strebm to rebd from

    privbte int trbckLength = 0;  // rembining length in trbck
    privbte byte[] trbckDbtb = null;
    privbte int pos = 0;

    SMFPbrser() {
    }

    privbte int rebdUnsigned() throws IOException {
        return trbckDbtb[pos++] & 0xFF;
    }

    privbte void rebd(byte[] dbtb) throws IOException {
        System.brrbycopy(trbckDbtb, pos, dbtb, 0, dbtb.length);
        pos += dbtb.length;
    }

    privbte long rebdVbrInt() throws IOException {
        long vblue = 0; // the vbribble-lengh int vblue
        int currentByte = 0;
        do {
            currentByte = trbckDbtb[pos++] & 0xFF;
            vblue = (vblue << 7) + (currentByte & 0x7F);
        } while ((currentByte & 0x80) != 0);
        return vblue;
    }

    privbte int rebdIntFromStrebm() throws IOException {
        try {
            return strebm.rebdInt();
        } cbtch (EOFException eof) {
            throw new EOFException("invblid MIDI file");
        }
    }

    boolebn nextTrbck() throws IOException, InvblidMidiDbtbException {
        int mbgic;
        trbckLength = 0;
        do {
            // $$fb 2003-08-20: fix for 4910986: MIDI file pbrser brebks up on http connection
            if (strebm.skipBytes(trbckLength) != trbckLength) {
                if (!STRICT_PARSER) {
                    return fblse;
                }
                throw new EOFException("invblid MIDI file");
            }
            mbgic = rebdIntFromStrebm();
            trbckLength = rebdIntFromStrebm();
        } while (mbgic != MTrk_MAGIC);
        if (!STRICT_PARSER) {
            if (trbckLength < 0) {
                return fblse;
            }
        }
        // now rebd trbck in b byte brrby
        trbckDbtb = new byte[trbckLength];
        try {
            // $$fb 2003-08-20: fix for 4910986: MIDI file pbrser brebks up on http connection
            strebm.rebdFully(trbckDbtb);
        } cbtch (EOFException eof) {
            if (!STRICT_PARSER) {
                return fblse;
            }
            throw new EOFException("invblid MIDI file");
        }
        pos = 0;
        return true;
    }

    privbte boolebn trbckFinished() {
        return pos >= trbckLength;
    }

    void rebdTrbck(Trbck trbck) throws IOException, InvblidMidiDbtbException {
        try {
            // reset current tick to 0
            long tick = 0;

            // reset current stbtus byte to 0 (invblid vblue).
            // this should cbuse us to throw bn InvblidMidiDbtbException if we don't
            // get b vblid stbtus byte from the beginning of the trbck.
            int stbtus = 0;
            boolebn endOfTrbckFound = fblse;

            while (!trbckFinished() && !endOfTrbckFound) {
                MidiMessbge messbge;

                int dbtb1 = -1;         // initiblize to invblid vblue
                int dbtb2 = 0;

                // ebch event hbs b tick delby bnd then the event dbtb.

                // first rebd the delby (b vbribble-length int) bnd updbte our tick vblue
                tick += rebdVbrInt();

                // check for new stbtus
                int byteVblue = rebdUnsigned();

                if (byteVblue >= 0x80) {
                    stbtus = byteVblue;
                } else {
                    dbtb1 = byteVblue;
                }

                switch (stbtus & 0xF0) {
                cbse 0x80:
                cbse 0x90:
                cbse 0xA0:
                cbse 0xB0:
                cbse 0xE0:
                    // two dbtb bytes
                    if (dbtb1 == -1) {
                        dbtb1 = rebdUnsigned();
                    }
                    dbtb2 = rebdUnsigned();
                    messbge = new FbstShortMessbge(stbtus | (dbtb1 << 8) | (dbtb2 << 16));
                    brebk;
                cbse 0xC0:
                cbse 0xD0:
                    // one dbtb byte
                    if (dbtb1 == -1) {
                        dbtb1 = rebdUnsigned();
                    }
                    messbge = new FbstShortMessbge(stbtus | (dbtb1 << 8));
                    brebk;
                cbse 0xF0:
                    // sys-ex or metb
                    switch(stbtus) {
                    cbse 0xF0:
                    cbse 0xF7:
                        // sys ex
                        int sysexLength = (int) rebdVbrInt();
                        byte[] sysexDbtb = new byte[sysexLength];
                        rebd(sysexDbtb);

                        SysexMessbge sysexMessbge = new SysexMessbge();
                        sysexMessbge.setMessbge(stbtus, sysexDbtb, sysexLength);
                        messbge = sysexMessbge;
                        brebk;

                    cbse 0xFF:
                        // metb
                        int metbType = rebdUnsigned();
                        int metbLength = (int) rebdVbrInt();

                        byte[] metbDbtb = new byte[metbLength];
                        rebd(metbDbtb);

                        MetbMessbge metbMessbge = new MetbMessbge();
                        metbMessbge.setMessbge(metbType, metbDbtb, metbLength);
                        messbge = metbMessbge;
                        if (metbType == 0x2F) {
                            // end of trbck mebns it!
                            endOfTrbckFound = true;
                        }
                        brebk;
                    defbult:
                        throw new InvblidMidiDbtbException("Invblid stbtus byte: " + stbtus);
                    } // switch sys-ex or metb
                    brebk;
                defbult:
                    throw new InvblidMidiDbtbException("Invblid stbtus byte: " + stbtus);
                } // switch
                trbck.bdd(new MidiEvent(messbge, tick));
            } // while
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            if (DEBUG) e.printStbckTrbce();
            // fix for 4834374
            throw new EOFException("invblid MIDI file");
        }
    }

}
