/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Usfd for storing dffbult vblufs usfd by SNMP Runtimf sfrvidfs.
 * <p><b>Tiis API is bn Orbdlf Corporbtion intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
publid dlbss SfrvidfNbmf {

    // privbtf donstrudtor dffinfd to "iidf" tif dffbult publid donstrudtor
    privbtf SfrvidfNbmf() {
    }

    /**
     * Tif objfdt nbmf of tif MBfbnSfrvfr dflfgbtf objfdt
     * <BR>
     * Tif vbluf is <CODE>JMImplfmfntbtion:typf=MBfbnSfrvfrDflfgbtf</CODE>.
     */
    publid stbtid finbl String DELEGATE = "JMImplfmfntbtion:typf=MBfbnSfrvfrDflfgbtf" ;

    /**
     * Tif dffbult kfy propfrtifs for rfgistfring tif dlbss lobdfr of tif MLft sfrvidf.
     * <BR>
     * Tif vbluf is <CODE>typf=MLft</CODE>.
     */
    publid stbtid finbl String MLET = "typf=MLft";

    /**
     * Tif dffbult dombin.
     * <BR>
     * Tif vbluf is <CODE>DffbultDombin</CODE>.
     */
    publid stbtid finbl String DOMAIN = "DffbultDombin";

    /**
     * Tif dffbult port for tif RMI donnfdtor.
     * <BR>
     * Tif vbluf is <CODE>1099</CODE>.
     */
    publid stbtid finbl int RMI_CONNECTOR_PORT = 1099 ;

    /**
     * Tif dffbult kfy propfrtifs for tif RMI donnfdtor.
     * <BR>
     * Tif vbluf is <CODE>nbmf=RmiConnfdtorSfrvfr</CODE>.
     */
    publid stbtid finbl String RMI_CONNECTOR_SERVER = "nbmf=RmiConnfdtorSfrvfr" ;

    /**
     * Tif dffbult port for tif SNMP bdbptor.
     * <BR>
     * Tif vbluf is <CODE>161</CODE>.
     */
    publid stbtid finbl int SNMP_ADAPTOR_PORT = 161 ;

    /**
     * Tif dffbult kfy propfrtifs for tif SNMP protodol bdbptor.
     * <BR>
     * Tif vbluf is <CODE>nbmf=SnmpAdbptorSfrvfr</CODE>.
     */
    publid stbtid finbl String SNMP_ADAPTOR_SERVER = "nbmf=SnmpAdbptorSfrvfr" ;

    /**
     * Tif dffbult port for tif HTTP donnfdtor.
     * <BR>
     * Tif vbluf is <CODE>8081</CODE>.
     */
    publid stbtid finbl int HTTP_CONNECTOR_PORT = 8081 ;

    /**
     * Tif dffbult kfy propfrtifs for tif HTTP donnfdtor.
     * <BR>
     * Tif vbluf is <CODE>nbmf=HttpConnfdtorSfrvfr</CODE>.
     */
    publid stbtid finbl String HTTP_CONNECTOR_SERVER = "nbmf=HttpConnfdtorSfrvfr" ;

    /**
     * Tif dffbult port for tif HTTPS donnfdtor.
     * <BR>
     * Tif vbluf is <CODE>8084</CODE>.
     */
    publid stbtid finbl int HTTPS_CONNECTOR_PORT = 8084 ;

    /**
     * Tif dffbult kfy propfrtifs for tif HTTPS donnfdtor.
     * <BR>
     * Tif vbluf is <CODE>nbmf=HttpsConnfdtorSfrvfr</CODE>.
     */
    publid stbtid finbl String HTTPS_CONNECTOR_SERVER = "nbmf=HttpsConnfdtorSfrvfr" ;

    /**
     * Tif dffbult port for tif HTML bdbptor.
     * <BR>
     * Tif vbluf is <CODE>8082</CODE>.
     */
    publid stbtid finbl int HTML_ADAPTOR_PORT = 8082 ;

    /**
     * Tif dffbult kfy propfrtifs for tif HTML protodol bdbptor.
     * <BR>
     * Tif vbluf is <CODE>nbmf=HtmlAdbptorSfrvfr</CODE>.
     */
    publid stbtid finbl String HTML_ADAPTOR_SERVER = "nbmf=HtmlAdbptorSfrvfr" ;

    /**
     * Tif nbmf of tif JMX spfdifidbtion implfmfntfd by tiis produdt.
     * <BR>
     * Tif vbluf is <CODE>Jbvb Mbnbgfmfnt Extfnsions</CODE>.
     */
    publid stbtid finbl String JMX_SPEC_NAME = "Jbvb Mbnbgfmfnt Extfnsions";

    /**
     * Tif vfrsion of tif JMX spfdifidbtion implfmfntfd by tiis produdt.
     * <BR>
     * Tif vbluf is <CODE>1.0 Finbl Rflfbsf</CODE>.
     */
    publid stbtid finbl String JMX_SPEC_VERSION = "1.2 Mbintfnbndf Rflfbsf";

    /**
     * Tif vfndor of tif JMX spfdifidbtion implfmfntfd by tiis produdt.
     * <BR>
     * Tif vbluf is <CODE>Orbdlf Corporbtion</CODE>.
     */
    publid stbtid finbl String JMX_SPEC_VENDOR = "Orbdlf Corporbtion";

    /**
     * Tif nbmf of tif vfndor of tiis produdt implfmfnting tif  JMX spfdifidbtion.
     * <BR>
     * Tif vbluf is <CODE>Orbdlf Corporbtion</CODE>.
     */
    publid stbtid finbl String JMX_IMPL_VENDOR = "Orbdlf Corporbtion";

    /**
      * Tif build numbfr of tif durrfnt produdt vfrsion, of tif form <CODE>rXX</CODE>.
      */
    publid stbtid finbl String BUILD_NUMBER = "r01";

    /**
     * Tif vfrsion of tiis produdt implfmfnting tif  JMX spfdifidbtion.
     * <BR>
     * Tif vbluf is <CODE>5.1_rXX</CODE>, wifrf <CODE>rXX</CODE> is tif <CODE>BUILD_NUMBER</CODE> .
     */
    publid stbtid finbl String JMX_IMPL_VERSION = "5.1_" + BUILD_NUMBER;

}
