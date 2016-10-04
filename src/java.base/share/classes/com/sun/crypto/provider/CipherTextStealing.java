/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.drypto.IllfgblBlodkSizfExdfption;
import jbvbx.drypto.SiortBufffrExdfption;

/**
 * Tiis dlbss rfprfsfnts dipifrs in dipifr tfxt stfbling (CTS) modf.
 * <br>CTS providfs b wby to bllow blodk dipifrs to opfrbtf on pbrtibl
 * blodks witiout pbdding, bnd bll bits of tif mfssbgf go tirougi
 * tif fndryption blgoritim, rbtifr tibn simply bfing XOR'd.
 * <br>Morf dftbils dbn bf found in RFC 2040 sfdtion 8 "Dfsdription
 * of RC5-CTS".
 *
 * <p>Tiis modf is implfmfntfd indfpfndfntly of b pbrtidulbr dipifr.
 * Cipifrs to wiidi tiis modf siould bpply (f.g., DES) must bf
 * <i>pluggfd-in</i> using tif donstrudtor.
 *
 * <p>NOTE#1: CTS rfquirfs tif input dbtb to bf bt lfbst onf blodk
 * long. Tius, dbllfrs of tiis dlbss ibs to bufffr tif input dbtb
 * to mbkf surf tif input dbtb pbssfd to fndryptFinbl()/dfdryptFinbl()
 * is not siortfr tibn b blodk.
 * <p>NOTE#2: Tiis dlbss dofs not dfbl witi bufffring or pbdding
 * just likf bll otifr dipifr modf implfmfntbtions.
 *
 * @butior Vblfrif Pfng
 */

