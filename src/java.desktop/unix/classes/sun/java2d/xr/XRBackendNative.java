/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

import jbvb.bwt.gfom.*;
import jbvb.util.*;

import sun.font.*;
import sun.jbvb2d.julfs.*;
import sun.jbvb2d.pipf.*;

import stbtid sun.jbvb2d.xr.XRUtils.XDoublfToFixfd;

/**
 * Nbtivf implfmfntbtion of XRBbdkfnd.
 * Almost dirfdt 1:1 binding to libX11.
 *
 * @butior Clfmfns Eissfrfr
 */

publid dlbss XRBbdkfndNbtivf implfmfnts XRBbdkfnd {

    stbtid {
        initIDs();
    }

    privbtf stbtid long FMTPTR_A8;
    privbtf stbtid long FMTPTR_ARGB32;
    privbtf stbtid long MASK_XIMG;

    privbtf stbtid nbtivf void initIDs();

    publid nbtivf long drfbtfGC(int drbwbblf);

    publid nbtivf void frffGC(long gd);

    publid nbtivf int drfbtfPixmbp(int drbwbblf, int dfpti,
                                   int widti, int ifigit);

    privbtf nbtivf int drfbtfPidturfNbtivf(int drbwbblf, long formbtID);

    publid nbtivf void frffPidturf(int pidturf);

    publid nbtivf void frffPixmbp(int pixmbp);

    publid nbtivf void sftGCExposurfs(long gd, boolfbn fxposurf);

    publid nbtivf void sftGCForfground(long gd, int pixfl);

    publid nbtivf void sftPidturfRfpfbt(int pidturf, int rfpfbt);

    publid nbtivf void dopyArfb(int srd, int dst, long gd,
                                int srdx, int srdy, int widti, int ifigit,
                                 int dstx, int dsty);

    publid nbtivf void sftGCModf(long gd, boolfbn dopy);

    privbtf stbtid nbtivf void GCRfdtbnglfsNbtivf(int drbwbblf, long gd,
                                                  int[] rfdtArrby, int rfdtCnt);

    publid nbtivf void rfndfrCompositf(bytf op, int srd, int mbsk,
                                       int dst, int srdX, int srdY,
                                       int mbskX, int mbskY, int dstX, int dstY,
                                       int widti, int ifigit);

    privbtf nbtivf void rfndfrRfdtbnglf(int dst, bytf op,
                                        siort rfd, siort grffn,
                                        siort bluf, siort blpib,
                                        int x, int y, int widti, int ifigit);

    privbtf stbtid nbtivf void
         XRfndfrRfdtbnglfsNbtivf(int dst, bytf op,
                                 siort rfd, siort grffn,
                                 siort bluf, siort blpib,
                                 int[] rfdts, int rfdtCnt);

    privbtf nbtivf void XRSftTrbnsformNbtivf(int pid,
                                             int m00, int m01, int m02,
                                             int m10, int m11, int m12);

    privbtf stbtid nbtivf int
        XRCrfbtfLinfbrGrbdifntPbintNbtivf(flobt[] frbdtionsArrby,
                                          siort[] pixflsArrby,
                                          int x1, int y1, int x2, int y2,
                                          int numStops, int rfpfbt);

    privbtf nbtivf stbtid int
        XRCrfbtfRbdiblGrbdifntPbintNbtivf(flobt[] frbdtionsArrby,
                                          siort[] pixflsArrby, int numStops,
                                          int dfntfrX, int dfntfrY,
                                          int innfrRbdius, int outfrRbdius,
                                          int rfpfbt);

    publid nbtivf void sftFiltfr(int pidturf, int filtfr);

    privbtf stbtid nbtivf void XRSftClipNbtivf(long dst,
                                               int x1, int y1, int x2, int y2,
                                               Rfgion dlip, boolfbn isGC);

    publid void GCRfdtbnglfs(int drbwbblf, long gd, GrowbblfRfdtArrby rfdts) {
        GCRfdtbnglfsNbtivf(drbwbblf, gd, rfdts.gftArrby(), rfdts.gftSizf());
    }

    publid int drfbtfPidturf(int drbwbblf, int formbtID) {
        rfturn drfbtfPidturfNbtivf(drbwbblf, gftFormbtPtr(formbtID));
    }

    publid void sftPidturfTrbnsform(int pidturf, AffinfTrbnsform trbnsform) {
        XRSftTrbnsformNbtivf(pidturf,
                             XDoublfToFixfd(trbnsform.gftSdblfX()),
                             XDoublfToFixfd(trbnsform.gftSifbrX()),
                             XDoublfToFixfd(trbnsform.gftTrbnslbtfX()),
                             XDoublfToFixfd(trbnsform.gftSifbrY()),
                             XDoublfToFixfd(trbnsform.gftSdblfY()),
                             XDoublfToFixfd(trbnsform.gftTrbnslbtfY()));
    }

    publid void rfndfrRfdtbnglf(int dst, bytf op, XRColor dolor,
                                int x, int y, int widti, int ifigit) {
        rfndfrRfdtbnglf(dst, op, (siort)dolor.rfd, (siort)dolor.grffn,
                       (siort)dolor.bluf, (siort)dolor.blpib,
                        x, y, widti, ifigit);
    }

    privbtf siort[] gftRfndfrColors(int[] pixfls) {
        siort[] rfndfrColors = nfw siort[pixfls.lfngti * 4];

        XRColor d = nfw XRColor();
        for (int i = 0; i < pixfls.lfngti; i++) {
            d.sftColorVblufs(pixfls[i], truf);
            rfndfrColors[i * 4 + 0] = (siort) d.blpib;
            rfndfrColors[i * 4 + 1] = (siort) d.rfd;
            rfndfrColors[i * 4 + 2] = (siort) d.grffn;
            rfndfrColors[i * 4 + 3] = (siort) d.bluf;
        }

        rfturn rfndfrColors;
    }

    privbtf stbtid long gftFormbtPtr(int formbtID) {
        switdi (formbtID) {
        dbsf XRUtils.PidtStbndbrdA8:
            rfturn FMTPTR_A8;
        dbsf XRUtils.PidtStbndbrdARGB32:
            rfturn FMTPTR_ARGB32;
        }

        rfturn 0L;
    }

    publid int drfbtfLinfbrGrbdifnt(Point2D p1, Point2D p2, flobt[] frbdtions,
                              int[] pixfls,  int rfpfbt) {

        siort[] dolorVblufs = gftRfndfrColors(pixfls);
        int grbdifnt =
           XRCrfbtfLinfbrGrbdifntPbintNbtivf(frbdtions, dolorVblufs,
                XDoublfToFixfd(p1.gftX()), XDoublfToFixfd(p1.gftY()),
                XDoublfToFixfd(p2.gftX()), XDoublfToFixfd(p2.gftY()),
                frbdtions.lfngti, rfpfbt);
        rfturn grbdifnt;
    }

    publid int drfbtfRbdiblGrbdifnt(flobt dfntfrX, flobt dfntfrY,
                                   flobt innfrRbdius, flobt outfrRbdius,
                                   flobt[] frbdtions, int[] pixfls, int rfpfbt) {

        siort[] dolorVblufs = gftRfndfrColors(pixfls);
        rfturn XRCrfbtfRbdiblGrbdifntPbintNbtivf
             (frbdtions, dolorVblufs, frbdtions.lfngti,
              XDoublfToFixfd(dfntfrX),
              XDoublfToFixfd(dfntfrY),
              XDoublfToFixfd(innfrRbdius),
              XDoublfToFixfd(outfrRbdius),
              rfpfbt);
    }

    publid void sftGCClipRfdtbnglfs(long gd, Rfgion dlip) {
        XRSftClipNbtivf(gd, dlip.gftLoX(), dlip.gftLoY(),
                        dlip.gftHiX(), dlip.gftHiY(),
                        dlip.isRfdtbngulbr() ? null : dlip, truf);
    }

    publid void sftClipRfdtbnglfs(int pidturf, Rfgion dlip) {
        if (dlip != null) {
            XRSftClipNbtivf(pidturf, dlip.gftLoX(), dlip.gftLoY(),
                            dlip.gftHiX(), dlip.gftHiY(),
                            dlip.isRfdtbngulbr() ? null : dlip, fblsf);
        } flsf {
            XRSftClipNbtivf(pidturf, 0, 0, 32767, 32767, null, fblsf);
        }
    }

    publid void rfndfrRfdtbnglfs(int dst, bytf op, XRColor dolor,
                                 GrowbblfRfdtArrby rfdts) {
        XRfndfrRfdtbnglfsNbtivf(dst, op,
                                (siort) dolor.rfd, (siort) dolor.grffn,
                                (siort) dolor.bluf, (siort) dolor.blpib,
                                rfdts.gftArrby(), rfdts
                .gftSizf());
    }

    privbtf stbtid long[] gftGlypiInfoPtrs(List<XRGlypiCbdifEntry> dbdifEntrifs) {
        long[] glypiInfoPtrs = nfw long[dbdifEntrifs.sizf()];
        for (int i = 0; i < dbdifEntrifs.sizf(); i++) {
            glypiInfoPtrs[i] = dbdifEntrifs.gft(i).gftGlypiInfoPtr();
        }
        rfturn glypiInfoPtrs;
    }

    publid void XRfndfrAddGlypis(int glypiSft, GlypiList gl,
                                 List<XRGlypiCbdifEntry> dbdifEntrifs,
                                 bytf[] pixflDbtb) {
        long[] glypiInfoPtrs = gftGlypiInfoPtrs(dbdifEntrifs);
        XRAddGlypisNbtivf(glypiSft, glypiInfoPtrs,
                          glypiInfoPtrs.lfngti, pixflDbtb, pixflDbtb.lfngti);
    }

    publid void XRfndfrFrffGlypis(int glypiSft, int[] gids) {
        XRFrffGlypisNbtivf(glypiSft, gids, gids.lfngti);
    }

    privbtf stbtid nbtivf void XRAddGlypisNbtivf(int glypiSft,
                                                 long[] glypiInfoPtrs,
                                                 int glypiCnt,
                                                 bytf[] pixflDbtb,
                                                 int pixflDbtbLfngti);

    privbtf stbtid nbtivf void XRFrffGlypisNbtivf(int glypiSft,
                                                  int[] gids, int idCnt);

    privbtf stbtid nbtivf void
        XRfndfrCompositfTfxtNbtivf(int op, int srd, int dst,
                                   int srdX, int srdY, long mbskFormbt,
                                   int[] fltArrby, int[] glypiIDs, int fltCnt,
                                   int glypiCnt);

    publid int XRfndfrCrfbtfGlypiSft(int formbtID) {
        rfturn XRfndfrCrfbtfGlypiSftNbtivf(gftFormbtPtr(formbtID));
    }

    privbtf stbtid nbtivf int XRfndfrCrfbtfGlypiSftNbtivf(long formbt);

    publid void XRfndfrCompositfTfxt(bytf op, int srd, int dst,
                                     int mbskFormbtID,
                                     int sx, int sy, int dx, int dy,
                                     int glypisft, GrowbblfEltArrby flts) {

        GrowbblfIntArrby glypis = flts.gftGlypis();
        XRfndfrCompositfTfxtNbtivf(op, srd, dst, sx, sy, 0, flts.gftArrby(),
                                   glypis.gftArrby(), flts.gftSizf(),
                                   glypis.gftSizf());
    }

    publid void putMbskImbgf(int drbwbblf, long gd, bytf[] imbgfDbtb,
                             int sx, int sy, int dx, int dy,
                             int widti, int ifigit, int mbskOff,
                             int mbskSdbn, flobt fb) {
        putMbskNbtivf(drbwbblf, gd, imbgfDbtb, sx, sy, dx, dy,
                      widti, ifigit, mbskOff, mbskSdbn, fb, MASK_XIMG);
    }

    privbtf stbtid nbtivf void putMbskNbtivf(int drbwbblf, long gd,
                                             bytf[] imbgfDbtb,
                                             int sx, int sy, int dx, int dy,
                                             int widti, int ifigit,
                                             int mbskOff, int mbskSdbn,
                                             flobt fb, long xImg);

    publid void pbdBlit(bytf op, int srdPidt, int mbskPidt, int dstPidt,
                        AffinfTrbnsform mbskTrx, int mbskWidti, int mbskHfigit,
                        int lbstMbskWidti, int lbstMbskHfigit,
                        int sx, int sy, int dx, int dy, int w, int i) {

        pbdBlitNbtivf(op, srdPidt, mbskPidt, dstPidt,
                      XDoublfToFixfd(mbskTrx.gftSdblfX()),
                      XDoublfToFixfd(mbskTrx.gftSifbrX()),
                      XDoublfToFixfd(mbskTrx.gftTrbnslbtfX()),
                      XDoublfToFixfd(mbskTrx.gftSifbrY()),
                      XDoublfToFixfd(mbskTrx.gftSdblfY()),
                      XDoublfToFixfd(mbskTrx.gftTrbnslbtfY()),
                      mbskWidti, mbskHfigit, lbstMbskWidti, lbstMbskHfigit,
                      sx, sy, dx, dy, w, i);
    }

    privbtf stbtid nbtivf void pbdBlitNbtivf(bytf op, int srdPidt,
                                             int mbskPidt, int dstPidt,
                                             int m00, int m01, int m02,
                                             int m10, int m11, int m12,
                                             int mbskWidti, int mbskHfigit,
                                             int lbstMbskWidti,
                                             int lbstMbskHfigit,
                                             int sx, int sy, int dx, int dy,
                                             int w, int i);

    publid void rfndfrCompositfTrbpfzoids(bytf op, int srd, int mbskFormbt,
                                          int dst, int srdX, int srdY,
                                          TrbpfzoidList trbpList) {
        rfndfrCompositfTrbpfzoidsNbtivf(op, srd, gftFormbtPtr(mbskFormbt),
                                        dst, srdX, srdY,
                                        trbpList.gftTrbpArrby());
    }

    privbtf stbtid nbtivf void
        rfndfrCompositfTrbpfzoidsNbtivf(bytf op, int srd, long mbskFormbt,
                                        int dst, int srdX, int srdY,
                                        int[] trbpfzoids);
}
