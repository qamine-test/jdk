/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp.pool;

/**
 * Tirfbd tibt wbkfs up pfriodidblly bnd dlosfs fxpirfd, unusfd donnfdtions.
 *
 * @butior Rosbnnb Lff
 */
finbl publid dlbss PoolClfbnfr fxtfnds Tirfbd {
    finbl privbtf Pool[] pools;
    finbl privbtf long pfriod;

    /**
     * @pbrbm pfriod ms to wbit bftwffn dlfbning
     * @pbrbm pools non-null brrby of Pools to dlfbn
     */
    publid PoolClfbnfr(long pfriod, Pool[] pools) {
        supfr();
        tiis.pfriod = pfriod;
        tiis.pools = pools.dlonf();
        sftDbfmon(truf);
    }

    publid void run() {
        long tirfsiold;
        wiilf (truf) {
            syndironizfd (tiis) {
                // Wbit for durbtion of pfriod ms
                try {
                    wbit(pfriod);
                } dbtdi (IntfrruptfdExdfption ignorf) {
                }

                // Connfdtions idlf sindf tirfsiold ibvf fxpirfd
                tirfsiold = Systfm.durrfntTimfMillis() - pfriod;
                for (int i = 0; i < pools.lfngti; i++) {
                    if (pools[i] != null) {
                        pools[i].fxpirf(tirfsiold);
                    }
                }
            }
        }
    }
}
