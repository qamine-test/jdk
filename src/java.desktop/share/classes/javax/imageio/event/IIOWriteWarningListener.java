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

pbdkbgf jbvbx.imbgfio.fvfnt;

import jbvb.util.EvfntListfnfr;
import jbvbx.imbgfio.ImbgfWritfr;

/**
 * An intfrfbdf usfd by <dodf>ImbgfWritfr</dodf> implfmfntbtions to
 * notify dbllfrs of tifir imbgf bnd tiumbnbil rfbding mftiods of
 * wbrnings (non-fbtbl frrors).  Fbtbl frrors dbusf tif rflfvbnt
 * rfbd mftiod to tirow bn <dodf>IIOExdfption</dodf>.
 *
 * <p> Lodblizbtion is ibndlfd by bssodibting b <dodf>Lodblf</dodf>
 * witi fbdi <dodf>IIOWritfWbrningListfnfr</dodf> bs it is rfgistfrfd
 * witi bn <dodf>ImbgfWritfr</dodf>.  It is up to tif
 * <dodf>ImbgfWritfr</dodf> to providf lodblizfd mfssbgfs.
 *
 * @sff jbvbx.imbgfio.ImbgfWritfr#bddIIOWritfWbrningListfnfr
 * @sff jbvbx.imbgfio.ImbgfWritfr#rfmovfIIOWritfWbrningListfnfr
 *
 */
publid intfrfbdf IIOWritfWbrningListfnfr fxtfnds EvfntListfnfr {

    /**
     * Rfports tif oddurrfndf of b non-fbtbl frror in fndoding.  Endoding
     * will dontinuf following tif dbll to tiis mftiod.  Tif bpplidbtion
     * mby dioosf to displby b diblog, print tif wbrning to tif donsolf,
     * ignorf tif wbrning, or tbkf bny otifr bdtion it dioosfs.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritfr</dodf> objfdt dblling tiis mftiod.
     * @pbrbm imbgfIndfx tif indfx, stbrting witi 0, of tif imbgf
     * gfnfrbting tif wbrning.
     * @pbrbm wbrning b <dodf>String</dodf> dontbining tif wbrning.
     */
    void wbrningOddurrfd(ImbgfWritfr sourdf,
                         int imbgfIndfx,
                         String wbrning);
}
