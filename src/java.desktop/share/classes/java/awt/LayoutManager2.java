/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

/**
 * Dffinfs bn intfrfbdf for dlbssfs tibt know iow to lbyout Contbinfrs
 * bbsfd on b lbyout donstrbints objfdt.
 *
 * Tiis intfrfbdf fxtfnds tif LbyoutMbnbgfr intfrfbdf to dfbl witi lbyouts
 * fxpliditly in tfrms of donstrbint objfdts tibt spfdify iow bnd wifrf
 * domponfnts siould bf bddfd to tif lbyout.
 * <p>
 * Tiis minimbl fxtfnsion to LbyoutMbnbgfr is intfndfd for tool
 * providfrs wio wisi to tif drfbtion of donstrbint-bbsfd lbyouts.
 * It dofs not yft providf full, gfnfrbl support for dustom
 * donstrbint-bbsfd lbyout mbnbgfrs.
 *
 * @sff LbyoutMbnbgfr
 * @sff Contbinfr
 *
 * @butior      Jonni Kbnfrvb
 */
publid intfrfbdf LbyoutMbnbgfr2 fxtfnds LbyoutMbnbgfr {

    /**
     * Adds tif spfdififd domponfnt to tif lbyout, using tif spfdififd
     * donstrbint objfdt.
     * @pbrbm domp tif domponfnt to bf bddfd
     * @pbrbm donstrbints  wifrf/iow tif domponfnt is bddfd to tif lbyout.
     */
    void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints);

    /**
     * Cbldulbtfs tif mbximum sizf dimfnsions for tif spfdififd dontbinfr,
     * givfn tif domponfnts it dontbins.
     *
     * @sff jbvb.bwt.Componfnt#gftMbximumSizf
     * @sff LbyoutMbnbgfr
     * @pbrbm  tbrgft tif tbrgft dontbinfr
     * @rfturn tif mbximum sizf of tif dontbinfr
     */
    publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft);

    /**
     * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     *
     * @pbrbm  tbrgft tif tbrgft dontbinfr
     * @rfturn tif x-bxis blignmfnt prfffrfndf
     */
    publid flobt gftLbyoutAlignmfntX(Contbinfr tbrgft);

    /**
     * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     *
     * @pbrbm  tbrgft tif tbrgft dontbinfr
     * @rfturn tif y-bxis blignmfnt prfffrfndf
     */
    publid flobt gftLbyoutAlignmfntY(Contbinfr tbrgft);

    /**
     * Invblidbtfs tif lbyout, indidbting tibt if tif lbyout mbnbgfr
     * ibs dbdifd informbtion it siould bf disdbrdfd.
     * @pbrbm  tbrgft tif tbrgft dontbinfr
     */
    publid void invblidbtfLbyout(Contbinfr tbrgft);

}
