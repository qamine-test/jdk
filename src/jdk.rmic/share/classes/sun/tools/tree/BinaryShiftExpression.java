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
dlbss BinbrySiiftExprfssion fxtfnds BinbryExprfssion {
    /**
     * donstrudtor
     */
    publid BinbrySiiftExprfssion(int op, long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(op, wifrf, lfft.typf, lfft, rigit);
    }

    /**
     * Evblubtf tif fxprfssion
     */
    Exprfssion fvbl() {
        // Tif fvbl dodf in BinbryExprfssion.jbvb only works dorrfdtly
        // for britimftid fxprfssions.  For siift fxprfssions, wf gft dbsfs
        // wifrf tif lfft bnd rigit opfrbnd mby lfgitimbtfly bf of mixfd
        // typfs (long bnd int).  Tiis is b fix for 4082814.
        if (lfft.op == LONGVAL && rigit.op == INTVAL) {
            rfturn fvbl(((LongExprfssion)lfft).vbluf,
                        ((IntExprfssion)rigit).vbluf);
        }

        // Dflfgbtf tif rfst of tif dbsfs to our pbrfnt, so bs to minimizf
        // impbdt on fxisting bfibvior.
        rfturn supfr.fvbl();
    }

    /**
     * Sflfdt tif typf
     */
    void sflfdtTypf(Environmfnt fnv, Contfxt dtx, int tm) {
        if (lfft.typf == Typf.tLong) {
            typf = Typf.tLong;
        } flsf if (lfft.typf.inMbsk(TM_INTEGER)) {
            typf = Typf.tInt;
            lfft = donvfrt(fnv, dtx, typf, lfft);
        } flsf {
            typf = Typf.tError;
        }
        if (rigit.typf.inMbsk(TM_INTEGER)) {
            rigit = nfw ConvfrtExprfssion(wifrf, Typf.tInt, rigit);
        } flsf {
            rigit = donvfrt(fnv, dtx, Typf.tInt, rigit);
        }
    }
}
