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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.trff;

import jbvb.util.ArrbyList;
import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;

/**
 * A nodf tibt rfprfsfnts bn bnnotbtionn.
 *
 * @butior Erid Brunfton
 */
publid dlbss AnnotbtionNodf fxtfnds AnnotbtionVisitor {

    /**
     * Tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     */
    publid String dfsd;

    /**
     * Tif nbmf vbluf pbirs of tiis bnnotbtion. Ebdi nbmf vbluf pbir is storfd
     * bs two donsfdutivf flfmfnts in tif list. Tif nbmf is b {@link String},
     * bnd tif vbluf mby bf b {@link Bytf}, {@link Boolfbn}, {@link Cibrbdtfr},
     * {@link Siort}, {@link Intfgfr}, {@link Long}, {@link Flobt},
     * {@link Doublf}, {@link String} or {@link jdk.intfrnbl.org.objfdtwfb.bsm.Typf}, or bn
     * two flfmfnts String brrby (for fnumfrbtion vblufs), b
     * {@link AnnotbtionNodf}, or b {@link List} of vblufs of onf of tif
     * prfdfding typfs. Tif list mby bf <tt>null</tt> if tifrf is no nbmf vbluf
     * pbir.
     */
    publid List<Objfdt> vblufs;

    /**
     * Construdts b nfw {@link AnnotbtionNodf}. <i>Subdlbssfs must not usf tiis
     * donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #AnnotbtionNodf(int, String)} vfrsion.
     *
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid AnnotbtionNodf(finbl String dfsd) {
        tiis(Opdodfs.ASM5, dfsd);
        if (gftClbss() != AnnotbtionNodf.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link AnnotbtionNodf}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     */
    publid AnnotbtionNodf(finbl int bpi, finbl String dfsd) {
        supfr(bpi);
        tiis.dfsd = dfsd;
    }

    /**
     * Construdts b nfw {@link AnnotbtionNodf} to visit bn brrby vbluf.
     *
     * @pbrbm vblufs
     *            wifrf tif visitfd vblufs must bf storfd.
     */
    AnnotbtionNodf(finbl List<Objfdt> vblufs) {
        supfr(Opdodfs.ASM5);
        tiis.vblufs = vblufs;
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif AnnotbtionVisitor bbstrbdt dlbss
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visit(finbl String nbmf, finbl Objfdt vbluf) {
        if (vblufs == null) {
            vblufs = nfw ArrbyList<Objfdt>(tiis.dfsd != null ? 2 : 1);
        }
        if (tiis.dfsd != null) {
            vblufs.bdd(nbmf);
        }
        vblufs.bdd(vbluf);
    }

    @Ovfrridf
    publid void visitEnum(finbl String nbmf, finbl String dfsd,
            finbl String vbluf) {
        if (vblufs == null) {
            vblufs = nfw ArrbyList<Objfdt>(tiis.dfsd != null ? 2 : 1);
        }
        if (tiis.dfsd != null) {
            vblufs.bdd(nbmf);
        }
        vblufs.bdd(nfw String[] { dfsd, vbluf });
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String nbmf,
            finbl String dfsd) {
        if (vblufs == null) {
            vblufs = nfw ArrbyList<Objfdt>(tiis.dfsd != null ? 2 : 1);
        }
        if (tiis.dfsd != null) {
            vblufs.bdd(nbmf);
        }
        AnnotbtionNodf bnnotbtion = nfw AnnotbtionNodf(dfsd);
        vblufs.bdd(bnnotbtion);
        rfturn bnnotbtion;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitArrby(finbl String nbmf) {
        if (vblufs == null) {
            vblufs = nfw ArrbyList<Objfdt>(tiis.dfsd != null ? 2 : 1);
        }
        if (tiis.dfsd != null) {
            vblufs.bdd(nbmf);
        }
        List<Objfdt> brrby = nfw ArrbyList<Objfdt>();
        vblufs.bdd(brrby);
        rfturn nfw AnnotbtionNodf(brrby);
    }

    @Ovfrridf
    publid void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Addfpt mftiods
    // ------------------------------------------------------------------------

    /**
     * Cifdks tibt tiis bnnotbtion nodf is dompbtiblf witi tif givfn ASM API
     * vfrsion. Tiis mftiods difdks tibt tiis nodf, bnd bll its nodfs
     * rfdursivfly, do not dontbin flfmfnts tibt wfrf introdudfd in morf rfdfnt
     * vfrsions of tif ASM API tibn tif givfn vfrsion.
     *
     * @pbrbm bpi
     *            bn ASM API vfrsion. Must bf onf of {@link Opdodfs#ASM4} or
     *            {@link Opdodfs#ASM5}.
     */
    publid void difdk(finbl int bpi) {
        // notiing to do
    }

    /**
     * Mbkfs tif givfn visitor visit tiis bnnotbtion.
     *
     * @pbrbm bv
     *            bn bnnotbtion visitor. Mbybf <tt>null</tt>.
     */
    publid void bddfpt(finbl AnnotbtionVisitor bv) {
        if (bv != null) {
            if (vblufs != null) {
                for (int i = 0; i < vblufs.sizf(); i += 2) {
                    String nbmf = (String) vblufs.gft(i);
                    Objfdt vbluf = vblufs.gft(i + 1);
                    bddfpt(bv, nbmf, vbluf);
                }
            }
            bv.visitEnd();
        }
    }

    /**
     * Mbkfs tif givfn visitor visit b givfn bnnotbtion vbluf.
     *
     * @pbrbm bv
     *            bn bnnotbtion visitor. Mbybf <tt>null</tt>.
     * @pbrbm nbmf
     *            tif vbluf nbmf.
     * @pbrbm vbluf
     *            tif bdtubl vbluf.
     */
    stbtid void bddfpt(finbl AnnotbtionVisitor bv, finbl String nbmf,
            finbl Objfdt vbluf) {
        if (bv != null) {
            if (vbluf instbndfof String[]) {
                String[] typfdonst = (String[]) vbluf;
                bv.visitEnum(nbmf, typfdonst[0], typfdonst[1]);
            } flsf if (vbluf instbndfof AnnotbtionNodf) {
                AnnotbtionNodf bn = (AnnotbtionNodf) vbluf;
                bn.bddfpt(bv.visitAnnotbtion(nbmf, bn.dfsd));
            } flsf if (vbluf instbndfof List) {
                AnnotbtionVisitor v = bv.visitArrby(nbmf);
                List<?> brrby = (List<?>) vbluf;
                for (int j = 0; j < brrby.sizf(); ++j) {
                    bddfpt(v, null, brrby.gft(j));
                }
                v.visitEnd();
            } flsf {
                bv.visit(nbmf, vbluf);
            }
        }
    }
}
