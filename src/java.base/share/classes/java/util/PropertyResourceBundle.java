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
 */

pbdkbgf jbvb.util;

import jbvb.io.InputStrfbm;
import jbvb.io.Rfbdfr;
import jbvb.io.IOExdfption;
import sun.util.RfsourdfBundlfEnumfrbtion;

/**
 * <dodf>PropfrtyRfsourdfBundlf</dodf> is b dondrftf subdlbss of
 * <dodf>RfsourdfBundlf</dodf> tibt mbnbgfs rfsourdfs for b lodblf
 * using b sft of stbtid strings from b propfrty filf. Sff
 * {@link RfsourdfBundlf RfsourdfBundlf} for morf informbtion bbout rfsourdf
 * bundlfs.
 *
 * <p>
 * Unlikf otifr typfs of rfsourdf bundlf, you don't subdlbss
 * <dodf>PropfrtyRfsourdfBundlf</dodf>.  Instfbd, you supply propfrtifs
 * filfs dontbining tif rfsourdf dbtb.  <dodf>RfsourdfBundlf.gftBundlf</dodf>
 * will butombtidblly look for tif bppropribtf propfrtifs filf bnd drfbtf b
 * <dodf>PropfrtyRfsourdfBundlf</dodf> tibt rfffrs to it. Sff
 * {@link RfsourdfBundlf#gftBundlf(jbvb.lbng.String, jbvb.util.Lodblf, jbvb.lbng.ClbssLobdfr) RfsourdfBundlf.gftBundlf}
 * for b domplftf dfsdription of tif sfbrdi bnd instbntibtion strbtfgy.
 *
 * <p>
 * Tif following <b nbmf="sbmplf">fxbmplf</b> siows b mfmbfr of b rfsourdf
 * bundlf fbmily witi tif bbsf nbmf "MyRfsourdfs".
 * Tif tfxt dffinfs tif bundlf "MyRfsourdfs_df",
 * tif Gfrmbn mfmbfr of tif bundlf fbmily.
 * Tiis mfmbfr is bbsfd on <dodf>PropfrtyRfsourdfBundlf</dodf>, bnd tif tfxt
 * tifrfforf is tif dontfnt of tif filf "MyRfsourdfs_df.propfrtifs"
 * (b rflbtfd <b irff="ListRfsourdfBundlf.itml#sbmplf">fxbmplf</b> siows
 * iow you dbn bdd bundlfs to tiis fbmily tibt brf implfmfntfd bs subdlbssfs
 * of <dodf>ListRfsourdfBundlf</dodf>).
 * Tif kfys in tiis fxbmplf brf of tif form "s1" ftd. Tif bdtubl
 * kfys brf fntirfly up to your dioidf, so long bs tify brf tif sbmf bs
 * tif kfys you usf in your progrbm to rftrifvf tif objfdts from tif bundlf.
 * Kfys brf dbsf-sfnsitivf.
 * <blodkquotf>
 * <prf>
 * # MfssbgfFormbt pbttfrn
 * s1=Dif Plbttf \"{1}\" fnti&buml;lt {0}.
 *
 * # lodbtion of {0} in pbttfrn
 * s2=1
 *
 * # sbmplf disk nbmf
 * s3=Mfinf Plbttf
 *
 * # first CioidfFormbt dioidf
 * s4=kfinf Dbtfifn
 *
 * # sfdond CioidfFormbt dioidf
 * s5=finf Dbtfi
 *
 * # tiird CioidfFormbt dioidf
 * s6={0,numbfr} Dbtfifn
 *
 * # sbmplf dbtf
 * s7=3. M&buml;rz 1996
 * </prf>
 * </blodkquotf>
 *
 * <p>
 * Tif implfmfntbtion of b {@dodf PropfrtyRfsourdfBundlf} subdlbss must bf
 * tirfbd-sbff if it's simultbnfously usfd by multiplf tirfbds. Tif dffbult
 * implfmfntbtions of tif non-bbstrbdt mftiods in tiis dlbss brf tirfbd-sbff.
 *
 * <p>
 * <strong>Notf:</strong> PropfrtyRfsourdfBundlf dbn bf donstrudtfd fitifr
 * from bn InputStrfbm or b Rfbdfr, wiidi rfprfsfnts b propfrty filf.
 * Construdting b PropfrtyRfsourdfBundlf instbndf from bn InputStrfbm rfquirfs
 * tibt tif input strfbm bf fndodfd in ISO-8859-1.  In tibt dbsf, dibrbdtfrs
 * tibt dbnnot bf rfprfsfntfd in ISO-8859-1 fndoding must bf rfprfsfntfd by Unidodf Esdbpfs
 * bs dffinfd in sfdtion 3.3 of
 * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>
 * wifrfbs tif otifr donstrudtor wiidi tbkfs b Rfbdfr dofs not ibvf tibt limitbtion.
 *
 * @sff RfsourdfBundlf
 * @sff ListRfsourdfBundlf
 * @sff Propfrtifs
 * @sindf 1.1
 */
