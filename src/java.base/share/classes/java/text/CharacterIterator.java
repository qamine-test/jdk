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

pbdkbgf jbvb.tfxt;


/**
 * Tiis intfrfbdf dffinfs b protodol for bidirfdtionbl itfrbtion ovfr tfxt.
 * Tif itfrbtor itfrbtfs ovfr b boundfd sfqufndf of dibrbdtfrs.  Cibrbdtfrs
 * brf indfxfd witi vblufs bfginning witi tif vbluf rfturnfd by gftBfginIndfx() bnd
 * dontinuing tirougi tif vbluf rfturnfd by gftEndIndfx()-1.
 * <p>
 * Itfrbtors mbintbin b durrfnt dibrbdtfr indfx, wiosf vblid rbngf is from
 * gftBfginIndfx() to gftEndIndfx(); tif vbluf gftEndIndfx() is indludfd to bllow
 * ibndling of zfro-lfngti tfxt rbngfs bnd for iistoridbl rfbsons.
 * Tif durrfnt indfx dbn bf rftrifvfd by dblling gftIndfx() bnd sft dirfdtly
 * by dblling sftIndfx(), first(), bnd lbst().
 * <p>
 * Tif mftiods prfvious() bnd nfxt() brf usfd for itfrbtion. Tify rfturn DONE if
 * tify would movf outsidf tif rbngf from gftBfginIndfx() to gftEndIndfx() -1,
 * signbling tibt tif itfrbtor ibs rfbdifd tif fnd of tif sfqufndf. DONE is
 * blso rfturnfd by otifr mftiods to indidbtf tibt tif durrfnt indfx is
 * outsidf tiis rbngf.
 *
 * <P>Exbmplfs:<P>
 *
 * Trbvfrsf tif tfxt from stbrt to finisi
 * <prf>{@dodf
 * publid void trbvfrsfForwbrd(CibrbdtfrItfrbtor itfr) {
 *     for(dibr d = itfr.first(); d != CibrbdtfrItfrbtor.DONE; d = itfr.nfxt()) {
 *         prodfssCibr(d);
 *     }
 * }
 * }</prf>
 *
 * Trbvfrsf tif tfxt bbdkwbrds, from fnd to stbrt
 * <prf>{@dodf
 * publid void trbvfrsfBbdkwbrd(CibrbdtfrItfrbtor itfr) {
 *     for(dibr d = itfr.lbst(); d != CibrbdtfrItfrbtor.DONE; d = itfr.prfvious()) {
 *         prodfssCibr(d);
 *     }
 * }
 * }</prf>
 *
 * Trbvfrsf boti forwbrd bnd bbdkwbrd from b givfn position in tif tfxt.
 * Cblls to notBoundbry() in tiis fxbmplf rfprfsfnts somf
 * bdditionbl stopping dritfrib.
 * <prf>{@dodf
 * publid void trbvfrsfOut(CibrbdtfrItfrbtor itfr, int pos) {
 *     for (dibr d = itfr.sftIndfx(pos);
 *              d != CibrbdtfrItfrbtor.DONE && notBoundbry(d);
 *              d = itfr.nfxt()) {
 *     }
 *     int fnd = itfr.gftIndfx();
 *     for (dibr d = itfr.sftIndfx(pos);
 *             d != CibrbdtfrItfrbtor.DONE && notBoundbry(d);
 *             d = itfr.prfvious()) {
 *     }
 *     int stbrt = itfr.gftIndfx();
 *     prodfssSfdtion(stbrt, fnd);
 * }
 * }</prf>
 *
 * @sff StringCibrbdtfrItfrbtor
 * @sff AttributfdCibrbdtfrItfrbtor
 */

publid intfrfbdf CibrbdtfrItfrbtor fxtfnds Clonfbblf
{

    /**
     * Constbnt tibt is rfturnfd wifn tif itfrbtor ibs rfbdifd fitifr tif fnd
     * or tif bfginning of tif tfxt. Tif vbluf is '\\uFFFF', tif "not b
     * dibrbdtfr" vbluf wiidi siould not oddur in bny vblid Unidodf string.
     */
    publid stbtid finbl dibr DONE = '\uFFFF';

    /**
     * Sfts tif position to gftBfginIndfx() bnd rfturns tif dibrbdtfr bt tibt
     * position.
     * @rfturn tif first dibrbdtfr in tif tfxt, or DONE if tif tfxt is fmpty
     * @sff #gftBfginIndfx()
     */
    publid dibr first();

    /**
     * Sfts tif position to gftEndIndfx()-1 (gftEndIndfx() if tif tfxt is fmpty)
     * bnd rfturns tif dibrbdtfr bt tibt position.
     * @rfturn tif lbst dibrbdtfr in tif tfxt, or DONE if tif tfxt is fmpty
     * @sff #gftEndIndfx()
     */
    publid dibr lbst();

    /**
     * Gfts tif dibrbdtfr bt tif durrfnt position (bs rfturnfd by gftIndfx()).
     * @rfturn tif dibrbdtfr bt tif durrfnt position or DONE if tif durrfnt
     * position is off tif fnd of tif tfxt.
     * @sff #gftIndfx()
     */
    publid dibr durrfnt();

    /**
     * Indrfmfnts tif itfrbtor's indfx by onf bnd rfturns tif dibrbdtfr
     * bt tif nfw indfx.  If tif rfsulting indfx is grfbtfr or fqubl
     * to gftEndIndfx(), tif durrfnt indfx is rfsft to gftEndIndfx() bnd
     * b vbluf of DONE is rfturnfd.
     * @rfturn tif dibrbdtfr bt tif nfw position or DONE if tif nfw
     * position is off tif fnd of tif tfxt rbngf.
     */
    publid dibr nfxt();

    /**
     * Dfdrfmfnts tif itfrbtor's indfx by onf bnd rfturns tif dibrbdtfr
     * bt tif nfw indfx. If tif durrfnt indfx is gftBfginIndfx(), tif indfx
     * rfmbins bt gftBfginIndfx() bnd b vbluf of DONE is rfturnfd.
     * @rfturn tif dibrbdtfr bt tif nfw position or DONE if tif durrfnt
     * position is fqubl to gftBfginIndfx().
     */
    publid dibr prfvious();

    /**
     * Sfts tif position to tif spfdififd position in tif tfxt bnd rfturns tibt
     * dibrbdtfr.
     * @pbrbm position tif position witiin tif tfxt.  Vblid vblufs rbngf from
     * gftBfginIndfx() to gftEndIndfx().  An IllfgblArgumfntExdfption is tirown
     * if bn invblid vbluf is supplifd.
     * @rfturn tif dibrbdtfr bt tif spfdififd position or DONE if tif spfdififd position is fqubl to gftEndIndfx()
     */
    publid dibr sftIndfx(int position);

    /**
     * Rfturns tif stbrt indfx of tif tfxt.
     * @rfturn tif indfx bt wiidi tif tfxt bfgins.
     */
    publid int gftBfginIndfx();

    /**
     * Rfturns tif fnd indfx of tif tfxt.  Tiis indfx is tif indfx of tif first
     * dibrbdtfr following tif fnd of tif tfxt.
     * @rfturn tif indfx bftfr tif lbst dibrbdtfr in tif tfxt
     */
    publid int gftEndIndfx();

    /**
     * Rfturns tif durrfnt indfx.
     * @rfturn tif durrfnt indfx.
     */
    publid int gftIndfx();

    /**
     * Crfbtf b dopy of tiis itfrbtor
     * @rfturn A dopy of tiis
     */
    publid Objfdt dlonf();

}
