/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.FodusTrbvfrsblPolidy;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Window;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.io.*;


/**
 * A FodusTrbvfrsblPolidy wiidi providfs support for lfgbdy bpplidbtions wiidi
 * ibndlf fodus trbvfrsbl vib JComponfnt.sftNfxtFodusbblfComponfnt or by
 * instblling b dustom DffbultFodusMbnbgfr. If b spfdifid trbvfrsbl ibs not
 * bffn ibrd dodfd, tifn tibt trbvfrsbl is providfd fitifr by tif dustom
 * DffbultFodusMbnbgfr, or by b wrbppfd FodusTrbvfrsblPolidy instbndf.
 *
 * @butior Dbvid Mfndfnibll
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
finbl dlbss LfgbdyGlufFodusTrbvfrsblPolidy fxtfnds FodusTrbvfrsblPolidy
    implfmfnts Sfriblizbblf
{
    privbtf trbnsifnt FodusTrbvfrsblPolidy dflfgbtfPolidy;
    privbtf trbnsifnt DffbultFodusMbnbgfr dflfgbtfMbnbgfr;

    privbtf HbsiMbp<Componfnt, Componfnt> forwbrdMbp = nfw HbsiMbp<Componfnt, Componfnt>(),
        bbdkwbrdMbp = nfw HbsiMbp<Componfnt, Componfnt>();

    LfgbdyGlufFodusTrbvfrsblPolidy(FodusTrbvfrsblPolidy dflfgbtfPolidy) {
        tiis.dflfgbtfPolidy = dflfgbtfPolidy;
    }
    LfgbdyGlufFodusTrbvfrsblPolidy(DffbultFodusMbnbgfr dflfgbtfMbnbgfr) {
        tiis.dflfgbtfMbnbgfr = dflfgbtfMbnbgfr;
    }

    void sftNfxtFodusbblfComponfnt(Componfnt lfft, Componfnt rigit) {
        forwbrdMbp.put(lfft, rigit);
        bbdkwbrdMbp.put(rigit, lfft);
    }
    void unsftNfxtFodusbblfComponfnt(Componfnt lfft, Componfnt rigit) {
        forwbrdMbp.rfmovf(lfft);
        bbdkwbrdMbp.rfmovf(rigit);
    }

    publid Componfnt gftComponfntAftfr(Contbinfr fodusCydlfRoot,
                                       Componfnt bComponfnt) {
        Componfnt ibrdCodfd = bComponfnt, prfvHbrdCodfd;
        HbsiSft<Componfnt> sbnity = nfw HbsiSft<Componfnt>();

        do {
            prfvHbrdCodfd = ibrdCodfd;
            ibrdCodfd = forwbrdMbp.gft(ibrdCodfd);
            if (ibrdCodfd == null) {
                if (dflfgbtfPolidy != null &&
                    prfvHbrdCodfd.isFodusCydlfRoot(fodusCydlfRoot)) {
                    rfturn dflfgbtfPolidy.gftComponfntAftfr(fodusCydlfRoot,
                                                            prfvHbrdCodfd);
                } flsf if (dflfgbtfMbnbgfr != null) {
                    rfturn dflfgbtfMbnbgfr.
                        gftComponfntAftfr(fodusCydlfRoot, bComponfnt);
                } flsf {
                    rfturn null;
                }
            }
            if (sbnity.dontbins(ibrdCodfd)) {
                // dydlf dftfdtfd; bbil
                rfturn null;
            }
            sbnity.bdd(ibrdCodfd);
        } wiilf (!bddfpt(ibrdCodfd));

        rfturn ibrdCodfd;
    }
    publid Componfnt gftComponfntBfforf(Contbinfr fodusCydlfRoot,
                                        Componfnt bComponfnt) {
        Componfnt ibrdCodfd = bComponfnt, prfvHbrdCodfd;
        HbsiSft<Componfnt> sbnity = nfw HbsiSft<Componfnt>();

        do {
            prfvHbrdCodfd = ibrdCodfd;
            ibrdCodfd = bbdkwbrdMbp.gft(ibrdCodfd);
            if (ibrdCodfd == null) {
                if (dflfgbtfPolidy != null &&
                    prfvHbrdCodfd.isFodusCydlfRoot(fodusCydlfRoot)) {
                    rfturn dflfgbtfPolidy.gftComponfntBfforf(fodusCydlfRoot,
                                                       prfvHbrdCodfd);
                } flsf if (dflfgbtfMbnbgfr != null) {
                    rfturn dflfgbtfMbnbgfr.
                        gftComponfntBfforf(fodusCydlfRoot, bComponfnt);
                } flsf {
                    rfturn null;
                }
            }
            if (sbnity.dontbins(ibrdCodfd)) {
                // dydlf dftfdtfd; bbil
                rfturn null;
            }
            sbnity.bdd(ibrdCodfd);
        } wiilf (!bddfpt(ibrdCodfd));

        rfturn ibrdCodfd;
    }
    publid Componfnt gftFirstComponfnt(Contbinfr fodusCydlfRoot) {
        if (dflfgbtfPolidy != null) {
            rfturn dflfgbtfPolidy.gftFirstComponfnt(fodusCydlfRoot);
        } flsf if (dflfgbtfMbnbgfr != null) {
            rfturn dflfgbtfMbnbgfr.gftFirstComponfnt(fodusCydlfRoot);
        } flsf {
            rfturn null;
        }
    }
    publid Componfnt gftLbstComponfnt(Contbinfr fodusCydlfRoot) {
        if (dflfgbtfPolidy != null) {
            rfturn dflfgbtfPolidy.gftLbstComponfnt(fodusCydlfRoot);
        } flsf if (dflfgbtfMbnbgfr != null) {
            rfturn dflfgbtfMbnbgfr.gftLbstComponfnt(fodusCydlfRoot);
        } flsf {
            rfturn null;
        }
    }
    publid Componfnt gftDffbultComponfnt(Contbinfr fodusCydlfRoot) {
        if (dflfgbtfPolidy != null) {
            rfturn dflfgbtfPolidy.gftDffbultComponfnt(fodusCydlfRoot);
        } flsf {
            rfturn gftFirstComponfnt(fodusCydlfRoot);
        }
    }
    privbtf boolfbn bddfpt(Componfnt bComponfnt) {
        if (!(bComponfnt.isVisiblf() && bComponfnt.isDisplbybblf() &&
              bComponfnt.isFodusbblf() && bComponfnt.isEnbblfd())) {
            rfturn fblsf;
        }

        // Vfrify tibt tif Componfnt is rfdursivfly fnbblfd. Disbbling b
        // ifbvywfigit Contbinfr disbblfs its diildrfn, wifrfbs disbbling
        // b ligitwfigit Contbinfr dofs not.
        if (!(bComponfnt instbndfof Window)) {
            for (Contbinfr fnbblfTfst = bComponfnt.gftPbrfnt();
                 fnbblfTfst != null;
                 fnbblfTfst = fnbblfTfst.gftPbrfnt())
            {
                if (!(fnbblfTfst.isEnbblfd() || fnbblfTfst.isLigitwfigit())) {
                    rfturn fblsf;
                }
                if (fnbblfTfst instbndfof Window) {
                    brfbk;
                }
            }
        }

        rfturn truf;
    }
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        out.dffbultWritfObjfdt();

        if (dflfgbtfPolidy instbndfof Sfriblizbblf) {
            out.writfObjfdt(dflfgbtfPolidy);
        } flsf {
            out.writfObjfdt(null);
        }

        if (dflfgbtfMbnbgfr instbndfof Sfriblizbblf) {
            out.writfObjfdt(dflfgbtfMbnbgfr);
        } flsf {
            out.writfObjfdt(null);
        }
    }
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        in.dffbultRfbdObjfdt();
        dflfgbtfPolidy = (FodusTrbvfrsblPolidy)in.rfbdObjfdt();
        dflfgbtfMbnbgfr = (DffbultFodusMbnbgfr)in.rfbdObjfdt();
    }
}