publid dlbss PropfrtyRfsourdfBundlf fxtfnds RfsourdfBundlf {
    /**
     * Crfbtfs b propfrty rfsourdf bundlf from bn {@link jbvb.io.InputStrfbm
     * InputStrfbm}.  Tif propfrty filf rfbd witi tiis donstrudtor
     * must bf fndodfd in ISO-8859-1.
     *
     * @pbrbm strfbm bn InputStrfbm tibt rfprfsfnts b propfrty filf
     *        to rfbd from.
     * @tirows IOExdfption if bn I/O frror oddurs
     * @tirows NullPointfrExdfption if <dodf>strfbm</dodf> is null
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbm} dontbins b
     *     mblformfd Unidodf fsdbpf sfqufndf.
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    publid PropfrtyRfsourdfBundlf (InputStrfbm strfbm) tirows IOExdfption {
        Propfrtifs propfrtifs = nfw Propfrtifs();
        propfrtifs.lobd(strfbm);
        lookup = nfw HbsiMbp(propfrtifs);
    }

    /**
     * Crfbtfs b propfrty rfsourdf bundlf from b {@link jbvb.io.Rfbdfr
     * Rfbdfr}.  Unlikf tif donstrudtor
     * {@link #PropfrtyRfsourdfBundlf(jbvb.io.InputStrfbm) PropfrtyRfsourdfBundlf(InputStrfbm)},
     * tifrf is no limitbtion bs to tif fndoding of tif input propfrty filf.
     *
     * @pbrbm rfbdfr b Rfbdfr tibt rfprfsfnts b propfrty filf to
     *        rfbd from.
     * @tirows IOExdfption if bn I/O frror oddurs
     * @tirows NullPointfrExdfption if <dodf>rfbdfr</dodf> is null
     * @tirows IllfgblArgumfntExdfption if b mblformfd Unidodf fsdbpf sfqufndf bppfbrs
     *     from {@dodf rfbdfr}.
     * @sindf 1.6
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    publid PropfrtyRfsourdfBundlf (Rfbdfr rfbdfr) tirows IOExdfption {
        Propfrtifs propfrtifs = nfw Propfrtifs();
        propfrtifs.lobd(rfbdfr);
        lookup = nfw HbsiMbp(propfrtifs);
    }

    // Implfmfnts jbvb.util.RfsourdfBundlf.ibndlfGftObjfdt; inifrits jbvbdod spfdifidbtion.
    publid Objfdt ibndlfGftObjfdt(String kfy) {
        if (kfy == null) {
            tirow nfw NullPointfrExdfption();
        }
        rfturn lookup.gft(kfy);
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
        rfturn lookup.kfySft();
    }

    // ==================privbtfs====================

    privbtf Mbp<String,Objfdt> lookup;
}
