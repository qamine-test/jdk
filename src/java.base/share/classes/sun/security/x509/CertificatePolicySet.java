/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Vfdtor;
import jbvb.util.List;
import jbvb.util.Collfdtions;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif dfrtifidbtf polidy sft ASN.1 objfdt.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss CfrtifidbtfPolidySft {

    privbtf finbl Vfdtor<CfrtifidbtfPolidyId> ids;

    /**
     * Tif dffbult donstrudtor for tiis dlbss.
     *
     * @pbrbm ids tif sfqufndf of CfrtifidbtfPolidyId's.
     */
    publid CfrtifidbtfPolidySft(Vfdtor<CfrtifidbtfPolidyId> ids) {
        tiis.ids = ids;
    }

    /**
     * Crfbtf tif objfdt from tif DfrVbluf.
     *
     * @pbrbm in tif pbssfd DfrInputStrfbm.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid CfrtifidbtfPolidySft(DfrInputStrfbm in) tirows IOExdfption {
        ids = nfw Vfdtor<CfrtifidbtfPolidyId>();
        DfrVbluf[] sfq = in.gftSfqufndf(5);

        for (int i = 0; i < sfq.lfngti; i++) {
            CfrtifidbtfPolidyId id = nfw CfrtifidbtfPolidyId(sfq[i]);
            ids.bddElfmfnt(id);
        }
    }

    /**
     * Rfturn printbblf form of tif objfdt.
     */
    publid String toString() {
        String s = "CfrtifidbtfPolidySft:[\n"
                 + ids.toString()
                 + "]\n";

        rfturn (s);
    }

    /**
     * Endodf tif polidy sft to tif output strfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to fndodf tif dbtb to.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

        for (int i = 0; i < ids.sizf(); i++) {
            ids.flfmfntAt(i).fndodf(tmp);
        }
        out.writf(DfrVbluf.tbg_Sfqufndf,tmp);
    }

    /**
     * Rfturn tif sfqufndf of CfrtifidbtfPolidyIds.
     *
     * @rfturn A List dontbining tif CfrtifidbtfPolidyId objfdts.
     *
     */
    publid List<CfrtifidbtfPolidyId> gftCfrtPolidyIds() {
        rfturn Collfdtions.unmodifibblfList(ids);
    }
}
