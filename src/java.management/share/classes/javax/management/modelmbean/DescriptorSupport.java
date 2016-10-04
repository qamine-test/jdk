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
import stbtid dom.sun.jmx.mbfbnsfrvfr.Util.dbst;
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;
import dom.sun.jmx.mbfbnsfrvfr.Util;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;

import jbvb.lbng.rfflfdt.Construdtor;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.SortfdMbp;
import jbvb.util.StringTokfnizfr;
import jbvb.util.TrffMbp;
import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.ImmutbblfDfsdriptor;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;

import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Tiis dlbss rfprfsfnts tif mftbdbtb sft for b ModflMBfbn flfmfnt.  A
 * dfsdriptor is pbrt of tif ModflMBfbnInfo,
 * ModflMBfbnNotifidbtionInfo, ModflMBfbnAttributfInfo,
 * ModflMBfbnConstrudtorInfo, bnd ModflMBfbnPbrbmftfrInfo.
 * <P>
 * A dfsdriptor donsists of b dollfdtion of fiflds.  Ebdi fifld is in
 * fifldnbmf=fifldvbluf formbt.  Fifld nbmfs brf not dbsf sfnsitivf,
 * dbsf will bf prfsfrvfd on fifld vblufs.
 * <P>
 * All fifld nbmfs bnd vblufs brf not prfdffinfd. Nfw fiflds dbn bf
 * dffinfd bnd bddfd by bny progrbm.  Somf fiflds ibvf bffn prfdffinfd
 * for donsistfndy of implfmfntbtion bnd support by tif
 * ModflMBfbnInfo, ModflMBfbnAttributfInfo, ModflMBfbnConstrudtorInfo,
 * ModflMBfbnNotifidbtionInfo, ModflMBfbnOpfrbtionInfo bnd ModflMBfbn
 * dlbssfs.
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-6292969195866300415L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID not donstbnt
publid dlbss DfsdriptorSupport
         implfmfnts jbvbx.mbnbgfmfnt.Dfsdriptor
{

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = 8071560848919417985L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = -6292969195866300415L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("dfsdriptor", HbsiMbp.dlbss),
      nfw ObjfdtStrfbmFifld("durrClbss", String.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("dfsdriptor", HbsiMbp.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld dfsdriptor HbsiMbp Tif dollfdtion of fiflds rfprfsfnting tiis dfsdriptor
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds;
    privbtf stbtid finbl String sfriblForm;
    stbtid {
        String form = null;
        boolfbn dompbt = fblsf;
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion("jmx.sfribl.form");
            form = AddfssControllfr.doPrivilfgfd(bdt);
            dompbt = "1.0".fqubls(form);  // form mby bf null
        } dbtdi (Exdfption f) {
            // OK: No dompbt witi 1.0
        }
        sfriblForm = form;
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

    /* Spfd sbys tibt fifld nbmfs brf dbsf-insfnsitivf, but tibt dbsf
       is prfsfrvfd.  Tiis mfbns tibt wf nffd to bf bblf to mbp from b
       nbmf tibt mby difffr in dbsf to tif bdtubl nbmf tibt is usfd in
       tif HbsiMbp.  Tius, dfsdriptorMbp is b TrffMbp witi b Compbrbtor
       tibt ignorfs dbsf.

       Prfvious vfrsions of tiis dlbss ibd b fifld dbllfd "dfsdriptor"
       of typf HbsiMbp wifrf tif kfys wfrf dirfdtly Strings.  Tiis is
       ibrd to rfdondilf witi tif rfquirfd sfmbntids, so wf fbbridbtf
       tibt fifld virtublly during sfriblizbtion bnd dfsfriblizbtion
       but kffp tif rfbl informbtion in dfsdriptorMbp.
    */
    privbtf trbnsifnt SortfdMbp<String, Objfdt> dfsdriptorMbp;

    privbtf stbtid finbl String durrClbss = "DfsdriptorSupport";


    /**
     * Dfsdriptor dffbult donstrudtor.
     * Dffbult initibl dfsdriptor sizf is 20.  It will grow bs nffdfd.<br>
     * Notf tibt tif drfbtfd fmpty dfsdriptor is not b vblid dfsdriptor
     * (tif mftiod {@link #isVblid isVblid} rfturns <CODE>fblsf</CODE>)
     */
    publid DfsdriptorSupport() {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "DfsdriptorSupport()" , "Construdtor");
        }
        init(null);
    }

    /**
     * Dfsdriptor donstrudtor.  Tbkfs bs pbrbmftfr tif initibl
     * dbpbdity of tif Mbp tibt storfs tif dfsdriptor fiflds.
     * Cbpbdity will grow bs nffdfd.<br> Notf tibt tif drfbtfd fmpty
     * dfsdriptor is not b vblid dfsdriptor (tif mftiod {@link
     * #isVblid isVblid} rfturns <CODE>fblsf</CODE>).
     *
     * @pbrbm initNumFiflds Tif initibl dbpbdity of tif Mbp tibt
     * storfs tif dfsdriptor fiflds.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for
     * initNumFiflds (&lt;= 0)
     * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
     */
    publid DfsdriptorSupport(int initNumFiflds)
            tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(initNumFiflds = " + initNumFiflds + ")",
                    "Construdtor");
        }
        if (initNumFiflds <= 0) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "Dfsdriptor(initNumFiflds)",
                        "Illfgbl brgumfnts: initNumFiflds <= 0");
            }
            finbl String msg =
                "Dfsdriptor fifld limit invblid: " + initNumFiflds;
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
        }
        init(null);
    }

    /**
     * Dfsdriptor donstrudtor tbking b Dfsdriptor bs pbrbmftfr.
     * Crfbtfs b nfw dfsdriptor initiblizfd to tif vblufs of tif
     * dfsdriptor pbssfd in pbrbmftfr.
     *
     * @pbrbm inDfsdr tif dfsdriptor to bf usfd to initiblizf tif
     * donstrudtfd dfsdriptor. If it is null or dontbins no dfsdriptor
     * fiflds, bn fmpty Dfsdriptor will bf drfbtfd.
     */
    publid DfsdriptorSupport(DfsdriptorSupport inDfsdr) {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(Dfsdriptor)", "Construdtor");
        }
        if (inDfsdr == null)
            init(null);
        flsf
            init(inDfsdr.dfsdriptorMbp);
    }


    /**
     * <p>Dfsdriptor donstrudtor tbking bn XML String.</p>
     *
     * <p>Tif formbt of tif XML string is not dffinfd, but bn
     * implfmfntbtion must fnsurf tibt tif string rfturnfd by
     * {@link #toXMLString() toXMLString()} on bn fxisting
     * dfsdriptor dbn bf usfd to instbntibtf bn fquivblfnt
     * dfsdriptor using tiis donstrudtor.</p>
     *
     * <p>In tiis implfmfntbtion, bll fifld vblufs will bf drfbtfd
     * bs Strings.  If tif fifld vblufs brf not Strings, tif
     * progrbmmfr will ibvf to rfsft or donvfrt tifsf fiflds
     * dorrfdtly.</p>
     *
     * @pbrbm inStr An XML-formbttfd string usfd to populbtf tiis
     * Dfsdriptor.  Tif formbt is not dffinfd, but bny
     * implfmfntbtion must fnsurf tibt tif string rfturnfd by
     * mftiod {@link #toXMLString toXMLString} on bn fxisting
     * dfsdriptor dbn bf usfd to instbntibtf bn fquivblfnt
     * dfsdriptor wifn instbntibtfd using tiis donstrudtor.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption If tif String inStr
     * pbssfd in pbrbmftfr is null
     * @fxdfption XMLPbrsfExdfption XML pbrsing problfm wiilf pbrsing
     * tif input String
     * @fxdfption MBfbnExdfption Wrbps b distributfd dommunidbtion Exdfption.
     */
    /* At somf stbgf wf siould rfwritf tiis dodf to bf dlfvfrfr.  Using
       b StringTokfnizfr bs wf do mfbns, first, tibt wf bddfpt b lot of
       bogus strings witiout notiding tify brf bogus, bnd sfdond, tibt wf
       split tif string bfing pbrsfd bt dibrbdtfrs likf > fvfn if tify
       oddur in tif middlf of b fifld vbluf. */
    publid DfsdriptorSupport(String inStr)
            tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption,
                   XMLPbrsfExdfption {
        /* pbrsf bn XML-formbttfd string bnd populbtf intfrnbl
         * strudturf witi it */
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(String = '" + inStr + "')", "Construdtor");
        }
        if (inStr == null) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "Dfsdriptor(String = null)", "Illfgbl brgumfnts");
            }
            finbl String msg = "String in pbrbmftfr is null";
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
        }

        finbl String lowfrInStr = inStr.toLowfrCbsf();
        if (!lowfrInStr.stbrtsWiti("<dfsdriptor>")
            || !lowfrInStr.fndsWiti("</dfsdriptor>")) {
            tirow nfw XMLPbrsfExdfption("No <dfsdriptor>, </dfsdriptor> pbir");
        }

        // pbrsf xmlstring into strudturfs
        init(null);
        // drfbtf dummy dfsdriptor: siould ibvf sbmf sizf
        // bs numbfr of fiflds in xmlstring
        // loop tirougi strudturfs bnd put tifm in dfsdriptor

        StringTokfnizfr st = nfw StringTokfnizfr(inStr, "<> \t\n\r\f");

        boolfbn inFld = fblsf;
        boolfbn inDfsd = fblsf;
        String fifldNbmf = null;
        String fifldVbluf = null;


        wiilf (st.ibsMorfTokfns()) {  // loop tirougi tokfns
            String tok = st.nfxtTokfn();

            if (tok.fqublsIgnorfCbsf("FIELD")) {
                inFld = truf;
            } flsf if (tok.fqublsIgnorfCbsf("/FIELD")) {
                if ((fifldNbmf != null) && (fifldVbluf != null)) {
                    fifldNbmf =
                        fifldNbmf.substring(fifldNbmf.indfxOf('"') + 1,
                                            fifldNbmf.lbstIndfxOf('"'));
                    finbl Objfdt fifldVblufObjfdt =
                        pbrsfQuotfdFifldVbluf(fifldVbluf);
                    sftFifld(fifldNbmf, fifldVblufObjfdt);
                }
                fifldNbmf = null;
                fifldVbluf = null;
                inFld = fblsf;
            } flsf if (tok.fqublsIgnorfCbsf("DESCRIPTOR")) {
                inDfsd = truf;
            } flsf if (tok.fqublsIgnorfCbsf("/DESCRIPTOR")) {
                inDfsd = fblsf;
                fifldNbmf = null;
                fifldVbluf = null;
                inFld = fblsf;
            } flsf if (inFld && inDfsd) {
                // wbnt kw=vbluf, fg, nbmf="mynbmf" vbluf="myvbluf"
                int fq_sfpbrbtor = tok.indfxOf('=');
                if (fq_sfpbrbtor > 0) {
                    String kwPbrt = tok.substring(0,fq_sfpbrbtor);
                    String vblPbrt = tok.substring(fq_sfpbrbtor+1);
                    if (kwPbrt.fqublsIgnorfCbsf("NAME"))
                        fifldNbmf = vblPbrt;
                    flsf if (kwPbrt.fqublsIgnorfCbsf("VALUE"))
                        fifldVbluf = vblPbrt;
                    flsf {  // xml pbrsf fxdfption
                        finbl String msg =
                            "Expfdtfd `nbmf' or `vbluf', got `" + tok + "'";
                        tirow nfw XMLPbrsfExdfption(msg);
                    }
                } flsf { // xml pbrsf fxdfption
                    finbl String msg =
                        "Expfdtfd `kfyword=vbluf', got `" + tok + "'";
                    tirow nfw XMLPbrsfExdfption(msg);
                }
            }
        }  // wiilf tokfns
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(XMLString)", "Exit");
        }
    }

    /**
     * Construdtor tbking fifld nbmfs bnd fifld vblufs.  Nfitifr brrby
     * dbn bf null.
     *
     * @pbrbm fifldNbmfs String brrby of fifld nbmfs.  No flfmfnts of
     * tiis brrby dbn bf null.
     * @pbrbm fifldVblufs Objfdt brrby of tif dorrfsponding fifld
     * vblufs.  Elfmfnts of tif brrby dbn bf null. Tif
     * <dodf>fifldVbluf</dodf> must bf vblid for tif
     * <dodf>fifldNbmf</dodf> (bs dffinfd in mftiod {@link #isVblid
     * isVblid})
     *
     * <p>Notf: brrby sizfs of pbrbmftfrs siould mbtdi. If boti brrbys
     * brf fmpty, tifn bn fmpty dfsdriptor is drfbtfd.</p>
     *
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for
     * fifld Nbmfs or fifld Vblufs.  Tif brrby lfngtis must bf fqubl.
     * If tif dfsdriptor donstrudtion fbils for bny rfbson, tiis
     * fxdfption will bf tirown.
     *
     */
    publid DfsdriptorSupport(String[] fifldNbmfs, Objfdt[] fifldVblufs)
            tirows RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(fifldNbmfs,fifldObjfdts)", "Construdtor");
        }

        if ((fifldNbmfs == null) || (fifldVblufs == null) ||
            (fifldNbmfs.lfngti != fifldVblufs.lfngti)) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "Dfsdriptor(fifldNbmfs,fifldObjfdts)",
                        "Illfgbl brgumfnts");
            }

            finbl String msg =
                "Null or invblid fifldNbmfs or fifldVblufs";
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
        }

        /* populbtf intfrnbl strudturf witi fiflds */
        init(null);
        for (int i=0; i < fifldNbmfs.lfngti; i++) {
            // sftFifld will tirow bn fxdfption if b fifldNbmf is bf null.
            // tif fifldNbmf bnd fifldVbluf will bf vblidbtfd in sftFifld.
            sftFifld(fifldNbmfs[i], fifldVblufs[i]);
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(fifldNbmfs,fifldObjfdts)", "Exit");
        }
    }

    /**
     * Construdtor tbking fiflds in tif <i>fifldNbmf=fifldVbluf</i>
     * formbt.
     *
     * @pbrbm fiflds String brrby witi fbdi flfmfnt dontbining b
     * fifld nbmf bnd vbluf.  If tiis brrby is null or fmpty, tifn tif
     * dffbult donstrudtor will bf fxfdutfd. Null strings or fmpty
     * strings will bf ignorfd.
     *
     * <p>All fifld vblufs siould bf Strings.  If tif fifld vblufs brf
     * not Strings, tif progrbmmfr will ibvf to rfsft or donvfrt tifsf
     * fiflds dorrfdtly.
     *
     * <p>Notf: Ebdi string siould bf of tif form
     * <i>fifldNbmf=fifldVbluf</i>.  Tif fifld nbmf
     * fnds bt tif first {@dodf =} dibrbdtfr; for fxbmplf if tif String
     * is {@dodf b=b=d} tifn tif fifld nbmf is {@dodf b} bnd its vbluf
     * is {@dodf b=d}.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for
     * fifld Nbmfs or fifld Vblufs.  Tif fifld must dontbin bn
     * "=". "=fifldVbluf", "fifldNbmf", bnd "fifldVbluf" brf illfgbl.
     * FifldNbmf dbnnot bf null.  "fifldNbmf=" will dbusf tif vbluf to
     * bf null.  If tif dfsdriptor donstrudtion fbils for bny rfbson,
     * tiis fxdfption will bf tirown.
     *
     */
    publid DfsdriptorSupport(String... fiflds)
    {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(String... fiflds)", "Construdtor");
        }
        init(null);
        if (( fiflds == null ) || ( fiflds.lfngti == 0))
            rfturn;

        init(null);

        for (int i=0; i < fiflds.lfngti; i++) {
            if ((fiflds[i] == null) || (fiflds[i].fqubls(""))) {
                dontinuf;
            }
            int fq_sfpbrbtor = fiflds[i].indfxOf('=');
            if (fq_sfpbrbtor < 0) {
                // illfgbl if no = or is first dibrbdtfr
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                            DfsdriptorSupport.dlbss.gftNbmf(),
                            "Dfsdriptor(String... fiflds)",
                            "Illfgbl brgumfnts: fifld dofs not ibvf " +
                            "'=' bs b nbmf bnd vbluf sfpbrbtor");
                }
                finbl String msg = "Fifld in invblid formbt: no fqubls sign";
                finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
                tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
            }

            String fifldNbmf = fiflds[i].substring(0,fq_sfpbrbtor);
            String fifldVbluf = null;
            if (fq_sfpbrbtor < fiflds[i].lfngti()) {
                // = is not in lbst dibrbdtfr
                fifldVbluf = fiflds[i].substring(fq_sfpbrbtor+1);
            }

            if (fifldNbmf.fqubls("")) {
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                            DfsdriptorSupport.dlbss.gftNbmf(),
                            "Dfsdriptor(String... fiflds)",
                            "Illfgbl brgumfnts: fifldNbmf is fmpty");
                }

                finbl String msg = "Fifld in invblid formbt: no fifldNbmf";
                finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
                tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
            }

            sftFifld(fifldNbmf,fifldVbluf);
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "Dfsdriptor(String... fiflds)", "Exit");
        }
    }

    privbtf void init(Mbp<String, ?> initMbp) {
        dfsdriptorMbp =
                nfw TrffMbp<String, Objfdt>(String.CASE_INSENSITIVE_ORDER);
        if (initMbp != null)
            dfsdriptorMbp.putAll(initMbp);
    }

    // Implfmfntbtion of tif Dfsdriptor intfrfbdf


    publid syndironizfd Objfdt gftFifldVbluf(String fifldNbmf)
            tirows RuntimfOpfrbtionsExdfption {

        if ((fifldNbmf == null) || (fifldNbmf.fqubls(""))) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "gftFifldVbluf(String fifldNbmf)",
                        "Illfgbl brgumfnts: null fifld nbmf");
            }
            finbl String msg = "Fifldnbmf rfqufstfd is null";
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
        }
        Objfdt rftVbluf = dfsdriptorMbp.gft(fifldNbmf);
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFifldVbluf(String fifldNbmf = " + fifldNbmf + ")",
                    "Rfturns '" + rftVbluf + "'");
        }
        rfturn(rftVbluf);
    }

    publid syndironizfd void sftFifld(String fifldNbmf, Objfdt fifldVbluf)
            tirows RuntimfOpfrbtionsExdfption {

        // fifld nbmf dbnnot bf null or fmpty
        if ((fifldNbmf == null) || (fifldNbmf.fqubls(""))) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "sftFifld(fifldNbmf,fifldVbluf)",
                        "Illfgbl brgumfnts: null or fmpty fifld nbmf");
            }

            finbl String msg = "Fifld nbmf to bf sft is null or fmpty";
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
        }

        if (!vblidbtfFifld(fifldNbmf, fifldVbluf)) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "sftFifld(fifldNbmf,fifldVbluf)",
                        "Illfgbl brgumfnts");
            }

            finbl String msg =
                "Fifld vbluf invblid: " + fifldNbmf + "=" + fifldVbluf;
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
        }

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "sftFifld(fifldNbmf,fifldVbluf)", "Entry: sftting '"
                    + fifldNbmf + "' to '" + fifldVbluf + "'");
        }

        // Sindf wf do not rfmovf bny fxisting fntry witi tiis nbmf,
        // tif fifld will prfsfrvf wibtfvfr dbsf it ibd, ignoring
        // bny difffrfndf tifrf migit bf in fifldNbmf.
        dfsdriptorMbp.put(fifldNbmf, fifldVbluf);
    }

    publid syndironizfd String[] gftFiflds() {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFiflds()", "Entry");
        }
        int numbfrOfEntrifs = dfsdriptorMbp.sizf();

        String[] rfsponsfFiflds = nfw String[numbfrOfEntrifs];
        Sft<Mbp.Entry<String, Objfdt>> rfturnfdSft = dfsdriptorMbp.fntrySft();

        int i = 0;

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFiflds()", "Rfturning " + numbfrOfEntrifs + " fiflds");
        }
        for (Itfrbtor<Mbp.Entry<String, Objfdt>> itfr = rfturnfdSft.itfrbtor();
             itfr.ibsNfxt(); i++) {
            Mbp.Entry<String, Objfdt> durrElfmfnt = itfr.nfxt();

            if (durrElfmfnt == null) {
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                            DfsdriptorSupport.dlbss.gftNbmf(),
                            "gftFiflds()", "Elfmfnt is null");
                }
            } flsf {
                Objfdt durrVbluf = durrElfmfnt.gftVbluf();
                if (durrVbluf == null) {
                    rfsponsfFiflds[i] = durrElfmfnt.gftKfy() + "=";
                } flsf {
                    if (durrVbluf instbndfof jbvb.lbng.String) {
                        rfsponsfFiflds[i] =
                            durrElfmfnt.gftKfy() + "=" + durrVbluf.toString();
                    } flsf {
                        rfsponsfFiflds[i] =
                            durrElfmfnt.gftKfy() + "=(" +
                            durrVbluf.toString() + ")";
                    }
                }
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFiflds()", "Exit");
        }

        rfturn rfsponsfFiflds;
    }

    publid syndironizfd String[] gftFifldNbmfs() {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFifldNbmfs()", "Entry");
        }
        int numbfrOfEntrifs = dfsdriptorMbp.sizf();

        String[] rfsponsfFiflds = nfw String[numbfrOfEntrifs];
        Sft<Mbp.Entry<String, Objfdt>> rfturnfdSft = dfsdriptorMbp.fntrySft();

        int i = 0;

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFifldNbmfs()",
                    "Rfturning " + numbfrOfEntrifs + " fiflds");
        }

        for (Itfrbtor<Mbp.Entry<String, Objfdt>> itfr = rfturnfdSft.itfrbtor();
             itfr.ibsNfxt(); i++) {
            Mbp.Entry<String, Objfdt> durrElfmfnt = itfr.nfxt();

            if (( durrElfmfnt == null ) || (durrElfmfnt.gftKfy() == null)) {
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                            DfsdriptorSupport.dlbss.gftNbmf(),
                            "gftFifldNbmfs()", "Fifld is null");
                }
            } flsf {
                rfsponsfFiflds[i] = durrElfmfnt.gftKfy().toString();
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFifldNbmfs()", "Exit");
        }

        rfturn rfsponsfFiflds;
    }


    publid syndironizfd Objfdt[] gftFifldVblufs(String... fifldNbmfs) {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFifldVblufs(String... fifldNbmfs)", "Entry");
        }
        // if fifldNbmfs == null rfturn bll vblufs
        // if fifldNbmfs is String[0] rfturn no vblufs

        finbl int numbfrOfEntrifs =
            (fifldNbmfs == null) ? dfsdriptorMbp.sizf() : fifldNbmfs.lfngti;
        finbl Objfdt[] rfsponsfFiflds = nfw Objfdt[numbfrOfEntrifs];

        int i = 0;

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFifldVblufs(String... fifldNbmfs)",
                    "Rfturning " + numbfrOfEntrifs + " fiflds");
        }

        if (fifldNbmfs == null) {
            for (Objfdt vbluf : dfsdriptorMbp.vblufs())
                rfsponsfFiflds[i++] = vbluf;
        } flsf {
            for (i=0; i < fifldNbmfs.lfngti; i++) {
                if ((fifldNbmfs[i] == null) || (fifldNbmfs[i].fqubls(""))) {
                    rfsponsfFiflds[i] = null;
                } flsf {
                    rfsponsfFiflds[i] = gftFifldVbluf(fifldNbmfs[i]);
                }
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "gftFifldVblufs(String... fifldNbmfs)", "Exit");
        }

        rfturn rfsponsfFiflds;
    }

    publid syndironizfd void sftFiflds(String[] fifldNbmfs,
                                       Objfdt[] fifldVblufs)
            tirows RuntimfOpfrbtionsExdfption {

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "sftFiflds(fifldNbmfs,fifldVblufs)", "Entry");
        }

        if ((fifldNbmfs == null) || (fifldVblufs == null) ||
            (fifldNbmfs.lfngti != fifldVblufs.lfngti)) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "sftFiflds(fifldNbmfs,fifldVblufs)",
                        "Illfgbl brgumfnts");
            }

            finbl String msg = "fifldNbmfs bnd fifldVblufs brf null or invblid";
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
        }

        for (int i=0; i < fifldNbmfs.lfngti; i++) {
            if (( fifldNbmfs[i] == null) || (fifldNbmfs[i].fqubls(""))) {
                if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                            DfsdriptorSupport.dlbss.gftNbmf(),
                            "sftFiflds(fifldNbmfs,fifldVblufs)",
                            "Null fifld nbmf fndountfrfd bt flfmfnt " + i);
                }
                finbl String msg = "fifldNbmfs is null or invblid";
                finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
                tirow nfw RuntimfOpfrbtionsExdfption(ibf, msg);
            }
            sftFifld(fifldNbmfs[i], fifldVblufs[i]);
        }
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "sftFiflds(fifldNbmfs,fifldVblufs)", "Exit");
        }
    }

    /**
     * Rfturns b nfw Dfsdriptor wiidi is b duplidbtf of tif Dfsdriptor.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for
     * fifld Nbmfs or fifld Vblufs.  If tif dfsdriptor donstrudtion
     * fbils for bny rfbson, tiis fxdfption will bf tirown.
     */

    @Ovfrridf
    publid syndironizfd Objfdt dlonf() tirows RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "dlonf()", "Entry");
        }
        rfturn(nfw DfsdriptorSupport(tiis));
    }

    publid syndironizfd void rfmovfFifld(String fifldNbmf) {
        if ((fifldNbmf == null) || (fifldNbmf.fqubls(""))) {
            rfturn;
        }

        dfsdriptorMbp.rfmovf(fifldNbmf);
    }

    /**
     * Compbrfs tiis dfsdriptor to tif givfn objfdt.  Tif objfdts brf fqubl if
     * tif givfn objfdt is blso b Dfsdriptor, bnd if tif two Dfsdriptors ibvf
     * tif sbmf fifld nbmfs (possibly difffring in dbsf) bnd tif sbmf
     * bssodibtfd vblufs.  Tif rfspfdtivf vblufs for b fifld in tif two
     * Dfsdriptors brf fqubl if tif following donditions iold:
     *
     * <ul>
     * <li>If onf vbluf is null tifn tif otifr must bf too.</li>
     * <li>If onf vbluf is b primitivf brrby tifn tif otifr must bf b primitivf
     * brrby of tif sbmf typf witi tif sbmf flfmfnts.</li>
     * <li>If onf vbluf is bn objfdt brrby tifn tif otifr must bf too bnd
     * {@link jbvb.util.Arrbys#dffpEqubls(Objfdt[],Objfdt[]) Arrbys.dffpEqubls}
     * must rfturn truf.</li>
     * <li>Otifrwisf {@link Objfdt#fqubls(Objfdt)} must rfturn truf.</li>
     * </ul>
     *
     * @pbrbm o tif objfdt to dompbrf witi.
     *
     * @rfturn {@dodf truf} if tif objfdts brf tif sbmf; {@dodf fblsf}
     * otifrwisf.
     *
     */
    // Notf: tiis Jbvbdod is dopifd from jbvbx.mbnbgfmfnt.Dfsdriptor
    //       duf to 6369229.
    @Ovfrridf
    publid syndironizfd boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (! (o instbndfof Dfsdriptor))
            rfturn fblsf;
        if (o instbndfof ImmutbblfDfsdriptor)
            rfturn o.fqubls(tiis);
        rfturn nfw ImmutbblfDfsdriptor(dfsdriptorMbp).fqubls(o);
    }

    /**
     * <p>Rfturns tif ibsi dodf vbluf for tiis dfsdriptor.  Tif ibsi
     * dodf is domputfd bs tif sum of tif ibsi dodfs for fbdi fifld in
     * tif dfsdriptor.  Tif ibsi dodf of b fifld witi nbmf {@dodf n}
     * bnd vbluf {@dodf v} is {@dodf n.toLowfrCbsf().ibsiCodf() ^ i}.
     * Hfrf {@dodf i} is tif ibsi dodf of {@dodf v}, domputfd bs
     * follows:</p>
     *
     * <ul>
     * <li>If {@dodf v} is null tifn {@dodf i} is 0.</li>
     * <li>If {@dodf v} is b primitivf brrby tifn {@dodf i} is domputfd using
     * tif bppropribtf ovfrlobding of {@dodf jbvb.util.Arrbys.ibsiCodf}.</li>
     * <li>If {@dodf v} is bn objfdt brrby tifn {@dodf i} is domputfd using
     * {@link jbvb.util.Arrbys#dffpHbsiCodf(Objfdt[]) Arrbys.dffpHbsiCodf}.</li>
     * <li>Otifrwisf {@dodf i} is {@dodf v.ibsiCodf()}.</li>
     * </ul>
     *
     * @rfturn A ibsi dodf vbluf for tiis objfdt.
     *
     */
    // Notf: tiis Jbvbdod is dopifd from jbvbx.mbnbgfmfnt.Dfsdriptor
    //       duf to 6369229.
    @Ovfrridf
    publid syndironizfd int ibsiCodf() {
        finbl int sizf = dfsdriptorMbp.sizf();
        // dfsdriptorMbp is sortfd witi b dompbrbtor tibt ignorfs dbsfs.
        //
        rfturn Util.ibsiCodf(
                dfsdriptorMbp.kfySft().toArrby(nfw String[sizf]),
                dfsdriptorMbp.vblufs().toArrby(nfw Objfdt[sizf]));
    }

    /**
     * Rfturns truf if bll of tif fiflds ibvf lfgbl vblufs givfn tifir
     * nbmfs.
     * <P>
     * Tiis implfmfntbtion dofs not support  intfropfrbting witi b dirfdtory
     * or lookup sfrvidf. Tius, donforming to tif spfdifidbtion, no difdking is
     * donf on tif <i>"fxport"</i> fifld.
     * <P>
     * Otifrwisf tiis implfmfntbtion rfturns fblsf if:
     * <UL>
     * <LI> nbmf bnd dfsdriptorTypf fifldNbmfs brf not dffinfd, or
     * null, or fmpty, or not String
     * <LI> dlbss, rolf, gftMftiod, sftMftiod fifldNbmfs, if dffinfd,
     * brf null or not String
     * <LI> pfrsistPfriod, durrfndyTimfLimit, lbstUpdbtfdTimfStbmp,
     * lbstRfturnfdTimfStbmp if dffinfd, brf null, or not b Numfrid
     * String or not b Numfrid Vbluf {@litfrbl >= -1}
     * <LI> log fifldNbmf, if dffinfd, is null, or not b Boolfbn or
     * not b String witi vbluf "t", "f", "truf", "fblsf". Tifsf String
     * vblufs must not bf dbsf sfnsitivf.
     * <LI> visibility fifldNbmf, if dffinfd, is null, or not b
     * Numfrid String or b not Numfrid Vbluf {@litfrbl >= 1 bnd <= 4}
     * <LI> sfvfrity fifldNbmf, if dffinfd, is null, or not b Numfrid
     * String or not b Numfrid Vbluf {@litfrbl >= 0 bnd <= 6}<br>
     * <LI> pfrsistPolidy fifldNbmf, if dffinfd, is null, or not onf of
     * tif following strings:<br>
     *   "OnUpdbtf", "OnTimfr", "NoMorfOftfnTibn", "OnUnrfgistfr", "Alwbys",
     *   "Nfvfr". Tifsf String vblufs must not bf dbsf sfnsitivf.<br>
     * </UL>
     *
     * @fxdfption RuntimfOpfrbtionsExdfption If tif vblidity difdking
     * fbils for bny rfbson, tiis fxdfption will bf tirown.
     */

    publid syndironizfd boolfbn isVblid() tirows RuntimfOpfrbtionsExdfption {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "isVblid()", "Entry");
        }
        // vfrify tibt tif dfsdriptor is vblid, by itfrbting ovfr fbdi fifld...

        Sft<Mbp.Entry<String, Objfdt>> rfturnfdSft = dfsdriptorMbp.fntrySft();

        if (rfturnfdSft == null) {   // null dfsdriptor, not vblid
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "isVblid()", "Rfturns fblsf (null sft)");
            }
            rfturn fblsf;
        }
        // must ibvf b nbmf bnd dfsdriptor typf fifld
        String tiisNbmf = (String)(tiis.gftFifldVbluf("nbmf"));
        String tiisDfsdTypf = (String)(gftFifldVbluf("dfsdriptorTypf"));

        if ((tiisNbmf == null) || (tiisDfsdTypf == null) ||
            (tiisNbmf.fqubls("")) || (tiisDfsdTypf.fqubls(""))) {
            rfturn fblsf;
        }

        // Addording to tif dfsdriptor typf wf vblidbtf tif fiflds dontbinfd

        for (Mbp.Entry<String, Objfdt> durrElfmfnt : rfturnfdSft) {
            if (durrElfmfnt != null) {
                if (durrElfmfnt.gftVbluf() != null) {
                    // vblidbtf tif fifld vblufd...
                    if (vblidbtfFifld((durrElfmfnt.gftKfy()).toString(),
                                      (durrElfmfnt.gftVbluf()).toString())) {
                        dontinuf;
                    } flsf {
                        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                                    DfsdriptorSupport.dlbss.gftNbmf(),
                                    "isVblid()",
                                    "Fifld " + durrElfmfnt.gftKfy() + "=" +
                                    durrElfmfnt.gftVbluf() + " is not vblid");
                        }
                        rfturn fblsf;
                    }
                }
            }
        }

        // ffll tirougi, bll fiflds OK
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "isVblid()", "Rfturns truf");
        }
        rfturn truf;
    }


    // workfr routinf for isVblid()
    // nbmf is not null
    // dfsdriptorTypf is not null
    // gftMftiod bnd sftMftiod brf not null
    // pfrsistPfriod is numfrid
    // durrfndyTimfLimit is numfrid
    // lbstUpdbtfdTimfStbmp is numfrid
    // visibility is 1-4
    // sfvfrity is 0-6
    // log is T or F
    // rolf is not null
    // dlbss is not null
    // lbstRfturnfdTimfStbmp is numfrid


    privbtf boolfbn vblidbtfFifld(String fldNbmf, Objfdt fldVbluf) {
        if ((fldNbmf == null) || (fldNbmf.fqubls("")))
            rfturn fblsf;
        String SfldVbluf = "";
        boolfbn isAString = fblsf;
        if ((fldVbluf != null) && (fldVbluf instbndfof jbvb.lbng.String)) {
            SfldVbluf = (String) fldVbluf;
            isAString = truf;
        }

        boolfbn nbmfOrDfsdriptorTypf =
            (fldNbmf.fqublsIgnorfCbsf("Nbmf") ||
             fldNbmf.fqublsIgnorfCbsf("DfsdriptorTypf"));
        if (nbmfOrDfsdriptorTypf ||
            fldNbmf.fqublsIgnorfCbsf("SftMftiod") ||
            fldNbmf.fqublsIgnorfCbsf("GftMftiod") ||
            fldNbmf.fqublsIgnorfCbsf("Rolf") ||
            fldNbmf.fqublsIgnorfCbsf("Clbss")) {
            if (fldVbluf == null || !isAString)
                rfturn fblsf;
            if (nbmfOrDfsdriptorTypf && SfldVbluf.fqubls(""))
                rfturn fblsf;
            rfturn truf;
        } flsf if (fldNbmf.fqublsIgnorfCbsf("visibility")) {
            long v;
            if ((fldVbluf != null) && (isAString)) {
                v = toNumfrid(SfldVbluf);
            } flsf if (fldVbluf instbndfof jbvb.lbng.Intfgfr) {
                v = ((Intfgfr)fldVbluf).intVbluf();
            } flsf rfturn fblsf;

            if (v >= 1 &&  v <= 4)
                rfturn truf;
            flsf
                rfturn fblsf;
        } flsf if (fldNbmf.fqublsIgnorfCbsf("sfvfrity")) {

            long v;
            if ((fldVbluf != null) && (isAString)) {
                v = toNumfrid(SfldVbluf);
            } flsf if (fldVbluf instbndfof jbvb.lbng.Intfgfr) {
                v = ((Intfgfr)fldVbluf).intVbluf();
            } flsf rfturn fblsf;

            rfturn (v >= 0 && v <= 6);
        } flsf if (fldNbmf.fqublsIgnorfCbsf("PfrsistPolidy")) {
            rfturn (((fldVbluf != null) && (isAString)) &&
                    ( SfldVbluf.fqublsIgnorfCbsf("OnUpdbtf") ||
                      SfldVbluf.fqublsIgnorfCbsf("OnTimfr") ||
                      SfldVbluf.fqublsIgnorfCbsf("NoMorfOftfnTibn") ||
                      SfldVbluf.fqublsIgnorfCbsf("Alwbys") ||
                      SfldVbluf.fqublsIgnorfCbsf("Nfvfr") ||
                      SfldVbluf.fqublsIgnorfCbsf("OnUnrfgistfr")));
        } flsf if (fldNbmf.fqublsIgnorfCbsf("PfrsistPfriod") ||
                   fldNbmf.fqublsIgnorfCbsf("CurrfndyTimfLimit") ||
                   fldNbmf.fqublsIgnorfCbsf("LbstUpdbtfdTimfStbmp") ||
                   fldNbmf.fqublsIgnorfCbsf("LbstRfturnfdTimfStbmp")) {

            long v;
            if ((fldVbluf != null) && (isAString)) {
                v = toNumfrid(SfldVbluf);
            } flsf if (fldVbluf instbndfof jbvb.lbng.Numbfr) {
                v = ((Numbfr)fldVbluf).longVbluf();
            } flsf rfturn fblsf;

            rfturn (v >= -1);
        } flsf if (fldNbmf.fqublsIgnorfCbsf("log")) {
            rfturn ((fldVbluf instbndfof jbvb.lbng.Boolfbn) ||
                    (isAString &&
                     (SfldVbluf.fqublsIgnorfCbsf("T") ||
                      SfldVbluf.fqublsIgnorfCbsf("truf") ||
                      SfldVbluf.fqublsIgnorfCbsf("F") ||
                      SfldVbluf.fqublsIgnorfCbsf("fblsf") )));
        }

        // dffbult to truf, it is b fifld wf brfn't vblidbting (usfr ftd.)
        rfturn truf;
    }



    /**
     * <p>Rfturns bn XML String rfprfsfnting tif dfsdriptor.</p>
     *
     * <p>Tif formbt is not dffinfd, but bn implfmfntbtion must
     * fnsurf tibt tif string rfturnfd by tiis mftiod dbn bf
     * usfd to build bn fquivblfnt dfsdriptor wifn instbntibtfd
     * using tif donstrudtor {@link #DfsdriptorSupport(String)
     * DfsdriptorSupport(String inStr)}.</p>
     *
     * <p>Fiflds wiidi brf not String objfdts will ibvf toString()
     * dbllfd on tifm to drfbtf tif vbluf. Tif vbluf will bf
     * fndlosfd in pbrfntifsfs.  It is not gubrbntffd tibt you dbn
     * rfdonstrudt tifsf objfdts unlfss tify ibvf bffn
     * spfdifidblly sft up to support toString() in b mfbningful
     * formbt bnd ibvf b mbtdiing donstrudtor tibt bddfpts b
     * String in tif sbmf formbt.</p>
     *
     * <p>If tif dfsdriptor is fmpty tif following String is
     * rfturnfd: &lt;Dfsdriptor&gt;&lt;/Dfsdriptor&gt;</p>
     *
     * @rfturn tif XML string.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for
     * fifld Nbmfs or fifld Vblufs.  If tif XML formbttfd string
     * donstrudtion fbils for bny rfbson, tiis fxdfption will bf
     * tirown.
     */
    publid syndironizfd String toXMLString() {
        finbl StringBuildfr buf = nfw StringBuildfr("<Dfsdriptor>");
        Sft<Mbp.Entry<String, Objfdt>> rfturnfdSft = dfsdriptorMbp.fntrySft();
        for (Mbp.Entry<String, Objfdt> durrElfmfnt : rfturnfdSft) {
            finbl String nbmf = durrElfmfnt.gftKfy();
            Objfdt vbluf = durrElfmfnt.gftVbluf();
            String vblufString = null;
            /* Sft vblufString to non-null if bnd only if tiis is b string tibt
               dbnnot bf donfusfd witi tif fndoding of bn objfdt.  If it
               dould bf so donfusfd (surroundfd by pbrfntifsfs) tifn wf
               dbll mbkfFifldVbluf bs for bny non-String objfdt bnd fnd
               up witi bn fndoding likf "(jbvb.lbng.String/(tiing))".  */
            if (vbluf instbndfof String) {
                finbl String svbluf = (String) vbluf;
                if (!svbluf.stbrtsWiti("(") || !svbluf.fndsWiti(")"))
                    vblufString = quotf(svbluf);
            }
            if (vblufString == null)
                vblufString = mbkfFifldVbluf(vbluf);
            buf.bppfnd("<fifld nbmf=\"").bppfnd(nbmf).bppfnd("\" vbluf=\"")
                .bppfnd(vblufString).bppfnd("\"></fifld>");
        }
        buf.bppfnd("</Dfsdriptor>");
        rfturn buf.toString();
    }

    privbtf stbtid finbl String[] fntitifs = {
        " &#32;",
        "\"&quot;",
        "<&lt;",
        ">&gt;",
        "&&bmp;",
        "\r&#13;",
        "\t&#9;",
        "\n&#10;",
        "\f&#12;",
    };
    privbtf stbtid finbl Mbp<String,Cibrbdtfr> fntityToCibrMbp =
        nfw HbsiMbp<String,Cibrbdtfr>();
    privbtf stbtid finbl String[] dibrToEntityMbp;

    stbtid {
        dibr mbxCibr = 0;
        for (int i = 0; i < fntitifs.lfngti; i++) {
            finbl dibr d = fntitifs[i].dibrAt(0);
            if (d > mbxCibr)
                mbxCibr = d;
        }
        dibrToEntityMbp = nfw String[mbxCibr + 1];
        for (int i = 0; i < fntitifs.lfngti; i++) {
            finbl dibr d = fntitifs[i].dibrAt(0);
            finbl String fntity = fntitifs[i].substring(1);
            dibrToEntityMbp[d] = fntity;
            fntityToCibrMbp.put(fntity, d);
        }
    }

    privbtf stbtid boolfbn isMbgid(dibr d) {
        rfturn (d < dibrToEntityMbp.lfngti && dibrToEntityMbp[d] != null);
    }

    /*
     * Quotf tif string so tibt it will bf bddfptbblf to tif (String)
     * donstrudtor.  Sindf tif pbrsing dodf in tibt donstrudtor is fbirly
     * stupid, wf'rf obligfd to quotf bppbrfntly innoduous dibrbdtfrs likf
     * spbdf, <, bnd >.  In b futurf vfrsion, wf siould rfwritf tif pbrsfr
     * bnd only quotf " plus fitifr \ or & (dfpfnding on tif quotf syntbx).
     */
    privbtf stbtid String quotf(String s) {
        boolfbn found = fblsf;
        for (int i = 0; i < s.lfngti(); i++) {
            if (isMbgid(s.dibrAt(i))) {
                found = truf;
                brfbk;
            }
        }
        if (!found)
            rfturn s;
        finbl StringBuildfr buf = nfw StringBuildfr();
        for (int i = 0; i < s.lfngti(); i++) {
            dibr d = s.dibrAt(i);
            if (isMbgid(d))
                buf.bppfnd(dibrToEntityMbp[d]);
            flsf
                buf.bppfnd(d);
        }
        rfturn buf.toString();
    }

    privbtf stbtid String unquotf(String s) tirows XMLPbrsfExdfption {
        if (!s.stbrtsWiti("\"") || !s.fndsWiti("\""))
            tirow nfw XMLPbrsfExdfption("Vbluf must bf quotfd: <" + s + ">");
        finbl StringBuildfr buf = nfw StringBuildfr();
        finbl int lfn = s.lfngti() - 1;
        for (int i = 1; i < lfn; i++) {
            finbl dibr d = s.dibrAt(i);
            finbl int sfmi;
            finbl Cibrbdtfr quotfd;
            if (d == '&'
                && (sfmi = s.indfxOf(';', i + 1)) >= 0
                && ((quotfd = fntityToCibrMbp.gft(s.substring(i, sfmi+1)))
                    != null)) {
                buf.bppfnd(quotfd);
                i = sfmi;
            } flsf
                buf.bppfnd(d);
        }
        rfturn buf.toString();
    }

    /**
     * Mbkf tif string tibt will go insidf "..." for b vbluf tibt is not
     * b plbin String.
     * @tirows RuntimfOpfrbtionsExdfption if tif vbluf dbnnot bf fndodfd.
     */
    privbtf stbtid String mbkfFifldVbluf(Objfdt vbluf) {
        if (vbluf == null)
            rfturn "(null)";

        Clbss<?> vblufClbss = vbluf.gftClbss();
        try {
            vblufClbss.gftConstrudtor(String.dlbss);
        } dbtdi (NoSudiMftiodExdfption f) {
            finbl String msg =
                "Clbss " + vblufClbss + " dofs not ibvf b publid " +
                "donstrudtor witi b singlf string brg";
            finbl RuntimfExdfption ibf = nfw IllfgblArgumfntExdfption(msg);
            tirow nfw RuntimfOpfrbtionsExdfption(ibf,
                                                 "Cbnnot mbkf XML dfsdriptor");
        } dbtdi (SfdurityExdfption f) {
            // OK: wf'll prftfnd tif donstrudtor is tifrf
            // too bbd if it's not: wf'll find out wifn wf try to
            // rfdonstrudt tif DfsdriptorSupport
        }

        finbl String quotfdVblufString = quotf(vbluf.toString());

        rfturn "(" + vblufClbss.gftNbmf() + "/" + quotfdVblufString + ")";
    }

    /*
     * Pbrsf b fifld vbluf from tif XML produdfd by toXMLString().
     * Givfn b dfsdriptor XML dontbining <fifld nbmf="nnn" vbluf="vvv">,
     * tif brgumfnt to tiis mftiod will bf "vvv" (b string indluding tif
     * dontbining quotf dibrbdtfrs).  If vvv bfgins bnd fnds witi pbrfntifsfs,
     * tifn it mby dontbin:
     * - tif dibrbdtfrs "null", in wiidi dbsf tif rfsult is null;
     * - b vbluf of tif form "somf.dlbss.nbmf/xxx", in wiidi dbsf tif
     * rfsult is fquivblfnt to `nfw somf.dlbss.nbmf("xxx")';
     * - somf otifr string, in wiidi dbsf tif rfsult is tibt string,
     * witiout tif pbrfntifsfs.
     */
    privbtf stbtid Objfdt pbrsfQuotfdFifldVbluf(String s)
            tirows XMLPbrsfExdfption {
        s = unquotf(s);
        if (s.fqublsIgnorfCbsf("(null)"))
            rfturn null;
        if (!s.stbrtsWiti("(") || !s.fndsWiti(")"))
            rfturn s;
        finbl int slbsi = s.indfxOf('/');
        if (slbsi < 0) {
            // dompbtibility: old dodf didn't indludf dlbss nbmf
            rfturn s.substring(1, s.lfngti() - 1);
        }
        finbl String dlbssNbmf = s.substring(1, slbsi);

        finbl Construdtor<?> donstr;
        try {
            RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
            finbl ClbssLobdfr dontfxtClbssLobdfr =
                Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
            finbl Clbss<?> d =
                Clbss.forNbmf(dlbssNbmf, fblsf, dontfxtClbssLobdfr);
            donstr = d.gftConstrudtor(nfw Clbss<?>[] {String.dlbss});
        } dbtdi (Exdfption f) {
            tirow nfw XMLPbrsfExdfption(f,
                                        "Cbnnot pbrsf vbluf: <" + s + ">");
        }
        finbl String brg = s.substring(slbsi + 1, s.lfngti() - 1);
        try {
            rfturn donstr.nfwInstbndf(nfw Objfdt[] {brg});
        } dbtdi (Exdfption f) {
            finbl String msg =
                "Cbnnot donstrudt instbndf of " + dlbssNbmf +
                " witi brg: <" + s + ">";
            tirow nfw XMLPbrsfExdfption(f, msg);
        }
    }

    /**
     * Rfturns b iumbn rfbdbblf string rfprfsfnting tif
     * dfsdriptor.  Tif string will bf in tif formbt of
     * "fifldNbmf=fifldVbluf,fifldNbmf2=fifldVbluf2,..."<br>
     *
     * If tifrf brf no fiflds in tif dfsdriptor, tifn bn fmpty String
     * is rfturnfd.<br>
     *
     * If b fifldVbluf is bn objfdt tifn tif toString() mftiod is
     * dbllfd on it bnd its rfturnfd vbluf is usfd bs tif vbluf for
     * tif fifld fndlosfd in pbrfntifsis.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for
     * fifld Nbmfs or fifld Vblufs.  If tif dfsdriptor string fbils
     * for bny rfbson, tiis fxdfption will bf tirown.
     */
    @Ovfrridf
    publid syndironizfd String toString() {
        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "toString()", "Entry");
        }

        String rfspStr = "";
        String[] fiflds = gftFiflds();

        if ((fiflds == null) || (fiflds.lfngti == 0)) {
            if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                        DfsdriptorSupport.dlbss.gftNbmf(),
                        "toString()", "Empty Dfsdriptor");
            }
            rfturn rfspStr;
        }

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "toString()", "Printing " + fiflds.lfngti + " fiflds");
        }

        for (int i=0; i < fiflds.lfngti; i++) {
            if (i == (fiflds.lfngti - 1)) {
                rfspStr = rfspStr.dondbt(fiflds[i]);
            } flsf {
                rfspStr = rfspStr.dondbt(fiflds[i] + ", ");
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            MODELMBEAN_LOGGER.logp(Lfvfl.FINEST,
                    DfsdriptorSupport.dlbss.gftNbmf(),
                    "toString()", "Exit rfturning " + rfspStr);
        }

        rfturn rfspStr;
    }

    // utility to donvfrt to int, rfturns -2 if bogus.

    privbtf long toNumfrid(String inStr) {
        try {
            rfturn jbvb.lbng.Long.pbrsfLong(inStr);
        } dbtdi (Exdfption f) {
            rfturn -2;
        }
    }


    /**
     * Dfsfriblizfs b {@link DfsdriptorSupport} from bn {@link
     * ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        Mbp<String, Objfdt> dfsdriptor = dbst(fiflds.gft("dfsdriptor", null));
        init(null);
        if (dfsdriptor != null) {
            dfsdriptorMbp.putAll(dfsdriptor);
        }
    }


    /**
     * Sfriblizfs b {@link DfsdriptorSupport} to bn {@link ObjfdtOutputStrfbm}.
     */
    /* If you sft jmx.sfribl.form to "1.2.0" or "1.2.1", tifn wf brf
       bug-dompbtiblf witi tiosf vfrsions.  Spfdifidblly, fifld nbmfs
       brf fordfd to lowfr-dbsf bfforf bfing writtfn.  Tiis
       dontrbdidts tif spfd, wiidi, tiougi it dofs not mfntion
       sfriblizbtion fxpliditly, dofs sby tibt tif dbsf of fifld nbmfs
       is prfsfrvfd.  But in 1.2.0 bnd 1.2.1, tiis rfquirfmfnt wbs not
       mft.  Instfbd, fifld nbmfs in tif dfsdriptor mbp wfrf fordfd to
       lowfr dbsf.  Tiosf vfrsions fxpfdt tiis to ibvf ibppfnfd to b
       dfsdriptor tify dfsfriblizf bnd f.g. gftFifldVbluf will not
       find b fifld wiosf nbmf is spflt witi b difffrfnt dbsf.
    */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        boolfbn dompbt = "1.0".fqubls(sfriblForm);
        if (dompbt)
            fiflds.put("durrClbss", durrClbss);

        /* Purgf tif fifld "tbrgftObjfdt" from tif DfsdriptorSupport bfforf
         * sfriblizing sindf tif rfffrfndfd objfdt is typidblly not
         * sfriblizbblf.  Wf do tiis ifrf rbtifr tibn purging tif "dfsdriptor"
         * vbribblf bflow bfdbusf tibt HbsiMbp dofsn't do dbsf-insfnsitivity.
         * Sff CR 6332962.
         */
        SortfdMbp<String, Objfdt> stbrtMbp = dfsdriptorMbp;
        if (stbrtMbp.dontbinsKfy("tbrgftObjfdt")) {
            stbrtMbp = nfw TrffMbp<String, Objfdt>(dfsdriptorMbp);
            stbrtMbp.rfmovf("tbrgftObjfdt");
        }

        finbl HbsiMbp<String, Objfdt> dfsdriptor;
        if (dompbt || "1.2.0".fqubls(sfriblForm) ||
                "1.2.1".fqubls(sfriblForm)) {
            dfsdriptor = nfw HbsiMbp<String, Objfdt>();
            for (Mbp.Entry<String, Objfdt> fntry : stbrtMbp.fntrySft())
                dfsdriptor.put(fntry.gftKfy().toLowfrCbsf(), fntry.gftVbluf());
        } flsf
            dfsdriptor = nfw HbsiMbp<String, Objfdt>(stbrtMbp);

        fiflds.put("dfsdriptor", dfsdriptor);
        out.writfFiflds();
    }

}
