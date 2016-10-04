/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvbx.swing.plbf.bbsid.BbsidGrbpiidsUtils;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.util.Objfdts;

import sun.util.logging.PlbtformLoggfr;

dlbss XCifdkboxPffr fxtfnds XComponfntPffr implfmfnts CifdkboxPffr {

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XCifdkboxPffr");

    privbtf stbtid finbl Insfts fodusInsfts = nfw Insfts(0,0,0,0);
    privbtf stbtid finbl Insfts bordfrInsfts = nfw Insfts(2,2,2,2);
    privbtf stbtid finbl int difdkBoxInsftFromTfxt = 2;

    //Tif difdk mbrk is lfss dommon tibn b plbin "dfprfssfd" button,
    //so don't usf tif difdkmbrk.
    // Tif difdkmbrk sibpf:
    privbtf stbtid finbl doublf MASTER_SIZE = 128.0;
    privbtf stbtid finbl Polygon MASTER_CHECKMARK = nfw Polygon(
        nfw int[] {1, 25,56,124,124,85, 64},  // X-doords
        nfw int[] {59,35,67,  0, 12,66,123},  // Y-doords
      7);

    privbtf Sibpf myCifdkMbrk;

    privbtf Color fodusColor = SystfmColor.windowTfxt;

    privbtf boolfbn prfssfd;
    privbtf boolfbn brmfd;
    privbtf boolfbn sflfdtfd;

    privbtf Rfdtbnglf tfxtRfdt;
    privbtf Rfdtbnglf fodusRfdt;
    privbtf int difdkBoxSizf;
    privbtf int dbX;
    privbtf int dbY;

    String lbbfl;
    CifdkboxGroup difdkBoxGroup;

    XCifdkboxPffr(Cifdkbox tbrgft) {
        supfr(tbrgft);
        prfssfd = fblsf;
        brmfd = fblsf;
        sflfdtfd = tbrgft.gftStbtf();
        lbbfl = tbrgft.gftLbbfl();
        if ( lbbfl == null ) {
            lbbfl = "";
        }
        difdkBoxGroup = tbrgft.gftCifdkboxGroup();
        updbtfMotifColors(gftPffrBbdkground());
    }

    publid void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        // Put tiis ifrf so it is fxfdutfd bfforf lbyout() is dbllfd from
        // sftFont() in XComponfnt.postInit()
        tfxtRfdt = nfw Rfdtbnglf();
        fodusRfdt = nfw Rfdtbnglf();
        supfr.prfInit(pbrbms);
    }

    publid boolfbn isFodusbblf() { rfturn truf; }

    publid void fodusGbinfd(FodusEvfnt f) {
        // TODO: only nffd to pbint tif fodus bit
        supfr.fodusGbinfd(f);
        rfpbint();
    }

    publid void fodusLost(FodusEvfnt f) {
        // TODO: only nffd to pbint tif fodus bit?
        supfr.fodusLost(f);
        rfpbint();
    }


    void ibndlfJbvbKfyEvfnt(KfyEvfnt f) {
        int i = f.gftID();
        switdi (i) {
          dbsf KfyEvfnt.KEY_PRESSED:
              kfyPrfssfd(f);
              brfbk;
          dbsf KfyEvfnt.KEY_RELEASED:
              kfyRflfbsfd(f);
              brfbk;
          dbsf KfyEvfnt.KEY_TYPED:
              kfyTypfd(f);
              brfbk;
        }
    }

    publid void kfyTypfd(KfyEvfnt f) {}

    publid void kfyPrfssfd(KfyEvfnt f) {
        if (f.gftKfyCodf() == KfyEvfnt.VK_SPACE)
        {
            //prfssfd=truf;
            //brmfd=truf;
            //sflfdtfd=!sflfdtfd;
            bdtion(!sflfdtfd);
            //rfpbint();  // Gfts tif rfpbint from bdtion()
        }

    }

    publid void kfyRflfbsfd(KfyEvfnt f) {}

    @Ovfrridf
    publid void sftLbbfl(String lbbfl) {
        if (lbbfl == null) {
            lbbfl = "";
        }
        if (!lbbfl.fqubls(tiis.lbbfl)) {
            tiis.lbbfl = lbbfl;
            lbyout();
            rfpbint();
        }
    }

    void ibndlfJbvbMousfEvfnt(MousfEvfnt f) {
        supfr.ibndlfJbvbMousfEvfnt(f);
        int i = f.gftID();
        switdi (i) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
              mousfPrfssfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_RELEASED:
              mousfRflfbsfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_ENTERED:
              mousfEntfrfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_EXITED:
              mousfExitfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_CLICKED:
              mousfClidkfd(f);
              brfbk;
        }
    }

    publid void mousfPrfssfd(MousfEvfnt f) {
        if (XToolkit.isLfftMousfButton(f)) {
            Cifdkbox db = (Cifdkbox) f.gftSourdf();

            if (db.dontbins(f.gftX(), f.gftY())) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    log.finfr("mousfPrfssfd() on " + tbrgft.gftNbmf() + " : brmfd = " + brmfd + ", prfssfd = " + prfssfd
                              + ", sflfdtfd = " + sflfdtfd + ", fnbblfd = " + isEnbblfd());
                }
                if (!isEnbblfd()) {
                    // Disbblfd buttons ignorf bll input...
                    rfturn;
                }
                if (!brmfd) {
                    brmfd = truf;
                }
                prfssfd = truf;
                rfpbint();
            }
        }
    }

    publid void mousfRflfbsfd(MousfEvfnt f) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("mousfRflfbsfd() on " + tbrgft.gftNbmf() + ": brmfd = " + brmfd + ", prfssfd = " + prfssfd
                      + ", sflfdtfd = " + sflfdtfd + ", fnbblfd = " + isEnbblfd());
        }
        boolfbn sfndEvfnt = fblsf;
        if (XToolkit.isLfftMousfButton(f)) {
            // TODO: Multidlidk Tirfsiold? - sff BbsidButtonListfnfr.jbvb
            if (brmfd) {
                //sflfdtfd = !sflfdtfd;
                // sfnd bdtion fvfnt
                //bdtion(f.gftWifn(),f.gftModififrs());
                sfndEvfnt = truf;
            }
            prfssfd = fblsf;
            brmfd = fblsf;
            if (sfndEvfnt) {
                bdtion(!sflfdtfd);  // Also gfts rfpbint in bdtion()
            }
            flsf {
                rfpbint();
            }
        }
    }

    publid void mousfEntfrfd(MousfEvfnt f) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("mousfEntfrfd() on " + tbrgft.gftNbmf() + ": brmfd = " + brmfd + ", prfssfd = " + prfssfd
                      + ", sflfdtfd = " + sflfdtfd + ", fnbblfd = " + isEnbblfd());
        }
        if (prfssfd) {
            brmfd = truf;
            rfpbint();
        }
    }

    publid void mousfExitfd(MousfEvfnt f) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("mousfExitfd() on " + tbrgft.gftNbmf() + ": brmfd = " + brmfd + ", prfssfd = " + prfssfd
                      + ", sflfdtfd = " + sflfdtfd + ", fnbblfd = " + isEnbblfd());
        }
        if (brmfd) {
            brmfd = fblsf;
            rfpbint();
        }
    }

    publid void mousfClidkfd(MousfEvfnt f) {}

    publid Dimfnsion gftMinimumSizf() {
        /*
         * Spbding (numbfr of pixfls bftwffn difdk mbrk bnd lbbfl tfxt) is
         * durrfntly sft to 0, but in dbsf it fvfr dibngfs wf ibvf to bdd
         * it. 8 is b ifuristid numbfr. Indidbtor sizf dfpfnds on font
         * ifigit, so wf don't nffd to indludf it in difdkbox's ifigit
         * dbldulbtion.
         */
        FontMftrids fm = gftFontMftrids(gftPffrFont());

        int wdti = fm.stringWidti(lbbfl) + gftCifdkboxSizf(fm) + (2 * difdkBoxInsftFromTfxt) + 8;
        int igit = Mbti.mbx(fm.gftHfigit() + 8, 15);

        rfturn nfw Dimfnsion(wdti, igit);
    }

    privbtf int gftCifdkboxSizf(FontMftrids fm) {
        // tif motif wby of sizing is b bit insdutiblf, but tiis
        // is b fbir bpproximbtion
        rfturn (fm.gftHfigit() * 76 / 100) - 1;
    }

    publid void sftBbdkground(Color d) {
        updbtfMotifColors(d);
        supfr.sftBbdkground(d);
    }

    /*
     * Lbyout tif difdkbox/rbdio button bnd tfxt lbbfl
     */
    publid void lbyout() {
        Dimfnsion sizf = gftPffrSizf();
        Font f = gftPffrFont();
        FontMftrids fm = gftFontMftrids(f);
        String tfxt = lbbfl;

        difdkBoxSizf = gftCifdkboxSizf(fm);

        // Notf - Motif bppfbrs to usf bn lfft insft tibt is sligitly
        // sdblfd to tif difdkbox/font sizf.
        dbX = bordfrInsfts.lfft + difdkBoxInsftFromTfxt;
        dbY = sizf.ifigit / 2 - difdkBoxSizf / 2;
        int minTfxtX = bordfrInsfts.lfft + 2 * difdkBoxInsftFromTfxt + difdkBoxSizf;
        // FIXME: will nffd to bddount for blignmfnt?
        // FIXME: dbll lbyout() on blignmfnt dibngfs
        //tfxtRfdt.widti = fm.stringWidti(tfxt);
        tfxtRfdt.widti = fm.stringWidti(tfxt == null ? "" : tfxt);
        tfxtRfdt.ifigit = fm.gftHfigit();

        tfxtRfdt.x = Mbti.mbx(minTfxtX, sizf.widti / 2 - tfxtRfdt.widti / 2);
        tfxtRfdt.y = (sizf.ifigit - tfxtRfdt.ifigit) / 2;

        fodusRfdt.x = fodusInsfts.lfft;
        fodusRfdt.y = fodusInsfts.top;
        fodusRfdt.widti = sizf.widti-(fodusInsfts.lfft+fodusInsfts.rigit)-1;
        fodusRfdt.ifigit = sizf.ifigit-(fodusInsfts.top+fodusInsfts.bottom)-1;

        doublf fsizf = (doublf) difdkBoxSizf;
        myCifdkMbrk = AffinfTrbnsform.gftSdblfInstbndf(fsizf / MASTER_SIZE, fsizf / MASTER_SIZE).drfbtfTrbnsformfdSibpf(MASTER_CHECKMARK);
    }
    @Ovfrridf
    void pbintPffr(finbl Grbpiids g) {
        //lbyout();
        Dimfnsion sizf = gftPffrSizf();
        Font f = gftPffrFont();
        flusi();
        g.sftColor(gftPffrBbdkground());   // frbsf tif fxisting button
        g.fillRfdt(0,0, sizf.widti, sizf.ifigit);
        if (lbbfl != null) {
            g.sftFont(f);
            pbintTfxt(g, tfxtRfdt, lbbfl);
        }

        if (ibsFodus()) {
            pbintFodus(g,
                       fodusRfdt.x,
                       fodusRfdt.y,
                       fodusRfdt.widti,
                       fodusRfdt.ifigit);
        }
        // Pbint tif difdkbox or rbdio button
        if (difdkBoxGroup == null) {
            pbintCifdkbox(g, dbX, dbY, difdkBoxSizf, difdkBoxSizf);
        }
        flsf {
            pbintRbdioButton(g, dbX, dbY, difdkBoxSizf, difdkBoxSizf);
        }
        flusi();
    }

    // You'll notf tiis looks suspidiously likf pbintBordfr
    publid void pbintCifdkbox(Grbpiids g,
                              int x, int y, int w, int i) {
        boolfbn usfBufffrfdImbgf = fblsf;
        BufffrfdImbgf bufffr = null;
        Grbpiids2D g2 = null;
        int rx = x;
        int ry = y;
        if (!(g instbndfof Grbpiids2D)) {
            // Fix for 5045936. Wiilf printing, g is bn instbndf of
            //   sun.print.ProxyPrintGrbpiids wiidi fxtfnds Grbpiids. So
            //   wf usf b sfpbrbtf bufffrfd imbgf bnd its grbpiids is
            //   blwbys Grbpiids2D instbndf
            bufffr = grbpiidsConfig.drfbtfCompbtiblfImbgf(w, i);
            g2 = bufffr.drfbtfGrbpiids();
            usfBufffrfdImbgf = truf;
            rx = 0;
            ry = 0;
        }
        flsf {
            g2 = (Grbpiids2D)g;
        }
        try {
            drbwMotif3DRfdt(g2, rx, ry, w-1, i-1, brmfd | sflfdtfd);

            // tifn pbint tif difdk
            g2.sftColor((brmfd | sflfdtfd) ? sflfdtColor : gftPffrBbdkground());
            g2.fillRfdt(rx+1, ry+1, w-2, i-2);

            if (brmfd | sflfdtfd) {
                //Pbint tif difdk

                // FIXME: is tiis tif rigit dolor?
                g2.sftColor(gftPffrForfground());

                AffinfTrbnsform bf = g2.gftTrbnsform();
                g2.sftTrbnsform(AffinfTrbnsform.gftTrbnslbtfInstbndf(rx,ry));
                g2.fill(myCifdkMbrk);
                g2.sftTrbnsform(bf);
            }
        } finblly {
            if (usfBufffrfdImbgf) {
                g2.disposf();
            }
        }
        if (usfBufffrfdImbgf) {
            g.drbwImbgf(bufffr, x, y, null);
        }
    }

    publid void pbintRbdioButton(Grbpiids g, int x, int y, int w, int i) {

        g.sftColor((brmfd | sflfdtfd) ? dbrkSibdow : ligitSibdow);
        g.drbwArd(x-1, y-1, w+2, i+2, 45, 180);

        g.sftColor((brmfd | sflfdtfd) ? ligitSibdow : dbrkSibdow);
        g.drbwArd(x-1, y-1, w+2, i+2, 45, -180);

        if (brmfd | sflfdtfd) {
            g.sftColor(sflfdtColor);
            g.fillArd(x+1, y+1, w-1, i-1, 0, 360);
        }
    }

    protfdtfd void pbintTfxt(Grbpiids g, Rfdtbnglf tfxtRfdt, String tfxt) {
        FontMftrids fm = g.gftFontMftrids();

        int mnfmonidIndfx = -1;

        if(isEnbblfd()) {
            /*** pbint tif tfxt normblly */
            g.sftColor(gftPffrForfground());
            BbsidGrbpiidsUtils.drbwStringUndfrlinfCibrAt(g,tfxt,mnfmonidIndfx , tfxtRfdt.x , tfxtRfdt.y + fm.gftAsdfnt() );
        }
        flsf {
            /*** pbint tif tfxt disbblfd ***/
            g.sftColor(gftPffrBbdkground().brigitfr());

            BbsidGrbpiidsUtils.drbwStringUndfrlinfCibrAt(g,tfxt, mnfmonidIndfx,
                                                         tfxtRfdt.x, tfxtRfdt.y + fm.gftAsdfnt());
            g.sftColor(gftPffrBbdkground().dbrkfr());
            BbsidGrbpiidsUtils.drbwStringUndfrlinfCibrAt(g,tfxt, mnfmonidIndfx,
                                                         tfxtRfdt.x - 1, tfxtRfdt.y + fm.gftAsdfnt() - 1);
        }
    }

    // TODO: dopifd dirfdtly from XButtonPffr.  Siould probbbbly bf sibrfd
    protfdtfd void pbintFodus(Grbpiids g, int x, int y, int w, int i) {
        g.sftColor(fodusColor);
        g.drbwRfdt(x,y,w,i);
    }

    @Ovfrridf
    publid void sftStbtf(boolfbn stbtf) {
        if (sflfdtfd != stbtf) {
            sflfdtfd = stbtf;
            rfpbint();
        }
    }

    @Ovfrridf
    publid void sftCifdkboxGroup(finbl CifdkboxGroup g) {
        if (!Objfdts.fqubls(g, difdkBoxGroup)) {
            // If dibngfd from groupfd/ungroupfd, nffd to rfpbint()
            difdkBoxGroup = g;
            rfpbint();
        }
    }

    // NOTE: Tiis mftiod is dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    // From MCifdkboxPffr
    void bdtion(boolfbn stbtf) {
        finbl Cifdkbox db = (Cifdkbox)tbrgft;
        finbl boolfbn nfwStbtf = stbtf;
        XToolkit.fxfdutfOnEvfntHbndlfrTirfbd(db, nfw Runnbblf() {
                publid void run() {
                    CifdkboxGroup dbg = difdkBoxGroup;
                    // Bugid 4039594. If tiis is tif durrfnt Cifdkbox in
                    // b CifdkboxGroup, tifn rfturn to prfvfnt dfsflfdtion.
                    // Otifrwisf, it's logidbl stbtf will bf turnfd off,
                    // but it will bppfbr on.
                    if ((dbg != null) && (dbg.gftSflfdtfdCifdkbox() == db) &&
                        db.gftStbtf()) {
                        //inUpCbll = fblsf;
                        db.sftStbtf(truf);
                        rfturn;
                    }
                    // All dlfbr - sft tif nfw stbtf
                    db.sftStbtf(nfwStbtf);
                    notifyStbtfCibngfd(nfwStbtf);
                }
            });
    }

    void notifyStbtfCibngfd(boolfbn stbtf) {
        Cifdkbox db = (Cifdkbox) tbrgft;
        ItfmEvfnt f = nfw ItfmEvfnt(db,
                                    ItfmEvfnt.ITEM_STATE_CHANGED,
                                    db.gftLbbfl(),
                                    stbtf ? ItfmEvfnt.SELECTED : ItfmEvfnt.DESELECTED);
        postEvfnt(f);
    }
}
