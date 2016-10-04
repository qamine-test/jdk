/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.buti;

/**
 * <p> Tiis dlbss fxtfnds <dodf>NTSid</dodf>
 * bnd rfprfsfnts b Windows NT usfr's dombin SID.
 *
 * <p> An NT usfr only ibs b dombin SID if in fbdt tify brf loggfd
 * into bn NT dombin.  If tif usfr is loggfd into b workgroup or
 * just b stbndblonf donfigurbtion, tify will NOT ibvf b dombin SID.
 *
 * <p> Prindipbls sudi bs tiis <dodf>NTSidDombinPrindipbl</dodf>
 * mby bf bssodibtfd witi b pbrtidulbr <dodf>Subjfdt</dodf>
 * to bugmfnt tibt <dodf>Subjfdt</dodf> witi bn bdditionbl
 * idfntity.  Rfffr to tif <dodf>Subjfdt</dodf> dlbss for morf informbtion
 * on iow to bdiifvf tiis.  Autiorizbtion dfdisions dbn tifn bf bbsfd upon
 * tif Prindipbls bssodibtfd witi b <dodf>Subjfdt</dodf>.
 *
 * @sff jbvb.sfdurity.Prindipbl
 * @sff jbvbx.sfdurity.buti.Subjfdt
 */
@jdk.Exportfd
publid dlbss NTSidDombinPrindipbl fxtfnds NTSid {

    privbtf stbtid finbl long sfriblVfrsionUID = 5247810785821650912L;

    /**
     * Crfbtf bn <dodf>NTSidDombinPrindipbl</dodf> witi b Windows NT SID.
     *
     * <p>
     *
     * @pbrbm nbmf b string vfrsion of tif Windows NT SID for tiis
     *                  usfr's dombin.<p>
     *
     * @fxdfption NullPointfrExdfption if tif <dodf>nbmf</dodf>
     *                  is <dodf>null</dodf>.
     */
    publid NTSidDombinPrindipbl(String nbmf) {
        supfr(nbmf);
    }

    /**
     * Rfturn b string rfprfsfntbtion of tiis <dodf>NTSidDombinPrindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn b string rfprfsfntbtion of tiis
     *          <dodf>NTSidDombinPrindipbl</dodf>.
     */
    publid String toString() {
        jbvb.tfxt.MfssbgfFormbt form = nfw jbvb.tfxt.MfssbgfFormbt
                (sun.sfdurity.util.RfsourdfsMgr.gftString
                        ("NTSidDombinPrindipbl.nbmf",
                        "sun.sfdurity.util.AutiRfsourdfs"));
        Objfdt[] sourdf = {gftNbmf()};
        rfturn form.formbt(sourdf);
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis <dodf>NTSidDombinPrindipbl</dodf>
     * for fqublity.  Rfturns truf if tif givfn objfdt is blso b
     * <dodf>NTSidDombinPrindipbl</dodf> bnd tif two NTSidDombinPrindipbls
     * ibvf tif sbmf SID.
     *
     * <p>
     *
     * @pbrbm o Objfdt to bf dompbrfd for fqublity witi tiis
     *          <dodf>NTSidDombinPrindipbl</dodf>.
     *
     * @rfturn truf if tif spfdififd Objfdt is fqubl fqubl to tiis
     *          <dodf>NTSidDombinPrindipbl</dodf>.
     */
    publid boolfbn fqubls(Objfdt o) {
            if (o == null)
                rfturn fblsf;

        if (tiis == o)
            rfturn truf;

        if (!(o instbndfof NTSidDombinPrindipbl))
            rfturn fblsf;

        rfturn supfr.fqubls(o);
    }
}
