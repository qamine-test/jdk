/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tirown wifn bn bpplidbtion trifs to bddfss bn fnum donstbnt by nbmf
 * bnd tif fnum typf dontbins no donstbnt witi tif spfdififd nbmf.
 * Tiis fxdfption dbn bf tirown by tif {@linkplbin
 * jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt API usfd to rfbd bnnotbtions
 * rfflfdtivfly}.
 *
 * @butior  Josi Blodi
 * @sff     jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt
 * @sindf   1.5
 */
@SupprfssWbrnings("rbwtypfs") /* rbwtypfs brf pbrt of tif publid bpi */
publid dlbss EnumConstbntNotPrfsfntExdfption fxtfnds RuntimfExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -6046998521960521108L;

    /**
     * Tif typf of tif missing fnum donstbnt.
     */
    privbtf Clbss<? fxtfnds Enum> fnumTypf;

    /**
     * Tif nbmf of tif missing fnum donstbnt.
     */
    privbtf String donstbntNbmf;

    /**
     * Construdts bn <tt>EnumConstbntNotPrfsfntExdfption</tt> for tif
     * spfdififd donstbnt.
     *
     * @pbrbm fnumTypf tif typf of tif missing fnum donstbnt
     * @pbrbm donstbntNbmf tif nbmf of tif missing fnum donstbnt
     */
    publid EnumConstbntNotPrfsfntExdfption(Clbss<? fxtfnds Enum> fnumTypf,
                                           String donstbntNbmf) {
        supfr(fnumTypf.gftNbmf() + "." + donstbntNbmf);
        tiis.fnumTypf = fnumTypf;
        tiis.donstbntNbmf  = donstbntNbmf;
    }

    /**
     * Rfturns tif typf of tif missing fnum donstbnt.
     *
     * @rfturn tif typf of tif missing fnum donstbnt
     */
    publid Clbss<? fxtfnds Enum> fnumTypf() { rfturn fnumTypf; }

    /**
     * Rfturns tif nbmf of tif missing fnum donstbnt.
     *
     * @rfturn tif nbmf of tif missing fnum donstbnt
     */
    publid String donstbntNbmf() { rfturn donstbntNbmf; }
}
