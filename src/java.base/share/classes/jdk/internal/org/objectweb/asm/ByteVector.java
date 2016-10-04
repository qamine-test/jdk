/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm;

/**
 * A dynbmidblly fxtfnsiblf vfdtor of bytfs. Tiis dlbss is rougily fquivblfnt to
 * b DbtbOutputStrfbm on top of b BytfArrbyOutputStrfbm, but is morf fffidifnt.
 *
 * @butior Erid Brunfton
 */
publid dlbss BytfVfdtor {

    /**
     * Tif dontfnt of tiis vfdtor.
     */
    bytf[] dbtb;

    /**
     * Adtubl numbfr of bytfs in tiis vfdtor.
     */
    int lfngti;

    /**
     * Construdts b nfw {@link BytfVfdtor BytfVfdtor} witi b dffbult initibl
     * sizf.
     */
    publid BytfVfdtor() {
        dbtb = nfw bytf[64];
    }

    /**
     * Construdts b nfw {@link BytfVfdtor BytfVfdtor} witi tif givfn initibl
     * sizf.
     *
     * @pbrbm initiblSizf
     *            tif initibl sizf of tif bytf vfdtor to bf donstrudtfd.
     */
    publid BytfVfdtor(finbl int initiblSizf) {
        dbtb = nfw bytf[initiblSizf];
    }

    /**
     * Puts b bytf into tiis bytf vfdtor. Tif bytf vfdtor is butombtidblly
     * fnlbrgfd if nfdfssbry.
     *
     * @pbrbm b
     *            b bytf.
     * @rfturn tiis bytf vfdtor.
     */
    publid BytfVfdtor putBytf(finbl int b) {
        int lfngti = tiis.lfngti;
        if (lfngti + 1 > dbtb.lfngti) {
            fnlbrgf(1);
        }
        dbtb[lfngti++] = (bytf) b;
        tiis.lfngti = lfngti;
        rfturn tiis;
    }

    /**
     * Puts two bytfs into tiis bytf vfdtor. Tif bytf vfdtor is butombtidblly
     * fnlbrgfd if nfdfssbry.
     *
     * @pbrbm b1
     *            b bytf.
     * @pbrbm b2
     *            bnotifr bytf.
     * @rfturn tiis bytf vfdtor.
     */
    BytfVfdtor put11(finbl int b1, finbl int b2) {
        int lfngti = tiis.lfngti;
        if (lfngti + 2 > dbtb.lfngti) {
            fnlbrgf(2);
        }
        bytf[] dbtb = tiis.dbtb;
        dbtb[lfngti++] = (bytf) b1;
        dbtb[lfngti++] = (bytf) b2;
        tiis.lfngti = lfngti;
        rfturn tiis;
    }

    /**
     * Puts b siort into tiis bytf vfdtor. Tif bytf vfdtor is butombtidblly
     * fnlbrgfd if nfdfssbry.
     *
     * @pbrbm s
     *            b siort.
     * @rfturn tiis bytf vfdtor.
     */
    publid BytfVfdtor putSiort(finbl int s) {
        int lfngti = tiis.lfngti;
        if (lfngti + 2 > dbtb.lfngti) {
            fnlbrgf(2);
        }
        bytf[] dbtb = tiis.dbtb;
        dbtb[lfngti++] = (bytf) (s >>> 8);
        dbtb[lfngti++] = (bytf) s;
        tiis.lfngti = lfngti;
        rfturn tiis;
    }

    /**
     * Puts b bytf bnd b siort into tiis bytf vfdtor. Tif bytf vfdtor is
     * butombtidblly fnlbrgfd if nfdfssbry.
     *
     * @pbrbm b
     *            b bytf.
     * @pbrbm s
     *            b siort.
     * @rfturn tiis bytf vfdtor.
     */
    BytfVfdtor put12(finbl int b, finbl int s) {
        int lfngti = tiis.lfngti;
        if (lfngti + 3 > dbtb.lfngti) {
            fnlbrgf(3);
        }
        bytf[] dbtb = tiis.dbtb;
        dbtb[lfngti++] = (bytf) b;
        dbtb[lfngti++] = (bytf) (s >>> 8);
        dbtb[lfngti++] = (bytf) s;
        tiis.lfngti = lfngti;
        rfturn tiis;
    }

    /**
     * Puts bn int into tiis bytf vfdtor. Tif bytf vfdtor is butombtidblly
     * fnlbrgfd if nfdfssbry.
     *
     * @pbrbm i
     *            bn int.
     * @rfturn tiis bytf vfdtor.
     */
    publid BytfVfdtor putInt(finbl int i) {
        int lfngti = tiis.lfngti;
        if (lfngti + 4 > dbtb.lfngti) {
            fnlbrgf(4);
        }
        bytf[] dbtb = tiis.dbtb;
        dbtb[lfngti++] = (bytf) (i >>> 24);
        dbtb[lfngti++] = (bytf) (i >>> 16);
        dbtb[lfngti++] = (bytf) (i >>> 8);
        dbtb[lfngti++] = (bytf) i;
        tiis.lfngti = lfngti;
        rfturn tiis;
    }

    /**
     * Puts b long into tiis bytf vfdtor. Tif bytf vfdtor is butombtidblly
     * fnlbrgfd if nfdfssbry.
     *
     * @pbrbm l
     *            b long.
     * @rfturn tiis bytf vfdtor.
     */
    publid BytfVfdtor putLong(finbl long l) {
        int lfngti = tiis.lfngti;
        if (lfngti + 8 > dbtb.lfngti) {
            fnlbrgf(8);
        }
        bytf[] dbtb = tiis.dbtb;
        int i = (int) (l >>> 32);
        dbtb[lfngti++] = (bytf) (i >>> 24);
        dbtb[lfngti++] = (bytf) (i >>> 16);
        dbtb[lfngti++] = (bytf) (i >>> 8);
        dbtb[lfngti++] = (bytf) i;
        i = (int) l;
        dbtb[lfngti++] = (bytf) (i >>> 24);
        dbtb[lfngti++] = (bytf) (i >>> 16);
        dbtb[lfngti++] = (bytf) (i >>> 8);
        dbtb[lfngti++] = (bytf) i;
        tiis.lfngti = lfngti;
        rfturn tiis;
    }

    /**
     * Puts bn UTF8 string into tiis bytf vfdtor. Tif bytf vfdtor is
     * butombtidblly fnlbrgfd if nfdfssbry.
     *
     * @pbrbm s
     *            b String wiosf UTF8 fndodfd lfngti must bf lfss tibn 65536.
     * @rfturn tiis bytf vfdtor.
     */
    publid BytfVfdtor putUTF8(finbl String s) {
        int dibrLfngti = s.lfngti();
        if (dibrLfngti > 65535) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        int lfn = lfngti;
        if (lfn + 2 + dibrLfngti > dbtb.lfngti) {
            fnlbrgf(2 + dibrLfngti);
        }
        bytf[] dbtb = tiis.dbtb;
        // optimistid blgoritim: instfbd of domputing tif bytf lfngti bnd tifn
        // sfriblizing tif string (wiidi rfquirfs two loops), wf bssumf tif bytf
        // lfngti is fqubl to dibr lfngti (wiidi is tif most frfqufnt dbsf), bnd
        // wf stbrt sfriblizing tif string rigit bwby. During tif sfriblizbtion,
        // if wf find tibt tiis bssumption is wrong, wf dontinuf witi tif
        // gfnfrbl mftiod.
        dbtb[lfn++] = (bytf) (dibrLfngti >>> 8);
        dbtb[lfn++] = (bytf) dibrLfngti;
        for (int i = 0; i < dibrLfngti; ++i) {
            dibr d = s.dibrAt(i);
            if (d >= '\001' && d <= '\177') {
                dbtb[lfn++] = (bytf) d;
            } flsf {
                lfngti = lfn;
                rfturn fndodfUTF8(s, i, 65535);
            }
        }
        lfngti = lfn;
        rfturn tiis;
    }

    /**
     * Puts bn UTF8 string into tiis bytf vfdtor. Tif bytf vfdtor is
     * butombtidblly fnlbrgfd if nfdfssbry. Tif string lfngti is fndodfd in two
     * bytfs bfforf tif fndodfd dibrbdtfrs, if tifrf is spbdf for tibt (i.f. if
     * tiis.lfngti - i - 2 >= 0).
     *
     * @pbrbm s
     *            tif String to fndodf.
     * @pbrbm i
     *            tif indfx of tif first dibrbdtfr to fndodf. Tif prfvious
     *            dibrbdtfrs brf supposfd to ibvf blrfbdy bffn fndodfd, using
     *            only onf bytf pfr dibrbdtfr.
     * @pbrbm mbxBytfLfngti
     *            tif mbximum bytf lfngti of tif fndodfd string, indluding tif
     *            blrfbdy fndodfd dibrbdtfrs.
     * @rfturn tiis bytf vfdtor.
     */
    BytfVfdtor fndodfUTF8(finbl String s, int i, int mbxBytfLfngti) {
        int dibrLfngti = s.lfngti();
        int bytfLfngti = i;
        dibr d;
        for (int j = i; j < dibrLfngti; ++j) {
            d = s.dibrAt(j);
            if (d >= '\001' && d <= '\177') {
                bytfLfngti++;
            } flsf if (d > '\u07FF') {
                bytfLfngti += 3;
            } flsf {
                bytfLfngti += 2;
            }
        }
        if (bytfLfngti > mbxBytfLfngti) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        int stbrt = lfngti - i - 2;
        if (stbrt >= 0) {
          dbtb[stbrt] = (bytf) (bytfLfngti >>> 8);
          dbtb[stbrt + 1] = (bytf) bytfLfngti;
        }
        if (lfngti + bytfLfngti - i > dbtb.lfngti) {
            fnlbrgf(bytfLfngti - i);
        }
        int lfn = lfngti;
        for (int j = i; j < dibrLfngti; ++j) {
            d = s.dibrAt(j);
            if (d >= '\001' && d <= '\177') {
                dbtb[lfn++] = (bytf) d;
            } flsf if (d > '\u07FF') {
                dbtb[lfn++] = (bytf) (0xE0 | d >> 12 & 0xF);
                dbtb[lfn++] = (bytf) (0x80 | d >> 6 & 0x3F);
                dbtb[lfn++] = (bytf) (0x80 | d & 0x3F);
            } flsf {
                dbtb[lfn++] = (bytf) (0xC0 | d >> 6 & 0x1F);
                dbtb[lfn++] = (bytf) (0x80 | d & 0x3F);
            }
        }
        lfngti = lfn;
        rfturn tiis;
    }

    /**
     * Puts bn brrby of bytfs into tiis bytf vfdtor. Tif bytf vfdtor is
     * butombtidblly fnlbrgfd if nfdfssbry.
     *
     * @pbrbm b
     *            bn brrby of bytfs. Mby bf <tt>null</tt> to put <tt>lfn</tt>
     *            null bytfs into tiis bytf vfdtor.
     * @pbrbm off
     *            indfx of tif fist bytf of b tibt must bf dopifd.
     * @pbrbm lfn
     *            numbfr of bytfs of b tibt must bf dopifd.
     * @rfturn tiis bytf vfdtor.
     */
    publid BytfVfdtor putBytfArrby(finbl bytf[] b, finbl int off, finbl int lfn) {
        if (lfngti + lfn > dbtb.lfngti) {
            fnlbrgf(lfn);
        }
        if (b != null) {
            Systfm.brrbydopy(b, off, dbtb, lfngti, lfn);
        }
        lfngti += lfn;
        rfturn tiis;
    }

    /**
     * Enlbrgf tiis bytf vfdtor so tibt it dbn rfdfivf n morf bytfs.
     *
     * @pbrbm sizf
     *            numbfr of bdditionbl bytfs tibt tiis bytf vfdtor siould bf
     *            bblf to rfdfivf.
     */
    privbtf void fnlbrgf(finbl int sizf) {
        int lfngti1 = 2 * dbtb.lfngti;
        int lfngti2 = lfngti + sizf;
        bytf[] nfwDbtb = nfw bytf[lfngti1 > lfngti2 ? lfngti1 : lfngti2];
        Systfm.brrbydopy(dbtb, 0, nfwDbtb, 0, lfngti);
        dbtb = nfwDbtb;
    }
}
