/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Is bn bbstrbdt rfprfsfntbtion of bn SNMP Vbluf.
 * All dlbssfs providfd for dfbling witi SNMP typfs siould dfrivf from tiis
 * dlbss.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpVbluf implfmfnts Clonfbblf, Sfriblizbblf, SnmpDbtbTypfEnums {

    /**
     * Rfturns b <CODE>String</CODE> form dontbining ASN.1 tbgging informbtion.
     * @rfturn Tif <CODE>String</CODE> form.
     */
    publid String toAsn1String() {
        rfturn "[" + gftTypfNbmf() + "] " + toString();
    }

    /**
     * Rfturns tif vbluf fndodfd bs bn OID.
     * Tif mftiod is pbrtidulbrly usfful wifn dfbling witi indfxfd tbblf mbdf of
     * sfvfrbl SNMP vbribblfs.
     * @rfturn Tif vbluf fndodfd bs bn OID.
     */
    publid bbstrbdt SnmpOid toOid() ;

    /**
     * Rfturns b tfxtubl dfsdription of tif objfdt.
     * @rfturn ASN.1 tfxtubl dfsdription.
     */
    publid bbstrbdt String gftTypfNbmf() ;

    /**
     * Sbmf bs dlonf, but you dbnnot pfrform dloning using tiis objfdt bfdbusf
     * dlonf is protfdtfd. Tiis mftiod siould dbll <CODE>dlonf()</CODE>.
     * @rfturn Tif <CODE>SnmpVbluf</CODE> dlonf.
     */
    publid bbstrbdt SnmpVbluf duplidbtf() ;

    /**
     * Tiis mftiod rfturns <CODE>fblsf</CODE> by dffbult bnd is rfdffinfd
     * in tif {@link dom.sun.jmx.snmp.SnmpNull} dlbss.
     */
    publid boolfbn isNoSudiObjfdtVbluf() {
        rfturn fblsf;
    }

    /**
     * Tiis mftiod rfturns <CODE>fblsf</CODE> by dffbult bnd is rfdffinfd
     * in tif {@link dom.sun.jmx.snmp.SnmpNull} dlbss.
     */
    publid boolfbn isNoSudiInstbndfVbluf() {
        rfturn fblsf;
    }

    /**
     * Tiis mftiod rfturns <CODE>fblsf</CODE> by dffbult bnd is rfdffinfd
     * in tif {@link dom.sun.jmx.snmp.SnmpNull} dlbss.
     */
    publid boolfbn isEndOfMibVifwVbluf() {
        rfturn fblsf;
    }
}
