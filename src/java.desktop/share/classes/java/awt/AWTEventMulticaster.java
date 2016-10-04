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
pbdkbgf jbvb.bwt;

import jbvb.bwt.fvfnt.*;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.util.EvfntListfnfr;
import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.EvfntListfnfr;


/**
 * {@dodf AWTEvfntMultidbstfr} implfmfnts fffidifnt bnd tirfbd-sbff multi-dbst
 * fvfnt dispbtdiing for tif AWT fvfnts dffinfd in tif {@dodf jbvb.bwt.fvfnt}
 * pbdkbgf.
 * <p>
 * Tif following fxbmplf illustrbtfs iow to usf tiis dlbss:
 *
 * <prf><dodf>
 * publid myComponfnt fxtfnds Componfnt {
 *     AdtionListfnfr bdtionListfnfr = null;
 *
 *     publid syndironizfd void bddAdtionListfnfr(AdtionListfnfr l) {
 *         bdtionListfnfr = AWTEvfntMultidbstfr.bdd(bdtionListfnfr, l);
 *     }
 *     publid syndironizfd void rfmovfAdtionListfnfr(AdtionListfnfr l) {
 *         bdtionListfnfr = AWTEvfntMultidbstfr.rfmovf(bdtionListfnfr, l);
 *     }
 *     publid void prodfssEvfnt(AWTEvfnt f) {
 *         // wifn fvfnt oddurs wiidi dbusfs "bdtion" sfmbntid
 *         AdtionListfnfr listfnfr = bdtionListfnfr;
 *         if (listfnfr != null) {
 *             listfnfr.bdtionPfrformfd(nfw AdtionEvfnt());
 *         }
 *     }
 * }
 * </dodf></prf>
 * Tif importbnt point to notf is tif first brgumfnt to tif {@dodf
 * bdd} bnd {@dodf rfmovf} mftiods is tif fifld mbintbining tif
 * listfnfrs. In bddition you must bssign tif rfsult of tif {@dodf bdd}
 * bnd {@dodf rfmovf} mftiods to tif fifld mbintbining tif listfnfrs.
 * <p>
 * {@dodf AWTEvfntMultidbstfr} is implfmfntfd bs b pbir of {@dodf
 * EvfntListfnfrs} tibt brf sft bt donstrudtion timf. {@dodf
 * AWTEvfntMultidbstfr} is immutbblf. Tif {@dodf bdd} bnd {@dodf
 * rfmovf} mftiods do not bltfr {@dodf AWTEvfntMultidbstfr} in
 * bnywby. If nfdfssbry, b nfw {@dodf AWTEvfntMultidbstfr} is
 * drfbtfd. In tiis wby it is sbff to bdd bnd rfmovf listfnfrs during
 * tif prodfss of bn fvfnt dispbtdiing.  Howfvfr, fvfnt listfnfrs
 * bddfd during tif prodfss of bn fvfnt dispbtdi opfrbtion brf not
 * notififd of tif fvfnt durrfntly bfing dispbtdifd.
 * <p>
 * All of tif {@dodf bdd} mftiods bllow {@dodf null} brgumfnts. If tif
 * first brgumfnt is {@dodf null}, tif sfdond brgumfnt is rfturnfd. If
 * tif first brgumfnt is not {@dodf null} bnd tif sfdond brgumfnt is
 * {@dodf null}, tif first brgumfnt is rfturnfd. If boti brgumfnts brf
 * {@dodf non-null}, b nfw {@dodf AWTEvfntMultidbstfr} is drfbtfd using
 * tif two brgumfnts bnd rfturnfd.
 * <p>
 * For tif {@dodf rfmovf} mftiods tibt tbkf two brgumfnts, tif following is
 * rfturnfd:
 * <ul>
 *   <li>{@dodf null}, if tif first brgumfnt is {@dodf null}, or
 *       tif brgumfnts brf fqubl, by wby of {@dodf ==}.
 *   <li>tif first brgumfnt, if tif first brgumfnt is not bn instbndf of
 *       {@dodf AWTEvfntMultidbstfr}.
 *   <li>rfsult of invoking {@dodf rfmovf(EvfntListfnfr)} on tif
 *       first brgumfnt, supplying tif sfdond brgumfnt to tif
 *       {@dodf rfmovf(EvfntListfnfr)} mftiod.
 * </ul>
 * <p>Swing mbkfs usf of
 * {@link jbvbx.swing.fvfnt.EvfntListfnfrList EvfntListfnfrList} for
 * similbr logid. Rfffr to it for dftbils.
 *
 * @sff jbvbx.swing.fvfnt.EvfntListfnfrList
 *
 * @butior      Join Rosf
 * @butior      Amy Fowlfr
 * @sindf       1.1
 */

