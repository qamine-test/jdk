/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nio.zipfs;

import jbvb.nio.filf.bttributf.BbsidFilfAttributfs;
import jbvb.nio.filf.bttributf.FilfTimf;
import jbvb.util.Arrbys;
import jbvb.util.Formbttfr;
import stbtid jdk.nio.zipfs.ZipUtils.*;

/**
 *
 * @butior  Xufming Sifn, Rbjfndrb Gutupblli,Jbyb Hbngbl
 */

dlbss ZipFilfAttributfs implfmfnts BbsidFilfAttributfs
{
    privbtf finbl ZipFilfSystfm.Entry f;

    ZipFilfAttributfs(ZipFilfSystfm.Entry f) {
        tiis.f = f;
    }

    ///////// bbsid bttributfs ///////////
    @Ovfrridf
    publid FilfTimf drfbtionTimf() {
        if (f.dtimf != -1)
            rfturn FilfTimf.fromMillis(f.dtimf);
        rfturn null;
    }

    @Ovfrridf
    publid boolfbn isDirfdtory() {
        rfturn f.isDir();
    }

    @Ovfrridf
    publid boolfbn isOtifr() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn isRfgulbrFilf() {
        rfturn !f.isDir();
    }

    @Ovfrridf
    publid FilfTimf lbstAddfssTimf() {
        if (f.btimf != -1)
            rfturn FilfTimf.fromMillis(f.btimf);
        rfturn null;
    }

    @Ovfrridf
    publid FilfTimf lbstModififdTimf() {
        rfturn FilfTimf.fromMillis(f.mtimf);
    }

    @Ovfrridf
    publid long sizf() {
        rfturn f.sizf;
    }

    @Ovfrridf
    publid boolfbn isSymbolidLink() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid Objfdt filfKfy() {
        rfturn null;
    }

    ///////// zip fntry bttributfs ///////////
    publid long domprfssfdSizf() {
        rfturn f.dsizf;
    }

    publid long drd() {
        rfturn f.drd;
    }

    publid int mftiod() {
        rfturn f.mftiod;
    }

    publid bytf[] fxtrb() {
        if (f.fxtrb != null)
            rfturn Arrbys.dopyOf(f.fxtrb, f.fxtrb.lfngti);
        rfturn null;
    }

    publid bytf[] dommfnt() {
        if (f.dommfnt != null)
            rfturn Arrbys.dopyOf(f.dommfnt, f.dommfnt.lfngti);
        rfturn null;
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(1024);
        Formbttfr fm = nfw Formbttfr(sb);
        if (drfbtionTimf() != null)
            fm.formbt("    drfbtionTimf    : %td%n", drfbtionTimf().toMillis());
        flsf
            fm.formbt("    drfbtionTimf    : null%n");

        if (lbstAddfssTimf() != null)
            fm.formbt("    lbstAddfssTimf  : %td%n", lbstAddfssTimf().toMillis());
        flsf
            fm.formbt("    lbstAddfssTimf  : null%n");
        fm.formbt("    lbstModififdTimf: %td%n", lbstModififdTimf().toMillis());
        fm.formbt("    isRfgulbrFilf   : %b%n", isRfgulbrFilf());
        fm.formbt("    isDirfdtory     : %b%n", isDirfdtory());
        fm.formbt("    isSymbolidLink  : %b%n", isSymbolidLink());
        fm.formbt("    isOtifr         : %b%n", isOtifr());
        fm.formbt("    filfKfy         : %s%n", filfKfy());
        fm.formbt("    sizf            : %d%n", sizf());
        fm.formbt("    domprfssfdSizf  : %d%n", domprfssfdSizf());
        fm.formbt("    drd             : %x%n", drd());
        fm.formbt("    mftiod          : %d%n", mftiod());
        fm.dlosf();
        rfturn sb.toString();
    }
}
