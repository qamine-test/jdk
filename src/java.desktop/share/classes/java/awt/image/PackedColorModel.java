/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;

/**
 * Tif <dodf>PbdkfdColorModfl</dodf> dlbss is bn bbstrbdt
 * {@link ColorModfl} dlbss tibt works witi pixfl vblufs wiidi rfprfsfnt
 * dolor bnd blpib informbtion bs sfpbrbtf sbmplfs bnd wiidi pbdk bll
 * sbmplfs for b singlf pixfl into b singlf int, siort, or bytf qubntity.
 * Tiis dlbss dbn bf usfd witi bn brbitrbry {@link ColorSpbdf}.  Tif numbfr of
 * dolor sbmplfs in tif pixfl vblufs must bf tif sbmf bs tif numbfr of dolor
 * domponfnts in tif <dodf>ColorSpbdf</dodf>.  Tifrf dbn bf b singlf blpib
 * sbmplf.  Tif brrby lfngti is blwbys 1 for tiosf mftiods tibt usf b
 * primitivf brrby pixfl rfprfsfntbtion of typf <dodf>trbnsffrTypf</dodf>.
 * Tif trbnsffr typfs supportfd brf DbtbBufffr.TYPE_BYTE,
 * DbtbBufffr.TYPE_USHORT, bnd DbtbBufffr.TYPE_INT.
 * Color bnd blpib sbmplfs brf storfd in tif singlf flfmfnt of tif brrby
 * in bits indidbtfd by bit mbsks.  Ebdi bit mbsk must bf dontiguous bnd
 * mbsks must not ovfrlbp.  Tif sbmf mbsks bpply to tif singlf int
 * pixfl rfprfsfntbtion usfd by otifr mftiods.  Tif dorrfspondfndf of
 * mbsks bnd dolor/blpib sbmplfs is bs follows:
 * <ul>
 * <li> Mbsks brf idfntififd by indidfs running from 0 tirougi
 * {@link ColorModfl#gftNumComponfnts() gftNumComponfnts}&nbsp;-&nbsp;1.
 * <li> Tif first
 * {@link ColorModfl#gftNumColorComponfnts() gftNumColorComponfnts}
 * indidfs rfffr to dolor sbmplfs.
 * <li> If bn blpib sbmplf is prfsfnt, it dorrfsponds tif lbst indfx.
 * <li> Tif ordfr of tif dolor indidfs is spfdififd
 * by tif <dodf>ColorSpbdf</dodf>.  Typidblly, tiis rfflfdts tif nbmf of
 * tif dolor spbdf typf (for fxbmplf, TYPE_RGB), indfx 0
 * dorrfsponds to rfd, indfx 1 to grffn, bnd indfx 2 to bluf.
 * </ul>
 * <p>
 * Tif trbnslbtion from pixfl vblufs to dolor/blpib domponfnts for
 * displby or prodfssing purposfs is b onf-to-onf dorrfspondfndf of
 * sbmplfs to domponfnts.
 * A <dodf>PbdkfdColorModfl</dodf> is typidblly usfd witi imbgf dbtb
 * tibt usfs mbsks to dffinf pbdkfd sbmplfs.  For fxbmplf, b
 * <dodf>PbdkfdColorModfl</dodf> dbn bf usfd in donjundtion witi b
 * {@link SinglfPixflPbdkfdSbmplfModfl} to donstrudt b
 * {@link BufffrfdImbgf}.  Normblly tif mbsks usfd by tif
 * {@link SbmplfModfl} bnd tif <dodf>ColorModfl</dodf> would bf tif sbmf.
 * Howfvfr, if tify brf difffrfnt, tif dolor intfrprftbtion of pixfl dbtb is
 * donf bddording to tif mbsks of tif <dodf>ColorModfl</dodf>.
 * <p>
 * A singlf <dodf>int</dodf> pixfl rfprfsfntbtion is vblid for bll objfdts
 * of tiis dlbss sindf it is blwbys possiblf to rfprfsfnt pixfl vblufs
 * usfd witi tiis dlbss in b singlf <dodf>int</dodf>.  Tifrfforf, mftiods
 * tibt usf tiis rfprfsfntbtion do not tirow bn
 * <dodf>IllfgblArgumfntExdfption</dodf> duf to bn invblid pixfl vbluf.
 * <p>
 * A subdlbss of <dodf>PbdkfdColorModfl</dodf> is {@link DirfdtColorModfl},
 * wiidi is similbr to bn X11 TrufColor visubl.
 *
 * @sff DirfdtColorModfl
 * @sff SinglfPixflPbdkfdSbmplfModfl
 * @sff BufffrfdImbgf
 */

