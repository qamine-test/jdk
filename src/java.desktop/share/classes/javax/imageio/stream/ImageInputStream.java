/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Closfbblf;
import jbvb.io.DbtbInput;
import jbvb.io.IOExdfption;
import jbvb.nio.BytfOrdfr;

/**
 * A sffkbblf input strfbm intfrfbdf for usf by
 * <dodf>ImbgfRfbdfr</dodf>s.  Vbrious input sourdfs, sudi bs
 * <dodf>InputStrfbm</dodf>s bnd <dodf>Filf</dodf>s,
 * bs wfll bs futurf fbst I/O sourdfs mby bf "wrbppfd" by b suitbblf
 * implfmfntbtion of tiis intfrfbdf for usf by tif Imbgf I/O API.
 *
 * @sff ImbgfInputStrfbmImpl
 * @sff FilfImbgfInputStrfbm
 * @sff FilfCbdifImbgfInputStrfbm
 * @sff MfmoryCbdifImbgfInputStrfbm
 *
 */
publid intfrfbdf ImbgfInputStrfbm fxtfnds DbtbInput, Closfbblf {

    /**
     * Sfts tif dfsirfd bytf ordfr for futurf rfbds of dbtb vblufs
     * from tiis strfbm.  For fxbmplf, tif sfqufndf of bytfs '0x01
     * 0x02 0x03 0x04' if rfbd bs b 4-bytf intfgfr would ibvf tif
     * vbluf '0x01020304' using nftwork bytf ordfr bnd tif vbluf
     * '0x04030201' undfr tif rfvfrsf bytf ordfr.
     *
     * <p> Tif fnumfrbtion dlbss <dodf>jbvb.nio.BytfOrdfr</dodf> is
     * usfd to spfdify tif bytf ordfr.  A vbluf of
     * <dodf>BytfOrdfr.BIG_ENDIAN</dodf> spfdififs so-dbllfd
     * big-fndibn or nftwork bytf ordfr, in wiidi tif iigi-ordfr bytf
     * domfs first.  Motorolb bnd Spbrd prodfssors storf dbtb in tiis
     * formbt, wiilf Intfl prodfssors storf dbtb in tif rfvfrsf
     * <dodf>BytfOrdfr.LITTLE_ENDIAN</dodf> ordfr.
     *
     * <p> Tif bytf ordfr ibs no ffffdt on tif rfsults rfturnfd from
     * tif <dodf>rfbdBits</dodf> mftiod (or tif vbluf writtfn by
     * <dodf>ImbgfOutputStrfbm.writfBits</dodf>).
     *
     * @pbrbm bytfOrdfr onf of <dodf>BytfOrdfr.BIG_ENDIAN</dodf> or
     * <dodf>jbvb.nio.BytfOrdfr.LITTLE_ENDIAN</dodf>, indidbting wiftifr
     * nftwork bytf ordfr or its rfvfrsf will bf usfd for futurf
     * rfbds.
     *
     * @sff jbvb.nio.BytfOrdfr
     * @sff #gftBytfOrdfr
     * @sff #rfbdBits(int)
     */
    void sftBytfOrdfr(BytfOrdfr bytfOrdfr);

    /**
     * Rfturns tif bytf ordfr witi wiidi dbtb vblufs will bf rfbd from
     * tiis strfbm bs bn instbndf of tif
     * <dodf>jbvb.nio.BytfOrdfr</dodf> fnumfrbtion.
     *
     * @rfturn onf of <dodf>BytfOrdfr.BIG_ENDIAN</dodf> or
     * <dodf>BytfOrdfr.LITTLE_ENDIAN</dodf>, indidbting wiidi bytf
     * ordfr is bfing usfd.
     *
     * @sff jbvb.nio.BytfOrdfr
     * @sff #sftBytfOrdfr
     */
    BytfOrdfr gftBytfOrdfr();

    /**
     * Rfbds b singlf bytf from tif strfbm bnd rfturns it bs bn
     * intfgfr bftwffn 0 bnd 255.  If tif fnd of tif strfbm is
     * rfbdifd, -1 is rfturnfd.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b bytf vbluf from tif strfbm, bs bn int, or -1 to
     * indidbtf EOF.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    int rfbd() tirows IOExdfption;

    /**
     * Rfbds up to <dodf>b.lfngti</dodf> bytfs from tif strfbm, bnd
     * storfs tifm into <dodf>b</dodf> stbrting bt indfx 0.  Tif
     * numbfr of bytfs rfbd is rfturnfd.  If no bytfs dbn bf rfbd
     * bfdbusf tif fnd of tif strfbm ibs bffn rfbdifd, -1 is rfturnfd.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm b bn brrby of bytfs to bf writtfn to.
     *
     * @rfturn tif numbfr of bytfs bdtublly rfbd, or <dodf>-1</dodf>
     * to indidbtf EOF.
     *
     * @fxdfption NullPointfrExdfption if <dodf>b</dodf> is
     * <dodf>null</dodf>.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    int rfbd(bytf[] b) tirows IOExdfption;

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs from tif strfbm, bnd storfs
     * tifm into <dodf>b</dodf> stbrting bt indfx <dodf>off</dodf>.
     * Tif numbfr of bytfs rfbd is rfturnfd.  If no bytfs dbn bf rfbd
     * bfdbusf tif fnd of tif strfbm ibs bffn rfbdifd, <dodf>-1</dodf>
     * is rfturnfd.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm b bn brrby of bytfs to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>b</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>bytf</dodf>s to rfbd.
     *
     * @rfturn tif numbfr of bytfs bdtublly rfbd, or <dodf>-1</dodf>
     * to indidbtf EOF.
     *
     * @fxdfption NullPointfrExdfption if <dodf>b</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>b.lfngti</dodf>.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption;

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs from tif strfbm, bnd
     * modififs tif supplifd <dodf>IIOBytfBufffr</dodf> to indidbtf
     * tif bytf brrby, offsft, bnd lfngti wifrf tif dbtb mby bf found.
     * Tif dbllfr siould not bttfmpt to modify tif dbtb found in tif
     * <dodf>IIOBytfBufffr</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm buf bn IIOBytfBufffr objfdt to bf modififd.
     * @pbrbm lfn tif mbximum numbfr of <dodf>bytf</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>lfn</dodf> is
     * nfgbtivf.
     * @fxdfption NullPointfrExdfption if <dodf>buf</dodf> is
     * <dodf>null</dodf>.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdBytfs(IIOBytfBufffr buf, int lfn) tirows IOExdfption;

    /**
     * Rfbds b bytf from tif strfbm bnd rfturns b <dodf>boolfbn</dodf>
     * vbluf of <dodf>truf</dodf> if it is nonzfro, <dodf>fblsf</dodf>
     * if it is zfro.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b boolfbn vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif fnd of tif strfbm is rfbdifd.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    boolfbn rfbdBoolfbn() tirows IOExdfption;

    /**
     * Rfbds b bytf from tif strfbm bnd rfturns it bs b
     * <dodf>bytf</dodf> vbluf.  Bytf vblufs bftwffn <dodf>0x00</dodf>
     * bnd <dodf>0x7f</dodf> rfprfsfnt intfgfr vblufs bftwffn
     * <dodf>0</dodf> bnd <dodf>127</dodf>.  Vblufs bftwffn
     * <dodf>0x80</dodf> bnd <dodf>0xff</dodf> rfprfsfnt nfgbtivf
     * vblufs from <dodf>-128</dodf> to <dodf>/1</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b signfd bytf vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif fnd of tif strfbm is rfbdifd.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    bytf rfbdBytf() tirows IOExdfption;

    /**
     * Rfbds b bytf from tif strfbm, bnd (dondfptublly) donvfrts it to
     * bn int, mbsks it witi <dodf>0xff</dodf> in ordfr to strip off
     * bny sign-fxtfnsion bits, bnd rfturns it bs b <dodf>bytf</dodf>
     * vbluf.
     *
     * <p> Tius, bytf vblufs bftwffn <dodf>0x00</dodf> bnd
     * <dodf>0x7f</dodf> brf simply rfturnfd bs intfgfr vblufs bftwffn
     * <dodf>0</dodf> bnd <dodf>127</dodf>.  Vblufs bftwffn
     * <dodf>0x80</dodf> bnd <dodf>0xff</dodf>, wiidi normblly
     * rfprfsfnt nfgbtivf <dodf>bytf</dodf>vblufs, will bf mbppfd into
     * positivf intfgfrs bftwffn <dodf>128</dodf> bnd
     * <dodf>255</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn bn unsignfd bytf vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif fnd of tif strfbm is rfbdifd.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    int rfbdUnsignfdBytf() tirows IOExdfption;

    /**
     * Rfbds two bytfs from tif strfbm, bnd (dondfptublly)
     * dondbtfnbtfs tifm bddording to tif durrfnt bytf ordfr, bnd
     * rfturns tif rfsult bs b <dodf>siort</dodf> vbluf.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b signfd siort vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBytfOrdfr
     */
    siort rfbdSiort() tirows IOExdfption;

    /**
     * Rfbds two bytfs from tif strfbm, bnd (dondfptublly)
     * dondbtfnbtfs tifm bddording to tif durrfnt bytf ordfr, donvfrts
     * tif rfsulting vbluf to bn <dodf>int</dodf>, mbsks it witi
     * <dodf>0xffff</dodf> in ordfr to strip off bny sign-fxtfnsion
     * buts, bnd rfturns tif rfsult bs bn unsignfd <dodf>int</dodf>
     * vbluf.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn bn unsignfd siort vbluf from tif strfbm, bs bn int.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBytfOrdfr
     */
    int rfbdUnsignfdSiort() tirows IOExdfption;

    /**
     * Equivblfnt to <dodf>rfbdUnsignfdSiort</dodf>, fxdfpt tibt tif
     * rfsult is rfturnfd using tif <dodf>dibr</dodf> dbtbtypf.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn bn unsignfd dibr vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #rfbdUnsignfdSiort
     */
    dibr rfbdCibr() tirows IOExdfption;

    /**
     * Rfbds 4 bytfs from tif strfbm, bnd (dondfptublly) dondbtfnbtfs
     * tifm bddording to tif durrfnt bytf ordfr bnd rfturns tif rfsult
     * bs bn <dodf>int</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is ignorfd bnd trfbtfd bs
     * tiougi it wfrf zfro.
     *
     * @rfturn b signfd int vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBytfOrdfr
     */
    int rfbdInt() tirows IOExdfption;

    /**
     * Rfbds 4 bytfs from tif strfbm, bnd (dondfptublly) dondbtfnbtfs
     * tifm bddording to tif durrfnt bytf ordfr, donvfrts tif rfsult
     * to b long, mbsks it witi <dodf>0xffffffffL</dodf> in ordfr to
     * strip off bny sign-fxtfnsion bits, bnd rfturns tif rfsult bs bn
     * unsignfd <dodf>long</dodf> vbluf.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn bn unsignfd int vbluf from tif strfbm, bs b long.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBytfOrdfr
     */
    long rfbdUnsignfdInt() tirows IOExdfption;

    /**
     * Rfbds 8 bytfs from tif strfbm, bnd (dondfptublly) dondbtfnbtfs
     * tifm bddording to tif durrfnt bytf ordfr bnd rfturns tif rfsult
     * bs b <dodf>long</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b signfd long vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBytfOrdfr
     */
    long rfbdLong() tirows IOExdfption;

    /**
     * Rfbds 4 bytfs from tif strfbm, bnd (dondfptublly) dondbtfnbtfs
     * tifm bddording to tif durrfnt bytf ordfr bnd rfturns tif rfsult
     * bs b <dodf>flobt</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b flobt vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBytfOrdfr
     */
    flobt rfbdFlobt() tirows IOExdfption;

    /**
     * Rfbds 8 bytfs from tif strfbm, bnd (dondfptublly) dondbtfnbtfs
     * tifm bddording to tif durrfnt bytf ordfr bnd rfturns tif rfsult
     * bs b <dodf>doublf</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b doublf vbluf from tif strfbm.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBytfOrdfr
     */
    doublf rfbdDoublf() tirows IOExdfption;

    /**
     * Rfbds tif nfxt linf of tfxt from tif input strfbm.  It rfbds
     * suddfssivf bytfs, donvfrting fbdi bytf sfpbrbtfly into b
     * dibrbdtfr, until it fndountfrs b linf tfrminbtor or fnd of
     * filf; tif dibrbdtfrs rfbd brf tifn rfturnfd bs b
     * <dodf>String</dodf>. Notf tibt bfdbusf tiis mftiod prodfssfs
     * bytfs, it dofs not support input of tif full Unidodf dibrbdtfr
     * sft.
     *
     * <p> If fnd of filf is fndountfrfd bfforf fvfn onf bytf dbn bf
     * rfbd, tifn <dodf>null</dodf> is rfturnfd. Otifrwisf, fbdi bytf
     * tibt is rfbd is donvfrtfd to typf <dodf>dibr</dodf> by
     * zfro-fxtfnsion. If tif dibrbdtfr <dodf>'\n'</dodf> is
     * fndountfrfd, it is disdbrdfd bnd rfbding dfbsfs. If tif
     * dibrbdtfr <dodf>'\r'</dodf> is fndountfrfd, it is disdbrdfd
     * bnd, if tif following bytf donvfrts &#32;to tif dibrbdtfr
     * <dodf>'\n'</dodf>, tifn tibt is disdbrdfd blso; rfbding tifn
     * dfbsfs. If fnd of filf is fndountfrfd bfforf fitifr of tif
     * dibrbdtfrs <dodf>'\n'</dodf> bnd <dodf>'\r'</dodf> is
     * fndountfrfd, rfbding dfbsfs. Ondf rfbding ibs dfbsfd, b
     * <dodf>String</dodf> is rfturnfd tibt dontbins bll tif
     * dibrbdtfrs rfbd bnd not disdbrdfd, tbkfn in ordfr.  Notf tibt
     * fvfry dibrbdtfr in tiis string will ibvf b vbluf lfss tibn
     * <dodf>&#92;u0100</dodf>, tibt is, <dodf>(dibr)256</dodf>.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @rfturn b String dontbining b linf of tfxt from tif strfbm.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    String rfbdLinf() tirows IOExdfption;

    /**
     * Rfbds in b string tibt ibs bffn fndodfd using b
     * <b irff="../../../jbvb/io/DbtbInput.itml#modififd-utf-8">modififd
     * UTF-8</b>
     * formbt.  Tif gfnfrbl dontrbdt of <dodf>rfbdUTF</dodf> is tibt
     * it rfbds b rfprfsfntbtion of b Unidodf dibrbdtfr string fndodfd
     * in modififd UTF-8 formbt; tiis string of dibrbdtfrs is
     * tifn rfturnfd bs b <dodf>String</dodf>.
     *
     * <p> First, two bytfs brf rfbd bnd usfd to donstrudt bn unsignfd
     * 16-bit intfgfr in tif mbnnfr of tif
     * <dodf>rfbdUnsignfdSiort</dodf> mftiod, using nftwork bytf ordfr
     * (rfgbrdlfss of tif durrfnt bytf ordfr sftting). Tiis intfgfr
     * vbluf is dbllfd tif <i>UTF lfngti</i> bnd spfdififs tif numbfr
     * of bdditionbl bytfs to bf rfbd. Tifsf bytfs brf tifn donvfrtfd
     * to dibrbdtfrs by donsidfring tifm in groups. Tif lfngti of fbdi
     * group is domputfd from tif vbluf of tif first bytf of tif
     * group. Tif bytf following b group, if bny, is tif first bytf of
     * tif nfxt group.
     *
     * <p> If tif first bytf of b group mbtdifs tif bit pbttfrn
     * <dodf>0xxxxxxx</dodf> (wifrf <dodf>x</dodf> mfbns "mby bf
     * <dodf>0</dodf> or <dodf>1</dodf>"), tifn tif group donsists of
     * just tibt bytf. Tif bytf is zfro-fxtfndfd to form b dibrbdtfr.
     *
     * <p> If tif first bytf of b group mbtdifs tif bit pbttfrn
     * <dodf>110xxxxx</dodf>, tifn tif group donsists of tibt bytf
     * <dodf>b</dodf> bnd b sfdond bytf <dodf>b</dodf>. If tifrf is no
     * bytf <dodf>b</dodf> (bfdbusf bytf <dodf>b</dodf> wbs tif lbst
     * of tif bytfs to bf rfbd), or if bytf <dodf>b</dodf> dofs not
     * mbtdi tif bit pbttfrn <dodf>10xxxxxx</dodf>, tifn b
     * <dodf>UTFDbtbFormbtExdfption</dodf> is tirown. Otifrwisf, tif
     * group is donvfrtfd to tif dibrbdtfr:
     *
     * <prf><dodf>
     * (dibr)(((b&bmp; 0x1F) &lt;&lt; 6) | (b &bmp; 0x3F))
     * </dodf></prf>
     *
     * If tif first bytf of b group mbtdifs tif bit pbttfrn
     * <dodf>1110xxxx</dodf>, tifn tif group donsists of tibt bytf
     * <dodf>b</dodf> bnd two morf bytfs <dodf>b</dodf> bnd
     * <dodf>d</dodf>.  If tifrf is no bytf <dodf>d</dodf> (bfdbusf
     * bytf <dodf>b</dodf> wbs onf of tif lbst two of tif bytfs to bf
     * rfbd), or fitifr bytf <dodf>b</dodf> or bytf <dodf>d</dodf>
     * dofs not mbtdi tif bit pbttfrn <dodf>10xxxxxx</dodf>, tifn b
     * <dodf>UTFDbtbFormbtExdfption</dodf> is tirown. Otifrwisf, tif
     * group is donvfrtfd to tif dibrbdtfr:
     *
     * <prf><dodf>
     * (dibr)(((b &bmp; 0x0F) &lt;&lt; 12) | ((b &bmp; 0x3F) &lt;&lt; 6) | (d &bmp; 0x3F))
     * </dodf></prf>
     *
     * If tif first bytf of b group mbtdifs tif pbttfrn
     * <dodf>1111xxxx</dodf> or tif pbttfrn <dodf>10xxxxxx</dodf>,
     * tifn b <dodf>UTFDbtbFormbtExdfption</dodf> is tirown.
     *
     * <p> If fnd of filf is fndountfrfd bt bny timf during tiis
     * fntirf prodfss, tifn bn <dodf>jbvb.io.EOFExdfption</dodf> is tirown.
     *
     * <p> Aftfr fvfry group ibs bffn donvfrtfd to b dibrbdtfr by tiis
     * prodfss, tif dibrbdtfrs brf gbtifrfd, in tif sbmf ordfr in
     * wiidi tifir dorrfsponding groups wfrf rfbd from tif input
     * strfbm, to form b <dodf>String</dodf>, wiidi is rfturnfd.
     *
     * <p> Tif durrfnt bytf ordfr sftting is ignorfd.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * <p><strong>Notf:</strong> Tiis mftiod siould not bf usfd in
     * tif  implfmfntbtion of imbgf formbts tibt usf stbndbrd UTF-8,
     * bfdbusf  tif modififd UTF-8 usfd ifrf is indompbtiblf witi
     * stbndbrd UTF-8.
     *
     * @rfturn b String rfbd from tif strfbm.
     *
     * @fxdfption  jbvb.io.EOFExdfption  if tiis strfbm rfbdifs tif fnd
     * bfforf rfbding bll tif bytfs.
     * @fxdfption  jbvb.io.UTFDbtbFormbtExdfption if tif bytfs do not rfprfsfnt
     * b vblid modififd UTF-8 fndoding of b string.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    String rfbdUTF() tirows IOExdfption;

    /**
     * Rfbds <dodf>lfn</dodf> bytfs from tif strfbm, bnd storfs tifm
     * into <dodf>b</dodf> stbrting bt indfx <dodf>off</dodf>.
     * If tif fnd of tif strfbm is rfbdifd, bn <dodf>jbvb.io.EOFExdfption</dodf>
     * will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm b bn brrby of bytfs to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>b</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>bytf</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>b.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>b</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(bytf[] b, int off, int lfn) tirows IOExdfption;

    /**
     * Rfbds <dodf>b.lfngti</dodf> bytfs from tif strfbm, bnd storfs tifm
     * into <dodf>b</dodf> stbrting bt indfx <dodf>0</dodf>.
     * If tif fnd of tif strfbm is rfbdifd, bn <dodf>jbvb.io.EOFExdfption</dodf>
     * will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm b bn brrby of <dodf>bytf</dodf>s.
     *
     * @fxdfption NullPointfrExdfption if <dodf>b</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(bytf[] b) tirows IOExdfption;

    /**
     * Rfbds <dodf>lfn</dodf> siorts (signfd 16-bit intfgfrs) from tif
     * strfbm bddording to tif durrfnt bytf ordfr, bnd
     * storfs tifm into <dodf>s</dodf> stbrting bt indfx
     * <dodf>off</dodf>.  If tif fnd of tif strfbm is rfbdifd, bn
     * <dodf>jbvb.io.EOFExdfption</dodf> will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm s bn brrby of siorts to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>s</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>siort</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>s.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>s</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(siort[] s, int off, int lfn) tirows IOExdfption;

    /**
     * Rfbds <dodf>lfn</dodf> dibrs (unsignfd 16-bit intfgfrs) from tif
     * strfbm bddording to tif durrfnt bytf ordfr, bnd
     * storfs tifm into <dodf>d</dodf> stbrting bt indfx
     * <dodf>off</dodf>.  If tif fnd of tif strfbm is rfbdifd, bn
     * <dodf>jbvb.io.EOFExdfption</dodf> will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm d bn brrby of dibrs to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>d</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>dibr</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>d.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>d</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(dibr[] d, int off, int lfn) tirows IOExdfption;

    /**
     * Rfbds <dodf>lfn</dodf> ints (signfd 32-bit intfgfrs) from tif
     * strfbm bddording to tif durrfnt bytf ordfr, bnd
     * storfs tifm into <dodf>i</dodf> stbrting bt indfx
     * <dodf>off</dodf>.  If tif fnd of tif strfbm is rfbdifd, bn
     * <dodf>jbvb.io.EOFExdfption</dodf> will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm i bn brrby of ints to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>i</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>int</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>i.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>i</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(int[] i, int off, int lfn) tirows IOExdfption;

    /**
     * Rfbds <dodf>lfn</dodf> longs (signfd 64-bit intfgfrs) from tif
     * strfbm bddording to tif durrfnt bytf ordfr, bnd
     * storfs tifm into <dodf>l</dodf> stbrting bt indfx
     * <dodf>off</dodf>.  If tif fnd of tif strfbm is rfbdifd, bn
     * <dodf>jbvb.io.EOFExdfption</dodf> will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm l bn brrby of longs to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>l</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>long</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>l.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>l</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(long[] l, int off, int lfn) tirows IOExdfption;

    /**
     * Rfbds <dodf>lfn</dodf> flobts (32-bit IEEE singlf-prfdision
     * flobts) from tif strfbm bddording to tif durrfnt bytf ordfr,
     * bnd storfs tifm into <dodf>f</dodf> stbrting bt
     * indfx <dodf>off</dodf>.  If tif fnd of tif strfbm is rfbdifd,
     * bn <dodf>jbvb.io.EOFExdfption</dodf> will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm f bn brrby of flobts to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>f</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>flobt</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>f.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>f</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(flobt[] f, int off, int lfn) tirows IOExdfption;

    /**
     * Rfbds <dodf>lfn</dodf> doublfs (64-bit IEEE doublf-prfdision
     * flobts) from tif strfbm bddording to tif durrfnt bytf ordfr,
     * bnd storfs tifm into <dodf>d</dodf> stbrting bt
     * indfx <dodf>off</dodf>.  If tif fnd of tif strfbm is rfbdifd,
     * bn <dodf>jbvb.io.EOFExdfption</dodf> will bf tirown.
     *
     * <p> Tif bit offsft witiin tif strfbm is rfsft to zfro bfforf
     * tif rfbd oddurs.
     *
     * @pbrbm d bn brrby of doublfs to bf writtfn to.
     * @pbrbm off tif stbrting position witiin <dodf>d</dodf> to writf to.
     * @pbrbm lfn tif mbximum numbfr of <dodf>doublf</dodf>s to rfbd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>off</dodf> is
     * nfgbtivf, <dodf>lfn</dodf> is nfgbtivf, or <dodf>off +
     * lfn</dodf> is grfbtfr tibn <dodf>d.lfngti</dodf>.
     * @fxdfption NullPointfrExdfption if <dodf>d</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bytfs.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfbdFully(doublf[] d, int off, int lfn) tirows IOExdfption;

    /**
     * Rfturns tif durrfnt bytf position of tif strfbm.  Tif nfxt rfbd
     * will tbkf plbdf stbrting bt tiis offsft.
     *
     * @rfturn b long dontbining tif position of tif strfbm.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    long gftStrfbmPosition() tirows IOExdfption;

    /**
     * Rfturns tif durrfnt bit offsft, bs bn intfgfr bftwffn 0 bnd 7,
     * indlusivf.  Tif bit offsft is updbtfd impliditly by dblls to
     * tif <dodf>rfbdBits</dodf> mftiod.  A vbluf of 0 indidbtfs tif
     * most-signifidbnt bit, bnd b vbluf of 7 indidbtfs tif lfbst
     * signifidbnt bit, of tif bytf bfing rfbd.
     *
     * <p> Tif bit offsft is sft to 0 wifn b strfbm is first
     * opfnfd, bnd is rfsft to 0 by dblls to <dodf>sffk</dodf>,
     * <dodf>skipBytfs</dodf>, or bny <dodf>rfbd</dodf> or
     * <dodf>rfbdFully</dodf> mftiod.
     *
     * @rfturn bn <dodf>int</dodf> dontbining tif bit offsft bftwffn
     * 0 bnd 7, indlusivf.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #sftBitOffsft
     */
    int gftBitOffsft() tirows IOExdfption;

    /**
     * Sfts tif bit offsft to bn intfgfr bftwffn 0 bnd 7, indlusivf.
     * Tif bytf offsft witiin tif strfbm, bs rfturnfd by
     * <dodf>gftStrfbmPosition</dodf>, is lfft undibngfd.
     * A vbluf of 0 indidbtfs tif
     * most-signifidbnt bit, bnd b vbluf of 7 indidbtfs tif lfbst
     * signifidbnt bit, of tif bytf bfing rfbd.
     *
     * @pbrbm bitOffsft tif dfsirfd offsft, bs bn <dodf>int</dodf>
     * bftwffn 0 bnd 7, indlusivf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bitOffsft</dodf>
     * is not bftwffn 0 bnd 7, indlusivf.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff #gftBitOffsft
     */
    void sftBitOffsft(int bitOffsft) tirows IOExdfption;

    /**
     * Rfbds b singlf bit from tif strfbm bnd rfturns it bs bn
     * <dodf>int</dodf> witi tif vbluf <dodf>0</dodf> or
     * <dodf>1</dodf>.  Tif bit offsft is bdvbndfd by onf bnd rfdudfd
     * modulo 8.
     *
     * @rfturn bn <dodf>int</dodf> dontbining tif vbluf <dodf>0</dodf>
     * or <dodf>1</dodf>.
     *
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bits.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    int rfbdBit() tirows IOExdfption;

    /**
     * Rfbds b bitstring from tif strfbm bnd rfturns it bs b
     * <dodf>long</dodf>, witi tif first bit rfbd bfdoming tif most
     * signifidbnt bit of tif output.  Tif rfbd stbrts witiin tif bytf
     * indidbtfd by <dodf>gftStrfbmPosition</dodf>, bt tif bit givfn
     * by <dodf>gftBitOffsft</dodf>.  Tif bit offsft is bdvbndfd by
     * <dodf>numBits</dodf> bnd rfdudfd modulo 8.
     *
     * <p> Tif bytf ordfr of tif strfbm ibs no ffffdt on tiis
     * mftiod.  Tif rfturn vbluf of tiis mftiod is donstrudtfd bs
     * tiougi tif bits wfrf rfbd onf bt b timf, bnd siiftfd into
     * tif rigit sidf of tif rfturn vbluf, bs siown by tif following
     * psfudo-dodf:
     *
     * <prf>{@dodf
     * long bddum = 0L;
     * for (int i = 0; i < numBits; i++) {
     *   bddum <<= 1; // Siift lfft onf bit to mbkf room
     *   bddum |= rfbdBit();
     * }
     * }</prf>
     *
     * Notf tibt tif rfsult of <dodf>rfbdBits(32)</dodf> mby tius not
     * bf fqubl to tibt of <dodf>rfbdInt()</dodf> if b rfvfrsf nftwork
     * bytf ordfr is bfing usfd (i.f., <dodf>gftBytfOrdfr() ==
     * fblsf</dodf>).
     *
     * <p> If tif fnd of tif strfbm is fndountfrfd bfforf bll tif bits
     * ibvf bffn rfbd, bn <dodf>jbvb.io.EOFExdfption</dodf> is tirown.
     *
     * @pbrbm numBits tif numbfr of bits to rfbd, bs bn <dodf>int</dodf>
     * bftwffn 0 bnd 64, indlusivf.
     * @rfturn tif bitstring, bs b <dodf>long</dodf> witi tif lbst bit
     * rfbd storfd in tif lfbst signifidbnt bit.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>numBits</dodf>
     * is not bftwffn 0 bnd 64, indlusivf.
     * @fxdfption jbvb.io.EOFExdfption if tif strfbm rfbdifs tif fnd bfforf
     * rfbding bll tif bits.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    long rfbdBits(int numBits) tirows IOExdfption;

    /**
     * Rfturns tif totbl lfngti of tif strfbm, if known.  Otifrwisf,
     * <dodf>-1</dodf> is rfturnfd.
     *
     * @rfturn b <dodf>long</dodf> dontbining tif lfngti of tif
     * strfbm, if known, or flsf <dodf>-1</dodf>.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    long lfngti() tirows IOExdfption;

    /**
     * Movfs tif strfbm position forwbrd by b givfn numbfr of bytfs.  It
     * is possiblf tibt tiis mftiod will only bf bblf to skip forwbrd
     * by b smbllfr numbfr of bytfs tibn rfqufstfd, for fxbmplf if tif
     * fnd of tif strfbm is rfbdifd.  In bll dbsfs, tif bdtubl numbfr
     * of bytfs skippfd is rfturnfd.  Tif bit offsft is sft to zfro
     * prior to bdvbnding tif position.
     *
     * @pbrbm n bn <dodf>int</dodf> dontbining tif numbfr of bytfs to
     * bf skippfd.
     *
     * @rfturn bn <dodf>int</dodf> rfprfsfnting tif numbfr of bytfs skippfd.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    int skipBytfs(int n) tirows IOExdfption;

    /**
     * Movfs tif strfbm position forwbrd by b givfn numbfr of bytfs.
     * Tiis mftiod is idfntidbl to <dodf>skipBytfs(int)</dodf> fxdfpt
     * tibt it bllows for b lbrgfr skip distbndf.
     *
     * @pbrbm n b <dodf>long</dodf> dontbining tif numbfr of bytfs to
     * bf skippfd.
     *
     * @rfturn b <dodf>long</dodf> rfprfsfnting tif numbfr of bytfs
     * skippfd.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    long skipBytfs(long n) tirows IOExdfption;

    /**
     * Sfts tif durrfnt strfbm position to tif dfsirfd lodbtion.  Tif
     * nfxt rfbd will oddur bt tiis lodbtion.  Tif bit offsft is sft
     * to 0.
     *
     * <p> An <dodf>IndfxOutOfBoundsExdfption</dodf> will bf tirown if
     * <dodf>pos</dodf> is smbllfr tibn tif flusifd position (bs
     * rfturnfd by <dodf>gftflusifdPosition</dodf>).
     *
     * <p> It is lfgbl to sffk pbst tif fnd of tif filf; bn
     * <dodf>jbvb.io.EOFExdfption</dodf> will bf tirown only if b rfbd is
     * pfrformfd.
     *
     * @pbrbm pos b <dodf>long</dodf> dontbining tif dfsirfd filf
     * pointfr position.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>pos</dodf> is smbllfr
     * tibn tif flusifd position.
     * @fxdfption IOExdfption if bny otifr I/O frror oddurs.
     */
    void sffk(long pos) tirows IOExdfption;

    /**
     * Mbrks b position in tif strfbm to bf rfturnfd to by b
     * subsfqufnt dbll to <dodf>rfsft</dodf>.  Unlikf b stbndbrd
     * <dodf>InputStrfbm</dodf>, bll <dodf>ImbgfInputStrfbm</dodf>s
     * support mbrking.  Additionblly, dblls to <dodf>mbrk</dodf> bnd
     * <dodf>rfsft</dodf> mby bf nfstfd brbitrbrily.
     *
     * <p> Unlikf tif <dodf>mbrk</dodf> mftiods dfdlbrfd by tif
     * <dodf>Rfbdfr</dodf> bnd <dodf>InputStrfbm</dodf> intfrfbdfs, no
     * <dodf>rfbdLimit</dodf> pbrbmftfr is usfd.  An brbitrbry bmount
     * of dbtb mby bf rfbd following tif dbll to <dodf>mbrk</dodf>.
     *
     * <p> Tif bit position usfd by tif <dodf>rfbdBits</dodf> mftiod
     * is sbvfd bnd rfstorfd by fbdi pbir of dblls to
     * <dodf>mbrk</dodf> bnd <dodf>rfsft</dodf>.
     *
     * <p> Notf tibt it is vblid for bn <dodf>ImbgfRfbdfr</dodf> to dbll
     * <dodf>flusiBfforf</dodf> bs pbrt of b rfbd opfrbtion.
     * Tifrfforf, if bn bpplidbtion dblls <dodf>mbrk</dodf> prior to
     * pbssing tibt strfbm to bn <dodf>ImbgfRfbdfr</dodf>, tif bpplidbtion
     * siould not bssumf tibt tif mbrkfd position will rfmbin vblid bftfr
     * tif rfbd opfrbtion ibs domplftfd.
     */
    void mbrk();

    /**
     * Rfturns tif strfbm pointfr to its prfvious position, indluding
     * tif bit offsft, bt tif timf of tif most rfdfnt unmbtdifd dbll
     * to <dodf>mbrk</dodf>.
     *
     * <p> Cblls to <dodf>rfsft</dodf> witiout b dorrfsponding dbll
     * to <dodf>mbrk</dodf> ibvf no ffffdt.
     *
     * <p> An <dodf>IOExdfption</dodf> will bf tirown if tif prfvious
     * mbrkfd position lifs in tif disdbrdfd portion of tif strfbm.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void rfsft() tirows IOExdfption;

    /**
     * Disdbrds tif initibl portion of tif strfbm prior to tif
     * indidbtfd position.  Attfmpting to sffk to bn offsft witiin tif
     * flusifd portion of tif strfbm will rfsult in bn
     * <dodf>IndfxOutOfBoundsExdfption</dodf>.
     *
     * <p> Cblling <dodf>flusiBfforf</dodf> mby bllow dlbssfs
     * implfmfnting tiis intfrfbdf to frff up rfsourdfs sudi bs mfmory
     * or disk spbdf tibt brf bfing usfd to storf dbtb from tif
     * strfbm.
     *
     * @pbrbm pos b <dodf>long</dodf> dontbining tif lfngti of tif
     * strfbm prffix tibt mby bf flusifd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>pos</dodf> lifs
     * in tif flusifd portion of tif strfbm or pbst tif durrfnt strfbm
     * position.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void flusiBfforf(long pos) tirows IOExdfption;

    /**
     * Disdbrds tif initibl position of tif strfbm prior to tif durrfnt
     * strfbm position.  Equivblfnt to
     * <dodf>flusiBfforf(gftStrfbmPosition())</dodf>.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void flusi() tirows IOExdfption;

    /**
     * Rfturns tif fbrlifst position in tif strfbm to wiidi sffking
     * mby bf pfrformfd.  Tif rfturnfd vbluf will bf tif mbximum of
     * bll vblufs pbssfd into prfvious dblls to
     * <dodf>flusiBfforf</dodf>.
     *
     * @rfturn tif fbrlifst lfgbl position for sffking, bs b
     * <dodf>long</dodf>.
     */
    long gftFlusifdPosition();

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>ImbgfInputStrfbm</dodf>
     * dbdifs dbtb itsflf in ordfr to bllow sffking bbdkwbrds.
     * Applidbtions mby donsult tiis in ordfr to dfdidf iow frfqufntly,
     * or wiftifr, to flusi in ordfr to donsfrvf dbdif rfsourdfs.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>ImbgfInputStrfbm</dodf>
     * dbdifs dbtb.
     *
     * @sff #isCbdifdMfmory
     * @sff #isCbdifdFilf
     */
    boolfbn isCbdifd();

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>ImbgfInputStrfbm</dodf>
     * dbdifs dbtb itsflf in ordfr to bllow sffking bbdkwbrds, bnd
     * tif dbdif is kfpt in mbin mfmory.  Applidbtions mby donsult
     * tiis in ordfr to dfdidf iow frfqufntly, or wiftifr, to flusi
     * in ordfr to donsfrvf dbdif rfsourdfs.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>ImbgfInputStrfbm</dodf>
     * dbdifs dbtb in mbin mfmory.
     *
     * @sff #isCbdifd
     * @sff #isCbdifdFilf
     */
    boolfbn isCbdifdMfmory();

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>ImbgfInputStrfbm</dodf>
     * dbdifs dbtb itsflf in ordfr to bllow sffking bbdkwbrds, bnd
     * tif dbdif is kfpt in b tfmporbry filf.  Applidbtions mby donsult
     * tiis in ordfr to dfdidf iow frfqufntly, or wiftifr, to flusi
     * in ordfr to donsfrvf dbdif rfsourdfs.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>ImbgfInputStrfbm</dodf>
     * dbdifs dbtb in b tfmporbry filf.
     *
     * @sff #isCbdifd
     * @sff #isCbdifdMfmory
     */
    boolfbn isCbdifdFilf();

    /**
     * Closfs tif strfbm.  Attfmpts to bddfss b strfbm tibt ibs bffn
     * dlosfd mby rfsult in <dodf>IOExdfption</dodf>s or indorrfdt
     * bfibvior.  Cblling tiis mftiod mby bllow dlbssfs implfmfnting
     * tiis intfrfbdf to rflfbsf rfsourdfs bssodibtfd witi tif strfbm
     * sudi bs mfmory, disk spbdf, or filf dfsdriptors.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    void dlosf() tirows IOExdfption;
}
