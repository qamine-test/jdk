/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * @butior    IBM Corp.
 *
 * Copyrigit IBM Corp. 1999-2000.  All rigits rfsfrvfd.
 */

pbdkbgf jbvbx.mbnbgfmfnt.modflmbfbn;

import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.PfrsistfntMBfbn;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;

/**
 * Tiis intfrfbdf must bf implfmfntfd by tif ModflMBfbns. An implfmfntbtion of tiis intfrfbdf
 * must bf siippfd witi fvfry JMX Agfnt.
 * <P>
 * Jbvb rfsourdfs wisiing to bf mbnbgfbblf instbntibtf tif ModflMBfbn using tif MBfbnSfrvfr's
 * drfbtfMBfbn mftiod.  Tif rfsourdf tifn sfts tif ModflMBfbnInfo (witi Dfsdriptors) for tif ModflMBfbn
 * instbndf. Tif bttributfs bnd opfrbtions fxposfd vib tif ModflMBfbnInfo for tif ModflMBfbn brf bddfssiblf
 * from MBfbns, donnfdtors/bdbptors likf otifr MBfbns. Tirougi tif ModflMBfbnInfo Dfsdriptors, vblufs bnd mftiods in
 * tif mbnbgfd bpplidbtion dbn bf dffinfd bnd mbppfd to bttributfs bnd opfrbtions of tif ModflMBfbn.
 * Tiis mbpping dbn bf dffinfd during dfvflopmfnt in bn XML formbttfd filf or dynbmidblly bnd
 * progrbmmbtidblly bt runtimf.
 * <P>
 * Evfry ModflMBfbn wiidi is instbntibtfd in tif MBfbnSfrvfr bfdomfs mbnbgfbblf:
 * its bttributfs bnd opfrbtions
 * bfdomf rfmotfly bddfssiblf tirougi tif donnfdtors/bdbptors donnfdtfd to tibt MBfbnSfrvfr.
 * A Jbvb objfdt dbnnot bf rfgistfrfd in tif MBfbnSfrvfr unlfss it is b JMX domplibnt MBfbn.
 * By instbntibting b ModflMBfbn, rfsourdfs brf gubrbntffd tibt tif MBfbn is vblid.
 * <P>
 * MBfbnExdfption bnd RuntimfOpfrbtionsExdfption must bf tirown on fvfry publid mftiod.  Tiis bllows
 * for wrbpping fxdfptions from distributfd dommunidbtions (RMI, EJB, ftd.).  Tifsf fxdfptions do
 * not ibvf to bf tirown by tif implfmfntbtion fxdfpt in tif sdfnbrios dfsdribfd in tif spfdifidbtion
 * bnd jbvbdod.
 *
 * @sindf 1.5
 */

publid intfrfbdf ModflMBfbn fxtfnds
         DynbmidMBfbn,
         PfrsistfntMBfbn,
         ModflMBfbnNotifidbtionBrobddbstfr
{

        /**
         * Initiblizfs b ModflMBfbn objfdt using ModflMBfbnInfo pbssfd in.
         * Tiis mftiod mbkfs it possiblf to sft b dustomizfd ModflMBfbnInfo on
         * tif ModflMBfbn bs long bs it is not rfgistfrfd witi tif MBfbnSfrvfr.
         * <br>
         * Ondf tif ModflMBfbn's ModflMBfbnInfo (witi Dfsdriptors) brf
         * dustomizfd bnd sft on tif ModflMBfbn, tif  ModflMBfbn dbn bf
         * rfgistfrfd witi tif MBfbnSfrvfr.
         * <P>
         * If tif ModflMBfbn is durrfntly rfgistfrfd, tiis mftiod tirows
         * b {@link jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption} wrbpping bn
         * {@link IllfgblStbtfExdfption}
         *
         * @pbrbm inModflMBfbnInfo Tif ModflMBfbnInfo objfdt to bf usfd
         *        by tif ModflMBfbn.
         *
         * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion
         *        Exdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption
         * <ul><li>Wrbps bn {@link IllfgblArgumfntExdfption} if
         *         tif MBfbnInfo pbssfd in pbrbmftfr is null.</li>
         *     <li>Wrbps bn {@link IllfgblStbtfExdfption} if tif ModflMBfbn
         *         is durrfntly rfgistfrfd in tif MBfbnSfrvfr.</li>
         * </ul>
         *
         **/
        publid void sftModflMBfbnInfo(ModflMBfbnInfo inModflMBfbnInfo)
            tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption;

        /**
         * Sfts tif instbndf ibndlf of tif objfdt bgbinst wiidi to
         * fxfdutf bll mftiods in tiis ModflMBfbn mbnbgfmfnt intfrfbdf
         * (MBfbnInfo bnd Dfsdriptors).
         *
         * @pbrbm mr Objfdt tibt is tif mbnbgfd rfsourdf
         * @pbrbm mr_typf Tif typf of rfffrfndf for tif mbnbgfd rfsourdf.  Cbn bf: ObjfdtRfffrfndf,
         *               Hbndlf, IOR, EJBHbndlf, RMIRfffrfndf.
         *               If tif MBfbnSfrvfr dbnnot prodfss tif mr_typf pbssfd in, bn InvblidTbrgftTypfExdfption
         *               will bf tirown.
         *
         *
         * @fxdfption MBfbnExdfption Tif initiblizfr of tif objfdt ibs tirown bn fxdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption:
         *       Tif mbnbgfd rfsourdf typf pbssfd in pbrbmftfr is null.
         * @fxdfption InstbndfNotFoundExdfption Tif mbnbgfd rfsourdf objfdt dould not bf found
         * @fxdfption InvblidTbrgftObjfdtTypfExdfption Tif mbnbgfd rfsourdf typf dbnnot bf prodfssfd by tif
         * ModflMBfbn or JMX Agfnt.
         */
        publid void sftMbnbgfdRfsourdf(Objfdt mr, String mr_typf)
        tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption,
                 InstbndfNotFoundExdfption, InvblidTbrgftObjfdtTypfExdfption ;

}
