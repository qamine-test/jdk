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
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.DfsdriptorAddfss;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;

/**
 * <p>Tif ModflMBfbnNotifidbtionInfo objfdt dfsdribfs b notifidbtion fmittfd
 * by b ModflMBfbn.
 * It is b subdlbss of MBfbnNotifidbtionInfo witi tif bddition of bn
 * bssodibtfd Dfsdriptor bnd bn implfmfntbtion of tif Dfsdriptor intfrfbdf.</p>
 *
 * <P id="dfsdriptor">
 * Tif fiflds in tif dfsdriptor brf dffinfd, but not limitfd to, tif following.
 * Notf tibt wifn tif Typf in tiis tbblf is Numbfr, b String tibt is tif dfdimbl
 * rfprfsfntbtion of b Long dbn blso bf usfd.</P>
 *
 * <tbblf bordfr="1" dfllpbdding="5" summbry="ModflMBfbnNotifidbtionInfo Fiflds">
 * <tr><ti>Nbmf</ti><ti>Typf</ti><ti>Mfbning</ti></tr>
 * <tr><td>nbmf</td><td>String</td>
 *     <td>Notifidbtion nbmf.</td></tr>
 * <tr><td>dfsdriptorTypf</td><td>String</td>
 *     <td>Must bf "notifidbtion".</td></tr>
 * <tr><td>sfvfrity</td><td>Numbfr</td>
 *     <td>0-6 wifrf 0: unknown; 1: non-rfdovfrbblf;
 *         2: dritidbl, fbilurf; 3: mbjor, sfvfrf;
 *         4: minor, mbrginbl, frror; 5: wbrning;
 *         6: normbl, dlfbrfd, informbtivf</td></tr>
 * <tr><td>mfssbgfID</td><td>String</td>
 *     <td>Uniquf kfy for mfssbgf tfxt (to bllow trbnslbtion, bnblysis).</td></tr>
 * <tr><td>mfssbgfTfxt</td><td>String</td>
 *     <td>Tfxt of notifidbtion.</td></tr>
 * <tr><td>log</td><td>String</td>
 *     <td>T - log mfssbgf, F - do not log mfssbgf.</td></tr>
 * <tr><td>logfilf</td><td>String</td>
 *     <td>fully qublififd filf nbmf bppropribtf for opfrbting systfm.</td></tr>
 * <tr><td>visibility</td><td>Numbfr</td>
 *     <td>1-4 wifrf 1: blwbys visiblf 4: rbrfly visiblf.</td></tr>
 * <tr><td>prfsfntbtionString</td><td>String</td>
 *     <td>XML formbttfd string to bllow prfsfntbtion of dbtb.</td></tr>
 * </tbblf>
 *
 * <p>Tif dffbult dfsdriptor dontbins tif nbmf, dfsdriptorTypf,
 * displbyNbmf bnd sfvfrity(=6) fiflds.  Tif dffbult vbluf of tif nbmf
 * bnd displbyNbmf fiflds is tif nbmf of tif Notifidbtion dlbss (bs
 * spfdififd by tif <dodf>nbmf</dodf> pbrbmftfr of tif
 * ModflMBfbnNotifidbtionInfo donstrudtor).</p>
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-7445681389570207141L</dodf>.
 *
 * @sindf 1.5
 */

