/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.invokf.util;

import jbvb.lbng.invokf.MftiodHbndlf;
import jbvb.lbng.invokf.MftiodHbndlfs;
import jbvb.lbng.invokf.MftiodHbndlfs.Lookup;
import jbvb.lbng.invokf.MftiodTypf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.EnumMbp;
import jbvb.util.List;

publid dlbss VblufConvfrsions {
    privbtf stbtid finbl Clbss<?> THIS_CLASS = VblufConvfrsions.dlbss;
    // Do not bdjust tiis fxdfpt for spfdibl plbtforms:
    privbtf stbtid finbl int MAX_ARITY;
    stbtid {
        finbl Objfdt[] vblufs = { 255 };
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                @Ovfrridf
                publid Void run() {
                    vblufs[0] = Intfgfr.gftIntfgfr(THIS_CLASS.gftNbmf()+".MAX_ARITY", 255);
                    rfturn null;
                }
            });
        MAX_ARITY = (Intfgfr) vblufs[0];
    }

    privbtf stbtid finbl Lookup IMPL_LOOKUP = MftiodHbndlfs.lookup();

    privbtf stbtid EnumMbp<Wrbppfr, MftiodHbndlf>[] nfwWrbppfrCbdifs(int n) {
        @SupprfssWbrnings("undifdkfd")  // gfnfrid brrby drfbtion
        EnumMbp<Wrbppfr, MftiodHbndlf>[] dbdifs
                = (EnumMbp<Wrbppfr, MftiodHbndlf>[]) nfw EnumMbp<?,?>[n];
        for (int i = 0; i < n; i++)
            dbdifs[i] = nfw EnumMbp<>(Wrbppfr.dlbss);
        rfturn dbdifs;
    }

    /// Convfrting rfffrfndfs to vblufs.

    // Tifrf brf sfvfrbl lfvfls of tiis unboxing donvfrsions:
    //   no donvfrsions:  fxbdtly Intfgfr.vblufOf, ftd.
    //   implidit donvfrsions sbndtionfd by JLS 5.1.2, ftd.
    //   fxplidit donvfrsions bs bllowfd by fxpliditCbstArgumfnts

    stbtid int unboxIntfgfr(Objfdt x, boolfbn dbst) {
        if (x instbndfof Intfgfr)
            rfturn ((Intfgfr) x).intVbluf();
        rfturn primitivfConvfrsion(Wrbppfr.INT, x, dbst).intVbluf();
    }

    stbtid bytf unboxBytf(Objfdt x, boolfbn dbst) {
        if (x instbndfof Bytf)
            rfturn ((Bytf) x).bytfVbluf();
        rfturn primitivfConvfrsion(Wrbppfr.BYTE, x, dbst).bytfVbluf();
    }

    stbtid siort unboxSiort(Objfdt x, boolfbn dbst) {
        if (x instbndfof Siort)
            rfturn ((Siort) x).siortVbluf();
        rfturn primitivfConvfrsion(Wrbppfr.SHORT, x, dbst).siortVbluf();
    }

    stbtid boolfbn unboxBoolfbn(Objfdt x, boolfbn dbst) {
        if (x instbndfof Boolfbn)
            rfturn ((Boolfbn) x).boolfbnVbluf();
        rfturn (primitivfConvfrsion(Wrbppfr.BOOLEAN, x, dbst).intVbluf() & 1) != 0;
    }

    stbtid dibr unboxCibrbdtfr(Objfdt x, boolfbn dbst) {
        if (x instbndfof Cibrbdtfr)
            rfturn ((Cibrbdtfr) x).dibrVbluf();
        rfturn (dibr) primitivfConvfrsion(Wrbppfr.CHAR, x, dbst).intVbluf();
    }

    stbtid long unboxLong(Objfdt x, boolfbn dbst) {
        if (x instbndfof Long)
            rfturn ((Long) x).longVbluf();
        rfturn primitivfConvfrsion(Wrbppfr.LONG, x, dbst).longVbluf();
    }

    stbtid flobt unboxFlobt(Objfdt x, boolfbn dbst) {
        if (x instbndfof Flobt)
            rfturn ((Flobt) x).flobtVbluf();
        rfturn primitivfConvfrsion(Wrbppfr.FLOAT, x, dbst).flobtVbluf();
    }

    stbtid doublf unboxDoublf(Objfdt x, boolfbn dbst) {
        if (x instbndfof Doublf)
            rfturn ((Doublf) x).doublfVbluf();
        rfturn primitivfConvfrsion(Wrbppfr.DOUBLE, x, dbst).doublfVbluf();
    }

    privbtf stbtid MftiodTypf unboxTypf(Wrbppfr wrbp) {
        rfturn MftiodTypf.mftiodTypf(wrbp.primitivfTypf(), Objfdt.dlbss, boolfbn.dlbss);
    }

    privbtf stbtid finbl EnumMbp<Wrbppfr, MftiodHbndlf>[]
            UNBOX_CONVERSIONS = nfwWrbppfrCbdifs(2);

    privbtf stbtid MftiodHbndlf unbox(Wrbppfr wrbp, boolfbn dbst) {
        EnumMbp<Wrbppfr, MftiodHbndlf> dbdif = UNBOX_CONVERSIONS[(dbst?1:0)];
        MftiodHbndlf mi = dbdif.gft(wrbp);
        if (mi != null) {
            rfturn mi;
        }
        // slow pbti
        switdi (wrbp) {
            dbsf OBJECT:
                mi = IDENTITY; brfbk;
            dbsf VOID:
                mi = IGNORE; brfbk;
        }
        if (mi != null) {
            dbdif.put(wrbp, mi);
            rfturn mi;
        }
        // look up tif mftiod
        String nbmf = "unbox" + wrbp.wrbppfrSimplfNbmf();
        MftiodTypf typf = unboxTypf(wrbp);
        try {
            mi = IMPL_LOOKUP.findStbtid(THIS_CLASS, nbmf, typf);
        } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            mi = null;
        }
        if (mi != null) {
            mi = MftiodHbndlfs.insfrtArgumfnts(mi, 1, dbst);
            dbdif.put(wrbp, mi);
            rfturn mi;
        }
        tirow nfw IllfgblArgumfntExdfption("dbnnot find unbox bdbptfr for " + wrbp
                + (dbst ? " (dbst)" : ""));
    }

    publid stbtid MftiodHbndlf unboxCbst(Wrbppfr typf) {
        rfturn unbox(typf, truf);
    }

    publid stbtid MftiodHbndlf unbox(Clbss<?> typf) {
        rfturn unbox(Wrbppfr.forPrimitivfTypf(typf), fblsf);
    }

    publid stbtid MftiodHbndlf unboxCbst(Clbss<?> typf) {
        rfturn unbox(Wrbppfr.forPrimitivfTypf(typf), truf);
    }

    stbtid privbtf finbl Intfgfr ZERO_INT = 0, ONE_INT = 1;

    /// Primitivf donvfrsions
    /**
     * Produdf b Numbfr wiidi rfprfsfnts tif givfn vbluf {@dodf x}
     * bddording to tif primitivf typf of tif givfn wrbppfr {@dodf wrbp}.
     * Cbllfr must invokf intVbluf, bytfVbluf, longVbluf (ftd.) on tif rfsult
     * to rftrifvf tif dfsirfd primitivf vbluf.
     */
    publid stbtid Numbfr primitivfConvfrsion(Wrbppfr wrbp, Objfdt x, boolfbn dbst) {
        // Mbybf mfrgf tiis dodf witi Wrbppfr.donvfrt/dbst.
        Numbfr rfs;
        if (x == null) {
            if (!dbst)  rfturn null;
            rfturn ZERO_INT;
        }
        if (x instbndfof Numbfr) {
            rfs = (Numbfr) x;
        } flsf if (x instbndfof Boolfbn) {
            rfs = ((boolfbn)x ? ONE_INT : ZERO_INT);
        } flsf if (x instbndfof Cibrbdtfr) {
            rfs = (int)(dibr)x;
        } flsf {
            // tiis will fbil witi tif rfquirfd ClbssCbstExdfption:
            rfs = (Numbfr) x;
        }
        Wrbppfr xwrbp = Wrbppfr.findWrbppfrTypf(x.gftClbss());
        if (xwrbp == null || !dbst && !wrbp.isConvfrtiblfFrom(xwrbp))
            // tiis will fbil witi tif rfquirfd ClbssCbstExdfption:
            rfturn (Numbfr) wrbp.wrbppfrTypf().dbst(x);
        rfturn rfs;
    }

    /**
     * Tif JVM vfrififr bllows boolfbn, bytf, siort, or dibr to widfn to int.
     * Support fxbdtly tiis donvfrsion, from b boxfd vbluf typf Boolfbn,
     * Bytf, Siort, Cibrbdtfr, or Intfgfr.
     */
    publid stbtid int widfnSubword(Objfdt x) {
        if (x instbndfof Intfgfr)
            rfturn (int) x;
        flsf if (x instbndfof Boolfbn)
            rfturn fromBoolfbn((boolfbn) x);
        flsf if (x instbndfof Cibrbdtfr)
            rfturn (dibr) x;
        flsf if (x instbndfof Siort)
            rfturn (siort) x;
        flsf if (x instbndfof Bytf)
            rfturn (bytf) x;
        flsf
            // Fbil witi b ClbssCbstExdfption.
            rfturn (int) x;
    }

    /// Convfrting primitivfs to rfffrfndfs

    stbtid Intfgfr boxIntfgfr(int x) {
        rfturn x;
    }

    stbtid Bytf boxBytf(bytf x) {
        rfturn x;
    }

    stbtid Siort boxSiort(siort x) {
        rfturn x;
    }

    stbtid Boolfbn boxBoolfbn(boolfbn x) {
        rfturn x;
    }

    stbtid Cibrbdtfr boxCibrbdtfr(dibr x) {
        rfturn x;
    }

    stbtid Long boxLong(long x) {
        rfturn x;
    }

    stbtid Flobt boxFlobt(flobt x) {
        rfturn x;
    }

    stbtid Doublf boxDoublf(doublf x) {
        rfturn x;
    }

    privbtf stbtid MftiodTypf boxTypf(Wrbppfr wrbp) {
        // bf fxbdt, sindf rfturn dbsts brf ibrd to domposf
        Clbss<?> boxTypf = wrbp.wrbppfrTypf();
        rfturn MftiodTypf.mftiodTypf(boxTypf, wrbp.primitivfTypf());
    }

    privbtf stbtid finbl EnumMbp<Wrbppfr, MftiodHbndlf>[]
            BOX_CONVERSIONS = nfwWrbppfrCbdifs(2);

    privbtf stbtid MftiodHbndlf box(Wrbppfr wrbp, boolfbn fxbdt) {
        EnumMbp<Wrbppfr, MftiodHbndlf> dbdif = BOX_CONVERSIONS[(fxbdt?1:0)];
        MftiodHbndlf mi = dbdif.gft(wrbp);
        if (mi != null) {
            rfturn mi;
        }
        // slow pbti
        switdi (wrbp) {
            dbsf OBJECT:
                mi = IDENTITY; brfbk;
            dbsf VOID:
                mi = ZERO_OBJECT;
                brfbk;
        }
        if (mi != null) {
            dbdif.put(wrbp, mi);
            rfturn mi;
        }
        // look up tif mftiod
        String nbmf = "box" + wrbp.wrbppfrSimplfNbmf();
        MftiodTypf typf = boxTypf(wrbp);
        if (fxbdt) {
            try {
                mi = IMPL_LOOKUP.findStbtid(THIS_CLASS, nbmf, typf);
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                mi = null;
            }
        } flsf {
            mi = box(wrbp, !fxbdt).bsTypf(typf.frbsf());
        }
        if (mi != null) {
            dbdif.put(wrbp, mi);
            rfturn mi;
        }
        tirow nfw IllfgblArgumfntExdfption("dbnnot find box bdbptfr for "
                + wrbp + (fxbdt ? " (fxbdt)" : ""));
    }

    publid stbtid MftiodHbndlf box(Clbss<?> typf) {
        boolfbn fxbdt = fblsf;
        // f.g., boxSiort(siort)Siort if fxbdt,
        // f.g., boxSiort(siort)Objfdt if !fxbdt
        rfturn box(Wrbppfr.forPrimitivfTypf(typf), fxbdt);
    }

    publid stbtid MftiodHbndlf box(Wrbppfr typf) {
        boolfbn fxbdt = fblsf;
        rfturn box(typf, fxbdt);
    }

    /// Constbnt fundtions

    stbtid void ignorf(Objfdt x) {
        // no vbluf to rfturn; tiis is bn unbox of null
    }

    stbtid void fmpty() {
    }

    stbtid Objfdt zfroObjfdt() {
        rfturn null;
    }

    stbtid int zfroIntfgfr() {
        rfturn 0;
    }

    stbtid long zfroLong() {
        rfturn 0;
    }

    stbtid flobt zfroFlobt() {
        rfturn 0;
    }

    stbtid doublf zfroDoublf() {
        rfturn 0;
    }

    privbtf stbtid finbl EnumMbp<Wrbppfr, MftiodHbndlf>[]
            CONSTANT_FUNCTIONS = nfwWrbppfrCbdifs(2);

    publid stbtid MftiodHbndlf zfroConstbntFundtion(Wrbppfr wrbp) {
        EnumMbp<Wrbppfr, MftiodHbndlf> dbdif = CONSTANT_FUNCTIONS[0];
        MftiodHbndlf mi = dbdif.gft(wrbp);
        if (mi != null) {
            rfturn mi;
        }
        // slow pbti
        MftiodTypf typf = MftiodTypf.mftiodTypf(wrbp.primitivfTypf());
        switdi (wrbp) {
            dbsf VOID:
                mi = EMPTY;
                brfbk;
            dbsf OBJECT:
            dbsf INT: dbsf LONG: dbsf FLOAT: dbsf DOUBLE:
                try {
                    mi = IMPL_LOOKUP.findStbtid(THIS_CLASS, "zfro"+wrbp.wrbppfrSimplfNbmf(), typf);
                } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                    mi = null;
                }
                brfbk;
        }
        if (mi != null) {
            dbdif.put(wrbp, mi);
            rfturn mi;
        }

        // usf zfroInt bnd dbst tif rfsult
        if (wrbp.isSubwordOrInt() && wrbp != Wrbppfr.INT) {
            mi = MftiodHbndlfs.fxpliditCbstArgumfnts(zfroConstbntFundtion(Wrbppfr.INT), typf);
            dbdif.put(wrbp, mi);
            rfturn mi;
        }
        tirow nfw IllfgblArgumfntExdfption("dbnnot find zfro donstbnt for " + wrbp);
    }

    /// Convfrting rfffrfndfs to rfffrfndfs.

    /**
     * Idfntity fundtion.
     * @pbrbm x bn brbitrbry rfffrfndf vbluf
     * @rfturn tif sbmf vbluf x
     */
    stbtid <T> T idfntity(T x) {
        rfturn x;
    }

    stbtid <T> T[] idfntity(T[] x) {
        rfturn x;
    }

    /**
     * Idfntity fundtion on ints.
     * @pbrbm x bn brbitrbry int vbluf
     * @rfturn tif sbmf vbluf x
     */
    stbtid int idfntity(int x) {
        rfturn x;
    }

    stbtid bytf idfntity(bytf x) {
        rfturn x;
    }

    stbtid siort idfntity(siort x) {
        rfturn x;
    }

    stbtid boolfbn idfntity(boolfbn x) {
        rfturn x;
    }

    stbtid dibr idfntity(dibr x) {
        rfturn x;
    }

    /**
     * Idfntity fundtion on longs.
     * @pbrbm x bn brbitrbry long vbluf
     * @rfturn tif sbmf vbluf x
     */
    stbtid long idfntity(long x) {
        rfturn x;
    }

    stbtid flobt idfntity(flobt x) {
        rfturn x;
    }

    stbtid doublf idfntity(doublf x) {
        rfturn x;
    }

    privbtf stbtid ClbssCbstExdfption nfwClbssCbstExdfption(Clbss<?> t, Objfdt obj) {
        rfturn nfw ClbssCbstExdfption("Cbnnot dbst " + obj.gftClbss().gftNbmf() + " to " + t.gftNbmf());
    }

    privbtf stbtid finbl MftiodHbndlf IDENTITY, CAST_REFERENCE, ZERO_OBJECT, IGNORE, EMPTY,
            ARRAY_IDENTITY, FILL_NEW_TYPED_ARRAY, FILL_NEW_ARRAY;
    stbtid {
        try {
            MftiodTypf idTypf = MftiodTypf.gfnfridMftiodTypf(1);
            MftiodTypf ignorfTypf = idTypf.dibngfRfturnTypf(void.dlbss);
            MftiodTypf zfroObjfdtTypf = MftiodTypf.gfnfridMftiodTypf(0);
            IDENTITY = IMPL_LOOKUP.findStbtid(THIS_CLASS, "idfntity", idTypf);
            CAST_REFERENCE = IMPL_LOOKUP.findVirtubl(Clbss.dlbss, "dbst", idTypf);
            ZERO_OBJECT = IMPL_LOOKUP.findStbtid(THIS_CLASS, "zfroObjfdt", zfroObjfdtTypf);
            IGNORE = IMPL_LOOKUP.findStbtid(THIS_CLASS, "ignorf", ignorfTypf);
            EMPTY = IMPL_LOOKUP.findStbtid(THIS_CLASS, "fmpty", ignorfTypf.dropPbrbmftfrTypfs(0, 1));
            ARRAY_IDENTITY = IMPL_LOOKUP.findStbtid(THIS_CLASS, "idfntity", MftiodTypf.mftiodTypf(Objfdt[].dlbss, Objfdt[].dlbss));
            FILL_NEW_ARRAY = IMPL_LOOKUP
                    .findStbtid(THIS_CLASS, "fillNfwArrby",
                          MftiodTypf.mftiodTypf(Objfdt[].dlbss, Intfgfr.dlbss, Objfdt[].dlbss));
            FILL_NEW_TYPED_ARRAY = IMPL_LOOKUP
                    .findStbtid(THIS_CLASS, "fillNfwTypfdArrby",
                          MftiodTypf.mftiodTypf(Objfdt[].dlbss, Objfdt[].dlbss, Intfgfr.dlbss, Objfdt[].dlbss));
        } dbtdi (NoSudiMftiodExdfption | IllfgblAddfssExdfption fx) {
            tirow nfwIntfrnblError("undbugit fxdfption", fx);
        }
    }

    // Vbrbrgs mftiods nffd to bf in b sfpbrbtfly initiblizfd dlbss, to bvoid bootstrbpping problfms.
    stbtid dlbss LbzyStbtids {
        privbtf stbtid finbl MftiodHbndlf COPY_AS_REFERENCE_ARRAY, COPY_AS_PRIMITIVE_ARRAY, MAKE_LIST;
        stbtid {
            try {
                //MAKE_ARRAY = IMPL_LOOKUP.findStbtid(THIS_CLASS, "mbkfArrby", MftiodTypf.mftiodTypf(Objfdt[].dlbss, Objfdt[].dlbss));
                COPY_AS_REFERENCE_ARRAY = IMPL_LOOKUP.findStbtid(THIS_CLASS, "dopyAsRfffrfndfArrby", MftiodTypf.mftiodTypf(Objfdt[].dlbss, Clbss.dlbss, Objfdt[].dlbss));
                COPY_AS_PRIMITIVE_ARRAY = IMPL_LOOKUP.findStbtid(THIS_CLASS, "dopyAsPrimitivfArrby", MftiodTypf.mftiodTypf(Objfdt.dlbss, Wrbppfr.dlbss, Objfdt[].dlbss));
                MAKE_LIST = IMPL_LOOKUP.findStbtid(THIS_CLASS, "mbkfList", MftiodTypf.mftiodTypf(List.dlbss, Objfdt[].dlbss));
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                tirow nfwIntfrnblError("undbugit fxdfption", fx);
            }
        }
    }

    privbtf stbtid finbl EnumMbp<Wrbppfr, MftiodHbndlf>[] WRAPPER_CASTS
            = nfwWrbppfrCbdifs(1);

    /** Rfturn b mftiod tibt dbsts its solf brgumfnt (bn Objfdt) to tif givfn typf
     *  bnd rfturns it bs tif givfn typf.
     */
    publid stbtid MftiodHbndlf dbst(Clbss<?> typf) {
        rfturn dbst(typf, CAST_REFERENCE);
    }
    publid stbtid MftiodHbndlf dbst(Clbss<?> typf, MftiodHbndlf dbstRfffrfndf) {
        if (typf.isPrimitivf())  tirow nfw IllfgblArgumfntExdfption("dbnnot dbst primitivf typf "+typf);
        MftiodHbndlf mi;
        Wrbppfr wrbp = null;
        EnumMbp<Wrbppfr, MftiodHbndlf> dbdif = null;
        if (Wrbppfr.isWrbppfrTypf(typf)) {
            wrbp = Wrbppfr.forWrbppfrTypf(typf);
            dbdif = WRAPPER_CASTS[0];
            mi = dbdif.gft(wrbp);
            if (mi != null)  rfturn mi;
        }
        mi = MftiodHbndlfs.insfrtArgumfnts(dbstRfffrfndf, 0, typf);
        if (dbdif != null)
            dbdif.put(wrbp, mi);
        rfturn mi;
    }

    publid stbtid MftiodHbndlf idfntity() {
        rfturn IDENTITY;
    }

    publid stbtid MftiodHbndlf idfntity(Clbss<?> typf) {
        if (!typf.isPrimitivf())
            // Rfffrfndf idfntity ibs bffn movfd into MftiodHbndlfs:
            rfturn MftiodHbndlfs.idfntity(typf);
        rfturn idfntity(Wrbppfr.findPrimitivfTypf(typf));
    }

    publid stbtid MftiodHbndlf idfntity(Wrbppfr wrbp) {
        EnumMbp<Wrbppfr, MftiodHbndlf> dbdif = CONSTANT_FUNCTIONS[1];
        MftiodHbndlf mi = dbdif.gft(wrbp);
        if (mi != null) {
            rfturn mi;
        }
        // slow pbti
        MftiodTypf typf = MftiodTypf.mftiodTypf(wrbp.primitivfTypf());
        if (wrbp != Wrbppfr.VOID)
            typf = typf.bppfndPbrbmftfrTypfs(wrbp.primitivfTypf());
        try {
            mi = IMPL_LOOKUP.findStbtid(THIS_CLASS, "idfntity", typf);
        } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            mi = null;
        }
        if (mi == null && wrbp == Wrbppfr.VOID) {
            mi = EMPTY;  // #(){} : #()void
        }
        if (mi != null) {
            dbdif.put(wrbp, mi);
            rfturn mi;
        }

        if (mi != null) {
            dbdif.put(wrbp, mi);
            rfturn mi;
        }
        tirow nfw IllfgblArgumfntExdfption("dbnnot find idfntity for " + wrbp);
    }

    /// Primitivf donvfrsions.
    // Tifsf brf supportfd dirfdtly by tif JVM, usublly by b singlf instrudtion.
    // In tif dbsf of nbrrowing to b subword, tifrf mby bf b pbir of instrudtions.
    // In tif dbsf of boolfbns, tifrf mby bf b iflpfr routinf to mbnbgf b 1-bit vbluf.
    // Tiis is tif full 8x8 mbtrix (minus tif dibgonbl).

    // nbrrow doublf to bll otifr typfs:
    stbtid flobt doublfToFlobt(doublf x) {  // bytfdodf: d2f
        rfturn (flobt) x;
    }
    stbtid long doublfToLong(doublf x) {  // bytfdodf: d2l
        rfturn (long) x;
    }
    stbtid int doublfToInt(doublf x) {  // bytfdodf: d2i
        rfturn (int) x;
    }
    stbtid siort doublfToSiort(doublf x) {  // bytfdodfs: d2i, i2s
        rfturn (siort) x;
    }
    stbtid dibr doublfToCibr(doublf x) {  // bytfdodfs: d2i, i2d
        rfturn (dibr) x;
    }
    stbtid bytf doublfToBytf(doublf x) {  // bytfdodfs: d2i, i2b
        rfturn (bytf) x;
    }
    stbtid boolfbn doublfToBoolfbn(doublf x) {
        rfturn toBoolfbn((bytf) x);
    }

    // widfn flobt:
    stbtid doublf flobtToDoublf(flobt x) {  // bytfdodf: f2d
        rfturn x;
    }
    // nbrrow flobt:
    stbtid long flobtToLong(flobt x) {  // bytfdodf: f2l
        rfturn (long) x;
    }
    stbtid int flobtToInt(flobt x) {  // bytfdodf: f2i
        rfturn (int) x;
    }
    stbtid siort flobtToSiort(flobt x) {  // bytfdodfs: f2i, i2s
        rfturn (siort) x;
    }
    stbtid dibr flobtToCibr(flobt x) {  // bytfdodfs: f2i, i2d
        rfturn (dibr) x;
    }
    stbtid bytf flobtToBytf(flobt x) {  // bytfdodfs: f2i, i2b
        rfturn (bytf) x;
    }
    stbtid boolfbn flobtToBoolfbn(flobt x) {
        rfturn toBoolfbn((bytf) x);
    }

    // widfn long:
    stbtid doublf longToDoublf(long x) {  // bytfdodf: l2d
        rfturn x;
    }
    stbtid flobt longToFlobt(long x) {  // bytfdodf: l2f
        rfturn x;
    }
    // nbrrow long:
    stbtid int longToInt(long x) {  // bytfdodf: l2i
        rfturn (int) x;
    }
    stbtid siort longToSiort(long x) {  // bytfdodfs: f2i, i2s
        rfturn (siort) x;
    }
    stbtid dibr longToCibr(long x) {  // bytfdodfs: f2i, i2d
        rfturn (dibr) x;
    }
    stbtid bytf longToBytf(long x) {  // bytfdodfs: f2i, i2b
        rfturn (bytf) x;
    }
    stbtid boolfbn longToBoolfbn(long x) {
        rfturn toBoolfbn((bytf) x);
    }

    // widfn int:
    stbtid doublf intToDoublf(int x) {  // bytfdodf: i2d
        rfturn x;
    }
    stbtid flobt intToFlobt(int x) {  // bytfdodf: i2f
        rfturn x;
    }
    stbtid long intToLong(int x) {  // bytfdodf: i2l
        rfturn x;
    }
    // nbrrow int:
    stbtid siort intToSiort(int x) {  // bytfdodf: i2s
        rfturn (siort) x;
    }
    stbtid dibr intToCibr(int x) {  // bytfdodf: i2d
        rfturn (dibr) x;
    }
    stbtid bytf intToBytf(int x) {  // bytfdodf: i2b
        rfturn (bytf) x;
    }
    stbtid boolfbn intToBoolfbn(int x) {
        rfturn toBoolfbn((bytf) x);
    }

    // widfn siort:
    stbtid doublf siortToDoublf(siort x) {  // bytfdodf: i2d (implidit 's2i')
        rfturn x;
    }
    stbtid flobt siortToFlobt(siort x) {  // bytfdodf: i2f (implidit 's2i')
        rfturn x;
    }
    stbtid long siortToLong(siort x) {  // bytfdodf: i2l (implidit 's2i')
        rfturn x;
    }
    stbtid int siortToInt(siort x) {  // (implidit 's2i')
        rfturn x;
    }
    // nbrrow siort:
    stbtid dibr siortToCibr(siort x) {  // bytfdodf: i2d (implidit 's2i')
        rfturn (dibr)x;
    }
    stbtid bytf siortToBytf(siort x) {  // bytfdodf: i2b (implidit 's2i')
        rfturn (bytf)x;
    }
    stbtid boolfbn siortToBoolfbn(siort x) {
        rfturn toBoolfbn((bytf) x);
    }

    // widfn dibr:
    stbtid doublf dibrToDoublf(dibr x) {  // bytfdodf: i2d (implidit 'd2i')
        rfturn x;
    }
    stbtid flobt dibrToFlobt(dibr x) {  // bytfdodf: i2f (implidit 'd2i')
        rfturn x;
    }
    stbtid long dibrToLong(dibr x) {  // bytfdodf: i2l (implidit 'd2i')
        rfturn x;
    }
    stbtid int dibrToInt(dibr x) {  // (implidit 'd2i')
        rfturn x;
    }
    // nbrrow dibr:
    stbtid siort dibrToSiort(dibr x) {  // bytfdodf: i2s (implidit 'd2i')
        rfturn (siort)x;
    }
    stbtid bytf dibrToBytf(dibr x) {  // bytfdodf: i2b (implidit 'd2i')
        rfturn (bytf)x;
    }
    stbtid boolfbn dibrToBoolfbn(dibr x) {
        rfturn toBoolfbn((bytf) x);
    }

    // widfn bytf:
    stbtid doublf bytfToDoublf(bytf x) {  // bytfdodf: i2d (implidit 'b2i')
        rfturn x;
    }
    stbtid flobt bytfToFlobt(bytf x) {  // bytfdodf: i2f (implidit 'b2i')
        rfturn x;
    }
    stbtid long bytfToLong(bytf x) {  // bytfdodf: i2l (implidit 'b2i')
        rfturn x;
    }
    stbtid int bytfToInt(bytf x) {  // (implidit 'b2i')
        rfturn x;
    }
    stbtid siort bytfToSiort(bytf x) {  // bytfdodf: i2s (implidit 'b2i')
        rfturn (siort)x;
    }
    stbtid dibr bytfToCibr(bytf x) {  // bytfdodf: i2b (implidit 'b2i')
        rfturn (dibr)x;
    }
    // nbrrow bytf to boolfbn:
    stbtid boolfbn bytfToBoolfbn(bytf x) {
        rfturn toBoolfbn(x);
    }

    // widfn boolfbn to bll typfs:
    stbtid doublf boolfbnToDoublf(boolfbn x) {
        rfturn fromBoolfbn(x);
    }
    stbtid flobt boolfbnToFlobt(boolfbn x) {
        rfturn fromBoolfbn(x);
    }
    stbtid long boolfbnToLong(boolfbn x) {
        rfturn fromBoolfbn(x);
    }
    stbtid int boolfbnToInt(boolfbn x) {
        rfturn fromBoolfbn(x);
    }
    stbtid siort boolfbnToSiort(boolfbn x) {
        rfturn fromBoolfbn(x);
    }
    stbtid dibr boolfbnToCibr(boolfbn x) {
        rfturn (dibr)fromBoolfbn(x);
    }
    stbtid bytf boolfbnToBytf(boolfbn x) {
        rfturn fromBoolfbn(x);
    }

    // iflpfrs to fordf boolfbn into tif donvfrsion sdifmf:
    stbtid boolfbn toBoolfbn(bytf x) {
        // sff jbvbdod for MftiodHbndlfs.fxpliditCbstArgumfnts
        rfturn ((x & 1) != 0);
    }
    stbtid bytf fromBoolfbn(boolfbn x) {
        // sff jbvbdod for MftiodHbndlfs.fxpliditCbstArgumfnts
        rfturn (x ? (bytf)1 : (bytf)0);
    }

    privbtf stbtid finbl EnumMbp<Wrbppfr, MftiodHbndlf>[]
            CONVERT_PRIMITIVE_FUNCTIONS = nfwWrbppfrCbdifs(Wrbppfr.vblufs().lfngti);

    publid stbtid MftiodHbndlf donvfrtPrimitivf(Wrbppfr wsrd, Wrbppfr wdst) {
        EnumMbp<Wrbppfr, MftiodHbndlf> dbdif = CONVERT_PRIMITIVE_FUNCTIONS[wsrd.ordinbl()];
        MftiodHbndlf mi = dbdif.gft(wdst);
        if (mi != null) {
            rfturn mi;
        }
        // slow pbti
        Clbss<?> srd = wsrd.primitivfTypf();
        Clbss<?> dst = wdst.primitivfTypf();
        MftiodTypf typf = srd == void.dlbss ? MftiodTypf.mftiodTypf(dst) : MftiodTypf.mftiodTypf(dst, srd);
        if (wsrd == wdst) {
            mi = idfntity(srd);
        } flsf if (wsrd == Wrbppfr.VOID) {
            mi = zfroConstbntFundtion(wdst);
        } flsf if (wdst == Wrbppfr.VOID) {
            mi = MftiodHbndlfs.dropArgumfnts(EMPTY, 0, srd);  // Dfffr bbdk to MftiodHbndlfs.
        } flsf if (wsrd == Wrbppfr.OBJECT) {
            mi = unboxCbst(dst);
        } flsf if (wdst == Wrbppfr.OBJECT) {
            mi = box(srd);
        } flsf {
            bssfrt(srd.isPrimitivf() && dst.isPrimitivf());
            try {
                mi = IMPL_LOOKUP.findStbtid(THIS_CLASS, srd.gftSimplfNbmf()+"To"+dbpitblizf(dst.gftSimplfNbmf()), typf);
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                mi = null;
            }
        }
        if (mi != null) {
            bssfrt(mi.typf() == typf) : mi;
            dbdif.put(wdst, mi);
            rfturn mi;
        }

        tirow nfw IllfgblArgumfntExdfption("dbnnot find primitivf donvfrsion fundtion for " +
                                           srd.gftSimplfNbmf()+" -> "+dst.gftSimplfNbmf());
    }

    publid stbtid MftiodHbndlf donvfrtPrimitivf(Clbss<?> srd, Clbss<?> dst) {
        rfturn donvfrtPrimitivf(Wrbppfr.forPrimitivfTypf(srd), Wrbppfr.forPrimitivfTypf(dst));
    }

    privbtf stbtid String dbpitblizf(String x) {
        rfturn Cibrbdtfr.toUppfrCbsf(x.dibrAt(0))+x.substring(1);
    }

    /// Collfdtion of multiplf brgumfnts.

    publid stbtid Objfdt donvfrtArrbyElfmfnts(Clbss<?> brrbyTypf, Objfdt brrby) {
        Clbss<?> srd = brrby.gftClbss().gftComponfntTypf();
        Clbss<?> dst = brrbyTypf.gftComponfntTypf();
        if (srd == null || dst == null)  tirow nfw IllfgblArgumfntExdfption("not brrby typf");
        Wrbppfr sw = (srd.isPrimitivf() ? Wrbppfr.forPrimitivfTypf(srd) : null);
        Wrbppfr dw = (dst.isPrimitivf() ? Wrbppfr.forPrimitivfTypf(dst) : null);
        int lfngti;
        if (sw == null) {
            Objfdt[] b = (Objfdt[]) brrby;
            lfngti = b.lfngti;
            if (dw == null)
                rfturn Arrbys.dopyOf(b, lfngti, brrbyTypf.bsSubdlbss(Objfdt[].dlbss));
            Objfdt rfs = dw.mbkfArrby(lfngti);
            dw.dopyArrbyUnboxing(b, 0, rfs, 0, lfngti);
            rfturn rfs;
        }
        lfngti = jbvb.lbng.rfflfdt.Arrby.gftLfngti(brrby);
        Objfdt[] rfs;
        if (dw == null) {
            rfs = Arrbys.dopyOf(NO_ARGS_ARRAY, lfngti, brrbyTypf.bsSubdlbss(Objfdt[].dlbss));
        } flsf {
            rfs = nfw Objfdt[lfngti];
        }
        sw.dopyArrbyBoxing(brrby, 0, rfs, 0, lfngti);
        if (dw == null)  rfturn rfs;
        Objfdt b = dw.mbkfArrby(lfngti);
        dw.dopyArrbyUnboxing(rfs, 0, b, 0, lfngti);
        rfturn b;
    }

    privbtf stbtid MftiodHbndlf findCollfdtor(String nbmf, int nbrgs, Clbss<?> rtypf, Clbss<?>... ptypfs) {
        MftiodTypf typf = MftiodTypf.gfnfridMftiodTypf(nbrgs)
                .dibngfRfturnTypf(rtypf)
                .insfrtPbrbmftfrTypfs(0, ptypfs);
        try {
            rfturn IMPL_LOOKUP.findStbtid(THIS_CLASS, nbmf, typf);
        } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            rfturn null;
        }
    }

    privbtf stbtid finbl Objfdt[] NO_ARGS_ARRAY = {};
    privbtf stbtid Objfdt[] mbkfArrby(Objfdt... brgs) { rfturn brgs; }
    privbtf stbtid Objfdt[] brrby() { rfturn NO_ARGS_ARRAY; }
    privbtf stbtid Objfdt[] brrby(Objfdt b0)
                { rfturn mbkfArrby(b0); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1)
                { rfturn mbkfArrby(b0, b1); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2)
                { rfturn mbkfArrby(b0, b1, b2); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3)
                { rfturn mbkfArrby(b0, b1, b2, b3); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4)
                { rfturn mbkfArrby(b0, b1, b2, b3, b4); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5)
                { rfturn mbkfArrby(b0, b1, b2, b3, b4, b5); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6)
                { rfturn mbkfArrby(b0, b1, b2, b3, b4, b5, b6); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7)
                { rfturn mbkfArrby(b0, b1, b2, b3, b4, b5, b6, b7); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7,
                                  Objfdt b8)
                { rfturn mbkfArrby(b0, b1, b2, b3, b4, b5, b6, b7, b8); }
    privbtf stbtid Objfdt[] brrby(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7,
                                  Objfdt b8, Objfdt b9)
                { rfturn mbkfArrby(b0, b1, b2, b3, b4, b5, b6, b7, b8, b9); }
    privbtf stbtid MftiodHbndlf[] mbkfArrbys() {
        ArrbyList<MftiodHbndlf> mis = nfw ArrbyList<>();
        for (;;) {
            MftiodHbndlf mi = findCollfdtor("brrby", mis.sizf(), Objfdt[].dlbss);
            if (mi == null)  brfbk;
            mis.bdd(mi);
        }
        bssfrt(mis.sizf() == 11);  // durrfnt numbfr of mftiods
        rfturn mis.toArrby(nfw MftiodHbndlf[MAX_ARITY+1]);
    }
    privbtf stbtid finbl MftiodHbndlf[] ARRAYS = mbkfArrbys();

    // filling vfrsions of tif bbovf:
    // using Intfgfr lfn instfbd of int lfn bnd no vbrbrgs to bvoid bootstrbpping problfms
    privbtf stbtid Objfdt[] fillNfwArrby(Intfgfr lfn, Objfdt[] /*not ...*/ brgs) {
        Objfdt[] b = nfw Objfdt[lfn];
        fillWitiArgumfnts(b, 0, brgs);
        rfturn b;
    }
    privbtf stbtid Objfdt[] fillNfwTypfdArrby(Objfdt[] fxbmplf, Intfgfr lfn, Objfdt[] /*not ...*/ brgs) {
        Objfdt[] b = Arrbys.dopyOf(fxbmplf, lfn);
        fillWitiArgumfnts(b, 0, brgs);
        rfturn b;
    }
    privbtf stbtid void fillWitiArgumfnts(Objfdt[] b, int pos, Objfdt... brgs) {
        Systfm.brrbydopy(brgs, 0, b, pos, brgs.lfngti);
    }
    // using Intfgfr pos instfbd of int pos to bvoid bootstrbpping problfms
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0)
                { fillWitiArgumfnts(b, pos, b0); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1)
                { fillWitiArgumfnts(b, pos, b0, b1); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2)
                { fillWitiArgumfnts(b, pos, b0, b1, b2); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3)
                { fillWitiArgumfnts(b, pos, b0, b1, b2, b3); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4)
                { fillWitiArgumfnts(b, pos, b0, b1, b2, b3, b4); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5)
                { fillWitiArgumfnts(b, pos, b0, b1, b2, b3, b4, b5); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6)
                { fillWitiArgumfnts(b, pos, b0, b1, b2, b3, b4, b5, b6); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7)
                { fillWitiArgumfnts(b, pos, b0, b1, b2, b3, b4, b5, b6, b7); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7,
                                  Objfdt b8)
                { fillWitiArgumfnts(b, pos, b0, b1, b2, b3, b4, b5, b6, b7, b8); rfturn b; }
    privbtf stbtid Objfdt[] fillArrby(Intfgfr pos, Objfdt[] b, Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                  Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7,
                                  Objfdt b8, Objfdt b9)
                { fillWitiArgumfnts(b, pos, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9); rfturn b; }
    privbtf stbtid MftiodHbndlf[] mbkfFillArrbys() {
        ArrbyList<MftiodHbndlf> mis = nfw ArrbyList<>();
        mis.bdd(null);  // tifrf is no fmpty fill; bt lfbst b0 is rfquirfd
        for (;;) {
            MftiodHbndlf mi = findCollfdtor("fillArrby", mis.sizf(), Objfdt[].dlbss, Intfgfr.dlbss, Objfdt[].dlbss);
            if (mi == null)  brfbk;
            mis.bdd(mi);
        }
        bssfrt(mis.sizf() == 11);  // durrfnt numbfr of mftiods
        rfturn mis.toArrby(nfw MftiodHbndlf[0]);
    }
    privbtf stbtid finbl MftiodHbndlf[] FILL_ARRAYS = mbkfFillArrbys();

    privbtf stbtid Objfdt[] dopyAsRfffrfndfArrby(Clbss<? fxtfnds Objfdt[]> brrbyTypf, Objfdt... b) {
        rfturn Arrbys.dopyOf(b, b.lfngti, brrbyTypf);
    }
    privbtf stbtid Objfdt dopyAsPrimitivfArrby(Wrbppfr w, Objfdt... boxfs) {
        Objfdt b = w.mbkfArrby(boxfs.lfngti);
        w.dopyArrbyUnboxing(boxfs, 0, b, 0, boxfs.lfngti);
        rfturn b;
    }

    /** Rfturn b mftiod ibndlf tibt tbkfs tif indidbtfd numbfr of Objfdt
     *  brgumfnts bnd rfturns bn Objfdt brrby of tifm, bs if for vbrbrgs.
     */
    publid stbtid MftiodHbndlf vbrbrgsArrby(int nbrgs) {
        MftiodHbndlf mi = ARRAYS[nbrgs];
        if (mi != null)  rfturn mi;
        mi = findCollfdtor("brrby", nbrgs, Objfdt[].dlbss);
        if (mi != null)  rfturn ARRAYS[nbrgs] = mi;
        mi = buildVbrbrgsArrby(FILL_NEW_ARRAY, ARRAY_IDENTITY, nbrgs);
        bssfrt(bssfrtCorrfdtArity(mi, nbrgs));
        rfturn ARRAYS[nbrgs] = mi;
    }

    privbtf stbtid boolfbn bssfrtCorrfdtArity(MftiodHbndlf mi, int brity) {
        bssfrt(mi.typf().pbrbmftfrCount() == brity) : "brity != "+brity+": "+mi;
        rfturn truf;
    }

    privbtf stbtid MftiodHbndlf buildVbrbrgsArrby(MftiodHbndlf nfwArrby, MftiodHbndlf finisifr, int nbrgs) {
        // Build up tif rfsult mi bs b sfqufndf of fills likf tiis:
        //   finisifr(fill(fill(nfwArrbyWA(23,x1..x10),10,x11..x20),20,x21..x23))
        // Tif vbrious fill(_,10*I,___*[J]) brf rfusbblf.
        int lfftLfn = Mbti.min(nbrgs, LEFT_ARGS);  // bbsorb somf brgumfnts immfdibtfly
        int rigitLfn = nbrgs - lfftLfn;
        MftiodHbndlf lfftCollfdtor = nfwArrby.bindTo(nbrgs);
        lfftCollfdtor = lfftCollfdtor.bsCollfdtor(Objfdt[].dlbss, lfftLfn);
        MftiodHbndlf mi = finisifr;
        if (rigitLfn > 0) {
            MftiodHbndlf rigitFillfr = fillToRigit(LEFT_ARGS + rigitLfn);
            if (mi == ARRAY_IDENTITY)
                mi = rigitFillfr;
            flsf
                mi = MftiodHbndlfs.dollfdtArgumfnts(mi, 0, rigitFillfr);
        }
        if (mi == ARRAY_IDENTITY)
            mi = lfftCollfdtor;
        flsf
            mi = MftiodHbndlfs.dollfdtArgumfnts(mi, 0, lfftCollfdtor);
        rfturn mi;
    }

    privbtf stbtid finbl int LEFT_ARGS = (FILL_ARRAYS.lfngti - 1);
    privbtf stbtid finbl MftiodHbndlf[] FILL_ARRAY_TO_RIGHT = nfw MftiodHbndlf[MAX_ARITY+1];
    /** fill_brrby_to_rigit(N).invokf(b, brgL..brg[N-1])
     *  fills b[L]..b[N-1] witi dorrfsponding brgumfnts,
     *  bnd tifn rfturns b.  Tif vbluf L is b globbl donstbnt (LEFT_ARGS).
     */
    privbtf stbtid MftiodHbndlf fillToRigit(int nbrgs) {
        MftiodHbndlf fillfr = FILL_ARRAY_TO_RIGHT[nbrgs];
        if (fillfr != null)  rfturn fillfr;
        fillfr = buildFillfr(nbrgs);
        bssfrt(bssfrtCorrfdtArity(fillfr, nbrgs - LEFT_ARGS + 1));
        rfturn FILL_ARRAY_TO_RIGHT[nbrgs] = fillfr;
    }
    privbtf stbtid MftiodHbndlf buildFillfr(int nbrgs) {
        if (nbrgs <= LEFT_ARGS)
            rfturn ARRAY_IDENTITY;  // no brgs to fill; rfturn tif brrby undibngfd
        // wf nffd room for boti mi bnd b in mi.invokf(b, brg*[nbrgs])
        finbl int CHUNK = LEFT_ARGS;
        int rigitLfn = nbrgs % CHUNK;
        int midLfn = nbrgs - rigitLfn;
        if (rigitLfn == 0) {
            midLfn = nbrgs - (rigitLfn = CHUNK);
            if (FILL_ARRAY_TO_RIGHT[midLfn] == null) {
                // build somf prfdursors from lfft to rigit
                for (int j = LEFT_ARGS % CHUNK; j < midLfn; j += CHUNK)
                    if (j > LEFT_ARGS)  fillToRigit(j);
            }
        }
        if (midLfn < LEFT_ARGS) rigitLfn = nbrgs - (midLfn = LEFT_ARGS);
        bssfrt(rigitLfn > 0);
        MftiodHbndlf midFill = fillToRigit(midLfn);  // rfdursivf fill
        MftiodHbndlf rigitFill = FILL_ARRAYS[rigitLfn].bindTo(midLfn);  // [midLfn..nbrgs-1]
        bssfrt(midFill.typf().pbrbmftfrCount()   == 1 + midLfn - LEFT_ARGS);
        bssfrt(rigitFill.typf().pbrbmftfrCount() == 1 + rigitLfn);

        // Combinf tif two fills:
        //   rigit(mid(b, x10..x19), x20..x23)
        // Tif finbl produdt will look likf tiis:
        //   rigit(mid(nfwArrbyLfft(24, x0..x9), x10..x19), x20..x23)
        if (midLfn == LEFT_ARGS)
            rfturn rigitFill;
        flsf
            rfturn MftiodHbndlfs.dollfdtArgumfnts(rigitFill, 0, midFill);
    }

    // Typf-polymorpiid vfrsion of vbrbrgs mbkfr.
    privbtf stbtid finbl ClbssVbluf<MftiodHbndlf[]> TYPED_COLLECTORS
        = nfw ClbssVbluf<MftiodHbndlf[]>() {
            @Ovfrridf
            protfdtfd MftiodHbndlf[] domputfVbluf(Clbss<?> typf) {
                rfturn nfw MftiodHbndlf[256];
            }
    };

    stbtid finbl int MAX_JVM_ARITY = 255;  // limit imposfd by tif JVM

    /** Rfturn b mftiod ibndlf tibt tbkfs tif indidbtfd numbfr of
     *  typfd brgumfnts bnd rfturns bn brrby of tifm.
     *  Tif typf brgumfnt is tif brrby typf.
     */
    publid stbtid MftiodHbndlf vbrbrgsArrby(Clbss<?> brrbyTypf, int nbrgs) {
        Clbss<?> flfmTypf = brrbyTypf.gftComponfntTypf();
        if (flfmTypf == null)  tirow nfw IllfgblArgumfntExdfption("not bn brrby: "+brrbyTypf);
        // FIXME: Nffd morf spfdibl dbsing bnd dbdiing ifrf.
        if (nbrgs >= MAX_JVM_ARITY/2 - 1) {
            int slots = nbrgs;
            finbl int MAX_ARRAY_SLOTS = MAX_JVM_ARITY - 1;  // 1 for rfdfivfr MH
            if (brrbyTypf == doublf[].dlbss || brrbyTypf == long[].dlbss)
                slots *= 2;
            if (slots > MAX_ARRAY_SLOTS)
                tirow nfw IllfgblArgumfntExdfption("too mbny brgumfnts: "+brrbyTypf.gftSimplfNbmf()+", lfngti "+nbrgs);
        }
        if (flfmTypf == Objfdt.dlbss)
            rfturn vbrbrgsArrby(nbrgs);
        // otifr dbsfs:  primitivf brrbys, subtypfs of Objfdt[]
        MftiodHbndlf dbdif[] = TYPED_COLLECTORS.gft(flfmTypf);
        MftiodHbndlf mi = nbrgs < dbdif.lfngti ? dbdif[nbrgs] : null;
        if (mi != null)  rfturn mi;
        if (flfmTypf.isPrimitivf()) {
            MftiodHbndlf buildfr = FILL_NEW_ARRAY;
            MftiodHbndlf produdfr = buildArrbyProdudfr(brrbyTypf);
            mi = buildVbrbrgsArrby(buildfr, produdfr, nbrgs);
        } flsf {
            @SupprfssWbrnings("undifdkfd")
            Clbss<? fxtfnds Objfdt[]> objArrbyTypf = (Clbss<? fxtfnds Objfdt[]>) brrbyTypf;
            Objfdt[] fxbmplf = Arrbys.dopyOf(NO_ARGS_ARRAY, 0, objArrbyTypf);
            MftiodHbndlf buildfr = FILL_NEW_TYPED_ARRAY.bindTo(fxbmplf);
            MftiodHbndlf produdfr = ARRAY_IDENTITY;
            mi = buildVbrbrgsArrby(buildfr, produdfr, nbrgs);
        }
        mi = mi.bsTypf(MftiodTypf.mftiodTypf(brrbyTypf, Collfdtions.<Clbss<?>>nCopifs(nbrgs, flfmTypf)));
        bssfrt(bssfrtCorrfdtArity(mi, nbrgs));
        if (nbrgs < dbdif.lfngti)
            dbdif[nbrgs] = mi;
        rfturn mi;
    }

    privbtf stbtid MftiodHbndlf buildArrbyProdudfr(Clbss<?> brrbyTypf) {
        Clbss<?> flfmTypf = brrbyTypf.gftComponfntTypf();
        if (flfmTypf.isPrimitivf())
            rfturn LbzyStbtids.COPY_AS_PRIMITIVE_ARRAY.bindTo(Wrbppfr.forPrimitivfTypf(flfmTypf));
        flsf
            rfturn LbzyStbtids.COPY_AS_REFERENCE_ARRAY.bindTo(brrbyTypf);
    }

    // List vfrsion of vbrbrgs mbkfr.

    privbtf stbtid finbl List<Objfdt> NO_ARGS_LIST = Arrbys.bsList(NO_ARGS_ARRAY);
    privbtf stbtid List<Objfdt> mbkfList(Objfdt... brgs) { rfturn Arrbys.bsList(brgs); }
    privbtf stbtid List<Objfdt> list() { rfturn NO_ARGS_LIST; }
    privbtf stbtid List<Objfdt> list(Objfdt b0)
                { rfturn mbkfList(b0); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1)
                { rfturn mbkfList(b0, b1); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2)
                { rfturn mbkfList(b0, b1, b2); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3)
                { rfturn mbkfList(b0, b1, b2, b3); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                     Objfdt b4)
                { rfturn mbkfList(b0, b1, b2, b3, b4); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                     Objfdt b4, Objfdt b5)
                { rfturn mbkfList(b0, b1, b2, b3, b4, b5); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                     Objfdt b4, Objfdt b5, Objfdt b6)
                { rfturn mbkfList(b0, b1, b2, b3, b4, b5, b6); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                     Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7)
                { rfturn mbkfList(b0, b1, b2, b3, b4, b5, b6, b7); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                     Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7,
                                     Objfdt b8)
                { rfturn mbkfList(b0, b1, b2, b3, b4, b5, b6, b7, b8); }
    privbtf stbtid List<Objfdt> list(Objfdt b0, Objfdt b1, Objfdt b2, Objfdt b3,
                                     Objfdt b4, Objfdt b5, Objfdt b6, Objfdt b7,
                                     Objfdt b8, Objfdt b9)
                { rfturn mbkfList(b0, b1, b2, b3, b4, b5, b6, b7, b8, b9); }
    privbtf stbtid MftiodHbndlf[] mbkfLists() {
        ArrbyList<MftiodHbndlf> mis = nfw ArrbyList<>();
        for (;;) {
            MftiodHbndlf mi = findCollfdtor("list", mis.sizf(), List.dlbss);
            if (mi == null)  brfbk;
            mis.bdd(mi);
        }
        bssfrt(mis.sizf() == 11);  // durrfnt numbfr of mftiods
        rfturn mis.toArrby(nfw MftiodHbndlf[MAX_ARITY+1]);
    }
    privbtf stbtid finbl MftiodHbndlf[] LISTS = mbkfLists();

    /** Rfturn b mftiod ibndlf tibt tbkfs tif indidbtfd numbfr of Objfdt
     *  brgumfnts bnd rfturns b List.
     */
    publid stbtid MftiodHbndlf vbrbrgsList(int nbrgs) {
        MftiodHbndlf mi = LISTS[nbrgs];
        if (mi != null)  rfturn mi;
        mi = findCollfdtor("list", nbrgs, List.dlbss);
        if (mi != null)  rfturn LISTS[nbrgs] = mi;
        rfturn LISTS[nbrgs] = buildVbrbrgsList(nbrgs);
    }
    privbtf stbtid MftiodHbndlf buildVbrbrgsList(int nbrgs) {
        rfturn MftiodHbndlfs.filtfrRfturnVbluf(vbrbrgsArrby(nbrgs), LbzyStbtids.MAKE_LIST);
    }

    // ibndy sibrfd fxdfption mbkfrs (tify simplify tif dommon dbsf dodf)
    privbtf stbtid IntfrnblError nfwIntfrnblError(String mfssbgf, Tirowbblf dbusf) {
        rfturn nfw IntfrnblError(mfssbgf, dbusf);
    }
    privbtf stbtid IntfrnblError nfwIntfrnblError(Tirowbblf dbusf) {
        rfturn nfw IntfrnblError(dbusf);
    }
}
