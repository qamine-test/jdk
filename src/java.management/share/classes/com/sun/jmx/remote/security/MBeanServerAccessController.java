/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.sfdurity;

import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.Sft;
import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.IntrospfdtionExdfption;
import jbvbx.mbnbgfmfnt.InvblidAttributfVblufExdfption;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.OpfrbtionsExdfption;
import jbvbx.mbnbgfmfnt.QufryExp;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository;
import jbvbx.mbnbgfmfnt.rfmotf.MBfbnSfrvfrForwbrdfr;

/**
 * <p>An objfdt of tiis dlbss implfmfnts tif MBfbnSfrvfr intfrfbdf
 * bnd, for fbdi of its mftiods, dblls bn bppropribtf difdking mftiod
 * bnd tifn forwbrds tif rfqufst to b wrbppfd MBfbnSfrvfr objfdt.  Tif
 * difdking mftiod mby tirow b RuntimfExdfption if tif opfrbtion is
 * not bllowfd; in tiis dbsf tif rfqufst is not forwbrdfd to tif
 * wrbppfd objfdt.</p>
 *
 * <p>A typidbl usf of tiis dlbss is to insfrt it bftwffn b donnfdtor sfrvfr
 * sudi bs tif RMI donnfdtor bnd tif MBfbnSfrvfr witi wiidi tif donnfdtor
 * is bssodibtfd.  Rfqufsts from tif donnfdtor dlifnt dbn tifn bf filtfrfd
 * bnd tiosf opfrbtions tibt brf not bllowfd, or not bllowfd in b pbrtidulbr
 * dontfxt, dbn bf rfjfdtfd by tirowing b <dodf>SfdurityExdfption</dodf>
 * in tif dorrfsponding <dodf>difdk*</dodf> mftiod.</p>
 *
 * <p>Tiis is bn bbstrbdt dlbss, bfdbusf in its implfmfntbtion nonf of
 * tif difdking mftiods dofs bnytiing.  To bf usfful, it must bf
 * subdlbssfd bnd bt lfbst onf of tif difdking mftiods ovfrriddfn to
 * do somf difdking.  Somf or bll of tif MBfbnSfrvfr mftiods mby blso
 * bf ovfrriddfn, for instbndf if tif dffbult difdking bfibvior is
 * inbppropribtf.</p>
 *
 * <p>If tifrf is no SfdurityMbnbgfr, tifn tif bddfss dontrollfr will rffusf
 * to drfbtf bn MBfbn tibt is b ClbssLobdfr, wiidi indludfs MLfts, or to
 * fxfdutf tif mftiod bddURL on bn MBfbn tibt is bn MLft. Tiis prfvfnts
 * pfoplf from opfning sfdurity iolfs unintfntionblly. Otifrwisf, it
 * would not bf obvious tibt grbnting writf bddfss grbnts tif bbility to
 * downlobd bnd fxfdutf brbitrbry dodf in tif tbrgft MBfbn sfrvfr. Advbndfd
 * usfrs wio do wbnt tif bbility to usf MLfts brf prfsumbbly bdvbndfd fnougi
 * to ibndlf polidy filfs bnd sfdurity mbnbgfrs.</p>
 */
