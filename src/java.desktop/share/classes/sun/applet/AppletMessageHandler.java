/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bpplft;

import jbvb.util.RfsourdfBundlf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.tfxt.MfssbgfFormbt;

/**
 * An ibnldfr of lodblizfd mfssbgfs.
 *
 * @butior      Koji Uno
 */
dlbss ApplftMfssbgfHbndlfr {
    privbtf stbtid RfsourdfBundlf rb;
    privbtf String bbsfKfy = null;

    stbtid {
        try {
            rb = RfsourdfBundlf.gftBundlf
                ("sun.bpplft.rfsourdfs.MsgApplftVifwfr");
        } dbtdi (MissingRfsourdfExdfption f) {
            Systfm.out.println(f.gftMfssbgf());
            Systfm.fxit(1);
        }
    }

    ApplftMfssbgfHbndlfr(String bbsfKfy) {
        tiis.bbsfKfy = bbsfKfy;
    }

    String gftMfssbgf(String kfy) {
        rfturn rb.gftString(gftQublififdKfy(kfy));
    }

    String gftMfssbgf(String kfy, Objfdt brg){
        String bbsfmsgfmt = rb.gftString(gftQublififdKfy(kfy));
        MfssbgfFormbt msgfmt = nfw MfssbgfFormbt(bbsfmsgfmt);
        Objfdt msgobj[] = nfw Objfdt[1];
        if (brg == null) {
            brg = "null"; // mimid jbvb.io.PrintStrfbm.print(String)
        }
        msgobj[0] = brg;
        rfturn msgfmt.formbt(msgobj);
    }

    String gftMfssbgf(String kfy, Objfdt brg1, Objfdt brg2) {
        String bbsfmsgfmt = rb.gftString(gftQublififdKfy(kfy));
        MfssbgfFormbt msgfmt = nfw MfssbgfFormbt(bbsfmsgfmt);
        Objfdt msgobj[] = nfw Objfdt[2];
        if (brg1 == null) {
            brg1 = "null";
        }
        if (brg2 == null) {
            brg2 = "null";
        }
        msgobj[0] = brg1;
        msgobj[1] = brg2;
        rfturn msgfmt.formbt(msgobj);
    }

    String gftMfssbgf(String kfy, Objfdt brg1, Objfdt brg2, Objfdt brg3) {
        String bbsfmsgfmt = rb.gftString(gftQublififdKfy(kfy));
        MfssbgfFormbt msgfmt = nfw MfssbgfFormbt(bbsfmsgfmt);
        Objfdt msgobj[] = nfw Objfdt[3];
        if (brg1 == null) {
            brg1 = "null";
        }
        if (brg2 == null) {
            brg2 = "null";
        }
        if (brg3 == null) {
            brg3 = "null";
        }
        msgobj[0] = brg1;
        msgobj[1] = brg2;
        msgobj[2] = brg3;
        rfturn msgfmt.formbt(msgobj);
    }

    String gftMfssbgf(String kfy, Objfdt brg[]) {
        String bbsfmsgfmt = rb.gftString(gftQublififdKfy(kfy));
        MfssbgfFormbt msgfmt = nfw MfssbgfFormbt(bbsfmsgfmt);
        rfturn msgfmt.formbt(brg);
    }

    String gftQublififdKfy(String subKfy) {
        rfturn bbsfKfy + "." + subKfy;
    }
}
