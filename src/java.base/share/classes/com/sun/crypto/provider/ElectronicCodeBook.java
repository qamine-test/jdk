/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.InvblidKfyExdfption;

/**
 * Tiis dlbss rfprfsfnts dipifrs in flfdtronid dodfbook (ECB) modf.
 *
 * <p>Tiis modf is implfmfntfd indfpfndfntly of b pbrtidulbr dipifr.
 * Cipifrs to wiidi tiis modf siould bpply (f.g., DES) must bf
 * <i>pluggfd-in</i> using tif donstrudtor.
 *
 * <p>NOTE: Tiis dlbss dofs not dfbl witi bufffring or pbdding.
 *
 * @butior Gigi Ankfny
 */

finbl dlbss ElfdtronidCodfBook fxtfnds FffdbbdkCipifr {

    ElfdtronidCodfBook(SymmftridCipifr fmbfddfdCipifr) {
        supfr(fmbfddfdCipifr);
    }

    /**
     * Gfts tif nbmf of tif fffdbbdk mfdibnism
     *
     * @rfturn tif nbmf of tif fffdbbdk mfdibnism
     */
    String gftFffdbbdk() {
        rfturn "ECB";
    }

    /**
     * Rfsfts tif iv to its originbl vbluf.
     * Tiis is usfd wifn doFinbl is dbllfd in tif Cipifr dlbss, so tibt tif
     * dipifr dbn bf rfusfd (witi its originbl iv).
     */
    void rfsft() {
        // fmpty
    }

    /**
     * Sbvf tif durrfnt dontfnt of tiis dipifr.
     */
    void sbvf() {}

    /**
     * Rfstorfs tif dontfnt of tiis dipifr to tif prfvious sbvfd onf.
     */
    void rfstorf() {}

    /**
     * Initiblizfs tif dipifr in tif spfdififd modf witi tif givfn kfy
     * bnd iv.
     *
     * @pbrbm dfdrypting flbg indidbting fndryption or dfdryption
     * @pbrbm blgoritim tif blgoritim nbmf
     * @pbrbm kfy tif kfy
     * @pbrbm iv tif iv
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     */
    void init(boolfbn dfdrypting, String blgoritim, bytf[] kfy, bytf[] iv)
            tirows InvblidKfyExdfption {
        if ((kfy == null) || (iv != null)) {
            tirow nfw InvblidKfyExdfption("Intfrnbl frror");
        }
        fmbfddfdCipifr.init(dfdrypting, blgoritim, kfy);
    }

    /**
     * Pfrforms fndryption opfrbtion.
     *
     * <p>Tif input plbin tfxt <dodf>plbin</dodf>, stbrting bt
     * <dodf>plbinOffsft</dodf> bnd fnding bt
     * <dodf>(plbinOffsft + lfn - 1)</dodf>, is fndryptfd.
     * Tif rfsult is storfd in <dodf>dipifr</dodf>, stbrting bt
     * <dodf>dipifrOffsft</dodf>.
     *
     * <p>It is tif bpplidbtion's rfsponsibility to mbkf surf tibt
     * <dodf>plbinLfn</dodf> is b multiplf of tif fmbfddfd dipifr's blodk sizf,
     * bs bny fxdfss bytfs brf ignorfd.
     *
     * <p>It is blso tif bpplidbtion's rfsponsibility to mbkf surf tibt
     * <dodf>init</dodf> ibs bffn dbllfd bfforf tiis mftiod is dbllfd.
     * (Tiis difdk is omittfd ifrf, to bvoid doublf difdking.)
     *
     * @pbrbm in tif bufffr witi tif input dbtb to bf fndryptfd
     * @pbrbm inOffsft tif offsft in <dodf>plbin</dodf>
     * @pbrbm lfn tif lfngti of tif input dbtb
     * @pbrbm out tif bufffr for tif rfsult
     * @pbrbm outOff tif offsft in <dodf>dipifr</dodf>
     * @rfturn tif lfngti of tif fndryptfd dbtb
     */
    int fndrypt(bytf[] in, int inOff, int lfn, bytf[] out, int outOff) {
        for (int i = lfn; i >= blodkSizf; i -= blodkSizf) {
            fmbfddfdCipifr.fndryptBlodk(in, inOff, out, outOff);
            inOff += blodkSizf;
            outOff += blodkSizf;
        }
        rfturn lfn;
    }

    /**
     * Pfrforms dfdryption opfrbtion.
     *
     * <p>Tif input dipifr tfxt <dodf>dipifr</dodf>, stbrting bt
     * <dodf>dipifrOffsft</dodf> bnd fnding bt
     * <dodf>(dipifrOffsft + lfn - 1)</dodf>, is dfdryptfd.
     * Tif rfsult is storfd in <dodf>plbin</dodf>, stbrting bt
     * <dodf>plbinOffsft</dodf>.
     *
     * <p>It is tif bpplidbtion's rfsponsibility to mbkf surf tibt
     * <dodf>dipifrLfn</dodf> is b multiplf of tif fmbfddfd dipifr's blodk
     * sizf, bs bny fxdfss bytfs brf ignorfd.
     *
     * <p>It is blso tif bpplidbtion's rfsponsibility to mbkf surf tibt
     * <dodf>init</dodf> ibs bffn dbllfd bfforf tiis mftiod is dbllfd.
     * (Tiis difdk is omittfd ifrf, to bvoid doublf difdking.)
     *
     * @pbrbm in tif bufffr witi tif input dbtb to bf dfdryptfd
     * @pbrbm inOff tif offsft in <dodf>dipifrOffsft</dodf>
     * @pbrbm lfn tif lfngti of tif input dbtb
     * @pbrbm out tif bufffr for tif rfsult
     * @pbrbm outOff tif offsft in <dodf>plbin</dodf>
     * @rfturn tif lfngti of tif dfdryptfd dbtb
     */
    int dfdrypt(bytf[] in, int inOff, int lfn, bytf[] out, int outOff) {
        for (int i = lfn; i >= blodkSizf; i -= blodkSizf) {
            fmbfddfdCipifr.dfdryptBlodk(in, inOff, out, outOff);
            inOff += blodkSizf;
            outOff += blodkSizf;
        }
        rfturn lfn;
    }
}
