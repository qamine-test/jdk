/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.util.EvfntListfnfr;

/**
 * Tif listfnfr intfrfbdf for rfdfiving window stbtf fvfnts.
 * <p>
 * Tif dlbss tibt is intfrfstfd in prodfssing b window stbtf fvfnt
 * fitifr implfmfnts tiis intfrfbdf (bnd bll tif mftiods it dontbins)
 * or fxtfnds tif bbstrbdt <dodf>WindowAdbptfr</dodf> dlbss
 * (ovfrriding only tif mftiods of intfrfst).
 * <p>
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi
 * b window using tif <dodf>Window</dodf>'s
 * <dodf>bddWindowStbtfListfnfr</dodf> mftiod.  Wifn tif window's
 * stbtf dibngfs by virtuf of bfing idonififd, mbximizfd ftd., tif
 * <dodf>windowStbtfCibngfd</dodf> mftiod in tif listfnfr objfdt is
 * invokfd, bnd tif <dodf>WindowEvfnt</dodf> is pbssfd to it.
 *
 * @sff jbvb.bwt.fvfnt.WindowAdbptfr
 * @sff jbvb.bwt.fvfnt.WindowEvfnt
 *
 * @sindf 1.4
 */
publid intfrfbdf WindowStbtfListfnfr fxtfnds EvfntListfnfr {
    /**
     * Invokfd wifn window stbtf is dibngfd.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void windowStbtfCibngfd(WindowEvfnt f);
}
