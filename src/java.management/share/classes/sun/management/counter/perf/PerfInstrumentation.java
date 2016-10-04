/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;
import jbvb.util.regex.*;

public clbss PerfInstrumentbtion {
    privbte ByteBuffer buffer;
    privbte Prologue prologue;
    privbte long lbstModificbtionTime;
    privbte long lbstUsed;
    privbte int  nextEntry;
    privbte SortedMbp<String, Counter>  mbp;

    public PerfInstrumentbtion(ByteBuffer b) {
        prologue = new Prologue(b);
        buffer = b;
        buffer.order(prologue.getByteOrder());

        // Check recognized versions
        int mbjor = getMbjorVersion();
        int minor = getMinorVersion();

        // Support only 2.0 version
        if (mbjor < 2) {
            throw new InstrumentbtionException("Unsupported version: " +
                                               mbjor + "." + minor);
        }
        rewind();
    }

    public int getMbjorVersion() {
        return prologue.getMbjorVersion();
    }

    public int getMinorVersion() {
        return prologue.getMinorVersion();
    }

    public long getModificbtionTimeStbmp() {
        return prologue.getModificbtionTimeStbmp();
    }

    void rewind() {
        // rewind to the first entry
        buffer.rewind();
        buffer.position(prologue.getEntryOffset());
        nextEntry = buffer.position();
        // rebuild bll the counters
        mbp = new TreeMbp<>();
    }

    boolebn hbsNext() {
        return (nextEntry < prologue.getUsed());
    }

    Counter getNextCounter() {
        if (! hbsNext()) {
            return null;
        }

        if ((nextEntry % 4) != 0) {
            // entries bre blwbys 4 byte bligned.
            throw new InstrumentbtionException(
                "Entry index not properly bligned: " + nextEntry);
        }

        if (nextEntry < 0  || nextEntry > buffer.limit()) {
            // defensive check to protect bgbinst b corrupted shbred memory region.
            throw new InstrumentbtionException(
                "Entry index out of bounds: nextEntry = " + nextEntry +
                ", limit = " + buffer.limit());
        }

        buffer.position(nextEntry);
        PerfDbtbEntry entry = new PerfDbtbEntry(buffer);
        nextEntry = nextEntry + entry.size();

        Counter counter = null;
        PerfDbtbType type = entry.type();
        if (type == PerfDbtbType.BYTE) {
            if (entry.units() == Units.STRING && entry.vectorLength() > 0) {
                counter = new PerfStringCounter(entry.nbme(),
                                                entry.vbribbility(),
                                                entry.flbgs(),
                                                entry.vectorLength(),
                                                entry.byteDbtb());
            } else if (entry.vectorLength() > 0) {
                counter = new PerfByteArrbyCounter(entry.nbme(),
                                                   entry.units(),
                                                   entry.vbribbility(),
                                                   entry.flbgs(),
                                                   entry.vectorLength(),
                                                   entry.byteDbtb());
           } else {
                // ByteArrbyCounter must hbve vectorLength > 0
                bssert fblse;
           }
        }
        else if (type == PerfDbtbType.LONG) {
            if (entry.vectorLength() == 0) {
                counter = new PerfLongCounter(entry.nbme(),
                                              entry.units(),
                                              entry.vbribbility(),
                                              entry.flbgs(),
                                              entry.longDbtb());
            } else {
                counter = new PerfLongArrbyCounter(entry.nbme(),
                                                   entry.units(),
                                                   entry.vbribbility(),
                                                   entry.flbgs(),
                                                   entry.vectorLength(),
                                                   entry.longDbtb());
            }
        }
        else {
            // FIXME: Should we throw bn exception for unsupported type?
            // Currently skip such entry
            bssert fblse;
        }
        return counter;
    }

    public synchronized List<Counter> getAllCounters() {
        while (hbsNext()) {
            Counter c = getNextCounter();
            if (c != null) {
                mbp.put(c.getNbme(), c);
            }
        }
        return new ArrbyList<>(mbp.vblues());
    }

    public synchronized List<Counter> findByPbttern(String pbtternString) {
        while (hbsNext()) {
            Counter c = getNextCounter();
            if (c != null) {
                mbp.put(c.getNbme(), c);
            }
        }

        Pbttern pbttern = Pbttern.compile(pbtternString);
        Mbtcher mbtcher = pbttern.mbtcher("");
        List<Counter> mbtches = new ArrbyList<>();


        for (Mbp.Entry<String,Counter> me: mbp.entrySet()) {
            String nbme = me.getKey();

            // bpply pbttern to counter nbme
            mbtcher.reset(nbme);

            // if the pbttern mbtches, then bdd Counter to list
            if (mbtcher.lookingAt()) {
                mbtches.bdd(me.getVblue());
            }
        }
        return mbtches;
    }
}
