/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.Strokf;
import jbvb.bwt.Trbnspbrfndy;

import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.TfxtLbyout;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.Linf2D;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.ComponfntSbmplfModfl;
import jbvb.bwt.imbgf.MultiPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.SbmplfModfl;

import sun.bwt.imbgf.BytfComponfntRbstfr;
import sun.bwt.imbgf.BytfPbdkfdRbstfr;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.bwt.print.PrintfrJob;

import jbvb.util.Arrbys;

import sun.font.CibrToGlypiMbppfr;
import sun.font.CompositfFont;
import sun.font.Font2D;
import sun.font.FontUtilitifs;
import sun.font.PiysidblFont;
import sun.font.TrufTypfFont;

import sun.print.PbtiGrbpiids;
import sun.print.ProxyGrbpiids2D;

finbl dlbss WPbtiGrbpiids fxtfnds PbtiGrbpiids {

    /**
     * For b drbwing bpplidbtion tif initibl usfr spbdf
     * rfsolution is 72dpi.
     */
    privbtf stbtid finbl int DEFAULT_USER_RES = 72;

    privbtf stbtid finbl flobt MIN_DEVICE_LINEWIDTH = 1.2f;
    privbtf stbtid finbl flobt MAX_THINLINE_INCHES = 0.014f;

    /* Notf tibt prfffrGDITfxtLbyout implifs usfGDITfxtLbyout.
     * "prfffr" is usfd to ovfrridf dbsfs wifrf would otifrwisf
     * dioosf not to usf it. Notf tibt non-lbyout fbdtors mby
     * still mfbn tibt GDI dbnnot bf usfd.
     */
    privbtf stbtid boolfbn usfGDITfxtLbyout = truf;
    privbtf stbtid boolfbn prfffrGDITfxtLbyout = fblsf;
    stbtid {
        String tfxtLbyoutStr =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                   nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                         "sun.jbvb2d.print.fnbblfGDITfxtLbyout"));

        if (tfxtLbyoutStr != null) {
            usfGDITfxtLbyout = Boolfbn.gftBoolfbn(tfxtLbyoutStr);
            if (!usfGDITfxtLbyout) {
                if (tfxtLbyoutStr.fqublsIgnorfCbsf("prfffr")) {
                    usfGDITfxtLbyout = truf;
                    prfffrGDITfxtLbyout = truf;
                }
            }
        }
    }

    WPbtiGrbpiids(Grbpiids2D grbpiids, PrintfrJob printfrJob,
                  Printbblf pbintfr, PbgfFormbt pbgfFormbt, int pbgfIndfx,
                  boolfbn dbnRfdrbw) {
        supfr(grbpiids, printfrJob, pbintfr, pbgfFormbt, pbgfIndfx, dbnRfdrbw);
    }

    /**
     * Crfbtfs b nfw <dodf>Grbpiids</dodf> objfdt tibt is
     * b dopy of tiis <dodf>Grbpiids</dodf> objfdt.
     * @rfturn     b nfw grbpiids dontfxt tibt is b dopy of
     *                       tiis grbpiids dontfxt.
     * @sindf      1.0
     */
    @Ovfrridf
    publid Grbpiids drfbtf() {

        rfturn nfw WPbtiGrbpiids((Grbpiids2D) gftDflfgbtf().drfbtf(),
                                 gftPrintfrJob(),
                                 gftPrintbblf(),
                                 gftPbgfFormbt(),
                                 gftPbgfIndfx(),
                                 dbnDoRfdrbws());
    }

    /**
     * Strokfs tif outlinf of b Sibpf using tif sfttings of tif durrfnt
     * grbpiids stbtf.  Tif rfndfring bttributfs bpplifd indludf tif
     * dlip, trbnsform, pbint or dolor, dompositf bnd strokf bttributfs.
     * @pbrbm s Tif sibpf to bf drbwn.
     * @sff #sftStrokf
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     * @sff #sftCompositf
     */
    @Ovfrridf
    publid void drbw(Sibpf s) {

        Strokf strokf = gftStrokf();

        /* If tif linf bfing drbwn is tiinnfr tibn dbn bf
         * rfndfrfd, tifn dibngf tif linf widti, strokf
         * tif sibpf, bnd tifn sft tif linf widti bbdk.
         * Wf dbn only do tiis for BbsidStrokf's.
         */
        if (strokf instbndfof BbsidStrokf) {
            BbsidStrokf linfStrokf;
            BbsidStrokf minLinfStrokf = null;
            flobt dfvidfLinfWidti;
            flobt linfWidti;
            AffinfTrbnsform dfvidfTrbnsform;
            Point2D.Flobt pfnSizf;

            /* Gft tif rfqufstfd linf widti in usfr spbdf.
             */
            linfStrokf = (BbsidStrokf) strokf;
            linfWidti = linfStrokf.gftLinfWidti();
            pfnSizf = nfw Point2D.Flobt(linfWidti, linfWidti);

            /* Computf tif linf widti in dfvidf doordinbtfs.
             * Work on b point in dbsf tifrf is bsymftrid sdbling
             * bftwffn usfr bnd dfvidf spbdf.
             * Tbkf tif bbsolutf vbluf in dbsf tifrf is nfgbtivf
             * sdbling in ffffdt.
             */
            dfvidfTrbnsform = gftTrbnsform();
            dfvidfTrbnsform.dfltbTrbnsform(pfnSizf, pfnSizf);
            dfvidfLinfWidti = Mbti.min(Mbti.bbs(pfnSizf.x),
                                       Mbti.bbs(pfnSizf.y));

            /* If tif rfqufstfd linf is too tiin tifn mbp our
             * minimum linf widti bbdk to usfr spbdf bnd sft
             * b nfw BbsidStrokf.
             */
            if (dfvidfLinfWidti < MIN_DEVICE_LINEWIDTH) {

                Point2D.Flobt minPfnSizf = nfw Point2D.Flobt(
                                                MIN_DEVICE_LINEWIDTH,
                                                MIN_DEVICE_LINEWIDTH);

                try {
                    AffinfTrbnsform invfrsf;
                    flobt minLinfWidti;

                    /* Convfrt tif minimum linf widti from dfvidf
                     * spbdf to usfr spbdf.
                     */
                    invfrsf = dfvidfTrbnsform.drfbtfInvfrsf();
                    invfrsf.dfltbTrbnsform(minPfnSizf, minPfnSizf);

                    minLinfWidti = Mbti.mbx(Mbti.bbs(minPfnSizf.x),
                                            Mbti.bbs(minPfnSizf.y));

                    /* Usf bll of tif pbrbmftfrs from tif durrfnt
                     * strokf but dibngf tif linf widti to our
                     * dbldulbtfd minimum.
                     */
                    minLinfStrokf = nfw BbsidStrokf(minLinfWidti,
                                                    linfStrokf.gftEndCbp(),
                                                    linfStrokf.gftLinfJoin(),
                                                    linfStrokf.gftMitfrLimit(),
                                                    linfStrokf.gftDbsiArrby(),
                                                    linfStrokf.gftDbsiPibsf());
                    sftStrokf(minLinfStrokf);

                } dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
                    /* If wf dbn't invfrt tif mbtrix tifrf is somftiing
                     * vfry wrong so don't worry bbout tif minor mbttfr
                     * of b minimum linf widti.
                     */
                }
            }

            supfr.drbw(s);

            /* If wf dibngfd tif strokf, put bbdk tif old
             * strokf in ordfr to mbintbin b minimum linf
             * widti.
             */
            if (minLinfStrokf != null) {
                sftStrokf(linfStrokf);
            }

        /* Tif strokf in ffffdt wbs not b BbsidStrokf so wf
         * will not try to fnfordf b minimum linf widti.
         */
        } flsf {
            supfr.drbw(s);
        }
    }

    /**
     * Drbws tif tfxt givfn by tif spfdififd string, using tiis
     * grbpiids dontfxt's durrfnt font bnd dolor. Tif bbsflinf of tif
     * first dibrbdtfr is bt position (<i>x</i>,&nbsp;<i>y</i>) in tiis
     * grbpiids dontfxt's doordinbtf systfm.
     * @pbrbm       str      tif string to bf drbwn.
     * @pbrbm       x        tif <i>x</i> doordinbtf.
     * @pbrbm       y        tif <i>y</i> doordinbtf.
     * @sff         jbvb.bwt.Grbpiids#drbwBytfs
     * @sff         jbvb.bwt.Grbpiids#drbwCibrs
     * @sindf       1.0
     */
    @Ovfrridf
    publid void drbwString(String str, int x, int y) {
        drbwString(str, (flobt) x, (flobt) y);
    }

    @Ovfrridf
     publid void drbwString(String str, flobt x, flobt y) {
         drbwString(str, x, y, gftFont(), gftFontRfndfrContfxt(), 0f);
     }

    /* A rfturn vbluf of 0 would mfbn font not bvbilbblf to GDI, or tif
     * it dbn't bf usfd for tiis string.
     * A rfturn of 1 mfbns it is suitbblf, indluding for dompositfs.
     * Wf difdk tibt tif trbnsform in ffffdt is dobblf witi GDI, bnd tibt
     * tiis is b dompositf font AWT dbn ibndlf, or b piysidbl font GDI
     * dbn ibndlf dirfdtly. Its possiblf tibt somf strings mby ultimbtfly
     * fbil tif morf stringfnt tfsts in drbwString but tiis is rbrf bnd
     * blso tibt mftiod will blwbys suddffd, bs if tif font isn't bvbilbblf
     * it will usf outlinfs vib b supfrdlbss dbll. Also it is only dbllfd for
     * tif dffbult rfndfr dontfxt (bs dbnDrbwStringToWidti() will rfturn
     * fblsf. Tibt is wiy it ignorfs tif frd bnd widti brgumfnts.
     */
    @Ovfrridf
    protfdtfd int plbtformFontCount(Font font, String str) {

        AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();
        AffinfTrbnsform fontTrbnsform = nfw AffinfTrbnsform(dfvidfTrbnsform);
        fontTrbnsform.dondbtfnbtf(gftFont().gftTrbnsform());
        int trbnsformTypf = fontTrbnsform.gftTypf();

        /* Tfst if GDI dbn ibndlf tif trbnsform */
        boolfbn dirfdtToGDI = ((trbnsformTypf !=
                               AffinfTrbnsform.TYPE_GENERAL_TRANSFORM)
                               && ((trbnsformTypf & AffinfTrbnsform.TYPE_FLIP)
                                   == 0));

        if (!dirfdtToGDI) {
            rfturn 0;
        }

        /* Sindf bll windows fonts brf bvbilbblf, bnd tif JRE fonts
         * brf blso rfgistfrfd. Only tif Font.drfbtfFont() dbsf is prfsfntly
         * unknown to GDI. Tiosf dbn bf rfgistfrfd too, bltiougi tibt
         * dodf dofs not fxist yft, it dbn bf bddfd too, so wf siould not
         * fbil tibt dbsf. Just do b quidk difdk wiftifr its b TrufTypfFont
         * - if not b Typf1 font ftd, bnd lft drbwString() rfsolvf tif rfst.
         */
        Font2D font2D = FontUtilitifs.gftFont2D(font);
        if (font2D instbndfof CompositfFont ||
            font2D instbndfof TrufTypfFont) {
            rfturn 1;
        } flsf {
            rfturn 0;
        }
    }

    privbtf stbtid boolfbn isXP() {
        String osVfrsion = Systfm.gftPropfrty("os.vfrsion");
        if (osVfrsion != null) {
            Flobt vfrsion = Flobt.vblufOf(osVfrsion);
            rfturn (vfrsion.flobtVbluf() >= 5.1f);
        } flsf {
            rfturn fblsf;
        }
    }

    /* In dbsf GDI dofsn't ibndlf sibping or BIDI donsistfntly witi
     * 2D's TfxtLbyout, wf dbn dftfdt tifsf dbsfs bnd rfdflfgbtf up to
     * bf drbwn vib TfxtLbyout, wiidi in is rfndfrfd bs runs of
     * GlypiVfdtors, to wiidi wf dbn bssign positions for fbdi glypi.
     */
    privbtf boolfbn strNffdsTfxtLbyout(String str, Font font) {
        dibr[] dibrs = str.toCibrArrby();
        boolfbn isComplfx = FontUtilitifs.isComplfxTfxt(dibrs, 0, dibrs.lfngti);
        if (!isComplfx) {
            rfturn fblsf;
        } flsf if (!usfGDITfxtLbyout) {
            rfturn truf;
        } flsf {
            if (prfffrGDITfxtLbyout ||
                (isXP() && FontUtilitifs.tfxtLbyoutIsCompbtiblf(font))) {
                rfturn fblsf;
            } flsf {
                rfturn truf;
            }
        }
    }

    privbtf int gftAnglf(Point2D.Doublf pt) {
        /* Gft tif rotbtion in 1/10'tis dfgrff (bs nffdfd by Windows)
         * so tibt GDI dbn drbw tif tfxt rotbtfd.
         * Tiis dbldulbtion is only vblid for b uniform sdblf, no sifbring.
         */
        doublf bnglf = Mbti.toDfgrffs(Mbti.btbn2(pt.y, pt.x));
        if (bnglf < 0.0) {
            bnglf+= 360.0;
        }
        /* Windows spfdififs tif rotbtion bnti-dlodkwisf from tif x-bxis
         * of tif dfvidf, 2D spfdififs +vf rotbtion towbrds tif y-bxis
         * Sindf tif 2D y-bxis runs from top-to-bottom, windows bnglf of
         * rotbtion ifrf is oppositf tibn 2D's, so tif rotbtion nffdfd
         * nffds to bf rfdbldulbtfd in tif oppositf dirfdtion.
         */
        if (bnglf != 0.0) {
            bnglf = 360.0 - bnglf;
        }
        rfturn (int)Mbti.round(bnglf * 10.0);
    }

    privbtf flobt gftAwSdblf(doublf sdblfFbdtorX, doublf sdblfFbdtorY) {

        flobt bwSdblf = (flobt)(sdblfFbdtorX/sdblfFbdtorY);
        /* don't lft rounding frrors bf intfrprftfd bs non-uniform sdblf */
        if (bwSdblf > 0.999f && bwSdblf < 1.001f) {
            bwSdblf = 1.0f;
        }
        rfturn bwSdblf;
    }

    /**
     * Rfndfrs tif tfxt spfdififd by tif spfdififd <dodf>String</dodf>,
     * using tif durrfnt <dodf>Font</dodf> bnd <dodf>Pbint</dodf> bttributfs
     * in tif <dodf>Grbpiids2D</dodf> dontfxt.
     * Tif bbsflinf of tif first dibrbdtfr is bt position
     * (<i>x</i>,&nbsp;<i>y</i>) in tif Usfr Spbdf.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, <dodf>Pbint</dodf>, <dodf>Font</dodf> bnd
     * <dodf>Compositf</dodf> bttributfs. For dibrbdtfrs in sdript systfms
     * sudi bs Hfbrfw bnd Arbbid, tif glypis dbn bf rfndfrfd from rigit to
     * lfft, in wiidi dbsf tif doordinbtf supplifd is tif lodbtion of tif
     * lfftmost dibrbdtfr on tif bbsflinf.
     * @pbrbm s tif <dodf>String</dodf> to bf rfndfrfd
     * @pbrbm x,&nbsp;y tif doordinbtfs wifrf tif <dodf>String</dodf>
     * siould bf rfndfrfd
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff jbvb.bwt.Grbpiids#sftFont
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    @Ovfrridf
    publid void drbwString(String str, flobt x, flobt y,
                           Font font, FontRfndfrContfxt frd, flobt tbrgftW) {
        if (str.lfngti() == 0) {
            rfturn;
        }

        if (WPrintfrJob.sibpfTfxtProp) {
            supfr.drbwString(str, x, y, font, frd, tbrgftW);
            rfturn;
        }

        /* If tif Font ibs lbyout bttributfs wf nffd to dflfgbtf to TfxtLbyout.
         * TfxtLbyout rfndfrs tfxt bs GlypiVfdtors. Wf try to print tiosf
         * using printfr fonts - if using Postsdript tfxt opfrbtors so
         * wf mby bf rfinvokfd. In tibt dbsf tif "!printingGlypiVfdtor" tfst
         * prfvfnts us rfdursing bnd instfbd sfnds us into tif body of tif
         * mftiod wifrf wf dbn sbffly ignorf lbyout bttributfs bs tiosf
         * brf blrfbdy ibndlfd by TfxtLbyout.
         * Similbrly if lbyout is nffdfd bbsfd on tif tfxt, tifn wf
         * dflfgbtf to TfxtLbyout if possiblf, or fbiling tibt wf dflfgbtf
         * upwbrds to fillfd sibpfs.
         */
        boolfbn lbyoutNffdfd = strNffdsTfxtLbyout(str, font);
        if ((font.ibsLbyoutAttributfs() || lbyoutNffdfd)
            && !printingGlypiVfdtor) {
            TfxtLbyout lbyout = nfw TfxtLbyout(str, font, frd);
            lbyout.drbw(tiis, x, y);
            rfturn;
        } flsf if (lbyoutNffdfd) {
            supfr.drbwString(str, x, y, font, frd, tbrgftW);
            rfturn;
        }

        AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();
        AffinfTrbnsform fontTrbnsform = nfw AffinfTrbnsform(dfvidfTrbnsform);
        fontTrbnsform.dondbtfnbtf(font.gftTrbnsform());
        int trbnsformTypf = fontTrbnsform.gftTypf();

        /* Usf GDI for tif tfxt if tif grbpiids trbnsform is somftiing
         * for wiidi wf dbn obtbin b suitbblf GDI font.
         * A flip or sifbring trbnsform on tif grbpiids or b trbnsform
         * on tif font fordf us to dfdomposf tif tfxt into b sibpf.
         */
        boolfbn dirfdtToGDI = ((trbnsformTypf !=
                               AffinfTrbnsform.TYPE_GENERAL_TRANSFORM)
                               && ((trbnsformTypf & AffinfTrbnsform.TYPE_FLIP)
                                   == 0));

        WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();
        try {
            wPrintfrJob.sftTfxtColor((Color)gftPbint());
        } dbtdi (ClbssCbstExdfption f) { // pffk siould dftfdt sudi pbints.
            dirfdtToGDI = fblsf;
        }

        if (!dirfdtToGDI) {
            supfr.drbwString(str, x, y, font, frd, tbrgftW);
            rfturn;
        }

        /* Now wf ibvf difdkfd fvfrytiing is OK to go tirougi GDI bs tfxt
         * witi tif fxdfption of tfsting GDI dbn find bnd usf tif font. Tibt
         * is ibndlfd in tif tfxtOut() dbll.
         */

        /* Computf tif stbrting position of tif string in
         * dfvidf spbdf.
         */
        Point2D.Flobt usfrpos = nfw Point2D.Flobt(x, y);
        Point2D.Flobt dfvpos = nfw Point2D.Flobt();

        /* Alrfbdy ibvf tif trbnslbtf from tif dfvidfTrbnsform,
         * but tif font mby ibvf b trbnslbtion domponfnt too.
         */
        if (font.isTrbnsformfd()) {
            AffinfTrbnsform fontTx = font.gftTrbnsform();
            flobt trbnslbtfX = (flobt)(fontTx.gftTrbnslbtfX());
            flobt trbnslbtfY = (flobt)(fontTx.gftTrbnslbtfY());
            if (Mbti.bbs(trbnslbtfX) < 0.00001) trbnslbtfX = 0f;
            if (Mbti.bbs(trbnslbtfY) < 0.00001) trbnslbtfY = 0f;
            usfrpos.x += trbnslbtfX; usfrpos.y += trbnslbtfY;
        }
        dfvidfTrbnsform.trbnsform(usfrpos, dfvpos);

        if (gftClip() != null) {
            dfvidfClip(gftClip().gftPbtiItfrbtor(dfvidfTrbnsform));
        }

        /* Gft tif font sizf in dfvidf doordinbtfs.
         * Tif sizf nffdfd is tif font ifigit sdblfd to dfvidf spbdf.
         * Altiougi wf ibvf blrfbdy tfstfd tibt tifrf is no sifbr,
         * tifrf mby bf b non-uniform sdblf, so tif widti of tif font
         * dofs not sdblf fqublly witi tif ifigit. Tibt is ibndlfd
         * by spfdifying bn 'bvfrbgf widti' sdblf to GDI.
         */
        flobt fontSizf = font.gftSizf2D();

        Point2D.Doublf pty = nfw Point2D.Doublf(0.0, 1.0);
        fontTrbnsform.dfltbTrbnsform(pty, pty);
        doublf sdblfFbdtorY = Mbti.sqrt(pty.x*pty.x+pty.y*pty.y);
        flobt sdblfdFontSizfY = (flobt)(fontSizf * sdblfFbdtorY);

        Point2D.Doublf ptx = nfw Point2D.Doublf(1.0, 0.0);
        fontTrbnsform.dfltbTrbnsform(ptx, ptx);
        doublf sdblfFbdtorX = Mbti.sqrt(ptx.x*ptx.x+ptx.y*ptx.y);
        flobt sdblfdFontSizfX = (flobt)(fontSizf * sdblfFbdtorX);

        flobt bwSdblf = gftAwSdblf(sdblfFbdtorX, sdblfFbdtorY);
        int ibnglf = gftAnglf(ptx);

        Font2D font2D = FontUtilitifs.gftFont2D(font);
        if (font2D instbndfof TrufTypfFont) {
            tfxtOut(str, font, (TrufTypfFont)font2D, frd,
                    sdblfdFontSizfY, ibnglf, bwSdblf,
                    dfvidfTrbnsform, sdblfFbdtorX,
                    x, y, dfvpos.x, dfvpos.y, tbrgftW);
        } flsf if (font2D instbndfof CompositfFont) {
            /* Compositf fonts brf mbdf up of multiplf fonts bnd fbdi
             * substring tibt usfs b pbrtidulbr domponfnt font nffds to
             * bf sfpbrbtfly sfnt to GDI.
             * Tiis works for stbndbrd dompositf fonts, bltfrnbtf onfs,
             * Fonts tibt brf b piysidbl font bbdkfd by b stbndbrd dompositf,
             * bnd witi fbllbbdk fonts.
             */
            CompositfFont dompFont = (CompositfFont)font2D;
            flobt usfrx = x, usfry = y;
            flobt dfvx = dfvpos.x, dfvy = dfvpos.y;
            dibr[] dibrs = str.toCibrArrby();
            int lfn = dibrs.lfngti;
            int[] glypis = nfw int[lfn];
            dompFont.gftMbppfr().dibrsToGlypis(lfn, dibrs, glypis);

            int stbrtCibr = 0, fndCibr = 0, slot = 0;
            wiilf (fndCibr < lfn) {

                stbrtCibr = fndCibr;
                slot = glypis[stbrtCibr] >>> 24;

                wiilf (fndCibr < lfn && ((glypis[fndCibr] >>> 24) == slot)) {
                    fndCibr++;
                }
                String substr = nfw String(dibrs, stbrtCibr,fndCibr-stbrtCibr);
                PiysidblFont slotFont = dompFont.gftSlotFont(slot);
                tfxtOut(substr, font, slotFont, frd,
                        sdblfdFontSizfY, ibnglf, bwSdblf,
                        dfvidfTrbnsform, sdblfFbdtorX,
                        usfrx, usfry, dfvx, dfvy, 0f);
                Rfdtbnglf2D bds = font.gftStringBounds(substr, frd);
                flobt xAdvbndf = (flobt)bds.gftWidti();
                usfrx += xAdvbndf;
                usfrpos.x += xAdvbndf;
                dfvidfTrbnsform.trbnsform(usfrpos, dfvpos);
                dfvx = dfvpos.x;
                dfvy = dfvpos.y;
            }
        } flsf {
            supfr.drbwString(str, x, y, font, frd, tbrgftW);
        }
    }

    /** rfturn truf if tif Grbpiids instbndf dbn dirfdtly print
     * tiis glypivfdtor
     */
    @Ovfrridf
    protfdtfd boolfbn printGlypiVfdtor(GlypiVfdtor gv, flobt x, flobt y) {
        /* Wf don't wbnt to try to ibndlf pfr-glypi trbnsforms. GDI dbn't
         * ibndlf pfr-glypi rotbtions, ftd. Tifrf's no wby to fxprfss it
         * in b singlf dbll, so just bbil for tiis undommon dbsf.
         */
        if ((gv.gftLbyoutFlbgs() & GlypiVfdtor.FLAG_HAS_TRANSFORMS) != 0) {
            rfturn fblsf;
        }

        if (gv.gftNumGlypis() == 0) {
            rfturn truf; // notiing to do.
        }

        AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();
        AffinfTrbnsform fontTrbnsform = nfw AffinfTrbnsform(dfvidfTrbnsform);
        Font font = gv.gftFont();
        fontTrbnsform.dondbtfnbtf(font.gftTrbnsform());
        int trbnsformTypf = fontTrbnsform.gftTypf();

        /* Usf GDI for tif tfxt if tif grbpiids trbnsform is somftiing
         * for wiidi wf dbn obtbin b suitbblf GDI font.
         * A flip or sifbring trbnsform on tif grbpiids or b trbnsform
         * on tif font fordf us to dfdomposf tif tfxt into b sibpf.
         */
        boolfbn dirfdtToGDI =
            ((trbnsformTypf != AffinfTrbnsform.TYPE_GENERAL_TRANSFORM) &&
             ((trbnsformTypf & AffinfTrbnsform.TYPE_FLIP) == 0));

        WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();
        try {
            wPrintfrJob.sftTfxtColor((Color)gftPbint());
        } dbtdi (ClbssCbstExdfption f) { // pffk siould dftfdt sudi pbints.
            dirfdtToGDI = fblsf;
        }

        if (WPrintfrJob.sibpfTfxtProp || !dirfdtToGDI) {
            rfturn fblsf;
        }
        /* Computf tif stbrting position of tif string in
         * dfvidf spbdf.
         */
        Point2D.Flobt usfrpos = nfw Point2D.Flobt(x, y);
        /* Add tif position of tif first glypi - its not blwbys 0,0 */
        Point2D g0pos = gv.gftGlypiPosition(0);
        usfrpos.x += (flobt)g0pos.gftX();
        usfrpos.y += (flobt)g0pos.gftY();
        Point2D.Flobt dfvpos = nfw Point2D.Flobt();

        /* Alrfbdy ibvf tif trbnslbtf from tif dfvidfTrbnsform,
         * but tif font mby ibvf b trbnslbtion domponfnt too.
         */
        if (font.isTrbnsformfd()) {
            AffinfTrbnsform fontTx = font.gftTrbnsform();
            flobt trbnslbtfX = (flobt)(fontTx.gftTrbnslbtfX());
            flobt trbnslbtfY = (flobt)(fontTx.gftTrbnslbtfY());
            if (Mbti.bbs(trbnslbtfX) < 0.00001) trbnslbtfX = 0f;
            if (Mbti.bbs(trbnslbtfY) < 0.00001) trbnslbtfY = 0f;
            usfrpos.x += trbnslbtfX; usfrpos.y += trbnslbtfY;
        }
        dfvidfTrbnsform.trbnsform(usfrpos, dfvpos);

        if (gftClip() != null) {
            dfvidfClip(gftClip().gftPbtiItfrbtor(dfvidfTrbnsform));
        }

        /* Gft tif font sizf in dfvidf doordinbtfs.
         * Tif sizf nffdfd is tif font ifigit sdblfd to dfvidf spbdf.
         * Altiougi wf ibvf blrfbdy tfstfd tibt tifrf is no sifbr,
         * tifrf mby bf b non-uniform sdblf, so tif widti of tif font
         * dofs not sdblf fqublly witi tif ifigit. Tibt is ibndlfd
         * by spfdifying bn 'bvfrbgf widti' sdblf to GDI.
         */
        flobt fontSizf = font.gftSizf2D();

        Point2D.Doublf pty = nfw Point2D.Doublf(0.0, 1.0);
        fontTrbnsform.dfltbTrbnsform(pty, pty);
        doublf sdblfFbdtorY = Mbti.sqrt(pty.x*pty.x+pty.y*pty.y);
        flobt sdblfdFontSizfY = (flobt)(fontSizf * sdblfFbdtorY);

        Point2D.Doublf pt = nfw Point2D.Doublf(1.0, 0.0);
        fontTrbnsform.dfltbTrbnsform(pt, pt);
        doublf sdblfFbdtorX = Mbti.sqrt(pt.x*pt.x+pt.y*pt.y);
        flobt sdblfdFontSizfX = (flobt)(fontSizf * sdblfFbdtorX);

        flobt bwSdblf = gftAwSdblf(sdblfFbdtorX, sdblfFbdtorY);
        int ibnglf = gftAnglf(pt);

        int numGlypis = gv.gftNumGlypis();
        int[] glypiCodfs = gv.gftGlypiCodfs(0, numGlypis, null);
        flobt[] glypiPos = gv.gftGlypiPositions(0, numGlypis, null);

        /* lbyout rfplbdfs glypis wiidi ibvf bffn dombinfd bwby
         * witi 0xffff or 0xffff. Tifsf brf supposfd to bf invisiblf
         * bnd wf nffd to ibndlf tiis ifrf bs GDI will intfrprft it
         * bs b missing glypi. Wf'll do it ifrf by dompbdting tif
         * glypi dodfs brrby, but wf ibvf to do it in donjundtion witi
         * dompbdting tif positions/bdvbndfs brrbys too AND updbting
         * tif numbfr of glypis ..
         * Notf tibt sindf tif slot numbfr for dompositfs is in tif
         * signifidbnt bytf wf nffd to mbsk out tibt for dompbrison of
         * tif invisiblf glypi.
         */
        int invisiblfGlypiCnt = 0;
        for (int gd=0; gd<numGlypis; gd++) {
            if ((glypiCodfs[gd] & 0xffff) >=
                CibrToGlypiMbppfr.INVISIBLE_GLYPHS) {
                invisiblfGlypiCnt++;
            }
        }
        if (invisiblfGlypiCnt > 0) {
            int visiblfGlypiCnt = numGlypis - invisiblfGlypiCnt;
            int[] visiblfGlypiCodfs = nfw int[visiblfGlypiCnt];
            flobt[] visiblfPositions = nfw flobt[visiblfGlypiCnt*2];
            int indfx = 0;
            for (int i=0; i<numGlypis; i++) {
                if ((glypiCodfs[i] & 0xffff)
                    < CibrToGlypiMbppfr.INVISIBLE_GLYPHS) {
                    visiblfGlypiCodfs[indfx] = glypiCodfs[i];
                    visiblfPositions[indfx*2]   = glypiPos[i*2];
                    visiblfPositions[indfx*2+1] = glypiPos[i*2+1];
                    indfx++;
                }
            }
            numGlypis = visiblfGlypiCnt;
            glypiCodfs = visiblfGlypiCodfs;
            glypiPos = visiblfPositions;
        }

        /* To gft GDI to rotbtf glypis wf nffd to spfdify tif bnglf
         * of rotbtion to GDI wifn drfbting tif HFONT. Tiis impliditly
         * blso rotbtfs tif bbsflinf, bnd tiis bdjusts tif X & Y bdvbndfs
         * of tif glypis bddordingly.
         * Wifn wf spfdify tif bdvbndfs, tify brf in dfvidf spbdf, so
         * wf don't wbnt bny furtifr intfrprftbtion bpplifd by GDI, but
         * sindf bs notfd tif bdvbndfs brf intfrprftfd in tif HFONT's
         * doordinbtf spbdf, our bdvbndfs would bf rotbtfd bgbin.
         * Wf don't ibvf bny wby to tfll GDI to rotbtf only tif glypis bnd
         * not tif bdvbndfs, so wf nffd to bddount for tiis in tif bdvbndfs
         * wf supply, by supplying unrotbtfd bdvbndfs.
         * Notf tibt "ibnglf" is in tif oppositf dirfdtion to 2D's normbl
         * dirfdtion of rotbtion, so tiis rotbtion invfrts tif
         * rotbtion flfmfnt of tif dfvidfTrbnsform.
         */
        AffinfTrbnsform bdvbndfTrbnsform =
            nfw AffinfTrbnsform(dfvidfTrbnsform);
        bdvbndfTrbnsform.rotbtf(ibnglf*Mbti.PI/1800.0);
        flobt[] glypiAdvPos = nfw flobt[glypiPos.lfngti];

        bdvbndfTrbnsform.trbnsform(glypiPos, 0,         //sourdf
                                   glypiAdvPos, 0,      //dfstinbtion
                                   glypiPos.lfngti/2);  //num points

        Font2D font2D = FontUtilitifs.gftFont2D(font);
        if (font2D instbndfof TrufTypfFont) {
            String fbmily = font2D.gftFbmilyNbmf(null);
            int stylf = font.gftStylf() | font2D.gftStylf();
            if (!wPrintfrJob.sftFont(fbmily, sdblfdFontSizfY, stylf,
                                     ibnglf, bwSdblf)) {
                rfturn fblsf;
            }
            wPrintfrJob.glypisOut(glypiCodfs, dfvpos.x, dfvpos.y, glypiAdvPos);

        } flsf if (font2D instbndfof CompositfFont) {
            /* Compositf fonts brf mbdf up of multiplf fonts bnd fbdi
             * substring tibt usfs b pbrtidulbr domponfnt font nffds to
             * bf sfpbrbtfly sfnt to GDI.
             * Tiis works for stbndbrd dompositf fonts, bltfrnbtf onfs,
             * Fonts tibt brf b piysidbl font bbdkfd by b stbndbrd dompositf,
             * bnd witi fbllbbdk fonts.
             */
            CompositfFont dompFont = (CompositfFont)font2D;
            flobt usfrx = x, usfry = y;
            flobt dfvx = dfvpos.x, dfvy = dfvpos.y;

            int stbrt = 0, fnd = 0, slot = 0;
            wiilf (fnd < numGlypis) {

                stbrt = fnd;
                slot = glypiCodfs[stbrt] >>> 24;

                wiilf (fnd < numGlypis && ((glypiCodfs[fnd] >>> 24) == slot)) {
                    fnd++;
                }
                /* If wf dbn't gft tif font, bbil to outlinfs.
                 * But wf siould blwbys bf bblf to gft bll fonts for
                 * Compositfs, so tiis is unlikfly, so bny ovfrstriking
                 * if only onf slot is unbvbilbblf is not worti worrying
                 * bbout.
                 */
                PiysidblFont slotFont = dompFont.gftSlotFont(slot);
                if (!(slotFont instbndfof TrufTypfFont)) {
                    rfturn fblsf;
                }
                String fbmily = slotFont.gftFbmilyNbmf(null);
                int stylf = font.gftStylf() | slotFont.gftStylf();
                if (!wPrintfrJob.sftFont(fbmily, sdblfdFontSizfY, stylf,
                                         ibnglf, bwSdblf)) {
                    rfturn fblsf;
                }

                int[] glypis = Arrbys.dopyOfRbngf(glypiCodfs, stbrt, fnd);
                flobt[] posns = Arrbys.dopyOfRbngf(glypiAdvPos,
                                                   stbrt*2, fnd*2);
                if (stbrt != 0) {
                    Point2D.Flobt p =
                        nfw Point2D.Flobt(x+glypiPos[stbrt*2],
                                          y+glypiPos[stbrt*2+1]);
                    dfvidfTrbnsform.trbnsform(p, p);
                    dfvx = p.x;
                    dfvy = p.y;
                }
                wPrintfrJob.glypisOut(glypis, dfvx, dfvy, posns);
            }
        } flsf {
            rfturn fblsf;
        }
        rfturn truf;
    }

    privbtf void tfxtOut(String str,
                          Font font, PiysidblFont font2D,
                          FontRfndfrContfxt frd,
                          flobt dfvidfSizf, int rotbtion, flobt bwSdblf,
                          AffinfTrbnsform dfvidfTrbnsform,
                          doublf sdblfFbdtorX,
                          flobt usfrx, flobt usfry,
                          flobt dfvx, flobt dfvy, flobt tbrgftW) {

         String fbmily = font2D.gftFbmilyNbmf(null);
         int stylf = font.gftStylf() | font2D.gftStylf();
         WPrintfrJob wPrintfrJob = (WPrintfrJob)gftPrintfrJob();
         boolfbn sftFont = wPrintfrJob.sftFont(fbmily, dfvidfSizf, stylf,
                                               rotbtion, bwSdblf);
         if (!sftFont) {
             supfr.drbwString(str, usfrx, usfry, font, frd, tbrgftW);
             rfturn;
         }

         flobt[] glypiPos = null;
         if (!okGDIMftrids(str, font, frd, sdblfFbdtorX)) {
             /* If tifrf is b 1:1 dibr->glypi mbpping tifn dibr positions
              * brf tif sbmf bs glypi positions bnd wf dbn tfll GDI
              * wifrf to plbdf tif glypis.
              * On drbwing wf rfmovf dontrol dibrs so tifsf nffd to bf
              * rfmovfd now so tif string bnd positions brf tif sbmf lfngti.
              * For otifr dbsfs wf nffd to pbss glypi dodfs to GDI.
              */
             str = wPrintfrJob.rfmovfControlCibrs(str);
             dibr[] dibrs = str.toCibrArrby();
             int lfn = dibrs.lfngti;
             GlypiVfdtor gv = null;
             if (!FontUtilitifs.isComplfxTfxt(dibrs, 0, lfn)) {
                 gv = font.drfbtfGlypiVfdtor(frd, str);
             }
             if (gv == null) {
                 supfr.drbwString(str, usfrx, usfry, font, frd, tbrgftW);
                 rfturn;
             }
             glypiPos = gv.gftGlypiPositions(0, lfn, null);
             Point2D gvAdvPt = gv.gftGlypiPosition(gv.gftNumGlypis());

             /* GDI bdvbndfs must not indludf dfvidf spbdf rotbtion.
              * Sff fbrlifr dommfnt in printGlypiVfdtor() for dftbils.
              */
             AffinfTrbnsform bdvbndfTrbnsform =
               nfw AffinfTrbnsform(dfvidfTrbnsform);
             bdvbndfTrbnsform.rotbtf(rotbtion*Mbti.PI/1800.0);
             flobt[] glypiAdvPos = nfw flobt[glypiPos.lfngti];

             bdvbndfTrbnsform.trbnsform(glypiPos, 0,         //sourdf
                                        glypiAdvPos, 0,      //dfstinbtion
                                        glypiPos.lfngti/2);  //num points
             glypiPos = glypiAdvPos;
         }
         wPrintfrJob.tfxtOut(str, dfvx, dfvy, glypiPos);
     }

     /* If 2D bnd GDI bgrff on tif bdvbndf of tif string wf do not
      * nffd to fxpliditly bssign glypi positions.
      * If wf brf to usf tif GDI bdvbndf, rfquirf it to bgrff witi
      * JDK to b prfdision of <= 0.2% - if 1 pixfl in 500
      * disdrfpbndy bftfr rounding tif 2D bdvbndf to tif
      * nfbrfst pixfl bnd is grfbtfr tibn onf pixfl in totbl.
      * if strings < 500 pixfls in lfngti will bf OK so long
      * bs tify difffr by only 1 pixfl fvfn tiougi tibt is > 0.02%
      * Tif bounds from 2D brf in usfr spbdf so nffd to
      * bf sdblfd to dfvidf spbdf for dompbrison witi GDI.
      * sdblfX is tif sdblf from usfr spbdf to dfvidf spbdf nffdfd for tiis.
      */
     privbtf boolfbn okGDIMftrids(String str, Font font,
                                  FontRfndfrContfxt frd, doublf sdblfX) {

         Rfdtbnglf2D bds = font.gftStringBounds(str, frd);
         doublf jdkAdvbndf = bds.gftWidti();
         jdkAdvbndf = Mbti.round(jdkAdvbndf*sdblfX);
         int gdiAdvbndf = ((WPrintfrJob)gftPrintfrJob()).gftGDIAdvbndf(str);
         if (jdkAdvbndf > 0 && gdiAdvbndf > 0) {
             doublf diff = Mbti.bbs(gdiAdvbndf-jdkAdvbndf);
             doublf rbtio = gdiAdvbndf/jdkAdvbndf;
             if (rbtio < 1) {
                 rbtio = 1/rbtio;
             }
             rfturn diff <= 1 || rbtio < 1.002;
         }
         rfturn truf;
     }

    /**
     * Tif vbrious <dodf>drbwImbgf()</dodf> mftiods for
     * <dodf>WPbtiGrbpiids</dodf> brf bll dfdomposfd
     * into bn invodbtion of <dodf>drbwImbgfToPlbtform</dodf>.
     * Tif portion of tif pbssfd in imbgf dffinfd by
     * <dodf>srdX, srdY, srdWidti, bnd srdHfigit</dodf>
     * is trbnsformfd by tif supplifd AffinfTrbnsform bnd
     * drbwn using GDI to tif printfr dontfxt.
     *
     * @pbrbm   img     Tif imbgf to bf drbwn.
     * @pbrbm   xform   Usfd to trbnsform tif imbgf bfforf drbwing.
     *                  Tiis dbn bf null.
     * @pbrbm   bgdolor Tiis dolor is drbwn wifrf tif imbgf ibs trbnspbrfnt
     *                  pixfls. If tiis pbrbmftfr is null tifn tif
     *                  pixfls blrfbdy in tif dfstinbtion siould siow
     *                  tirougi.
     * @pbrbm   srdX    Witi srdY tiis dffinfs tif uppfr-lfft dornfr
     *                  of tif portion of tif imbgf to bf drbwn.
     *
     * @pbrbm   srdY    Witi srdX tiis dffinfs tif uppfr-lfft dornfr
     *                  of tif portion of tif imbgf to bf drbwn.
     * @pbrbm   srdWidti    Tif widti of tif portion of tif imbgf to
     *                      bf drbwn.
     * @pbrbm   srdHfigit   Tif ifigit of tif portion of tif imbgf to
     *                      bf drbwn.
     * @pbrbm   ibndlingTrbnspbrfndy if bfing rfdursivfly dbllfd to
     *                    print opbquf rfgion of trbnspbrfnt imbgf
     */
    @Ovfrridf
    protfdtfd boolfbn drbwImbgfToPlbtform(Imbgf imbgf, AffinfTrbnsform xform,
                                          Color bgdolor,
                                          int srdX, int srdY,
                                          int srdWidti, int srdHfigit,
                                          boolfbn ibndlingTrbnspbrfndy) {

        BufffrfdImbgf img = gftBufffrfdImbgf(imbgf);
        if (img == null) {
            rfturn truf;
        }

        WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();

        /* Tif full trbnsform to bf bpplifd to tif imbgf is tif
         * dbllfr's trbnsform dondbtfnbtfd on to tif trbnsform
         * from usfr spbdf to dfvidf spbdf. If tif dbllfr didn't
         * supply b trbnsform tifn wf just bdt bs if tify pbssfd
         * in tif idfntify trbnsform.
         */
        AffinfTrbnsform fullTrbnsform = gftTrbnsform();
        if (xform == null) {
            xform = nfw AffinfTrbnsform();
        }
        fullTrbnsform.dondbtfnbtf(xform);

        /* Split tif full trbnsform into b pbir of
         * trbnsforms. Tif first trbnsform iolds ffffdts
         * tibt GDI (undfr Win95) dbn not pfrform sudi
         * bs rotbtion bnd sifbring. Tif sfdond trbnsform
         * is sftup to iold only tif sdbling ffffdts.
         * Tifsf trbnsforms brf drfbtfd sudi tibt b point,
         * p, in usfr spbdf, wifn trbnsformfd by 'fullTrbnsform'
         * lbnds in tif sbmf plbdf bs wifn it is trbnsformfd
         * by 'rotTrbnsform' bnd tifn 'sdblfTrbnsform'.
         *
         * Tif fntirf imbgf trbnsformbtion is not in Jbvb in ordfr
         * to minimizf tif bmount of mfmory nffdfd in tif VM. By
         * dividing tif trbnsform in two, wf rotbtf bnd sifbr
         * tif sourdf imbgf in its own spbdf bnd only go to
         * tif, usublly, lbrgfr, dfvidf spbdf wifn wf bsk
         * GDI to pfrform tif finbl sdbling.
         * Clbmp tiis to tif dfvidf sdblf for bfttfr qublity printing.
         */
        doublf[] fullMbtrix = nfw doublf[6];
        fullTrbnsform.gftMbtrix(fullMbtrix);

        /* Cbldulbtf tif bmount of sdbling in tif x
         * bnd y dirfdtions. Tiis sdbling is domputfd by
         * trbnsforming b unit vfdtor blong fbdi bxis
         * bnd domputing tif rfsulting mbgnitudf.
         * Tif domputfd vblufs 'sdblfX' bnd 'sdblfY'
         * rfprfsfnt tif bmount of sdbling GDI will bf bskfd
         * to pfrform.
         */
        Point2D.Flobt unitVfdtorX = nfw Point2D.Flobt(1, 0);
        Point2D.Flobt unitVfdtorY = nfw Point2D.Flobt(0, 1);
        fullTrbnsform.dfltbTrbnsform(unitVfdtorX, unitVfdtorX);
        fullTrbnsform.dfltbTrbnsform(unitVfdtorY, unitVfdtorY);

        Point2D.Flobt origin = nfw Point2D.Flobt(0, 0);
        doublf sdblfX = unitVfdtorX.distbndf(origin);
        doublf sdblfY = unitVfdtorY.distbndf(origin);

        doublf dfvRfsX = wPrintfrJob.gftXRfs();
        doublf dfvRfsY = wPrintfrJob.gftYRfs();
        doublf dfvSdblfX = dfvRfsX / DEFAULT_USER_RES;
        doublf dfvSdblfY = dfvRfsY / DEFAULT_USER_RES;

        /* difdk if rotbtfd or sifbrfd */
        int trbnsformTypf = fullTrbnsform.gftTypf();
        boolfbn dlbmpSdblf = ((trbnsformTypf &
                               (AffinfTrbnsform.TYPE_GENERAL_ROTATION |
                                AffinfTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);
        if (dlbmpSdblf) {
            if (sdblfX > dfvSdblfX) sdblfX = dfvSdblfX;
            if (sdblfY > dfvSdblfY) sdblfY = dfvSdblfY;
        }

        /* Wf do not nffd to drbw bnytiing if fitifr sdbling
         * fbdtor is zfro.
         */
        if (sdblfX != 0 && sdblfY != 0) {

            /* Hfrf's tif trbnsformbtion wf will do witi Jbvb2D,
            */
            AffinfTrbnsform rotTrbnsform = nfw AffinfTrbnsform(
                                        fullMbtrix[0] / sdblfX,  //m00
                                        fullMbtrix[1] / sdblfY,  //m10
                                        fullMbtrix[2] / sdblfX,  //m01
                                        fullMbtrix[3] / sdblfY,  //m11
                                        fullMbtrix[4] / sdblfX,  //m02
                                        fullMbtrix[5] / sdblfY); //m12

            /* Tif sdblf trbnsform is not usfd dirfdtly: wf instfbd
             * dirfdtly multiply by sdblfX bnd sdblfY.
             *
             * Condfptublly ifrf is wibt tif sdblfTrbnsform is:
             *
             * AffinfTrbnsform sdblfTrbnsform = nfw AffinfTrbnsform(
             *                      sdblfX,                     //m00
             *                      0,                          //m10
             *                      0,                          //m01
             *                      sdblfY,                     //m11
             *                      0,                          //m02
             *                      0);                         //m12
             */

            /* Convfrt tif imbgf sourdf's rfdtbnglf into tif rotbtfd
             * bnd sifbrfd spbdf. Ondf tifrf, wf dbldulbtf b rfdtbnglf
             * tibt fndlosfs tif rfsulting sibpf. It is tiis rfdtbnglf
             * wiidi dffinfs tif sizf of tif BufffrfdImbgf wf nffd to
             * drfbtf to iold tif trbnsformfd imbgf.
             */
            Rfdtbnglf2D.Flobt srdRfdt = nfw Rfdtbnglf2D.Flobt(srdX, srdY,
                                                              srdWidti,
                                                              srdHfigit);

            Sibpf rotSibpf = rotTrbnsform.drfbtfTrbnsformfdSibpf(srdRfdt);
            Rfdtbnglf2D rotBounds = rotSibpf.gftBounds2D();

            /* bdd b fudgf fbdtor bs somf fp prfdision problfms ibvf
             * bffn obsfrvfd wiidi dbusfd pixfls to bf roundfd down bnd
             * out of tif imbgf.
             */
            rotBounds.sftRfdt(rotBounds.gftX(), rotBounds.gftY(),
                              rotBounds.gftWidti()+0.001,
                              rotBounds.gftHfigit()+0.001);

            int boundsWidti = (int) rotBounds.gftWidti();
            int boundsHfigit = (int) rotBounds.gftHfigit();

            if (boundsWidti > 0 && boundsHfigit > 0) {

                /* If tif imbgf ibs trbnspbrfnt or sfmi-trbnspbrfnt
                 * pixfls tifn wf'll ibvf tif bpplidbtion rf-rfndfr
                 * tif portion of tif pbgf dovfrfd by tif imbgf.
                 * Tif BufffrfdImbgf will bf bt tif imbgf's rfsolution
                 * to bvoid wbsting mfmory. By rf-rfndfring tiis portion
                 * of b pbgf bll dompositing is donf by Jbvb2D into
                 * tif BufffrfdImbgf bnd tifn tibt imbgf is dopifd to
                 * GDI.
                 * Howfvfr sfvfrbl spfdibl dbsfs dbn bf ibndlfd otifrwisf:
                 * - bitmbsk trbnspbrfndy witi b solid bbdkground dolour
                 * - imbgfs wiidi ibvf trbnspbrfndy dolor modfls but no
                 * trbnspbrfnt pixfls
                 * - imbgfs witi bitmbsk trbnspbrfndy bnd bn IndfxColorModfl
                 * (tif dommon trbnspbrfnt GIF dbsf) dbn bf ibndlfd by
                 * rfndfring just tif opbquf pixfls.
                 */
                boolfbn drbwOpbquf = truf;
                if (!ibndlingTrbnspbrfndy && ibsTrbnspbrfntPixfls(img)) {
                    drbwOpbquf = fblsf;
                    if (isBitmbskTrbnspbrfndy(img)) {
                        if (bgdolor == null) {
                            if (drbwBitmbskImbgf(img, xform, bgdolor,
                                                 srdX, srdY,
                                                 srdWidti, srdHfigit)) {
                                // imbgf drbwn, just rfturn.
                                rfturn truf;
                            }
                        } flsf if (bgdolor.gftTrbnspbrfndy()
                                   == Trbnspbrfndy.OPAQUE) {
                            drbwOpbquf = truf;
                        }
                    }
                    if (!dbnDoRfdrbws()) {
                        drbwOpbquf = truf;
                    }
                } flsf {
                    // if tifrf's no trbnspbrfnt pixfls tifrf's no nffd
                    // for b bbdkground dolour. Tiis dbn bvoid fdgf brtifbdts
                    // in rotbtion dbsfs.
                    bgdolor = null;
                }
                // if srd rfgion fxtfnds bfyond tif imbgf, tif "opbquf" pbti
                // mby blit b/g dolour (indluding wiitf) wifrf it sioudn't.
                if ((srdX+srdWidti > img.gftWidti(null) ||
                     srdY+srdHfigit > img.gftHfigit(null))
                    && dbnDoRfdrbws()) {
                    drbwOpbquf = fblsf;
                }
                if (drbwOpbquf == fblsf) {

                    fullTrbnsform.gftMbtrix(fullMbtrix);
                    AffinfTrbnsform tx =
                        nfw AffinfTrbnsform(
                                            fullMbtrix[0] / dfvSdblfX,  //m00
                                            fullMbtrix[1] / dfvSdblfY,  //m10
                                            fullMbtrix[2] / dfvSdblfX,  //m01
                                            fullMbtrix[3] / dfvSdblfY,  //m11
                                            fullMbtrix[4] / dfvSdblfX,  //m02
                                            fullMbtrix[5] / dfvSdblfY); //m12

                    Rfdtbnglf2D.Flobt rfdt =
                        nfw Rfdtbnglf2D.Flobt(srdX, srdY, srdWidti, srdHfigit);

                    Sibpf sibpf = fullTrbnsform.drfbtfTrbnsformfdSibpf(rfdt);
                    // Rfgion isn't usfr spbdf bfdbusf its potfntiblly
                    // bffn rotbtfd for lbndsdbpf.
                    Rfdtbnglf2D rfgion = sibpf.gftBounds2D();

                    rfgion.sftRfdt(rfgion.gftX(), rfgion.gftY(),
                                   rfgion.gftWidti()+0.001,
                                   rfgion.gftHfigit()+0.001);

                    // Try to limit tif bmount of mfmory usfd to 8Mb, so
                    // if bt dfvidf rfsolution tiis fxdffds b dfrtbin
                    // imbgf sizf tifn sdblf down tif rfgion to fit in
                    // tibt mfmory, but nfvfr to lfss tibn 72 dpi.

                    int w = (int)rfgion.gftWidti();
                    int i = (int)rfgion.gftHfigit();
                    int nbytfs = w * i * 3;
                    int mbxBytfs = 8 * 1024 * 1024;
                    doublf origDpi = (dfvRfsX < dfvRfsY) ? dfvRfsX : dfvRfsY;
                    int dpi = (int)origDpi;
                    doublf sdblfFbdtor = 1;

                    doublf mbxSFX = w/(doublf)boundsWidti;
                    doublf mbxSFY = i/(doublf)boundsHfigit;
                    doublf mbxSF = (mbxSFX > mbxSFY) ? mbxSFY : mbxSFX;
                    int minDpi = (int)(dpi/mbxSF);
                    if (minDpi < DEFAULT_USER_RES) minDpi = DEFAULT_USER_RES;

                    wiilf (nbytfs > mbxBytfs && dpi > minDpi) {
                        sdblfFbdtor *= 2;
                        dpi /= 2;
                        nbytfs /= 4;
                    }
                    if (dpi < minDpi) {
                        sdblfFbdtor = (origDpi / minDpi);
                    }

                    rfgion.sftRfdt(rfgion.gftX()/sdblfFbdtor,
                                   rfgion.gftY()/sdblfFbdtor,
                                   rfgion.gftWidti()/sdblfFbdtor,
                                   rfgion.gftHfigit()/sdblfFbdtor);

                    /*
                     * Wf nffd to ibvf tif dlip bs pbrt of tif sbvfd stbtf,
                     * fitifr dirfdtly, or bll tif domponfnts tibt brf
                     * nffdfd to rfdonstitutf it (imbgf sourdf brfb,
                     * imbgf trbnsform bnd durrfnt grbpiids trbnsform).
                     * Tif dlip is dfsdribfd in usfr spbdf, so wf nffd to
                     * sbvf tif durrfnt grbpiids trbnsform bnywby so just
                     * sbvf tifsf two.
                     */
                    wPrintfrJob.sbvfStbtf(gftTrbnsform(), gftClip(),
                                          rfgion, sdblfFbdtor, sdblfFbdtor);
                    rfturn truf;
                /* Tif imbgf dbn bf rfndfrfd dirfdtly by GDI so wf
                 * dopy it into b BufffrfdImbgf (tiis tbkfs dbrf of
                 * ColorSpbdf bnd BufffrfdImbgfOp issufs) bnd tifn
                 * sfnd tibt to GDI.
                 */
                } flsf {
                    /* Crfbtf b bufffrfd imbgf big fnougi to iold tif portion
                     * of tif sourdf imbgf bfing printfd.
                     * Tif imbgf formbt will bf 3BYTE_BGR for most dbsfs
                     * fxdfpt wifrf wf dbn rfprfsfnt tif imbgf bs b 1, 4 or 8
                     * bits-pfr-pixfl DIB.
                     */
                    int dibTypf = BufffrfdImbgf.TYPE_3BYTE_BGR;
                    IndfxColorModfl idm = null;

                    ColorModfl dm = img.gftColorModfl();
                    int imgTypf = img.gftTypf();
                    if (dm instbndfof IndfxColorModfl &&
                        dm.gftPixflSizf() <= 8 &&
                        (imgTypf == BufffrfdImbgf.TYPE_BYTE_BINARY ||
                         imgTypf == BufffrfdImbgf.TYPE_BYTE_INDEXED)) {
                        idm = (IndfxColorModfl)dm;
                        dibTypf = imgTypf;
                        /* BYTE_BINARY mby bf 2 bpp wiidi DIB dbn't ibndlf.
                         * Convfrt tiis to 4bpp.
                         */
                        if (imgTypf == BufffrfdImbgf.TYPE_BYTE_BINARY &&
                            dm.gftPixflSizf() == 2) {

                            int[] rgbs = nfw int[16];
                            idm.gftRGBs(rgbs);
                            boolfbn trbnspbrfnt =
                                idm.gftTrbnspbrfndy() != Trbnspbrfndy.OPAQUE;
                            int trbnspixfl = idm.gftTrbnspbrfntPixfl();

                            idm = nfw IndfxColorModfl(4, 16,
                                                      rgbs, 0,
                                                      trbnspbrfnt, trbnspixfl,
                                                      DbtbBufffr.TYPE_BYTE);
                        }
                    }

                    int iw = (int)rotBounds.gftWidti();
                    int ii = (int)rotBounds.gftHfigit();
                    BufffrfdImbgf dffpImbgf = null;
                    /* If tifrf is no spfdibl trbnsform nffdfd (tiis is b
                     * simplf BLIT) bnd dibTypf == img.gftTypf() bnd wf
                     * didn't drfbtf b nfw IndfxColorModfl AND tif wiolf of
                     * tif sourdf imbgf is bfing drbwn (GDI dbn't ibndlf b
                     * portion of tif originbl sourdf imbgf) tifn wf
                     * don't nffd to drfbtf tiis intfrmfdibtf imbgf - GDI
                     * dbn bddfss tif dbtb from tif originbl imbgf.
                     * Sindf b subimbgf dbn bf drfbtfd by dblling
                     * BufffrfdImbgf.gftSubImbgf() tibt dondition nffds to
                     * bf bddountfd for too. Tiis implifs inspfdting tif
                     * dbtb bufffr. In tif fnd too mbny dbsfs brf not bblf
                     * to tbkf bdvbntbgf of tiis option until wf dbn tfbdi
                     * tif nbtivf dodf to propfrly nbvigbtf tif dbtb bufffr.
                     * Tifrf wbs b dondfrn tibt sindf in nbtivf dodf sindf wf
                     * nffd to DWORD blign bnd flip to b bottom up DIB tibt
                     * tif "originbl" imbgf mby gft pfrturbfd by tiis.
                     * But in fbdt wf blwbys mbllod nfw mfmory for tif blignfd
                     * dopy so tiis isn't b problfm.
                     * Tiis points out tibt wf bllodbtf two tfmporbrifs dopifs
                     * of tif imbgf : onf in Jbvb bnd onf in nbtivf. If
                     * wf dbn bf smbrtfr bbout not bllodbting tiis onf wifn
                     * not nffdfd, tibt would sffm likf b good tiing to do,
                     * fvfn if in mbny dbsfs tif ColorModfls don't mbtdi bnd
                     * its nffdfd.
                     * Until bll of tiis is rfsolvfd nfwImbgf is blwbys truf.
                     */
                    boolfbn nfwImbgf = truf;
                    if (nfwImbgf) {
                        if (idm == null) {
                            dffpImbgf = nfw BufffrfdImbgf(iw, ii, dibTypf);
                        } flsf {
                            dffpImbgf = nfw BufffrfdImbgf(iw, ii, dibTypf,idm);
                        }

                        /* Sftup b Grbpiids2D on to tif BufffrfdImbgf so tibt
                         * tif sourdf imbgf wifn dopifd, lbnds witiin tif
                         * imbgf bufffr.
                         */
                        Grbpiids2D imbgfGrbpiids = dffpImbgf.drfbtfGrbpiids();
                        imbgfGrbpiids.dlipRfdt(0, 0,
                                               dffpImbgf.gftWidti(),
                                               dffpImbgf.gftHfigit());

                        imbgfGrbpiids.trbnslbtf(-rotBounds.gftX(),
                                                -rotBounds.gftY());
                        imbgfGrbpiids.trbnsform(rotTrbnsform);

                        /* Fill tif BufffrfdImbgf fitifr witi tif dbllfr
                         * supplifd dolor, 'bgColor' or, if null, witi wiitf.
                         */
                        if (bgdolor == null) {
                            bgdolor = Color.wiitf;
                        }

                        imbgfGrbpiids.drbwImbgf(img,
                                                srdX, srdY,
                                                srdX + srdWidti,
                                                srdY + srdHfigit,
                                                srdX, srdY,
                                                srdX + srdWidti,
                                                srdY + srdHfigit,
                                                bgdolor, null);
                        imbgfGrbpiids.disposf();
                    } flsf {
                        dffpImbgf = img;
                    }

                    /* Sdblf tif bounding rfdtbnglf by tif sdblf trbnsform.
                     * Bfdbusf tif sdbling trbnsform ibs only x bnd y
                     * sdbling domponfnts it is fquivblfnt to multiply
                     * tif x domponfnts of tif bounding rfdtbnglf by
                     * tif x sdbling fbdtor bnd to multiply tif y domponfnts
                     * by tif y sdbling fbdtor.
                     */
                    Rfdtbnglf2D.Flobt sdblfdBounds
                            = nfw Rfdtbnglf2D.Flobt(
                                    (flobt) (rotBounds.gftX() * sdblfX),
                                    (flobt) (rotBounds.gftY() * sdblfY),
                                    (flobt) (rotBounds.gftWidti() * sdblfX),
                                    (flobt) (rotBounds.gftHfigit() * sdblfY));

                    /* Pull tif rbstfr dbtb from tif bufffrfd imbgf
                     * bnd pbss it blong to GDI.
                     */
                    WritbblfRbstfr rbstfr = dffpImbgf.gftRbstfr();
                    bytf[] dbtb;
                    if (rbstfr instbndfof BytfComponfntRbstfr) {
                        dbtb = ((BytfComponfntRbstfr)rbstfr).gftDbtbStorbgf();
                    } flsf if (rbstfr instbndfof BytfPbdkfdRbstfr) {
                        dbtb = ((BytfPbdkfdRbstfr)rbstfr).gftDbtbStorbgf();
                    } flsf {
                        rfturn fblsf;
                    }

                    int bitsPfrPixfl = 24;
                    SbmplfModfl sm = dffpImbgf.gftSbmplfModfl();
                    if (sm instbndfof ComponfntSbmplfModfl) {
                        ComponfntSbmplfModfl dsm = (ComponfntSbmplfModfl)sm;
                        bitsPfrPixfl = dsm.gftPixflStridf() * 8;
                    } flsf if (sm instbndfof MultiPixflPbdkfdSbmplfModfl) {
                        MultiPixflPbdkfdSbmplfModfl mppsm =
                            (MultiPixflPbdkfdSbmplfModfl)sm;
                        bitsPfrPixfl = mppsm.gftPixflBitStridf();
                    } flsf {
                        if (idm != null) {
                            int diw = dffpImbgf.gftWidti();
                            int dii = dffpImbgf.gftHfigit();
                            if (diw > 0 && dii > 0) {
                                bitsPfrPixfl = dbtb.lfngti*8/diw/dii;
                            }
                        }
                    }

                    /* Bfdbusf tif dbllfr's imbgf ibs bffn rotbtfd
                     * bnd sifbrfd into our BufffrfdImbgf bnd bfdbusf
                     * wf will bf ibnding tibt BufffrfdImbgf dirfdtly to
                     * GDI, wf nffd to sft bn bdditionbl dlip. Tiis dlip
                     * mbkfs surf tibt only pbrts of tif BufffrfdImbgf
                     * tibt brf blso pbrt of tif dbllfr's imbgf brf drbwn.
                     */
                    Sibpf ioldClip = gftClip();
                    dlip(xform.drfbtfTrbnsformfdSibpf(srdRfdt));
                    dfvidfClip(gftClip().gftPbtiItfrbtor(gftTrbnsform()));

                    wPrintfrJob.drbwDIBImbgf
                        (dbtb, sdblfdBounds.x, sdblfdBounds.y,
                         (flobt)Mbti.rint(sdblfdBounds.widti+0.5),
                         (flobt)Mbti.rint(sdblfdBounds.ifigit+0.5),
                         0f, 0f,
                         dffpImbgf.gftWidti(), dffpImbgf.gftHfigit(),
                         bitsPfrPixfl, idm);

                    sftClip(ioldClip);
                }
            }
        }

        rfturn truf;
    }

    /**
     * Hbvf tif printing bpplidbtion rfdrbw fvfrytiing tibt fblls
     * witiin tif pbgf bounds dffinfd by <dodf>rfgion</dodf>.
     */
    @Ovfrridf
    publid void rfdrbwRfgion(Rfdtbnglf2D rfgion, doublf sdblfX, doublf sdblfY,
                             Sibpf sbvfdClip, AffinfTrbnsform sbvfdTrbnsform)
            tirows PrintfrExdfption {

        WPrintfrJob wPrintfrJob = (WPrintfrJob)gftPrintfrJob();
        Printbblf pbintfr = gftPrintbblf();
        PbgfFormbt pbgfFormbt = gftPbgfFormbt();
        int pbgfIndfx = gftPbgfIndfx();

        /* Crfbtf b bufffrfd imbgf big fnougi to iold tif portion
         * of tif sourdf imbgf bfing printfd.
         */
        BufffrfdImbgf dffpImbgf = nfw BufffrfdImbgf(
                                        (int) rfgion.gftWidti(),
                                        (int) rfgion.gftHfigit(),
                                        BufffrfdImbgf.TYPE_3BYTE_BGR);

        /* Gft b grbpiids for tif bpplidbtion to rfndfr into.
         * Wf initiblizf tif bufffr to wiitf in ordfr to
         * mbtdi tif pbpfr bnd tifn wf siift tif BufffrfdImbgf
         * so tibt it dovfrs tif brfb on tif pbgf wifrf tif
         * dbllfr's Imbgf will bf drbwn.
         */
        Grbpiids2D g = dffpImbgf.drfbtfGrbpiids();
        ProxyGrbpiids2D proxy = nfw ProxyGrbpiids2D(g, wPrintfrJob);
        proxy.sftColor(Color.wiitf);
        proxy.fillRfdt(0, 0, dffpImbgf.gftWidti(), dffpImbgf.gftHfigit());
        proxy.dlipRfdt(0, 0, dffpImbgf.gftWidti(), dffpImbgf.gftHfigit());

        proxy.trbnslbtf(-rfgion.gftX(), -rfgion.gftY());

        /* Cbldulbtf tif rfsolution of tif sourdf imbgf.
         */
        flobt sourdfRfsX = (flobt)(wPrintfrJob.gftXRfs() / sdblfX);
        flobt sourdfRfsY = (flobt)(wPrintfrJob.gftYRfs() / sdblfY);

        /* Tif bpplidbtion fxpfdts to sff usfr spbdf bt 72 dpi.
         * so dibngf usfr spbdf from imbgf sourdf rfsolution to
         *  72 dpi.
         */
        proxy.sdblf(sourdfRfsX / DEFAULT_USER_RES,
                    sourdfRfsY / DEFAULT_USER_RES);

        proxy.trbnslbtf(
            -wPrintfrJob.gftPiysidblPrintbblfX(pbgfFormbt.gftPbpfr())
               / wPrintfrJob.gftXRfs() * DEFAULT_USER_RES,
            -wPrintfrJob.gftPiysidblPrintbblfY(pbgfFormbt.gftPbpfr())
               / wPrintfrJob.gftYRfs() * DEFAULT_USER_RES);
        /* NB Usfr spbdf now ibs to bf bt 72 dpi for tiis dbld to bf dorrfdt */
        proxy.trbnsform(nfw AffinfTrbnsform(gftPbgfFormbt().gftMbtrix()));
        proxy.sftPbint(Color.blbdk);

        pbintfr.print(proxy, pbgfFormbt, pbgfIndfx);

        g.disposf();

        /* Wf nffd to sft tif dfvidf dlip using sbvfd informbtion.
         * sbvfdClip intfrsfdts tif usfr dlip witi b dlip tibt rfstridts
         * tif GDI rfndfrfd brfb of our BufffrfdImbgf to tibt wiidi
         * mby dorrfspond to b rotbtf or sifbr.
         * Tif sbvfd dfvidf trbnsform is nffdfd bs tif durrfnt trbnsform
         * is not likfly to bf tif sbmf.
         */
        dfvidfClip(sbvfdClip.gftPbtiItfrbtor(sbvfdTrbnsform));

        /* Sdblf tif bounding rfdtbnglf by tif sdblf trbnsform.
         * Bfdbusf tif sdbling trbnsform ibs only x bnd y
         * sdbling domponfnts it is fquivblfnt to multiplying
         * tif x domponfnts of tif bounding rfdtbnglf by
         * tif x sdbling fbdtor bnd to multiplying tif y domponfnts
         * by tif y sdbling fbdtor.
         */
        Rfdtbnglf2D.Flobt sdblfdBounds
                = nfw Rfdtbnglf2D.Flobt(
                        (flobt) (rfgion.gftX() * sdblfX),
                        (flobt) (rfgion.gftY() * sdblfY),
                        (flobt) (rfgion.gftWidti() * sdblfX),
                        (flobt) (rfgion.gftHfigit() * sdblfY));

        /* Pull tif rbstfr dbtb from tif bufffrfd imbgf
         * bnd pbss it blong to GDI.
         */
       BytfComponfntRbstfr tilf
                = (BytfComponfntRbstfr)dffpImbgf.gftRbstfr();

        wPrintfrJob.drbwImbgf3BytfBGR(tilf.gftDbtbStorbgf(),
                    sdblfdBounds.x, sdblfdBounds.y,
                    sdblfdBounds.widti,
                    sdblfdBounds.ifigit,
                    0f, 0f,
                    dffpImbgf.gftWidti(), dffpImbgf.gftHfigit());

    }

    /*
     * Fill tif pbti dffinfd by <dodf>pbtiItfr</dodf>
     * witi tif spfdififd dolor.
     * Tif pbti is providfd in dfvidf doordinbtfs.
     */
    @Ovfrridf
    protfdtfd void dfvidfFill(PbtiItfrbtor pbtiItfr, Color dolor) {

        WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();

        donvfrtToWPbti(pbtiItfr);
        wPrintfrJob.sflfdtSolidBrusi(dolor);
        wPrintfrJob.fillPbti();
    }

    /*
     * Sft tif printfr dfvidf's dlip to bf tif
     * pbti dffinfd by <dodf>pbtiItfr</dodf>
     * Tif pbti is providfd in dfvidf doordinbtfs.
     */
    @Ovfrridf
    protfdtfd void dfvidfClip(PbtiItfrbtor pbtiItfr) {

        WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();

        donvfrtToWPbti(pbtiItfr);
        wPrintfrJob.sflfdtClipPbti();
    }

    /**
     * Drbw tif bounding rfdtbnglf using trbnsformfd doordinbtfs.
     */
     @Ovfrridf
     protfdtfd void dfvidfFrbmfRfdt(int x, int y, int widti, int ifigit,
                                     Color dolor) {

        AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();

        /* difdk if rotbtfd or sifbrfd */
        int trbnsformTypf = dfvidfTrbnsform.gftTypf();
        boolfbn usfPbti = ((trbnsformTypf &
                           (AffinfTrbnsform.TYPE_GENERAL_ROTATION |
                            AffinfTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);

        if (usfPbti) {
            drbw(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
            rfturn;
        }

        Strokf strokf = gftStrokf();

        if (strokf instbndfof BbsidStrokf) {
            BbsidStrokf linfStrokf = (BbsidStrokf) strokf;

            int fndCbp = linfStrokf.gftEndCbp();
            int linfJoin = linfStrokf.gftLinfJoin();


            /* difdk for dffbult stylf bnd try to optimizf it by
             * dblling tif frbmfRfdt nbtivf fundtion instfbd of using pbtis.
             */
            if ((fndCbp == BbsidStrokf.CAP_SQUARE) &&
                (linfJoin == BbsidStrokf.JOIN_MITER) &&
                (linfStrokf.gftMitfrLimit() ==10.0f)) {

                flobt linfWidti = linfStrokf.gftLinfWidti();
                Point2D.Flobt pfnSizf = nfw Point2D.Flobt(linfWidti,
                                                          linfWidti);

                dfvidfTrbnsform.dfltbTrbnsform(pfnSizf, pfnSizf);
                flobt dfvidfLinfWidti = Mbti.min(Mbti.bbs(pfnSizf.x),
                                                 Mbti.bbs(pfnSizf.y));

                /* trbnsform uppfr lfft doordinbtf */
                Point2D.Flobt ul_pos = nfw Point2D.Flobt(x, y);
                dfvidfTrbnsform.trbnsform(ul_pos, ul_pos);

                /* trbnsform lowfr rigit doordinbtf */
                Point2D.Flobt lr_pos = nfw Point2D.Flobt(x + widti,
                                                         y + ifigit);
                dfvidfTrbnsform.trbnsform(lr_pos, lr_pos);

                flobt w = (flobt) (lr_pos.gftX() - ul_pos.gftX());
                flobt i = (flobt)(lr_pos.gftY() - ul_pos.gftY());

                WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();

                /* usf sflfdtStylfPfn, if supportfd */
                if (wPrintfrJob.sflfdtStylfPfn(fndCbp, linfJoin,
                                           dfvidfLinfWidti, dolor) == truf)  {
                    wPrintfrJob.frbmfRfdt((flobt)ul_pos.gftX(),
                                          (flobt)ul_pos.gftY(), w, i);
                }
                /* not supportfd, must bf b Win 9x */
                flsf {

                    doublf lowfrRfs = Mbti.min(wPrintfrJob.gftXRfs(),
                                               wPrintfrJob.gftYRfs());

                    if ((dfvidfLinfWidti/lowfrRfs) < MAX_THINLINE_INCHES) {
                        /* usf tif dffbult pfn stylfs for tiin pfns. */
                        wPrintfrJob.sflfdtPfn(dfvidfLinfWidti, dolor);
                        wPrintfrJob.frbmfRfdt((flobt)ul_pos.gftX(),
                                              (flobt)ul_pos.gftY(), w, i);
                    }
                    flsf {
                        drbw(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
                    }
                }
            }
            flsf {
                drbw(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
            }
        }
     }


     /*
      * Fill tif rfdtbnglf witi spfdififd dolor bnd using Windows'
      * GDI fillRfdt fundtion.
      * Boundbrifs brf dftfrminfd by tif givfn doordinbtfs.
      */
    @Ovfrridf
    protfdtfd void dfvidfFillRfdt(int x, int y, int widti, int ifigit,
                                  Color dolor) {
        /*
         * Trbnsform to dfvidf doordinbtfs
         */
        AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();

        /* difdk if rotbtfd or sifbrfd */
        int trbnsformTypf = dfvidfTrbnsform.gftTypf();
        boolfbn usfPbti =  ((trbnsformTypf &
                               (AffinfTrbnsform.TYPE_GENERAL_ROTATION |
                                AffinfTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);
        if (usfPbti) {
            fill(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
            rfturn;
        }

        Point2D.Flobt tld_pos = nfw Point2D.Flobt(x, y);
        dfvidfTrbnsform.trbnsform(tld_pos, tld_pos);

        Point2D.Flobt brd_pos = nfw Point2D.Flobt(x+widti, y+ifigit);
        dfvidfTrbnsform.trbnsform(brd_pos, brd_pos);

        flobt dfvidfWidti = (flobt) (brd_pos.gftX() - tld_pos.gftX());
        flobt dfvidfHfigit = (flobt)(brd_pos.gftY() - tld_pos.gftY());

        WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();
        wPrintfrJob.fillRfdt((flobt)tld_pos.gftX(), (flobt)tld_pos.gftY(),
                             dfvidfWidti, dfvidfHfigit, dolor);
    }


    /**
     * Drbw b linf using b pfn drfbtfd using tif spfdififd dolor
     * bnd durrfnt strokf propfrtifs.
     */
    @Ovfrridf
    protfdtfd void dfvidfDrbwLinf(int xBfgin, int yBfgin, int xEnd, int yEnd,
                                  Color dolor) {
        Strokf strokf = gftStrokf();

        if (strokf instbndfof BbsidStrokf) {
            BbsidStrokf linfStrokf = (BbsidStrokf) strokf;

            if (linfStrokf.gftDbsiArrby() != null) {
                drbw(nfw Linf2D.Flobt(xBfgin, yBfgin, xEnd, yEnd));
                rfturn;
            }

            flobt linfWidti = linfStrokf.gftLinfWidti();
            Point2D.Flobt pfnSizf = nfw Point2D.Flobt(linfWidti, linfWidti);

            AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();
            dfvidfTrbnsform.dfltbTrbnsform(pfnSizf, pfnSizf);

            flobt dfvidfLinfWidti = Mbti.min(Mbti.bbs(pfnSizf.x),
                                             Mbti.bbs(pfnSizf.y));

            Point2D.Flobt bfgin_pos = nfw Point2D.Flobt(xBfgin, yBfgin);
            dfvidfTrbnsform.trbnsform(bfgin_pos, bfgin_pos);

            Point2D.Flobt fnd_pos = nfw Point2D.Flobt(xEnd, yEnd);
            dfvidfTrbnsform.trbnsform(fnd_pos, fnd_pos);

            int fndCbp = linfStrokf.gftEndCbp();
            int linfJoin = linfStrokf.gftLinfJoin();

            /* difdk if it's b onf-pixfl linf */
            if ((fnd_pos.gftX() == bfgin_pos.gftX())
                && (fnd_pos.gftY() == bfgin_pos.gftY())) {

                /* fndCbp otifr tibn Round will not print!
                 * duf to Windows GDI limitbtion, fordf it to CAP_ROUND
                 */
                fndCbp = BbsidStrokf.CAP_ROUND;
            }


            WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();

            /* dbll nbtivf fundtion tibt drfbtfs pfn witi stylf */
            if (wPrintfrJob.sflfdtStylfPfn(fndCbp, linfJoin,
                                           dfvidfLinfWidti, dolor)) {
                wPrintfrJob.movfTo((flobt)bfgin_pos.gftX(),
                                   (flobt)bfgin_pos.gftY());
                wPrintfrJob.linfTo((flobt)fnd_pos.gftX(),
                                   (flobt)fnd_pos.gftY());
            }
            /* sflfdtStylfPfn is not supportfd, must bf Win 9X */
            flsf {

                /* lft's sff if wf dbn usf b b dffbult pfn
                 *  if it's round fnd (Windows' dffbult stylf)
                 *  or it's vfrtidbl/iorizontbl
                 *  or strokf is too tiin.
                 */
                doublf lowfrRfs = Mbti.min(wPrintfrJob.gftXRfs(),
                                           wPrintfrJob.gftYRfs());

                if ((fndCbp == BbsidStrokf.CAP_ROUND) ||
                 (((xBfgin == xEnd) || (yBfgin == yEnd)) &&
                 (dfvidfLinfWidti/lowfrRfs < MAX_THINLINE_INCHES))) {

                    wPrintfrJob.sflfdtPfn(dfvidfLinfWidti, dolor);
                    wPrintfrJob.movfTo((flobt)bfgin_pos.gftX(),
                                       (flobt)bfgin_pos.gftY());
                    wPrintfrJob.linfTo((flobt)fnd_pos.gftX(),
                                       (flobt)fnd_pos.gftY());
                }
                flsf {
                    drbw(nfw Linf2D.Flobt(xBfgin, yBfgin, xEnd, yEnd));
                }
            }
        }
    }


    /**
     * Givfn b Jbvb2D <dodf>PbtiItfrbtor</dodf> instbndf,
     * tiis mftiod trbnslbtfs tibt into b Window's pbti
     * in tif printfr dfvidf dontfxt.
     */
    privbtf void donvfrtToWPbti(PbtiItfrbtor pbtiItfr) {

        flobt[] sfgmfnt = nfw flobt[6];
        int sfgmfntTypf;

        WPrintfrJob wPrintfrJob = (WPrintfrJob) gftPrintfrJob();

        /* Mbp tif PbtiItfrbtor's fill rulf into tif Window's
         * polygon fill rulf.
         */
        int polyFillRulf;
        if (pbtiItfr.gftWindingRulf() == PbtiItfrbtor.WIND_EVEN_ODD) {
            polyFillRulf = WPrintfrJob.POLYFILL_ALTERNATE;
        } flsf {
            polyFillRulf = WPrintfrJob.POLYFILL_WINDING;
        }
        wPrintfrJob.sftPolyFillModf(polyFillRulf);

        wPrintfrJob.bfginPbti();

        wiilf (pbtiItfr.isDonf() == fblsf) {
            sfgmfntTypf = pbtiItfr.durrfntSfgmfnt(sfgmfnt);

            switdi (sfgmfntTypf) {
             dbsf PbtiItfrbtor.SEG_MOVETO:
                wPrintfrJob.movfTo(sfgmfnt[0], sfgmfnt[1]);
                brfbk;

             dbsf PbtiItfrbtor.SEG_LINETO:
                wPrintfrJob.linfTo(sfgmfnt[0], sfgmfnt[1]);
                brfbk;

            /* Convfrt tif qubd pbti to b bfzifr.
             */
             dbsf PbtiItfrbtor.SEG_QUADTO:
                int lbstX = wPrintfrJob.gftPfnX();
                int lbstY = wPrintfrJob.gftPfnY();
                flobt d1x = lbstX + (sfgmfnt[0] - lbstX) * 2 / 3;
                flobt d1y = lbstY + (sfgmfnt[1] - lbstY) * 2 / 3;
                flobt d2x = sfgmfnt[2] - (sfgmfnt[2] - sfgmfnt[0]) * 2/ 3;
                flobt d2y = sfgmfnt[3] - (sfgmfnt[3] - sfgmfnt[1]) * 2/ 3;
                wPrintfrJob.polyBfzifrTo(d1x, d1y,
                                         d2x, d2y,
                                         sfgmfnt[2], sfgmfnt[3]);
                brfbk;

             dbsf PbtiItfrbtor.SEG_CUBICTO:
                wPrintfrJob.polyBfzifrTo(sfgmfnt[0], sfgmfnt[1],
                                         sfgmfnt[2], sfgmfnt[3],
                                         sfgmfnt[4], sfgmfnt[5]);
                brfbk;

             dbsf PbtiItfrbtor.SEG_CLOSE:
                wPrintfrJob.dlosfFigurf();
                brfbk;
            }


            pbtiItfr.nfxt();
        }

        wPrintfrJob.fndPbti();

    }

}
