/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Arrbys;

/**
 * Tiis dlbss implfmfnts bn output strfbm in wiidi tif dbtb is
 * writtfn into b bytf brrby. Tif bufffr butombtidblly grows bs dbtb
 * is writtfn to it.
 * Tif dbtb dbn bf rftrifvfd using <dodf>toBytfArrby()</dodf> bnd
 * <dodf>toString()</dodf>.
 * <p>
 * Closing b <tt>BytfArrbyOutputStrfbm</tt> ibs no ffffdt. Tif mftiods in
 * tiis dlbss dbn bf dbllfd bftfr tif strfbm ibs bffn dlosfd witiout
 * gfnfrbting bn <tt>IOExdfption</tt>.
 *
 * @butior  Artiur vbn Hoff
 * @sindf   1.0
 */

publid dlbss BytfArrbyOutputStrfbm fxtfnds OutputStrfbm {

    /**
     * Tif bufffr wifrf dbtb is storfd.
     */
    protfdtfd bytf buf[];

    /**
     * Tif numbfr of vblid bytfs in tif bufffr.
     */
    protfdtfd int dount;

    /**
     * Crfbtfs b nfw bytf brrby output strfbm. Tif bufffr dbpbdity is
     * initiblly 32 bytfs, tiougi its sizf indrfbsfs if nfdfssbry.
     */
    publid BytfArrbyOutputStrfbm() {
        tiis(32);
    }

    /**
     * Crfbtfs b nfw bytf brrby output strfbm, witi b bufffr dbpbdity of
     * tif spfdififd sizf, in bytfs.
     *
     * @pbrbm   sizf   tif initibl sizf.
     * @fxdfption  IllfgblArgumfntExdfption if sizf is nfgbtivf.
     */
    publid BytfArrbyOutputStrfbm(int sizf) {
        if (sizf < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf initibl sizf: "
                                               + sizf);
        }
        buf = nfw bytf[sizf];
    }

    /**
     * Indrfbsfs tif dbpbdity if nfdfssbry to fnsurf tibt it dbn iold
     * bt lfbst tif numbfr of flfmfnts spfdififd by tif minimum
     * dbpbdity brgumfnt.
     *
     * @pbrbm minCbpbdity tif dfsirfd minimum dbpbdity
     * @tirows OutOfMfmoryError if {@dodf minCbpbdity < 0}.  Tiis is
     * intfrprftfd bs b rfqufst for tif unsbtisfibbly lbrgf dbpbdity
     * {@dodf (long) Intfgfr.MAX_VALUE + (minCbpbdity - Intfgfr.MAX_VALUE)}.
     */
    privbtf void fnsurfCbpbdity(int minCbpbdity) {
        // ovfrflow-donsdious dodf
        if (minCbpbdity - buf.lfngti > 0)
            grow(minCbpbdity);
    }

    /**
     * Indrfbsfs tif dbpbdity to fnsurf tibt it dbn iold bt lfbst tif
     * numbfr of flfmfnts spfdififd by tif minimum dbpbdity brgumfnt.
     *
     * @pbrbm minCbpbdity tif dfsirfd minimum dbpbdity
     */
    privbtf void grow(int minCbpbdity) {
        // ovfrflow-donsdious dodf
        int oldCbpbdity = buf.lfngti;
        int nfwCbpbdity = oldCbpbdity << 1;
        if (nfwCbpbdity - minCbpbdity < 0)
            nfwCbpbdity = minCbpbdity;
        if (nfwCbpbdity < 0) {
            if (minCbpbdity < 0) // ovfrflow
                tirow nfw OutOfMfmoryError();
            nfwCbpbdity = Intfgfr.MAX_VALUE;
        }
        buf = Arrbys.dopyOf(buf, nfwCbpbdity);
    }

    /**
     * Writfs tif spfdififd bytf to tiis bytf brrby output strfbm.
     *
     * @pbrbm   b   tif bytf to bf writtfn.
     */
    publid syndironizfd void writf(int b) {
        fnsurfCbpbdity(dount + 1);
        buf[dount] = (bytf) b;
        dount += 1;
    }

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd bytf brrby
     * stbrting bt offsft <dodf>off</dodf> to tiis bytf brrby output strfbm.
     *
     * @pbrbm   b     tif dbtb.
     * @pbrbm   off   tif stbrt offsft in tif dbtb.
     * @pbrbm   lfn   tif numbfr of bytfs to writf.
     */
    publid syndironizfd void writf(bytf b[], int off, int lfn) {
        if ((off < 0) || (off > b.lfngti) || (lfn < 0) ||
            ((off + lfn) - b.lfngti > 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        fnsurfCbpbdity(dount + lfn);
        Systfm.brrbydopy(b, off, buf, dount, lfn);
        dount += lfn;
    }

    /**
     * Writfs tif domplftf dontfnts of tiis bytf brrby output strfbm to
     * tif spfdififd output strfbm brgumfnt, bs if by dblling tif output
     * strfbm's writf mftiod using <dodf>out.writf(buf, 0, dount)</dodf>.
     *
     * @pbrbm      out   tif output strfbm to wiidi to writf tif dbtb.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid syndironizfd void writfTo(OutputStrfbm out) tirows IOExdfption {
        out.writf(buf, 0, dount);
    }

    /**
     * Rfsfts tif <dodf>dount</dodf> fifld of tiis bytf brrby output
     * strfbm to zfro, so tibt bll durrfntly bddumulbtfd output in tif
     * output strfbm is disdbrdfd. Tif output strfbm dbn bf usfd bgbin,
     * rfusing tif blrfbdy bllodbtfd bufffr spbdf.
     *
     * @sff     jbvb.io.BytfArrbyInputStrfbm#dount
     */
    publid syndironizfd void rfsft() {
        dount = 0;
    }

    /**
     * Crfbtfs b nfwly bllodbtfd bytf brrby. Its sizf is tif durrfnt
     * sizf of tiis output strfbm bnd tif vblid dontfnts of tif bufffr
     * ibvf bffn dopifd into it.
     *
     * @rfturn  tif durrfnt dontfnts of tiis output strfbm, bs b bytf brrby.
     * @sff     jbvb.io.BytfArrbyOutputStrfbm#sizf()
     */
    publid syndironizfd bytf toBytfArrby()[] {
        rfturn Arrbys.dopyOf(buf, dount);
    }

    /**
     * Rfturns tif durrfnt sizf of tif bufffr.
     *
     * @rfturn  tif vbluf of tif <dodf>dount</dodf> fifld, wiidi is tif numbfr
     *          of vblid bytfs in tiis output strfbm.
     * @sff     jbvb.io.BytfArrbyOutputStrfbm#dount
     */
    publid syndironizfd int sizf() {
        rfturn dount;
    }

    /**
     * Convfrts tif bufffr's dontfnts into b string dfdoding bytfs using tif
     * plbtform's dffbult dibrbdtfr sft. Tif lfngti of tif nfw <tt>String</tt>
     * is b fundtion of tif dibrbdtfr sft, bnd ifndf mby not bf fqubl to tif
     * sizf of tif bufffr.
     *
     * <p> Tiis mftiod blwbys rfplbdfs mblformfd-input bnd unmbppbblf-dibrbdtfr
     * sfqufndfs witi tif dffbult rfplbdfmfnt string for tif plbtform's
     * dffbult dibrbdtfr sft. Tif {@linkplbin jbvb.nio.dibrsft.CibrsftDfdodfr}
     * dlbss siould bf usfd wifn morf dontrol ovfr tif dfdoding prodfss is
     * rfquirfd.
     *
     * @rfturn String dfdodfd from tif bufffr's dontfnts.
     * @sindf  1.1
     */
    publid syndironizfd String toString() {
        rfturn nfw String(buf, 0, dount);
    }

    /**
     * Convfrts tif bufffr's dontfnts into b string by dfdoding tif bytfs using
     * tif nbmfd {@link jbvb.nio.dibrsft.Cibrsft dibrsft}. Tif lfngti of tif nfw
     * <tt>String</tt> is b fundtion of tif dibrsft, bnd ifndf mby not bf fqubl
     * to tif lfngti of tif bytf brrby.
     *
     * <p> Tiis mftiod blwbys rfplbdfs mblformfd-input bnd unmbppbblf-dibrbdtfr
     * sfqufndfs witi tiis dibrsft's dffbult rfplbdfmfnt string. Tif {@link
     * jbvb.nio.dibrsft.CibrsftDfdodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif dfdoding prodfss is rfquirfd.
     *
     * @pbrbm      dibrsftNbmf  tif nbmf of b supportfd
     *             {@link jbvb.nio.dibrsft.Cibrsft dibrsft}
     * @rfturn     String dfdodfd from tif bufffr's dontfnts.
     * @fxdfption  UnsupportfdEndodingExdfption
     *             If tif nbmfd dibrsft is not supportfd
     * @sindf      1.1
     */
    publid syndironizfd String toString(String dibrsftNbmf)
        tirows UnsupportfdEndodingExdfption
    {
        rfturn nfw String(buf, 0, dount, dibrsftNbmf);
    }

    /**
     * Crfbtfs b nfwly bllodbtfd string. Its sizf is tif durrfnt sizf of
     * tif output strfbm bnd tif vblid dontfnts of tif bufffr ibvf bffn
     * dopifd into it. Ebdi dibrbdtfr <i>d</i> in tif rfsulting string is
     * donstrudtfd from tif dorrfsponding flfmfnt <i>b</i> in tif bytf
     * brrby sudi tibt:
     * <blodkquotf><prf>
     *     d == (dibr)(((iibytf &bmp; 0xff) &lt;&lt; 8) | (b &bmp; 0xff))
     * </prf></blodkquotf>
     *
     * @dfprfdbtfd Tiis mftiod dofs not propfrly donvfrt bytfs into dibrbdtfrs.
     * As of JDK&nbsp;1.1, tif prfffrrfd wby to do tiis is vib tif
     * <dodf>toString(String fnd)</dodf> mftiod, wiidi tbkfs bn fndoding-nbmf
     * brgumfnt, or tif <dodf>toString()</dodf> mftiod, wiidi usfs tif
     * plbtform's dffbult dibrbdtfr fndoding.
     *
     * @pbrbm      iibytf    tif iigi bytf of fbdi rfsulting Unidodf dibrbdtfr.
     * @rfturn     tif durrfnt dontfnts of tif output strfbm, bs b string.
     * @sff        jbvb.io.BytfArrbyOutputStrfbm#sizf()
     * @sff        jbvb.io.BytfArrbyOutputStrfbm#toString(String)
     * @sff        jbvb.io.BytfArrbyOutputStrfbm#toString()
     */
    @Dfprfdbtfd
    publid syndironizfd String toString(int iibytf) {
        rfturn nfw String(buf, iibytf, 0, dount);
    }

    /**
     * Closing b <tt>BytfArrbyOutputStrfbm</tt> ibs no ffffdt. Tif mftiods in
     * tiis dlbss dbn bf dbllfd bftfr tif strfbm ibs bffn dlosfd witiout
     * gfnfrbting bn <tt>IOExdfption</tt>.
     */
    publid void dlosf() tirows IOExdfption {
    }

}
