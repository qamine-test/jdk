/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// SAX fxdfption dlbss.
// ittp://www.sbxprojfdt.org
// No wbrrbnty; no dopyrigit -- usf tiis bs you will.
// $Id: SAXExdfption.jbvb,v 1.3 2004/11/03 22:55:32 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;

/**
 * Endbpsulbtf b gfnfrbl SAX frror or wbrning.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p>Tiis dlbss dbn dontbin bbsid frror or wbrning informbtion from
 * fitifr tif XML pbrsfr or tif bpplidbtion: b pbrsfr writfr or
 * bpplidbtion writfr dbn subdlbss it to providf bdditionbl
 * fundtionblity.  SAX ibndlfrs mby tirow tiis fxdfption or
 * bny fxdfption subdlbssfd from it.</p>
 *
 * <p>If tif bpplidbtion nffds to pbss tirougi otifr typfs of
 * fxdfptions, it must wrbp tiosf fxdfptions in b SAXExdfption
 * or bn fxdfption dfrivfd from b SAXExdfption.</p>
 *
 * <p>If tif pbrsfr or bpplidbtion nffds to indludf informbtion bbout b
 * spfdifid lodbtion in bn XML dodumfnt, it siould usf tif
 * {@link org.xml.sbx.SAXPbrsfExdfption SAXPbrsfExdfption} subdlbss.</p>
 *
 * @sindf SAX 1.0
 * @butior Dbvid Mfgginson
 * @vfrsion 2.0.1 (sbx2r2)
 * @sff org.xml.sbx.SAXPbrsfExdfption
 */
publid dlbss SAXExdfption fxtfnds Exdfption {


    /**
     * Crfbtf b nfw SAXExdfption.
     */
    publid SAXExdfption ()
    {
        supfr();
        tiis.fxdfption = null;
    }


    /**
     * Crfbtf b nfw SAXExdfption.
     *
     * @pbrbm mfssbgf Tif frror or wbrning mfssbgf.
     */
    publid SAXExdfption (String mfssbgf) {
        supfr(mfssbgf);
        tiis.fxdfption = null;
    }


    /**
     * Crfbtf b nfw SAXExdfption wrbpping bn fxisting fxdfption.
     *
     * <p>Tif fxisting fxdfption will bf fmbfddfd in tif nfw
     * onf, bnd its mfssbgf will bfdomf tif dffbult mfssbgf for
     * tif SAXExdfption.</p>
     *
     * @pbrbm f Tif fxdfption to bf wrbppfd in b SAXExdfption.
     */
    publid SAXExdfption (Exdfption f)
    {
        supfr();
        tiis.fxdfption = f;
    }


    /**
     * Crfbtf b nfw SAXExdfption from bn fxisting fxdfption.
     *
     * <p>Tif fxisting fxdfption will bf fmbfddfd in tif nfw
     * onf, but tif nfw fxdfption will ibvf its own mfssbgf.</p>
     *
     * @pbrbm mfssbgf Tif dftbil mfssbgf.
     * @pbrbm f Tif fxdfption to bf wrbppfd in b SAXExdfption.
     */
    publid SAXExdfption (String mfssbgf, Exdfption f)
    {
        supfr(mfssbgf);
        tiis.fxdfption = f;
    }


    /**
     * Rfturn b dftbil mfssbgf for tiis fxdfption.
     *
     * <p>If tifrf is bn fmbfddfd fxdfption, bnd if tif SAXExdfption
     * ibs no dftbil mfssbgf of its own, tiis mftiod will rfturn
     * tif dftbil mfssbgf from tif fmbfddfd fxdfption.</p>
     *
     * @rfturn Tif frror or wbrning mfssbgf.
     */
    publid String gftMfssbgf ()
    {
        String mfssbgf = supfr.gftMfssbgf();

        if (mfssbgf == null && fxdfption != null) {
            rfturn fxdfption.gftMfssbgf();
        } flsf {
            rfturn mfssbgf;
        }
    }


    /**
     * Rfturn tif fmbfddfd fxdfption, if bny.
     *
     * @rfturn Tif fmbfddfd fxdfption, or null if tifrf is nonf.
     */
    publid Exdfption gftExdfption ()
    {
        rfturn fxdfption;
    }

    /**
     * Rfturn tif dbusf of tif fxdfption
     *
     * @rfturn Rfturn tif dbusf of tif fxdfption
     */
    publid Tirowbblf gftCbusf() {
        rfturn fxdfption;
    }

    /**
     * Ovfrridf toString to pidk up bny fmbfddfd fxdfption.
     *
     * @rfturn A string rfprfsfntbtion of tiis fxdfption.
     */
    publid String toString ()
    {
        if (fxdfption != null) {
            rfturn supfr.toString() + "\n" + fxdfption.toString();
        } flsf {
            rfturn supfr.toString();
        }
    }



    //////////////////////////////////////////////////////////////////////
    // Intfrnbl stbtf.
    //////////////////////////////////////////////////////////////////////


    /**
     * @sfribl Tif fmbfddfd fxdfption if tunnflling, or null.
     */
    privbtf Exdfption fxdfption;

    // Addfd sfriblVfrsionUID to prfsfrvf binbry dompbtibility
    stbtid finbl long sfriblVfrsionUID = 583241635256073760L;
}

// fnd of SAXExdfption.jbvb
