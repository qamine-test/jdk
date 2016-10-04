/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.DfsdriptorAddfss;
import jbvbx.mbnbgfmfnt.DfsdriptorKfy;
import jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo;
import jbvbx.mbnbgfmfnt.MBfbnPbrbmftfrInfo;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;

/**
 * <p>Tif ModflMBfbnOpfrbtionInfo objfdt dfsdribfs b mbnbgfmfnt opfrbtion of
 * tif ModflMBfbn.  It is b subdlbss of MBfbnOpfrbtionInfo witi tif bddition
 * of bn bssodibtfd Dfsdriptor bnd bn implfmfntbtion of tif DfsdriptorAddfss
 * intfrfbdf.</p>
 *
 * <P id="dfsdriptor">
 * Tif fiflds in tif dfsdriptor brf dffinfd, but not limitfd to, tif following.
 * Notf tibt wifn tif Typf in tiis tbblf is Numbfr, b String tibt is tif dfdimbl
 * rfprfsfntbtion of b Long dbn blso bf usfd.</P>
 *
 * <tbblf bordfr="1" dfllpbdding="5" summbry="ModflMBfbnOpfrbtionInfo Fiflds">
 * <tr><ti>Nbmf</ti><ti>Typf</ti><ti>Mfbning</ti></tr>
 * <tr><td>nbmf</td><td>String</td>
 *     <td>Opfrbtion nbmf.</td></tr>
 * <tr><td>dfsdriptorTypf</td><td>String</td>
 *     <td>Must bf "opfrbtion".</td></tr>
 * <tr><td>dlbss</td><td>String</td>
 *     <td>Clbss wifrf mftiod is dffinfd (fully qublififd).</td></tr>
 * <tr><td>rolf</td><td>String</td>
 *     <td>Must bf "opfrbtion", "gfttfr", or "sfttfr".</td></tr>
 * <tr><td>tbrgftObjfdt</td><td>Objfdt</td>
 *     <td>Objfdt on wiidi to fxfdutf tiis mftiod.</td></tr>
 * <tr><td>tbrgftTypf</td><td>String</td>
 *     <td>typf of objfdt rfffrfndf for tbrgftObjfdt. Cbn bf:
 *         ObjfdtRfffrfndf | Hbndlf | EJBHbndlf | IOR | RMIRfffrfndf.</td></tr>
 * <tr><td>vbluf</td><td>Objfdt</td>
 *     <td>Cbdifd vbluf for opfrbtion.</td></tr>
 * <tr><td>displbyNbmf</td><td>String</td>
 *     <td>Humbn rfbdbblf displby nbmf of tif opfrbtion.</td>
 * <tr><td>durrfndyTimfLimit</td><td>Numbfr</td>
 *     <td>How long dbdifd vbluf is vblid.</td></tr>
 * <tr><td>lbstUpdbtfdTimfStbmp</td><td>Numbfr</td>
 *     <td>Wifn dbdifd vbluf wbs sft.</td></tr>
 * <tr><td>visibility</td><td>Numbfr</td>
 *     <td>1-4 wifrf 1: blwbys visiblf 4: rbrfly visiblf.</td></tr>
 * <tr><td>prfsfntbtionString</td><td>String</td>
 *     <td>XML formbttfd string to dfsdribf iow to prfsfnt opfrbtion</td></tr>
 * </tbblf>
 *
 * <p>Tif dffbult dfsdriptor will ibvf nbmf, dfsdriptorTypf, displbyNbmf bnd
 * rolf fiflds sft.  Tif dffbult vbluf of tif nbmf bnd displbyNbmf fiflds is
 * tif opfrbtion nbmf.</p>
 *
 * <p><b>Notf:</b> bfdbusf of indonsistfndifs in prfvious vfrsions of
 * tiis spfdifidbtion, it is rfdommfndfd not to usf nfgbtivf or zfro
 * vblufs for <dodf>durrfndyTimfLimit</dodf>.  To indidbtf tibt b
 * dbdifd vbluf is nfvfr vblid, omit tif
 * <dodf>durrfndyTimfLimit</dodf> fifld.  To indidbtf tibt it is
 * blwbys vblid, usf b vfry lbrgf numbfr for tiis fifld.</p>
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>6532732096650090465L</dodf>.
 *
 * @sindf 1.5
 */

