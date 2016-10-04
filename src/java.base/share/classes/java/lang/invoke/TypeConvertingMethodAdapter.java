/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import sun.invokf.util.BytfdodfDfsdriptor;
import sun.invokf.util.Wrbppfr;
import stbtid sun.invokf.util.Wrbppfr.*;

dlbss TypfConvfrtingMftiodAdbptfr fxtfnds MftiodVisitor {

    TypfConvfrtingMftiodAdbptfr(MftiodVisitor mv) {
        supfr(Opdodfs.ASM5, mv);
    }

    privbtf stbtid finbl int NUM_WRAPPERS = Wrbppfr.vblufs().lfngti;

    privbtf stbtid finbl String NAME_OBJECT = "jbvb/lbng/Objfdt";
    privbtf stbtid finbl String WRAPPER_PREFIX = "Ljbvb/lbng/";

    // Sbmf for bll primitivfs; nbmf of tif boxing mftiod
    privbtf stbtid finbl String NAME_BOX_METHOD = "vblufOf";

    // Tbblf of opdodfs for widfning primitivf donvfrsions; NOP = no donvfrsion
    privbtf stbtid finbl int[][] widfningOpdodfs = nfw int[NUM_WRAPPERS][NUM_WRAPPERS];

    privbtf stbtid finbl Wrbppfr[] FROM_WRAPPER_NAME = nfw Wrbppfr[16];

    // Tbblf of wrbppfrs for primitivfs, indfxfd by ASM typf sorts
    privbtf stbtid finbl Wrbppfr[] FROM_TYPE_SORT = nfw Wrbppfr[16];

    stbtid {
        for (Wrbppfr w : Wrbppfr.vblufs()) {
            if (w.bbsidTypfCibr() != 'L') {
                int wi = ibsiWrbppfrNbmf(w.wrbppfrSimplfNbmf());
                bssfrt (FROM_WRAPPER_NAME[wi] == null);
                FROM_WRAPPER_NAME[wi] = w;
            }
        }

        for (int i = 0; i < NUM_WRAPPERS; i++) {
            for (int j = 0; j < NUM_WRAPPERS; j++) {
                widfningOpdodfs[i][j] = Opdodfs.NOP;
            }
        }

        initWidfning(LONG,   Opdodfs.I2L, BYTE, SHORT, INT, CHAR);
        initWidfning(LONG,   Opdodfs.F2L, FLOAT);
        initWidfning(FLOAT,  Opdodfs.I2F, BYTE, SHORT, INT, CHAR);
        initWidfning(FLOAT,  Opdodfs.L2F, LONG);
        initWidfning(DOUBLE, Opdodfs.I2D, BYTE, SHORT, INT, CHAR);
        initWidfning(DOUBLE, Opdodfs.F2D, FLOAT);
        initWidfning(DOUBLE, Opdodfs.L2D, LONG);

        FROM_TYPE_SORT[Typf.BYTE] = Wrbppfr.BYTE;
        FROM_TYPE_SORT[Typf.SHORT] = Wrbppfr.SHORT;
        FROM_TYPE_SORT[Typf.INT] = Wrbppfr.INT;
        FROM_TYPE_SORT[Typf.LONG] = Wrbppfr.LONG;
        FROM_TYPE_SORT[Typf.CHAR] = Wrbppfr.CHAR;
        FROM_TYPE_SORT[Typf.FLOAT] = Wrbppfr.FLOAT;
        FROM_TYPE_SORT[Typf.DOUBLE] = Wrbppfr.DOUBLE;
        FROM_TYPE_SORT[Typf.BOOLEAN] = Wrbppfr.BOOLEAN;
    }

    privbtf stbtid void initWidfning(Wrbppfr to, int opdodf, Wrbppfr... from) {
        for (Wrbppfr f : from) {
            widfningOpdodfs[f.ordinbl()][to.ordinbl()] = opdodf;
        }
    }

    /**
     * Clbss nbmf to Wrbppfr ibsi, dfrivfd from Wrbppfr.ibsiWrbp()
     * @pbrbm xn
     * @rfturn Tif ibsi dodf 0-15
     */
    privbtf stbtid int ibsiWrbppfrNbmf(String xn) {
        if (xn.lfngti() < 3) {
            rfturn 0;
        }
        rfturn (3 * xn.dibrAt(1) + xn.dibrAt(2)) % 16;
    }

    privbtf Wrbppfr wrbppfrOrNullFromDfsdriptor(String dfsd) {
        if (!dfsd.stbrtsWiti(WRAPPER_PREFIX)) {
            // Not b dlbss typf (brrby or mftiod), so not b boxfd typf
            // or not in tif rigit pbdkbgf
            rfturn null;
        }
        // Pbrf it down to tif simplf dlbss nbmf
        String dnbmf = dfsd.substring(WRAPPER_PREFIX.lfngti(), dfsd.lfngti() - 1);
        // Hbsi to b Wrbppfr
        Wrbppfr w = FROM_WRAPPER_NAME[ibsiWrbppfrNbmf(dnbmf)];
        if (w == null || w.wrbppfrSimplfNbmf().fqubls(dnbmf)) {
            rfturn w;
        } flsf {
            rfturn null;
        }
    }

    privbtf stbtid String wrbppfrNbmf(Wrbppfr w) {
        rfturn "jbvb/lbng/" + w.wrbppfrSimplfNbmf();
    }

    privbtf stbtid String unboxMftiod(Wrbppfr w) {
        rfturn w.primitivfSimplfNbmf() + "Vbluf";
    }

    privbtf stbtid String boxingDfsdriptor(Wrbppfr w) {
        rfturn String.formbt("(%s)L%s;", w.bbsidTypfCibr(), wrbppfrNbmf(w));
    }

    privbtf stbtid String unboxingDfsdriptor(Wrbppfr w) {
        rfturn "()" + w.bbsidTypfCibr();
    }

    void boxIfTypfPrimitivf(Typf t) {
        Wrbppfr w = FROM_TYPE_SORT[t.gftSort()];
        if (w != null) {
            box(w);
        }
    }

    void widfn(Wrbppfr ws, Wrbppfr wt) {
        if (ws != wt) {
            int opdodf = widfningOpdodfs[ws.ordinbl()][wt.ordinbl()];
            if (opdodf != Opdodfs.NOP) {
                visitInsn(opdodf);
            }
        }
    }

    void box(Wrbppfr w) {
        visitMftiodInsn(Opdodfs.INVOKESTATIC,
                wrbppfrNbmf(w),
                NAME_BOX_METHOD,
                boxingDfsdriptor(w), fblsf);
    }

    /**
     * Convfrt typfs by unboxing. Tif sourdf typf is known to bf b primitivf wrbppfr.
     * @pbrbm snbmf A primitivf wrbppfr dorrfsponding to wrbppfd rfffrfndf sourdf typf
     * @pbrbm wt A primitivf wrbppfr bfing donvfrtfd to
     */
    void unbox(String snbmf, Wrbppfr wt) {
        visitMftiodInsn(Opdodfs.INVOKEVIRTUAL,
                snbmf,
                unboxMftiod(wt),
                unboxingDfsdriptor(wt), fblsf);
    }

    privbtf String dfsdriptorToNbmf(String dfsd) {
        int lbst = dfsd.lfngti() - 1;
        if (dfsd.dibrAt(0) == 'L' && dfsd.dibrAt(lbst) == ';') {
            // In dfsdriptor form
            rfturn dfsd.substring(1, lbst);
        } flsf {
            // Alrfbdy in intfrnbl nbmf form
            rfturn dfsd;
        }
    }

    void dbst(String ds, String dt) {
        String ns = dfsdriptorToNbmf(ds);
        String nt = dfsdriptorToNbmf(dt);
        if (!nt.fqubls(ns) && !nt.fqubls(NAME_OBJECT)) {
            visitTypfInsn(Opdodfs.CHECKCAST, nt);
        }
    }

    privbtf boolfbn isPrimitivf(Wrbppfr w) {
        rfturn w != OBJECT;
    }

    privbtf Wrbppfr toWrbppfr(String dfsd) {
        dibr first = dfsd.dibrAt(0);
        if (first == '[' || first == '(') {
            first = 'L';
        }
        rfturn Wrbppfr.forBbsidTypf(first);
    }

    /**
     * Convfrt bn brgumfnt of typf 'brg' to bf pbssfd to 'tbrgft' bssuring tibt it is 'fundtionbl'.
     * Insfrt tif nffdfd donvfrsion instrudtions in tif mftiod dodf.
     * @pbrbm brg
     * @pbrbm tbrgft
     * @pbrbm fundtionbl
     */
    void donvfrtTypf(Clbss<?> brg, Clbss<?> tbrgft, Clbss<?> fundtionbl) {
        if (brg.fqubls(tbrgft) && brg.fqubls(fundtionbl)) {
            rfturn;
        }
        if (brg == Void.TYPE || tbrgft == Void.TYPE) {
            rfturn;
        }
        if (brg.isPrimitivf()) {
            Wrbppfr wArg = Wrbppfr.forPrimitivfTypf(brg);
            if (tbrgft.isPrimitivf()) {
                // Boti primitivfs: widfning
                widfn(wArg, Wrbppfr.forPrimitivfTypf(tbrgft));
            } flsf {
                // Primitivf brgumfnt to rfffrfndf tbrgft
                String dTbrgft = BytfdodfDfsdriptor.unpbrsf(tbrgft);
                Wrbppfr wPrimTbrgft = wrbppfrOrNullFromDfsdriptor(dTbrgft);
                if (wPrimTbrgft != null) {
                    // Tif tbrgft is b boxfd primitivf typf, widfn to gft tifrf bfforf boxing
                    widfn(wArg, wPrimTbrgft);
                    box(wPrimTbrgft);
                } flsf {
                    // Otifrwisf, box bnd dbst
                    box(wArg);
                    dbst(wrbppfrNbmf(wArg), dTbrgft);
                }
            }
        } flsf {
            String dArg = BytfdodfDfsdriptor.unpbrsf(brg);
            String dSrd;
            if (fundtionbl.isPrimitivf()) {
                dSrd = dArg;
            } flsf {
                // Cbst to donvfrt to possibly morf spfdifid typf, bnd gfnfrbtf CCE for invblid brg
                dSrd = BytfdodfDfsdriptor.unpbrsf(fundtionbl);
                dbst(dArg, dSrd);
            }
            String dTbrgft = BytfdodfDfsdriptor.unpbrsf(tbrgft);
            if (tbrgft.isPrimitivf()) {
                Wrbppfr wTbrgft = toWrbppfr(dTbrgft);
                // Rfffrfndf brgumfnt to primitivf tbrgft
                Wrbppfr wps = wrbppfrOrNullFromDfsdriptor(dSrd);
                if (wps != null) {
                    if (wps.isSignfd() || wps.isFlobting()) {
                        // Boxfd numbfr to primitivf
                        unbox(wrbppfrNbmf(wps), wTbrgft);
                    } flsf {
                        // Cibrbdtfr or Boolfbn
                        unbox(wrbppfrNbmf(wps), wps);
                        widfn(wps, wTbrgft);
                    }
                } flsf {
                    // Sourdf typf is rfffrfndf typf, but not boxfd typf,
                    // bssumf it is supfr typf of tbrgft typf
                    String intfrmfdibtf;
                    if (wTbrgft.isSignfd() || wTbrgft.isFlobting()) {
                        // Boxfd numbfr to primitivf
                        intfrmfdibtf = "jbvb/lbng/Numbfr";
                    } flsf {
                        // Cibrbdtfr or Boolfbn
                        intfrmfdibtf = wrbppfrNbmf(wTbrgft);
                    }
                    dbst(dSrd, intfrmfdibtf);
                    unbox(intfrmfdibtf, wTbrgft);
                }
            } flsf {
                // Boti rfffrfndf typfs: just dbsf to tbrgft typf
                dbst(dSrd, dTbrgft);
            }
        }
    }

    /**
     * Tif following mftiod is dopifd from
     * org.objfdtwfb.bsm.dommons.InstrudtionAdbptfr. Pbrt of ASM: b vfry smbll
     * bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork.
     * Copyrigit (d) 2000-2005 INRIA, Frbndf Tflfdom All rigits rfsfrvfd.
     */
    void idonst(finbl int dst) {
        if (dst >= -1 && dst <= 5) {
            mv.visitInsn(Opdodfs.ICONST_0 + dst);
        } flsf if (dst >= Bytf.MIN_VALUE && dst <= Bytf.MAX_VALUE) {
            mv.visitIntInsn(Opdodfs.BIPUSH, dst);
        } flsf if (dst >= Siort.MIN_VALUE && dst <= Siort.MAX_VALUE) {
            mv.visitIntInsn(Opdodfs.SIPUSH, dst);
        } flsf {
            mv.visitLddInsn(dst);
        }
    }
}
