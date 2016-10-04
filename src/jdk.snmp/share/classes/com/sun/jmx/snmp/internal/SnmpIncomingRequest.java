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

import dom.sun.jmx.snmp.SnmpSfdurityPbrbmftfrs;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpMsg;

import dom.sun.jmx.snmp.SnmpUnknownSfdModflExdfption;
import dom.sun.jmx.snmp.SnmpBbdSfdurityLfvflExdfption;

/**
<P> An <CODE>SnmpIndomingRfqufst</CODE> ibndlfs boti sidfs of bn indoming SNMP rfqufst:
<ul>
<li> Tif rfqufst. Unmbrsiblling of tif rfdfivfd mfssbgf. </li>
<li> Tif rfsponsf. Mbrsiblling of tif mfssbgf to sfnd. </li>
</ul>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid intfrfbdf SnmpIndomingRfqufst {
    /**
     * Ondf tif indoming rfqufst dfdodfd, rfturns tif dfdodfd sfdurity pbrbmftfrs.
     * @rfturn Tif dfdodfd sfdurity pbrbmftfrs.
     */
    publid SnmpSfdurityPbrbmftfrs gftSfdurityPbrbmftfrs();
     /**
     * Tfsts if b rfport is fxpfdtfd.
     * @rfturn boolfbn indidbting if b rfport is to bf sfnt.
     */
    publid boolfbn isRfport();
    /**
     * Tfsts if b rfsponsf is fxpfdtfd.
     * @rfturn boolfbn indidbting if b rfsponsf is to bf sfnt.
     */
    publid boolfbn isRfsponsf();

    /**
     * Tflls tiis rfqufst tibt no rfsponsf will bf sfnt.
     */
    publid void noRfsponsf();
    /**
     * Gfts tif indoming rfqufst prindipbl.
     * @rfturn Tif rfqufst prindipbl.
     **/
    publid String gftPrindipbl();
    /**
     * Gfts tif indoming rfqufst sfdurity lfvfl. Tiis lfvfl is dffinfd in {@link dom.sun.jmx.snmp.SnmpEnginf SnmpEnginf}.
     * @rfturn Tif sfdurity lfvfl.
     */
    publid int gftSfdurityLfvfl();
    /**
     * Gfts tif indoming rfqufst sfdurity modfl.
     * @rfturn Tif sfdurity modfl.
     */
    publid int gftSfdurityModfl();
    /**
     * Gfts tif indoming rfqufst dontfxt nbmf.
     * @rfturn Tif dontfxt nbmf.
     */
    publid bytf[] gftContfxtNbmf();
    /**
     * Gfts tif indoming rfqufst dontfxt fnginf Id.
     * @rfturn Tif dontfxt fnginf Id.
     */
    publid bytf[] gftContfxtEnginfId();
    /**
     * Gfts tif indoming rfqufst dontfxt nbmf usfd by Addfss Control Modfl in ordfr to bllow or dfny tif bddfss to OIDs.
     */
    publid bytf[] gftAddfssContfxt();
    /**
     * Endodfs tif rfsponsf mfssbgf to sfnd bnd puts tif rfsult in tif spfdififd bytf brrby.
     *
     * @pbrbm outputBytfs An brrby to rfdfivf tif rfsulting fndoding.
     *
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif rfsult dofs not fit
     *                                           into tif spfdififd brrby.
     */
    publid int fndodfMfssbgf(bytf[] outputBytfs)
        tirows SnmpTooBigExdfption;

    /**
     * Dfdodfs tif spfdififd bytfs bnd initiblizfs tif rfqufst witi tif indoming mfssbgf.
     *
     * @pbrbm inputBytfs Tif bytfs to bf dfdodfd.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd bytfs brf not b vblid fndoding or if tif sfdurity bpplifd to tiis rfqufst fbilfd bnd no rfport is to bf sfnt (typidblly trbp PDU).
     */
    publid void dfdodfMfssbgf(bytf[] inputBytfs,
                              int bytfCount,
                              InftAddrfss bddrfss,
                              int port)
        tirows SnmpStbtusExdfption, SnmpUnknownSfdModflExdfption,
               SnmpBbdSfdurityLfvflExdfption;

     /**
     * Initiblizfs tif rfsponsf to sfnd witi tif pbssfd Pdu.
     * <P>
     * If tif fndoding lfngti fxdffds <CODE>mbxDbtbLfngti</CODE>,
     * tif mftiod tirows bn fxdfption.
     *
     * @pbrbm p Tif PDU to bf fndodfd.
     * @pbrbm mbxDbtbLfngti Tif mbximum lfngti pfrmittfd for tif dbtb fifld.
     *
     * @fxdfption SnmpStbtusExdfption If tif spfdififd <CODE>pdu</CODE>
     *     is not vblid.
     * @fxdfption SnmpTooBigExdfption If tif rfsulting fndoding dofs not fit
     * into <CODE>mbxDbtbLfngti</CODE> bytfs.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif fndoding fxdffds
     *   <CODE>mbxDbtbLfngti</CODE>.
     */
    publid SnmpMsg fndodfSnmpPdu(SnmpPdu p,
                                 int mbxDbtbLfngti)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption;

    /**
     * Gfts tif rfqufst PDU fndodfd in tif rfdfivfd mfssbgf.
     * <P>
     * Tiis mftiod dfdodfs tif dbtb fifld bnd rfturns tif rfsulting PDU.
     *
     * @rfturn Tif rfsulting PDU.
     * @fxdfption SnmpStbtusExdfption If tif fndoding is not vblid.
     */
    publid SnmpPdu dfdodfSnmpPdu()
        tirows SnmpStbtusExdfption;

    /**
     * Rfturns b stringififd form of tif rfdfivfd mfssbgf.
     * @rfturn Tif mfssbgf stbtf string.
     */
    publid String printRfqufstMfssbgf();
    /**
     * Rfturns b stringififd form of tif mfssbgf to sfnd.
     * @rfturn Tif mfssbgf stbtf string.
     */
    publid String printRfsponsfMfssbgf();
}
