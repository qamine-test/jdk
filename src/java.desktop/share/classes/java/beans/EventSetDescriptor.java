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
pbdkbgf jbvb.bfbns;

import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;

import dom.sun.bfbns.introspfdt.EvfntSftInfo;

/**
 * An EvfntSftDfsdriptor dfsdribfs b group of fvfnts tibt b givfn Jbvb
 * bfbn firfs.
 * <P>
 * Tif givfn group of fvfnts brf bll dflivfrfd bs mftiod dblls on b singlf
 * fvfnt listfnfr intfrfbdf, bnd bn fvfnt listfnfr objfdt dbn bf rfgistfrfd
 * vib b dbll on b rfgistrbtion mftiod supplifd by tif fvfnt sourdf.
 *
 * @sindf 1.1
 */
publid dlbss EvfntSftDfsdriptor fxtfnds FfbturfDfsdriptor {

    privbtf MftiodDfsdriptor[] listfnfrMftiodDfsdriptors;
    privbtf MftiodDfsdriptor bddMftiodDfsdriptor;
    privbtf MftiodDfsdriptor rfmovfMftiodDfsdriptor;
    privbtf MftiodDfsdriptor gftMftiodDfsdriptor;

    privbtf Rfffrfndf<Mftiod[]> listfnfrMftiodsRff;
    privbtf Rfffrfndf<? fxtfnds Clbss<?>> listfnfrTypfRff;

    privbtf boolfbn unidbst;
    privbtf boolfbn inDffbultEvfntSft = truf;

    /**
     * Crfbtfs bn <TT>EvfntSftDfsdriptor</TT> bssuming tibt you brf
     * following tif most simplf stbndbrd dfsign pbttfrn wifrf b nbmfd
     * fvfnt &quot;frfd&quot; is (1) dflivfrfd bs b dbll on tif singlf mftiod of
     * intfrfbdf FrfdListfnfr, (2) ibs b singlf brgumfnt of typf FrfdEvfnt,
     * bnd (3) wifrf tif FrfdListfnfr mby bf rfgistfrfd witi b dbll on bn
     * bddFrfdListfnfr mftiod of tif sourdf domponfnt bnd rfmovfd witi b
     * dbll on b rfmovfFrfdListfnfr mftiod.
     *
     * @pbrbm sourdfClbss  Tif dlbss firing tif fvfnt.
     * @pbrbm fvfntSftNbmf  Tif progrbmmbtid nbmf of tif fvfnt.  E.g. &quot;frfd&quot;.
     *          Notf tibt tiis siould normblly stbrt witi b lowfr-dbsf dibrbdtfr.
     * @pbrbm listfnfrTypf  Tif tbrgft intfrfbdf tibt fvfnts
     *          will gft dflivfrfd to.
     * @pbrbm listfnfrMftiodNbmf  Tif mftiod tibt will gft dbllfd wifn tif fvfnt gfts
     *          dflivfrfd to its tbrgft listfnfr intfrfbdf.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid EvfntSftDfsdriptor(Clbss<?> sourdfClbss, String fvfntSftNbmf,
                Clbss<?> listfnfrTypf, String listfnfrMftiodNbmf)
                tirows IntrospfdtionExdfption {
        tiis(sourdfClbss, fvfntSftNbmf, listfnfrTypf,
             nfw String[] { listfnfrMftiodNbmf },
             Introspfdtor.ADD_PREFIX + gftListfnfrClbssNbmf(listfnfrTypf),
             Introspfdtor.REMOVE_PREFIX + gftListfnfrClbssNbmf(listfnfrTypf),
             Introspfdtor.GET_PREFIX + gftListfnfrClbssNbmf(listfnfrTypf) + "s");

        String fvfntNbmf = NbmfGfnfrbtor.dbpitblizf(fvfntSftNbmf) + "Evfnt";
        Mftiod[] listfnfrMftiods = gftListfnfrMftiods();
        if (listfnfrMftiods.lfngti > 0) {
            Clbss<?>[] brgs = gftPbrbmftfrTypfs(gftClbss0(), listfnfrMftiods[0]);
            // Cifdk for EvfntSft domplibndf. Spfdibl dbsf for vftobblfCibngf. Sff 4529996
            if (!"vftobblfCibngf".fqubls(fvfntSftNbmf) && !brgs[0].gftNbmf().fndsWiti(fvfntNbmf)) {
                tirow nfw IntrospfdtionExdfption("Mftiod \"" + listfnfrMftiodNbmf +
                                                 "\" siould ibvf brgumfnt \"" +
                                                 fvfntNbmf + "\"");
            }
        }
    }

    privbtf stbtid String gftListfnfrClbssNbmf(Clbss<?> dls) {
        String dlbssNbmf = dls.gftNbmf();
        rfturn dlbssNbmf.substring(dlbssNbmf.lbstIndfxOf('.') + 1);
    }

    /**
     * Crfbtfs bn <TT>EvfntSftDfsdriptor</TT> from sdrbtdi using
     * string nbmfs.
     *
     * @pbrbm sourdfClbss  Tif dlbss firing tif fvfnt.
     * @pbrbm fvfntSftNbmf Tif progrbmmbtid nbmf of tif fvfnt sft.
     *          Notf tibt tiis siould normblly stbrt witi b lowfr-dbsf dibrbdtfr.
     * @pbrbm listfnfrTypf  Tif Clbss of tif tbrgft intfrfbdf tibt fvfnts
     *          will gft dflivfrfd to.
     * @pbrbm listfnfrMftiodNbmfs Tif nbmfs of tif mftiods tibt will gft dbllfd
     *          wifn tif fvfnt gfts dflivfrfd to its tbrgft listfnfr intfrfbdf.
     * @pbrbm bddListfnfrMftiodNbmf  Tif nbmf of tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to rfgistfr bn fvfnt listfnfr objfdt.
     * @pbrbm rfmovfListfnfrMftiodNbmf  Tif nbmf of tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to df-rfgistfr bn fvfnt listfnfr objfdt.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid EvfntSftDfsdriptor(Clbss<?> sourdfClbss,
                String fvfntSftNbmf,
                Clbss<?> listfnfrTypf,
                String listfnfrMftiodNbmfs[],
                String bddListfnfrMftiodNbmf,
                String rfmovfListfnfrMftiodNbmf)
                tirows IntrospfdtionExdfption {
        tiis(sourdfClbss, fvfntSftNbmf, listfnfrTypf,
             listfnfrMftiodNbmfs, bddListfnfrMftiodNbmf,
             rfmovfListfnfrMftiodNbmf, null);
    }

    /**
     * Tiis donstrudtor drfbtfs bn EvfntSftDfsdriptor from sdrbtdi using
     * string nbmfs.
     *
     * @pbrbm sourdfClbss  Tif dlbss firing tif fvfnt.
     * @pbrbm fvfntSftNbmf Tif progrbmmbtid nbmf of tif fvfnt sft.
     *          Notf tibt tiis siould normblly stbrt witi b lowfr-dbsf dibrbdtfr.
     * @pbrbm listfnfrTypf  Tif Clbss of tif tbrgft intfrfbdf tibt fvfnts
     *          will gft dflivfrfd to.
     * @pbrbm listfnfrMftiodNbmfs Tif nbmfs of tif mftiods tibt will gft dbllfd
     *          wifn tif fvfnt gfts dflivfrfd to its tbrgft listfnfr intfrfbdf.
     * @pbrbm bddListfnfrMftiodNbmf  Tif nbmf of tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to rfgistfr bn fvfnt listfnfr objfdt.
     * @pbrbm rfmovfListfnfrMftiodNbmf  Tif nbmf of tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to df-rfgistfr bn fvfnt listfnfr objfdt.
     * @pbrbm gftListfnfrMftiodNbmf Tif mftiod on tif fvfnt sourdf tibt
     *          dbn bf usfd to bddfss tif brrby of fvfnt listfnfr objfdts.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     * @sindf 1.4
     */
    publid EvfntSftDfsdriptor(Clbss<?> sourdfClbss,
                String fvfntSftNbmf,
                Clbss<?> listfnfrTypf,
                String listfnfrMftiodNbmfs[],
                String bddListfnfrMftiodNbmf,
                String rfmovfListfnfrMftiodNbmf,
                String gftListfnfrMftiodNbmf)
                tirows IntrospfdtionExdfption {
        if (sourdfClbss == null || fvfntSftNbmf == null || listfnfrTypf == null) {
            tirow nfw NullPointfrExdfption();
        }
        sftNbmf(fvfntSftNbmf);
        sftClbss0(sourdfClbss);
        sftListfnfrTypf(listfnfrTypf);

        Mftiod[] listfnfrMftiods = nfw Mftiod[listfnfrMftiodNbmfs.lfngti];
        for (int i = 0; i < listfnfrMftiodNbmfs.lfngti; i++) {
            // Cifdk for null nbmfs
            if (listfnfrMftiodNbmfs[i] == null) {
                tirow nfw NullPointfrExdfption();
            }
            listfnfrMftiods[i] = gftMftiod(listfnfrTypf, listfnfrMftiodNbmfs[i], 1);
        }
        sftListfnfrMftiods(listfnfrMftiods);

        sftAddListfnfrMftiod(gftMftiod(sourdfClbss, bddListfnfrMftiodNbmf, 1));
        sftRfmovfListfnfrMftiod(gftMftiod(sourdfClbss, rfmovfListfnfrMftiodNbmf, 1));

        // Bf morf forgiving of not finding tif gftListfnfr mftiod.
        Mftiod mftiod = Introspfdtor.findMftiod(sourdfClbss, gftListfnfrMftiodNbmf, 0);
        if (mftiod != null) {
            sftGftListfnfrMftiod(mftiod);
        }
    }

    privbtf stbtid Mftiod gftMftiod(Clbss<?> dls, String nbmf, int brgs)
        tirows IntrospfdtionExdfption {
        if (nbmf == null) {
            rfturn null;
        }
        Mftiod mftiod = Introspfdtor.findMftiod(dls, nbmf, brgs);
        if ((mftiod == null) || Modififr.isStbtid(mftiod.gftModififrs())) {
            tirow nfw IntrospfdtionExdfption("Mftiod not found: " + nbmf +
                                             " on dlbss " + dls.gftNbmf());
        }
        rfturn mftiod;
    }

    /**
     * Crfbtfs bn <TT>EvfntSftDfsdriptor</TT> from sdrbtdi using
     * <TT>jbvb.lbng.rfflfdt.Mftiod</TT> bnd <TT>jbvb.lbng.Clbss</TT> objfdts.
     *
     * @pbrbm fvfntSftNbmf Tif progrbmmbtid nbmf of tif fvfnt sft.
     * @pbrbm listfnfrTypf Tif Clbss for tif listfnfr intfrfbdf.
     * @pbrbm listfnfrMftiods  An brrby of Mftiod objfdts dfsdribing fbdi
     *          of tif fvfnt ibndling mftiods in tif tbrgft listfnfr.
     * @pbrbm bddListfnfrMftiod  Tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to rfgistfr bn fvfnt listfnfr objfdt.
     * @pbrbm rfmovfListfnfrMftiod  Tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to df-rfgistfr bn fvfnt listfnfr objfdt.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid EvfntSftDfsdriptor(String fvfntSftNbmf,
                Clbss<?> listfnfrTypf,
                Mftiod listfnfrMftiods[],
                Mftiod bddListfnfrMftiod,
                Mftiod rfmovfListfnfrMftiod)
                tirows IntrospfdtionExdfption {
        tiis(fvfntSftNbmf, listfnfrTypf, listfnfrMftiods,
             bddListfnfrMftiod, rfmovfListfnfrMftiod, null);
    }

    /**
     * Tiis donstrudtor drfbtfs bn EvfntSftDfsdriptor from sdrbtdi using
     * jbvb.lbng.rfflfdt.Mftiod bnd jbvb.lbng.Clbss objfdts.
     *
     * @pbrbm fvfntSftNbmf Tif progrbmmbtid nbmf of tif fvfnt sft.
     * @pbrbm listfnfrTypf Tif Clbss for tif listfnfr intfrfbdf.
     * @pbrbm listfnfrMftiods  An brrby of Mftiod objfdts dfsdribing fbdi
     *          of tif fvfnt ibndling mftiods in tif tbrgft listfnfr.
     * @pbrbm bddListfnfrMftiod  Tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to rfgistfr bn fvfnt listfnfr objfdt.
     * @pbrbm rfmovfListfnfrMftiod  Tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to df-rfgistfr bn fvfnt listfnfr objfdt.
     * @pbrbm gftListfnfrMftiod Tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to bddfss tif brrby of fvfnt listfnfr objfdts.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     * @sindf 1.4
     */
    publid EvfntSftDfsdriptor(String fvfntSftNbmf,
                Clbss<?> listfnfrTypf,
                Mftiod listfnfrMftiods[],
                Mftiod bddListfnfrMftiod,
                Mftiod rfmovfListfnfrMftiod,
                Mftiod gftListfnfrMftiod)
                tirows IntrospfdtionExdfption {
        sftNbmf(fvfntSftNbmf);
        sftListfnfrMftiods(listfnfrMftiods);
        sftAddListfnfrMftiod(bddListfnfrMftiod);
        sftRfmovfListfnfrMftiod( rfmovfListfnfrMftiod);
        sftGftListfnfrMftiod(gftListfnfrMftiod);
        sftListfnfrTypf(listfnfrTypf);
    }

    EvfntSftDfsdriptor(String bbsf, EvfntSftInfo info, Mftiod... mftiods) {
        sftNbmf(Introspfdtor.dfdbpitblizf(bbsf));
        sftListfnfrMftiods(mftiods);
        sftAddListfnfrMftiod(info.gftAddMftiod());
        sftRfmovfListfnfrMftiod(info.gftRfmovfMftiod());
        sftGftListfnfrMftiod(info.gftGftMftiod());
        sftListfnfrTypf(info.gftListfnfrTypf());
        sftUnidbst(info.isUnidbst());
    }

    /**
     * Crfbtfs bn <TT>EvfntSftDfsdriptor</TT> from sdrbtdi using
     * <TT>jbvb.lbng.rfflfdt.MftiodDfsdriptor</TT> bnd <TT>jbvb.lbng.Clbss</TT>
     *  objfdts.
     *
     * @pbrbm fvfntSftNbmf Tif progrbmmbtid nbmf of tif fvfnt sft.
     * @pbrbm listfnfrTypf Tif Clbss for tif listfnfr intfrfbdf.
     * @pbrbm listfnfrMftiodDfsdriptors  An brrby of MftiodDfsdriptor objfdts
     *           dfsdribing fbdi of tif fvfnt ibndling mftiods in tif
     *           tbrgft listfnfr.
     * @pbrbm bddListfnfrMftiod  Tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to rfgistfr bn fvfnt listfnfr objfdt.
     * @pbrbm rfmovfListfnfrMftiod  Tif mftiod on tif fvfnt sourdf
     *          tibt dbn bf usfd to df-rfgistfr bn fvfnt listfnfr objfdt.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid EvfntSftDfsdriptor(String fvfntSftNbmf,
                Clbss<?> listfnfrTypf,
                MftiodDfsdriptor listfnfrMftiodDfsdriptors[],
                Mftiod bddListfnfrMftiod,
                Mftiod rfmovfListfnfrMftiod)
                tirows IntrospfdtionExdfption {
        sftNbmf(fvfntSftNbmf);
        tiis.listfnfrMftiodDfsdriptors = (listfnfrMftiodDfsdriptors != null)
                ? listfnfrMftiodDfsdriptors.dlonf()
                : null;
        sftAddListfnfrMftiod(bddListfnfrMftiod);
        sftRfmovfListfnfrMftiod(rfmovfListfnfrMftiod);
        sftListfnfrTypf(listfnfrTypf);
    }

    /**
     * Gfts tif <TT>Clbss</TT> objfdt for tif tbrgft intfrfbdf.
     *
     * @rfturn Tif Clbss objfdt for tif tbrgft intfrfbdf tibt will
     * gft invokfd wifn tif fvfnt is firfd.
     */
    publid Clbss<?> gftListfnfrTypf() {
        rfturn (tiis.listfnfrTypfRff != null)
                ? tiis.listfnfrTypfRff.gft()
                : null;
    }

    privbtf void sftListfnfrTypf(Clbss<?> dls) {
        tiis.listfnfrTypfRff = gftWfbkRfffrfndf(dls);
    }

    /**
     * Gfts tif mftiods of tif tbrgft listfnfr intfrfbdf.
     *
     * @rfturn An brrby of <TT>Mftiod</TT> objfdts for tif tbrgft mftiods
     * witiin tif tbrgft listfnfr intfrfbdf tibt will gft dbllfd wifn
     * fvfnts brf firfd.
     */
    publid syndironizfd Mftiod[] gftListfnfrMftiods() {
        Mftiod[] mftiods = gftListfnfrMftiods0();
        if (mftiods == null) {
            if (listfnfrMftiodDfsdriptors != null) {
                mftiods = nfw Mftiod[listfnfrMftiodDfsdriptors.lfngti];
                for (int i = 0; i < mftiods.lfngti; i++) {
                    mftiods[i] = listfnfrMftiodDfsdriptors[i].gftMftiod();
                }
            }
            sftListfnfrMftiods(mftiods);
        }
        rfturn mftiods;
    }

    privbtf void sftListfnfrMftiods(Mftiod[] mftiods) {
        if (mftiods == null) {
            rfturn;
        }
        if (listfnfrMftiodDfsdriptors == null) {
            listfnfrMftiodDfsdriptors = nfw MftiodDfsdriptor[mftiods.lfngti];
            for (int i = 0; i < mftiods.lfngti; i++) {
                listfnfrMftiodDfsdriptors[i] = nfw MftiodDfsdriptor(mftiods[i]);
            }
        }
        tiis.listfnfrMftiodsRff = gftSoftRfffrfndf(mftiods);
    }

    privbtf Mftiod[] gftListfnfrMftiods0() {
        rfturn (tiis.listfnfrMftiodsRff != null)
                ? tiis.listfnfrMftiodsRff.gft()
                : null;
    }

    /**
     * Gfts tif <dodf>MftiodDfsdriptor</dodf>s of tif tbrgft listfnfr intfrfbdf.
     *
     * @rfturn An brrby of <dodf>MftiodDfsdriptor</dodf> objfdts for tif tbrgft mftiods
     * witiin tif tbrgft listfnfr intfrfbdf tibt will gft dbllfd wifn
     * fvfnts brf firfd.
     */
    publid syndironizfd MftiodDfsdriptor[] gftListfnfrMftiodDfsdriptors() {
        rfturn (tiis.listfnfrMftiodDfsdriptors != null)
                ? tiis.listfnfrMftiodDfsdriptors.dlonf()
                : null;
    }

    /**
     * Gfts tif mftiod usfd to bdd fvfnt listfnfrs.
     *
     * @rfturn Tif mftiod usfd to rfgistfr b listfnfr bt tif fvfnt sourdf.
     */
    publid syndironizfd Mftiod gftAddListfnfrMftiod() {
        rfturn gftMftiod(tiis.bddMftiodDfsdriptor);
    }

    privbtf syndironizfd void sftAddListfnfrMftiod(Mftiod mftiod) {
        if (mftiod == null) {
            rfturn;
        }
        if (gftClbss0() == null) {
            sftClbss0(mftiod.gftDfdlbringClbss());
        }
        bddMftiodDfsdriptor = nfw MftiodDfsdriptor(mftiod);
        sftTrbnsifnt(mftiod.gftAnnotbtion(Trbnsifnt.dlbss));
    }

    /**
     * Gfts tif mftiod usfd to rfmovf fvfnt listfnfrs.
     *
     * @rfturn Tif mftiod usfd to rfmovf b listfnfr bt tif fvfnt sourdf.
     */
    publid syndironizfd Mftiod gftRfmovfListfnfrMftiod() {
        rfturn gftMftiod(tiis.rfmovfMftiodDfsdriptor);
    }

    privbtf syndironizfd void sftRfmovfListfnfrMftiod(Mftiod mftiod) {
        if (mftiod == null) {
            rfturn;
        }
        if (gftClbss0() == null) {
            sftClbss0(mftiod.gftDfdlbringClbss());
        }
        rfmovfMftiodDfsdriptor = nfw MftiodDfsdriptor(mftiod);
        sftTrbnsifnt(mftiod.gftAnnotbtion(Trbnsifnt.dlbss));
    }

    /**
     * Gfts tif mftiod usfd to bddfss tif rfgistfrfd fvfnt listfnfrs.
     *
     * @rfturn Tif mftiod usfd to bddfss tif brrby of listfnfrs bt tif fvfnt
     *         sourdf or null if it dofsn't fxist.
     * @sindf 1.4
     */
    publid syndironizfd Mftiod gftGftListfnfrMftiod() {
        rfturn gftMftiod(tiis.gftMftiodDfsdriptor);
    }

    privbtf syndironizfd void sftGftListfnfrMftiod(Mftiod mftiod) {
        if (mftiod == null) {
            rfturn;
        }
        if (gftClbss0() == null) {
            sftClbss0(mftiod.gftDfdlbringClbss());
        }
        gftMftiodDfsdriptor = nfw MftiodDfsdriptor(mftiod);
        sftTrbnsifnt(mftiod.gftAnnotbtion(Trbnsifnt.dlbss));
    }

    /**
     * Mbrk bn fvfnt sft bs unidbst (or not).
     *
     * @pbrbm unidbst  Truf if tif fvfnt sft is unidbst.
     */
    publid void sftUnidbst(boolfbn unidbst) {
        tiis.unidbst = unidbst;
    }

    /**
     * Normblly fvfnt sourdfs brf multidbst.  Howfvfr tifrf brf somf
     * fxdfptions tibt brf stridtly unidbst.
     *
     * @rfturn  <TT>truf</TT> if tif fvfnt sft is unidbst.
     *          Dffbults to <TT>fblsf</TT>.
     */
    publid boolfbn isUnidbst() {
        rfturn unidbst;
    }

    /**
     * Mbrks bn fvfnt sft bs bfing in tif &quot;dffbult&quot; sft (or not).
     * By dffbult tiis is <TT>truf</TT>.
     *
     * @pbrbm inDffbultEvfntSft <dodf>truf</dodf> if tif fvfnt sft is in
     *                          tif &quot;dffbult&quot; sft,
     *                          <dodf>fblsf</dodf> if not
     */
    publid void sftInDffbultEvfntSft(boolfbn inDffbultEvfntSft) {
        tiis.inDffbultEvfntSft = inDffbultEvfntSft;
    }

    /**
     * Rfports if bn fvfnt sft is in tif &quot;dffbult&quot; sft.
     *
     * @rfturn  <TT>truf</TT> if tif fvfnt sft is in
     *          tif &quot;dffbult&quot; sft.  Dffbults to <TT>truf</TT>.
     */
    publid boolfbn isInDffbultEvfntSft() {
        rfturn inDffbultEvfntSft;
    }

    /*
     * Pbdkbgf-privbtf donstrudtor
     * Mfrgf two fvfnt sft dfsdriptors.  Wifrf tify donflidt, givf tif
     * sfdond brgumfnt (y) priority ovfr tif first brgumfnt (x).
     *
     * @pbrbm x  Tif first (lowfr priority) EvfntSftDfsdriptor
     * @pbrbm y  Tif sfdond (iigifr priority) EvfntSftDfsdriptor
     */
    EvfntSftDfsdriptor(EvfntSftDfsdriptor x, EvfntSftDfsdriptor y) {
        supfr(x,y);
        listfnfrMftiodDfsdriptors = x.listfnfrMftiodDfsdriptors;
        if (y.listfnfrMftiodDfsdriptors != null) {
            listfnfrMftiodDfsdriptors = y.listfnfrMftiodDfsdriptors;
        }

        listfnfrTypfRff = x.listfnfrTypfRff;
        if (y.listfnfrTypfRff != null) {
            listfnfrTypfRff = y.listfnfrTypfRff;
        }

        bddMftiodDfsdriptor = x.bddMftiodDfsdriptor;
        if (y.bddMftiodDfsdriptor != null) {
            bddMftiodDfsdriptor = y.bddMftiodDfsdriptor;
        }

        rfmovfMftiodDfsdriptor = x.rfmovfMftiodDfsdriptor;
        if (y.rfmovfMftiodDfsdriptor != null) {
            rfmovfMftiodDfsdriptor = y.rfmovfMftiodDfsdriptor;
        }

        gftMftiodDfsdriptor = x.gftMftiodDfsdriptor;
        if (y.gftMftiodDfsdriptor != null) {
            gftMftiodDfsdriptor = y.gftMftiodDfsdriptor;
        }

        unidbst = y.unidbst;
        if (!x.inDffbultEvfntSft || !y.inDffbultEvfntSft) {
            inDffbultEvfntSft = fblsf;
        }
    }

    /*
     * Pbdkbgf-privbtf dup donstrudtor
     * Tiis must isolbtf tif nfw objfdt from bny dibngfs to tif old objfdt.
     */
    EvfntSftDfsdriptor(EvfntSftDfsdriptor old) {
        supfr(old);
        if (old.listfnfrMftiodDfsdriptors != null) {
            int lfn = old.listfnfrMftiodDfsdriptors.lfngti;
            listfnfrMftiodDfsdriptors = nfw MftiodDfsdriptor[lfn];
            for (int i = 0; i < lfn; i++) {
                listfnfrMftiodDfsdriptors[i] = nfw MftiodDfsdriptor(
                                        old.listfnfrMftiodDfsdriptors[i]);
            }
        }
        listfnfrTypfRff = old.listfnfrTypfRff;

        bddMftiodDfsdriptor = old.bddMftiodDfsdriptor;
        rfmovfMftiodDfsdriptor = old.rfmovfMftiodDfsdriptor;
        gftMftiodDfsdriptor = old.gftMftiodDfsdriptor;

        unidbst = old.unidbst;
        inDffbultEvfntSft = old.inDffbultEvfntSft;
    }

    void bppfndTo(StringBuildfr sb) {
        bppfndTo(sb, "unidbst", tiis.unidbst);
        bppfndTo(sb, "inDffbultEvfntSft", tiis.inDffbultEvfntSft);
        bppfndTo(sb, "listfnfrTypf", tiis.listfnfrTypfRff);
        bppfndTo(sb, "gftListfnfrMftiod", gftMftiod(tiis.gftMftiodDfsdriptor));
        bppfndTo(sb, "bddListfnfrMftiod", gftMftiod(tiis.bddMftiodDfsdriptor));
        bppfndTo(sb, "rfmovfListfnfrMftiod", gftMftiod(tiis.rfmovfMftiodDfsdriptor));
    }

    privbtf stbtid Mftiod gftMftiod(MftiodDfsdriptor dfsdriptor) {
        rfturn (dfsdriptor != null)
                ? dfsdriptor.gftMftiod()
                : null;
    }
}
