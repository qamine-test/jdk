/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.snmp.bgfnt;

import jbvb.io.Sfriblizbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.logging.Lfvfl;
import jbvb.util.Vfdtor;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MblformfdObjfdtNbmfExdfption;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

/**
 * Abstrbdt dlbss for rfprfsfnting bn SNMP MIB.
 * <P>
 * Wifn dompiling b SNMP MIB, bmong bll tif dlbssfs gfnfrbtfd by
 * <CODE>mibgfn</CODE>, tifrf is onf wiidi fxtfnds <CODE>SnmpMib</CODE>
 * for rfprfsfnting b wiolf MIB.
 * <BR>Tif dlbss is usfd by tif SNMP protodol bdbptor bs tif fntry point in
 * tif MIB.
 *
 * <p>Tiis gfnfrbtfd dlbss dbn bf subdlbssfd in your dodf in ordfr to
 * plug in your own spfdifid bfibviour.
 * </p>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpMib fxtfnds SnmpMibAgfnt implfmfnts Sfriblizbblf {

    /**
     * Dffbult donstrudtor.
     * Initiblizfs tif OID trff.
     */
    publid SnmpMib() {
        root= nfw SnmpMibOid();
    }


    // --------------------------------------------------------------------
    // POLYMORHIC METHODS
    // --------------------------------------------------------------------

    /**
     * <p>
     * Tiis dbllbbdk siould rfturn tif OID bssodibtfd to tif group
     * idfntififd by tif givfn <dodf>groupNbmf</dodf>.
     * </p>
     *
     * <p>
     * Tiis mftiod is providfd bs b iook to plug-in somf dustom
     * spfdifid bfibvior. Altiougi doing so is disdourbgfd you migit
     * wbnt to subdlbss tiis mftiod in ordfr to storf & providf morf mftbdbtb
     * informbtion (mbpping OID <-> symbolid nbmf) witiin tif bgfnt,
     * or to "dibngf" tif root of tif MIB OID by prffixing tif
     * dffbultOid by bn bpplidbtion dfpfndbnt OID string, for instbndf.
     * </p>
     *
     * <p>
     * Tif dffbult implfmfntbtion of tiis mftiod is to rfturn tif givfn
     * <dodf>dffbultOid</dodf>
     * </p>
     *
     * @pbrbm groupNbmf   Tif jbvb-izfd nbmf of tif SNMP group.
     * @pbrbm dffbultOid  Tif OID dffinfd in tif MIB for tibt group
     *                    (in dot notbtion).
     *
     * @rfturn Tif OID of tif group idfntififd by <dodf>groupNbmf</dodf>,
     *         in dot-notbtion.
     */
    protfdtfd String gftGroupOid(String groupNbmf, String dffbultOid) {
        rfturn dffbultOid;
    }

    /**
     * <p>
     * Tiis dbllbbdk siould rfturn tif ObjfdtNbmf bssodibtfd to tif
     * group idfntififd by tif givfn <dodf>groupNbmf</dodf>.
     * </p>
     *
     * <p>
     * Tiis mftiod is providfd bs b iook to plug-in somf dustom
     * spfdifid bfibvior. You migit wbnt to ovfrridf tiis mftiod
     * in ordfr to providf b difffrfnt objfdt nbming sdifmf tibn
     * tibt proposfd by dffbult by <dodf>mibgfn</dodf>.
     * </p>
     *
     * <p>
     * Tiis mftiod is only mfbningful if tif MIB is rfgistfrfd
     * in tif MBfbnSfrvfr, otifrwisf, it will not bf dbllfd.
     * </p>
     *
     * <p>
     * Tif dffbult implfmfntbtion of tiis mftiod is to rfturn bn ObjfdtNbmf
     * built from tif givfn <dodf>dffbultNbmf</dodf>.
     * </p>
     *
     * @pbrbm nbmf  Tif jbvb-izfd nbmf of tif SNMP group.
     * @pbrbm oid   Tif OID rfturnfd by gftGroupOid() - in dot notbtion.
     * @pbrbm dffbultNbmf Tif nbmf by dffbult gfnfrbtfd by <dodf>
     *                    mibgfn</dodf>
     *
     * @rfturn Tif ObjfdtNbmf of tif group idfntififd by <dodf>nbmf</dodf>
     */
    protfdtfd ObjfdtNbmf gftGroupObjfdtNbmf(String nbmf, String oid,
                                            String dffbultNbmf)
        tirows MblformfdObjfdtNbmfExdfption {
        rfturn nfw ObjfdtNbmf(dffbultNbmf);
    }

    /**
     * <p>
     * Rfgistfr bn SNMP group bnd its mftbdbtb nodf in tif MIB.
     * </p>
     *
     * <p>
     * Tiis mftiod is providfd bs b iook to plug-in somf dustom
     * spfdifid bfibvior. You migit wbnt to ovfrridf tiis mftiod
     * if you wbnt to sft spfdibl links bftwffn tif MBfbn, its mftbdbtb
     * nodf, its OID or ObjfdtNbmf ftd..
     * </p>
     *
     * <p>
     * If tif MIB is not rfgistfrfd in tif MBfbnSfrvfr, tif <dodf>
     * sfrvfr</dodf> bnd <dodf>groupObjNbmf</dodf> pbrbmftfrs will bf
     * <dodf>null</dodf>.<br>
     * If tif givfn group MBfbn is not <dodf>null</dodf>, bnd if tif
     * <dodf>sfrvfr</dodf> bnd <dodf>groupObjNbmf</dodf> pbrbmftfrs brf
     * not null, tifn tiis mftiod will blso butombtidblly rfgistfr tif
     * group MBfbn witi tif givfn MBfbnSfrvfr <dodf>sfrvfr</dodf>.
     * </p>
     *
     * @pbrbm groupNbmf  Tif jbvb-izfd nbmf of tif SNMP group.
     * @pbrbm groupOid   Tif OID bs rfturnfd by gftGroupOid() - in dot
     *                   notbtion.
     * @pbrbm groupObjNbmf Tif ObjfdtNbmf bs rfturnfd by gftGroupObjfdtNbmf().
     *                   Tiis pbrbmftfr mby bf <dodf>null</dodf> if tif
     *                   MIB is not rfgistfrfd in tif MBfbnSfrvfr.
     * @pbrbm nodf       Tif mftbdbtb nodf, bs rfturnfd by tif mftbdbtb
     *                   fbdtory mftiod for tiis group.
     * @pbrbm group      Tif MBfbn for tiis group, bs rfturnfd by tif
     *                   MBfbn fbdtory mftiod for tiis group.
     * @pbrbm sfrvfr     Tif MBfbnSfrvfr in wiidi tif groups brf to bf
     *                   rfgistfrfd. Tiis pbrbmftfr will bf <dodf>null</dodf>
     *                   if tif MIB is not rfgistfrfd, otifrwisf it is b
     *                   rfffrfndf to tif MBfbnSfrvfr in wiidi tif MIB is
     *                   rfgistfrfd.
     *
     */
    protfdtfd void rfgistfrGroupNodf(String groupNbmf,   String groupOid,
                                     ObjfdtNbmf groupObjNbmf, SnmpMibNodf nodf,
                                     Objfdt group, MBfbnSfrvfr sfrvfr)
        tirows NotComplibntMBfbnExdfption, MBfbnRfgistrbtionExdfption,
        InstbndfAlrfbdyExistsExdfption, IllfgblAddfssExdfption {
        root.rfgistfrNodf(groupOid,nodf);
        if (sfrvfr != null && groupObjNbmf != null && group != null)
            sfrvfr.rfgistfrMBfbn(group,groupObjNbmf);
    }

    /**
     * <p>
     * Rfgistfr bn SNMP Tbblf mftbdbtb nodf in tif MIB.
     * </p>
     *
     * <p>
     * <b><i>
     * Tiis mftiod is usfd intfrnblly bnd you siould nfvfr nffd to
     * dbll it dirfdtly.</i></b><br> It is usfd to fstbblisi tif link
     * bftwffn bn SNMP tbblf mftbdbtb nodf bnd its bfbn-likf dountfrpbrt.
     * <br>
     * Tif group mftbdbtb nodfs will drfbtf bnd rfgistfr tifir
     * undfrlying tbblf mftbdbtb nodfs in tif MIB using tiis
     * mftiod. <br>
     * Tif mftbdbtb nodfs will bf lbtfr rftrifvfd from tif MIB by tif
     * bfbn-likf tbblf objfdts using tif gftRfgistfrTbblfMftb() mftiod.
     * </p>
     *
     * @pbrbm nbmf      Tif jbvb-izfd nbmf of tif SNMP tbblf.
     * @pbrbm tbblf     Tif SNMP tbblf mftbdbtb nodf - usublly tiis
     *                  dorrfsponds to b <dodf>mibgfn</dodf> gfnfrbtfd
     *                  objfdt.
     */
    publid bbstrbdt void rfgistfrTbblfMftb(String nbmf, SnmpMibTbblf tbblf);

    /**
     * Rfturns b rfgistfrfd SNMP Tbblf mftbdbtb nodf.
     *
     * <p><b><i>
     * Tiis mftiod is usfd intfrnblly bnd you siould nfvfr nffd to
     * dbll it dirfdtly.
     * </i></b></p>
     *
     */
    publid bbstrbdt SnmpMibTbblf gftRfgistfrfdTbblfMftb(String nbmf);

    // --------------------------------------------------------------------
    // PUBLIC METHODS
    // --------------------------------------------------------------------

    /**
     * Prodfssfs b <CODE>gft</CODE> opfrbtion.
     *
     **/
    // Implfmfnts tif mftiod dffinfd in SnmpMibAgfnt. Sff SnmpMibAgfnt
    // for jbvb-dod
    //
    @Ovfrridf
    publid void gft(SnmpMibRfqufst rfq) tirows SnmpStbtusExdfption {

        // Builds tif rfqufst trff: drfbtion is not bllowfd, opfrbtion
        // is not btomid.

        finbl int rfqTypf = SnmpDffinitions.pduGftRfqufstPdu;
        SnmpRfqufstTrff ibndlfrs = gftHbndlfrs(rfq,fblsf,fblsf,rfqTypf);

        SnmpRfqufstTrff.Hbndlfr i = null;
        SnmpMibNodf mftb = null;

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMib.dlbss.gftNbmf(),
                    "gft", "Prodfssing ibndlfrs for GET... ");
        }

        // For fbdi sub-rfqufst storfd in tif rfqufst-trff, invokf tif
        // gft() mftiod.
        for (Enumfrbtion<SnmpRfqufstTrff.Hbndlfr> fi=ibndlfrs.gftHbndlfrs();fi.ibsMorfElfmfnts();) {
            i = fi.nfxtElfmfnt();

            // Gfts tif Mftb nodf. It dbn bf fitifr b Group Mftb or b
            // Tbblf Mftb.
            //
            mftb = ibndlfrs.gftMftbNodf(i);

            // Gfts tif dfpti of tif Mftb nodf in tif OID trff
            finbl int dfpti = ibndlfrs.gftOidDfpti(i);

            for (Enumfrbtion<SnmpMibSubRfqufst> rqs=ibndlfrs.gftSubRfqufsts(i);
                 rqs.ibsMorfElfmfnts();) {

                // Invokf tif gft() opfrbtion.
                mftb.gft(rqs.nfxtElfmfnt(),dfpti);
            }
        }
    }

    /**
     * Prodfssfs b <CODE>sft</CODE> opfrbtion.
     *
     */
    // Implfmfnts tif mftiod dffinfd in SnmpMibAgfnt. Sff SnmpMibAgfnt
    // for jbvb-dod
    //
    @Ovfrridf
    publid void sft(SnmpMibRfqufst rfq) tirows SnmpStbtusExdfption {

        SnmpRfqufstTrff ibndlfrs = null;

        // Optimizbtion: wf'rf going to gft tif wiolf SnmpRfqufstTrff
        // built in tif "difdk" mftiod, so tibt wf don't ibvf to rfbuild
        // it ifrf.
        //
        if (rfq instbndfof SnmpMibRfqufstImpl)
            ibndlfrs = ((SnmpMibRfqufstImpl)rfq).gftRfqufstTrff();

        // Optimizbtion didn't work: wf ibvf to rfbuild tif trff.
        //
        // Builds tif rfqufst trff: drfbtion is not bllowfd, opfrbtion
        // is btomid.
        //
        finbl int rfqTypf = SnmpDffinitions.pduSftRfqufstPdu;
        if (ibndlfrs == null) ibndlfrs = gftHbndlfrs(rfq,fblsf,truf,rfqTypf);
        ibndlfrs.switdiCrfbtionFlbg(fblsf);
        ibndlfrs.sftPduTypf(rfqTypf);

        SnmpRfqufstTrff.Hbndlfr i;
        SnmpMibNodf mftb;

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMib.dlbss.gftNbmf(),
                    "sft", "Prodfssing ibndlfrs for SET... ");
        }

        // For fbdi sub-rfqufst storfd in tif rfqufst-trff, invokf tif
        // gft() mftiod.
        for (Enumfrbtion<SnmpRfqufstTrff.Hbndlfr> fi=ibndlfrs.gftHbndlfrs();fi.ibsMorfElfmfnts();) {
            i = fi.nfxtElfmfnt();

            // Gfts tif Mftb nodf. It dbn bf fitifr b Group Mftb or b
            // Tbblf Mftb.
            //
            mftb = ibndlfrs.gftMftbNodf(i);

            // Gfts tif dfpti of tif Mftb nodf in tif OID trff
            finbl int dfpti = ibndlfrs.gftOidDfpti(i);

            for (Enumfrbtion<SnmpMibSubRfqufst> rqs=ibndlfrs.gftSubRfqufsts(i);
                 rqs.ibsMorfElfmfnts();) {

                // Invokf tif sft() opfrbtion
                mftb.sft(rqs.nfxtElfmfnt(),dfpti);
            }
        }
    }

    /**
     * Cifdks if b <CODE>sft</CODE> opfrbtion dbn bf pfrformfd.
     * If tif opfrbtion dbnnot bf pfrformfd, tif mftiod will rbisf b
     * <CODE>SnmpStbtusExdfption</CODE>.
     *
     */
    // Implfmfnts tif mftiod dffinfd in SnmpMibAgfnt. Sff SnmpMibAgfnt
    // for jbvb-dod
    //
    @Ovfrridf
    publid void difdk(SnmpMibRfqufst rfq) tirows SnmpStbtusExdfption {

        finbl int rfqTypf = SnmpDffinitions.pduWblkRfqufst;
        // Builds tif rfqufst trff: drfbtion is bllowfd, opfrbtion
        // is btomid.
        SnmpRfqufstTrff ibndlfrs = gftHbndlfrs(rfq,truf,truf,rfqTypf);

        SnmpRfqufstTrff.Hbndlfr i;
        SnmpMibNodf mftb;

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMib.dlbss.gftNbmf(),
                    "difdk", "Prodfssing ibndlfrs for CHECK... ");
        }

        // For fbdi sub-rfqufst storfd in tif rfqufst-trff, invokf tif
        // difdk() mftiod.
        for (Enumfrbtion<SnmpRfqufstTrff.Hbndlfr> fi=ibndlfrs.gftHbndlfrs();fi.ibsMorfElfmfnts();) {
            i = fi.nfxtElfmfnt();

            // Gfts tif Mftb nodf. It dbn bf fitifr b Group Mftb or b
            // Tbblf Mftb.
            //
            mftb = ibndlfrs.gftMftbNodf(i);

            // Gfts tif dfpti of tif Mftb nodf in tif OID trff
            finbl int dfpti = ibndlfrs.gftOidDfpti(i);

            for (Enumfrbtion<SnmpMibSubRfqufst> rqs=ibndlfrs.gftSubRfqufsts(i);
                 rqs.ibsMorfElfmfnts();) {

                // Invokf tif difdk() opfrbtion
                mftb.difdk(rqs.nfxtElfmfnt(),dfpti);
            }
        }

        // Optimizbtion: wf'rf going to pbss tif wiolf SnmpRfqufstTrff
        // to tif "sft" mftiod, so tibt wf don't ibvf to rfbuild it tifrf.
        //
        if (rfq instbndfof SnmpMibRfqufstImpl) {
            ((SnmpMibRfqufstImpl)rfq).sftRfqufstTrff(ibndlfrs);
        }

    }

    /**
     * Prodfssfs b <CODE>gftNfxt</CODE> opfrbtion.
     *
     */
    // Implfmfnts tif mftiod dffinfd in SnmpMibAgfnt. Sff SnmpMibAgfnt
    // for jbvb-dod
    //
    @Ovfrridf
    publid void gftNfxt(SnmpMibRfqufst rfq) tirows SnmpStbtusExdfption {
        // Build tif rfqufst trff for tif opfrbtion
        // Tif subrfqufst storfd in tif rfqufst trff brf vblid GET rfqufsts
        SnmpRfqufstTrff ibndlfrs = gftGftNfxtHbndlfrs(rfq);

        SnmpRfqufstTrff.Hbndlfr i;
        SnmpMibNodf mftb;

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMib.dlbss.gftNbmf(),
                    "gftNfxt", "Prodfssing ibndlfrs for GET-NEXT... ");
        }

        // Now invokf gft() for fbdi subrfqufst of tif rfqufst trff.
        for (Enumfrbtion<SnmpRfqufstTrff.Hbndlfr> fi=ibndlfrs.gftHbndlfrs();fi.ibsMorfElfmfnts();) {
            i = fi.nfxtElfmfnt();

            // Gfts tif Mftb nodf. It dbn bf fitifr b Group Mftb or b
            // Tbblf Mftb.
            //
            mftb = ibndlfrs.gftMftbNodf(i);

            // Gfts tif dfpti of tif Mftb nodf in tif OID trff
            int dfpti = ibndlfrs.gftOidDfpti(i);

            for (Enumfrbtion<SnmpMibSubRfqufst> rqs=ibndlfrs.gftSubRfqufsts(i);
                 rqs.ibsMorfElfmfnts();) {

                // Invokf tif gft() opfrbtion
                mftb.gft(rqs.nfxtElfmfnt(),dfpti);
            }
        }
    }


    /**
     * Prodfssfs b <CODE>gftBulk</CODE> opfrbtion.
     * Tif mftiod implfmfnts tif <CODE>gftBulk</CODE> opfrbtion by dblling
     * bppropribtfly tif <CODE>gftNfxt</CODE> mftiod.
     *
     */
    // Implfmfnts tif mftiod dffinfd in SnmpMibAgfnt. Sff SnmpMibAgfnt
    // for jbvb-dod
    //
    @Ovfrridf
    publid void gftBulk(SnmpMibRfqufst rfq, int nonRfpfbt, int mbxRfpfbt)
        tirows SnmpStbtusExdfption {

        gftBulkWitiGftNfxt(rfq, nonRfpfbt, mbxRfpfbt);
    }

    /**
     * Gfts tif root objfdt idfntififr of tif MIB.
     * <P>In ordfr to bf bddurbtf, tif mftiod siould bf dbllfd ondf tif
     * MIB is fully initiblizfd (tibt is, bftfr b dbll to <CODE>init</CODE>
     * or <CODE>prfRfgistfr</CODE>).
     *
     * @rfturn Tif root objfdt idfntififr.
     */
    @Ovfrridf
    publid long[] gftRootOid() {

        if( rootOid == null) {
            Vfdtor<Intfgfr> list= nfw Vfdtor<>(10);

            // Ask tif trff to do tif job !
            //
            root.gftRootOid(list);

            // Now formbt tif rfsult
            //
            rootOid= nfw long[list.sizf()];
            int i=0;
            for(Enumfrbtion<Intfgfr> f= list.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                Intfgfr vbl= f.nfxtElfmfnt();
                rootOid[i++]= vbl.longVbluf();
            }
        }
        rfturn rootOid.dlonf();
    }

    // --------------------------------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------------------------------

    /**
     * Tiis mftiod builds tif tfmporbry rfqufst-trff tibt will bf usfd to
     * pfrform tif SNMP rfqufst bssodibtfd witi tif givfn vfdtor of vbrbinds
     * `list'.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst objfdt iolding tif vbrbind list
     *             dondfrning tiis MIB.
     * @pbrbm drfbtfflbg Indidbtfs wiftifr tif opfrbtion bllow for drfbtion
     *        of nfw instbndfs (if: it is b SET).
     * @pbrbm btomid Indidbtfs wiftifr tif opfrbtion is btomid or not.
     * @pbrbm typf Rfqufst typf (from SnmpDffinitions).
     *
     * @rfturn Tif rfqufst-trff wifrf tif originbl vbrbind list ibs bffn
     *         dispbtdifd to tif bppropribtf nodfs.
     */
    privbtf SnmpRfqufstTrff gftHbndlfrs(SnmpMibRfqufst rfq,
                                        boolfbn drfbtfflbg, boolfbn btomid,
                                        int typf)
        tirows SnmpStbtusExdfption {

        // Build bn fmpty rfqufst trff
        SnmpRfqufstTrff ibndlfrs =
            nfw SnmpRfqufstTrff(rfq,drfbtfflbg,typf);

        int indfx=0;
        SnmpVbrBind vbr;
        finbl int vfr= rfq.gftVfrsion();

        // For fbdi vbrbind in tif list finds its ibndling nodf.
        for (Enumfrbtion<SnmpVbrBind> f= rfq.gftElfmfnts(); f.ibsMorfElfmfnts(); indfx++) {

            vbr= f.nfxtElfmfnt();

            try {
                // Find tif ibndling nodf for tiis vbrbind.
                root.findHbndlingNodf(vbr,vbr.oid.longVbluf(fblsf),
                                      0,ibndlfrs);
            } dbtdi(SnmpStbtusExdfption x) {

                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMib.dlbss.gftNbmf(),
                            "gftHbndlfrs",
                            "Couldn't find b ibndling nodf for " +
                            vbr.oid.toString());
                }

                // If tif opfrbtion is btomid (Cifdk/Sft) or tif vfrsion
                // is V1 wf must gfnfrbtf bn fxdfption.
                //
                if (vfr == SnmpDffinitions.snmpVfrsionOnf) {

                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "gftHbndlfrs", "\tV1: Tirowing fxdfption");
                    }

                    // Tif indfx in tif fxdfption must dorrfspond to tif
                    // SNMP indfx ...
                    //
                    finbl SnmpStbtusExdfption ssf =
                        nfw SnmpStbtusExdfption(x, indfx + 1);
                    ssf.initCbusf(x);
                    tirow ssf;
                } flsf if ((typf == SnmpDffinitions.pduWblkRfqufst)   ||
                           (typf == SnmpDffinitions.pduSftRfqufstPdu)) {
                    finbl int stbtus =
                        SnmpRfqufstTrff.mbpSftExdfption(x.gftStbtus(),vfr);

                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "gftHbndlfrs", "\tSET: Tirowing fxdfption");
                    }

                    finbl SnmpStbtusExdfption ssf =
                        nfw SnmpStbtusExdfption(stbtus, indfx + 1);
                    ssf.initCbusf(x);
                    tirow ssf;
                } flsf if (btomid) {

                    // Siould nfvfr domf ifrf...
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "gftHbndlfrs", "\tATOMIC: Tirowing fxdfption");
                    }

                    finbl SnmpStbtusExdfption ssf =
                        nfw SnmpStbtusExdfption(x, indfx + 1);
                    ssf.initCbusf(x);
                    tirow ssf;
                }

                finbl int stbtus =
                    SnmpRfqufstTrff.mbpGftExdfption(x.gftStbtus(),vfr);

                if (stbtus == SnmpStbtusExdfption.noSudiInstbndf) {

                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "gftHbndlfrs",
                                "\tGET: Rfgistfring noSudiInstbndf");
                    }

                    vbr.vbluf= SnmpVbrBind.noSudiInstbndf;

                } flsf if (stbtus == SnmpStbtusExdfption.noSudiObjfdt) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "gftHbndlfrs",
                                "\tGET: Rfgistfring noSudiObjfdt");
                    }

                        vbr.vbluf= SnmpVbrBind.noSudiObjfdt;

                } flsf {

                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "gftHbndlfrs",
                                "\tGET: Rfgistfring globbl frror: " + stbtus);
                    }

                    finbl SnmpStbtusExdfption ssf =
                        nfw SnmpStbtusExdfption(stbtus, indfx + 1);
                    ssf.initCbusf(x);
                    tirow ssf;
                }
            }
        }
        rfturn ibndlfrs;
    }

    /**
     * Tiis mftiod builds tif tfmporbry rfqufst-trff tibt will bf usfd to
     * pfrform tif SNMP GET-NEXT rfqufst bssodibtfd witi tif givfn vfdtor
     * of vbrbinds `list'.
     *
     * @pbrbm rfq Tif SnmpMibRfqufst objfdt iolding tif vbrbind list
     *             dondfrning tiis MIB.
     *
     * @rfturn Tif rfqufst-trff wifrf tif originbl vbrbind list ibs bffn
     *         dispbtdifd to tif bppropribtf nodfs, bnd wifrf tif originbl
     *         OIDs ibvf bffn rfplbdfd witi tif dorrfdt "nfxt" OID.
     */
    privbtf SnmpRfqufstTrff gftGftNfxtHbndlfrs(SnmpMibRfqufst rfq)
        tirows SnmpStbtusExdfption {

        // Crfbtfs bn fmpty rfqufst trff, no fntry drfbtion is bllowfd (fblsf)
        SnmpRfqufstTrff ibndlfrs = nfw
            SnmpRfqufstTrff(rfq,fblsf,SnmpDffinitions.pduGftNfxtRfqufstPdu);

        // Sfts tif gftNfxt flbg: if vfrsion=V2, stbtus fxdfption brf
        // trbnsformfd in  fndOfMibVifw
        ibndlfrs.sftGftNfxtFlbg();

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMib.dlbss.gftNbmf(),
                    "gftGftNfxtHbndlfrs", "Rfdfivfd MIB rfqufst : " + rfq);
        }
        AdmCifdkfr difdkfr = nfw AdmCifdkfr(rfq);
        int indfx=0;
        SnmpVbrBind vbr = null;
        finbl int vfr= rfq.gftVfrsion();
        SnmpOid originbl = null;
        // For fbdi vbrbind, finds tif ibndling nodf.
        // Tiis fundtion ibs tif sidf ffffdt of trbnsforming b GET-NEXT
        // rfqufst into b vblid GET rfqufst, rfplbding tif OIDs in tif
        // originbl GET-NEXT rfqufst witi tif OID of tif first lfbf tibt
        // follows.
        for (Enumfrbtion<SnmpVbrBind> f= rfq.gftElfmfnts(); f.ibsMorfElfmfnts(); indfx++) {

            vbr = f.nfxtElfmfnt();
            SnmpOid rfsult;
            try {
                // Find tif nodf ibndling tif OID tibt follows tif vbrbind
                // OID. `rfsult' dontbins tiis nfxt lfbf OID.
                //ACM loop.
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMib.dlbss.gftNbmf(),
                            "gftGftNfxtHbndlfrs", " Nfxt OID of : " + vbr.oid);
                }
                rfsult = nfw SnmpOid(root.findNfxtHbndlingNodf
                                     (vbr,vbr.oid.longVbluf(fblsf),0,
                                      0,ibndlfrs, difdkfr));

                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMib.dlbss.gftNbmf(),
                            "gftGftNfxtHbndlfrs", " is : " + rfsult);
                }
                // Wf rfplbdf tif vbrbind originbl OID witi tif OID of tif
                // lfbf objfdt wf ibvf to rfturn.
                vbr.oid = rfsult;
            } dbtdi(SnmpStbtusExdfption x) {

                // if (isDfbugOn())
                //    dfbug("gftGftNfxtHbndlfrs",
                //        "Couldn't find b ibndling nodf for "
                //        + vbr.oid.toString());

                if (vfr == SnmpDffinitions.snmpVfrsionOnf) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "gftGftNfxtHbndlfrs",
                                "\tTirowing fxdfption " + x.toString());
                    }
                    // Tif indfx in tif fxdfption must dorrfspond to tif
                    // SNMP indfx ...
                    //
                    tirow nfw SnmpStbtusExdfption(x, indfx + 1);
                }
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMib.dlbss.gftNbmf(),
                            "gftGftNfxtHbndlfrs",
                            "Exdfption : " + x.gftStbtus());
                }

                vbr.sftSnmpVbluf(SnmpVbrBind.fndOfMibVifw);
            }
        }
        rfturn ibndlfrs;
    }

    // --------------------------------------------------------------------
    // PROTECTED VARIABLES
    // --------------------------------------------------------------------

    /**
     * Tif top flfmfnt in tif Mib trff.
     * @sfribl
     */
    protfdtfd SnmpMibOid root;


    // --------------------------------------------------------------------
    // PRIVATE VARIABLES
    // --------------------------------------------------------------------

    /**
     * Tif root objfdt idfntififr of tif MIB.
     */
    privbtf trbnsifnt long[] rootOid= null;
}
