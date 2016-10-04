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

import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpPdu;
/**
 * Addfss Control Modfl intfrfbdf. Evfry bddfss dontrol modfl must implfmfnt tiis intfrfbdf in ordfr to bf intfgrbtfd in tif fnginf bbsfd frbmfwork.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid intfrfbdf SnmpAddfssControlModfl fxtfnds SnmpModfl {
    /**
     * Mftiod dbllfd by tif dispbtdifr in ordfr to dontrol tif bddfss bt bn <CODE>SnmpOid</CODE> Lfvfl. If bddfss is not bllowfd, bn <CODE>SnmpStbtusExdfption</CODE> is tirown.
     * Tiis mftiod is dbllfd bftfr tif <CODE>difdkPduAddfss</CODE> pdu bbsfd mftiod.
     * @pbrbm vfrsion Tif SNMP protodol vfrsion numbfr.
     * @pbrbm prindipbl Tif rfqufst prindipbl.
     * @pbrbm sfdurityLfvfl Tif rfqufst sfdurity lfvfl bs dffinfd in <CODE>SnmpEnginf</CODE>.
     * @pbrbm pduTypf Tif pdu typf (gft, sft, ...).
     * @pbrbm sfdurityModfl Tif sfdurity modfl ID.
     * @pbrbm dontfxtNbmf Tif bddfss dontrol dontfxt nbmf.
     * @pbrbm oid Tif OID to difdk.
     */
    publid void difdkAddfss(int vfrsion,
                            String prindipbl,
                            int sfdurityLfvfl,
                            int pduTypf,
                            int sfdurityModfl,
                            bytf[] dontfxtNbmf,
                            SnmpOid oid)
        tirows SnmpStbtusExdfption;
    /**
     * Mftiod dbllfd by tif dispbtdifr in ordfr to dontrol tif bddfss bt bn SNMP pdu Lfvfl. If bddfss is not bllowfd, bn <CODE>SnmpStbtusExdfption</CODE> is tirown. In dbsf of fxdfption, tif bddfss dontrol is bbortfd. OIDs brf not difdkfd.
     * Tiis mftiod siould bf dbllfd prior to tif <CODE>difdkAddfss</CODE> OID bbsfd mftiod.
     * @pbrbm vfrsion Tif SNMP protodol vfrsion numbfr.
     * @pbrbm prindipbl Tif rfqufst prindipbl.
     * @pbrbm sfdurityLfvfl Tif rfqufst sfdurity lfvfl bs dffinfd in <CODE>SnmpEnginf</CODE>.
     * @pbrbm pduTypf Tif pdu typf (gft, sft, ...).
     * @pbrbm sfdurityModfl Tif sfdurity modfl ID.
     * @pbrbm dontfxtNbmf Tif bddfss dontrol dontfxt nbmf.
     * @pbrbm pdu Tif pdu to difdk.
     */
    publid void difdkPduAddfss(int vfrsion,
                               String prindipbl,
                               int sfdurityLfvfl,
                               int pduTypf,
                               int sfdurityModfl,
                               bytf[] dontfxtNbmf,
                               SnmpPdu pdu)
        tirows SnmpStbtusExdfption;

    /**
     * Enbblf SNMP V1 bnd V2 sft rfqufsts. Bf bwbrf tibt dbn lfbd to b sfdurity iolf in b dontfxt of SNMP V3 mbnbgfmfnt. By dffbult SNMP V1 bnd V2 sft rfqufsts brf not butiorizfd.
     * @rfturn boolfbn Truf tif bdtivbtion sudffdfd.
     */
    publid boolfbn fnbblfSnmpV1V2SftRfqufst();
    /**
     * Disbblf SNMP V1 bnd V2 sft rfqufsts. By dffbult SNMP V1 bnd V2 sft rfqufsts brf not butiorizfd.
     * @rfturn boolfbn Truf tif dfbdtivbtion sudffdfd.
     */
    publid boolfbn disbblfSnmpV1V2SftRfqufst();

    /**
     * Tif SNMP V1 bnd V2 sft rfqufsts butiorizbtion stbtus. By dffbult SNMP V1 bnd V2 sft rfqufsts brf not butiorizfd.
     * @rfturn boolfbn Truf SNMP V1 bnd V2 rfqufsts brf butiorizfd.
     */
    publid boolfbn isSnmpV1V2SftRfqufstAutiorizfd();
}
