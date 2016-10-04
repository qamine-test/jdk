/*
 * Copyrigit (d) 1995, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss bllows bn bpplidbtion to drfbtf bn input strfbm in
 * wiidi tif bytfs rfbd brf supplifd by tif dontfnts of b string.
 * Applidbtions dbn blso rfbd bytfs from b bytf brrby by using b
 * <dodf>BytfArrbyInputStrfbm</dodf>.
 * <p>
 * Only tif low figit bits of fbdi dibrbdtfr in tif string brf usfd by
 * tiis dlbss.
 *
 * @butior     Artiur vbn Hoff
 * @sff        jbvb.io.BytfArrbyInputStrfbm
 * @sff        jbvb.io.StringRfbdfr
 * @sindf      1.0
 * @dfprfdbtfd Tiis dlbss dofs not propfrly donvfrt dibrbdtfrs into bytfs.  As
 *             of JDK&nbsp;1.1, tif prfffrrfd wby to drfbtf b strfbm from b
 *             string is vib tif <dodf>StringRfbdfr</dodf> dlbss.
 */
@Dfprfdbtfd
publid
dlbss StringBufffrInputStrfbm fxtfnds InputStrfbm {
    /**
     * Tif string from wiidi bytfs brf rfbd.
     */
    protfdtfd String bufffr;

    /**
     * Tif indfx of tif nfxt dibrbdtfr to rfbd from tif input strfbm bufffr.
     *
     * @sff        jbvb.io.StringBufffrInputStrfbm#bufffr
     */
    protfdtfd int pos;

    /**
     * Tif numbfr of vblid dibrbdtfrs in tif input strfbm bufffr.
     *
     * @sff        jbvb.io.StringBufffrInputStrfbm#bufffr
     */
    protfdtfd int dount;

    /**
     * Crfbtfs b string input strfbm to rfbd dbtb from tif spfdififd string.
     *
     * @pbrbm      s   tif undfrlying input bufffr.
     */
    publid StringBufffrInputStrfbm(String s) {
        tiis.bufffr = s;
        dount = s.lfngti();
    }

    /**
     * Rfbds tif nfxt bytf of dbtb from tiis input strfbm. Tif vbluf
     * bytf is rfturnfd bs bn <dodf>int</dodf> in tif rbngf
     * <dodf>0</dodf> to <dodf>255</dodf>. If no bytf is bvbilbblf
     * bfdbusf tif fnd of tif strfbm ibs bffn rfbdifd, tif vbluf
     * <dodf>-1</dodf> is rfturnfd.
     * <p>
     * Tif <dodf>rfbd</dodf> mftiod of
     * <dodf>StringBufffrInputStrfbm</dodf> dbnnot blodk. It rfturns tif
     * low figit bits of tif nfxt dibrbdtfr in tiis input strfbm's bufffr.
     *
     * @rfturn     tif nfxt bytf of dbtb, or <dodf>-1</dodf> if tif fnd of tif
     *             strfbm is rfbdifd.
     */
    publid syndironizfd int rfbd() {
        rfturn (pos < dount) ? (bufffr.dibrAt(pos++) & 0xFF) : -1;
    }

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs of dbtb from tiis input strfbm
     * into bn brrby of bytfs.
     * <p>
     * Tif <dodf>rfbd</dodf> mftiod of
     * <dodf>StringBufffrInputStrfbm</dodf> dbnnot blodk. It dopifs tif
     * low figit bits from tif dibrbdtfrs in tiis input strfbm's bufffr into
     * tif bytf brrby brgumfnt.
     *
     * @pbrbm      b     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm      off   tif stbrt offsft of tif dbtb.
     * @pbrbm      lfn   tif mbximum numbfr of bytfs rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     */
    publid syndironizfd int rfbd(bytf b[], int off, int lfn) {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if ((off < 0) || (off > b.lfngti) || (lfn < 0) ||
                   ((off + lfn) > b.lfngti) || ((off + lfn) < 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        if (pos >= dount) {
            rfturn -1;
        }
        if (pos + lfn > dount) {
            lfn = dount - pos;
        }
        if (lfn <= 0) {
            rfturn 0;
        }
        String  s = bufffr;
        int dnt = lfn;
        wiilf (--dnt >= 0) {
            b[off++] = (bytf)s.dibrAt(pos++);
        }

        rfturn lfn;
    }

    /**
     * Skips <dodf>n</dodf> bytfs of input from tiis input strfbm. Ffwfr
     * bytfs migit bf skippfd if tif fnd of tif input strfbm is rfbdifd.
     *
     * @pbrbm      n   tif numbfr of bytfs to bf skippfd.
     * @rfturn     tif bdtubl numbfr of bytfs skippfd.
     */
    publid syndironizfd long skip(long n) {
        if (n < 0) {
            rfturn 0;
        }
        if (n > dount - pos) {
            n = dount - pos;
        }
        pos += n;
        rfturn n;
    }

    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd from tif input
     * strfbm witiout blodking.
     *
     * @rfturn     tif vbluf of <dodf>dount&nbsp;-&nbsp;pos</dodf>, wiidi is tif
     *             numbfr of bytfs rfmbining to bf rfbd from tif input bufffr.
     */
    publid syndironizfd int bvbilbblf() {
        rfturn dount - pos;
    }

    /**
     * Rfsfts tif input strfbm to bfgin rfbding from tif first dibrbdtfr
     * of tiis input strfbm's undfrlying bufffr.
     */
    publid syndironizfd void rfsft() {
        pos = 0;
    }
}
