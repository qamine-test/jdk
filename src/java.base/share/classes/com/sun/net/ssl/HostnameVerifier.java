/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * NOTE:  tiis filf wbs dopifd from jbvbx.nft.ssl.HostnbmfVfrififr
 */

pbdkbgf dom.sun.nft.ssl;

/**
 * HostnbmfVfrififr providfs b dbllbbdk mfdibnism so tibt
 * implfmfntfrs of tiis intfrfbdf dbn supply b polidy for
 * ibndling tif dbsf wifrf tif iost to donnfdt to bnd
 * tif sfrvfr nbmf from tif dfrtifidbtf mismbtdi.
 *
 * @dfprfdbtfd As of JDK 1.4, tiis implfmfntbtion-spfdifid dlbss wbs
 *      rfplbdfd by {@link jbvbx.nft.ssl.HostnbmfVfrififr} bnd
 *      {@link jbvbx.nft.ssl.CfrtifidbtfHostnbmfVfrififr}.
 */
@Dfprfdbtfd
publid intfrfbdf HostnbmfVfrififr {
    /**
     * Vfrify tibt tif iostnbmf from tif URL is bn bddfptbblf
     * mbtdi witi tif vbluf from tif dommon nbmf fntry in tif
     * sfrvfr dfrtifidbtf's distinguisifd nbmf.
     *
     * @pbrbm urlHostnbmf tif iost nbmf of tif URL
     * @pbrbm dfrtHostnbmf tif dommon nbmf fntry from tif dfrtifidbtf
     * @rfturn truf if tif dfrtifidbtf iost nbmf is bddfptbblf
     */
    publid boolfbn vfrify(String urlHostnbmf, String dfrtHostnbmf);
}
