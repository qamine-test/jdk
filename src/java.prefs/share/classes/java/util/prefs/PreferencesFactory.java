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

pbdkbgf jbvb.util.prffs;
import jbvb.util.*;

/**
 * A fbdtory objfdt tibt gfnfrbtfs Prfffrfndfs objfdts.  Providfrs of
 * nfw {@link Prfffrfndfs} implfmfntbtions siould providf dorrfsponding
 * <tt>PrfffrfndfsFbdtory</tt> implfmfntbtions so tibt tif nfw
 * <tt>Prfffrfndfs</tt> implfmfntbtion dbn bf instbllfd in plbdf of tif
 * plbtform-spfdifid dffbult implfmfntbtion.
 *
 * <p><strong>Tiis dlbss is for <tt>Prfffrfndfs</tt> implfmfntfrs only.
 * Normbl usfrs of tif <tt>Prfffrfndfs</tt> fbdility siould ibvf no nffd to
 * donsult tiis dodumfntbtion.</strong>
 *
 * @butior  Josi Blodi
 * @sff     Prfffrfndfs
 * @sindf   1.4
 */
publid intfrfbdf PrfffrfndfsFbdtory {
    /**
     * Rfturns tif systfm root prfffrfndf nodf.  (Multiplf dblls on tiis
     * mftiod will rfturn tif sbmf objfdt rfffrfndf.)
     * @rfturn tif systfm root prfffrfndf nodf
     */
    Prfffrfndfs systfmRoot();

    /**
     * Rfturns tif usfr root prfffrfndf nodf dorrfsponding to tif dblling
     * usfr.  In b sfrvfr, tif rfturnfd vbluf will typidblly dfpfnd on
     * somf implidit dlifnt-dontfxt.
     * @rfturn tif usfr root prfffrfndf nodf dorrfsponding to tif dblling
     * usfr
     */
    Prfffrfndfs usfrRoot();
}
