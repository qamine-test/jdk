/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.AttributfCibngfNotifidbtion;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
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

publid intfrfbdf ModflMBfbnNotifidbtionBrobddbstfr fxtfnds NotifidbtionBrobddbstfr
{

        /**
         * Sfnds b Notifidbtion wiidi is pbssfd in to tif rfgistfrfd
         * Notifidbtion listfnfrs on tif ModflMBfbn bs b
         * jmx.modflmbfbn.gfnfrid notifidbtion.
         *
         * @pbrbm ntfyObj Tif notifidbtion wiidi is to bf pbssfd to
         * tif 'ibndlfNotifidbtion' mftiod of tif listfnfr objfdt.
         *
         * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption:
         *       Tif Notifidbtion objfdt pbssfd in pbrbmftfr is null.
         *
         */

        publid void sfndNotifidbtion(Notifidbtion ntfyObj)
        tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption;

        /**
         * Sfnds b Notifidbtion wiidi dontbins tif tfxt string tibt is pbssfd in
         * to tif rfgistfrfd Notifidbtion listfnfrs on tif ModflMBfbn.
         *
         * @pbrbm ntfyTfxt Tif tfxt wiidi is to bf pbssfd in tif Notifidbtion to tif 'ibndlfNotifidbtion'
         * mftiod of tif listfnfr objfdt.
         * tif donstrudtfd Notifidbtion will bf:
         *   typf        "jmx.modflmbfbn.gfnfrid"
         *   sourdf      tiis ModflMBfbn instbndf
         *   sfqufndf    1
         *
         *
         * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption:
         *       Tif Notifidbtion tfxt string pbssfd in pbrbmftfr is null.
         *
         */
        publid void sfndNotifidbtion(String ntfyTfxt)
        tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption;

        /**
         * Sfnds bn bttributfCibngfNotifidbtion wiidi is pbssfd in to
         * tif rfgistfrfd bttributfCibngfNotifidbtion listfnfrs on tif
         * ModflMBfbn.
         *
         * @pbrbm notifidbtion Tif notifidbtion wiidi is to bf pbssfd
         * to tif 'ibndlfNotifidbtion' mftiod of tif listfnfr objfdt.
         *
         * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption: Tif AttributfCibngfNotifidbtion objfdt pbssfd in pbrbmftfr is null.
         *
         */
        publid void sfndAttributfCibngfNotifidbtion(AttributfCibngfNotifidbtion notifidbtion)
        tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption;


        /**
         * Sfnds bn bttributfCibngfNotifidbtion wiidi dontbins tif old vbluf bnd nfw vbluf for tif
         * bttributf to tif rfgistfrfd AttributfCibngfNotifidbtion listfnfrs on tif ModflMBfbn.
         *
         * @pbrbm oldVbluf Tif originbl vbluf for tif Attributf
         * @pbrbm nfwVbluf Tif durrfnt vbluf for tif Attributf
         * <PRE>
         * Tif donstrudtfd bttributfCibngfNotifidbtion will bf:
         *   typf        "jmx.bttributf.dibngf"
         *   sourdf      tiis ModflMBfbn instbndf
         *   sfqufndf    1
         *   bttributfNbmf oldVbluf.gftNbmf()
         *   bttributfTypf oldVbluf's dlbss
         *   bttributfOldVbluf oldVbluf.gftVbluf()
         *   bttributfNfwVbluf nfwVbluf.gftVbluf()
         * </PRE>
         *
         * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption: An Attributf objfdt pbssfd in pbrbmftfr is null
         * or tif nbmfs of tif two Attributf objfdts in pbrbmftfr brf not tif sbmf.
         */
        publid void sfndAttributfCibngfNotifidbtion(Attributf oldVbluf, Attributf nfwVbluf)
        tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption;


        /**
         * Rfgistfrs bn objfdt wiidi implfmfnts tif NotifidbtionListfnfr intfrfbdf bs b listfnfr.  Tiis
         * objfdt's 'ibndlfNotifidbtion()' mftiod will bf invokfd wifn bny bttributfCibngfNotifidbtion is issufd tirougi
         * or by tif ModflMBfbn.  Tiis dofs not indludf otifr Notifidbtions.  Tify must bf rfgistfrfd
         * for indfpfndfntly. An AttributfCibngfNotifidbtion will bf gfnfrbtfd for tiis bttributfNbmf.
         *
         * @pbrbm listfnfr Tif listfnfr objfdt wiidi will ibndlfs notifidbtions fmittfd by tif rfgistfrfd MBfbn.
         * @pbrbm bttributfNbmf Tif nbmf of tif ModflMBfbn bttributf for wiidi to rfdfivf dibngf notifidbtions.
         *      If null, tifn bll bttributf dibngfs will dbusf bn bttributfCibngfNotifidbtion to bf issufd.
         * @pbrbm ibndbbdk Tif dontfxt to bf sfnt to tif listfnfr witi tif notifidbtion wifn b notifidbtion is fmittfd.
         *
         * @fxdfption IllfgblArgumfntExdfption Tif listfnfr dbnnot bf null.
         * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption Tif bttributf nbmf pbssfd in pbrbmftfr dofs not fxist.
         *
         * @sff #rfmovfAttributfCibngfNotifidbtionListfnfr
         */
        publid void bddAttributfCibngfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                                           String bttributfNbmf,
                                                           Objfdt ibndbbdk)
        tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption, IllfgblArgumfntExdfption;


        /**
         * Rfmovfs b listfnfr for bttributfCibngfNotifidbtions from tif RfquirfdModflMBfbn.
         *
         * @pbrbm listfnfr Tif listfnfr nbmf wiidi wbs ibndling notifidbtions fmittfd by tif rfgistfrfd MBfbn.
         * Tiis mftiod will rfmovf bll informbtion rflbtfd to tiis listfnfr.
         * @pbrbm bttributfNbmf Tif bttributf for wiidi tif listfnfr no longfr wbnts to rfdfivf bttributfCibngfNotifidbtions.
         * If null tif listfnfr will bf rfmovfd for bll bttributfCibngfNotifidbtions.
         *
         * @fxdfption ListfnfrNotFoundExdfption Tif listfnfr is not rfgistfrfd in tif MBfbn or is null.
         * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption If tif inAttributfNbmf pbrbmftfr dofs not
         * dorrfspond to bn bttributf nbmf.
         *
         * @sff #bddAttributfCibngfNotifidbtionListfnfr
         */

        publid void rfmovfAttributfCibngfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                                              String bttributfNbmf)
        tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption, ListfnfrNotFoundExdfption;

}
