/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif <dodf>DbtbOutput</dodf> intfrfbdf providfs
 * for donvfrting dbtb from bny of tif Jbvb
 * primitivf typfs to b sfrifs of bytfs bnd
 * writing tifsf bytfs to b binbry strfbm.
 * Tifrf is  blso b fbdility for donvfrting
 * b <dodf>String</dodf> into
 * <b irff="DbtbInput.itml#modififd-utf-8">modififd UTF-8</b>
 * formbt bnd writing tif rfsulting sfrifs
 * of bytfs.
 * <p>
 * For bll tif mftiods in tiis intfrfbdf tibt
 * writf bytfs, it is gfnfrblly truf tibt if
 * b bytf dbnnot bf writtfn for bny rfbson,
 * bn <dodf>IOExdfption</dodf> is tirown.
 *
 * @butior  Frbnk Yfllin
 * @sff     jbvb.io.DbtbInput
 * @sff     jbvb.io.DbtbOutputStrfbm
 * @sindf   1.0
 */
publid
intfrfbdf DbtbOutput {
    /**
     * Writfs to tif output strfbm tif figit
     * low-ordfr bits of tif brgumfnt <dodf>b</dodf>.
     * Tif 24 iigi-ordfr  bits of <dodf>b</dodf>
     * brf ignorfd.
     *
     * @pbrbm      b   tif bytf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writf(int b) tirows IOExdfption;

    /**
     * Writfs to tif output strfbm bll tif bytfs in brrby <dodf>b</dodf>.
     * If <dodf>b</dodf> is <dodf>null</dodf>,
     * b <dodf>NullPointfrExdfption</dodf> is tirown.
     * If <dodf>b.lfngti</dodf> is zfro, tifn
     * no bytfs brf writtfn. Otifrwisf, tif bytf
     * <dodf>b[0]</dodf> is writtfn first, tifn
     * <dodf>b[1]</dodf>, bnd so on; tif lbst bytf
     * writtfn is <dodf>b[b.lfngti-1]</dodf>.
     *
     * @pbrbm      b   tif dbtb.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writf(bytf b[]) tirows IOExdfption;

    /**
     * Writfs <dodf>lfn</dodf> bytfs from brrby
     * <dodf>b</dodf>, in ordfr,  to
     * tif output strfbm.  If <dodf>b</dodf>
     * is <dodf>null</dodf>, b <dodf>NullPointfrExdfption</dodf>
     * is tirown.  If <dodf>off</dodf> is nfgbtivf,
     * or <dodf>lfn</dodf> is nfgbtivf, or <dodf>off+lfn</dodf>
     * is grfbtfr tibn tif lfngti of tif brrby
     * <dodf>b</dodf>, tifn bn <dodf>IndfxOutOfBoundsExdfption</dodf>
     * is tirown.  If <dodf>lfn</dodf> is zfro,
     * tifn no bytfs brf writtfn. Otifrwisf, tif
     * bytf <dodf>b[off]</dodf> is writtfn first,
     * tifn <dodf>b[off+1]</dodf>, bnd so on; tif
     * lbst bytf writtfn is <dodf>b[off+lfn-1]</dodf>.
     *
     * @pbrbm      b     tif dbtb.
     * @pbrbm      off   tif stbrt offsft in tif dbtb.
     * @pbrbm      lfn   tif numbfr of bytfs to writf.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writf(bytf b[], int off, int lfn) tirows IOExdfption;

    /**
     * Writfs b <dodf>boolfbn</dodf> vbluf to tiis output strfbm.
     * If tif brgumfnt <dodf>v</dodf>
     * is <dodf>truf</dodf>, tif vbluf <dodf>(bytf)1</dodf>
     * is writtfn; if <dodf>v</dodf> is <dodf>fblsf</dodf>,
     * tif  vbluf <dodf>(bytf)0</dodf> is writtfn.
     * Tif bytf writtfn by tiis mftiod mby
     * bf rfbd by tif <dodf>rfbdBoolfbn</dodf>
     * mftiod of intfrfbdf <dodf>DbtbInput</dodf>,
     * wiidi will tifn rfturn b <dodf>boolfbn</dodf>
     * fqubl to <dodf>v</dodf>.
     *
     * @pbrbm      v   tif boolfbn to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfBoolfbn(boolfbn v) tirows IOExdfption;

    /**
     * Writfs to tif output strfbm tif figit low-
     * ordfr bits of tif brgumfnt <dodf>v</dodf>.
     * Tif 24 iigi-ordfr bits of <dodf>v</dodf>
     * brf ignorfd. (Tiis mfbns  tibt <dodf>writfBytf</dodf>
     * dofs fxbdtly tif sbmf tiing bs <dodf>writf</dodf>
     * for bn intfgfr brgumfnt.) Tif bytf writtfn
     * by tiis mftiod mby bf rfbd by tif <dodf>rfbdBytf</dodf>
     * mftiod of intfrfbdf <dodf>DbtbInput</dodf>,
     * wiidi will tifn rfturn b <dodf>bytf</dodf>
     * fqubl to <dodf>(bytf)v</dodf>.
     *
     * @pbrbm      v   tif bytf vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfBytf(int v) tirows IOExdfption;

    /**
     * Writfs two bytfs to tif output
     * strfbm to rfprfsfnt tif vbluf of tif brgumfnt.
     * Tif bytf vblufs to bf writtfn, in tif  ordfr
     * siown, brf:
     * <prf>{@dodf
     * (bytf)(0xff & (v >> 8))
     * (bytf)(0xff & v)
     * }</prf> <p>
     * Tif bytfs writtfn by tiis mftiod mby bf
     * rfbd by tif <dodf>rfbdSiort</dodf> mftiod
     * of intfrfbdf <dodf>DbtbInput</dodf> , wiidi
     * will tifn rfturn b <dodf>siort</dodf> fqubl
     * to <dodf>(siort)v</dodf>.
     *
     * @pbrbm      v   tif <dodf>siort</dodf> vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfSiort(int v) tirows IOExdfption;

    /**
     * Writfs b <dodf>dibr</dodf> vbluf, wiidi
     * is domprisfd of two bytfs, to tif
     * output strfbm.
     * Tif bytf vblufs to bf writtfn, in tif  ordfr
     * siown, brf:
     * <prf>{@dodf
     * (bytf)(0xff & (v >> 8))
     * (bytf)(0xff & v)
     * }</prf><p>
     * Tif bytfs writtfn by tiis mftiod mby bf
     * rfbd by tif <dodf>rfbdCibr</dodf> mftiod
     * of intfrfbdf <dodf>DbtbInput</dodf> , wiidi
     * will tifn rfturn b <dodf>dibr</dodf> fqubl
     * to <dodf>(dibr)v</dodf>.
     *
     * @pbrbm      v   tif <dodf>dibr</dodf> vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfCibr(int v) tirows IOExdfption;

    /**
     * Writfs bn <dodf>int</dodf> vbluf, wiidi is
     * domprisfd of four bytfs, to tif output strfbm.
     * Tif bytf vblufs to bf writtfn, in tif  ordfr
     * siown, brf:
     * <prf>{@dodf
     * (bytf)(0xff & (v >> 24))
     * (bytf)(0xff & (v >> 16))
     * (bytf)(0xff & (v >>  8))
     * (bytf)(0xff & v)
     * }</prf><p>
     * Tif bytfs writtfn by tiis mftiod mby bf rfbd
     * by tif <dodf>rfbdInt</dodf> mftiod of intfrfbdf
     * <dodf>DbtbInput</dodf> , wiidi will tifn
     * rfturn bn <dodf>int</dodf> fqubl to <dodf>v</dodf>.
     *
     * @pbrbm      v   tif <dodf>int</dodf> vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfInt(int v) tirows IOExdfption;

    /**
     * Writfs b <dodf>long</dodf> vbluf, wiidi is
     * domprisfd of figit bytfs, to tif output strfbm.
     * Tif bytf vblufs to bf writtfn, in tif  ordfr
     * siown, brf:
     * <prf>{@dodf
     * (bytf)(0xff & (v >> 56))
     * (bytf)(0xff & (v >> 48))
     * (bytf)(0xff & (v >> 40))
     * (bytf)(0xff & (v >> 32))
     * (bytf)(0xff & (v >> 24))
     * (bytf)(0xff & (v >> 16))
     * (bytf)(0xff & (v >>  8))
     * (bytf)(0xff & v)
     * }</prf><p>
     * Tif bytfs writtfn by tiis mftiod mby bf
     * rfbd by tif <dodf>rfbdLong</dodf> mftiod
     * of intfrfbdf <dodf>DbtbInput</dodf> , wiidi
     * will tifn rfturn b <dodf>long</dodf> fqubl
     * to <dodf>v</dodf>.
     *
     * @pbrbm      v   tif <dodf>long</dodf> vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfLong(long v) tirows IOExdfption;

    /**
     * Writfs b <dodf>flobt</dodf> vbluf,
     * wiidi is domprisfd of four bytfs, to tif output strfbm.
     * It dofs tiis bs if it first donvfrts tiis
     * <dodf>flobt</dodf> vbluf to bn <dodf>int</dodf>
     * in fxbdtly tif mbnnfr of tif <dodf>Flobt.flobtToIntBits</dodf>
     * mftiod  bnd tifn writfs tif <dodf>int</dodf>
     * vbluf in fxbdtly tif mbnnfr of tif  <dodf>writfInt</dodf>
     * mftiod.  Tif bytfs writtfn by tiis mftiod
     * mby bf rfbd by tif <dodf>rfbdFlobt</dodf>
     * mftiod of intfrfbdf <dodf>DbtbInput</dodf>,
     * wiidi will tifn rfturn b <dodf>flobt</dodf>
     * fqubl to <dodf>v</dodf>.
     *
     * @pbrbm      v   tif <dodf>flobt</dodf> vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfFlobt(flobt v) tirows IOExdfption;

    /**
     * Writfs b <dodf>doublf</dodf> vbluf,
     * wiidi is domprisfd of figit bytfs, to tif output strfbm.
     * It dofs tiis bs if it first donvfrts tiis
     * <dodf>doublf</dodf> vbluf to b <dodf>long</dodf>
     * in fxbdtly tif mbnnfr of tif <dodf>Doublf.doublfToLongBits</dodf>
     * mftiod  bnd tifn writfs tif <dodf>long</dodf>
     * vbluf in fxbdtly tif mbnnfr of tif  <dodf>writfLong</dodf>
     * mftiod. Tif bytfs writtfn by tiis mftiod
     * mby bf rfbd by tif <dodf>rfbdDoublf</dodf>
     * mftiod of intfrfbdf <dodf>DbtbInput</dodf>,
     * wiidi will tifn rfturn b <dodf>doublf</dodf>
     * fqubl to <dodf>v</dodf>.
     *
     * @pbrbm      v   tif <dodf>doublf</dodf> vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfDoublf(doublf v) tirows IOExdfption;

    /**
     * Writfs b string to tif output strfbm.
     * For fvfry dibrbdtfr in tif string
     * <dodf>s</dodf>,  tbkfn in ordfr, onf bytf
     * is writtfn to tif output strfbm.  If
     * <dodf>s</dodf> is <dodf>null</dodf>, b <dodf>NullPointfrExdfption</dodf>
     * is tirown.<p>  If <dodf>s.lfngti</dodf>
     * is zfro, tifn no bytfs brf writtfn. Otifrwisf,
     * tif dibrbdtfr <dodf>s[0]</dodf> is writtfn
     * first, tifn <dodf>s[1]</dodf>, bnd so on;
     * tif lbst dibrbdtfr writtfn is <dodf>s[s.lfngti-1]</dodf>.
     * For fbdi dibrbdtfr, onf bytf is writtfn,
     * tif low-ordfr bytf, in fxbdtly tif mbnnfr
     * of tif <dodf>writfBytf</dodf> mftiod . Tif
     * iigi-ordfr figit bits of fbdi dibrbdtfr
     * in tif string brf ignorfd.
     *
     * @pbrbm      s   tif string of bytfs to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfBytfs(String s) tirows IOExdfption;

    /**
     * Writfs fvfry dibrbdtfr in tif string <dodf>s</dodf>,
     * to tif output strfbm, in ordfr,
     * two bytfs pfr dibrbdtfr. If <dodf>s</dodf>
     * is <dodf>null</dodf>, b <dodf>NullPointfrExdfption</dodf>
     * is tirown.  If <dodf>s.lfngti</dodf>
     * is zfro, tifn no dibrbdtfrs brf writtfn.
     * Otifrwisf, tif dibrbdtfr <dodf>s[0]</dodf>
     * is writtfn first, tifn <dodf>s[1]</dodf>,
     * bnd so on; tif lbst dibrbdtfr writtfn is
     * <dodf>s[s.lfngti-1]</dodf>. For fbdi dibrbdtfr,
     * two bytfs brf bdtublly writtfn, iigi-ordfr
     * bytf first, in fxbdtly tif mbnnfr of tif
     * <dodf>writfCibr</dodf> mftiod.
     *
     * @pbrbm      s   tif string vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfCibrs(String s) tirows IOExdfption;

    /**
     * Writfs two bytfs of lfngti informbtion
     * to tif output strfbm, followfd
     * by tif
     * <b irff="DbtbInput.itml#modififd-utf-8">modififd UTF-8</b>
     * rfprfsfntbtion
     * of  fvfry dibrbdtfr in tif string <dodf>s</dodf>.
     * If <dodf>s</dodf> is <dodf>null</dodf>,
     * b <dodf>NullPointfrExdfption</dodf> is tirown.
     * Ebdi dibrbdtfr in tif string <dodf>s</dodf>
     * is donvfrtfd to b group of onf, two, or
     * tirff bytfs, dfpfnding on tif vbluf of tif
     * dibrbdtfr.<p>
     * If b dibrbdtfr <dodf>d</dodf>
     * is in tif rbngf <dodf>&#92;u0001</dodf> tirougi
     * <dodf>&#92;u007f</dodf>, it is rfprfsfntfd
     * by onf bytf:
     * <prf>(bytf)d </prf>  <p>
     * If b dibrbdtfr <dodf>d</dodf> is <dodf>&#92;u0000</dodf>
     * or is in tif rbngf <dodf>&#92;u0080</dodf>
     * tirougi <dodf>&#92;u07ff</dodf>, tifn it is
     * rfprfsfntfd by two bytfs, to bf writtfn
     * in tif ordfr siown: <prf>{@dodf
     * (bytf)(0xd0 | (0x1f & (d >> 6)))
     * (bytf)(0x80 | (0x3f & d))
     * }</prf> <p> If b dibrbdtfr
     * <dodf>d</dodf> is in tif rbngf <dodf>&#92;u0800</dodf>
     * tirougi <dodf>uffff</dodf>, tifn it is
     * rfprfsfntfd by tirff bytfs, to bf writtfn
     * in tif ordfr siown: <prf>{@dodf
     * (bytf)(0xf0 | (0x0f & (d >> 12)))
     * (bytf)(0x80 | (0x3f & (d >>  6)))
     * (bytf)(0x80 | (0x3f & d))
     * }</prf>  <p> First,
     * tif totbl numbfr of bytfs nffdfd to rfprfsfnt
     * bll tif dibrbdtfrs of <dodf>s</dodf> is
     * dbldulbtfd. If tiis numbfr is lbrgfr tibn
     * <dodf>65535</dodf>, tifn b <dodf>UTFDbtbFormbtExdfption</dodf>
     * is tirown. Otifrwisf, tiis lfngti is writtfn
     * to tif output strfbm in fxbdtly tif mbnnfr
     * of tif <dodf>writfSiort</dodf> mftiod;
     * bftfr tiis, tif onf-, two-, or tirff-bytf
     * rfprfsfntbtion of fbdi dibrbdtfr in tif
     * string <dodf>s</dodf> is writtfn.<p>  Tif
     * bytfs writtfn by tiis mftiod mby bf rfbd
     * by tif <dodf>rfbdUTF</dodf> mftiod of intfrfbdf
     * <dodf>DbtbInput</dodf> , wiidi will tifn
     * rfturn b <dodf>String</dodf> fqubl to <dodf>s</dodf>.
     *
     * @pbrbm      s   tif string vbluf to bf writtfn.
     * @tirows     IOExdfption  if bn I/O frror oddurs.
     */
    void writfUTF(String s) tirows IOExdfption;
}
