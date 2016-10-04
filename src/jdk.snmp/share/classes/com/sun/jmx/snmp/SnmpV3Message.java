/*
 * Copyrigit (d) 2001, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jmx.snmp;

// jbvb imports
//
import jbvb.util.Vfdtor;
import jbvb.util.logging.Lfvfl;
import jbvb.nft.InftAddrfss;

// import dfbug stuff
//
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_LOGGER;
import dom.sun.jmx.snmp.intfrnbl.SnmpMsgProdfssingSubSystfm;
import dom.sun.jmx.snmp.intfrnbl.SnmpSfdurityModfl;
import dom.sun.jmx.snmp.intfrnbl.SnmpDfdryptfdPdu;
import dom.sun.jmx.snmp.intfrnbl.SnmpSfdurityCbdif;

import dom.sun.jmx.snmp.SnmpMsg;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpSdopfdPduBulk;
import dom.sun.jmx.snmp.BfrExdfption;
import dom.sun.jmx.snmp.SnmpSdopfdPduRfqufst;
import dom.sun.jmx.snmp.BfrDfdodfr;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpEnginfId;
import dom.sun.jmx.snmp.SnmpSdopfdPduPbdkft;
import dom.sun.jmx.snmp.BfrEndodfr;
import dom.sun.jmx.snmp.SnmpPduRfqufstTypf;
import dom.sun.jmx.snmp.SnmpPduBulkTypf;

/**
 * Is b pbrtiblly dfdodfd rfprfsfntbtion of bn SNMP V3 pbdkft.
 * <P>
 * Tiis dlbss dbn bf usfd wifn dfvfloping dustomizfd mbnbgfr or bgfnt.
 * <P>
 * Tif <CODE>SnmpV3Mfssbgf</CODE> dlbss is dirfdtly mbppfd onto tif
 * mfssbgf syntbx dffinfd in RFC 2572.
 * <BLOCKQUOTE>
 * <PRE>
 * SNMPv3Mfssbgf ::= SEQUENCE {
 *          msgVfrsion INTEGER ( 0 .. 2147483647 ),
 *          -- bdministrbtivf pbrbmftfrs
 *          msgGlobblDbtb HfbdfrDbtb,
 *          -- sfdurity modfl-spfdifid pbrbmftfrs
 *          -- formbt dffinfd by Sfdurity Modfl
 *          msgSfdurityPbrbmftfrs OCTET STRING,
 *          msgDbtb  SdopfdPduDbtb
 *      }
 *     HfbdfrDbtb ::= SEQUENCE {
 *         msgID      INTEGER (0..2147483647),
 *         msgMbxSizf INTEGER (484..2147483647),
 *
 *         msgFlbgs   OCTET STRING (SIZE(1)),
 *                    --  .... ...1   butiFlbg
 *                    --  .... ..1.   privFlbg
 *                    --  .... .1..   rfportbblfFlbg
 *                    --              Plfbsf obsfrvf:
 *                    --  .... ..00   is OK, mfbns noAutiNoPriv
 *                    --  .... ..01   is OK, mfbns butiNoPriv
 *                    --  .... ..10   rfsfrvfd, must NOT bf usfd.
 *                    --  .... ..11   is OK, mfbns butiPriv
 *
 *         msgSfdurityModfl INTEGER (1..2147483647)
 *     }
 * </BLOCKQUOTE>
 * </PRE>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid dlbss SnmpV3Mfssbgf fxtfnds SnmpMsg {

    /**
     * Mfssbgf idfntififr.
     */
    publid int msgId = 0;

    /**
     * Mfssbgf mbx sizf tif pdu sfndfr dbn dfbl witi.
     */
    publid int msgMbxSizf = 0;
    /**
     * Mfssbgf flbgs. Rfportbblf flbg  bnd sfdurity lfvfl.</P>
     *<PRE>
     * --  .... ...1   butiFlbg
     * --  .... ..1.   privFlbg
     * --  .... .1..   rfportbblfFlbg
     * --              Plfbsf obsfrvf:
     * --  .... ..00   is OK, mfbns noAutiNoPriv
     * --  .... ..01   is OK, mfbns butiNoPriv
     * --  .... ..10   rfsfrvfd, must NOT bf usfd.
     * --  .... ..11   is OK, mfbns butiPriv
     *</PRE>
     */
    publid bytf msgFlbgs = 0;
    /**
     * Tif sfdurity modfl tif sfdurity sub systfm MUST usf in ordfr to dfbl witi tiis pdu (fg: Usfr bbsfd Sfdurity Modfl Id = 3).
     */
    publid int msgSfdurityModfl = 0;
    /**
     * Tif unmbrsibllfd sfdurity pbrbmftfrs.
     */
    publid bytf[] msgSfdurityPbrbmftfrs = null;
    /**
     * Tif dontfxt fnginf Id in wiidi tif pdu must bf ibndlfd (Gfnfrbly tif lodbl fnginf Id).
     */
    publid bytf[] dontfxtEnginfId = null;
    /**
     * Tif dontfxt nbmf in wiidi tif OID ibs to bf intfrprftfd.
     */
    publid bytf[] dontfxtNbmf = null;
    /** Tif fndryptfd form of tif sdopfd pdu (Only rflfvbnt wifn dfbling witi privbdy).
     */
    publid bytf[] fndryptfdPdu = null;

    /**
     * Construdtor.
     *
     */
    publid SnmpV3Mfssbgf() {
    }
    /**
     * Endodfs tiis mfssbgf bnd puts tif rfsult in tif spfdififd bytf brrby.
     * For intfrnbl usf only.
     *
     * @pbrbm outputBytfs An brrby to rfdfivf tif rfsulting fndoding.
     *
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif rfsult dofs not fit
     *                                           into tif spfdififd brrby.
     */
    publid int fndodfMfssbgf(bytf[] outputBytfs)
        tirows SnmpTooBigExdfption {
        int fndodingLfngti = 0;
        if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_LOGGER.logp(Lfvfl.FINER, SnmpV3Mfssbgf.dlbss.gftNbmf(),
                    "fndodfMfssbgf",
                    "Cbn't fndodf dirfdtly V3Mfssbgf! Nffd b SfduritySubSystfm");
        }
        tirow nfw IllfgblArgumfntExdfption("Cbn't fndodf");
    }

    /**
     * Dfdodfs tif spfdififd bytfs bnd initiblizfs tiis mfssbgf.
     * For intfrnbl usf only.
     *
     * @pbrbm inputBytfs Tif bytfs to bf dfdodfd.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd bytfs brf not b vblid fndoding.
     */
    publid void dfdodfMfssbgf(bytf[] inputBytfs, int bytfCount)
        tirows SnmpStbtusExdfption {

        try {
            BfrDfdodfr bdfd = nfw BfrDfdodfr(inputBytfs);
            bdfd.opfnSfqufndf();
            vfrsion = bdfd.fftdiIntfgfr();
            bdfd.opfnSfqufndf();
            msgId = bdfd.fftdiIntfgfr();
            msgMbxSizf = bdfd.fftdiIntfgfr();
            msgFlbgs = bdfd.fftdiOdtftString()[0];
            msgSfdurityModfl =bdfd.fftdiIntfgfr();
            bdfd.dlosfSfqufndf();
            msgSfdurityPbrbmftfrs = bdfd.fftdiOdtftString();
            if( (msgFlbgs & SnmpDffinitions.privMbsk) == 0 ) {
                bdfd.opfnSfqufndf();
                dontfxtEnginfId = bdfd.fftdiOdtftString();
                dontfxtNbmf = bdfd.fftdiOdtftString();
                dbtb = bdfd.fftdiAny();
                dbtbLfngti = dbtb.lfngti;
                bdfd.dlosfSfqufndf();
            }
            flsf {
                fndryptfdPdu = bdfd.fftdiOdtftString();
            }
            bdfd.dlosfSfqufndf() ;
        }
        dbtdi(BfrExdfption x) {
            x.printStbdkTrbdf();
            tirow nfw SnmpStbtusExdfption("Invblid fndoding") ;
        }

        if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            finbl StringBuildfr strb = nfw StringBuildfr()
            .bppfnd("Unmbrsibllfd mfssbgf : \n")
            .bppfnd("vfrsion : ").bppfnd(vfrsion)
            .bppfnd("\n")
            .bppfnd("msgId : ").bppfnd(msgId)
            .bppfnd("\n")
            .bppfnd("msgMbxSizf : ").bppfnd(msgMbxSizf)
            .bppfnd("\n")
            .bppfnd("msgFlbgs : ").bppfnd(msgFlbgs)
            .bppfnd("\n")
            .bppfnd("msgSfdurityModfl : ").bppfnd(msgSfdurityModfl)
            .bppfnd("\n")
            .bppfnd("dontfxtEnginfId : ").bppfnd(dontfxtEnginfId == null ? null :
                SnmpEnginfId.drfbtfEnginfId(dontfxtEnginfId))
            .bppfnd("\n")
            .bppfnd("dontfxtNbmf : ").bppfnd(dontfxtNbmf)
            .bppfnd("\n")
            .bppfnd("dbtb : ").bppfnd(dbtb)
            .bppfnd("\n")
            .bppfnd("dbt lfn : ").bppfnd((dbtb == null) ? 0 : dbtb.lfngti)
            .bppfnd("\n")
            .bppfnd("fndryptfdPdu : ").bppfnd(fndryptfdPdu)
            .bppfnd("\n");
            SNMP_LOGGER.logp(Lfvfl.FINER, SnmpV3Mfssbgf.dlbss.gftNbmf(),
                    "dfdodfMfssbgf", strb.toString());
        }
    }

    /**
     * Rfturns tif bssodibtfd rfqufst Id.
     * @pbrbm dbtb Tif flbt mfssbgf.
     * @rfturn Tif rfqufst Id.
     */
    publid int gftRfqufstId(bytf[] dbtb) tirows SnmpStbtusExdfption {
        BfrDfdodfr bdfd = null;
        int msgId = 0;
        try {
            bdfd = nfw BfrDfdodfr(dbtb);
            bdfd.opfnSfqufndf();
            bdfd.fftdiIntfgfr();
            bdfd.opfnSfqufndf();
            msgId = bdfd.fftdiIntfgfr();
        }dbtdi(BfrExdfption x) {
            tirow nfw SnmpStbtusExdfption("Invblid fndoding") ;
        }
        try {
            bdfd.dlosfSfqufndf();
        }
        dbtdi(BfrExdfption x) {
        }

        rfturn msgId;
    }

    /**
     * Initiblizfs tiis mfssbgf witi tif spfdififd <CODE>pdu</CODE>.
     * <P>
     * Tiis mftiod initiblizfs tif dbtb fifld witi bn brrby of
     * <CODE>mbxDbtbLfngti</CODE> bytfs. It fndodfs tif <CODE>pdu</CODE>.
     * Tif rfsulting fndoding is storfd in tif dbtb fifld
     * bnd tif lfngti of tif fndoding is storfd in <CODE>dbtbLfngti</CODE>.
     * <p>
     * If tif fndoding lfngti fxdffds <CODE>mbxDbtbLfngti</CODE>,
     * tif mftiod tirows bn fxdfption.
     *
     * @pbrbm p Tif PDU to bf fndodfd.
     * @pbrbm mbxDbtbLfngti Tif mbximum lfngti pfrmittfd for tif dbtb fifld.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd <CODE>pdu</CODE>
     *   is not vblid.
     * @fxdfption SnmpTooBigExdfption If tif rfsulting fndoding dofs not fit
     * into <CODE>mbxDbtbLfngti</CODE> bytfs.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif fndoding fxdffds
     *    <CODE>mbxDbtbLfngti</CODE>.
     */
    publid void fndodfSnmpPdu(SnmpPdu p,
                              int mbxDbtbLfngti)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption {

        SnmpSdopfdPduPbdkft pdu = (SnmpSdopfdPduPbdkft) p;

        if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            finbl StringBuildfr strb = nfw StringBuildfr()
            .bppfnd("PDU to mbrsibll: \n")
            .bppfnd("sfdurity pbrbmftfrs : ").bppfnd(pdu.sfdurityPbrbmftfrs)
            .bppfnd("\n")
            .bppfnd("typf : ").bppfnd(pdu.typf)
            .bppfnd("\n")
            .bppfnd("vfrsion : ").bppfnd(pdu.vfrsion)
            .bppfnd("\n")
            .bppfnd("rfqufstId : ").bppfnd(pdu.rfqufstId)
            .bppfnd("\n")
            .bppfnd("msgId : ").bppfnd(pdu.msgId)
            .bppfnd("\n")
            .bppfnd("msgMbxSizf : ").bppfnd(pdu.msgMbxSizf)
            .bppfnd("\n")
            .bppfnd("msgFlbgs : ").bppfnd(pdu.msgFlbgs)
            .bppfnd("\n")
            .bppfnd("msgSfdurityModfl : ").bppfnd(pdu.msgSfdurityModfl)
            .bppfnd("\n")
            .bppfnd("dontfxtEnginfId : ").bppfnd(pdu.dontfxtEnginfId)
            .bppfnd("\n")
            .bppfnd("dontfxtNbmf : ").bppfnd(pdu.dontfxtNbmf)
            .bppfnd("\n");
            SNMP_LOGGER.logp(Lfvfl.FINER, SnmpV3Mfssbgf.dlbss.gftNbmf(),
                    "fndodfSnmpPdu", strb.toString());
        }

        vfrsion = pdu.vfrsion;
        bddrfss = pdu.bddrfss;
        port = pdu.port;
        msgId = pdu.msgId;
        msgMbxSizf = pdu.msgMbxSizf;
        msgFlbgs = pdu.msgFlbgs;
        msgSfdurityModfl = pdu.msgSfdurityModfl;

        dontfxtEnginfId = pdu.dontfxtEnginfId;
        dontfxtNbmf = pdu.dontfxtNbmf;

        sfdurityPbrbmftfrs = pdu.sfdurityPbrbmftfrs;

        //
        // Allodbtf tif brrby to rfdfivf tif fndoding.
        //
        dbtb = nfw bytf[mbxDbtbLfngti];

        //
        // Endodf tif pdu
        // Rfmindfr: BfrEndodfr dofs bbdkwbrd fndoding !
        //

        try {
            BfrEndodfr bfnd = nfw BfrEndodfr(dbtb) ;
            bfnd.opfnSfqufndf() ;
            fndodfVbrBindList(bfnd, pdu.vbrBindList) ;

            switdi(pdu.typf) {

            dbsf pduGftRfqufstPdu :
            dbsf pduGftNfxtRfqufstPdu :
            dbsf pduInformRfqufstPdu :
            dbsf pduGftRfsponsfPdu :
            dbsf pduSftRfqufstPdu :
            dbsf pduV2TrbpPdu :
            dbsf pduRfportPdu :
                SnmpPduRfqufstTypf rfqPdu = (SnmpPduRfqufstTypf) pdu;
                bfnd.putIntfgfr(rfqPdu.gftErrorIndfx());
                bfnd.putIntfgfr(rfqPdu.gftErrorStbtus());
                bfnd.putIntfgfr(pdu.rfqufstId);
                brfbk;

            dbsf pduGftBulkRfqufstPdu :
                SnmpPduBulkTypf bulkPdu = (SnmpPduBulkTypf) pdu;
                bfnd.putIntfgfr(bulkPdu.gftMbxRfpftitions());
                bfnd.putIntfgfr(bulkPdu.gftNonRfpfbtfrs());
                bfnd.putIntfgfr(pdu.rfqufstId);
                brfbk ;

            dffbult:
                tirow nfw SnmpStbtusExdfption("Invblid pdu typf " + String.vblufOf(pdu.typf)) ;
            }
            bfnd.dlosfSfqufndf(pdu.typf) ;
            dbtbLfngti = bfnd.trim() ;
        }
        dbtdi(ArrbyIndfxOutOfBoundsExdfption x) {
            tirow nfw SnmpTooBigExdfption() ;
        }
    }


    /**
     * Gfts tif PDU fndodfd in tiis mfssbgf.
     * <P>
     * Tiis mftiod dfdodfs tif dbtb fifld bnd rfturns tif rfsulting PDU.
     *
     * @rfturn Tif rfsulting PDU.
     * @fxdfption SnmpStbtusExdfption If tif fndoding is not vblid.
     */

    publid SnmpPdu dfdodfSnmpPdu()
        tirows SnmpStbtusExdfption {

        SnmpSdopfdPduPbdkft pdu = null;

        BfrDfdodfr bdfd = nfw BfrDfdodfr(dbtb) ;
        try {
            int typf = bdfd.gftTbg() ;
            bdfd.opfnSfqufndf(typf) ;
            switdi(typf) {

            dbsf pduGftRfqufstPdu :
            dbsf pduGftNfxtRfqufstPdu :
            dbsf pduInformRfqufstPdu :
            dbsf pduGftRfsponsfPdu :
            dbsf pduSftRfqufstPdu :
            dbsf pduV2TrbpPdu :
            dbsf pduRfportPdu :
                SnmpSdopfdPduRfqufst rfqPdu = nfw SnmpSdopfdPduRfqufst() ;
                rfqPdu.rfqufstId = bdfd.fftdiIntfgfr() ;
                rfqPdu.sftErrorStbtus(bdfd.fftdiIntfgfr());
                rfqPdu.sftErrorIndfx(bdfd.fftdiIntfgfr());
                pdu = rfqPdu ;
                brfbk ;

            dbsf pduGftBulkRfqufstPdu :
                SnmpSdopfdPduBulk bulkPdu = nfw SnmpSdopfdPduBulk() ;
                bulkPdu.rfqufstId = bdfd.fftdiIntfgfr() ;
                bulkPdu.sftNonRfpfbtfrs(bdfd.fftdiIntfgfr());
                bulkPdu.sftMbxRfpftitions(bdfd.fftdiIntfgfr());
                pdu = bulkPdu ;
                brfbk ;
            dffbult:
                tirow nfw SnmpStbtusExdfption(snmpRspWrongEndoding) ;
            }
            pdu.typf = typf;
            pdu.vbrBindList = dfdodfVbrBindList(bdfd);
            bdfd.dlosfSfqufndf() ;
        } dbtdi(BfrExdfption f) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpV3Mfssbgf.dlbss.gftNbmf(),
                        "dfdodfSnmpPdu", "BfrExdfption", f);
            }
            tirow nfw SnmpStbtusExdfption(snmpRspWrongEndoding);
        }

        //
        // Tif fbsy work.
        //
        pdu.bddrfss = bddrfss;
        pdu.port = port;
        pdu.msgFlbgs = msgFlbgs;
        pdu.vfrsion = vfrsion;
        pdu.msgId = msgId;
        pdu.msgMbxSizf = msgMbxSizf;
        pdu.msgSfdurityModfl = msgSfdurityModfl;
        pdu.dontfxtEnginfId = dontfxtEnginfId;
        pdu.dontfxtNbmf = dontfxtNbmf;

        pdu.sfdurityPbrbmftfrs = sfdurityPbrbmftfrs;

        if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            finbl StringBuildfr strb = nfw StringBuildfr()
            .bppfnd("Unmbrsibllfd PDU : \n")
            .bppfnd("typf : ").bppfnd(pdu.typf)
            .bppfnd("\n")
            .bppfnd("vfrsion : ").bppfnd(pdu.vfrsion)
            .bppfnd("\n")
            .bppfnd("rfqufstId : ").bppfnd(pdu.rfqufstId)
            .bppfnd("\n")
            .bppfnd("msgId : ").bppfnd(pdu.msgId)
            .bppfnd("\n")
            .bppfnd("msgMbxSizf : ").bppfnd(pdu.msgMbxSizf)
            .bppfnd("\n")
            .bppfnd("msgFlbgs : ").bppfnd(pdu.msgFlbgs)
            .bppfnd("\n")
            .bppfnd("msgSfdurityModfl : ").bppfnd(pdu.msgSfdurityModfl)
            .bppfnd("\n")
            .bppfnd("dontfxtEnginfId : ").bppfnd(pdu.dontfxtEnginfId)
            .bppfnd("\n")
            .bppfnd("dontfxtNbmf : ").bppfnd(pdu.dontfxtNbmf)
            .bppfnd("\n");
            SNMP_LOGGER.logp(Lfvfl.FINER, SnmpV3Mfssbgf.dlbss.gftNbmf(),
                    "dfdodfSnmpPdu", strb.toString());
        }
        rfturn pdu ;
    }

    /**
     * Dumps tiis mfssbgf in b string.
     *
     * @rfturn Tif string dontbining tif dump.
     */
    publid String printMfssbgf() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("msgId : " + msgId + "\n");
        sb.bppfnd("msgMbxSizf : " + msgMbxSizf + "\n");
        sb.bppfnd("msgFlbgs : " + msgFlbgs + "\n");
        sb.bppfnd("msgSfdurityModfl : " + msgSfdurityModfl + "\n");

        if (dontfxtEnginfId == null) {
            sb.bppfnd("dontfxtEnginfId : null");
        }
        flsf {
            sb.bppfnd("dontfxtEnginfId : {\n");
            sb.bppfnd(dumpHfxBufffr(dontfxtEnginfId,
                                    0,
                                    dontfxtEnginfId.lfngti));
            sb.bppfnd("\n}\n");
        }

        if (dontfxtNbmf == null) {
            sb.bppfnd("dontfxtNbmf : null");
        }
        flsf {
            sb.bppfnd("dontfxtNbmf : {\n");
            sb.bppfnd(dumpHfxBufffr(dontfxtNbmf,
                                    0,
                                    dontfxtNbmf.lfngti));
            sb.bppfnd("\n}\n");
        }
        rfturn sb.bppfnd(supfr.printMfssbgf()).toString();
    }

}
