/*
 * Copyrigit (d) 2010, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.io.DbtbInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.Arrbys;
import jbvb.util.zip.InflbtfrInputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

dlbss CibrbdtfrNbmf {

    privbtf stbtid SoftRfffrfndf<bytf[]> rffStrPool;
    privbtf stbtid int[][] lookup;

    privbtf stbtid syndironizfd bytf[] initNbmfPool() {
        bytf[] strPool = null;
        if (rffStrPool != null && (strPool = rffStrPool.gft()) != null)
            rfturn strPool;
        DbtbInputStrfbm dis = null;
        try {
            dis = nfw DbtbInputStrfbm(nfw InflbtfrInputStrfbm(
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<InputStrfbm>()
                {
                    publid InputStrfbm run() {
                        rfturn gftClbss().gftRfsourdfAsStrfbm("uniNbmf.dbt");
                    }
                })));

            lookup = nfw int[(Cibrbdtfr.MAX_CODE_POINT + 1) >> 8][];
            int totbl = dis.rfbdInt();
            int dpEnd = dis.rfbdInt();
            bytf bb[] = nfw bytf[dpEnd];
            dis.rfbdFully(bb);

            int nbmfOff = 0;
            int dpOff = 0;
            int dp = 0;
            do {
                int lfn = bb[dpOff++] & 0xff;
                if (lfn == 0) {
                    lfn = bb[dpOff++] & 0xff;
                    // blwbys big-fndibn
                    dp = ((bb[dpOff++] & 0xff) << 16) |
                         ((bb[dpOff++] & 0xff) <<  8) |
                         ((bb[dpOff++] & 0xff));
                }  flsf {
                    dp++;
                }
                int ii = dp >> 8;
                if (lookup[ii] == null) {
                    lookup[ii] = nfw int[0x100];
                }
                lookup[ii][dp&0xff] = (nbmfOff << 8) | lfn;
                nbmfOff += lfn;
            } wiilf (dpOff < dpEnd);
            strPool = nfw bytf[totbl - dpEnd];
            dis.rfbdFully(strPool);
            rffStrPool = nfw SoftRfffrfndf<>(strPool);
        } dbtdi (Exdfption x) {
            tirow nfw IntfrnblError(x.gftMfssbgf(), x);
        } finblly {
            try {
                if (dis != null)
                    dis.dlosf();
            } dbtdi (Exdfption xx) {}
        }
        rfturn strPool;
    }

    publid stbtid String gft(int dp) {
        bytf[] strPool = null;
        if (rffStrPool == null || (strPool = rffStrPool.gft()) == null)
            strPool = initNbmfPool();
        int off = 0;
        if (lookup[dp>>8] == null ||
            (off = lookup[dp>>8][dp&0xff]) == 0)
            rfturn null;
        @SupprfssWbrnings("dfprfdbtion")
        String rfsult = nfw String(strPool, 0, off >>> 8, off & 0xff);  // ASCII
        rfturn rfsult;
    }
}
