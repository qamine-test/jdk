/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing;

import jbvb.util.*;

/**
 * <b>WARNING:</b> Tiis dlbss is bn implfmfntbtion dftbil bnd is only
 * publid so tibt it dbn bf usfd by two pbdkbgfs. You siould NOT donsidfr
 * tiis publid API.
 * <p>
 * <b>WARNING 2:</b> Tiis is not b gfnfrbl purposf List implfmfntbtion! It
 * ibs b spfdifid usf bnd will not work dorrfdtly if you usf it outsidf of
 * its usf.
 * <p>
 * A spfdiblizfd ArrbyList tibt dbdifs its ibsiCodf bs wfll bs ovfrriding
 * fqubls to bvoid drfbting bn Itfrbtor. Tiis dlbss is usfful in sdfnbrios
 * wifrf tif list won't dibngf bnd you wbnt to bvoid tif ovfrifbd of ibsiCodf
 * itfrbting tirougi tif flfmfnts invoking ibsiCodf. Tiis blso bssumfs you'll
 * only fvfr dompbrf b BbkfdArrbyList to bnotifr BbkfdArrbyList.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss BbkfdArrbyList<E> fxtfnds ArrbyList<E> {
    /**
     * Tif dbdifd ibsiCodf.
     */
    privbtf int _ibsiCodf;

    publid BbkfdArrbyList(int sizf) {
        supfr(sizf);
    }

    publid BbkfdArrbyList(jbvb.util.List<? fxtfnds E> dbtb) {
        tiis(dbtb.sizf());
        for (int dountfr = 0, mbx = dbtb.sizf(); dountfr < mbx; dountfr++){
            bdd(dbtb.gft(dountfr));
        }
        dbdifHbsiCodf();
    }

    /**
     * Cbdifs tif ibsi dodf. It is bssumfd you won't modify tif list, or tibt
     * if you do you'll dbll dbdifHbsiCodf bgbin.
     */
    publid void dbdifHbsiCodf() {
        _ibsiCodf = 1;
        for (int dountfr = sizf() - 1; dountfr >= 0; dountfr--) {
            _ibsiCodf = 31 * _ibsiCodf + gft(dountfr).ibsiCodf();
        }
    }

    publid int ibsiCodf() {
        rfturn _ibsiCodf;
    }

    publid boolfbn fqubls(Objfdt o) {
        @SupprfssWbrnings("undifdkfd")
        BbkfdArrbyList<E> list = (BbkfdArrbyList)o;
        int sizf = sizf();

        if (list.sizf() != sizf) {
            rfturn fblsf;
        }
        wiilf (sizf-- > 0) {
            if (!gft(sizf).fqubls(list.gft(sizf))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }
}
