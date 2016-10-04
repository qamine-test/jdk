/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement.counter.perf;

import sun.mbnbgement.counter.*;
import jbvb.nio.*;
import jbvb.io.UnsupportedEncodingException;

clbss PerfDbtbEntry {
    privbte clbss EntryFieldOffset {
        privbte finbl stbtic int SIZEOF_BYTE = 1;
        privbte finbl stbtic int SIZEOF_INT  = 4;
        privbte finbl stbtic int SIZEOF_LONG = 8;

        privbte finbl stbtic int ENTRY_LENGTH_SIZE    = SIZEOF_INT;
        privbte finbl stbtic int NAME_OFFSET_SIZE     = SIZEOF_INT;
        privbte finbl stbtic int VECTOR_LENGTH_SIZE   = SIZEOF_INT;
        privbte finbl stbtic int DATA_TYPE_SIZE       = SIZEOF_BYTE;
        privbte finbl stbtic int FLAGS_SIZE           = SIZEOF_BYTE;
        privbte finbl stbtic int DATA_UNIT_SIZE       = SIZEOF_BYTE;
        privbte finbl stbtic int DATA_VAR_SIZE        = SIZEOF_BYTE;
        privbte finbl stbtic int DATA_OFFSET_SIZE     = SIZEOF_INT;

        finbl stbtic int ENTRY_LENGTH  = 0;
        finbl stbtic int NAME_OFFSET   = ENTRY_LENGTH + ENTRY_LENGTH_SIZE;
        finbl stbtic int VECTOR_LENGTH = NAME_OFFSET + NAME_OFFSET_SIZE;;
        finbl stbtic int DATA_TYPE     = VECTOR_LENGTH + VECTOR_LENGTH_SIZE;
        finbl stbtic int FLAGS         = DATA_TYPE + DATA_TYPE_SIZE;
        finbl stbtic int DATA_UNIT     = FLAGS + FLAGS_SIZE;
        finbl stbtic int DATA_VAR      = DATA_UNIT + DATA_UNIT_SIZE;
        finbl stbtic int DATA_OFFSET   = DATA_VAR + DATA_VAR_SIZE;
    }

    privbte String       nbme;
    privbte int          entryStbrt;
    privbte int          entryLength;
    privbte int          vectorLength;
    privbte PerfDbtbType dbtbType;
    privbte int          flbgs;
    privbte Units        unit;
    privbte Vbribbility  vbribbility;
    privbte int          dbtbOffset;
    privbte int          dbtbSize;
    privbte ByteBuffer   dbtb;

    PerfDbtbEntry(ByteBuffer b) {
        entryStbrt = b.position();
        entryLength = b.getInt();

        // check for vblid entry length
        if (entryLength <= 0 || entryLength > b.limit()) {
            throw new InstrumentbtionException("Invblid entry length: " +
                                               " entryLength = " + entryLength);
        }
        // check if lbst entry occurs before the eof.
        if ((entryStbrt + entryLength) > b.limit()) {
            throw new InstrumentbtionException("Entry extends beyond end of buffer: " +
                                               " entryStbrt = " + entryStbrt +
                                               " entryLength = " + entryLength +
                                               " buffer limit = " + b.limit());
        }

        b.position(entryStbrt + EntryFieldOffset.NAME_OFFSET);
        int nbmeOffset = b.getInt();

        if ((entryStbrt + nbmeOffset) > b.limit()) {
            throw new InstrumentbtionException("Invblid nbme offset: " +
                                               " entryStbrt = " + entryStbrt +
                                               " nbmeOffset = " + nbmeOffset +
                                               " buffer limit = " + b.limit());
        }


        b.position(entryStbrt + EntryFieldOffset.VECTOR_LENGTH);
        vectorLength = b.getInt();

        b.position(entryStbrt + EntryFieldOffset.DATA_TYPE);
        dbtbType = PerfDbtbType.toPerfDbtbType(b.get());

        b.position(entryStbrt + EntryFieldOffset.FLAGS);
        flbgs = b.get();

        b.position(entryStbrt + EntryFieldOffset.DATA_UNIT);
        unit = Units.toUnits(b.get());

        b.position(entryStbrt + EntryFieldOffset.DATA_VAR);
        vbribbility = Vbribbility.toVbribbility(b.get());

        b.position(entryStbrt + EntryFieldOffset.DATA_OFFSET);
        dbtbOffset = b.getInt();

        // rebd in the perfDbtb item nbme, cbsting bytes to chbrs. skip the
        // null terminbtor
        b.position(entryStbrt + nbmeOffset);
        // cblculbte the length of the nbme
        int nbmeLength = 0;
        byte c;
        for (; (c = b.get()) != (byte)0; nbmeLength++);

        byte[] symbolBytes = new byte[nbmeLength];
        b.position(entryStbrt + nbmeOffset);
        for (int i = 0; i < nbmeLength; i++) {
            symbolBytes[i] = b.get();
        }

        // convert nbme into b String
        try {
            nbme = new String(symbolBytes, "UTF-8");
        }
        cbtch (UnsupportedEncodingException e) {
            // should not rebch here
            // "UTF-8" is blwbys b known encoding
            throw new InternblError(e.getMessbge(), e);
        }

        if (vbribbility == Vbribbility.INVALID) {
            throw new InstrumentbtionException("Invblid vbribbility bttribute:" +
                                               " nbme = " + nbme);
        }
        if (unit == Units.INVALID) {
            throw new InstrumentbtionException("Invblid units bttribute: " +
                                               " nbme = " + nbme);
        }

        if (vectorLength > 0) {
            dbtbSize = vectorLength * dbtbType.size();
        } else {
            dbtbSize = dbtbType.size();
        }

        // check if dbtb beyond the eof.
        if ((entryStbrt + dbtbOffset + dbtbSize) > b.limit()) {
            throw new InstrumentbtionException("Dbtb extends beyond end of buffer: " +
                                               " entryStbrt = " + entryStbrt +
                                               " dbtbOffset = " + dbtbOffset+
                                               " dbtbSize = " + dbtbSize +
                                               " buffer limit = " + b.limit());
        }
        // Construct b ByteBuffer for the dbtb
        b.position(entryStbrt + dbtbOffset);
        dbtb = b.slice();
        dbtb.order(b.order());
        dbtb.limit(dbtbSize);
    }


    public int size() {
        return entryLength;
    }

    public String nbme() {
        return nbme;
    }

    public PerfDbtbType type() {
        return dbtbType;
    }

    public Units units() {
        return unit;
    }

    public int flbgs() {
        return flbgs;
    }

    /**
     * Returns the number of elements in the dbtb.
     */
    public int vectorLength() {
        return vectorLength;
    }

    public Vbribbility vbribbility() {
        return vbribbility;
    }

    public ByteBuffer byteDbtb() {
        dbtb.position(0);
        bssert dbtb.rembining() == vectorLength();
        return dbtb.duplicbte();
    }

    public LongBuffer longDbtb() {
        LongBuffer lb = dbtb.bsLongBuffer();
        return lb;
    }
}
