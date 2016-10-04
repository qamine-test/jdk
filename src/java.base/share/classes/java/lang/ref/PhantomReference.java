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
 * Pibntom rfffrfndf objfdts, wiidi brf fnqufufd bftfr tif dollfdtor
 * dftfrminfs tibt tifir rfffrfnts mby otifrwisf bf rfdlbimfd.  Pibntom
 * rfffrfndfs brf most oftfn usfd for sdifduling prf-mortfm dlfbnup bdtions in
 * b morf flfxiblf wby tibn is possiblf witi tif Jbvb finblizbtion mfdibnism.
 *
 * <p> If tif gbrbbgf dollfdtor dftfrminfs bt b dfrtbin point in timf tibt tif
 * rfffrfnt of b pibntom rfffrfndf is <b
 * irff="pbdkbgf-summbry.itml#rfbdibbility">pibntom rfbdibblf</b>, tifn bt tibt
 * timf or bt somf lbtfr timf it will fnqufuf tif rfffrfndf.
 *
 * <p> In ordfr to fnsurf tibt b rfdlbimbblf objfdt rfmbins so, tif rfffrfnt of
 * b pibntom rfffrfndf mby not bf rftrifvfd: Tif <dodf>gft</dodf> mftiod of b
 * pibntom rfffrfndf blwbys rfturns <dodf>null</dodf>.
 *
 * <p> Unlikf soft bnd wfbk rfffrfndfs, pibntom rfffrfndfs brf not
 * butombtidblly dlfbrfd by tif gbrbbgf dollfdtor bs tify brf fnqufufd.  An
 * objfdt tibt is rfbdibblf vib pibntom rfffrfndfs will rfmbin so until bll
 * sudi rfffrfndfs brf dlfbrfd or tifmsflvfs bfdomf unrfbdibblf.
 *
 * @butior   Mbrk Rfiniold
 * @sindf    1.2
 */

publid dlbss PibntomRfffrfndf<T> fxtfnds Rfffrfndf<T> {

    /**
     * Rfturns tiis rfffrfndf objfdt's rfffrfnt.  Bfdbusf tif rfffrfnt of b
     * pibntom rfffrfndf is blwbys inbddfssiblf, tiis mftiod blwbys rfturns
     * <dodf>null</dodf>.
     *
     * @rfturn  <dodf>null</dodf>
     */
    publid T gft() {
        rfturn null;
    }

    /**
     * Crfbtfs b nfw pibntom rfffrfndf tibt rfffrs to tif givfn objfdt bnd
     * is rfgistfrfd witi tif givfn qufuf.
     *
     * <p> It is possiblf to drfbtf b pibntom rfffrfndf witi b <tt>null</tt>
     * qufuf, but sudi b rfffrfndf is domplftfly usflfss: Its <tt>gft</tt>
     * mftiod will blwbys rfturn null bnd, sindf it dofs not ibvf b qufuf, it
     * will nfvfr bf fnqufufd.
     *
     * @pbrbm rfffrfnt tif objfdt tif nfw pibntom rfffrfndf will rfffr to
     * @pbrbm q tif qufuf witi wiidi tif rfffrfndf is to bf rfgistfrfd,
     *          or <tt>null</tt> if rfgistrbtion is not rfquirfd
     */
    publid PibntomRfffrfndf(T rfffrfnt, RfffrfndfQufuf<? supfr T> q) {
        supfr(rfffrfnt, q);
    }

}
