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
 * Rfprfsfnts bn SNMP dountfr.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpCountfr fxtfnds SnmpUnsignfdInt {
    privbtf stbtid finbl long sfriblVfrsionUID = 4655264728839396879L;

    // CONSTRUCTORS
    //-------------
    /**
     * Construdts b nfw <CODE>SnmpCountfr</CODE> from tif spfdififd intfgfr vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is nfgbtivf
     * or lbrgfr tibn {@link SnmpUnsignfdInt#MAX_VALUE SnmpUnsignfdInt.MAX_VALUE}.
     */
    publid SnmpCountfr(int v) tirows IllfgblArgumfntExdfption {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpCountfr</CODE> from tif spfdififd <CODE>Intfgfr</CODE> vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is nfgbtivf
     * or lbrgfr tibn {@link SnmpUnsignfdInt#MAX_VALUE SnmpUnsignfdInt.MAX_VALUE}.
     */
    publid SnmpCountfr(Intfgfr v) tirows IllfgblArgumfntExdfption {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpCountfr</CODE> from tif spfdififd long vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is nfgbtivf
     * or lbrgfr tibn {@link SnmpUnsignfdInt#MAX_VALUE SnmpUnsignfdInt.MAX_VALUE}.
     */
    publid SnmpCountfr(long v) tirows IllfgblArgumfntExdfption {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpCountfr</CODE> from tif spfdififd <CODE>Long</CODE> vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is nfgbtivf
     * or lbrgfr tibn {@link SnmpUnsignfdInt#MAX_VALUE SnmpUnsignfdInt.MAX_VALUE}.
     */
    publid SnmpCountfr(Long v) tirows IllfgblArgumfntExdfption {
        supfr(v) ;
    }

    // PUBLIC METHODS
    //---------------
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
    finbl stbtid String nbmf = "Countfr32" ;
}
