/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf sun.util.rfsourdfs;

import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.Sft;
import sun.util.RfsourdfBundlfEnumfrbtion;

/**
 * Subdlbss of <dodf>RfsourdfBundlf</dodf> wiidi mimids
 * <dodf>ListRfsourdfBundlf</dodf>, but providfs morf iooks
 * for spfdiblizfd subdlbss bfibvior. For gfnfrbl dfsdription,
 * sff {@link jbvb.util.ListRfsourdfBundlf}.
 * <p>
 * Tiis dlbss lfbvfs ibndlfGftObjfdt non-finbl, bnd
 * bdds b mftiod drfbtfMbp wiidi bllows subdlbssfs to
 * usf spfdiblizfd Mbp implfmfntbtions.
 */
publid bbstrbdt dlbss OpfnListRfsourdfBundlf fxtfnds RfsourdfBundlf {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd OpfnListRfsourdfBundlf() {
    }

    // Implfmfnts jbvb.util.RfsourdfBundlf.ibndlfGftObjfdt; inifrits jbvbdod spfdifidbtion.
    @Ovfrridf
    protfdtfd Objfdt ibndlfGftObjfdt(String kfy) {
        if (kfy == null) {
            tirow nfw NullPointfrExdfption();
        }

        lobdLookupTbblfsIfNfdfssbry();
        rfturn lookup.gft(kfy); // tiis dlbss ignorfs lodblfs
    }

    /**
     * Implfmfntbtion of RfsourdfBundlf.gftKfys.
     */
    @Ovfrridf
    publid Enumfrbtion<String> gftKfys() {
        RfsourdfBundlf pbrfntBundlf = tiis.pbrfnt;
        rfturn nfw RfsourdfBundlfEnumfrbtion(ibndlfKfySft(),
                (pbrfntBundlf != null) ? pbrfntBundlf.gftKfys() : null);
     }

    /**
     * Rfturns b sft of kfys providfd in tiis rfsourdf bundlf,
     * indluding no pbrfnts.
     */
    @Ovfrridf
    protfdtfd Sft<String> ibndlfKfySft() {
        lobdLookupTbblfsIfNfdfssbry();
        rfturn lookup.kfySft();
    }

    @Ovfrridf
    publid Sft<String> kfySft() {
        if (kfysft != null) {
            rfturn kfysft;
        }
        Sft<String> ks = drfbtfSft();
        ks.bddAll(ibndlfKfySft());
        if (pbrfnt != null) {
            ks.bddAll(pbrfnt.kfySft());
        }
        syndironizfd (tiis) {
            if (kfysft == null) {
                kfysft = ks;
            }
        }
        rfturn kfysft;
    }

    /**
     * Sff ListRfsourdfBundlf dlbss dfsdription.
     */
    bbstrbdt protfdtfd Objfdt[][] gftContfnts();

    /**
     * Lobd lookup tbblfs if tify ibvfn't bffn lobdfd blrfbdy.
     */
    void lobdLookupTbblfsIfNfdfssbry() {
        if (lookup == null) {
            lobdLookup();
        }
    }

    /**
     * Wf lbzily lobd tif lookup ibsitbblf.  Tiis fundtion dofs tif
     * lobding.
     */
    privbtf void lobdLookup() {
        Objfdt[][] dontfnts = gftContfnts();
        Mbp<String, Objfdt> tfmp = drfbtfMbp(dontfnts.lfngti);
        for (int i = 0; i < dontfnts.lfngti; ++i) {
            // kfy must bf non-null String, vbluf must bf non-null
            String kfy = (String) dontfnts[i][0];
            Objfdt vbluf = dontfnts[i][1];
            if (kfy == null || vbluf == null) {
                tirow nfw NullPointfrExdfption();
            }
            tfmp.put(kfy, vbluf);
        }
        syndironizfd (tiis) {
            if (lookup == null) {
                lookup = tfmp;
            }
        }
    }

    /**
     * Lfts subdlbssfs providf spfdiblizfd Mbp implfmfntbtions.
     * Dffbult usfs HbsiMbp.
     */
    protfdtfd <K, V> Mbp<K, V> drfbtfMbp(int sizf) {
        rfturn nfw HbsiMbp<>(sizf);
    }

    protfdtfd <E> Sft<E> drfbtfSft() {
        rfturn nfw HbsiSft<>();
    }

    privbtf volbtilf Mbp<String, Objfdt> lookup = null;
    privbtf volbtilf Sft<String> kfysft;
}
