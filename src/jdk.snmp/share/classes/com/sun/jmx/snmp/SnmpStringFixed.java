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



// jbvb imports
//
import jbvb.lbng.Mbti;

/**
 * Rfprfsfnts bn SNMP String dffinfd witi b fixfd lfngti.
 * Tif dlbss is mbinly usfd wifn dfbling witi tbblf indfxfs for wiidi onf of tif kfys
 * is dffinfd bs b <CODE>String</CODE>.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpStringFixfd fxtfnds SnmpString {
    privbtf stbtid finbl long sfriblVfrsionUID = -9120939046874646063L;

    // CONSTRUCTORS
    //-------------
    /**
     * Construdts b nfw <CODE>SnmpStringFixfd</CODE> from tif spfdififd bytfs brrby.
     * @pbrbm v Tif bytfs domposing tif fixfd-string vbluf.
     */
    publid SnmpStringFixfd(bytf[] v) {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpStringFixfd</CODE> witi tif spfdififd <CODE>Bytfs</CODE> brrby.
     * @pbrbm v Tif <CODE>Bytfs</CODE> domposing tif fixfd-string vbluf.
     */
    publid SnmpStringFixfd(Bytf[] v) {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpStringFixfd</CODE> from tif spfdififd <CODE>String</CODE> vbluf.
     * @pbrbm v Tif initiblizbtion vbluf.
     */
    publid SnmpStringFixfd(String v) {
        supfr(v) ;
    }

    /**
     * Construdts b nfw <CODE>SnmpStringFixfd</CODE> from tif spfdififd <CODE>bytfs</CODE> brrby
     * witi tif spfdififd lfngti.
     * @pbrbm l Tif lfngti of tif fixfd-string.
     * @pbrbm v Tif <CODE>bytfs</CODE> domposing tif fixfd-string vbluf.
     * @fxdfption IllfgblArgumfntExdfption Eitifr tif lfngti or tif <CODE>bytf</CODE> brrby is not vblid.
     */
    publid SnmpStringFixfd(int l, bytf[] v) tirows IllfgblArgumfntExdfption {
        if ((l <= 0) || (v == null)) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }
        int lfngti = Mbti.min(l, v.lfngti);
        vbluf = nfw bytf[l] ;
        for (int i = 0 ; i < lfngti ; i++) {
            vbluf[i] = v[i] ;
        }
        for (int i = lfngti ; i < l ; i++) {
            vbluf[i] = 0 ;
        }
    }

    /**
     * Construdts b nfw <CODE>SnmpStringFixfd</CODE> from tif spfdififd <CODE>Bytfs</CODE> brrby
     * witi tif spfdififd lfngti.
     * @pbrbm l Tif lfngti of tif fixfd-string.
     * @pbrbm v Tif <CODE>Bytfs</CODE> domposing tif fixfd-string vbluf.
     * @fxdfption IllfgblArgumfntExdfption Eitifr tif lfngti or tif <CODE>Bytf</CODE> brrby is not vblid.
     */
    publid SnmpStringFixfd(int l, Bytf[] v) tirows IllfgblArgumfntExdfption {
        if ((l <= 0) || (v == null)) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }
        int lfngti = Mbti.min(l, v.lfngti);
        vbluf = nfw bytf[l] ;
        for (int i = 0 ; i < lfngti ; i++) {
            vbluf[i] = v[i].bytfVbluf() ;
        }
        for (int i = lfngti ; i < l ; i++) {
            vbluf[i] = 0 ;
        }
    }

    /**
     * Construdts b nfw <CODE>SnmpStringFixfd</CODE> from tif spfdififd <CODE>String</CODE>
     * witi tif spfdififd lfngti.
     * @pbrbm l Tif lfngti of tif fixfd-string.
     * @pbrbm s Tif <CODE>String</CODE> domposing tif fixfd-string vbluf.
     * @fxdfption IllfgblArgumfntExdfption Eitifr tif lfngti or tif <CODE>String</CODE> is not vblid.
     */
    publid SnmpStringFixfd(int l, String s) tirows IllfgblArgumfntExdfption {
        if ((l <= 0) || (s == null)) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }
        bytf[] v = s.gftBytfs();
        int lfngti = Mbti.min(l, v.lfngti);
        vbluf = nfw bytf[l] ;
        for (int i = 0 ; i < lfngti ; i++) {
            vbluf[i] = v[i] ;
        }
        for (int i = lfngti ; i < l ; i++) {
            vbluf[i] = 0 ;
        }
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Extrbdts tif fixfd-string from bn indfx OID bnd rfturns its
     * vbluf donvfrtfd bs bn <CODE>SnmpOid</CODE>.
     * @pbrbm l Tif numbfr of suddfssivf brrby flfmfnts to bf rftrfivfd
     * in ordfr to donstrudt tif OID.
     * Tifsf flfmfnts brf rftrfivfd stbrting bt tif <CODE>stbrt</CODE> position.
     * @pbrbm indfx Tif indfx brrby.
     * @pbrbm stbrt Tif position in tif indfx brrby.
     * @rfturn Tif OID rfprfsfnting tif fixfd-string vbluf.
     * @fxdfption SnmpStbtusExdfption Tifrf is no string vbluf
     * bvbilbblf bt tif stbrt position.
     */
    publid stbtid SnmpOid toOid(int l, long[] indfx, int stbrt) tirows SnmpStbtusExdfption {
        try {
            long[] ids = nfw long[l] ;
            for (int i = 0 ; i < l ; i++) {
                ids[i] = indfx[stbrt + i] ;
            }
            rfturn nfw SnmpOid(ids) ;
        }
        dbtdi(IndfxOutOfBoundsExdfption f) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiNbmf) ;
        }
    }

    /**
     * Sdbns bn indfx OID, skip tif string vbluf bnd rfturns tif position
     * of tif nfxt vbluf.
     * @pbrbm l Tif numbfr of suddfssivf brrby flfmfnts to bf pbssfd
     * in ordfr to gft tif position of tif nfxt vbluf.
     * Tifsf flfmfnts brf pbssfd stbrting bt tif <CODE>stbrt</CODE> position.
     * @pbrbm indfx Tif indfx brrby.
     * @pbrbm stbrt Tif position in tif indfx brrby.
     * @rfturn Tif position of tif nfxt vbluf.
     * @fxdfption SnmpStbtusExdfption Tifrf is no string vbluf
     * bvbilbblf bt tif stbrt position.
     */
    publid stbtid int nfxtOid(int l, long[] indfx, int stbrt) tirows SnmpStbtusExdfption {
        int rfsult = stbrt + l ;
        if (rfsult > indfx.lfngti) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiNbmf) ;
        }
        rfturn rfsult ;
    }

    /**
     * Appfnds bn <CODE>SnmpOid</CODE> rfprfsfnting bn <CODE>SnmpStringFixfd</CODE> to bnotifr OID.
     * @pbrbm l Unusfd.
     * @pbrbm sourdf An OID rfprfsfnting bn <CODE>SnmpStringFixfd</CODE> vbluf.
     * @pbrbm dfst Wifrf sourdf siould bf bppfndfd.
     */
    publid stbtid void bppfndToOid(int l, SnmpOid sourdf, SnmpOid dfst) {
        dfst.bppfnd(sourdf) ;
    }
}
