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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.dommons;

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;

/**
 * A {@link MftiodVisitor} tibt rfnumbfrs lodbl vbribblfs in tifir ordfr of
 * bppfbrbndf. Tiis bdbptfr bllows onf to fbsily bdd nfw lodbl vbribblfs to b
 * mftiod. It mby bf usfd by inifriting from tiis dlbss, but tif prfffrrfd wby
 * of using it is vib dflfgbtion: tif nfxt visitor in tif dibin dbn indffd bdd
 * nfw lodbls wifn nffdfd by dblling {@link #nfwLodbl} on tiis bdbptfr (tiis
 * rfquirfs b rfffrfndf bbdk to tiis {@link LodblVbribblfsSortfr}).
 *
 * @butior Ciris Noklfbfrg
 * @butior Eugfnf Kulfsiov
 * @butior Erid Brunfton
 */
publid dlbss LodblVbribblfsSortfr fxtfnds MftiodVisitor {

    privbtf stbtid finbl Typf OBJECT_TYPE = Typf
            .gftObjfdtTypf("jbvb/lbng/Objfdt");

    /**
     * Mbpping from old to nfw lodbl vbribblf indfxfs. A lodbl vbribblf bt indfx
     * i of sizf 1 is rfmbppfd to 'mbpping[2*i]', wiilf b lodbl vbribblf bt
     * indfx i of sizf 2 is rfmbppfd to 'mbpping[2*i+1]'.
     */
    privbtf int[] mbpping = nfw int[40];

    /**
     * Arrby usfd to storf stbdk mbp lodbl vbribblf typfs bftfr rfmbpping.
     */
    privbtf Objfdt[] nfwLodbls = nfw Objfdt[20];

    /**
     * Indfx of tif first lodbl vbribblf, bftfr formbl pbrbmftfrs.
     */
    protfdtfd finbl int firstLodbl;

    /**
     * Indfx of tif nfxt lodbl vbribblf to bf drfbtfd by {@link #nfwLodbl}.
     */
    protfdtfd int nfxtLodbl;

    /**
     * Indidbtfs if bt lfbst onf lodbl vbribblf ibs movfd duf to rfmbpping.
     */
    privbtf boolfbn dibngfd;

    /**
     * Crfbtfs b nfw {@link LodblVbribblfsSortfr}. <i>Subdlbssfs must not usf
     * tiis donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #LodblVbribblfsSortfr(int, int, String, MftiodVisitor)} vfrsion.
     *
     * @pbrbm bddfss
     *            bddfss flbgs of tif bdbptfd mftiod.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr dflfgbtfs dblls.
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid LodblVbribblfsSortfr(finbl int bddfss, finbl String dfsd,
            finbl MftiodVisitor mv) {
        tiis(Opdodfs.ASM5, bddfss, dfsd, mv);
        if (gftClbss() != LodblVbribblfsSortfr.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Crfbtfs b nfw {@link LodblVbribblfsSortfr}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm bddfss
     *            bddfss flbgs of tif bdbptfd mftiod.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr dflfgbtfs dblls.
     */
    protfdtfd LodblVbribblfsSortfr(finbl int bpi, finbl int bddfss,
            finbl String dfsd, finbl MftiodVisitor mv) {
        supfr(bpi, mv);
        Typf[] brgs = Typf.gftArgumfntTypfs(dfsd);
        nfxtLodbl = (Opdodfs.ACC_STATIC & bddfss) == 0 ? 1 : 0;
        for (int i = 0; i < brgs.lfngti; i++) {
            nfxtLodbl += brgs[i].gftSizf();
        }
        firstLodbl = nfxtLodbl;
    }

    @Ovfrridf
    publid void visitVbrInsn(finbl int opdodf, finbl int vbr) {
        Typf typf;
        switdi (opdodf) {
        dbsf Opdodfs.LLOAD:
        dbsf Opdodfs.LSTORE:
            typf = Typf.LONG_TYPE;
            brfbk;

        dbsf Opdodfs.DLOAD:
        dbsf Opdodfs.DSTORE:
            typf = Typf.DOUBLE_TYPE;
            brfbk;

        dbsf Opdodfs.FLOAD:
        dbsf Opdodfs.FSTORE:
            typf = Typf.FLOAT_TYPE;
            brfbk;

        dbsf Opdodfs.ILOAD:
        dbsf Opdodfs.ISTORE:
            typf = Typf.INT_TYPE;
            brfbk;

        dffbult:
            // dbsf Opdodfs.ALOAD:
            // dbsf Opdodfs.ASTORE:
            // dbsf RET:
            typf = OBJECT_TYPE;
            brfbk;
        }
        mv.visitVbrInsn(opdodf, rfmbp(vbr, typf));
    }

    @Ovfrridf
    publid void visitIindInsn(finbl int vbr, finbl int indrfmfnt) {
        mv.visitIindInsn(rfmbp(vbr, Typf.INT_TYPE), indrfmfnt);
    }

    @Ovfrridf
    publid void visitMbxs(finbl int mbxStbdk, finbl int mbxLodbls) {
        mv.visitMbxs(mbxStbdk, nfxtLodbl);
    }

    @Ovfrridf
    publid void visitLodblVbribblf(finbl String nbmf, finbl String dfsd,
            finbl String signbturf, finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl int indfx) {
        int nfwIndfx = rfmbp(indfx, Typf.gftTypf(dfsd));
        mv.visitLodblVbribblf(nbmf, dfsd, signbturf, stbrt, fnd, nfwIndfx);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitLodblVbribblfAnnotbtion(int typfRff,
            TypfPbti typfPbti, Lbbfl[] stbrt, Lbbfl[] fnd, int[] indfx,
            String dfsd, boolfbn visiblf) {
        Typf t = Typf.gftTypf(dfsd);
        int[] nfwIndfx = nfw int[indfx.lfngti];
        for (int i = 0; i < nfwIndfx.lfngti; ++i) {
            nfwIndfx[i] = rfmbp(indfx[i], t);
        }
        rfturn mv.visitLodblVbribblfAnnotbtion(typfRff, typfPbti, stbrt, fnd,
                nfwIndfx, dfsd, visiblf);
    }

    @Ovfrridf
    publid void visitFrbmf(finbl int typf, finbl int nLodbl,
            finbl Objfdt[] lodbl, finbl int nStbdk, finbl Objfdt[] stbdk) {
        if (typf != Opdodfs.F_NEW) { // undomprfssfd frbmf
            tirow nfw IllfgblStbtfExdfption(
                    "ClbssRfbdfr.bddfpt() siould bf dbllfd witi EXPAND_FRAMES flbg");
        }

        if (!dibngfd) { // optimizbtion for tif dbsf wifrf mbpping = idfntity
            mv.visitFrbmf(typf, nLodbl, lodbl, nStbdk, stbdk);
            rfturn;
        }

        // drfbtfs b dopy of nfwLodbls
        Objfdt[] oldLodbls = nfw Objfdt[nfwLodbls.lfngti];
        Systfm.brrbydopy(nfwLodbls, 0, oldLodbls, 0, oldLodbls.lfngti);

        updbtfNfwLodbls(nfwLodbls);

        // dopifs typfs from 'lodbl' to 'nfwLodbls'
        // 'nfwLodbls' blrfbdy dontbins tif vbribblfs bddfd witi 'nfwLodbl'

        int indfx = 0; // old lodbl vbribblf indfx
        int numbfr = 0; // old lodbl vbribblf numbfr
        for (; numbfr < nLodbl; ++numbfr) {
            Objfdt t = lodbl[numbfr];
            int sizf = t == Opdodfs.LONG || t == Opdodfs.DOUBLE ? 2 : 1;
            if (t != Opdodfs.TOP) {
                Typf typ = OBJECT_TYPE;
                if (t == Opdodfs.INTEGER) {
                    typ = Typf.INT_TYPE;
                } flsf if (t == Opdodfs.FLOAT) {
                    typ = Typf.FLOAT_TYPE;
                } flsf if (t == Opdodfs.LONG) {
                    typ = Typf.LONG_TYPE;
                } flsf if (t == Opdodfs.DOUBLE) {
                    typ = Typf.DOUBLE_TYPE;
                } flsf if (t instbndfof String) {
                    typ = Typf.gftObjfdtTypf((String) t);
                }
                sftFrbmfLodbl(rfmbp(indfx, typ), t);
            }
            indfx += sizf;
        }

        // rfmovfs TOP bftfr long bnd doublf typfs bs wfll bs trbiling TOPs

        indfx = 0;
        numbfr = 0;
        for (int i = 0; indfx < nfwLodbls.lfngti; ++i) {
            Objfdt t = nfwLodbls[indfx++];
            if (t != null && t != Opdodfs.TOP) {
                nfwLodbls[i] = t;
                numbfr = i + 1;
                if (t == Opdodfs.LONG || t == Opdodfs.DOUBLE) {
                    indfx += 1;
                }
            } flsf {
                nfwLodbls[i] = Opdodfs.TOP;
            }
        }

        // visits rfmbppfd frbmf
        mv.visitFrbmf(typf, numbfr, nfwLodbls, nStbdk, stbdk);

        // rfstorfs originbl vbluf of 'nfwLodbls'
        nfwLodbls = oldLodbls;
    }

    // -------------

    /**
     * Crfbtfs b nfw lodbl vbribblf of tif givfn typf.
     *
     * @pbrbm typf
     *            tif typf of tif lodbl vbribblf to bf drfbtfd.
     * @rfturn tif idfntififr of tif nfwly drfbtfd lodbl vbribblf.
     */
    publid int nfwLodbl(finbl Typf typf) {
        Objfdt t;
        switdi (typf.gftSort()) {
        dbsf Typf.BOOLEAN:
        dbsf Typf.CHAR:
        dbsf Typf.BYTE:
        dbsf Typf.SHORT:
        dbsf Typf.INT:
            t = Opdodfs.INTEGER;
            brfbk;
        dbsf Typf.FLOAT:
            t = Opdodfs.FLOAT;
            brfbk;
        dbsf Typf.LONG:
            t = Opdodfs.LONG;
            brfbk;
        dbsf Typf.DOUBLE:
            t = Opdodfs.DOUBLE;
            brfbk;
        dbsf Typf.ARRAY:
            t = typf.gftDfsdriptor();
            brfbk;
        // dbsf Typf.OBJECT:
        dffbult:
            t = typf.gftIntfrnblNbmf();
            brfbk;
        }
        int lodbl = nfwLodblMbpping(typf);
        sftLodblTypf(lodbl, typf);
        sftFrbmfLodbl(lodbl, t);
        dibngfd = truf;
        rfturn lodbl;
    }

    /**
     * Notififs subdlbssfs tibt b nfw stbdk mbp frbmf is bfing visitfd. Tif
     * brrby brgumfnt dontbins tif stbdk mbp frbmf typfs dorrfsponding to tif
     * lodbl vbribblfs bddfd witi {@link #nfwLodbl}. Tiis mftiod dbn updbtf
     * tifsf typfs in plbdf for tif stbdk mbp frbmf bfing visitfd. Tif dffbult
     * implfmfntbtion of tiis mftiod dofs notiing, i.f. b lodbl vbribblf bddfd
     * witi {@link #nfwLodbl} will ibvf tif sbmf typf in bll stbdk mbp frbmfs.
     * But tiis bfibvior is not blwbys tif dfsirfd onf, for instbndf if b lodbl
     * vbribblf is bddfd in tif middlf of b try/dbtdi blodk: tif frbmf for tif
     * fxdfption ibndlfr siould ibvf b TOP typf for tiis nfw lodbl.
     *
     * @pbrbm nfwLodbls
     *            tif stbdk mbp frbmf typfs dorrfsponding to tif lodbl vbribblfs
     *            bddfd witi {@link #nfwLodbl} (bnd null for tif otifrs). Tif
     *            formbt of tiis brrby is tif sbmf bs in
     *            {@link MftiodVisitor#visitFrbmf}, fxdfpt tibt long bnd doublf
     *            typfs usf two slots. Tif typfs for tif durrfnt stbdk mbp frbmf
     *            must bf updbtfd in plbdf in tiis brrby.
     */
    protfdtfd void updbtfNfwLodbls(Objfdt[] nfwLodbls) {
    }

    /**
     * Notififs subdlbssfs tibt b lodbl vbribblf ibs bffn bddfd or rfmbppfd. Tif
     * dffbult implfmfntbtion of tiis mftiod dofs notiing.
     *
     * @pbrbm lodbl
     *            b lodbl vbribblf idfntififr, bs rfturnfd by {@link #nfwLodbl
     *            nfwLodbl()}.
     * @pbrbm typf
     *            tif typf of tif vbluf bfing storfd in tif lodbl vbribblf.
     */
    protfdtfd void sftLodblTypf(finbl int lodbl, finbl Typf typf) {
    }

    privbtf void sftFrbmfLodbl(finbl int lodbl, finbl Objfdt typf) {
        int l = nfwLodbls.lfngti;
        if (lodbl >= l) {
            Objfdt[] b = nfw Objfdt[Mbti.mbx(2 * l, lodbl + 1)];
            Systfm.brrbydopy(nfwLodbls, 0, b, 0, l);
            nfwLodbls = b;
        }
        nfwLodbls[lodbl] = typf;
    }

    privbtf int rfmbp(finbl int vbr, finbl Typf typf) {
        if (vbr + typf.gftSizf() <= firstLodbl) {
            rfturn vbr;
        }
        int kfy = 2 * vbr + typf.gftSizf() - 1;
        int sizf = mbpping.lfngti;
        if (kfy >= sizf) {
            int[] nfwMbpping = nfw int[Mbti.mbx(2 * sizf, kfy + 1)];
            Systfm.brrbydopy(mbpping, 0, nfwMbpping, 0, sizf);
            mbpping = nfwMbpping;
        }
        int vbluf = mbpping[kfy];
        if (vbluf == 0) {
            vbluf = nfwLodblMbpping(typf);
            sftLodblTypf(vbluf, typf);
            mbpping[kfy] = vbluf + 1;
        } flsf {
            vbluf--;
        }
        if (vbluf != vbr) {
            dibngfd = truf;
        }
        rfturn vbluf;
    }

    protfdtfd int nfwLodblMbpping(finbl Typf typf) {
        int lodbl = nfxtLodbl;
        nfxtLodbl += typf.gftSizf();
        rfturn lodbl;
    }
}
