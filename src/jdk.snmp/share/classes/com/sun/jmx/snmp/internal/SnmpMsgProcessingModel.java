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


import dom.sun.jmx.snmp.mpm.SnmpMsgTrbnslbtor;

import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpPduFbdtory;
import dom.sun.jmx.snmp.SnmpSfdurityPbrbmftfrs;

import dom.sun.jmx.snmp.SnmpPbrbms;
/**
 * Tif mfssbgf prodfssing modfl intfrfbdf. Any mfssbgf prodfssing modfl must implfmfnt tiis intfrfbdf in ordfr to bf intfgrbtfd in tif fnginf frbmfwork.
 * Tif modfl is dbllfd by tif dispbtdifr wifn b dbll is rfdfivfd or wifn b dbll is sfnt.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid intfrfbdf SnmpMsgProdfssingModfl fxtfnds SnmpModfl {
    /**
     * Tiis mftiod is dbllfd wifn b dbll is to bf sfnt to tif nftwork.
     * @pbrbm fbdtory Tif pdu fbdtory to usf to fndodf bnd dfdodf pdu.
     * @rfturn Tif objfdt tibt will ibndlf fvfry stfps of tif sfnding (mbinly mbrsiblling bnd sfdurity).
     */
    publid SnmpOutgoingRfqufst gftOutgoingRfqufst(SnmpPduFbdtory fbdtory);
    /**
     * Tiis mftiod is dbllfd wifn b dbll is rfdfivfd from tif nftwork.
     * @pbrbm fbdtory Tif pdu fbdtory to usf to fndodf bnd dfdodf pdu.
     * @rfturn Tif objfdt tibt will ibndlf fvfry stfps of tif rfdfiving (mbinly unmbrsiblling bnd sfdurity).
     */
    publid SnmpIndomingRfqufst gftIndomingRfqufst(SnmpPduFbdtory fbdtory);

     /**
     * Tiis mftiod is dbllfd wifn b rfsponsf is rfdfivfd from tif nftwork.
     * @pbrbm fbdtory Tif pdu fbdtory to usf to fndodf bnd dfdodf pdu.
     * @rfturn Tif objfdt tibt will ibndlf fvfry stfps of tif rfdfiving (mbinly unmbrsiblling bnd sfdurity).
     */
    publid SnmpIndomingRfsponsf gftIndomingRfsponsf(SnmpPduFbdtory fbdtory);
    /**
     * Tiis mftiod is dbllfd to instbntibtf b pdu bddording to tif pbssfd pdu typf bnd pbrbmftfrs.
     * @pbrbm p Tif rfqufst pbrbmftfrs.
     * @pbrbm typf Tif pdu typf.
     * @rfturn Tif pdu.
     */
    publid SnmpPdu gftRfqufstPdu(SnmpPbrbms p, int typf) tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod is dbllfd to fndodf b full sdopfd pdu tibt ibs not bffn fndryptfd. <CODE>dontfxtNbmf</CODE>, <CODE>dontfxtEnginfID</CODE> bnd dbtb brf known.
     * <BR>Tif spfdififd pbrbmftfrs brf dffinfd in RFC 2572 (sff blso tif {@link dom.sun.jmx.snmp.SnmpV3Mfssbgf} dlbss).
     * @pbrbm vfrsion Tif SNMP protodol vfrsion.
     * @pbrbm msgID Tif SNMP mfssbgf ID.
     * @pbrbm msgMbxSizf Tif mbx mfssbgf sizf.
     * @pbrbm msgFlbgs Tif mfssbgf flbgs.
     * @pbrbm msgSfdurityModfl Tif mfssbgf sfdurity modfl.
     * @pbrbm pbrbms Tif sfdurity pbrbmftfrs.
     * @pbrbm dontfxtEnginfID Tif dontfxt fnginf ID.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf.
     * @pbrbm dbtb Tif fndodfd dbtb.
     * @pbrbm dbtbLfngti Tif fndodfd dbtb lfngti.
     * @pbrbm outputBytfs Tif bufffr dontbining tif fndodfd mfssbgf.
     * @rfturn Tif fndodfd bytfs numbfr.
     */
    publid int fndodf(int vfrsion,
                      int msgID,
                      int msgMbxSizf,
                      bytf msgFlbgs,
                      int msgSfdurityModfl,
                      SnmpSfdurityPbrbmftfrs pbrbms,
                      bytf[] dontfxtEnginfID,
                      bytf[] dontfxtNbmf,
                      bytf[] dbtb,
                      int dbtbLfngti,
                      bytf[] outputBytfs) tirows SnmpTooBigExdfption;
    /**
     * Tiis mftiod is dbllfd to fndodf b full sdopfd pdu tibt bs bffn fndryptfd. <CODE>dontfxtNbmf</CODE>, <CODE>dontfxtEnginfID</CODE> bnd dbtb brf known.
     * <BR>Tif spfdififd pbrbmftfrs brf dffinfd in RFC 2572 (sff blso tif {@link dom.sun.jmx.snmp.SnmpV3Mfssbgf} dlbss).
     * @pbrbm vfrsion Tif SNMP protodol vfrsion.
     * @pbrbm msgID Tif SNMP mfssbgf ID.
     * @pbrbm msgMbxSizf Tif mbx mfssbgf sizf.
     * @pbrbm msgFlbgs Tif mfssbgf flbgs.
     * @pbrbm msgSfdurityModfl Tif mfssbgf sfdurity modfl.
     * @pbrbm pbrbms Tif sfdurity pbrbmftfrs.
     * @pbrbm fndryptfdPdu Tif fndryptfd pdu.
     * @pbrbm outputBytfs Tif bufffr dontbining tif fndodfd mfssbgf.
     * @rfturn Tif fndodfd bytfs numbfr.
     */
    publid int fndodfPriv(int vfrsion,
                          int msgID,
                          int msgMbxSizf,
                          bytf msgFlbgs,
                          int msgSfdurityModfl,
                          SnmpSfdurityPbrbmftfrs pbrbms,
                          bytf[] fndryptfdPdu,
                          bytf[] outputBytfs) tirows SnmpTooBigExdfption;
     /**
     * Tiis mftiod rfturns b dfdodfd sdopfd pdu. Tiis mftiod dfdodfs only tif <CODE>dontfxtEnginfID</CODE>, <CODE>dontfxtNbmf</CODE> bnd dbtb. It is nffdfd by tif <CODE>SnmpSfdurityModfl</CODE> bftfr dfdryption.
     * @pbrbm pdu Tif fndodfd pdu.
     * @rfturn Tif pbrtibly sdopfd pdu.
     */
    publid SnmpDfdryptfdPdu dfdodf(bytf[] pdu) tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod rfturns bn fndodfd sdopfd pdu. Tiis mftiod fndodf only tif <CODE>dontfxtEnginfID</CODE>, <CODE>dontfxtNbmf</CODE> bnd dbtb. It is nffdfd by tif <CODE>SnmpSfdurityModfl</CODE> for dfdryption.
     * @pbrbm pdu Tif pdu to fndodf.
     * @pbrbm outputBytfs Tif pbrtibly sdopfd pdu.
     * @rfturn Tif fndodfd bytfs numbfr.
     */
    publid int fndodf(SnmpDfdryptfdPdu pdu,
                      bytf[] outputBytfs) tirows SnmpTooBigExdfption;

    /**
     * In ordfr to dibngf tif bfibvior of tif trbnslbtor, sft it.
     * @pbrbm trbnslbtor Tif trbnslbtor tibt will bf usfd.
     */
    publid void sftMsgTrbnslbtor(SnmpMsgTrbnslbtor trbnslbtor);

    /**
     * Rfturns tif durrfnt trbnslbtor.
     * @rfturn Tif durrfnt trbnslbtor.
     */
    publid SnmpMsgTrbnslbtor gftMsgTrbnslbtor();
}
