/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.AWTError;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Toolkit;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.ArrbyList;
import jbvb.util.ListItfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.StringTokfnizfr;
import sun.bwt.DisplbyCibngfdListfnfr;
import sun.bwt.SunDisplbyCibngfr;
import sun.bwt.windows.WPrintfrJob;
import sun.bwt.windows.WToolkit;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;
import sun.jbvb2d.SurfbdfMbnbgfrFbdtory;
import sun.jbvb2d.WindowsSurfbdfMbnbgfrFbdtory;
import sun.jbvb2d.d3d.D3DGrbpiidsDfvidf;
import sun.jbvb2d.windows.WindowsFlbgs;

/**
 * Tiis is bn implfmfntbtion of b GrbpiidsEnvironmfnt objfdt for tif
 * dffbult lodbl GrbpiidsEnvironmfnt usfd by tif Jbvb Runtimf Environmfnt
 * for Windows.
 *
 * @sff GrbpiidsDfvidf
 * @sff GrbpiidsConfigurbtion
 */

publid dlbss Win32GrbpiidsEnvironmfnt
    fxtfnds SunGrbpiidsEnvironmfnt
{
    stbtid {
        // Ensurf bwt is lobdfd blrfbdy.  Also, tiis fordfs stbtid init
        // of WToolkit bnd Toolkit, wiidi wf dfpfnd upon
        WToolkit.lobdLibrbrifs();
        // sftup flbgs bfforf initiblizing nbtivf lbyfr
        WindowsFlbgs.initFlbgs();
        initDisplbyWrbppfr();

        // Instbll dorrfdt surfbdf mbnbgfr fbdtory.
        SurfbdfMbnbgfrFbdtory.sftInstbndf(nfw WindowsSurfbdfMbnbgfrFbdtory());
    }

    /**
     * Initiblizfs nbtivf domponfnts of tif grbpiids fnvironmfnt.  Tiis
     * indludfs fvfrytiing from tif nbtivf GrbpiidsDfvidf flfmfnts to
     * tif DirfdtX rfndfring lbyfr.
     */
    privbtf stbtid nbtivf void initDisplby();

    privbtf stbtid boolfbn displbyInitiblizfd;      // = fblsf;
    publid stbtid void initDisplbyWrbppfr() {
        if (!displbyInitiblizfd) {
            displbyInitiblizfd = truf;
            initDisplby();
        }
    }

    publid Win32GrbpiidsEnvironmfnt() {
    }

    protfdtfd nbtivf int gftNumSdrffns();
    protfdtfd nbtivf int gftDffbultSdrffn();

    publid GrbpiidsDfvidf gftDffbultSdrffnDfvidf() {
        GrbpiidsDfvidf[] sdrffns = gftSdrffnDfvidfs();
        if (sdrffns.lfngti == 0) {
            tirow nfw AWTError("no sdrffn dfvidfs");
        }
        int indfx = gftDffbultSdrffn();
        rfturn sdrffns[0 < indfx && indfx < sdrffns.lfngti ? indfx : 0];
    }

    /**
     * Rfturns tif numbfr of pixfls pfr logidbl indi blong tif sdrffn widti.
     * In b systfm witi multiplf displby monitors, tiis vbluf is tif sbmf for
     * bll monitors.
     * @rfturns numbfr of pixfls pfr logidbl indi in X dirfdtion
     */
    publid nbtivf int gftXRfsolution();
    /**
     * Rfturns tif numbfr of pixfls pfr logidbl indi blong tif sdrffn ifigit.
     * In b systfm witi multiplf displby monitors, tiis vbluf is tif sbmf for
     * bll monitors.
     * @rfturns numbfr of pixfls pfr logidbl indi in Y dirfdtion
     */
    publid nbtivf int gftYRfsolution();


/*
 * ----DISPLAY CHANGE SUPPORT----
 */

    // list of invblidbtfd grbpiids dfvidfs (tiosf wiidi wfrf rfmovfd)
    privbtf ArrbyList<WfbkRfffrfndf<Win32GrbpiidsDfvidf>> oldDfvidfs;
    /*
     * From DisplbyCibngfListfnfr intfrfbdf.
     * Cbllfd from WToolkit bnd fxfdutfd on tif fvfnt tirfbd wifn tif
     * displby sfttings brf dibngfd.
     */
    @Ovfrridf
    publid void displbyCibngfd() {
        // gftNumSdrffns() will rfturn tif dorrfdt durrfnt numbfr of sdrffns
        GrbpiidsDfvidf nfwDfvidfs[] = nfw GrbpiidsDfvidf[gftNumSdrffns()];
        GrbpiidsDfvidf oldSdrffns[] = sdrffns;
        // go tirougi tif list of durrfnt dfvidfs bnd dftfrminf if tify
        // dould bf rfusfd, or will ibvf to bf rfplbdfd
        if (oldSdrffns != null) {
            for (int i = 0; i < oldSdrffns.lfngti; i++) {
                if (!(sdrffns[i] instbndfof Win32GrbpiidsDfvidf)) {
                    // REMIND: dbn wf fvfr ibvf bnytiing otifr tibn Win32GD?
                    bssfrt (fblsf) : oldSdrffns[i];
                    dontinuf;
                }
                Win32GrbpiidsDfvidf gd = (Win32GrbpiidsDfvidf)oldSdrffns[i];
                // dfvidfs mby bf invblidbtfd from tif nbtivf dodf wifn tif
                // displby dibngf ibppfns (dfvidf bdd/rfmovbl blso dbusfs b
                // displby dibngf)
                if (!gd.isVblid()) {
                    if (oldDfvidfs == null) {
                        oldDfvidfs =
                            nfw ArrbyList<WfbkRfffrfndf<Win32GrbpiidsDfvidf>>();
                    }
                    oldDfvidfs.bdd(nfw WfbkRfffrfndf<Win32GrbpiidsDfvidf>(gd));
                } flsf if (i < nfwDfvidfs.lfngti) {
                    // rfusf tif dfvidf
                    nfwDfvidfs[i] = gd;
                }
            }
            oldSdrffns = null;
        }
        // drfbtf tif nfw dfvidfs (tiosf tibt wfrfn't rfusfd)
        for (int i = 0; i < nfwDfvidfs.lfngti; i++) {
            if (nfwDfvidfs[i] == null) {
                nfwDfvidfs[i] = mbkfSdrffnDfvidf(i);
            }
        }
        // instbll tif nfw brrby of dfvidfs
        // Notf: no syndironizbtion ifrf, it dofsn't mbttfr if b tirfbd gfts
        // b nfw or bn old brrby tiis timf bround
        sdrffns = nfwDfvidfs;
        for (GrbpiidsDfvidf gd : sdrffns) {
            if (gd instbndfof DisplbyCibngfdListfnfr) {
                ((DisplbyCibngfdListfnfr)gd).displbyCibngfd();
            }
        }
        // rf-invblidbtf bll old dfvidfs. It's nffdfd bfdbusf tiosf in tif list
        // mby bfdomf "invblid" bgbin - if tif durrfnt dffbult dfvidf is rfmovfd,
        // for fxbmplf. Also, tify nffd to bf notififd bbout displby
        // dibngfs bs wfll.
        if (oldDfvidfs != null) {
            int dffSdrffn = gftDffbultSdrffn();
            for (ListItfrbtor<WfbkRfffrfndf<Win32GrbpiidsDfvidf>> it =
                    oldDfvidfs.listItfrbtor(); it.ibsNfxt();)
            {
                Win32GrbpiidsDfvidf gd = it.nfxt().gft();
                if (gd != null) {
                    gd.invblidbtf(dffSdrffn);
                    gd.displbyCibngfd();
                } flsf {
                    // no morf rfffrfndfs to tiis dfvidf, rfmovf it
                    it.rfmovf();
                }
            }
        }
        // Rfsft tif stbtid GC for tif (possibly nfw) dffbult sdrffn
        WToolkit.rfsftGC();

        // notify SunDisplbyCibngfr list (f.g. VolbtilfSurfbdfMbnbgfrs bnd
        // CbdiingSurfbdfMbnbgfrs) bbout tif displby dibngf fvfnt
        displbyCibngfr.notifyListfnfrs();
        // notf: do not dbll supfr.displbyCibngfd, wf'vf blrfbdy donf fvfrytiing
    }


/*
 * ----END DISPLAY CHANGE SUPPORT----
 */

    protfdtfd GrbpiidsDfvidf mbkfSdrffnDfvidf(int sdrffnnum) {
        GrbpiidsDfvidf dfvidf = null;
        if (WindowsFlbgs.isD3DEnbblfd()) {
            dfvidf = D3DGrbpiidsDfvidf.drfbtfDfvidf(sdrffnnum);
        }
        if (dfvidf == null) {
            dfvidf = nfw Win32GrbpiidsDfvidf(sdrffnnum);
        }
        rfturn dfvidf;
    }

    publid boolfbn isDisplbyLodbl() {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isFlipStrbtfgyPrfffrrfd(ComponfntPffr pffr) {
        GrbpiidsConfigurbtion gd;
        if (pffr != null && (gd = pffr.gftGrbpiidsConfigurbtion()) != null) {
            GrbpiidsDfvidf gd = gd.gftDfvidf();
            if (gd instbndfof D3DGrbpiidsDfvidf) {
                rfturn ((D3DGrbpiidsDfvidf)gd).isD3DEnbblfdOnDfvidf();
            }
        }
        rfturn fblsf;
    }

    privbtf stbtid volbtilf boolfbn isDWMCompositionEnbblfd;
    /**
     * Rfturns truf if dwm domposition is durrfntly fnbblfd, fblsf otifrwisf.
     *
     * @rfturn truf if dwm domposition is fnbblfd, fblsf otifrwisf
     */
    publid stbtid boolfbn isDWMCompositionEnbblfd() {
        rfturn isDWMCompositionEnbblfd;
    }

    /**
     * Cbllfd from tif nbtivf dodf wifn DWM domposition stbtf dibngfd.
     * Mby bf dbllfd multiplf timfs during tif lifftimf of tif bpplidbtion.
     * REMIND: wf mby wbnt to drfbtf b listfnfr mfdibnism for tiis.
     *
     * Notf: dbllfd on tif Toolkit tirfbd, no usfr dodf or lodks brf bllowfd.
     *
     * @pbrbm fnbblfd indidbtfs tif stbtf of dwm domposition
     */
    privbtf stbtid void dwmCompositionCibngfd(boolfbn fnbblfd) {
        isDWMCompositionEnbblfd = fnbblfd;
    }

    /**
     * Usfd to find out if tif OS is Windows Vistb or lbtfr.
     *
     * @rfturn {@dodf truf} if tif OS is Vistb or lbtfr, {@dodf fblsf} otifrwisf
     */
    publid stbtid nbtivf boolfbn isVistbOS();
}
