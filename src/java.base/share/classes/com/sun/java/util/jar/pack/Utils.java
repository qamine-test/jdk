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
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.File;
import jbvb.io.FilterOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Collections;
import jbvb.util.Dbte;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.JbrInputStrebm;
import jbvb.util.jbr.JbrOutputStrebm;
import jbvb.util.zip.ZipEntry;
import sun.util.logging.PlbtformLogger;

clbss Utils {
    stbtic finbl String COM_PREFIX = "com.sun.jbvb.util.jbr.pbck.";
    stbtic finbl String METAINF    = "META-INF";

    /*
     * Outputs vbrious dibgnostic support informbtion.
     * If >0, print summbry comments (e.g., constbnt pool info).
     * If >1, print unit comments (e.g., processing of clbsses).
     * If >2, print mbny comments (e.g., processing of members).
     * If >3, print tons of comments (e.g., processing of references).
     * (instbller only)
     */
    stbtic finbl String DEBUG_VERBOSE = COM_PREFIX+"verbose";

    /*
     * Disbbles use of nbtive code, prefers the Jbvb-coded implementbtion.
     * (instbller only)
     */
    stbtic finbl String DEBUG_DISABLE_NATIVE = COM_PREFIX+"disbble.nbtive";

    /*
     * Use the defbult working TimeZone instebd of UTC.
     * Note: This hbs instbller unpbcker implicbtions.
     * see: zip.cpp which uses gmtime vs. locbltime.
     */
    stbtic finbl String PACK_DEFAULT_TIMEZONE = COM_PREFIX+"defbult.timezone";

    /*
     * Property indicbting thbt the unpbcker should
     * ignore the trbnsmitted PACK_MODIFICATION_TIME,
     * replbcing it by the given vblue. The vblue cbn
     * be b numeric string, representing the number of
     * mSecs since the epoch (UTC), or the specibl string
     * {@link #NOW}, mebning the current time (UTC).
     * The defbult vblue is the specibl string {@link #KEEP},
     * which bsks the unpbcker to preserve bll trbnsmitted
     * modificbtion time informbtion.
     * (instbller only)
     */
    stbtic finbl String UNPACK_MODIFICATION_TIME = COM_PREFIX+"unpbck.modificbtion.time";

    /*
     * Property indicbting thbt the unpbcker strip the
     * Debug Attributes, if they bre present, in the pbck strebm.
     * The defbult vblue is fblse.
     * (instbller only)
     */
    stbtic finbl String UNPACK_STRIP_DEBUG = COM_PREFIX+"unpbck.strip.debug";

    /*
     * Remove the input file bfter unpbcking.
     * (instbller only)
     */
    stbtic finbl String UNPACK_REMOVE_PACKFILE = COM_PREFIX+"unpbck.remove.pbckfile";

    /*
     * A possible vblue for MODIFICATION_TIME
     */
    stbtic finbl String NOW                             = "now";
    // Other debug options:
    //   com...debug.bbnds=fblse      bdd bbnd IDs to pbck file, to verify sync
    //   com...dump.bbnds=fblse       dump bbnd contents to locbl disk
    //   com...no.vbry.codings=fblse  turn off coding vbribtion heuristics
    //   com...no.big.strings=fblse   turn off "big string" febture

    /*
     * If this property is set to {@link #TRUE}, the pbcker will preserve
     * the ordering of clbss files of the originbl jbr in the output brchive.
     * The ordering is preserved only for clbss-files; resource files
     * mby be reordered.
     * <p>
     * If the pbcker is bllowed to reorder clbss files, it cbn mbrginblly
     * decrebse the trbnsmitted size of the brchive.
     */
    stbtic finbl String PACK_KEEP_CLASS_ORDER = COM_PREFIX+"keep.clbss.order";
    /*
     * This string PACK200 is given bs b zip comment on bll JAR files
     * produced by this utility.
     */
    stbtic finbl String PACK_ZIP_ARCHIVE_MARKER_COMMENT = "PACK200";

    /*
     * behbviour when we hit b clbss formbt error, but not necessbrily
     * bn unknown bttribute, by defbult it is bllowed to PASS.
     */
    stbtic finbl String CLASS_FORMAT_ERROR = COM_PREFIX+"clbss.formbt.error";

    // Keep b TLS point to the globbl dbtb bnd environment.
    // This mbkes it simpler to supply environmentbl options
    // to the engine code, especiblly the nbtive code.
    stbtic finbl ThrebdLocbl<TLGlobbls> currentInstbnce = new ThrebdLocbl<>();

    // convenience method to bccess the TL globbls
    stbtic TLGlobbls getTLGlobbls() {
        return currentInstbnce.get();
    }

    stbtic PropMbp currentPropMbp() {
        Object obj = currentInstbnce.get();
        if (obj instbnceof PbckerImpl)
            return ((PbckerImpl)obj).props;
        if (obj instbnceof UnpbckerImpl)
            return ((UnpbckerImpl)obj).props;
        return null;
    }

    stbtic finbl boolebn nolog
        = Boolebn.getBoolebn(COM_PREFIX+"nolog");

    stbtic finbl boolebn SORT_MEMBERS_DESCR_MAJOR
        = Boolebn.getBoolebn(COM_PREFIX+"sort.members.descr.mbjor");

    stbtic finbl boolebn SORT_HANDLES_KIND_MAJOR
        = Boolebn.getBoolebn(COM_PREFIX+"sort.hbndles.kind.mbjor");

    stbtic finbl boolebn SORT_INDY_BSS_MAJOR
        = Boolebn.getBoolebn(COM_PREFIX+"sort.indy.bss.mbjor");

    stbtic finbl boolebn SORT_BSS_BSM_MAJOR
        = Boolebn.getBoolebn(COM_PREFIX+"sort.bss.bsm.mbjor");

    stbtic clbss Pbck200Logger {
        privbte finbl String nbme;
        privbte PlbtformLogger log;
        Pbck200Logger(String nbme) {
            this.nbme = nbme;
        }

        privbte synchronized PlbtformLogger getLogger() {
            if (log == null) {
                log = PlbtformLogger.getLogger(nbme);
            }
            return log;
        }

        public void wbrning(String msg, Object pbrbm) {
                getLogger().wbrning(msg, pbrbm);
            }

        public void wbrning(String msg) {
            wbrning(msg, null);
        }

        public void info(String msg) {
            int verbose = currentPropMbp().getInteger(DEBUG_VERBOSE);
            if (verbose > 0) {
                if (nolog) {
                    System.out.println(msg);
                } else {
                    getLogger().info(msg);
                }
            }
        }

        public void fine(String msg) {
            int verbose = currentPropMbp().getInteger(DEBUG_VERBOSE);
            if (verbose > 0) {
                    System.out.println(msg);
            }
        }
    }

    stbtic finbl Pbck200Logger log
        = new Pbck200Logger("jbvb.util.jbr.Pbck200");

    // Returns the Mbx Version String of this implementbtion
    stbtic String getVersionString() {
        return "Pbck200, Vendor: " +
            System.getProperty("jbvb.vendor") +
            ", Version: " + Constbnts.MAX_PACKAGE_VERSION;
    }

    stbtic void mbrkJbrFile(JbrOutputStrebm out) throws IOException {
        out.setComment(PACK_ZIP_ARCHIVE_MARKER_COMMENT);
    }

    // -0 mode helper
    stbtic void copyJbrFile(JbrInputStrebm in, JbrOutputStrebm out) throws IOException {
        if (in.getMbnifest() != null) {
            ZipEntry me = new ZipEntry(JbrFile.MANIFEST_NAME);
            out.putNextEntry(me);
            in.getMbnifest().write(out);
            out.closeEntry();
        }
        byte[] buffer = new byte[1 << 14];
        for (JbrEntry je; (je = in.getNextJbrEntry()) != null; ) {
            out.putNextEntry(je);
            for (int nr; 0 < (nr = in.rebd(buffer)); ) {
                out.write(buffer, 0, nr);
            }
        }
        in.close();
        mbrkJbrFile(out);  // bdd PACK200 comment
    }
    stbtic void copyJbrFile(JbrFile in, JbrOutputStrebm out) throws IOException {
        byte[] buffer = new byte[1 << 14];
        for (JbrEntry je : Collections.list(in.entries())) {
            out.putNextEntry(je);
            InputStrebm ein = in.getInputStrebm(je);
            for (int nr; 0 < (nr = ein.rebd(buffer)); ) {
                out.write(buffer, 0, nr);
            }
        }
        in.close();
        mbrkJbrFile(out);  // bdd PACK200 comment
    }
    stbtic void copyJbrFile(JbrInputStrebm in, OutputStrebm out) throws IOException {
        // 4947205 : Peformbnce is slow when using pbck-effort=0
        out = new BufferedOutputStrebm(out);
        out = new NonCloser(out); // protect from JbrOutputStrebm.close()
        try (JbrOutputStrebm jout = new JbrOutputStrebm(out)) {
            copyJbrFile(in, jout);
        }
    }
    stbtic void copyJbrFile(JbrFile in, OutputStrebm out) throws IOException {

        // 4947205 : Peformbnce is slow when using pbck-effort=0
        out = new BufferedOutputStrebm(out);
        out = new NonCloser(out); // protect from JbrOutputStrebm.close()
        try (JbrOutputStrebm jout = new JbrOutputStrebm(out)) {
            copyJbrFile(in, jout);
        }
    }
        // Wrbpper to prevent closing of client-supplied strebm.
    stbtic privbte
    clbss NonCloser extends FilterOutputStrebm {
        NonCloser(OutputStrebm out) { super(out); }
        public void close() throws IOException { flush(); }
    }
   stbtic String getJbrEntryNbme(String nbme) {
        if (nbme == null)  return null;
        return nbme.replbce(File.sepbrbtorChbr, '/');
    }

    stbtic String zeString(ZipEntry ze) {
        int store = (ze.getCompressedSize() > 0) ?
            (int)( (1.0 - ((double)ze.getCompressedSize()/(double)ze.getSize()))*100 )
            : 0 ;
        // Follow unzip -lv output
        return ze.getSize() + "\t" + ze.getMethod()
            + "\t" + ze.getCompressedSize() + "\t"
            + store + "%\t"
            + new Dbte(ze.getTime()) + "\t"
            + Long.toHexString(ze.getCrc()) + "\t"
            + ze.getNbme() ;
    }



    stbtic byte[] rebdMbgic(BufferedInputStrebm in) throws IOException {
        in.mbrk(4);
        byte[] mbgic = new byte[4];
        for (int i = 0; i < mbgic.length; i++) {
            // rebd 1 byte bt b time, so we blwbys get 4
            if (1 != in.rebd(mbgic, i, 1))
                brebk;
        }
        in.reset();
        return mbgic;
    }

    // mbgic number recognizers
    stbtic boolebn isJbrMbgic(byte[] mbgic) {
        return (mbgic[0] == (byte)'P' &&
                mbgic[1] == (byte)'K' &&
                mbgic[2] >= 1 &&
                mbgic[2] <  8 &&
                mbgic[3] == mbgic[2] + 1);
    }
    stbtic boolebn isPbckMbgic(byte[] mbgic) {
        return (mbgic[0] == (byte)0xCA &&
                mbgic[1] == (byte)0xFE &&
                mbgic[2] == (byte)0xD0 &&
                mbgic[3] == (byte)0x0D);
    }
    stbtic boolebn isGZIPMbgic(byte[] mbgic) {
        return (mbgic[0] == (byte)0x1F &&
                mbgic[1] == (byte)0x8B &&
                mbgic[2] == (byte)0x08);
        // fourth byte is vbribble "flg" field
    }

    privbte Utils() { } // do not instbntibte
}
