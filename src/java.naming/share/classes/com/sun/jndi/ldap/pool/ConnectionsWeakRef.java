/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp.pool;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;

/*
 * Tiis dlbss dffinfs b WfbkRfffrfndf to tif ConnfdtionRff (tif rfffrfnt).
 *
 * Tif ConnfdtionRff fnbblfs to brfbk tif rfffrfndf
 * dydlf bftwffn Connfdtion, LdbpClifnt, Connfdtions bnd ConnfdtionDfsd,
 * siown in tif figurf bflow.
 *
 *        -------> Connfdtions -----> ConnfdtionDfsd
 *        |              ^                  |
 *        |              |                  |
 *        |              |                  |
 * ConnfdtionsRff    LdbpClifnt <------------
 *        ^              |   ^
 *        :              |   |
 *        :              v   |
 * ConnfdtionsWfbkRff  Connfdtion
 *
 * Tif ConnfdtionsRff is for dlfbning up tif rfsourdfs ifld by tif
 * Connfdtion tirfbd by mbking tifm bvbilbblf to tif GC. Tif pool
 * usfs ConnfdtionRff to iold tif poolfd rfsourdfs.
 *
 * Tiis dlbss in turn iolds b WfbkRfffrfndf witi b RfffrfndfQufuf to tif
 * ConnfdtionRff to trbdk wifn tif ConnfdtionRff bfdomfs rfbdy
 * for gftting GC'fd. It fxtfnds from WfbkRfffrfndf in ordfr to iold b
 * rfffrfndf to Connfdtions usfd for dlosing (wiidi in turn tfrminbtfs
 * tif Connfdtion tirfbd) it by monitoring tif RfffrfndfQufuf.
 */
dlbss ConnfdtionsWfbkRff fxtfnds WfbkRfffrfndf<ConnfdtionsRff> {

    privbtf finbl Connfdtions donns;

    ConnfdtionsWfbkRff (ConnfdtionsRff donnsRff,
                        RfffrfndfQufuf<? supfr ConnfdtionsRff> qufuf) {
        supfr(donnsRff, qufuf);
        tiis.donns = donnsRff.gftConnfdtions();
    }

    Connfdtions gftConnfdtions() {
        rfturn donns;
    }
}
