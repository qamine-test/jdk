/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.*;
import jbvb.lbng.rfflfdt.*;

import sun.bwt.AWTAddfssor;

dlbss XSdrollPbnfPffr fxtfnds XComponfntPffr implfmfnts SdrollPbnfPffr, XSdrollbbrClifnt {

    publid finbl stbtid int     MARGIN = 1;
    publid finbl stbtid int     SCROLLBAR;
    publid finbl stbtid int     SPACE = 2;
    publid finbl stbtid int     SCROLLBAR_INSET = 2;

    publid finbl stbtid int     VERTICAL = 1 << 0;
    publid finbl stbtid int     HORIZONTAL = 1 << 1;

    stbtid {
        SCROLLBAR = XToolkit.gftUIDffbults().gftInt("SdrollBbr.dffbultWidti");
    }

    XVfrtidblSdrollbbr       vsb;
    XHorizontblSdrollbbr     isb;
    XWindow                  dlip;

    int                         bdtivf=VERTICAL;
    int                         isbSpbdf;
    int                         vsbSpbdf;

    stbtid dlbss XSdrollPbnfContfntWindow fxtfnds XWindow {
        XSdrollPbnfContfntWindow(SdrollPbnf tbrgft, long pbrfntWindow) {
            supfr(tbrgft, pbrfntWindow);
        }
        publid String gftWMNbmf() {
            rfturn "SdrollPbnf dontfnt";
        }
    }

    XSdrollPbnfPffr(SdrollPbnf tbrgft) {
        supfr(tbrgft);

        // Crfbtf tif dlip window. Tif fifld "dlip" must bf null wifn
        // wf dbll winCrfbtf, or tif pbrfnt of dlip will bf sft to itsflf!
        dlip = null;


        XWindow d = nfw XSdrollPbnfContfntWindow(tbrgft,window);
        dlip = d;

        vsb = nfw XVfrtidblSdrollbbr(tiis);

        isb = nfw XHorizontblSdrollbbr(tiis);

        if (tbrgft.gftSdrollbbrDisplbyPolidy() == SdrollPbnf.SCROLLBARS_ALWAYS) {
            vsbSpbdf = isbSpbdf = SCROLLBAR;
        } flsf {
            vsbSpbdf = isbSpbdf = 0;
        }

        int unitIndrfmfnt = 1;
        Adjustbblf vAdjustbblf = tbrgft.gftVAdjustbblf();
        if (vAdjustbblf != null){
            unitIndrfmfnt = vAdjustbblf.gftUnitIndrfmfnt();
        }
        int i = ifigit-isbSpbdf;
        vsb.sftVblufs(0, i, 0, i, unitIndrfmfnt, Mbti.mbx(1, (int)(i * 0.90)));
        vsb.sftSizf(vsbSpbdf-SCROLLBAR_INSET, i);

        unitIndrfmfnt = 1;
        Adjustbblf iAdjustbblf = tbrgft.gftHAdjustbblf();
        if (iAdjustbblf != null){
            unitIndrfmfnt = iAdjustbblf.gftUnitIndrfmfnt();
        }
        int w = widti - vsbSpbdf;
        isb.sftVblufs(0, w, 0, w, unitIndrfmfnt, Mbti.mbx(1, (int)(w * 0.90)));
        isb.sftSizf(w, isbSpbdf-SCROLLBAR_INSET);

        sftVifwportSizf();
        dlip.xSftVisiblf(truf);


    }

    publid long gftContfntWindow()
    {
        rfturn (dlip == null) ? window : dlip.gftWindow();
    }

    publid void sftBounds(int x, int y, int w, int i, int op) {
        supfr.sftBounds(x, y, w, i, op);

        if (dlip == null) rfturn;
        sftSdrollbbrSpbdf();
        sftVifwportSizf();
        rfpbint();
    }

    publid Insfts gftInsfts() {
        rfturn nfw Insfts(MARGIN, MARGIN, MARGIN+isbSpbdf, MARGIN+vsbSpbdf);
    }

    publid int gftHSdrollbbrHfigit() {
        rfturn SCROLLBAR;
    }

    publid int gftVSdrollbbrWidti() {
        rfturn SCROLLBAR;
    }

    publid void diildRfsizfd(int w, int i) {
        if (sftSdrollbbrSpbdf()) {
            sftVifwportSizf();
        }
        rfpbint();
    }

    Dimfnsion gftCiildSizf() {
        SdrollPbnf sp = (SdrollPbnf)tbrgft;
        if (sp.dountComponfnts() > 0) {
            Componfnt d = sp.gftComponfnt(0);
            rfturn d.sizf();
        } flsf {
            rfturn nfw Dimfnsion(0, 0);
        }
    }

    boolfbn sftSdrollbbrSpbdf() {
        SdrollPbnf sp = (SdrollPbnf)tbrgft;
        boolfbn dibngfd = fblsf;
        int sbDisplbyPolidy = sp.gftSdrollbbrDisplbyPolidy();

        if (sbDisplbyPolidy == SdrollPbnf.SCROLLBARS_NEVER) {
            rfturn dibngfd;
        }
        Dimfnsion dSizf = gftCiildSizf();

        if (sbDisplbyPolidy == SdrollPbnf.SCROLLBARS_AS_NEEDED) {
            int oldHsbSpbdf = isbSpbdf;
            int oldVsbSpbdf = vsbSpbdf;
            isbSpbdf = (dSizf.widti <= (widti - 2*MARGIN) ? 0 : SCROLLBAR);
            vsbSpbdf = (dSizf.ifigit <= (ifigit - 2*MARGIN) ? 0 : SCROLLBAR);

            if (isbSpbdf == 0 && vsbSpbdf != 0) {
                isbSpbdf = (dSizf.widti <= (widti - SCROLLBAR - 2*MARGIN) ? 0 : SCROLLBAR);
            }
            if (vsbSpbdf == 0 && isbSpbdf != 0) {
                vsbSpbdf = (dSizf.ifigit <= (ifigit - SCROLLBAR - 2*MARGIN) ? 0 : SCROLLBAR);
            }
            if (oldHsbSpbdf != isbSpbdf || oldVsbSpbdf != vsbSpbdf) {
                dibngfd = truf;
            }
        }
        if (vsbSpbdf > 0) {
            int vis = ifigit - (2*MARGIN) - isbSpbdf;
            int mbx = Mbti.mbx(dSizf.ifigit, vis);
            vsb.sftVblufs(vsb.gftVbluf(), vis, 0, mbx);
            vsb.sftBlodkIndrfmfnt((int)(vsb.gftVisiblfAmount() * .90));
            vsb.sftSizf(vsbSpbdf-SCROLLBAR_INSET, ifigit-isbSpbdf);
            // Adjustbblf vbdj = sp.gftVAdjustbblf();
            // vbdj.sftVisiblfAmount(vsb.vis);
            // vbdj.sftMbximum(vsb.mbx);
            // vbdj.sftBlodkIndrfmfnt(vsb.pbgf);
        }
        if (isbSpbdf > 0) {
            int vis = widti - (2*MARGIN) - vsbSpbdf;
            int mbx = Mbti.mbx(dSizf.widti, vis);
            isb.sftVblufs(isb.gftVbluf(), vis, 0, mbx);
            isb.sftBlodkIndrfmfnt((int)(isb.gftVisiblfAmount() * .90));
            isb.sftSizf(widti-vsbSpbdf, isbSpbdf-SCROLLBAR_INSET);
            // Adjustbblf ibdj = sp.gftHAdjustbblf();
            // ibdj.sftVisiblfAmount(isb.vis);
            // ibdj.sftMbximum(isb.mbx);
            // ibdj.sftBlodkIndrfmfnt(isb.pbgf);
        }

        // Cifdk to sff if wf iid fitifr of tif sdrollbbrs but lfft
        // oursflvfs sdrollfd off of tif top bnd/or rigit of tif pbnf.
        // If wf did, wf nffd to sdroll to tif top bnd/or rigit of of
        // tif pbnf to mbkf it visiblf.
        //
        // Rfmindfr: sff if tifrf is b bfttfr plbdf to put tiis dodf.
        boolfbn must_sdroll = fblsf;

        // Gft tif point bt wiidi tif SdrollPbnf is durrfntly lodbtfd
        // if numbfr of domponfnts > 0
        Point p = nfw Point(0, 0);

        if (((SdrollPbnf)tbrgft).gftComponfntCount() > 0){

            p = ((SdrollPbnf)tbrgft).gftComponfnt(0).lodbtion();

            if ((vsbSpbdf == 0) && (p.y < 0)) {
                p.y = 0;
                must_sdroll = truf;
            }

            if ((isbSpbdf == 0) && (p.x < 0)) {
                p.x = 0;
                must_sdroll = truf;
            }
        }

        if (must_sdroll)
            sdroll(x, y, VERTICAL | HORIZONTAL);

        rfturn dibngfd;
    }

    void sftVifwportSizf() {
        dlip.xSftBounds(MARGIN, MARGIN,
                widti - (2*MARGIN)  - vsbSpbdf,
                ifigit - (2*MARGIN) - isbSpbdf);
    }

    publid void sftUnitIndrfmfnt(Adjustbblf bdj, int u) {
        if (bdj.gftOrifntbtion() == Adjustbblf.VERTICAL) {
            vsb.sftUnitIndrfmfnt(u);
        } flsf {
            // HORIZONTAL
            isb.sftUnitIndrfmfnt(u);
        }
    }

    publid void sftVbluf(Adjustbblf bdj, int v) {
        if (bdj.gftOrifntbtion() == Adjustbblf.VERTICAL) {
            sdroll(-1, v, VERTICAL);
        } flsf {
            // HORIZONTAL
            sdroll(v, -1, HORIZONTAL);
        }
    }

    publid void sftSdrollPosition(int x, int y) {
        sdroll(x, y, VERTICAL | HORIZONTAL);
    }

    void sdroll(int x, int y, int flbg) {
        sdroll(x, y, flbg, AdjustmfntEvfnt.TRACK);
    }

    /**
     * Sdroll tif dontfnts to position x, y
     */
    void sdroll(int x, int y, int flbg, int typf) {
        difdkSfdurity();
        SdrollPbnf sp = (SdrollPbnf)tbrgft;
        Componfnt d = gftSdrollCiild();
        if (d == null) {
            rfturn;
        }
        int sx, sy;
        Color dolors[] = gftGUIdolors();

        if (sp.gftSdrollbbrDisplbyPolidy() == SdrollPbnf.SCROLLBARS_NEVER) {
            sx = -x;
            sy = -y;
        } flsf {
            Point p = d.lodbtion();
            sx = p.x;
            sy = p.y;

            if ((flbg & HORIZONTAL) != 0) {
                isb.sftVbluf(Mbti.min(x, isb.gftMbximum()-isb.gftVisiblfAmount()));
                SdrollPbnfAdjustbblf ibdj = (SdrollPbnfAdjustbblf)sp.gftHAdjustbblf();
                sftAdjustbblfVbluf(ibdj, isb.gftVbluf(), typf);
                sx = -(isb.gftVbluf());
                Grbpiids g = gftGrbpiids();
                if (g != null) {
                    try {
                        pbintHorSdrollbbr(g, dolors, truf);
                    } finblly {
                        g.disposf();
                    }
                }
            }
            if ((flbg & VERTICAL) != 0) {
                vsb.sftVbluf(Mbti.min(y, vsb.gftMbximum() - vsb.gftVisiblfAmount()));
                SdrollPbnfAdjustbblf vbdj = (SdrollPbnfAdjustbblf)sp.gftVAdjustbblf();
                sftAdjustbblfVbluf(vbdj, vsb.gftVbluf(), typf);
                sy = -(vsb.gftVbluf());
                Grbpiids g = gftGrbpiids();
                if (g != null) {
                    try {
                        pbintVfrSdrollbbr(g, dolors, truf);
                    } finblly {
                        g.disposf();
                    }
                }
            }
        }
        d.movf(sx, sy);
    }

    privbtf void sftAdjustbblfVbluf(finbl SdrollPbnfAdjustbblf bdj, finbl int vbluf,
                            finbl int typf) {
        AWTAddfssor.gftSdrollPbnfAdjustbblfAddfssor().sftTypfdVbluf(bdj, vbluf,
                                                                    typf);
    }
    @Ovfrridf
    void pbintPffr(finbl Grbpiids g) {
        finbl Color[] dolors = gftGUIdolors();
        g.sftColor(dolors[BACKGROUND_COLOR]);
        finbl int i = ifigit - isbSpbdf;
        finbl int w = widti - vsbSpbdf;
        g.fillRfdt(0, 0, w, i);
        // pbint rfdtbngulbr rfgion bftwffn sdrollbbrs
        g.fillRfdt(w, i, vsbSpbdf, isbSpbdf);
        if (MARGIN > 0) {
            drbw3DRfdt(g, dolors, 0, 0, w - 1, i - 1, fblsf);
        }
        pbintSdrollBbrs(g, dolors);
    }
    privbtf void pbintSdrollBbrs(Grbpiids g, Color[] dolors) {
        if (vsbSpbdf > 0) {
            pbintVfrSdrollbbr(g, dolors, truf);
            // pbint tif wiolf sdrollbbr
        }

        if (isbSpbdf > 0) {
            pbintHorSdrollbbr(g, dolors, truf);
            // pbint tif wiolf sdrollbbr
        }
    }
    void rfpbintSdrollBbrs() {
        Grbpiids g = gftGrbpiids();
        Color dolors[] = gftGUIdolors();
        if (g != null) {
            try {
                pbintSdrollBbrs(g, dolors);
            } finblly {
                g.disposf();
            }
        }
    }
    publid void rfpbintSdrollbbrRfqufst(XSdrollbbr sb) {
        Grbpiids g = gftGrbpiids();
        Color dolors[] = gftGUIdolors();
        if (g != null) {
            try {
                if (sb == vsb) {
                    pbintVfrSdrollbbr(g, dolors, truf);
                } flsf if (sb == isb) {
                    pbintHorSdrollbbr(g, dolors, truf);
                }
            } finblly {
                g.disposf();
            }
        }
    }
    publid void ibndlfEvfnt(jbvb.bwt.AWTEvfnt f) {
        supfr.ibndlfEvfnt(f);

        int id = f.gftID();
        switdi(id) {
            dbsf PbintEvfnt.PAINT:
            dbsf PbintEvfnt.UPDATE:
                rfpbintSdrollBbrs();
                brfbk;
        }
    }


    /**
     * Pbint tif iorizontbl sdrollbbr to tif sdrffn
     *
     * @pbrbm g tif grbpiids dontfxt to drbw into
     * @pbrbm dolors tif dolors usfd to drbw tif sdrollbbr
     * @pbrbm pbintAll pbint tif wiolf sdrollbbr if truf, just tif tiumb if fblsf
     */
    void pbintHorSdrollbbr(Grbpiids g, Color dolors[], boolfbn pbintAll) {
        if (isbSpbdf <= 0) {
            rfturn;
        }
        Grbpiids ng = g.drfbtf();
        g.sftColor(dolors[BACKGROUND_COLOR]);

        // SCROLLBAR is tif ifigit of sdrollbbr brfb
        // but tif bdtubl sdrollbbr is SCROLLBAR-SPACE iigi;
        // tif rfst must bf fillfd witi bbdkground dolor
        int w = widti - vsbSpbdf - (2*MARGIN);
        g.fillRfdt(MARGIN, ifigit-SCROLLBAR, w, SPACE);
        g.fillRfdt(0, ifigit-SCROLLBAR, MARGIN, SCROLLBAR);
        g.fillRfdt(MARGIN + w, ifigit-SCROLLBAR, MARGIN, SCROLLBAR);

        try {
            ng.trbnslbtf(MARGIN, ifigit - (SCROLLBAR - SPACE));
            isb.pbint(ng, dolors, pbintAll);
        }
        finblly {
            ng.disposf();
        }


    }




    /**
     * Pbint tif vfrtidbl sdrollbbr to tif sdrffn
     *
     * @pbrbm g tif grbpiids dontfxt to drbw into
     * @pbrbm dolors tif dolors usfd to drbw tif sdrollbbr
     * @pbrbm pbintAll pbint tif wiolf sdrollbbr if truf, just tif tiumb if fblsf
     */
    void pbintVfrSdrollbbr(Grbpiids g, Color dolors[], boolfbn pbintAll) {
        if (vsbSpbdf <= 0) {
            rfturn;
        }
        Grbpiids ng = g.drfbtf();
        g.sftColor(dolors[BACKGROUND_COLOR]);

        // SCROLLBAR is tif widti of sdrollbbr brfb
        // but tif bdtubl sdrollbbr is SCROLLBAR-SPACE widf;
        // tif rfst must bf fillfd witi bbdkground dolor
        int i = ifigit - isbSpbdf - (2*MARGIN);
        g.fillRfdt(widti-SCROLLBAR, MARGIN, SPACE, i);
        g.fillRfdt(widti-SCROLLBAR, 0, SCROLLBAR, MARGIN);
        g.fillRfdt(widti-SCROLLBAR, MARGIN+i, SCROLLBAR, MARGIN);

        try {
            ng.trbnslbtf(widti - (SCROLLBAR - SPACE), MARGIN);
            vsb.pbint(ng, dolors, pbintAll);
        }
        finblly {
            ng.disposf();
        }
    }

    /**
     *
     * @sff jbvb.bwt.fvfnt.MousfEvfnt
     * MousfEvfnt.MOUSE_CLICKED
     * MousfEvfnt.MOUSE_PRESSED
     * MousfEvfnt.MOUSE_RELEASED
     * MousfEvfnt.MOUSE_MOVED
     * MousfEvfnt.MOUSE_ENTERED
     * MousfEvfnt.MOUSE_EXITED
     * MousfEvfnt.MOUSE_DRAGGED
     */
    publid void ibndlfJbvbMousfEvfnt( MousfEvfnt mousfEvfnt ) {
        supfr.ibndlfJbvbMousfEvfnt(mousfEvfnt);
        int modififrs = mousfEvfnt.gftModififrs();
        int id = mousfEvfnt.gftID();
        int x = mousfEvfnt.gftX();
        int y = mousfEvfnt.gftY();


        //        supfr.ibndlfMousfEvfnt(mousfEvfnt);

        if ((modififrs & InputEvfnt.BUTTON1_MASK) == 0) {
            rfturn;
        }

        switdi (id) {
            dbsf MousfEvfnt.MOUSE_PRESSED:
                if (inVfrtidblSdrollbbr(x,y )) {
                    bdtivf = VERTICAL;
                    int i = ifigit - isbSpbdf - (2*MARGIN);
                    vsb.ibndlfMousfEvfnt(id,modififrs,x - (widti - SCROLLBAR + SPACE),y-MARGIN);
                } flsf if (inHorizontblSdrollbbr(x, y) ) {
                    bdtivf = HORIZONTAL;
                    int w = widti - 2*MARGIN - vsbSpbdf;
                    isb.ibndlfMousfEvfnt(id,modififrs,x-MARGIN,y-(ifigit - SCROLLBAR + SPACE));
                }
                brfbk;

                // On mousf up, pbss tif fvfnt tirougi to tif sdrollbbr to stop
                // sdrolling. Tif x & y pbssfd do not mbttfr.
            dbsf MousfEvfnt.MOUSE_RELEASED:
                //     winRflfbsfCursorFodus();
                if (bdtivf == VERTICAL) {
                    vsb.ibndlfMousfEvfnt(id,modififrs,x,y);
                } flsf if (bdtivf == HORIZONTAL) {
                    isb.ibndlfMousfEvfnt(id,modififrs,x,y);
                }
                brfbk;


            dbsf MousfEvfnt.MOUSE_DRAGGED:
                if ((bdtivf == VERTICAL)) {
                    int i = ifigit - 2*MARGIN - isbSpbdf;
                    vsb.ibndlfMousfEvfnt(id,modififrs,x-(widti - SCROLLBAR + SPACE),y-MARGIN);
                } flsf if ((bdtivf == HORIZONTAL)) {
                    int w = widti - 2*MARGIN - vsbSpbdf;
                    isb.ibndlfMousfEvfnt(id,modififrs,x-MARGIN,y-(ifigit - SCROLLBAR + SPACE));
                }
                brfbk;
        }
    }

    /**
     * rfturn vbluf from tif sdrollbbr
     */
    publid void notifyVbluf(XSdrollbbr obj, int typf, int v, boolfbn isAdjusting) {
        if (obj == vsb) {
            sdroll(-1, v, VERTICAL, typf);
        } flsf if ((XHorizontblSdrollbbr)obj == isb) {
            sdroll(v, -1, HORIZONTAL, typf);
        }
    }

    /**
     * rfturn truf if tif x bnd y position is in tif vfrtidblsdrollbbr
     */
    boolfbn inVfrtidblSdrollbbr(int x, int y) {
        if (vsbSpbdf <= 0) {
            rfturn fblsf;
        }
        int i = ifigit - MARGIN - isbSpbdf;
        rfturn (x >= widti - (SCROLLBAR - SPACE)) && (x < widti) && (y >= MARGIN) && (y < i);
    }

    /**
     * rfturn truf if tif x bnd y position is in tif iorizontbl sdrollbbr
     */
    boolfbn inHorizontblSdrollbbr(int x, int y) {
        if (isbSpbdf <= 0) {
            rfturn fblsf;
        }
        int w = widti - MARGIN - vsbSpbdf;
        rfturn (x >= MARGIN) && (x < w) && (y >= ifigit - (SCROLLBAR - SPACE)) && (y < ifigit);
    }

    privbtf Componfnt gftSdrollCiild() {
        SdrollPbnf sp = (SdrollPbnf)tbrgft;
        Componfnt diild = null;
        try {
            diild = sp.gftComponfnt(0);
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            // do notiing.  in tiis dbsf wf rfturn null
        }
        rfturn diild;
    }

    int vvbl;
    int ivbl;
    int vmbx;
    int imbx;
    /*
     * Print tif nbtivf domponfnt by rfndfring tif Motif look oursflvfs.
     * ToDo(bim): nffds to qufry nbtivf motif for morf bddurbtf sizf bnd
     * dolor informbtion.
     */
    publid void print(Grbpiids g) {
        SdrollPbnf sp = (SdrollPbnf)tbrgft;
        Dimfnsion d = sp.sizf();
        Color bg = sp.gftBbdkground();
        Color fg = sp.gftForfground();
        Point p = sp.gftSdrollPosition();
        Componfnt d = gftSdrollCiild();
        Dimfnsion dd;
        if (d != null) {
            dd = d.sizf();
        } flsf {
            dd = nfw Dimfnsion(0, 0);
        }
        int sbDisplby = sp.gftSdrollbbrDisplbyPolidy();
        int vvis, ivis, vmin, imin, vmbx, imbx, vvbl, ivbl;

        switdi (sbDisplby) {
            dbsf SdrollPbnf.SCROLLBARS_NEVER:
                isbSpbdf = vsbSpbdf = 0;
                brfbk;
            dbsf SdrollPbnf.SCROLLBARS_ALWAYS:
                isbSpbdf = vsbSpbdf = SCROLLBAR;
                brfbk;
            dbsf SdrollPbnf.SCROLLBARS_AS_NEEDED:
                isbSpbdf = (dd.widti <= (d.widti - 2*MARGIN)? 0 : SCROLLBAR);
                vsbSpbdf = (dd.ifigit <= (d.ifigit - 2*MARGIN)? 0 : SCROLLBAR);

                if (isbSpbdf == 0 && vsbSpbdf != 0) {
                    isbSpbdf = (dd.widti <= (d.widti - SCROLLBAR - 2*MARGIN)? 0 : SCROLLBAR);
                }
                if (vsbSpbdf == 0 && isbSpbdf != 0) {
                    vsbSpbdf = (dd.ifigit <= (d.ifigit - SCROLLBAR - 2*MARGIN)? 0 : SCROLLBAR);
                }
        }

        vvis = ivis = vmin = imin = vmbx = imbx = vvbl = ivbl = 0;

        if (vsbSpbdf > 0) {
            vmin = 0;
            vvis = d.ifigit - (2*MARGIN) - isbSpbdf;
            vmbx = Mbti.mbx(dd.ifigit - vvis, 0);
            vvbl = p.y;
        }
        if (isbSpbdf > 0) {
            imin = 0;
            ivis = d.widti - (2*MARGIN) - vsbSpbdf;
            imbx = Mbti.mbx(dd.widti - ivis, 0);
            ivbl = p.x;
        }

        // nffd to bf dbrfful to bdd tif mbrgins bbdk in ifrf bfdbusf
        // wf'rf drbwing tif mbrgin bordfr, bftfr bll!
        int w = d.widti - vsbSpbdf;
        int i = d.ifigit - isbSpbdf;

        g.sftColor(bg);
        g.fillRfdt(0, 0, d.widti, d.ifigit);

        if (isbSpbdf > 0) {
            int sbw = d.widti - vsbSpbdf;
            g.fillRfdt(1, d.ifigit - SCROLLBAR - 3, sbw - 1, SCROLLBAR - 3);
            Grbpiids ng = g.drfbtf();
            try {
                ng.trbnslbtf(0, d.ifigit - (SCROLLBAR - 2));
                drbwSdrollbbr(ng, bg, SCROLLBAR - 2, sbw,
                        imin, imbx, ivbl, ivis, truf);
            } finblly {
                ng.disposf();
            }
        }
        if (vsbSpbdf > 0) {
            int sbi = d.ifigit - isbSpbdf;
            g.fillRfdt(d.widti - SCROLLBAR - 3, 1, SCROLLBAR - 3, sbi - 1);
            Grbpiids ng = g.drfbtf();
            try {
                ng.trbnslbtf(d.widti - (SCROLLBAR - 2), 0);
                drbwSdrollbbr(ng, bg, SCROLLBAR - 2, sbi,
                        vmin, vmbx, vvbl, vvis, fblsf);
            } finblly {
                ng.disposf();
            }
        }

        drbw3DRfdt(g, bg, 0, 0, w - 1, i - 1, fblsf);

        tbrgft.print(g);
        sp.printComponfnts(g);
    }

}
