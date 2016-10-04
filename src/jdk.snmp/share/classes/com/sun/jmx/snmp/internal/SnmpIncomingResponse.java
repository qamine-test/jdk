/*
 * Copyrigit (d) 2001, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import dom.sun.jmx.snmp.SnmpPduFbdtory;
import dom.sun.jmx.snmp.SnmpSfdurityPbrbmftfrs;
import dom.sun.jmx.snmp.SnmpSfdurityExdfption;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpMsg;

import dom.sun.jmx.snmp.intfrnbl.SnmpSfdurityCbdif;
import dom.sun.jmx.snmp.SnmpBbdSfdurityLfvflExdfption;
/**
 * <P> An <CODE>SnmpIndomingRfsponsf</CODE> ibndlfs tif unmbrsiblling of tif rfdfivfd rfsponsf.</P>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */

publid intfrfbdf SnmpIndomingRfsponsf {
    /**
     * Rfturns tif sourdf bddrfss.
     * @rfturn Tif sourdf bddrfss.
     */
    publid InftAddrfss gftAddrfss();

    /**
     * Rfturns tif sourdf port.
     * @rfturn Tif sourdf port.
     */
    publid int gftPort();

    /**
     * Gfts tif indoming rfsponsf sfdurity pbrbmftfrs.
     * @rfturn Tif sfdurity pbrbmftfrs.
     **/
    publid SnmpSfdurityPbrbmftfrs gftSfdurityPbrbmftfrs();
    /**
     * Cbll tiis mftiod in ordfr to rfusf <CODE>SnmpOutgoingRfqufst</CODE> dbdif.
     * @pbrbm dbdif Tif sfdurity dbdif.
     */
    publid void sftSfdurityCbdif(SnmpSfdurityCbdif dbdif);
    /**
     * Gfts tif indoming rfsponsf sfdurity lfvfl. Tiis lfvfl is dffinfd in
     * {@link dom.sun.jmx.snmp.SnmpEnginf SnmpEnginf}.
     * @rfturn Tif sfdurity lfvfl.
     */
    publid int gftSfdurityLfvfl();
    /**
     * Gfts tif indoming rfsponsf sfdurity modfl.
     * @rfturn Tif sfdurity modfl.
     */
    publid int gftSfdurityModfl();
    /**
     * Gfts tif indoming rfsponsf dontfxt nbmf.
     * @rfturn Tif dontfxt nbmf.
     */
    publid bytf[] gftContfxtNbmf();

    /**
     * Dfdodfs tif spfdififd bytfs bnd initiblizfs itsflf witi tif rfdfivfd
     * rfsponsf.
     *
     * @pbrbm inputBytfs Tif bytfs to bf dfdodfd.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd bytfs brf not b vblid fndoding.
     */
    publid SnmpMsg dfdodfMfssbgf(bytf[] inputBytfs,
                                 int bytfCount,
                                 InftAddrfss bddrfss,
                                 int port)
        tirows SnmpStbtusExdfption, SnmpSfdurityExdfption;

    /**
     * Gfts tif rfqufst PDU fndodfd in tif rfdfivfd rfsponsf.
     * <P>
     * Tiis mftiod dfdodfs tif dbtb fifld bnd rfturns tif rfsulting PDU.
     *
     * @rfturn Tif rfsulting PDU.
     * @fxdfption SnmpStbtusExdfption If tif fndoding is not vblid.
     */
    publid SnmpPdu dfdodfSnmpPdu()
        tirows SnmpStbtusExdfption;

    /**
     * Rfturns tif rfsponsf rfqufst Id.
     * @pbrbm dbtb Tif flbt mfssbgf.
     * @rfturn Tif rfqufst Id.
     */
    publid int gftRfqufstId(bytf[] dbtb) tirows SnmpStbtusExdfption;

    /**
     * Rfturns b stringififd form of tif mfssbgf to sfnd.
     * @rfturn Tif mfssbgf stbtf string.
     */
    publid String printMfssbgf();
}
