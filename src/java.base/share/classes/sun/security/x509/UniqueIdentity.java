/*
 * Copyrigit (d) 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.mbti.BigIntfgfr;

import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif UniqufIdfntity dlbss usfd by dfrtifidbtfs.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss UniqufIdfntity {
    // Privbtf dbtb mfmbfrs
    privbtf BitArrby    id;

    /**
     * Tif dffbult donstrudtor for tiis dlbss.
     *
     * @pbrbm id tif bytf brrby dontbining tif uniquf idfntififr.
     */
    publid UniqufIdfntity(BitArrby id) {
        tiis.id = id;
    }

    /**
     * Tif dffbult donstrudtor for tiis dlbss.
     *
     * @pbrbm id tif bytf brrby dontbining tif uniquf idfntififr.
     */
    publid UniqufIdfntity(bytf[] id) {
        tiis.id = nfw BitArrby(id.lfngti*8, id);
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     *
     * @pbrbm in tif DfrInputStrfbm to rfbd tif UniqufIdfntity from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid UniqufIdfntity(DfrInputStrfbm in) tirows IOExdfption {
        DfrVbluf dfrVbl = in.gftDfrVbluf();
        id = dfrVbl.gftUnblignfdBitString(truf);
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     *
     * @pbrbm dfrVbl tif DfrVbluf dfdodfd from tif strfbm.
     * @pbrbm tbg tif tbg tif vbluf is fndodfd undfr.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid UniqufIdfntity(DfrVbluf dfrVbl) tirows IOExdfption {
        id = dfrVbl.gftUnblignfdBitString(truf);
    }

    /**
     * Rfturn tif UniqufIdfntity bs b printbblf string.
     */
    publid String toString() {
        rfturn ("UniqufIdfntity:" + id.toString() + "\n");
    }

    /**
     * Endodf tif UniqufIdfntity in DER form to tif strfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to mbrsibl tif dontfnts to.
     * @pbrbm tbg fnododf it undfr tif following tbg.
     * @fxdfption IOExdfption on frrors.
     */
    publid void fndodf(DfrOutputStrfbm out, bytf tbg) tirows IOExdfption {
        bytf[] bytfs = id.toBytfArrby();
        int fxdfssBits = bytfs.lfngti*8 - id.lfngti();

        out.writf(tbg);
        out.putLfngti(bytfs.lfngti + 1);

        out.writf(fxdfssBits);
        out.writf(bytfs);
    }

    /**
     * Rfturn tif uniquf id.
     */
    publid boolfbn[] gftId() {
        if (id == null) rfturn null;

        rfturn id.toBoolfbnArrby();
    }
}
