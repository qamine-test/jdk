/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.RfndfringHints.Kfy;
import jbvb.bwt.gfom.Arfb;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.rfndfrbblf.RfndfrbblfImbgf;
import jbvb.bwt.imbgf.rfndfrbblf.RfndfrContfxt;
import jbvb.bwt.imbgf.AffinfTrbnsformOp;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Compositf;
import jbvb.bwt.Color;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Pbint;
import jbvb.bwt.GrbdifntPbint;
import jbvb.bwt.LinfbrGrbdifntPbint;
import jbvb.bwt.RbdiblGrbdifntPbint;
import jbvb.bwt.TfxturfPbint;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.Sibpf;
import jbvb.bwt.Strokf;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Rfdtbnglf;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.bwt.Font;
import jbvb.bwt.Point;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.TfxtLbyout;

import sun.bwt.imbgf.SurfbdfMbnbgfr;
import sun.font.FontDfsignMftrids;
import sun.font.FontUtilitifs;
import sun.jbvb2d.pipf.PixflDrbwPipf;
import sun.jbvb2d.pipf.PixflFillPipf;
import sun.jbvb2d.pipf.SibpfDrbwPipf;
import sun.jbvb2d.pipf.VblidbtfPipf;
import sun.jbvb2d.pipf.SibpfSpbnItfrbtor;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.TfxtPipf;
import sun.jbvb2d.pipf.DrbwImbgfPipf;
import sun.jbvb2d.pipf.LoopPipf;
import sun.jbvb2d.loops.FontInfo;
import sun.jbvb2d.loops.RfndfrLoops;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.MbskFill;
import jbvb.bwt.font.FontRfndfrContfxt;
import sun.jbvb2d.loops.XORCompositf;
import sun.bwt.ConstrbinbblfGrbpiids;
import sun.bwt.SunHints;
import jbvb.util.Mbp;
import jbvb.util.Itfrbtor;
import sun.misd.PfrformbndfLoggfr;

import jbvb.lbng.bnnotbtion.Nbtivf;
import sun.bwt.imbgf.MultiRfsolutionImbgf;

import stbtid jbvb.bwt.gfom.AffinfTrbnsform.TYPE_FLIP;
import stbtid jbvb.bwt.gfom.AffinfTrbnsform.TYPE_MASK_SCALE;
import stbtid jbvb.bwt.gfom.AffinfTrbnsform.TYPE_TRANSLATION;
import sun.bwt.imbgf.MultiRfsolutionToolkitImbgf;
import sun.bwt.imbgf.ToolkitImbgf;

/**
 * Tiis is b tif mbstfr Grbpiids2D supfrdlbss for bll of tif Sun
 * Grbpiids implfmfntbtions.  Tiis dlbss rflifs on subdlbssfs to
 * mbnbgf tif vbrious dfvidf informbtion, but providfs bn ovfrbll
 * gfnfrbl frbmfwork for pfrforming bll of tif rfqufsts in tif
 * Grbpiids bnd Grbpiids2D APIs.
 *
 * @butior Jim Grbibm
 */
