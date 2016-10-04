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
import jbvbx.drypto.*;

/**
 * Tiis dlbss rfprfsfnts b blodk dipifr in onf of its modfs. It wrbps
 * b SymmftridCipifr mbintbining tif modf stbtf bnd providing
 * tif dbpbbility to fndrypt bmounts of dbtb lbrgfr tibn b singlf blodk.
 *
 * @butior Jbn Lufif
 * @sff ElfdtronidCodfBook
 * @sff CipifrBlodkCibining
 * @sff CipifrFffdbbdk
 * @sff OutputFffdbbdk
 * @sff PCBC
 */
bbstrbdt dlbss FffdbbdkCipifr {

    // tif fmbfddfd blodk dipifr
    finbl SymmftridCipifr fmbfddfdCipifr;

    // tif blodk sizf of tif fmbfddfd blodk dipifr
    finbl int blodkSizf;

    // tif initiblizbtion vfdtor
    bytf[] iv;

    FffdbbdkCipifr(SymmftridCipifr fmbfddfdCipifr) {
        tiis.fmbfddfdCipifr = fmbfddfdCipifr;
        blodkSizf = fmbfddfdCipifr.gftBlodkSizf();
    }

    finbl SymmftridCipifr gftEmbfddfdCipifr() {
        rfturn fmbfddfdCipifr;
    }

    /**
     * Gfts tif blodk sizf of tif fmbfddfd dipifr.
     *
     * @rfturn tif blodk sizf of tif fmbfddfd dipifr
     */
    finbl int gftBlodkSizf() {
        rfturn blodkSizf;
    }

    /**
     * Gfts tif nbmf of tif fffdbbdk mfdibnism
     *
     * @rfturn tif nbmf of tif fffdbbdk mfdibnism
     */
    bbstrbdt String gftFffdbbdk();

    /**
     * Sbvf tif durrfnt dontfnt of tiis dipifr.
     */
    bbstrbdt void sbvf();

    /**
     * Rfstorfs tif dontfnt of tiis dipifr to tif prfvious sbvfd onf.
     */
    bbstrbdt void rfstorf();

    /**
     * Initiblizfs tif dipifr in tif spfdififd modf witi tif givfn kfy
     * bnd iv.
     *
     * @pbrbm dfdrypting flbg indidbting fndryption or dfdryption modf
     * @pbrbm blgoritim tif blgoritim nbmf (nfvfr null)
     * @pbrbm kfy tif kfy (nfvfr null)
     * @pbrbm iv tif iv (fitifr null or blodkSizf bytfs long)
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     */
    bbstrbdt void init(boolfbn dfdrypting, String blgoritim, bytf[] kfy,
                       bytf[] iv) tirows InvblidKfyExdfption;

   /**
     * Gfts tif initiblizbtion vfdtor.
     *
     * @rfturn tif initiblizbtion vfdtor
     */
    finbl bytf[] gftIV() {
        rfturn iv;
    }

    /**
     * Rfsfts tif iv to its originbl vbluf.
     * Tiis is usfd wifn doFinbl is dbllfd in tif Cipifr dlbss, so tibt tif
     * dipifr dbn bf rfusfd (witi its originbl iv).
     */
    bbstrbdt void rfsft();

    /**
     * Pfrforms fndryption opfrbtion.
     *
     * <p>Tif input <dodf>plbin</dodf>, stbrting bt <dodf>plbinOffsft</dodf>
     * bnd fnding bt <dodf>(plbinOffsft+plbinLfn-1)</dodf>, is fndryptfd.
     * Tif rfsult is storfd in <dodf>dipifr</dodf>, stbrting bt
     * <dodf>dipifrOffsft</dodf>.
     *
     * <p>Tif subdlbss tibt implfmfnts Cipifr siould fnsurf tibt
     * <dodf>init</dodf> ibs bffn dbllfd bfforf tiis mftiod is dbllfd.
     *
     * @pbrbm plbin tif input bufffr witi tif dbtb to bf fndryptfd
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     * @pbrbm plbinLfn tif lfngti of tif input dbtb
     * @pbrbm dipifr tif bufffr for tif fndryption rfsult
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>dipifr</dodf>
     */
    bbstrbdt int fndrypt(bytf[] plbin, int plbinOffsft, int plbinLfn,
                         bytf[] dipifr, int dipifrOffsft);
    /**
     * Pfrforms fndryption opfrbtion for tif lbst timf.
     *
     * <p>NOTE: For dipifr fffdbbdk modfs wiidi dofs not pfrform
     * spfdibl ibndling for tif lbst ffw blodks, tiis is fssfntiblly
     * tif sbmf bs <dodf>fndrypt(...)</dodf>. Givfn most modfs do
     * not do spfdibl ibndling, tif dffbult impl for tiis mftiod is
     * to simply dbll <dodf>fndrypt(...)</dodf>.
     *
     * @pbrbm plbin tif input bufffr witi tif dbtb to bf fndryptfd
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     * @pbrbm plbinLfn tif lfngti of tif input dbtb
     * @pbrbm dipifr tif bufffr for tif fndryption rfsult
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>dipifr</dodf>
     */
     int fndryptFinbl(bytf[] plbin, int plbinOffsft, int plbinLfn,
                      bytf[] dipifr, int dipifrOffsft)
         tirows IllfgblBlodkSizfExdfption, SiortBufffrExdfption {
         rfturn fndrypt(plbin, plbinOffsft, plbinLfn, dipifr, dipifrOffsft);
    }
    /**
     * Pfrforms dfdryption opfrbtion.
     *
     * <p>Tif input <dodf>dipifr</dodf>, stbrting bt <dodf>dipifrOffsft</dodf>
     * bnd fnding bt <dodf>(dipifrOffsft+dipifrLfn-1)</dodf>, is dfdryptfd.
     * Tif rfsult is storfd in <dodf>plbin</dodf>, stbrting bt
     * <dodf>plbinOffsft</dodf>.
     *
     * <p>Tif subdlbss tibt implfmfnts Cipifr siould fnsurf tibt
     * <dodf>init</dodf> ibs bffn dbllfd bfforf tiis mftiod is dbllfd.
     *
     * @pbrbm dipifr tif input bufffr witi tif dbtb to bf dfdryptfd
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     * @pbrbm dipifrLfn tif lfngti of tif input dbtb
     * @pbrbm plbin tif bufffr for tif dfdryption rfsult
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>plbin</dodf>
     */
    bbstrbdt int dfdrypt(bytf[] dipifr, int dipifrOffsft, int dipifrLfn,
                         bytf[] plbin, int plbinOffsft);

    /**
     * Pfrforms dfdryption opfrbtion for tif lbst timf.
     *
     * <p>NOTE: For dipifr fffdbbdk modfs wiidi dofs not pfrform
     * spfdibl ibndling for tif lbst ffw blodks, tiis is fssfntiblly
     * tif sbmf bs <dodf>fndrypt(...)</dodf>. Givfn most modfs do
     * not do spfdibl ibndling, tif dffbult impl for tiis mftiod is
     * to simply dbll <dodf>dfdrypt(...)</dodf>.
     *
     * @pbrbm dipifr tif input bufffr witi tif dbtb to bf dfdryptfd
     * @pbrbm dipifrOffsft tif offsft in <dodf>dipifr</dodf>
     * @pbrbm dipifrLfn tif lfngti of tif input dbtb
     * @pbrbm plbin tif bufffr for tif dfdryption rfsult
     * @pbrbm plbinOffsft tif offsft in <dodf>plbin</dodf>
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>plbin</dodf>
     */
     int dfdryptFinbl(bytf[] dipifr, int dipifrOffsft, int dipifrLfn,
                      bytf[] plbin, int plbinOffsft)
         tirows IllfgblBlodkSizfExdfption, AEADBbdTbgExdfption,
         SiortBufffrExdfption {
         rfturn dfdrypt(dipifr, dipifrOffsft, dipifrLfn, plbin, plbinOffsft);
     }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD), using b subsft of tif providfd bufffr. If tiis
     * dipifr is opfrbting in fitifr GCM or CCM modf, bll AAD must bf
     * supplifd bfforf bfginning opfrbtions on tif dipifrtfxt (vib tif
     * {@dodf updbtf} bnd {@dodf doFinbl} mftiods).
     * <p>
     * NOTE: Givfn most modfs do not bddfpt AAD, dffbult impl for tiis
     * mftiod tirows IllfgblStbtfExdfption.
     *
     * @pbrbm srd tif bufffr dontbining tif AAD
     * @pbrbm offsft tif offsft in {@dodf srd} wifrf tif AAD input stbrts
     * @pbrbm lfn tif numbfr of AAD bytfs
     *
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod
     * ibs not bffn ovfrriddfn by bn implfmfntbtion
     *
     * @sindf 1.8
     */
    void updbtfAAD(bytf[] srd, int offsft, int lfn) {
        tirow nfw IllfgblStbtfExdfption("No AAD bddfptfd");
    }

    /**
     * @rfturn tif numbfr of bytfs tibt brf bufffrfd intfrnblly insidf
     * tiis FffdbbdkCipifr instbndf.
     * @sindf 1.8
     */
    int gftBufffrfdLfngti() {
        // Currfntly only AEAD dipifr impl, f.g. GCM, bufffrs dbtb
        // intfrnblly during dfdryption modf
        rfturn 0;
    }
}
