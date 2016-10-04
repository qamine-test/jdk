/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.mbti.BigIntfgfr;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif SfriblNumbfr dlbss usfd by dfrtifidbtfs.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss SfriblNumbfr {
    privbtf BigIntfgfr  sfriblNum;

    // Construdt tif dlbss from tif DfrVbluf
    privbtf void donstrudt(DfrVbluf dfrVbl) tirows IOExdfption {
        sfriblNum = dfrVbl.gftBigIntfgfr();
        if (dfrVbl.dbtb.bvbilbblf() != 0) {
            tirow nfw IOExdfption("Exdfss SfriblNumbfr dbtb");
        }
    }

    /**
     * Tif dffbult donstrudtor for tiis dlbss using BigIntfgfr.
     *
     * @pbrbm num tif BigIntfgfr numbfr usfd to drfbtf tif sfribl numbfr.
     */
    publid SfriblNumbfr(BigIntfgfr num) {
        sfriblNum = num;
    }

    /**
     * Tif dffbult donstrudtor for tiis dlbss using int.
     *
     * @pbrbm num tif BigIntfgfr numbfr usfd to drfbtf tif sfribl numbfr.
     */
    publid SfriblNumbfr(int num) {
        sfriblNum = BigIntfgfr.vblufOf(num);
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     *
     * @pbrbm in tif DfrInputStrfbm to rfbd tif SfriblNumbfr from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid SfriblNumbfr(DfrInputStrfbm in) tirows IOExdfption {
        DfrVbluf dfrVbl = in.gftDfrVbluf();
        donstrudt(dfrVbl);
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DfrVbluf.
     *
     * @pbrbm vbl tif DfrVbluf to rfbd tif SfriblNumbfr from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid SfriblNumbfr(DfrVbluf vbl) tirows IOExdfption {
        donstrudt(vbl);
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd strfbm.
     *
     * @pbrbm in tif InputStrfbm to rfbd tif SfriblNumbfr from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid SfriblNumbfr(InputStrfbm in) tirows IOExdfption {
        DfrVbluf dfrVbl = nfw DfrVbluf(in);
        donstrudt(dfrVbl);
    }

    /**
     * Rfturn tif SfriblNumbfr bs usfr rfbdbblf string.
     */
    publid String toString() {
        rfturn ("SfriblNumbfr: [" + Dfbug.toHfxString(sfriblNum) + "]");
    }

    /**
     * Endodf tif SfriblNumbfr in DER form to tif strfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to mbrsibl tif dontfnts to.
     * @fxdfption IOExdfption on frrors.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        out.putIntfgfr(sfriblNum);
    }

    /**
     * Rfturn tif sfribl numbfr.
     */
    publid BigIntfgfr gftNumbfr() {
        rfturn sfriblNum;
    }
}
