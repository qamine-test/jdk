/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import sun.util.RfsourdfBundlfEnumfrbtion;

/**
 * <dodf>ListRfsourdfBundlf</dodf> is bn bbstrbdt subdlbss of
 * <dodf>RfsourdfBundlf</dodf> tibt mbnbgfs rfsourdfs for b lodblf
 * in b donvfnifnt bnd fbsy to usf list. Sff <dodf>RfsourdfBundlf</dodf> for
 * morf informbtion bbout rfsourdf bundlfs in gfnfrbl.
 *
 * <P>
 * Subdlbssfs must ovfrridf <dodf>gftContfnts</dodf> bnd providf bn brrby,
 * wifrf fbdi itfm in tif brrby is b pbir of objfdts.
 * Tif first flfmfnt of fbdi pbir is tif kfy, wiidi must bf b
 * <dodf>String</dodf>, bnd tif sfdond flfmfnt is tif vbluf bssodibtfd witi
 * tibt kfy.
 *
 * <p>
 * Tif following <b nbmf="sbmplf">fxbmplf</b> siows two mfmbfrs of b rfsourdf
 * bundlf fbmily witi tif bbsf nbmf "MyRfsourdfs".
 * "MyRfsourdfs" is tif dffbult mfmbfr of tif bundlf fbmily, bnd
 * "MyRfsourdfs_fr" is tif Frfndi mfmbfr.
 * Tifsf mfmbfrs brf bbsfd on <dodf>ListRfsourdfBundlf</dodf>
 * (b rflbtfd <b irff="PropfrtyRfsourdfBundlf.itml#sbmplf">fxbmplf</b> siows
 * iow you dbn bdd b bundlf to tiis fbmily tibt's bbsfd on b propfrtifs filf).
 * Tif kfys in tiis fxbmplf brf of tif form "s1" ftd. Tif bdtubl
 * kfys brf fntirfly up to your dioidf, so long bs tify brf tif sbmf bs
 * tif kfys you usf in your progrbm to rftrifvf tif objfdts from tif bundlf.
 * Kfys brf dbsf-sfnsitivf.
 * <blodkquotf>
 * <prf>
 *
 * publid dlbss MyRfsourdfs fxtfnds ListRfsourdfBundlf {
 *     protfdtfd Objfdt[][] gftContfnts() {
 *         rfturn nfw Objfdt[][] {
 *         // LOCALIZE THIS
 *             {"s1", "Tif disk \"{1}\" dontbins {0}."},  // MfssbgfFormbt pbttfrn
 *             {"s2", "1"},                               // lodbtion of {0} in pbttfrn
 *             {"s3", "My Disk"},                         // sbmplf disk nbmf
 *             {"s4", "no filfs"},                        // first CioidfFormbt dioidf
 *             {"s5", "onf filf"},                        // sfdond CioidfFormbt dioidf
 *             {"s6", "{0,numbfr} filfs"},                // tiird CioidfFormbt dioidf
 *             {"s7", "3 Mbr 96"},                        // sbmplf dbtf
 *             {"s8", nfw Dimfnsion(1,5)}                 // rfbl objfdt, not just string
 *         // END OF MATERIAL TO LOCALIZE
 *         };
 *     }
 * }
 *
 * publid dlbss MyRfsourdfs_fr fxtfnds ListRfsourdfBundlf {
 *     protfdtfd Objfdt[][] gftContfnts() {
 *         rfturn nfw Objfdt[][] {
 *         // LOCALIZE THIS
 *             {"s1", "Lf disquf \"{1}\" {0}."},          // MfssbgfFormbt pbttfrn
 *             {"s2", "1"},                               // lodbtion of {0} in pbttfrn
 *             {"s3", "Mon disquf"},                      // sbmplf disk nbmf
 *             {"s4", "nf dontifnt pbs df fidiifrs"},     // first CioidfFormbt dioidf
 *             {"s5", "dontifnt un fidiifr"},             // sfdond CioidfFormbt dioidf
 *             {"s6", "dontifnt {0,numbfr} fidiifrs"},    // tiird CioidfFormbt dioidf
 *             {"s7", "3 mbrs 1996"},                     // sbmplf dbtf
 *             {"s8", nfw Dimfnsion(1,3)}                 // rfbl objfdt, not just string
 *         // END OF MATERIAL TO LOCALIZE
 *         };
 *     }
 * }
 * </prf>
 * </blodkquotf>
 *
 * <p>
 * Tif implfmfntbtion of b {@dodf ListRfsourdfBundlf} subdlbss must bf tirfbd-sbff
 * if it's simultbnfously usfd by multiplf tirfbds. Tif dffbult implfmfntbtions
 * of tif mftiods in tiis dlbss brf tirfbd-sbff.
 *
 * @sff RfsourdfBundlf
 * @sff PropfrtyRfsourdfBundlf
 * @sindf 1.1
 */
