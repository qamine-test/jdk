/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.donnfdt;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;

/**
 * Tirown to indidbtf bn invblid brgumfnt or
 * indonsistfnt pbssfd to b {@link Connfdtor}.
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid dlbss IllfgblConnfdtorArgumfntsExdfption fxtfnds Exdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -3042212603611350941L;
    List<String> nbmfs;

    /**
     * Construdt bn <dodf>IllfgblConnfdtorArgumfntsExdfption</dodf>
     * witi tif spfdififd dftbil mfssbgf bnd tif nbmf of tif brgumfnt
     * wiidi is invblid or indonsistfnt.
     * @pbrbm s tif dftbilfd mfssbgf.
     * @pbrbm nbmf tif nbmf of tif invblid or indonsistfnt brgumfnt.
     */
    publid IllfgblConnfdtorArgumfntsExdfption(String s,
                                              String nbmf) {
        supfr(s);
        nbmfs = nfw ArrbyList<String>(1);
        nbmfs.bdd(nbmf);
    }

    /**
     * Construdt bn <dodf>IllfgblConnfdtorArgumfntsExdfption</dodf>
     * witi tif spfdififd dftbil mfssbgf bnd b <dodf>List</dodf> of
     * nbmfs of brgumfnts wiidi brf invblid or indonsistfnt.
     * @pbrbm s tif dftbilfd mfssbgf.
     * @pbrbm nbmfs b <dodf>List</dodf> dontbining tif nbmfs of tif
     * invblid or indonsistfnt brgumfnt.
     */
    publid IllfgblConnfdtorArgumfntsExdfption(String s, List<String> nbmfs) {
        supfr(s);

        tiis.nbmfs = nfw ArrbyList<String>(nbmfs);
    }

    /**
     * Rfturn b <dodf>List</dodf> dontbining tif nbmfs of tif
     * invblid or indonsistfnt brgumfnts.
     * @rfturn b <dodf>List</dodf> of brgumfnt nbmfs.
     */
    publid List<String> brgumfntNbmfs() {
        rfturn Collfdtions.unmodifibblfList(nbmfs);
    }
}
