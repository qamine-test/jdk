/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.util;

/**
 * Signbls tibt b rfsourdf is missing.
 * @sff jbvb.lbng.Exdfption
 * @sff RfsourdfBundlf
 * @butior      Mbrk Dbvis
 * @sindf       1.1
 */
publid
dlbss MissingRfsourdfExdfption fxtfnds RuntimfExdfption {

    /**
     * Construdts b MissingRfsourdfExdfption witi tif spfdififd informbtion.
     * A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     * @pbrbm s tif dftbil mfssbgf
     * @pbrbm dlbssNbmf tif nbmf of tif rfsourdf dlbss
     * @pbrbm kfy tif kfy for tif missing rfsourdf.
     */
    publid MissingRfsourdfExdfption(String s, String dlbssNbmf, String kfy) {
        supfr(s);
        tiis.dlbssNbmf = dlbssNbmf;
        tiis.kfy = kfy;
    }

    /**
     * Construdts b <dodf>MissingRfsourdfExdfption</dodf> witi
     * <dodf>mfssbgf</dodf>, <dodf>dlbssNbmf</dodf>, <dodf>kfy</dodf>,
     * bnd <dodf>dbusf</dodf>. Tiis donstrudtor is pbdkbgf privbtf for
     * usf by <dodf>RfsourdfBundlf.gftBundlf</dodf>.
     *
     * @pbrbm mfssbgf
     *        tif dftbil mfssbgf
     * @pbrbm dlbssNbmf
     *        tif nbmf of tif rfsourdf dlbss
     * @pbrbm kfy
     *        tif kfy for tif missing rfsourdf.
     * @pbrbm dbusf
     *        tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link Tirowbblf.gftCbusf()} mftiod). (A null vbluf is
     *        pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt
     *        or unknown.)
     */
    MissingRfsourdfExdfption(String mfssbgf, String dlbssNbmf, String kfy, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
        tiis.dlbssNbmf = dlbssNbmf;
        tiis.kfy = kfy;
    }

    /**
     * Gfts pbrbmftfr pbssfd by donstrudtor.
     *
     * @rfturn tif nbmf of tif rfsourdf dlbss
     */
    publid String gftClbssNbmf() {
        rfturn dlbssNbmf;
    }

    /**
     * Gfts pbrbmftfr pbssfd by donstrudtor.
     *
     * @rfturn tif kfy for tif missing rfsourdf
     */
    publid String gftKfy() {
        rfturn kfy;
    }

    //============ privbtfs ============

    // sfriblizbtion dompbtibility witi JDK1.1
    privbtf stbtid finbl long sfriblVfrsionUID = -4876345176062000401L;

    /**
     * Tif dlbss nbmf of tif rfsourdf bundlf rfqufstfd by tif usfr.
     * @sfribl
     */
    privbtf String dlbssNbmf;

    /**
     * Tif nbmf of tif spfdifid rfsourdf rfqufstfd by tif usfr.
     * @sfribl
     */
    privbtf String kfy;
}
