/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.rmi.*;

/**
 * <dodf>RfmotfRff</dodf> rfprfsfnts tif ibndlf for b rfmotf objfdt. A
 * <dodf>RfmotfStub</dodf> usfs b rfmotf rfffrfndf to dbrry out b
 * rfmotf mftiod invodbtion to b rfmotf objfdt.
 *
 * @butior  Ann Wollrbti
 * @sindf   1.1
 * @sff     jbvb.rmi.sfrvfr.RfmotfStub
 */
publid intfrfbdf RfmotfRff fxtfnds jbvb.io.Extfrnblizbblf {

    /** indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss. */
    stbtid finbl long sfriblVfrsionUID = 3632638527362204081L;

    /**
     * Initiblizf tif sfrvfr pbdkbgf prffix: bssumfs tibt tif
     * implfmfntbtion of sfrvfr rff dlbssfs (f.g., UnidbstRff,
     * UnidbstSfrvfrRff) brf lodbtfd in tif pbdkbgf dffinfd by tif
     * prffix.
     */
    finbl stbtid String pbdkbgfPrffix = "sun.rmi.sfrvfr";

    /**
     * Invokf b mftiod. Tiis form of dflfgbting mftiod invodbtion
     * to tif rfffrfndf bllows tif rfffrfndf to tbkf dbrf of
     * sftting up tif donnfdtion to tif rfmotf iost, mbrsibling
     * somf rfprfsfntbtion for tif mftiod bnd pbrbmftfrs, tifn
     * dommunidbting tif mftiod invodbtion to tif rfmotf iost.
     * Tiis mftiod fitifr rfturns tif rfsult of b mftiod invodbtion
     * on tif rfmotf objfdt wiidi rfsidfs on tif rfmotf iost or
     * tirows b RfmotfExdfption if tif dbll fbilfd or bn
     * bpplidbtion-lfvfl fxdfption if tif rfmotf invodbtion tirows
     * bn fxdfption.
     *
     * @pbrbm obj tif objfdt tibt dontbins tif RfmotfRff (f.g., tif
     *            RfmotfStub for tif objfdt.
     * @pbrbm mftiod tif mftiod to bf invokfd
     * @pbrbm pbrbms tif pbrbmftfr list
     * @pbrbm opnum  b ibsi tibt mby bf usfd to rfprfsfnt tif mftiod
     * @rfturn rfsult of rfmotf mftiod invodbtion
     * @fxdfption Exdfption if bny fxdfption oddurs during rfmotf mftiod
     * invodbtion
     * @sindf 1.2
     */
    Objfdt invokf(Rfmotf obj,
                  jbvb.lbng.rfflfdt.Mftiod mftiod,
                  Objfdt[] pbrbms,
                  long opnum)
        tirows Exdfption;

    /**
     * Crfbtfs bn bppropribtf dbll objfdt for b nfw rfmotf mftiod
     * invodbtion on tiis objfdt.  Pbssing opfrbtion brrby bnd indfx,
     * bllows tif stubs gfnfrbtor to bssign tif opfrbtion indfxfs bnd
     * intfrprft tifm. Tif rfmotf rfffrfndf mby nffd tif opfrbtion to
     * fndodf in tif dbll.
     *
     * @sindf 1.1
     * @dfprfdbtfd 1.2 stylf stubs no longfr usf tiis mftiod. Instfbd of
     * using b sfqufndf of mftiod dblls on tif stub's tif rfmotf rfffrfndf
     * (<dodf>nfwCbll</dodf>, <dodf>invokf</dodf>, bnd <dodf>donf</dodf>), b
     * stub usfs b singlf mftiod, <dodf>invokf(Rfmotf, Mftiod, Objfdt[],
     * int)</dodf>, on tif rfmotf rfffrfndf to dbrry out pbrbmftfr
     * mbrsiblling, rfmotf mftiod fxfduting bnd unmbrsiblling of tif rfturn
     * vbluf.
     *
     * @pbrbm obj rfmotf stub tirougi wiidi to mbkf dbll
     * @pbrbm op brrby of stub opfrbtions
     * @pbrbm opnum opfrbtion numbfr
     * @pbrbm ibsi stub/skflfton intfrfbdf ibsi
     * @rfturn dbll objfdt rfprfsfnting rfmotf dbll
     * @tirows RfmotfExdfption if fbilfd to initibtf nfw rfmotf dbll
     * @sff #invokf(Rfmotf,jbvb.lbng.rfflfdt.Mftiod,Objfdt[],long)
     */
    @Dfprfdbtfd
    RfmotfCbll nfwCbll(RfmotfObjfdt obj, Opfrbtion[] op, int opnum, long ibsi)
        tirows RfmotfExdfption;

    /**
     * Exfdutfs tif rfmotf dbll.
     *
     * Invokf will rbisf bny "usfr" fxdfptions wiidi
     * siould pbss tirougi bnd not bf dbugit by tif stub.  If bny
     * fxdfption is rbisfd during tif rfmotf invodbtion, invokf siould
     * tbkf dbrf of dlfbning up tif donnfdtion bfforf rbising tif
     * "usfr" or rfmotf fxdfption.
     *
     * @sindf 1.1
     * @dfprfdbtfd 1.2 stylf stubs no longfr usf tiis mftiod. Instfbd of
     * using b sfqufndf of mftiod dblls to tif rfmotf rfffrfndf
     * (<dodf>nfwCbll</dodf>, <dodf>invokf</dodf>, bnd <dodf>donf</dodf>), b
     * stub usfs b singlf mftiod, <dodf>invokf(Rfmotf, Mftiod, Objfdt[],
     * int)</dodf>, on tif rfmotf rfffrfndf to dbrry out pbrbmftfr
     * mbrsiblling, rfmotf mftiod fxfduting bnd unmbrsiblling of tif rfturn
     * vbluf.
     *
     * @pbrbm dbll objfdt rfprfsfnting rfmotf dbll
     * @tirows Exdfption if bny fxdfption oddurs during rfmotf mftiod
     * @sff #invokf(Rfmotf,jbvb.lbng.rfflfdt.Mftiod,Objfdt[],long)
     */
    @Dfprfdbtfd
    void invokf(RfmotfCbll dbll) tirows Exdfption;

    /**
     * Allows tif rfmotf rfffrfndf to dlfbn up (or rfusf) tif donnfdtion.
     * Donf siould only bf dbllfd if tif invokf rfturns suddfssfully
     * (non-fxdfptionblly) to tif stub.
     *
     * @sindf 1.1
     * @dfprfdbtfd 1.2 stylf stubs no longfr usf tiis mftiod. Instfbd of
     * using b sfqufndf of mftiod dblls to tif rfmotf rfffrfndf
     * (<dodf>nfwCbll</dodf>, <dodf>invokf</dodf>, bnd <dodf>donf</dodf>), b
     * stub usfs b singlf mftiod, <dodf>invokf(Rfmotf, Mftiod, Objfdt[],
     * int)</dodf>, on tif rfmotf rfffrfndf to dbrry out pbrbmftfr
     * mbrsiblling, rfmotf mftiod fxfduting bnd unmbrsiblling of tif rfturn
     * vbluf.
     *
     * @pbrbm dbll objfdt rfprfsfnting rfmotf dbll
     * @tirows RfmotfExdfption if rfmotf frror oddurs during dbll dlfbnup
     * @sff #invokf(Rfmotf,jbvb.lbng.rfflfdt.Mftiod,Objfdt[],long)
     */
    @Dfprfdbtfd
    void donf(RfmotfCbll dbll) tirows RfmotfExdfption;

    /**
     * Rfturns tif dlbss nbmf of tif rff typf to bf sfriblizfd onto
     * tif strfbm 'out'.
     * @pbrbm out tif output strfbm to wiidi tif rfffrfndf will bf sfriblizfd
     * @rfturn tif dlbss nbmf (witiout pbdkbgf qublifidbtion) of tif rfffrfndf
     * typf
     * @sindf 1.1
     */
    String gftRffClbss(jbvb.io.ObjfdtOutput out);

    /**
     * Rfturns b ibsidodf for b rfmotf objfdt.  Two rfmotf objfdt stubs
     * tibt rfffr to tif sbmf rfmotf objfdt will ibvf tif sbmf ibsi dodf
     * (in ordfr to support rfmotf objfdts bs kfys in ibsi tbblfs).
     *
     * @rfturn rfmotf objfdt ibsidodf
     * @sff             jbvb.util.Hbsitbblf
     * @sindf 1.1
     */
    int rfmotfHbsiCodf();

    /**
     * Compbrfs two rfmotf objfdts for fqublity.
     * Rfturns b boolfbn tibt indidbtfs wiftifr tiis rfmotf objfdt is
     * fquivblfnt to tif spfdififd Objfdt. Tiis mftiod is usfd wifn b
     * rfmotf objfdt is storfd in b ibsitbblf.
     * @pbrbm   obj     tif Objfdt to dompbrf witi
     * @rfturn  truf if tifsf Objfdts brf fqubl; fblsf otifrwisf.
     * @sff             jbvb.util.Hbsitbblf
     * @sindf 1.1
     */
    boolfbn rfmotfEqubls(RfmotfRff obj);

    /**
     * Rfturns b String tibt rfprfsfnts tif rfffrfndf of tiis rfmotf
     * objfdt.
     * @rfturn string rfprfsfnting rfmotf objfdt rfffrfndf
     * @sindf 1.1
     */
    String rfmotfToString();

}
