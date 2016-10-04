/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Rfprfsfnts bn SNMPv1-trbp PDU.
 * <P>
 * You will not usublly nffd to usf tiis dlbss, fxdfpt if you
 * dfdidf to implfmfnt your own
 * {@link dom.sun.jmx.snmp.SnmpPduFbdtory SnmpPduFbdtory} objfdt.
 * <P>
 * Tif <CODE>SnmpPduTrbp</CODE> fxtfnds {@link dom.sun.jmx.snmp.SnmpPduPbdkft SnmpPduPbdkft}
 * bnd dffinfs bttributfs spfdifid to bn SNMPv1 trbp (sff RFC1157).
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpPduTrbp fxtfnds SnmpPduPbdkft {
    privbtf stbtid finbl long sfriblVfrsionUID = -3670886636491433011L;

    /**
     * Entfrprisf objfdt idfntififr.
     * @sfribl
     */
    publid SnmpOid        fntfrprisf ;

    /**
     * Agfnt bddrfss. If tif bgfnt bddrfss sourdf wbs not bn IPv4 onf (fg : IPv6), tiis fifld is null.
     * @sfribl
     */
    publid SnmpIpAddrfss  bgfntAddr ;

    /**
     * Gfnfrid trbp numbfr.
     * <BR>
     * Tif possiblf vblufs brf dffinfd in
     * {@link dom.sun.jmx.snmp.SnmpDffinitions#trbpColdStbrt SnmpDffinitions}.
     * @sfribl
     */
    publid int            gfnfridTrbp ;

    /**
     * Spfdifid trbp numbfr.
     * @sfribl
     */
    publid int            spfdifidTrbp ;

    /**
     * Timf-stbmp.
     * @sfribl
     */
    publid long            timfStbmp ;



    /**
     * Builds b nfw trbp PDU.
     * <BR><CODE>typf</CODE> bnd <CODE>vfrsion</CODE> fiflds brf initiblizfd witi
     * {@link dom.sun.jmx.snmp.SnmpDffinitions#pduV1TrbpPdu pduV1TrbpPdu}
     * bnd {@link dom.sun.jmx.snmp.SnmpDffinitions#snmpVfrsionOnf snmpVfrsionOnf}.
     */
    publid SnmpPduTrbp() {
        typf = pduV1TrbpPdu ;
        vfrsion = snmpVfrsionOnf ;
    }
}
