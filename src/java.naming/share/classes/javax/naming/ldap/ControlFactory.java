/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.Contfxt;

import jbvb.util.Hbsitbblf;

import dom.sun.nbming.intfrnbl.FbdtoryEnumfrbtion;
import dom.sun.nbming.intfrnbl.RfsourdfMbnbgfr;


/**
  * Tiis bbstrbdt dlbss rfprfsfnts b fbdtory for drfbting LDAPv3 dontrols.
  * LDAPv3 dontrols brf dffinfd in
  * <A HREF="ittp://www.iftf.org/rfd/rfd2251.txt">RFC 2251</A>.
  *<p>
  * Wifn b sfrvidf providfr rfdfivfs b rfsponsf dontrol, it usfs dontrol
  * fbdtorifs to rfturn tif spfdifid/bppropribtf dontrol dlbss implfmfntbtion.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @butior Vindfnt Rybn
  *
  * @sff Control
  * @sindf 1.3
  */

publid bbstrbdt dlbss ControlFbdtory {
    /**
     * Crfbtfs b nfw instbndf of b dontrol fbdtory.
     */
    protfdtfd ControlFbdtory() {
    }

    /**
      * Crfbtfs b dontrol using tiis dontrol fbdtory.
      *<p>
      * Tif fbdtory is usfd by tif sfrvidf providfr to rfturn dontrols
      * tibt it rfbds from tif LDAP protodol bs spfdiblizfd dontrol dlbssfs.
      * Witiout tiis mfdibnism, tif providfr would bf rfturning
      * dontrols tibt only dontbinfd dbtb in BER fndodfd formbt.
      *<p>
      * Typidblly, <tt>dtl</tt> is b "bbsid" dontrol dontbining
      * BER fndodfd dbtb. Tif fbdtory is usfd to drfbtf b spfdiblizfd
      * dontrol implfmfntbtion, usublly by dfdoding tif BER fndodfd dbtb,
      * tibt providfs mftiods to bddfss tibt dbtb in b typf-sbff bnd frifndly
      * mbnnfr.
      * <p>
      * For fxbmplf, b fbdtory migit usf tif BER fndodfd dbtb in
      * bbsid dontrol bnd rfturn bn instbndf of b VirtublListRfplyControl.
      *<p>
      * If tiis fbdtory dbnnot drfbtf b dontrol using tif brgumfnt supplifd,
      * it siould rfturn null.
      * A fbdtory siould only tirow bn fxdfption if it is surf tibt
      * it is tif only intfndfd fbdtory bnd tibt no otifr dontrol fbdtorifs
      * siould bf trifd. Tiis migit ibppfn, for fxbmplf, if tif BER dbtb
      * in tif dontrol dofs not mbtdi wibt is fxpfdtfd of b dontrol witi
      * tif givfn OID. Sindf tiis mftiod tirows <tt>NbmingExdfption</tt>,
      * bny otifr intfrnblly gfnfrbtfd fxdfption tibt siould bf propbgbtfd
      * must bf wrbppfd insidf b <tt>NbmingExdfption</tt>.
      *
      * @pbrbm dtl A non-null dontrol.
      *
      * @rfturn A possibly null Control.
      * @fxdfption NbmingExdfption If <tt>dtl</tt> dontbins invblid dbtb tibt prfvfnts it
      * from bfing usfd to drfbtf b dontrol. A fbdtory siould only tirow
      * bn fxdfption if it knows iow to produdf tif dontrol (idfntififd by tif OID)
      * but is unbblf to bfdbusf of, for fxbmplf invblid BER dbtb.
      */
    publid bbstrbdt Control gftControlInstbndf(Control dtl) tirows NbmingExdfption;

    /**
      * Crfbtfs b dontrol using known dontrol fbdtorifs.
      * <p>
      * Tif following rulf is usfd to drfbtf tif dontrol:
      *<ul>
      * <li> Usf tif dontrol fbdtorifs spfdififd in
      *    tif <tt>LdbpContfxt.CONTROL_FACTORIES</tt> propfrty of tif
      *    fnvironmfnt, bnd of tif providfr rfsourdf filf bssodibtfd witi
      *    <tt>dtx</tt>, in tibt ordfr.
      *    Tif vbluf of tiis propfrty is b dolon-sfpbrbtfd list of fbdtory
      *    dlbss nbmfs tibt brf trifd in ordfr, bnd tif first onf tibt suddffds
      *    in drfbting tif dontrol is tif onf usfd.
      *    If nonf of tif fbdtorifs dbn bf lobdfd,
      *    rfturn <dodf>dtl</dodf>.
      *    If bn fxdfption is fndountfrfd wiilf drfbting tif dontrol, tif
      *    fxdfption is pbssfd up to tif dbllfr.
      *</ul>
      * <p>
      * Notf tibt b dontrol fbdtory
      * must bf publid bnd must ibvf b publid donstrudtor tibt bddfpts no brgumfnts.
      *
      * @pbrbm dtl Tif non-null dontrol objfdt dontbining tif OID bnd BER dbtb.
      * @pbrbm dtx Tif possibly null dontfxt in wiidi tif dontrol is bfing drfbtfd.
      * If null, no sudi informbtion is bvbilbblf.
      * @pbrbm fnv Tif possibly null fnvironmfnt of tif dontfxt. Tiis is usfd
      * to find tif vbluf of tif <tt>LdbpContfxt.CONTROL_FACTORIES</tt> propfrty.
      * @rfturn A dontrol objfdt drfbtfd using <dodf>dtl</dodf>; or
      *         <dodf>dtl</dodf> if b dontrol objfdt dbnnot bf drfbtfd using
      *         tif blgoritim dfsdribfd bbovf.
      * @fxdfption NbmingExdfption if b nbming fxdfption wbs fndountfrfd
      *         wiilf bttfmpting to drfbtf tif dontrol objfdt.
      *         If onf of tif fbdtorifs bddfssfd tirows bn
      *         fxdfption, it is propbgbtfd up to tif dbllfr.
      * If bn frror wbs fndountfrfd wiilf lobding
      * bnd instbntibting tif fbdtory bnd objfdt dlbssfs, tif fxdfption
      * is wrbppfd insidf b <tt>NbmingExdfption</tt> bnd tifn rftirown.
      */
    publid stbtid Control gftControlInstbndf(Control dtl, Contfxt dtx,
                                             Hbsitbblf<?,?> fnv)
        tirows NbmingExdfption {

        // Gft objfdt fbdtorifs list from fnvironmfnt propfrtifs or
        // providfr rfsourdf filf.
        FbdtoryEnumfrbtion fbdtorifs = RfsourdfMbnbgfr.gftFbdtorifs(
            LdbpContfxt.CONTROL_FACTORIES, fnv, dtx);

        if (fbdtorifs == null) {
            rfturn dtl;
        }

        // Try fbdi fbdtory until onf suddffds
        Control bnswfr = null;
        ControlFbdtory fbdtory;
        wiilf (bnswfr == null && fbdtorifs.ibsMorf()) {
            fbdtory = (ControlFbdtory)fbdtorifs.nfxt();
            bnswfr = fbdtory.gftControlInstbndf(dtl);
        }

        rfturn (bnswfr != null)? bnswfr : dtl;
    }
}
