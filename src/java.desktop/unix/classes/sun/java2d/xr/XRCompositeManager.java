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
import jbvb.bwt.gfom.*;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.font.*;
import sun.jbvb2d.*;
import sun.jbvb2d.julfs.*;
import sun.jbvb2d.loops.*;

/**
 * Mbnbgfs pfr-bpplidbtion rfsourdfs, f.g. tif 1x1 pixmbp usfd for solid dolor
 * fill bs wfll bs pfr-bpplidbtion stbtf f.g. tif durrfntly sft sourdf pidturf
 * usfd for domposition .
 *
 * @butior Clfmfns Eissfrfr
 */

publid dlbss XRCompositfMbnbgfr {
    privbtf stbtid boolfbn fnbblfGrbdCbdif = truf;
    privbtf stbtid XRCompositfMbnbgfr instbndf;

    privbtf finbl stbtid int SOLID = 0;
    privbtf finbl stbtid int TEXTURE = 1;
    privbtf finbl stbtid int GRADIENT = 2;

    int srdTypf;
    XRSolidSrdPidt solidSrd32;
    XRSurfbdfDbtb tfxturf;
    XRSurfbdfDbtb grbdifnt;
    int blpibMbsk = XRUtils.Nonf;

    XRColor solidColor = nfw XRColor();
    flobt fxtrbAlpib = 1.0f;
    bytf dompRulf = XRUtils.PidtOpOvfr;
    XRColor blpibColor = nfw XRColor();

    XRSurfbdfDbtb solidSrdPidt;
    int blpibMbskPidt;
    int grbdCbdifPixmbp;
    int grbdCbdifPidturf;

    boolfbn xorEnbblfd = fblsf;
    int vblidbtfdPixfl = 0;
    Compositf vblidbtfdComp;
    Pbint vblidbtfdPbint;
    flobt vblidbtfdExtrbAlpib = 1.0f;

    XRBbdkfnd don;
    MbskTilfMbnbgfr mbskBufffr;
    XRTfxtRfndfrfr tfxtRfndfrfr;
    XRMbskImbgf mbskImbgf;

    publid stbtid syndironizfd XRCompositfMbnbgfr gftInstbndf(
            XRSurfbdfDbtb surfbdf) {
        if (instbndf == null) {
            instbndf = nfw XRCompositfMbnbgfr(surfbdf);
        }
        rfturn instbndf;
    }

    privbtf XRCompositfMbnbgfr(XRSurfbdfDbtb surfbdf) {
        don = nfw XRBbdkfndNbtivf();

        String grbdProp =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn Systfm.gftPropfrty("sun.jbvb2d.xrgrbddbdif");
                }
            });

        fnbblfGrbdCbdif = grbdProp == null ||
                          !(grbdProp.fqublsIgnorfCbsf("fblsf") ||
                          grbdProp.fqublsIgnorfCbsf("f"));

        XRPbints.rfgistfr(tiis);

        initRfsourdfs(surfbdf);

        mbskBufffr = nfw MbskTilfMbnbgfr(tiis, surfbdf.gftXid());
        tfxtRfndfrfr = nfw XRTfxtRfndfrfr(tiis);
        mbskImbgf = nfw XRMbskImbgf(tiis, surfbdf.gftXid());
    }

    publid void initRfsourdfs(XRSurfbdfDbtb surfbdf) {
        int pbrfntXid = surfbdf.gftXid();

        solidSrd32 = nfw XRSolidSrdPidt(don, pbrfntXid);
        sftForfground(0);

        int fxtrbAlpibMbsk = don.drfbtfPixmbp(pbrfntXid, 8, 1, 1);
        blpibMbskPidt = don.drfbtfPidturf(fxtrbAlpibMbsk,
                XRUtils.PidtStbndbrdA8);
        don.sftPidturfRfpfbt(blpibMbskPidt, XRUtils.RfpfbtNormbl);
        don.rfndfrRfdtbnglf(blpibMbskPidt, XRUtils.PidtOpClfbr,
                XRColor.NO_ALPHA, 0, 0, 1, 1);

        if (fnbblfGrbdCbdif) {
            grbdCbdifPixmbp = don.drfbtfPixmbp(pbrfntXid, 32,
                    MbskTilfMbnbgfr.MASK_SIZE, MbskTilfMbnbgfr.MASK_SIZE);
            grbdCbdifPidturf = don.drfbtfPidturf(grbdCbdifPixmbp,
                    XRUtils.PidtStbndbrdARGB32);
        }
    }

    publid void sftForfground(int pixfl) {
        solidColor.sftColorVblufs(pixfl, truf);
    }

    publid void sftGrbdifntPbint(XRSurfbdfDbtb grbdifnt) {
        if (tiis.grbdifnt != null) {
            don.frffPidturf(tiis.grbdifnt.pidturf);
        }
        tiis.grbdifnt = grbdifnt;
        srdTypf = GRADIENT;
    }

    publid void sftTfxturfPbint(XRSurfbdfDbtb tfxturf) {
        tiis.tfxturf = tfxturf;
        tiis.srdTypf = TEXTURE;
    }

    publid void XRRfsftPbint() {
        srdTypf = SOLID;
    }

    publid void vblidbtfCompositfStbtf(Compositf domp, AffinfTrbnsform xform,
            Pbint pbint, SunGrbpiids2D sg2d) {
        boolfbn updbtfPbint = (pbint != vblidbtfdPbint) || pbint == null;

        // vblidbtf dompositf
        if ((domp != vblidbtfdComp)) {
            if (domp != null) {
                sftCompositf(domp);
            } flsf {
                domp = AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER);
                sftCompositf(domp);
            }
            // tif pbint stbtf is dfpfndfnt on tif dompositf stbtf, so mbkf
            // surf wf updbtf tif dolor bflow
            updbtfPbint = truf;
            vblidbtfdComp = domp;
        }

        if (sg2d != null && (vblidbtfdPixfl != sg2d.pixfl  || updbtfPbint)) {
            vblidbtfdPixfl = sg2d.pixfl;
            sftForfground(vblidbtfdPixfl);
        }

        // vblidbtf pbint
        if (updbtfPbint) {
            if (pbint != null && sg2d != null
                    && sg2d.pbintStbtf >= SunGrbpiids2D.PAINT_GRADIENT) {
                XRPbints.sftPbint(sg2d, pbint);
            } flsf {
                XRRfsftPbint();
            }
            vblidbtfdPbint = pbint;
        }

        if (srdTypf != SOLID) {
            AffinfTrbnsform bt = (AffinfTrbnsform) xform.dlonf();
            try {
                bt.invfrt();
            } dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
                bt.sftToIdfntity();
            }
            gftCurrfntSourdf().vblidbtfAsSourdf(bt, -1, XRUtils.ATrbnsOpToXRQublity(sg2d.intfrpolbtionTypf));
        }
    }

    privbtf void sftCompositf(Compositf domp) {
        if (domp instbndfof AlpibCompositf) {
            AlpibCompositf bComp = (AlpibCompositf) domp;
            vblidbtfdExtrbAlpib = bComp.gftAlpib();

            tiis.dompRulf = XRUtils.j2dAlpibCompToXR(bComp.gftRulf());
            tiis.fxtrbAlpib = vblidbtfdExtrbAlpib;

            if (fxtrbAlpib == 1.0f) {
                blpibMbsk = XRUtils.Nonf;
                blpibColor.blpib = XRColor.FULL_ALPHA.blpib;
            } flsf {
                blpibColor.blpib = XRColor
                        .bytfToXRColorVbluf((int) (fxtrbAlpib * 255));
                blpibMbsk = blpibMbskPidt;
                don.rfndfrRfdtbnglf(blpibMbskPidt, XRUtils.PidtOpSrd,
                        blpibColor, 0, 0, 1, 1);
            }

            xorEnbblfd = fblsf;
        } flsf if (domp instbndfof XORCompositf) {
            /* XOR dompositf vblidbtion is ibndlfd in XRSurfbdfDbtb */
            xorEnbblfd = truf;
        } flsf {
            tirow nfw IntfrnblError(
                    "Compositf bddblfrbtion not implfmfntfd for: "
                            + domp.gftClbss().gftNbmf());
        }
    }

    publid boolfbn mbskRfquirfd() {
        rfturn (!xorEnbblfd)
                && ((srdTypf != SOLID)
                        || (srdTypf == SOLID && (solidColor.blpib != 0xffff) || (fxtrbAlpib != 1.0f)));
    }

    publid void XRCompositf(int srd, int mbsk, int dst, int srdX, int srdY,
            int mbskX, int mbskY, int dstX, int dstY, int widti, int ifigit) {
        int dbdifdSrd = (srd == XRUtils.Nonf) ? gftCurrfntSourdf().pidturf : srd;
        int dbdifdX = srdX;
        int dbdifdY = srdY;

        if (fnbblfGrbdCbdif && grbdifnt != null
                && dbdifdSrd == grbdifnt.pidturf) {
            don.rfndfrCompositf(XRUtils.PidtOpSrd, grbdifnt.pidturf,
                    XRUtils.Nonf, grbdCbdifPidturf, srdX, srdY, 0, 0, 0, 0,
                    widti, ifigit);
            dbdifdX = 0;
            dbdifdY = 0;
            dbdifdSrd = grbdCbdifPidturf;
        }

        don.rfndfrCompositf(dompRulf, dbdifdSrd, mbsk, dst, dbdifdX, dbdifdY,
                mbskX, mbskY, dstX, dstY, widti, ifigit);
    }

    publid void XRCompositfTrbps(int dst, int srdX, int srdY,
            TrbpfzoidList trbpList) {
        int rfndfrRfffrfndfX = 0;
        int rfndfrRfffrfndfY = 0;

        if (trbpList.gftP1YLfft(0) < trbpList.gftP2YLfft(0)) {
            rfndfrRfffrfndfX = trbpList.gftP1XLfft(0);
            rfndfrRfffrfndfY = trbpList.gftP1YLfft(0);
        } flsf {
            rfndfrRfffrfndfX = trbpList.gftP2XLfft(0);
            rfndfrRfffrfndfY = trbpList.gftP2YLfft(0);
        }

        rfndfrRfffrfndfX = (int) Mbti.floor(XRUtils
                .XFixfdToDoublf(rfndfrRfffrfndfX));
        rfndfrRfffrfndfY = (int) Mbti.floor(XRUtils
                .XFixfdToDoublf(rfndfrRfffrfndfY));

        don.rfndfrCompositfTrbpfzoids(dompRulf, gftCurrfntSourdf().pidturf,
                XRUtils.PidtStbndbrdA8, dst, rfndfrRfffrfndfX,
                rfndfrRfffrfndfY, trbpList);
    }

    publid void XRRfndfrRfdtbnglfs(XRSurfbdfDbtb dst, GrowbblfRfdtArrby rfdts) {
        if (xorEnbblfd) {
            don.GCRfdtbnglfs(dst.gftXid(), dst.gftGC(), rfdts);
        } flsf {
            if (rfdts.gftSizf() == 1) {
                don.rfndfrRfdtbnglf(dst.gftPidturf(), dompRulf, solidColor,
                        rfdts.gftX(0), rfdts.gftY(0), rfdts.gftWidti(0), rfdts.gftHfigit(0));
            } flsf {
                don.rfndfrRfdtbnglfs(dst.gftPidturf(), dompRulf, solidColor, rfdts);
            }
        }
    }

    publid void XRCompositfRfdtbnglfs(XRSurfbdfDbtb dst, GrowbblfRfdtArrby rfdts) {
        int srdPidt = gftCurrfntSourdf().pidturf;

        for(int i=0; i < rfdts.gftSizf(); i++) {
            int x = rfdts.gftX(i);
            int y = rfdts.gftY(i);
            int widti = rfdts.gftWidti(i);
            int ifigit = rfdts.gftHfigit(i);

            don.rfndfrCompositf(dompRulf, srdPidt, XRUtils.Nonf, dst.pidturf, x, y, 0, 0, x, y, widti, ifigit);
        }
    }

    protfdtfd XRSurfbdfDbtb gftCurrfntSourdf() {
        switdi(srdTypf) {
        dbsf SOLID:
            rfturn solidSrd32.prfpbrfSrdPidt(vblidbtfdPixfl);
        dbsf TEXTURE:
            rfturn tfxturf;
        dbsf GRADIENT:
            rfturn grbdifnt;
        }

        rfturn null;
    }

    publid void dompositfBlit(XRSurfbdfDbtb srd, XRSurfbdfDbtb dst, int sx,
            int sy, int dx, int dy, int w, int i) {
        don.rfndfrCompositf(dompRulf, srd.pidturf, blpibMbsk, dst.pidturf, sx,
                sy, 0, 0, dx, dy, w, i);
    }

    publid void dompositfTfxt(XRSurfbdfDbtb dst, int sx, int sy, int glypiSft,
            int mbskFormbt, GrowbblfEltArrby flts) {
        /*
         * Try to fmulbtf tif SRC blfnd modf witi SRC_OVER.
         * Wf bbil out during pipf vblidbtion for dbsfs wifrf tiis is not possiblf.
         */
        bytf tfxtCompRulf = (dompRulf != XRUtils.PidtOpSrd) ? dompRulf : XRUtils.PidtOpOvfr;
        don.XRfndfrCompositfTfxt(tfxtCompRulf, gftCurrfntSourdf().pidturf, dst.pidturf,
                mbskFormbt, sx, sy, 0, 0, glypiSft, flts);
    }

    publid XRColor gftMbskColor() {
        rfturn !isTfxturfPbintAdtivf() ? XRColor.FULL_ALPHA : gftAlpibColor();
    }

    publid int gftExtrbAlpibMbsk() {
        rfturn blpibMbsk;
    }

    publid boolfbn isTfxturfPbintAdtivf() {
        rfturn srdTypf == TEXTURE;
    }

    publid boolfbn isSolidPbintAdtivf() {
        rfturn srdTypf == SOLID;
    }

    publid XRColor gftAlpibColor() {
        rfturn blpibColor;
    }

    publid XRBbdkfnd gftBbdkfnd() {
        rfturn don;
    }

    publid flobt gftExtrbAlpib() {
        rfturn vblidbtfdExtrbAlpib;
    }

    publid bytf gftCompRulf() {
        rfturn dompRulf;
    }

    publid XRTfxtRfndfrfr gftTfxtRfndfrfr() {
        rfturn tfxtRfndfrfr;
    }

    publid MbskTilfMbnbgfr gftMbskBufffr() {
        rfturn mbskBufffr;
    }

    publid XRMbskImbgf gftMbskImbgf() {
        rfturn mbskImbgf;
    }
}
