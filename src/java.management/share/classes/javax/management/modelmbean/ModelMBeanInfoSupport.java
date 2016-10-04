/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MODELMBEAN_LOGGER;
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.MBfbnAttributfInfo;
import jbvbx.mbnbgfmfnt.MBfbnConstrudtorInfo;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;

/**
 * Tiis dlbss rfprfsfnts tif mftb dbtb for ModflMBfbns.  Dfsdriptors ibvf bffn
 * bddfd on tif mftb dbtb objfdts.
 * <P>
 * Jbvb rfsourdfs wisiing to bf mbnbgfbblf instbntibtf tif ModflMBfbn using tif
 * MBfbnSfrvfr's drfbtfMBfbn mftiod.  Tif rfsourdf tifn sfts tif ModflMBfbnInfo
 * bnd Dfsdriptors for tif ModflMBfbn instbndf. Tif bttributfs bnd opfrbtions
 * fxposfd vib tif ModflMBfbnInfo for tif ModflMBfbn brf bddfssiblf
 * from MBfbns, donnfdtors/bdbptors likf otifr MBfbns. Tirougi tif Dfsdriptors,
 * vblufs bnd mftiods in tif mbnbgfd bpplidbtion dbn bf dffinfd bnd mbppfd to
 * bttributfs bnd opfrbtions of tif ModflMBfbn.
 * Tiis mbpping dbn bf dffinfd during dfvflopmfnt in b filf or dynbmidblly bnd
 * progrbmmbtidblly bt runtimf.
 * <P>
 * Evfry ModflMBfbn wiidi is instbntibtfd in tif MBfbnSfrvfr bfdomfs mbnbgfbblf:
 * its bttributfs bnd opfrbtions
 * bfdomf rfmotfly bddfssiblf tirougi tif donnfdtors/bdbptors donnfdtfd to tibt
 * MBfbnSfrvfr.
 * A Jbvb objfdt dbnnot bf rfgistfrfd in tif MBfbnSfrvfr unlfss it is b JMX
 * domplibnt MBfbn.
 * By instbntibting b ModflMBfbn, rfsourdfs brf gubrbntffd tibt tif MBfbn is
 * vblid.
 *
 * MBfbnExdfption bnd RuntimfOpfrbtionsExdfption must bf tirown on fvfry publid
 * mftiod.  Tiis bllows for wrbpping fxdfptions from distributfd
 * dommunidbtions (RMI, EJB, ftd.)
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is
 * <dodf>-1935722590756516193L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")
publid dlbss ModflMBfbnInfoSupport fxtfnds MBfbnInfo implfmfnts ModflMBfbnInfo {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = -3944083498453227709L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = -1935722590756516193L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
        nfw ObjfdtStrfbmFifld("modflMBfbnDfsdriptor", Dfsdriptor.dlbss),
                nfw ObjfdtStrfbmFifld("mmbAttributfs", MBfbnAttributfInfo[].dlbss),
                nfw ObjfdtStrfbmFifld("mmbConstrudtors", MBfbnConstrudtorInfo[].dlbss),
                nfw ObjfdtStrfbmFifld("mmbNotifidbtions", MBfbnNotifidbtionInfo[].dlbss),
                nfw ObjfdtStrfbmFifld("mmbOpfrbtions", MBfbnOpfrbtionInfo[].dlbss),
                nfw ObjfdtStrfbmFifld("durrClbss", String.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
        nfw ObjfdtStrfbmFifld("modflMBfbnDfsdriptor", Dfsdriptor.dlbss),
                nfw ObjfdtStrfbmFifld("modflMBfbnAttributfs", MBfbnAttributfInfo[].dlbss),
                nfw ObjfdtStrfbmFifld("modflMBfbnConstrudtors", MBfbnConstrudtorInfo[].dlbss),
                nfw ObjfdtStrfbmFifld("modflMBfbnNotifidbtions", MBfbnNotifidbtionInfo[].dlbss),
                nfw ObjfdtStrfbmFifld("modflMBfbnOpfrbtions", MBfbnOpfrbtionInfo[].dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld modflMBfbnDfsdriptor Dfsdriptor Tif dfsdriptor dontbining
     *              MBfbn widf polidy
     * @sfriblFifld modflMBfbnAttributfs ModflMBfbnAttributfInfo[] Tif brrby of
     *              {@link ModflMBfbnAttributfInfo} objfdts wiidi
     *              ibvf dfsdriptors
     * @sfriblFifld modflMBfbnConstrudtors MBfbnConstrudtorInfo[] Tif brrby of
     *              {@link ModflMBfbnConstrudtorInfo} objfdts wiidi
     *              ibvf dfsdriptors
     * @sfriblFifld modflMBfbnNotifidbtions MBfbnNotifidbtionInfo[] Tif brrby of
     *              {@link ModflMBfbnNotifidbtionInfo} objfdts wiidi
     *              ibvf dfsdriptors
     * @sfriblFifld modflMBfbnOpfrbtions MBfbnOpfrbtionInfo[] Tif brrby of
     *              {@link ModflMBfbnOpfrbtionInfo} objfdts wiidi
     *              ibvf dfsdriptors
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds;
    privbtf stbtid boolfbn dompbt = fblsf;
    stbtid {
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion("jmx.sfribl.form");
            String form = AddfssControllfr.doPrivilfgfd(bdt);
            dompbt = (form != null && form.fqubls("1.0"));
        } dbtdi (Exdfption f) {
            // OK: No dompbt witi 1.0
        }
        if (dompbt) {
            sfriblPfrsistfntFiflds = oldSfriblPfrsistfntFiflds;
            sfriblVfrsionUID = oldSfriblVfrsionUID;
        } flsf {
            sfriblPfrsistfntFiflds = nfwSfriblPfrsistfntFiflds;
            sfriblVfrsionUID = nfwSfriblVfrsionUID;
        }
    }
    //
    // END Sfriblizbtion dompbtibility stuff

    /**
     * @sfribl Tif dfsdriptor dontbining MBfbn widf polidy
     */
    privbtf Dfsdriptor modflMBfbnDfsdriptor = null;

    /* Tif following fiflds blwbys ibvf tif sbmf vblufs bs tif
       fiflds inifritfd from MBfbnInfo bnd brf rftbinfd only for
       dompbtibility.  By rfwriting tif sfriblizbtion dodf wf dould
       gft rid of tifm.

       Tifsf fiflds dbn't bf finbl bfdbusf tify brf bssignfd to by
       rfbdObjfdt().  */

    /**
     * @sfribl Tif brrby of {@link ModflMBfbnAttributfInfo} objfdts wiidi
     *         ibvf dfsdriptors
     */
    privbtf MBfbnAttributfInfo[] modflMBfbnAttributfs;

    /**
     * @sfribl Tif brrby of {@link ModflMBfbnConstrudtorInfo} objfdts wiidi
     *         ibvf dfsdriptors
     */
    privbtf MBfbnConstrudtorInfo[] modflMBfbnConstrudtors;

    /**
     * @sfribl Tif brrby of {@link ModflMBfbnNotifidbtionInfo} objfdts wiidi
     *         ibvf dfsdriptors
     */
    privbtf MBfbnNotifidbtionInfo[] modflMBfbnNotifidbtions;

    /**
     * @sfribl Tif brrby of {@link ModflMBfbnOpfrbtionInfo} objfdts wiidi
     *         ibvf dfsdriptors
     */
    privbtf MBfbnOpfrbtionInfo[] modflMBfbnOpfrbtions;

    privbtf stbtid finbl String ATTR = "bttributf";
    privbtf stbtid finbl String OPER = "opfrbtion";
    privbtf stbtid finbl String NOTF = "notifidbtion";
    privbtf stbtid finbl String CONS = "donstrudtor";
    privbtf stbtid finbl String MMB = "mbfbn";
    privbtf stbtid finbl String ALL = "bll";
    privbtf stbtid finbl String durrClbss = "ModflMBfbnInfoSupport";

    /**
     * Construdts b ModflMBfbnInfoSupport wiidi is b duplidbtf of tif givfn
     * ModflMBfbnInfo.  Tif rfturnfd objfdt is b sibllow dopy of tif givfn
     * objfdt.  Nfitifr tif Dfsdriptor nor tif dontbinfd brrbys
     * ({@dodf ModflMBfbnAttributfInfo[]} ftd) brf dlonfd.  Tiis mftiod is
     * diiffly of intfrfst to modify tif Dfsdriptor of tif rfturnfd instbndf
     * vib {@link #sftDfsdriptor sftDfsdriptor} witiout bfffdting tif
     * Dfsdriptor of tif originbl objfdt.
     *
     * @pbrbm mbi tif ModflMBfbnInfo instbndf from wiidi tif ModflMBfbnInfo
     * bfing drfbtfd is initiblizfd.
     */
    publid ModflMBfbnInfoSupport(ModflMBfbnInfo  mbi) {
        supfr(mbi.gftClbssNbmf(),
                mbi.gftDfsdription(),
                mbi.gftAttributfs(),
                mbi.gftConstrudtors(),
                mbi.gftOpfrbtions(),
                mbi.gftNotifidbtions());

        modflMBfbnAttributfs = mbi.gftAttributfs();
        modflMBfbnConstrudtors = mbi.gftConstrudtors();
        modflMBfbnOpfrbtions = mbi.gftOpfrbtions();
        modflMBfbnNotifidbtions = mbi.gftNotifidbtions();

        try {
            Dfsdriptor mbfbndfsdriptor = mbi.gftMBfbnDfsdriptor();
            modflMBfbnDfsdriptor = vblidDfsdriptor(mbfbndfsdriptor);
        } dbtdi (MBfbnExdfption mbf) {
            modflMBfbnDfsdriptor = vblidDfsdriptor(null);
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                        ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                        "ModflMBfbnInfo(ModflMBfbnInfo)",
                        "Could not gft b vblid modflMBfbnDfsdriptor, " +
                        "sftting b dffbult Dfsdriptor");
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "ModflMBfbnInfo(ModflMBfbnInfo)", "Exit");
        }
    }

    /**
     * Crfbtfs b ModflMBfbnInfoSupport witi tif providfd informbtion,
     * but tif dfsdriptor is b dffbult.
     * Tif dffbult dfsdriptor is: nbmf=dlbssNbmf, dfsdriptorTypf="mbfbn",
     * displbyNbmf=dlbssNbmf, pfrsistPolidy="nfvfr", log="F", visibility="1"
     *
     * @pbrbm dlbssNbmf dlbssnbmf of tif MBfbn
     * @pbrbm dfsdription iumbn rfbdbblf dfsdription of tif
     * ModflMBfbn
     * @pbrbm bttributfs brrby of ModflMBfbnAttributfInfo objfdts
     * wiidi ibvf dfsdriptors
     * @pbrbm donstrudtors brrby of ModflMBfbnConstrudtorInfo
     * objfdts wiidi ibvf dfsdriptors
     * @pbrbm opfrbtions brrby of ModflMBfbnOpfrbtionInfo objfdts
     * wiidi ibvf dfsdriptors
     * @pbrbm notifidbtions brrby of ModflMBfbnNotifidbtionInfo
     * objfdts wiidi ibvf dfsdriptors
     */
    publid ModflMBfbnInfoSupport(String dlbssNbmf,
            String dfsdription,
            ModflMBfbnAttributfInfo[] bttributfs,
            ModflMBfbnConstrudtorInfo[] donstrudtors,
            ModflMBfbnOpfrbtionInfo[] opfrbtions,
            ModflMBfbnNotifidbtionInfo[] notifidbtions) {
        tiis(dlbssNbmf, dfsdription, bttributfs, donstrudtors,
                opfrbtions, notifidbtions, null);
    }

    /**
     * Crfbtfs b ModflMBfbnInfoSupport witi tif providfd informbtion
     * bnd tif dfsdriptor givfn in pbrbmftfr.
     *
     * @pbrbm dlbssNbmf dlbssnbmf of tif MBfbn
     * @pbrbm dfsdription iumbn rfbdbblf dfsdription of tif
     * ModflMBfbn
     * @pbrbm bttributfs brrby of ModflMBfbnAttributfInfo objfdts
     * wiidi ibvf dfsdriptors
     * @pbrbm donstrudtors brrby of ModflMBfbnConstrudtorInfo
     * objfdts wiidi ibvf dfsdriptor
     * @pbrbm opfrbtions brrby of ModflMBfbnOpfrbtionInfo objfdts
     * wiidi ibvf dfsdriptor
     * @pbrbm notifidbtions brrby of ModflMBfbnNotifidbtionInfo
     * objfdts wiidi ibvf dfsdriptor
     * @pbrbm mbfbndfsdriptor dfsdriptor to bf usfd bs tif
     * MBfbnDfsdriptor dontbining MBfbn widf polidy. If tif
     * dfsdriptor is null, b dffbult dfsdriptor will bf donstrudtfd.
     * Tif dffbult dfsdriptor is:
     * nbmf=dlbssNbmf, dfsdriptorTypf="mbfbn", displbyNbmf=dlbssNbmf,
     * pfrsistPolidy="nfvfr", log="F", visibility="1".  If tif dfsdriptor
     * dofs not dontbin bll of tifsf fiflds, tif missing onfs brf
     * bddfd witi tifsf dffbult vblufs.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn
     * IllfgblArgumfntExdfption for invblid dfsdriptor pbssfd in
     * pbrbmftfr.  (sff {@link #gftMBfbnDfsdriptor
     * gftMBfbnDfsdriptor} for tif dffinition of b vblid MBfbn
     * dfsdriptor.)
     */

    publid ModflMBfbnInfoSupport(String    dlbssNbmf,
            String dfsdription,
            ModflMBfbnAttributfInfo[] bttributfs,
            ModflMBfbnConstrudtorInfo[] donstrudtors,
            ModflMBfbnOpfrbtionInfo[] opfrbtions,
            ModflMBfbnNotifidbtionInfo[] notifidbtions,
            Dfsdriptor mbfbndfsdriptor) {
        supfr(dlbssNbmf,
                dfsdription,
                (bttributfs != null) ? bttributfs : NO_ATTRIBUTES,
                (donstrudtors != null) ? donstrudtors : NO_CONSTRUCTORS,
                (opfrbtions != null) ? opfrbtions : NO_OPERATIONS,
                (notifidbtions != null) ? notifidbtions : NO_NOTIFICATIONS);
        /* Tif vblufs sbvfd ifrf brf possibly null, but wf
           difdk tiis fvfrywifrf tify brf rfffrfndfd.  If bt
           somf stbgf wf rfplbdf null witi bn fmpty brrby
           ifrf, bs wf do in tif supfrdlbss donstrudtor
           pbrbmftfrs, tifn wf must blso do tiis in
           rfbdObjfdt().  */
        modflMBfbnAttributfs = bttributfs;
        modflMBfbnConstrudtors = donstrudtors;
        modflMBfbnOpfrbtions = opfrbtions;
        modflMBfbnNotifidbtions = notifidbtions;
        modflMBfbnDfsdriptor = vblidDfsdriptor(mbfbndfsdriptor);
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "ModflMBfbnInfoSupport(String,String,ModflMBfbnAttributfInfo[]," +
                    "ModflMBfbnConstrudtorInfo[],ModflMBfbnOpfrbtionInfo[]," +
                    "ModflMBfbnNotifidbtionInfo[],Dfsdriptor)",
                    "Exit");
        }
    }

    privbtf stbtid finbl ModflMBfbnAttributfInfo[] NO_ATTRIBUTES =
            nfw ModflMBfbnAttributfInfo[0];
    privbtf stbtid finbl ModflMBfbnConstrudtorInfo[] NO_CONSTRUCTORS =
            nfw ModflMBfbnConstrudtorInfo[0];
    privbtf stbtid finbl ModflMBfbnNotifidbtionInfo[] NO_NOTIFICATIONS =
            nfw ModflMBfbnNotifidbtionInfo[0];
    privbtf stbtid finbl ModflMBfbnOpfrbtionInfo[] NO_OPERATIONS =
            nfw ModflMBfbnOpfrbtionInfo[0];

    // Jbvb dod inifritfd from MOdflMBfbnInfo intfrfbdf

    /**
     * Rfturns b sibllow dlonf of tiis instbndf.  Nfitifr tif Dfsdriptor nor
     * tif dontbinfd brrbys ({@dodf ModflMBfbnAttributfInfo[]} ftd) brf
     * dlonfd.  Tiis mftiod is diiffly of intfrfst to modify tif Dfsdriptor
     * of tif dlonf vib {@link #sftDfsdriptor sftDfsdriptor} witiout bfffdting
     * tif Dfsdriptor of tif originbl objfdt.
     *
     * @rfturn b sibllow dlonf of tiis instbndf.
     */
    publid Objfdt dlonf() {
        rfturn(nfw ModflMBfbnInfoSupport(tiis));
    }


    publid Dfsdriptor[] gftDfsdriptors(String inDfsdriptorTypf)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftDfsdriptors(String)", "Entry");
        }

        if ((inDfsdriptorTypf == null) || (inDfsdriptorTypf.fqubls(""))) {
            inDfsdriptorTypf = "bll";
        }

        // if no dfsdriptors of tibt typf, will rfturn fmpty brrby
        //
        finbl Dfsdriptor[] rftList;

        if (inDfsdriptorTypf.fqublsIgnorfCbsf(MMB)) {
            rftList = nfw Dfsdriptor[] {modflMBfbnDfsdriptor};
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(ATTR)) {
            finbl MBfbnAttributfInfo[] bttrList = modflMBfbnAttributfs;
            int numAttrs = 0;
            if (bttrList != null) numAttrs = bttrList.lfngti;

            rftList = nfw Dfsdriptor[numAttrs];
            for (int i=0; i < numAttrs; i++) {
                rftList[i] = (((ModflMBfbnAttributfInfo)
                    bttrList[i]).gftDfsdriptor());
            }
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(OPER)) {
            finbl MBfbnOpfrbtionInfo[] opfrList = modflMBfbnOpfrbtions;
            int numOpfrs = 0;
            if (opfrList != null) numOpfrs = opfrList.lfngti;

            rftList = nfw Dfsdriptor[numOpfrs];
            for (int i=0; i < numOpfrs; i++) {
                rftList[i] = (((ModflMBfbnOpfrbtionInfo)
                    opfrList[i]).gftDfsdriptor());
            }
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(CONS)) {
            finbl MBfbnConstrudtorInfo[] donsList =  modflMBfbnConstrudtors;
            int numCons = 0;
            if (donsList != null) numCons = donsList.lfngti;

            rftList = nfw Dfsdriptor[numCons];
            for (int i=0; i < numCons; i++) {
                rftList[i] = (((ModflMBfbnConstrudtorInfo)
                    donsList[i]).gftDfsdriptor());
            }
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(NOTF)) {
            finbl MBfbnNotifidbtionInfo[] notifList = modflMBfbnNotifidbtions;
            int numNotifs = 0;
            if (notifList != null) numNotifs = notifList.lfngti;

            rftList = nfw Dfsdriptor[numNotifs];
            for (int i=0; i < numNotifs; i++) {
                rftList[i] = (((ModflMBfbnNotifidbtionInfo)
                    notifList[i]).gftDfsdriptor());
            }
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(ALL)) {

            finbl MBfbnAttributfInfo[] bttrList = modflMBfbnAttributfs;
            int numAttrs = 0;
            if (bttrList != null) numAttrs = bttrList.lfngti;

            finbl MBfbnOpfrbtionInfo[] opfrList = modflMBfbnOpfrbtions;
            int numOpfrs = 0;
            if (opfrList != null) numOpfrs = opfrList.lfngti;

            finbl MBfbnConstrudtorInfo[] donsList = modflMBfbnConstrudtors;
            int numCons = 0;
            if (donsList != null) numCons = donsList.lfngti;

            finbl MBfbnNotifidbtionInfo[] notifList = modflMBfbnNotifidbtions;
            int numNotifs = 0;
            if (notifList != null) numNotifs = notifList.lfngti;

            int dount = numAttrs + numCons + numOpfrs + numNotifs + 1;
            rftList = nfw Dfsdriptor[dount];

            rftList[dount-1] = modflMBfbnDfsdriptor;

            int j=0;
            for (int i=0; i < numAttrs; i++) {
                rftList[j] = (((ModflMBfbnAttributfInfo)
                    bttrList[i]).gftDfsdriptor());
                j++;
            }
            for (int i=0; i < numCons; i++) {
                rftList[j] = (((ModflMBfbnConstrudtorInfo)
                    donsList[i]).gftDfsdriptor());
                j++;
            }
            for (int i=0; i < numOpfrs; i++) {
                rftList[j] = (((ModflMBfbnOpfrbtionInfo)opfrList[i]).
                        gftDfsdriptor());
                j++;
            }
            for (int i=0; i < numNotifs; i++) {
                rftList[j] = (((ModflMBfbnNotifidbtionInfo)notifList[i]).
                        gftDfsdriptor());
                j++;
            }
        } flsf {
            finbl IllfgblArgumfntExdfption ibf =
                    nfw IllfgblArgumfntExdfption("Dfsdriptor Typf is invblid");
            finbl String msg = "Exdfption oddurrfd trying to find"+
                    " tif dfsdriptors of tif MBfbn";
            tirow nfw RuntimfOpfrbtionsExdfption(ibf,msg);
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftDfsdriptors(String)", "Exit");
        }

        rfturn rftList;
    }


    publid void sftDfsdriptors(Dfsdriptor[] inDfsdriptors)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "sftDfsdriptors(Dfsdriptor[])", "Entry");
        }
        if (inDfsdriptors==null) {
            // tirow RuntimfOpfrbtionsExdfption - invblid dfsdriptor
            tirow nfw RuntimfOpfrbtionsExdfption(
                    nfw IllfgblArgumfntExdfption("Dfsdriptor list is invblid"),
                    "Exdfption oddurrfd trying to sft tif dfsdriptors " +
                    "of tif MBfbnInfo");
        }
        if (inDfsdriptors.lfngti == 0) { // fmpty list, no-op
            rfturn;
        }
        for (int j=0; j < inDfsdriptors.lfngti; j++) {
            sftDfsdriptor(inDfsdriptors[j],null);
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "sftDfsdriptors(Dfsdriptor[])", "Exit");
        }

    }


    /**
     * Rfturns b Dfsdriptor rfqufstfd by nbmf.
     *
     * @pbrbm inDfsdriptorNbmf Tif nbmf of tif dfsdriptor.
     *
     * @rfturn Dfsdriptor dontbining b dfsdriptor for tif ModflMBfbn witi tif
     *         sbmf nbmf. If no dfsdriptor is found, null is rfturnfd.
     *
     * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption
     *            for null nbmf.
     *
     * @sff #sftDfsdriptor
     */

    publid Dfsdriptor gftDfsdriptor(String inDfsdriptorNbmf)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftDfsdriptor(String)", "Entry");
        }
        rfturn(gftDfsdriptor(inDfsdriptorNbmf, null));
    }


    publid Dfsdriptor gftDfsdriptor(String inDfsdriptorNbmf,
            String inDfsdriptorTypf)
            tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        if (inDfsdriptorNbmf==null) {
            // tirow RuntimfOpfrbtionsExdfption - invblid dfsdriptor
            tirow nfw RuntimfOpfrbtionsExdfption(
                    nfw IllfgblArgumfntExdfption("Dfsdriptor is invblid"),
                    "Exdfption oddurrfd trying to sft tif dfsdriptors of " +
                    "tif MBfbnInfo");
        }

        if (MMB.fqublsIgnorfCbsf(inDfsdriptorTypf)) {
            rfturn (Dfsdriptor) modflMBfbnDfsdriptor.dlonf();
        }

            /* Tif logid ifrf is b bit donvolutfd, bfdbusf wf brf
               dfbling witi two possiblf dbsfs, dfpfnding on wiftifr
               inDfsdriptorTypf is null.  If it's not null, tifn only
               onf of tif following ifs will run, bnd it will fitifr
               rfturn b dfsdriptor or null.  If inDfsdriptorTypf is
               null, tifn bll of tif following ifs will run until onf
               of tifm finds b dfsdriptor.  */
        if (ATTR.fqublsIgnorfCbsf(inDfsdriptorTypf) || inDfsdriptorTypf == null) {
            ModflMBfbnAttributfInfo bttr = gftAttributf(inDfsdriptorNbmf);
            if (bttr != null)
                rfturn bttr.gftDfsdriptor();
            if (inDfsdriptorTypf != null)
                rfturn null;
        }
        if (OPER.fqublsIgnorfCbsf(inDfsdriptorTypf) || inDfsdriptorTypf == null) {
            ModflMBfbnOpfrbtionInfo opfr = gftOpfrbtion(inDfsdriptorNbmf);
            if (opfr != null)
                rfturn opfr.gftDfsdriptor();
            if (inDfsdriptorTypf != null)
                rfturn null;
        }
        if (CONS.fqublsIgnorfCbsf(inDfsdriptorTypf) || inDfsdriptorTypf == null) {
            ModflMBfbnConstrudtorInfo opfr =
                    gftConstrudtor(inDfsdriptorNbmf);
            if (opfr != null)
                rfturn opfr.gftDfsdriptor();
            if (inDfsdriptorTypf != null)
                rfturn null;
        }
        if (NOTF.fqublsIgnorfCbsf(inDfsdriptorTypf) || inDfsdriptorTypf == null) {
            ModflMBfbnNotifidbtionInfo notif =
                    gftNotifidbtion(inDfsdriptorNbmf);
            if (notif != null)
                rfturn notif.gftDfsdriptor();
            if (inDfsdriptorTypf != null)
                rfturn null;
        }
        if (inDfsdriptorTypf == null)
            rfturn null;
        tirow nfw RuntimfOpfrbtionsExdfption(
                nfw IllfgblArgumfntExdfption("Dfsdriptor Typf is invblid"),
                "Exdfption oddurrfd trying to find tif dfsdriptors of tif MBfbn");

    }



    publid void sftDfsdriptor(Dfsdriptor inDfsdriptor,
            String inDfsdriptorTypf)
            tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        finbl String fxdMsg =
                "Exdfption oddurrfd trying to sft tif dfsdriptors of tif MBfbn";
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "sftDfsdriptor(Dfsdriptor,String)", "Entry");
        }

        if (inDfsdriptor==null) {
            inDfsdriptor = nfw DfsdriptorSupport();
        }

        if ((inDfsdriptorTypf == null) || (inDfsdriptorTypf.fqubls(""))) {
            inDfsdriptorTypf =
                    (String) inDfsdriptor.gftFifldVbluf("dfsdriptorTypf");

            if (inDfsdriptorTypf == null) {
                   MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                                ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                                "sftDfsdriptor(Dfsdriptor,String)",
                                "dfsdriptorTypf null in boti String pbrbmftfr bnd Dfsdriptor, dffbulting to "+ MMB);
                inDfsdriptorTypf = MMB;
            }
        }

        String inDfsdriptorNbmf =
                (String) inDfsdriptor.gftFifldVbluf("nbmf");
        if (inDfsdriptorNbmf == null) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                                ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                                "sftDfsdriptor(Dfsdriptor,String)",
                                "dfsdriptor nbmf null, dffbulting to "+ tiis.gftClbssNbmf());
            inDfsdriptorNbmf = tiis.gftClbssNbmf();
        }
        boolfbn found = fblsf;
        if (inDfsdriptorTypf.fqublsIgnorfCbsf(MMB)) {
            sftMBfbnDfsdriptor(inDfsdriptor);
            found = truf;
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(ATTR)) {
            MBfbnAttributfInfo[] bttrList =  modflMBfbnAttributfs;
            int numAttrs = 0;
            if (bttrList != null) numAttrs = bttrList.lfngti;

            for (int i=0; i < numAttrs; i++) {
                if (inDfsdriptorNbmf.fqubls(bttrList[i].gftNbmf())) {
                    found = truf;
                    ModflMBfbnAttributfInfo mmbbi =
                            (ModflMBfbnAttributfInfo) bttrList[i];
                    mmbbi.sftDfsdriptor(inDfsdriptor);
                    if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        StringBuildfr strb = nfw StringBuildfr()
                        .bppfnd("Sftting dfsdriptor to ").bppfnd(inDfsdriptor)
                        .bppfnd("\t\n lodbl: AttributfInfo dfsdriptor is ")
                        .bppfnd(mmbbi.gftDfsdriptor())
                        .bppfnd("\t\n modflMBfbnInfo: AttributfInfo dfsdriptor is ")
                        .bppfnd(tiis.gftDfsdriptor(inDfsdriptorNbmf,"bttributf"));
                        MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                                ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                                "sftDfsdriptor(Dfsdriptor,String)",
                                strb.toString());
                    }
                }
            }
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(OPER)) {
            MBfbnOpfrbtionInfo[] opfrList =  modflMBfbnOpfrbtions;
            int numOpfrs = 0;
            if (opfrList != null) numOpfrs = opfrList.lfngti;

            for (int i=0; i < numOpfrs; i++) {
                if (inDfsdriptorNbmf.fqubls(opfrList[i].gftNbmf())) {
                    found = truf;
                    ModflMBfbnOpfrbtionInfo mmboi =
                            (ModflMBfbnOpfrbtionInfo) opfrList[i];
                    mmboi.sftDfsdriptor(inDfsdriptor);
                }
            }
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(CONS)) {
            MBfbnConstrudtorInfo[] donsList =  modflMBfbnConstrudtors;
            int numCons = 0;
            if (donsList != null) numCons = donsList.lfngti;

            for (int i=0; i < numCons; i++) {
                if (inDfsdriptorNbmf.fqubls(donsList[i].gftNbmf())) {
                    found = truf;
                    ModflMBfbnConstrudtorInfo mmbdi =
                            (ModflMBfbnConstrudtorInfo) donsList[i];
                    mmbdi.sftDfsdriptor(inDfsdriptor);
                }
            }
        } flsf if (inDfsdriptorTypf.fqublsIgnorfCbsf(NOTF)) {
            MBfbnNotifidbtionInfo[] notifList =  modflMBfbnNotifidbtions;
            int numNotifs = 0;
            if (notifList != null) numNotifs = notifList.lfngti;

            for (int i=0; i < numNotifs; i++) {
                if (inDfsdriptorNbmf.fqubls(notifList[i].gftNbmf())) {
                    found = truf;
                    ModflMBfbnNotifidbtionInfo mmbni =
                            (ModflMBfbnNotifidbtionInfo) notifList[i];
                    mmbni.sftDfsdriptor(inDfsdriptor);
                }
            }
        } flsf {
            RuntimfExdfption ibf =
                    nfw IllfgblArgumfntExdfption("Invblid dfsdriptor typf: " +
                    inDfsdriptorTypf);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, fxdMsg);
        }

        if (!found) {
            RuntimfExdfption ibf =
                    nfw IllfgblArgumfntExdfption("Dfsdriptor nbmf is invblid: " +
                    "typf=" + inDfsdriptorTypf +
                    "; nbmf=" + inDfsdriptorNbmf);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, fxdMsg);
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "sftDfsdriptor(Dfsdriptor,String)", "Exit");
        }

    }


    publid ModflMBfbnAttributfInfo gftAttributf(String inNbmf)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        ModflMBfbnAttributfInfo rftInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftAttributf(String)", "Entry");
        }
        if (inNbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(
                    nfw IllfgblArgumfntExdfption("Attributf Nbmf is null"),
                    "Exdfption oddurrfd trying to gft tif " +
                    "ModflMBfbnAttributfInfo of tif MBfbn");
        }
        MBfbnAttributfInfo[] bttrList = modflMBfbnAttributfs;
        int numAttrs = 0;
        if (bttrList != null) numAttrs = bttrList.lfngti;

        for (int i=0; (i < numAttrs) && (rftInfo == null); i++) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                finbl StringBuildfr strb = nfw StringBuildfr()
                .bppfnd("\t\n tiis.gftAttributfs() MBfbnAttributfInfo Arrby ")
                .bppfnd(i).bppfnd(":")
                .bppfnd(((ModflMBfbnAttributfInfo)bttrList[i]).gftDfsdriptor())
                .bppfnd("\t\n tiis.modflMBfbnAttributfs MBfbnAttributfInfo Arrby ")
                .bppfnd(i).bppfnd(":")
                .bppfnd(((ModflMBfbnAttributfInfo)modflMBfbnAttributfs[i]).gftDfsdriptor());
                MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                        ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                        "gftAttributf(String)", strb.toString());
            }
            if (inNbmf.fqubls(bttrList[i].gftNbmf())) {
                rftInfo = ((ModflMBfbnAttributfInfo)bttrList[i].dlonf());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftAttributf(String)", "Exit");
        }

        rfturn rftInfo;
    }



    publid ModflMBfbnOpfrbtionInfo gftOpfrbtion(String inNbmf)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        ModflMBfbnOpfrbtionInfo rftInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftOpfrbtion(String)", "Entry");
        }
        if (inNbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(
                    nfw IllfgblArgumfntExdfption("inNbmf is null"),
                    "Exdfption oddurrfd trying to gft tif " +
                    "ModflMBfbnOpfrbtionInfo of tif MBfbn");
        }
        MBfbnOpfrbtionInfo[] opfrList = modflMBfbnOpfrbtions; //tiis.gftOpfrbtions();
        int numOpfrs = 0;
        if (opfrList != null) numOpfrs = opfrList.lfngti;

        for (int i=0; (i < numOpfrs) && (rftInfo == null); i++) {
            if (inNbmf.fqubls(opfrList[i].gftNbmf())) {
                rftInfo = ((ModflMBfbnOpfrbtionInfo) opfrList[i].dlonf());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftOpfrbtion(String)", "Exit");
        }

        rfturn rftInfo;
    }

    /**
     * Rfturns tif ModflMBfbnConstrudtorInfo rfqufstfd by nbmf.
     * If no ModflMBfbnConstrudtorInfo fxists for tiis nbmf null is rfturnfd.
     *
     * @pbrbm inNbmf tif nbmf of tif donstrudtor.
     *
     * @rfturn tif donstrudtor info for tif nbmfd donstrudtor, or null
     * if tifrf is nonf.
     *
     * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn IllfgblArgumfntExdfption
     *            for b null donstrudtor nbmf.
     */

    publid ModflMBfbnConstrudtorInfo gftConstrudtor(String inNbmf)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        ModflMBfbnConstrudtorInfo rftInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftConstrudtor(String)", "Entry");
        }
        if (inNbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(
                    nfw IllfgblArgumfntExdfption("Construdtor nbmf is null"),
                    "Exdfption oddurrfd trying to gft tif " +
                    "ModflMBfbnConstrudtorInfo of tif MBfbn");
        }
        MBfbnConstrudtorInfo[] donsList = modflMBfbnConstrudtors; //tiis.gftConstrudtors();
        int numCons = 0;
        if (donsList != null) numCons = donsList.lfngti;

        for (int i=0; (i < numCons) && (rftInfo == null); i++) {
            if (inNbmf.fqubls(donsList[i].gftNbmf())) {
                rftInfo = ((ModflMBfbnConstrudtorInfo) donsList[i].dlonf());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftConstrudtor(String)", "Exit");
        }

        rfturn rftInfo;
    }


    publid ModflMBfbnNotifidbtionInfo gftNotifidbtion(String inNbmf)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        ModflMBfbnNotifidbtionInfo rftInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftNotifidbtion(String)", "Entry");
        }
        if (inNbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(
                    nfw IllfgblArgumfntExdfption("Notifidbtion nbmf is null"),
                    "Exdfption oddurrfd trying to gft tif " +
                    "ModflMBfbnNotifidbtionInfo of tif MBfbn");
        }
        MBfbnNotifidbtionInfo[] notifList = modflMBfbnNotifidbtions; //tiis.gftNotifidbtions();
        int numNotifs = 0;
        if (notifList != null) numNotifs = notifList.lfngti;

        for (int i=0; (i < numNotifs) && (rftInfo == null); i++) {
            if (inNbmf.fqubls(notifList[i].gftNbmf())) {
                rftInfo = ((ModflMBfbnNotifidbtionInfo) notifList[i].dlonf());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftNotifidbtion(String)", "Exit");
        }

        rfturn rftInfo;
    }


    /* Wf ovfrridf MBfbnInfo.gftDfsdriptor() to rfturn our dfsdriptor. */
    /**
     * @sindf 1.6
     */
    @Ovfrridf
    publid Dfsdriptor gftDfsdriptor() {
        rfturn gftMBfbnDfsdriptorNoExdfption();
    }

    publid Dfsdriptor gftMBfbnDfsdriptor() tirows MBfbnExdfption {
        rfturn gftMBfbnDfsdriptorNoExdfption();
    }

    privbtf Dfsdriptor gftMBfbnDfsdriptorNoExdfption() {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftMBfbnDfsdriptorNoExdfption()", "Entry");
        }

        if (modflMBfbnDfsdriptor == null)
            modflMBfbnDfsdriptor = vblidDfsdriptor(null);

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "gftMBfbnDfsdriptorNoExdfption()",
                    "Exit, rfturning: " + modflMBfbnDfsdriptor);
        }
        rfturn (Dfsdriptor) modflMBfbnDfsdriptor.dlonf();
    }

    publid void sftMBfbnDfsdriptor(Dfsdriptor inMBfbnDfsdriptor)
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnInfoSupport.dlbss.gftNbmf(),
                    "sftMBfbnDfsdriptor(Dfsdriptor)", "Entry");
        }
        modflMBfbnDfsdriptor = vblidDfsdriptor(inMBfbnDfsdriptor);
    }


    /**
     * Clonfs tif pbssfd in Dfsdriptor, sfts dffbult vblufs, bnd difdks for vblidity.
     * If tif Dfsdriptor is invblid (for instbndf by ibving tif wrong "nbmf"),
     * tiis indidbtfs progrbmming frror bnd b RuntimfOpfrbtionsExdfption will bf tirown.
     *
     * Tif following fiflds will bf dffbultfd if tify brf not blrfbdy sft:
     * displbyNbmf=dlbssNbmf,nbmf=dlbssNbmf,dfsdriptorTypf="mbfbn",
     * pfrsistPolidy="nfvfr", log="F", visibility="1"
     *
     * @pbrbm in Dfsdriptor to bf difdkfd, or null wiidi is fquivblfnt to
     * bn fmpty Dfsdriptor.
     * @fxdfption RuntimfOpfrbtionsExdfption if Dfsdriptor is invblid
     */
    privbtf Dfsdriptor vblidDfsdriptor(finbl Dfsdriptor in) tirows RuntimfOpfrbtionsExdfption {
        Dfsdriptor dlonf;
        boolfbn dffbultfd = (in == null);
        if (dffbultfd) {
            dlonf = nfw DfsdriptorSupport();
            MODELMBEAN_LOGGER.finfr("Null Dfsdriptor, drfbting nfw.");
        } flsf {
            dlonf = (Dfsdriptor) in.dlonf();
        }

        //Sftting dffbults.
        if (dffbultfd && dlonf.gftFifldVbluf("nbmf")==null) {
            dlonf.sftFifld("nbmf", tiis.gftClbssNbmf());
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor nbmf to " + tiis.gftClbssNbmf());
        }
        if (dffbultfd && dlonf.gftFifldVbluf("dfsdriptorTypf")==null) {
            dlonf.sftFifld("dfsdriptorTypf", MMB);
            MODELMBEAN_LOGGER.finfr("Dffbulting dfsdriptorTypf to \"" + MMB + "\"");
        }
        if (dlonf.gftFifldVbluf("displbyNbmf") == null) {
            dlonf.sftFifld("displbyNbmf",tiis.gftClbssNbmf());
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor displbyNbmf to " + tiis.gftClbssNbmf());
        }
        if (dlonf.gftFifldVbluf("pfrsistPolidy") == null) {
            dlonf.sftFifld("pfrsistPolidy","nfvfr");
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor pfrsistPolidy to \"nfvfr\"");
        }
        if (dlonf.gftFifldVbluf("log") == null) {
            dlonf.sftFifld("log","F");
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor \"log\" fifld to \"F\"");
        }
        if (dlonf.gftFifldVbluf("visibility") == null) {
            dlonf.sftFifld("visibility","1");
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor visibility to 1");
        }

        //Cifdking vblidity
        if (!dlonf.isVblid()) {
             tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                "Tif isVblid() mftiod of tif Dfsdriptor objfdt itsflf rfturnfd fblsf,"+
                "onf or morf rfquirfd fiflds brf invblid. Dfsdriptor:" + dlonf.toString());
        }

        if (! ((String)dlonf.gftFifldVbluf("dfsdriptorTypf")).fqublsIgnorfCbsf(MMB)) {
                 tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                "Tif Dfsdriptor \"dfsdriptorTypf\" fifld dofs not mbtdi tif objfdt dfsdribfd. " +
                 " Expfdtfd: "+ MMB + " , wbs: " + dlonf.gftFifldVbluf("dfsdriptorTypf"));
        }

        rfturn dlonf;
    }




    /**
     * Dfsfriblizfs b {@link ModflMBfbnInfoSupport} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
    tirows IOExdfption, ClbssNotFoundExdfption {
        if (dompbt) {
            // Rfbd bn objfdt sfriblizfd in tif old sfribl form
            //
            ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
            modflMBfbnDfsdriptor =
                    (Dfsdriptor) fiflds.gft("modflMBfbnDfsdriptor", null);
            if (fiflds.dffbultfd("modflMBfbnDfsdriptor")) {
                tirow nfw NullPointfrExdfption("modflMBfbnDfsdriptor");
            }
            modflMBfbnAttributfs =
                    (MBfbnAttributfInfo[]) fiflds.gft("mmbAttributfs", null);
            if (fiflds.dffbultfd("mmbAttributfs")) {
                tirow nfw NullPointfrExdfption("mmbAttributfs");
            }
            modflMBfbnConstrudtors =
                    (MBfbnConstrudtorInfo[]) fiflds.gft("mmbConstrudtors", null);
            if (fiflds.dffbultfd("mmbConstrudtors")) {
                tirow nfw NullPointfrExdfption("mmbConstrudtors");
            }
            modflMBfbnNotifidbtions =
                    (MBfbnNotifidbtionInfo[]) fiflds.gft("mmbNotifidbtions", null);
            if (fiflds.dffbultfd("mmbNotifidbtions")) {
                tirow nfw NullPointfrExdfption("mmbNotifidbtions");
            }
            modflMBfbnOpfrbtions =
                    (MBfbnOpfrbtionInfo[]) fiflds.gft("mmbOpfrbtions", null);
            if (fiflds.dffbultfd("mmbOpfrbtions")) {
                tirow nfw NullPointfrExdfption("mmbOpfrbtions");
            }
        } flsf {
            // Rfbd bn objfdt sfriblizfd in tif nfw sfribl form
            //
            in.dffbultRfbdObjfdt();
        }
    }


    /**
     * Sfriblizfs b {@link ModflMBfbnInfoSupport} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
    tirows IOExdfption {
        if (dompbt) {
            // Sfriblizfs tiis instbndf in tif old sfribl form
            //
            ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
            fiflds.put("modflMBfbnDfsdriptor", modflMBfbnDfsdriptor);
            fiflds.put("mmbAttributfs", modflMBfbnAttributfs);
            fiflds.put("mmbConstrudtors", modflMBfbnConstrudtors);
            fiflds.put("mmbNotifidbtions", modflMBfbnNotifidbtions);
            fiflds.put("mmbOpfrbtions", modflMBfbnOpfrbtions);
            fiflds.put("durrClbss", durrClbss);
            out.writfFiflds();
        } flsf {
            // Sfriblizfs tiis instbndf in tif nfw sfribl form
            //
            out.dffbultWritfObjfdt();
        }
    }


}
