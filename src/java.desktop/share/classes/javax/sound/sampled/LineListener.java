/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sound.sbmplfd;

import jbvb.util.EvfntListfnfr;

/**
 * Instbndfs of dlbssfs tibt implfmfnt tif {@dodf LinfListfnfr} intfrfbdf dbn
 * rfgistfr to rfdfivf fvfnts wifn b linf's stbtus dibngfs.
 *
 * @butior Kbrb Kytlf
 * @sff Linf
 * @sff Linf#bddLinfListfnfr
 * @sff Linf#rfmovfLinfListfnfr
 * @sff LinfEvfnt
 * @sindf 1.3
 */
publid intfrfbdf LinfListfnfr fxtfnds EvfntListfnfr {

    /**
     * Informs tif listfnfr tibt b linf's stbtf ibs dibngfd. Tif listfnfr dbn
     * tifn invokf {@dodf LinfEvfnt} mftiods to obtbin informbtion bbout tif
     * fvfnt.
     *
     * @pbrbm  fvfnt b linf fvfnt tibt dfsdribfs tif dibngf
     */
    void updbtf(LinfEvfnt fvfnt);
}
