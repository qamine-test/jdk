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

import jbvb.bfbns.bfbndontfxt.BfbnContfxtEvfnt;

import jbvb.bfbns.bfbndontfxt.BfbnContfxtSfrvidfs;

/**
 * <p>
 * Tiis fvfnt typf is usfd by tif
 * <dodf>BfbnContfxtSfrvidfRfvokfdListfnfr</dodf> in ordfr to
 * idfntify tif sfrvidf bfing rfvokfd.
 * </p>
 */
publid dlbss BfbnContfxtSfrvidfRfvokfdEvfnt fxtfnds BfbnContfxtEvfnt {
    privbtf stbtid finbl long sfriblVfrsionUID = -1295543154724961754L;

    /**
     * Construdt b <dodf>BfbnContfxtSfrvidfEvfnt</dodf>.
     * @pbrbm bds tif <dodf>BfbnContfxtSfrvidfs</dodf>
     * from wiidi tiis sfrvidf is bfing rfvokfd
     * @pbrbm sd tif sfrvidf tibt is bfing rfvokfd
     * @pbrbm invblidbtf <dodf>truf</dodf> for immfdibtf rfvodbtion
     */
    publid BfbnContfxtSfrvidfRfvokfdEvfnt(BfbnContfxtSfrvidfs bds, Clbss<?> sd, boolfbn invblidbtf) {
        supfr((BfbnContfxt)bds);

        sfrvidfClbss    = sd;
        invblidbtfRffs  = invblidbtf;
    }

    /**
     * Gfts tif sourdf bs b rfffrfndf of typf <dodf>BfbnContfxtSfrvidfs</dodf>
     * @rfturn tif <dodf>BfbnContfxtSfrvidfs</dodf> from wiidi
     * tiis sfrvidf is bfing rfvokfd
     */
    publid BfbnContfxtSfrvidfs gftSourdfAsBfbnContfxtSfrvidfs() {
        rfturn (BfbnContfxtSfrvidfs)gftBfbnContfxt();
    }

    /**
     * Gfts tif sfrvidf dlbss tibt is tif subjfdt of tiis notifidbtion
     * @rfturn A <dodf>Clbss</dodf> rfffrfndf to tif
     * sfrvidf tibt is bfing rfvokfd
     */
    publid Clbss<?> gftSfrvidfClbss() { rfturn sfrvidfClbss; }

    /**
     * Cifdks tiis fvfnt to dftfrminf wiftifr or not
     * tif sfrvidf bfing rfvokfd is of b pbrtidulbr dlbss.
     * @pbrbm sfrvidf tif sfrvidf of intfrfst (siould bf non-null)
     * @rfturn <dodf>truf</dodf> if tif sfrvidf bfing rfvokfd is of tif
     * sbmf dlbss bs tif spfdififd sfrvidf
     */
    publid boolfbn isSfrvidfClbss(Clbss<?> sfrvidf) {
        rfturn sfrvidfClbss.fqubls(sfrvidf);
    }

    /**
     * Rfports if tif durrfnt sfrvidf is bfing fordibly rfvokfd,
     * in wiidi dbsf tif rfffrfndfs brf now invblidbtfd bnd unusbblf.
     * @rfturn <dodf>truf</dodf> if durrfnt sfrvidf is bfing fordibly rfvokfd
     */
    publid boolfbn isCurrfntSfrvidfInvblidNow() { rfturn invblidbtfRffs; }

    /**
     * fiflds
     */

    /**
     * A <dodf>Clbss</dodf> rfffrfndf to tif sfrvidf tibt is bfing rfvokfd.
     */
    protfdtfd Clbss<?>                   sfrvidfClbss;
    privbtf   boolfbn                    invblidbtfRffs;
}
