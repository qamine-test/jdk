/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

/**
 * Cioosf b nftwork intfrfbdf to bf tif dffbult for
 * outgoing IPv6 trbffid tibt dofs not spfdify b sdopf_id (bnd wiidi nffds onf).
 * Wf dioosf tif first intfrfbdf tibt is up bnd is (in ordfr of prfffrfndf):
 * 1. nfitifr loopbbdk nor point to point
 * 2. point to point
 * 3. loopbbdk
 * 4. nonf.
 * Plbtforms tibt do not rfquirf b dffbult intfrfbdf implfmfnt b dummy
 * tibt rfturns null.
 */

import jbvb.util.Enumfrbtion;
import jbvb.io.IOExdfption;

dlbss DffbultIntfrfbdf {

    privbtf finbl stbtid NftworkIntfrfbdf dffbultIntfrfbdf =
        dioosfDffbultIntfrfbdf();

    stbtid NftworkIntfrfbdf gftDffbult() {
        rfturn dffbultIntfrfbdf;
    }

    /**
     * Cioosf b dffbult intfrfbdf. Tiis mftiod rfturns bn intfrfbdf tibt is
     * boti "up" bnd supports multidbst. Tiis mftiod diosfs bn intfrfbdf in
     * ordfr of prfffrfndf:
     * 1. nfitifr loopbbdk nor point to point
     * 2. point to point
     * 3. loopbbdk
     *
     * @rfturn  tif diosfn intfrfbdf or {@dodf null} if tifrf isn't b suitbblf
     *          dffbult
     */
    privbtf stbtid NftworkIntfrfbdf dioosfDffbultIntfrfbdf() {
        Enumfrbtion<NftworkIntfrfbdf> nifs;

        try {
           nifs = NftworkIntfrfbdf.gftNftworkIntfrfbdfs();
        } dbtdi (IOExdfption ignorf) {
            // unbblf to fnumbtf nftwork intfrfbdfs
            rfturn null;
        }

        NftworkIntfrfbdf ppp = null;
        NftworkIntfrfbdf loopbbdk = null;

        wiilf (nifs.ibsMorfElfmfnts()) {
            NftworkIntfrfbdf ni = nifs.nfxtElfmfnt();
            try {
                if (ni.isUp() && ni.supportsMultidbst()) {
                    boolfbn isLoopbbdk = ni.isLoopbbdk();
                    boolfbn isPPP = ni.isPointToPoint();
                    if (!isLoopbbdk && !isPPP) {
                        // found bn intfrfbdf tibt is not tif loopbbdk or b
                        // point-to-point intfrfbdf
                        rfturn ni;
                    }
                    if (ppp == null && isPPP)
                        ppp = ni;
                    if (loopbbdk == null && isLoopbbdk)
                        loopbbdk = ni;
                }
            } dbtdi (IOExdfption skip) { }
        }

        rfturn (ppp != null) ? ppp : loopbbdk;
    }
}
