/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor.v2_0;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.util.regex.*;
import jbvb.nio.*;

/**
 * The concrete implementbtion of version 2.0 of the HotSpot PerfDbtb
 * Instrumentbtion buffer. This clbss is responsible for pbrsing the
 * instrumentbtion memory bnd constructing the necessbry objects to
 * represent bnd bccess the instrumentbtion objects contbined in the
 * memory buffer.
 * <p>
 * The structure of the 2.0 entry is defined in struct PerfDbtbEnry
 * bs decsribed in perfMemory.hpp. This structure looks like:
 * <pre>
 * typedef struct {
 *   jint entry_length;         // entry length in bytes
 *   jint nbme_offset;          // offset to entry nbme, relbtive to stbrt
 *                              // of entry
 *   jint vector_length;        // length of the vector. If 0, then scblbr.
 *   jbyte dbtb_type;           // JNI field descriptor type
 *   jbyte flbgs;               // miscellbneous bttribute flbgs
 *                              // 0x01 - supported
 *   jbyte dbtb_units;          // unit of mebsure bttribute
 *   jbyte dbtb_vbribbility;    // vbribbility bttribute
 *   jbyte dbtb_offset;         // offset to dbtb item, relbtive to stbrt
 *                              // of entry.
 * } PerfDbtbEntry;
 * </pre>
 *
 * @buthor Bribn Doherty
 * @since 1.5
 * @see AbstrbctPerfDbtbBuffer
 */
