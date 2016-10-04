/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.Sfriblizbblf;

// jmx import
//
import dom.sun.jmx.snmp.SnmpPduFbdtory;
import dom.sun.jmx.snmp.SnmpMfssbgf;
import dom.sun.jmx.snmp.SnmpPduPbdkft;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpMsg;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpDffinitions;

// SNMP Runtimf import
//
import dom.sun.jmx.snmp.SnmpV3Mfssbgf;

/**
 * Dffbult implfmfntbtion of tif {@link dom.sun.jmx.snmp.SnmpPduFbdtory SnmpPduFbdtory} intfrfbdf.
 * <BR>It usfs tif BER (bbsid fndoding rulfs) stbndbrdizfd fndoding sdifmf bssodibtfd witi ASN.1.
 * <P>
 * Tiis implfmfntbtion of tif <CODE>SnmpPduFbdtory</CODE> is vfry
 * bbsid: it simply dblls fndoding bnd dfdoding mftiods from
 * {@link dom.sun.jmx.snmp.SnmpMsg}.
 * <BLOCKQUOTE>
 * <PRE>
 * publid SnmpPdu dfdodfSnmpPdu(SnmpMsg msg)
 * tirows SnmpStbtusExdfption {
 *   rfturn msg.dfdodfSnmpPdu() ;
 * }
 *
 * publid SnmpMsg fndodfSnmpPdu(SnmpPdu pdu, int mbxPktSizf)
 * tirows SnmpStbtusExdfption, SnmpTooBigExdfption {
 *   SnmpMsg rfsult = nfw SnmpMfssbgf() ;       // for SNMP v1/v2
 * <I>or</I>
 *   SnmpMsg rfsult = nfw SnmpV3Mfssbgf() ;     // for SNMP v3
 *   rfsult.fndodfSnmpPdu(pdu, mbxPktSizf) ;
 *   rfturn rfsult ;
 * }
 * </PRE>
 * </BLOCKQUOTE>
 * To implfmfnt your own objfdt, you dbn implfmfnt <CODE>SnmpPduFbdtory</CODE>
 * or fxtfnd <CODE>SnmpPduFbdtoryBER</CODE>.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpPduFbdtoryBER implfmfnts SnmpPduFbdtory, Sfriblizbblf {
   privbtf stbtid finbl long sfriblVfrsionUID = -3525318344000547635L;

   /**
     * Cblls {@link dom.sun.jmx.snmp.SnmpMsg#dfdodfSnmpPdu SnmpMsg.dfdodfSnmpPdu}
     * on tif spfdififd mfssbgf bnd rfturns tif rfsulting <CODE>SnmpPdu</CODE>.
     *
     * @pbrbm msg Tif SNMP mfssbgf to bf dfdodfd.
     * @rfturn Tif rfsulting SNMP PDU pbdkft.
     * @fxdfption SnmpStbtusExdfption If tif fndoding is invblid.
     *
     * @sindf 1.5
     */
    publid SnmpPdu dfdodfSnmpPdu(SnmpMsg msg) tirows SnmpStbtusExdfption {
        rfturn msg.dfdodfSnmpPdu();
    }

    /**
     * Endodfs tif spfdififd <CODE>SnmpPdu</CODE> bnd
     * rfturns tif rfsulting <CODE>SnmpMsg</CODE>. If tiis
     * mftiod rfturns null, tif spfdififd <CODE>SnmpPdu</CODE>
     * will bf droppfd bnd tif durrfnt SNMP rfqufst will bf
     * bbortfd.
     *
     * @pbrbm p Tif <CODE>SnmpPdu</CODE> to bf fndodfd.
     * @pbrbm mbxDbtbLfngti Tif sizf limit of tif rfsulting fndoding.
     * @rfturn Null or b fully fndodfd <CODE>SnmpMsg</CODE>.
     * @fxdfption SnmpStbtusExdfption If <CODE>pdu</CODE> dontbins
     *            illfgbl vblufs bnd dbnnot bf fndodfd.
     * @fxdfption SnmpTooBigExdfption If tif rfsulting fndoding dofs not
     *            fit into <CODE>mbxPktSizf</CODE> bytfs.
     *
     * @sindf 1.5
     */
    publid SnmpMsg fndodfSnmpPdu(SnmpPdu p, int mbxDbtbLfngti)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption {
        switdi(p.vfrsion) {
        dbsf SnmpDffinitions.snmpVfrsionOnf:
        dbsf SnmpDffinitions.snmpVfrsionTwo: {
            SnmpMfssbgf rfsult = nfw SnmpMfssbgf();
            rfsult.fndodfSnmpPdu((SnmpPduPbdkft) p, mbxDbtbLfngti);
            rfturn rfsult;
        }
        dbsf SnmpDffinitions.snmpVfrsionTirff: {
            SnmpV3Mfssbgf rfsult = nfw SnmpV3Mfssbgf();
            rfsult.fndodfSnmpPdu(p, mbxDbtbLfngti);
            rfturn rfsult;
        }
        dffbult:
            rfturn null;
        }
    }
}
