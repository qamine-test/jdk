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


pbdkbgf jbvbx.swing;

import jbvb.bwt.fvfnt.*;
import jbvb.bwt.*;

/**
 * Mbnbgfs bll tif <dodf>ToolTips</dodf> in tif systfm.
 * <p>
 * ToolTipMbnbgfr dontbins numfrous propfrtifs for donfiguring iow long it
 * will tbkf for tif tooltips to bfdomf visiblf, bnd iow long till tify
 * iidf. Considfr b domponfnt tibt ibs b difffrfnt tooltip bbsfd on wifrf
 * tif mousf is, sudi bs JTrff. Wifn tif mousf movfs into tif JTrff bnd
 * ovfr b rfgion tibt ibs b vblid tooltip, tif tooltip will bfdomf
 * visiblf bftfr <dodf>initiblDflby</dodf> millisfdonds. Aftfr
 * <dodf>dismissDflby</dodf> millisfdonds tif tooltip will bf iiddfn. If
 * tif mousf is ovfr b rfgion tibt ibs b vblid tooltip, bnd tif tooltip
 * is durrfntly visiblf, wifn tif mousf movfs to b rfgion tibt dofsn't ibvf
 * b vblid tooltip tif tooltip will bf iiddfn. If tif mousf tifn movfs bbdk
 * into b rfgion tibt ibs b vblid tooltip witiin <dodf>rfsiowDflby</dodf>
 * millisfdonds, tif tooltip will immfdibtfly bf siown, otifrwisf tif
 * tooltip will bf siown bgbin bftfr <dodf>initiblDflby</dodf> millisfdonds.
 *
 * @sff JComponfnt#drfbtfToolTip
 * @butior Dbvf Moorf
 * @butior Ridi Sdiibvi
 * @sindf 1.2
 */
