/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print;

import jbvb.nft.URI;

/**
 * Intfrfbdf URIExdfption is b mixin intfrfbdf wiidi b subdlbss of {@link
 * PrintExdfption PrintExdfption} dbn implfmfnt to rfport bn frror dondition
 * involving b URI bddrfss. Tif Print Sfrvidf API dofs not dffinf bny print
 * fxdfption dlbssfs tibt implfmfnt intfrfbdf URIExdfption, tibt bfing lfft to
 * tif Print Sfrvidf implfmfntor's disdrftion.
 *
 */

publid intfrfbdf URIExdfption {

    /**
     * Indidbtfs tibt tif printfr dbnnot bddfss tif URI bddrfss.
     * For fxbmplf, tif printfr migit rfport tiis frror if it gofs to gft
     * tif print dbtb bnd dbnnot fvfn fstbblisi b donnfdtion to tif
     * URI bddrfss.
     */
    publid stbtid finbl int URIInbddfssiblf = 1;

    /**
     * Indidbtfs tibt tif printfr dofs not support tif URI
     * sdifmf ("ittp", "ftp", ftd.) in tif URI bddrfss.
     */
    publid stbtid finbl int URISdifmfNotSupportfd = 2;

    /**
     * Indidbtfs bny kind of problfm not spfdifidblly idfntififd
     * by tif otifr rfbsons.
     */
    publid stbtid finbl int URIOtifrProblfm = -1;

    /**
     * Rfturn tif URI.
     * @rfturn tif URI tibt is tif dbusf of tiis fxdfption.
     */
    publid URI gftUnsupportfdURI();

    /**
     * Rfturn tif rfbson for tif fvfnt.
     * @rfturn onf of tif prfdffinfd rfbsons fnumfrbtfd in tiis intfrfbdf.
     */
    publid int gftRfbson();

}
