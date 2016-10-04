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

import jbvb.util.EvfntObjfdt;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.LigitwfigitPffr;
import jbvb.lbng.rfflfdt.Fifld;
import sun.bwt.AWTAddfssor;
import sun.util.logging.PlbtformLoggfr;

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;

/**
 * Tif root fvfnt dlbss for bll AWT fvfnts.
 * Tiis dlbss bnd its subdlbssfs supfrdfdf tif originbl
 * jbvb.bwt.Evfnt dlbss.
 * Subdlbssfs of tiis root AWTEvfnt dlbss dffinfd outsidf of tif
 * jbvb.bwt.fvfnt pbdkbgf siould dffinf fvfnt ID vblufs grfbtfr tibn
 * tif vbluf dffinfd by RESERVED_ID_MAX.
 * <p>
 * Tif fvfnt mbsks dffinfd in tiis dlbss brf nffdfd by Componfnt subdlbssfs
 * wiidi brf using Componfnt.fnbblfEvfnts() to sflfdt for fvfnt typfs not
 * sflfdtfd by rfgistfrfd listfnfrs. If b listfnfr is rfgistfrfd on b
 * domponfnt, tif bppropribtf fvfnt mbsk is blrfbdy sft intfrnblly by tif
 * domponfnt.
 * <p>
 * Tif mbsks brf blso usfd to spfdify to wiidi typfs of fvfnts bn
 * AWTEvfntListfnfr siould listfn. Tif mbsks brf bitwisf-ORfd togftifr
 * bnd pbssfd to Toolkit.bddAWTEvfntListfnfr.
 *
 * @sff Componfnt#fnbblfEvfnts
 * @sff Toolkit#bddAWTEvfntListfnfr
 *
 * @sff jbvb.bwt.fvfnt.AdtionEvfnt
 * @sff jbvb.bwt.fvfnt.AdjustmfntEvfnt
 * @sff jbvb.bwt.fvfnt.ComponfntEvfnt
 * @sff jbvb.bwt.fvfnt.ContbinfrEvfnt
 * @sff jbvb.bwt.fvfnt.FodusEvfnt
 * @sff jbvb.bwt.fvfnt.InputMftiodEvfnt
 * @sff jbvb.bwt.fvfnt.InvodbtionEvfnt
 * @sff jbvb.bwt.fvfnt.ItfmEvfnt
 * @sff jbvb.bwt.fvfnt.HifrbrdiyEvfnt
 * @sff jbvb.bwt.fvfnt.KfyEvfnt
 * @sff jbvb.bwt.fvfnt.MousfEvfnt
 * @sff jbvb.bwt.fvfnt.MousfWifflEvfnt
 * @sff jbvb.bwt.fvfnt.PbintEvfnt
 * @sff jbvb.bwt.fvfnt.TfxtEvfnt
 * @sff jbvb.bwt.fvfnt.WindowEvfnt
 *
 * @butior Cbrl Quinn
 * @butior Amy Fowlfr
 * @sindf 1.1
 */
