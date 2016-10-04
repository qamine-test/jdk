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

import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpUnknownSfdModflExdfption;
import dom.sun.jmx.snmp.SnmpSfdurityExdfption;
import dom.sun.jmx.snmp.SnmpSfdurityPbrbmftfrs;

/**
 * Sfdurity sub systfm intfrfbdf. To bllow fnginf intfgrbtion, b sfdurity sub systfm must implfmfnt tiis intfrfbdf.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
publid intfrfbdf SnmpSfduritySubSystfm fxtfnds SnmpSubSystfm {
     /**
     * Instbntibtfs bn <CODE>SnmpSfdurityCbdif</CODE> tibt is dfpfndbnt to tif modfl implfmfntbtion. Tiis dbll is routfd to tif dfdidbtfd modfl bddording to tif modfl ID.
     * @pbrbm id Tif modfl ID.
     * @rfturn Tif modfl dfpfndbnt sfdurity dbdif.
     */
    publid SnmpSfdurityCbdif drfbtfSfdurityCbdif(int id) tirows SnmpUnknownSfdModflExdfption;
    /**
     * To rflfbsf tif prfviously drfbtfd dbdif. Tiis dbll is routfd to tif dfdidbtfd modfl bddording to tif modfl ID.
     * @pbrbm id Tif modfl ID.
     * @pbrbm dbdif Tif sfdurity dbdif to rflfbsf.
     */
    publid void rflfbsfSfdurityCbdif(int id,
                                     SnmpSfdurityCbdif dbdif) tirows SnmpUnknownSfdModflExdfption;

     /**
     * Cbllfd wifn b rfqufst is to bf sfnt to tif nftwork. It must bf sfdurizfd. Tiis dbll is routfd to tif dfdidbtfd modfl bddording to tif modfl ID.
     * <BR>Tif spfdififd pbrbmftfrs brf dffinfd in RFC 2572 (sff blso tif {@link dom.sun.jmx.snmp.SnmpV3Mfssbgf} dlbss).
     * @pbrbm dbdif Tif dbdif tibt ibs bffn drfbtfd by dblling <CODE>drfbtfSfdurityCbdif</CODE> on tiis modfl.
     * @pbrbm vfrsion Tif SNMP protodol vfrsion.
     * @pbrbm msgID Tif durrfnt rfqufst id.
     * @pbrbm msgMbxSizf Tif mfssbgf mbx sizf.
     * @pbrbm msgFlbgs Tif mfssbgf flbgs (rfportbblf, Auti bnd Priv).
     * @pbrbm msgSfdurityModfl Tiis durrfnt sfdurity modfl.
     * @pbrbm pbrbms Tif sfdurity pbrbmftfrs tibt dontbin tif modfl dfpfndbnt pbrbmftfrs.
     * @pbrbm dontfxtEnginfID Tif dontfxt fnginf ID.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf.
     * @pbrbm dbtb Tif mbrsibllfd vbrbind list
     * @pbrbm dbtbLfngti Tif mbrsibllfd vbrbind list lfngti.
     * @pbrbm outputBytfs Tif bufffr to fill witi sfdurizfd rfqufst. Tiis is b rfprfsfntbtion indfpfndbnt mbrsibllfd formbt. Tiis bufffr will bf sfnt to tif nftwork.
     * @rfturn Tif mbrsibllfd bytf numbfr.
     */
    publid int gfnfrbtfRfqufstMsg(SnmpSfdurityCbdif dbdif,
                                  int vfrsion,
                                  int msgID,
                                  int msgMbxSizf,
                                  bytf msgFlbgs,
                                  int msgSfdurityModfl,
                                  SnmpSfdurityPbrbmftfrs pbrbms,
                                  bytf[] dontfxtEnginfID,
                                  bytf[] dontfxtNbmf,
                                  bytf[] dbtb,
                                  int dbtbLfngti,
                                  bytf[] outputBytfs)
        tirows SnmpTooBigExdfption, SnmpStbtusExdfption, SnmpSfdurityExdfption, SnmpUnknownSfdModflExdfption;

    /**
     * Cbllfd wifn b rfsponsf is to bf sfnt to tif nftwork. It must bf sfdurizfd. Tiis dbll is routfd to tif dfdidbtfd modfl bddording to tif modfl ID.
     * <BR>Tif spfdififd pbrbmftfrs brf dffinfd in RFC 2572 (sff blso tif {@link dom.sun.jmx.snmp.SnmpV3Mfssbgf} dlbss).
     * @pbrbm dbdif Tif dbdif tibt ibs bffn drfbtfd by dblling <CODE>drfbtfSfdurityCbdif</CODE> on tiis modfl.
     * @pbrbm vfrsion Tif SNMP protodol vfrsion.
     * @pbrbm msgID Tif durrfnt rfqufst id.
     * @pbrbm msgMbxSizf Tif mfssbgf mbx sizf.
     * @pbrbm msgFlbgs Tif mfssbgf flbgs (rfportbblf, Auti bnd Priv).
     * @pbrbm msgSfdurityModfl Tiis durrfnt sfdurity modfl.
     * @pbrbm pbrbms Tif sfdurity pbrbmftfrs tibt dontbin tif modfl dfpfndbnt pbrbmftfrs.
     * @pbrbm dontfxtEnginfID Tif dontfxt fnginf ID.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf.
     * @pbrbm dbtb Tif mbrsibllfd vbrbind list
     * @pbrbm dbtbLfngti Tif mbrsibllfd vbrbind list lfngti.
     * @pbrbm outputBytfs Tif bufffr to fill witi sfdurizfd rfqufst. Tiis is b rfprfsfntbtion indfpfndbnt mbrsibllfd formbt. Tiis bufffr will bf sfnt to tif nftwork.
     * @rfturn Tif mbrsibllfd bytf numbfr.
     */
    publid int gfnfrbtfRfsponsfMsg(SnmpSfdurityCbdif dbdif,
                                   int vfrsion,
                                   int msgID,
                                   int msgMbxSizf,
                                   bytf msgFlbgs,
                                   int msgSfdurityModfl,
                                   SnmpSfdurityPbrbmftfrs pbrbms,
                                   bytf[] dontfxtEnginfID,
                                   bytf[] dontfxtNbmf,
                                   bytf[] dbtb,
                                   int dbtbLfngti,
                                   bytf[] outputBytfs)
        tirows SnmpTooBigExdfption, SnmpStbtusExdfption,
               SnmpSfdurityExdfption, SnmpUnknownSfdModflExdfption;
      /**
     * Cbllfd wifn b rfqufst is rfdfivfd from tif nftwork. It ibndlfs butifntidbtion bnd privbdy. Tiis dbll is routfd to tif dfdidbtfd modfl bddording to tif modfl ID.
     * <BR>Tif spfdififd pbrbmftfrs brf dffinfd in RFC 2572 (sff blso tif {@link dom.sun.jmx.snmp.SnmpV3Mfssbgf} dlbss).
     * @pbrbm dbdif Tif dbdif tibt ibs bffn drfbtfd by dblling <CODE>drfbtfSfdurityCbdif</CODE> on tiis modfl.
     * @pbrbm vfrsion Tif SNMP protodol vfrsion.
     * @pbrbm msgID Tif durrfnt rfqufst id.
     * @pbrbm msgMbxSizf Tif mfssbgf mbx sizf.
     * @pbrbm msgFlbgs Tif mfssbgf flbgs (rfportbblf, Auti bnd Priv)
     * @pbrbm msgSfdurityModfl Tiis durrfnt sfdurity modfl.
     * @pbrbm pbrbms Tif sfdurity pbrbmftfrs in b mbrsibllfd formbt. Tif informbtions dointbinfd in tiis brrby brf modfl dfpfndbnt.
     * @pbrbm dontfxtEnginfID Tif dontfxt fnginf ID or null if fndryptfd.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf or null if fndryptfd.
     * @pbrbm dbtb Tif mbrsibllfd vbrbind list or null if fndryptfd.
     * @pbrbm fndryptfdPdu Tif fndryptfd pdu or null if not fndryptfd.
     * @pbrbm dfdryptfdPdu Tif dfdryptfd pdu. If no dfdryption is to bf donf, tif pbssfd dontfxt fnginf ID, dontfxt nbmf bnd dbtb dould bf usfd to fill tiis objfdt.
     * @rfturn Tif dfdodfd sfdurity pbrbmftfrs.

     */
    publid SnmpSfdurityPbrbmftfrs
        prodfssIndomingRfqufst(SnmpSfdurityCbdif dbdif,
                               int vfrsion,
                               int msgID,
                               int msgMbxSizf,
                               bytf msgFlbgs,
                               int msgSfdurityModfl,
                               bytf[] pbrbms,
                               bytf[] dontfxtEnginfID,
                               bytf[] dontfxtNbmf,
                               bytf[] dbtb,
                               bytf[] fndryptfdPdu,
                               SnmpDfdryptfdPdu dfdryptfdPdu)
        tirows SnmpStbtusExdfption, SnmpSfdurityExdfption, SnmpUnknownSfdModflExdfption;
          /**
     * Cbllfd wifn b rfsponsf is rfdfivfd from tif nftwork. It ibndlfs butifntidbtion bnd privbdy. Tiis dbll is routfd to tif dfdidbtfd modfl bddording to tif modfl ID.
     * <BR>Tif spfdififd pbrbmftfrs brf dffinfd in RFC 2572 (sff blso tif {@link dom.sun.jmx.snmp.SnmpV3Mfssbgf} dlbss).
     * @pbrbm dbdif Tif dbdif tibt ibs bffn drfbtfd by dblling <CODE>drfbtfSfdurityCbdif</CODE> on tiis modfl.
     * @pbrbm vfrsion Tif SNMP protodol vfrsion.
     * @pbrbm msgID Tif durrfnt rfqufst id.
     * @pbrbm msgMbxSizf Tif mfssbgf mbx sizf.
     * @pbrbm msgFlbgs Tif mfssbgf flbgs (rfportbblf, Auti bnd Priv).
     * @pbrbm msgSfdurityModfl Tiis durrfnt sfdurity modfl.
     * @pbrbm pbrbms Tif sfdurity pbrbmftfrs in b mbrsibllfd formbt. Tif informbtions dointbinfd in tiis brrby brf modfl dfpfndbnt.
     * @pbrbm dontfxtEnginfID Tif dontfxt fnginf ID or null if fndryptfd.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf or null if fndryptfd.
     * @pbrbm dbtb Tif mbrsibllfd vbrbind list or null if fndryptfd.
     * @pbrbm fndryptfdPdu Tif fndryptfd pdu or null if not fndryptfd.
     * @pbrbm dfdryptfdPdu Tif dfdryptfd pdu. If no dfdryption is to bf donf, tif pbssfd dontfxt fnginf ID, dontfxt nbmf bnd dbtb dould bf usfd to fill tiis objfdt.
     * @rfturn Tif sfdurity pbrbmftfrs.

     */
    publid SnmpSfdurityPbrbmftfrs prodfssIndomingRfsponsf(SnmpSfdurityCbdif dbdif,
                                                          int vfrsion,
                                                          int msgID,
                                                          int msgMbxSizf,
                                                          bytf msgFlbgs,
                                                          int msgSfdurityModfl,
                                                          bytf[] pbrbms,
                                                          bytf[] dontfxtEnginfID,
                                                          bytf[] dontfxtNbmf,
                                                          bytf[] dbtb,
                                                          bytf[] fndryptfdPdu,
                                                          SnmpDfdryptfdPdu dfdryptfdPdu)
        tirows SnmpStbtusExdfption, SnmpSfdurityExdfption, SnmpUnknownSfdModflExdfption;
}
