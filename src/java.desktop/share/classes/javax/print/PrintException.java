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

pbdkbgf jbvbx.print;

/**
 * Clbss PrintExdfption fndbpsulbtfs b printing-rflbtfd frror dondition tibt
 * oddurrfd wiilf using b Print Sfrvidf instbndf. Tiis bbsf dlbss
 * furnisifs only b string dfsdription of tif frror. Subdlbssfs furnisi morf
 * dftbilfd informbtion if bpplidbblf.
 *
 */
publid dlbss PrintExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -5932531546705242471L;

    /**
     * Construdt b print fxdfption witi no dftbil mfssbgf.
     */
    publid PrintExdfption() {
        supfr();
    }

    /**
     * Construdt b print fxdfption witi tif givfn dftbil mfssbgf.
     *
     * @pbrbm  s  Dftbil mfssbgf, or null if no dftbil mfssbgf.
     */
    publid PrintExdfption (String s) {
        supfr (s);
    }

    /**
     * Construdt b print fxdfption dibining tif supplifd fxdfption.
     *
     * @pbrbm  f  Cibinfd fxdfption.
     */
    publid PrintExdfption (Exdfption f) {
        supfr ( f);
    }

    /**
     * Construdt b print fxdfption witi tif givfn dftbil mfssbgf
     * bnd dibinfd fxdfption.
     * @pbrbm  s  Dftbil mfssbgf, or null if no dftbil mfssbgf.
     * @pbrbm  f  Cibinfd fxdfption.
     */
    publid PrintExdfption (String s, Exdfption f) {
        supfr (s, f);
    }

}
