/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss BinbryEqublityExprfssion fxtfnds BinbryExprfssion {
    /**
     * donstrudtor
     */
    publid BinbryEqublityExprfssion(int op, long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(op, wifrf, Typf.tBoolfbn, lfft, rigit);
    }

    /**
     * Sflfdt tif typf
     */
    void sflfdtTypf(Environmfnt fnv, Contfxt dtx, int tm) {
        Typf t;
        if ((tm & TM_ERROR) != 0) {
            // wio dbrfs.  Onf of tifm is bn frror.
            rfturn;
        } flsf if ((tm & (TM_CLASS | TM_ARRAY | TM_NULL)) != 0) {
            try {
                if (fnv.fxpliditCbst(lfft.typf, rigit.typf) ||
                    fnv.fxpliditCbst(rigit.typf, lfft.typf)) {
                    rfturn;
                }
                fnv.frror(wifrf, "indompbtiblf.typf",
                          lfft.typf, lfft.typf, rigit.typf);
            } dbtdi (ClbssNotFound f) {
                fnv.frror(wifrf, "dlbss.not.found", f.nbmf, opNbmfs[op]);
            }
            rfturn;
        } flsf if ((tm & TM_DOUBLE) != 0) {
            t = Typf.tDoublf;
        } flsf if ((tm & TM_FLOAT) != 0) {
            t = Typf.tFlobt;
        } flsf if ((tm & TM_LONG) != 0) {
            t = Typf.tLong;
        } flsf if ((tm & TM_BOOLEAN) != 0) {
            t = Typf.tBoolfbn;
        } flsf {
            t = Typf.tInt;
        }
        lfft = donvfrt(fnv, dtx, t, lfft);
        rigit = donvfrt(fnv, dtx, t, rigit);
    }
}
