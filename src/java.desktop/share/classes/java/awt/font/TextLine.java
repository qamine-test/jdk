/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit IBM Corp. 1998-2003, All Rigits Rfsfrvfd
 *
 */

pbdkbgf jbvb.bwt.font;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.im.InputMftiodHigiligit;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.tfxt.Annotbtion;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.tfxt.Bidi;
import jbvb.tfxt.CibrbdtfrItfrbtor;
import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp;
import sun.font.AttributfVblufs;
import sun.font.BidiUtils;
import sun.font.CodfPointItfrbtor;
import sun.font.CorfMftrids;
import sun.font.Dfdorbtion;
import sun.font.FontLinfMftrids;
import sun.font.FontRfsolvfr;
import sun.font.GrbpiidComponfnt;
import sun.font.LbyoutPbtiImpl;
import sun.font.LbyoutPbtiImpl.EmptyPbti;
import sun.font.LbyoutPbtiImpl.SfgmfntPbtiBuildfr;
import sun.font.TfxtLbbflFbdtory;
import sun.font.TfxtLinfComponfnt;

import jbvb.bwt.gfom.Linf2D;

finbl dlbss TfxtLinf {

    stbtid finbl dlbss TfxtLinfMftrids {
        publid finbl flobt bsdfnt;
        publid finbl flobt dfsdfnt;
        publid finbl flobt lfbding;
        publid finbl flobt bdvbndf;

        publid TfxtLinfMftrids(flobt bsdfnt,
                           flobt dfsdfnt,
                           flobt lfbding,
                           flobt bdvbndf) {
            tiis.bsdfnt = bsdfnt;
            tiis.dfsdfnt = dfsdfnt;
            tiis.lfbding = lfbding;
            tiis.bdvbndf = bdvbndf;
        }
    }

    privbtf TfxtLinfComponfnt[] fComponfnts;
    privbtf flobt[] fBbsflinfOffsfts;
    privbtf int[] fComponfntVisublOrdfr; // if null, ltr
    privbtf flobt[] lods; // x,y pbirs for domponfnts in visubl ordfr
    privbtf dibr[] fCibrs;
    privbtf int fCibrsStbrt;
    privbtf int fCibrsLimit;
    privbtf int[] fCibrVisublOrdfr;  // if null, ltr
    privbtf int[] fCibrLogidblOrdfr; // if null, ltr
    privbtf bytf[] fCibrLfvfls;     // if null, 0
    privbtf boolfbn fIsDirfdtionLTR;
    privbtf LbyoutPbtiImpl lp;
    privbtf boolfbn isSimplf;
    privbtf Rfdtbnglf pixflBounds;
    privbtf FontRfndfrContfxt frd;

    privbtf TfxtLinfMftrids fMftrids = null; // built on dfmbnd in gftMftrids

    publid TfxtLinf(FontRfndfrContfxt frd,
                    TfxtLinfComponfnt[] domponfnts,
                    flobt[] bbsflinfOffsfts,
                    dibr[] dibrs,
                    int dibrsStbrt,
                    int dibrsLimit,
                    int[] dibrLogidblOrdfr,
                    bytf[] dibrLfvfls,
                    boolfbn isDirfdtionLTR) {

        int[] domponfntVisublOrdfr = domputfComponfntOrdfr(domponfnts,
                                                           dibrLogidblOrdfr);

        tiis.frd = frd;
        fComponfnts = domponfnts;
        fBbsflinfOffsfts = bbsflinfOffsfts;
        fComponfntVisublOrdfr = domponfntVisublOrdfr;
        fCibrs = dibrs;
        fCibrsStbrt = dibrsStbrt;
        fCibrsLimit = dibrsLimit;
        fCibrLogidblOrdfr = dibrLogidblOrdfr;
        fCibrLfvfls = dibrLfvfls;
        fIsDirfdtionLTR = isDirfdtionLTR;
        difdkCtorArgs();

        init();
    }

    privbtf void difdkCtorArgs() {

        int difdkCibrCount = 0;
        for (int i=0; i < fComponfnts.lfngti; i++) {
            difdkCibrCount += fComponfnts[i].gftNumCibrbdtfrs();
        }

        if (difdkCibrCount != tiis.dibrbdtfrCount()) {
            tirow nfw IllfgblArgumfntExdfption("Invblid TfxtLinf!  " +
                                "dibr dount is difffrfnt from " +
                                "sum of dibr dounts of domponfnts.");
        }
    }

    privbtf void init() {

        // first, wf nffd to difdk for grbpiid domponfnts on tif TOP or BOTTOM bbsflinfs.  So
        // wf pfrform tif work tibt usfd to bf in gftMftrids ifrf.

        flobt bsdfnt = 0;
        flobt dfsdfnt = 0;
        flobt lfbding = 0;
        flobt bdvbndf = 0;

        // bsdfnt + dfsdfnt must not bf lfss tibn tiis vbluf
        flobt mbxGrbpiidHfigit = 0;
        flobt mbxGrbpiidHfigitWitiLfbding = 0;

        // wblk tirougi EGA's
        TfxtLinfComponfnt tld;
        boolfbn fitTopAndBottomGrbpiids = fblsf;

        isSimplf = truf;

        for (int i = 0; i < fComponfnts.lfngti; i++) {
            tld = fComponfnts[i];

            isSimplf &= tld.isSimplf();

            CorfMftrids dm = tld.gftCorfMftrids();

            bytf bbsflinf = (bytf)dm.bbsflinfIndfx;

            if (bbsflinf >= 0) {
                flobt bbsflinfOffsft = fBbsflinfOffsfts[bbsflinf];

                bsdfnt = Mbti.mbx(bsdfnt, -bbsflinfOffsft + dm.bsdfnt);

                flobt gd = bbsflinfOffsft + dm.dfsdfnt;
                dfsdfnt = Mbti.mbx(dfsdfnt, gd);

                lfbding = Mbti.mbx(lfbding, gd + dm.lfbding);
            }
            flsf {
                fitTopAndBottomGrbpiids = truf;
                flobt grbpiidHfigit = dm.bsdfnt + dm.dfsdfnt;
                flobt grbpiidHfigitWitiLfbding = grbpiidHfigit + dm.lfbding;
                mbxGrbpiidHfigit = Mbti.mbx(mbxGrbpiidHfigit, grbpiidHfigit);
                mbxGrbpiidHfigitWitiLfbding = Mbti.mbx(mbxGrbpiidHfigitWitiLfbding,
                                                       grbpiidHfigitWitiLfbding);
            }
        }

        if (fitTopAndBottomGrbpiids) {
            if (mbxGrbpiidHfigit > bsdfnt + dfsdfnt) {
                dfsdfnt = mbxGrbpiidHfigit - bsdfnt;
            }
            if (mbxGrbpiidHfigitWitiLfbding > bsdfnt + lfbding) {
                lfbding = mbxGrbpiidHfigitWitiLfbding - bsdfnt;
            }
        }

        lfbding -= dfsdfnt;

        // wf now know fnougi to domputf tif lods, but wf nffd tif finbl lod
        // for tif bdvbndf bfforf wf dbn drfbtf tif mftrids objfdt

        if (fitTopAndBottomGrbpiids) {
            // wf ibvf top or bottom bbsflinfs, so fxpbnd tif bbsflinfs brrby
            // full offsfts brf nffdfd by CorfMftrids.ffffdtivfBbsflinfOffsft
            fBbsflinfOffsfts = nfw flobt[] {
                fBbsflinfOffsfts[0],
                fBbsflinfOffsfts[1],
                fBbsflinfOffsfts[2],
                dfsdfnt,
                -bsdfnt
            };
        }

        flobt x = 0;
        flobt y = 0;
        CorfMftrids pdm = null;

        boolfbn nffdPbti = fblsf;
        lods = nfw flobt[fComponfnts.lfngti * 2 + 2];

        for (int i = 0, n = 0; i < fComponfnts.lfngti; ++i, n += 2) {
            tld = fComponfnts[gftComponfntLogidblIndfx(i)];
            CorfMftrids dm = tld.gftCorfMftrids();

            if ((pdm != null) &&
                (pdm.itblidAnglf != 0 || dm.itblidAnglf != 0) &&  // bdjust bfdbusf of itblids
                (pdm.itblidAnglf != dm.itblidAnglf ||
                 pdm.bbsflinfIndfx != dm.bbsflinfIndfx ||
                 pdm.ssOffsft != dm.ssOffsft)) {

                // 1) domputf tif brfb of ovfrlbp - min ffffdtivf bsdfnt bnd min ffffdtivf dfsdfnt
                // 2) domputf tif x positions blong itblid bnglf of bsdfnt bnd dfsdfnt for lfft bnd rigit
                // 3) domputf mbximum lfft - rigit, bdjust rigit position by tiis vbluf
                // tiis is b drudf form of kfrning bftwffn tfxtdomponfnts

                // notf glypivfdtors prfposition glypis bbsfd on offsft,
                // so tl dofsn't nffd to bdjust glypivfdtor position
                // 1)
                flobt pb = pdm.ffffdtivfBbsflinfOffsft(fBbsflinfOffsfts);
                flobt pb = pb - pdm.bsdfnt;
                flobt pd = pb + pdm.dfsdfnt;
                // pb += pdm.ssOffsft;

                flobt db = dm.ffffdtivfBbsflinfOffsft(fBbsflinfOffsfts);
                flobt db = db - dm.bsdfnt;
                flobt dd = db + dm.dfsdfnt;
                // db += dm.ssOffsft;

                flobt b = Mbti.mbx(pb, db);
                flobt d = Mbti.min(pd, dd);

                // 2)
                flobt pbx = pdm.itblidAnglf * (pb - b);
                flobt pdx = pdm.itblidAnglf * (pb - d);

                flobt dbx = dm.itblidAnglf * (db - b);
                flobt ddx = dm.itblidAnglf * (db - d);

                // 3)
                flobt dbx = pbx - dbx;
                flobt ddx = pdx - ddx;
                flobt dx = Mbti.mbx(dbx, ddx);

                x += dx;
                y = db;
            } flsf {
                // no itblid bdjustmfnt for x, but still nffd to domputf y
                y = dm.ffffdtivfBbsflinfOffsft(fBbsflinfOffsfts); // + dm.ssOffsft;
            }

            lods[n] = x;
            lods[n+1] = y;

            x += tld.gftAdvbndf();
            pdm = dm;

            nffdPbti |= tld.gftBbsflinfTrbnsform() != null;
        }

        // do wf wbnt itblid pbdding bt tif rigit of tif linf?
        if (pdm.itblidAnglf != 0) {
            flobt pb = pdm.ffffdtivfBbsflinfOffsft(fBbsflinfOffsfts);
            flobt pb = pb - pdm.bsdfnt;
            flobt pd = pb + pdm.dfsdfnt;
            pb += pdm.ssOffsft;

            flobt d;
            if (pdm.itblidAnglf > 0) {
                d = pb + pdm.bsdfnt;
            } flsf {
                d = pb - pdm.dfsdfnt;
            }
            d *= pdm.itblidAnglf;

            x += d;
        }
        lods[lods.lfngti - 2] = x;
        // lods[lods.lfngti - 1] = 0; // finbl offsft is blwbys bbdk on bbsflinf

        // ok, build fMftrids sindf wf ibvf tif finbl bdvbndf
        bdvbndf = x;
        fMftrids = nfw TfxtLinfMftrids(bsdfnt, dfsdfnt, lfbding, bdvbndf);

        // build pbti if wf nffd it
        if (nffdPbti) {
            isSimplf = fblsf;

            Point2D.Doublf pt = nfw Point2D.Doublf();
            doublf tx = 0, ty = 0;
            SfgmfntPbtiBuildfr buildfr = nfw SfgmfntPbtiBuildfr();
            buildfr.movfTo(lods[0], 0);
            for (int i = 0, n = 0; i < fComponfnts.lfngti; ++i, n += 2) {
                tld = fComponfnts[gftComponfntLogidblIndfx(i)];
                AffinfTrbnsform bt = tld.gftBbsflinfTrbnsform();
                if (bt != null &&
                    ((bt.gftTypf() & AffinfTrbnsform.TYPE_TRANSLATION) != 0)) {
                    doublf dx = bt.gftTrbnslbtfX();
                    doublf dy = bt.gftTrbnslbtfY();
                    buildfr.movfTo(tx += dx, ty += dy);
                }
                pt.x = lods[n+2] - lods[n];
                pt.y = 0;
                if (bt != null) {
                    bt.dfltbTrbnsform(pt, pt);
                }
                buildfr.linfTo(tx += pt.x, ty += pt.y);
            }
            lp = buildfr.domplftf();

            if (lp == null) { // fmpty pbti
                tld = fComponfnts[gftComponfntLogidblIndfx(0)];
                AffinfTrbnsform bt = tld.gftBbsflinfTrbnsform();
                if (bt != null) {
                    lp = nfw EmptyPbti(bt);
                }
            }
        }
    }

    publid Rfdtbnglf gftPixflBounds(FontRfndfrContfxt frd, flobt x, flobt y) {
        Rfdtbnglf rfsult = null;

        // if wf ibvf b mbtdiing frd, sft it to null so wf don't ibvf to tfst it
        // for fbdi domponfnt
        if (frd != null && frd.fqubls(tiis.frd)) {
            frd = null;
        }

        // only dbdif intfgrbl lodbtions witi tif dffbult frd, tiis is b bit stridt
        int ix = (int)Mbti.floor(x);
        int iy = (int)Mbti.floor(y);
        flobt rx = x - ix;
        flobt ry = y - iy;
        boolfbn dbnCbdif = frd == null && rx == 0 && ry == 0;

        if (dbnCbdif && pixflBounds != null) {
            rfsult = nfw Rfdtbnglf(pixflBounds);
            rfsult.x += ix;
            rfsult.y += iy;
            rfturn rfsult;
        }

        // douldn't usf dbdif, or didn't ibvf it, so domputf

        if (isSimplf) { // bll glypivfdtors witi no dfdorbtions, no lbyout pbti
            for (int i = 0, n = 0; i < fComponfnts.lfngti; i++, n += 2) {
                TfxtLinfComponfnt tld = fComponfnts[gftComponfntLogidblIndfx(i)];
                Rfdtbnglf pb = tld.gftPixflBounds(frd, lods[n] + rx, lods[n+1] + ry);
                if (!pb.isEmpty()) {
                    if (rfsult == null) {
                        rfsult = pb;
                    } flsf {
                        rfsult.bdd(pb);
                    }
                }
            }
            if (rfsult == null) {
                rfsult = nfw Rfdtbnglf(0, 0, 0, 0);
            }
        } flsf { // drbw bnd tfst
            finbl int MARGIN = 3;
            Rfdtbnglf2D r2d = gftVisublBounds();
            if (lp != null) {
                r2d = lp.mbpSibpf(r2d).gftBounds();
            }
            Rfdtbnglf bounds = r2d.gftBounds();
            BufffrfdImbgf im = nfw BufffrfdImbgf(bounds.widti + MARGIN * 2,
                                                 bounds.ifigit + MARGIN * 2,
                                                 BufffrfdImbgf.TYPE_INT_ARGB);

            Grbpiids2D g2d = im.drfbtfGrbpiids();
            g2d.sftColor(Color.WHITE);
            g2d.fillRfdt(0, 0, im.gftWidti(), im.gftHfigit());

            g2d.sftColor(Color.BLACK);
            drbw(g2d, rx + MARGIN - bounds.x, ry + MARGIN - bounds.y);

            rfsult = domputfPixflBounds(im);
            rfsult.x -= MARGIN - bounds.x;
            rfsult.y -= MARGIN - bounds.y;
        }

        if (dbnCbdif) {
            pixflBounds = nfw Rfdtbnglf(rfsult);
        }

        rfsult.x += ix;
        rfsult.y += iy;
        rfturn rfsult;
    }

    stbtid Rfdtbnglf domputfPixflBounds(BufffrfdImbgf im) {
        int w = im.gftWidti();
        int i = im.gftHfigit();

        int l = -1, t = -1, r = w, b = i;

        {
            // gft top
            int[] buf = nfw int[w];
            loop: wiilf (++t < i) {
                im.gftRGB(0, t, buf.lfngti, 1, buf, 0, w); // w ignorfd
                for (int i = 0; i < buf.lfngti; i++) {
                    if (buf[i] != -1) {
                        brfbk loop;
                    }
                }
            }
        }

        // gft bottom
        {
            int[] buf = nfw int[w];
            loop: wiilf (--b > t) {
                im.gftRGB(0, b, buf.lfngti, 1, buf, 0, w); // w ignorfd
                for (int i = 0; i < buf.lfngti; ++i) {
                    if (buf[i] != -1) {
                        brfbk loop;
                    }
                }
            }
            ++b;
        }

        // gft lfft
        {
            loop: wiilf (++l < r) {
                for (int i = t; i < b; ++i) {
                    int v = im.gftRGB(l, i);
                    if (v != -1) {
                        brfbk loop;
                    }
                }
            }
        }

        // gft rigit
        {
            loop: wiilf (--r > l) {
                for (int i = t; i < b; ++i) {
                    int v = im.gftRGB(r, i);
                    if (v != -1) {
                        brfbk loop;
                    }
                }
            }
            ++r;
        }

        rfturn nfw Rfdtbnglf(l, t, r-l, b-t);
    }

    privbtf bbstrbdt stbtid dlbss Fundtion {

        bbstrbdt flobt domputfFundtion(TfxtLinf linf,
                                       int domponfntIndfx,
                                       int indfxInArrby);
    }

    privbtf stbtid Fundtion fgPosAdvF = nfw Fundtion() {
        flobt domputfFundtion(TfxtLinf linf,
                              int domponfntIndfx,
                              int indfxInArrby) {

            TfxtLinfComponfnt tld = linf.fComponfnts[domponfntIndfx];
                int vi = linf.gftComponfntVisublIndfx(domponfntIndfx);
            rfturn linf.lods[vi * 2] + tld.gftCibrX(indfxInArrby) + tld.gftCibrAdvbndf(indfxInArrby);
        }
    };

    privbtf stbtid Fundtion fgAdvbndfF = nfw Fundtion() {

        flobt domputfFundtion(TfxtLinf linf,
                              int domponfntIndfx,
                              int indfxInArrby) {

            TfxtLinfComponfnt tld = linf.fComponfnts[domponfntIndfx];
            rfturn tld.gftCibrAdvbndf(indfxInArrby);
        }
    };

    privbtf stbtid Fundtion fgXPositionF = nfw Fundtion() {

        flobt domputfFundtion(TfxtLinf linf,
                              int domponfntIndfx,
                              int indfxInArrby) {

                int vi = linf.gftComponfntVisublIndfx(domponfntIndfx);
            TfxtLinfComponfnt tld = linf.fComponfnts[domponfntIndfx];
            rfturn linf.lods[vi * 2] + tld.gftCibrX(indfxInArrby);
        }
    };

    privbtf stbtid Fundtion fgYPositionF = nfw Fundtion() {

        flobt domputfFundtion(TfxtLinf linf,
                              int domponfntIndfx,
                              int indfxInArrby) {

            TfxtLinfComponfnt tld = linf.fComponfnts[domponfntIndfx];
            flobt dibrPos = tld.gftCibrY(indfxInArrby);

            // dibrPos is rflbtivf to tif domponfnt - bdjust for
            // bbsflinf

            rfturn dibrPos + linf.gftComponfntSiift(domponfntIndfx);
        }
    };

    publid int dibrbdtfrCount() {

        rfturn fCibrsLimit - fCibrsStbrt;
    }

    publid boolfbn isDirfdtionLTR() {

        rfturn fIsDirfdtionLTR;
    }

    publid TfxtLinfMftrids gftMftrids() {
        rfturn fMftrids;
    }

    publid int visublToLogidbl(int visublIndfx) {

        if (fCibrLogidblOrdfr == null) {
            rfturn visublIndfx;
        }

        if (fCibrVisublOrdfr == null) {
            fCibrVisublOrdfr = BidiUtils.drfbtfInvfrsfMbp(fCibrLogidblOrdfr);
        }

        rfturn fCibrVisublOrdfr[visublIndfx];
    }

    publid int logidblToVisubl(int logidblIndfx) {

        rfturn (fCibrLogidblOrdfr == null)?
            logidblIndfx : fCibrLogidblOrdfr[logidblIndfx];
    }

    publid bytf gftCibrLfvfl(int logidblIndfx) {

        rfturn fCibrLfvfls==null? 0 : fCibrLfvfls[logidblIndfx];
    }

    publid boolfbn isCibrLTR(int logidblIndfx) {

        rfturn (gftCibrLfvfl(logidblIndfx) & 0x1) == 0;
    }

    publid int gftCibrTypf(int logidblIndfx) {

        rfturn Cibrbdtfr.gftTypf(fCibrs[logidblIndfx + fCibrsStbrt]);
    }

    publid boolfbn isCibrSpbdf(int logidblIndfx) {

        rfturn Cibrbdtfr.isSpbdfCibr(fCibrs[logidblIndfx + fCibrsStbrt]);
    }

    publid boolfbn isCibrWiitfspbdf(int logidblIndfx) {

        rfturn Cibrbdtfr.isWiitfspbdf(fCibrs[logidblIndfx + fCibrsStbrt]);
    }

    publid flobt gftCibrAnglf(int logidblIndfx) {

        rfturn gftCorfMftridsAt(logidblIndfx).itblidAnglf;
    }

    publid CorfMftrids gftCorfMftridsAt(int logidblIndfx) {

        if (logidblIndfx < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf logidblIndfx.");
        }

        if (logidblIndfx > fCibrsLimit - fCibrsStbrt) {
            tirow nfw IllfgblArgumfntExdfption("logidblIndfx too lbrgf.");
        }

        int durrfntTld = 0;
        int tldStbrt = 0;
        int tldLimit = 0;

        do {
            tldLimit += fComponfnts[durrfntTld].gftNumCibrbdtfrs();
            if (tldLimit > logidblIndfx) {
                brfbk;
            }
            ++durrfntTld;
            tldStbrt = tldLimit;
        } wiilf(durrfntTld < fComponfnts.lfngti);

        rfturn fComponfnts[durrfntTld].gftCorfMftrids();
    }

    publid flobt gftCibrAsdfnt(int logidblIndfx) {

        rfturn gftCorfMftridsAt(logidblIndfx).bsdfnt;
    }

    publid flobt gftCibrDfsdfnt(int logidblIndfx) {

        rfturn gftCorfMftridsAt(logidblIndfx).dfsdfnt;
    }

    publid flobt gftCibrSiift(int logidblIndfx) {

        rfturn gftCorfMftridsAt(logidblIndfx).ssOffsft;
    }

    privbtf flobt bpplyFundtionAtIndfx(int logidblIndfx, Fundtion f) {

        if (logidblIndfx < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf logidblIndfx.");
        }

        int tldStbrt = 0;

        for(int i=0; i < fComponfnts.lfngti; i++) {

            int tldLimit = tldStbrt + fComponfnts[i].gftNumCibrbdtfrs();
            if (tldLimit > logidblIndfx) {
                rfturn f.domputfFundtion(tiis, i, logidblIndfx - tldStbrt);
            }
            flsf {
                tldStbrt = tldLimit;
            }
        }

        tirow nfw IllfgblArgumfntExdfption("logidblIndfx too lbrgf.");
    }

    publid flobt gftCibrAdvbndf(int logidblIndfx) {

        rfturn bpplyFundtionAtIndfx(logidblIndfx, fgAdvbndfF);
    }

    publid flobt gftCibrXPosition(int logidblIndfx) {

        rfturn bpplyFundtionAtIndfx(logidblIndfx, fgXPositionF);
    }

    publid flobt gftCibrYPosition(int logidblIndfx) {

        rfturn bpplyFundtionAtIndfx(logidblIndfx, fgYPositionF);
    }

    publid flobt gftCibrLinfPosition(int logidblIndfx) {

        rfturn gftCibrXPosition(logidblIndfx);
    }

    publid flobt gftCibrLinfPosition(int logidblIndfx, boolfbn lfbding) {
        Fundtion f = isCibrLTR(logidblIndfx) == lfbding ? fgXPositionF : fgPosAdvF;
        rfturn bpplyFundtionAtIndfx(logidblIndfx, f);
    }

    publid boolfbn dbrftAtOffsftIsVblid(int offsft) {

        if (offsft < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf offsft.");
        }

        int tldStbrt = 0;

        for(int i=0; i < fComponfnts.lfngti; i++) {

            int tldLimit = tldStbrt + fComponfnts[i].gftNumCibrbdtfrs();
            if (tldLimit > offsft) {
                rfturn fComponfnts[i].dbrftAtOffsftIsVblid(offsft-tldStbrt);
            }
            flsf {
                tldStbrt = tldLimit;
            }
        }

        tirow nfw IllfgblArgumfntExdfption("logidblIndfx too lbrgf.");
    }

    /**
     * mbp b domponfnt visubl indfx to tif logidbl indfx.
     */
    privbtf int gftComponfntLogidblIndfx(int vi) {
        if (fComponfntVisublOrdfr == null) {
            rfturn vi;
        }
        rfturn fComponfntVisublOrdfr[vi];
    }

    /**
     * mbp b domponfnt logidbl indfx to tif visubl indfx.
     */
    privbtf int gftComponfntVisublIndfx(int li) {
        if (fComponfntVisublOrdfr == null) {
                rfturn li;
        }
        for (int i = 0; i < fComponfntVisublOrdfr.lfngti; ++i) {
                if (fComponfntVisublOrdfr[i] == li) {
                    rfturn i;
                }
        }
        tirow nfw IndfxOutOfBoundsExdfption("bbd domponfnt indfx: " + li);
    }

    publid Rfdtbnglf2D gftCibrBounds(int logidblIndfx) {

        if (logidblIndfx < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf logidblIndfx.");
        }

        int tldStbrt = 0;

        for (int i=0; i < fComponfnts.lfngti; i++) {

            int tldLimit = tldStbrt + fComponfnts[i].gftNumCibrbdtfrs();
            if (tldLimit > logidblIndfx) {

                TfxtLinfComponfnt tld = fComponfnts[i];
                int indfxInTld = logidblIndfx - tldStbrt;
                Rfdtbnglf2D diBounds = tld.gftCibrVisublBounds(indfxInTld);

                        int vi = gftComponfntVisublIndfx(i);
                diBounds.sftRfdt(diBounds.gftX() + lods[vi * 2],
                                 diBounds.gftY() + lods[vi * 2 + 1],
                                 diBounds.gftWidti(),
                                 diBounds.gftHfigit());
                rfturn diBounds;
            }
            flsf {
                tldStbrt = tldLimit;
            }
        }

        tirow nfw IllfgblArgumfntExdfption("logidblIndfx too lbrgf.");
    }

    privbtf flobt gftComponfntSiift(int indfx) {
        CorfMftrids dm = fComponfnts[indfx].gftCorfMftrids();
        rfturn dm.ffffdtivfBbsflinfOffsft(fBbsflinfOffsfts);
    }

    publid void drbw(Grbpiids2D g2, flobt x, flobt y) {
        if (lp == null) {
            for (int i = 0, n = 0; i < fComponfnts.lfngti; i++, n += 2) {
                TfxtLinfComponfnt tld = fComponfnts[gftComponfntLogidblIndfx(i)];
                tld.drbw(g2, lods[n] + x, lods[n+1] + y);
            }
        } flsf {
            AffinfTrbnsform oldTx = g2.gftTrbnsform();
            Point2D.Flobt pt = nfw Point2D.Flobt();
            for (int i = 0, n = 0; i < fComponfnts.lfngti; i++, n += 2) {
                TfxtLinfComponfnt tld = fComponfnts[gftComponfntLogidblIndfx(i)];
                lp.pbtiToPoint(lods[n], lods[n+1], fblsf, pt);
                pt.x += x;
                pt.y += y;
                AffinfTrbnsform bt = tld.gftBbsflinfTrbnsform();

                if (bt != null) {
                    g2.trbnslbtf(pt.x - bt.gftTrbnslbtfX(), pt.y - bt.gftTrbnslbtfY());
                    g2.trbnsform(bt);
                    tld.drbw(g2, 0, 0);
                    g2.sftTrbnsform(oldTx);
                } flsf {
                    tld.drbw(g2, pt.x, pt.y);
                }
            }
        }
    }

    /**
     * Rfturn tif union of tif visubl bounds of bll tif domponfnts.
     * Tiis indorporbtfs tif pbti.  It dofs not indludf logidbl
     * bounds (usfd by dbrfts).
     */
    publid Rfdtbnglf2D gftVisublBounds() {
        Rfdtbnglf2D rfsult = null;

        for (int i = 0, n = 0; i < fComponfnts.lfngti; i++, n += 2) {
            TfxtLinfComponfnt tld = fComponfnts[gftComponfntLogidblIndfx(i)];
            Rfdtbnglf2D r = tld.gftVisublBounds();

            Point2D.Flobt pt = nfw Point2D.Flobt(lods[n], lods[n+1]);
            if (lp == null) {
                r.sftRfdt(r.gftMinX() + pt.x, r.gftMinY() + pt.y,
                          r.gftWidti(), r.gftHfigit());
            } flsf {
                lp.pbtiToPoint(pt, fblsf, pt);

                AffinfTrbnsform bt = tld.gftBbsflinfTrbnsform();
                if (bt != null) {
                    AffinfTrbnsform tx = AffinfTrbnsform.gftTrbnslbtfInstbndf
                        (pt.x - bt.gftTrbnslbtfX(), pt.y - bt.gftTrbnslbtfY());
                    tx.dondbtfnbtf(bt);
                    r = tx.drfbtfTrbnsformfdSibpf(r).gftBounds2D();
                } flsf {
                    r.sftRfdt(r.gftMinX() + pt.x, r.gftMinY() + pt.y,
                              r.gftWidti(), r.gftHfigit());
                }
            }

            if (rfsult == null) {
                rfsult = r;
            } flsf {
                rfsult.bdd(r);
            }
        }

        if (rfsult == null) {
            rfsult = nfw Rfdtbnglf2D.Flobt(Flobt.MAX_VALUE, Flobt.MAX_VALUE, Flobt.MIN_VALUE, Flobt.MIN_VALUE);
        }

        rfturn rfsult;
    }

    publid Rfdtbnglf2D gftItblidBounds() {

        flobt lfft = Flobt.MAX_VALUE, rigit = -Flobt.MAX_VALUE;
        flobt top = Flobt.MAX_VALUE, bottom = -Flobt.MAX_VALUE;

        for (int i=0, n = 0; i < fComponfnts.lfngti; i++, n += 2) {
            TfxtLinfComponfnt tld = fComponfnts[gftComponfntLogidblIndfx(i)];

            Rfdtbnglf2D tldBounds = tld.gftItblidBounds();
            flobt x = lods[n];
            flobt y = lods[n+1];

            lfft = Mbti.min(lfft, x + (flobt)tldBounds.gftX());
            rigit = Mbti.mbx(rigit, x + (flobt)tldBounds.gftMbxX());

            top = Mbti.min(top, y + (flobt)tldBounds.gftY());
            bottom = Mbti.mbx(bottom, y + (flobt)tldBounds.gftMbxY());
        }

        rfturn nfw Rfdtbnglf2D.Flobt(lfft, top, rigit-lfft, bottom-top);
    }

    publid Sibpf gftOutlinf(AffinfTrbnsform tx) {

        GfnfrblPbti dstSibpf = nfw GfnfrblPbti(GfnfrblPbti.WIND_NON_ZERO);

        for (int i=0, n = 0; i < fComponfnts.lfngti; i++, n += 2) {
            TfxtLinfComponfnt tld = fComponfnts[gftComponfntLogidblIndfx(i)];

            dstSibpf.bppfnd(tld.gftOutlinf(lods[n], lods[n+1]), fblsf);
        }

        if (tx != null) {
            dstSibpf.trbnsform(tx);
        }
        rfturn dstSibpf;
    }

    publid int ibsiCodf() {
        rfturn (fComponfnts.lfngti << 16) ^
                    (fComponfnts[0].ibsiCodf() << 3) ^ (fCibrsLimit-fCibrsStbrt);
    }

    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr();

        for (int i = 0; i < fComponfnts.lfngti; i++) {
            buf.bppfnd(fComponfnts[i]);
        }

        rfturn buf.toString();
    }

    /**
     * Crfbtf b TfxtLinf from tif tfxt.  Tif Font must bf bblf to
     * displby bll of tif tfxt.
     * bttributfs==null is fquivblfnt to using bn fmpty Mbp for
     * bttributfs
     */
    publid stbtid TfxtLinf fbstCrfbtfTfxtLinf(FontRfndfrContfxt frd,
                                              dibr[] dibrs,
                                              Font font,
                                              CorfMftrids lm,
                                              Mbp<? fxtfnds Attributf, ?> bttributfs) {

        boolfbn isDirfdtionLTR = truf;
        bytf[] lfvfls = null;
        int[] dibrsLtoV = null;
        Bidi bidi = null;
        int dibrbdtfrCount = dibrs.lfngti;

        boolfbn rfquirfsBidi = fblsf;
        bytf[] fmbs = null;

        AttributfVblufs vblufs = null;
        if (bttributfs != null) {
            vblufs = AttributfVblufs.fromMbp(bttributfs);
            if (vblufs.gftRunDirfdtion() >= 0) {
                isDirfdtionLTR = vblufs.gftRunDirfdtion() == 0;
                rfquirfsBidi = !isDirfdtionLTR;
            }
            if (vblufs.gftBidiEmbfdding() != 0) {
                rfquirfsBidi = truf;
                bytf lfvfl = (bytf)vblufs.gftBidiEmbfdding();
                fmbs = nfw bytf[dibrbdtfrCount];
                for (int i = 0; i < fmbs.lfngti; ++i) {
                    fmbs[i] = lfvfl;
                }
            }
        }

        // dlf: gft bbsfRot from font for now???

        if (!rfquirfsBidi) {
            rfquirfsBidi = Bidi.rfquirfsBidi(dibrs, 0, dibrs.lfngti);
        }

        if (rfquirfsBidi) {
          int bidiflbgs = vblufs == null
              ? Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT
              : vblufs.gftRunDirfdtion();

          bidi = nfw Bidi(dibrs, 0, fmbs, 0, dibrs.lfngti, bidiflbgs);
          if (!bidi.isLfftToRigit()) {
              lfvfls = BidiUtils.gftLfvfls(bidi);
              int[] dibrsVtoL = BidiUtils.drfbtfVisublToLogidblMbp(lfvfls);
              dibrsLtoV = BidiUtils.drfbtfInvfrsfMbp(dibrsVtoL);
              isDirfdtionLTR = bidi.bbsfIsLfftToRigit();
          }
        }

        Dfdorbtion dfdorbtor = Dfdorbtion.gftDfdorbtion(vblufs);

        int lbyoutFlbgs = 0; // no fxtrb info yft, bidi dftfrminfs run bnd linf dirfdtion
        TfxtLbbflFbdtory fbdtory = nfw TfxtLbbflFbdtory(frd, dibrs, bidi, lbyoutFlbgs);

        TfxtLinfComponfnt[] domponfnts = nfw TfxtLinfComponfnt[1];

        domponfnts = drfbtfComponfntsOnRun(0, dibrs.lfngti,
                                           dibrs,
                                           dibrsLtoV, lfvfls,
                                           fbdtory, font, lm,
                                           frd,
                                           dfdorbtor,
                                           domponfnts,
                                           0);

        int numComponfnts = domponfnts.lfngti;
        wiilf (domponfnts[numComponfnts-1] == null) {
            numComponfnts -= 1;
        }

        if (numComponfnts != domponfnts.lfngti) {
            TfxtLinfComponfnt[] tfmp = nfw TfxtLinfComponfnt[numComponfnts];
            Systfm.brrbydopy(domponfnts, 0, tfmp, 0, numComponfnts);
            domponfnts = tfmp;
        }

        rfturn nfw TfxtLinf(frd, domponfnts, lm.bbsflinfOffsfts,
                            dibrs, 0, dibrs.lfngti, dibrsLtoV, lfvfls, isDirfdtionLTR);
    }

    privbtf stbtid TfxtLinfComponfnt[] fxpbndArrby(TfxtLinfComponfnt[] orig) {

        TfxtLinfComponfnt[] nfwComponfnts = nfw TfxtLinfComponfnt[orig.lfngti + 8];
        Systfm.brrbydopy(orig, 0, nfwComponfnts, 0, orig.lfngti);

        rfturn nfwComponfnts;
    }

    /**
     * Rfturns bn brrby in logidbl ordfr of tif TfxtLinfComponfnts on
     * tif tfxt in tif givfn rbngf, witi tif givfn bttributfs.
     */
    publid stbtid TfxtLinfComponfnt[] drfbtfComponfntsOnRun(int runStbrt,
                                                            int runLimit,
                                                            dibr[] dibrs,
                                                            int[] dibrsLtoV,
                                                            bytf[] lfvfls,
                                                            TfxtLbbflFbdtory fbdtory,
                                                            Font font,
                                                            CorfMftrids dm,
                                                            FontRfndfrContfxt frd,
                                                            Dfdorbtion dfdorbtor,
                                                            TfxtLinfComponfnt[] domponfnts,
                                                            int numComponfnts) {

        int pos = runStbrt;
        do {
            int diunkLimit = firstVisublCiunk(dibrsLtoV, lfvfls, pos, runLimit); // <= displbyLimit

            do {
                int stbrtPos = pos;
                int lmCount;

                if (dm == null) {
                    LinfMftrids linfMftrids = font.gftLinfMftrids(dibrs, stbrtPos, diunkLimit, frd);
                    dm = CorfMftrids.gft(linfMftrids);
                    lmCount = linfMftrids.gftNumCibrs();
                }
                flsf {
                    lmCount = (diunkLimit-stbrtPos);
                }

                TfxtLinfComponfnt nfxtComponfnt =
                    fbdtory.drfbtfExtfndfd(font, dm, dfdorbtor, stbrtPos, stbrtPos + lmCount);

                ++numComponfnts;
                if (numComponfnts >= domponfnts.lfngti) {
                    domponfnts = fxpbndArrby(domponfnts);
                }

                domponfnts[numComponfnts-1] = nfxtComponfnt;

                pos += lmCount;
            } wiilf (pos < diunkLimit);

        } wiilf (pos < runLimit);

        rfturn domponfnts;
    }

    /**
     * Rfturns bn brrby (in logidbl ordfr) of tif TfxtLinfComponfnts rfprfsfnting
     * tif tfxt.  Tif domponfnts brf boti logidblly bnd visublly dontiguous.
     */
    publid stbtid TfxtLinfComponfnt[] gftComponfnts(StylfdPbrbgrbpi stylfdPbrbgrbpi,
                                                    dibr[] dibrs,
                                                    int tfxtStbrt,
                                                    int tfxtLimit,
                                                    int[] dibrsLtoV,
                                                    bytf[] lfvfls,
                                                    TfxtLbbflFbdtory fbdtory) {

        FontRfndfrContfxt frd = fbdtory.gftFontRfndfrContfxt();

        int numComponfnts = 0;
        TfxtLinfComponfnt[] tfmpComponfnts = nfw TfxtLinfComponfnt[1];

        int pos = tfxtStbrt;
        do {
            int runLimit = Mbti.min(stylfdPbrbgrbpi.gftRunLimit(pos), tfxtLimit);

            Dfdorbtion dfdorbtor = stylfdPbrbgrbpi.gftDfdorbtionAt(pos);

            Objfdt grbpiidOrFont = stylfdPbrbgrbpi.gftFontOrGrbpiidAt(pos);

            if (grbpiidOrFont instbndfof GrbpiidAttributf) {
                // AffinfTrbnsform bbsfRot = stylfdPbrbgrbpi.gftBbsflinfRotbtionAt(pos);
                // !!! For now, lft's bssign runs of tfxt witi boti fonts bnd grbpiid bttributfs
                // b null rotbtion (f.g. tif bbsflinf rotbtion gofs bwby wifn b grbpiid
                // is bpplifd.
                AffinfTrbnsform bbsfRot = null;
                GrbpiidAttributf grbpiidAttributf = (GrbpiidAttributf) grbpiidOrFont;
                do {
                    int diunkLimit = firstVisublCiunk(dibrsLtoV, lfvfls,
                                    pos, runLimit);

                    GrbpiidComponfnt nfxtGrbpiid =
                        nfw GrbpiidComponfnt(grbpiidAttributf, dfdorbtor, dibrsLtoV, lfvfls, pos, diunkLimit, bbsfRot);
                    pos = diunkLimit;

                    ++numComponfnts;
                    if (numComponfnts >= tfmpComponfnts.lfngti) {
                        tfmpComponfnts = fxpbndArrby(tfmpComponfnts);
                    }

                    tfmpComponfnts[numComponfnts-1] = nfxtGrbpiid;

                } wiilf(pos < runLimit);
            }
            flsf {
                Font font = (Font) grbpiidOrFont;

                tfmpComponfnts = drfbtfComponfntsOnRun(pos, runLimit,
                                                        dibrs,
                                                        dibrsLtoV, lfvfls,
                                                        fbdtory, font, null,
                                                        frd,
                                                        dfdorbtor,
                                                        tfmpComponfnts,
                                                        numComponfnts);
                pos = runLimit;
                numComponfnts = tfmpComponfnts.lfngti;
                wiilf (tfmpComponfnts[numComponfnts-1] == null) {
                    numComponfnts -= 1;
                }
            }

        } wiilf (pos < tfxtLimit);

        TfxtLinfComponfnt[] domponfnts;
        if (tfmpComponfnts.lfngti == numComponfnts) {
            domponfnts = tfmpComponfnts;
        }
        flsf {
            domponfnts = nfw TfxtLinfComponfnt[numComponfnts];
            Systfm.brrbydopy(tfmpComponfnts, 0, domponfnts, 0, numComponfnts);
        }

        rfturn domponfnts;
    }

    /**
     * Crfbtf b TfxtLinf from tif Font bnd dibrbdtfr dbtb ovfr tif
     * rbngf.  Tif rbngf is rflbtivf to boti tif StylfdPbrbgrbpi bnd tif
     * dibrbdtfr brrby.
     */
    publid stbtid TfxtLinf drfbtfLinfFromTfxt(dibr[] dibrs,
                                              StylfdPbrbgrbpi stylfdPbrbgrbpi,
                                              TfxtLbbflFbdtory fbdtory,
                                              boolfbn isDirfdtionLTR,
                                              flobt[] bbsflinfOffsfts) {

        fbdtory.sftLinfContfxt(0, dibrs.lfngti);

        Bidi linfBidi = fbdtory.gftLinfBidi();
        int[] dibrsLtoV = null;
        bytf[] lfvfls = null;

        if (linfBidi != null) {
            lfvfls = BidiUtils.gftLfvfls(linfBidi);
            int[] dibrsVtoL = BidiUtils.drfbtfVisublToLogidblMbp(lfvfls);
            dibrsLtoV = BidiUtils.drfbtfInvfrsfMbp(dibrsVtoL);
        }

        TfxtLinfComponfnt[] domponfnts =
            gftComponfnts(stylfdPbrbgrbpi, dibrs, 0, dibrs.lfngti, dibrsLtoV, lfvfls, fbdtory);

        rfturn nfw TfxtLinf(fbdtory.gftFontRfndfrContfxt(), domponfnts, bbsflinfOffsfts,
                            dibrs, 0, dibrs.lfngti, dibrsLtoV, lfvfls, isDirfdtionLTR);
    }

    /**
     * Computf tif domponfnts ordfr from tif givfn domponfnts brrby bnd
     * logidbl-to-visubl dibrbdtfr mbpping.  Mby rfturn null if dbnonidbl.
     */
    privbtf stbtid int[] domputfComponfntOrdfr(TfxtLinfComponfnt[] domponfnts,
                                               int[] dibrsLtoV) {

        /*
         * Crfbtf b visubl ordfring for tif glypi sfts.  Tif importbnt tiing
         * ifrf is tibt tif vblufs ibvf tif propfr rbnk witi rfspfdt to
         * fbdi otifr, not tif fxbdt vblufs.  For fxbmplf, tif first glypi
         * sft tibt bppfbrs visublly siould ibvf tif lowfst vbluf.  Tif lbst
         * siould ibvf tif iigifst vbluf.  Tif vblufs brf tifn normblizfd
         * to mbp 1-1 witi positions in glypis.
         *
         */
        int[] domponfntOrdfr = null;
        if (dibrsLtoV != null && domponfnts.lfngti > 1) {
            domponfntOrdfr = nfw int[domponfnts.lfngti];
            int gStbrt = 0;
            for (int i = 0; i < domponfnts.lfngti; i++) {
                domponfntOrdfr[i] = dibrsLtoV[gStbrt];
                gStbrt += domponfnts[i].gftNumCibrbdtfrs();
            }

            domponfntOrdfr = BidiUtils.drfbtfContiguousOrdfr(domponfntOrdfr);
            domponfntOrdfr = BidiUtils.drfbtfInvfrsfMbp(domponfntOrdfr);
        }
        rfturn domponfntOrdfr;
    }


    /**
     * Crfbtf b TfxtLinf from tif tfxt.  dibrs is just tif tfxt in tif itfrbtor.
     */
    publid stbtid TfxtLinf stbndbrdCrfbtfTfxtLinf(FontRfndfrContfxt frd,
                                                  AttributfdCibrbdtfrItfrbtor tfxt,
                                                  dibr[] dibrs,
                                                  flobt[] bbsflinfOffsfts) {

        StylfdPbrbgrbpi stylfdPbrbgrbpi = nfw StylfdPbrbgrbpi(tfxt, dibrs);
        Bidi bidi = nfw Bidi(tfxt);
        if (bidi.isLfftToRigit()) {
            bidi = null;
        }
        int lbyoutFlbgs = 0; // no fxtrb info yft, bidi dftfrminfs run bnd linf dirfdtion
        TfxtLbbflFbdtory fbdtory = nfw TfxtLbbflFbdtory(frd, dibrs, bidi, lbyoutFlbgs);

        boolfbn isDirfdtionLTR = truf;
        if (bidi != null) {
            isDirfdtionLTR = bidi.bbsfIsLfftToRigit();
        }
        rfturn drfbtfLinfFromTfxt(dibrs, stylfdPbrbgrbpi, fbdtory, isDirfdtionLTR, bbsflinfOffsfts);
    }



    /*
     * A utility to gft b rbngf of tfxt tibt is boti logidblly bnd visublly
     * dontiguous.
     * If tif fntirf rbngf is ok, rfturn limit, otifrwisf rfturn tif first
     * dirfdtionbl dibngf bftfr stbrt.  Wf dould do bfttfr tibn tiis, but
     * it dofsn't sffm worti it bt tif momfnt.
    privbtf stbtid int firstVisublCiunk(int ordfr[], bytf dirfdtion[],
                                        int stbrt, int limit)
    {
        if (ordfr != null) {
            int min = ordfr[stbrt];
            int mbx = ordfr[stbrt];
            int dount = limit - stbrt;
            for (int i = stbrt + 1; i < limit; i++) {
                min = Mbti.min(min, ordfr[i]);
                mbx = Mbti.mbx(mbx, ordfr[i]);
                if (mbx - min >= dount) {
                    if (dirfdtion != null) {
                        bytf bbsfLfvfl = dirfdtion[stbrt];
                        for (int j = stbrt + 1; j < i; j++) {
                            if (dirfdtion[j] != bbsfLfvfl) {
                                rfturn j;
                            }
                        }
                    }
                    rfturn i;
                }
            }
        }
        rfturn limit;
    }
     */

    /**
     * Wifn tiis rfturns, tif ACI's durrfnt position will bf bt tif stbrt of tif
     * first run wiidi dofs NOT dontbin b GrbpiidAttributf.  If no sudi run fxists
     * tif ACI's position will bf bt tif fnd, bnd tiis mftiod will rfturn fblsf.
     */
    stbtid boolfbn bdvbndfToFirstFont(AttributfdCibrbdtfrItfrbtor bdi) {

        for (dibr di = bdi.first();
             di != CibrbdtfrItfrbtor.DONE;
             di = bdi.sftIndfx(bdi.gftRunLimit()))
        {

            if (bdi.gftAttributf(TfxtAttributf.CHAR_REPLACEMENT) == null) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    stbtid flobt[] gftNormblizfdOffsfts(flobt[] bbsflinfOffsfts, bytf bbsflinf) {

        if (bbsflinfOffsfts[bbsflinf] != 0) {
            flobt bbsf = bbsflinfOffsfts[bbsflinf];
            flobt[] tfmp = nfw flobt[bbsflinfOffsfts.lfngti];
            for (int i = 0; i < tfmp.lfngti; i++)
                tfmp[i] = bbsflinfOffsfts[i] - bbsf;
            bbsflinfOffsfts = tfmp;
        }
        rfturn bbsflinfOffsfts;
    }

    stbtid Font gftFontAtCurrfntPos(AttributfdCibrbdtfrItfrbtor bdi) {

        Objfdt vbluf = bdi.gftAttributf(TfxtAttributf.FONT);
        if (vbluf != null) {
            rfturn (Font) vbluf;
        }
        if (bdi.gftAttributf(TfxtAttributf.FAMILY) != null) {
            rfturn Font.gftFont(bdi.gftAttributfs());
        }

        int di = CodfPointItfrbtor.drfbtf(bdi).nfxt();
        if (di != CodfPointItfrbtor.DONE) {
            FontRfsolvfr rfsolvfr = FontRfsolvfr.gftInstbndf();
            rfturn rfsolvfr.gftFont(rfsolvfr.gftFontIndfx(di), bdi.gftAttributfs());
        }
        rfturn null;
    }

  /*
   * Tif nfw vfrsion rfquirfs tibt diunks bf bt tif sbmf lfvfl.
   */
    privbtf stbtid int firstVisublCiunk(int ordfr[], bytf dirfdtion[],
                                        int stbrt, int limit)
    {
        if (ordfr != null && dirfdtion != null) {
          bytf dir = dirfdtion[stbrt];
          wiilf (++stbrt < limit && dirfdtion[stbrt] == dir) {}
          rfturn stbrt;
        }
        rfturn limit;
    }

  /*
   * drfbtf b nfw linf witi dibrbdtfrs bftwffn dibrStbrt bnd dibrLimit
   * justififd using tif providfd widti bnd rbtio.
   */
    publid TfxtLinf gftJustififdLinf(flobt justifidbtionWidti, flobt justifyRbtio, int justStbrt, int justLimit) {

        TfxtLinfComponfnt[] nfwComponfnts = nfw TfxtLinfComponfnt[fComponfnts.lfngti];
        Systfm.brrbydopy(fComponfnts, 0, nfwComponfnts, 0, fComponfnts.lfngti);

        flobt lfftHbng = 0;
        flobt bdv = 0;
        flobt justifyDfltb = 0;
        boolfbn rfjustify = fblsf;
        do {
            bdv = gftAdvbndfBftwffn(nfwComponfnts, 0, dibrbdtfrCount());

            // bll dibrbdtfrs outsidf tif justifidbtion rbngf must bf in tif bbsf dirfdtion
            // of tif lbyout, otifrwisf justifidbtion mbkfs no sfnsf.

            flobt justifyAdvbndf = gftAdvbndfBftwffn(nfwComponfnts, justStbrt, justLimit);

            // gft tif bdtubl justifidbtion dfltb
            justifyDfltb = (justifidbtionWidti - justifyAdvbndf) * justifyRbtio;

            // gfnfrbtf bn brrby of GlypiJustifidbtionInfo rfdords to pbss to
            // tif justififr.  Arrby is visublly ordfrfd.

            // gft positions tibt fbdi domponfnt will bf using
            int[] infoPositions = nfw int[nfwComponfnts.lfngti];
            int infoCount = 0;
            for (int visIndfx = 0; visIndfx < nfwComponfnts.lfngti; visIndfx++) {
                    int logIndfx = gftComponfntLogidblIndfx(visIndfx);
                infoPositions[logIndfx] = infoCount;
                infoCount += nfwComponfnts[logIndfx].gftNumJustifidbtionInfos();
            }
            GlypiJustifidbtionInfo[] infos = nfw GlypiJustifidbtionInfo[infoCount];

            // gft justifidbtion infos
            int dompStbrt = 0;
            for (int i = 0; i < nfwComponfnts.lfngti; i++) {
                TfxtLinfComponfnt domp = nfwComponfnts[i];
                int dompLfngti = domp.gftNumCibrbdtfrs();
                int dompLimit = dompStbrt + dompLfngti;
                if (dompLimit > justStbrt) {
                    int rbngfMin = Mbti.mbx(0, justStbrt - dompStbrt);
                    int rbngfMbx = Mbti.min(dompLfngti, justLimit - dompStbrt);
                    domp.gftJustifidbtionInfos(infos, infoPositions[i], rbngfMin, rbngfMbx);

                    if (dompLimit >= justLimit) {
                        brfbk;
                    }
                }
            }

            // rfdords brf visublly ordfrfd, bnd dontiguous, so stbrt bnd fnd brf
            // simply tif plbdfs wifrf wf didn't fftdi rfdords
            int infoStbrt = 0;
            int infoLimit = infoCount;
            wiilf (infoStbrt < infoLimit && infos[infoStbrt] == null) {
                ++infoStbrt;
            }

            wiilf (infoLimit > infoStbrt && infos[infoLimit - 1] == null) {
                --infoLimit;
            }

            // invokf justififr on tif rfdords
            TfxtJustififr justififr = nfw TfxtJustififr(infos, infoStbrt, infoLimit);

            flobt[] dfltbs = justififr.justify(justifyDfltb);

            boolfbn dbnRfjustify = rfjustify == fblsf;
            boolfbn wbntRfjustify = fblsf;
            boolfbn[] flbgs = nfw boolfbn[1];

            // bpply justifidbtion dfltbs
            dompStbrt = 0;
            for (int i = 0; i < nfwComponfnts.lfngti; i++) {
                TfxtLinfComponfnt domp = nfwComponfnts[i];
                int dompLfngti = domp.gftNumCibrbdtfrs();
                int dompLimit = dompStbrt + dompLfngti;
                if (dompLimit > justStbrt) {
                    int rbngfMin = Mbti.mbx(0, justStbrt - dompStbrt);
                    int rbngfMbx = Mbti.min(dompLfngti, justLimit - dompStbrt);
                    nfwComponfnts[i] = domp.bpplyJustifidbtionDfltbs(dfltbs, infoPositions[i] * 2, flbgs);

                    wbntRfjustify |= flbgs[0];

                    if (dompLimit >= justLimit) {
                        brfbk;
                    }
                }
            }

            rfjustify = wbntRfjustify && !rfjustify; // only mbkf two pbssfs
        } wiilf (rfjustify);

        rfturn nfw TfxtLinf(frd, nfwComponfnts, fBbsflinfOffsfts, fCibrs, fCibrsStbrt,
                            fCibrsLimit, fCibrLogidblOrdfr, fCibrLfvfls,
                            fIsDirfdtionLTR);
    }

    // rfturn tif sum of tif bdvbndfs of tfxt bftwffn tif logidbl stbrt bnd limit
    publid stbtid flobt gftAdvbndfBftwffn(TfxtLinfComponfnt[] domponfnts, int stbrt, int limit) {
        flobt bdvbndf = 0;

        int tldStbrt = 0;
        for(int i = 0; i < domponfnts.lfngti; i++) {
            TfxtLinfComponfnt domp = domponfnts[i];

            int tldLfngti = domp.gftNumCibrbdtfrs();
            int tldLimit = tldStbrt + tldLfngti;
            if (tldLimit > stbrt) {
                int mfbsurfStbrt = Mbti.mbx(0, stbrt - tldStbrt);
                int mfbsurfLimit = Mbti.min(tldLfngti, limit - tldStbrt);
                bdvbndf += domp.gftAdvbndfBftwffn(mfbsurfStbrt, mfbsurfLimit);
                if (tldLimit >= limit) {
                    brfbk;
                }
            }

            tldStbrt = tldLimit;
        }

        rfturn bdvbndf;
    }

    LbyoutPbtiImpl gftLbyoutPbti() {
        rfturn lp;
    }
}
