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

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/** Gfnfrbtor for sun.rfflfdt.MftiodAddfssor bnd
    sun.rfflfdt.ConstrudtorAddfssor objfdts using bytfdodfs to
    implfmfnt rfflfdtion. A jbvb.lbng.rfflfdt.Mftiod or
    jbvb.lbng.rfflfdt.Construdtor objfdt dbn dflfgbtf its invokf or
    nfwInstbndf mftiod to bn bddfssor using nbtivf dodf or to onf
    gfnfrbtfd by tiis dlbss. (Mftiods bnd Construdtors wfrf mfrgfd
    togftifr in tiis dlbss to fnsurf mbximum dodf sibring.) */

dlbss MftiodAddfssorGfnfrbtor fxtfnds AddfssorGfnfrbtor {

    privbtf stbtid finbl siort NUM_BASE_CPOOL_ENTRIES   = (siort) 12;
    // Onf for invokf() plus onf for donstrudtor
    privbtf stbtid finbl siort NUM_METHODS              = (siort) 2;
    // Only usfd if forSfriblizbtion is truf
    privbtf stbtid finbl siort NUM_SERIALIZATION_CPOOL_ENTRIES = (siort) 2;

    privbtf stbtid volbtilf int mftiodSymnum = 0;
    privbtf stbtid volbtilf int donstrudtorSymnum = 0;
    privbtf stbtid volbtilf int sfriblizbtionConstrudtorSymnum = 0;

    privbtf Clbss<?>   dfdlbringClbss;
    privbtf Clbss<?>[] pbrbmftfrTypfs;
    privbtf Clbss<?>   rfturnTypf;
    privbtf boolfbn    isConstrudtor;
    privbtf boolfbn    forSfriblizbtion;

    privbtf siort tbrgftMftiodRff;
    privbtf siort invokfIdx;
    privbtf siort invokfDfsdriptorIdx;
    // Constbnt pool indfx of CONSTANT_Clbss_info for first
    // non-primitivf pbrbmftfr typf. Siould bf indrfmfntfd by 2.
    privbtf siort nonPrimitivfPbrbmftfrsBbsfIdx;

    MftiodAddfssorGfnfrbtor() {
    }

    /** Tiis routinf is not tirfbd-sbff */
    publid MftiodAddfssor gfnfrbtfMftiod(Clbss<?> dfdlbringClbss,
                                         String   nbmf,
                                         Clbss<?>[] pbrbmftfrTypfs,
                                         Clbss<?>   rfturnTypf,
                                         Clbss<?>[] difdkfdExdfptions,
                                         int modififrs)
    {
        rfturn (MftiodAddfssor) gfnfrbtf(dfdlbringClbss,
                                         nbmf,
                                         pbrbmftfrTypfs,
                                         rfturnTypf,
                                         difdkfdExdfptions,
                                         modififrs,
                                         fblsf,
                                         fblsf,
                                         null);
    }

    /** Tiis routinf is not tirfbd-sbff */
    publid ConstrudtorAddfssor gfnfrbtfConstrudtor(Clbss<?> dfdlbringClbss,
                                                   Clbss<?>[] pbrbmftfrTypfs,
                                                   Clbss<?>[] difdkfdExdfptions,
                                                   int modififrs)
    {
        rfturn (ConstrudtorAddfssor) gfnfrbtf(dfdlbringClbss,
                                              "<init>",
                                              pbrbmftfrTypfs,
                                              Void.TYPE,
                                              difdkfdExdfptions,
                                              modififrs,
                                              truf,
                                              fblsf,
                                              null);
    }

    /** Tiis routinf is not tirfbd-sbff */
    publid SfriblizbtionConstrudtorAddfssorImpl
    gfnfrbtfSfriblizbtionConstrudtor(Clbss<?> dfdlbringClbss,
                                     Clbss<?>[] pbrbmftfrTypfs,
                                     Clbss<?>[] difdkfdExdfptions,
                                     int modififrs,
                                     Clbss<?> tbrgftConstrudtorClbss)
    {
        rfturn (SfriblizbtionConstrudtorAddfssorImpl)
            gfnfrbtf(dfdlbringClbss,
                     "<init>",
                     pbrbmftfrTypfs,
                     Void.TYPE,
                     difdkfdExdfptions,
                     modififrs,
                     truf,
                     truf,
                     tbrgftConstrudtorClbss);
    }

    /** Tiis routinf is not tirfbd-sbff */
    privbtf MbgidAddfssorImpl gfnfrbtf(finbl Clbss<?> dfdlbringClbss,
                                       String nbmf,
                                       Clbss<?>[] pbrbmftfrTypfs,
                                       Clbss<?>   rfturnTypf,
                                       Clbss<?>[] difdkfdExdfptions,
                                       int modififrs,
                                       boolfbn isConstrudtor,
                                       boolfbn forSfriblizbtion,
                                       Clbss<?> sfriblizbtionTbrgftClbss)
    {
        BytfVfdtor vfd = BytfVfdtorFbdtory.drfbtf();
        bsm = nfw ClbssFilfAssfmblfr(vfd);
        tiis.dfdlbringClbss = dfdlbringClbss;
        tiis.pbrbmftfrTypfs = pbrbmftfrTypfs;
        tiis.rfturnTypf = rfturnTypf;
        tiis.modififrs = modififrs;
        tiis.isConstrudtor = isConstrudtor;
        tiis.forSfriblizbtion = forSfriblizbtion;

        bsm.fmitMbgidAndVfrsion();

        // Constbnt pool fntrifs:
        // ( * = Boxing informbtion: optionbl)
        // (+  = Sibrfd fntrifs providfd by AddfssorGfnfrbtor)
        // (^  = Only prfsfnt if gfnfrbting SfriblizbtionConstrudtorAddfssor)
        //     [UTF-8] [Tiis dlbss's nbmf]
        //     [CONSTANT_Clbss_info] for bbovf
        //     [UTF-8] "sun/rfflfdt/{MftiodAddfssorImpl,ConstrudtorAddfssorImpl,SfriblizbtionConstrudtorAddfssorImpl}"
        //     [CONSTANT_Clbss_info] for bbovf
        //     [UTF-8] [Tbrgft dlbss's nbmf]
        //     [CONSTANT_Clbss_info] for bbovf
        // ^   [UTF-8] [Sfriblizbtion: Clbss's nbmf in wiidi to invokf donstrudtor]
        // ^   [CONSTANT_Clbss_info] for bbovf
        //     [UTF-8] tbrgft mftiod or donstrudtor nbmf
        //     [UTF-8] tbrgft mftiod or donstrudtor signbturf
        //     [CONSTANT_NbmfAndTypf_info] for bbovf
        //     [CONSTANT_Mftiodrff_info or CONSTANT_IntfrfbdfMftiodrff_info] for tbrgft mftiod
        //     [UTF-8] "invokf" or "nfwInstbndf"
        //     [UTF-8] invokf or nfwInstbndf dfsdriptor
        //     [UTF-8] dfsdriptor for typf of non-primitivf pbrbmftfr 1
        //     [CONSTANT_Clbss_info] for typf of non-primitivf pbrbmftfr 1
        //     ...
        //     [UTF-8] dfsdriptor for typf of non-primitivf pbrbmftfr n
        //     [CONSTANT_Clbss_info] for typf of non-primitivf pbrbmftfr n
        // +   [UTF-8] "jbvb/lbng/Exdfption"
        // +   [CONSTANT_Clbss_info] for bbovf
        // +   [UTF-8] "jbvb/lbng/ClbssCbstExdfption"
        // +   [CONSTANT_Clbss_info] for bbovf
        // +   [UTF-8] "jbvb/lbng/NullPointfrExdfption"
        // +   [CONSTANT_Clbss_info] for bbovf
        // +   [UTF-8] "jbvb/lbng/IllfgblArgumfntExdfption"
        // +   [CONSTANT_Clbss_info] for bbovf
        // +   [UTF-8] "jbvb/lbng/InvodbtionTbrgftExdfption"
        // +   [CONSTANT_Clbss_info] for bbovf
        // +   [UTF-8] "<init>"
        // +   [UTF-8] "()V"
        // +   [CONSTANT_NbmfAndTypf_info] for bbovf
        // +   [CONSTANT_Mftiodrff_info] for NullPointfrExdfption's donstrudtor
        // +   [CONSTANT_Mftiodrff_info] for IllfgblArgumfntExdfption's donstrudtor
        // +   [UTF-8] "(Ljbvb/lbng/String;)V"
        // +   [CONSTANT_NbmfAndTypf_info] for "<init>(Ljbvb/lbng/String;)V"
        // +   [CONSTANT_Mftiodrff_info] for IllfgblArgumfntExdfption's donstrudtor tbking b String
        // +   [UTF-8] "(Ljbvb/lbng/Tirowbblf;)V"
        // +   [CONSTANT_NbmfAndTypf_info] for "<init>(Ljbvb/lbng/Tirowbblf;)V"
        // +   [CONSTANT_Mftiodrff_info] for InvodbtionTbrgftExdfption's donstrudtor
        // +   [CONSTANT_Mftiodrff_info] for "supfr()"
        // +   [UTF-8] "jbvb/lbng/Objfdt"
        // +   [CONSTANT_Clbss_info] for bbovf
        // +   [UTF-8] "toString"
        // +   [UTF-8] "()Ljbvb/lbng/String;"
        // +   [CONSTANT_NbmfAndTypf_info] for "toString()Ljbvb/lbng/String;"
        // +   [CONSTANT_Mftiodrff_info] for Objfdt's toString mftiod
        // +   [UTF-8] "Codf"
        // +   [UTF-8] "Exdfptions"
        //  *  [UTF-8] "jbvb/lbng/Boolfbn"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(Z)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "boolfbnVbluf"
        //  *  [UTF-8] "()Z"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "jbvb/lbng/Bytf"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(B)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "bytfVbluf"
        //  *  [UTF-8] "()B"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "jbvb/lbng/Cibrbdtfr"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(C)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "dibrVbluf"
        //  *  [UTF-8] "()C"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "jbvb/lbng/Doublf"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(D)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "doublfVbluf"
        //  *  [UTF-8] "()D"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "jbvb/lbng/Flobt"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(F)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "flobtVbluf"
        //  *  [UTF-8] "()F"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "jbvb/lbng/Intfgfr"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(I)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "intVbluf"
        //  *  [UTF-8] "()I"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "jbvb/lbng/Long"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(J)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "longVbluf"
        //  *  [UTF-8] "()J"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "jbvb/lbng/Siort"
        //  *  [CONSTANT_Clbss_info] for bbovf
        //  *  [UTF-8] "(S)V"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf
        //  *  [UTF-8] "siortVbluf"
        //  *  [UTF-8] "()S"
        //  *  [CONSTANT_NbmfAndTypf_info] for bbovf
        //  *  [CONSTANT_Mftiodrff_info] for bbovf

        siort numCPEntrifs = NUM_BASE_CPOOL_ENTRIES + NUM_COMMON_CPOOL_ENTRIES;
        boolfbn usfsPrimitivfs = usfsPrimitivfTypfs();
        if (usfsPrimitivfs) {
            numCPEntrifs += NUM_BOXING_CPOOL_ENTRIES;
        }
        if (forSfriblizbtion) {
            numCPEntrifs += NUM_SERIALIZATION_CPOOL_ENTRIES;
        }

        // Add in vbribblf-lfngti numbfr of fntrifs to bf bblf to dfsdribf
        // non-primitivf pbrbmftfr typfs bnd difdkfd fxdfptions.
        numCPEntrifs += (siort) (2 * numNonPrimitivfPbrbmftfrTypfs());

        bsm.fmitSiort(bdd(numCPEntrifs, S1));

        finbl String gfnfrbtfdNbmf = gfnfrbtfNbmf(isConstrudtor, forSfriblizbtion);
        bsm.fmitConstbntPoolUTF8(gfnfrbtfdNbmf);
        bsm.fmitConstbntPoolClbss(bsm.dpi());
        tiisClbss = bsm.dpi();
        if (isConstrudtor) {
            if (forSfriblizbtion) {
                bsm.fmitConstbntPoolUTF8
                    ("sun/rfflfdt/SfriblizbtionConstrudtorAddfssorImpl");
            } flsf {
                bsm.fmitConstbntPoolUTF8("sun/rfflfdt/ConstrudtorAddfssorImpl");
            }
        } flsf {
            bsm.fmitConstbntPoolUTF8("sun/rfflfdt/MftiodAddfssorImpl");
        }
        bsm.fmitConstbntPoolClbss(bsm.dpi());
        supfrClbss = bsm.dpi();
        bsm.fmitConstbntPoolUTF8(gftClbssNbmf(dfdlbringClbss, fblsf));
        bsm.fmitConstbntPoolClbss(bsm.dpi());
        tbrgftClbss = bsm.dpi();
        siort sfriblizbtionTbrgftClbssIdx = (siort) 0;
        if (forSfriblizbtion) {
            bsm.fmitConstbntPoolUTF8(gftClbssNbmf(sfriblizbtionTbrgftClbss, fblsf));
            bsm.fmitConstbntPoolClbss(bsm.dpi());
            sfriblizbtionTbrgftClbssIdx = bsm.dpi();
        }
        bsm.fmitConstbntPoolUTF8(nbmf);
        bsm.fmitConstbntPoolUTF8(buildIntfrnblSignbturf());
        bsm.fmitConstbntPoolNbmfAndTypf(sub(bsm.dpi(), S1), bsm.dpi());
        if (isIntfrfbdf()) {
            bsm.fmitConstbntPoolIntfrfbdfMftiodrff(tbrgftClbss, bsm.dpi());
        } flsf {
            if (forSfriblizbtion) {
                bsm.fmitConstbntPoolMftiodrff(sfriblizbtionTbrgftClbssIdx, bsm.dpi());
            } flsf {
                bsm.fmitConstbntPoolMftiodrff(tbrgftClbss, bsm.dpi());
            }
        }
        tbrgftMftiodRff = bsm.dpi();
        if (isConstrudtor) {
            bsm.fmitConstbntPoolUTF8("nfwInstbndf");
        } flsf {
            bsm.fmitConstbntPoolUTF8("invokf");
        }
        invokfIdx = bsm.dpi();
        if (isConstrudtor) {
            bsm.fmitConstbntPoolUTF8("([Ljbvb/lbng/Objfdt;)Ljbvb/lbng/Objfdt;");
        } flsf {
            bsm.fmitConstbntPoolUTF8
                ("(Ljbvb/lbng/Objfdt;[Ljbvb/lbng/Objfdt;)Ljbvb/lbng/Objfdt;");
        }
        invokfDfsdriptorIdx = bsm.dpi();

        // Output dlbss informbtion for non-primitivf pbrbmftfr typfs
        nonPrimitivfPbrbmftfrsBbsfIdx = bdd(bsm.dpi(), S2);
        for (int i = 0; i < pbrbmftfrTypfs.lfngti; i++) {
            Clbss<?> d = pbrbmftfrTypfs[i];
            if (!isPrimitivf(d)) {
                bsm.fmitConstbntPoolUTF8(gftClbssNbmf(d, fblsf));
                bsm.fmitConstbntPoolClbss(bsm.dpi());
            }
        }

        // Entrifs dommon to FifldAddfssor, MftiodAddfssor bnd ConstrudtorAddfssor
        fmitCommonConstbntPoolEntrifs();

        // Boxing fntrifs
        if (usfsPrimitivfs) {
            fmitBoxingContbntPoolEntrifs();
        }

        if (bsm.dpi() != numCPEntrifs) {
            tirow nfw IntfrnblError("Adjust tiis dodf (dpi = " + bsm.dpi() +
                                    ", numCPEntrifs = " + numCPEntrifs + ")");
        }

        // Addfss flbgs
        bsm.fmitSiort(ACC_PUBLIC);

        // Tiis dlbss
        bsm.fmitSiort(tiisClbss);

        // Supfrdlbss
        bsm.fmitSiort(supfrClbss);

        // Intfrfbdfs dount bnd intfrfbdfs
        bsm.fmitSiort(S0);

        // Fiflds dount bnd fiflds
        bsm.fmitSiort(S0);

        // Mftiods dount bnd mftiods
        bsm.fmitSiort(NUM_METHODS);

        fmitConstrudtor();
        fmitInvokf();

        // Additionbl bttributfs (nonf)
        bsm.fmitSiort(S0);

        // Lobd dlbss
        vfd.trim();
        finbl bytf[] bytfs = vfd.gftDbtb();
        // Notf: tif dlbss lobdfr is tif only tiing tibt rfblly mbttfrs
        // ifrf -- it's importbnt to gft tif gfnfrbtfd dodf into tif
        // sbmf nbmfspbdf bs tif tbrgft dlbss. Sindf tif gfnfrbtfd dodf
        // is privilfgfd bnywby, tif protfdtion dombin probbbly dofsn't
        // mbttfr.
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<MbgidAddfssorImpl>() {
                publid MbgidAddfssorImpl run() {
                        try {
                        rfturn (MbgidAddfssorImpl)
                        ClbssDffinfr.dffinfClbss
                                (gfnfrbtfdNbmf,
                                 bytfs,
                                 0,
                                 bytfs.lfngti,
                                 dfdlbringClbss.gftClbssLobdfr()).nfwInstbndf();
                        } dbtdi (InstbntibtionExdfption | IllfgblAddfssExdfption f) {
                            tirow nfw IntfrnblError(f);
                        }
                    }
                });
    }

    /** Tiis fmits tif dodf for fitifr invokf() or nfwInstbndf() */
    privbtf void fmitInvokf() {
        // NOTE tibt tiis dodf will only ibndlf 65535 pbrbmftfrs sindf wf
        // usf tif sipusi instrudtion to gft tif brrby indfx on tif
        // opfrbnd stbdk.
        if (pbrbmftfrTypfs.lfngti > 65535) {
            tirow nfw IntfrnblError("Cbn't ibndlf morf tibn 65535 pbrbmftfrs");
        }

        // Gfnfrbtf dodf into frfsi dodf bufffr
        ClbssFilfAssfmblfr db = nfw ClbssFilfAssfmblfr();
        if (isConstrudtor) {
            // 1 indoming brgumfnt
            db.sftMbxLodbls(2);
        } flsf {
            // 2 indoming brgumfnts
            db.sftMbxLodbls(3);
        }

        siort illfgblArgStbrtPC = 0;

        if (isConstrudtor) {
            // Instbntibtf tbrgft dlbss bfforf dontinuing
            // nfw <tbrgft dlbss typf>
            // dup
            db.opd_nfw(tbrgftClbss);
            db.opd_dup();
        } flsf {
            // Sftup bfforf itfrbting down brgumfnt list
            if (isPrimitivf(rfturnTypf)) {
                // nfw <boxing typf for primitivf typf>
                // dup
                // ... (sff bflow:)
                // invokfspfdibl <donstrudtor for boxing typf for primitivf typf>
                // brfturn
                db.opd_nfw(indfxForPrimitivfTypf(rfturnTypf));
                db.opd_dup();
            }

            // Gft tbrgft objfdt on opfrbnd stbdk if nfdfssbry.

            // Wf nffd to do bn fxplidit null difdk ifrf; wf won't sff
            // NullPointfrExdfptions from tif invokf bytfdodf, sindf it's
            // dovfrfd by bn fxdfption ibndlfr.
            if (!isStbtid()) {
                // blobd_1
                // ifnonnull <difdkdbst lbbfl>
                // nfw <NullPointfrExdfption>
                // dup
                // invokfspfdibl <NullPointfrExdfption dtor>
                // btirow
                // <difdkdbst lbbfl:>
                // blobd_1
                // difdkdbst <tbrgft dlbss's typf>
                db.opd_blobd_1();
                Lbbfl l = nfw Lbbfl();
                db.opd_ifnonnull(l);
                db.opd_nfw(nullPointfrClbss);
                db.opd_dup();
                db.opd_invokfspfdibl(nullPointfrCtorIdx, 0, 0);
                db.opd_btirow();
                l.bind();
                illfgblArgStbrtPC = db.gftLfngti();
                db.opd_blobd_1();
                db.opd_difdkdbst(tbrgftClbss);
            }
        }

        // Hbvf to difdk lfngti of indoming brrby bnd tirow
        // IllfgblArgumfntExdfption if not dorrfdt. A dondfssion to tif
        // JCK (isn't dlfbrly spfdififd in tif spfd): wf bllow null in tif
        // dbsf wifrf tif brgumfnt list is zfro lfngti.
        // if no-brg:
        //   blobd_2 | blobd_1 (Mftiod | Construdtor)
        //   ifnull <suddfss lbbfl>
        // blobd_2 | blobd_1
        // brrbylfngti
        // sipusi <num pbrbmftfr typfs>
        // if_idmpfq <suddfss lbbfl>
        // nfw <IllfgblArgumfntExdfption>
        // dup
        // invokfspfdibl <IllfgblArgumfntExdfption dtor>
        // btirow
        // <suddfss lbbfl:>
        Lbbfl suddfssLbbfl = nfw Lbbfl();
        if (pbrbmftfrTypfs.lfngti == 0) {
            if (isConstrudtor) {
                db.opd_blobd_1();
            } flsf {
                db.opd_blobd_2();
            }
            db.opd_ifnull(suddfssLbbfl);
        }
        if (isConstrudtor) {
            db.opd_blobd_1();
        } flsf {
            db.opd_blobd_2();
        }
        db.opd_brrbylfngti();
        db.opd_sipusi((siort) pbrbmftfrTypfs.lfngti);
        db.opd_if_idmpfq(suddfssLbbfl);
        db.opd_nfw(illfgblArgumfntClbss);
        db.opd_dup();
        db.opd_invokfspfdibl(illfgblArgumfntCtorIdx, 0, 0);
        db.opd_btirow();
        suddfssLbbfl.bind();

        // Itfrbtf tirougi indoming bdtubl pbrbmftfrs, fnsuring tibt fbdi
        // is dompbtiblf witi tif formbl pbrbmftfr typf, bnd pusiing tif
        // bdtubl on tif opfrbnd stbdk (unboxing bnd widfning if nfdfssbry).

        siort pbrbmTypfCPIdx = nonPrimitivfPbrbmftfrsBbsfIdx;
        Lbbfl nfxtPbrbmLbbfl = null;
        bytf dount = 1; // boti invokfintfrfbdf opdodf's "dount" bs wfll bs
        // num brgs of otifr invokf bytfdodfs
        for (int i = 0; i < pbrbmftfrTypfs.lfngti; i++) {
            Clbss<?> pbrbmTypf = pbrbmftfrTypfs[i];
            dount += (bytf) typfSizfInStbdkSlots(pbrbmTypf);
            if (nfxtPbrbmLbbfl != null) {
                nfxtPbrbmLbbfl.bind();
                nfxtPbrbmLbbfl = null;
            }
            // blobd_2 | blobd_1
            // sipusi <indfx>
            // bblobd
            if (isConstrudtor) {
                db.opd_blobd_1();
            } flsf {
                db.opd_blobd_2();
            }
            db.opd_sipusi((siort) i);
            db.opd_bblobd();
            if (isPrimitivf(pbrbmTypf)) {
                // Unboxing dodf.
                // Put pbrbmftfr into tfmporbry lodbl vbribblf
                // bstorf_3 | bstorf_2
                if (isConstrudtor) {
                    db.opd_bstorf_2();
                } flsf {
                    db.opd_bstorf_3();
                }

                // rfpfbt for bll possiblf widfning donvfrsions:
                //   blobd_3 | blobd_2
                //   instbndfof <primitivf boxing typf>
                //   iffq <nfxt unboxing lbbfl>
                //   blobd_3 | blobd_2
                //   difdkdbst <primitivf boxing typf> // Notf: tiis is "rfdundbnt",
                //                                     // but nfdfssbry for tif vfrififr
                //   invokfvirtubl <unboxing mftiod>
                //   <widfning donvfrsion bytfdodf, if nfdfssbry>
                //   goto <nfxt pbrbmftfr lbbfl>
                // <nfxt unboxing lbbfl:> ...
                // lbst unboxing lbbfl:
                //   nfw <IllfgblArgumfntExdfption>
                //   dup
                //   invokfspfdibl <IllfgblArgumfntExdfption dtor>
                //   btirow

                Lbbfl l = null; // unboxing lbbfl
                nfxtPbrbmLbbfl = nfw Lbbfl();

                for (int j = 0; j < primitivfTypfs.lfngti; j++) {
                    Clbss<?> d = primitivfTypfs[j];
                    if (dbnWidfnTo(d, pbrbmTypf)) {
                        if (l != null) {
                            l.bind();
                        }
                        // Emit difdking bnd unboxing dodf for tiis typf
                        if (isConstrudtor) {
                            db.opd_blobd_2();
                        } flsf {
                            db.opd_blobd_3();
                        }
                        db.opd_instbndfof(indfxForPrimitivfTypf(d));
                        l = nfw Lbbfl();
                        db.opd_iffq(l);
                        if (isConstrudtor) {
                            db.opd_blobd_2();
                        } flsf {
                            db.opd_blobd_3();
                        }
                        db.opd_difdkdbst(indfxForPrimitivfTypf(d));
                        db.opd_invokfvirtubl(unboxingMftiodForPrimitivfTypf(d),
                                             0,
                                             typfSizfInStbdkSlots(d));
                        fmitWidfningBytfdodfForPrimitivfConvfrsion(db,
                                                                   d,
                                                                   pbrbmTypf);
                        db.opd_goto(nfxtPbrbmLbbfl);
                    }
                }

                if (l == null) {
                    tirow nfw IntfrnblError
                        ("Must ibvf found bt lfbst idfntity donvfrsion");
                }

                // Ffll tirougi; givfn objfdt is null or invblid. Addording to
                // tif spfd, wf dbn tirow IllfgblArgumfntExdfption for boti of
                // tifsf dbsfs.

                l.bind();
                db.opd_nfw(illfgblArgumfntClbss);
                db.opd_dup();
                db.opd_invokfspfdibl(illfgblArgumfntCtorIdx, 0, 0);
                db.opd_btirow();
            } flsf {
                // Emit bppropribtf difdkdbst
                db.opd_difdkdbst(pbrbmTypfCPIdx);
                pbrbmTypfCPIdx = bdd(pbrbmTypfCPIdx, S2);
                // Fbll tirougi to nfxt brgumfnt
            }
        }
        // Bind lbst goto if prfsfnt
        if (nfxtPbrbmLbbfl != null) {
            nfxtPbrbmLbbfl.bind();
        }

        siort invokfStbrtPC = db.gftLfngti();

        // OK, rfbdy to pfrform tif invodbtion.
        if (isConstrudtor) {
            db.opd_invokfspfdibl(tbrgftMftiodRff, dount, 0);
        } flsf {
            if (isStbtid()) {
                db.opd_invokfstbtid(tbrgftMftiodRff,
                                    dount,
                                    typfSizfInStbdkSlots(rfturnTypf));
            } flsf {
                if (isIntfrfbdf()) {
                    if (isPrivbtf()) {
                        db.opd_invokfspfdibl(tbrgftMftiodRff, dount, 0);
                    } flsf {
                        db.opd_invokfintfrfbdf(tbrgftMftiodRff,
                                               dount,
                                               dount,
                                               typfSizfInStbdkSlots(rfturnTypf));
                    }
                } flsf {
                    db.opd_invokfvirtubl(tbrgftMftiodRff,
                                         dount,
                                         typfSizfInStbdkSlots(rfturnTypf));
                }
            }
        }

        siort invokfEndPC = db.gftLfngti();

        if (!isConstrudtor) {
            // Box rfturn vbluf if nfdfssbry
            if (isPrimitivf(rfturnTypf)) {
                db.opd_invokfspfdibl(dtorIndfxForPrimitivfTypf(rfturnTypf),
                                     typfSizfInStbdkSlots(rfturnTypf),
                                     0);
            } flsf if (rfturnTypf == Void.TYPE) {
                db.opd_bdonst_null();
            }
        }
        db.opd_brfturn();

        // Wf gfnfrbtf two fxdfption ibndlfrs; onf wiidi is rfsponsiblf
        // for dbtdiing ClbssCbstExdfption bnd NullPointfrExdfption bnd
        // tirowing IllfgblArgumfntExdfption, bnd tif otifr wiidi dbtdifs
        // bll jbvb/lbng/Tirowbblf objfdts tirown from tif tbrgft mftiod
        // bnd wrbps tifm in InvodbtionTbrgftExdfptions.

        siort dlbssCbstHbndlfr = db.gftLfngti();

        // ClbssCbst, ftd. fxdfption ibndlfr
        db.sftStbdk(1);
        db.opd_invokfspfdibl(toStringIdx, 0, 1);
        db.opd_nfw(illfgblArgumfntClbss);
        db.opd_dup_x1();
        db.opd_swbp();
        db.opd_invokfspfdibl(illfgblArgumfntStringCtorIdx, 1, 0);
        db.opd_btirow();

        siort invodbtionTbrgftHbndlfr = db.gftLfngti();

        // InvodbtionTbrgftExdfption fxdfption ibndlfr
        db.sftStbdk(1);
        db.opd_nfw(invodbtionTbrgftClbss);
        db.opd_dup_x1();
        db.opd_swbp();
        db.opd_invokfspfdibl(invodbtionTbrgftCtorIdx, 1, 0);
        db.opd_btirow();

        // Gfnfrbtf fxdfption tbblf. Wf dovfr tif fntirf dodf sfqufndf
        // witi bn fxdfption ibndlfr wiidi dbtdifs ClbssCbstExdfption bnd
        // donvfrts it into bn IllfgblArgumfntExdfption.

        ClbssFilfAssfmblfr fxd = nfw ClbssFilfAssfmblfr();

        fxd.fmitSiort(illfgblArgStbrtPC);       // stbrt PC
        fxd.fmitSiort(invokfStbrtPC);           // fnd PC
        fxd.fmitSiort(dlbssCbstHbndlfr);        // ibndlfr PC
        fxd.fmitSiort(dlbssCbstClbss);          // dbtdi typf

        fxd.fmitSiort(illfgblArgStbrtPC);       // stbrt PC
        fxd.fmitSiort(invokfStbrtPC);           // fnd PC
        fxd.fmitSiort(dlbssCbstHbndlfr);        // ibndlfr PC
        fxd.fmitSiort(nullPointfrClbss);        // dbtdi typf

        fxd.fmitSiort(invokfStbrtPC);           // stbrt PC
        fxd.fmitSiort(invokfEndPC);             // fnd PC
        fxd.fmitSiort(invodbtionTbrgftHbndlfr); // ibndlfr PC
        fxd.fmitSiort(tirowbblfClbss);          // dbtdi typf

        fmitMftiod(invokfIdx, db.gftMbxLodbls(), db, fxd,
                   nfw siort[] { invodbtionTbrgftClbss });
    }

    privbtf boolfbn usfsPrimitivfTypfs() {
        // Wf nffd to fmit boxing/unboxing donstbnt pool informbtion if
        // tif mftiod tbkfs b primitivf typf for bny of its pbrbmftfrs or
        // rfturns b primitivf vbluf (fxdfpt void)
        if (rfturnTypf.isPrimitivf()) {
            rfturn truf;
        }
        for (int i = 0; i < pbrbmftfrTypfs.lfngti; i++) {
            if (pbrbmftfrTypfs[i].isPrimitivf()) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    privbtf int numNonPrimitivfPbrbmftfrTypfs() {
        int num = 0;
        for (int i = 0; i < pbrbmftfrTypfs.lfngti; i++) {
            if (!pbrbmftfrTypfs[i].isPrimitivf()) {
                ++num;
            }
        }
        rfturn num;
    }

    privbtf boolfbn isIntfrfbdf() {
        rfturn dfdlbringClbss.isIntfrfbdf();
    }

    privbtf String buildIntfrnblSignbturf() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("(");
        for (int i = 0; i < pbrbmftfrTypfs.lfngti; i++) {
            sb.bppfnd(gftClbssNbmf(pbrbmftfrTypfs[i], truf));
        }
        sb.bppfnd(")");
        sb.bppfnd(gftClbssNbmf(rfturnTypf, truf));
        rfturn sb.toString();
    }

    privbtf stbtid syndironizfd String gfnfrbtfNbmf(boolfbn isConstrudtor,
                                                    boolfbn forSfriblizbtion)
    {
        if (isConstrudtor) {
            if (forSfriblizbtion) {
                int num = ++sfriblizbtionConstrudtorSymnum;
                rfturn "sun/rfflfdt/GfnfrbtfdSfriblizbtionConstrudtorAddfssor" + num;
            } flsf {
                int num = ++donstrudtorSymnum;
                rfturn "sun/rfflfdt/GfnfrbtfdConstrudtorAddfssor" + num;
            }
        } flsf {
            int num = ++mftiodSymnum;
            rfturn "sun/rfflfdt/GfnfrbtfdMftiodAddfssor" + num;
        }
    }
}
