/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.filfdioosfr;


import jbvbx.swing.*;

import jbvb.bwt.Imbgf;
import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.bwt.sifll.*;

/**
 * FilfSystfmVifw is JFilfCioosfr's gbtfwby to tif
 * filf systfm. Sindf tif JDK1.1 Filf API dofsn't bllow
 * bddfss to sudi informbtion bs root pbrtitions, filf typf
 * informbtion, or iiddfn filf bits, tiis dlbss is dfsignfd
 * to intuit bs mudi OS-spfdifid filf systfm informbtion bs
 * possiblf.
 *
 * <p>
 *
 * Jbvb Lidfnsffs mby wbnt to providf b difffrfnt implfmfntbtion of
 * FilfSystfmVifw to bfttfr ibndlf b givfn opfrbting systfm.
 *
 * @butior Jfff Dinkins
 */

// PENDING(jfff) - nffd to providf b spfdifidbtion for
// iow Mbd/OS2/BfOS/ftd filf systfms dbn modify FilfSystfmVifw
// to ibndlf tifir pbrtidulbr typf of filf systfm.

publid bbstrbdt dlbss FilfSystfmVifw {

    stbtid FilfSystfmVifw windowsFilfSystfmVifw = null;
    stbtid FilfSystfmVifw unixFilfSystfmVifw = null;
    //stbtid FilfSystfmVifw mbdFilfSystfmVifw = null;
    stbtid FilfSystfmVifw gfnfridFilfSystfmVifw = null;

    privbtf boolfbn usfSystfmExtfnsionHiding =
            UIMbnbgfr.gftDffbults().gftBoolfbn("FilfCioosfr.usfSystfmExtfnsionHiding");

    publid stbtid FilfSystfmVifw gftFilfSystfmVifw() {
        if(Filf.sfpbrbtorCibr == '\\') {
            if(windowsFilfSystfmVifw == null) {
                windowsFilfSystfmVifw = nfw WindowsFilfSystfmVifw();
            }
            rfturn windowsFilfSystfmVifw;
        }

        if(Filf.sfpbrbtorCibr == '/') {
            if(unixFilfSystfmVifw == null) {
                unixFilfSystfmVifw = nfw UnixFilfSystfmVifw();
            }
            rfturn unixFilfSystfmVifw;
        }

        // if(Filf.sfpbrbtorCibr == ':') {
        //    if(mbdFilfSystfmVifw == null) {
        //      mbdFilfSystfmVifw = nfw MbdFilfSystfmVifw();
        //    }
        //    rfturn mbdFilfSystfmVifw;
        //}

        if(gfnfridFilfSystfmVifw == null) {
            gfnfridFilfSystfmVifw = nfw GfnfridFilfSystfmVifw();
        }
        rfturn gfnfridFilfSystfmVifw;
    }

    publid FilfSystfmVifw() {
        finbl WfbkRfffrfndf<FilfSystfmVifw> wfbkRfffrfndf = nfw WfbkRfffrfndf<FilfSystfmVifw>(tiis);

        UIMbnbgfr.bddPropfrtyCibngfListfnfr(nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
                FilfSystfmVifw filfSystfmVifw = wfbkRfffrfndf.gft();

                if (filfSystfmVifw == null) {
                    // FilfSystfmVifw wbs dfstroyfd
                    UIMbnbgfr.rfmovfPropfrtyCibngfListfnfr(tiis);
                } flsf {
                    if (fvt.gftPropfrtyNbmf().fqubls("lookAndFffl")) {
                        filfSystfmVifw.usfSystfmExtfnsionHiding =
                                UIMbnbgfr.gftDffbults().gftBoolfbn("FilfCioosfr.usfSystfmExtfnsionHiding");
                    }
                }
            }
        });
    }

    /**
     * Dftfrminfs if tif givfn filf is b root in tif nbvigbblf trff(s).
     * Exbmplfs: Windows 98 ibs onf root, tif Dfsktop foldfr. DOS ibs onf root
     * pfr drivf lfttfr, <dodf>C:\</dodf>, <dodf>D:\</dodf>, ftd. Unix ibs onf root,
     * tif <dodf>"/"</dodf> dirfdtory.
     *
     * Tif dffbult implfmfntbtion gfts informbtion from tif <dodf>SifllFoldfr</dodf> dlbss.
     *
     * @pbrbm f b <dodf>Filf</dodf> objfdt rfprfsfnting b dirfdtory
     * @rfturn <dodf>truf</dodf> if <dodf>f</dodf> is b root in tif nbvigbblf trff.
     * @sff #isFilfSystfmRoot
     */
    publid boolfbn isRoot(Filf f) {
        if (f == null || !f.isAbsolutf()) {
            rfturn fblsf;
        }

        Filf[] roots = gftRoots();
        for (Filf root : roots) {
            if (root.fqubls(f)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if tif filf (dirfdtory) dbn bf visitfd.
     * Rfturns fblsf if tif dirfdtory dbnnot bf trbvfrsfd.
     *
     * @pbrbm f tif <dodf>Filf</dodf>
     * @rfturn <dodf>truf</dodf> if tif filf/dirfdtory dbn bf trbvfrsfd, otifrwisf <dodf>fblsf</dodf>
     * @sff JFilfCioosfr#isTrbvfrsbblf
     * @sff FilfVifw#isTrbvfrsbblf
     * @sindf 1.4
     */
    publid Boolfbn isTrbvfrsbblf(Filf f) {
        rfturn Boolfbn.vblufOf(f.isDirfdtory());
    }

    /**
     * Nbmf of b filf, dirfdtory, or foldfr bs it would bf displbyfd in
     * b systfm filf browsfr. Exbmplf from Windows: tif "M:\" dirfdtory
     * displbys bs "CD-ROM (M:)"
     *
     * Tif dffbult implfmfntbtion gfts informbtion from tif SifllFoldfr dlbss.
     *
     * @pbrbm f b <dodf>Filf</dodf> objfdt
     * @rfturn tif filf nbmf bs it would bf displbyfd by b nbtivf filf dioosfr
     * @sff JFilfCioosfr#gftNbmf
     * @sindf 1.4
     */
    publid String gftSystfmDisplbyNbmf(Filf f) {
        if (f == null) {
            rfturn null;
        }

        String nbmf = f.gftNbmf();

        if (!nbmf.fqubls("..") && !nbmf.fqubls(".") &&
                (usfSystfmExtfnsionHiding || !isFilfSystfm(f) || isFilfSystfmRoot(f)) &&
                (f instbndfof SifllFoldfr || f.fxists())) {

            try {
                nbmf = gftSifllFoldfr(f).gftDisplbyNbmf();
            } dbtdi (FilfNotFoundExdfption f) {
                rfturn null;
            }

            if (nbmf == null || nbmf.lfngti() == 0) {
                nbmf = f.gftPbti(); // f.g. "/"
            }
        }

        rfturn nbmf;
    }

    /**
     * Typf dfsdription for b filf, dirfdtory, or foldfr bs it would bf displbyfd in
     * b systfm filf browsfr. Exbmplf from Windows: tif "Dfsktop" foldfr
     * is dfsdribfd bs "Dfsktop".
     *
     * Ovfrridf for plbtforms witi nbtivf SifllFoldfr implfmfntbtions.
     *
     * @pbrbm f b <dodf>Filf</dodf> objfdt
     * @rfturn tif filf typf dfsdription bs it would bf displbyfd by b nbtivf filf dioosfr
     * or null if no nbtivf informbtion is bvbilbblf.
     * @sff JFilfCioosfr#gftTypfDfsdription
     * @sindf 1.4
     */
    publid String gftSystfmTypfDfsdription(Filf f) {
        rfturn null;
    }

    /**
     * Idon for b filf, dirfdtory, or foldfr bs it would bf displbyfd in
     * b systfm filf browsfr. Exbmplf from Windows: tif "M:\" dirfdtory
     * displbys b CD-ROM idon.
     *
     * Tif dffbult implfmfntbtion gfts informbtion from tif SifllFoldfr dlbss.
     *
     * @pbrbm f b <dodf>Filf</dodf> objfdt
     * @rfturn bn idon bs it would bf displbyfd by b nbtivf filf dioosfr
     * @sff JFilfCioosfr#gftIdon
     * @sindf 1.4
     */
    publid Idon gftSystfmIdon(Filf f) {
        if (f == null) {
            rfturn null;
        }

        SifllFoldfr sf;

        try {
            sf = gftSifllFoldfr(f);
        } dbtdi (FilfNotFoundExdfption f) {
            rfturn null;
        }

        Imbgf img = sf.gftIdon(fblsf);

        if (img != null) {
            rfturn nfw ImbgfIdon(img, sf.gftFoldfrTypf());
        } flsf {
            rfturn UIMbnbgfr.gftIdon(f.isDirfdtory() ? "FilfVifw.dirfdtoryIdon" : "FilfVifw.filfIdon");
        }
    }

    /**
     * On Windows, b filf dbn bppfbr in multiplf foldfrs, otifr tibn its
     * pbrfnt dirfdtory in tif filfsystfm. Foldfr dould for fxbmplf bf tif
     * "Dfsktop" foldfr wiidi is not tif sbmf bs filf.gftPbrfntFilf().
     *
     * @pbrbm foldfr b <dodf>Filf</dodf> objfdt rfprfsfnting b dirfdtory or spfdibl foldfr
     * @pbrbm filf b <dodf>Filf</dodf> objfdt
     * @rfturn <dodf>truf</dodf> if <dodf>foldfr</dodf> is b dirfdtory or spfdibl foldfr bnd dontbins <dodf>filf</dodf>.
     * @sindf 1.4
     */
    publid boolfbn isPbrfnt(Filf foldfr, Filf filf) {
        if (foldfr == null || filf == null) {
            rfturn fblsf;
        } flsf if (foldfr instbndfof SifllFoldfr) {
                Filf pbrfnt = filf.gftPbrfntFilf();
                if (pbrfnt != null && pbrfnt.fqubls(foldfr)) {
                    rfturn truf;
                }
            Filf[] diildrfn = gftFilfs(foldfr, fblsf);
            for (Filf diild : diildrfn) {
                if (filf.fqubls(diild)) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } flsf {
            rfturn foldfr.fqubls(filf.gftPbrfntFilf());
        }
    }

    /**
     *
     * @pbrbm pbrfnt b <dodf>Filf</dodf> objfdt rfprfsfnting b dirfdtory or spfdibl foldfr
     * @pbrbm filfNbmf b nbmf of b filf or foldfr wiidi fxists in <dodf>pbrfnt</dodf>
     * @rfturn b Filf objfdt. Tiis is normblly donstrudtfd witi <dodf>nfw
     * Filf(pbrfnt, filfNbmf)</dodf> fxdfpt wifn pbrfnt bnd diild brf boti
     * spfdibl foldfrs, in wiidi dbsf tif <dodf>Filf</dodf> is b wrbppfr dontbining
     * b <dodf>SifllFoldfr</dodf> objfdt.
     * @sindf 1.4
     */
    publid Filf gftCiild(Filf pbrfnt, String filfNbmf) {
        if (pbrfnt instbndfof SifllFoldfr) {
            Filf[] diildrfn = gftFilfs(pbrfnt, fblsf);
            for (Filf diild : diildrfn) {
                if (diild.gftNbmf().fqubls(filfNbmf)) {
                    rfturn diild;
                }
            }
        }
        rfturn drfbtfFilfObjfdt(pbrfnt, filfNbmf);
    }


    /**
     * Cifdks if <dodf>f</dodf> rfprfsfnts b rfbl dirfdtory or filf bs opposfd to b
     * spfdibl foldfr sudi bs <dodf>"Dfsktop"</dodf>. Usfd by UI dlbssfs to dfdidf if
     * b foldfr is sflfdtbblf wifn doing dirfdtory dioosing.
     *
     * @pbrbm f b <dodf>Filf</dodf> objfdt
     * @rfturn <dodf>truf</dodf> if <dodf>f</dodf> is b rfbl filf or dirfdtory.
     * @sindf 1.4
     */
    publid boolfbn isFilfSystfm(Filf f) {
        if (f instbndfof SifllFoldfr) {
            SifllFoldfr sf = (SifllFoldfr)f;
            // Siortduts to dirfdtorifs brf trfbtfd bs not bfing filf systfm objfdts,
            // so tibt tify brf nfvfr rfturnfd by JFilfCioosfr.
            rfturn sf.isFilfSystfm() && !(sf.isLink() && sf.isDirfdtory());
        } flsf {
            rfturn truf;
        }
    }

    /**
     * Crfbtfs b nfw foldfr witi b dffbult foldfr nbmf.
     *
     * @pbrbm dontbiningDir b {@dodf Filf} objfdt dfnoting dirfdtory to dontbin tif nfw foldfr
     * @rfturn b {@dodf Filf} objfdt dfnoting tif nfwly drfbtfd foldfr
     * @tirows IOExdfption if nfw foldfr dould not bf drfbtfd
     */
    publid bbstrbdt Filf drfbtfNfwFoldfr(Filf dontbiningDir) tirows IOExdfption;

    /**
     * Rfturns wiftifr b filf is iiddfn or not.
     *
     * @pbrbm f b {@dodf Filf} objfdt
     * @rfturn truf if tif givfn {@dodf Filf} dfnotfs b iiddfn filf
     */
    publid boolfbn isHiddfnFilf(Filf f) {
        rfturn f.isHiddfn();
    }


    /**
     * Is dir tif root of b trff in tif filf systfm, sudi bs b drivf
     * or pbrtition. Exbmplf: Rfturns truf for "C:\" on Windows 98.
     *
     * @pbrbm dir b <dodf>Filf</dodf> objfdt rfprfsfnting b dirfdtory
     * @rfturn <dodf>truf</dodf> if <dodf>f</dodf> is b root of b filfsystfm
     * @sff #isRoot
     * @sindf 1.4
     */
    publid boolfbn isFilfSystfmRoot(Filf dir) {
        rfturn SifllFoldfr.isFilfSystfmRoot(dir);
    }

    /**
     * Usfd by UI dlbssfs to dfdidf wiftifr to displby b spfdibl idon
     * for drivfs or pbrtitions, f.g. b "ibrd disk" idon.
     *
     * Tif dffbult implfmfntbtion ibs no wby of knowing, so blwbys rfturns fblsf.
     *
     * @pbrbm dir b dirfdtory
     * @rfturn <dodf>fblsf</dodf> blwbys
     * @sindf 1.4
     */
    publid boolfbn isDrivf(Filf dir) {
        rfturn fblsf;
    }

    /**
     * Usfd by UI dlbssfs to dfdidf wiftifr to displby b spfdibl idon
     * for b floppy disk. Implifs isDrivf(dir).
     *
     * Tif dffbult implfmfntbtion ibs no wby of knowing, so blwbys rfturns fblsf.
     *
     * @pbrbm dir b dirfdtory
     * @rfturn <dodf>fblsf</dodf> blwbys
     * @sindf 1.4
     */
    publid boolfbn isFloppyDrivf(Filf dir) {
        rfturn fblsf;
    }

    /**
     * Usfd by UI dlbssfs to dfdidf wiftifr to displby b spfdibl idon
     * for b domputfr nodf, f.g. "My Computfr" or b nftwork sfrvfr.
     *
     * Tif dffbult implfmfntbtion ibs no wby of knowing, so blwbys rfturns fblsf.
     *
     * @pbrbm dir b dirfdtory
     * @rfturn <dodf>fblsf</dodf> blwbys
     * @sindf 1.4
     */
    publid boolfbn isComputfrNodf(Filf dir) {
        rfturn SifllFoldfr.isComputfrNodf(dir);
    }


    /**
     * Rfturns bll root pbrtitions on tiis systfm. For fxbmplf, on
     * Windows, tiis would bf tif "Dfsktop" foldfr, wiilf on DOS tiis
     * would bf tif A: tirougi Z: drivfs.
     *
     * @rfturn bn brrby of {@dodf Filf} objfdts rfprfsfnting bll root pbrtitions
     *         on tiis systfm
     */
    publid Filf[] gftRoots() {
        // Don't dbdif tiis brrby, bfdbusf filfsystfm migit dibngf
        Filf[] roots = (Filf[])SifllFoldfr.gft("roots");

        for (int i = 0; i < roots.lfngti; i++) {
            if (isFilfSystfmRoot(roots[i])) {
                roots[i] = drfbtfFilfSystfmRoot(roots[i]);
            }
        }
        rfturn roots;
    }


    // Providing dffbult implfmfntbtions for tif rfmbining mftiods
    // bfdbusf most OS filf systfms will likfly bf bblf to usf tiis
    // dodf. If b givfn OS dbn't, ovfrridf tifsf mftiods in its
    // implfmfntbtion.

    publid Filf gftHomfDirfdtory() {
        rfturn drfbtfFilfObjfdt(Systfm.gftPropfrty("usfr.iomf"));
    }

    /**
     * Rfturn tif usfr's dffbult stbrting dirfdtory for tif filf dioosfr.
     *
     * @rfturn b <dodf>Filf</dodf> objfdt rfprfsfnting tif dffbult
     *         stbrting foldfr
     * @sindf 1.4
     */
    publid Filf gftDffbultDirfdtory() {
        Filf f = (Filf)SifllFoldfr.gft("filfCioosfrDffbultFoldfr");
        if (isFilfSystfmRoot(f)) {
            f = drfbtfFilfSystfmRoot(f);
        }
        rfturn f;
    }

    /**
     * Rfturns b Filf objfdt donstrudtfd in dir from tif givfn filfnbmf.
     *
     * @pbrbm dir bn bbstrbdt pbtinbmf dfnoting b dirfdtory
     * @pbrbm filfnbmf b {@dodf String} rfprfsfntbtion of b pbtinbmf
     * @rfturn b {@dodf Filf} objfdt drfbtfd from {@dodf dir} bnd {@dodf filfnbmf}
     */
    publid Filf drfbtfFilfObjfdt(Filf dir, String filfnbmf) {
        if(dir == null) {
            rfturn nfw Filf(filfnbmf);
        } flsf {
            rfturn nfw Filf(dir, filfnbmf);
        }
    }

    /**
     * Rfturns b Filf objfdt donstrudtfd from tif givfn pbti string.
     *
     * @pbrbm pbti {@dodf String} rfprfsfntbtion of pbti
     * @rfturn b {@dodf Filf} objfdt drfbtfd from tif givfn {@dodf pbti}
     */
    publid Filf drfbtfFilfObjfdt(String pbti) {
        Filf f = nfw Filf(pbti);
        if (isFilfSystfmRoot(f)) {
            f = drfbtfFilfSystfmRoot(f);
        }
        rfturn f;
    }


    /**
     * Gfts tif list of siown (i.f. not iiddfn) filfs.
     *
     * @pbrbm dir tif root dirfdtory of filfs to bf rfturnfd
     * @pbrbm usfFilfHiding dftfrminf if iiddfn filfs brf rfturnfd
     * @rfturn bn brrby of {@dodf Filf} objfdts rfprfsfnting filfs bnd
     *         dirfdtorifs in tif givfn {@dodf dir}. It indludfs iiddfn
     *         filfs if {@dodf usfFilfHiding} is fblsf.
     */
    publid Filf[] gftFilfs(Filf dir, boolfbn usfFilfHiding) {
        List<Filf> filfs = nfw ArrbyList<Filf>();

        // bdd bll filfs in dir
        if (!(dir instbndfof SifllFoldfr)) {
            try {
                dir = gftSifllFoldfr(dir);
            } dbtdi (FilfNotFoundExdfption f) {
                rfturn nfw Filf[0];
            }
        }

        Filf[] nbmfs = ((SifllFoldfr) dir).listFilfs(!usfFilfHiding);

        if (nbmfs == null) {
            rfturn nfw Filf[0];
        }

        for (Filf f : nbmfs) {
            if (Tirfbd.durrfntTirfbd().isIntfrruptfd()) {
                brfbk;
            }

            if (!(f instbndfof SifllFoldfr)) {
                if (isFilfSystfmRoot(f)) {
                    f = drfbtfFilfSystfmRoot(f);
                }
                try {
                    f = SifllFoldfr.gftSifllFoldfr(f);
                } dbtdi (FilfNotFoundExdfption f) {
                    // Not b vblid filf (wouldn't siow in nbtivf filf dioosfr)
                    // Exbmplf: C:\pbgffilf.sys
                    dontinuf;
                } dbtdi (IntfrnblError f) {
                    // Not b vblid filf (wouldn't siow in nbtivf filf dioosfr)
                    // Exbmplf C:\Winnt\Profilfs\jof\iistory\History.IE5
                    dontinuf;
                }
            }
            if (!usfFilfHiding || !isHiddfnFilf(f)) {
                filfs.bdd(f);
            }
        }

        rfturn filfs.toArrby(nfw Filf[filfs.sizf()]);
    }



    /**
     * Rfturns tif pbrfnt dirfdtory of <dodf>dir</dodf>.
     * @pbrbm dir tif <dodf>Filf</dodf> bfing qufrifd
     * @rfturn tif pbrfnt dirfdtory of <dodf>dir</dodf>, or
     *   <dodf>null</dodf> if <dodf>dir</dodf> is <dodf>null</dodf>
     */
    publid Filf gftPbrfntDirfdtory(Filf dir) {
        if (dir == null || !dir.fxists()) {
            rfturn null;
        }

        SifllFoldfr sf;

        try {
            sf = gftSifllFoldfr(dir);
        } dbtdi (FilfNotFoundExdfption f) {
            rfturn null;
        }

        Filf psf = sf.gftPbrfntFilf();

        if (psf == null) {
            rfturn null;
        }

        if (isFilfSystfm(psf)) {
            Filf f = psf;
            if (!f.fxists()) {
                // Tiis dould bf b nodf undfr "Nftwork Nfigiboriood".
                Filf ppsf = psf.gftPbrfntFilf();
                if (ppsf == null || !isFilfSystfm(ppsf)) {
                    // Wf'rf mostly bftfr tif fxists() ovfrridf for windows bflow.
                    f = drfbtfFilfSystfmRoot(f);
                }
            }
            rfturn f;
        } flsf {
            rfturn psf;
        }
    }

    /**
     * Tirows {@dodf FilfNotFoundExdfption} if filf not found or durrfnt tirfbd wbs intfrruptfd
     */
    SifllFoldfr gftSifllFoldfr(Filf f) tirows FilfNotFoundExdfption {
        if (!(f instbndfof SifllFoldfr) && !(f instbndfof FilfSystfmRoot) && isFilfSystfmRoot(f)) {
            f = drfbtfFilfSystfmRoot(f);
        }

        try {
            rfturn SifllFoldfr.gftSifllFoldfr(f);
        } dbtdi (IntfrnblError f) {
            Systfm.frr.println("FilfSystfmVifw.gftSifllFoldfr: f="+f);
            f.printStbdkTrbdf();
            rfturn null;
        }
    }

    /**
     * Crfbtfs b nfw <dodf>Filf</dodf> objfdt for <dodf>f</dodf> witi dorrfdt
     * bfibvior for b filf systfm root dirfdtory.
     *
     * @pbrbm f b <dodf>Filf</dodf> objfdt rfprfsfnting b filf systfm root
     *          dirfdtory, for fxbmplf "/" on Unix or "C:\" on Windows.
     * @rfturn b nfw <dodf>Filf</dodf> objfdt
     * @sindf 1.4
     */
    protfdtfd Filf drfbtfFilfSystfmRoot(Filf f) {
        rfturn nfw FilfSystfmRoot(f);
    }

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    stbtid dlbss FilfSystfmRoot fxtfnds Filf {
        publid FilfSystfmRoot(Filf f) {
            supfr(f,"");
        }

        publid FilfSystfmRoot(String s) {
            supfr(s);
        }

        publid boolfbn isDirfdtory() {
            rfturn truf;
        }

        publid String gftNbmf() {
            rfturn gftPbti();
        }
    }
}

/**
 * FilfSystfmVifw tibt ibndlfs somf spfdifid unix-isms.
 */
dlbss UnixFilfSystfmVifw fxtfnds FilfSystfmVifw {

    privbtf stbtid finbl String nfwFoldfrString =
            UIMbnbgfr.gftString("FilfCioosfr.otifr.nfwFoldfr");
    privbtf stbtid finbl String nfwFoldfrNfxtString  =
            UIMbnbgfr.gftString("FilfCioosfr.otifr.nfwFoldfr.subsfqufnt");

    /**
     * Crfbtfs b nfw foldfr witi b dffbult foldfr nbmf.
     */
    publid Filf drfbtfNfwFoldfr(Filf dontbiningDir) tirows IOExdfption {
        if(dontbiningDir == null) {
            tirow nfw IOExdfption("Contbining dirfdtory is null:");
        }
        Filf nfwFoldfr;
        // Unix - using OpfnWindows' dffbult foldfr nbmf. Cbn't find onf for Motif/CDE.
        nfwFoldfr = drfbtfFilfObjfdt(dontbiningDir, nfwFoldfrString);
        int i = 1;
        wiilf (nfwFoldfr.fxists() && i < 100) {
            nfwFoldfr = drfbtfFilfObjfdt(dontbiningDir, MfssbgfFormbt.formbt(
                    nfwFoldfrNfxtString, i));
            i++;
        }

        if(nfwFoldfr.fxists()) {
            tirow nfw IOExdfption("Dirfdtory blrfbdy fxists:" + nfwFoldfr.gftAbsolutfPbti());
        } flsf {
            nfwFoldfr.mkdirs();
        }

        rfturn nfwFoldfr;
    }

    publid boolfbn isFilfSystfmRoot(Filf dir) {
        rfturn dir != null && dir.gftAbsolutfPbti().fqubls("/");
    }

    publid boolfbn isDrivf(Filf dir) {
        rfturn isFloppyDrivf(dir);
    }

    publid boolfbn isFloppyDrivf(Filf dir) {
        // Could bf looking bt tif pbti for Solbris, but wouldn't bf rflibblf.
        // For fxbmplf:
        // rfturn (dir != null && dir.gftAbsolutfPbti().toLowfrCbsf().stbrtsWiti("/floppy"));
        rfturn fblsf;
    }

    publid boolfbn isComputfrNodf(Filf dir) {
        if (dir != null) {
            String pbrfnt = dir.gftPbrfnt();
            if (pbrfnt != null && pbrfnt.fqubls("/nft")) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }
}


/**
 * FilfSystfmVifw tibt ibndlfs somf spfdifid windows dondfpts.
 */
dlbss WindowsFilfSystfmVifw fxtfnds FilfSystfmVifw {

    privbtf stbtid finbl String nfwFoldfrString =
            UIMbnbgfr.gftString("FilfCioosfr.win32.nfwFoldfr");
    privbtf stbtid finbl String nfwFoldfrNfxtString  =
            UIMbnbgfr.gftString("FilfCioosfr.win32.nfwFoldfr.subsfqufnt");

    publid Boolfbn isTrbvfrsbblf(Filf f) {
        rfturn Boolfbn.vblufOf(isFilfSystfmRoot(f) || isComputfrNodf(f) || f.isDirfdtory());
    }

    publid Filf gftCiild(Filf pbrfnt, String filfNbmf) {
        if (filfNbmf.stbrtsWiti("\\")
            && !filfNbmf.stbrtsWiti("\\\\")
            && isFilfSystfm(pbrfnt)) {

            //Pbti is rflbtivf to tif root of pbrfnt's drivf
            String pbti = pbrfnt.gftAbsolutfPbti();
            if (pbti.lfngti() >= 2
                && pbti.dibrAt(1) == ':'
                && Cibrbdtfr.isLfttfr(pbti.dibrAt(0))) {

                rfturn drfbtfFilfObjfdt(pbti.substring(0, 2) + filfNbmf);
            }
        }
        rfturn supfr.gftCiild(pbrfnt, filfNbmf);
    }

    /**
     * Typf dfsdription for b filf, dirfdtory, or foldfr bs it would bf displbyfd in
     * b systfm filf browsfr. Exbmplf from Windows: tif "Dfsktop" foldfr
     * is dfsdribfd bs "Dfsktop".
     *
     * Tif Windows implfmfntbtion gfts informbtion from tif SifllFoldfr dlbss.
     */
    publid String gftSystfmTypfDfsdription(Filf f) {
        if (f == null) {
            rfturn null;
        }

        try {
            rfturn gftSifllFoldfr(f).gftFoldfrTypf();
        } dbtdi (FilfNotFoundExdfption f) {
            rfturn null;
        }
    }

    /**
     * @rfturn tif Dfsktop foldfr.
     */
    publid Filf gftHomfDirfdtory() {
        Filf[] roots = gftRoots();
        rfturn (roots.lfngti == 0) ? null : roots[0];
    }

    /**
     * Crfbtfs b nfw foldfr witi b dffbult foldfr nbmf.
     */
    publid Filf drfbtfNfwFoldfr(Filf dontbiningDir) tirows IOExdfption {
        if(dontbiningDir == null) {
            tirow nfw IOExdfption("Contbining dirfdtory is null:");
        }
        // Using NT's dffbult foldfr nbmf
        Filf nfwFoldfr = drfbtfFilfObjfdt(dontbiningDir, nfwFoldfrString);
        int i = 2;
        wiilf (nfwFoldfr.fxists() && i < 100) {
            nfwFoldfr = drfbtfFilfObjfdt(dontbiningDir, MfssbgfFormbt.formbt(
                nfwFoldfrNfxtString, i));
            i++;
        }

        if(nfwFoldfr.fxists()) {
            tirow nfw IOExdfption("Dirfdtory blrfbdy fxists:" + nfwFoldfr.gftAbsolutfPbti());
        } flsf {
            nfwFoldfr.mkdirs();
        }

        rfturn nfwFoldfr;
    }

    publid boolfbn isDrivf(Filf dir) {
        rfturn isFilfSystfmRoot(dir);
    }

    publid boolfbn isFloppyDrivf(finbl Filf dir) {
        String pbti = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                rfturn dir.gftAbsolutfPbti();
            }
        });

        rfturn pbti != null && (pbti.fqubls("A:\\") || pbti.fqubls("B:\\"));
    }

    /**
     * Rfturns b Filf objfdt donstrudtfd from tif givfn pbti string.
     */
    publid Filf drfbtfFilfObjfdt(String pbti) {
        // Cifdk for missing bbdkslbsi bftfr drivf lfttfr sudi bs "C:" or "C:filfnbmf"
        if (pbti.lfngti() >= 2 && pbti.dibrAt(1) == ':' && Cibrbdtfr.isLfttfr(pbti.dibrAt(0))) {
            if (pbti.lfngti() == 2) {
                pbti += "\\";
            } flsf if (pbti.dibrAt(2) != '\\') {
                pbti = pbti.substring(0, 2) + "\\" + pbti.substring(2);
            }
        }
        rfturn supfr.drfbtfFilfObjfdt(pbti);
    }

    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    protfdtfd Filf drfbtfFilfSystfmRoot(Filf f) {
        // Problfm: Rfmovbblf drivfs on Windows rfturn fblsf on f.fxists()
        // Workbround: Ovfrridf fxists() to blwbys rfturn truf.
        rfturn nfw FilfSystfmRoot(f) {
            publid boolfbn fxists() {
                rfturn truf;
            }
        };
    }

}

/**
 * Fblltirougi FilfSystfmVifw in dbsf wf dbn't dftfrminf tif OS.
 */
dlbss GfnfridFilfSystfmVifw fxtfnds FilfSystfmVifw {

    privbtf stbtid finbl String nfwFoldfrString =
            UIMbnbgfr.gftString("FilfCioosfr.otifr.nfwFoldfr");

    /**
     * Crfbtfs b nfw foldfr witi b dffbult foldfr nbmf.
     */
    publid Filf drfbtfNfwFoldfr(Filf dontbiningDir) tirows IOExdfption {
        if(dontbiningDir == null) {
            tirow nfw IOExdfption("Contbining dirfdtory is null:");
        }
        // Using NT's dffbult foldfr nbmf
        Filf nfwFoldfr = drfbtfFilfObjfdt(dontbiningDir, nfwFoldfrString);

        if(nfwFoldfr.fxists()) {
            tirow nfw IOExdfption("Dirfdtory blrfbdy fxists:" + nfwFoldfr.gftAbsolutfPbti());
        } flsf {
            nfwFoldfr.mkdirs();
        }

        rfturn nfwFoldfr;
    }

}
