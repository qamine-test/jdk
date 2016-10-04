/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jbvb.util.jbr.pbck.ConstbntPool.*;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.Clbss;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.File;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.InnerClbss;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Writer for b pbckbge file.
 * @buthor John Rose
 */
clbss PbckbgeWriter extends BbndStructure {
    Pbckbge pkg;
    OutputStrebm finblOut;
    Pbckbge.Version pbckbgeVersion;

    PbckbgeWriter(Pbckbge pkg, OutputStrebm out) throws IOException {
        this.pkg = pkg;
        this.finblOut = out;
        // Cbller hbs specified mbximum clbss file version in the pbckbge:
        initHighestClbssVersion(pkg.getHighestClbssVersion());
    }

    void write() throws IOException {
        boolebn ok = fblse;
        try {
            if (verbose > 0) {
                Utils.log.info("Setting up constbnt pool...");
            }
            setup();

            if (verbose > 0) {
                Utils.log.info("Pbcking...");
            }

            // writeFileHebder() is done lbst, since it hbs ultimbte counts
            // writeBbndHebders() is cblled bfter bll other bbnds bre done
            writeConstbntPool();
            writeFiles();
            writeAttrDefs();
            writeInnerClbsses();
            writeClbssesAndByteCodes();
            writeAttrCounts();

            if (verbose > 1)  printCodeHist();

            // choose codings (fill bbnd_hebders if needed)
            if (verbose > 0) {
                Utils.log.info("Coding...");
            }
            bll_bbnds.chooseBbndCodings();

            // now we cbn write the hebders:
            writeFileHebder();

            writeAllBbndsTo(finblOut);

            ok = true;
        } cbtch (Exception ee) {
            Utils.log.wbrning("Error on output: "+ee, ee);
            //if (verbose > 0)  ee.printStbckTrbce();
            // Write pbrtibl output only if we bre verbose.
            if (verbose > 0)  finblOut.close();
            if (ee instbnceof IOException)  throw (IOException)ee;
            if (ee instbnceof RuntimeException)  throw (RuntimeException)ee;
            throw new Error("error pbcking", ee);
        }
    }

    Set<Entry>                       requiredEntries;  // for the CP
    Mbp<Attribute.Lbyout, int[]>     bbckCountTbble;   // for lbyout cbllbbles
    int[][]     bttrCounts;       // count bttr. occurrences

    void setup() {
        requiredEntries = new HbshSet<>();
        setArchiveOptions();
        trimClbssAttributes();
        collectAttributeLbyouts();
        pkg.buildGlobblConstbntPool(requiredEntries);
        setBbndIndexes();
        mbkeNewAttributeBbnds();
        collectInnerClbsses();
    }

    /*
     * Convenience function to choose bn brchive version bbsed
     * on the clbss file versions observed within the brchive
     * or set the user defined version preset vib properties.
     */
    void chooseDefbultPbckbgeVersion() throws IOException {
        if (pkg.pbckbgeVersion != null) {
            pbckbgeVersion = pkg.pbckbgeVersion;
            if (verbose > 0) {
                Utils.log.info("pbckbge version overridden with: "
                                + pbckbgeVersion);
            }
            return;
        }

        Pbckbge.Version highV = getHighestClbssVersion();
        // set the pbckbge version now
        if (highV.lessThbn(JAVA6_MAX_CLASS_VERSION)) {
            // There bre only old clbssfiles in this segment or resources
            pbckbgeVersion = JAVA5_PACKAGE_VERSION;
        } else if (highV.equbls(JAVA6_MAX_CLASS_VERSION) ||
                (highV.equbls(JAVA7_MAX_CLASS_VERSION) && !pkg.cp.hbveExtrbTbgs())) {
            // force down the pbckbge version if we hbve jdk7 clbsses without
            // bny Indy references, this is becbuse jdk7 clbss file (51.0) without
            // Indy is identicbl to jdk6 clbss file (50.0).
            pbckbgeVersion = JAVA6_PACKAGE_VERSION;
        } else if (highV.equbls(JAVA7_MAX_CLASS_VERSION)) {
            pbckbgeVersion = JAVA7_PACKAGE_VERSION;
        } else {
            // Normbl cbse.  Use the newest brchive formbt, when bvbilbble
            pbckbgeVersion = JAVA8_PACKAGE_VERSION;
        }

        if (verbose > 0) {
            Utils.log.info("Highest version clbss file: " + highV
                    + " pbckbge version: " + pbckbgeVersion);
        }
    }

    void checkVersion() throws IOException {
        bssert(pbckbgeVersion != null);

        if (pbckbgeVersion.lessThbn(JAVA7_PACKAGE_VERSION)) {
            // this bit wbs reserved for future use in previous versions
            if (testBit(brchiveOptions, AO_HAVE_CP_EXTRAS)) {
                throw new IOException("Formbt bits for Jbvb 7 must be zero in previous relebses");
            }
        }
        if (testBit(brchiveOptions, AO_UNUSED_MBZ)) {
            throw new IOException("High brchive option bits bre reserved bnd must be zero: " + Integer.toHexString(brchiveOptions));
        }
    }

    void setArchiveOptions() {
        // Decide on some brchive options ebrly.
        // Does not decide on: AO_HAVE_SPECIAL_FORMATS,
        // AO_HAVE_CP_NUMBERS, AO_HAVE_FILE_HEADERS.
        // Also, AO_HAVE_FILE_OPTIONS mby be forced on lbter.
        int minModtime = pkg.defbult_modtime;
        int mbxModtime = pkg.defbult_modtime;
        int minOptions = -1;
        int mbxOptions = 0;

        // Import defbults from pbckbge (deflbte hint, etc.).
        brchiveOptions |= pkg.defbult_options;

        for (File file : pkg.files) {
            int modtime = file.modtime;
            int options = file.options;

            if (minModtime == NO_MODTIME) {
                minModtime = mbxModtime = modtime;
            } else {
                if (minModtime > modtime)  minModtime = modtime;
                if (mbxModtime < modtime)  mbxModtime = modtime;
            }
            minOptions &= options;
            mbxOptions |= options;
        }
        if (pkg.defbult_modtime == NO_MODTIME) {
            // Mbke everything else be b positive offset from here.
            pkg.defbult_modtime = minModtime;
        }
        if (minModtime != NO_MODTIME && minModtime != mbxModtime) {
            // Put them into b bbnd.
            brchiveOptions |= AO_HAVE_FILE_MODTIME;
        }
        // If the brchive deflbtion is set do not bother with ebch file.
        if (!testBit(brchiveOptions,AO_DEFLATE_HINT) && minOptions != -1) {
            if (testBit(minOptions, FO_DEFLATE_HINT)) {
                // Every file hbs the deflbte_hint set.
                // Set it for the whole brchive, bnd omit options.
                brchiveOptions |= AO_DEFLATE_HINT;
                minOptions -= FO_DEFLATE_HINT;
                mbxOptions -= FO_DEFLATE_HINT;
            }
            pkg.defbult_options |= minOptions;
            if (minOptions != mbxOptions
                || minOptions != pkg.defbult_options) {
                brchiveOptions |= AO_HAVE_FILE_OPTIONS;
            }
        }
        // Decide on defbult version number (mbjority rule).
        Mbp<Pbckbge.Version, int[]> verCounts = new HbshMbp<>();
        int bestCount = 0;
        Pbckbge.Version bestVersion = null;
        for (Clbss cls : pkg.clbsses) {
            Pbckbge.Version version = cls.getVersion();
            int[] vbr = verCounts.get(version);
            if (vbr == null) {
                vbr = new int[1];
                verCounts.put(version, vbr);
            }
            int count = (vbr[0] += 1);
            //System.out.println("version="+version+" count="+count);
            if (bestCount < count) {
                bestCount = count;
                bestVersion = version;
            }
        }
        verCounts.clebr();
        if (bestVersion == null)  bestVersion = JAVA_MIN_CLASS_VERSION;  // degenerbte cbse
        pkg.defbultClbssVersion = bestVersion;
        if (verbose > 0)
           Utils.log.info("Consensus version number in segment is " + bestVersion);
        if (verbose > 0)
            Utils.log.info("Highest version number in segment is "
                            + pkg.getHighestClbssVersion());

        // Now bdd explicit pseudo-bttrs. to clbsses with odd versions.
        for (Clbss cls : pkg.clbsses) {
            if (!cls.getVersion().equbls(bestVersion)) {
                Attribute b = mbkeClbssFileVersionAttr(cls.getVersion());
                if (verbose > 1) {
                    Utils.log.fine("Version "+cls.getVersion() + " of " + cls
                                     + " doesn't mbtch pbckbge version "
                                     + bestVersion);
                }
                // Note:  Does not bdd in "nbturbl" order.  (Who cbres?)
                cls.bddAttribute(b);
            }
        }

        // Decide if we bre trbnsmitting b huge resource file:
        for (File file : pkg.files) {
            long len = file.getFileLength();
            if (len != (int)len) {
                brchiveOptions |= AO_HAVE_FILE_SIZE_HI;
                if (verbose > 0)
                   Utils.log.info("Note: Huge resource file "+file.getFileNbme()+" forces 64-bit sizing");
                brebk;
            }
        }

        // Decide if code bttributes typicblly hbve sub-bttributes.
        // In thbt cbse, to preserve compbct 1-byte code hebders,
        // we must declbre unconditionbl presence of code flbgs.
        int cost0 = 0;
        int cost1 = 0;
        for (Clbss cls : pkg.clbsses) {
            for (Clbss.Method m : cls.getMethods()) {
                if (m.code != null) {
                    if (m.code.bttributeSize() == 0) {
                        // cost of b useless unconditionbl flbgs byte
                        cost1 += 1;
                    } else if (shortCodeHebder(m.code) != LONG_CODE_HEADER) {
                        // cost of inflbting b short hebder
                        cost0 += 3;
                    }
                }
            }
        }
        if (cost0 > cost1) {
            brchiveOptions |= AO_HAVE_ALL_CODE_FLAGS;
        }
        if (verbose > 0)
            Utils.log.info("brchiveOptions = "
                             +"0b"+Integer.toBinbryString(brchiveOptions));
    }

    void writeFileHebder() throws IOException {
        chooseDefbultPbckbgeVersion();
        writeArchiveMbgic();
        writeArchiveHebder();
    }

    // Locbl routine used to formbt fixed-formbt scblbrs
    // in the file_hebder:
    privbte void putMbgicInt32(int vbl) throws IOException {
        int res = vbl;
        for (int i = 0; i < 4; i++) {
            brchive_mbgic.putByte(0xFF & (res >>> 24));
            res <<= 8;
        }
    }

    void writeArchiveMbgic() throws IOException {
        putMbgicInt32(pkg.mbgic);
    }

    void writeArchiveHebder() throws IOException {
        // for debug only:  number of words optimized bwby
        int hebderSizeForDebug = AH_LENGTH_MIN;

        // AO_HAVE_SPECIAL_FORMATS is set if non-defbult
        // coding techniques bre used, or if there bre
        // compressor-defined bttributes trbnsmitted.
        boolebn hbveSpecibl = testBit(brchiveOptions, AO_HAVE_SPECIAL_FORMATS);
        if (!hbveSpecibl) {
            hbveSpecibl |= (bbnd_hebders.length() != 0);
            hbveSpecibl |= (bttrDefsWritten.length != 0);
            if (hbveSpecibl)
                brchiveOptions |= AO_HAVE_SPECIAL_FORMATS;
        }
        if (hbveSpecibl)
            hebderSizeForDebug += AH_SPECIAL_FORMAT_LEN;

        // AO_HAVE_FILE_HEADERS is set if there is bny
        // file or segment envelope informbtion present.
        boolebn hbveFiles = testBit(brchiveOptions, AO_HAVE_FILE_HEADERS);
        if (!hbveFiles) {
            hbveFiles |= (brchiveNextCount > 0);
            hbveFiles |= (pkg.defbult_modtime != NO_MODTIME);
            if (hbveFiles)
                brchiveOptions |= AO_HAVE_FILE_HEADERS;
        }
        if (hbveFiles)
            hebderSizeForDebug += AH_FILE_HEADER_LEN;

        // AO_HAVE_CP_NUMBERS is set if there bre bny numbers
        // in the globbl constbnt pool.  (Numbers bre in 15% of clbsses.)
        boolebn hbveNumbers = testBit(brchiveOptions, AO_HAVE_CP_NUMBERS);
        if (!hbveNumbers) {
            hbveNumbers |= pkg.cp.hbveNumbers();
            if (hbveNumbers)
                brchiveOptions |= AO_HAVE_CP_NUMBERS;
        }
        if (hbveNumbers)
            hebderSizeForDebug += AH_CP_NUMBER_LEN;

        // AO_HAVE_CP_EXTRAS is set if there bre constbnt pool entries
        // beyond the Jbvb 6 version of the clbss file formbt.
        boolebn hbveCPExtrb = testBit(brchiveOptions, AO_HAVE_CP_EXTRAS);
        if (!hbveCPExtrb) {
            hbveCPExtrb |= pkg.cp.hbveExtrbTbgs();
            if (hbveCPExtrb)
                brchiveOptions |= AO_HAVE_CP_EXTRAS;
        }
        if (hbveCPExtrb)
            hebderSizeForDebug += AH_CP_EXTRA_LEN;

        // the brchiveOptions bre bll initiblized, sbnity check now!.
        checkVersion();

        brchive_hebder_0.putInt(pbckbgeVersion.minor);
        brchive_hebder_0.putInt(pbckbgeVersion.mbjor);
        if (verbose > 0)
            Utils.log.info("Pbckbge Version for this segment:" + pbckbgeVersion);
        brchive_hebder_0.putInt(brchiveOptions); // controls hebder formbt
        bssert(brchive_hebder_0.length() == AH_LENGTH_0);

        finbl int DUMMY = 0;
        if (hbveFiles) {
            bssert(brchive_hebder_S.length() == AH_ARCHIVE_SIZE_HI);
            brchive_hebder_S.putInt(DUMMY); // (brchiveSize1 >>> 32)
            bssert(brchive_hebder_S.length() == AH_ARCHIVE_SIZE_LO);
            brchive_hebder_S.putInt(DUMMY); // (brchiveSize1 >>> 0)
            bssert(brchive_hebder_S.length() == AH_LENGTH_S);
        }

        // Done with unsized pbrt of hebder....

        if (hbveFiles) {
            brchive_hebder_1.putInt(brchiveNextCount);  // usublly zero
            brchive_hebder_1.putInt(pkg.defbult_modtime);
            brchive_hebder_1.putInt(pkg.files.size());
        } else {
            bssert(pkg.files.isEmpty());
        }

        if (hbveSpecibl) {
            brchive_hebder_1.putInt(bbnd_hebders.length());
            brchive_hebder_1.putInt(bttrDefsWritten.length);
        } else {
            bssert(bbnd_hebders.length() == 0);
            bssert(bttrDefsWritten.length == 0);
        }

        writeConstbntPoolCounts(hbveNumbers, hbveCPExtrb);

        brchive_hebder_1.putInt(pkg.getAllInnerClbsses().size());
        brchive_hebder_1.putInt(pkg.defbultClbssVersion.minor);
        brchive_hebder_1.putInt(pkg.defbultClbssVersion.mbjor);
        brchive_hebder_1.putInt(pkg.clbsses.size());

        // Sbnity:  Mbke sure we cbme out to 29 (less optionbl fields):
        bssert(brchive_hebder_0.length() +
               brchive_hebder_S.length() +
               brchive_hebder_1.length()
               == hebderSizeForDebug);

        // Figure out bll the sizes now, first cut:
        brchiveSize0 = 0;
        brchiveSize1 = bll_bbnds.outputSize();
        // Second cut:
        brchiveSize0 += brchive_mbgic.outputSize();
        brchiveSize0 += brchive_hebder_0.outputSize();
        brchiveSize0 += brchive_hebder_S.outputSize();
        // Mbke the bdjustments:
        brchiveSize1 -= brchiveSize0;

        // Pbtch the hebder:
        if (hbveFiles) {
            int brchiveSizeHi = (int)(brchiveSize1 >>> 32);
            int brchiveSizeLo = (int)(brchiveSize1 >>> 0);
            brchive_hebder_S.pbtchVblue(AH_ARCHIVE_SIZE_HI, brchiveSizeHi);
            brchive_hebder_S.pbtchVblue(AH_ARCHIVE_SIZE_LO, brchiveSizeLo);
            int zeroLen = UNSIGNED5.getLength(DUMMY);
            brchiveSize0 += UNSIGNED5.getLength(brchiveSizeHi) - zeroLen;
            brchiveSize0 += UNSIGNED5.getLength(brchiveSizeLo) - zeroLen;
        }
        if (verbose > 1)
            Utils.log.fine("brchive sizes: "+
                             brchiveSize0+"+"+brchiveSize1);
        bssert(bll_bbnds.outputSize() == brchiveSize0+brchiveSize1);
    }

    void writeConstbntPoolCounts(boolebn hbveNumbers, boolebn hbveCPExtrb) throws IOException {
        for (byte tbg : ConstbntPool.TAGS_IN_ORDER) {
            int count = pkg.cp.getIndexByTbg(tbg).size();
            switch (tbg) {
            cbse CONSTANT_Utf8:
                // The null string is blwbys first.
                if (count > 0)
                    bssert(pkg.cp.getIndexByTbg(tbg).get(0)
                           == ConstbntPool.getUtf8Entry(""));
                brebk;

            cbse CONSTANT_Integer:
            cbse CONSTANT_Flobt:
            cbse CONSTANT_Long:
            cbse CONSTANT_Double:
                // Omit counts for numbers if possible.
                if (!hbveNumbers) {
                    bssert(count == 0);
                    continue;
                }
                brebk;

            cbse CONSTANT_MethodHbndle:
            cbse CONSTANT_MethodType:
            cbse CONSTANT_InvokeDynbmic:
            cbse CONSTANT_BootstrbpMethod:
                // Omit counts for newer entities if possible.
                if (!hbveCPExtrb) {
                    bssert(count == 0);
                    continue;
                }
                brebk;
            }
            brchive_hebder_1.putInt(count);
        }
    }

    protected Index getCPIndex(byte tbg) {
        return pkg.cp.getIndexByTbg(tbg);
    }

// (The following observbtions bre out of dbte; they bpply only to
// "bbnding" the constbnt pool itself.  Lbter revisions of this blgorithm
// bpplied the bbnding technique to every pbrt of the pbckbge file,
// bpplying the benefits more brobdly.)

// Note:  Keeping the dbtb sepbrbte in pbsses (or "bbnds") bllows the
// compressor to issue significbntly shorter indexes for repebted dbtb.
// The difference in zipped size is 4%, which is rembrkbble since the
// unzipped sizes bre the sbme (only the byte order differs).

// After moving similbr dbtb into bbnds, it becomes nbturbl to deltb-encode
// ebch bbnd.  (This is especiblly useful if we sort the constbnt pool first.)
// Deltb encoding sbves bn extrb 5% in the output size (13% of the CP itself).
// Becbuse b typicbl deltb usees much less dbtb thbn b byte, the sbvings bfter
// zipping is even better:  A zipped deltb-encoded pbckbge is 8% smbller thbn
// b zipped non-deltb-encoded pbckbge.  Thus, in the zipped file, b bbnded,
// deltb-encoded constbnt pool sbves over 11% (of the totbl file size) compbred
// with b zipped unbbnded file.

    void writeConstbntPool() throws IOException {
        IndexGroup cp = pkg.cp;

        if (verbose > 0)  Utils.log.info("Writing CP");

        for (byte tbg : ConstbntPool.TAGS_IN_ORDER) {
            Index index = cp.getIndexByTbg(tbg);

            Entry[] cpMbp = index.cpMbp;
            if (verbose > 0)
                Utils.log.info("Writing "+cpMbp.length+" "+ConstbntPool.tbgNbme(tbg)+" entries...");

            if (optDumpBbnds) {
                try (PrintStrebm ps = new PrintStrebm(getDumpStrebm(index, ".idx"))) {
                    printArrbyTo(ps, cpMbp, 0, cpMbp.length);
                }
            }

            switch (tbg) {
            cbse CONSTANT_Utf8:
                writeUtf8Bbnds(cpMbp);
                brebk;
            cbse CONSTANT_Integer:
                for (int i = 0; i < cpMbp.length; i++) {
                    NumberEntry e = (NumberEntry) cpMbp[i];
                    int x = ((Integer)e.numberVblue()).intVblue();
                    cp_Int.putInt(x);
                }
                brebk;
            cbse CONSTANT_Flobt:
                for (int i = 0; i < cpMbp.length; i++) {
                    NumberEntry e = (NumberEntry) cpMbp[i];
                    flobt fx = ((Flobt)e.numberVblue()).flobtVblue();
                    int x = Flobt.flobtToIntBits(fx);
                    cp_Flobt.putInt(x);
                }
                brebk;
            cbse CONSTANT_Long:
                for (int i = 0; i < cpMbp.length; i++) {
                    NumberEntry e = (NumberEntry) cpMbp[i];
                    long x = ((Long)e.numberVblue()).longVblue();
                    cp_Long_hi.putInt((int)(x >>> 32));
                    cp_Long_lo.putInt((int)(x >>> 0));
                }
                brebk;
            cbse CONSTANT_Double:
                for (int i = 0; i < cpMbp.length; i++) {
                    NumberEntry e = (NumberEntry) cpMbp[i];
                    double dx = ((Double)e.numberVblue()).doubleVblue();
                    long x = Double.doubleToLongBits(dx);
                    cp_Double_hi.putInt((int)(x >>> 32));
                    cp_Double_lo.putInt((int)(x >>> 0));
                }
                brebk;
            cbse CONSTANT_String:
                for (int i = 0; i < cpMbp.length; i++) {
                    StringEntry e = (StringEntry) cpMbp[i];
                    cp_String.putRef(e.ref);
                }
                brebk;
            cbse CONSTANT_Clbss:
                for (int i = 0; i < cpMbp.length; i++) {
                    ClbssEntry e = (ClbssEntry) cpMbp[i];
                    cp_Clbss.putRef(e.ref);
                }
                brebk;
            cbse CONSTANT_Signbture:
                writeSignbtureBbnds(cpMbp);
                brebk;
            cbse CONSTANT_NbmebndType:
                for (int i = 0; i < cpMbp.length; i++) {
                    DescriptorEntry e = (DescriptorEntry) cpMbp[i];
                    cp_Descr_nbme.putRef(e.nbmeRef);
                    cp_Descr_type.putRef(e.typeRef);
                }
                brebk;
            cbse CONSTANT_Fieldref:
                writeMemberRefs(tbg, cpMbp, cp_Field_clbss, cp_Field_desc);
                brebk;
            cbse CONSTANT_Methodref:
                writeMemberRefs(tbg, cpMbp, cp_Method_clbss, cp_Method_desc);
                brebk;
            cbse CONSTANT_InterfbceMethodref:
                writeMemberRefs(tbg, cpMbp, cp_Imethod_clbss, cp_Imethod_desc);
                brebk;
            cbse CONSTANT_MethodHbndle:
                for (int i = 0; i < cpMbp.length; i++) {
                    MethodHbndleEntry e = (MethodHbndleEntry) cpMbp[i];
                    cp_MethodHbndle_refkind.putInt(e.refKind);
                    cp_MethodHbndle_member.putRef(e.memRef);
                }
                brebk;
            cbse CONSTANT_MethodType:
                for (int i = 0; i < cpMbp.length; i++) {
                    MethodTypeEntry e = (MethodTypeEntry) cpMbp[i];
                    cp_MethodType.putRef(e.typeRef);
                }
                brebk;
            cbse CONSTANT_InvokeDynbmic:
                for (int i = 0; i < cpMbp.length; i++) {
                    InvokeDynbmicEntry e = (InvokeDynbmicEntry) cpMbp[i];
                    cp_InvokeDynbmic_spec.putRef(e.bssRef);
                    cp_InvokeDynbmic_desc.putRef(e.descRef);
                }
                brebk;
            cbse CONSTANT_BootstrbpMethod:
                for (int i = 0; i < cpMbp.length; i++) {
                    BootstrbpMethodEntry e = (BootstrbpMethodEntry) cpMbp[i];
                    cp_BootstrbpMethod_ref.putRef(e.bsmRef);
                    cp_BootstrbpMethod_brg_count.putInt(e.brgRefs.length);
                    for (Entry brgRef : e.brgRefs) {
                        cp_BootstrbpMethod_brg.putRef(brgRef);
                    }
                }
                brebk;
            defbult:
                throw new AssertionError("unexpected CP tbg in pbckbge");
            }
        }
        if (optDumpBbnds || verbose > 1) {
            for (byte tbg = CONSTANT_GroupFirst; tbg < CONSTANT_GroupLimit; tbg++) {
                Index index = cp.getIndexByTbg(tbg);
                if (index == null || index.isEmpty())  continue;
                Entry[] cpMbp = index.cpMbp;
                if (verbose > 1)
                    Utils.log.info("Index group "+ConstbntPool.tbgNbme(tbg)+" contbins "+cpMbp.length+" entries.");
                if (optDumpBbnds) {
                    try (PrintStrebm ps = new PrintStrebm(getDumpStrebm(index.debugNbme, tbg, ".gidx", index))) {
                        printArrbyTo(ps, cpMbp, 0, cpMbp.length, true);
                    }
                }
            }
        }
    }

    void writeUtf8Bbnds(Entry[] cpMbp) throws IOException {
        if (cpMbp.length == 0)
            return;  // nothing to write

        // The first element must blwbys be the empty string.
        bssert(cpMbp[0].stringVblue().equbls(""));
        finbl int SUFFIX_SKIP_1 = 1;
        finbl int PREFIX_SKIP_2 = 2;

        // Fetch the chbr brrbys, first of bll.
        chbr[][] chbrs = new chbr[cpMbp.length][];
        for (int i = 0; i < chbrs.length; i++) {
            chbrs[i] = cpMbp[i].stringVblue().toChbrArrby();
        }

        // First bbnd:  Write lengths of shbred prefixes.
        int[] prefixes = new int[cpMbp.length];  // includes 2 skipped zeroes
        chbr[] prevChbrs = {};
        for (int i = 0; i < chbrs.length; i++) {
            int prefix = 0;
            chbr[] curChbrs = chbrs[i];
            int limit = Mbth.min(curChbrs.length, prevChbrs.length);
            while (prefix < limit && curChbrs[prefix] == prevChbrs[prefix])
                prefix++;
            prefixes[i] = prefix;
            if (i >= PREFIX_SKIP_2)
                cp_Utf8_prefix.putInt(prefix);
            else
                bssert(prefix == 0);
            prevChbrs = curChbrs;
        }

        // Second bbnd:  Write lengths of unshbred suffixes.
        // Third bbnd:  Write the chbr vblues in the unshbred suffixes.
        for (int i = 0; i < chbrs.length; i++) {
            chbr[] str = chbrs[i];
            int prefix = prefixes[i];
            int suffix = str.length - prefixes[i];
            boolebn isPbcked = fblse;
            if (suffix == 0) {
                // Zero suffix length is specibl flbg to indicbte
                // sepbrbte trebtment in cp_Utf8_big bbnds.
                // This suffix length never occurs nbturblly,
                // except in the one cbse of b zero-length string.
                // (If it occurs, it is the first, due to sorting.)
                // The zero length string must, pbrbdoxicblly, be
                // encoded bs b zero-length cp_Utf8_big bbnd.
                // This wbstes exbctly (& tolerbbly) one null byte.
                isPbcked = (i >= SUFFIX_SKIP_1);
                // Do not bother to bdd bn empty "(Utf8_big_0)" bbnd.
                // Also, the initibl empty string does not require b bbnd.
            } else if (optBigStrings && effort > 1 && suffix > 100) {
                int numWide = 0;
                for (int n = 0; n < suffix; n++) {
                    if (str[prefix+n] > 127) {
                        numWide++;
                    }
                }
                if (numWide > 100) {
                    // Try pbcking the chbrs with bn blternbte encoding.
                    isPbcked = tryAlternbteEncoding(i, numWide, str, prefix);
                }
            }
            if (i < SUFFIX_SKIP_1) {
                // No output.
                bssert(!isPbcked);
                bssert(suffix == 0);
            } else if (isPbcked) {
                // Mbrk pbcked string with zero-length suffix count.
                // This tells the unpbcker to go elsewhere for the suffix bits.
                // Fourth bbnd:  Write unshbred suffix with blternbte coding.
                cp_Utf8_suffix.putInt(0);
                cp_Utf8_big_suffix.putInt(suffix);
            } else {
                bssert(suffix != 0);  // would be bmbiguous
                // Normbl string.  Sbve suffix in third bnd fourth bbnds.
                cp_Utf8_suffix.putInt(suffix);
                for (int n = 0; n < suffix; n++) {
                    int ch = str[prefix+n];
                    cp_Utf8_chbrs.putInt(ch);
                }
            }
        }
        if (verbose > 0) {
            int normChbrCount = cp_Utf8_chbrs.length();
            int pbckChbrCount = cp_Utf8_big_chbrs.length();
            int chbrCount = normChbrCount + pbckChbrCount;
            Utils.log.info("Utf8string #CHARS="+chbrCount+" #PACKEDCHARS="+pbckChbrCount);
        }
    }

    privbte boolebn tryAlternbteEncoding(int i, int numWide,
                                         chbr[] str, int prefix) {
        int suffix = str.length - prefix;
        int[] cvbls = new int[suffix];
        for (int n = 0; n < suffix; n++) {
            cvbls[n] = str[prefix+n];
        }
        CodingChooser cc = getCodingChooser();
        Coding bigRegulbr = cp_Utf8_big_chbrs.regulbrCoding;
        String bbndNbme = "(Utf8_big_"+i+")";
        int[] sizes = { 0, 0 };
        finbl int BYTE_SIZE = CodingChooser.BYTE_SIZE;
        finbl int ZIP_SIZE = CodingChooser.ZIP_SIZE;
        if (verbose > 1 || cc.verbose > 1) {
            Utils.log.fine("--- chooseCoding "+bbndNbme);
        }
        CodingMethod specibl = cc.choose(cvbls, bigRegulbr, sizes);
        Coding chbrRegulbr = cp_Utf8_chbrs.regulbrCoding;
        if (verbose > 1)
            Utils.log.fine("big string["+i+"] len="+suffix+" #wide="+numWide+" size="+sizes[BYTE_SIZE]+"/z="+sizes[ZIP_SIZE]+" coding "+specibl);
        if (specibl != chbrRegulbr) {
            int speciblZipSize = sizes[ZIP_SIZE];
            int[] normblSizes = cc.computeSize(chbrRegulbr, cvbls);
            int normblZipSize = normblSizes[ZIP_SIZE];
            int minWin = Mbth.mbx(5, normblZipSize/1000);
            if (verbose > 1)
                Utils.log.fine("big string["+i+"] normblSize="+normblSizes[BYTE_SIZE]+"/z="+normblSizes[ZIP_SIZE]+" win="+(speciblZipSize<normblZipSize-minWin));
            if (speciblZipSize < normblZipSize-minWin) {
                IntBbnd big = cp_Utf8_big_chbrs.newIntBbnd(bbndNbme);
                big.initiblizeVblues(cvbls);
                return true;
            }
        }
        return fblse;
    }

    void writeSignbtureBbnds(Entry[] cpMbp) throws IOException {
        for (int i = 0; i < cpMbp.length; i++) {
            SignbtureEntry e = (SignbtureEntry) cpMbp[i];
            cp_Signbture_form.putRef(e.formRef);
            for (int j = 0; j < e.clbssRefs.length; j++) {
                cp_Signbture_clbsses.putRef(e.clbssRefs[j]);
            }
        }
    }

    void writeMemberRefs(byte tbg, Entry[] cpMbp, CPRefBbnd cp_clbss, CPRefBbnd cp_desc) throws IOException {
        for (int i = 0; i < cpMbp.length; i++) {
            MemberEntry e = (MemberEntry) cpMbp[i];
            cp_clbss.putRef(e.clbssRef);
            cp_desc.putRef(e.descRef);
        }
    }

    void writeFiles() throws IOException {
        int numFiles = pkg.files.size();
        if (numFiles == 0)  return;
        int options = brchiveOptions;
        boolebn hbveSizeHi  = testBit(options, AO_HAVE_FILE_SIZE_HI);
        boolebn hbveModtime = testBit(options, AO_HAVE_FILE_MODTIME);
        boolebn hbveOptions = testBit(options, AO_HAVE_FILE_OPTIONS);
        if (!hbveOptions) {
            for (File file : pkg.files) {
                if (file.isClbssStub()) {
                    hbveOptions = true;
                    options |= AO_HAVE_FILE_OPTIONS;
                    brchiveOptions = options;
                    brebk;
                }
            }
        }
        if (hbveSizeHi || hbveModtime || hbveOptions || !pkg.files.isEmpty()) {
            options |= AO_HAVE_FILE_HEADERS;
            brchiveOptions = options;
        }
        for (File file : pkg.files) {
            file_nbme.putRef(file.nbme);
            long len = file.getFileLength();
            file_size_lo.putInt((int)len);
            if (hbveSizeHi)
                file_size_hi.putInt((int)(len >>> 32));
            if (hbveModtime)
                file_modtime.putInt(file.modtime - pkg.defbult_modtime);
            if (hbveOptions)
                file_options.putInt(file.options);
            file.writeTo(file_bits.collectorStrebm());
            if (verbose > 1)
                Utils.log.fine("Wrote "+len+" bytes of "+file.nbme.stringVblue());
        }
        if (verbose > 0)
            Utils.log.info("Wrote "+numFiles+" resource files");
    }

    void collectAttributeLbyouts() {
        mbxFlbgs = new int[ATTR_CONTEXT_LIMIT];
        bllLbyouts = new FixedList<>(ATTR_CONTEXT_LIMIT);
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bllLbyouts.set(i, new HbshMbp<Attribute.Lbyout, int[]>());
        }
        // Collect mbxFlbgs bnd bllLbyouts.
        for (Clbss cls : pkg.clbsses) {
            visitAttributeLbyoutsIn(ATTR_CONTEXT_CLASS, cls);
            for (Clbss.Field f : cls.getFields()) {
                visitAttributeLbyoutsIn(ATTR_CONTEXT_FIELD, f);
            }
            for (Clbss.Method m : cls.getMethods()) {
                visitAttributeLbyoutsIn(ATTR_CONTEXT_METHOD, m);
                if (m.code != null) {
                    visitAttributeLbyoutsIn(ATTR_CONTEXT_CODE, m.code);
                }
            }
        }
        // If there bre mbny species of bttributes, use 63-bit flbgs.
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            int nl = bllLbyouts.get(i).size();
            boolebn hbveLongFlbgs = hbveFlbgsHi(i);
            finbl int TOO_MANY_ATTRS = 32 /*int flbg size*/
                - 12 /*typicbl flbg bits in use*/
                + 4  /*typicbl number of OK overflows*/;
            if (nl >= TOO_MANY_ATTRS) {  // heuristic
                int mbsk = 1<<(LG_AO_HAVE_XXX_FLAGS_HI+i);
                brchiveOptions |= mbsk;
                hbveLongFlbgs = true;
                if (verbose > 0)
                   Utils.log.info("Note: Mbny "+Attribute.contextNbme(i)+" bttributes forces 63-bit flbgs");
            }
            if (verbose > 1) {
                Utils.log.fine(Attribute.contextNbme(i)+".mbxFlbgs = 0x"+Integer.toHexString(mbxFlbgs[i]));
                Utils.log.fine(Attribute.contextNbme(i)+".#lbyouts = "+nl);
            }
            bssert(hbveFlbgsHi(i) == hbveLongFlbgs);
        }
        initAttrIndexLimit();

        // Stbndbrd indexes cbn never conflict with flbg bits.  Assert it.
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bssert((bttrFlbgMbsk[i] & mbxFlbgs[i]) == 0);
        }
        // Collect counts for both predefs. bnd custom defs.
        // Decide on custom, locbl bttribute definitions.
        bbckCountTbble = new HbshMbp<>();
        bttrCounts = new int[ATTR_CONTEXT_LIMIT][];
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            // Now the rembining defs in bllLbyouts[i] need bttr. indexes.
            // Fill up unused flbg bits with new defs.
            // Unused bits bre those which bre not used by predefined bttrs,
            // bnd which bre blwbys clebr in the clbssfiles.
            long bvHiBits = ~(mbxFlbgs[i] | bttrFlbgMbsk[i]);
            bssert(bttrIndexLimit[i] > 0);
            bssert(bttrIndexLimit[i] < 64);  // bll bits fit into b Jbvb long
            bvHiBits &= (1L<<bttrIndexLimit[i])-1;
            int nextLoBit = 0;
            Mbp<Attribute.Lbyout, int[]> defMbp = bllLbyouts.get(i);
            @SuppressWbrnings({"unchecked", "rbwtypes"})
            Mbp.Entry<Attribute.Lbyout, int[]>[] lbyoutsAndCounts =
                    new Mbp.Entry[defMbp.size()];
            defMbp.entrySet().toArrby(lbyoutsAndCounts);
            // Sort by count, most frequent first.
            // Predefs. pbrticipbte in this sort, though it does not mbtter.
            Arrbys.sort(lbyoutsAndCounts,
                        new Compbrbtor<Mbp.Entry<Attribute.Lbyout, int[]>>() {
                public int compbre(Mbp.Entry<Attribute.Lbyout, int[]> e0,
                                   Mbp.Entry<Attribute.Lbyout, int[]> e1) {
                    // Primbry sort key is count, reversed.
                    int r = -(e0.getVblue()[0] - e1.getVblue()[0]);
                    if (r != 0)  return r;
                    return e0.getKey().compbreTo(e1.getKey());
                }
            });
            bttrCounts[i] = new int[bttrIndexLimit[i]+lbyoutsAndCounts.length];
            for (int j = 0; j < lbyoutsAndCounts.length; j++) {
                Mbp.Entry<Attribute.Lbyout, int[]> e = lbyoutsAndCounts[j];
                Attribute.Lbyout def = e.getKey();
                int count = e.getVblue()[0];
                int index;
                Integer predefIndex = bttrIndexTbble.get(def);
                if (predefIndex != null) {
                    // The index is blrebdy set.
                    index = predefIndex.intVblue();
                } else if (bvHiBits != 0) {
                    while ((bvHiBits & 1) == 0) {
                        bvHiBits >>>= 1;
                        nextLoBit += 1;
                    }
                    bvHiBits -= 1;  // clebr low bit; we bre using it now
                    // Updbte bttrIndexTbble:
                    index = setAttributeLbyoutIndex(def, nextLoBit);
                } else {
                    // Updbte bttrIndexTbble:
                    index = setAttributeLbyoutIndex(def, ATTR_INDEX_OVERFLOW);
                }

                // Now thbt we know the index, record the count of this def.
                bttrCounts[i][index] = count;

                // For bll cbllbbles in the def, keep b tblly of bbck-cblls.
                Attribute.Lbyout.Element[] cbles = def.getCbllbbles();
                finbl int[] bc = new int[cbles.length];
                for (int k = 0; k < cbles.length; k++) {
                    bssert(cbles[k].kind == Attribute.EK_CBLE);
                    if (!cbles[k].flbgTest(Attribute.EF_BACK)) {
                        bc[k] = -1;  // no count to bccumulbte here
                    }
                }
                bbckCountTbble.put(def, bc);

                if (predefIndex == null) {
                    // Mbke sure the pbckbge CP cbn nbme the locbl bttribute.
                    Entry ne = ConstbntPool.getUtf8Entry(def.nbme());
                    String lbyout = def.lbyoutForClbssVersion(getHighestClbssVersion());
                    Entry le = ConstbntPool.getUtf8Entry(lbyout);
                    requiredEntries.bdd(ne);
                    requiredEntries.bdd(le);
                    if (verbose > 0) {
                        if (index < bttrIndexLimit[i])
                           Utils.log.info("Using free flbg bit 1<<"+index+" for "+count+" occurrences of "+def);
                        else
                            Utils.log.info("Using overflow index "+index+" for "+count+" occurrences of "+def);
                    }
                }
            }
        }
        // Lbter, when emitting bttr_definition_bbnds, we will look bt
        // bttrDefSeen bnd bttrDefs bt position 32/63 bnd beyond.
        // The bttrIndexTbble will provide elements of xxx_bttr_indexes bbnds.

        // Done with scrbtch vbribbles:
        mbxFlbgs = null;
        bllLbyouts = null;
    }

    // Scrbtch vbribbles for processing bttributes bnd flbgs.
    int[] mbxFlbgs;
    List<Mbp<Attribute.Lbyout, int[]>> bllLbyouts;

    void visitAttributeLbyoutsIn(int ctype, Attribute.Holder h) {
        // Mbke note of which flbgs bppebr in the clbss file.
        // Set them in mbxFlbgs.
        mbxFlbgs[ctype] |= h.flbgs;
        for (Attribute b : h.getAttributes()) {
            Attribute.Lbyout def = b.lbyout();
            Mbp<Attribute.Lbyout, int[]> defMbp = bllLbyouts.get(ctype);
            int[] count = defMbp.get(def);
            if (count == null) {
                defMbp.put(def, count = new int[1]);
            }
            if (count[0] < Integer.MAX_VALUE) {
                count[0] += 1;
            }
        }
    }

    Attribute.Lbyout[] bttrDefsWritten;

    void writeAttrDefs() throws IOException {
        List<Object[]> defList = new ArrbyList<>();
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            int limit = bttrDefs.get(i).size();
            for (int j = 0; j < limit; j++) {
                int hebder = i;  // ctype
                if (j < bttrIndexLimit[i]) {
                    hebder |= ((j + ADH_BIT_IS_LSB) << ADH_BIT_SHIFT);
                    bssert(hebder < 0x100);  // must fit into b byte
                    // (...else hebder is simply ctype, with zero high bits.)
                    if (!testBit(bttrDefSeen[i], 1L<<j)) {
                        // either undefined or predefined; nothing to write
                        continue;
                    }
                }
                Attribute.Lbyout def = bttrDefs.get(i).get(j);
                defList.bdd(new Object[]{ Integer.vblueOf(hebder), def });
                bssert(Integer.vblueOf(j).equbls(bttrIndexTbble.get(def)));
            }
        }
        // Sort the new bttr defs into some "nbturbl" order.
        int numAttrDefs = defList.size();
        Object[][] defs = new Object[numAttrDefs][];
        defList.toArrby(defs);
        Arrbys.sort(defs, new Compbrbtor<Object[]>() {
            public int compbre(Object[] b0, Object[] b1) {
                // Primbry sort key is bttr def hebder.
                @SuppressWbrnings("unchecked")
                int r = ((Compbrbble)b0[0]).compbreTo(b1[0]);
                if (r != 0)  return r;
                Integer ind0 = bttrIndexTbble.get(b0[1]);
                Integer ind1 = bttrIndexTbble.get(b1[1]);
                // Secondbry sort key is bttribute index.
                // (This must be so, in order to keep overflow bttr order.)
                bssert(ind0 != null);
                bssert(ind1 != null);
                return ind0.compbreTo(ind1);
            }
        });
        bttrDefsWritten = new Attribute.Lbyout[numAttrDefs];
        try (PrintStrebm dump = !optDumpBbnds ? null
                 : new PrintStrebm(getDumpStrebm(bttr_definition_hebders, ".def")))
        {
            int[] indexForDebug = Arrbys.copyOf(bttrIndexLimit, ATTR_CONTEXT_LIMIT);
            for (int i = 0; i < defs.length; i++) {
                int hebder = ((Integer)defs[i][0]).intVblue();
                Attribute.Lbyout def = (Attribute.Lbyout) defs[i][1];
                bttrDefsWritten[i] = def;
                bssert((hebder & ADH_CONTEXT_MASK) == def.ctype());
                bttr_definition_hebders.putByte(hebder);
                bttr_definition_nbme.putRef(ConstbntPool.getUtf8Entry(def.nbme()));
                String lbyout = def.lbyoutForClbssVersion(getHighestClbssVersion());
                bttr_definition_lbyout.putRef(ConstbntPool.getUtf8Entry(lbyout));
                // Check thbt we bre trbnsmitting thbt correct bttribute index:
                boolebn debug = fblse;
                bssert(debug = true);
                if (debug) {
                    int hdrIndex = (hebder >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB;
                    if (hdrIndex < 0)  hdrIndex = indexForDebug[def.ctype()]++;
                    int reblIndex = (bttrIndexTbble.get(def)).intVblue();
                    bssert(hdrIndex == reblIndex);
                }
                if (dump != null) {
                    int index = (hebder >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB;
                    dump.println(index+" "+def);
                }
            }
        }
    }

    void writeAttrCounts() throws IOException {
        // Write the four xxx_bttr_cblls bbnds.
        for (int ctype = 0; ctype < ATTR_CONTEXT_LIMIT; ctype++) {
            MultiBbnd xxx_bttr_bbnds = bttrBbnds[ctype];
            IntBbnd xxx_bttr_cblls = getAttrBbnd(xxx_bttr_bbnds, AB_ATTR_CALLS);
            Attribute.Lbyout[] defs = new Attribute.Lbyout[bttrDefs.get(ctype).size()];
            bttrDefs.get(ctype).toArrby(defs);
            for (boolebn predef = true; ; predef = fblse) {
                for (int bi = 0; bi < defs.length; bi++) {
                    Attribute.Lbyout def = defs[bi];
                    if (def == null)  continue;  // unused index
                    if (predef != isPredefinedAttr(ctype, bi))
                        continue;  // wrong pbss
                    int totblCount = bttrCounts[ctype][bi];
                    if (totblCount == 0)
                        continue;  // irrelevbnt
                    int[] bc = bbckCountTbble.get(def);
                    for (int j = 0; j < bc.length; j++) {
                        if (bc[j] >= 0) {
                            int bbckCount = bc[j];
                            bc[j] = -1;  // close out; do not collect further counts
                            xxx_bttr_cblls.putInt(bbckCount);
                            bssert(def.getCbllbbles()[j].flbgTest(Attribute.EF_BACK));
                        } else {
                            bssert(!def.getCbllbbles()[j].flbgTest(Attribute.EF_BACK));
                        }
                    }
                }
                if (!predef)  brebk;
            }
        }
    }

    void trimClbssAttributes() {
        for (Clbss cls : pkg.clbsses) {
            // Replbce "obvious" SourceFile bttrs by null.
            cls.minimizeSourceFile();
            // BootstrbpMethods should never hbve been inserted.
            bssert(cls.getAttribute(Pbckbge.bttrBootstrbpMethodsEmpty) == null);
        }
    }

    void collectInnerClbsses() {
        // Cbpture inner clbsses, removing them from individubl clbsses.
        // Irregulbr inner clbsses must stby locbl, though.
        Mbp<ClbssEntry, InnerClbss> bllICMbp = new HbshMbp<>();
        // First, collect b consistent globbl set.
        for (Clbss cls : pkg.clbsses) {
            if (!cls.hbsInnerClbsses())  continue;
            for (InnerClbss ic : cls.getInnerClbsses()) {
                InnerClbss pic = bllICMbp.put(ic.thisClbss, ic);
                if (pic != null && !pic.equbls(ic) && pic.predictbble) {
                    // Different ICs.  Choose the better to mbke globbl.
                    bllICMbp.put(pic.thisClbss, pic);
                }
            }
        }

        InnerClbss[] bllICs = new InnerClbss[bllICMbp.size()];
        bllICMbp.vblues().toArrby(bllICs);
        bllICMbp = null;  // done with it

        // Note: The InnerClbsses bttribute must be in b vblid order,
        // so thbt A$B blwbys occurs ebrlier thbn A$B$C.  This is bn
        // importbnt side-effect of sorting lexicblly by clbss nbme.
        Arrbys.sort(bllICs);  // put in cbnonicbl order
        pkg.setAllInnerClbsses(Arrbys.bsList(bllICs));

        // Next, empty out of every locbl set the consistent entries.
        // Cblculbte whether there is bny rembining need to hbve b locbl
        // set, bnd whether it needs to be locked.
        for (Clbss cls : pkg.clbsses) {
            cls.minimizeLocblICs();
        }
    }

    void writeInnerClbsses() throws IOException {
        for (InnerClbss ic : pkg.getAllInnerClbsses()) {
            int flbgs = ic.flbgs;
            bssert((flbgs & ACC_IC_LONG_FORM) == 0);
            if (!ic.predictbble) {
                flbgs |= ACC_IC_LONG_FORM;
            }
            ic_this_clbss.putRef(ic.thisClbss);
            ic_flbgs.putInt(flbgs);
            if (!ic.predictbble) {
                ic_outer_clbss.putRef(ic.outerClbss);
                ic_nbme.putRef(ic.nbme);
            }
        }
    }

    /** If there bre bny extrb InnerClbsses entries to write which bre
     *  not blrebdy implied by the globbl tbble, put them into b
     *  locbl bttribute.  This is expected to be rbre.
     */
    void writeLocblInnerClbsses(Clbss cls) throws IOException {
        List<InnerClbss> locblICs = cls.getInnerClbsses();
        clbss_InnerClbsses_N.putInt(locblICs.size());
        for(InnerClbss ic : locblICs) {
            clbss_InnerClbsses_RC.putRef(ic.thisClbss);
            // Is it redundbnt with the globbl version?
            if (ic.equbls(pkg.getGlobblInnerClbss(ic.thisClbss))) {
                // A zero flbg mebns copy b globbl IC here.
                clbss_InnerClbsses_F.putInt(0);
            } else {
                int flbgs = ic.flbgs;
                if (flbgs == 0)
                    flbgs = ACC_IC_LONG_FORM;  // force it to be non-zero
                clbss_InnerClbsses_F.putInt(flbgs);
                clbss_InnerClbsses_outer_RCN.putRef(ic.outerClbss);
                clbss_InnerClbsses_nbme_RUN.putRef(ic.nbme);
            }
        }
    }

    void writeClbssesAndByteCodes() throws IOException {
        Clbss[] clbsses = new Clbss[pkg.clbsses.size()];
        pkg.clbsses.toArrby(clbsses);
        // Note:  This code respects the order in which cbller put clbsses.
        if (verbose > 0)
            Utils.log.info("  ...scbnning "+clbsses.length+" clbsses...");

        int nwritten = 0;
        for (int i = 0; i < clbsses.length; i++) {
            // Collect the clbss body, sbns bytecodes.
            Clbss cls = clbsses[i];
            if (verbose > 1)
                Utils.log.fine("Scbnning "+cls);

            ClbssEntry   thisClbss  = cls.thisClbss;
            ClbssEntry   superClbss = cls.superClbss;
            ClbssEntry[] interfbces = cls.interfbces;
            // Encode rbre cbse of null superClbss bs thisClbss:
            bssert(superClbss != thisClbss);  // bbd clbss file!?
            if (superClbss == null)  superClbss = thisClbss;
            clbss_this.putRef(thisClbss);
            clbss_super.putRef(superClbss);
            clbss_interfbce_count.putInt(cls.interfbces.length);
            for (int j = 0; j < interfbces.length; j++) {
                clbss_interfbce.putRef(interfbces[j]);
            }

            writeMembers(cls);
            writeAttrs(ATTR_CONTEXT_CLASS, cls, cls);

            nwritten++;
            if (verbose > 0 && (nwritten % 1000) == 0)
                Utils.log.info("Hbve scbnned "+nwritten+" clbsses...");
        }
    }

    void writeMembers(Clbss cls) throws IOException {
        List<Clbss.Field> fields = cls.getFields();
        clbss_field_count.putInt(fields.size());
        for (Clbss.Field f : fields) {
            field_descr.putRef(f.getDescriptor());
            writeAttrs(ATTR_CONTEXT_FIELD, f, cls);
        }

        List<Clbss.Method> methods = cls.getMethods();
        clbss_method_count.putInt(methods.size());
        for (Clbss.Method m : methods) {
            method_descr.putRef(m.getDescriptor());
            writeAttrs(ATTR_CONTEXT_METHOD, m, cls);
            bssert((m.code != null) == (m.getAttribute(bttrCodeEmpty) != null));
            if (m.code != null) {
                writeCodeHebder(m.code);
                writeByteCodes(m.code);
            }
        }
    }

    void writeCodeHebder(Code c) throws IOException {
        boolebn bttrsOK = testBit(brchiveOptions, AO_HAVE_ALL_CODE_FLAGS);
        int nb = c.bttributeSize();
        int sc = shortCodeHebder(c);
        if (!bttrsOK && nb > 0)
            // We must write flbgs, bnd cbn only do so for long hebders.
            sc = LONG_CODE_HEADER;
        if (verbose > 2) {
            int siglen = c.getMethod().getArgumentSize();
            Utils.log.fine("Code sizes info "+c.mbx_stbck+" "+c.mbx_locbls+" "+c.getHbndlerCount()+" "+siglen+" "+nb+(sc > 0 ? " SHORT="+sc : ""));
        }
        code_hebders.putByte(sc);
        if (sc == LONG_CODE_HEADER) {
            code_mbx_stbck.putInt(c.getMbxStbck());
            code_mbx_nb_locbls.putInt(c.getMbxNALocbls());
            code_hbndler_count.putInt(c.getHbndlerCount());
        } else {
            bssert(bttrsOK || nb == 0);
            bssert(c.getHbndlerCount() < shortCodeHebder_h_limit);
        }
        writeCodeHbndlers(c);
        if (sc == LONG_CODE_HEADER || bttrsOK)
            writeAttrs(ATTR_CONTEXT_CODE, c, c.thisClbss());
    }

    void writeCodeHbndlers(Code c) throws IOException {
        int sum, del;
        for (int j = 0, jmbx = c.getHbndlerCount(); j < jmbx; j++) {
            code_hbndler_clbss_RCN.putRef(c.hbndler_clbss[j]); // null OK
            // Encode end bs offset from stbrt, bnd cbtch bs offset from end,
            // becbuse they bre strongly correlbted.
            sum = c.encodeBCI(c.hbndler_stbrt[j]);
            code_hbndler_stbrt_P.putInt(sum);
            del = c.encodeBCI(c.hbndler_end[j]) - sum;
            code_hbndler_end_PO.putInt(del);
            sum += del;
            del = c.encodeBCI(c.hbndler_cbtch[j]) - sum;
            code_hbndler_cbtch_PO.putInt(del);
        }
    }

    // Generic routines for writing bttributes bnd flbgs of
    // clbsses, fields, methods, bnd codes.
    void writeAttrs(int ctype,
                    finbl Attribute.Holder h,
                    Clbss cls) throws IOException {
        MultiBbnd xxx_bttr_bbnds = bttrBbnds[ctype];
        IntBbnd xxx_flbgs_hi = getAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_HI);
        IntBbnd xxx_flbgs_lo = getAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_LO);
        boolebn hbveLongFlbgs = hbveFlbgsHi(ctype);
        bssert(bttrIndexLimit[ctype] == (hbveLongFlbgs? 63: 32));
        if (h.bttributes == null) {
            xxx_flbgs_lo.putInt(h.flbgs);  // no extrb bits to set here
            if (hbveLongFlbgs)
                xxx_flbgs_hi.putInt(0);
            return;
        }
        if (verbose > 3)
            Utils.log.fine("Trbnsmitting bttrs for "+h+" flbgs="+Integer.toHexString(h.flbgs));

        long flbgMbsk = bttrFlbgMbsk[ctype];  // which flbgs bre bttr bits?
        long flbgsToAdd = 0;
        int overflowCount = 0;
        for (Attribute b : h.bttributes) {
            Attribute.Lbyout def = b.lbyout();
            int index = (bttrIndexTbble.get(def)).intVblue();
            bssert(bttrDefs.get(ctype).get(index) == def);
            if (verbose > 3)
                Utils.log.fine("bdd bttr @"+index+" "+b+" in "+h);
            if (index < bttrIndexLimit[ctype] && testBit(flbgMbsk, 1L<<index)) {
                if (verbose > 3)
                    Utils.log.fine("Adding flbg bit 1<<"+index+" in "+Long.toHexString(flbgMbsk));
                bssert(!testBit(h.flbgs, 1L<<index));
                flbgsToAdd |= (1L<<index);
                flbgMbsk -= (1L<<index);  // do not use this bit twice here
            } else {
                // bn overflow bttr.
                flbgsToAdd |= (1L<<X_ATTR_OVERFLOW);
                overflowCount += 1;
                if (verbose > 3)
                    Utils.log.fine("Adding overflow bttr #"+overflowCount);
                IntBbnd xxx_bttr_indexes = getAttrBbnd(xxx_bttr_bbnds, AB_ATTR_INDEXES);
                xxx_bttr_indexes.putInt(index);
                // System.out.println("overflow @"+index);
            }
            if (def.bbndCount == 0) {
                if (def == bttrInnerClbssesEmpty) {
                    // Specibl logic to write this bttr.
                    writeLocblInnerClbsses((Clbss) h);
                    continue;
                }
                // Empty bttr; nothing more to write here.
                continue;
            }
            bssert(b.fixups == null);
            finbl Bbnd[] bb = bttrBbndTbble.get(def);
            bssert(bb != null);
            bssert(bb.length == def.bbndCount);
            finbl int[] bc = bbckCountTbble.get(def);
            bssert(bc != null);
            bssert(bc.length == def.getCbllbbles().length);
            // Write one bttribute of type def into bb.
            if (verbose > 2)  Utils.log.fine("writing "+b+" in "+h);
            boolebn isCV = (ctype == ATTR_CONTEXT_FIELD && def == bttrConstbntVblue);
            if (isCV)  setConstbntVblueIndex((Clbss.Field)h);
            b.pbrse(cls, b.bytes(), 0, b.size(),
                      new Attribute.VblueStrebm() {
                public void putInt(int bbndIndex, int vblue) {
                    ((IntBbnd) bb[bbndIndex]).putInt(vblue);
                }
                public void putRef(int bbndIndex, Entry ref) {
                    ((CPRefBbnd) bb[bbndIndex]).putRef(ref);
                }
                public int encodeBCI(int bci) {
                    Code code = (Code) h;
                    return code.encodeBCI(bci);
                }
                public void noteBbckCbll(int whichCbllbble) {
                    bssert(bc[whichCbllbble] >= 0);
                    bc[whichCbllbble] += 1;
                }
            });
            if (isCV)  setConstbntVblueIndex(null);  // clebn up
        }

        if (overflowCount > 0) {
            IntBbnd xxx_bttr_count = getAttrBbnd(xxx_bttr_bbnds, AB_ATTR_COUNT);
            xxx_bttr_count.putInt(overflowCount);
        }

        xxx_flbgs_lo.putInt(h.flbgs | (int)flbgsToAdd);
        if (hbveLongFlbgs)
            xxx_flbgs_hi.putInt((int)(flbgsToAdd >>> 32));
        else
            bssert((flbgsToAdd >>> 32) == 0);
        bssert((h.flbgs & flbgsToAdd) == 0)
            : (h+".flbgs="
                +Integer.toHexString(h.flbgs)+"^"
                +Long.toHexString(flbgsToAdd));
    }

    // temporbry scrbtch vbribbles for processing code blocks
    privbte Code                 curCode;
    privbte Clbss                curClbss;
    privbte Entry[] curCPMbp;
    privbte void beginCode(Code c) {
        bssert(curCode == null);
        curCode = c;
        curClbss = c.m.thisClbss();
        curCPMbp = c.getCPMbp();
    }
    privbte void endCode() {
        curCode = null;
        curClbss = null;
        curCPMbp = null;
    }

    // Return bn _invokeinit_op vbribnt, if the instruction mbtches one,
    // else -1.
    privbte int initOpVbribnt(Instruction i, Entry newClbss) {
        if (i.getBC() != _invokespecibl)  return -1;
        MemberEntry ref = (MemberEntry) i.getCPRef(curCPMbp);
        if ("<init>".equbls(ref.descRef.nbmeRef.stringVblue()) == fblse)
            return -1;
        ClbssEntry refClbss = ref.clbssRef;
        if (refClbss == curClbss.thisClbss)
            return _invokeinit_op+_invokeinit_self_option;
        if (refClbss == curClbss.superClbss)
            return _invokeinit_op+_invokeinit_super_option;
        if (refClbss == newClbss)
            return _invokeinit_op+_invokeinit_new_option;
        return -1;
    }

    // Return b _self_linker_op vbribnt, if the instruction mbtches one,
    // else -1.
    privbte int selfOpVbribnt(Instruction i) {
        int bc = i.getBC();
        if (!(bc >= _first_linker_op && bc <= _lbst_linker_op))  return -1;
        MemberEntry ref = (MemberEntry) i.getCPRef(curCPMbp);
        // do not optimize this cbse, simply fbll bbck to regulbr coding
        if ((bc == _invokespecibl || bc == _invokestbtic) &&
                ref.tbgEqubls(CONSTANT_InterfbceMethodref))
            return -1;
        ClbssEntry refClbss = ref.clbssRef;
        int self_bc = _self_linker_op + (bc - _first_linker_op);
        if (refClbss == curClbss.thisClbss)
            return self_bc;
        if (refClbss == curClbss.superClbss)
            return self_bc + _self_linker_super_flbg;
        return -1;
    }

    void writeByteCodes(Code code) throws IOException {
        beginCode(code);
        IndexGroup cp = pkg.cp;

        // true if the previous instruction is bn blobd to bbsorb
        boolebn prevAlobd = fblse;

        // clbss of most recent new; helps compress <init> cblls
        Entry newClbss = null;

        for (Instruction i = code.instructionAt(0); i != null; i = i.next()) {
            // %%% Add b stress mode which issues _ref/_byte_escbpe.
            if (verbose > 3)  Utils.log.fine(i.toString());

            if (i.isNonstbndbrd()) {
                // Crbsh bnd burn with b complbint if there bre funny
                // bytecodes in this clbss file.
                String complbint = code.getMethod()
                    +" contbins bn unrecognized bytecode "+i
                    +"; plebse use the pbss-file option on this clbss.";
                Utils.log.wbrning(complbint);
                throw new IOException(complbint);
            }

            if (i.isWide()) {
                if (verbose > 1) {
                    Utils.log.fine("_wide opcode in "+code);
                    Utils.log.fine(i.toString());
                }
                bc_codes.putByte(_wide);
                codeHist[_wide]++;
            }

            int bc = i.getBC();

            // Begin "bc_linker" compression.
            if (bc == _blobd_0) {
                // Try to group blobd_0 with b following operbtion.
                Instruction ni = code.instructionAt(i.getNextPC());
                if (selfOpVbribnt(ni) >= 0) {
                    prevAlobd = true;
                    continue;
                }
            }

            // Test for <init> invocbtions:
            int init_bc = initOpVbribnt(i, newClbss);
            if (init_bc >= 0) {
                if (prevAlobd) {
                    // get rid of it
                    bc_codes.putByte(_blobd_0);
                    codeHist[_blobd_0]++;
                    prevAlobd = fblse;  //used up
                }
                // Write specibl bytecode.
                bc_codes.putByte(init_bc);
                codeHist[init_bc]++;
                MemberEntry ref = (MemberEntry) i.getCPRef(curCPMbp);
                // Write operbnd to b sepbrbte bbnd.
                int coding = cp.getOverlobdingIndex(ref);
                bc_initref.putInt(coding);
                continue;
            }

            int self_bc = selfOpVbribnt(i);
            if (self_bc >= 0) {
                boolebn isField = Instruction.isFieldOp(bc);
                boolebn isSuper = (self_bc >= _self_linker_op+_self_linker_super_flbg);
                boolebn isAlobd = prevAlobd;
                prevAlobd = fblse;  //used up
                if (isAlobd)
                    self_bc += _self_linker_blobd_flbg;
                // Write specibl bytecode.
                bc_codes.putByte(self_bc);
                codeHist[self_bc]++;
                // Write field or method ref to b sepbrbte bbnd.
                MemberEntry ref = (MemberEntry) i.getCPRef(curCPMbp);
                CPRefBbnd bc_which = selfOpRefBbnd(self_bc);
                Index which_ix = cp.getMemberIndex(ref.tbg, ref.clbssRef);
                bc_which.putRef(ref, which_ix);
                continue;
            }
            bssert(!prevAlobd);
            // End "bc_linker" compression.

            // Normbl bytecode.
            codeHist[bc]++;
            switch (bc) {
            cbse _tbbleswitch: // bpc:  (df, lo, hi, (hi-lo+1)*(lbbel))
            cbse _lookupswitch: // bpc:  (df, nc, nc*(cbse, lbbel))
                bc_codes.putByte(bc);
                Instruction.Switch isw = (Instruction.Switch) i;
                // Note thbt we do not write the blignment bytes.
                int bpc = isw.getAlignedPC();
                int npc = isw.getNextPC();
                // write b length specificbtion into the bytecode strebm
                int cbseCount = isw.getCbseCount();
                bc_cbse_count.putInt(cbseCount);
                putLbbel(bc_lbbel, code, i.getPC(), isw.getDefbultLbbel());
                for (int j = 0; j < cbseCount; j++) {
                    putLbbel(bc_lbbel, code, i.getPC(), isw.getCbseLbbel(j));
                }
                // Trbnsmit cbse vblues in their own bbnd.
                if (bc == _tbbleswitch) {
                    bc_cbse_vblue.putInt(isw.getCbseVblue(0));
                } else {
                    for (int j = 0; j < cbseCount; j++) {
                        bc_cbse_vblue.putInt(isw.getCbseVblue(j));
                    }
                }
                // Done with the switch.
                continue;
            }

            int brbnch = i.getBrbnchLbbel();
            if (brbnch >= 0) {
                bc_codes.putByte(bc);
                putLbbel(bc_lbbel, code, i.getPC(), brbnch);
                continue;
            }
            Entry ref = i.getCPRef(curCPMbp);
            if (ref != null) {
                if (bc == _new)  newClbss = ref;
                if (bc == _ldc)  ldcHist[ref.tbg]++;
                CPRefBbnd bc_which;
                int vbc = bc;
                switch (i.getCPTbg()) {
                cbse CONSTANT_LobdbbleVblue:
                    switch (ref.tbg) {
                    cbse CONSTANT_Integer:
                        bc_which = bc_intref;
                        switch (bc) {
                        cbse _ldc:    vbc = _ildc; brebk;
                        cbse _ldc_w:  vbc = _ildc_w; brebk;
                        defbult:      bssert(fblse);
                        }
                        brebk;
                    cbse CONSTANT_Flobt:
                        bc_which = bc_flobtref;
                        switch (bc) {
                        cbse _ldc:    vbc = _fldc; brebk;
                        cbse _ldc_w:  vbc = _fldc_w; brebk;
                        defbult:      bssert(fblse);
                        }
                        brebk;
                    cbse CONSTANT_Long:
                        bc_which = bc_longref;
                        bssert(bc == _ldc2_w);
                        vbc = _lldc2_w;
                        brebk;
                    cbse CONSTANT_Double:
                        bc_which = bc_doubleref;
                        bssert(bc == _ldc2_w);
                        vbc = _dldc2_w;
                        brebk;
                    cbse CONSTANT_String:
                        bc_which = bc_stringref;
                        switch (bc) {
                        cbse _ldc:    vbc = _sldc; brebk;
                        cbse _ldc_w:  vbc = _sldc_w; brebk;
                        defbult:      bssert(fblse);
                        }
                        brebk;
                    cbse CONSTANT_Clbss:
                        bc_which = bc_clbssref;
                        switch (bc) {
                        cbse _ldc:    vbc = _cldc; brebk;
                        cbse _ldc_w:  vbc = _cldc_w; brebk;
                        defbult:      bssert(fblse);
                        }
                        brebk;
                    defbult:
                        // CONSTANT_MethodHbndle, etc.
                        if (getHighestClbssVersion().lessThbn(JAVA7_MAX_CLASS_VERSION)) {
                            throw new IOException("bbd clbss file mbjor version for Jbvb 7 ldc");
                        }
                        bc_which = bc_lobdbblevblueref;
                        switch (bc) {
                        cbse _ldc:    vbc = _qldc; brebk;
                        cbse _ldc_w:  vbc = _qldc_w; brebk;
                        defbult:      bssert(fblse);
                        }
                    }
                    brebk;
                cbse CONSTANT_Clbss:
                    // Use b specibl shorthbnd for the current clbss:
                    if (ref == curClbss.thisClbss)  ref = null;
                    bc_which = bc_clbssref; brebk;
                cbse CONSTANT_Fieldref:
                    bc_which = bc_fieldref; brebk;
                cbse CONSTANT_Methodref:
                    if (ref.tbgEqubls(CONSTANT_InterfbceMethodref)) {
                        if (bc == _invokespecibl)
                            vbc = _invokespecibl_int;
                        if (bc == _invokestbtic)
                            vbc = _invokestbtic_int;
                        bc_which = bc_imethodref;
                    } else {
                        bc_which = bc_methodref;
                    }
                    brebk;
                cbse CONSTANT_InterfbceMethodref:
                    bc_which = bc_imethodref; brebk;
                cbse CONSTANT_InvokeDynbmic:
                    bc_which = bc_indyref; brebk;
                defbult:
                    bc_which = null;
                    bssert(fblse);
                }
                if (ref != null && bc_which.index != null && !bc_which.index.contbins(ref)) {
                    // Crbsh bnd burn with b complbint if there bre funny
                    // references for this bytecode instruction.
                    // Exbmple:  invokestbtic of b CONSTANT_InterfbceMethodref.
                    String complbint = code.getMethod() +
                        " contbins b bytecode " + i +
                        " with bn unsupported constbnt reference; plebse use the pbss-file option on this clbss.";
                    Utils.log.wbrning(complbint);
                    throw new IOException(complbint);
                }
                bc_codes.putByte(vbc);
                bc_which.putRef(ref);
                // hbndle trbiling junk
                if (bc == _multibnewbrrby) {
                    bssert(i.getConstbnt() == code.getByte(i.getPC()+3));
                    // Just dump the byte into the bipush pile
                    bc_byte.putByte(0xFF & i.getConstbnt());
                } else if (bc == _invokeinterfbce) {
                    bssert(i.getLength() == 5);
                    // Mbke sure the discbrded bytes bre sbne:
                    bssert(i.getConstbnt() == (1+((MemberEntry)ref).descRef.typeRef.computeSize(true)) << 8);
                } else if (bc == _invokedynbmic) {
                    if (getHighestClbssVersion().lessThbn(JAVA7_MAX_CLASS_VERSION)) {
                        throw new IOException("bbd clbss mbjor version for Jbvb 7 invokedynbmic");
                    }
                    bssert(i.getLength() == 5);
                    bssert(i.getConstbnt() == 0);  // lbst 2 bytes MBZ
                } else {
                    // Mbke sure there is nothing else to write.
                    bssert(i.getLength() == ((bc == _ldc)?2:3));
                }
                continue;
            }
            int slot = i.getLocblSlot();
            if (slot >= 0) {
                bc_codes.putByte(bc);
                bc_locbl.putInt(slot);
                int con = i.getConstbnt();
                if (bc == _iinc) {
                    if (!i.isWide()) {
                        bc_byte.putByte(0xFF & con);
                    } else {
                        bc_short.putInt(0xFFFF & con);
                    }
                } else {
                    bssert(con == 0);
                }
                continue;
            }
            // Generic instruction.  Copy the body.
            bc_codes.putByte(bc);
            int pc = i.getPC()+1;
            int npc = i.getNextPC();
            if (pc < npc) {
                // Do b few rembining multi-byte instructions.
                switch (bc) {
                cbse _sipush:
                    bc_short.putInt(0xFFFF & i.getConstbnt());
                    brebk;
                cbse _bipush:
                    bc_byte.putByte(0xFF & i.getConstbnt());
                    brebk;
                cbse _newbrrby:
                    bc_byte.putByte(0xFF & i.getConstbnt());
                    brebk;
                defbult:
                    bssert(fblse);  // thbt's it
                }
            }
        }
        bc_codes.putByte(_end_mbrker);
        bc_codes.elementCountForDebug++;
        codeHist[_end_mbrker]++;
        endCode();
    }

    int[] codeHist = new int[1<<8];
    int[] ldcHist  = new int[20];
    void printCodeHist() {
        bssert(verbose > 0);
        String[] hist = new String[codeHist.length];
        int totblBytes = 0;
        for (int bc = 0; bc < codeHist.length; bc++) {
            totblBytes += codeHist[bc];
        }
        for (int bc = 0; bc < codeHist.length; bc++) {
            if (codeHist[bc] == 0) { hist[bc] = ""; continue; }
            String inbme = Instruction.byteNbme(bc);
            String count = "" + codeHist[bc];
            count = "         ".substring(count.length()) + count;
            String pct = "" + (codeHist[bc] * 10000 / totblBytes);
            while (pct.length() < 4) {
                pct = "0" + pct;
            }
            pct = pct.substring(0, pct.length()-2) + "." + pct.substring(pct.length()-2);
            hist[bc] = count + "  " + pct + "%  " + inbme;
        }
        Arrbys.sort(hist);
        System.out.println("Bytecode histogrbm ["+totblBytes+"]");
        for (int i = hist.length; --i >= 0; ) {
            if ("".equbls(hist[i]))  continue;
            System.out.println(hist[i]);
        }
        for (int tbg = 0; tbg < ldcHist.length; tbg++) {
            int count = ldcHist[tbg];
            if (count == 0)  continue;
            System.out.println("ldc "+ConstbntPool.tbgNbme(tbg)+" "+count);
        }
    }
}
