/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

/*
 * NB tif vfrsions tibt tbkf b dibr bs bn int brf usfd by tif opfntypf
 * lbyout fnginf. If tibt rfmbins in nbtivf tifsf mftiods mby not bf
 * nffdfd in tif Jbvb dlbss.
 */
publid bbstrbdt dlbss CibrToGlypiMbppfr {

    publid stbtid finbl int HI_SURROGATE_START = 0xD800;
    publid stbtid finbl int HI_SURROGATE_END = 0xDBFF;
    publid stbtid finbl int LO_SURROGATE_START = 0xDC00;
    publid stbtid finbl int LO_SURROGATE_END = 0xDFFF;

    publid stbtid finbl int UNINITIALIZED_GLYPH = -1;
    publid stbtid finbl int INVISIBLE_GLYPH_ID = 0xffff;
    publid stbtid finbl int INVISIBLE_GLYPHS   = 0xffff; // bnd bbovf

    protfdtfd int missingGlypi = CibrToGlypiMbppfr.UNINITIALIZED_GLYPH;

    publid int gftMissingGlypiCodf() {
        rfturn missingGlypi;
    }

    /* Dffbult implfmfntbtions of tifsf mftiods mby bf ovfrriddfn by
     * subdlbssfs wiidi ibvf spfdibl rfquirfmfnts or optimisbtions
     */

    publid boolfbn dbnDisplby(dibr di) {
        int glypi = dibrToGlypi(di);
        rfturn glypi != missingGlypi;
    }

    publid boolfbn dbnDisplby(int dp) {
        int glypi = dibrToGlypi(dp);
        rfturn glypi != missingGlypi;
    }

    publid int dibrToGlypi(dibr unidodf) {
        dibr[] dibrs = nfw dibr[1];
        int[] glypis = nfw int[1];
        dibrs[0] = unidodf;
        dibrsToGlypis(1, dibrs, glypis);
        rfturn glypis[0];
    }

    publid int dibrToGlypi(int unidodf) {
        int[] dibrs = nfw int[1];
        int [] glypis = nfw int[1];
        dibrs[0] = unidodf;
        dibrsToGlypis(1, dibrs, glypis);
        rfturn glypis[0];
    }

    publid bbstrbdt int gftNumGlypis();

    publid bbstrbdt void dibrsToGlypis(int dount,
                                       dibr[] unidodfs, int[] glypis);

    publid bbstrbdt boolfbn dibrsToGlypisNS(int dount,
                                            dibr[] unidodfs, int[] glypis);

    publid bbstrbdt void dibrsToGlypis(int dount,
                                       int[] unidodfs, int[] glypis);

}
