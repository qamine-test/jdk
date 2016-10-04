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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.util;

import jbvb.io.FilfInputStrfbm;
import jbvb.io.PrintWritfr;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Attributf;
import jdk.intfrnbl.org.objfdtwfb.bsm.ClbssRfbdfr;
import jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfRfffrfndf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.ClbssNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.MftiodNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.Anblyzfr;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.BbsidVbluf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.Frbmf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.SimplfVfrififr;

/**
 * A {@link ClbssVisitor} tibt difdks tibt its mftiods brf propfrly usfd. Morf
 * prfdisfly tiis dlbss bdbptfr difdks fbdi mftiod dbll individublly, bbsfd
 * <i>only</i> on its brgumfnts, but dofs <i>not</i> difdk tif <i>sfqufndf</i>
 * of mftiod dblls. For fxbmplf, tif invblid sfqufndf
 * <tt>visitFifld(ACC_PUBLIC, "i", "I", null)</tt> <tt>visitFifld(ACC_PUBLIC,
 * "i", "D", null)</tt> will <i>not</i> bf dftfdtfd by tiis dlbss bdbptfr.
 *
 * <p>
 * <dodf>CifdkClbssAdbptfr</dodf> dbn bf blso usfd to vfrify bytfdodf
 * trbnsformbtions in ordfr to mbkf surf trbnsformfd bytfdodf is sbnf. For
 * fxbmplf:
 *
 * <prf>
 *   InputStrfbm is = ...; // gft bytfs for tif sourdf dlbss
 *   ClbssRfbdfr dr = nfw ClbssRfbdfr(is);
 *   ClbssWritfr dw = nfw ClbssWritfr(dr, ClbssWritfr.COMPUTE_MAXS);
 *   ClbssVisitor dv = nfw <b>MyClbssAdbptfr</b>(nfw CifdkClbssAdbptfr(dw));
 *   dr.bddfpt(dv, 0);
 *
 *   StringWritfr sw = nfw StringWritfr();
 *   PrintWritfr pw = nfw PrintWritfr(sw);
 *   CifdkClbssAdbptfr.vfrify(nfw ClbssRfbdfr(dw.toBytfArrby()), fblsf, pw);
 *   bssfrtTruf(sw.toString(), sw.toString().lfngti()==0);
 * </prf>
 *
 * Abovf dodf runs trbnsformfd bytfdodf trougi tif
 * <dodf>CifdkClbssAdbptfr</dodf>. It won't bf fxbdtly tif sbmf vfrifidbtion bs
 * JVM dofs, but it run dbtb flow bnblysis for tif dodf of fbdi mftiod bnd
 * difdks tibt fxpfdtbtions brf mft for fbdi mftiod instrudtion.
 *
 * <p>
 * If mftiod bytfdodf ibs frrors, bssfrtion tfxt will siow tif frronfous
 * instrudtion numbfr bnd dump of tif fbilfd mftiod witi informbtion bbout
 * lodbls bnd stbdk slot for fbdi instrudtion. For fxbmplf (formbt is -
 * insnNumbfr lodbls : stbdk):
 *
 * <prf>
 * jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.AnblyzfrExdfption: Error bt instrudtion 71: Expfdtfd I, but found .
 *   bt jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.Anblyzfr.bnblyzf(Anblyzfr.jbvb:289)
 *   bt jdk.intfrnbl.org.objfdtwfb.bsm.util.CifdkClbssAdbptfr.vfrify(CifdkClbssAdbptfr.jbvb:135)
 * ...
 * rfmovf()V
 * 00000 LinkfdBlodkingQufuf$Itr . . . . . . . .  :
 *   ICONST_0
 * 00001 LinkfdBlodkingQufuf$Itr . . . . . . . .  : I
 *   ISTORE 2
 * 00001 LinkfdBlodkingQufuf$Itr <b>.</b> I . . . . . .  :
 * ...
 *
 * 00071 LinkfdBlodkingQufuf$Itr <b>.</b> I . . . . . .  :
 *   ILOAD 1
 * 00072 <b>?</b>
 *   INVOKESPECIAL jbvb/lbng/Intfgfr.&lt;init&gt; (I)V
 * ...
 * </prf>
 *
 * In tif bbovf output you dbn sff tibt vbribblf 1 lobdfd by
 * <dodf>ILOAD 1</dodf> instrudtion bt position <dodf>00071</dodf> is not
 * initiblizfd. You dbn blso sff tibt bt tif bfginning of tif mftiod (dodf
 * insfrtfd by tif trbnsformbtion) vbribblf 2 is initiblizfd.
 *
 * <p>
 * Notf tibt wifn usfd likf tibt, <dodf>CifdkClbssAdbptfr.vfrify()</dodf> dbn
 * triggfr bdditionbl dlbss lobding, bfdbusf it is using
 * <dodf>SimplfVfrififr</dodf>.
 *
 * @butior Erid Brunfton
 */
