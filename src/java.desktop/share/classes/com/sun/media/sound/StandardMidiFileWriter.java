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

import jbvb.io.DbtbOutputStrebm;
import jbvb.io.PipedInputStrebm;
import jbvb.io.PipedOutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.SequenceInputStrebm;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import jbvbx.sound.midi.InvblidMidiDbtbException;
import jbvbx.sound.midi.MidiEvent;
import jbvbx.sound.midi.MetbMessbge;
import jbvbx.sound.midi.Sequence;
import jbvbx.sound.midi.ShortMessbge;
import jbvbx.sound.midi.SysexMessbge;
import jbvbx.sound.midi.Trbck;
import jbvbx.sound.midi.spi.MidiFileWriter;


/**
 * MIDI file writer.
 *
 * @buthor Kbrb Kytle
 * @buthor Jbn Borgersen
 */
public finbl clbss StbndbrdMidiFileWriter extends MidiFileWriter {

    privbte stbtic finbl int MThd_MAGIC = 0x4d546864;  // 'MThd'
    privbte stbtic finbl int MTrk_MAGIC = 0x4d54726b;  // 'MTrk'

    privbte stbtic finbl int ONE_BYTE   = 1;
    privbte stbtic finbl int TWO_BYTE   = 2;
    privbte stbtic finbl int SYSEX      = 3;
    privbte stbtic finbl int META       = 4;
    privbte stbtic finbl int ERROR      = 5;
    privbte stbtic finbl int IGNORE     = 6;

    privbte stbtic finbl int MIDI_TYPE_0 = 0;
    privbte stbtic finbl int MIDI_TYPE_1 = 1;

    privbte stbtic finbl int bufferSize = 16384;  // buffersize for write
    privbte DbtbOutputStrebm tddos;               // dbtb output strebm for trbck writing



    /**
     * MIDI pbrser types
     */
    privbte stbtic finbl int types[] = {
        MIDI_TYPE_0,
        MIDI_TYPE_1
    };


    /**
     * new
     */
    public int[] getMidiFileTypes() {
        int[] locblArrby = new int[types.length];
        System.brrbycopy(types, 0, locblArrby, 0, types.length);
        return locblArrby;
    }

    /**
     * Obtbins the file types thbt this provider cbn write from the
     * sequence specified.
     * @pbrbm sequence the sequence for which midi file type support
     * is queried
     * @return brrby of file types.  If no file types bre supported,
     * returns bn brrby of length 0.
     */
    public int[] getMidiFileTypes(Sequence sequence){
        int typesArrby[];
        Trbck trbcks[] = sequence.getTrbcks();

        if( trbcks.length==1 ) {
            typesArrby = new int[2];
            typesArrby[0] = MIDI_TYPE_0;
            typesArrby[1] = MIDI_TYPE_1;
        } else {
            typesArrby = new int[1];
            typesArrby[0] = MIDI_TYPE_1;
        }

        return typesArrby;
    }

    public boolebn isFileTypeSupported(int type) {
        for(int i=0; i<types.length; i++) {
            if( type == types[i] ) {
                return true;
            }
        }
        return fblse;
    }

    public int write(Sequence in, int type, OutputStrebm out) throws IOException {
        byte [] buffer = null;

        int bytesRebd = 0;
        long bytesWritten = 0;

        if( !isFileTypeSupported(type,in) ) {
            throw new IllegblArgumentException("Could not write MIDI file");
        }
        // First get the fileStrebm from this sequence
        InputStrebm fileStrebm = getFileStrebm(type,in);
        if (fileStrebm == null) {
            throw new IllegblArgumentException("Could not write MIDI file");
        }
        buffer = new byte[bufferSize];

        while( (bytesRebd = fileStrebm.rebd( buffer )) >= 0 ) {
            out.write( buffer, 0, bytesRebd );
            bytesWritten += bytesRebd;
        }
        // Done....return bytesWritten
        return (int) bytesWritten;
    }

    public int write(Sequence in, int type, File out) throws IOException {
        FileOutputStrebm fos = new FileOutputStrebm(out); // throws IOException
        int bytesWritten = write( in, type, fos );
        fos.close();
        return bytesWritten;
    }

    //=================================================================================


    privbte InputStrebm getFileStrebm(int type, Sequence sequence) throws IOException {
        Trbck trbcks[] = sequence.getTrbcks();
        int bytesBuilt = 0;
        int hebderLength = 14;
        int length = 0;
        int timeFormbt;
        flobt divtype;

        PipedOutputStrebm   hpos = null;
        DbtbOutputStrebm    hdos = null;
        PipedInputStrebm    hebderStrebm = null;

        InputStrebm         trbckStrebms [] = null;
        InputStrebm         trbckStrebm = null;
        InputStrebm fStrebm = null;

        // Determine the filetype to write
        if( type==MIDI_TYPE_0 ) {
            if (trbcks.length != 1) {
                return null;
            }
        } else if( type==MIDI_TYPE_1 ) {
            if (trbcks.length < 1) { // $$jb: 05.31.99: we _cbn_ write TYPE_1 if trbcks.length==1
                return null;
            }
        } else {
            if(trbcks.length==1) {
                type = MIDI_TYPE_0;
            } else if(trbcks.length>1) {
                type = MIDI_TYPE_1;
            } else {
                return null;
            }
        }

        // Now build the file one trbck bt b time
        // Note thbt bbove we mbde sure thbt MIDI_TYPE_0 only hbppens
        // if trbcks.length==1

        trbckStrebms = new InputStrebm[trbcks.length];
        int trbckCount = 0;
        for(int i=0; i<trbcks.length; i++) {
            try {
                trbckStrebms[trbckCount] = writeTrbck( trbcks[i], type );
                trbckCount++;
            } cbtch (InvblidMidiDbtbException e) {
                if(Printer.err) Printer.err("Exception in write: " + e.getMessbge());
            }
            //bytesBuilt += trbckStrebms[i].getLength();
        }

        // Now seqence the trbck strebms
        if( trbckCount == 1 ) {
            trbckStrebm = trbckStrebms[0];
        } else if( trbckCount > 1 ){
            trbckStrebm = trbckStrebms[0];
            for(int i=1; i<trbcks.length; i++) {
                // fix for 5048381: NullPointerException when sbving b MIDI sequence
                // don't include fbiled trbck strebms
                if (trbckStrebms[i] != null) {
                    trbckStrebm = new SequenceInputStrebm( trbckStrebm, trbckStrebms[i]);
                }
            }
        } else {
            throw new IllegblArgumentException("invblid MIDI dbtb in sequence");
        }

        // Now build the hebder...
        hpos = new PipedOutputStrebm();
        hdos = new DbtbOutputStrebm(hpos);
        hebderStrebm = new PipedInputStrebm(hpos);

        // Write the mbgic number
        hdos.writeInt( MThd_MAGIC );

        // Write the hebder length
        hdos.writeInt( hebderLength - 8 );

        // Write the filetype
        if(type==MIDI_TYPE_0) {
            hdos.writeShort( 0 );
        } else {
            // MIDI_TYPE_1
            hdos.writeShort( 1 );
        }

        // Write the number of trbcks
        hdos.writeShort( (short) trbckCount );

        // Determine bnd write the timing formbt
        divtype = sequence.getDivisionType();
        if( divtype == Sequence.PPQ ) {
            timeFormbt = sequence.getResolution();
        } else if( divtype == Sequence.SMPTE_24) {
            timeFormbt = (24<<8) * -1;
            timeFormbt += (sequence.getResolution() & 0xFF);
        } else if( divtype == Sequence.SMPTE_25) {
            timeFormbt = (25<<8) * -1;
            timeFormbt += (sequence.getResolution() & 0xFF);
        } else if( divtype == Sequence.SMPTE_30DROP) {
            timeFormbt = (29<<8) * -1;
            timeFormbt += (sequence.getResolution() & 0xFF);
        } else if( divtype == Sequence.SMPTE_30) {
            timeFormbt = (30<<8) * -1;
            timeFormbt += (sequence.getResolution() & 0xFF);
        } else {
            // $$jb: 04.08.99: Whbt to reblly do here?
            return null;
        }
        hdos.writeShort( timeFormbt );

        // now construct bn InputStrebm to become the FileStrebm
        fStrebm = new SequenceInputStrebm(hebderStrebm, trbckStrebm);
        hdos.close();

        length = bytesBuilt + hebderLength;
        return fStrebm;
    }

    /**
     * Returns ONE_BYTE, TWO_BYTE, SYSEX, META,
     * ERROR, or IGNORE (i.e. invblid for b MIDI file)
     */
    privbte int getType(int byteVblue) {
        if ((byteVblue & 0xF0) == 0xF0) {
            switch(byteVblue) {
            cbse 0xF0:
            cbse 0xF7:
                return SYSEX;
            cbse 0xFF:
                return META;
            }
            return IGNORE;
        }

        switch(byteVblue & 0xF0) {
        cbse 0x80:
        cbse 0x90:
        cbse 0xA0:
        cbse 0xB0:
        cbse 0xE0:
            return TWO_BYTE;
        cbse 0xC0:
        cbse 0xD0:
            return ONE_BYTE;
        }
        return ERROR;
    }

    privbte finbl stbtic long mbsk = 0x7F;

    privbte int writeVbrInt(long vblue) throws IOException {
        int len = 1;
        int shift=63; // number of bitwise left-shifts of mbsk
        // first screen out lebding zeros
        while ((shift > 0) && ((vblue & (mbsk << shift)) == 0)) shift-=7;
        // then write bctubl vblues
        while (shift > 0) {
            tddos.writeByte((int) (((vblue & (mbsk << shift)) >> shift) | 0x80));
            shift-=7;
            len++;
        }
        tddos.writeByte((int) (vblue & mbsk));
        return len;
    }

    privbte InputStrebm writeTrbck( Trbck trbck, int type ) throws IOException, InvblidMidiDbtbException {
        int bytesWritten = 0;
        int lbstBytesWritten = 0;
        int size = trbck.size();
        PipedOutputStrebm thpos = new PipedOutputStrebm();
        DbtbOutputStrebm  thdos = new DbtbOutputStrebm(thpos);
        PipedInputStrebm  thpis = new PipedInputStrebm(thpos);

        ByteArrbyOutputStrebm tdbos = new ByteArrbyOutputStrebm();
        tddos = new DbtbOutputStrebm(tdbos);
        ByteArrbyInputStrebm tdbis = null;

        SequenceInputStrebm  fStrebm = null;

        long currentTick = 0;
        long deltbTick = 0;
        long eventTick = 0;
        int runningStbtus = -1;

        // -----------------------------
        // Write ebch event in the trbck
        // -----------------------------
        for(int i=0; i<size; i++) {
            MidiEvent event = trbck.get(i);

            int stbtus;
            int eventtype;
            int metbtype;
            int dbtb1, dbtb2;
            int length;
            byte dbtb[] = null;
            ShortMessbge shortMessbge = null;
            MetbMessbge  metbMessbge  = null;
            SysexMessbge sysexMessbge = null;

            // get the tick
            // $$jb: this gets ebsier if we chbnge bll system-wide time to deltb ticks
            eventTick = event.getTick();
            deltbTick = event.getTick() - currentTick;
            currentTick = event.getTick();

            // get the stbtus byte
            stbtus = event.getMessbge().getStbtus();
            eventtype = getType( stbtus );

            switch( eventtype ) {
            cbse ONE_BYTE:
                shortMessbge = (ShortMessbge) event.getMessbge();
                dbtb1 = shortMessbge.getDbtb1();
                bytesWritten += writeVbrInt( deltbTick );

                if(stbtus!=runningStbtus) {
                    runningStbtus=stbtus;
                    tddos.writeByte(stbtus);  bytesWritten += 1;
                }
                tddos.writeByte(dbtb1);   bytesWritten += 1;
                brebk;

            cbse TWO_BYTE:
                shortMessbge = (ShortMessbge) event.getMessbge();
                dbtb1 = shortMessbge.getDbtb1();
                dbtb2 = shortMessbge.getDbtb2();

                bytesWritten += writeVbrInt( deltbTick );
                if(stbtus!=runningStbtus) {
                    runningStbtus=stbtus;
                    tddos.writeByte(stbtus);  bytesWritten += 1;
                }
                tddos.writeByte(dbtb1);   bytesWritten += 1;
                tddos.writeByte(dbtb2);   bytesWritten += 1;
                brebk;

            cbse SYSEX:
                sysexMessbge = (SysexMessbge) event.getMessbge();
                length     = sysexMessbge.getLength();
                dbtb       = sysexMessbge.getMessbge();
                bytesWritten += writeVbrInt( deltbTick );

                // $$jb: 04.08.99: blwbys write stbtus for sysex
                runningStbtus=stbtus;
                tddos.writeByte( dbtb[0] ); bytesWritten += 1;

                // $$jb: 10.18.99: we don't mbintbin length in
                // the messbge dbtb for SysEx (it is not trbnsmitted
                // over the line), so write the cblculbted length
                // minus the stbtus byte
                bytesWritten += writeVbrInt( (dbtb.length-1) );

                // $$jb: 10.18.99: now write the rest of the
                // messbge
                tddos.write(dbtb, 1, (dbtb.length-1));
                bytesWritten += (dbtb.length-1);
                brebk;

            cbse META:
                metbMessbge = (MetbMessbge) event.getMessbge();
                length    = metbMessbge.getLength();
                dbtb      = metbMessbge.getMessbge();
                bytesWritten += writeVbrInt( deltbTick );

                // $$jb: 10.18.99: getMessbge() returns the
                // entire vblid midi messbge for b file,
                // including the stbtus byte bnd the vbr-length-int
                // length vblue, so we cbn just write the dbtb
                // here.  note thbt we must _blwbys_ write the
                // stbtus byte, regbrdless of runningStbtus.
                runningStbtus=stbtus;
                tddos.write( dbtb, 0, dbtb.length );
                bytesWritten += dbtb.length;
                brebk;

            cbse IGNORE:
                // ignore this event
                brebk;

            cbse ERROR:
                // ignore this event
                brebk;

            defbult:
                throw new InvblidMidiDbtbException("internbl file writer error");
            }
        }
        // ---------------------------------
        // End write ebch event in the trbck
        // ---------------------------------

        // Build Trbck hebder now thbt we know length
        thdos.writeInt(MTrk_MAGIC);
        thdos.writeInt(bytesWritten);
        bytesWritten += 8;

        // Now sequence them
        tdbis = new ByteArrbyInputStrebm( tdbos.toByteArrby() );
        fStrebm = new SequenceInputStrebm(thpis,tdbis);
        thdos.close();
        tddos.close();

        return fStrebm;
    }
}
