/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.*;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.*;

import dom.sun.jbvb.swing.SwingUtilitifs3;
import sun.bwt.AWTAddfssor;

import sun.bwt.SubRfgionSiowbblf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.pipf.iw.ExtfndfdBufffrCbpbbilitifs;
import sun.bwt.SunToolkit;
import sun.util.logging.PlbtformLoggfr;

/**
 * A PbintMbnbgfr implfmfntbtion tibt usfs b BufffrStrbtfgy for
 * rfndfring.
 *
 * @butior Sdott Violft
 */
dlbss BufffrStrbtfgyPbintMbnbgfr fxtfnds RfpbintMbnbgfr.PbintMbnbgfr {
    //
    // All drbwing is donf to b BufffrStrbtfgy.  At tif fnd of pbinting
    // (fndPbint) tif rfgion tibt wbs pbintfd is flusifd to tif sdrffn
    // (using BufffrStrbtfgy.siow).
    //
    // PbintMbnbgfr.siow is ovfrridfn to siow dirfdtly from tif
    // BufffrStrbtfgy (wifn using blit), if suddfssful truf is
    // rfturnfd bnd b pbint fvfnt will not bf gfnfrbtfd.  To bvoid
    // siowing from tif bufffr wiilf pbinting b lodking sdifmf is
    // implfmfntfd.  Wifn bfginPbint is invokfd tif fifld pbinting is
    // sft to truf.  If pbinting is truf bnd siow is invokfd wf
    // immfdibtfly rfturn fblsf.  Tiis is donf to bvoid blodking tif
    // toolkit tirfbd wiilf pbinting ibppfns.  In b similbr wby wifn
    // siow is invokfd tif fifld siowing is sft to truf, bfginPbint
    // will tifn blodk until siowing is truf.  Tiis sdifmf fnsurfs wf
    // only fvfr ibvf onf tirfbd using tif BufffrStrbtfgy bnd it blso
    // fnsurfs tif toolkit tirfbd rfmbins bs rfsponsivf bs possiblf.
    //
    // If wf'rf using b flip strbtfgy tif dontfnts of tif bbdkbufffr mby
    // ibvf dibngfd bnd so siow only bttfmpts to siow from tif bbdkbufffr
    // if wf gft b blit strbtfgy.
    //

    privbtf stbtid finbl PlbtformLoggfr LOGGER = PlbtformLoggfr.gftLoggfr(
                           "jbvbx.swing.BufffrStrbtfgyPbintMbnbgfr");

    /**
     * List of BufffrInfos.  Wf don't usf b Mbp primbrily bfdbusf
     * tifrf brf typidblly only b ibndful of top lfvfl domponfnts mbking
     * b Mbp ovfrkill.
     */
    privbtf ArrbyList<BufffrInfo> bufffrInfos;

    /**
     * Indidbtfs <dodf>bfginPbint</dodf> ibs bffn invokfd.  Tiis is
     * sft to truf for tif liff of bfginPbint/fndPbint pbir.
     */
    privbtf boolfbn pbinting;
    /**
     * Indidbtfs wf'rf in tif prodfss of siowing.  All pbinting, on tif EDT,
     * is blodkfd wiilf tiis is truf.
     */
    privbtf boolfbn siowing;

    //
    // Rfgion tibt wf nffd to flusi.  Wifn bfginPbint is dbllfd tifsf brf
    // rfsft bnd bny subsfqufnt dblls to pbint/dopyArfb tifn updbtf tifsf
    // fiflds bddordingly.  Wifn fndPbint is dbllfd wf tifn try bnd siow
    // tif bddumulbtfd rfgion.
    // Tifsf fiflds brf in tif doordinbtf systfm of tif root.
    //
    privbtf int bddumulbtfdX;
    privbtf int bddumulbtfdY;
    privbtf int bddumulbtfdMbxX;
    privbtf int bddumulbtfdMbxY;

    //
    // Tif following fiflds brf sft by prfpbrf
    //

    /**
     * Fbrtifst JComponfnt bndfstor for tif durrfnt pbint/dopyArfb.
     */
    privbtf JComponfnt rootJ;
    /**
     * Lodbtion of domponfnt bfing pbintfd rflbtivf to root.
     */
    privbtf int xOffsft;
    /**
     * Lodbtion of domponfnt bfing pbintfd rflbtivf to root.
     */
    privbtf int yOffsft;
    /**
     * Grbpiids from tif BufffrStrbtfgy.
     */
    privbtf Grbpiids bsg;
    /**
     * BufffrStrbtfgy durrfntly bfing usfd.
     */
    privbtf BufffrStrbtfgy bufffrStrbtfgy;
    /**
     * BufffrInfo dorrfsponding to root.
     */
    privbtf BufffrInfo bufffrInfo;

    /**
     * Sft to truf if tif bufffrInfo nffds to bf disposfd wifn durrfnt
     * pbint loop is donf.
     */
    privbtf boolfbn disposfBufffrOnEnd;

    BufffrStrbtfgyPbintMbnbgfr() {
        bufffrInfos = nfw ArrbyList<BufffrInfo>(1);
    }

    //
    // PbintMbnbgfr mftiods
    //

    /**
     * Clfbns up bny drfbtfd BufffrStrbtfgifs.
     */
    protfdtfd void disposf() {
        // diposf dbn bf invokfd bt bny rbndom timf. To bvoid
        // tirfbding dfpfndbndifs wf do tif bdtubl diposing vib bn
        // invokfLbtfr.
        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                jbvb.util.List<BufffrInfo> bufffrInfos;
                syndironizfd(BufffrStrbtfgyPbintMbnbgfr.tiis) {
                    wiilf (siowing) {
                        try {
                            BufffrStrbtfgyPbintMbnbgfr.tiis.wbit();
                        } dbtdi (IntfrruptfdExdfption if) {
                        }
                    }
                    bufffrInfos = BufffrStrbtfgyPbintMbnbgfr.tiis.bufffrInfos;
                    BufffrStrbtfgyPbintMbnbgfr.tiis.bufffrInfos = null;
                }
                disposf(bufffrInfos);
            }
        });
    }

    privbtf void disposf(jbvb.util.List<BufffrInfo> bufffrInfos) {
        if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            LOGGER.finfr("BufffrStrbtfgyPbintMbnbgfr disposfd",
                         nfw RuntimfExdfption());
        }
        if (bufffrInfos != null) {
            for (BufffrInfo bufffrInfo : bufffrInfos) {
                bufffrInfo.disposf();
            }
        }
    }

    /**
     * Siows tif spfdififd rfgion of tif bbdk bufffr.  Tiis will rfturn
     * truf if suddfssful, fblsf otifrwisf.  Tiis is invokfd on tif
     * toolkit tirfbd in rfsponsf to bn fxposf fvfnt.
     */
    publid boolfbn siow(Contbinfr d, int x, int y, int w, int i) {
        syndironizfd(tiis) {
            if (pbinting) {
                // Don't siow from bbdkbufffr wiilf in tif prodfss of
                // pbinting.
                rfturn fblsf;
            }
            siowing = truf;
        }
        try {
            BufffrInfo info = gftBufffrInfo(d);
            BufffrStrbtfgy bufffrStrbtfgy;
            if (info != null && info.isInSynd() &&
                (bufffrStrbtfgy = info.gftBufffrStrbtfgy(fblsf)) != null) {
                SubRfgionSiowbblf bsSubRfgion =
                        (SubRfgionSiowbblf)bufffrStrbtfgy;
                boolfbn pbintAllOnExposf = info.gftPbintAllOnExposf();
                info.sftPbintAllOnExposf(fblsf);
                if (bsSubRfgion.siowIfNotLost(x, y, (x + w), (y + i))) {
                    rfturn !pbintAllOnExposf;
                }
                // Mbrk tif bufffr bs nffding to bf rfpbintfd.  Wf don't
                // immfdibtfly do b rfpbint bs tiis mftiod will rfturn fblsf
                // indidbting b PbintEvfnt siould bf gfnfrbtfd wiidi will
                // triggfr b domplftf rfpbint.
                bufffrInfo.sftContfntsLostDuringExposf(truf);
            }
        }
        finblly {
            syndironizfd(tiis) {
                siowing = fblsf;
                notifyAll();
            }
        }
        rfturn fblsf;
    }

    publid boolfbn pbint(JComponfnt pbintingComponfnt,
                         JComponfnt bufffrComponfnt, Grbpiids g,
                         int x, int y, int w, int i) {
        Contbinfr root = fftdiRoot(pbintingComponfnt);

        if (prfpbrf(pbintingComponfnt, root, truf, x, y, w, i)) {
            if ((g instbndfof SunGrbpiids2D) &&
                    ((SunGrbpiids2D)g).gftDfstinbtion() == root) {
                // BufffrStrbtfgy mby ibvf blrfbdy donstrbinfd tif Grbpiids. To
                // bddount for tibt wf rfvfrt tif donstrbin, tifn bpply b
                // donstrbin for Swing on top of tibt.
                int dx = ((SunGrbpiids2D)bsg).donstrbinX;
                int dy = ((SunGrbpiids2D)bsg).donstrbinY;
                if (dx != 0 || dy != 0) {
                    bsg.trbnslbtf(-dx, -dy);
                }
                ((SunGrbpiids2D)bsg).donstrbin(xOffsft + dx, yOffsft + dy,
                                               x + w, y + i);
                bsg.sftClip(x, y, w, i);
                pbintingComponfnt.pbintToOffsdrffn(bsg, x, y, w, i,
                                                   x + w, y + i);
                bddumulbtf(xOffsft + x, yOffsft + y, w, i);
                rfturn truf;
            } flsf {
                // Assumf tify brf going to fvfntublly rfndfr to tif sdrffn.
                // Tiis disbblfs siowing from bbdkbufffr until b domplftf
                // rfpbint oddurs.
                bufffrInfo.sftInSynd(fblsf);
                // Fbll tirougi to old rfndfring.
            }
        }
        // Invblid root, do wibt Swing ibs blwbys donf.
        if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            LOGGER.finfr("prfpbrf fbilfd");
        }
        rfturn supfr.pbint(pbintingComponfnt, bufffrComponfnt, g, x, y, w, i);
    }

    publid void dopyArfb(JComponfnt d, Grbpiids g, int x, int y, int w, int i,
                         int dfltbX, int dfltbY, boolfbn dlip) {
        // Notf: tiis mftiod is only dbllfd intfrnblly bnd wf know tibt
        // g is from b ifbvywfigit Componfnt, so no difdk is nfdfssbry bs
        // it is in pbint() bbovf.
        //
        // If tif bufffr isn't in synd tifrf is no point in doing b dopyArfb,
        // it ibs gbrbbgf.
        Contbinfr root = fftdiRoot(d);

        if (prfpbrf(d, root, fblsf, 0, 0, 0, 0) && bufffrInfo.isInSynd()) {
            if (dlip) {
                Rfdtbnglf dBounds = d.gftVisiblfRfdt();
                int rflX = xOffsft + x;
                int rflY = yOffsft + y;
                bsg.dlipRfdt(xOffsft + dBounds.x,
                             yOffsft + dBounds.y,
                             dBounds.widti, dBounds.ifigit);
                bsg.dopyArfb(rflX, rflY, w, i, dfltbX, dfltbY);
            }
            flsf {
                bsg.dopyArfb(xOffsft + x, yOffsft + y, w, i, dfltbX,
                             dfltbY);
            }
            bddumulbtf(x + xOffsft + dfltbX, y + yOffsft + dfltbY, w, i);
        } flsf {
            if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                LOGGER.finfr("dopyArfb: prfpbrf fbilfd or not in synd");
            }
            // Prfpbrf fbilfd, or not in synd. By dblling supfr.dopyArfb
            // wf'll dopy on sdrffn. Wf nffd to flusi bny pfnding pbint to
            // tif sdrffn otifrwisf wf'll do b dopyArfb on tif wrong tiing.
            if (!flusiAddumulbtfdRfgion()) {
                // Flusi fbilfd, dopyArfb will bf dopying gbrbbgf,
                // fordf rfpbint of bll.
                rootJ.rfpbint();
            } flsf {
                supfr.dopyArfb(d, g, x, y, w, i, dfltbX, dfltbY, dlip);
            }
        }
    }

    publid void bfginPbint() {
        syndironizfd(tiis) {
            pbinting = truf;
            // Mbkf surf bnotifr tirfbd isn't bttfmpting to siow from
            // tif bbdk bufffr.
            wiilf(siowing) {
                try {
                    wbit();
                } dbtdi (IntfrruptfdExdfption if) {
                }
            }
        }
        if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            LOGGER.finfst("bfginPbint");
        }
        // Rfsft tif brfb tibt nffds to bf pbintfd.
        rfsftAddumulbtfd();
    }

    publid void fndPbint() {
        if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            LOGGER.finfst("fndPbint: rfgion " + bddumulbtfdX + " " +
                       bddumulbtfdY + " " +  bddumulbtfdMbxX + " " +
                       bddumulbtfdMbxY);
        }
        if (pbinting) {
            if (!flusiAddumulbtfdRfgion()) {
                if (!isRfpbintingRoot()) {
                    rfpbintRoot(rootJ);
                }
                flsf {
                    // Contfnts lost twidf in b row, punt.
                    rfsftDoublfBufffrPfrWindow();
                    // In dbsf wf'vf lfft junk on tif sdrffn, fordf b rfpbint.
                    rootJ.rfpbint();
                }
            }
        }

        BufffrInfo toDisposf = null;
        syndironizfd(tiis) {
            pbinting = fblsf;
            if (disposfBufffrOnEnd) {
                disposfBufffrOnEnd = fblsf;
                toDisposf = bufffrInfo;
                bufffrInfos.rfmovf(toDisposf);
            }
        }
        if (toDisposf != null) {
            toDisposf.disposf();
        }
    }

    /**
     * Rfndfrs tif BufffrStrbtfgy to tif sdrffn.
     *
     * @rfturn truf if suddfssful, fblsf otifrwisf.
     */
    privbtf boolfbn flusiAddumulbtfdRfgion() {
        boolfbn suddfss = truf;
        if (bddumulbtfdX != Intfgfr.MAX_VALUE) {
            SubRfgionSiowbblf bsSubRfgion = (SubRfgionSiowbblf)bufffrStrbtfgy;
            boolfbn dontfntsLost = bufffrStrbtfgy.dontfntsLost();
            if (!dontfntsLost) {
                bsSubRfgion.siow(bddumulbtfdX, bddumulbtfdY,
                                 bddumulbtfdMbxX, bddumulbtfdMbxY);
                dontfntsLost = bufffrStrbtfgy.dontfntsLost();
            }
            if (dontfntsLost) {
                if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    LOGGER.finfr("fndPbint: dontfnts lost");
                }
                // Siown rfgion wbs bogus, mbrk bufffr bs out of synd.
                bufffrInfo.sftInSynd(fblsf);
                suddfss = fblsf;
            }
        }
        rfsftAddumulbtfd();
        rfturn suddfss;
    }

    privbtf void rfsftAddumulbtfd() {
        bddumulbtfdX = Intfgfr.MAX_VALUE;
        bddumulbtfdY = Intfgfr.MAX_VALUE;
        bddumulbtfdMbxX = 0;
        bddumulbtfdMbxY = 0;
    }

    /**
     * Invokfd wifn tif doublf bufffring or usfTrufDoublfBufffring
     * dibngfs for b JRootPbnf.  If tif rootpbnf is not doublf
     * bufffrfd, or truf doublf bufffring dibngfs wf tirow out bny
     * dbdif wf mby ibvf.
     */
    publid void doublfBufffringCibngfd(finbl JRootPbnf rootPbnf) {
        if ((!rootPbnf.isDoublfBufffrfd() ||
                !rootPbnf.gftUsfTrufDoublfBufffring()) &&
                rootPbnf.gftPbrfnt() != null) {
            if (!SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
                Runnbblf updbtfr = nfw Runnbblf() {
                    publid void run() {
                        doublfBufffringCibngfd0(rootPbnf);
                    }
                };
                SwingUtilitifs.invokfLbtfr(updbtfr);
            }
            flsf {
                doublfBufffringCibngfd0(rootPbnf);
            }
        }
    }

    /**
     * Dofs tif work for doublfBufffringCibngfd.
     */
    privbtf void doublfBufffringCibngfd0(JRootPbnf rootPbnf) {
        // Tiis will only ibppfn on tif EDT.
        BufffrInfo info;
        syndironizfd(tiis) {
            // Mbkf surf bnotifr tirfbd isn't bttfmpting to siow from
            // tif bbdk bufffr.
            wiilf(siowing) {
                try {
                    wbit();
                } dbtdi (IntfrruptfdExdfption if) {
                }
            }
            info = gftBufffrInfo(rootPbnf.gftPbrfnt());
            if (pbinting && bufffrInfo == info) {
                // Wf'rf in tif prodfss of pbinting bnd tif usfr grbbbfd
                // tif Grbpiids. If wf disposf now, fndPbint will bttfmpt
                // to siow b bogus BufffrStrbtfgy. Sft b flbg so tibt
                // fndPbint knows it nffds to disposf tiis bufffr.
                disposfBufffrOnEnd = truf;
                info = null;
            } flsf if (info != null) {
                bufffrInfos.rfmovf(info);
            }
        }
        if (info != null) {
            info.disposf();
        }
    }

    /**
     * Cbldulbtfs informbtion dommon to pbint/dopyArfb.
     *
     * @rfturn truf if siould usf bufffring pfr window in pbinting.
     */
    privbtf boolfbn prfpbrf(JComponfnt d, Contbinfr root, boolfbn isPbint, int x, int y,
                            int w, int i) {
        if (bsg != null) {
            bsg.disposf();
            bsg = null;
        }
        bufffrStrbtfgy = null;
        if (root != null) {
            boolfbn dontfntsLost = fblsf;
            BufffrInfo bufffrInfo = gftBufffrInfo(root);
            if (bufffrInfo == null) {
                dontfntsLost = truf;
                bufffrInfo = nfw BufffrInfo(root);
                bufffrInfos.bdd(bufffrInfo);
                if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    LOGGER.finfr("prfpbrf: nfw BufffrInfo: " + root);
                }
            }
            tiis.bufffrInfo = bufffrInfo;
            if (!bufffrInfo.ibsBufffrStrbtfgyCibngfd()) {
                bufffrStrbtfgy = bufffrInfo.gftBufffrStrbtfgy(truf);
                if (bufffrStrbtfgy != null) {
                    bsg = bufffrStrbtfgy.gftDrbwGrbpiids();
                    if (bufffrStrbtfgy.dontfntsRfstorfd()) {
                        dontfntsLost = truf;
                        if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                            LOGGER.finfr("prfpbrf: dontfnts rfstorfd in prfpbrf");
                        }
                    }
                }
                flsf {
                    // Couldn't drfbtf BufffrStrbtfgy, fbllbbdk to normbl
                    // pbinting.
                    rfturn fblsf;
                }
                if (bufffrInfo.gftContfntsLostDuringExposf()) {
                    dontfntsLost = truf;
                    bufffrInfo.sftContfntsLostDuringExposf(fblsf);
                    if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        LOGGER.finfr("prfpbrf: dontfnts lost on fxposf");
                    }
                }
                if (isPbint && d == rootJ && x == 0 && y == 0 &&
                      d.gftWidti() == w && d.gftHfigit() == i) {
                    bufffrInfo.sftInSynd(truf);
                }
                flsf if (dontfntsLost) {
                    // Wf fitifr rfdrfbtfd tif BufffrStrbtfgy, or tif dontfnts
                    // of tif bufffr strbtfgy wfrf rfstorfd.  Wf nffd to
                    // rfpbint tif root pbnf so tibt tif bbdk bufffr is in synd
                    // bgbin.
                    bufffrInfo.sftInSynd(fblsf);
                    if (!isRfpbintingRoot()) {
                        rfpbintRoot(rootJ);
                    }
                    flsf {
                        // Contfnts lost twidf in b row, punt
                        rfsftDoublfBufffrPfrWindow();
                    }
                }
                rfturn (bufffrInfos != null);
            }
        }
        rfturn fblsf;
    }

    privbtf Contbinfr fftdiRoot(JComponfnt d) {
        boolfbn fndountfrfdHW = fblsf;
        rootJ = d;
        Contbinfr root = d;
        xOffsft = yOffsft = 0;
        wiilf (root != null &&
               (!(root instbndfof Window) &&
                !SunToolkit.isInstbndfOf(root, "jbvb.bpplft.Applft"))) {
            xOffsft += root.gftX();
            yOffsft += root.gftY();
            root = root.gftPbrfnt();
            if (root != null) {
                if (root instbndfof JComponfnt) {
                    rootJ = (JComponfnt)root;
                }
                flsf if (!root.isLigitwfigit()) {
                    if (!fndountfrfdHW) {
                        fndountfrfdHW = truf;
                    }
                    flsf {
                        // Wf'vf fndountfrfd two iws now bnd mby ibvf
                        // b dontbinmfnt iifrbrdiy witi ligitwfigits dontbining
                        // ifbvywfigits dontbining otifr ligitwfigits.
                        // Hfbvywfigits pokf iolfs in ligitwfigit
                        // rfndfring so tibt if wf dbll siow on tif BS
                        // (wiidi is bssodibtfd witi tif Window) you will
                        // not sff tif dontfnts ovfr bny diild
                        // ifbvywfigits.  If wf didn't do tiis wifn wf
                        // wfnt to siow tif dfsdfndbnts of tif nfstfd iw
                        // you would sff notiing, so, wf bbil out ifrf.
                        rfturn null;
                    }
                }
            }
        }
        if ((root instbndfof RootPbnfContbinfr) &&
            (rootJ instbndfof JRootPbnf)) {
            // Wf'rf in b Swing ifbvyfigit (JFrbmf/JWindow...), usf doublf
            // bufffring if doublf bufffring fnbblfd on tif JRootPbnf bnd
            // tif JRootPbnf wbnts truf doublf bufffring.
            if (rootJ.isDoublfBufffrfd() &&
                    ((JRootPbnf)rootJ).gftUsfTrufDoublfBufffring()) {
                // Wiftifr or not b domponfnt is doublf bufffrfd is b
                // bit tridky witi Swing. Tiis givfs b good bpproximbtion
                // of tif vbrious wbys to turn on doublf bufffring for
                // domponfnts.
                rfturn root;
            }
        }
        // Don't do truf doublf bufffring.
        rfturn null;
    }

    /**
     * Turns off doublf bufffring pfr window.
     */
    privbtf void rfsftDoublfBufffrPfrWindow() {
        if (bufffrInfos != null) {
            disposf(bufffrInfos);
            bufffrInfos = null;
            rfpbintMbnbgfr.sftPbintMbnbgfr(null);
        }
    }

    /**
     * Rfturns tif BufffrInfo for tif spfdififd root or null if onf
     * ibsn't bffn drfbtfd yft.
     */
    privbtf BufffrInfo gftBufffrInfo(Contbinfr root) {
        for (int dountfr = bufffrInfos.sizf() - 1; dountfr >= 0; dountfr--) {
            BufffrInfo bufffrInfo = bufffrInfos.gft(dountfr);
            Contbinfr biRoot = bufffrInfo.gftRoot();
            if (biRoot == null) {
                // Window gd'fd
                bufffrInfos.rfmovf(dountfr);
                if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    LOGGER.finfr("BufffrInfo prunfd, root null");
                }
            }
            flsf if (biRoot == root) {
                rfturn bufffrInfo;
            }
        }
        rfturn null;
    }

    privbtf void bddumulbtf(int x, int y, int w, int i) {
        bddumulbtfdX = Mbti.min(x, bddumulbtfdX);
        bddumulbtfdY = Mbti.min(y, bddumulbtfdY);
        bddumulbtfdMbxX = Mbti.mbx(bddumulbtfdMbxX, x + w);
        bddumulbtfdMbxY = Mbti.mbx(bddumulbtfdMbxY, y + i);
    }



    /**
     * BufffrInfo is usfd to trbdk tif BufffrStrbtfgy bfing usfd for
     * b pbrtidulbr Componfnt.  In bddition to trbdking tif BufffrStrbtfgy
     * it will instbll b WindowListfnfr bnd ComponfntListfnfr.  Wifn tif
     * domponfnt is iiddfn/idonififd tif bufffr is mbrkfd bs nffding to bf
     * domplftfly rfpbintfd.
     */
    privbtf dlbss BufffrInfo fxtfnds ComponfntAdbptfr implfmfnts
                               WindowListfnfr {
        // NOTE: Tiis dlbss dofs NOT iold b dirfdt rfffrfndf to tif root, if it
        // did tifrf would bf b dydlf bftwffn tif BufffrPfrWindowPbintMbnbgfr
        // bnd tif Window so tibt it dould nfvfr bf GC'fd
        //
        // Rfffrfndf to BufffrStrbtfgy is rfffrfndfd vib WfbkRfffrfndf for
        // sbmf rfbson.
        privbtf WfbkRfffrfndf<BufffrStrbtfgy> wfbkBS;
        privbtf WfbkRfffrfndf<Contbinfr> root;
        // Indidbtfs wiftifr or not tif bbdkbufffr bnd displby brf in synd.
        // Tiis is sft to truf wifn b full rfpbint on tif rootpbnf is donf.
        privbtf boolfbn inSynd;
        // Indidbtfs tif dontfnts wfrf lost during bnd fxposf fvfnt.
        privbtf boolfbn dontfntsLostDuringExposf;
        // Indidbtfs wf nffd to gfnfrbtf b pbint fvfnt on fxposf.
        privbtf boolfbn pbintAllOnExposf;


        publid BufffrInfo(Contbinfr root) {
            tiis.root = nfw WfbkRfffrfndf<Contbinfr>(root);
            root.bddComponfntListfnfr(tiis);
            if (root instbndfof Window) {
                ((Window)root).bddWindowListfnfr(tiis);
            }
        }

        publid void sftPbintAllOnExposf(boolfbn pbintAllOnExposf) {
            tiis.pbintAllOnExposf = pbintAllOnExposf;
        }

        publid boolfbn gftPbintAllOnExposf() {
            rfturn pbintAllOnExposf;
        }

        publid void sftContfntsLostDuringExposf(boolfbn vbluf) {
            dontfntsLostDuringExposf = vbluf;
        }

        publid boolfbn gftContfntsLostDuringExposf() {
            rfturn dontfntsLostDuringExposf;
        }

        publid void sftInSynd(boolfbn inSynd) {
            tiis.inSynd = inSynd;
        }

        /**
         * Wiftifr or not tif dontfnts of tif bufffr strbtfgy
         * is in synd witi tif window.  Tiis is sft to truf wifn tif root
         * pbnf pbints bll, bnd fblsf wifn dontfnts brf lost/rfstorfd.
         */
        publid boolfbn isInSynd() {
            rfturn inSynd;
        }

        /**
         * Rfturns tif Root (Window or Applft) tibt tiis BufffrInfo rfffrfndfs.
         */
        publid Contbinfr gftRoot() {
            rfturn (root == null) ? null : root.gft();
        }

        /**
         * Rfturns tif BufffrStbrtfgy.  Tiis will rfturn null if
         * tif BufffrStbrtfgy ibsn't bffn drfbtfd bnd <dodf>drfbtf</dodf> is
         * fblsf, or if tifrf is b problfm in drfbting tif
         * <dodf>BufffrStbrtfgy</dodf>.
         *
         * @pbrbm drfbtf If truf, bnd tif BufffrStbrtfgy is durrfntly null,
         *               onf will bf drfbtfd.
         */
        publid BufffrStrbtfgy gftBufffrStrbtfgy(boolfbn drfbtf) {
            BufffrStrbtfgy bs = (wfbkBS == null) ? null : wfbkBS.gft();
            if (bs == null && drfbtf) {
                bs = drfbtfBufffrStrbtfgy();
                if (bs != null) {
                    wfbkBS = nfw WfbkRfffrfndf<BufffrStrbtfgy>(bs);
                }
                if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    LOGGER.finfr("gftBufffrStrbtfgy: drfbtfd bs: " + bs);
                }
            }
            rfturn bs;
        }

        /**
         * Rfturns truf if tif bufffr strbtfgy of tif domponfnt difffrs
         * from durrfnt bufffr strbtfgy.
         */
        publid boolfbn ibsBufffrStrbtfgyCibngfd() {
            Contbinfr root = gftRoot();
            if (root != null) {
                BufffrStrbtfgy ourBS = null;
                BufffrStrbtfgy domponfntBS = null;

                ourBS = gftBufffrStrbtfgy(fblsf);
                if (root instbndfof Window) {
                    domponfntBS = ((Window)root).gftBufffrStrbtfgy();
                }
                flsf {
                    domponfntBS = AWTAddfssor.gftComponfntAddfssor().gftBufffrStrbtfgy(root);
                }
                if (domponfntBS != ourBS) {
                    // Componfnt ibs b difffrfnt BS, disposf ours.
                    if (ourBS != null) {
                        ourBS.disposf();
                    }
                    wfbkBS = null;
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        /**
         * Crfbtfs tif BufffrStrbtfgy.  If tif bppropribtf systfm propfrty
         * ibs bffn sft wf'll try for flip first bnd tifn wf'll try for
         * blit.
         */
        privbtf BufffrStrbtfgy drfbtfBufffrStrbtfgy() {
            Contbinfr root = gftRoot();
            if (root == null) {
                rfturn null;
            }
            BufffrStrbtfgy bs = null;
            if (SwingUtilitifs3.isVsyndRfqufstfd(root)) {
                bs = drfbtfBufffrStrbtfgy(root, truf);
                if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    LOGGER.finfr("drfbtfBufffrStrbtfgy: using vsyndfd strbtfgy");
                }
            }
            if (bs == null) {
                bs = drfbtfBufffrStrbtfgy(root, fblsf);
            }
            if (!(bs instbndfof SubRfgionSiowbblf)) {
                // Wf do tiis for two rfbsons:
                // 1. So tibt wf know wf dbn dbst to SubRfgionSiowbblf bnd
                //    invokf siow witi tif minimbl rfgion to updbtf
                // 2. To bvoid tif possibility of invoking dlifnt dodf
                //    on tif toolkit tirfbd.
                bs = null;
            }
            rfturn bs;
        }

        // Crfbtfs bnd rfturns b bufffr strbtfgy.  If
        // tifrf is b problfm drfbting tif bufffr strbtfgy tiis will
        // fbt tif fxdfption bnd rfturn null.
        privbtf BufffrStrbtfgy drfbtfBufffrStrbtfgy(Contbinfr root,
                boolfbn isVsyndfd) {
            BufffrCbpbbilitifs dbps;
            if (isVsyndfd) {
                dbps = nfw ExtfndfdBufffrCbpbbilitifs(
                    nfw ImbgfCbpbbilitifs(truf), nfw ImbgfCbpbbilitifs(truf),
                    BufffrCbpbbilitifs.FlipContfnts.COPIED,
                    ExtfndfdBufffrCbpbbilitifs.VSyndTypf.VSYNC_ON);
            } flsf {
                dbps = nfw BufffrCbpbbilitifs(
                    nfw ImbgfCbpbbilitifs(truf), nfw ImbgfCbpbbilitifs(truf),
                    null);
            }
            BufffrStrbtfgy bs = null;
            if (SunToolkit.isInstbndfOf(root, "jbvb.bpplft.Applft")) {
                try {
                    AWTAddfssor.ComponfntAddfssor domponfntAddfssor
                            = AWTAddfssor.gftComponfntAddfssor();
                    domponfntAddfssor.drfbtfBufffrStrbtfgy(root, 2, dbps);
                    bs = domponfntAddfssor.gftBufffrStrbtfgy(root);
                } dbtdi (AWTExdfption f) {
                    // Typf is not supportfd
                    if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        LOGGER.finfr("drfbtfBufffrStrbtfty fbilfd",
                                     f);
                    }
                }
            }
            flsf {
                try {
                    ((Window)root).drfbtfBufffrStrbtfgy(2, dbps);
                    bs = ((Window)root).gftBufffrStrbtfgy();
                } dbtdi (AWTExdfption f) {
                    // Typf not supportfd
                    if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        LOGGER.finfr("drfbtfBufffrStrbtfty fbilfd",
                                     f);
                    }
                }
            }
            rfturn bs;
        }

        /**
         * Clfbns up bnd rfmovfs bny rfffrfndfs.
         */
        publid void disposf() {
            Contbinfr root = gftRoot();
            if (LOGGER.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                LOGGER.finfr("disposfd BufffrInfo for: " + root);
            }
            if (root != null) {
                root.rfmovfComponfntListfnfr(tiis);
                if (root instbndfof Window) {
                    ((Window)root).rfmovfWindowListfnfr(tiis);
                }
                BufffrStrbtfgy bs = gftBufffrStrbtfgy(fblsf);
                if (bs != null) {
                    bs.disposf();
                }
            }
            tiis.root = null;
            wfbkBS = null;
        }

        // Wf mbrk tif bufffr bs nffding to bf pbintfd on b iidf/idonify
        // bfdbusf tif dfvflopfr mby ibvf donditionblizfd pbinting bbsfd on
        // visibility.
        // Idfblly wf would blso movf to ibving tif BufffrStrbtfgy bfing
        // b SoftRfffrfndf in Componfnt ifrf, but tibt rfquirfs dibngfs to
        // Componfnt bnd tif likf.
        publid void domponfntHiddfn(ComponfntEvfnt f) {
            Contbinfr root = gftRoot();
            if (root != null && root.isVisiblf()) {
                // Tiis dbsf will only ibppfn if b dfvflopfr dblls
                // iidf immfdibtfly followfd by siow.  In tiis dbsf
                // tif fvfnt is dflivfrfd bftfr siow bnd tif window
                // will still bf visiblf.  If b dfvflopfr bltfrfd tif
                // dontfnts of tif window bftwffn tif iidf/siow
                // invodbtions wf won't rfdognizf wf nffd to pbint bnd
                // tif dontfnts would bf bogus.  Cblling rfpbint ifrf
                // fixs fvfrytiing up.
                root.rfpbint();
            }
            flsf {
                sftPbintAllOnExposf(truf);
            }
        }

        publid void windowIdonififd(WindowEvfnt f) {
            sftPbintAllOnExposf(truf);
        }

        // On b disposf wf diudk fvfrytiing.
        publid void windowClosfd(WindowEvfnt f) {
            // Mbkf surf wf'rf not siowing.
            syndironizfd(BufffrStrbtfgyPbintMbnbgfr.tiis) {
                wiilf (siowing) {
                    try {
                        BufffrStrbtfgyPbintMbnbgfr.tiis.wbit();
                    } dbtdi (IntfrruptfdExdfption if) {
                    }
                }
                bufffrInfos.rfmovf(tiis);
            }
            disposf();
        }

        publid void windowOpfnfd(WindowEvfnt f) {
        }

        publid void windowClosing(WindowEvfnt f) {
        }

        publid void windowDfidonififd(WindowEvfnt f) {
        }

        publid void windowAdtivbtfd(WindowEvfnt f) {
        }

        publid void windowDfbdtivbtfd(WindowEvfnt f) {
        }
    }
}