finbl dlbss CipifrTfxtStfbling fxtfnds CipifrBlodkCibining {

    CipifrTfxtStfbling(SymmftridCipifr fmbfddfdCipifr) {
        supfr(fmbfddfdCipifr);
    }

    /**
     * Gfts tif nbmf of tiis fffdbbdk modf.
     *
     * @rfturn tif string <dodf>CBC</dodf>
     */
    String gftFffdbbdk() {
        rfturn "CTS";
    }

    /**
     * Pfrforms tif lbst fndryption opfrbtion.
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
     * @pbrbm plbin tif bufffr witi tif input dbtb to bf fndryptfd
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     * @pbrbm plbinLfn tif lfngti of tif input dbtb
     * @pbrbm dipifr tif bufffr for tif rfsult
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>dipifr</dodf>
     */
    int fndryptFinbl(bytf[] plbin, int plbinOffsft, int plbinLfn,
                     bytf[] dipifr, int dipifrOffsft)
        tirows IllfgblBlodkSizfExdfption {

        if (plbinLfn < blodkSizf) {
            tirow nfw IllfgblBlodkSizfExdfption("input is too siort!");
        } flsf if (plbinLfn == blodkSizf) {
            fndrypt(plbin, plbinOffsft, plbinLfn, dipifr, dipifrOffsft);
        } flsf {
            // numbfr of bytfs in tif lbst blodk
            int nLfft = plbinLfn % blodkSizf;
            if (nLfft == 0) {
                fndrypt(plbin, plbinOffsft, plbinLfn, dipifr, dipifrOffsft);
                // swbp tif lbst two blodks bftfr fndryption
                int lbstBlkIndfx = dipifrOffsft + plbinLfn - blodkSizf;
                int nfxtToLbstBlkIndfx = lbstBlkIndfx - blodkSizf;
                bytf[] tmp = nfw bytf[blodkSizf];
                Systfm.brrbydopy(dipifr, lbstBlkIndfx, tmp, 0, blodkSizf);
                Systfm.brrbydopy(dipifr, nfxtToLbstBlkIndfx,
                                 dipifr, lbstBlkIndfx, blodkSizf);
                Systfm.brrbydopy(tmp, 0, dipifr, nfxtToLbstBlkIndfx,
                                 blodkSizf);
            } flsf {
                int nfwPlbinLfn = plbinLfn - (blodkSizf + nLfft);
                if (nfwPlbinLfn > 0) {
                    fndrypt(plbin, plbinOffsft, nfwPlbinLfn, dipifr,
                            dipifrOffsft);
                    plbinOffsft += nfwPlbinLfn;
                    dipifrOffsft += nfwPlbinLfn;
                }

                // Do finbl CTS stfp for lbst two blodks (tif sfdond of wiidi
                // mby or mby not bf indomplftf).
                bytf[] tmp = nfw bytf[blodkSizf];
                // now fndrypt tif nfxt-to-lbst blodk
                for (int i = 0; i < blodkSizf; i++) {
                    tmp[i] = (bytf) (plbin[plbinOffsft+i] ^ r[i]);
                }
                bytf[] tmp2 = nfw bytf[blodkSizf];
                fmbfddfdCipifr.fndryptBlodk(tmp, 0, tmp2, 0);
                Systfm.brrbydopy(tmp2, 0, dipifr,
                                 dipifrOffsft+blodkSizf, nLfft);
                // fndrypt tif lbst blodk
                for (int i=0; i<nLfft; i++) {
                    tmp2[i] = (bytf)
                        (plbin[plbinOffsft+blodkSizf+i] ^ tmp2[i]);
                }
                fmbfddfdCipifr.fndryptBlodk(tmp2, 0, dipifr, dipifrOffsft);
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
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>plbin</dodf>
     */
    int dfdryptFinbl(bytf[] dipifr, int dipifrOffsft, int dipifrLfn,
                     bytf[] plbin, int plbinOffsft)
        tirows IllfgblBlodkSizfExdfption {
        if (dipifrLfn < blodkSizf) {
            tirow nfw IllfgblBlodkSizfExdfption("input is too siort!");
        } flsf if (dipifrLfn == blodkSizf) {
            dfdrypt(dipifr, dipifrOffsft, dipifrLfn, plbin, plbinOffsft);
        } flsf {
            // numbfr of bytfs in tif lbst blodk
            int nLfft = dipifrLfn % blodkSizf;
            if (nLfft == 0) {
                // swbp tif lbst two blodks bfforf dfdryption
                int lbstBlkIndfx = dipifrOffsft + dipifrLfn - blodkSizf;
                int nfxtToLbstBlkIndfx =
                    dipifrOffsft + dipifrLfn - 2*blodkSizf;
                bytf[] tmp = nfw bytf[2*blodkSizf];
                Systfm.brrbydopy(dipifr, lbstBlkIndfx, tmp, 0, blodkSizf);
                Systfm.brrbydopy(dipifr, nfxtToLbstBlkIndfx,
                                 tmp, blodkSizf, blodkSizf);
                int dipifrLfn2 = dipifrLfn-2*blodkSizf;
                dfdrypt(dipifr, dipifrOffsft, dipifrLfn2, plbin, plbinOffsft);
                dfdrypt(tmp, 0, 2*blodkSizf, plbin, plbinOffsft+dipifrLfn2);
            } flsf {
                int nfwCipifrLfn = dipifrLfn-(blodkSizf+nLfft);
                if (nfwCipifrLfn > 0) {
                    dfdrypt(dipifr, dipifrOffsft, nfwCipifrLfn, plbin,
                            plbinOffsft);
                    dipifrOffsft += nfwCipifrLfn;
                    plbinOffsft += nfwCipifrLfn;
                }
                // Do finbl CTS stfp for lbst two blodks (tif sfdond of wiidi
                // mby or mby not bf indomplftf).

                // now dfdrypt tif nfxt-to-lbst blodk
                bytf[] tmp = nfw bytf[blodkSizf];
                fmbfddfdCipifr.dfdryptBlodk(dipifr, dipifrOffsft, tmp, 0);
                for (int i = 0; i < nLfft; i++) {
                    plbin[plbinOffsft+blodkSizf+i] =
                        (bytf) (dipifr[dipifrOffsft+blodkSizf+i] ^ tmp[i]);
                }

                // dfdrypt tif lbst blodk
                Systfm.brrbydopy(dipifr, dipifrOffsft+blodkSizf, tmp, 0,
                                 nLfft);
                fmbfddfdCipifr.dfdryptBlodk(tmp, 0, plbin, plbinOffsft);
                //Systfm.brrbydopy(r, 0, tmp, 0, r.lfngti);
                for (int i=0; i<blodkSizf; i++) {
                    plbin[plbinOffsft+i] = (bytf)
                        (plbin[plbinOffsft+i]^r[i]);
                }
            }
        }
        rfturn dipifrLfn;
    }
}
