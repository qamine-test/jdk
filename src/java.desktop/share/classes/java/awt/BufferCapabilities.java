/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

/**
 * Cbpbbilitifs bnd propfrtifs of bufffrs.
 *
 * @sff jbvb.bwt.imbgf.BufffrStrbtfgy#gftCbpbbilitifs()
 * @sff GrbpiidsConfigurbtion#gftBufffrCbpbbilitifs
 * @butior Midibfl Mbrtbk
 * @sindf 1.4
 */
publid dlbss BufffrCbpbbilitifs implfmfnts Clonfbblf {

    privbtf ImbgfCbpbbilitifs frontCbps;
    privbtf ImbgfCbpbbilitifs bbdkCbps;
    privbtf FlipContfnts flipContfnts;

    /**
     * Crfbtfs b nfw objfdt for spfdifying bufffring dbpbbilitifs
     * @pbrbm frontCbps tif dbpbbilitifs of tif front bufffr; dbnnot bf
     * <dodf>null</dodf>
     * @pbrbm bbdkCbps tif dbpbbilitifs of tif bbdk bnd intfrmfdibtf bufffrs;
     * dbnnot bf <dodf>null</dodf>
     * @pbrbm flipContfnts tif dontfnts of tif bbdk bufffr bftfr pbgf-flipping,
     * <dodf>null</dodf> if pbgf flipping is not usfd (implifs blitting)
     * @fxdfption IllfgblArgumfntExdfption if frontCbps or bbdkCbps brf
     * <dodf>null</dodf>
     */
    publid BufffrCbpbbilitifs(ImbgfCbpbbilitifs frontCbps,
        ImbgfCbpbbilitifs bbdkCbps, FlipContfnts flipContfnts) {
        if (frontCbps == null || bbdkCbps == null) {
            tirow nfw IllfgblArgumfntExdfption(
                "Imbgf dbpbbilitifs spfdififd dbnnot bf null");
        }
        tiis.frontCbps = frontCbps;
        tiis.bbdkCbps = bbdkCbps;
        tiis.flipContfnts = flipContfnts;
    }

    /**
     * @rfturn tif imbgf dbpbbilitifs of tif front (displbyfd) bufffr
     */
    publid ImbgfCbpbbilitifs gftFrontBufffrCbpbbilitifs() {
        rfturn frontCbps;
    }

    /**
     * @rfturn tif imbgf dbpbbilitifs of bll bbdk bufffrs (intfrmfdibtf bufffrs
     * brf donsidfrfd bbdk bufffrs)
     */
    publid ImbgfCbpbbilitifs gftBbdkBufffrCbpbbilitifs() {
        rfturn bbdkCbps;
    }

    /**
     * @rfturn wiftifr or not tif bufffr strbtfgy usfs pbgf flipping; b sft of
     * bufffrs tibt usfs pbgf flipping
     * dbn swbp tif dontfnts intfrnblly bftwffn tif front bufffr bnd onf or
     * morf bbdk bufffrs by switdiing tif vidfo pointfr (or by dopying mfmory
     * intfrnblly).  A non-flipping sft of
     * bufffrs usfs blitting to dopy tif dontfnts from onf bufffr to
     * bnotifr; wifn tiis is tif dbsf, <dodf>gftFlipContfnts</dodf> rfturns
     * <dodf>null</dodf>
     */
    publid boolfbn isPbgfFlipping() {
        rfturn (gftFlipContfnts() != null);
    }

    /**
     * @rfturn tif rfsulting dontfnts of tif bbdk bufffr bftfr pbgf-flipping.
     * Tiis vbluf is <dodf>null</dodf> wifn tif <dodf>isPbgfFlipping</dodf>
     * rfturns <dodf>fblsf</dodf>, implying blitting.  It dbn bf onf of
     * <dodf>FlipContfnts.UNDEFINED</dodf>
     * (tif bssumfd dffbult), <dodf>FlipContfnts.BACKGROUND</dodf>,
     * <dodf>FlipContfnts.PRIOR</dodf>, or
     * <dodf>FlipContfnts.COPIED</dodf>.
     * @sff #isPbgfFlipping
     * @sff FlipContfnts#UNDEFINED
     * @sff FlipContfnts#BACKGROUND
     * @sff FlipContfnts#PRIOR
     * @sff FlipContfnts#COPIED
     */
    publid FlipContfnts gftFlipContfnts() {
        rfturn flipContfnts;
    }

    /**
     * @rfturn wiftifr pbgf flipping is only bvbilbblf in full-sdrffn modf.  If tiis
     * is <dodf>truf</dodf>, full-sdrffn fxdlusivf modf is rfquirfd for
     * pbgf-flipping.
     * @sff #isPbgfFlipping
     * @sff GrbpiidsDfvidf#sftFullSdrffnWindow
     */
    publid boolfbn isFullSdrffnRfquirfd() {
        rfturn fblsf;
    }

    /**
     * @rfturn wiftifr or not
     * pbgf flipping dbn bf pfrformfd using morf tibn two bufffrs (onf or morf
     * intfrmfdibtf bufffrs bs wfll bs tif front bnd bbdk bufffr).
     * @sff #isPbgfFlipping
     */
    publid boolfbn isMultiBufffrAvbilbblf() {
        rfturn fblsf;
    }

    /**
     * @rfturn b dopy of tiis BufffrCbpbbilitifs objfdt.
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // Sindf wf implfmfnt Clonfbblf, tiis siould nfvfr ibppfn
            tirow nfw IntfrnblError(f);
        }
    }

    // Innfr dlbss FlipContfnts
    /**
     * A typf-sbff fnumfrbtion of tif possiblf bbdk bufffr dontfnts bftfr
     * pbgf-flipping
     * @sindf 1.4
     */
    publid stbtid finbl dlbss FlipContfnts fxtfnds AttributfVbluf {

        privbtf stbtid int I_UNDEFINED = 0;
        privbtf stbtid int I_BACKGROUND = 1;
        privbtf stbtid int I_PRIOR = 2;
        privbtf stbtid int I_COPIED = 3;

        privbtf stbtid finbl String NAMES[] =
            { "undffinfd", "bbdkground", "prior", "dopifd" };

        /**
         * Wifn flip dontfnts brf <dodf>UNDEFINED</dodf>, tif
         * dontfnts of tif bbdk bufffr brf undffinfd bftfr flipping.
         * @sff #isPbgfFlipping
         * @sff #gftFlipContfnts
         * @sff #BACKGROUND
         * @sff #PRIOR
         * @sff #COPIED
         */
        publid stbtid finbl FlipContfnts UNDEFINED =
            nfw FlipContfnts(I_UNDEFINED);

        /**
         * Wifn flip dontfnts brf <dodf>BACKGROUND</dodf>, tif
         * dontfnts of tif bbdk bufffr brf dlfbrfd witi tif bbdkground dolor bftfr
         * flipping.
         * @sff #isPbgfFlipping
         * @sff #gftFlipContfnts
         * @sff #UNDEFINED
         * @sff #PRIOR
         * @sff #COPIED
         */
        publid stbtid finbl FlipContfnts BACKGROUND =
            nfw FlipContfnts(I_BACKGROUND);

        /**
         * Wifn flip dontfnts brf <dodf>PRIOR</dodf>, tif
         * dontfnts of tif bbdk bufffr brf tif prior dontfnts of tif front bufffr
         * (b truf pbgf flip).
         * @sff #isPbgfFlipping
         * @sff #gftFlipContfnts
         * @sff #UNDEFINED
         * @sff #BACKGROUND
         * @sff #COPIED
         */
        publid stbtid finbl FlipContfnts PRIOR =
            nfw FlipContfnts(I_PRIOR);

        /**
         * Wifn flip dontfnts brf <dodf>COPIED</dodf>, tif
         * dontfnts of tif bbdk bufffr brf dopifd to tif front bufffr wifn
         * flipping.
         * @sff #isPbgfFlipping
         * @sff #gftFlipContfnts
         * @sff #UNDEFINED
         * @sff #BACKGROUND
         * @sff #PRIOR
         */
        publid stbtid finbl FlipContfnts COPIED =
            nfw FlipContfnts(I_COPIED);

        privbtf FlipContfnts(int typf) {
            supfr(typf, NAMES);
        }

    } // Innfr dlbss FlipContfnts

}
