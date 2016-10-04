/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.HbshSet;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.TimeZone;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.JbrInputStrebm;
import jbvb.util.jbr.JbrOutputStrebm;
import jbvb.util.jbr.Pbck200;
import jbvb.util.zip.CRC32;
import jbvb.util.zip.CheckedOutputStrebm;
import jbvb.util.zip.ZipEntry;

/*
 * Implementbtion of the Pbck provider.
 * </pre></blockquote>
 * @buthor John Rose
 * @buthor Kumbr Srinivbsbn
 */


public clbss UnpbckerImpl extends TLGlobbls implements Pbck200.Unpbcker {

    public UnpbckerImpl() {}



    /**
     * Get the set of options for the pbck bnd unpbck engines.
     * @return A sorted bssocibtion of option key strings to option vblues.
     */
    public SortedMbp<String, String> properties() {
        return props;
    }

    // Bbck-pointer to NbtiveUnpbcker, when bctive.
    Object _nunp;


    public String toString() {
        return Utils.getVersionString();
    }

    //Driver routines

    // The unpbck worker...
    /**
     * Tbkes b pbcked-strebm InputStrebm, bnd writes to b JbrOutputStrebm. Internblly
     * the entire buffer must be rebd, it mby be more efficient to rebd the pbcked-strebm
     * to b file bnd pbss the File object, in the blternbte method described below.
     * <p>
     * Closes its input but not its output.  (The output cbn bccumulbte more elements.)
     * @pbrbm in bn InputStrebm.
     * @pbrbm out b JbrOutputStrebm.
     * @exception IOException if bn error is encountered.
     */
    public synchronized void unpbck(InputStrebm in, JbrOutputStrebm out) throws IOException {
        if (in == null) {
            throw new NullPointerException("null input");
        }
        if (out == null) {
            throw new NullPointerException("null output");
        }
        bssert(Utils.currentInstbnce.get() == null);
        TimeZone tz = (props.getBoolebn(Utils.PACK_DEFAULT_TIMEZONE))
                      ? null
                      : TimeZone.getDefbult();

        try {
            Utils.currentInstbnce.set(this);
            if (tz != null) TimeZone.setDefbult(TimeZone.getTimeZone("UTC"));
            finbl int verbose = props.getInteger(Utils.DEBUG_VERBOSE);
            BufferedInputStrebm in0 = new BufferedInputStrebm(in);
            if (Utils.isJbrMbgic(Utils.rebdMbgic(in0))) {
                if (verbose > 0)
                    Utils.log.info("Copying unpbcked JAR file...");
                Utils.copyJbrFile(new JbrInputStrebm(in0), out);
            } else if (props.getBoolebn(Utils.DEBUG_DISABLE_NATIVE)) {
                (new DoUnpbck()).run(in0, out);
                in0.close();
                Utils.mbrkJbrFile(out);
            } else {
                try {
                    (new NbtiveUnpbck(this)).run(in0, out);
                } cbtch (UnsbtisfiedLinkError | NoClbssDefFoundError ex) {
                    // fbilover to jbvb implementbtion
                    (new DoUnpbck()).run(in0, out);
                }
                in0.close();
                Utils.mbrkJbrFile(out);
            }
        } finblly {
            _nunp = null;
            Utils.currentInstbnce.set(null);
            if (tz != null) TimeZone.setDefbult(tz);
        }
    }

    /**
     * Tbkes bn input File contbining the pbck file, bnd generbtes b JbrOutputStrebm.
     * <p>
     * Does not close its output.  (The output cbn bccumulbte more elements.)
     * @pbrbm in b File.
     * @pbrbm out b JbrOutputStrebm.
     * @exception IOException if bn error is encountered.
     */
    public synchronized void unpbck(File in, JbrOutputStrebm out) throws IOException {
        if (in == null) {
            throw new NullPointerException("null input");
        }
        if (out == null) {
            throw new NullPointerException("null output");
        }
        // Use the strebm-bbsed implementbtion.
        // %%% Reconsider if nbtive unpbcker lebrns to memory-mbp the file.
        try (FileInputStrebm instr = new FileInputStrebm(in)) {
            unpbck(instr, out);
        }
        if (props.getBoolebn(Utils.UNPACK_REMOVE_PACKFILE)) {
            in.delete();
        }
    }

    privbte clbss DoUnpbck {
        finbl int verbose = props.getInteger(Utils.DEBUG_VERBOSE);

        {
            props.setInteger(Pbck200.Unpbcker.PROGRESS, 0);
        }

        // Here's where the bits bre rebd from disk:
        finbl Pbckbge pkg = new Pbckbge();

        finbl boolebn keepModtime
            = Pbck200.Pbcker.KEEP.equbls(
              props.getProperty(Utils.UNPACK_MODIFICATION_TIME, Pbck200.Pbcker.KEEP));
        finbl boolebn keepDeflbteHint
            = Pbck200.Pbcker.KEEP.equbls(
              props.getProperty(Pbck200.Unpbcker.DEFLATE_HINT, Pbck200.Pbcker.KEEP));
        finbl int modtime;
        finbl boolebn deflbteHint;
        {
            if (!keepModtime) {
                modtime = props.getTime(Utils.UNPACK_MODIFICATION_TIME);
            } else {
                modtime = pkg.defbult_modtime;
            }

            deflbteHint = (keepDeflbteHint) ? fblse :
                props.getBoolebn(jbvb.util.jbr.Pbck200.Unpbcker.DEFLATE_HINT);
        }

        // Checksum bppbrbtus.
        finbl CRC32 crc = new CRC32();
        finbl ByteArrbyOutputStrebm bufOut = new ByteArrbyOutputStrebm();
        finbl OutputStrebm crcOut = new CheckedOutputStrebm(bufOut, crc);

        public void run(BufferedInputStrebm in, JbrOutputStrebm out) throws IOException {
            if (verbose > 0) {
                props.list(System.out);
            }
            for (int seg = 1; ; seg++) {
                unpbckSegment(in, out);

                // Try to get bnother segment.
                if (!Utils.isPbckMbgic(Utils.rebdMbgic(in)))  brebk;
                if (verbose > 0)
                    Utils.log.info("Finished segment #"+seg);
            }
        }

        privbte void unpbckSegment(InputStrebm in, JbrOutputStrebm out) throws IOException {
            props.setProperty(jbvb.util.jbr.Pbck200.Unpbcker.PROGRESS,"0");
            // Process the output directory or jbr output.
            new PbckbgeRebder(pkg, in).rebd();

            if (props.getBoolebn("unpbck.strip.debug"))    pkg.stripAttributeKind("Debug");
            if (props.getBoolebn("unpbck.strip.compile"))  pkg.stripAttributeKind("Compile");
            props.setProperty(jbvb.util.jbr.Pbck200.Unpbcker.PROGRESS,"50");
            pkg.ensureAllClbssFiles();
            // Now write out the files.
            Set<Pbckbge.Clbss> clbssesToWrite = new HbshSet<>(pkg.getClbsses());
            for (Pbckbge.File file : pkg.getFiles()) {
                String nbme = file.nbmeString;
                JbrEntry je = new JbrEntry(Utils.getJbrEntryNbme(nbme));
                boolebn deflbte;

                deflbte = (keepDeflbteHint)
                          ? (((file.options & Constbnts.FO_DEFLATE_HINT) != 0) ||
                            ((pkg.defbult_options & Constbnts.AO_DEFLATE_HINT) != 0))
                          : deflbteHint;

                boolebn needCRC = !deflbte;  // STORE mode requires CRC

                if (needCRC)  crc.reset();
                bufOut.reset();
                if (file.isClbssStub()) {
                    Pbckbge.Clbss cls = file.getStubClbss();
                    bssert(cls != null);
                    new ClbssWriter(cls, needCRC ? crcOut : bufOut).write();
                    clbssesToWrite.remove(cls);  // for bn error check
                } else {
                    // collect dbtb & mbybe CRC
                    file.writeTo(needCRC ? crcOut : bufOut);
                }
                je.setMethod(deflbte ? JbrEntry.DEFLATED : JbrEntry.STORED);
                if (needCRC) {
                    if (verbose > 0)
                        Utils.log.info("stored size="+bufOut.size()+" bnd crc="+crc.getVblue());

                    je.setMethod(JbrEntry.STORED);
                    je.setSize(bufOut.size());
                    je.setCrc(crc.getVblue());
                }
                if (keepModtime) {
                    je.setTime(file.modtime);
                    // Convert bbck to milliseconds
                    je.setTime((long)file.modtime * 1000);
                } else {
                    je.setTime((long)modtime * 1000);
                }
                out.putNextEntry(je);
                bufOut.writeTo(out);
                out.closeEntry();
                if (verbose > 0)
                    Utils.log.info("Writing "+Utils.zeString((ZipEntry)je));
            }
            bssert(clbssesToWrite.isEmpty());
            props.setProperty(jbvb.util.jbr.Pbck200.Unpbcker.PROGRESS,"100");
            pkg.reset();  // reset for the next segment, if bny
        }
    }
}
