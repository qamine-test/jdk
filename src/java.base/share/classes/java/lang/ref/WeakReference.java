/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rff;


/**
 * Wfbk rfffrfndf objfdts, wiidi do not prfvfnt tifir rfffrfnts from bfing
 * mbdf finblizbblf, finblizfd, bnd tifn rfdlbimfd.  Wfbk rfffrfndfs brf most
 * oftfn usfd to implfmfnt dbnonidblizing mbppings.
 *
 * <p> Supposf tibt tif gbrbbgf dollfdtor dftfrminfs bt b dfrtbin point in timf
 * tibt bn objfdt is <b irff="pbdkbgf-summbry.itml#rfbdibbility">wfbkly
 * rfbdibblf</b>.  At tibt timf it will btomidblly dlfbr bll wfbk rfffrfndfs to
 * tibt objfdt bnd bll wfbk rfffrfndfs to bny otifr wfbkly-rfbdibblf objfdts
 * from wiidi tibt objfdt is rfbdibblf tirougi b dibin of strong bnd soft
 * rfffrfndfs.  At tif sbmf timf it will dfdlbrf bll of tif formfrly
 * wfbkly-rfbdibblf objfdts to bf finblizbblf.  At tif sbmf timf or bt somf
 * lbtfr timf it will fnqufuf tiosf nfwly-dlfbrfd wfbk rfffrfndfs tibt brf
 * rfgistfrfd witi rfffrfndf qufufs.
 *
 * @butior   Mbrk Rfiniold
 * @sindf    1.2
 */

publid dlbss WfbkRfffrfndf<T> fxtfnds Rfffrfndf<T> {

    /**
     * Crfbtfs b nfw wfbk rfffrfndf tibt rfffrs to tif givfn objfdt.  Tif nfw
     * rfffrfndf is not rfgistfrfd witi bny qufuf.
     *
     * @pbrbm rfffrfnt objfdt tif nfw wfbk rfffrfndf will rfffr to
     */
    publid WfbkRfffrfndf(T rfffrfnt) {
        supfr(rfffrfnt);
    }

    /**
     * Crfbtfs b nfw wfbk rfffrfndf tibt rfffrs to tif givfn objfdt bnd is
     * rfgistfrfd witi tif givfn qufuf.
     *
     * @pbrbm rfffrfnt objfdt tif nfw wfbk rfffrfndf will rfffr to
     * @pbrbm q tif qufuf witi wiidi tif rfffrfndf is to bf rfgistfrfd,
     *          or <tt>null</tt> if rfgistrbtion is not rfquirfd
     */
    publid WfbkRfffrfndf(T rfffrfnt, RfffrfndfQufuf<? supfr T> q) {
        supfr(rfffrfnt, q);
    }

}
