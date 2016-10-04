/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import jbvb.util.EvfntObjfdt;

/**
 * A "PropfrtyCibngf" fvfnt gfts dflivfrfd wifnfvfr b bfbn dibngfs b "bound"
 * or "donstrbinfd" propfrty.  A PropfrtyCibngfEvfnt objfdt is sfnt bs bn
 * brgumfnt to tif PropfrtyCibngfListfnfr bnd VftobblfCibngfListfnfr mftiods.
 * <P>
 * Normblly PropfrtyCibngfEvfnts brf bddompbnifd by tif nbmf bnd tif old
 * bnd nfw vbluf of tif dibngfd propfrty.  If tif nfw vbluf is b primitivf
 * typf (sudi bs int or boolfbn) it must bf wrbppfd bs tif
 * dorrfsponding jbvb.lbng.* Objfdt typf (sudi bs Intfgfr or Boolfbn).
 * <P>
 * Null vblufs mby bf providfd for tif old bnd tif nfw vblufs if tifir
 * truf vblufs brf not known.
 * <P>
 * An fvfnt sourdf mby sfnd b null objfdt bs tif nbmf to indidbtf tibt bn
 * brbitrbry sft of if its propfrtifs ibvf dibngfd.  In tiis dbsf tif
 * old bnd nfw vblufs siould blso bf null.
 *
 * @sindf 1.1
 */
publid dlbss PropfrtyCibngfEvfnt fxtfnds EvfntObjfdt {
    privbtf stbtid finbl long sfriblVfrsionUID = 7042693688939648123L;

    /**
     * Construdts b nfw {@dodf PropfrtyCibngfEvfnt}.
     *
     * @pbrbm sourdf        tif bfbn tibt firfd tif fvfnt
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd
     * @pbrbm oldVbluf      tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf      tif nfw vbluf of tif propfrty
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf sourdf} is {@dodf null}
     */
    publid PropfrtyCibngfEvfnt(Objfdt sourdf, String propfrtyNbmf,
                               Objfdt oldVbluf, Objfdt nfwVbluf) {
        supfr(sourdf);
        tiis.propfrtyNbmf = propfrtyNbmf;
        tiis.nfwVbluf = nfwVbluf;
        tiis.oldVbluf = oldVbluf;
    }

    /**
     * Gfts tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd.
     *
     * @rfturn  Tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd.
     *          Mby bf null if multiplf propfrtifs ibvf dibngfd.
     */
    publid String gftPropfrtyNbmf() {
        rfturn propfrtyNbmf;
    }

    /**
     * Gfts tif nfw vbluf for tif propfrty, fxprfssfd bs bn Objfdt.
     *
     * @rfturn  Tif nfw vbluf for tif propfrty, fxprfssfd bs bn Objfdt.
     *          Mby bf null if multiplf propfrtifs ibvf dibngfd.
     */
    publid Objfdt gftNfwVbluf() {
        rfturn nfwVbluf;
    }

    /**
     * Gfts tif old vbluf for tif propfrty, fxprfssfd bs bn Objfdt.
     *
     * @rfturn  Tif old vbluf for tif propfrty, fxprfssfd bs bn Objfdt.
     *          Mby bf null if multiplf propfrtifs ibvf dibngfd.
     */
    publid Objfdt gftOldVbluf() {
        rfturn oldVbluf;
    }

    /**
     * Sfts tif propbgbtionId objfdt for tif fvfnt.
     *
     * @pbrbm propbgbtionId  Tif propbgbtionId objfdt for tif fvfnt.
     */
    publid void sftPropbgbtionId(Objfdt propbgbtionId) {
        tiis.propbgbtionId = propbgbtionId;
    }

    /**
     * Tif "propbgbtionId" fifld is rfsfrvfd for futurf usf.  In Bfbns 1.0
     * tif solf rfquirfmfnt is tibt if b listfnfr dbtdifs b PropfrtyCibngfEvfnt
     * bnd tifn firfs b PropfrtyCibngfEvfnt of its own, tifn it siould
     * mbkf surf tibt it propbgbtfs tif propbgbtionId fifld from its
     * indoming fvfnt to its outgoing fvfnt.
     *
     * @rfturn tif propbgbtionId objfdt bssodibtfd witi b bound/donstrbinfd
     *          propfrty updbtf.
     */
    publid Objfdt gftPropbgbtionId() {
        rfturn propbgbtionId;
    }

    /**
     * nbmf of tif propfrty tibt dibngfd.  Mby bf null, if not known.
     * @sfribl
     */
    privbtf String propfrtyNbmf;

    /**
     * Nfw vbluf for propfrty.  Mby bf null if not known.
     * @sfribl
     */
    privbtf Objfdt nfwVbluf;

    /**
     * Prfvious vbluf for propfrty.  Mby bf null if not known.
     * @sfribl
     */
    privbtf Objfdt oldVbluf;

    /**
     * Propbgbtion ID.  Mby bf null.
     * @sfribl
     * @sff #gftPropbgbtionId
     */
    privbtf Objfdt propbgbtionId;

    /**
     * Rfturns b string rfprfsfntbtion of tif objfdt.
     *
     * @rfturn b string rfprfsfntbtion of tif objfdt
     *
     * @sindf 1.7
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(gftClbss().gftNbmf());
        sb.bppfnd("[propfrtyNbmf=").bppfnd(gftPropfrtyNbmf());
        bppfndTo(sb);
        sb.bppfnd("; oldVbluf=").bppfnd(gftOldVbluf());
        sb.bppfnd("; nfwVbluf=").bppfnd(gftNfwVbluf());
        sb.bppfnd("; propbgbtionId=").bppfnd(gftPropbgbtionId());
        sb.bppfnd("; sourdf=").bppfnd(gftSourdf());
        rfturn sb.bppfnd("]").toString();
    }

    void bppfndTo(StringBuildfr sb) {
    }
}
