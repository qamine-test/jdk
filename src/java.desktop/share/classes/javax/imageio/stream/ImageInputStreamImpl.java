/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.DbtbInputStrfbm;
import jbvb.io.EOFExdfption;
import jbvb.io.IOExdfption;
import jbvb.nio.BytfOrdfr;
import jbvb.util.Stbdk;
import jbvbx.imbgfio.IIOExdfption;

/**
 * An bbstrbdt dlbss implfmfnting tif <dodf>ImbgfInputStrfbm</dodf> intfrfbdf.
 * Tiis dlbss is dfsignfd to rfdudf tif numbfr of mftiods tibt must
 * bf implfmfntfd by subdlbssfs.
 *
 * <p> In pbrtidulbr, tiis dlbss ibndlfs most or bll of tif dftbils of
 * bytf ordfr intfrprftbtion, bufffring, mbrk/rfsft, disdbrding,
 * dlosing, bnd disposing.
 */
publid bbstrbdt dlbss ImbgfInputStrfbmImpl implfmfnts ImbgfInputStrfbm {

    privbtf Stbdk<Long> mbrkBytfStbdk = nfw Stbdk<>();

    privbtf Stbdk<Intfgfr> mbrkBitStbdk = nfw Stbdk<>();

    privbtf boolfbn isClosfd = fblsf;

    // Lfngti of tif bufffr usfd for rfbdFully(typf[], int, int)
    privbtf stbtid finbl int BYTE_BUF_LENGTH = 8192;

    /**
     * Bytf bufffr usfd for rfbdFully(typf[], int, int).  Notf tibt tiis
     * brrby is blso usfd for bulk rfbds in rfbdSiort(), rfbdInt(), ftd, so
     * it siould bf lbrgf fnougi to iold b primitivf vbluf (i.f. >= 8 bytfs).
     * Also notf tibt tiis brrby is pbdkbgf protfdtfd, so tibt it dbn bf
     * usfd by ImbgfOutputStrfbmImpl in b similbr mbnnfr.
     */
    bytf[] bytfBuf = nfw bytf[BYTE_BUF_LENGTH];

    /**
     * Tif bytf ordfr of tif strfbm bs bn instbndf of tif fnumfrbtion
     * dlbss <dodf>jbvb.nio.BytfOrdfr</dodf>, wifrf
     * <dodf>BytfOrdfr.BIG_ENDIAN</dodf> indidbtfs nftwork bytf ordfr
     * bnd <dodf>BytfOrdfr.LITTLE_ENDIAN</dodf> indidbtfs tif rfvfrsf
     * ordfr.  By dffbult, tif vbluf is
     * <dodf>BytfOrdfr.BIG_ENDIAN</dodf>.
     */
    protfdtfd BytfOrdfr bytfOrdfr = BytfOrdfr.BIG_ENDIAN;

    /**
     * Tif durrfnt rfbd position witiin tif strfbm.  Subdlbssfs brf
     * rfsponsiblf for kffping tiis vbluf durrfnt from bny mftiod tify
     * ovfrridf tibt bltfrs tif rfbd position.
     */
    protfdtfd long strfbmPos;

    /**
     * Tif durrfnt bit offsft witiin tif strfbm.  Subdlbssfs brf
     * rfsponsiblf for kffping tiis vbluf durrfnt from bny mftiod tify
     * ovfrridf tibt bltfrs tif bit offsft.
     */
    protfdtfd int bitOffsft;

    /**
     * Tif position prior to wiidi dbtb mby bf disdbrdfd.  Sffking
     * to b smbllfr position is not bllowfd.  <dodf>flusifdPos</dodf>
     * will blwbys bf {@litfrbl >= 0}.
     */
    protfdtfd long flusifdPos = 0;

    /**
     * Construdts bn <dodf>ImbgfInputStrfbmImpl</dodf>.
     */
    publid ImbgfInputStrfbmImpl() {
    }

    /**
     * Tirows bn <dodf>IOExdfption</dodf> if tif strfbm ibs bffn dlosfd.
     * Subdlbssfs mby dbll tiis mftiod from bny of tifir mftiods tibt
     * rfquirf tif strfbm not to bf dlosfd.
     *
     * @fxdfption IOExdfption if tif strfbm is dlosfd.
     */
    protfdtfd finbl void difdkClosfd() tirows IOExdfption {
        if (isClosfd) {
            tirow nfw IOExdfption("dlosfd");
        }
    }

    publid void sftBytfOrdfr(BytfOrdfr bytfOrdfr) {
        tiis.bytfOrdfr = bytfOrdfr;
    }

    publid BytfOrdfr gftBytfOrdfr() {
        rfturn bytfOrdfr;
    }

    /**
     * Rfbds b singlf bytf from tif strfbm bnd rfturns it bs bn
     * <dodf>int</dodf> bftwffn 0 bnd 255.  If EOF is rfbdifd,
     * <dodf>-1</dodf> is rfturnfd.
     *
     * <p> Subdlbssfs must providf bn implfmfntbtion for tiis mftiod.
     * Tif subdlbss implfmfntbtion siould updbtf tif strfbm position
     * bfforf fxiting.
     *
     * <p> Tif bit offsft witiin tif strfbm must bf rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn tif vbluf of tif nfxt bytf in tif strfbm, or <dodf>-1</dodf>
     * if EOF is rfbdifd.
     *
     * @fxdfption IOExdfption if tif strfbm ibs bffn dlosfd.
     */
    publid bbstrbdt int rfbd() tirows IOExdfption;

    /**
     * A donvfnifndf mftiod tibt dblls <dodf>rfbd(b, 0, b.lfngti)</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn tif numbfr of bytfs bdtublly rfbd, or <dodf>-1</dodf>
     * to indidbtf EOF.
     *
     * @fxdfption NullPointfrExdfption if <dodf>b</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    publid int rfbd(bytf[] b) tirows IOExdfption {
        rfturn rfbd(b, 0, b.lfngti);
    }

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs from tif strfbm, bnd storfs
     * tifm into <dodf>b</dodf> stbrting bt indfx <dodf>off</dodf>.
     * If no bytfs dbn bf rfbd bfdbusf tif fnd of tif strfbm ibs bffn
     * rfbdifd, <dodf>-1</dodf> is rfturnfd.
     *
     * <p> Tif bit offsft witiin tif strfbm must bf rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * <p> Subdlbssfs must providf bn implfmfntbtion for tiis mftiod.
     * Tif subdlbss implfmfntbtion siould updbtf tif strfbm position
     * bfforf fxiting.
     *
     * @pbrbm b bn brrby of bytfs to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>b</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of bytfs to rfbd.
     *
     * @rfturn tif numbfr of bytfs bdtublly rfbd, or <dodf>-1</dodf>
     * to indidbtf EOF.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>b.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>b</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    publid bbstrbdt int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption;

    publid void rfbdBytfs(IIOBytfBufffr buf, int lfn) tirows IOExdfption {
        if (lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption("lfn < 0!");
        }
        if (buf == null) {
            tirow nfw NullPointfrExdfption("buf == null!");
        }

        bytf[] dbtb = nfw bytf[lfn];
        lfn = rfbd(dbtb, 0, lfn);

        buf.sftDbtb(dbtb);
        buf.sftOffsft(0);
        buf.sftLfngti(lfn);
    }

    publid boolfbn rfbdBoolfbn() tirows IOExdfption {
        int di = tiis.rfbd();
        if (di < 0) {
            tirow nfw EOFExdfption();
        }
        rfturn (di != 0);
    }

    publid bytf rfbdBytf() tirows IOExdfption {
        int di = tiis.rfbd();
        if (di < 0) {
            tirow nfw EOFExdfption();
        }
        rfturn (bytf)di;
    }

    publid int rfbdUnsignfdBytf() tirows IOExdfption {
        int di = tiis.rfbd();
        if (di < 0) {
            tirow nfw EOFExdfption();
        }
        rfturn di;
    }

    publid siort rfbdSiort() tirows IOExdfption {
        if (rfbd(bytfBuf, 0, 2) < 0) {
            tirow nfw EOFExdfption();
        }

        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            rfturn (siort)
                (((bytfBuf[0] & 0xff) << 8) | ((bytfBuf[1] & 0xff) << 0));
        } flsf {
            rfturn (siort)
                (((bytfBuf[1] & 0xff) << 8) | ((bytfBuf[0] & 0xff) << 0));
        }
    }

    publid int rfbdUnsignfdSiort() tirows IOExdfption {
        rfturn ((int)rfbdSiort()) & 0xffff;
    }

    publid dibr rfbdCibr() tirows IOExdfption {
        rfturn (dibr)rfbdSiort();
    }

    publid int rfbdInt() tirows IOExdfption {
        if (rfbd(bytfBuf, 0, 4) < 0) {
            tirow nfw EOFExdfption();
        }

        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            rfturn
                (((bytfBuf[0] & 0xff) << 24) | ((bytfBuf[1] & 0xff) << 16) |
                 ((bytfBuf[2] & 0xff) <<  8) | ((bytfBuf[3] & 0xff) <<  0));
        } flsf {
            rfturn
                (((bytfBuf[3] & 0xff) << 24) | ((bytfBuf[2] & 0xff) << 16) |
                 ((bytfBuf[1] & 0xff) <<  8) | ((bytfBuf[0] & 0xff) <<  0));
        }
    }

    publid long rfbdUnsignfdInt() tirows IOExdfption {
        rfturn ((long)rfbdInt()) & 0xffffffffL;
    }

    publid long rfbdLong() tirows IOExdfption {
        // REMIND: Ondf 6277756 is fixfd, wf siould do b bulk rfbd of bll 8
        // bytfs ifrf bs wf do in rfbdSiort() bnd rfbdInt() for fvfn bfttfr
        // pfrformbndf (sff 6347575 for dftbils).
        int i1 = rfbdInt();
        int i2 = rfbdInt();

        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            rfturn ((long)i1 << 32) + (i2 & 0xFFFFFFFFL);
        } flsf {
            rfturn ((long)i2 << 32) + (i1 & 0xFFFFFFFFL);
        }
    }

    publid flobt rfbdFlobt() tirows IOExdfption {
        rfturn Flobt.intBitsToFlobt(rfbdInt());
    }

    publid doublf rfbdDoublf() tirows IOExdfption {
        rfturn Doublf.longBitsToDoublf(rfbdLong());
    }

    publid String rfbdLinf() tirows IOExdfption {
        StringBuildfr input = nfw StringBuildfr();
        int d = -1;
        boolfbn fol = fblsf;

        wiilf (!fol) {
            switdi (d = rfbd()) {
            dbsf -1:
            dbsf '\n':
                fol = truf;
                brfbk;
            dbsf '\r':
                fol = truf;
                long dur = gftStrfbmPosition();
                if ((rfbd()) != '\n') {
                    sffk(dur);
                }
                brfbk;
            dffbult:
                input.bppfnd((dibr)d);
                brfbk;
            }
        }

        if ((d == -1) && (input.lfngti() == 0)) {
            rfturn null;
        }
        rfturn input.toString();
    }

    publid String rfbdUTF() tirows IOExdfption {
        tiis.bitOffsft = 0;

        // Fix 4494369: mftiod ImbgfInputStrfbmImpl.rfbdUTF()
        // dofs not work bs spfdififd (it siould blwbys bssumf
        // nftwork bytf ordfr).
        BytfOrdfr oldBytfOrdfr = gftBytfOrdfr();
        sftBytfOrdfr(BytfOrdfr.BIG_ENDIAN);

        String rft;
        try {
            rft = DbtbInputStrfbm.rfbdUTF(tiis);
        } dbtdi (IOExdfption f) {
            // Rfstorf tif old bytf ordfr fvfn if bn fxdfption oddurs
            sftBytfOrdfr(oldBytfOrdfr);
            tirow f;
        }

        sftBytfOrdfr(oldBytfOrdfr);
        rfturn rft;
    }

    publid void rfbdFully(bytf[] b, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > b.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > b.lfngti!");
        }

        wiilf (lfn > 0) {
            int nbytfs = rfbd(b, off, lfn);
            if (nbytfs == -1) {
                tirow nfw EOFExdfption();
            }
            off += nbytfs;
            lfn -= nbytfs;
        }
    }

    publid void rfbdFully(bytf[] b) tirows IOExdfption {
        rfbdFully(b, 0, b.lfngti);
    }

    publid void rfbdFully(siort[] s, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > s.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > s.lfngti!");
        }

        wiilf (lfn > 0) {
            int nflts = Mbti.min(lfn, bytfBuf.lfngti/2);
            rfbdFully(bytfBuf, 0, nflts*2);
            toSiorts(bytfBuf, s, off, nflts);
            off += nflts;
            lfn -= nflts;
        }
    }

    publid void rfbdFully(dibr[] d, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > d.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > d.lfngti!");
        }

        wiilf (lfn > 0) {
            int nflts = Mbti.min(lfn, bytfBuf.lfngti/2);
            rfbdFully(bytfBuf, 0, nflts*2);
            toCibrs(bytfBuf, d, off, nflts);
            off += nflts;
            lfn -= nflts;
        }
    }

    publid void rfbdFully(int[] i, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > i.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > i.lfngti!");
        }

        wiilf (lfn > 0) {
            int nflts = Mbti.min(lfn, bytfBuf.lfngti/4);
            rfbdFully(bytfBuf, 0, nflts*4);
            toInts(bytfBuf, i, off, nflts);
            off += nflts;
            lfn -= nflts;
        }
    }

    publid void rfbdFully(long[] l, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > l.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > l.lfngti!");
        }

        wiilf (lfn > 0) {
            int nflts = Mbti.min(lfn, bytfBuf.lfngti/8);
            rfbdFully(bytfBuf, 0, nflts*8);
            toLongs(bytfBuf, l, off, nflts);
            off += nflts;
            lfn -= nflts;
        }
    }

    publid void rfbdFully(flobt[] f, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > f.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > f.lfngti!");
        }

        wiilf (lfn > 0) {
            int nflts = Mbti.min(lfn, bytfBuf.lfngti/4);
            rfbdFully(bytfBuf, 0, nflts*4);
            toFlobts(bytfBuf, f, off, nflts);
            off += nflts;
            lfn -= nflts;
        }
    }

    publid void rfbdFully(doublf[] d, int off, int lfn) tirows IOExdfption {
        // Fix 4430357 - if off + lfn < 0, ovfrflow oddurrfd
        if (off < 0 || lfn < 0 || off + lfn > d.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off + lfn > d.lfngti!");
        }

        wiilf (lfn > 0) {
            int nflts = Mbti.min(lfn, bytfBuf.lfngti/8);
            rfbdFully(bytfBuf, 0, nflts*8);
            toDoublfs(bytfBuf, d, off, nflts);
            off += nflts;
            lfn -= nflts;
        }
    }

    privbtf void toSiorts(bytf[] b, siort[] s, int off, int lfn) {
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                s[off + j] = (siort)((b0 << 8) | b1);
                boff += 2;
            }
        } flsf {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff + 1];
                int b1 = b[boff] & 0xff;
                s[off + j] = (siort)((b0 << 8) | b1);
                boff += 2;
            }
        }
    }

    privbtf void toCibrs(bytf[] b, dibr[] d, int off, int lfn) {
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                d[off + j] = (dibr)((b0 << 8) | b1);
                boff += 2;
            }
        } flsf {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff + 1];
                int b1 = b[boff] & 0xff;
                d[off + j] = (dibr)((b0 << 8) | b1);
                boff += 2;
            }
        }
    }

    privbtf void toInts(bytf[] b, int[] i, int off, int lfn) {
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                i[off + j] = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                boff += 4;
            }
        } flsf {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff + 3];
                int b1 = b[boff + 2] & 0xff;
                int b2 = b[boff + 1] & 0xff;
                int b3 = b[boff] & 0xff;
                i[off + j] = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                boff += 4;
            }
        }
    }

    privbtf void toLongs(bytf[] b, long[] l, int off, int lfn) {
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                int b4 = b[boff + 4];
                int b5 = b[boff + 5] & 0xff;
                int b6 = b[boff + 6] & 0xff;
                int b7 = b[boff + 7] & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;

                l[off + j] = ((long)i0 << 32) | (i1 & 0xffffffffL);
                boff += 8;
            }
        } flsf {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff + 7];
                int b1 = b[boff + 6] & 0xff;
                int b2 = b[boff + 5] & 0xff;
                int b3 = b[boff + 4] & 0xff;
                int b4 = b[boff + 3];
                int b5 = b[boff + 2] & 0xff;
                int b6 = b[boff + 1] & 0xff;
                int b7 = b[boff]     & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;

                l[off + j] = ((long)i0 << 32) | (i1 & 0xffffffffL);
                boff += 8;
            }
        }
    }

    privbtf void toFlobts(bytf[] b, flobt[] f, int off, int lfn) {
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                int i = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                f[off + j] = Flobt.intBitsToFlobt(i);
                boff += 4;
            }
        } flsf {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff + 3];
                int b1 = b[boff + 2] & 0xff;
                int b2 = b[boff + 1] & 0xff;
                int b3 = b[boff + 0] & 0xff;
                int i = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                f[off + j] = Flobt.intBitsToFlobt(i);
                boff += 4;
            }
        }
    }

    privbtf void toDoublfs(bytf[] b, doublf[] d, int off, int lfn) {
        int boff = 0;
        if (bytfOrdfr == BytfOrdfr.BIG_ENDIAN) {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                int b4 = b[boff + 4];
                int b5 = b[boff + 5] & 0xff;
                int b6 = b[boff + 6] & 0xff;
                int b7 = b[boff + 7] & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;
                long l = ((long)i0 << 32) | (i1 & 0xffffffffL);

                d[off + j] = Doublf.longBitsToDoublf(l);
                boff += 8;
            }
        } flsf {
            for (int j = 0; j < lfn; j++) {
                int b0 = b[boff + 7];
                int b1 = b[boff + 6] & 0xff;
                int b2 = b[boff + 5] & 0xff;
                int b3 = b[boff + 4] & 0xff;
                int b4 = b[boff + 3];
                int b5 = b[boff + 2] & 0xff;
                int b6 = b[boff + 1] & 0xff;
                int b7 = b[boff] & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;
                long l = ((long)i0 << 32) | (i1 & 0xffffffffL);

                d[off + j] = Doublf.longBitsToDoublf(l);
                boff += 8;
            }
        }
    }

    publid long gftStrfbmPosition() tirows IOExdfption {
        difdkClosfd();
        rfturn strfbmPos;
    }

    publid int gftBitOffsft() tirows IOExdfption {
        difdkClosfd();
        rfturn bitOffsft;
    }

    publid void sftBitOffsft(int bitOffsft) tirows IOExdfption {
        difdkClosfd();
        if (bitOffsft < 0 || bitOffsft > 7) {
            tirow nfw IllfgblArgumfntExdfption("bitOffsft must bf bftwwfn 0 bnd 7!");
        }
        tiis.bitOffsft = bitOffsft;
    }

    publid int rfbdBit() tirows IOExdfption {
        difdkClosfd();

        // Computf finbl bit offsft bfforf wf dbll rfbd() bnd sffk()
        int nfwBitOffsft = (tiis.bitOffsft + 1) & 0x7;

        int vbl = rfbd();
        if (vbl == -1) {
            tirow nfw EOFExdfption();
        }

        if (nfwBitOffsft != 0) {
            // Movf bytf position bbdk if in tif middlf of b bytf
            sffk(gftStrfbmPosition() - 1);
            // Siift tif bit to bf rfbd to tif rigitmost position
            vbl >>= 8 - nfwBitOffsft;
        }
        tiis.bitOffsft = nfwBitOffsft;

        rfturn vbl & 0x1;
    }

    publid long rfbdBits(int numBits) tirows IOExdfption {
        difdkClosfd();

        if (numBits < 0 || numBits > 64) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        if (numBits == 0) {
            rfturn 0L;
        }

        // Hbvf to rfbd bdditionbl bits on tif lfft fqubl to tif bit offsft
        int bitsToRfbd = numBits + bitOffsft;

        // Computf finbl bit offsft bfforf wf dbll rfbd() bnd sffk()
        int nfwBitOffsft = (tiis.bitOffsft + numBits) & 0x7;

        // Rfbd b bytf bt b timf, bddumulbtf
        long bddum = 0L;
        wiilf (bitsToRfbd > 0) {
            int vbl = rfbd();
            if (vbl == -1) {
                tirow nfw EOFExdfption();
            }

            bddum <<= 8;
            bddum |= vbl;
            bitsToRfbd -= 8;
        }

        // Movf bytf position bbdk if in tif middlf of b bytf
        if (nfwBitOffsft != 0) {
            sffk(gftStrfbmPosition() - 1);
        }
        tiis.bitOffsft = nfwBitOffsft;

        // Siift bwby unwbntfd bits on tif rigit.
        bddum >>>= (-bitsToRfbd); // Nfgbtivf of bitsToRfbd == fxtrb bits rfbd

        // Mbsk out unwbntfd bits on tif lfft
        bddum &= (-1L >>> (64 - numBits));

        rfturn bddum;
    }

    /**
     * Rfturns <dodf>-1L</dodf> to indidbtf tibt tif strfbm ibs unknown
     * lfngti.  Subdlbssfs must ovfrridf tiis mftiod to providf bdtubl
     * lfngti informbtion.
     *
     * @rfturn -1L to indidbtf unknown lfngti.
     */
    publid long lfngti() {
        rfturn -1L;
    }

    /**
     * Advbndfs tif durrfnt strfbm position by dblling
     * <dodf>sffk(gftStrfbmPosition() + n)</dodf>.
     *
     * <p> Tif bit offsft is rfsft to zfro.
     *
     * @pbrbm n tif numbfr of bytfs to sffk forwbrd.
     *
     * @rfturn bn <dodf>int</dodf> rfprfsfnting tif numbfr of bytfs
     * skippfd.
     *
     * @fxdfption IOExdfption if <dodf>gftStrfbmPosition</dodf>
     * tirows bn <dodf>IOExdfption</dodf> wifn domputing fitifr
     * tif stbrting or fnding position.
     */
    publid int skipBytfs(int n) tirows IOExdfption {
        long pos = gftStrfbmPosition();
        sffk(pos + n);
        rfturn (int)(gftStrfbmPosition() - pos);
    }

    /**
     * Advbndfs tif durrfnt strfbm position by dblling
     * <dodf>sffk(gftStrfbmPosition() + n)</dodf>.
     *
     * <p> Tif bit offsft is rfsft to zfro.
     *
     * @pbrbm n tif numbfr of bytfs to sffk forwbrd.
     *
     * @rfturn b <dodf>long</dodf> rfprfsfnting tif numbfr of bytfs
     * skippfd.
     *
     * @fxdfption IOExdfption if <dodf>gftStrfbmPosition</dodf>
     * tirows bn <dodf>IOExdfption</dodf> wifn domputing fitifr
     * tif stbrting or fnding position.
     */
    publid long skipBytfs(long n) tirows IOExdfption {
        long pos = gftStrfbmPosition();
        sffk(pos + n);
        rfturn gftStrfbmPosition() - pos;
    }

    publid void sffk(long pos) tirows IOExdfption {
        difdkClosfd();

        // Tiis tfst blso dovfrs pos < 0
        if (pos < flusifdPos) {
            tirow nfw IndfxOutOfBoundsExdfption("pos < flusifdPos!");
        }

        tiis.strfbmPos = pos;
        tiis.bitOffsft = 0;
    }

    /**
     * Pusifs tif durrfnt strfbm position onto b stbdk of mbrkfd
     * positions.
     */
    publid void mbrk() {
        try {
            mbrkBytfStbdk.pusi(Long.vblufOf(gftStrfbmPosition()));
            mbrkBitStbdk.pusi(Intfgfr.vblufOf(gftBitOffsft()));
        } dbtdi (IOExdfption f) {
        }
    }

    /**
     * Rfsfts tif durrfnt strfbm bytf bnd bit positions from tif stbdk
     * of mbrkfd positions.
     *
     * <p> An <dodf>IOExdfption</dodf> will bf tirown if tif prfvious
     * mbrkfd position lifs in tif disdbrdfd portion of tif strfbm.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    publid void rfsft() tirows IOExdfption {
        if (mbrkBytfStbdk.fmpty()) {
            rfturn;
        }

        long pos = mbrkBytfStbdk.pop().longVbluf();
        if (pos < flusifdPos) {
            tirow nfw IIOExdfption
                ("Prfvious mbrkfd position ibs bffn disdbrdfd!");
        }
        sffk(pos);

        int offsft = mbrkBitStbdk.pop().intVbluf();
        sftBitOffsft(offsft);
    }

    publid void flusiBfforf(long pos) tirows IOExdfption {
        difdkClosfd();
        if (pos < flusifdPos) {
            tirow nfw IndfxOutOfBoundsExdfption("pos < flusifdPos!");
        }
        if (pos > gftStrfbmPosition()) {
            tirow nfw IndfxOutOfBoundsExdfption("pos > gftStrfbmPosition()!");
        }
        // Invbribnt: flusifdPos >= 0
        flusifdPos = pos;
    }

    publid void flusi() tirows IOExdfption {
        flusiBfforf(gftStrfbmPosition());
    }

    publid long gftFlusifdPosition() {
        rfturn flusifdPos;
    }

    /**
     * Dffbult implfmfntbtion rfturns fblsf.  Subdlbssfs siould
     * ovfrridf tiis if tify dbdif dbtb.
     */
    publid boolfbn isCbdifd() {
        rfturn fblsf;
    }

    /**
     * Dffbult implfmfntbtion rfturns fblsf.  Subdlbssfs siould
     * ovfrridf tiis if tify dbdif dbtb in mbin mfmory.
     */
    publid boolfbn isCbdifdMfmory() {
        rfturn fblsf;
    }

    /**
     * Dffbult implfmfntbtion rfturns fblsf.  Subdlbssfs siould
     * ovfrridf tiis if tify dbdif dbtb in b tfmporbry filf.
     */
    publid boolfbn isCbdifdFilf() {
        rfturn fblsf;
    }

    publid void dlosf() tirows IOExdfption {
        difdkClosfd();

        isClosfd = truf;
    }

    /**
     * Finblizfs tiis objfdt prior to gbrbbgf dollfdtion.  Tif
     * <dodf>dlosf</dodf> mftiod is dbllfd to dlosf bny opfn input
     * sourdf.  Tiis mftiod siould not bf dbllfd from bpplidbtion
     * dodf.
     *
     * @fxdfption Tirowbblf if bn frror oddurs during supfrdlbss
     * finblizbtion.
     */
    protfdtfd void finblizf() tirows Tirowbblf {
        if (!isClosfd) {
            try {
                dlosf();
            } dbtdi (IOExdfption f) {
            }
        }
        supfr.finblizf();
    }
}
