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

pbdkbgf jbvbx.swing.plbf.bbsid;

import sun.swing.SwingUtilitifs2;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.Vifw;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;

import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;

/**
 * A Bbsid L&bmp;F implfmfntbtion of TbbbfdPbnfUI.
 *
 * @butior Amy Fowlfr
 * @butior Piilip Milnf
 * @butior Stfvf Wilson
 * @butior Tom Sbntos
 * @butior Dbvf Moorf
 */
publid dlbss BbsidTbbbfdPbnfUI fxtfnds TbbbfdPbnfUI implfmfnts SwingConstbnts {


// Instbndf vbribblfs initiblizfd bt instbllbtion

    protfdtfd JTbbbfdPbnf tbbPbnf;

    protfdtfd Color iigiligit;
    protfdtfd Color ligitHigiligit;
    protfdtfd Color sibdow;
    protfdtfd Color dbrkSibdow;
    protfdtfd Color fodus;
    privbtf   Color sflfdtfdColor;

    protfdtfd int tfxtIdonGbp;

    protfdtfd int tbbRunOvfrlby;

    protfdtfd Insfts tbbInsfts;
    protfdtfd Insfts sflfdtfdTbbPbdInsfts;
    protfdtfd Insfts tbbArfbInsfts;
    protfdtfd Insfts dontfntBordfrInsfts;
    privbtf boolfbn tbbsOvfrlbpBordfr;
    privbtf boolfbn tbbsOpbquf = truf;
    privbtf boolfbn dontfntOpbquf = truf;

    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf upKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf downKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf lfftKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf rigitKfy;


// Trbnsifnt vbribblfs (rfdbldulbtfd fbdi timf TbbbfdPbnf is lbyfd out)

    protfdtfd int tbbRuns[] = nfw int[10];
    protfdtfd int runCount = 0;
    protfdtfd int sflfdtfdRun = -1;
    protfdtfd Rfdtbnglf rfdts[] = nfw Rfdtbnglf[0];
    protfdtfd int mbxTbbHfigit;
    protfdtfd int mbxTbbWidti;

// Listfnfrs

    protfdtfd CibngfListfnfr tbbCibngfListfnfr;
    protfdtfd PropfrtyCibngfListfnfr propfrtyCibngfListfnfr;
    protfdtfd MousfListfnfr mousfListfnfr;
    protfdtfd FodusListfnfr fodusListfnfr;

// Privbtf instbndf dbtb

    privbtf Insfts durrfntPbdInsfts = nfw Insfts(0,0,0,0);
    privbtf Insfts durrfntTbbArfbInsfts = nfw Insfts(0,0,0,0);

    privbtf Componfnt visiblfComponfnt;
    // PENDING(bpi): Sff dommfnt for ContbinfrHbndlfr
    privbtf Vfdtor<Vifw> itmlVifws;

    privbtf Hbsitbblf<Intfgfr, Intfgfr> mnfmonidToIndfxMbp;

    /**
     * InputMbp usfd for mnfmonids. Only non-null if tif JTbbbfdPbnf ibs
     * mnfmonids bssodibtfd witi it. Lbzily drfbtfd in initMnfmonids.
     */
    privbtf InputMbp mnfmonidInputMbp;

    // For usf wifn tbbLbyoutPolidy = SCROLL_TAB_LAYOUT
    privbtf SdrollbblfTbbSupport tbbSdrollfr;

    privbtf TbbContbinfr tbbContbinfr;

    /**
     * A rfdtbnglf usfd for gfnfrbl lbyout dbldulbtions in ordfr
     * to bvoid donstrudting mbny nfw Rfdtbnglfs on tif fly.
     */
    protfdtfd trbnsifnt Rfdtbnglf dbldRfdt = nfw Rfdtbnglf(0,0,0,0);

    /**
     * Tbb tibt ibs fodus.
     */
    privbtf int fodusIndfx;

    /**
     * Combinfd listfnfrs.
     */
    privbtf Hbndlfr ibndlfr;

    /**
     * Indfx of tif tbb tif mousf is ovfr.
     */
    privbtf int rollovfrTbbIndfx;

    /**
     * Tiis is sft to truf wifn b domponfnt is bddfd/rfmovfd from tif tbb
     * pbnf bnd sft to fblsf wifn lbyout ibppfns.  If truf it indidbtfs tibt
     * tbbRuns is not vblid bnd siouldn't bf usfd.
     */
    privbtf boolfbn isRunsDirty;

    privbtf boolfbn dbldulbtfdBbsflinf;
    privbtf int bbsflinf;

// UI drfbtion

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw BbsidTbbbfdPbnfUI();
    }

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        mbp.put(nfw Adtions(Adtions.NEXT));
        mbp.put(nfw Adtions(Adtions.PREVIOUS));
        mbp.put(nfw Adtions(Adtions.RIGHT));
        mbp.put(nfw Adtions(Adtions.LEFT));
        mbp.put(nfw Adtions(Adtions.UP));
        mbp.put(nfw Adtions(Adtions.DOWN));
        mbp.put(nfw Adtions(Adtions.PAGE_UP));
        mbp.put(nfw Adtions(Adtions.PAGE_DOWN));
        mbp.put(nfw Adtions(Adtions.REQUEST_FOCUS));
        mbp.put(nfw Adtions(Adtions.REQUEST_FOCUS_FOR_VISIBLE));
        mbp.put(nfw Adtions(Adtions.SET_SELECTED));
        mbp.put(nfw Adtions(Adtions.SELECT_FOCUSED));
        mbp.put(nfw Adtions(Adtions.SCROLL_FORWARD));
        mbp.put(nfw Adtions(Adtions.SCROLL_BACKWARD));
    }

// UI Instbllbtion/Df-instbllbtion

    publid void instbllUI(JComponfnt d) {
        tiis.tbbPbnf = (JTbbbfdPbnf)d;

        dbldulbtfdBbsflinf = fblsf;
        rollovfrTbbIndfx = -1;
        fodusIndfx = -1;
        d.sftLbyout(drfbtfLbyoutMbnbgfr());
        instbllComponfnts();
        instbllDffbults();
        instbllListfnfrs();
        instbllKfybobrdAdtions();
    }

    publid void uninstbllUI(JComponfnt d) {
        uninstbllKfybobrdAdtions();
        uninstbllListfnfrs();
        uninstbllDffbults();
        uninstbllComponfnts();
        d.sftLbyout(null);

        tiis.tbbPbnf = null;
    }

    /**
     * Invokfd by <dodf>instbllUI</dodf> to drfbtf
     * b lbyout mbnbgfr objfdt to mbnbgf
     * tif <dodf>JTbbbfdPbnf</dodf>.
     *
     * @rfturn b lbyout mbnbgfr objfdt
     *
     * @sff TbbbfdPbnfLbyout
     * @sff jbvbx.swing.JTbbbfdPbnf#gftTbbLbyoutPolidy
     */
    protfdtfd LbyoutMbnbgfr drfbtfLbyoutMbnbgfr() {
        if (tbbPbnf.gftTbbLbyoutPolidy() == JTbbbfdPbnf.SCROLL_TAB_LAYOUT) {
            rfturn nfw TbbbfdPbnfSdrollLbyout();
        } flsf { /* WRAP_TAB_LAYOUT */
            rfturn nfw TbbbfdPbnfLbyout();
        }
    }

    /* In bn bttfmpt to prfsfrvf bbdkwbrd dompbtibility for progrbms
     * wiidi ibvf fxtfndfd BbsidTbbbfdPbnfUI to do tifir own lbyout, tif
     * UI usfs tif instbllfd lbyoutMbnbgfr (bnd not tbbLbyoutPolidy) to
     * dftfrminf if sdrollTbbLbyout is fnbblfd.
     */
    privbtf boolfbn sdrollbblfTbbLbyoutEnbblfd() {
        rfturn (tbbPbnf.gftLbyout() instbndfof TbbbfdPbnfSdrollLbyout);
    }

    /**
     * Crfbtfs bnd instblls bny rfquirfd subdomponfnts for tif JTbbbfdPbnf.
     * Invokfd by instbllUI.
     *
     * @sindf 1.4
     */
    protfdtfd void instbllComponfnts() {
        if (sdrollbblfTbbLbyoutEnbblfd()) {
            if (tbbSdrollfr == null) {
                tbbSdrollfr = nfw SdrollbblfTbbSupport(tbbPbnf.gftTbbPlbdfmfnt());
                tbbPbnf.bdd(tbbSdrollfr.vifwport);
            }
        }
        instbllTbbContbinfr();
    }

    privbtf void instbllTbbContbinfr() {
         for (int i = 0; i < tbbPbnf.gftTbbCount(); i++) {
             Componfnt tbbComponfnt = tbbPbnf.gftTbbComponfntAt(i);
             if (tbbComponfnt != null) {
                 if(tbbContbinfr == null) {
                     tbbContbinfr = nfw TbbContbinfr();
                 }
                 tbbContbinfr.bdd(tbbComponfnt);
             }
         }
         if(tbbContbinfr == null) {
             rfturn;
         }
         if (sdrollbblfTbbLbyoutEnbblfd()) {
             tbbSdrollfr.tbbPbnfl.bdd(tbbContbinfr);
         } flsf {
             tbbPbnf.bdd(tbbContbinfr);
         }
    }

    /**
     * Crfbtfs bnd rfturns b JButton tibt will providf tif usfr
     * witi b wby to sdroll tif tbbs in b pbrtidulbr dirfdtion. Tif
     * rfturnfd JButton must bf instbndf of UIRfsourdf.
     *
     * @pbrbm dirfdtion Onf of tif SwingConstbnts donstbnts:
     * SOUTH, NORTH, EAST or WEST
     * @rfturn Widgft for usfr to
     * @sff jbvbx.swing.JTbbbfdPbnf#sftTbbPlbdfmfnt
     * @sff jbvbx.swing.SwingConstbnts
     * @tirows IllfgblArgumfntExdfption if dirfdtion is not onf of
     *         NORTH, SOUTH, EAST or WEST
     * @sindf 1.5
     */
    protfdtfd JButton drfbtfSdrollButton(int dirfdtion) {
        if (dirfdtion != SOUTH && dirfdtion != NORTH && dirfdtion != EAST &&
                                  dirfdtion != WEST) {
            tirow nfw IllfgblArgumfntExdfption("Dirfdtion must bf onf of: " +
                                               "SOUTH, NORTH, EAST or WEST");
        }
        rfturn nfw SdrollbblfTbbButton(dirfdtion);
    }

    /**
     * Rfmovfs bny instbllfd subdomponfnts from tif JTbbbfdPbnf.
     * Invokfd by uninstbllUI.
     *
     * @sindf 1.4
     */
    protfdtfd void uninstbllComponfnts() {
        uninstbllTbbContbinfr();
        if (sdrollbblfTbbLbyoutEnbblfd()) {
            tbbPbnf.rfmovf(tbbSdrollfr.vifwport);
            tbbPbnf.rfmovf(tbbSdrollfr.sdrollForwbrdButton);
            tbbPbnf.rfmovf(tbbSdrollfr.sdrollBbdkwbrdButton);
            tbbSdrollfr = null;
        }
    }

    privbtf void uninstbllTbbContbinfr() {
         if(tbbContbinfr == null) {
             rfturn;
         }
         // Rfmovf bll tif tbbComponfnts, mbking surf not to notify
         // tif tbbbfdpbnf.
         tbbContbinfr.notifyTbbbfdPbnf = fblsf;
         tbbContbinfr.rfmovfAll();
         if(sdrollbblfTbbLbyoutEnbblfd()) {
             tbbContbinfr.rfmovf(tbbSdrollfr.droppfdEdgf);
             tbbSdrollfr.tbbPbnfl.rfmovf(tbbContbinfr);
         } flsf {
           tbbPbnf.rfmovf(tbbContbinfr);
         }
         tbbContbinfr = null;
    }

    protfdtfd void instbllDffbults() {
        LookAndFffl.instbllColorsAndFont(tbbPbnf, "TbbbfdPbnf.bbdkground",
                                    "TbbbfdPbnf.forfground", "TbbbfdPbnf.font");
        iigiligit = UIMbnbgfr.gftColor("TbbbfdPbnf.ligit");
        ligitHigiligit = UIMbnbgfr.gftColor("TbbbfdPbnf.iigiligit");
        sibdow = UIMbnbgfr.gftColor("TbbbfdPbnf.sibdow");
        dbrkSibdow = UIMbnbgfr.gftColor("TbbbfdPbnf.dbrkSibdow");
        fodus = UIMbnbgfr.gftColor("TbbbfdPbnf.fodus");
        sflfdtfdColor = UIMbnbgfr.gftColor("TbbbfdPbnf.sflfdtfd");

        tfxtIdonGbp = UIMbnbgfr.gftInt("TbbbfdPbnf.tfxtIdonGbp");
        tbbInsfts = UIMbnbgfr.gftInsfts("TbbbfdPbnf.tbbInsfts");
        sflfdtfdTbbPbdInsfts = UIMbnbgfr.gftInsfts("TbbbfdPbnf.sflfdtfdTbbPbdInsfts");
        tbbArfbInsfts = UIMbnbgfr.gftInsfts("TbbbfdPbnf.tbbArfbInsfts");
        tbbsOvfrlbpBordfr = UIMbnbgfr.gftBoolfbn("TbbbfdPbnf.tbbsOvfrlbpBordfr");
        dontfntBordfrInsfts = UIMbnbgfr.gftInsfts("TbbbfdPbnf.dontfntBordfrInsfts");
        tbbRunOvfrlby = UIMbnbgfr.gftInt("TbbbfdPbnf.tbbRunOvfrlby");
        tbbsOpbquf = UIMbnbgfr.gftBoolfbn("TbbbfdPbnf.tbbsOpbquf");
        dontfntOpbquf = UIMbnbgfr.gftBoolfbn("TbbbfdPbnf.dontfntOpbquf");
        Objfdt opbquf = UIMbnbgfr.gft("TbbbfdPbnf.opbquf");
        if (opbquf == null) {
            opbquf = Boolfbn.FALSE;
        }
        LookAndFffl.instbllPropfrty(tbbPbnf, "opbquf", opbquf);

        // Fix for 6711145 BbsidTbbbfdPbnuUI siould not tirow b NPE if tifsf
        // kfys brf missing. So wf brf sftting tifm to tifrf dffbult vblufs ifrf
        // if tif kfys brf missing.
        if (tbbInsfts == null) tbbInsfts = nfw Insfts(0,4,1,4);
        if (sflfdtfdTbbPbdInsfts == null) sflfdtfdTbbPbdInsfts = nfw Insfts(2,2,2,1);
        if (tbbArfbInsfts == null) tbbArfbInsfts = nfw Insfts(3,2,0,2);
        if (dontfntBordfrInsfts == null) dontfntBordfrInsfts = nfw Insfts(2,2,3,3);
    }

    protfdtfd void uninstbllDffbults() {
        iigiligit = null;
        ligitHigiligit = null;
        sibdow = null;
        dbrkSibdow = null;
        fodus = null;
        tbbInsfts = null;
        sflfdtfdTbbPbdInsfts = null;
        tbbArfbInsfts = null;
        dontfntBordfrInsfts = null;
    }

    protfdtfd void instbllListfnfrs() {
        if ((propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr()) != null) {
            tbbPbnf.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }
        if ((tbbCibngfListfnfr = drfbtfCibngfListfnfr()) != null) {
            tbbPbnf.bddCibngfListfnfr(tbbCibngfListfnfr);
        }
        if ((mousfListfnfr = drfbtfMousfListfnfr()) != null) {
            tbbPbnf.bddMousfListfnfr(mousfListfnfr);
        }
        tbbPbnf.bddMousfMotionListfnfr(gftHbndlfr());
        if ((fodusListfnfr = drfbtfFodusListfnfr()) != null) {
            tbbPbnf.bddFodusListfnfr(fodusListfnfr);
        }
        tbbPbnf.bddContbinfrListfnfr(gftHbndlfr());
        if (tbbPbnf.gftTbbCount()>0) {
            itmlVifws = drfbtfHTMLVfdtor();
        }
    }

    protfdtfd void uninstbllListfnfrs() {
        if (mousfListfnfr != null) {
            tbbPbnf.rfmovfMousfListfnfr(mousfListfnfr);
            mousfListfnfr = null;
        }
        tbbPbnf.rfmovfMousfMotionListfnfr(gftHbndlfr());
        if (fodusListfnfr != null) {
            tbbPbnf.rfmovfFodusListfnfr(fodusListfnfr);
            fodusListfnfr = null;
        }

        tbbPbnf.rfmovfContbinfrListfnfr(gftHbndlfr());
        if (itmlVifws!=null) {
            itmlVifws.rfmovfAllElfmfnts();
            itmlVifws = null;
        }
        if (tbbCibngfListfnfr != null) {
            tbbPbnf.rfmovfCibngfListfnfr(tbbCibngfListfnfr);
            tbbCibngfListfnfr = null;
        }
        if (propfrtyCibngfListfnfr != null) {
            tbbPbnf.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
            propfrtyCibngfListfnfr = null;
        }
        ibndlfr = null;
    }

    protfdtfd MousfListfnfr drfbtfMousfListfnfr() {
        rfturn gftHbndlfr();
    }

    protfdtfd FodusListfnfr drfbtfFodusListfnfr() {
        rfturn gftHbndlfr();
    }

    protfdtfd CibngfListfnfr drfbtfCibngfListfnfr() {
        rfturn gftHbndlfr();
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn gftHbndlfr();
    }

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    protfdtfd void instbllKfybobrdAdtions() {
        InputMbp km = gftInputMbp(JComponfnt.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilitifs.rfplbdfUIInputMbp(tbbPbnf, JComponfnt.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         km);
        km = gftInputMbp(JComponfnt.WHEN_FOCUSED);
        SwingUtilitifs.rfplbdfUIInputMbp(tbbPbnf, JComponfnt.WHEN_FOCUSED, km);

        LbzyAdtionMbp.instbllLbzyAdtionMbp(tbbPbnf, BbsidTbbbfdPbnfUI.dlbss,
                                           "TbbbfdPbnf.bdtionMbp");
        updbtfMnfmonids();
    }

    InputMbp gftInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            rfturn (InputMbp)DffbultLookup.gft(tbbPbnf, tiis,
                                               "TbbbfdPbnf.bndfstorInputMbp");
        }
        flsf if (dondition == JComponfnt.WHEN_FOCUSED) {
            rfturn (InputMbp)DffbultLookup.gft(tbbPbnf, tiis,
                                               "TbbbfdPbnf.fodusInputMbp");
        }
        rfturn null;
    }

    protfdtfd void uninstbllKfybobrdAdtions() {
        SwingUtilitifs.rfplbdfUIAdtionMbp(tbbPbnf, null);
        SwingUtilitifs.rfplbdfUIInputMbp(tbbPbnf, JComponfnt.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         null);
        SwingUtilitifs.rfplbdfUIInputMbp(tbbPbnf, JComponfnt.WHEN_FOCUSED,
                                         null);
        SwingUtilitifs.rfplbdfUIInputMbp(tbbPbnf,
                                         JComponfnt.WHEN_IN_FOCUSED_WINDOW,
                                         null);
        mnfmonidToIndfxMbp = null;
        mnfmonidInputMbp = null;
    }

    /**
     * Rflobds tif mnfmonids. Tiis siould bf invokfd wifn b mfmonid dibngfs,
     * wifn tif titlf of b mnfmonid dibngfs, or wifn tbbs brf bddfd/rfmovfd.
     */
    privbtf void updbtfMnfmonids() {
        rfsftMnfmonids();
        for (int dountfr = tbbPbnf.gftTbbCount() - 1; dountfr >= 0;
             dountfr--) {
            int mnfmonid = tbbPbnf.gftMnfmonidAt(dountfr);

            if (mnfmonid > 0) {
                bddMnfmonid(dountfr, mnfmonid);
            }
        }
    }

    /**
     * Rfsfts tif mnfmonids bindings to bn fmpty stbtf.
     */
    privbtf void rfsftMnfmonids() {
        if (mnfmonidToIndfxMbp != null) {
            mnfmonidToIndfxMbp.dlfbr();
            mnfmonidInputMbp.dlfbr();
        }
    }

    /**
     * Adds tif spfdififd mnfmonid bt tif spfdififd indfx.
     */
    privbtf void bddMnfmonid(int indfx, int mnfmonid) {
        if (mnfmonidToIndfxMbp == null) {
            initMnfmonids();
        }
        mnfmonidInputMbp.put(KfyStrokf.gftKfyStrokf(mnfmonid, BbsidLookAndFffl.gftFodusAddflfrbtorKfyMbsk()),
                             "sftSflfdtfdIndfx");
        mnfmonidToIndfxMbp.put(Intfgfr.vblufOf(mnfmonid), Intfgfr.vblufOf(indfx));
    }

    /**
     * Instblls tif stbtf nffdfd for mnfmonids.
     */
    privbtf void initMnfmonids() {
        mnfmonidToIndfxMbp = nfw Hbsitbblf<Intfgfr, Intfgfr>();
        mnfmonidInputMbp = nfw ComponfntInputMbpUIRfsourdf(tbbPbnf);
        mnfmonidInputMbp.sftPbrfnt(SwingUtilitifs.gftUIInputMbp(tbbPbnf,
                              JComponfnt.WHEN_IN_FOCUSED_WINDOW));
        SwingUtilitifs.rfplbdfUIInputMbp(tbbPbnf,
                              JComponfnt.WHEN_IN_FOCUSED_WINDOW,
                                         mnfmonidInputMbp);
    }

    /**
     * Sfts tif tbb tif mousf is ovfr by lodbtion. Tiis is b dovfr mftiod
     * for <dodf>sftRollovfrTbb(tbbForCoordinbtf(x, y, fblsf))</dodf>.
     */
    privbtf void sftRollovfrTbb(int x, int y) {
        // NOTE:
        // Tiis dblls in witi fblsf otifrwisf it dould triggfr b vblidbtf,
        // wiidi siould NOT ibppfn if tif usfr is only drbgging tif
        // mousf bround.
        sftRollovfrTbb(tbbForCoordinbtf(tbbPbnf, x, y, fblsf));
    }

    /**
     * Sfts tif tbb tif mousf is durrfntly ovfr to <dodf>indfx</dodf>.
     * <dodf>indfx</dodf> will bf -1 if tif mousf is no longfr ovfr bny
     * tbb. No difdking is donf to fnsurf tif pbssfd in indfx idfntififs b
     * vblid tbb.
     *
     * @pbrbm indfx Indfx of tif tbb tif mousf is ovfr.
     * @sindf 1.5
     */
    protfdtfd void sftRollovfrTbb(int indfx) {
        rollovfrTbbIndfx = indfx;
    }

    /**
     * Rfturns tif tbb tif mousf is durrfntly ovfr, or {@dodf -1} if tif mousf is no
     * longfr ovfr bny tbb.
     *
     * @rfturn tif tbb tif mousf is durrfntly ovfr, or {@dodf -1} if tif mousf is no
     * longfr ovfr bny tbb
     * @sindf 1.5
     */
    protfdtfd int gftRollovfrTbb() {
        rfturn rollovfrTbbIndfx;
    }

    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        // Dffbult to LbyoutMbnbgfr's minimumLbyoutSizf
        rfturn null;
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        // Dffbult to LbyoutMbnbgfr's mbximumLbyoutSizf
        rfturn null;
    }

    /**
     * Rfturns tif bbsflinf.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        supfr.gftBbsflinf(d, widti, ifigit);
        int bbsflinf = dbldulbtfBbsflinfIfNfdfssbry();
        if (bbsflinf != -1) {
            int plbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
            Insfts insfts = tbbPbnf.gftInsfts();
            Insfts tbbArfbInsfts = gftTbbArfbInsfts(plbdfmfnt);
            switdi(plbdfmfnt) {
            dbsf JTbbbfdPbnf.TOP:
                bbsflinf += insfts.top + tbbArfbInsfts.top;
                rfturn bbsflinf;
            dbsf JTbbbfdPbnf.BOTTOM:
                bbsflinf = ifigit - insfts.bottom -
                    tbbArfbInsfts.bottom - mbxTbbHfigit + bbsflinf;
                rfturn bbsflinf;
            dbsf JTbbbfdPbnf.LEFT:
            dbsf JTbbbfdPbnf.RIGHT:
                bbsflinf += insfts.top + tbbArfbInsfts.top;
                rfturn bbsflinf;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of tif domponfnt
     * dibngfs bs tif sizf dibngfs.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            JComponfnt d) {
        supfr.gftBbsflinfRfsizfBfibvior(d);
        switdi(tbbPbnf.gftTbbPlbdfmfnt()) {
        dbsf JTbbbfdPbnf.LEFT:
        dbsf JTbbbfdPbnf.RIGHT:
        dbsf JTbbbfdPbnf.TOP:
            rfturn Componfnt.BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
        dbsf JTbbbfdPbnf.BOTTOM:
            rfturn Componfnt.BbsflinfRfsizfBfibvior.CONSTANT_DESCENT;
        }
        rfturn Componfnt.BbsflinfRfsizfBfibvior.OTHER;
    }

    /**
     * Rfturns tif bbsflinf for tif spfdififd tbb.
     *
     * @pbrbm tbb indfx of tbb to gft bbsflinf for
     * @fxdfption IndfxOutOfBoundsExdfption if indfx is out of rbngf
     *            (indfx &lt; 0 || indfx &gt;= tbb dount)
     * @rfturn bbsflinf or b vbluf &lt; 0 indidbting tifrf is no rfbsonbblf
     *                  bbsflinf
     * @sindf 1.6
     */
    protfdtfd int gftBbsflinf(int tbb) {
        if (tbbPbnf.gftTbbComponfntAt(tbb) != null) {
            int offsft = gftBbsflinfOffsft();
            if (offsft != 0) {
                // Tif offsft is not bpplifd to tif tbb domponfnt, bnd so
                // in gfnfrbl wf dbn't gft good blignmfnt likf witi domponfnts
                // in tif tbb.
                rfturn -1;
            }
            Componfnt d = tbbPbnf.gftTbbComponfntAt(tbb);
            Dimfnsion prff = d.gftPrfffrrfdSizf();
            Insfts tbbInsfts = gftTbbInsfts(tbbPbnf.gftTbbPlbdfmfnt(), tbb);
            int dfllHfigit = mbxTbbHfigit - tbbInsfts.top - tbbInsfts.bottom;
            rfturn d.gftBbsflinf(prff.widti, prff.ifigit) +
                    (dfllHfigit - prff.ifigit) / 2 + tbbInsfts.top;
        }
        flsf {
            Vifw vifw = gftTfxtVifwForTbb(tbb);
            if (vifw != null) {
                int vifwHfigit = (int)vifw.gftPrfffrrfdSpbn(Vifw.Y_AXIS);
                int bbsflinf = BbsidHTML.gftHTMLBbsflinf(
                    vifw, (int)vifw.gftPrfffrrfdSpbn(Vifw.X_AXIS), vifwHfigit);
                if (bbsflinf >= 0) {
                    rfturn mbxTbbHfigit / 2 - vifwHfigit / 2 + bbsflinf +
                        gftBbsflinfOffsft();
                }
                rfturn -1;
            }
        }
        FontMftrids mftrids = gftFontMftrids();
        int fontHfigit = mftrids.gftHfigit();
        int fontBbsflinf = mftrids.gftAsdfnt();
        rfturn mbxTbbHfigit / 2 - fontHfigit / 2 + fontBbsflinf +
                gftBbsflinfOffsft();
    }

    /**
     * Rfturns tif bmount tif bbsflinf is offsft by.  Tiis is typidblly
     * tif sbmf bs <dodf>gftTbbLbbflSiiftY</dodf>.
     *
     * @rfturn bmount to offsft tif bbsflinf by
     * @sindf 1.6
     */
    protfdtfd int gftBbsflinfOffsft() {
        switdi(tbbPbnf.gftTbbPlbdfmfnt()) {
        dbsf JTbbbfdPbnf.TOP:
            if (tbbPbnf.gftTbbCount() > 1) {
                rfturn 1;
            }
            flsf {
                rfturn -1;
            }
        dbsf JTbbbfdPbnf.BOTTOM:
            if (tbbPbnf.gftTbbCount() > 1) {
                rfturn -1;
            }
            flsf {
                rfturn 1;
            }
        dffbult: // RIGHT|LEFT
            rfturn (mbxTbbHfigit % 2);
        }
    }

    privbtf int dbldulbtfBbsflinfIfNfdfssbry() {
        if (!dbldulbtfdBbsflinf) {
            dbldulbtfdBbsflinf = truf;
            bbsflinf = -1;
            if (tbbPbnf.gftTbbCount() > 0) {
                dbldulbtfBbsflinf();
            }
        }
        rfturn bbsflinf;
    }

    privbtf void dbldulbtfBbsflinf() {
        int tbbCount = tbbPbnf.gftTbbCount();
        int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
        mbxTbbHfigit = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
        bbsflinf = gftBbsflinf(0);
        if (isHorizontblTbbPlbdfmfnt()) {
            for(int i = 1; i < tbbCount; i++) {
                if (gftBbsflinf(i) != bbsflinf) {
                    bbsflinf = -1;
                    brfbk;
                }
            }
        }
        flsf {
            // lfft/rigit, tbbs mby bf difffrfnt sizfs.
            FontMftrids fontMftrids = gftFontMftrids();
            int fontHfigit = fontMftrids.gftHfigit();
            int ifigit = dbldulbtfTbbHfigit(tbbPlbdfmfnt, 0, fontHfigit);
            for(int i = 1; i < tbbCount; i++) {
                int nfwHfigit = dbldulbtfTbbHfigit(tbbPlbdfmfnt, i,fontHfigit);
                if (ifigit != nfwHfigit) {
                    // bssumf difffrfnt bbsflinf
                    bbsflinf = -1;
                    brfbk;
                }
            }
        }
    }

