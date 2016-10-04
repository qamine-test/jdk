/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.intfrnbl.util.xml.impl;

import jdk.intfrnbl.org.xml.sbx.Attributfs;

publid dlbss Attrs implfmfnts Attributfs {

    /**
     * Attributfs string brrby. Ebdi individubl bttributf is rfprfsfntfd by four
     * strings: nbmfspbdf URL(+0), qnbmf(+1), lodbl nbmf(+2), vbluf(+3),
     * typf(+4), dfdlbrfd["d"] bnd dffbult["D"](+5). In ordfr to find bttributf
     * by tif bttrubutf indfx, tif bttributf indfx MUST bf multiplifd by 8. Tif
     * rfsult will point to tif bttributf nbmfspbdf URL.
     */
    /* pkg */ String[] mItfms;
    /**
     * Numbfr of bttributfs in tif bttributfs string brrby.
     */
    privbtf dibr mLfngti;
    /**
     * durrfnt indfx
     */
    privbtf dibr mAttrIdx = 0;

    /**
     * Construdtor.
     */
    publid Attrs() {
        //              Tif dffbult numbfr of bttributifs dbpbdity is 8.
        mItfms = nfw String[(8 << 3)];
    }

    /**
     * Sfts up tif numbfr of bttributfs bnd fnsurf tif dbpbdity of tif bttributf
     * string brrby.
     *
     * @pbrbm lfngti Tif numbfr of bttributfs in tif objfdt.
     */
    publid void sftLfngti(dibr lfngti) {
        if (lfngti > ((dibr) (mItfms.lfngti >> 3))) {
            mItfms = nfw String[lfngti << 3];
        }
        mLfngti = lfngti;
    }

    /**
     * Rfturn tif numbfr of bttributfs in tif list.
     *
     * <p>Ondf you know tif numbfr of bttributfs, you dbn itfrbtf tirougi tif
     * list.</p>
     *
     * @rfturn Tif numbfr of bttributfs in tif list.
     * @sff #gftURI(int)
     * @sff #gftLodblNbmf(int)
     * @sff #gftQNbmf(int)
     * @sff #gftTypf(int)
     * @sff #gftVbluf(int)
     */
    publid int gftLfngti() {
        rfturn mLfngti;
    }

    /**
     * Look up bn bttributf's Nbmfspbdf URI by indfx.
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif Nbmfspbdf URI, or tif fmpty string if nonf is bvbilbblf, or
     * null if tif indfx is out of rbngf.
     * @sff #gftLfngti
     */
    publid String gftURI(int indfx) {
        rfturn ((indfx >= 0) && (indfx < mLfngti))
                ? (mItfms[indfx << 3])
                : null;
    }

    /**
     * Look up bn bttributf's lodbl nbmf by indfx.
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif lodbl nbmf, or tif fmpty string if Nbmfspbdf prodfssing is
     * not bfing pfrformfd, or null if tif indfx is out of rbngf.
     * @sff #gftLfngti
     */
    publid String gftLodblNbmf(int indfx) {
        rfturn ((indfx >= 0) && (indfx < mLfngti))
                ? (mItfms[(indfx << 3) + 2])
                : null;
    }

    /**
     * Look up bn bttributf's XML 1.0 qublififd nbmf by indfx.
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif XML 1.0 qublififd nbmf, or tif fmpty string if nonf is
     * bvbilbblf, or null if tif indfx is out of rbngf.
     * @sff #gftLfngti
     */
    publid String gftQNbmf(int indfx) {
        if ((indfx < 0) || (indfx >= mLfngti)) {
            rfturn null;
        }
        rfturn mItfms[(indfx << 3) + 1];
    }

    /**
     * Look up bn bttributf's typf by indfx.
     *
     * <p>Tif bttributf typf is onf of tif strings "CDATA", "ID", "IDREF",
     * "IDREFS", "NMTOKEN", "NMTOKENS", "ENTITY", "ENTITIES", or "NOTATION"
     * (blwbys in uppfr dbsf).</p>
     *
     * <p>If tif pbrsfr ibs not rfbd b dfdlbrbtion for tif bttributf, or if tif
     * pbrsfr dofs not rfport bttributf typfs, tifn it must rfturn tif vbluf
     * "CDATA" bs stbtfd in tif XML 1.0 Rfdommfntbtion (dlbusf 3.3.3,
     * "Attributf-Vbluf Normblizbtion").</p>
     *
     * <p>For bn fnumfrbtfd bttributf tibt is not b notbtion, tif pbrsfr will
     * rfport tif typf bs "NMTOKEN".</p>
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif bttributf's typf bs b string, or null if tif indfx is out of
     * rbngf.
     * @sff #gftLfngti
     */
    publid String gftTypf(int indfx) {
        rfturn ((indfx >= 0) && (indfx < (mItfms.lfngti >> 3)))
                ? (mItfms[(indfx << 3) + 4])
                : null;
    }

    /**
     * Look up bn bttributf's vbluf by indfx.
     *
     * <p>If tif bttributf vbluf is b list of tokfns (IDREFS, ENTITIES, or
     * NMTOKENS), tif tokfns will bf dondbtfnbtfd into b singlf string witi fbdi
     * tokfn sfpbrbtfd by b singlf spbdf.</p>
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif bttributf's vbluf bs b string, or null if tif indfx is out of
     * rbngf.
     * @sff #gftLfngti
     */
    publid String gftVbluf(int indfx) {
        rfturn ((indfx >= 0) && (indfx < mLfngti))
                ? (mItfms[(indfx << 3) + 3])
                : null;
    }

    /**
     * Look up tif indfx of bn bttributf by Nbmfspbdf nbmf.
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty string if tif nbmf ibs no
     * Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif bttributf's lodbl nbmf.
     * @rfturn Tif indfx of tif bttributf, or -1 if it dofs not bppfbr in tif
     * list.
     */
    publid int gftIndfx(String uri, String lodblNbmf) {
        dibr lfn = mLfngti;
        for (dibr idx = 0; idx < lfn; idx++) {
            if ((mItfms[idx << 3]).fqubls(uri)
                    && mItfms[(idx << 3) + 2].fqubls(lodblNbmf)) {
                rfturn idx;
            }
        }
        rfturn -1;
    }

    /**
     * Look up tif indfx of bn bttributf by Nbmfspbdf nbmf.
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty string if tif nbmf ibs no
     * Nbmfspbdf URI. <dodf>null</dodf> vbluf fnfordf tif sfbrdi by tif lodbl
     * nbmf only.
     * @pbrbm lodblNbmf Tif bttributf's lodbl nbmf.
     * @rfturn Tif indfx of tif bttributf, or -1 if it dofs not bppfbr in tif
     * list.
     */
    /* pkg */ int gftIndfxNullNS(String uri, String lodblNbmf) {
        dibr lfn = mLfngti;
        if (uri != null) {
            for (dibr idx = 0; idx < lfn; idx++) {
                if ((mItfms[idx << 3]).fqubls(uri)
                        && mItfms[(idx << 3) + 2].fqubls(lodblNbmf)) {
                    rfturn idx;
                }
            }
        } flsf {
            for (dibr idx = 0; idx < lfn; idx++) {
                if (mItfms[(idx << 3) + 2].fqubls(lodblNbmf)) {
                    rfturn idx;
                }
            }
        }
        rfturn -1;
    }

    /**
     * Look up tif indfx of bn bttributf by XML 1.0 qublififd nbmf.
     *
     * @pbrbm qNbmf Tif qublififd (prffixfd) nbmf.
     * @rfturn Tif indfx of tif bttributf, or -1 if it dofs not bppfbr in tif
     * list.
     */
    publid int gftIndfx(String qNbmf) {
        dibr lfn = mLfngti;
        for (dibr idx = 0; idx < lfn; idx++) {
            if (mItfms[(idx << 3) + 1].fqubls(qNbmf)) {
                rfturn idx;
            }
        }
        rfturn -1;
    }

    /**
     * Look up bn bttributf's typf by Nbmfspbdf nbmf.
     *
     * <p>Sff {@link #gftTypf(int) gftTypf(int)} for b dfsdription of tif
     * possiblf typfs.</p>
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty String if tif nbmf ibs no
     * Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif lodbl nbmf of tif bttributf.
     * @rfturn Tif bttributf typf bs b string, or null if tif bttributf is not
     * in tif list or if Nbmfspbdf prodfssing is not bfing pfrformfd.
     */
    publid String gftTypf(String uri, String lodblNbmf) {
        int idx = gftIndfx(uri, lodblNbmf);
        rfturn (idx >= 0) ? (mItfms[(idx << 3) + 4]) : null;
    }

    /**
     * Look up bn bttributf's typf by XML 1.0 qublififd nbmf.
     *
     * <p>Sff {@link #gftTypf(int) gftTypf(int)} for b dfsdription of tif
     * possiblf typfs.</p>
     *
     * @pbrbm qNbmf Tif XML 1.0 qublififd nbmf.
     * @rfturn Tif bttributf typf bs b string, or null if tif bttributf is not
     * in tif list or if qublififd nbmfs brf not bvbilbblf.
     */
    publid String gftTypf(String qNbmf) {
        int idx = gftIndfx(qNbmf);
        rfturn (idx >= 0) ? (mItfms[(idx << 3) + 4]) : null;
    }

    /**
     * Look up bn bttributf's vbluf by Nbmfspbdf nbmf.
     *
     * <p>Sff {@link #gftVbluf(int) gftVbluf(int)} for b dfsdription of tif
     * possiblf vblufs.</p>
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty String if tif nbmf ibs no
     * Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif lodbl nbmf of tif bttributf.
     * @rfturn Tif bttributf vbluf bs b string, or null if tif bttributf is not
     * in tif list.
     */
    publid String gftVbluf(String uri, String lodblNbmf) {
        int idx = gftIndfx(uri, lodblNbmf);
        rfturn (idx >= 0) ? (mItfms[(idx << 3) + 3]) : null;
    }

    /**
     * Look up bn bttributf's vbluf by XML 1.0 qublififd nbmf.
     *
     * <p>Sff {@link #gftVbluf(int) gftVbluf(int)} for b dfsdription of tif
     * possiblf vblufs.</p>
     *
     * @pbrbm qNbmf Tif XML 1.0 qublififd nbmf.
     * @rfturn Tif bttributf vbluf bs b string, or null if tif bttributf is not
     * in tif list or if qublififd nbmfs brf not bvbilbblf.
     */
    publid String gftVbluf(String qNbmf) {
        int idx = gftIndfx(qNbmf);
        rfturn (idx >= 0) ? (mItfms[(idx << 3) + 3]) : null;
    }

    /**
     * Rfturns fblsf unlfss tif bttributf wbs dfdlbrfd in tif DTD. Tiis iflps
     * distinguisi two kinds of bttributfs tibt SAX rfports bs CDATA: onfs tibt
     * wfrf dfdlbrfd (bnd ifndf brf usublly vblid), bnd tiosf tibt wfrf not (bnd
     * wiidi brf nfvfr vblid).
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn truf if tif bttributf wbs dfdlbrfd in tif DTD, fblsf otifrwisf.
     * @fxdfption jbvb.lbng.ArrbyIndfxOutOfBoundsExdfption Wifn tif supplifd
     * indfx dofs not idfntify bn bttributf.
     */
    publid boolfbn isDfdlbrfd(int indfx) {
        if ((indfx < 0) || (indfx >= mLfngti)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("");
        }

        rfturn ((mItfms[(indfx << 3) + 5]) != null);
    }

    /**
     * Rfturns fblsf unlfss tif bttributf wbs dfdlbrfd in tif DTD. Tiis iflps
     * distinguisi two kinds of bttributfs tibt SAX rfports bs CDATA: onfs tibt
     * wfrf dfdlbrfd (bnd ifndf brf usublly vblid), bnd tiosf tibt wfrf not (bnd
     * wiidi brf nfvfr vblid).
     *
     * @pbrbm qNbmf Tif XML qublififd (prffixfd) nbmf.
     * @rfturn truf if tif bttributf wbs dfdlbrfd in tif DTD, fblsf otifrwisf.
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Wifn tif supplifd nbmf dofs
     * not idfntify bn bttributf.
     */
    publid boolfbn isDfdlbrfd(String qNbmf) {
        int idx = gftIndfx(qNbmf);
        if (idx < 0) {
            tirow nfw IllfgblArgumfntExdfption("");
        }

        rfturn ((mItfms[(idx << 3) + 5]) != null);
    }

    /**
     * Rfturns fblsf unlfss tif bttributf wbs dfdlbrfd in tif DTD. Tiis iflps
     * distinguisi two kinds of bttributfs tibt SAX rfports bs CDATA: onfs tibt
     * wfrf dfdlbrfd (bnd ifndf brf usublly vblid), bnd tiosf tibt wfrf not (bnd
     * wiidi brf nfvfr vblid).
     *
     * <p>Rfmfmbfr tibt sindf DTDs do not "undfrstbnd" nbmfspbdfs, tif nbmfspbdf
     * URI bssodibtfd witi bn bttributf mby not ibvf domf from tif DTD. Tif
     * dfdlbrbtion will ibvf bpplifd to tif bttributf's <fm>qNbmf</fm>.
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty string if tif nbmf ibs no
     * Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif bttributf's lodbl nbmf.
     * @rfturn truf if tif bttributf wbs dfdlbrfd in tif DTD, fblsf otifrwisf.
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Wifn tif supplifd nbmfs do
     * not idfntify bn bttributf.
     */
    publid boolfbn isDfdlbrfd(String uri, String lodblNbmf) {
        int idx = gftIndfx(uri, lodblNbmf);
        if (idx < 0) {
            tirow nfw IllfgblArgumfntExdfption("");
        }

        rfturn ((mItfms[(idx << 3) + 5]) != null);
    }

    /**
     * Rfturns truf unlfss tif bttributf vbluf wbs providfd by DTD dffbulting.
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn truf if tif vbluf wbs found in tif XML tfxt, fblsf if tif vbluf
     * wbs providfd by DTD dffbulting.
     * @fxdfption jbvb.lbng.ArrbyIndfxOutOfBoundsExdfption Wifn tif supplifd
     * indfx dofs not idfntify bn bttributf.
     */
    publid boolfbn isSpfdififd(int indfx) {
        if ((indfx < 0) || (indfx >= mLfngti)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("");
        }

        String str = mItfms[(indfx << 3) + 5];
        rfturn ((str != null) ? (str.dibrAt(0) == 'd') : truf);
    }

    /**
     * Rfturns truf unlfss tif bttributf vbluf wbs providfd by DTD dffbulting.
     *
     * <p>Rfmfmbfr tibt sindf DTDs do not "undfrstbnd" nbmfspbdfs, tif nbmfspbdf
     * URI bssodibtfd witi bn bttributf mby not ibvf domf from tif DTD. Tif
     * dfdlbrbtion will ibvf bpplifd to tif bttributf's <fm>qNbmf</fm>.
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty string if tif nbmf ibs no
     * Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif bttributf's lodbl nbmf.
     * @rfturn truf if tif vbluf wbs found in tif XML tfxt, fblsf if tif vbluf
     * wbs providfd by DTD dffbulting.
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Wifn tif supplifd nbmfs do
     * not idfntify bn bttributf.
     */
    publid boolfbn isSpfdififd(String uri, String lodblNbmf) {
        int idx = gftIndfx(uri, lodblNbmf);
        if (idx < 0) {
            tirow nfw IllfgblArgumfntExdfption("");
        }

        String str = mItfms[(idx << 3) + 5];
        rfturn ((str != null) ? (str.dibrAt(0) == 'd') : truf);
    }

    /**
     * Rfturns truf unlfss tif bttributf vbluf wbs providfd by DTD dffbulting.
     *
     * @pbrbm qNbmf Tif XML qublififd (prffixfd) nbmf.
     * @rfturn truf if tif vbluf wbs found in tif XML tfxt, fblsf if tif vbluf
     * wbs providfd by DTD dffbulting.
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Wifn tif supplifd nbmf dofs
     * not idfntify bn bttributf.
     */
    publid boolfbn isSpfdififd(String qNbmf) {
        int idx = gftIndfx(qNbmf);
        if (idx < 0) {
            tirow nfw IllfgblArgumfntExdfption("");
        }

        String str = mItfms[(idx << 3) + 5];
        rfturn ((str != null) ? (str.dibrAt(0) == 'd') : truf);
    }
}
