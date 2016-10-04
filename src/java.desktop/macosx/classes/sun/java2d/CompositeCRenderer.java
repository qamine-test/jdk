/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

import jbvb.bwt.*;
import jbvb.bwt.font.*;
import jbvb.bwt.gfom.*;
import jbvb.bwt.imbgf.*;

import sun.bwt.imbgf.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipf.*;

publid dlbss CompositfCRfndfrfr fxtfnds CRfndfrfr implfmfnts PixflDrbwPipf, PixflFillPipf, SibpfDrbwPipf, DrbwImbgfPipf, TfxtPipf {
    finbl stbtid int fPbdding = 4;
    finbl stbtid int fPbddingHblf = fPbdding / 2;

    privbtf stbtid AffinfTrbnsform sIdfntityMbtrix = nfw AffinfTrbnsform();

    AffinfTrbnsform SibpfTM = nfw AffinfTrbnsform();
    Rfdtbnglf2D SibpfBounds = nfw Rfdtbnglf2D.Flobt();

    Linf2D linf = nfw Linf2D.Flobt();
    Rfdtbnglf2D rfdtbnglf = nfw Rfdtbnglf2D.Flobt();
    RoundRfdtbnglf2D roundrfdtbnglf = nfw RoundRfdtbnglf2D.Flobt();
    Ellipsf2D fllipsf = nfw Ellipsf2D.Flobt();
    Ard2D brd = nfw Ard2D.Flobt();

    publid syndironizfd void drbwLinf(SunGrbpiids2D sg2d, int x1, int y1, int x2, int y2) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        linf.sftLinf(x1, y1, x2, y2);

        drbw(sg2d, linf);
    }

    publid syndironizfd void drbwRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        rfdtbnglf.sftRfdt(x, y, widti, ifigit);

        drbw(sg2d, rfdtbnglf);
    }

    publid syndironizfd void drbwRoundRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int brdWidti, int brdHfigit) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        roundrfdtbnglf.sftRoundRfdt(x, y, widti, ifigit, brdWidti, brdHfigit);

        drbw(sg2d, roundrfdtbnglf);
    }

    publid syndironizfd void drbwOvbl(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        fllipsf.sftFrbmf(x, y, widti, ifigit);

        drbw(sg2d, fllipsf);
    }

    publid syndironizfd void drbwArd(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int stbrtAnglf, int brdAnglf) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        brd.sftArd(x, y, widti, ifigit, stbrtAnglf, brdAnglf, Ard2D.OPEN);

        drbw(sg2d, brd);
    }

    publid syndironizfd void drbwPolylinf(SunGrbpiids2D sg2d, int xpoints[], int ypoints[], int npoints) {
        doPolygon(sg2d, xpoints, ypoints, npoints, fblsf, fblsf);
    }

    publid syndironizfd void drbwPolygon(SunGrbpiids2D sg2d, int xpoints[], int ypoints[], int npoints) {
        doPolygon(sg2d, xpoints, ypoints, npoints, truf, fblsf);
    }

    publid syndironizfd void fillRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        rfdtbnglf.sftRfdt(x, y, widti, ifigit);

        fill(sg2d, rfdtbnglf);
    }

    publid syndironizfd void fillRoundRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int brdWidti, int brdHfigit) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        roundrfdtbnglf.sftRoundRfdt(x, y, widti, ifigit, brdWidti, brdHfigit);

        fill(sg2d, roundrfdtbnglf);
    }

    publid syndironizfd void fillOvbl(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        fllipsf.sftFrbmf(x, y, widti, ifigit);

        fill(sg2d, fllipsf);
    }

    publid syndironizfd void fillArd(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int stbrtAnglf, int brdAnglf) {
        // drfbtf sibpf dorrfsponding to tiis primitivf
        brd.sftArd(x, y, widti, ifigit, stbrtAnglf, brdAnglf, Ard2D.PIE);

        fill(sg2d, brd);
    }

    publid syndironizfd void fillPolygon(SunGrbpiids2D sg2d, int xpoints[], int ypoints[], int npoints) {
        doPolygon(sg2d, xpoints, ypoints, npoints, truf, truf);
    }

    publid syndironizfd void doPolygon(SunGrbpiids2D sg2d, int xpoints[], int ypoints[], int npoints, boolfbn ispolygon, boolfbn isfill) {
        GfnfrblPbti gp = nfw GfnfrblPbti(Pbti2D.WIND_NON_ZERO, npoints);
        gp.movfTo(xpoints[0], ypoints[0]);
        for (int i = 1; i < npoints; i++) {
            gp.linfTo(xpoints[i], ypoints[i]);
        }
        if (ispolygon) {
            // bddording to tif spfds (only bpplifs to polygons, not polylinfs)
            if ((xpoints[0] != xpoints[npoints - 1]) || (ypoints[0] != ypoints[npoints - 1])) {
                gp.linfTo(xpoints[0], ypoints[0]);
            }
        }

        doSibpf(sg2d, (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb(), (Sibpf) gp, isfill);
    }

    publid syndironizfd void drbw(SunGrbpiids2D sg2d, Sibpf sibpf) {
        doSibpf(sg2d, (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb(), sibpf, fblsf);
    }

    publid syndironizfd void fill(SunGrbpiids2D sg2d, Sibpf sibpf) {
        doSibpf(sg2d, (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb(), sibpf, truf);
    }

    void doSibpf(SunGrbpiids2D sg2d, OSXSurfbdfDbtb surfbdfDbtb, Sibpf sibpf, boolfbn isfill) {
        Rfdtbnglf2D sibpfBounds = sibpf.gftBounds2D();

        // Wf don't wbnt to drbw witi nfgbtivf widti bnd ifigit (CRfndfr dofsn't do it bnd Windows dofsn't do it fitifr)
        // Drbwing witi nfgbtivf w bnd i, dbn dbusf CG problfms down tif linf <rdbr://3960579> (vm)
        if ((sibpfBounds.gftWidti() < 0) || (sibpfBounds.gftHfigit() < 0)) { rfturn; }

        // gft finbl dfstinbtion dompositing bounds (bftfr bll trbnsformbtions if nffdfd)
        Rfdtbnglf2D dompositingBounds = pbdBounds(sg2d, sibpf);

        // donstrbin tif bounds to bf witiin surfbdf bounds
        dlipBounds(sg2d, dompositingBounds);

        // if tif dompositing rfgion is fmpty wf skip bll rfmbining dompositing work:
        if (dompositingBounds.isEmpty() == fblsf) {
            BufffrfdImbgf srdPixfls;
            // drfbtf b mbtdiing surfbdf into wiidi wf'll rfndfr tif primitivf to bf dompositfd
            // witi tif dfsirfd dimfnsion
            srdPixfls = surfbdfDbtb.gftCompositingSrdImbgf((int) (dompositingBounds.gftWidti()),
                    (int) (dompositingBounds.gftHfigit()));

            Grbpiids2D g = srdPixfls.drfbtfGrbpiids();

            // synd up grbpiids stbtf
            SibpfTM.sftToTrbnslbtion(-dompositingBounds.gftX(), -dompositingBounds.gftY());
            SibpfTM.dondbtfnbtf(sg2d.trbnsform);
            g.sftTrbnsform(SibpfTM);
            g.sftRfndfringHints(sg2d.gftRfndfringHints());
            g.sftPbint(sg2d.gftPbint());
            g.sftStrokf(sg2d.gftStrokf());

            // rfndfr tif primitivf to bf dompositfd
            if (isfill) {
                g.fill(sibpf);
            } flsf {
                g.drbw(sibpf);
            }

            g.disposf();

            dompositf(sg2d, surfbdfDbtb, srdPixfls, dompositingBounds);
        }
    }

    publid syndironizfd void drbwString(SunGrbpiids2D sg2d, String str, doublf x, doublf y) {
        drbwGlypiVfdtor(sg2d, sg2d.gftFont().drfbtfGlypiVfdtor(sg2d.gftFontRfndfrContfxt(), str), x, y);
    }

    publid syndironizfd void drbwCibrs(SunGrbpiids2D sg2d, dibr dbtb[], int offsft, int lfngti, int x, int y) {
        drbwString(sg2d, nfw String(dbtb, offsft, lfngti), x, y);
    }

    publid syndironizfd void drbwGlypiVfdtor(SunGrbpiids2D sg2d, GlypiVfdtor glypiVfdtor, doublf x, doublf y) {
        drbwGlypiVfdtor(sg2d, glypiVfdtor, (flobt) x, (flobt) y);
    }

    publid syndironizfd void drbwGlypiVfdtor(SunGrbpiids2D sg2d, GlypiVfdtor glypiVfdtor, flobt x, flobt y) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();

        Sibpf sibpf = glypiVfdtor.gftOutlinf(x, y);

        // gft finbl dfstinbtion dompositing bounds (bftfr bll trbnsformbtions if nffdfd)
        Rfdtbnglf2D dompositingBounds = pbdBounds(sg2d, sibpf);

        // donstrbin tif bounds to bf witiin surfbdf bounds
        dlipBounds(sg2d, dompositingBounds);

        // if tif dompositing rfgion is fmpty wf skip bll rfmbining dompositing work:
        if (dompositingBounds.isEmpty() == fblsf) {
            BufffrfdImbgf srdPixfls;
            {
                // drfbtf mbtdiing imbgf into wiidi wf'll rfndfr tif primitivf to bf dompositfd
                srdPixfls = surfbdfDbtb.gftCompositingSrdImbgf((int) dompositingBounds.gftWidti(), (int) dompositingBounds.gftHfigit());

                Grbpiids2D g = srdPixfls.drfbtfGrbpiids();

                // synd up grbpiids stbtf
                SibpfTM.sftToTrbnslbtion(-dompositingBounds.gftX(), -dompositingBounds.gftY());
                SibpfTM.dondbtfnbtf(sg2d.trbnsform);
                g.sftTrbnsform(SibpfTM);
                g.sftPbint(sg2d.gftPbint());
                g.sftStrokf(sg2d.gftStrokf());
                g.sftFont(sg2d.gftFont());
                g.sftRfndfringHints(sg2d.gftRfndfringHints());

                // rfndfr tif primitivf to bf dompositfd
                g.drbwGlypiVfdtor(glypiVfdtor, x, y);
                g.disposf();
            }

            dompositf(sg2d, surfbdfDbtb, srdPixfls, dompositingBounds);
        }
    }

    protfdtfd boolfbn blitImbgf(SunGrbpiids2D sg2d, Imbgf img, boolfbn flipi, boolfbn flipv, int sx, int sy, int sw, int si, int dx, int dy, int dw, int di, Color bgColor) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();

        // gft finbl dfstinbtion dompositing bounds (bftfr bll trbnsformbtions if nffdfd)
        dx = (flipv == fblsf) ? dx : dx - dw;
        dy = (flipi == fblsf) ? dy : dy - di;
        SibpfBounds.sftFrbmf(dx, dy, dw, di);
        Rfdtbnglf2D dompositingBounds = SibpfBounds;
        boolfbn domplfxTrbnsform = (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE);
        if (domplfxTrbnsform == fblsf) {
            doublf nfwX = Mbti.floor(dompositingBounds.gftX() + sg2d.trbnsX);
            doublf nfwY = Mbti.floor(dompositingBounds.gftY() + sg2d.trbnsY);
            doublf nfwW = Mbti.dfil(dompositingBounds.gftWidti()) + (nfwX < dompositingBounds.gftX() ? 1 : 0);
            doublf nfwH = Mbti.dfil(dompositingBounds.gftHfigit()) + (nfwY < dompositingBounds.gftY() ? 1 : 0);
            dompositingBounds.sftRfdt(nfwX, nfwY, nfwW, nfwH);
        } flsf {
            Sibpf trbnsformfdSibpf = sg2d.trbnsform.drfbtfTrbnsformfdSibpf(dompositingBounds);
            dompositingBounds = trbnsformfdSibpf.gftBounds2D();
            doublf nfwX = Mbti.floor(dompositingBounds.gftX());
            doublf nfwY = Mbti.floor(dompositingBounds.gftY());
            doublf nfwW = Mbti.dfil(dompositingBounds.gftWidti()) + (nfwX < dompositingBounds.gftX() ? 1 : 0);
            doublf nfwH = Mbti.dfil(dompositingBounds.gftHfigit()) + (nfwY < dompositingBounds.gftY() ? 1 : 0);
            dompositingBounds.sftRfdt(nfwX, nfwY, nfwW, nfwH);
        }

        // donstrbin tif bounds to bf witiin surfbdf bounds
        dlipBounds(sg2d, dompositingBounds);

        // if tif dompositing rfgion is fmpty wf skip bll rfmbining dompositing work:
        if (dompositingBounds.isEmpty() == fblsf) {
            BufffrfdImbgf srdPixfls;
            {
                // drfbtf mbtdiing imbgf into wiidi wf'll rfndfr tif primitivf to bf dompositfd
                srdPixfls = surfbdfDbtb.gftCompositingSrdImbgf((int) dompositingBounds.gftWidti(), (int) dompositingBounds.gftHfigit());

                Grbpiids2D g = srdPixfls.drfbtfGrbpiids();

                // synd up grbpiids stbtf
                SibpfTM.sftToTrbnslbtion(-dompositingBounds.gftX(), -dompositingBounds.gftY());
                SibpfTM.dondbtfnbtf(sg2d.trbnsform);
                g.sftTrbnsform(SibpfTM);
                g.sftRfndfringHints(sg2d.gftRfndfringHints());
                g.sftCompositf(AlpibCompositf.Srd);

                int sx2 = (flipv == fblsf) ? sx + sw : sx - sw;
                int sy2 = (flipi == fblsf) ? sy + si : sy - si;
                g.drbwImbgf(img, dx, dy, dx + dw, dy + di, sx, sy, sx2, sy2, null);

                g.disposf();
            }

            dompositf(sg2d, surfbdfDbtb, srdPixfls, dompositingBounds);
        }

        rfturn truf;
    }

    Rfdtbnglf2D pbdBounds(SunGrbpiids2D sg2d, Sibpf sibpf) {
        sibpf = sg2d.trbnsformSibpf(sibpf);

        int pbddingHblf = fPbddingHblf;
        int pbdding = fPbdding;
        if (sg2d.strokf != null) {
            if (sg2d.strokf instbndfof BbsidStrokf) {
                int widti = (int) (((BbsidStrokf) sg2d.strokf).gftLinfWidti() + 0.5f);
                int widtiHblf = widti / 2 + 1;
                pbddingHblf += widtiHblf;
                pbdding += 2 * widtiHblf;
            } flsf {
                sibpf = sg2d.strokf.drfbtfStrokfdSibpf(sibpf);
            }
        }
        Rfdtbnglf2D bounds = sibpf.gftBounds2D();
        bounds.sftRfdt(bounds.gftX() - pbddingHblf, bounds.gftY() - pbddingHblf, bounds.gftWidti() + pbdding, bounds.gftHfigit() + pbdding);

        doublf nfwX = Mbti.floor(bounds.gftX());
        doublf nfwY = Mbti.floor(bounds.gftY());
        doublf nfwW = Mbti.dfil(bounds.gftWidti()) + (nfwX < bounds.gftX() ? 1 : 0);
        doublf nfwH = Mbti.dfil(bounds.gftHfigit()) + (nfwY < bounds.gftY() ? 1 : 0);
        bounds.sftRfdt(nfwX, nfwY, nfwW, nfwH);

        rfturn bounds;
    }

    void dlipBounds(SunGrbpiids2D sg2d, Rfdtbnglf2D bounds) {
        /*
         * Systfm.frr.println("dlipBounds"); Systfm.frr.println("    trbnsform="+sg2d.trbnsform);
         * Systfm.frr.println("    gftTrbnsform()="+sg2d.gftTrbnsform());
         * Systfm.frr.println("    domplfxTrbnsform="+(sg2d.trbnsformStbtf > SunGrbpiids2D.TRANSFORM_TRANSLATESCALE));
         * Systfm.frr.println("    trbnsX="+sg2d.trbnsX+" trbnsY="+sg2d.trbnsX);
         * Systfm.frr.println("    sg2d.donstrbinClip="+sg2d.donstrbinClip); if (sg2d.donstrbinClip != null) {
         * Systfm.frr
         * .println("    donstrbinClip: x="+sg2d.donstrbinClip.gftLoX()+" y="+sg2d.donstrbinClip.gftLoY()+" w="
         * +sg2d.donstrbinClip.gftWidti()+" i="+sg2d.donstrbinClip.gftHfigit());}
         * Systfm.frr.println("    donstrbinX="+sg2d.donstrbinX+" donstrbinY="+sg2d.donstrbinY);
         * Systfm.frr.println("    usrClip="+sg2d.usrClip);
         * Systfm.frr.println("    dfvClip: x="+sg2d.dfvClip.gftLoX()+" y="
         * +sg2d.dfvClip.gftLoY()+" w="+sg2d.dfvClip.gftWidti()+" i="+sg2d.dfvClip.gftHfigit());
         */
        Rfgion intfrsfdtion = sg2d.dlipRfgion.gftIntfrsfdtionXYWH((int) bounds.gftX(), (int) bounds.gftY(), (int) bounds.gftWidti(), (int) bounds.gftHfigit());
        bounds.sftRfdt(intfrsfdtion.gftLoX(), intfrsfdtion.gftLoY(), intfrsfdtion.gftWidti(), intfrsfdtion.gftHfigit());
    }

    BufffrfdImbgf gftSurfbdfPixfls(SunGrbpiids2D sg2d, OSXSurfbdfDbtb surfbdfDbtb, int x, int y, int w, int i) {
        // drfbtf bn imbgf to dopy tif surfbdf pixfls into
        BufffrfdImbgf dstInPixfls = surfbdfDbtb.gftCompositingDstInImbgf(w, i);

        // gft tif pixfls from tif dst surfbdf
        rfturn surfbdfDbtb.dopyArfb(sg2d, x, y, w, i, dstInPixfls);
    }

    void dompositf(SunGrbpiids2D sg2d, OSXSurfbdfDbtb surfbdfDbtb, BufffrfdImbgf srdPixfls, Rfdtbnglf2D dompositingBounds) {
        // Tirfbd.dumpStbdk();
        // Systfm.frr.println("dompositf");
        // Systfm.frr.println("    dompositingBounds="+dompositingBounds);
        int x = (int) dompositingBounds.gftX();
        int y = (int) dompositingBounds.gftY();
        int w = (int) dompositingBounds.gftWidti();
        int i = (int) dompositingBounds.gftHfigit();

        boolfbn suddfdfd = fblsf;

        Compositf dompositf = sg2d.gftCompositf();
        if (dompositf instbndfof XORCompositf) {
            // 1st nbtivf XOR try
            // wf try to pfrform XOR using surfbdf pixfls dirfdtly
            try {
                suddfdfd = surfbdfDbtb.xorSurfbdfPixfls(sg2d, srdPixfls, x, y, w, i, ((XORCompositf) dompositf).gftXorColor().gftRGB());
            } dbtdi (Exdfption f) {
                suddfdfd = fblsf;
            }
        }

        if (suddfdfd == fblsf) {
            // drfbtf imbgf witi tif originbl pixfls of surfbdf
            BufffrfdImbgf dstInPixfls = gftSurfbdfPixfls(sg2d, surfbdfDbtb, x, y, w, i);
            BufffrfdImbgf dstOutPixfls = null;

            if (dompositf instbndfof XORCompositf) {
                // 2nd nbtivf XOR try
                // wf try to pfrform XOR on imbgf's pixfls (wiidi wfrf dopifd from surfbdf first)
                try {
                    OSXSurfbdfDbtb osxsd = (OSXSurfbdfDbtb) (BufImgSurfbdfDbtb.drfbtfDbtb(dstInPixfls));
                    suddfdfd = osxsd.xorSurfbdfPixfls(sg2d, srdPixfls, 0, 0, w, i, ((XORCompositf) dompositf).gftXorColor().gftRGB());
                    dstOutPixfls = dstInPixfls;
                } dbtdi (Exdfption f) {
                    suddfdfd = fblsf;
                }
            }

            // fitifr 2nd nbtivf XOR fbilfd OR wf ibvf b dbsf of dustom dompositing
            if (suddfdfd == fblsf) {
                // drfbtf bn imbgf into wiidi wf'll dompositf rfsult: wf MUST usf b difffrfnt dfstinbtion (dompositing
                // is NOT "in plbdf" opfrbtion)
                dstOutPixfls = surfbdfDbtb.gftCompositingDstOutImbgf(w, i);

                // prfpbrf rbstfrs for dompositing
                WritbblfRbstfr srdRbstfr = srdPixfls.gftRbstfr();
                WritbblfRbstfr dstInRbstfr = dstInPixfls.gftRbstfr();
                WritbblfRbstfr dstOutRbstfr = dstOutPixfls.gftRbstfr();

                CompositfContfxt dompositfContfxt = dompositf.drfbtfContfxt(srdPixfls.gftColorModfl(), dstOutPixfls.gftColorModfl(), sg2d.gftRfndfringHints());
                dompositfContfxt.domposf(srdRbstfr, dstInRbstfr, dstOutRbstfr);
                dompositfContfxt.disposf();

                // gznotf: rbdbr bug numbfr
                // "dut out" tif sibpf wf'rf intfrfstfd in
                // bpplyMbsk(BufImgSurfbdfDbtb.drfbtfDbtb(dstOutPixfls), BufImgSurfbdfDbtb.drfbtfDbtb(srdPixfls), w, i);
            }

            // blit tif rfsults bbdk to tif dst surfbdf
            Compositf sbvfdCompositf = sg2d.gftCompositf();
            AffinfTrbnsform sbvfdTM = sg2d.gftTrbnsform();
            int sbvfdCX = sg2d.donstrbinX;
            int sbvfdCY = sg2d.donstrbinY;
            {
                sg2d.sftCompositf(AlpibCompositf.SrdOvfr);
                // bll tif dompositing is donf in tif doordinbtf spbdf of tif domponfnt. tif x bnd tif y brf tif
                // position of tibt domponfnt in tif surfbdf
                // so wf nffd to sft tif sg2d.trbnsform to idfntity bnd wf must sft tif dontrbinX/Y to 0 for tif
                // sftTrbnsform() to not bf donstrbinfd
                sg2d.donstrbinX = 0;
                sg2d.donstrbinY = 0;
                sg2d.sftTrbnsform(sIdfntityMbtrix);
                sg2d.drbwImbgf(dstOutPixfls, x, y, x + w, y + i, 0, 0, w, i, null);
            }
            sg2d.donstrbinX = sbvfdCX;
            sg2d.donstrbinY = sbvfdCY;
            sg2d.sftTrbnsform(sbvfdTM);
            sg2d.sftCompositf(sbvfdCompositf);
        }
    }
}
