/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.rfflfdt.misd.MftiodUtil;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Tif <dodf>EvfntHbndlfr</dodf> dlbss providfs
 * support for dynbmidblly gfnfrbting fvfnt listfnfrs wiosf mftiods
 * fxfdutf b simplf stbtfmfnt involving bn indoming fvfnt objfdt
 * bnd b tbrgft objfdt.
 * <p>
 * Tif <dodf>EvfntHbndlfr</dodf> dlbss is intfndfd to bf usfd by intfrbdtivf tools, sudi bs
 * bpplidbtion buildfrs, tibt bllow dfvflopfrs to mbkf donnfdtions bftwffn
 * bfbns. Typidblly donnfdtions brf mbdf from b usfr intfrfbdf bfbn
 * (tif fvfnt <fm>sourdf</fm>)
 * to bn bpplidbtion logid bfbn (tif <fm>tbrgft</fm>). Tif most ffffdtivf
 * donnfdtions of tiis kind isolbtf tif bpplidbtion logid from tif usfr
 * intfrfbdf.  For fxbmplf, tif <dodf>EvfntHbndlfr</dodf> for b
 * donnfdtion from b <dodf>JCifdkBox</dodf> to b mftiod
 * tibt bddfpts b boolfbn vbluf dbn dfbl witi fxtrbdting tif stbtf
 * of tif difdk box bnd pbssing it dirfdtly to tif mftiod so tibt
 * tif mftiod is isolbtfd from tif usfr intfrfbdf lbyfr.
 * <p>
 * Innfr dlbssfs brf bnotifr, morf gfnfrbl wby to ibndlf fvfnts from
 * usfr intfrfbdfs.  Tif <dodf>EvfntHbndlfr</dodf> dlbss
 * ibndlfs only b subsft of wibt is possiblf using innfr
 * dlbssfs. Howfvfr, <dodf>EvfntHbndlfr</dodf> works bfttfr
 * witi tif long-tfrm pfrsistfndf sdifmf tibn innfr dlbssfs.
 * Also, using <dodf>EvfntHbndlfr</dodf> in lbrgf bpplidbtions in
 * wiidi tif sbmf intfrfbdf is implfmfntfd mbny timfs dbn
 * rfdudf tif disk bnd mfmory footprint of tif bpplidbtion.
 * <p>
 * Tif rfbson tibt listfnfrs drfbtfd witi <dodf>EvfntHbndlfr</dodf>
 * ibvf sudi b smbll
 * footprint is tibt tif <dodf>Proxy</dodf> dlbss, on wiidi
 * tif <dodf>EvfntHbndlfr</dodf> rflifs, sibrfs implfmfntbtions
 * of idfntidbl
 * intfrfbdfs. For fxbmplf, if you usf
 * tif <dodf>EvfntHbndlfr</dodf> <dodf>drfbtf</dodf> mftiods to mbkf
 * bll tif <dodf>AdtionListfnfr</dodf>s in bn bpplidbtion,
 * bll tif bdtion listfnfrs will bf instbndfs of b singlf dlbss
 * (onf drfbtfd by tif <dodf>Proxy</dodf> dlbss).
 * In gfnfrbl, listfnfrs bbsfd on
 * tif <dodf>Proxy</dodf> dlbss rfquirf onf listfnfr dlbss
 * to bf drfbtfd pfr <fm>listfnfr typf</fm> (intfrfbdf),
 * wifrfbs tif innfr dlbss
 * bpprobdi rfquirfs onf dlbss to bf drfbtfd pfr <fm>listfnfr</fm>
 * (objfdt tibt implfmfnts tif intfrfbdf).
 *
 * <p>
 * You don't gfnfrblly dfbl dirfdtly witi <dodf>EvfntHbndlfr</dodf>
 * instbndfs.
 * Instfbd, you usf onf of tif <dodf>EvfntHbndlfr</dodf>
 * <dodf>drfbtf</dodf> mftiods to drfbtf
 * bn objfdt tibt implfmfnts b givfn listfnfr intfrfbdf.
 * Tiis listfnfr objfdt usfs bn <dodf>EvfntHbndlfr</dodf> objfdt
 * bfiind tif sdfnfs to fndbpsulbtf informbtion bbout tif
 * fvfnt, tif objfdt to bf sfnt b mfssbgf wifn tif fvfnt oddurs,
 * tif mfssbgf (mftiod) to bf sfnt, bnd bny brgumfnt
 * to tif mftiod.
 * Tif following sfdtion givfs fxbmplfs of iow to drfbtf listfnfr
 * objfdts using tif <dodf>drfbtf</dodf> mftiods.
 *
 * <i2>Exbmplfs of Using EvfntHbndlfr</i2>
 *
 * Tif simplfst usf of <dodf>EvfntHbndlfr</dodf> is to instbll
 * b listfnfr tibt dblls b mftiod on tif tbrgft objfdt witi no brgumfnts.
 * In tif following fxbmplf wf drfbtf bn <dodf>AdtionListfnfr</dodf>
 * tibt invokfs tif <dodf>toFront</dodf> mftiod on bn instbndf
 * of <dodf>jbvbx.swing.JFrbmf</dodf>.
 *
 * <blodkquotf>
 *<prf>
 *myButton.bddAdtionListfnfr(
 *    (AdtionListfnfr)EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, frbmf, "toFront"));
 *</prf>
 * </blodkquotf>
 *
 * Wifn <dodf>myButton</dodf> is prfssfd, tif stbtfmfnt
 * <dodf>frbmf.toFront()</dodf> will bf fxfdutfd.  Onf dould gft
 * tif sbmf ffffdt, witi somf bdditionbl dompilf-timf typf sbffty,
 * by dffining b nfw implfmfntbtion of tif <dodf>AdtionListfnfr</dodf>
 * intfrfbdf bnd bdding bn instbndf of it to tif button:
 *
 * <blodkquotf>
 *<prf>
//Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
 *myButton.bddAdtionListfnfr(nfw AdtionListfnfr() {
 *    publid void bdtionPfrformfd(AdtionEvfnt f) {
 *        frbmf.toFront();
 *    }
 *});
 *</prf>
 * </blodkquotf>
 *
 * Tif nfxt simplfst usf of <dodf>EvfntHbndlfr</dodf> is
 * to fxtrbdt b propfrty vbluf from tif first brgumfnt
 * of tif mftiod in tif listfnfr intfrfbdf (typidblly bn fvfnt objfdt)
 * bnd usf it to sft tif vbluf of b propfrty in tif tbrgft objfdt.
 * In tif following fxbmplf wf drfbtf bn <dodf>AdtionListfnfr</dodf> tibt
 * sfts tif <dodf>nfxtFodusbblfComponfnt</dodf> propfrty of tif tbrgft
 * (myButton) objfdt to tif vbluf of tif "sourdf" propfrty of tif fvfnt.
 *
 * <blodkquotf>
 *<prf>
 *EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, myButton, "nfxtFodusbblfComponfnt", "sourdf")
 *</prf>
 * </blodkquotf>
 *
 * Tiis would dorrfspond to tif following innfr dlbss implfmfntbtion:
 *
 * <blodkquotf>
 *<prf>
//Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
 *nfw AdtionListfnfr() {
 *    publid void bdtionPfrformfd(AdtionEvfnt f) {
 *        myButton.sftNfxtFodusbblfComponfnt((Componfnt)f.gftSourdf());
 *    }
 *}
 *</prf>
 * </blodkquotf>
 *
 * It's blso possiblf to drfbtf bn <dodf>EvfntHbndlfr</dodf> tibt
 * just pbssfs tif indoming fvfnt objfdt to tif tbrgft's bdtion.
 * If tif fourti <dodf>EvfntHbndlfr.drfbtf</dodf> brgumfnt is
 * bn fmpty string, tifn tif fvfnt is just pbssfd blong:
 *
 * <blodkquotf>
 *<prf>
 *EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, tbrgft, "doAdtionEvfnt", "")
 *</prf>
 * </blodkquotf>
 *
 * Tiis would dorrfspond to tif following innfr dlbss implfmfntbtion:
 *
 * <blodkquotf>
 *<prf>
//Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
 *nfw AdtionListfnfr() {
 *    publid void bdtionPfrformfd(AdtionEvfnt f) {
 *        tbrgft.doAdtionEvfnt(f);
 *    }
 *}
 *</prf>
 * </blodkquotf>
 *
 * Probbbly tif most dommon usf of <dodf>EvfntHbndlfr</dodf>
 * is to fxtrbdt b propfrty vbluf from tif
 * <fm>sourdf</fm> of tif fvfnt objfdt bnd sft tiis vbluf bs
 * tif vbluf of b propfrty of tif tbrgft objfdt.
 * In tif following fxbmplf wf drfbtf bn <dodf>AdtionListfnfr</dodf> tibt
 * sfts tif "lbbfl" propfrty of tif tbrgft
 * objfdt to tif vbluf of tif "tfxt" propfrty of tif
 * sourdf (tif vbluf of tif "sourdf" propfrty) of tif fvfnt.
 *
 * <blodkquotf>
 *<prf>
 *EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, myButton, "lbbfl", "sourdf.tfxt")
 *</prf>
 * </blodkquotf>
 *
 * Tiis would dorrfspond to tif following innfr dlbss implfmfntbtion:
 *
 * <blodkquotf>
 *<prf>
//Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
 *nfw AdtionListfnfr {
 *    publid void bdtionPfrformfd(AdtionEvfnt f) {
 *        myButton.sftLbbfl(((JTfxtFifld)f.gftSourdf()).gftTfxt());
 *    }
 *}
 *</prf>
 * </blodkquotf>
 *
 * Tif fvfnt propfrty mby bf "qublififd" witi bn brbitrbry numbfr
 * of propfrty prffixfs dflimitfd witi tif "." dibrbdtfr. Tif "qublifying"
 * nbmfs tibt bppfbr bfforf tif "." dibrbdtfrs brf tbkfn bs tif nbmfs of
 * propfrtifs tibt siould bf bpplifd, lfft-most first, to
 * tif fvfnt objfdt.
 * <p>
 * For fxbmplf, tif following bdtion listfnfr
 *
 * <blodkquotf>
 *<prf>
 *EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, tbrgft, "b", "b.d.d")
 *</prf>
 * </blodkquotf>
 *
 * migit bf writtfn bs tif following innfr dlbss
 * (bssuming bll tif propfrtifs ibd dbnonidbl gfttfr mftiods bnd
 * rfturnfd tif bppropribtf typfs):
 *
 * <blodkquotf>
 *<prf>
//Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
 *nfw AdtionListfnfr {
 *    publid void bdtionPfrformfd(AdtionEvfnt f) {
 *        tbrgft.sftA(f.gftB().gftC().isD());
 *    }
 *}
 *</prf>
 * </blodkquotf>
 * Tif tbrgft propfrty mby blso bf "qublififd" witi bn brbitrbry numbfr
 * of propfrty prffixs dflimitfd witi tif "." dibrbdtfr.  For fxbmplf, tif
 * following bdtion listfnfr:
 * <prf>
 *   EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, tbrgft, "b.b", "d.d")
 * </prf>
 * migit bf writtfn bs tif following innfr dlbss
 * (bssuming bll tif propfrtifs ibd dbnonidbl gfttfr mftiods bnd
 * rfturnfd tif bppropribtf typfs):
 * <prf>
 *   //Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
 *   nfw AdtionListfnfr {
 *     publid void bdtionPfrformfd(AdtionEvfnt f) {
 *         tbrgft.gftA().sftB(f.gftC().isD());
 *    }
 *}
 *</prf>
 * <p>
 * As <dodf>EvfntHbndlfr</dodf> ultimbtfly rflifs on rfflfdtion to invokf
 * b mftiod wf rfdommfnd bgbinst tbrgfting bn ovfrlobdfd mftiod.  For fxbmplf,
 * if tif tbrgft is bn instbndf of tif dlbss <dodf>MyTbrgft</dodf> wiidi is
 * dffinfd bs:
 * <prf>
 *   publid dlbss MyTbrgft {
 *     publid void doIt(String);
 *     publid void doIt(Objfdt);
 *   }
 * </prf>
 * Tifn tif mftiod <dodf>doIt</dodf> is ovfrlobdfd.  EvfntHbndlfr will invokf
 * tif mftiod tibt is bppropribtf bbsfd on tif sourdf.  If tif sourdf is
 * null, tifn fitifr mftiod is bppropribtf bnd tif onf tibt is invokfd is
 * undffinfd.  For tibt rfbson wf rfdommfnd bgbinst tbrgfting ovfrlobdfd
 * mftiods.
 *
 * @sff jbvb.lbng.rfflfdt.Proxy
 * @sff jbvb.util.EvfntObjfdt
 *
 * @sindf 1.4
 *
 * @butior Mbrk Dbvidson
 * @butior Piilip Milnf
 * @butior Hbns Mullfr
 *
 */
