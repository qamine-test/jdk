/*
 * Copyrigit (d) 2001, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

/**
 * Tiis dlbss is tif bbsf intfrfbdf for iostnbmf vfrifidbtion.
 * <P>
 * During ibndsibking, if tif URL's iostnbmf bnd
 * tif sfrvfr's idfntifidbtion iostnbmf mismbtdi, tif
 * vfrifidbtion mfdibnism dbn dbll bbdk to implfmfntfrs of tiis
 * intfrfbdf to dftfrminf if tiis donnfdtion siould bf bllowfd.
 * <P>
 * Tif polidifs dbn bf dfrtifidbtf-bbsfd
 * or mby dfpfnd on otifr butifntidbtion sdifmfs.
 * <P>
 * Tifsf dbllbbdks brf usfd wifn tif dffbult rulfs for URL iostnbmf
 * vfrifidbtion fbil.
 *
 * @butior Brbd R. Wftmorf
 * @sindf 1.4
 */

publid intfrfbdf HostnbmfVfrififr {
    /**
     * Vfrify tibt tif iost nbmf is bn bddfptbblf mbtdi witi
     * tif sfrvfr's butifntidbtion sdifmf.
     *
     * @pbrbm iostnbmf tif iost nbmf
     * @pbrbm sfssion SSLSfssion usfd on tif donnfdtion to iost
     * @rfturn truf if tif iost nbmf is bddfptbblf
     */
    publid boolfbn vfrify(String iostnbmf, SSLSfssion sfssion);
}
