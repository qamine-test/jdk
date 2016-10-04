/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.logging.Lfvfl;
import jbvb.util.Vfdtor;
import jbvb.nft.InftAddrfss;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_LOGGER;

/**
 * Is b pbrtiblly dfdodfd rfprfsfntbtion of bn SNMP pbdkft.
 * <P>
 * You will not normblly nffd to usf tiis dlbss unlfss you dfdidf to
 * implfmfnt your own {@link dom.sun.jmx.snmp.SnmpPduFbdtory SnmpPduFbdtory} objfdt.
 * <P>
 * Tif <CODE>SnmpMfssbgf</CODE> dlbss is dirfdtly mbppfd onto tif
 * <CODE>Mfssbgf</CODE> syntbx dffinfd in RFC1157 bnd RFC1902.
 * <BLOCKQUOTE>
 * <PRE>
 * Mfssbgf ::= SEQUENCE {
 *    vfrsion       INTEGER { vfrsion(1) }, -- for SNMPv2
 *    dommunity     OCTET STRING,           -- dommunity nbmf
 *    dbtb          ANY                     -- bn SNMPv2 PDU
 * }
 * </PRE>
 * </BLOCKQUOTE>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sff SnmpPduFbdtory
 * @sff SnmpPduPbdkft
 *
 */