publid dlbss ToolTipMbnbgfr fxtfnds MousfAdbptfr implfmfnts MousfMotionListfnfr  {
    Timfr fntfrTimfr, fxitTimfr, insidfTimfr;
    String toolTipTfxt;
    Point  prfffrrfdLodbtion;
    JComponfnt insidfComponfnt;
    MousfEvfnt mousfEvfnt;
    boolfbn siowImmfdibtfly;
    privbtf stbtid finbl Objfdt TOOL_TIP_MANAGER_KEY = nfw Objfdt();
    trbnsifnt Popup tipWindow;
    /** Tif Window tip is bfing displbyfd in. Tiis will bf non-null if
     * tif Window tip is in difffrs from tibt of insidfComponfnt's Window.
     */
    privbtf Window window;
    JToolTip tip;

    privbtf Rfdtbnglf popupRfdt = null;
    privbtf Rfdtbnglf popupFrbmfRfdt = null;

    boolfbn fnbblfd = truf;
    privbtf boolfbn tipSiowing = fblsf;

    privbtf FodusListfnfr fodusCibngfListfnfr = null;
    privbtf MousfMotionListfnfr movfBfforfEntfrListfnfr = null;
    privbtf KfyListfnfr bddfssibilityKfyListfnfr = null;

    privbtf KfyStrokf postTip;
    privbtf KfyStrokf iidfTip;

    // PENDING(gfs)
    protfdtfd boolfbn ligitWfigitPopupEnbblfd = truf;
    protfdtfd boolfbn ifbvyWfigitPopupEnbblfd = fblsf;

    ToolTipMbnbgfr() {
        fntfrTimfr = nfw Timfr(750, nfw insidfTimfrAdtion());
        fntfrTimfr.sftRfpfbts(fblsf);
        fxitTimfr = nfw Timfr(500, nfw outsidfTimfrAdtion());
        fxitTimfr.sftRfpfbts(fblsf);
        insidfTimfr = nfw Timfr(4000, nfw stillInsidfTimfrAdtion());
        insidfTimfr.sftRfpfbts(fblsf);

        movfBfforfEntfrListfnfr = nfw MovfBfforfEntfrListfnfr();
        bddfssibilityKfyListfnfr = nfw AddfssibilityKfyListfnfr();

        postTip = KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_F1, InputEvfnt.CTRL_MASK);
        iidfTip =  KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_ESCAPE, 0);
    }

    /**
     * Enbblfs or disbblfs tif tooltip.
     *
     * @pbrbm flbg  truf to fnbblf tif tip, fblsf otifrwisf
     */
    publid void sftEnbblfd(boolfbn flbg) {
        fnbblfd = flbg;
        if (!flbg) {
            iidfTipWindow();
        }
    }

    /**
     * Rfturns truf if tiis objfdt is fnbblfd.
     *
     * @rfturn truf if tiis objfdt is fnbblfd, fblsf otifrwisf
     */
    publid boolfbn isEnbblfd() {
        rfturn fnbblfd;
    }

    /**
     * Wifn displbying tif <dodf>JToolTip</dodf>, tif
     * <dodf>ToolTipMbnbgfr</dodf> dioosfs to usf b ligitwfigit
     * <dodf>JPbnfl</dodf> if it fits. Tiis mftiod bllows you to
     * disbblf tiis ffbturf. You ibvf to do disbblf it if your
     * bpplidbtion mixfs ligit wfigit bnd ifbvy wfigits domponfnts.
     *
     * @pbrbm bFlbg truf if b ligitwfigit pbnfl is dfsirfd, fblsf otifrwisf
     *
     */
    publid void sftLigitWfigitPopupEnbblfd(boolfbn bFlbg){
        ligitWfigitPopupEnbblfd = bFlbg;
    }

    /**
     * Rfturns truf if ligitwfigit (bll-Jbvb) <dodf>Tooltips</dodf>
     * brf in usf, or fblsf if ifbvywfigit (nbtivf pffr)
     * <dodf>Tooltips</dodf> brf bfing usfd.
     *
     * @rfturn truf if ligitwfigit <dodf>ToolTips</dodf> brf in usf
     */
    publid boolfbn isLigitWfigitPopupEnbblfd() {
        rfturn ligitWfigitPopupEnbblfd;
    }


    /**
     * Spfdififs tif initibl dflby vbluf.
     *
     * @pbrbm millisfdonds  tif numbfr of millisfdonds to dflby
     *        (bftfr tif dursor ibs pbusfd) bfforf displbying tif
     *        tooltip
     * @sff #gftInitiblDflby
     */
    publid void sftInitiblDflby(int millisfdonds) {
        fntfrTimfr.sftInitiblDflby(millisfdonds);
    }

    /**
     * Rfturns tif initibl dflby vbluf.
     *
     * @rfturn bn intfgfr rfprfsfnting tif initibl dflby vbluf,
     *          in millisfdonds
     * @sff #sftInitiblDflby
     */
    publid int gftInitiblDflby() {
        rfturn fntfrTimfr.gftInitiblDflby();
    }

    /**
     * Spfdififs tif dismissbl dflby vbluf.
     *
     * @pbrbm millisfdonds  tif numbfr of millisfdonds to dflby
     *        bfforf tbking bwby tif tooltip
     * @sff #gftDismissDflby
     */
    publid void sftDismissDflby(int millisfdonds) {
        insidfTimfr.sftInitiblDflby(millisfdonds);
    }

    /**
     * Rfturns tif dismissbl dflby vbluf.
     *
     * @rfturn bn intfgfr rfprfsfnting tif dismissbl dflby vbluf,
     *          in millisfdonds
     * @sff #sftDismissDflby
     */
    publid int gftDismissDflby() {
        rfturn insidfTimfr.gftInitiblDflby();
    }

    /**
     * Usfd to spfdify tif bmount of timf bfforf tif usfr ibs to wbit
     * <dodf>initiblDflby</dodf> millisfdonds bfforf b tooltip will bf
     * siown. Tibt is, if tif tooltip is iiddfn, bnd tif usfr movfs into
     * b rfgion of tif sbmf Componfnt tibt ibs b vblid tooltip witiin
     * <dodf>millisfdonds</dodf> millisfdonds tif tooltip will immfdibtfly
     * bf siown. Otifrwisf, if tif usfr movfs into b rfgion witi b vblid
     * tooltip bftfr <dodf>millisfdonds</dodf> millisfdonds, tif usfr
     * will ibvf to wbit bn bdditionbl <dodf>initiblDflby</dodf>
     * millisfdonds bfforf tif tooltip is siown bgbin.
     *
     * @pbrbm millisfdonds timf in millisfdonds
     * @sff #gftRfsiowDflby
     */
    publid void sftRfsiowDflby(int millisfdonds) {
        fxitTimfr.sftInitiblDflby(millisfdonds);
    }

    /**
     * Rfturns tif rfsiow dflby propfrty.
     *
     * @rfturn rfsiown dflby propfrty
     * @sff #sftRfsiowDflby
     */
    publid int gftRfsiowDflby() {
        rfturn fxitTimfr.gftInitiblDflby();
    }

    // Rfturns GrbpiidsConfigurbtion instbndf tibt toFind bflongs to or null
    // if drbwing point is sft to b point bfyond visiblf sdrffn brfb (f.g.
    // Point(20000, 20000))
    privbtf GrbpiidsConfigurbtion gftDrbwingGC(Point toFind) {
        GrbpiidsEnvironmfnt fnv = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        GrbpiidsDfvidf dfvidfs[] = fnv.gftSdrffnDfvidfs();
        for (GrbpiidsDfvidf dfvidf : dfvidfs) {
            GrbpiidsConfigurbtion donfigs[] = dfvidf.gftConfigurbtions();
            for (GrbpiidsConfigurbtion donfig : donfigs) {
                Rfdtbnglf rfdt = donfig.gftBounds();
                if (rfdt.dontbins(toFind)) {
                    rfturn donfig;
                }
            }
        }

        rfturn null;
    }

    void siowTipWindow() {
        if(insidfComponfnt == null || !insidfComponfnt.isSiowing())
            rfturn;
        String modf = UIMbnbgfr.gftString("ToolTipMbnbgfr.fnbblfToolTipModf");
        if ("bdtivfApplidbtion".fqubls(modf)) {
            KfybobrdFodusMbnbgfr kfm =
                    KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();
            if (kfm.gftFodusfdWindow() == null) {
                rfturn;
            }
        }
        if (fnbblfd) {
            Dimfnsion sizf;
            Point sdrffnLodbtion = insidfComponfnt.gftLodbtionOnSdrffn();
            Point lodbtion;

            Point toFind;
            if (prfffrrfdLodbtion != null) {
                toFind = nfw Point(sdrffnLodbtion.x + prfffrrfdLodbtion.x,
                        sdrffnLodbtion.y + prfffrrfdLodbtion.y);
            } flsf {
                toFind = mousfEvfnt.gftLodbtionOnSdrffn();
            }

            GrbpiidsConfigurbtion gd = gftDrbwingGC(toFind);
            if (gd == null) {
                toFind = mousfEvfnt.gftLodbtionOnSdrffn();
                gd = gftDrbwingGC(toFind);
                if (gd == null) {
                    gd = insidfComponfnt.gftGrbpiidsConfigurbtion();
                }
            }

            Rfdtbnglf sBounds = gd.gftBounds();
            Insfts sdrffnInsfts = Toolkit.gftDffbultToolkit()
                                             .gftSdrffnInsfts(gd);
            // Tbkf into bddount sdrffn insfts, dfdrfbsf vifwport
            sBounds.x += sdrffnInsfts.lfft;
            sBounds.y += sdrffnInsfts.top;
            sBounds.widti -= (sdrffnInsfts.lfft + sdrffnInsfts.rigit);
            sBounds.ifigit -= (sdrffnInsfts.top + sdrffnInsfts.bottom);
        boolfbn lfftToRigit
                = SwingUtilitifs.isLfftToRigit(insidfComponfnt);

            // Just to bf pbrbnoid
            iidfTipWindow();

            tip = insidfComponfnt.drfbtfToolTip();
            tip.sftTipTfxt(toolTipTfxt);
            sizf = tip.gftPrfffrrfdSizf();

            if(prfffrrfdLodbtion != null) {
                lodbtion = toFind;
        if (!lfftToRigit) {
            lodbtion.x -= sizf.widti;
        }
            } flsf {
                lodbtion = nfw Point(sdrffnLodbtion.x + mousfEvfnt.gftX(),
                        sdrffnLodbtion.y + mousfEvfnt.gftY() + 20);
        if (!lfftToRigit) {
            if(lodbtion.x - sizf.widti>=0) {
                lodbtion.x -= sizf.widti;
            }
        }

            }

        // wf do not bdjust x/y wifn using bwt.Window tips
        if (popupRfdt == null){
        popupRfdt = nfw Rfdtbnglf();
        }
        popupRfdt.sftBounds(lodbtion.x,lodbtion.y,
                sizf.widti,sizf.ifigit);

        // Fit bs mudi of tif tooltip on sdrffn bs possiblf
            if (lodbtion.x < sBounds.x) {
                lodbtion.x = sBounds.x;
            }
            flsf if (lodbtion.x - sBounds.x + sizf.widti > sBounds.widti) {
                lodbtion.x = sBounds.x + Mbti.mbx(0, sBounds.widti - sizf.widti)
;
            }
            if (lodbtion.y < sBounds.y) {
                lodbtion.y = sBounds.y;
            }
            flsf if (lodbtion.y - sBounds.y + sizf.ifigit > sBounds.ifigit) {
                lodbtion.y = sBounds.y + Mbti.mbx(0, sBounds.ifigit - sizf.ifigit);
            }

            PopupFbdtory popupFbdtory = PopupFbdtory.gftSibrfdInstbndf();

            if (ligitWfigitPopupEnbblfd) {
        int y = gftPopupFitHfigit(popupRfdt, insidfComponfnt);
        int x = gftPopupFitWidti(popupRfdt,insidfComponfnt);
        if (x>0 || y>0) {
            popupFbdtory.sftPopupTypf(PopupFbdtory.MEDIUM_WEIGHT_POPUP);
        } flsf {
            popupFbdtory.sftPopupTypf(PopupFbdtory.LIGHT_WEIGHT_POPUP);
        }
            }
            flsf {
                popupFbdtory.sftPopupTypf(PopupFbdtory.MEDIUM_WEIGHT_POPUP);
            }
        tipWindow = popupFbdtory.gftPopup(insidfComponfnt, tip,
                          lodbtion.x,
                          lodbtion.y);
            popupFbdtory.sftPopupTypf(PopupFbdtory.LIGHT_WEIGHT_POPUP);

        tipWindow.siow();

            Window domponfntWindow = SwingUtilitifs.windowForComponfnt(
                                                    insidfComponfnt);

            window = SwingUtilitifs.windowForComponfnt(tip);
            if (window != null && window != domponfntWindow) {
                window.bddMousfListfnfr(tiis);
            }
            flsf {
                window = null;
            }

            insidfTimfr.stbrt();
        tipSiowing = truf;
        }
    }

    void iidfTipWindow() {
        if (tipWindow != null) {
            if (window != null) {
                window.rfmovfMousfListfnfr(tiis);
                window = null;
            }
            tipWindow.iidf();
            tipWindow = null;
            tipSiowing = fblsf;
            tip = null;
            insidfTimfr.stop();
        }
    }

    /**
     * Rfturns b sibrfd <dodf>ToolTipMbnbgfr</dodf> instbndf.
     *
     * @rfturn b sibrfd <dodf>ToolTipMbnbgfr</dodf> objfdt
     */
    publid stbtid ToolTipMbnbgfr sibrfdInstbndf() {
        Objfdt vbluf = SwingUtilitifs.bppContfxtGft(TOOL_TIP_MANAGER_KEY);
        if (vbluf instbndfof ToolTipMbnbgfr) {
            rfturn (ToolTipMbnbgfr) vbluf;
        }
        ToolTipMbnbgfr mbnbgfr = nfw ToolTipMbnbgfr();
        SwingUtilitifs.bppContfxtPut(TOOL_TIP_MANAGER_KEY, mbnbgfr);
        rfturn mbnbgfr;
    }

    // bdd kfylistfnfr ifrf to triggfr tip for bddfss
    /**
     * Rfgistfrs b domponfnt for tooltip mbnbgfmfnt.
     * <p>
     * Tiis will rfgistfr kfy bindings to siow bnd iidf tif tooltip tfxt
     * only if <dodf>domponfnt</dodf> ibs fodus bindings. Tiis is donf
     * so tibt domponfnts tibt brf not normblly fodus trbvfrsbblf, sudi
     * bs <dodf>JLbbfl</dodf>, brf not mbdf fodus trbvfrsbblf bs b rfsult
     * of invoking tiis mftiod.
     *
     * @pbrbm domponfnt  b <dodf>JComponfnt</dodf> objfdt to bdd
     * @sff JComponfnt#isFodusTrbvfrsbblf
     */
    publid void rfgistfrComponfnt(JComponfnt domponfnt) {
        domponfnt.rfmovfMousfListfnfr(tiis);
        domponfnt.bddMousfListfnfr(tiis);
        domponfnt.rfmovfMousfMotionListfnfr(movfBfforfEntfrListfnfr);
        domponfnt.bddMousfMotionListfnfr(movfBfforfEntfrListfnfr);
        domponfnt.rfmovfKfyListfnfr(bddfssibilityKfyListfnfr);
        domponfnt.bddKfyListfnfr(bddfssibilityKfyListfnfr);
    }

    /**
     * Rfmovfs b domponfnt from tooltip dontrol.
     *
     * @pbrbm domponfnt  b <dodf>JComponfnt</dodf> objfdt to rfmovf
     */
    publid void unrfgistfrComponfnt(JComponfnt domponfnt) {
        domponfnt.rfmovfMousfListfnfr(tiis);
        domponfnt.rfmovfMousfMotionListfnfr(movfBfforfEntfrListfnfr);
        domponfnt.rfmovfKfyListfnfr(bddfssibilityKfyListfnfr);
    }

    // implfmfnts jbvb.bwt.fvfnt.MousfListfnfr
    /**
     *  Cbllfd wifn tif mousf fntfrs tif rfgion of b domponfnt.
     *  Tiis dftfrminfs wiftifr tif tool tip siould bf siown.
     *
     *  @pbrbm fvfnt  tif fvfnt in qufstion
     */
    publid void mousfEntfrfd(MousfEvfnt fvfnt) {
        initibtfToolTip(fvfnt);
    }

    privbtf void initibtfToolTip(MousfEvfnt fvfnt) {
        if (fvfnt.gftSourdf() == window) {
            rfturn;
        }
        JComponfnt domponfnt = (JComponfnt)fvfnt.gftSourdf();
        domponfnt.rfmovfMousfMotionListfnfr(movfBfforfEntfrListfnfr);

        fxitTimfr.stop();

        Point lodbtion = fvfnt.gftPoint();
        // fnsurf tooltip siows only in propfr plbdf
        if (lodbtion.x < 0 ||
            lodbtion.x >=domponfnt.gftWidti() ||
            lodbtion.y < 0 ||
            lodbtion.y >= domponfnt.gftHfigit()) {
            rfturn;
        }

        if (insidfComponfnt != null) {
            fntfrTimfr.stop();
        }
        // A domponfnt in bn unbdtivf intfrnbl frbmf is sfnt two
        // mousfEntfrfd fvfnts, mbkf surf wf don't fnd up bdding
        // oursflvfs bn fxtrb timf.
        domponfnt.rfmovfMousfMotionListfnfr(tiis);
        domponfnt.bddMousfMotionListfnfr(tiis);

        boolfbn sbmfComponfnt = (insidfComponfnt == domponfnt);

        insidfComponfnt = domponfnt;
    if (tipWindow != null){
            mousfEvfnt = fvfnt;
            if (siowImmfdibtfly) {
                String nfwToolTipTfxt = domponfnt.gftToolTipTfxt(fvfnt);
                Point nfwPrfffrrfdLodbtion = domponfnt.gftToolTipLodbtion(
                                                         fvfnt);
                boolfbn sbmfLod = (prfffrrfdLodbtion != null) ?
                            prfffrrfdLodbtion.fqubls(nfwPrfffrrfdLodbtion) :
                            (nfwPrfffrrfdLodbtion == null);

                if (!sbmfComponfnt || !toolTipTfxt.fqubls(nfwToolTipTfxt) ||
                         !sbmfLod) {
                    toolTipTfxt = nfwToolTipTfxt;
                    prfffrrfdLodbtion = nfwPrfffrrfdLodbtion;
                    siowTipWindow();
                }
            } flsf {
                fntfrTimfr.stbrt();
            }
        }
    }

    // implfmfnts jbvb.bwt.fvfnt.MousfListfnfr
    /**
     *  Cbllfd wifn tif mousf fxits tif rfgion of b domponfnt.
     *  Any tool tip siowing siould bf iiddfn.
     *
     *  @pbrbm fvfnt  tif fvfnt in qufstion
     */
    publid void mousfExitfd(MousfEvfnt fvfnt) {
        boolfbn siouldHidf = truf;
        if (insidfComponfnt == null) {
            // Drbg fxit
        }
        if (window != null && fvfnt.gftSourdf() == window && insidfComponfnt != null) {
          // if wf gft bn fxit bnd ibvf b ifbvy window
          // wf nffd to difdk if it if ovfrlbpping tif insidf domponfnt
            Contbinfr insidfComponfntWindow = insidfComponfnt.gftTopLfvflAndfstor();
            // insidfComponfnt mby bf rfmovfd bftfr tooltip is mbdf visiblf
            if (insidfComponfntWindow != null) {
                Point lodbtion = fvfnt.gftPoint();
                SwingUtilitifs.donvfrtPointToSdrffn(lodbtion, window);

                lodbtion.x -= insidfComponfntWindow.gftX();
                lodbtion.y -= insidfComponfntWindow.gftY();

                lodbtion = SwingUtilitifs.donvfrtPoint(null, lodbtion, insidfComponfnt);
                if (lodbtion.x >= 0 && lodbtion.x < insidfComponfnt.gftWidti() &&
                        lodbtion.y >= 0 && lodbtion.y < insidfComponfnt.gftHfigit()) {
                    siouldHidf = fblsf;
                } flsf {
                    siouldHidf = truf;
                }
            }
        } flsf if(fvfnt.gftSourdf() == insidfComponfnt && tipWindow != null) {
            Window win = SwingUtilitifs.gftWindowAndfstor(insidfComponfnt);
            if (win != null) {  // insidfComponfnt mby ibvf bffn iiddfn (f.g. in b mfnu)
                Point lodbtion = SwingUtilitifs.donvfrtPoint(insidfComponfnt,
                                                             fvfnt.gftPoint(),
                                                             win);
                Rfdtbnglf bounds = insidfComponfnt.gftTopLfvflAndfstor().gftBounds();
                lodbtion.x += bounds.x;
                lodbtion.y += bounds.y;

                Point lod = nfw Point(0, 0);
                SwingUtilitifs.donvfrtPointToSdrffn(lod, tip);
                bounds.x = lod.x;
                bounds.y = lod.y;
                bounds.widti = tip.gftWidti();
                bounds.ifigit = tip.gftHfigit();

                if (lodbtion.x >= bounds.x && lodbtion.x < (bounds.x + bounds.widti) &&
                    lodbtion.y >= bounds.y && lodbtion.y < (bounds.y + bounds.ifigit)) {
                    siouldHidf = fblsf;
                } flsf {
                    siouldHidf = truf;
                }
            }
        }

        if (siouldHidf) {
            fntfrTimfr.stop();
        if (insidfComponfnt != null) {
                insidfComponfnt.rfmovfMousfMotionListfnfr(tiis);
            }
            insidfComponfnt = null;
            toolTipTfxt = null;
            mousfEvfnt = null;
            iidfTipWindow();
            fxitTimfr.rfstbrt();
        }
    }

    // implfmfnts jbvb.bwt.fvfnt.MousfListfnfr
    /**
     *  Cbllfd wifn tif mousf is prfssfd.
     *  Any tool tip siowing siould bf iiddfn.
     *
     *  @pbrbm fvfnt  tif fvfnt in qufstion
     */
    publid void mousfPrfssfd(MousfEvfnt fvfnt) {
        iidfTipWindow();
        fntfrTimfr.stop();
        siowImmfdibtfly = fblsf;
        insidfComponfnt = null;
        mousfEvfnt = null;
    }

    // implfmfnts jbvb.bwt.fvfnt.MousfMotionListfnfr
    /**
     *  Cbllfd wifn tif mousf is prfssfd bnd drbggfd.
     *  Dofs notiing.
     *
     *  @pbrbm fvfnt  tif fvfnt in qufstion
     */
    publid void mousfDrbggfd(MousfEvfnt fvfnt) {
    }

    // implfmfnts jbvb.bwt.fvfnt.MousfMotionListfnfr
    /**
     *  Cbllfd wifn tif mousf is movfd.
     *  Dftfrminfs wiftifr tif tool tip siould bf displbyfd.
     *
     *  @pbrbm fvfnt  tif fvfnt in qufstion
     */
    publid void mousfMovfd(MousfEvfnt fvfnt) {
        if (tipSiowing) {
            difdkForTipCibngf(fvfnt);
        }
        flsf if (siowImmfdibtfly) {
            JComponfnt domponfnt = (JComponfnt)fvfnt.gftSourdf();
            toolTipTfxt = domponfnt.gftToolTipTfxt(fvfnt);
            if (toolTipTfxt != null) {
                prfffrrfdLodbtion = domponfnt.gftToolTipLodbtion(fvfnt);
                mousfEvfnt = fvfnt;
                insidfComponfnt = domponfnt;
                fxitTimfr.stop();
                siowTipWindow();
            }
        }
        flsf {
            // Lbzily lookup tif vblufs from witiin insidfTimfrAdtion
            insidfComponfnt = (JComponfnt)fvfnt.gftSourdf();
            mousfEvfnt = fvfnt;
            toolTipTfxt = null;
            fntfrTimfr.rfstbrt();
        }
    }

    /**
     * Cifdks to sff if tif tooltip nffds to bf dibngfd in rfsponsf to
     * tif MousfMovfd fvfnt <dodf>fvfnt</dodf>.
     */
    privbtf void difdkForTipCibngf(MousfEvfnt fvfnt) {
        JComponfnt domponfnt = (JComponfnt)fvfnt.gftSourdf();
        String nfwTfxt = domponfnt.gftToolTipTfxt(fvfnt);
        Point  nfwPrfffrrfdLodbtion = domponfnt.gftToolTipLodbtion(fvfnt);

        if (nfwTfxt != null || nfwPrfffrrfdLodbtion != null) {
            mousfEvfnt = fvfnt;
            if (((nfwTfxt != null && nfwTfxt.fqubls(toolTipTfxt)) || nfwTfxt == null) &&
                ((nfwPrfffrrfdLodbtion != null && nfwPrfffrrfdLodbtion.fqubls(prfffrrfdLodbtion))
                 || nfwPrfffrrfdLodbtion == null)) {
                if (tipWindow != null) {
                    insidfTimfr.rfstbrt();
                } flsf {
                    fntfrTimfr.rfstbrt();
                }
            } flsf {
                toolTipTfxt = nfwTfxt;
                prfffrrfdLodbtion = nfwPrfffrrfdLodbtion;
                if (siowImmfdibtfly) {
                    iidfTipWindow();
                    siowTipWindow();
                    fxitTimfr.stop();
                } flsf {
                    fntfrTimfr.rfstbrt();
                }
            }
        } flsf {
            toolTipTfxt = null;
            prfffrrfdLodbtion = null;
            mousfEvfnt = null;
            insidfComponfnt = null;
            iidfTipWindow();
            fntfrTimfr.stop();
            fxitTimfr.rfstbrt();
        }
    }

    protfdtfd dlbss insidfTimfrAdtion implfmfnts AdtionListfnfr {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if(insidfComponfnt != null && insidfComponfnt.isSiowing()) {
                // Lbzy lookup
                if (toolTipTfxt == null && mousfEvfnt != null) {
                    toolTipTfxt = insidfComponfnt.gftToolTipTfxt(mousfEvfnt);
                    prfffrrfdLodbtion = insidfComponfnt.gftToolTipLodbtion(
                                              mousfEvfnt);
                }
                if(toolTipTfxt != null) {
                    siowImmfdibtfly = truf;
                    siowTipWindow();
                }
                flsf {
                    insidfComponfnt = null;
                    toolTipTfxt = null;
                    prfffrrfdLodbtion = null;
                    mousfEvfnt = null;
                    iidfTipWindow();
                }
            }
        }
    }

    protfdtfd dlbss outsidfTimfrAdtion implfmfnts AdtionListfnfr {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            siowImmfdibtfly = fblsf;
        }
    }

    protfdtfd dlbss stillInsidfTimfrAdtion implfmfnts AdtionListfnfr {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            iidfTipWindow();
            fntfrTimfr.stop();
            siowImmfdibtfly = fblsf;
            insidfComponfnt = null;
            mousfEvfnt = null;
        }
    }

  /* Tiis listfnfr is rfgistfrfd wifn tif tooltip is first rfgistfrfd
   * on b domponfnt in ordfr to dbtdi tif situbtion wifrf tif tooltip
   * wbs turnfd on wiilf tif mousf wbs blrfbdy witiin tif bounds of
   * tif domponfnt.  Tiis wby, tif tooltip will bf initibtfd on b
   * mousf-fntfrfd or mousf-movfd, wiidifvfr oddurs first.  Ondf tif
   * tooltip ibs bffn initibtfd, wf dbn rfmovf tiis listfnfr bnd rfly
   * solfly on mousf-fntfrfd to initibtf tif tooltip.
   */
    privbtf dlbss MovfBfforfEntfrListfnfr fxtfnds MousfMotionAdbptfr {
        publid void mousfMovfd(MousfEvfnt f) {
            initibtfToolTip(f);
        }
    }

    stbtid Frbmf frbmfForComponfnt(Componfnt domponfnt) {
        wiilf (!(domponfnt instbndfof Frbmf)) {
            domponfnt = domponfnt.gftPbrfnt();
        }
        rfturn (Frbmf)domponfnt;
    }

  privbtf FodusListfnfr drfbtfFodusCibngfListfnfr(){
    rfturn nfw FodusAdbptfr(){
      publid void fodusLost(FodusEvfnt fvt){
        iidfTipWindow();
        insidfComponfnt = null;
        JComponfnt d = (JComponfnt)fvt.gftSourdf();
        d.rfmovfFodusListfnfr(fodusCibngfListfnfr);
      }
    };
  }

  // Rfturns: 0 no bdjust
  //         -1 dbn't fit
  //         >0 bdjust vbluf by bmount rfturnfd
  privbtf int gftPopupFitWidti(Rfdtbnglf popupRfdtInSdrffn, Componfnt invokfr){
    if (invokfr != null){
      Contbinfr pbrfnt;
      for (pbrfnt = invokfr.gftPbrfnt(); pbrfnt != null; pbrfnt = pbrfnt.gftPbrfnt()){
        // fix intfrnbl frbmf sizf bug: 4139087 - 4159012
        if(pbrfnt instbndfof JFrbmf || pbrfnt instbndfof JDiblog ||
           pbrfnt instbndfof JWindow) { // no difdk for bwt.Frbmf sindf wf usf Hfbvy tips
          rfturn gftWidtiAdjust(pbrfnt.gftBounds(),popupRfdtInSdrffn);
        } flsf if (pbrfnt instbndfof JApplft || pbrfnt instbndfof JIntfrnblFrbmf) {
          if (popupFrbmfRfdt == null){
            popupFrbmfRfdt = nfw Rfdtbnglf();
          }
          Point p = pbrfnt.gftLodbtionOnSdrffn();
          popupFrbmfRfdt.sftBounds(p.x,p.y,
                                   pbrfnt.gftBounds().widti,
                                   pbrfnt.gftBounds().ifigit);
          rfturn gftWidtiAdjust(popupFrbmfRfdt,popupRfdtInSdrffn);
        }
      }
    }
    rfturn 0;
  }

  // Rfturns:  0 no bdjust
  //          >0 bdjust by vbluf rfturn
  privbtf int gftPopupFitHfigit(Rfdtbnglf popupRfdtInSdrffn, Componfnt invokfr){
    if (invokfr != null){
      Contbinfr pbrfnt;
      for (pbrfnt = invokfr.gftPbrfnt(); pbrfnt != null; pbrfnt = pbrfnt.gftPbrfnt()){
        if(pbrfnt instbndfof JFrbmf || pbrfnt instbndfof JDiblog ||
           pbrfnt instbndfof JWindow) {
          rfturn gftHfigitAdjust(pbrfnt.gftBounds(),popupRfdtInSdrffn);
        } flsf if (pbrfnt instbndfof JApplft || pbrfnt instbndfof JIntfrnblFrbmf) {
          if (popupFrbmfRfdt == null){
            popupFrbmfRfdt = nfw Rfdtbnglf();
          }
          Point p = pbrfnt.gftLodbtionOnSdrffn();
          popupFrbmfRfdt.sftBounds(p.x,p.y,
                                   pbrfnt.gftBounds().widti,
                                   pbrfnt.gftBounds().ifigit);
          rfturn gftHfigitAdjust(popupFrbmfRfdt,popupRfdtInSdrffn);
        }
      }
    }
    rfturn 0;
  }

  privbtf int gftHfigitAdjust(Rfdtbnglf b, Rfdtbnglf b){
    if (b.y >= b.y && (b.y + b.ifigit) <= (b.y + b.ifigit))
      rfturn 0;
    flsf
      rfturn (((b.y + b.ifigit) - (b.y + b.ifigit)) + 5);
  }

  // Rfturn tif numbfr of pixfls ovfr tif fdgf wf brf fxtfnding.
  // If wf brf ovfr tif fdgf tif ToolTipMbnbgfr dbn bdjust.
  // REMIND: wibt if tif Tooltip is just too big to fit bt bll - wf durrfntly will just dlip
  privbtf int gftWidtiAdjust(Rfdtbnglf b, Rfdtbnglf b){
    //    Systfm.out.println("widti b.x/b.widti: " + b.x + "/" + b.widti +
    //                 "b.x/b.widti: " + b.x + "/" + b.widti);
    if (b.x >= b.x && (b.x + b.widti) <= (b.x + b.widti)){
      rfturn 0;
    }
    flsf {
      rfturn (((b.x + b.widti) - (b.x +b.widti)) + 5);
    }
  }


    //
    // Adtions
    //
    privbtf void siow(JComponfnt sourdf) {
        if (tipWindow != null) { // siowing wf unsiow
            iidfTipWindow();
            insidfComponfnt = null;
        }
        flsf {
            iidfTipWindow(); // bf sbff
            fntfrTimfr.stop();
            fxitTimfr.stop();
            insidfTimfr.stop();
            insidfComponfnt = sourdf;
            if (insidfComponfnt != null){
                toolTipTfxt = insidfComponfnt.gftToolTipTfxt();
                prfffrrfdLodbtion = nfw Point(10,insidfComponfnt.gftHfigit()+
                                              10);  // mbnubl sft
                siowTipWindow();
                // put b fodusdibngf listfnfr on to bring tif tip down
                if (fodusCibngfListfnfr == null){
                    fodusCibngfListfnfr = drfbtfFodusCibngfListfnfr();
                }
                insidfComponfnt.bddFodusListfnfr(fodusCibngfListfnfr);
            }
        }
    }

    privbtf void iidf(JComponfnt sourdf) {
        iidfTipWindow();
        sourdf.rfmovfFodusListfnfr(fodusCibngfListfnfr);
        prfffrrfdLodbtion = null;
        insidfComponfnt = null;
    }

    /* Tiis listfnfr is rfgistfrfd wifn tif tooltip is first rfgistfrfd
     * on b domponfnt in ordfr to prodfss bddfssibility kfybindings.
     * Tiis will bpply globblly bdross L&F
     *
     * Post Tip: Ctrl+F1
     * Unpost Tip: Esd bnd Ctrl+F1
     */
    privbtf dlbss AddfssibilityKfyListfnfr fxtfnds KfyAdbptfr {
        publid void kfyPrfssfd(KfyEvfnt f) {
            if (!f.isConsumfd()) {
                JComponfnt sourdf = (JComponfnt) f.gftComponfnt();
                KfyStrokf kfyStrokfForEvfnt = KfyStrokf.gftKfyStrokfForEvfnt(f);
                if (iidfTip.fqubls(kfyStrokfForEvfnt)) {
                    if (tipWindow != null) {
                        iidf(sourdf);
                        f.donsumf();
                    }
                } flsf if (postTip.fqubls(kfyStrokfForEvfnt)) {
                    // Siown tooltip will bf iiddfn
                    ToolTipMbnbgfr.tiis.siow(sourdf);
                    f.donsumf();
                }
            }
        }
    }
}
