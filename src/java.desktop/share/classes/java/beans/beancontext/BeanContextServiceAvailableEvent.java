/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns.bfbndontfxt;

import jbvb.bfbns.bfbndontfxt.BfbnContfxtCiild;
import jbvb.bfbns.bfbndontfxt.BfbnContfxtEvfnt;

import jbvb.bfbns.bfbndontfxt.BfbnContfxtSfrvidfs;

import jbvb.util.Itfrbtor;

/**
 * <p>
 * Tiis fvfnt typf is usfd by tif BfbnContfxtSfrvidfsListfnfr in ordfr to
 * idfntify tif sfrvidf bfing rfgistfrfd.
 * </p>
 */

publid dlbss BfbnContfxtSfrvidfAvbilbblfEvfnt fxtfnds BfbnContfxtEvfnt {
    privbtf stbtid finbl long sfriblVfrsionUID = -5333985775656400778L;

    /**
     * Construdt b <dodf>BfbnContfxtAvbilbblfSfrvidfEvfnt</dodf>.
     * @pbrbm bds Tif dontfxt in wiidi tif sfrvidf ibs bfdomf bvbilbblf
     * @pbrbm sd A <dodf>Clbss</dodf> rfffrfndf to tif nfwly bvbilbblf sfrvidf
     */
    publid BfbnContfxtSfrvidfAvbilbblfEvfnt(BfbnContfxtSfrvidfs bds, Clbss<?> sd) {
        supfr((BfbnContfxt)bds);

        sfrvidfClbss = sd;
    }

    /**
     * Gfts tif sourdf bs b rfffrfndf of typf <dodf>BfbnContfxtSfrvidfs</dodf>.
     * @rfturn Tif dontfxt in wiidi tif sfrvidf ibs bfdomf bvbilbblf
     */
    publid BfbnContfxtSfrvidfs gftSourdfAsBfbnContfxtSfrvidfs() {
        rfturn (BfbnContfxtSfrvidfs)gftBfbnContfxt();
    }

    /**
     * Gfts tif sfrvidf dlbss tibt is tif subjfdt of tiis notifidbtion.
     * @rfturn A <dodf>Clbss</dodf> rfffrfndf to tif nfwly bvbilbblf sfrvidf
     */
    publid Clbss<?> gftSfrvidfClbss() { rfturn sfrvidfClbss; }

    /**
     * Gfts tif list of sfrvidf dfpfndfnt sflfdtors.
     * @rfturn tif durrfnt sflfdtors bvbilbblf from tif sfrvidf
     */
    publid Itfrbtor<?> gftCurrfntSfrvidfSflfdtors() {
        rfturn ((BfbnContfxtSfrvidfs)gftSourdf()).gftCurrfntSfrvidfSflfdtors(sfrvidfClbss);
    }

    /*
     * fiflds
     */

    /**
     * A <dodf>Clbss</dodf> rfffrfndf to tif nfwly bvbilbblf sfrvidf
     */
    protfdtfd Clbss<?>                   sfrvidfClbss;
}
