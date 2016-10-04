/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * A dbtb output strfbm lfts bn bpplidbtion writf primitivf Jbvb dbtb
 * typfs to bn output strfbm in b portbblf wby. An bpplidbtion dbn
 * tifn usf b dbtb input strfbm to rfbd tif dbtb bbdk in.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.io.DbtbInputStrfbm
 * @sindf   1.0
 */
publid
dlbss DbtbOutputStrfbm fxtfnds FiltfrOutputStrfbm implfmfnts DbtbOutput {
    /**
     * Tif numbfr of bytfs writtfn to tif dbtb output strfbm so fbr.
     * If tiis dountfr ovfrflows, it will bf wrbppfd to Intfgfr.MAX_VALUE.
     */
    protfdtfd int writtfn;

    /**
     * bytfbrr is initiblizfd on dfmbnd by writfUTF
     */
    privbtf bytf[] bytfbrr = null;

    /**
     * Crfbtfs b nfw dbtb output strfbm to writf dbtb to tif spfdififd
     * undfrlying output strfbm. Tif dountfr <dodf>writtfn</dodf> is
     * sft to zfro.
     *
     * @pbrbm   out   tif undfrlying output strfbm, to bf sbvfd for lbtfr
     *                usf.
     * @sff     jbvb.io.FiltfrOutputStrfbm#out
     */
    publid DbtbOutputStrfbm(OutputStrfbm out) {
        supfr(out);
    }

    /**
     * Indrfbsfs tif writtfn dountfr by tif spfdififd vbluf
     * until it rfbdifs Intfgfr.MAX_VALUE.
     */
    privbtf void indCount(int vbluf) {
        int tfmp = writtfn + vbluf;
        if (tfmp < 0) {
            tfmp = Intfgfr.MAX_VALUE;
        }
        writtfn = tfmp;
    }

    /**
     * Writfs tif spfdififd bytf (tif low figit bits of tif brgumfnt
     * <dodf>b</dodf>) to tif undfrlying output strfbm. If no fxdfption
     * is tirown, tif dountfr <dodf>writtfn</dodf> is indrfmfntfd by
     * <dodf>1</dodf>.
     * <p>
     * Implfmfnts tif <dodf>writf</dodf> mftiod of <dodf>OutputStrfbm</dodf>.
     *
     * @pbrbm      b   tif <dodf>bytf</dodf> to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid syndironizfd void writf(int b) tirows IOExdfption {
        out.writf(b);
        indCount(1);
    }

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd bytf brrby
     * stbrting bt offsft <dodf>off</dodf> to tif undfrlying output strfbm.
     * If no fxdfption is tirown, tif dountfr <dodf>writtfn</dodf> is
     * indrfmfntfd by <dodf>lfn</dodf>.
     *
     * @pbrbm      b     tif dbtb.
     * @pbrbm      off   tif stbrt offsft in tif dbtb.
     * @pbrbm      lfn   tif numbfr of bytfs to writf.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid syndironizfd void writf(bytf b[], int off, int lfn)
        tirows IOExdfption
    {
        out.writf(b, off, lfn);
        indCount(lfn);
    }

    /**
     * Flusifs tiis dbtb output strfbm. Tiis fordfs bny bufffrfd output
     * bytfs to bf writtfn out to tif strfbm.
     * <p>
     * Tif <dodf>flusi</dodf> mftiod of <dodf>DbtbOutputStrfbm</dodf>
     * dblls tif <dodf>flusi</dodf> mftiod of its undfrlying output strfbm.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     * @sff        jbvb.io.OutputStrfbm#flusi()
     */
    publid void flusi() tirows IOExdfption {
        out.flusi();
    }

    /**
     * Writfs b <dodf>boolfbn</dodf> to tif undfrlying output strfbm bs
     * b 1-bytf vbluf. Tif vbluf <dodf>truf</dodf> is writtfn out bs tif
     * vbluf <dodf>(bytf)1</dodf>; tif vbluf <dodf>fblsf</dodf> is
     * writtfn out bs tif vbluf <dodf>(bytf)0</dodf>. If no fxdfption is
     * tirown, tif dountfr <dodf>writtfn</dodf> is indrfmfntfd by
     * <dodf>1</dodf>.
     *
     * @pbrbm      v   b <dodf>boolfbn</dodf> vbluf to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfBoolfbn(boolfbn v) tirows IOExdfption {
        out.writf(v ? 1 : 0);
        indCount(1);
    }

    /**
     * Writfs out b <dodf>bytf</dodf> to tif undfrlying output strfbm bs
     * b 1-bytf vbluf. If no fxdfption is tirown, tif dountfr
     * <dodf>writtfn</dodf> is indrfmfntfd by <dodf>1</dodf>.
     *
     * @pbrbm      v   b <dodf>bytf</dodf> vbluf to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfBytf(int v) tirows IOExdfption {
        out.writf(v);
        indCount(1);
    }

    /**
     * Writfs b <dodf>siort</dodf> to tif undfrlying output strfbm bs two
     * bytfs, iigi bytf first. If no fxdfption is tirown, tif dountfr
     * <dodf>writtfn</dodf> is indrfmfntfd by <dodf>2</dodf>.
     *
     * @pbrbm      v   b <dodf>siort</dodf> to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfSiort(int v) tirows IOExdfption {
        out.writf((v >>> 8) & 0xFF);
        out.writf((v >>> 0) & 0xFF);
        indCount(2);
    }

    /**
     * Writfs b <dodf>dibr</dodf> to tif undfrlying output strfbm bs b
     * 2-bytf vbluf, iigi bytf first. If no fxdfption is tirown, tif
     * dountfr <dodf>writtfn</dodf> is indrfmfntfd by <dodf>2</dodf>.
     *
     * @pbrbm      v   b <dodf>dibr</dodf> vbluf to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfCibr(int v) tirows IOExdfption {
        out.writf((v >>> 8) & 0xFF);
        out.writf((v >>> 0) & 0xFF);
        indCount(2);
    }

    /**
     * Writfs bn <dodf>int</dodf> to tif undfrlying output strfbm bs four
     * bytfs, iigi bytf first. If no fxdfption is tirown, tif dountfr
     * <dodf>writtfn</dodf> is indrfmfntfd by <dodf>4</dodf>.
     *
     * @pbrbm      v   bn <dodf>int</dodf> to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfInt(int v) tirows IOExdfption {
        out.writf((v >>> 24) & 0xFF);
        out.writf((v >>> 16) & 0xFF);
        out.writf((v >>>  8) & 0xFF);
        out.writf((v >>>  0) & 0xFF);
        indCount(4);
    }

    privbtf bytf writfBufffr[] = nfw bytf[8];

    /**
     * Writfs b <dodf>long</dodf> to tif undfrlying output strfbm bs figit
     * bytfs, iigi bytf first. In no fxdfption is tirown, tif dountfr
     * <dodf>writtfn</dodf> is indrfmfntfd by <dodf>8</dodf>.
     *
     * @pbrbm      v   b <dodf>long</dodf> to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfLong(long v) tirows IOExdfption {
        writfBufffr[0] = (bytf)(v >>> 56);
        writfBufffr[1] = (bytf)(v >>> 48);
        writfBufffr[2] = (bytf)(v >>> 40);
        writfBufffr[3] = (bytf)(v >>> 32);
        writfBufffr[4] = (bytf)(v >>> 24);
        writfBufffr[5] = (bytf)(v >>> 16);
        writfBufffr[6] = (bytf)(v >>>  8);
        writfBufffr[7] = (bytf)(v >>>  0);
        out.writf(writfBufffr, 0, 8);
        indCount(8);
    }

    /**
     * Convfrts tif flobt brgumfnt to bn <dodf>int</dodf> using tif
     * <dodf>flobtToIntBits</dodf> mftiod in dlbss <dodf>Flobt</dodf>,
     * bnd tifn writfs tibt <dodf>int</dodf> vbluf to tif undfrlying
     * output strfbm bs b 4-bytf qubntity, iigi bytf first. If no
     * fxdfption is tirown, tif dountfr <dodf>writtfn</dodf> is
     * indrfmfntfd by <dodf>4</dodf>.
     *
     * @pbrbm      v   b <dodf>flobt</dodf> vbluf to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     * @sff        jbvb.lbng.Flobt#flobtToIntBits(flobt)
     */
    publid finbl void writfFlobt(flobt v) tirows IOExdfption {
        writfInt(Flobt.flobtToIntBits(v));
    }

    /**
     * Convfrts tif doublf brgumfnt to b <dodf>long</dodf> using tif
     * <dodf>doublfToLongBits</dodf> mftiod in dlbss <dodf>Doublf</dodf>,
     * bnd tifn writfs tibt <dodf>long</dodf> vbluf to tif undfrlying
     * output strfbm bs bn 8-bytf qubntity, iigi bytf first. If no
     * fxdfption is tirown, tif dountfr <dodf>writtfn</dodf> is
     * indrfmfntfd by <dodf>8</dodf>.
     *
     * @pbrbm      v   b <dodf>doublf</dodf> vbluf to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     * @sff        jbvb.lbng.Doublf#doublfToLongBits(doublf)
     */
    publid finbl void writfDoublf(doublf v) tirows IOExdfption {
        writfLong(Doublf.doublfToLongBits(v));
    }

    /**
     * Writfs out tif string to tif undfrlying output strfbm bs b
     * sfqufndf of bytfs. Ebdi dibrbdtfr in tif string is writtfn out, in
     * sfqufndf, by disdbrding its iigi figit bits. If no fxdfption is
     * tirown, tif dountfr <dodf>writtfn</dodf> is indrfmfntfd by tif
     * lfngti of <dodf>s</dodf>.
     *
     * @pbrbm      s   b string of bytfs to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfBytfs(String s) tirows IOExdfption {
        int lfn = s.lfngti();
        for (int i = 0 ; i < lfn ; i++) {
            out.writf((bytf)s.dibrAt(i));
        }
        indCount(lfn);
    }

    /**
     * Writfs b string to tif undfrlying output strfbm bs b sfqufndf of
     * dibrbdtfrs. Ebdi dibrbdtfr is writtfn to tif dbtb output strfbm bs
     * if by tif <dodf>writfCibr</dodf> mftiod. If no fxdfption is
     * tirown, tif dountfr <dodf>writtfn</dodf> is indrfmfntfd by twidf
     * tif lfngti of <dodf>s</dodf>.
     *
     * @pbrbm      s   b <dodf>String</dodf> vbluf to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.DbtbOutputStrfbm#writfCibr(int)
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid finbl void writfCibrs(String s) tirows IOExdfption {
        int lfn = s.lfngti();
        for (int i = 0 ; i < lfn ; i++) {
            int v = s.dibrAt(i);
            out.writf((v >>> 8) & 0xFF);
            out.writf((v >>> 0) & 0xFF);
        }
        indCount(lfn * 2);
    }

    /**
     * Writfs b string to tif undfrlying output strfbm using
     * <b irff="DbtbInput.itml#modififd-utf-8">modififd UTF-8</b>
     * fndoding in b mbdiinf-indfpfndfnt mbnnfr.
     * <p>
     * First, two bytfs brf writtfn to tif output strfbm bs if by tif
     * <dodf>writfSiort</dodf> mftiod giving tif numbfr of bytfs to
     * follow. Tiis vbluf is tif numbfr of bytfs bdtublly writtfn out,
     * not tif lfngti of tif string. Following tif lfngti, fbdi dibrbdtfr
     * of tif string is output, in sfqufndf, using tif modififd UTF-8 fndoding
     * for tif dibrbdtfr. If no fxdfption is tirown, tif dountfr
     * <dodf>writtfn</dodf> is indrfmfntfd by tif totbl numbfr of
     * bytfs writtfn to tif output strfbm. Tiis will bf bt lfbst two
     * plus tif lfngti of <dodf>str</dodf>, bnd bt most two plus
     * tiridf tif lfngti of <dodf>str</dodf>.
     *
     * @pbrbm      str   b string to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid finbl void writfUTF(String str) tirows IOExdfption {
        writfUTF(str, tiis);
    }

    /**
     * Writfs b string to tif spfdififd DbtbOutput using
     * <b irff="DbtbInput.itml#modififd-utf-8">modififd UTF-8</b>
     * fndoding in b mbdiinf-indfpfndfnt mbnnfr.
     * <p>
     * First, two bytfs brf writtfn to out bs if by tif <dodf>writfSiort</dodf>
     * mftiod giving tif numbfr of bytfs to follow. Tiis vbluf is tif numbfr of
     * bytfs bdtublly writtfn out, not tif lfngti of tif string. Following tif
     * lfngti, fbdi dibrbdtfr of tif string is output, in sfqufndf, using tif
     * modififd UTF-8 fndoding for tif dibrbdtfr. If no fxdfption is tirown, tif
     * dountfr <dodf>writtfn</dodf> is indrfmfntfd by tif totbl numbfr of
     * bytfs writtfn to tif output strfbm. Tiis will bf bt lfbst two
     * plus tif lfngti of <dodf>str</dodf>, bnd bt most two plus
     * tiridf tif lfngti of <dodf>str</dodf>.
     *
     * @pbrbm      str   b string to bf writtfn.
     * @pbrbm      out   dfstinbtion to writf to
     * @rfturn     Tif numbfr of bytfs writtfn out.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    stbtid int writfUTF(String str, DbtbOutput out) tirows IOExdfption {
        int strlfn = str.lfngti();
        int utflfn = 0;
        int d, dount = 0;

        /* usf dibrAt instfbd of dopying String to dibr brrby */
        for (int i = 0; i < strlfn; i++) {
            d = str.dibrAt(i);
            if ((d >= 0x0001) && (d <= 0x007F)) {
                utflfn++;
            } flsf if (d > 0x07FF) {
                utflfn += 3;
            } flsf {
                utflfn += 2;
            }
        }

        if (utflfn > 65535)
            tirow nfw UTFDbtbFormbtExdfption(
                "fndodfd string too long: " + utflfn + " bytfs");

        bytf[] bytfbrr = null;
        if (out instbndfof DbtbOutputStrfbm) {
            DbtbOutputStrfbm dos = (DbtbOutputStrfbm)out;
            if(dos.bytfbrr == null || (dos.bytfbrr.lfngti < (utflfn+2)))
                dos.bytfbrr = nfw bytf[(utflfn*2) + 2];
            bytfbrr = dos.bytfbrr;
        } flsf {
            bytfbrr = nfw bytf[utflfn+2];
        }

        bytfbrr[dount++] = (bytf) ((utflfn >>> 8) & 0xFF);
        bytfbrr[dount++] = (bytf) ((utflfn >>> 0) & 0xFF);

        int i=0;
        for (i=0; i<strlfn; i++) {
           d = str.dibrAt(i);
           if (!((d >= 0x0001) && (d <= 0x007F))) brfbk;
           bytfbrr[dount++] = (bytf) d;
        }

        for (;i < strlfn; i++){
            d = str.dibrAt(i);
            if ((d >= 0x0001) && (d <= 0x007F)) {
                bytfbrr[dount++] = (bytf) d;

            } flsf if (d > 0x07FF) {
                bytfbrr[dount++] = (bytf) (0xE0 | ((d >> 12) & 0x0F));
                bytfbrr[dount++] = (bytf) (0x80 | ((d >>  6) & 0x3F));
                bytfbrr[dount++] = (bytf) (0x80 | ((d >>  0) & 0x3F));
            } flsf {
                bytfbrr[dount++] = (bytf) (0xC0 | ((d >>  6) & 0x1F));
                bytfbrr[dount++] = (bytf) (0x80 | ((d >>  0) & 0x3F));
            }
        }
        out.writf(bytfbrr, 0, utflfn+2);
        rfturn utflfn + 2;
    }

    /**
     * Rfturns tif durrfnt vbluf of tif dountfr <dodf>writtfn</dodf>,
     * tif numbfr of bytfs writtfn to tiis dbtb output strfbm so fbr.
     * If tif dountfr ovfrflows, it will bf wrbppfd to Intfgfr.MAX_VALUE.
     *
     * @rfturn  tif vbluf of tif <dodf>writtfn</dodf> fifld.
     * @sff     jbvb.io.DbtbOutputStrfbm#writtfn
     */
    publid finbl int sizf() {
        rfturn writtfn;
    }
}
