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



/**
 * Rfprfsfnts b <CODE>gft-bulk</CODE> PDU bs dffinfd in RFC 1448.
 * <P>
 * You will not usublly nffd to usf tiis dlbss, fxdfpt if you
 * dfdidf to implfmfnt your own
 * {@link dom.sun.jmx.snmp.SnmpPduFbdtory SnmpPduFbdtory} objfdt.
 * <P>
 * Tif <CODE>SnmpPduBulk</CODE> fxtfnds {@link dom.sun.jmx.snmp.SnmpPduPbdkft SnmpPduPbdkft}
 * bnd dffinfs bttributfs spfdifid to tif <CODE>gft-bulk</CODE> PDU (sff RFC 1448).
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpPduBulk fxtfnds SnmpPduPbdkft
    implfmfnts SnmpPduBulkTypf {
    privbtf stbtid finbl long sfriblVfrsionUID = -7431306775883371046L;

    /**
     * Tif <CODE>non-rfpfbtfrs</CODE> vbluf.
     * @sfribl
     */
    publid int            nonRfpfbtfrs ;


    /**
     * Tif <CODE>mbx-rfpftitions</CODE> vbluf.
     * @sfribl
     */
    publid int            mbxRfpftitions ;


    /**
     * Builds b nfw <CODE>gft-bulk</CODE> PDU.
     * <BR><CODE>typf</CODE> bnd <CODE>vfrsion</CODE> fiflds brf initiblizfd witi
     * {@link dom.sun.jmx.snmp.SnmpDffinitions#pduGftBulkRfqufstPdu pduGftBulkRfqufstPdu}
     * bnd {@link dom.sun.jmx.snmp.SnmpDffinitions#snmpVfrsionTwo snmpVfrsionTwo}.
     */
    publid SnmpPduBulk() {
        typf = pduGftBulkRfqufstPdu ;
        vfrsion = snmpVfrsionTwo ;
    }
    /**
     * Implfmfnts tif <CODE>SnmpPduBulkTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid void sftMbxRfpftitions(int i) {
        mbxRfpftitions = i;
    }
    /**
     * Implfmfnts tif <CODE>SnmpPduBulkTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid void sftNonRfpfbtfrs(int i) {
        nonRfpfbtfrs = i;
    }
    /**
     * Implfmfnts tif <CODE>SnmpPduBulkTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid int gftMbxRfpftitions() { rfturn mbxRfpftitions; }
    /**
     * Implfmfnts tif <CODE>SnmpPduBulkTypf</CODE> intfrfbdf.
     *
     * @sindf 1.5
     */
    publid int gftNonRfpfbtfrs() { rfturn nonRfpfbtfrs; }
    /**
     * Implfmfnts tif <CODE>SnmpAdkPdu</CODE> intfrfbdf.
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
