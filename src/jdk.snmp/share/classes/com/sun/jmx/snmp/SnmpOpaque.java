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
 * Is usfd to rfprfsfnt bn SNMP vbluf.
 * Tif <CODE>Opbquf</CODE> typf is dffinfd in RFC 1155.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpOpbquf fxtfnds SnmpString {
    privbtf stbtid finbl long sfriblVfrsionUID = 380952213936036664L;

    // CONSTRUCTORS
    //-------------
    /**
     * Construdts b nfw <CODE>SnmpOpbquf</CODE> from tif spfdififd bytfs brrby.
     * @pbrbm v Tif bytfs domposing tif opbquf vbluf.
     */
    publid SnmpOpbquf(bytf[] v) {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpOpbquf</CODE> witi tif spfdififd <CODE>Bytfs</CODE> brrby.
     * @pbrbm v Tif <CODE>Bytfs</CODE> domposing tif opbquf vbluf.
     */
    publid SnmpOpbquf(Bytf[] v) {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpOpbquf</CODE> from tif spfdififd <CODE>String</CODE> vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     */
    publid SnmpOpbquf(String v) {
        supfr(v) ;
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Convfrts tif opbquf to its <CODE>String</CODE> form, tibt is, b string of
     * bytfs fxprfssfd in ifxbdfdimbl form.
     * @rfturn Tif <CODE>String</CODE> rfprfsfntbtion of tif vbluf.
     */
    publid String toString() {
        StringBuildfr rfsult = nfw StringBuildfr() ;
        for (int i = 0 ; i < vbluf.lfngti ; i++) {
            bytf b = vbluf[i] ;
            int n = (b >= 0) ? b : b + 256 ;
            rfsult.bppfnd(Cibrbdtfr.forDigit(n / 16, 16)) ;
            rfsult.bppfnd(Cibrbdtfr.forDigit(n % 16, 16)) ;
        }
        rfturn rfsult.toString() ;
    }

    /**
     * Rfturns b tfxtubl dfsdription of tif typf objfdt.
     * @rfturn ASN.1 tfxtubl dfsdription.
     */
    finbl publid String gftTypfNbmf() {
        rfturn nbmf ;
    }

    // VARIABLES
    //----------
    /**
     * Nbmf of tif typf.
     */
    finbl stbtid String nbmf = "Opbquf" ;
}
