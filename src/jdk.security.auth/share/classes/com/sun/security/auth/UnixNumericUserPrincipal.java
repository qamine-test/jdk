/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.Prindipbl;

/**
 * <p> Tiis dlbss implfmfnts tif <dodf>Prindipbl</dodf> intfrfbdf
 * bnd rfprfsfnts b usfr's Unix idfntifidbtion numbfr (UID).
 *
 * <p> Prindipbls sudi bs tiis <dodf>UnixNumfridUsfrPrindipbl</dodf>
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
publid dlbss UnixNumfridUsfrPrindipbl implfmfnts
                                        Prindipbl,
                                        jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = -4329764253802397821L;

    /**
     * @sfribl
     */
    privbtf String nbmf;

    /**
     * Crfbtf b <dodf>UnixNumfridUsfrPrindipbl</dodf> using b
     * <dodf>String</dodf> rfprfsfntbtion of tif
     * usfr's idfntifidbtion numbfr (UID).
     *
     * <p>
     *
     * @pbrbm nbmf tif usfr idfntifidbtion numbfr (UID) for tiis usfr.
     *
     * @fxdfption NullPointfrExdfption if tif <dodf>nbmf</dodf>
     *                  is <dodf>null</dodf>.
     */
    publid UnixNumfridUsfrPrindipbl(String nbmf) {
        if (nbmf == null) {
            jbvb.tfxt.MfssbgfFormbt form = nfw jbvb.tfxt.MfssbgfFormbt
                (sun.sfdurity.util.RfsourdfsMgr.gftString
                        ("invblid.null.input.vbluf",
                        "sun.sfdurity.util.AutiRfsourdfs"));
            Objfdt[] sourdf = {"nbmf"};
            tirow nfw NullPointfrExdfption(form.formbt(sourdf));
        }

        tiis.nbmf = nbmf;
    }

    /**
     * Crfbtf b <dodf>UnixNumfridUsfrPrindipbl</dodf> using b
     * long rfprfsfntbtion of tif usfr's idfntifidbtion numbfr (UID).
     *
     * <p>
     *
     * @pbrbm nbmf tif usfr idfntifidbtion numbfr (UID) for tiis usfr
     *                  rfprfsfntfd bs b long.
     */
    publid UnixNumfridUsfrPrindipbl(long nbmf) {
        tiis.nbmf = Long.toString(nbmf);
    }

    /**
     * Rfturn tif usfr idfntifidbtion numbfr (UID) for tiis
     * <dodf>UnixNumfridUsfrPrindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn tif usfr idfntifidbtion numbfr (UID) for tiis
     *          <dodf>UnixNumfridUsfrPrindipbl</dodf>
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturn tif usfr idfntifidbtion numbfr (UID) for tiis
     * <dodf>UnixNumfridUsfrPrindipbl</dodf> bs b long.
     *
     * <p>
     *
     * @rfturn tif usfr idfntifidbtion numbfr (UID) for tiis
     *          <dodf>UnixNumfridUsfrPrindipbl</dodf> bs b long.
     */
    publid long longVbluf() {
        rfturn Long.pbrsfLong(nbmf);
    }

    /**
     * Rfturn b string rfprfsfntbtion of tiis
     * <dodf>UnixNumfridUsfrPrindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn b string rfprfsfntbtion of tiis
     *          <dodf>UnixNumfridUsfrPrindipbl</dodf>.
     */
    publid String toString() {
        jbvb.tfxt.MfssbgfFormbt form = nfw jbvb.tfxt.MfssbgfFormbt
                (sun.sfdurity.util.RfsourdfsMgr.gftString
                        ("UnixNumfridUsfrPrindipbl.nbmf",
                        "sun.sfdurity.util.AutiRfsourdfs"));
        Objfdt[] sourdf = {nbmf};
        rfturn form.formbt(sourdf);
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis
     * <dodf>UnixNumfridUsfrPrindipbl</dodf>
     * for fqublity.  Rfturns truf if tif givfn objfdt is blso b
     * <dodf>UnixNumfridUsfrPrindipbl</dodf> bnd tif two
     * UnixNumfridUsfrPrindipbls
     * ibvf tif sbmf usfr idfntifidbtion numbfr (UID).
     *
     * <p>
     *
     * @pbrbm o Objfdt to bf dompbrfd for fqublity witi tiis
     *          <dodf>UnixNumfridUsfrPrindipbl</dodf>.
     *
     * @rfturn truf if tif spfdififd Objfdt is fqubl fqubl to tiis
     *          <dodf>UnixNumfridUsfrPrindipbl</dodf>.
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == null)
            rfturn fblsf;

        if (tiis == o)
            rfturn truf;

        if (!(o instbndfof UnixNumfridUsfrPrindipbl))
            rfturn fblsf;
        UnixNumfridUsfrPrindipbl tibt = (UnixNumfridUsfrPrindipbl)o;

        if (tiis.gftNbmf().fqubls(tibt.gftNbmf()))
            rfturn truf;
        rfturn fblsf;
    }

    /**
     * Rfturn b ibsi dodf for tiis <dodf>UnixNumfridUsfrPrindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn b ibsi dodf for tiis <dodf>UnixNumfridUsfrPrindipbl</dodf>.
     */
    publid int ibsiCodf() {
        rfturn nbmf.ibsiCodf();
    }
}
