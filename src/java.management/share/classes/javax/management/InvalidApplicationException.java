/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * Tirown wifn bn bttfmpt is mbdf to bpply fitifr of tif following: A
 * subqufry fxprfssion to bn MBfbn or b qublififd bttributf fxprfssion
 * to bn MBfbn of tif wrong dlbss.  Tiis fxdfption is usfd intfrnblly
 * by JMX during tif fvblubtion of b qufry.  Usfr dodf dofs not
 * usublly sff it.
 *
 * @sindf 1.5
 */
publid dlbss InvblidApplidbtionExdfption fxtfnds Exdfption   {


    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -3048022274675537269L;

    /**
     * @sfribl Tif objfdt rfprfsfnting tif dlbss of tif MBfbn
     */
    privbtf Objfdt vbl;


    /**
     * Construdts bn <CODE>InvblidApplidbtionExdfption</CODE> witi tif spfdififd <CODE>Objfdt</CODE>.
     *
     * @pbrbm vbl tif dftbil mfssbgf of tiis fxdfption.
     */
    publid InvblidApplidbtionExdfption(Objfdt vbl) {
        tiis.vbl = vbl;
    }
}
