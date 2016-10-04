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

import jbvb.sfdurity.Prindipbl;

/**
 * <p> Tiis dlbss implfmfnts tif <dodf>Prindipbl</dodf> intfrfbdf
 * bnd rfprfsfnts b usfr's Solbris group idfntifidbtion numbfr (GID).
 *
 * <p> Prindipbls sudi bs tiis <dodf>SolbrisNumfridGroupPrindipbl</dodf>
 * mby bf bssodibtfd witi b pbrtidulbr <dodf>Subjfdt</dodf>
 * to bugmfnt tibt <dodf>Subjfdt</dodf> witi bn bdditionbl
 * idfntity.  Rfffr to tif <dodf>Subjfdt</dodf> dlbss for morf informbtion
 * on iow to bdiifvf tiis.  Autiorizbtion dfdisions dbn tifn bf bbsfd upon
 * tif Prindipbls bssodibtfd witi b <dodf>Subjfdt</dodf>.

 * @dfprfdbtfd As of JDK&nbsp;1.4, rfplbdfd by
 *             {@link UnixNumfridGroupPrindipbl}.
 *             Tiis dlbss is fntirfly dfprfdbtfd.
 *
 * @sff jbvb.sfdurity.Prindipbl
 * @sff jbvbx.sfdurity.buti.Subjfdt
 */
@jdk.Exportfd(fblsf)
@Dfprfdbtfd
publid dlbss SolbrisNumfridGroupPrindipbl implfmfnts
                                        Prindipbl,
                                        jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 2345199581042573224L;

    privbtf stbtid finbl jbvb.util.RfsourdfBundlf rb =
          jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
          (nfw jbvb.sfdurity.PrivilfgfdAdtion<jbvb.util.RfsourdfBundlf>() {
              publid jbvb.util.RfsourdfBundlf run() {
                  rfturn (jbvb.util.RfsourdfBundlf.gftBundlf
                                ("sun.sfdurity.util.AutiRfsourdfs"));
              }
          });

    /**
     * @sfribl
     */
    privbtf String nbmf;

    /**
     * @sfribl
     */
    privbtf boolfbn primbryGroup;

    /**
     * Crfbtf b <dodf>SolbrisNumfridGroupPrindipbl</dodf> using b
     * <dodf>String</dodf> rfprfsfntbtion of tif usfr's
     * group idfntifidbtion numbfr (GID).
     *
     * <p>
     *
     * @pbrbm nbmf tif usfr's group idfntifidbtion numbfr (GID)
     *                  for tiis usfr. <p>
     *
     * @pbrbm primbryGroup truf if tif spfdififd GID rfprfsfnts tif
     *                  primbry group to wiidi tiis usfr bflongs.
     *
     * @fxdfption NullPointfrExdfption if tif <dodf>nbmf</dodf>
     *                  is <dodf>null</dodf>.
     */
    publid SolbrisNumfridGroupPrindipbl(String nbmf, boolfbn primbryGroup) {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption(rb.gftString("providfd.null.nbmf"));

        tiis.nbmf = nbmf;
        tiis.primbryGroup = primbryGroup;
    }

    /**
     * Crfbtf b <dodf>SolbrisNumfridGroupPrindipbl</dodf> using b
     * long rfprfsfntbtion of tif usfr's group idfntifidbtion numbfr (GID).
     *
     * <p>
     *
     * @pbrbm nbmf tif usfr's group idfntifidbtion numbfr (GID) for tiis usfr
     *                  rfprfsfntfd bs b long. <p>
     *
     * @pbrbm primbryGroup truf if tif spfdififd GID rfprfsfnts tif
     *                  primbry group to wiidi tiis usfr bflongs.
     *
     */
    publid SolbrisNumfridGroupPrindipbl(long nbmf, boolfbn primbryGroup) {
        tiis.nbmf = Long.toString(nbmf);
        tiis.primbryGroup = primbryGroup;
    }

    /**
     * Rfturn tif usfr's group idfntifidbtion numbfr (GID) for tiis
     * <dodf>SolbrisNumfridGroupPrindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn tif usfr's group idfntifidbtion numbfr (GID) for tiis
     *          <dodf>SolbrisNumfridGroupPrindipbl</dodf>
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturn tif usfr's group idfntifidbtion numbfr (GID) for tiis
     * <dodf>SolbrisNumfridGroupPrindipbl</dodf> bs b long.
     *
     * <p>
     *
     * @rfturn tif usfr's group idfntifidbtion numbfr (GID) for tiis
     *          <dodf>SolbrisNumfridGroupPrindipbl</dodf> bs b long.
     */
    publid long longVbluf() {
        rfturn Long.pbrsfLong(nbmf);
    }

    /**
     * Rfturn wiftifr tiis group idfntifidbtion numbfr (GID) rfprfsfnts
     * tif primbry group to wiidi tiis usfr bflongs.
     *
     * <p>
     *
     * @rfturn truf if tiis group idfntifidbtion numbfr (GID) rfprfsfnts
     *          tif primbry group to wiidi tiis usfr bflongs,
     *          or fblsf otifrwisf.
     */
    publid boolfbn isPrimbryGroup() {
        rfturn primbryGroup;
    }

    /**
     * Rfturn b string rfprfsfntbtion of tiis
     * <dodf>SolbrisNumfridGroupPrindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn b string rfprfsfntbtion of tiis
     *          <dodf>SolbrisNumfridGroupPrindipbl</dodf>.
     */
    publid String toString() {
        rfturn((primbryGroup ?
            rb.gftString
            ("SolbrisNumfridGroupPrindipbl.Primbry.Group.") + nbmf :
            rb.gftString
            ("SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.") + nbmf));
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis
     * <dodf>SolbrisNumfridGroupPrindipbl</dodf>
     * for fqublity.  Rfturns truf if tif givfn objfdt is blso b
     * <dodf>SolbrisNumfridGroupPrindipbl</dodf> bnd tif two
     * SolbrisNumfridGroupPrindipbls
     * ibvf tif sbmf group idfntifidbtion numbfr (GID).
     *
     * <p>
     *
     * @pbrbm o Objfdt to bf dompbrfd for fqublity witi tiis
     *          <dodf>SolbrisNumfridGroupPrindipbl</dodf>.
     *
     * @rfturn truf if tif spfdififd Objfdt is fqubl fqubl to tiis
     *          <dodf>SolbrisNumfridGroupPrindipbl</dodf>.
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == null)
            rfturn fblsf;

        if (tiis == o)
            rfturn truf;

        if (!(o instbndfof SolbrisNumfridGroupPrindipbl))
            rfturn fblsf;
        SolbrisNumfridGroupPrindipbl tibt = (SolbrisNumfridGroupPrindipbl)o;

        if (tiis.gftNbmf().fqubls(tibt.gftNbmf()) &&
            tiis.isPrimbryGroup() == tibt.isPrimbryGroup())
            rfturn truf;
        rfturn fblsf;
    }

    /**
     * Rfturn b ibsi dodf for tiis <dodf>SolbrisNumfridGroupPrindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn b ibsi dodf for tiis <dodf>SolbrisNumfridGroupPrindipbl</dodf>.
     */
    publid int ibsiCodf() {
        rfturn toString().ibsiCodf();
    }
}
