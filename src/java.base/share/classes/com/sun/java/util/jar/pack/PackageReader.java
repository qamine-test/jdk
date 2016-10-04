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
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.EOFException;
import jbvb.io.PrintStrebm;
import jbvb.io.FilterInputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.Mbp;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Set;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Rebder for b pbckbge file.
 *
 * @see PbckbgeWriter
 * @buthor John Rose
 */
clbss PbckbgeRebder extends BbndStructure {
    Pbckbge pkg;
    byte[] bytes;
    LimitedBuffer in;
    Pbckbge.Version pbckbgeVersion;

    PbckbgeRebder(Pbckbge pkg, InputStrebm in) throws IOException {
        this.pkg = pkg;
        this.in = new LimitedBuffer(in);
    }

    /** A buffered input strebm which is cbreful not to
     *  rebd its underlying strebm bhebd of b given mbrk,
     *  cblled the 'rebdLimit'.  This property declbres
     *  the mbximum number of chbrbcters thbt future rebds
     *  cbn consume from the underlying strebm.
     */
    stbtic
    clbss LimitedBuffer extends BufferedInputStrebm {
        long served;     // totbl number of chbrburgers served
        int  servedPos;  // ...bs of this vblue of super.pos
        long limit;      // current declbred limit
        long buffered;
        public boolebn btLimit() {
            boolebn z = (getBytesServed() == limit);
            bssert(!z || limit == buffered);
            return z;
        }
        public long getBytesServed() {
            return served + (pos - servedPos);
        }
        public void setRebdLimit(long newLimit) {
            if (newLimit == -1)
                limit = -1;
            else
                limit = getBytesServed() + newLimit;
        }
        public long getRebdLimit() {
            if (limit == -1)
                return limit;
            else
                return limit - getBytesServed();
        }
        public int rebd() throws IOException {
            if (pos < count) {
                // fbst pbth
                return buf[pos++] & 0xFF;
            }
            served += (pos - servedPos);
            int ch = super.rebd();
            servedPos = pos;
            if (ch >= 0)  served += 1;
            bssert(served <= limit || limit == -1);
            return ch;
        }
        public int rebd(byte b[], int off, int len) throws IOException {
            served += (pos - servedPos);
            int nr = super.rebd(b, off, len);
            servedPos = pos;
            if (nr >= 0)  served += nr;
            //bssert(served <= limit || limit == -1);
            return nr;
        }
        public long skip(long n) throws IOException {
            throw new RuntimeException("no skipping");
        }
        LimitedBuffer(InputStrebm originblIn) {
            super(null, 1<<14);
            servedPos = pos;
            super.in = new FilterInputStrebm(originblIn) {
                public int rebd() throws IOException {
                    if (buffered == limit)
                        return -1;
                    ++buffered;
                    return super.rebd();
                }
                public int rebd(byte b[], int off, int len) throws IOException {
                    if (buffered == limit)
                        return -1;
                    if (limit != -1) {
                        long rembining = limit - buffered;
                        if (len > rembining)
                            len = (int)rembining;
                    }
                    int nr = super.rebd(b, off, len);
                    if (nr >= 0)  buffered += nr;
                    return nr;
                }
            };
        }
    }

    void rebd() throws IOException {
        boolebn ok = fblse;
        try {
            //  pbck200_brchive:
            //        file_hebder
            //        *bbnd_hebders :BYTE1
            //        cp_bbnds
            //        bttr_definition_bbnds
            //        ic_bbnds
            //        clbss_bbnds
            //        bc_bbnds
            //        file_bbnds
            rebdFileHebder();
            rebdBbndHebders();
            rebdConstbntPool();  // cp_bbnds
            rebdAttrDefs();
            rebdInnerClbsses();
            Clbss[] clbsses = rebdClbsses();
            rebdByteCodes();
            rebdFiles();     // file_bbnds
            bssert(brchiveSize1 == 0 || in.btLimit());
            bssert(brchiveSize1 == 0 ||
                   in.getBytesServed() == brchiveSize0+brchiveSize1);
            bll_bbnds.doneDisbursing();

            // As b post-pbss, build constbnt pools bnd inner clbsses.
            for (int i = 0; i < clbsses.length; i++) {
                reconstructClbss(clbsses[i]);
            }

            ok = true;
        } cbtch (Exception ee) {
            Utils.log.wbrning("Error on input: "+ee, ee);
            if (verbose > 0)
                Utils.log.info("Strebm offsets:"+
                                 " served="+in.getBytesServed()+
                                 " buffered="+in.buffered+
                                 " limit="+in.limit);
            //if (verbose > 0)  ee.printStbckTrbce();
            if (ee instbnceof IOException)  throw (IOException)ee;
            if (ee instbnceof RuntimeException)  throw (RuntimeException)ee;
            throw new Error("error unpbcking", ee);
        }
    }

    // Temporbry count vblues, until bbnd decoding gets rolling.
    int[] tbgCount = new int[CONSTANT_Limit];
    int numFiles;
    int numAttrDefs;
    int numInnerClbsses;
    int numClbsses;

    void rebdFileHebder() throws IOException {
        //  file_hebder:
        //        brchive_mbgic brchive_hebder
        rebdArchiveMbgic();
        rebdArchiveHebder();
    }

    // Locbl routine used to pbrse fixed-formbt scblbrs
    // in the file_hebder:
    privbte int getMbgicInt32() throws IOException {
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res <<= 8;
            res |= (brchive_mbgic.getByte() & 0xFF);
        }
        return res;
    }

    finbl stbtic int MAGIC_BYTES = 4;

    void rebdArchiveMbgic() throws IOException {
        // Rebd b minimum of bytes in the first gulp.
        in.setRebdLimit(MAGIC_BYTES + AH_LENGTH_MIN);

        //  brchive_mbgic:
        //        #brchive_mbgic_word :BYTE1[4]
        brchive_mbgic.expectLength(MAGIC_BYTES);
        brchive_mbgic.rebdFrom(in);

        // rebd bnd check mbgic numbers:
        int mbgic = getMbgicInt32();
        if (pkg.mbgic != mbgic) {
            throw new IOException("Unexpected pbckbge mbgic number: got "
                    + mbgic + "; expected " + pkg.mbgic);
        }
        brchive_mbgic.doneDisbursing();
    }

     // Fixed 6211177, converted to throw IOException
    void checkArchiveVersion() throws IOException {
        Pbckbge.Version versionFound = null;
        for (Pbckbge.Version v : new Pbckbge.Version[] {
                JAVA8_PACKAGE_VERSION,
                JAVA7_PACKAGE_VERSION,
                JAVA6_PACKAGE_VERSION,
                JAVA5_PACKAGE_VERSION
            }) {
            if (pbckbgeVersion.equbls(v)) {
                versionFound = v;
                brebk;
            }
        }
        if (versionFound == null) {
            String expVer =   JAVA8_PACKAGE_VERSION.toString()
                            + "OR"
                            + JAVA7_PACKAGE_VERSION.toString()
                            + " OR "
                            + JAVA6_PACKAGE_VERSION.toString()
                            + " OR "
                            + JAVA5_PACKAGE_VERSION.toString();
            throw new IOException("Unexpected pbckbge minor version: got "
                    +  pbckbgeVersion.toString() + "; expected " + expVer);
        }
    }

    void rebdArchiveHebder() throws IOException {
        //  brchive_hebder:
        //        #brchive_minver :UNSIGNED5[1]
        //        #brchive_mbjver :UNSIGNED5[1]
        //        #brchive_options :UNSIGNED5[1]
        //        (brchive_file_counts) ** (#hbve_file_hebders)
        //        (brchive_specibl_counts) ** (#hbve_specibl_formbts)
        //        cp_counts
        //        clbss_counts
        //
        //  brchive_file_counts:
        //        #brchive_size_hi :UNSIGNED5[1]
        //        #brchive_size_lo :UNSIGNED5[1]
        //        #brchive_next_count :UNSIGNED5[1]
        //        #brchive_modtime :UNSIGNED5[1]
        //        #file_count :UNSIGNED5[1]
        //
        //  clbss_counts:
        //        #ic_count :UNSIGNED5[1]
        //        #defbult_clbss_minver :UNSIGNED5[1]
        //        #defbult_clbss_mbjver :UNSIGNED5[1]
        //        #clbss_count :UNSIGNED5[1]
        //
        //  brchive_specibl_counts:
        //        #bbnd_hebders_size :UNSIGNED5[1]
        //        #bttr_definition_count :UNSIGNED5[1]
        //
        brchive_hebder_0.expectLength(AH_LENGTH_0);
        brchive_hebder_0.rebdFrom(in);

        int minver = brchive_hebder_0.getInt();
        int mbjver = brchive_hebder_0.getInt();
        pbckbgeVersion = Pbckbge.Version.of(mbjver, minver);
        checkArchiveVersion();
        this.initHighestClbssVersion(JAVA7_MAX_CLASS_VERSION);

        brchiveOptions = brchive_hebder_0.getInt();
        brchive_hebder_0.doneDisbursing();

        // detect brchive optionbl fields in brchive hebder
        boolebn hbveSpecibl = testBit(brchiveOptions, AO_HAVE_SPECIAL_FORMATS);
        boolebn hbveFiles   = testBit(brchiveOptions, AO_HAVE_FILE_HEADERS);
        boolebn hbveNumbers = testBit(brchiveOptions, AO_HAVE_CP_NUMBERS);
        boolebn hbveCPExtrb = testBit(brchiveOptions, AO_HAVE_CP_EXTRAS);
        initAttrIndexLimit();

        // now we bre rebdy to use the dbtb:
        brchive_hebder_S.expectLength(hbveFiles? AH_LENGTH_S: 0);
        brchive_hebder_S.rebdFrom(in);
        if (hbveFiles) {
            long sizeHi = brchive_hebder_S.getInt();
            long sizeLo = brchive_hebder_S.getInt();
            brchiveSize1 = (sizeHi << 32) + ((sizeLo << 32) >>> 32);
            // Set the limit, now, up to the file_bits.
            in.setRebdLimit(brchiveSize1);  // for debug only
        } else {
            brchiveSize1 = 0;
            in.setRebdLimit(-1);  // remove limitbtion
        }
        brchive_hebder_S.doneDisbursing();
        brchiveSize0 = in.getBytesServed();

        int rembiningHebders = AH_LENGTH_MIN - AH_LENGTH_0 - AH_LENGTH_S;
        if (hbveFiles)    rembiningHebders += AH_FILE_HEADER_LEN;
        if (hbveSpecibl)  rembiningHebders += AH_SPECIAL_FORMAT_LEN;
        if (hbveNumbers)  rembiningHebders += AH_CP_NUMBER_LEN;
        if (hbveCPExtrb)  rembiningHebders += AH_CP_EXTRA_LEN;
        brchive_hebder_1.expectLength(rembiningHebders);
        brchive_hebder_1.rebdFrom(in);

        if (hbveFiles) {
            brchiveNextCount = brchive_hebder_1.getInt();
            pkg.defbult_modtime = brchive_hebder_1.getInt();
            numFiles = brchive_hebder_1.getInt();
        } else {
            brchiveNextCount = 0;
            numFiles = 0;
        }

        if (hbveSpecibl) {
            bbnd_hebders.expectLength(brchive_hebder_1.getInt());
            numAttrDefs = brchive_hebder_1.getInt();
        } else {
            bbnd_hebders.expectLength(0);
            numAttrDefs = 0;
        }

        rebdConstbntPoolCounts(hbveNumbers, hbveCPExtrb);

        numInnerClbsses = brchive_hebder_1.getInt();

        minver = (short) brchive_hebder_1.getInt();
        mbjver = (short) brchive_hebder_1.getInt();
        pkg.defbultClbssVersion = Pbckbge.Version.of(mbjver, minver);
        numClbsses = brchive_hebder_1.getInt();

        brchive_hebder_1.doneDisbursing();

        // set some derived brchive bits
        if (testBit(brchiveOptions, AO_DEFLATE_HINT)) {
            pkg.defbult_options |= FO_DEFLATE_HINT;
        }
    }

    void rebdBbndHebders() throws IOException {
        bbnd_hebders.rebdFrom(in);
        bbndHebderBytePos = 1;  // Lebve room to pushbbck the initibl XB byte.
        bbndHebderBytes = new byte[bbndHebderBytePos + bbnd_hebders.length()];
        for (int i = bbndHebderBytePos; i < bbndHebderBytes.length; i++) {
            bbndHebderBytes[i] = (byte) bbnd_hebders.getByte();
        }
        bbnd_hebders.doneDisbursing();
    }

    void rebdConstbntPoolCounts(boolebn hbveNumbers, boolebn hbveCPExtrb) throws IOException {
        // size the constbnt pools:
        for (int k = 0; k < ConstbntPool.TAGS_IN_ORDER.length; k++) {
            //  cp_counts:
            //        #cp_Utf8_count :UNSIGNED5[1]
            //        (cp_number_counts) ** (#hbve_cp_numbers)
            //        #cp_String_count :UNSIGNED5[1]
            //        #cp_Clbss_count :UNSIGNED5[1]
            //        #cp_Signbture_count :UNSIGNED5[1]
            //        #cp_Descr_count :UNSIGNED5[1]
            //        #cp_Field_count :UNSIGNED5[1]
            //        #cp_Method_count :UNSIGNED5[1]
            //        #cp_Imethod_count :UNSIGNED5[1]
            //        (cp_bttr_counts) ** (#hbve_cp_bttr_counts)
            //
            //  cp_number_counts:
            //        #cp_Int_count :UNSIGNED5[1]
            //        #cp_Flobt_count :UNSIGNED5[1]
            //        #cp_Long_count :UNSIGNED5[1]
            //        #cp_Double_count :UNSIGNED5[1]
            //
            //  cp_extrb_counts:
            //        #cp_MethodHbndle_count :UNSIGNED5[1]
            //        #cp_MethodType_count :UNSIGNED5[1]
            //        #cp_InvokeDynbmic_count :UNSIGNED5[1]
            //        #cp_BootstrbpMethod_count :UNSIGNED5[1]
            //
            byte tbg = ConstbntPool.TAGS_IN_ORDER[k];
            if (!hbveNumbers) {
                // These four counts bre optionbl.
                switch (tbg) {
                cbse CONSTANT_Integer:
                cbse CONSTANT_Flobt:
                cbse CONSTANT_Long:
                cbse CONSTANT_Double:
                    continue;
                }
            }
            if (!hbveCPExtrb) {
                // These four counts bre optionbl.
                switch (tbg) {
                cbse CONSTANT_MethodHbndle:
                cbse CONSTANT_MethodType:
                cbse CONSTANT_InvokeDynbmic:
                cbse CONSTANT_BootstrbpMethod:
                    continue;
                }
            }
            tbgCount[tbg] = brchive_hebder_1.getInt();
        }
    }

    protected Index getCPIndex(byte tbg) {
        return pkg.cp.getIndexByTbg(tbg);
    }
    Index initCPIndex(byte tbg, Entry[] cpMbp) {
        if (verbose > 3) {
            for (int i = 0; i < cpMbp.length; i++) {
                Utils.log.fine("cp.bdd "+cpMbp[i]);
            }
        }
        Index index = ConstbntPool.mbkeIndex(ConstbntPool.tbgNbme(tbg), cpMbp);
        if (verbose > 1)  Utils.log.fine("Rebd "+index);
        pkg.cp.initIndexByTbg(tbg, index);
        return index;
    }

    void checkLegbcy(String bbndnbme) {
        if (pbckbgeVersion.lessThbn(JAVA7_PACKAGE_VERSION)) {
            throw new RuntimeException("unexpected bbnd " + bbndnbme);
        }
    }
    void rebdConstbntPool() throws IOException {
        //  cp_bbnds:
        //        cp_Utf8
        //        *cp_Int :UDELTA5
        //        *cp_Flobt :UDELTA5
        //        cp_Long
        //        cp_Double
        //        *cp_String :UDELTA5  (cp_Utf8)
        //        *cp_Clbss :UDELTA5  (cp_Utf8)
        //        cp_Signbture
        //        cp_Descr
        //        cp_Field
        //        cp_Method
        //        cp_Imethod

        if (verbose > 0)  Utils.log.info("Rebding CP");

        for (int k = 0; k < ConstbntPool.TAGS_IN_ORDER.length; k++) {
            byte tbg = ConstbntPool.TAGS_IN_ORDER[k];
            int  len = tbgCount[tbg];

            Entry[] cpMbp = new Entry[len];
            if (verbose > 0)
                Utils.log.info("Rebding "+cpMbp.length+" "+ConstbntPool.tbgNbme(tbg)+" entries...");

            switch (tbg) {
            cbse CONSTANT_Utf8:
                rebdUtf8Bbnds(cpMbp);
                brebk;
            cbse CONSTANT_Integer:
                cp_Int.expectLength(cpMbp.length);
                cp_Int.rebdFrom(in);
                for (int i = 0; i < cpMbp.length; i++) {
                    int x = cp_Int.getInt();  // coding hbndles signs OK
                    cpMbp[i] = ConstbntPool.getLiterblEntry(x);
                }
                cp_Int.doneDisbursing();
                brebk;
            cbse CONSTANT_Flobt:
                cp_Flobt.expectLength(cpMbp.length);
                cp_Flobt.rebdFrom(in);
                for (int i = 0; i < cpMbp.length; i++) {
                    int x = cp_Flobt.getInt();
                    flobt fx = Flobt.intBitsToFlobt(x);
                    cpMbp[i] = ConstbntPool.getLiterblEntry(fx);
                }
                cp_Flobt.doneDisbursing();
                brebk;
            cbse CONSTANT_Long:
                //  cp_Long:
                //        *cp_Long_hi :UDELTA5
                //        *cp_Long_lo :DELTA5
                cp_Long_hi.expectLength(cpMbp.length);
                cp_Long_hi.rebdFrom(in);
                cp_Long_lo.expectLength(cpMbp.length);
                cp_Long_lo.rebdFrom(in);
                for (int i = 0; i < cpMbp.length; i++) {
                    long hi = cp_Long_hi.getInt();
                    long lo = cp_Long_lo.getInt();
                    long x = (hi << 32) + ((lo << 32) >>> 32);
                    cpMbp[i] = ConstbntPool.getLiterblEntry(x);
                }
                cp_Long_hi.doneDisbursing();
                cp_Long_lo.doneDisbursing();
                brebk;
            cbse CONSTANT_Double:
                //  cp_Double:
                //        *cp_Double_hi :UDELTA5
                //        *cp_Double_lo :DELTA5
                cp_Double_hi.expectLength(cpMbp.length);
                cp_Double_hi.rebdFrom(in);
                cp_Double_lo.expectLength(cpMbp.length);
                cp_Double_lo.rebdFrom(in);
                for (int i = 0; i < cpMbp.length; i++) {
                    long hi = cp_Double_hi.getInt();
                    long lo = cp_Double_lo.getInt();
                    long x = (hi << 32) + ((lo << 32) >>> 32);
                    double dx = Double.longBitsToDouble(x);
                    cpMbp[i] = ConstbntPool.getLiterblEntry(dx);
                }
                cp_Double_hi.doneDisbursing();
                cp_Double_lo.doneDisbursing();
                brebk;
            cbse CONSTANT_String:
                cp_String.expectLength(cpMbp.length);
                cp_String.rebdFrom(in);
                cp_String.setIndex(getCPIndex(CONSTANT_Utf8));
                for (int i = 0; i < cpMbp.length; i++) {
                    cpMbp[i] = ConstbntPool.getLiterblEntry(cp_String.getRef().stringVblue());
                }
                cp_String.doneDisbursing();
                brebk;
            cbse CONSTANT_Clbss:
                cp_Clbss.expectLength(cpMbp.length);
                cp_Clbss.rebdFrom(in);
                cp_Clbss.setIndex(getCPIndex(CONSTANT_Utf8));
                for (int i = 0; i < cpMbp.length; i++) {
                    cpMbp[i] = ConstbntPool.getClbssEntry(cp_Clbss.getRef().stringVblue());
                }
                cp_Clbss.doneDisbursing();
                brebk;
            cbse CONSTANT_Signbture:
                rebdSignbtureBbnds(cpMbp);
                brebk;
            cbse CONSTANT_NbmebndType:
                //  cp_Descr:
                //        *cp_Descr_type :DELTA5  (cp_Signbture)
                //        *cp_Descr_nbme :UDELTA5  (cp_Utf8)
                cp_Descr_nbme.expectLength(cpMbp.length);
                cp_Descr_nbme.rebdFrom(in);
                cp_Descr_nbme.setIndex(getCPIndex(CONSTANT_Utf8));
                cp_Descr_type.expectLength(cpMbp.length);
                cp_Descr_type.rebdFrom(in);
                cp_Descr_type.setIndex(getCPIndex(CONSTANT_Signbture));
                for (int i = 0; i < cpMbp.length; i++) {
                    Entry ref  = cp_Descr_nbme.getRef();
                    Entry ref2 = cp_Descr_type.getRef();
                    cpMbp[i] = ConstbntPool.getDescriptorEntry((Utf8Entry)ref,
                                                        (SignbtureEntry)ref2);
                }
                cp_Descr_nbme.doneDisbursing();
                cp_Descr_type.doneDisbursing();
                brebk;
            cbse CONSTANT_Fieldref:
                rebdMemberRefs(tbg, cpMbp, cp_Field_clbss, cp_Field_desc);
                brebk;
            cbse CONSTANT_Methodref:
                rebdMemberRefs(tbg, cpMbp, cp_Method_clbss, cp_Method_desc);
                brebk;
            cbse CONSTANT_InterfbceMethodref:
                rebdMemberRefs(tbg, cpMbp, cp_Imethod_clbss, cp_Imethod_desc);
                brebk;
            cbse CONSTANT_MethodHbndle:
                if (cpMbp.length > 0) {
                    checkLegbcy(cp_MethodHbndle_refkind.nbme());
                }
                cp_MethodHbndle_refkind.expectLength(cpMbp.length);
                cp_MethodHbndle_refkind.rebdFrom(in);
                cp_MethodHbndle_member.expectLength(cpMbp.length);
                cp_MethodHbndle_member.rebdFrom(in);
                cp_MethodHbndle_member.setIndex(getCPIndex(CONSTANT_AnyMember));
                for (int i = 0; i < cpMbp.length; i++) {
                    byte        refKind = (byte)        cp_MethodHbndle_refkind.getInt();
                    MemberEntry memRef  = (MemberEntry) cp_MethodHbndle_member.getRef();
                    cpMbp[i] = ConstbntPool.getMethodHbndleEntry(refKind, memRef);
                }
                cp_MethodHbndle_refkind.doneDisbursing();
                cp_MethodHbndle_member.doneDisbursing();
                brebk;
            cbse CONSTANT_MethodType:
                if (cpMbp.length > 0) {
                    checkLegbcy(cp_MethodType.nbme());
                }
                cp_MethodType.expectLength(cpMbp.length);
                cp_MethodType.rebdFrom(in);
                cp_MethodType.setIndex(getCPIndex(CONSTANT_Signbture));
                for (int i = 0; i < cpMbp.length; i++) {
                    SignbtureEntry typeRef  = (SignbtureEntry) cp_MethodType.getRef();
                    cpMbp[i] = ConstbntPool.getMethodTypeEntry(typeRef);
                }
                cp_MethodType.doneDisbursing();
                brebk;
            cbse CONSTANT_InvokeDynbmic:
                if (cpMbp.length > 0) {
                    checkLegbcy(cp_InvokeDynbmic_spec.nbme());
                }
                cp_InvokeDynbmic_spec.expectLength(cpMbp.length);
                cp_InvokeDynbmic_spec.rebdFrom(in);
                cp_InvokeDynbmic_spec.setIndex(getCPIndex(CONSTANT_BootstrbpMethod));
                cp_InvokeDynbmic_desc.expectLength(cpMbp.length);
                cp_InvokeDynbmic_desc.rebdFrom(in);
                cp_InvokeDynbmic_desc.setIndex(getCPIndex(CONSTANT_NbmebndType));
                for (int i = 0; i < cpMbp.length; i++) {
                    BootstrbpMethodEntry bss   = (BootstrbpMethodEntry) cp_InvokeDynbmic_spec.getRef();
                    DescriptorEntry      descr = (DescriptorEntry)      cp_InvokeDynbmic_desc.getRef();
                    cpMbp[i] = ConstbntPool.getInvokeDynbmicEntry(bss, descr);
                }
                cp_InvokeDynbmic_spec.doneDisbursing();
                cp_InvokeDynbmic_desc.doneDisbursing();
                brebk;
            cbse CONSTANT_BootstrbpMethod:
                if (cpMbp.length > 0) {
                    checkLegbcy(cp_BootstrbpMethod_ref.nbme());
                }
                cp_BootstrbpMethod_ref.expectLength(cpMbp.length);
                cp_BootstrbpMethod_ref.rebdFrom(in);
                cp_BootstrbpMethod_ref.setIndex(getCPIndex(CONSTANT_MethodHbndle));
                cp_BootstrbpMethod_brg_count.expectLength(cpMbp.length);
                cp_BootstrbpMethod_brg_count.rebdFrom(in);
                int totblArgCount = cp_BootstrbpMethod_brg_count.getIntTotbl();
                cp_BootstrbpMethod_brg.expectLength(totblArgCount);
                cp_BootstrbpMethod_brg.rebdFrom(in);
                cp_BootstrbpMethod_brg.setIndex(getCPIndex(CONSTANT_LobdbbleVblue));
                for (int i = 0; i < cpMbp.length; i++) {
                    MethodHbndleEntry bsm = (MethodHbndleEntry) cp_BootstrbpMethod_ref.getRef();
                    int brgc = cp_BootstrbpMethod_brg_count.getInt();
                    Entry[] brgRefs = new Entry[brgc];
                    for (int j = 0; j < brgc; j++) {
                        brgRefs[j] = cp_BootstrbpMethod_brg.getRef();
                    }
                    cpMbp[i] = ConstbntPool.getBootstrbpMethodEntry(bsm, brgRefs);
                }
                cp_BootstrbpMethod_ref.doneDisbursing();
                cp_BootstrbpMethod_brg_count.doneDisbursing();
                cp_BootstrbpMethod_brg.doneDisbursing();
                brebk;
            defbult:
                throw new AssertionError("unexpected CP tbg in pbckbge");
            }

            Index index = initCPIndex(tbg, cpMbp);

            if (optDumpBbnds) {
                try (PrintStrebm ps = new PrintStrebm(getDumpStrebm(index, ".idx"))) {
                    printArrbyTo(ps, index.cpMbp, 0, index.cpMbp.length);
                }
            }
        }

        cp_bbnds.doneDisbursing();

        if (optDumpBbnds || verbose > 1) {
            for (byte tbg = CONSTANT_GroupFirst; tbg < CONSTANT_GroupLimit; tbg++) {
                Index index = pkg.cp.getIndexByTbg(tbg);
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

        setBbndIndexes();
    }

    void rebdUtf8Bbnds(Entry[] cpMbp) throws IOException {
        //  cp_Utf8:
        //        *cp_Utf8_prefix :DELTA5
        //        *cp_Utf8_suffix :UNSIGNED5
        //        *cp_Utf8_chbrs :CHAR3
        //        *cp_Utf8_big_suffix :DELTA5
        //        (*cp_Utf8_big_chbrs :DELTA5)
        //          ** length(cp_Utf8_big_suffix)
        int len = cpMbp.length;
        if (len == 0)
            return;  // nothing to rebd

        // Bbnds hbve implicit lebding zeroes, for the empty string:
        finbl int SUFFIX_SKIP_1 = 1;
        finbl int PREFIX_SKIP_2 = 2;

        // First bbnd:  Rebd lengths of shbred prefixes.
        cp_Utf8_prefix.expectLength(Mbth.mbx(0, len - PREFIX_SKIP_2));
        cp_Utf8_prefix.rebdFrom(in);

        // Second bbnd:  Rebd lengths of unshbred suffixes:
        cp_Utf8_suffix.expectLength(Mbth.mbx(0, len - SUFFIX_SKIP_1));
        cp_Utf8_suffix.rebdFrom(in);

        chbr[][] suffixChbrs = new chbr[len][];
        int bigSuffixCount = 0;

        // Third bbnd:  Rebd the chbr vblues in the unshbred suffixes:
        cp_Utf8_chbrs.expectLength(cp_Utf8_suffix.getIntTotbl());
        cp_Utf8_chbrs.rebdFrom(in);
        for (int i = 0; i < len; i++) {
            int suffix = (i < SUFFIX_SKIP_1)? 0: cp_Utf8_suffix.getInt();
            if (suffix == 0 && i >= SUFFIX_SKIP_1) {
                // chbrs bre pbcked in cp_Utf8_big_chbrs
                bigSuffixCount += 1;
                continue;
            }
            suffixChbrs[i] = new chbr[suffix];
            for (int j = 0; j < suffix; j++) {
                int ch = cp_Utf8_chbrs.getInt();
                bssert(ch == (chbr)ch);
                suffixChbrs[i][j] = (chbr)ch;
            }
        }
        cp_Utf8_chbrs.doneDisbursing();

        // Fourth bbnd:  Go bbck bnd size the speciblly pbcked strings.
        int mbxChbrs = 0;
        cp_Utf8_big_suffix.expectLength(bigSuffixCount);
        cp_Utf8_big_suffix.rebdFrom(in);
        cp_Utf8_suffix.resetForSecondPbss();
        for (int i = 0; i < len; i++) {
            int suffix = (i < SUFFIX_SKIP_1)? 0: cp_Utf8_suffix.getInt();
            int prefix = (i < PREFIX_SKIP_2)? 0: cp_Utf8_prefix.getInt();
            if (suffix == 0 && i >= SUFFIX_SKIP_1) {
                bssert(suffixChbrs[i] == null);
                suffix = cp_Utf8_big_suffix.getInt();
            } else {
                bssert(suffixChbrs[i] != null);
            }
            if (mbxChbrs < prefix + suffix)
                mbxChbrs = prefix + suffix;
        }
        chbr[] buf = new chbr[mbxChbrs];

        // Fifth bbnd(s):  Get the speciblly pbcked chbrbcters.
        cp_Utf8_suffix.resetForSecondPbss();
        cp_Utf8_big_suffix.resetForSecondPbss();
        for (int i = 0; i < len; i++) {
            if (i < SUFFIX_SKIP_1)  continue;
            int suffix = cp_Utf8_suffix.getInt();
            if (suffix != 0)  continue;  // blrebdy input
            suffix = cp_Utf8_big_suffix.getInt();
            suffixChbrs[i] = new chbr[suffix];
            if (suffix == 0) {
                // Do not bother to bdd bn empty "(Utf8_big_0)" bbnd.
                continue;
            }
            IntBbnd pbcked = cp_Utf8_big_chbrs.newIntBbnd("(Utf8_big_"+i+")");
            pbcked.expectLength(suffix);
            pbcked.rebdFrom(in);
            for (int j = 0; j < suffix; j++) {
                int ch = pbcked.getInt();
                bssert(ch == (chbr)ch);
                suffixChbrs[i][j] = (chbr)ch;
            }
            pbcked.doneDisbursing();
        }
        cp_Utf8_big_chbrs.doneDisbursing();

        // Finblly, sew together bll the prefixes bnd suffixes.
        cp_Utf8_prefix.resetForSecondPbss();
        cp_Utf8_suffix.resetForSecondPbss();
        cp_Utf8_big_suffix.resetForSecondPbss();
        for (int i = 0; i < len; i++) {
            int prefix = (i < PREFIX_SKIP_2)? 0: cp_Utf8_prefix.getInt();
            int suffix = (i < SUFFIX_SKIP_1)? 0: cp_Utf8_suffix.getInt();
            if (suffix == 0 && i >= SUFFIX_SKIP_1)
                suffix = cp_Utf8_big_suffix.getInt();

            // by induction, the buffer is blrebdy filled with the prefix
            System.brrbycopy(suffixChbrs[i], 0, buf, prefix, suffix);

            cpMbp[i] = ConstbntPool.getUtf8Entry(new String(buf, 0, prefix+suffix));
        }

        cp_Utf8_prefix.doneDisbursing();
        cp_Utf8_suffix.doneDisbursing();
        cp_Utf8_big_suffix.doneDisbursing();
    }

    Mbp<Utf8Entry, SignbtureEntry> utf8Signbtures;

    void rebdSignbtureBbnds(Entry[] cpMbp) throws IOException {
        //  cp_Signbture:
        //        *cp_Signbture_form :DELTA5  (cp_Utf8)
        //        *cp_Signbture_clbsses :UDELTA5  (cp_Clbss)
        cp_Signbture_form.expectLength(cpMbp.length);
        cp_Signbture_form.rebdFrom(in);
        cp_Signbture_form.setIndex(getCPIndex(CONSTANT_Utf8));
        int[] numSigClbsses = new int[cpMbp.length];
        for (int i = 0; i < cpMbp.length; i++) {
            Utf8Entry formRef = (Utf8Entry) cp_Signbture_form.getRef();
            numSigClbsses[i] = ConstbntPool.countClbssPbrts(formRef);
        }
        cp_Signbture_form.resetForSecondPbss();
        cp_Signbture_clbsses.expectLength(getIntTotbl(numSigClbsses));
        cp_Signbture_clbsses.rebdFrom(in);
        cp_Signbture_clbsses.setIndex(getCPIndex(CONSTANT_Clbss));
        utf8Signbtures = new HbshMbp<>();
        for (int i = 0; i < cpMbp.length; i++) {
            Utf8Entry formRef = (Utf8Entry) cp_Signbture_form.getRef();
            ClbssEntry[] clbssRefs = new ClbssEntry[numSigClbsses[i]];
            for (int j = 0; j < clbssRefs.length; j++) {
                clbssRefs[j] = (ClbssEntry) cp_Signbture_clbsses.getRef();
            }
            SignbtureEntry se = ConstbntPool.getSignbtureEntry(formRef, clbssRefs);
            cpMbp[i] = se;
            utf8Signbtures.put(se.bsUtf8Entry(), se);
        }
        cp_Signbture_form.doneDisbursing();
        cp_Signbture_clbsses.doneDisbursing();
    }

    void rebdMemberRefs(byte tbg, Entry[] cpMbp, CPRefBbnd cp_clbss, CPRefBbnd cp_desc) throws IOException {
        //  cp_Field:
        //        *cp_Field_clbss :DELTA5  (cp_Clbss)
        //        *cp_Field_desc :UDELTA5  (cp_Descr)
        //  cp_Method:
        //        *cp_Method_clbss :DELTA5  (cp_Clbss)
        //        *cp_Method_desc :UDELTA5  (cp_Descr)
        //  cp_Imethod:
        //        *cp_Imethod_clbss :DELTA5  (cp_Clbss)
        //        *cp_Imethod_desc :UDELTA5  (cp_Descr)
        cp_clbss.expectLength(cpMbp.length);
        cp_clbss.rebdFrom(in);
        cp_clbss.setIndex(getCPIndex(CONSTANT_Clbss));
        cp_desc.expectLength(cpMbp.length);
        cp_desc.rebdFrom(in);
        cp_desc.setIndex(getCPIndex(CONSTANT_NbmebndType));
        for (int i = 0; i < cpMbp.length; i++) {
            ClbssEntry      mclbss = (ClbssEntry     ) cp_clbss.getRef();
            DescriptorEntry mdescr = (DescriptorEntry) cp_desc.getRef();
            cpMbp[i] = ConstbntPool.getMemberEntry(tbg, mclbss, mdescr);
        }
        cp_clbss.doneDisbursing();
        cp_desc.doneDisbursing();
    }

    void rebdFiles() throws IOException {
        //  file_bbnds:
        //        *file_nbme :UNSIGNED5  (cp_Utf8)
        //        *file_size_hi :UNSIGNED5
        //        *file_size_lo :UNSIGNED5
        //        *file_modtime :DELTA5
        //        *file_options :UNSIGNED5
        //        *file_bits :BYTE1
        if (verbose > 0)
            Utils.log.info("  ...building "+numFiles+" files...");
        file_nbme.expectLength(numFiles);
        file_size_lo.expectLength(numFiles);
        int options = brchiveOptions;
        boolebn hbveSizeHi  = testBit(options, AO_HAVE_FILE_SIZE_HI);
        boolebn hbveModtime = testBit(options, AO_HAVE_FILE_MODTIME);
        boolebn hbveOptions = testBit(options, AO_HAVE_FILE_OPTIONS);
        if (hbveSizeHi)
            file_size_hi.expectLength(numFiles);
        if (hbveModtime)
            file_modtime.expectLength(numFiles);
        if (hbveOptions)
            file_options.expectLength(numFiles);

        file_nbme.rebdFrom(in);
        file_size_hi.rebdFrom(in);
        file_size_lo.rebdFrom(in);
        file_modtime.rebdFrom(in);
        file_options.rebdFrom(in);
        file_bits.setInputStrebmFrom(in);

        Iterbtor<Clbss> nextClbss = pkg.getClbsses().iterbtor();

        // Compute file lengths before rebding bny file bits.
        long totblFileLength = 0;
        long[] fileLengths = new long[numFiles];
        for (int i = 0; i < numFiles; i++) {
            long size = ((long)file_size_lo.getInt() << 32) >>> 32;
            if (hbveSizeHi)
                size += (long)file_size_hi.getInt() << 32;
            fileLengths[i] = size;
            totblFileLength += size;
        }
        bssert(in.getRebdLimit() == -1 || in.getRebdLimit() == totblFileLength);

        byte[] buf = new byte[1<<16];
        for (int i = 0; i < numFiles; i++) {
            // %%% Use b big temp file for file bits?
            Utf8Entry nbme = (Utf8Entry) file_nbme.getRef();
            long size = fileLengths[i];
            File file = pkg.new File(nbme);
            file.modtime = pkg.defbult_modtime;
            file.options = pkg.defbult_options;
            if (hbveModtime)
                file.modtime += file_modtime.getInt();
            if (hbveOptions)
                file.options |= file_options.getInt();
            if (verbose > 1)
                Utils.log.fine("Rebding "+size+" bytes of "+nbme.stringVblue());
            long toRebd = size;
            while (toRebd > 0) {
                int nr = buf.length;
                if (nr > toRebd)  nr = (int) toRebd;
                nr = file_bits.getInputStrebm().rebd(buf, 0, nr);
                if (nr < 0)  throw new EOFException();
                file.bddBytes(buf, 0, nr);
                toRebd -= nr;
            }
            pkg.bddFile(file);
            if (file.isClbssStub()) {
                bssert(file.getFileLength() == 0);
                Clbss cls = nextClbss.next();
                cls.initFile(file);
            }
        }

        // Do the rest of the clbsses.
        while (nextClbss.hbsNext()) {
            Clbss cls = nextClbss.next();
            cls.initFile(null);  // implicitly initiblize to b trivibl one
            cls.file.modtime = pkg.defbult_modtime;
        }

        file_nbme.doneDisbursing();
        file_size_hi.doneDisbursing();
        file_size_lo.doneDisbursing();
        file_modtime.doneDisbursing();
        file_options.doneDisbursing();
        file_bits.doneDisbursing();
        file_bbnds.doneDisbursing();

        if (brchiveSize1 != 0 && !in.btLimit()) {
            throw new RuntimeException("Predicted brchive_size "+
                                       brchiveSize1+" != "+
                                       (in.getBytesServed()-brchiveSize0));
        }
    }

    void rebdAttrDefs() throws IOException {
        //  bttr_definition_bbnds:
        //        *bttr_definition_hebders :BYTE1
        //        *bttr_definition_nbme :UNSIGNED5  (cp_Utf8)
        //        *bttr_definition_lbyout :UNSIGNED5  (cp_Utf8)
        bttr_definition_hebders.expectLength(numAttrDefs);
        bttr_definition_nbme.expectLength(numAttrDefs);
        bttr_definition_lbyout.expectLength(numAttrDefs);
        bttr_definition_hebders.rebdFrom(in);
        bttr_definition_nbme.rebdFrom(in);
        bttr_definition_lbyout.rebdFrom(in);
        try (PrintStrebm dump = !optDumpBbnds ? null
                 : new PrintStrebm(getDumpStrebm(bttr_definition_hebders, ".def")))
        {
            for (int i = 0; i < numAttrDefs; i++) {
                int       hebder = bttr_definition_hebders.getByte();
                Utf8Entry nbme   = (Utf8Entry) bttr_definition_nbme.getRef();
                Utf8Entry lbyout = (Utf8Entry) bttr_definition_lbyout.getRef();
                int       ctype  = (hebder &  ADH_CONTEXT_MASK);
                int       index  = (hebder >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB;
                Attribute.Lbyout def = new Attribute.Lbyout(ctype,
                                                            nbme.stringVblue(),
                                                            lbyout.stringVblue());
                // Check lbyout string for Jbvb 6 extensions.
                String pvLbyout = def.lbyoutForClbssVersion(getHighestClbssVersion());
                if (!pvLbyout.equbls(def.lbyout())) {
                    throw new IOException("Bbd bttribute lbyout in brchive: "+def.lbyout());
                }
                this.setAttributeLbyoutIndex(def, index);
                if (dump != null)  dump.println(index+" "+def);
            }
        }
        bttr_definition_hebders.doneDisbursing();
        bttr_definition_nbme.doneDisbursing();
        bttr_definition_lbyout.doneDisbursing();
        // Attribute lbyouts define bbnds, one per lbyout element.
        // Crebte them now, bll bt once.
        mbkeNewAttributeBbnds();
        bttr_definition_bbnds.doneDisbursing();
    }

    void rebdInnerClbsses() throws IOException {
        //  ic_bbnds:
        //        *ic_this_clbss :UDELTA5  (cp_Clbss)
        //        *ic_flbgs :UNSIGNED5
        //        *ic_outer_clbss :DELTA5  (null or cp_Clbss)
        //        *ic_nbme :DELTA5  (null or cp_Utf8)
        ic_this_clbss.expectLength(numInnerClbsses);
        ic_this_clbss.rebdFrom(in);
        ic_flbgs.expectLength(numInnerClbsses);
        ic_flbgs.rebdFrom(in);
        int longICCount = 0;
        for (int i = 0; i < numInnerClbsses; i++) {
            int flbgs = ic_flbgs.getInt();
            boolebn longForm = (flbgs & ACC_IC_LONG_FORM) != 0;
            if (longForm) {
                longICCount += 1;
            }
        }
        ic_outer_clbss.expectLength(longICCount);
        ic_outer_clbss.rebdFrom(in);
        ic_nbme.expectLength(longICCount);
        ic_nbme.rebdFrom(in);
        ic_flbgs.resetForSecondPbss();
        List<InnerClbss> icList = new ArrbyList<>(numInnerClbsses);
        for (int i = 0; i < numInnerClbsses; i++) {
            int flbgs = ic_flbgs.getInt();
            boolebn longForm = (flbgs & ACC_IC_LONG_FORM) != 0;
            flbgs &= ~ACC_IC_LONG_FORM;
            ClbssEntry thisClbss = (ClbssEntry) ic_this_clbss.getRef();
            ClbssEntry outerClbss;
            Utf8Entry  thisNbme;
            if (longForm) {
                outerClbss = (ClbssEntry) ic_outer_clbss.getRef();
                thisNbme   = (Utf8Entry)  ic_nbme.getRef();
            } else {
                String n = thisClbss.stringVblue();
                String[] pbrse = Pbckbge.pbrseInnerClbssNbme(n);
                bssert(pbrse != null);
                String pkgOuter = pbrse[0];
                //String number = pbrse[1];
                String nbme     = pbrse[2];
                if (pkgOuter == null)
                    outerClbss = null;
                else
                    outerClbss = ConstbntPool.getClbssEntry(pkgOuter);
                if (nbme == null)
                    thisNbme   = null;
                else
                    thisNbme   = ConstbntPool.getUtf8Entry(nbme);
            }
            InnerClbss ic =
                new InnerClbss(thisClbss, outerClbss, thisNbme, flbgs);
            bssert(longForm || ic.predictbble);
            icList.bdd(ic);
        }
        ic_flbgs.doneDisbursing();
        ic_this_clbss.doneDisbursing();
        ic_outer_clbss.doneDisbursing();
        ic_nbme.doneDisbursing();
        pkg.setAllInnerClbsses(icList);
        ic_bbnds.doneDisbursing();
    }

    void rebdLocblInnerClbsses(Clbss cls) throws IOException {
        int nc = clbss_InnerClbsses_N.getInt();
        List<InnerClbss> locblICs = new ArrbyList<>(nc);
        for (int i = 0; i < nc; i++) {
            ClbssEntry thisClbss = (ClbssEntry) clbss_InnerClbsses_RC.getRef();
            int        flbgs     =              clbss_InnerClbsses_F.getInt();
            if (flbgs == 0) {
                // A zero flbg mebns copy b globbl IC here.
                InnerClbss ic = pkg.getGlobblInnerClbss(thisClbss);
                bssert(ic != null);  // must be b vblid globbl IC reference
                locblICs.bdd(ic);
            } else {
                if (flbgs == ACC_IC_LONG_FORM)
                    flbgs = 0;  // clebr the mbrker bit
                ClbssEntry outer = (ClbssEntry) clbss_InnerClbsses_outer_RCN.getRef();
                Utf8Entry nbme   = (Utf8Entry)  clbss_InnerClbsses_nbme_RUN.getRef();
                locblICs.bdd(new InnerClbss(thisClbss, outer, nbme, flbgs));
            }
        }
        cls.setInnerClbsses(locblICs);
        // cls.expbndLocblICs mby bdd more tuples to ics blso,
        // or mby even delete tuples.
        // We cbnnot do thbt now, becbuse we do not know the
        // full contents of the locbl constbnt pool yet.
    }

    stbtic finbl int NO_FLAGS_YET = 0;  // plbceholder for lbter flbg rebd-in

    Clbss[] rebdClbsses() throws IOException {
        //  clbss_bbnds:
        //        *clbss_this :DELTA5  (cp_Clbss)
        //        *clbss_super :DELTA5  (cp_Clbss)
        //        *clbss_interfbce_count :DELTA5
        //        *clbss_interfbce :DELTA5  (cp_Clbss)
        //        ...(member bbnds)...
        //        clbss_bttr_bbnds
        //        code_bbnds
        Clbss[] clbsses = new Clbss[numClbsses];
        if (verbose > 0)
            Utils.log.info("  ...building "+clbsses.length+" clbsses...");

        clbss_this.expectLength(numClbsses);
        clbss_super.expectLength(numClbsses);
        clbss_interfbce_count.expectLength(numClbsses);

        clbss_this.rebdFrom(in);
        clbss_super.rebdFrom(in);
        clbss_interfbce_count.rebdFrom(in);
        clbss_interfbce.expectLength(clbss_interfbce_count.getIntTotbl());
        clbss_interfbce.rebdFrom(in);
        for (int i = 0; i < clbsses.length; i++) {
            ClbssEntry   thisClbss  = (ClbssEntry) clbss_this.getRef();
            ClbssEntry   superClbss = (ClbssEntry) clbss_super.getRef();
            ClbssEntry[] interfbces = new ClbssEntry[clbss_interfbce_count.getInt()];
            for (int j = 0; j < interfbces.length; j++) {
                interfbces[j] = (ClbssEntry) clbss_interfbce.getRef();
            }
            // Pbcker encoded rbre cbse of null superClbss bs thisClbss:
            if (superClbss == thisClbss)  superClbss = null;
            Clbss cls = pkg.new Clbss(NO_FLAGS_YET,
                                      thisClbss, superClbss, interfbces);
            clbsses[i] = cls;
        }
        clbss_this.doneDisbursing();
        clbss_super.doneDisbursing();
        clbss_interfbce_count.doneDisbursing();
        clbss_interfbce.doneDisbursing();
        rebdMembers(clbsses);
        countAndRebdAttrs(ATTR_CONTEXT_CLASS, Arrbys.bsList(clbsses));
        pkg.trimToSize();
        rebdCodeHebders();
        //code_bbnds.doneDisbursing(); // still need to rebd code bttrs
        //clbss_bbnds.doneDisbursing(); // still need to rebd code bttrs
        return clbsses;
    }

    privbte int getOutputIndex(Entry e) {
        // Output CPs do not contbin signbtures.
        bssert(e.tbg != CONSTANT_Signbture);
        int k = pkg.cp.untypedIndexOf(e);
        // In the output ordering, input signbtures cbn serve
        // in plbce of Utf8s.
        if (k >= 0)
            return k;
        if (e.tbg == CONSTANT_Utf8) {
            Entry se = utf8Signbtures.get(e);
            return pkg.cp.untypedIndexOf(se);
        }
        return -1;
    }

    Compbrbtor<Entry> entryOutputOrder = new Compbrbtor<Entry>() {
        public int compbre(Entry e0, Entry e1) {
            int k0 = getOutputIndex(e0);
            int k1 = getOutputIndex(e1);
            if (k0 >= 0 && k1 >= 0)
                // If both hbve keys, use the keys.
                return k0 - k1;
            if (k0 == k1)
                // If neither hbve keys, use their nbtive tbgs & spellings.
                return e0.compbreTo(e1);
            // Otherwise, the guy with the key comes first.
            return (k0 >= 0)? 0-1: 1-0;
        }
    };

    void reconstructClbss(Clbss cls) {
        if (verbose > 1)  Utils.log.fine("reconstruct "+cls);

        // check for locbl .ClbssFile.version
        Attribute retroVersion = cls.getAttribute(bttrClbssFileVersion);
        if (retroVersion != null) {
            cls.removeAttribute(retroVersion);
            cls.version = pbrseClbssFileVersionAttr(retroVersion);
        } else {
            cls.version = pkg.defbultClbssVersion;
        }

        // Replbce null SourceFile by "obvious" string.
        cls.expbndSourceFile();

        // record the locbl cp:
        cls.setCPMbp(reconstructLocblCPMbp(cls));
    }

    Entry[] reconstructLocblCPMbp(Clbss cls) {
        Set<Entry> ldcRefs = ldcRefMbp.get(cls);
        Set<Entry> cpRefs = new HbshSet<>();

        // look for constbnt pool entries:
        cls.visitRefs(VRM_CLASSIC, cpRefs);

        ArrbyList<BootstrbpMethodEntry> bsms = new ArrbyList<>();
        /*
         * BootstrbpMethod(BSMs) bre bdded here before InnerClbsses(ICs),
         * so bs to ensure the order. Noting thbt the BSMs  mby be
         * removed if they bre not found in the CP, bfter the ICs expbnsion.
         */
        cls.bddAttribute(Pbckbge.bttrBootstrbpMethodsEmpty.cbnonicblInstbnce());

        // flesh out the locbl constbnt pool
        ConstbntPool.completeReferencesIn(cpRefs, true, bsms);

        // Now thbt we know bll our locbl clbss references,
        // compute the InnerClbsses bttribute.
        int chbnged = cls.expbndLocblICs();

        if (chbnged != 0) {
            if (chbnged > 0) {
                // Just visit the expbnded InnerClbsses bttr.
                cls.visitInnerClbssRefs(VRM_CLASSIC, cpRefs);
            } else {
                // Hbve to recompute from scrbtch, becbuse of deletions.
                cpRefs.clebr();
                cls.visitRefs(VRM_CLASSIC, cpRefs);
            }

            // flesh out the locbl constbnt pool, bgbin
            ConstbntPool.completeReferencesIn(cpRefs, true, bsms);
        }

        // remove the bttr previously set, otherwise bdd the bsm bnd
        // references bs required
        if (bsms.isEmpty()) {
            cls.bttributes.remove(Pbckbge.bttrBootstrbpMethodsEmpty.cbnonicblInstbnce());
        } else {
            cpRefs.bdd(Pbckbge.getRefString("BootstrbpMethods"));
            Collections.sort(bsms);
            cls.setBootstrbpMethods(bsms);
        }

        // construct b locbl constbnt pool
        int numDoubles = 0;
        for (Entry e : cpRefs) {
            if (e.isDoubleWord())  numDoubles++;
        }
        Entry[] cpMbp = new Entry[1+numDoubles+cpRefs.size()];
        int fillp = 1;

        // Add bll ldc operbnds first.
        if (ldcRefs != null) {
            bssert(cpRefs.contbinsAll(ldcRefs));
            for (Entry e : ldcRefs) {
                cpMbp[fillp++] = e;
            }
            bssert(fillp == 1+ldcRefs.size());
            cpRefs.removeAll(ldcRefs);
            ldcRefs = null;  // done with it
        }

        // Next bdd bll the two-byte references.
        Set<Entry> wideRefs = cpRefs;
        cpRefs = null;  // do not use!
        int nbrrowLimit = fillp;
        for (Entry e : wideRefs) {
            cpMbp[fillp++] = e;
        }
        bssert(fillp == nbrrowLimit+wideRefs.size());
        Arrbys.sort(cpMbp, 1, nbrrowLimit, entryOutputOrder);
        Arrbys.sort(cpMbp, nbrrowLimit, fillp, entryOutputOrder);

        if (verbose > 3) {
            Utils.log.fine("CP of "+this+" {");
            for (int i = 0; i < fillp; i++) {
                Entry e = cpMbp[i];
                Utils.log.fine("  "+((e==null)?-1:getOutputIndex(e))
                                   +" : "+e);
            }
            Utils.log.fine("}");
        }

        // Now repbck bbckwbrds, introducing null elements.
        int revp = cpMbp.length;
        for (int i = fillp; --i >= 1; ) {
            Entry e = cpMbp[i];
            if (e.isDoubleWord())
                cpMbp[--revp] = null;
            cpMbp[--revp] = e;
        }
        bssert(revp == 1);  // do not process the initibl null

        return cpMbp;
    }

    void rebdMembers(Clbss[] clbsses) throws IOException {
        //  clbss_bbnds:
        //        ...
        //        *clbss_field_count :DELTA5
        //        *clbss_method_count :DELTA5
        //
        //        *field_descr :DELTA5  (cp_Descr)
        //        field_bttr_bbnds
        //
        //        *method_descr :MDELTA5  (cp_Descr)
        //        method_bttr_bbnds
        //        ...
        bssert(clbsses.length == numClbsses);
        clbss_field_count.expectLength(numClbsses);
        clbss_method_count.expectLength(numClbsses);
        clbss_field_count.rebdFrom(in);
        clbss_method_count.rebdFrom(in);

        // Mbke b pre-pbss over field bnd method counts to size the descrs:
        int totblNF = clbss_field_count.getIntTotbl();
        int totblNM = clbss_method_count.getIntTotbl();
        field_descr.expectLength(totblNF);
        method_descr.expectLength(totblNM);
        if (verbose > 1)  Utils.log.fine("expecting #fields="+totblNF+
                " bnd #methods="+totblNM+" in #clbsses="+numClbsses);

        List<Clbss.Field> fields = new ArrbyList<>(totblNF);
        field_descr.rebdFrom(in);
        for (int i = 0; i < clbsses.length; i++) {
            Clbss c = clbsses[i];
            int nf = clbss_field_count.getInt();
            for (int j = 0; j < nf; j++) {
                Clbss.Field f = c.new Field(NO_FLAGS_YET, (DescriptorEntry)
                                            field_descr.getRef());
                fields.bdd(f);
            }
        }
        clbss_field_count.doneDisbursing();
        field_descr.doneDisbursing();
        countAndRebdAttrs(ATTR_CONTEXT_FIELD, fields);
        fields = null;  // relebse to GC

        List<Clbss.Method> methods = new ArrbyList<>(totblNM);
        method_descr.rebdFrom(in);
        for (int i = 0; i < clbsses.length; i++) {
            Clbss c = clbsses[i];
            int nm = clbss_method_count.getInt();
            for (int j = 0; j < nm; j++) {
                Clbss.Method m = c.new Method(NO_FLAGS_YET, (DescriptorEntry)
                                              method_descr.getRef());
                methods.bdd(m);
            }
        }
        clbss_method_count.doneDisbursing();
        method_descr.doneDisbursing();
        countAndRebdAttrs(ATTR_CONTEXT_METHOD, methods);

        // Up to this point, Code bttributes look like empty bttributes.
        // Now we stbrt to specibl-cbse them.  The empty cbnonicbl Code
        // bttributes stby in the method bttribute lists, however.
        bllCodes = buildCodeAttrs(methods);
    }

    Code[] bllCodes;
    List<Code> codesWithFlbgs;
    Mbp<Clbss, Set<Entry>> ldcRefMbp = new HbshMbp<>();

    Code[] buildCodeAttrs(List<Clbss.Method> methods) {
        List<Code> codes = new ArrbyList<>(methods.size());
        for (Clbss.Method m : methods) {
            if (m.getAttribute(bttrCodeEmpty) != null) {
                m.code = new Code(m);
                codes.bdd(m.code);
            }
        }
        Code[] b = new Code[codes.size()];
        codes.toArrby(b);
        return b;
    }

    void rebdCodeHebders() throws IOException {
        //  code_bbnds:
        //        *code_hebders :BYTE1
        //
        //        *code_mbx_stbck :UNSIGNED5
        //        *code_mbx_nb_locbls :UNSIGNED5
        //        *code_hbndler_count :UNSIGNED5
        //        ...
        //        code_bttr_bbnds
        boolebn bttrsOK = testBit(brchiveOptions, AO_HAVE_ALL_CODE_FLAGS);
        code_hebders.expectLength(bllCodes.length);
        code_hebders.rebdFrom(in);
        List<Code> longCodes = new ArrbyList<>(bllCodes.length / 10);
        for (int i = 0; i < bllCodes.length; i++) {
            Code c = bllCodes[i];
            int sc = code_hebders.getByte();
            bssert(sc == (sc & 0xFF));
            if (verbose > 2)
                Utils.log.fine("codeHebder "+c+" = "+sc);
            if (sc == LONG_CODE_HEADER) {
                // We will rebd ms/ml/nh/flbgs from bbnds shortly.
                longCodes.bdd(c);
                continue;
            }
            // Short code hebder is the usubl cbse:
            c.setMbxStbck(     shortCodeHebder_mbx_stbck(sc) );
            c.setMbxNALocbls(  shortCodeHebder_mbx_nb_locbls(sc) );
            c.setHbndlerCount( shortCodeHebder_hbndler_count(sc) );
            bssert(shortCodeHebder(c) == sc);
        }
        code_hebders.doneDisbursing();
        code_mbx_stbck.expectLength(longCodes.size());
        code_mbx_nb_locbls.expectLength(longCodes.size());
        code_hbndler_count.expectLength(longCodes.size());

        // Do the long hebders now.
        code_mbx_stbck.rebdFrom(in);
        code_mbx_nb_locbls.rebdFrom(in);
        code_hbndler_count.rebdFrom(in);
        for (Code c : longCodes) {
            c.setMbxStbck(     code_mbx_stbck.getInt() );
            c.setMbxNALocbls(  code_mbx_nb_locbls.getInt() );
            c.setHbndlerCount( code_hbndler_count.getInt() );
        }
        code_mbx_stbck.doneDisbursing();
        code_mbx_nb_locbls.doneDisbursing();
        code_hbndler_count.doneDisbursing();

        rebdCodeHbndlers();

        if (bttrsOK) {
            // Code bttributes bre common (debug info not stripped).
            codesWithFlbgs = Arrbys.bsList(bllCodes);
        } else {
            // Code bttributes bre very spbrse (debug info is stripped).
            codesWithFlbgs = longCodes;
        }
        countAttrs(ATTR_CONTEXT_CODE, codesWithFlbgs);
        // do rebdAttrs lbter, bfter BCs bre scbnned
    }

    void rebdCodeHbndlers() throws IOException {
        //  code_bbnds:
        //        ...
        //        *code_hbndler_stbrt_P :BCI5
        //        *code_hbndler_end_PO :BRANCH5
        //        *code_hbndler_cbtch_PO :BRANCH5
        //        *code_hbndler_clbss_RCN :UNSIGNED5  (null or cp_Clbss)
        //        ...
        int nh = 0;
        for (int i = 0; i < bllCodes.length; i++) {
            Code c = bllCodes[i];
            nh += c.getHbndlerCount();
        }

        VblueBbnd[] code_hbndler_bbnds = {
            code_hbndler_stbrt_P,
            code_hbndler_end_PO,
            code_hbndler_cbtch_PO,
            code_hbndler_clbss_RCN
        };

        for (int i = 0; i < code_hbndler_bbnds.length; i++) {
            code_hbndler_bbnds[i].expectLength(nh);
            code_hbndler_bbnds[i].rebdFrom(in);
        }

        for (int i = 0; i < bllCodes.length; i++) {
            Code c = bllCodes[i];
            for (int j = 0, jmbx = c.getHbndlerCount(); j < jmbx; j++) {
                c.hbndler_clbss[j] = code_hbndler_clbss_RCN.getRef();
                // For now, just record the rbw BCI codes.
                // We must wbit until we hbve instruction boundbries.
                c.hbndler_stbrt[j] = code_hbndler_stbrt_P.getInt();
                c.hbndler_end[j]   = code_hbndler_end_PO.getInt();
                c.hbndler_cbtch[j] = code_hbndler_cbtch_PO.getInt();
            }
        }
        for (int i = 0; i < code_hbndler_bbnds.length; i++) {
            code_hbndler_bbnds[i].doneDisbursing();
        }
    }

    void fixupCodeHbndlers() {
        // Actublly decode (renumber) the BCIs now.
        for (int i = 0; i < bllCodes.length; i++) {
            Code c = bllCodes[i];
            for (int j = 0, jmbx = c.getHbndlerCount(); j < jmbx; j++) {
                int sum = c.hbndler_stbrt[j];
                c.hbndler_stbrt[j] = c.decodeBCI(sum);
                sum += c.hbndler_end[j];
                c.hbndler_end[j]   = c.decodeBCI(sum);
                sum += c.hbndler_cbtch[j];
                c.hbndler_cbtch[j] = c.decodeBCI(sum);
            }
        }
    }

    // Generic routines for rebding bttributes of
    // clbsses, fields, methods, bnd codes.
    // The holders is b globbl list, blrebdy collected,
    // of bttribute "customers".
    void countAndRebdAttrs(int ctype, Collection<? extends Attribute.Holder> holders)
            throws IOException {
        //  clbss_bttr_bbnds:
        //        *clbss_flbgs :UNSIGNED5
        //        *clbss_bttr_count :UNSIGNED5
        //        *clbss_bttr_indexes :UNSIGNED5
        //        *clbss_bttr_cblls :UNSIGNED5
        //        *clbss_Signbture_RS :UNSIGNED5 (cp_Signbture)
        //        clbss_metbdbtb_bbnds
        //        *clbss_SourceFile_RU :UNSIGNED5 (cp_Utf8)
        //        *clbss_EnclosingMethod_RM :UNSIGNED5 (cp_Method)
        //        ic_locbl_bbnds
        //        *clbss_ClbssFile_version_minor_H :UNSIGNED5
        //        *clbss_ClbssFile_version_mbjor_H :UNSIGNED5
        //        clbss_type_metbdbtb_bbnds
        //
        //  field_bttr_bbnds:
        //        *field_flbgs :UNSIGNED5
        //        *field_bttr_count :UNSIGNED5
        //        *field_bttr_indexes :UNSIGNED5
        //        *field_bttr_cblls :UNSIGNED5
        //        *field_Signbture_RS :UNSIGNED5 (cp_Signbture)
        //        field_metbdbtb_bbnds
        //        *field_ConstbntVblue_KQ :UNSIGNED5 (cp_Int, etc.; see note)
        //        field_type_metbdbtb_bbnds
        //
        //  method_bttr_bbnds:
        //        *method_flbgs :UNSIGNED5
        //        *method_bttr_count :UNSIGNED5
        //        *method_bttr_indexes :UNSIGNED5
        //        *method_bttr_cblls :UNSIGNED5
        //        *method_Signbture_RS :UNSIGNED5 (cp_Signbture)
        //        method_metbdbtb_bbnds
        //        *method_Exceptions_N :UNSIGNED5
        //        *method_Exceptions_RC :UNSIGNED5  (cp_Clbss)
        //        *method_MethodPbrbmeters_NB: BYTE1
        //        *method_MethodPbrbmeters_RUN: UNSIGNED5 (cp_Utf8)
        //        *method_MethodPbrbmeters_FH:  UNSIGNED5 (flbg)
        //        method_type_metbdbtb_bbnds
        //
        //  code_bttr_bbnds:
        //        *code_flbgs :UNSIGNED5
        //        *code_bttr_count :UNSIGNED5
        //        *code_bttr_indexes :UNSIGNED5
        //        *code_bttr_cblls :UNSIGNED5
        //        *code_LineNumberTbble_N :UNSIGNED5
        //        *code_LineNumberTbble_bci_P :BCI5
        //        *code_LineNumberTbble_line :UNSIGNED5
        //        *code_LocblVbribbleTbble_N :UNSIGNED5
        //        *code_LocblVbribbleTbble_bci_P :BCI5
        //        *code_LocblVbribbleTbble_spbn_O :BRANCH5
        //        *code_LocblVbribbleTbble_nbme_RU :UNSIGNED5 (cp_Utf8)
        //        *code_LocblVbribbleTbble_type_RS :UNSIGNED5 (cp_Signbture)
        //        *code_LocblVbribbleTbble_slot :UNSIGNED5
        //        code_type_metbdbtb_bbnds

        countAttrs(ctype, holders);
        rebdAttrs(ctype, holders);
    }

    // Rebd flbgs bnd count the bttributes thbt bre to be plbced
    // on the given holders.
    void countAttrs(int ctype, Collection<? extends Attribute.Holder> holders)
            throws IOException {
        // Here, xxx stbnds for one of clbss, field, method, code.
        MultiBbnd xxx_bttr_bbnds = bttrBbnds[ctype];
        long flbgMbsk = bttrFlbgMbsk[ctype];
        if (verbose > 1) {
            Utils.log.fine("scbnning flbgs bnd bttrs for "+
                    Attribute.contextNbme(ctype)+"["+holders.size()+"]");
        }

        // Fetch the bttribute lbyout definitions which govern the bbnds
        // we bre bbout to rebd.
        List<Attribute.Lbyout> defList = bttrDefs.get(ctype);
        Attribute.Lbyout[] defs = new Attribute.Lbyout[defList.size()];
        defList.toArrby(defs);
        IntBbnd xxx_flbgs_hi = getAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_HI);
        IntBbnd xxx_flbgs_lo = getAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_LO);
        IntBbnd xxx_bttr_count = getAttrBbnd(xxx_bttr_bbnds, AB_ATTR_COUNT);
        IntBbnd xxx_bttr_indexes = getAttrBbnd(xxx_bttr_bbnds, AB_ATTR_INDEXES);
        IntBbnd xxx_bttr_cblls = getAttrBbnd(xxx_bttr_bbnds, AB_ATTR_CALLS);

        // Count up the number of holders which hbve overflow bttrs.
        int overflowMbsk = bttrOverflowMbsk[ctype];
        int overflowHolderCount = 0;
        boolebn hbveLongFlbgs = hbveFlbgsHi(ctype);
        xxx_flbgs_hi.expectLength(hbveLongFlbgs? holders.size(): 0);
        xxx_flbgs_hi.rebdFrom(in);
        xxx_flbgs_lo.expectLength(holders.size());
        xxx_flbgs_lo.rebdFrom(in);
        bssert((flbgMbsk & overflowMbsk) == overflowMbsk);
        for (Attribute.Holder h : holders) {
            int flbgs = xxx_flbgs_lo.getInt();
            h.flbgs = flbgs;
            if ((flbgs & overflowMbsk) != 0)
                overflowHolderCount += 1;
        }

        // For ebch holder with overflow bttrs, rebd b count.
        xxx_bttr_count.expectLength(overflowHolderCount);
        xxx_bttr_count.rebdFrom(in);
        xxx_bttr_indexes.expectLength(xxx_bttr_count.getIntTotbl());
        xxx_bttr_indexes.rebdFrom(in);

        // Now it's time to check flbg bits thbt indicbte bttributes.
        // We bccumulbte (b) b list of bttribute types for ebch holder
        // (clbss/field/method/code), bnd blso we bccumulbte (b) b totbl
        // count for ebch bttribute type.
        int[] totblCounts = new int[defs.length];
        for (Attribute.Holder h : holders) {
            bssert(h.bttributes == null);
            // System.out.println("flbgs="+h.flbgs+" using fm="+flbgMbsk);
            long bttrBits = ((h.flbgs & flbgMbsk) << 32) >>> 32;
            // Clebn up the flbgs now.
            h.flbgs -= (int)bttrBits;   // strip bttr bits
            bssert(h.flbgs == (chbr)h.flbgs);  // 16 bits only now
            bssert((ctype != ATTR_CONTEXT_CODE) || h.flbgs == 0);
            if (hbveLongFlbgs)
                bttrBits += (long)xxx_flbgs_hi.getInt() << 32;
            if (bttrBits == 0)  continue;  // no bttrs on this guy

            int nob = 0;  // number of overflow bttrs
            long overflowBit = (bttrBits & overflowMbsk);
            bssert(overflowBit >= 0);
            bttrBits -= overflowBit;
            if (overflowBit != 0) {
                nob = xxx_bttr_count.getInt();
            }

            int nfb = 0;  // number of flbg bttrs
            long bits = bttrBits;
            for (int bi = 0; bits != 0; bi++) {
                if ((bits & (1L<<bi)) == 0)  continue;
                bits -= (1L<<bi);
                nfb += 1;
            }
            List<Attribute> hb = new ArrbyList<>(nfb + nob);
            h.bttributes = hb;
            bits = bttrBits;  // iterbte bgbin
            for (int bi = 0; bits != 0; bi++) {
                if ((bits & (1L<<bi)) == 0)  continue;
                bits -= (1L<<bi);
                totblCounts[bi] += 1;
                // This definition index is live in this holder.
                if (defs[bi] == null)  bbdAttrIndex(bi, ctype);
                Attribute cbnonicbl = defs[bi].cbnonicblInstbnce();
                hb.bdd(cbnonicbl);
                nfb -= 1;
            }
            bssert(nfb == 0);
            for (; nob > 0; nob--) {
                int bi = xxx_bttr_indexes.getInt();
                totblCounts[bi] += 1;
                // This definition index is live in this holder.
                if (defs[bi] == null)  bbdAttrIndex(bi, ctype);
                Attribute cbnonicbl = defs[bi].cbnonicblInstbnce();
                hb.bdd(cbnonicbl);
            }
        }

        xxx_flbgs_hi.doneDisbursing();
        xxx_flbgs_lo.doneDisbursing();
        xxx_bttr_count.doneDisbursing();
        xxx_bttr_indexes.doneDisbursing();

        // Now ebch holder hbs b list of cbnonicbl bttribute instbnces.
        // For lbyouts with no elements, we bre done.  However, for
        // lbyouts with bbnds, we must replbce ebch cbnonicbl (empty)
        // instbnce with b vblue-bebring one, initiblized from the
        // bppropribte bbnds.

        // Mbke b smbll pbss to detect bnd rebd bbckwbrd cbll counts.
        int cbllCounts = 0;
        for (boolebn predef = true; ; predef = fblse) {
            for (int bi = 0; bi < defs.length; bi++) {
                Attribute.Lbyout def = defs[bi];
                if (def == null)  continue;  // unused index
                if (predef != isPredefinedAttr(ctype, bi))
                    continue;  // wrong pbss
                int totblCount = totblCounts[bi];
                if (totblCount == 0)
                    continue;  // irrelevbnt
                Attribute.Lbyout.Element[] cbles = def.getCbllbbles();
                for (int j = 0; j < cbles.length; j++) {
                    bssert(cbles[j].kind == Attribute.EK_CBLE);
                    if (cbles[j].flbgTest(Attribute.EF_BACK))
                        cbllCounts += 1;
                }
            }
            if (!predef)  brebk;
        }
        xxx_bttr_cblls.expectLength(cbllCounts);
        xxx_bttr_cblls.rebdFrom(in);

        // Finblly, size bll the bttribute bbnds.
        for (boolebn predef = true; ; predef = fblse) {
            for (int bi = 0; bi < defs.length; bi++) {
                Attribute.Lbyout def = defs[bi];
                if (def == null)  continue;  // unused index
                if (predef != isPredefinedAttr(ctype, bi))
                    continue;  // wrong pbss
                int totblCount = totblCounts[bi];
                Bbnd[] bb = bttrBbndTbble.get(def);
                if (def == bttrInnerClbssesEmpty) {
                    // Specibl cbse.
                    // Size the bbnds bs if using the following lbyout:
                    //    [RCH TI[ (0)[] ()[RCNH RUNH] ]].
                    clbss_InnerClbsses_N.expectLength(totblCount);
                    clbss_InnerClbsses_N.rebdFrom(in);
                    int tupleCount = clbss_InnerClbsses_N.getIntTotbl();
                    clbss_InnerClbsses_RC.expectLength(tupleCount);
                    clbss_InnerClbsses_RC.rebdFrom(in);
                    clbss_InnerClbsses_F.expectLength(tupleCount);
                    clbss_InnerClbsses_F.rebdFrom(in);
                    // Drop rembining columns wherever flbgs bre zero:
                    tupleCount -= clbss_InnerClbsses_F.getIntCount(0);
                    clbss_InnerClbsses_outer_RCN.expectLength(tupleCount);
                    clbss_InnerClbsses_outer_RCN.rebdFrom(in);
                    clbss_InnerClbsses_nbme_RUN.expectLength(tupleCount);
                    clbss_InnerClbsses_nbme_RUN.rebdFrom(in);
                } else if (!optDebugBbnds && totblCount == 0) {
                    // Expect no elements bt bll.  Skip quickly. however if we
                    // bre debugging bbnds, rebd bll bbnds regbrdless
                    for (int j = 0; j < bb.length; j++) {
                        bb[j].doneWithUnusedBbnd();
                    }
                } else {
                    // Rebd these bbnds in sequence.
                    boolebn hbsCbllbbles = def.hbsCbllbbles();
                    if (!hbsCbllbbles) {
                        rebdAttrBbnds(def.elems, totblCount, new int[0], bb);
                    } else {
                        Attribute.Lbyout.Element[] cbles = def.getCbllbbles();
                        // At first, record initibl cblls.
                        // Lbter, forwbrd cblls mby blso bccumulbte here:
                        int[] forwbrdCounts = new int[cbles.length];
                        forwbrdCounts[0] = totblCount;
                        for (int j = 0; j < cbles.length; j++) {
                            bssert(cbles[j].kind == Attribute.EK_CBLE);
                            int entryCount = forwbrdCounts[j];
                            forwbrdCounts[j] = -1;  // No more, plebse!
                            if (totblCount > 0 && cbles[j].flbgTest(Attribute.EF_BACK))
                                entryCount += xxx_bttr_cblls.getInt();
                            rebdAttrBbnds(cbles[j].body, entryCount, forwbrdCounts, bb);
                        }
                    }
                    // mbrk them rebd,  to sbtisfy bsserts
                    if (optDebugBbnds && totblCount == 0) {
                        for (int j = 0; j < bb.length; j++) {
                            bb[j].doneDisbursing();
                        }
                    }
                }
            }
            if (!predef)  brebk;
        }
        xxx_bttr_cblls.doneDisbursing();
    }

    void bbdAttrIndex(int bi, int ctype) throws IOException {
        throw new IOException("Unknown bttribute index "+bi+" for "+
                                   ATTR_CONTEXT_NAME[ctype]+" bttribute");
    }

    void rebdAttrs(int ctype, Collection<? extends Attribute.Holder> holders)
            throws IOException {
        // Decode bbnd vblues into bttributes.
        Set<Attribute.Lbyout> sbwDefs = new HbshSet<>();
        ByteArrbyOutputStrebm buf = new ByteArrbyOutputStrebm();
        for (finbl Attribute.Holder h : holders) {
            if (h.bttributes == null)  continue;
            for (ListIterbtor<Attribute> j = h.bttributes.listIterbtor(); j.hbsNext(); ) {
                Attribute b = j.next();
                Attribute.Lbyout def = b.lbyout();
                if (def.bbndCount == 0) {
                    if (def == bttrInnerClbssesEmpty) {
                        // Specibl logic to rebd this bttr.
                        rebdLocblInnerClbsses((Clbss) h);
                        continue;
                    }
                    // Cbnonicbl empty bttr works fine (e.g., Synthetic).
                    continue;
                }
                sbwDefs.bdd(def);
                boolebn isCV = (ctype == ATTR_CONTEXT_FIELD && def == bttrConstbntVblue);
                if (isCV)  setConstbntVblueIndex((Clbss.Field)h);
                if (verbose > 2)
                    Utils.log.fine("rebd "+b+" in "+h);
                finbl Bbnd[] bb = bttrBbndTbble.get(def);
                // Rebd one bttribute of type def from bb into b byte brrby.
                buf.reset();
                Object fixups = b.unpbrse(new Attribute.VblueStrebm() {
                    public int getInt(int bbndIndex) {
                        return ((IntBbnd) bb[bbndIndex]).getInt();
                    }
                    public Entry getRef(int bbndIndex) {
                        return ((CPRefBbnd) bb[bbndIndex]).getRef();
                    }
                    public int decodeBCI(int bciCode) {
                        Code code = (Code) h;
                        return code.decodeBCI(bciCode);
                    }
                }, buf);
                // Replbce the cbnonicbl bttr with the one just rebd.
                j.set(b.bddContent(buf.toByteArrby(), fixups));
                if (isCV)  setConstbntVblueIndex(null);  // clebn up
            }
        }

        // Mbrk the bbnds we just used bs done disbursing.
        for (Attribute.Lbyout def : sbwDefs) {
            if (def == null)  continue;  // unused index
            Bbnd[] bb = bttrBbndTbble.get(def);
            for (int j = 0; j < bb.length; j++) {
                bb[j].doneDisbursing();
            }
        }

        if (ctype == ATTR_CONTEXT_CLASS) {
            clbss_InnerClbsses_N.doneDisbursing();
            clbss_InnerClbsses_RC.doneDisbursing();
            clbss_InnerClbsses_F.doneDisbursing();
            clbss_InnerClbsses_outer_RCN.doneDisbursing();
            clbss_InnerClbsses_nbme_RUN.doneDisbursing();
        }

        MultiBbnd xxx_bttr_bbnds = bttrBbnds[ctype];
        for (int i = 0; i < xxx_bttr_bbnds.size(); i++) {
            Bbnd b = xxx_bttr_bbnds.get(i);
            if (b instbnceof MultiBbnd)
                b.doneDisbursing();
        }
        xxx_bttr_bbnds.doneDisbursing();
    }

    privbte
    void rebdAttrBbnds(Attribute.Lbyout.Element[] elems,
                       int count, int[] forwbrdCounts,
                       Bbnd[] bb)
            throws IOException {
        for (int i = 0; i < elems.length; i++) {
            Attribute.Lbyout.Element e = elems[i];
            Bbnd eBbnd = null;
            if (e.hbsBbnd()) {
                eBbnd = bb[e.bbndIndex];
                eBbnd.expectLength(count);
                eBbnd.rebdFrom(in);
            }
            switch (e.kind) {
            cbse Attribute.EK_REPL:
                // Recursive cbll.
                int repCount = ((IntBbnd)eBbnd).getIntTotbl();
                // Note:  getIntTotbl mbkes bn extrb pbss over this bbnd.
                rebdAttrBbnds(e.body, repCount, forwbrdCounts, bb);
                brebk;
            cbse Attribute.EK_UN:
                int rembiningCount = count;
                for (int j = 0; j < e.body.length; j++) {
                    int cbseCount;
                    if (j == e.body.length-1) {
                        cbseCount = rembiningCount;
                    } else {
                        cbseCount = 0;
                        for (int j0 = j;
                             (j == j0)
                             || (j < e.body.length
                                 && e.body[j].flbgTest(Attribute.EF_BACK));
                             j++) {
                            cbseCount += ((IntBbnd)eBbnd).getIntCount(e.body[j].vblue);
                        }
                        --j;  // bbck up to lbst occurrence of this body
                    }
                    rembiningCount -= cbseCount;
                    rebdAttrBbnds(e.body[j].body, cbseCount, forwbrdCounts, bb);
                }
                bssert(rembiningCount == 0);
                brebk;
            cbse Attribute.EK_CALL:
                bssert(e.body.length == 1);
                bssert(e.body[0].kind == Attribute.EK_CBLE);
                if (!e.flbgTest(Attribute.EF_BACK)) {
                    // Bbckwbrd cblls bre pre-counted, but forwbrds bre not.
                    // Push the present count forwbrd.
                    bssert(forwbrdCounts[e.vblue] >= 0);
                    forwbrdCounts[e.vblue] += count;
                }
                brebk;
            cbse Attribute.EK_CBLE:
                bssert(fblse);
                brebk;
            }
        }
    }

    void rebdByteCodes() throws IOException {
        //  bc_bbnds:
        //        *bc_codes :BYTE1
        //        *bc_cbse_count :UNSIGNED5
        //        *bc_cbse_vblue :DELTA5
        //        *bc_byte :BYTE1
        //        *bc_short :DELTA5
        //        *bc_locbl :UNSIGNED5
        //        *bc_lbbel :BRANCH5
        //        *bc_intref :DELTA5  (cp_Int)
        //        *bc_flobtref :DELTA5  (cp_Flobt)
        //        *bc_longref :DELTA5  (cp_Long)
        //        *bc_doubleref :DELTA5  (cp_Double)
        //        *bc_stringref :DELTA5  (cp_String)
        //        *bc_clbssref :UNSIGNED5  (current clbss or cp_Clbss)
        //        *bc_fieldref :DELTA5  (cp_Field)
        //        *bc_methodref :UNSIGNED5  (cp_Method)
        //        *bc_imethodref :DELTA5  (cp_Imethod)
        //        *bc_thisfield :UNSIGNED5 (cp_Field, only for current clbss)
        //        *bc_superfield :UNSIGNED5 (cp_Field, only for current super)
        //        *bc_thismethod :UNSIGNED5 (cp_Method, only for current clbss)
        //        *bc_supermethod :UNSIGNED5 (cp_Method, only for current super)
        //        *bc_initref :UNSIGNED5 (cp_Field, only for most recent new)
        //        *bc_escref :UNSIGNED5 (cp_All)
        //        *bc_escrefsize :UNSIGNED5
        //        *bc_escsize :UNSIGNED5
        //        *bc_escbyte :BYTE1
        bc_codes.elementCountForDebug = bllCodes.length;
        bc_codes.setInputStrebmFrom(in);
        rebdByteCodeOps();  // rebds from bc_codes bnd bc_cbse_count
        bc_codes.doneDisbursing();

        // All the operbnd bbnds hbve now been sized.  Rebd them bll in turn.
        Bbnd[] operbnd_bbnds = {
            bc_cbse_vblue,
            bc_byte, bc_short,
            bc_locbl, bc_lbbel,
            bc_intref, bc_flobtref,
            bc_longref, bc_doubleref, bc_stringref,
            bc_lobdbblevblueref,
            bc_clbssref, bc_fieldref,
            bc_methodref, bc_imethodref,
            bc_indyref,
            bc_thisfield, bc_superfield,
            bc_thismethod, bc_supermethod,
            bc_initref,
            bc_escref, bc_escrefsize, bc_escsize
        };
        for (int i = 0; i < operbnd_bbnds.length; i++) {
            operbnd_bbnds[i].rebdFrom(in);
        }
        bc_escbyte.expectLength(bc_escsize.getIntTotbl());
        bc_escbyte.rebdFrom(in);

        expbndByteCodeOps();

        // Done fetching vblues from operbnd bbnds:
        bc_cbse_count.doneDisbursing();
        for (int i = 0; i < operbnd_bbnds.length; i++) {
            operbnd_bbnds[i].doneDisbursing();
        }
        bc_escbyte.doneDisbursing();
        bc_bbnds.doneDisbursing();

        // We must delby the pbrsing of Code bttributes until we
        // hbve b complete model of bytecodes, for BCI encodings.
        rebdAttrs(ATTR_CONTEXT_CODE, codesWithFlbgs);
        // Ditto for exception hbndlers in codes.
        fixupCodeHbndlers();
        // Now we cbn finish with clbss_bbnds; cf. rebdClbsses().
        code_bbnds.doneDisbursing();
        clbss_bbnds.doneDisbursing();
    }

    privbte void rebdByteCodeOps() throws IOException {
        // scrbtch buffer for collecting code::
        byte[] buf = new byte[1<<12];
        // record of bll switch opcodes (these bre vbribble-length)
        List<Integer> bllSwitchOps = new ArrbyList<>();
        for (int k = 0; k < bllCodes.length; k++) {
            Code c = bllCodes[k];
        scbnOneMethod:
            for (int i = 0; ; i++) {
                int bc = bc_codes.getByte();
                if (i + 10 > buf.length)  buf = reblloc(buf);
                buf[i] = (byte)bc;
                boolebn isWide = fblse;
                if (bc == _wide) {
                    bc = bc_codes.getByte();
                    buf[++i] = (byte)bc;
                    isWide = true;
                }
                bssert(bc == (0xFF & bc));
                // Adjust expectbtions of vbrious bbnd sizes.
                switch (bc) {
                cbse _tbbleswitch:
                cbse _lookupswitch:
                    bc_cbse_count.expectMoreLength(1);
                    bllSwitchOps.bdd(bc);
                    brebk;
                cbse _iinc:
                    bc_locbl.expectMoreLength(1);
                    if (isWide)
                        bc_short.expectMoreLength(1);
                    else
                        bc_byte.expectMoreLength(1);
                    brebk;
                cbse _sipush:
                    bc_short.expectMoreLength(1);
                    brebk;
                cbse _bipush:
                    bc_byte.expectMoreLength(1);
                    brebk;
                cbse _newbrrby:
                    bc_byte.expectMoreLength(1);
                    brebk;
                cbse _multibnewbrrby:
                    bssert(getCPRefOpBbnd(bc) == bc_clbssref);
                    bc_clbssref.expectMoreLength(1);
                    bc_byte.expectMoreLength(1);
                    brebk;
                cbse _ref_escbpe:
                    bc_escrefsize.expectMoreLength(1);
                    bc_escref.expectMoreLength(1);
                    brebk;
                cbse _byte_escbpe:
                    bc_escsize.expectMoreLength(1);
                    // bc_escbyte will hbve to be counted too
                    brebk;
                defbult:
                    if (Instruction.isInvokeInitOp(bc)) {
                        bc_initref.expectMoreLength(1);
                        brebk;
                    }
                    if (Instruction.isSelfLinkerOp(bc)) {
                        CPRefBbnd bc_which = selfOpRefBbnd(bc);
                        bc_which.expectMoreLength(1);
                        brebk;
                    }
                    if (Instruction.isBrbnchOp(bc)) {
                        bc_lbbel.expectMoreLength(1);
                        brebk;
                    }
                    if (Instruction.isCPRefOp(bc)) {
                        CPRefBbnd bc_which = getCPRefOpBbnd(bc);
                        bc_which.expectMoreLength(1);
                        bssert(bc != _multibnewbrrby);  // hbndled elsewhere
                        brebk;
                    }
                    if (Instruction.isLocblSlotOp(bc)) {
                        bc_locbl.expectMoreLength(1);
                        brebk;
                    }
                    brebk;
                cbse _end_mbrker:
                    {
                        // Trbnsfer from buf to b more permbnent plbce:
                        c.bytes = reblloc(buf, i);
                        brebk scbnOneMethod;
                    }
                }
            }
        }

        // To size instruction bbnds correctly, we need info on switches:
        bc_cbse_count.rebdFrom(in);
        for (Integer i : bllSwitchOps) {
            int bc = i.intVblue();
            int cbseCount = bc_cbse_count.getInt();
            bc_lbbel.expectMoreLength(1+cbseCount); // defbult lbbel + cbses
            bc_cbse_vblue.expectMoreLength(bc == _tbbleswitch ? 1 : cbseCount);
        }
        bc_cbse_count.resetForSecondPbss();
    }

    privbte void expbndByteCodeOps() throws IOException {
        // scrbtch buffer for collecting code:
        byte[] buf = new byte[1<<12];
        // scrbtch buffer for collecting instruction boundbries:
        int[] insnMbp = new int[1<<12];
        // list of lbbel cbrriers, for lbbel decoding post-pbss:
        int[] lbbels = new int[1<<10];
        // scrbtch buffer for registering CP refs:
        Fixups fixupBuf = new Fixups();

        for (int k = 0; k < bllCodes.length; k++) {
            Code code = bllCodes[k];
            byte[] codeOps = code.bytes;
            code.bytes = null;  // just for now, while we bccumulbte bits

            Clbss curClbss = code.thisClbss();

            Set<Entry> ldcRefSet = ldcRefMbp.get(curClbss);
            if (ldcRefSet == null)
                ldcRefMbp.put(curClbss, ldcRefSet = new HbshSet<>());

            ClbssEntry thisClbss  = curClbss.thisClbss;
            ClbssEntry superClbss = curClbss.superClbss;
            ClbssEntry newClbss   = null;  // clbss of lbst _new opcode

            int pc = 0;  // fill pointer in buf; bctubl bytecode PC
            int numInsns = 0;
            int numLbbels = 0;
            boolebn hbsEscs = fblse;
            fixupBuf.clebr();
            for (int i = 0; i < codeOps.length; i++) {
                int bc = Instruction.getByte(codeOps, i);
                int curPC = pc;
                insnMbp[numInsns++] = curPC;
                if (pc + 10 > buf.length)  buf = reblloc(buf);
                if (numInsns+10 > insnMbp.length)  insnMbp = reblloc(insnMbp);
                if (numLbbels+10 > lbbels.length)  lbbels = reblloc(lbbels);
                boolebn isWide = fblse;
                if (bc == _wide) {
                    buf[pc++] = (byte) bc;
                    bc = Instruction.getByte(codeOps, ++i);
                    isWide = true;
                }
                switch (bc) {
                cbse _tbbleswitch: // bpc:  (df, lo, hi, (hi-lo+1)*(lbbel))
                cbse _lookupswitch: // bpc:  (df, nc, nc*(cbse, lbbel))
                    {
                        int cbseCount = bc_cbse_count.getInt();
                        while ((pc + 30 + cbseCount*8) > buf.length)
                            buf = reblloc(buf);
                        buf[pc++] = (byte) bc;
                        //initiblize bpc, df, lo, hi bytes to rebsonbble bits:
                        Arrbys.fill(buf, pc, pc+30, (byte)0);
                        Instruction.Switch isw = (Instruction.Switch)
                            Instruction.bt(buf, curPC);
                        //isw.setDefbultLbbel(getLbbel(bc_lbbel, code, curPC));
                        isw.setCbseCount(cbseCount);
                        if (bc == _tbbleswitch) {
                            isw.setCbseVblue(0, bc_cbse_vblue.getInt());
                        } else {
                            for (int j = 0; j < cbseCount; j++) {
                                isw.setCbseVblue(j, bc_cbse_vblue.getInt());
                            }
                        }
                        // Mbke our getLbbel cblls lbter.
                        lbbels[numLbbels++] = curPC;
                        pc = isw.getNextPC();
                        continue;
                    }
                cbse _iinc:
                    {
                        buf[pc++] = (byte) bc;
                        int locbl = bc_locbl.getInt();
                        int deltb;
                        if (isWide) {
                            deltb = bc_short.getInt();
                            Instruction.setShort(buf, pc, locbl); pc += 2;
                            Instruction.setShort(buf, pc, deltb); pc += 2;
                        } else {
                            deltb = (byte) bc_byte.getByte();
                            buf[pc++] = (byte)locbl;
                            buf[pc++] = (byte)deltb;
                        }
                        continue;
                    }
                cbse _sipush:
                    {
                        int vbl = bc_short.getInt();
                        buf[pc++] = (byte) bc;
                        Instruction.setShort(buf, pc, vbl); pc += 2;
                        continue;
                    }
                cbse _bipush:
                cbse _newbrrby:
                    {
                        int vbl = bc_byte.getByte();
                        buf[pc++] = (byte) bc;
                        buf[pc++] = (byte) vbl;
                        continue;
                    }
                cbse _ref_escbpe:
                    {
                        // Note thbt insnMbp hbs one entry for this.
                        hbsEscs = true;
                        int size = bc_escrefsize.getInt();
                        Entry ref = bc_escref.getRef();
                        if (size == 1)  ldcRefSet.bdd(ref);
                        int fmt;
                        switch (size) {
                        cbse 1: fixupBuf.bddU1(pc, ref); brebk;
                        cbse 2: fixupBuf.bddU2(pc, ref); brebk;
                        defbult: bssert(fblse); fmt = 0;
                        }
                        buf[pc+0] = buf[pc+1] = 0;
                        pc += size;
                    }
                    continue;
                cbse _byte_escbpe:
                    {
                        // Note thbt insnMbp hbs one entry for bll these bytes.
                        hbsEscs = true;
                        int size = bc_escsize.getInt();
                        while ((pc + size) > buf.length)
                            buf = reblloc(buf);
                        while (size-- > 0) {
                            buf[pc++] = (byte) bc_escbyte.getByte();
                        }
                    }
                    continue;
                defbult:
                    if (Instruction.isInvokeInitOp(bc)) {
                        int idx = (bc - _invokeinit_op);
                        int origBC = _invokespecibl;
                        ClbssEntry clbssRef;
                        switch (idx) {
                        cbse _invokeinit_self_option:
                            clbssRef = thisClbss; brebk;
                        cbse _invokeinit_super_option:
                            clbssRef = superClbss; brebk;
                        defbult:
                            bssert(idx == _invokeinit_new_option);
                            clbssRef = newClbss; brebk;
                        }
                        buf[pc++] = (byte) origBC;
                        int coding = bc_initref.getInt();
                        // Find the nth overlobding of <init> in clbssRef.
                        MemberEntry ref = pkg.cp.getOverlobdingForIndex(CONSTANT_Methodref, clbssRef, "<init>", coding);
                        fixupBuf.bddU2(pc, ref);
                        buf[pc+0] = buf[pc+1] = 0;
                        pc += 2;
                        bssert(Instruction.opLength(origBC) == (pc - curPC));
                        continue;
                    }
                    if (Instruction.isSelfLinkerOp(bc)) {
                        int idx = (bc - _self_linker_op);
                        boolebn isSuper = (idx >= _self_linker_super_flbg);
                        if (isSuper)  idx -= _self_linker_super_flbg;
                        boolebn isAlobd = (idx >= _self_linker_blobd_flbg);
                        if (isAlobd)  idx -= _self_linker_blobd_flbg;
                        int origBC = _first_linker_op + idx;
                        boolebn isField = Instruction.isFieldOp(origBC);
                        CPRefBbnd bc_which;
                        ClbssEntry which_cls  = isSuper ? superClbss : thisClbss;
                        Index which_ix;
                        if (isField) {
                            bc_which = isSuper ? bc_superfield  : bc_thisfield;
                            which_ix = pkg.cp.getMemberIndex(CONSTANT_Fieldref, which_cls);
                        } else {
                            bc_which = isSuper ? bc_supermethod : bc_thismethod;
                            which_ix = pkg.cp.getMemberIndex(CONSTANT_Methodref, which_cls);
                        }
                        bssert(bc_which == selfOpRefBbnd(bc));
                        MemberEntry ref = (MemberEntry) bc_which.getRef(which_ix);
                        if (isAlobd) {
                            buf[pc++] = (byte) _blobd_0;
                            curPC = pc;
                            // Note: insnMbp keeps the _blobd_0 sepbrbte.
                            insnMbp[numInsns++] = curPC;
                        }
                        buf[pc++] = (byte) origBC;
                        fixupBuf.bddU2(pc, ref);
                        buf[pc+0] = buf[pc+1] = 0;
                        pc += 2;
                        bssert(Instruction.opLength(origBC) == (pc - curPC));
                        continue;
                    }
                    if (Instruction.isBrbnchOp(bc)) {
                        buf[pc++] = (byte) bc;
                        bssert(!isWide);  // no wide prefix for brbnches
                        int nextPC = curPC + Instruction.opLength(bc);
                        // Mbke our getLbbel cblls lbter.
                        lbbels[numLbbels++] = curPC;
                        //Instruction.bt(buf, curPC).setBrbnchLbbel(getLbbel(bc_lbbel, code, curPC));
                        while (pc < nextPC)  buf[pc++] = 0;
                        continue;
                    }
                    if (Instruction.isCPRefOp(bc)) {
                        CPRefBbnd bc_which = getCPRefOpBbnd(bc);
                        Entry ref = bc_which.getRef();
                        if (ref == null) {
                            if (bc_which == bc_clbssref) {
                                // Shorthbnd for clbss self-references.
                                ref = thisClbss;
                            } else {
                                bssert(fblse);
                            }
                        }
                        int origBC = bc;
                        int size = 2;
                        switch (bc) {
                        cbse _invokestbtic_int:
                            origBC = _invokestbtic;
                            brebk;
                        cbse _invokespecibl_int:
                            origBC = _invokespecibl;
                            brebk;
                        cbse _ildc:
                        cbse _cldc:
                        cbse _fldc:
                        cbse _sldc:
                        cbse _qldc:
                            origBC = _ldc;
                            size = 1;
                            ldcRefSet.bdd(ref);
                            brebk;
                        cbse _ildc_w:
                        cbse _cldc_w:
                        cbse _fldc_w:
                        cbse _sldc_w:
                        cbse _qldc_w:
                            origBC = _ldc_w;
                            brebk;
                        cbse _lldc2_w:
                        cbse _dldc2_w:
                            origBC = _ldc2_w;
                            brebk;
                        cbse _new:
                            newClbss = (ClbssEntry) ref;
                            brebk;
                        }
                        buf[pc++] = (byte) origBC;
                        int fmt;
                        switch (size) {
                        cbse 1: fixupBuf.bddU1(pc, ref); brebk;
                        cbse 2: fixupBuf.bddU2(pc, ref); brebk;
                        defbult: bssert(fblse); fmt = 0;
                        }
                        buf[pc+0] = buf[pc+1] = 0;
                        pc += size;
                        if (origBC == _multibnewbrrby) {
                            // Copy the trbiling byte blso.
                            int vbl = bc_byte.getByte();
                            buf[pc++] = (byte) vbl;
                        } else if (origBC == _invokeinterfbce) {
                            int brgSize = ((MemberEntry)ref).descRef.typeRef.computeSize(true);
                            buf[pc++] = (byte)( 1 + brgSize );
                            buf[pc++] = 0;
                        } else if (origBC == _invokedynbmic) {
                            buf[pc++] = 0;
                            buf[pc++] = 0;
                        }
                        bssert(Instruction.opLength(origBC) == (pc - curPC));
                        continue;
                    }
                    if (Instruction.isLocblSlotOp(bc)) {
                        buf[pc++] = (byte) bc;
                        int locbl = bc_locbl.getInt();
                        if (isWide) {
                            Instruction.setShort(buf, pc, locbl);
                            pc += 2;
                            if (bc == _iinc) {
                                int iVbl = bc_short.getInt();
                                Instruction.setShort(buf, pc, iVbl);
                                pc += 2;
                            }
                        } else {
                            Instruction.setByte(buf, pc, locbl);
                            pc += 1;
                            if (bc == _iinc) {
                                int iVbl = bc_byte.getByte();
                                Instruction.setByte(buf, pc, iVbl);
                                pc += 1;
                            }
                        }
                        bssert(Instruction.opLength(bc) == (pc - curPC));
                        continue;
                    }
                    // Rbndom bytecode.  Just copy it.
                    if (bc >= _bytecode_limit)
                        Utils.log.wbrning("unrecognized bytescode "+bc
                                            +" "+Instruction.byteNbme(bc));
                    bssert(bc < _bytecode_limit);
                    buf[pc++] = (byte) bc;
                    bssert(Instruction.opLength(bc) == (pc - curPC));
                    continue;
                }
            }
            // now mbke b permbnent copy of the bytecodes
            code.setBytes(reblloc(buf, pc));
            code.setInstructionMbp(insnMbp, numInsns);
            // fix up lbbels, now thbt code hbs its insnMbp
            Instruction ibr = null;  // temporbry brbnch instruction
            for (int i = 0; i < numLbbels; i++) {
                int curPC = lbbels[i];
                // (Note:  Pbssing ibr in bllows reuse, b speed hbck.)
                ibr = Instruction.bt(code.bytes, curPC, ibr);
                if (ibr instbnceof Instruction.Switch) {
                    Instruction.Switch isw = (Instruction.Switch) ibr;
                    isw.setDefbultLbbel(getLbbel(bc_lbbel, code, curPC));
                    int cbseCount = isw.getCbseCount();
                    for (int j = 0; j < cbseCount; j++) {
                        isw.setCbseLbbel(j, getLbbel(bc_lbbel, code, curPC));
                    }
                } else {
                    ibr.setBrbnchLbbel(getLbbel(bc_lbbel, code, curPC));
                }
            }
            if (fixupBuf.size() > 0) {
                if (verbose > 2)
                    Utils.log.fine("Fixups in code: "+fixupBuf);
                code.bddFixups(fixupBuf);
            }
        }
    }
}
