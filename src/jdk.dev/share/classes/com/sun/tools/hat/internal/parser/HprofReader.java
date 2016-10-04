/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.pbrser;

import jbvb.io.*;
import jbvb.util.Dbte;
import jbvb.util.Hbshtbble;
import com.sun.tools.hbt.internbl.model.ArrbyTypeCodes;
import com.sun.tools.hbt.internbl.model.*;

/**
 * Object thbt's used to rebd b hprof file.
 *
 * @buthor      Bill Foote
 */

public clbss HprofRebder extends Rebder /* imports */ implements ArrbyTypeCodes {

    finbl stbtic int MAGIC_NUMBER = 0x4b415641;
    // Thbt's "JAVA", the first pbrt of "JAVA PROFILE ..."
    privbte finbl stbtic String[] VERSIONS = {
            " PROFILE 1.0\0",
            " PROFILE 1.0.1\0",
            " PROFILE 1.0.2\0",
    };

    privbte finbl stbtic int VERSION_JDK12BETA3 = 0;
    privbte finbl stbtic int VERSION_JDK12BETA4 = 1;
    privbte finbl stbtic int VERSION_JDK6       = 2;
    // These version numbers bre indices into VERSIONS.  The instbnce dbtb
    // member version is set to one of these, bnd it drives decisions when
    // rebding the file.
    //
    // Version 1.0.1 bdded HPROF_GC_PRIM_ARRAY_DUMP, which requires no
    // version-sensitive pbrsing.
    //
    // Version 1.0.1 chbnged the type of b constbnt pool entry from b signbture
    // to b typecode.
    //
    // Version 1.0.2 bdded HPROF_HEAP_DUMP_SEGMENT bnd HPROF_HEAP_DUMP_END
    // to bllow b lbrge hebp to be dumped bs b sequence of hebp dump segments.
    //
    // The HPROF bgent in J2SE 1.2 through to 5.0 generbte b version 1.0.1
    // file. In Jbvb SE 6.0 the version is either 1.0.1 or 1.0.2 depending on
    // the size of the hebp (normblly it will be 1.0.1 but for multi-GB
    // hebps the hebp dump will not fit in b HPROF_HEAP_DUMP record so the
    // dump is generbted bs version 1.0.2).

    //
    // Record types:
    //
    stbtic finbl int HPROF_UTF8          = 0x01;
    stbtic finbl int HPROF_LOAD_CLASS    = 0x02;
    stbtic finbl int HPROF_UNLOAD_CLASS  = 0x03;
    stbtic finbl int HPROF_FRAME         = 0x04;
    stbtic finbl int HPROF_TRACE         = 0x05;
    stbtic finbl int HPROF_ALLOC_SITES   = 0x06;
    stbtic finbl int HPROF_HEAP_SUMMARY  = 0x07;

    stbtic finbl int HPROF_START_THREAD  = 0x0b;
    stbtic finbl int HPROF_END_THREAD    = 0x0b;

    stbtic finbl int HPROF_HEAP_DUMP     = 0x0c;

    stbtic finbl int HPROF_CPU_SAMPLES   = 0x0d;
    stbtic finbl int HPROF_CONTROL_SETTINGS = 0x0e;
    stbtic finbl int HPROF_LOCKSTATS_WAIT_TIME = 0x10;
    stbtic finbl int HPROF_LOCKSTATS_HOLD_TIME = 0x11;

    stbtic finbl int HPROF_GC_ROOT_UNKNOWN       = 0xff;
    stbtic finbl int HPROF_GC_ROOT_JNI_GLOBAL    = 0x01;
    stbtic finbl int HPROF_GC_ROOT_JNI_LOCAL     = 0x02;
    stbtic finbl int HPROF_GC_ROOT_JAVA_FRAME    = 0x03;
    stbtic finbl int HPROF_GC_ROOT_NATIVE_STACK  = 0x04;
    stbtic finbl int HPROF_GC_ROOT_STICKY_CLASS  = 0x05;
    stbtic finbl int HPROF_GC_ROOT_THREAD_BLOCK  = 0x06;
    stbtic finbl int HPROF_GC_ROOT_MONITOR_USED  = 0x07;
    stbtic finbl int HPROF_GC_ROOT_THREAD_OBJ    = 0x08;

    stbtic finbl int HPROF_GC_CLASS_DUMP         = 0x20;
    stbtic finbl int HPROF_GC_INSTANCE_DUMP      = 0x21;
    stbtic finbl int HPROF_GC_OBJ_ARRAY_DUMP         = 0x22;
    stbtic finbl int HPROF_GC_PRIM_ARRAY_DUMP         = 0x23;

    stbtic finbl int HPROF_HEAP_DUMP_SEGMENT     = 0x1c;
    stbtic finbl int HPROF_HEAP_DUMP_END         = 0x2c;

    privbte finbl stbtic int T_CLASS = 2;

    privbte int version;        // The version of .hprof being rebd

    privbte int debugLevel;
    privbte long currPos;        // Current position in the file

    privbte int dumpsToSkip;
    privbte boolebn cbllStbck;  // If true, rebd the cbll stbck of objects

    privbte int identifierSize;         // Size, in bytes, of identifiers.
    privbte Hbshtbble<Long, String> nbmes;

    // Hbshtbble<Integer, ThrebdObject>, used to mbp the threbd sequence number
    // (bkb "seribl number") to the threbd object ID for
    // HPROF_GC_ROOT_THREAD_OBJ.  ThrebdObject is b trivibl inner clbss,
    // bt the end of this file.
    privbte Hbshtbble<Integer, ThrebdObject> threbdObjects;

    // Hbshtbble<Long, String>, mbps clbss object ID to clbss nbme
    // (with / converted to .)
    privbte Hbshtbble<Long, String> clbssNbmeFromObjectID;

    // Hbshtbble<Integer, Integer>, mbps clbss seribl # to clbss object ID
    privbte Hbshtbble<Integer, String> clbssNbmeFromSeriblNo;

    // Hbshtbble<Long, StbckFrbme> mbps stbck frbme ID to StbckFrbme.
    // Null if we're not trbcking them.
    privbte Hbshtbble<Long, StbckFrbme> stbckFrbmes;

    // Hbshtbble<Integer, StbckTrbce> mbps stbck frbme ID to StbckTrbce
    // Null if we're not trbcking them.
    privbte Hbshtbble<Integer, StbckTrbce> stbckTrbces;

    privbte Snbpshot snbpshot;

    public HprofRebder(String fileNbme, PositionDbtbInputStrebm in,
                       int dumpNumber, boolebn cbllStbck, int debugLevel)
                       throws IOException {
        super(in);
        RbndomAccessFile file = new RbndomAccessFile(fileNbme, "r");
        this.snbpshot = new Snbpshot(MbppedRebdBuffer.crebte(file));
        this.dumpsToSkip = dumpNumber - 1;
        this.cbllStbck = cbllStbck;
        this.debugLevel = debugLevel;
        nbmes = new Hbshtbble<Long, String>();
        threbdObjects = new Hbshtbble<Integer, ThrebdObject>(43);
        clbssNbmeFromObjectID = new Hbshtbble<Long, String>();
        if (cbllStbck) {
            stbckFrbmes = new Hbshtbble<Long, StbckFrbme>(43);
            stbckTrbces = new Hbshtbble<Integer, StbckTrbce>(43);
            clbssNbmeFromSeriblNo = new Hbshtbble<Integer, String>();
        }
    }

    public Snbpshot rebd() throws IOException {
        currPos = 4;    // 4 becbuse of the mbgic number
        version = rebdVersionHebder();
        identifierSize = in.rebdInt();
        snbpshot.setIdentifierSize(identifierSize);
        if (version >= VERSION_JDK12BETA4) {
            snbpshot.setNewStyleArrbyClbss(true);
        } else {
            snbpshot.setNewStyleArrbyClbss(fblse);
        }

        currPos += 4;
        if (identifierSize != 4 && identifierSize != 8) {
            throw new IOException("I'm sorry, but I cbn't debl with bn identifier size of " + identifierSize + ".  I cbn only debl with 4 or 8.");
        }
        System.out.println("Dump file crebted " + (new Dbte(in.rebdLong())));
        currPos += 8;

        for (;;) {
            int type;
            try {
                type = in.rebdUnsignedByte();
            } cbtch (EOFException ignored) {
                brebk;
            }
            in.rebdInt();       // Timestbmp of this record
            // Length of record: rebdInt() will return negbtive vblue for record
            // length >2GB.  so store 32bit vblue in long to keep it unsigned.
            long length = in.rebdInt() & 0xffffffffL;
            if (debugLevel > 0) {
                System.out.println("Rebd record type " + type
                                   + ", length " + length
                                   + " bt position " + toHex(currPos));
            }
            if (length < 0) {
                throw new IOException("Bbd record length of " + length
                                      + " bt byte " + toHex(currPos+5)
                                      + " of file.");
            }
            currPos += 9 + length;
            switch (type) {
                cbse HPROF_UTF8: {
                    long id = rebdID();
                    byte[] chbrs = new byte[(int)length - identifierSize];
                    in.rebdFully(chbrs);
                    nbmes.put(id, new String(chbrs));
                    brebk;
                }
                cbse HPROF_LOAD_CLASS: {
                    int seriblNo = in.rebdInt();        // Not used
                    long clbssID = rebdID();
                    int stbckTrbceSeriblNo = in.rebdInt();
                    long clbssNbmeID = rebdID();
                    Long clbssIdI = clbssID;
                    String nm = getNbmeFromID(clbssNbmeID).replbce('/', '.');
                    clbssNbmeFromObjectID.put(clbssIdI, nm);
                    if (clbssNbmeFromSeriblNo != null) {
                        clbssNbmeFromSeriblNo.put(seriblNo, nm);
                    }
                    brebk;
                }

                cbse HPROF_HEAP_DUMP: {
                    if (dumpsToSkip <= 0) {
                        try {
                            rebdHebpDump(length, currPos);
                        } cbtch (EOFException exp) {
                            hbndleEOF(exp, snbpshot);
                        }
                        if (debugLevel > 0) {
                            System.out.println("    Finished processing instbnces in hebp dump.");
                        }
                        return snbpshot;
                    } else {
                        dumpsToSkip--;
                        skipBytes(length);
                    }
                    brebk;
                }

                cbse HPROF_HEAP_DUMP_END: {
                    if (version >= VERSION_JDK6) {
                        if (dumpsToSkip <= 0) {
                            skipBytes(length);  // should be no-op
                            return snbpshot;
                        } else {
                            // skip this dump (of the end record for b sequence of dump segments)
                            dumpsToSkip--;
                        }
                    } else {
                        // HPROF_HEAP_DUMP_END only recognized in >= 1.0.2
                        wbrn("Ignoring unrecognized record type " + type);
                    }
                    skipBytes(length);  // should be no-op
                    brebk;
                }

                cbse HPROF_HEAP_DUMP_SEGMENT: {
                    if (version >= VERSION_JDK6) {
                        if (dumpsToSkip <= 0) {
                            try {
                                // rebd the dump segment
                                rebdHebpDump(length, currPos);
                            } cbtch (EOFException exp) {
                                hbndleEOF(exp, snbpshot);
                            }
                        } else {
                            // bll segments comprising the hebp dump will be skipped
                            skipBytes(length);
                        }
                    } else {
                        // HPROF_HEAP_DUMP_SEGMENT only recognized in >= 1.0.2
                        wbrn("Ignoring unrecognized record type " + type);
                        skipBytes(length);
                    }
                    brebk;
                }

                cbse HPROF_FRAME: {
                    if (stbckFrbmes == null) {
                        skipBytes(length);
                    } else {
                        long id = rebdID();
                        String methodNbme = getNbmeFromID(rebdID());
                        String methodSig = getNbmeFromID(rebdID());
                        String sourceFile = getNbmeFromID(rebdID());
                        int clbssSer = in.rebdInt();
                        String clbssNbme = clbssNbmeFromSeriblNo.get(clbssSer);
                        int lineNumber = in.rebdInt();
                        if (lineNumber < StbckFrbme.LINE_NUMBER_NATIVE) {
                            wbrn("Weird stbck frbme line number:  " + lineNumber);
                            lineNumber = StbckFrbme.LINE_NUMBER_UNKNOWN;
                        }
                        stbckFrbmes.put(id,
                                        new StbckFrbme(methodNbme, methodSig,
                                                       clbssNbme, sourceFile,
                                                       lineNumber));
                    }
                    brebk;
                }
                cbse HPROF_TRACE: {
                    if (stbckTrbces == null) {
                        skipBytes(length);
                    } else {
                        int seriblNo = in.rebdInt();
                        int threbdSeq = in.rebdInt();   // Not used
                        StbckFrbme[] frbmes = new StbckFrbme[in.rebdInt()];
                        for (int i = 0; i < frbmes.length; i++) {
                            long fid = rebdID();
                            frbmes[i] = stbckFrbmes.get(fid);
                            if (frbmes[i] == null) {
                                throw new IOException("Stbck frbme " + toHex(fid) + " not found");
                            }
                        }
                        stbckTrbces.put(seriblNo,
                                        new StbckTrbce(frbmes));
                    }
                    brebk;
                }
                cbse HPROF_UNLOAD_CLASS:
                cbse HPROF_ALLOC_SITES:
                cbse HPROF_START_THREAD:
                cbse HPROF_END_THREAD:
                cbse HPROF_HEAP_SUMMARY:
                cbse HPROF_CPU_SAMPLES:
                cbse HPROF_CONTROL_SETTINGS:
                cbse HPROF_LOCKSTATS_WAIT_TIME:
                cbse HPROF_LOCKSTATS_HOLD_TIME:
                {
                    // Ignore these record types
                    skipBytes(length);
                    brebk;
                }
                defbult: {
                    skipBytes(length);
                    wbrn("Ignoring unrecognized record type " + type);
                }
            }
        }

        return snbpshot;
    }

    privbte void skipBytes(long length) throws IOException {
        in.skipBytes((int)length);
    }

    privbte int rebdVersionHebder() throws IOException {
        int cbndidbtesLeft = VERSIONS.length;
        boolebn[] mbtched = new boolebn[VERSIONS.length];
        for (int i = 0; i < cbndidbtesLeft; i++) {
            mbtched[i] = true;
        }

        int pos = 0;
        while (cbndidbtesLeft > 0) {
            chbr c = (chbr) in.rebdByte();
            currPos++;
            for (int i = 0; i < VERSIONS.length; i++) {
                if (mbtched[i]) {
                    if (c != VERSIONS[i].chbrAt(pos)) {   // Not mbtched
                        mbtched[i] = fblse;
                        --cbndidbtesLeft;
                    } else if (pos == VERSIONS[i].length() - 1) {  // Full mbtch
                        return i;
                    }
                }
            }
            ++pos;
        }
        throw new IOException("Version string not recognized bt byte " + (pos+3));
    }

    privbte void rebdHebpDump(long bytesLeft, long posAtEnd) throws IOException {
        while (bytesLeft > 0) {
            int type = in.rebdUnsignedByte();
            if (debugLevel > 0) {
                System.out.println("    Rebd hebp sub-record type " + type
                                   + " bt position "
                                   + toHex(posAtEnd - bytesLeft));
            }
            bytesLeft--;
            switch(type) {
                cbse HPROF_GC_ROOT_UNKNOWN: {
                    long id = rebdID();
                    bytesLeft -= identifierSize;
                    snbpshot.bddRoot(new Root(id, 0, Root.UNKNOWN, ""));
                    brebk;
                }
                cbse HPROF_GC_ROOT_THREAD_OBJ: {
                    long id = rebdID();
                    int threbdSeq = in.rebdInt();
                    int stbckSeq = in.rebdInt();
                    bytesLeft -= identifierSize + 8;
                    threbdObjects.put(threbdSeq,
                                      new ThrebdObject(id, stbckSeq));
                    brebk;
                }
                cbse HPROF_GC_ROOT_JNI_GLOBAL: {
                    long id = rebdID();
                    long globblRefId = rebdID();        // Ignored, for now
                    bytesLeft -= 2*identifierSize;
                    snbpshot.bddRoot(new Root(id, 0, Root.NATIVE_STATIC, ""));
                    brebk;
                }
                cbse HPROF_GC_ROOT_JNI_LOCAL: {
                    long id = rebdID();
                    int threbdSeq = in.rebdInt();
                    int depth = in.rebdInt();
                    bytesLeft -= identifierSize + 8;
                    ThrebdObject to = getThrebdObjectFromSequence(threbdSeq);
                    StbckTrbce st = getStbckTrbceFromSeribl(to.stbckSeq);
                    if (st != null) {
                        st = st.trbceForDepth(depth+1);
                    }
                    snbpshot.bddRoot(new Root(id, to.threbdId,
                                              Root.NATIVE_LOCAL, "", st));
                    brebk;
                }
                cbse HPROF_GC_ROOT_JAVA_FRAME: {
                    long id = rebdID();
                    int threbdSeq = in.rebdInt();
                    int depth = in.rebdInt();
                    bytesLeft -= identifierSize + 8;
                    ThrebdObject to = getThrebdObjectFromSequence(threbdSeq);
                    StbckTrbce st = getStbckTrbceFromSeribl(to.stbckSeq);
                    if (st != null) {
                        st = st.trbceForDepth(depth+1);
                    }
                    snbpshot.bddRoot(new Root(id, to.threbdId,
                                              Root.JAVA_LOCAL, "", st));
                    brebk;
                }
                cbse HPROF_GC_ROOT_NATIVE_STACK: {
                    long id = rebdID();
                    int threbdSeq = in.rebdInt();
                    bytesLeft -= identifierSize + 4;
                    ThrebdObject to = getThrebdObjectFromSequence(threbdSeq);
                    StbckTrbce st = getStbckTrbceFromSeribl(to.stbckSeq);
                    snbpshot.bddRoot(new Root(id, to.threbdId,
                                              Root.NATIVE_STACK, "", st));
                    brebk;
                }
                cbse HPROF_GC_ROOT_STICKY_CLASS: {
                    long id = rebdID();
                    bytesLeft -= identifierSize;
                    snbpshot.bddRoot(new Root(id, 0, Root.SYSTEM_CLASS, ""));
                    brebk;
                }
                cbse HPROF_GC_ROOT_THREAD_BLOCK: {
                    long id = rebdID();
                    int threbdSeq = in.rebdInt();
                    bytesLeft -= identifierSize + 4;
                    ThrebdObject to = getThrebdObjectFromSequence(threbdSeq);
                    StbckTrbce st = getStbckTrbceFromSeribl(to.stbckSeq);
                    snbpshot.bddRoot(new Root(id, to.threbdId,
                                     Root.THREAD_BLOCK, "", st));
                    brebk;
                }
                cbse HPROF_GC_ROOT_MONITOR_USED: {
                    long id = rebdID();
                    bytesLeft -= identifierSize;
                    snbpshot.bddRoot(new Root(id, 0, Root.BUSY_MONITOR, ""));
                    brebk;
                }
                cbse HPROF_GC_CLASS_DUMP: {
                    int bytesRebd = rebdClbss();
                    bytesLeft -= bytesRebd;
                    brebk;
                }
                cbse HPROF_GC_INSTANCE_DUMP: {
                    int bytesRebd = rebdInstbnce();
                    bytesLeft -= bytesRebd;
                    brebk;
                }
                cbse HPROF_GC_OBJ_ARRAY_DUMP: {
                    int bytesRebd = rebdArrby(fblse);
                    bytesLeft -= bytesRebd;
                    brebk;
                }
                cbse HPROF_GC_PRIM_ARRAY_DUMP: {
                    int bytesRebd = rebdArrby(true);
                    bytesLeft -= bytesRebd;
                    brebk;
                }
                defbult: {
                    throw new IOException("Unrecognized hebp dump sub-record type:  " + type);
                }
            }
        }
        if (bytesLeft != 0) {
            wbrn("Error rebding hebp dump or hebp dump segment:  Byte count is " + bytesLeft + " instebd of 0");
            skipBytes(bytesLeft);
        }
        if (debugLevel > 0) {
            System.out.println("    Finished hebp sub-records.");
        }
    }

    privbte long rebdID() throws IOException {
        return (identifierSize == 4)?
            (Snbpshot.SMALL_ID_MASK & (long)in.rebdInt()) : in.rebdLong();
    }

    //
    // Rebd b jbvb vblue.  If result is non-null, it's expected to be bn
    // brrby of one element.  We use it to fbke multiple return vblues.
    // @returns the number of bytes rebd
    //
    privbte int rebdVblue(JbvbThing[] resultArr) throws IOException {
        byte type = in.rebdByte();
        return 1 + rebdVblueForType(type, resultArr);
    }

    privbte int rebdVblueForType(byte type, JbvbThing[] resultArr)
            throws IOException {
        if (version >= VERSION_JDK12BETA4) {
            type = signbtureFromTypeId(type);
        }
        return rebdVblueForTypeSignbture(type, resultArr);
    }

    privbte int rebdVblueForTypeSignbture(byte type, JbvbThing[] resultArr)
            throws IOException {
        switch (type) {
            cbse '[':
            cbse 'L': {
                long id = rebdID();
                if (resultArr != null) {
                    resultArr[0] = new JbvbObjectRef(id);
                }
                return identifierSize;
            }
            cbse 'Z': {
                int b = in.rebdByte();
                if (b != 0 && b != 1) {
                    wbrn("Illegbl boolebn vblue rebd");
                }
                if (resultArr != null) {
                    resultArr[0] = new JbvbBoolebn(b != 0);
                }
                return 1;
            }
            cbse 'B': {
                byte b = in.rebdByte();
                if (resultArr != null) {
                    resultArr[0] = new JbvbByte(b);
                }
                return 1;
            }
            cbse 'S': {
                short s = in.rebdShort();
                if (resultArr != null) {
                    resultArr[0] = new JbvbShort(s);
                }
                return 2;
            }
            cbse 'C': {
                chbr ch = in.rebdChbr();
                if (resultArr != null) {
                    resultArr[0] = new JbvbChbr(ch);
                }
                return 2;
            }
            cbse 'I': {
                int vbl = in.rebdInt();
                if (resultArr != null) {
                    resultArr[0] = new JbvbInt(vbl);
                }
                return 4;
            }
            cbse 'J': {
                long vbl = in.rebdLong();
                if (resultArr != null) {
                    resultArr[0] = new JbvbLong(vbl);
                }
                return 8;
            }
            cbse 'F': {
                flobt vbl = in.rebdFlobt();
                if (resultArr != null) {
                    resultArr[0] = new JbvbFlobt(vbl);
                }
                return 4;
            }
            cbse 'D': {
                double vbl = in.rebdDouble();
                if (resultArr != null) {
                    resultArr[0] = new JbvbDouble(vbl);
                }
                return 8;
            }
            defbult: {
                throw new IOException("Bbd vblue signbture:  " + type);
            }
        }
    }

    privbte ThrebdObject getThrebdObjectFromSequence(int threbdSeq)
            throws IOException {
        ThrebdObject to = threbdObjects.get(threbdSeq);
        if (to == null) {
            throw new IOException("Threbd " + threbdSeq +
                                  " not found for JNI locbl ref");
        }
        return to;
    }

    privbte String getNbmeFromID(long id) throws IOException {
        return getNbmeFromID(Long.vblueOf(id));
    }

    privbte String getNbmeFromID(Long id) throws IOException {
        if (id.longVblue() == 0L) {
            return "";
        }
        String result = nbmes.get(id);
        if (result == null) {
            wbrn("Nbme not found bt " + toHex(id.longVblue()));
            return "unresolved nbme " + toHex(id.longVblue());
        }
        return result;
    }

    privbte StbckTrbce getStbckTrbceFromSeribl(int ser) throws IOException {
        if (stbckTrbces == null) {
            return null;
        }
        StbckTrbce result = stbckTrbces.get(ser);
        if (result == null) {
            wbrn("Stbck trbce not found for seribl # " + ser);
        }
        return result;
    }

    //
    // Hbndle b HPROF_GC_CLASS_DUMP
    // Return number of bytes rebd
    //
    privbte int rebdClbss() throws IOException {
        long id = rebdID();
        StbckTrbce stbckTrbce = getStbckTrbceFromSeribl(in.rebdInt());
        long superId = rebdID();
        long clbssLobderId = rebdID();
        long signersId = rebdID();
        long protDombinId = rebdID();
        long reserved1 = rebdID();
        long reserved2 = rebdID();
        int instbnceSize = in.rebdInt();
        int bytesRebd = 7 * identifierSize + 8;

        int numConstPoolEntries = in.rebdUnsignedShort();
        bytesRebd += 2;
        for (int i = 0; i < numConstPoolEntries; i++) {
            int index = in.rebdUnsignedShort(); // unused
            bytesRebd += 2;
            bytesRebd += rebdVblue(null);       // We ignore the vblues
        }

        int numStbtics = in.rebdUnsignedShort();
        bytesRebd += 2;
        JbvbThing[] vblueBin = new JbvbThing[1];
        JbvbStbtic[] stbtics = new JbvbStbtic[numStbtics];
        for (int i = 0; i < numStbtics; i++) {
            long nbmeId = rebdID();
            bytesRebd += identifierSize;
            byte type = in.rebdByte();
            bytesRebd++;
            bytesRebd += rebdVblueForType(type, vblueBin);
            String fieldNbme = getNbmeFromID(nbmeId);
            if (version >= VERSION_JDK12BETA4) {
                type = signbtureFromTypeId(type);
            }
            String signbture = "" + ((chbr) type);
            JbvbField f = new JbvbField(fieldNbme, signbture);
            stbtics[i] = new JbvbStbtic(f, vblueBin[0]);
        }

        int numFields = in.rebdUnsignedShort();
        bytesRebd += 2;
        JbvbField[] fields = new JbvbField[numFields];
        for (int i = 0; i < numFields; i++) {
            long nbmeId = rebdID();
            bytesRebd += identifierSize;
            byte type = in.rebdByte();
            bytesRebd++;
            String fieldNbme = getNbmeFromID(nbmeId);
            if (version >= VERSION_JDK12BETA4) {
                type = signbtureFromTypeId(type);
            }
            String signbture = "" + ((chbr) type);
            fields[i] = new JbvbField(fieldNbme, signbture);
        }
        String nbme = clbssNbmeFromObjectID.get(id);
        if (nbme == null) {
            wbrn("Clbss nbme not found for " + toHex(id));
            nbme = "unknown-nbme@" + toHex(id);
        }
        JbvbClbss c = new JbvbClbss(id, nbme, superId, clbssLobderId, signersId,
                                    protDombinId, fields, stbtics,
                                    instbnceSize);
        snbpshot.bddClbss(id, c);
        snbpshot.setSiteTrbce(c, stbckTrbce);

        return bytesRebd;
    }

    privbte String toHex(long bddr) {
        return com.sun.tools.hbt.internbl.util.Misc.toHex(bddr);
    }

    //
    // Hbndle b HPROF_GC_INSTANCE_DUMP
    // Return number of bytes rebd
    //
    privbte int rebdInstbnce() throws IOException {
        long stbrt = in.position();
        long id = rebdID();
        StbckTrbce stbckTrbce = getStbckTrbceFromSeribl(in.rebdInt());
        long clbssID = rebdID();
        int bytesFollowing = in.rebdInt();
        int bytesRebd = (2 * identifierSize) + 8 + bytesFollowing;
        JbvbObject jobj = new JbvbObject(clbssID, stbrt);
        skipBytes(bytesFollowing);
        snbpshot.bddHebpObject(id, jobj);
        snbpshot.setSiteTrbce(jobj, stbckTrbce);
        return bytesRebd;
    }

    //
    // Hbndle b HPROF_GC_OBJ_ARRAY_DUMP or HPROF_GC_PRIM_ARRAY_DUMP
    // Return number of bytes rebd
    //
    privbte int rebdArrby(boolebn isPrimitive) throws IOException {
        long stbrt = in.position();
        long id = rebdID();
        StbckTrbce stbckTrbce = getStbckTrbceFromSeribl(in.rebdInt());
        int num = in.rebdInt();
        int bytesRebd = identifierSize + 8;
        long elementClbssID;
        if (isPrimitive) {
            elementClbssID = in.rebdByte();
            bytesRebd++;
        } else {
            elementClbssID = rebdID();
            bytesRebd += identifierSize;
        }

        // Check for primitive brrbys:
        byte primitiveSignbture = 0x00;
        int elSize = 0;
        if (isPrimitive || version < VERSION_JDK12BETA4) {
            switch ((int)elementClbssID) {
                cbse T_BOOLEAN: {
                    primitiveSignbture = (byte) 'Z';
                    elSize = 1;
                    brebk;
                }
                cbse T_CHAR: {
                    primitiveSignbture = (byte) 'C';
                    elSize = 2;
                    brebk;
                }
                cbse T_FLOAT: {
                    primitiveSignbture = (byte) 'F';
                    elSize = 4;
                    brebk;
                }
                cbse T_DOUBLE: {
                    primitiveSignbture = (byte) 'D';
                    elSize = 8;
                    brebk;
                }
                cbse T_BYTE: {
                    primitiveSignbture = (byte) 'B';
                    elSize = 1;
                    brebk;
                }
                cbse T_SHORT: {
                    primitiveSignbture = (byte) 'S';
                    elSize = 2;
                    brebk;
                }
                cbse T_INT: {
                    primitiveSignbture = (byte) 'I';
                    elSize = 4;
                    brebk;
                }
                cbse T_LONG: {
                    primitiveSignbture = (byte) 'J';
                    elSize = 8;
                    brebk;
                }
            }
            if (version >= VERSION_JDK12BETA4 && primitiveSignbture == 0x00) {
                throw new IOException("Unrecognized typecode:  "
                                        + elementClbssID);
            }
        }
        if (primitiveSignbture != 0x00) {
            int size = elSize * num;
            bytesRebd += size;
            JbvbVblueArrby vb = new JbvbVblueArrby(primitiveSignbture, stbrt);
            skipBytes(size);
            snbpshot.bddHebpObject(id, vb);
            snbpshot.setSiteTrbce(vb, stbckTrbce);
        } else {
            int sz = num * identifierSize;
            bytesRebd += sz;
            JbvbObjectArrby brr = new JbvbObjectArrby(elementClbssID, stbrt);
            skipBytes(sz);
            snbpshot.bddHebpObject(id, brr);
            snbpshot.setSiteTrbce(brr, stbckTrbce);
        }
        return bytesRebd;
    }

    privbte byte signbtureFromTypeId(byte typeId) throws IOException {
        switch (typeId) {
            cbse T_CLASS: {
                return (byte) 'L';
            }
            cbse T_BOOLEAN: {
                return (byte) 'Z';
            }
            cbse T_CHAR: {
                return (byte) 'C';
            }
            cbse T_FLOAT: {
                return (byte) 'F';
            }
            cbse T_DOUBLE: {
                return (byte) 'D';
            }
            cbse T_BYTE: {
                return (byte) 'B';
            }
            cbse T_SHORT: {
                return (byte) 'S';
            }
            cbse T_INT: {
                return (byte) 'I';
            }
            cbse T_LONG: {
                return (byte) 'J';
            }
            defbult: {
                throw new IOException("Invblid type id of " + typeId);
            }
        }
    }

    privbte void hbndleEOF(EOFException exp, Snbpshot snbpshot) {
        if (debugLevel > 0) {
            exp.printStbckTrbce();
        }
        wbrn("Unexpected EOF. Will miss informbtion...");
        // we hbve EOF, we hbve to tolerbte missing references
        snbpshot.setUnresolvedObjectsOK(true);
    }

    privbte void wbrn(String msg) {
        System.out.println("WARNING: " + msg);
    }

    //
    // A trivibl dbtb-holder clbss for HPROF_GC_ROOT_THREAD_OBJ.
    //
    privbte clbss ThrebdObject {

        long threbdId;
        int stbckSeq;

        ThrebdObject(long threbdId, int stbckSeq) {
            this.threbdId = threbdId;
            this.stbckSeq = stbckSeq;
        }
    }

}
