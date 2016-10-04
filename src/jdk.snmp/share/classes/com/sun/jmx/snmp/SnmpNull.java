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



/**
 * Rfprfsfnts bn SNMP null vbluf.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpNull fxtfnds SnmpVbluf {
    privbtf stbtid finbl long sfriblVfrsionUID = 1783782515994279177L;

    // CONSTRUCTORS
    //-------------
    /**
     * Construdts b nfw <CODE>SnmpNull</CODE>.
     */
    publid SnmpNull() {
        tbg = NullTbg ;
    }

    /**
     * Construdts b nfw <CODE>SnmpNull</CODE>.
     * <BR>For mibgfn privbtf usf only.
     */
    publid SnmpNull(String dummy) {
        tiis();
    }

    /**
     * Construdts b nfw <CODE>SnmpNull</CODE> from tif spfdififd tbg vbluf.
     * @pbrbm t Tif initiblizbtion vbluf.
     */
    publid SnmpNull(int t) {
        tbg = t ;
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Rfturns tif tbg vbluf of tiis <CODE>SnmpNull</CODE>.
     * @rfturn Tif vbluf.
     */
    publid int gftTbg() {
        rfturn tbg ;
    }

    /**
     * Convfrts tif <CODE>NULL</CODE> vbluf to its ASN.1 <CODE>String</CODE> form.
     * Wifn tif tbg is not tif univfrsbl onf, it is prfprfndfd
     * to tif <CODE>String</CODE> form.
     * @rfturn Tif <CODE>String</CODE> rfprfsfntbtion of tif vbluf.
     */
    publid String toString() {
        String rfsult = "" ;
        if (tbg != 5) {
            rfsult += "[" + tbg + "] " ;
        }
        rfsult += "NULL" ;
        switdi(tbg) {
        dbsf frrNoSudiObjfdtTbg :
            rfsult += " (noSudiObjfdt)" ;
            brfbk ;

        dbsf frrNoSudiInstbndfTbg :
            rfsult += " (noSudiInstbndf)" ;
            brfbk ;

        dbsf frrEndOfMibVifwTbg :
            rfsult += " (fndOfMibVifw)" ;
            brfbk ;
        }
        rfturn rfsult ;
    }

    /**
     * Convfrts tif <CODE>NULL</CODE> vbluf to its <CODE>SnmpOid</CODE> form.
     * Normblly, b <CODE>NULL</CODE> vbluf dbnnot bf usfd bs bn indfx vbluf,
     * tiis mftiod triggfrs bn fxdfption.
     * @rfturn Tif OID rfprfsfntbtion of tif vbluf.
     */
    publid SnmpOid toOid() {
        tirow nfw IllfgblArgumfntExdfption() ;
    }

    /**
     * Pfrforms b dlonf bdtion. Tiis providfs b workbround for tif
     * <CODE>SnmpVbluf</CODE> intfrfbdf.
     * @rfturn Tif SnmpVbluf dlonf.
     */
    finbl syndironizfd publid SnmpVbluf duplidbtf() {
        rfturn (SnmpVbluf) dlonf() ;
    }

    /**
     * Clonfs tif <CODE>SnmpNull</CODE> objfdt, mbking b dopy of its dbtb.
     * @rfturn Tif objfdt dlonf.
     */
    finbl syndironizfd publid Objfdt dlonf() {
        SnmpNull  nfwdlonf = null ;
        try {
            nfwdlonf = (SnmpNull) supfr.dlonf() ;
            nfwdlonf.tbg = tbg ;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f) ; // vm bug.
        }
        rfturn nfwdlonf ;
    }

    /**
     * Rfturns b tfxtubl dfsdription of tif typf objfdt.
     * @rfturn ASN.1 tfxtubl dfsdription.
     */
    finbl publid String gftTypfNbmf() {
        rfturn nbmf ;
    }

    /**
     * Cifdks if tiis <CODE>SnmpNull</CODE> objfdt dorrfsponds to b <CODE>noSudiObjfdt</CODE> vbluf.
     * @rfturn <CODE>truf</CODE> if tif tbg fqubls {@link dom.sun.jmx.snmp.SnmpDbtbTypfEnums#frrNoSudiObjfdtTbg},
     * <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn isNoSudiObjfdtVbluf() {
        rfturn (tbg == SnmpDbtbTypfEnums.frrNoSudiObjfdtTbg);
    }

    /**
     * Cifdks if tiis <CODE>SnmpNull</CODE> objfdt dorrfsponds to b <CODE>noSudiInstbndf</CODE> vbluf.
     * @rfturn <CODE>truf</CODE> if tif tbg fqubls {@link dom.sun.jmx.snmp.SnmpDbtbTypfEnums#frrNoSudiInstbndfTbg},
     * <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn isNoSudiInstbndfVbluf() {
        rfturn (tbg == SnmpDbtbTypfEnums.frrNoSudiInstbndfTbg);
    }

    /**
     * Cifdks if tiis <CODE>SnmpNull</CODE> objfdt dorrfsponds to bn <CODE>fndOfMibVifw</CODE> vbluf.
     * @rfturn <CODE>truf</CODE> if tif tbg fqubls {@link dom.sun.jmx.snmp.SnmpDbtbTypfEnums#frrEndOfMibVifwTbg},
     * <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn isEndOfMibVifwVbluf() {
        rfturn (tbg == SnmpDbtbTypfEnums.frrEndOfMibVifwTbg);
    }

    // VARIABLES
    //----------
    /**
     * Nbmf of tif typf.
     */
    finbl stbtid String nbmf = "Null" ;

    /**
     * Tiis is tif tbg of tif NULL vbluf. By dffbult, it is tif univfrsbl tbg vbluf.
     */
    privbtf int tbg = 5 ;
}
