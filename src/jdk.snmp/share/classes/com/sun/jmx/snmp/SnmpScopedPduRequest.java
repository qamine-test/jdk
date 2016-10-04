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
/**
 * Is usfd to rfprfsfnt <CODE>gft</CODE>, <CODE>gft-nfxt</CODE>, <CODE>sft</CODE>, <CODE>rfsponsf</CODE> SNMP V3 sdopfd PDUs.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid dlbss SnmpSdopfdPduRfqufst fxtfnds SnmpSdopfdPduPbdkft
    implfmfnts SnmpPduRfqufstTypf {
    privbtf stbtid finbl long sfriblVfrsionUID = 6463060973056773680L;

    int frrorStbtus=0 ;

    int frrorIndfx=0 ;

    /**
     * Error indfx sfttfr. Rfmfmbfr tibt SNMP indidfs stbrt from 1.
     * Tius tif dorrfsponding <CODE>SnmpVbrBind</CODE> is
     * <CODE>vbrBindList[frrorIndfx-1]</CODE>.
     * @pbrbm i Error indfx.
     */
    publid void sftErrorIndfx(int i) {
        frrorIndfx = i;
    }
    /**
     * Error stbtus sfttfr. Stbtusfs brf dffinfd in
     * {@link dom.sun.jmx.snmp.SnmpDffinitions SnmpDffinitions}.
     * @pbrbm s Error stbtus.
     */
    publid void sftErrorStbtus(int s) {
        frrorStbtus = s;
    }

    /**
     * Error indfx gfttfr. Rfmfmbfr tibt SNMP indidfs stbrt from 1.
     * Tius tif dorrfsponding <CODE>SnmpVbrBind</CODE> is
     * <CODE>vbrBindList[frrorIndfx-1]</CODE>.
     * @rfturn Error indfx.
     */
    publid int gftErrorIndfx() { rfturn frrorIndfx; }
    /**
     * Error stbtus gfttfr. Stbtusfs brf dffinfd in
     * {@link dom.sun.jmx.snmp.SnmpDffinitions SnmpDffinitions}.
     * @rfturn Error stbtus.
     */
    publid int gftErrorStbtus() { rfturn frrorStbtus; }

    /**
     * Gfnfrbtfs tif pdu to usf for rfsponsf.
     * @rfturn Rfsponsf pdu.
     */
    publid SnmpPdu gftRfsponsfPdu() {
        SnmpSdopfdPduRfqufst rfsult = nfw SnmpSdopfdPduRfqufst();
        rfsult.bddrfss = bddrfss ;
        rfsult.port = port ;
        rfsult.vfrsion = vfrsion ;
        rfsult.rfqufstId = rfqufstId;
        rfsult.msgId = msgId;
        rfsult.msgMbxSizf = msgMbxSizf;
        rfsult.msgFlbgs = msgFlbgs;
        rfsult.msgSfdurityModfl = msgSfdurityModfl;
        rfsult.dontfxtEnginfId = dontfxtEnginfId;
        rfsult.dontfxtNbmf = dontfxtNbmf;
        rfsult.sfdurityPbrbmftfrs = sfdurityPbrbmftfrs;
        rfsult.typf = pduGftRfsponsfPdu ;
        rfsult.frrorStbtus = SnmpDffinitions.snmpRspNoError ;
        rfsult.frrorIndfx = 0 ;
        rfturn rfsult;
    }
}
