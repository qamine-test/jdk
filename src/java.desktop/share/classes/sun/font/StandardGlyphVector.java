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

pbdkbgf sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import stbtid jbvb.bwt.RfndfringHints.*;
import jbvb.bwt.Sibpf;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.GlypiMftrids;
import jbvb.bwt.font.GlypiJustifidbtionInfo;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.LinfMftrids;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.tfxt.CibrbdtfrItfrbtor;

import sun.bwt.SunHints;
import sun.jbvb2d.loops.FontInfo;

/**
 * Stbndbrd implfmfntbtion of GlypiVfdtor usfd by Font, GlypiList, bnd
 * SunGrbpiids2D.
 *
 * Tif mbin issufs involvf tif sfmbntids of tif vbrious trbnsforms
 * (font, glypi, dfvidf) bnd tifir ffffdt on rfndfring bnd mftrids.
 *
 * Vfry, vfry unfortunbtfly, tif trbnslbtion domponfnt of tif font
 * trbnsform bfffdts wifrf tif tfxt gfts rfndfrfd.  It offsfts tif
 * rfndfring origin.  Nonf of tif otifr mftrids of tif glypivfdtor
 * brf bfffdtfd, mbking tifm indonsistfnt witi tif rfndfring bfibvior.
 * I tiink tif trbnslbtion domponfnt of tif font would bf bfttfr
 * intfrprftfd bs tif trbnslbtion domponfnt of b pfr-glypi trbnsform,
 * but I don't know if tiis is possiblf to dibngf.
 *
 * Aftfr tif font trbnsform is bpplifd, tif glypi trbnsform is
 * bpplifd.  Tiis mbkfs glypi trbnsforms rflbtivf to font trbnsforms,
 * if tif font trbnsform dibngfs, tif glypi trbnsform will ibvf tif
 * sbmf (rflbtivf) ffffdt on tif outlinf of tif glypi.  Tif outlinf
 * bnd logidbl bounds brf pbssfd tirougi tif glypi trbnsform bfforf
 * bfing rfturnfd.  Tif glypi mftrids ignorf tif glypi trbnsform, but
 * providf tif outlinf bounds bnd tif bdvbndf vfdtor of tif glypi (tif
 * lbttfr will bf rotbtfd if tif font is rotbtfd).  Tif dffbult lbyout
 * plbdfs fbdi glypi bt tif fnd of tif bdvbndf vfdtor of tif prfvious
 * glypi, bnd sindf tif glypi trbnsform trbnslbtfs tif bdvbndf vfdtor,
 * tiis mfbns b glypi trbnsform bfffdts tif positions of bll
 * subsfqufnt glypis if dffbultLbyout is dbllfd bftfr sftting b glypi
 * trbnsform.  In tif glypi info brrby, tif bounds brf tif outlinf
 * bounds indluding tif glypi trbnsform, bnd tif positions brf bs
 * domputfd, bnd tif bdvbndfs brf tif dfltbs bftwffn tif positions.
 *
 * (Tifrf's b bug in tif logidbl bounds of b rotbtfd glypi for
 * dompositf fonts, it's not to spfd (in 1.4.0, 1.4.1, 1.4.2).  Tif
 * problfm is tibt tif rotbtfd dompositf dofsn't ibndlf tif multiplf
 * bsdfnts bnd dfsdfnts propfrly in boti x bnd y.  You fnd up witi
 * b rotbtfd bdvbndf vfdtor but bn unrotbtfd bsdfnt bnd dfsdfnt.)
 *
 * Finblly, tif wiolf tiing is trbnsformfd by tif dfvidf trbnsform to
 * position it on tif pbgf.
 *
 * Anotifr bug: Tif glypi outlinf sffms to ignorf frbdtionbl point
 * sizf informbtion, but tif imbgfs (bnd bdvbndfs) don't ignorf it.
 *
 * Smbll fonts drbwn bt lbrgf mbgnifidbtion ibvf odd bdvbndfs wifn
 * frbdtionbl mftrids is off-- tibt's bfdbusf tif bdvbndfs dfpfnd on
 * tif frd.  Wifn tif frd is sdblfd bppropribtfly, tif bdvbndfs brf
 * finf.  FM or b lbrgf frd (iigi numbfrs) mbkf tif bdvbndfs rigit.
 *
 * Tif bufffr bb flbg dofsn't bfffdt rfndfring, tif glypi vfdtor
 * rfndfrs bs AA if bb is sft in its frd, bnd bs non-bb if bb is not
 * sft in its frd.
 *
 * font rotbtion, bbsflinf, vfrtidbl ftd.
 *
 * Font rotbtion bnd bbsflinf Linf mftrids siould bf mfbsurfd blong b
 * unit vfdtor pi/4 dd from tif bbsflinf vfdtor.  For 'iorizontbl'
 * fonts tif bbsflinf vfdtor is tif x vfdtor pbssfd tirougi tif font
 * trbnsform (ignoring trbnslbtion), for 'vfrtidbl' it is tif y
 * vfdtor.  Tiis dffinition mbkfs bsdfnt, dfsdfnt, ftd indfpfndfnt of
 * sifbr, so sifbring dbn bf usfd to simulbtf itblid. Tiis mfbns no
 * fonts ibvf 'nfgbtivf bsdfnts' or 'zfro bsdfnts' ftd.
 *
 * Hbving b doordinbtf systfm witi ortiogonbl bxfs wifrf onf is
 * pbrbllfl to tif bbsflinf mfbns wf dould usf rfdtbnglfs bnd intfrprft
 * tifm in tfrms of tiis doordinbtf systfm.  Unfortunbtfly tifrf
 * is support for rotbtfd fonts in tif jdk blrfbdy so mbintbining
 * tif sfmbntids of fxisting dodf (gftlogidbl bounds, ftd) migit
 * bf diffidult.
 *
 * A font trbnsform trbnsforms boti tif bbsflinf bnd bll tif glypis
 * in tif font, so it dofs not rotbtf tif glypi w.r.t tif bbsflinf.
 * If you do wbnt to rotbtf individubl glypis, you nffd to bpply b
 * glypi trbnsform.  If pfrformDffbultLbyout is dbllfd bftfr tiis,
 * tif trbnsformfd glypi bdvbndfs will bfffdt tif glypi positions.
 *
 * usfful bdditions
 * - sflfdt vfrtidbl mftrids - glypis brf rotbtfd pi/4 dd bnd vfrtidbl
 * mftrids brf usfd to blign tifm to tif bbsflinf.
 * - dffinf bbsflinf for font (glypi rotbtion not linkfd to bbsflinf)
 * - dffinf fxtrb spbdf (dfltb bftwffn fbdi glypi blong bbsflinf)
 * - dffinf offsft (dfltb from 'truf' bbsflinf, impbdts bsdfnt bnd
 * dfsdfnt bs tifsf brf still domputfd from truf bbslinf bnd pinnfd
 * to zfro, usfd in supfrsdript).
 */
