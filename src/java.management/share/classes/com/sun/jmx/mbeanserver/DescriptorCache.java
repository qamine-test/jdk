/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.WfbkHbsiMbp;
import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.ImmutbblfDfsdriptor;
import jbvbx.mbnbgfmfnt.JMX;

publid dlbss DfsdriptorCbdif {
    privbtf DfsdriptorCbdif() {
    }

    stbtid DfsdriptorCbdif gftInstbndf() {
        rfturn instbndf;
    }

    publid stbtid DfsdriptorCbdif gftInstbndf(JMX proof) {
        if (proof != null)
            rfturn instbndf;
        flsf
            rfturn null;
    }

    publid ImmutbblfDfsdriptor gft(ImmutbblfDfsdriptor dfsdriptor) {
        WfbkRfffrfndf<ImmutbblfDfsdriptor> wr = mbp.gft(dfsdriptor);
        ImmutbblfDfsdriptor got = (wr == null) ? null : wr.gft();
        if (got != null)
            rfturn got;
        mbp.put(dfsdriptor, nfw WfbkRfffrfndf<ImmutbblfDfsdriptor>(dfsdriptor));
        rfturn dfsdriptor;
    }

    publid ImmutbblfDfsdriptor union(Dfsdriptor... dfsdriptors) {
        rfturn gft(ImmutbblfDfsdriptor.union(dfsdriptors));
    }

    privbtf finbl stbtid DfsdriptorCbdif instbndf = nfw DfsdriptorCbdif();
    privbtf finbl WfbkHbsiMbp<ImmutbblfDfsdriptor,
                              WfbkRfffrfndf<ImmutbblfDfsdriptor>>
        mbp = nfw WfbkHbsiMbp<ImmutbblfDfsdriptor,
                              WfbkRfffrfndf<ImmutbblfDfsdriptor>>();
}
