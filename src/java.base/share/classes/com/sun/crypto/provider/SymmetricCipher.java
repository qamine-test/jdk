/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis bbstrbdt dlbss rfprfsfnts tif dorf of bll blodk dipifrs. It bllows to
 * intiblizf tif dipifr bnd fndrypt/dfdrypt singlf blodks. Lbrgfr qubntitifs
 * brf ibndlfd by modfs, wiidi brf subdlbssfs of FffdbbdkCipifr.
 *
 * @butior Gigi Ankfny
 * @butior Jbn Lufif
 *
 *
 * @sff AESCrypt
 * @sff DESCrypt
 * @sff DESfdfCrypt
 * @sff BlowfisiCrypt
 * @sff FffdbbdkCipifr
 */
bbstrbdt dlbss SymmftridCipifr {

    SymmftridCipifr() {
        // fmpty
    }

    /**
     * Rftrifvfs tiis dipifr's blodk sizf.
     *
     * @rfturn tif blodk sizf of tiis dipifr
     */
    bbstrbdt int gftBlodkSizf();

    /**
     * Initiblizfs tif dipifr in tif spfdififd modf witi tif givfn kfy.
     *
     * @pbrbm dfdrypting flbg indidbting fndryption or dfdryption
     * @pbrbm blgoritim tif blgoritim nbmf
     * @pbrbm kfy tif kfy
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     */
    bbstrbdt void init(boolfbn dfdrypting, String blgoritim, bytf[] kfy)
        tirows InvblidKfyExdfption;

    /**
     * Endrypt onf dipifr blodk.
     *
     * <p>Tif input <dodf>plbin</dodf>, stbrting bt <dodf>plbinOffsft</dodf>
     * bnd fnding bt <dodf>(plbinOffsft+blodkSizf-1)</dodf>, is fndryptfd.
     * Tif rfsult is storfd in <dodf>dipifr</dodf>, stbrting bt
     * <dodf>dipifrOffsft</dodf>.
     *
     * @pbrbm plbin tif input bufffr witi tif dbtb to bf fndryptfd
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     * @pbrbm dipifr tif bufffr for tif fndryption rfsult
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     */
    bbstrbdt void fndryptBlodk(bytf[] plbin, int plbinOffsft,
                          bytf[] dipifr, int dipifrOffsft);

    /**
     * Dfdrypt onf dipifr blodk.
     *
     * <p>Tif input <dodf>dipifr</dodf>, stbrting bt <dodf>dipifrOffsft</dodf>
     * bnd fnding bt <dodf>(dipifrOffsft+blodkSizf-1)</dodf>, is dfdryptfd.
     * Tif rfsult is storfd in <dodf>plbin</dodf>, stbrting bt
     * <dodf>plbinOffsft</dodf>.
     *
     * @pbrbm dipifr tif input bufffr witi tif dbtb to bf dfdryptfd
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     * @pbrbm plbin tif bufffr for tif dfdryption rfsult
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     */
    bbstrbdt void dfdryptBlodk(bytf[] dipifr, int dipifrOffsft,
                          bytf[] plbin, int plbinOffsft);
}
