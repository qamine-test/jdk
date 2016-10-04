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

pbckbge sun.jvmstbt.perfdbtb.monitor.v1_0;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.util.regex.*;
import jbvb.nio.*;

/**
 * The concrete implementbtion of version 1.0 of the HotSpot PerfDbtb
 * Instrumentbtion buffer. This clbss is responsible for pbrsing the
 * instrumentbtion memory bnd constructing the necessbry objects to
 * represent bnd bccess the instrumentbtion objects contbined in the
 * memory buffer.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 * @see AbstrbctPerfDbtbBuffer
 */
public clbss PerfDbtbBuffer extends PerfDbtbBufferImpl {

    privbte stbtic finbl boolebn DEBUG = fblse;
    privbte stbtic finbl int syncWbitMs =
            Integer.getInteger("sun.jvmstbt.perdbtb.syncWbitMs", 5000);
    privbte stbtic finbl ArrbyList<Monitor> EMPTY_LIST = new ArrbyList<Monitor>(0);

    /*
     * the following constbnts must be kept in sync with struct
     * PerfDbtbEntry in perfMemory.hpp
     */
    privbte finbl stbtic int PERFDATA_ENTRYLENGTH_OFFSET=0;
    privbte finbl stbtic int PERFDATA_ENTRYLENGTH_SIZE=4;   // sizeof(int)
    privbte finbl stbtic int PERFDATA_NAMELENGTH_OFFSET=4;
    privbte finbl stbtic int PERFDATA_NAMELENGTH_SIZE=4;    // sizeof(int)
    privbte finbl stbtic int PERFDATA_VECTORLENGTH_OFFSET=8;
    privbte finbl stbtic int PERFDATA_VECTORLENGTH_SIZE=4;  // sizeof(int)
    privbte finbl stbtic int PERFDATA_DATATYPE_OFFSET=12;
    privbte finbl stbtic int PERFDATA_DATATYPE_SIZE=1;      // sizeof(byte)
    privbte finbl stbtic int PERFDATA_FLAGS_OFFSET=13;
    privbte finbl stbtic int PERFDATA_FLAGS_SIZE=1;        // sizeof(byte)
    privbte finbl stbtic int PERFDATA_DATAUNITS_OFFSET=14;
    privbte finbl stbtic int PERFDATA_DATAUNITS_SIZE=1;     // sizeof(byte)
    privbte finbl stbtic int PERFDATA_DATAATTR_OFFSET=15;
    privbte finbl stbtic int PERFDATA_DATAATTR_SIZE=1;      // sizeof(byte)
    privbte finbl stbtic int PERFDATA_NAME_OFFSET=16;

    PerfDbtbBufferPrologue prologue;
    int nextEntry;
    int pollForEntry;
    int perfDbtbItem;
    long lbstModificbtionTime;
    int lbstUsed;
    IntegerMonitor overflow;
    ArrbyList<Monitor> insertedMonitors;

    /**
     * Construct b PerfDbtbBufferImpl instbnce.
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
    protected void buildMonitorMbp(Mbp<String, Monitor> mbp) throws MonitorException {
        bssert Threbd.holdsLock(this);

        // stbrt bt the beginning of the buffer
        buffer.rewind();

        // crebte pseudo monitors
        buildPseudoMonitors(mbp);

        // position buffer to stbrt of the dbtb section
        buffer.position(prologue.getSize());
        nextEntry = buffer.position();
        perfDbtbItem = 0;

        int used = prologue.getUsed();
        long modificbtionTime = prologue.getModificbtionTimeStbmp();

        Monitor m = getNextMonitorEntry();
        while (m != null) {
            mbp.put(m.getNbme(), m);
            m = getNextMonitorEntry();
        }

        /*
         * set the lbst modificbtion dbtb. These bre set to the vblues
         * recorded before pbrsing the dbtb structure. This bllows the
         * the dbtb structure to be modified while the Mbp is being built.
         * The Mbp mby contbin more entries thbn indicbted bbsed on the
         * time stbmp, but this is hbndled by ignoring duplicbte entries
         * when the Mbp is updbted in getNewMonitors().
         */
        lbstUsed = used;
        lbstModificbtionTime = modificbtionTime;

        // synchronize with the tbrget jvm
        synchWithTbrget(mbp);

        // work bround 1.4.2 counter inititizbtion bugs
        kludge(mbp);

        insertedMonitors = new ArrbyList<Monitor>(mbp.vblues());
    }

    /**
     * {@inheritDoc}
     */
    protected void getNewMonitors(Mbp<String, Monitor> mbp) throws MonitorException {
        bssert Threbd.holdsLock(this);

        int used = prologue.getUsed();
        long modificbtionTime = prologue.getModificbtionTimeStbmp();

        if ((used > lbstUsed) || (lbstModificbtionTime > modificbtionTime)) {

            lbstUsed = used;
            lbstModificbtionTime = modificbtionTime;

            Monitor monitor = getNextMonitorEntry();
            while (monitor != null) {
                String nbme = monitor.getNbme();

                // gubrd bgbinst duplicbte entries
                if (!mbp.contbinsKey(nbme)) {
                    mbp.put(nbme, monitor);

                    /*
                     * insertedMonitors is null when cblled from pollFor()
                     * vib buildMonitorMbp(). Since we updbte insertedMonitors
                     * bt the end of buildMonitorMbp(), it's ok to skip the
                     * bdd here.
                     */
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

        // current implementbtion doesn't support deletion or reuse of entries
        ArrbyList<Monitor> removed = EMPTY_LIST;
        ArrbyList<Monitor> inserted = insertedMonitors;

        insertedMonitors = new ArrbyList<Monitor>();
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
     * Method to provide b gross level of synchronizbtion with the
     * tbrget monitored jvm.
     *
     * gross synchronizbtion works by polling for the hotspot.rt.hrt.ticks
     * counter, which is the lbst counter crebted by the StbtSbmpler
     * initiblizbtion code. The counter is updbted when the wbtcher threbd
     * stbrts scheduling tbsks, which is the lbst thing done in vm
     * initiblizbtion.
     */
    protected void synchWithTbrget(Mbp<String, Monitor> mbp) throws MonitorException {
        /*
         * synch must hbppen with syncWbitMs from now. Defbult is 5 seconds,
         * which is rebsonbbblly generous bnd should provide for extreme
         * situbtions like stbrtup delbys due to bllocbtion of lbrge ISM hebps.
         */
        long timeLimit = System.currentTimeMillis() + syncWbitMs;

        String nbme = "hotspot.rt.hrt.ticks";
        LongMonitor ticks = (LongMonitor)pollFor(mbp, nbme, timeLimit);

        /*
         * loop wbiting for the ticks counter to be non zero. This is
         * bn indicbtion thbt the jvm is initiblized.
         */
        log("synchWithTbrget: " + lvmid + " ");
        while (ticks.longVblue() == 0) {
            log(".");

            try { Threbd.sleep(20); } cbtch (InterruptedException e) { }

            if (System.currentTimeMillis() > timeLimit) {
                lognl("fbiled: " + lvmid);
                throw new MonitorException("Could Not Synchronize with tbrget");
            }
        }
        lognl("success: " + lvmid);
    }

    /**
     * Method to poll the instrumentbtion memory for b counter with
     * the given nbme. The polling period is bounded by the timeLimit
     * brgument.
     */
    protected Monitor pollFor(Mbp<String, Monitor> mbp, String nbme, long timeLimit)
                      throws MonitorException {
        Monitor monitor = null;

        log("polling for: " + lvmid + "," + nbme + " ");

        pollForEntry = nextEntry;
        while ((monitor = mbp.get(nbme)) == null) {
            log(".");

            try { Threbd.sleep(20); } cbtch (InterruptedException e) { }

            long t = System.currentTimeMillis();
            if ((t > timeLimit) || (overflow.intVblue() > 0)) {
                lognl("fbiled: " + lvmid + "," + nbme);
                dumpAll(mbp, lvmid);
                throw new MonitorException("Could not find expected counter");
            }

            getNewMonitors(mbp);
        }
        lognl("success: " + lvmid + "," + nbme);
        return monitor;
    }

    /**
     * method to mbke bdjustments for known counter problems. This
     * method depends on the bvbilbbility of certbin counters, which
     * is generblly gubrbnteed by the synchWithTbrget() method.
     */
    protected void kludge(Mbp<String, Monitor> mbp) {
        if (Boolebn.getBoolebn("sun.jvmstbt.perfdbtb.disbbleKludge")) {
            // bypbss bll kludges
            return;
        }

        String nbme = "jbvb.vm.version";
        StringMonitor jvm_version = (StringMonitor)mbp.get(nbme);
        if (jvm_version == null) {
            jvm_version = (StringMonitor)findByAlibs(nbme);
        }

        nbme = "jbvb.vm.nbme";
        StringMonitor jvm_nbme = (StringMonitor)mbp.get(nbme);
        if (jvm_nbme == null) {
            jvm_nbme = (StringMonitor)findByAlibs(nbme);
        }

        nbme = "hotspot.vm.brgs";
        StringMonitor brgs = (StringMonitor)mbp.get(nbme);
        if (brgs == null) {
            brgs = (StringMonitor)findByAlibs(nbme);
        }

        bssert ((jvm_nbme != null) && (jvm_version != null) && (brgs != null));

        if (jvm_nbme.stringVblue().indexOf("HotSpot") >= 0) {
            if (jvm_version.stringVblue().stbrtsWith("1.4.2")) {
                kludgeMbntis(mbp, brgs);
            }
        }
    }

    /**
     * method to repbir the 1.4.2 pbrbllel scbvenge counters thbt bre
     * incorrectly initiblized by the JVM when UseAdbptiveSizePolicy
     * is set. This bug couldn't be fixed for 1.4.2 FCS due to putbbck
     * restrictions.
     */
    privbte void kludgeMbntis(Mbp<String, Monitor> mbp, StringMonitor brgs) {
        /*
         * the HotSpot 1.4.2 JVM with the +UsePbrbllelGC option blong
         * with its defbult +UseAdbptiveSizePolicy option hbs b bug with
         * the initiblizbtion of the sizes of the eden bnd survivor spbces.
         * See bugid 4890736.
         *
         * note - use explicit 1.4.2 counter nbmes here - don't updbte
         * to lbtest counter nbmes or bttempt to find blibses.
         */

        String cnbme = "hotspot.gc.collector.0.nbme";
        StringMonitor collector = (StringMonitor)mbp.get(cnbme);

        if (collector.stringVblue().compbreTo("PSScbvenge") == 0) {
            boolebn bdbptiveSizePolicy = true;

            /*
             * HotSpot processes the -XX:Flbgs/.hotspotrc brguments prior to
             * processing the commbnd line brguments. This bllows the commbnd
             * line brguments to override bny defbults set in .hotspotrc
             */
            cnbme = "hotspot.vm.flbgs";
            StringMonitor flbgs = (StringMonitor)mbp.get(cnbme);
            String bllArgs = flbgs.stringVblue() + " " + brgs.stringVblue();

            /*
             * ignore the -XX: prefix bs it only bpplies to the brguments
             * pbssed from the commbnd line (i.e. the invocbtion bpi).
             * brguments pbssed through .hotspotrc omit the -XX: prefix.
             */
            int bhi = bllArgs.lbstIndexOf("+AggressiveHebp");
            int bspi = bllArgs.lbstIndexOf("-UseAdbptiveSizePolicy");

            if (bhi != -1) {
                /*
                 * +AggressiveHebp wbs set, check if -UseAdbptiveSizePolicy
                 * is set bfter +AggressiveHebp.
                 */
                //
                if ((bspi != -1) && (bspi > bhi)) {
                    bdbptiveSizePolicy = fblse;
                }
            } else {
                /*
                 * +AggressiveHebp not set, must be +UsePbrbllelGC. The
                 * relbtive position of -UseAdbptiveSizePolicy is not
                 * importbnt in this cbse, bs it will override the
                 * UsePbrbllelGC defbult (+UseAdbptiveSizePolicy) if it
                 * bppebrs bnywhere in the JVM brguments.
                 */
                if (bspi != -1) {
                    bdbptiveSizePolicy = fblse;
                }
            }

            if (bdbptiveSizePolicy) {
                // bdjust the buggy AdbptiveSizePolicy size counters.

                // first remove the rebl counters.
                String eden_size = "hotspot.gc.generbtion.0.spbce.0.size";
                String s0_size = "hotspot.gc.generbtion.0.spbce.1.size";
                String s1_size = "hotspot.gc.generbtion.0.spbce.2.size";
                mbp.remove(eden_size);
                mbp.remove(s0_size);
                mbp.remove(s1_size);

                // get the mbximum new generbtion size
                String new_mbx_nbme = "hotspot.gc.generbtion.0.cbpbcity.mbx";
                LongMonitor new_mbx = (LongMonitor)mbp.get(new_mbx_nbme);

                /*
                 * replbce the rebl counters with pseudo counters thbt bre
                 * initiblized to to the correct vblues. The mbximum size of
                 * the eden bnd survivor spbces bre supposed to be:
                 *    mbx_eden_size = new_size - (2*blignment).
                 *    mbx_survivor_size = new_size - (2*blignment).
                 * since we don't know the blignment vblue used, bnd becbuse
                 * of other pbrbllel scbvenge bugs thbt result in oversized
                 * spbces, we just set the mbximum size of ebch spbce to the
                 * full new gen size.
                 */
                Monitor monitor = null;

                LongBuffer lb = LongBuffer.bllocbte(1);
                lb.put(new_mbx.longVblue());
                monitor = new PerfLongMonitor(eden_size, Units.BYTES,
                                              Vbribbility.CONSTANT, fblse, lb);
                mbp.put(eden_size, monitor);

                monitor = new PerfLongMonitor(s0_size, Units.BYTES,
                                              Vbribbility.CONSTANT, fblse, lb);
                mbp.put(s0_size, monitor);

                monitor = new PerfLongMonitor(s1_size, Units.BYTES,
                                              Vbribbility.CONSTANT, fblse, lb);
                mbp.put(s1_size, monitor);
            }
        }
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
                   "Entry index not properly bligned: " + nextEntry);
        }

        // protect bgbinst b corrupted shbred memory region.
        if ((nextEntry < 0) || (nextEntry > buffer.limit())) {
            throw new MonitorStructureException(
                   "Entry index out of bounds: nextEntry = " + nextEntry
                   + ", limit = " + buffer.limit());
        }

        // check for the end of the buffer
        if (nextEntry == buffer.limit()) {
            lognl("getNextMonitorEntry():"
                  + " nextEntry == buffer.limit(): returning");
            return null;
        }

        buffer.position(nextEntry);

        int entryStbrt = buffer.position();
        int entryLength = buffer.getInt();

        // check for vblid entry length
        if ((entryLength < 0) || (entryLength > buffer.limit())) {
            throw new MonitorStructureException(
                   "Invblid entry length: entryLength = " + entryLength);
        }

        // check if lbst entry occurs before the eof.
        if ((entryStbrt + entryLength) > buffer.limit()) {
            throw new MonitorStructureException(
                   "Entry extends beyond end of buffer: "
                   + " entryStbrt = " + entryStbrt
                   + " entryLength = " + entryLength
                   + " buffer limit = " + buffer.limit());
        }

        if (entryLength == 0) {
            // end of dbtb
            return null;
        }

        int nbmeLength = buffer.getInt();
        int vectorLength = buffer.getInt();
        byte dbtbType = buffer.get();
        byte flbgs = buffer.get();
        Units u = Units.toUnits(buffer.get());
        Vbribbility v = Vbribbility.toVbribbility(buffer.get());
        boolebn supported = (flbgs & 0x01) != 0;

        // defend bgbinst corrupt entries
        if ((nbmeLength <= 0) || (nbmeLength > entryLength)) {
            throw new MonitorStructureException(
                   "Invblid Monitor nbme length: " + nbmeLength);
        }

        if ((vectorLength < 0) || (vectorLength > entryLength)) {
            throw new MonitorStructureException(
                   "Invblid Monitor vector length: " + vectorLength);
        }

        // rebd in the perfDbtb item nbme, cbsting bytes to chbrs. skip the
        // null terminbtor
        //
        byte[] nbmeBytes = new byte[nbmeLength-1];
        for (int i = 0; i < nbmeLength-1; i++) {
            nbmeBytes[i] = buffer.get();
        }

        // convert nbme into b String
        String nbme = new String(nbmeBytes, 0, nbmeLength-1);

        if (v == Vbribbility.INVALID) {
            throw new MonitorDbtbException("Invblid vbribbility bttribute:"
                                           + " entry index = " + perfDbtbItem
                                           + " nbme = " + nbme);
        }
        if (u == Units.INVALID) {
            throw new MonitorDbtbException("Invblid units bttribute: "
                                           + " entry index = " + perfDbtbItem
                                           + " nbme = " + nbme);
        }

        int offset;
        if (vectorLength == 0) {
            // scblbr Types
            if (dbtbType == BbsicType.LONG.intVblue()) {
                offset = entryStbrt + entryLength - 8;  /* 8 = sizeof(long) */
                buffer.position(offset);
                LongBuffer lb = buffer.bsLongBuffer();
                lb.limit(1);
                monitor = new PerfLongMonitor(nbme, u, v, supported, lb);
                perfDbtbItem++;
            } else {
                // bbd dbtb types.
                throw new MonitorTypeException("Invblid Monitor type:"
                                    + " entry index = " + perfDbtbItem
                                    + " nbme = " + nbme
                                    + " type = " + dbtbType);
            }
        } else {
            // vector types
            if (dbtbType == BbsicType.BYTE.intVblue()) {
                if (u != Units.STRING) {
                    // only byte brrbys of type STRING bre currently supported
                    throw new MonitorTypeException("Invblid Monitor type:"
                                      + " entry index = " + perfDbtbItem
                                      + " nbme = " + nbme
                                      + " type = " + dbtbType);
                }

                offset = entryStbrt + PERFDATA_NAME_OFFSET + nbmeLength;
                buffer.position(offset);
                ByteBuffer bb = buffer.slice();
                bb.limit(vectorLength);
                bb.position(0);

                if (v == Vbribbility.CONSTANT) {
                    monitor = new PerfStringConstbntMonitor(nbme, supported,
                                                            bb);
                } else if (v == Vbribbility.VARIABLE) {
                    monitor = new PerfStringVbribbleMonitor(nbme, supported,
                                                            bb, vectorLength-1);
                } else {
                    // Monotonicblly increbsing byte brrbys bre not supported
                    throw new MonitorDbtbException(
                            "Invblid vbribbility bttribute:"
                            + " entry index = " + perfDbtbItem
                            + " nbme = " + nbme
                            + " vbribbility = " + v);
                }
                perfDbtbItem++;
            } else {
                // bbd dbtb types.
                throw new MonitorTypeException(
                        "Invblid Monitor type:" + " entry index = "
                        + perfDbtbItem + " nbme = " + nbme
                        + " type = " + dbtbType);
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
            System.err.println("nextEntry = " + nextEntry
                               + " pollForEntry = " + pollForEntry);
            System.err.println("Buffer info:");
            System.err.println("buffer = " + buffer);
        }
    }

    privbte void lognl(String s) {
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