@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID is not donstbnt
publid dlbss ModflMBfbnOpfrbtionInfo fxtfnds MBfbnOpfrbtionInfo
         implfmfnts DfsdriptorAddfss
{

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = 9087646304346171239L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = 6532732096650090465L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("opfrbtionDfsdriptor", Dfsdriptor.dlbss),
      nfw ObjfdtStrfbmFifld("durrClbss", String.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("opfrbtionDfsdriptor", Dfsdriptor.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld opfrbtionDfsdriptor Dfsdriptor Tif dfsdriptor
     * dontbining tif bppropribtf mftbdbtb for tiis instbndf
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
         * @sfribl Tif dfsdriptor dontbining tif bppropribtf mftbdbtb for tiis instbndf
         */
        privbtf Dfsdriptor opfrbtionDfsdriptor = vblidDfsdriptor(null);

        privbtf stbtid finbl String durrClbss = "ModflMBfbnOpfrbtionInfo";

        /**
         * Construdts b ModflMBfbnOpfrbtionInfo objfdt witi b dffbult
         * dfsdriptor. Tif {@link Dfsdriptor} of tif donstrudtfd
         * objfdt will indludf fiflds dontributfd by bny bnnotbtions
         * on tif {@dodf Mftiod} objfdt tibt dontbin tif {@link
         * DfsdriptorKfy} mftb-bnnotbtion.
         *
         * @pbrbm opfrbtionMftiod Tif jbvb.lbng.rfflfdt.Mftiod objfdt
         * dfsdribing tif MBfbn opfrbtion.
         * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif opfrbtion.
         */

        publid ModflMBfbnOpfrbtionInfo(String dfsdription,
                                       Mftiod opfrbtionMftiod)
        {
                supfr(dfsdription, opfrbtionMftiod);
                // drfbtf dffbult dfsdriptor
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                            ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                            "ModflMBfbnOpfrbtionInfo(String,Mftiod)",
                            "Entry");
                }
                opfrbtionDfsdriptor = vblidDfsdriptor(null);
        }

        /**
         * Construdts b ModflMBfbnOpfrbtionInfo objfdt. Tif {@link
         * Dfsdriptor} of tif donstrudtfd objfdt will indludf fiflds
         * dontributfd by bny bnnotbtions on tif {@dodf Mftiod} objfdt
         * tibt dontbin tif {@link DfsdriptorKfy} mftb-bnnotbtion.
         *
         * @pbrbm opfrbtionMftiod Tif jbvb.lbng.rfflfdt.Mftiod objfdt
         * dfsdribing tif MBfbn opfrbtion.
         * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif
         * opfrbtion.
         * @pbrbm dfsdriptor An instbndf of Dfsdriptor dontbining tif
         * bppropribtf mftbdbtb for tiis instbndf of tif
         * ModflMBfbnOpfrbtionInfo.  If it is null b dffbult
         * dfsdriptor will bf drfbtfd. If tif dfsdriptor dofs not
         * dontbin tif fiflds
         * "displbyNbmf" or "rolf", tif missing onfs brf bddfd witi
         * tifir dffbult vblufs.
         *
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn
         * IllfgblArgumfntExdfption. Tif dfsdriptor is invblid; or
         * dfsdriptor fifld "nbmf" is not fqubl to
         * opfrbtion nbmf; or dfsdriptor fifld "DfsdriptorTypf" is
         * not fqubl to "opfrbtion"; or dfsdriptor
         * optionbl fifld "rolf" is prfsfnt but not fqubl to "opfrbtion",
         * "gfttfr", or "sfttfr".
         *
         */

        publid ModflMBfbnOpfrbtionInfo(String dfsdription,
                                       Mftiod opfrbtionMftiod,
                                       Dfsdriptor dfsdriptor)
        {

                supfr(dfsdription, opfrbtionMftiod);
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                            ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                            "ModflMBfbnOpfrbtionInfo(String,Mftiod,Dfsdriptor)",
                            "Entry");
                }
                opfrbtionDfsdriptor = vblidDfsdriptor(dfsdriptor);
        }

        /**
        * Construdts b ModflMBfbnOpfrbtionInfo objfdt witi b dffbult dfsdriptor.
        *
        * @pbrbm nbmf Tif nbmf of tif mftiod.
        * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif opfrbtion.
        * @pbrbm signbturf MBfbnPbrbmftfrInfo objfdts dfsdribing tif
        * pbrbmftfrs(brgumfnts) of tif mftiod.
        * @pbrbm typf Tif typf of tif mftiod's rfturn vbluf.
        * @pbrbm impbdt Tif impbdt of tif mftiod, onf of INFO, ACTION,
        * ACTION_INFO, UNKNOWN.
        */

        publid ModflMBfbnOpfrbtionInfo(String nbmf,
                                       String dfsdription,
                                       MBfbnPbrbmftfrInfo[] signbturf,
                                       String typf,
                                       int impbdt)
        {

                supfr(nbmf, dfsdription, signbturf, typf, impbdt);
                // drfbtf dffbult dfsdriptor
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                            ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                            "ModflMBfbnOpfrbtionInfo(" +
                            "String,String,MBfbnPbrbmftfrInfo[],String,int)",
                            "Entry");
                }
                opfrbtionDfsdriptor = vblidDfsdriptor(null);
        }

        /**
        * Construdts b ModflMBfbnOpfrbtionInfo objfdt.
        *
        * @pbrbm nbmf Tif nbmf of tif mftiod.
        * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif opfrbtion.
        * @pbrbm signbturf MBfbnPbrbmftfrInfo objfdts dfsdribing tif
        * pbrbmftfrs(brgumfnts) of tif mftiod.
        * @pbrbm typf Tif typf of tif mftiod's rfturn vbluf.
        * @pbrbm impbdt Tif impbdt of tif mftiod, onf of INFO, ACTION,
        * ACTION_INFO, UNKNOWN.
        * @pbrbm dfsdriptor An instbndf of Dfsdriptor dontbining tif
        * bppropribtf mftbdbtb for tiis instbndf of tif
        * MBfbnOpfrbtionInfo. If it is null tifn b dffbult dfsdriptor
        * will bf drfbtfd.  If tif dfsdriptor dofs not dontbin
        * fiflds "displbyNbmf" or "rolf",
        * tif missing onfs brf bddfd witi tifir dffbult vblufs.
        *
        * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn
        * IllfgblArgumfntExdfption. Tif dfsdriptor is invblid; or
        * dfsdriptor fifld "nbmf" is not fqubl to
        * opfrbtion nbmf; or dfsdriptor fifld "DfsdriptorTypf" is
        * not fqubl to "opfrbtion"; or dfsdriptor optionbl
        * fifld "rolf" is prfsfnt but not fqubl to "opfrbtion", "gfttfr", or
        * "sfttfr".
        */

        publid ModflMBfbnOpfrbtionInfo(String nbmf,
                                       String dfsdription,
                                       MBfbnPbrbmftfrInfo[] signbturf,
                                       String typf,
                                       int impbdt,
                                       Dfsdriptor dfsdriptor)
        {
                supfr(nbmf, dfsdription, signbturf, typf, impbdt);
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                            ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                            "ModflMBfbnOpfrbtionInfo(String,String," +
                            "MBfbnPbrbmftfrInfo[],String,int,Dfsdriptor)",
                            "Entry");
                }
                opfrbtionDfsdriptor = vblidDfsdriptor(dfsdriptor);
        }

        /**
         * Construdts b nfw ModflMBfbnOpfrbtionInfo objfdt from tiis
         * ModflMBfbnOpfrbtion Objfdt.
         *
         * @pbrbm inInfo tif ModflMBfbnOpfrbtionInfo to bf duplidbtfd
         *
         */

        publid ModflMBfbnOpfrbtionInfo(ModflMBfbnOpfrbtionInfo inInfo)
        {
                supfr(inInfo.gftNbmf(),
                          inInfo.gftDfsdription(),
                          inInfo.gftSignbturf(),
                          inInfo.gftRfturnTypf(),
                          inInfo.gftImpbdt());
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                            ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                            "ModflMBfbnOpfrbtionInfo(ModflMBfbnOpfrbtionInfo)",
                            "Entry");
                }
                Dfsdriptor nfwDfsd = inInfo.gftDfsdriptor();
                opfrbtionDfsdriptor = vblidDfsdriptor(nfwDfsd);
        }

        /**
        * Crfbtfs bnd rfturns b nfw ModflMBfbnOpfrbtionInfo wiidi is b
        * duplidbtf of tiis ModflMBfbnOpfrbtionInfo.
        *
        */

        publid Objfdt dlonf ()
        {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                        ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                        "dlonf()", "Entry");
            }
                rfturn(nfw ModflMBfbnOpfrbtionInfo(tiis)) ;
        }

        /**
         * Rfturns b dopy of tif bssodibtfd Dfsdriptor of tif
         * ModflMBfbnOpfrbtionInfo.
         *
         * @rfturn Dfsdriptor bssodibtfd witi tif
         * ModflMBfbnOpfrbtionInfo objfdt.
         *
         * @sff #sftDfsdriptor
         */

        publid Dfsdriptor gftDfsdriptor()
        {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                        ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                        "gftDfsdriptor()", "Entry");
            }
            if (opfrbtionDfsdriptor == null) {
                opfrbtionDfsdriptor = vblidDfsdriptor(null);
            }

            rfturn((Dfsdriptor) opfrbtionDfsdriptor.dlonf());
        }

        /**
         * Sfts bssodibtfd Dfsdriptor (full rfplbdf) for tif
         * ModflMBfbnOpfrbtionInfo If tif nfw Dfsdriptor is null, tifn
         * tif bssodibtfd Dfsdriptor rfvfrts to b dffbult dfsdriptor.
         * Tif Dfsdriptor is vblidbtfd bfforf it is bssignfd.  If tif
         * nfw Dfsdriptor is invblid, tifn b
         * RuntimfOpfrbtionsExdfption wrbpping bn
         * IllfgblArgumfntExdfption is tirown.
         *
         * @pbrbm inDfsdriptor rfplbdfs tif Dfsdriptor bssodibtfd witi tif
         * ModflMBfbnOpfrbtion.
         *
         * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn
         * IllfgblArgumfntExdfption for invblid Dfsdriptor.
         *
         * @sff #gftDfsdriptor
         */
        publid void sftDfsdriptor(Dfsdriptor inDfsdriptor)
        {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                        ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                        "sftDfsdriptor(Dfsdriptor)", "Entry");
            }
            opfrbtionDfsdriptor = vblidDfsdriptor(inDfsdriptor);
        }

        /**
        * Rfturns b string dontbining tif fntirf dontfnts of tif
        * ModflMBfbnOpfrbtionInfo in iumbn rfbdbblf form.
        */
        publid String toString()
        {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                        ModflMBfbnOpfrbtionInfo.dlbss.gftNbmf(),
                        "toString()", "Entry");
            }
                String rftStr =
                    "ModflMBfbnOpfrbtionInfo: " + tiis.gftNbmf() +
                    " ; Dfsdription: " + tiis.gftDfsdription() +
                    " ; Dfsdriptor: " + tiis.gftDfsdriptor() +
                    " ; RfturnTypf: " + tiis.gftRfturnTypf() +
                    " ; Signbturf: ";
                MBfbnPbrbmftfrInfo[] pTypfs = tiis.gftSignbturf();
                for (int i=0; i < pTypfs.lfngti; i++)
                {
                        rftStr = rftStr.dondbt((pTypfs[i]).gftTypf() + ", ");
                }
                rfturn rftStr;
        }

        /**
         * Clonfs tif pbssfd in Dfsdriptor, sfts dffbult vblufs, bnd difdks for vblidity.
         * If tif Dfsdriptor is invblid (for instbndf by ibving tif wrong "nbmf"),
         * tiis indidbtfs progrbmming frror bnd b RuntimfOpfrbtionsExdfption will bf tirown.
         *
         * Tif following fiflds will bf dffbultfd if tify brf not blrfbdy sft:
         * displbyNbmf=tiis.gftNbmf(),nbmf=tiis.gftNbmf(),
         * dfsdriptorTypf="opfrbtion", rolf="opfrbtion"
         *
         *
         * @pbrbm in Dfsdriptor to bf difdkfd, or null wiidi is fquivblfnt to
         * bn fmpty Dfsdriptor.
         * @fxdfption RuntimfOpfrbtionsExdfption if Dfsdriptor is invblid
         */
        privbtf Dfsdriptor vblidDfsdriptor(finbl Dfsdriptor in)
        tirows RuntimfOpfrbtionsExdfption {
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
                dlonf.sftFifld("nbmf", tiis.gftNbmf());
                MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor nbmf to " + tiis.gftNbmf());
            }
            if (dffbultfd && dlonf.gftFifldVbluf("dfsdriptorTypf")==null) {
                dlonf.sftFifld("dfsdriptorTypf", "opfrbtion");
                MODELMBEAN_LOGGER.finfr("Dffbulting dfsdriptorTypf to \"opfrbtion\"");
            }
            if (dlonf.gftFifldVbluf("displbyNbmf") == null) {
                dlonf.sftFifld("displbyNbmf",tiis.gftNbmf());
                MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor displbyNbmf to " + tiis.gftNbmf());
            }
            if (dlonf.gftFifldVbluf("rolf") == null) {
                dlonf.sftFifld("rolf","opfrbtion");
                MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor rolf fifld to \"opfrbtion\"");
            }

            //Cifdking vblidity
            if (!dlonf.isVblid()) {
                 tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                    "Tif isVblid() mftiod of tif Dfsdriptor objfdt itsflf rfturnfd fblsf,"+
                    "onf or morf rfquirfd fiflds brf invblid. Dfsdriptor:" + dlonf.toString());
            }
            if (!gftNbmf().fqublsIgnorfCbsf((String) dlonf.gftFifldVbluf("nbmf"))) {
                    tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                    "Tif Dfsdriptor \"nbmf\" fifld dofs not mbtdi tif objfdt dfsdribfd. " +
                     " Expfdtfd: "+ tiis.gftNbmf() + " , wbs: " + dlonf.gftFifldVbluf("nbmf"));
            }
            if (!"opfrbtion".fqublsIgnorfCbsf((String) dlonf.gftFifldVbluf("dfsdriptorTypf"))) {
                     tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                    "Tif Dfsdriptor \"dfsdriptorTypf\" fifld dofs not mbtdi tif objfdt dfsdribfd. " +
                     " Expfdtfd: \"opfrbtion\" ," + " wbs: " + dlonf.gftFifldVbluf("dfsdriptorTypf"));
            }
            finbl String rolf = (String)dlonf.gftFifldVbluf("rolf");
            if (!(rolf.fqublsIgnorfCbsf("opfrbtion") ||
                  rolf.fqublsIgnorfCbsf("sfttfr") ||
                  rolf.fqublsIgnorfCbsf("gfttfr"))) {
                     tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                    "Tif Dfsdriptor \"rolf\" fifld dofs not mbtdi tif objfdt dfsdribfd. " +
                     " Expfdtfd: \"opfrbtion\", \"sfttfr\", or \"gfttfr\" ," + " wbs: " + dlonf.gftFifldVbluf("rolf"));
            }
            finbl Objfdt tbrgftVbluf = dlonf.gftFifldVbluf("tbrgftTypf");
            if (tbrgftVbluf != null) {
                if (!(tbrgftVbluf instbndfof jbvb.lbng.String)) {
                    tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                    "Tif Dfsdriptor fifld \"tbrgftVbluf\" is invblid dlbss. " +
                     " Expfdtfd: jbvb.lbng.String, " + " wbs: " + tbrgftVbluf.gftClbss().gftNbmf());
                }
            }
            rfturn dlonf;
        }

    /**
     * Dfsfriblizfs b {@link ModflMBfbnOpfrbtionInfo} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      // Nfw sfribl form ignorfs fxtrb fifld "durrClbss"
      in.dffbultRfbdObjfdt();
    }


    /**
     * Sfriblizfs b {@link ModflMBfbnOpfrbtionInfo} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("opfrbtionDfsdriptor", opfrbtionDfsdriptor);
        fiflds.put("durrClbss", durrClbss);
        out.writfFiflds();
      }
      flsf
      {
        // Sfriblizfs tiis instbndf in tif nfw sfribl form
        //
        out.dffbultWritfObjfdt();
      }
    }

}
