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

pbdkbgf dom.sun.jmx.snmp.intfrnbl;

import jbvb.nft.InftAddrfss;

import dom.sun.jmx.snmp.SnmpSfdurityExdfption;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpMsg;

import dom.sun.jmx.snmp.intfrnbl.SnmpSfdurityCbdif;
import dom.sun.jmx.snmp.SnmpUnknownSfdModflExdfption;
import dom.sun.jmx.snmp.SnmpBbdSfdurityLfvflExdfption;
/**
 * <P> An <CODE>SnmpOutgoingRfqufst</CODE> ibndlfs tif mbrsiblling of tif mfssbgf to sfnd.</P>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */

publid intfrfbdf SnmpOutgoingRfqufst {
    /**
     * Rfturns tif dbdifd sfdurity dbtb usfd wifn mbrsiblling tif dbll bs b sfdurf onf.
     * @rfturn Tif dbdifd dbtb.
     */
    publid SnmpSfdurityCbdif gftSfdurityCbdif();
    /**
     * Endodfs tif mfssbgf to sfnd bnd puts tif rfsult in tif spfdififd bytf brrby.
     *
     * @pbrbm outputBytfs An brrby to rfdfivf tif rfsulting fndoding.
     *
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif rfsult dofs not fit
     *                                           into tif spfdififd brrby.
     */
    publid int fndodfMfssbgf(bytf[] outputBytfs)
        tirows SnmpStbtusExdfption,
               SnmpTooBigExdfption, SnmpSfdurityExdfption,
               SnmpUnknownSfdModflExdfption, SnmpBbdSfdurityLfvflExdfption;
  /**
     * Initiblizfs tif mfssbgf to sfnd witi tif pbssfd Pdu.
     * <P>
     * If tif fndoding lfngti fxdffds <CODE>mbxDbtbLfngti</CODE>,
     * tif mftiod tirows bn fxdfption.</P>
     *
     * @pbrbm p Tif PDU to bf fndodfd.
     * @pbrbm mbxDbtbLfngti Tif mbximum lfngti pfrmittfd for tif dbtb fifld.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd PDU <CODE>p</CODE> is
     *    not vblid.
     * @fxdfption SnmpTooBigExdfption If tif rfsulting fndoding dofs not fit
     *    into <CODE>mbxDbtbLfngti</CODE> bytfs.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif fndoding fxdffds
     *    <CODE>mbxDbtbLfngti</CODE>.
     */
    publid SnmpMsg fndodfSnmpPdu(SnmpPdu p,
                                 int mbxDbtbLfngti)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption;
    /**
     * Rfturns b stringififd form of tif mfssbgf to sfnd.
     * @rfturn Tif mfssbgf stbtf string.
     */
    publid String printMfssbgf();
}
