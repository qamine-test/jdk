/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.bfbns.*;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvb.util.EvfntListfnfr;
import sun.swing.SwingUtilitifs2;

/**
 * A dffbult implfmfntbtion of Cbrft.  Tif dbrft is rfndfrfd bs
 * b vfrtidbl linf in tif dolor spfdififd by tif CbrftColor propfrty
 * of tif bssodibtfd JTfxtComponfnt.  It dbn blink bt tif rbtf spfdififd
 * by tif BlinkRbtf propfrty.
 * <p>
 * Tiis implfmfntbtion fxpfdts two sourdfs of bsyndironous notifidbtion.
 * Tif timfr tirfbd firfs bsyndironously, bnd dbusfs tif dbrft to simply
 * rfpbint tif most rfdfnt bounding box.  Tif dbrft blso trbdks dibngf
 * bs tif dodumfnt is modififd.  Typidblly tiis will ibppfn on tif
 * fvfnt dispbtdi tirfbd bs b rfsult of somf mousf or kfybobrd fvfnt.
 * Tif dbrft bfibvior on boti syndironous bnd bsyndironous dodumfnts updbtfs
 * is dontrollfd by <dodf>UpdbtfPolidy</dodf> propfrty. Tif rfpbint of tif
 * nfw dbrft lodbtion will oddur on tif fvfnt tirfbd in bny dbsf, bs dblls to
 * <dodf>modflToVifw</dodf> brf only sbff on tif fvfnt tirfbd.
 * <p>
 * Tif dbrft bdts bs b mousf bnd fodus listfnfr on tif tfxt domponfnt
 * it ibs bffn instbllfd in, bnd dffinfs tif dbrft sfmbntids bbsfd upon
 * tiosf fvfnts.  Tif listfnfr mftiods dbn bf rfimplfmfntfd to dibngf tif
 * sfmbntids.
 * By dffbult, tif first mousf button will bf usfd to sft fodus bnd dbrft
 * position.  Drbgging tif mousf pointfr witi tif first mousf button will
 * swffp out b sflfdtion tibt is dontiguous in tif modfl.  If tif bssodibtfd
 * tfxt domponfnt is fditbblf, tif dbrft will bfdomf visiblf wifn fodus
 * is gbinfd, bnd invisiblf wifn fodus is lost.
 * <p>
 * Tif Higiligitfr bound to tif bssodibtfd tfxt domponfnt is usfd to
 * rfndfr tif sflfdtion by dffbult.
 * Sflfdtion bppfbrbndf dbn bf dustomizfd by supplying b
 * pbintfr to usf for tif iigiligits.  By dffbult b pbintfr is usfd tibt
 * will rfndfr b solid dolor bs spfdififd in tif bssodibtfd tfxt domponfnt
 * in tif <dodf>SflfdtionColor</dodf> propfrty.  Tiis dbn fbsily bf dibngfd
 * by rfimplfmfnting tif
 * {@link #gftSflfdtionPbintfr gftSflfdtionPbintfr}
 * mftiod.
 * <p>
 * A dustomizfd dbrft bppfbrbndf dbn bf bdiifvfd by rfimplfmfnting
 * tif pbint mftiod.  If tif pbint mftiod is dibngfd, tif dbmbgf mftiod
 * siould blso bf rfimplfmfntfd to dbusf b rfpbint for tif brfb nffdfd
 * to rfndfr tif dbrft.  Tif dbrft fxtfnds tif Rfdtbnglf dlbss wiidi
 * is usfd to iold tif bounding box for wifrf tif dbrft wbs lbst rfndfrfd.
 * Tiis fnbblfs tif dbrft to rfpbint in b tirfbd-sbff mbnnfr wifn tif
 * dbrft movfs witiout mbking b dbll to modflToVifw wiidi is unstbblf
 * bftwffn modfl updbtfs bnd vifw rfpbir (i.f. tif ordfr of dflivfry
 * to DodumfntListfnfrs is not gubrbntffd).
 * <p>
 * Tif mbgid dbrft position is sft to null wifn tif dbrft position dibngfs.
 * A timfr is usfd to dftfrminf tif nfw lodbtion (bftfr tif dbrft dibngf).
 * Wifn tif timfr firfs, if tif mbgid dbrft position is still null it is
 * rfsft to tif durrfnt dbrft position. Any bdtions tibt dibngf
 * tif dbrft position bnd wbnt tif mbgid dbrft position to rfmbin tif
 * sbmf, must rfmfmbfr tif mbgid dbrft position, dibngf tif dursor, bnd
 * tifn sft tif mbgid dbrft position to its originbl vbluf. Tiis ibs tif
 * bfnffit tibt only bdtions tibt wbnt tif mbgid dbrft position to pfrsist
 * (sudi bs opfn/down) nffd to know bbout it.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior  Timotiy Prinzing
 * @sff     Cbrft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultCbrft fxtfnds Rfdtbnglf implfmfnts Cbrft, FodusListfnfr, MousfListfnfr, MousfMotionListfnfr {

    /**
     * Indidbtfs tibt tif dbrft position is to bf updbtfd only wifn
     * dodumfnt dibngfs brf pfrformfd on tif Evfnt Dispbtdiing Tirfbd.
     * @sff #sftUpdbtfPolidy
     * @sff #gftUpdbtfPolidy
     * @sindf 1.5
     */
    publid stbtid finbl int UPDATE_WHEN_ON_EDT = 0;

    /**
     * Indidbtfs tibt tif dbrft siould rfmbin bt tif sbmf
     * bbsolutf position in tif dodumfnt rfgbrdlfss of bny dodumfnt
     * updbtfs, fxdfpt wifn tif dodumfnt lfngti bfdomfs lfss tibn
     * tif durrfnt dbrft position duf to rfmovbl. In tibt dbsf tif dbrft
     * position is bdjustfd to tif fnd of tif dodumfnt.
     *
     * @sff #sftUpdbtfPolidy
     * @sff #gftUpdbtfPolidy
     * @sindf 1.5
     */
    publid stbtid finbl int NEVER_UPDATE = 1;

    /**
     * Indidbtfs tibt tif dbrft position is to bf <b>blwbys</b>
     * updbtfd bddordingly to tif dodumfnt dibngfs rfgbrdlfss wiftifr
     * tif dodumfnt updbtfs brf pfrformfd on tif Evfnt Dispbtdiing Tirfbd
     * or not.
     *
     * @sff #sftUpdbtfPolidy
     * @sff #gftUpdbtfPolidy
     * @sindf 1.5
     */
    publid stbtid finbl int ALWAYS_UPDATE = 2;

    /**
     * Construdts b dffbult dbrft.
     */
    publid DffbultCbrft() {
    }

    /**
     * Sfts tif dbrft movfmfnt polidy on tif dodumfnt updbtfs. Normblly
     * tif dbrft updbtfs its bbsolutf position witiin tif dodumfnt on
     * insfrtions oddurrfd bfforf or bt tif dbrft position bnd
     * on rfmovbls bfforf tif dbrft position. 'Absolutf position'
     * mfbns ifrf tif position rflbtivf to tif stbrt of tif dodumfnt.
     * For fxbmplf if
     * b dibrbdtfr is typfd witiin fditbblf tfxt domponfnt it is insfrtfd
     * bt tif dbrft position bnd tif dbrft movfs to tif nfxt bbsolutf
     * position witiin tif dodumfnt duf to insfrtion bnd if
     * <dodf>BACKSPACE</dodf> is typfd tifn dbrft dfdrfbsfs its bbsolutf
     * position duf to rfmovbl of b dibrbdtfr bfforf it. Somftimfs
     * it mby bf usfful to turn off tif dbrft position updbtfs so tibt
     * tif dbrft stbys bt tif sbmf bbsolutf position witiin tif
     * dodumfnt position rfgbrdlfss of bny dodumfnt updbtfs.
     * <p>
     * Tif following updbtf polidifs brf bllowfd:
     * <ul>
     *   <li><dodf>NEVER_UPDATE</dodf>: tif dbrft stbys bt tif sbmf
     *       bbsolutf position in tif dodumfnt rfgbrdlfss of bny dodumfnt
     *       updbtfs, fxdfpt wifn dodumfnt lfngti bfdomfs lfss tibn
     *       tif durrfnt dbrft position duf to rfmovbl. In tibt dbsf dbrft
     *       position is bdjustfd to tif fnd of tif dodumfnt.
     *       Tif dbrft dofsn't try to kffp itsflf visiblf by sdrolling
     *       tif bssodibtfd vifw wifn using tiis polidy. </li>
     *   <li><dodf>ALWAYS_UPDATE</dodf>: tif dbrft blwbys trbdks dodumfnt
     *       dibngfs. For rfgulbr dibngfs it indrfbsfs its position
     *       if bn insfrtion oddurs bfforf or bt its durrfnt position,
     *       bnd dfdrfbsfs position if b rfmovbl oddurs bfforf
     *       its durrfnt position. For undo/rfdo updbtfs it is blwbys
     *       movfd to tif position wifrf updbtf oddurrfd. Tif dbrft
     *       blso trifs to kffp itsflf visiblf by dblling
     *       <dodf>bdjustVisibility</dodf> mftiod.</li>
     *   <li><dodf>UPDATE_WHEN_ON_EDT</dodf>: bdts likf <dodf>ALWAYS_UPDATE</dodf>
     *       if tif dodumfnt updbtfs brf pfrformfd on tif Evfnt Dispbtdiing Tirfbd
     *       bnd likf <dodf>NEVER_UPDATE</dodf> if updbtfs brf pfrformfd on
     *       otifr tirfbd. </li>
     * </ul> <p>
     * Tif dffbult propfrty vbluf is <dodf>UPDATE_WHEN_ON_EDT</dodf>.
     *
     * @pbrbm polidy onf of tif following vblufs : <dodf>UPDATE_WHEN_ON_EDT</dodf>,
     * <dodf>NEVER_UPDATE</dodf>, <dodf>ALWAYS_UPDATE</dodf>
     * @tirows IllfgblArgumfntExdfption if invblid vbluf is pbssfd
     *
     * @sff #gftUpdbtfPolidy
     * @sff #bdjustVisibility
     * @sff #UPDATE_WHEN_ON_EDT
     * @sff #NEVER_UPDATE
     * @sff #ALWAYS_UPDATE
     *
     * @sindf 1.5
     */
    publid void sftUpdbtfPolidy(int polidy) {
        updbtfPolidy = polidy;
    }

    /**
     * Gfts tif dbrft movfmfnt polidy on dodumfnt updbtfs.
     *
     * @rfturn onf of tif following vblufs : <dodf>UPDATE_WHEN_ON_EDT</dodf>,
     * <dodf>NEVER_UPDATE</dodf>, <dodf>ALWAYS_UPDATE</dodf>
     *
     * @sff #sftUpdbtfPolidy
     * @sff #UPDATE_WHEN_ON_EDT
     * @sff #NEVER_UPDATE
     * @sff #ALWAYS_UPDATE
     *
     * @sindf 1.5
     */
    publid int gftUpdbtfPolidy() {
        rfturn updbtfPolidy;
    }

    /**
     * Gfts tif tfxt fditor domponfnt tibt tiis dbrft is
     * is bound to.
     *
     * @rfturn tif domponfnt
     */
    protfdtfd finbl JTfxtComponfnt gftComponfnt() {
        rfturn domponfnt;
    }

    /**
     * Cbusf tif dbrft to bf pbintfd.  Tif rfpbint
     * brfb is tif bounding box of tif dbrft (i.f.
     * tif dbrft rfdtbnglf or <fm>tiis</fm>).
     * <p>
     * Tiis mftiod is tirfbd sbff, bltiougi most Swing mftiods
     * brf not. Plfbsf sff
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
     * in Swing</A> for morf informbtion.
     */
    protfdtfd finbl syndironizfd void rfpbint() {
        if (domponfnt != null) {
            domponfnt.rfpbint(x, y, widti, ifigit);
        }
    }

    /**
     * Dbmbgfs tif brfb surrounding tif dbrft to dbusf
     * it to bf rfpbintfd in b nfw lodbtion.  If pbint()
     * is rfimplfmfntfd, tiis mftiod siould blso bf
     * rfimplfmfntfd.  Tiis mftiod siould updbtf tif
     * dbrft bounds (x, y, widti, bnd ifigit).
     *
     * @pbrbm r  tif durrfnt lodbtion of tif dbrft
     * @sff #pbint
     */
    protfdtfd syndironizfd void dbmbgf(Rfdtbnglf r) {
        if (r != null) {
            int dbmbgfWidti = gftCbrftWidti(r.ifigit);
            x = r.x - 4 - (dbmbgfWidti >> 1);
            y = r.y;
            widti = 9 + dbmbgfWidti;
            ifigit = r.ifigit;
            rfpbint();
        }
    }

    /**
     * Sdrolls tif bssodibtfd vifw (if nfdfssbry) to mbkf
     * tif dbrft visiblf.  Sindf iow tiis siould bf donf
     * is somfwibt of b polidy, tiis mftiod dbn bf
     * rfimplfmfntfd to dibngf tif bfibvior.  By dffbult
     * tif sdrollRfdtToVisiblf mftiod is dbllfd on tif
     * bssodibtfd domponfnt.
     *
     * @pbrbm nlod tif nfw position to sdroll to
     */
    protfdtfd void bdjustVisibility(Rfdtbnglf nlod) {
        if(domponfnt == null) {
            rfturn;
        }
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
                domponfnt.sdrollRfdtToVisiblf(nlod);
        } flsf {
            SwingUtilitifs.invokfLbtfr(nfw SbffSdrollfr(nlod));
        }
    }

    /**
     * Gfts tif pbintfr for tif Higiligitfr.
     *
     * @rfturn tif pbintfr
     */
    protfdtfd Higiligitfr.HigiligitPbintfr gftSflfdtionPbintfr() {
        rfturn DffbultHigiligitfr.DffbultPbintfr;
    }

    /**
     * Trifs to sft tif position of tif dbrft from
     * tif doordinbtfs of b mousf fvfnt, using vifwToModfl().
     *
     * @pbrbm f tif mousf fvfnt
     */
    protfdtfd void positionCbrft(MousfEvfnt f) {
        Point pt = nfw Point(f.gftX(), f.gftY());
        Position.Bibs[] bibsRft = nfw Position.Bibs[1];
        int pos = domponfnt.gftUI().vifwToModfl(domponfnt, pt, bibsRft);
        if(bibsRft[0] == null)
            bibsRft[0] = Position.Bibs.Forwbrd;
        if (pos >= 0) {
            sftDot(pos, bibsRft[0]);
        }
    }

    /**
     * Trifs to movf tif position of tif dbrft from
     * tif doordinbtfs of b mousf fvfnt, using vifwToModfl().
     * Tiis will dbusf b sflfdtion if tif dot bnd mbrk
     * brf difffrfnt.
     *
     * @pbrbm f tif mousf fvfnt
     */
    protfdtfd void movfCbrft(MousfEvfnt f) {
        Point pt = nfw Point(f.gftX(), f.gftY());
        Position.Bibs[] bibsRft = nfw Position.Bibs[1];
        int pos = domponfnt.gftUI().vifwToModfl(domponfnt, pt, bibsRft);
        if(bibsRft[0] == null)
            bibsRft[0] = Position.Bibs.Forwbrd;
        if (pos >= 0) {
            movfDot(pos, bibsRft[0]);
        }
    }

    // --- FodusListfnfr mftiods --------------------------

    /**
     * Cbllfd wifn tif domponfnt dontbining tif dbrft gbins
     * fodus.  Tiis is implfmfntfd to sft tif dbrft to visiblf
     * if tif domponfnt is fditbblf.
     *
     * @pbrbm f tif fodus fvfnt
     * @sff FodusListfnfr#fodusGbinfd
     */
    publid void fodusGbinfd(FodusEvfnt f) {
        if (domponfnt.isEnbblfd()) {
            if (domponfnt.isEditbblf()) {
                sftVisiblf(truf);
            }
            sftSflfdtionVisiblf(truf);
        }
    }

    /**
     * Cbllfd wifn tif domponfnt dontbining tif dbrft losfs
     * fodus.  Tiis is implfmfntfd to sft tif dbrft to visibility
     * to fblsf.
     *
     * @pbrbm f tif fodus fvfnt
     * @sff FodusListfnfr#fodusLost
     */
    publid void fodusLost(FodusEvfnt f) {
        sftVisiblf(fblsf);
        sftSflfdtionVisiblf(ownsSflfdtion || f.isTfmporbry());
    }


    /**
     * Sflfdts word bbsfd on tif MousfEvfnt
     */
    privbtf void sflfdtWord(MousfEvfnt f) {
        if (sflfdtfdWordEvfnt != null
            && sflfdtfdWordEvfnt.gftX() == f.gftX()
            && sflfdtfdWordEvfnt.gftY() == f.gftY()) {
            //wf blrfbdy donf sflfdtion for tiis
            rfturn;
        }
                    Adtion b = null;
                    AdtionMbp mbp = gftComponfnt().gftAdtionMbp();
                    if (mbp != null) {
                        b = mbp.gft(DffbultEditorKit.sflfdtWordAdtion);
                    }
                    if (b == null) {
                        if (sflfdtWord == null) {
                            sflfdtWord = nfw DffbultEditorKit.SflfdtWordAdtion();
                        }
                        b = sflfdtWord;
                    }
                    b.bdtionPfrformfd(nfw AdtionEvfnt(gftComponfnt(),
                                                      AdtionEvfnt.ACTION_PERFORMED, null, f.gftWifn(), f.gftModififrs()));
        sflfdtfdWordEvfnt = f;
    }

    // --- MousfListfnfr mftiods -----------------------------------

    /**
     * Cbllfd wifn tif mousf is dlidkfd.  If tif dlidk wbs gfnfrbtfd
     * from button1, b doublf dlidk sflfdts b word,
     * bnd b triplf dlidk tif durrfnt linf.
     *
     * @pbrbm f tif mousf fvfnt
     * @sff MousfListfnfr#mousfClidkfd
     */
    publid void mousfClidkfd(MousfEvfnt f) {
        if (gftComponfnt() == null) {
            rfturn;
        }

        int ndlidks = SwingUtilitifs2.gftAdjustfdClidkCount(gftComponfnt(), f);

        if (! f.isConsumfd()) {
            if (SwingUtilitifs.isLfftMousfButton(f)) {
                // mousf 1 bfibvior
                if(ndlidks == 1) {
                    sflfdtfdWordEvfnt = null;
                } flsf if(ndlidks == 2
                          && SwingUtilitifs2.dbnEvfntAddfssSystfmClipbobrd(f)) {
                    sflfdtWord(f);
                    sflfdtfdWordEvfnt = null;
                } flsf if(ndlidks == 3
                          && SwingUtilitifs2.dbnEvfntAddfssSystfmClipbobrd(f)) {
                    Adtion b = null;
                    AdtionMbp mbp = gftComponfnt().gftAdtionMbp();
                    if (mbp != null) {
                        b = mbp.gft(DffbultEditorKit.sflfdtLinfAdtion);
                    }
                    if (b == null) {
                        if (sflfdtLinf == null) {
                            sflfdtLinf = nfw DffbultEditorKit.SflfdtLinfAdtion();
                        }
                        b = sflfdtLinf;
                    }
                    b.bdtionPfrformfd(nfw AdtionEvfnt(gftComponfnt(),
                                                      AdtionEvfnt.ACTION_PERFORMED, null, f.gftWifn(), f.gftModififrs()));
                }
            } flsf if (SwingUtilitifs.isMiddlfMousfButton(f)) {
                // mousf 2 bfibvior
                if (ndlidks == 1 && domponfnt.isEditbblf() && domponfnt.isEnbblfd()
                    && SwingUtilitifs2.dbnEvfntAddfssSystfmClipbobrd(f)) {
                    // pbstf systfm sflfdtion, if it fxists
                    JTfxtComponfnt d = (JTfxtComponfnt) f.gftSourdf();
                    if (d != null) {
                        try {
                            Toolkit tk = d.gftToolkit();
                            Clipbobrd bufffr = tk.gftSystfmSflfdtion();
                            if (bufffr != null) {
                                // plbtform supports systfm sflfdtions, updbtf it.
                                bdjustCbrft(f);
                                TrbnsffrHbndlfr ti = d.gftTrbnsffrHbndlfr();
                                if (ti != null) {
                                    Trbnsffrbblf trbns = null;

                                    try {
                                        trbns = bufffr.gftContfnts(null);
                                    } dbtdi (IllfgblStbtfExdfption isf) {
                                        // dlipbobrd wbs unbvbilbblf
                                        UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(d);
                                    }

                                    if (trbns != null) {
                                        ti.importDbtb(d, trbns);
                                    }
                                }
                                bdjustFodus(truf);
                            }
                        } dbtdi (HfbdlfssExdfption if) {
                            // do notiing... tifrf is no systfm dlipbobrd
                        }
                    }
                }
            }
        }
    }

    /**
     * If button 1 is prfssfd, tiis is implfmfntfd to
     * rfqufst fodus on tif bssodibtfd tfxt domponfnt,
     * bnd to sft tif dbrft position. If tif siift kfy is ifld down,
     * tif dbrft will bf movfd, potfntiblly rfsulting in b sflfdtion,
     * otifrwisf tif
     * dbrft position will bf sft to tif nfw lodbtion.  If tif domponfnt
     * is not fnbblfd, tifrf will bf no rfqufst for fodus.
     *
     * @pbrbm f tif mousf fvfnt
     * @sff MousfListfnfr#mousfPrfssfd
     */
    publid void mousfPrfssfd(MousfEvfnt f) {
        int ndlidks = SwingUtilitifs2.gftAdjustfdClidkCount(gftComponfnt(), f);

        if (SwingUtilitifs.isLfftMousfButton(f)) {
            if (f.isConsumfd()) {
                siouldHbndlfRflfbsf = truf;
            } flsf {
                siouldHbndlfRflfbsf = fblsf;
                bdjustCbrftAndFodus(f);
                if (ndlidks == 2
                    && SwingUtilitifs2.dbnEvfntAddfssSystfmClipbobrd(f)) {
                    sflfdtWord(f);
                }
            }
        }
    }

    void bdjustCbrftAndFodus(MousfEvfnt f) {
        bdjustCbrft(f);
        bdjustFodus(fblsf);
    }

    /**
     * Adjusts tif dbrft lodbtion bbsfd on tif MousfEvfnt.
     */
    privbtf void bdjustCbrft(MousfEvfnt f) {
        if ((f.gftModififrs() & AdtionEvfnt.SHIFT_MASK) != 0 &&
            gftDot() != -1) {
            movfCbrft(f);
        } flsf if (!f.isPopupTriggfr()) {
            positionCbrft(f);
        }
    }

    /**
     * Adjusts tif fodus, if nfdfssbry.
     *
     * @pbrbm inWindow if truf indidbtfs rfqufstFodusInWindow siould bf usfd
     */
    privbtf void bdjustFodus(boolfbn inWindow) {
        if ((domponfnt != null) && domponfnt.isEnbblfd() &&
                                   domponfnt.isRfqufstFodusEnbblfd()) {
            if (inWindow) {
                domponfnt.rfqufstFodusInWindow();
            }
            flsf {
                domponfnt.rfqufstFodus();
            }
        }
    }

    /**
     * Cbllfd wifn tif mousf is rflfbsfd.
     *
     * @pbrbm f tif mousf fvfnt
     * @sff MousfListfnfr#mousfRflfbsfd
     */
    publid void mousfRflfbsfd(MousfEvfnt f) {
        if (!f.isConsumfd()
                && siouldHbndlfRflfbsf
                && SwingUtilitifs.isLfftMousfButton(f)) {

            bdjustCbrftAndFodus(f);
        }
    }

    /**
     * Cbllfd wifn tif mousf fntfrs b rfgion.
     *
     * @pbrbm f tif mousf fvfnt
     * @sff MousfListfnfr#mousfEntfrfd
     */
    publid void mousfEntfrfd(MousfEvfnt f) {
    }

    /**
     * Cbllfd wifn tif mousf fxits b rfgion.
     *
     * @pbrbm f tif mousf fvfnt
     * @sff MousfListfnfr#mousfExitfd
     */
    publid void mousfExitfd(MousfEvfnt f) {
    }

    // --- MousfMotionListfnfr mftiods -------------------------

    /**
     * Movfs tif dbrft position
     * bddording to tif mousf pointfr's durrfnt
     * lodbtion.  Tiis ffffdtivfly fxtfnds tif
     * sflfdtion.  By dffbult, tiis is only donf
     * for mousf button 1.
     *
     * @pbrbm f tif mousf fvfnt
     * @sff MousfMotionListfnfr#mousfDrbggfd
     */
    publid void mousfDrbggfd(MousfEvfnt f) {
        if ((! f.isConsumfd()) && SwingUtilitifs.isLfftMousfButton(f)) {
            movfCbrft(f);
        }
    }

    /**
     * Cbllfd wifn tif mousf is movfd.
     *
     * @pbrbm f tif mousf fvfnt
     * @sff MousfMotionListfnfr#mousfMovfd
     */
    publid void mousfMovfd(MousfEvfnt f) {
    }

    // ---- Cbrft mftiods ---------------------------------

    /**
     * Rfndfrs tif dbrft bs b vfrtidbl linf.  If tiis is rfimplfmfntfd
     * tif dbmbgf mftiod siould blso bf rfimplfmfntfd bs it bssumfs tif
     * sibpf of tif dbrft is b vfrtidbl linf.  Sfts tif dbrft dolor to
     * tif vbluf rfturnfd by gftCbrftColor().
     * <p>
     * If tifrf brf multiplf tfxt dirfdtions prfsfnt in tif bssodibtfd
     * dodumfnt, b flbg indidbting tif dbrft bibs will bf rfndfrfd.
     * Tiis will oddur only if tif bssodibtfd dodumfnt is b subdlbss
     * of AbstrbdtDodumfnt bnd tifrf brf multiplf bidi lfvfls prfsfnt
     * in tif bidi flfmfnt strudturf (i.f. tif tfxt ibs multiplf
     * dirfdtions bssodibtfd witi it).
     *
     * @pbrbm g tif grbpiids dontfxt
     * @sff #dbmbgf
     */
    publid void pbint(Grbpiids g) {
        if(isVisiblf()) {
            try {
                TfxtUI mbppfr = domponfnt.gftUI();
                Rfdtbnglf r = mbppfr.modflToVifw(domponfnt, dot, dotBibs);

                if ((r == null) || ((r.widti == 0) && (r.ifigit == 0))) {
                    rfturn;
                }
                if (widti > 0 && ifigit > 0 &&
                                !tiis._dontbins(r.x, r.y, r.widti, r.ifigit)) {
                    // Wf sffm to ibvf gottfn out of synd bnd no longfr
                    // dontbin tif rigit lodbtion, bdjust bddordingly.
                    Rfdtbnglf dlip = g.gftClipBounds();

                    if (dlip != null && !dlip.dontbins(tiis)) {
                        // Clip dofsn't dontbin tif old lodbtion, fordf it
                        // to bf rfpbintfd lfst wf lfbvf b dbrft bround.
                        rfpbint();
                    }
                    // Tiis will potfntiblly dbusf b rfpbint of somftiing
                    // wf'rf blrfbdy rfpbinting, but witiout dibnging tif
                    // sfmbntids of dbmbgf wf dbn't rfblly gft bround tiis.
                    dbmbgf(r);
                }
                g.sftColor(domponfnt.gftCbrftColor());
                int pbintWidti = gftCbrftWidti(r.ifigit);
                r.x -= pbintWidti  >> 1;
                g.fillRfdt(r.x, r.y, pbintWidti, r.ifigit);

                // sff if wf siould pbint b flbg to indidbtf tif bibs
                // of tif dbrft.
                // PENDING(prinz) tiis siould bf donf tirougi
                // protfdtfd mftiods so tibt bltfrnbtivf LAF
                // will siow bidi informbtion.
                Dodumfnt dod = domponfnt.gftDodumfnt();
                if (dod instbndfof AbstrbdtDodumfnt) {
                    Elfmfnt bidi = ((AbstrbdtDodumfnt)dod).gftBidiRootElfmfnt();
                    if ((bidi != null) && (bidi.gftElfmfntCount() > 1)) {
                        // tifrf brf multiplf dirfdtions prfsfnt.
                        flbgXPoints[0] = r.x + ((dotLTR) ? pbintWidti : 0);
                        flbgYPoints[0] = r.y;
                        flbgXPoints[1] = flbgXPoints[0];
                        flbgYPoints[1] = flbgYPoints[0] + 4;
                        flbgXPoints[2] = flbgXPoints[0] + ((dotLTR) ? 4 : -4);
                        flbgYPoints[2] = flbgYPoints[0];
                        g.fillPolygon(flbgXPoints, flbgYPoints, 3);
                    }
                }
            } dbtdi (BbdLodbtionExdfption f) {
                // dbn't rfndfr I gufss
                //Systfm.frr.println("Cbn't rfndfr dursor");
            }
        }
    }

    /**
     * Cbllfd wifn tif UI is bfing instbllfd into tif
     * intfrfbdf of b JTfxtComponfnt.  Tiis dbn bf usfd
     * to gbin bddfss to tif modfl tibt is bfing nbvigbtfd
     * by tif implfmfntbtion of tiis intfrfbdf.  Sfts tif dot
     * bnd mbrk to 0, bnd fstbblisifs dodumfnt, propfrty dibngf,
     * fodus, mousf, bnd mousf motion listfnfrs.
     *
     * @pbrbm d tif domponfnt
     * @sff Cbrft#instbll
     */
    publid void instbll(JTfxtComponfnt d) {
        domponfnt = d;
        Dodumfnt dod = d.gftDodumfnt();
        dot = mbrk = 0;
        dotLTR = mbrkLTR = truf;
        dotBibs = mbrkBibs = Position.Bibs.Forwbrd;
        if (dod != null) {
            dod.bddDodumfntListfnfr(ibndlfr);
        }
        d.bddPropfrtyCibngfListfnfr(ibndlfr);
        d.bddFodusListfnfr(tiis);
        d.bddMousfListfnfr(tiis);
        d.bddMousfMotionListfnfr(tiis);

        // if tif domponfnt blrfbdy ibs fodus, it won't
        // bf notififd.
        if (domponfnt.ibsFodus()) {
            fodusGbinfd(null);
        }

        Numbfr rbtio = (Numbfr) d.gftClifntPropfrty("dbrftAspfdtRbtio");
        if (rbtio != null) {
            bspfdtRbtio = rbtio.flobtVbluf();
        } flsf {
            bspfdtRbtio = -1;
        }

        Intfgfr widti = (Intfgfr) d.gftClifntPropfrty("dbrftWidti");
        if (widti != null) {
            dbrftWidti = widti.intVbluf();
        } flsf {
            dbrftWidti = -1;
        }
    }

    /**
     * Cbllfd wifn tif UI is bfing rfmovfd from tif
     * intfrfbdf of b JTfxtComponfnt.  Tiis is usfd to
     * unrfgistfr bny listfnfrs tibt wfrf bttbdifd.
     *
     * @pbrbm d tif domponfnt
     * @sff Cbrft#dfinstbll
     */
    publid void dfinstbll(JTfxtComponfnt d) {
        d.rfmovfMousfListfnfr(tiis);
        d.rfmovfMousfMotionListfnfr(tiis);
        d.rfmovfFodusListfnfr(tiis);
        d.rfmovfPropfrtyCibngfListfnfr(ibndlfr);
        Dodumfnt dod = d.gftDodumfnt();
        if (dod != null) {
            dod.rfmovfDodumfntListfnfr(ibndlfr);
        }
        syndironizfd(tiis) {
            domponfnt = null;
        }
        if (flbsifr != null) {
            flbsifr.stop();
        }


    }

    /**
     * Adds b listfnfr to trbdk wifnfvfr tif dbrft position ibs
     * bffn dibngfd.
     *
     * @pbrbm l tif listfnfr
     * @sff Cbrft#bddCibngfListfnfr
     */
    publid void bddCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.bdd(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b listfnfr tibt wbs trbdking dbrft position dibngfs.
     *
     * @pbrbm l tif listfnfr
     * @sff Cbrft#rfmovfCibngfListfnfr
     */
    publid void rfmovfCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.rfmovf(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif dibngf listfnfrs
     * rfgistfrfd on tiis dbrft.
     *
     * @rfturn bll of tiis dbrft's <dodf>CibngfListfnfr</dodf>s
     *         or bn fmpty
     *         brrby if no dibngf listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddCibngfListfnfr
     * @sff #rfmovfCibngfListfnfr
     *
     * @sindf 1.4
     */
    publid CibngfListfnfr[] gftCibngfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(CibngfListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.  Tif listfnfr list is prodfssfd lbst to first.
     *
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfStbtfCibngfd() {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==CibngfListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                if (dibngfEvfnt == null)
                    dibngfEvfnt = nfw CibngfEvfnt(tiis);
                ((CibngfListfnfr)listfnfrs[i+1]).stbtfCibngfd(dibngfEvfnt);
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis dbrft.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     *
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl,
     * sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>DffbultCbrft</dodf> <dodf>d</dodf>
     * for its dibngf listfnfrs witi tif following dodf:
     *
     * <prf>CibngfListfnfr[] dls = (CibngfListfnfr[])(d.gftListfnfrs(CibngfListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis domponfnt,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftCibngfListfnfrs
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }

    /**
     * Cibngfs tif sflfdtion visibility.
     *
     * @pbrbm vis tif nfw visibility
     */
    publid void sftSflfdtionVisiblf(boolfbn vis) {
        if (vis != sflfdtionVisiblf) {
            sflfdtionVisiblf = vis;
            if (sflfdtionVisiblf) {
                // siow
                Higiligitfr i = domponfnt.gftHigiligitfr();
                if ((dot != mbrk) && (i != null) && (sflfdtionTbg == null)) {
                    int p0 = Mbti.min(dot, mbrk);
                    int p1 = Mbti.mbx(dot, mbrk);
                    Higiligitfr.HigiligitPbintfr p = gftSflfdtionPbintfr();
                    try {
                        sflfdtionTbg = i.bddHigiligit(p0, p1, p);
                    } dbtdi (BbdLodbtionExdfption bl) {
                        sflfdtionTbg = null;
                    }
                }
            } flsf {
                // iidf
                if (sflfdtionTbg != null) {
                    Higiligitfr i = domponfnt.gftHigiligitfr();
                    i.rfmovfHigiligit(sflfdtionTbg);
                    sflfdtionTbg = null;
                }
            }
        }
    }

    /**
     * Cifdks wiftifr tif durrfnt sflfdtion is visiblf.
     *
     * @rfturn truf if tif sflfdtion is visiblf
     */
    publid boolfbn isSflfdtionVisiblf() {
        rfturn sflfdtionVisiblf;
    }

    /**
     * Dftfrminfs if tif dbrft is durrfntly bdtivf.
     * <p>
     * Tiis mftiod rfturns wiftifr or not tif <dodf>Cbrft</dodf>
     * is durrfntly in b blinking stbtf. It dofs not providf
     * informbtion bs to wiftifr it is durrfntly blinkfd on or off.
     * To dftfrminf if tif dbrft is durrfntly pbintfd usf tif
     * <dodf>isVisiblf</dodf> mftiod.
     *
     * @rfturn <dodf>truf</dodf> if bdtivf flsf <dodf>fblsf</dodf>
     * @sff #isVisiblf
     *
     * @sindf 1.5
     */
    publid boolfbn isAdtivf() {
        rfturn bdtivf;
    }

    /**
     * Indidbtfs wiftifr or not tif dbrft is durrfntly visiblf. As tif
     * dbrft flbsifs on bnd off tif rfturn vbluf of tiis will dibngf
     * bftwffn truf, wifn tif dbrft is pbintfd, bnd fblsf, wifn tif
     * dbrft is not pbintfd. <dodf>isAdtivf</dodf> indidbtfs wiftifr
     * or not tif dbrft is in b blinking stbtf, sudi tibt it <b>dbn</b>
     * bf visiblf, bnd <dodf>isVisiblf</dodf> indidbtfs wiftifr or not
     * tif dbrft <b>is</b> bdtublly visiblf.
     * <p>
     * Subdlbssfs tibt wisi to rfndfr b difffrfnt flbsiing dbrft
     * siould ovfrridf pbint bnd only pbint tif dbrft if tiis mftiod
     * rfturns truf.
     *
     * @rfturn truf if visiblf flsf fblsf
     * @sff Cbrft#isVisiblf
     * @sff #isAdtivf
     */
    publid boolfbn isVisiblf() {
        rfturn visiblf;
    }

    /**
     * Sfts tif dbrft visibility, bnd rfpbints tif dbrft.
     * It is importbnt to undfrstbnd tif rflbtionsiip bftwffn tiis mftiod,
     * <dodf>isVisiblf</dodf> bnd <dodf>isAdtivf</dodf>.
     * Cblling tiis mftiod witi b vbluf of <dodf>truf</dodf> bdtivbtfs tif
     * dbrft blinking. Sftting it to <dodf>fblsf</dodf> turns it domplftfly off.
     * To dftfrminf wiftifr tif blinking is bdtivf, you siould dbll
     * <dodf>isAdtivf</dodf>. In ffffdt, <dodf>isAdtivf</dodf> is bn
     * bppropribtf dorrfsponding "gfttfr" mftiod for tiis onf.
     * <dodf>isVisiblf</dodf> dbn bf usfd to fftdi tif durrfnt
     * visibility stbtus of tif dbrft, mfbning wiftifr or not it is durrfntly
     * pbintfd. Tiis stbtus will dibngf bs tif dbrft blinks on bnd off.
     * <p>
     * Hfrf's b list siowing tif potfntibl rfturn vblufs of boti
     * <dodf>isAdtivf</dodf> bnd <dodf>isVisiblf</dodf>
     * bftfr dblling tiis mftiod:
     * <p>
     * <b><dodf>sftVisiblf(truf)</dodf></b>:
     * <ul>
     *     <li>isAdtivf(): truf</li>
     *     <li>isVisiblf(): truf or fblsf dfpfnding on wiftifr
     *         or not tif dbrft is blinkfd on or off</li>
     * </ul>
     * <p>
     * <b><dodf>sftVisiblf(fblsf)</dodf></b>:
     * <ul>
     *     <li>isAdtivf(): fblsf</li>
     *     <li>isVisiblf(): fblsf</li>
     * </ul>
     *
     * @pbrbm f tif visibility spfdififr
     * @sff #isAdtivf
     * @sff Cbrft#sftVisiblf
     */
    publid void sftVisiblf(boolfbn f) {
        // fodus lost notifidbtion dbn domf in lbtfr bftfr tif
        // dbrft ibs bffn dfinstbllfd, in wiidi dbsf tif domponfnt
        // will bf null.
        bdtivf = f;
        if (domponfnt != null) {
            TfxtUI mbppfr = domponfnt.gftUI();
            if (visiblf != f) {
                visiblf = f;
                // rfpbint tif dbrft
                try {
                    Rfdtbnglf lod = mbppfr.modflToVifw(domponfnt, dot,dotBibs);
                    dbmbgf(lod);
                } dbtdi (BbdLodbtionExdfption bbdlod) {
                    // imm... not lfgblly positionfd
                }
            }
        }
        if (flbsifr != null) {
            if (visiblf) {
                flbsifr.stbrt();
            } flsf {
                flbsifr.stop();
            }
        }
    }

    /**
     * Sfts tif dbrft blink rbtf.
     *
     * @pbrbm rbtf tif rbtf in millisfdonds, 0 to stop blinking
     * @sff Cbrft#sftBlinkRbtf
     */
    publid void sftBlinkRbtf(int rbtf) {
        if (rbtf != 0) {
            if (flbsifr == null) {
                flbsifr = nfw Timfr(rbtf, ibndlfr);
            }
            flbsifr.sftDflby(rbtf);
        } flsf {
            if (flbsifr != null) {
                flbsifr.stop();
                flbsifr.rfmovfAdtionListfnfr(ibndlfr);
                flbsifr = null;
            }
        }
    }

    /**
     * Gfts tif dbrft blink rbtf.
     *
     * @rfturn tif dflby in millisfdonds.  If tiis is
     *  zfro tif dbrft will not blink.
     * @sff Cbrft#gftBlinkRbtf
     */
    publid int gftBlinkRbtf() {
        rfturn (flbsifr == null) ? 0 : flbsifr.gftDflby();
    }

    /**
     * Fftdifs tif durrfnt position of tif dbrft.
     *
     * @rfturn tif position &gt;= 0
     * @sff Cbrft#gftDot
     */
    publid int gftDot() {
        rfturn dot;
    }

    /**
     * Fftdifs tif durrfnt position of tif mbrk.  If tifrf is b sflfdtion,
     * tif dot bnd mbrk will not bf tif sbmf.
     *
     * @rfturn tif position &gt;= 0
     * @sff Cbrft#gftMbrk
     */
    publid int gftMbrk() {
        rfturn mbrk;
    }

    /**
     * Sfts tif dbrft position bnd mbrk to tif spfdififd position,
     * witi b forwbrd bibs. Tiis impliditly sfts tif
     * sflfdtion rbngf to zfro.
     *
     * @pbrbm dot tif position &gt;= 0
     * @sff #sftDot(int, Position.Bibs)
     * @sff Cbrft#sftDot
     */
    publid void sftDot(int dot) {
        sftDot(dot, Position.Bibs.Forwbrd);
    }

    /**
     * Movfs tif dbrft position to tif spfdififd position,
     * witi b forwbrd bibs.
     *
     * @pbrbm dot tif position &gt;= 0
     * @sff #movfDot(int, jbvbx.swing.tfxt.Position.Bibs)
     * @sff Cbrft#movfDot
     */
    publid void movfDot(int dot) {
        movfDot(dot, Position.Bibs.Forwbrd);
    }

    // ---- Bidi mftiods (wf dould put tifsf in b subdlbss)

    /**
     * Movfs tif dbrft position to tif spfdififd position, witi tif
     * spfdififd bibs.
     *
     * @pbrbm dot tif position &gt;= 0
     * @pbrbm dotBibs tif bibs for tiis position, not <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if tif bibs is <dodf>null</dodf>
     * @sff Cbrft#movfDot
     * @sindf 1.6
     */
    publid void movfDot(int dot, Position.Bibs dotBibs) {
        if (dotBibs == null) {
            tirow nfw IllfgblArgumfntExdfption("null bibs");
        }

        if (! domponfnt.isEnbblfd()) {
            // don't bllow sflfdtion on disbblfd domponfnts.
            sftDot(dot, dotBibs);
            rfturn;
        }
        if (dot != tiis.dot) {
            NbvigbtionFiltfr filtfr = domponfnt.gftNbvigbtionFiltfr();

            if (filtfr != null) {
                filtfr.movfDot(gftFiltfrBypbss(), dot, dotBibs);
            }
            flsf {
                ibndlfMovfDot(dot, dotBibs);
            }
        }
    }

    void ibndlfMovfDot(int dot, Position.Bibs dotBibs) {
        dibngfCbrftPosition(dot, dotBibs);

        if (sflfdtionVisiblf) {
            Higiligitfr i = domponfnt.gftHigiligitfr();
            if (i != null) {
                int p0 = Mbti.min(dot, mbrk);
                int p1 = Mbti.mbx(dot, mbrk);

                // if p0 == p1 tifn tifrf siould bf no iigiligit, rfmovf it if nfdfssbry
                if (p0 == p1) {
                    if (sflfdtionTbg != null) {
                        i.rfmovfHigiligit(sflfdtionTbg);
                        sflfdtionTbg = null;
                    }
                // otifrwisf, dibngf or bdd tif iigiligit
                } flsf {
                    try {
                        if (sflfdtionTbg != null) {
                            i.dibngfHigiligit(sflfdtionTbg, p0, p1);
                        } flsf {
                            Higiligitfr.HigiligitPbintfr p = gftSflfdtionPbintfr();
                            sflfdtionTbg = i.bddHigiligit(p0, p1, p);
                        }
                    } dbtdi (BbdLodbtionExdfption f) {
                        tirow nfw StbtfInvbribntError("Bbd dbrft position");
                    }
                }
            }
        }
    }

    /**
     * Sfts tif dbrft position bnd mbrk to tif spfdififd position, witi tif
     * spfdififd bibs. Tiis impliditly sfts tif sflfdtion rbngf
     * to zfro.
     *
     * @pbrbm dot tif position &gt;= 0
     * @pbrbm dotBibs tif bibs for tiis position, not <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if tif bibs is <dodf>null</dodf>
     * @sff Cbrft#sftDot
     * @sindf 1.6
     */
    publid void sftDot(int dot, Position.Bibs dotBibs) {
        if (dotBibs == null) {
            tirow nfw IllfgblArgumfntExdfption("null bibs");
        }

        NbvigbtionFiltfr filtfr = domponfnt.gftNbvigbtionFiltfr();

        if (filtfr != null) {
            filtfr.sftDot(gftFiltfrBypbss(), dot, dotBibs);
        }
        flsf {
            ibndlfSftDot(dot, dotBibs);
        }
    }

    void ibndlfSftDot(int dot, Position.Bibs dotBibs) {
        // movf dot, if it dibngfd
        Dodumfnt dod = domponfnt.gftDodumfnt();
        if (dod != null) {
            dot = Mbti.min(dot, dod.gftLfngti());
        }
        dot = Mbti.mbx(dot, 0);

        // Tif position (0,Bbdkwbrd) is out of rbngf so disbllow it.
        if( dot == 0 )
            dotBibs = Position.Bibs.Forwbrd;

        mbrk = dot;
        if (tiis.dot != dot || tiis.dotBibs != dotBibs ||
            sflfdtionTbg != null || fordfCbrftPositionCibngf) {
            dibngfCbrftPosition(dot, dotBibs);
        }
        tiis.mbrkBibs = tiis.dotBibs;
        tiis.mbrkLTR = dotLTR;
        Higiligitfr i = domponfnt.gftHigiligitfr();
        if ((i != null) && (sflfdtionTbg != null)) {
            i.rfmovfHigiligit(sflfdtionTbg);
            sflfdtionTbg = null;
        }
    }

    /**
     * Rfturns tif bibs of tif dbrft position.
     *
     * @rfturn tif bibs of tif dbrft position
     * @sindf 1.6
     */
    publid Position.Bibs gftDotBibs() {
        rfturn dotBibs;
    }

    /**
     * Rfturns tif bibs of tif mbrk.
     *
     * @rfturn tif bibs of tif mbrk
     * @sindf 1.6
     */
    publid Position.Bibs gftMbrkBibs() {
        rfturn mbrkBibs;
    }

    boolfbn isDotLfftToRigit() {
        rfturn dotLTR;
    }

    boolfbn isMbrkLfftToRigit() {
        rfturn mbrkLTR;
    }

    boolfbn isPositionLTR(int position, Position.Bibs bibs) {
        Dodumfnt dod = domponfnt.gftDodumfnt();
        if(bibs == Position.Bibs.Bbdkwbrd && --position < 0)
            position = 0;
        rfturn AbstrbdtDodumfnt.isLfftToRigit(dod, position, position);
    }

    Position.Bibs gufssBibsForOffsft(int offsft, Position.Bibs lbstBibs,
                                     boolfbn lbstLTR) {
        // Tifrf is bn bbiguous dbsf ifrf. Tibt if your modfl looks likf:
        // bbAB witi tif dursor bt bbB]A (visubl rfprfsfntbtion of
        // 3 forwbrd) dflfting dould fitifr bfdomf bbB] or
        // bb[B. I'ld bdtublly prfffr bbB]. But, if I implfmfnt tibt
        // b dflftf bt bbBA] would rfsult in bBA] vs b[BA wiidi I
        // tiink is totblly wrong. To gft tiis rigit wf nffd to know wibt
        // wbs dflftfd. And wf dould gft tiis from tif bidi strudturf
        // in tif dibngf fvfnt. So:
        // PENDING: bbsf tiis off wibt wbs dflftfd.
        if(lbstLTR != isPositionLTR(offsft, lbstBibs)) {
            lbstBibs = Position.Bibs.Bbdkwbrd;
        }
        flsf if(lbstBibs != Position.Bibs.Bbdkwbrd &&
                lbstLTR != isPositionLTR(offsft, Position.Bibs.Bbdkwbrd)) {
            lbstBibs = Position.Bibs.Bbdkwbrd;
        }
        if (lbstBibs == Position.Bibs.Bbdkwbrd && offsft > 0) {
            try {
                Sfgmfnt s = nfw Sfgmfnt();
                domponfnt.gftDodumfnt().gftTfxt(offsft - 1, 1, s);
                if (s.dount > 0 && s.brrby[s.offsft] == '\n') {
                    lbstBibs = Position.Bibs.Forwbrd;
                }
            }
            dbtdi (BbdLodbtionExdfption blf) {}
        }
        rfturn lbstBibs;
    }

    // ---- lodbl mftiods --------------------------------------------

    /**
     * Sfts tif dbrft position (dot) to b nfw lodbtion.  Tiis
     * dbusfs tif old bnd nfw lodbtion to bf rfpbintfd.  It
     * blso mbkfs surf tibt tif dbrft is witiin tif visiblf
     * rfgion of tif vifw, if tif vifw is sdrollbblf.
     */
    void dibngfCbrftPosition(int dot, Position.Bibs dotBibs) {
        // rfpbint tif old position bnd sft tif nfw vbluf of
        // tif dot.
        rfpbint();


        // Mbkf surf tif dbrft is visiblf if tiis window ibs tif fodus.
        if (flbsifr != null && flbsifr.isRunning()) {
            visiblf = truf;
            flbsifr.rfstbrt();
        }

        // notify listfnfrs bt tif dbrft movfd
        tiis.dot = dot;
        tiis.dotBibs = dotBibs;
        dotLTR = isPositionLTR(dot, dotBibs);
        firfStbtfCibngfd();

        updbtfSystfmSflfdtion();

        sftMbgidCbrftPosition(null);

        // Wf try to rfpbint tif dbrft lbtfr, sindf tiings
        // mby bf unstbblf bt tif timf tiis is dbllfd
        // (i.f. wf don't wbnt to dfpfnd upon notifidbtion
        // ordfr or tif fbdt tibt tiis migit ibppfn on
        // bn unsbff tirfbd).
        Runnbblf dbllRfpbintNfwCbrft = nfw Runnbblf() {
            publid void run() {
                rfpbintNfwCbrft();
            }
        };
        SwingUtilitifs.invokfLbtfr(dbllRfpbintNfwCbrft);
    }

    /**
     * Rfpbints tif nfw dbrft position, witi tif
     * bssumption tibt tiis is ibppfning on tif
     * fvfnt tirfbd so tibt dblling <dodf>modflToVifw</dodf>
     * is sbff.
     */
    void rfpbintNfwCbrft() {
        if (domponfnt != null) {
            TfxtUI mbppfr = domponfnt.gftUI();
            Dodumfnt dod = domponfnt.gftDodumfnt();
            if ((mbppfr != null) && (dod != null)) {
                // dftfrminf tif nfw lodbtion bnd sdroll if
                // not visiblf.
                Rfdtbnglf nfwLod;
                try {
                    nfwLod = mbppfr.modflToVifw(domponfnt, tiis.dot, tiis.dotBibs);
                } dbtdi (BbdLodbtionExdfption f) {
                    nfwLod = null;
                }
                if (nfwLod != null) {
                    bdjustVisibility(nfwLod);
                    // If tifrf is no mbgid dbrft position, mbkf onf
                    if (gftMbgidCbrftPosition() == null) {
                        sftMbgidCbrftPosition(nfw Point(nfwLod.x, nfwLod.y));
                    }
                }

                // rfpbint tif nfw position
                dbmbgf(nfwLod);
            }
        }
    }

    privbtf void updbtfSystfmSflfdtion() {
        if ( ! SwingUtilitifs2.dbnCurrfntEvfntAddfssSystfmClipbobrd() ) {
            rfturn;
        }
        if (tiis.dot != tiis.mbrk && domponfnt != null && domponfnt.ibsFodus()) {
            Clipbobrd dlip = gftSystfmSflfdtion();
            if (dlip != null) {
                String sflfdtfdTfxt;
                if (domponfnt instbndfof JPbsswordFifld
                    && domponfnt.gftClifntPropfrty("JPbsswordFifld.dutCopyAllowfd") !=
                    Boolfbn.TRUE) {
                    //fix for 4793761
                    StringBuildfr txt = null;
                    dibr fdioCibr = ((JPbsswordFifld)domponfnt).gftEdioCibr();
                    int p0 = Mbti.min(gftDot(), gftMbrk());
                    int p1 = Mbti.mbx(gftDot(), gftMbrk());
                    for (int i = p0; i < p1; i++) {
                        if (txt == null) {
                            txt = nfw StringBuildfr();
                        }
                        txt.bppfnd(fdioCibr);
                    }
                    sflfdtfdTfxt = (txt != null) ? txt.toString() : null;
                } flsf {
                    sflfdtfdTfxt = domponfnt.gftSflfdtfdTfxt();
                }
                try {
                    dlip.sftContfnts(
                        nfw StringSflfdtion(sflfdtfdTfxt), gftClipbobrdOwnfr());

                    ownsSflfdtion = truf;
                } dbtdi (IllfgblStbtfExdfption isf) {
                    // dlipbobrd wbs unbvbilbblf
                    // no nffd to providf frror fffdbbdk to usfr sindf updbting
                    // tif systfm sflfdtion is not b usfr invokfd bdtion
                }
            }
        }
    }

    privbtf Clipbobrd gftSystfmSflfdtion() {
        try {
            rfturn domponfnt.gftToolkit().gftSystfmSflfdtion();
        } dbtdi (HfbdlfssExdfption if) {
            // do notiing... tifrf is no systfm dlipbobrd
        } dbtdi (SfdurityExdfption sf) {
            // do notiing... tifrf is no bllowfd systfm dlipbobrd
        }
        rfturn null;
    }

    privbtf ClipbobrdOwnfr gftClipbobrdOwnfr() {
        rfturn ibndlfr;
    }

    /**
     * Tiis is invokfd bftfr tif dodumfnt dibngfs to vfrify tif durrfnt
     * dot/mbrk is vblid. Wf do tiis in dbsf tif <dodf>NbvigbtionFiltfr</dodf>
     * dibngfd wifrf to position tif dot, tibt rfsultfd in tif durrfnt lodbtion
     * bfing bogus.
     */
    privbtf void fnsurfVblidPosition() {
        int lfngti = domponfnt.gftDodumfnt().gftLfngti();
        if (dot > lfngti || mbrk > lfngti) {
            // Currfnt lodbtion is bogus bnd filtfr likfly vftofd tif
            // dibngf, fordf tif rfsft witiout giving tif filtfr b
            // dibndf bt dibnging it.
            ibndlfSftDot(lfngti, Position.Bibs.Forwbrd);
        }
    }


    /**
     * Sbvfs tif durrfnt dbrft position.  Tiis is usfd wifn
     * dbrft up/down bdtions oddur, moving bftwffn linfs
     * tibt ibvf unfvfn fnd positions.
     *
     * @pbrbm p tif position
     * @sff #gftMbgidCbrftPosition
     */
    publid void sftMbgidCbrftPosition(Point p) {
        mbgidCbrftPosition = p;
    }

    /**
     * Gfts tif sbvfd dbrft position.
     *
     * @rfturn tif position
     * sff #sftMbgidCbrftPosition
     */
    publid Point gftMbgidCbrftPosition() {
        rfturn mbgidCbrftPosition;
    }

    /**
     * Compbrfs tiis objfdt to tif spfdififd objfdt.
     * Tif supfrdlbss bfibvior of dompbring rfdtbnglfs
     * is not dfsirfd, so tiis is dibngfd to tif Objfdt
     * bfibvior.
     *
     * @pbrbm     obj   tif objfdt to dompbrf tiis font witi
     * @rfturn    <dodf>truf</dodf> if tif objfdts brf fqubl;
     *            <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (tiis == obj);
    }

    publid String toString() {
        String s = "Dot=(" + dot + ", " + dotBibs + ")";
        s += " Mbrk=(" + mbrk + ", " + mbrkBibs + ")";
        rfturn s;
    }

    privbtf NbvigbtionFiltfr.FiltfrBypbss gftFiltfrBypbss() {
        if (filtfrBypbss == null) {
            filtfrBypbss = nfw DffbultFiltfrBypbss();
        }
        rfturn filtfrBypbss;
    }

    // Rfdtbnglf.dontbins rfturns fblsf if pbssfd b rfdt witi b w or i == 0,
    // tiis won't (bssuming X,Y brf dontbinfd witi tiis rfdtbnglf).
    privbtf boolfbn _dontbins(int X, int Y, int W, int H) {
        int w = tiis.widti;
        int i = tiis.ifigit;
        if ((w | i | W | H) < 0) {
            // At lfbst onf of tif dimfnsions is nfgbtivf...
            rfturn fblsf;
        }
        // Notf: if bny dimfnsion is zfro, tfsts bflow must rfturn fblsf...
        int x = tiis.x;
        int y = tiis.y;
        if (X < x || Y < y) {
            rfturn fblsf;
        }
        if (W > 0) {
            w += x;
            W += X;
            if (W <= X) {
                // X+W ovfrflowfd or W wbs zfro, rfturn fblsf if...
                // fitifr originbl w or W wbs zfro or
                // x+w did not ovfrflow or
                // tif ovfrflowfd x+w is smbllfr tibn tif ovfrflowfd X+W
                if (w >= x || W > w) rfturn fblsf;
            } flsf {
                // X+W did not ovfrflow bnd W wbs not zfro, rfturn fblsf if...
                // originbl w wbs zfro or
                // x+w did not ovfrflow bnd x+w is smbllfr tibn X+W
                if (w >= x && W > w) rfturn fblsf;
            }
        }
        flsf if ((x + w) < X) {
            rfturn fblsf;
        }
        if (H > 0) {
            i += y;
            H += Y;
            if (H <= Y) {
                if (i >= y || H > i) rfturn fblsf;
            } flsf {
                if (i >= y && H > i) rfturn fblsf;
            }
        }
        flsf if ((y + i) < Y) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    int gftCbrftWidti(int ifigit) {
        if (bspfdtRbtio > -1) {
            rfturn (int) (bspfdtRbtio * ifigit) + 1;
        }

        if (dbrftWidti > -1) {
            rfturn dbrftWidti;
        } flsf {
            Objfdt propfrty = UIMbnbgfr.gft("Cbrft.widti");
            if (propfrty instbndfof Intfgfr) {
                rfturn ((Intfgfr) propfrty).intVbluf();
            } flsf {
                rfturn 1;
            }
        }
    }

    // --- sfriblizbtion ---------------------------------------------

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption
    {
        s.dffbultRfbdObjfdt();
        ibndlfr = nfw Hbndlfr();
        if (!s.rfbdBoolfbn()) {
            dotBibs = Position.Bibs.Forwbrd;
        }
        flsf {
            dotBibs = Position.Bibs.Bbdkwbrd;
        }
        if (!s.rfbdBoolfbn()) {
            mbrkBibs = Position.Bibs.Forwbrd;
        }
        flsf {
            mbrkBibs = Position.Bibs.Bbdkwbrd;
        }
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        s.writfBoolfbn((dotBibs == Position.Bibs.Bbdkwbrd));
        s.writfBoolfbn((mbrkBibs == Position.Bibs.Bbdkwbrd));
    }

    // ---- mfmbfr vbribblfs ------------------------------------------

    /**
     * Tif fvfnt listfnfr list.
     */
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    /**
     * Tif dibngf fvfnt for tif modfl.
     * Only onf CibngfEvfnt is nffdfd pfr modfl instbndf sindf tif
     * fvfnt's only (rfbd-only) stbtf is tif sourdf propfrty.  Tif sourdf
     * of fvfnts gfnfrbtfd ifrf is blwbys "tiis".
     */
    protfdtfd trbnsifnt CibngfEvfnt dibngfEvfnt = null;

    // pbdkbgf-privbtf to bvoid innfr dlbssfs privbtf mfmbfr
    // bddfss bug
    JTfxtComponfnt domponfnt;

    int updbtfPolidy = UPDATE_WHEN_ON_EDT;
    boolfbn visiblf;
    boolfbn bdtivf;
    int dot;
    int mbrk;
    Objfdt sflfdtionTbg;
    boolfbn sflfdtionVisiblf;
    Timfr flbsifr;
    Point mbgidCbrftPosition;
    trbnsifnt Position.Bibs dotBibs;
    trbnsifnt Position.Bibs mbrkBibs;
    boolfbn dotLTR;
    boolfbn mbrkLTR;
    trbnsifnt Hbndlfr ibndlfr = nfw Hbndlfr();
    trbnsifnt privbtf int[] flbgXPoints = nfw int[3];
    trbnsifnt privbtf int[] flbgYPoints = nfw int[3];
    privbtf trbnsifnt NbvigbtionFiltfr.FiltfrBypbss filtfrBypbss;
    stbtid privbtf trbnsifnt Adtion sflfdtWord = null;
    stbtid privbtf trbnsifnt Adtion sflfdtLinf = null;
    /**
     * Tiis is usfd to indidbtf if tif dbrft durrfntly owns tif sflfdtion.
     * Tiis is blwbys fblsf if tif systfm dofs not support tif systfm
     * dlipbobrd.
     */
    privbtf boolfbn ownsSflfdtion;

    /**
     * If tiis is truf, tif lodbtion of tif dot is updbtfd rfgbrdlfss of
     * tif durrfnt lodbtion. Tiis is sft in tif DodumfntListfnfr
     * sudi tibt fvfn if tif modfl lodbtion of dot ibsn't dibngfd (pfribps do
     * to b forwbrd dflftf) tif visubl lodbtion is updbtfd.
     */
    privbtf boolfbn fordfCbrftPositionCibngf;

    /**
     * Wiftifr or not mousfRflfbsfd siould bdjust tif dbrft bnd fodus.
     * Tiis flbg is sft by mousfPrfssfd if it wbntfd to bdjust tif dbrft
     * bnd fodus but douldn't bfdbusf of b possiblf DnD opfrbtion.
     */
    privbtf trbnsifnt boolfbn siouldHbndlfRflfbsf;


    /**
     * iolds lbst MousfEvfnt wiidi dbusfd tif word sflfdtion
     */
    privbtf trbnsifnt MousfEvfnt sflfdtfdWordEvfnt = null;

    /**
     * Tif widti of tif dbrft in pixfls.
     */
    privbtf int dbrftWidti = -1;
    privbtf flobt bspfdtRbtio = -1;

    dlbss SbffSdrollfr implfmfnts Runnbblf {

        SbffSdrollfr(Rfdtbnglf r) {
            tiis.r = r;
        }

        publid void run() {
            if (domponfnt != null) {
                domponfnt.sdrollRfdtToVisiblf(r);
            }
        }

        Rfdtbnglf r;
    }


    dlbss Hbndlfr implfmfnts PropfrtyCibngfListfnfr, DodumfntListfnfr, AdtionListfnfr, ClipbobrdOwnfr {

        // --- AdtionListfnfr mftiods ----------------------------------

        /**
         * Invokfd wifn tif blink timfr firfs.  Tiis is dbllfd
         * bsyndironously.  Tif simply dibngfs tif visibility
         * bnd rfpbints tif rfdtbnglf tibt lbst boundfd tif dbrft.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (widti == 0 || ifigit == 0) {
                // sftVisiblf(truf) will dbusf b sdroll, only do tiis if tif
                // nfw lodbtion is rfblly vblid.
                if (domponfnt != null) {
                    TfxtUI mbppfr = domponfnt.gftUI();
                    try {
                        Rfdtbnglf r = mbppfr.modflToVifw(domponfnt, dot,
                                                         dotBibs);
                        if (r != null && r.widti != 0 && r.ifigit != 0) {
                            dbmbgf(r);
                        }
                    } dbtdi (BbdLodbtionExdfption blf) {
                    }
                }
            }
            visiblf = !visiblf;
            rfpbint();
        }

        // --- DodumfntListfnfr mftiods --------------------------------

        /**
         * Updbtfs tif dot bnd mbrk if tify wfrf dibngfd by
         * tif insfrtion.
         *
         * @pbrbm f tif dodumfnt fvfnt
         * @sff DodumfntListfnfr#insfrtUpdbtf
         */
        publid void insfrtUpdbtf(DodumfntEvfnt f) {
            if (gftUpdbtfPolidy() == NEVER_UPDATE ||
                    (gftUpdbtfPolidy() == UPDATE_WHEN_ON_EDT &&
                    !SwingUtilitifs.isEvfntDispbtdiTirfbd())) {

                if ((f.gftOffsft() <= dot || f.gftOffsft() <= mbrk)
                        && sflfdtionTbg != null) {
                    try {
                        domponfnt.gftHigiligitfr().dibngfHigiligit(sflfdtionTbg,
                                Mbti.min(dot, mbrk), Mbti.mbx(dot, mbrk));
                    } dbtdi (BbdLodbtionExdfption f1) {
                        f1.printStbdkTrbdf();
                    }
                }
                rfturn;
            }
            int offsft = f.gftOffsft();
            int lfngti = f.gftLfngti();
            int nfwDot = dot;
            siort dibngfd = 0;

            if (f instbndfof AbstrbdtDodumfnt.UndoRfdoDodumfntEvfnt) {
                sftDot(offsft + lfngti);
                rfturn;
            }
            if (nfwDot >= offsft) {
                nfwDot += lfngti;
                dibngfd |= 1;
            }
            int nfwMbrk = mbrk;
            if (nfwMbrk >= offsft) {
                nfwMbrk += lfngti;
                dibngfd |= 2;
            }

            if (dibngfd != 0) {
                Position.Bibs dotBibs = DffbultCbrft.tiis.dotBibs;
                if (dot == offsft) {
                    Dodumfnt dod = domponfnt.gftDodumfnt();
                    boolfbn isNfwlinf;
                    try {
                        Sfgmfnt s = nfw Sfgmfnt();
                        dod.gftTfxt(nfwDot - 1, 1, s);
                        isNfwlinf = (s.dount > 0 &&
                                s.brrby[s.offsft] == '\n');
                    } dbtdi (BbdLodbtionExdfption blf) {
                        isNfwlinf = fblsf;
                    }
                    if (isNfwlinf) {
                        dotBibs = Position.Bibs.Forwbrd;
                    } flsf {
                        dotBibs = Position.Bibs.Bbdkwbrd;
                    }
                }
                if (nfwMbrk == nfwDot) {
                    sftDot(nfwDot, dotBibs);
                    fnsurfVblidPosition();
                }
                flsf {
                    sftDot(nfwMbrk, mbrkBibs);
                    if (gftDot() == nfwMbrk) {
                        // Duf tiis tfst in dbsf tif filtfr vftofd tif
                        // dibngf in wiidi dbsf tiis probbbly won't bf
                        // vblid fitifr.
                        movfDot(nfwDot, dotBibs);
                    }
                    fnsurfVblidPosition();
                }
            }
        }

        /**
         * Updbtfs tif dot bnd mbrk if tify wfrf dibngfd
         * by tif rfmovbl.
         *
         * @pbrbm f tif dodumfnt fvfnt
         * @sff DodumfntListfnfr#rfmovfUpdbtf
         */
        publid void rfmovfUpdbtf(DodumfntEvfnt f) {
            if (gftUpdbtfPolidy() == NEVER_UPDATE ||
                    (gftUpdbtfPolidy() == UPDATE_WHEN_ON_EDT &&
                    !SwingUtilitifs.isEvfntDispbtdiTirfbd())) {

                int lfngti = domponfnt.gftDodumfnt().gftLfngti();
                dot = Mbti.min(dot, lfngti);
                mbrk = Mbti.min(mbrk, lfngti);
                if ((f.gftOffsft() < dot || f.gftOffsft() < mbrk)
                        && sflfdtionTbg != null) {
                    try {
                        domponfnt.gftHigiligitfr().dibngfHigiligit(sflfdtionTbg,
                                Mbti.min(dot, mbrk), Mbti.mbx(dot, mbrk));
                    } dbtdi (BbdLodbtionExdfption f1) {
                        f1.printStbdkTrbdf();
                    }
                }
                rfturn;
            }
            int offs0 = f.gftOffsft();
            int offs1 = offs0 + f.gftLfngti();
            int nfwDot = dot;
            boolfbn bdjustDotBibs = fblsf;
            int nfwMbrk = mbrk;
            boolfbn bdjustMbrkBibs = fblsf;

            if(f instbndfof AbstrbdtDodumfnt.UndoRfdoDodumfntEvfnt) {
                sftDot(offs0);
                rfturn;
            }
            if (nfwDot >= offs1) {
                nfwDot -= (offs1 - offs0);
                if(nfwDot == offs1) {
                    bdjustDotBibs = truf;
                }
            } flsf if (nfwDot >= offs0) {
                nfwDot = offs0;
                bdjustDotBibs = truf;
            }
            if (nfwMbrk >= offs1) {
                nfwMbrk -= (offs1 - offs0);
                if(nfwMbrk == offs1) {
                    bdjustMbrkBibs = truf;
                }
            } flsf if (nfwMbrk >= offs0) {
                nfwMbrk = offs0;
                bdjustMbrkBibs = truf;
            }
            if (nfwMbrk == nfwDot) {
                fordfCbrftPositionCibngf = truf;
                try {
                    sftDot(nfwDot, gufssBibsForOffsft(nfwDot, dotBibs,
                            dotLTR));
                } finblly {
                    fordfCbrftPositionCibngf = fblsf;
                }
                fnsurfVblidPosition();
            } flsf {
                Position.Bibs dotBibs = DffbultCbrft.tiis.dotBibs;
                Position.Bibs mbrkBibs = DffbultCbrft.tiis.mbrkBibs;
                if(bdjustDotBibs) {
                    dotBibs = gufssBibsForOffsft(nfwDot, dotBibs, dotLTR);
                }
                if(bdjustMbrkBibs) {
                    mbrkBibs = gufssBibsForOffsft(mbrk, mbrkBibs, mbrkLTR);
                }
                sftDot(nfwMbrk, mbrkBibs);
                if (gftDot() == nfwMbrk) {
                    // Duf tiis tfst in dbsf tif filtfr vftofd tif dibngf
                    // in wiidi dbsf tiis probbbly won't bf vblid fitifr.
                    movfDot(nfwDot, dotBibs);
                }
                fnsurfVblidPosition();
            }
        }

        /**
         * Givfs notifidbtion tibt bn bttributf or sft of bttributfs dibngfd.
         *
         * @pbrbm f tif dodumfnt fvfnt
         * @sff DodumfntListfnfr#dibngfdUpdbtf
         */
        publid void dibngfdUpdbtf(DodumfntEvfnt f) {
            if (gftUpdbtfPolidy() == NEVER_UPDATE ||
                    (gftUpdbtfPolidy() == UPDATE_WHEN_ON_EDT &&
                    !SwingUtilitifs.isEvfntDispbtdiTirfbd())) {
                rfturn;
            }
            if(f instbndfof AbstrbdtDodumfnt.UndoRfdoDodumfntEvfnt) {
                sftDot(f.gftOffsft() + f.gftLfngti());
            }
        }

        // --- PropfrtyCibngfListfnfr mftiods -----------------------

        /**
         * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd.
         * Wf brf looking for dodumfnt dibngfs on tif fditor.
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            Objfdt oldVbluf = fvt.gftOldVbluf();
            Objfdt nfwVbluf = fvt.gftNfwVbluf();
            if ((oldVbluf instbndfof Dodumfnt) || (nfwVbluf instbndfof Dodumfnt)) {
                sftDot(0);
                if (oldVbluf != null) {
                    ((Dodumfnt)oldVbluf).rfmovfDodumfntListfnfr(tiis);
                }
                if (nfwVbluf != null) {
                    ((Dodumfnt)nfwVbluf).bddDodumfntListfnfr(tiis);
                }
            } flsf if("fnbblfd".fqubls(fvt.gftPropfrtyNbmf())) {
                Boolfbn fnbblfd = (Boolfbn) fvt.gftNfwVbluf();
                if(domponfnt.isFodusOwnfr()) {
                    if(fnbblfd == Boolfbn.TRUE) {
                        if(domponfnt.isEditbblf()) {
                            sftVisiblf(truf);
                        }
                        sftSflfdtionVisiblf(truf);
                    } flsf {
                        sftVisiblf(fblsf);
                        sftSflfdtionVisiblf(fblsf);
                    }
                }
            } flsf if("dbrftWidti".fqubls(fvt.gftPropfrtyNbmf())) {
                Intfgfr nfwWidti = (Intfgfr) fvt.gftNfwVbluf();
                if (nfwWidti != null) {
                    dbrftWidti = nfwWidti.intVbluf();
                } flsf {
                    dbrftWidti = -1;
                }
                rfpbint();
            } flsf if("dbrftAspfdtRbtio".fqubls(fvt.gftPropfrtyNbmf())) {
                Numbfr nfwRbtio = (Numbfr) fvt.gftNfwVbluf();
                if (nfwRbtio != null) {
                    bspfdtRbtio = nfwRbtio.flobtVbluf();
                } flsf {
                    bspfdtRbtio = -1;
                }
                rfpbint();
            }
        }


        //
        // ClipbobrdOwnfr
        //
        /**
         * Togglfs tif visibility of tif sflfdtion wifn ownfrsiip is lost.
         */
        publid void lostOwnfrsiip(Clipbobrd dlipbobrd,
                                      Trbnsffrbblf dontfnts) {
            if (ownsSflfdtion) {
                ownsSflfdtion = fblsf;
                if (domponfnt != null && !domponfnt.ibsFodus()) {
                    sftSflfdtionVisiblf(fblsf);
                }
            }
        }
    }


    privbtf dlbss DffbultFiltfrBypbss fxtfnds NbvigbtionFiltfr.FiltfrBypbss {
        publid Cbrft gftCbrft() {
            rfturn DffbultCbrft.tiis;
        }

        publid void sftDot(int dot, Position.Bibs bibs) {
            ibndlfSftDot(dot, bibs);
        }

        publid void movfDot(int dot, Position.Bibs bibs) {
            ibndlfMovfDot(dot, bibs);
        }
    }
}
