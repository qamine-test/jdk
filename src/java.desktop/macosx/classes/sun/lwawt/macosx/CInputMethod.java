/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.im.spi.*;
import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.im.*;
import jbvb.bwt.font.*;
import jbvb.lbng.Cibrbdtfr.Subsft;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.tfxt.*;
import jbvbx.swing.tfxt.JTfxtComponfnt;

import sun.bwt.im.InputMftiodAdbptfr;
import sun.lwbwt.*;

publid dlbss CInputMftiod fxtfnds InputMftiodAdbptfr {
    privbtf InputMftiodContfxt fIMContfxt;
    privbtf Componfnt fAwtFodussfdComponfnt;
    privbtf LWComponfntPffr<?, ?> fAwtFodussfdComponfntPffr;
    privbtf boolfbn isAdtivf;

    privbtf stbtid Mbp<TfxtAttributf, Intfgfr>[] sHigiligitStylfs;

    // Intitblizf iigiligit mbpping tbblf bnd its mbppfr.
    stbtid {
        @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
        Mbp<TfxtAttributf, Intfgfr> stylfs[] = nfw Mbp[4];
        HbsiMbp<TfxtAttributf, Intfgfr> mbp;

        // UNSELECTED_RAW_TEXT_HIGHLIGHT
        mbp = nfw HbsiMbp<TfxtAttributf, Intfgfr>(1);
        mbp.put(TfxtAttributf.INPUT_METHOD_UNDERLINE,
                TfxtAttributf.UNDERLINE_LOW_GRAY);
        stylfs[0] = Collfdtions.unmodifibblfMbp(mbp);

        // SELECTED_RAW_TEXT_HIGHLIGHT
        mbp = nfw HbsiMbp<TfxtAttributf, Intfgfr>(1);
        mbp.put(TfxtAttributf.INPUT_METHOD_UNDERLINE,
                TfxtAttributf.UNDERLINE_LOW_GRAY);
        stylfs[1] = Collfdtions.unmodifibblfMbp(mbp);

        // UNSELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = nfw HbsiMbp<TfxtAttributf, Intfgfr>(1);
        mbp.put(TfxtAttributf.INPUT_METHOD_UNDERLINE,
                TfxtAttributf.UNDERLINE_LOW_ONE_PIXEL);
        stylfs[2] = Collfdtions.unmodifibblfMbp(mbp);

        // SELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = nfw HbsiMbp<TfxtAttributf, Intfgfr>(1);
        mbp.put(TfxtAttributf.INPUT_METHOD_UNDERLINE,
                TfxtAttributf.UNDERLINE_LOW_TWO_PIXEL);
        stylfs[3] = Collfdtions.unmodifibblfMbp(mbp);

        sHigiligitStylfs = stylfs;

        nbtivfInit();

    }

    publid CInputMftiod() {
    }


    /**
        * Sfts tif input mftiod dontfxt, wiidi is usfd to dispbtdi input mftiod
     * fvfnts to tif dlifnt domponfnt bnd to rfqufst informbtion from
     * tif dlifnt domponfnt.
     * <p>
     * Tiis mftiod is dbllfd ondf immfdibtfly bftfr instbntibting tiis input
     * mftiod.
     *
     * @pbrbm dontfxt tif input mftiod dontfxt for tiis input mftiod
     * @fxdfption NullPointfrExdfption if <dodf>dontfxt</dodf> is null
     */
    publid void sftInputMftiodContfxt(InputMftiodContfxt dontfxt) {
        fIMContfxt = dontfxt;
    }

    /**
        * Attfmpts to sft tif input lodblf. If tif input mftiod supports tif
     * dfsirfd lodblf, it dibngfs its bfibvior to support input for tif lodblf
     * bnd rfturns truf.
     * Otifrwisf, it rfturns fblsf bnd dofs not dibngf its bfibvior.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod},
     * <li>wifn switdiing to tiis input mftiod tirougi tif usfr intfrfbdf if tif usfr
     *     spfdififd b lodblf or if tif prfviously sflfdtfd input mftiod's
     *     {@link jbvb.bwt.im.spi.InputMftiod#gftLodblf gftLodblf} mftiod
     *     rfturns b non-null vbluf.
     * </ul>
     *
     * @pbrbm lbng lodblf to input
     * @rfturn wiftifr tif spfdififd lodblf is supportfd
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     */
    publid boolfbn sftLodblf(Lodblf lbng) {
        rfturn sftLodblf(lbng, fblsf);
    }

    privbtf boolfbn sftLodblf(Lodblf lbng, boolfbn onAdtivbtf) {
        Objfdt[] bvbilbblf = CInputMftiodDfsdriptor.gftAvbilbblfLodblfsIntfrnbl();
        for (int i = 0; i < bvbilbblf.lfngti; i++) {
            Lodblf lodblf = (Lodblf)bvbilbblf[i];
            if (lbng.fqubls(lodblf) ||
                // spfdibl dompbtibility rulf for Jbpbnfsf bnd Korfbn
                lodblf.fqubls(Lodblf.JAPAN) && lbng.fqubls(Lodblf.JAPANESE) ||
                lodblf.fqubls(Lodblf.KOREA) && lbng.fqubls(Lodblf.KOREAN)) {
                if (isAdtivf) {
                    sftNbtivfLodblf(lodblf.toString(), onAdtivbtf);
                }
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
        * Rfturns tif durrfnt input lodblf. Migit rfturn null in fxdfptionbl dbsfs.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#gftLodblf InputContfxt.gftLodblf} bnd
     * <li>wifn switdiing from tiis input mftiod to b difffrfnt onf tirougi tif
     *     usfr intfrfbdf.
     * </ul>
     *
     * @rfturn tif durrfnt input lodblf, or null
     */
    publid Lodblf gftLodblf() {
        // On Mbd OS X wf'll bsk tif durrfntly bdtivf input mftiod wibt its lodblf is.
        Lodblf rfturnVbluf = gftNbtivfLodblf();
        if (rfturnVbluf == null) {
            rfturnVbluf = Lodblf.gftDffbult();
        }

        rfturn rfturnVbluf;
    }

    /**
        * Sfts tif subsfts of tif Unidodf dibrbdtfr sft tibt tiis input mftiod
     * is bllowfd to input. Null mby bf pbssfd in to indidbtf tibt bll
     * dibrbdtfrs brf bllowfd.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>immfdibtfly bftfr instbntibting tiis input mftiod,
     * <li>wifn switdiing to tiis input mftiod from b difffrfnt onf, bnd
     * <li>by {@link jbvb.bwt.im.InputContfxt#sftCibrbdtfrSubsfts InputContfxt.sftCibrbdtfrSubsfts}.
     * </ul>
     *
     * @pbrbm subsfts tif subsfts of tif Unidodf dibrbdtfr sft from wiidi
     * dibrbdtfrs mby bf input
     */
    publid void sftCibrbdtfrSubsfts(Subsft[] subsfts) {
        // -- SAK: Dofs mbd OS X support tiis?
    }

    /**
        * Composition dbnnot bf sft on Mbd OS X -- tif input mftiod rfmfmbfrs tiis
     */
    publid void sftCompositionEnbblfd(boolfbn fnbblf) {
        tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't bdjust domposition modf on Mbd OS X.");
    }

    publid boolfbn isCompositionEnbblfd() {
        tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't bdjust domposition modf on Mbd OS X.");
    }

    /**
     * Dispbtdifs tif fvfnt to tif input mftiod. If input mftiod support is
     * fnbblfd for tif fodussfd domponfnt, indoming fvfnts of dfrtbin typfs
     * brf dispbtdifd to tif durrfnt input mftiod for tiis domponfnt bfforf
     * tify brf dispbtdifd to tif domponfnt's mftiods or fvfnt listfnfrs.
     * Tif input mftiod dfdidfs wiftifr it nffds to ibndlf tif fvfnt. If it
     * dofs, it blso dblls tif fvfnt's <dodf>donsumf</dodf> mftiod; tiis
     * dbusfs tif fvfnt to not gft dispbtdifd to tif domponfnt's fvfnt
     * prodfssing mftiods or fvfnt listfnfrs.
     * <p>
     * Evfnts brf dispbtdifd if tify brf instbndfs of InputEvfnt or its
     * subdlbssfs.
     * Tiis indludfs instbndfs of tif AWT dlbssfs KfyEvfnt bnd MousfEvfnt.
     * <p>
     * Tiis mftiod is dbllfd by {@link jbvb.bwt.im.InputContfxt#dispbtdiEvfnt InputContfxt.dispbtdiEvfnt}.
     *
     * @pbrbm fvfnt tif fvfnt bfing dispbtdifd to tif input mftiod
     * @fxdfption NullPointfrExdfption if <dodf>fvfnt</dodf> is null
     */
    publid void dispbtdiEvfnt(finbl AWTEvfnt fvfnt) {
        // No-op for Mbd OS X.
    }


    /**
     * Adtivbtf bnd dfbdtivbtf brf no-ops on Mbd OS X.
     * A non-US kfybobrd lbyout is bn 'input mftiod' in tibt it gfnfrbtfs fvfnts tif sbmf wby bs
     * b CJK input mftiod. A domponfnt tibt dofsn't wbnt input mftiod fvfnts still wbnts tif dfbd-kfy
     * fvfnts.
     *
     *
     */
    publid void bdtivbtf() {
        isAdtivf = truf;
    }

    publid void dfbdtivbtf(boolfbn isTfmporbry) {
        isAdtivf = fblsf;
    }

    /**
     * Closfs or iidfs bll windows opfnfd by tiis input mftiod instbndf or
     * its dlbss.  Dfbdtivbtf iidfs windows for us on Mbd OS X.
     */
    publid void iidfWindows() {
    }

    long gftNbtivfVifwPtr(LWComponfntPffr<?, ?> pffr) {
        if (pffr.gftPlbtformWindow() instbndfof CPlbtformWindow) {
            CPlbtformWindow plbtformWindow = (CPlbtformWindow) pffr.gftPlbtformWindow();
            CPlbtformVifw plbtformVifw = plbtformWindow.gftContfntVifw();
            rfturn plbtformVifw.gftAWTVifw();
        } flsf {
            rfturn 0;
        }
    }

    /**
        * Notififs tif input mftiod tibt b dlifnt domponfnt ibs bffn
     * rfmovfd from its dontbinmfnt iifrbrdiy, or tibt input mftiod
     * support ibs bffn disbblfd for tif domponfnt.
     */
    publid void rfmovfNotify() {
        if (fAwtFodussfdComponfntPffr != null) {
            nbtivfEndComposition(gftNbtivfVifwPtr(fAwtFodussfdComponfntPffr));
        }

        fAwtFodussfdComponfntPffr = null;
    }

    /**
     * Informs tif input mftiod bdbptfr bbout tif domponfnt tibt ibs tif AWT
     * fodus if it's using tif input dontfxt owning tiis bdbptfr instbndf.
     * Wf blso tbkf tif opportunity to tfll tif nbtivf sidf tibt wf brf tif input mftiod
     * to tblk to wifn rfsponding to kfy fvfnts.
     */
    protfdtfd void sftAWTFodussfdComponfnt(Componfnt domponfnt) {
        LWComponfntPffr<?, ?> pffr = null;
        long modflPtr = 0;
        CInputMftiod imInstbndf = tiis;

        // domponfnt will bf null wifn wf brf told tifrf's no fodusfd domponfnt.
        // Wifn tibt ibppfns wf nffd to notify tif nbtivf brdiitfdturf to stop gfnfrbting IMEs
        if (domponfnt == null) {
            pffr = fAwtFodussfdComponfntPffr;
            imInstbndf = null;
        } flsf {
            pffr = gftNfbrfstNbtivfPffr(domponfnt);

            // If wf ibvf b pbssivf dlifnt, don't pbss input mftiod fvfnts to it.
            if (domponfnt.gftInputMftiodRfqufsts() == null) {
                imInstbndf = null;
            }
        }

        if (pffr != null) {
            modflPtr = gftNbtivfVifwPtr(pffr);

            // modflPtr rfffrs to tif ControlModfl tibt fitifr got or lost fodus.
            nbtivfNotifyPffr(modflPtr, imInstbndf);
        }

        // Trbdk tif fodusfd domponfnt bnd its nfbrfst pffr.
        fAwtFodussfdComponfnt = domponfnt;
        fAwtFodussfdComponfntPffr = gftNfbrfstNbtivfPffr(domponfnt);
    }

    /**
        * @sff jbvb.bwt.Toolkit#mbpInputMftiodHigiligit
     */
    publid stbtid Mbp<TfxtAttributf, ?> mbpInputMftiodHigiligit(InputMftiodHigiligit iigiligit) {
        int indfx;
        int stbtf = iigiligit.gftStbtf();
        if (stbtf == InputMftiodHigiligit.RAW_TEXT) {
            indfx = 0;
        } flsf if (stbtf == InputMftiodHigiligit.CONVERTED_TEXT) {
            indfx = 2;
        } flsf {
            rfturn null;
        }
        if (iigiligit.isSflfdtfd()) {
            indfx += 1;
        }
        rfturn sHigiligitStylfs[indfx];
    }

    /**
        * Ends bny input domposition tibt mby durrfntly bf going on in tiis
     * dontfxt. Dfpfnding on tif plbtform bnd possibly usfr prfffrfndfs,
     * tiis mby dommit or dflftf undommittfd tfxt. Any dibngfs to tif tfxt
     * brf dommunidbtfd to tif bdtivf domponfnt using bn input mftiod fvfnt.
     *
     * <p>
     * A tfxt fditing domponfnt mby dbll tiis in b vbrifty of situbtions,
     * for fxbmplf, wifn tif usfr movfs tif insfrtion point witiin tif tfxt
     * (but outsidf tif domposfd tfxt), or wifn tif domponfnt's tfxt is
     * sbvfd to b filf or dopifd to tif dlipbobrd.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#fndComposition InputContfxt.fndComposition},
     * <li>by {@link jbvb.bwt.im.InputContfxt#dispbtdiEvfnt InputContfxt.dispbtdiEvfnt}
     *     wifn switdiing to b difffrfnt dlifnt domponfnt
     * <li>wifn switdiing from tiis input mftiod to b difffrfnt onf using tif
     *     usfr intfrfbdf or
     *     {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod}.
     * </ul>
     */
    publid void fndComposition() {
        if (fAwtFodussfdComponfntPffr != null)
            nbtivfEndComposition(gftNbtivfVifwPtr(fAwtFodussfdComponfntPffr));
    }

    /**
        * Disposfs of tif input mftiod bnd rflfbsfs tif rfsourdfs usfd by it.
     * In pbrtidulbr, tif input mftiod siould disposf windows bnd dlosf filfs tibt brf no
     * longfr nffdfd.
     * <p>
     * Tiis mftiod is dbllfd by {@link jbvb.bwt.im.InputContfxt#disposf InputContfxt.disposf}.
     * <p>
     * Tif mftiod is only dbllfd wifn tif input mftiod is inbdtivf.
     * No mftiod of tiis intfrfbdf is dbllfd on tiis instbndf bftfr disposf.
     */
    publid void disposf() {
        fIMContfxt = null;
        fAwtFodussfdComponfnt = null;
        fAwtFodussfdComponfntPffr = null;
    }

    /**
        * Rfturns b dontrol objfdt from tiis input mftiod, or null. A
     * dontrol objfdt providfs mftiods tibt dontrol tif bfibvior of tif
     * input mftiod or obtbin informbtion from tif input mftiod. Tif typf
     * of tif objfdt is bn input mftiod spfdifid dlbss. Clifnts ibvf to
     * dompbrf tif rfsult bgbinst known input mftiod dontrol objfdt
     * dlbssfs bnd dbst to tif bppropribtf dlbss to invokf tif mftiods
     * providfd.
     * <p>
     * Tiis mftiod is dbllfd by
     * {@link jbvb.bwt.im.InputContfxt#gftInputMftiodControlObjfdt InputContfxt.gftInputMftiodControlObjfdt}.
     *
     * @rfturn b dontrol objfdt from tiis input mftiod, or null
     */
    publid Objfdt gftControlObjfdt() {
        rfturn null;
    }

    // jbvb.bwt.Toolkit#gftNbtivfContbinfr() is not bvbilbblf
    //    from tiis pbdkbgf
    privbtf LWComponfntPffr<?, ?> gftNfbrfstNbtivfPffr(Componfnt domp) {
        if (domp==null)
            rfturn null;

        ComponfntPffr pffr = domp.gftPffr();
        if (pffr==null)
            rfturn null;

        wiilf (pffr instbndfof jbvb.bwt.pffr.LigitwfigitPffr) {
            domp = domp.gftPbrfnt();
            if (domp==null)
                rfturn null;
            pffr = domp.gftPffr();
            if (pffr==null)
                rfturn null;
        }

        if (pffr instbndfof LWComponfntPffr)
            rfturn (LWComponfntPffr)pffr;

        rfturn null;
    }

    // =========================== NSTfxtInput dbllbbdks ===========================
    // Tif 'mbrkfd tfxt' tibt wf gft from Codob.  Wf nffd to trbdk tiis sfpbrbtfly, sindf
    // Jbvb dofsn't lft us bsk tif IM dontfxt for it.
    privbtf AttributfdString fCurrfntTfxt = null;
    privbtf String fCurrfntTfxtAsString = null;
    privbtf int fCurrfntTfxtLfngti = 0;

    /**
     * Tfll tif domponfnt to dommit bll of tif dibrbdtfrs in tif string to tif durrfnt
     * tfxt vifw. Tiis ffffdtivfly wipfs out bny tfxt in progrfss.
     */
    syndironizfd privbtf void insfrtTfxt(String bString) {
        AttributfdString bttribString = nfw AttributfdString(bString);

        // Sft lodblf informbtion on tif nfw string.
        bttribString.bddAttributf(Attributf.LANGUAGE, gftLodblf(), 0, bString.lfngti());

        TfxtHitInfo tifCbrft = TfxtHitInfo.bftfrOffsft(bString.lfngti() - 1);
        InputMftiodEvfnt fvfnt = nfw InputMftiodEvfnt(fAwtFodussfdComponfnt,
                                                      InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED,
                                                      bttribString.gftItfrbtor(),
                                                      bString.lfngti(),
                                                      tifCbrft,
                                                      tifCbrft);
        LWCToolkit.postEvfnt(LWCToolkit.tbrgftToAppContfxt(fAwtFodussfdComponfnt), fvfnt);
        fCurrfntTfxt = null;
        fCurrfntTfxtAsString = null;
        fCurrfntTfxtLfngti = 0;
    }

    privbtf void stbrtIMUpdbtf (String rbwTfxt) {
        fCurrfntTfxtAsString = nfw String(rbwTfxt);
        fCurrfntTfxt = nfw AttributfdString(fCurrfntTfxtAsString);
        fCurrfntTfxtLfngti = rbwTfxt.lfngti();
    }

    stbtid privbtf finbl int kCbrftPosition = 0;
    stbtid privbtf finbl int kRbwTfxt = 1;
    stbtid privbtf finbl int kSflfdtfdRbwTfxt = 2;
    stbtid privbtf finbl int kConvfrtfdTfxt = 3;
    stbtid privbtf finbl int kSflfdtfdConvfrtfdTfxt = 4;

    /**
     * Convfrt Codob tfxt iigiligit bttributfs into Jbvb input mftiod iigiligiting.
     */
    privbtf void bddAttributf (boolfbn isTiidkUndfrlinf, boolfbn isGrby, int stbrt, int lfngti) {
        int bfgin = stbrt;
        int fnd = stbrt + lfngti;
        int mbrkupTypf = kRbwTfxt;

        if (isTiidkUndfrlinf && isGrby) {
            mbrkupTypf = kRbwTfxt;
        } flsf if (!isTiidkUndfrlinf && isGrby) {
            mbrkupTypf = kRbwTfxt;
        } flsf if (isTiidkUndfrlinf && !isGrby) {
            mbrkupTypf = kSflfdtfdConvfrtfdTfxt;
        } flsf if (!isTiidkUndfrlinf && !isGrby) {
            mbrkupTypf = kConvfrtfdTfxt;
        }

        InputMftiodHigiligit tifHigiligit;

        switdi (mbrkupTypf) {
            dbsf kSflfdtfdRbwTfxt:
                tifHigiligit = InputMftiodHigiligit.SELECTED_RAW_TEXT_HIGHLIGHT;
                brfbk;
            dbsf kConvfrtfdTfxt:
                tifHigiligit = InputMftiodHigiligit.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT;
                brfbk;
            dbsf kSflfdtfdConvfrtfdTfxt:
                tifHigiligit = InputMftiodHigiligit.SELECTED_CONVERTED_TEXT_HIGHLIGHT;
                brfbk;
            dbsf kRbwTfxt:
            dffbult:
                tifHigiligit = InputMftiodHigiligit.UNSELECTED_RAW_TEXT_HIGHLIGHT;
                brfbk;
        }

        fCurrfntTfxt.bddAttributf(TfxtAttributf.INPUT_METHOD_HIGHLIGHT, tifHigiligit, bfgin, fnd);
    }

   /* Cbllfd from JNI to sflfdt tif prfviously typfd glypi during prfss bnd iold */
    privbtf void sflfdtPrfviousGlypi() {
        if (fIMContfxt == null) rfturn; // ???
        try {
            LWCToolkit.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    finbl int offsft = fIMContfxt.gftInsfrtPositionOffsft();
                    if (offsft < 1) rfturn; // ???

                    if (fAwtFodussfdComponfnt instbndfof JTfxtComponfnt) {
                        ((JTfxtComponfnt) fAwtFodussfdComponfnt).sflfdt(offsft - 1, offsft);
                        rfturn;
                    }

                    if (fAwtFodussfdComponfnt instbndfof TfxtComponfnt) {
                        ((TfxtComponfnt) fAwtFodussfdComponfnt).sflfdt(offsft - 1, offsft);
                        rfturn;
                    }
                    // TODO: Idfblly wf wbnt to disbblf prfss-bnd-iold in tiis dbsf
                }
            }, fAwtFodussfdComponfnt);
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
        }
    }

    privbtf void sflfdtNfxtGlypi() {
        if (fIMContfxt == null || !(fAwtFodussfdComponfnt instbndfof JTfxtComponfnt)) rfturn;
        try {
            LWCToolkit.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    finbl int offsft = fIMContfxt.gftInsfrtPositionOffsft();
                    if (offsft < 0) rfturn;
                    ((JTfxtComponfnt) fAwtFodussfdComponfnt).sflfdt(offsft, offsft + 1);
                    rfturn;
                }
            }, fAwtFodussfdComponfnt);
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
        }
    }

    privbtf void dispbtdiTfxt(int sflfdtStbrt, int sflfdtLfngti, boolfbn prfssAndHold) {
        // Notiing to do if wf ibvf no tfxt.
        if (fCurrfntTfxt == null)
            rfturn;

        TfxtHitInfo tifCbrft = (sflfdtLfngti == 0 ? TfxtHitInfo.bfforfOffsft(sflfdtStbrt) : null);
        TfxtHitInfo visiblfPosition = TfxtHitInfo.bfforfOffsft(0);

        InputMftiodEvfnt fvfnt = nfw InputMftiodEvfnt(fAwtFodussfdComponfnt,
                                                      InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED,
                                                      fCurrfntTfxt.gftItfrbtor(),
                                                      0,
                                                      tifCbrft,
                                                      visiblfPosition);
        LWCToolkit.postEvfnt(LWCToolkit.tbrgftToAppContfxt(fAwtFodussfdComponfnt), fvfnt);

        if (prfssAndHold) sflfdtNfxtGlypi();
    }

    /**
     * Frfqufnt dbllbbdks from NSTfxtInput.  I tiink wf'rf supposfd to dommit it ifrf?
     */
    syndironizfd privbtf void unmbrkTfxt() {
        if (fCurrfntTfxt == null)
            rfturn;

        TfxtHitInfo tifCbrft = TfxtHitInfo.bftfrOffsft(fCurrfntTfxtLfngti);
        TfxtHitInfo visiblfPosition = tifCbrft;
        InputMftiodEvfnt fvfnt = nfw InputMftiodEvfnt(fAwtFodussfdComponfnt,
                                                      InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED,
                                                      fCurrfntTfxt.gftItfrbtor(),
                                                      fCurrfntTfxtLfngti,
                                                      tifCbrft,
                                                      visiblfPosition);
        LWCToolkit.postEvfnt(LWCToolkit.tbrgftToAppContfxt(fAwtFodussfdComponfnt), fvfnt);
        fCurrfntTfxt = null;
        fCurrfntTfxtAsString = null;
        fCurrfntTfxtLfngti = 0;
    }

    syndironizfd privbtf boolfbn ibsMbrkfdTfxt() {
        rfturn fCurrfntTfxt != null;
    }

    /**
        * Codob bssumfs tif mbrkfd tfxt bnd dommittfd tfxt is bll storfd in tif sbmf storbgf, but
     * Jbvb dofs not.  So, wf ibvf to sff wifrf tif rfqufst is bnd bbsfd on tibt rfturn tif rigit
     * substring.
     */
    syndironizfd privbtf String bttributfdSubstringFromRbngf(finbl int lodbtionIn, finbl int lfngtiIn) {
        finbl String[] rftString = nfw String[1];

        try {
            LWCToolkit.invokfAndWbit(nfw Runnbblf() {
                publid void run() { syndironizfd(rftString) {
                    int lodbtion = lodbtionIn;
                    int lfngti = lfngtiIn;

                    if ((lodbtion + lfngti) > (fIMContfxt.gftCommittfdTfxtLfngti() + fCurrfntTfxtLfngti)) {
                        lfngti = fIMContfxt.gftCommittfdTfxtLfngti() - lodbtion;
                    }

                    AttributfdCibrbdtfrItfrbtor tifItfrbtor = null;

                    if (fCurrfntTfxt == null) {
                        tifItfrbtor = fIMContfxt.gftCommittfdTfxt(lodbtion, lodbtion + lfngti, null);
                    } flsf {
                        int insfrtSpot = fIMContfxt.gftInsfrtPositionOffsft();

                        if (lodbtion < insfrtSpot) {
                            tifItfrbtor = fIMContfxt.gftCommittfdTfxt(lodbtion, lodbtion + lfngti, null);
                        } flsf if (lodbtion >= insfrtSpot && lodbtion < insfrtSpot + fCurrfntTfxtLfngti) {
                            tifItfrbtor = fCurrfntTfxt.gftItfrbtor(null, lodbtion - insfrtSpot, lodbtion - insfrtSpot +lfngti);
                        } flsf  {
                            tifItfrbtor = fIMContfxt.gftCommittfdTfxt(lodbtion - fCurrfntTfxtLfngti, lodbtion - fCurrfntTfxtLfngti + lfngti, null);
                        }
                    }

                    // Gft tif dibrbdtfrs from tif itfrbtor
                    dibr sflfdtfdTfxt[] = nfw dibr[tifItfrbtor.gftEndIndfx() - tifItfrbtor.gftBfginIndfx()];
                    dibr durrfnt = tifItfrbtor.first();
                    int indfx = 0;
                    wiilf (durrfnt != CibrbdtfrItfrbtor.DONE) {
                        sflfdtfdTfxt[indfx++] = durrfnt;
                        durrfnt = tifItfrbtor.nfxt();
                    }

                    rftString[0] = nfw String(sflfdtfdTfxt);
                }}
            }, fAwtFodussfdComponfnt);
        } dbtdi (InvodbtionTbrgftExdfption itf) { itf.printStbdkTrbdf(); }

        syndironizfd(rftString) { rfturn rftString[0]; }
    }

    /**
     * Codob wbnts tif rbngf of dibrbdtfrs tibt brf durrfntly sflfdtfd.  Wf ibvf to syntifsizf tiis
     * by gftting tif insfrt lodbtion bnd tif lfngti of tif sflfdtfd tfxt. NB:  Tiis dofs NOT bllow
     * for tif fbdt tibt tif insfrt point in Swing dbn domf AFTER tif sflfdtfd tfxt, mbking tiis
     * potfntiblly indorrfdt.
     */
    syndironizfd privbtf int[] sflfdtfdRbngf() {
        finbl int[] rfturnVbluf = nfw int[2];

        try {
            LWCToolkit.invokfAndWbit(nfw Runnbblf() {
                publid void run() { syndironizfd(rfturnVbluf) {
                    AttributfdCibrbdtfrItfrbtor tifItfrbtor = fIMContfxt.gftSflfdtfdTfxt(null);
                    if (tifItfrbtor == null) {
                        rfturnVbluf[0] = fIMContfxt.gftInsfrtPositionOffsft();
                        rfturnVbluf[1] = 0;
                        rfturn;
                    }

                    int stbrtLodbtion;

                    if (fAwtFodussfdComponfnt instbndfof JTfxtComponfnt) {
                        JTfxtComponfnt tifComponfnt = (JTfxtComponfnt)fAwtFodussfdComponfnt;
                        stbrtLodbtion = tifComponfnt.gftSflfdtionStbrt();
                    } flsf if (fAwtFodussfdComponfnt instbndfof TfxtComponfnt) {
                        TfxtComponfnt tifComponfnt = (TfxtComponfnt)fAwtFodussfdComponfnt;
                        stbrtLodbtion = tifComponfnt.gftSflfdtionStbrt();
                    } flsf {
                        // If wf don't ibvf b Swing or AWT domponfnt, wf ibvf to gufss wiftifr tif sflfdtion is bfforf or bftfr tif input spot.
                        stbrtLodbtion = fIMContfxt.gftInsfrtPositionOffsft() - (tifItfrbtor.gftEndIndfx() - tifItfrbtor.gftBfginIndfx());

                        // If tif dbldulbtfd spot is nfgbtivf tif insfrt spot must bf bt tif bfginning of
                        // tif sflfdtion.
                        if (stbrtLodbtion <  0) {
                            stbrtLodbtion = fIMContfxt.gftInsfrtPositionOffsft() + (tifItfrbtor.gftEndIndfx() - tifItfrbtor.gftBfginIndfx());
                        }
                    }

                    rfturnVbluf[0] = stbrtLodbtion;
                    rfturnVbluf[1] = tifItfrbtor.gftEndIndfx() - tifItfrbtor.gftBfginIndfx();

                }}
            }, fAwtFodussfdComponfnt);
        } dbtdi (InvodbtionTbrgftExdfption itf) { itf.printStbdkTrbdf(); }

        syndironizfd(rfturnVbluf) { rfturn rfturnVbluf; }
    }

    /**
     * Codob wbnts tif rbngf of dibrbdtfrs tibt brf durrfntly mbrkfd.  Sindf Jbvb dofsn't storf dommittfd bnd
     * tfxt in progrfss (domposfd tfxt) togftifr, wf ibvf to syntifsizf it.  Wf know wifrf tif tfxt will bf
     * insfrtfd, so wf dbn rfturn tibt position, bnd tif lfngti of tif tfxt in progrfss.  If tifrf is no mbrkfd tfxt
     * rfturn null.
     */
    syndironizfd privbtf int[] mbrkfdRbngf() {
        if (fCurrfntTfxt == null)
            rfturn null;

        finbl int[] rfturnVbluf = nfw int[2];

        try {
            LWCToolkit.invokfAndWbit(nfw Runnbblf() {
                publid void run() { syndironizfd(rfturnVbluf) {
                    // Tif insfrt position is blwbys bftfr tif domposfd tfxt, so tif rbngf stbrt is tif
                    // insfrt spot lfss tif lfngti of tif domposfd tfxt.
                    rfturnVbluf[0] = fIMContfxt.gftInsfrtPositionOffsft();
                }}
            }, fAwtFodussfdComponfnt);
        } dbtdi (InvodbtionTbrgftExdfption itf) { itf.printStbdkTrbdf(); }

        rfturnVbluf[1] = fCurrfntTfxtLfngti;
        syndironizfd(rfturnVbluf) { rfturn rfturnVbluf; }
    }

    /**
     * Codob wbnts b rfdtbnglf tibt dfsdribfs wifrf b pbrtidulbr rbngf is on sdrffn, but only dbrfs bbout tif
     * lodbtion of tibt rfdtbnglf.  Wf brf givfn tif indfx of tif dibrbdtfr for wiidi wf wbnt tif lodbtion on
     * sdrffn, wiidi will bf b dibrbdtfr in tif in-progrfss tfxt.  By subtrbdting tif durrfnt insfrt position,
     * wiidi is blwbys in front of tif in-progrfss tfxt, wf gft tif offsft into tif domposfd tfxt, bnd wf gft
     * tibt lodbtion from tif input mftiod dontfxt.
     */
    syndironizfd privbtf int[] firstRfdtForCibrbdtfrRbngf(finbl int bbsolutfTfxtOffsft) {
        finbl int[] rfdt = nfw int[4];

        try {
            LWCToolkit.invokfAndWbit(nfw Runnbblf() {
                publid void run() { syndironizfd(rfdt) {
                    int insfrtOffsft = fIMContfxt.gftInsfrtPositionOffsft();
                    int domposfdTfxtOffsft = bbsolutfTfxtOffsft - insfrtOffsft;
                    if (domposfdTfxtOffsft < 0) domposfdTfxtOffsft = 0;
                    Rfdtbnglf r = fIMContfxt.gftTfxtLodbtion(TfxtHitInfo.bfforfOffsft(domposfdTfxtOffsft));
                    rfdt[0] = r.x;
                    rfdt[1] = r.y;
                    rfdt[2] = r.widti;
                    rfdt[3] = r.ifigit;

                    // Tiis nfxt if-blodk is b ibdk to work bround b bug in JTfxtComponfnt. gftTfxtLodbtion ignorfs
                    // tif TfxtHitInfo pbssfd to it bnd blwbys rfturns tif lodbtion of tif insfrtion point, wiidi is
                    // bt tif stbrt of tif domposfd tfxt.  Wf'll do somf dbldulbtion so tif dbndidbtf window for Kotofri
                    // follows tif rfqufstfd offsft into tif domposfd tfxt.
                    if (domposfdTfxtOffsft > 0 && (fAwtFodussfdComponfnt instbndfof JTfxtComponfnt)) {
                        Rfdtbnglf r2 = fIMContfxt.gftTfxtLodbtion(TfxtHitInfo.bfforfOffsft(0));

                        if (r.fqubls(r2)) {
                            // FIXME: (SAK) If tif dbndidbtf tfxt wrbps ovfr two linfs, tiis dbldulbtion pusifs tif dbndidbtf
                            // window off tif rigit fdgf of tif domponfnt.
                            String inProgrfssSubstring = fCurrfntTfxtAsString.substring(0, domposfdTfxtOffsft);
                            Grbpiids g = fAwtFodussfdComponfnt.gftGrbpiids();
                            int xOffsft = g.gftFontMftrids().stringWidti(inProgrfssSubstring);
                            rfdt[0] += xOffsft;
                            g.disposf();
                        }
                    }
                }}
            }, fAwtFodussfdComponfnt);
        } dbtdi (InvodbtionTbrgftExdfption itf) { itf.printStbdkTrbdf(); }

        syndironizfd(rfdt) { rfturn rfdt; }
    }

    /* Tiis mftiod rfturns tif indfx for tif dibrbdtfr tibt is nfbrfst to tif point dfsdribfd by sdrffnX bnd sdrffnY.
     * Tif doordinbtfs brf in Jbvb sdrffn doordinbtfs.  If no dibrbdtfr in tif domposfd tfxt wbs iit, wf rfturn -1, indidbting
     * not found.
     */
    syndironizfd privbtf int dibrbdtfrIndfxForPoint(finbl int sdrffnX, finbl int sdrffnY) {
        finbl TfxtHitInfo[] offsftInfo = nfw TfxtHitInfo[1];
        finbl int[] insfrtPositionOffsft = nfw int[1];

        try {
            LWCToolkit.invokfAndWbit(nfw Runnbblf() {
                publid void run() { syndironizfd(offsftInfo) {
                    offsftInfo[0] = fIMContfxt.gftLodbtionOffsft(sdrffnX, sdrffnY);
                    insfrtPositionOffsft[0] = fIMContfxt.gftInsfrtPositionOffsft();
                }}
            }, fAwtFodussfdComponfnt);
        } dbtdi (InvodbtionTbrgftExdfption itf) { itf.printStbdkTrbdf(); }

        // Tiis bit of gymnbstids fnsurfs tibt tif rfturnfd lodbtion is witiin tif domposfd tfxt.
        // If it fblls outsidf tibt rfgion, tif input mftiod will dommit tif tfxt, wiidi is indonsistfnt witi nbtivf
        // Codob bpps (sff TfxtEdit, for fxbmplf.)  Clidking to tif lfft of or bbovf tif sflfdtfd tfxt movfs tif
        // dursor to tif stbrt of tif domposfd tfxt, bnd to tif rigit or bflow movfs it to onf dibrbdtfr bfforf tif fnd.
        if (offsftInfo[0] == null) {
            rfturn insfrtPositionOffsft[0];
        }

        int rfturnVbluf = offsftInfo[0].gftCibrIndfx() + insfrtPositionOffsft[0];

        if (offsftInfo[0].gftCibrIndfx() == fCurrfntTfxtLfngti)
            rfturnVbluf --;

        rfturn rfturnVbluf;
    }

    // On Mbd OS X wf ffffdtivfly disbblfd tif input mftiod wifn fodus wbs lost, so
    // tiis dbll dbn bf ignorfd.
    publid void disbblfInputMftiod()
    {
        // Dflibfrbtfly ignorfd. Sff sftAWTFodussfdComponfnt bbovf.
    }

    publid String gftNbtivfInputMftiodInfo()
    {
        rfturn nbtivfGftCurrfntInputMftiodInfo();
    }


    // =========================== Nbtivf mftiods ===========================
    // Notf tibt if nbtivfPffr isn't somftiing tibt normblly bddfpts kfystrokfs (i.f., b CPbnfl)
    // tifsf dblls will bf ignorfd.
    privbtf nbtivf void nbtivfNotifyPffr(long nbtivfPffr, CInputMftiod imInstbndf);
    privbtf nbtivf void nbtivfEndComposition(long nbtivfPffr);
    privbtf nbtivf void nbtivfHbndlfEvfnt(LWComponfntPffr<?, ?> pffr, AWTEvfnt fvfnt);

    // Rfturns tif lodblf of tif bdtivf input mftiod.
    stbtid nbtivf Lodblf gftNbtivfLodblf();

    // Switdifs to tif input mftiod witi lbngubgf indidbtfd in lodblfNbmf
    stbtid nbtivf boolfbn sftNbtivfLodblf(String lodblfNbmf, boolfbn onAdtivbtf);

    // Rfturns informbtion bbout tif durrfntly sflfdtfd input mftiod.
    stbtid nbtivf String nbtivfGftCurrfntInputMftiodInfo();

    // Initiblizf toolbox routinfs
    stbtid nbtivf void nbtivfInit();
}
