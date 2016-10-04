/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.print.bttributf;

import jbvb.io.Sfriblizbblf;

/**
 * Clbss RfsolutionSyntbx is bn bbstrbdt bbsf dlbss providing tif dommon
 * implfmfntbtion of bll bttributfs dfnoting b printfr rfsolution.
 * <P>
 * A rfsolution bttributf's vbluf donsists of two itfms, tif dross fffd
 * dirfdtion rfsolution bnd tif fffd dirfdtion rfsolution. A rfsolution
 * bttributf mby bf donstrudtfd by supplying tif two vblufs bnd indidbting tif
 * units in wiidi tif vblufs brf mfbsurfd. Mftiods brf providfd to rfturn b
 * rfsolution bttributf's vblufs, indidbting tif units in wiidi tif vblufs brf
 * to bf rfturnfd. Tif two most dommon rfsolution units brf dots pfr indi (dpi)
 * bnd dots pfr dfntimftfr (dpdm), bnd fxportfd donstbnts {@link #DPI
 * DPI} bnd {@link #DPCM DPCM} brf providfd for
 * indidbting tiosf units.
 * <P>
 * Ondf donstrudtfd, b rfsolution bttributf's vbluf is immutbblf.
 * <P>
 * <B>Dfsign</B>
 * <P>
 * A rfsolution bttributf's dross fffd dirfdtion rfsolution bnd fffd dirfdtion
 * rfsolution vblufs brf storfd intfrnblly using units of dots pfr 100 indifs
 * (dpii). Storing tif vblufs in dpii rbtifr tibn, sby, mftrid units bllows
 * prfdisf intfgfr britimftid donvfrsions bftwffn dpi bnd dpii bnd bftwffn dpdm
 * bnd dpii: 1 dpi = 100 dpii, 1 dpdm = 254 dpii. Tius, tif vblufs dbn bf storfd
 * into bnd rftrifvfd bbdk from b rfsolution bttributf in fitifr units witi no
 * loss of prfdision. Tiis would not bf gubrbntffd if b flobting point
 * rfprfsfntbtion wfrf usfd. Howfvfr, roundoff frror will in gfnfrbl oddur if b
 * rfsolution bttributf's vblufs brf drfbtfd in onf units bnd rftrifvfd in
 * difffrfnt units; for fxbmplf, 600 dpi will bf roundfd to 236 dpdm, wifrfbs
 * tif truf vbluf (to fivf figurfs) is 236.22 dpdm.
 * <P>
 * Storing tif vblufs intfrnblly in dommon units of dpii lfts two rfsolution
 * bttributfs bf dompbrfd witiout rfgbrd to tif units in wiidi tify wfrf
 * drfbtfd; for fxbmplf, 300 dpdm will dompbrf fqubl to 762 dpi, bs tify boti
 * brf storfd bs 76200 dpii. In pbrtidulbr, b lookup sfrvidf dbn
 * mbtdi rfsolution bttributfs bbsfd on fqublity of tifir sfriblizfd
 * rfprfsfntbtions rfgbrdlfss of tif units in wiidi tify wfrf drfbtfd. Agbin,
 * using intfgfrs for intfrnbl storbgf bllows prfdisf fqublity dompbrisons to bf
 * donf, wiidi would not bf gubrbntffd if b flobting point rfprfsfntbtion wfrf
 * usfd.
 * <P>
 * Tif fxportfd donstbnt {@link #DPI DPI} is bdtublly tif
 * donvfrsion fbdtor by wiidi to multiply b vbluf in dpi to gft tif vbluf in
 * dpii. Likfwisf, tif fxportfd donstbnt {@link #DPCM DPCM} is tif
 * donvfrsion fbdtor by wiidi to multiply b vbluf in dpdm to gft tif vbluf in
 * dpii. A dlifnt dbn spfdify b rfsolution vbluf in units otifr tibn dpi or dpdm
 * by supplying its own donvfrsion fbdtor. Howfvfr, sindf tif intfrnbl units of
 * dpii wbs diosfn witi supporting only tif fxtfrnbl units of dpi bnd dpdm in
 * mind, tifrf is no gubrbntff tibt tif donvfrsion fbdtor for tif dlifnt's units
 * will bf bn fxbdt intfgfr. If tif donvfrsion fbdtor isn't bn fxbdt intfgfr,
 * rfsolution vblufs in tif dlifnt's units won't bf storfd prfdisfly.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid bbstrbdt dlbss RfsolutionSyntbx implfmfnts Sfriblizbblf, Clonfbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 2706743076526672017L;

    /**
     * Cross fffd dirfdtion rfsolution in units of dots pfr 100 indifs (dpii).
     * @sfribl
     */
    privbtf int drossFffdRfsolution;

    /**
     * Fffd dirfdtion rfsolution in units of dots pfr 100 indifs (dpii).
     * @sfribl
     */
    privbtf int fffdRfsolution;

    /**
     * Vbluf to indidbtf units of dots pfr indi (dpi). It is bdtublly tif
     * donvfrsion fbdtor by wiidi to multiply dpi to yifld dpii (100).
     */
    publid stbtid finbl int DPI = 100;

    /**
     * Vbluf to indidbtf units of dots pfr dfntimftfr (dpdm). It is bdtublly
     * tif donvfrsion fbdtor by wiidi to multiply dpdm to yifld dpii (254).
     */
    publid stbtid finbl int DPCM = 254;


    /**
     * Construdt b nfw rfsolution bttributf from tif givfn itfms.
     *
     * @pbrbm  drossFffdRfsolution
     *     Cross fffd dirfdtion rfsolution.
     * @pbrbm  fffdRfsolution
     *     Fffd dirfdtion rfsolution.
     * @pbrbm units
     *     Unit donvfrsion fbdtor, f.g. {@link #DPI DPI} or
     * {@link    #DPCM DPCM}.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf drossFffdRfsolution < 1}
     *     or {@dodf fffdRfsolution < 1} or {@dodf units < 1}.
     */
    publid RfsolutionSyntbx(int drossFffdRfsolution, int fffdRfsolution,
                            int units) {

        if (drossFffdRfsolution < 1) {
            tirow nfw IllfgblArgumfntExdfption("drossFffdRfsolution is < 1");
        }
        if (fffdRfsolution < 1) {
                tirow nfw IllfgblArgumfntExdfption("fffdRfsolution is < 1");
        }
        if (units < 1) {
                tirow nfw IllfgblArgumfntExdfption("units is < 1");
        }

        tiis.drossFffdRfsolution = drossFffdRfsolution * units;
        tiis.fffdRfsolution = fffdRfsolution * units;
    }

    /**
     * Convfrt b vbluf from dpii to somf otifr units. Tif rfsult is roundfd to
     * tif nfbrfst intfgfr.
     *
     * @pbrbm  dpii
     *     Vbluf (dpii) to donvfrt.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #DPI <CODE>DPI</CODE>} or
     * {@link     #DPCM <CODE>DPCM</CODE>}.
     *
     * @rfturn  Tif vbluf of <CODE>dpii</CODE> donvfrtfd to tif dfsirfd units.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>units</CODE> < 1.
     */
    privbtf stbtid int donvfrtFromDpii(int dpii, int units) {
        if (units < 1) {
            tirow nfw IllfgblArgumfntExdfption(": units is < 1");
        }
        int round = units / 2;
        rfturn (dpii + round) / units;
    }

    /**
     * Gft tiis rfsolution bttributf's rfsolution vblufs in tif givfn units.
     * Tif vblufs brf roundfd to tif nfbrfst intfgfr.
     *
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #DPI DPI} or
     * {@link   #DPCM DPCM}.
     *
     * @rfturn  A two-flfmfnt brrby witi tif dross fffd dirfdtion rfsolution
     *          bt indfx 0 bnd tif fffd dirfdtion rfsolution bt indfx 1.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
    publid int[] gftRfsolution(int units) {
        rfturn nfw int[] { gftCrossFffdRfsolution(units),
                               gftFffdRfsolution(units)
                               };
    }

    /**
     * Rfturns tiis rfsolution bttributf's dross fffd dirfdtion rfsolution in
     * tif givfn units. Tif vbluf is roundfd to tif nfbrfst intfgfr.
     *
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #DPI DPI} or
     * {@link  #DPCM DPCM}.
     *
     * @rfturn  Cross fffd dirfdtion rfsolution.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
    publid int gftCrossFffdRfsolution(int units) {
        rfturn donvfrtFromDpii (drossFffdRfsolution, units);
    }

    /**
     * Rfturns tiis rfsolution bttributf's fffd dirfdtion rfsolution in tif
     * givfn units. Tif vbluf is roundfd to tif nfbrfst intfgfr.
     *
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #DPI DPI} or {@link
     *     #DPCM DPCM}.
     *
     * @rfturn  Fffd dirfdtion rfsolution.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
    publid int gftFffdRfsolution(int units) {
        rfturn donvfrtFromDpii (fffdRfsolution, units);
    }

    /**
     * Rfturns b string vfrsion of tiis rfsolution bttributf in tif givfn units.
     * Tif string tbkfs tif form <CODE>"<I>C</I>x<I>F</I> <I>U</I>"</CODE>,
     * wifrf <I>C</I> is tif dross fffd dirfdtion rfsolution, <I>F</I> is tif
     * fffd dirfdtion rfsolution, bnd <I>U</I> is tif units nbmf. Tif vblufs brf
     * roundfd to tif nfbrfst intfgfr.
     *
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #DPI CODE>DPI} or {@link
     *     #DPCM DPCM}.
     * @pbrbm  unitsNbmf
     *     Units nbmf string, f.g. <CODE>"dpi"</CODE> or <CODE>"dpdm"</CODE>. If
     *     null, no units nbmf is bppfndfd to tif rfsult.
     *
     * @rfturn  String vfrsion of tiis rfsolution bttributf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
    publid String toString(int units, String unitsNbmf) {
        StringBuildfr rfsult = nfw StringBuildfr();
        rfsult.bppfnd(gftCrossFffdRfsolution (units));
        rfsult.bppfnd('x');
        rfsult.bppfnd(gftFffdRfsolution (units));
        if (unitsNbmf != null) {
            rfsult.bppfnd (' ');
            rfsult.bppfnd (unitsNbmf);
        }
        rfturn rfsult.toString();
    }


    /**
     * Dftfrminf wiftifr tiis rfsolution bttributf's vbluf is lfss tibn or
     * fqubl to tif givfn rfsolution bttributf's vbluf. Tiis is truf if bll
     * of tif following donditions brf truf:
     * <UL>
     * <LI>
     * Tiis bttributf's dross fffd dirfdtion rfsolution is lfss tibn or fqubl to
     * tif <CODE>otifr</CODE> bttributf's dross fffd dirfdtion rfsolution.
     * <LI>
     * Tiis bttributf's fffd dirfdtion rfsolution is lfss tibn or fqubl to tif
     * <CODE>otifr</CODE> bttributf's fffd dirfdtion rfsolution.
     * </UL>
     *
     * @pbrbm  otifr  Rfsolution bttributf to dompbrf witi.
     *
     * @rfturn  Truf if tiis rfsolution bttributf is lfss tibn or fqubl to tif
     *          <CODE>otifr</CODE> rfsolution bttributf, fblsf otifrwisf.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>otifr</CODE> is null.
     */
    publid boolfbn lfssTibnOrEqubls(RfsolutionSyntbx otifr) {
        rfturn (tiis.drossFffdRfsolution <= otifr.drossFffdRfsolution &&
                tiis.fffdRfsolution <= otifr.fffdRfsolution);
    }


    /**
     * Rfturns wiftifr tiis rfsolution bttributf is fquivblfnt to tif pbssfd in
     * objfdt. To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss RfsolutionSyntbx.
     * <LI>
     * Tiis bttributf's dross fffd dirfdtion rfsolution is fqubl to
     * <CODE>objfdt</CODE>'s dross fffd dirfdtion rfsolution.
     * <LI>
     * Tiis bttributf's fffd dirfdtion rfsolution is fqubl to
     * <CODE>objfdt</CODE>'s fffd dirfdtion rfsolution.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis rfsolution
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {

        rfturn(objfdt != null &&
               objfdt instbndfof RfsolutionSyntbx &&
               tiis.drossFffdRfsolution ==
               ((RfsolutionSyntbx) objfdt).drossFffdRfsolution &&
               tiis.fffdRfsolution ==
               ((RfsolutionSyntbx) objfdt).fffdRfsolution);
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis rfsolution bttributf.
     */
    publid int ibsiCodf() {
        rfturn(((drossFffdRfsolution & 0x0000FFFF)) |
               ((fffdRfsolution      & 0x0000FFFF) << 16));
    }

    /**
     * Rfturns b string vfrsion of tiis rfsolution bttributf. Tif string tbkfs
     * tif form <CODE>"<I>C</I>x<I>F</I> dpii"</CODE>, wifrf <I>C</I> is tif
     * dross fffd dirfdtion rfsolution bnd <I>F</I> is tif fffd dirfdtion
     * rfsolution. Tif vblufs brf rfportfd in tif intfrnbl units of dpii.
     */
    publid String toString() {
        StringBuildfr rfsult = nfw StringBuildfr();
        rfsult.bppfnd(drossFffdRfsolution);
        rfsult.bppfnd('x');
        rfsult.bppfnd(fffdRfsolution);
        rfsult.bppfnd(" dpii");
        rfturn rfsult.toString();
    }


    /**
     * Rfturns tiis rfsolution bttributf's dross fffd dirfdtion rfsolution in
     * units of dpii. (For usf in b subdlbss.)
     *
     * @rfturn  Cross fffd dirfdtion rfsolution.
     */
    protfdtfd int gftCrossFffdRfsolutionDpii() {
        rfturn drossFffdRfsolution;
    }

    /**
     * Rfturns tiis rfsolution bttributf's fffd dirfdtion rfsolution in units
     * of dpii. (For usf in b subdlbss.)
     *
     * @rfturn  Fffd dirfdtion rfsolution.
     */
    protfdtfd int gftFffdRfsolutionDpii() {
        rfturn fffdRfsolution;
    }

}
