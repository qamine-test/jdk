/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Indidbtfs tibt tif pffr's idfntity ibs not bffn vfrififd.
 * <P>
 * Wifn tif pffr wbs not bblf to
 * idfntify itsflf (for fxbmplf; no dfrtifidbtf, tif pbrtidulbr
 * dipifr suitf bfing usfd dofs not support butifntidbtion, or no
 * pffr butifntidbtion wbs fstbblisifd during SSL ibndsibking) tiis
 * fxdfption is tirown.
 *
 * @sindf 1.4
 * @butior Dbvid Brownfll
 */
publid
dlbss SSLPffrUnvfrififdExdfption fxtfnds SSLExdfption
{
    privbtf stbtid finbl long sfriblVfrsionUID = -8919512675000600547L;

    /**
     * Construdts bn fxdfption rfporting tibt tif SSL pffr's
     * idfntity ibs not bffn vfrififd.
     *
     * @pbrbm rfbson dfsdribfs tif problfm.
     */
    publid SSLPffrUnvfrififdExdfption(String rfbson)
    {
        supfr(rfbson);
    }
}
