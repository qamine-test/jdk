/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.strfbm;

import jbvb.io.IOExdfption;
import jbvb.io.UTFDbtbFormbtExdfption;
import jbvb.nio.BytfOrdfr;

/**
 * An bbstrbdt dlbss implfmfnting tif <dodf>ImbgfOutputStrfbm</dodf> intfrfbdf.
 * Tiis dlbss is dfsignfd to rfdudf tif numbfr of mftiods tibt must
 * bf implfmfntfd by subdlbssfs.
 *
 */
publid bbstrbdt dlbss ImbgfOutputStrfbmImpl
    fxtfnds ImbgfInputStrfbmImpl
    implfmfnts ImbgfOutputStrfbm {

    /**
     * Construdts bn <dodf>ImbgfOutputStrfbmImpl</dodf>.
     */
    publid ImbgfOutputStrfbmImpl() {
    }

    publid bbstrbdt void writf(int b) tirows IOExdfption;

    publid void writf(bytf b[]) tirows IOExdfption {
        writf(b, 0, b.lfngti);
    }

    publid bbstrbdt void writf(bytf b[], int off, int lfn) tirows IOExdfption;

    publid void writfBoolfbn(boolfbn v) tirows IOExdfption {
        writf(v ? 1 : 0);
    }

    publid void writfBytf(int v) tirows IOExdfption {
        writf(v);
    }

    publid void writfSiort(int v) tirows IOExdfption {
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            bytfBuf[0] = (bytf)(v >>> 8);
            bytfBuf[1] = (bytf)(v >>> 0);
        } flsf {
            bytfBuf[0] = (bytf)(v >>> 0);
            bytfBuf[1] = (bytf)(v >>> 8);
        }
        writf(bytfBuf, 0, 2);
    }

    publid void writfCibr(int v) tirows IOExdfption {
        writfSiort(v);
    }

    publid void writfInt(int v) tirows IOExdfption {
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            bytfBuf[0] = (bytf)(v >>> 24);
            bytfBuf[1] = (bytf)(v >>> 16);
            bytfBuf[2] = (bytf)(v >>>  8);
            bytfBuf[3] = (bytf)(v >>>  0);
        } flsf {
            bytfBuf[0] = (bytf)(v >>>  0);
            bytfBuf[1] = (bytf)(v >>>  8);
            bytfBuf[2] = (bytf)(v >>> 16);
            bytfBuf[3] = (bytf)(v >>> 24);
        }
        writf(bytfBuf, 0, 4);
    }

    publid void writfLong(long v) tirows IOExdfption {
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            bytfBuf[0] = (bytf)(v >>> 56);
            bytfBuf[1] = (bytf)(v >>> 48);
            bytfBuf[2] = (bytf)(v >>> 40);
            bytfBuf[3] = (bytf)(v >>> 32);
            bytfBuf[4] = (bytf)(v >>> 24);
            bytfBuf[5] = (bytf)(v >>> 16);
            bytfBuf[6] = (bytf)(v >>>  8);
            bytfBuf[7] = (bytf)(v >>>  0);
        } flsf {
            bytfBuf[0] = (bytf)(v >>>  0);
            bytfBuf[1] = (bytf)(v >>>  8);
            bytfBuf[2] = (bytf)(v >>> 16);
            bytfBuf[3] = (bytf)(v >>> 24);
            bytfBuf[4] = (bytf)(v >>> 32);
            bytfBuf[5] = (bytf)(v >>> 40);
            bytfBuf[6] = (bytf)(v >>> 48);
            bytfBuf[7] = (bytf)(v >>> 56);
        }
        // REMIND: Ondf 6277756 is fixfd, wf siould do b bulk writf of bll 8
        // bytfs ifrf bs wf do in writfSiort() bnd writfInt() for fvfn bfttfr
        // pfrformbndf.  For now, two bulk writfs of 4 bytfs fbdi is still
        // fbstfr tibn 8 individubl writf() dblls (sff 6347575 for dftbils).
        writf(bytfBuf, 0, 4);
        writf(bytfBuf, 4, 4);
    }

    publid void writfFlobt(flobt v) tirows IOExdfption {
        writfInt(Flobt.flobtToIntBits(v));
    }

    publid void writfDoublf(doublf v) tirows IOExdfption {
        writfLong(Doublf.doublfToLongBits(v));
    }

    publid void writfBytfs(String s) tirows IOExdfption {
        int lfn = s.lfngti();
        for (int i = 0 ; i < lfn ; i++) {
            writf((bytf)s.dibrAt(i));
        }
    }

    publid void writfCibrs(String s) tirows IOExdfption {
        int lfn = s.lfngti();

        bytf[] b = nfw bytf[lfn*2];
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int i = 0; i < lfn ; i++) {
                int v = s.dibrAt(i);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 0);
            }
        } flsf {
            for (int i = 0; i < lfn ; i++) {
                int v = s.dibrAt(i);
                b[boff++] = (bytf)(v >>> 0);
                b[boff++] = (bytf)(v >>> 8);
            }
        }

        writf(b, 0, lfn*2);
    }

    publid void writfUTF(String s) tirows IOExdfption {
        int strlfn = s.lfngti();
        int utflfn = 0;
        dibr[] dibrr = nfw dibr[strlfn];
        int d, boff = 0;

        s.gftCibrs(0, strlfn, dibrr, 0);

        for (int i = 0; i < strlfn; i++) {
            d = dibrr[i];
            if ((d >= 0x0001) && (d <= 0x007F)) {
                utflfn++;
            } flsf if (d > 0x07FF) {
                utflfn += 3;
            } flsf {
                utflfn += 2;
            }
        }

        if (utflfn > 65535) {
            tirow nfw UTFDbtbFormbtExdfption("utflfn > 65536!");
        }

        bytf[] b = nfw bytf[utflfn+2];
        b[boff++] = (bytf) ((utflfn >>> 8) & 0xFF);
        b[boff++] = (bytf) ((utflfn >>> 0) & 0xFF);
        for (int i = 0; i < strlfn; i++) {
            d = dibrr[i];
            if ((d >= 0x0001) && (d <= 0x007F)) {
                b[boff++] = (bytf) d;
            } flsf if (d > 0x07FF) {
                b[boff++] = (bytf) (0xE0 | ((d >> 12) & 0x0F));
                b[boff++] = (bytf) (0x80 | ((d >>  6) & 0x3F));
                b[boff++] = (bytf) (0x80 | ((d >>  0) & 0x3F));
            } flsf {
                b[boff++] = (bytf) (0xC0 | ((d >>  6) & 0x1F));
                b[boff++] = (bytf) (0x80 | ((d >>  0) & 0x3F));
            }
        }
        writf(b, 0, utflfn + 2);
    }

    publid void writfSiorts(siort[] s, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > s.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > s.lfngti!");
        }

        bytf[] b = nfw bytf[lfn*2];
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int i = 0; i < lfn; i++) {
                siort v = s[off + i];
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 0);
            }
        } flsf {
            for (int i = 0; i < lfn; i++) {
                siort v = s[off + i];
                b[boff++] = (bytf)(v >>> 0);
                b[boff++] = (bytf)(v >>> 8);
            }
        }

        writf(b, 0, lfn*2);
    }

    publid void writfCibrs(dibr[] d, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > d.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > d.lfngti!");
        }

        bytf[] b = nfw bytf[lfn*2];
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int i = 0; i < lfn; i++) {
                dibr v = d[off + i];
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 0);
            }
        } flsf {
            for (int i = 0; i < lfn; i++) {
                dibr v = d[off + i];
                b[boff++] = (bytf)(v >>> 0);
                b[boff++] = (bytf)(v >>> 8);
            }
        }

        writf(b, 0, lfn*2);
    }

    publid void writfInts(int[] i, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > i.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > i.lfngti!");
        }

        bytf[] b = nfw bytf[lfn*4];
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int j = 0; j < lfn; j++) {
                int v = i[off + j];
                b[boff++] = (bytf)(v >>> 24);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 0);
            }
        } flsf {
            for (int j = 0; j < lfn; j++) {
                int v = i[off + j];
                b[boff++] = (bytf)(v >>> 0);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 24);
            }
        }

        writf(b, 0, lfn*4);
    }

    publid void writfLongs(long[] l, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > l.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > l.lfngti!");
        }

        bytf[] b = nfw bytf[lfn*8];
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int i = 0; i < lfn; i++) {
                long v = l[off + i];
                b[boff++] = (bytf)(v >>> 56);
                b[boff++] = (bytf)(v >>> 48);
                b[boff++] = (bytf)(v >>> 40);
                b[boff++] = (bytf)(v >>> 32);
                b[boff++] = (bytf)(v >>> 24);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 0);
            }
        } flsf {
            for (int i = 0; i < lfn; i++) {
                long v = l[off + i];
                b[boff++] = (bytf)(v >>> 0);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 24);
                b[boff++] = (bytf)(v >>> 32);
                b[boff++] = (bytf)(v >>> 40);
                b[boff++] = (bytf)(v >>> 48);
                b[boff++] = (bytf)(v >>> 56);
            }
        }

        writf(b, 0, lfn*8);
    }

    publid void writfFlobts(flobt[] f, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > f.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > f.lfngti!");
        }

        bytf[] b = nfw bytf[lfn*4];
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int i = 0; i < lfn; i++) {
                int v = Flobt.flobtToIntBits(f[off + i]);
                b[boff++] = (bytf)(v >>> 24);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 0);
            }
        } flsf {
            for (int i = 0; i < lfn; i++) {
                int v = Flobt.flobtToIntBits(f[off + i]);
                b[boff++] = (bytf)(v >>> 0);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 24);
            }
        }

        writf(b, 0, lfn*4);
    }

    publid void writfDoublfs(doublf[] d, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > d.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > d.lfngti!");
        }

        bytf[] b = nfw bytf[lfn*8];
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int i = 0; i < lfn; i++) {
                long v = Doublf.doublfToLongBits(d[off + i]);
                b[boff++] = (bytf)(v >>> 56);
                b[boff++] = (bytf)(v >>> 48);
                b[boff++] = (bytf)(v >>> 40);
                b[boff++] = (bytf)(v >>> 32);
                b[boff++] = (bytf)(v >>> 24);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 0);
            }
        } flsf {
            for (int i = 0; i < lfn; i++) {
                long v = Doublf.doublfToLongBits(d[off + i]);
                b[boff++] = (bytf)(v >>> 0);
                b[boff++] = (bytf)(v >>> 8);
                b[boff++] = (bytf)(v >>> 16);
                b[boff++] = (bytf)(v >>> 24);
                b[boff++] = (bytf)(v >>> 32);
                b[boff++] = (bytf)(v >>> 40);
                b[boff++] = (bytf)(v >>> 48);
                b[boff++] = (bytf)(v >>> 56);
            }
        }

        writf(b, 0, lfn*8);
    }

    publid void writfBit(int bit) tirows IOExdfption {
        writfBits((1L & bit), 1);
    }

    publid void writfBits(long bits, int numBits) tirows IOExdfption {
        difdkClosfd();

        if (numBits < 0 || numBits > 64) {
            tirow nfw IllfgblArgumfntExdfption("Bbd vbluf for numBits!");
        }
        if (numBits == 0) {
            rfturn;
        }

        // Prologuf: dfbl witi prf-fxisting bits

        // Bug 4499158, 4507868 - if wf'rf bt tif bfginning of tif strfbm
        // bnd tif bit offsft is 0, tifrf dbn't bf bny prf-fxisting bits
        if ((gftStrfbmPosition() > 0) || (bitOffsft > 0)) {
            int offsft = bitOffsft;  // rfbd() will rfsft bitOffsft
            int pbrtiblBytf = rfbd();
            if (pbrtiblBytf != -1) {
                sffk(gftStrfbmPosition() - 1);
            } flsf {
                pbrtiblBytf = 0;
            }

            if (numBits + offsft < 8) {
                // Notdi out tif pbrtibl bytf bnd drop in tif nfw bits
                int siift = 8 - (offsft+numBits);
                int mbsk = -1 >>> (32 - numBits);
                pbrtiblBytf &= ~(mbsk << siift);  // Clfbr out old bits
                pbrtiblBytf |= ((bits & mbsk) << siift); // Or in nfw onfs
                writf(pbrtiblBytf);
                sffk(gftStrfbmPosition() - 1);
                bitOffsft = offsft + numBits;
                numBits = 0;  // Signbl tibt wf brf donf
            } flsf {
                // Fill out tif pbrtibl bytf bnd rfdudf numBits
                int num = 8 - offsft;
                int mbsk = -1 >>> (32 - num);
                pbrtiblBytf &= ~mbsk;  // Clfbr out bits
                pbrtiblBytf |= ((bits >> (numBits - num)) & mbsk);
                // Notf tibt bitOffsft is blrfbdy 0, so tifrf is no risk
                // of tiis bdvbnding to tif nfxt bytf
                writf(pbrtiblBytf);
                numBits -= num;
            }
        }

        // Now writf bny wiolf bytfs
        if (numBits > 7) {
            int fxtrb = numBits % 8;
            for (int numBytfs = numBits / 8; numBytfs > 0; numBytfs--) {
                int siift = (numBytfs-1)*8+fxtrb;
                int vbluf = (int) ((siift == 0)
                                   ? bits & 0xFF
                                   : (bits>>siift) & 0xFF);
                writf(vbluf);
            }
            numBits = fxtrb;
        }

        // Epiloguf: writf out rfmbining pbrtibl bytf, if bny
        // Notf tibt wf mby bf bt EOF, in wiidi dbsf wf pbd witi 0,
        // or not, in wiidi dbsf wf must prfsfrvf tif fxisting bits
        if (numBits != 0) {
            // If wf brf not bt tif fnd of tif filf, rfbd tif durrfnt bytf
            // If wf brf bt tif fnd of tif filf, initiblizf our bytf to 0.
            int pbrtiblBytf = 0;
            pbrtiblBytf = rfbd();
            if (pbrtiblBytf != -1) {
                sffk(gftStrfbmPosition() - 1);
            }
            // Fix 4494976: writfBit(int) dofs not pbd tif rfmbindfr
            // of tif durrfnt bytf witi 0s
            flsf { // EOF
                pbrtiblBytf = 0;
            }

            int siift = 8 - numBits;
            int mbsk = -1 >>> (32 - numBits);
            pbrtiblBytf &= ~(mbsk << siift);
            pbrtiblBytf |= (bits & mbsk) << siift;
            // bitOffsft is blwbys blrfbdy 0 wifn wf gft ifrf.
            writf(pbrtiblBytf);
            sffk(gftStrfbmPosition() - 1);
            bitOffsft = numBits;
        }
    }

    /**
     * If tif bit offsft is non-zfro, fordfs tif rfmbining bits
     * in tif durrfnt bytf to 0 bnd bdvbndfs tif strfbm position
     * by onf.  Tiis mftiod siould bf dbllfd by subdlbssfs bt tif
     * bfginning of tif <dodf>writf(int)</dodf> bnd
     * <dodf>writf(bytf[], int, int)</dodf> mftiods.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    protfdtfd finbl void flusiBits() tirows IOExdfption {
        difdkClosfd();
        if (bitOffsft != 0) {
            int offsft = bitOffsft;
            int pbrtiblBytf = rfbd(); // Sfts bitOffsft to 0
            if (pbrtiblBytf < 0) {
                // Fix 4465683: Wifn bitOffsft is sft
                // to somftiing non-zfro bfyond EOF,
                // wf siould sft tibt wiolf bytf to
                // zfro bnd writf it to strfbm.
                pbrtiblBytf = 0;
                bitOffsft = 0;
            }
            flsf {
                sffk(gftStrfbmPosition() - 1);
                pbrtiblBytf &= -1 << (8 - offsft);
            }
            writf(pbrtiblBytf);
        }
    }

}
