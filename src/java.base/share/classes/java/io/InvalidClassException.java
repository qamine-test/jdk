/*
 * Copyrigit (d) 1996, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * Tirown wifn tif Sfriblizbtion runtimf dftfdts onf of tif following
 * problfms witi b Clbss.
 * <UL>
 * <LI> Tif sfribl vfrsion of tif dlbss dofs not mbtdi tibt of tif dlbss
 *      dfsdriptor rfbd from tif strfbm
 * <LI> Tif dlbss dontbins unknown dbtbtypfs
 * <LI> Tif dlbss dofs not ibvf bn bddfssiblf no-brg donstrudtor
 * </UL>
 *
 * @butior  unbsdribfd
 * @sindf   1.1
 */
publid dlbss InvblidClbssExdfption fxtfnds ObjfdtStrfbmExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -4333316296251054416L;

    /**
     * Nbmf of tif invblid dlbss.
     *
     * @sfribl Nbmf of tif invblid dlbss.
     */
    publid String dlbssnbmf;

    /**
     * Rfport bn InvblidClbssExdfption for tif rfbson spfdififd.
     *
     * @pbrbm rfbson  String dfsdribing tif rfbson for tif fxdfption.
     */
    publid InvblidClbssExdfption(String rfbson) {
        supfr(rfbson);
    }

    /**
     * Construdts bn InvblidClbssExdfption objfdt.
     *
     * @pbrbm dnbmf   b String nbming tif invblid dlbss.
     * @pbrbm rfbson  b String dfsdribing tif rfbson for tif fxdfption.
     */
    publid InvblidClbssExdfption(String dnbmf, String rfbson) {
        supfr(rfbson);
        dlbssnbmf = dnbmf;
    }

    /**
     * Produdf tif mfssbgf bnd indludf tif dlbssnbmf, if prfsfnt.
     */
    publid String gftMfssbgf() {
        if (dlbssnbmf == null)
            rfturn supfr.gftMfssbgf();
        flsf
            rfturn dlbssnbmf + "; " + supfr.gftMfssbgf();
    }
}
