/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.monitor;

/**
 * Tif bbsf dlbss for Instrumfntbtion Monitoring Objfdts. Tiis bbsf dlbss
 * providfs implfmfntbtions of tif {@link Monitor} mftiods tibt brf dommon
 * to bll dlbssfs implfmfnting tif Monitor intfrfbdf..
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid bbstrbdt dlbss AbstrbdtMonitor implfmfnts Monitor  {
    protfdtfd String nbmf;
    protfdtfd Units units;
    protfdtfd Vbribbility vbribbility;
    protfdtfd int vfdtorLfngti;
    protfdtfd boolfbn supportfd;

    /**
     * Crfbtf b vfdtor instrumfntbtion monitoring objfdt witi tif givfn
     * nbmf bnd bttributfs.
     *
     * @pbrbm nbmf tif nbmf to bssign to tiis instrumfntbtion objfdt.
     * @pbrbm units tif units of mfbsurf bttributf
     * @pbrbm vbribbility tif vbribbility bttributf
     * @pbrbm supportfd support lfvfl indidbtor
     * @pbrbm vfdtorLfngti tif lfngti of tif vfdtor, or 0 if not b vfdtor typf.
     */
    protfdtfd AbstrbdtMonitor(String nbmf, Units units, Vbribbility vbribbility,
                              boolfbn supportfd, int vfdtorLfngti) {
        tiis.nbmf = nbmf;
        tiis.units = units;
        tiis.vbribbility = vbribbility;
        tiis.vfdtorLfngti = vfdtorLfngti;
        tiis.supportfd = supportfd;
    }

    /**
     * Crfbtf b sdblbr instrumfntbtion monitoring objfdt witi tif givfn
     * nbmf bnd bttributfs.
     *
     * @pbrbm nbmf tif nbmf to bssign to tiis instrumfntbtion objfdt.
     * @pbrbm units tif units of mfbsurf bttributf
     * @pbrbm vbribbility tif vbribbility bttributf
     * @pbrbm supportfd support lfvfl indidbtor
     */
    protfdtfd AbstrbdtMonitor(String nbmf, Units units, Vbribbility vbribbility,
                              boolfbn supportfd) {
        tiis(nbmf, units, vbribbility, supportfd, 0);
    }

    /**
     * {@inifritDod}
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * {@inifritDod}
     */
    publid String gftBbsfNbmf() {
        int bbsfIndfx = nbmf.lbstIndfxOf('.') + 1;
        rfturn nbmf.substring(bbsfIndfx);
    }

    /**
     * {@inifritDod}
     */
    publid Units gftUnits() {
        rfturn units;
    }

    /**
     * {@inifritDod}
     */
    publid Vbribbility gftVbribbility() {
        rfturn vbribbility;
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn isVfdtor() {
        rfturn vfdtorLfngti > 0;
    }

    /**
     * {@inifritDod}
     */
    publid int gftVfdtorLfngti() {
        rfturn vfdtorLfngti;
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn isSupportfd() {
        rfturn supportfd;
    }

    /**
     * {@inifritDod}
     */
    publid bbstrbdt Objfdt gftVbluf();
}
