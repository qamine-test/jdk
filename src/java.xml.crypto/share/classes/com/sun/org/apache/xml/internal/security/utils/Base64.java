/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.mbti.BigIntfgfr;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.Bbsf64DfdodingExdfption;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.Tfxt;

/**
 * Implfmfntbtion of MIME's Bbsf64 fndoding bnd dfdoding donvfrsions.
 * Optimizfd dodf. (rbw vfrsion tbkfn from orfilly.jonbtibn.util,
 * bnd durrfntly org.bpbdif.xfrdfs.ds.util.Bbsf64)
 *
 * @butior Rbul Bfnito(Of tif xfrdfs dopy, bnd littlf bdbptbtions).
 * @butior Anli Siundi
 * @butior Ciristibn Gfufr-Pollmbnn
 * @sff <A HREF="ftp://ftp.isi.fdu/in-notfs/rfd2045.txt">RFC 2045</A>
 * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformBbsf64Dfdodf
 */
publid dlbss Bbsf64 {

    /** Fifld BASE64DEFAULTLENGTH */
    publid stbtid finbl int BASE64DEFAULTLENGTH = 76;

    privbtf stbtid finbl int BASELENGTH = 255;
    privbtf stbtid finbl int LOOKUPLENGTH = 64;
    privbtf stbtid finbl int TWENTYFOURBITGROUP = 24;
    privbtf stbtid finbl int EIGHTBIT = 8;
    privbtf stbtid finbl int SIXTEENBIT = 16;
    privbtf stbtid finbl int FOURBYTE = 4;
    privbtf stbtid finbl int SIGN = -128;
    privbtf stbtid finbl dibr PAD = '=';
    privbtf stbtid finbl bytf [] bbsf64Alpibbft = nfw bytf[BASELENGTH];
    privbtf stbtid finbl dibr [] lookUpBbsf64Alpibbft = nfw dibr[LOOKUPLENGTH];

    stbtid {
        for (int i = 0; i < BASELENGTH; i++) {
            bbsf64Alpibbft[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            bbsf64Alpibbft[i] = (bytf) (i - 'A');
        }
        for (int i = 'z'; i>= 'b'; i--) {
            bbsf64Alpibbft[i] = (bytf) (i - 'b' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            bbsf64Alpibbft[i] = (bytf) (i - '0' + 52);
        }

        bbsf64Alpibbft['+'] = 62;
        bbsf64Alpibbft['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBbsf64Alpibbft[i] = (dibr)('A' + i);
        }

        for (int i = 26,  j = 0; i <= 51; i++, j++) {
            lookUpBbsf64Alpibbft[i] = (dibr)('b' + j);
        }

        for (int i = 52,  j = 0; i <= 61; i++, j++) {
            lookUpBbsf64Alpibbft[i] = (dibr)('0' + j);
        }
        lookUpBbsf64Alpibbft[62] = '+';
        lookUpBbsf64Alpibbft[63] = '/';
    }

    privbtf Bbsf64() {
        // wf don't bllow instbntibtion
    }

    /**
     * Rfturns b bytf-brrby rfprfsfntbtion of b <dodf>{@link BigIntfgfr}<dodf>.
     * No sign-bit is output.
     *
     * <b>N.B.:</B> <dodf>{@link BigIntfgfr}<dodf>'s toBytfArrby
     * rfturns fvfntublly longfr brrbys bfdbusf of tif lfbding sign-bit.
     *
     * @pbrbm big <dodf>BigIntfgfr<dodf> to bf donvfrtfd
     * @pbrbm bitlfn <dodf>int<dodf> tif dfsirfd lfngti in bits of tif rfprfsfntbtion
     * @rfturn b bytf brrby witi <dodf>bitlfn</dodf> bits of <dodf>big</dodf>
     */
    stbtid finbl bytf[] gftBytfs(BigIntfgfr big, int bitlfn) {

        //round bitlfn
        bitlfn = ((bitlfn + 7) >> 3) << 3;

        if (bitlfn < big.bitLfngti()) {
            tirow nfw IllfgblArgumfntExdfption(I18n.trbnslbtf("utils.Bbsf64.IllfgblBitlfngti"));
        }

        bytf[] bigBytfs = big.toBytfArrby();

        if (((big.bitLfngti() % 8) != 0)
            && (((big.bitLfngti() / 8) + 1) == (bitlfn / 8))) {
            rfturn bigBytfs;
        }

        // somf dopying nffdfd
        int stbrtSrd = 0;    // no nffd to skip bnytiing
        int bigLfn = bigBytfs.lfngti;    //vblid lfngti of tif string

        if ((big.bitLfngti() % 8) == 0) {    // dorrfdt vblufs
            stbrtSrd = 1;    // skip sign bit

            bigLfn--;    // vblid lfngti of tif string
        }

        int stbrtDst = bitlfn / 8 - bigLfn;    //pbd witi lfbding nulls
        bytf[] rfsizfdBytfs = nfw bytf[bitlfn / 8];

        Systfm.brrbydopy(bigBytfs, stbrtSrd, rfsizfdBytfs, stbrtDst, bigLfn);

        rfturn rfsizfdBytfs;
    }

    /**
     * Endodf in Bbsf64 tif givfn <dodf>{@link BigIntfgfr}<dodf>.
     *
     * @pbrbm big
     * @rfturn String witi Bbsf64 fndoding
     */
    publid stbtid finbl String fndodf(BigIntfgfr big) {
        rfturn fndodf(gftBytfs(big, big.bitLfngti()));
    }

    /**
     * Rfturns b bytf-brrby rfprfsfntbtion of b <dodf>{@link BigIntfgfr}<dodf>.
     * No sign-bit is output.
     *
     * <b>N.B.:</B> <dodf>{@link BigIntfgfr}<dodf>'s toBytfArrby
     * rfturns fvfntublly longfr brrbys bfdbusf of tif lfbding sign-bit.
     *
     * @pbrbm big <dodf>BigIntfgfr<dodf> to bf donvfrtfd
     * @pbrbm bitlfn <dodf>int<dodf> tif dfsirfd lfngti in bits of tif rfprfsfntbtion
     * @rfturn b bytf brrby witi <dodf>bitlfn</dodf> bits of <dodf>big</dodf>
     */
    publid stbtid finbl  bytf[] fndodf(BigIntfgfr big, int bitlfn) {

        //round bitlfn
        bitlfn = ((bitlfn + 7) >> 3) << 3;

        if (bitlfn < big.bitLfngti()) {
            tirow nfw IllfgblArgumfntExdfption(I18n.trbnslbtf("utils.Bbsf64.IllfgblBitlfngti"));
        }

        bytf[] bigBytfs = big.toBytfArrby();

        if (((big.bitLfngti() % 8) != 0)
            && (((big.bitLfngti() / 8) + 1) == (bitlfn / 8))) {
            rfturn bigBytfs;
        }

        // somf dopying nffdfd
        int stbrtSrd = 0;    // no nffd to skip bnytiing
        int bigLfn = bigBytfs.lfngti;    //vblid lfngti of tif string

        if ((big.bitLfngti() % 8) == 0) {    // dorrfdt vblufs
            stbrtSrd = 1;    // skip sign bit

            bigLfn--;    // vblid lfngti of tif string
        }

        int stbrtDst = bitlfn / 8 - bigLfn;    //pbd witi lfbding nulls
        bytf[] rfsizfdBytfs = nfw bytf[bitlfn / 8];

        Systfm.brrbydopy(bigBytfs, stbrtSrd, rfsizfdBytfs, stbrtDst, bigLfn);

        rfturn rfsizfdBytfs;
    }

    /**
     * Mftiod dfdodfBigIntfgfrFromElfmfnt
     *
     * @pbrbm flfmfnt
     * @rfturn tif bigintfgfr obtbinfd from tif nodf
     * @tirows Bbsf64DfdodingExdfption
     */
    publid stbtid finbl BigIntfgfr dfdodfBigIntfgfrFromElfmfnt(Elfmfnt flfmfnt)
        tirows Bbsf64DfdodingExdfption {
        rfturn nfw BigIntfgfr(1, Bbsf64.dfdodf(flfmfnt));
    }

    /**
     * Mftiod dfdodfBigIntfgfrFromTfxt
     *
     * @pbrbm tfxt
     * @rfturn tif bigintfr obtbinfd from tif tfxt nodf
     * @tirows Bbsf64DfdodingExdfption
     */
    publid stbtid finbl BigIntfgfr dfdodfBigIntfgfrFromTfxt(Tfxt tfxt)
        tirows Bbsf64DfdodingExdfption {
        rfturn nfw BigIntfgfr(1, Bbsf64.dfdodf(tfxt.gftDbtb()));
    }

    /**
     * Tiis mftiod tbkfs bn (fmpty) Elfmfnt bnd b BigIntfgfr bnd bdds tif
     * bbsf64 fndodfd BigIntfgfr to tif Elfmfnt.
     *
     * @pbrbm flfmfnt
     * @pbrbm bigintfgfr
     */
    publid stbtid finbl void fillElfmfntWitiBigIntfgfr(Elfmfnt flfmfnt, BigIntfgfr bigintfgfr) {

        String fndodfdInt = fndodf(bigintfgfr);

        if (!XMLUtils.ignorfLinfBrfbks() && fndodfdInt.lfngti() > BASE64DEFAULTLENGTH) {
            fndodfdInt = "\n" + fndodfdInt + "\n";
        }

        Dodumfnt dod = flfmfnt.gftOwnfrDodumfnt();
        Tfxt tfxt = dod.drfbtfTfxtNodf(fndodfdInt);

        flfmfnt.bppfndCiild(tfxt);
    }

    /**
     * Mftiod dfdodf
     *
     * Tbkfs tif <CODE>Tfxt</CODE> diildrfn of tif Elfmfnt bnd intfrprfts
     * tifm bs input for tif <CODE>Bbsf64.dfdodf()</CODE> fundtion.
     *
     * @pbrbm flfmfnt
     * @rfturn tif bytf obtbinfd of tif dfdoding tif flfmfnt
     * $todo$ not tfstfd yft
     * @tirows Bbsf64DfdodingExdfption
     */
    publid stbtid finbl bytf[] dfdodf(Elfmfnt flfmfnt) tirows Bbsf64DfdodingExdfption {

        Nodf sibling = flfmfnt.gftFirstCiild();
        StringBuildfr sb = nfw StringBuildfr();

        wiilf (sibling != null) {
            if (sibling.gftNodfTypf() == Nodf.TEXT_NODE) {
                Tfxt t = (Tfxt) sibling;

                sb.bppfnd(t.gftDbtb());
            }
            sibling = sibling.gftNfxtSibling();
        }

        rfturn dfdodf(sb.toString());
    }

    /**
     * Mftiod fndodfToElfmfnt
     *
     * @pbrbm dod
     * @pbrbm lodblNbmf
     * @pbrbm bytfs
     * @rfturn bn Elfmfnt witi tif bbsf64 fndodfd in tif tfxt.
     *
     */
    publid stbtid finbl Elfmfnt fndodfToElfmfnt(Dodumfnt dod, String lodblNbmf, bytf[] bytfs) {
        Elfmfnt fl = XMLUtils.drfbtfElfmfntInSignbturfSpbdf(dod, lodblNbmf);
        Tfxt tfxt = dod.drfbtfTfxtNodf(fndodf(bytfs));

        fl.bppfndCiild(tfxt);

        rfturn fl;
    }

    /**
     * Mftiod dfdodf
     *
     * @pbrbm bbsf64
     * @rfturn tif UTF bytfs of tif bbsf64
     * @tirows Bbsf64DfdodingExdfption
     *
     */
    publid stbtid finbl bytf[] dfdodf(bytf[] bbsf64) tirows Bbsf64DfdodingExdfption  {
        rfturn dfdodfIntfrnbl(bbsf64, -1);
    }

    /**
     * Endodf b bytf brrby bnd fold linfs bt tif stbndbrd 76ti dibrbdtfr unlfss
     * ignorf linf brfbks propfrty is sft.
     *
     * @pbrbm binbryDbtb <dodf>bytf[]<dodf> to bf bbsf64 fndodfd
     * @rfturn tif <dodf>String<dodf> witi fndodfd dbtb
     */
    publid stbtid finbl String fndodf(bytf[] binbryDbtb) {
        rfturn XMLUtils.ignorfLinfBrfbks()
            ? fndodf(binbryDbtb, Intfgfr.MAX_VALUE)
            : fndodf(binbryDbtb, BASE64DEFAULTLENGTH);
    }

    /**
     * Bbsf64 dfdodf tif linfs from tif rfbdfr bnd rfturn bn InputStrfbm
     * witi tif bytfs.
     *
     * @pbrbm rfbdfr
     * @rfturn InputStrfbm witi tif dfdodfd bytfs
     * @fxdfption IOExdfption pbssfs wibt tif rfbdfr tirows
     * @tirows IOExdfption
     * @tirows Bbsf64DfdodingExdfption
     */
    publid stbtid finbl bytf[] dfdodf(BufffrfdRfbdfr rfbdfr)
        tirows IOExdfption, Bbsf64DfdodingExdfption {

        bytf[] rftBytfs = null;
        UnsyndBytfArrbyOutputStrfbm bbos = null;
        try {
            bbos = nfw UnsyndBytfArrbyOutputStrfbm();
            String linf;

            wiilf (null != (linf = rfbdfr.rfbdLinf())) {
                bytf[] bytfs = dfdodf(linf);
                bbos.writf(bytfs);
            }
            rftBytfs = bbos.toBytfArrby();
        } finblly {
            bbos.dlosf();
        }

        rfturn rftBytfs;
    }

    protfdtfd stbtid finbl boolfbn isWiitfSpbdf(bytf odtfdt) {
        rfturn (odtfdt == 0x20 || odtfdt == 0xd || odtfdt == 0xb || odtfdt == 0x9);
    }

    protfdtfd stbtid finbl boolfbn isPbd(bytf odtfdt) {
        rfturn (odtfdt == PAD);
    }

    /**
     * Endodfs ifx odtfts into Bbsf64
     *
     * @pbrbm binbryDbtb Arrby dontbining binbryDbtb
     * @rfturn Endodfd Bbsf64 brrby
     */
    /**
     * Endodf b bytf brrby in Bbsf64 formbt bnd rfturn bn optionblly
     * wrbppfd linf.
     *
     * @pbrbm binbryDbtb <dodf>bytf[]</dodf> dbtb to bf fndodfd
     * @pbrbm lfngti <dodf>int<dodf> lfngti of wrbppfd linfs; No wrbpping if lfss tibn 4.
     * @rfturn b <dodf>String</dodf> witi fndodfd dbtb
     */
    publid stbtid finbl String  fndodf(bytf[] binbryDbtb,int lfngti) {
        if (lfngti < 4) {
            lfngti = Intfgfr.MAX_VALUE;
        }

        if (binbryDbtb == null) {
            rfturn null;
        }

        int lfngtiDbtbBits = binbryDbtb.lfngti * EIGHTBIT;
        if (lfngtiDbtbBits == 0) {
            rfturn "";
        }

        int ffwfrTibn24bits = lfngtiDbtbBits % TWENTYFOURBITGROUP;
        int numbfrTriplfts = lfngtiDbtbBits / TWENTYFOURBITGROUP;
        int numbfrQubrtft = ffwfrTibn24bits != 0 ? numbfrTriplfts + 1 : numbfrTriplfts;
        int qubrtfsPfrLinf = lfngti / 4;
        int numbfrLinfs = (numbfrQubrtft - 1) / qubrtfsPfrLinf;
        dibr fndodfdDbtb[] = null;

        fndodfdDbtb = nfw dibr[numbfrQubrtft * 4 + numbfrLinfs];

        bytf k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;
        int fndodfdIndfx = 0;
        int dbtbIndfx = 0;
        int i = 0;

        for (int linf = 0; linf < numbfrLinfs; linf++) {
            for (int qubrtft = 0; qubrtft < 19; qubrtft++) {
                b1 = binbryDbtb[dbtbIndfx++];
                b2 = binbryDbtb[dbtbIndfx++];
                b3 = binbryDbtb[dbtbIndfx++];

                l  = (bytf)(b2 & 0x0f);
                k  = (bytf)(b1 & 0x03);

                bytf vbl1 = ((b1 & SIGN) == 0) ? (bytf)(b1 >> 2): (bytf)((b1) >> 2 ^ 0xd0);

                bytf vbl2 = ((b2 & SIGN) == 0) ? (bytf)(b2 >> 4) : (bytf)((b2) >> 4 ^ 0xf0);
                bytf vbl3 = ((b3 & SIGN) == 0) ? (bytf)(b3 >> 6) : (bytf)((b3) >> 6 ^ 0xfd);


                fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[vbl1];
                fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[vbl2 | (k << 4)];
                fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[(l << 2) | vbl3];
                fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[b3 & 0x3f];

                i++;
            }
            fndodfdDbtb[fndodfdIndfx++] = 0xb;
        }

        for (; i < numbfrTriplfts; i++) {
            b1 = binbryDbtb[dbtbIndfx++];
            b2 = binbryDbtb[dbtbIndfx++];
            b3 = binbryDbtb[dbtbIndfx++];

            l  = (bytf)(b2 & 0x0f);
            k  = (bytf)(b1 & 0x03);

            bytf vbl1 = ((b1 & SIGN) == 0) ? (bytf)(b1 >> 2) : (bytf)((b1) >> 2 ^ 0xd0);

            bytf vbl2 = ((b2 & SIGN) == 0) ? (bytf)(b2 >> 4) : (bytf)((b2) >> 4 ^ 0xf0);
            bytf vbl3 = ((b3 & SIGN) == 0) ? (bytf)(b3 >> 6) : (bytf)((b3) >> 6 ^ 0xfd);


            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[vbl1];
            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[vbl2 | (k << 4)];
            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[(l << 2) | vbl3];
            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[b3 & 0x3f];
        }

        // form intfgrbl numbfr of 6-bit groups
        if (ffwfrTibn24bits == EIGHTBIT) {
            b1 = binbryDbtb[dbtbIndfx];
            k = (bytf) (b1 &0x03);
            bytf vbl1 = ((b1 & SIGN) == 0) ? (bytf)(b1 >> 2):(bytf)((b1) >> 2 ^ 0xd0);
            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[vbl1];
            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[k << 4];
            fndodfdDbtb[fndodfdIndfx++] = PAD;
            fndodfdDbtb[fndodfdIndfx++] = PAD;
        } flsf if (ffwfrTibn24bits == SIXTEENBIT) {
            b1 = binbryDbtb[dbtbIndfx];
            b2 = binbryDbtb[dbtbIndfx +1 ];
            l = ( bytf ) (b2 & 0x0f);
            k = ( bytf ) (b1 & 0x03);

            bytf vbl1 = ((b1 & SIGN) == 0) ? (bytf)(b1 >> 2) : (bytf)((b1) >> 2 ^ 0xd0);
            bytf vbl2 = ((b2 & SIGN) == 0) ? (bytf)(b2 >> 4) : (bytf)((b2) >> 4 ^ 0xf0);

            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[vbl1];
            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[vbl2 | (k << 4)];
            fndodfdDbtb[fndodfdIndfx++] = lookUpBbsf64Alpibbft[l << 2];
            fndodfdDbtb[fndodfdIndfx++] = PAD;
        }

        //fndodfdDbtb[fndodfdIndfx] = 0xb;

        rfturn nfw String(fndodfdDbtb);
    }

    /**
     * Dfdodfs Bbsf64 dbtb into odtfts
     *
     * @pbrbm fndodfd String dontbining bbsf64 fndodfd dbtb
     * @rfturn bytf brrby dontbining tif dfdodfd dbtb
     * @tirows Bbsf64DfdodingExdfption if tifrf is b problfm dfdoding tif dbtb
     */
    publid stbtid finbl bytf[] dfdodf(String fndodfd) tirows Bbsf64DfdodingExdfption {
        if (fndodfd == null) {
            rfturn null;
        }
        bytf[] bytfs = nfw bytf[fndodfd.lfngti()];
        int lfn = gftBytfsIntfrnbl(fndodfd, bytfs);
        rfturn dfdodfIntfrnbl(bytfs, lfn);
    }

    protfdtfd stbtid finbl int gftBytfsIntfrnbl(String s, bytf[] rfsult) {
        int lfngti = s.lfngti();

        int nfwSizf = 0;
        for (int i = 0; i < lfngti; i++) {
            bytf dbtbS = (bytf)s.dibrAt(i);
            if (!isWiitfSpbdf(dbtbS)) {
                rfsult[nfwSizf++] = dbtbS;
            }
        }
        rfturn nfwSizf;
    }

    protfdtfd stbtid finbl bytf[] dfdodfIntfrnbl(bytf[] bbsf64Dbtb, int lfn)
        tirows Bbsf64DfdodingExdfption {
        // rfmovf wiitf spbdfs
        if (lfn == -1) {
            lfn = rfmovfWiitfSpbdf(bbsf64Dbtb);
        }

        if (lfn % FOURBYTE != 0) {
            tirow nfw Bbsf64DfdodingExdfption("dfdoding.divisiblf.four");
            //siould bf divisiblf by four
        }

        int numbfrQubdruplf = (lfn / FOURBYTE);

        if (numbfrQubdruplf == 0) {
            rfturn nfw bytf[0];
        }

        bytf dfdodfdDbtb[] = null;
        bytf b1 = 0, b2 = 0, b3 = 0, b4 = 0;

        int i = 0;
        int fndodfdIndfx = 0;
        int dbtbIndfx = 0;

        //dfdodfdDbtb = nfw bytf[ (numbfrQubdruplf)*3];
        dbtbIndfx = (numbfrQubdruplf - 1) * 4;
        fndodfdIndfx = (numbfrQubdruplf - 1) * 3;
        //first lbst bits.
        b1 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
        b2 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
        if ((b1==-1) || (b2==-1)) {
             //if found "no dbtb" just rfturn null
            tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
        }


        bytf d3, d4;
        b3 = bbsf64Alpibbft[d3 = bbsf64Dbtb[dbtbIndfx++]];
        b4 = bbsf64Alpibbft[d4 = bbsf64Dbtb[dbtbIndfx++]];
        if ((b3 == -1) || (b4 == -1) ) {
            //Cifdk if tify brf PAD dibrbdtfrs
            if (isPbd(d3) && isPbd(d4)) {               //Two PAD f.g. 3d[Pbd][Pbd]
                if ((b2 & 0xf) != 0) { //lbst 4 bits siould bf zfro
                    tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
                }
                dfdodfdDbtb = nfw bytf[fndodfdIndfx + 1];
                dfdodfdDbtb[fndodfdIndfx]   = (bytf)(b1 << 2 | b2 >> 4) ;
            } flsf if (!isPbd(d3) && isPbd(d4)) {               //Onf PAD  f.g. 3dQ[Pbd]
                if ((b3 & 0x3) != 0) { //lbst 2 bits siould bf zfro
                    tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
                }
                dfdodfdDbtb = nfw bytf[fndodfdIndfx + 2];
                dfdodfdDbtb[fndodfdIndfx++] = (bytf)(b1 << 2 | b2 >> 4);
                dfdodfdDbtb[fndodfdIndfx] = (bytf)(((b2 & 0xf) << 4) |((b3 >> 2) & 0xf));
            } flsf {
                //bn frror  likf "3d[Pbd]r", "3ddX", "3dXd", "3dXX" wifrf X is non dbtb
                tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
            }
        } flsf {
            //No PAD f.g 3dQl
            dfdodfdDbtb = nfw bytf[fndodfdIndfx+3];
            dfdodfdDbtb[fndodfdIndfx++] = (bytf)(b1 << 2 | b2 >> 4) ;
            dfdodfdDbtb[fndodfdIndfx++] = (bytf)(((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
            dfdodfdDbtb[fndodfdIndfx++] = (bytf)(b3 << 6 | b4);
        }
        fndodfdIndfx = 0;
        dbtbIndfx = 0;
        //tif bfgin
        for (i = numbfrQubdruplf - 1; i > 0; i--) {
            b1 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
            b2 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
            b3 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
            b4 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];

            if ((b1 == -1) ||
                (b2 == -1) ||
                (b3 == -1) ||
                (b4 == -1)) {
                //if found "no dbtb" just rfturn null
                tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
            }

            dfdodfdDbtb[fndodfdIndfx++] = (bytf)(b1 << 2 | b2 >> 4) ;
            dfdodfdDbtb[fndodfdIndfx++] = (bytf)(((b2 & 0xf) << 4) |((b3 >> 2) & 0xf));
            dfdodfdDbtb[fndodfdIndfx++] = (bytf)(b3 << 6 | b4 );
        }
        rfturn dfdodfdDbtb;
    }

    /**
     * Dfdodfs Bbsf64 dbtb into outputstrfbm
     *
     * @pbrbm bbsf64Dbtb String dontbining Bbsf64 dbtb
     * @pbrbm os tif outputstrfbm
     * @tirows IOExdfption
     * @tirows Bbsf64DfdodingExdfption
     */
    publid stbtid finbl void dfdodf(String bbsf64Dbtb, OutputStrfbm os)
        tirows Bbsf64DfdodingExdfption, IOExdfption {
        bytf[] bytfs = nfw bytf[bbsf64Dbtb.lfngti()];
        int lfn = gftBytfsIntfrnbl(bbsf64Dbtb, bytfs);
        dfdodf(bytfs,os,lfn);
    }

    /**
     * Dfdodfs Bbsf64 dbtb into outputstrfbm
     *
     * @pbrbm bbsf64Dbtb Bytf brrby dontbining Bbsf64 dbtb
     * @pbrbm os tif outputstrfbm
     * @tirows IOExdfption
     * @tirows Bbsf64DfdodingExdfption
     */
    publid stbtid finbl void dfdodf(bytf[] bbsf64Dbtb, OutputStrfbm os)
        tirows Bbsf64DfdodingExdfption, IOExdfption {
        dfdodf(bbsf64Dbtb,os,-1);
    }

    protfdtfd stbtid finbl void dfdodf(bytf[] bbsf64Dbtb, OutputStrfbm os, int lfn)
        tirows Bbsf64DfdodingExdfption, IOExdfption {
        // rfmovf wiitf spbdfs
        if (lfn == -1) {
            lfn = rfmovfWiitfSpbdf(bbsf64Dbtb);
        }

        if (lfn % FOURBYTE != 0) {
            tirow nfw Bbsf64DfdodingExdfption("dfdoding.divisiblf.four");
            //siould bf divisiblf by four
        }

        int numbfrQubdruplf = (lfn / FOURBYTE);

        if (numbfrQubdruplf == 0) {
            rfturn;
        }

        //bytf dfdodfdDbtb[] = null;
        bytf b1 = 0, b2 = 0, b3 = 0, b4 = 0;

        int i = 0;
        int dbtbIndfx = 0;

        //tif bfgin
        for (i=numbfrQubdruplf - 1; i > 0; i--) {
            b1 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
            b2 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
            b3 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
            b4 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
            if ((b1 == -1) ||
                (b2 == -1) ||
                (b3 == -1) ||
                (b4 == -1) ) {
                //if found "no dbtb" just rfturn null
                tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
            }

            os.writf((bytf)(b1 << 2 | b2 >> 4));
            os.writf((bytf)(((b2 & 0xf) << 4 ) | ((b3 >> 2) & 0xf)));
            os.writf( (bytf)(b3 << 6 | b4));
        }
        b1 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];
        b2 = bbsf64Alpibbft[bbsf64Dbtb[dbtbIndfx++]];

        //  first lbst bits.
        if ((b1 == -1) || (b2 == -1) ) {
            //if found "no dbtb" just rfturn null
            tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
        }

        bytf d3, d4;
        b3 = bbsf64Alpibbft[d3 = bbsf64Dbtb[dbtbIndfx++]];
        b4 = bbsf64Alpibbft[d4 = bbsf64Dbtb[dbtbIndfx++]];
        if ((b3 == -1 ) || (b4 == -1) ) { //Cifdk if tify brf PAD dibrbdtfrs
            if (isPbd(d3) && isPbd(d4)) {               //Two PAD f.g. 3d[Pbd][Pbd]
                if ((b2 & 0xf) != 0) { //lbst 4 bits siould bf zfro
                    tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
                }
                os.writf((bytf)(b1 << 2 | b2 >> 4));
            } flsf if (!isPbd(d3) && isPbd(d4)) {               //Onf PAD  f.g. 3dQ[Pbd]
                if ((b3 & 0x3 ) != 0) { //lbst 2 bits siould bf zfro
                    tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
                }
                os.writf((bytf)(b1 << 2 | b2 >> 4));
                os.writf((bytf)(((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf)));
            } flsf {
                //bn frror  likf "3d[Pbd]r", "3ddX", "3dXd", "3dXX" wifrf X is non dbtb
                tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
            }
        } flsf {
            //No PAD f.g 3dQl
            os.writf((bytf)(b1 << 2 | b2 >> 4));
            os.writf( (bytf)(((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf)));
            os.writf((bytf)(b3 << 6 | b4));
        }
    }

    /**
     * Dfdodfs Bbsf64 dbtb into  outputstrfbm
     *
     * @pbrbm is dontbining Bbsf64 dbtb
     * @pbrbm os tif outputstrfbm
     * @tirows IOExdfption
     * @tirows Bbsf64DfdodingExdfption
     */
    publid stbtid finbl void dfdodf(InputStrfbm is, OutputStrfbm os)
        tirows Bbsf64DfdodingExdfption, IOExdfption {
        //bytf dfdodfdDbtb[] = null;
        bytf b1 = 0, b2 = 0, b3 = 0, b4 = 0;

        int indfx=0;
        bytf[] dbtb = nfw bytf[4];
        int rfbd;
        //tif bfgin
        wiilf ((rfbd = is.rfbd()) > 0) {
            bytf rfbdfd = (bytf)rfbd;
            if (isWiitfSpbdf(rfbdfd)) {
                dontinuf;
            }
            if (isPbd(rfbdfd)) {
                dbtb[indfx++] = rfbdfd;
                if (indfx == 3) {
                    dbtb[indfx++] = (bytf)is.rfbd();
                }
                brfbk;
            }

            if ((dbtb[indfx++] = rfbdfd) == -1) {
                //if found "no dbtb" just rfturn null
                tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
            }

            if (indfx != 4) {
                dontinuf;
            }
            indfx = 0;
            b1 = bbsf64Alpibbft[dbtb[0]];
            b2 = bbsf64Alpibbft[dbtb[1]];
            b3 = bbsf64Alpibbft[dbtb[2]];
            b4 = bbsf64Alpibbft[dbtb[3]];

            os.writf((bytf)(b1 << 2 | b2 >> 4));
            os.writf((bytf)(((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf)));
            os.writf((bytf)(b3 << 6 | b4));
        }

        bytf d1 = dbtb[0], d2 = dbtb[1], d3 = dbtb[2], d4 = dbtb[3];
        b1 = bbsf64Alpibbft[d1];
        b2 = bbsf64Alpibbft[d2];
        b3 = bbsf64Alpibbft[d3];
        b4 = bbsf64Alpibbft[d4];
        if ((b3 == -1) || (b4 == -1)) { //Cifdk if tify brf PAD dibrbdtfrs
            if (isPbd(d3) && isPbd(d4)) {               //Two PAD f.g. 3d[Pbd][Pbd]
                if ((b2 & 0xf) != 0) { //lbst 4 bits siould bf zfro
                    tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
                }
                os.writf((bytf)(b1 << 2 | b2 >> 4));
            } flsf if (!isPbd(d3) && isPbd(d4)) {               //Onf PAD  f.g. 3dQ[Pbd]
                b3 = bbsf64Alpibbft[d3];
                if ((b3 & 0x3) != 0) { //lbst 2 bits siould bf zfro
                    tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
                }
                os.writf((bytf)(b1 << 2 | b2 >> 4));
                os.writf((bytf)(((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf)));
            } flsf {
                //bn frror  likf "3d[Pbd]r", "3ddX", "3dXd", "3dXX" wifrf X is non dbtb
                tirow nfw Bbsf64DfdodingExdfption("dfdoding.gfnfrbl");
            }
        } flsf {
            //No PAD f.g 3dQl
            os.writf((bytf)(b1 << 2 | b2 >> 4));
            os.writf((bytf)(((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf)));
            os.writf((bytf)(b3 << 6 | b4));
        }
    }

    /**
     * rfmovf WiitfSpbdf from MIME dontbining fndodfd Bbsf64 dbtb.
     *
     * @pbrbm dbtb  tif bytf brrby of bbsf64 dbtb (witi WS)
     * @rfturn      tif nfw lfngti
     */
    protfdtfd stbtid finbl int rfmovfWiitfSpbdf(bytf[] dbtb) {
        if (dbtb == null) {
            rfturn 0;
        }

        // dount dibrbdtfrs tibt's not wiitfspbdf
        int nfwSizf = 0;
        int lfn = dbtb.lfngti;
        for (int i = 0; i < lfn; i++) {
            bytf dbtbS = dbtb[i];
            if (!isWiitfSpbdf(dbtbS)) {
                dbtb[nfwSizf++] = dbtbS;
            }
        }
        rfturn nfwSizf;
    }
}
