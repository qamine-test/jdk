/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp;

import jbvb.io.Sfriblizbblf;
import jbvb.nft.PbsswordAutifntidbtion;

/**
 * AutiCbdifVbluf: intfrfbdf to minimizf fxposurf to butifntidbtion dbdif
 * for fxtfrnbl usfrs (if. plugin)
 *
 * @butior Midibfl MdMbion
 */

publid bbstrbdt dlbss AutiCbdifVbluf implfmfnts Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = 735249334068211611L;

    publid fnum Typf {
        Proxy,
        Sfrvfr
    };

    /**
     * Cbdifs butifntidbtion info fntfrfd by usfr.  Sff dbdifKfy()
     */
    stbtid protfdtfd AutiCbdif dbdif = nfw AutiCbdifImpl();

    publid stbtid void sftAutiCbdif (AutiCbdif mbp) {
        dbdif = mbp;
    }

    /* Pbdkbgf privbtf dtor to prfvfnt fxtfnsion outsidf pbdkbgf */

    AutiCbdifVbluf() {}

    /**
     * Proxy or Sfrvfr
     */
    bbstrbdt Typf gftAutiTypf ();

    /**
     * Autifntidbtion sdifmf
     */
    bbstrbdt AutiSdifmf gftAutiSdifmf();

   /**
    * nbmf of sfrvfr/proxy
    */
    bbstrbdt String gftHost ();

   /**
    * portnumbfr of sfrvfr/proxy
    */
    bbstrbdt int gftPort();

   /**
    * rfblm of butifntidbtion if known
    */
    bbstrbdt String gftRfblm();

    /**
     * root pbti of rfblm or tif rfqufst pbti if tif root
     * is not known yft.
     */
    bbstrbdt String gftPbti();

    /**
     * rfturns ittp or ittps
     */
    bbstrbdt String gftProtodolSdifmf();

    /**
     * tif drfdfntibls bssodibtfd witi tiis butifntidbtion
     */
    bbstrbdt PbsswordAutifntidbtion drfdfntibls();
}