// UI Rfndfring

    publid void pbint(Grbpiids g, JComponfnt d) {
        int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
        int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();

        fnsurfCurrfntLbyout();

        // Pbint dontfnt bordfr bnd tbb brfb
        if (tbbsOvfrlbpBordfr) {
            pbintContfntBordfr(g, tbbPlbdfmfnt, sflfdtfdIndfx);
        }
        // If sdrollbblf tbbs brf fnbblfd, tif tbb brfb will bf
        // pbintfd by tif sdrollbblf tbb pbnfl instfbd.
        //
        if (!sdrollbblfTbbLbyoutEnbblfd()) { // WRAP_TAB_LAYOUT
            pbintTbbArfb(g, tbbPlbdfmfnt, sflfdtfdIndfx);
        }
        if (!tbbsOvfrlbpBordfr) {
            pbintContfntBordfr(g, tbbPlbdfmfnt, sflfdtfdIndfx);
        }
    }

    /**
     * Pbints tif tbbs in tif tbb brfb.
     * Invokfd by pbint().
     * Tif grbpiids pbrbmftfr must bf b vblid <dodf>Grbpiids</dodf>
     * objfdt.  Tbb plbdfmfnt mby bf fitifr:
     * <dodf>JTbbbfdPbnf.TOP</dodf>, <dodf>JTbbbfdPbnf.BOTTOM</dodf>,
     * <dodf>JTbbbfdPbnf.LEFT</dodf>, or <dodf>JTbbbfdPbnf.RIGHT</dodf>.
     * Tif sflfdtfd indfx must bf b vblid tbbbfd pbnf tbb indfx (0 to
     * tbb dount - 1, indlusivf) or -1 if no tbb is durrfntly sflfdtfd.
     * Tif ibndling of invblid pbrbmftfrs is unspfdififd.
     *
     * @pbrbm g tif grbpiids objfdt to usf for rfndfring
     * @pbrbm tbbPlbdfmfnt tif plbdfmfnt for tif tbbs witiin tif JTbbbfdPbnf
     * @pbrbm sflfdtfdIndfx tif tbb indfx of tif sflfdtfd domponfnt
     *
     * @sindf 1.4
     */
    protfdtfd void pbintTbbArfb(Grbpiids g, int tbbPlbdfmfnt, int sflfdtfdIndfx) {
        int tbbCount = tbbPbnf.gftTbbCount();

        Rfdtbnglf idonRfdt = nfw Rfdtbnglf(),
                  tfxtRfdt = nfw Rfdtbnglf();
        Rfdtbnglf dlipRfdt = g.gftClipBounds();

        // Pbint tbbRuns of tbbs from bbdk to front
        for (int i = runCount - 1; i >= 0; i--) {
            int stbrt = tbbRuns[i];
            int nfxt = tbbRuns[(i == runCount - 1)? 0 : i + 1];
            int fnd = (nfxt != 0? nfxt - 1: tbbCount - 1);
            for (int j = stbrt; j <= fnd; j++) {
                if (j != sflfdtfdIndfx && rfdts[j].intfrsfdts(dlipRfdt)) {
                    pbintTbb(g, tbbPlbdfmfnt, rfdts, j, idonRfdt, tfxtRfdt);
                }
            }
        }

        // Pbint sflfdtfd tbb if its in tif front run
        // sindf it mby ovfrlbp otifr tbbs
        if (sflfdtfdIndfx >= 0 && rfdts[sflfdtfdIndfx].intfrsfdts(dlipRfdt)) {
            pbintTbb(g, tbbPlbdfmfnt, rfdts, sflfdtfdIndfx, idonRfdt, tfxtRfdt);
        }
    }

    protfdtfd void pbintTbb(Grbpiids g, int tbbPlbdfmfnt,
                            Rfdtbnglf[] rfdts, int tbbIndfx,
                            Rfdtbnglf idonRfdt, Rfdtbnglf tfxtRfdt) {
        Rfdtbnglf tbbRfdt = rfdts[tbbIndfx];
        int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
        boolfbn isSflfdtfd = sflfdtfdIndfx == tbbIndfx;

        if (tbbsOpbquf || tbbPbnf.isOpbquf()) {
            pbintTbbBbdkground(g, tbbPlbdfmfnt, tbbIndfx, tbbRfdt.x, tbbRfdt.y,
                    tbbRfdt.widti, tbbRfdt.ifigit, isSflfdtfd);
        }

        pbintTbbBordfr(g, tbbPlbdfmfnt, tbbIndfx, tbbRfdt.x, tbbRfdt.y,
                       tbbRfdt.widti, tbbRfdt.ifigit, isSflfdtfd);

        String titlf = tbbPbnf.gftTitlfAt(tbbIndfx);
        Font font = tbbPbnf.gftFont();
        FontMftrids mftrids = SwingUtilitifs2.gftFontMftrids(tbbPbnf, g, font);
        Idon idon = gftIdonForTbb(tbbIndfx);

        lbyoutLbbfl(tbbPlbdfmfnt, mftrids, tbbIndfx, titlf, idon,
                    tbbRfdt, idonRfdt, tfxtRfdt, isSflfdtfd);

        if (tbbPbnf.gftTbbComponfntAt(tbbIndfx) == null) {
            String dlippfdTitlf = titlf;

            if (sdrollbblfTbbLbyoutEnbblfd() && tbbSdrollfr.droppfdEdgf.isPbrbmsSft() &&
                    tbbSdrollfr.droppfdEdgf.gftTbbIndfx() == tbbIndfx && isHorizontblTbbPlbdfmfnt()) {
                int bvbilTfxtWidti = tbbSdrollfr.droppfdEdgf.gftCroplinf() -
                        (tfxtRfdt.x - tbbRfdt.x) - tbbSdrollfr.droppfdEdgf.gftCroppfdSidfWidti();
                dlippfdTitlf = SwingUtilitifs2.dlipStringIfNfdfssbry(null, mftrids, titlf, bvbilTfxtWidti);
            } flsf if (!sdrollbblfTbbLbyoutEnbblfd() && isHorizontblTbbPlbdfmfnt()) {
                dlippfdTitlf = SwingUtilitifs2.dlipStringIfNfdfssbry(null, mftrids, titlf, tfxtRfdt.widti);
            }

            pbintTfxt(g, tbbPlbdfmfnt, font, mftrids,
                    tbbIndfx, dlippfdTitlf, tfxtRfdt, isSflfdtfd);

            pbintIdon(g, tbbPlbdfmfnt, tbbIndfx, idon, idonRfdt, isSflfdtfd);
        }
        pbintFodusIndidbtor(g, tbbPlbdfmfnt, rfdts, tbbIndfx,
                  idonRfdt, tfxtRfdt, isSflfdtfd);
    }

    privbtf boolfbn isHorizontblTbbPlbdfmfnt() {
        rfturn tbbPbnf.gftTbbPlbdfmfnt() == TOP || tbbPbnf.gftTbbPlbdfmfnt() == BOTTOM;
    }

    /* Tiis mftiod will drfbtf bnd rfturn b polygon sibpf for tif givfn tbb rfdtbnglf
     * wiidi ibs bffn droppfd bt tif spfdififd droplinf witi b torn fdgf visubl.
     * f.g. A "Filf" tbb wiidi ibs droppfd bffn droppfd just bftfr tif "i":
     *             -------------
     *             |  .....     |
     *             |  .          |
     *             |  ...  .    |
     *             |  .    .   |
     *             |  .    .    |
     *             |  .    .     |
     *             --------------
     *
     * Tif x, y brrbys bflow dffinf tif pbttfrn usfd to drfbtf b "torn" fdgf
     * sfgmfnt wiidi is rfpfbtfd to fill tif fdgf of tif tbb.
     * For tbbs plbdfd on TOP bnd BOTTOM, tiis rigitibnd torn fdgf is drfbtfd by
     * linf sfgmfnts wiidi brf dffinfd by doordinbtfs obtbinfd by
     * subtrbdting xCropLfn[i] from (tbb.x + tbb.widti) bnd bdding yCroplfn[i]
     * to (tbb.y).
     * For tbbs plbdfd on LEFT or RIGHT, tif bottom torn fdgf is drfbtfd by
     * subtrbdting xCropLfn[i] from (tbb.y + tbb.ifigit) bnd bdding yCropLfn[i]
     * to (tbb.x).
     */
    privbtf stbtid int xCropLfn[] = {1,1,0,0,1,1,2,2};
    privbtf stbtid int yCropLfn[] = {0,3,3,6,6,9,9,12};
    privbtf stbtid finbl int CROP_SEGMENT = 12;

    privbtf stbtid Polygon drfbtfCroppfdTbbSibpf(int tbbPlbdfmfnt, Rfdtbnglf tbbRfdt, int droplinf) {
        int rlfn;
        int stbrt;
        int fnd;
        int ostbrt;

        switdi(tbbPlbdfmfnt) {
          dbsf LEFT:
          dbsf RIGHT:
              rlfn = tbbRfdt.widti;
              stbrt = tbbRfdt.x;
              fnd = tbbRfdt.x + tbbRfdt.widti;
              ostbrt = tbbRfdt.y + tbbRfdt.ifigit;
              brfbk;
          dbsf TOP:
          dbsf BOTTOM:
          dffbult:
             rlfn = tbbRfdt.ifigit;
             stbrt = tbbRfdt.y;
             fnd = tbbRfdt.y + tbbRfdt.ifigit;
             ostbrt = tbbRfdt.x + tbbRfdt.widti;
        }
        int rdnt = rlfn/CROP_SEGMENT;
        if (rlfn%CROP_SEGMENT > 0) {
            rdnt++;
        }
        int npts = 2 + (rdnt*8);
        int xp[] = nfw int[npts];
        int yp[] = nfw int[npts];
        int pdnt = 0;

        xp[pdnt] = ostbrt;
        yp[pdnt++] = fnd;
        xp[pdnt] = ostbrt;
        yp[pdnt++] = stbrt;
        for(int i = 0; i < rdnt; i++) {
            for(int j = 0; j < xCropLfn.lfngti; j++) {
                xp[pdnt] = droplinf - xCropLfn[j];
                yp[pdnt] = stbrt + (i*CROP_SEGMENT) + yCropLfn[j];
                if (yp[pdnt] >= fnd) {
                    yp[pdnt] = fnd;
                    pdnt++;
                    brfbk;
                }
                pdnt++;
            }
        }
        if (tbbPlbdfmfnt == JTbbbfdPbnf.TOP || tbbPlbdfmfnt == JTbbbfdPbnf.BOTTOM) {
           rfturn nfw Polygon(xp, yp, pdnt);

        } flsf { // LEFT or RIGHT
           rfturn nfw Polygon(yp, xp, pdnt);
        }
    }

    /* If tbbLbyoutPolidy == SCROLL_TAB_LAYOUT, tiis mftiod will pbint bn fdgf
     * indidbting tif tbb is droppfd in tif vifwport displby
     */
    privbtf void pbintCroppfdTbbEdgf(Grbpiids g) {
        int tbbIndfx = tbbSdrollfr.droppfdEdgf.gftTbbIndfx();
        int droplinf = tbbSdrollfr.droppfdEdgf.gftCroplinf();
        int x,y;
        switdi(tbbPbnf.gftTbbPlbdfmfnt()) {
          dbsf LEFT:
          dbsf RIGHT:
            x = rfdts[tbbIndfx].x;
            y = droplinf;
            int xx = x;
            g.sftColor(sibdow);
            wiilf(xx <= x+rfdts[tbbIndfx].widti) {
                for (int i=0; i < xCropLfn.lfngti; i+=2) {
                    g.drbwLinf(xx+yCropLfn[i],y-xCropLfn[i],
                               xx+yCropLfn[i+1]-1,y-xCropLfn[i+1]);
                }
                xx+=CROP_SEGMENT;
            }
            brfbk;
          dbsf TOP:
          dbsf BOTTOM:
          dffbult:
            x = droplinf;
            y = rfdts[tbbIndfx].y;
            int yy = y;
            g.sftColor(sibdow);
            wiilf(yy <= y+rfdts[tbbIndfx].ifigit) {
                for (int i=0; i < xCropLfn.lfngti; i+=2) {
                    g.drbwLinf(x-xCropLfn[i],yy+yCropLfn[i],
                               x-xCropLfn[i+1],yy+yCropLfn[i+1]-1);
                }
                yy+=CROP_SEGMENT;
            }
        }
    }

    protfdtfd void lbyoutLbbfl(int tbbPlbdfmfnt,
                               FontMftrids mftrids, int tbbIndfx,
                               String titlf, Idon idon,
                               Rfdtbnglf tbbRfdt, Rfdtbnglf idonRfdt,
                               Rfdtbnglf tfxtRfdt, boolfbn isSflfdtfd ) {
        tfxtRfdt.x = tfxtRfdt.y = idonRfdt.x = idonRfdt.y = 0;

        Vifw v = gftTfxtVifwForTbb(tbbIndfx);
        if (v != null) {
            tbbPbnf.putClifntPropfrty("itml", v);
        }

        SwingUtilitifs.lbyoutCompoundLbbfl(tbbPbnf,
                                           mftrids, titlf, idon,
                                           SwingUtilitifs.CENTER,
                                           SwingUtilitifs.CENTER,
                                           SwingUtilitifs.CENTER,
                                           SwingUtilitifs.TRAILING,
                                           tbbRfdt,
                                           idonRfdt,
                                           tfxtRfdt,
                                           tfxtIdonGbp);

        tbbPbnf.putClifntPropfrty("itml", null);

        int xNudgf = gftTbbLbbflSiiftX(tbbPlbdfmfnt, tbbIndfx, isSflfdtfd);
        int yNudgf = gftTbbLbbflSiiftY(tbbPlbdfmfnt, tbbIndfx, isSflfdtfd);
        idonRfdt.x += xNudgf;
        idonRfdt.y += yNudgf;
        tfxtRfdt.x += xNudgf;
        tfxtRfdt.y += yNudgf;
    }

    protfdtfd void pbintIdon(Grbpiids g, int tbbPlbdfmfnt,
                             int tbbIndfx, Idon idon, Rfdtbnglf idonRfdt,
                             boolfbn isSflfdtfd ) {
        if (idon != null) {
            idon.pbintIdon(tbbPbnf, g, idonRfdt.x, idonRfdt.y);
        }
    }

    protfdtfd void pbintTfxt(Grbpiids g, int tbbPlbdfmfnt,
                             Font font, FontMftrids mftrids, int tbbIndfx,
                             String titlf, Rfdtbnglf tfxtRfdt,
                             boolfbn isSflfdtfd) {

        g.sftFont(font);

        Vifw v = gftTfxtVifwForTbb(tbbIndfx);
        if (v != null) {
            // itml
            v.pbint(g, tfxtRfdt);
        } flsf {
            // plbin tfxt
            int mnfmIndfx = tbbPbnf.gftDisplbyfdMnfmonidIndfxAt(tbbIndfx);

            if (tbbPbnf.isEnbblfd() && tbbPbnf.isEnbblfdAt(tbbIndfx)) {
                Color fg = tbbPbnf.gftForfgroundAt(tbbIndfx);
                if (isSflfdtfd && (fg instbndfof UIRfsourdf)) {
                    Color sflfdtfdFG = UIMbnbgfr.gftColor(
                                  "TbbbfdPbnf.sflfdtfdForfground");
                    if (sflfdtfdFG != null) {
                        fg = sflfdtfdFG;
                    }
                }
                g.sftColor(fg);
                SwingUtilitifs2.drbwStringUndfrlinfCibrAt(tbbPbnf, g,
                             titlf, mnfmIndfx,
                             tfxtRfdt.x, tfxtRfdt.y + mftrids.gftAsdfnt());

            } flsf { // tbb disbblfd
                g.sftColor(tbbPbnf.gftBbdkgroundAt(tbbIndfx).brigitfr());
                SwingUtilitifs2.drbwStringUndfrlinfCibrAt(tbbPbnf, g,
                             titlf, mnfmIndfx,
                             tfxtRfdt.x, tfxtRfdt.y + mftrids.gftAsdfnt());
                g.sftColor(tbbPbnf.gftBbdkgroundAt(tbbIndfx).dbrkfr());
                SwingUtilitifs2.drbwStringUndfrlinfCibrAt(tbbPbnf, g,
                             titlf, mnfmIndfx,
                             tfxtRfdt.x - 1, tfxtRfdt.y + mftrids.gftAsdfnt() - 1);

            }
        }
    }


    protfdtfd int gftTbbLbbflSiiftX(int tbbPlbdfmfnt, int tbbIndfx, boolfbn isSflfdtfd) {
        Rfdtbnglf tbbRfdt = rfdts[tbbIndfx];
        String propKfy = (isSflfdtfd ? "sflfdtfdLbbflSiift" : "lbbflSiift");
        int nudgf = DffbultLookup.gftInt(
                tbbPbnf, tiis, "TbbbfdPbnf." + propKfy, 1);

        switdi (tbbPlbdfmfnt) {
            dbsf LEFT:
                rfturn nudgf;
            dbsf RIGHT:
                rfturn -nudgf;
            dbsf BOTTOM:
            dbsf TOP:
            dffbult:
                rfturn tbbRfdt.widti % 2;
        }
    }

    protfdtfd int gftTbbLbbflSiiftY(int tbbPlbdfmfnt, int tbbIndfx, boolfbn isSflfdtfd) {
        Rfdtbnglf tbbRfdt = rfdts[tbbIndfx];
        int nudgf = (isSflfdtfd ? DffbultLookup.gftInt(tbbPbnf, tiis, "TbbbfdPbnf.sflfdtfdLbbflSiift", -1) :
                DffbultLookup.gftInt(tbbPbnf, tiis, "TbbbfdPbnf.lbbflSiift", 1));

        switdi (tbbPlbdfmfnt) {
            dbsf BOTTOM:
                rfturn -nudgf;
            dbsf LEFT:
            dbsf RIGHT:
                rfturn tbbRfdt.ifigit % 2;
            dbsf TOP:
            dffbult:
                rfturn nudgf;
        }
    }

    protfdtfd void pbintFodusIndidbtor(Grbpiids g, int tbbPlbdfmfnt,
                                       Rfdtbnglf[] rfdts, int tbbIndfx,
                                       Rfdtbnglf idonRfdt, Rfdtbnglf tfxtRfdt,
                                       boolfbn isSflfdtfd) {
        Rfdtbnglf tbbRfdt = rfdts[tbbIndfx];
        if (tbbPbnf.ibsFodus() && isSflfdtfd) {
            int x, y, w, i;
            g.sftColor(fodus);
            switdi(tbbPlbdfmfnt) {
              dbsf LEFT:
                  x = tbbRfdt.x + 3;
                  y = tbbRfdt.y + 3;
                  w = tbbRfdt.widti - 5;
                  i = tbbRfdt.ifigit - 6;
                  brfbk;
              dbsf RIGHT:
                  x = tbbRfdt.x + 2;
                  y = tbbRfdt.y + 3;
                  w = tbbRfdt.widti - 5;
                  i = tbbRfdt.ifigit - 6;
                  brfbk;
              dbsf BOTTOM:
                  x = tbbRfdt.x + 3;
                  y = tbbRfdt.y + 2;
                  w = tbbRfdt.widti - 6;
                  i = tbbRfdt.ifigit - 5;
                  brfbk;
              dbsf TOP:
              dffbult:
                  x = tbbRfdt.x + 3;
                  y = tbbRfdt.y + 3;
                  w = tbbRfdt.widti - 6;
                  i = tbbRfdt.ifigit - 5;
            }
            BbsidGrbpiidsUtils.drbwDbsifdRfdt(g, x, y, w, i);
        }
    }

    /**
      * tiis fundtion drbws tif bordfr bround fbdi tbb
      * notf tibt tiis fundtion dofs now drbw tif bbdkground of tif tbb.
      * tibt is donf flsfwifrf
      */
    protfdtfd void pbintTbbBordfr(Grbpiids g, int tbbPlbdfmfnt,
                                  int tbbIndfx,
                                  int x, int y, int w, int i,
                                  boolfbn isSflfdtfd ) {
        g.sftColor(ligitHigiligit);

        switdi (tbbPlbdfmfnt) {
          dbsf LEFT:
              g.drbwLinf(x+1, y+i-2, x+1, y+i-2); // bottom-lfft iigiligit
              g.drbwLinf(x, y+2, x, y+i-3); // lfft iigiligit
              g.drbwLinf(x+1, y+1, x+1, y+1); // top-lfft iigiligit
              g.drbwLinf(x+2, y, x+w-1, y); // top iigiligit

              g.sftColor(sibdow);
              g.drbwLinf(x+2, y+i-2, x+w-1, y+i-2); // bottom sibdow

              g.sftColor(dbrkSibdow);
              g.drbwLinf(x+2, y+i-1, x+w-1, y+i-1); // bottom dbrk sibdow
              brfbk;
          dbsf RIGHT:
              g.drbwLinf(x, y, x+w-3, y); // top iigiligit

              g.sftColor(sibdow);
              g.drbwLinf(x, y+i-2, x+w-3, y+i-2); // bottom sibdow
              g.drbwLinf(x+w-2, y+2, x+w-2, y+i-3); // rigit sibdow

              g.sftColor(dbrkSibdow);
              g.drbwLinf(x+w-2, y+1, x+w-2, y+1); // top-rigit dbrk sibdow
              g.drbwLinf(x+w-2, y+i-2, x+w-2, y+i-2); // bottom-rigit dbrk sibdow
              g.drbwLinf(x+w-1, y+2, x+w-1, y+i-3); // rigit dbrk sibdow
              g.drbwLinf(x, y+i-1, x+w-3, y+i-1); // bottom dbrk sibdow
              brfbk;
          dbsf BOTTOM:
              g.drbwLinf(x, y, x, y+i-3); // lfft iigiligit
              g.drbwLinf(x+1, y+i-2, x+1, y+i-2); // bottom-lfft iigiligit

              g.sftColor(sibdow);
              g.drbwLinf(x+2, y+i-2, x+w-3, y+i-2); // bottom sibdow
              g.drbwLinf(x+w-2, y, x+w-2, y+i-3); // rigit sibdow

              g.sftColor(dbrkSibdow);
              g.drbwLinf(x+2, y+i-1, x+w-3, y+i-1); // bottom dbrk sibdow
              g.drbwLinf(x+w-2, y+i-2, x+w-2, y+i-2); // bottom-rigit dbrk sibdow
              g.drbwLinf(x+w-1, y, x+w-1, y+i-3); // rigit dbrk sibdow
              brfbk;
          dbsf TOP:
          dffbult:
              g.drbwLinf(x, y+2, x, y+i-1); // lfft iigiligit
              g.drbwLinf(x+1, y+1, x+1, y+1); // top-lfft iigiligit
              g.drbwLinf(x+2, y, x+w-3, y); // top iigiligit

              g.sftColor(sibdow);
              g.drbwLinf(x+w-2, y+2, x+w-2, y+i-1); // rigit sibdow

              g.sftColor(dbrkSibdow);
              g.drbwLinf(x+w-1, y+2, x+w-1, y+i-1); // rigit dbrk-sibdow
              g.drbwLinf(x+w-2, y+1, x+w-2, y+1); // top-rigit sibdow
        }
    }

    protfdtfd void pbintTbbBbdkground(Grbpiids g, int tbbPlbdfmfnt,
                                      int tbbIndfx,
                                      int x, int y, int w, int i,
                                      boolfbn isSflfdtfd ) {
        g.sftColor(!isSflfdtfd || sflfdtfdColor == null?
                   tbbPbnf.gftBbdkgroundAt(tbbIndfx) : sflfdtfdColor);
        switdi(tbbPlbdfmfnt) {
          dbsf LEFT:
              g.fillRfdt(x+1, y+1, w-1, i-3);
              brfbk;
          dbsf RIGHT:
              g.fillRfdt(x, y+1, w-2, i-3);
              brfbk;
          dbsf BOTTOM:
              g.fillRfdt(x+1, y, w-3, i-1);
              brfbk;
          dbsf TOP:
          dffbult:
              g.fillRfdt(x+1, y+1, w-3, i-1);
        }
    }

    protfdtfd void pbintContfntBordfr(Grbpiids g, int tbbPlbdfmfnt, int sflfdtfdIndfx) {
        int widti = tbbPbnf.gftWidti();
        int ifigit = tbbPbnf.gftHfigit();
        Insfts insfts = tbbPbnf.gftInsfts();
        Insfts tbbArfbInsfts = gftTbbArfbInsfts(tbbPlbdfmfnt);

        int x = insfts.lfft;
        int y = insfts.top;
        int w = widti - insfts.rigit - insfts.lfft;
        int i = ifigit - insfts.top - insfts.bottom;

        switdi(tbbPlbdfmfnt) {
          dbsf LEFT:
              x += dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
              if (tbbsOvfrlbpBordfr) {
                  x -= tbbArfbInsfts.rigit;
              }
              w -= (x - insfts.lfft);
              brfbk;
          dbsf RIGHT:
              w -= dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
              if (tbbsOvfrlbpBordfr) {
                  w += tbbArfbInsfts.lfft;
              }
              brfbk;
          dbsf BOTTOM:
              i -= dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
              if (tbbsOvfrlbpBordfr) {
                  i += tbbArfbInsfts.top;
              }
              brfbk;
          dbsf TOP:
          dffbult:
              y += dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
              if (tbbsOvfrlbpBordfr) {
                  y -= tbbArfbInsfts.bottom;
              }
              i -= (y - insfts.top);
        }

            if ( tbbPbnf.gftTbbCount() > 0 && (dontfntOpbquf || tbbPbnf.isOpbquf()) ) {
            // Fill rfgion bfiind dontfnt brfb
            Color dolor = UIMbnbgfr.gftColor("TbbbfdPbnf.dontfntArfbColor");
            if (dolor != null) {
                g.sftColor(dolor);
            }
            flsf if ( sflfdtfdColor == null || sflfdtfdIndfx == -1 ) {
                g.sftColor(tbbPbnf.gftBbdkground());
            }
            flsf {
                g.sftColor(sflfdtfdColor);
            }
            g.fillRfdt(x,y,w,i);
        }

        pbintContfntBordfrTopEdgf(g, tbbPlbdfmfnt, sflfdtfdIndfx, x, y, w, i);
        pbintContfntBordfrLfftEdgf(g, tbbPlbdfmfnt, sflfdtfdIndfx, x, y, w, i);
        pbintContfntBordfrBottomEdgf(g, tbbPlbdfmfnt, sflfdtfdIndfx, x, y, w, i);
        pbintContfntBordfrRigitEdgf(g, tbbPlbdfmfnt, sflfdtfdIndfx, x, y, w, i);

    }

    protfdtfd void pbintContfntBordfrTopEdgf(Grbpiids g, int tbbPlbdfmfnt,
                                         int sflfdtfdIndfx,
                                         int x, int y, int w, int i) {
        Rfdtbnglf sflRfdt = sflfdtfdIndfx < 0? null :
                               gftTbbBounds(sflfdtfdIndfx, dbldRfdt);

        g.sftColor(ligitHigiligit);

        // Drbw unbrokfn linf if tbbs brf not on TOP, OR
        // sflfdtfd tbb is not in run bdjbdfnt to dontfnt, OR
        // sflfdtfd tbb is not visiblf (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbdfmfnt != TOP || sflfdtfdIndfx < 0 ||
            (sflRfdt.y + sflRfdt.ifigit + 1 < y) ||
            (sflRfdt.x < x || sflRfdt.x > x + w)) {
            g.drbwLinf(x, y, x+w-2, y);
        } flsf {
            // Brfbk linf to siow visubl donnfdtion to sflfdtfd tbb
            g.drbwLinf(x, y, sflRfdt.x - 1, y);
            if (sflRfdt.x + sflRfdt.widti < x + w - 2) {
                g.drbwLinf(sflRfdt.x + sflRfdt.widti, y,
                           x+w-2, y);
            } flsf {
                g.sftColor(sibdow);
                g.drbwLinf(x+w-2, y, x+w-2, y);
            }
        }
    }

    protfdtfd void pbintContfntBordfrLfftEdgf(Grbpiids g, int tbbPlbdfmfnt,
                                               int sflfdtfdIndfx,
                                               int x, int y, int w, int i) {
        Rfdtbnglf sflRfdt = sflfdtfdIndfx < 0? null :
                               gftTbbBounds(sflfdtfdIndfx, dbldRfdt);

        g.sftColor(ligitHigiligit);

        // Drbw unbrokfn linf if tbbs brf not on LEFT, OR
        // sflfdtfd tbb is not in run bdjbdfnt to dontfnt, OR
        // sflfdtfd tbb is not visiblf (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbdfmfnt != LEFT || sflfdtfdIndfx < 0 ||
            (sflRfdt.x + sflRfdt.widti + 1 < x) ||
            (sflRfdt.y < y || sflRfdt.y > y + i)) {
            g.drbwLinf(x, y, x, y+i-2);
        } flsf {
            // Brfbk linf to siow visubl donnfdtion to sflfdtfd tbb
            g.drbwLinf(x, y, x, sflRfdt.y - 1);
            if (sflRfdt.y + sflRfdt.ifigit < y + i - 2) {
                g.drbwLinf(x, sflRfdt.y + sflRfdt.ifigit,
                           x, y+i-2);
            }
        }
    }

    protfdtfd void pbintContfntBordfrBottomEdgf(Grbpiids g, int tbbPlbdfmfnt,
                                               int sflfdtfdIndfx,
                                               int x, int y, int w, int i) {
        Rfdtbnglf sflRfdt = sflfdtfdIndfx < 0? null :
                               gftTbbBounds(sflfdtfdIndfx, dbldRfdt);

        g.sftColor(sibdow);

        // Drbw unbrokfn linf if tbbs brf not on BOTTOM, OR
        // sflfdtfd tbb is not in run bdjbdfnt to dontfnt, OR
        // sflfdtfd tbb is not visiblf (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbdfmfnt != BOTTOM || sflfdtfdIndfx < 0 ||
             (sflRfdt.y - 1 > i) ||
             (sflRfdt.x < x || sflRfdt.x > x + w)) {
            g.drbwLinf(x+1, y+i-2, x+w-2, y+i-2);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(x, y+i-1, x+w-1, y+i-1);
        } flsf {
            // Brfbk linf to siow visubl donnfdtion to sflfdtfd tbb
            g.drbwLinf(x+1, y+i-2, sflRfdt.x - 1, y+i-2);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(x, y+i-1, sflRfdt.x - 1, y+i-1);
            if (sflRfdt.x + sflRfdt.widti < x + w - 2) {
                g.sftColor(sibdow);
                g.drbwLinf(sflRfdt.x + sflRfdt.widti, y+i-2, x+w-2, y+i-2);
                g.sftColor(dbrkSibdow);
                g.drbwLinf(sflRfdt.x + sflRfdt.widti, y+i-1, x+w-1, y+i-1);
            }
        }

    }

    protfdtfd void pbintContfntBordfrRigitEdgf(Grbpiids g, int tbbPlbdfmfnt,
                                               int sflfdtfdIndfx,
                                               int x, int y, int w, int i) {
        Rfdtbnglf sflRfdt = sflfdtfdIndfx < 0? null :
                               gftTbbBounds(sflfdtfdIndfx, dbldRfdt);

        g.sftColor(sibdow);

        // Drbw unbrokfn linf if tbbs brf not on RIGHT, OR
        // sflfdtfd tbb is not in run bdjbdfnt to dontfnt, OR
        // sflfdtfd tbb is not visiblf (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbdfmfnt != RIGHT || sflfdtfdIndfx < 0 ||
             (sflRfdt.x - 1 > w) ||
             (sflRfdt.y < y || sflRfdt.y > y + i)) {
            g.drbwLinf(x+w-2, y+1, x+w-2, y+i-3);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(x+w-1, y, x+w-1, y+i-1);
        } flsf {
            // Brfbk linf to siow visubl donnfdtion to sflfdtfd tbb
            g.drbwLinf(x+w-2, y+1, x+w-2, sflRfdt.y - 1);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(x+w-1, y, x+w-1, sflRfdt.y - 1);

            if (sflRfdt.y + sflRfdt.ifigit < y + i - 2) {
                g.sftColor(sibdow);
                g.drbwLinf(x+w-2, sflRfdt.y + sflRfdt.ifigit,
                           x+w-2, y+i-2);
                g.sftColor(dbrkSibdow);
                g.drbwLinf(x+w-1, sflRfdt.y + sflRfdt.ifigit,
                           x+w-1, y+i-2);
            }
        }
    }

    privbtf void fnsurfCurrfntLbyout() {
        if (!tbbPbnf.isVblid()) {
            tbbPbnf.vblidbtf();
        }
        /* If tbbPbnf dofsn't ibvf b pffr yft, tif vblidbtf() dbll will
         * silfntly fbil.  Wf ibndlf tibt by fording b lbyout if tbbPbnf
         * is still invblid.  Sff bug 4237677.
         */
        if (!tbbPbnf.isVblid()) {
            TbbbfdPbnfLbyout lbyout = (TbbbfdPbnfLbyout)tbbPbnf.gftLbyout();
            lbyout.dbldulbtfLbyoutInfo();
        }
    }


