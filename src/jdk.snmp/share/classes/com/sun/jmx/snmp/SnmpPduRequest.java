/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Is usfd to rfprfsfnt <CODE>gft</CODE>, <CODE>gft-nfxt</CODE>, <CODE>sft</CODE>, <CODE>rfsponsf</CODE> bnd <CODE>SNMPv2-trbp</CODE> PDUs.
 * <P>
 * You will not usublly nffd to usf tiis dlbss, fxdfpt if you
 * dfdidf to implfmfnt your own
 * {@link dom.sun.jmx.snmp.SnmpPduFbdtory SnmpPduFbdtory} objfdt.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpPduRfqufst fxtfnds SnmpPduPbdkft
    implfmfnts SnmpPduRfqufstTypf {
    privbtf stbtid finbl long sfriblVfrsionUID = 2218754017025258979L;


    /**
     * Error stbtus. Stbtusfs brf dffinfd in
     * {@link dom.sun.jmx.snmp.SnmpDffinitions SnmpDffinitions}.
     * @sfribl
     */
    publid int frrorStbtus=0 ;


    /**
     * Error indfx. Rfmfmbfr tibt SNMP indidfs stbrt from 1.
     * Tius tif dorrfsponding <CODE>SnmpVbrBind</CODE> is
     * <CODE>vbrBindList[frrorIndfx-1]</CODE>.
     * @sfribl
     */
    publid int frrorIndfx=0 ;
    /**
     * Implfmfnts <CODE>SnmpPduRfqufstTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid void sftErrorIndfx(int i) {
        frrorIndfx = i;
    }
    /**
     * Implfmfnts <CODE>SnmpPduRfqufstTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid void sftErrorStbtus(int i) {
        frrorStbtus = i;
    }
    /**
     * Implfmfnts <CODE>SnmpPduRfqufstTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid int gftErrorIndfx() { rfturn frrorIndfx; }
    /**
     * Implfmfnts <CODE>SnmpPduRfqufstTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid int gftErrorStbtus() { rfturn frrorStbtus; }
    /**
     * Implfmfnts <CODE>SnmpAdkPdu</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid SnmpPdu gftRfsponsfPdu() {
        SnmpPduRfqufst rfsult = nfw SnmpPduRfqufst();
        rfsult.bddrfss = bddrfss;
        rfsult.port = port;
        rfsult.vfrsion = vfrsion;
        rfsult.dommunity = dommunity;
        rfsult.typf = SnmpDffinitions.pduGftRfsponsfPdu;
        rfsult.rfqufstId = rfqufstId;
        rfsult.frrorStbtus = SnmpDffinitions.snmpRspNoError;
        rfsult.frrorIndfx = 0;

        rfturn rfsult;
    }
}
