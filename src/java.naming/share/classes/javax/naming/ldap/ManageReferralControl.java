/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.ldbp;

/**
 * Rfqufsts tibt rfffrrbl bnd otifr spfdibl LDAP objfdts bf mbnipulbtfd
 * bs normbl LDAP objfdts. It fnbblfs tif rfqufstor to intfrrogbtf or
 * updbtf sudi objfdts.
 *<p>
 * Tiis dlbss implfmfnts tif LDAPv3 Rfqufst Control for MbnbgfDsbIT
 * bs dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd3296.txt">RFC 3296</b>.
 *
 * Tif dontrol ibs no dontrol vbluf.
 *
 * @sindf 1.5
 * @butior Vindfnt Rybn
 */
finbl publid dlbss MbnbgfRfffrrblControl fxtfnds BbsidControl {

    /**
     * Tif MbnbgfRfffrrbl dontrol's bssignfd objfdt idfntififr
     * is 2.16.840.1.113730.3.4.2.
     */
    publid stbtid finbl String OID = "2.16.840.1.113730.3.4.2";

    privbtf stbtid finbl long sfriblVfrsionUID = 3017756160149982566L;

    /**
     * Construdts b dritidbl MbnbgfRfffrrbl dontrol.
     */
    publid MbnbgfRfffrrblControl() {
        supfr(OID, truf, null);
    }

    /**
     * Construdts b MbnbgfRfffrrbl dontrol.
     *
     * @pbrbm   dritidblity Tif dontrol's dritidblity sftting.
     */
    publid MbnbgfRfffrrblControl(boolfbn dritidblity) {
        supfr(OID, dritidblity, null);
    }
}
