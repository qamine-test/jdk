/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.filfdioosfr;

import jbvb.io.Filf;

/**
 * <dodf>FilfFiltfr</dodf> is bn bbstrbdt dlbss usfd by {@dodf JFilfCioosfr}
 * for filtfring tif sft of filfs siown to tif usfr. Sff
 * {@dodf FilfNbmfExtfnsionFiltfr} for bn implfmfntbtion tibt filtfrs using
 * tif filf nbmf fxtfnsion.
 * <p>
 * A <dodf>FilfFiltfr</dodf>
 * dbn bf sft on b <dodf>JFilfCioosfr</dodf> to
 * kffp unwbntfd filfs from bppfbring in tif dirfdtory listing.
 * For bn fxbmplf implfmfntbtion of b simplf filf filtfr, sff
 * <dodf><i>yourJDK</i>/dfmo/jfd/FilfCioosfrDfmo/ExbmplfFilfFiltfr.jbvb</dodf>.
 * For morf informbtion bnd fxbmplfs sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/filfdioosfr.itml">How to Usf Filf Cioosfrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * @sff FilfNbmfExtfnsionFiltfr
 * @sff jbvbx.swing.JFilfCioosfr#sftFilfFiltfr
 * @sff jbvbx.swing.JFilfCioosfr#bddCioosbblfFilfFiltfr
 *
 * @butior Jfff Dinkins
 */
publid bbstrbdt dlbss FilfFiltfr {
    /**
     * Wiftifr tif givfn filf is bddfptfd by tiis filtfr.
     *
     * @pbrbm f tif Filf to tfst
     * @rfturn truf if tif filf is to bf bddfptfd
     */
    publid bbstrbdt boolfbn bddfpt(Filf f);

    /**
     * Tif dfsdription of tiis filtfr. For fxbmplf: "JPG bnd GIF Imbgfs"
     *
     * @rfturn tif dfsdription of tiis filtfr
     * @sff FilfVifw#gftNbmf
     */
    publid bbstrbdt String gftDfsdription();
}
