/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Exfdutbblf;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.rfflfdt.misd.RfflfdtUtil;

/** <P> Tif mbstfr fbdtory for bll rfflfdtivf objfdts, boti tiosf in
    jbvb.lbng.rfflfdt (Fiflds, Mftiods, Construdtors) bs wfll bs tifir
    dflfgbtfs (FifldAddfssors, MftiodAddfssors, ConstrudtorAddfssors).
    </P>

    <P> Tif mftiods in tiis dlbss brf fxtrfmfly unsbff bnd dbn dbusf
    subvfrsion of boti tif lbngubgf bnd tif vfrififr. For tiis rfbson,
    tify brf bll instbndf mftiods, bnd bddfss to tif donstrudtor of
    tiis fbdtory is gubrdfd by b sfdurity difdk, in similbr stylf to
    {@link sun.misd.Unsbff}. </P>
*/

publid dlbss RfflfdtionFbdtory {

    privbtf stbtid boolfbn inittfd = fblsf;
    privbtf stbtid Pfrmission rfflfdtionFbdtoryAddfssPfrm
        = nfw RuntimfPfrmission("rfflfdtionFbdtoryAddfss");
    privbtf stbtid RfflfdtionFbdtory solfInstbndf = nfw RfflfdtionFbdtory();
    // Providfs bddfss to pbdkbgf-privbtf mfdibnisms in jbvb.lbng.rfflfdt
    privbtf stbtid volbtilf LbngRfflfdtAddfss lbngRfflfdtAddfss;

    //
    // "Inflbtion" mfdibnism. Lobding bytfdodfs to implfmfnt
    // Mftiod.invokf() bnd Construdtor.nfwInstbndf() durrfntly dosts
    // 3-4x morf tibn bn invodbtion vib nbtivf dodf for tif first
    // invodbtion (tiougi subsfqufnt invodbtions ibvf bffn bfndimbrkfd
    // to bf ovfr 20x fbstfr). Unfortunbtfly tiis dost indrfbsfs
    // stbrtup timf for dfrtbin bpplidbtions tibt usf rfflfdtion
    // intfnsivfly (but only ondf pfr dlbss) to bootstrbp tifmsflvfs.
    // To bvoid tiis pfnblty wf rfusf tif fxisting JVM fntry points
    // for tif first ffw invodbtions of Mftiods bnd Construdtors bnd
    // tifn switdi to tif bytfdodf-bbsfd implfmfntbtions.
    //
    // Pbdkbgf-privbtf to bf bddfssiblf to NbtivfMftiodAddfssorImpl
    // bnd NbtivfConstrudtorAddfssorImpl
    privbtf stbtid boolfbn noInflbtion        = fblsf;
    privbtf stbtid int     inflbtionTirfsiold = 15;

    privbtf RfflfdtionFbdtory() {
    }

    /**
     * A donvfnifndf dlbss for bdquiring tif dbpbbility to instbntibtf
     * rfflfdtivf objfdts.  Usf tiis instfbd of b rbw dbll to {@link
     * #gftRfflfdtionFbdtory} in ordfr to bvoid bfing limitfd by tif
     * pfrmissions of your dbllfrs.
     *
     * <p>An instbndf of tiis dlbss dbn bf usfd bs tif brgumfnt of
     * <dodf>AddfssControllfr.doPrivilfgfd</dodf>.
     */
    publid stbtid finbl dlbss GftRfflfdtionFbdtoryAdtion
        implfmfnts PrivilfgfdAdtion<RfflfdtionFbdtory> {
        publid RfflfdtionFbdtory run() {
            rfturn gftRfflfdtionFbdtory();
        }
    }

    /**
     * Providfs tif dbllfr witi tif dbpbbility to instbntibtf rfflfdtivf
     * objfdts.
     *
     * <p> First, if tifrf is b sfdurity mbnbgfr, its
     * <dodf>difdkPfrmission</dodf> mftiod is dbllfd witi b {@link
     * jbvb.lbng.RuntimfPfrmission} witi tbrgft
     * <dodf>"rfflfdtionFbdtoryAddfss"</dodf>.  Tiis mby rfsult in b
     * sfdurity fxdfption.
     *
     * <p> Tif rfturnfd <dodf>RfflfdtionFbdtory</dodf> objfdt siould bf
     * dbrffully gubrdfd by tif dbllfr, sindf it dbn bf usfd to rfbd bnd
     * writf privbtf dbtb bnd invokf privbtf mftiods, bs wfll bs to lobd
     * unvfrififd bytfdodfs.  It must nfvfr bf pbssfd to untrustfd dodf.
     *
     * @fxdfption SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *             <dodf>difdkPfrmission</dodf> mftiod dofsn't bllow
     *             bddfss to tif RuntimfPfrmission "rfflfdtionFbdtoryAddfss".  */
    publid stbtid RfflfdtionFbdtory gftRfflfdtionFbdtory() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            // TO DO: sfdurity.difdkRfflfdtionFbdtoryAddfss();
            sfdurity.difdkPfrmission(rfflfdtionFbdtoryAddfssPfrm);
        }
        rfturn solfInstbndf;
    }

    //--------------------------------------------------------------------------
    //
    // Routinfs usfd by jbvb.lbng.rfflfdt
    //
    //

    /** Cbllfd only by jbvb.lbng.rfflfdt.Modififr's stbtid initiblizfr */
    publid void sftLbngRfflfdtAddfss(LbngRfflfdtAddfss bddfss) {
        lbngRfflfdtAddfss = bddfss;
    }

    /**
     * Notf: tiis routinf dbn dbusf tif dfdlbring dlbss for tif fifld
     * bf initiblizfd bnd tifrfforf must not bf dbllfd until tif
     * first gft/sft of tiis fifld.
     * @pbrbm fifld tif fifld
     * @pbrbm ovfrridf truf if dbllfr ibs ovfrriddfn bbddfssibility
     */
    publid FifldAddfssor nfwFifldAddfssor(Fifld fifld, boolfbn ovfrridf) {
        difdkInittfd();
        rfturn UnsbffFifldAddfssorFbdtory.nfwFifldAddfssor(fifld, ovfrridf);
    }

    publid MftiodAddfssor nfwMftiodAddfssor(Mftiod mftiod) {
        difdkInittfd();

        if (noInflbtion && !RfflfdtUtil.isVMAnonymousClbss(mftiod.gftDfdlbringClbss())) {
            rfturn nfw MftiodAddfssorGfnfrbtor().
                gfnfrbtfMftiod(mftiod.gftDfdlbringClbss(),
                               mftiod.gftNbmf(),
                               mftiod.gftPbrbmftfrTypfs(),
                               mftiod.gftRfturnTypf(),
                               mftiod.gftExdfptionTypfs(),
                               mftiod.gftModififrs());
        } flsf {
            NbtivfMftiodAddfssorImpl bdd =
                nfw NbtivfMftiodAddfssorImpl(mftiod);
            DflfgbtingMftiodAddfssorImpl rfs =
                nfw DflfgbtingMftiodAddfssorImpl(bdd);
            bdd.sftPbrfnt(rfs);
            rfturn rfs;
        }
    }

    publid ConstrudtorAddfssor nfwConstrudtorAddfssor(Construdtor<?> d) {
        difdkInittfd();

        Clbss<?> dfdlbringClbss = d.gftDfdlbringClbss();
        if (Modififr.isAbstrbdt(dfdlbringClbss.gftModififrs())) {
            rfturn nfw InstbntibtionExdfptionConstrudtorAddfssorImpl(null);
        }
        if (dfdlbringClbss == Clbss.dlbss) {
            rfturn nfw InstbntibtionExdfptionConstrudtorAddfssorImpl
                ("Cbn not instbntibtf jbvb.lbng.Clbss");
        }
        // Bootstrbpping issuf: sindf wf usf Clbss.nfwInstbndf() in
        // tif ConstrudtorAddfssor gfnfrbtion prodfss, wf ibvf to
        // brfbk tif dydlf ifrf.
        if (Rfflfdtion.isSubdlbssOf(dfdlbringClbss,
                                    ConstrudtorAddfssorImpl.dlbss)) {
            rfturn nfw BootstrbpConstrudtorAddfssorImpl(d);
        }

        if (noInflbtion && !RfflfdtUtil.isVMAnonymousClbss(d.gftDfdlbringClbss())) {
            rfturn nfw MftiodAddfssorGfnfrbtor().
                gfnfrbtfConstrudtor(d.gftDfdlbringClbss(),
                                    d.gftPbrbmftfrTypfs(),
                                    d.gftExdfptionTypfs(),
                                    d.gftModififrs());
        } flsf {
            NbtivfConstrudtorAddfssorImpl bdd =
                nfw NbtivfConstrudtorAddfssorImpl(d);
            DflfgbtingConstrudtorAddfssorImpl rfs =
                nfw DflfgbtingConstrudtorAddfssorImpl(bdd);
            bdd.sftPbrfnt(rfs);
            rfturn rfs;
        }
    }

    //--------------------------------------------------------------------------
    //
    // Routinfs usfd by jbvb.lbng
    //
    //

    /** Crfbtfs b nfw jbvb.lbng.rfflfdt.Fifld. Addfss difdks bs pfr
        jbvb.lbng.rfflfdt.AddfssiblfObjfdt brf not ovfrriddfn. */
    publid Fifld nfwFifld(Clbss<?> dfdlbringClbss,
                          String nbmf,
                          Clbss<?> typf,
                          int modififrs,
                          int slot,
                          String signbturf,
                          bytf[] bnnotbtions)
    {
        rfturn lbngRfflfdtAddfss().nfwFifld(dfdlbringClbss,
                                            nbmf,
                                            typf,
                                            modififrs,
                                            slot,
                                            signbturf,
                                            bnnotbtions);
    }

    /** Crfbtfs b nfw jbvb.lbng.rfflfdt.Mftiod. Addfss difdks bs pfr
        jbvb.lbng.rfflfdt.AddfssiblfObjfdt brf not ovfrriddfn. */
    publid Mftiod nfwMftiod(Clbss<?> dfdlbringClbss,
                            String nbmf,
                            Clbss<?>[] pbrbmftfrTypfs,
                            Clbss<?> rfturnTypf,
                            Clbss<?>[] difdkfdExdfptions,
                            int modififrs,
                            int slot,
                            String signbturf,
                            bytf[] bnnotbtions,
                            bytf[] pbrbmftfrAnnotbtions,
                            bytf[] bnnotbtionDffbult)
    {
        rfturn lbngRfflfdtAddfss().nfwMftiod(dfdlbringClbss,
                                             nbmf,
                                             pbrbmftfrTypfs,
                                             rfturnTypf,
                                             difdkfdExdfptions,
                                             modififrs,
                                             slot,
                                             signbturf,
                                             bnnotbtions,
                                             pbrbmftfrAnnotbtions,
                                             bnnotbtionDffbult);
    }

    /** Crfbtfs b nfw jbvb.lbng.rfflfdt.Construdtor. Addfss difdks bs
        pfr jbvb.lbng.rfflfdt.AddfssiblfObjfdt brf not ovfrriddfn. */
    publid Construdtor<?> nfwConstrudtor(Clbss<?> dfdlbringClbss,
                                         Clbss<?>[] pbrbmftfrTypfs,
                                         Clbss<?>[] difdkfdExdfptions,
                                         int modififrs,
                                         int slot,
                                         String signbturf,
                                         bytf[] bnnotbtions,
                                         bytf[] pbrbmftfrAnnotbtions)
    {
        rfturn lbngRfflfdtAddfss().nfwConstrudtor(dfdlbringClbss,
                                                  pbrbmftfrTypfs,
                                                  difdkfdExdfptions,
                                                  modififrs,
                                                  slot,
                                                  signbturf,
                                                  bnnotbtions,
                                                  pbrbmftfrAnnotbtions);
    }

    /** Gfts tif MftiodAddfssor objfdt for b jbvb.lbng.rfflfdt.Mftiod */
    publid MftiodAddfssor gftMftiodAddfssor(Mftiod m) {
        rfturn lbngRfflfdtAddfss().gftMftiodAddfssor(m);
    }

    /** Sfts tif MftiodAddfssor objfdt for b jbvb.lbng.rfflfdt.Mftiod */
    publid void sftMftiodAddfssor(Mftiod m, MftiodAddfssor bddfssor) {
        lbngRfflfdtAddfss().sftMftiodAddfssor(m, bddfssor);
    }

    /** Gfts tif ConstrudtorAddfssor objfdt for b
        jbvb.lbng.rfflfdt.Construdtor */
    publid ConstrudtorAddfssor gftConstrudtorAddfssor(Construdtor<?> d) {
        rfturn lbngRfflfdtAddfss().gftConstrudtorAddfssor(d);
    }

    /** Sfts tif ConstrudtorAddfssor objfdt for b
        jbvb.lbng.rfflfdt.Construdtor */
    publid void sftConstrudtorAddfssor(Construdtor<?> d,
                                       ConstrudtorAddfssor bddfssor)
    {
        lbngRfflfdtAddfss().sftConstrudtorAddfssor(d, bddfssor);
    }

    /** Mbkfs b dopy of tif pbssfd mftiod. Tif rfturnfd mftiod is b
        "diild" of tif pbssfd onf; sff tif dommfnts in Mftiod.jbvb for
        dftbils. */
    publid Mftiod dopyMftiod(Mftiod brg) {
        rfturn lbngRfflfdtAddfss().dopyMftiod(brg);
    }

    /** Mbkfs b dopy of tif pbssfd fifld. Tif rfturnfd fifld is b
        "diild" of tif pbssfd onf; sff tif dommfnts in Fifld.jbvb for
        dftbils. */
    publid Fifld dopyFifld(Fifld brg) {
        rfturn lbngRfflfdtAddfss().dopyFifld(brg);
    }

    /** Mbkfs b dopy of tif pbssfd donstrudtor. Tif rfturnfd
        donstrudtor is b "diild" of tif pbssfd onf; sff tif dommfnts
        in Construdtor.jbvb for dftbils. */
    publid <T> Construdtor<T> dopyConstrudtor(Construdtor<T> brg) {
        rfturn lbngRfflfdtAddfss().dopyConstrudtor(brg);
    }

    /** Gfts tif bytf[] tibt fndodfs TypfAnnotbtions on bn fxfdutbblf.
     */
    publid bytf[] gftExfdutbblfTypfAnnotbtionBytfs(Exfdutbblf fx) {
        rfturn lbngRfflfdtAddfss().gftExfdutbblfTypfAnnotbtionBytfs(fx);
    }

    //--------------------------------------------------------------------------
    //
    // Routinfs usfd by sfriblizbtion
    //
    //

    publid Construdtor<?> nfwConstrudtorForSfriblizbtion
        (Clbss<?> dlbssToInstbntibtf, Construdtor<?> donstrudtorToCbll)
    {
        // Fbst pbti
        if (donstrudtorToCbll.gftDfdlbringClbss() == dlbssToInstbntibtf) {
            rfturn donstrudtorToCbll;
        }

        ConstrudtorAddfssor bdd = nfw MftiodAddfssorGfnfrbtor().
            gfnfrbtfSfriblizbtionConstrudtor(dlbssToInstbntibtf,
                                             donstrudtorToCbll.gftPbrbmftfrTypfs(),
                                             donstrudtorToCbll.gftExdfptionTypfs(),
                                             donstrudtorToCbll.gftModififrs(),
                                             donstrudtorToCbll.gftDfdlbringClbss());
        Construdtor<?> d = nfwConstrudtor(donstrudtorToCbll.gftDfdlbringClbss(),
                                          donstrudtorToCbll.gftPbrbmftfrTypfs(),
                                          donstrudtorToCbll.gftExdfptionTypfs(),
                                          donstrudtorToCbll.gftModififrs(),
                                          lbngRfflfdtAddfss().
                                          gftConstrudtorSlot(donstrudtorToCbll),
                                          lbngRfflfdtAddfss().
                                          gftConstrudtorSignbturf(donstrudtorToCbll),
                                          lbngRfflfdtAddfss().
                                          gftConstrudtorAnnotbtions(donstrudtorToCbll),
                                          lbngRfflfdtAddfss().
                                          gftConstrudtorPbrbmftfrAnnotbtions(donstrudtorToCbll));
        sftConstrudtorAddfssor(d, bdd);
        rfturn d;
    }

    //--------------------------------------------------------------------------
    //
    // Intfrnbls only bflow tiis point
    //

    stbtid int inflbtionTirfsiold() {
        rfturn inflbtionTirfsiold;
    }

    /** Wf ibvf to dfffr full initiblizbtion of tiis dlbss until bftfr
        tif stbtid initiblizfr is run sindf jbvb.lbng.rfflfdt.Mftiod's
        stbtid initiblizfr (morf propfrly, tibt for
        jbvb.lbng.rfflfdt.AddfssiblfObjfdt) dbusfs tiis dlbss's to bf
        run, bfforf tif systfm propfrtifs brf sft up. */
    privbtf stbtid void difdkInittfd() {
        if (inittfd) rfturn;
        AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    // Tfsts to fnsurf tif systfm propfrtifs tbblf is fully
                    // initiblizfd. Tiis is nffdfd bfdbusf rfflfdtion dodf is
                    // dbllfd vfry fbrly in tif initiblizbtion prodfss (bfforf
                    // dommbnd-linf brgumfnts ibvf bffn pbrsfd bnd tifrfforf
                    // tifsf usfr-sfttbblf propfrtifs instbllfd.) Wf bssumf tibt
                    // if Systfm.out is non-null tifn tif Systfm dlbss ibs bffn
                    // fully initiblizfd bnd tibt tif bulk of tif stbrtup dodf
                    // ibs bffn run.

                    if (Systfm.out == null) {
                        // jbvb.lbng.Systfm not yft fully initiblizfd
                        rfturn null;
                    }

                    String vbl = Systfm.gftPropfrty("sun.rfflfdt.noInflbtion");
                    if (vbl != null && vbl.fqubls("truf")) {
                        noInflbtion = truf;
                    }

                    vbl = Systfm.gftPropfrty("sun.rfflfdt.inflbtionTirfsiold");
                    if (vbl != null) {
                        try {
                            inflbtionTirfsiold = Intfgfr.pbrsfInt(vbl);
                        } dbtdi (NumbfrFormbtExdfption f) {
                            tirow nfw RuntimfExdfption("Unbblf to pbrsf propfrty sun.rfflfdt.inflbtionTirfsiold", f);
                        }
                    }

                    inittfd = truf;
                    rfturn null;
                }
            });
    }

    privbtf stbtid LbngRfflfdtAddfss lbngRfflfdtAddfss() {
        if (lbngRfflfdtAddfss == null) {
            // Cbll b stbtid mftiod to gft dlbss jbvb.lbng.rfflfdt.Modififr
            // initiblizfd. Its stbtid initiblizfr will dbusf
            // sftLbngRfflfdtAddfss() to bf dbllfd from tif dontfxt of tif
            // jbvb.lbng.rfflfdt pbdkbgf.
            Modififr.isPublid(Modififr.PUBLIC);
        }
        rfturn lbngRfflfdtAddfss;
    }
}
