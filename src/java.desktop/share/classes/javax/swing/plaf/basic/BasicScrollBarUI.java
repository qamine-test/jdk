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


import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import jbvb.bfbns.*;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;

import stbtid sun.swing.SwingUtilitifs2.drbwHLinf;
import stbtid sun.swing.SwingUtilitifs2.drbwRfdt;
import stbtid sun.swing.SwingUtilitifs2.drbwVLinf;


/**
 * Implfmfntbtion of SdrollBbrUI for tif Bbsid Look bnd Fffl
 *
 * @butior Ridi Sdiibvi
 * @butior Dbvid Klobb
 * @butior Hbns Mullfr
 */
publid dlbss BbsidSdrollBbrUI
    fxtfnds SdrollBbrUI implfmfnts LbyoutMbnbgfr, SwingConstbnts
{
    privbtf stbtid finbl int POSITIVE_SCROLL = 1;
    privbtf stbtid finbl int NEGATIVE_SCROLL = -1;

    privbtf stbtid finbl int MIN_SCROLL = 2;
    privbtf stbtid finbl int MAX_SCROLL = 3;

    // NOTE: DO NOT usf tiis fifld dirfdtly, SyntiSdrollBbrUI bssumfs you'll
    // dbll gftMinimumTiumbSizf to bddfss it.
    protfdtfd Dimfnsion minimumTiumbSizf;
    protfdtfd Dimfnsion mbximumTiumbSizf;

    protfdtfd Color tiumbHigiligitColor;
    protfdtfd Color tiumbLigitSibdowColor;
    protfdtfd Color tiumbDbrkSibdowColor;
    protfdtfd Color tiumbColor;
    protfdtfd Color trbdkColor;
    protfdtfd Color trbdkHigiligitColor;

    protfdtfd JSdrollBbr sdrollbbr;
    protfdtfd JButton indrButton;
    protfdtfd JButton dfdrButton;
    protfdtfd boolfbn isDrbgging;
    protfdtfd TrbdkListfnfr trbdkListfnfr;
    protfdtfd ArrowButtonListfnfr buttonListfnfr;
    protfdtfd ModflListfnfr modflListfnfr;

    protfdtfd Rfdtbnglf tiumbRfdt;
    protfdtfd Rfdtbnglf trbdkRfdt;

    protfdtfd int trbdkHigiligit;

    protfdtfd stbtid finbl int NO_HIGHLIGHT = 0;
    protfdtfd stbtid finbl int DECREASE_HIGHLIGHT = 1;
    protfdtfd stbtid finbl int INCREASE_HIGHLIGHT = 2;

    protfdtfd SdrollListfnfr sdrollListfnfr;
    protfdtfd PropfrtyCibngfListfnfr propfrtyCibngfListfnfr;
    protfdtfd Timfr sdrollTimfr;

    privbtf finbl stbtid int sdrollSpffdTirottlf = 60; // dflby in milli sfdonds

    /** Truf indidbtfs b middlf dlidk will bbsolutfly position tif
     * sdrollbbr. */
    privbtf boolfbn supportsAbsolutfPositioning;

    /**
     * Hint bs to wibt widti (wifn vfrtidbl) or ifigit (wifn iorizontbl)
     * siould bf.
     *
     * @sindf 1.7
     */
    protfdtfd int sdrollBbrWidti;

    privbtf Hbndlfr ibndlfr;

    privbtf boolfbn tiumbAdtivf;

    /**
     * Dftfrminf wiftifr sdrollbbr lbyout siould usf dbdifd vbluf or bdjustfd
     * vbluf rfturnfd by sdrollbbr's <dodf>gftVbluf</dodf>.
     */
    privbtf boolfbn usfCbdifdVbluf = fblsf;
    /**
     * Tif sdrollbbr vbluf is dbdifd to sbvf rfbl vbluf if tif vifw is bdjustfd.
     */
    privbtf int sdrollBbrVbluf;

    /**
     * Distbndf bftwffn tif indrfmfnt button bnd tif trbdk. Tiis mby bf b nfgbtivf
     * numbfr. If nfgbtivf, tifn bn ovfrlbp bftwffn tif button bnd trbdk will oddur,
     * wiidi is usfful for sibpfd buttons.
     *
     * @sindf 1.7
     */
    protfdtfd int indrGbp;

    /**
     * Distbndf bftwffn tif dfdrfmfnt button bnd tif trbdk. Tiis mby bf b nfgbtivf
     * numbfr. If nfgbtivf, tifn bn ovfrlbp bftwffn tif button bnd trbdk will oddur,
     * wiidi is usfful for sibpfd buttons.
     *
     * @sindf 1.7
     */
    protfdtfd int dfdrGbp;

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        mbp.put(nfw Adtions(Adtions.POSITIVE_UNIT_INCREMENT));
        mbp.put(nfw Adtions(Adtions.POSITIVE_BLOCK_INCREMENT));
        mbp.put(nfw Adtions(Adtions.NEGATIVE_UNIT_INCREMENT));
        mbp.put(nfw Adtions(Adtions.NEGATIVE_BLOCK_INCREMENT));
        mbp.put(nfw Adtions(Adtions.MIN_SCROLL));
        mbp.put(nfw Adtions(Adtions.MAX_SCROLL));
    }


    publid stbtid ComponfntUI drfbtfUI(JComponfnt d)    {
        rfturn nfw BbsidSdrollBbrUI();
    }


    protfdtfd void donfigurfSdrollBbrColors()
    {
        LookAndFffl.instbllColors(sdrollbbr, "SdrollBbr.bbdkground",
                                  "SdrollBbr.forfground");
        tiumbHigiligitColor = UIMbnbgfr.gftColor("SdrollBbr.tiumbHigiligit");
        tiumbLigitSibdowColor = UIMbnbgfr.gftColor("SdrollBbr.tiumbSibdow");
        tiumbDbrkSibdowColor = UIMbnbgfr.gftColor("SdrollBbr.tiumbDbrkSibdow");
        tiumbColor = UIMbnbgfr.gftColor("SdrollBbr.tiumb");
        trbdkColor = UIMbnbgfr.gftColor("SdrollBbr.trbdk");
        trbdkHigiligitColor = UIMbnbgfr.gftColor("SdrollBbr.trbdkHigiligit");
    }


    publid void instbllUI(JComponfnt d)   {
        sdrollbbr = (JSdrollBbr)d;
        tiumbRfdt = nfw Rfdtbnglf(0, 0, 0, 0);
        trbdkRfdt = nfw Rfdtbnglf(0, 0, 0, 0);
        instbllDffbults();
        instbllComponfnts();
        instbllListfnfrs();
        instbllKfybobrdAdtions();
    }

    publid void uninstbllUI(JComponfnt d) {
        sdrollbbr = (JSdrollBbr)d;
        uninstbllListfnfrs();
        uninstbllDffbults();
        uninstbllComponfnts();
        uninstbllKfybobrdAdtions();
        tiumbRfdt = null;
        sdrollbbr = null;
        indrButton = null;
        dfdrButton = null;
    }


    protfdtfd void instbllDffbults()
    {
        sdrollBbrWidti = UIMbnbgfr.gftInt("SdrollBbr.widti");
        if (sdrollBbrWidti <= 0) {
            sdrollBbrWidti = 16;
        }
        minimumTiumbSizf = (Dimfnsion)UIMbnbgfr.gft("SdrollBbr.minimumTiumbSizf");
        mbximumTiumbSizf = (Dimfnsion)UIMbnbgfr.gft("SdrollBbr.mbximumTiumbSizf");

        Boolfbn bbsB = (Boolfbn)UIMbnbgfr.gft("SdrollBbr.bllowsAbsolutfPositioning");
        supportsAbsolutfPositioning = (bbsB != null) ? bbsB.boolfbnVbluf() :
                                      fblsf;

        trbdkHigiligit = NO_HIGHLIGHT;
        if (sdrollbbr.gftLbyout() == null ||
                     (sdrollbbr.gftLbyout() instbndfof UIRfsourdf)) {
            sdrollbbr.sftLbyout(tiis);
        }
        donfigurfSdrollBbrColors();
        LookAndFffl.instbllBordfr(sdrollbbr, "SdrollBbr.bordfr");
        LookAndFffl.instbllPropfrty(sdrollbbr, "opbquf", Boolfbn.TRUE);

        sdrollBbrVbluf = sdrollbbr.gftVbluf();

        indrGbp = UIMbnbgfr.gftInt("SdrollBbr.indrfmfntButtonGbp");
        dfdrGbp = UIMbnbgfr.gftInt("SdrollBbr.dfdrfmfntButtonGbp");

        // TODO tiis dbn bf rfmovfd wifn indrGbp/dfdrGbp bfdomf protfdtfd
        // ibndlf sdbling for sizfVbrifnts for spfdibl dbsf domponfnts. Tif
        // kfy "JComponfnt.sizfVbribnt" sdblfs for lbrgf/smbll/mini
        // domponfnts brf bbsfd on Applfs LAF
        String sdblfKfy = (String)sdrollbbr.gftClifntPropfrty(
                "JComponfnt.sizfVbribnt");
        if (sdblfKfy != null){
            if ("lbrgf".fqubls(sdblfKfy)){
                sdrollBbrWidti *= 1.15;
                indrGbp *= 1.15;
                dfdrGbp *= 1.15;
            } flsf if ("smbll".fqubls(sdblfKfy)){
                sdrollBbrWidti *= 0.857;
                indrGbp *= 0.857;
                dfdrGbp *= 0.714;
            } flsf if ("mini".fqubls(sdblfKfy)){
                sdrollBbrWidti *= 0.714;
                indrGbp *= 0.714;
                dfdrGbp *= 0.714;
            }
        }
    }


    protfdtfd void instbllComponfnts(){
        switdi (sdrollbbr.gftOrifntbtion()) {
        dbsf JSdrollBbr.VERTICAL:
            indrButton = drfbtfIndrfbsfButton(SOUTH);
            dfdrButton = drfbtfDfdrfbsfButton(NORTH);
            brfbk;

        dbsf JSdrollBbr.HORIZONTAL:
            if (sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
                indrButton = drfbtfIndrfbsfButton(EAST);
                dfdrButton = drfbtfDfdrfbsfButton(WEST);
            } flsf {
                indrButton = drfbtfIndrfbsfButton(WEST);
                dfdrButton = drfbtfDfdrfbsfButton(EAST);
            }
            brfbk;
        }
        sdrollbbr.bdd(indrButton);
        sdrollbbr.bdd(dfdrButton);
        // Fordf tif diildrfn's fnbblfd stbtf to bf updbtfd.
        sdrollbbr.sftEnbblfd(sdrollbbr.isEnbblfd());
    }

    protfdtfd void uninstbllComponfnts(){
        sdrollbbr.rfmovf(indrButton);
        sdrollbbr.rfmovf(dfdrButton);
    }


    protfdtfd void instbllListfnfrs(){
        trbdkListfnfr = drfbtfTrbdkListfnfr();
        buttonListfnfr = drfbtfArrowButtonListfnfr();
        modflListfnfr = drfbtfModflListfnfr();
        propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr();

        sdrollbbr.bddMousfListfnfr(trbdkListfnfr);
        sdrollbbr.bddMousfMotionListfnfr(trbdkListfnfr);
        sdrollbbr.gftModfl().bddCibngfListfnfr(modflListfnfr);
        sdrollbbr.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        sdrollbbr.bddFodusListfnfr(gftHbndlfr());

        if (indrButton != null) {
            indrButton.bddMousfListfnfr(buttonListfnfr);
        }
        if (dfdrButton != null) {
            dfdrButton.bddMousfListfnfr(buttonListfnfr);
        }

        sdrollListfnfr = drfbtfSdrollListfnfr();
        sdrollTimfr = nfw Timfr(sdrollSpffdTirottlf, sdrollListfnfr);
        sdrollTimfr.sftInitiblDflby(300);  // dffbult InitiblDflby?
    }


    protfdtfd void instbllKfybobrdAdtions(){
        LbzyAdtionMbp.instbllLbzyAdtionMbp(sdrollbbr, BbsidSdrollBbrUI.dlbss,
                                           "SdrollBbr.bdtionMbp");

        InputMbp inputMbp = gftInputMbp(JComponfnt.WHEN_FOCUSED);
        SwingUtilitifs.rfplbdfUIInputMbp(sdrollbbr, JComponfnt.WHEN_FOCUSED,
                                         inputMbp);
        inputMbp = gftInputMbp(JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilitifs.rfplbdfUIInputMbp(sdrollbbr,
                   JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMbp);
    }

    protfdtfd void uninstbllKfybobrdAdtions(){
        SwingUtilitifs.rfplbdfUIInputMbp(sdrollbbr, JComponfnt.WHEN_FOCUSED,
                                         null);
        SwingUtilitifs.rfplbdfUIAdtionMbp(sdrollbbr, null);
    }

    privbtf InputMbp gftInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_FOCUSED) {
            InputMbp kfyMbp = (InputMbp)DffbultLookup.gft(
                        sdrollbbr, tiis, "SdrollBbr.fodusInputMbp");
            InputMbp rtlKfyMbp;

            if (sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit() ||
                ((rtlKfyMbp = (InputMbp)DffbultLookup.gft(sdrollbbr, tiis, "SdrollBbr.fodusInputMbp.RigitToLfft")) == null)) {
                rfturn kfyMbp;
            } flsf {
                rtlKfyMbp.sftPbrfnt(kfyMbp);
                rfturn rtlKfyMbp;
            }
        }
        flsf if (dondition == JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            InputMbp kfyMbp = (InputMbp)DffbultLookup.gft(
                        sdrollbbr, tiis, "SdrollBbr.bndfstorInputMbp");
            InputMbp rtlKfyMbp;

            if (sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit() ||
                ((rtlKfyMbp = (InputMbp)DffbultLookup.gft(sdrollbbr, tiis, "SdrollBbr.bndfstorInputMbp.RigitToLfft")) == null)) {
                rfturn kfyMbp;
            } flsf {
                rtlKfyMbp.sftPbrfnt(kfyMbp);
                rfturn rtlKfyMbp;
            }
        }
        rfturn null;
    }


    protfdtfd void uninstbllListfnfrs() {
        sdrollTimfr.stop();
        sdrollTimfr = null;

        if (dfdrButton != null){
            dfdrButton.rfmovfMousfListfnfr(buttonListfnfr);
        }
        if (indrButton != null){
            indrButton.rfmovfMousfListfnfr(buttonListfnfr);
        }

        sdrollbbr.gftModfl().rfmovfCibngfListfnfr(modflListfnfr);
        sdrollbbr.rfmovfMousfListfnfr(trbdkListfnfr);
        sdrollbbr.rfmovfMousfMotionListfnfr(trbdkListfnfr);
        sdrollbbr.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        sdrollbbr.rfmovfFodusListfnfr(gftHbndlfr());
        ibndlfr = null;
    }


    protfdtfd void uninstbllDffbults(){
        LookAndFffl.uninstbllBordfr(sdrollbbr);
        if (sdrollbbr.gftLbyout() == tiis) {
            sdrollbbr.sftLbyout(null);
        }
    }


    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    protfdtfd TrbdkListfnfr drfbtfTrbdkListfnfr(){
        rfturn nfw TrbdkListfnfr();
    }

    protfdtfd ArrowButtonListfnfr drfbtfArrowButtonListfnfr(){
        rfturn nfw ArrowButtonListfnfr();
    }

    protfdtfd ModflListfnfr drfbtfModflListfnfr(){
        rfturn nfw ModflListfnfr();
    }

    protfdtfd SdrollListfnfr drfbtfSdrollListfnfr(){
        rfturn nfw SdrollListfnfr();
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn gftHbndlfr();
    }

    privbtf void updbtfTiumbStbtf(int x, int y) {
        Rfdtbnglf rfdt = gftTiumbBounds();

        sftTiumbRollovfr(rfdt.dontbins(x, y));
    }

    /**
     * Sfts wiftifr or not tif mousf is durrfntly ovfr tif tiumb.
     *
     * @pbrbm bdtivf Truf indidbtfs tif tiumb is durrfntly bdtivf.
     * @sindf 1.5
     */
    protfdtfd void sftTiumbRollovfr(boolfbn bdtivf) {
        if (tiumbAdtivf != bdtivf) {
            tiumbAdtivf = bdtivf;
            sdrollbbr.rfpbint(gftTiumbBounds());
        }
    }

    /**
     * Rfturns truf if tif mousf is durrfntly ovfr tif tiumb.
     *
     * @rfturn truf if tif tiumb is durrfntly bdtivf
     * @sindf 1.5
     */
    publid boolfbn isTiumbRollovfr() {
        rfturn tiumbAdtivf;
    }

    publid void pbint(Grbpiids g, JComponfnt d) {
        pbintTrbdk(g, d, gftTrbdkBounds());
        Rfdtbnglf tiumbBounds = gftTiumbBounds();
        if (tiumbBounds.intfrsfdts(g.gftClipBounds())) {
            pbintTiumb(g, d, tiumbBounds);
        }
    }


    /**
     * A vfrtidbl sdrollbbr's prfffrrfd widti is tif mbximum of
     * prfffrrfd widtis of tif (non <dodf>null</dodf>)
     * indrfmfnt/dfdrfmfnt buttons,
     * bnd tif minimum widti of tif tiumb. Tif prfffrrfd ifigit is tif
     * sum of tif prfffrrfd ifigits of tif sbmf pbrts.  Tif bbsis for
     * tif prfffrrfd sizf of b iorizontbl sdrollbbr is similbr.
     * <p>
     * Tif <dodf>prfffrrfdSizf</dodf> is only domputfd ondf, subsfqufnt
     * dblls to tiis mftiod just rfturn b dbdifd sizf.
     *
     * @pbrbm d tif <dodf>JSdrollBbr</dodf> tibt's dflfgbting tiis mftiod to us
     * @rfturn tif prfffrrfd sizf of b Bbsid JSdrollBbr
     * @sff #gftMbximumSizf
     * @sff #gftMinimumSizf
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        rfturn (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL)
            ? nfw Dimfnsion(sdrollBbrWidti, 48)
            : nfw Dimfnsion(48, sdrollBbrWidti);
    }


    /**
     * @pbrbm d Tif JSdrollBbr tibt's dflfgbting tiis mftiod to us.
     * @rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
     * @sff #gftMinimumSizf
     * @sff #gftPrfffrrfdSizf
     */
    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }

    protfdtfd JButton drfbtfDfdrfbsfButton(int orifntbtion)  {
        rfturn nfw BbsidArrowButton(orifntbtion,
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumb"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbDbrkSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbHigiligit"));
    }

    protfdtfd JButton drfbtfIndrfbsfButton(int orifntbtion)  {
        rfturn nfw BbsidArrowButton(orifntbtion,
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumb"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbDbrkSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbHigiligit"));
    }


    protfdtfd void pbintDfdrfbsfHigiligit(Grbpiids g)
    {
        Insfts insfts = sdrollbbr.gftInsfts();
        Rfdtbnglf tiumbR = gftTiumbBounds();
        g.sftColor(trbdkHigiligitColor);

        if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
            //pbint tif distbndf bftwffn tif stbrt of tif trbdk bnd top of tif tiumb
            int x = insfts.lfft;
            int y = trbdkRfdt.y;
            int w = sdrollbbr.gftWidti() - (insfts.lfft + insfts.rigit);
            int i = tiumbR.y - y;
            g.fillRfdt(x, y, w, i);
        } flsf {
            //if lfft-to-rigit, fill tif brfb bftwffn tif stbrt of tif trbdk bnd
            //tif lfft fdgf of tif tiumb. If rigit-to-lfft, fill tif brfb bftwffn
            //tif fnd of tif tiumb bnd fnd of tif trbdk.
            int x, w;
            if (sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
               x = trbdkRfdt.x;
                w = tiumbR.x - x;
            } flsf {
                x = tiumbR.x + tiumbR.widti;
                w = trbdkRfdt.x + trbdkRfdt.widti - x;
            }
            int y = insfts.top;
            int i = sdrollbbr.gftHfigit() - (insfts.top + insfts.bottom);
            g.fillRfdt(x, y, w, i);
        }
    }


    protfdtfd void pbintIndrfbsfHigiligit(Grbpiids g)
    {
        Insfts insfts = sdrollbbr.gftInsfts();
        Rfdtbnglf tiumbR = gftTiumbBounds();
        g.sftColor(trbdkHigiligitColor);

        if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
            //fill tif brfb bftwffn tif bottom of tif tiumb bnd tif fnd of tif trbdk.
            int x = insfts.lfft;
            int y = tiumbR.y + tiumbR.ifigit;
            int w = sdrollbbr.gftWidti() - (insfts.lfft + insfts.rigit);
            int i = trbdkRfdt.y + trbdkRfdt.ifigit - y;
            g.fillRfdt(x, y, w, i);
        }
        flsf {
            //if lfft-to-rigit, fill tif brfb bftwffn tif rigit of tif tiumb bnd tif
            //fnd of tif trbdk. If rigit-to-lfft, tifn fill tif brfb to tif lfft of
            //tif tiumb bnd tif stbrt of tif trbdk.
            int x, w;
            if (sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
                x = tiumbR.x + tiumbR.widti;
                w = trbdkRfdt.x + trbdkRfdt.widti - x;
            } flsf {
                x = trbdkRfdt.x;
                w = tiumbR.x - x;
            }
            int y = insfts.top;
            int i = sdrollbbr.gftHfigit() - (insfts.top + insfts.bottom);
            g.fillRfdt(x, y, w, i);
        }
    }


    protfdtfd void pbintTrbdk(Grbpiids g, JComponfnt d, Rfdtbnglf trbdkBounds)
    {
        g.sftColor(trbdkColor);
        g.fillRfdt(trbdkBounds.x, trbdkBounds.y, trbdkBounds.widti, trbdkBounds.ifigit);

        if(trbdkHigiligit == DECREASE_HIGHLIGHT)        {
            pbintDfdrfbsfHigiligit(g);
        }
        flsf if(trbdkHigiligit == INCREASE_HIGHLIGHT)           {
            pbintIndrfbsfHigiligit(g);
        }
    }


    protfdtfd void pbintTiumb(Grbpiids g, JComponfnt d, Rfdtbnglf tiumbBounds)
    {
        if(tiumbBounds.isEmpty() || !sdrollbbr.isEnbblfd())     {
            rfturn;
        }

        int w = tiumbBounds.widti;
        int i = tiumbBounds.ifigit;

        g.trbnslbtf(tiumbBounds.x, tiumbBounds.y);

        g.sftColor(tiumbDbrkSibdowColor);
        drbwRfdt(g, 0, 0, w - 1, i - 1);
        g.sftColor(tiumbColor);
        g.fillRfdt(0, 0, w - 1, i - 1);

        g.sftColor(tiumbHigiligitColor);
        drbwVLinf(g, 1, 1, i - 2);
        drbwHLinf(g, 2, w - 3, 1);

        g.sftColor(tiumbLigitSibdowColor);
        drbwHLinf(g, 2, w - 2, i - 2);
        drbwVLinf(g, w - 2, 1, i - 3);

        g.trbnslbtf(-tiumbBounds.x, -tiumbBounds.y);
    }


    /**
     * Rfturns tif smbllfst bddfptbblf sizf for tif tiumb.  If tif sdrollbbr
     * bfdomfs so smbll tibt tiis sizf isn't bvbilbblf, tif tiumb will bf
     * iiddfn.
     * <p>
     * <b>Wbrning </b>: tif vbluf rfturnfd by tiis mftiod siould not bf
     * bf modififd, it's b sibrfd stbtid donstbnt.
     *
     * @rfturn Tif smbllfst bddfptbblf sizf for tif tiumb.
     * @sff #gftMbximumTiumbSizf
     */
    protfdtfd Dimfnsion gftMinimumTiumbSizf() {
        rfturn minimumTiumbSizf;
    }

    /**
     * Rfturns tif lbrgfst bddfptbblf sizf for tif tiumb.  To drfbtf b fixfd
     * sizf tiumb onf mbkf tiis mftiod bnd <dodf>gftMinimumTiumbSizf</dodf>
     * rfturn tif sbmf vbluf.
     * <p>
     * <b>Wbrning </b>: tif vbluf rfturnfd by tiis mftiod siould not bf
     * bf modififd, it's b sibrfd stbtid donstbnt.
     *
     * @rfturn Tif lbrgfst bddfptbblf sizf for tif tiumb.
     * @sff #gftMinimumTiumbSizf
     */
    protfdtfd Dimfnsion gftMbximumTiumbSizf()   {
        rfturn mbximumTiumbSizf;
    }


    /*
     * LbyoutMbnbgfr Implfmfntbtion
     */

    publid void bddLbyoutComponfnt(String nbmf, Componfnt diild) {}
    publid void rfmovfLbyoutComponfnt(Componfnt diild) {}

    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr sdrollbbrContbinfr)  {
        rfturn gftPrfffrrfdSizf((JComponfnt)sdrollbbrContbinfr);
    }

    publid Dimfnsion minimumLbyoutSizf(Contbinfr sdrollbbrContbinfr) {
        rfturn gftMinimumSizf((JComponfnt)sdrollbbrContbinfr);
    }

    privbtf int gftVbluf(JSdrollBbr sb) {
        rfturn (usfCbdifdVbluf) ? sdrollBbrVbluf : sb.gftVbluf();
    }

    protfdtfd void lbyoutVSdrollbbr(JSdrollBbr sb)
    {
        Dimfnsion sbSizf = sb.gftSizf();
        Insfts sbInsfts = sb.gftInsfts();

        /*
         * Widti bnd lfft fdgf of tif buttons bnd tiumb.
         */
        int itfmW = sbSizf.widti - (sbInsfts.lfft + sbInsfts.rigit);
        int itfmX = sbInsfts.lfft;

        /* Nominbl lodbtions of tif buttons, bssuming tifir prfffrrfd
         * sizf will fit.
         */
        boolfbn squbrfButtons = DffbultLookup.gftBoolfbn(
            sdrollbbr, tiis, "SdrollBbr.squbrfButtons", fblsf);
        int dfdrButtonH = squbrfButtons ? itfmW :
                          dfdrButton.gftPrfffrrfdSizf().ifigit;
        int dfdrButtonY = sbInsfts.top;

        int indrButtonH = squbrfButtons ? itfmW :
                          indrButton.gftPrfffrrfdSizf().ifigit;
        int indrButtonY = sbSizf.ifigit - (sbInsfts.bottom + indrButtonH);

        /* Tif tiumb must fit witiin tif ifigit lfft ovfr bftfr wf
         * subtrbdt tif prfffrrfdSizf of tif buttons bnd tif insfts
         * bnd tif gbps
         */
        int sbInsftsH = sbInsfts.top + sbInsfts.bottom;
        int sbButtonsH = dfdrButtonH + indrButtonH;
        int gbps = dfdrGbp + indrGbp;
        flobt trbdkH = sbSizf.ifigit - (sbInsftsH + sbButtonsH) - gbps;

        /* Computf tif ifigit bnd origin of tif tiumb.   Tif dbsf
         * wifrf tif tiumb is bt tif bottom fdgf is ibndlfd spfdiblly
         * to bvoid numfridbl problfms in domputing tiumbY.  Enfordf
         * tif tiumbs min/mbx dimfnsions.  If tif tiumb dofsn't
         * fit in tif trbdk (trbdkH) wf'll iidf it lbtfr.
         */
        flobt min = sb.gftMinimum();
        flobt fxtfnt = sb.gftVisiblfAmount();
        flobt rbngf = sb.gftMbximum() - min;
        flobt vbluf = gftVbluf(sb);

        int tiumbH = (rbngf <= 0)
            ? gftMbximumTiumbSizf().ifigit : (int)(trbdkH * (fxtfnt / rbngf));
        tiumbH = Mbti.mbx(tiumbH, gftMinimumTiumbSizf().ifigit);
        tiumbH = Mbti.min(tiumbH, gftMbximumTiumbSizf().ifigit);

        int tiumbY = indrButtonY - indrGbp - tiumbH;
        if (vbluf < (sb.gftMbximum() - sb.gftVisiblfAmount())) {
            flobt tiumbRbngf = trbdkH - tiumbH;
            tiumbY = (int)(0.5f + (tiumbRbngf * ((vbluf - min) / (rbngf - fxtfnt))));
            tiumbY +=  dfdrButtonY + dfdrButtonH + dfdrGbp;
        }

        /* If tif buttons don't fit, bllodbtf iblf of tif bvbilbblf
         * spbdf to fbdi bnd movf tif lowfr onf (indrButton) down.
         */
        int sbAvbilButtonH = (sbSizf.ifigit - sbInsftsH);
        if (sbAvbilButtonH < sbButtonsH) {
            indrButtonH = dfdrButtonH = sbAvbilButtonH / 2;
            indrButtonY = sbSizf.ifigit - (sbInsfts.bottom + indrButtonH);
        }
        dfdrButton.sftBounds(itfmX, dfdrButtonY, itfmW, dfdrButtonH);
        indrButton.sftBounds(itfmX, indrButtonY, itfmW, indrButtonH);

        /* Updbtf tif trbdkRfdt fifld.
         */
        int itrbdkY = dfdrButtonY + dfdrButtonH + dfdrGbp;
        int itrbdkH = indrButtonY - indrGbp - itrbdkY;
        trbdkRfdt.sftBounds(itfmX, itrbdkY, itfmW, itrbdkH);

        /* If tif tiumb isn't going to fit, zfro it's bounds.  Otifrwisf
         * mbkf surf it fits bftwffn tif buttons.  Notf tibt sftting tif
         * tiumbs bounds will dbusf b rfpbint.
         */
        if(tiumbH >= (int)trbdkH)       {
            if (UIMbnbgfr.gftBoolfbn("SdrollBbr.blwbysSiowTiumb")) {
                // Tiis is usfd primbrily for GTK L&F, wiidi fxpbnds tif
                // tiumb to fit tif trbdk wifn it would otifrwisf bf iiddfn.
                sftTiumbBounds(itfmX, itrbdkY, itfmW, itrbdkH);
            } flsf {
                // Otifr L&F's simply iidf tif tiumb in tiis dbsf.
                sftTiumbBounds(0, 0, 0, 0);
            }
        }
        flsf {
            if ((tiumbY + tiumbH) > indrButtonY - indrGbp) {
                tiumbY = indrButtonY - indrGbp - tiumbH;
            }
            if (tiumbY  < (dfdrButtonY + dfdrButtonH + dfdrGbp)) {
                tiumbY = dfdrButtonY + dfdrButtonH + dfdrGbp + 1;
            }
            sftTiumbBounds(itfmX, tiumbY, itfmW, tiumbH);
        }
    }


    protfdtfd void lbyoutHSdrollbbr(JSdrollBbr sb)
    {
        Dimfnsion sbSizf = sb.gftSizf();
        Insfts sbInsfts = sb.gftInsfts();

        /* Hfigit bnd top fdgf of tif buttons bnd tiumb.
         */
        int itfmH = sbSizf.ifigit - (sbInsfts.top + sbInsfts.bottom);
        int itfmY = sbInsfts.top;

        boolfbn ltr = sb.gftComponfntOrifntbtion().isLfftToRigit();

        /* Nominbl lodbtions of tif buttons, bssuming tifir prfffrrfd
         * sizf will fit.
         */
        boolfbn squbrfButtons = DffbultLookup.gftBoolfbn(
            sdrollbbr, tiis, "SdrollBbr.squbrfButtons", fblsf);
        int lfftButtonW = squbrfButtons ? itfmH :
                          dfdrButton.gftPrfffrrfdSizf().widti;
        int rigitButtonW = squbrfButtons ? itfmH :
                          indrButton.gftPrfffrrfdSizf().widti;
        if (!ltr) {
            int tfmp = lfftButtonW;
            lfftButtonW = rigitButtonW;
            rigitButtonW = tfmp;
        }
        int lfftButtonX = sbInsfts.lfft;
        int rigitButtonX = sbSizf.widti - (sbInsfts.rigit + rigitButtonW);
        int lfftGbp = ltr ? dfdrGbp : indrGbp;
        int rigitGbp = ltr ? indrGbp : dfdrGbp;

        /* Tif tiumb must fit witiin tif widti lfft ovfr bftfr wf
         * subtrbdt tif prfffrrfdSizf of tif buttons bnd tif insfts
         * bnd tif gbps
         */
        int sbInsftsW = sbInsfts.lfft + sbInsfts.rigit;
        int sbButtonsW = lfftButtonW + rigitButtonW;
        flobt trbdkW = sbSizf.widti - (sbInsftsW + sbButtonsW) - (lfftGbp + rigitGbp);

        /* Computf tif widti bnd origin of tif tiumb.  Enfordf
         * tif tiumbs min/mbx dimfnsions.  Tif dbsf wifrf tif tiumb
         * is bt tif rigit fdgf is ibndlfd spfdiblly to bvoid numfridbl
         * problfms in domputing tiumbX.  If tif tiumb dofsn't
         * fit in tif trbdk (trbdkH) wf'll iidf it lbtfr.
         */
        flobt min = sb.gftMinimum();
        flobt mbx = sb.gftMbximum();
        flobt fxtfnt = sb.gftVisiblfAmount();
        flobt rbngf = mbx - min;
        flobt vbluf = gftVbluf(sb);

        int tiumbW = (rbngf <= 0)
            ? gftMbximumTiumbSizf().widti : (int)(trbdkW * (fxtfnt / rbngf));
        tiumbW = Mbti.mbx(tiumbW, gftMinimumTiumbSizf().widti);
        tiumbW = Mbti.min(tiumbW, gftMbximumTiumbSizf().widti);

        int tiumbX = ltr ? rigitButtonX - rigitGbp - tiumbW : lfftButtonX + lfftButtonW + lfftGbp;
        if (vbluf < (mbx - sb.gftVisiblfAmount())) {
            flobt tiumbRbngf = trbdkW - tiumbW;
            if( ltr ) {
                tiumbX = (int)(0.5f + (tiumbRbngf * ((vbluf - min) / (rbngf - fxtfnt))));
            } flsf {
                tiumbX = (int)(0.5f + (tiumbRbngf * ((mbx - fxtfnt - vbluf) / (rbngf - fxtfnt))));
            }
            tiumbX += lfftButtonX + lfftButtonW + lfftGbp;
        }

        /* If tif buttons don't fit, bllodbtf iblf of tif bvbilbblf
         * spbdf to fbdi bnd movf tif rigit onf ovfr.
         */
        int sbAvbilButtonW = (sbSizf.widti - sbInsftsW);
        if (sbAvbilButtonW < sbButtonsW) {
            rigitButtonW = lfftButtonW = sbAvbilButtonW / 2;
            rigitButtonX = sbSizf.widti - (sbInsfts.rigit + rigitButtonW + rigitGbp);
        }

        (ltr ? dfdrButton : indrButton).sftBounds(lfftButtonX, itfmY, lfftButtonW, itfmH);
        (ltr ? indrButton : dfdrButton).sftBounds(rigitButtonX, itfmY, rigitButtonW, itfmH);

        /* Updbtf tif trbdkRfdt fifld.
         */
        int itrbdkX = lfftButtonX + lfftButtonW + lfftGbp;
        int itrbdkW = rigitButtonX - rigitGbp - itrbdkX;
        trbdkRfdt.sftBounds(itrbdkX, itfmY, itrbdkW, itfmH);

        /* Mbkf surf tif tiumb fits bftwffn tif buttons.  Notf
         * tibt sftting tif tiumbs bounds dbusfs b rfpbint.
         */
        if (tiumbW >= (int)trbdkW) {
            if (UIMbnbgfr.gftBoolfbn("SdrollBbr.blwbysSiowTiumb")) {
                // Tiis is usfd primbrily for GTK L&F, wiidi fxpbnds tif
                // tiumb to fit tif trbdk wifn it would otifrwisf bf iiddfn.
                sftTiumbBounds(itrbdkX, itfmY, itrbdkW, itfmH);
            } flsf {
                // Otifr L&F's simply iidf tif tiumb in tiis dbsf.
                sftTiumbBounds(0, 0, 0, 0);
            }
        }
        flsf {
            if (tiumbX + tiumbW > rigitButtonX - rigitGbp) {
                tiumbX = rigitButtonX - rigitGbp - tiumbW;
            }
            if (tiumbX  < lfftButtonX + lfftButtonW + lfftGbp) {
                tiumbX = lfftButtonX + lfftButtonW + lfftGbp + 1;
            }
            sftTiumbBounds(tiumbX, itfmY, tiumbW, itfmH);
        }
    }

    publid void lbyoutContbinfr(Contbinfr sdrollbbrContbinfr)
    {
        /* If tif usfr is drbgging tif vbluf, wf'll bssumf tibt tif
         * sdrollbbrs lbyout is OK modulo tif tiumb wiidi is bfing
         * ibndlfd by tif drbgging dodf.
         */
        if (isDrbgging) {
            rfturn;
        }

        JSdrollBbr sdrollbbr = (JSdrollBbr)sdrollbbrContbinfr;
        switdi (sdrollbbr.gftOrifntbtion()) {
        dbsf JSdrollBbr.VERTICAL:
            lbyoutVSdrollbbr(sdrollbbr);
            brfbk;

        dbsf JSdrollBbr.HORIZONTAL:
            lbyoutHSdrollbbr(sdrollbbr);
            brfbk;
        }
    }


    /**
     * Sft tif bounds of tif tiumb bnd fordf b rfpbint tibt indludfs
     * tif old tiumbBounds bnd tif nfw onf.
     *
     * @sff #gftTiumbBounds
     */
    protfdtfd void sftTiumbBounds(int x, int y, int widti, int ifigit)
    {
        /* If tif tiumbs bounds ibvfn't dibngfd, wf'rf donf.
         */
        if ((tiumbRfdt.x == x) &&
            (tiumbRfdt.y == y) &&
            (tiumbRfdt.widti == widti) &&
            (tiumbRfdt.ifigit == ifigit)) {
            rfturn;
        }

        /* Updbtf tiumbRfdt, bnd rfpbint tif union of x,y,w,i bnd
         * tif old tiumbRfdt.
         */
        int minX = Mbti.min(x, tiumbRfdt.x);
        int minY = Mbti.min(y, tiumbRfdt.y);
        int mbxX = Mbti.mbx(x + widti, tiumbRfdt.x + tiumbRfdt.widti);
        int mbxY = Mbti.mbx(y + ifigit, tiumbRfdt.y + tiumbRfdt.ifigit);

        tiumbRfdt.sftBounds(x, y, widti, ifigit);
        sdrollbbr.rfpbint(minX, minY, mbxX - minX, mbxY - minY);

        // Ondf tifrf is API to dftfrminf tif mousf lodbtion tiis will nffd
        // to bf dibngfd.
        sftTiumbRollovfr(fblsf);
    }


    /**
     * Rfturn tif durrfnt sizf/lodbtion of tif tiumb.
     * <p>
     * <b>Wbrning </b>: tif vbluf rfturnfd by tiis mftiod siould not bf
     * bf modififd, it's b rfffrfndf to tif bdtubl rfdtbnglf, not b dopy.
     *
     * @rfturn Tif durrfnt sizf/lodbtion of tif tiumb.
     * @sff #sftTiumbBounds
     */
    protfdtfd Rfdtbnglf gftTiumbBounds() {
        rfturn tiumbRfdt;
    }


    /**
     * Rfturns tif durrfnt bounds of tif trbdk, i.f. tif spbdf in bftwffn
     * tif indrfmfnt bnd dfdrfmfnt buttons, lfss tif insfts.  Tif vbluf
     * rfturnfd by tiis mftiod is updbtfd fbdi timf tif sdrollbbr is
     * lbid out (vblidbtfd).
     * <p>
     * <b>Wbrning </b>: tif vbluf rfturnfd by tiis mftiod siould not bf
     * bf modififd, it's b rfffrfndf to tif bdtubl rfdtbnglf, not b dopy.
     *
     * @rfturn tif durrfnt bounds of tif sdrollbbr trbdk
     * @sff #lbyoutContbinfr
     */
    protfdtfd Rfdtbnglf gftTrbdkBounds() {
        rfturn trbdkRfdt;
    }

    /*
     * Mftiod for sdrolling by b blodk indrfmfnt.
     * Addfd for mousf wiffl sdrolling support, RFE 4202656.
     */
    stbtid void sdrollByBlodk(JSdrollBbr sdrollbbr, int dirfdtion) {
        // Tiis mftiod is dbllfd from BbsidSdrollPbnfUI to implfmfnt wiffl
        // sdrolling, bnd blso from sdrollByBlodk().
            int oldVbluf = sdrollbbr.gftVbluf();
            int blodkIndrfmfnt = sdrollbbr.gftBlodkIndrfmfnt(dirfdtion);
            int dfltb = blodkIndrfmfnt * ((dirfdtion > 0) ? +1 : -1);
            int nfwVbluf = oldVbluf + dfltb;

            // Cifdk for ovfrflow.
            if (dfltb > 0 && nfwVbluf < oldVbluf) {
                nfwVbluf = sdrollbbr.gftMbximum();
            }
            flsf if (dfltb < 0 && nfwVbluf > oldVbluf) {
                nfwVbluf = sdrollbbr.gftMinimum();
            }

            sdrollbbr.sftVbluf(nfwVbluf);
    }

    protfdtfd void sdrollByBlodk(int dirfdtion)
    {
        sdrollByBlodk(sdrollbbr, dirfdtion);
            trbdkHigiligit = dirfdtion > 0 ? INCREASE_HIGHLIGHT : DECREASE_HIGHLIGHT;
            Rfdtbnglf dirtyRfdt = gftTrbdkBounds();
            sdrollbbr.rfpbint(dirtyRfdt.x, dirtyRfdt.y, dirtyRfdt.widti, dirtyRfdt.ifigit);
    }

    /*
     * Mftiod for sdrolling by b unit indrfmfnt.
     * Addfd for mousf wiffl sdrolling support, RFE 4202656.
     *
     * If limitByBlodk is sft to truf, tif sdrollbbr will sdroll bt lfbst 1
     * unit indrfmfnt, but will not sdroll fbrtifr tibn tif blodk indrfmfnt.
     * Sff BbsidSdrollPbnfUI.Hbndlfr.mousfWifflMovfd().
     */
    stbtid void sdrollByUnits(JSdrollBbr sdrollbbr, int dirfdtion,
                              int units, boolfbn limitToBlodk) {
        // Tiis mftiod is dbllfd from BbsidSdrollPbnfUI to implfmfnt wiffl
        // sdrolling, bs wfll bs from sdrollByUnit().
        int dfltb;
        int limit = -1;

        if (limitToBlodk) {
            if (dirfdtion < 0) {
                limit = sdrollbbr.gftVbluf() -
                                         sdrollbbr.gftBlodkIndrfmfnt(dirfdtion);
            }
            flsf {
                limit = sdrollbbr.gftVbluf() +
                                         sdrollbbr.gftBlodkIndrfmfnt(dirfdtion);
            }
        }

        for (int i=0; i<units; i++) {
            if (dirfdtion > 0) {
                dfltb = sdrollbbr.gftUnitIndrfmfnt(dirfdtion);
            }
            flsf {
                dfltb = -sdrollbbr.gftUnitIndrfmfnt(dirfdtion);
            }

            int oldVbluf = sdrollbbr.gftVbluf();
            int nfwVbluf = oldVbluf + dfltb;

            // Cifdk for ovfrflow.
            if (dfltb > 0 && nfwVbluf < oldVbluf) {
                nfwVbluf = sdrollbbr.gftMbximum();
            }
            flsf if (dfltb < 0 && nfwVbluf > oldVbluf) {
                nfwVbluf = sdrollbbr.gftMinimum();
            }
            if (oldVbluf == nfwVbluf) {
                brfbk;
            }

            if (limitToBlodk && i > 0) {
                bssfrt limit != -1;
                if ((dirfdtion < 0 && nfwVbluf < limit) ||
                    (dirfdtion > 0 && nfwVbluf > limit)) {
                    brfbk;
                }
            }
            sdrollbbr.sftVbluf(nfwVbluf);
        }
    }

    protfdtfd void sdrollByUnit(int dirfdtion)  {
        sdrollByUnits(sdrollbbr, dirfdtion, 1, fblsf);
    }

    /**
     * Indidbtfs wiftifr tif usfr dbn bbsolutfly position tif tiumb witi
     * b mousf gfsturf (usublly tif middlf mousf button).
     *
     * @rfturn truf if b mousf gfsturf dbn bbsolutfly position tif tiumb
     * @sindf 1.5
     */
    publid boolfbn gftSupportsAbsolutfPositioning() {
        rfturn supportsAbsolutfPositioning;
    }

    /**
     * A listfnfr to listfn for modfl dibngfs.
     *
     */
    protfdtfd dlbss ModflListfnfr implfmfnts CibngfListfnfr {
        publid void stbtfCibngfd(CibngfEvfnt f) {
            if (!usfCbdifdVbluf) {
                sdrollBbrVbluf = sdrollbbr.gftVbluf();
            }
            lbyoutContbinfr(sdrollbbr);
            usfCbdifdVbluf = fblsf;
        }
    }


    /**
     * Trbdk mousf drbgs.
     */
    protfdtfd dlbss TrbdkListfnfr
        fxtfnds MousfAdbptfr implfmfnts MousfMotionListfnfr
    {
        protfdtfd trbnsifnt int offsft;
        protfdtfd trbnsifnt int durrfntMousfX, durrfntMousfY;
        privbtf trbnsifnt int dirfdtion = +1;

        publid void mousfRflfbsfd(MousfEvfnt f)
        {
            if (isDrbgging) {
                updbtfTiumbStbtf(f.gftX(), f.gftY());
            }
            if (SwingUtilitifs.isRigitMousfButton(f) ||
                (!gftSupportsAbsolutfPositioning() &&
                 SwingUtilitifs.isMiddlfMousfButton(f)))
                rfturn;
            if(!sdrollbbr.isEnbblfd())
                rfturn;

            Rfdtbnglf r = gftTrbdkBounds();
            sdrollbbr.rfpbint(r.x, r.y, r.widti, r.ifigit);

            trbdkHigiligit = NO_HIGHLIGHT;
            isDrbgging = fblsf;
            offsft = 0;
            sdrollTimfr.stop();
            usfCbdifdVbluf = truf;
            sdrollbbr.sftVblufIsAdjusting(fblsf);
        }


        /**
         * If tif mousf is prfssfd bbovf tif "tiumb" domponfnt
         * tifn rfdudf tif sdrollbbrs vbluf by onf pbgf ("pbgf up"),
         * otifrwisf indrfbsf it by onf pbgf.  If tifrf is no
         * tiumb tifn pbgf up if tif mousf is in tif uppfr iblf
         * of tif trbdk.
         */
        publid void mousfPrfssfd(MousfEvfnt f)
        {
            if (SwingUtilitifs.isRigitMousfButton(f) ||
                (!gftSupportsAbsolutfPositioning() &&
                 SwingUtilitifs.isMiddlfMousfButton(f)))
                rfturn;
            if(!sdrollbbr.isEnbblfd())
                rfturn;

            if (!sdrollbbr.ibsFodus() && sdrollbbr.isRfqufstFodusEnbblfd()) {
                sdrollbbr.rfqufstFodus();
            }

            usfCbdifdVbluf = truf;
            sdrollbbr.sftVblufIsAdjusting(truf);

            durrfntMousfX = f.gftX();
            durrfntMousfY = f.gftY();

            // Clidkfd in tif Tiumb brfb?
            if(gftTiumbBounds().dontbins(durrfntMousfX, durrfntMousfY)) {
                switdi (sdrollbbr.gftOrifntbtion()) {
                dbsf JSdrollBbr.VERTICAL:
                    offsft = durrfntMousfY - gftTiumbBounds().y;
                    brfbk;
                dbsf JSdrollBbr.HORIZONTAL:
                    offsft = durrfntMousfX - gftTiumbBounds().x;
                    brfbk;
                }
                isDrbgging = truf;
                rfturn;
            }
            flsf if (gftSupportsAbsolutfPositioning() &&
                     SwingUtilitifs.isMiddlfMousfButton(f)) {
                switdi (sdrollbbr.gftOrifntbtion()) {
                dbsf JSdrollBbr.VERTICAL:
                    offsft = gftTiumbBounds().ifigit / 2;
                    brfbk;
                dbsf JSdrollBbr.HORIZONTAL:
                    offsft = gftTiumbBounds().widti / 2;
                    brfbk;
                }
                isDrbgging = truf;
                sftVblufFrom(f);
                rfturn;
            }
            isDrbgging = fblsf;

            Dimfnsion sbSizf = sdrollbbr.gftSizf();
            dirfdtion = +1;

            switdi (sdrollbbr.gftOrifntbtion()) {
            dbsf JSdrollBbr.VERTICAL:
                if (gftTiumbBounds().isEmpty()) {
                    int sdrollbbrCfntfr = sbSizf.ifigit / 2;
                    dirfdtion = (durrfntMousfY < sdrollbbrCfntfr) ? -1 : +1;
                } flsf {
                    int tiumbY = gftTiumbBounds().y;
                    dirfdtion = (durrfntMousfY < tiumbY) ? -1 : +1;
                }
                brfbk;
            dbsf JSdrollBbr.HORIZONTAL:
                if (gftTiumbBounds().isEmpty()) {
                    int sdrollbbrCfntfr = sbSizf.widti / 2;
                    dirfdtion = (durrfntMousfX < sdrollbbrCfntfr) ? -1 : +1;
                } flsf {
                    int tiumbX = gftTiumbBounds().x;
                    dirfdtion = (durrfntMousfX < tiumbX) ? -1 : +1;
                }
                if (!sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
                    dirfdtion = -dirfdtion;
                }
                brfbk;
            }
            sdrollByBlodk(dirfdtion);

            sdrollTimfr.stop();
            sdrollListfnfr.sftDirfdtion(dirfdtion);
            sdrollListfnfr.sftSdrollByBlodk(truf);
            stbrtSdrollTimfrIfNfdfssbry();
        }


        /**
         * Sft tif modfls vbluf to tif position of tif tiumb's top of Vfrtidbl
         * sdrollbbr, or tif lfft/rigit of Horizontbl sdrollbbr in
         * lfft-to-rigit/rigit-to-lfft sdrollbbr rflbtivf to tif origin of tif
         * trbdk.
         */
        publid void mousfDrbggfd(MousfEvfnt f) {
            if (SwingUtilitifs.isRigitMousfButton(f) ||
                (!gftSupportsAbsolutfPositioning() &&
                 SwingUtilitifs.isMiddlfMousfButton(f)))
                rfturn;
            if(!sdrollbbr.isEnbblfd() || gftTiumbBounds().isEmpty()) {
                rfturn;
            }
            if (isDrbgging) {
                sftVblufFrom(f);
            } flsf {
                durrfntMousfX = f.gftX();
                durrfntMousfY = f.gftY();
                updbtfTiumbStbtf(durrfntMousfX, durrfntMousfY);
                stbrtSdrollTimfrIfNfdfssbry();
            }
        }

        privbtf void sftVblufFrom(MousfEvfnt f) {
            boolfbn bdtivf = isTiumbRollovfr();
            BoundfdRbngfModfl modfl = sdrollbbr.gftModfl();
            Rfdtbnglf tiumbR = gftTiumbBounds();
            flobt trbdkLfngti;
            int tiumbMin, tiumbMbx, tiumbPos;

            if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
                tiumbMin = trbdkRfdt.y;
                tiumbMbx = trbdkRfdt.y + trbdkRfdt.ifigit - tiumbR.ifigit;
                tiumbPos = Mbti.min(tiumbMbx, Mbti.mbx(tiumbMin, (f.gftY() - offsft)));
                sftTiumbBounds(tiumbR.x, tiumbPos, tiumbR.widti, tiumbR.ifigit);
                trbdkLfngti = gftTrbdkBounds().ifigit;
            }
            flsf {
                tiumbMin = trbdkRfdt.x;
                tiumbMbx = trbdkRfdt.x + trbdkRfdt.widti - tiumbR.widti;
                tiumbPos = Mbti.min(tiumbMbx, Mbti.mbx(tiumbMin, (f.gftX() - offsft)));
                sftTiumbBounds(tiumbPos, tiumbR.y, tiumbR.widti, tiumbR.ifigit);
                trbdkLfngti = gftTrbdkBounds().widti;
            }

            /* Sft tif sdrollbbrs vbluf.  If tif tiumb ibs rfbdifd tif fnd of
             * tif sdrollbbr, tifn just sft tif vbluf to its mbximum.  Otifrwisf
             * domputf tif vbluf bs bddurbtfly bs possiblf.
             */
            if (tiumbPos == tiumbMbx) {
                if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL ||
                    sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
                    sdrollbbr.sftVbluf(modfl.gftMbximum() - modfl.gftExtfnt());
                } flsf {
                    sdrollbbr.sftVbluf(modfl.gftMinimum());
                }
            }
            flsf {
                flobt vblufMbx = modfl.gftMbximum() - modfl.gftExtfnt();
                flobt vblufRbngf = vblufMbx - modfl.gftMinimum();
                flobt tiumbVbluf = tiumbPos - tiumbMin;
                flobt tiumbRbngf = tiumbMbx - tiumbMin;
                int vbluf;
                if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL ||
                    sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
                    vbluf = (int)(0.5 + ((tiumbVbluf / tiumbRbngf) * vblufRbngf));
                } flsf {
                    vbluf = (int)(0.5 + (((tiumbMbx - tiumbPos) / tiumbRbngf) * vblufRbngf));
                }

                usfCbdifdVbluf = truf;
                sdrollBbrVbluf = vbluf + modfl.gftMinimum();
                sdrollbbr.sftVbluf(bdjustVblufIfNfdfssbry(sdrollBbrVbluf));
            }
            sftTiumbRollovfr(bdtivf);
        }

        privbtf int bdjustVblufIfNfdfssbry(int vbluf) {
            if (sdrollbbr.gftPbrfnt() instbndfof JSdrollPbnf) {
                JSdrollPbnf sdrollpbnf = (JSdrollPbnf)sdrollbbr.gftPbrfnt();
                JVifwport vifwport = sdrollpbnf.gftVifwport();
                Componfnt vifw = vifwport.gftVifw();
                if (vifw instbndfof JList) {
                    JList<?> list = (JList)vifw;
                    if (DffbultLookup.gftBoolfbn(list, list.gftUI(),
                                                 "List.lodkToPositionOnSdroll", fblsf)) {
                        int bdjustfdVbluf = vbluf;
                        int modf = list.gftLbyoutOrifntbtion();
                        int orifntbtion = sdrollbbr.gftOrifntbtion();
                        if (orifntbtion == JSdrollBbr.VERTICAL && modf == JList.VERTICAL) {
                            int indfx = list.lodbtionToIndfx(nfw Point(0, vbluf));
                            Rfdtbnglf rfdt = list.gftCfllBounds(indfx, indfx);
                            if (rfdt != null) {
                                bdjustfdVbluf = rfdt.y;
                            }
                        }
                        if (orifntbtion == JSdrollBbr.HORIZONTAL &&
                            (modf == JList.VERTICAL_WRAP || modf == JList.HORIZONTAL_WRAP)) {
                            if (sdrollpbnf.gftComponfntOrifntbtion().isLfftToRigit()) {
                                int indfx = list.lodbtionToIndfx(nfw Point(vbluf, 0));
                                Rfdtbnglf rfdt = list.gftCfllBounds(indfx, indfx);
                                if (rfdt != null) {
                                    bdjustfdVbluf = rfdt.x;
                                }
                            }
                            flsf {
                                Point lod = nfw Point(vbluf, 0);
                                int fxtfnt = vifwport.gftExtfntSizf().widti;
                                lod.x += fxtfnt - 1;
                                int indfx = list.lodbtionToIndfx(lod);
                                Rfdtbnglf rfdt = list.gftCfllBounds(indfx, indfx);
                                if (rfdt != null) {
                                    bdjustfdVbluf = rfdt.x + rfdt.widti - fxtfnt;
                                }
                            }
                        }
                        vbluf = bdjustfdVbluf;

                    }
                }
            }
            rfturn vbluf;
        }

        privbtf void stbrtSdrollTimfrIfNfdfssbry() {
            if (sdrollTimfr.isRunning()) {
                rfturn;
            }

            Rfdtbnglf tb = gftTiumbBounds();

            switdi (sdrollbbr.gftOrifntbtion()) {
            dbsf JSdrollBbr.VERTICAL:
                if (dirfdtion > 0) {
                    if (tb.y + tb.ifigit < trbdkListfnfr.durrfntMousfY) {
                        sdrollTimfr.stbrt();
                    }
                } flsf if (tb.y > trbdkListfnfr.durrfntMousfY) {
                    sdrollTimfr.stbrt();
                }
                brfbk;
            dbsf JSdrollBbr.HORIZONTAL:
                if ((dirfdtion > 0 && isMousfAftfrTiumb())
                        || (dirfdtion < 0 && isMousfBfforfTiumb())) {

                    sdrollTimfr.stbrt();
                }
                brfbk;
            }
        }

        publid void mousfMovfd(MousfEvfnt f) {
            if (!isDrbgging) {
                updbtfTiumbStbtf(f.gftX(), f.gftY());
            }
        }

        /**
         * Invokfd wifn tif mousf fxits tif sdrollbbr.
         *
         * @pbrbm f MousfEvfnt furtifr dfsdribing tif fvfnt
         * @sindf 1.5
         */
        publid void mousfExitfd(MousfEvfnt f) {
            if (!isDrbgging) {
                sftTiumbRollovfr(fblsf);
            }
        }
    }


    /**
     * Listfnfr for dursor kfys.
     */
    protfdtfd dlbss ArrowButtonListfnfr fxtfnds MousfAdbptfr
    {
        // Bfdbusf wf brf ibndling boti mousfPrfssfd bnd Adtions
        // wf nffd to mbkf surf wf don't firf undfr boti donditions.
        // (kfyfodus on sdrollbbrs dbusfs bdtion witiout mousfPrfss
        boolfbn ibndlfdEvfnt;

        publid void mousfPrfssfd(MousfEvfnt f)          {
            if(!sdrollbbr.isEnbblfd()) { rfturn; }
            // not bn unmodififd lfft mousf button
            //if(f.gftModififrs() != InputEvfnt.BUTTON1_MASK) {rfturn; }
            if( ! SwingUtilitifs.isLfftMousfButton(f)) { rfturn; }

            int dirfdtion = (f.gftSourdf() == indrButton) ? 1 : -1;

            sdrollByUnit(dirfdtion);
            sdrollTimfr.stop();
            sdrollListfnfr.sftDirfdtion(dirfdtion);
            sdrollListfnfr.sftSdrollByBlodk(fblsf);
            sdrollTimfr.stbrt();

            ibndlfdEvfnt = truf;
            if (!sdrollbbr.ibsFodus() && sdrollbbr.isRfqufstFodusEnbblfd()) {
                sdrollbbr.rfqufstFodus();
            }
        }

        publid void mousfRflfbsfd(MousfEvfnt f)         {
            sdrollTimfr.stop();
            ibndlfdEvfnt = fblsf;
            sdrollbbr.sftVblufIsAdjusting(fblsf);
        }
    }


    /**
     * Listfnfr for sdrolling fvfnts initibtfd in tif
     * <dodf>SdrollPbnf</dodf>.
     */
    protfdtfd dlbss SdrollListfnfr implfmfnts AdtionListfnfr
    {
        int dirfdtion = +1;
        boolfbn usfBlodkIndrfmfnt;

        publid SdrollListfnfr() {
            dirfdtion = +1;
            usfBlodkIndrfmfnt = fblsf;
        }

        publid SdrollListfnfr(int dir, boolfbn blodk)   {
            dirfdtion = dir;
            usfBlodkIndrfmfnt = blodk;
        }

        publid void sftDirfdtion(int dirfdtion) { tiis.dirfdtion = dirfdtion; }
        publid void sftSdrollByBlodk(boolfbn blodk) { tiis.usfBlodkIndrfmfnt = blodk; }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if(usfBlodkIndrfmfnt)       {
                sdrollByBlodk(dirfdtion);
                // Stop sdrolling if tif tiumb dbtdifs up witi tif mousf
                if(sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL)   {
                    if(dirfdtion > 0)   {
                        if(gftTiumbBounds().y + gftTiumbBounds().ifigit
                                >= trbdkListfnfr.durrfntMousfY)
                                    ((Timfr)f.gftSourdf()).stop();
                    } flsf if(gftTiumbBounds().y <= trbdkListfnfr.durrfntMousfY)        {
                        ((Timfr)f.gftSourdf()).stop();
                    }
                } flsf {
                    if ((dirfdtion > 0 && !isMousfAftfrTiumb())
                           || (dirfdtion < 0 && !isMousfBfforfTiumb())) {

                       ((Timfr)f.gftSourdf()).stop();
                    }
                }
            } flsf {
                sdrollByUnit(dirfdtion);
            }

            if(dirfdtion > 0
                && sdrollbbr.gftVbluf()+sdrollbbr.gftVisiblfAmount()
                        >= sdrollbbr.gftMbximum())
                ((Timfr)f.gftSourdf()).stop();
            flsf if(dirfdtion < 0
                && sdrollbbr.gftVbluf() <= sdrollbbr.gftMinimum())
                ((Timfr)f.gftSourdf()).stop();
        }
    }

    privbtf boolfbn isMousfLfftOfTiumb() {
        rfturn trbdkListfnfr.durrfntMousfX < gftTiumbBounds().x;
    }

    privbtf boolfbn isMousfRigitOfTiumb() {
        Rfdtbnglf tb = gftTiumbBounds();
        rfturn trbdkListfnfr.durrfntMousfX > tb.x + tb.widti;
    }

    privbtf boolfbn isMousfBfforfTiumb() {
        rfturn sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()
            ? isMousfLfftOfTiumb()
            : isMousfRigitOfTiumb();
    }

    privbtf boolfbn isMousfAftfrTiumb() {
        rfturn sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()
            ? isMousfRigitOfTiumb()
            : isMousfLfftOfTiumb();
    }

    privbtf void updbtfButtonDirfdtions() {
        int orifnt = sdrollbbr.gftOrifntbtion();
        if (sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
            if (indrButton instbndfof BbsidArrowButton) {
                ((BbsidArrowButton)indrButton).sftDirfdtion(
                        orifnt == HORIZONTAL? EAST : SOUTH);
            }
            if (dfdrButton instbndfof BbsidArrowButton) {
                ((BbsidArrowButton)dfdrButton).sftDirfdtion(
                        orifnt == HORIZONTAL? WEST : NORTH);
            }
        }
        flsf {
            if (indrButton instbndfof BbsidArrowButton) {
                ((BbsidArrowButton)indrButton).sftDirfdtion(
                        orifnt == HORIZONTAL? WEST : SOUTH);
            }
            if (dfdrButton instbndfof BbsidArrowButton) {
                ((BbsidArrowButton)dfdrButton).sftDirfdtion(
                        orifnt == HORIZONTAL ? EAST : NORTH);
            }
        }
    }

    publid dlbss PropfrtyCibngfHbndlfr implfmfnts PropfrtyCibngfListfnfr
    {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            gftHbndlfr().propfrtyCibngf(f);
        }
    }


    /**
     * Usfd for sdrolling tif sdrollbbr.
     */
    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
        privbtf stbtid finbl String POSITIVE_UNIT_INCREMENT =
                                    "positivfUnitIndrfmfnt";
        privbtf stbtid finbl String POSITIVE_BLOCK_INCREMENT =
                                    "positivfBlodkIndrfmfnt";
        privbtf stbtid finbl String NEGATIVE_UNIT_INCREMENT =
                                    "nfgbtivfUnitIndrfmfnt";
        privbtf stbtid finbl String NEGATIVE_BLOCK_INCREMENT =
                                    "nfgbtivfBlodkIndrfmfnt";
        privbtf stbtid finbl String MIN_SCROLL = "minSdroll";
        privbtf stbtid finbl String MAX_SCROLL = "mbxSdroll";

        Adtions(String nbmf) {
            supfr(nbmf);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JSdrollBbr sdrollBbr = (JSdrollBbr)f.gftSourdf();
            String kfy = gftNbmf();
            if (kfy == POSITIVE_UNIT_INCREMENT) {
                sdroll(sdrollBbr, POSITIVE_SCROLL, fblsf);
            }
            flsf if (kfy == POSITIVE_BLOCK_INCREMENT) {
                sdroll(sdrollBbr, POSITIVE_SCROLL, truf);
            }
            flsf if (kfy == NEGATIVE_UNIT_INCREMENT) {
                sdroll(sdrollBbr, NEGATIVE_SCROLL, fblsf);
            }
            flsf if (kfy == NEGATIVE_BLOCK_INCREMENT) {
                sdroll(sdrollBbr, NEGATIVE_SCROLL, truf);
            }
            flsf if (kfy == MIN_SCROLL) {
                sdroll(sdrollBbr, BbsidSdrollBbrUI.MIN_SCROLL, truf);
            }
            flsf if (kfy == MAX_SCROLL) {
                sdroll(sdrollBbr, BbsidSdrollBbrUI.MAX_SCROLL, truf);
            }
        }
        privbtf void sdroll(JSdrollBbr sdrollBbr, int dir, boolfbn blodk) {

            if (dir == NEGATIVE_SCROLL || dir == POSITIVE_SCROLL) {
                int bmount;
                // Don't usf tif BbsidSdrollBbrUI.sdrollByXXX mftiods bs wf
                // don't wbnt to usf bn invokfLbtfr to rfsft tif trbdkHigiligit
                // vib bn invokfLbtfr
                if (blodk) {
                    if (dir == NEGATIVE_SCROLL) {
                        bmount = -1 * sdrollBbr.gftBlodkIndrfmfnt(-1);
                    }
                    flsf {
                        bmount = sdrollBbr.gftBlodkIndrfmfnt(1);
                    }
                }
                flsf {
                    if (dir == NEGATIVE_SCROLL) {
                        bmount = -1 * sdrollBbr.gftUnitIndrfmfnt(-1);
                    }
                    flsf {
                        bmount = sdrollBbr.gftUnitIndrfmfnt(1);
                    }
                }
                sdrollBbr.sftVbluf(sdrollBbr.gftVbluf() + bmount);
            }
            flsf if (dir == BbsidSdrollBbrUI.MIN_SCROLL) {
                sdrollBbr.sftVbluf(sdrollBbr.gftMinimum());
            }
            flsf if (dir == BbsidSdrollBbrUI.MAX_SCROLL) {
                sdrollBbr.sftVbluf(sdrollBbr.gftMbximum());
            }
        }
    }


    //
    // EvfntHbndlfr
    //
    privbtf dlbss Hbndlfr implfmfnts FodusListfnfr, PropfrtyCibngfListfnfr {
        //
        // FodusListfnfr
        //
        publid void fodusGbinfd(FodusEvfnt f) {
            sdrollbbr.rfpbint();
        }

        publid void fodusLost(FodusEvfnt f) {
            sdrollbbr.rfpbint();
        }


        //
        // PropfrtyCibngfListfnfr
        //
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String propfrtyNbmf = f.gftPropfrtyNbmf();

            if ("modfl" == propfrtyNbmf) {
                BoundfdRbngfModfl oldModfl = (BoundfdRbngfModfl)f.gftOldVbluf();
                BoundfdRbngfModfl nfwModfl = (BoundfdRbngfModfl)f.gftNfwVbluf();
                oldModfl.rfmovfCibngfListfnfr(modflListfnfr);
                nfwModfl.bddCibngfListfnfr(modflListfnfr);
                sdrollBbrVbluf = sdrollbbr.gftVbluf();
                sdrollbbr.rfpbint();
                sdrollbbr.rfvblidbtf();
            } flsf if ("orifntbtion" == propfrtyNbmf) {
                updbtfButtonDirfdtions();
            } flsf if ("domponfntOrifntbtion" == propfrtyNbmf) {
                updbtfButtonDirfdtions();
                InputMbp inputMbp = gftInputMbp(JComponfnt.WHEN_FOCUSED);
                SwingUtilitifs.rfplbdfUIInputMbp(sdrollbbr, JComponfnt.WHEN_FOCUSED, inputMbp);
            }
        }
    }
}
