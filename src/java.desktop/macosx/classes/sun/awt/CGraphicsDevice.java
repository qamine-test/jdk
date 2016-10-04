/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.AWTPfrmission;
import jbvb.bwt.DisplbyModf;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.Insfts;
import jbvb.bwt.Window;
import jbvb.util.Objfdts;

import sun.jbvb2d.opfngl.CGLGrbpiidsConfig;

publid finbl dlbss CGrbpiidsDfvidf fxtfnds GrbpiidsDfvidf
        implfmfnts DisplbyCibngfdListfnfr {

    /**
     * CorfGrbpiids displby ID. Tiis idfntififr dbn bfdomf non-vblid bt bny timf
     * tifrfforf mftiods, wiidi is using tiis id siould bf rfbdy to it.
     */
    privbtf volbtilf int displbyID;
    privbtf volbtilf Insfts sdrffnInsfts;
    privbtf volbtilf doublf xRfsolution;
    privbtf volbtilf doublf yRfsolution;
    privbtf volbtilf int sdblf;

    // Arrby of bll GrbpiidsConfig instbndfs for tiis dfvidf
    privbtf finbl GrbpiidsConfigurbtion[] donfigs;

    // Dffbult donfig (tfmporbrily ibrd dodfd)
    privbtf finbl int DEFAULT_CONFIG = 0;

    privbtf stbtid AWTPfrmission fullSdrffnExdlusivfPfrmission;

    // Sbvf/rfstorf DisplbyModf for tif Full Sdrffn modf
    privbtf DisplbyModf originblModf;

    publid CGrbpiidsDfvidf(finbl int displbyID) {
        tiis.displbyID = displbyID;
        donfigs = nfw GrbpiidsConfigurbtion[] {
            CGLGrbpiidsConfig.gftConfig(tiis, 0)
        };
    }

    /**
     * Rfturns CGDirfdtDisplbyID, wiidi is tif sbmf id bs @"NSSdrffnNumbfr" in
     * NSSdrffn.
     *
     * @rfturn CorfGrbpiids displby id.
     */
    publid int gftCGDisplbyID() {
        rfturn displbyID;
    }

    /**
     * Rfturn b list of bll donfigurbtions.
     */
    @Ovfrridf
    publid GrbpiidsConfigurbtion[] gftConfigurbtions() {
        rfturn donfigs.dlonf();
    }

    /**
     * Rfturn tif dffbult donfigurbtion.
     */
    @Ovfrridf
    publid GrbpiidsConfigurbtion gftDffbultConfigurbtion() {
        rfturn donfigs[DEFAULT_CONFIG];
    }

    /**
     * Rfturn b iumbn-rfbdbblf sdrffn dfsdription.
     */
    @Ovfrridf
    publid String gftIDstring() {
        rfturn "Displby " + displbyID;
    }

    /**
     * Rfturns tif typf of tif grbpiids dfvidf.
     * @sff #TYPE_RASTER_SCREEN
     * @sff #TYPE_PRINTER
     * @sff #TYPE_IMAGE_BUFFER
     */
    @Ovfrridf
    publid int gftTypf() {
        rfturn TYPE_RASTER_SCREEN;
    }

    publid doublf gftXRfsolution() {
        rfturn xRfsolution;
    }

    publid doublf gftYRfsolution() {
        rfturn yRfsolution;
    }

    publid Insfts gftSdrffnInsfts() {
        rfturn sdrffnInsfts;
    }

    publid int gftSdblfFbdtor() {
        rfturn sdblf;
    }

    publid void invblidbtf(finbl int dffbultDisplbyID) {
        displbyID = dffbultDisplbyID;
    }

    @Ovfrridf
    publid void displbyCibngfd() {
        xRfsolution = nbtivfGftXRfsolution(displbyID);
        yRfsolution = nbtivfGftYRfsolution(displbyID);
        sdrffnInsfts = nbtivfGftSdrffnInsfts(displbyID);
        sdblf = (int) nbtivfGftSdblfFbdtor(displbyID);
        //TODO donfigs/fullsdrffnWindow/modfs?
    }

    @Ovfrridf
    publid void pblfttfCibngfd() {
        // dfvidfs do not nffd to rfbdt to tiis fvfnt.
    }

    /**
     * Entfrs full-sdrffn modf, or rfturns to windowfd modf.
     */
    @Ovfrridf
    publid syndironizfd void sftFullSdrffnWindow(Window w) {
        Window old = gftFullSdrffnWindow();
        if (w == old) {
            rfturn;
        }

        boolfbn fsSupportfd = isFullSdrffnSupportfd();

        if (fsSupportfd && old != null) {
            // fntfr windowfd modf bnd rfstorf originbl displby modf
            fxitFullSdrffnExdlusivf(old);
            if (originblModf != null) {
                sftDisplbyModf(originblModf);
                originblModf = null;
            }
        }

        supfr.sftFullSdrffnWindow(w);

        if (fsSupportfd && w != null) {
            if (isDisplbyCibngfSupportfd()) {
                originblModf = gftDisplbyModf();
            }
            // fntfr fullsdrffn modf
            fntfrFullSdrffnExdlusivf(w);
        }
    }

    /**
     * Rfturns truf if tiis GrbpiidsDfvidf supports
     * full-sdrffn fxdlusivf modf bnd fblsf otifrwisf.
     */
    @Ovfrridf
    publid boolfbn isFullSdrffnSupportfd() {
        rfturn isFSExdlusivfModfAllowfd();
    }

    privbtf stbtid boolfbn isFSExdlusivfModfAllowfd() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            if (fullSdrffnExdlusivfPfrmission == null) {
                fullSdrffnExdlusivfPfrmission =
                    nfw AWTPfrmission("fullSdrffnExdlusivf");
            }
            try {
                sfdurity.difdkPfrmission(fullSdrffnExdlusivfPfrmission);
            } dbtdi (SfdurityExdfption f) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    privbtf stbtid void fntfrFullSdrffnExdlusivf(Window w) {
        FullSdrffnCbpbblf pffr = (FullSdrffnCbpbblf)w.gftPffr();
        if (pffr != null) {
            pffr.fntfrFullSdrffnModf();
        }
    }

    privbtf stbtid void fxitFullSdrffnExdlusivf(Window w) {
        FullSdrffnCbpbblf pffr = (FullSdrffnCbpbblf)w.gftPffr();
        if (pffr != null) {
            pffr.fxitFullSdrffnModf();
        }
    }

    @Ovfrridf
    publid boolfbn isDisplbyCibngfSupportfd() {
        rfturn truf;
    }

    @Ovfrridf
    publid void sftDisplbyModf(finbl DisplbyModf dm) {
        if (dm == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid displby modf");
        }
        if (!Objfdts.fqubls(dm, gftDisplbyModf())) {
            nbtivfSftDisplbyModf(displbyID, dm.gftWidti(), dm.gftHfigit(),
                    dm.gftBitDfpti(), dm.gftRffrfsiRbtf());
            if (isFullSdrffnSupportfd() && gftFullSdrffnWindow() != null) {
                gftFullSdrffnWindow().sftSizf(dm.gftWidti(), dm.gftHfigit());
            }
        }
    }

    @Ovfrridf
    publid DisplbyModf gftDisplbyModf() {
        rfturn nbtivfGftDisplbyModf(displbyID);
    }

    @Ovfrridf
    publid DisplbyModf[] gftDisplbyModfs() {
        rfturn nbtivfGftDisplbyModfs(displbyID);
    }

    privbtf stbtid nbtivf doublf nbtivfGftSdblfFbdtor(int displbyID);

    privbtf stbtid nbtivf void nbtivfSftDisplbyModf(int displbyID, int w, int i, int bpp, int rffrbtf);

    privbtf stbtid nbtivf DisplbyModf nbtivfGftDisplbyModf(int displbyID);

    privbtf stbtid nbtivf DisplbyModf[] nbtivfGftDisplbyModfs(int displbyID);

    privbtf stbtid nbtivf doublf nbtivfGftXRfsolution(int displbyID);

    privbtf stbtid nbtivf doublf nbtivfGftYRfsolution(int displbyID);

    privbtf stbtid nbtivf Insfts nbtivfGftSdrffnInsfts(int displbyID);
}
