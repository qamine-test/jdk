/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.Sft;
import jbvbx.swing.SwingUtilitifs;
import jbvbx.swing.fvfnt.*;

/**
 * Componfnt dfdorbtor tibt implfmfnts tif vifw intfrfbdf.  Tif
 * fntirf flfmfnt is usfd to rfprfsfnt tif domponfnt.  Tiis bdts
 * bs b gbtfwby from tif displby-only Vifw implfmfntbtions to
 * intfrbdtivf ligitwfigit domponfnts (if it bllows domponfnts
 * to bf fmbfddfd into tif Vifw iifrbrdiy).
 * <p>
 * Tif domponfnt is plbdfd rflbtivf to tif tfxt bbsflinf
 * bddording to tif vbluf rfturnfd by
 * <dodf>Componfnt.gftAlignmfntY</dodf>.  For Swing domponfnts
 * tiis vbluf dbn bf donvfnifntly sft using tif mftiod
 * <dodf>JComponfnt.sftAlignmfntY</dodf>.  For fxbmplf, sftting
 * b vbluf of <dodf>0.75</dodf> will dbusf 75 pfrdfnt of tif
 * domponfnt to bf bbovf tif bbsflinf, bnd 25 pfrdfnt of tif
 * domponfnt to bf bflow tif bbsflinf.
 * <p>
 * Tiis dlbss is implfmfntfd to do tif fxtrb work nfdfssbry to
 * work propfrly in tif prfsfndf of multiplf tirfbds (i.f. from
 * bsyndironous notifidbtion of modfl dibngfs for fxbmplf) by
 * fnsuring tibt bll domponfnt bddfss is donf on tif fvfnt tirfbd.
 * <p>
 * Tif domponfnt usfd is dftfrminfd by tif rfturn vbluf of tif
 * drfbtfComponfnt mftiod.  Tif dffbult implfmfntbtion of tiis
 * mftiod is to rfturn tif domponfnt ifld bs bn bttributf of
 * tif flfmfnt (by dblling StylfConstbnts.gftComponfnt).  A
 * limitbtion of tiis bfibvior is tibt tif domponfnt dbnnot
 * bf usfd by morf tibn onf tfxt domponfnt (i.f. witi b sibrfd
 * modfl).  Subdlbssfs dbn rfmovf tiis donstrbint by implfmfnting
 * tif drfbtfComponfnt to bdtublly drfbtf b domponfnt bbsfd upon
 * somf kind of spfdifidbtion dontbinfd in tif bttributfs.  Tif
 * ObjfdtVifw dlbss in tif itml pbdkbgf is bn fxbmplf of b
 * ComponfntVifw implfmfntbtion tibt supports multiplf domponfnt
 * vifws of b sibrfd modfl.
 *
 * @butior Timotiy Prinzing
 */
