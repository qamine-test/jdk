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
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import jdk.intfrnbl.org.objfdtwfb.bsm.Attributf;
import jdk.intfrnbl.org.objfdtwfb.bsm.ClbssRfbdfr;
import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfRfffrfndf;
import jdk.intfrnbl.org.objfdtwfb.bsm.signbturf.SignbturfRfbdfr;

/**
 * A {@link Printfr} tibt prints b disbssfmblfd vifw of tif dlbssfs it visits.
 *
 * @butior Erid Brunfton
 */
publid dlbss Tfxtififr fxtfnds Printfr {

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for intfrnbl
     * typf nbmfs in bytfdodf notbtion.
     */
    publid stbtid finbl int INTERNAL_NAME = 0;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for fifld
     * dfsdriptors, formbttfd in bytfdodf notbtion
     */
    publid stbtid finbl int FIELD_DESCRIPTOR = 1;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for fifld
     * signbturfs, formbttfd in bytfdodf notbtion
     */
    publid stbtid finbl int FIELD_SIGNATURE = 2;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for mftiod
     * dfsdriptors, formbttfd in bytfdodf notbtion
     */
    publid stbtid finbl int METHOD_DESCRIPTOR = 3;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for mftiod
     * signbturfs, formbttfd in bytfdodf notbtion
     */
    publid stbtid finbl int METHOD_SIGNATURE = 4;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for dlbss
     * signbturfs, formbttfd in bytfdodf notbtion
     */
    publid stbtid finbl int CLASS_SIGNATURE = 5;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for fifld or
     * mftiod rfturn vbluf signbturfs, formbttfd in dffbult Jbvb notbtion
     * (non-bytfdodf)
     */
    publid stbtid finbl int TYPE_DECLARATION = 6;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for dlbss
     * signbturfs, formbttfd in dffbult Jbvb notbtion (non-bytfdodf)
     */
    publid stbtid finbl int CLASS_DECLARATION = 7;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for mftiod
     * pbrbmftfr signbturfs, formbttfd in dffbult Jbvb notbtion (non-bytfdodf)
     */
    publid stbtid finbl int PARAMETERS_DECLARATION = 8;

    /**
     * Constbnt usfd in {@link #bppfndDfsdriptor bppfndDfsdriptor} for ibndlf
     * dfsdriptors, formbttfd in bytfdodf notbtion
     */
    publid stbtid finbl int HANDLE_DESCRIPTOR = 9;

    /**
     * Tbb for dlbss mfmbfrs.
     */
    protfdtfd String tbb = "  ";

    /**
     * Tbb for bytfdodf instrudtions.
     */
    protfdtfd String tbb2 = "    ";

    /**
     * Tbb for tbblf bnd lookup switdi instrudtions.
     */
    protfdtfd String tbb3 = "      ";

    /**
     * Tbb for lbbfls.
     */
    protfdtfd String ltbb = "   ";

    /**
     * Tif lbbfl nbmfs. Tiis mbp bssodibtf String vblufs to Lbbfl kfys.
     */
    protfdtfd Mbp<Lbbfl, String> lbbflNbmfs;

    /**
     * Clbss bddfss flbgs
     */
    privbtf int bddfss;

    privbtf int vblufNumbfr = 0;

    /**
     * Construdts b nfw {@link Tfxtififr}. <i>Subdlbssfs must not usf tiis
     * donstrudtor</i>. Instfbd, tify must usf tif {@link #Tfxtififr(int)}
     * vfrsion.
     *
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid Tfxtififr() {
        tiis(Opdodfs.ASM5);
        if (gftClbss() != Tfxtififr.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link Tfxtififr}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    protfdtfd Tfxtififr(finbl int bpi) {
        supfr(bpi);
    }

    /**
     * Prints b disbssfmblfd vifw of tif givfn dlbss to tif stbndbrd output.
     * <p>
     * Usbgf: Tfxtififr [-dfbug] &lt;binbry dlbss nbmf or dlbss filf nbmf &gt;
     *
     * @pbrbm brgs
     *            tif dommbnd linf brgumfnts.
     *
     * @tirows Exdfption
     *             if tif dlbss dbnnot bf found, or if bn IO fxdfption oddurs.
     */
    publid stbtid void mbin(finbl String[] brgs) tirows Exdfption {
        int i = 0;
        int flbgs = ClbssRfbdfr.SKIP_DEBUG;

        boolfbn ok = truf;
        if (brgs.lfngti < 1 || brgs.lfngti > 2) {
            ok = fblsf;
        }
        if (ok && "-dfbug".fqubls(brgs[0])) {
            i = 1;
            flbgs = 0;
            if (brgs.lfngti != 2) {
                ok = fblsf;
            }
        }
        if (!ok) {
            Systfm.frr
                    .println("Prints b disbssfmblfd vifw of tif givfn dlbss.");
            Systfm.frr.println("Usbgf: Tfxtififr [-dfbug] "
                    + "<fully qublififd dlbss nbmf or dlbss filf nbmf>");
            rfturn;
        }
        ClbssRfbdfr dr;
        if (brgs[i].fndsWiti(".dlbss") || brgs[i].indfxOf('\\') > -1
                || brgs[i].indfxOf('/') > -1) {
            dr = nfw ClbssRfbdfr(nfw FilfInputStrfbm(brgs[i]));
        } flsf {
            dr = nfw ClbssRfbdfr(brgs[i]);
        }
        dr.bddfpt(nfw TrbdfClbssVisitor(nfw PrintWritfr(Systfm.out)), flbgs);
    }

    // ------------------------------------------------------------------------
    // Clbssfs
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visit(finbl int vfrsion, finbl int bddfss, finbl String nbmf,
            finbl String signbturf, finbl String supfrNbmf,
            finbl String[] intfrfbdfs) {
        tiis.bddfss = bddfss;
        int mbjor = vfrsion & 0xFFFF;
        int minor = vfrsion >>> 16;
        buf.sftLfngti(0);
        buf.bppfnd("// dlbss vfrsion ").bppfnd(mbjor).bppfnd('.').bppfnd(minor)
                .bppfnd(" (").bppfnd(vfrsion).bppfnd(")\n");
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            buf.bppfnd("// DEPRECATED\n");
        }
        buf.bppfnd("// bddfss flbgs 0x")
                .bppfnd(Intfgfr.toHfxString(bddfss).toUppfrCbsf()).bppfnd('\n');

        bppfndDfsdriptor(CLASS_SIGNATURE, signbturf);
        if (signbturf != null) {
            TrbdfSignbturfVisitor sv = nfw TrbdfSignbturfVisitor(bddfss);
            SignbturfRfbdfr r = nfw SignbturfRfbdfr(signbturf);
            r.bddfpt(sv);
            buf.bppfnd("// dfdlbrbtion: ").bppfnd(nbmf)
                    .bppfnd(sv.gftDfdlbrbtion()).bppfnd('\n');
        }

        bppfndAddfss(bddfss & ~Opdodfs.ACC_SUPER);
        if ((bddfss & Opdodfs.ACC_ANNOTATION) != 0) {
            buf.bppfnd("@intfrfbdf ");
        } flsf if ((bddfss & Opdodfs.ACC_INTERFACE) != 0) {
            buf.bppfnd("intfrfbdf ");
        } flsf if ((bddfss & Opdodfs.ACC_ENUM) == 0) {
            buf.bppfnd("dlbss ");
        }
        bppfndDfsdriptor(INTERNAL_NAME, nbmf);

        if (supfrNbmf != null && !"jbvb/lbng/Objfdt".fqubls(supfrNbmf)) {
            buf.bppfnd(" fxtfnds ");
            bppfndDfsdriptor(INTERNAL_NAME, supfrNbmf);
            buf.bppfnd(' ');
        }
        if (intfrfbdfs != null && intfrfbdfs.lfngti > 0) {
            buf.bppfnd(" implfmfnts ");
            for (int i = 0; i < intfrfbdfs.lfngti; ++i) {
                bppfndDfsdriptor(INTERNAL_NAME, intfrfbdfs[i]);
                buf.bppfnd(' ');
            }
        }
        buf.bppfnd(" {\n\n");

        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitSourdf(finbl String filf, finbl String dfbug) {
        buf.sftLfngti(0);
        if (filf != null) {
            buf.bppfnd(tbb).bppfnd("// dompilfd from: ").bppfnd(filf)
                    .bppfnd('\n');
        }
        if (dfbug != null) {
            buf.bppfnd(tbb).bppfnd("// dfbug info: ").bppfnd(dfbug)
                    .bppfnd('\n');
        }
        if (buf.lfngti() > 0) {
            tfxt.bdd(buf.toString());
        }
    }

    @Ovfrridf
    publid void visitOutfrClbss(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb).bppfnd("OUTERCLASS ");
        bppfndDfsdriptor(INTERNAL_NAME, ownfr);
        buf.bppfnd(' ');
        if (nbmf != null) {
            buf.bppfnd(nbmf).bppfnd(' ');
        }
        bppfndDfsdriptor(METHOD_DESCRIPTOR, dfsd);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid Tfxtififr visitClbssAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        tfxt.bdd("\n");
        rfturn visitAnnotbtion(dfsd, visiblf);
    }

    @Ovfrridf
    publid Printfr visitClbssTypfAnnotbtion(int typfRff, TypfPbti typfPbti,
            String dfsd, boolfbn visiblf) {
        tfxt.bdd("\n");
        rfturn visitTypfAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
    }

    @Ovfrridf
    publid void visitClbssAttributf(finbl Attributf bttr) {
        tfxt.bdd("\n");
        visitAttributf(bttr);
    }

    @Ovfrridf
    publid void visitInnfrClbss(finbl String nbmf, finbl String outfrNbmf,
            finbl String innfrNbmf, finbl int bddfss) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb).bppfnd("// bddfss flbgs 0x");
        buf.bppfnd(
                Intfgfr.toHfxString(bddfss & ~Opdodfs.ACC_SUPER).toUppfrCbsf())
                .bppfnd('\n');
        buf.bppfnd(tbb);
        bppfndAddfss(bddfss);
        buf.bppfnd("INNERCLASS ");
        bppfndDfsdriptor(INTERNAL_NAME, nbmf);
        buf.bppfnd(' ');
        bppfndDfsdriptor(INTERNAL_NAME, outfrNbmf);
        buf.bppfnd(' ');
        bppfndDfsdriptor(INTERNAL_NAME, innfrNbmf);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid Tfxtififr visitFifld(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl Objfdt vbluf) {
        buf.sftLfngti(0);
        buf.bppfnd('\n');
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            buf.bppfnd(tbb).bppfnd("// DEPRECATED\n");
        }
        buf.bppfnd(tbb).bppfnd("// bddfss flbgs 0x")
                .bppfnd(Intfgfr.toHfxString(bddfss).toUppfrCbsf()).bppfnd('\n');
        if (signbturf != null) {
            buf.bppfnd(tbb);
            bppfndDfsdriptor(FIELD_SIGNATURE, signbturf);

            TrbdfSignbturfVisitor sv = nfw TrbdfSignbturfVisitor(0);
            SignbturfRfbdfr r = nfw SignbturfRfbdfr(signbturf);
            r.bddfptTypf(sv);
            buf.bppfnd(tbb).bppfnd("// dfdlbrbtion: ")
                    .bppfnd(sv.gftDfdlbrbtion()).bppfnd('\n');
        }

        buf.bppfnd(tbb);
        bppfndAddfss(bddfss);

        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd(' ').bppfnd(nbmf);
        if (vbluf != null) {
            buf.bppfnd(" = ");
            if (vbluf instbndfof String) {
                buf.bppfnd('\"').bppfnd(vbluf).bppfnd('\"');
            } flsf {
                buf.bppfnd(vbluf);
            }
        }

        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());

        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        rfturn t;
    }

    @Ovfrridf
    publid Tfxtififr visitMftiod(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl String[] fxdfptions) {
        buf.sftLfngti(0);
        buf.bppfnd('\n');
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            buf.bppfnd(tbb).bppfnd("// DEPRECATED\n");
        }
        buf.bppfnd(tbb).bppfnd("// bddfss flbgs 0x")
                .bppfnd(Intfgfr.toHfxString(bddfss).toUppfrCbsf()).bppfnd('\n');

        if (signbturf != null) {
            buf.bppfnd(tbb);
            bppfndDfsdriptor(METHOD_SIGNATURE, signbturf);

            TrbdfSignbturfVisitor v = nfw TrbdfSignbturfVisitor(0);
            SignbturfRfbdfr r = nfw SignbturfRfbdfr(signbturf);
            r.bddfpt(v);
            String gfnfridDfdl = v.gftDfdlbrbtion();
            String gfnfridRfturn = v.gftRfturnTypf();
            String gfnfridExdfptions = v.gftExdfptions();

            buf.bppfnd(tbb).bppfnd("// dfdlbrbtion: ").bppfnd(gfnfridRfturn)
                    .bppfnd(' ').bppfnd(nbmf).bppfnd(gfnfridDfdl);
            if (gfnfridExdfptions != null) {
                buf.bppfnd(" tirows ").bppfnd(gfnfridExdfptions);
            }
            buf.bppfnd('\n');
        }

        buf.bppfnd(tbb);
        bppfndAddfss(bddfss & ~Opdodfs.ACC_VOLATILE);
        if ((bddfss & Opdodfs.ACC_NATIVE) != 0) {
            buf.bppfnd("nbtivf ");
        }
        if ((bddfss & Opdodfs.ACC_VARARGS) != 0) {
            buf.bppfnd("vbrbrgs ");
        }
        if ((bddfss & Opdodfs.ACC_BRIDGE) != 0) {
            buf.bppfnd("bridgf ");
        }
        if ((tiis.bddfss & Opdodfs.ACC_INTERFACE) != 0
                && (bddfss & Opdodfs.ACC_ABSTRACT) == 0
                && (bddfss & Opdodfs.ACC_STATIC) == 0) {
            buf.bppfnd("dffbult ");
        }

        buf.bppfnd(nbmf);
        bppfndDfsdriptor(METHOD_DESCRIPTOR, dfsd);
        if (fxdfptions != null && fxdfptions.lfngti > 0) {
            buf.bppfnd(" tirows ");
            for (int i = 0; i < fxdfptions.lfngti; ++i) {
                bppfndDfsdriptor(INTERNAL_NAME, fxdfptions[i]);
                buf.bppfnd(' ');
            }
        }

        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());

        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        rfturn t;
    }

    @Ovfrridf
    publid void visitClbssEnd() {
        tfxt.bdd("}\n");
    }

    // ------------------------------------------------------------------------
    // Annotbtions
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visit(finbl String nbmf, finbl Objfdt vbluf) {
        buf.sftLfngti(0);
        bppfndComb(vblufNumbfr++);

        if (nbmf != null) {
            buf.bppfnd(nbmf).bppfnd('=');
        }

        if (vbluf instbndfof String) {
            visitString((String) vbluf);
        } flsf if (vbluf instbndfof Typf) {
            visitTypf((Typf) vbluf);
        } flsf if (vbluf instbndfof Bytf) {
            visitBytf(((Bytf) vbluf).bytfVbluf());
        } flsf if (vbluf instbndfof Boolfbn) {
            visitBoolfbn(((Boolfbn) vbluf).boolfbnVbluf());
        } flsf if (vbluf instbndfof Siort) {
            visitSiort(((Siort) vbluf).siortVbluf());
        } flsf if (vbluf instbndfof Cibrbdtfr) {
            visitCibr(((Cibrbdtfr) vbluf).dibrVbluf());
        } flsf if (vbluf instbndfof Intfgfr) {
            visitInt(((Intfgfr) vbluf).intVbluf());
        } flsf if (vbluf instbndfof Flobt) {
            visitFlobt(((Flobt) vbluf).flobtVbluf());
        } flsf if (vbluf instbndfof Long) {
            visitLong(((Long) vbluf).longVbluf());
        } flsf if (vbluf instbndfof Doublf) {
            visitDoublf(((Doublf) vbluf).doublfVbluf());
        } flsf if (vbluf.gftClbss().isArrby()) {
            buf.bppfnd('{');
            if (vbluf instbndfof bytf[]) {
                bytf[] v = (bytf[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitBytf(v[i]);
                }
            } flsf if (vbluf instbndfof boolfbn[]) {
                boolfbn[] v = (boolfbn[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitBoolfbn(v[i]);
                }
            } flsf if (vbluf instbndfof siort[]) {
                siort[] v = (siort[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitSiort(v[i]);
                }
            } flsf if (vbluf instbndfof dibr[]) {
                dibr[] v = (dibr[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitCibr(v[i]);
                }
            } flsf if (vbluf instbndfof int[]) {
                int[] v = (int[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitInt(v[i]);
                }
            } flsf if (vbluf instbndfof long[]) {
                long[] v = (long[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitLong(v[i]);
                }
            } flsf if (vbluf instbndfof flobt[]) {
                flobt[] v = (flobt[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitFlobt(v[i]);
                }
            } flsf if (vbluf instbndfof doublf[]) {
                doublf[] v = (doublf[]) vbluf;
                for (int i = 0; i < v.lfngti; i++) {
                    bppfndComb(i);
                    visitDoublf(v[i]);
                }
            }
            buf.bppfnd('}');
        }

        tfxt.bdd(buf.toString());
    }

    privbtf void visitInt(finbl int vbluf) {
        buf.bppfnd(vbluf);
    }

    privbtf void visitLong(finbl long vbluf) {
        buf.bppfnd(vbluf).bppfnd('L');
    }

    privbtf void visitFlobt(finbl flobt vbluf) {
        buf.bppfnd(vbluf).bppfnd('F');
    }

    privbtf void visitDoublf(finbl doublf vbluf) {
        buf.bppfnd(vbluf).bppfnd('D');
    }

    privbtf void visitCibr(finbl dibr vbluf) {
        buf.bppfnd("(dibr)").bppfnd((int) vbluf);
    }

    privbtf void visitSiort(finbl siort vbluf) {
        buf.bppfnd("(siort)").bppfnd(vbluf);
    }

    privbtf void visitBytf(finbl bytf vbluf) {
        buf.bppfnd("(bytf)").bppfnd(vbluf);
    }

    privbtf void visitBoolfbn(finbl boolfbn vbluf) {
        buf.bppfnd(vbluf);
    }

    privbtf void visitString(finbl String vbluf) {
        bppfndString(buf, vbluf);
    }

    privbtf void visitTypf(finbl Typf vbluf) {
        buf.bppfnd(vbluf.gftClbssNbmf()).bppfnd(".dlbss");
    }

    @Ovfrridf
    publid void visitEnum(finbl String nbmf, finbl String dfsd,
            finbl String vbluf) {
        buf.sftLfngti(0);
        bppfndComb(vblufNumbfr++);
        if (nbmf != null) {
            buf.bppfnd(nbmf).bppfnd('=');
        }
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('.').bppfnd(vbluf);
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid Tfxtififr visitAnnotbtion(finbl String nbmf, finbl String dfsd) {
        buf.sftLfngti(0);
        bppfndComb(vblufNumbfr++);
        if (nbmf != null) {
            buf.bppfnd(nbmf).bppfnd('=');
        }
        buf.bppfnd('@');
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('(');
        tfxt.bdd(buf.toString());
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        tfxt.bdd(")");
        rfturn t;
    }

    @Ovfrridf
    publid Tfxtififr visitArrby(finbl String nbmf) {
        buf.sftLfngti(0);
        bppfndComb(vblufNumbfr++);
        if (nbmf != null) {
            buf.bppfnd(nbmf).bppfnd('=');
        }
        buf.bppfnd('{');
        tfxt.bdd(buf.toString());
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        tfxt.bdd("}");
        rfturn t;
    }

    @Ovfrridf
    publid void visitAnnotbtionEnd() {
    }

    // ------------------------------------------------------------------------
    // Fiflds
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid Tfxtififr visitFifldAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        rfturn visitAnnotbtion(dfsd, visiblf);
    }

    @Ovfrridf
    publid Printfr visitFifldTypfAnnotbtion(int typfRff, TypfPbti typfPbti,
            String dfsd, boolfbn visiblf) {
        rfturn visitTypfAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
    }

    @Ovfrridf
    publid void visitFifldAttributf(finbl Attributf bttr) {
        visitAttributf(bttr);
    }

    @Ovfrridf
    publid void visitFifldEnd() {
    }

    // ------------------------------------------------------------------------
    // Mftiods
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visitPbrbmftfr(finbl String nbmf, finbl int bddfss) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("// pbrbmftfr ");
        bppfndAddfss(bddfss);
        buf.bppfnd(' ').bppfnd((nbmf == null) ? "<no nbmf>" : nbmf)
                .bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid Tfxtififr visitAnnotbtionDffbult() {
        tfxt.bdd(tbb2 + "dffbult=");
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        tfxt.bdd("\n");
        rfturn t;
    }

    @Ovfrridf
    publid Tfxtififr visitMftiodAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        rfturn visitAnnotbtion(dfsd, visiblf);
    }

    @Ovfrridf
    publid Printfr visitMftiodTypfAnnotbtion(int typfRff, TypfPbti typfPbti,
            String dfsd, boolfbn visiblf) {
        rfturn visitTypfAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
    }

    @Ovfrridf
    publid Tfxtififr visitPbrbmftfrAnnotbtion(finbl int pbrbmftfr,
            finbl String dfsd, finbl boolfbn visiblf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd('@');
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('(');
        tfxt.bdd(buf.toString());
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        tfxt.bdd(visiblf ? ") // pbrbmftfr " : ") // invisiblf, pbrbmftfr ");
        tfxt.bdd(nfw Intfgfr(pbrbmftfr));
        tfxt.bdd("\n");
        rfturn t;
    }

    @Ovfrridf
    publid void visitMftiodAttributf(finbl Attributf bttr) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb).bppfnd("ATTRIBUTE ");
        bppfndDfsdriptor(-1, bttr.typf);

        if (bttr instbndfof Tfxtifibblf) {
            ((Tfxtifibblf) bttr).tfxtify(buf, lbbflNbmfs);
        } flsf {
            buf.bppfnd(" : unknown\n");
        }

        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitCodf() {
    }

    @Ovfrridf
    publid void visitFrbmf(finbl int typf, finbl int nLodbl,
            finbl Objfdt[] lodbl, finbl int nStbdk, finbl Objfdt[] stbdk) {
        buf.sftLfngti(0);
        buf.bppfnd(ltbb);
        buf.bppfnd("FRAME ");
        switdi (typf) {
        dbsf Opdodfs.F_NEW:
        dbsf Opdodfs.F_FULL:
            buf.bppfnd("FULL [");
            bppfndFrbmfTypfs(nLodbl, lodbl);
            buf.bppfnd("] [");
            bppfndFrbmfTypfs(nStbdk, stbdk);
            buf.bppfnd(']');
            brfbk;
        dbsf Opdodfs.F_APPEND:
            buf.bppfnd("APPEND [");
            bppfndFrbmfTypfs(nLodbl, lodbl);
            buf.bppfnd(']');
            brfbk;
        dbsf Opdodfs.F_CHOP:
            buf.bppfnd("CHOP ").bppfnd(nLodbl);
            brfbk;
        dbsf Opdodfs.F_SAME:
            buf.bppfnd("SAME");
            brfbk;
        dbsf Opdodfs.F_SAME1:
            buf.bppfnd("SAME1 ");
            bppfndFrbmfTypfs(1, stbdk);
            brfbk;
        }
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitInsn(finbl int opdodf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd(OPCODES[opdodf]).bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitIntInsn(finbl int opdodf, finbl int opfrbnd) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2)
                .bppfnd(OPCODES[opdodf])
                .bppfnd(' ')
                .bppfnd(opdodf == Opdodfs.NEWARRAY ? TYPES[opfrbnd] : Intfgfr
                        .toString(opfrbnd)).bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitVbrInsn(finbl int opdodf, finbl int vbr) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd(OPCODES[opdodf]).bppfnd(' ').bppfnd(vbr)
                .bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitTypfInsn(finbl int opdodf, finbl String typf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd(OPCODES[opdodf]).bppfnd(' ');
        bppfndDfsdriptor(INTERNAL_NAME, typf);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitFifldInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd(OPCODES[opdodf]).bppfnd(' ');
        bppfndDfsdriptor(INTERNAL_NAME, ownfr);
        buf.bppfnd('.').bppfnd(nbmf).bppfnd(" : ");
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Dfprfdbtfd
    @Ovfrridf
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd,
                opdodf == Opdodfs.INVOKEINTERFACE);
    }

    @Ovfrridf
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
    }

    privbtf void doVisitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd(OPCODES[opdodf]).bppfnd(' ');
        bppfndDfsdriptor(INTERNAL_NAME, ownfr);
        buf.bppfnd('.').bppfnd(nbmf).bppfnd(' ');
        bppfndDfsdriptor(METHOD_DESCRIPTOR, dfsd);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitInvokfDynbmidInsn(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt... bsmArgs) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("INVOKEDYNAMIC").bppfnd(' ');
        buf.bppfnd(nbmf);
        bppfndDfsdriptor(METHOD_DESCRIPTOR, dfsd);
        buf.bppfnd(" [");
        buf.bppfnd('\n');
        buf.bppfnd(tbb3);
        bppfndHbndlf(bsm);
        buf.bppfnd('\n');
        buf.bppfnd(tbb3).bppfnd("// brgumfnts:");
        if (bsmArgs.lfngti == 0) {
            buf.bppfnd(" nonf");
        } flsf {
            buf.bppfnd('\n');
            for (int i = 0; i < bsmArgs.lfngti; i++) {
                buf.bppfnd(tbb3);
                Objfdt dst = bsmArgs[i];
                if (dst instbndfof String) {
                    Printfr.bppfndString(buf, (String) dst);
                } flsf if (dst instbndfof Typf) {
                    Typf typf = (Typf) dst;
                    if(typf.gftSort() == Typf.METHOD){
                        bppfndDfsdriptor(METHOD_DESCRIPTOR, typf.gftDfsdriptor());
                    } flsf {
                        buf.bppfnd(typf.gftDfsdriptor()).bppfnd(".dlbss");
                    }
                } flsf if (dst instbndfof Hbndlf) {
                    bppfndHbndlf((Hbndlf) dst);
                } flsf {
                    buf.bppfnd(dst);
                }
                buf.bppfnd(", \n");
            }
            buf.sftLfngti(buf.lfngti() - 3);
        }
        buf.bppfnd('\n');
        buf.bppfnd(tbb2).bppfnd("]\n");
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitJumpInsn(finbl int opdodf, finbl Lbbfl lbbfl) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd(OPCODES[opdodf]).bppfnd(' ');
        bppfndLbbfl(lbbfl);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitLbbfl(finbl Lbbfl lbbfl) {
        buf.sftLfngti(0);
        buf.bppfnd(ltbb);
        bppfndLbbfl(lbbfl);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitLddInsn(finbl Objfdt dst) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("LDC ");
        if (dst instbndfof String) {
            Printfr.bppfndString(buf, (String) dst);
        } flsf if (dst instbndfof Typf) {
            buf.bppfnd(((Typf) dst).gftDfsdriptor()).bppfnd(".dlbss");
        } flsf {
            buf.bppfnd(dst);
        }
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitIindInsn(finbl int vbr, finbl int indrfmfnt) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("IINC ").bppfnd(vbr).bppfnd(' ')
                .bppfnd(indrfmfnt).bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitTbblfSwitdiInsn(finbl int min, finbl int mbx,
            finbl Lbbfl dflt, finbl Lbbfl... lbbfls) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("TABLESWITCH\n");
        for (int i = 0; i < lbbfls.lfngti; ++i) {
            buf.bppfnd(tbb3).bppfnd(min + i).bppfnd(": ");
            bppfndLbbfl(lbbfls[i]);
            buf.bppfnd('\n');
        }
        buf.bppfnd(tbb3).bppfnd("dffbult: ");
        bppfndLbbfl(dflt);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitLookupSwitdiInsn(finbl Lbbfl dflt, finbl int[] kfys,
            finbl Lbbfl[] lbbfls) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("LOOKUPSWITCH\n");
        for (int i = 0; i < lbbfls.lfngti; ++i) {
            buf.bppfnd(tbb3).bppfnd(kfys[i]).bppfnd(": ");
            bppfndLbbfl(lbbfls[i]);
            buf.bppfnd('\n');
        }
        buf.bppfnd(tbb3).bppfnd("dffbult: ");
        bppfndLbbfl(dflt);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitMultiANfwArrbyInsn(finbl String dfsd, finbl int dims) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("MULTIANEWARRAY ");
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd(' ').bppfnd(dims).bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid Printfr visitInsnAnnotbtion(int typfRff, TypfPbti typfPbti,
            String dfsd, boolfbn visiblf) {
        rfturn visitTypfAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
    }

    @Ovfrridf
    publid void visitTryCbtdiBlodk(finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl Lbbfl ibndlfr, finbl String typf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("TRYCATCHBLOCK ");
        bppfndLbbfl(stbrt);
        buf.bppfnd(' ');
        bppfndLbbfl(fnd);
        buf.bppfnd(' ');
        bppfndLbbfl(ibndlfr);
        buf.bppfnd(' ');
        bppfndDfsdriptor(INTERNAL_NAME, typf);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid Printfr visitTryCbtdiAnnotbtion(int typfRff, TypfPbti typfPbti,
            String dfsd, boolfbn visiblf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("TRYCATCHBLOCK @");
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('(');
        tfxt.bdd(buf.toString());
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        buf.sftLfngti(0);
        buf.bppfnd(") : ");
        bppfndTypfRfffrfndf(typfRff);
        buf.bppfnd(", ").bppfnd(typfPbti);
        buf.bppfnd(visiblf ? "\n" : " // invisiblf\n");
        tfxt.bdd(buf.toString());
        rfturn t;
    }

    @Ovfrridf
    publid void visitLodblVbribblf(finbl String nbmf, finbl String dfsd,
            finbl String signbturf, finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl int indfx) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("LOCALVARIABLE ").bppfnd(nbmf).bppfnd(' ');
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd(' ');
        bppfndLbbfl(stbrt);
        buf.bppfnd(' ');
        bppfndLbbfl(fnd);
        buf.bppfnd(' ').bppfnd(indfx).bppfnd('\n');

        if (signbturf != null) {
            buf.bppfnd(tbb2);
            bppfndDfsdriptor(FIELD_SIGNATURE, signbturf);

            TrbdfSignbturfVisitor sv = nfw TrbdfSignbturfVisitor(0);
            SignbturfRfbdfr r = nfw SignbturfRfbdfr(signbturf);
            r.bddfptTypf(sv);
            buf.bppfnd(tbb2).bppfnd("// dfdlbrbtion: ")
                    .bppfnd(sv.gftDfdlbrbtion()).bppfnd('\n');
        }
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid Printfr visitLodblVbribblfAnnotbtion(int typfRff, TypfPbti typfPbti,
            Lbbfl[] stbrt, Lbbfl[] fnd, int[] indfx, String dfsd,
            boolfbn visiblf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("LOCALVARIABLE @");
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('(');
        tfxt.bdd(buf.toString());
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        buf.sftLfngti(0);
        buf.bppfnd(") : ");
        bppfndTypfRfffrfndf(typfRff);
        buf.bppfnd(", ").bppfnd(typfPbti);
        for (int i = 0; i < stbrt.lfngti; ++i) {
            buf.bppfnd(" [ ");
            bppfndLbbfl(stbrt[i]);
            buf.bppfnd(" - ");
            bppfndLbbfl(fnd[i]);
            buf.bppfnd(" - ").bppfnd(indfx[i]).bppfnd(" ]");
        }
        buf.bppfnd(visiblf ? "\n" : " // invisiblf\n");
        tfxt.bdd(buf.toString());
        rfturn t;
    }

    @Ovfrridf
    publid void visitLinfNumbfr(finbl int linf, finbl Lbbfl stbrt) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("LINENUMBER ").bppfnd(linf).bppfnd(' ');
        bppfndLbbfl(stbrt);
        buf.bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitMbxs(finbl int mbxStbdk, finbl int mbxLodbls) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("MAXSTACK = ").bppfnd(mbxStbdk).bppfnd('\n');
        tfxt.bdd(buf.toString());

        buf.sftLfngti(0);
        buf.bppfnd(tbb2).bppfnd("MAXLOCALS = ").bppfnd(mbxLodbls).bppfnd('\n');
        tfxt.bdd(buf.toString());
    }

    @Ovfrridf
    publid void visitMftiodEnd() {
    }

    // ------------------------------------------------------------------------
    // Common mftiods
    // ------------------------------------------------------------------------

    /**
     * Prints b disbssfmblfd vifw of tif givfn bnnotbtion.
     *
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs.
     */
    publid Tfxtififr visitAnnotbtion(finbl String dfsd, finbl boolfbn visiblf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb).bppfnd('@');
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('(');
        tfxt.bdd(buf.toString());
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        tfxt.bdd(visiblf ? ")\n" : ") // invisiblf\n");
        rfturn t;
    }

    /**
     * Prints b disbssfmblfd vifw of tif givfn typf bnnotbtion.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs.
     */
    publid Tfxtififr visitTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb).bppfnd('@');
        bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
        buf.bppfnd('(');
        tfxt.bdd(buf.toString());
        Tfxtififr t = drfbtfTfxtififr();
        tfxt.bdd(t.gftTfxt());
        buf.sftLfngti(0);
        buf.bppfnd(") : ");
        bppfndTypfRfffrfndf(typfRff);
        buf.bppfnd(", ").bppfnd(typfPbti);
        buf.bppfnd(visiblf ? "\n" : " // invisiblf\n");
        tfxt.bdd(buf.toString());
        rfturn t;
    }

    /**
     * Prints b disbssfmblfd vifw of tif givfn bttributf.
     *
     * @pbrbm bttr
     *            bn bttributf.
     */
    publid void visitAttributf(finbl Attributf bttr) {
        buf.sftLfngti(0);
        buf.bppfnd(tbb).bppfnd("ATTRIBUTE ");
        bppfndDfsdriptor(-1, bttr.typf);

        if (bttr instbndfof Tfxtifibblf) {
            ((Tfxtifibblf) bttr).tfxtify(buf, null);
        } flsf {
            buf.bppfnd(" : unknown\n");
        }

        tfxt.bdd(buf.toString());
    }

    // ------------------------------------------------------------------------
    // Utility mftiods
    // ------------------------------------------------------------------------

    /**
     * Crfbtfs b nfw TrbdfVisitor instbndf.
     *
     * @rfturn b nfw TrbdfVisitor.
     */
    protfdtfd Tfxtififr drfbtfTfxtififr() {
        rfturn nfw Tfxtififr();
    }

    /**
     * Appfnds bn intfrnbl nbmf, b typf dfsdriptor or b typf signbturf to
     * {@link #buf buf}.
     *
     * @pbrbm typf
     *            indidbtfs if dfsd is bn intfrnbl nbmf, b fifld dfsdriptor, b
     *            mftiod dfsdriptor, b dlbss signbturf, ...
     * @pbrbm dfsd
     *            bn intfrnbl nbmf, typf dfsdriptor, or typf signbturf. Mby bf
     *            <tt>null</tt>.
     */
    protfdtfd void bppfndDfsdriptor(finbl int typf, finbl String dfsd) {
        if (typf == CLASS_SIGNATURE || typf == FIELD_SIGNATURE
                || typf == METHOD_SIGNATURE) {
            if (dfsd != null) {
                buf.bppfnd("// signbturf ").bppfnd(dfsd).bppfnd('\n');
            }
        } flsf {
            buf.bppfnd(dfsd);
        }
    }

    /**
     * Appfnds tif nbmf of tif givfn lbbfl to {@link #buf buf}. Crfbtfs b nfw
     * lbbfl nbmf if tif givfn lbbfl dofs not yft ibvf onf.
     *
     * @pbrbm l
     *            b lbbfl.
     */
    protfdtfd void bppfndLbbfl(finbl Lbbfl l) {
        if (lbbflNbmfs == null) {
            lbbflNbmfs = nfw HbsiMbp<Lbbfl, String>();
        }
        String nbmf = lbbflNbmfs.gft(l);
        if (nbmf == null) {
            nbmf = "L" + lbbflNbmfs.sizf();
            lbbflNbmfs.put(l, nbmf);
        }
        buf.bppfnd(nbmf);
    }

    /**
     * Appfnds tif informbtion bbout tif givfn ibndlf to {@link #buf buf}.
     *
     * @pbrbm i
     *            b ibndlf, non null.
     */
    protfdtfd void bppfndHbndlf(finbl Hbndlf i) {
        int tbg = i.gftTbg();
        buf.bppfnd("// ibndlf kind 0x").bppfnd(Intfgfr.toHfxString(tbg))
                .bppfnd(" : ");
        boolfbn isMftiodHbndlf = fblsf;
        switdi (tbg) {
        dbsf Opdodfs.H_GETFIELD:
            buf.bppfnd("GETFIELD");
            brfbk;
        dbsf Opdodfs.H_GETSTATIC:
            buf.bppfnd("GETSTATIC");
            brfbk;
        dbsf Opdodfs.H_PUTFIELD:
            buf.bppfnd("PUTFIELD");
            brfbk;
        dbsf Opdodfs.H_PUTSTATIC:
            buf.bppfnd("PUTSTATIC");
            brfbk;
        dbsf Opdodfs.H_INVOKEINTERFACE:
            buf.bppfnd("INVOKEINTERFACE");
            isMftiodHbndlf = truf;
            brfbk;
        dbsf Opdodfs.H_INVOKESPECIAL:
            buf.bppfnd("INVOKESPECIAL");
            isMftiodHbndlf = truf;
            brfbk;
        dbsf Opdodfs.H_INVOKESTATIC:
            buf.bppfnd("INVOKESTATIC");
            isMftiodHbndlf = truf;
            brfbk;
        dbsf Opdodfs.H_INVOKEVIRTUAL:
            buf.bppfnd("INVOKEVIRTUAL");
            isMftiodHbndlf = truf;
            brfbk;
        dbsf Opdodfs.H_NEWINVOKESPECIAL:
            buf.bppfnd("NEWINVOKESPECIAL");
            isMftiodHbndlf = truf;
            brfbk;
        }
        buf.bppfnd('\n');
        buf.bppfnd(tbb3);
        bppfndDfsdriptor(INTERNAL_NAME, i.gftOwnfr());
        buf.bppfnd('.');
        buf.bppfnd(i.gftNbmf());
        if(!isMftiodHbndlf){
            buf.bppfnd('(');
        }
        bppfndDfsdriptor(HANDLE_DESCRIPTOR, i.gftDfsd());
        if(!isMftiodHbndlf){
            buf.bppfnd(')');
        }
    }

    /**
     * Appfnds b string rfprfsfntbtion of tif givfn bddfss modififrs to
     * {@link #buf buf}.
     *
     * @pbrbm bddfss
     *            somf bddfss modififrs.
     */
    privbtf void bppfndAddfss(finbl int bddfss) {
        if ((bddfss & Opdodfs.ACC_PUBLIC) != 0) {
            buf.bppfnd("publid ");
        }
        if ((bddfss & Opdodfs.ACC_PRIVATE) != 0) {
            buf.bppfnd("privbtf ");
        }
        if ((bddfss & Opdodfs.ACC_PROTECTED) != 0) {
            buf.bppfnd("protfdtfd ");
        }
        if ((bddfss & Opdodfs.ACC_FINAL) != 0) {
            buf.bppfnd("finbl ");
        }
        if ((bddfss & Opdodfs.ACC_STATIC) != 0) {
            buf.bppfnd("stbtid ");
        }
        if ((bddfss & Opdodfs.ACC_SYNCHRONIZED) != 0) {
            buf.bppfnd("syndironizfd ");
        }
        if ((bddfss & Opdodfs.ACC_VOLATILE) != 0) {
            buf.bppfnd("volbtilf ");
        }
        if ((bddfss & Opdodfs.ACC_TRANSIENT) != 0) {
            buf.bppfnd("trbnsifnt ");
        }
        if ((bddfss & Opdodfs.ACC_ABSTRACT) != 0) {
            buf.bppfnd("bbstrbdt ");
        }
        if ((bddfss & Opdodfs.ACC_STRICT) != 0) {
            buf.bppfnd("stridtfp ");
        }
        if ((bddfss & Opdodfs.ACC_SYNTHETIC) != 0) {
            buf.bppfnd("syntiftid ");
        }
        if ((bddfss & Opdodfs.ACC_MANDATED) != 0) {
            buf.bppfnd("mbndbtfd ");
        }
        if ((bddfss & Opdodfs.ACC_ENUM) != 0) {
            buf.bppfnd("fnum ");
        }
    }

    privbtf void bppfndComb(finbl int i) {
        if (i != 0) {
            buf.bppfnd(", ");
        }
    }

    privbtf void bppfndTypfRfffrfndf(finbl int typfRff) {
        TypfRfffrfndf rff = nfw TypfRfffrfndf(typfRff);
        switdi (rff.gftSort()) {
        dbsf TypfRfffrfndf.CLASS_TYPE_PARAMETER:
            buf.bppfnd("CLASS_TYPE_PARAMETER ").bppfnd(
                    rff.gftTypfPbrbmftfrIndfx());
            brfbk;
        dbsf TypfRfffrfndf.METHOD_TYPE_PARAMETER:
            buf.bppfnd("METHOD_TYPE_PARAMETER ").bppfnd(
                    rff.gftTypfPbrbmftfrIndfx());
            brfbk;
        dbsf TypfRfffrfndf.CLASS_EXTENDS:
            buf.bppfnd("CLASS_EXTENDS ").bppfnd(rff.gftSupfrTypfIndfx());
            brfbk;
        dbsf TypfRfffrfndf.CLASS_TYPE_PARAMETER_BOUND:
            buf.bppfnd("CLASS_TYPE_PARAMETER_BOUND ")
                    .bppfnd(rff.gftTypfPbrbmftfrIndfx()).bppfnd(", ")
                    .bppfnd(rff.gftTypfPbrbmftfrBoundIndfx());
            brfbk;
        dbsf TypfRfffrfndf.METHOD_TYPE_PARAMETER_BOUND:
            buf.bppfnd("METHOD_TYPE_PARAMETER_BOUND ")
                    .bppfnd(rff.gftTypfPbrbmftfrIndfx()).bppfnd(", ")
                    .bppfnd(rff.gftTypfPbrbmftfrBoundIndfx());
            brfbk;
        dbsf TypfRfffrfndf.FIELD:
            buf.bppfnd("FIELD");
            brfbk;
        dbsf TypfRfffrfndf.METHOD_RETURN:
            buf.bppfnd("METHOD_RETURN");
            brfbk;
        dbsf TypfRfffrfndf.METHOD_RECEIVER:
            buf.bppfnd("METHOD_RECEIVER");
            brfbk;
        dbsf TypfRfffrfndf.METHOD_FORMAL_PARAMETER:
            buf.bppfnd("METHOD_FORMAL_PARAMETER ").bppfnd(
                    rff.gftFormblPbrbmftfrIndfx());
            brfbk;
        dbsf TypfRfffrfndf.THROWS:
            buf.bppfnd("THROWS ").bppfnd(rff.gftExdfptionIndfx());
            brfbk;
        dbsf TypfRfffrfndf.LOCAL_VARIABLE:
            buf.bppfnd("LOCAL_VARIABLE");
            brfbk;
        dbsf TypfRfffrfndf.RESOURCE_VARIABLE:
            buf.bppfnd("RESOURCE_VARIABLE");
            brfbk;
        dbsf TypfRfffrfndf.EXCEPTION_PARAMETER:
            buf.bppfnd("EXCEPTION_PARAMETER ").bppfnd(
                    rff.gftTryCbtdiBlodkIndfx());
            brfbk;
        dbsf TypfRfffrfndf.INSTANCEOF:
            buf.bppfnd("INSTANCEOF");
            brfbk;
        dbsf TypfRfffrfndf.NEW:
            buf.bppfnd("NEW");
            brfbk;
        dbsf TypfRfffrfndf.CONSTRUCTOR_REFERENCE:
            buf.bppfnd("CONSTRUCTOR_REFERENCE");
            brfbk;
        dbsf TypfRfffrfndf.METHOD_REFERENCE:
            buf.bppfnd("METHOD_REFERENCE");
            brfbk;
        dbsf TypfRfffrfndf.CAST:
            buf.bppfnd("CAST ").bppfnd(rff.gftTypfArgumfntIndfx());
            brfbk;
        dbsf TypfRfffrfndf.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
            buf.bppfnd("CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT ").bppfnd(
                    rff.gftTypfArgumfntIndfx());
            brfbk;
        dbsf TypfRfffrfndf.METHOD_INVOCATION_TYPE_ARGUMENT:
            buf.bppfnd("METHOD_INVOCATION_TYPE_ARGUMENT ").bppfnd(
                    rff.gftTypfArgumfntIndfx());
            brfbk;
        dbsf TypfRfffrfndf.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
            buf.bppfnd("CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT ").bppfnd(
                    rff.gftTypfArgumfntIndfx());
            brfbk;
        dbsf TypfRfffrfndf.METHOD_REFERENCE_TYPE_ARGUMENT:
            buf.bppfnd("METHOD_REFERENCE_TYPE_ARGUMENT ").bppfnd(
                    rff.gftTypfArgumfntIndfx());
            brfbk;
        }
    }

    privbtf void bppfndFrbmfTypfs(finbl int n, finbl Objfdt[] o) {
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                buf.bppfnd(' ');
            }
            if (o[i] instbndfof String) {
                String dfsd = (String) o[i];
                if (dfsd.stbrtsWiti("[")) {
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, dfsd);
                } flsf {
                    bppfndDfsdriptor(INTERNAL_NAME, dfsd);
                }
            } flsf if (o[i] instbndfof Intfgfr) {
                switdi (((Intfgfr) o[i]).intVbluf()) {
                dbsf 0:
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, "T");
                    brfbk;
                dbsf 1:
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, "I");
                    brfbk;
                dbsf 2:
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, "F");
                    brfbk;
                dbsf 3:
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, "D");
                    brfbk;
                dbsf 4:
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, "J");
                    brfbk;
                dbsf 5:
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, "N");
                    brfbk;
                dbsf 6:
                    bppfndDfsdriptor(FIELD_DESCRIPTOR, "U");
                    brfbk;
                }
            } flsf {
                bppfndLbbfl((Lbbfl) o[i]);
            }
        }
    }
}