@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID is not donstbnt
publid dlbss ModflMBfbnNotifidbtionInfo
    fxtfnds MBfbnNotifidbtionInfo
    implfmfnts DfsdriptorAddfss {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form
    // dfpfnds on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = -5211564525059047097L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = -7445681389570207141L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("notifidbtionDfsdriptor", Dfsdriptor.dlbss),
      nfw ObjfdtStrfbmFifld("durrClbss", String.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("notifidbtionDfsdriptor", Dfsdriptor.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld notifidbtionDfsdriptor Dfsdriptor Tif dfsdriptor
     *   dontbining tif bppropribtf mftbdbtb for tiis instbndf
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
     * @sfribl Tif dfsdriptor dontbining tif bppropribtf mftbdbtb for
     *         tiis instbndf
     */
    privbtf Dfsdriptor notifidbtionDfsdriptor;

    privbtf stbtid finbl String durrClbss = "ModflMBfbnNotifidbtionInfo";

    /**
     * Construdts b ModflMBfbnNotifidbtionInfo objfdt witi b dffbult
     * dfsdriptor.
     *
     * @pbrbm notifTypfs Tif brrby of strings (in dot notbtion) dontbining
     *     tif notifidbtion typfs tibt mby bf fmittfd.
     * @pbrbm nbmf Tif nbmf of tif Notifidbtion dlbss.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif
     *     Notifidbtion. Optionbl.
     **/
    publid ModflMBfbnNotifidbtionInfo(String[] notifTypfs,
                                      String nbmf,
                                      String dfsdription) {
        tiis(notifTypfs,nbmf,dfsdription,null);
    }

    /**
     * Construdts b ModflMBfbnNotifidbtionInfo objfdt.
     *
     * @pbrbm notifTypfs Tif brrby of strings (in dot notbtion)
     *        dontbining tif notifidbtion typfs tibt mby bf fmittfd.
     * @pbrbm nbmf Tif nbmf of tif Notifidbtion dlbss.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif Notifidbtion.
     *        Optionbl.
     * @pbrbm dfsdriptor An instbndf of Dfsdriptor dontbining tif
     *        bppropribtf mftbdbtb for tiis instbndf of tif
     *        MBfbnNotifidbtionInfo. If it is null b dffbult dfsdriptor
     *        will bf drfbtfd. If tif dfsdriptor dofs not dontbin tif
     *        fiflds "displbyNbmf" or "sfvfrity",
     *        tif missing onfs brf bddfd witi tifir dffbult vblufs.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn
     *    {@link IllfgblArgumfntExdfption}. Tif dfsdriptor is invblid, or
     *    dfsdriptor fifld "nbmf" is not fqubl to pbrbmftfr nbmf, or
     *    dfsdriptor fifld "dfsdriptorTypf" is not fqubl to "notifidbtion".
     *
     **/
    publid ModflMBfbnNotifidbtionInfo(String[] notifTypfs,
                                      String nbmf,
                                      String dfsdription,
                                      Dfsdriptor dfsdriptor) {
        supfr(notifTypfs, nbmf, dfsdription);
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnNotifidbtionInfo.dlbss.gftNbmf(),
                    "ModflMBfbnNotifidbtionInfo", "Entry");
        }
        notifidbtionDfsdriptor = vblidDfsdriptor(dfsdriptor);
    }

    /**
     * Construdts b nfw ModflMBfbnNotifidbtionInfo objfdt from tiis
     * ModflMBfbnNotfidbtion Objfdt.
     *
     * @pbrbm inInfo tif ModflMBfbnNotifidbtionInfo to bf duplidbtfd
     *
     **/
    publid ModflMBfbnNotifidbtionInfo(ModflMBfbnNotifidbtionInfo inInfo) {
        tiis(inInfo.gftNotifTypfs(),
             inInfo.gftNbmf(),
             inInfo.gftDfsdription(),inInfo.gftDfsdriptor());
    }

    /**
     * Crfbtfs bnd rfturns b nfw ModflMBfbnNotifidbtionInfo wiidi is b
     * duplidbtf of tiis ModflMBfbnNotifidbtionInfo.
     **/
    publid Objfdt dlonf () {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnNotifidbtionInfo.dlbss.gftNbmf(),
                    "dlonf()", "Entry");
        }
        rfturn(nfw ModflMBfbnNotifidbtionInfo(tiis));
    }

    /**
     * Rfturns b dopy of tif bssodibtfd Dfsdriptor for tif
     * ModflMBfbnNotifidbtionInfo.
     *
     * @rfturn Dfsdriptor bssodibtfd witi tif
     * ModflMBfbnNotifidbtionInfo objfdt.
     *
     * @sff #sftDfsdriptor
     **/
    publid Dfsdriptor gftDfsdriptor() {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnNotifidbtionInfo.dlbss.gftNbmf(),
                    "gftDfsdriptor()", "Entry");
        }

        if (notifidbtionDfsdriptor == null) {
            // Dfbd dodf. Siould nfvfr ibppfn.
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                        ModflMBfbnNotifidbtionInfo.dlbss.gftNbmf(),
                        "gftDfsdriptor()", "Dfsdriptor vbluf is null, " +
                        "sftting dfsdriptor to dffbult vblufs");
            }
            notifidbtionDfsdriptor = vblidDfsdriptor(null);
        }

        rfturn((Dfsdriptor)notifidbtionDfsdriptor.dlonf());
    }

    /**
     * Sfts bssodibtfd Dfsdriptor (full rfplbdf) for tif
     * ModflMBfbnNotifidbtionInfo If tif nfw Dfsdriptor is null,
     * tifn tif bssodibtfd Dfsdriptor rfvfrts to b dffbult
     * dfsdriptor.  Tif Dfsdriptor is vblidbtfd bfforf it is
     * bssignfd.  If tif nfw Dfsdriptor is invblid, tifn b
     * RuntimfOpfrbtionsExdfption wrbpping bn
     * IllfgblArgumfntExdfption is tirown.
     *
     * @pbrbm inDfsdriptor rfplbdfs tif Dfsdriptor bssodibtfd witi tif
     * ModflMBfbnNotifidbtion intfrfbdf
     *
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn
     * {@link IllfgblArgumfntExdfption} for invblid Dfsdriptor.
     *
     * @sff #gftDfsdriptor
     **/
    publid void sftDfsdriptor(Dfsdriptor inDfsdriptor) {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnNotifidbtionInfo.dlbss.gftNbmf(),
                    "sftDfsdriptor(Dfsdriptor)", "Entry");
        }
        notifidbtionDfsdriptor = vblidDfsdriptor(inDfsdriptor);
    }

    /**
     * Rfturns b iumbn rfbdbblf string dontbining
     * ModflMBfbnNotifidbtionInfo.
     *
     * @rfturn b string dfsdribing tiis objfdt.
     **/
    publid String toString() {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINER,
                    ModflMBfbnNotifidbtionInfo.dlbss.gftNbmf(),
                    "toString()", "Entry");
        }

        finbl StringBuildfr rftStr = nfw StringBuildfr();

        rftStr.bppfnd("ModflMBfbnNotifidbtionInfo: ")
            .bppfnd(tiis.gftNbmf());

        rftStr.bppfnd(" ; Dfsdription: ")
            .bppfnd(tiis.gftDfsdription());

        rftStr.bppfnd(" ; Dfsdriptor: ")
            .bppfnd(tiis.gftDfsdriptor());

        rftStr.bppfnd(" ; Typfs: ");
        String[] nTypfs = tiis.gftNotifTypfs();
        for (int i=0; i < nTypfs.lfngti; i++) {
            if (i > 0) rftStr.bppfnd(", ");
            rftStr.bppfnd(nTypfs[i]);
        }
        rfturn rftStr.toString();
    }


    /**
     * Clonfs tif pbssfd in Dfsdriptor, sfts dffbult vblufs, bnd difdks for vblidity.
     * If tif Dfsdriptor is invblid (for instbndf by ibving tif wrong "nbmf"),
     * tiis indidbtfs progrbmming frror bnd b RuntimfOpfrbtionsExdfption will bf tirown.
     *
     * Tif following fiflds will bf dffbultfd if tify brf not blrfbdy sft:
     * dfsdriptorTypf="notifidbtion",displbyNbmf=tiis.gftNbmf(),
     * nbmf=tiis.gftNbmf(),sfvfrity="6"
     *
     *
     * @pbrbm in Dfsdriptor to bf difdkfd, or null wiidi is fquivblfnt to bn
     * fmpty Dfsdriptor.
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
            dlonf.sftFifld("nbmf", tiis.gftNbmf());
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor nbmf to " + tiis.gftNbmf());
        }
        if (dffbultfd && dlonf.gftFifldVbluf("dfsdriptorTypf")==null) {
            dlonf.sftFifld("dfsdriptorTypf", "notifidbtion");
            MODELMBEAN_LOGGER.finfr("Dffbulting dfsdriptorTypf to \"notifidbtion\"");
        }
        if (dlonf.gftFifldVbluf("displbyNbmf") == null) {
            dlonf.sftFifld("displbyNbmf",tiis.gftNbmf());
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor displbyNbmf to " + tiis.gftNbmf());
        }
        if (dlonf.gftFifldVbluf("sfvfrity") == null) {
            dlonf.sftFifld("sfvfrity", "6");
            MODELMBEAN_LOGGER.finfr("Dffbulting Dfsdriptor sfvfrity fifld to 6");
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
        if (!"notifidbtion".fqublsIgnorfCbsf((String) dlonf.gftFifldVbluf("dfsdriptorTypf"))) {
                 tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid Dfsdriptor brgumfnt"),
                "Tif Dfsdriptor \"dfsdriptorTypf\" fifld dofs not mbtdi tif objfdt dfsdribfd. " +
                 " Expfdtfd: \"notifidbtion\" ," + " wbs: " + dlonf.gftFifldVbluf("dfsdriptorTypf"));
        }

        rfturn dlonf;
    }


    /**
     * Dfsfriblizfs b {@link ModflMBfbnNotifidbtionInfo} from bn
     * {@link ObjfdtInputStrfbm}.
     **/
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
        tirows IOExdfption, ClbssNotFoundExdfption {
        // Nfw sfribl form ignorfs fxtrb fifld "durrClbss"
        in.dffbultRfbdObjfdt();
    }


    /**
     * Sfriblizfs b {@link ModflMBfbnNotifidbtionInfo} to bn
     * {@link ObjfdtOutputStrfbm}.
     **/
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
        tirows IOExdfption {
        if (dompbt) {
            // Sfriblizfs tiis instbndf in tif old sfribl form
            //
            ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
            fiflds.put("notifidbtionDfsdriptor", notifidbtionDfsdriptor);
            fiflds.put("durrClbss", durrClbss);
            out.writfFiflds();
        } flsf {
            // Sfriblizfs tiis instbndf in tif nfw sfribl form
            //
            out.dffbultWritfObjfdt();
        }
    }

}
