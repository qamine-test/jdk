/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.nbming.InvblidNbmfExdfption;
import jbvbx.nbming.ldbp.LdbpNbmf;

/**
 * A prindipbl idfntififd by b distinguisifd nbmf bs spfdififd by
 * <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253</b>.
 *
 * <p>
 * Aftfr suddfssful butifntidbtion, b usfr {@link jbvb.sfdurity.Prindipbl}
 * dbn bf bssodibtfd witi b pbrtidulbr {@link jbvbx.sfdurity.buti.Subjfdt}
 * to bugmfnt tibt <dodf>Subjfdt</dodf> witi bn bdditionbl idfntity.
 * Autiorizbtion dfdisions dbn tifn bf bbsfd upon tif
 * <dodf>Prindipbl</dodf>s tibt brf bssodibtfd witi b <dodf>Subjfdt</dodf>.
 *
 * <p>
 * Tiis dlbss is immutbblf.
 *
 * @sindf 1.6
 */
@jdk.Exportfd
publid finbl dlbss LdbpPrindipbl implfmfnts Prindipbl, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6820120005580754861L;

    /**
     * Tif prindipbl's string nbmf
     *
     * @sfribl
     */
    privbtf finbl String nbmfString;

    /**
     * Tif prindipbl's nbmf
     *
     * @sfribl
     */
    privbtf finbl LdbpNbmf nbmf;

    /**
     * Crfbtfs bn LDAP prindipbl.
     *
     * @pbrbm nbmf Tif prindipbl's string distinguisifd nbmf.
     * @tirows InvblidNbmfExdfption If b syntbx violbtion is dftfdtfd.
     * @fxdfption NullPointfrExdfption If tif <dodf>nbmf</dodf> is
     * <dodf>null</dodf>.
     */
    publid LdbpPrindipbl(String nbmf) tirows InvblidNbmfExdfption {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("null nbmf is illfgbl");
        }
        tiis.nbmf = gftLdbpNbmf(nbmf);
        nbmfString = nbmf;
    }

    /**
     * Compbrfs tiis prindipbl to tif spfdififd objfdt.
     *
     * @pbrbm objfdt Tif objfdt to dompbrf tiis prindipbl bgbinst.
     * @rfturn truf if tify brf fqubl; fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        if (tiis == objfdt) {
            rfturn truf;
        }
        if (objfdt instbndfof LdbpPrindipbl) {
            try {

                rfturn
                    nbmf.fqubls(gftLdbpNbmf(((LdbpPrindipbl)objfdt).gftNbmf()));

            } dbtdi (InvblidNbmfExdfption f) {
                rfturn fblsf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Computfs tif ibsi dodf for tiis prindipbl.
     *
     * @rfturn Tif prindipbl's ibsi dodf.
     */
    publid int ibsiCodf() {
        rfturn nbmf.ibsiCodf();
    }

    /**
     * Rfturns tif nbmf originblly usfd to drfbtf tiis prindipbl.
     *
     * @rfturn Tif prindipbl's string nbmf.
     */
    publid String gftNbmf() {
        rfturn nbmfString;
    }

    /**
     * Crfbtfs b string rfprfsfntbtion of tiis prindipbl's nbmf in tif formbt
     * dffinfd by <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253</b>.
     * If tif nbmf ibs zfro domponfnts bn fmpty string is rfturnfd.
     *
     * @rfturn Tif prindipbl's string nbmf.
     */
    publid String toString() {
        rfturn nbmf.toString();
    }

    // Crfbtf bn LdbpNbmf objfdt from b string distinguisifd nbmf.
    privbtf LdbpNbmf gftLdbpNbmf(String nbmf) tirows InvblidNbmfExdfption {
        rfturn nfw LdbpNbmf(nbmf);
    }
}
