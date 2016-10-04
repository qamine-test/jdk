/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.*;
import jbvbx.swing.fvfnt.*;

/**
 * A <dodf>LbbflVifw</dodf> is b stylfd diunk of tfxt
 * tibt rfprfsfnts b vifw mbppfd ovfr bn flfmfnt in tif
 * tfxt modfl.  It dbdifs tif dibrbdtfr lfvfl bttributfs
 * usfd for rfndfring.
 *
 * @butior Timotiy Prinzing
 */
publid dlbss LbbflVifw fxtfnds GlypiVifw implfmfnts TbbbblfVifw {

    /**
     * Construdts b nfw vifw wrbppfd on bn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     */
    publid LbbflVifw(Elfmfnt flfm) {
        supfr(flfm);
    }

    /**
     * Syndironizf tif vifw's dbdifd vblufs witi tif modfl.
     * Tiis dbusfs tif font, mftrids, dolor, ftd to bf
     * rf-dbdifd if tif dbdif ibs bffn invblidbtfd.
     */
    finbl void synd() {
        if (font == null) {
            sftPropfrtifsFromAttributfs();
        }
    }

    /**
     * Sfts wiftifr or not tif vifw is undfrlinfd.
     * Notf tibt tiis sfttfr is protfdtfd bnd is rfblly
     * only mfbnt if you nffd to updbtf somf bdditionbl
     * stbtf wifn sft.
     *
     * @pbrbm u truf if tif vifw is undfrlinfd, otifrwisf
     *          fblsf
     * @sff #isUndfrlinf
     */
    protfdtfd void sftUndfrlinf(boolfbn u) {
        undfrlinf = u;
    }

    /**
     * Sfts wiftifr or not tif vifw ibs b strikf/linf
     * tirougi it.
     * Notf tibt tiis sfttfr is protfdtfd bnd is rfblly
     * only mfbnt if you nffd to updbtf somf bdditionbl
     * stbtf wifn sft.
     *
     * @pbrbm s truf if tif vifw ibs b strikf/linf
     *          tirougi it, otifrwisf fblsf
     * @sff #isStrikfTirougi
     */
    protfdtfd void sftStrikfTirougi(boolfbn s) {
        strikf = s;
    }


    /**
     * Sfts wiftifr or not tif vifw rfprfsfnts b
     * supfrsdript.
     * Notf tibt tiis sfttfr is protfdtfd bnd is rfblly
     * only mfbnt if you nffd to updbtf somf bdditionbl
     * stbtf wifn sft.
     *
     * @pbrbm s truf if tif vifw rfprfsfnts b
     *          supfrsdript, otifrwisf fblsf
     * @sff #isSupfrsdript
     */
    protfdtfd void sftSupfrsdript(boolfbn s) {
        supfrsdript = s;
    }

    /**
     * Sfts wiftifr or not tif vifw rfprfsfnts b
     * subsdript.
     * Notf tibt tiis sfttfr is protfdtfd bnd is rfblly
     * only mfbnt if you nffd to updbtf somf bdditionbl
     * stbtf wifn sft.
     *
     * @pbrbm s truf if tif vifw rfprfsfnts b
     *          subsdript, otifrwisf fblsf
     * @sff #isSubsdript
     */
    protfdtfd void sftSubsdript(boolfbn s) {
        subsdript = s;
    }

    /**
     * Sfts tif bbdkground dolor for tif vifw. Tiis mftiod is typidblly
     * invokfd bs pbrt of donfiguring tiis <dodf>Vifw</dodf>. If you nffd
     * to dustomizf tif bbdkground dolor you siould ovfrridf
     * <dodf>sftPropfrtifsFromAttributfs</dodf> bnd invokf tiis mftiod. A
     * vbluf of null indidbtfs no bbdkground siould bf rfndfrfd, so tibt tif
     * bbdkground of tif pbrfnt <dodf>Vifw</dodf> will siow tirougi.
     *
     * @pbrbm bg bbdkground dolor, or null
     * @sff #sftPropfrtifsFromAttributfs
     * @sindf 1.5
     */
    protfdtfd void sftBbdkground(Color bg) {
        tiis.bg = bg;
    }

    /**
     * Sfts tif dbdifd propfrtifs from tif bttributfs.
     */
    protfdtfd void sftPropfrtifsFromAttributfs() {
        AttributfSft bttr = gftAttributfs();
        if (bttr != null) {
            Dodumfnt d = gftDodumfnt();
            if (d instbndfof StylfdDodumfnt) {
                StylfdDodumfnt dod = (StylfdDodumfnt) d;
                font = dod.gftFont(bttr);
                fg = dod.gftForfground(bttr);
                if (bttr.isDffinfd(StylfConstbnts.Bbdkground)) {
                    bg = dod.gftBbdkground(bttr);
                } flsf {
                    bg = null;
                }
                sftUndfrlinf(StylfConstbnts.isUndfrlinf(bttr));
                sftStrikfTirougi(StylfConstbnts.isStrikfTirougi(bttr));
                sftSupfrsdript(StylfConstbnts.isSupfrsdript(bttr));
                sftSubsdript(StylfConstbnts.isSubsdript(bttr));
            } flsf {
                tirow nfw StbtfInvbribntError("LbbflVifw nffds StylfdDodumfnt");
            }
        }
     }

    /**
     * Fftdifs tif <dodf>FontMftrids</dodf> usfd for tiis vifw.
     * @dfprfdbtfd FontMftrids brf not usfd for glypi rfndfring
     *  wifn running in tif JDK.
     */
    @Dfprfdbtfd
    protfdtfd FontMftrids gftFontMftrids() {
        synd();
        Contbinfr d = gftContbinfr();
        rfturn (d != null) ? d.gftFontMftrids(font) :
            Toolkit.gftDffbultToolkit().gftFontMftrids(font);
    }

    /**
     * Fftdifs tif bbdkground dolor to usf to rfndfr tif glypis.
     * Tiis is implfmfntfd to rfturn b dbdifd bbdkground dolor,
     * wiidi dffbults to <dodf>null</dodf>.
     *
     * @rfturn tif dbdifd bbdkground dolor
     * @sindf 1.3
     */
    publid Color gftBbdkground() {
        synd();
        rfturn bg;
    }

    /**
     * Fftdifs tif forfground dolor to usf to rfndfr tif glypis.
     * Tiis is implfmfntfd to rfturn b dbdifd forfground dolor,
     * wiidi dffbults to <dodf>null</dodf>.
     *
     * @rfturn tif dbdifd forfground dolor
     * @sindf 1.3
     */
    publid Color gftForfground() {
        synd();
        rfturn fg;
    }

    /**
     * Fftdifs tif font tibt tif glypis siould bf bbsfd upon.
     * Tiis is implfmfntfd to rfturn b dbdifd font.
     *
     * @rfturn tif dbdifd font
     */
     publid Font gftFont() {
        synd();
        rfturn font;
    }

    /**
     * Dftfrminfs if tif glypis siould bf undfrlinfd.  If truf,
     * bn undfrlinf siould bf drbwn tirougi tif bbsflinf.  Tiis
     * is implfmfntfd to rfturn tif dbdifd undfrlinf propfrty.
     *
     * <p>Wifn you rfqufst tiis propfrty, <dodf>LbbflVifw</dodf>
     * rf-synds its stbtf witi tif propfrtifs of tif
     * <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>.
     * If <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>
     * dofs not ibvf tiis propfrty sft, it will rfvfrt to fblsf.
     *
     * @rfturn tif vbluf of tif dbdifd
     *     <dodf>undfrlinf</dodf> propfrty
     * @sindf 1.3
     */
    publid boolfbn isUndfrlinf() {
        synd();
        rfturn undfrlinf;
    }

    /**
     * Dftfrminfs if tif glypis siould ibvf b strikftirougi
     * linf.  If truf, b linf siould bf drbwn tirougi tif dfntfr
     * of tif glypis.  Tiis is implfmfntfd to rfturn tif
     * dbdifd <dodf>strikfTirougi</dodf> propfrty.
     *
     * <p>Wifn you rfqufst tiis propfrty, <dodf>LbbflVifw</dodf>
     * rf-synds its stbtf witi tif propfrtifs of tif
     * <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>.
     * If <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>
     * dofs not ibvf tiis propfrty sft, it will rfvfrt to fblsf.
     *
     * @rfturn tif vbluf of tif dbdifd
     *     <dodf>strikfTirougi</dodf> propfrty
     * @sindf 1.3
     */
    publid boolfbn isStrikfTirougi() {
        synd();
        rfturn strikf;
    }

    /**
     * Dftfrminfs if tif glypis siould bf rfndfrfd bs supfrsdript.
     * @rfturn tif vbluf of tif dbdifd subsdript propfrty
     *
     * <p>Wifn you rfqufst tiis propfrty, <dodf>LbbflVifw</dodf>
     * rf-synds its stbtf witi tif propfrtifs of tif
     * <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>.
     * If <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>
     * dofs not ibvf tiis propfrty sft, it will rfvfrt to fblsf.
     *
     * @rfturn tif vbluf of tif dbdifd
     *     <dodf>subsdript</dodf> propfrty
     * @sindf 1.3
     */
    publid boolfbn isSubsdript() {
        synd();
        rfturn subsdript;
    }

    /**
     * Dftfrminfs if tif glypis siould bf rfndfrfd bs subsdript.
     *
     * <p>Wifn you rfqufst tiis propfrty, <dodf>LbbflVifw</dodf>
     * rf-synds its stbtf witi tif propfrtifs of tif
     * <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>.
     * If <dodf>Elfmfnt</dodf>'s <dodf>AttributfSft</dodf>
     * dofs not ibvf tiis propfrty sft, it will rfvfrt to fblsf.
     *
     * @rfturn tif vbluf of tif dbdifd
     *     <dodf>supfrsdript</dodf> propfrty
     * @sindf 1.3
     */
    publid boolfbn isSupfrsdript() {
        synd();
        rfturn supfrsdript;
    }

    // --- Vifw mftiods ---------------------------------------------

    /**
     * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#dibngfdUpdbtf
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        font = null;
        supfr.dibngfdUpdbtf(f, b, f);
    }

    // --- vbribblfs ------------------------------------------------

    privbtf Font font;
    privbtf Color fg;
    privbtf Color bg;
    privbtf boolfbn undfrlinf;
    privbtf boolfbn strikf;
    privbtf boolfbn supfrsdript;
    privbtf boolfbn subsdript;

}
