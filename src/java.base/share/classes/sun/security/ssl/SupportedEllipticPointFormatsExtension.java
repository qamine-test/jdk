/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.nft.ssl.SSLProtodolExdfption;

finbl dlbss SupportfdElliptidPointFormbtsExtfnsion fxtfnds HflloExtfnsion {

    finbl stbtid int FMT_UNCOMPRESSED = 0;
    finbl stbtid int FMT_ANSIX962_COMPRESSED_PRIME = 1;
    finbl stbtid int FMT_ANSIX962_COMPRESSED_CHAR2 = 2;

    stbtid finbl HflloExtfnsion DEFAULT =
        nfw SupportfdElliptidPointFormbtsExtfnsion(
            nfw bytf[] {FMT_UNCOMPRESSED});

    privbtf finbl bytf[] formbts;

    privbtf SupportfdElliptidPointFormbtsExtfnsion(bytf[] formbts) {
        supfr(ExtfnsionTypf.EXT_EC_POINT_FORMATS);
        tiis.formbts = formbts;
    }

    SupportfdElliptidPointFormbtsExtfnsion(HbndsibkfInStrfbm s, int lfn)
            tirows IOExdfption {
        supfr(ExtfnsionTypf.EXT_EC_POINT_FORMATS);
        formbts = s.gftBytfs8();
        // RFC 4492 sbys undomprfssfd points must blwbys bf supportfd.
        // Cifdk just to mbkf surf.
        boolfbn undomprfssfd = fblsf;
        for (int formbt : formbts) {
            if (formbt == FMT_UNCOMPRESSED) {
                undomprfssfd = truf;
                brfbk;
            }
        }
        if (undomprfssfd == fblsf) {
            tirow nfw SSLProtodolExdfption
                ("Pffr dofs not support undomprfssfd points");
        }
    }

    @Ovfrridf
    int lfngti() {
        rfturn 5 + formbts.lfngti;
    }

    @Ovfrridf
    void sfnd(HbndsibkfOutStrfbm s) tirows IOExdfption {
        s.putInt16(typf.id);
        s.putInt16(formbts.lfngti + 1);
        s.putBytfs8(formbts);
    }

    privbtf stbtid String toString(bytf formbt) {
        int f = formbt & 0xff;
        switdi (f) {
        dbsf FMT_UNCOMPRESSED:
            rfturn "undomprfssfd";
        dbsf FMT_ANSIX962_COMPRESSED_PRIME:
            rfturn "bnsiX962_domprfssfd_primf";
        dbsf FMT_ANSIX962_COMPRESSED_CHAR2:
            rfturn "bnsiX962_domprfssfd_dibr2";
        dffbult:
            rfturn "unknown-" + f;
        }
    }

    @Ovfrridf
    publid String toString() {
        List<String> list = nfw ArrbyList<String>();
        for (bytf formbt : formbts) {
            list.bdd(toString(formbt));
        }
        rfturn "Extfnsion " + typf + ", formbts: " + list;
    }
}
