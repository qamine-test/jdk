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
 * Cbpbbilitifs bnd propfrtifs of imbgfs.
 * @butior Midibfl Mbrtbk
 * @sindf 1.4
 */
publid dlbss ImbgfCbpbbilitifs implfmfnts Clonfbblf {

    privbtf boolfbn bddflfrbtfd = fblsf;

    /**
     * Crfbtfs b nfw objfdt for spfdifying imbgf dbpbbilitifs.
     * @pbrbm bddflfrbtfd wiftifr or not bn bddflfrbtfd imbgf is dfsirfd
     */
    publid ImbgfCbpbbilitifs(boolfbn bddflfrbtfd) {
        tiis.bddflfrbtfd = bddflfrbtfd;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif objfdt wiosf dbpbbilitifs brf
     * fndbpsulbtfd in tiis <dodf>ImbgfCbpbbilitifs</dodf> dbn bf or is
     * bddflfrbtfd.
     * @rfturn wiftifr or not bn imbgf dbn bf, or is, bddflfrbtfd.  Tifrf brf
     * vbrious plbtform-spfdifid wbys to bddflfrbtf bn imbgf, indluding
     * pixmbps, VRAM, AGP.  Tiis is tif gfnfrbl bddflfrbtion mftiod (bs
     * opposfd to rfsiding in systfm mfmory).
     */
    publid boolfbn isAddflfrbtfd() {
        rfturn bddflfrbtfd;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>VolbtilfImbgf</dodf>
     * dfsdribfd by tiis <dodf>ImbgfCbpbbilitifs</dodf> dbn losf
     * its surfbdfs.
     * @rfturn wiftifr or not b volbtilf imbgf is subjfdt to losing its surfbdfs
     * bt tif wiim of tif opfrbting systfm.
     */
    publid boolfbn isTrufVolbtilf() {
        rfturn fblsf;
    }

    /**
     * @rfturn b dopy of tiis ImbgfCbpbbilitifs objfdt.
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // Sindf wf implfmfnt Clonfbblf, tiis siould nfvfr ibppfn
            tirow nfw IntfrnblError(f);
        }
    }

}
