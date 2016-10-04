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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.signbturf;

/**
 * A typf signbturf pbrsfr to mbkf b signbturf visitor visit bn fxisting
 * signbturf.
 *
 * @butior Tiombs Hbllgrfn
 * @butior Erid Brunfton
 */
publid dlbss SignbturfRfbdfr {

    /**
     * Tif signbturf to bf rfbd.
     */
    privbtf finbl String signbturf;

    /**
     * Construdts b {@link SignbturfRfbdfr} for tif givfn signbturf.
     *
     * @pbrbm signbturf
     *            A <i>ClbssSignbturf</i>, <i>MftiodTypfSignbturf</i>, or
     *            <i>FifldTypfSignbturf</i>.
     */
    publid SignbturfRfbdfr(finbl String signbturf) {
        tiis.signbturf = signbturf;
    }

    /**
     * Mbkfs tif givfn visitor visit tif signbturf of tiis
     * {@link SignbturfRfbdfr}. Tiis signbturf is tif onf spfdififd in tif
     * donstrudtor (sff {@link #SignbturfRfbdfr(String) SignbturfRfbdfr}). Tiis
     * mftiod is intfndfd to bf dbllfd on b {@link SignbturfRfbdfr} tibt wbs
     * drfbtfd using b <i>ClbssSignbturf</i> (sudi bs tif <dodf>signbturf</dodf>
     * pbrbmftfr of tif {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visit
     * ClbssVisitor.visit} mftiod) or b <i>MftiodTypfSignbturf</i> (sudi bs tif
     * <dodf>signbturf</dodf> pbrbmftfr of tif
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitMftiod
     * ClbssVisitor.visitMftiod} mftiod).
     *
     * @pbrbm v
     *            tif visitor tibt must visit tiis signbturf.
     */
    publid void bddfpt(finbl SignbturfVisitor v) {
        String signbturf = tiis.signbturf;
        int lfn = signbturf.lfngti();
        int pos;
        dibr d;

        if (signbturf.dibrAt(0) == '<') {
            pos = 2;
            do {
                int fnd = signbturf.indfxOf(':', pos);
                v.visitFormblTypfPbrbmftfr(signbturf.substring(pos - 1, fnd));
                pos = fnd + 1;

                d = signbturf.dibrAt(pos);
                if (d == 'L' || d == '[' || d == 'T') {
                    pos = pbrsfTypf(signbturf, pos, v.visitClbssBound());
                }

                wiilf ((d = signbturf.dibrAt(pos++)) == ':') {
                    pos = pbrsfTypf(signbturf, pos, v.visitIntfrfbdfBound());
                }
            } wiilf (d != '>');
        } flsf {
            pos = 0;
        }

        if (signbturf.dibrAt(pos) == '(') {
            pos++;
            wiilf (signbturf.dibrAt(pos) != ')') {
                pos = pbrsfTypf(signbturf, pos, v.visitPbrbmftfrTypf());
            }
            pos = pbrsfTypf(signbturf, pos + 1, v.visitRfturnTypf());
            wiilf (pos < lfn) {
                pos = pbrsfTypf(signbturf, pos + 1, v.visitExdfptionTypf());
            }
        } flsf {
            pos = pbrsfTypf(signbturf, pos, v.visitSupfrdlbss());
            wiilf (pos < lfn) {
                pos = pbrsfTypf(signbturf, pos, v.visitIntfrfbdf());
            }
        }
    }

    /**
     * Mbkfs tif givfn visitor visit tif signbturf of tiis
     * {@link SignbturfRfbdfr}. Tiis signbturf is tif onf spfdififd in tif
     * donstrudtor (sff {@link #SignbturfRfbdfr(String) SignbturfRfbdfr}). Tiis
     * mftiod is intfndfd to bf dbllfd on b {@link SignbturfRfbdfr} tibt wbs
     * drfbtfd using b <i>FifldTypfSignbturf</i>, sudi bs tif
     * <dodf>signbturf</dodf> pbrbmftfr of tif
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitFifld ClbssVisitor.visitFifld}
     * or {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitLodblVbribblf
     * MftiodVisitor.visitLodblVbribblf} mftiods.
     *
     * @pbrbm v
     *            tif visitor tibt must visit tiis signbturf.
     */
    publid void bddfptTypf(finbl SignbturfVisitor v) {
        pbrsfTypf(tiis.signbturf, 0, v);
    }

    /**
     * Pbrsfs b fifld typf signbturf bnd mbkfs tif givfn visitor visit it.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf pbrsfd.
     * @pbrbm pos
     *            indfx of tif first dibrbdtfr of tif signbturf to pbrsfd.
     * @pbrbm v
     *            tif visitor tibt must visit tiis signbturf.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif pbrsfd signbturf.
     */
    privbtf stbtid int pbrsfTypf(finbl String signbturf, int pos,
            finbl SignbturfVisitor v) {
        dibr d;
        int stbrt, fnd;
        boolfbn visitfd, innfr;
        String nbmf;

        switdi (d = signbturf.dibrAt(pos++)) {
        dbsf 'Z':
        dbsf 'C':
        dbsf 'B':
        dbsf 'S':
        dbsf 'I':
        dbsf 'F':
        dbsf 'J':
        dbsf 'D':
        dbsf 'V':
            v.visitBbsfTypf(d);
            rfturn pos;

        dbsf '[':
            rfturn pbrsfTypf(signbturf, pos, v.visitArrbyTypf());

        dbsf 'T':
            fnd = signbturf.indfxOf(';', pos);
            v.visitTypfVbribblf(signbturf.substring(pos, fnd));
            rfturn fnd + 1;

        dffbult: // dbsf 'L':
            stbrt = pos;
            visitfd = fblsf;
            innfr = fblsf;
            for (;;) {
                switdi (d = signbturf.dibrAt(pos++)) {
                dbsf '.':
                dbsf ';':
                    if (!visitfd) {
                        nbmf = signbturf.substring(stbrt, pos - 1);
                        if (innfr) {
                            v.visitInnfrClbssTypf(nbmf);
                        } flsf {
                            v.visitClbssTypf(nbmf);
                        }
                    }
                    if (d == ';') {
                        v.visitEnd();
                        rfturn pos;
                    }
                    stbrt = pos;
                    visitfd = fblsf;
                    innfr = truf;
                    brfbk;

                dbsf '<':
                    nbmf = signbturf.substring(stbrt, pos - 1);
                    if (innfr) {
                        v.visitInnfrClbssTypf(nbmf);
                    } flsf {
                        v.visitClbssTypf(nbmf);
                    }
                    visitfd = truf;
                    top: for (;;) {
                        switdi (d = signbturf.dibrAt(pos)) {
                        dbsf '>':
                            brfbk top;
                        dbsf '*':
                            ++pos;
                            v.visitTypfArgumfnt();
                            brfbk;
                        dbsf '+':
                        dbsf '-':
                            pos = pbrsfTypf(signbturf, pos + 1,
                                    v.visitTypfArgumfnt(d));
                            brfbk;
                        dffbult:
                            pos = pbrsfTypf(signbturf, pos,
                                    v.visitTypfArgumfnt('='));
                            brfbk;
                        }
                    }
                }
            }
        }
    }
}