publid dlbss AWTEvfntMultidbstfr implfmfnts
    ComponfntListfnfr, ContbinfrListfnfr, FodusListfnfr, KfyListfnfr,
    MousfListfnfr, MousfMotionListfnfr, WindowListfnfr, WindowFodusListfnfr,
    WindowStbtfListfnfr, AdtionListfnfr, ItfmListfnfr, AdjustmfntListfnfr,
    TfxtListfnfr, InputMftiodListfnfr, HifrbrdiyListfnfr,
    HifrbrdiyBoundsListfnfr, MousfWifflListfnfr {

    /**
     * A vbribblf in tif fvfnt dibin (listfnfr-b)
     */
    protfdtfd finbl EvfntListfnfr b;

    /**
     * A vbribblf in tif fvfnt dibin (listfnfr-b)
     */
    protfdtfd finbl EvfntListfnfr b;

    /**
     * Crfbtfs bn fvfnt multidbstfr instbndf wiidi dibins listfnfr-b
     * witi listfnfr-b. Input pbrbmftfrs <dodf>b</dodf> bnd <dodf>b</dodf>
     * siould not bf <dodf>null</dodf>, tiougi implfmfntbtions mby vbry in
     * dioosing wiftifr or not to tirow <dodf>NullPointfrExdfption</dodf>
     * in tibt dbsf.
     * @pbrbm b listfnfr-b
     * @pbrbm b listfnfr-b
     */
    protfdtfd AWTEvfntMultidbstfr(EvfntListfnfr b, EvfntListfnfr b) {
        tiis.b = b; tiis.b = b;
    }

    /**
     * Rfmovfs b listfnfr from tiis multidbstfr.
     * <p>
     * Tif rfturnfd multidbstfr dontbins bll tif listfnfrs in tiis
     * multidbstfr witi tif fxdfption of bll oddurrfndfs of {@dodf oldl}.
     * If tif rfsulting multidbstfr dontbins only onf rfgulbr listfnfr
     * tif rfgulbr listfnfr mby bf rfturnfd.  If tif rfsulting multidbstfr
     * is fmpty, tifn {@dodf null} mby bf rfturnfd instfbd.
     * <p>
     * No fxdfption is tirown if {@dodf oldl} is {@dodf null}.
     *
     * @pbrbm oldl tif listfnfr to bf rfmovfd
     * @rfturn rfsulting listfnfr
     */
    protfdtfd EvfntListfnfr rfmovf(EvfntListfnfr oldl) {
        if (oldl == b)  rfturn b;
        if (oldl == b)  rfturn b;
        EvfntListfnfr b2 = rfmovfIntfrnbl(b, oldl);
        EvfntListfnfr b2 = rfmovfIntfrnbl(b, oldl);
        if (b2 == b && b2 == b) {
            rfturn tiis;        // it's not ifrf
        }
        rfturn bddIntfrnbl(b2, b2);
    }

    /**
     * Hbndlfs tif domponfntRfsizfd fvfnt by invoking tif
     * domponfntRfsizfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif domponfnt fvfnt
     */
    publid void domponfntRfsizfd(ComponfntEvfnt f) {
        ((ComponfntListfnfr)b).domponfntRfsizfd(f);
        ((ComponfntListfnfr)b).domponfntRfsizfd(f);
    }

    /**
     * Hbndlfs tif domponfntMovfd fvfnt by invoking tif
     * domponfntMovfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif domponfnt fvfnt
     */
    publid void domponfntMovfd(ComponfntEvfnt f) {
        ((ComponfntListfnfr)b).domponfntMovfd(f);
        ((ComponfntListfnfr)b).domponfntMovfd(f);
    }

    /**
     * Hbndlfs tif domponfntSiown fvfnt by invoking tif
     * domponfntSiown mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif domponfnt fvfnt
     */
    publid void domponfntSiown(ComponfntEvfnt f) {
        ((ComponfntListfnfr)b).domponfntSiown(f);
        ((ComponfntListfnfr)b).domponfntSiown(f);
    }

    /**
     * Hbndlfs tif domponfntHiddfn fvfnt by invoking tif
     * domponfntHiddfn mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif domponfnt fvfnt
     */
    publid void domponfntHiddfn(ComponfntEvfnt f) {
        ((ComponfntListfnfr)b).domponfntHiddfn(f);
        ((ComponfntListfnfr)b).domponfntHiddfn(f);
    }

    /**
     * Hbndlfs tif domponfntAddfd dontbinfr fvfnt by invoking tif
     * domponfntAddfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif domponfnt fvfnt
     */
    publid void domponfntAddfd(ContbinfrEvfnt f) {
        ((ContbinfrListfnfr)b).domponfntAddfd(f);
        ((ContbinfrListfnfr)b).domponfntAddfd(f);
    }

    /**
     * Hbndlfs tif domponfntRfmovfd dontbinfr fvfnt by invoking tif
     * domponfntRfmovfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif domponfnt fvfnt
     */
    publid void domponfntRfmovfd(ContbinfrEvfnt f) {
        ((ContbinfrListfnfr)b).domponfntRfmovfd(f);
        ((ContbinfrListfnfr)b).domponfntRfmovfd(f);
    }

    /**
     * Hbndlfs tif fodusGbinfd fvfnt by invoking tif
     * fodusGbinfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif fodus fvfnt
     */
    publid void fodusGbinfd(FodusEvfnt f) {
        ((FodusListfnfr)b).fodusGbinfd(f);
        ((FodusListfnfr)b).fodusGbinfd(f);
    }

    /**
     * Hbndlfs tif fodusLost fvfnt by invoking tif
     * fodusLost mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif fodus fvfnt
     */
    publid void fodusLost(FodusEvfnt f) {
        ((FodusListfnfr)b).fodusLost(f);
        ((FodusListfnfr)b).fodusLost(f);
    }

    /**
     * Hbndlfs tif kfyTypfd fvfnt by invoking tif
     * kfyTypfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif kfy fvfnt
     */
    publid void kfyTypfd(KfyEvfnt f) {
        ((KfyListfnfr)b).kfyTypfd(f);
        ((KfyListfnfr)b).kfyTypfd(f);
    }

    /**
     * Hbndlfs tif kfyPrfssfd fvfnt by invoking tif
     * kfyPrfssfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif kfy fvfnt
     */
    publid void kfyPrfssfd(KfyEvfnt f) {
        ((KfyListfnfr)b).kfyPrfssfd(f);
        ((KfyListfnfr)b).kfyPrfssfd(f);
    }

    /**
     * Hbndlfs tif kfyRflfbsfd fvfnt by invoking tif
     * kfyRflfbsfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif kfy fvfnt
     */
    publid void kfyRflfbsfd(KfyEvfnt f) {
        ((KfyListfnfr)b).kfyRflfbsfd(f);
        ((KfyListfnfr)b).kfyRflfbsfd(f);
    }

    /**
     * Hbndlfs tif mousfClidkfd fvfnt by invoking tif
     * mousfClidkfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     */
    publid void mousfClidkfd(MousfEvfnt f) {
        ((MousfListfnfr)b).mousfClidkfd(f);
        ((MousfListfnfr)b).mousfClidkfd(f);
    }

    /**
     * Hbndlfs tif mousfPrfssfd fvfnt by invoking tif
     * mousfPrfssfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     */
    publid void mousfPrfssfd(MousfEvfnt f) {
        ((MousfListfnfr)b).mousfPrfssfd(f);
        ((MousfListfnfr)b).mousfPrfssfd(f);
    }

    /**
     * Hbndlfs tif mousfRflfbsfd fvfnt by invoking tif
     * mousfRflfbsfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     */
    publid void mousfRflfbsfd(MousfEvfnt f) {
        ((MousfListfnfr)b).mousfRflfbsfd(f);
        ((MousfListfnfr)b).mousfRflfbsfd(f);
    }

    /**
     * Hbndlfs tif mousfEntfrfd fvfnt by invoking tif
     * mousfEntfrfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     */
    publid void mousfEntfrfd(MousfEvfnt f) {
        ((MousfListfnfr)b).mousfEntfrfd(f);
        ((MousfListfnfr)b).mousfEntfrfd(f);
    }

    /**
     * Hbndlfs tif mousfExitfd fvfnt by invoking tif
     * mousfExitfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     */
    publid void mousfExitfd(MousfEvfnt f) {
        ((MousfListfnfr)b).mousfExitfd(f);
        ((MousfListfnfr)b).mousfExitfd(f);
    }

    /**
     * Hbndlfs tif mousfDrbggfd fvfnt by invoking tif
     * mousfDrbggfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     */
    publid void mousfDrbggfd(MousfEvfnt f) {
        ((MousfMotionListfnfr)b).mousfDrbggfd(f);
        ((MousfMotionListfnfr)b).mousfDrbggfd(f);
    }

    /**
     * Hbndlfs tif mousfMovfd fvfnt by invoking tif
     * mousfMovfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     */
    publid void mousfMovfd(MousfEvfnt f) {
        ((MousfMotionListfnfr)b).mousfMovfd(f);
        ((MousfMotionListfnfr)b).mousfMovfd(f);
    }

    /**
     * Hbndlfs tif windowOpfnfd fvfnt by invoking tif
     * windowOpfnfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     */
    publid void windowOpfnfd(WindowEvfnt f) {
        ((WindowListfnfr)b).windowOpfnfd(f);
        ((WindowListfnfr)b).windowOpfnfd(f);
    }

    /**
     * Hbndlfs tif windowClosing fvfnt by invoking tif
     * windowClosing mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     */
    publid void windowClosing(WindowEvfnt f) {
        ((WindowListfnfr)b).windowClosing(f);
        ((WindowListfnfr)b).windowClosing(f);
    }

    /**
     * Hbndlfs tif windowClosfd fvfnt by invoking tif
     * windowClosfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     */
    publid void windowClosfd(WindowEvfnt f) {
        ((WindowListfnfr)b).windowClosfd(f);
        ((WindowListfnfr)b).windowClosfd(f);
    }

    /**
     * Hbndlfs tif windowIdonififd fvfnt by invoking tif
     * windowIdonififd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     */
    publid void windowIdonififd(WindowEvfnt f) {
        ((WindowListfnfr)b).windowIdonififd(f);
        ((WindowListfnfr)b).windowIdonififd(f);
    }

    /**
     * Hbndlfs tif windowDfidonfifd fvfnt by invoking tif
     * windowDfidonififd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     */
    publid void windowDfidonififd(WindowEvfnt f) {
        ((WindowListfnfr)b).windowDfidonififd(f);
        ((WindowListfnfr)b).windowDfidonififd(f);
    }

    /**
     * Hbndlfs tif windowAdtivbtfd fvfnt by invoking tif
     * windowAdtivbtfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     */
    publid void windowAdtivbtfd(WindowEvfnt f) {
        ((WindowListfnfr)b).windowAdtivbtfd(f);
        ((WindowListfnfr)b).windowAdtivbtfd(f);
    }

    /**
     * Hbndlfs tif windowDfbdtivbtfd fvfnt by invoking tif
     * windowDfbdtivbtfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     */
    publid void windowDfbdtivbtfd(WindowEvfnt f) {
        ((WindowListfnfr)b).windowDfbdtivbtfd(f);
        ((WindowListfnfr)b).windowDfbdtivbtfd(f);
    }

    /**
     * Hbndlfs tif windowStbtfCibngfd fvfnt by invoking tif
     * windowStbtfCibngfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     * @sindf 1.4
     */
    publid void windowStbtfCibngfd(WindowEvfnt f) {
        ((WindowStbtfListfnfr)b).windowStbtfCibngfd(f);
        ((WindowStbtfListfnfr)b).windowStbtfCibngfd(f);
    }


    /**
     * Hbndlfs tif windowGbinfdFodus fvfnt by invoking tif windowGbinfdFodus
     * mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     * @sindf 1.4
     */
    publid void windowGbinfdFodus(WindowEvfnt f) {
        ((WindowFodusListfnfr)b).windowGbinfdFodus(f);
        ((WindowFodusListfnfr)b).windowGbinfdFodus(f);
    }

    /**
     * Hbndlfs tif windowLostFodus fvfnt by invoking tif windowLostFodus
     * mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif window fvfnt
     * @sindf 1.4
     */
    publid void windowLostFodus(WindowEvfnt f) {
        ((WindowFodusListfnfr)b).windowLostFodus(f);
        ((WindowFodusListfnfr)b).windowLostFodus(f);
    }

    /**
     * Hbndlfs tif bdtionPfrformfd fvfnt by invoking tif
     * bdtionPfrformfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif bdtion fvfnt
     */
    publid void bdtionPfrformfd(AdtionEvfnt f) {
        ((AdtionListfnfr)b).bdtionPfrformfd(f);
        ((AdtionListfnfr)b).bdtionPfrformfd(f);
    }

    /**
     * Hbndlfs tif itfmStbtfCibngfd fvfnt by invoking tif
     * itfmStbtfCibngfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif itfm fvfnt
     */
    publid void itfmStbtfCibngfd(ItfmEvfnt f) {
        ((ItfmListfnfr)b).itfmStbtfCibngfd(f);
        ((ItfmListfnfr)b).itfmStbtfCibngfd(f);
    }

    /**
     * Hbndlfs tif bdjustmfntVblufCibngfd fvfnt by invoking tif
     * bdjustmfntVblufCibngfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif bdjustmfnt fvfnt
     */
    publid void bdjustmfntVblufCibngfd(AdjustmfntEvfnt f) {
        ((AdjustmfntListfnfr)b).bdjustmfntVblufCibngfd(f);
        ((AdjustmfntListfnfr)b).bdjustmfntVblufCibngfd(f);
    }
    publid void tfxtVblufCibngfd(TfxtEvfnt f) {
        ((TfxtListfnfr)b).tfxtVblufCibngfd(f);
        ((TfxtListfnfr)b).tfxtVblufCibngfd(f);
    }

    /**
     * Hbndlfs tif inputMftiodTfxtCibngfd fvfnt by invoking tif
     * inputMftiodTfxtCibngfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif itfm fvfnt
     */
    publid void inputMftiodTfxtCibngfd(InputMftiodEvfnt f) {
       ((InputMftiodListfnfr)b).inputMftiodTfxtCibngfd(f);
       ((InputMftiodListfnfr)b).inputMftiodTfxtCibngfd(f);
    }

    /**
     * Hbndlfs tif dbrftPositionCibngfd fvfnt by invoking tif
     * dbrftPositionCibngfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif itfm fvfnt
     */
    publid void dbrftPositionCibngfd(InputMftiodEvfnt f) {
       ((InputMftiodListfnfr)b).dbrftPositionCibngfd(f);
       ((InputMftiodListfnfr)b).dbrftPositionCibngfd(f);
    }

    /**
     * Hbndlfs tif iifrbrdiyCibngfd fvfnt by invoking tif
     * iifrbrdiyCibngfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif itfm fvfnt
     * @sindf 1.3
     */
    publid void iifrbrdiyCibngfd(HifrbrdiyEvfnt f) {
        ((HifrbrdiyListfnfr)b).iifrbrdiyCibngfd(f);
        ((HifrbrdiyListfnfr)b).iifrbrdiyCibngfd(f);
    }

    /**
     * Hbndlfs tif bndfstorMovfd fvfnt by invoking tif
     * bndfstorMovfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif itfm fvfnt
     * @sindf 1.3
     */
    publid void bndfstorMovfd(HifrbrdiyEvfnt f) {
        ((HifrbrdiyBoundsListfnfr)b).bndfstorMovfd(f);
        ((HifrbrdiyBoundsListfnfr)b).bndfstorMovfd(f);
    }

    /**
     * Hbndlfs tif bndfstorRfsizfd fvfnt by invoking tif
     * bndfstorRfsizfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif itfm fvfnt
     * @sindf 1.3
     */
    publid void bndfstorRfsizfd(HifrbrdiyEvfnt f) {
        ((HifrbrdiyBoundsListfnfr)b).bndfstorRfsizfd(f);
        ((HifrbrdiyBoundsListfnfr)b).bndfstorRfsizfd(f);
    }

    /**
     * Hbndlfs tif mousfWifflMovfd fvfnt by invoking tif
     * mousfWifflMovfd mftiods on listfnfr-b bnd listfnfr-b.
     * @pbrbm f tif mousf fvfnt
     * @sindf 1.4
     */
    publid void mousfWifflMovfd(MousfWifflEvfnt f) {
        ((MousfWifflListfnfr)b).mousfWifflMovfd(f);
        ((MousfWifflListfnfr)b).mousfWifflMovfd(f);
    }

    /**
     * Adds domponfnt-listfnfr-b witi domponfnt-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b domponfnt-listfnfr-b
     * @pbrbm b domponfnt-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid ComponfntListfnfr bdd(ComponfntListfnfr b, ComponfntListfnfr b) {
        rfturn (ComponfntListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds dontbinfr-listfnfr-b witi dontbinfr-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b dontbinfr-listfnfr-b
     * @pbrbm b dontbinfr-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid ContbinfrListfnfr bdd(ContbinfrListfnfr b, ContbinfrListfnfr b) {
        rfturn (ContbinfrListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds fodus-listfnfr-b witi fodus-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b fodus-listfnfr-b
     * @pbrbm b fodus-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid FodusListfnfr bdd(FodusListfnfr b, FodusListfnfr b) {
        rfturn (FodusListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds kfy-listfnfr-b witi kfy-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b kfy-listfnfr-b
     * @pbrbm b kfy-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid KfyListfnfr bdd(KfyListfnfr b, KfyListfnfr b) {
        rfturn (KfyListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds mousf-listfnfr-b witi mousf-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b mousf-listfnfr-b
     * @pbrbm b mousf-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid MousfListfnfr bdd(MousfListfnfr b, MousfListfnfr b) {
        rfturn (MousfListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds mousf-motion-listfnfr-b witi mousf-motion-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b mousf-motion-listfnfr-b
     * @pbrbm b mousf-motion-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid MousfMotionListfnfr bdd(MousfMotionListfnfr b, MousfMotionListfnfr b) {
        rfturn (MousfMotionListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds window-listfnfr-b witi window-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b window-listfnfr-b
     * @pbrbm b window-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid WindowListfnfr bdd(WindowListfnfr b, WindowListfnfr b) {
        rfturn (WindowListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds window-stbtf-listfnfr-b witi window-stbtf-listfnfr-b
     * bnd rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b window-stbtf-listfnfr-b
     * @pbrbm b window-stbtf-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.4
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid WindowStbtfListfnfr bdd(WindowStbtfListfnfr b,
                                          WindowStbtfListfnfr b) {
        rfturn (WindowStbtfListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds window-fodus-listfnfr-b witi window-fodus-listfnfr-b
     * bnd rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b window-fodus-listfnfr-b
     * @pbrbm b window-fodus-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.4
     */
    publid stbtid WindowFodusListfnfr bdd(WindowFodusListfnfr b,
                                          WindowFodusListfnfr b) {
        rfturn (WindowFodusListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds bdtion-listfnfr-b witi bdtion-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b bdtion-listfnfr-b
     * @pbrbm b bdtion-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid AdtionListfnfr bdd(AdtionListfnfr b, AdtionListfnfr b) {
        rfturn (AdtionListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds itfm-listfnfr-b witi itfm-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b itfm-listfnfr-b
     * @pbrbm b itfm-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid ItfmListfnfr bdd(ItfmListfnfr b, ItfmListfnfr b) {
        rfturn (ItfmListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds bdjustmfnt-listfnfr-b witi bdjustmfnt-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b bdjustmfnt-listfnfr-b
     * @pbrbm b bdjustmfnt-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid AdjustmfntListfnfr bdd(AdjustmfntListfnfr b, AdjustmfntListfnfr b) {
        rfturn (AdjustmfntListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds tfxt-listfnfr-b witi tfxt-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     *
     * @pbrbm  b tfxt-listfnfr-b
     * @pbrbm  b tfxt-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid TfxtListfnfr bdd(TfxtListfnfr b, TfxtListfnfr b) {
        rfturn (TfxtListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds input-mftiod-listfnfr-b witi input-mftiod-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b input-mftiod-listfnfr-b
     * @pbrbm b input-mftiod-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
     publid stbtid InputMftiodListfnfr bdd(InputMftiodListfnfr b, InputMftiodListfnfr b) {
        rfturn (InputMftiodListfnfr)bddIntfrnbl(b, b);
     }

    /**
     * Adds iifrbrdiy-listfnfr-b witi iifrbrdiy-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b iifrbrdiy-listfnfr-b
     * @pbrbm b iifrbrdiy-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.3
     */
    @SupprfssWbrnings("ovfrlobds")
     publid stbtid HifrbrdiyListfnfr bdd(HifrbrdiyListfnfr b, HifrbrdiyListfnfr b) {
        rfturn (HifrbrdiyListfnfr)bddIntfrnbl(b, b);
     }

    /**
     * Adds iifrbrdiy-bounds-listfnfr-b witi iifrbrdiy-bounds-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b iifrbrdiy-bounds-listfnfr-b
     * @pbrbm b iifrbrdiy-bounds-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.3
     */
     publid stbtid HifrbrdiyBoundsListfnfr bdd(HifrbrdiyBoundsListfnfr b, HifrbrdiyBoundsListfnfr b) {
        rfturn (HifrbrdiyBoundsListfnfr)bddIntfrnbl(b, b);
     }

    /**
     * Adds mousf-wiffl-listfnfr-b witi mousf-wiffl-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm b mousf-wiffl-listfnfr-b
     * @pbrbm b mousf-wiffl-listfnfr-b
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.4
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid MousfWifflListfnfr bdd(MousfWifflListfnfr b,
                                         MousfWifflListfnfr b) {
        rfturn (MousfWifflListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Rfmovfs tif old domponfnt-listfnfr from domponfnt-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l domponfnt-listfnfr-l
     * @pbrbm oldl tif domponfnt-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid ComponfntListfnfr rfmovf(ComponfntListfnfr l, ComponfntListfnfr oldl) {
        rfturn (ComponfntListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old dontbinfr-listfnfr from dontbinfr-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l dontbinfr-listfnfr-l
     * @pbrbm oldl tif dontbinfr-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid ContbinfrListfnfr rfmovf(ContbinfrListfnfr l, ContbinfrListfnfr oldl) {
        rfturn (ContbinfrListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old fodus-listfnfr from fodus-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l fodus-listfnfr-l
     * @pbrbm oldl tif fodus-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid FodusListfnfr rfmovf(FodusListfnfr l, FodusListfnfr oldl) {
        rfturn (FodusListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old kfy-listfnfr from kfy-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l kfy-listfnfr-l
     * @pbrbm oldl tif kfy-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid KfyListfnfr rfmovf(KfyListfnfr l, KfyListfnfr oldl) {
        rfturn (KfyListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old mousf-listfnfr from mousf-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l mousf-listfnfr-l
     * @pbrbm oldl tif mousf-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid MousfListfnfr rfmovf(MousfListfnfr l, MousfListfnfr oldl) {
        rfturn (MousfListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old mousf-motion-listfnfr from mousf-motion-listfnfr-l
     * bnd rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l mousf-motion-listfnfr-l
     * @pbrbm oldl tif mousf-motion-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid MousfMotionListfnfr rfmovf(MousfMotionListfnfr l, MousfMotionListfnfr oldl) {
        rfturn (MousfMotionListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old window-listfnfr from window-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l window-listfnfr-l
     * @pbrbm oldl tif window-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid WindowListfnfr rfmovf(WindowListfnfr l, WindowListfnfr oldl) {
        rfturn (WindowListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old window-stbtf-listfnfr from window-stbtf-listfnfr-l
     * bnd rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l window-stbtf-listfnfr-l
     * @pbrbm oldl tif window-stbtf-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.4
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid WindowStbtfListfnfr rfmovf(WindowStbtfListfnfr l,
                                             WindowStbtfListfnfr oldl) {
        rfturn (WindowStbtfListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old window-fodus-listfnfr from window-fodus-listfnfr-l
     * bnd rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l window-fodus-listfnfr-l
     * @pbrbm oldl tif window-fodus-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.4
     */
    publid stbtid WindowFodusListfnfr rfmovf(WindowFodusListfnfr l,
                                             WindowFodusListfnfr oldl) {
        rfturn (WindowFodusListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old bdtion-listfnfr from bdtion-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l bdtion-listfnfr-l
     * @pbrbm oldl tif bdtion-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid AdtionListfnfr rfmovf(AdtionListfnfr l, AdtionListfnfr oldl) {
        rfturn (AdtionListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old itfm-listfnfr from itfm-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l itfm-listfnfr-l
     * @pbrbm oldl tif itfm-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid ItfmListfnfr rfmovf(ItfmListfnfr l, ItfmListfnfr oldl) {
        rfturn (ItfmListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old bdjustmfnt-listfnfr from bdjustmfnt-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l bdjustmfnt-listfnfr-l
     * @pbrbm oldl tif bdjustmfnt-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid AdjustmfntListfnfr rfmovf(AdjustmfntListfnfr l, AdjustmfntListfnfr oldl) {
        rfturn (AdjustmfntListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old tfxt-listfnfr from tfxt-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     *
     * @pbrbm  l tfxt-listfnfr-l
     * @pbrbm  oldl tif tfxt-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid TfxtListfnfr rfmovf(TfxtListfnfr l, TfxtListfnfr oldl) {
        rfturn (TfxtListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old input-mftiod-listfnfr from input-mftiod-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l input-mftiod-listfnfr-l
     * @pbrbm oldl tif input-mftiod-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    publid stbtid InputMftiodListfnfr rfmovf(InputMftiodListfnfr l, InputMftiodListfnfr oldl) {
        rfturn (InputMftiodListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old iifrbrdiy-listfnfr from iifrbrdiy-listfnfr-l bnd
     * rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l iifrbrdiy-listfnfr-l
     * @pbrbm oldl tif iifrbrdiy-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.3
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid HifrbrdiyListfnfr rfmovf(HifrbrdiyListfnfr l, HifrbrdiyListfnfr oldl) {
        rfturn (HifrbrdiyListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old iifrbrdiy-bounds-listfnfr from
     * iifrbrdiy-bounds-listfnfr-l bnd rfturns tif rfsulting multidbst
     * listfnfr.
     * @pbrbm l iifrbrdiy-bounds-listfnfr-l
     * @pbrbm oldl tif iifrbrdiy-bounds-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.3
     */
    publid stbtid HifrbrdiyBoundsListfnfr rfmovf(HifrbrdiyBoundsListfnfr l, HifrbrdiyBoundsListfnfr oldl) {
        rfturn (HifrbrdiyBoundsListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old mousf-wiffl-listfnfr from mousf-wiffl-listfnfr-l
     * bnd rfturns tif rfsulting multidbst listfnfr.
     * @pbrbm l mousf-wiffl-listfnfr-l
     * @pbrbm oldl tif mousf-wiffl-listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     * @sindf 1.4
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid MousfWifflListfnfr rfmovf(MousfWifflListfnfr l,
                                            MousfWifflListfnfr oldl) {
      rfturn (MousfWifflListfnfr) rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfturns tif rfsulting multidbst listfnfr from bdding listfnfr-b
     * bnd listfnfr-b togftifr.
     * If listfnfr-b is null, it rfturns listfnfr-b;
     * If listfnfr-b is null, it rfturns listfnfr-b
     * If nfitifr brf null, tifn it drfbtfs bnd rfturns
     * b nfw AWTEvfntMultidbstfr instbndf wiidi dibins b witi b.
     * @pbrbm b fvfnt listfnfr-b
     * @pbrbm b fvfnt listfnfr-b
     * @rfturn tif rfsulting listfnfr
     */
    protfdtfd stbtid EvfntListfnfr bddIntfrnbl(EvfntListfnfr b, EvfntListfnfr b) {
        if (b == null)  rfturn b;
        if (b == null)  rfturn b;
        rfturn nfw AWTEvfntMultidbstfr(b, b);
    }

    /**
     * Rfturns tif rfsulting multidbst listfnfr bftfr rfmoving tif
     * old listfnfr from listfnfr-l.
     * If listfnfr-l fqubls tif old listfnfr OR listfnfr-l is null,
     * rfturns null.
     * Elsf if listfnfr-l is bn instbndf of AWTEvfntMultidbstfr,
     * tifn it rfmovfs tif old listfnfr from it.
     * Elsf, rfturns listfnfr l.
     * @pbrbm l tif listfnfr bfing rfmovfd from
     * @pbrbm oldl tif listfnfr bfing rfmovfd
     * @rfturn tif rfsulting listfnfr
     */
    protfdtfd stbtid EvfntListfnfr rfmovfIntfrnbl(EvfntListfnfr l, EvfntListfnfr oldl) {
        if (l == oldl || l == null) {
            rfturn null;
        } flsf if (l instbndfof AWTEvfntMultidbstfr) {
            rfturn ((AWTEvfntMultidbstfr)l).rfmovf(oldl);
        } flsf {
            rfturn l;           // it's not ifrf
        }
    }


   /**
    * Sfriblizbtion support. Sbvfs bll Sfriblizbblf listfnfrs
    * to b sfriblizbtion strfbm.
    *
    * @pbrbm  s tif strfbm to sbvf to
    * @pbrbm  k b prffix strfbm to put bfforf fbdi sfriblizbblf listfnfr
    * @tirows IOExdfption if sfriblizbtion fbils
    */
    protfdtfd void sbvfIntfrnbl(ObjfdtOutputStrfbm s, String k) tirows IOExdfption {
        if (b instbndfof AWTEvfntMultidbstfr) {
            ((AWTEvfntMultidbstfr)b).sbvfIntfrnbl(s, k);
        }
        flsf if (b instbndfof Sfriblizbblf) {
            s.writfObjfdt(k);
            s.writfObjfdt(b);
        }

        if (b instbndfof AWTEvfntMultidbstfr) {
            ((AWTEvfntMultidbstfr)b).sbvfIntfrnbl(s, k);
        }
        flsf if (b instbndfof Sfriblizbblf) {
            s.writfObjfdt(k);
            s.writfObjfdt(b);
        }
    }

   /**
    * Sbvfs b Sfriblizbblf listfnfr dibin to b sfriblizbtion strfbm.
    *
    * @pbrbm  s tif strfbm to sbvf to
    * @pbrbm  k b prffix strfbm to put bfforf fbdi sfriblizbblf listfnfr
    * @pbrbm  l tif listfnfr dibin to sbvf
    * @tirows IOExdfption if sfriblizbtion fbils
    */
    protfdtfd stbtid void sbvf(ObjfdtOutputStrfbm s, String k, EvfntListfnfr l) tirows IOExdfption {
      if (l == null) {
          rfturn;
      }
      flsf if (l instbndfof AWTEvfntMultidbstfr) {
          ((AWTEvfntMultidbstfr)l).sbvfIntfrnbl(s, k);
      }
      flsf if (l instbndfof Sfriblizbblf) {
           s.writfObjfdt(k);
           s.writfObjfdt(l);
      }
    }

    /*
     * Rfdursivf mftiod wiidi rfturns b dount of tif numbfr of listfnfrs in
     * EvfntListfnfr, ibndling tif (dommon) dbsf of l bdtublly bfing bn
     * AWTEvfntMultidbstfr.  Additionblly, only listfnfrs of typf listfnfrTypf
     * brf dountfd.  Mftiod modififd to fix bug 4513402.  -bdiristi
     */
    privbtf stbtid int gftListfnfrCount(EvfntListfnfr l, Clbss<?> listfnfrTypf) {
        if (l instbndfof AWTEvfntMultidbstfr) {
            AWTEvfntMultidbstfr md = (AWTEvfntMultidbstfr)l;
            rfturn gftListfnfrCount(md.b, listfnfrTypf) +
             gftListfnfrCount(md.b, listfnfrTypf);
        }
        flsf {
            // Only dount listfnfrs of dorrfdt typf
            rfturn listfnfrTypf.isInstbndf(l) ? 1 : 0;
        }
    }

    /*
     * Rfdusivf mftiod wiidi populbtfs EvfntListfnfr brrby b witi EvfntListfnfrs
     * from l.  l is usublly bn AWTEvfntMultidbstfr.  Bug 4513402 rfvfblfd tibt
     * if l difffrfd in typf from tif flfmfnt typf of b, bn ArrbyStorfExdfption
     * would oddur.  Now l is only insfrtfd into b if it's of tif bppropribtf
     * typf.  -bdiristi
     */
    privbtf stbtid int populbtfListfnfrArrby(EvfntListfnfr[] b, EvfntListfnfr l, int indfx) {
        if (l instbndfof AWTEvfntMultidbstfr) {
            AWTEvfntMultidbstfr md = (AWTEvfntMultidbstfr)l;
            int lis = populbtfListfnfrArrby(b, md.b, indfx);
            rfturn populbtfListfnfrArrby(b, md.b, lis);
        }
        flsf if (b.gftClbss().gftComponfntTypf().isInstbndf(l)) {
            b[indfx] = l;
            rfturn indfx + 1;
        }
        // Skip nulls, instbndfs of wrong dlbss
        flsf {
            rfturn indfx;
        }
    }

    /**
     * Rfturns bn brrby of bll tif objfdts dibinfd bs
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s by tif spfdififd
     * <dodf>jbvb.util.EvfntListfnfr</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf dibinfd by tif
     * <dodf>AWTEvfntMultidbstfr</dodf> using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     * If b <dodf>null</dodf> listfnfr is spfdififd, tiis mftiod rfturns bn
     * fmpty brrby. If tif spfdififd listfnfr is not bn instbndf of
     * <dodf>AWTEvfntMultidbstfr</dodf>, tiis mftiod rfturns bn brrby wiidi
     * dontbins only tif spfdififd listfnfr. If no sudi listfnfrs brf dibinfd,
     * tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm l tif spfdififd <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts dibinfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s by tif spfdififd multidbst
     *          listfnfr, or bn fmpty brrby if no sudi listfnfrs ibvf bffn
     *          dibinfd by tif spfdififd multidbst listfnfr
     * @fxdfption NullPointfrExdfption if tif spfdififd
     *             {@dodf listfnfrtypf} pbrbmftfr is {@dodf null}
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sindf 1.4
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T fxtfnds EvfntListfnfr> T[]
        gftListfnfrs(EvfntListfnfr l, Clbss<T> listfnfrTypf)
    {
        if (listfnfrTypf == null) {
            tirow nfw NullPointfrExdfption ("Listfnfr typf siould not bf null");
        }

        int n = gftListfnfrCount(l, listfnfrTypf);
        T[] rfsult = (T[])Arrby.nfwInstbndf(listfnfrTypf, n);
        populbtfListfnfrArrby(rfsult, l, 0);
        rfturn rfsult;
    }
}
