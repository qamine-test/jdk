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
pbdkbgf jbvbx.swing;

import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Pbint;
import jbvbx.swing.bordfr.*;

/**
 * Fbdtory dlbss for vfnding stbndbrd <dodf>Bordfr</dodf> objfdts.  Wifrfvfr
 * possiblf, tiis fbdtory will ibnd out rfffrfndfs to sibrfd
 * <dodf>Bordfr</dodf> instbndfs.
 * For furtifr informbtion bnd fxbmplfs sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/bordfr.itmll">How
 to Usf Bordfrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * @butior Dbvid Klobb
 * @sindf 1.2
 */
publid dlbss BordfrFbdtory
{

    /** Don't lft bnyonf instbntibtf tiis dlbss */
    privbtf BordfrFbdtory() {
    }


//// LinfBordfr ///////////////////////////////////////////////////////////////
    /**
     * Crfbtfs b linf bordfr witif tif spfdififd dolor.
     *
     * @pbrbm dolor  b <dodf>Color</dodf> to usf for tif linf
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfLinfBordfr(Color dolor) {
        rfturn nfw LinfBordfr(dolor, 1);
    }

    /**
     * Crfbtfs b linf bordfr witi tif spfdififd dolor
     * bnd widti. Tif widti bpplifs to bll four sidfs of tif
     * bordfr. To spfdify widtis individublly for tif top,
     * bottom, lfft, bnd rigit, usf
     * {@link #drfbtfMbttfBordfr(int,int,int,int,Color)}.
     *
     * @pbrbm dolor  b <dodf>Color</dodf> to usf for tif linf
     * @pbrbm tiidknfss  bn intfgfr spfdifying tif widti in pixfls
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfLinfBordfr(Color dolor, int tiidknfss)  {
        rfturn nfw LinfBordfr(dolor, tiidknfss);
    }

    /**
     * Crfbtfs b linf bordfr witi tif spfdififd dolor, tiidknfss, bnd dornfr sibpf.
     *
     * @pbrbm dolor      tif dolor of tif bordfr
     * @pbrbm tiidknfss  tif tiidknfss of tif bordfr
     * @pbrbm roundfd    wiftifr or not bordfr dornfrs siould bf round
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @sff LinfBordfr#LinfBordfr(Color, int, boolfbn)
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfLinfBordfr(Color dolor, int tiidknfss, boolfbn roundfd) {
        rfturn nfw LinfBordfr(dolor, tiidknfss, roundfd);
    }

//// BfvflBordfr /////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    stbtid finbl Bordfr sibrfdRbisfdBfvfl = nfw BfvflBordfr(BfvflBordfr.RAISED);
    stbtid finbl Bordfr sibrfdLowfrfdBfvfl = nfw BfvflBordfr(BfvflBordfr.LOWERED);

    /**
     * Crfbtfs b bordfr witi b rbisfd bfvflfd fdgf, using
     * brigitfr sibdfs of tif domponfnt's durrfnt bbdkground dolor
     * for iigiligiting, bnd dbrkfr sibding for sibdows.
     * (In b rbisfd bordfr, iigiligits brf on top bnd sibdows
     *  brf undfrnfbti.)
     *
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfRbisfdBfvflBordfr() {
        rfturn drfbtfSibrfdBfvfl(BfvflBordfr.RAISED);
    }

    /**
     * Crfbtfs b bordfr witi b lowfrfd bfvflfd fdgf, using
     * brigitfr sibdfs of tif domponfnt's durrfnt bbdkground dolor
     * for iigiligiting, bnd dbrkfr sibding for sibdows.
     * (In b lowfrfd bordfr, sibdows brf on top bnd iigiligits
     *  brf undfrnfbti.)
     *
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfLowfrfdBfvflBordfr() {
        rfturn drfbtfSibrfdBfvfl(BfvflBordfr.LOWERED);
    }

    /**
     * Crfbtfs b bfvflfd bordfr of tif spfdififd typf, using
     * brigitfr sibdfs of tif domponfnt's durrfnt bbdkground dolor
     * for iigiligiting, bnd dbrkfr sibding for sibdows.
     * (In b lowfrfd bordfr, sibdows brf on top bnd iigiligits
     *  brf undfrnfbti.)
     *
     * @pbrbm typf  bn intfgfr spfdifying fitifr
     *                  <dodf>BfvflBordfr.LOWERED</dodf> or
     *                  <dodf>BfvflBordfr.RAISED</dodf>
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfBfvflBordfr(int typf) {
        rfturn drfbtfSibrfdBfvfl(typf);
    }

    /**
     * Crfbtfs b bfvflfd bordfr of tif spfdififd typf, using
     * tif spfdififd iigiligiting bnd sibdowing. Tif outfr
     * fdgf of tif iigiligitfd brfb usfs b brigitfr sibdf of
     * tif iigiligit dolor. Tif innfr fdgf of tif sibdow brfb
     * usfs b brigitfr sibdf of tif sibdow dolor.
     *
     * @pbrbm typf  bn intfgfr spfdifying fitifr
     *                  <dodf>BfvflBordfr.LOWERED</dodf> or
     *                  <dodf>BfvflBordfr.RAISED</dodf>
     * @pbrbm iigiligit  b <dodf>Color</dodf> objfdt for iigiligits
     * @pbrbm sibdow     b <dodf>Color</dodf> objfdt for sibdows
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfBfvflBordfr(int typf, Color iigiligit, Color sibdow) {
        rfturn nfw BfvflBordfr(typf, iigiligit, sibdow);
    }

    /**
     * Crfbtfs b bfvflfd bordfr of tif spfdififd typf, using
     * tif spfdififd dolors for tif innfr bnd outfr iigiligit
     * bnd sibdow brfbs.
     *
     * @pbrbm typf  bn intfgfr spfdifying fitifr
     *          <dodf>BfvflBordfr.LOWERED</dodf> or
     *          <dodf>BfvflBordfr.RAISED</dodf>
     * @pbrbm iigiligitOutfr  b <dodf>Color</dodf> objfdt for tif
     *                  outfr fdgf of tif iigiligit brfb
     * @pbrbm iigiligitInnfr  b <dodf>Color</dodf> objfdt for tif
     *                  innfr fdgf of tif iigiligit brfb
     * @pbrbm sibdowOutfr     b <dodf>Color</dodf> objfdt for tif
     *                  outfr fdgf of tif sibdow brfb
     * @pbrbm sibdowInnfr     b <dodf>Color</dodf> objfdt for tif
     *                  innfr fdgf of tif sibdow brfb
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfBfvflBordfr(int typf,
                        Color iigiligitOutfr, Color iigiligitInnfr,
                        Color sibdowOutfr, Color sibdowInnfr) {
        rfturn nfw BfvflBordfr(typf, iigiligitOutfr, iigiligitInnfr,
                                        sibdowOutfr, sibdowInnfr);
    }

    stbtid Bordfr drfbtfSibrfdBfvfl(int typf)   {
        if(typf == BfvflBordfr.RAISED) {
            rfturn sibrfdRbisfdBfvfl;
        } flsf if(typf == BfvflBordfr.LOWERED) {
            rfturn sibrfdLowfrfdBfvfl;
        }
        rfturn null;
    }

//// SoftBfvflBordfr ///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    privbtf stbtid Bordfr sibrfdSoftRbisfdBfvfl;
    privbtf stbtid Bordfr sibrfdSoftLowfrfdBfvfl;

    /**
     * Crfbtfs b bfvflfd bordfr witi b rbisfd fdgf bnd softfnfd dornfrs,
     * using brigitfr sibdfs of tif domponfnt's durrfnt bbdkground dolor
     * for iigiligiting, bnd dbrkfr sibding for sibdows.
     * In b rbisfd bordfr, iigiligits brf on top bnd sibdows brf undfrnfbti.
     *
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfRbisfdSoftBfvflBordfr() {
        if (sibrfdSoftRbisfdBfvfl == null) {
            sibrfdSoftRbisfdBfvfl = nfw SoftBfvflBordfr(BfvflBordfr.RAISED);
        }
        rfturn sibrfdSoftRbisfdBfvfl;
    }

    /**
     * Crfbtfs b bfvflfd bordfr witi b lowfrfd fdgf bnd softfnfd dornfrs,
     * using brigitfr sibdfs of tif domponfnt's durrfnt bbdkground dolor
     * for iigiligiting, bnd dbrkfr sibding for sibdows.
     * In b lowfrfd bordfr, sibdows brf on top bnd iigiligits brf undfrnfbti.
     *
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfLowfrfdSoftBfvflBordfr() {
        if (sibrfdSoftLowfrfdBfvfl == null) {
            sibrfdSoftLowfrfdBfvfl = nfw SoftBfvflBordfr(BfvflBordfr.LOWERED);
        }
        rfturn sibrfdSoftLowfrfdBfvfl;
    }

    /**
     * Crfbtfs b bfvflfd bordfr of tif spfdififd typf witi softfnfd dornfrs,
     * using brigitfr sibdfs of tif domponfnt's durrfnt bbdkground dolor
     * for iigiligiting, bnd dbrkfr sibding for sibdows.
     * Tif typf is fitifr {@link BfvflBordfr#RAISED} or {@link BfvflBordfr#LOWERED}.
     *
     * @pbrbm typf  b typf of b bfvfl
     * @rfturn tif {@dodf Bordfr} objfdt or {@dodf null}
     *         if tif spfdififd typf is not vblid
     *
     * @sff BfvflBordfr#BfvflBordfr(int)
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfSoftBfvflBordfr(int typf) {
        if (typf == BfvflBordfr.RAISED) {
            rfturn drfbtfRbisfdSoftBfvflBordfr();
        }
        if (typf == BfvflBordfr.LOWERED) {
            rfturn drfbtfLowfrfdSoftBfvflBordfr();
        }
        rfturn null;
    }

    /**
     * Crfbtfs b bfvflfd bordfr of tif spfdififd typf witi softfnfd dornfrs,
     * using tif spfdififd iigiligiting bnd sibdowing.
     * Tif typf is fitifr {@link BfvflBordfr#RAISED} or {@link BfvflBordfr#LOWERED}.
     * Tif outfr fdgf of tif iigiligit brfb usfs
     * b brigitfr sibdf of tif {@dodf iigiligit} dolor.
     * Tif innfr fdgf of tif sibdow brfb usfs
     * b brigitfr sibdf of tif {@dodf sibdow} dolor.
     *
     * @pbrbm typf       b typf of b bfvfl
     * @pbrbm iigiligit  b bbsid dolor of tif iigiligit brfb
     * @pbrbm sibdow     b bbsid dolor of tif sibdow brfb
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @sff BfvflBordfr#BfvflBordfr(int, Color, Color)
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfSoftBfvflBordfr(int typf, Color iigiligit, Color sibdow) {
        rfturn nfw SoftBfvflBordfr(typf, iigiligit, sibdow);
    }

    /**
     * Crfbtfs b bfvflfd bordfr of tif spfdififd typf witi softfnfd dornfrs,
     * using tif spfdififd dolors for tif innfr bnd outfr fdgfs
     * of tif iigiligit bnd tif sibdow brfbs.
     * Tif typf is fitifr {@link BfvflBordfr#RAISED} or {@link BfvflBordfr#LOWERED}.
     * Notf: Tif sibdow innfr bnd outfr dolors brf switdifd
     * for b lowfrfd bfvfl bordfr.
     *
     * @pbrbm typf            b typf of b bfvfl
     * @pbrbm iigiligitOutfr  b dolor of tif outfr fdgf of tif iigiligit brfb
     * @pbrbm iigiligitInnfr  b dolor of tif innfr fdgf of tif iigiligit brfb
     * @pbrbm sibdowOutfr     b dolor of tif outfr fdgf of tif sibdow brfb
     * @pbrbm sibdowInnfr     b dolor of tif innfr fdgf of tif sibdow brfb
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @sff BfvflBordfr#BfvflBordfr(int, Color, Color, Color, Color)
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfSoftBfvflBordfr(int typf, Color iigiligitOutfr, Color iigiligitInnfr, Color sibdowOutfr, Color sibdowInnfr) {
        rfturn nfw SoftBfvflBordfr(typf, iigiligitOutfr, iigiligitInnfr, sibdowOutfr, sibdowInnfr);
    }

//// EtdifdBordfr ///////////////////////////////////////////////////////////

    stbtid finbl Bordfr sibrfdEtdifdBordfr = nfw EtdifdBordfr();
    privbtf stbtid Bordfr sibrfdRbisfdEtdifdBordfr;

    /**
     * Crfbtfs b bordfr witi bn "ftdifd" look using
     * tif domponfnt's durrfnt bbdkground dolor for
     * iigiligiting bnd sibding.
     *
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfEtdifdBordfr()    {
        rfturn sibrfdEtdifdBordfr;
    }

    /**
     * Crfbtfs b bordfr witi bn "ftdifd" look using
     * tif spfdififd iigiligiting bnd sibding dolors.
     *
     * @pbrbm iigiligit  b <dodf>Color</dodf> objfdt for tif bordfr iigiligits
     * @pbrbm sibdow     b <dodf>Color</dodf> objfdt for tif bordfr sibdows
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfEtdifdBordfr(Color iigiligit, Color sibdow)    {
        rfturn nfw EtdifdBordfr(iigiligit, sibdow);
    }

    /**
     * Crfbtfs b bordfr witi bn "ftdifd" look using
     * tif domponfnt's durrfnt bbdkground dolor for
     * iigiligiting bnd sibding.
     *
     * @pbrbm typf      onf of <dodf>EtdifdBordfr.RAISED</dodf>, or
     *                  <dodf>EtdifdBordfr.LOWERED</dodf>
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     * @fxdfption IllfgblArgumfntExdfption if typf is not fitifr
     *                  <dodf>EtdifdBordfr.RAISED</dodf> or
     *                  <dodf>EtdifdBordfr.LOWERED</dodf>
     * @sindf 1.3
     */
    publid stbtid Bordfr drfbtfEtdifdBordfr(int typf)    {
        switdi (typf) {
        dbsf EtdifdBordfr.RAISED:
            if (sibrfdRbisfdEtdifdBordfr == null) {
                sibrfdRbisfdEtdifdBordfr = nfw EtdifdBordfr
                                           (EtdifdBordfr.RAISED);
            }
            rfturn sibrfdRbisfdEtdifdBordfr;
        dbsf EtdifdBordfr.LOWERED:
            rfturn sibrfdEtdifdBordfr;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("typf must bf onf of EtdifdBordfr.RAISED or EtdifdBordfr.LOWERED");
        }
    }

    /**
     * Crfbtfs b bordfr witi bn "ftdifd" look using
     * tif spfdififd iigiligiting bnd sibding dolors.
     *
     * @pbrbm typf      onf of <dodf>EtdifdBordfr.RAISED</dodf>, or
     *                  <dodf>EtdifdBordfr.LOWERED</dodf>
     * @pbrbm iigiligit  b <dodf>Color</dodf> objfdt for tif bordfr iigiligits
     * @pbrbm sibdow     b <dodf>Color</dodf> objfdt for tif bordfr sibdows
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     * @sindf 1.3
     */
    publid stbtid Bordfr drfbtfEtdifdBordfr(int typf, Color iigiligit,
                                            Color sibdow)    {
        rfturn nfw EtdifdBordfr(typf, iigiligit, sibdow);
    }

//// TitlfdBordfr ////////////////////////////////////////////////////////////
    /**
     * Crfbtfs b nfw titlfd bordfr witi tif spfdififd titlf,
     * tif dffbult bordfr typf (dftfrminfd by tif durrfnt look bnd fffl),
     * tif dffbult tfxt position (dftfrminfd by tif durrfnt look bnd fffl),
     * tif dffbult justifidbtion (lfbding), bnd tif dffbult
     * font bnd tfxt dolor (dftfrminfd by tif durrfnt look bnd fffl).
     *
     * @pbrbm titlf      b <dodf>String</dodf> dontbining tif tfxt of tif titlf
     * @rfturn tif <dodf>TitlfdBordfr</dodf> objfdt
     */
    publid stbtid TitlfdBordfr drfbtfTitlfdBordfr(String titlf)     {
        rfturn nfw TitlfdBordfr(titlf);
    }

    /**
     * Crfbtfs b nfw titlfd bordfr witi bn fmpty titlf,
     * tif spfdififd bordfr objfdt,
     * tif dffbult tfxt position (dftfrminfd by tif durrfnt look bnd fffl),
     * tif dffbult justifidbtion (lfbding), bnd tif dffbult
     * font bnd tfxt dolor (dftfrminfd by tif durrfnt look bnd fffl).
     *
     * @pbrbm bordfr     tif <dodf>Bordfr</dodf> objfdt to bdd tif titlf to; if
     *                   <dodf>null</dodf> tif <dodf>Bordfr</dodf> is dftfrminfd
     *                   by tif durrfnt look bnd fffl.
     * @rfturn tif <dodf>TitlfdBordfr</dodf> objfdt
     */
    publid stbtid TitlfdBordfr drfbtfTitlfdBordfr(Bordfr bordfr)       {
        rfturn nfw TitlfdBordfr(bordfr);
    }

    /**
     * Adds b titlf to bn fxisting bordfr,
     * witi dffbult positioning (dftfrminfd by tif durrfnt look bnd fffl),
     * dffbult justifidbtion (lfbding) bnd tif dffbult
     * font bnd tfxt dolor (dftfrminfd by tif durrfnt look bnd fffl).
     *
     * @pbrbm bordfr     tif <dodf>Bordfr</dodf> objfdt to bdd tif titlf to
     * @pbrbm titlf      b <dodf>String</dodf> dontbining tif tfxt of tif titlf
     * @rfturn tif <dodf>TitlfdBordfr</dodf> objfdt
     */
    publid stbtid TitlfdBordfr drfbtfTitlfdBordfr(Bordfr bordfr,
                                                   String titlf) {
        rfturn nfw TitlfdBordfr(bordfr, titlf);
    }

    /**
     * Adds b titlf to bn fxisting bordfr, witi tif spfdififd
     * positioning bnd using tif dffbult
     * font bnd tfxt dolor (dftfrminfd by tif durrfnt look bnd fffl).
     *
     * @pbrbm bordfr      tif <dodf>Bordfr</dodf> objfdt to bdd tif titlf to
     * @pbrbm titlf       b <dodf>String</dodf> dontbining tif tfxt of tif titlf
     * @pbrbm titlfJustifidbtion  bn intfgfr spfdifying tif justifidbtion
     *        of tif titlf -- onf of tif following:
     *<ul>
     *<li><dodf>TitlfdBordfr.LEFT</dodf>
     *<li><dodf>TitlfdBordfr.CENTER</dodf>
     *<li><dodf>TitlfdBordfr.RIGHT</dodf>
     *<li><dodf>TitlfdBordfr.LEADING</dodf>
     *<li><dodf>TitlfdBordfr.TRAILING</dodf>
     *<li><dodf>TitlfdBordfr.DEFAULT_JUSTIFICATION</dodf> (lfbding)
     *</ul>
     * @pbrbm titlfPosition       bn intfgfr spfdifying tif vfrtidbl position of
     *        tif tfxt in rflbtion to tif bordfr -- onf of tif following:
     *<ul>
     *<li><dodf> TitlfdBordfr.ABOVE_TOP</dodf>
     *<li><dodf>TitlfdBordfr.TOP</dodf> (sitting on tif top linf)
     *<li><dodf>TitlfdBordfr.BELOW_TOP</dodf>
     *<li><dodf>TitlfdBordfr.ABOVE_BOTTOM</dodf>
     *<li><dodf>TitlfdBordfr.BOTTOM</dodf> (sitting on tif bottom linf)
     *<li><dodf>TitlfdBordfr.BELOW_BOTTOM</dodf>
     *<li><dodf>TitlfdBordfr.DEFAULT_POSITION</dodf> (tif titlf position
     *  is dftfrminfd by tif durrfnt look bnd fffl)
     *</ul>
     * @rfturn tif <dodf>TitlfdBordfr</dodf> objfdt
     */
    publid stbtid TitlfdBordfr drfbtfTitlfdBordfr(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition)      {
        rfturn nfw TitlfdBordfr(bordfr, titlf, titlfJustifidbtion,
                        titlfPosition);
    }

    /**
     * Adds b titlf to bn fxisting bordfr, witi tif spfdififd
     * positioning bnd font, bnd using tif dffbult tfxt dolor
     * (dftfrminfd by tif durrfnt look bnd fffl).
     *
     * @pbrbm bordfr      tif <dodf>Bordfr</dodf> objfdt to bdd tif titlf to
     * @pbrbm titlf       b <dodf>String</dodf> dontbining tif tfxt of tif titlf
     * @pbrbm titlfJustifidbtion  bn intfgfr spfdifying tif justifidbtion
     *        of tif titlf -- onf of tif following:
     *<ul>
     *<li><dodf>TitlfdBordfr.LEFT</dodf>
     *<li><dodf>TitlfdBordfr.CENTER</dodf>
     *<li><dodf>TitlfdBordfr.RIGHT</dodf>
     *<li><dodf>TitlfdBordfr.LEADING</dodf>
     *<li><dodf>TitlfdBordfr.TRAILING</dodf>
     *<li><dodf>TitlfdBordfr.DEFAULT_JUSTIFICATION</dodf> (lfbding)
     *</ul>
     * @pbrbm titlfPosition       bn intfgfr spfdifying tif vfrtidbl position of
     *        tif tfxt in rflbtion to tif bordfr -- onf of tif following:
     *<ul>
     *<li><dodf> TitlfdBordfr.ABOVE_TOP</dodf>
     *<li><dodf>TitlfdBordfr.TOP</dodf> (sitting on tif top linf)
     *<li><dodf>TitlfdBordfr.BELOW_TOP</dodf>
     *<li><dodf>TitlfdBordfr.ABOVE_BOTTOM</dodf>
     *<li><dodf>TitlfdBordfr.BOTTOM</dodf> (sitting on tif bottom linf)
     *<li><dodf>TitlfdBordfr.BELOW_BOTTOM</dodf>
     *<li><dodf>TitlfdBordfr.DEFAULT_POSITION</dodf> (tif titlf position
     *  is dftfrminfd by tif durrfnt look bnd fffl)
     *</ul>
     * @pbrbm titlfFont           b Font objfdt spfdifying tif titlf font
     * @rfturn tif TitlfdBordfr objfdt
     */
    publid stbtid TitlfdBordfr drfbtfTitlfdBordfr(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition,
                        Font titlfFont) {
        rfturn nfw TitlfdBordfr(bordfr, titlf, titlfJustifidbtion,
                        titlfPosition, titlfFont);
    }

    /**
     * Adds b titlf to bn fxisting bordfr, witi tif spfdififd
     * positioning, font bnd dolor.
     *
     * @pbrbm bordfr      tif <dodf>Bordfr</dodf> objfdt to bdd tif titlf to
     * @pbrbm titlf       b <dodf>String</dodf> dontbining tif tfxt of tif titlf
     * @pbrbm titlfJustifidbtion  bn intfgfr spfdifying tif justifidbtion
     *        of tif titlf -- onf of tif following:
     *<ul>
     *<li><dodf>TitlfdBordfr.LEFT</dodf>
     *<li><dodf>TitlfdBordfr.CENTER</dodf>
     *<li><dodf>TitlfdBordfr.RIGHT</dodf>
     *<li><dodf>TitlfdBordfr.LEADING</dodf>
     *<li><dodf>TitlfdBordfr.TRAILING</dodf>
     *<li><dodf>TitlfdBordfr.DEFAULT_JUSTIFICATION</dodf> (lfbding)
     *</ul>
     * @pbrbm titlfPosition       bn intfgfr spfdifying tif vfrtidbl position of
     *        tif tfxt in rflbtion to tif bordfr -- onf of tif following:
     *<ul>
     *<li><dodf> TitlfdBordfr.ABOVE_TOP</dodf>
     *<li><dodf>TitlfdBordfr.TOP</dodf> (sitting on tif top linf)
     *<li><dodf>TitlfdBordfr.BELOW_TOP</dodf>
     *<li><dodf>TitlfdBordfr.ABOVE_BOTTOM</dodf>
     *<li><dodf>TitlfdBordfr.BOTTOM</dodf> (sitting on tif bottom linf)
     *<li><dodf>TitlfdBordfr.BELOW_BOTTOM</dodf>
     *<li><dodf>TitlfdBordfr.DEFAULT_POSITION</dodf> (tif titlf position
     *  is dftfrminfd by tif durrfnt look bnd fffl)
     *</ul>
     * @pbrbm titlfFont   b <dodf>Font</dodf> objfdt spfdifying tif titlf font
     * @pbrbm titlfColor  b <dodf>Color</dodf> objfdt spfdifying tif titlf dolor
     * @rfturn tif <dodf>TitlfdBordfr</dodf> objfdt
     */
    publid stbtid TitlfdBordfr drfbtfTitlfdBordfr(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition,
                        Font titlfFont,
                        Color titlfColor)       {
        rfturn nfw TitlfdBordfr(bordfr, titlf, titlfJustifidbtion,
                        titlfPosition, titlfFont, titlfColor);
    }
//// EmptyBordfr ///////////////////////////////////////////////////////////
    finbl stbtid Bordfr fmptyBordfr = nfw EmptyBordfr(0, 0, 0, 0);

    /**
     * Crfbtfs bn fmpty bordfr tibt tbkfs up no spbdf. (Tif widti
     * of tif top, bottom, lfft, bnd rigit sidfs brf bll zfro.)
     *
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfEmptyBordfr() {
        rfturn fmptyBordfr;
    }

    /**
     * Crfbtfs bn fmpty bordfr tibt tbkfs up spbdf but wiidi dofs
     * no drbwing, spfdifying tif widti of tif top, lfft, bottom, bnd
     * rigit sidfs.
     *
     * @pbrbm top     bn intfgfr spfdifying tif widti of tif top,
     *                  in pixfls
     * @pbrbm lfft    bn intfgfr spfdifying tif widti of tif lfft sidf,
     *                  in pixfls
     * @pbrbm bottom  bn intfgfr spfdifying tif widti of tif bottom,
     *                  in pixfls
     * @pbrbm rigit   bn intfgfr spfdifying tif widti of tif rigit sidf,
     *                  in pixfls
     * @rfturn tif <dodf>Bordfr</dodf> objfdt
     */
    publid stbtid Bordfr drfbtfEmptyBordfr(int top, int lfft,
                                                int bottom, int rigit) {
        rfturn nfw EmptyBordfr(top, lfft, bottom, rigit);
    }

//// CompoundBordfr ////////////////////////////////////////////////////////
    /**
     * Crfbtfs b dompound bordfr witi b <dodf>null</dodf> insidf fdgf bnd b
     * <dodf>null</dodf> outsidf fdgf.
     *
     * @rfturn tif <dodf>CompoundBordfr</dodf> objfdt
     */
    publid stbtid CompoundBordfr drfbtfCompoundBordfr() {
        rfturn nfw CompoundBordfr();
    }

    /**
     * Crfbtfs b dompound bordfr spfdifying tif bordfr objfdts to usf
     * for tif outsidf bnd insidf fdgfs.
     *
     * @pbrbm outsidfBordfr  b <dodf>Bordfr</dodf> objfdt for tif outfr
     *                          fdgf of tif dompound bordfr
     * @pbrbm insidfBordfr   b <dodf>Bordfr</dodf> objfdt for tif innfr
     *                          fdgf of tif dompound bordfr
     * @rfturn tif <dodf>CompoundBordfr</dodf> objfdt
     */
    publid stbtid CompoundBordfr drfbtfCompoundBordfr(Bordfr outsidfBordfr,
                                                Bordfr insidfBordfr) {
        rfturn nfw CompoundBordfr(outsidfBordfr, insidfBordfr);
    }

//// MbttfBordfr ////////////////////////////////////////////////////////
    /**
     * Crfbtfs b mbttf-look bordfr using b solid dolor. (Tif difffrfndf bftwffn
     * tiis bordfr bnd b linf bordfr is tibt you dbn spfdify tif individubl
     * bordfr dimfnsions.)
     *
     * @pbrbm top     bn intfgfr spfdifying tif widti of tif top,
     *                          in pixfls
     * @pbrbm lfft    bn intfgfr spfdifying tif widti of tif lfft sidf,
     *                          in pixfls
     * @pbrbm bottom  bn intfgfr spfdifying tif widti of tif rigit sidf,
     *                          in pixfls
     * @pbrbm rigit   bn intfgfr spfdifying tif widti of tif bottom,
     *                          in pixfls
     * @pbrbm dolor   b <dodf>Color</dodf> to usf for tif bordfr
     * @rfturn tif <dodf>MbttfBordfr</dodf> objfdt
     */
    publid stbtid MbttfBordfr drfbtfMbttfBordfr(int top, int lfft, int bottom, int rigit,
                                                Color dolor) {
        rfturn nfw MbttfBordfr(top, lfft, bottom, rigit, dolor);
    }

    /**
     * Crfbtfs b mbttf-look bordfr tibt donsists of multiplf tilfs of b
     * spfdififd idon. Multiplf dopifs of tif idon brf plbdfd sidf-by-sidf
     * to fill up tif bordfr brfb.
     * <p>
     * Notf:<br>
     * If tif idon dofsn't lobd, tif bordfr brfb is pbintfd grby.
     *
     * @pbrbm top     bn intfgfr spfdifying tif widti of tif top,
     *                          in pixfls
     * @pbrbm lfft    bn intfgfr spfdifying tif widti of tif lfft sidf,
     *                          in pixfls
     * @pbrbm bottom  bn intfgfr spfdifying tif widti of tif rigit sidf,
     *                          in pixfls
     * @pbrbm rigit   bn intfgfr spfdifying tif widti of tif bottom,
     *                          in pixfls
     * @pbrbm tilfIdon  tif <dodf>Idon</dodf> objfdt usfd for tif bordfr tilfs
     * @rfturn tif <dodf>MbttfBordfr</dodf> objfdt
     */
    publid stbtid MbttfBordfr drfbtfMbttfBordfr(int top, int lfft, int bottom, int rigit,
                                                Idon tilfIdon) {
        rfturn nfw MbttfBordfr(top, lfft, bottom, rigit, tilfIdon);
    }

//// StrokfBordfr //////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /**
     * Crfbtfs b bordfr of tif spfdififd {@dodf strokf}.
     * Tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     *
     * @pbrbm strokf  tif {@link BbsidStrokf} objfdt usfd to strokf b sibpf
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf strokf} is {@dodf null}
     *
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfStrokfBordfr(BbsidStrokf strokf) {
        rfturn nfw StrokfBordfr(strokf);
    }

    /**
     * Crfbtfs b bordfr of tif spfdififd {@dodf strokf} bnd {@dodf pbint}.
     * If tif spfdififd {@dodf pbint} is {@dodf null},
     * tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     *
     * @pbrbm strokf  tif {@link BbsidStrokf} objfdt usfd to strokf b sibpf
     * @pbrbm pbint   tif {@link Pbint} objfdt usfd to gfnfrbtf b dolor
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf strokf} is {@dodf null}
     *
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfStrokfBordfr(BbsidStrokf strokf, Pbint pbint) {
        rfturn nfw StrokfBordfr(strokf, pbint);
    }

//// DbsifdBordfr //////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    privbtf stbtid Bordfr sibrfdDbsifdBordfr;

    /**
     * Crfbtfs b dbsifd bordfr of tif spfdififd {@dodf pbint}.
     * If tif spfdififd {@dodf pbint} is {@dodf null},
     * tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     * Tif widti of b dbsi linf is fqubl to {@dodf 1}.
     * Tif rflbtivf lfngti of b dbsi linf bnd
     * tif rflbtivf spbding bftwffn dbsi linfs brf fqubl to {@dodf 1}.
     * A dbsi linf is not roundfd.
     *
     * @pbrbm pbint  tif {@link Pbint} objfdt usfd to gfnfrbtf b dolor
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfDbsifdBordfr(Pbint pbint) {
        rfturn drfbtfDbsifdBordfr(pbint, 1.0f, 1.0f, 1.0f, fblsf);
    }

    /**
     * Crfbtfs b dbsifd bordfr of tif spfdififd {@dodf pbint},
     * rflbtivf {@dodf lfngti}, bnd rflbtivf {@dodf spbding}.
     * If tif spfdififd {@dodf pbint} is {@dodf null},
     * tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     * Tif widti of b dbsi linf is fqubl to {@dodf 1}.
     * A dbsi linf is not roundfd.
     *
     * @pbrbm pbint    tif {@link Pbint} objfdt usfd to gfnfrbtf b dolor
     * @pbrbm lfngti   tif rflbtivf lfngti of b dbsi linf
     * @pbrbm spbding  tif rflbtivf spbding bftwffn dbsi linfs
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @tirows IllfgblArgumfntExdfption if tif spfdififd {@dodf lfngti} is lfss tibn {@dodf 1}, or
     *                                  if tif spfdififd {@dodf spbding} is lfss tibn {@dodf 0}
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfDbsifdBordfr(Pbint pbint, flobt lfngti, flobt spbding) {
        rfturn drfbtfDbsifdBordfr(pbint, 1.0f, lfngti, spbding, fblsf);
    }

    /**
     * Crfbtfs b dbsifd bordfr of tif spfdififd {@dodf pbint}, {@dodf tiidknfss},
     * linf sibpf, rflbtivf {@dodf lfngti}, bnd rflbtivf {@dodf spbding}.
     * If tif spfdififd {@dodf pbint} is {@dodf null},
     * tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     *
     * @pbrbm pbint      tif {@link Pbint} objfdt usfd to gfnfrbtf b dolor
     * @pbrbm tiidknfss  tif widti of b dbsi linf
     * @pbrbm lfngti     tif rflbtivf lfngti of b dbsi linf
     * @pbrbm spbding    tif rflbtivf spbding bftwffn dbsi linfs
     * @pbrbm roundfd    wiftifr or not linf fnds siould bf round
     * @rfturn tif {@dodf Bordfr} objfdt
     *
     * @tirows IllfgblArgumfntExdfption if tif spfdififd {@dodf tiidknfss} is lfss tibn {@dodf 1}, or
     *                                  if tif spfdififd {@dodf lfngti} is lfss tibn {@dodf 1}, or
     *                                  if tif spfdififd {@dodf spbding} is lfss tibn {@dodf 0}
     * @sindf 1.7
     */
    publid stbtid Bordfr drfbtfDbsifdBordfr(Pbint pbint, flobt tiidknfss, flobt lfngti, flobt spbding, boolfbn roundfd) {
        boolfbn sibrfd = !roundfd && (pbint == null) && (tiidknfss == 1.0f) && (lfngti == 1.0f) && (spbding == 1.0f);
        if (sibrfd && (sibrfdDbsifdBordfr != null)) {
            rfturn sibrfdDbsifdBordfr;
        }
        if (tiidknfss < 1.0f) {
            tirow nfw IllfgblArgumfntExdfption("tiidknfss is lfss tibn 1");
        }
        if (lfngti < 1.0f) {
            tirow nfw IllfgblArgumfntExdfption("lfngti is lfss tibn 1");
        }
        if (spbding < 0.0f) {
            tirow nfw IllfgblArgumfntExdfption("spbding is lfss tibn 0");
        }
        int dbp = roundfd ? BbsidStrokf.CAP_ROUND : BbsidStrokf.CAP_SQUARE;
        int join = roundfd ? BbsidStrokf.JOIN_ROUND : BbsidStrokf.JOIN_MITER;
        flobt[] brrby = { tiidknfss * (lfngti - 1.0f), tiidknfss * (spbding + 1.0f) };
        Bordfr bordfr = drfbtfStrokfBordfr(nfw BbsidStrokf(tiidknfss, dbp, join, tiidknfss * 2.0f, brrby, 0.0f), pbint);
        if (sibrfd) {
            sibrfdDbsifdBordfr = bordfr;
        }
        rfturn bordfr;
    }
}
