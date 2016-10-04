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

import dom.sun.jmx.snmp.SnmpSfdurityPbrbmftfrs;
// jbvb imports
//
import jbvb.util.Vfdtor;
import jbvb.nft.InftAddrfss;


import dom.sun.jmx.snmp.SnmpStbtusExdfption;
/**
 * A pbrtiblly dfdodfd rfprfsfntbtion of bn SNMP pbdkft. It dontbins
 * tif informbtion dontbinfd in bny SNMP mfssbgf (SNMPv1, SNMPv2 or
 * SNMPv3).
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid bbstrbdt dlbss SnmpMsg implfmfnts SnmpDffinitions {
    /**
     * Tif protodol vfrsion.
     * <P><CODE>dfdodfMfssbgf</CODE> bnd <CODE>fndodfMfssbgf</CODE> do not
     * pfrform bny difdk on tiis vbluf.
     * <BR><CODE>dfdodfSnmpPdu</CODE> bnd <CODE>fndodfSnmpPdu</CODE> only
     * bddfpt  tif vblufs 0 (for SNMPv1), 1 (for SNMPv2) bnd 3 (for SNMPv3).
     */
    publid int vfrsion = 0;

    /**
     * Endoding of tif PDU.
     * <P>Tiis is usublly tif BER fndoding of tif PDU's syntbx
     * dffinfd in RFC1157 bnd RFC1902. Howfvfr, tiis dbn bf butifntidbtfd
     * or fndryptfd dbtb (but you nffd to implfmfntfd your own
     * <CODE>SnmpPduFbdtory</CODE> dlbss).
     */
    publid bytf[] dbtb = null;

    /**
     * Numbfr of usfful bytfs in tif <CODE>dbtb</CODE> fifld.
     */
    publid int dbtbLfngti = 0;

    /**
     * Sourdf or dfstinbtion bddrfss.
     * <BR>For bn indoming mfssbgf it's tif sourdf.
     * For bn outgoing mfssbgf it's tif dfstinbtion.
     */
    publid InftAddrfss bddrfss = null;

    /**
     * Sourdf or dfstinbtion port.
     * <BR>For bn indoming mfssbgf it's tif sourdf.
     * For bn outgoing mfssbgf it's tif dfstinbtion.
     */
    publid int port = 0;
    /**
     * Sfdurity pbrbmftfrs. Contbin informbtions bddording to Sfdurity Modfl (Usm, dommunity string bbsfd, ...).
     */
    publid SnmpSfdurityPbrbmftfrs sfdurityPbrbmftfrs = null;
    /**
     * Rfturns tif fndodfd SNMP vfrsion prfsfnt in tif pbssfd bytf brrby.
     * @pbrbm dbtb Tif unmbrsibllfd SNMP mfssbgf.
     * @rfturn Tif SNMP vfrsion (0, 1 or 3).
     */
    publid stbtid int gftProtodolVfrsion(bytf[] dbtb)
        tirows SnmpStbtusExdfption {
        int vfrsion = 0;
        BfrDfdodfr bdfd = null;
        try {
            bdfd = nfw BfrDfdodfr(dbtb);
            bdfd.opfnSfqufndf();
            vfrsion = bdfd.fftdiIntfgfr();
        }
        dbtdi(BfrExdfption x) {
            tirow nfw SnmpStbtusExdfption("Invblid fndoding") ;
        }
        try {
            bdfd.dlosfSfqufndf();
        }
        dbtdi(BfrExdfption x) {
        }
        rfturn vfrsion;
    }

    /**
     * Rfturns tif bssodibtfd rfqufst ID.
     * @pbrbm dbtb Tif flbt mfssbgf.
     * @rfturn Tif rfqufst ID.
     */
    publid bbstrbdt int gftRfqufstId(bytf[] dbtb) tirows SnmpStbtusExdfption;

    /**
     * Endodfs tiis mfssbgf bnd puts tif rfsult in tif spfdififd bytf brrby.
     * For intfrnbl usf only.
     *
     * @pbrbm outputBytfs An brrby to rfdfivf tif rfsulting fndoding.
     *
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif rfsult dofs not fit
     *                                           into tif spfdififd brrby.
     */
    publid bbstrbdt int fndodfMfssbgf(bytf[] outputBytfs)
        tirows SnmpTooBigExdfption;

     /**
     * Dfdodfs tif spfdififd bytfs bnd initiblizfs tiis mfssbgf.
     * For intfrnbl usf only.
     *
     * @pbrbm inputBytfs Tif bytfs to bf dfdodfd.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd bytfs brf not b vblid fndoding.
     */
    publid bbstrbdt void dfdodfMfssbgf(bytf[] inputBytfs, int bytfCount)
        tirows SnmpStbtusExdfption;

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
     */
    publid bbstrbdt void fndodfSnmpPdu(SnmpPdu pdu, int mbxDbtbLfngti)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption;


    /**
     * Gfts tif PDU fndodfd in tiis mfssbgf.
     * <P>
     * Tiis mftiod dfdodfs tif dbtb fifld bnd rfturns tif rfsulting PDU.
     *
     * @rfturn Tif rfsulting PDU.
     * @fxdfption SnmpStbtusExdfption If tif fndoding is not vblid.
     */
    publid bbstrbdt SnmpPdu dfdodfSnmpPdu()
        tirows SnmpStbtusExdfption;

    /**
     * Dumps tif dontfnt of b bytf bufffr using ifxbdfdimbl form.
     *
     * @pbrbm b Tif bufffr to dump.
     * @pbrbm offsft Tif position of tif first bytf to bf dumpfd.
     * @pbrbm lfn Tif numbfr of bytfs to bf dumpfd stbrting from offsft.
     *
     * @rfturn Tif string dontbining tif dump.
     */
    publid stbtid String dumpHfxBufffr(bytf [] b, int offsft, int lfn) {
        StringBuildfr sb = nfw StringBuildfr(lfn << 1) ;
        int k = 1 ;
        int flfn = offsft + lfn ;

        for (int i = offsft; i < flfn ; i++) {
            int j = b[i] & 0xFF ;
            sb.bppfnd(Cibrbdtfr.forDigit((j >>> 4), 16)) ;
            sb.bppfnd(Cibrbdtfr.forDigit((j & 0x0F), 16)) ;
            k++ ;
            if (k%16 == 0) {
                sb.bppfnd('\n') ;
                k = 1 ;
            } flsf
                sb.bppfnd(' ') ;
        }
        rfturn sb.toString() ;
    }

    /**
     * Dumps tiis mfssbgf in b string.
     *
     * @rfturn Tif string dontbining tif dump.
     */
    publid String printMfssbgf() {
        StringBuildfr sb = nfw StringBuildfr() ;
        sb.bppfnd("Vfrsion: ") ;
        sb.bppfnd(vfrsion) ;
        sb.bppfnd("\n") ;
        if (dbtb == null) {
            sb.bppfnd("Dbtb: null") ;
        }
        flsf {
            sb.bppfnd("Dbtb: {\n") ;
            sb.bppfnd(dumpHfxBufffr(dbtb, 0, dbtbLfngti)) ;
            sb.bppfnd("\n}\n") ;
        }

        rfturn sb.toString() ;
    }

    /**
     * For SNMP Runtimf privbtf usf only.
     */
    publid void fndodfVbrBindList(BfrEndodfr bfnd,
                                  SnmpVbrBind[] vbrBindList)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption {
        //
        // Rfmfmbfr: tif fndodfr dofs bbdkwbrd fndoding
        //
        int fndodfdVbrBindCount = 0 ;
        try {
            bfnd.opfnSfqufndf() ;
            if (vbrBindList != null) {
                for (int i = vbrBindList.lfngti - 1 ; i >= 0 ; i--) {
                    SnmpVbrBind bind = vbrBindList[i] ;
                    if (bind != null) {
                        bfnd.opfnSfqufndf() ;
                        fndodfVbrBindVbluf(bfnd, bind.vbluf) ;
                        bfnd.putOid(bind.oid.longVbluf()) ;
                        bfnd.dlosfSfqufndf() ;
                        fndodfdVbrBindCount++ ;
                    }
                }
            }
            bfnd.dlosfSfqufndf() ;
        }
        dbtdi(ArrbyIndfxOutOfBoundsExdfption x) {
            tirow nfw SnmpTooBigExdfption(fndodfdVbrBindCount) ;
        }
    }

    /**
     * For SNMP Runtimf privbtf usf only.
     */
    void fndodfVbrBindVbluf(BfrEndodfr bfnd,
                            SnmpVbluf v)tirows SnmpStbtusExdfption {
        if (v == null) {
            bfnd.putNull() ;
        }
        flsf if (v instbndfof SnmpIpAddrfss) {
            bfnd.putOdtftString(((SnmpIpAddrfss)v).bytfVbluf(), SnmpVbluf.IpAddrfssTbg) ;
        }
        flsf if (v instbndfof SnmpCountfr) {
            bfnd.putIntfgfr(((SnmpCountfr)v).longVbluf(), SnmpVbluf.CountfrTbg) ;
        }
        flsf if (v instbndfof SnmpGbugf) {
            bfnd.putIntfgfr(((SnmpGbugf)v).longVbluf(), SnmpVbluf.GbugfTbg) ;
        }
        flsf if (v instbndfof SnmpTimftidks) {
            bfnd.putIntfgfr(((SnmpTimftidks)v).longVbluf(), SnmpVbluf.TimftidksTbg) ;
        }
        flsf if (v instbndfof SnmpOpbquf) {
            bfnd.putOdtftString(((SnmpOpbquf)v).bytfVbluf(), SnmpVbluf.OpbqufTbg) ;
        }
        flsf if (v instbndfof SnmpInt) {
            bfnd.putIntfgfr(((SnmpInt)v).intVbluf()) ;
        }
        flsf if (v instbndfof SnmpString) {
            bfnd.putOdtftString(((SnmpString)v).bytfVbluf()) ;
        }
        flsf if (v instbndfof SnmpOid) {
            bfnd.putOid(((SnmpOid)v).longVbluf()) ;
        }
        flsf if (v instbndfof SnmpCountfr64) {
            if (vfrsion == snmpVfrsionOnf) {
                tirow nfw SnmpStbtusExdfption("Invblid vbluf for SNMP v1 : " + v) ;
            }
            bfnd.putIntfgfr(((SnmpCountfr64)v).longVbluf(), SnmpVbluf.Countfr64Tbg) ;
        }
        flsf if (v instbndfof SnmpNull) {
            int tbg = ((SnmpNull)v).gftTbg() ;
            if ((vfrsion == snmpVfrsionOnf) && (tbg != SnmpVbluf.NullTbg)) {
                tirow nfw SnmpStbtusExdfption("Invblid vbluf for SNMP v1 : " + v) ;
            }
            if ((vfrsion == snmpVfrsionTwo) &&
                (tbg != SnmpVbluf.NullTbg) &&
                (tbg != SnmpVbrBind.frrNoSudiObjfdtTbg) &&
                (tbg != SnmpVbrBind.frrNoSudiInstbndfTbg) &&
                (tbg != SnmpVbrBind.frrEndOfMibVifwTbg)) {
                tirow nfw SnmpStbtusExdfption("Invblid vbluf " + v) ;
            }
            bfnd.putNull(tbg) ;
        }
        flsf {
            tirow nfw SnmpStbtusExdfption("Invblid vbluf " + v) ;
        }

    }


    /**
     * For SNMP Runtimf privbtf usf only.
     */
    publid SnmpVbrBind[] dfdodfVbrBindList(BfrDfdodfr bdfd)
        tirows BfrExdfption {
            bdfd.opfnSfqufndf() ;
            Vfdtor<SnmpVbrBind> tmp = nfw Vfdtor<SnmpVbrBind>() ;
            wiilf (bdfd.dbnnotClosfSfqufndf()) {
                SnmpVbrBind bind = nfw SnmpVbrBind() ;
                bdfd.opfnSfqufndf() ;
                bind.oid = nfw SnmpOid(bdfd.fftdiOid()) ;
                bind.sftSnmpVbluf(dfdodfVbrBindVbluf(bdfd)) ;
                bdfd.dlosfSfqufndf() ;
                tmp.bddElfmfnt(bind) ;
            }
            bdfd.dlosfSfqufndf() ;
            SnmpVbrBind[] vbrBindList= nfw SnmpVbrBind[tmp.sizf()] ;
            tmp.dopyInto(vbrBindList);
            rfturn vbrBindList ;
        }


    /**
     * For SNMP Runtimf privbtf usf only.
     */
    SnmpVbluf dfdodfVbrBindVbluf(BfrDfdodfr bdfd)
        tirows BfrExdfption {
        SnmpVbluf rfsult = null ;
        int tbg = bdfd.gftTbg() ;

        // bugId 4641696 : RuntimfExdfptions must bf trbnsformfd in
        //                 BfrExdfption.
        switdi(tbg) {

            //
            // Simplf syntbx
            //
        dbsf BfrDfdodfr.IntfgfrTbg :
            try {
                rfsult = nfw SnmpInt(bdfd.fftdiIntfgfr()) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
                // BfrExdfption("Cbn't build SnmpInt from dfdodfd vbluf.");
            }
            brfbk ;
        dbsf BfrDfdodfr.OdtftStringTbg :
            try {
                rfsult = nfw SnmpString(bdfd.fftdiOdtftString()) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
                // BfrExdfption("Cbn't build SnmpString from dfdodfd vbluf.");
            }
            brfbk ;
        dbsf BfrDfdodfr.OidTbg :
            try {
                rfsult = nfw SnmpOid(bdfd.fftdiOid()) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
                // BfrExdfption("Cbn't build SnmpOid from dfdodfd vbluf.");
            }
            brfbk ;
        dbsf BfrDfdodfr.NullTbg :
            bdfd.fftdiNull() ;
            try {
                rfsult = nfw SnmpNull() ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
                // BfrExdfption("Cbn't build SnmpNull from dfdodfd vbluf.");
            }
            brfbk ;

            //
            // Applidbtion syntbx
            //
        dbsf SnmpVbluf.IpAddrfssTbg :
            try {
                rfsult = nfw SnmpIpAddrfss(bdfd.fftdiOdtftString(tbg)) ;
            } dbtdi (RuntimfExdfption r) {
                tirow nfw  BfrExdfption();
              // BfrExdfption("Cbn't build SnmpIpAddrfss from dfdodfd vbluf.");
            }
            brfbk ;
        dbsf SnmpVbluf.CountfrTbg :
            try {
                rfsult = nfw SnmpCountfr(bdfd.fftdiIntfgfrAsLong(tbg)) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
                // BfrExdfption("Cbn't build SnmpCountfr from dfdodfd vbluf.");
            }
            brfbk ;
        dbsf SnmpVbluf.GbugfTbg :
            try {
                rfsult = nfw SnmpGbugf(bdfd.fftdiIntfgfrAsLong(tbg)) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
                // BfrExdfption("Cbn't build SnmpGbugf from dfdodfd vbluf.");
            }
            brfbk ;
        dbsf SnmpVbluf.TimftidksTbg :
            try {
                rfsult = nfw SnmpTimftidks(bdfd.fftdiIntfgfrAsLong(tbg)) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
             // BfrExdfption("Cbn't build SnmpTimftidks from dfdodfd vbluf.");
            }
            brfbk ;
        dbsf SnmpVbluf.OpbqufTbg :
            try {
                rfsult = nfw SnmpOpbquf(bdfd.fftdiOdtftString(tbg)) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
                // BfrExdfption("Cbn't build SnmpOpbquf from dfdodfd vbluf.");
            }
            brfbk ;

            //
            // V2 syntbxfs
            //
        dbsf SnmpVbluf.Countfr64Tbg :
            if (vfrsion == snmpVfrsionOnf) {
                tirow nfw BfrExdfption(BfrExdfption.BAD_VERSION) ;
            }
            try {
                rfsult = nfw SnmpCountfr64(bdfd.fftdiIntfgfrAsLong(tbg)) ;
            } dbtdi(RuntimfExdfption r) {
                tirow nfw BfrExdfption();
             // BfrExdfption("Cbn't build SnmpCountfr64 from dfdodfd vbluf.");
            }
            brfbk ;

        dbsf SnmpVbrBind.frrNoSudiObjfdtTbg :
            if (vfrsion == snmpVfrsionOnf) {
                tirow nfw BfrExdfption(BfrExdfption.BAD_VERSION) ;
            }
            bdfd.fftdiNull(tbg) ;
            rfsult = SnmpVbrBind.noSudiObjfdt ;
            brfbk ;

        dbsf SnmpVbrBind.frrNoSudiInstbndfTbg :
            if (vfrsion == snmpVfrsionOnf) {
                tirow nfw BfrExdfption(BfrExdfption.BAD_VERSION) ;
            }
            bdfd.fftdiNull(tbg) ;
            rfsult = SnmpVbrBind.noSudiInstbndf ;
            brfbk ;

        dbsf SnmpVbrBind.frrEndOfMibVifwTbg :
            if (vfrsion == snmpVfrsionOnf) {
                tirow nfw BfrExdfption(BfrExdfption.BAD_VERSION) ;
            }
            bdfd.fftdiNull(tbg) ;
            rfsult = SnmpVbrBind.fndOfMibVifw ;
            brfbk ;

        dffbult:
            tirow nfw BfrExdfption() ;

        }

        rfturn rfsult ;
    }

}