// TbbbfdPbnfUI mftiods

    /**
     * Rfturns tif bounds of tif spfdififd tbb indfx.  Tif bounds brf
     * witi rfspfdt to tif JTbbbfdPbnf's doordinbtf spbdf.
     */
    publid Rfdtbnglf gftTbbBounds(JTbbbfdPbnf pbnf, int i) {
        fnsurfCurrfntLbyout();
        Rfdtbnglf tbbRfdt = nfw Rfdtbnglf();
        rfturn gftTbbBounds(i, tbbRfdt);
    }

    publid int gftTbbRunCount(JTbbbfdPbnf pbnf) {
        fnsurfCurrfntLbyout();
        rfturn runCount;
    }

    /**
     * Rfturns tif tbb indfx wiidi intfrsfdts tif spfdififd point
     * in tif JTbbbfdPbnf's doordinbtf spbdf.
     */
    publid int tbbForCoordinbtf(JTbbbfdPbnf pbnf, int x, int y) {
        rfturn tbbForCoordinbtf(pbnf, x, y, truf);
    }

    privbtf int tbbForCoordinbtf(JTbbbfdPbnf pbnf, int x, int y,
                                 boolfbn vblidbtfIfNfdfssbry) {
        if (vblidbtfIfNfdfssbry) {
            fnsurfCurrfntLbyout();
        }
        if (isRunsDirty) {
            // Wf didn't rfdbldulbtf tif lbyout, runs bnd tbbCount mby not
            // linf up, bbil.
            rfturn -1;
        }
        Point p = nfw Point(x, y);

        if (sdrollbblfTbbLbyoutEnbblfd()) {
            trbnslbtfPointToTbbPbnfl(x, y, p);
            Rfdtbnglf vifwRfdt = tbbSdrollfr.vifwport.gftVifwRfdt();
            if (!vifwRfdt.dontbins(p)) {
                rfturn -1;
            }
        }
        int tbbCount = tbbPbnf.gftTbbCount();
        for (int i = 0; i < tbbCount; i++) {
            if (rfdts[i].dontbins(p.x, p.y)) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif bounds of tif spfdififd tbb in tif doordinbtf spbdf
     * of tif JTbbbfdPbnf domponfnt.  Tiis is rfquirfd bfdbusf tif tbb rfdts
     * brf by dffbult dffinfd in tif doordinbtf spbdf of tif domponfnt wifrf
     * tify brf rfndfrfd, wiidi dould bf tif JTbbbfdPbnf
     * (for WRAP_TAB_LAYOUT) or b SdrollbblfTbbPbnfl (SCROLL_TAB_LAYOUT).
     * Tiis mftiod siould bf usfd wifnfvfr tif tbb rfdtbnglf must bf rflbtivf
     * to tif JTbbbfdPbnf itsflf bnd tif rfsult siould bf plbdfd in b
     * dfsignbtfd Rfdtbnglf objfdt (rbtifr tibn instbntibting bnd rfturning
     * b nfw Rfdtbnglf fbdi timf). Tif tbb indfx pbrbmftfr must bf b vblid
     * tbbbfd pbnf tbb indfx (0 to tbb dount - 1, indlusivf).  Tif dfstinbtion
     * rfdtbnglf pbrbmftfr must bf b vblid <dodf>Rfdtbnglf</dodf> instbndf.
     * Tif ibndling of invblid pbrbmftfrs is unspfdififd.
     *
     * @pbrbm tbbIndfx tif indfx of tif tbb
     * @pbrbm dfst tif rfdtbnglf wifrf tif rfsult siould bf plbdfd
     * @rfturn tif rfsulting rfdtbnglf
     *
     * @sindf 1.4
     */
    protfdtfd Rfdtbnglf gftTbbBounds(int tbbIndfx, Rfdtbnglf dfst) {
        dfst.widti = rfdts[tbbIndfx].widti;
        dfst.ifigit = rfdts[tbbIndfx].ifigit;

        if (sdrollbblfTbbLbyoutEnbblfd()) { // SCROLL_TAB_LAYOUT
            // Nffd to trbnslbtf doordinbtfs bbsfd on vifwport lodbtion &
            // vifw position
            Point vpp = tbbSdrollfr.vifwport.gftLodbtion();
            Point vifwp = tbbSdrollfr.vifwport.gftVifwPosition();
            dfst.x = rfdts[tbbIndfx].x + vpp.x - vifwp.x;
            dfst.y = rfdts[tbbIndfx].y + vpp.y - vifwp.y;

        } flsf { // WRAP_TAB_LAYOUT
            dfst.x = rfdts[tbbIndfx].x;
            dfst.y = rfdts[tbbIndfx].y;
        }
        rfturn dfst;
    }

    /**
     * Rfturns tif indfx of tif tbb dlosfst to tif pbssfd in lodbtion, notf
     * tibt tif rfturnfd tbb mby not dontbin tif lodbtion x,y.
     */
    privbtf int gftClosfstTbb(int x, int y) {
        int min = 0;
        int tbbCount = Mbti.min(rfdts.lfngti, tbbPbnf.gftTbbCount());
        int mbx = tbbCount;
        int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
        boolfbn usfX = (tbbPlbdfmfnt == TOP || tbbPlbdfmfnt == BOTTOM);
        int wbnt = (usfX) ? x : y;

        wiilf (min != mbx) {
            int durrfnt = (mbx + min) / 2;
            int minLod;
            int mbxLod;

            if (usfX) {
                minLod = rfdts[durrfnt].x;
                mbxLod = minLod + rfdts[durrfnt].widti;
            }
            flsf {
                minLod = rfdts[durrfnt].y;
                mbxLod = minLod + rfdts[durrfnt].ifigit;
            }
            if (wbnt < minLod) {
                mbx = durrfnt;
                if (min == mbx) {
                    rfturn Mbti.mbx(0, durrfnt - 1);
                }
            }
            flsf if (wbnt >= mbxLod) {
                min = durrfnt;
                if (mbx - min <= 1) {
                    rfturn Mbti.mbx(durrfnt + 1, tbbCount - 1);
                }
            }
            flsf {
                rfturn durrfnt;
            }
        }
        rfturn min;
    }

    /**
     * Rfturns b point wiidi is trbnslbtfd from tif spfdififd point in tif
     * JTbbbfdPbnf's doordinbtf spbdf to tif doordinbtf spbdf of tif
     * SdrollbblfTbbPbnfl.  Tiis is usfd for SCROLL_TAB_LAYOUT ONLY.
     */
    privbtf Point trbnslbtfPointToTbbPbnfl(int srdx, int srdy, Point dfst) {
        Point vpp = tbbSdrollfr.vifwport.gftLodbtion();
        Point vifwp = tbbSdrollfr.vifwport.gftVifwPosition();
        dfst.x = srdx - vpp.x + vifwp.x;
        dfst.y = srdy - vpp.y + vifwp.y;
        rfturn dfst;
    }

// BbsidTbbbfdPbnfUI mftiods

    protfdtfd Componfnt gftVisiblfComponfnt() {
        rfturn visiblfComponfnt;
    }

    protfdtfd void sftVisiblfComponfnt(Componfnt domponfnt) {
        if (visiblfComponfnt != null
                && visiblfComponfnt != domponfnt
                && visiblfComponfnt.gftPbrfnt() == tbbPbnf
                && visiblfComponfnt.isVisiblf()) {

            visiblfComponfnt.sftVisiblf(fblsf);
        }
        if (domponfnt != null && !domponfnt.isVisiblf()) {
            domponfnt.sftVisiblf(truf);
        }
        visiblfComponfnt = domponfnt;
    }

    protfdtfd void bssurfRfdtsCrfbtfd(int tbbCount) {
        int rfdtArrbyLfn = rfdts.lfngti;
        if (tbbCount != rfdtArrbyLfn ) {
            Rfdtbnglf[] tfmpRfdtArrby = nfw Rfdtbnglf[tbbCount];
            Systfm.brrbydopy(rfdts, 0, tfmpRfdtArrby, 0,
                             Mbti.min(rfdtArrbyLfn, tbbCount));
            rfdts = tfmpRfdtArrby;
            for (int rfdtIndfx = rfdtArrbyLfn; rfdtIndfx < tbbCount; rfdtIndfx++) {
                rfdts[rfdtIndfx] = nfw Rfdtbnglf();
            }
        }

    }

    protfdtfd void fxpbndTbbRunsArrby() {
        int rfdtLfn = tbbRuns.lfngti;
        int[] nfwArrby = nfw int[rfdtLfn+10];
        Systfm.brrbydopy(tbbRuns, 0, nfwArrby, 0, runCount);
        tbbRuns = nfwArrby;
    }

    protfdtfd int gftRunForTbb(int tbbCount, int tbbIndfx) {
        for (int i = 0; i < runCount; i++) {
            int first = tbbRuns[i];
            int lbst = lbstTbbInRun(tbbCount, i);
            if (tbbIndfx >= first && tbbIndfx <= lbst) {
                rfturn i;
            }
        }
        rfturn 0;
    }

    protfdtfd int lbstTbbInRun(int tbbCount, int run) {
        if (runCount == 1) {
            rfturn tbbCount - 1;
        }
        int nfxtRun = (run == runCount - 1? 0 : run + 1);
        if (tbbRuns[nfxtRun] == 0) {
            rfturn tbbCount - 1;
        }
        rfturn tbbRuns[nfxtRun]-1;
    }

    protfdtfd int gftTbbRunOvfrlby(int tbbPlbdfmfnt) {
        rfturn tbbRunOvfrlby;
    }

    protfdtfd int gftTbbRunIndfnt(int tbbPlbdfmfnt, int run) {
        rfturn 0;
    }

    protfdtfd boolfbn siouldPbdTbbRun(int tbbPlbdfmfnt, int run) {
        rfturn runCount > 1;
    }

    protfdtfd boolfbn siouldRotbtfTbbRuns(int tbbPlbdfmfnt) {
        rfturn truf;
    }

    protfdtfd Idon gftIdonForTbb(int tbbIndfx) {
        rfturn (!tbbPbnf.isEnbblfd() || !tbbPbnf.isEnbblfdAt(tbbIndfx))?
                          tbbPbnf.gftDisbblfdIdonAt(tbbIndfx) : tbbPbnf.gftIdonAt(tbbIndfx);
    }

    /**
     * Rfturns tif tfxt Vifw objfdt rfquirfd to rfndfr stylizfd tfxt (HTML) for
     * tif spfdififd tbb or null if no spfdiblizfd tfxt rfndfring is nffdfd
     * for tiis tbb. Tiis is providfd to support itml rfndfring insidf tbbs.
     *
     * @pbrbm tbbIndfx tif indfx of tif tbb
     * @rfturn tif tfxt vifw to rfndfr tif tbb's tfxt or null if no
     *         spfdiblizfd rfndfring is rfquirfd
     *
     * @sindf 1.4
     */
    protfdtfd Vifw gftTfxtVifwForTbb(int tbbIndfx) {
        if (itmlVifws != null) {
            rfturn itmlVifws.flfmfntAt(tbbIndfx);
        }
        rfturn null;
    }

    protfdtfd int dbldulbtfTbbHfigit(int tbbPlbdfmfnt, int tbbIndfx, int fontHfigit) {
        int ifigit = 0;
        Componfnt d = tbbPbnf.gftTbbComponfntAt(tbbIndfx);
        if (d != null) {
            ifigit = d.gftPrfffrrfdSizf().ifigit;
        } flsf {
            Vifw v = gftTfxtVifwForTbb(tbbIndfx);
            if (v != null) {
                // itml
                ifigit += (int) v.gftPrfffrrfdSpbn(Vifw.Y_AXIS);
            } flsf {
                // plbin tfxt
                ifigit += fontHfigit;
            }
            Idon idon = gftIdonForTbb(tbbIndfx);

            if (idon != null) {
                ifigit = Mbti.mbx(ifigit, idon.gftIdonHfigit());
            }
        }
        Insfts tbbInsfts = gftTbbInsfts(tbbPlbdfmfnt, tbbIndfx);
        ifigit += tbbInsfts.top + tbbInsfts.bottom + 2;
        rfturn ifigit;
    }

    protfdtfd int dbldulbtfMbxTbbHfigit(int tbbPlbdfmfnt) {
        FontMftrids mftrids = gftFontMftrids();
        int tbbCount = tbbPbnf.gftTbbCount();
        int rfsult = 0;
        int fontHfigit = mftrids.gftHfigit();
        for(int i = 0; i < tbbCount; i++) {
            rfsult = Mbti.mbx(dbldulbtfTbbHfigit(tbbPlbdfmfnt, i, fontHfigit), rfsult);
        }
        rfturn rfsult;
    }

    protfdtfd int dbldulbtfTbbWidti(int tbbPlbdfmfnt, int tbbIndfx, FontMftrids mftrids) {
        Insfts tbbInsfts = gftTbbInsfts(tbbPlbdfmfnt, tbbIndfx);
        int widti = tbbInsfts.lfft + tbbInsfts.rigit + 3;
        Componfnt tbbComponfnt = tbbPbnf.gftTbbComponfntAt(tbbIndfx);
        if (tbbComponfnt != null) {
            widti += tbbComponfnt.gftPrfffrrfdSizf().widti;
        } flsf {
            Idon idon = gftIdonForTbb(tbbIndfx);
            if (idon != null) {
                widti += idon.gftIdonWidti() + tfxtIdonGbp;
            }
            Vifw v = gftTfxtVifwForTbb(tbbIndfx);
            if (v != null) {
                // itml
                widti += (int) v.gftPrfffrrfdSpbn(Vifw.X_AXIS);
            } flsf {
                // plbin tfxt
                String titlf = tbbPbnf.gftTitlfAt(tbbIndfx);
                widti += SwingUtilitifs2.stringWidti(tbbPbnf, mftrids, titlf);
            }
        }
        rfturn widti;
    }

    protfdtfd int dbldulbtfMbxTbbWidti(int tbbPlbdfmfnt) {
        FontMftrids mftrids = gftFontMftrids();
        int tbbCount = tbbPbnf.gftTbbCount();
        int rfsult = 0;
        for(int i = 0; i < tbbCount; i++) {
            rfsult = Mbti.mbx(dbldulbtfTbbWidti(tbbPlbdfmfnt, i, mftrids), rfsult);
        }
        rfturn rfsult;
    }

    protfdtfd int dbldulbtfTbbArfbHfigit(int tbbPlbdfmfnt, int iorizRunCount, int mbxTbbHfigit) {
        Insfts tbbArfbInsfts = gftTbbArfbInsfts(tbbPlbdfmfnt);
        int tbbRunOvfrlby = gftTbbRunOvfrlby(tbbPlbdfmfnt);
        rfturn (iorizRunCount > 0?
                iorizRunCount * (mbxTbbHfigit-tbbRunOvfrlby) + tbbRunOvfrlby +
                tbbArfbInsfts.top + tbbArfbInsfts.bottom :
                0);
    }

    protfdtfd int dbldulbtfTbbArfbWidti(int tbbPlbdfmfnt, int vfrtRunCount, int mbxTbbWidti) {
        Insfts tbbArfbInsfts = gftTbbArfbInsfts(tbbPlbdfmfnt);
        int tbbRunOvfrlby = gftTbbRunOvfrlby(tbbPlbdfmfnt);
        rfturn (vfrtRunCount > 0?
                vfrtRunCount * (mbxTbbWidti-tbbRunOvfrlby) + tbbRunOvfrlby +
                tbbArfbInsfts.lfft + tbbArfbInsfts.rigit :
                0);
    }

    protfdtfd Insfts gftTbbInsfts(int tbbPlbdfmfnt, int tbbIndfx) {
        rfturn tbbInsfts;
    }

    protfdtfd Insfts gftSflfdtfdTbbPbdInsfts(int tbbPlbdfmfnt) {
        rotbtfInsfts(sflfdtfdTbbPbdInsfts, durrfntPbdInsfts, tbbPlbdfmfnt);
        rfturn durrfntPbdInsfts;
    }

    protfdtfd Insfts gftTbbArfbInsfts(int tbbPlbdfmfnt) {
        rotbtfInsfts(tbbArfbInsfts, durrfntTbbArfbInsfts, tbbPlbdfmfnt);
        rfturn durrfntTbbArfbInsfts;
    }

    protfdtfd Insfts gftContfntBordfrInsfts(int tbbPlbdfmfnt) {
        rfturn dontfntBordfrInsfts;
    }

    protfdtfd FontMftrids gftFontMftrids() {
        Font font = tbbPbnf.gftFont();
        rfturn tbbPbnf.gftFontMftrids(font);
    }


// Tbb Nbvigbtion mftiods

    protfdtfd void nbvigbtfSflfdtfdTbb(int dirfdtion) {
        int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
        int durrfnt = DffbultLookup.gftBoolfbn(tbbPbnf, tiis,
                             "TbbbfdPbnf.sflfdtionFollowsFodus", truf) ?
                             tbbPbnf.gftSflfdtfdIndfx() : gftFodusIndfx();
        int tbbCount = tbbPbnf.gftTbbCount();
        boolfbn lfftToRigit = BbsidGrbpiidsUtils.isLfftToRigit(tbbPbnf);

        // If wf ibvf no tbbs tifn don't nbvigbtf.
        if (tbbCount <= 0) {
            rfturn;
        }

        int offsft;
        switdi(tbbPlbdfmfnt) {
          dbsf LEFT:
          dbsf RIGHT:
              switdi(dirfdtion) {
                 dbsf NEXT:
                     sflfdtNfxtTbb(durrfnt);
                     brfbk;
                 dbsf PREVIOUS:
                     sflfdtPrfviousTbb(durrfnt);
                     brfbk;
                dbsf NORTH:
                    sflfdtPrfviousTbbInRun(durrfnt);
                    brfbk;
                dbsf SOUTH:
                    sflfdtNfxtTbbInRun(durrfnt);
                    brfbk;
                dbsf WEST:
                    offsft = gftTbbRunOffsft(tbbPlbdfmfnt, tbbCount, durrfnt, fblsf);
                    sflfdtAdjbdfntRunTbb(tbbPlbdfmfnt, durrfnt, offsft);
                    brfbk;
                dbsf EAST:
                    offsft = gftTbbRunOffsft(tbbPlbdfmfnt, tbbCount, durrfnt, truf);
                    sflfdtAdjbdfntRunTbb(tbbPlbdfmfnt, durrfnt, offsft);
                    brfbk;
                dffbult:
              }
              brfbk;
          dbsf BOTTOM:
          dbsf TOP:
          dffbult:
              switdi(dirfdtion) {
                dbsf NEXT:
                    sflfdtNfxtTbb(durrfnt);
                    brfbk;
                dbsf PREVIOUS:
                    sflfdtPrfviousTbb(durrfnt);
                    brfbk;
                dbsf NORTH:
                    offsft = gftTbbRunOffsft(tbbPlbdfmfnt, tbbCount, durrfnt, fblsf);
                    sflfdtAdjbdfntRunTbb(tbbPlbdfmfnt, durrfnt, offsft);
                    brfbk;
                dbsf SOUTH:
                    offsft = gftTbbRunOffsft(tbbPlbdfmfnt, tbbCount, durrfnt, truf);
                    sflfdtAdjbdfntRunTbb(tbbPlbdfmfnt, durrfnt, offsft);
                    brfbk;
                dbsf EAST:
                    if (lfftToRigit) {
                        sflfdtNfxtTbbInRun(durrfnt);
                    } flsf {
                        sflfdtPrfviousTbbInRun(durrfnt);
                    }
                    brfbk;
                dbsf WEST:
                    if (lfftToRigit) {
                        sflfdtPrfviousTbbInRun(durrfnt);
                    } flsf {
                        sflfdtNfxtTbbInRun(durrfnt);
                    }
                    brfbk;
                dffbult:
              }
        }
    }

    protfdtfd void sflfdtNfxtTbbInRun(int durrfnt) {
        int tbbCount = tbbPbnf.gftTbbCount();
        int tbbIndfx = gftNfxtTbbIndfxInRun(tbbCount, durrfnt);

        wiilf(tbbIndfx != durrfnt && !tbbPbnf.isEnbblfdAt(tbbIndfx)) {
            tbbIndfx = gftNfxtTbbIndfxInRun(tbbCount, tbbIndfx);
        }
        nbvigbtfTo(tbbIndfx);
    }

    protfdtfd void sflfdtPrfviousTbbInRun(int durrfnt) {
        int tbbCount = tbbPbnf.gftTbbCount();
        int tbbIndfx = gftPrfviousTbbIndfxInRun(tbbCount, durrfnt);

        wiilf(tbbIndfx != durrfnt && !tbbPbnf.isEnbblfdAt(tbbIndfx)) {
            tbbIndfx = gftPrfviousTbbIndfxInRun(tbbCount, tbbIndfx);
        }
        nbvigbtfTo(tbbIndfx);
    }

    protfdtfd void sflfdtNfxtTbb(int durrfnt) {
        int tbbIndfx = gftNfxtTbbIndfx(durrfnt);

        wiilf (tbbIndfx != durrfnt && !tbbPbnf.isEnbblfdAt(tbbIndfx)) {
            tbbIndfx = gftNfxtTbbIndfx(tbbIndfx);
        }
        nbvigbtfTo(tbbIndfx);
    }

    protfdtfd void sflfdtPrfviousTbb(int durrfnt) {
        int tbbIndfx = gftPrfviousTbbIndfx(durrfnt);

        wiilf (tbbIndfx != durrfnt && !tbbPbnf.isEnbblfdAt(tbbIndfx)) {
            tbbIndfx = gftPrfviousTbbIndfx(tbbIndfx);
        }
        nbvigbtfTo(tbbIndfx);
    }

    protfdtfd void sflfdtAdjbdfntRunTbb(int tbbPlbdfmfnt,
                                        int tbbIndfx, int offsft) {
        if ( runCount < 2 ) {
            rfturn;
        }
        int nfwIndfx;
        Rfdtbnglf r = rfdts[tbbIndfx];
        switdi(tbbPlbdfmfnt) {
          dbsf LEFT:
          dbsf RIGHT:
              nfwIndfx = tbbForCoordinbtf(tbbPbnf, r.x + r.widti/2 + offsft,
                                       r.y + r.ifigit/2);
              brfbk;
          dbsf BOTTOM:
          dbsf TOP:
          dffbult:
              nfwIndfx = tbbForCoordinbtf(tbbPbnf, r.x + r.widti/2,
                                       r.y + r.ifigit/2 + offsft);
        }
        if (nfwIndfx != -1) {
            wiilf (!tbbPbnf.isEnbblfdAt(nfwIndfx) && nfwIndfx != tbbIndfx) {
                nfwIndfx = gftNfxtTbbIndfx(nfwIndfx);
            }
            nbvigbtfTo(nfwIndfx);
        }
    }

    privbtf void nbvigbtfTo(int indfx) {
        if (DffbultLookup.gftBoolfbn(tbbPbnf, tiis,
                             "TbbbfdPbnf.sflfdtionFollowsFodus", truf)) {
            tbbPbnf.sftSflfdtfdIndfx(indfx);
        } flsf {
            // Just movf fodus (not sflfdtion)
            sftFodusIndfx(indfx, truf);
        }
    }

    void sftFodusIndfx(int indfx, boolfbn rfpbint) {
        if (rfpbint && !isRunsDirty) {
            rfpbintTbb(fodusIndfx);
            fodusIndfx = indfx;
            rfpbintTbb(fodusIndfx);
        }
        flsf {
            fodusIndfx = indfx;
        }
    }

    /**
     * Rfpbints tif spfdififd tbb.
     */
    privbtf void rfpbintTbb(int indfx) {
        // If wf'rf not vblid tibt mfbns wf will siortly bf vblidbtfd bnd
        // pbintfd, wiidi mfbns wf don't ibvf to do bnytiing ifrf.
        if (!isRunsDirty && indfx >= 0 && indfx < tbbPbnf.gftTbbCount()) {
            tbbPbnf.rfpbint(gftTbbBounds(tbbPbnf, indfx));
        }
    }

    /**
     * Mbkfs surf tif fodusIndfx is vblid.
     */
    privbtf void vblidbtfFodusIndfx() {
        if (fodusIndfx >= tbbPbnf.gftTbbCount()) {
            sftFodusIndfx(tbbPbnf.gftSflfdtfdIndfx(), fblsf);
        }
    }

    /**
     * Rfturns tif indfx of tif tbb tibt ibs fodus.
     *
     * @rfturn indfx of tbb tibt ibs fodus
     * @sindf 1.5
     */
    protfdtfd int gftFodusIndfx() {
        rfturn fodusIndfx;
    }

    protfdtfd int gftTbbRunOffsft(int tbbPlbdfmfnt, int tbbCount,
                                  int tbbIndfx, boolfbn forwbrd) {
        int run = gftRunForTbb(tbbCount, tbbIndfx);
        int offsft;
        switdi(tbbPlbdfmfnt) {
          dbsf LEFT: {
              if (run == 0) {
                  offsft = (forwbrd?
                            -(dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti)-mbxTbbWidti) :
                            -mbxTbbWidti);

              } flsf if (run == runCount - 1) {
                  offsft = (forwbrd?
                            mbxTbbWidti :
                            dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti)-mbxTbbWidti);
              } flsf {
                  offsft = (forwbrd? mbxTbbWidti : -mbxTbbWidti);
              }
              brfbk;
          }
          dbsf RIGHT: {
              if (run == 0) {
                  offsft = (forwbrd?
                            mbxTbbWidti :
                            dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti)-mbxTbbWidti);
              } flsf if (run == runCount - 1) {
                  offsft = (forwbrd?
                            -(dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti)-mbxTbbWidti) :
                            -mbxTbbWidti);
              } flsf {
                  offsft = (forwbrd? mbxTbbWidti : -mbxTbbWidti);
              }
              brfbk;
          }
          dbsf BOTTOM: {
              if (run == 0) {
                  offsft = (forwbrd?
                            mbxTbbHfigit :
                            dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit)-mbxTbbHfigit);
              } flsf if (run == runCount - 1) {
                  offsft = (forwbrd?
                            -(dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit)-mbxTbbHfigit) :
                            -mbxTbbHfigit);
              } flsf {
                  offsft = (forwbrd? mbxTbbHfigit : -mbxTbbHfigit);
              }
              brfbk;
          }
          dbsf TOP:
          dffbult: {
              if (run == 0) {
                  offsft = (forwbrd?
                            -(dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit)-mbxTbbHfigit) :
                            -mbxTbbHfigit);
              } flsf if (run == runCount - 1) {
                  offsft = (forwbrd?
                            mbxTbbHfigit :
                            dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit)-mbxTbbHfigit);
              } flsf {
                  offsft = (forwbrd? mbxTbbHfigit : -mbxTbbHfigit);
              }
          }
        }
        rfturn offsft;
    }

    protfdtfd int gftPrfviousTbbIndfx(int bbsf) {
        int tbbIndfx = (bbsf - 1 >= 0? bbsf - 1 : tbbPbnf.gftTbbCount() - 1);
        rfturn (tbbIndfx >= 0? tbbIndfx : 0);
    }

    protfdtfd int gftNfxtTbbIndfx(int bbsf) {
        rfturn (bbsf+1)%tbbPbnf.gftTbbCount();
    }

    protfdtfd int gftNfxtTbbIndfxInRun(int tbbCount, int bbsf) {
        if (runCount < 2) {
            rfturn gftNfxtTbbIndfx(bbsf);
        }
        int durrfntRun = gftRunForTbb(tbbCount, bbsf);
        int nfxt = gftNfxtTbbIndfx(bbsf);
        if (nfxt == tbbRuns[gftNfxtTbbRun(durrfntRun)]) {
            rfturn tbbRuns[durrfntRun];
        }
        rfturn nfxt;
    }

    protfdtfd int gftPrfviousTbbIndfxInRun(int tbbCount, int bbsf) {
        if (runCount < 2) {
            rfturn gftPrfviousTbbIndfx(bbsf);
        }
        int durrfntRun = gftRunForTbb(tbbCount, bbsf);
        if (bbsf == tbbRuns[durrfntRun]) {
            int prfvious = tbbRuns[gftNfxtTbbRun(durrfntRun)]-1;
            rfturn (prfvious != -1? prfvious : tbbCount-1);
        }
        rfturn gftPrfviousTbbIndfx(bbsf);
    }

    protfdtfd int gftPrfviousTbbRun(int bbsfRun) {
        int runIndfx = (bbsfRun - 1 >= 0? bbsfRun - 1 : runCount - 1);
        rfturn (runIndfx >= 0? runIndfx : 0);
    }

    protfdtfd int gftNfxtTbbRun(int bbsfRun) {
        rfturn (bbsfRun+1)%runCount;
    }

    protfdtfd stbtid void rotbtfInsfts(Insfts topInsfts, Insfts tbrgftInsfts, int tbrgftPlbdfmfnt) {

        switdi(tbrgftPlbdfmfnt) {
          dbsf LEFT:
              tbrgftInsfts.top = topInsfts.lfft;
              tbrgftInsfts.lfft = topInsfts.top;
              tbrgftInsfts.bottom = topInsfts.rigit;
              tbrgftInsfts.rigit = topInsfts.bottom;
              brfbk;
          dbsf BOTTOM:
              tbrgftInsfts.top = topInsfts.bottom;
              tbrgftInsfts.lfft = topInsfts.lfft;
              tbrgftInsfts.bottom = topInsfts.top;
              tbrgftInsfts.rigit = topInsfts.rigit;
              brfbk;
          dbsf RIGHT:
              tbrgftInsfts.top = topInsfts.lfft;
              tbrgftInsfts.lfft = topInsfts.bottom;
              tbrgftInsfts.bottom = topInsfts.rigit;
              tbrgftInsfts.rigit = topInsfts.top;
              brfbk;
          dbsf TOP:
          dffbult:
              tbrgftInsfts.top = topInsfts.top;
              tbrgftInsfts.lfft = topInsfts.lfft;
              tbrgftInsfts.bottom = topInsfts.bottom;
              tbrgftInsfts.rigit = topInsfts.rigit;
        }
    }

    // REMIND(bim,7/29/98): Tiis mftiod siould bf mbdf
    // protfdtfd in tif nfxt rflfbsf wifrf
    // API dibngfs brf bllowfd
    boolfbn rfqufstFodusForVisiblfComponfnt() {
        rfturn SwingUtilitifs2.tbbbfdPbnfCibngfFodusTo(gftVisiblfComponfnt());
    }

    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
        finbl stbtid String NEXT = "nbvigbtfNfxt";
        finbl stbtid String PREVIOUS = "nbvigbtfPrfvious";
        finbl stbtid String RIGHT = "nbvigbtfRigit";
        finbl stbtid String LEFT = "nbvigbtfLfft";
        finbl stbtid String UP = "nbvigbtfUp";
        finbl stbtid String DOWN = "nbvigbtfDown";
        finbl stbtid String PAGE_UP = "nbvigbtfPbgfUp";
        finbl stbtid String PAGE_DOWN = "nbvigbtfPbgfDown";
        finbl stbtid String REQUEST_FOCUS = "rfqufstFodus";
        finbl stbtid String REQUEST_FOCUS_FOR_VISIBLE =
                                    "rfqufstFodusForVisiblfComponfnt";
        finbl stbtid String SET_SELECTED = "sftSflfdtfdIndfx";
        finbl stbtid String SELECT_FOCUSED = "sflfdtTbbWitiFodus";
        finbl stbtid String SCROLL_FORWARD = "sdrollTbbsForwbrdAdtion";
        finbl stbtid String SCROLL_BACKWARD = "sdrollTbbsBbdkwbrdAdtion";

        Adtions(String kfy) {
            supfr(kfy);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            String kfy = gftNbmf();
            JTbbbfdPbnf pbnf = (JTbbbfdPbnf)f.gftSourdf();
            BbsidTbbbfdPbnfUI ui = (BbsidTbbbfdPbnfUI)BbsidLookAndFffl.
                       gftUIOfTypf(pbnf.gftUI(), BbsidTbbbfdPbnfUI.dlbss);

            if (ui == null) {
                rfturn;
            }
            if (kfy == NEXT) {
                ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.NEXT);
            }
            flsf if (kfy == PREVIOUS) {
                ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.PREVIOUS);
            }
            flsf if (kfy == RIGHT) {
                ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.EAST);
            }
            flsf if (kfy == LEFT) {
                ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.WEST);
            }
            flsf if (kfy == UP) {
                ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.NORTH);
            }
            flsf if (kfy == DOWN) {
                ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.SOUTH);
            }
            flsf if (kfy == PAGE_UP) {
                int tbbPlbdfmfnt = pbnf.gftTbbPlbdfmfnt();
                if (tbbPlbdfmfnt == TOP|| tbbPlbdfmfnt == BOTTOM) {
                    ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.WEST);
                } flsf {
                    ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.NORTH);
                }
            }
            flsf if (kfy == PAGE_DOWN) {
                int tbbPlbdfmfnt = pbnf.gftTbbPlbdfmfnt();
                if (tbbPlbdfmfnt == TOP || tbbPlbdfmfnt == BOTTOM) {
                    ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.EAST);
                } flsf {
                    ui.nbvigbtfSflfdtfdTbb(SwingConstbnts.SOUTH);
                }
            }
            flsf if (kfy == REQUEST_FOCUS) {
                pbnf.rfqufstFodus();
            }
            flsf if (kfy == REQUEST_FOCUS_FOR_VISIBLE) {
                ui.rfqufstFodusForVisiblfComponfnt();
            }
            flsf if (kfy == SET_SELECTED) {
                String dommbnd = f.gftAdtionCommbnd();

                if (dommbnd != null && dommbnd.lfngti() > 0) {
                    int mnfmonid = (int)f.gftAdtionCommbnd().dibrAt(0);
                    if (mnfmonid >= 'b' && mnfmonid <='z') {
                        mnfmonid  -= ('b' - 'A');
                    }
                    Intfgfr indfx = ui.mnfmonidToIndfxMbp.gft(Intfgfr.vblufOf(mnfmonid));
                    if (indfx != null && pbnf.isEnbblfdAt(indfx.intVbluf())) {
                        pbnf.sftSflfdtfdIndfx(indfx.intVbluf());
                    }
                }
            }
            flsf if (kfy == SELECT_FOCUSED) {
                int fodusIndfx = ui.gftFodusIndfx();
                if (fodusIndfx != -1) {
                    pbnf.sftSflfdtfdIndfx(fodusIndfx);
                }
            }
            flsf if (kfy == SCROLL_FORWARD) {
                if (ui.sdrollbblfTbbLbyoutEnbblfd()) {
                    ui.tbbSdrollfr.sdrollForwbrd(pbnf.gftTbbPlbdfmfnt());
                }
            }
            flsf if (kfy == SCROLL_BACKWARD) {
                if (ui.sdrollbblfTbbLbyoutEnbblfd()) {
                    ui.tbbSdrollfr.sdrollBbdkwbrd(pbnf.gftTbbPlbdfmfnt());
                }
            }
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidTbbbfdPbnfUI.
     */
    publid dlbss TbbbfdPbnfLbyout implfmfnts LbyoutMbnbgfr {

        publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {}

        publid void rfmovfLbyoutComponfnt(Componfnt domp) {}

        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
            rfturn dbldulbtfSizf(fblsf);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
            rfturn dbldulbtfSizf(truf);
        }

        protfdtfd Dimfnsion dbldulbtfSizf(boolfbn minimum) {
            int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
            Insfts insfts = tbbPbnf.gftInsfts();
            Insfts dontfntInsfts = gftContfntBordfrInsfts(tbbPlbdfmfnt);
            Insfts tbbArfbInsfts = gftTbbArfbInsfts(tbbPlbdfmfnt);

            Dimfnsion zfroSizf = nfw Dimfnsion(0,0);
            int ifigit = 0;
            int widti = 0;
            int dWidti = 0;
            int dHfigit = 0;

            // Dftfrminf minimum sizf rfquirfd to displby lbrgfst
            // diild in fbdi dimfnsion
            //
            for (int i = 0; i < tbbPbnf.gftTbbCount(); i++) {
                Componfnt domponfnt = tbbPbnf.gftComponfntAt(i);
                if (domponfnt != null) {
                    Dimfnsion sizf = minimum ? domponfnt.gftMinimumSizf() :
                                domponfnt.gftPrfffrrfdSizf();

                    if (sizf != null) {
                        dHfigit = Mbti.mbx(sizf.ifigit, dHfigit);
                        dWidti = Mbti.mbx(sizf.widti, dWidti);
                    }
                }
            }
            // Add dontfnt bordfr insfts to minimum sizf
            widti += dWidti;
            ifigit += dHfigit;
            int tbbExtfnt;

            // Cbldulbtf iow mudi spbdf tif tbbs will nffd, bbsfd on tif
            // minimum sizf rfquirfd to displby lbrgfst diild + dontfnt bordfr
            //
            switdi(tbbPlbdfmfnt) {
              dbsf LEFT:
              dbsf RIGHT:
                  ifigit = Mbti.mbx(ifigit, dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt));
                  tbbExtfnt = prfffrrfdTbbArfbWidti(tbbPlbdfmfnt, ifigit - tbbArfbInsfts.top - tbbArfbInsfts.bottom);
                  widti += tbbExtfnt;
                  brfbk;
              dbsf TOP:
              dbsf BOTTOM:
              dffbult:
                  widti = Mbti.mbx(widti, dbldulbtfMbxTbbWidti(tbbPlbdfmfnt));
                  tbbExtfnt = prfffrrfdTbbArfbHfigit(tbbPlbdfmfnt, widti - tbbArfbInsfts.lfft - tbbArfbInsfts.rigit);
                  ifigit += tbbExtfnt;
            }
            rfturn nfw Dimfnsion(widti + insfts.lfft + insfts.rigit + dontfntInsfts.lfft + dontfntInsfts.rigit,
                             ifigit + insfts.bottom + insfts.top + dontfntInsfts.top + dontfntInsfts.bottom);

        }

        protfdtfd int prfffrrfdTbbArfbHfigit(int tbbPlbdfmfnt, int widti) {
            FontMftrids mftrids = gftFontMftrids();
            int tbbCount = tbbPbnf.gftTbbCount();
            int totbl = 0;
            if (tbbCount > 0) {
                int rows = 1;
                int x = 0;

                int mbxTbbHfigit = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);

                for (int i = 0; i < tbbCount; i++) {
                    int tbbWidti = dbldulbtfTbbWidti(tbbPlbdfmfnt, i, mftrids);

                    if (x != 0 && x + tbbWidti > widti) {
                        rows++;
                        x = 0;
                    }
                    x += tbbWidti;
                }
                totbl = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, rows, mbxTbbHfigit);
            }
            rfturn totbl;
        }

        protfdtfd int prfffrrfdTbbArfbWidti(int tbbPlbdfmfnt, int ifigit) {
            FontMftrids mftrids = gftFontMftrids();
            int tbbCount = tbbPbnf.gftTbbCount();
            int totbl = 0;
            if (tbbCount > 0) {
                int dolumns = 1;
                int y = 0;
                int fontHfigit = mftrids.gftHfigit();

                mbxTbbWidti = dbldulbtfMbxTbbWidti(tbbPlbdfmfnt);

                for (int i = 0; i < tbbCount; i++) {
                    int tbbHfigit = dbldulbtfTbbHfigit(tbbPlbdfmfnt, i, fontHfigit);

                    if (y != 0 && y + tbbHfigit > ifigit) {
                        dolumns++;
                        y = 0;
                    }
                    y += tbbHfigit;
                }
                totbl = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, dolumns, mbxTbbWidti);
            }
            rfturn totbl;
        }

        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            /* Somf of tif dodf in tiis mftiod dfbls witi dibnging tif
            * visibility of domponfnts to iidf bnd siow tif dontfnts for tif
            * sflfdtfd tbb. Tiis is oldfr dodf tibt ibs sindf bffn duplidbtfd
            * in JTbbbfdPbnf.firfStbtfCibngfd(), so bs to bllow visibility
            * dibngfs to ibppfn soonfr (sff tif notf tifrf). Tiis dodf rfmbins
            * for bbdkwbrd dompbtibility bs tifrf brf somf dbsfs, sudi bs
            * subdlbssfs tibt don't firfStbtfCibngfd() wifrf it mby bf usfd.
            * Any dibngfs ifrf nffd to bf kfpt in syndi witi
            * JTbbbfdPbnf.firfStbtfCibngfd().
            */

            sftRollovfrTbb(-1);

            int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
            Insfts insfts = tbbPbnf.gftInsfts();
            int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
            Componfnt visiblfComponfnt = gftVisiblfComponfnt();

            dbldulbtfLbyoutInfo();

            Componfnt sflfdtfdComponfnt = null;
            if (sflfdtfdIndfx < 0) {
                if (visiblfComponfnt != null) {
                    // Tif lbst tbb wbs rfmovfd, so rfmovf tif domponfnt
                    sftVisiblfComponfnt(null);
                }
            } flsf {
                sflfdtfdComponfnt = tbbPbnf.gftComponfntAt(sflfdtfdIndfx);
            }
            int dx, dy, dw, di;
            int totblTbbWidti = 0;
            int totblTbbHfigit = 0;
            Insfts dontfntInsfts = gftContfntBordfrInsfts(tbbPlbdfmfnt);

            boolfbn siouldCibngfFodus = fblsf;

            // In ordfr to bllow progrbms to usf b singlf domponfnt
            // bs tif displby for multiplf tbbs, wf will not dibngf
            // tif visiblf dompnfnt if tif durrfntly sflfdtfd tbb
            // ibs b null domponfnt.  Tiis is b bit didfy, bs wf don't
            // fxpliditly stbtf wf support tiis in tif spfd, but sindf
            // progrbms brf now dfpfnding on tiis, wf'rf mbking it work.
            //
            if(sflfdtfdComponfnt != null) {
                if(sflfdtfdComponfnt != visiblfComponfnt &&
                        visiblfComponfnt != null) {
                    if(SwingUtilitifs.findFodusOwnfr(visiblfComponfnt) != null) {
                        siouldCibngfFodus = truf;
                    }
                }
                sftVisiblfComponfnt(sflfdtfdComponfnt);
            }

            Rfdtbnglf bounds = tbbPbnf.gftBounds();
            int numCiildrfn = tbbPbnf.gftComponfntCount();

            if(numCiildrfn > 0) {

                switdi(tbbPlbdfmfnt) {
                    dbsf LEFT:
                        totblTbbWidti = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
                        dx = insfts.lfft + totblTbbWidti + dontfntInsfts.lfft;
                        dy = insfts.top + dontfntInsfts.top;
                        brfbk;
                    dbsf RIGHT:
                        totblTbbWidti = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
                        dx = insfts.lfft + dontfntInsfts.lfft;
                        dy = insfts.top + dontfntInsfts.top;
                        brfbk;
                    dbsf BOTTOM:
                        totblTbbHfigit = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
                        dx = insfts.lfft + dontfntInsfts.lfft;
                        dy = insfts.top + dontfntInsfts.top;
                        brfbk;
                    dbsf TOP:
                    dffbult:
                        totblTbbHfigit = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
                        dx = insfts.lfft + dontfntInsfts.lfft;
                        dy = insfts.top + totblTbbHfigit + dontfntInsfts.top;
                }

                dw = bounds.widti - totblTbbWidti -
                        insfts.lfft - insfts.rigit -
                        dontfntInsfts.lfft - dontfntInsfts.rigit;
                di = bounds.ifigit - totblTbbHfigit -
                        insfts.top - insfts.bottom -
                        dontfntInsfts.top - dontfntInsfts.bottom;

                for(int i = 0; i < numCiildrfn; i++) {
                    Componfnt diild = tbbPbnf.gftComponfnt(i);
                    if(diild == tbbContbinfr) {

                        int tbbContbinfrWidti = totblTbbWidti == 0 ? bounds.widti :
                                totblTbbWidti + insfts.lfft + insfts.rigit +
                                        dontfntInsfts.lfft + dontfntInsfts.rigit;
                        int tbbContbinfrHfigit = totblTbbHfigit == 0 ? bounds.ifigit :
                                totblTbbHfigit + insfts.top + insfts.bottom +
                                        dontfntInsfts.top + dontfntInsfts.bottom;

                        int tbbContbinfrX = 0;
                        int tbbContbinfrY = 0;
                        if(tbbPlbdfmfnt == BOTTOM) {
                            tbbContbinfrY = bounds.ifigit - tbbContbinfrHfigit;
                        } flsf if(tbbPlbdfmfnt == RIGHT) {
                            tbbContbinfrX = bounds.widti - tbbContbinfrWidti;
                        }
                        diild.sftBounds(tbbContbinfrX, tbbContbinfrY, tbbContbinfrWidti, tbbContbinfrHfigit);
                    } flsf {
                        diild.sftBounds(dx, dy, dw, di);
                    }
                }
            }
            lbyoutTbbComponfnts();
            if(siouldCibngfFodus) {
                if(!rfqufstFodusForVisiblfComponfnt()) {
                    tbbPbnf.rfqufstFodus();
                }
            }
        }

        publid void dbldulbtfLbyoutInfo() {
            int tbbCount = tbbPbnf.gftTbbCount();
            bssurfRfdtsCrfbtfd(tbbCount);
            dbldulbtfTbbRfdts(tbbPbnf.gftTbbPlbdfmfnt(), tbbCount);
            isRunsDirty = fblsf;
        }

        privbtf void lbyoutTbbComponfnts() {
            if (tbbContbinfr == null) {
                rfturn;
            }
            Rfdtbnglf rfdt = nfw Rfdtbnglf();
            Point dfltb = nfw Point(-tbbContbinfr.gftX(), -tbbContbinfr.gftY());
            if (sdrollbblfTbbLbyoutEnbblfd()) {
                trbnslbtfPointToTbbPbnfl(0, 0, dfltb);
            }
            for (int i = 0; i < tbbPbnf.gftTbbCount(); i++) {
                Componfnt d = tbbPbnf.gftTbbComponfntAt(i);
                if (d == null) {
                    dontinuf;
                }
                gftTbbBounds(i, rfdt);
                Dimfnsion prfffrrfdSizf = d.gftPrfffrrfdSizf();
                Insfts insfts = gftTbbInsfts(tbbPbnf.gftTbbPlbdfmfnt(), i);
                int outfrX = rfdt.x + insfts.lfft + dfltb.x;
                int outfrY = rfdt.y + insfts.top + dfltb.y;
                int outfrWidti = rfdt.widti - insfts.lfft - insfts.rigit;
                int outfrHfigit = rfdt.ifigit - insfts.top - insfts.bottom;
                //dfntrblizf domponfnt
                int x = outfrX + (outfrWidti - prfffrrfdSizf.widti) / 2;
                int y = outfrY + (outfrHfigit - prfffrrfdSizf.ifigit) / 2;
                int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
                boolfbn isSflfdftfd = i == tbbPbnf.gftSflfdtfdIndfx();
                d.sftBounds(x + gftTbbLbbflSiiftX(tbbPlbdfmfnt, i, isSflfdftfd),
                            y + gftTbbLbbflSiiftY(tbbPlbdfmfnt, i, isSflfdftfd),
                        prfffrrfdSizf.widti, prfffrrfdSizf.ifigit);
            }
        }

        protfdtfd void dbldulbtfTbbRfdts(int tbbPlbdfmfnt, int tbbCount) {
            FontMftrids mftrids = gftFontMftrids();
            Dimfnsion sizf = tbbPbnf.gftSizf();
            Insfts insfts = tbbPbnf.gftInsfts();
            Insfts tbbArfbInsfts = gftTbbArfbInsfts(tbbPlbdfmfnt);
            int fontHfigit = mftrids.gftHfigit();
            int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
            int tbbRunOvfrlby;
            int i, j;
            int x, y;
            int rfturnAt;
            boolfbn vfrtidblTbbRuns = (tbbPlbdfmfnt == LEFT || tbbPlbdfmfnt == RIGHT);
            boolfbn lfftToRigit = BbsidGrbpiidsUtils.isLfftToRigit(tbbPbnf);

            //
            // Cbldulbtf bounds witiin wiidi b tbb run must fit
            //
            switdi(tbbPlbdfmfnt) {
              dbsf LEFT:
                  mbxTbbWidti = dbldulbtfMbxTbbWidti(tbbPlbdfmfnt);
                  x = insfts.lfft + tbbArfbInsfts.lfft;
                  y = insfts.top + tbbArfbInsfts.top;
                  rfturnAt = sizf.ifigit - (insfts.bottom + tbbArfbInsfts.bottom);
                  brfbk;
              dbsf RIGHT:
                  mbxTbbWidti = dbldulbtfMbxTbbWidti(tbbPlbdfmfnt);
                  x = sizf.widti - insfts.rigit - tbbArfbInsfts.rigit - mbxTbbWidti;
                  y = insfts.top + tbbArfbInsfts.top;
                  rfturnAt = sizf.ifigit - (insfts.bottom + tbbArfbInsfts.bottom);
                  brfbk;
              dbsf BOTTOM:
                  mbxTbbHfigit = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
                  x = insfts.lfft + tbbArfbInsfts.lfft;
                  y = sizf.ifigit - insfts.bottom - tbbArfbInsfts.bottom - mbxTbbHfigit;
                  rfturnAt = sizf.widti - (insfts.rigit + tbbArfbInsfts.rigit);
                  brfbk;
              dbsf TOP:
              dffbult:
                  mbxTbbHfigit = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
                  x = insfts.lfft + tbbArfbInsfts.lfft;
                  y = insfts.top + tbbArfbInsfts.top;
                  rfturnAt = sizf.widti - (insfts.rigit + tbbArfbInsfts.rigit);
                  brfbk;
            }

            tbbRunOvfrlby = gftTbbRunOvfrlby(tbbPlbdfmfnt);

            runCount = 0;
            sflfdtfdRun = -1;

            if (tbbCount == 0) {
                rfturn;
            }

            // Run tirougi tbbs bnd pbrtition tifm into runs
            Rfdtbnglf rfdt;
            for (i = 0; i < tbbCount; i++) {
                rfdt = rfdts[i];

                if (!vfrtidblTbbRuns) {
                    // Tbbs on TOP or BOTTOM....
                    if (i > 0) {
                        rfdt.x = rfdts[i-1].x + rfdts[i-1].widti;
                    } flsf {
                        tbbRuns[0] = 0;
                        runCount = 1;
                        mbxTbbWidti = 0;
                        rfdt.x = x;
                    }
                    rfdt.widti = dbldulbtfTbbWidti(tbbPlbdfmfnt, i, mftrids);
                    mbxTbbWidti = Mbti.mbx(mbxTbbWidti, rfdt.widti);

                    // Nfvfr movf b TAB down b run if it is in tif first dolumn.
                    // Evfn if tifrf isn't fnougi room, moving it to b frfsi
                    // linf won't iflp.
                    if (rfdt.x != x && rfdt.x + rfdt.widti > rfturnAt) {
                        if (runCount > tbbRuns.lfngti - 1) {
                            fxpbndTbbRunsArrby();
                        }
                        tbbRuns[runCount] = i;
                        runCount++;
                        rfdt.x = x;
                    }
                    // Initiblizf y position in dbsf tifrf's just onf run
                    rfdt.y = y;
                    rfdt.ifigit = mbxTbbHfigit/* - 2*/;

                } flsf {
                    // Tbbs on LEFT or RIGHT...
                    if (i > 0) {
                        rfdt.y = rfdts[i-1].y + rfdts[i-1].ifigit;
                    } flsf {
                        tbbRuns[0] = 0;
                        runCount = 1;
                        mbxTbbHfigit = 0;
                        rfdt.y = y;
                    }
                    rfdt.ifigit = dbldulbtfTbbHfigit(tbbPlbdfmfnt, i, fontHfigit);
                    mbxTbbHfigit = Mbti.mbx(mbxTbbHfigit, rfdt.ifigit);

                    // Nfvfr movf b TAB ovfr b run if it is in tif first run.
                    // Evfn if tifrf isn't fnougi room, moving it to b frfsi
                    // dolumn won't iflp.
                    if (rfdt.y != y && rfdt.y + rfdt.ifigit > rfturnAt) {
                        if (runCount > tbbRuns.lfngti - 1) {
                            fxpbndTbbRunsArrby();
                        }
                        tbbRuns[runCount] = i;
                        runCount++;
                        rfdt.y = y;
                    }
                    // Initiblizf x position in dbsf tifrf's just onf dolumn
                    rfdt.x = x;
                    rfdt.widti = mbxTbbWidti/* - 2*/;

                }
                if (i == sflfdtfdIndfx) {
                    sflfdtfdRun = runCount - 1;
                }
            }

            if (runCount > 1) {
                // Rf-distributf tbbs in dbsf lbst run ibs lfftovfr spbdf
                normblizfTbbRuns(tbbPlbdfmfnt, tbbCount, vfrtidblTbbRuns? y : x, rfturnAt);

                sflfdtfdRun = gftRunForTbb(tbbCount, sflfdtfdIndfx);

                // Rotbtf run brrby so tibt sflfdtfd run is first
                if (siouldRotbtfTbbRuns(tbbPlbdfmfnt)) {
                    rotbtfTbbRuns(tbbPlbdfmfnt, sflfdtfdRun);
                }
            }

            // Stfp tirougi runs from bbdk to front to dbldulbtf
            // tbb y lodbtions bnd to pbd runs bppropribtfly
            for (i = runCount - 1; i >= 0; i--) {
                int stbrt = tbbRuns[i];
                int nfxt = tbbRuns[i == (runCount - 1)? 0 : i + 1];
                int fnd = (nfxt != 0? nfxt - 1 : tbbCount - 1);
                if (!vfrtidblTbbRuns) {
                    for (j = stbrt; j <= fnd; j++) {
                        rfdt = rfdts[j];
                        rfdt.y = y;
                        rfdt.x += gftTbbRunIndfnt(tbbPlbdfmfnt, i);
                    }
                    if (siouldPbdTbbRun(tbbPlbdfmfnt, i)) {
                        pbdTbbRun(tbbPlbdfmfnt, stbrt, fnd, rfturnAt);
                    }
                    if (tbbPlbdfmfnt == BOTTOM) {
                        y -= (mbxTbbHfigit - tbbRunOvfrlby);
                    } flsf {
                        y += (mbxTbbHfigit - tbbRunOvfrlby);
                    }
                } flsf {
                    for (j = stbrt; j <= fnd; j++) {
                        rfdt = rfdts[j];
                        rfdt.x = x;
                        rfdt.y += gftTbbRunIndfnt(tbbPlbdfmfnt, i);
                    }
                    if (siouldPbdTbbRun(tbbPlbdfmfnt, i)) {
                        pbdTbbRun(tbbPlbdfmfnt, stbrt, fnd, rfturnAt);
                    }
                    if (tbbPlbdfmfnt == RIGHT) {
                        x -= (mbxTbbWidti - tbbRunOvfrlby);
                    } flsf {
                        x += (mbxTbbWidti - tbbRunOvfrlby);
                    }
                }
            }

            // Pbd tif sflfdtfd tbb so tibt it bppfbrs rbisfd in front
            pbdSflfdtfdTbb(tbbPlbdfmfnt, sflfdtfdIndfx);

            // if rigit to lfft bnd tbb plbdfmfnt on tif top or
            // tif bottom, flip x positions bnd bdjust by widtis
            if (!lfftToRigit && !vfrtidblTbbRuns) {
                int rigitMbrgin = sizf.widti
                                  - (insfts.rigit + tbbArfbInsfts.rigit);
                for (i = 0; i < tbbCount; i++) {
                    rfdts[i].x = rigitMbrgin - rfdts[i].x - rfdts[i].widti;
                }
            }
        }


       /*
       * Rotbtfs tif run-indfx brrby so tibt tif sflfdtfd run is run[0]
       */
        protfdtfd void rotbtfTbbRuns(int tbbPlbdfmfnt, int sflfdtfdRun) {
            for (int i = 0; i < sflfdtfdRun; i++) {
                int sbvf = tbbRuns[0];
                for (int j = 1; j < runCount; j++) {
                    tbbRuns[j - 1] = tbbRuns[j];
                }
                tbbRuns[runCount-1] = sbvf;
            }
        }

        protfdtfd void normblizfTbbRuns(int tbbPlbdfmfnt, int tbbCount,
                                     int stbrt, int mbx) {
            boolfbn vfrtidblTbbRuns = (tbbPlbdfmfnt == LEFT || tbbPlbdfmfnt == RIGHT);
            int run = runCount - 1;
            boolfbn kffpAdjusting = truf;
            doublf wfigit = 1.25;

            // At tiis point tif tbb runs brf pbdkfd to fit bs mbny
            // tbbs bs possiblf, wiidi dbn lfbvf tif lbst run witi b lot
            // of fxtrb spbdf (rfsulting in vfry fbt tbbs on tif lbst run).
            // So wf'll bttfmpt to distributf tiis fxtrb spbdf morf fvfnly
            // bdross tif runs in ordfr to mbkf tif runs look morf donsistfnt.
            //
            // Stbrting witi tif lbst run, dftfrminf wiftifr tif lbst tbb in
            // tif prfvious run would fit (gfnfrously) in tiis run; if so,
            // movf tbb to durrfnt run bnd siift tbbs bddordingly.  Cydlf
            // tirougi rfmbining runs using tif sbmf blgoritim.
            //
            wiilf (kffpAdjusting) {
                int lbst = lbstTbbInRun(tbbCount, run);
                int prfvLbst = lbstTbbInRun(tbbCount, run-1);
                int fnd;
                int prfvLbstLfn;

                if (!vfrtidblTbbRuns) {
                    fnd = rfdts[lbst].x + rfdts[lbst].widti;
                    prfvLbstLfn = (int)(mbxTbbWidti*wfigit);
                } flsf {
                    fnd = rfdts[lbst].y + rfdts[lbst].ifigit;
                    prfvLbstLfn = (int)(mbxTbbHfigit*wfigit*2);
                }

                // Cifdk if tif run ibs fnougi fxtrb spbdf to fit tif lbst tbb
                // from tif prfvious row...
                if (mbx - fnd > prfvLbstLfn) {

                    // Insfrt tbb from prfvious row bnd siift rfst ovfr
                    tbbRuns[run] = prfvLbst;
                    if (!vfrtidblTbbRuns) {
                        rfdts[prfvLbst].x = stbrt;
                    } flsf {
                        rfdts[prfvLbst].y = stbrt;
                    }
                    for (int i = prfvLbst+1; i <= lbst; i++) {
                        if (!vfrtidblTbbRuns) {
                            rfdts[i].x = rfdts[i-1].x + rfdts[i-1].widti;
                        } flsf {
                            rfdts[i].y = rfdts[i-1].y + rfdts[i-1].ifigit;
                        }
                    }

                } flsf if (run == runCount - 1) {
                    // no morf room lfft in lbst run, so wf'rf donf!
                    kffpAdjusting = fblsf;
                }
                if (run - 1 > 0) {
                    // difdk prfvious run nfxt...
                    run -= 1;
                } flsf {
                    // difdk lbst run bgbin...but rfquirf b iigifr rbtio
                    // of fxtrbspbdf-to-tbbsizf bfdbusf wf don't wbnt to
                    // fnd up witi too mbny tbbs on tif lbst run!
                    run = runCount - 1;
                    wfigit += .25;
                }
            }
        }

        protfdtfd void pbdTbbRun(int tbbPlbdfmfnt, int stbrt, int fnd, int mbx) {
            Rfdtbnglf lbstRfdt = rfdts[fnd];
            if (tbbPlbdfmfnt == TOP || tbbPlbdfmfnt == BOTTOM) {
                int runWidti = (lbstRfdt.x + lbstRfdt.widti) - rfdts[stbrt].x;
                int dfltbWidti = mbx - (lbstRfdt.x + lbstRfdt.widti);
                flobt fbdtor = (flobt)dfltbWidti / (flobt)runWidti;

                for (int j = stbrt; j <= fnd; j++) {
                    Rfdtbnglf pbstRfdt = rfdts[j];
                    if (j > stbrt) {
                        pbstRfdt.x = rfdts[j-1].x + rfdts[j-1].widti;
                    }
                    pbstRfdt.widti += Mbti.round((flobt)pbstRfdt.widti * fbdtor);
                }
                lbstRfdt.widti = mbx - lbstRfdt.x;
            } flsf {
                int runHfigit = (lbstRfdt.y + lbstRfdt.ifigit) - rfdts[stbrt].y;
                int dfltbHfigit = mbx - (lbstRfdt.y + lbstRfdt.ifigit);
                flobt fbdtor = (flobt)dfltbHfigit / (flobt)runHfigit;

                for (int j = stbrt; j <= fnd; j++) {
                    Rfdtbnglf pbstRfdt = rfdts[j];
                    if (j > stbrt) {
                        pbstRfdt.y = rfdts[j-1].y + rfdts[j-1].ifigit;
                    }
                    pbstRfdt.ifigit += Mbti.round((flobt)pbstRfdt.ifigit * fbdtor);
                }
                lbstRfdt.ifigit = mbx - lbstRfdt.y;
            }
        }

        protfdtfd void pbdSflfdtfdTbb(int tbbPlbdfmfnt, int sflfdtfdIndfx) {

            if (sflfdtfdIndfx >= 0) {
                Rfdtbnglf sflRfdt = rfdts[sflfdtfdIndfx];
                Insfts pbdInsfts = gftSflfdtfdTbbPbdInsfts(tbbPlbdfmfnt);
                sflRfdt.x -= pbdInsfts.lfft;
                sflRfdt.widti += (pbdInsfts.lfft + pbdInsfts.rigit);
                sflRfdt.y -= pbdInsfts.top;
                sflRfdt.ifigit += (pbdInsfts.top + pbdInsfts.bottom);

                if (!sdrollbblfTbbLbyoutEnbblfd()) { // WRAP_TAB_LAYOUT
                    // do not fxpbnd sflfdtfd tbb morf tifn nfdfssbry
                    Dimfnsion sizf = tbbPbnf.gftSizf();
                    Insfts insfts = tbbPbnf.gftInsfts();

                    if ((tbbPlbdfmfnt == LEFT) || (tbbPlbdfmfnt == RIGHT)) {
                        int top = insfts.top - sflRfdt.y;
                        if (top > 0) {
                            sflRfdt.y += top;
                            sflRfdt.ifigit -= top;
                        }
                        int bottom = (sflRfdt.y + sflRfdt.ifigit) + insfts.bottom - sizf.ifigit;
                        if (bottom > 0) {
                            sflRfdt.ifigit -= bottom;
                        }
                    } flsf {
                        int lfft = insfts.lfft - sflRfdt.x;
                        if (lfft > 0) {
                            sflRfdt.x += lfft;
                            sflRfdt.widti -= lfft;
                        }
                        int rigit = (sflRfdt.x + sflRfdt.widti) + insfts.rigit - sizf.widti;
                        if (rigit > 0) {
                            sflRfdt.widti -= rigit;
                        }
                    }
                }
            }
        }
    }

    privbtf dlbss TbbbfdPbnfSdrollLbyout fxtfnds TbbbfdPbnfLbyout {

        protfdtfd int prfffrrfdTbbArfbHfigit(int tbbPlbdfmfnt, int widti) {
            rfturn dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
        }

        protfdtfd int prfffrrfdTbbArfbWidti(int tbbPlbdfmfnt, int ifigit) {
            rfturn dbldulbtfMbxTbbWidti(tbbPlbdfmfnt);
        }

        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            /* Somf of tif dodf in tiis mftiod dfbls witi dibnging tif
             * visibility of domponfnts to iidf bnd siow tif dontfnts for tif
             * sflfdtfd tbb. Tiis is oldfr dodf tibt ibs sindf bffn duplidbtfd
             * in JTbbbfdPbnf.firfStbtfCibngfd(), so bs to bllow visibility
             * dibngfs to ibppfn soonfr (sff tif notf tifrf). Tiis dodf rfmbins
             * for bbdkwbrd dompbtibility bs tifrf brf somf dbsfs, sudi bs
             * subdlbssfs tibt don't firfStbtfCibngfd() wifrf it mby bf usfd.
             * Any dibngfs ifrf nffd to bf kfpt in syndi witi
             * JTbbbfdPbnf.firfStbtfCibngfd().
             */

            sftRollovfrTbb(-1);

            int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
            int tbbCount = tbbPbnf.gftTbbCount();
            Insfts insfts = tbbPbnf.gftInsfts();
            int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
            Componfnt visiblfComponfnt = gftVisiblfComponfnt();

            dbldulbtfLbyoutInfo();

            Componfnt sflfdtfdComponfnt = null;
            if (sflfdtfdIndfx < 0) {
                if (visiblfComponfnt != null) {
                    // Tif lbst tbb wbs rfmovfd, so rfmovf tif domponfnt
                    sftVisiblfComponfnt(null);
                }
            } flsf {
                sflfdtfdComponfnt = tbbPbnf.gftComponfntAt(sflfdtfdIndfx);
            }

            if (tbbPbnf.gftTbbCount() == 0) {
                tbbSdrollfr.droppfdEdgf.rfsftPbrbms();
                tbbSdrollfr.sdrollForwbrdButton.sftVisiblf(fblsf);
                tbbSdrollfr.sdrollBbdkwbrdButton.sftVisiblf(fblsf);
                rfturn;
            }

            boolfbn siouldCibngfFodus = fblsf;

            // In ordfr to bllow progrbms to usf b singlf domponfnt
            // bs tif displby for multiplf tbbs, wf will not dibngf
            // tif visiblf dompnfnt if tif durrfntly sflfdtfd tbb
            // ibs b null domponfnt.  Tiis is b bit didfy, bs wf don't
            // fxpliditly stbtf wf support tiis in tif spfd, but sindf
            // progrbms brf now dfpfnding on tiis, wf'rf mbking it work.
            //
            if(sflfdtfdComponfnt != null) {
                if(sflfdtfdComponfnt != visiblfComponfnt &&
                        visiblfComponfnt != null) {
                    if(SwingUtilitifs.findFodusOwnfr(visiblfComponfnt) != null) {
                        siouldCibngfFodus = truf;
                    }
                }
                sftVisiblfComponfnt(sflfdtfdComponfnt);
            }
            int tx, ty, tw, ti; // tbb brfb bounds
            int dx, dy, dw, di; // dontfnt brfb bounds
            Insfts dontfntInsfts = gftContfntBordfrInsfts(tbbPlbdfmfnt);
            Rfdtbnglf bounds = tbbPbnf.gftBounds();
            int numCiildrfn = tbbPbnf.gftComponfntCount();

            if(numCiildrfn > 0) {
                switdi(tbbPlbdfmfnt) {
                    dbsf LEFT:
                        // dbldulbtf tbb brfb bounds
                        tw = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
                        ti = bounds.ifigit - insfts.top - insfts.bottom;
                        tx = insfts.lfft;
                        ty = insfts.top;

                        // dbldulbtf dontfnt brfb bounds
                        dx = tx + tw + dontfntInsfts.lfft;
                        dy = ty + dontfntInsfts.top;
                        dw = bounds.widti - insfts.lfft - insfts.rigit - tw -
                                dontfntInsfts.lfft - dontfntInsfts.rigit;
                        di = bounds.ifigit - insfts.top - insfts.bottom -
                                dontfntInsfts.top - dontfntInsfts.bottom;
                        brfbk;
                    dbsf RIGHT:
                        // dbldulbtf tbb brfb bounds
                        tw = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
                        ti = bounds.ifigit - insfts.top - insfts.bottom;
                        tx = bounds.widti - insfts.rigit - tw;
                        ty = insfts.top;

                        // dbldulbtf dontfnt brfb bounds
                        dx = insfts.lfft + dontfntInsfts.lfft;
                        dy = insfts.top + dontfntInsfts.top;
                        dw = bounds.widti - insfts.lfft - insfts.rigit - tw -
                                dontfntInsfts.lfft - dontfntInsfts.rigit;
                        di = bounds.ifigit - insfts.top - insfts.bottom -
                                dontfntInsfts.top - dontfntInsfts.bottom;
                        brfbk;
                    dbsf BOTTOM:
                        // dbldulbtf tbb brfb bounds
                        tw = bounds.widti - insfts.lfft - insfts.rigit;
                        ti = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
                        tx = insfts.lfft;
                        ty = bounds.ifigit - insfts.bottom - ti;

                        // dbldulbtf dontfnt brfb bounds
                        dx = insfts.lfft + dontfntInsfts.lfft;
                        dy = insfts.top + dontfntInsfts.top;
                        dw = bounds.widti - insfts.lfft - insfts.rigit -
                                dontfntInsfts.lfft - dontfntInsfts.rigit;
                        di = bounds.ifigit - insfts.top - insfts.bottom - ti -
                                dontfntInsfts.top - dontfntInsfts.bottom;
                        brfbk;
                    dbsf TOP:
                    dffbult:
                        // dbldulbtf tbb brfb bounds
                        tw = bounds.widti - insfts.lfft - insfts.rigit;
                        ti = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
                        tx = insfts.lfft;
                        ty = insfts.top;

                        // dbldulbtf dontfnt brfb bounds
                        dx = tx + dontfntInsfts.lfft;
                        dy = ty + ti + dontfntInsfts.top;
                        dw = bounds.widti - insfts.lfft - insfts.rigit -
                                dontfntInsfts.lfft - dontfntInsfts.rigit;
                        di = bounds.ifigit - insfts.top - insfts.bottom - ti -
                                dontfntInsfts.top - dontfntInsfts.bottom;
                }

                for(int i = 0; i < numCiildrfn; i++) {
                    Componfnt diild = tbbPbnf.gftComponfnt(i);

                    if(tbbSdrollfr != null && diild == tbbSdrollfr.vifwport) {
                        JVifwport vifwport = (JVifwport) diild;
                        Rfdtbnglf vifwRfdt = vifwport.gftVifwRfdt();
                        int vw = tw;
                        int vi = ti;
                        Dimfnsion butSizf = tbbSdrollfr.sdrollForwbrdButton.gftPrfffrrfdSizf();
                        switdi(tbbPlbdfmfnt) {
                            dbsf LEFT:
                            dbsf RIGHT:
                                int totblTbbHfigit = rfdts[tbbCount - 1].y + rfdts[tbbCount - 1].ifigit;
                                if(totblTbbHfigit > ti) {
                                    // Allow spbdf for sdrollbuttons
                                    vi = (ti > 2 * butSizf.ifigit) ? ti - 2 * butSizf.ifigit : 0;
                                    if(totblTbbHfigit - vifwRfdt.y <= vi) {
                                        // Sdrollfd to tif fnd, so fnsurf tif vifwport sizf is
                                        // sudi tibt tif sdroll offsft bligns witi b tbb
                                        vi = totblTbbHfigit - vifwRfdt.y;
                                    }
                                }
                                brfbk;
                            dbsf BOTTOM:
                            dbsf TOP:
                            dffbult:
                                int totblTbbWidti = rfdts[tbbCount - 1].x + rfdts[tbbCount - 1].widti;
                                if(totblTbbWidti > tw) {
                                    // Nffd to bllow spbdf for sdrollbuttons
                                    vw = (tw > 2 * butSizf.widti) ? tw - 2 * butSizf.widti : 0;
                                    if(totblTbbWidti - vifwRfdt.x <= vw) {
                                        // Sdrollfd to tif fnd, so fnsurf tif vifwport sizf is
                                        // sudi tibt tif sdroll offsft bligns witi b tbb
                                        vw = totblTbbWidti - vifwRfdt.x;
                                    }
                                }
                        }
                        diild.sftBounds(tx, ty, vw, vi);

                    } flsf if(tbbSdrollfr != null &&
                            (diild == tbbSdrollfr.sdrollForwbrdButton ||
                            diild == tbbSdrollfr.sdrollBbdkwbrdButton)) {
                        Componfnt sdrollbutton = diild;
                        Dimfnsion bsizf = sdrollbutton.gftPrfffrrfdSizf();
                        int bx = 0;
                        int by = 0;
                        int bw = bsizf.widti;
                        int bi = bsizf.ifigit;
                        boolfbn visiblf = fblsf;

                        switdi(tbbPlbdfmfnt) {
                            dbsf LEFT:
                            dbsf RIGHT:
                                int totblTbbHfigit = rfdts[tbbCount - 1].y + rfdts[tbbCount - 1].ifigit;
                                if(totblTbbHfigit > ti) {
                                    visiblf = truf;
                                    bx = (tbbPlbdfmfnt == LEFT ? tx + tw - bsizf.widti : tx);
                                    by = (diild == tbbSdrollfr.sdrollForwbrdButton) ?
                                            bounds.ifigit - insfts.bottom - bsizf.ifigit :
                                            bounds.ifigit - insfts.bottom - 2 * bsizf.ifigit;
                                }
                                brfbk;

                            dbsf BOTTOM:
                            dbsf TOP:
                            dffbult:
                                int totblTbbWidti = rfdts[tbbCount - 1].x + rfdts[tbbCount - 1].widti;

                                if(totblTbbWidti > tw) {
                                    visiblf = truf;
                                    bx = (diild == tbbSdrollfr.sdrollForwbrdButton) ?
                                            bounds.widti - insfts.lfft - bsizf.widti :
                                            bounds.widti - insfts.lfft - 2 * bsizf.widti;
                                    by = (tbbPlbdfmfnt == TOP ? ty + ti - bsizf.ifigit : ty);
                                }
                        }
                        diild.sftVisiblf(visiblf);
                        if(visiblf) {
                            diild.sftBounds(bx, by, bw, bi);
                        }

                    } flsf {
                        // All dontfnt diildrfn...
                        diild.sftBounds(dx, dy, dw, di);
                    }
                }
                supfr.lbyoutTbbComponfnts();
                lbyoutCroppfdEdgf();
                if(siouldCibngfFodus) {
                    if(!rfqufstFodusForVisiblfComponfnt()) {
                        tbbPbnf.rfqufstFodus();
                    }
                }
            }
        }

        privbtf void lbyoutCroppfdEdgf() {
            tbbSdrollfr.droppfdEdgf.rfsftPbrbms();
            Rfdtbnglf vifwRfdt = tbbSdrollfr.vifwport.gftVifwRfdt();
            int droplinf;
            for (int i = 0; i < rfdts.lfngti; i++) {
                Rfdtbnglf tbbRfdt = rfdts[i];
                switdi (tbbPbnf.gftTbbPlbdfmfnt()) {
                    dbsf LEFT:
                    dbsf RIGHT:
                        droplinf = vifwRfdt.y + vifwRfdt.ifigit;
                        if ((tbbRfdt.y < droplinf) && (tbbRfdt.y + tbbRfdt.ifigit > droplinf)) {
                            tbbSdrollfr.droppfdEdgf.sftPbrbms(i, droplinf - tbbRfdt.y - 1,
                                    -durrfntTbbArfbInsfts.lfft,  0);
                        }
                        brfbk;
                    dbsf TOP:
                    dbsf BOTTOM:
                    dffbult:
                        droplinf = vifwRfdt.x + vifwRfdt.widti;
                        if ((tbbRfdt.x < droplinf - 1) && (tbbRfdt.x + tbbRfdt.widti > droplinf)) {
                            tbbSdrollfr.droppfdEdgf.sftPbrbms(i, droplinf - tbbRfdt.x - 1,
                                    0, -durrfntTbbArfbInsfts.top);
                        }
                }
            }
        }

        protfdtfd void dbldulbtfTbbRfdts(int tbbPlbdfmfnt, int tbbCount) {
            FontMftrids mftrids = gftFontMftrids();
            Dimfnsion sizf = tbbPbnf.gftSizf();
            Insfts insfts = tbbPbnf.gftInsfts();
            Insfts tbbArfbInsfts = gftTbbArfbInsfts(tbbPlbdfmfnt);
            int fontHfigit = mftrids.gftHfigit();
            int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
            int i;
            boolfbn vfrtidblTbbRuns = (tbbPlbdfmfnt == LEFT || tbbPlbdfmfnt == RIGHT);
            boolfbn lfftToRigit = BbsidGrbpiidsUtils.isLfftToRigit(tbbPbnf);
            int x = tbbArfbInsfts.lfft;
            int y = tbbArfbInsfts.top;
            int totblWidti = 0;
            int totblHfigit = 0;

            //
            // Cbldulbtf bounds witiin wiidi b tbb run must fit
            //
            switdi(tbbPlbdfmfnt) {
              dbsf LEFT:
              dbsf RIGHT:
                  mbxTbbWidti = dbldulbtfMbxTbbWidti(tbbPlbdfmfnt);
                  brfbk;
              dbsf BOTTOM:
              dbsf TOP:
              dffbult:
                  mbxTbbHfigit = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
            }

            runCount = 0;
            sflfdtfdRun = -1;

            if (tbbCount == 0) {
                rfturn;
            }

            sflfdtfdRun = 0;
            runCount = 1;

            // Run tirougi tbbs bnd lby tifm out in b singlf run
            Rfdtbnglf rfdt;
            for (i = 0; i < tbbCount; i++) {
                rfdt = rfdts[i];

                if (!vfrtidblTbbRuns) {
                    // Tbbs on TOP or BOTTOM....
                    if (i > 0) {
                        rfdt.x = rfdts[i-1].x + rfdts[i-1].widti;
                    } flsf {
                        tbbRuns[0] = 0;
                        mbxTbbWidti = 0;
                        totblHfigit += mbxTbbHfigit;
                        rfdt.x = x;
                    }
                    rfdt.widti = dbldulbtfTbbWidti(tbbPlbdfmfnt, i, mftrids);
                    totblWidti = rfdt.x + rfdt.widti;
                    mbxTbbWidti = Mbti.mbx(mbxTbbWidti, rfdt.widti);

                    rfdt.y = y;
                    rfdt.ifigit = mbxTbbHfigit/* - 2*/;

                } flsf {
                    // Tbbs on LEFT or RIGHT...
                    if (i > 0) {
                        rfdt.y = rfdts[i-1].y + rfdts[i-1].ifigit;
                    } flsf {
                        tbbRuns[0] = 0;
                        mbxTbbHfigit = 0;
                        totblWidti = mbxTbbWidti;
                        rfdt.y = y;
                    }
                    rfdt.ifigit = dbldulbtfTbbHfigit(tbbPlbdfmfnt, i, fontHfigit);
                    totblHfigit = rfdt.y + rfdt.ifigit;
                    mbxTbbHfigit = Mbti.mbx(mbxTbbHfigit, rfdt.ifigit);

                    rfdt.x = x;
                    rfdt.widti = mbxTbbWidti/* - 2*/;

                }
            }

            if (tbbsOvfrlbpBordfr) {
                // Pbd tif sflfdtfd tbb so tibt it bppfbrs rbisfd in front
                pbdSflfdtfdTbb(tbbPlbdfmfnt, sflfdtfdIndfx);
            }

            // if rigit to lfft bnd tbb plbdfmfnt on tif top or
            // tif bottom, flip x positions bnd bdjust by widtis
            if (!lfftToRigit && !vfrtidblTbbRuns) {
                int rigitMbrgin = sizf.widti
                                  - (insfts.rigit + tbbArfbInsfts.rigit);
                for (i = 0; i < tbbCount; i++) {
                    rfdts[i].x = rigitMbrgin - rfdts[i].x - rfdts[i].widti;
                }
            }
            tbbSdrollfr.tbbPbnfl.sftPrfffrrfdSizf(nfw Dimfnsion(totblWidti, totblHfigit));
        }
    }

    privbtf dlbss SdrollbblfTbbSupport implfmfnts AdtionListfnfr,
                            CibngfListfnfr {
        publid SdrollbblfTbbVifwport vifwport;
        publid SdrollbblfTbbPbnfl tbbPbnfl;
        publid JButton sdrollForwbrdButton;
        publid JButton sdrollBbdkwbrdButton;
        publid CroppfdEdgf droppfdEdgf;
        publid int lfbdingTbbIndfx;

        privbtf Point tbbVifwPosition = nfw Point(0,0);

        SdrollbblfTbbSupport(int tbbPlbdfmfnt) {
            vifwport = nfw SdrollbblfTbbVifwport();
            tbbPbnfl = nfw SdrollbblfTbbPbnfl();
            vifwport.sftVifw(tbbPbnfl);
            vifwport.bddCibngfListfnfr(tiis);
            droppfdEdgf = nfw CroppfdEdgf();
            drfbtfButtons();
        }

        /**
         * Rfdrfbtfs tif sdroll buttons bnd bdds tifm to tif TbbbfdPbnf.
         */
        void drfbtfButtons() {
            if (sdrollForwbrdButton != null) {
                tbbPbnf.rfmovf(sdrollForwbrdButton);
                sdrollForwbrdButton.rfmovfAdtionListfnfr(tiis);
                tbbPbnf.rfmovf(sdrollBbdkwbrdButton);
                sdrollBbdkwbrdButton.rfmovfAdtionListfnfr(tiis);
            }
            int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
            if (tbbPlbdfmfnt == TOP || tbbPlbdfmfnt == BOTTOM) {
                sdrollForwbrdButton = drfbtfSdrollButton(EAST);
                sdrollBbdkwbrdButton = drfbtfSdrollButton(WEST);

            } flsf { // tbbPlbdfmfnt = LEFT || RIGHT
                sdrollForwbrdButton = drfbtfSdrollButton(SOUTH);
                sdrollBbdkwbrdButton = drfbtfSdrollButton(NORTH);
            }
            sdrollForwbrdButton.bddAdtionListfnfr(tiis);
            sdrollBbdkwbrdButton.bddAdtionListfnfr(tiis);
            tbbPbnf.bdd(sdrollForwbrdButton);
            tbbPbnf.bdd(sdrollBbdkwbrdButton);
        }

        publid void sdrollForwbrd(int tbbPlbdfmfnt) {
            Dimfnsion vifwSizf = vifwport.gftVifwSizf();
            Rfdtbnglf vifwRfdt = vifwport.gftVifwRfdt();

            if (tbbPlbdfmfnt == TOP || tbbPlbdfmfnt == BOTTOM) {
                if (vifwRfdt.widti >= vifwSizf.widti - vifwRfdt.x) {
                    rfturn; // no room lfft to sdroll
                }
            } flsf { // tbbPlbdfmfnt == LEFT || tbbPlbdfmfnt == RIGHT
                if (vifwRfdt.ifigit >= vifwSizf.ifigit - vifwRfdt.y) {
                    rfturn;
                }
            }
            sftLfbdingTbbIndfx(tbbPlbdfmfnt, lfbdingTbbIndfx+1);
        }

        publid void sdrollBbdkwbrd(int tbbPlbdfmfnt) {
            if (lfbdingTbbIndfx == 0) {
                rfturn; // no room lfft to sdroll
            }
            sftLfbdingTbbIndfx(tbbPlbdfmfnt, lfbdingTbbIndfx-1);
        }

        publid void sftLfbdingTbbIndfx(int tbbPlbdfmfnt, int indfx) {
            lfbdingTbbIndfx = indfx;
            Dimfnsion vifwSizf = vifwport.gftVifwSizf();
            Rfdtbnglf vifwRfdt = vifwport.gftVifwRfdt();

            switdi(tbbPlbdfmfnt) {
              dbsf TOP:
              dbsf BOTTOM:
                tbbVifwPosition.x = lfbdingTbbIndfx == 0? 0 : rfdts[lfbdingTbbIndfx].x;

                if ((vifwSizf.widti - tbbVifwPosition.x) < vifwRfdt.widti) {
                    // Wf'vf sdrollfd to tif fnd, so bdjust tif vifwport sizf
                    // to fnsurf tif vifw position rfmbins blignfd on b tbb boundbry
                    Dimfnsion fxtfntSizf = nfw Dimfnsion(vifwSizf.widti - tbbVifwPosition.x,
                                                         vifwRfdt.ifigit);
                    vifwport.sftExtfntSizf(fxtfntSizf);
                }
                brfbk;
              dbsf LEFT:
              dbsf RIGHT:
                tbbVifwPosition.y = lfbdingTbbIndfx == 0? 0 : rfdts[lfbdingTbbIndfx].y;

                if ((vifwSizf.ifigit - tbbVifwPosition.y) < vifwRfdt.ifigit) {
                // Wf'vf sdrollfd to tif fnd, so bdjust tif vifwport sizf
                // to fnsurf tif vifw position rfmbins blignfd on b tbb boundbry
                     Dimfnsion fxtfntSizf = nfw Dimfnsion(vifwRfdt.widti,
                                                          vifwSizf.ifigit - tbbVifwPosition.y);
                     vifwport.sftExtfntSizf(fxtfntSizf);
                }
            }
            vifwport.sftVifwPosition(tbbVifwPosition);
        }

        publid void stbtfCibngfd(CibngfEvfnt f) {
            updbtfVifw();
        }

        privbtf void updbtfVifw() {
            int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
            int tbbCount = tbbPbnf.gftTbbCount();
            bssurfRfdtsCrfbtfd(tbbCount);
            Rfdtbnglf vpRfdt = vifwport.gftBounds();
            Dimfnsion vifwSizf = vifwport.gftVifwSizf();
            Rfdtbnglf vifwRfdt = vifwport.gftVifwRfdt();

            lfbdingTbbIndfx = gftClosfstTbb(vifwRfdt.x, vifwRfdt.y);

            // If tif tbb isn't rigit blignfd, bdjust it.
            if (lfbdingTbbIndfx + 1 < tbbCount) {
                switdi (tbbPlbdfmfnt) {
                dbsf TOP:
                dbsf BOTTOM:
                    if (rfdts[lfbdingTbbIndfx].x < vifwRfdt.x) {
                        lfbdingTbbIndfx++;
                    }
                    brfbk;
                dbsf LEFT:
                dbsf RIGHT:
                    if (rfdts[lfbdingTbbIndfx].y < vifwRfdt.y) {
                        lfbdingTbbIndfx++;
                    }
                    brfbk;
                }
            }
            Insfts dontfntInsfts = gftContfntBordfrInsfts(tbbPlbdfmfnt);
            switdi(tbbPlbdfmfnt) {
              dbsf LEFT:
                  tbbPbnf.rfpbint(vpRfdt.x+vpRfdt.widti, vpRfdt.y,
                                  dontfntInsfts.lfft, vpRfdt.ifigit);
                  sdrollBbdkwbrdButton.sftEnbblfd(
                          vifwRfdt.y > 0 && lfbdingTbbIndfx > 0);
                  sdrollForwbrdButton.sftEnbblfd(
                          lfbdingTbbIndfx < tbbCount-1 &&
                          vifwSizf.ifigit-vifwRfdt.y > vifwRfdt.ifigit);
                  brfbk;
              dbsf RIGHT:
                  tbbPbnf.rfpbint(vpRfdt.x-dontfntInsfts.rigit, vpRfdt.y,
                                  dontfntInsfts.rigit, vpRfdt.ifigit);
                  sdrollBbdkwbrdButton.sftEnbblfd(
                          vifwRfdt.y > 0 && lfbdingTbbIndfx > 0);
                  sdrollForwbrdButton.sftEnbblfd(
                          lfbdingTbbIndfx < tbbCount-1 &&
                          vifwSizf.ifigit-vifwRfdt.y > vifwRfdt.ifigit);
                  brfbk;
              dbsf BOTTOM:
                  tbbPbnf.rfpbint(vpRfdt.x, vpRfdt.y-dontfntInsfts.bottom,
                                  vpRfdt.widti, dontfntInsfts.bottom);
                  sdrollBbdkwbrdButton.sftEnbblfd(
                          vifwRfdt.x > 0 && lfbdingTbbIndfx > 0);
                  sdrollForwbrdButton.sftEnbblfd(
                          lfbdingTbbIndfx < tbbCount-1 &&
                          vifwSizf.widti-vifwRfdt.x > vifwRfdt.widti);
                  brfbk;
              dbsf TOP:
              dffbult:
                  tbbPbnf.rfpbint(vpRfdt.x, vpRfdt.y+vpRfdt.ifigit,
                                  vpRfdt.widti, dontfntInsfts.top);
                  sdrollBbdkwbrdButton.sftEnbblfd(
                          vifwRfdt.x > 0 && lfbdingTbbIndfx > 0);
                  sdrollForwbrdButton.sftEnbblfd(
                          lfbdingTbbIndfx < tbbCount-1 &&
                          vifwSizf.widti-vifwRfdt.x > vifwRfdt.widti);
            }
        }

        /**
         * AdtionListfnfr for tif sdroll buttons.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            AdtionMbp mbp = tbbPbnf.gftAdtionMbp();

            if (mbp != null) {
                String bdtionKfy;

                if (f.gftSourdf() == sdrollForwbrdButton) {
                    bdtionKfy = "sdrollTbbsForwbrdAdtion";
                }
                flsf {
                    bdtionKfy = "sdrollTbbsBbdkwbrdAdtion";
                }
                Adtion bdtion = mbp.gft(bdtionKfy);

                if (bdtion != null && bdtion.isEnbblfd()) {
                    bdtion.bdtionPfrformfd(nfw AdtionEvfnt(tbbPbnf,
                        AdtionEvfnt.ACTION_PERFORMED, null, f.gftWifn(),
                        f.gftModififrs()));
                }
            }
        }

        publid String toString() {
            rfturn "vifwport.vifwSizf=" + vifwport.gftVifwSizf() + "\n" +
                              "vifwport.vifwRfdtbnglf="+vifwport.gftVifwRfdt()+"\n"+
                              "lfbdingTbbIndfx="+lfbdingTbbIndfx+"\n"+
                              "tbbVifwPosition=" + tbbVifwPosition;
        }

    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SdrollbblfTbbVifwport fxtfnds JVifwport implfmfnts UIRfsourdf {
        publid SdrollbblfTbbVifwport() {
            supfr();
            sftNbmf("TbbbfdPbnf.sdrollbblfVifwport");
            sftSdrollModf(SIMPLE_SCROLL_MODE);
            sftOpbquf(tbbPbnf.isOpbquf());
            Color bgColor = UIMbnbgfr.gftColor("TbbbfdPbnf.tbbArfbBbdkground");
            if (bgColor == null) {
                bgColor = tbbPbnf.gftBbdkground();
            }
            sftBbdkground(bgColor);
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SdrollbblfTbbPbnfl fxtfnds JPbnfl implfmfnts UIRfsourdf {
        publid SdrollbblfTbbPbnfl() {
            supfr(null);
            sftOpbquf(tbbPbnf.isOpbquf());
            Color bgColor = UIMbnbgfr.gftColor("TbbbfdPbnf.tbbArfbBbdkground");
            if (bgColor == null) {
                bgColor = tbbPbnf.gftBbdkground();
            }
            sftBbdkground(bgColor);
        }
        publid void pbintComponfnt(Grbpiids g) {
            supfr.pbintComponfnt(g);
            BbsidTbbbfdPbnfUI.tiis.pbintTbbArfb(g, tbbPbnf.gftTbbPlbdfmfnt(),
                                                tbbPbnf.gftSflfdtfdIndfx());
            if (tbbSdrollfr.droppfdEdgf.isPbrbmsSft() && tbbContbinfr == null) {
                Rfdtbnglf droppfdRfdt = rfdts[tbbSdrollfr.droppfdEdgf.gftTbbIndfx()];
                g.trbnslbtf(droppfdRfdt.x, droppfdRfdt.y);
                tbbSdrollfr.droppfdEdgf.pbintComponfnt(g);
                g.trbnslbtf(-droppfdRfdt.x, -droppfdRfdt.y);
            }
        }

        publid void doLbyout() {
            if (gftComponfntCount() > 0) {
                Componfnt diild = gftComponfnt(0);
                diild.sftBounds(0, 0, gftWidti(), gftHfigit());
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SdrollbblfTbbButton fxtfnds BbsidArrowButton implfmfnts UIRfsourdf,
                                                                            SwingConstbnts {
        publid SdrollbblfTbbButton(int dirfdtion) {
            supfr(dirfdtion,
                  UIMbnbgfr.gftColor("TbbbfdPbnf.sflfdtfd"),
                  UIMbnbgfr.gftColor("TbbbfdPbnf.sibdow"),
                  UIMbnbgfr.gftColor("TbbbfdPbnf.dbrkSibdow"),
                  UIMbnbgfr.gftColor("TbbbfdPbnf.iigiligit"));
        }
    }


// Controllfr: fvfnt listfnfrs

    privbtf dlbss Hbndlfr implfmfnts CibngfListfnfr, ContbinfrListfnfr,
                  FodusListfnfr, MousfListfnfr, MousfMotionListfnfr,
                  PropfrtyCibngfListfnfr {
        //
        // PropfrtyCibngfListfnfr
        //
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            JTbbbfdPbnf pbnf = (JTbbbfdPbnf)f.gftSourdf();
            String nbmf = f.gftPropfrtyNbmf();
            boolfbn isSdrollLbyout = sdrollbblfTbbLbyoutEnbblfd();
            if (nbmf == "mnfmonidAt") {
                updbtfMnfmonids();
                pbnf.rfpbint();
            }
            flsf if (nbmf == "displbyfdMnfmonidIndfxAt") {
                pbnf.rfpbint();
            }
            flsf if (nbmf =="indfxForTitlf") {
                dbldulbtfdBbsflinf = fblsf;
                Intfgfr indfx = (Intfgfr) f.gftNfwVbluf();
                // rfmovf tif durrfnt indfx
                // to lft updbtfHtmlVifws() insfrt tif dorrfdt onf
                if (itmlVifws != null) {
                    itmlVifws.rfmovfElfmfntAt(indfx);
                }
                updbtfHtmlVifws(indfx);
            } flsf if (nbmf == "tbbLbyoutPolidy") {
                BbsidTbbbfdPbnfUI.tiis.uninstbllUI(pbnf);
                BbsidTbbbfdPbnfUI.tiis.instbllUI(pbnf);
                dbldulbtfdBbsflinf = fblsf;
            } flsf if (nbmf == "tbbPlbdfmfnt") {
                if (sdrollbblfTbbLbyoutEnbblfd()) {
                    tbbSdrollfr.drfbtfButtons();
                }
                dbldulbtfdBbsflinf = fblsf;
            } flsf if (nbmf == "opbquf" && isSdrollLbyout) {
                boolfbn nfwVbl = ((Boolfbn)f.gftNfwVbluf()).boolfbnVbluf();
                tbbSdrollfr.tbbPbnfl.sftOpbquf(nfwVbl);
                tbbSdrollfr.vifwport.sftOpbquf(nfwVbl);
            } flsf if (nbmf == "bbdkground" && isSdrollLbyout) {
                Color nfwVbl = (Color)f.gftNfwVbluf();
                tbbSdrollfr.tbbPbnfl.sftBbdkground(nfwVbl);
                tbbSdrollfr.vifwport.sftBbdkground(nfwVbl);
                Color nfwColor = sflfdtfdColor == null ? nfwVbl : sflfdtfdColor;
                tbbSdrollfr.sdrollForwbrdButton.sftBbdkground(nfwColor);
                tbbSdrollfr.sdrollBbdkwbrdButton.sftBbdkground(nfwColor);
            } flsf if (nbmf == "indfxForTbbComponfnt") {
                if (tbbContbinfr != null) {
                    tbbContbinfr.rfmovfUnusfdTbbComponfnts();
                }
                Componfnt d = tbbPbnf.gftTbbComponfntAt(
                        (Intfgfr)f.gftNfwVbluf());
                if (d != null) {
                    if (tbbContbinfr == null) {
                        instbllTbbContbinfr();
                    } flsf {
                        tbbContbinfr.bdd(d);
                    }
                }
                tbbPbnf.rfvblidbtf();
                tbbPbnf.rfpbint();
                dbldulbtfdBbsflinf = fblsf;
            } flsf if (nbmf == "indfxForNullComponfnt") {
                isRunsDirty = truf;
                updbtfHtmlVifws((Intfgfr)f.gftNfwVbluf());
            } flsf if (nbmf == "font") {
                dbldulbtfdBbsflinf = fblsf;
            }
        }

        privbtf void updbtfHtmlVifws(int indfx) {
            String titlf = tbbPbnf.gftTitlfAt(indfx);
            boolfbn isHTML = BbsidHTML.isHTMLString(titlf);
            if (isHTML) {
                if (itmlVifws==null) {    // Initiblizf vfdtor
                    itmlVifws = drfbtfHTMLVfdtor();
                } flsf {                  // Vfdtor blrfbdy fxists
                    Vifw v = BbsidHTML.drfbtfHTMLVifw(tbbPbnf, titlf);
                    itmlVifws.insfrtElfmfntAt(v, indfx);
                }
            } flsf {                             // Not HTML
                if (itmlVifws!=null) {           // Add plbdfioldfr
                    itmlVifws.insfrtElfmfntAt(null, indfx);
                }                                // flsf nbdb!
            }
            updbtfMnfmonids();
        }

        //
        // CibngfListfnfr
        //
        publid void stbtfCibngfd(CibngfEvfnt f) {
            JTbbbfdPbnf tbbPbnf = (JTbbbfdPbnf)f.gftSourdf();
            tbbPbnf.rfvblidbtf();
            tbbPbnf.rfpbint();

            sftFodusIndfx(tbbPbnf.gftSflfdtfdIndfx(), fblsf);

            if (sdrollbblfTbbLbyoutEnbblfd()) {
                int indfx = tbbPbnf.gftSflfdtfdIndfx();
                if (indfx < rfdts.lfngti && indfx != -1) {
                    tbbSdrollfr.tbbPbnfl.sdrollRfdtToVisiblf(
                            (Rfdtbnglf)rfdts[indfx].dlonf());
                }
            }
        }

        //
        // MousfListfnfr
        //
        publid void mousfClidkfd(MousfEvfnt f) {
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
        }

        publid void mousfEntfrfd(MousfEvfnt f) {
            sftRollovfrTbb(f.gftX(), f.gftY());
        }

        publid void mousfExitfd(MousfEvfnt f) {
            sftRollovfrTbb(-1);
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            if (!tbbPbnf.isEnbblfd()) {
                rfturn;
            }
            int tbbIndfx = tbbForCoordinbtf(tbbPbnf, f.gftX(), f.gftY());
            if (tbbIndfx >= 0 && tbbPbnf.isEnbblfdAt(tbbIndfx)) {
                if (tbbIndfx != tbbPbnf.gftSflfdtfdIndfx()) {
                    // Clidking on unsflfdtfd tbb, dibngf sflfdtion, do NOT
                    // rfqufst fodus.
                    // Tiis will triggfr tif fodusIndfx to dibngf by wby
                    // of stbtfCibngfd.
                    tbbPbnf.sftSflfdtfdIndfx(tbbIndfx);
                }
                flsf if (tbbPbnf.isRfqufstFodusEnbblfd()) {
                    // Clidking on sflfdtfd tbb, try bnd givf tif tbbbfdpbnf
                    // fodus.  Rfpbint will oddur in fodusGbinfd.
                    tbbPbnf.rfqufstFodus();
                }
            }
        }

        //
        // MousfMotionListfnfr
        //
        publid void mousfDrbggfd(MousfEvfnt f) {
        }

        publid void mousfMovfd(MousfEvfnt f) {
            sftRollovfrTbb(f.gftX(), f.gftY());
        }

        //
        // FodusListfnfr
        //
        publid void fodusGbinfd(FodusEvfnt f) {
           sftFodusIndfx(tbbPbnf.gftSflfdtfdIndfx(), truf);
        }
        publid void fodusLost(FodusEvfnt f) {
           rfpbintTbb(fodusIndfx);
        }


        //
        // ContbinfrListfnfr
        //
    /* GES 2/3/99:
       Tif dontbinfr listfnfr dodf wbs bddfd to support HTML
       rfndfring of tbb titlfs.

       Idfblly, wf would bf bblf to listfn for propfrty dibngfs
       wifn b tbb is bddfd or its tfxt modififd.  At tif momfnt
       tifrf brf no sudi fvfnts bfdbusf tif Bfbns spfd dofsn't
       bllow 'indfxfd' propfrty dibngfs (i.f. tbb 2's tfxt dibngfd
       from A to B).

       In ordfr to gft bround tiis, wf listfn for tbbs to bf bddfd
       or rfmovfd by listfning for tif dontbinfr fvfnts.  wf tifn
       qufuf up b runnbblf (so tif domponfnt ibs b dibndf to domplftf
       tif bdd) wiidi difdks tif tbb titlf of tif nfw domponfnt to sff
       if it rfquirfs HTML rfndfring.

       Tif Vifws (onf pfr tbb titlf rfquiring HTML rfndfring) brf
       storfd in tif itmlVifws Vfdtor, wiidi is only bllodbtfd bftfr
       tif first timf wf run into bn HTML tbb.  Notf tibt tiis vfdtor
       is kfpt in stfp witi tif numbfr of pbgfs, bnd nulls brf bddfd
       for tiosf pbgfs wiosf tbb titlf do not rfquirf HTML rfndfring.

       Tiis mbkfs it fbsy for tif pbint bnd lbyout dodf to tfll
       wiftifr to invokf tif HTML fnginf witiout ibving to difdk
       tif string during timf-sfnsitivf opfrbtions.

       Wifn wf ibvf bddfd b wby to listfn for tbb bdditions bnd
       dibngfs to tbb tfxt, tiis dodf siould bf rfmovfd bnd
       rfplbdfd by somftiing wiidi usfs tibt.  */

        publid void domponfntAddfd(ContbinfrEvfnt f) {
            JTbbbfdPbnf tp = (JTbbbfdPbnf)f.gftContbinfr();
            Componfnt diild = f.gftCiild();
            if (diild instbndfof UIRfsourdf) {
                rfturn;
            }
            isRunsDirty = truf;
            updbtfHtmlVifws(tp.indfxOfComponfnt(diild));
        }
        publid void domponfntRfmovfd(ContbinfrEvfnt f) {
            JTbbbfdPbnf tp = (JTbbbfdPbnf)f.gftContbinfr();
            Componfnt diild = f.gftCiild();
            if (diild instbndfof UIRfsourdf) {
                rfturn;
            }

            // NOTE 4/15/2002 (joutwbtf):
            // Tiis fix is implfmfntfd using dlifnt propfrtifs sindf tifrf is
            // durrfntly no IndfxPropfrtyCibngfEvfnt.  Ondf
            // IndfxPropfrtyCibngfEvfnts ibvf bffn bddfd tiis dodf siould bf
            // modififd to usf it.
            Intfgfr indfxObj =
                (Intfgfr)tp.gftClifntPropfrty("__indfx_to_rfmovf__");
            if (indfxObj != null) {
                int indfx = indfxObj.intVbluf();
                if (itmlVifws != null && itmlVifws.sizf() > indfx) {
                    itmlVifws.rfmovfElfmfntAt(indfx);
                }
                tp.putClifntPropfrty("__indfx_to_rfmovf__", null);
            }
            isRunsDirty = truf;
            updbtfMnfmonids();

            vblidbtfFodusIndfx();
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidTbbbfdPbnfUI.
     */
    publid dlbss PropfrtyCibngfHbndlfr implfmfnts PropfrtyCibngfListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            gftHbndlfr().propfrtyCibngf(f);
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidTbbbfdPbnfUI.
     */
    publid dlbss TbbSflfdtionHbndlfr implfmfnts CibngfListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void stbtfCibngfd(CibngfEvfnt f) {
            gftHbndlfr().stbtfCibngfd(f);
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidTbbbfdPbnfUI.
     */
    publid dlbss MousfHbndlfr fxtfnds MousfAdbptfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void mousfPrfssfd(MousfEvfnt f) {
            gftHbndlfr().mousfPrfssfd(f);
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidTbbbfdPbnfUI.
     */
    publid dlbss FodusHbndlfr fxtfnds FodusAdbptfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void fodusGbinfd(FodusEvfnt f) {
            gftHbndlfr().fodusGbinfd(f);
        }
        publid void fodusLost(FodusEvfnt f) {
            gftHbndlfr().fodusLost(f);
        }
    }

    privbtf Vfdtor<Vifw> drfbtfHTMLVfdtor() {
        Vfdtor<Vifw> itmlVifws = nfw Vfdtor<Vifw>();
        int dount = tbbPbnf.gftTbbCount();
        if (dount>0) {
            for (int i=0 ; i<dount; i++) {
                String titlf = tbbPbnf.gftTitlfAt(i);
                if (BbsidHTML.isHTMLString(titlf)) {
                    itmlVifws.bddElfmfnt(BbsidHTML.drfbtfHTMLVifw(tbbPbnf, titlf));
                } flsf {
                    itmlVifws.bddElfmfnt(null);
                }
            }
        }
        rfturn itmlVifws;
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss TbbContbinfr fxtfnds JPbnfl implfmfnts UIRfsourdf {
        privbtf boolfbn notifyTbbbfdPbnf = truf;

        publid TbbContbinfr() {
            supfr(null);
            sftOpbquf(fblsf);
        }

        publid void rfmovf(Componfnt domp) {
            int indfx = tbbPbnf.indfxOfTbbComponfnt(domp);
            supfr.rfmovf(domp);
            if (notifyTbbbfdPbnf && indfx != -1) {
                tbbPbnf.sftTbbComponfntAt(indfx, null);
            }
        }

        privbtf void rfmovfUnusfdTbbComponfnts() {
            for (Componfnt d : gftComponfnts()) {
                if (!(d instbndfof UIRfsourdf)) {
                    int indfx = tbbPbnf.indfxOfTbbComponfnt(d);
                    if (indfx == -1) {
                        supfr.rfmovf(d);
                    }
                }
            }
        }

        publid boolfbn isOptimizfdDrbwingEnbblfd() {
            rfturn tbbSdrollfr != null && !tbbSdrollfr.droppfdEdgf.isPbrbmsSft();
        }

        publid void doLbyout() {
            // Wf lbyout tbbComponfnts in JTbbbfdPbnf's lbyout mbnbgfr
            // bnd usf tiis mftiod bs b iook for rfpbinting tbbs
            // to updbtf tbbs brfb f.g. wifn tif sizf of tbbComponfnt wbs dibngfd
            if (sdrollbblfTbbLbyoutEnbblfd()) {
                tbbSdrollfr.tbbPbnfl.rfpbint();
                tbbSdrollfr.updbtfVifw();
            } flsf {
                tbbPbnf.rfpbint(gftBounds());
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss CroppfdEdgf fxtfnds JPbnfl implfmfnts UIRfsourdf {
        privbtf Sibpf sibpf;
        privbtf int tbbIndfx;
        privbtf int droplinf;
        privbtf int dropx, dropy;

        publid CroppfdEdgf() {
            sftOpbquf(fblsf);
        }

        publid void sftPbrbms(int tbbIndfx, int droplinf, int dropx, int dropy) {
            tiis.tbbIndfx = tbbIndfx;
            tiis.droplinf = droplinf;
            tiis.dropx = dropx;
            tiis.dropy = dropy;
            Rfdtbnglf tbbRfdt = rfdts[tbbIndfx];
            sftBounds(tbbRfdt);
            sibpf = drfbtfCroppfdTbbSibpf(tbbPbnf.gftTbbPlbdfmfnt(), tbbRfdt, droplinf);
            if (gftPbrfnt() == null && tbbContbinfr != null) {
                tbbContbinfr.bdd(tiis, 0);
            }
        }

        publid void rfsftPbrbms() {
            sibpf = null;
            if (gftPbrfnt() == tbbContbinfr && tbbContbinfr != null) {
                tbbContbinfr.rfmovf(tiis);
            }
        }

        publid boolfbn isPbrbmsSft() {
            rfturn sibpf != null;
        }

        publid int gftTbbIndfx() {
            rfturn tbbIndfx;
        }

        publid int gftCroplinf() {
            rfturn droplinf;
        }

        publid int gftCroppfdSidfWidti() {
            rfturn 3;
        }

        privbtf Color gftBgColor() {
            Componfnt pbrfnt = tbbPbnf.gftPbrfnt();
            if (pbrfnt != null) {
                Color bg = pbrfnt.gftBbdkground();
                if (bg != null) {
                    rfturn bg;
                }
            }
            rfturn UIMbnbgfr.gftColor("dontrol");
        }

        protfdtfd void pbintComponfnt(Grbpiids g) {
            supfr.pbintComponfnt(g);
            if (isPbrbmsSft() && g instbndfof Grbpiids2D) {
                Grbpiids2D g2 = (Grbpiids2D) g;
                g2.dlipRfdt(0, 0, gftWidti(), gftHfigit());
                g2.sftColor(gftBgColor());
                g2.trbnslbtf(dropx, dropy);
                g2.fill(sibpf);
                pbintCroppfdTbbEdgf(g);
                g2.trbnslbtf(-dropx, -dropy);
            }
        }
    }
}
