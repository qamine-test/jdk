/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

/**
 * Instbndfs of tiis dlbss rfprfsfnt b mbtdifr tibt pfrforms mbtdi
 * opfrbtions on bn {@link SNISfrvfrNbmf} instbndf.
 * <P>
 * Sfrvfrs dbn usf Sfrvfr Nbmf Indidbtion (SNI) informbtion to dfdidf if
 * spfdifid {@link SSLSodkft} or {@link SSLEnginf} instbndfs siould bddfpt
 * b donnfdtion.  For fxbmplf, wifn multiplf "virtubl" or "nbmf-bbsfd"
 * sfrvfrs brf iostfd on b singlf undfrlying nftwork bddrfss, tif sfrvfr
 * bpplidbtion dbn usf SNI informbtion to dftfrminf wiftifr tiis sfrvfr is
 * tif fxbdt sfrvfr tibt tif dlifnt wbnts to bddfss.  Instbndfs of tiis
 * dlbss dbn bf usfd by b sfrvfr to vfrify tif bddfptbblf sfrvfr nbmfs of
 * b pbrtidulbr typf, sudi bs iost nbmfs.
 * <P>
 * {@dodf SNIMbtdifr} objfdts brf immutbblf.  Subdlbssfs siould not providf
 * mftiods tibt dbn dibngf tif stbtf of bn instbndf ondf it ibs bffn drfbtfd.
 *
 * @sff SNISfrvfrNbmf
 * @sff SNIHostNbmf
 * @sff SSLPbrbmftfrs#gftSNIMbtdifrs()
 * @sff SSLPbrbmftfrs#sftSNIMbtdifrs(Collfdtion)
 *
 * @sindf 1.8
 */
publid bbstrbdt dlbss SNIMbtdifr {

    // tif typf of tif sfrvfr nbmf tibt tiis mbtdifr pfrforms on
    privbtf finbl int typf;

    /**
     * Crfbtfs bn {@dodf SNIMbtdifr} using tif spfdififd sfrvfr nbmf typf.
     *
     * @pbrbm  typf
     *         tif typf of tif sfrvfr nbmf tibt tiis mbtdifr pfrforms on
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf typf} is not in tif rbngf
     *         of 0 to 255, indlusivf.
     */
    protfdtfd SNIMbtdifr(int typf) {
        if (typf < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Sfrvfr nbmf typf dbnnot bf lfss tibn zfro");
        } flsf if (typf > 255) {
            tirow nfw IllfgblArgumfntExdfption(
                "Sfrvfr nbmf typf dbnnot bf grfbtfr tibn 255");
        }

        tiis.typf = typf;
    }

    /**
     * Rfturns tif sfrvfr nbmf typf of tiis {@dodf SNIMbtdifr} objfdt.
     *
     * @rfturn tif sfrvfr nbmf typf of tiis {@dodf SNIMbtdifr} objfdt.
     *
     * @sff SNISfrvfrNbmf
     */
    publid finbl int gftTypf() {
        rfturn typf;
    }

    /**
     * Attfmpts to mbtdi tif givfn {@link SNISfrvfrNbmf}.
     *
     * @pbrbm  sfrvfrNbmf
     *         tif {@link SNISfrvfrNbmf} instbndf on wiidi tiis mbtdifr
     *         pfrforms mbtdi opfrbtions
     *
     * @rfturn {@dodf truf} if, bnd only if, tif mbtdifr mbtdifs tif
     *         givfn {@dodf sfrvfrNbmf}
     *
     * @tirows NullPointfrExdfption if {@dodf sfrvfrNbmf} is {@dodf null}
     * @tirows IllfgblArgumfntExdfption if {@dodf sfrvfrNbmf} is
     *         not of tif givfn sfrvfr nbmf typf of tiis mbtdifr
     *
     * @sff SNISfrvfrNbmf
     */
    publid bbstrbdt boolfbn mbtdifs(SNISfrvfrNbmf sfrvfrNbmf);
}