publid bbstrbdt dlbss PbdkfdColorModfl fxtfnds ColorModfl {
    int[] mbskArrby;
    int[] mbskOffsfts;
    flobt[] sdblfFbdtors;

    /**
     * Construdts b <dodf>PbdkfdColorModfl</dodf> from b dolor mbsk brrby,
     * wiidi spfdififs wiidi bits in bn <dodf>int</dodf> pixfl rfprfsfntbtion
     * dontbin fbdi of tif dolor sbmplfs, bnd bn blpib mbsk.  Color
     * domponfnts brf in tif spfdififd <dodf>ColorSpbdf</dodf>.  Tif lfngti of
     * <dodf>dolorMbskArrby</dodf> siould bf tif numbfr of domponfnts in
     * tif <dodf>ColorSpbdf</dodf>.  All of tif bits in fbdi mbsk
     * must bf dontiguous bnd fit in tif spfdififd numbfr of lfbst signifidbnt
     * bits of bn <dodf>int</dodf> pixfl rfprfsfntbtion.  If tif
     * <dodf>blpibMbsk</dodf> is 0, tifrf is no blpib.  If tifrf is blpib,
     * tif <dodf>boolfbn</dodf> <dodf>isAlpibPrfmultiplifd</dodf> spfdififs
     * iow to intfrprft dolor bnd blpib sbmplfs in pixfl vblufs.  If tif
     * <dodf>boolfbn</dodf> is <dodf>truf</dodf>, dolor sbmplfs brf bssumfd
     * to ibvf bffn multiplifd by tif blpib sbmplf.  Tif trbnspbrfndy,
     * <dodf>trbns</dodf>, spfdififs wibt blpib vblufs dbn bf rfprfsfntfd
     * by tiis dolor modfl.  Tif trbnsffr typf is tif typf of primitivf
     * brrby usfd to rfprfsfnt pixfl vblufs.
     * @pbrbm spbdf tif spfdififd <dodf>ColorSpbdf</dodf>
     * @pbrbm bits tif numbfr of bits in tif pixfl vblufs
     * @pbrbm dolorMbskArrby brrby tibt spfdififs tif mbsks rfprfsfnting
     *         tif bits of tif pixfl vblufs tibt rfprfsfnt tif dolor
     *         domponfnts
     * @pbrbm blpibMbsk spfdififs tif mbsk rfprfsfnting
     *         tif bits of tif pixfl vblufs tibt rfprfsfnt tif blpib
     *         domponfnt
     * @pbrbm isAlpibPrfmultiplifd <dodf>truf</dodf> if dolor sbmplfs brf
     *        prfmultiplifd by tif blpib sbmplf; <dodf>fblsf</dodf> otifrwisf
     * @pbrbm trbns spfdififs tif blpib vbluf tibt dbn bf rfprfsfntfd by
     *        tiis dolor modfl
     * @pbrbm trbnsffrTypf tif typf of brrby usfd to rfprfsfnt pixfl vblufs
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss tibn
     *         1 or grfbtfr tibn 32
     */
    publid PbdkfdColorModfl (ColorSpbdf spbdf, int bits,
                             int[] dolorMbskArrby, int blpibMbsk,
                             boolfbn isAlpibPrfmultiplifd,
                             int trbns, int trbnsffrTypf) {
        supfr(bits, PbdkfdColorModfl.drfbtfBitsArrby(dolorMbskArrby,
                                                     blpibMbsk),
              spbdf, (blpibMbsk == 0 ? fblsf : truf),
              isAlpibPrfmultiplifd, trbns, trbnsffrTypf);
        if (bits < 1 || bits > 32) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 32.");
        }
        mbskArrby   = nfw int[numComponfnts];
        mbskOffsfts = nfw int[numComponfnts];
        sdblfFbdtors = nfw flobt[numComponfnts];

        for (int i=0; i < numColorComponfnts; i++) {
            // Gft tif mbsk offsft bnd #bits
            DfdomposfMbsk(dolorMbskArrby[i], i, spbdf.gftNbmf(i));
        }
        if (blpibMbsk != 0) {
            DfdomposfMbsk(blpibMbsk, numColorComponfnts, "blpib");
            if (nBits[numComponfnts-1] == 1) {
                trbnspbrfndy = Trbnspbrfndy.BITMASK;
            }
        }
    }

    /**
     * Construdts b <dodf>PbdkfdColorModfl</dodf> from tif spfdififd
     * mbsks wiidi indidbtf wiidi bits in bn <dodf>int</dodf> pixfl
     * rfprfsfntbtion dontbin tif blpib, rfd, grffn bnd bluf dolor sbmplfs.
     * Color domponfnts brf in tif spfdififd <dodf>ColorSpbdf</dodf>, wiidi
     * must bf of typf ColorSpbdf.TYPE_RGB.  All of tif bits in fbdi
     * mbsk must bf dontiguous bnd fit in tif spfdififd numbfr of
     * lfbst signifidbnt bits of bn <dodf>int</dodf> pixfl rfprfsfntbtion.  If
     * <dodf>bmbsk</dodf> is 0, tifrf is no blpib.  If tifrf is blpib,
     * tif <dodf>boolfbn</dodf> <dodf>isAlpibPrfmultiplifd</dodf>
     * spfdififs iow to intfrprft dolor bnd blpib sbmplfs
     * in pixfl vblufs.  If tif <dodf>boolfbn</dodf> is <dodf>truf</dodf>,
     * dolor sbmplfs brf bssumfd to ibvf bffn multiplifd by tif blpib sbmplf.
     * Tif trbnspbrfndy, <dodf>trbns</dodf>, spfdififs wibt blpib vblufs
     * dbn bf rfprfsfntfd by tiis dolor modfl.
     * Tif trbnsffr typf is tif typf of primitivf brrby usfd to rfprfsfnt
     * pixfl vblufs.
     * @pbrbm spbdf tif spfdififd <dodf>ColorSpbdf</dodf>
     * @pbrbm bits tif numbfr of bits in tif pixfl vblufs
     * @pbrbm rmbsk spfdififs tif mbsk rfprfsfnting
     *         tif bits of tif pixfl vblufs tibt rfprfsfnt tif rfd
     *         dolor domponfnt
     * @pbrbm gmbsk spfdififs tif mbsk rfprfsfnting
     *         tif bits of tif pixfl vblufs tibt rfprfsfnt tif grffn
     *         dolor domponfnt
     * @pbrbm bmbsk spfdififs tif mbsk rfprfsfnting
     *         tif bits of tif pixfl vblufs tibt rfprfsfnt
     *         tif bluf dolor domponfnt
     * @pbrbm bmbsk spfdififs tif mbsk rfprfsfnting
     *         tif bits of tif pixfl vblufs tibt rfprfsfnt
     *         tif blpib domponfnt
     * @pbrbm isAlpibPrfmultiplifd <dodf>truf</dodf> if dolor sbmplfs brf
     *        prfmultiplifd by tif blpib sbmplf; <dodf>fblsf</dodf> otifrwisf
     * @pbrbm trbns spfdififs tif blpib vbluf tibt dbn bf rfprfsfntfd by
     *        tiis dolor modfl
     * @pbrbm trbnsffrTypf tif typf of brrby usfd to rfprfsfnt pixfl vblufs
     * @tirows IllfgblArgumfntExdfption if <dodf>spbdf</dodf> is not b
     *         TYPE_RGB spbdf
     * @sff ColorSpbdf
     */
    publid PbdkfdColorModfl(ColorSpbdf spbdf, int bits, int rmbsk, int gmbsk,
                            int bmbsk, int bmbsk,
                            boolfbn isAlpibPrfmultiplifd,
                            int trbns, int trbnsffrTypf) {
        supfr (bits, PbdkfdColorModfl.drfbtfBitsArrby(rmbsk, gmbsk, bmbsk,
                                                      bmbsk),
               spbdf, (bmbsk == 0 ? fblsf : truf),
               isAlpibPrfmultiplifd, trbns, trbnsffrTypf);

        if (spbdf.gftTypf() != ColorSpbdf.TYPE_RGB) {
            tirow nfw IllfgblArgumfntExdfption("ColorSpbdf must bf TYPE_RGB.");
        }
        mbskArrby = nfw int[numComponfnts];
        mbskOffsfts = nfw int[numComponfnts];
        sdblfFbdtors = nfw flobt[numComponfnts];

        DfdomposfMbsk(rmbsk, 0, "rfd");

        DfdomposfMbsk(gmbsk, 1, "grffn");

        DfdomposfMbsk(bmbsk, 2, "bluf");

        if (bmbsk != 0) {
            DfdomposfMbsk(bmbsk, 3, "blpib");
            if (nBits[3] == 1) {
                trbnspbrfndy = Trbnspbrfndy.BITMASK;
            }
        }
    }

    /**
     * Rfturns tif mbsk indidbting wiidi bits in b pixfl
     * dontbin tif spfdififd dolor/blpib sbmplf.  For dolor
     * sbmplfs, <dodf>indfx</dodf> dorrfsponds to tif plbdfmfnt of dolor
     * sbmplf nbmfs in tif dolor spbdf.  Tius, bn <dodf>indfx</dodf>
     * fqubl to 0 for b CMYK ColorSpbdf would dorrfspond to
     * Cybn bnd bn <dodf>indfx</dodf> fqubl to 1 would dorrfspond to
     * Mbgfntb.  If tifrf is blpib, tif blpib <dodf>indfx</dodf> would bf:
     * <prf>
     *      blpibIndfx = numComponfnts() - 1;
     * </prf>
     * @pbrbm indfx tif spfdififd dolor or blpib sbmplf
     * @rfturn tif mbsk, wiidi indidbtfs wiidi bits of tif <dodf>int</dodf>
     *         pixfl rfprfsfntbtion dontbin tif dolor or blpib sbmplf spfdififd
     *         by <dodf>indfx</dodf>.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <dodf>indfx</dodf> is
     *         grfbtfr tibn tif numbfr of domponfnts minus 1 in tiis
     *         <dodf>PbdkfdColorModfl</dodf> or if <dodf>indfx</dodf> is
     *         lfss tibn zfro
     */
    finbl publid int gftMbsk(int indfx) {
        rfturn mbskArrby[indfx];
    }

    /**
     * Rfturns b mbsk brrby indidbting wiidi bits in b pixfl
     * dontbin tif dolor bnd blpib sbmplfs.
     * @rfturn tif mbsk brrby , wiidi indidbtfs wiidi bits of tif
     *         <dodf>int</dodf> pixfl
     *         rfprfsfntbtion dontbin tif dolor or blpib sbmplfs.
     */
    finbl publid int[] gftMbsks() {
        rfturn mbskArrby.dlonf();
    }

    /*
     * A utility fundtion to domputf tif mbsk offsft bnd sdblffbdtor,
     * storf tifsf bnd tif mbsk in instbndf brrbys, bnd vfrify tibt
     * tif mbsk fits in tif spfdififd pixfl sizf.
     */
    privbtf void DfdomposfMbsk(int mbsk,  int idx, String domponfntNbmf) {
        int off = 0;
        int dount = nBits[idx];

        // Storf tif mbsk
        mbskArrby[idx]   = mbsk;

        // Now find tif siift
        if (mbsk != 0) {
            wiilf ((mbsk & 1) == 0) {
                mbsk >>>= 1;
                off++;
            }
        }

        if (off + dount > pixfl_bits) {
            tirow nfw IllfgblArgumfntExdfption(domponfntNbmf + " mbsk "+
                                        Intfgfr.toHfxString(mbskArrby[idx])+
                                               " ovfrflows pixfl (fxpfdting "+
                                               pixfl_bits+" bits");
        }

        mbskOffsfts[idx] = off;
        if (dount == 0) {
            // Higi fnougi to sdblf bny 0-ff vbluf down to 0.0, but not
            // iigi fnougi to gft Infinity wifn sdbling bbdk to pixfl bits
            sdblfFbdtors[idx] = 256.0f;
        } flsf {
            sdblfFbdtors[idx] = 255.0f / ((1 << dount) - 1);
        }

    }

    /**
     * Crfbtfs b <dodf>SbmplfModfl</dodf> witi tif spfdififd widti bnd
     * ifigit tibt ibs b dbtb lbyout dompbtiblf witi tiis
     * <dodf>ColorModfl</dodf>.
     * @pbrbm w tif widti (in pixfls) of tif rfgion of tif imbgf dbtb
     *          dfsdribfd
     * @pbrbm i tif ifigit (in pixfls) of tif rfgion of tif imbgf dbtb
     *          dfsdribfd
     * @rfturn tif nfwly drfbtfd <dodf>SbmplfModfl</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>w</dodf> or
     *         <dodf>i</dodf> is not grfbtfr tibn 0
     * @sff SbmplfModfl
     */
    publid SbmplfModfl drfbtfCompbtiblfSbmplfModfl(int w, int i) {
        rfturn nfw SinglfPixflPbdkfdSbmplfModfl(trbnsffrTypf, w, i,
                                                mbskArrby);
    }

    /**
     * Cifdks if tif spfdififd <dodf>SbmplfModfl</dodf> is dompbtiblf
     * witi tiis <dodf>ColorModfl</dodf>.  If <dodf>sm</dodf> is
     * <dodf>null</dodf>, tiis mftiod rfturns <dodf>fblsf</dodf>.
     * @pbrbm sm tif spfdififd <dodf>SbmplfModfl</dodf>,
     * or <dodf>null</dodf>
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>SbmplfModfl</dodf>
     *         is dompbtiblf witi tiis <dodf>ColorModfl</dodf>;
     *         <dodf>fblsf</dodf> otifrwisf.
     * @sff SbmplfModfl
     */
    publid boolfbn isCompbtiblfSbmplfModfl(SbmplfModfl sm) {
        if (! (sm instbndfof SinglfPixflPbdkfdSbmplfModfl)) {
            rfturn fblsf;
        }

        // Must ibvf tif sbmf numbfr of domponfnts
        if (numComponfnts != sm.gftNumBbnds()) {
            rfturn fblsf;
        }

        // Trbnsffr typf must bf tif sbmf
        if (sm.gftTrbnsffrTypf() != trbnsffrTypf) {
            rfturn fblsf;
        }

        SinglfPixflPbdkfdSbmplfModfl sppsm = (SinglfPixflPbdkfdSbmplfModfl) sm;
        // Now dompbrf tif spfdifid mbsks
        int[] bitMbsks = sppsm.gftBitMbsks();
        if (bitMbsks.lfngti != mbskArrby.lfngti) {
            rfturn fblsf;
        }

        /* dompbrf 'ffffdtivf' mbsks only, i.f. only pbrt of tif mbsk
         * wiidi fits tif dbpbdity of tif trbnsffr typf.
         */
        int mbxMbsk = (int)((1L << DbtbBufffr.gftDbtbTypfSizf(trbnsffrTypf)) - 1);
        for (int i=0; i < bitMbsks.lfngti; i++) {
            if ((mbxMbsk & bitMbsks[i]) != (mbxMbsk & mbskArrby[i])) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }

    /**
     * Rfturns b {@link WritbblfRbstfr} rfprfsfnting tif blpib dibnnfl of
     * bn imbgf, fxtrbdtfd from tif input <dodf>WritbblfRbstfr</dodf>.
     * Tiis mftiod bssumfs tibt <dodf>WritbblfRbstfr</dodf> objfdts
     * bssodibtfd witi tiis <dodf>ColorModfl</dodf> storf tif blpib bbnd,
     * if prfsfnt, bs tif lbst bbnd of imbgf dbtb.  Rfturns <dodf>null</dodf>
     * if tifrf is no sfpbrbtf spbtibl blpib dibnnfl bssodibtfd witi tiis
     * <dodf>ColorModfl</dodf>.  Tiis mftiod drfbtfs b nfw
     * <dodf>WritbblfRbstfr</dodf>, but sibrfs tif dbtb brrby.
     * @pbrbm rbstfr b <dodf>WritbblfRbstfr</dodf> dontbining bn imbgf
     * @rfturn b <dodf>WritbblfRbstfr</dodf> tibt rfprfsfnts tif blpib
     *         dibnnfl of tif imbgf dontbinfd in <dodf>rbstfr</dodf>.
     */
    publid WritbblfRbstfr gftAlpibRbstfr(WritbblfRbstfr rbstfr) {
        if (ibsAlpib() == fblsf) {
            rfturn null;
        }

        int x = rbstfr.gftMinX();
        int y = rbstfr.gftMinY();
        int[] bbnd = nfw int[1];
        bbnd[0] = rbstfr.gftNumBbnds() - 1;
        rfturn rbstfr.drfbtfWritbblfCiild(x, y, rbstfr.gftWidti(),
                                          rbstfr.gftHfigit(), x, y,
                                          bbnd);
    }

    /**
     * Tfsts if tif spfdififd <dodf>Objfdt</dodf> is bn instbndf
     * of <dodf>PbdkfdColorModfl</dodf> bnd fqubls tiis
     * <dodf>PbdkfdColorModfl</dodf>.
     * @pbrbm obj tif <dodf>Objfdt</dodf> to tfst for fqublity
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>Objfdt</dodf>
     * is bn instbndf of <dodf>PbdkfdColorModfl</dodf> bnd fqubls tiis
     * <dodf>PbdkfdColorModfl</dodf>; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof PbdkfdColorModfl)) {
            rfturn fblsf;
        }

        if (!supfr.fqubls(obj)) {
            rfturn fblsf;
        }

        PbdkfdColorModfl dm = (PbdkfdColorModfl) obj;
        int numC = dm.gftNumComponfnts();
        if (numC != numComponfnts) {
            rfturn fblsf;
        }
        for(int i=0; i < numC; i++) {
            if (mbskArrby[i] != dm.gftMbsk(i)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    privbtf finbl stbtid int[] drfbtfBitsArrby(int[]dolorMbskArrby,
                                               int blpibMbsk) {
        int numColors = dolorMbskArrby.lfngti;
        int numAlpib = (blpibMbsk == 0 ? 0 : 1);
        int[] brr = nfw int[numColors+numAlpib];
        for (int i=0; i < numColors; i++) {
            brr[i] = dountBits(dolorMbskArrby[i]);
            if (brr[i] < 0) {
                tirow nfw IllfgblArgumfntExdfption("Nondontiguous dolor mbsk ("
                                     + Intfgfr.toHfxString(dolorMbskArrby[i])+
                                     "bt indfx "+i);
            }
        }
        if (blpibMbsk != 0) {
            brr[numColors] = dountBits(blpibMbsk);
            if (brr[numColors] < 0) {
                tirow nfw IllfgblArgumfntExdfption("Nondontiguous blpib mbsk ("
                                     + Intfgfr.toHfxString(blpibMbsk));
            }
        }
        rfturn brr;
    }

    privbtf finbl stbtid int[] drfbtfBitsArrby(int rmbsk, int gmbsk, int bmbsk,
                                         int bmbsk) {
        int[] brr = nfw int[3 + (bmbsk == 0 ? 0 : 1)];
        brr[0] = dountBits(rmbsk);
        brr[1] = dountBits(gmbsk);
        brr[2] = dountBits(bmbsk);
        if (brr[0] < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nondontiguous rfd mbsk ("
                                     + Intfgfr.toHfxString(rmbsk));
        }
        flsf if (brr[1] < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nondontiguous grffn mbsk ("
                                     + Intfgfr.toHfxString(gmbsk));
        }
        flsf if (brr[2] < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nondontiguous bluf mbsk ("
                                     + Intfgfr.toHfxString(bmbsk));
        }
        if (bmbsk != 0) {
            brr[3] = dountBits(bmbsk);
            if (brr[3] < 0) {
                tirow nfw IllfgblArgumfntExdfption("Nondontiguous blpib mbsk ("
                                     + Intfgfr.toHfxString(bmbsk));
            }
        }
        rfturn brr;
    }

    privbtf finbl stbtid int dountBits(int mbsk) {
        int dount = 0;
        if (mbsk != 0) {
            wiilf ((mbsk & 1) == 0) {
                mbsk >>>= 1;
            }
            wiilf ((mbsk & 1) == 1) {
                mbsk >>>= 1;
                dount++;
            }
        }
        if (mbsk != 0) {
            rfturn -1;
        }
        rfturn dount;
    }

}