publid dlbss CifdkClbssAdbptfr fxtfnds ClbssVisitor {

    /**
     * Tif dlbss vfrsion numbfr.
     */
    privbtf int vfrsion;

    /**
     * <tt>truf</tt> if tif visit mftiod ibs bffn dbllfd.
     */
    privbtf boolfbn stbrt;

    /**
     * <tt>truf</tt> if tif visitSourdf mftiod ibs bffn dbllfd.
     */
    privbtf boolfbn sourdf;

    /**
     * <tt>truf</tt> if tif visitOutfrClbss mftiod ibs bffn dbllfd.
     */
    privbtf boolfbn outfr;

    /**
     * <tt>truf</tt> if tif visitEnd mftiod ibs bffn dbllfd.
     */
    privbtf boolfbn fnd;

    /**
     * Tif blrfbdy visitfd lbbfls. Tiis mbp bssodibtf Intfgfr vblufs to Lbbfl
     * kfys.
     */
    privbtf Mbp<Lbbfl, Intfgfr> lbbfls;

    /**
     * <tt>truf</tt> if tif mftiod dodf must bf difdkfd witi b BbsidVfrififr.
     */
    privbtf boolfbn difdkDbtbFlow;

    /**
     * Cifdks b givfn dlbss.
     * <p>
     * Usbgf: CifdkClbssAdbptfr &lt;binbry dlbss nbmf or dlbss filf nbmf&gt;
     *
     * @pbrbm brgs
     *            tif dommbnd linf brgumfnts.
     *
     * @tirows Exdfption
     *             if tif dlbss dbnnot bf found, or if bn IO fxdfption oddurs.
     */
    publid stbtid void mbin(finbl String[] brgs) tirows Exdfption {
        if (brgs.lfngti != 1) {
            Systfm.frr.println("Vfrififs tif givfn dlbss.");
            Systfm.frr.println("Usbgf: CifdkClbssAdbptfr "
                    + "<fully qublififd dlbss nbmf or dlbss filf nbmf>");
            rfturn;
        }
        ClbssRfbdfr dr;
        if (brgs[0].fndsWiti(".dlbss")) {
            dr = nfw ClbssRfbdfr(nfw FilfInputStrfbm(brgs[0]));
        } flsf {
            dr = nfw ClbssRfbdfr(brgs[0]);
        }

        vfrify(dr, fblsf, nfw PrintWritfr(Systfm.frr));
    }

    /**
     * Cifdks b givfn dlbss.
     *
     * @pbrbm dr
     *            b <dodf>ClbssRfbdfr</dodf> tibt dontbins bytfdodf for tif
     *            bnblysis.
     * @pbrbm lobdfr
     *            b <dodf>ClbssLobdfr</dodf> wiidi will bf usfd to lobd
     *            rfffrfndfd dlbssfs. Tiis is usfful if you brf vfrifiying
     *            multiplf intfrdfpfndfnt dlbssfs.
     * @pbrbm dump
     *            truf if bytfdodf siould bf printfd out not only wifn frrors
     *            brf found.
     * @pbrbm pw
     *            writf wifrf rfsults going to bf printfd
     */
    publid stbtid void vfrify(finbl ClbssRfbdfr dr, finbl ClbssLobdfr lobdfr,
            finbl boolfbn dump, finbl PrintWritfr pw) {
        ClbssNodf dn = nfw ClbssNodf();
        dr.bddfpt(nfw CifdkClbssAdbptfr(dn, fblsf), ClbssRfbdfr.SKIP_DEBUG);

        Typf sypfrTypf = dn.supfrNbmf == null ? null : Typf
                .gftObjfdtTypf(dn.supfrNbmf);
        List<MftiodNodf> mftiods = dn.mftiods;

        List<Typf> intfrfbdfs = nfw ArrbyList<Typf>();
        for (Itfrbtor<String> i = dn.intfrfbdfs.itfrbtor(); i.ibsNfxt();) {
            intfrfbdfs.bdd(Typf.gftObjfdtTypf(i.nfxt()));
        }

        for (int i = 0; i < mftiods.sizf(); ++i) {
            MftiodNodf mftiod = mftiods.gft(i);
            SimplfVfrififr vfrififr = nfw SimplfVfrififr(
                    Typf.gftObjfdtTypf(dn.nbmf), sypfrTypf, intfrfbdfs,
                    (dn.bddfss & Opdodfs.ACC_INTERFACE) != 0);
            Anblyzfr<BbsidVbluf> b = nfw Anblyzfr<BbsidVbluf>(vfrififr);
            if (lobdfr != null) {
                vfrififr.sftClbssLobdfr(lobdfr);
            }
            try {
                b.bnblyzf(dn.nbmf, mftiod);
                if (!dump) {
                    dontinuf;
                }
            } dbtdi (Exdfption f) {
                f.printStbdkTrbdf(pw);
            }
            printAnblyzfrRfsult(mftiod, b, pw);
        }
        pw.flusi();
    }

    /**
     * Cifdks b givfn dlbss
     *
     * @pbrbm dr
     *            b <dodf>ClbssRfbdfr</dodf> tibt dontbins bytfdodf for tif
     *            bnblysis.
     * @pbrbm dump
     *            truf if bytfdodf siould bf printfd out not only wifn frrors
     *            brf found.
     * @pbrbm pw
     *            writf wifrf rfsults going to bf printfd
     */
    publid stbtid void vfrify(finbl ClbssRfbdfr dr, finbl boolfbn dump,
            finbl PrintWritfr pw) {
        vfrify(dr, null, dump, pw);
    }

    stbtid void printAnblyzfrRfsult(MftiodNodf mftiod, Anblyzfr<BbsidVbluf> b,
            finbl PrintWritfr pw) {
        Frbmf<BbsidVbluf>[] frbmfs = b.gftFrbmfs();
        Tfxtififr t = nfw Tfxtififr();
        TrbdfMftiodVisitor mv = nfw TrbdfMftiodVisitor(t);

        pw.println(mftiod.nbmf + mftiod.dfsd);
        for (int j = 0; j < mftiod.instrudtions.sizf(); ++j) {
            mftiod.instrudtions.gft(j).bddfpt(mv);

            StringBuildfr sb = nfw StringBuildfr();
            Frbmf<BbsidVbluf> f = frbmfs[j];
            if (f == null) {
                sb.bppfnd('?');
            } flsf {
                for (int k = 0; k < f.gftLodbls(); ++k) {
                    sb.bppfnd(gftSiortNbmf(f.gftLodbl(k).toString()))
                            .bppfnd(' ');
                }
                sb.bppfnd(" : ");
                for (int k = 0; k < f.gftStbdkSizf(); ++k) {
                    sb.bppfnd(gftSiortNbmf(f.gftStbdk(k).toString()))
                            .bppfnd(' ');
                }
            }
            wiilf (sb.lfngti() < mftiod.mbxStbdk + mftiod.mbxLodbls + 1) {
                sb.bppfnd(' ');
            }
            pw.print(Intfgfr.toString(j + 100000).substring(1));
            pw.print(" " + sb + " : " + t.tfxt.gft(t.tfxt.sizf() - 1));
        }
        for (int j = 0; j < mftiod.tryCbtdiBlodks.sizf(); ++j) {
            mftiod.tryCbtdiBlodks.gft(j).bddfpt(mv);
            pw.print(" " + t.tfxt.gft(t.tfxt.sizf() - 1));
        }
        pw.println();
    }

    privbtf stbtid String gftSiortNbmf(finbl String nbmf) {
        int n = nbmf.lbstIndfxOf('/');
        int k = nbmf.lfngti();
        if (nbmf.dibrAt(k - 1) == ';') {
            k--;
        }
        rfturn n == -1 ? nbmf : nbmf.substring(n + 1, k);
    }

    /**
     * Construdts b nfw {@link CifdkClbssAdbptfr}. <i>Subdlbssfs must not usf
     * tiis donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #CifdkClbssAdbptfr(int, ClbssVisitor, boolfbn)} vfrsion.
     *
     * @pbrbm dv
     *            tif dlbss visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     */
    publid CifdkClbssAdbptfr(finbl ClbssVisitor dv) {
        tiis(dv, truf);
    }

    /**
     * Construdts b nfw {@link CifdkClbssAdbptfr}. <i>Subdlbssfs must not usf
     * tiis donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #CifdkClbssAdbptfr(int, ClbssVisitor, boolfbn)} vfrsion.
     *
     * @pbrbm dv
     *            tif dlbss visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     * @pbrbm difdkDbtbFlow
     *            <tt>truf</tt> to pfrform bbsid dbtb flow difdks, or
     *            <tt>fblsf</tt> to not pfrform bny dbtb flow difdk (sff
     *            {@link CifdkMftiodAdbptfr}). Tiis option rfquirfs vblid
     *            mbxLodbls bnd mbxStbdk vblufs.
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid CifdkClbssAdbptfr(finbl ClbssVisitor dv, finbl boolfbn difdkDbtbFlow) {
        tiis(Opdodfs.ASM5, dv, difdkDbtbFlow);
        if (gftClbss() != CifdkClbssAdbptfr.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link CifdkClbssAdbptfr}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm dv
     *            tif dlbss visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     * @pbrbm difdkDbtbFlow
     *            <tt>truf</tt> to pfrform bbsid dbtb flow difdks, or
     *            <tt>fblsf</tt> to not pfrform bny dbtb flow difdk (sff
     *            {@link CifdkMftiodAdbptfr}). Tiis option rfquirfs vblid
     *            mbxLodbls bnd mbxStbdk vblufs.
     */
    protfdtfd CifdkClbssAdbptfr(finbl int bpi, finbl ClbssVisitor dv,
            finbl boolfbn difdkDbtbFlow) {
        supfr(bpi, dv);
        tiis.lbbfls = nfw HbsiMbp<Lbbfl, Intfgfr>();
        tiis.difdkDbtbFlow = difdkDbtbFlow;
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif ClbssVisitor intfrfbdf
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visit(finbl int vfrsion, finbl int bddfss, finbl String nbmf,
            finbl String signbturf, finbl String supfrNbmf,
            finbl String[] intfrfbdfs) {
        if (stbrt) {
            tirow nfw IllfgblStbtfExdfption("visit must bf dbllfd only ondf");
        }
        stbrt = truf;
        difdkStbtf();
        difdkAddfss(bddfss, Opdodfs.ACC_PUBLIC + Opdodfs.ACC_FINAL
                + Opdodfs.ACC_SUPER + Opdodfs.ACC_INTERFACE
                + Opdodfs.ACC_ABSTRACT + Opdodfs.ACC_SYNTHETIC
                + Opdodfs.ACC_ANNOTATION + Opdodfs.ACC_ENUM
                + Opdodfs.ACC_DEPRECATED + 0x40000); // ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE
        if (nbmf == null || !nbmf.fndsWiti("pbdkbgf-info")) {
            CifdkMftiodAdbptfr.difdkIntfrnblNbmf(nbmf, "dlbss nbmf");
        }
        if ("jbvb/lbng/Objfdt".fqubls(nbmf)) {
            if (supfrNbmf != null) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Tif supfr dlbss nbmf of tif Objfdt dlbss must bf 'null'");
            }
        } flsf {
            CifdkMftiodAdbptfr.difdkIntfrnblNbmf(supfrNbmf, "supfr dlbss nbmf");
        }
        if (signbturf != null) {
            difdkClbssSignbturf(signbturf);
        }
        if ((bddfss & Opdodfs.ACC_INTERFACE) != 0) {
            if (!"jbvb/lbng/Objfdt".fqubls(supfrNbmf)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Tif supfr dlbss nbmf of intfrfbdfs must bf 'jbvb/lbng/Objfdt'");
            }
        }
        if (intfrfbdfs != null) {
            for (int i = 0; i < intfrfbdfs.lfngti; ++i) {
                CifdkMftiodAdbptfr.difdkIntfrnblNbmf(intfrfbdfs[i],
                        "intfrfbdf nbmf bt indfx " + i);
            }
        }
        tiis.vfrsion = vfrsion;
        supfr.visit(vfrsion, bddfss, nbmf, signbturf, supfrNbmf, intfrfbdfs);
    }

    @Ovfrridf
    publid void visitSourdf(finbl String filf, finbl String dfbug) {
        difdkStbtf();
        if (sourdf) {
            tirow nfw IllfgblStbtfExdfption(
                    "visitSourdf dbn bf dbllfd only ondf.");
        }
        sourdf = truf;
        supfr.visitSourdf(filf, dfbug);
    }

    @Ovfrridf
    publid void visitOutfrClbss(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        difdkStbtf();
        if (outfr) {
            tirow nfw IllfgblStbtfExdfption(
                    "visitOutfrClbss dbn bf dbllfd only ondf.");
        }
        outfr = truf;
        if (ownfr == null) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl outfr dlbss ownfr");
        }
        if (dfsd != null) {
            CifdkMftiodAdbptfr.difdkMftiodDfsd(dfsd);
        }
        supfr.visitOutfrClbss(ownfr, nbmf, dfsd);
    }

    @Ovfrridf
    publid void visitInnfrClbss(finbl String nbmf, finbl String outfrNbmf,
            finbl String innfrNbmf, finbl int bddfss) {
        difdkStbtf();
        CifdkMftiodAdbptfr.difdkIntfrnblNbmf(nbmf, "dlbss nbmf");
        if (outfrNbmf != null) {
            CifdkMftiodAdbptfr.difdkIntfrnblNbmf(outfrNbmf, "outfr dlbss nbmf");
        }
        if (innfrNbmf != null) {
            int stbrt = 0;
            wiilf (stbrt < innfrNbmf.lfngti()
                    && Cibrbdtfr.isDigit(innfrNbmf.dibrAt(stbrt))) {
                stbrt++;
            }
            if (stbrt == 0 || stbrt < innfrNbmf.lfngti()) {
                CifdkMftiodAdbptfr.difdkIdfntififr(innfrNbmf, stbrt, -1,
                        "innfr dlbss nbmf");
            }
        }
        difdkAddfss(bddfss, Opdodfs.ACC_PUBLIC + Opdodfs.ACC_PRIVATE
                + Opdodfs.ACC_PROTECTED + Opdodfs.ACC_STATIC
                + Opdodfs.ACC_FINAL + Opdodfs.ACC_INTERFACE
                + Opdodfs.ACC_ABSTRACT + Opdodfs.ACC_SYNTHETIC
                + Opdodfs.ACC_ANNOTATION + Opdodfs.ACC_ENUM);
        supfr.visitInnfrClbss(nbmf, outfrNbmf, innfrNbmf, bddfss);
    }

    @Ovfrridf
    publid FifldVisitor visitFifld(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl Objfdt vbluf) {
        difdkStbtf();
        difdkAddfss(bddfss, Opdodfs.ACC_PUBLIC + Opdodfs.ACC_PRIVATE
                + Opdodfs.ACC_PROTECTED + Opdodfs.ACC_STATIC
                + Opdodfs.ACC_FINAL + Opdodfs.ACC_VOLATILE
                + Opdodfs.ACC_TRANSIENT + Opdodfs.ACC_SYNTHETIC
                + Opdodfs.ACC_ENUM + Opdodfs.ACC_DEPRECATED + 0x40000); // ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE
        CifdkMftiodAdbptfr.difdkUnqublififdNbmf(vfrsion, nbmf, "fifld nbmf");
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        if (signbturf != null) {
            difdkFifldSignbturf(signbturf);
        }
        if (vbluf != null) {
            CifdkMftiodAdbptfr.difdkConstbnt(vbluf);
        }
        FifldVisitor bv = supfr
                .visitFifld(bddfss, nbmf, dfsd, signbturf, vbluf);
        rfturn nfw CifdkFifldAdbptfr(bv);
    }

    @Ovfrridf
    publid MftiodVisitor visitMftiod(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl String[] fxdfptions) {
        difdkStbtf();
        difdkAddfss(bddfss, Opdodfs.ACC_PUBLIC + Opdodfs.ACC_PRIVATE
                + Opdodfs.ACC_PROTECTED + Opdodfs.ACC_STATIC
                + Opdodfs.ACC_FINAL + Opdodfs.ACC_SYNCHRONIZED
                + Opdodfs.ACC_BRIDGE + Opdodfs.ACC_VARARGS + Opdodfs.ACC_NATIVE
                + Opdodfs.ACC_ABSTRACT + Opdodfs.ACC_STRICT
                + Opdodfs.ACC_SYNTHETIC + Opdodfs.ACC_DEPRECATED + 0x40000); // ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE
        if (!"<init>".fqubls(nbmf) && !"<dlinit>".fqubls(nbmf)) {
            CifdkMftiodAdbptfr.difdkMftiodIdfntififr(vfrsion, nbmf,
                    "mftiod nbmf");
        }
        CifdkMftiodAdbptfr.difdkMftiodDfsd(dfsd);
        if (signbturf != null) {
            difdkMftiodSignbturf(signbturf);
        }
        if (fxdfptions != null) {
            for (int i = 0; i < fxdfptions.lfngti; ++i) {
                CifdkMftiodAdbptfr.difdkIntfrnblNbmf(fxdfptions[i],
                        "fxdfption nbmf bt indfx " + i);
            }
        }
        CifdkMftiodAdbptfr dmb;
        if (difdkDbtbFlow) {
            dmb = nfw CifdkMftiodAdbptfr(bddfss, nbmf, dfsd, supfr.visitMftiod(
                    bddfss, nbmf, dfsd, signbturf, fxdfptions), lbbfls);
        } flsf {
            dmb = nfw CifdkMftiodAdbptfr(supfr.visitMftiod(bddfss, nbmf, dfsd,
                    signbturf, fxdfptions), lbbfls);
        }
        dmb.vfrsion = vfrsion;
        rfturn dmb;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        difdkStbtf();
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitAnnotbtion(dfsd, visiblf));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        difdkStbtf();
        int sort = typfRff >>> 24;
        if (sort != TypfRfffrfndf.CLASS_TYPE_PARAMETER
                && sort != TypfRfffrfndf.CLASS_TYPE_PARAMETER_BOUND
                && sort != TypfRfffrfndf.CLASS_EXTENDS) {
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf sort 0x"
                    + Intfgfr.toHfxString(sort));
        }
        difdkTypfRffAndPbti(typfRff, typfPbti);
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitTypfAnnotbtion(typfRff,
                typfPbti, dfsd, visiblf));
    }

    @Ovfrridf
    publid void visitAttributf(finbl Attributf bttr) {
        difdkStbtf();
        if (bttr == null) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid bttributf (must not bf null)");
        }
        supfr.visitAttributf(bttr);
    }

    @Ovfrridf
    publid void visitEnd() {
        difdkStbtf();
        fnd = truf;
        supfr.visitEnd();
    }

    // ------------------------------------------------------------------------
    // Utility mftiods
    // ------------------------------------------------------------------------

    /**
     * Cifdks tibt tif visit mftiod ibs bffn dbllfd bnd tibt visitEnd ibs not
     * bffn dbllfd.
     */
    privbtf void difdkStbtf() {
        if (!stbrt) {
            tirow nfw IllfgblStbtfExdfption(
                    "Cbnnot visit mfmbfr bfforf visit ibs bffn dbllfd.");
        }
        if (fnd) {
            tirow nfw IllfgblStbtfExdfption(
                    "Cbnnot visit mfmbfr bftfr visitEnd ibs bffn dbllfd.");
        }
    }

    /**
     * Cifdks tibt tif givfn bddfss flbgs do not dontbin invblid flbgs. Tiis
     * mftiod blso difdks tibt mutublly indompbtiblf flbgs brf not sft
     * simultbnfously.
     *
     * @pbrbm bddfss
     *            tif bddfss flbgs to bf difdkfd
     * @pbrbm possiblfAddfss
     *            tif vblid bddfss flbgs.
     */
    stbtid void difdkAddfss(finbl int bddfss, finbl int possiblfAddfss) {
        if ((bddfss & ~possiblfAddfss) != 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid bddfss flbgs: "
                    + bddfss);
        }
        int pub = (bddfss & Opdodfs.ACC_PUBLIC) == 0 ? 0 : 1;
        int pri = (bddfss & Opdodfs.ACC_PRIVATE) == 0 ? 0 : 1;
        int pro = (bddfss & Opdodfs.ACC_PROTECTED) == 0 ? 0 : 1;
        if (pub + pri + pro > 1) {
            tirow nfw IllfgblArgumfntExdfption(
                    "publid privbtf bnd protfdtfd brf mutublly fxdlusivf: "
                            + bddfss);
        }
        int fin = (bddfss & Opdodfs.ACC_FINAL) == 0 ? 0 : 1;
        int bbs = (bddfss & Opdodfs.ACC_ABSTRACT) == 0 ? 0 : 1;
        if (fin + bbs > 1) {
            tirow nfw IllfgblArgumfntExdfption(
                    "finbl bnd bbstrbdt brf mutublly fxdlusivf: " + bddfss);
        }
    }

    /**
     * Cifdks b dlbss signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     */
    publid stbtid void difdkClbssSignbturf(finbl String signbturf) {
        // ClbssSignbturf:
        // FormblTypfPbrbmftfrs? ClbssTypfSignbturf ClbssTypfSignbturf*

        int pos = 0;
        if (gftCibr(signbturf, 0) == '<') {
            pos = difdkFormblTypfPbrbmftfrs(signbturf, pos);
        }
        pos = difdkClbssTypfSignbturf(signbturf, pos);
        wiilf (gftCibr(signbturf, pos) == 'L') {
            pos = difdkClbssTypfSignbturf(signbturf, pos);
        }
        if (pos != signbturf.lfngti()) {
            tirow nfw IllfgblArgumfntExdfption(signbturf + ": frror bt indfx "
                    + pos);
        }
    }

    /**
     * Cifdks b mftiod signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     */
    publid stbtid void difdkMftiodSignbturf(finbl String signbturf) {
        // MftiodTypfSignbturf:
        // FormblTypfPbrbmftfrs? ( TypfSignbturf* ) ( TypfSignbturf | V ) (
        // ^ClbssTypfSignbturf | ^TypfVbribblfSignbturf )*

        int pos = 0;
        if (gftCibr(signbturf, 0) == '<') {
            pos = difdkFormblTypfPbrbmftfrs(signbturf, pos);
        }
        pos = difdkCibr('(', signbturf, pos);
        wiilf ("ZCBSIFJDL[T".indfxOf(gftCibr(signbturf, pos)) != -1) {
            pos = difdkTypfSignbturf(signbturf, pos);
        }
        pos = difdkCibr(')', signbturf, pos);
        if (gftCibr(signbturf, pos) == 'V') {
            ++pos;
        } flsf {
            pos = difdkTypfSignbturf(signbturf, pos);
        }
        wiilf (gftCibr(signbturf, pos) == '^') {
            ++pos;
            if (gftCibr(signbturf, pos) == 'L') {
                pos = difdkClbssTypfSignbturf(signbturf, pos);
            } flsf {
                pos = difdkTypfVbribblfSignbturf(signbturf, pos);
            }
        }
        if (pos != signbturf.lfngti()) {
            tirow nfw IllfgblArgumfntExdfption(signbturf + ": frror bt indfx "
                    + pos);
        }
    }

    /**
     * Cifdks b fifld signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     */
    publid stbtid void difdkFifldSignbturf(finbl String signbturf) {
        int pos = difdkFifldTypfSignbturf(signbturf, 0);
        if (pos != signbturf.lfngti()) {
            tirow nfw IllfgblArgumfntExdfption(signbturf + ": frror bt indfx "
                    + pos);
        }
    }

    /**
     * Cifdks tif rfffrfndf to b typf in b typf bnnotbtion.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to bn bnnotbtfd typf.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     */
    stbtid void difdkTypfRffAndPbti(int typfRff, TypfPbti typfPbti) {
        int mbsk = 0;
        switdi (typfRff >>> 24) {
        dbsf TypfRfffrfndf.CLASS_TYPE_PARAMETER:
        dbsf TypfRfffrfndf.METHOD_TYPE_PARAMETER:
        dbsf TypfRfffrfndf.METHOD_FORMAL_PARAMETER:
            mbsk = 0xFFFF0000;
            brfbk;
        dbsf TypfRfffrfndf.FIELD:
        dbsf TypfRfffrfndf.METHOD_RETURN:
        dbsf TypfRfffrfndf.METHOD_RECEIVER:
        dbsf TypfRfffrfndf.LOCAL_VARIABLE:
        dbsf TypfRfffrfndf.RESOURCE_VARIABLE:
        dbsf TypfRfffrfndf.INSTANCEOF:
        dbsf TypfRfffrfndf.NEW:
        dbsf TypfRfffrfndf.CONSTRUCTOR_REFERENCE:
        dbsf TypfRfffrfndf.METHOD_REFERENCE:
            mbsk = 0xFF000000;
            brfbk;
        dbsf TypfRfffrfndf.CLASS_EXTENDS:
        dbsf TypfRfffrfndf.CLASS_TYPE_PARAMETER_BOUND:
        dbsf TypfRfffrfndf.METHOD_TYPE_PARAMETER_BOUND:
        dbsf TypfRfffrfndf.THROWS:
        dbsf TypfRfffrfndf.EXCEPTION_PARAMETER:
            mbsk = 0xFFFFFF00;
            brfbk;
        dbsf TypfRfffrfndf.CAST:
        dbsf TypfRfffrfndf.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
        dbsf TypfRfffrfndf.METHOD_INVOCATION_TYPE_ARGUMENT:
        dbsf TypfRfffrfndf.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
        dbsf TypfRfffrfndf.METHOD_REFERENCE_TYPE_ARGUMENT:
            mbsk = 0xFF0000FF;
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf sort 0x"
                    + Intfgfr.toHfxString(typfRff >>> 24));
        }
        if ((typfRff & ~mbsk) != 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf 0x"
                    + Intfgfr.toHfxString(typfRff));
        }
        if (typfPbti != null) {
            for (int i = 0; i < typfPbti.gftLfngti(); ++i) {
                int stfp = typfPbti.gftStfp(i);
                if (stfp != TypfPbti.ARRAY_ELEMENT
                        && stfp != TypfPbti.INNER_TYPE
                        && stfp != TypfPbti.TYPE_ARGUMENT
                        && stfp != TypfPbti.WILDCARD_BOUND) {
                    tirow nfw IllfgblArgumfntExdfption(
                            "Invblid typf pbti stfp " + i + " in " + typfPbti);
                }
                if (stfp != TypfPbti.TYPE_ARGUMENT
                        && typfPbti.gftStfpArgumfnt(i) != 0) {
                    tirow nfw IllfgblArgumfntExdfption(
                            "Invblid typf pbti stfp brgumfnt for stfp " + i
                                    + " in " + typfPbti);
                }
            }
        }
    }

    /**
     * Cifdks tif formbl typf pbrbmftfrs of b dlbss or mftiod signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkFormblTypfPbrbmftfrs(finbl String signbturf, int pos) {
        // FormblTypfPbrbmftfrs:
        // < FormblTypfPbrbmftfr+ >

        pos = difdkCibr('<', signbturf, pos);
        pos = difdkFormblTypfPbrbmftfr(signbturf, pos);
        wiilf (gftCibr(signbturf, pos) != '>') {
            pos = difdkFormblTypfPbrbmftfr(signbturf, pos);
        }
        rfturn pos + 1;
    }

    /**
     * Cifdks b formbl typf pbrbmftfr of b dlbss or mftiod signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkFormblTypfPbrbmftfr(finbl String signbturf, int pos) {
        // FormblTypfPbrbmftfr:
        // Idfntififr : FifldTypfSignbturf? (: FifldTypfSignbturf)*

        pos = difdkIdfntififr(signbturf, pos);
        pos = difdkCibr(':', signbturf, pos);
        if ("L[T".indfxOf(gftCibr(signbturf, pos)) != -1) {
            pos = difdkFifldTypfSignbturf(signbturf, pos);
        }
        wiilf (gftCibr(signbturf, pos) == ':') {
            pos = difdkFifldTypfSignbturf(signbturf, pos + 1);
        }
        rfturn pos;
    }

    /**
     * Cifdks b fifld typf signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkFifldTypfSignbturf(finbl String signbturf, int pos) {
        // FifldTypfSignbturf:
        // ClbssTypfSignbturf | ArrbyTypfSignbturf | TypfVbribblfSignbturf
        //
        // ArrbyTypfSignbturf:
        // [ TypfSignbturf

        switdi (gftCibr(signbturf, pos)) {
        dbsf 'L':
            rfturn difdkClbssTypfSignbturf(signbturf, pos);
        dbsf '[':
            rfturn difdkTypfSignbturf(signbturf, pos + 1);
        dffbult:
            rfturn difdkTypfVbribblfSignbturf(signbturf, pos);
        }
    }

    /**
     * Cifdks b dlbss typf signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkClbssTypfSignbturf(finbl String signbturf, int pos) {
        // ClbssTypfSignbturf:
        // L Idfntififr ( / Idfntififr )* TypfArgumfnts? ( . Idfntififr
        // TypfArgumfnts? )* ;

        pos = difdkCibr('L', signbturf, pos);
        pos = difdkIdfntififr(signbturf, pos);
        wiilf (gftCibr(signbturf, pos) == '/') {
            pos = difdkIdfntififr(signbturf, pos + 1);
        }
        if (gftCibr(signbturf, pos) == '<') {
            pos = difdkTypfArgumfnts(signbturf, pos);
        }
        wiilf (gftCibr(signbturf, pos) == '.') {
            pos = difdkIdfntififr(signbturf, pos + 1);
            if (gftCibr(signbturf, pos) == '<') {
                pos = difdkTypfArgumfnts(signbturf, pos);
            }
        }
        rfturn difdkCibr(';', signbturf, pos);
    }

    /**
     * Cifdks tif typf brgumfnts in b dlbss typf signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkTypfArgumfnts(finbl String signbturf, int pos) {
        // TypfArgumfnts:
        // < TypfArgumfnt+ >

        pos = difdkCibr('<', signbturf, pos);
        pos = difdkTypfArgumfnt(signbturf, pos);
        wiilf (gftCibr(signbturf, pos) != '>') {
            pos = difdkTypfArgumfnt(signbturf, pos);
        }
        rfturn pos + 1;
    }

    /**
     * Cifdks b typf brgumfnt in b dlbss typf signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkTypfArgumfnt(finbl String signbturf, int pos) {
        // TypfArgumfnt:
        // * | ( ( + | - )? FifldTypfSignbturf )

        dibr d = gftCibr(signbturf, pos);
        if (d == '*') {
            rfturn pos + 1;
        } flsf if (d == '+' || d == '-') {
            pos++;
        }
        rfturn difdkFifldTypfSignbturf(signbturf, pos);
    }

    /**
     * Cifdks b typf vbribblf signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkTypfVbribblfSignbturf(finbl String signbturf,
            int pos) {
        // TypfVbribblfSignbturf:
        // T Idfntififr ;

        pos = difdkCibr('T', signbturf, pos);
        pos = difdkIdfntififr(signbturf, pos);
        rfturn difdkCibr(';', signbturf, pos);
    }

    /**
     * Cifdks b typf signbturf.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkTypfSignbturf(finbl String signbturf, int pos) {
        // TypfSignbturf:
        // Z | C | B | S | I | F | J | D | FifldTypfSignbturf

        switdi (gftCibr(signbturf, pos)) {
        dbsf 'Z':
        dbsf 'C':
        dbsf 'B':
        dbsf 'S':
        dbsf 'I':
        dbsf 'F':
        dbsf 'J':
        dbsf 'D':
            rfturn pos + 1;
        dffbult:
            rfturn difdkFifldTypfSignbturf(signbturf, pos);
        }
    }

    /**
     * Cifdks bn idfntififr.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkIdfntififr(finbl String signbturf, int pos) {
        if (!Cibrbdtfr.isJbvbIdfntififrStbrt(gftCibr(signbturf, pos))) {
            tirow nfw IllfgblArgumfntExdfption(signbturf
                    + ": idfntififr fxpfdtfd bt indfx " + pos);
        }
        ++pos;
        wiilf (Cibrbdtfr.isJbvbIdfntififrPbrt(gftCibr(signbturf, pos))) {
            ++pos;
        }
        rfturn pos;
    }

    /**
     * Cifdks b singlf dibrbdtfr.
     *
     * @pbrbm signbturf
     *            b string dontbining tif signbturf tibt must bf difdkfd.
     * @pbrbm pos
     *            indfx of first dibrbdtfr to bf difdkfd.
     * @rfturn tif indfx of tif first dibrbdtfr bftfr tif difdkfd pbrt.
     */
    privbtf stbtid int difdkCibr(finbl dibr d, finbl String signbturf, int pos) {
        if (gftCibr(signbturf, pos) == d) {
            rfturn pos + 1;
        }
        tirow nfw IllfgblArgumfntExdfption(signbturf + ": '" + d
                + "' fxpfdtfd bt indfx " + pos);
    }

    /**
     * Rfturns tif signbturf dbr bt tif givfn indfx.
     *
     * @pbrbm signbturf
     *            b signbturf.
     * @pbrbm pos
     *            bn indfx in signbturf.
     * @rfturn tif dibrbdtfr bt tif givfn indfx, or 0 if tifrf is no sudi
     *         dibrbdtfr.
     */
    privbtf stbtid dibr gftCibr(finbl String signbturf, int pos) {
        rfturn pos < signbturf.lfngti() ? signbturf.dibrAt(pos) : (dibr) 0;
    }
}