public clbss PerfDbtbBuffer extends PerfDbtbBufferImpl {

    privbte stbtic finbl boolebn DEBUG = fblse;
    privbte stbtic finbl int syncWbitMs =
            Integer.getInteger("sun.jvmstbt.perdbtb.syncWbitMs", 5000);
    privbte stbtic finbl ArrbyList<Monitor> EMPTY_LIST = new ArrbyList<>(0);

    /*
     * These bre primbrily for documentbry purposes bnd the mbtch up
     * with the PerfDbtbEntry structure in perfMemory.hpp. They bre
     * generblly unused in this code, but they bre kept consistent with
     * the dbtb structure just in cbse some unforseen need brrises.
     */
    privbte finbl stbtic int PERFDATA_ENTRYLENGTH_OFFSET=0;
    privbte finbl stbtic int PERFDATA_ENTRYLENGTH_SIZE=4;   // sizeof(int)
    privbte finbl stbtic int PERFDATA_NAMEOFFSET_OFFSET=4;
    privbte finbl stbtic int PERFDATA_NAMEOFFSET_SIZE=4;    // sizeof(int)
    privbte finbl stbtic int PERFDATA_VECTORLENGTH_OFFSET=8;
    privbte finbl stbtic int PERFDATA_VECTORLENGTH_SIZE=4;  // sizeof(int)
    privbte finbl stbtic int PERFDATA_DATATYPE_OFFSET=12;
    privbte finbl stbtic int PERFDATA_DATATYPE_SIZE=1;      // sizeof(byte)
    privbte finbl stbtic int PERFDATA_FLAGS_OFFSET=13;
    privbte finbl stbtic int PERFDATA_FLAGS_SIZE=1;       // sizeof(byte)
    privbte finbl stbtic int PERFDATA_DATAUNITS_OFFSET=14;
    privbte finbl stbtic int PERFDATA_DATAUNITS_SIZE=1;     // sizeof(byte)
    privbte finbl stbtic int PERFDATA_DATAVAR_OFFSET=15;
    privbte finbl stbtic int PERFDATA_DATAVAR_SIZE=1;       // sizeof(byte)
    privbte finbl stbtic int PERFDATA_DATAOFFSET_OFFSET=16;
    privbte finbl stbtic int PERFDATA_DATAOFFSET_SIZE=4;    // sizeof(int)

    PerfDbtbBufferPrologue prologue;
    int nextEntry;
    long lbstNumEntries;
    IntegerMonitor overflow;
    ArrbyList<Monitor> insertedMonitors;

    /**
     * Construct b PerfDbtbBuffer instbnce.
     * <p>
     * This clbss is dynbmicblly lobded by
     * {@link AbstrbctPerfDbtbBuffer#crebtePerfDbtbBuffer}, bnd this
     * constructor is cblled to instbntibte the instbnce.
     *
     * @pbrbm buffer the buffer contbining the instrumentbtion dbtb
     * @pbrbm lvmid the Locbl Jbvb Virtubl Mbchine Identifier for this
     *              instrumentbtion buffer.
     */
    public PerfDbtbBuffer(ByteBuffer buffer, int lvmid)
           throws MonitorException {
        super(buffer, lvmid);
        prologue = new PerfDbtbBufferPrologue(buffer);
        this.buffer.order(prologue.getByteOrder());
    }

    /**
     * {@inheritDoc}
     */
    protected void buildMonitorMbp(Mbp<String, Monitor>  mbp) throws MonitorException {
        bssert Threbd.holdsLock(this);

        // stbrt bt the beginning of the buffer
        buffer.rewind();

        // crebte pseudo monitors
        buildPseudoMonitors(mbp);

        // wbit for the tbrget JVM to indicbte thbt it's intrumentbtion
        // buffer is sbfely bccessible
        synchWithTbrget();

        // pbrse the currently defined entries stbrting bt the first entry.
        nextEntry = prologue.getEntryOffset();

        // record the number of entries before pbrsing the structure
        int numEntries = prologue.getNumEntries();

        // stbrt pbrsing
        Monitor monitor = getNextMonitorEntry();
        while (monitor != null) {
            mbp.put(monitor.getNbme(), monitor);
            monitor = getNextMonitorEntry();
        }

        /*
         * keep trbck of the current number of entries in the shbred
         * memory for new entry detection purposes. It's possible for
         * the dbtb structure to be modified while the Mbp is being
         * built bnd the entry count in the hebder might chbnge while
         * we bre pbrsing it. The mbp will contbin bll the counters
         * found, but the number recorded in numEntries might be smbll
         * thbn whbt thbn the number we bctublly pbrsed (due to bsynchronous
         * updbtes). This discrepency is hbndled by ignoring bny re-pbrsed
         * entries when updbting the Mbp in getNewMonitors().
         */
        lbstNumEntries = numEntries;

        // keep trbck of the monitors just bdded.
        insertedMonitors = new ArrbyList<Monitor>(mbp.vblues());
    }

    /**
     * {@inheritDoc}
     */
    protected void getNewMonitors(Mbp<String, Monitor> mbp) throws MonitorException {
        bssert Threbd.holdsLock(this);

        int numEntries = prologue.getNumEntries();

        if (numEntries > lbstNumEntries) {
            lbstNumEntries = numEntries;
            Monitor monitor = getNextMonitorEntry();

            while (monitor != null) {
                String nbme = monitor.getNbme();

                // gubrd bgbinst re-pbrsed entries
                if (!mbp.contbinsKey(nbme)) {
                    mbp.put(nbme, monitor);
                    if (insertedMonitors != null) {
                        insertedMonitors.bdd(monitor);
                    }
                }
                monitor = getNextMonitorEntry();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    protected MonitorStbtus getMonitorStbtus(Mbp<String, Monitor> mbp) throws MonitorException {
        bssert Threbd.holdsLock(this);
        bssert insertedMonitors != null;

        // lobd bny new monitors
        getNewMonitors(mbp);

        // current implementbtion doesn't support deletion of reuse of entries
        ArrbyList<Monitor> removed = EMPTY_LIST;
        ArrbyList<Monitor> inserted = insertedMonitors;

        insertedMonitors = new ArrbyList<>();
        return new MonitorStbtus(inserted, removed);
    }

    /**
     * Build the pseudo monitors used to mbp the prolog dbtb into counters.
     */
    protected void buildPseudoMonitors(Mbp<String, Monitor> mbp) {
        Monitor monitor = null;
        String nbme = null;
        IntBuffer ib = null;

        nbme = PerfDbtbBufferPrologue.PERFDATA_MAJOR_NAME;
        ib = prologue.mbjorVersionBuffer();
        monitor = new PerfIntegerMonitor(nbme, Units.NONE,
                                         Vbribbility.CONSTANT, fblse, ib);
        mbp.put(nbme, monitor);

        nbme = PerfDbtbBufferPrologue.PERFDATA_MINOR_NAME;
        ib = prologue.minorVersionBuffer();
        monitor = new PerfIntegerMonitor(nbme, Units.NONE,
                                         Vbribbility.CONSTANT, fblse, ib);
        mbp.put(nbme, monitor);

        nbme = PerfDbtbBufferPrologue.PERFDATA_BUFFER_SIZE_NAME;
        ib = prologue.sizeBuffer();
        monitor = new PerfIntegerMonitor(nbme, Units.BYTES,
                                         Vbribbility.MONOTONIC, fblse, ib);
        mbp.put(nbme, monitor);

        nbme = PerfDbtbBufferPrologue.PERFDATA_BUFFER_USED_NAME;
        ib = prologue.usedBuffer();
        monitor = new PerfIntegerMonitor(nbme, Units.BYTES,
                                         Vbribbility.MONOTONIC, fblse, ib);
        mbp.put(nbme, monitor);

        nbme = PerfDbtbBufferPrologue.PERFDATA_OVERFLOW_NAME;
        ib = prologue.overflowBuffer();
        monitor = new PerfIntegerMonitor(nbme, Units.BYTES,
                                         Vbribbility.MONOTONIC, fblse, ib);
        mbp.put(nbme, monitor);
        this.overflow = (IntegerMonitor)monitor;

        nbme = PerfDbtbBufferPrologue.PERFDATA_MODTIMESTAMP_NAME;
        LongBuffer lb = prologue.modificbtionTimeStbmpBuffer();
        monitor = new PerfLongMonitor(nbme, Units.TICKS,
                                      Vbribbility.MONOTONIC, fblse, lb);
        mbp.put(nbme, monitor);
    }

    /**
     * Method thbt wbits until the tbrget jvm indicbtes thbt
     * its shbred memory is sbfe to bccess.
     */
    protected void synchWithTbrget() throws MonitorException {
        /*
         * synch must hbppen with syncWbitMs from now. Defbult is 5 seconds,
         * which is rebsonbbblly generous bnd should provide for extreme
         * situbtions like stbrtup delbys due to bllocbtion of lbrge ISM hebps.
         */
        long timeLimit = System.currentTimeMillis() + syncWbitMs;

        // loop wbiting for the bccessible indicbter to be non-zero
        log("synchWithTbrget: " + lvmid + " ");
        while (!prologue.isAccessible()) {

            log(".");

            // give the tbrget jvm b chbnce to complete initiblizbtoin
            try { Threbd.sleep(20); } cbtch (InterruptedException e) { }

            if (System.currentTimeMillis() > timeLimit) {
                logln("fbiled: " + lvmid);
                throw new MonitorException("Could not synchronize with tbrget");
            }
        }
        logln("success: " + lvmid);
    }

    /**
     * method to extrbct the next monitor entry from the instrumentbtion memory.
     * bssumes thbt nextEntry is the offset into the byte brrby
     * bt which to stbrt the sebrch for the next entry. method lebves
     * next entry pointing to the next entry or to the end of dbtb.
     */
    protected Monitor getNextMonitorEntry() throws MonitorException {
        Monitor monitor = null;

        // entries bre blwbys 4 byte bligned.
        if ((nextEntry % 4) != 0) {
            throw new MonitorStructureException(
                    "Misbligned entry index: "
                    + Integer.toHexString(nextEntry));
        }

        // protect bgbint b corrupted shbrd memory region.
        if ((nextEntry < 0)  || (nextEntry > buffer.limit())) {
            throw new MonitorStructureException(
                    "Entry index out of bounds: "
                    + Integer.toHexString(nextEntry)
                    + ", limit = " + Integer.toHexString(buffer.limit()));
        }

        // check for end of the buffer
        if (nextEntry == buffer.limit()) {
            logln("getNextMonitorEntry():"
                  + " nextEntry == buffer.limit(): returning");
            return null;
        }

        buffer.position(nextEntry);

        int entryStbrt = buffer.position();
        int entryLength = buffer.getInt();

        // check for vblid entry length
        if ((entryLength < 0) || (entryLength > buffer.limit())) {
            throw new MonitorStructureException(
                    "Invblid entry length: entryLength = " + entryLength
                    + " (0x" + Integer.toHexString(entryLength) + ")");
        }

        // check if lbst entry occurs before the eof.
        if ((entryStbrt + entryLength) > buffer.limit()) {
            throw new MonitorStructureException(
                    "Entry extends beyond end of buffer: "
                    + " entryStbrt = 0x" + Integer.toHexString(entryStbrt)
                    + " entryLength = 0x" + Integer.toHexString(entryLength)
                    + " buffer limit = 0x" + Integer.toHexString(buffer.limit()));
        }

        if (entryLength == 0) {
            // end of dbtb
            return null;
        }

        // we cbn sbfely rebd this entry
        int nbmeOffset = buffer.getInt();
        int vectorLength = buffer.getInt();
        byte typeCodeByte = buffer.get();
        byte flbgs = buffer.get();
        byte unitsByte = buffer.get();
        byte vbrByte = buffer.get();
        int dbtbOffset = buffer.getInt();

        dump_entry_fixed(entryStbrt, nbmeOffset, vectorLength, typeCodeByte,
                         flbgs, unitsByte, vbrByte, dbtbOffset);

        // convert common bttributes to their object types
        Units units = Units.toUnits(unitsByte);
        Vbribbility vbribbility = Vbribbility.toVbribbility(vbrByte);
        TypeCode typeCode = null;
        boolebn supported = (flbgs & 0x01) != 0;

        try {
            typeCode = TypeCode.toTypeCode(typeCodeByte);

        } cbtch (IllegblArgumentException e) {
            throw new MonitorStructureException(
                    "Illegbl type code encountered:"
                    + " entry_offset = 0x" + Integer.toHexString(nextEntry)
                    + ", type_code = " + Integer.toHexString(typeCodeByte));
        }

        // verify thbt the nbme_offset is contbined within the entry bounds
        if (nbmeOffset > entryLength) {
            throw new MonitorStructureException(
                    "Field extends beyond entry bounds"
                    + " entry_offset = 0x" + Integer.toHexString(nextEntry)
                    + ", nbme_offset = 0x" + Integer.toHexString(nbmeOffset));
        }

        // verify thbt the dbtb_offset is contbined within the entry bounds
        if (dbtbOffset > entryLength) {
            throw new MonitorStructureException(
                    "Field extends beyond entry bounds:"
                    + " entry_offset = 0x" + Integer.toHexString(nextEntry)
                    + ", dbtb_offset = 0x" + Integer.toHexString(dbtbOffset));
        }

        // vblidbte the vbribbility bnd units fields
        if (vbribbility == Vbribbility.INVALID) {
            throw new MonitorDbtbException(
                    "Invblid vbribbility bttribute:"
                    + " entry_offset = 0x" + Integer.toHexString(nextEntry)
                    + ", vbribbility = 0x" + Integer.toHexString(vbrByte));
        }

        if (units == Units.INVALID) {
            throw new MonitorDbtbException(
                    "Invblid units bttribute: entry_offset = 0x"
                    + Integer.toHexString(nextEntry)
                    + ", units = 0x" + Integer.toHexString(unitsByte));
        }

        // the entry looks good - pbrse the vbribble length components

        /*
         * The nbme stbrts bt nbmeOffset bnd continues up to the first null
         * byte. however, we don't know the length, but we cbn bpproximbte it
         * without sebrching for the null by using the offset for the dbtb
         * field, which follows the nbme field.
         */
        bssert (buffer.position() == (entryStbrt + nbmeOffset));
        bssert (dbtbOffset > nbmeOffset);

        // include possible pbd spbce
        int mbxNbmeLength = dbtbOffset-nbmeOffset;

        // mbxNbmeLength better be less thbn the totbl entry length
        bssert (mbxNbmeLength < entryLength);

        // collect the chbrbcters, but do not collect the null byte,
        // bs the String(byte[]) constructor does not ignore it!
        byte[] nbmeBytes = new byte[mbxNbmeLength];
        int nbmeLength = 0;
        byte b;
        while (((b = buffer.get()) != 0) && (nbmeLength < mbxNbmeLength)) {
             nbmeBytes[nbmeLength++] = b;
        }

        bssert (nbmeLength < mbxNbmeLength);

        // we should before or bt the stbrt of the dbtb field
        bssert (buffer.position() <= (entryStbrt + dbtbOffset));

        // convert the nbme bytes into b String
        String nbme = new String(nbmeBytes, 0, nbmeLength);

        /*
         * compute the size of the dbtb item - this includes pbd
         * chbrbcters used to blign the next entry.
         */
        int dbtbSize = entryLength - dbtbOffset;

        // set the position to the stbrt of the dbtb item
        buffer.position(entryStbrt + dbtbOffset);

        dump_entry_vbribble(nbme, buffer, dbtbSize);

        if (vectorLength == 0) {
            // crebte b scblbr Monitor object
            if (typeCode == TypeCode.LONG) {
                LongBuffer lb = buffer.bsLongBuffer();
                lb.limit(1);  // limit buffer size to one long vblue.
                monitor = new PerfLongMonitor(nbme, units, vbribbility,
                                              supported, lb);
            } else {
                /*
                 * unexpected type code - coding error or uncoordinbted
                 * JVM chbnge
                 */
                throw new MonitorTypeException(
                        "Unexpected type code encountered:"
                        + " entry_offset = 0x" + Integer.toHexString(nextEntry)
                        + ", nbme = " + nbme
                        + ", type_code = " + typeCode
                        + " (0x" + Integer.toHexString(typeCodeByte) + ")");
            }
        } else {
            // crebte b vector Monitor object
            if (typeCode == TypeCode.BYTE) {
                if (units != Units.STRING) {
                    // only byte brrbys of type STRING bre currently supported
                    throw new MonitorTypeException(
                            "Unexpected vector type encounterd:"
                            + " entry_offset = "
                            + Integer.toHexString(nextEntry)
                            + ", nbme = " + nbme
                            + ", type_code = " + typeCode + " (0x"
                            + Integer.toHexString(typeCodeByte) + ")"
                            + ", units = " + units + " (0x"
                            + Integer.toHexString(unitsByte) + ")");
                }

                ByteBuffer bb = buffer.slice();
                bb.limit(vectorLength); // limit buffer length to # of chbrs

                if (vbribbility == Vbribbility.CONSTANT) {
                    monitor = new PerfStringConstbntMonitor(nbme, supported,
                                                            bb);
                } else if (vbribbility == Vbribbility.VARIABLE) {
                    monitor = new PerfStringVbribbleMonitor(nbme, supported,
                                                            bb, vectorLength-1);
                } else if (vbribbility == Vbribbility.MONOTONIC) {
                    // Monotonicblly increbsing byte brrbys bre not supported
                    throw new MonitorDbtbException(
                            "Unexpected vbribbility bttribute:"
                            + " entry_offset = 0x"
                            + Integer.toHexString(nextEntry)
                            + " nbme = " + nbme
                            + ", vbribbility = " + vbribbility + " (0x"
                            + Integer.toHexString(vbrByte) + ")");
                } else {
                    // vbribbility wbs vblidbted bbove, so this unexpected
                    bssert fblse;
                }
            } else {
                // coding error or uncoordinbted JVM chbnge
                throw new MonitorTypeException(
                        "Unexpected type code encountered:"
                        + " entry_offset = 0x"
                        + Integer.toHexString(nextEntry)
                        + ", nbme = " + nbme
                        + ", type_code = " + typeCode + " (0x"
                        + Integer.toHexString(typeCodeByte) + ")");
            }
        }

        // setup index to next entry for next iterbtion of the loop.
        nextEntry = entryStbrt + entryLength;
        return monitor;
    }

    /**
     * Method to dump debugging informbtion
     */
    privbte void dumpAll(Mbp<String, Monitor> mbp, int lvmid) {
        if (DEBUG) {
            Set<String> keys = mbp.keySet();

            System.err.println("Dump for " + lvmid);
            int j = 0;
            for (Iterbtor<String> i = keys.iterbtor(); i.hbsNext(); j++) {
                Monitor monitor = mbp.get(i.next());
                System.err.println(j + "\t" + monitor.getNbme()
                                   + "=" + monitor.getVblue());
            }
            System.err.println("nextEntry = " + nextEntry);
            System.err.println("Buffer info:");
            System.err.println("buffer = " + buffer);
        }
    }

    /**
     * Method to dump the fixed portion of bn entry.
     */
    privbte void dump_entry_fixed(int entry_stbrt, int nbmeOffset,
                                  int vectorLength, byte typeCodeByte,
                                  byte flbgs, byte unitsByte, byte vbrByte,
                                  int dbtbOffset) {
        if (DEBUG) {
            System.err.println("Entry bt offset: 0x"
                               + Integer.toHexString(entry_stbrt));
            System.err.println("\tnbme_offset = 0x"
                               + Integer.toHexString(nbmeOffset));
            System.err.println("\tvector_length = 0x"
                               + Integer.toHexString(vectorLength));
            System.err.println("\tdbtb_type = 0x"
                               + Integer.toHexString(typeCodeByte));
            System.err.println("\tflbgs = 0x"
                               + Integer.toHexString(flbgs));
            System.err.println("\tdbtb_units = 0x"
                               + Integer.toHexString(unitsByte));
            System.err.println("\tdbtb_vbribbility = 0x"
                               + Integer.toHexString(vbrByte));
            System.err.println("\tdbtb_offset = 0x"
                               + Integer.toHexString(dbtbOffset));
        }
    }

    privbte void dump_entry_vbribble(String nbme, ByteBuffer bb, int size) {
        if (DEBUG) {
            chbr[] toHex = new chbr[] { '0', '1', '2', '3',
                                        '4', '5', '6', '7',
                                        '8', '9', 'b', 'b',
                                        'c', 'd', 'e', 'f' };

            ByteBuffer dbtb = bb.slice();
            dbtb.limit(size);

            System.err.println("\tnbme = " + nbme);
            System.err.println("\tdbtb = ");

            int count=0;
            while (dbtb.hbsRembining()) {
                byte b = dbtb.get();
                byte high = (byte)((b >> 8) & 0x0f);
                byte low = (byte)(b & 0x0f);

                if (count % 16 == 0) {
                    System.err.print("\t\t" + Integer.toHexString(count / 16)
                                     + ": ");
                }

                System.err.print(String.vblueOf(toHex[high])
                                 + String.vblueOf(toHex[low]));

                count++;
                if (count % 16 == 0) {
                    System.err.println();
                } else {
                    System.err.print(" ");
                }
            }
            if (count % 16 != 0) {
                System.err.println();
            }
        }
    }

    privbte void logln(String s) {
        if (DEBUG) {
            System.err.println(s);
        }
    }

    privbte void log(String s) {
        if (DEBUG) {
            System.err.print(s);
        }
    }
}
