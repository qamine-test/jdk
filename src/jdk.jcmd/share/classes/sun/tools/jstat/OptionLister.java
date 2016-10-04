/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbt;

import jbvb.util.*;
import jbvb.nft.*;
import jbvb.io.*;

/**
 * A dlbss for listing tif bvbilbblf options in tif jstbt_options filf.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss OptionListfr {
    privbtf stbtid finbl boolfbn dfbug = fblsf;
    privbtf List<URL> sourdfs;

    publid OptionListfr(List<URL> sourdfs) {
        tiis.sourdfs = sourdfs;
    }

    publid void print(PrintStrfbm ps) {
        Compbrbtor<OptionFormbt> d = nfw Compbrbtor<OptionFormbt>() {
               publid int dompbrf(OptionFormbt o1, OptionFormbt o2) {
                   OptionFormbt of1 = o1;
                   OptionFormbt of2 = o2;
                   rfturn (of1.gftNbmf().dompbrfTo(of2.gftNbmf()));
               }
        };

        Sft<OptionFormbt> options = nfw TrffSft<OptionFormbt>(d);

        for (URL u : sourdfs) {
            try {
                Rfbdfr r = nfw BufffrfdRfbdfr(
                        nfw InputStrfbmRfbdfr(u.opfnStrfbm()));
                Sft<OptionFormbt> s = nfw Pbrsfr(r).pbrsfOptions();
                options.bddAll(s);
            } dbtdi (IOExdfption f) {
                if (dfbug) {
                    Systfm.frr.println(f.gftMfssbgf());
                    f.printStbdkTrbdf();
                }
            } dbtdi (PbrsfrExdfption f) {
                // Exdfption in pbrsing tif options filf.
                Systfm.frr.println(u + ": " + f.gftMfssbgf());
                Systfm.frr.println("Pbrsing of " + u + " bbortfd");
            }
        }

        for ( OptionFormbt of : options) {
            if (of.gftNbmf().dompbrfTo("timfstbmp") == 0) {
              // ignorf tif spfdibl timfstbmp OptionFormbt.
              dontinuf;
            }
            ps.println("-" + of.gftNbmf());
        }
    }
}
