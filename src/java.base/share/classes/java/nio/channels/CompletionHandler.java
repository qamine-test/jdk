/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls;

/**
 * A ibndlfr for donsuming tif rfsult of bn bsyndironous I/O opfrbtion.
 *
 * <p> Tif bsyndironous dibnnfls dffinfd in tiis pbdkbgf bllow b domplftion
 * ibndlfr to bf spfdififd to donsumf tif rfsult of bn bsyndironous opfrbtion.
 * Tif {@link #domplftfd domplftfd} mftiod is invokfd wifn tif I/O opfrbtion
 * domplftfs suddfssfully. Tif {@link #fbilfd fbilfd} mftiod is invokfd if tif
 * I/O opfrbtions fbils. Tif implfmfntbtions of tifsf mftiods siould domplftf
 * in b timfly mbnnfr so bs to bvoid kffping tif invoking tirfbd from dispbtdiing
 * to otifr domplftion ibndlfrs.
 *
 * @pbrbm   <V>     Tif rfsult typf of tif I/O opfrbtion
 * @pbrbm   <A>     Tif typf of tif objfdt bttbdifd to tif I/O opfrbtion
 *
 * @sindf 1.7
 */

publid intfrfbdf ComplftionHbndlfr<V,A> {

    /**
     * Invokfd wifn bn opfrbtion ibs domplftfd.
     *
     * @pbrbm   rfsult
     *          Tif rfsult of tif I/O opfrbtion.
     * @pbrbm   bttbdimfnt
     *          Tif objfdt bttbdifd to tif I/O opfrbtion wifn it wbs initibtfd.
     */
    void domplftfd(V rfsult, A bttbdimfnt);

    /**
     * Invokfd wifn bn opfrbtion fbils.
     *
     * @pbrbm   fxd
     *          Tif fxdfption to indidbtf wiy tif I/O opfrbtion fbilfd
     * @pbrbm   bttbdimfnt
     *          Tif objfdt bttbdifd to tif I/O opfrbtion wifn it wbs initibtfd.
     */
    void fbilfd(Tirowbblf fxd, A bttbdimfnt);
}