publid dlbss EvfntHbndlfr implfmfnts InvodbtionHbndlfr {
    privbtf Objfdt tbrgft;
    privbtf String bdtion;
    privbtf finbl String fvfntPropfrtyNbmf;
    privbtf finbl String listfnfrMftiodNbmf;
    privbtf finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();

    /**
     * Crfbtfs b nfw <dodf>EvfntHbndlfr</dodf> objfdt;
     * you gfnfrblly usf onf of tif <dodf>drfbtf</dodf> mftiods
     * instfbd of invoking tiis donstrudtor dirfdtly.  Rfffr to
     * {@link jbvb.bfbns.EvfntHbndlfr#drfbtf(Clbss, Objfdt, String, String)
     * tif gfnfrbl vfrsion of drfbtf} for b domplftf dfsdription of
     * tif <dodf>fvfntPropfrtyNbmf</dodf> bnd <dodf>listfnfrMftiodNbmf</dodf>
     * pbrbmftfr.
     *
     * @pbrbm tbrgft tif objfdt tibt will pfrform tif bdtion
     * @pbrbm bdtion tif nbmf of b (possibly qublififd) propfrty or mftiod on
     *        tif tbrgft
     * @pbrbm fvfntPropfrtyNbmf tif (possibly qublififd) nbmf of b rfbdbblf propfrty of tif indoming fvfnt
     * @pbrbm listfnfrMftiodNbmf tif nbmf of tif mftiod in tif listfnfr intfrfbdf tibt siould triggfr tif bdtion
     *
     * @tirows NullPointfrExdfption if <dodf>tbrgft</dodf> is null
     * @tirows NullPointfrExdfption if <dodf>bdtion</dodf> is null
     *
     * @sff EvfntHbndlfr
     * @sff #drfbtf(Clbss, Objfdt, String, String, String)
     * @sff #gftTbrgft
     * @sff #gftAdtion
     * @sff #gftEvfntPropfrtyNbmf
     * @sff #gftListfnfrMftiodNbmf
     */
    @ConstrudtorPropfrtifs({"tbrgft", "bdtion", "fvfntPropfrtyNbmf", "listfnfrMftiodNbmf"})
    publid EvfntHbndlfr(Objfdt tbrgft, String bdtion, String fvfntPropfrtyNbmf, String listfnfrMftiodNbmf) {
        tiis.tbrgft = tbrgft;
        tiis.bdtion = bdtion;
        if (tbrgft == null) {
            tirow nfw NullPointfrExdfption("tbrgft must bf non-null");
        }
        if (bdtion == null) {
            tirow nfw NullPointfrExdfption("bdtion must bf non-null");
        }
        tiis.fvfntPropfrtyNbmf = fvfntPropfrtyNbmf;
        tiis.listfnfrMftiodNbmf = listfnfrMftiodNbmf;
    }

    /**
     * Rfturns tif objfdt to wiidi tiis fvfnt ibndlfr will sfnd b mfssbgf.
     *
     * @rfturn tif tbrgft of tiis fvfnt ibndlfr
     * @sff #EvfntHbndlfr(Objfdt, String, String, String)
     */
    publid Objfdt gftTbrgft()  {
        rfturn tbrgft;
    }

    /**
     * Rfturns tif nbmf of tif tbrgft's writbblf propfrty
     * tibt tiis fvfnt ibndlfr will sft,
     * or tif nbmf of tif mftiod tibt tiis fvfnt ibndlfr
     * will invokf on tif tbrgft.
     *
     * @rfturn tif bdtion of tiis fvfnt ibndlfr
     * @sff #EvfntHbndlfr(Objfdt, String, String, String)
     */
    publid String gftAdtion()  {
        rfturn bdtion;
    }

    /**
     * Rfturns tif propfrty of tif fvfnt tibt siould bf
     * usfd in tif bdtion bpplifd to tif tbrgft.
     *
     * @rfturn tif propfrty of tif fvfnt
     *
     * @sff #EvfntHbndlfr(Objfdt, String, String, String)
     */
    publid String gftEvfntPropfrtyNbmf()  {
        rfturn fvfntPropfrtyNbmf;
    }

    /**
     * Rfturns tif nbmf of tif mftiod tibt will triggfr tif bdtion.
     * A rfturn vbluf of <dodf>null</dodf> signififs tibt bll mftiods in tif
     * listfnfr intfrfbdf triggfr tif bdtion.
     *
     * @rfturn tif nbmf of tif mftiod tibt will triggfr tif bdtion
     *
     * @sff #EvfntHbndlfr(Objfdt, String, String, String)
     */
    publid String gftListfnfrMftiodNbmf()  {
        rfturn listfnfrMftiodNbmf;
    }

    privbtf Objfdt bpplyGfttfrs(Objfdt tbrgft, String gfttfrs) {
        if (gfttfrs == null || gfttfrs.fqubls("")) {
            rfturn tbrgft;
        }
        int firstDot = gfttfrs.indfxOf('.');
        if (firstDot == -1) {
            firstDot = gfttfrs.lfngti();
        }
        String first = gfttfrs.substring(0, firstDot);
        String rfst = gfttfrs.substring(Mbti.min(firstDot + 1, gfttfrs.lfngti()));

        try {
            Mftiod gfttfr = null;
            if (tbrgft != null) {
                gfttfr = Stbtfmfnt.gftMftiod(tbrgft.gftClbss(),
                                      "gft" + NbmfGfnfrbtor.dbpitblizf(first),
                                      nfw Clbss<?>[]{});
                if (gfttfr == null) {
                    gfttfr = Stbtfmfnt.gftMftiod(tbrgft.gftClbss(),
                                   "is" + NbmfGfnfrbtor.dbpitblizf(first),
                                   nfw Clbss<?>[]{});
                }
                if (gfttfr == null) {
                    gfttfr = Stbtfmfnt.gftMftiod(tbrgft.gftClbss(), first, nfw Clbss<?>[]{});
                }
            }
            if (gfttfr == null) {
                tirow nfw RuntimfExdfption("No mftiod dbllfd: " + first +
                                           " dffinfd on " + tbrgft);
            }
            Objfdt nfwTbrgft = MftiodUtil.invokf(gfttfr, tbrgft, nfw Objfdt[]{});
            rfturn bpplyGfttfrs(nfwTbrgft, rfst);
        }
        dbtdi (Exdfption f) {
            tirow nfw RuntimfExdfption("Fbilfd to dbll mftiod: " + first +
                                       " on " + tbrgft, f);
        }
    }

    /**
     * Extrbdt tif bppropribtf propfrty vbluf from tif fvfnt bnd
     * pbss it to tif bdtion bssodibtfd witi
     * tiis <dodf>EvfntHbndlfr</dodf>.
     *
     * @pbrbm proxy tif proxy objfdt
     * @pbrbm mftiod tif mftiod in tif listfnfr intfrfbdf
     * @rfturn tif rfsult of bpplying tif bdtion to tif tbrgft
     *
     * @sff EvfntHbndlfr
     */
    publid Objfdt invokf(finbl Objfdt proxy, finbl Mftiod mftiod, finbl Objfdt[] brgumfnts) {
        AddfssControlContfxt bdd = tiis.bdd;
        if ((bdd == null) && (Systfm.gftSfdurityMbnbgfr() != null)) {
            tirow nfw SfdurityExdfption("AddfssControlContfxt is not sft");
        }
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                rfturn invokfIntfrnbl(proxy, mftiod, brgumfnts);
            }
        }, bdd);
    }

    privbtf Objfdt invokfIntfrnbl(Objfdt proxy, Mftiod mftiod, Objfdt[] brgumfnts) {
        String mftiodNbmf = mftiod.gftNbmf();
        if (mftiod.gftDfdlbringClbss() == Objfdt.dlbss)  {
            // Hbndlf tif Objfdt publid mftiods.
            if (mftiodNbmf.fqubls("ibsiCodf"))  {
                rfturn Systfm.idfntityHbsiCodf(proxy);
            } flsf if (mftiodNbmf.fqubls("fqubls")) {
                rfturn (proxy == brgumfnts[0] ? Boolfbn.TRUE : Boolfbn.FALSE);
            } flsf if (mftiodNbmf.fqubls("toString")) {
                rfturn proxy.gftClbss().gftNbmf() + '@' + Intfgfr.toHfxString(proxy.ibsiCodf());
            }
        }

        if (listfnfrMftiodNbmf == null || listfnfrMftiodNbmf.fqubls(mftiodNbmf)) {
            Clbss<?>[] brgTypfs = null;
            Objfdt[] nfwArgs = null;

            if (fvfntPropfrtyNbmf == null) {     // Nullbry mftiod.
                nfwArgs = nfw Objfdt[]{};
                brgTypfs = nfw Clbss<?>[]{};
            }
            flsf {
                Objfdt input = bpplyGfttfrs(brgumfnts[0], gftEvfntPropfrtyNbmf());
                nfwArgs = nfw Objfdt[]{input};
                brgTypfs = nfw Clbss<?>[]{input == null ? null :
                                       input.gftClbss()};
            }
            try {
                int lbstDot = bdtion.lbstIndfxOf('.');
                if (lbstDot != -1) {
                    tbrgft = bpplyGfttfrs(tbrgft, bdtion.substring(0, lbstDot));
                    bdtion = bdtion.substring(lbstDot + 1);
                }
                Mftiod tbrgftMftiod = Stbtfmfnt.gftMftiod(
                             tbrgft.gftClbss(), bdtion, brgTypfs);
                if (tbrgftMftiod == null) {
                    tbrgftMftiod = Stbtfmfnt.gftMftiod(tbrgft.gftClbss(),
                             "sft" + NbmfGfnfrbtor.dbpitblizf(bdtion), brgTypfs);
                }
                if (tbrgftMftiod == null) {
                    String brgTypfString = (brgTypfs.lfngti == 0)
                        ? " witi no brgumfnts"
                        : " witi brgumfnt " + brgTypfs[0];
                    tirow nfw RuntimfExdfption(
                        "No mftiod dbllfd " + bdtion + " on " +
                        tbrgft.gftClbss() + brgTypfString);
                }
                rfturn MftiodUtil.invokf(tbrgftMftiod, tbrgft, nfwArgs);
            }
            dbtdi (IllfgblAddfssExdfption fx) {
                tirow nfw RuntimfExdfption(fx);
            }
            dbtdi (InvodbtionTbrgftExdfption fx) {
                Tirowbblf ti = fx.gftTbrgftExdfption();
                tirow (ti instbndfof RuntimfExdfption)
                        ? (RuntimfExdfption) ti
                        : nfw RuntimfExdfption(ti);
            }
        }
        rfturn null;
    }

    /**
     * Crfbtfs bn implfmfntbtion of <dodf>listfnfrIntfrfbdf</dodf> in wiidi
     * <fm>bll</fm> of tif mftiods in tif listfnfr intfrfbdf bpply
     * tif ibndlfr's <dodf>bdtion</dodf> to tif <dodf>tbrgft</dodf>. Tiis
     * mftiod is implfmfntfd by dblling tif otifr, morf gfnfrbl,
     * implfmfntbtion of tif <dodf>drfbtf</dodf> mftiod witi boti
     * tif <dodf>fvfntPropfrtyNbmf</dodf> bnd tif <dodf>listfnfrMftiodNbmf</dodf>
     * tbking tif vbluf <dodf>null</dodf>. Rfffr to
     * {@link jbvb.bfbns.EvfntHbndlfr#drfbtf(Clbss, Objfdt, String, String)
     * tif gfnfrbl vfrsion of drfbtf} for b domplftf dfsdription of
     * tif <dodf>bdtion</dodf> pbrbmftfr.
     * <p>
     * To drfbtf bn <dodf>AdtionListfnfr</dodf> tibt siows b
     * <dodf>JDiblog</dodf> witi <dodf>diblog.siow()</dodf>,
     * onf dbn writf:
     *
     *<blodkquotf>
     *<prf>
     *EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, diblog, "siow")
     *</prf>
     *</blodkquotf>
     *
     * @pbrbm <T> tif typf to drfbtf
     * @pbrbm listfnfrIntfrfbdf tif listfnfr intfrfbdf to drfbtf b proxy for
     * @pbrbm tbrgft tif objfdt tibt will pfrform tif bdtion
     * @pbrbm bdtion tif nbmf of b (possibly qublififd) propfrty or mftiod on
     *        tif tbrgft
     * @rfturn bn objfdt tibt implfmfnts <dodf>listfnfrIntfrfbdf</dodf>
     *
     * @tirows NullPointfrExdfption if <dodf>listfnfrIntfrfbdf</dodf> is null
     * @tirows NullPointfrExdfption if <dodf>tbrgft</dodf> is null
     * @tirows NullPointfrExdfption if <dodf>bdtion</dodf> is null
     *
     * @sff #drfbtf(Clbss, Objfdt, String, String)
     */
    publid stbtid <T> T drfbtf(Clbss<T> listfnfrIntfrfbdf,
                               Objfdt tbrgft, String bdtion)
    {
        rfturn drfbtf(listfnfrIntfrfbdf, tbrgft, bdtion, null, null);
    }

    /**
    /**
     * Crfbtfs bn implfmfntbtion of <dodf>listfnfrIntfrfbdf</dodf> in wiidi
     * <fm>bll</fm> of tif mftiods pbss tif vbluf of tif fvfnt
     * fxprfssion, <dodf>fvfntPropfrtyNbmf</dodf>, to tif finbl mftiod in tif
     * stbtfmfnt, <dodf>bdtion</dodf>, wiidi is bpplifd to tif <dodf>tbrgft</dodf>.
     * Tiis mftiod is implfmfntfd by dblling tif
     * morf gfnfrbl, implfmfntbtion of tif <dodf>drfbtf</dodf> mftiod witi
     * tif <dodf>listfnfrMftiodNbmf</dodf> tbking tif vbluf <dodf>null</dodf>.
     * Rfffr to
     * {@link jbvb.bfbns.EvfntHbndlfr#drfbtf(Clbss, Objfdt, String, String)
     * tif gfnfrbl vfrsion of drfbtf} for b domplftf dfsdription of
     * tif <dodf>bdtion</dodf> bnd <dodf>fvfntPropfrtyNbmf</dodf> pbrbmftfrs.
     * <p>
     * To drfbtf bn <dodf>AdtionListfnfr</dodf> tibt sfts tif
     * tif tfxt of b <dodf>JLbbfl</dodf> to tif tfxt vbluf of
     * tif <dodf>JTfxtFifld</dodf> sourdf of tif indoming fvfnt,
     * you dbn usf tif following dodf:
     *
     *<blodkquotf>
     *<prf>
     *EvfntHbndlfr.drfbtf(AdtionListfnfr.dlbss, lbbfl, "tfxt", "sourdf.tfxt");
     *</prf>
     *</blodkquotf>
     *
     * Tiis is fquivblfnt to tif following dodf:
     *<blodkquotf>
     *<prf>
//Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
     *nfw AdtionListfnfr() {
     *    publid void bdtionPfrformfd(AdtionEvfnt fvfnt) {
     *        lbbfl.sftTfxt(((JTfxtFifld)(fvfnt.gftSourdf())).gftTfxt());
     *     }
     *};
     *</prf>
     *</blodkquotf>
     *
     * @pbrbm <T> tif typf to drfbtf
     * @pbrbm listfnfrIntfrfbdf tif listfnfr intfrfbdf to drfbtf b proxy for
     * @pbrbm tbrgft tif objfdt tibt will pfrform tif bdtion
     * @pbrbm bdtion tif nbmf of b (possibly qublififd) propfrty or mftiod on
     *        tif tbrgft
     * @pbrbm fvfntPropfrtyNbmf tif (possibly qublififd) nbmf of b rfbdbblf propfrty of tif indoming fvfnt
     *
     * @rfturn bn objfdt tibt implfmfnts <dodf>listfnfrIntfrfbdf</dodf>
     *
     * @tirows NullPointfrExdfption if <dodf>listfnfrIntfrfbdf</dodf> is null
     * @tirows NullPointfrExdfption if <dodf>tbrgft</dodf> is null
     * @tirows NullPointfrExdfption if <dodf>bdtion</dodf> is null
     *
     * @sff #drfbtf(Clbss, Objfdt, String, String, String)
     */
    publid stbtid <T> T drfbtf(Clbss<T> listfnfrIntfrfbdf,
                               Objfdt tbrgft, String bdtion,
                               String fvfntPropfrtyNbmf)
    {
        rfturn drfbtf(listfnfrIntfrfbdf, tbrgft, bdtion, fvfntPropfrtyNbmf, null);
    }

    /**
     * Crfbtfs bn implfmfntbtion of <dodf>listfnfrIntfrfbdf</dodf> in wiidi
     * tif mftiod nbmfd <dodf>listfnfrMftiodNbmf</dodf>
     * pbssfs tif vbluf of tif fvfnt fxprfssion, <dodf>fvfntPropfrtyNbmf</dodf>,
     * to tif finbl mftiod in tif stbtfmfnt, <dodf>bdtion</dodf>, wiidi
     * is bpplifd to tif <dodf>tbrgft</dodf>. All of tif otifr listfnfr
     * mftiods do notiing.
     * <p>
     * Tif <dodf>fvfntPropfrtyNbmf</dodf> string is usfd to fxtrbdt b vbluf
     * from tif indoming fvfnt objfdt tibt is pbssfd to tif tbrgft
     * mftiod.  Tif dommon dbsf is tif tbrgft mftiod tbkfs no brgumfnts, in
     * wiidi dbsf b vbluf of null siould bf usfd for tif
     * <dodf>fvfntPropfrtyNbmf</dodf>.  Altfrnbtivfly if you wbnt
     * tif indoming fvfnt objfdt pbssfd dirfdtly to tif tbrgft mftiod usf
     * tif fmpty string.
     * Tif formbt of tif <dodf>fvfntPropfrtyNbmf</dodf> string is b sfqufndf of
     * mftiods or propfrtifs wifrf fbdi mftiod or
     * propfrty is bpplifd to tif vbluf rfturnfd by tif prfdfding mftiod
     * stbrting from tif indoming fvfnt objfdt.
     * Tif syntbx is: <dodf>propfrtyNbmf{.propfrtyNbmf}*</dodf>
     * wifrf <dodf>propfrtyNbmf</dodf> mbtdifs b mftiod or
     * propfrty.  For fxbmplf, to fxtrbdt tif <dodf>point</dodf>
     * propfrty from b <dodf>MousfEvfnt</dodf>, you dould usf fitifr
     * <dodf>"point"</dodf> or <dodf>"gftPoint"</dodf> bs tif
     * <dodf>fvfntPropfrtyNbmf</dodf>.  To fxtrbdt tif "tfxt" propfrty from
     * b <dodf>MousfEvfnt</dodf> witi b <dodf>JLbbfl</dodf> sourdf usf bny
     * of tif following bs <dodf>fvfntPropfrtyNbmf</dodf>:
     * <dodf>"sourdf.tfxt"</dodf>,
     * <dodf>"gftSourdf.tfxt"</dodf> <dodf>"gftSourdf.gftTfxt"</dodf> or
     * <dodf>"sourdf.gftTfxt"</dodf>.  If b mftiod dbn not bf found, or bn
     * fxdfption is gfnfrbtfd bs pbrt of invoking b mftiod b
     * <dodf>RuntimfExdfption</dodf> will bf tirown bt dispbtdi timf.  For
     * fxbmplf, if tif indoming fvfnt objfdt is null, bnd
     * <dodf>fvfntPropfrtyNbmf</dodf> is non-null bnd not fmpty, b
     * <dodf>RuntimfExdfption</dodf> will bf tirown.
     * <p>
     * Tif <dodf>bdtion</dodf> brgumfnt is of tif sbmf formbt bs tif
     * <dodf>fvfntPropfrtyNbmf</dodf> brgumfnt wifrf tif lbst propfrty nbmf
     * idfntififs fitifr b mftiod nbmf or writbblf propfrty.
     * <p>
     * If tif <dodf>listfnfrMftiodNbmf</dodf> is <dodf>null</dodf>
     * <fm>bll</fm> mftiods in tif intfrfbdf triggfr tif <dodf>bdtion</dodf> to bf
     * fxfdutfd on tif <dodf>tbrgft</dodf>.
     * <p>
     * For fxbmplf, to drfbtf b <dodf>MousfListfnfr</dodf> tibt sfts tif tbrgft
     * objfdt's <dodf>origin</dodf> propfrty to tif indoming <dodf>MousfEvfnt</dodf>'s
     * lodbtion (tibt's tif vbluf of <dodf>mousfEvfnt.gftPoint()</dodf>) fbdi
     * timf b mousf button is prfssfd, onf would writf:
     *<blodkquotf>
     *<prf>
     *EvfntHbndlfr.drfbtf(MousfListfnfr.dlbss, tbrgft, "origin", "point", "mousfPrfssfd");
     *</prf>
     *</blodkquotf>
     *
     * Tiis is dompbrbblf to writing b <dodf>MousfListfnfr</dodf> in wiidi bll
     * of tif mftiods fxdfpt <dodf>mousfPrfssfd</dodf> brf no-ops:
     *
     *<blodkquotf>
     *<prf>
//Equivblfnt dodf using bn innfr dlbss instfbd of EvfntHbndlfr.
     *nfw MousfAdbptfr() {
     *    publid void mousfPrfssfd(MousfEvfnt f) {
     *        tbrgft.sftOrigin(f.gftPoint());
     *    }
     *};
     * </prf>
     *</blodkquotf>
     *
     * @pbrbm <T> tif typf to drfbtf
     * @pbrbm listfnfrIntfrfbdf tif listfnfr intfrfbdf to drfbtf b proxy for
     * @pbrbm tbrgft tif objfdt tibt will pfrform tif bdtion
     * @pbrbm bdtion tif nbmf of b (possibly qublififd) propfrty or mftiod on
     *        tif tbrgft
     * @pbrbm fvfntPropfrtyNbmf tif (possibly qublififd) nbmf of b rfbdbblf propfrty of tif indoming fvfnt
     * @pbrbm listfnfrMftiodNbmf tif nbmf of tif mftiod in tif listfnfr intfrfbdf tibt siould triggfr tif bdtion
     *
     * @rfturn bn objfdt tibt implfmfnts <dodf>listfnfrIntfrfbdf</dodf>
     *
     * @tirows NullPointfrExdfption if <dodf>listfnfrIntfrfbdf</dodf> is null
     * @tirows NullPointfrExdfption if <dodf>tbrgft</dodf> is null
     * @tirows NullPointfrExdfption if <dodf>bdtion</dodf> is null
     *
     * @sff EvfntHbndlfr
     */
    publid stbtid <T> T drfbtf(Clbss<T> listfnfrIntfrfbdf,
                               Objfdt tbrgft, String bdtion,
                               String fvfntPropfrtyNbmf,
                               String listfnfrMftiodNbmf)
    {
        // Crfbtf tiis first to vfrify tbrgft/bdtion brf non-null
        finbl EvfntHbndlfr ibndlfr = nfw EvfntHbndlfr(tbrgft, bdtion,
                                                     fvfntPropfrtyNbmf,
                                                     listfnfrMftiodNbmf);
        if (listfnfrIntfrfbdf == null) {
            tirow nfw NullPointfrExdfption(
                          "listfnfrIntfrfbdf must bf non-null");
        }
        finbl ClbssLobdfr lobdfr = gftClbssLobdfr(listfnfrIntfrfbdf);
        finbl Clbss<?>[] intfrfbdfs = {listfnfrIntfrfbdf};
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<T>() {
            @SupprfssWbrnings("undifdkfd")
            publid T run() {
                rfturn (T) Proxy.nfwProxyInstbndf(lobdfr, intfrfbdfs, ibndlfr);
            }
        });
    }

    privbtf stbtid ClbssLobdfr gftClbssLobdfr(Clbss<?> typf) {
        RfflfdtUtil.difdkPbdkbgfAddfss(typf);
        ClbssLobdfr lobdfr = typf.gftClbssLobdfr();
        if (lobdfr == null) {
            lobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr(); // bvoid usf of BCP
            if (lobdfr == null) {
                lobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
            }
        }
        rfturn lobdfr;
    }
}
