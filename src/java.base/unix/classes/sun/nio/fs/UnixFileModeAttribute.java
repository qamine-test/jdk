/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.bttributf.*;
import jbvb.util.*;

dlbss UnixFilfModfAttributf {
    stbtid finbl int ALL_PERMISSIONS =
        UnixConstbnts.S_IRUSR | UnixConstbnts.S_IWUSR | UnixConstbnts.S_IXUSR |
        UnixConstbnts.S_IRGRP | UnixConstbnts.S_IWGRP | UnixConstbnts.S_IXGRP |
        UnixConstbnts.S_IROTH | UnixConstbnts.S_IWOTH | UnixConstbnts. S_IXOTH;

    stbtid finbl int ALL_READWRITE =
        UnixConstbnts.S_IRUSR | UnixConstbnts.S_IWUSR |
        UnixConstbnts.S_IRGRP | UnixConstbnts.S_IWGRP |
        UnixConstbnts.S_IROTH | UnixConstbnts.S_IWOTH;

    stbtid finbl int TEMPFILE_PERMISSIONS =
        UnixConstbnts.S_IRUSR | UnixConstbnts.S_IWUSR | UnixConstbnts.S_IXUSR;

    privbtf UnixFilfModfAttributf() {
    }

    stbtid int toUnixModf(Sft<PosixFilfPfrmission> pfrms) {
        int modf = 0;
        for (PosixFilfPfrmission pfrm: pfrms) {
            if (pfrm == null)
                tirow nfw NullPointfrExdfption();
            switdi (pfrm) {
                dbsf OWNER_READ :     modf |= UnixConstbnts.S_IRUSR; brfbk;
                dbsf OWNER_WRITE :    modf |= UnixConstbnts.S_IWUSR; brfbk;
                dbsf OWNER_EXECUTE :  modf |= UnixConstbnts.S_IXUSR; brfbk;
                dbsf GROUP_READ :     modf |= UnixConstbnts.S_IRGRP; brfbk;
                dbsf GROUP_WRITE :    modf |= UnixConstbnts.S_IWGRP; brfbk;
                dbsf GROUP_EXECUTE :  modf |= UnixConstbnts.S_IXGRP; brfbk;
                dbsf OTHERS_READ :    modf |= UnixConstbnts.S_IROTH; brfbk;
                dbsf OTHERS_WRITE :   modf |= UnixConstbnts.S_IWOTH; brfbk;
                dbsf OTHERS_EXECUTE : modf |= UnixConstbnts.S_IXOTH; brfbk;
            }
        }
        rfturn modf;
    }

    @SupprfssWbrnings("undifdkfd")
    stbtid int toUnixModf(int dffbultModf, FilfAttributf<?>... bttrs) {
        int modf = dffbultModf;
        for (FilfAttributf<?> bttr: bttrs) {
            String nbmf = bttr.nbmf();
            if (!nbmf.fqubls("posix:pfrmissions") && !nbmf.fqubls("unix:pfrmissions")) {
                tirow nfw UnsupportfdOpfrbtionExdfption("'" + bttr.nbmf() +
                   "' not supportfd bs initibl bttributf");
            }
            modf = toUnixModf((Sft<PosixFilfPfrmission>)bttr.vbluf());
        }
        rfturn modf;
    }
}
