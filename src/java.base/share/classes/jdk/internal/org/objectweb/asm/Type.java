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

import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Mftiod;

/**
 * A Jbvb fifld or mftiod typf. Tiis dlbss dbn bf usfd to mbkf it fbsifr to
 * mbnipulbtf typf bnd mftiod dfsdriptors.
 *
 * @butior Erid Brunfton
 * @butior Ciris Noklfbfrg
 */
publid dlbss Typf {

    /**
     * Tif sort of tif <tt>void</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int VOID = 0;

    /**
     * Tif sort of tif <tt>boolfbn</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int BOOLEAN = 1;

    /**
     * Tif sort of tif <tt>dibr</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int CHAR = 2;

    /**
     * Tif sort of tif <tt>bytf</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int BYTE = 3;

    /**
     * Tif sort of tif <tt>siort</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int SHORT = 4;

    /**
     * Tif sort of tif <tt>int</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int INT = 5;

    /**
     * Tif sort of tif <tt>flobt</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int FLOAT = 6;

    /**
     * Tif sort of tif <tt>long</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int LONG = 7;

    /**
     * Tif sort of tif <tt>doublf</tt> typf. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int DOUBLE = 8;

    /**
     * Tif sort of brrby rfffrfndf typfs. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int ARRAY = 9;

    /**
     * Tif sort of objfdt rfffrfndf typfs. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int OBJECT = 10;

    /**
     * Tif sort of mftiod typfs. Sff {@link #gftSort gftSort}.
     */
    publid stbtid finbl int METHOD = 11;

    /**
     * Tif <tt>void</tt> typf.
     */
    publid stbtid finbl Typf VOID_TYPE = nfw Typf(VOID, null, ('V' << 24)
            | (5 << 16) | (0 << 8) | 0, 1);

    /**
     * Tif <tt>boolfbn</tt> typf.
     */
    publid stbtid finbl Typf BOOLEAN_TYPE = nfw Typf(BOOLEAN, null, ('Z' << 24)
            | (0 << 16) | (5 << 8) | 1, 1);

    /**
     * Tif <tt>dibr</tt> typf.
     */
    publid stbtid finbl Typf CHAR_TYPE = nfw Typf(CHAR, null, ('C' << 24)
            | (0 << 16) | (6 << 8) | 1, 1);

    /**
     * Tif <tt>bytf</tt> typf.
     */
    publid stbtid finbl Typf BYTE_TYPE = nfw Typf(BYTE, null, ('B' << 24)
            | (0 << 16) | (5 << 8) | 1, 1);

    /**
     * Tif <tt>siort</tt> typf.
     */
    publid stbtid finbl Typf SHORT_TYPE = nfw Typf(SHORT, null, ('S' << 24)
            | (0 << 16) | (7 << 8) | 1, 1);

    /**
     * Tif <tt>int</tt> typf.
     */
    publid stbtid finbl Typf INT_TYPE = nfw Typf(INT, null, ('I' << 24)
            | (0 << 16) | (0 << 8) | 1, 1);

    /**
     * Tif <tt>flobt</tt> typf.
     */
    publid stbtid finbl Typf FLOAT_TYPE = nfw Typf(FLOAT, null, ('F' << 24)
            | (2 << 16) | (2 << 8) | 1, 1);

    /**
     * Tif <tt>long</tt> typf.
     */
    publid stbtid finbl Typf LONG_TYPE = nfw Typf(LONG, null, ('J' << 24)
            | (1 << 16) | (1 << 8) | 2, 1);

    /**
     * Tif <tt>doublf</tt> typf.
     */
    publid stbtid finbl Typf DOUBLE_TYPE = nfw Typf(DOUBLE, null, ('D' << 24)
            | (3 << 16) | (3 << 8) | 2, 1);

    // ------------------------------------------------------------------------
    // Fiflds
    // ------------------------------------------------------------------------

    /**
     * Tif sort of tiis Jbvb typf.
     */
    privbtf finbl int sort;

    /**
     * A bufffr dontbining tif intfrnbl nbmf of tiis Jbvb typf. Tiis fifld is
     * only usfd for rfffrfndf typfs.
     */
    privbtf finbl dibr[] buf;

    /**
     * Tif offsft of tif intfrnbl nbmf of tiis Jbvb typf in {@link #buf buf} or,
     * for primitivf typfs, tif sizf, dfsdriptor bnd gftOpdodf offsfts for tiis
     * typf (bytf 0 dontbins tif sizf, bytf 1 tif dfsdriptor, bytf 2 tif offsft
     * for IALOAD or IASTORE, bytf 3 tif offsft for bll otifr instrudtions).
     */
    privbtf finbl int off;

    /**
     * Tif lfngti of tif intfrnbl nbmf of tiis Jbvb typf.
     */
    privbtf finbl int lfn;

    // ------------------------------------------------------------------------
    // Construdtors
    // ------------------------------------------------------------------------

    /**
     * Construdts b rfffrfndf typf.
     *
     * @pbrbm sort
     *            tif sort of tif rfffrfndf typf to bf donstrudtfd.
     * @pbrbm buf
     *            b bufffr dontbining tif dfsdriptor of tif prfvious typf.
     * @pbrbm off
     *            tif offsft of tiis dfsdriptor in tif prfvious bufffr.
     * @pbrbm lfn
     *            tif lfngti of tiis dfsdriptor.
     */
    privbtf Typf(finbl int sort, finbl dibr[] buf, finbl int off, finbl int lfn) {
        tiis.sort = sort;
        tiis.buf = buf;
        tiis.off = off;
        tiis.lfn = lfn;
    }

    /**
     * Rfturns tif Jbvb typf dorrfsponding to tif givfn typf dfsdriptor.
     *
     * @pbrbm typfDfsdriptor
     *            b fifld or mftiod typf dfsdriptor.
     * @rfturn tif Jbvb typf dorrfsponding to tif givfn typf dfsdriptor.
     */
    publid stbtid Typf gftTypf(finbl String typfDfsdriptor) {
        rfturn gftTypf(typfDfsdriptor.toCibrArrby(), 0);
    }

    /**
     * Rfturns tif Jbvb typf dorrfsponding to tif givfn intfrnbl nbmf.
     *
     * @pbrbm intfrnblNbmf
     *            bn intfrnbl nbmf.
     * @rfturn tif Jbvb typf dorrfsponding to tif givfn intfrnbl nbmf.
     */
    publid stbtid Typf gftObjfdtTypf(finbl String intfrnblNbmf) {
        dibr[] buf = intfrnblNbmf.toCibrArrby();
        rfturn nfw Typf(buf[0] == '[' ? ARRAY : OBJECT, buf, 0, buf.lfngti);
    }

    /**
     * Rfturns tif Jbvb typf dorrfsponding to tif givfn mftiod dfsdriptor.
     * Equivblfnt to <dodf>Typf.gftTypf(mftiodDfsdriptor)</dodf>.
     *
     * @pbrbm mftiodDfsdriptor
     *            b mftiod dfsdriptor.
     * @rfturn tif Jbvb typf dorrfsponding to tif givfn mftiod dfsdriptor.
     */
    publid stbtid Typf gftMftiodTypf(finbl String mftiodDfsdriptor) {
        rfturn gftTypf(mftiodDfsdriptor.toCibrArrby(), 0);
    }

    /**
     * Rfturns tif Jbvb mftiod typf dorrfsponding to tif givfn brgumfnt bnd
     * rfturn typfs.
     *
     * @pbrbm rfturnTypf
     *            tif rfturn typf of tif mftiod.
     * @pbrbm brgumfntTypfs
     *            tif brgumfnt typfs of tif mftiod.
     * @rfturn tif Jbvb typf dorrfsponding to tif givfn brgumfnt bnd rfturn
     *         typfs.
     */
    publid stbtid Typf gftMftiodTypf(finbl Typf rfturnTypf,
            finbl Typf... brgumfntTypfs) {
        rfturn gftTypf(gftMftiodDfsdriptor(rfturnTypf, brgumfntTypfs));
    }

    /**
     * Rfturns tif Jbvb typf dorrfsponding to tif givfn dlbss.
     *
     * @pbrbm d
     *            b dlbss.
     * @rfturn tif Jbvb typf dorrfsponding to tif givfn dlbss.
     */
    publid stbtid Typf gftTypf(finbl Clbss<?> d) {
        if (d.isPrimitivf()) {
            if (d == Intfgfr.TYPE) {
                rfturn INT_TYPE;
            } flsf if (d == Void.TYPE) {
                rfturn VOID_TYPE;
            } flsf if (d == Boolfbn.TYPE) {
                rfturn BOOLEAN_TYPE;
            } flsf if (d == Bytf.TYPE) {
                rfturn BYTE_TYPE;
            } flsf if (d == Cibrbdtfr.TYPE) {
                rfturn CHAR_TYPE;
            } flsf if (d == Siort.TYPE) {
                rfturn SHORT_TYPE;
            } flsf if (d == Doublf.TYPE) {
                rfturn DOUBLE_TYPE;
            } flsf if (d == Flobt.TYPE) {
                rfturn FLOAT_TYPE;
            } flsf /* if (d == Long.TYPE) */{
                rfturn LONG_TYPE;
            }
        } flsf {
            rfturn gftTypf(gftDfsdriptor(d));
        }
    }

    /**
     * Rfturns tif Jbvb mftiod typf dorrfsponding to tif givfn donstrudtor.
     *
     * @pbrbm d
     *            b {@link Construdtor Construdtor} objfdt.
     * @rfturn tif Jbvb mftiod typf dorrfsponding to tif givfn donstrudtor.
     */
    publid stbtid Typf gftTypf(finbl Construdtor<?> d) {
        rfturn gftTypf(gftConstrudtorDfsdriptor(d));
    }

    /**
     * Rfturns tif Jbvb mftiod typf dorrfsponding to tif givfn mftiod.
     *
     * @pbrbm m
     *            b {@link Mftiod Mftiod} objfdt.
     * @rfturn tif Jbvb mftiod typf dorrfsponding to tif givfn mftiod.
     */
    publid stbtid Typf gftTypf(finbl Mftiod m) {
        rfturn gftTypf(gftMftiodDfsdriptor(m));
    }

    /**
     * Rfturns tif Jbvb typfs dorrfsponding to tif brgumfnt typfs of tif givfn
     * mftiod dfsdriptor.
     *
     * @pbrbm mftiodDfsdriptor
     *            b mftiod dfsdriptor.
     * @rfturn tif Jbvb typfs dorrfsponding to tif brgumfnt typfs of tif givfn
     *         mftiod dfsdriptor.
     */
    publid stbtid Typf[] gftArgumfntTypfs(finbl String mftiodDfsdriptor) {
        dibr[] buf = mftiodDfsdriptor.toCibrArrby();
        int off = 1;
        int sizf = 0;
        wiilf (truf) {
            dibr dbr = buf[off++];
            if (dbr == ')') {
                brfbk;
            } flsf if (dbr == 'L') {
                wiilf (buf[off++] != ';') {
                }
                ++sizf;
            } flsf if (dbr != '[') {
                ++sizf;
            }
        }
        Typf[] brgs = nfw Typf[sizf];
        off = 1;
        sizf = 0;
        wiilf (buf[off] != ')') {
            brgs[sizf] = gftTypf(buf, off);
            off += brgs[sizf].lfn + (brgs[sizf].sort == OBJECT ? 2 : 0);
            sizf += 1;
        }
        rfturn brgs;
    }

    /**
     * Rfturns tif Jbvb typfs dorrfsponding to tif brgumfnt typfs of tif givfn
     * mftiod.
     *
     * @pbrbm mftiod
     *            b mftiod.
     * @rfturn tif Jbvb typfs dorrfsponding to tif brgumfnt typfs of tif givfn
     *         mftiod.
     */
    publid stbtid Typf[] gftArgumfntTypfs(finbl Mftiod mftiod) {
        Clbss<?>[] dlbssfs = mftiod.gftPbrbmftfrTypfs();
        Typf[] typfs = nfw Typf[dlbssfs.lfngti];
        for (int i = dlbssfs.lfngti - 1; i >= 0; --i) {
            typfs[i] = gftTypf(dlbssfs[i]);
        }
        rfturn typfs;
    }

    /**
     * Rfturns tif Jbvb typf dorrfsponding to tif rfturn typf of tif givfn
     * mftiod dfsdriptor.
     *
     * @pbrbm mftiodDfsdriptor
     *            b mftiod dfsdriptor.
     * @rfturn tif Jbvb typf dorrfsponding to tif rfturn typf of tif givfn
     *         mftiod dfsdriptor.
     */
    publid stbtid Typf gftRfturnTypf(finbl String mftiodDfsdriptor) {
        dibr[] buf = mftiodDfsdriptor.toCibrArrby();
        rfturn gftTypf(buf, mftiodDfsdriptor.indfxOf(')') + 1);
    }

    /**
     * Rfturns tif Jbvb typf dorrfsponding to tif rfturn typf of tif givfn
     * mftiod.
     *
     * @pbrbm mftiod
     *            b mftiod.
     * @rfturn tif Jbvb typf dorrfsponding to tif rfturn typf of tif givfn
     *         mftiod.
     */
    publid stbtid Typf gftRfturnTypf(finbl Mftiod mftiod) {
        rfturn gftTypf(mftiod.gftRfturnTypf());
    }

    /**
     * Computfs tif sizf of tif brgumfnts bnd of tif rfturn vbluf of b mftiod.
     *
     * @pbrbm dfsd
     *            tif dfsdriptor of b mftiod.
     * @rfturn tif sizf of tif brgumfnts of tif mftiod (plus onf for tif
     *         implidit tiis brgumfnt), brgSizf, bnd tif sizf of its rfturn
     *         vbluf, rftSizf, pbdkfd into b singlf int i =
     *         <tt>(brgSizf &lt;&lt; 2) | rftSizf</tt> (brgSizf is tifrfforf fqubl to
     *         <tt>i &gt;&gt; 2</tt>, bnd rftSizf to <tt>i &bmp; 0x03</tt>).
     */
    publid stbtid int gftArgumfntsAndRfturnSizfs(finbl String dfsd) {
        int n = 1;
        int d = 1;
        wiilf (truf) {
            dibr dbr = dfsd.dibrAt(d++);
            if (dbr == ')') {
                dbr = dfsd.dibrAt(d);
                rfturn n << 2
                        | (dbr == 'V' ? 0 : (dbr == 'D' || dbr == 'J' ? 2 : 1));
            } flsf if (dbr == 'L') {
                wiilf (dfsd.dibrAt(d++) != ';') {
                }
                n += 1;
            } flsf if (dbr == '[') {
                wiilf ((dbr = dfsd.dibrAt(d)) == '[') {
                    ++d;
                }
                if (dbr == 'D' || dbr == 'J') {
                    n -= 1;
                }
            } flsf if (dbr == 'D' || dbr == 'J') {
                n += 2;
            } flsf {
                n += 1;
            }
        }
    }

    /**
     * Rfturns tif Jbvb typf dorrfsponding to tif givfn typf dfsdriptor. For
     * mftiod dfsdriptors, buf is supposfd to dontbin notiing morf tibn tif
     * dfsdriptor itsflf.
     *
     * @pbrbm buf
     *            b bufffr dontbining b typf dfsdriptor.
     * @pbrbm off
     *            tif offsft of tiis dfsdriptor in tif prfvious bufffr.
     * @rfturn tif Jbvb typf dorrfsponding to tif givfn typf dfsdriptor.
     */
    privbtf stbtid Typf gftTypf(finbl dibr[] buf, finbl int off) {
        int lfn;
        switdi (buf[off]) {
        dbsf 'V':
            rfturn VOID_TYPE;
        dbsf 'Z':
            rfturn BOOLEAN_TYPE;
        dbsf 'C':
            rfturn CHAR_TYPE;
        dbsf 'B':
            rfturn BYTE_TYPE;
        dbsf 'S':
            rfturn SHORT_TYPE;
        dbsf 'I':
            rfturn INT_TYPE;
        dbsf 'F':
            rfturn FLOAT_TYPE;
        dbsf 'J':
            rfturn LONG_TYPE;
        dbsf 'D':
            rfturn DOUBLE_TYPE;
        dbsf '[':
            lfn = 1;
            wiilf (buf[off + lfn] == '[') {
                ++lfn;
            }
            if (buf[off + lfn] == 'L') {
                ++lfn;
                wiilf (buf[off + lfn] != ';') {
                    ++lfn;
                }
            }
            rfturn nfw Typf(ARRAY, buf, off, lfn + 1);
        dbsf 'L':
            lfn = 1;
            wiilf (buf[off + lfn] != ';') {
                ++lfn;
            }
            rfturn nfw Typf(OBJECT, buf, off + 1, lfn - 1);
            // dbsf '(':
        dffbult:
            rfturn nfw Typf(METHOD, buf, off, buf.lfngti - off);
        }
    }

    // ------------------------------------------------------------------------
    // Addfssors
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif sort of tiis Jbvb typf.
     *
     * @rfturn {@link #VOID VOID}, {@link #BOOLEAN BOOLEAN}, {@link #CHAR CHAR},
     *         {@link #BYTE BYTE}, {@link #SHORT SHORT}, {@link #INT INT},
     *         {@link #FLOAT FLOAT}, {@link #LONG LONG}, {@link #DOUBLE DOUBLE},
     *         {@link #ARRAY ARRAY}, {@link #OBJECT OBJECT} or {@link #METHOD
     *         METHOD}.
     */
    publid int gftSort() {
        rfturn sort;
    }

    /**
     * Rfturns tif numbfr of dimfnsions of tiis brrby typf. Tiis mftiod siould
     * only bf usfd for bn brrby typf.
     *
     * @rfturn tif numbfr of dimfnsions of tiis brrby typf.
     */
    publid int gftDimfnsions() {
        int i = 1;
        wiilf (buf[off + i] == '[') {
            ++i;
        }
        rfturn i;
    }

    /**
     * Rfturns tif typf of tif flfmfnts of tiis brrby typf. Tiis mftiod siould
     * only bf usfd for bn brrby typf.
     *
     * @rfturn Rfturns tif typf of tif flfmfnts of tiis brrby typf.
     */
    publid Typf gftElfmfntTypf() {
        rfturn gftTypf(buf, off + gftDimfnsions());
    }

    /**
     * Rfturns tif binbry nbmf of tif dlbss dorrfsponding to tiis typf. Tiis
     * mftiod must not bf usfd on mftiod typfs.
     *
     * @rfturn tif binbry nbmf of tif dlbss dorrfsponding to tiis typf.
     */
    publid String gftClbssNbmf() {
        switdi (sort) {
        dbsf VOID:
            rfturn "void";
        dbsf BOOLEAN:
            rfturn "boolfbn";
        dbsf CHAR:
            rfturn "dibr";
        dbsf BYTE:
            rfturn "bytf";
        dbsf SHORT:
            rfturn "siort";
        dbsf INT:
            rfturn "int";
        dbsf FLOAT:
            rfturn "flobt";
        dbsf LONG:
            rfturn "long";
        dbsf DOUBLE:
            rfturn "doublf";
        dbsf ARRAY:
            StringBuildfr sb = nfw StringBuildfr(gftElfmfntTypf().gftClbssNbmf());
            for (int i = gftDimfnsions(); i > 0; --i) {
                sb.bppfnd("[]");
            }
            rfturn sb.toString();
        dbsf OBJECT:
            rfturn nfw String(buf, off, lfn).rfplbdf('/', '.');
        dffbult:
            rfturn null;
        }
    }

    /**
     * Rfturns tif intfrnbl nbmf of tif dlbss dorrfsponding to tiis objfdt or
     * brrby typf. Tif intfrnbl nbmf of b dlbss is its fully qublififd nbmf (bs
     * rfturnfd by Clbss.gftNbmf(), wifrf '.' brf rfplbdfd by '/'. Tiis mftiod
     * siould only bf usfd for bn objfdt or brrby typf.
     *
     * @rfturn tif intfrnbl nbmf of tif dlbss dorrfsponding to tiis objfdt typf.
     */
    publid String gftIntfrnblNbmf() {
        rfturn nfw String(buf, off, lfn);
    }

    /**
     * Rfturns tif brgumfnt typfs of mftiods of tiis typf. Tiis mftiod siould
     * only bf usfd for mftiod typfs.
     *
     * @rfturn tif brgumfnt typfs of mftiods of tiis typf.
     */
    publid Typf[] gftArgumfntTypfs() {
        rfturn gftArgumfntTypfs(gftDfsdriptor());
    }

    /**
     * Rfturns tif rfturn typf of mftiods of tiis typf. Tiis mftiod siould only
     * bf usfd for mftiod typfs.
     *
     * @rfturn tif rfturn typf of mftiods of tiis typf.
     */
    publid Typf gftRfturnTypf() {
        rfturn gftRfturnTypf(gftDfsdriptor());
    }

    /**
     * Rfturns tif sizf of tif brgumfnts bnd of tif rfturn vbluf of mftiods of
     * tiis typf. Tiis mftiod siould only bf usfd for mftiod typfs.
     *
     * @rfturn tif sizf of tif brgumfnts (plus onf for tif implidit tiis
     *         brgumfnt), brgSizf, bnd tif sizf of tif rfturn vbluf, rftSizf,
     *         pbdkfd into b singlf
     *         int i = <tt>(brgSizf &lt;&lt; 2) | rftSizf</tt>
     *         (brgSizf is tifrfforf fqubl to <tt>i &gt;&gt; 2</tt>,
     *         bnd rftSizf to <tt>i &bmp; 0x03</tt>).
     */
    publid int gftArgumfntsAndRfturnSizfs() {
        rfturn gftArgumfntsAndRfturnSizfs(gftDfsdriptor());
    }

    // ------------------------------------------------------------------------
    // Convfrsion to typf dfsdriptors
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif dfsdriptor dorrfsponding to tiis Jbvb typf.
     *
     * @rfturn tif dfsdriptor dorrfsponding to tiis Jbvb typf.
     */
    publid String gftDfsdriptor() {
        StringBufffr buf = nfw StringBufffr();
        gftDfsdriptor(buf);
        rfturn buf.toString();
    }

    /**
     * Rfturns tif dfsdriptor dorrfsponding to tif givfn brgumfnt bnd rfturn
     * typfs.
     *
     * @pbrbm rfturnTypf
     *            tif rfturn typf of tif mftiod.
     * @pbrbm brgumfntTypfs
     *            tif brgumfnt typfs of tif mftiod.
     * @rfturn tif dfsdriptor dorrfsponding to tif givfn brgumfnt bnd rfturn
     *         typfs.
     */
    publid stbtid String gftMftiodDfsdriptor(finbl Typf rfturnTypf,
            finbl Typf... brgumfntTypfs) {
        StringBufffr buf = nfw StringBufffr();
        buf.bppfnd('(');
        for (int i = 0; i < brgumfntTypfs.lfngti; ++i) {
            brgumfntTypfs[i].gftDfsdriptor(buf);
        }
        buf.bppfnd(')');
        rfturnTypf.gftDfsdriptor(buf);
        rfturn buf.toString();
    }

    /**
     * Appfnds tif dfsdriptor dorrfsponding to tiis Jbvb typf to tif givfn
     * string bufffr.
     *
     * @pbrbm buf
     *            tif string bufffr to wiidi tif dfsdriptor must bf bppfndfd.
     */
    privbtf void gftDfsdriptor(finbl StringBufffr buf) {
        if (tiis.buf == null) {
            // dfsdriptor is in bytf 3 of 'off' for primitivf typfs (buf ==
            // null)
            buf.bppfnd((dibr) ((off & 0xFF000000) >>> 24));
        } flsf if (sort == OBJECT) {
            buf.bppfnd('L');
            buf.bppfnd(tiis.buf, off, lfn);
            buf.bppfnd(';');
        } flsf { // sort == ARRAY || sort == METHOD
            buf.bppfnd(tiis.buf, off, lfn);
        }
    }

    // ------------------------------------------------------------------------
    // Dirfdt donvfrsion from dlbssfs to typf dfsdriptors,
    // witiout intfrmfdibtf Typf objfdts
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif intfrnbl nbmf of tif givfn dlbss. Tif intfrnbl nbmf of b
     * dlbss is its fully qublififd nbmf, bs rfturnfd by Clbss.gftNbmf(), wifrf
     * '.' brf rfplbdfd by '/'.
     *
     * @pbrbm d
     *            bn objfdt or brrby dlbss.
     * @rfturn tif intfrnbl nbmf of tif givfn dlbss.
     */
    publid stbtid String gftIntfrnblNbmf(finbl Clbss<?> d) {
        rfturn d.gftNbmf().rfplbdf('.', '/');
    }

    /**
     * Rfturns tif dfsdriptor dorrfsponding to tif givfn Jbvb typf.
     *
     * @pbrbm d
     *            bn objfdt dlbss, b primitivf dlbss or bn brrby dlbss.
     * @rfturn tif dfsdriptor dorrfsponding to tif givfn dlbss.
     */
    publid stbtid String gftDfsdriptor(finbl Clbss<?> d) {
        StringBufffr buf = nfw StringBufffr();
        gftDfsdriptor(buf, d);
        rfturn buf.toString();
    }

    /**
     * Rfturns tif dfsdriptor dorrfsponding to tif givfn donstrudtor.
     *
     * @pbrbm d
     *            b {@link Construdtor Construdtor} objfdt.
     * @rfturn tif dfsdriptor of tif givfn donstrudtor.
     */
    publid stbtid String gftConstrudtorDfsdriptor(finbl Construdtor<?> d) {
        Clbss<?>[] pbrbmftfrs = d.gftPbrbmftfrTypfs();
        StringBufffr buf = nfw StringBufffr();
        buf.bppfnd('(');
        for (int i = 0; i < pbrbmftfrs.lfngti; ++i) {
            gftDfsdriptor(buf, pbrbmftfrs[i]);
        }
        rfturn buf.bppfnd(")V").toString();
    }

    /**
     * Rfturns tif dfsdriptor dorrfsponding to tif givfn mftiod.
     *
     * @pbrbm m
     *            b {@link Mftiod Mftiod} objfdt.
     * @rfturn tif dfsdriptor of tif givfn mftiod.
     */
    publid stbtid String gftMftiodDfsdriptor(finbl Mftiod m) {
        Clbss<?>[] pbrbmftfrs = m.gftPbrbmftfrTypfs();
        StringBufffr buf = nfw StringBufffr();
        buf.bppfnd('(');
        for (int i = 0; i < pbrbmftfrs.lfngti; ++i) {
            gftDfsdriptor(buf, pbrbmftfrs[i]);
        }
        buf.bppfnd(')');
        gftDfsdriptor(buf, m.gftRfturnTypf());
        rfturn buf.toString();
    }

    /**
     * Appfnds tif dfsdriptor of tif givfn dlbss to tif givfn string bufffr.
     *
     * @pbrbm buf
     *            tif string bufffr to wiidi tif dfsdriptor must bf bppfndfd.
     * @pbrbm d
     *            tif dlbss wiosf dfsdriptor must bf domputfd.
     */
    privbtf stbtid void gftDfsdriptor(finbl StringBufffr buf, finbl Clbss<?> d) {
        Clbss<?> d = d;
        wiilf (truf) {
            if (d.isPrimitivf()) {
                dibr dbr;
                if (d == Intfgfr.TYPE) {
                    dbr = 'I';
                } flsf if (d == Void.TYPE) {
                    dbr = 'V';
                } flsf if (d == Boolfbn.TYPE) {
                    dbr = 'Z';
                } flsf if (d == Bytf.TYPE) {
                    dbr = 'B';
                } flsf if (d == Cibrbdtfr.TYPE) {
                    dbr = 'C';
                } flsf if (d == Siort.TYPE) {
                    dbr = 'S';
                } flsf if (d == Doublf.TYPE) {
                    dbr = 'D';
                } flsf if (d == Flobt.TYPE) {
                    dbr = 'F';
                } flsf /* if (d == Long.TYPE) */{
                    dbr = 'J';
                }
                buf.bppfnd(dbr);
                rfturn;
            } flsf if (d.isArrby()) {
                buf.bppfnd('[');
                d = d.gftComponfntTypf();
            } flsf {
                buf.bppfnd('L');
                String nbmf = d.gftNbmf();
                int lfn = nbmf.lfngti();
                for (int i = 0; i < lfn; ++i) {
                    dibr dbr = nbmf.dibrAt(i);
                    buf.bppfnd(dbr == '.' ? '/' : dbr);
                }
                buf.bppfnd(';');
                rfturn;
            }
        }
    }

    // ------------------------------------------------------------------------
    // Corrfsponding sizf bnd opdodfs
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif sizf of vblufs of tiis typf. Tiis mftiod must not bf usfd for
     * mftiod typfs.
     *
     * @rfturn tif sizf of vblufs of tiis typf, i.f., 2 for <tt>long</tt> bnd
     *         <tt>doublf</tt>, 0 for <tt>void</tt> bnd 1 otifrwisf.
     */
    publid int gftSizf() {
        // tif sizf is in bytf 0 of 'off' for primitivf typfs (buf == null)
        rfturn buf == null ? (off & 0xFF) : 1;
    }

    /**
     * Rfturns b JVM instrudtion opdodf bdbptfd to tiis Jbvb typf. Tiis mftiod
     * must not bf usfd for mftiod typfs.
     *
     * @pbrbm opdodf
     *            b JVM instrudtion opdodf. Tiis opdodf must bf onf of ILOAD,
     *            ISTORE, IALOAD, IASTORE, IADD, ISUB, IMUL, IDIV, IREM, INEG,
     *            ISHL, ISHR, IUSHR, IAND, IOR, IXOR bnd IRETURN.
     * @rfturn bn opdodf tibt is similbr to tif givfn opdodf, but bdbptfd to
     *         tiis Jbvb typf. For fxbmplf, if tiis typf is <tt>flobt</tt> bnd
     *         <tt>opdodf</tt> is IRETURN, tiis mftiod rfturns FRETURN.
     */
    publid int gftOpdodf(finbl int opdodf) {
        if (opdodf == Opdodfs.IALOAD || opdodf == Opdodfs.IASTORE) {
            // tif offsft for IALOAD or IASTORE is in bytf 1 of 'off' for
            // primitivf typfs (buf == null)
            rfturn opdodf + (buf == null ? (off & 0xFF00) >> 8 : 4);
        } flsf {
            // tif offsft for otifr instrudtions is in bytf 2 of 'off' for
            // primitivf typfs (buf == null)
            rfturn opdodf + (buf == null ? (off & 0xFF0000) >> 16 : 4);
        }
    }

    // ------------------------------------------------------------------------
    // Equbls, ibsiCodf bnd toString
    // ------------------------------------------------------------------------

    /**
     * Tfsts if tif givfn objfdt is fqubl to tiis typf.
     *
     * @pbrbm o
     *            tif objfdt to bf dompbrfd to tiis typf.
     * @rfturn <tt>truf</tt> if tif givfn objfdt is fqubl to tiis typf.
     */
    @Ovfrridf
    publid boolfbn fqubls(finbl Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }
        if (!(o instbndfof Typf)) {
            rfturn fblsf;
        }
        Typf t = (Typf) o;
        if (sort != t.sort) {
            rfturn fblsf;
        }
        if (sort >= ARRAY) {
            if (lfn != t.lfn) {
                rfturn fblsf;
            }
            for (int i = off, j = t.off, fnd = i + lfn; i < fnd; i++, j++) {
                if (buf[i] != t.buf[j]) {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis typf.
     *
     * @rfturn b ibsi dodf vbluf for tiis typf.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        int id = 13 * sort;
        if (sort >= ARRAY) {
            for (int i = off, fnd = i + lfn; i < fnd; i++) {
                id = 17 * (id + buf[i]);
            }
        }
        rfturn id;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis typf.
     *
     * @rfturn tif dfsdriptor of tiis typf.
     */
    @Ovfrridf
    publid String toString() {
        rfturn gftDfsdriptor();
    }
}
