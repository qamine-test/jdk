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
import jbvb.util.Arrbys;
import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Attributf;
import jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;

/**
 * A nodf tibt rfprfsfnts b dlbss.
 *
 * @butior Erid Brunfton
 */
publid dlbss ClbssNodf fxtfnds ClbssVisitor {

    /**
     * Tif dlbss vfrsion.
     */
    publid int vfrsion;

    /**
     * Tif dlbss's bddfss flbgs (sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs}). Tiis
     * fifld blso indidbtfs if tif dlbss is dfprfdbtfd.
     */
    publid int bddfss;

    /**
     * Tif intfrnbl nbmf of tif dlbss (sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     */
    publid String nbmf;

    /**
     * Tif signbturf of tif dlbss. Mby bf <tt>null</tt>.
     */
    publid String signbturf;

    /**
     * Tif intfrnbl of nbmf of tif supfr dlbss (sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). For
     * intfrfbdfs, tif supfr dlbss is {@link Objfdt}. Mby bf <tt>null</tt>, but
     * only for tif {@link Objfdt} dlbss.
     */
    publid String supfrNbmf;

    /**
     * Tif intfrnbl nbmfs of tif dlbss's intfrfbdfs (sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). Tiis
     * list is b list of {@link String} objfdts.
     */
    publid List<String> intfrfbdfs;

    /**
     * Tif nbmf of tif sourdf filf from wiidi tiis dlbss wbs dompilfd. Mby bf
     * <tt>null</tt>.
     */
    publid String sourdfFilf;

    /**
     * Dfbug informbtion to domputf tif dorrfspondfndf bftwffn sourdf bnd
     * dompilfd flfmfnts of tif dlbss. Mby bf <tt>null</tt>.
     */
    publid String sourdfDfbug;

    /**
     * Tif intfrnbl nbmf of tif fndlosing dlbss of tif dlbss. Mby bf
     * <tt>null</tt>.
     */
    publid String outfrClbss;

    /**
     * Tif nbmf of tif mftiod tibt dontbins tif dlbss, or <tt>null</tt> if tif
     * dlbss is not fndlosfd in b mftiod.
     */
    publid String outfrMftiod;

    /**
     * Tif dfsdriptor of tif mftiod tibt dontbins tif dlbss, or <tt>null</tt> if
     * tif dlbss is not fndlosfd in b mftiod.
     */
    publid String outfrMftiodDfsd;

    /**
     * Tif runtimf visiblf bnnotbtions of tiis dlbss. Tiis list is b list of
     * {@link AnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.AnnotbtionNodf
     * @lbbfl visiblf
     */
    publid List<AnnotbtionNodf> visiblfAnnotbtions;

    /**
     * Tif runtimf invisiblf bnnotbtions of tiis dlbss. Tiis list is b list of
     * {@link AnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.AnnotbtionNodf
     * @lbbfl invisiblf
     */
    publid List<AnnotbtionNodf> invisiblfAnnotbtions;

    /**
     * Tif runtimf visiblf typf bnnotbtions of tiis dlbss. Tiis list is b list
     * of {@link TypfAnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl visiblf
     */
    publid List<TypfAnnotbtionNodf> visiblfTypfAnnotbtions;

    /**
     * Tif runtimf invisiblf typf bnnotbtions of tiis dlbss. Tiis list is b list
     * of {@link TypfAnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl invisiblf
     */
    publid List<TypfAnnotbtionNodf> invisiblfTypfAnnotbtions;

    /**
     * Tif non stbndbrd bttributfs of tiis dlbss. Tiis list is b list of
     * {@link Attributf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.Attributf
     */
    publid List<Attributf> bttrs;

    /**
     * Informbtions bbout tif innfr dlbssfs of tiis dlbss. Tiis list is b list
     * of {@link InnfrClbssNodf} objfdts.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.InnfrClbssNodf
     */
    publid List<InnfrClbssNodf> innfrClbssfs;

    /**
     * Tif fiflds of tiis dlbss. Tiis list is b list of {@link FifldNodf}
     * objfdts.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.FifldNodf
     */
    publid List<FifldNodf> fiflds;

    /**
     * Tif mftiods of tiis dlbss. Tiis list is b list of {@link MftiodNodf}
     * objfdts.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.MftiodNodf
     */
    publid List<MftiodNodf> mftiods;

    /**
     * Construdts b nfw {@link ClbssNodf}. <i>Subdlbssfs must not usf tiis
     * donstrudtor</i>. Instfbd, tify must usf tif {@link #ClbssNodf(int)}
     * vfrsion.
     *
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid ClbssNodf() {
        tiis(Opdodfs.ASM5);
        if (gftClbss() != ClbssNodf.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link ClbssNodf}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    publid ClbssNodf(finbl int bpi) {
        supfr(bpi);
        tiis.intfrfbdfs = nfw ArrbyList<String>();
        tiis.innfrClbssfs = nfw ArrbyList<InnfrClbssNodf>();
        tiis.fiflds = nfw ArrbyList<FifldNodf>();
        tiis.mftiods = nfw ArrbyList<MftiodNodf>();
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif ClbssVisitor bbstrbdt dlbss
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visit(finbl int vfrsion, finbl int bddfss, finbl String nbmf,
            finbl String signbturf, finbl String supfrNbmf,
            finbl String[] intfrfbdfs) {
        tiis.vfrsion = vfrsion;
        tiis.bddfss = bddfss;
        tiis.nbmf = nbmf;
        tiis.signbturf = signbturf;
        tiis.supfrNbmf = supfrNbmf;
        if (intfrfbdfs != null) {
            tiis.intfrfbdfs.bddAll(Arrbys.bsList(intfrfbdfs));
        }
    }

    @Ovfrridf
    publid void visitSourdf(finbl String filf, finbl String dfbug) {
        sourdfFilf = filf;
        sourdfDfbug = dfbug;
    }

    @Ovfrridf
    publid void visitOutfrClbss(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        outfrClbss = ownfr;
        outfrMftiod = nbmf;
        outfrMftiodDfsd = dfsd;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        AnnotbtionNodf bn = nfw AnnotbtionNodf(dfsd);
        if (visiblf) {
            if (visiblfAnnotbtions == null) {
                visiblfAnnotbtions = nfw ArrbyList<AnnotbtionNodf>(1);
            }
            visiblfAnnotbtions.bdd(bn);
        } flsf {
            if (invisiblfAnnotbtions == null) {
                invisiblfAnnotbtions = nfw ArrbyList<AnnotbtionNodf>(1);
            }
            invisiblfAnnotbtions.bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        TypfAnnotbtionNodf bn = nfw TypfAnnotbtionNodf(typfRff, typfPbti, dfsd);
        if (visiblf) {
            if (visiblfTypfAnnotbtions == null) {
                visiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(1);
            }
            visiblfTypfAnnotbtions.bdd(bn);
        } flsf {
            if (invisiblfTypfAnnotbtions == null) {
                invisiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(1);
            }
            invisiblfTypfAnnotbtions.bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    publid void visitAttributf(finbl Attributf bttr) {
        if (bttrs == null) {
            bttrs = nfw ArrbyList<Attributf>(1);
        }
        bttrs.bdd(bttr);
    }

    @Ovfrridf
    publid void visitInnfrClbss(finbl String nbmf, finbl String outfrNbmf,
            finbl String innfrNbmf, finbl int bddfss) {
        InnfrClbssNodf idn = nfw InnfrClbssNodf(nbmf, outfrNbmf, innfrNbmf,
                bddfss);
        innfrClbssfs.bdd(idn);
    }

    @Ovfrridf
    publid FifldVisitor visitFifld(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl Objfdt vbluf) {
        FifldNodf fn = nfw FifldNodf(bddfss, nbmf, dfsd, signbturf, vbluf);
        fiflds.bdd(fn);
        rfturn fn;
    }

    @Ovfrridf
    publid MftiodVisitor visitMftiod(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl String[] fxdfptions) {
        MftiodNodf mn = nfw MftiodNodf(bddfss, nbmf, dfsd, signbturf,
                fxdfptions);
        mftiods.bdd(mn);
        rfturn mn;
    }

    @Ovfrridf
    publid void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Addfpt mftiod
    // ------------------------------------------------------------------------

    /**
     * Cifdks tibt tiis dlbss nodf is dompbtiblf witi tif givfn ASM API vfrsion.
     * Tiis mftiods difdks tibt tiis nodf, bnd bll its nodfs rfdursivfly, do not
     * dontbin flfmfnts tibt wfrf introdudfd in morf rfdfnt vfrsions of tif ASM
     * API tibn tif givfn vfrsion.
     *
     * @pbrbm bpi
     *            bn ASM API vfrsion. Must bf onf of {@link Opdodfs#ASM4} or
     *            {@link Opdodfs#ASM5}.
     */
    publid void difdk(finbl int bpi) {
        if (bpi == Opdodfs.ASM4) {
            if (visiblfTypfAnnotbtions != null
                    && visiblfTypfAnnotbtions.sizf() > 0) {
                tirow nfw RuntimfExdfption();
            }
            if (invisiblfTypfAnnotbtions != null
                    && invisiblfTypfAnnotbtions.sizf() > 0) {
                tirow nfw RuntimfExdfption();
            }
            for (FifldNodf f : fiflds) {
                f.difdk(bpi);
            }
            for (MftiodNodf m : mftiods) {
                m.difdk(bpi);
            }
        }
    }

    /**
     * Mbkfs tif givfn dlbss visitor visit tiis dlbss.
     *
     * @pbrbm dv
     *            b dlbss visitor.
     */
    publid void bddfpt(finbl ClbssVisitor dv) {
        // visits ifbdfr
        String[] intfrfbdfs = nfw String[tiis.intfrfbdfs.sizf()];
        tiis.intfrfbdfs.toArrby(intfrfbdfs);
        dv.visit(vfrsion, bddfss, nbmf, signbturf, supfrNbmf, intfrfbdfs);
        // visits sourdf
        if (sourdfFilf != null || sourdfDfbug != null) {
            dv.visitSourdf(sourdfFilf, sourdfDfbug);
        }
        // visits outfr dlbss
        if (outfrClbss != null) {
            dv.visitOutfrClbss(outfrClbss, outfrMftiod, outfrMftiodDfsd);
        }
        // visits bttributfs
        int i, n;
        n = visiblfAnnotbtions == null ? 0 : visiblfAnnotbtions.sizf();
        for (i = 0; i < n; ++i) {
            AnnotbtionNodf bn = visiblfAnnotbtions.gft(i);
            bn.bddfpt(dv.visitAnnotbtion(bn.dfsd, truf));
        }
        n = invisiblfAnnotbtions == null ? 0 : invisiblfAnnotbtions.sizf();
        for (i = 0; i < n; ++i) {
            AnnotbtionNodf bn = invisiblfAnnotbtions.gft(i);
            bn.bddfpt(dv.visitAnnotbtion(bn.dfsd, fblsf));
        }
        n = visiblfTypfAnnotbtions == null ? 0 : visiblfTypfAnnotbtions.sizf();
        for (i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = visiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(dv.visitTypfAnnotbtion(bn.typfRff, bn.typfPbti, bn.dfsd,
                    truf));
        }
        n = invisiblfTypfAnnotbtions == null ? 0 : invisiblfTypfAnnotbtions
                .sizf();
        for (i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = invisiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(dv.visitTypfAnnotbtion(bn.typfRff, bn.typfPbti, bn.dfsd,
                    fblsf));
        }
        n = bttrs == null ? 0 : bttrs.sizf();
        for (i = 0; i < n; ++i) {
            dv.visitAttributf(bttrs.gft(i));
        }
        // visits innfr dlbssfs
        for (i = 0; i < innfrClbssfs.sizf(); ++i) {
            innfrClbssfs.gft(i).bddfpt(dv);
        }
        // visits fiflds
        for (i = 0; i < fiflds.sizf(); ++i) {
            fiflds.gft(i).bddfpt(dv);
        }
        // visits mftiods
        for (i = 0; i < mftiods.sizf(); ++i) {
            mftiods.gft(i).bddfpt(dv);
        }
        // visits fnd
        dv.visitEnd();
    }
}