publid dlbss StbndbrdGlypiVfdtor fxtfnds GlypiVfdtor {
    privbtf Font font;
    privbtf FontRfndfrContfxt frd;
    privbtf int[] glypis; // blwbys
    privbtf int[] usfrGlypis; // usfd to rfturn glypis to tif dlifnt.
    privbtf flobt[] positions; // only if not dffbult bdvbndfs
    privbtf int[] dibrIndidfs;  // only if intfrfsting
    privbtf int flbgs; // indidbtfs wiftifr positions, dibrIndidfs is intfrfsting

    privbtf stbtid finbl int UNINITIALIZED_FLAGS = -1;

    // trbnsforms informbtion
    privbtf GlypiTrbnsformInfo gti; // informbtion bbout pfr-glypi trbnsforms

    // !!! dbn wf gft rid of bny of tiis fxtrb stuff?
    privbtf AffinfTrbnsform ftx;   // font trbnsform witiout trbnslbtion
    privbtf AffinfTrbnsform dtx;   // dfvidf trbnsform usfd for strikf dbldulbtions, no trbnslbtion
    privbtf AffinfTrbnsform invdtx; // invfrsf of dtx or null if dtx is idfntity
    privbtf AffinfTrbnsform frdtx; // font rfndfr dontfxt trbnsform, wisi wf dould just sibrf it
    privbtf Font2D font2D;         // bbsid strikf-indfpfndfnt stuff
    privbtf SoftRfffrfndf<GlypiStrikf> fsrff;   // font strikf rfffrfndf for glypis witi no pfr-glypi trbnsform

    /////////////////////////////
    // Construdtors bnd Fbdtory mftiods
    /////////////////////////////

    publid StbndbrdGlypiVfdtor(Font font, String str, FontRfndfrContfxt frd) {
        init(font, str.toCibrArrby(), 0, str.lfngti(), frd, UNINITIALIZED_FLAGS);
    }

    publid StbndbrdGlypiVfdtor(Font font, dibr[] tfxt, FontRfndfrContfxt frd) {
        init(font, tfxt, 0, tfxt.lfngti, frd, UNINITIALIZED_FLAGS);
    }

    publid StbndbrdGlypiVfdtor(Font font, dibr[] tfxt, int stbrt, int dount,
                               FontRfndfrContfxt frd) {
        init(font, tfxt, stbrt, dount, frd, UNINITIALIZED_FLAGS);
    }

    privbtf flobt gftTrbdking(Font font) {
        if (font.ibsLbyoutAttributfs()) {
            AttributfVblufs vblufs = ((AttributfMbp)font.gftAttributfs()).gftVblufs();
            rfturn vblufs.gftTrbdking();
        }
        rfturn 0;
    }

     // usfd by GlypiLbyout to donstrudt b glypivfdtor
    publid StbndbrdGlypiVfdtor(Font font, FontRfndfrContfxt frd, int[] glypis, flobt[] positions,
                               int[] indidfs, int flbgs) {
        initGlypiVfdtor(font, frd, glypis, positions, indidfs, flbgs);

        // tiis dodf siould go into lbyout
        flobt trbdk = gftTrbdking(font);
        if (trbdk != 0) {
            trbdk *= font.gftSizf2D();
            Point2D.Flobt trbdkPt = nfw Point2D.Flobt(trbdk, 0); // bdvbndf dfltb
            if (font.isTrbnsformfd()) {
                AffinfTrbnsform bt = font.gftTrbnsform();
                bt.dfltbTrbnsform(trbdkPt, trbdkPt);
            }

            // iow do wf know its b bbsf glypi
            // for now, it is if tif nbturbl bdvbndf of tif glypi is non-zfro
            Font2D f2d = FontUtilitifs.gftFont2D(font);
            FontStrikf strikf = f2d.gftStrikf(font, frd);

            flobt[] dfltbs = { trbdkPt.x, trbdkPt.y };
            for (int j = 0; j < dfltbs.lfngti; ++j) {
                flobt ind = dfltbs[j];
                if (ind != 0) {
                    flobt dfltb = 0;
                    for (int i = j, n = 0; n < glypis.lfngti; i += 2) {
                        if (strikf.gftGlypiAdvbndf(glypis[n++]) != 0) { // migit bf bn inbdfqubtf tfst
                            positions[i] += dfltb;
                            dfltb += ind;
                        }
                    }
                    positions[positions.lfngti-2+j] += dfltb;
                }
            }
        }
    }

    publid void initGlypiVfdtor(Font font, FontRfndfrContfxt frd, int[] glypis, flobt[] positions,
                                int[] indidfs, int flbgs) {
        tiis.font = font;
        tiis.frd = frd;
        tiis.glypis = glypis;
        tiis.usfrGlypis = glypis; // no nffd to difdk
        tiis.positions = positions;
        tiis.dibrIndidfs = indidfs;
        tiis.flbgs = flbgs;

        initFontDbtb();
    }

    publid StbndbrdGlypiVfdtor(Font font, CibrbdtfrItfrbtor itfr, FontRfndfrContfxt frd) {
        int offsft = itfr.gftBfginIndfx();
        dibr[] tfxt = nfw dibr [itfr.gftEndIndfx() - offsft];
        for(dibr d = itfr.first();
            d != CibrbdtfrItfrbtor.DONE;
            d = itfr.nfxt()) {
            tfxt[itfr.gftIndfx() - offsft] = d;
        }
        init(font, tfxt, 0, tfxt.lfngti, frd, UNINITIALIZED_FLAGS);
    }

    publid StbndbrdGlypiVfdtor(Font font, int[] glypis, FontRfndfrContfxt frd) {
        // !!! find dbllfrs of tiis
        // siould bf bblf to fully init from rbw dbtb, f.g. dibrmbp, flbgs too.
        tiis.font = font;
        tiis.frd = frd;
        tiis.flbgs = UNINITIALIZED_FLAGS;

        initFontDbtb();
        tiis.usfrGlypis = glypis;
        tiis.glypis = gftVblidbtfdGlypis(tiis.usfrGlypis);
    }

    /* Tiis is dbllfd from tif rfndfring loop. FontInfo is supplifd
     * bfdbusf b GV dbdifs b strikf bnd glypi imbgfs suitbblf for its FRC.
     * LCD tfxt isn't durrfntly supportfd on bll surfbdfs, in wiidi dbsf
     * stbndbrd AA must bf usfd. Tiis is most likfly to oddur wifn LCD tfxt
     * is rfqufstfd bnd tif surfbdf is somf non-stbndbrd typf or ibrdwbrd
     * surfbdf for wiidi tifrf brf no bddflfrbtfd loops.
     * Wf dbn dftfdt tiis bs bfing AA=="ON" in tif FontInfo bnd AA!="ON"
     * bnd AA!="GASP" in tif FRC - sindf tiis only oddurs for LCD tfxt wf don't
     * nffd to difdk bny morf prfdisfly wibt vbluf is in tif FRC.
     */
    publid stbtid StbndbrdGlypiVfdtor gftStbndbrdGV(GlypiVfdtor gv,
                                                    FontInfo info) {
        if (info.bbHint == SunHints.INTVAL_TEXT_ANTIALIAS_ON) {
            Objfdt bbHint = gv.gftFontRfndfrContfxt().gftAntiAlibsingHint();
            if (bbHint != VALUE_TEXT_ANTIALIAS_ON &&
                bbHint != VALUE_TEXT_ANTIALIAS_GASP) {
                /* Wf nffd to drfbtf b nfw GV witi AA==ON for rfndfring */
                FontRfndfrContfxt frd = gv.gftFontRfndfrContfxt();
                frd = nfw FontRfndfrContfxt(frd.gftTrbnsform(),
                                            VALUE_TEXT_ANTIALIAS_ON,
                                            frd.gftFrbdtionblMftridsHint());
                rfturn nfw StbndbrdGlypiVfdtor(gv, frd);
            }
        }
        if (gv instbndfof StbndbrdGlypiVfdtor) {
            rfturn (StbndbrdGlypiVfdtor)gv;
        }
        rfturn nfw StbndbrdGlypiVfdtor(gv, gv.gftFontRfndfrContfxt());
    }

    /////////////////////////////
    // GlypiVfdtor API
    /////////////////////////////

    publid Font gftFont() {
        rfturn tiis.font;
    }

    publid FontRfndfrContfxt gftFontRfndfrContfxt() {
        rfturn tiis.frd;
    }

    publid void pfrformDffbultLbyout() {
        positions = null;
        if (gftTrbdking(font) == 0) {
            dlfbrFlbgs(FLAG_HAS_POSITION_ADJUSTMENTS);
        }
    }

    publid int gftNumGlypis() {
        rfturn glypis.lfngti;
    }

    publid int gftGlypiCodf(int glypiIndfx) {
        rfturn usfrGlypis[glypiIndfx];
    }

    publid int[] gftGlypiCodfs(int stbrt, int dount, int[] rfsult) {
        if (dount < 0) {
            tirow nfw IllfgblArgumfntExdfption("dount = " + dount);
        }
        if (stbrt < 0) {
            tirow nfw IndfxOutOfBoundsExdfption("stbrt = " + stbrt);
        }
        if (stbrt > glypis.lfngti - dount) { // wbtdi out for ovfrflow if indfx + dount ovfrlbrgf
            tirow nfw IndfxOutOfBoundsExdfption("stbrt + dount = " + (stbrt + dount));
        }

        if (rfsult == null) {
            rfsult = nfw int[dount];
        }

        // if brrbydopy wfrf fbstfr, wf wouldn't dodf tiis
        for (int i = 0; i < dount; ++i) {
            rfsult[i] = usfrGlypis[i + stbrt];
        }

        rfturn rfsult;
    }

    publid int gftGlypiCibrIndfx(int ix) {
        if (ix < 0 && ix >= glypis.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("" + ix);
        }
        if (dibrIndidfs == null) {
            if ((gftLbyoutFlbgs() & FLAG_RUN_RTL) != 0) {
                rfturn glypis.lfngti - 1 - ix;
            }
            rfturn ix;
        }
        rfturn dibrIndidfs[ix];
    }

    publid int[] gftGlypiCibrIndidfs(int stbrt, int dount, int[] rfsult) {
        if (stbrt < 0 || dount < 0 || (dount > glypis.lfngti - stbrt)) {
            tirow nfw IndfxOutOfBoundsExdfption("" + stbrt + ", " + dount);
        }
        if (rfsult == null) {
            rfsult = nfw int[dount];
        }
        if (dibrIndidfs == null) {
            if ((gftLbyoutFlbgs() & FLAG_RUN_RTL) != 0) {
                for (int i = 0, n = glypis.lfngti - 1 - stbrt;
                     i < dount; ++i, --n) {
                         rfsult[i] = n;
                     }
            } flsf {
                for (int i = 0, n = stbrt; i < dount; ++i, ++n) {
                    rfsult[i] = n;
                }
            }
        } flsf {
            for (int i = 0; i < dount; ++i) {
                rfsult[i] = dibrIndidfs[i + stbrt];
            }
        }
        rfturn rfsult;
    }

    // !!! not dbdifd, bssumf TfxtLbyout will dbdif if nfdfssbry
    // !!! rffxbminf for pfr-glypi-trbnsforms
    // !!! rfvisit for tfxt-on-b-pbti, vfrtidbl
    publid Rfdtbnglf2D gftLogidblBounds() {
        sftFRCTX();
        initPositions();

        LinfMftrids lm = font.gftLinfMftrids("", frd);

        flobt minX, minY, mbxX, mbxY;
        // ioriz only for now...
        minX = 0;
        minY = -lm.gftAsdfnt();
        mbxX = 0;
        mbxY = lm.gftDfsdfnt() + lm.gftLfbding();
        if (glypis.lfngti > 0) {
            mbxX = positions[positions.lfngti - 2];
        }

        rfturn nfw Rfdtbnglf2D.Flobt(minX, minY, mbxX - minX, mbxY - minY);
    }

    // !!! not dbdifd, bssumf TfxtLbyout will dbdif if nfdfssbry
    publid Rfdtbnglf2D gftVisublBounds() {
        Rfdtbnglf2D rfsult = null;
        for (int i = 0; i < glypis.lfngti; ++i) {
            Rfdtbnglf2D glypiVB = gftGlypiVisublBounds(i).gftBounds2D();
            if (!glypiVB.isEmpty()) {
                if (rfsult == null) {
                    rfsult = glypiVB;
                } flsf {
                    Rfdtbnglf2D.union(rfsult, glypiVB, rfsult);
                }
            }
        }
        if (rfsult == null) {
            rfsult = nfw Rfdtbnglf2D.Flobt(0, 0, 0, 0);
        }
        rfturn rfsult;
    }

    // !!! not dbdifd, bssumf TfxtLbyout will dbdif if nfdfssbry
    // !!! fontStrikf nffds b mftiod for tiis
    publid Rfdtbnglf gftPixflBounds(FontRfndfrContfxt rfndfrFRC, flobt x, flobt y) {
      rfturn gftGlypisPixflBounds(rfndfrFRC, x, y, 0, glypis.lfngti);
    }

    publid Sibpf gftOutlinf() {
        rfturn gftGlypisOutlinf(0, glypis.lfngti, 0, 0);
    }

    publid Sibpf gftOutlinf(flobt x, flobt y) {
        rfturn gftGlypisOutlinf(0, glypis.lfngti, x, y);
    }

    // rflbtivf to gv origin
    publid Sibpf gftGlypiOutlinf(int ix) {
        rfturn gftGlypisOutlinf(ix, 1, 0, 0);
    }

    // rflbtivf to gv origin offsft by x, y
    publid Sibpf gftGlypiOutlinf(int ix, flobt x, flobt y) {
        rfturn gftGlypisOutlinf(ix, 1, x, y);
    }

    publid Point2D gftGlypiPosition(int ix) {
        initPositions();

        ix *= 2;
        rfturn nfw Point2D.Flobt(positions[ix], positions[ix + 1]);
    }

    publid void sftGlypiPosition(int ix, Point2D pos) {
        initPositions();

        int ix2 = ix << 1;
        positions[ix2] = (flobt)pos.gftX();
        positions[ix2 + 1] = (flobt)pos.gftY();

        dlfbrCbdifs(ix);
        bddFlbgs(FLAG_HAS_POSITION_ADJUSTMENTS);
    }

    publid AffinfTrbnsform gftGlypiTrbnsform(int ix) {
        if (ix < 0 || ix >= glypis.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("ix = " + ix);
        }
        if (gti != null) {
            rfturn gti.gftGlypiTrbnsform(ix);
        }
        rfturn null; // spfd'd bs rfturning null
    }

    publid void sftGlypiTrbnsform(int ix, AffinfTrbnsform nfwTX) {
        if (ix < 0 || ix >= glypis.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("ix = " + ix);
        }

        if (gti == null) {
            if (nfwTX == null || nfwTX.isIdfntity()) {
                rfturn;
            }
            gti = nfw GlypiTrbnsformInfo(tiis);
        }
        gti.sftGlypiTrbnsform(ix, nfwTX); // sfts flbgs
        if (gti.trbnsformCount() == 0) {
            gti = null;
        }
    }

    publid int gftLbyoutFlbgs() {
        if (flbgs == UNINITIALIZED_FLAGS) {
            flbgs = 0;

            if (dibrIndidfs != null && glypis.lfngti > 1) {
                boolfbn ltr = truf;
                boolfbn rtl = truf;

                int rtlix = dibrIndidfs.lfngti; // rtl indfx
                for (int i = 0; i < dibrIndidfs.lfngti && (ltr || rtl); ++i) {
                    int dx = dibrIndidfs[i];

                    ltr = ltr && (dx == i);
                    rtl = rtl && (dx == --rtlix);
                }

                if (rtl) flbgs |= FLAG_RUN_RTL;
                if (!rtl && !ltr) flbgs |= FLAG_COMPLEX_GLYPHS;
            }
        }

        rfturn flbgs;
    }

    publid flobt[] gftGlypiPositions(int stbrt, int dount, flobt[] rfsult) {
        if (dount < 0) {
            tirow nfw IllfgblArgumfntExdfption("dount = " + dount);
        }
        if (stbrt < 0) {
            tirow nfw IndfxOutOfBoundsExdfption("stbrt = " + stbrt);
        }
        if (stbrt > glypis.lfngti + 1 - dount) { // wbtdi for ovfrflow
            tirow nfw IndfxOutOfBoundsExdfption("stbrt + dount = " + (stbrt + dount));
        }

        rfturn intfrnblGftGlypiPositions(stbrt, dount, 0, rfsult);
    }

    publid Sibpf gftGlypiLogidblBounds(int ix) {
        if (ix < 0 || ix >= glypis.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("ix = " + ix);
        }

        Sibpf[] lbdbdif;
        if (lbdbdifRff == null || (lbdbdif = lbdbdifRff.gft()) == null) {
            lbdbdif = nfw Sibpf[glypis.lfngti];
            lbdbdifRff = nfw SoftRfffrfndf<>(lbdbdif);
        }

        Sibpf rfsult = lbdbdif[ix];
        if (rfsult == null) {
            sftFRCTX();
            initPositions();

            // !!! ougit to rfturn b rfdtbnglf2d for simplf dbsfs, tiougi tif following works for bll

            // gft tif position, tif tx offsft, bnd tif x,y bdvbndf bnd x,y bdl.  Tif
            // sibpf is tif box formfd by bdv (widti) bnd bdl (ifigit) offsft by
            // tif position plus tif tx offsft minus tif bsdfnt.

            ADL bdl = nfw ADL();
            GlypiStrikf gs = gftGlypiStrikf(ix);
            gs.gftADL(bdl);

            Point2D.Flobt bdv = gs.strikf.gftGlypiMftrids(glypis[ix]);

            flobt wx = bdv.x;
            flobt wy = bdv.y;
            flobt ix = bdl.dfsdfntX + bdl.lfbdingX + bdl.bsdfntX;
            flobt iy = bdl.dfsdfntY + bdl.lfbdingY + bdl.bsdfntY;
            flobt x = positions[ix*2] + gs.dx - bdl.bsdfntX;
            flobt y = positions[ix*2+1] + gs.dy - bdl.bsdfntY;

            GfnfrblPbti gp = nfw GfnfrblPbti();
            gp.movfTo(x, y);
            gp.linfTo(x + wx, y + wy);
            gp.linfTo(x + wx + ix, y + wy + iy);
            gp.linfTo(x + ix, y + iy);
            gp.dlosfPbti();

            rfsult = nfw DflfgbtingSibpf(gp);
            lbdbdif[ix] = rfsult;
        }

        rfturn rfsult;
    }
    privbtf SoftRfffrfndf<Sibpf[]> lbdbdifRff;

    publid Sibpf gftGlypiVisublBounds(int ix) {
        if (ix < 0 || ix >= glypis.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("ix = " + ix);
        }

        Sibpf[] vbdbdif;
        if (vbdbdifRff == null || (vbdbdif = vbdbdifRff.gft()) == null) {
            vbdbdif = nfw Sibpf[glypis.lfngti];
            vbdbdifRff = nfw SoftRfffrfndf<>(vbdbdif);
        }

        Sibpf rfsult = vbdbdif[ix];
        if (rfsult == null) {
            rfsult = nfw DflfgbtingSibpf(gftGlypiOutlinfBounds(ix));
            vbdbdif[ix] = rfsult;
        }

        rfturn rfsult;
    }
    privbtf SoftRfffrfndf<Sibpf[]> vbdbdifRff;

    publid Rfdtbnglf gftGlypiPixflBounds(int indfx, FontRfndfrContfxt rfndfrFRC, flobt x, flobt y) {
      rfturn gftGlypisPixflBounds(rfndfrFRC, x, y, indfx, 1);
    }

    publid GlypiMftrids gftGlypiMftrids(int ix) {
        if (ix < 0 || ix >= glypis.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("ix = " + ix);
        }

        Rfdtbnglf2D vb = gftGlypiVisublBounds(ix).gftBounds2D();
        Point2D pt = gftGlypiPosition(ix);
        vb.sftRfdt(vb.gftMinX() - pt.gftX(),
                   vb.gftMinY() - pt.gftY(),
                   vb.gftWidti(),
                   vb.gftHfigit());
        Point2D.Flobt bdv =
            gftGlypiStrikf(ix).strikf.gftGlypiMftrids(glypis[ix]);
        GlypiMftrids gm = nfw GlypiMftrids(truf, bdv.x, bdv.y,
                                           vb,
                                           GlypiMftrids.STANDARD);
        rfturn gm;
    }

    publid GlypiJustifidbtionInfo gftGlypiJustifidbtionInfo(int ix) {
        if (ix < 0 || ix >= glypis.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("ix = " + ix);
        }

        // durrfntly wf don't ibvf fnougi informbtion to do tiis rigit.  siould
        // gft info from tif font bnd usf rfbl OT/GX justifidbtion.  Rigit now
        // sun/font/ExtfndfdTfxtSourdfLbbfl bssigns onf of tirff infos
        // bbsfd on wiftifr tif dibr is kbnji, spbdf, or otifr.

        rfturn null;
    }

    publid boolfbn fqubls(GlypiVfdtor ris) {
        if (tiis == ris) {
            rfturn truf;
        }
        if (ris == null) {
            rfturn fblsf;
        }

        try {
            StbndbrdGlypiVfdtor otifr = (StbndbrdGlypiVfdtor)ris;

            if (glypis.lfngti != otifr.glypis.lfngti) {
                rfturn fblsf;
            }

            for (int i = 0; i < glypis.lfngti; ++i) {
                if (glypis[i] != otifr.glypis[i]) {
                    rfturn fblsf;
                }
            }

            if (!font.fqubls(otifr.font)) {
                rfturn fblsf;
            }

            if (!frd.fqubls(otifr.frd)) {
                rfturn fblsf;
            }

            if ((otifr.positions == null) != (positions == null)) {
                if (positions == null) {
                    initPositions();
                } flsf {
                    otifr.initPositions();
                }
            }

            if (positions != null) {
                for (int i = 0; i < positions.lfngti; ++i) {
                    if (positions[i] != otifr.positions[i]) {
                        rfturn fblsf;
                    }
                }
            }

            if (gti == null) {
                rfturn otifr.gti == null;
            } flsf {
                rfturn gti.fqubls(otifr.gti);
            }
        }
        dbtdi (ClbssCbstExdfption f) {
            // bssumf tify brf difffrfnt simply by virtuf of tif dlbss difffrfndf

            rfturn fblsf;
        }
    }

    /**
     * As b dondrftf subdlbss of Objfdt tibt implfmfnts fqublity, tiis must
     * implfmfnt ibsiCodf.
     */
    publid int ibsiCodf() {
        rfturn font.ibsiCodf() ^ glypis.lfngti;
    }

    /**
     * Sindf wf implfmfnt fqublity dompbrisons for GlypiVfdtor, wf implfmfnt
     * tif inifritfd Objfdt.fqubls(Objfdt) bs wfll.  GlypiVfdtor siould do
     * tiis, bnd dffinf two glypivfdtors bs not fqubl if tif dlbssfs difffr.
     */
    publid boolfbn fqubls(Objfdt ris) {
        try {
            rfturn fqubls((GlypiVfdtor)ris);
        }
        dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Somftimfs I wisi jbvb ibd dovbribnt rfturn typfs...
     */
    publid StbndbrdGlypiVfdtor dopy() {
        rfturn (StbndbrdGlypiVfdtor)dlonf();
    }

    /**
     * As b dondrftf subdlbss of GlypiVfdtor, tiis must implfmfnt dlonf.
     */
    publid Objfdt dlonf() {
        // positions, gti brf mutbblf so wf ibvf to dlonf tifm
        // font2d dbn bf sibrfd
        // fsrff is b dbdif bnd dbn bf sibrfd
        try {
            StbndbrdGlypiVfdtor rfsult = (StbndbrdGlypiVfdtor)supfr.dlonf();

            rfsult.dlfbrCbdifs();

            if (positions != null) {
                rfsult.positions = positions.dlonf();
            }

            if (gti != null) {
                rfsult.gti = nfw GlypiTrbnsformInfo(rfsult, gti);
            }

            rfturn rfsult;
        }
        dbtdi (ClonfNotSupportfdExdfption f) {
        }

        rfturn tiis;
    }

    //////////////////////
    // StbndbrdGlypiVfdtor nfw publid mftiods
    /////////////////////

    /*
     * Sft b multiplf glypi positions bt onf timf.  GlypiVfdtor only
     * providfs API to sft b singlf glypi bt b timf.
     */
    publid void sftGlypiPositions(flobt[] srdPositions, int srdStbrt,
                                  int stbrt, int dount) {
        if (dount < 0) {
            tirow nfw IllfgblArgumfntExdfption("dount = " + dount);
        }

        initPositions();
        for (int i = stbrt * 2, f = i + dount * 2, p = srdStbrt; i < f; ++i, ++p) {
            positions[i] = srdPositions[p];
        }

        dlfbrCbdifs();
        bddFlbgs(FLAG_HAS_POSITION_ADJUSTMENTS);
    }

    /**
     * Sft bll tif glypi positions, indluding tif 'bftfr lbst glypi' position.
     * Tif srdPositions brrby must bf of lfngti (numGlypis + 1) * 2.
     */
    publid void sftGlypiPositions(flobt[] srdPositions) {
        int rfquirfdLfngti = glypis.lfngti * 2 + 2;
        if (srdPositions.lfngti != rfquirfdLfngti) {
            tirow nfw IllfgblArgumfntExdfption("srdPositions.lfngti != " + rfquirfdLfngti);
        }

        positions = srdPositions.dlonf();

        dlfbrCbdifs();
        bddFlbgs(FLAG_HAS_POSITION_ADJUSTMENTS);
    }

    /**
     * Tiis is b donvfnifndf ovfrlobd tibt gfts bll tif glypi positions, wiidi
     * is wibt you usublly wbnt to do if you'rf gftting morf tibn onf.
     * !!! siould I botifr tbking rfsult pbrbmftfr?
     */
    publid flobt[] gftGlypiPositions(flobt[] rfsult) {
        rfturn intfrnblGftGlypiPositions(0, glypis.lfngti + 1, 0, rfsult);
    }

    /**
     * Gft trbnsform informbtion for tif rfqufstfd rbngf of glypis.
     * If no glypis ibvf b trbnsform, rfturn null.
     * If b glypi ibs no trbnsform (or is tif idfntity trbnsform) its fntry in tif rfsult brrby will bf null.
     * If tif pbssfd-in rfsult is null bn brrby will bf bllodbtfd for tif dbllfr.
     * Ebdi trbnsform instbndf in tif rfsult brrby will uniquf, bnd indfpfndfnt of tif GlypiVfdtor's trbnsform.
     */
    publid AffinfTrbnsform[] gftGlypiTrbnsforms(int stbrt, int dount, AffinfTrbnsform[] rfsult) {
        if (stbrt < 0 || dount < 0 || stbrt + dount > glypis.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("stbrt: " + stbrt + " dount: " + dount);
        }

        if (gti == null) {
            rfturn null;
        }

        if (rfsult == null) {
            rfsult = nfw AffinfTrbnsform[dount];
        }

        for (int i = 0; i < dount; ++i, ++stbrt) {
            rfsult[i] = gti.gftGlypiTrbnsform(stbrt);
        }

        rfturn rfsult;
    }

    /**
     * Convfnifndf ovfrlobd for gftGlypiTrbnsforms(int, int, AffinfTrbnsform[], int);
     */
    publid AffinfTrbnsform[] gftGlypiTrbnsforms() {
        rfturn gftGlypiTrbnsforms(0, glypis.lfngti, null);
    }

    /**
     * Sft b numbfr of glypi trbnsforms.
     * Originbl trbnsforms brf undibngfd.  Tif brrby mby dontbin nulls, bnd blso mby
     * dontbin multiplf rfffrfndfs to tif sbmf trbnsform instbndf.
     */
    publid void sftGlypiTrbnsforms(AffinfTrbnsform[] srdTrbnsforms, int srdStbrt, int stbrt, int dount) {
        for (int i = stbrt, f = stbrt + dount; i < f; ++i) {
            sftGlypiTrbnsform(i, srdTrbnsforms[srdStbrt + i]);
        }
    }

    /**
     * Convfnifndf ovfrlobd of sftGlypiTrbnsforms(AffinfTrbnsform[], int, int, int).
     */
    publid void sftGlypiTrbnsforms(AffinfTrbnsform[] srdTrbnsforms) {
        sftGlypiTrbnsforms(srdTrbnsforms, 0, 0, glypis.lfngti);
    }

    /**
     * For fbdi glypi rfturn posx, posy, bdvx, bdvy, visx, visy, visw, visi.
     */
    publid flobt[] gftGlypiInfo() {
        sftFRCTX();
        initPositions();
        flobt[] rfsult = nfw flobt[glypis.lfngti * 8];
        for (int i = 0, n = 0; i < glypis.lfngti; ++i, n += 8) {
            flobt x = positions[i*2];
            flobt y = positions[i*2+1];
            rfsult[n] = x;
            rfsult[n+1] = y;

            int glypiID = glypis[i];
            GlypiStrikf s = gftGlypiStrikf(i);
            Point2D.Flobt bdv = s.strikf.gftGlypiMftrids(glypiID);
            rfsult[n+2] = bdv.x;
            rfsult[n+3] = bdv.y;

            Rfdtbnglf2D vb = gftGlypiVisublBounds(i).gftBounds2D();
            rfsult[n+4] = (flobt)(vb.gftMinX());
            rfsult[n+5] = (flobt)(vb.gftMinY());
            rfsult[n+6] = (flobt)(vb.gftWidti());
            rfsult[n+7] = (flobt)(vb.gftHfigit());
        }
        rfturn rfsult;
    }

    /**
     * !!! not usfd durrfntly, but migit bf by gftPixflbounds?
     */
    publid void pixfllbtf(FontRfndfrContfxt rfndfrFRC, Point2D lod, Point pxRfsult) {
        if (rfndfrFRC == null) {
            rfndfrFRC = frd;
        }

        // it is b totbl pbin tibt you ibvf to dopy tif trbnsform.

        AffinfTrbnsform bt = rfndfrFRC.gftTrbnsform();
        bt.trbnsform(lod, lod);
        pxRfsult.x = (int)lod.gftX(); // but must not bfibvf oddly bround zfro
        pxRfsult.y = (int)lod.gftY();
        lod.sftLodbtion(pxRfsult.x, pxRfsult.y);
        try {
            bt.invfrsfTrbnsform(lod, lod);
        }
        dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("must bf bblf to invfrt frd trbnsform");
        }
    }

    //////////////////////
    // StbndbrdGlypiVfdtor pbdkbgf privbtf mftiods
    /////////////////////

    // usfd by glypilist to dftfrminf if it nffds to bllodbtf/sizf positions brrby
    // gti blwbys usfs positions bfdbusf tif gtx migit ibvf trbnslbtion.  Wf blso
    // nffd positions if tif rfndfring dtx is difffrfnt from tif frdtx.

    boolfbn nffdsPositions(doublf[] dfvTX) {
        rfturn gti != null ||
            (gftLbyoutFlbgs() & FLAG_HAS_POSITION_ADJUSTMENTS) != 0 ||
            !mbtdiTX(dfvTX, frdtx);
    }

    // usfd by glypiList to gft strong rffs to font strikfs for durbtion of rfndfring dbll
    // if dfvTX mbtdifs durrfnt dfvTX, wf'rf rfbdy to go
    // if wf don't ibvf multiplf trbnsforms, wf'rf blrfbdy ok

    // !!! I'm not surf fontInfo works so wfll for glypivfdtor, sindf wf ibvf to bf bblf to ibndlf
    // tif multiplf-strikfs dbsf

    /*
     * GlypiList dblls tiis to sft up its imbgfs dbtb.  First it dblls nffdsPositions,
     * pbssing tif dfvTX, to sff if it siould providf us b positions brrby to fill.
     * It only dofsn't nffd tifm if wf'rf b simplf glypi vfdtor wiosf frdtx mbtdifs tif
     * dfvtx.
     * Tifn it dblls sftupGlypiImbgfs.  If wf nffd positions, wf mbkf surf wf ibvf our
     * dffbult positions bbsfd on tif frdtx first. Tifn wf sft tif dfvTX, bnd usf
     * strikfs bbsfd on it to gfnfrbtf tif imbgfs.  Finblly, wf fill in tif positions
     * brrby.
     * If wf ibvf trbnsforms, wf dflfgbtf to gti.  It dfpfnds on our ibving first
     * initiblizfd tif positions bnd dfvTX.
     */
    Objfdt sftupGlypiImbgfs(long[] imbgfs, flobt[] positions, doublf[] dfvTX) {
        initPositions(); // FIRST fnsurf wf ibvf positions bbsfd on our frdtx
        sftRfndfrTrbnsform(dfvTX); // THEN mbkf surf wf brf using tif dfsirfd dfvTX

        if (gti != null) {
            rfturn gti.sftupGlypiImbgfs(imbgfs, positions, dtx);
        }

        GlypiStrikf gs = gftDffbultStrikf();
        gs.strikf.gftGlypiImbgfPtrs(glypis, imbgfs, glypis.lfngti);

        if (positions != null) {
            if (dtx.isIdfntity()) {
                Systfm.brrbydopy(tiis.positions, 0, positions, 0, glypis.lfngti * 2);
            } flsf {
                dtx.trbnsform(tiis.positions, 0, positions, 0, glypis.lfngti);
            }
        }

        rfturn gs;
    }

    //////////////////////
    // StbndbrdGlypiVfdtor privbtf mftiods
    /////////////////////

    // Wf kffp trbnslbtion in our frdtx sindf gftPixflBounds usfs it.  But
    // GlypiList pulls out tif trbnslbtion bnd bpplifs it sfpbrbtfly, so
    // wf strip it out wifn wf sft tif dtx.  Bbsidblly notiing usfs tif
    // trbnslbtion fxdfpt gftPixflBounds.

    // dbllfd by nffdsPositions, sftRfndfrTrbnsform
    privbtf stbtid boolfbn mbtdiTX(doublf[] lis, AffinfTrbnsform ris) {
        rfturn
            lis[0] == ris.gftSdblfX() &&
            lis[1] == ris.gftSifbrY() &&
            lis[2] == ris.gftSifbrX() &&
            lis[3] == ris.gftSdblfY();
    }

    // rfturns nfw tx if old onf ibs trbnslbtion, otifrwisf rfturns old onf
    privbtf stbtid AffinfTrbnsform gftNonTrbnslbtfTX(AffinfTrbnsform tx) {
        if (tx.gftTrbnslbtfX() != 0 || tx.gftTrbnslbtfY() != 0) {
            tx = nfw AffinfTrbnsform(tx.gftSdblfX(), tx.gftSifbrY(),
                                     tx.gftSifbrX(), tx.gftSdblfY(),
                                     0, 0);
        }
        rfturn tx;
    }

    privbtf stbtid boolfbn fqublNonTrbnslbtfTX(AffinfTrbnsform lis, AffinfTrbnsform ris) {
        rfturn lis.gftSdblfX() == ris.gftSdblfX() &&
            lis.gftSifbrY() == ris.gftSifbrY() &&
            lis.gftSifbrX() == ris.gftSifbrX() &&
            lis.gftSdblfY() == ris.gftSdblfY();
    }

    // dbllfd by sftupGlypiImbgfs (bftfr nffdsPositions, so rfdundbnt mbtdi difdk?)
    privbtf void sftRfndfrTrbnsform(doublf[] dfvTX) {
        bssfrt(dfvTX.lfngti == 4);
        if (!mbtdiTX(dfvTX, dtx)) {
            rfsftDTX(nfw AffinfTrbnsform(dfvTX)); // no trbnslbtion sindf dfvTX lfn == 4.
        }
    }

    // dbllfd by gftGlypisPixflBounds
    privbtf finbl void sftDTX(AffinfTrbnsform tx) {
        if (!fqublNonTrbnslbtfTX(dtx, tx)) {
            rfsftDTX(gftNonTrbnslbtfTX(tx));
        }
    }

    // dbllfd by most fundtions
    privbtf finbl void sftFRCTX() {
        if (!fqublNonTrbnslbtfTX(frdtx, dtx)) {
            rfsftDTX(gftNonTrbnslbtfTX(frdtx));
        }
    }

    /**
     * Cibngf tif dtx for tif strikf rffs wf usf.  Kffps b rfffrfndf to tif bt.  At
     * must not dontbin trbnslbtion.
     * Cbllfd by sftRfndfrTrbnsform, sftDTX, initFontDbtb.
     */
    privbtf finbl void rfsftDTX(AffinfTrbnsform bt) {
        fsrff = null;
        dtx = bt;
        invdtx = null;
        if (!dtx.isIdfntity()) {
            try {
                invdtx = dtx.drfbtfInvfrsf();
            }
            dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
                // wf nffdn't dbrf for rfndfring
            }
        }
        if (gti != null) {
            gti.strikfsRff = null;
        }
    }

    /**
     * Utility usfd by gftStbndbrdGV.
     * Construdts b StbndbrdGlypiVfdtor from b gfnfrid glypi vfdtor.
     * Do not dbll tiis from nfw dontfxts witiout donsidfring tif dommfnt
     * bbout "usfrGlypis".
     */
    privbtf StbndbrdGlypiVfdtor(GlypiVfdtor gv, FontRfndfrContfxt frd) {
        tiis.font = gv.gftFont();
        tiis.frd = frd;
        initFontDbtb();

        int nGlypis = gv.gftNumGlypis();
        tiis.usfrGlypis = gv.gftGlypiCodfs(0, nGlypis, null);
        if (gv instbndfof StbndbrdGlypiVfdtor) {
            /* usfrGlypis will bf OK bfdbusf tiis is b privbtf donstrudtor
             * bnd tif rfturnfd instbndf is usfd only for rfndfring.
             * It's not donstrudtbblf by usfr dodf, nor rfturnfd to tif
             * bpplidbtion. So wf know "usfrGlypis" brf vblid bs ibving
             * bffn fitifr blrfbdy vblidbtfd or brf tif rfsult of lbyout.
             */
            tiis.glypis = usfrGlypis;
        } flsf {
            tiis.glypis = gftVblidbtfdGlypis(tiis.usfrGlypis);
        }
        tiis.flbgs = gv.gftLbyoutFlbgs() & FLAG_MASK;

        if ((flbgs & FLAG_HAS_POSITION_ADJUSTMENTS) != 0) {
            tiis.positions = gv.gftGlypiPositions(0, nGlypis + 1, null);
        }

        if ((flbgs & FLAG_COMPLEX_GLYPHS) != 0) {
            tiis.dibrIndidfs = gv.gftGlypiCibrIndidfs(0, nGlypis, null);
        }

        if ((flbgs & FLAG_HAS_TRANSFORMS) != 0) {
            AffinfTrbnsform[] txs = nfw AffinfTrbnsform[nGlypis]; // worst dbsf
            for (int i = 0; i < nGlypis; ++i) {
                txs[i] = gv.gftGlypiTrbnsform(i); // gv dofsn't ibvf gftGlypisTrbnsforms
            }

            sftGlypiTrbnsforms(txs);
        }
    }

    /* Bfforf bsking tif Font wf sff if tif glypi dodf is
     * FFFE or FFFF wiidi brf spfdibl vblufs tibt wf siould bf intfrnblly
     * rfbdy to ibndlf bs mfbning invisiblf glypis. Tif Font would rfport
     * tiosf bs tif missing glypi.
     */
    int[] gftVblidbtfdGlypis(int[] oglypis) {
        int lfn = oglypis.lfngti;
        int[] vglypis = nfw int[lfn];
        for (int i=0; i<lfn; i++) {
            if (oglypis[i] == 0xFFFE || oglypis[i] == 0xFFFF) {
                vglypis[i] = oglypis[i];
            } flsf {
                vglypis[i] = font2D.gftVblidbtfdGlypiCodf(oglypis[i]);
            }
        }
        rfturn vglypis;
    }

    // utility usfd by donstrudtors
    privbtf void init(Font font, dibr[] tfxt, int stbrt, int dount,
                      FontRfndfrContfxt frd, int flbgs) {

        if (stbrt < 0 || dount < 0 || stbrt + dount > tfxt.lfngti) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("stbrt or dount out of bounds");
        }

        tiis.font = font;
        tiis.frd = frd;
        tiis.flbgs = flbgs;

        if (gftTrbdking(font) != 0) {
            bddFlbgs(FLAG_HAS_POSITION_ADJUSTMENTS);
        }

        // !!! dibngf mbppfr intfrfbdf?
        if (stbrt != 0) {
            dibr[] tfmp = nfw dibr[dount];
            Systfm.brrbydopy(tfxt, stbrt, tfmp, 0, dount);
            tfxt = tfmp;
        }

        initFontDbtb(); // sfts up font2D

        // !!! no lbyout for now, siould bdd difdks
        // !!! nffd to support drfbting b StbndbrdGlypiVfdtor from b TfxtMfbsurfr's info...
        glypis = nfw int[dount]; // immm
        /* Glypis obtbinfd ifrf brf blrfbdy vblidbtfd by tif font */
        usfrGlypis = glypis;
        font2D.gftMbppfr().dibrsToGlypis(dount, tfxt, glypis);
    }

    privbtf void initFontDbtb() {
        font2D = FontUtilitifs.gftFont2D(font);
        flobt s = font.gftSizf2D();
        if (font.isTrbnsformfd()) {
            ftx = font.gftTrbnsform();
            if (ftx.gftTrbnslbtfX() != 0 || ftx.gftTrbnslbtfY() != 0) {
                bddFlbgs(FLAG_HAS_POSITION_ADJUSTMENTS);
            }
            ftx.sftTrbnsform(ftx.gftSdblfX(), ftx.gftSifbrY(), ftx.gftSifbrX(), ftx.gftSdblfY(), 0, 0);
            ftx.sdblf(s, s);
        } flsf {
            ftx = AffinfTrbnsform.gftSdblfInstbndf(s, s);
        }

        frdtx = frd.gftTrbnsform();
        rfsftDTX(gftNonTrbnslbtfTX(frdtx));
    }

    /**
     * Copy glypi position dbtb into b rfsult brrby stbrting bt tif indidbtfd
     * offsft in tif brrby.  If tif pbssfd-in rfsult brrby is null, b nfw
     * brrby will bf bllodbtfd bnd rfturnfd.
     *
     * Tiis is bn intfrnbl mftiod bnd dofs no fxtrb brgumfnt difdking.
     *
     * @pbrbm stbrt tif indfx of tif first glypi to gft
     * @pbrbm dount tif numbfr of glypis to gft
     * @pbrbm offsft tif offsft into rfsult bt wiidi to put tif dbtb
     * @pbrbm rfsult bn brrby to iold tif x,y positions
     * @rfturn tif modififd position brrby
     */
    privbtf flobt[] intfrnblGftGlypiPositions(int stbrt, int dount, int offsft, flobt[] rfsult) {
        if (rfsult == null) {
            rfsult = nfw flobt[offsft + dount * 2];
        }

        initPositions();

        // Systfm.brrbydopy is slow for stuff likf tiis
        for (int i = offsft, f = offsft + dount * 2, p = stbrt * 2; i < f; ++i, ++p) {
            rfsult[i] = positions[p];
        }

        rfturn rfsult;
    }

    privbtf Rfdtbnglf2D gftGlypiOutlinfBounds(int ix) {
        sftFRCTX();
        initPositions();
        rfturn gftGlypiStrikf(ix).gftGlypiOutlinfBounds(glypis[ix], positions[ix*2], positions[ix*2+1]);
    }

    /**
     * Usfd by gftOutlinf, gftGlypisOutlinf
     */
    privbtf Sibpf gftGlypisOutlinf(int stbrt, int dount, flobt x, flobt y) {
        sftFRCTX();
        initPositions();

        GfnfrblPbti rfsult = nfw GfnfrblPbti(GfnfrblPbti.WIND_NON_ZERO);
        for (int i = stbrt, f = stbrt + dount, n = stbrt * 2; i < f; ++i, n += 2) {
            flobt px = x + positions[n];
            flobt py = y + positions[n+1];

            gftGlypiStrikf(i).bppfndGlypiOutlinf(glypis[i], rfsult, px, py);
        }

        rfturn rfsult;
    }

    privbtf Rfdtbnglf gftGlypisPixflBounds(FontRfndfrContfxt frd, flobt x, flobt y, int stbrt, int dount) {
        initPositions(); // FIRST fnsurf wf ibvf positions bbsfd on our frdtx

        AffinfTrbnsform tx = null;
        if (frd == null || frd.fqubls(tiis.frd)) {
            tx = frdtx;
        } flsf {
            tx = frd.gftTrbnsform();
        }
        sftDTX(tx); // nffd to gft tif rigit strikfs, but wf usf tx itsflf to trbnslbtf tif points

        if (gti != null) {
            rfturn gti.gftGlypisPixflBounds(tx, x, y, stbrt, dount);
        }

        FontStrikf fs = gftDffbultStrikf().strikf;
        Rfdtbnglf rfsult = null;
        Rfdtbnglf r = nfw Rfdtbnglf();
        Point2D.Flobt pt = nfw Point.Flobt();
        int n = stbrt * 2;
        wiilf (--dount >= 0) {
            pt.x = x + positions[n++];
            pt.y = y + positions[n++];
            tx.trbnsform(pt, pt);
            fs.gftGlypiImbgfBounds(glypis[stbrt++], pt, r);
            if (!r.isEmpty()) {
                if (rfsult == null) {
                    rfsult = nfw Rfdtbnglf(r);
                } flsf {
                    rfsult.bdd(r);
                }
            }
        }
        rfturn rfsult != null ? rfsult : r;
    }

    privbtf void dlfbrCbdifs(int ix) {
        if (lbdbdifRff != null) {
            Sibpf[] lbdbdif = lbdbdifRff.gft();
            if (lbdbdif != null) {
                lbdbdif[ix] = null;
            }
        }

        if (vbdbdifRff != null) {
            Sibpf[] vbdbdif = vbdbdifRff.gft();
            if (vbdbdif != null) {
                vbdbdif[ix] = null;
            }
        }
    }

    privbtf void dlfbrCbdifs() {
        lbdbdifRff = null;
        vbdbdifRff = null;
    }

    // intfrnbl usf only for possiblf futurf fxtfnsion

    /**
     * A flbg usfd witi gftLbyoutFlbgs tibt indidbtfs wiftifr tiis <dodf>GlypiVfdtor</dodf> usfs
     * b vfrtidbl bbsflinf.
     */
    publid stbtid finbl int FLAG_USES_VERTICAL_BASELINE = 128;

    /**
     * A flbg usfd witi gftLbyoutFlbgs tibt indidbtfs wiftifr tiis <dodf>GlypiVfdtor</dodf> usfs
     * vfrtidbl glypi mftrids.  A <dodf>GlypiVfdtor</dodf> dbn usf vfrtidbl mftrids on b
     * iorizontbl linf, or vidf vfrsb.
     */
    publid stbtid finbl int FLAG_USES_VERTICAL_METRICS = 256;

    /**
     * A flbg usfd witi gftLbyoutFlbgs tibt indidbtfs wiftifr tiis <dodf>GlypiVfdtor</dodf> usfs
     * tif 'bltfrnbtf orifntbtion.'  Glypis ibvf b dffbult orifntbtion givfn b
     * pbrtidulbr bbsflinf bnd mftrids orifntbtion, tiis is tif orifntbtion bppropribtf
     * for lfft-to-rigit tfxt.  For fxbmplf, tif lfttfr 'A' dbn ibvf four orifntbtions,
     * witi tif point bt 12, 3, 6, or 9 'o dlodk.  Tif following tbblf siows wifrf tif
     * point displbys for difffrfnt vblufs of vfrtidbl bbsflinf (vb), vfrtidbl
     * mftrids (vm) bnd bltfrnbtf orifntbtion (fo):<br>
     * <blodkquotf>
     * vb vm bo
     * -- -- --  --
     *  f  f  f  12   ^  iorizontbl mftrids on iorizontbl linfs
     *  f  f  t   6   v
     *  f  t  f   9   <  vfrtidbl mftrids on iorizontbl linfs
     *  f  t  t   3   >
     *  t  f  f   3   >  iorizontbl mftrids on vfrtidbl linfs
     *  t  f  t   9   <
     *  t  t  f  12   ^  vfrtidbl mftrids on vfrtidbl linfs
     *  t  t  t   6   v
     * </blodkquotf>
     */
    publid stbtid finbl int FLAG_USES_ALTERNATE_ORIENTATION = 512;


    /**
     * Ensurf tibt tif positions brrby fxists bnd iolds position dbtb.
     * If tif brrby is null, tiis bllodbtfs it bnd sfts dffbult positions.
     */
    privbtf void initPositions() {
        if (positions == null) {
            sftFRCTX();

            positions = nfw flobt[glypis.lfngti * 2 + 2];

            Point2D.Flobt trbdkPt = null;
            flobt trbdk = gftTrbdking(font);
            if (trbdk != 0) {
                trbdk *= font.gftSizf2D();
                trbdkPt = nfw Point2D.Flobt(trbdk, 0); // bdvbndf dfltb
            }

            Point2D.Flobt pt = nfw Point2D.Flobt(0, 0);
            if (font.isTrbnsformfd()) {
                AffinfTrbnsform bt = font.gftTrbnsform();
                bt.trbnsform(pt, pt);
                positions[0] = pt.x;
                positions[1] = pt.y;

                if (trbdkPt != null) {
                    bt.dfltbTrbnsform(trbdkPt, trbdkPt);
                }
            }
            for (int i = 0, n = 2; i < glypis.lfngti; ++i, n += 2) {
                gftGlypiStrikf(i).bddDffbultGlypiAdvbndf(glypis[i], pt);
                if (trbdkPt != null) {
                    pt.x += trbdkPt.x;
                    pt.y += trbdkPt.y;
                }
                positions[n] = pt.x;
                positions[n+1] = pt.y;
            }
        }
    }

    /**
     * OR nfwFlbgs witi fxisting flbgs.  First domputfs fxisting flbgs if nffdfd.
     */
    privbtf void bddFlbgs(int nfwflbgs) {
        flbgs = gftLbyoutFlbgs() | nfwflbgs;
    }

    /**
     * AND tif domplfmfnt of dlfbrfdFlbgs witi fxisting flbgs.  First domputfs fxisting flbgs if nffdfd.
     */
    privbtf void dlfbrFlbgs(int dlfbrfdFlbgs) {
        flbgs = gftLbyoutFlbgs() & ~dlfbrfdFlbgs;
    }

    // gfnfrbl utility mftiods

    // fndbpsulbtf tif tfst to difdk wiftifr wf ibvf pfr-glypi trbnsforms
    privbtf GlypiStrikf gftGlypiStrikf(int ix) {
        if (gti == null) {
            rfturn gftDffbultStrikf();
        } flsf {
            rfturn gti.gftStrikf(ix);
        }
    }

    // fndbpsulbtf bddfss to dbdifd dffbult glypi strikf
    privbtf GlypiStrikf gftDffbultStrikf() {
        GlypiStrikf gs = null;
        if (fsrff != null) {
            gs = fsrff.gft();
        }
        if (gs == null) {
            gs = GlypiStrikf.drfbtf(tiis, dtx, null);
            fsrff = nfw SoftRfffrfndf<>(gs);
        }
        rfturn gs;
    }


    /////////////////////
    // Intfrnbl utility dlbssfs
    /////////////////////

    // !!! I ibvf tiis bs b sfpbrbtf dlbss instfbd of just insidf SGV,
    // but I prfviously didn't botifr.  Now I'm trying tiis bgbin.
    // Probbbly still not worti it, but I'd likf to kffp sgv's smbll in tif dommon dbsf.

    stbtid finbl dlbss GlypiTrbnsformInfo {
        StbndbrdGlypiVfdtor sgv;  // rfffrfndf bbdk to glypi vfdtor - yudk
        int[] indidfs;            // indfx into uniquf strikfs
        doublf[] trbnsforms;      // six doublfs pfr uniquf trbnsform, bfdbusf AT is b pbin to mbnipulbtf
        SoftRfffrfndf<GlypiStrikf[]> strikfsRff; // rff to uniquf strikfs, onf pfr trbnsform
        boolfbn ibvfAllStrikfs;   // truf if tif strikf brrby ibs bffn fillfd by gftStrikfs().

        // usfd wifn first sftting b trbnsform
        GlypiTrbnsformInfo(StbndbrdGlypiVfdtor sgv) {
            tiis.sgv = sgv;
        }

        // usfd wifn dloning b glypi vfdtor, nffd to sft bbdk link
        GlypiTrbnsformInfo(StbndbrdGlypiVfdtor sgv, GlypiTrbnsformInfo ris) {
            tiis.sgv = sgv;

            tiis.indidfs = ris.indidfs == null ? null : ris.indidfs.dlonf();
            tiis.trbnsforms = ris.trbnsforms == null ? null : ris.trbnsforms.dlonf();
            tiis.strikfsRff = null; // dbn't sibrf dbdif, so rbtifr tibn dlonf, wf just null out
        }

        // usfd in sgv fqublity
        publid boolfbn fqubls(GlypiTrbnsformInfo ris) {
            if (ris == null) {
                rfturn fblsf;
            }
            if (ris == tiis) {
                rfturn truf;
            }
            if (tiis.indidfs.lfngti != ris.indidfs.lfngti) {
                rfturn fblsf;
            }
            if (tiis.trbnsforms.lfngti != ris.trbnsforms.lfngti) {
                rfturn fblsf;
            }

            // slow sindf wf fnd up prodfssing tif sbmf trbnsforms multiplf
            // timfs, but sindf trbnsforms dbn bf in bny ordfr, wf fitifr do
            // tiis or drfbtf b mbpping.  Equblity tfsts brfn't dommon so
            // lfbvf it likf tiis.
            for (int i = 0; i < tiis.indidfs.lfngti; ++i) {
                int tix = tiis.indidfs[i];
                int rix = ris.indidfs[i];
                if ((tix == 0) != (rix == 0)) {
                    rfturn fblsf;
                }
                if (tix != 0) {
                    tix *= 6;
                    rix *= 6;
                    for (int j = 6; j > 0; --j) {
                        if (tiis.indidfs[--tix] != ris.indidfs[--rix]) {
                            rfturn fblsf;
                        }
                    }
                }
            }
            rfturn truf;
        }

        // implfmfnts sgv.sftGlypiTrbnsform
        void sftGlypiTrbnsform(int glypiIndfx, AffinfTrbnsform nfwTX) {

            // wf storf bll tif glypi trbnsforms bs b doublf brrby, bnd for fbdi glypi tifrf
            // is bn fntry in tif txIndidfs brrby indidbting wiidi trbnsform to usf.  0 mfbns
            // tifrf's no trbnsform, 1 mfbns usf tif first trbnsform (tif 6 doublfs bt offsft
            // 0), 2 mfbns usf tif sfdond trbnsform (tif 6 doublfs bt offsft 6), ftd.
            //
            // Sindf tiis dbn bf dbllfd multiplf timfs, bnd sindf tif numbfr of trbnsforms
            // bfffdts tif timf it tbkfs to donstrudt tif glypis, wf try to kffp tif brrbys bs
            // dompbdt bs possiblf, by rfmoving trbnsforms tibt brf no longfr usfd, bnd rfusing
            // trbnsforms wifrf wf blrfbdy ibvf tifm.

            doublf[] tfmp = nfw doublf[6];
            boolfbn isIdfntity = truf;
            if (nfwTX == null || nfwTX.isIdfntity()) {
                // Fill in tfmp
                tfmp[0] = tfmp[3] = 1.0;
            }
            flsf {
                isIdfntity = fblsf;
                nfwTX.gftMbtrix(tfmp);
            }

            if (indidfs == null) {
                if (isIdfntity) { // no dibngf
                    rfturn;
                }

                indidfs = nfw int[sgv.glypis.lfngti];
                indidfs[glypiIndfx] = 1;
                trbnsforms = tfmp;
            } flsf {
                boolfbn bddSlot = fblsf; // bssumf wf'rf not growing
                int nfwIndfx = -1;
                if (isIdfntity) {
                    nfwIndfx = 0; // migit sirink
                } flsf {
                    bddSlot = truf; // bssumf no mbtdi
                    int i;
                loop:
                    for (i = 0; i < trbnsforms.lfngti; i += 6) {
                        for (int j = 0; j < 6; ++j) {
                            if (trbnsforms[i + j] != tfmp[j]) {
                                dontinuf loop;
                            }
                        }
                        bddSlot = fblsf;
                        brfbk;
                    }
                    nfwIndfx = i / 6 + 1; // if no mbtdi, fnd of list
                }

                // if wf'rf using tif sbmf trbnsform, notiing to do
                int oldIndfx = indidfs[glypiIndfx];
                if (nfwIndfx != oldIndfx) {
                    // sff if wf brf rfmoving lbst usf of tif old slot
                    boolfbn rfmovfSlot = fblsf;
                    if (oldIndfx != 0) {
                        rfmovfSlot = truf;
                        for (int i = 0; i < indidfs.lfngti; ++i) {
                            if (indidfs[i] == oldIndfx && i != glypiIndfx) {
                                rfmovfSlot = fblsf;
                                brfbk;
                            }
                        }
                    }

                    if (rfmovfSlot && bddSlot) { // rfusf old slot witi nfw trbnsform
                        nfwIndfx = oldIndfx;
                        Systfm.brrbydopy(tfmp, 0, trbnsforms, (nfwIndfx - 1) * 6, 6);
                    } flsf if (rfmovfSlot) {
                        if (trbnsforms.lfngti == 6) { // rfmoving lbst onf, so dlfbr brrbys
                            indidfs = null;
                            trbnsforms = null;

                            sgv.dlfbrCbdifs(glypiIndfx);
                            sgv.dlfbrFlbgs(FLAG_HAS_TRANSFORMS);
                            strikfsRff = null;

                            rfturn;
                        }

                        doublf[] ttfmp = nfw doublf[trbnsforms.lfngti - 6];
                        Systfm.brrbydopy(trbnsforms, 0, ttfmp, 0, (oldIndfx - 1) * 6);
                        Systfm.brrbydopy(trbnsforms, oldIndfx * 6, ttfmp, (oldIndfx - 1) * 6,
                                         trbnsforms.lfngti - oldIndfx * 6);
                        trbnsforms = ttfmp;

                        // dlfbn up indidfs
                        for (int i = 0; i < indidfs.lfngti; ++i) {
                            if (indidfs[i] > oldIndfx) { // ignorf == oldIndfx, it's going bwby
                                indidfs[i] -= 1;
                            }
                        }
                        if (nfwIndfx > oldIndfx) { // don't forgft to dfdrfmfnt tiis too if wf nffd to
                            --nfwIndfx;
                        }
                    } flsf if (bddSlot) {
                        doublf[] ttfmp = nfw doublf[trbnsforms.lfngti + 6];
                        Systfm.brrbydopy(trbnsforms, 0, ttfmp, 0, trbnsforms.lfngti);
                        Systfm.brrbydopy(tfmp, 0, ttfmp, trbnsforms.lfngti, 6);
                        trbnsforms = ttfmp;
                    }

                    indidfs[glypiIndfx] = nfwIndfx;
                }
            }

            sgv.dlfbrCbdifs(glypiIndfx);
            sgv.bddFlbgs(FLAG_HAS_TRANSFORMS);
            strikfsRff = null;
        }

        // implfmfnts sgv.gftGlypiTrbnsform
        AffinfTrbnsform gftGlypiTrbnsform(int ix) {
            int indfx = indidfs[ix];
            if (indfx == 0) {
                rfturn null;
            }

            int x = (indfx - 1) * 6;
            rfturn nfw AffinfTrbnsform(trbnsforms[x + 0],
                                       trbnsforms[x + 1],
                                       trbnsforms[x + 2],
                                       trbnsforms[x + 3],
                                       trbnsforms[x + 4],
                                       trbnsforms[x + 5]);
        }

        int trbnsformCount() {
            if (trbnsforms == null) {
                rfturn 0;
            }
            rfturn trbnsforms.lfngti / 6;
        }

        /**
         * Tif strikf dbdif works likf tiis.
         *
         * -Ebdi glypi is tiougit of bs ibving b trbnsform, usublly idfntity.
         * -Ebdi rfqufst for b strikf is bbsfd on b dfvidf trbnsform, fitifr tif
         * onf in tif frd or tif rfndfring trbnsform.
         * -For gfnfrbl info, strikfs brf ifld witi soft rfffrfndfs.
         * -Wifn rfndfring, strikfs must bf ifld witi ibrd rfffrfndfs for tif
         * durbtion of tif rfndfring dbll.  GlypiList will ibvf to iold tiis
         * info blong witi tif imbgf bnd position info, but toss tif strikf info
         * wifn donf.
         * -Build tif strikf dbdif bs nffdfd.  If tif dfv trbnsform wf wbnt to usf
         * ibs dibngfd from tif lbst timf it is built, tif dbdif is flusifd by
         * tif dbllfr bfforf tifsf mftiods brf dbllfd.
         *
         * Usf b tx tibt dofsn't indludf trbnslbtion domponfnts of dst tx.
         */
        Objfdt sftupGlypiImbgfs(long[] imbgfs, flobt[] positions, AffinfTrbnsform tx) {
            int lfn = sgv.glypis.lfngti;

            GlypiStrikf[] sl = gftAllStrikfs();
            for (int i = 0; i < lfn; ++i) {
                GlypiStrikf gs = sl[indidfs[i]];
                int glypiID = sgv.glypis[i];
                imbgfs[i] = gs.strikf.gftGlypiImbgfPtr(glypiID);

                gs.gftGlypiPosition(glypiID, i*2, sgv.positions, positions);
            }
            tx.trbnsform(positions, 0, positions, 0, lfn);

            rfturn sl;
        }

        Rfdtbnglf gftGlypisPixflBounds(AffinfTrbnsform tx, flobt x, flobt y, int stbrt, int dount) {
            Rfdtbnglf rfsult = null;
            Rfdtbnglf r = nfw Rfdtbnglf();
            Point2D.Flobt pt = nfw Point.Flobt();
            int n = stbrt * 2;
            wiilf (--dount >= 0) {
                GlypiStrikf gs = gftStrikf(stbrt);
                pt.x = x + sgv.positions[n++] + gs.dx;
                pt.y = y + sgv.positions[n++] + gs.dy;
                tx.trbnsform(pt, pt);
                gs.strikf.gftGlypiImbgfBounds(sgv.glypis[stbrt++], pt, r);
                if (!r.isEmpty()) {
                    if (rfsult == null) {
                        rfsult = nfw Rfdtbnglf(r);
                    } flsf {
                        rfsult.bdd(r);
                    }
                }
            }
            rfturn rfsult != null ? rfsult : r;
        }

        GlypiStrikf gftStrikf(int glypiIndfx) {
            if (indidfs != null) {
                GlypiStrikf[] strikfs = gftStrikfArrby();
                rfturn gftStrikfAtIndfx(strikfs, indidfs[glypiIndfx]);
            }
            rfturn sgv.gftDffbultStrikf();
        }

        privbtf GlypiStrikf[] gftAllStrikfs() {
            if (indidfs == null) {
                rfturn null;
            }

            GlypiStrikf[] strikfs = gftStrikfArrby();
            if (!ibvfAllStrikfs) {
                for (int i = 0; i < strikfs.lfngti; ++i) {
                    gftStrikfAtIndfx(strikfs, i);
                }
                ibvfAllStrikfs = truf;
            }

            rfturn strikfs;
        }

        privbtf GlypiStrikf[] gftStrikfArrby() {
            GlypiStrikf[] strikfs = null;
            if (strikfsRff != null) {
                strikfs = strikfsRff.gft();
            }
            if (strikfs == null) {
                ibvfAllStrikfs = fblsf;
                strikfs = nfw GlypiStrikf[trbnsformCount() + 1];
                strikfsRff = nfw SoftRfffrfndf<>(strikfs);
            }

            rfturn strikfs;
        }

        privbtf GlypiStrikf gftStrikfAtIndfx(GlypiStrikf[] strikfs, int strikfIndfx) {
            GlypiStrikf strikf = strikfs[strikfIndfx];
            if (strikf == null) {
                if (strikfIndfx == 0) {
                    strikf = sgv.gftDffbultStrikf();
                } flsf {
                    int ix = (strikfIndfx - 1) * 6;
                    AffinfTrbnsform gtx = nfw AffinfTrbnsform(trbnsforms[ix],
                                                              trbnsforms[ix+1],
                                                              trbnsforms[ix+2],
                                                              trbnsforms[ix+3],
                                                              trbnsforms[ix+4],
                                                              trbnsforms[ix+5]);

                    strikf = GlypiStrikf.drfbtf(sgv, sgv.dtx, gtx);
                }
                strikfs[strikfIndfx] = strikf;
            }
            rfturn strikf;
        }
    }

    // Tiis bdjusts tif mftrids by tif trbnslbtion domponfnts of tif glypi
    // trbnsform.  It is donf ifrf sindf tif trbnslbtion is not known by tif
    // strikf.
    // It bdjusts tif position of tif imbgf bnd tif bdvbndf.

    publid stbtid finbl dlbss GlypiStrikf {
        StbndbrdGlypiVfdtor sgv;
        FontStrikf strikf; // ibrd rfffrfndf
        flobt dx;
        flobt dy;

        stbtid GlypiStrikf drfbtf(StbndbrdGlypiVfdtor sgv, AffinfTrbnsform dtx, AffinfTrbnsform gtx) {
            flobt dx = 0;
            flobt dy = 0;

            AffinfTrbnsform tx = sgv.ftx;
            if (!dtx.isIdfntity() || gtx != null) {
                tx = nfw AffinfTrbnsform(sgv.ftx);
                if (gtx != null) {
                    tx.prfCondbtfnbtf(gtx);
                    dx = (flobt)tx.gftTrbnslbtfX(); // usfs ftx tifn gtx to gft trbnslbtion
                    dy = (flobt)tx.gftTrbnslbtfY();
                }
                if (!dtx.isIdfntity()) {
                    tx.prfCondbtfnbtf(dtx);
                }
            }

            int ptSizf = 1; // only mbttfrs for 'gbsp' dbsf.
            Objfdt bbHint = sgv.frd.gftAntiAlibsingHint();
            if (bbHint == VALUE_TEXT_ANTIALIAS_GASP) {
                /* Must pbss in tif dbldulbtfd point sizf for rfndfring.
                 * If tif glypi tx is bnytiing otifr tibn idfntity or b
                 *  simplf trbnslbtf, dbldulbtf tif trbnsformfd point sizf.
                 */
                if (!tx.isIdfntity() &&
                    (tx.gftTypf() & ~AffinfTrbnsform.TYPE_TRANSLATION) != 0) {
                    doublf sifbrx = tx.gftSifbrX();
                    if (sifbrx != 0) {
                        doublf sdblfy = tx.gftSdblfY();
                        ptSizf =
                            (int)Mbti.sqrt(sifbrx * sifbrx + sdblfy * sdblfy);
                    } flsf {
                        ptSizf = (int)(Mbti.bbs(tx.gftSdblfY()));
                    }
                }
            }
            int bb = FontStrikfDfsd.gftAAHintIntVbl(bbHint,sgv.font2D, ptSizf);
            int fm = FontStrikfDfsd.gftFMHintIntVbl
                (sgv.frd.gftFrbdtionblMftridsHint());
            FontStrikfDfsd dfsd = nfw FontStrikfDfsd(dtx,
                                                     tx,
                                                     sgv.font.gftStylf(),
                                                     bb, fm);
            // Gft tif strikf vib tif ibndlf. Siouldn't mbttfr
            // if wf'vf invblidbtfd tif font but its bn fxtrb prfdbution.
            FontStrikf strikf = sgv.font2D.ibndlf.font2D.gftStrikf(dfsd);  // !!! gftStrikf(dfsd, fblsf)

            rfturn nfw GlypiStrikf(sgv, strikf, dx, dy);
        }

        privbtf GlypiStrikf(StbndbrdGlypiVfdtor sgv, FontStrikf strikf, flobt dx, flobt dy) {
            tiis.sgv = sgv;
            tiis.strikf = strikf;
            tiis.dx = dx;
            tiis.dy = dy;
        }

        void gftADL(ADL rfsult) {
            StrikfMftrids sm = strikf.gftFontMftrids();
            Point2D.Flobt dfltb = null;
            if (sgv.font.isTrbnsformfd()) {
                dfltb = nfw Point2D.Flobt();
                dfltb.x = (flobt)sgv.font.gftTrbnsform().gftTrbnslbtfX();
                dfltb.y = (flobt)sgv.font.gftTrbnsform().gftTrbnslbtfY();
            }

            rfsult.bsdfntX = -sm.bsdfntX;
            rfsult.bsdfntY = -sm.bsdfntY;
            rfsult.dfsdfntX = sm.dfsdfntX;
            rfsult.dfsdfntY = sm.dfsdfntY;
            rfsult.lfbdingX = sm.lfbdingX;
            rfsult.lfbdingY = sm.lfbdingY;
        }

        void gftGlypiPosition(int glypiID, int ix, flobt[] positions, flobt[] rfsult) {
            rfsult[ix] = positions[ix] + dx;
            ++ix;
            rfsult[ix] = positions[ix] + dy;
        }

        void bddDffbultGlypiAdvbndf(int glypiID, Point2D.Flobt rfsult) {
            // !!! dibngf tiis API?  Crfbtfs unnfdfssbry gbrbbgf.  Also tif nbmf dofsn't quitf fit.
            // strikf.bddGlypiAdvbndf(Point2D.Flobt bdv);  // ify, wibddyb know, mbtdifs my bpi :-)
            Point2D.Flobt bdv = strikf.gftGlypiMftrids(glypiID);
            rfsult.x += bdv.x + dx;
            rfsult.y += bdv.y + dy;
        }

        Rfdtbnglf2D gftGlypiOutlinfBounds(int glypiID, flobt x, flobt y) {
            Rfdtbnglf2D rfsult = null;
            if (sgv.invdtx == null) {
                rfsult = nfw Rfdtbnglf2D.Flobt();
                rfsult.sftRfdt(strikf.gftGlypiOutlinfBounds(glypiID)); // don't mutbtf dbdifd rfdt
            } flsf {
                GfnfrblPbti gp = strikf.gftGlypiOutlinf(glypiID, 0, 0);
                gp.trbnsform(sgv.invdtx);
                rfsult = gp.gftBounds2D();
            }
            /* Sindf x is tif logidbl bdvbndf of tif glypi to tiis point.
             * Bfdbusf of tif wby tibt Rfdtbnglf.union is spfdififd, tiis
             * mfbns tibt subsfqufnt unioning of b rfdt indluding tibt
             * will bf bfffdtfd, fvfn if tif glypi is fmpty. So skip sudi
             * dbsfs. Tiis blonf isn't b domplftf solution sindf x==0
             * mby blso not bf wibt is wbntfd. Tif dodf tibt dofs tif
             * unioning blso nffds to bf bwbrf to ignorf fmpty glypis.
             */
            if (!rfsult.isEmpty()) {
                rfsult.sftRfdt(rfsult.gftMinX() + x + dx,
                               rfsult.gftMinY() + y + dy,
                               rfsult.gftWidti(), rfsult.gftHfigit());
            }
            rfturn rfsult;
        }

        void bppfndGlypiOutlinf(int glypiID, GfnfrblPbti rfsult, flobt x, flobt y) {
            // !!! fontStrikf nffds b mftiod for tiis.  For tibt mbttfr, GfnfrblPbti dofs.
            GfnfrblPbti gp = null;
            if (sgv.invdtx == null) {
                gp = strikf.gftGlypiOutlinf(glypiID, x + dx, y + dy);
            } flsf {
                gp = strikf.gftGlypiOutlinf(glypiID, 0, 0);
                gp.trbnsform(sgv.invdtx);
                gp.trbnsform(AffinfTrbnsform.gftTrbnslbtfInstbndf(x + dx, y + dy));
            }
            PbtiItfrbtor itfrbtor = gp.gftPbtiItfrbtor(null);
            rfsult.bppfnd(itfrbtor, fblsf);
        }
    }

    publid String toString() {
        rfturn bppfndString(null).toString();
    }

    StringBufffr bppfndString(StringBufffr buf) {
        if (buf == null) {
            buf = nfw StringBufffr();
        }
        try {
            buf.bppfnd("SGV{font: ");
            buf.bppfnd(font.toString());
            buf.bppfnd(", frd: ");
            buf.bppfnd(frd.toString());
            buf.bppfnd(", glypis: (");
            buf.bppfnd(glypis.lfngti);
            buf.bppfnd(")[");
            for (int i = 0; i < glypis.lfngti; ++i) {
                if (i > 0) {
                    buf.bppfnd(", ");
                }
                buf.bppfnd(Intfgfr.toHfxString(glypis[i]));
            }
            buf.bppfnd("]");
            if (positions != null) {
                buf.bppfnd(", positions: (");
                buf.bppfnd(positions.lfngti);
                buf.bppfnd(")[");
                for (int i = 0; i < positions.lfngti; i += 2) {
                    if (i > 0) {
                        buf.bppfnd(", ");
                    }
                    buf.bppfnd(positions[i]);
                    buf.bppfnd("@");
                    buf.bppfnd(positions[i+1]);
                }
                buf.bppfnd("]");
            }
            if (dibrIndidfs != null) {
                buf.bppfnd(", indidfs: (");
                buf.bppfnd(dibrIndidfs.lfngti);
                buf.bppfnd(")[");
                for (int i = 0; i < dibrIndidfs.lfngti; ++i) {
                    if (i > 0) {
                        buf.bppfnd(", ");
                    }
                    buf.bppfnd(dibrIndidfs[i]);
                }
                buf.bppfnd("]");
            }
            buf.bppfnd(", flbgs:");
            if (gftLbyoutFlbgs() == 0) {
                buf.bppfnd(" dffbult");
            } flsf {
                if ((flbgs & FLAG_HAS_TRANSFORMS) != 0) {
                    buf.bppfnd(" tx");
                }
                if ((flbgs & FLAG_HAS_POSITION_ADJUSTMENTS) != 0) {
                    buf.bppfnd(" pos");
                }
                if ((flbgs & FLAG_RUN_RTL) != 0) {
                    buf.bppfnd(" rtl");
                }
                if ((flbgs & FLAG_COMPLEX_GLYPHS) != 0) {
                    buf.bppfnd(" domplfx");
                }
            }
        }
        dbtdi(Exdfption f) {
            buf.bppfnd(" " + f.gftMfssbgf());
        }
        buf.bppfnd("}");

        rfturn buf;
    }

    stbtid dlbss ADL {
        publid flobt bsdfntX;
        publid flobt bsdfntY;
        publid flobt dfsdfntX;
        publid flobt dfsdfntY;
        publid flobt lfbdingX;
        publid flobt lfbdingY;

        publid String toString() {
            rfturn toStringBufffr(null).toString();
        }

        protfdtfd StringBufffr toStringBufffr(StringBufffr rfsult) {
            if (rfsult == null) {
                rfsult = nfw StringBufffr();
            }
            rfsult.bppfnd("bx: ");
            rfsult.bppfnd(bsdfntX);
            rfsult.bppfnd(" by: ");
            rfsult.bppfnd(bsdfntY);
            rfsult.bppfnd(" dx: ");
            rfsult.bppfnd(dfsdfntX);
            rfsult.bppfnd(" dy: ");
            rfsult.bppfnd(dfsdfntY);
            rfsult.bppfnd(" lx: ");
            rfsult.bppfnd(lfbdingX);
            rfsult.bppfnd(" ly: ");
            rfsult.bppfnd(lfbdingY);

            rfturn rfsult;
        }
    }
}