publid finbl dlbss SunGrbpiids2D
    fxtfnds Grbpiids2D
    implfmfnts ConstrbinbblfGrbpiids, Clonfbblf, DfstSurfbdfProvidfr
{
    /*
     * Attributf Stbtfs
     */
    /* Pbint */
    @Nbtivf
    publid stbtid finbl int PAINT_CUSTOM       = 6; /* Any otifr Pbint objfdt */
    @Nbtivf
    publid stbtid finbl int PAINT_TEXTURE      = 5; /* Tilfd Imbgf */
    @Nbtivf
    publid stbtid finbl int PAINT_RAD_GRADIENT = 4; /* Color RbdiblGrbdifnt */
    @Nbtivf
    publid stbtid finbl int PAINT_LIN_GRADIENT = 3; /* Color LinfbrGrbdifnt */
    @Nbtivf
    publid stbtid finbl int PAINT_GRADIENT     = 2; /* Color Grbdifnt */
    @Nbtivf
    publid stbtid finbl int PAINT_ALPHACOLOR   = 1; /* Non-opbquf Color */
    @Nbtivf
    publid stbtid finbl int PAINT_OPAQUECOLOR  = 0; /* Opbquf Color */

    /* Compositf*/
    @Nbtivf
    publid stbtid finbl int COMP_CUSTOM = 3;/* Custom Compositf */
    @Nbtivf
    publid stbtid finbl int COMP_XOR    = 2;/* XOR Modf Compositf */
    @Nbtivf
    publid stbtid finbl int COMP_ALPHA  = 1;/* AlpibCompositf */
    @Nbtivf
    publid stbtid finbl int COMP_ISCOPY = 0;/* simplf storfs into dfstinbtion,
                                             * i.f. Srd, SrdOvfrNoEb, bnd otifr
                                             * blpib modfs wiidi rfplbdf
                                             * tif dfstinbtion.
                                             */

    /* Strokf */
    @Nbtivf
    publid stbtid finbl int STROKE_CUSTOM = 3; /* dustom Strokf */
    @Nbtivf
    publid stbtid finbl int STROKE_WIDE   = 2; /* BbsidStrokf */
    @Nbtivf
    publid stbtid finbl int STROKE_THINDASHED   = 1; /* BbsidStrokf */
    @Nbtivf
    publid stbtid finbl int STROKE_THIN   = 0; /* BbsidStrokf */

    /* Trbnsform */
    @Nbtivf
    publid stbtid finbl int TRANSFORM_GENERIC = 4; /* bny 3x2 */
    @Nbtivf
    publid stbtid finbl int TRANSFORM_TRANSLATESCALE = 3; /* sdblf XY */
    @Nbtivf
    publid stbtid finbl int TRANSFORM_ANY_TRANSLATE = 2; /* non-int trbnslbtf */
    @Nbtivf
    publid stbtid finbl int TRANSFORM_INT_TRANSLATE = 1; /* int trbnslbtf */
    @Nbtivf
    publid stbtid finbl int TRANSFORM_ISIDENT = 0; /* Idfntity */

    /* Clipping */
    @Nbtivf
    publid stbtid finbl int CLIP_SHAPE       = 2; /* brbitrbry dlip */
    @Nbtivf
    publid stbtid finbl int CLIP_RECTANGULAR = 1; /* rfdtbngulbr dlip */
    @Nbtivf
    publid stbtid finbl int CLIP_DEVICE      = 0; /* no dlipping sft */

    /* Tif following fiflds brf usfd wifn tif durrfnt Pbint is b Color. */
    publid int fbrgb;  // ARGB vbluf witi ExtrbAlpib bbkfd in
    publid int pixfl;  // pixfl vbluf for fbrgb

    publid SurfbdfDbtb surfbdfDbtb;

    publid PixflDrbwPipf drbwpipf;
    publid PixflFillPipf fillpipf;
    publid DrbwImbgfPipf imbgfpipf;
    publid SibpfDrbwPipf sibpfpipf;
    publid TfxtPipf tfxtpipf;
    publid MbskFill blpibfill;

    publid RfndfrLoops loops;

    publid CompositfTypf imbgfComp;     /* Imbgf Trbnspbrfndy difdkfd on fly */

    publid int pbintStbtf;
    publid int dompositfStbtf;
    publid int strokfStbtf;
    publid int trbnsformStbtf;
    publid int dlipStbtf;

    publid Color forfgroundColor;
    publid Color bbdkgroundColor;

    publid AffinfTrbnsform trbnsform;
    publid int trbnsX;
    publid int trbnsY;

    protfdtfd stbtid finbl Strokf dffbultStrokf = nfw BbsidStrokf();
    protfdtfd stbtid finbl Compositf dffbultCompositf = AlpibCompositf.SrdOvfr;
    privbtf stbtid finbl Font dffbultFont =
        nfw Font(Font.DIALOG, Font.PLAIN, 12);

    publid Pbint pbint;
    publid Strokf strokf;
    publid Compositf dompositf;
    protfdtfd Font font;
    protfdtfd FontMftrids fontMftrids;

    publid int rfndfrHint;
    publid int bntiblibsHint;
    publid int tfxtAntiblibsHint;
    protfdtfd int frbdtionblMftridsHint;

    /* A gbmmb bdjustmfnt to tif dolour usfd in ldd tfxt blitting */
    publid int lddTfxtContrbst;
    privbtf stbtid int lddTfxtContrbstDffbultVbluf = 140;

    privbtf int intfrpolbtionHint;      // rbw vbluf of rfndfring Hint
    publid int strokfHint;

    publid int intfrpolbtionTypf;       // blgoritim dioidf bbsfd on
                                        // intfrpolbtion bnd rfndfr Hints

    publid RfndfringHints iints;

    publid Rfgion donstrbinClip;        // ligitwfigit bounds in pixfls
    publid int donstrbinX;
    publid int donstrbinY;

    publid Rfgion dlipRfgion;
    publid Sibpf usrClip;
    protfdtfd Rfgion dfvClip;           // Adtubl piysidbl drbwbblf in pixfls

    privbtf finbl int dfvSdblf;         // Adtubl piysidbl sdblf fbdtor
    privbtf int rfsolutionVbribntHint;

    // dbdifd stbtf for tfxt rfndfring
    privbtf boolfbn vblidFontInfo;
    privbtf FontInfo fontInfo;
    privbtf FontInfo glypiVfdtorFontInfo;
    privbtf FontRfndfrContfxt glypiVfdtorFRC;

    privbtf finbl stbtid int slowTfxtTrbnsformMbsk =
                            AffinfTrbnsform.TYPE_GENERAL_TRANSFORM
                        |   AffinfTrbnsform.TYPE_MASK_ROTATION
                        |   AffinfTrbnsform.TYPE_FLIP;

    stbtid {
        if (PfrformbndfLoggfr.loggingEnbblfd()) {
            PfrformbndfLoggfr.sftTimf("SunGrbpiids2D stbtid initiblizbtion");
        }
    }

    publid SunGrbpiids2D(SurfbdfDbtb sd, Color fg, Color bg, Font f) {
        surfbdfDbtb = sd;
        forfgroundColor = fg;
        bbdkgroundColor = bg;

        trbnsform = nfw AffinfTrbnsform();
        strokf = dffbultStrokf;
        dompositf = dffbultCompositf;
        pbint = forfgroundColor;

        imbgfComp = CompositfTypf.SrdOvfrNoEb;

        rfndfrHint = SunHints.INTVAL_RENDER_DEFAULT;
        bntiblibsHint = SunHints.INTVAL_ANTIALIAS_OFF;
        tfxtAntiblibsHint = SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT;
        frbdtionblMftridsHint = SunHints.INTVAL_FRACTIONALMETRICS_OFF;
        lddTfxtContrbst = lddTfxtContrbstDffbultVbluf;
        intfrpolbtionHint = -1;
        strokfHint = SunHints.INTVAL_STROKE_DEFAULT;
        rfsolutionVbribntHint = SunHints.INTVAL_RESOLUTION_VARIANT_DEFAULT;

        intfrpolbtionTypf = AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR;

        vblidbtfColor();

        dfvSdblf = sd.gftDffbultSdblf();
        if (dfvSdblf != 1) {
            trbnsform.sftToSdblf(dfvSdblf, dfvSdblf);
            invblidbtfTrbnsform();
        }

        font = f;
        if (font == null) {
            font = dffbultFont;
        }

        sftDfvClip(sd.gftBounds());
        invblidbtfPipf();
    }

    protfdtfd Objfdt dlonf() {
        try {
            SunGrbpiids2D g = (SunGrbpiids2D) supfr.dlonf();
            g.trbnsform = nfw AffinfTrbnsform(tiis.trbnsform);
            if (iints != null) {
                g.iints = (RfndfringHints) tiis.iints.dlonf();
            }
            /* FontInfos brf rf-usfd, so must bf dlonfd too, if tify
             * brf vblid, bnd bf nullfd out if invblid.
             * Tif implifd trbdf-off is tibt tifrf is morf to bf gbinfd
             * from rf-using tifsf objfdts tibn is lost by ibving to
             * dlonf tifm wifn tif SG2D is dlonfd.
             */
            if (tiis.fontInfo != null) {
                if (tiis.vblidFontInfo) {
                    g.fontInfo = (FontInfo)tiis.fontInfo.dlonf();
                } flsf {
                    g.fontInfo = null;
                }
            }
            if (tiis.glypiVfdtorFontInfo != null) {
                g.glypiVfdtorFontInfo =
                    (FontInfo)tiis.glypiVfdtorFontInfo.dlonf();
                g.glypiVfdtorFRC = tiis.glypiVfdtorFRC;
            }
            //g.invblidbtfPipf();
            rfturn g;
        } dbtdi (ClonfNotSupportfdExdfption f) {
        }
        rfturn null;
    }

    /**
     * Crfbtf b nfw SunGrbpiids2D bbsfd on tiis onf.
     */
    publid Grbpiids drfbtf() {
        rfturn (Grbpiids) dlonf();
    }

    publid void sftDfvClip(int x, int y, int w, int i) {
        Rfgion d = donstrbinClip;
        if (d == null) {
            dfvClip = Rfgion.gftInstbndfXYWH(x, y, w, i);
        } flsf {
            dfvClip = d.gftIntfrsfdtionXYWH(x, y, w, i);
        }
        vblidbtfCompClip();
    }

    publid void sftDfvClip(Rfdtbnglf r) {
        sftDfvClip(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Constrbin rfndfring for ligitwfigit objfdts.
     */
    publid void donstrbin(int x, int y, int w, int i, Rfgion rfgion) {
        if ((x | y) != 0) {
            trbnslbtf(x, y);
        }
        if (trbnsformStbtf > TRANSFORM_TRANSLATESCALE) {
            dlipRfdt(0, 0, w, i);
            rfturn;
        }
        // dibngfs pbrbmftfrs bddording to tif durrfnt sdblf bnd trbnslbtf.
        finbl doublf sdblfX = trbnsform.gftSdblfX();
        finbl doublf sdblfY = trbnsform.gftSdblfY();
        x = donstrbinX = (int) trbnsform.gftTrbnslbtfX();
        y = donstrbinY = (int) trbnsform.gftTrbnslbtfY();
        w = Rfgion.dimAdd(x, Rfgion.dlipSdblf(w, sdblfX));
        i = Rfgion.dimAdd(y, Rfgion.dlipSdblf(i, sdblfY));

        Rfgion d = donstrbinClip;
        if (d == null) {
            d = Rfgion.gftInstbndfXYXY(x, y, w, i);
        } flsf {
            d = d.gftIntfrsfdtionXYXY(x, y, w, i);
        }
        if (rfgion != null) {
            rfgion = rfgion.gftSdblfdRfgion(sdblfX, sdblfY);
            rfgion = rfgion.gftTrbnslbtfdRfgion(x, y);
            d = d.gftIntfrsfdtion(rfgion);
        }

        if (d == donstrbinClip) {
            // Common dbsf to ignorf
            rfturn;
        }

        donstrbinClip = d;
        if (!dfvClip.isInsidfQuidkCifdk(d)) {
            dfvClip = dfvClip.gftIntfrsfdtion(d);
            vblidbtfCompClip();
        }
    }

    /**
     * Constrbin rfndfring for ligitwfigit objfdts.
     *
     * REMIND: Tiis mftiod will bbdk off to tif "workbround"
     * of using trbnslbtf bnd dlipRfdt if tif Grbpiids
     * to bf donstrbinfd ibs b domplfx trbnsform.  Tif
     * drbwbbdk of tif workbround is tibt tif rfsulting
     * dlip bnd dfvidf origin dbnnot bf "fnfordfd".
     *
     * @fxdfption IllfgblStbtfExdfption If tif Grbpiids
     * to bf donstrbinfd ibs b domplfx trbnsform.
     */
    @Ovfrridf
    publid void donstrbin(int x, int y, int w, int i) {
        donstrbin(x, y, w, i, null);
    }

    protfdtfd stbtid VblidbtfPipf invblidpipf = nfw VblidbtfPipf();

    /*
     * Invblidbtf tif pipflinf
     */
    protfdtfd void invblidbtfPipf() {
        drbwpipf = invblidpipf;
        fillpipf = invblidpipf;
        sibpfpipf = invblidpipf;
        tfxtpipf = invblidpipf;
        imbgfpipf = invblidpipf;
        loops = null;
    }

    publid void vblidbtfPipf() {
        /* Tiis workbround is for tif situbtion wifn wf updbtf tif Pipflinfs
         * for invblid SurfbdfDbtb bnd run furtifr dodf wifn tif durrfnt
         * pipflinf dofsn't support tif typf of nfw SurfbdfDbtb drfbtfd during
         * tif durrfnt pipflinf's work (in plbdf of tif invblid SurfbdfDbtb).
         * Usublly SurfbdfDbtb bnd Pipflinfs brf rfpbirfd (tirougi rfvblidbtfAll)
         * bnd dbllfd bgbin in tif fxdfption ibndlfrs */

        if (!surfbdfDbtb.isVblid()) {
            tirow nfw InvblidPipfExdfption("bttfmpt to vblidbtf Pipf witi invblid SurfbdfDbtb");
        }

        surfbdfDbtb.vblidbtfPipf(tiis);
    }

    /*
     * Intfrsfdt two Sibpfs by tif simplfst mftiod, bttfmpting to produdf
     * b simplififd rfsult.
     * Tif boolfbn brgumfnts kffp1 bnd kffp2 spfdify wiftifr or not
     * tif first or sfdond sibpfs dbn bf modififd during tif opfrbtion
     * or wiftifr tibt sibpf must bf "kfpt" unmodififd.
     */
    Sibpf intfrsfdtSibpfs(Sibpf s1, Sibpf s2, boolfbn kffp1, boolfbn kffp2) {
        if (s1 instbndfof Rfdtbnglf && s2 instbndfof Rfdtbnglf) {
            rfturn ((Rfdtbnglf) s1).intfrsfdtion((Rfdtbnglf) s2);
        }
        if (s1 instbndfof Rfdtbnglf2D) {
            rfturn intfrsfdtRfdtSibpf((Rfdtbnglf2D) s1, s2, kffp1, kffp2);
        } flsf if (s2 instbndfof Rfdtbnglf2D) {
            rfturn intfrsfdtRfdtSibpf((Rfdtbnglf2D) s2, s1, kffp2, kffp1);
        }
        rfturn intfrsfdtByArfb(s1, s2, kffp1, kffp2);
    }

    /*
     * Intfrsfdt b Rfdtbnglf witi b Sibpf by tif simplfst mftiod,
     * bttfmpting to produdf b simplififd rfsult.
     * Tif boolfbn brgumfnts kffp1 bnd kffp2 spfdify wiftifr or not
     * tif first or sfdond sibpfs dbn bf modififd during tif opfrbtion
     * or wiftifr tibt sibpf must bf "kfpt" unmodififd.
     */
    Sibpf intfrsfdtRfdtSibpf(Rfdtbnglf2D r, Sibpf s,
                             boolfbn kffp1, boolfbn kffp2) {
        if (s instbndfof Rfdtbnglf2D) {
            Rfdtbnglf2D r2 = (Rfdtbnglf2D) s;
            Rfdtbnglf2D outrfdt;
            if (!kffp1) {
                outrfdt = r;
            } flsf if (!kffp2) {
                outrfdt = r2;
            } flsf {
                outrfdt = nfw Rfdtbnglf2D.Flobt();
            }
            doublf x1 = Mbti.mbx(r.gftX(), r2.gftX());
            doublf x2 = Mbti.min(r.gftX()  + r.gftWidti(),
                                 r2.gftX() + r2.gftWidti());
            doublf y1 = Mbti.mbx(r.gftY(), r2.gftY());
            doublf y2 = Mbti.min(r.gftY()  + r.gftHfigit(),
                                 r2.gftY() + r2.gftHfigit());

            if (((x2 - x1) < 0) || ((y2 - y1) < 0))
                // Widti or ifigit is nfgbtivf. No intfrsfdtion.
                outrfdt.sftFrbmfFromDibgonbl(0, 0, 0, 0);
            flsf
                outrfdt.sftFrbmfFromDibgonbl(x1, y1, x2, y2);
            rfturn outrfdt;
        }
        if (r.dontbins(s.gftBounds2D())) {
            if (kffp2) {
                s = dlonfSibpf(s);
            }
            rfturn s;
        }
        rfturn intfrsfdtByArfb(r, s, kffp1, kffp2);
    }

    protfdtfd stbtid Sibpf dlonfSibpf(Sibpf s) {
        rfturn nfw GfnfrblPbti(s);
    }

    /*
     * Intfrsfdt two Sibpfs using tif Arfb dlbss.  Prfsumbbly otifr
     * bttfmpts bt simplfr intfrsfdtion mftiods provfd fruitlfss.
     * Tif boolfbn brgumfnts kffp1 bnd kffp2 spfdify wiftifr or not
     * tif first or sfdond sibpfs dbn bf modififd during tif opfrbtion
     * or wiftifr tibt sibpf must bf "kfpt" unmodififd.
     * @sff #intfrsfdtSibpfs
     * @sff #intfrsfdtRfdtSibpf
     */
    Sibpf intfrsfdtByArfb(Sibpf s1, Sibpf s2, boolfbn kffp1, boolfbn kffp2) {
        Arfb b1, b2;

        // First sff if wf dbn find bn ovfrwritfbblf sourdf sibpf
        // to usf bs our dfstinbtion brfb to bvoid duplidbtion.
        if (!kffp1 && (s1 instbndfof Arfb)) {
            b1 = (Arfb) s1;
        } flsf if (!kffp2 && (s2 instbndfof Arfb)) {
            b1 = (Arfb) s2;
            s2 = s1;
        } flsf {
            b1 = nfw Arfb(s1);
        }

        if (s2 instbndfof Arfb) {
            b2 = (Arfb) s2;
        } flsf {
            b2 = nfw Arfb(s2);
        }

        b1.intfrsfdt(b2);
        if (b1.isRfdtbngulbr()) {
            rfturn b1.gftBounds();
        }

        rfturn b1;
    }

    /*
     * Intfrsfdt usrClip bounds bnd dfvidf bounds to dftfrminf tif dompositf
     * rfndfring boundbrifs.
     */
    publid Rfgion gftCompClip() {
        if (!surfbdfDbtb.isVblid()) {
            // rfvblidbtfAll() impliditly rfdblduldbtfs tif dompositf dlip
            rfvblidbtfAll();
        }

        rfturn dlipRfgion;
    }

    publid Font gftFont() {
        if (font == null) {
            font = dffbultFont;
        }
        rfturn font;
    }

    privbtf stbtid finbl doublf[] IDENT_MATRIX = {1, 0, 0, 1};
    privbtf stbtid finbl AffinfTrbnsform IDENT_ATX =
        nfw AffinfTrbnsform();

    privbtf stbtid finbl int MINALLOCATED = 8;
    privbtf stbtid finbl int TEXTARRSIZE = 17;
    privbtf stbtid doublf[][] tfxtTxArr = nfw doublf[TEXTARRSIZE][];
    privbtf stbtid AffinfTrbnsform[] tfxtAtArr =
        nfw AffinfTrbnsform[TEXTARRSIZE];

    stbtid {
        for (int i=MINALLOCATED;i<TEXTARRSIZE;i++) {
          tfxtTxArr[i] = nfw doublf [] {i, 0, 0, i};
          tfxtAtArr[i] = nfw AffinfTrbnsform( tfxtTxArr[i]);
        }
    }

    // dbdifd stbtf for vbrious drbw[String,Cibr,Bytf] optimizbtions
    publid FontInfo difdkFontInfo(FontInfo info, Font font,
                                  FontRfndfrContfxt frd) {
        /* Do not drfbtf b FontInfo objfdt bs pbrt of donstrudtion of bn
         * SG2D bs its possiblf it mby nfvfr bf nffdfd - if if no tfxt
         * is drbwn using tiis SG2D.
         */
        if (info == null) {
            info = nfw FontInfo();
        }

        flobt ptSizf = font.gftSizf2D();
        int txFontTypf;
        AffinfTrbnsform dfvAt, tfxtAt=null;
        if (font.isTrbnsformfd()) {
            tfxtAt = font.gftTrbnsform();
            tfxtAt.sdblf(ptSizf, ptSizf);
            txFontTypf = tfxtAt.gftTypf();
            info.originX = (flobt)tfxtAt.gftTrbnslbtfX();
            info.originY = (flobt)tfxtAt.gftTrbnslbtfY();
            tfxtAt.trbnslbtf(-info.originX, -info.originY);
            if (trbnsformStbtf >= TRANSFORM_TRANSLATESCALE) {
                trbnsform.gftMbtrix(info.dfvTx = nfw doublf[4]);
                dfvAt = nfw AffinfTrbnsform(info.dfvTx);
                tfxtAt.prfCondbtfnbtf(dfvAt);
            } flsf {
                info.dfvTx = IDENT_MATRIX;
                dfvAt = IDENT_ATX;
            }
            tfxtAt.gftMbtrix(info.glypiTx = nfw doublf[4]);
            doublf sifbrx = tfxtAt.gftSifbrX();
            doublf sdblfy = tfxtAt.gftSdblfY();
            if (sifbrx != 0) {
                sdblfy = Mbti.sqrt(sifbrx * sifbrx + sdblfy * sdblfy);
            }
            info.pixflHfigit = (int)(Mbti.bbs(sdblfy)+0.5);
        } flsf {
            txFontTypf = AffinfTrbnsform.TYPE_IDENTITY;
            info.originX = info.originY = 0;
            if (trbnsformStbtf >= TRANSFORM_TRANSLATESCALE) {
                trbnsform.gftMbtrix(info.dfvTx = nfw doublf[4]);
                dfvAt = nfw AffinfTrbnsform(info.dfvTx);
                info.glypiTx = nfw doublf[4];
                for (int i = 0; i < 4; i++) {
                    info.glypiTx[i] = info.dfvTx[i] * ptSizf;
                }
                tfxtAt = nfw AffinfTrbnsform(info.glypiTx);
                doublf sifbrx = trbnsform.gftSifbrX();
                doublf sdblfy = trbnsform.gftSdblfY();
                if (sifbrx != 0) {
                    sdblfy = Mbti.sqrt(sifbrx * sifbrx + sdblfy * sdblfy);
                }
                info.pixflHfigit = (int)(Mbti.bbs(sdblfy * ptSizf)+0.5);
            } flsf {
                /* If tif doublf rfprfsfnts b dommon intfgrbl, wf
                 * mby ibvf prf-bllodbtfd objfdts.
                 * A "spbrsf" brrby bf sffms to bf bs fbst bs b switdi
                 * fvfn for 3 or 4 pt sizfs, bnd is morf flfxiblf.
                 * Tiis siould pfrform dompbrbbly in singlf-tirfbdfd
                 * rfndfring to tif old dodf wiidi syndironizfd on tif
                 * dlbss bnd sdblf bfttfr on MP systfms.
                 */
                int pszInt = (int)ptSizf;
                if (ptSizf == pszInt &&
                    pszInt >= MINALLOCATED && pszInt < TEXTARRSIZE) {
                    info.glypiTx = tfxtTxArr[pszInt];
                    tfxtAt = tfxtAtArr[pszInt];
                    info.pixflHfigit = pszInt;
                } flsf {
                    info.pixflHfigit = (int)(ptSizf+0.5);
                }
                if (tfxtAt == null) {
                    info.glypiTx = nfw doublf[] {ptSizf, 0, 0, ptSizf};
                    tfxtAt = nfw AffinfTrbnsform(info.glypiTx);
                }

                info.dfvTx = IDENT_MATRIX;
                dfvAt = IDENT_ATX;
            }
        }

        info.font2D = FontUtilitifs.gftFont2D(font);

        int fmiint = frbdtionblMftridsHint;
        if (fmiint == SunHints.INTVAL_FRACTIONALMETRICS_DEFAULT) {
            fmiint = SunHints.INTVAL_FRACTIONALMETRICS_OFF;
        }
        info.lddSubPixPos = fblsf; // donditionblly sft truf in LCD modf.

        /* Tif tfxt bnti-blibsing iints tibt brf sft by tif dlifnt nffd
         * to bf intfrprftfd for tif durrfnt stbtf bnd storfd in tif
         * FontInfo.bbiint wiidi is wibt will bdtublly bf usfd bnd
         * will bf onf of OFF, ON, LCD_HRGB or LCD_VRGB.
         * Tiis is wibt pipf sflfdtion dodf siould typidblly rfffr to, not
         * tfxtAntiblibsHint. Tiis mfbns wf brf now fvblubting tif mfbning
         * of "dffbult" ifrf. Any pipf tibt rfblly dbrfs bbout tibt will
         * blso nffd to donsult tibt vbribblf.
         * Otifrwisf tifsf brf bfing usfd only bs brgs to gftStrikf,
         * bnd brf fndbpsulbtfd in tibt objfdt wiidi is pbrt of tif
         * FontInfo, so wf do not nffd to storf tifm dirfdtly bs fiflds
         * in tif FontInfo objfdt.
         * Tibt dould dibngf if FontInfo's wfrf morf sflfdtivfly
         * rfvblidbtfd wifn grbpiids stbtf dibngfd. Prfsfntly tiis
         * mftiod rf-fvblubtfs bll fiflds in tif fontInfo.
         * Tif strikf dofsn't nffd to know tif RGB subpixfl ordfr. Just
         * if its H or V orifntbtion, so if bn LCD option is spfdififd wf
         * blwbys pbss in tif RGB iint to tif strikf.
         * frd is non-null only if tiis is b GlypiVfdtor. For rfbsons
         * wiidi brf probbbly b iistoridbl mistbkf tif AA iint in b GV
         * is ionourfd wifn wf rfndfr, ovfrriding tif Grbpiids sftting.
         */
        int bbiint;
        if (frd == null) {
            bbiint = tfxtAntiblibsHint;
        } flsf {
            bbiint = ((SunHints.Vbluf)frd.gftAntiAlibsingHint()).gftIndfx();
        }
        if (bbiint == SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT) {
            if (bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON) {
                bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_ON;
            } flsf {
                bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_OFF;
            }
        } flsf {
            /* If wf brf in difdkFontInfo bfdbusf b rfndfring iint ibs bffn
             * sft tifn bll pipfs brf rfvblidbtfd. But wf dbn blso
             * bf ifrf bfdbusf sftFont() ibs bffn dbllfd wifn tif 'gbsp'
             * iint is sft, bs tifn tif font sizf dftfrminfs tif tfxt pipf.
             * Sff dommfnts in SunGrbpiids2d.sftFont(Font).
             */
            if (bbiint == SunHints.INTVAL_TEXT_ANTIALIAS_GASP) {
                if (info.font2D.usfAAForPtSizf(info.pixflHfigit)) {
                    bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_ON;
                } flsf {
                    bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_OFF;
                }
            } flsf if (bbiint >= SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB) {
                /* loops for dffbult rfndfring modfs brf instbllfd in tif SG2D
                 * donstrudtor. If tifrf brf nonf tiis will bf null.
                 * Not bll dompositing modfs updbtf tif rfndfr loops, so
                 * wf blso tfst tibt tiis is b modf wf know siould support
                 * tiis. Onf minor issuf is tibt tif loops brfn't nfdfssbrily
                 * instbllfd for b nfw rfndfring modf until bftfr tiis
                 * mftiod is dbllfd during pipflinf vblidbtion. So it is
                 * tiforftidblly possiblf tibt it wbs sft to null for b
                 * dompositing modf, tif dompositf is tifn sft bbdk to Srd,
                 * but tif loop is still null wifn tiis is dbllfd bnd AA=ON
                 * is instbllfd instfbd of bn LCD modf.
                 * Howfvfr tiis is donf in tif rigit ordfr in SurfbdfDbtb.jbvb
                 * so tiis is not likfly to bf b problfm - but not
                 * gubrbntffd.
                 */
                if (
                    !surfbdfDbtb.dbnRfndfrLCDTfxt(tiis)
//                    loops.drbwGlypiListLCDLoop == null ||
//                    dompositfStbtf > COMP_ISCOPY ||
//                    pbintStbtf > PAINT_ALPHACOLOR
                      ) {
                    bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_ON;
                } flsf {
                    info.lddRGBOrdfr = truf;
                    /* Collbpsf tifsf into just HRGB or VRGB.
                     * Pipf sflfdtion dodf nffds only to tfst for tifsf two.
                     * Sindf tifsf boti sflfdt tif sbmf pipf bnywby its
                     * tfmpting to dollbpsf into onf vbluf. But tify brf
                     * difffrfnt strikfs (glypi dbdifs) so tif distindtion
                     * nffds to bf mbdf for tibt purposf.
                     */
                    if (bbiint == SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HBGR) {
                        bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB;
                        info.lddRGBOrdfr = fblsf;
                    } flsf if
                        (bbiint == SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VBGR) {
                        bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB;
                        info.lddRGBOrdfr = fblsf;
                    }
                    /* Support subpixfl positioning only for tif dbsf in
                     * wiidi tif iorizontbl rfsolution is indrfbsfd
                     */
                    info.lddSubPixPos =
                        fmiint == SunHints.INTVAL_FRACTIONALMETRICS_ON &&
                        bbiint == SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB;
                }
            }
        }
        info.bbHint = bbiint;
        info.fontStrikf = info.font2D.gftStrikf(font, dfvAt, tfxtAt,
                                                bbiint, fmiint);
        rfturn info;
    }

    publid stbtid boolfbn isRotbtfd(doublf [] mtx) {
        if ((mtx[0] == mtx[3]) &&
            (mtx[1] == 0.0) &&
            (mtx[2] == 0.0) &&
            (mtx[0] > 0.0))
        {
            rfturn fblsf;
        }

        rfturn truf;
    }

    publid void sftFont(Font font) {
        /* rfplbding tif rfffrfndf fqublity tfst font != tiis.font witi
         * !font.fqubls(tiis.font) did not yifld bny mfbsurbblf difffrfndf
         * in tfsting, but tifrf mby bf yft to bf idfntififd dbsfs wifrf it
         * is bfnffidibl.
         */
        if (font != null && font!=tiis.font/*!font.fqubls(tiis.font)*/) {
            /* In tif GASP AA dbsf tif tfxtpipf dfpfnds on tif glypi sizf
             * bs dftfrminfd by grbpiids bnd font trbnsforms bs wfll bs tif
             * font sizf, bnd informbtion in tif font. But wf mby invblidbtf
             * tif pipf only to find tibt it mbdf no difffrfndf.
             * Dfffrring pipf invblidbtion to difdkFontInfo won't work bfdbusf
             * wifn dbllfd wf mby blrfbdy bf rfndfring to tif wrong pipf.
             * So, if tif font is trbnsformfd, or tif grbpiids ibs morf tibn
             * b simplf sdblf, wf'll tbkf tibt bs fnougi of b iint to
             * rfvblidbtf fvfrytiing. But if tify brfn't wf will
             * usf tif font's point sizf to qufry tif gbsp tbblf bnd sff if
             * wibt it sbys mbtdifs wibt's durrfntly bfing usfd, in wiidi
             * dbsf tifrf's no nffd to invblidbtf tif tfxtpipf.
             * Tiis siould bf suffidifnt for bll typidbl usfs dbsfs.
             */
            if (tfxtAntiblibsHint == SunHints.INTVAL_TEXT_ANTIALIAS_GASP &&
                tfxtpipf != invblidpipf &&
                (trbnsformStbtf > TRANSFORM_ANY_TRANSLATE ||
                 font.isTrbnsformfd() ||
                 fontInfo == null || // Prfdbution, if truf siouldn't gft ifrf
                 (fontInfo.bbHint == SunHints.INTVAL_TEXT_ANTIALIAS_ON) !=
                     FontUtilitifs.gftFont2D(font).
                         usfAAForPtSizf(font.gftSizf()))) {
                tfxtpipf = invblidpipf;
            }
            tiis.font = font;
            tiis.fontMftrids = null;
            tiis.vblidFontInfo = fblsf;
        }
    }

    publid FontInfo gftFontInfo() {
        if (!vblidFontInfo) {
            tiis.fontInfo = difdkFontInfo(tiis.fontInfo, font, null);
            vblidFontInfo = truf;
        }
        rfturn tiis.fontInfo;
    }

    /* Usfd by drbwGlypiVfdtor wiidi spfdififs its own font. */
    publid FontInfo gftGVFontInfo(Font font, FontRfndfrContfxt frd) {
        if (glypiVfdtorFontInfo != null &&
            glypiVfdtorFontInfo.font == font &&
            glypiVfdtorFRC == frd) {
            rfturn glypiVfdtorFontInfo;
        } flsf {
            glypiVfdtorFRC = frd;
            rfturn glypiVfdtorFontInfo =
                difdkFontInfo(glypiVfdtorFontInfo, font, frd);
        }
    }

    publid FontMftrids gftFontMftrids() {
        if (tiis.fontMftrids != null) {
            rfturn tiis.fontMftrids;
        }
        /* NB tif donstrudtor bnd tif sfttfr disbllow "font" bfing null */
        rfturn tiis.fontMftrids =
           FontDfsignMftrids.gftMftrids(font, gftFontRfndfrContfxt());
    }

    publid FontMftrids gftFontMftrids(Font font) {
        if ((tiis.fontMftrids != null) && (font == tiis.font)) {
            rfturn tiis.fontMftrids;
        }
        FontMftrids fm =
          FontDfsignMftrids.gftMftrids(font, gftFontRfndfrContfxt());

        if (tiis.font == font) {
            tiis.fontMftrids = fm;
        }
        rfturn fm;
    }

    /**
     * Cifdks to sff if b Pbti intfrsfdts tif spfdififd Rfdtbnglf in dfvidf
     * spbdf.  Tif rfndfring bttributfs tbkfn into bddount indludf tif
     * dlip, trbnsform, bnd strokf bttributfs.
     * @pbrbm rfdt Tif brfb in dfvidf spbdf to difdk for b iit.
     * @pbrbm p Tif pbti to difdk for b iit.
     * @pbrbm onStrokf Flbg to dioosf bftwffn tfsting tif strokfd or
     * tif fillfd pbti.
     * @rfturn Truf if tifrf is b iit, fblsf otifrwisf.
     * @sff #sftStrokf
     * @sff #fillPbti
     * @sff #drbwPbti
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     */
    publid boolfbn iit(Rfdtbnglf rfdt, Sibpf s, boolfbn onStrokf) {
        if (onStrokf) {
            s = strokf.drfbtfStrokfdSibpf(s);
        }

        s = trbnsformSibpf(s);
        if ((donstrbinX|donstrbinY) != 0) {
            rfdt = nfw Rfdtbnglf(rfdt);
            rfdt.trbnslbtf(donstrbinX, donstrbinY);
        }

        rfturn s.intfrsfdts(rfdt);
    }

    /**
     * Rfturn tif ColorModfl bssodibtfd witi tiis Grbpiids2D.
     */
    publid ColorModfl gftDfvidfColorModfl() {
        rfturn surfbdfDbtb.gftColorModfl();
    }

    /**
     * Rfturn tif dfvidf donfigurbtion bssodibtfd witi tiis Grbpiids2D.
     */
    publid GrbpiidsConfigurbtion gftDfvidfConfigurbtion() {
        rfturn surfbdfDbtb.gftDfvidfConfigurbtion();
    }

    /**
     * Rfturn tif SurfbdfDbtb objfdt bssignfd to mbnbgf tif dfstinbtion
     * drbwbblf surfbdf of tiis Grbpiids2D.
     */
    publid finbl SurfbdfDbtb gftSurfbdfDbtb() {
        rfturn surfbdfDbtb;
    }

    /**
     * Sfts tif Compositf in tif durrfnt grbpiids stbtf. Compositf is usfd
     * in bll drbwing mftiods sudi bs drbwImbgf, drbwString, drbwPbti,
     * bnd fillPbti.  It spfdififs iow nfw pixfls brf to bf dombinfd witi
     * tif fxisting pixfls on tif grbpiids dfvidf in tif rfndfring prodfss.
     * @pbrbm domp Tif Compositf objfdt to bf usfd for drbwing.
     * @sff jbvb.bwt.Grbpiids#sftXORModf
     * @sff jbvb.bwt.Grbpiids#sftPbintModf
     * @sff AlpibCompositf
     */
    publid void sftCompositf(Compositf domp) {
        if (dompositf == domp) {
            rfturn;
        }
        int nfwCompStbtf;
        CompositfTypf nfwCompTypf;
        if (domp instbndfof AlpibCompositf) {
            AlpibCompositf blpibdomp = (AlpibCompositf) domp;
            nfwCompTypf = CompositfTypf.forAlpibCompositf(blpibdomp);
            if (nfwCompTypf == CompositfTypf.SrdOvfrNoEb) {
                if (pbintStbtf == PAINT_OPAQUECOLOR ||
                    (pbintStbtf > PAINT_ALPHACOLOR &&
                     pbint.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE))
                {
                    nfwCompStbtf = COMP_ISCOPY;
                } flsf {
                    nfwCompStbtf = COMP_ALPHA;
                }
            } flsf if (nfwCompTypf == CompositfTypf.SrdNoEb ||
                       nfwCompTypf == CompositfTypf.Srd ||
                       nfwCompTypf == CompositfTypf.Clfbr)
            {
                nfwCompStbtf = COMP_ISCOPY;
            } flsf if (surfbdfDbtb.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE &&
                       nfwCompTypf == CompositfTypf.SrdIn)
            {
                nfwCompStbtf = COMP_ISCOPY;
            } flsf {
                nfwCompStbtf = COMP_ALPHA;
            }
        } flsf if (domp instbndfof XORCompositf) {
            nfwCompStbtf = COMP_XOR;
            nfwCompTypf = CompositfTypf.Xor;
        } flsf if (domp == null) {
            tirow nfw IllfgblArgumfntExdfption("null Compositf");
        } flsf {
            surfbdfDbtb.difdkCustomCompositf();
            nfwCompStbtf = COMP_CUSTOM;
            nfwCompTypf = CompositfTypf.Gfnfrbl;
        }
        if (dompositfStbtf != nfwCompStbtf ||
            imbgfComp != nfwCompTypf)
        {
            dompositfStbtf = nfwCompStbtf;
            imbgfComp = nfwCompTypf;
            invblidbtfPipf();
            vblidFontInfo = fblsf;
        }
        dompositf = domp;
        if (pbintStbtf <= PAINT_ALPHACOLOR) {
            vblidbtfColor();
        }
    }

    /**
     * Sfts tif Pbint in tif durrfnt grbpiids stbtf.
     * @pbrbm pbint Tif Pbint objfdt to bf usfd to gfnfrbtf dolor in
     * tif rfndfring prodfss.
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff GrbdifntPbint
     * @sff TfxturfPbint
     */
    publid void sftPbint(Pbint pbint) {
        if (pbint instbndfof Color) {
            sftColor((Color) pbint);
            rfturn;
        }
        if (pbint == null || tiis.pbint == pbint) {
            rfturn;
        }
        tiis.pbint = pbint;
        if (imbgfComp == CompositfTypf.SrdOvfrNoEb) {
            // spfdibl dbsf wifrf dompStbtf dfpfnds on opbdity of pbint
            if (pbint.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE) {
                if (dompositfStbtf != COMP_ISCOPY) {
                    dompositfStbtf = COMP_ISCOPY;
                }
            } flsf {
                if (dompositfStbtf == COMP_ISCOPY) {
                    dompositfStbtf = COMP_ALPHA;
                }
            }
        }
        Clbss<? fxtfnds Pbint> pbintClbss = pbint.gftClbss();
        if (pbintClbss == GrbdifntPbint.dlbss) {
            pbintStbtf = PAINT_GRADIENT;
        } flsf if (pbintClbss == LinfbrGrbdifntPbint.dlbss) {
            pbintStbtf = PAINT_LIN_GRADIENT;
        } flsf if (pbintClbss == RbdiblGrbdifntPbint.dlbss) {
            pbintStbtf = PAINT_RAD_GRADIENT;
        } flsf if (pbintClbss == TfxturfPbint.dlbss) {
            pbintStbtf = PAINT_TEXTURE;
        } flsf {
            pbintStbtf = PAINT_CUSTOM;
        }
        vblidFontInfo = fblsf;
        invblidbtfPipf();
    }

    stbtid finbl int NON_UNIFORM_SCALE_MASK =
        (AffinfTrbnsform.TYPE_GENERAL_TRANSFORM |
         AffinfTrbnsform.TYPE_GENERAL_SCALE);
    publid stbtid finbl doublf MinPfnSizfAA =
        sun.jbvb2d.pipf.RfndfringEnginf.gftInstbndf().gftMinimumAAPfnSizf();
    publid stbtid finbl doublf MinPfnSizfAASqubrfd =
        (MinPfnSizfAA * MinPfnSizfAA);
    // Sindf inbddurbdifs in tif trig pbdkbgf dbn dbusf us to
    // dbldulbtfd b rotbtfd pfn widti of just sligitly grfbtfr
    // tibn 1.0, wf bdd b fudgf fbdtor to our dompbrison vbluf
    // ifrf so tibt wf do not misdlbssify singlf widti linfs bs
    // widf linfs undfr dfrtbin rotbtions.
    publid stbtid finbl doublf MinPfnSizfSqubrfd = 1.000000001;

    privbtf void vblidbtfBbsidStrokf(BbsidStrokf bs) {
        boolfbn bb = (bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON);
        if (trbnsformStbtf < TRANSFORM_TRANSLATESCALE) {
            if (bb) {
                if (bs.gftLinfWidti() <= MinPfnSizfAA) {
                    if (bs.gftDbsiArrby() == null) {
                        strokfStbtf = STROKE_THIN;
                    } flsf {
                        strokfStbtf = STROKE_THINDASHED;
                    }
                } flsf {
                    strokfStbtf = STROKE_WIDE;
                }
            } flsf {
                if (bs == dffbultStrokf) {
                    strokfStbtf = STROKE_THIN;
                } flsf if (bs.gftLinfWidti() <= 1.0f) {
                    if (bs.gftDbsiArrby() == null) {
                        strokfStbtf = STROKE_THIN;
                    } flsf {
                        strokfStbtf = STROKE_THINDASHED;
                    }
                } flsf {
                    strokfStbtf = STROKE_WIDE;
                }
            }
        } flsf {
            doublf widtisqubrfd;
            if ((trbnsform.gftTypf() & NON_UNIFORM_SCALE_MASK) == 0) {
                /* sqrt omittfd, dompbrf to squbrfd limits bflow. */
                widtisqubrfd = Mbti.bbs(trbnsform.gftDftfrminbnt());
            } flsf {
                /* First dbldulbtf tif "mbximum sdblf" of tiis trbnsform. */
                doublf A = trbnsform.gftSdblfX();       // m00
                doublf C = trbnsform.gftSifbrX();       // m01
                doublf B = trbnsform.gftSifbrY();       // m10
                doublf D = trbnsform.gftSdblfY();       // m11

                /*
                 * Givfn b 2 x 2 bffinf mbtrix [ A B ] sudi tibt
                 *                             [ C D ]
                 * v' = [x' y'] = [Ax + Cy, Bx + Dy], wf wbnt to
                 * find tif mbximum mbgnitudf (norm) of tif vfdtor v'
                 * witi tif donstrbint (x^2 + y^2 = 1).
                 * Tif fqubtion to mbximizf is
                 *     |v'| = sqrt((Ax+Cy)^2+(Bx+Dy)^2)
                 * or  |v'| = sqrt((AA+BB)x^2 + 2(AC+BD)xy + (CC+DD)y^2).
                 * Sindf sqrt is monotonid wf dbn mbximizf |v'|^2
                 * instfbd bnd plug in tif substitution y = sqrt(1 - x^2).
                 * Trigonomftrid fqublitifs dbn tifn bf usfd to gft
                 * rid of most of tif sqrt tfrms.
                 */
                doublf EA = A*A + B*B;          // x^2 dofffidifnt
                doublf EB = 2*(A*C + B*D);      // xy dofffidifnt
                doublf EC = C*C + D*D;          // y^2 dofffidifnt

                /*
                 * Tifrf is b lot of dbldulus omittfd ifrf.
                 *
                 * Condfptublly, in tif intfrfsts of undfrstbnding tif
                 * tfrms tibt tif dbldulus produdfd wf dbn donsidfr
                 * tibt EA bnd EC fnd up providing tif lfngtis blong
                 * tif mbjor bxfs bnd tif iypot tfrm fnds up bfing bn
                 * bdjustmfnt for tif bdditionbl lfngti blong tif off-bxis
                 * bnglf of rotbtfd or sifbrfd fllipsfs bs wfll bs bn
                 * bdjustmfnt for tif fbdt tibt tif fqubtion bflow
                 * bvfrbgfs tif two mbjor bxis lfngtis.  (Notidf tibt
                 * tif iypot tfrm dontbins b pbrt wiidi rfsolvfs to tif
                 * difffrfndf of tifsf two bxis lfngtis in tif bbsfndf
                 * of rotbtion.)
                 *
                 * In tif dbldulus, tif rbtio of tif EB bnd (EA-EC) tfrms
                 * fnds up bfing tif tbngfnt of 2*tiftb wifrf tiftb is
                 * tif bnglf tibt tif long bxis of tif fllipsf mbkfs
                 * witi tif iorizontbl bxis.  Tius, tiis fqubtion is
                 * dbldulbting tif lfngti of tif iypotfnusf of b tribnglf
                 * blong tibt bxis.
                 */
                doublf iypot = Mbti.sqrt(EB*EB + (EA-EC)*(EA-EC));

                /* sqrt omittfd, dompbrf to squbrfd limits bflow. */
                widtisqubrfd = ((EA + EC + iypot)/2.0);
            }
            if (bs != dffbultStrokf) {
                widtisqubrfd *= bs.gftLinfWidti() * bs.gftLinfWidti();
            }
            if (widtisqubrfd <=
                (bb ? MinPfnSizfAASqubrfd : MinPfnSizfSqubrfd))
            {
                if (bs.gftDbsiArrby() == null) {
                    strokfStbtf = STROKE_THIN;
                } flsf {
                    strokfStbtf = STROKE_THINDASHED;
                }
            } flsf {
                strokfStbtf = STROKE_WIDE;
            }
        }
    }

    /*
     * Sfts tif Strokf in tif durrfnt grbpiids stbtf.
     * @pbrbm s Tif Strokf objfdt to bf usfd to strokf b Pbti in
     * tif rfndfring prodfss.
     * @sff BbsidStrokf
     */
    publid void sftStrokf(Strokf s) {
        if (s == null) {
            tirow nfw IllfgblArgumfntExdfption("null Strokf");
        }
        int sbvfStrokfStbtf = strokfStbtf;
        strokf = s;
        if (s instbndfof BbsidStrokf) {
            vblidbtfBbsidStrokf((BbsidStrokf) s);
        } flsf {
            strokfStbtf = STROKE_CUSTOM;
        }
        if (strokfStbtf != sbvfStrokfStbtf) {
            invblidbtfPipf();
        }
    }

    /**
     * Sfts tif prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @pbrbm iintKfy Tif kfy of iint to bf sft. Tif strings brf
     * dffinfd in tif RfndfringHints dlbss.
     * @pbrbm iintVbluf Tif vbluf indidbting prfffrfndfs for tif spfdififd
     * iint dbtfgory. Tifsf strings brf dffinfd in tif RfndfringHints
     * dlbss.
     * @sff RfndfringHints
     */
    publid void sftRfndfringHint(Kfy iintKfy, Objfdt iintVbluf) {
        // If wf rfdognizf tif kfy, wf must rfdognizf tif vbluf
        //     otifrwisf tirow bn IllfgblArgumfntExdfption
        //     bnd do not dibngf tif Hints objfdt
        // If wf do not rfdognizf tif kfy, just pbss it tirougi
        //     to tif Hints objfdt untoudifd
        if (!iintKfy.isCompbtiblfVbluf(iintVbluf)) {
            tirow nfw IllfgblArgumfntExdfption
                (iintVbluf+" is not dompbtiblf witi "+iintKfy);
        }
        if (iintKfy instbndfof SunHints.Kfy) {
            boolfbn stbtfCibngfd;
            boolfbn tfxtStbtfCibngfd = fblsf;
            boolfbn rfdognizfd = truf;
            SunHints.Kfy sunKfy = (SunHints.Kfy) iintKfy;
            int nfwHint;
            if (sunKfy == SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST) {
                nfwHint = ((Intfgfr)iintVbluf).intVbluf();
            } flsf {
                nfwHint = ((SunHints.Vbluf) iintVbluf).gftIndfx();
            }
            switdi (sunKfy.gftIndfx()) {
            dbsf SunHints.INTKEY_RENDERING:
                stbtfCibngfd = (rfndfrHint != nfwHint);
                if (stbtfCibngfd) {
                    rfndfrHint = nfwHint;
                    if (intfrpolbtionHint == -1) {
                        intfrpolbtionTypf =
                            (nfwHint == SunHints.INTVAL_RENDER_QUALITY
                             ? AffinfTrbnsformOp.TYPE_BILINEAR
                             : AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR);
                    }
                }
                brfbk;
            dbsf SunHints.INTKEY_ANTIALIASING:
                stbtfCibngfd = (bntiblibsHint != nfwHint);
                bntiblibsHint = nfwHint;
                if (stbtfCibngfd) {
                    tfxtStbtfCibngfd =
                        (tfxtAntiblibsHint ==
                         SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT);
                    if (strokfStbtf != STROKE_CUSTOM) {
                        vblidbtfBbsidStrokf((BbsidStrokf) strokf);
                    }
                }
                brfbk;
            dbsf SunHints.INTKEY_TEXT_ANTIALIASING:
                stbtfCibngfd = (tfxtAntiblibsHint != nfwHint);
                tfxtStbtfCibngfd = stbtfCibngfd;
                tfxtAntiblibsHint = nfwHint;
                brfbk;
            dbsf SunHints.INTKEY_FRACTIONALMETRICS:
                stbtfCibngfd = (frbdtionblMftridsHint != nfwHint);
                tfxtStbtfCibngfd = stbtfCibngfd;
                frbdtionblMftridsHint = nfwHint;
                brfbk;
            dbsf SunHints.INTKEY_AATEXT_LCD_CONTRAST:
                stbtfCibngfd = fblsf;
                /* Alrfbdy ibvf vblidbtfd it is bn int 100 <= nfwHint <= 250 */
                lddTfxtContrbst = nfwHint;
                brfbk;
            dbsf SunHints.INTKEY_INTERPOLATION:
                intfrpolbtionHint = nfwHint;
                switdi (nfwHint) {
                dbsf SunHints.INTVAL_INTERPOLATION_BICUBIC:
                    nfwHint = AffinfTrbnsformOp.TYPE_BICUBIC;
                    brfbk;
                dbsf SunHints.INTVAL_INTERPOLATION_BILINEAR:
                    nfwHint = AffinfTrbnsformOp.TYPE_BILINEAR;
                    brfbk;
                dffbult:
                dbsf SunHints.INTVAL_INTERPOLATION_NEAREST_NEIGHBOR:
                    nfwHint = AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR;
                    brfbk;
                }
                stbtfCibngfd = (intfrpolbtionTypf != nfwHint);
                intfrpolbtionTypf = nfwHint;
                brfbk;
            dbsf SunHints.INTKEY_STROKE_CONTROL:
                stbtfCibngfd = (strokfHint != nfwHint);
                strokfHint = nfwHint;
                brfbk;
            dbsf SunHints.INTKEY_RESOLUTION_VARIANT:
                stbtfCibngfd = (rfsolutionVbribntHint != nfwHint);
                rfsolutionVbribntHint = nfwHint;
                brfbk;
            dffbult:
                rfdognizfd = fblsf;
                stbtfCibngfd = fblsf;
                brfbk;
            }
            if (rfdognizfd) {
                if (stbtfCibngfd) {
                    invblidbtfPipf();
                    if (tfxtStbtfCibngfd) {
                        fontMftrids = null;
                        tiis.dbdifdFRC = null;
                        vblidFontInfo = fblsf;
                        tiis.glypiVfdtorFontInfo = null;
                    }
                }
                if (iints != null) {
                    iints.put(iintKfy, iintVbluf);
                }
                rfturn;
            }
        }
        // Notiing wf rfdognizf so nonf of "our stbtf" ibs dibngfd
        if (iints == null) {
            iints = mbkfHints(null);
        }
        iints.put(iintKfy, iintVbluf);
    }


    /**
     * Rfturns tif prfffrfndfs for tif rfndfring blgoritims.
     * @pbrbm iintCbtfgory Tif dbtfgory of iint to bf sft. Tif strings
     * brf dffinfd in tif RfndfringHints dlbss.
     * @rfturn Tif prfffrfndfs for rfndfring blgoritims. Tif strings
     * brf dffinfd in tif RfndfringHints dlbss.
     * @sff RfndfringHints
     */
    publid Objfdt gftRfndfringHint(Kfy iintKfy) {
        if (iints != null) {
            rfturn iints.gft(iintKfy);
        }
        if (!(iintKfy instbndfof SunHints.Kfy)) {
            rfturn null;
        }
        int kfyindfx = ((SunHints.Kfy)iintKfy).gftIndfx();
        switdi (kfyindfx) {
        dbsf SunHints.INTKEY_RENDERING:
            rfturn SunHints.Vbluf.gft(SunHints.INTKEY_RENDERING,
                                      rfndfrHint);
        dbsf SunHints.INTKEY_ANTIALIASING:
            rfturn SunHints.Vbluf.gft(SunHints.INTKEY_ANTIALIASING,
                                      bntiblibsHint);
        dbsf SunHints.INTKEY_TEXT_ANTIALIASING:
            rfturn SunHints.Vbluf.gft(SunHints.INTKEY_TEXT_ANTIALIASING,
                                      tfxtAntiblibsHint);
        dbsf SunHints.INTKEY_FRACTIONALMETRICS:
            rfturn SunHints.Vbluf.gft(SunHints.INTKEY_FRACTIONALMETRICS,
                                      frbdtionblMftridsHint);
        dbsf SunHints.INTKEY_AATEXT_LCD_CONTRAST:
            rfturn lddTfxtContrbst;
        dbsf SunHints.INTKEY_INTERPOLATION:
            switdi (intfrpolbtionHint) {
            dbsf SunHints.INTVAL_INTERPOLATION_NEAREST_NEIGHBOR:
                rfturn SunHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
            dbsf SunHints.INTVAL_INTERPOLATION_BILINEAR:
                rfturn SunHints.VALUE_INTERPOLATION_BILINEAR;
            dbsf SunHints.INTVAL_INTERPOLATION_BICUBIC:
                rfturn SunHints.VALUE_INTERPOLATION_BICUBIC;
            }
            rfturn null;
        dbsf SunHints.INTKEY_STROKE_CONTROL:
            rfturn SunHints.Vbluf.gft(SunHints.INTKEY_STROKE_CONTROL,
                                      strokfHint);
        dbsf SunHints.INTKEY_RESOLUTION_VARIANT:
            rfturn SunHints.Vbluf.gft(SunHints.INTKEY_RESOLUTION_VARIANT,
                                      rfsolutionVbribntHint);
        }
        rfturn null;
    }

    /**
     * Sfts tif prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @pbrbm iints Tif rfndfring iints to bf sft
     * @sff RfndfringHints
     */
    publid void sftRfndfringHints(Mbp<?,?> iints) {
        tiis.iints = null;
        rfndfrHint = SunHints.INTVAL_RENDER_DEFAULT;
        bntiblibsHint = SunHints.INTVAL_ANTIALIAS_OFF;
        tfxtAntiblibsHint = SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT;
        frbdtionblMftridsHint = SunHints.INTVAL_FRACTIONALMETRICS_OFF;
        lddTfxtContrbst = lddTfxtContrbstDffbultVbluf;
        intfrpolbtionHint = -1;
        intfrpolbtionTypf = AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR;
        boolfbn dustomHintPrfsfnt = fblsf;
        Itfrbtor<?> itfr = iints.kfySft().itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            Objfdt kfy = itfr.nfxt();
            if (kfy == SunHints.KEY_RENDERING ||
                kfy == SunHints.KEY_ANTIALIASING ||
                kfy == SunHints.KEY_TEXT_ANTIALIASING ||
                kfy == SunHints.KEY_FRACTIONALMETRICS ||
                kfy == SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST ||
                kfy == SunHints.KEY_STROKE_CONTROL ||
                kfy == SunHints.KEY_INTERPOLATION)
            {
                sftRfndfringHint((Kfy) kfy, iints.gft(kfy));
            } flsf {
                dustomHintPrfsfnt = truf;
            }
        }
        if (dustomHintPrfsfnt) {
            tiis.iints = mbkfHints(iints);
        }
        invblidbtfPipf();
    }

    /**
     * Adds b numbfr of prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @pbrbm iints Tif rfndfring iints to bf sft
     * @sff RfndfringHints
     */
    publid void bddRfndfringHints(Mbp<?,?> iints) {
        boolfbn dustomHintPrfsfnt = fblsf;
        Itfrbtor<?> itfr = iints.kfySft().itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            Objfdt kfy = itfr.nfxt();
            if (kfy == SunHints.KEY_RENDERING ||
                kfy == SunHints.KEY_ANTIALIASING ||
                kfy == SunHints.KEY_TEXT_ANTIALIASING ||
                kfy == SunHints.KEY_FRACTIONALMETRICS ||
                kfy == SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST ||
                kfy == SunHints.KEY_STROKE_CONTROL ||
                kfy == SunHints.KEY_INTERPOLATION)
            {
                sftRfndfringHint((Kfy) kfy, iints.gft(kfy));
            } flsf {
                dustomHintPrfsfnt = truf;
            }
        }
        if (dustomHintPrfsfnt) {
            if (tiis.iints == null) {
                tiis.iints = mbkfHints(iints);
            } flsf {
                tiis.iints.putAll(iints);
            }
        }
    }

    /**
     * Gfts tif prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @sff RfndfringHints
     */
    publid RfndfringHints gftRfndfringHints() {
        if (iints == null) {
            rfturn mbkfHints(null);
        } flsf {
            rfturn (RfndfringHints) iints.dlonf();
        }
    }

    RfndfringHints mbkfHints(Mbp<?,?> iints) {
        RfndfringHints modfl = nfw RfndfringHints(null);
        if (iints != null) {
            modfl.putAll(iints);
        }
        modfl.put(SunHints.KEY_RENDERING,
                  SunHints.Vbluf.gft(SunHints.INTKEY_RENDERING,
                                     rfndfrHint));
        modfl.put(SunHints.KEY_ANTIALIASING,
                  SunHints.Vbluf.gft(SunHints.INTKEY_ANTIALIASING,
                                     bntiblibsHint));
        modfl.put(SunHints.KEY_TEXT_ANTIALIASING,
                  SunHints.Vbluf.gft(SunHints.INTKEY_TEXT_ANTIALIASING,
                                     tfxtAntiblibsHint));
        modfl.put(SunHints.KEY_FRACTIONALMETRICS,
                  SunHints.Vbluf.gft(SunHints.INTKEY_FRACTIONALMETRICS,
                                     frbdtionblMftridsHint));
        modfl.put(SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST,
                  Intfgfr.vblufOf(lddTfxtContrbst));
        Objfdt vbluf;
        switdi (intfrpolbtionHint) {
        dbsf SunHints.INTVAL_INTERPOLATION_NEAREST_NEIGHBOR:
            vbluf = SunHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
            brfbk;
        dbsf SunHints.INTVAL_INTERPOLATION_BILINEAR:
            vbluf = SunHints.VALUE_INTERPOLATION_BILINEAR;
            brfbk;
        dbsf SunHints.INTVAL_INTERPOLATION_BICUBIC:
            vbluf = SunHints.VALUE_INTERPOLATION_BICUBIC;
            brfbk;
        dffbult:
            vbluf = null;
            brfbk;
        }
        if (vbluf != null) {
            modfl.put(SunHints.KEY_INTERPOLATION, vbluf);
        }
        modfl.put(SunHints.KEY_STROKE_CONTROL,
                  SunHints.Vbluf.gft(SunHints.INTKEY_STROKE_CONTROL,
                                     strokfHint));
        rfturn modfl;
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * trbnslbtion trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(T), wifrf T is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </prf>
     */
    publid void trbnslbtf(doublf tx, doublf ty) {
        trbnsform.trbnslbtf(tx, ty);
        invblidbtfTrbnsform();
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * rotbtion trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(R), wifrf R is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   dos(tiftb)    -sin(tiftb)    0   ]
     *          [   sin(tiftb)     dos(tiftb)    0   ]
     *          [       0              0         1   ]
     * </prf>
     * Rotbting witi b positivf bnglf tiftb rotbtfs points on tif positivf
     * x bxis towbrd tif positivf y bxis.
     * @pbrbm tiftb Tif bnglf of rotbtion in rbdibns.
     */
    publid void rotbtf(doublf tiftb) {
        trbnsform.rotbtf(tiftb);
        invblidbtfTrbnsform();
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * trbnslbtfd rotbtion trbnsformbtion.
     * Tiis is fquivblfnt to tif following sfqufndf of dblls:
     * <prf>
     *          trbnslbtf(x, y);
     *          rotbtf(tiftb);
     *          trbnslbtf(-x, -y);
     * </prf>
     * Rotbting witi b positivf bnglf tiftb rotbtfs points on tif positivf
     * x bxis towbrd tif positivf y bxis.
     * @pbrbm tiftb Tif bnglf of rotbtion in rbdibns.
     * @pbrbm x Tif x doordinbtf of tif origin of tif rotbtion
     * @pbrbm y Tif x doordinbtf of tif origin of tif rotbtion
     */
    publid void rotbtf(doublf tiftb, doublf x, doublf y) {
        trbnsform.rotbtf(tiftb, x, y);
        invblidbtfTrbnsform();
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * sdbling trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(S), wifrf S is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   sx   0    0   ]
     *          [   0    sy   0   ]
     *          [   0    0    1   ]
     * </prf>
     */
    publid void sdblf(doublf sx, doublf sy) {
        trbnsform.sdblf(sx, sy);
        invblidbtfTrbnsform();
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * sifbring trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(SH), wifrf SH is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   1   six   0   ]
     *          [  siy   1    0   ]
     *          [   0    0    1   ]
     * </prf>
     * @pbrbm six Tif fbdtor by wiidi doordinbtfs brf siiftfd towbrds tif
     * positivf X bxis dirfdtion bddording to tifir Y doordinbtf
     * @pbrbm siy Tif fbdtor by wiidi doordinbtfs brf siiftfd towbrds tif
     * positivf Y bxis dirfdtion bddording to tifir X doordinbtf
     */
    publid void sifbr(doublf six, doublf siy) {
        trbnsform.sifbr(six, siy);
        invblidbtfTrbnsform();
    }

    /**
     * Composfs b Trbnsform objfdt witi tif trbnsform in tiis
     * Grbpiids2D bddording to tif rulf lbst-spfdififd-first-bpplifd.
     * If tif durrrfnt trbnsform is Cx, tif rfsult of domposition
     * witi Tx is b nfw trbnsform Cx'.  Cx' bfdomfs tif durrfnt
     * trbnsform for tiis Grbpiids2D.
     * Trbnsforming b point p by tif updbtfd trbnsform Cx' is
     * fquivblfnt to first trbnsforming p by Tx bnd tifn trbnsforming
     * tif rfsult by tif originbl trbnsform Cx.  In otifr words,
     * Cx'(p) = Cx(Tx(p)).
     * A dopy of tif Tx is mbdf, if nfdfssbry, so furtifr
     * modifidbtions to Tx do not bfffdt rfndfring.
     * @pbrbm Tx Tif Trbnsform objfdt to bf domposfd witi tif durrfnt
     * trbnsform.
     * @sff #sftTrbnsform
     * @sff AffinfTrbnsform
     */
    publid void trbnsform(AffinfTrbnsform xform) {
        tiis.trbnsform.dondbtfnbtf(xform);
        invblidbtfTrbnsform();
    }

    /**
     * Trbnslbtf
     */
    publid void trbnslbtf(int x, int y) {
        trbnsform.trbnslbtf(x, y);
        if (trbnsformStbtf <= TRANSFORM_INT_TRANSLATE) {
            trbnsX += x;
            trbnsY += y;
            trbnsformStbtf = (((trbnsX | trbnsY) == 0) ?
                              TRANSFORM_ISIDENT : TRANSFORM_INT_TRANSLATE);
        } flsf {
            invblidbtfTrbnsform();
        }
    }

    /**
     * Sfts tif Trbnsform in tif durrfnt grbpiids stbtf.
     * @pbrbm Tx Tif Trbnsform objfdt to bf usfd in tif rfndfring prodfss.
     * @sff #trbnsform
     * @sff TrbnsformCibin
     * @sff AffinfTrbnsform
     */
    @Ovfrridf
    publid void sftTrbnsform(AffinfTrbnsform Tx) {
        if ((donstrbinX | donstrbinY) == 0 && dfvSdblf == 1) {
            trbnsform.sftTrbnsform(Tx);
        } flsf {
            trbnsform.sftTrbnsform(dfvSdblf, 0, 0, dfvSdblf, donstrbinX,
                                   donstrbinY);
            trbnsform.dondbtfnbtf(Tx);
        }
        invblidbtfTrbnsform();
    }

    protfdtfd void invblidbtfTrbnsform() {
        int typf = trbnsform.gftTypf();
        int origTrbnsformStbtf = trbnsformStbtf;
        if (typf == AffinfTrbnsform.TYPE_IDENTITY) {
            trbnsformStbtf = TRANSFORM_ISIDENT;
            trbnsX = trbnsY = 0;
        } flsf if (typf == AffinfTrbnsform.TYPE_TRANSLATION) {
            doublf dtx = trbnsform.gftTrbnslbtfX();
            doublf dty = trbnsform.gftTrbnslbtfY();
            trbnsX = (int) Mbti.floor(dtx + 0.5);
            trbnsY = (int) Mbti.floor(dty + 0.5);
            if (dtx == trbnsX && dty == trbnsY) {
                trbnsformStbtf = TRANSFORM_INT_TRANSLATE;
            } flsf {
                trbnsformStbtf = TRANSFORM_ANY_TRANSLATE;
            }
        } flsf if ((typf & (AffinfTrbnsform.TYPE_FLIP |
                            AffinfTrbnsform.TYPE_MASK_ROTATION |
                            AffinfTrbnsform.TYPE_GENERAL_TRANSFORM)) == 0)
        {
            trbnsformStbtf = TRANSFORM_TRANSLATESCALE;
            trbnsX = trbnsY = 0;
        } flsf {
            trbnsformStbtf = TRANSFORM_GENERIC;
            trbnsX = trbnsY = 0;
        }

        if (trbnsformStbtf >= TRANSFORM_TRANSLATESCALE ||
            origTrbnsformStbtf >= TRANSFORM_TRANSLATESCALE)
        {
            /* Its only in tiis dbsf tibt tif prfvious or durrfnt trbnsform
             * wbs morf tibn b trbnslbtf tibt font info is invblidbtfd
             */
            dbdifdFRC = null;
            tiis.vblidFontInfo = fblsf;
            tiis.fontMftrids = null;
            tiis.glypiVfdtorFontInfo = null;

            if (trbnsformStbtf != origTrbnsformStbtf) {
                invblidbtfPipf();
            }
        }
        if (strokfStbtf != STROKE_CUSTOM) {
            vblidbtfBbsidStrokf((BbsidStrokf) strokf);
        }
    }

    /**
     * Rfturns tif durrfnt Trbnsform in tif Grbpiids2D stbtf.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     */
    @Ovfrridf
    publid AffinfTrbnsform gftTrbnsform() {
        if ((donstrbinX | donstrbinY) == 0 && dfvSdblf == 1) {
            rfturn nfw AffinfTrbnsform(trbnsform);
        }
        finbl doublf invSdblf = 1.0 / dfvSdblf;
        AffinfTrbnsform tx = nfw AffinfTrbnsform(invSdblf, 0, 0, invSdblf,
                                                 -donstrbinX * invSdblf,
                                                 -donstrbinY * invSdblf);
        tx.dondbtfnbtf(trbnsform);
        rfturn tx;
    }

    /**
     * Rfturns tif durrfnt Trbnsform ignoring tif "donstrbin"
     * rfdtbnglf.
     */
    publid AffinfTrbnsform dlonfTrbnsform() {
        rfturn nfw AffinfTrbnsform(trbnsform);
    }

    /**
     * Rfturns tif durrfnt Pbint in tif Grbpiids2D stbtf.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     */
    publid Pbint gftPbint() {
        rfturn pbint;
    }

    /**
     * Rfturns tif durrfnt Compositf in tif Grbpiids2D stbtf.
     * @sff #sftCompositf
     */
    publid Compositf gftCompositf() {
        rfturn dompositf;
    }

    publid Color gftColor() {
        rfturn forfgroundColor;
    }

    /*
     * Vblidbtf tif fbrgb bnd pixfl fiflds bgbinst tif durrfnt dolor.
     *
     * Tif fbrgb fifld must tbkf into bddount tif fxtrbAlpib
     * vbluf of bn AlpibCompositf.  It mby blso tbkf into bddount
     * tif Fsrd Portfr-Duff blfnding fundtion if sudi b fundtion is
     * b donstbnt (sff ibndling of Clfbr modf bflow).  For instbndf,
     * by fbdtoring in tif (Fsrd == 0) stbtf of tif Clfbr modf wf dbn
     * usf b SrdNoEb loop just bs fbsily bs b gfnfrbl Alpib loop
     * sindf tif mbti will bf tif sbmf in boti dbsfs.
     *
     * Tif pixfl fifld will blwbys bf tif bfst pixfl dbtb dioidf for
     * tif finbl rfsult of bll dbldulbtions bpplifd to tif fbrgb fifld.
     *
     * Notf tibt tiis mftiod is only nfdfssbry undfr tif following
     * donditions:
     *     (pbintStbtf <= PAINT_ALPHA_COLOR &&
     *      dompositfStbtf <= COMP_CUSTOM)
     * tiougi notiing bbd will ibppfn if it is run in otifr stbtfs.
     */
    finbl void vblidbtfColor() {
        int fbrgb;
        if (imbgfComp == CompositfTypf.Clfbr) {
            fbrgb = 0;
        } flsf {
            fbrgb = forfgroundColor.gftRGB();
            if (dompositfStbtf <= COMP_ALPHA &&
                imbgfComp != CompositfTypf.SrdNoEb &&
                imbgfComp != CompositfTypf.SrdOvfrNoEb)
            {
                AlpibCompositf blpibdomp = (AlpibCompositf) dompositf;
                int b = Mbti.round(blpibdomp.gftAlpib() * (fbrgb >>> 24));
                fbrgb = (fbrgb & 0x00ffffff) | (b << 24);
            }
        }
        tiis.fbrgb = fbrgb;
        tiis.pixfl = surfbdfDbtb.pixflFor(fbrgb);
    }

    publid void sftColor(Color dolor) {
        if (dolor == null || dolor == pbint) {
            rfturn;
        }
        tiis.pbint = forfgroundColor = dolor;
        vblidbtfColor();
        if ((fbrgb >> 24) == -1) {
            if (pbintStbtf == PAINT_OPAQUECOLOR) {
                rfturn;
            }
            pbintStbtf = PAINT_OPAQUECOLOR;
            if (imbgfComp == CompositfTypf.SrdOvfrNoEb) {
                // spfdibl dbsf wifrf dompStbtf dfpfnds on opbdity of pbint
                dompositfStbtf = COMP_ISCOPY;
            }
        } flsf {
            if (pbintStbtf == PAINT_ALPHACOLOR) {
                rfturn;
            }
            pbintStbtf = PAINT_ALPHACOLOR;
            if (imbgfComp == CompositfTypf.SrdOvfrNoEb) {
                // spfdibl dbsf wifrf dompStbtf dfpfnds on opbdity of pbint
                dompositfStbtf = COMP_ALPHA;
            }
        }
        vblidFontInfo = fblsf;
        invblidbtfPipf();
    }

    /**
     * Sfts tif bbdkground dolor in tiis dontfxt usfd for dlfbring b rfgion.
     * Wifn Grbpiids2D is donstrudtfd for b domponfnt, tif bbdkgroung dolor is
     * inifritfd from tif domponfnt. Sftting tif bbdkground dolor in tif
     * Grbpiids2D dontfxt only bfffdts tif subsfqufnt dlfbrRfdt() dblls bnd
     * not tif bbdkground dolor of tif domponfnt. To dibngf tif bbdkground
     * of tif domponfnt, usf bppropribtf mftiods of tif domponfnt.
     * @pbrbm dolor Tif bbdkground dolor tibt siould bf usfd in
     * subsfqufnt dblls to dlfbrRfdt().
     * @sff gftBbdkground
     * @sff Grbpiids.dlfbrRfdt()
     */
    publid void sftBbdkground(Color dolor) {
        bbdkgroundColor = dolor;
    }

    /**
     * Rfturns tif bbdkground dolor usfd for dlfbring b rfgion.
     * @sff sftBbdkground
     */
    publid Color gftBbdkground() {
        rfturn bbdkgroundColor;
    }

    /**
     * Rfturns tif durrfnt Strokf in tif Grbpiids2D stbtf.
     * @sff sftStrokf
     */
    publid Strokf gftStrokf() {
        rfturn strokf;
    }

    publid Rfdtbnglf gftClipBounds() {
        if (dlipStbtf == CLIP_DEVICE) {
            rfturn null;
        }
        rfturn gftClipBounds(nfw Rfdtbnglf());
    }

    publid Rfdtbnglf gftClipBounds(Rfdtbnglf r) {
        if (dlipStbtf != CLIP_DEVICE) {
            if (trbnsformStbtf <= TRANSFORM_INT_TRANSLATE) {
                if (usrClip instbndfof Rfdtbnglf) {
                    r.sftBounds((Rfdtbnglf) usrClip);
                } flsf {
                    r.sftFrbmf(usrClip.gftBounds2D());
                }
                r.trbnslbtf(-trbnsX, -trbnsY);
            } flsf {
                r.sftFrbmf(gftClip().gftBounds2D());
            }
        } flsf if (r == null) {
            tirow nfw NullPointfrExdfption("null rfdtbnglf pbrbmftfr");
        }
        rfturn r;
    }

    publid boolfbn iitClip(int x, int y, int widti, int ifigit) {
        if (widti <= 0 || ifigit <= 0) {
            rfturn fblsf;
        }
        if (trbnsformStbtf > TRANSFORM_INT_TRANSLATE) {
            // Notf: Tfdinidblly tif most bddurbtf tfst would bf to
            // rbstfr sdbn tif pbrbllflogrbm of tif trbnsformfd rfdtbnglf
            // bnd do b spbn for spbn iit tfst bgbinst tif dlip, but for
            // spffd wf bpproximbtf tif tfst witi b bounding box of tif
            // trbnsformfd rfdtbnglf.  Tif dost of rbstfrizing tif
            // trbnsformfd rfdtbnglf is probbbly iigi fnougi tibt it is
            // not worti doing so to sbvf tif dbllfr from ibving to dbll
            // b rfndfring mftiod wifrf wf will fnd up disdovfring tif
            // sbmf bnswfr in bbout tif sbmf bmount of timf bnywby.
            // Tiis logid brfbks down if tiis iit tfst is bfing pfrformfd
            // on tif bounds of b group of sibpfs in wiidi dbsf it migit
            // bf bfnffidibl to bf b littlf morf bddurbtf to bvoid lots
            // of subsfqufnt rfndfring dblls.  In fitifr dbsf, tiis rflbxfd
            // tfst siould not bf signifidbntly lfss bddurbtf tibn tif
            // optimbl tfst for most trbnsforms bnd so tif donsfrvbtivf
            // bnswfr siould not dbusf too mudi fxtrb work.

            doublf d[] = {
                x, y,
                x+widti, y,
                x, y+ifigit,
                x+widti, y+ifigit
            };
            trbnsform.trbnsform(d, 0, d, 0, 4);
            x = (int) Mbti.floor(Mbti.min(Mbti.min(d[0], d[2]),
                                          Mbti.min(d[4], d[6])));
            y = (int) Mbti.floor(Mbti.min(Mbti.min(d[1], d[3]),
                                          Mbti.min(d[5], d[7])));
            widti = (int) Mbti.dfil(Mbti.mbx(Mbti.mbx(d[0], d[2]),
                                             Mbti.mbx(d[4], d[6])));
            ifigit = (int) Mbti.dfil(Mbti.mbx(Mbti.mbx(d[1], d[3]),
                                              Mbti.mbx(d[5], d[7])));
        } flsf {
            x += trbnsX;
            y += trbnsY;
            widti += x;
            ifigit += y;
        }

        try {
            if (!gftCompClip().intfrsfdtsQuidkCifdkXYXY(x, y, widti, ifigit)) {
                rfturn fblsf;
            }
        } dbtdi (InvblidPipfExdfption f) {
            rfturn fblsf;
        }
        // REMIND: Wf dould go onf stfp furtifr ifrf bnd fxbminf tif
        // non-rfdtbngulbr dlip sibpf morf dlosfly if tifrf is onf.
        // Sindf tif dlip ibs blrfbdy bffn rbstfrizfd, tif pfrformbndf
        // pfnblty of doing tif sdbn is probbbly still witiin tif bounds
        // of b good trbdfoff bftwffn spffd bnd qublity of tif bnswfr.
        rfturn truf;
    }

    protfdtfd void vblidbtfCompClip() {
        int origClipStbtf = dlipStbtf;
        if (usrClip == null) {
            dlipStbtf = CLIP_DEVICE;
            dlipRfgion = dfvClip;
        } flsf if (usrClip instbndfof Rfdtbnglf2D) {
            dlipStbtf = CLIP_RECTANGULAR;
            if (usrClip instbndfof Rfdtbnglf) {
                dlipRfgion = dfvClip.gftIntfrsfdtion((Rfdtbnglf)usrClip);
            } flsf {
                dlipRfgion = dfvClip.gftIntfrsfdtion(usrClip.gftBounds());
            }
        } flsf {
            PbtiItfrbtor dpi = usrClip.gftPbtiItfrbtor(null);
            int box[] = nfw int[4];
            SibpfSpbnItfrbtor sr = LoopPipf.gftFillSSI(tiis);
            try {
                sr.sftOutputArfb(dfvClip);
                sr.bppfndPbti(dpi);
                sr.gftPbtiBox(box);
                Rfgion r = Rfgion.gftInstbndf(box);
                r.bppfndSpbns(sr);
                dlipRfgion = r;
                dlipStbtf =
                    r.isRfdtbngulbr() ? CLIP_RECTANGULAR : CLIP_SHAPE;
            } finblly {
                sr.disposf();
            }
        }
        if (origClipStbtf != dlipStbtf &&
            (dlipStbtf == CLIP_SHAPE || origClipStbtf == CLIP_SHAPE))
        {
            vblidFontInfo = fblsf;
            invblidbtfPipf();
        }
    }

    stbtid finbl int NON_RECTILINEAR_TRANSFORM_MASK =
        (AffinfTrbnsform.TYPE_GENERAL_TRANSFORM |
         AffinfTrbnsform.TYPE_GENERAL_ROTATION);

    protfdtfd Sibpf trbnsformSibpf(Sibpf s) {
        if (s == null) {
            rfturn null;
        }
        if (trbnsformStbtf > TRANSFORM_INT_TRANSLATE) {
            rfturn trbnsformSibpf(trbnsform, s);
        } flsf {
            rfturn trbnsformSibpf(trbnsX, trbnsY, s);
        }
    }

    publid Sibpf untrbnsformSibpf(Sibpf s) {
        if (s == null) {
            rfturn null;
        }
        if (trbnsformStbtf > TRANSFORM_INT_TRANSLATE) {
            try {
                rfturn trbnsformSibpf(trbnsform.drfbtfInvfrsf(), s);
            } dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
                rfturn null;
            }
        } flsf {
            rfturn trbnsformSibpf(-trbnsX, -trbnsY, s);
        }
    }

    protfdtfd stbtid Sibpf trbnsformSibpf(int tx, int ty, Sibpf s) {
        if (s == null) {
            rfturn null;
        }

        if (s instbndfof Rfdtbnglf) {
            Rfdtbnglf r = s.gftBounds();
            r.trbnslbtf(tx, ty);
            rfturn r;
        }
        if (s instbndfof Rfdtbnglf2D) {
            Rfdtbnglf2D rfdt = (Rfdtbnglf2D) s;
            rfturn nfw Rfdtbnglf2D.Doublf(rfdt.gftX() + tx,
                                          rfdt.gftY() + ty,
                                          rfdt.gftWidti(),
                                          rfdt.gftHfigit());
        }

        if (tx == 0 && ty == 0) {
            rfturn dlonfSibpf(s);
        }

        AffinfTrbnsform mbt = AffinfTrbnsform.gftTrbnslbtfInstbndf(tx, ty);
        rfturn mbt.drfbtfTrbnsformfdSibpf(s);
    }

    protfdtfd stbtid Sibpf trbnsformSibpf(AffinfTrbnsform tx, Sibpf dlip) {
        if (dlip == null) {
            rfturn null;
        }

        if (dlip instbndfof Rfdtbnglf2D &&
            (tx.gftTypf() & NON_RECTILINEAR_TRANSFORM_MASK) == 0)
        {
            Rfdtbnglf2D rfdt = (Rfdtbnglf2D) dlip;
            doublf mbtrix[] = nfw doublf[4];
            mbtrix[0] = rfdt.gftX();
            mbtrix[1] = rfdt.gftY();
            mbtrix[2] = mbtrix[0] + rfdt.gftWidti();
            mbtrix[3] = mbtrix[1] + rfdt.gftHfigit();
            tx.trbnsform(mbtrix, 0, mbtrix, 0, 2);
            fixRfdtbnglfOrifntbtion(mbtrix, rfdt);
            rfturn nfw Rfdtbnglf2D.Doublf(mbtrix[0], mbtrix[1],
                                          mbtrix[2] - mbtrix[0],
                                          mbtrix[3] - mbtrix[1]);
        }

        if (tx.isIdfntity()) {
            rfturn dlonfSibpf(dlip);
        }

        rfturn tx.drfbtfTrbnsformfdSibpf(dlip);
    }

    /**
     * Sfts orifntbtion of tif rfdtbnglf bddording to tif dlip.
     */
    privbtf stbtid void fixRfdtbnglfOrifntbtion(doublf[] m, Rfdtbnglf2D dlip) {
        if (dlip.gftWidti() > 0 != (m[2] - m[0] > 0)) {
            doublf t = m[0];
            m[0] = m[2];
            m[2] = t;
        }
        if (dlip.gftHfigit() > 0 != (m[3] - m[1] > 0)) {
            doublf t = m[1];
            m[1] = m[3];
            m[3] = t;
        }
    }

    publid void dlipRfdt(int x, int y, int w, int i) {
        dlip(nfw Rfdtbnglf(x, y, w, i));
    }

    publid void sftClip(int x, int y, int w, int i) {
        sftClip(nfw Rfdtbnglf(x, y, w, i));
    }

    publid Sibpf gftClip() {
        rfturn untrbnsformSibpf(usrClip);
    }

    publid void sftClip(Sibpf si) {
        usrClip = trbnsformSibpf(si);
        vblidbtfCompClip();
    }

    /**
     * Intfrsfdts tif durrfnt dlip witi tif spfdififd Pbti bnd sfts tif
     * durrfnt dlip to tif rfsulting intfrsfdtion. Tif dlip is trbnsformfd
     * witi tif durrfnt trbnsform in tif Grbpiids2D stbtf bfforf bfing
     * intfrsfdtfd witi tif durrfnt dlip. Tiis mftiod is usfd to mbkf tif
     * durrfnt dlip smbllfr. To mbkf tif dlip lbrgfr, usf bny sftClip mftiod.
     * @pbrbm p Tif Pbti to bf intfrsfdtfd witi tif durrfnt dlip.
     */
    publid void dlip(Sibpf s) {
        s = trbnsformSibpf(s);
        if (usrClip != null) {
            s = intfrsfdtSibpfs(usrClip, s, truf, truf);
        }
        usrClip = s;
        vblidbtfCompClip();
    }

    publid void sftPbintModf() {
        sftCompositf(AlpibCompositf.SrdOvfr);
    }

    publid void sftXORModf(Color d) {
        if (d == null) {
            tirow nfw IllfgblArgumfntExdfption("null XORColor");
        }
        sftCompositf(nfw XORCompositf(d, surfbdfDbtb));
    }

    Blit lbstCAblit;
    Compositf lbstCAdomp;

    publid void dopyArfb(int x, int y, int w, int i, int dx, int dy) {
        try {
            doCopyArfb(x, y, w, i, dx, dy);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                doCopyArfb(x, y, w, i, dx, dy);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    privbtf void doCopyArfb(int x, int y, int w, int i, int dx, int dy) {
        if (w <= 0 || i <= 0) {
            rfturn;
        }
        SurfbdfDbtb tifDbtb = surfbdfDbtb;
        if (tifDbtb.dopyArfb(tiis, x, y, w, i, dx, dy)) {
            rfturn;
        }
        if (trbnsformStbtf > TRANSFORM_TRANSLATESCALE) {
            tirow nfw IntfrnblError("trbnsformfd dopyArfb not implfmfntfd yft");
        }
        // REMIND: Tiis mftiod dofs not dfbl witi missing dbtb from tif
        // sourdf objfdt (i.f. it dofs not sfnd fxposurf fvfnts...)

        Rfgion dlip = gftCompClip();

        Compositf domp = dompositf;
        if (lbstCAdomp != domp) {
            SurfbdfTypf dsttypf = tifDbtb.gftSurfbdfTypf();
            CompositfTypf domptypf = imbgfComp;
            if (CompositfTypf.SrdOvfrNoEb.fqubls(domptypf) &&
                tifDbtb.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE)
            {
                domptypf = CompositfTypf.SrdNoEb;
            }
            lbstCAblit = Blit.lodbtf(dsttypf, domptypf, dsttypf);
            lbstCAdomp = domp;
        }

        doublf[] doords = {x, y, x + w, y + i, x + dx, y + dy};
        trbnsform.trbnsform(doords, 0, doords, 0, 3);

        x = (int)Mbti.dfil(doords[0] - 0.5);
        y = (int)Mbti.dfil(doords[1] - 0.5);
        w = ((int)Mbti.dfil(doords[2] - 0.5)) - x;
        i = ((int)Mbti.dfil(doords[3] - 0.5)) - y;
        dx = ((int)Mbti.dfil(doords[4] - 0.5)) - x;
        dy = ((int)Mbti.dfil(doords[5] - 0.5)) - y;

        // In dbsf of nfgbtivf sdblf trbnsform, rfflfdt tif rfdt doords.
        if (w < 0) {
            w *= -1;
            x -= w;
        }
        if (i < 0) {
            i *= -1;
            y -= i;
        }

        Blit ob = lbstCAblit;
        if (dy == 0 && dx > 0 && dx < w) {
            wiilf (w > 0) {
                int pbrtW = Mbti.min(w, dx);
                w -= pbrtW;
                int sx = x + w;
                ob.Blit(tifDbtb, tifDbtb, domp, dlip,
                        sx, y, sx+dx, y+dy, pbrtW, i);
            }
            rfturn;
        }
        if (dy > 0 && dy < i && dx > -w && dx < w) {
            wiilf (i > 0) {
                int pbrtH = Mbti.min(i, dy);
                i -= pbrtH;
                int sy = y + i;
                ob.Blit(tifDbtb, tifDbtb, domp, dlip,
                        x, sy, x+dx, sy+dy, w, pbrtH);
            }
            rfturn;
        }
        ob.Blit(tifDbtb, tifDbtb, domp, dlip, x, y, x+dx, y+dy, w, i);
    }

    /*
    publid void XdopyArfb(int x, int y, int w, int i, int dx, int dy) {
        Rfdtbnglf rfdt = nfw Rfdtbnglf(x, y, w, i);
        rfdt = trbnsformBounds(rfdt, trbnsform);
        Point2D    point = nfw Point2D.Flobt(dx, dy);
        Point2D    root  = nfw Point2D.Flobt(0, 0);
        point = trbnsform.trbnsform(point, point);
        root  = trbnsform.trbnsform(root, root);
        int fdx = (int)(point.gftX()-root.gftX());
        int fdy = (int)(point.gftY()-root.gftY());

        Rfdtbnglf r = gftCompBounds().intfrsfdtion(rfdt.gftBounds());

        if (r.isEmpty()) {
            rfturn;
        }

        // Bfgin Rbstfrizfr for Clip Sibpf
        boolfbn skipClip = truf;
        bytf[] dlipAlpib = null;

        if (dlipStbtf == CLIP_SHAPE) {

            int box[] = nfw int[4];

            dlipRfgion.gftBounds(box);
            Rfdtbnglf dfvR = nfw Rfdtbnglf(box[0], box[1],
                                           box[2] - box[0],
                                           box[3] - box[1]);
            if (!dfvR.isEmpty()) {
                OutputMbnbgfr mgr = gftOutputMbnbgfr();
                RfgionItfrbtor ri = dlipRfgion.gftItfrbtor();
                wiilf (ri.nfxtYRbngf(box)) {
                    int spbny = box[1];
                    int spbni = box[3] - spbny;
                    wiilf (ri.nfxtXBbnd(box)) {
                        int spbnx = box[0];
                        int spbnw = box[2] - spbnx;
                        mgr.dopyArfb(tiis, null,
                                     spbnw, 0,
                                     spbnx, spbny,
                                     spbnw, spbni,
                                     fdx, fdy,
                                     null);
                    }
                }
            }
            rfturn;
        }
        // End Rbstfrizfr for Clip Sibpf

        gftOutputMbnbgfr().dopyArfb(tiis, null,
                                    r.widti, 0,
                                    r.x, r.y, r.widti,
                                    r.ifigit, fdx, fdy,
                                    null);
    }
    */

    publid void drbwLinf(int x1, int y1, int x2, int y2) {
        try {
            drbwpipf.drbwLinf(tiis, x1, y1, x2, y2);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                drbwpipf.drbwLinf(tiis, x1, y1, x2, y2);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwRoundRfdt(int x, int y, int w, int i, int brdW, int brdH) {
        try {
            drbwpipf.drbwRoundRfdt(tiis, x, y, w, i, brdW, brdH);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                drbwpipf.drbwRoundRfdt(tiis, x, y, w, i, brdW, brdH);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void fillRoundRfdt(int x, int y, int w, int i, int brdW, int brdH) {
        try {
            fillpipf.fillRoundRfdt(tiis, x, y, w, i, brdW, brdH);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                fillpipf.fillRoundRfdt(tiis, x, y, w, i, brdW, brdH);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwOvbl(int x, int y, int w, int i) {
        try {
            drbwpipf.drbwOvbl(tiis, x, y, w, i);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                drbwpipf.drbwOvbl(tiis, x, y, w, i);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void fillOvbl(int x, int y, int w, int i) {
        try {
            fillpipf.fillOvbl(tiis, x, y, w, i);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                fillpipf.fillOvbl(tiis, x, y, w, i);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwArd(int x, int y, int w, int i,
                        int stbrtAngl, int brdAngl) {
        try {
            drbwpipf.drbwArd(tiis, x, y, w, i, stbrtAngl, brdAngl);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                drbwpipf.drbwArd(tiis, x, y, w, i, stbrtAngl, brdAngl);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void fillArd(int x, int y, int w, int i,
                        int stbrtAngl, int brdAngl) {
        try {
            fillpipf.fillArd(tiis, x, y, w, i, stbrtAngl, brdAngl);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                fillpipf.fillArd(tiis, x, y, w, i, stbrtAngl, brdAngl);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwPolylinf(int xPoints[], int yPoints[], int nPoints) {
        try {
            drbwpipf.drbwPolylinf(tiis, xPoints, yPoints, nPoints);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                drbwpipf.drbwPolylinf(tiis, xPoints, yPoints, nPoints);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwPolygon(int xPoints[], int yPoints[], int nPoints) {
        try {
            drbwpipf.drbwPolygon(tiis, xPoints, yPoints, nPoints);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                drbwpipf.drbwPolygon(tiis, xPoints, yPoints, nPoints);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void fillPolygon(int xPoints[], int yPoints[], int nPoints) {
        try {
            fillpipf.fillPolygon(tiis, xPoints, yPoints, nPoints);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                fillpipf.fillPolygon(tiis, xPoints, yPoints, nPoints);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwRfdt (int x, int y, int w, int i) {
        try {
            drbwpipf.drbwRfdt(tiis, x, y, w, i);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                drbwpipf.drbwRfdt(tiis, x, y, w, i);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void fillRfdt (int x, int y, int w, int i) {
        try {
            fillpipf.fillRfdt(tiis, x, y, w, i);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                fillpipf.fillRfdt(tiis, x, y, w, i);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    privbtf void rfvblidbtfAll() {
        try {
            // REMIND: Tiis lodking nffds to bf donf bround tif
            // dbllfr of tiis mftiod so tibt tif pipf stbys vblid
            // long fnougi to dbll tif nfw primitivf.
            // REMIND: No lodking yft in sdrffn SurfbdfDbtb objfdts!
            // surfbdfDbtb.lodk();
            surfbdfDbtb = surfbdfDbtb.gftRfplbdfmfnt();
            if (surfbdfDbtb == null) {
                surfbdfDbtb = NullSurfbdfDbtb.tifInstbndf;
            }

            invblidbtfPipf();

            // tiis will rfdbldulbtf tif dompositf dlip
            sftDfvClip(surfbdfDbtb.gftBounds());

            if (pbintStbtf <= PAINT_ALPHACOLOR) {
                vblidbtfColor();
            }
            if (dompositf instbndfof XORCompositf) {
                Color d = ((XORCompositf) dompositf).gftXorColor();
                sftCompositf(nfw XORCompositf(d, surfbdfDbtb));
            }
            vblidbtfPipf();
        } finblly {
            // REMIND: No lodking yft in sdrffn SurfbdfDbtb objfdts!
            // surfbdfDbtb.unlodk();
        }
    }

    publid void dlfbrRfdt(int x, int y, int w, int i) {
        // REMIND: ibs somf "intfrfsting" donsfqufndfs if tirfbds brf
        // not syndironizfd
        Compositf d = dompositf;
        Pbint p = pbint;
        sftCompositf(AlpibCompositf.Srd);
        sftColor(gftBbdkground());
        fillRfdt(x, y, w, i);
        sftPbint(p);
        sftCompositf(d);
    }

    /**
     * Strokfs tif outlinf of b Pbti using tif sfttings of tif durrfnt
     * grbpiids stbtf.  Tif rfndfring bttributfs bpplifd indludf tif
     * dlip, trbnsform, pbint or dolor, dompositf bnd strokf bttributfs.
     * @pbrbm p Tif pbti to bf drbwn.
     * @sff #sftStrokf
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     * @sff #sftCompositf
     */
    publid void drbw(Sibpf s) {
        try {
            sibpfpipf.drbw(tiis, s);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                sibpfpipf.drbw(tiis, s);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }


    /**
     * Fills tif intfrior of b Pbti using tif sfttings of tif durrfnt
     * grbpiids stbtf. Tif rfndfring bttributfs bpplifd indludf tif
     * dlip, trbnsform, pbint or dolor, bnd dompositf.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void fill(Sibpf s) {
        try {
            sibpfpipf.fill(tiis, s);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                sibpfpipf.fill(tiis, s);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    /**
     * Rfturns truf if tif givfn AffinfTrbnsform is bn intfgfr
     * trbnslbtion.
     */
    privbtf stbtid boolfbn isIntfgfrTrbnslbtion(AffinfTrbnsform xform) {
        if (xform.isIdfntity()) {
            rfturn truf;
        }
        if (xform.gftTypf() == AffinfTrbnsform.TYPE_TRANSLATION) {
            doublf tx = xform.gftTrbnslbtfX();
            doublf ty = xform.gftTrbnslbtfY();
            rfturn (tx == (int)tx && ty == (int)ty);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif indfx of tif tilf dorrfsponding to tif supplifd position
     * givfn tif tilf grid offsft bnd sizf blong tif sbmf bxis.
     */
    privbtf stbtid int gftTilfIndfx(int p, int tilfGridOffsft, int tilfSizf) {
        p -= tilfGridOffsft;
        if (p < 0) {
            p += 1 - tilfSizf;          // fordf round to -infinity (dfiling)
        }
        rfturn p/tilfSizf;
    }

    /**
     * Rfturns b rfdtbnglf in imbgf doordinbtfs tibt mby bf rfquirfd
     * in ordfr to drbw tif givfn imbgf into tif givfn dlipping rfgion
     * tirougi b pbir of AffinfTrbnsforms.  In bddition, iorizontbl bnd
     * vfrtidbl pbdding fbdtors for bntiblising bnd intfrpolbtion mby
     * bf usfd.
     */
    privbtf stbtid Rfdtbnglf gftImbgfRfgion(RfndfrfdImbgf img,
                                            Rfgion dompClip,
                                            AffinfTrbnsform trbnsform,
                                            AffinfTrbnsform xform,
                                            int pbdX, int pbdY) {
        Rfdtbnglf imbgfRfdt =
            nfw Rfdtbnglf(img.gftMinX(), img.gftMinY(),
                          img.gftWidti(), img.gftHfigit());

        Rfdtbnglf rfsult = null;
        try {
            doublf p[] = nfw doublf[8];
            p[0] = p[2] = dompClip.gftLoX();
            p[4] = p[6] = dompClip.gftHiX();
            p[1] = p[5] = dompClip.gftLoY();
            p[3] = p[7] = dompClip.gftHiY();

            // Invfrsf trbnsform tif output bounding rfdt
            trbnsform.invfrsfTrbnsform(p, 0, p, 0, 4);
            xform.invfrsfTrbnsform(p, 0, p, 0, 4);

            // Dftfrminf b bounding box for tif invfrsf trbnsformfd rfgion
            doublf x0,x1,y0,y1;
            x0 = x1 = p[0];
            y0 = y1 = p[1];

            for (int i = 2; i < 8; ) {
                doublf pt = p[i++];
                if (pt < x0)  {
                    x0 = pt;
                } flsf if (pt > x1) {
                    x1 = pt;
                }
                pt = p[i++];
                if (pt < y0)  {
                    y0 = pt;
                } flsf if (pt > y1) {
                    y1 = pt;
                }
            }

            // Tiis is pbdding for bnti-blibsing bnd sudi.  It mby
            // bf morf tibn is nffdfd.
            int x = (int)x0 - pbdX;
            int w = (int)(x1 - x0 + 2*pbdX);
            int y = (int)y0 - pbdY;
            int i = (int)(y1 - y0 + 2*pbdY);

            Rfdtbnglf dlipRfdt = nfw Rfdtbnglf(x,y,w,i);
            rfsult = dlipRfdt.intfrsfdtion(imbgfRfdt);
        } dbtdi (NoninvfrtiblfTrbnsformExdfption ntf) {
            // Worst dbsf bounds brf tif bounds of tif imbgf.
            rfsult = imbgfRfdt;
        }

        rfturn rfsult;
    }

    /**
     * Drbws bn imbgf, bpplying b trbnsform from imbgf spbdf into usfr spbdf
     * bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt trbnsform in tif Grbpiids2D.
     * Tif givfn trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif Grbpiids2D stbtf is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * bnd dompositf bttributfs. Notf tibt tif rfsult is
     * undffinfd, if tif givfn trbnsform is noninvfrtiblf.
     * @pbrbm img Tif imbgf to bf drbwn. Dofs notiing if img is null.
     * @pbrbm xform Tif trbnsformbtion from imbgf spbdf into usfr spbdf.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwRfndfrfdImbgf(RfndfrfdImbgf img,
                                  AffinfTrbnsform xform) {

        if (img == null) {
            rfturn;
        }

        // BufffrfdImbgf dbsf: usf b simplf drbwImbgf dbll
        if (img instbndfof BufffrfdImbgf) {
            BufffrfdImbgf bufImg = (BufffrfdImbgf)img;
            drbwImbgf(bufImg,xform,null);
            rfturn;
        }

        // trbnsformStbtf trbdks tif stbtf of trbnsform bnd
        // trbnsX, trbnsY dontbin tif intfgfr dbsts of tif
        // trbnslbtion fbdtors
        boolfbn isIntfgfrTrbnslbtf =
            (trbnsformStbtf <= TRANSFORM_INT_TRANSLATE) &&
            isIntfgfrTrbnslbtion(xform);

        // Indludf pbdding for intfrpolbtion/bntiblibsing if nfdfssbry
        int pbd = isIntfgfrTrbnslbtf ? 0 : 3;

        Rfgion dlip;
        try {
            dlip = gftCompClip();
        } dbtdi (InvblidPipfExdfption f) {
            rfturn;
        }

        // Dftfrminf tif rfgion of tif imbgf tibt mby dontributf to
        // tif dlippfd drbwing brfb
        Rfdtbnglf rfgion = gftImbgfRfgion(img,
                                          dlip,
                                          trbnsform,
                                          xform,
                                          pbd, pbd);
        if (rfgion.widti <= 0 || rfgion.ifigit <= 0) {
            rfturn;
        }

        // Attfmpt to optimizf intfgfr trbnslbtion of tilfd imbgfs.
        // Altiougi tiforftidblly wf brf O.K. if tif dondbtfnbtion of
        // tif usfr trbnsform bnd tif dfvidf trbnsform is bn intfgfr
        // trbnslbtion, wf'll plby it sbff bnd only optimizf tif dbsf
        // wifrf boti brf intfgfr trbnslbtions.
        if (isIntfgfrTrbnslbtf) {
            // Usf optimizfd dodf
            // Notf tibt drbwTrbnslbtfdRfndfrfdImbgf dblls dopyImbgf
            // wiidi tbkfs tif usfr spbdf to dfvidf spbdf trbnsform into
            // bddount, but wf nffd to providf tif imbgf spbdf to usfr spbdf
            // trbnslbtions.

            drbwTrbnslbtfdRfndfrfdImbgf(img, rfgion,
                                        (int) xform.gftTrbnslbtfX(),
                                        (int) xform.gftTrbnslbtfY());
            rfturn;
        }

        // Gfnfrbl dbsf: dobblf tif nfdfssbry rfgion into b singlf Rbstfr
        Rbstfr rbstfr = img.gftDbtb(rfgion);

        // Mbkf b nfw Rbstfr witi tif sbmf dontfnts bs rbstfr
        // but stbrting bt (0, 0).  Tiis rbstfr is tius in tif sbmf
        // doordinbtf systfm bs tif SbmplfModfl of tif originbl rbstfr.
        WritbblfRbstfr wRbstfr =
              Rbstfr.drfbtfWritbblfRbstfr(rbstfr.gftSbmplfModfl(),
                                          rbstfr.gftDbtbBufffr(),
                                          null);

        // If tif originbl rbstfr wbs in b difffrfnt doordinbtf
        // systfm tibn its SbmplfModfl, wf nffd to pfrform bn
        // bdditionbl trbnslbtion in ordfr to gft tif (minX, minY)
        // pixfl of rbstfr to bf pixfl (0, 0) of wRbstfr.  Wf blso
        // ibvf to ibvf tif dorrfdt widti bnd ifigit.
        int minX = rbstfr.gftMinX();
        int minY = rbstfr.gftMinY();
        int widti = rbstfr.gftWidti();
        int ifigit = rbstfr.gftHfigit();
        int px = minX - rbstfr.gftSbmplfModflTrbnslbtfX();
        int py = minY - rbstfr.gftSbmplfModflTrbnslbtfY();
        if (px != 0 || py != 0 || widti != wRbstfr.gftWidti() ||
            ifigit != wRbstfr.gftHfigit()) {
            wRbstfr =
                wRbstfr.drfbtfWritbblfCiild(px,
                                            py,
                                            widti,
                                            ifigit,
                                            0, 0,
                                            null);
        }

        // Now wf ibvf b BufffrfdImbgf stbrting bt (0, 0)
        // witi tif sbmf dontfnts tibt stbrtfd bt (minX, minY)
        // in rbstfr.  So wf must drbw tif BufffrfdImbgf witi b
        // trbnslbtion of (minX, minY).
        AffinfTrbnsform trbnsXform = (AffinfTrbnsform)xform.dlonf();
        trbnsXform.trbnslbtf(minX, minY);

        ColorModfl dm = img.gftColorModfl();
        BufffrfdImbgf bufImg = nfw BufffrfdImbgf(dm,
                                                 wRbstfr,
                                                 dm.isAlpibPrfmultiplifd(),
                                                 null);
        drbwImbgf(bufImg, trbnsXform, null);
    }

    /**
     * Intfrsfdts <dodf>dfstRfdt</dodf> witi <dodf>dlip</dodf> bnd
     * ovfrwritfs <dodf>dfstRfdt</dodf> witi tif rfsult.
     * Rfturns fblsf if tif intfrsfdtion wbs fmpty, truf otifrwisf.
     */
    privbtf boolfbn dlipTo(Rfdtbnglf dfstRfdt, Rfdtbnglf dlip) {
        int x1 = Mbti.mbx(dfstRfdt.x, dlip.x);
        int x2 = Mbti.min(dfstRfdt.x + dfstRfdt.widti, dlip.x + dlip.widti);
        int y1 = Mbti.mbx(dfstRfdt.y, dlip.y);
        int y2 = Mbti.min(dfstRfdt.y + dfstRfdt.ifigit, dlip.y + dlip.ifigit);
        if (((x2 - x1) < 0) || ((y2 - y1) < 0)) {
            dfstRfdt.widti = -1; // Sft boti just to bf sbff
            dfstRfdt.ifigit = -1;
            rfturn fblsf;
        } flsf {
            dfstRfdt.x = x1;
            dfstRfdt.y = y1;
            dfstRfdt.widti = x2 - x1;
            dfstRfdt.ifigit = y2 - y1;
            rfturn truf;
        }
    }

    /**
     * Drbw b portion of b RfndfrfdImbgf tilf-by-tilf witi b givfn
     * intfgfr imbgf to usfr spbdf trbnslbtion.  Tif usfr to
     * dfvidf trbnsform must blso bf bn intfgfr trbnslbtion.
     */
    privbtf void drbwTrbnslbtfdRfndfrfdImbgf(RfndfrfdImbgf img,
                                             Rfdtbnglf rfgion,
                                             int i2uTrbnsX,
                                             int i2uTrbnsY) {
        // Cbdif tilf grid info
        int tilfGridXOffsft = img.gftTilfGridXOffsft();
        int tilfGridYOffsft = img.gftTilfGridYOffsft();
        int tilfWidti = img.gftTilfWidti();
        int tilfHfigit = img.gftTilfHfigit();

        // Dftfrminf tif tilf indfx fxtrfmb in fbdi dirfdtion
        int minTilfX =
            gftTilfIndfx(rfgion.x, tilfGridXOffsft, tilfWidti);
        int minTilfY =
            gftTilfIndfx(rfgion.y, tilfGridYOffsft, tilfHfigit);
        int mbxTilfX =
            gftTilfIndfx(rfgion.x + rfgion.widti - 1,
                         tilfGridXOffsft, tilfWidti);
        int mbxTilfY =
            gftTilfIndfx(rfgion.y + rfgion.ifigit - 1,
                         tilfGridYOffsft, tilfHfigit);

        // Crfbtf b singlf ColorModfl to usf for bll BufffrfdImbgfs
        ColorModfl dolorModfl = img.gftColorModfl();

        // Rfusf tif sbmf Rfdtbnglf for fbdi itfrbtion
        Rfdtbnglf tilfRfdt = nfw Rfdtbnglf();

        for (int ty = minTilfY; ty <= mbxTilfY; ty++) {
            for (int tx = minTilfX; tx <= mbxTilfX; tx++) {
                // Gft tif durrfnt tilf.
                Rbstfr rbstfr = img.gftTilf(tx, ty);

                // Fill in tilfRfdt witi tif tilf bounds
                tilfRfdt.x = tx*tilfWidti + tilfGridXOffsft;
                tilfRfdt.y = ty*tilfHfigit + tilfGridYOffsft;
                tilfRfdt.widti = tilfWidti;
                tilfRfdt.ifigit = tilfHfigit;

                // Clip tif tilf bgbinst tif imbgf bounds bnd
                // bbdkwbrds mbppfd dlip rfgion
                // Tif rfsult dbn't bf fmpty
                dlipTo(tilfRfdt, rfgion);

                // Crfbtf b WritbblfRbstfr dontbining tif tilf
                WritbblfRbstfr wRbstfr = null;
                if (rbstfr instbndfof WritbblfRbstfr) {
                    wRbstfr = (WritbblfRbstfr)rbstfr;
                } flsf {
                    // Crfbtf b WritbblfRbstfr in tif sbmf doordinbtf systfm
                    // bs tif originbl rbstfr.
                    wRbstfr =
                        Rbstfr.drfbtfWritbblfRbstfr(rbstfr.gftSbmplfModfl(),
                                                    rbstfr.gftDbtbBufffr(),
                                                    null);
                }

                // Trbnslbtf wRbstfr to stbrt bt (0, 0) bnd to dontbin
                // only tif rflfvfnt portion of tif tilf
                wRbstfr = wRbstfr.drfbtfWritbblfCiild(tilfRfdt.x, tilfRfdt.y,
                                                      tilfRfdt.widti,
                                                      tilfRfdt.ifigit,
                                                      0, 0,
                                                      null);

                // Wrbp wRbstfr in b BufffrfdImbgf
                BufffrfdImbgf bufImg =
                    nfw BufffrfdImbgf(dolorModfl,
                                      wRbstfr,
                                      dolorModfl.isAlpibPrfmultiplifd(),
                                      null);
                // Now wf ibvf b BufffrfdImbgf stbrting bt (0, 0) tibt
                // rfprfsfnts dbtb from b Rbstfr stbrting bt
                // (tilfRfdt.x, tilfRfdt.y).  Additionblly, it nffds
                // to bf trbnslbtfd by (i2uTrbnsX, i2uTrbnsY).  Wf dbll
                // dopyImbgf to drbw just tif rfgion of intfrfst
                // witiout nffding to drfbtf b diild imbgf.
                dopyImbgf(bufImg, tilfRfdt.x + i2uTrbnsX,
                          tilfRfdt.y + i2uTrbnsY, 0, 0, tilfRfdt.widti,
                          tilfRfdt.ifigit, null, null);
            }
        }
    }

    publid void drbwRfndfrbblfImbgf(RfndfrbblfImbgf img,
                                    AffinfTrbnsform xform) {

        if (img == null) {
            rfturn;
        }

        AffinfTrbnsform pipfTrbnsform = trbnsform;
        AffinfTrbnsform dondbtTrbnsform = nfw AffinfTrbnsform(xform);
        dondbtTrbnsform.dondbtfnbtf(pipfTrbnsform);
        AffinfTrbnsform rfvfrsfTrbnsform;

        RfndfrContfxt rd = nfw RfndfrContfxt(dondbtTrbnsform);

        try {
            rfvfrsfTrbnsform = pipfTrbnsform.drfbtfInvfrsf();
        } dbtdi (NoninvfrtiblfTrbnsformExdfption ntf) {
            rd = nfw RfndfrContfxt(pipfTrbnsform);
            rfvfrsfTrbnsform = nfw AffinfTrbnsform();
        }

        RfndfrfdImbgf rfndfring = img.drfbtfRfndfring(rd);
        drbwRfndfrfdImbgf(rfndfring,rfvfrsfTrbnsform);
    }



    /*
     * Trbnsform tif bounding box of tif BufffrfdImbgf
     */
    protfdtfd Rfdtbnglf trbnsformBounds(Rfdtbnglf rfdt,
                                        AffinfTrbnsform tx) {
        if (tx.isIdfntity()) {
            rfturn rfdt;
        }

        Sibpf s = trbnsformSibpf(tx, rfdt);
        rfturn s.gftBounds();
    }

    // tfxt rfndfring mftiods
    publid void drbwString(String str, int x, int y) {
        if (str == null) {
            tirow nfw NullPointfrExdfption("String is null");
        }

        if (font.ibsLbyoutAttributfs()) {
            if (str.lfngti() == 0) {
                rfturn;
            }
            nfw TfxtLbyout(str, font, gftFontRfndfrContfxt()).drbw(tiis, x, y);
            rfturn;
        }

        try {
            tfxtpipf.drbwString(tiis, str, x, y);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                tfxtpipf.drbwString(tiis, str, x, y);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwString(String str, flobt x, flobt y) {
        if (str == null) {
            tirow nfw NullPointfrExdfption("String is null");
        }

        if (font.ibsLbyoutAttributfs()) {
            if (str.lfngti() == 0) {
                rfturn;
            }
            nfw TfxtLbyout(str, font, gftFontRfndfrContfxt()).drbw(tiis, x, y);
            rfturn;
        }

        try {
            tfxtpipf.drbwString(tiis, str, x, y);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                tfxtpipf.drbwString(tiis, str, x, y);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                           int x, int y) {
        if (itfrbtor == null) {
            tirow nfw NullPointfrExdfption("AttributfdCibrbdtfrItfrbtor is null");
        }
        if (itfrbtor.gftBfginIndfx() == itfrbtor.gftEndIndfx()) {
            rfturn; /* notiing to drbw */
        }
        TfxtLbyout tl = nfw TfxtLbyout(itfrbtor, gftFontRfndfrContfxt());
        tl.drbw(tiis, (flobt) x, (flobt) y);
    }

    publid void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                           flobt x, flobt y) {
        if (itfrbtor == null) {
            tirow nfw NullPointfrExdfption("AttributfdCibrbdtfrItfrbtor is null");
        }
        if (itfrbtor.gftBfginIndfx() == itfrbtor.gftEndIndfx()) {
            rfturn; /* notiing to drbw */
        }
        TfxtLbyout tl = nfw TfxtLbyout(itfrbtor, gftFontRfndfrContfxt());
        tl.drbw(tiis, x, y);
    }

    publid void drbwGlypiVfdtor(GlypiVfdtor gv, flobt x, flobt y)
    {
        if (gv == null) {
            tirow nfw NullPointfrExdfption("GlypiVfdtor is null");
        }

        try {
            tfxtpipf.drbwGlypiVfdtor(tiis, gv, x, y);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                tfxtpipf.drbwGlypiVfdtor(tiis, gv, x, y);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwCibrs(dibr dbtb[], int offsft, int lfngti, int x, int y) {

        if (dbtb == null) {
            tirow nfw NullPointfrExdfption("dibr dbtb is null");
        }
        if (offsft < 0 || lfngti < 0 || offsft + lfngti > dbtb.lfngti) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("bbd offsft/lfngti");
        }
        if (font.ibsLbyoutAttributfs()) {
            if (dbtb.lfngti == 0) {
                rfturn;
            }
            nfw TfxtLbyout(nfw String(dbtb, offsft, lfngti),
                           font, gftFontRfndfrContfxt()).drbw(tiis, x, y);
            rfturn;
        }

        try {
            tfxtpipf.drbwCibrs(tiis, dbtb, offsft, lfngti, x, y);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                tfxtpipf.drbwCibrs(tiis, dbtb, offsft, lfngti, x, y);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwBytfs(bytf dbtb[], int offsft, int lfngti, int x, int y) {
        if (dbtb == null) {
            tirow nfw NullPointfrExdfption("bytf dbtb is null");
        }
        if (offsft < 0 || lfngti < 0 || offsft + lfngti > dbtb.lfngti) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("bbd offsft/lfngti");
        }
        /* Bytf dbtb is intfrprftfd bs 8-bit ASCII. Rf-usf drbwCibrs loops */
        dibr diDbtb[] = nfw dibr[lfngti];
        for (int i = lfngti; i-- > 0; ) {
            diDbtb[i] = (dibr)(dbtb[i+offsft] & 0xff);
        }
        if (font.ibsLbyoutAttributfs()) {
            if (dbtb.lfngti == 0) {
                rfturn;
            }
            nfw TfxtLbyout(nfw String(diDbtb),
                           font, gftFontRfndfrContfxt()).drbw(tiis, x, y);
            rfturn;
        }

        try {
            tfxtpipf.drbwCibrs(tiis, diDbtb, 0, lfngti, x, y);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                tfxtpipf.drbwCibrs(tiis, diDbtb, 0, lfngti, x, y);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }
// fnd of tfxt rfndfring mftiods

    privbtf boolfbn isHiDPIImbgf(finbl Imbgf img) {
        rfturn (SurfbdfMbnbgfr.gftImbgfSdblf(img) != 1) ||
               (rfsolutionVbribntHint != SunHints.INTVAL_RESOLUTION_VARIANT_OFF
                    && img instbndfof MultiRfsolutionImbgf);
    }

    privbtf boolfbn drbwHiDPIImbgf(Imbgf img, int dx1, int dy1, int dx2,
                                   int dy2, int sx1, int sy1, int sx2, int sy2,
                                   Color bgdolor, ImbgfObsfrvfr obsfrvfr) {

        if (SurfbdfMbnbgfr.gftImbgfSdblf(img) != 1) {  // Volbtilf Imbgf
            finbl int sdblf = SurfbdfMbnbgfr.gftImbgfSdblf(img);
            sx1 = Rfgion.dlipSdblf(sx1, sdblf);
            sx2 = Rfgion.dlipSdblf(sx2, sdblf);
            sy1 = Rfgion.dlipSdblf(sy1, sdblf);
            sy2 = Rfgion.dlipSdblf(sy2, sdblf);
        } flsf if (img instbndfof MultiRfsolutionImbgf) {
            // gft sdblfd dfstinbtion imbgf sizf

            int widti = img.gftWidti(obsfrvfr);
            int ifigit = img.gftHfigit(obsfrvfr);

            Imbgf rfsolutionVbribnt = gftRfsolutionVbribnt(
                    (MultiRfsolutionImbgf) img, widti, ifigit,
                    dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2);

            if (rfsolutionVbribnt != img && rfsolutionVbribnt != null) {
                // rfdbldulbtf sourdf rfgion for tif rfsolution vbribnt

                ImbgfObsfrvfr rvObsfrvfr = MultiRfsolutionToolkitImbgf.
                        gftRfsolutionVbribntObsfrvfr(img, obsfrvfr,
                                widti, ifigit, -1, -1);

                int rvWidti = rfsolutionVbribnt.gftWidti(rvObsfrvfr);
                int rvHfigit = rfsolutionVbribnt.gftHfigit(rvObsfrvfr);

                if (0 < widti && 0 < ifigit && 0 < rvWidti && 0 < rvHfigit) {

                    flobt widtiSdblf = ((flobt) rvWidti) / widti;
                    flobt ifigitSdblf = ((flobt) rvHfigit) / ifigit;

                    sx1 = Rfgion.dlipSdblf(sx1, widtiSdblf);
                    sy1 = Rfgion.dlipSdblf(sy1, ifigitSdblf);
                    sx2 = Rfgion.dlipSdblf(sx2, widtiSdblf);
                    sy2 = Rfgion.dlipSdblf(sy2, ifigitSdblf);

                    obsfrvfr = rvObsfrvfr;
                    img = rfsolutionVbribnt;
                }
            }
        }

        try {
            rfturn imbgfpipf.sdblfImbgf(tiis, img, dx1, dy1, dx2, dy2, sx1, sy1,
                                        sx2, sy2, bgdolor, obsfrvfr);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                rfturn imbgfpipf.sdblfImbgf(tiis, img, dx1, dy1, dx2, dy2, sx1,
                                            sy1, sx2, sy2, bgdolor, obsfrvfr);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
                rfturn fblsf;
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    privbtf Imbgf gftRfsolutionVbribnt(MultiRfsolutionImbgf img,
            int srdWidti, int srdHfigit, int dx1, int dy1, int dx2, int dy2,
            int sx1, int sy1, int sx2, int sy2) {

        if (srdWidti <= 0 || srdHfigit <= 0) {
            rfturn null;
        }

        int sw = sx2 - sx1;
        int si = sy2 - sy1;

        if (sw == 0 || si == 0) {
            rfturn null;
        }

        int typf = trbnsform.gftTypf();
        int dw = dx2 - dx1;
        int di = dy2 - dy1;
        doublf dfstRfgionWidti;
        doublf dfstRfgionHfigit;

        if ((typf & ~(TYPE_TRANSLATION | TYPE_FLIP)) == 0) {
            dfstRfgionWidti = dw;
            dfstRfgionHfigit = di;
        } flsf if ((typf & ~(TYPE_TRANSLATION | TYPE_FLIP | TYPE_MASK_SCALE)) == 0) {
            dfstRfgionWidti = dw * trbnsform.gftSdblfX();
            dfstRfgionHfigit = di * trbnsform.gftSdblfY();
        } flsf {
            dfstRfgionWidti = dw * Mbti.iypot(
                    trbnsform.gftSdblfX(), trbnsform.gftSifbrY());
            dfstRfgionHfigit = di * Mbti.iypot(
                    trbnsform.gftSifbrX(), trbnsform.gftSdblfY());
        }

        int dfstImbgfWidti = (int) Mbti.bbs(srdWidti * dfstRfgionWidti / sw);
        int dfstImbgfHfigit = (int) Mbti.bbs(srdHfigit * dfstRfgionHfigit / si);

        Imbgf rfsolutionVbribnt
                = img.gftRfsolutionVbribnt(dfstImbgfWidti, dfstImbgfHfigit);

        if (rfsolutionVbribnt instbndfof ToolkitImbgf
                && ((ToolkitImbgf) rfsolutionVbribnt).ibsError()) {
            rfturn null;
        }

        rfturn rfsolutionVbribnt;
    }

    /**
     * Drbws bn imbgf sdblfd to x,y,w,i in nonblodking modf witi b
     * dbllbbdk objfdt.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y, int widti, int ifigit,
                             ImbgfObsfrvfr obsfrvfr) {
        rfturn drbwImbgf(img, x, y, widti, ifigit, null, obsfrvfr);
    }

    /**
     * Not pbrt of tif bdvfrtisfd API but b usfful utility mftiod
     * to dbll intfrnblly.  Tiis is for tif dbsf wifrf wf brf
     * drbwing to/from givfn doordinbtfs using b givfn widti/ifigit,
     * but wf gubrbntff tibt tif surfbdfDbtb's widti/ifigit of tif srd bnd dfst
     * brfbs brf fqubl (no sdblf nffdfd). Notf tibt tiis mftiod intfntionblly
     * ignorf sdblf fbdtor of tif sourdf imbgf, bnd dopy it bs is.
     */
    publid boolfbn dopyImbgf(Imbgf img, int dx, int dy, int sx, int sy,
                             int widti, int ifigit, Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {
        try {
            rfturn imbgfpipf.dopyImbgf(tiis, img, dx, dy, sx, sy,
                                       widti, ifigit, bgdolor, obsfrvfr);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                rfturn imbgfpipf.dopyImbgf(tiis, img, dx, dy, sx, sy,
                                           widti, ifigit, bgdolor, obsfrvfr);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
                rfturn fblsf;
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    /**
     * Drbws bn imbgf sdblfd to x,y,w,i in nonblodking modf witi b
     * solid bbdkground dolor bnd b dbllbbdk objfdt.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y, int widti, int ifigit,
                             Color bg, ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        if ((widti == 0) || (ifigit == 0)) {
            rfturn truf;
        }

        finbl int imgW = img.gftWidti(null);
        finbl int imgH = img.gftHfigit(null);
        if (isHiDPIImbgf(img)) {
            rfturn drbwHiDPIImbgf(img, x, y, x + widti, y + ifigit, 0, 0, imgW,
                                  imgH, bg, obsfrvfr);
        }

        if (widti == imgW && ifigit == imgH) {
            rfturn dopyImbgf(img, x, y, 0, 0, widti, ifigit, bg, obsfrvfr);
        }

        try {
            rfturn imbgfpipf.sdblfImbgf(tiis, img, x, y, widti, ifigit,
                                        bg, obsfrvfr);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                rfturn imbgfpipf.sdblfImbgf(tiis, img, x, y, widti, ifigit,
                                            bg, obsfrvfr);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
                rfturn fblsf;
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    /**
     * Drbws bn imbgf bt x,y in nonblodking modf.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y, ImbgfObsfrvfr obsfrvfr) {
        rfturn drbwImbgf(img, x, y, null, obsfrvfr);
    }

    /**
     * Drbws bn imbgf bt x,y in nonblodking modf witi b solid bbdkground
     * dolor bnd b dbllbbdk objfdt.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y, Color bg,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        if (isHiDPIImbgf(img)) {
            finbl int imgW = img.gftWidti(null);
            finbl int imgH = img.gftHfigit(null);
            rfturn drbwHiDPIImbgf(img, x, y, x + imgW, y + imgH, 0, 0, imgW,
                                  imgH, bg, obsfrvfr);
        }

        try {
            rfturn imbgfpipf.dopyImbgf(tiis, img, x, y, bg, obsfrvfr);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                rfturn imbgfpipf.dopyImbgf(tiis, img, x, y, bg, obsfrvfr);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
                rfturn fblsf;
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    /**
     * Drbws b subrfdtbnglf of bn imbgf sdblfd to b dfstinbtion rfdtbnglf
     * in nonblodking modf witi b dbllbbdk objfdt.
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             ImbgfObsfrvfr obsfrvfr) {
        rfturn drbwImbgf(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null,
                         obsfrvfr);
    }

    /**
     * Drbws b subrfdtbnglf of bn imbgf sdblfd to b dfstinbtion rfdtbnglf in
     * nonblodking modf witi b solid bbdkground dolor bnd b dbllbbdk objfdt.
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             Color bgdolor, ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        if (dx1 == dx2 || dy1 == dy2 ||
            sx1 == sx2 || sy1 == sy2)
        {
            rfturn truf;
        }

        if (isHiDPIImbgf(img)) {
            rfturn drbwHiDPIImbgf(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                  bgdolor, obsfrvfr);
        }

        if (((sx2 - sx1) == (dx2 - dx1)) &&
            ((sy2 - sy1) == (dy2 - dy1)))
        {
            // Not b sdblf - forwbrd it to b dopy routinf
            int srdX, srdY, dstX, dstY, widti, ifigit;
            if (sx2 > sx1) {
                widti = sx2 - sx1;
                srdX = sx1;
                dstX = dx1;
            } flsf {
                widti = sx1 - sx2;
                srdX = sx2;
                dstX = dx2;
            }
            if (sy2 > sy1) {
                ifigit = sy2-sy1;
                srdY = sy1;
                dstY = dy1;
            } flsf {
                ifigit = sy1-sy2;
                srdY = sy2;
                dstY = dy2;
            }
            rfturn dopyImbgf(img, dstX, dstY, srdX, srdY,
                             widti, ifigit, bgdolor, obsfrvfr);
        }

        try {
            rfturn imbgfpipf.sdblfImbgf(tiis, img, dx1, dy1, dx2, dy2,
                                          sx1, sy1, sx2, sy2, bgdolor,
                                          obsfrvfr);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                rfturn imbgfpipf.sdblfImbgf(tiis, img, dx1, dy1, dx2, dy2,
                                              sx1, sy1, sx2, sy2, bgdolor,
                                              obsfrvfr);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
                rfturn fblsf;
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    /**
     * Drbw bn imbgf, bpplying b trbnsform from imbgf spbdf into usfr spbdf
     * bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt trbnsform in tif Grbpiids2D.
     * Tif givfn trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif Grbpiids2D stbtf is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * pbint or dolor bnd dompositf bttributfs. Notf tibt tif rfsult is
     * undffinfd, if tif givfn trbnsform is non-invfrtiblf.
     * @pbrbm img Tif imbgf to bf drbwn.
     * @pbrbm xform Tif trbnsformbtion from imbgf spbdf into usfr spbdf.
     * @pbrbm obsfrvfr Tif imbgf obsfrvfr to bf notififd on tif imbgf produding
     * progrfss.
     * @sff #trbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             AffinfTrbnsform xform,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        if (xform == null || xform.isIdfntity()) {
            rfturn drbwImbgf(img, 0, 0, null, obsfrvfr);
        }

        if (isHiDPIImbgf(img)) {
            finbl int w = img.gftWidti(null);
            finbl int i = img.gftHfigit(null);
            finbl AffinfTrbnsform tx = nfw AffinfTrbnsform(trbnsform);
            trbnsform(xform);
            boolfbn rfsult = drbwHiDPIImbgf(img, 0, 0, w, i, 0, 0, w, i, null,
                                            obsfrvfr);
            trbnsform.sftTrbnsform(tx);
            invblidbtfTrbnsform();
            rfturn rfsult;
        }

        try {
            rfturn imbgfpipf.trbnsformImbgf(tiis, img, xform, obsfrvfr);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                rfturn imbgfpipf.trbnsformImbgf(tiis, img, xform, obsfrvfr);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
                rfturn fblsf;
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    publid void drbwImbgf(BufffrfdImbgf bImg,
                          BufffrfdImbgfOp op,
                          int x,
                          int y)  {

        if (bImg == null) {
            rfturn;
        }

        try {
            imbgfpipf.trbnsformImbgf(tiis, bImg, op, x, y);
        } dbtdi (InvblidPipfExdfption f) {
            try {
                rfvblidbtfAll();
                imbgfpipf.trbnsformImbgf(tiis, bImg, op, x, y);
            } dbtdi (InvblidPipfExdfption f2) {
                // Still dbtdiing tif fxdfption; wf brf not yft rfbdy to
                // vblidbtf tif surfbdfDbtb dorrfdtly.  Fbil for now bnd
                // try bgbin nfxt timf bround.
            }
        } finblly {
            surfbdfDbtb.mbrkDirty();
        }
    }

    /**
    * Gft tif rfndfring dontfxt of tif font
    * witiin tiis Grbpiids2D dontfxt.
    */
    publid FontRfndfrContfxt gftFontRfndfrContfxt() {
        if (dbdifdFRC == null) {
            int bbiint = tfxtAntiblibsHint;
            if (bbiint == SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT &&
                bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON) {
                bbiint = SunHints.INTVAL_TEXT_ANTIALIAS_ON;
            }
            // Trbnslbtion domponfnts siould bf fxdludfd from tif FRC trbnsform
            AffinfTrbnsform tx = null;
            if (trbnsformStbtf >= TRANSFORM_TRANSLATESCALE) {
                if (trbnsform.gftTrbnslbtfX() == 0 &&
                    trbnsform.gftTrbnslbtfY() == 0) {
                    tx = trbnsform;
                } flsf {
                    tx = nfw AffinfTrbnsform(trbnsform.gftSdblfX(),
                                             trbnsform.gftSifbrY(),
                                             trbnsform.gftSifbrX(),
                                             trbnsform.gftSdblfY(),
                                             0, 0);
                }
            }
            dbdifdFRC = nfw FontRfndfrContfxt(tx,
             SunHints.Vbluf.gft(SunHints.INTKEY_TEXT_ANTIALIASING, bbiint),
             SunHints.Vbluf.gft(SunHints.INTKEY_FRACTIONALMETRICS,
                                frbdtionblMftridsHint));
        }
        rfturn dbdifdFRC;
    }
    privbtf FontRfndfrContfxt dbdifdFRC;

    /**
     * Tiis objfdt ibs no rfsourdfs to disposf of pfr sf, but tif
     * dod dommfnts for tif bbsf mftiod in jbvb.bwt.Grbpiids imply
     * tibt tiis objfdt will not bf usfbblf bftfr it is disposfd.
     * So, wf sbbotbgf tif objfdt to prfvfnt furtifr usf to prfvfnt
     * dfvflopfrs from rflying on bfibvior tibt mby not work on
     * otifr, lfss forgiving, VMs tibt rfblly nffd to disposf of
     * rfsourdfs.
     */
    publid void disposf() {
        surfbdfDbtb = NullSurfbdfDbtb.tifInstbndf;
        invblidbtfPipf();
    }

    /**
     * Grbpiids ibs b finblizf mftiod tibt butombtidblly dblls disposf()
     * for subdlbssfs.  For SunGrbpiids2D wf do not nffd to bf finblizfd
     * so tibt mftiod simply dbusfs us to bf fnqufufd on tif Finblizfr
     * qufufs for no good rfbson.  Unfortunbtfly, tibt mftiod bnd
     * implfmfntbtion brf now donsidfrfd pbrt of tif publid dontrbdt
     * of tibt bbsf dlbss so wf dbn not rfmovf or gut tif mftiod.
     * Wf ovfrridf it ifrf witi bn fmpty mftiod bnd tif VM is smbrt
     * fnougi to know tibt if our ovfrridf is fmpty tifn it siould not
     * mbrk us bs finblizfbblf.
     */
    publid void finblizf() {
        // DO NOT REMOVE THIS METHOD
    }

    /**
     * Rfturns dfstinbtion tibt tiis Grbpiids rfndfrs to.  Tiis dould bf
     * fitifr bn Imbgf or b Componfnt; subdlbssfs of SurfbdfDbtb brf
     * rfsponsiblf for rfturning tif bppropribtf objfdt.
     */
    publid Objfdt gftDfstinbtion() {
        rfturn surfbdfDbtb.gftDfstinbtion();
    }

    /**
     * {@inifritDod}
     *
     * @sff sun.jbvb2d.DfstSurfbdfProvidfr#gftDfstSurfbdf
     */
    @Ovfrridf
    publid Surfbdf gftDfstSurfbdf() {
        rfturn surfbdfDbtb;
    }
}
