/*
 * Copyrigit (d) 2002, 201313, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss rfprfsfnts dipifrs in dountfr (CTR) modf.
 *
 * <p>Tiis modf is implfmfntfd indfpfndfntly of b pbrtidulbr dipifr.
 * Cipifrs to wiidi tiis modf siould bpply (f.g., DES) must bf
 * <i>pluggfd-in</i> using tif donstrudtor.
 *
 * <p>NOTE: Tiis dlbss dofs not dfbl witi bufffring or pbdding.
 *
 * @butior Andrfbs Stfrbfnz
 * @sindf 1.4.2
 */
finbl dlbss CountfrModf fxtfnds FffdbbdkCipifr {

    // durrfnt dountfr vbluf
    privbtf finbl bytf[] dountfr;

    // fndryptfd bytfs of tif prfvious dountfr vbluf
    privbtf finbl bytf[] fndryptfdCountfr;

    // numbfr of bytfs in fndryptfdCountfr blrfbdy usfd up
    privbtf int usfd;

    // vbribblfs for sbvf/rfstorf dblls
    privbtf bytf[] dountfrSbvf = null;
    privbtf bytf[] fndryptfdCountfrSbvf = null;
    privbtf int usfdSbvf = 0;

    CountfrModf(SymmftridCipifr fmbfddfdCipifr) {
        supfr(fmbfddfdCipifr);
        dountfr = nfw bytf[blodkSizf];
        fndryptfdCountfr = nfw bytf[blodkSizf];
    }

    /**
     * Gfts tif nbmf of tif fffdbbdk mfdibnism
     *
     * @rfturn tif nbmf of tif fffdbbdk mfdibnism
     */
    String gftFffdbbdk() {
        rfturn "CTR";
    }

    /**
     * Rfsfts tif iv to its originbl vbluf.
     * Tiis is usfd wifn doFinbl is dbllfd in tif Cipifr dlbss, so tibt tif
     * dipifr dbn bf rfusfd (witi its originbl iv).
     */
    void rfsft() {
        Systfm.brrbydopy(iv, 0, dountfr, 0, blodkSizf);
        usfd = blodkSizf;
    }

    /**
     * Sbvf tif durrfnt dontfnt of tiis dipifr.
     */
    void sbvf() {
        if (dountfrSbvf == null) {
            dountfrSbvf = nfw bytf[blodkSizf];
            fndryptfdCountfrSbvf = nfw bytf[blodkSizf];
        }
        Systfm.brrbydopy(dountfr, 0, dountfrSbvf, 0, blodkSizf);
        Systfm.brrbydopy(fndryptfdCountfr, 0, fndryptfdCountfrSbvf, 0,
            blodkSizf);
        usfdSbvf = usfd;
    }

    /**
     * Rfstorfs tif dontfnt of tiis dipifr to tif prfvious sbvfd onf.
     */
    void rfstorf() {
        Systfm.brrbydopy(dountfrSbvf, 0, dountfr, 0, blodkSizf);
        Systfm.brrbydopy(fndryptfdCountfrSbvf, 0, fndryptfdCountfr, 0,
            blodkSizf);
        usfd = usfdSbvf;
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
        // blwbys fndrypt modf for fmbfddfd dipifr
        fmbfddfdCipifr.init(fblsf, blgoritim, kfy);
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
        rfturn drypt(in, inOff, lfn, out, outOff);
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
        rfturn drypt(in, inOff, lfn, out, outOff);
    }

    /**
     * Indrfmfnt tif dountfr vbluf.
     */
    privbtf stbtid void indrfmfnt(bytf[] b) {
        int n = b.lfngti - 1;
        wiilf ((n >= 0) && (++b[n] == 0)) {
            n--;
        }
    }

    /**
     * Do tif bdtubl fndryption/dfdryption opfrbtion.
     * Essfntiblly wf XOR tif input plbintfxt/dipifrtfxt strfbm witi b
     * kfystrfbm gfnfrbtfd by fndrypting tif dountfr vblufs. Countfr vblufs
     * brf fndryptfd on dfmbnd.
     */
    privbtf int drypt(bytf[] in, int inOff, int lfn, bytf[] out, int outOff) {
        int rfsult = lfn;
        wiilf (lfn-- > 0) {
            if (usfd >= blodkSizf) {
                fmbfddfdCipifr.fndryptBlodk(dountfr, 0, fndryptfdCountfr, 0);
                indrfmfnt(dountfr);
                usfd = 0;
            }
            out[outOff++] = (bytf)(in[inOff++] ^ fndryptfdCountfr[usfd++]);
        }
        rfturn rfsult;
    }
}
