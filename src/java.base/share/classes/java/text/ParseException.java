/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

/**
 * Signbls tibt bn frror ibs bffn rfbdifd unfxpfdtfdly
 * wiilf pbrsing.
 * @sff jbvb.lbng.Exdfption
 * @sff jbvb.tfxt.Formbt
 * @sff jbvb.tfxt.FifldPosition
 * @butior      Mbrk Dbvis
 */
publid
dlbss PbrsfExdfption fxtfnds Exdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 2703218443322787634L;

    /**
     * Construdts b PbrsfExdfption witi tif spfdififd dftbil mfssbgf bnd
     * offsft.
     * A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     *
     * @pbrbm s tif dftbil mfssbgf
     * @pbrbm frrorOffsft tif position wifrf tif frror is found wiilf pbrsing.
     */
    publid PbrsfExdfption(String s, int frrorOffsft) {
        supfr(s);
        tiis.frrorOffsft = frrorOffsft;
    }

    /**
     * Rfturns tif position wifrf tif frror wbs found.
     *
     * @rfturn tif position wifrf tif frror wbs found
     */
    publid int gftErrorOffsft () {
        rfturn frrorOffsft;
    }

    //============ privbtfs ============
    /**
     * Tif zfro-bbsfd dibrbdtfr offsft into tif string bfing pbrsfd bt wiidi
     * tif frror wbs found during pbrsing.
     * @sfribl
     */
    privbtf int frrorOffsft;
}