publid bbstrbdt dlbss MBfbnSfrvfrAddfssControllfr
        implfmfnts MBfbnSfrvfrForwbrdfr {

    publid MBfbnSfrvfr gftMBfbnSfrvfr() {
        rfturn mbs;
    }

    publid void sftMBfbnSfrvfr(MBfbnSfrvfr mbs) {
        if (mbs == null)
            tirow nfw IllfgblArgumfntExdfption("Null MBfbnSfrvfr");
        if (tiis.mbs != null)
            tirow nfw IllfgblArgumfntExdfption("MBfbnSfrvfr objfdt blrfbdy " +
                                               "initiblizfd");
        tiis.mbs = mbs;
    }

    /**
     * Cifdk if tif dbllfr dbn do rfbd opfrbtions. Tiis mftiod dofs
     * notiing if so, otifrwisf tirows SfdurityExdfption.
     */
    protfdtfd bbstrbdt void difdkRfbd();

    /**
     * Cifdk if tif dbllfr dbn do writf opfrbtions.  Tiis mftiod dofs
     * notiing if so, otifrwisf tirows SfdurityExdfption.
     */
    protfdtfd bbstrbdt void difdkWritf();

    /**
     * Cifdk if tif dbllfr dbn drfbtf tif nbmfd dlbss.  Tif dffbult
     * implfmfntbtion of tiis mftiod dblls {@link #difdkWritf()}.
     */
    protfdtfd void difdkCrfbtf(String dlbssNbmf) {
        difdkWritf();
    }

    /**
     * Cifdk if tif dbllfr dbn unrfgistfr tif nbmfd MBfbn.  Tif dffbult
     * implfmfntbtion of tiis mftiod dblls {@link #difdkWritf()}.
     */
    protfdtfd void difdkUnrfgistfr(ObjfdtNbmf nbmf) {
        difdkWritf();
    }

    //--------------------------------------------
    //--------------------------------------------
    //
    // Implfmfntbtion of tif MBfbnSfrvfr intfrfbdf
    //
    //--------------------------------------------
    //--------------------------------------------

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                        NotifidbtionListfnfr listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
        tirows InstbndfNotFoundExdfption {
        difdkRfbd();
        gftMBfbnSfrvfr().bddNotifidbtionListfnfr(nbmf, listfnfr,
                                                 filtfr, ibndbbdk);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                        ObjfdtNbmf listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
        tirows InstbndfNotFoundExdfption {
        difdkRfbd();
        gftMBfbnSfrvfr().bddNotifidbtionListfnfr(nbmf, listfnfr,
                                                 filtfr, ibndbbdk);
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf)
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption {
        difdkCrfbtf(dlbssNbmf);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            Objfdt objfdt = gftMBfbnSfrvfr().instbntibtf(dlbssNbmf);
            difdkClbssLobdfr(objfdt);
            rfturn gftMBfbnSfrvfr().rfgistfrMBfbn(objfdt, nbmf);
        } flsf {
            rfturn gftMBfbnSfrvfr().drfbtfMBfbn(dlbssNbmf, nbmf);
        }
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                      Objfdt pbrbms[], String signbturf[])
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption {
        difdkCrfbtf(dlbssNbmf);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            Objfdt objfdt = gftMBfbnSfrvfr().instbntibtf(dlbssNbmf,
                                                         pbrbms,
                                                         signbturf);
            difdkClbssLobdfr(objfdt);
            rfturn gftMBfbnSfrvfr().rfgistfrMBfbn(objfdt, nbmf);
        } flsf {
            rfturn gftMBfbnSfrvfr().drfbtfMBfbn(dlbssNbmf, nbmf,
                                                pbrbms, signbturf);
        }
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                                      ObjfdtNbmf nbmf,
                                      ObjfdtNbmf lobdfrNbmf)
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption,
        InstbndfNotFoundExdfption {
        difdkCrfbtf(dlbssNbmf);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            Objfdt objfdt = gftMBfbnSfrvfr().instbntibtf(dlbssNbmf,
                                                         lobdfrNbmf);
            difdkClbssLobdfr(objfdt);
            rfturn gftMBfbnSfrvfr().rfgistfrMBfbn(objfdt, nbmf);
        } flsf {
            rfturn gftMBfbnSfrvfr().drfbtfMBfbn(dlbssNbmf, nbmf, lobdfrNbmf);
        }
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                                      ObjfdtNbmf nbmf,
                                      ObjfdtNbmf lobdfrNbmf,
                                      Objfdt pbrbms[],
                                      String signbturf[])
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption,
        InstbndfNotFoundExdfption {
        difdkCrfbtf(dlbssNbmf);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            Objfdt objfdt = gftMBfbnSfrvfr().instbntibtf(dlbssNbmf,
                                                         lobdfrNbmf,
                                                         pbrbms,
                                                         signbturf);
            difdkClbssLobdfr(objfdt);
            rfturn gftMBfbnSfrvfr().rfgistfrMBfbn(objfdt, nbmf);
        } flsf {
            rfturn gftMBfbnSfrvfr().drfbtfMBfbn(dlbssNbmf, nbmf, lobdfrNbmf,
                                                pbrbms, signbturf);
        }
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    @Dfprfdbtfd
    publid ObjfdtInputStrfbm dfsfriblizf(ObjfdtNbmf nbmf, bytf[] dbtb)
        tirows InstbndfNotFoundExdfption, OpfrbtionsExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().dfsfriblizf(nbmf, dbtb);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    @Dfprfdbtfd
    publid ObjfdtInputStrfbm dfsfriblizf(String dlbssNbmf, bytf[] dbtb)
        tirows OpfrbtionsExdfption, RfflfdtionExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().dfsfriblizf(dlbssNbmf, dbtb);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    @Dfprfdbtfd
    publid ObjfdtInputStrfbm dfsfriblizf(String dlbssNbmf,
                                         ObjfdtNbmf lobdfrNbmf,
                                         bytf[] dbtb)
        tirows
        InstbndfNotFoundExdfption,
        OpfrbtionsExdfption,
        RfflfdtionExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().dfsfriblizf(dlbssNbmf, lobdfrNbmf, dbtb);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Objfdt gftAttributf(ObjfdtNbmf nbmf, String bttributf)
        tirows
        MBfbnExdfption,
        AttributfNotFoundExdfption,
        InstbndfNotFoundExdfption,
        RfflfdtionExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftAttributf(nbmf, bttributf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid AttributfList gftAttributfs(ObjfdtNbmf nbmf, String[] bttributfs)
        tirows InstbndfNotFoundExdfption, RfflfdtionExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftAttributfs(nbmf, bttributfs);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ClbssLobdfr gftClbssLobdfr(ObjfdtNbmf lobdfrNbmf)
        tirows InstbndfNotFoundExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftClbssLobdfr(lobdfrNbmf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ClbssLobdfr gftClbssLobdfrFor(ObjfdtNbmf mbfbnNbmf)
        tirows InstbndfNotFoundExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftClbssLobdfrFor(mbfbnNbmf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ClbssLobdfrRfpository gftClbssLobdfrRfpository() {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftClbssLobdfrRfpository();
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid String gftDffbultDombin() {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftDffbultDombin();
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid String[] gftDombins() {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftDombins();
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Intfgfr gftMBfbnCount() {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftMBfbnCount();
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid MBfbnInfo gftMBfbnInfo(ObjfdtNbmf nbmf)
        tirows
        InstbndfNotFoundExdfption,
        IntrospfdtionExdfption,
        RfflfdtionExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftMBfbnInfo(nbmf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ObjfdtInstbndf gftObjfdtInstbndf(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().gftObjfdtInstbndf(nbmf);
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Objfdt instbntibtf(String dlbssNbmf)
        tirows RfflfdtionExdfption, MBfbnExdfption {
        difdkCrfbtf(dlbssNbmf);
        rfturn gftMBfbnSfrvfr().instbntibtf(dlbssNbmf);
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Objfdt instbntibtf(String dlbssNbmf,
                              Objfdt pbrbms[],
                              String signbturf[])
        tirows RfflfdtionExdfption, MBfbnExdfption {
        difdkCrfbtf(dlbssNbmf);
        rfturn gftMBfbnSfrvfr().instbntibtf(dlbssNbmf, pbrbms, signbturf);
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Objfdt instbntibtf(String dlbssNbmf, ObjfdtNbmf lobdfrNbmf)
        tirows RfflfdtionExdfption, MBfbnExdfption, InstbndfNotFoundExdfption {
        difdkCrfbtf(dlbssNbmf);
        rfturn gftMBfbnSfrvfr().instbntibtf(dlbssNbmf, lobdfrNbmf);
    }

    /**
     * Cbll <dodf>difdkCrfbtf(dlbssNbmf)</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Objfdt instbntibtf(String dlbssNbmf, ObjfdtNbmf lobdfrNbmf,
                              Objfdt pbrbms[], String signbturf[])
        tirows RfflfdtionExdfption, MBfbnExdfption, InstbndfNotFoundExdfption {
        difdkCrfbtf(dlbssNbmf);
        rfturn gftMBfbnSfrvfr().instbntibtf(dlbssNbmf, lobdfrNbmf,
                                            pbrbms, signbturf);
    }

    /**
     * Cbll <dodf>difdkWritf()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Objfdt invokf(ObjfdtNbmf nbmf, String opfrbtionNbmf,
                         Objfdt pbrbms[], String signbturf[])
        tirows
        InstbndfNotFoundExdfption,
        MBfbnExdfption,
        RfflfdtionExdfption {
        difdkWritf();
        difdkMLftMftiods(nbmf, opfrbtionNbmf);
        rfturn gftMBfbnSfrvfr().invokf(nbmf, opfrbtionNbmf, pbrbms, signbturf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid boolfbn isInstbndfOf(ObjfdtNbmf nbmf, String dlbssNbmf)
        tirows InstbndfNotFoundExdfption {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().isInstbndfOf(nbmf, dlbssNbmf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid boolfbn isRfgistfrfd(ObjfdtNbmf nbmf) {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().isRfgistfrfd(nbmf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Sft<ObjfdtInstbndf> qufryMBfbns(ObjfdtNbmf nbmf, QufryExp qufry) {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().qufryMBfbns(nbmf, qufry);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid Sft<ObjfdtNbmf> qufryNbmfs(ObjfdtNbmf nbmf, QufryExp qufry) {
        difdkRfbd();
        rfturn gftMBfbnSfrvfr().qufryNbmfs(nbmf, qufry);
    }

    /**
     * Cbll <dodf>difdkWritf()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid ObjfdtInstbndf rfgistfrMBfbn(Objfdt objfdt, ObjfdtNbmf nbmf)
        tirows
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        NotComplibntMBfbnExdfption {
        difdkWritf();
        rfturn gftMBfbnSfrvfr().rfgistfrMBfbn(objfdt, nbmf);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           NotifidbtionListfnfr listfnfr)
        tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {
        difdkRfbd();
        gftMBfbnSfrvfr().rfmovfNotifidbtionListfnfr(nbmf, listfnfr);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           NotifidbtionListfnfr listfnfr,
                                           NotifidbtionFiltfr filtfr,
                                           Objfdt ibndbbdk)
        tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {
        difdkRfbd();
        gftMBfbnSfrvfr().rfmovfNotifidbtionListfnfr(nbmf, listfnfr,
                                                    filtfr, ibndbbdk);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           ObjfdtNbmf listfnfr)
        tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {
        difdkRfbd();
        gftMBfbnSfrvfr().rfmovfNotifidbtionListfnfr(nbmf, listfnfr);
    }

    /**
     * Cbll <dodf>difdkRfbd()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           ObjfdtNbmf listfnfr,
                                           NotifidbtionFiltfr filtfr,
                                           Objfdt ibndbbdk)
        tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {
        difdkRfbd();
        gftMBfbnSfrvfr().rfmovfNotifidbtionListfnfr(nbmf, listfnfr,
                                                    filtfr, ibndbbdk);
    }

    /**
     * Cbll <dodf>difdkWritf()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void sftAttributf(ObjfdtNbmf nbmf, Attributf bttributf)
        tirows
        InstbndfNotFoundExdfption,
        AttributfNotFoundExdfption,
        InvblidAttributfVblufExdfption,
        MBfbnExdfption,
        RfflfdtionExdfption {
        difdkWritf();
        gftMBfbnSfrvfr().sftAttributf(nbmf, bttributf);
    }

    /**
     * Cbll <dodf>difdkWritf()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid AttributfList sftAttributfs(ObjfdtNbmf nbmf,
                                       AttributfList bttributfs)
        tirows InstbndfNotFoundExdfption, RfflfdtionExdfption {
        difdkWritf();
        rfturn gftMBfbnSfrvfr().sftAttributfs(nbmf, bttributfs);
    }

    /**
     * Cbll <dodf>difdkUnrfgistfr()</dodf>, tifn forwbrd tiis mftiod to tif
     * wrbppfd objfdt.
     */
    publid void unrfgistfrMBfbn(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption, MBfbnRfgistrbtionExdfption {
        difdkUnrfgistfr(nbmf);
        gftMBfbnSfrvfr().unrfgistfrMBfbn(nbmf);
    }

    //----------------
    // PRIVATE METHODS
    //----------------

    privbtf void difdkClbssLobdfr(Objfdt objfdt) {
        if (objfdt instbndfof ClbssLobdfr)
            tirow nfw SfdurityExdfption("Addfss dfnifd! Crfbting bn " +
                                        "MBfbn tibt is b ClbssLobdfr " +
                                        "is forbiddfn unlfss b sfdurity " +
                                        "mbnbgfr is instbllfd.");
    }

    privbtf void difdkMLftMftiods(ObjfdtNbmf nbmf, String opfrbtion)
    tirows InstbndfNotFoundExdfption {
        // Cifdk if sfdurity mbnbgfr instbllfd
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            rfturn;
        }
        // Cifdk for bddURL bnd gftMBfbnsFromURL mftiods
        if (!opfrbtion.fqubls("bddURL") &&
                !opfrbtion.fqubls("gftMBfbnsFromURL")) {
            rfturn;
        }
        // Cifdk if MBfbn is instbndf of MLft
        if (!gftMBfbnSfrvfr().isInstbndfOf(nbmf,
                "jbvbx.mbnbgfmfnt.lobding.MLft")) {
            rfturn;
        }
        // Tirow sfdurity fxdfption
        if (opfrbtion.fqubls("bddURL")) { // bddURL
            tirow nfw SfdurityExdfption("Addfss dfnifd! MLft mftiod bddURL " +
                    "dbnnot bf invokfd unlfss b sfdurity mbnbgfr is instbllfd.");
        } flsf { // gftMBfbnsFromURL
            // Wiftifr or not dblling gftMBfbnsFromURL is bllowfd is dontrollfd
            // by tif vbluf of tif "jmx.rfmotf.x.mlft.bllow.gftMBfbnsFromURL"
            // systfm propfrty. If tif vbluf of tiis propfrty is truf, dblling
            // tif MLft's gftMBfbnsFromURL mftiod is bllowfd. Tif dffbult vbluf
            // for tiis propfrty is fblsf.
            finbl String propNbmf = "jmx.rfmotf.x.mlft.bllow.gftMBfbnsFromURL";
            GftPropfrtyAdtion propAdtion = nfw GftPropfrtyAdtion(propNbmf);
            String propVbluf = AddfssControllfr.doPrivilfgfd(propAdtion);
            boolfbn bllowGftMBfbnsFromURL = "truf".fqublsIgnorfCbsf(propVbluf);
            if (!bllowGftMBfbnsFromURL) {
                tirow nfw SfdurityExdfption("Addfss dfnifd! MLft mftiod " +
                        "gftMBfbnsFromURL dbnnot bf invokfd unlfss b " +
                        "sfdurity mbnbgfr is instbllfd or tif systfm propfrty " +
                        "-Djmx.rfmotf.x.mlft.bllow.gftMBfbnsFromURL=truf " +
                        "is spfdififd.");
            }
        }
    }

    //------------------
    // PRIVATE VARIABLES
    //------------------

    privbtf MBfbnSfrvfr mbs;
}
