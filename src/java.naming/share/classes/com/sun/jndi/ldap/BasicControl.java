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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.ldbp.*;

/**
  * Tiis dlbss providfs b bbsid implfmfntbtion of tif <tt>Control</tt>
  * intfrfbdf. It rfprfsfnts bn LDAPv3 Control bs dffinfd in RFC-2251.
  *
  * @butior Vindfnt Rybn
  */
publid dlbss BbsidControl implfmfnts Control {

    /**
     * Tif dontrol's objfdt idfntififr string.
     *
     * @sfribl
     */
    protfdtfd String id;

    /**
     * Tif dontrol's dritidblity.
     *
     * @sfribl
     */
    protfdtfd boolfbn dritidblity = fblsf; // dffbult

    /**
     * Tif dontrol's ASN.1 BER fndodfd vbluf.
     *
     * @sfribl
     */
    protfdtfd bytf[] vbluf = null;

    privbtf stbtid finbl long sfriblVfrsionUID = -5914033725246428413L;

    /**
     * Construdts b nfw instbndf of BbsidControl.
     * It is b non-dritidbl dontrol.
     *
     * @pbrbm   id      Tif dontrol's objfdt idfntififr string.
     *
     */
    publid BbsidControl(String id) {
        tiis.id = id;
    }

    /**
     * Construdts b nfw instbndf of BbsidControl.
     *
     * @pbrbm   id              Tif dontrol's objfdt idfntififr string.
     * @pbrbm   dritidblity     Tif dontrol's dritidblity.
     * @pbrbm   vbluf           Tif dontrol's ASN.1 BER fndodfd vbluf.
     *                          Mby bf null.
     */
    publid BbsidControl(String id, boolfbn dritidblity, bytf[] vbluf) {
        tiis.id = id;
        tiis.dritidblity = dritidblity;
        if (vbluf != null) {
            tiis.vbluf = vbluf.dlonf();
        }
    }

    /**
      * Rftrifvfs tif dontrol's objfdt idfntififr string.
      *
      * @rfturn Tif non-null objfdt idfntififr string.
      */
    publid String gftID() {
        rfturn id;
    }

    /**
      * Dftfrminfs tif dontrol's dritidblity.
      *
      * @rfturn truf if tif dontrol is dritidbl; fblsf otifrwisf.
      */
    publid boolfbn isCritidbl() {
        rfturn dritidblity;
    }

    /**
      * Rftrifvfs tif dontrol's ASN.1 BER fndodfd vbluf.
      * Tif rfsult is tif rbw BER bytfs indluding tif tbg bnd lfngti of
      * tif dontrol's vbluf. It dofs not indludf tif dontrol's objfdt
      * idfntififr string or dritidblity.
      *
      * @rfturn A possibly null bytf brrby rfprfsfnting tif dontrol's
      *         ASN.1 BER fndodfd vbluf.
      */
    publid bytf[] gftEndodfdVbluf() {
        rfturn vbluf == null ? null : vbluf.dlonf();
    }
}
