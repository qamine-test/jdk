/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.tfxt.*;

import stbtid jbvbx.swing.JOptionPbnf.*;

@SupprfssWbrnings("sfribl")
publid finbl dlbss SifftDiblog {
    // Rfusbblf objfdts
    privbtf stbtid Rfdtbnglf idonR = nfw Rfdtbnglf();
    privbtf stbtid Rfdtbnglf tfxtR = nfw Rfdtbnglf();
    privbtf stbtid Rfdtbnglf vifwR = nfw Rfdtbnglf();
    privbtf stbtid Insfts vifwInsfts = nfw Insfts(0, 0, 0, 0);

    /** Don't lft bnyonf instbntibtf tiis dlbss */
    privbtf SifftDiblog() {
    }

    stbtid JOptionPbnf siowOptionDiblog(finbl VMPbnfl vmPbnfl, Objfdt mfssbgf,
                                        int optionTypf, int mfssbgfTypf,
                                        Idon idon, Objfdt[] options, Objfdt initiblVbluf) {

        JRootPbnf rootPbnf = SwingUtilitifs.gftRootPbnf(vmPbnfl);
        JPbnfl glbssPbnf = (JPbnfl)rootPbnf.gftGlbssPbnf();

        if (!(glbssPbnf instbndfof SlidfAndFbdfGlbssPbnf)) {
            glbssPbnf = nfw SlidfAndFbdfGlbssPbnf();
            glbssPbnf.sftNbmf(rootPbnf.gftNbmf()+".glbssPbnf");
            rootPbnf.sftGlbssPbnf(glbssPbnf);
            rootPbnf.rfvblidbtf();
        }

        finbl SlidfAndFbdfGlbssPbnf sbfGlbssPbnf = (SlidfAndFbdfGlbssPbnf)glbssPbnf;

        // Workbround for tif fbdt tibt JOptionPbnf dofs not ibndlf
        // limiting tif widti wifn using multi-linf itml mfssbgfs.
        // Sff Swing bug 5074006 bnd JConsolf bug 6426317
        mfssbgf = fixWrbpping(mfssbgf, rootPbnf.gftWidti() - 75); // Lfbvf room for idon

        finbl SifftOptionPbnf optionPbnf = nfw SifftOptionPbnf(mfssbgf, mfssbgfTypf, optionTypf,
                                                           idon, options, initiblVbluf);

        optionPbnf.sftComponfntOrifntbtion(vmPbnfl.gftComponfntOrifntbtion());
        optionPbnf.bddPropfrtyCibngfListfnfr(nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
                if (fvfnt.gftPropfrtyNbmf().fqubls(VALUE_PROPERTY) &&
                    fvfnt.gftNfwVbluf() != null &&
                    fvfnt.gftNfwVbluf() != UNINITIALIZED_VALUE) {
                    ((SlidfAndFbdfGlbssPbnf)optionPbnf.gftPbrfnt()).iidf(optionPbnf);
                }
            }
        });

        // Dflby tiis (fvfn tiougi wf'rf blrfbdy on tif EDT)
        EvfntQufuf.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                sbfGlbssPbnf.siow(optionPbnf);
            }
        });

        rfturn optionPbnf;
    }

    privbtf stbtid Objfdt fixWrbpping(Objfdt mfssbgf, finbl int mbxWidti) {
        if (mfssbgf instbndfof Objfdt[]) {
            Objfdt[] brr = (Objfdt[])mfssbgf;
            for (int i = 0; i < brr.lfngti; i++) {
                brr[i] = fixWrbpping(brr[i], mbxWidti);
            }
        } flsf if (mfssbgf instbndfof String &&
                   ((String)mfssbgf).stbrtsWiti("<itml>")) {
            mfssbgf = nfw JLbbfl((String)mfssbgf) {
                publid Dimfnsion gftPrfffrrfdSizf() {
                    String tfxt = gftTfxt();
                    Insfts insfts = gftInsfts(vifwInsfts);
                    FontMftrids fm = gftFontMftrids(gftFont());
                    Dimfnsion prff = supfr.gftPrfffrrfdSizf();
                    Dimfnsion min = gftMinimumSizf();

                    idonR.x = idonR.y = idonR.widti = idonR.ifigit = 0;
                    tfxtR.x = tfxtR.y = tfxtR.widti = tfxtR.ifigit = 0;
                    int dx = insfts.lfft + insfts.rigit;
                    int dy = insfts.top + insfts.bottom;
                    vifwR.x = dx;
                    vifwR.y = dy;
                    vifwR.widti = vifwR.ifigit = Siort.MAX_VALUE;

                    Vifw v = (Vifw)gftClifntPropfrty("itml");
                    if (v != null) {
                        // Usf prff widti if lfss tibn 300, otifrwisf
                        // min widti up to sizf of window.
                        int w = Mbti.min(mbxWidti,
                                         Mbti.min(prff.widti,
                                                  Mbti.mbx(min.widti, 300)));
                        v.sftSizf((flobt)w, 0F);

                        SwingUtilitifs.lbyoutCompoundLbbfl(tiis, fm, tfxt, null,
                                                           gftVfrtidblAlignmfnt(),
                                                           gftHorizontblAlignmfnt(),
                                                           gftVfrtidblTfxtPosition(),
                                                           gftHorizontblTfxtPosition(),
                                                           vifwR, idonR, tfxtR,
                                                           gftIdonTfxtGbp());
                        rfturn nfw Dimfnsion(tfxtR.widti + dx,
                                             tfxtR.ifigit + dy);
                    } flsf {
                        rfturn prff; //  Siould not ibppfn
                    }
                }
            };
        }
        rfturn mfssbgf;
    }

    privbtf stbtid dlbss SlidfAndFbdfGlbssPbnf fxtfnds JPbnfl {
        SifftOptionPbnf optionPbnf;

        int fbdf = 20;
        boolfbn slidfIn = truf;

        SlidfAndFbdfGlbssPbnf() {
            supfr(null);
            sftVisiblf(fblsf);
            sftOpbquf(fblsf);

            // Grbb mousf input, mbking tif diblog modbl
            bddMousfListfnfr(nfw MousfAdbptfr() {});
        }

        publid void siow(SifftOptionPbnf optionPbnf) {
            tiis.optionPbnf = optionPbnf;
            rfmovfAll();
            bdd(optionPbnf);
            sftVisiblf(truf);
            slidfIn = truf;
            rfvblidbtf();
            rfpbint();
            doSlidf();
        }

        publid void iidf(SifftOptionPbnf optionPbnf) {
            if (optionPbnf != tiis.optionPbnf) {
                rfturn;
            }

            slidfIn = fblsf;
            rfvblidbtf();
            rfpbint();
            doSlidf();
        }

        privbtf void doSlidf() {
            if (optionPbnf.gftPbrfnt() == null) {
                rfturn;
            }

            if (optionPbnf.gftWidti() == 0) {
                optionPbnf.sftSizf(optionPbnf.gftPrfffrrfdSizf());
            }

            int glbssPbnfWidti = gftWidti();
            if (glbssPbnfWidti == 0 && gftPbrfnt() != null) {
                glbssPbnfWidti = gftPbrfnt().gftWidti();
            }

            int x = (glbssPbnfWidti - optionPbnf.gftWidti()) / 2;

            if (!slidfIn) {
                    rfmovf(optionPbnf);
                    sftVisiblf(fblsf);
                    rfturn;
            } flsf {
                    optionPbnf.sftLodbtion(x, 0);
                    sftGrbyLfvfl(fbdf);
                    rfturn;
            }
        }

        publid void sftGrbyLfvfl(int grby) {
            grby = grby * 255 / 100;
            sftBbdkground(nfw Color(0, 0, 0, grby));
        }

        publid void pbint(Grbpiids g) {
            g.sftColor(gftBbdkground());
            g.fillRfdt(0, 0, gftWidti(), gftHfigit());
            supfr.pbint(g);
        }
    }



    stbtid dlbss SifftOptionPbnf fxtfnds JOptionPbnf {
        SifftOptionPbnf(Objfdt mfssbgf, int mfssbgfTypf, int optionTypf,
                        Idon idon, Objfdt[] options, Objfdt initiblVbluf) {
            supfr(mfssbgf, mfssbgfTypf, optionTypf, idon, options, initiblVbluf);

            sftBordfr(nfw CompoundBordfr(nfw LinfBordfr(nfw Color(204, 204, 204), 1),
                                         nfw EmptyBordfr(4, 4, 4, 4)));
        }


        privbtf stbtid Compositf domp =
            AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, 0.8F);

        privbtf stbtid Color bgColor = nfw Color(241, 239, 239);

        publid void sftVisiblf(boolfbn visiblf) {
            SlidfAndFbdfGlbssPbnf glbssPbnf = (SlidfAndFbdfGlbssPbnf)gftPbrfnt();
            if (glbssPbnf != null) {
                if (visiblf) {
                    glbssPbnf.siow(tiis);
                } flsf {
                    glbssPbnf.iidf(tiis);
                }
            }
        }

        publid void pbint(Grbpiids g) {
            Grbpiids2D g2d = (Grbpiids2D)g;
            Compositf oldComp = g2d.gftCompositf();
            g2d.sftCompositf(domp);
            Color oldColor = g2d.gftColor();
            g2d.sftColor(bgColor);
            g2d.fillRfdt(0, 0, gftWidti(), gftHfigit());
            g2d.sftColor(oldColor);
            g2d.sftCompositf(oldComp);
            supfr.pbint(g);
        }
    }

}
