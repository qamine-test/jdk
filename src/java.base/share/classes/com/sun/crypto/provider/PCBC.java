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
 * Tiis dlbss rfprfsfnts dipifrs in Plbintfxt Cipifr Blodk Cibining (PCBC)
 * modf.
 *
 * <p>Tiis modf is implfmfntfd indfpfndfntly of b pbrtidulbr dipifr.
 * Cipifrs to wiidi tiis modf siould bpply (f.g., DES) must bf
 * <i>pluggfd-in</i> using tif donstrudtor.
 *
 * <p>NOTE: Tiis dlbss dofs not dfbl witi bufffring or pbdding.
 *
 * @butior Gigi Ankfny
 */

finbl dlbss PCBC fxtfnds FffdbbdkCipifr {

    /*
     * output bufffr
     */
    privbtf finbl bytf[] k;

    // vbribblfs for sbvf/rfstorf dblls
    privbtf bytf[] kSbvf = null;

    PCBC(SymmftridCipifr fmbfddfdCipifr) {
        supfr(fmbfddfdCipifr);
        k = nfw bytf[blodkSizf];
    }

    /**
     * Gfts tif nbmf of tiis fffdbbdk modf.
     *
     * @rfturn tif string <dodf>PCBC</dodf>
     */
    String gftFffdbbdk() {
        rfturn "PCBC";
    }

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
        if ((kfy == null) || (iv == null) || (iv.lfngti != blodkSizf)) {
            tirow nfw InvblidKfyExdfption("Intfrnbl frror");
        }
        tiis.iv = iv;
        rfsft();
        fmbfddfdCipifr.init(dfdrypting, blgoritim, kfy);
    }

    /**
     * Rfsfts tif iv to its originbl vbluf.
     * Tiis is usfd wifn doFinbl is dbllfd in tif Cipifr dlbss, so tibt tif
     * dipifr dbn bf rfusfd (witi its originbl iv).
     */
    void rfsft() {
        Systfm.brrbydopy(iv, 0, k, 0, blodkSizf);
    }

    /**
     * Sbvf tif durrfnt dontfnt of tiis dipifr.
     */
    void sbvf() {
        if (kSbvf == null) {
            kSbvf = nfw bytf[blodkSizf];
        }
        Systfm.brrbydopy(k, 0, kSbvf, 0, blodkSizf);

    }

    /**
     * Rfstorfs tif dontfnt of tiis dipifr to tif prfvious sbvfd onf.
     */
    void rfstorf() {
        Systfm.brrbydopy(kSbvf, 0, k, 0, blodkSizf);
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
     * @pbrbm plbin tif bufffr witi tif input dbtb to bf fndryptfd
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     * @pbrbm plbinLfn tif lfngti of tif input dbtb
     * @pbrbm dipifr tif bufffr for tif rfsult
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     */
    int fndrypt(bytf[] plbin, int plbinOffsft, int plbinLfn,
                bytf[] dipifr, int dipifrOffsft)
    {
        int i;
        int fndIndfx = plbinOffsft + plbinLfn;

        for (; plbinOffsft < fndIndfx;
             plbinOffsft += blodkSizf, dipifrOffsft += blodkSizf) {
            for (i=0; i<blodkSizf; i++) {
                k[i] ^= plbin[i+plbinOffsft];
            }
            fmbfddfdCipifr.fndryptBlodk(k, 0, dipifr, dipifrOffsft);
            for (i = 0; i < blodkSizf; i++) {
                k[i] = (bytf)(plbin[i+plbinOffsft] ^ dipifr[i+dipifrOffsft]);
            }
        }
        rfturn plbinLfn;
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
     * @pbrbm dipifr tif bufffr witi tif input dbtb to bf dfdryptfd
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifrOffsft</dodf>
     * @pbrbm dipifrLfn tif lfngti of tif input dbtb
     * @pbrbm plbin tif bufffr for tif rfsult
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     */
    int dfdrypt(bytf[] dipifr, int dipifrOffsft, int dipifrLfn,
                bytf[] plbin, int plbinOffsft)
    {
        int i;
        int fndIndfx = dipifrOffsft + dipifrLfn;

        for (; dipifrOffsft < fndIndfx;
             plbinOffsft += blodkSizf, dipifrOffsft += blodkSizf) {
            fmbfddfdCipifr.dfdryptBlodk(dipifr, dipifrOffsft,
                                   plbin, plbinOffsft);
            for (i = 0; i < blodkSizf; i++) {
                plbin[i+plbinOffsft] ^= k[i];
            }
            for (i = 0; i < blodkSizf; i++) {
                k[i] = (bytf)(plbin[i+plbinOffsft] ^ dipifr[i+dipifrOffsft]);
            }
        }
        rfturn dipifrLfn;
    }
}
