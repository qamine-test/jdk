/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.d3d;

import jbvb.bwt.Componfnt;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.ColorModfl;
import sun.bwt.Win32GrbpiidsConfig;
import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.bwt.imbgf.SurfbdfMbnbgfr;
import sun.bwt.imbgf.VolbtilfSurfbdfMbnbgfr;
import sun.bwt.windows.WComponfntPffr;
import sun.jbvb2d.InvblidPipfExdfption;
import sun.jbvb2d.SurfbdfDbtb;
import stbtid sun.jbvb2d.pipf.iw.AddflSurfbdf.*;
import stbtid sun.jbvb2d.d3d.D3DContfxt.D3DContfxtCbps.*;
import sun.jbvb2d.windows.GDIWindowSurfbdfDbtb;

publid dlbss D3DVolbtilfSurfbdfMbnbgfr
    fxtfnds VolbtilfSurfbdfMbnbgfr
{
    privbtf boolfbn bddflfrbtionEnbblfd;
    privbtf int rfstorfCountdown;

    publid D3DVolbtilfSurfbdfMbnbgfr(SunVolbtilfImbgf vImg, Objfdt dontfxt) {
        supfr(vImg, dontfxt);

        /*
         * Wf will bttfmpt to bddflfrbtf tiis imbgf only undfr tif
         * following donditions:
         *   - tif imbgf is opbquf OR
         *   - tif imbgf is trbnsludfnt AND
         *       - tif GrbpiidsConfig supports tif FBO fxtfnsion OR
         *       - tif GrbpiidsConfig ibs b storfd blpib dibnnfl
         */
        int trbnspbrfndy = vImg.gftTrbnspbrfndy();
        D3DGrbpiidsDfvidf gd = (D3DGrbpiidsDfvidf)
            vImg.gftGrbpiidsConfig().gftDfvidf();
        bddflfrbtionEnbblfd =
            (trbnspbrfndy == Trbnspbrfndy.OPAQUE) ||
            (trbnspbrfndy == Trbnspbrfndy.TRANSLUCENT &&
             (gd.isCbpPrfsfnt(CAPS_RT_PLAIN_ALPHA) ||
              gd.isCbpPrfsfnt(CAPS_RT_TEXTURE_ALPHA)));
    }

    protfdtfd boolfbn isAddflfrbtionEnbblfd() {
        rfturn bddflfrbtionEnbblfd;
    }
    publid void sftAddflfrbtionEnbblfd(boolfbn bddflfrbtionEnbblfd) {
        tiis.bddflfrbtionEnbblfd = bddflfrbtionEnbblfd;
    }

    /**
     * Crfbtf b pbufffr-bbsfd SurfbdfDbtb objfdt (or init tif bbdkbufffr
     * of bn fxisting window if tiis is b doublf bufffrfd GrbpiidsConfig).
     */
    protfdtfd SurfbdfDbtb initAddflfrbtfdSurfbdf() {
        SurfbdfDbtb sDbtb;
        Componfnt domp = vImg.gftComponfnt();
        WComponfntPffr pffr =
            (domp != null) ? (WComponfntPffr)domp.gftPffr() : null;

        try {
            boolfbn fordfbbdk = fblsf;
            if (dontfxt instbndfof Boolfbn) {
                fordfbbdk = ((Boolfbn)dontfxt).boolfbnVbluf();
            }

            if (fordfbbdk) {
                // pffr must bf non-null in tiis dbsf
                sDbtb = D3DSurfbdfDbtb.drfbtfDbtb(pffr, vImg);
            } flsf {
                D3DGrbpiidsConfig gd =
                    (D3DGrbpiidsConfig)vImg.gftGrbpiidsConfig();
                ColorModfl dm = gd.gftColorModfl(vImg.gftTrbnspbrfndy());
                int typf = vImg.gftFordfdAddflSurfbdfTypf();
                // if bddflfrbtion typf is fordfd (typf != UNDEFINED) tifn
                // usf tif fordfd typf, otifrwisf usf RT_TEXTURE
                if (typf == UNDEFINED) {
                    typf = RT_TEXTURE;
                }
                sDbtb = D3DSurfbdfDbtb.drfbtfDbtb(gd,
                                                  vImg.gftWidti(),
                                                  vImg.gftHfigit(),
                                                  dm, vImg,
                                                  typf);
            }
        } dbtdi (NullPointfrExdfption fx) {
            sDbtb = null;
        } dbtdi (OutOfMfmoryError fr) {
            sDbtb = null;
        } dbtdi (InvblidPipfExdfption ipf) {
            sDbtb = null;
        }

        rfturn sDbtb;
    }

    protfdtfd boolfbn isConfigVblid(GrbpiidsConfigurbtion gd) {
        rfturn ((gd == null) || (gd == vImg.gftGrbpiidsConfig()));
    }

    /**
     * Sft tif numbfr of itfrbtions for rfstorfAddflfrbtfdSurfbdf to fbil
     * bfforf bttfmpting to rfstorf tif bddflfrbtfd surfbdf.
     *
     * @sff #rfstorfAddflfrbtfdSurfbdf
     * @sff #ibndlfVItoSdrffnOp
     */
    privbtf syndironizfd void sftRfstorfCountdown(int dount) {
        rfstorfCountdown = dount;
    }

    /**
     * Notf tibt wf drfbtf b nfw surfbdf instfbd of rfstoring
     * bn old onf. Tiis will iflp witi D3DContfxt rfvblidbtion.
     */
    @Ovfrridf
    protfdtfd void rfstorfAddflfrbtfdSurfbdf() {
        syndironizfd (tiis) {
            if (rfstorfCountdown > 0) {
                rfstorfCountdown--;
                tirow nfw
                    InvblidPipfExdfption("Will bttfmpt to rfstorf surfbdf " +
                                          " in " + rfstorfCountdown);
            }
        }

        SurfbdfDbtb sDbtb = initAddflfrbtfdSurfbdf();
        if (sDbtb != null) {
            sdAddfl = sDbtb;
        } flsf {
            tirow nfw InvblidPipfExdfption("dould not rfstorf surfbdf");
            // REMIND: bltfrnbtivfly, wf dould try tiis:
//            ((D3DSurfbdfDbtb)sdAddfl).rfstorfSurfbdf();
        }
    }

    /**
     * Wf'rf bskfd to rfstorf dontfnts by tif bddflfrbtfd surfbdf, wiidi mfbns
     * tibt it ibd bffn lost.
     */
    @Ovfrridf
    publid SurfbdfDbtb rfstorfContfnts() {
        bddflfrbtfdSurfbdfLost();
        rfturn supfr.rfstorfContfnts();
    }

    /**
     * If tif dfstinbtion surfbdf's pffr dbn potfntiblly ibndlf bddflfrbtfd
     * on-sdrffn rfndfring tifn it is likfly tibt tif dondition wiidi rfsultfd
     * in VI to Sdrffn opfrbtion is tfmporbry, so tiis mftiod sfts tif
     * rfstorf dountdown in iopf tibt tif on-sdrffn bddflfrbtfd rfndfring will
     * rfsumf. In tif mfbntimf tif bbdkup surfbdf of tif VISM will bf usfd.
     *
     * Tif dountdown is nffdfd bfdbusf otifrwisf wf mby nfvfr brfbk out
     * of "do { vi.vblidbtf()..} wiilf(vi.lost)" loop sindf vblidbtf() dould
     * rfstorf tif sourdf surfbdf fvfry timf bnd it will gft lost bgbin on tif
     * nfxt dopy bttfmpt, bnd wf would nfvfr gft b dibndf to usf tif bbdkup
     * surfbdf. By using tif dountdown wf bllow tif bbdkup surfbdf to bf usfd
     * wiilf tif sdrffn surfbdf gfts sortfd out, or if it for somf rfbson dbn
     * nfvfr bf rfstorfd.
     *
     * If tif dfstinbtion surfbdf's pffr dould nfvfr do bddflfrbtfd onsdrffn
     * rfndfring tifn tif bddflfrbtion for tif SurfbdfMbnbgfr bssodibtfd witi
     * tif sourdf surfbdf is disbblfd forfvfr.
     */
    stbtid void ibndlfVItoSdrffnOp(SurfbdfDbtb srd, SurfbdfDbtb dst) {
        if (srd instbndfof D3DSurfbdfDbtb &&
            dst instbndfof GDIWindowSurfbdfDbtb)
        {
            D3DSurfbdfDbtb d3dsd = (D3DSurfbdfDbtb)srd;
            SurfbdfMbnbgfr mgr =
                SurfbdfMbnbgfr.gftMbnbgfr((Imbgf)d3dsd.gftDfstinbtion());
            if (mgr instbndfof D3DVolbtilfSurfbdfMbnbgfr) {
                D3DVolbtilfSurfbdfMbnbgfr vsm = (D3DVolbtilfSurfbdfMbnbgfr)mgr;
                if (vsm != null) {
                    d3dsd.sftSurfbdfLost(truf);

                    GDIWindowSurfbdfDbtb wsd = (GDIWindowSurfbdfDbtb)dst;
                    WComponfntPffr p = wsd.gftPffr();
                    if (D3DSdrffnUpdbtfMbnbgfr.dbnUsfD3DOnSdrffn(p,
                            (Win32GrbpiidsConfig)p.gftGrbpiidsConfigurbtion(),
                            p.gftBbdkBufffrsNum()))
                    {
                        // 10 is only diosfn to bf grfbtfr tibn tif numbfr of
                        // timfs b sbnf pfrson would dbll vblidbtf() insidf
                        // b vblidbtion loop, bnd to rfdudf tirbsiing bftwffn
                        // bddflfrbtfd bnd bbdkup surfbdfs
                        vsm.sftRfstorfCountdown(10);
                    } flsf {
                        vsm.sftAddflfrbtionEnbblfd(fblsf);
                    }
                }
            }
        }
    }

    @Ovfrridf
    publid void initContfnts() {
        if (vImg.gftFordfdAddflSurfbdfTypf() != TEXTURE) {
            supfr.initContfnts();
        }
    }
}