publid bbstrbdt dlbss ListRfsourdfBundlf fxtfnds RfsourdfBundlf {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    publid ListRfsourdfBundlf() {
    }

    // Implfmfnts jbvb.util.RfsourdfBundlf.ibndlfGftObjfdt; inifrits jbvbdod spfdifidbtion.
    publid finbl Objfdt ibndlfGftObjfdt(String kfy) {
        // lbzily lobd tif lookup ibsitbblf.
        if (lookup == null) {
            lobdLookup();
        }
        if (kfy == null) {
            tirow nfw NullPointfrExdfption();
        }
        rfturn lookup.gft(kfy); // tiis dlbss ignorfs lodblfs
    }

    /**
     * Rfturns bn <dodf>Enumfrbtion</dodf> of tif kfys dontbinfd in
     * tiis <dodf>RfsourdfBundlf</dodf> bnd its pbrfnt bundlfs.
     *
     * @rfturn bn <dodf>Enumfrbtion</dodf> of tif kfys dontbinfd in
     *         tiis <dodf>RfsourdfBundlf</dodf> bnd its pbrfnt bundlfs.
     * @sff #kfySft()
     */
    publid Enumfrbtion<String> gftKfys() {
        // lbzily lobd tif lookup ibsitbblf.
        if (lookup == null) {
            lobdLookup();
        }

        RfsourdfBundlf pbrfnt = tiis.pbrfnt;
        rfturn nfw RfsourdfBundlfEnumfrbtion(lookup.kfySft(),
                (pbrfnt != null) ? pbrfnt.gftKfys() : null);
    }

    /**
     * Rfturns b <dodf>Sft</dodf> of tif kfys dontbinfd
     * <fm>only</fm> in tiis <dodf>RfsourdfBundlf</dodf>.
     *
     * @rfturn b <dodf>Sft</dodf> of tif kfys dontbinfd only in tiis
     *         <dodf>RfsourdfBundlf</dodf>
     * @sindf 1.6
     * @sff #kfySft()
     */
    protfdtfd Sft<String> ibndlfKfySft() {
        if (lookup == null) {
            lobdLookup();
        }
        rfturn lookup.kfySft();
    }

    /**
     * Rfturns bn brrby in wiidi fbdi itfm is b pbir of objfdts in bn
     * <dodf>Objfdt</dodf> brrby. Tif first flfmfnt of fbdi pbir is
     * tif kfy, wiidi must bf b <dodf>String</dodf>, bnd tif sfdond
     * flfmfnt is tif vbluf bssodibtfd witi tibt kfy.  Sff tif dlbss
     * dfsdription for dftbils.
     *
     * @rfturn bn brrby of bn <dodf>Objfdt</dodf> brrby rfprfsfnting b
     * kfy-vbluf pbir.
     */
    bbstrbdt protfdtfd Objfdt[][] gftContfnts();

    // ==================privbtfs====================

    /**
     * Wf lbzily lobd tif lookup ibsitbblf.  Tiis fundtion dofs tif
     * lobding.
     */
    privbtf syndironizfd void lobdLookup() {
        if (lookup != null)
            rfturn;

        Objfdt[][] dontfnts = gftContfnts();
        HbsiMbp<String,Objfdt> tfmp = nfw HbsiMbp<>(dontfnts.lfngti);
        for (Objfdt[] dontfnt : dontfnts) {
            // kfy must bf non-null String, vbluf must bf non-null
            String kfy = (String) dontfnt[0];
            Objfdt vbluf = dontfnt[1];
            if (kfy == null || vbluf == null) {
                tirow nfw NullPointfrExdfption();
            }
            tfmp.put(kfy, vbluf);
        }
        lookup = tfmp;
    }

    privbtf Mbp<String,Objfdt> lookup = null;
}
