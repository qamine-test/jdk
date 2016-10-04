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
import jbvb.util.Dbtf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;
import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.EnumRowStbtus;
import dom.sun.jmx.snmp.SnmpInt;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpVbrBind;

/**
 * Tiis dlbss is tif bbsf dlbss for SNMP tbblf mftbdbtb.
 * <p>
 * Its rfsponsibility is to mbnbgf b sortfd brrby of OID indfxfs
 * bddording to tif SNMP indfxing sdifmf ovfr tif "rfbl" tbblf.
 * Ebdi objfdt of tiis dlbss dbn bf bound to bn
 * {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory} to wiidi it will
 * forwbrd rfmotf fntry drfbtion rfqufsts, bnd invokf dbllbbdks
 * wifn bn fntry ibs bffn suddfssfully bddfd to / rfmovfd from
 * tif OID indfx brrby.
 * </p>
 *
 * <p>
 * For fbdi tbblf dffinfd in tif MIB, mibgfn will gfnfrbtf b spfdifid
 * dlbss dbllfd Tbblf<i>TbblfNbmf</i> tibt will implfmfnt tif
 * SnmpTbblfEntryFbdtory intfrfbdf, bnd b dorrfsponding
 * <i>TbblfNbmf</i>Mftb dlbss tibt will fxtfnd tiis dlbss. <br>
 * Tif Tbblf<i>TbblfNbmf</i> dlbss dorrfsponds to tif MBfbn vifw of tif
 * tbblf wiilf tif <i>TbblfNbmf</i>Mftb dlbss dorrfsponds to tif
 * MIB mftbdbtb vifw of tif sbmf tbblf.
 * </p>
 *
 * <p>
 * Objfdts of tiis dlbss brf instbntibtfd by tif gfnfrbtfd
 * wiolf MIB dlbss fxtfnding {@link dom.sun.jmx.snmp.bgfnt.SnmpMib}
 * You siould nfvfr nffd to instbntibtf tiis dlbss dirfdtly.
 * </p>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sff dom.sun.jmx.snmp.bgfnt.SnmpMib
 * @sff dom.sun.jmx.snmp.bgfnt.SnmpMibEntry
 * @sff dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory
 * @sff dom.sun.jmx.snmp.bgfnt.SnmpTbblfSupport
 *
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpMibTbblf fxtfnds SnmpMibNodf
    implfmfnts NotifidbtionBrobddbstfr, Sfriblizbblf {

    /**
     * Crfbtf b nfw <CODE>SnmpMibTbblf</CODE> mftbdbtb nodf.
     *
     * <p>
     * @pbrbm mib Tif SNMP MIB to wiidi tif mftbdbtb will bf linkfd.
     */
    publid SnmpMibTbblf(SnmpMib mib) {
        tiis.tifMib= mib;
        sftCrfbtionEnbblfd(fblsf);
    }

    // -------------------------------------------------------------------
    // PUBLIC METHODS
    // -------------------------------------------------------------------

    /**
     * Tiis mftiod is invokfd wifn tif drfbtion of b nfw fntry is rfqufstfd
     * by b rfmotf SNMP mbnbgfr.
     * <br>By dffbult, rfmotf fntry drfbtion is disbblfd - bnd tiis mftiod
     * will not bf dbllfd. You dbn dynbmidblly switdi tif fntry drfbtion
     * polidy by dblling <dodf>sftCrfbtionEnbblfd(truf)</dodf> bnd <dodf>
     * sftCrfbtionEnbblfd(fblsf)</dodf> on tiis objfdt.
     * <p><b><i>
     * Tiis mftiod is dbllfd intfrnblly by tif SNMP runtimf bnd you
     * siould nfvfr nffd to dbll it dirfdtly. </b></i>Howfvfr you migit wbnt
     * to fxtfnd it in ordfr to implfmfnt your own spfdifid bpplidbtion
     * bfibviour, siould tif dffbult bfibviour not bf bt your donvfnifndf.
     * </p>
     * <p>
     * @pbrbm rfq   Tif SNMP  subrfqufst rfqufsting tiis drfbtion
     * @pbrbm rowOid  Tif OID indfxing tif dondfptubl row (fntry) for wiidi
     *                tif drfbtion wbs rfqufstfd.
     * @pbrbm dfpti Tif position of tif dolumnbr objfdt brd in tif OIDs
     *              from tif vbrbind list.
     *
     * @fxdfption SnmpStbtusExdfption if tif fntry dbnnot bf drfbtfd.
     */
    publid bbstrbdt void drfbtfNfwEntry(SnmpMibSubRfqufst rfq, SnmpOid rowOid,
                                        int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Tfll wiftifr tif spfdifid vfrsion of tiis mftbdbtb gfnfrbtfd
     * by <dodf>mibgfn</dodf> rfquirfs fntrifs to bf rfgistfrfd witi
     * tif MBfbnSfrvfr. In tiis dbsf bn ObjfdtNbmf will ibvf to bf
     * pbssfd to bddEntry() in ordfr for tif tbblf to bfibvf dorrfdtly
     * (dbsf of tif gfnfrid mftbdbtb).
     * <p>
     * If tibt vfrsion of tif mftbdbtb dofs not rfquirf fntry to bf
     * rfgistfrfd, tifn pbssing bn ObjfdtNbmf bfdomfs optionbl (null
     * dbn bf pbssfd instfbd).
     *
     * @rfturn <dodf>truf</dodf> if rfgistrbtion is rfquirfd by tiis
     *         vfrsion of tif mftbdbtb.
     */
    publid bbstrbdt boolfbn isRfgistrbtionRfquirfd();

    /**
     * Tfll wiftifr b nfw fntry siould bf drfbtfd wifn b SET opfrbtion
     * is rfdfivfd for bn fntry tibt dofs not fxist yft.
     *
     * @rfturn truf if b nfw fntry must bf drfbtfd, fblsf otifrwisf.<br>
     *         [dffbult: rfturns <CODE>fblsf</CODE>]
     **/
    publid boolfbn isCrfbtionEnbblfd() {
        rfturn drfbtionEnbblfd;
    }

    /**
     * Tiis mftiod lfts you dynbmidblly switdi tif drfbtion polidy.
     *
     * <p>
     * @pbrbm rfmotfCrfbtionFlbg Tflls wiftifr rfmotf fntry drfbtion must
     *        bf fnbblfd or disbblfd.
     * <ul><li>
     * <CODE>sftCrfbtionEnbblfd(truf)</CODE> will fnbblf rfmotf fntry
     *      drfbtion vib SET opfrbtions.</li>
     * <li>
     * <CODE>sftCrfbtionEnbblfd(fblsf)</CODE> will disbblf rfmotf fntry
     *      drfbtion vib SET opfrbtions.</li>
     * <p> By dffbult rfmotf fntry drfbtion vib SET opfrbtion is disbblfd.
     * </p>
     * </ul>
     **/
    publid void sftCrfbtionEnbblfd(boolfbn rfmotfCrfbtionFlbg) {
        drfbtionEnbblfd = rfmotfCrfbtionFlbg;
    }

    /**
     * Rfturn <dodf>truf</dodf> if tif dondfptubl row dontbins b dolumnbr
     * objfdt usfd to dontrol drfbtion/dflftion of rows in tiis tbblf.
     * <p>
     * Tiis  dolumnbr objfdt dbn bf fitifr b vbribblf witi RowStbtus
     * syntbx bs dffinfd by RFC 2579, or b plbin vbribblf wiosf
     * sfmbntids is tbblf spfdifid.
     * <p>
     * By dffbult, tiis fundtion rfturns <dodf>fblsf</dodf>, bnd it is
     * bssumfd tibt tif tbblf ibs no sudi dontrol vbribblf.<br>
     * Wifn <dodf>mibgfn</dodf> is usfd ovfr SMIv2 MIBs, it will gfnfrbtf
     * bn <dodf>ibsRowStbtus()</dodf> mftiod rfturning <dodf>truf</dodf>
     * for fbdi tbblf dontbining bn objfdt witi RowStbtus syntbx.
     * <p>
     * Wifn tiis mftiod rfturns <dodf>fblsf</dodf> tif dffbult mfdibnism
     * for rfmotf fntry drfbtion is usfd.
     * Otifrwisf, drfbtion/dflftion is pfrformfd bs spfdififd
     * by tif dontrol vbribblf (sff gftRowAdtion() for morf dftbils).
     * <p>
     * Tiis mftiod is dbllfd intfrnblly wifn b SET rfqufst involving
     * tiis tbblf is prodfssfd.
     * <p>
     * If you nffd to implfmfnt b dontrol vbribblf wiidi do not usf
     * tif RowStbtus donvfntion bs dffinfd by RFC 2579, you siould
     * subdlbss tif gfnfrbtfd tbblf mftbdbtb dlbss in ordfr to rfdffinf
     * tiis mftiod bnd mbkf it rfturns <dodf>truf</dodf>.<br>
     * You will tifn ibvf to rfdffinf tif isRowStbtus(), mbpRowStbtus(),
     * isRowRfbdy(), bnd sftRowStbtus() mftiods to suit your spfdifid
     * implfmfntbtion.
     * <p>
     * @rfturn <li><dodf>truf</dodf> if tiis tbblf dontbins b dontrol
     *         vbribblf (fg: b vbribblf witi RFC 2579 RowStbtus syntbx),
     *         </li>
     *         <li><dodf>fblsf</dodf> if tiis tbblf dofs not dontbin
     *         bny dontrol vbribblf.</li>
     *
     **/
    publid boolfbn ibsRowStbtus() {
        rfturn fblsf;
    }

    // ---------------------------------------------------------------------
    //
    // Implfmfnts tif mftiod dffinfd in SnmpMibNodf.
    //
    // ---------------------------------------------------------------------
    /**
     * Gfnfrid ibndling of tif <CODE>gft</CODE> opfrbtion.
     * <p> Tif dffbult implfmfntbtion of tiis mftiod is to
     * <ul>
     * <li> difdk wiftifr tif fntry fxists, bnd if not rfgistfr bn
     *      fxdfption for fbdi vbrbind in tif list.
     * <li> dbll tif gfnfrbtfd
     *      <CODE>gft(rfq,oid,dfpti+1)</CODE> mftiod. </li>
     * </ul>
     * <p>
     * <prf>
     * publid void gft(SnmpMibSubRfqufst rfq, int dfpti)
     *    tirows SnmpStbtusExdfption {
     *    boolfbn         isnfw  = rfq.isNfwEntry();
     *
     *    // if tif fntry dofs not fxists, tifn rfgistfrs bn frror for
     *    // fbdi vbrbind involvfd (nb: tiis siould not ibppfn, sindf
     *    // tif frror siould blrfbdy ibvf bffn dftfdtfd fbrlifr)
     *    //
     *    if (isnfw) {
     *        SnmpVbrBind     vbr = null;
     *        for (Enumfrbtion f= rfq.gftElfmfnts(); f.ibsMorfElfmfnts();) {
     *            vbr = (SnmpVbrBind) f.nfxtElfmfnt();
     *            rfq.rfgistfrGftExdfption(vbr,noSudiNbmfExdfption);
     *        }
     *    }
     *
     *    finbl SnmpOid oid = rfq.gftEntryOid();
     *    gft(rfq,oid,dfpti+1);
     * }
     * </prf>
     * <p> You siould not nffd to ovfrridf tiis mftiod in bny dbsfs, bfdbusf
     * it will fvfntublly dbll
     * <CODE>gft(SnmpMibSubRfqufst rfq, int dfpti)</CODE> on tif gfnfrbtfd
     * dfrivbtivf of <CODE>SnmpMibEntry</CODE>. If you nffd to implfmfnt
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs, or if you nffd to implfmfnt somf donsistfndy
     * difdks bftwffn tif difffrfnt vblufs providfd in tif vbrbind list,
     * you siould tifn rbtifr ovfrridf
     * <CODE>gft(SnmpMibSubRfqufst rfq, int dfpti)</CODE> on tif gfnfrbtfd
     * dfrivbtivf of <CODE>SnmpMibEntry</CODE>.
     * <p>
     *
     */
    @Ovfrridf
    publid void gft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {

        finbl boolfbn         isnfw  = rfq.isNfwEntry();
        finbl SnmpMibSubRfqufst  r      = rfq;

        // if tif fntry dofs not fxists, tifn rfgistfrs bn frror for
        // fbdi vbrbind involvfd (nb: siould not ibppfn, tif frror
        // siould ibvf bffn rfgistfrfd fbrlifr)
        if (isnfw) {
            SnmpVbrBind vbr;
            for (Enumfrbtion<SnmpVbrBind> f= r.gftElfmfnts(); f.ibsMorfElfmfnts();) {
                vbr = f.nfxtElfmfnt();
                r.rfgistfrGftExdfption(vbr,nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf));
            }
        }

        finbl SnmpOid     oid    = r.gftEntryOid();

        // SnmpIndfx   indfx  = buildSnmpIndfx(oid.longVbluf(fblsf), 0);
        // gft(rfq,indfx,dfpti+1);
        //
        gft(rfq,oid,dfpti+1);
    }

    // ---------------------------------------------------------------------
    //
    // Implfmfnts tif mftiod dffinfd in SnmpMibNodf.
    //
    // ---------------------------------------------------------------------
    /**
     * Gfnfrid ibndling of tif <CODE>difdk</CODE> opfrbtion.
     * <p> Tif dffbult implfmfntbtion of tiis mftiod is to
     * <ul>
     * <li> difdk wiftifr b nfw fntry must bf drfbtfd, bnd if rfmotf
     *      drfbtion of fntrifs is fnbblfd, drfbtf it. </li>
     * <li> dbll tif gfnfrbtfd
     *      <CODE>difdk(rfq,oid,dfpti+1)</CODE> mftiod. </li>
     * </ul>
     * <p>
     * <prf>
     * publid void difdk(SnmpMibSubRfqufst rfq, int dfpti)
     *    tirows SnmpStbtusExdfption {
     *    finbl SnmpOid     oid    = rfq.gftEntryOid();
     *    finbl int         bdtion = gftRowAdtion(rfq,oid,dfpti+1);
     *
     *    bfginRowAdtion(rfq,oid,dfpti+1,bdtion);
     *    difdk(rfq,oid,dfpti+1);
     * }
     * </prf>
     * <p> You siould not nffd to ovfrridf tiis mftiod in bny dbsfs, bfdbusf
     * it will fvfntublly dbll
     * <CODE>difdk(SnmpMibSubRfqufst rfq, int dfpti)</CODE> on tif gfnfrbtfd
     * dfrivbtivf of <CODE>SnmpMibEntry</CODE>. If you nffd to implfmfnt
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs, or if you nffd to implfmfnt somf donsistfndy
     * difdks bftwffn tif difffrfnt vblufs providfd in tif vbrbind list,
     * you siould tifn rbtifr ovfrridf
     * <CODE>difdk(SnmpMibSubRfqufst rfq, int dfpti)</CODE> on tif gfnfrbtfd
     * dfrivbtivf of <CODE>SnmpMibEntry</CODE>.
     * <p>
     *
     */
    @Ovfrridf
    publid void difdk(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {
        finbl SnmpOid     oid    = rfq.gftEntryOid();
        finbl int         bdtion = gftRowAdtion(rfq,oid,dfpti+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMibTbblf.dlbss.gftNbmf(),
                    "difdk", "Cblling bfginRowAdtion");
        }

        bfginRowAdtion(rfq,oid,dfpti+1,bdtion);

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMibTbblf.dlbss.gftNbmf(),
                    "difdk",
                    "Cblling difdk for " + rfq.gftSizf() + " vbrbinds");
        }

        difdk(rfq,oid,dfpti+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMibTbblf.dlbss.gftNbmf(),
                    "difdk", "difdk finisifd");
        }
    }

    // ---------------------------------------------------------------------
    //
    // Implfmfnts tif mftiod dffinfd in SnmpMibNodf.
    //
    // ---------------------------------------------------------------------
    /**
     * Gfnfrid ibndling of tif <CODE>sft</CODE> opfrbtion.
     * <p> Tif dffbult implfmfntbtion of tiis mftiod is to
     * dbll tif gfnfrbtfd
     * <CODE>sft(rfq,oid,dfpti+1)</CODE> mftiod.
     * <p>
     * <prf>
     * publid void sft(SnmpMibSubRfqufst rfq, int dfpti)
     *    tirows SnmpStbtusExdfption {
     *    finbl SnmpOid oid = rfq.gftEntryOid();
     *    finbl int  bdtion = gftRowAdtion(rfq,oid,dfpti+1);
     *
     *    sft(rfq,oid,dfpti+1);
     *    fndRowAdtion(rfq,oid,dfpti+1,bdtion);
     * }
     * </prf>
     * <p> You siould not nffd to ovfrridf tiis mftiod in bny dbsfs, bfdbusf
     * it will fvfntublly dbll
     * <CODE>sft(SnmpMibSubRfqufst rfq, int dfpti)</CODE> on tif gfnfrbtfd
     * dfrivbtivf of <CODE>SnmpMibEntry</CODE>. If you nffd to implfmfnt
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs, or if you nffd to implfmfnt somf donsistfndy
     * difdks bftwffn tif difffrfnt vblufs providfd in tif vbrbind list,
     * you siould tifn rbtifr ovfrridf
     * <CODE>sft(SnmpMibSubRfqufst rfq, int dfpti)</CODE> on tif gfnfrbtfd
     * dfrivbtivf of <CODE>SnmpMibEntry</CODE>.
     * <p>
     *
     */
    @Ovfrridf
    publid void sft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {


        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMibTbblf.dlbss.gftNbmf(),
                    "sft", "Entfring sft");
        }

        finbl SnmpOid     oid    = rfq.gftEntryOid();
        finbl int         bdtion = gftRowAdtion(rfq,oid,dfpti+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMibTbblf.dlbss.gftNbmf(),
                    "sft", "Cblling sft for " + rfq.gftSizf() + " vbrbinds");
        }

        sft(rfq,oid,dfpti+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMibTbblf.dlbss.gftNbmf(),
                    "sft", "Cblling fndRowAdtion");
        }

        fndRowAdtion(rfq,oid,dfpti+1,bdtion);

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMibTbblf.dlbss.gftNbmf(),
                    "sft", "RowAdtion finisifd");
        }

    }

    /**
     * Add b nfw fntry in tiis <CODE>SnmpMibTbblf</CODE>.
     * Also triggfrs tif bddEntryCB() dbllbbdk of tif
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory} intfrfbdf
     * if tiis nodf is bound to b fbdtory.
     *
     * Tiis mftiod bssumfs tibt tif givfn fntry will not bf rfgistfrfd.
     * If tif fntry is going to bf rfgistfrfd, or if ObjfdtNbmf's brf
     * rfquirfd, tifn
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf#bddEntry(SnmpOid,
     * ObjfdtNbmf, Objfdt)} siould bf prfffrrfd.
     * <br> Tiis fundtion is mbinly providfd for bbdkwbrd dompbtibility.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row to bf bddfd.
     * @pbrbm fntry Tif fntry to bdd.
     *
     * @fxdfption SnmpStbtusExdfption Tif fntry douldn't bf bddfd
     *            bt tif position idfntififd by tif givfn
     *            <dodf>rowOid</dodf>, or tiis vfrsion of tif mftbdbtb
     *            rfquirfs ObjfdtNbmf's.
     */
     // publid void bddEntry(SnmpIndfx indfx, Objfdt fntry)
     publid void bddEntry(SnmpOid rowOid, Objfdt fntry)
        tirows SnmpStbtusExdfption {

         bddEntry(rowOid, null, fntry);
    }

    /**
     * Add b nfw fntry in tiis <CODE>SnmpMibTbblf</CODE>.
     * Also triggfrs tif bddEntryCB() dbllbbdk of tif
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory} intfrfbdf
     * if tiis nodf is bound to b fbdtory.
     *
     * <p>
     * @pbrbm oid    Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row to bf bddfd.
     *
     * @pbrbm nbmf  Tif ObjfdtNbmf witi wiidi tiis fntry is rfgistfrfd.
     *              Tiis pbrbmftfr dbn bf omittfd if isRfgistrbtionRfquirfd()
     *              rfturn fblsf.
     *
     * @pbrbm fntry Tif fntry to bdd.
     *
     * @fxdfption SnmpStbtusExdfption Tif fntry douldn't bf bddfd
     *            bt tif position idfntififd by tif givfn
     *            <dodf>rowOid</dodf>, or if tiis vfrsion of tif mftbdbtb
     *            rfquirfs ObjfdtNbmf's, bnd tif givfn nbmf is null.
     */
    // protfdtfd syndironizfd void bddEntry(SnmpIndfx indfx, ObjfdtNbmf nbmf,
    //                                      Objfdt fntry)
    publid syndironizfd void bddEntry(SnmpOid oid, ObjfdtNbmf nbmf,
                                      Objfdt fntry)
        tirows SnmpStbtusExdfption {

        if (isRfgistrbtionRfquirfd() == truf && nbmf == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.bbdVbluf);

        if (sizf == 0) {
            //            indfxfs.bddElfmfnt(indfx);
            // XX oids.bddElfmfnt(oid);
            insfrtOid(0,oid);
            if (fntrifs != null)
                fntrifs.bddElfmfnt(fntry);
            if (fntrynbmfs != null)
                fntrynbmfs.bddElfmfnt(nbmf);
            sizf++;

            // triggfrs dbllbbdks on tif fntry fbdtory
            //
            if (fbdtory != null) {
                try {
                    fbdtory.bddEntryCb(0,oid,nbmf,fntry,tiis);
                } dbtdi (SnmpStbtusExdfption x) {
                    rfmovfOid(0);
                    if (fntrifs != null)
                        fntrifs.rfmovfElfmfntAt(0);
                    if (fntrynbmfs != null)
                        fntrynbmfs.rfmovfElfmfntAt(0);
                    tirow x;
                }
            }

            // sfnds tif notifidbtions
            //
            sfndNotifidbtion(SnmpTbblfEntryNotifidbtion.SNMP_ENTRY_ADDED,
                             (nfw Dbtf()).gftTimf(), fntry, nbmf);
            rfturn;
        }

        // Gft tif insfrtion position ...
        //
        int pos= 0;
        // bug jbw.00356.B : usf oid rbtifr tibn indfx to gft tif
        // insfrtion point.
        //
        pos= gftInsfrtionPoint(oid,truf);
        if (pos == sizf) {
            // Add b nfw flfmfnt in tif vfdtors ...
            //
            //            indfxfs.bddElfmfnt(indfx);
            // XX oids.bddElfmfnt(oid);
            insfrtOid(tbblfdount,oid);
            if (fntrifs != null)
                fntrifs.bddElfmfnt(fntry);
            if (fntrynbmfs != null)
                fntrynbmfs.bddElfmfnt(nbmf);
            sizf++;
        } flsf {
            // Insfrt nfw flfmfnt ...
            //
            try {
                //                indfxfs.insfrtElfmfntAt(indfx, pos);
                // XX oids.insfrtElfmfntAt(oid, pos);
                insfrtOid(pos,oid);
                if (fntrifs != null)
                    fntrifs.insfrtElfmfntAt(fntry, pos);
                if (fntrynbmfs != null)
                    fntrynbmfs.insfrtElfmfntAt(nbmf,pos);
                sizf++;
            } dbtdi(ArrbyIndfxOutOfBoundsExdfption f) {
            }
        }

        // triggfrs dbllbbdks on tif fntry fbdtory
        //
        if (fbdtory != null) {
            try {
                fbdtory.bddEntryCb(pos,oid,nbmf,fntry,tiis);
            } dbtdi (SnmpStbtusExdfption x) {
                rfmovfOid(pos);
                if (fntrifs != null)
                    fntrifs.rfmovfElfmfntAt(pos);
                if (fntrynbmfs != null)
                    fntrynbmfs.rfmovfElfmfntAt(pos);
                tirow x;
            }
        }

        // sfnds tif notifidbtions
        //
        sfndNotifidbtion(SnmpTbblfEntryNotifidbtion.SNMP_ENTRY_ADDED,
                         (nfw Dbtf()).gftTimf(), fntry, nbmf);
    }

    /**
     * Rfmovf tif spfdififd fntry from tif tbblf.
     * Also triggfrs tif rfmovfEntryCB() dbllbbdk of tif
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory} intfrfbdf
     * if tiis nodf is bound to b fbdtory.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row to rfmovf.
     *
     * @pbrbm fntry Tif fntry to bf rfmovfd. Tiis pbrbmftfr is not usfd
     *              intfrnblly, it is simply pbssfd blong to tif
     *              rfmovfEntryCB() dbllbbdk.
     *
     * @fxdfption SnmpStbtusExdfption if tif spfdififd fntry douldn't
     *            bf rfmovfd (if tif givfn <dodf>rowOid</dodf> is not
     *            vblid for instbndf).
     */
    publid syndironizfd void rfmovfEntry(SnmpOid rowOid, Objfdt fntry)
        tirows SnmpStbtusExdfption {
        int pos = findObjfdt(rowOid);
        if (pos == -1)
            rfturn;
        rfmovfEntry(pos,fntry);
    }

    /**
     * Rfmovf tif spfdififd fntry from tif tbblf.
     * Also triggfrs tif rfmovfEntryCB() dbllbbdk of tif
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory} intfrfbdf
     * if tiis nodf is bound to b fbdtory.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row to rfmovf.
     *
     * @fxdfption SnmpStbtusExdfption if tif spfdififd fntry douldn't
     *            bf rfmovfd (if tif givfn <dodf>rowOid</dodf> is not
     *            vblid for instbndf).
     */
    publid void rfmovfEntry(SnmpOid rowOid)
        tirows SnmpStbtusExdfption {
        int pos = findObjfdt(rowOid);
        if (pos == -1)
            rfturn;
        rfmovfEntry(pos,null);
    }

    /**
     * Rfmovf tif spfdififd fntry from tif tbblf.
     * Also triggfrs tif rfmovfEntryCB() dbllbbdk of tif
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory} intfrfbdf
     * if tiis nodf is bound to b fbdtory.
     *
     * <p>
     * @pbrbm pos Tif position of tif fntry in tif tbblf.
     *
     * @pbrbm fntry Tif fntry to bf rfmovfd. Tiis pbrbmftfr is not usfd
     *              intfrnblly, it is simply pbssfd blong to tif
     *              rfmovfEntryCB() dbllbbdk.
     *
     * @fxdfption SnmpStbtusExdfption if tif spfdififd fntry douldn't
     *            bf rfmovfd.
     */
    publid syndironizfd void rfmovfEntry(int pos, Objfdt fntry)
        tirows SnmpStbtusExdfption {
        if (pos == -1)
            rfturn;
        if (pos >= sizf) rfturn;

        Objfdt obj = fntry;
        if (fntrifs != null && fntrifs.sizf() > pos) {
            obj = fntrifs.flfmfntAt(pos);
            fntrifs.rfmovfElfmfntAt(pos);
        }

        ObjfdtNbmf nbmf = null;
        if (fntrynbmfs != null && fntrynbmfs.sizf() > pos) {
            nbmf = fntrynbmfs.flfmfntAt(pos);
            fntrynbmfs.rfmovfElfmfntAt(pos);
        }

        finbl SnmpOid rowOid = tbblfoids[pos];
        rfmovfOid(pos);
        sizf --;

        if (obj == null) obj = fntry;

        if (fbdtory != null)
            fbdtory.rfmovfEntryCb(pos,rowOid,nbmf,obj,tiis);

        sfndNotifidbtion(SnmpTbblfEntryNotifidbtion.SNMP_ENTRY_REMOVED,
                         (nfw Dbtf()).gftTimf(), obj, nbmf);
    }

    /**
     * Gft tif fntry dorrfsponding to tif spfdififd rowOid.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif
     *        row to bf rftrifvfd.
     *
     * @rfturn Tif fntry.
     *
     * @fxdfption SnmpStbtusExdfption Tifrf is no fntry witi tif spfdififd
     *      <dodf>rowOid</dodf> in tif tbblf.
     */
    publid syndironizfd Objfdt gftEntry(SnmpOid rowOid)
        tirows SnmpStbtusExdfption {
        int pos= findObjfdt(rowOid);
        if (pos == -1)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        rfturn fntrifs.flfmfntAt(pos);
    }

    /**
     * Gft tif ObjfdtNbmf of tif fntry dorrfsponding to tif
     * spfdififd rowOid.
     * Tif rfsult of tiis mftiod is only mfbningful if
     * isRfgistrbtionRfquirfd() yiflds truf.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *        row wiosf ObjfdtNbmf wf wbnt to rftrifvf.
     *
     * @rfturn Tif objfdt nbmf of tif fntry.
     *
     * @fxdfption SnmpStbtusExdfption Tifrf is no fntry witi tif spfdififd
     *      <dodf>rowOid</dodf> in tif tbblf.
     */
    publid syndironizfd ObjfdtNbmf gftEntryNbmf(SnmpOid rowOid)
        tirows SnmpStbtusExdfption {
        int pos = findObjfdt(rowOid);
        if (fntrynbmfs == null) rfturn null;
        if (pos == -1 || pos >= fntrynbmfs.sizf())
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        rfturn fntrynbmfs.flfmfntAt(pos);
    }

    /**
     * Rfturn tif fntrifs storfd in tiis tbblf <CODE>SnmpMibTbblf</CODE>.
     * <p>
     * If tif subdlbss gfnfrbtfd by mibgfn usfs tif gfnfrid wby to bddfss
     * tif fntrifs (i.f. if it gofs tirougi tif MBfbnSfrvfr) tifn somf of
     * tif fntrifs mby bf <dodf>null</dodf>. It bll dfpfnds wiftifr b non
     * <dodf>null</dodf> fntry wbs pbssfd to bddEntry().<br>
     * Otifrwisf, if it usfs tif stbndbrd wby (bddfss tif fntry dirfdtly
     * tirougi tifir stbndbrd MBfbn intfrfbdf) tiis brrby will dontbin bll
     * tif fntrifs.
     * <p>
     * @rfturn Tif fntrifs brrby.
     */
    publid Objfdt[] gftBbsidEntrifs() {
        Objfdt[] brrby= nfw Objfdt[sizf];
        fntrifs.dopyInto(brrby);
        rfturn brrby;
    }

    /**
     * Gft tif sizf of tif tbblf.
     *
     * @rfturn Tif numbfr of fntrifs durrfntly rfgistfrfd in tiis tbblf.
     */
    publid int gftSizf() {
        rfturn sizf;
    }

    // EVENT STUFF
    //------------

    /**
     * Enbblf to bdd bn SNMP fntry listfnfr to tiis
     * <CODE>SnmpMibTbblf</CODE>.
     *
     * <p>
     * @pbrbm listfnfr Tif listfnfr objfdt wiidi will ibndlf tif
     *    notifidbtions fmittfd by tif rfgistfrfd MBfbn.
     *
     * @pbrbm filtfr Tif filtfr objfdt. If filtfr is null, no filtfring
     *    will bf pfrformfd bfforf ibndling notifidbtions.
     *
     * @pbrbm ibndbbdk Tif dontfxt to bf sfnt to tif listfnfr wifn b
     *    notifidbtion is fmittfd.
     *
     * @fxdfption IllfgblArgumfntExdfption Listfnfr pbrbmftfr is null.
     */
    @Ovfrridf
    publid syndironizfd void
        bddNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                NotifidbtionFiltfr filtfr, Objfdt ibndbbdk)  {

        // Cifdk listfnfr
        //
        if (listfnfr == null) {
            tirow nfw jbvb.lbng.IllfgblArgumfntExdfption
                ("Listfnfr dbn't bf null") ;
        }

        // looking for listfnfr in ibndbbdkTbblf
        //
        Vfdtor<Objfdt> ibndbbdkList = ibndbbdkTbblf.gft(listfnfr) ;
        Vfdtor<NotifidbtionFiltfr> filtfrList = filtfrTbblf.gft(listfnfr) ;
        if ( ibndbbdkList == null ) {
            ibndbbdkList = nfw Vfdtor<>() ;
            filtfrList = nfw Vfdtor<>() ;
            ibndbbdkTbblf.put(listfnfr, ibndbbdkList) ;
            filtfrTbblf.put(listfnfr, filtfrList) ;
        }

        // Add tif ibndbbdk bnd tif filtfr
        //
        ibndbbdkList.bddElfmfnt(ibndbbdk) ;
        filtfrList.bddElfmfnt(filtfr) ;
    }

    /**
     * Enbblf to rfmovf bn SNMP fntry listfnfr from tiis
     * <CODE>SnmpMibTbblf</CODE>.
     *
     * @pbrbm listfnfr Tif listfnfr objfdt wiidi will ibndlf tif
     *    notifidbtions fmittfd by tif rfgistfrfd MBfbn.
     *    Tiis mftiod will rfmovf bll tif informbtion rflbtfd to tiis
     *    listfnfr.
     *
     * @fxdfption ListfnfrNotFoundExdfption Tif listfnfr is not rfgistfrfd
     *    in tif MBfbn.
     */
    @Ovfrridf
    publid syndironizfd void
        rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr)
        tirows ListfnfrNotFoundExdfption {

        // looking for listfnfr in ibndbbdkTbblf
        //
        jbvb.util.Vfdtor<?> ibndbbdkList = ibndbbdkTbblf.gft(listfnfr) ;
        if ( ibndbbdkList == null ) {
            tirow nfw ListfnfrNotFoundExdfption("listfnfr");
        }

        // If ibndbbdk is null, rfmovf tif listfnfr fntry
        //
        ibndbbdkTbblf.rfmovf(listfnfr) ;
        filtfrTbblf.rfmovf(listfnfr) ;
    }

    /**
     * Rfturn b <CODE>NotifidbtionInfo</CODE> objfdt dontbining tif
     * notifidbtion dlbss bnd tif notifidbtion typf sfnt by tif
     * <CODE>SnmpMibTbblf</CODE>.
     */
    @Ovfrridf
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {

        String[] typfs = {SnmpTbblfEntryNotifidbtion.SNMP_ENTRY_ADDED,
                          SnmpTbblfEntryNotifidbtion.SNMP_ENTRY_REMOVED};

        MBfbnNotifidbtionInfo[] notifsInfo = {
            nfw MBfbnNotifidbtionInfo
            (typfs, "dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryNotifidbtion",
             "Notifidbtions sfnt by tif SnmpMibTbblf")
        };

        rfturn notifsInfo;
    }


    /**
     * Rfgistfr tif fbdtory tirougi wiidi tbblf fntrifs siould
     * bf drfbtfd wifn rfmotf fntry drfbtion is fnbblfd.
     *
     * <p>
     * @pbrbm fbdtory Tif
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfEntryFbdtory} tirougi
     *        wiidi fntrifs will bf drfbtfd wifn b rfmotf SNMP mbnbgfr
     *        rfqufst tif drfbtion of b nfw fntry vib bn SNMP SET rfqufst.
     */
    publid void rfgistfrEntryFbdtory(SnmpTbblfEntryFbdtory fbdtory) {
        tiis.fbdtory = fbdtory;
    }

    // ----------------------------------------------------------------------
    // PROTECTED METHODS - RowStbtus
    // ----------------------------------------------------------------------

    /**
     * Rfturn truf if tif dolumnbr objfdt idfntififd by <dodf>vbr</dodf>
     * is usfd to dontrol tif bddition/dflftion of rows in tiis tbblf.
     *
     * <p>
     * By dffbult, tiis mftiod bssumfs tibt tifrf is no dontrol vbribblf
     * bnd blwbys rfturn <dodf>fblsf</dodf>
     * <p>
     * If tiis tbblf wbs dffinfd using SMIv2, bnd if it dontbins b
     * dontrol vbribblf witi RowStbtus syntbx, <dodf>mibgfn</dodf>
     * will gfnfrbtf b non dffbult implfmfntbtion for tiis mftiod
     * tibt will idfntify tif RowStbtus dontrol vbribblf.
     * <p>
     * You will ibvf to rfdffinf tiis mftiod if you nffd to implfmfnt
     * dontrol vbribblfs tibt do not donform to RFC 2579 RowStbtus
     * TEXTUAL-CONVENTION.
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm vbr Tif OID brd idfntifying tif involvfd dolumnbr objfdt.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     **/
    protfdtfd boolfbn isRowStbtus(SnmpOid rowOid, long vbr,
                                    Objfdt  usfrDbtb) {
        rfturn fblsf;
    }


    /**
     * Rfturn tif RowStbtus dodf vbluf spfdififd in tiis rfqufst.
     * <p>
     * Tif RowStbtus dodf vbluf siould bf onf of tif vblufs dffinfd
     * by {@link dom.sun.jmx.snmp.EnumRowStbtus}. Tifsf dodfs dorrfspond
     * to RowStbtus dodfs bs dffinfd in RFC 2579, plus tif <i>unspfdififd</i>
     * vbluf wiidi is SNMP Runtimf spfdifid.
     * <p>
     *
     * @pbrbm rfq    Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm dfpti  Tif dfpti rfbdifd in tif OID trff.
     *
     * @rfturn Tif RowStbtus dodf spfdififd in tiis rfqufst, if bny:
     * <ul>
     * <li>If tif spfdififd row dofs not fxist bnd tiis tbblf do
     *     not usf bny vbribblf to dontrol drfbtion/dflftion of
     *     rows, tifn dffbult drfbtion mfdibnism is bssumfd bnd
     *     <i>drfbtfAndGo</i> is rfturnfd</li>
     * <li>Otifrwisf, if tif row fxists bnd tiis tbblf do not usf bny
     *     vbribblf to dontrol drfbtion/dflftion of rows,
     *     <i>unspfdififd</i> is rfturnfd.</li>
     * <li>Otifrwisf, if tif rfqufst dofs not dontbin tif dontrol vbribblf,
     *     <i>unspfdififd</i> is rfturnfd.</li>
     * <li>Otifrwisf, mbpRowStbtus() is dbllfd to fxtrbdt tif RowStbtus
     *     dodf from tif SnmpVbrBind tibt dontbins tif dontrol vbribblf.</li>
     * </ul>
     *
     * @fxdfption SnmpStbtusExdfption if tif vbluf of tif dontrol vbribblf
     *            dould not bf mbppfd to b RowStbtus dodf.
     *
     * @sff dom.sun.jmx.snmp.EnumRowStbtus
     **/
    protfdtfd int gftRowAdtion(SnmpMibSubRfqufst rfq, SnmpOid rowOid,
                               int dfpti)
        tirows SnmpStbtusExdfption {
        finbl boolfbn     isnfw  = rfq.isNfwEntry();
        finbl SnmpVbrBind vb = rfq.gftRowStbtusVbrBind();
        if (vb == null) {
            if (isnfw && ! ibsRowStbtus())
                rfturn EnumRowStbtus.drfbtfAndGo;
            flsf rfturn EnumRowStbtus.unspfdififd;
        }

        try {
            rfturn mbpRowStbtus(rowOid, vb, rfq.gftUsfrDbtb());
        } dbtdi( SnmpStbtusExdfption x) {
            difdkRowStbtusFbil(rfq, x.gftStbtus());
        }
        rfturn EnumRowStbtus.unspfdififd;
    }

    /**
     * Mbp tif vbluf of tif <dodf>vbstbtus</dodf> vbrbind to tif
     * dorrfsponding RowStbtus dodf dffinfd in
     * {@link dom.sun.jmx.snmp.EnumRowStbtus}.
     * Tifsf dodfs dorrfspond to RowStbtus dodfs bs dffinfd in RFC 2579,
     * plus tif <i>unspfdififd</i> vbluf wiidi is SNMP Runtimf spfdifid.
     * <p>
     * By dffbult, tiis mftiod bssumfs tibt tif dontrol vbribblf is
     * bn Intfgfr, bnd it simply rfturns its vbluf witiout furtifr
     * bnblysis.
     * <p>
     * If tiis tbblf wbs dffinfd using SMIv2, bnd if it dontbins b
     * dontrol vbribblf witi RowStbtus syntbx, <dodf>mibgfn</dodf>
     * will gfnfrbtf b non dffbult implfmfntbtion for tiis mftiod.
     * <p>
     * You will ibvf to rfdffinf tiis mftiod if you nffd to implfmfnt
     * dontrol vbribblfs tibt do not donform to RFC 2579 RowStbtus
     * TEXTUAL-CONVENTION.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm vbstbtus Tif SnmpVbrBind dontbining tif vbluf of tif dontrol
     *           vbribblf, bs idfntififd by tif isRowStbtus() mftiod.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn Tif RowStbtus dodf mbppfd from tif vbluf dontbinfd
     *     in <dodf>vbstbtus</dodf>.
     *
     * @fxdfption SnmpStbtusExdfption if tif vbluf of tif dontrol vbribblf
     *            dould not bf mbppfd to b RowStbtus dodf.
     *
     * @sff dom.sun.jmx.snmp.EnumRowStbtus
     **/
    protfdtfd int mbpRowStbtus(SnmpOid rowOid, SnmpVbrBind vbstbtus,
                               Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        finbl SnmpVbluf rsvbluf = vbstbtus.vbluf;

        if (rsvbluf instbndfof SnmpInt)
            rfturn ((SnmpInt)rsvbluf).intVbluf();
        flsf
            tirow nfw SnmpStbtusExdfption(
                       SnmpStbtusExdfption.snmpRspIndonsistfntVbluf);
    }

    /**
     * Sft tif dontrol vbribblf to tif spfdififd <dodf>nfwStbtus</dodf>
     * vbluf.
     *
     * <p>
     * Tiis mftiod mbps tif givfn <dodf>nfwStbtus</dodf> to tif bppropribtf
     * vbluf for tif dontrol vbribblf, tifn sfts tif dontrol vbribblf in
     * tif fntry idfntififd by <dodf>rowOid</dodf>. It rfturns tif nfw
     * vbluf of tif dontrol vbribblf.
     * <p>
     * By dffbult, it is bssumfd tibt tifrf is no dontrol vbribblf so tiis
     * mftiod dofs notiing bnd simply rfturns <dodf>null</dodf>.
     * <p>
     * If tiis tbblf wbs dffinfd using SMIv2, bnd if it dontbins b
     * dontrol vbribblf witi RowStbtus syntbx, <dodf>mibgfn</dodf>
     * will gfnfrbtf b non dffbult implfmfntbtion for tiis mftiod.
     * <p>
     * You will ibvf to rfdffinf tiis mftiod if you nffd to implfmfnt
     * dontrol vbribblfs tibt do not donform to RFC 2579 RowStbtus
     * TEXTUAL-CONVENTION.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm nfwStbtus Tif nfw stbtus for tif row: onf of tif
     *        RowStbtus dodf dffinfd in
     *        {@link dom.sun.jmx.snmp.EnumRowStbtus}. Tifsf dodfs
     *        dorrfspond to RowStbtus dodfs bs dffinfd in RFC 2579,
     *        plus tif <i>unspfdififd</i> vbluf wiidi is SNMP Runtimf spfdifid.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn Tif nfw vbluf of tif dontrol vbribblf (usublly
     *         <dodf>nfw SnmpInt(nfwStbtus)</dodf>) or <dodf>null</dodf>
     *         if tif tbblf do not ibvf bny dontrol vbribblf.
     *
     * @fxdfption SnmpStbtusExdfption If tif givfn <dodf>nfwStbtus</dodf>
     *            dould not bf sft on tif spfdififd fntry, or if tif
     *            givfn <dodf>nfwStbtus</dodf> is not vblid.
     *
     * @sff dom.sun.jmx.snmp.EnumRowStbtus
     **/
    protfdtfd SnmpVbluf sftRowStbtus(SnmpOid rowOid, int nfwStbtus,
                                     Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        rfturn null;
    }

    /**
     * Tfll wiftifr tif spfdififd row is rfbdy bnd dbn bf put in tif
     * <i>notInSfrvidf</i> stbtf.
     * <p>
     * Tiis mftiod is dbllfd only ondf, bftfr bll tif vbrbind ibvf bffn
     * sft on b nfw fntry for wiidi <i>drfbtfAndWbit</i> wbs spfdififd.
     * <p>
     * If tif fntry is not yft rfbdy, tiis mftiod siould rfturn fblsf.
     * It will tifn bf tif rfsponsibility of tif fntry to switdi its
     * own stbtf to <i>notInSfrvidf</i> wifn it bfdomfs rfbdy.
     * No furtifr dbll to <dodf>isRowRfbdy()</dodf> will bf mbdf.
     * <p>
     * By dffbult, tiis mftiod blwbys rfturn truf. <br>
     * <dodf>mibgfn</dodf> will not gfnfrbtf bny spfdifid implfmfntbtion
     * for tiis mftiod - mfbning tibt by dffbult, b row drfbtfd using
     * <i>drfbtfAndWbit</i> will blwbys bf plbdfd in <i>notInSfrvidf</i>
     * stbtf bt tif fnd of tif rfqufst.
     * <p>
     * If tiis tbblf wbs dffinfd using SMIv2, bnd if it dontbins b
     * dontrol vbribblf witi RowStbtus syntbx, <dodf>mibgfn</dodf>
     * will gfnfrbtf bn implfmfntbtion for tiis mftiod tibt will
     * dflfgbtf tif work to tif mftbdbtb dlbss modflling tif dondfptubl
     * row, so tibt you dbn ovfrridf tif dffbult bfibviour by subdlbssing
     * tibt mftbdbtb dlbss.
     * <p>
     * You will ibvf to rfdffinf tiis mftiod if tiis dffbult mfdibnism
     * dofs not suit your nffds.
     *
     * <p>
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn <dodf>truf</dodf> if tif row dbn bf plbdfd in
     *         <i>notInSfrvidf</i> stbtf.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf trying
     *            to rftrifvf tif row stbtus, bnd tif opfrbtion siould
     *            bf bbortfd.
     *
     * @sff dom.sun.jmx.snmp.EnumRowStbtus
     **/
    protfdtfd boolfbn isRowRfbdy(SnmpOid rowOid, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        rfturn truf;
    }

    /**
     * Cifdk wiftifr tif dontrol vbribblf of tif givfn row dbn bf
     * switdifd to tif nfw spfdififd <dodf>nfwStbtus</dodf>.
     * <p>
     * Tiis mftiod is dbllfd during tif <i>difdk</i> pibsf of b SET
     * rfqufst wifn tif dontrol vbribblf spfdififs <i>bdtivf</i> or
     * <i>notInSfrvidf</i>.
     * <p>
     * By dffbult it is bssumfd tibt notiing prfvfnts putting tif
     * row in tif rfqufstfd stbtf, bnd tiis mftiod dofs notiing.
     * It is simply providfd bs b iook so tibt spfdifid difdks dbn
     * bf implfmfntfd.
     * <p>
     * Notf tibt if tif bdtubl row dflftion fbils bftfrwbrd, tif
     * btomidity of tif rfqufst is no longfr gubrbntffd.
     *
     * <p>
     * @pbrbm rfq    Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm dfpti  Tif dfpti rfbdifd in tif OID trff.
     *
     * @pbrbm nfwStbtus Tif nfw stbtus for tif row: onf of tif
     *        RowStbtus dodf dffinfd in
     *        {@link dom.sun.jmx.snmp.EnumRowStbtus}. Tifsf dodfs
     *        dorrfspond to RowStbtus dodfs bs dffinfd in RFC 2579,
     *        plus tif <i>unspfdififd</i> vbluf wiidi is SNMP Runtimf spfdifid.
     *
     * @fxdfption SnmpStbtusExdfption if switdiing to tiis nfw stbtf
     *            would fbil.
     *
     **/
    protfdtfd void difdkRowStbtusCibngf(SnmpMibSubRfqufst rfq,
                                        SnmpOid rowOid, int dfpti,
                                        int nfwStbtus)
        tirows SnmpStbtusExdfption {

    }

    /**
     * Cifdk wiftifr tif spfdififd row dbn bf rfmovfd from tif tbblf.
     * <p>
     * Tiis mftiod is dbllfd during tif <i>difdk</i> pibsf of b SET
     * rfqufst wifn tif dontrol vbribblf spfdififs <i>dfstroy</i>
     * <p>
     * By dffbult it is bssumfd tibt notiing prfvfnts row dflftion
     * bnd tiis mftiod dofs notiing. It is simply providfd bs b iook
     * so tibt spfdifid difdks dbn bf implfmfntfd.
     * <p>
     * Notf tibt if tif bdtubl row dflftion fbils bftfrwbrd, tif
     * btomidity of tif rfqufst is no longfr gubrbntffd.
     *
     * <p>
     * @pbrbm rfq    Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm dfpti  Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption if tif row dflftion must bf
     *            rfjfdtfd.
     **/
    protfdtfd void difdkRfmovfTbblfRow(SnmpMibSubRfqufst rfq, SnmpOid rowOid,
                                       int dfpti)
        tirows SnmpStbtusExdfption {

    }

    /**
     * Rfmovf b tbblf row upon b rfmotf mbnbgfr rfqufst.
     *
     * Tiis mftiod is dbllfd intfrnblly wifn <dodf>gftRowAdtion()</dodf>
     * yiflds <i>dfstroy</i> - i.f.: it is only dbllfd wifn b rfmotf
     * mbnbgfr rfqufsts tif rfmovbl of b tbblf row.<br>
     * You siould nfvfr nffd to dbll tiis fundtion dirfdtly.
     * <p>
     * By dffbult, tiis mftiod simply dblls <dodf>rfmovfEntry(rowOid)
     * </dodf>.
     * <p>
     * You dbn rfdffinf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid bfibviour wifn b rfmotf row dflftion is invokfd.
     * <p>
     * Notf tibt spfdifid difdks siould not bf implfmfntfd in tiis
     * mftiod, but rbtifr in <dodf>difdkRfmovfTbblfRow()</dodf>.
     * If <dodf>difdkRfmovfTbblfRow()</dodf> suddffds bnd tiis mftiod
     * fbils bftfrwbrd, tif btomidity of tif originbl SET rfqufst dbn no
     * longfr bf gubrbntffd.
     * <p>
     *
     * @pbrbm rfq    Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm dfpti  Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption if tif bdtubl row dflftion fbils.
     *            Tiis siould not ibppfn sindf it would brfbk tif
     *            btomidity of tif SET rfqufst. Spfdifid difdks siould
     *            bf implfmfntfd in <dodf>difdkRfmovfTbblfRow()</dodf>
     *            if nffdfd. If tif fntry dofs not fxists, no fxdfption
     *            is gfnfrbtfd bnd tif mftiod simply rfturns.
     *
     **/
    protfdtfd void rfmovfTbblfRow(SnmpMibSubRfqufst rfq, SnmpOid rowOid,
                                  int dfpti)
        tirows SnmpStbtusExdfption {

        rfmovfEntry(rowOid);
    }

    /**
     * Tiis mftiod tbkfs dbrf of initibl RowStbtus ibndling during tif
     * difdk() pibsf of b SET rfqufst.
     *
     * In pbrtidulbr it will:
     * <ul><li>difdk tibt tif givfn <dodf>rowAdtion</dodf> rfturnfd by
     *         <dodf>gftRowAdtion()</dodf> is vblid.</li>
     * <li>Tifn dfpfnding on tif <dodf>rowAdtion</dodf> spfdififd it will:
     *     <ul><li>fitifr dbll <dodf>drfbtfNfwEntry()</dodf> (<dodf>
     *         rowAdtion = <i>drfbtfAndGo</i> or <i>drfbtfAndWbit</i>
     *         </dodf>),</li>
     *     <li>or dbll <dodf>difdkRfmovfTbblfRow()</dodf> (<dodf>
     *         rowAdtion = <i>dfstroy</i></dodf>),</li>
     *     <li>or dbll <dodf>difdkRowStbtusCibngf()</dodf> (<dodf>
     *         rowAdtion = <i>bdtivf</i> or <i>notInSfrvidf</i></dodf>),</li>
     *     <li>or gfnfrbtf b SnmpStbtusExdfption if tif pbssfd <dodf>
     *         rowAdtion</dodf> is not dorrfdt.</li>
     * </ul></li></ul>
     * <p>
     * In prindiplf, you siould not nffd to rfdffinf tiis mftiod.
     * <p>
     * <dodf>bfginRowAdtion()</dodf> is dbllfd during tif difdk pibsf
     * of b SET rfqufst, bfforf bdtubl difdking on tif vbrbind list
     * is pfrformfd.
     *
     * <p>
     * @pbrbm rfq    Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm dfpti  Tif dfpti rfbdifd in tif OID trff.
     *
     * @pbrbm rowAdtion Tif rfqufstfd bdtion bs rfturnfd by <dodf>
     *        gftRowAdtion()</dodf>: onf of tif RowStbtus dodfs dffinfd in
     *        {@link dom.sun.jmx.snmp.EnumRowStbtus}. Tifsf dodfs
     *        dorrfspond to RowStbtus dodfs bs dffinfd in RFC 2579,
     *        plus tif <i>unspfdififd</i> vbluf wiidi is SNMP Runtimf spfdifid.
     *
     * @fxdfption SnmpStbtusExdfption if tif spfdififd <dodf>rowAdtion</dodf>
     *            is not vblid or dbnnot bf fxfdutfd.
     *            Tiis siould not ibppfn sindf it would brfbk tif
     *            btomidity of tif SET rfqufst. Spfdifid difdks siould
     *            bf implfmfntfd in <dodf>bfginRowAdtion()</dodf> if nffdfd.
     *
     * @sff dom.sun.jmx.snmp.EnumRowStbtus
     **/
    protfdtfd syndironizfd void bfginRowAdtion(SnmpMibSubRfqufst rfq,
                              SnmpOid rowOid, int dfpti, int rowAdtion)
        tirows SnmpStbtusExdfption {
        finbl boolfbn     isnfw  = rfq.isNfwEntry();
        finbl SnmpOid     oid    = rowOid;
        finbl int         bdtion = rowAdtion;

        switdi (bdtion) {
        dbsf EnumRowStbtus.unspfdififd:
            if (isnfw) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "bfginRowAdtion", "Fbilfd to drfbtf row[" +
                            rowOid + "] : RowStbtus = unspfdififd");
                }
                difdkRowStbtusFbil(rfq,SnmpStbtusExdfption.snmpRspNoAddfss);
            }
            brfbk;
        dbsf EnumRowStbtus.drfbtfAndGo:
        dbsf EnumRowStbtus.drfbtfAndWbit:
            if (isnfw) {
                if (isCrfbtionEnbblfd()) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMibTbblf.dlbss.gftNbmf(),
                                "bfginRowAdtion", "Crfbting row[" + rowOid +
                                "] : RowStbtus = drfbtfAndGo | drfbtfAndWbit");
                    }
                    drfbtfNfwEntry(rfq,oid,dfpti);
                } flsf {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMibTbblf.dlbss.gftNbmf(),
                                "bfginRowAdtion", "Cbn't drfbtf row[" + rowOid +
                                "] : RowStbtus = drfbtfAndGo | drfbtfAndWbit " +
                                "but drfbtion is disbblfd");
                    }
                    difdkRowStbtusFbil(rfq,
                       SnmpStbtusExdfption.snmpRspNoAddfss);
                }
            } flsf {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "bfginRowAdtion", "Cbn't drfbtf row[" + rowOid +
                            "] : RowStbtus = drfbtfAndGo | drfbtfAndWbit " +
                            "but row blrfbdy fxists");
                }
                difdkRowStbtusFbil(rfq,
                       SnmpStbtusExdfption.snmpRspIndonsistfntVbluf);
            }
            brfbk;
        dbsf EnumRowStbtus.dfstroy:
            if (isnfw) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "bfginRowAdtion",
                            "Wbrning: dbn't dfstroy row[" + rowOid +
                            "] : RowStbtus = dfstroy but row dofs not fxist");
                }
            } flsf if (!isCrfbtionEnbblfd()) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "bfginRowAdtion",
                            "Cbn't dfstroy row[" + rowOid + "] : " +
                            "RowStbtus = dfstroy but drfbtion is disbblfd");
                }
                difdkRowStbtusFbil(rfq,SnmpStbtusExdfption.snmpRspNoAddfss);
            }
            difdkRfmovfTbblfRow(rfq,rowOid,dfpti);
            brfbk;
        dbsf EnumRowStbtus.bdtivf:
        dbsf EnumRowStbtus.notInSfrvidf:
            if (isnfw) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "bfginRowAdtion", "Cbn't switdi stbtf of row[" +
                            rowOid + "] : spfdififd RowStbtus = bdtivf | " +
                            "notInSfrvidf but row dofs not fxist");
                }
                difdkRowStbtusFbil(rfq,
                        SnmpStbtusExdfption.snmpRspIndonsistfntVbluf);
            }
            difdkRowStbtusCibngf(rfq,rowOid,dfpti,bdtion);
            brfbk;
        dbsf EnumRowStbtus.notRfbdy:
        dffbult:
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                        SnmpMibTbblf.dlbss.gftNbmf(),
                        "bfginRowAdtion", "Invblid RowStbtus vbluf for row[" +
                        rowOid + "] : spfdififd RowStbtus = " + bdtion);
            }
            difdkRowStbtusFbil(rfq,
                    SnmpStbtusExdfption.snmpRspIndonsistfntVbluf);
        }
    }

    /**
     * Tiis mftiod tbkfs dbrf of finbl RowStbtus ibndling during tif
     * sft() pibsf of b SET rfqufst.
     *
     * In pbrtidulbr it will:
     *     <ul><li>fitifr dbll <dodf>sftRowStbtus(<i>bdtivf</i>)</dodf>
     *         (<dodf> rowAdtion = <i>drfbtfAndGo</i> or <i>bdtivf</i>
     *         </dodf>),</li>
     *     <li>or dbll <dodf>sftRowStbtus(<i>notInSfrvidf</i> or <i>
     *         notRfbdy</i>)</dodf> dfpfnding on tif rfsult of <dodf>
     *         isRowRfbdy()</dodf> (<dodf>rowAdtion = <i>drfbtfAndWbit</i>
     *         </dodf>),</li>
     *     <li>or dbll <dodf>sftRowStbtus(<i>notInSfrvidf</i>)</dodf>
     *         (<dodf> rowAdtion = <i>notInSfrvidf</i></dodf>),
     *     <li>or dbll <dodf>rfmovfTbblfRow()</dodf> (<dodf>
     *         rowAdtion = <i>dfstroy</i></dodf>),</li>
     *     <li>or gfnfrbtf b SnmpStbtusExdfption if tif pbssfd <dodf>
     *         rowAdtion</dodf> is not dorrfdt. Tiis siould bf bvoidfd
     *         sindf it would brfbk SET rfqufst btomidity</li>
     *     </ul>
     * <p>
     * In prindiplf, you siould not nffd to rfdffinf tiis mftiod.
     * <p>
     * <dodf>fndRowAdtion()</dodf> is dbllfd during tif sft() pibsf
     * of b SET rfqufst, bftfr tif bdtubl sft() on tif vbrbind list
     * ibs bffn pfrformfd. Tif vbrbind dontbining tif dontrol vbribblf
     * is updbtfd witi tif vbluf rfturnfd by sftRowStbtus() (if it is
     * not <dodf>null</dodf>).
     *
     * <p>
     * @pbrbm rfq    Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm rowOid Tif <CODE>SnmpOid</CODE> idfntifying tif tbblf
     *               row involvfd in tif opfrbtion.
     *
     * @pbrbm dfpti  Tif dfpti rfbdifd in tif OID trff.
     *
     * @pbrbm rowAdtion Tif rfqufstfd bdtion bs rfturnfd by <dodf>
     *        gftRowAdtion()</dodf>: onf of tif RowStbtus dodfs dffinfd in
     *        {@link dom.sun.jmx.snmp.EnumRowStbtus}. Tifsf dodfs
     *        dorrfspond to RowStbtus dodfs bs dffinfd in RFC 2579,
     *        plus tif <i>unspfdififd</i> vbluf wiidi is SNMP Runtimf spfdifid.
     *
     * @fxdfption SnmpStbtusExdfption if tif spfdififd <dodf>rowAdtion</dodf>
     *            is not vblid.
     *
     * @sff dom.sun.jmx.snmp.EnumRowStbtus
     **/
    protfdtfd void fndRowAdtion(SnmpMibSubRfqufst rfq, SnmpOid rowOid,
                               int dfpti, int rowAdtion)
        tirows SnmpStbtusExdfption {
        finbl boolfbn     isnfw  = rfq.isNfwEntry();
        finbl SnmpOid     oid    = rowOid;
        finbl int         bdtion = rowAdtion;
        finbl Objfdt      dbtb   = rfq.gftUsfrDbtb();
        SnmpVbluf         vbluf  = null;

        switdi (bdtion) {
        dbsf EnumRowStbtus.unspfdififd:
            brfbk;
        dbsf EnumRowStbtus.drfbtfAndGo:
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                        SnmpMibTbblf.dlbss.gftNbmf(),
                        "fndRowAdtion", "Sftting RowStbtus to 'bdtivf' " +
                        "for row[" + rowOid + "] : rfqufstfd RowStbtus = " +
                        "drfbtfAndGo");
            }
            vbluf = sftRowStbtus(oid,EnumRowStbtus.bdtivf,dbtb);
            brfbk;
        dbsf EnumRowStbtus.drfbtfAndWbit:
            if (isRowRfbdy(oid,dbtb)) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "fndRowAdtion",
                            "Sftting RowStbtus to 'notInSfrvidf' for row[" +
                            rowOid + "] : rfqufstfd RowStbtus = drfbtfAndWbit");
                }
                vbluf = sftRowStbtus(oid,EnumRowStbtus.notInSfrvidf,dbtb);
            } flsf {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "fndRowAdtion", "Sftting RowStbtus to 'notRfbdy' " +
                            "for row[" + rowOid + "] : rfqufstfd RowStbtus = " +
                            "drfbtfAndWbit");
                }
                vbluf = sftRowStbtus(oid,EnumRowStbtus.notRfbdy,dbtb);
            }
            brfbk;
        dbsf EnumRowStbtus.dfstroy:
            if (isnfw) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "fndRowAdtion",
                            "Wbrning: rfqufstfd RowStbtus = dfstroy, " +
                            "but row[" + rowOid + "] dofs not fxist");
                }
            } flsf {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                            SnmpMibTbblf.dlbss.gftNbmf(),
                            "fndRowAdtion", "Dfstroying row[" + rowOid +
                            "] : rfqufstfd RowStbtus = dfstroy");
                }
            }
            rfmovfTbblfRow(rfq,oid,dfpti);
            brfbk;
        dbsf EnumRowStbtus.bdtivf:
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                        SnmpMibTbblf.dlbss.gftNbmf(),
                        "fndRowAdtion",
                        "Sftting RowStbtus to 'bdtivf' for row[" +
                        rowOid + "] : rfqufstfd RowStbtus = bdtivf");
            }
            vbluf = sftRowStbtus(oid,EnumRowStbtus.bdtivf,dbtb);
            brfbk;
        dbsf EnumRowStbtus.notInSfrvidf:
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                        SnmpMibTbblf.dlbss.gftNbmf(),
                        "fndRowAdtion",
                        "Sftting RowStbtus to 'notInSfrvidf' for row[" +
                        rowOid + "] : rfqufstfd RowStbtus = notInSfrvidf");
            }
            vbluf = sftRowStbtus(oid,EnumRowStbtus.notInSfrvidf,dbtb);
            brfbk;
        dbsf EnumRowStbtus.notRfbdy:
        dffbult:
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                        SnmpMibTbblf.dlbss.gftNbmf(),
                        "fndRowAdtion", "Invblid RowStbtus vbluf for row[" +
                        rowOid + "] : spfdififd RowStbtus = " + bdtion);
            }
            sftRowStbtusFbil(rfq,
                          SnmpStbtusExdfption.snmpRspIndonsistfntVbluf);
        }
        if (vbluf != null) {
            finbl SnmpVbrBind vb = rfq.gftRowStbtusVbrBind();
            if (vb != null) vb.vbluf = vbluf;
        }
    }

    // -------------------------------------------------------------------
    // PROTECTED METHODS - gft nfxt
    // -------------------------------------------------------------------

    /**
     * Rfturn tif nfxt OID brd dorrfsponding to b rfbdbblf dolumnbr
     * objfdt in tif undfrlying fntry OBJECT-TYPE, possibly skipping ovfr
     * tiosf objfdts tibt must not or dbnnot bf rfturnfd.
     * Cblls {@link
     * #gftNfxtVbrEntryId(dom.sun.jmx.snmp.SnmpOid,long,jbvb.lbng.Objfdt)},
     * until
     * {@link #skipEntryVbribblf(dom.sun.jmx.snmp.SnmpOid,long,
     * jbvb.lbng.Objfdt,int)} rfturns fblsf.
     *
     *
     * @pbrbm rowOid Tif OID indfx of tif row involvfd in tif opfrbtion.
     *
     * @pbrbm vbr Id of tif vbribblf wf stbrt from, looking for tif nfxt.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @pbrbm pduVfrsion Protodol vfrsion of tif originbl rfqufst PDU.
     *
     * @rfturn Tif nfxt dolumnbr objfdt id wiidi dbn bf rfturnfd using
     *         tif givfn PDU's protodol vfrsion.
     *
     * @fxdfption SnmpStbtusExdfption If no id is found bftfr tif givfn id.
     *
     **/
    protfdtfd long gftNfxtVbrEntryId(SnmpOid rowOid,
                                     long vbr,
                                     Objfdt usfrDbtb,
                                     int pduVfrsion)
        tirows SnmpStbtusExdfption {

        long vbrid=vbr;
        do {
            vbrid = gftNfxtVbrEntryId(rowOid,vbrid,usfrDbtb);
        } wiilf (skipEntryVbribblf(rowOid,vbrid,usfrDbtb,pduVfrsion));

        rfturn vbrid;
    }

    /**
     * Hook for subdlbssfs.
     * Tif dffbult implfmfntbtion of tiis mftiod is to blwbys rfturn
     * fblsf. Subdlbssfs siould rfdffinf tiis mftiod so tibt it rfturns
     * truf wifn:
     * <ul><li>tif vbribblf is b lfbf tibt is not instbntibtfd,</li>
     * <li>or tif vbribblf is b lfbf wiosf typf dbnnot bf rfturnfd by tibt
     *     vfrsion of tif protodol (f.g. bn Countfr64 witi SNMPv1).</li>
     * </ul>
     *
     * @pbrbm rowOid Tif OID indfx of tif row involvfd in tif opfrbtion.
     *
     * @pbrbm vbr Id of tif vbribblf wf stbrt from, looking for tif nfxt.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @pbrbm pduVfrsion Protodol vfrsion of tif originbl rfqufst PDU.
     *
     * @rfturn truf if tif vbribblf must bf skippfd by tif gft-nfxt
     *         blgoritim.
     */
    protfdtfd boolfbn skipEntryVbribblf(SnmpOid rowOid,
                                        long vbr,
                                        Objfdt usfrDbtb,
                                        int pduVfrsion) {
        rfturn fblsf;
    }

    /**
     * Gft tif <CODE>SnmpOid</CODE> indfx of tif row tibt follows
     * tif givfn <CODE>oid</CODE> in tif tbblf. Tif givfn <CODE>
     * oid</CODE> dofs not nffd to bf b vblid row OID indfx.
     *
     * <p>
     * @pbrbm oid Tif OID from wiidi tif sfbrdi will bfgin.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn Tif nfxt <CODE>SnmpOid</CODE> indfx.
     *
     * @fxdfption SnmpStbtusExdfption Tifrf is no indfx following tif
     *     spfdififd <CODE>oid</CODE> in tif tbblf.
     */
    protfdtfd SnmpOid gftNfxtOid(SnmpOid oid, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {

        if (sizf == 0) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        finbl SnmpOid rfsOid = oid;

        // Just b simplf difdk to spffd up rftrifvbl of lbst flfmfnt ...
        //
        // XX SnmpOid lbst= (SnmpOid) oids.lbstElfmfnt();
        SnmpOid lbst= tbblfoids[tbblfdount-1];
        if (lbst.fqubls(rfsOid)) {
            // Lbst flfmfnt of tif tbblf ...
            //
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        // First find tif oid. Tiis will bllow to spffd up rftrifvbl prodfss
        // during smbrt disdovfry of tbblf (using tif gftNfxt) bs tif
        // mbnbgfmfnt stbtion will usf tif vblid indfx rfturnfd during b
        // prfvious gftNfxt ...
        //

        // Rfturns tif position following tif position bt wiidi rfsOid
        // is found, or tif position bt wiidi rfsOid siould bf insfrtfd.
        //
        finbl int nfwPos = gftInsfrtionPoint(rfsOid,fblsf);

        // If tif position rfturnfd is not out of bound, wf will find
        // tif nfxt flfmfnt in tif brrby.
        //
        if (nfwPos > -1 && nfwPos < sizf) {
            try {
                // XX lbst = (SnmpOid) oids.flfmfntAt(nfwPos);
                lbst = tbblfoids[nfwPos];
            } dbtdi(ArrbyIndfxOutOfBoundsExdfption f) {
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
            }
        } flsf {
            // Wf brf dfbling witi tif lbst flfmfnt of tif tbblf ..
            //
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }


        rfturn lbst;
    }

    /**
     * Rfturn tif first fntry OID rfgistfrfd in tif tbblf.
     *
     * <p>
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn Tif <CODE>SnmpOid</CODE> of tif first fntry in tif tbblf.
     *
     * @fxdfption SnmpStbtusExdfption If tif tbblf is fmpty.
     */
    protfdtfd SnmpOid gftNfxtOid(Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        if (sizf == 0) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }
        // XX rfturn (SnmpOid) oids.firstElfmfnt();
        rfturn tbblfoids[0];
    }

    // -------------------------------------------------------------------
    // Abstrbdt Protfdtfd Mftiods
    // -------------------------------------------------------------------

    /**
     * Tiis mftiod is usfd intfrnblly bnd is implfmfntfd by tif
     * <CODE>SnmpMibTbblf</CODE> subdlbssfs gfnfrbtfd by <CODE>mibgfn</CODE>.
     *
     * <p> Rfturn tif nfxt OID brd dorrfsponding to b rfbdbblf dolumnbr
     *     objfdt in tif undfrlying fntry OBJECT-TYPE.</p>
     *
     * <p>
     * @pbrbm rowOid Tif OID indfx of tif row involvfd in tif opfrbtion.
     *
     * @pbrbm vbr Id of tif vbribblf wf stbrt from, looking for tif nfxt.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn Tif nfxt dolumnbr objfdt id.
     *
     * @fxdfption SnmpStbtusExdfption If no id is found bftfr tif givfn id.
     *
     **/
    bbstrbdt protfdtfd long gftNfxtVbrEntryId(SnmpOid rowOid, long vbr,
                                              Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod is usfd intfrnblly bnd is implfmfntfd by tif
     * <CODE>SnmpMibTbblf</CODE> subdlbssfs gfnfrbtfd by <CODE>mibgfn</CODE>.
     *
     * <p>
     * @pbrbm rowOid Tif OID indfx of tif row involvfd in tif opfrbtion.
     *
     * @pbrbm vbr Tif vbr wf wbnt to vblidbtf.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @fxdfption SnmpStbtusExdfption If tiis id is not vblid.
     *
     */
    bbstrbdt protfdtfd void vblidbtfVbrEntryId(SnmpOid rowOid, long vbr,
                                               Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption;

    /**
     *
     * Tiis mftiod is usfd intfrnblly bnd is implfmfntfd by tif
     * <CODE>SnmpMibTbblf</CODE> subdlbssfs gfnfrbtfd by <CODE>mibgfn</CODE>.
     *
     * <p>
     * @pbrbm rowOid Tif OID indfx of tif row involvfd in tif opfrbtion.
     *
     * @pbrbm vbr Tif OID brd.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @fxdfption SnmpStbtusExdfption If tiis id is not vblid.
     *
     */
    bbstrbdt protfdtfd boolfbn isRfbdbblfEntryId(SnmpOid rowOid, long vbr,
                                                 Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod is usfd intfrnblly bnd is implfmfntfd by tif
     * <CODE>SnmpMibTbblf</CODE> subdlbssfs gfnfrbtfd by <CODE>mibgfn</CODE>.
     */
    bbstrbdt protfdtfd void gft(SnmpMibSubRfqufst rfq,
                                SnmpOid rowOid, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod is usfd intfrnblly bnd is implfmfntfd by tif
     * <CODE>SnmpMibTbblf</CODE> subdlbssfs gfnfrbtfd by <CODE>mibgfn</CODE>.
     */
    bbstrbdt protfdtfd void difdk(SnmpMibSubRfqufst rfq,
                                  SnmpOid rowOid, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod is usfd intfrnblly bnd is implfmfntfd by tif
     * <CODE>SnmpMibTbblf</CODE> subdlbssfs gfnfrbtfd by <CODE>mibgfn</CODE>.
     */
    bbstrbdt protfdtfd void sft(SnmpMibSubRfqufst rfq,
                                SnmpOid rowOid, int dfpti)
        tirows SnmpStbtusExdfption;

    // ----------------------------------------------------------------------
    // PACKAGE METHODS
    // ----------------------------------------------------------------------

    /**
     * Gft tif <CODE>SnmpOid</CODE> indfx of tif row tibt follows tif
     * indfx fxtrbdtfd from tif spfdififd OID brrby.
     * Builds tif SnmpOid dorrfsponding to tif row OID bnd dblls
     * <dodf>gftNfxtOid(oid,usfrDbtb)</dodf>;
     *
     * <p>
     * @pbrbm oid Tif OID brrby.
     *
     * @pbrbm pos Tif position in tif OID brrby bt wiidi tif indfx stbrts.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn Tif nfxt <CODE>SnmpOid</CODE>.
     *
     * @fxdfption SnmpStbtusExdfption Tifrf is no indfx following tif
     *     spfdififd onf in tif tbblf.
     */
    SnmpOid gftNfxtOid(long[] oid, int pos, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {

        // Construdt tif sub-oid stbrting bt pos.
        // Tiis sub-oid dorrfspond to tif oid pbrt just bftfr tif fntry
        // vbribblf oid.
        //
        finbl SnmpOid rfsOid = nfw SnmpEntryOid(oid,pos);

        rfturn gftNfxtOid(rfsOid,usfrDbtb);
    }

    // ---------------------------------------------------------------------
    //
    // Rfgistfr bn fxdfption wifn difdking tif RowStbtus vbribblf
    //
    // ---------------------------------------------------------------------

    stbtid void difdkRowStbtusFbil(SnmpMibSubRfqufst rfq, int frrorStbtus)
        tirows SnmpStbtusExdfption {

        finbl SnmpVbrBind stbtusvb  = rfq.gftRowStbtusVbrBind();
        finbl SnmpStbtusExdfption x = nfw SnmpStbtusExdfption(frrorStbtus);
        rfq.rfgistfrCifdkExdfption(stbtusvb,x);
    }

    // ---------------------------------------------------------------------
    //
    // Rfgistfr bn fxdfption wifn difdking tif RowStbtus vbribblf
    //
    // ---------------------------------------------------------------------

    stbtid void sftRowStbtusFbil(SnmpMibSubRfqufst rfq, int frrorStbtus)
        tirows SnmpStbtusExdfption {

        finbl SnmpVbrBind stbtusvb  = rfq.gftRowStbtusVbrBind();
        finbl SnmpStbtusExdfption x = nfw SnmpStbtusExdfption(frrorStbtus);
        rfq.rfgistfrSftExdfption(stbtusvb,x);
    }

    // ---------------------------------------------------------------------
    //
    // Implfmfnts tif mftiod dffinfd in SnmpMibNodf.
    //
    // ---------------------------------------------------------------------
    @Ovfrridf
    finbl syndironizfd void findHbndlingNodf(SnmpVbrBind vbrbind,
                                             long[] oid, int dfpti,
                                             SnmpRfqufstTrff ibndlfrs)
        tirows SnmpStbtusExdfption {

        finbl int  lfngti = oid.lfngti;

        if (ibndlfrs == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspGfnErr);

        if (dfpti >= lfngti)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);

        if (oid[dfpti] != nodfId)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);

        if (dfpti+2 >= lfngti)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);

        // Cifdks tibt tif oid is vblid
        // vblidbtfOid(oid,dfpti);

        // Gfts tif pbrt of tif OID tibt idfntififs tif fntry
        finbl SnmpOid fntryoid = nfw SnmpEntryOid(oid, dfpti+2);

        // Finds tif fntry: fblsf mfbns tibt tif fntry dofs not fxists
        finbl Objfdt dbtb = ibndlfrs.gftUsfrDbtb();
        finbl boolfbn ibsEntry = dontbins(fntryoid, dbtb);

        // Fbils if tif fntry is not found bnd tif tbblf dofs not
        // not support drfbtion.
        // Wf know tibt tif fntry dofs not fxists if (isfntry == fblsf).
        if (!ibsEntry) {
            if (!ibndlfrs.isCrfbtionAllowfd()) {
                // wf'rf not doing b sft
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
            } flsf if (!isCrfbtionEnbblfd())
                // wf'rf doing b sft but drfbtion is disbblfd.
                tirow nfw
                    SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNoAddfss);
        }

        finbl long   vbr  = oid[dfpti+1];

        // Vblidbtf tif fntry id
        if (ibsEntry) {
            // Tif fntry blrfbdy fxists - vblidbtf tif id
            vblidbtfVbrEntryId(fntryoid,vbr,dbtb);
        }

        // Rfgistfrs tiis nodf for tif idfntififd fntry.
        //
        if (ibndlfrs.isSftRfqufst() && isRowStbtus(fntryoid,vbr,dbtb))

            // Wf only try to idfntify tif RowStbtus for SET opfrbtions
            //
            ibndlfrs.bdd(tiis,dfpti,fntryoid,vbrbind,(!ibsEntry),vbrbind);

        flsf
            ibndlfrs.bdd(tiis,dfpti,fntryoid,vbrbind,(!ibsEntry));
    }


    // ---------------------------------------------------------------------
    //
    // Implfmfnts tif mftiod dffinfd in SnmpMibNodf. Tif blgoritim is vfry
    // lbrgfly inspirfd from tif originbl gftNfxt() mftiod.
    //
    // ---------------------------------------------------------------------
    @Ovfrridf
    finbl syndironizfd long[] findNfxtHbndlingNodf(SnmpVbrBind vbrbind,
                                                   long[] oid,
                                                   int pos,
                                                   int dfpti,
                                                   SnmpRfqufstTrff ibndlfrs,
                                                   AdmCifdkfr difdkfr)
        tirows SnmpStbtusExdfption {

            int lfngti = oid.lfngti;

            if (ibndlfrs == null) {
                // Tiis siould bf donsidfrfd bs b gfnErr, but wf do not wbnt to
                // bbort tif wiolf rfqufst, so wf'rf going to tirow
                // b noSudiObjfdt...
                //
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
            }

            finbl Objfdt dbtb = ibndlfrs.gftUsfrDbtb();
            finbl int pduVfrsion = ibndlfrs.gftRfqufstPduVfrsion();

            long vbr= -1;

            // If tif qufrrifd oid dontbins lfss brds tibn tif OID of tif
            // xxxEntry objfdt, wf must rfturn tif first lfbf undfr tif
            // first dolumnbr objfdt: tif bfst wby to do tibt is to rfsft
            // tif qufrifd oid:
            //   oid[0] = nodfId (brd of tif xxxEntry objfdt)
            //   pos    = 0 (points to tif brd of tif xxxEntry objfdt)
            // tifn wf just ibvf to prodffd...
            //
            if (pos >= lfngti) {
                // tiis will ibvf tif sidf ffffdt to sft
                //    oid[pos] = nodfId
                // bnd
                //    (pos+1) = lfngti
                // so wf won't fbll into tif "flsf if" dbsfs bflow -
                // so using "flsf if" rbtifr tibn "if ..." is gubrbntffd
                // to bf sbff.
                //
                oid = nfw long[1];
                oid[0] = nodfId;
                pos = 0;
                lfngti = 1;
            } flsf if (oid[pos] > nodfId) {
                // oid[pos] is fxpfdtfd to bf tif id of tif xxxEntry ...
                // Tif id rfqufstfd is grfbtfr tibn tif id of tif xxxEntry,
                // so wf won't find tif nfxt flfmfnt in tiis tbblf... (bny
                // flfmfnt in tiis tbblf will ibvf b smbllfr OID)
                //
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
            } flsf if (oid[pos] < nodfId) {
                // wf must rfturn tif first lfbf undfr tif first dolumnbr
                // objfdt, so wf brf bbdk to our first dbsf wifrf pos wbs
                // out of bounds... => rfsft tif oid to dontbin only tif
                // brd of tif xxxEntry objfdt.
                //
                oid = nfw long[1];
                oid[0] = nodfId;
                pos = 0;
                lfngti = 0;
            } flsf if ((pos + 1) < lfngti) {
                // Tif brd bt tif position "pos+1" is tif id of tif dolumnbr
                // objfdt (if: tif id of tif vbribblf in tif tbblf fntry)
                //
                vbr = oid[pos+1];
            }

            // Now tibt wf'vf got fvfrytiing rigit wf dbn bfgin.
            SnmpOid fntryoid;

            if (pos == (lfngti - 1)) {
                // pos points to tif lbst brd in tif oid, bnd tiis brd is
                // gubrbntffd to bf tif xxxEntry id (wf ibvf ibndlfd bll
                // tif otifr possibilitifs bfforf)
                //
                // Wf must tifrfforf rfturn tif first lfbf bflow tif first
                // dolumnbr objfdt in tif tbblf.
                //
                // Gft tif first indfx. If bn fxdfption is rbisfd,
                // tifn it mfbns tibt tif tbblf is fmpty. Wf tius do not
                // ibvf to dbtdi tif fxdfption - wf lft it propbgbtf to
                // tif dbllfr.
                //
                fntryoid = gftNfxtOid(dbtb);
                vbr = gftNfxtVbrEntryId(fntryoid,vbr,dbtb,pduVfrsion);
            } flsf if ( pos == (lfngti-2)) {
                // In tibt dbsf wf ibvf (pos+1) = (lfngti-1), so pos
                // points to tif brd of tif qufrrifd vbribblf (dolumnbr objfdt).
                // Sindf tif rfqufstfd oid stops tifrf, it mfbns wf ibvf
                // to rfturn tif first lfbf undfr tiis dolumnbr objfdt.
                //
                // So wf first gft tif first indfx:
                // Notf: if tiis rbisfs bn fxdfption, tiis mfbns tibt tif tbblf
                // is fmpty, so wf dbn lft tif fxdfption propbgbtf to tif dbllfr.
                //
                fntryoid = gftNfxtOid(dbtb);

                // XXX rfvisit: not fxbdtly pfrffdt:
                //     b spfdifid row dould bf fmpty.. But wf don't know
                //     iow to mbkf tif difffrfndf! => trbdfoff iolfs
                //     in tbblfs dbn't bf propfrly supportfd (bll rows
                //     must ibvf tif sbmf iolfs)
                //
                if (skipEntryVbribblf(fntryoid,vbr,dbtb,pduVfrsion)) {
                    vbr = gftNfxtVbrEntryId(fntryoid,vbr,dbtb,pduVfrsion);
                }
            } flsf {

                // So now tifrf rfmbin onf lbst dbsf, nbmfly: somf pbrt of tif
                // indfx is providfd by tif oid...
                // Wf build b possibly indomplftf bnd invblid indfx from
                // tif OID.
                // Tif pifdf of indfx providfd siould bfgin bt pos+2
                //   oid[pos]   = id of tif xxxEntry objfdt,
                //   oid[pos+1] = id of tif dolumnbr objfdt,
                //   oid[pos+2] ... oid[lfngti-1] = pifdf of indfx.
                //

                // Wf gft tif nfxt indfx following tif providfd indfx.
                // If tiis rbisfs bn fxdfption, tifn it mfbns tibt wf ibvf
                // rfbdifd tif lbst indfx in tif tbblf, bnd wf must tifn
                // try witi tif nfxt dolumnbr objfdt.
                //
                // Bug fix 4269251
                // Tif SnmpIndfx is dffinfd to dontbin b vblid oid:
                // tiis is not bn SNMP rfquirfmfnt for tif gftNfxt rfqufst.
                // So wf no morf usf tif SnmpIndfx but dirfdtly tif SnmpOid.
                //
                try {
                    fntryoid = gftNfxtOid(oid, pos + 2, dbtb);

                    // If tif vbribblf must nf skippfd, fbll tirougi...
                    //
                    // XXX rfvisit: not fxbdtly pfrffdt:
                    //     b spfdifid row dould bf fmpty.. But wf don't know
                    //     iow to mbkf tif difffrfndf! => trbdfoff iolfs
                    //     in tbblfs dbn't bf propfrly supportfd (bll rows
                    //     must ibvf tif sbmf iolfs)
                    //
                    if (skipEntryVbribblf(fntryoid,vbr,dbtb,pduVfrsion)) {
                        tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
                    }
                } dbtdi(SnmpStbtusExdfption sf) {
                    fntryoid = gftNfxtOid(dbtb);
                    vbr = gftNfxtVbrEntryId(fntryoid,vbr,dbtb,pduVfrsion);
                }
            }

            rfturn findNfxtAddfssiblfOid(fntryoid,
                                         vbrbind,
                                         oid,
                                         dfpti,
                                         ibndlfrs,
                                         difdkfr,
                                         dbtb,
                                         vbr);
        }

    privbtf long[] findNfxtAddfssiblfOid(SnmpOid fntryoid,
                                         SnmpVbrBind vbrbind,long[] oid,
                                         int dfpti, SnmpRfqufstTrff ibndlfrs,
                                         AdmCifdkfr difdkfr, Objfdt dbtb,
                                         long vbr)
        tirows SnmpStbtusExdfption {
        finbl int pduVfrsion = ibndlfrs.gftRfqufstPduVfrsion();

        // Loop on fbdi vbr (dolumn)
        wiilf(truf) {
            // Tiis siould not ibppfn. If it ibppfns, (bug, or dustomizfd
            // mftiods rfturning gbrbbgf instfbd of rbising bn fxdfption),
            // it probbbly mfbns tibt tifrf is notiing to rfturn bnywby.
            // So wf tirow tif fxdfption.
            // => will skip to nfxt nodf in tif MIB trff.
            //
            if (fntryoid == null || vbr == -1 ) {
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
            }

            // So ifrf wf know boti tif row (fntryoid) bnd tif dolumn (vbr)
            //

            try {
                // Rbising bn fxdfption ifrf will mbkf tif dbtdi() dlbusf
                // switdi to tif nfxt vbribblf. If `vbr' is not rfbdbblf
                // for tiis spfdifid fntry, it is not rfbdbblf for bny
                // otifr fntry => skip to nfxt dolumn.
                //
                if (!isRfbdbblfEntryId(fntryoid,vbr,dbtb)) {
                    tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
                }

                // Prfpbrf tif rfsult bnd tif ACM difdkfr.
                //
                finbl long[] ftbblf  = fntryoid.longVbluf(fblsf);
                finbl int    flfngti = ftbblf.lfngti;
                finbl long[] rfsult  = nfw long[dfpti + 2 + flfngti];
                rfsult[0] = -1 ; // Bug dftfdtor!

                // Copy tif fntryOid bt tif fnd of `rfsult'
                //
                jbvb.lbng.Systfm.brrbydopy(ftbblf, 0, rfsult,
                                           dfpti+2, flfngti);

                // Sft tif nodf Id bnd vbr Id in rfsult.
                //
                rfsult[dfpti] = nodfId;
                rfsult[dfpti+1] = vbr;

                // Appfnd nodfId.vbrId.<rowOid> to ACM difdkfr.
                //
                difdkfr.bdd(dfpti,rfsult,dfpti,flfngti+2);

                // No wf'rf going to ACM difdk our OID.
                try {
                    difdkfr.difdkCurrfntOid();

                    // No fxdfption tirown by difdkfr => tiis is bll OK!
                    // wf ibvf it: rfgistfr tif ibndlfr bnd rfturn tif
                    // rfsult.
                    //
                    ibndlfrs.bdd(tiis,dfpti,fntryoid,vbrbind,fblsf);
                    rfturn rfsult;
                } dbtdi(SnmpStbtusExdfption f) {
                    // Skip to tif nfxt fntry. If bn fxdfption is
                    // tirown, will bf dbtdi by fndlosing dbtdi
                    // bnd b skip is donf to tif nfxt vbr.
                    //
                    fntryoid = gftNfxtOid(fntryoid, dbtb);
                } finblly {
                    // Clfbn tif difdkfr.
                    //
                    difdkfr.rfmovf(dfpti,flfngti+2);
                }
            } dbtdi(SnmpStbtusExdfption f) {
                // Cbtdiing bn fxdfption ifrf mfbns wf ibvf to skip to tif
                // nfxt dolumn.
                //
                // Bbdk to tif first row.
                fntryoid = gftNfxtOid(dbtb);

                // Find out tif nfxt dolumn.
                //
                vbr = gftNfxtVbrEntryId(fntryoid,vbr,dbtb,pduVfrsion);

            }

            // Tiis siould not ibppfn. If it ibppfns, (bug, or dustomizfd
            // mftiods rfturning gbrbbgf instfbd of rbising bn fxdfption),
            // it probbbly mfbns tibt tifrf is notiing to rfturn bnywby.
            // No nffd to dontinuf, wf tirow bn fxdfption.
            // => will skip to nfxt nodf in tif MIB trff.
            //
            if (fntryoid == null || vbr == -1 ) {
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
            }
        }
    }


    /**
     * Vblidbtf tif spfdififd OID.
     *
     * <p>
     * @pbrbm oid Tif OID brrby.
     *
     * @pbrbm pos Tif position in tif brrby.
     *
     * @fxdfption SnmpStbtusExdfption If tif vblidbtion fbils.
     */
    finbl void vblidbtfOid(long[] oid, int pos) tirows SnmpStbtusExdfption {
        finbl int lfngti= oid.lfngti;

        // Control tif lfngti of tif oid
        //
        if (pos +2 >= lfngti) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        // Cifdk tibt tif fntry idfntififr is spfdififd
        //
        if (oid[pos] != nodfId) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
        }
    }

    // ----------------------------------------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------------------------------------

    /**
     * Enbblf tiis <CODE>SnmpMibTbblf</CODE> to sfnd b notifidbtion.
     *
     * <p>
     * @pbrbm notifidbtion Tif notifidbtion to sfnd.
     */
    privbtf syndironizfd void sfndNotifidbtion(Notifidbtion notifidbtion) {

        // loop on listfnfr
        //
        for(jbvb.util.Enumfrbtion<NotifidbtionListfnfr> k = ibndbbdkTbblf.kfys();
            k.ibsMorfElfmfnts(); ) {

            NotifidbtionListfnfr listfnfr = k.nfxtElfmfnt();

            // Gft tif bssodibtfd ibndbbdk list bnd tif bssodibtfd filtfr list
            //
            jbvb.util.Vfdtor<?> ibndbbdkList = ibndbbdkTbblf.gft(listfnfr) ;
            jbvb.util.Vfdtor<NotifidbtionFiltfr> filtfrList =
                filtfrTbblf.gft(listfnfr) ;

            // loop on ibndbbdk
            //
            jbvb.util.Enumfrbtion<NotifidbtionFiltfr> f = filtfrList.flfmfnts();
            for(jbvb.util.Enumfrbtion<?> i = ibndbbdkList.flfmfnts();
                i.ibsMorfElfmfnts(); ) {

                Objfdt ibndbbdk = i.nfxtElfmfnt();
                NotifidbtionFiltfr filtfr = f.nfxtElfmfnt();

                if ((filtfr == null) ||
                     (filtfr.isNotifidbtionEnbblfd(notifidbtion))) {

                    listfnfr.ibndlfNotifidbtion(notifidbtion,ibndbbdk) ;
                }
            }
        }
    }

    /**
     * Tiis mftiod is usfd by tif SnmpMibTbblf to drfbtf bnd sfnd b tbblf
     * fntry notifidbtion to bll tif listfnfrs rfgistfrfd for tiis kind of
     * notifidbtion.
     *
     * <p>
     * @pbrbm typf Tif notifidbtion typf.
     *
     * @pbrbm timfStbmp Tif notifidbtion fmission dbtf.
     *
     * @pbrbm fntry Tif fntry objfdt.
     */
    privbtf void sfndNotifidbtion(String typf, long timfStbmp,
                                  Objfdt fntry, ObjfdtNbmf nbmf) {

        syndironizfd(tiis) {
            sfqufndfNumbfr = sfqufndfNumbfr + 1;
        }

        SnmpTbblfEntryNotifidbtion notif =
            nfw SnmpTbblfEntryNotifidbtion(typf, tiis, sfqufndfNumbfr,
                                           timfStbmp, fntry, nbmf);

        tiis.sfndNotifidbtion(notif) ;
    }

    /**
     * Rfturn truf if tif fntry idfntififd by tif givfn OID indfx
     * is dontbinfd in tiis tbblf.
     * <p>
     * <b>Do not dbll tiis mftiod dirfdtly</b>.
     * <p>
     * Tiis mftiod is providfd ibs b iook for subdlbssfs.
     * It is dbllfd wifn b gft/sft rfqufst is rfdfivfd in ordfr to
     * dftfrminf wiftifr tif spfdififd fntry is dontbinfd in tif tbblf.
     * You mby wbnt to ovfrridf tiis mftiod if you nffd to pfrform f.g.
     * lbzy fvblubtion of tbblfs (you nffd to updbtf tif tbblf wifn b
     * rfqufst is rfdfivfd) or if your tbblf is virtubl.
     * <p>
     * Notf tibt tiis mftiod is dbllfd by tif Runtimf from witiin b
     * syndironizfd blodk.
     *
     * @pbrbm oid Tif indfx pbrt of tif OID wf'rf looking for.
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn <dodf>truf</dodf> if tif fntry is found, <dodf>fblsf</dodf>
     *         otifrwisf.
     *
     * @sindf 1.5
     **/
    protfdtfd boolfbn dontbins(SnmpOid oid, Objfdt usfrDbtb) {
        rfturn (findObjfdt(oid) > -1);
    }

    /**
     * Look for tif givfn oid in tif OID tbblf (tbblfoids) bnd rfturns
     * its position.
     *
     * <p>
     * @pbrbm oid Tif OID wf'rf looking for.
     *
     * @rfturn Tif position of tif OID in tif tbblf. -1 if tif givfn
     *         OID wbs not found.
     *
     **/
    privbtf int findObjfdt(SnmpOid oid) {
        int low= 0;
        int mbx= sizf - 1;
        SnmpOid pos;
        int domp;
        int durr= low + (mbx-low)/2;
        //Systfm.out.println("Try to rftrifvf: " + oid.toString());
        wiilf (low <= mbx) {

            // XX pos = (SnmpOid) oids.flfmfntAt(durr);
            pos = tbblfoids[durr];

            //Systfm.out.println("Compbrf witi" + pos.toString());
            // nfvfr know ...wf migit find somftiing ...
            //
            domp = oid.dompbrfTo(pos);
            if (domp == 0)
                rfturn durr;

            if (oid.fqubls(pos) == truf) {
                rfturn durr;
            }
            if (domp > 0) {
                low = durr + 1;
            } flsf {
                mbx = durr - 1;
            }
            durr = low + (mbx-low)/2;
        }
        rfturn -1;
    }

    /**
     * Sfbrdi tif position bt wiidi tif givfn oid siould bf insfrtfd
     * in tif OID tbblf (tbblfoids).
     *
     * <p>
     * @pbrbm oid Tif OID wf would likf to insfrt.
     *
     * @pbrbm fbil Tflls wiftifr b SnmpStbtusExdfption must bf gfnfrbtfd
     *             if tif givfn OID is blrfbdy prfsfnt in tif tbblf.
     *
     * @rfturn Tif position bt wiidi tif OID siould bf insfrtfd in
     *         tif tbblf. Wifn tif OID is found, it rfturns tif nfxt
     *         position. Notf tibt it is not vblid to insfrt twidf tif
     *         sbmf OID. Tiis ffbturf is only bn optimizbtion to improvf
     *         tif gftNfxtOid() bfibviour.
     *
     * @fxdfption SnmpStbtusExdfption if tif OID is blrfbdy prfsfnt in tif
     *            tbblf bnd <dodf>fbil</dodf> is <dodf>truf</dodf>.
     *
     **/
    privbtf int gftInsfrtionPoint(SnmpOid oid, boolfbn fbil)
        tirows SnmpStbtusExdfption {

        finbl int fbilStbtus = SnmpStbtusExdfption.snmpRspNotWritbblf;
        int low= 0;
        int mbx= sizf - 1;
        SnmpOid pos;
        int domp;
        int durr= low + (mbx-low)/2;
        wiilf (low <= mbx) {

            // XX pos= (SnmpOid) oids.flfmfntAt(durr);
            pos= tbblfoids[durr];

            // nfvfr know ...wf migit find somftiing ...
            //
            domp= oid.dompbrfTo(pos);

            if (domp == 0) {
                if (fbil)
                    tirow nfw SnmpStbtusExdfption(fbilStbtus,durr);
                flsf
                    rfturn durr+1;
            }

            if (domp>0) {
                low= durr +1;
            } flsf {
                mbx= durr -1;
            }
            durr= low + (mbx-low)/2;
        }
        rfturn durr;
    }

    /**
     * Rfmovf tif OID lodbtfd bt tif givfn position.
     *
     * <p>
     * @pbrbm pos Tif position bt wiidi tif OID to bf rfmovfd is lodbtfd.
     *
     **/
    privbtf void rfmovfOid(int pos) {
        if (pos >= tbblfdount) rfturn;
        if (pos < 0) rfturn;
        finbl int l1 = --tbblfdount-pos;
        tbblfoids[pos] = null;
        if (l1 > 0)
            jbvb.lbng.Systfm.brrbydopy(tbblfoids,pos+1,tbblfoids,pos,l1);
        tbblfoids[tbblfdount] = null;
    }

    /**
     * Insfrt bn OID bt tif givfn position.
     *
     * <p>
     * @pbrbm oid Tif OID to bf insfrtfd in tif tbblf
     * @pbrbm pos Tif position bt wiidi tif OID to bf bddfd is lodbtfd.
     *
     **/
    privbtf void insfrtOid(int pos, SnmpOid oid) {
        if (pos >= tbblfsizf || tbblfdount == tbblfsizf) {
                // Vfdtor must bf fnlbrgfd

                // Sbvf old vfdtor
                finbl SnmpOid[] oldf = tbblfoids;

                // Allodbtf lbrgfr vfdtors
                tbblfsizf += Dfltb;
                tbblfoids = nfw SnmpOid[tbblfsizf];

                // Cifdk pos vblidity
                if (pos > tbblfdount) pos = tbblfdount;
                if (pos < 0) pos = 0;

                finbl int l1 = pos;
                finbl int l2 = tbblfdount - pos;

                // Copy originbl vfdtor up to `pos'
                if (l1 > 0)
                    jbvb.lbng.Systfm.brrbydopy(oldf,0,tbblfoids,0,l1);

                // Copy originbl vfdtor from `pos' to fnd, lfbving
                // bn fmpty room bt `pos' in tif nfw vfdtor.
                if (l2 > 0)
                    jbvb.lbng.Systfm.brrbydopy(oldf,l1,tbblfoids,
                                               l1+1,l2);

            } flsf if (pos < tbblfdount) {
                // Vfdtor is lbrgf fnougi to bddommodbtf onf bdditionbl
                // fntry.
                //
                // Siift vfdtor, mbking bn fmpty room bt `pos'

                jbvb.lbng.Systfm.brrbydopy(tbblfoids,pos,tbblfoids,
                                           pos+1,tbblfdount-pos);
            }

            // Fill tif gbp bt `pos'
            tbblfoids[pos]  = oid;
            tbblfdount++;
    }


    // ----------------------------------------------------------------------
    // PROTECTED VARIABLES
    // ----------------------------------------------------------------------

    /**
     * Tif id of tif dontbinfd fntry objfdt.
     * @sfribl
     */
    protfdtfd int nodfId=1;

    /**
     * Tif MIB to wiidi tif mftbdbtb is linkfd.
     * @sfribl
     */
    protfdtfd SnmpMib tifMib;

    /**
     * <CODE>truf</CODE> if rfmotf drfbtion of fntrifs vib SET opfrbtions
     * is fnbblfd.
     * [dffbult vbluf is <CODE>fblsf</CODE>]
     * @sfribl
     */
    protfdtfd boolfbn drfbtionEnbblfd = fblsf;

    /**
     * Tif fntry fbdtory
     */
    protfdtfd SnmpTbblfEntryFbdtory fbdtory = null;

    // ----------------------------------------------------------------------
    // PRIVATE VARIABLES
    // ----------------------------------------------------------------------

    /**
     * Tif numbfr of flfmfnts in tif tbblf.
     * @sfribl
     */
    privbtf int sizf=0;

    /**
     * Tif list of indfxfs.
     * @sfribl
     */
    //    privbtf Vfdtor indfxfs= nfw Vfdtor();

    /**
     * Tif list of OIDs.
     * @sfribl
     */
    // privbtf Vfdtor oids= nfw Vfdtor();
    privbtf finbl stbtid int Dfltb = 16;
    privbtf int     tbblfdount     = 0;
    privbtf int     tbblfsizf      = Dfltb;
    privbtf SnmpOid tbblfoids[]    = nfw SnmpOid[tbblfsizf];

    /**
     * Tif list of fntrifs.
     * @sfribl
     */
    privbtf finbl Vfdtor<Objfdt> fntrifs= nfw Vfdtor<>();

    /**
     * Tif list of objfdt nbmfs.
     * @sfribl
     */
    privbtf finbl Vfdtor<ObjfdtNbmf> fntrynbmfs= nfw Vfdtor<>();

    /**
     * Cbllbbdk ibndlfrs
     */
    // finbl Vfdtor dbllbbdks = nfw Vfdtor();

    /**
     * Listfnfr ibsitbblf dontbining tif ibnd-bbdk objfdts.
     */
    privbtf Hbsitbblf<NotifidbtionListfnfr, Vfdtor<Objfdt>> ibndbbdkTbblf =
            nfw Hbsitbblf<>();

    /**
     * Listfnfr ibsitbblf dontbining tif filtfr objfdts.
     */
    privbtf Hbsitbblf<NotifidbtionListfnfr, Vfdtor<NotifidbtionFiltfr>>
            filtfrTbblf = nfw Hbsitbblf<>();

    // PACKAGE VARIABLES
    //------------------
    /**
     * SNMP tbblf sfqufndf numbfr.
     * Tif dffbult vbluf is sft to 0.
     */
    trbnsifnt long sfqufndfNumbfr = 0;
}
