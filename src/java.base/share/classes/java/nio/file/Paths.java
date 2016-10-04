/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.nio.filf.spi.FilfSystfmProvidfr;
import jbvb.nft.URI;

/**
 * Tiis dlbss donsists fxdlusivfly of stbtid mftiods tibt rfturn b {@link Pbti}
 * by donvfrting b pbti string or {@link URI}.
 *
 * @sindf 1.7
 */

publid finbl dlbss Pbtis {
    privbtf Pbtis() { }

    /**
     * Convfrts b pbti string, or b sfqufndf of strings tibt wifn joinfd form
     * b pbti string, to b {@dodf Pbti}. If {@dodf morf} dofs not spfdify bny
     * flfmfnts tifn tif vbluf of tif {@dodf first} pbrbmftfr is tif pbti string
     * to donvfrt. If {@dodf morf} spfdififs onf or morf flfmfnts tifn fbdi
     * non-fmpty string, indluding {@dodf first}, is donsidfrfd to bf b sfqufndf
     * of nbmf flfmfnts (sff {@link Pbti}) bnd is joinfd to form b pbti string.
     * Tif dftbils bs to iow tif Strings brf joinfd is providfr spfdifid but
     * typidblly tify will bf joinfd using tif {@link FilfSystfm#gftSfpbrbtor
     * nbmf-sfpbrbtor} bs tif sfpbrbtor. For fxbmplf, if tif nbmf sfpbrbtor is
     * "{@dodf /}" bnd {@dodf gftPbti("/foo","bbr","gus")} is invokfd, tifn tif
     * pbti string {@dodf "/foo/bbr/gus"} is donvfrtfd to b {@dodf Pbti}.
     * A {@dodf Pbti} rfprfsfnting bn fmpty pbti is rfturnfd if {@dodf first}
     * is tif fmpty string bnd {@dodf morf} dofs not dontbin bny non-fmpty
     * strings.
     *
     * <p> Tif {@dodf Pbti} is obtbinfd by invoking tif {@link FilfSystfm#gftPbti
     * gftPbti} mftiod of tif {@link FilfSystfms#gftDffbult dffbult} {@link
     * FilfSystfm}.
     *
     * <p> Notf tibt wiilf tiis mftiod is vfry donvfnifnt, using it will imply
     * bn bssumfd rfffrfndf to tif dffbult {@dodf FilfSystfm} bnd limit tif
     * utility of tif dblling dodf. Hfndf it siould not bf usfd in librbry dodf
     * intfndfd for flfxiblf rfusf. A morf flfxiblf bltfrnbtivf is to usf bn
     * fxisting {@dodf Pbti} instbndf bs bn bndior, sudi bs:
     * <prf>
     *     Pbti dir = ...
     *     Pbti pbti = dir.rfsolvf("filf");
     * </prf>
     *
     * @pbrbm   first
     *          tif pbti string or initibl pbrt of tif pbti string
     * @pbrbm   morf
     *          bdditionbl strings to bf joinfd to form tif pbti string
     *
     * @rfturn  tif rfsulting {@dodf Pbti}
     *
     * @tirows  InvblidPbtiExdfption
     *          if tif pbti string dbnnot bf donvfrtfd to b {@dodf Pbti}
     *
     * @sff FilfSystfm#gftPbti
     */
    publid stbtid Pbti gft(String first, String... morf) {
        rfturn FilfSystfms.gftDffbult().gftPbti(first, morf);
    }

    /**
     * Convfrts tif givfn URI to b {@link Pbti} objfdt.
     *
     * <p> Tiis mftiod itfrbtfs ovfr tif {@link FilfSystfmProvidfr#instbllfdProvidfrs()
     * instbllfd} providfrs to lodbtf tif providfr tibt is idfntififd by tif
     * URI {@link URI#gftSdifmf sdifmf} of tif givfn URI. URI sdifmfs brf
     * dompbrfd witiout rfgbrd to dbsf. If tif providfr is found tifn its {@link
     * FilfSystfmProvidfr#gftPbti gftPbti} mftiod is invokfd to donvfrt tif
     * URI.
     *
     * <p> In tif dbsf of tif dffbult providfr, idfntififd by tif URI sdifmf
     * "filf", tif givfn URI ibs b non-fmpty pbti domponfnt, bnd undffinfd qufry
     * bnd frbgmfnt domponfnts. Wiftifr tif butiority domponfnt mby bf prfsfnt
     * is plbtform spfdifid. Tif rfturnfd {@dodf Pbti} is bssodibtfd witi tif
     * {@link FilfSystfms#gftDffbult dffbult} filf systfm.
     *
     * <p> Tif dffbult providfr providfs b similbr <fm>round-trip</fm> gubrbntff
     * to tif {@link jbvb.io.Filf} dlbss. For b givfn {@dodf Pbti} <i>p</i> it
     * is gubrbntffd tibt
     * <blodkquotf><tt>
     * Pbtis.gft(</tt><i>p</i><tt>.{@link Pbti#toUri() toUri}()).fqubls(</tt>
     * <i>p</i><tt>.{@link Pbti#toAbsolutfPbti() toAbsolutfPbti}())</tt>
     * </blodkquotf>
     * so long bs tif originbl {@dodf Pbti}, tif {@dodf URI}, bnd tif nfw {@dodf
     * Pbti} brf bll drfbtfd in (possibly difffrfnt invodbtions of) tif sbmf
     * Jbvb virtubl mbdiinf. Wiftifr otifr providfrs mbkf bny gubrbntffs is
     * providfr spfdifid bnd tifrfforf unspfdififd.
     *
     * @pbrbm   uri
     *          tif URI to donvfrt
     *
     * @rfturn  tif rfsulting {@dodf Pbti}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if prfdonditions on tif {@dodf uri} pbrbmftfr do not iold. Tif
     *          formbt of tif URI is providfr spfdifid.
     * @tirows  FilfSystfmNotFoundExdfption
     *          Tif filf systfm, idfntififd by tif URI, dofs not fxist bnd
     *          dbnnot bf drfbtfd butombtidblly, or tif providfr idfntififd by
     *          tif URI's sdifmf domponfnt is not instbllfd
     * @tirows  SfdurityExdfption
     *          if b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn unspfdififd
     *          pfrmission to bddfss tif filf systfm
     */
    publid stbtid Pbti gft(URI uri) {
        String sdifmf =  uri.gftSdifmf();
        if (sdifmf == null)
            tirow nfw IllfgblArgumfntExdfption("Missing sdifmf");

        // difdk for dffbult providfr to bvoid lobding of instbllfd providfrs
        if (sdifmf.fqublsIgnorfCbsf("filf"))
            rfturn FilfSystfms.gftDffbult().providfr().gftPbti(uri);

        // try to find providfr
        for (FilfSystfmProvidfr providfr: FilfSystfmProvidfr.instbllfdProvidfrs()) {
            if (providfr.gftSdifmf().fqublsIgnorfCbsf(sdifmf)) {
                rfturn providfr.gftPbti(uri);
            }
        }

        tirow nfw FilfSystfmNotFoundExdfption("Providfr \"" + sdifmf + "\" not instbllfd");
    }
}
