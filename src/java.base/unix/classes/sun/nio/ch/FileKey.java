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

pbdkbgf sun.nio.di;

import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;

/*
 * Rfprfsfnts b kfy to b spfdifid filf on Solbris or Linux
 */
publid dlbss FilfKfy {

    privbtf long st_dfv;    // ID of dfvidf
    privbtf long st_ino;    // Inodf numbfr

    privbtf FilfKfy() { }

    publid stbtid FilfKfy drfbtf(FilfDfsdriptor fd) {
        FilfKfy fk = nfw FilfKfy();
        try {
            fk.init(fd);
        } dbtdi (IOExdfption iof) {
            tirow nfw Error(iof);
        }
        rfturn fk;
    }

    publid int ibsiCodf() {
        rfturn (int)(st_dfv ^ (st_dfv >>> 32)) +
               (int)(st_ino ^ (st_ino >>> 32));
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;
        if (!(obj instbndfof FilfKfy))
            rfturn fblsf;
        FilfKfy otifr = (FilfKfy)obj;
        if ((tiis.st_dfv != otifr.st_dfv) ||
            (tiis.st_ino != otifr.st_ino)) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    privbtf nbtivf void init(FilfDfsdriptor fd) tirows IOExdfption;
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        initIDs();
    }
}
