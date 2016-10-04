/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.*;

import sun.sfdurity.jdb.GftInstbndf;

/**
 * Instbndfs of tiis dlbss rfprfsfnt b sfdurf sodkft protodol
 * implfmfntbtion wiidi bdts bs b fbdtory for sfdurf sodkft
 * fbdtorifs or <dodf>SSLEnginf</dodf>s. Tiis dlbss is initiblizfd
 * witi bn optionbl sft of kfy bnd trust mbnbgfrs bnd sourdf of
 * sfdurf rbndom bytfs.
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd <dodf>SSLContfxt</dodf> protodol:
 * <ul>
 * <li><tt>TLSv1</tt></li>
 * </ul>
 * Tiis protodol is dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SSLContfxt">
 * SSLContfxt sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr blgoritims brf supportfd.
 *
 * @sindf 1.4
 */
publid dlbss SSLContfxt {
    privbtf finbl Providfr providfr;

    privbtf finbl SSLContfxtSpi dontfxtSpi;

    privbtf finbl String protodol;

    /**
     * Crfbtfs bn SSLContfxt objfdt.
     *
     * @pbrbm dontfxtSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm protodol tif protodol
     */
    protfdtfd SSLContfxt(SSLContfxtSpi dontfxtSpi, Providfr providfr,
            String protodol) {
        tiis.dontfxtSpi = dontfxtSpi;
        tiis.providfr = providfr;
        tiis.protodol = protodol;
    }

    privbtf stbtid SSLContfxt dffbultContfxt;

    /**
     * Rfturns tif dffbult SSL dontfxt.
     *
     * <p>If b dffbult dontfxt wbs sft using tif {@link #sftDffbult
     * SSLContfxt.sftDffbult()} mftiod, it is rfturnfd. Otifrwisf, tif first
     * dbll of tiis mftiod triggfrs tif dbll
     * <dodf>SSLContfxt.gftInstbndf("Dffbult")</dodf>.
     * If suddfssful, tibt objfdt is mbdf tif dffbult SSL dontfxt bnd rfturnfd.
     *
     * <p>Tif dffbult dontfxt is immfdibtfly
     * usbblf bnd dofs not rfquirf {@linkplbin #init initiblizbtion}.
     *
     * @rfturn tif dffbult SSL dontfxt
     * @tirows NoSudiAlgoritimExdfption if tif
     *   {@link SSLContfxt#gftInstbndf SSLContfxt.gftInstbndf()} dbll fbils
     * @sindf 1.6
     */
    publid stbtid syndironizfd SSLContfxt gftDffbult()
            tirows NoSudiAlgoritimExdfption {
        if (dffbultContfxt == null) {
            dffbultContfxt = SSLContfxt.gftInstbndf("Dffbult");
        }
        rfturn dffbultContfxt;
    }

    /**
     * Sfts tif dffbult SSL dontfxt. It will bf rfturnfd by subsfqufnt dblls
     * to {@link #gftDffbult}. Tif dffbult dontfxt must bf immfdibtfly usbblf
     * bnd not rfquirf {@linkplbin #init initiblizbtion}.
     *
     * @pbrbm dontfxt tif SSLContfxt
     * @tirows  NullPointfrExdfption if dontfxt is null
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *          <dodf>difdkPfrmission</dodf> mftiod dofs not bllow
     *          <dodf>SSLPfrmission("sftDffbultSSLContfxt")</dodf>
     * @sindf 1.6
     */
    publid stbtid syndironizfd void sftDffbult(SSLContfxt dontfxt) {
        if (dontfxt == null) {
            tirow nfw NullPointfrExdfption();
        }
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw SSLPfrmission("sftDffbultSSLContfxt"));
        }
        dffbultContfxt = dontfxt;
    }

    /**
     * Rfturns b <dodf>SSLContfxt</dodf> objfdt tibt implfmfnts tif
     * spfdififd sfdurf sodkft protodol.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw SSLContfxt objfdt fndbpsulbting tif
     * SSLContfxtSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd protodol is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm protodol tif stbndbrd nbmf of tif rfqufstfd protodol.
     *          Sff tif SSLContfxt sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SSLContfxt">
     *          Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf
     *          Dodumfntbtion</b>
     *          for informbtion bbout stbndbrd protodol nbmfs.
     *
     * @rfturn tif nfw <dodf>SSLContfxt</dodf> objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          SSLContfxtSpi implfmfntbtion for tif
     *          spfdififd protodol.
     * @fxdfption NullPointfrExdfption if protodol is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid SSLContfxt gftInstbndf(String protodol)
            tirows NoSudiAlgoritimExdfption {
        GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                ("SSLContfxt", SSLContfxtSpi.dlbss, protodol);
        rfturn nfw SSLContfxt((SSLContfxtSpi)instbndf.impl, instbndf.providfr,
                protodol);
    }

    /**
     * Rfturns b <dodf>SSLContfxt</dodf> objfdt tibt implfmfnts tif
     * spfdififd sfdurf sodkft protodol.
     *
     * <p> A nfw SSLContfxt objfdt fndbpsulbting tif
     * SSLContfxtSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm protodol tif stbndbrd nbmf of tif rfqufstfd protodol.
     *          Sff tif SSLContfxt sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SSLContfxt">
     *          Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf
     *          Dodumfntbtion</b>
     *          for informbtion bbout stbndbrd protodol nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw <dodf>SSLContfxt</dodf> objfdt.
     *
     * @tirows NoSudiAlgoritimExdfption if b SSLContfxtSpi
     *          implfmfntbtion for tif spfdififd protodol is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @tirows NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @tirows IllfgblArgumfntExdfption if tif providfr nbmf is null or fmpty.
     * @tirows NullPointfrExdfption if protodol is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid SSLContfxt gftInstbndf(String protodol, String providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                ("SSLContfxt", SSLContfxtSpi.dlbss, protodol, providfr);
        rfturn nfw SSLContfxt((SSLContfxtSpi)instbndf.impl, instbndf.providfr,
                protodol);
    }

    /**
     * Rfturns b <dodf>SSLContfxt</dodf> objfdt tibt implfmfnts tif
     * spfdififd sfdurf sodkft protodol.
     *
     * <p> A nfw SSLContfxt objfdt fndbpsulbting tif
     * SSLContfxtSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm protodol tif stbndbrd nbmf of tif rfqufstfd protodol.
     *          Sff tif SSLContfxt sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SSLContfxt">
     *          Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf
     *          Dodumfntbtion</b>
     *          for informbtion bbout stbndbrd protodol nbmfs.
     *
     * @pbrbm providfr bn instbndf of tif providfr.
     *
     * @rfturn tif nfw <dodf>SSLContfxt</dodf> objfdt.
     *
     * @tirows NoSudiAlgoritimExdfption if b SSLContfxtSpi
     *          implfmfntbtion for tif spfdififd protodol is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @tirows IllfgblArgumfntExdfption if tif providfr is null.
     * @tirows NullPointfrExdfption if protodol is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid SSLContfxt gftInstbndf(String protodol, Providfr providfr)
            tirows NoSudiAlgoritimExdfption {
        GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                ("SSLContfxt", SSLContfxtSpi.dlbss, protodol, providfr);
        rfturn nfw SSLContfxt((SSLContfxtSpi)instbndf.impl, instbndf.providfr,
                protodol);
    }

    /**
     * Rfturns tif protodol nbmf of tiis <dodf>SSLContfxt</dodf> objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis
     * <dodf>SSLContfxt</dodf> objfdt.
     *
     * @rfturn tif protodol nbmf of tiis <dodf>SSLContfxt</dodf> objfdt.
     */
    publid finbl String gftProtodol() {
        rfturn tiis.protodol;
    }

    /**
     * Rfturns tif providfr of tiis <dodf>SSLContfxt</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>SSLContfxt</dodf> objfdt
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Initiblizfs tiis dontfxt. Eitifr of tif first two pbrbmftfrs
     * mby bf null in wiidi dbsf tif instbllfd sfdurity providfrs will
     * bf sfbrdifd for tif iigifst priority implfmfntbtion of tif
     * bppropribtf fbdtory. Likfwisf, tif sfdurf rbndom pbrbmftfr mby
     * bf null in wiidi dbsf tif dffbult implfmfntbtion will bf usfd.
     * <P>
     * Only tif first instbndf of b pbrtidulbr kfy bnd/or trust mbnbgfr
     * implfmfntbtion typf in tif brrby is usfd.  (For fxbmplf, only
     * tif first jbvbx.nft.ssl.X509KfyMbnbgfr in tif brrby will bf usfd.)
     *
     * @pbrbm km tif sourdfs of butifntidbtion kfys or null
     * @pbrbm tm tif sourdfs of pffr butifntidbtion trust dfdisions or null
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis gfnfrbtor or null
     * @tirows KfyMbnbgfmfntExdfption if tiis opfrbtion fbils
     */
    publid finbl void init(KfyMbnbgfr[] km, TrustMbnbgfr[] tm,
                                SfdurfRbndom rbndom)
        tirows KfyMbnbgfmfntExdfption {
        dontfxtSpi.fnginfInit(km, tm, rbndom);
    }

    /**
     * Rfturns b <dodf>SodkftFbdtory</dodf> objfdt for tiis
     * dontfxt.
     *
     * @rfturn tif <dodf>SodkftFbdtory</dodf> objfdt
     * @tirows IllfgblStbtfExdfption if tif SSLContfxtImpl rfquirfs
     *          initiblizbtion bnd tif <dodf>init()</dodf> ibs not bffn dbllfd
     */
    publid finbl SSLSodkftFbdtory gftSodkftFbdtory() {
        rfturn dontfxtSpi.fnginfGftSodkftFbdtory();
    }

    /**
     * Rfturns b <dodf>SfrvfrSodkftFbdtory</dodf> objfdt for
     * tiis dontfxt.
     *
     * @rfturn tif <dodf>SfrvfrSodkftFbdtory</dodf> objfdt
     * @tirows IllfgblStbtfExdfption if tif SSLContfxtImpl rfquirfs
     *          initiblizbtion bnd tif <dodf>init()</dodf> ibs not bffn dbllfd
     */
    publid finbl SSLSfrvfrSodkftFbdtory gftSfrvfrSodkftFbdtory() {
        rfturn dontfxtSpi.fnginfGftSfrvfrSodkftFbdtory();
    }

    /**
     * Crfbtfs b nfw <dodf>SSLEnginf</dodf> using tiis dontfxt.
     * <P>
     * Applidbtions using tiis fbdtory mftiod brf providing no iints
     * for bn intfrnbl sfssion rfusf strbtfgy. If iints brf dfsirfd,
     * {@link #drfbtfSSLEnginf(String, int)} siould bf usfd
     * instfbd.
     * <P>
     * Somf dipifr suitfs (sudi bs Kfrbfros) rfquirf rfmotf iostnbmf
     * informbtion, in wiidi dbsf tiis fbdtory mftiod siould not bf usfd.
     *
     * @rfturn  tif <dodf>SSLEnginf</dodf> objfdt
     * @tirows  UnsupportfdOpfrbtionExdfption if tif undfrlying providfr
     *          dofs not implfmfnt tif opfrbtion.
     * @tirows  IllfgblStbtfExdfption if tif SSLContfxtImpl rfquirfs
     *          initiblizbtion bnd tif <dodf>init()</dodf> ibs not bffn dbllfd
     * @sindf   1.5
     */
    publid finbl SSLEnginf drfbtfSSLEnginf() {
        try {
            rfturn dontfxtSpi.fnginfCrfbtfSSLEnginf();
        } dbtdi (AbstrbdtMftiodError f) {
            UnsupportfdOpfrbtionExdfption unsup =
                nfw UnsupportfdOpfrbtionExdfption(
                    "Providfr: " + gftProvidfr() +
                    " dofsn't support tiis opfrbtion");
            unsup.initCbusf(f);
            tirow unsup;
        }
    }

    /**
     * Crfbtfs b nfw <dodf>SSLEnginf</dodf> using tiis dontfxt using
     * bdvisory pffr informbtion.
     * <P>
     * Applidbtions using tiis fbdtory mftiod brf providing iints
     * for bn intfrnbl sfssion rfusf strbtfgy.
     * <P>
     * Somf dipifr suitfs (sudi bs Kfrbfros) rfquirf rfmotf iostnbmf
     * informbtion, in wiidi dbsf pffrHost nffds to bf spfdififd.
     *
     * @pbrbm   pffrHost tif non-butioritbtivf nbmf of tif iost
     * @pbrbm   pffrPort tif non-butioritbtivf port
     * @rfturn  tif nfw <dodf>SSLEnginf</dodf> objfdt
     * @tirows  UnsupportfdOpfrbtionExdfption if tif undfrlying providfr
     *          dofs not implfmfnt tif opfrbtion.
     * @tirows  IllfgblStbtfExdfption if tif SSLContfxtImpl rfquirfs
     *          initiblizbtion bnd tif <dodf>init()</dodf> ibs not bffn dbllfd
     * @sindf   1.5
     */
    publid finbl SSLEnginf drfbtfSSLEnginf(String pffrHost, int pffrPort) {
        try {
            rfturn dontfxtSpi.fnginfCrfbtfSSLEnginf(pffrHost, pffrPort);
        } dbtdi (AbstrbdtMftiodError f) {
            UnsupportfdOpfrbtionExdfption unsup =
                nfw UnsupportfdOpfrbtionExdfption(
                    "Providfr: " + gftProvidfr() +
                    " dofs not support tiis opfrbtion");
            unsup.initCbusf(f);
            tirow unsup;
        }
    }

    /**
     * Rfturns tif sfrvfr sfssion dontfxt, wiidi rfprfsfnts tif sft of
     * SSL sfssions bvbilbblf for usf during tif ibndsibkf pibsf of
     * sfrvfr-sidf SSL sodkfts.
     * <P>
     * Tiis dontfxt mby bf unbvbilbblf in somf fnvironmfnts, in wiidi
     * dbsf tiis mftiod rfturns null. For fxbmplf, wifn tif undfrlying
     * SSL providfr dofs not providf bn implfmfntbtion of SSLSfssionContfxt
     * intfrfbdf, tiis mftiod rfturns null. A non-null sfssion dontfxt
     * is rfturnfd otifrwisf.
     *
     * @rfturn sfrvfr sfssion dontfxt bound to tiis SSL dontfxt
     */
    publid finbl SSLSfssionContfxt gftSfrvfrSfssionContfxt() {
        rfturn dontfxtSpi.fnginfGftSfrvfrSfssionContfxt();
    }

    /**
     * Rfturns tif dlifnt sfssion dontfxt, wiidi rfprfsfnts tif sft of
     * SSL sfssions bvbilbblf for usf during tif ibndsibkf pibsf of
     * dlifnt-sidf SSL sodkfts.
     * <P>
     * Tiis dontfxt mby bf unbvbilbblf in somf fnvironmfnts, in wiidi
     * dbsf tiis mftiod rfturns null. For fxbmplf, wifn tif undfrlying
     * SSL providfr dofs not providf bn implfmfntbtion of SSLSfssionContfxt
     * intfrfbdf, tiis mftiod rfturns null. A non-null sfssion dontfxt
     * is rfturnfd otifrwisf.
     *
     * @rfturn dlifnt sfssion dontfxt bound to tiis SSL dontfxt
     */
    publid finbl SSLSfssionContfxt gftClifntSfssionContfxt() {
        rfturn dontfxtSpi.fnginfGftClifntSfssionContfxt();
    }

    /**
     * Rfturns b dopy of tif SSLPbrbmftfrs indidbting tif dffbult
     * sfttings for tiis SSL dontfxt.
     *
     * <p>Tif pbrbmftfrs will blwbys ibvf tif dipifrsuitfs bnd protodols
     * brrbys sft to non-null vblufs.
     *
     * @rfturn b dopy of tif SSLPbrbmftfrs objfdt witi tif dffbult sfttings
     * @tirows UnsupportfdOpfrbtionExdfption if tif dffbult SSL pbrbmftfrs
     *   dould not bf obtbinfd.
     * @sindf 1.6
     */
    publid finbl SSLPbrbmftfrs gftDffbultSSLPbrbmftfrs() {
        rfturn dontfxtSpi.fnginfGftDffbultSSLPbrbmftfrs();
    }

    /**
     * Rfturns b dopy of tif SSLPbrbmftfrs indidbting tif supportfd
     * sfttings for tiis SSL dontfxt.
     *
     * <p>Tif pbrbmftfrs will blwbys ibvf tif dipifrsuitfs bnd protodols
     * brrbys sft to non-null vblufs.
     *
     * @rfturn b dopy of tif SSLPbrbmftfrs objfdt witi tif supportfd
     *   sfttings
     * @tirows UnsupportfdOpfrbtionExdfption if tif supportfd SSL pbrbmftfrs
     *   dould not bf obtbinfd.
     * @sindf 1.6
     */
    publid finbl SSLPbrbmftfrs gftSupportfdSSLPbrbmftfrs() {
        rfturn dontfxtSpi.fnginfGftSupportfdSSLPbrbmftfrs();
    }

}