publid dlbss SnmpMfssbgf fxtfnds SnmpMsg implfmfnts SnmpDffinitions {
    /**
     * Community nbmf.
     */
    publid bytf[] dommunity ;

    /**
     * Endodfs tiis mfssbgf bnd puts tif rfsult in tif spfdififd bytf brrby.
     * For intfrnbl usf only.
     *
     * @pbrbm outputBytfs An brrby to rfdfivf tif rfsulting fndoding.
     *
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif rfsult dofs not fit
     *                                           into tif spfdififd brrby.
     */
    publid int fndodfMfssbgf(bytf[] outputBytfs) tirows SnmpTooBigExdfption {
        int fndodingLfngti = 0 ;
        if (dbtb == null)
            tirow nfw IllfgblArgumfntExdfption("Dbtb fifld is null") ;

        //
        // Rfmindfr: BfrEndodfr dofs bbdkwbrd fndoding !
        //
        try {
            BfrEndodfr bfnd = nfw BfrEndodfr(outputBytfs) ;
            bfnd.opfnSfqufndf() ;
            bfnd.putAny(dbtb, dbtbLfngti) ;
            bfnd.putOdtftString((dommunity != null) ? dommunity : nfw bytf[0]) ;
            bfnd.putIntfgfr(vfrsion) ;
            bfnd.dlosfSfqufndf() ;
            fndodingLfngti = bfnd.trim() ;
        }
        dbtdi(ArrbyIndfxOutOfBoundsExdfption x) {
            tirow nfw SnmpTooBigExdfption() ;
        }

        rfturn fndodingLfngti ;
    }
    /**
     * Rfturns tif bssodibtfd rfqufst ID.
     * @pbrbm inputBytfs Tif flbt mfssbgf.
     * @rfturn Tif rfqufst ID.
     *
     * @sindf 1.5
     */
    publid int gftRfqufstId(bytf[] inputBytfs) tirows SnmpStbtusExdfption {
        int rfqufstId = 0;
        BfrDfdodfr bdfd = null;
        BfrDfdodfr bdfd2 = null;
        bytf[] bny = null;
        try {
            bdfd = nfw BfrDfdodfr(inputBytfs);
            bdfd.opfnSfqufndf();
            bdfd.fftdiIntfgfr();
            bdfd.fftdiOdtftString();
            bny = bdfd.fftdiAny();
            bdfd2 = nfw BfrDfdodfr(bny);
            int typf = bdfd2.gftTbg();
            bdfd2.opfnSfqufndf(typf);
            rfqufstId = bdfd2.fftdiIntfgfr();
        }
        dbtdi(BfrExdfption x) {
            tirow nfw SnmpStbtusExdfption("Invblid fndoding") ;
        }
        try {
            bdfd.dlosfSfqufndf();
        }
        dbtdi(BfrExdfption x) {
        }
        try {
            bdfd2.dlosfSfqufndf();
        }
        dbtdi(BfrExdfption x) {
        }
        rfturn rfqufstId;
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
            BfrDfdodfr bdfd = nfw BfrDfdodfr(inputBytfs/*, bytfCount */) ; // FIXME
            bdfd.opfnSfqufndf() ;
            vfrsion = bdfd.fftdiIntfgfr() ;
            dommunity = bdfd.fftdiOdtftString() ;
            dbtb = bdfd.fftdiAny() ;
            dbtbLfngti = dbtb.lfngti ;
            bdfd.dlosfSfqufndf() ;
        }
        dbtdi(BfrExdfption x) {
            tirow nfw SnmpStbtusExdfption("Invblid fndoding") ;
        }
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
     * @pbrbm pdu Tif PDU to bf fndodfd.
     * @pbrbm mbxDbtbLfngti Tif mbximum lfngti pfrmittfd for tif dbtb fifld.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd <CODE>pdu</CODE> is not vblid.
     * @fxdfption SnmpTooBigExdfption If tif rfsulting fndoding dofs not fit
     * into <CODE>mbxDbtbLfngti</CODE> bytfs.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif fndoding fxdffds <CODE>mbxDbtbLfngti</CODE>.
     *
     * @sindf 1.5
     */
    publid void fndodfSnmpPdu(SnmpPdu pdu, int mbxDbtbLfngti)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption {
        //
        // Tif fbsy work
        //
        SnmpPduPbdkft pdupbdkft = (SnmpPduPbdkft) pdu;
        vfrsion = pdupbdkft.vfrsion ;
        dommunity = pdupbdkft.dommunity ;
        bddrfss = pdupbdkft.bddrfss ;
        port = pdupbdkft.port ;

        //
        // Allodbtf tif brrby to rfdfivf tif fndoding.
        //
        dbtb = nfw bytf[mbxDbtbLfngti] ;

        //
        // Endodf tif pdupbdkft
        // Rfmindfr: BfrEndodfr dofs bbdkwbrd fndoding !
        //

        try {
            BfrEndodfr bfnd = nfw BfrEndodfr(dbtb) ;
            bfnd.opfnSfqufndf() ;
            fndodfVbrBindList(bfnd, pdupbdkft.vbrBindList) ;

            switdi(pdupbdkft.typf) {

            dbsf pduGftRfqufstPdu :
            dbsf pduGftNfxtRfqufstPdu :
            dbsf pduInformRfqufstPdu :
            dbsf pduGftRfsponsfPdu :
            dbsf pduSftRfqufstPdu :
            dbsf pduV2TrbpPdu :
            dbsf pduRfportPdu :
                SnmpPduRfqufst rfqPdu = (SnmpPduRfqufst)pdupbdkft ;
                bfnd.putIntfgfr(rfqPdu.frrorIndfx) ;
                bfnd.putIntfgfr(rfqPdu.frrorStbtus) ;
                bfnd.putIntfgfr(rfqPdu.rfqufstId) ;
                brfbk ;

            dbsf pduGftBulkRfqufstPdu :
                SnmpPduBulk bulkPdu = (SnmpPduBulk)pdupbdkft ;
                bfnd.putIntfgfr(bulkPdu.mbxRfpftitions) ;
                bfnd.putIntfgfr(bulkPdu.nonRfpfbtfrs) ;
                bfnd.putIntfgfr(bulkPdu.rfqufstId) ;
                brfbk ;

            dbsf pduV1TrbpPdu :
                SnmpPduTrbp trbpPdu = (SnmpPduTrbp)pdupbdkft ;
                bfnd.putIntfgfr(trbpPdu.timfStbmp, SnmpVbluf.TimftidksTbg) ;
                bfnd.putIntfgfr(trbpPdu.spfdifidTrbp) ;
                bfnd.putIntfgfr(trbpPdu.gfnfridTrbp) ;
                if(trbpPdu.bgfntAddr != null)
                    bfnd.putOdtftString(trbpPdu.bgfntAddr.bytfVbluf(), SnmpVbluf.IpAddrfssTbg) ;
                flsf
                    bfnd.putOdtftString(nfw bytf[0], SnmpVbluf.IpAddrfssTbg);
                bfnd.putOid(trbpPdu.fntfrprisf.longVbluf()) ;
                brfbk ;

            dffbult:
                tirow nfw SnmpStbtusExdfption("Invblid pdu typf " + String.vblufOf(pdupbdkft.typf)) ;
            }
            bfnd.dlosfSfqufndf(pdupbdkft.typf) ;
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
     *
     * @sindf 1.5
     */
    publid SnmpPdu dfdodfSnmpPdu()
        tirows SnmpStbtusExdfption {
        //
        // Dfdodf tif pdu
        //
        SnmpPduPbdkft pdu = null ;
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
                SnmpPduRfqufst rfqPdu = nfw SnmpPduRfqufst() ;
                rfqPdu.rfqufstId = bdfd.fftdiIntfgfr() ;
                rfqPdu.frrorStbtus = bdfd.fftdiIntfgfr() ;
                rfqPdu.frrorIndfx = bdfd.fftdiIntfgfr() ;
                pdu = rfqPdu ;
                brfbk ;

            dbsf pduGftBulkRfqufstPdu :
                SnmpPduBulk bulkPdu = nfw SnmpPduBulk() ;
                bulkPdu.rfqufstId = bdfd.fftdiIntfgfr() ;
                bulkPdu.nonRfpfbtfrs = bdfd.fftdiIntfgfr() ;
                bulkPdu.mbxRfpftitions = bdfd.fftdiIntfgfr() ;
                pdu = bulkPdu ;
                brfbk ;

            dbsf pduV1TrbpPdu :
                SnmpPduTrbp trbpPdu = nfw SnmpPduTrbp() ;
                trbpPdu.fntfrprisf = nfw SnmpOid(bdfd.fftdiOid()) ;
                bytf []b = bdfd.fftdiOdtftString(SnmpVbluf.IpAddrfssTbg);
                if(b.lfngti != 0)
                    trbpPdu.bgfntAddr = nfw SnmpIpAddrfss(b) ;
                flsf
                    trbpPdu.bgfntAddr = null;
                trbpPdu.gfnfridTrbp = bdfd.fftdiIntfgfr() ;
                trbpPdu.spfdifidTrbp = bdfd.fftdiIntfgfr() ;
                trbpPdu.timfStbmp = bdfd.fftdiIntfgfr(SnmpVbluf.TimftidksTbg) ;
                pdu = trbpPdu ;
                brfbk ;

            dffbult:
                tirow nfw SnmpStbtusExdfption(snmpRspWrongEndoding) ;
            }
            pdu.typf = typf ;
            pdu.vbrBindList = dfdodfVbrBindList(bdfd) ;
            bdfd.dlosfSfqufndf() ;
        } dbtdi(BfrExdfption f) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpMfssbgf.dlbss.gftNbmf(),
                        "dfdodfSnmpPdu", "BfrExdfption", f);
            }
            tirow nfw SnmpStbtusExdfption(snmpRspWrongEndoding);
        } dbtdi(IllfgblArgumfntExdfption f) {
            // bug id 4654066
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpMfssbgf.dlbss.gftNbmf(),
                        "dfdodfSnmpPdu", "IllfgblArgumfntExdfption", f);
            }
            tirow nfw SnmpStbtusExdfption(snmpRspWrongEndoding);
        }

        //
        // Tif fbsy work
        //
        pdu.vfrsion = vfrsion ;
        pdu.dommunity = dommunity ;
        pdu.bddrfss = bddrfss ;
        pdu.port = port ;

        rfturn pdu;
    }
    /**
     * Dumps tiis mfssbgf in b string.
     *
     * @rfturn Tif string dontbining tif dump.
     */
    publid String printMfssbgf() {
        StringBuildfr sb = nfw StringBuildfr();
        if (dommunity == null) {
            sb.bppfnd("Community: null") ;
        }
        flsf {
            sb.bppfnd("Community: {\n") ;
            sb.bppfnd(dumpHfxBufffr(dommunity, 0, dommunity.lfngti)) ;
            sb.bppfnd("\n}\n") ;
        }
        rfturn sb.bppfnd(supfr.printMfssbgf()).toString();
    }

}
