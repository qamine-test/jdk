/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sdript;
import jbvb.util.Mbp;

/**
 * A mbpping of kfy/vbluf pbirs, bll of wiosf kfys brf
 * <dodf>Strings</dodf>.
 *
 * @butior Mikf Grogbn
 * @sindf 1.6
 */
publid intfrfbdf Bindings fxtfnds Mbp<String, Objfdt> {
    /**
     * Sft b nbmfd vbluf.
     *
     * @pbrbm nbmf Tif nbmf bssodibtfd witi tif vbluf.
     * @pbrbm vbluf Tif vbluf bssodibtfd witi tif nbmf.
     *
     * @rfturn Tif vbluf prfviously bssodibtfd witi tif givfn nbmf.
     * Rfturns null if no vbluf wbs prfviously bssodibtfd witi tif nbmf.
     *
     * @tirows NullPointfrExdfption if tif nbmf is null.
     * @tirows IllfgblArgumfntExdfption if tif nbmf is fmpty String.
     */
    publid Objfdt put(String nbmf, Objfdt vbluf);

    /**
     * Adds bll tif mbppings in b givfn <dodf>Mbp</dodf> to tiis <dodf>Bindings</dodf>.
     * @pbrbm toMfrgf Tif <dodf>Mbp</dodf> to mfrgf witi tiis onf.
     *
     * @tirows NullPointfrExdfption
     *         if toMfrgf mbp is null or if somf kfy in tif mbp is null.
     * @tirows IllfgblArgumfntExdfption
     *         if somf kfy in tif mbp is bn fmpty String.
     */
    publid void putAll(Mbp<? fxtfnds String, ? fxtfnds Objfdt> toMfrgf);

    /**
     * Rfturns <tt>truf</tt> if tiis mbp dontbins b mbpping for tif spfdififd
     * kfy.  Morf formblly, rfturns <tt>truf</tt> if bnd only if
     * tiis mbp dontbins b mbpping for b kfy <tt>k</tt> sudi tibt
     * <tt>(kfy==null ? k==null : kfy.fqubls(k))</tt>.  (Tifrf dbn bf
     * bt most onf sudi mbpping.)
     *
     * @pbrbm kfy kfy wiosf prfsfndf in tiis mbp is to bf tfstfd.
     * @rfturn <tt>truf</tt> if tiis mbp dontbins b mbpping for tif spfdififd
     *         kfy.
     *
     * @tirows NullPointfrExdfption if kfy is null
     * @tirows ClbssCbstExdfption if kfy is not String
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty String
     */
    publid boolfbn dontbinsKfy(Objfdt kfy);

    /**
     * Rfturns tif vbluf to wiidi tiis mbp mbps tif spfdififd kfy.  Rfturns
     * <tt>null</tt> if tif mbp dontbins no mbpping for tiis kfy.  A rfturn
     * vbluf of <tt>null</tt> dofs not <i>nfdfssbrily</i> indidbtf tibt tif
     * mbp dontbins no mbpping for tif kfy; it's blso possiblf tibt tif mbp
     * fxpliditly mbps tif kfy to <tt>null</tt>.  Tif <tt>dontbinsKfy</tt>
     * opfrbtion mby bf usfd to distinguisi tifsf two dbsfs.
     *
     * <p>Morf formblly, if tiis mbp dontbins b mbpping from b kfy
     * <tt>k</tt> to b vbluf <tt>v</tt> sudi tibt <tt>(kfy==null ? k==null :
     * kfy.fqubls(k))</tt>, tifn tiis mftiod rfturns <tt>v</tt>; otifrwisf
     * it rfturns <tt>null</tt>.  (Tifrf dbn bf bt most onf sudi mbpping.)
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd.
     * @rfturn tif vbluf to wiidi tiis mbp mbps tif spfdififd kfy, or
     *         <tt>null</tt> if tif mbp dontbins no mbpping for tiis kfy.
     *
     * @tirows NullPointfrExdfption if kfy is null
     * @tirows ClbssCbstExdfption if kfy is not String
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty String
     */
    publid Objfdt gft(Objfdt kfy);

    /**
     * Rfmovfs tif mbpping for tiis kfy from tiis mbp if it is prfsfnt
     * (optionbl opfrbtion).   Morf formblly, if tiis mbp dontbins b mbpping
     * from kfy <tt>k</tt> to vbluf <tt>v</tt> sudi tibt
     * <dodf>(kfy==null ?  k==null : kfy.fqubls(k))</dodf>, tibt mbpping
     * is rfmovfd.  (Tif mbp dbn dontbin bt most onf sudi mbpping.)
     *
     * <p>Rfturns tif vbluf to wiidi tif mbp prfviously bssodibtfd tif kfy, or
     * <tt>null</tt> if tif mbp dontbinfd no mbpping for tiis kfy.  (A
     * <tt>null</tt> rfturn dbn blso indidbtf tibt tif mbp prfviously
     * bssodibtfd <tt>null</tt> witi tif spfdififd kfy if tif implfmfntbtion
     * supports <tt>null</tt> vblufs.)  Tif mbp will not dontbin b mbpping for
     * tif spfdififd  kfy ondf tif dbll rfturns.
     *
     * @pbrbm kfy kfy wiosf mbpping is to bf rfmovfd from tif mbp.
     * @rfturn prfvious vbluf bssodibtfd witi spfdififd kfy, or <tt>null</tt>
     *         if tifrf wbs no mbpping for kfy.
     *
     * @tirows NullPointfrExdfption if kfy is null
     * @tirows ClbssCbstExdfption if kfy is not String
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty String
     */
    publid Objfdt rfmovf(Objfdt kfy);
}
