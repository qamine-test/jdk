/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

/**
 * A sfrvidf tibt dfdouplfs tif produdtion of nfw bsyndironous tbsks
 * from tif donsumption of tif rfsults of domplftfd tbsks.  Produdfrs
 * {@dodf submit} tbsks for fxfdution. Consumfrs {@dodf tbkf}
 * domplftfd tbsks bnd prodfss tifir rfsults in tif ordfr tify
 * domplftf.  A {@dodf ComplftionSfrvidf} dbn for fxbmplf bf usfd to
 * mbnbgf bsyndironous I/O, in wiidi tbsks tibt pfrform rfbds brf
 * submittfd in onf pbrt of b progrbm or systfm, bnd tifn bdtfd upon
 * in b difffrfnt pbrt of tif progrbm wifn tif rfbds domplftf,
 * possibly in b difffrfnt ordfr tibn tify wfrf rfqufstfd.
 *
 * <p>Typidblly, b {@dodf ComplftionSfrvidf} rflifs on b sfpbrbtf
 * {@link Exfdutor} to bdtublly fxfdutf tif tbsks, in wiidi dbsf tif
 * {@dodf ComplftionSfrvidf} only mbnbgfs bn intfrnbl domplftion
 * qufuf. Tif {@link ExfdutorComplftionSfrvidf} dlbss providfs bn
 * implfmfntbtion of tiis bpprobdi.
 *
 * <p>Mfmory donsistfndy ffffdts: Adtions in b tirfbd prior to
 * submitting b tbsk to b {@dodf ComplftionSfrvidf}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions tbkfn by tibt tbsk, wiidi in turn <i>ibppfn-bfforf</i>
 * bdtions following b suddfssful rfturn from tif dorrfsponding {@dodf tbkf()}.
 */
publid intfrfbdf ComplftionSfrvidf<V> {
    /**
     * Submits b vbluf-rfturning tbsk for fxfdution bnd rfturns b Futurf
     * rfprfsfnting tif pfnding rfsults of tif tbsk.  Upon domplftion,
     * tiis tbsk mby bf tbkfn or pollfd.
     *
     * @pbrbm tbsk tif tbsk to submit
     * @rfturn b Futurf rfprfsfnting pfnding domplftion of tif tbsk
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     * @tirows NullPointfrExdfption if tif tbsk is null
     */
    Futurf<V> submit(Cbllbblf<V> tbsk);

    /**
     * Submits b Runnbblf tbsk for fxfdution bnd rfturns b Futurf
     * rfprfsfnting tibt tbsk.  Upon domplftion, tiis tbsk mby bf
     * tbkfn or pollfd.
     *
     * @pbrbm tbsk tif tbsk to submit
     * @pbrbm rfsult tif rfsult to rfturn upon suddfssful domplftion
     * @rfturn b Futurf rfprfsfnting pfnding domplftion of tif tbsk,
     *         bnd wiosf {@dodf gft()} mftiod will rfturn tif givfn
     *         rfsult vbluf upon domplftion
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     * @tirows NullPointfrExdfption if tif tbsk is null
     */
    Futurf<V> submit(Runnbblf tbsk, V rfsult);

    /**
     * Rftrifvfs bnd rfmovfs tif Futurf rfprfsfnting tif nfxt
     * domplftfd tbsk, wbiting if nonf brf yft prfsfnt.
     *
     * @rfturn tif Futurf rfprfsfnting tif nfxt domplftfd tbsk
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    Futurf<V> tbkf() tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs bnd rfmovfs tif Futurf rfprfsfnting tif nfxt
     * domplftfd tbsk, or {@dodf null} if nonf brf prfsfnt.
     *
     * @rfturn tif Futurf rfprfsfnting tif nfxt domplftfd tbsk, or
     *         {@dodf null} if nonf brf prfsfnt
     */
    Futurf<V> poll();

    /**
     * Rftrifvfs bnd rfmovfs tif Futurf rfprfsfnting tif nfxt
     * domplftfd tbsk, wbiting if nfdfssbry up to tif spfdififd wbit
     * timf if nonf brf yft prfsfnt.
     *
     * @pbrbm timfout iow long to wbit bfforf giving up, in units of
     *        {@dodf unit}
     * @pbrbm unit b {@dodf TimfUnit} dftfrmining iow to intfrprft tif
     *        {@dodf timfout} pbrbmftfr
     * @rfturn tif Futurf rfprfsfnting tif nfxt domplftfd tbsk or
     *         {@dodf null} if tif spfdififd wbiting timf flbpsfs
     *         bfforf onf is prfsfnt
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    Futurf<V> poll(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption;
}
