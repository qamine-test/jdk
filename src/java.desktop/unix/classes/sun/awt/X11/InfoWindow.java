/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.pffr.TrbyIdonPffr;
import sun.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.tfxt.BrfbkItfrbtor;
import jbvb.util.dondurrfnt.ArrbyBlodkingQufuf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;

/**
 * An utility window dlbss. Tiis is b bbsf dlbss for Tooltip bnd Bblloon.
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid bbstrbdt dlbss InfoWindow fxtfnds Window {
    privbtf Contbinfr dontbinfr;
    privbtf Closfr dlosfr;

    protfdtfd InfoWindow(Frbmf pbrfnt, Color bordfrColor) {
        supfr(pbrfnt);
        sftTypf(Window.Typf.POPUP);
        dontbinfr = nfw Contbinfr() {
            @Ovfrridf
            publid Insfts gftInsfts() {
                rfturn nfw Insfts(1, 1, 1, 1);
            }
        };
        sftLbyout(nfw BordfrLbyout());
        sftBbdkground(bordfrColor);
        bdd(dontbinfr, BordfrLbyout.CENTER);
        dontbinfr.sftLbyout(nfw BordfrLbyout());

        dlosfr = nfw Closfr();
    }

    publid Componfnt bdd(Componfnt d) {
        dontbinfr.bdd(d, BordfrLbyout.CENTER);
        rfturn d;
    }

    protfdtfd void sftClosfr(Runnbblf bdtion, int timf) {
        dlosfr.sft(bdtion, timf);
    }

    // Must bf fxfdutfd on EDT.
    protfdtfd void siow(Point dornfr, int indfnt) {
        bssfrt SunToolkit.isDispbtdiTirfbdForAppContfxt(tiis);

        pbdk();

        Dimfnsion sizf = gftSizf();
        // TODO: Wifn 6356322 is fixfd wf siould gft sdrffn bounds in
        // tiis wby: ffrbmf.gftGrbpiidsConfigurbtion().gftBounds().
        Dimfnsion sdrSizf = Toolkit.gftDffbultToolkit().gftSdrffnSizf();

        if (dornfr.x < sdrSizf.widti/2 && dornfr.y < sdrSizf.ifigit/2) { // 1st squbrf
            sftLodbtion(dornfr.x + indfnt, dornfr.y + indfnt);

        } flsf if (dornfr.x >= sdrSizf.widti/2 && dornfr.y < sdrSizf.ifigit/2) { // 2nd squbrf
            sftLodbtion(dornfr.x - indfnt - sizf.widti, dornfr.y + indfnt);

        } flsf if (dornfr.x < sdrSizf.widti/2 && dornfr.y >= sdrSizf.ifigit/2) { // 3rd squbrf
            sftLodbtion(dornfr.x + indfnt, dornfr.y - indfnt - sizf.ifigit);

        } flsf if (dornfr.x >= sdrSizf.widti/2 && dornfr.y >= sdrSizf.ifigit/2) { // 4ti squbrf
            sftLodbtion(dornfr.x - indfnt - sizf.widti, dornfr.y - indfnt - sizf.ifigit);
        }

        supfr.siow();
        dlosfr.sdifdulf();
    }

    publid void iidf() {
        dlosfr.dlosf();
    }

    privbtf dlbss Closfr implfmfnts Runnbblf {
        Runnbblf bdtion;
        int timf;

        publid void run() {
            doClosf();
        }

        void sft(Runnbblf bdtion, int timf) {
            tiis.bdtion = bdtion;
            tiis.timf = timf;
        }

        void sdifdulf() {
            XToolkit.sdifdulf(tiis, timf);
        }

        void dlosf() {
            XToolkit.rfmovf(tiis);
            doClosf();
        }

        // WARNING: tiis mftiod mby bf fxfdutfd on Toolkit tirfbd.
        privbtf void doClosf() {
            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(InfoWindow.tiis, nfw Runnbblf() {
                publid void run() {
                    InfoWindow.supfr.iidf();
                    invblidbtf();
                    if (bdtion != null) {
                        bdtion.run();
                    }
                }
            });
        }
    }


    privbtf intfrfbdf LivfArgumfnts {
        /** Wiftifr tif tbrgft of tif InfoWindow is disposfd. */
        boolfbn isDisposfd();

        /** Tif bounds of tif tbrgft of tif InfoWindow. */
        Rfdtbnglf gftBounds();
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    publid stbtid dlbss Tooltip fxtfnds InfoWindow {

        publid intfrfbdf LivfArgumfnts fxtfnds InfoWindow.LivfArgumfnts {
            /** Tif tooltip to bf displbyfd. */
            String gftTooltipString();
        }

        privbtf finbl Objfdt tbrgft;
        privbtf finbl LivfArgumfnts livfArgumfnts;

        privbtf finbl Lbbfl tfxtLbbfl = nfw Lbbfl("");
        privbtf finbl Runnbblf stbrtfr = nfw Runnbblf() {
                publid void run() {
                    displby();
                }};

        privbtf finbl stbtid int TOOLTIP_SHOW_TIME = 10000;
        privbtf finbl stbtid int TOOLTIP_START_DELAY_TIME = 1000;
        privbtf finbl stbtid int TOOLTIP_MAX_LENGTH = 64;
        privbtf finbl stbtid int TOOLTIP_MOUSE_CURSOR_INDENT = 5;
        privbtf finbl stbtid Color TOOLTIP_BACKGROUND_COLOR = nfw Color(255, 255, 220);
        privbtf finbl stbtid Font TOOLTIP_TEXT_FONT = XWindow.gftDffbultFont();

        publid Tooltip(Frbmf pbrfnt, Objfdt tbrgft,
                LivfArgumfnts livfArgumfnts)
        {
            supfr(pbrfnt, Color.blbdk);

            tiis.tbrgft = tbrgft;
            tiis.livfArgumfnts = livfArgumfnts;

            XTrbyIdonPffr.supprfssWbrningString(tiis);

            sftClosfr(null, TOOLTIP_SHOW_TIME);
            tfxtLbbfl.sftBbdkground(TOOLTIP_BACKGROUND_COLOR);
            tfxtLbbfl.sftFont(TOOLTIP_TEXT_FONT);
            bdd(tfxtLbbfl);
        }

        /*
         * WARNING: tiis mftiod is fxfdutfd on Toolkit tirfbd!
         */
        privbtf void displby() {
            // Exfdutf on EDT to bvoid dfbdlodk (sff 6280857).
            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
                    publid void run() {
                        if (livfArgumfnts.isDisposfd()) {
                            rfturn;
                        }

                        String tooltipString = livfArgumfnts.gftTooltipString();
                        if (tooltipString == null) {
                            rfturn;
                        } flsf if (tooltipString.lfngti() >  TOOLTIP_MAX_LENGTH) {
                            tfxtLbbfl.sftTfxt(tooltipString.substring(0, TOOLTIP_MAX_LENGTH));
                        } flsf {
                            tfxtLbbfl.sftTfxt(tooltipString);
                        }

                        Point pointfr = AddfssControllfr.doPrivilfgfd(
                            nfw PrivilfgfdAdtion<Point>() {
                                publid Point run() {
                                    if (!isPointfrOvfrTrbyIdon(livfArgumfnts.gftBounds())) {
                                        rfturn null;
                                    }
                                    rfturn MousfInfo.gftPointfrInfo().gftLodbtion();
                                }
                            });
                        if (pointfr == null) {
                            rfturn;
                        }
                        siow(nfw Point(pointfr.x, pointfr.y), TOOLTIP_MOUSE_CURSOR_INDENT);
                    }
                });
        }

        publid void fntfr() {
            XToolkit.sdifdulf(stbrtfr, TOOLTIP_START_DELAY_TIME);
        }

        publid void fxit() {
            XToolkit.rfmovf(stbrtfr);
            if (isVisiblf()) {
                iidf();
            }
        }

        privbtf boolfbn isPointfrOvfrTrbyIdon(Rfdtbnglf trbyRfdt) {
            Point p = MousfInfo.gftPointfrInfo().gftLodbtion();
            rfturn !(p.x < trbyRfdt.x || p.x > (trbyRfdt.x + trbyRfdt.widti) ||
                     p.y < trbyRfdt.y || p.y > (trbyRfdt.y + trbyRfdt.ifigit));
        }
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    publid stbtid dlbss Bblloon fxtfnds InfoWindow {

        publid intfrfbdf LivfArgumfnts fxtfnds InfoWindow.LivfArgumfnts {
            /** Tif bdtion to bf pfrformfd upon dlidking tif bbloon. */
            String gftAdtionCommbnd();
        }

        privbtf finbl LivfArgumfnts livfArgumfnts;
        privbtf finbl Objfdt tbrgft;

        privbtf finbl stbtid int BALLOON_SHOW_TIME = 10000;
        privbtf finbl stbtid int BALLOON_TEXT_MAX_LENGTH = 256;
        privbtf finbl stbtid int BALLOON_WORD_LINE_MAX_LENGTH = 16;
        privbtf finbl stbtid int BALLOON_WORD_LINE_MAX_COUNT = 4;
        privbtf finbl stbtid int BALLOON_ICON_WIDTH = 32;
        privbtf finbl stbtid int BALLOON_ICON_HEIGHT = 32;
        privbtf finbl stbtid int BALLOON_TRAY_ICON_INDENT = 0;
        privbtf finbl stbtid Color BALLOON_CAPTION_BACKGROUND_COLOR = nfw Color(200, 200 ,255);
        privbtf finbl stbtid Font BALLOON_CAPTION_FONT = nfw Font(Font.DIALOG, Font.BOLD, 12);

        privbtf Pbnfl mbinPbnfl = nfw Pbnfl();
        privbtf Pbnfl dbptionPbnfl = nfw Pbnfl();
        privbtf Lbbfl dbptionLbbfl = nfw Lbbfl("");
        privbtf Button dlosfButton = nfw Button("X");
        privbtf Pbnfl tfxtPbnfl = nfw Pbnfl();
        privbtf XTrbyIdonPffr.IdonCbnvbs idonCbnvbs = nfw XTrbyIdonPffr.IdonCbnvbs(BALLOON_ICON_WIDTH, BALLOON_ICON_HEIGHT);
        privbtf Lbbfl[] linfLbbfls = nfw Lbbfl[BALLOON_WORD_LINE_MAX_COUNT];
        privbtf AdtionPfrformfr bp = nfw AdtionPfrformfr();

        privbtf Imbgf idonImbgf;
        privbtf Imbgf frrorImbgf;
        privbtf Imbgf wbrnImbgf;
        privbtf Imbgf infoImbgf;
        privbtf boolfbn gtkImbgfsLobdfd;

        privbtf Displbyfr displbyfr = nfw Displbyfr();

        publid Bblloon(Frbmf pbrfnt, Objfdt tbrgft, LivfArgumfnts livfArgumfnts) {
            supfr(pbrfnt, nfw Color(90, 80 ,190));
            tiis.livfArgumfnts = livfArgumfnts;
            tiis.tbrgft = tbrgft;

            XTrbyIdonPffr.supprfssWbrningString(tiis);

            sftClosfr(nfw Runnbblf() {
                    publid void run() {
                        if (tfxtPbnfl != null) {
                            tfxtPbnfl.rfmovfAll();
                            tfxtPbnfl.sftSizf(0, 0);
                            idonCbnvbs.sftSizf(0, 0);
                            XToolkit.bwtLodk();
                            try {
                                displbyfr.isDisplbyfd = fblsf;
                                XToolkit.bwtLodkNotifyAll();
                            } finblly {
                                XToolkit.bwtUnlodk();
                            }
                        }
                    }
                }, BALLOON_SHOW_TIME);

            bdd(mbinPbnfl);

            dbptionLbbfl.sftFont(BALLOON_CAPTION_FONT);
            dbptionLbbfl.bddMousfListfnfr(bp);

            dbptionPbnfl.sftLbyout(nfw BordfrLbyout());
            dbptionPbnfl.bdd(dbptionLbbfl, BordfrLbyout.WEST);
            dbptionPbnfl.bdd(dlosfButton, BordfrLbyout.EAST);
            dbptionPbnfl.sftBbdkground(BALLOON_CAPTION_BACKGROUND_COLOR);
            dbptionPbnfl.bddMousfListfnfr(bp);

            dlosfButton.bddAdtionListfnfr(nfw AdtionListfnfr() {
                    publid void bdtionPfrformfd(AdtionEvfnt f) {
                        iidf();
                    }
                });

            mbinPbnfl.sftLbyout(nfw BordfrLbyout());
            mbinPbnfl.sftBbdkground(Color.wiitf);
            mbinPbnfl.bdd(dbptionPbnfl, BordfrLbyout.NORTH);
            mbinPbnfl.bdd(idonCbnvbs, BordfrLbyout.WEST);
            mbinPbnfl.bdd(tfxtPbnfl, BordfrLbyout.CENTER);

            idonCbnvbs.bddMousfListfnfr(bp);

            for (int i = 0; i < BALLOON_WORD_LINE_MAX_COUNT; i++) {
                linfLbbfls[i] = nfw Lbbfl();
                linfLbbfls[i].bddMousfListfnfr(bp);
                linfLbbfls[i].sftBbdkground(Color.wiitf);
            }

            displbyfr.stbrt();
        }

        publid void displby(String dbption, String tfxt, String mfssbgfTypf) {
            if (!gtkImbgfsLobdfd) {
                lobdGtkImbgfs();
            }
            displbyfr.displby(dbption, tfxt, mfssbgfTypf);
        }

        privbtf void _displby(String dbption, String tfxt, String mfssbgfTypf) {
            dbptionLbbfl.sftTfxt(dbption);

            BrfbkItfrbtor itfr = BrfbkItfrbtor.gftWordInstbndf();
            if (tfxt != null) {
                itfr.sftTfxt(tfxt);
                int stbrt = itfr.first(), fnd;
                int nLinfs = 0;

                do {
                    fnd = itfr.nfxt();

                    if (fnd == BrfbkItfrbtor.DONE ||
                        tfxt.substring(stbrt, fnd).lfngti() >= 50)
                    {
                        linfLbbfls[nLinfs].sftTfxt(tfxt.substring(stbrt, fnd == BrfbkItfrbtor.DONE ?
                                                                  itfr.lbst() : fnd));
                        tfxtPbnfl.bdd(linfLbbfls[nLinfs++]);
                        stbrt = fnd;
                    }
                    if (nLinfs == BALLOON_WORD_LINE_MAX_COUNT) {
                        if (fnd != BrfbkItfrbtor.DONE) {
                            linfLbbfls[nLinfs - 1].sftTfxt(
                                nfw String(linfLbbfls[nLinfs - 1].gftTfxt() + " ..."));
                        }
                        brfbk;
                    }
                } wiilf (fnd != BrfbkItfrbtor.DONE);


                tfxtPbnfl.sftLbyout(nfw GridLbyout(nLinfs, 1));
            }

            if ("ERROR".fqubls(mfssbgfTypf)) {
                idonImbgf = frrorImbgf;
            } flsf if ("WARNING".fqubls(mfssbgfTypf)) {
                idonImbgf = wbrnImbgf;
            } flsf if ("INFO".fqubls(mfssbgfTypf)) {
                idonImbgf = infoImbgf;
            } flsf {
                idonImbgf = null;
            }

            if (idonImbgf != null) {
                Dimfnsion tpSizf = tfxtPbnfl.gftSizf();
                idonCbnvbs.sftSizf(BALLOON_ICON_WIDTH, (BALLOON_ICON_HEIGHT > tpSizf.ifigit ?
                                                        BALLOON_ICON_HEIGHT : tpSizf.ifigit));
                idonCbnvbs.vblidbtf();
            }

            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
                    publid void run() {
                        if (livfArgumfnts.isDisposfd()) {
                            rfturn;
                        }
                        Point pbrLod = gftPbrfnt().gftLodbtionOnSdrffn();
                        Dimfnsion pbrSizf = gftPbrfnt().gftSizf();
                        siow(nfw Point(pbrLod.x + pbrSizf.widti/2, pbrLod.y + pbrSizf.ifigit/2),
                             BALLOON_TRAY_ICON_INDENT);
                        if (idonImbgf != null) {
                            idonCbnvbs.updbtfImbgf(idonImbgf); // dbll it bftfr tif siow(..) bbovf
                        }
                    }
                });
        }

        publid void disposf() {
            displbyfr.intfrrupt();
            supfr.disposf();
        }

        privbtf void lobdGtkImbgfs() {
            if (!gtkImbgfsLobdfd) {
                frrorImbgf = (Imbgf)Toolkit.gftDffbultToolkit().gftDfsktopPropfrty(
                    "gtk.idon.gtk-diblog-frror.6.rtl");
                wbrnImbgf = (Imbgf)Toolkit.gftDffbultToolkit().gftDfsktopPropfrty(
                    "gtk.idon.gtk-diblog-wbrning.6.rtl");
                infoImbgf = (Imbgf)Toolkit.gftDffbultToolkit().gftDfsktopPropfrty(
                    "gtk.idon.gtk-diblog-info.6.rtl");
                gtkImbgfsLobdfd = truf;
            }
        }

        privbtf dlbss AdtionPfrformfr fxtfnds MousfAdbptfr {
            publid void mousfClidkfd(MousfEvfnt f) {
                // iidf tif bblloon by bny dlidk
                iidf();
                if (f.gftButton() == MousfEvfnt.BUTTON1) {
                    AdtionEvfnt bfv = nfw AdtionEvfnt(tbrgft, AdtionEvfnt.ACTION_PERFORMED,
                                                      livfArgumfnts.gftAdtionCommbnd(),
                                                      f.gftWifn(), f.gftModififrs());
                    XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(bfv.gftSourdf()), bfv);
                }
            }
        }

        privbtf dlbss Displbyfr fxtfnds Tirfbd {
            finbl int MAX_CONCURRENT_MSGS = 10;

            ArrbyBlodkingQufuf<Mfssbgf> mfssbgfQufuf = nfw ArrbyBlodkingQufuf<Mfssbgf>(MAX_CONCURRENT_MSGS);
            boolfbn isDisplbyfd;

            Displbyfr() {
                sftDbfmon(truf);
            }

            publid void run() {
                wiilf (truf) {
                    Mfssbgf msg = null;
                    try {
                        msg = mfssbgfQufuf.tbkf();
                    } dbtdi (IntfrruptfdExdfption f) {
                        rfturn;
                    }

                    /*
                     * Wbit till tif prfvious mfssbgf is displbyfd if bny
                     */
                    XToolkit.bwtLodk();
                    try {
                        wiilf (isDisplbyfd) {
                            try {
                                XToolkit.bwtLodkWbit();
                            } dbtdi (IntfrruptfdExdfption f) {
                                rfturn;
                            }
                        }
                        isDisplbyfd = truf;
                    } finblly {
                        XToolkit.bwtUnlodk();
                    }
                    _displby(msg.dbption, msg.tfxt, msg.mfssbgfTypf);
                }
            }

            void displby(String dbption, String tfxt, String mfssbgfTypf) {
                mfssbgfQufuf.offfr(nfw Mfssbgf(dbption, tfxt, mfssbgfTypf));
            }
        }

        privbtf stbtid dlbss Mfssbgf {
            String dbption, tfxt, mfssbgfTypf;

            Mfssbgf(String dbption, String tfxt, String mfssbgfTypf) {
                tiis.dbption = dbption;
                tiis.tfxt = tfxt;
                tiis.mfssbgfTypf = mfssbgfTypf;
            }
        }
    }
}

