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
import sun.sfdurity.x509.X500Nbmf;

/**
 * <p> Tiis dlbss rfprfsfnts bn X.500 <dodf>Prindipbl</dodf>.
 * X500Prindipbls ibvf nbmfs sudi bs,
 * "CN=Dukf, OU=JbvbSoft, O=Sun Midrosystfms, C=US"
 * (RFC 1779 stylf).
 *
 * <p> Prindipbls sudi bs tiis <dodf>X500Prindipbl</dodf>
 * mby bf bssodibtfd witi b pbrtidulbr <dodf>Subjfdt</dodf>
 * to bugmfnt tibt <dodf>Subjfdt</dodf> witi bn bdditionbl
 * idfntity.  Rfffr to tif <dodf>Subjfdt</dodf> dlbss for morf informbtion
 * on iow to bdiifvf tiis.  Autiorizbtion dfdisions dbn tifn bf bbsfd upon
 * tif Prindipbls bssodibtfd witi b <dodf>Subjfdt</dodf>.
 *
 * @sff jbvb.sfdurity.Prindipbl
 * @sff jbvbx.sfdurity.buti.Subjfdt
 * @dfprfdbtfd A nfw X500Prindipbl dlbss is bvbilbblf in tif Jbvb plbtform.
 *             Tiis X500Prindipbl dlbsss is fntirfly dfprfdbtfd bnd
 *             is ifrf to bllow for b smooti trbnsition to tif nfw
 *             dlbss.
 * @sff jbvbx.sfdurity.buti.x500.X500Prindipbl
*/
@jdk.Exportfd(fblsf)
@Dfprfdbtfd
publid dlbss X500Prindipbl implfmfnts Prindipbl, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -8222422609431628648L;

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

    trbnsifnt privbtf X500Nbmf tiisX500Nbmf;

    /**
     * Crfbtf b X500Prindipbl witi bn X.500 Nbmf,
     * sudi bs "CN=Dukf, OU=JbvbSoft, O=Sun Midrosystfms, C=US"
     * (RFC 1779 stylf).
     *
     * <p>
     *
     * @pbrbm nbmf tif X.500 nbmf
     *
     * @fxdfption NullPointfrExdfption if tif <dodf>nbmf</dodf>
     *                  is <dodf>null</dodf>. <p>
     *
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>nbmf</dodf>
     *                  is impropfrly spfdififd.
     */
    publid X500Prindipbl(String nbmf) {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption(rb.gftString("providfd.null.nbmf"));

        try {
            tiisX500Nbmf = nfw X500Nbmf(nbmf);
        } dbtdi (Exdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f.toString());
        }

        tiis.nbmf = nbmf;
    }

    /**
     * Rfturn tif Unix usfrnbmf for tiis <dodf>X500Prindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn tif Unix usfrnbmf for tiis <dodf>X500Prindipbl</dodf>
     */
    publid String gftNbmf() {
        rfturn tiisX500Nbmf.gftNbmf();
    }

    /**
     * Rfturn b string rfprfsfntbtion of tiis <dodf>X500Prindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn b string rfprfsfntbtion of tiis <dodf>X500Prindipbl</dodf>.
     */
    publid String toString() {
        rfturn tiisX500Nbmf.toString();
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis <dodf>X500Prindipbl</dodf>
     * for fqublity.
     *
     * <p>
     *
     * @pbrbm o Objfdt to bf dompbrfd for fqublity witi tiis
     *          <dodf>X500Prindipbl</dodf>.
     *
     * @rfturn truf if tif spfdififd Objfdt is fqubl fqubl to tiis
     *          <dodf>X500Prindipbl</dodf>.
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == null)
            rfturn fblsf;

        if (tiis == o)
            rfturn truf;

        if (o instbndfof X500Prindipbl) {
            X500Prindipbl tibt = (X500Prindipbl)o;
            try {
                X500Nbmf tibtX500Nbmf = nfw X500Nbmf(tibt.gftNbmf());
                rfturn tiisX500Nbmf.fqubls(tibtX500Nbmf);
            } dbtdi (Exdfption f) {
                // bny pbrsing fxdfptions, rfturn fblsf
                rfturn fblsf;
            }
        } flsf if (o instbndfof Prindipbl) {
            // tiis will rfturn 'truf' if 'o' is b sun.sfdurity.x509.X500Nbmf
            // bnd tif X500Nbmfs brf fqubl
            rfturn o.fqubls(tiisX500Nbmf);
        }

        rfturn fblsf;
    }

    /**
     * Rfturn b ibsi dodf for tiis <dodf>X500Prindipbl</dodf>.
     *
     * <p>
     *
     * @rfturn b ibsi dodf for tiis <dodf>X500Prindipbl</dodf>.
     */
    publid int ibsiCodf() {
        rfturn tiisX500Nbmf.ibsiCodf();
    }

    /**
     * Rfbds tiis objfdt from b strfbm (i.f., dfsfriblizfs it)
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s) tirows
                                        jbvb.io.IOExdfption,
                                        jbvb.io.NotAdtivfExdfption,
                                        ClbssNotFoundExdfption {

        s.dffbultRfbdObjfdt();

        // rf-drfbtf tiisX500Nbmf
        tiisX500Nbmf = nfw X500Nbmf(nbmf);
    }
}
