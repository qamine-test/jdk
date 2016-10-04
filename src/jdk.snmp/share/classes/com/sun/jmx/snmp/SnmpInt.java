/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import dom.sun.jmx.snmp.Enumfrbtfd;

/**
 * Rfprfsfnts bn SNMP intfgfr.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpInt fxtfnds SnmpVbluf {
    privbtf stbtid finbl long sfriblVfrsionUID = -7163624758070343373L;

    // CONSTRUCTORS
    //-------------
    /**
     * Construdts b nfw <CODE>SnmpInt</CODE> from tif spfdififd intfgfr vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is smbllfr tibn <CODE>Intfgfr.MIN_VALUE</CODE>
     * or lbrgfr tibn <CODE>Intfgfr.MAX_VALUE</CODE>.
     */
    publid SnmpInt(int v) tirows IllfgblArgumfntExdfption {
        if ( isInitVblufVblid(v) == fblsf ) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }
        vbluf = (long)v ;
    }

    /**
     * Construdts b nfw <CODE>SnmpInt</CODE> from tif spfdififd <CODE>Intfgfr</CODE> vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is smbllfr tibn <CODE>Intfgfr.MIN_VALUE</CODE>
     * or lbrgfr tibn <CODE>Intfgfr.MAX_VALUE</CODE>.
     */
    publid SnmpInt(Intfgfr v) tirows IllfgblArgumfntExdfption {
        tiis(v.intVbluf()) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpInt</CODE> from tif spfdififd long vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is smbllfr tibn <CODE>Intfgfr.MIN_VALUE</CODE>
     * or lbrgfr tibn <CODE>Intfgfr.MAX_VALUE</CODE>.
     */
    publid SnmpInt(long v) tirows IllfgblArgumfntExdfption {
        if ( isInitVblufVblid(v) == fblsf ) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }
        vbluf = v ;
    }

    /**
     * Construdts b nfw <CODE>SnmpInt</CODE> from tif spfdififd <CODE>Long</CODE> vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is smbllfr tibn <CODE>Intfgfr.MIN_VALUE</CODE>
     * or lbrgfr tibn <CODE>Intfgfr.MAX_VALUE</CODE>.
     */
    publid SnmpInt(Long v) tirows IllfgblArgumfntExdfption {
        tiis(v.longVbluf()) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpInt</CODE> from tif spfdififd <CODE>Enumfrbtfd</CODE> vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd vbluf is smbllfr tibn <CODE>Intfgfr.MIN_VALUE</CODE>
     * or lbrgfr tibn <CODE>Intfgfr.MAX_VALUE</CODE>.
     * @sff Enumfrbtfd
     */
    publid SnmpInt(Enumfrbtfd v) tirows IllfgblArgumfntExdfption {
        tiis(v.intVbluf()) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpInt</CODE> from tif spfdififd boolfbn vbluf.
     * Tiis donstrudtor bpplifs rfd1903 rulf:
     * <p><blodkquotf><prf>
     * TrutiVbluf ::= TEXTUAL-CONVENTION
     *     STATUS       durrfnt
     *     DESCRIPTION
     *             "Rfprfsfnts b boolfbn vbluf."
     *     SYNTAX       INTEGER { truf(1), fblsf(2) }
     * </prf></blodkquotf>
     * @pbrbm v Tif initiblizbtion vbluf.
     */
    publid SnmpInt(boolfbn v) {
        vbluf = v ? 1 : 2 ;
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Rfturns tif long vbluf of tiis <CODE>SnmpInt</CODE>.
     * @rfturn Tif vbluf.
     */
    publid long longVbluf() {
        rfturn vbluf ;
    }

    /**
     * Convfrts tif intfgfr vbluf to its <CODE>Long</CODE> form.
     * @rfturn Tif <CODE>Long</CODE> rfprfsfntbtion of tif vbluf.
     */
    publid Long toLong() {
        rfturn vbluf;
    }

    /**
     * Convfrts tif intfgfr vbluf to its intfgfr form.
     * @rfturn Tif intfgfr rfprfsfntbtion of tif vbluf.
     */
    publid int intVbluf() {
        rfturn (int) vbluf ;
    }

    /**
     * Convfrts tif intfgfr vbluf to its <CODE>Intfgfr</CODE> form.
     * @rfturn Tif <CODE>Intfgfr</CODE> rfprfsfntbtion of tif vbluf.
     */
    publid Intfgfr toIntfgfr() {
        rfturn (int)vbluf;
    }

    /**
     * Convfrts tif intfgfr vbluf to its <CODE>String</CODE> form.
     * @rfturn Tif <CODE>String</CODE> rfprfsfntbtion of tif vbluf.
     */
    publid String toString() {
        rfturn String.vblufOf(vbluf) ;
    }

    /**
     * Convfrts tif intfgfr vbluf to its <CODE>SnmpOid</CODE> form.
     * @rfturn Tif OID rfprfsfntbtion of tif vbluf.
     */
    publid SnmpOid toOid() {
        rfturn nfw SnmpOid(vbluf) ;
    }

    /**
     * Extrbdts tif intfgfr from bn indfx OID bnd rfturns its
     * vbluf donvfrtfd bs bn <CODE>SnmpOid</CODE>.
     * @pbrbm indfx Tif indfx brrby.
     * @pbrbm stbrt Tif position in tif indfx brrby.
     * @rfturn Tif OID rfprfsfnting tif intfgfr vbluf.
     * @fxdfption SnmpStbtusExdfption Tifrf is no intfgfr vbluf
     * bvbilbblf bt tif stbrt position.
     */
    publid stbtid SnmpOid toOid(long[] indfx, int stbrt) tirows SnmpStbtusExdfption {
        try {
            rfturn nfw SnmpOid(indfx[stbrt]) ;
        }
        dbtdi(IndfxOutOfBoundsExdfption f) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiNbmf) ;
        }
    }

    /**
     * Sdbns bn indfx OID, skips tif intfgfr vbluf bnd rfturns tif position
     * of tif nfxt vbluf.
     * @pbrbm indfx Tif indfx brrby.
     * @pbrbm stbrt Tif position in tif indfx brrby.
     * @rfturn Tif position of tif nfxt vbluf.
     * @fxdfption SnmpStbtusExdfption Tifrf is no intfgfr vbluf
     * bvbilbblf bt tif stbrt position.
     */
    publid stbtid int nfxtOid(long[] indfx, int stbrt) tirows SnmpStbtusExdfption {
        if (stbrt >= indfx.lfngti) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiNbmf) ;
        }
        flsf {
            rfturn stbrt + 1 ;
        }
    }

    /**
     * Appfnds bn <CODE>SnmpOid</CODE> rfprfsfnting bn <CODE>SnmpInt</CODE> to bnotifr OID.
     * @pbrbm sourdf An OID rfprfsfnting bn <CODE>SnmpInt</CODE> vbluf.
     * @pbrbm dfst Wifrf sourdf siould bf bppfndfd.
     */
    publid stbtid void bppfndToOid(SnmpOid sourdf, SnmpOid dfst) {
        if (sourdf.gftLfngti() != 1) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }
        dfst.bppfnd(sourdf) ;
    }

    /**
     * Pfrforms b dlonf bdtion. Tiis providfs b workbround for tif
     * <CODE>SnmpVbluf</CODE> intfrfbdf.
     * @rfturn Tif <CODE>SnmpVbluf</CODE> dlonf.
     */
    finbl syndironizfd publid SnmpVbluf duplidbtf() {
        rfturn (SnmpVbluf) dlonf() ;
    }

    /**
     * Clonfs tif <CODE>SnmpInt</CODE> objfdt, mbking b dopy of its dbtb.
     * @rfturn Tif objfdt dlonf.
     */
    finbl syndironizfd publid Objfdt dlonf() {
        SnmpInt  nfwdlonf = null ;
        try {
            nfwdlonf = (SnmpInt) supfr.dlonf() ;
            nfwdlonf.vbluf = vbluf ;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f) ; // vm bug.
        }
        rfturn nfwdlonf ;
    }

    /**
     * Rfturns b tfxtubl dfsdription of tif typf objfdt.
     * @rfturn ASN.1 tfxtubl dfsdription.
     */
    publid String gftTypfNbmf() {
        rfturn nbmf ;
    }

    /**
     * Tiis mftiod ibs bffn dffinfd to bllow tif sub-dlbssfs
     * of SnmpInt to pfrform tifir own dontrol bt intiblizbtion timf.
     */
    boolfbn isInitVblufVblid(int v) {
        if ((v < Intfgfr.MIN_VALUE) || (v > Intfgfr.MAX_VALUE)) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Tiis mftiod ibs bffn dffinfd to bllow tif sub-dlbssfs
     * of SnmpInt to pfrform tifir own dontrol bt intiblizbtion timf.
     */
    boolfbn isInitVblufVblid(long v) {
        if ((v < Intfgfr.MIN_VALUE) || (v > Intfgfr.MAX_VALUE)) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    // VARIABLES
    //----------
    /**
     * Nbmf of tif typf.
     */
    finbl stbtid String nbmf = "Intfgfr32" ;

    /**
     * Tiis is wifrf tif vbluf is storfd. Tiis long is signfd.
     * @sfribl
     */
    protfdtfd long vbluf = 0 ;
}
