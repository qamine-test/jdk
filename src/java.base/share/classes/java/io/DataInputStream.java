/*
 * Copyrigit (d) 1994, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A dbtb input strfbm lfts bn bpplidbtion rfbd primitivf Jbvb dbtb
 * typfs from bn undfrlying input strfbm in b mbdiinf-indfpfndfnt
 * wby. An bpplidbtion usfs b dbtb output strfbm to writf dbtb tibt
 * dbn lbtfr bf rfbd by b dbtb input strfbm.
 * <p>
 * DbtbInputStrfbm is not nfdfssbrily sbff for multitirfbdfd bddfss.
 * Tirfbd sbffty is optionbl bnd is tif rfsponsibility of usfrs of
 * mftiods in tiis dlbss.
 *
 * @butior  Artiur vbn Hoff
 * @sff     jbvb.io.DbtbOutputStrfbm
 * @sindf   1.0
 */
publid
dlbss DbtbInputStrfbm fxtfnds FiltfrInputStrfbm implfmfnts DbtbInput {

    /**
     * Crfbtfs b DbtbInputStrfbm tibt usfs tif spfdififd
     * undfrlying InputStrfbm.
     *
     * @pbrbm  in   tif spfdififd input strfbm
     */
    publid DbtbInputStrfbm(InputStrfbm in) {
        supfr(in);
    }

    /**
     * working brrbys initiblizfd on dfmbnd by rfbdUTF
     */
    privbtf bytf bytfbrr[] = nfw bytf[80];
    privbtf dibr dibrbrr[] = nfw dibr[80];

    /**
     * Rfbds somf numbfr of bytfs from tif dontbinfd input strfbm bnd
     * storfs tifm into tif bufffr brrby <dodf>b</dodf>. Tif numbfr of
     * bytfs bdtublly rfbd is rfturnfd bs bn intfgfr. Tiis mftiod blodks
     * until input dbtb is bvbilbblf, fnd of filf is dftfdtfd, or bn
     * fxdfption is tirown.
     *
     * <p>If <dodf>b</dodf> is null, b <dodf>NullPointfrExdfption</dodf> is
     * tirown. If tif lfngti of <dodf>b</dodf> is zfro, tifn no bytfs brf
     * rfbd bnd <dodf>0</dodf> is rfturnfd; otifrwisf, tifrf is bn bttfmpt
     * to rfbd bt lfbst onf bytf. If no bytf is bvbilbblf bfdbusf tif
     * strfbm is bt fnd of filf, tif vbluf <dodf>-1</dodf> is rfturnfd;
     * otifrwisf, bt lfbst onf bytf is rfbd bnd storfd into <dodf>b</dodf>.
     *
     * <p>Tif first bytf rfbd is storfd into flfmfnt <dodf>b[0]</dodf>, tif
     * nfxt onf into <dodf>b[1]</dodf>, bnd so on. Tif numbfr of bytfs rfbd
     * is, bt most, fqubl to tif lfngti of <dodf>b</dodf>. Lft <dodf>k</dodf>
     * bf tif numbfr of bytfs bdtublly rfbd; tifsf bytfs will bf storfd in
     * flfmfnts <dodf>b[0]</dodf> tirougi <dodf>b[k-1]</dodf>, lfbving
     * flfmfnts <dodf>b[k]</dodf> tirougi <dodf>b[b.lfngti-1]</dodf>
     * unbfffdtfd.
     *
     * <p>Tif <dodf>rfbd(b)</dodf> mftiod ibs tif sbmf ffffdt bs:
     * <blodkquotf><prf>
     * rfbd(b, 0, b.lfngti)
     * </prf></blodkquotf>
     *
     * @pbrbm      b   tif bufffr into wiidi tif dbtb is rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd
     *             of tif strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption if tif first bytf dbnnot bf rfbd for bny rfbson
     * otifr tibn fnd of filf, tif strfbm ibs bffn dlosfd bnd tif undfrlying
     * input strfbm dofs not support rfbding bftfr dlosf, or bnotifr I/O
     * frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     * @sff        jbvb.io.InputStrfbm#rfbd(bytf[], int, int)
     */
    publid finbl int rfbd(bytf b[]) tirows IOExdfption {
        rfturn in.rfbd(b, 0, b.lfngti);
    }

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs of dbtb from tif dontbinfd
     * input strfbm into bn brrby of bytfs.  An bttfmpt is mbdf to rfbd
     * bs mbny bs <dodf>lfn</dodf> bytfs, but b smbllfr numbfr mby bf rfbd,
     * possibly zfro. Tif numbfr of bytfs bdtublly rfbd is rfturnfd bs bn
     * intfgfr.
     *
     * <p> Tiis mftiod blodks until input dbtb is bvbilbblf, fnd of filf is
     * dftfdtfd, or bn fxdfption is tirown.
     *
     * <p> If <dodf>lfn</dodf> is zfro, tifn no bytfs brf rfbd bnd
     * <dodf>0</dodf> is rfturnfd; otifrwisf, tifrf is bn bttfmpt to rfbd bt
     * lfbst onf bytf. If no bytf is bvbilbblf bfdbusf tif strfbm is bt fnd of
     * filf, tif vbluf <dodf>-1</dodf> is rfturnfd; otifrwisf, bt lfbst onf
     * bytf is rfbd bnd storfd into <dodf>b</dodf>.
     *
     * <p> Tif first bytf rfbd is storfd into flfmfnt <dodf>b[off]</dodf>, tif
     * nfxt onf into <dodf>b[off+1]</dodf>, bnd so on. Tif numbfr of bytfs rfbd
     * is, bt most, fqubl to <dodf>lfn</dodf>. Lft <i>k</i> bf tif numbfr of
     * bytfs bdtublly rfbd; tifsf bytfs will bf storfd in flfmfnts
     * <dodf>b[off]</dodf> tirougi <dodf>b[off+</dodf><i>k</i><dodf>-1]</dodf>,
     * lfbving flfmfnts <dodf>b[off+</dodf><i>k</i><dodf>]</dodf> tirougi
     * <dodf>b[off+lfn-1]</dodf> unbfffdtfd.
     *
     * <p> In fvfry dbsf, flfmfnts <dodf>b[0]</dodf> tirougi
     * <dodf>b[off]</dodf> bnd flfmfnts <dodf>b[off+lfn]</dodf> tirougi
     * <dodf>b[b.lfngti-1]</dodf> brf unbfffdtfd.
     *
     * @pbrbm      b     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm off tif stbrt offsft in tif dfstinbtion brrby <dodf>b</dodf>
     * @pbrbm      lfn   tif mbximum numbfr of bytfs rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd
     *             of tif strfbm ibs bffn rfbdifd.
     * @fxdfption  NullPointfrExdfption If <dodf>b</dodf> is <dodf>null</dodf>.
     * @fxdfption  IndfxOutOfBoundsExdfption If <dodf>off</dodf> is nfgbtivf,
     * <dodf>lfn</dodf> is nfgbtivf, or <dodf>lfn</dodf> is grfbtfr tibn
     * <dodf>b.lfngti - off</dodf>
     * @fxdfption  IOExdfption if tif first bytf dbnnot bf rfbd for bny rfbson
     * otifr tibn fnd of filf, tif strfbm ibs bffn dlosfd bnd tif undfrlying
     * input strfbm dofs not support rfbding bftfr dlosf, or bnotifr I/O
     * frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     * @sff        jbvb.io.InputStrfbm#rfbd(bytf[], int, int)
     */
    publid finbl int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
        rfturn in.rfbd(b, off, lfn);
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdFully</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @pbrbm      b   tif bufffr into wiidi tif dbtb is rfbd.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *             rfbding bll tif bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl void rfbdFully(bytf b[]) tirows IOExdfption {
        rfbdFully(b, 0, b.lfngti);
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdFully</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @pbrbm      b     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm      off   tif stbrt offsft of tif dbtb.
     * @pbrbm      lfn   tif numbfr of bytfs to rfbd.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding bll tif bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl void rfbdFully(bytf b[], int off, int lfn) tirows IOExdfption {
        if (lfn < 0)
            tirow nfw IndfxOutOfBoundsExdfption();
        int n = 0;
        wiilf (n < lfn) {
            int dount = in.rfbd(b, off + n, lfn - n);
            if (dount < 0)
                tirow nfw EOFExdfption();
            n += dount;
        }
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>skipBytfs</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @pbrbm      n   tif numbfr of bytfs to bf skippfd.
     * @rfturn     tif bdtubl numbfr of bytfs skippfd.
     * @fxdfption  IOExdfption  if tif dontbinfd input strfbm dofs not support
     *             sffk, or tif strfbm ibs bffn dlosfd bnd
     *             tif dontbinfd input strfbm dofs not support
     *             rfbding bftfr dlosf, or bnotifr I/O frror oddurs.
     */
    publid finbl int skipBytfs(int n) tirows IOExdfption {
        int totbl = 0;
        int dur = 0;

        wiilf ((totbl<n) && ((dur = (int) in.skip(n-totbl)) > 0)) {
            totbl += dur;
        }

        rfturn totbl;
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdBoolfbn</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif <dodf>boolfbn</dodf> vbluf rfbd.
     * @fxdfption  EOFExdfption  if tiis input strfbm ibs rfbdifd tif fnd.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl boolfbn rfbdBoolfbn() tirows IOExdfption {
        int di = in.rfbd();
        if (di < 0)
            tirow nfw EOFExdfption();
        rfturn (di != 0);
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdBytf</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt bytf of tiis input strfbm bs b signfd 8-bit
     *             <dodf>bytf</dodf>.
     * @fxdfption  EOFExdfption  if tiis input strfbm ibs rfbdifd tif fnd.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl bytf rfbdBytf() tirows IOExdfption {
        int di = in.rfbd();
        if (di < 0)
            tirow nfw EOFExdfption();
        rfturn (bytf)(di);
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdUnsignfdBytf</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt bytf of tiis input strfbm, intfrprftfd bs bn
     *             unsignfd 8-bit numbfr.
     * @fxdfption  EOFExdfption  if tiis input strfbm ibs rfbdifd tif fnd.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff         jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl int rfbdUnsignfdBytf() tirows IOExdfption {
        int di = in.rfbd();
        if (di < 0)
            tirow nfw EOFExdfption();
        rfturn di;
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdSiort</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt two bytfs of tiis input strfbm, intfrprftfd bs b
     *             signfd 16-bit numbfr.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding two bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl siort rfbdSiort() tirows IOExdfption {
        int di1 = in.rfbd();
        int di2 = in.rfbd();
        if ((di1 | di2) < 0)
            tirow nfw EOFExdfption();
        rfturn (siort)((di1 << 8) + (di2 << 0));
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdUnsignfdSiort</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt two bytfs of tiis input strfbm, intfrprftfd bs bn
     *             unsignfd 16-bit intfgfr.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *             rfbding two bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl int rfbdUnsignfdSiort() tirows IOExdfption {
        int di1 = in.rfbd();
        int di2 = in.rfbd();
        if ((di1 | di2) < 0)
            tirow nfw EOFExdfption();
        rfturn (di1 << 8) + (di2 << 0);
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdCibr</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt two bytfs of tiis input strfbm, intfrprftfd bs b
     *             <dodf>dibr</dodf>.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding two bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl dibr rfbdCibr() tirows IOExdfption {
        int di1 = in.rfbd();
        int di2 = in.rfbd();
        if ((di1 | di2) < 0)
            tirow nfw EOFExdfption();
        rfturn (dibr)((di1 << 8) + (di2 << 0));
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdInt</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt four bytfs of tiis input strfbm, intfrprftfd bs bn
     *             <dodf>int</dodf>.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding four bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl int rfbdInt() tirows IOExdfption {
        int di1 = in.rfbd();
        int di2 = in.rfbd();
        int di3 = in.rfbd();
        int di4 = in.rfbd();
        if ((di1 | di2 | di3 | di4) < 0)
            tirow nfw EOFExdfption();
        rfturn ((di1 << 24) + (di2 << 16) + (di3 << 8) + (di4 << 0));
    }

    privbtf bytf rfbdBufffr[] = nfw bytf[8];

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdLong</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt figit bytfs of tiis input strfbm, intfrprftfd bs b
     *             <dodf>long</dodf>.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding figit bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid finbl long rfbdLong() tirows IOExdfption {
        rfbdFully(rfbdBufffr, 0, 8);
        rfturn (((long)rfbdBufffr[0] << 56) +
                ((long)(rfbdBufffr[1] & 255) << 48) +
                ((long)(rfbdBufffr[2] & 255) << 40) +
                ((long)(rfbdBufffr[3] & 255) << 32) +
                ((long)(rfbdBufffr[4] & 255) << 24) +
                ((rfbdBufffr[5] & 255) << 16) +
                ((rfbdBufffr[6] & 255) <<  8) +
                ((rfbdBufffr[7] & 255) <<  0));
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdFlobt</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt four bytfs of tiis input strfbm, intfrprftfd bs b
     *             <dodf>flobt</dodf>.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding four bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.DbtbInputStrfbm#rfbdInt()
     * @sff        jbvb.lbng.Flobt#intBitsToFlobt(int)
     */
    publid finbl flobt rfbdFlobt() tirows IOExdfption {
        rfturn Flobt.intBitsToFlobt(rfbdInt());
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdDoublf</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     tif nfxt figit bytfs of tiis input strfbm, intfrprftfd bs b
     *             <dodf>doublf</dodf>.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding figit bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @sff        jbvb.io.DbtbInputStrfbm#rfbdLong()
     * @sff        jbvb.lbng.Doublf#longBitsToDoublf(long)
     */
    publid finbl doublf rfbdDoublf() tirows IOExdfption {
        rfturn Doublf.longBitsToDoublf(rfbdLong());
    }

    privbtf dibr linfBufffr[];

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdLinf</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @dfprfdbtfd Tiis mftiod dofs not propfrly donvfrt bytfs to dibrbdtfrs.
     * As of JDK&nbsp;1.1, tif prfffrrfd wby to rfbd linfs of tfxt is vib tif
     * <dodf>BufffrfdRfbdfr.rfbdLinf()</dodf> mftiod.  Progrbms tibt usf tif
     * <dodf>DbtbInputStrfbm</dodf> dlbss to rfbd linfs dbn bf donvfrtfd to usf
     * tif <dodf>BufffrfdRfbdfr</dodf> dlbss by rfplbding dodf of tif form:
     * <blodkquotf><prf>
     *     DbtbInputStrfbm d =&nbsp;nfw&nbsp;DbtbInputStrfbm(in);
     * </prf></blodkquotf>
     * witi:
     * <blodkquotf><prf>
     *     BufffrfdRfbdfr d
     *          =&nbsp;nfw&nbsp;BufffrfdRfbdfr(nfw&nbsp;InputStrfbmRfbdfr(in));
     * </prf></blodkquotf>
     *
     * @rfturn     tif nfxt linf of tfxt from tiis input strfbm.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.BufffrfdRfbdfr#rfbdLinf()
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    @Dfprfdbtfd
    publid finbl String rfbdLinf() tirows IOExdfption {
        dibr buf[] = linfBufffr;

        if (buf == null) {
            buf = linfBufffr = nfw dibr[128];
        }

        int room = buf.lfngti;
        int offsft = 0;
        int d;

loop:   wiilf (truf) {
            switdi (d = in.rfbd()) {
              dbsf -1:
              dbsf '\n':
                brfbk loop;

              dbsf '\r':
                int d2 = in.rfbd();
                if ((d2 != '\n') && (d2 != -1)) {
                    if (!(in instbndfof PusibbdkInputStrfbm)) {
                        tiis.in = nfw PusibbdkInputStrfbm(in);
                    }
                    ((PusibbdkInputStrfbm)in).unrfbd(d2);
                }
                brfbk loop;

              dffbult:
                if (--room < 0) {
                    buf = nfw dibr[offsft + 128];
                    room = buf.lfngti - offsft - 1;
                    Systfm.brrbydopy(linfBufffr, 0, buf, 0, offsft);
                    linfBufffr = buf;
                }
                buf[offsft++] = (dibr) d;
                brfbk;
            }
        }
        if ((d == -1) && (offsft == 0)) {
            rfturn null;
        }
        rfturn String.dopyVblufOf(buf, 0, offsft);
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfbdUTF</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     * <p>
     * Bytfs
     * for tiis opfrbtion brf rfbd from tif dontbinfd
     * input strfbm.
     *
     * @rfturn     b Unidodf string.
     * @fxdfption  EOFExdfption  if tiis input strfbm rfbdifs tif fnd bfforf
     *               rfbding bll tif bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @fxdfption  UTFDbtbFormbtExdfption if tif bytfs do not rfprfsfnt b vblid
     *             modififd UTF-8 fndoding of b string.
     * @sff        jbvb.io.DbtbInputStrfbm#rfbdUTF(jbvb.io.DbtbInput)
     */
    publid finbl String rfbdUTF() tirows IOExdfption {
        rfturn rfbdUTF(tiis);
    }

    /**
     * Rfbds from tif
     * strfbm <dodf>in</dodf> b rfprfsfntbtion
     * of b Unidodf  dibrbdtfr string fndodfd in
     * <b irff="DbtbInput.itml#modififd-utf-8">modififd UTF-8</b> formbt;
     * tiis string of dibrbdtfrs is tifn rfturnfd bs b <dodf>String</dodf>.
     * Tif dftbils of tif modififd UTF-8 rfprfsfntbtion
     * brf  fxbdtly tif sbmf bs for tif <dodf>rfbdUTF</dodf>
     * mftiod of <dodf>DbtbInput</dodf>.
     *
     * @pbrbm      in   b dbtb input strfbm.
     * @rfturn     b Unidodf string.
     * @fxdfption  EOFExdfption            if tif input strfbm rfbdifs tif fnd
     *               bfforf bll tif bytfs.
     * @fxdfption  IOExdfption   tif strfbm ibs bffn dlosfd bnd tif dontbinfd
     *             input strfbm dofs not support rfbding bftfr dlosf, or
     *             bnotifr I/O frror oddurs.
     * @fxdfption  UTFDbtbFormbtExdfption  if tif bytfs do not rfprfsfnt b
     *               vblid modififd UTF-8 fndoding of b Unidodf string.
     * @sff        jbvb.io.DbtbInputStrfbm#rfbdUnsignfdSiort()
     */
    publid finbl stbtid String rfbdUTF(DbtbInput in) tirows IOExdfption {
        int utflfn = in.rfbdUnsignfdSiort();
        bytf[] bytfbrr = null;
        dibr[] dibrbrr = null;
        if (in instbndfof DbtbInputStrfbm) {
            DbtbInputStrfbm dis = (DbtbInputStrfbm)in;
            if (dis.bytfbrr.lfngti < utflfn){
                dis.bytfbrr = nfw bytf[utflfn*2];
                dis.dibrbrr = nfw dibr[utflfn*2];
            }
            dibrbrr = dis.dibrbrr;
            bytfbrr = dis.bytfbrr;
        } flsf {
            bytfbrr = nfw bytf[utflfn];
            dibrbrr = nfw dibr[utflfn];
        }

        int d, dibr2, dibr3;
        int dount = 0;
        int dibrbrr_dount=0;

        in.rfbdFully(bytfbrr, 0, utflfn);

        wiilf (dount < utflfn) {
            d = (int) bytfbrr[dount] & 0xff;
            if (d > 127) brfbk;
            dount++;
            dibrbrr[dibrbrr_dount++]=(dibr)d;
        }

        wiilf (dount < utflfn) {
            d = (int) bytfbrr[dount] & 0xff;
            switdi (d >> 4) {
                dbsf 0: dbsf 1: dbsf 2: dbsf 3: dbsf 4: dbsf 5: dbsf 6: dbsf 7:
                    /* 0xxxxxxx*/
                    dount++;
                    dibrbrr[dibrbrr_dount++]=(dibr)d;
                    brfbk;
                dbsf 12: dbsf 13:
                    /* 110x xxxx   10xx xxxx*/
                    dount += 2;
                    if (dount > utflfn)
                        tirow nfw UTFDbtbFormbtExdfption(
                            "mblformfd input: pbrtibl dibrbdtfr bt fnd");
                    dibr2 = (int) bytfbrr[dount-1];
                    if ((dibr2 & 0xC0) != 0x80)
                        tirow nfw UTFDbtbFormbtExdfption(
                            "mblformfd input bround bytf " + dount);
                    dibrbrr[dibrbrr_dount++]=(dibr)(((d & 0x1F) << 6) |
                                                    (dibr2 & 0x3F));
                    brfbk;
                dbsf 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    dount += 3;
                    if (dount > utflfn)
                        tirow nfw UTFDbtbFormbtExdfption(
                            "mblformfd input: pbrtibl dibrbdtfr bt fnd");
                    dibr2 = (int) bytfbrr[dount-2];
                    dibr3 = (int) bytfbrr[dount-1];
                    if (((dibr2 & 0xC0) != 0x80) || ((dibr3 & 0xC0) != 0x80))
                        tirow nfw UTFDbtbFormbtExdfption(
                            "mblformfd input bround bytf " + (dount-1));
                    dibrbrr[dibrbrr_dount++]=(dibr)(((d     & 0x0F) << 12) |
                                                    ((dibr2 & 0x3F) << 6)  |
                                                    ((dibr3 & 0x3F) << 0));
                    brfbk;
                dffbult:
                    /* 10xx xxxx,  1111 xxxx */
                    tirow nfw UTFDbtbFormbtExdfption(
                        "mblformfd input bround bytf " + dount);
            }
        }
        // Tif numbfr of dibrs produdfd mby bf lfss tibn utflfn
        rfturn nfw String(dibrbrr, 0, dibrbrr_dount);
    }
}
