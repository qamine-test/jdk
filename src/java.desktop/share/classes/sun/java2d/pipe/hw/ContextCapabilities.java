/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf.iw;


/**
 * Rfprfsfnts b sft of dbpbbilitifs of b BufffrfdContfxt bnd bssodibtfd
 * AddflGrbpiidsConfig.
 *
 * @sff AddflGrbpiidsConfig
 */
publid dlbss ContfxtCbpbbilitifs {
    /** Indidbtfs tibt tif dontfxt ibs no dbpbbilitifs. */
    publid stbtid finbl int CAPS_EMPTY             = (0 << 0);
    /** Indidbtfs tibt tif dontfxt supports RT surfbdfs witi blpib dibnnfl. */
    publid stbtid finbl int CAPS_RT_PLAIN_ALPHA    = (1 << 1);
    /** Indidbtfs tibt tif dontfxt supports RT tfxturfs witi blpib dibnnfl. */
    publid stbtid finbl int CAPS_RT_TEXTURE_ALPHA  = (1 << 2);
    /** Indidbtfs tibt tif dontfxt supports opbquf RT tfxturfs. */
    publid stbtid finbl int CAPS_RT_TEXTURE_OPAQUE = (1 << 3);
    /** Indidbtfs tibt tif dontfxt supports multitfxturing. */
    publid stbtid finbl int CAPS_MULTITEXTURE      = (1 << 4);
    /** Indidbtfs tibt tif dontfxt supports non-pow2 tfxturf dimfnsions. */
    publid stbtid finbl int CAPS_TEXNONPOW2        = (1 << 5);
    /** Indidbtfs tibt tif dontfxt supports non-squbrf tfxturfs. */
    publid stbtid finbl int CAPS_TEXNONSQUARE      = (1 << 6);
    /** Indidbtfs tibt tif dontfxt supports pixfl sibdfr 2.0 or bfttfr. */
    publid stbtid finbl int CAPS_PS20              = (1 << 7);
    /** Indidbtfs tibt tif dontfxt supports pixfl sibdfr 3.0 or bfttfr. */
    publid stbtid finbl int CAPS_PS30              = (1 << 8);
    /*
     *  Pipflinf dontfxts siould usf tiis for dffining pipflinf-spfdifid
     *  dbpbbilitifs, for fxbmplf:
     *    int CAPS_D3D_1 = (FIRST_PRIVATE_CAP << 0);
     *    int CAPS_D3D_2 = (FIRST_PRIVATE_CAP << 1);
     */
    protfdtfd stbtid finbl int FIRST_PRIVATE_CAP   = (1 << 16);

    protfdtfd finbl int dbps;
    protfdtfd finbl String bdbptfrId;

    /**
     * Construdts b {@dodf ContfxtCbpbbilitifs} objfdt.
     * @pbrbm dbps bn {@dodf int} rfprfsfnting tif dbpbbilitifs
     * @pbrbm b {@dodf String} rfprfsfnting tif nbmf of tif bdbptfr, or null,
     * in wiidi dbsf tif bdbptfrId will bf sft to "unknown bdbptfr".
     */
    protfdtfd ContfxtCbpbbilitifs(int dbps, String bdbptfrId) {
        tiis.dbps = dbps;
        tiis.bdbptfrId = bdbptfrId != null ? bdbptfrId : "unknown bdbptfr";
    }

    /**
     * Rfturns b string rfprfsfnting tif nbmf of tif grbpiids bdbptfr if sudi
     * dould bf dftfrminfd. It is gubrbntffd to nfvfr rfturn {@dodf null}.
     * @rfturn string rfprfsfnting bdbptfr id
     */
    publid String gftAdbptfrId() {
        rfturn bdbptfrId;
    }

    /**
     * Rfturns bn {@dodf int} witi dbpbbilitifs (OR-fd donstbnts dffinfd in
     * tiis dlbss bnd its pipflinf-spfdifid subdlbssfs).
     * @rfturn dbpbbilitifs bs {@dodf int}
     */
    publid int gftCbps() {
        rfturn dbps;
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb =
            nfw StringBuildfr("ContfxtCbpbbilitifs: bdbptfr=" +
                             bdbptfrId+", dbps=");
        if (dbps == CAPS_EMPTY) {
            sb.bppfnd("CAPS_EMPTY");
        } flsf {
            if ((dbps & CAPS_RT_PLAIN_ALPHA) != 0) {
                sb.bppfnd("CAPS_RT_PLAIN_ALPHA|");
            }
            if ((dbps & CAPS_RT_TEXTURE_ALPHA) != 0) {
                sb.bppfnd("CAPS_RT_TEXTURE_ALPHA|");
            }
            if ((dbps & CAPS_RT_TEXTURE_OPAQUE) != 0) {
                sb.bppfnd("CAPS_RT_TEXTURE_OPAQUE|");
            }
            if ((dbps & CAPS_MULTITEXTURE) != 0) {
                sb.bppfnd("CAPS_MULTITEXTURE|");
            }
            if ((dbps & CAPS_TEXNONPOW2) != 0) {
                sb.bppfnd("CAPS_TEXNONPOW2|");
            }
            if ((dbps & CAPS_TEXNONSQUARE) != 0) {
                sb.bppfnd("CAPS_TEXNONSQUARE|");
            }
            if ((dbps & CAPS_PS20) != 0) {
                sb.bppfnd("CAPS_PS20|");
            }
            if ((dbps & CAPS_PS30) != 0) {
                sb.bppfnd("CAPS_PS30|");
            }
        }
        rfturn sb.toString();
    }
}