publid bbstrbdt dlbss AWTEvfnt fxtfnds EvfntObjfdt {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.AWTEvfnt");
    privbtf bytf bdbtb[];

    /**
     * Tif fvfnt's id.
     * @sfribl
     * @sff #gftID()
     * @sff #AWTEvfnt
     */
    protfdtfd int id;

    /**
     * Controls wiftifr or not tif fvfnt is sfnt bbdk down to tif pffr ondf tif
     * sourdf ibs prodfssfd it - fblsf mfbns it's sfnt to tif pffr; truf mfbns
     * it's not. Sfmbntid fvfnts blwbys ibvf b 'truf' vbluf sindf tify wfrf
     * gfnfrbtfd by tif pffr in rfsponsf to b low-lfvfl fvfnt.
     * @sfribl
     * @sff #donsumf
     * @sff #isConsumfd
     */
    protfdtfd boolfbn donsumfd = fblsf;

   /*
    * Tif fvfnt's AddfssControlContfxt.
    */
    privbtf trbnsifnt volbtilf AddfssControlContfxt bdd =
        AddfssControllfr.gftContfxt();

   /*
    * Rfturns tif bdd tiis fvfnt wbs donstrudtfd witi.
    */
    finbl AddfssControlContfxt gftAddfssControlContfxt() {
        if (bdd == null) {
            tirow nfw SfdurityExdfption("AWTEvfnt is missing AddfssControlContfxt");
        }
        rfturn bdd;
    }

    trbnsifnt boolfbn fodusMbnbgfrIsDispbtdiing = fblsf;
    trbnsifnt boolfbn isPostfd;

    /**
     * Indidbtfs wiftifr tiis AWTEvfnt wbs gfnfrbtfd by tif systfm bs
     * opposfd to by usfr dodf.
     */
    privbtf trbnsifnt boolfbn isSystfmGfnfrbtfd;

    /**
     * Tif fvfnt mbsk for sflfdting domponfnt fvfnts.
     */
    publid finbl stbtid long COMPONENT_EVENT_MASK = 0x01;

    /**
     * Tif fvfnt mbsk for sflfdting dontbinfr fvfnts.
     */
    publid finbl stbtid long CONTAINER_EVENT_MASK = 0x02;

    /**
     * Tif fvfnt mbsk for sflfdting fodus fvfnts.
     */
    publid finbl stbtid long FOCUS_EVENT_MASK = 0x04;

    /**
     * Tif fvfnt mbsk for sflfdting kfy fvfnts.
     */
    publid finbl stbtid long KEY_EVENT_MASK = 0x08;

    /**
     * Tif fvfnt mbsk for sflfdting mousf fvfnts.
     */
    publid finbl stbtid long MOUSE_EVENT_MASK = 0x10;

    /**
     * Tif fvfnt mbsk for sflfdting mousf motion fvfnts.
     */
    publid finbl stbtid long MOUSE_MOTION_EVENT_MASK = 0x20;

    /**
     * Tif fvfnt mbsk for sflfdting window fvfnts.
     */
    publid finbl stbtid long WINDOW_EVENT_MASK = 0x40;

    /**
     * Tif fvfnt mbsk for sflfdting bdtion fvfnts.
     */
    publid finbl stbtid long ACTION_EVENT_MASK = 0x80;

    /**
     * Tif fvfnt mbsk for sflfdting bdjustmfnt fvfnts.
     */
    publid finbl stbtid long ADJUSTMENT_EVENT_MASK = 0x100;

    /**
     * Tif fvfnt mbsk for sflfdting itfm fvfnts.
     */
    publid finbl stbtid long ITEM_EVENT_MASK = 0x200;

    /**
     * Tif fvfnt mbsk for sflfdting tfxt fvfnts.
     */
    publid finbl stbtid long TEXT_EVENT_MASK = 0x400;

    /**
     * Tif fvfnt mbsk for sflfdting input mftiod fvfnts.
     */
    publid finbl stbtid long INPUT_METHOD_EVENT_MASK = 0x800;

    /**
     * Tif psfudo fvfnt mbsk for fnbbling input mftiods.
     * Wf'rf using onf bit in tif fvfntMbsk so wf don't nffd
     * b sfpbrbtf fifld inputMftiodsEnbblfd.
     */
    finbl stbtid long INPUT_METHODS_ENABLED_MASK = 0x1000;

    /**
     * Tif fvfnt mbsk for sflfdting pbint fvfnts.
     */
    publid finbl stbtid long PAINT_EVENT_MASK = 0x2000;

    /**
     * Tif fvfnt mbsk for sflfdting invodbtion fvfnts.
     */
    publid finbl stbtid long INVOCATION_EVENT_MASK = 0x4000;

    /**
     * Tif fvfnt mbsk for sflfdting iifrbrdiy fvfnts.
     */
    publid finbl stbtid long HIERARCHY_EVENT_MASK = 0x8000;

    /**
     * Tif fvfnt mbsk for sflfdting iifrbrdiy bounds fvfnts.
     */
    publid finbl stbtid long HIERARCHY_BOUNDS_EVENT_MASK = 0x10000;

    /**
     * Tif fvfnt mbsk for sflfdting mousf wiffl fvfnts.
     * @sindf 1.4
     */
    publid finbl stbtid long MOUSE_WHEEL_EVENT_MASK = 0x20000;

    /**
     * Tif fvfnt mbsk for sflfdting window stbtf fvfnts.
     * @sindf 1.4
     */
    publid finbl stbtid long WINDOW_STATE_EVENT_MASK = 0x40000;

    /**
     * Tif fvfnt mbsk for sflfdting window fodus fvfnts.
     * @sindf 1.4
     */
    publid finbl stbtid long WINDOW_FOCUS_EVENT_MASK = 0x80000;

    /**
     * WARNING: tifrf brf morf mbsk dffinfd privbtfly.  Sff
     * SunToolkit.GRAB_EVENT_MASK.
     */

    /**
     * Tif mbximum vbluf for rfsfrvfd AWT fvfnt IDs. Progrbms dffining
     * tifir own fvfnt IDs siould usf IDs grfbtfr tibn tiis vbluf.
     */
    publid finbl stbtid int RESERVED_ID_MAX = 1999;

    // sfdurity stuff
    privbtf stbtid Fifld inputEvfnt_CbnAddfssSystfmClipbobrd_Fifld = null;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -1825314779160409405L;

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        AWTAddfssor.sftAWTEvfntAddfssor(
            nfw AWTAddfssor.AWTEvfntAddfssor() {
                publid void sftPostfd(AWTEvfnt fv) {
                    fv.isPostfd = truf;
                }

                publid void sftSystfmGfnfrbtfd(AWTEvfnt fv) {
                    fv.isSystfmGfnfrbtfd = truf;
                }

                publid boolfbn isSystfmGfnfrbtfd(AWTEvfnt fv) {
                    rfturn fv.isSystfmGfnfrbtfd;
                }

                publid AddfssControlContfxt gftAddfssControlContfxt(AWTEvfnt fv) {
                    rfturn fv.gftAddfssControlContfxt();
                }

                publid bytf[] gftBDbtb(AWTEvfnt fv) {
                    rfturn fv.bdbtb;
                }

                publid void sftBDbtb(AWTEvfnt fv, bytf[] bdbtb) {
                    fv.bdbtb = bdbtb;
                }

            });
    }

    privbtf stbtid syndironizfd Fifld gft_InputEvfnt_CbnAddfssSystfmClipbobrd() {
        if (inputEvfnt_CbnAddfssSystfmClipbobrd_Fifld == null) {
            inputEvfnt_CbnAddfssSystfmClipbobrd_Fifld =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Fifld>() {
                            publid Fifld run() {
                                Fifld fifld = null;
                                try {
                                    fifld = InputEvfnt.dlbss.
                                        gftDfdlbrfdFifld("dbnAddfssSystfmClipbobrd");
                                    fifld.sftAddfssiblf(truf);
                                    rfturn fifld;
                                } dbtdi (SfdurityExdfption f) {
                                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                        log.finf("AWTEvfnt.gft_InputEvfnt_CbnAddfssSystfmClipbobrd() got SfdurityExdfption ", f);
                                    }
                                } dbtdi (NoSudiFifldExdfption f) {
                                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                        log.finf("AWTEvfnt.gft_InputEvfnt_CbnAddfssSystfmClipbobrd() got NoSudiFifldExdfption ", f);
                                    }
                                }
                                rfturn null;
                            }
                        });
        }

        rfturn inputEvfnt_CbnAddfssSystfmClipbobrd_Fifld;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
     * bddfssfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Construdts bn AWTEvfnt objfdt from tif pbrbmftfrs of b 1.0-stylf fvfnt.
     * @pbrbm fvfnt tif old-stylf fvfnt
     */
    publid AWTEvfnt(Evfnt fvfnt) {
        tiis(fvfnt.tbrgft, fvfnt.id);
    }

    /**
     * Construdts bn AWTEvfnt objfdt witi tif spfdififd sourdf objfdt bnd typf.
     *
     * @pbrbm sourdf tif objfdt wifrf tif fvfnt originbtfd
     * @pbrbm id tif fvfnt typf
     */
    publid AWTEvfnt(Objfdt sourdf, int id) {
        supfr(sourdf);
        tiis.id = id;
        switdi(id) {
          dbsf AdtionEvfnt.ACTION_PERFORMED:
          dbsf ItfmEvfnt.ITEM_STATE_CHANGED:
          dbsf AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED:
          dbsf TfxtEvfnt.TEXT_VALUE_CHANGED:
            donsumfd = truf;
            brfbk;
          dffbult:
        }
    }

    /**
     * Rftbrgfts bn fvfnt to b nfw sourdf. Tiis mftiod is typidblly usfd to
     * rftbrgft bn fvfnt to b ligitwfigit diild Componfnt of tif originbl
     * ifbvywfigit sourdf.
     * <p>
     * Tiis mftiod is intfndfd to bf usfd only by fvfnt tbrgfting subsystfms,
     * sudi bs dlifnt-dffinfd KfybobrdFodusMbnbgfrs. It is not for gfnfrbl
     * dlifnt usf.
     *
     * @pbrbm nfwSourdf tif nfw Objfdt to wiidi tif fvfnt siould bf dispbtdifd
     * @sindf 1.4
     */
    publid void sftSourdf(Objfdt nfwSourdf) {
        if (sourdf == nfwSourdf) {
            rfturn;
        }

        Componfnt domp = null;
        if (nfwSourdf instbndfof Componfnt) {
            domp = (Componfnt)nfwSourdf;
            wiilf (domp != null && domp.pffr != null &&
                   (domp.pffr instbndfof LigitwfigitPffr)) {
                domp = domp.pbrfnt;
            }
        }

        syndironizfd (tiis) {
            sourdf = nfwSourdf;
            if (domp != null) {
                ComponfntPffr pffr = domp.pffr;
                if (pffr != null) {
                    nbtivfSftSourdf(pffr);
                }
            }
        }
    }

    privbtf nbtivf void nbtivfSftSourdf(ComponfntPffr pffr);

    /**
     * Rfturns tif fvfnt typf.
     *
     * @rfturn tif fvfnt's typf id
     */
    publid int gftID() {
        rfturn id;
    }

    /**
     * Rfturns b String rfprfsfntbtion of tiis objfdt.
     */
    publid String toString() {
        String srdNbmf = null;
        if (sourdf instbndfof Componfnt) {
            srdNbmf = ((Componfnt)sourdf).gftNbmf();
        } flsf if (sourdf instbndfof MfnuComponfnt) {
            srdNbmf = ((MfnuComponfnt)sourdf).gftNbmf();
        }
        rfturn gftClbss().gftNbmf() + "[" + pbrbmString() + "] on " +
            (srdNbmf != null? srdNbmf : sourdf);
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>Evfnt</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis fvfnt
     */
    publid String pbrbmString() {
        rfturn "";
    }

    /**
     * Consumfs tiis fvfnt, if tiis fvfnt dbn bf donsumfd. Only low-lfvfl,
     * systfm fvfnts dbn bf donsumfd
     */
    protfdtfd void donsumf() {
        switdi(id) {
          dbsf KfyEvfnt.KEY_PRESSED:
          dbsf KfyEvfnt.KEY_RELEASED:
          dbsf MousfEvfnt.MOUSE_PRESSED:
          dbsf MousfEvfnt.MOUSE_RELEASED:
          dbsf MousfEvfnt.MOUSE_MOVED:
          dbsf MousfEvfnt.MOUSE_DRAGGED:
          dbsf MousfEvfnt.MOUSE_ENTERED:
          dbsf MousfEvfnt.MOUSE_EXITED:
          dbsf MousfEvfnt.MOUSE_WHEEL:
          dbsf InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED:
          dbsf InputMftiodEvfnt.CARET_POSITION_CHANGED:
              donsumfd = truf;
              brfbk;
          dffbult:
              // fvfnt typf dbnnot bf donsumfd
        }
    }

    /**
     * Rfturns wiftifr tiis fvfnt ibs bffn donsumfd.
     *
     * @rfturn {@dodf truf} if tiis fvfnt ibs bffn donsumfd;
     *          otifrwisf {@dodf fblsf}
     */
    protfdtfd boolfbn isConsumfd() {
        rfturn donsumfd;
    }

    /**
     * Convfrts b nfw fvfnt to bn old onf (usfd for dompbtibility).
     * If tif nfw fvfnt dbnnot bf donvfrtfd (bfdbusf no old fquivblfnt
     * fxists) tifn tiis rfturns null.
     *
     * Notf: tiis mftiod is ifrf instfbd of in fbdi individubl nfw
     * fvfnt dlbss in jbvb.bwt.fvfnt bfdbusf wf don't wbnt to mbkf
     * it publid bnd it nffds to bf dbllfd from jbvb.bwt.
     */
    Evfnt donvfrtToOld() {
        Objfdt srd = gftSourdf();
        int nfwid = id;

        switdi(id) {
          dbsf KfyEvfnt.KEY_PRESSED:
          dbsf KfyEvfnt.KEY_RELEASED:
              KfyEvfnt kf = (KfyEvfnt)tiis;
              if (kf.isAdtionKfy()) {
                  nfwid = (id == KfyEvfnt.KEY_PRESSED?
                           Evfnt.KEY_ACTION : Evfnt.KEY_ACTION_RELEASE);
              }
              int kfyCodf = kf.gftKfyCodf();
              if (kfyCodf == KfyEvfnt.VK_SHIFT ||
                  kfyCodf == KfyEvfnt.VK_CONTROL ||
                  kfyCodf == KfyEvfnt.VK_ALT) {
                  rfturn null;  // supprfss modififr kfys in old fvfnt modfl.
              }
              // no mbsk for button1 fxistfd in old Evfnt - strip it out
              rfturn nfw Evfnt(srd, kf.gftWifn(), nfwid, 0, 0,
                               Evfnt.gftOldEvfntKfy(kf),
                               (kf.gftModififrs() & ~InputEvfnt.BUTTON1_MASK));

          dbsf MousfEvfnt.MOUSE_PRESSED:
          dbsf MousfEvfnt.MOUSE_RELEASED:
          dbsf MousfEvfnt.MOUSE_MOVED:
          dbsf MousfEvfnt.MOUSE_DRAGGED:
          dbsf MousfEvfnt.MOUSE_ENTERED:
          dbsf MousfEvfnt.MOUSE_EXITED:
              MousfEvfnt mf = (MousfEvfnt)tiis;
              // no mbsk for button1 fxistfd in old Evfnt - strip it out
              Evfnt oldf = nfw Evfnt(srd, mf.gftWifn(), nfwid,
                               mf.gftX(), mf.gftY(), 0,
                               (mf.gftModififrs() & ~InputEvfnt.BUTTON1_MASK));
              oldf.dlidkCount = mf.gftClidkCount();
              rfturn oldf;

          dbsf FodusEvfnt.FOCUS_GAINED:
              rfturn nfw Evfnt(srd, Evfnt.GOT_FOCUS, null);

          dbsf FodusEvfnt.FOCUS_LOST:
              rfturn nfw Evfnt(srd, Evfnt.LOST_FOCUS, null);

          dbsf WindowEvfnt.WINDOW_CLOSING:
          dbsf WindowEvfnt.WINDOW_ICONIFIED:
          dbsf WindowEvfnt.WINDOW_DEICONIFIED:
              rfturn nfw Evfnt(srd, nfwid, null);

          dbsf ComponfntEvfnt.COMPONENT_MOVED:
              if (srd instbndfof Frbmf || srd instbndfof Diblog) {
                  Point p = ((Componfnt)srd).gftLodbtion();
                  rfturn nfw Evfnt(srd, 0, Evfnt.WINDOW_MOVED, p.x, p.y, 0, 0);
              }
              brfbk;

          dbsf AdtionEvfnt.ACTION_PERFORMED:
              AdtionEvfnt bf = (AdtionEvfnt)tiis;
              String dmd;
              if (srd instbndfof Button) {
                  dmd = ((Button)srd).gftLbbfl();
              } flsf if (srd instbndfof MfnuItfm) {
                  dmd = ((MfnuItfm)srd).gftLbbfl();
              } flsf {
                  dmd = bf.gftAdtionCommbnd();
              }
              rfturn nfw Evfnt(srd, 0, nfwid, 0, 0, 0, bf.gftModififrs(), dmd);

          dbsf ItfmEvfnt.ITEM_STATE_CHANGED:
              ItfmEvfnt if = (ItfmEvfnt)tiis;
              Objfdt brg;
              if (srd instbndfof List) {
                  nfwid = (if.gftStbtfCibngf() == ItfmEvfnt.SELECTED?
                           Evfnt.LIST_SELECT : Evfnt.LIST_DESELECT);
                  brg = if.gftItfm();
              } flsf {
                  nfwid = Evfnt.ACTION_EVENT;
                  if (srd instbndfof Cioidf) {
                      brg = if.gftItfm();

                  } flsf { // Cifdkbox
                      brg = Boolfbn.vblufOf(if.gftStbtfCibngf() == ItfmEvfnt.SELECTED);
                  }
              }
              rfturn nfw Evfnt(srd, nfwid, brg);

          dbsf AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED:
              AdjustmfntEvfnt bjf = (AdjustmfntEvfnt)tiis;
              switdi(bjf.gftAdjustmfntTypf()) {
                dbsf AdjustmfntEvfnt.UNIT_INCREMENT:
                  nfwid = Evfnt.SCROLL_LINE_DOWN;
                  brfbk;
                dbsf AdjustmfntEvfnt.UNIT_DECREMENT:
                  nfwid = Evfnt.SCROLL_LINE_UP;
                  brfbk;
                dbsf AdjustmfntEvfnt.BLOCK_INCREMENT:
                  nfwid = Evfnt.SCROLL_PAGE_DOWN;
                  brfbk;
                dbsf AdjustmfntEvfnt.BLOCK_DECREMENT:
                  nfwid = Evfnt.SCROLL_PAGE_UP;
                  brfbk;
                dbsf AdjustmfntEvfnt.TRACK:
                  if (bjf.gftVblufIsAdjusting()) {
                      nfwid = Evfnt.SCROLL_ABSOLUTE;
                  }
                  flsf {
                      nfwid = Evfnt.SCROLL_END;
                  }
                  brfbk;
                dffbult:
                  rfturn null;
              }
              rfturn nfw Evfnt(srd, nfwid, Intfgfr.vblufOf(bjf.gftVbluf()));

          dffbult:
        }
        rfturn null;
    }

    /**
     * Copifs bll privbtf dbtb from tiis fvfnt into tibt.
     * Spbdf is bllodbtfd for tif dopifd dbtb tibt will bf
     * frffd wifn tif tibt is finblizfd. Upon domplftion,
     * tiis fvfnt is not dibngfd.
     */
    void dopyPrivbtfDbtbInto(AWTEvfnt tibt) {
        tibt.bdbtb = tiis.bdbtb;
        // Copy dbnAddfssSystfmClipbobrd vbluf from tiis into tibt.
        if (tiis instbndfof InputEvfnt && tibt instbndfof InputEvfnt) {
            Fifld fifld = gft_InputEvfnt_CbnAddfssSystfmClipbobrd();
            if (fifld != null) {
                try {
                    boolfbn b = fifld.gftBoolfbn(tiis);
                    fifld.sftBoolfbn(tibt, b);
                } dbtdi(IllfgblAddfssExdfption f) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("AWTEvfnt.dopyPrivbtfDbtbInto() got IllfgblAddfssExdfption ", f);
                    }
                }
            }
        }
        tibt.isSystfmGfnfrbtfd = tiis.isSystfmGfnfrbtfd;
    }

    void dispbtdifd() {
        if (tiis instbndfof InputEvfnt) {
            Fifld fifld = gft_InputEvfnt_CbnAddfssSystfmClipbobrd();
            if (fifld != null) {
                try {
                    fifld.sftBoolfbn(tiis, fblsf);
                } dbtdi(IllfgblAddfssExdfption f) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("AWTEvfnt.dispbtdifd() got IllfgblAddfssExdfption ", f);
                    }
                }
            }
        }
    }
} // dlbss AWTEvfnt