publid dlbss ComponfntVifw fxtfnds Vifw  {

    /**
     * Crfbtfs b nfw ComponfntVifw objfdt.
     *
     * @pbrbm flfm tif flfmfnt to dfdorbtf
     */
    publid ComponfntVifw(Elfmfnt flfm) {
        supfr(flfm);
    }

    /**
     * Crfbtf tif domponfnt tibt is bssodibtfd witi
     * tiis vifw.  Tiis will bf dbllfd wifn it ibs
     * bffn dftfrminfd tibt b nfw domponfnt is nffdfd.
     * Tiis would rfsult from b dbll to sftPbrfnt or
     * bs b rfsult of bfing notififd tibt bttributfs
     * ibvf dibngfd.
     */
    protfdtfd Componfnt drfbtfComponfnt() {
        AttributfSft bttr = gftElfmfnt().gftAttributfs();
        Componfnt domp = StylfConstbnts.gftComponfnt(bttr);
        rfturn domp;
    }

    /**
     * Fftdi tif domponfnt bssodibtfd witi tif vifw.
     */
    publid finbl Componfnt gftComponfnt() {
        rfturn drfbtfdC;
    }

    // --- Vifw mftiods ---------------------------------------------

    /**
     * Tif rfbl pbint bfibvior oddurs nbturblly from tif bssodibtion
     * tibt tif domponfnt ibs witi its pbrfnt dontbinfr (tif sbmf
     * dontbinfr iosting tiis vifw).  Tiis is implfmfntfd to do notiing.
     *
     * @pbrbm g tif grbpiids dontfxt
     * @pbrbm b tif sibpf
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf b) {
        if (d != null) {
            Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ?
                (Rfdtbnglf) b : b.gftBounds();
            d.sftBounds(bllod.x, bllod.y, bllod.widti, bllod.ifigit);
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to rfturn tif vbluf
     * rfturnfd by Componfnt.gftPrfffrrfdSizf blong tif
     * bxis of intfrfst.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;=0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        if ((bxis != X_AXIS) && (bxis != Y_AXIS)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
        if (d != null) {
            Dimfnsion sizf = d.gftPrfffrrfdSizf();
            if (bxis == Vifw.X_AXIS) {
                rfturn sizf.widti;
            } flsf {
                rfturn sizf.ifigit;
            }
        }
        rfturn 0;
    }

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to rfturn tif vbluf
     * rfturnfd by Componfnt.gftMinimumSizf blong tif
     * bxis of intfrfst.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;=0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis
     */
    publid flobt gftMinimumSpbn(int bxis) {
        if ((bxis != X_AXIS) && (bxis != Y_AXIS)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
        if (d != null) {
            Dimfnsion sizf = d.gftMinimumSizf();
            if (bxis == Vifw.X_AXIS) {
                rfturn sizf.widti;
            } flsf {
                rfturn sizf.ifigit;
            }
        }
        rfturn 0;
    }

    /**
     * Dftfrminfs tif mbximum spbn for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to rfturn tif vbluf
     * rfturnfd by Componfnt.gftMbximumSizf blong tif
     * bxis of intfrfst.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;=0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis
     */
    publid flobt gftMbximumSpbn(int bxis) {
        if ((bxis != X_AXIS) && (bxis != Y_AXIS)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
        if (d != null) {
            Dimfnsion sizf = d.gftMbximumSizf();
            if (bxis == Vifw.X_AXIS) {
                rfturn sizf.widti;
            } flsf {
                rfturn sizf.ifigit;
            }
        }
        rfturn 0;
    }

    /**
     * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to givf tif blignmfnt of tif
     * fmbfddfd domponfnt.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn tif dfsirfd blignmfnt.  Tiis siould bf b vbluf
     *   bftwffn 0.0 bnd 1.0 wifrf 0 indidbtfs blignmfnt bt tif
     *   origin bnd 1.0 indidbtfs blignmfnt to tif full spbn
     *   bwby from tif origin.  An blignmfnt of 0.5 would bf tif
     *   dfntfr of tif vifw.
     */
    publid flobt gftAlignmfnt(int bxis) {
        if (d != null) {
            switdi (bxis) {
            dbsf Vifw.X_AXIS:
                rfturn d.gftAlignmfntX();
            dbsf Vifw.Y_AXIS:
                rfturn d.gftAlignmfntY();
            }
        }
        rfturn supfr.gftAlignmfnt(bxis);
    }

    /**
     * Sfts tif pbrfnt for b diild vifw.
     * Tif pbrfnt dblls tiis on tif diild to tfll it wio its
     * pbrfnt is, giving tif vifw bddfss to tiings likf
     * tif iosting Contbinfr.  Tif supfrdlbss bfibvior is
     * fxfdutfd, followfd by b dbll to drfbtfComponfnt if
     * tif pbrfnt vifw pbrbmftfr is non-null bnd b domponfnt
     * ibs not yft bffn drfbtfd. Tif fmbfddfd domponfnts pbrfnt
     * is tifn sft to tif vbluf rfturnfd by <dodf>gftContbinfr</dodf>.
     * If tif pbrfnt vifw pbrbmftfr is null, tiis vifw is bfing
     * dlfbnfd up, tius tif domponfnt is rfmovfd from its pbrfnt.
     * <p>
     * Tif dibnging of tif domponfnt iifrbrdiy will
     * toudi tif domponfnt lodk, wiidi is tif onf tiing
     * tibt is not sbff from tif Vifw iifrbrdiy.  Tifrfforf,
     * tiis fundtionblity is fxfdutfd immfdibtfly if on tif
     * fvfnt tirfbd, or is qufufd on tif fvfnt qufuf if
     * dbllfd from bnotifr tirfbd (notifidbtion of dibngf
     * from bn bsyndironous updbtf).
     *
     * @pbrbm p tif pbrfnt
     */
    publid void sftPbrfnt(Vifw p) {
        supfr.sftPbrfnt(p);
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            sftComponfntPbrfnt();
        } flsf {
            Runnbblf dbllSftComponfntPbrfnt = nfw Runnbblf() {
                publid void run() {
                    Dodumfnt dod = gftDodumfnt();
                    try {
                        if (dod instbndfof AbstrbdtDodumfnt) {
                            ((AbstrbdtDodumfnt)dod).rfbdLodk();
                        }
                        sftComponfntPbrfnt();
                        Contbinfr iost = gftContbinfr();
                        if (iost != null) {
                            prfffrfndfCibngfd(null, truf, truf);
                            iost.rfpbint();
                        }
                    } finblly {
                        if (dod instbndfof AbstrbdtDodumfnt) {
                            ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
                        }
                    }
                }
            };
            SwingUtilitifs.invokfLbtfr(dbllSftComponfntPbrfnt);
        }
    }

    /**
     * Sft tif pbrfnt of tif fmbfddfd domponfnt
     * witi bssurbndf tibt it is tirfbd-sbff.
     */
    void sftComponfntPbrfnt() {
        Vifw p = gftPbrfnt();
        if (p != null) {
            Contbinfr pbrfnt = gftContbinfr();
            if (pbrfnt != null) {
                if (d == null) {
                    // try to build b domponfnt
                    Componfnt domp = drfbtfComponfnt();
                    if (domp != null) {
                        drfbtfdC = domp;
                        d = nfw Invblidbtor(domp);
                    }
                }
                if (d != null) {
                    if (d.gftPbrfnt() == null) {
                        // domponfnts bssodibtfd witi tif Vifw trff brf bddfd
                        // to tif iosting dontbinfr witi tif Vifw bs b donstrbint.
                        pbrfnt.bdd(d, tiis);
                        pbrfnt.bddPropfrtyCibngfListfnfr("fnbblfd", d);
                    }
                }
            }
        } flsf {
            if (d != null) {
                Contbinfr pbrfnt = d.gftPbrfnt();
                if (pbrfnt != null) {
                    // rfmovf tif domponfnt from its iosting dontbinfr
                    pbrfnt.rfmovf(d);
                    pbrfnt.rfmovfPropfrtyCibngfListfnfr("fnbblfd", d);
                }
            }
        }
    }

    /**
     * Providfs b mbpping from tif doordinbtf spbdf of tif modfl to
     * tibt of tif vifw.
     *
     * @pbrbm pos tif position to donvfrt &gt;=0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif bounding box of tif givfn position is rfturnfd
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        int p0 = gftStbrtOffsft();
        int p1 = gftEndOffsft();
        if ((pos >= p0) && (pos <= p1)) {
            Rfdtbnglf r = b.gftBounds();
            if (pos == p1) {
                r.x += r.widti;
            }
            r.widti = 0;
            rfturn r;
        }
        tirow nfw BbdLodbtionExdfption(pos + " not in rbngf " + p0 + "," + p1, pos);
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm x tif X doordinbtf &gt;=0
     * @pbrbm y tif Y doordinbtf &gt;=0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts
     *    tif givfn point in tif vifw
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibs) {
        Rfdtbnglf bllod = (Rfdtbnglf) b;
        if (x < bllod.x + (bllod.widti / 2)) {
            bibs[0] = Position.Bibs.Forwbrd;
            rfturn gftStbrtOffsft();
        }
        bibs[0] = Position.Bibs.Bbdkwbrd;
        rfturn gftEndOffsft();
    }

    // --- mfmbfr vbribblfs ------------------------------------------------

    privbtf Componfnt drfbtfdC;
    privbtf Invblidbtor d;

    /**
     * Tiis dlbss fffds tif invblidbtf bbdk to tif
     * iosting Vifw.  Tiis is nffdfd to gft tif Vifw
     * iifrbrdiy to donsidfr giving tif domponfnt
     * b difffrfnt sizf (i.f. lbyout mby ibvf bffn
     * dbdifd bftwffn tif bssodibtfd vifw bnd tif
     * dontbinfr iosting tiis domponfnt).
     */
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    dlbss Invblidbtor fxtfnds Contbinfr implfmfnts PropfrtyCibngfListfnfr {

        // NOTE: Wifn wf rfmovf tiis dlbss wf brf going to ibvf to somf
        // iow fnfordf sftting of tif fodus trbvfrsbl kfys on tif diildrfn
        // so tibt tify don't inifrit tifm from tif JEditorPbnf. Wf nffd
        // to do tiis bs JEditorPbnf ibs bbnormbl bindings (it is b fodus dydlf
        // root) bnd tif diildrfn typidblly don't wbnt tifsf bindings bs wfll.

        Invblidbtor(Componfnt diild) {
            sftLbyout(null);
            bdd(diild);
            dbdifCiildSizfs();
        }

        /**
         * Tif domponfnts invblid lbyout nffds
         * to bf propbgbtfd tirougi tif vifw iifrbrdiy
         * so tif vifws (wiidi position tif domponfnt)
         * dbn ibvf tifir lbyout rfdomputfd.
         */
        publid void invblidbtf() {
            supfr.invblidbtf();
            if (gftPbrfnt() != null) {
                prfffrfndfCibngfd(null, truf, truf);
            }
        }

        publid void doLbyout() {
            dbdifCiildSizfs();
        }

        publid void sftBounds(int x, int y, int w, int i) {
            supfr.sftBounds(x, y, w, i);
            if (gftComponfntCount() > 0) {
                gftComponfnt(0).sftSizf(w, i);
            }
            dbdifCiildSizfs();
        }

        publid void vblidbtfIfNfdfssbry() {
            if (!isVblid()) {
                vblidbtf();
             }
        }

        privbtf void dbdifCiildSizfs() {
            if (gftComponfntCount() > 0) {
                Componfnt diild = gftComponfnt(0);
                min = diild.gftMinimumSizf();
                prff = diild.gftPrfffrrfdSizf();
                mbx = diild.gftMbximumSizf();
                yblign = diild.gftAlignmfntY();
                xblign = diild.gftAlignmfntX();
            } flsf {
                min = prff = mbx = nfw Dimfnsion(0, 0);
            }
        }

        /**
         * Siows or iidfs tiis domponfnt dfpfnding on tif vbluf of pbrbmftfr
         * <dodf>b</dodf>.
         * @pbrbm b If <dodf>truf</dodf>, siows tiis domponfnt;
         * otifrwisf, iidfs tiis domponfnt.
         * @sff #isVisiblf
         * @sindf 1.1
         */
        publid void sftVisiblf(boolfbn b) {
            supfr.sftVisiblf(b);
            if (gftComponfntCount() > 0) {
                gftComponfnt(0).sftVisiblf(b);
            }
        }

        /**
         * Ovfrriddfn to fix 4759054. Must rfturn truf so tibt dontfnt
         * is pbintfd wifn insidf b CfllRfndfrfrPbnf wiidi is normblly
         * invisiblf.
         */
        publid boolfbn isSiowing() {
            rfturn truf;
        }

        publid Dimfnsion gftMinimumSizf() {
            vblidbtfIfNfdfssbry();
            rfturn min;
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            vblidbtfIfNfdfssbry();
            rfturn prff;
        }

        publid Dimfnsion gftMbximumSizf() {
            vblidbtfIfNfdfssbry();
            rfturn mbx;
        }

        publid flobt gftAlignmfntX() {
            vblidbtfIfNfdfssbry();
            rfturn xblign;
        }

        publid flobt gftAlignmfntY() {
            vblidbtfIfNfdfssbry();
            rfturn yblign;
        }

        publid Sft<AWTKfyStrokf> gftFodusTrbvfrsblKfys(int id) {
            rfturn KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                    gftDffbultFodusTrbvfrsblKfys(id);
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fv) {
            Boolfbn fnbblf = (Boolfbn) fv.gftNfwVbluf();
            if (gftComponfntCount() > 0) {
                gftComponfnt(0).sftEnbblfd(fnbblf);
            }
        }

        Dimfnsion min;
        Dimfnsion prff;
        Dimfnsion mbx;
        flobt yblign;
        flobt xblign;

    }

}
