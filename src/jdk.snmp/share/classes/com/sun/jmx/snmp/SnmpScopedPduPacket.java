/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Sfriblizbblf;

import dom.sun.jmx.snmp.SnmpSfdurityPbrbmftfrs;

import dom.sun.jmx.snmp.SnmpDffinitions;
/**
 * Is tif fully dfdodfd rfprfsfntbtion of bn SNMP V3 pbdkft.
 * <P>
 *
 * Clbssfs brf dfrivfd from <CODE>SnmpPdu</CODE> to
 * rfprfsfnt tif difffrfnt forms of SNMP pdu
 * ({@link dom.sun.jmx.snmp.SnmpSdopfdPduRfqufst SnmpSdopfdPduRfqufst},
 * {@link dom.sun.jmx.snmp.SnmpSdopfdPduBulk SnmpSdopfdPduBulk}).
 * <BR>Tif <CODE>SnmpSdopfdPduPbdkft</CODE> dlbss dffinfs tif bttributfs
 * dommon to fvfry sdopfd SNMP pbdkfts.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sff SnmpV3Mfssbgf
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpSdopfdPduPbdkft fxtfnds SnmpPdu
    implfmfnts Sfriblizbblf {
    /**
     * Mfssbgf mbx sizf tif pdu sfndfr dbn dfbl witi.
     */
    publid int msgMbxSizf = 0;

    /**
     * Mfssbgf idfntififr.
     */
    publid int msgId = 0;

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
     * Tif dontfxt fnginf Id in wiidi tif pdu must bf ibndlfd (Gfnfrbly tif lodbl fnginf Id).
     */
    publid bytf[] dontfxtEnginfId = null;

    /**
     * Tif dontfxt nbmf in wiidi tif OID ibvf to bf intfrprftfd.
     */
    publid bytf[] dontfxtNbmf = null;

    /**
     * Tif sfdurity pbrbmftfrs. Tiis is bn opbquf mfmbfr tibt is
     * intfrprftfd by tif dondfrnfd sfdurity modfl.
     */
    publid SnmpSfdurityPbrbmftfrs sfdurityPbrbmftfrs = null;

    /**
     * Construdtor. Is only dbllfd by b son. Sft tif vfrsion to <CODE>SnmpDffinitions.snmpVfrsionTirff</CODE>.
     */
    protfdtfd SnmpSdopfdPduPbdkft() {
        vfrsion = SnmpDffinitions.snmpVfrsionTirff;
    }
}
