/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.util.EvfntListfnfr;

/**
 * A listfnfr intfrfbdf for rfdfiving mousf motion fvfnts during b drbg
 * opfrbtion.
 * <p>
 * Tif dlbss tibt is intfrfstfd in prodfssing mousf motion fvfnts during
 * b drbg opfrbtion fitifr implfmfnts tiis intfrfbdf or fxtfnds tif bbstrbdt
 * <dodf>DrbgSourdfAdbptfr</dodf> dlbss (ovfrriding only tif mftiods of
 * intfrfst).
 * <p>
 * Crfbtf b listfnfr objfdt using tibt dlbss bnd tifn rfgistfr it witi
 * b <dodf>DrbgSourdf</dodf>. Wifnfvfr tif mousf movfs during b drbg
 * opfrbtion initibtfd witi tiis <dodf>DrbgSourdf</dodf>, tibt objfdt's
 * <dodf>drbgMousfMovfd</dodf> mftiod is invokfd, bnd tif
 * <dodf>DrbgSourdfDrbgEvfnt</dodf> is pbssfd to it.
 *
 * @sff DrbgSourdfDrbgEvfnt
 * @sff DrbgSourdf
 * @sff DrbgSourdfListfnfr
 * @sff DrbgSourdfAdbptfr
 *
 * @sindf 1.4
 */

publid intfrfbdf DrbgSourdfMotionListfnfr fxtfnds EvfntListfnfr {

    /**
     * Cbllfd wifnfvfr tif mousf is movfd during b drbg opfrbtion.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    void drbgMousfMovfd(DrbgSourdfDrbgEvfnt dsdf);
}
