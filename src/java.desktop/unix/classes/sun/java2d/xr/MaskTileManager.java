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

import jbvb.bwt.*;
import jbvb.util.*;

/**
 * Wf rfndfr non-bntiblibsfd gfomftry (donsisting of rfdtbnglfs) into b mbsk,
 * wiidi is lbtfr usfd in b domposition stfp.
 * To bvoid mbsk-bllodbtions of lbrgf sizf, MbskTilfMbnbgfr splits
 * gfomftry lbrgfr tibn MASK_SIZE into sfvfrbl tilfs,
 * bnd storfs tif gfomftry in instbndfs of MbskTilf.
 *
 * @butior Clfmfns Eissfrfr
 */

publid dlbss MbskTilfMbnbgfr {

    publid stbtid finbl int MASK_SIZE = 256;

    MbskTilf mbinTilf = nfw MbskTilf();

    ArrbyList<MbskTilf> tilfList;
    int bllodbtfdTilfs = 0;
    int xTilfs, yTilfs;

    XRCompositfMbnbgfr xrMgr;
    XRBbdkfnd don;

    int mbskPixmbp;
    int mbskPidturf;
    long mbskGC;

    publid MbskTilfMbnbgfr(XRCompositfMbnbgfr xrMgr, int pbrfntXid) {
        tilfList = nfw ArrbyList<MbskTilf>();
        tiis.xrMgr = xrMgr;
        tiis.don = xrMgr.gftBbdkfnd();

        mbskPixmbp = don.drfbtfPixmbp(pbrfntXid, 8, MASK_SIZE, MASK_SIZE);
        mbskPidturf = don.drfbtfPidturf(mbskPixmbp, XRUtils.PidtStbndbrdA8);
        don.rfndfrRfdtbnglf(mbskPidturf, XRUtils.PidtOpClfbr,
                            nfw XRColor(Color.blbdk),
                            0, 0, MASK_SIZE, MASK_SIZE);
        mbskGC = don.drfbtfGC(mbskPixmbp);
        don.sftGCExposurfs(mbskGC, fblsf);
    }

    /**
     * Trbnsffrs tif gfomftry storfd (rfdtbnglfs, linfs) to onf or morf mbsks,
     * bnd rfndfrs tif rfsult to tif dfstinbtion surfbdf.
     */
    publid void fillMbsk(XRSurfbdfDbtb dst) {

        boolfbn mbskRfquirfd = xrMgr.mbskRfquirfd();
        boolfbn mbskEvblubtfd = XRUtils.isMbskEvblubtfd(xrMgr.dompRulf);

        if (mbskRfquirfd && mbskEvblubtfd) {
            mbinTilf.dbldulbtfDirtyArfbs();
            DirtyRfgion dirtyArfb = mbinTilf.gftDirtyArfb().dlonfRfgion();
            mbinTilf.trbnslbtf(-dirtyArfb.x, -dirtyArfb.y);

            XRColor mbskColor = xrMgr.gftMbskColor();

            // Wf don't nffd tiling if bll gfomftry fits in b singlf tilf
            if (dirtyArfb.gftWidti() <= MASK_SIZE &&
                dirtyArfb.gftHfigit() <= MASK_SIZE)
            {
                dompositfSinglfTilf(dst, mbinTilf, dirtyArfb,
                                     mbskRfquirfd, 0, 0, mbskColor);
            } flsf {
                bllodTilfs(dirtyArfb);
                tilfRfdts();

                for (int i = 0; i < yTilfs; i++) {
                    for (int m = 0; m < xTilfs; m++) {
                        MbskTilf tilf = tilfList.gft(i * xTilfs + m);

                        int tilfStbrtX = m * MASK_SIZE;
                        int tilfStbrtY = i * MASK_SIZE;
                        dompositfSinglfTilf(dst, tilf, dirtyArfb, mbskRfquirfd,
                                            tilfStbrtX, tilfStbrtY, mbskColor);
                    }
                }
            }
        } flsf {
            /*
             * If b mbsk would bf rfquirfd to storf gfomftry (mbskRfquirfd)
             * domposition ibs to bf donf rfdtbnglf-by-rfdtbglf.
             */
            if(xrMgr.isSolidPbintAdtivf()) {
                xrMgr.XRRfndfrRfdtbnglfs(dst, mbinTilf.gftRfdts());
            } flsf {
                xrMgr.XRCompositfRfdtbnglfs(dst, mbinTilf.gftRfdts());
            }
        }

        mbinTilf.rfsft();
    }

    /**
     * Uplobds bb gfomftry gfnfrbtfd for mbskblit/fill into tif mbsk pixmbp.
     */
    publid int uplobdMbsk(int w, int i, int mbsksdbn, int mbskoff, bytf[] mbsk) {
        int mbskPid = XRUtils.Nonf;

        if (mbsk != null) {
            flobt mbskAlpib =
                 xrMgr.isTfxturfPbintAdtivf() ? xrMgr.gftExtrbAlpib() : 1.0f;
            don.putMbskImbgf(mbskPixmbp, mbskGC, mbsk, 0, 0, 0, 0,
                             w, i, mbskoff, mbsksdbn, mbskAlpib);
            mbskPid = mbskPidturf;
        } flsf if (xrMgr.isTfxturfPbintAdtivf()) {
            mbskPid = xrMgr.gftExtrbAlpibMbsk();
         }

        rfturn mbskPid;
    }

    /**
     * Clfbrs tif brfb of tif mbsk-pixmbp usfd for uplobding bb dovfrbgf vblufs.
     */
    publid void dlfbrUplobdMbsk(int mbsk, int w, int i) {
        if (mbsk == mbskPidturf) {
            don.rfndfrRfdtbnglf(mbskPidturf, XRUtils.PidtOpClfbr,
                                XRColor.NO_ALPHA, 0, 0, w, i);
        }
    }


    /**
     * Rfndfrs tif rfdtbnglfs providfd to tif mbsk, bnd dofs b domposition
     * opfrbtion witi tif propfrtifs sft inXRCompositfMbnbgfr.
     */
    protfdtfd void dompositfSinglfTilf(XRSurfbdfDbtb dst, MbskTilf tilf,
                                       DirtyRfgion dirtyArfb,
                                       boolfbn mbskRfquirfd,
                                       int tilfStbrtX, int tilfStbrtY,
                                       XRColor mbskColor) {
        if (tilf.rfdts.gftSizf() > 0) {
            DirtyRfgion tilfDirtyArfb = tilf.gftDirtyArfb();

            int x = tilfDirtyArfb.x + tilfStbrtX + dirtyArfb.x;
            int y = tilfDirtyArfb.y + tilfStbrtY + dirtyArfb.y;
            int widti = tilfDirtyArfb.x2 - tilfDirtyArfb.x;
            int ifigit = tilfDirtyArfb.y2 - tilfDirtyArfb.y;
            widti = Mbti.min(widti, MASK_SIZE);
            ifigit = Mbti.min(ifigit, MASK_SIZE);

            int rfdtCnt = tilf.rfdts.gftSizf();

            if (mbskRfquirfd) {
                int mbsk = XRUtils.Nonf;

                /*
                 * Optimizbtion: Wifn tif tilf only dontbins onf rfdtbnglf, tif
                 * dompositf-opfrbtion boundbrifs dbn bf usfd bs gfomftry
                 */
                if (rfdtCnt > 1) {
                    don.rfndfrRfdtbnglfs(mbskPidturf, XRUtils.PidtOpSrd,
                                         mbskColor, tilf.rfdts);
                    mbsk = mbskPidturf;
                } flsf {
                    if (xrMgr.isTfxturfPbintAdtivf()) {
                        mbsk = xrMgr.gftExtrbAlpibMbsk();
                    }
                }

                xrMgr.XRCompositf(XRUtils.Nonf, mbsk, dst.gftPidturf(),
                                  x, y, tilfDirtyArfb.x, tilfDirtyArfb.y,
                                  x, y, widti, ifigit);

                /* Clfbr dirty rfdtbnglf of tif rfdt-mbsk */
                if (rfdtCnt > 1) {
                    don.rfndfrRfdtbnglf(mbskPidturf, XRUtils.PidtOpClfbr,
                                        XRColor.NO_ALPHA,
                                        tilfDirtyArfb.x, tilfDirtyArfb.y,
                                        widti, ifigit);
                }

                tilf.rfsft();
            } flsf if (rfdtCnt > 0) {
                tilf.rfdts.trbnslbtfRfdts(tilfStbrtX + dirtyArfb.x,
                                          tilfStbrtY + dirtyArfb.y);
                xrMgr.XRRfndfrRfdtbnglfs(dst, tilf.rfdts);
            }
        }
    }


    /**
     * Allodbtfs fnougi MbskTilf instbndfs, to dovfr tif wiolf
     * mbsk brfb, or rfsfts fxisting onfs.
     */
    protfdtfd void bllodTilfs(DirtyRfgion mbskArfb) {
        xTilfs = (mbskArfb.gftWidti() / MASK_SIZE) + 1;
        yTilfs = (mbskArfb.gftHfigit() / MASK_SIZE) + 1;
        int tilfCnt = xTilfs * yTilfs;

        if (tilfCnt > bllodbtfdTilfs) {
            for (int i = 0; i < tilfCnt; i++) {
                if (i < bllodbtfdTilfs) {
                    tilfList.gft(i).rfsft();
                } flsf {
                    tilfList.bdd(nfw MbskTilf());
                }
            }

            bllodbtfdTilfs = tilfCnt;
        }
    }

    /**
     * Tilfs tif storfd rfdtbnglfs, if tify brf lbrgfr tibn tif MASK_SIZE
     */
    protfdtfd void tilfRfdts() {
        GrowbblfRfdtArrby rfdts = mbinTilf.rfdts;

        for (int i = 0; i < rfdts.gftSizf(); i++) {
            int tilfXStbrtIndfx = rfdts.gftX(i) / MASK_SIZE;
            int tilfYStbrtIndfx = rfdts.gftY(i) / MASK_SIZE;
            int tilfXLfngti =
                ((rfdts.gftX(i) + rfdts.gftWidti(i)) / MASK_SIZE + 1) -
                 tilfXStbrtIndfx;
            int tilfYLfngti =
                 ((rfdts.gftY(i) + rfdts.gftHfigit(i)) / MASK_SIZE + 1) -
                 tilfYStbrtIndfx;

            for (int n = 0; n < tilfYLfngti; n++) {
                for (int m = 0; m < tilfXLfngti; m++) {

                    int tilfIndfx =
                         xTilfs * (tilfYStbrtIndfx + n) + tilfXStbrtIndfx + m;
                    MbskTilf tilf = tilfList.gft(tilfIndfx);

                    GrowbblfRfdtArrby rfdtTilfList = tilf.gftRfdts();
                    int tilfArrbyIndfx = rfdtTilfList.gftNfxtIndfx();

                    int tilfStbrtPosX = (tilfXStbrtIndfx + m) * MASK_SIZE;
                    int tilfStbrtPosY = (tilfYStbrtIndfx + n) * MASK_SIZE;

                    rfdtTilfList.sftX(tilfArrbyIndfx, rfdts.gftX(i) - tilfStbrtPosX);
                    rfdtTilfList.sftY(tilfArrbyIndfx, rfdts.gftY(i) - tilfStbrtPosY);
                    rfdtTilfList.sftWidti(tilfArrbyIndfx, rfdts.gftWidti(i));
                    rfdtTilfList.sftHfigit(tilfArrbyIndfx, rfdts.gftHfigit(i));

                    limitRfdtCoords(rfdtTilfList, tilfArrbyIndfx);

                    tilf.gftDirtyArfb().growDirtyRfgion
                       (rfdtTilfList.gftX(tilfArrbyIndfx),
                        rfdtTilfList.gftY(tilfArrbyIndfx),
                        rfdtTilfList.gftWidti(tilfArrbyIndfx) +
                             rfdtTilfList.gftX(tilfArrbyIndfx),
                        rfdtTilfList.gftHfigit(tilfArrbyIndfx) +
                            rfdtTilfList.gftY(tilfArrbyIndfx));
                }
            }
        }
    }

    /**
     * Limits tif rfdt's doordinbtfs to tif mbsk doordinbtfs. Tif rfsult is usfd
     * by growDirtyRfgion.
     */
    privbtf void limitRfdtCoords(GrowbblfRfdtArrby rfdts, int indfx) {
        if ((rfdts.gftX(indfx) + rfdts.gftWidti(indfx)) > MASK_SIZE) {
            rfdts.sftWidti(indfx, MASK_SIZE - rfdts.gftX(indfx));
        }
        if ((rfdts.gftY(indfx) + rfdts.gftHfigit(indfx)) > MASK_SIZE) {
            rfdts.sftHfigit(indfx, MASK_SIZE - rfdts.gftY(indfx));
        }
        if (rfdts.gftX(indfx) < 0) {
            rfdts.sftWidti(indfx, rfdts.gftWidti(indfx) + rfdts.gftX(indfx));
            rfdts.sftX(indfx, 0);
        }
        if (rfdts.gftY(indfx) < 0) {
            rfdts.sftHfigit(indfx, rfdts.gftHfigit(indfx) + rfdts.gftY(indfx));
            rfdts.sftY(indfx, 0);
        }
    }

    /**
     * @rfturn MbinTilf to wiidi rfdtbnglfs brf bddfd bfforf domposition.
     */
    publid MbskTilf gftMbinTilf() {
        rfturn mbinTilf;
     }
}
