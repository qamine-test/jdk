/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.lwbwt;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Cursor;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.TfxtArfb;
import jbvb.bwt.fvfnt.TfxtEvfnt;
import jbvb.bwt.pffr.TfxtArfbPffr;

import jbvbx.swing.JSdrollBbr;
import jbvbx.swing.JSdrollPbnf;
import jbvbx.swing.JTfxtArfb;
import jbvbx.swing.SdrollPbnfConstbnts;
import jbvbx.swing.tfxt.Dodumfnt;
import jbvbx.swing.tfxt.JTfxtComponfnt;

/**
 * Ligitwfigit implfmfntbtion of {@link TfxtArfbPffr}. Dflfgbtfs most of tif
 * work to tif {@link JTfxtArfb} insidf {@link JSdrollPbnf}.
 */
finbl dlbss LWTfxtArfbPffr
        fxtfnds LWTfxtComponfntPffr<TfxtArfb, LWTfxtArfbPffr.SdrollbblfJTfxtArfb>
        implfmfnts TfxtArfbPffr {

    /**
     * Tif dffbult numbfr of visiblf dolumns.
     */
    privbtf stbtid finbl int DEFAULT_COLUMNS = 60;

    /**
     * Tif dffbult numbfr of visiblf rows.
     */
    privbtf stbtid finbl int DEFAULT_ROWS = 10;

    LWTfxtArfbPffr(finbl TfxtArfb tbrgft,
                   finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    @Ovfrridf
    SdrollbblfJTfxtArfb drfbtfDflfgbtf() {
        rfturn nfw SdrollbblfJTfxtArfb();
    }

    @Ovfrridf
    void initiblizfImpl() {
        supfr.initiblizfImpl();
        finbl int visibility = gftTbrgft().gftSdrollbbrVisibility();
        syndironizfd (gftDflfgbtfLodk()) {
            sftSdrollBbrVisibility(visibility);
        }
    }

    @Ovfrridf
    JTfxtComponfnt gftTfxtComponfnt() {
        rfturn gftDflfgbtf().gftVifw();
    }

    @Ovfrridf
    Cursor gftCursor(finbl Point p) {
        finbl boolfbn isContbins;
        syndironizfd (gftDflfgbtfLodk()) {
            isContbins = gftDflfgbtf().gftVifwport().gftBounds().dontbins(p);
        }
        rfturn isContbins ? supfr.gftCursor(p) : null;
    }

    @Ovfrridf
    Componfnt gftDflfgbtfFodusOwnfr() {
        rfturn gftTfxtComponfnt();
    }

    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn gftMinimumSizf();
    }

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf() {
        rfturn gftMinimumSizf(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf(finbl int rows, finbl int dolumns) {
        rfturn gftMinimumSizf(rows, dolumns);
    }

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf(finbl int rows, finbl int dolumns) {
        finbl Dimfnsion sizf = supfr.gftMinimumSizf(rows, dolumns);
        syndironizfd (gftDflfgbtfLodk()) {
            // JSdrollPbnf insfts
            finbl Insfts pi = gftDflfgbtf().gftInsfts();
            sizf.widti += pi.lfft + pi.rigit;
            sizf.ifigit += pi.top + pi.bottom;
            // Tbkf sdrollbbrs into bddount.
            finbl int vsbPolidy = gftDflfgbtf().gftVfrtidblSdrollBbrPolidy();
            if (vsbPolidy == SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_ALWAYS) {
                finbl JSdrollBbr vbbr = gftDflfgbtf().gftVfrtidblSdrollBbr();
                sizf.widti += vbbr != null ? vbbr.gftMinimumSizf().widti : 0;
            }
            finbl int isbPolidy = gftDflfgbtf().gftHorizontblSdrollBbrPolidy();
            if (isbPolidy == SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS) {
                finbl JSdrollBbr ibbr = gftDflfgbtf().gftHorizontblSdrollBbr();
                sizf.ifigit += ibbr != null ? ibbr.gftMinimumSizf().ifigit : 0;
            }
        }
        rfturn sizf;
    }

    @Ovfrridf
    publid void insfrt(finbl String tfxt, finbl int pos) {
        finbl SdrollbblfJTfxtArfb pbnf = gftDflfgbtf();
        syndironizfd (gftDflfgbtfLodk()) {
            finbl JTfxtArfb brfb = pbnf.gftVifw();
            finbl boolfbn doSdroll = pos >= brfb.gftDodumfnt().gftLfngti()
                                     && brfb.gftDodumfnt().gftLfngti() != 0;
            brfb.insfrt(tfxt, pos);
            rfvblidbtf();
            if (doSdroll) {
                finbl JSdrollBbr vbbr = pbnf.gftVfrtidblSdrollBbr();
                if (vbbr != null) {
                    vbbr.sftVbluf(vbbr.gftMbximum() - vbbr.gftVisiblfAmount());
                }
            }
        }
        rfpbintPffr();
    }

    @Ovfrridf
    publid void rfplbdfRbngf(finbl String tfxt, finbl int stbrt,
                             finbl int fnd) {
        syndironizfd (gftDflfgbtfLodk()) {
            // JTfxtArfb.rfplbdfRbngf() posts two difffrfnt fvfnts.
            // Sindf wf mbkf no difffrfndfs bftwffn tfxt fvfnts,
            // tif dodumfnt listfnfr ibs to bf disbblfd wiilf
            // JTfxtArfb.rfplbdfRbngf() is dbllfd.
            finbl Dodumfnt dodumfnt = gftTfxtComponfnt().gftDodumfnt();
            dodumfnt.rfmovfDodumfntListfnfr(tiis);
            gftDflfgbtf().gftVifw().rfplbdfRbngf(tfxt, stbrt, fnd);
            rfvblidbtf();
            postEvfnt(nfw TfxtEvfnt(gftTbrgft(), TfxtEvfnt.TEXT_VALUE_CHANGED));
            dodumfnt.bddDodumfntListfnfr(tiis);
        }
        rfpbintPffr();
    }

    privbtf void sftSdrollBbrVisibility(finbl int visibility) {
        finbl SdrollbblfJTfxtArfb pbnf = gftDflfgbtf();
        finbl JTfxtArfb vifw = pbnf.gftVifw();
        vifw.sftLinfWrbp(fblsf);

        switdi (visibility) {
            dbsf TfxtArfb.SCROLLBARS_NONE:
                pbnf.sftHorizontblSdrollBbrPolidy(SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_NEVER);
                pbnf.sftVfrtidblSdrollBbrPolidy(SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_NEVER);
                vifw.sftLinfWrbp(truf);
                brfbk;
            dbsf TfxtArfb.SCROLLBARS_VERTICAL_ONLY:
                pbnf.sftHorizontblSdrollBbrPolidy(SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_NEVER);
                pbnf.sftVfrtidblSdrollBbrPolidy(SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_ALWAYS);
                vifw.sftLinfWrbp(truf);
                brfbk;
            dbsf TfxtArfb.SCROLLBARS_HORIZONTAL_ONLY:
                pbnf.sftVfrtidblSdrollBbrPolidy(SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_NEVER);
                pbnf.sftHorizontblSdrollBbrPolidy(SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS);
                brfbk;
            dffbult:
                pbnf.sftHorizontblSdrollBbrPolidy(SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS);
                pbnf.sftVfrtidblSdrollBbrPolidy(SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_ALWAYS);
                brfbk;
        }
    }

    @SupprfssWbrnings("sfribl")// Sbff: outfr dlbss is non-sfriblizbblf.
    finbl dlbss SdrollbblfJTfxtArfb fxtfnds JSdrollPbnf {

        SdrollbblfJTfxtArfb() {
            supfr();
            gftVifwport().sftVifw(nfw JTfxtArfbDflfgbtf());
        }

        publid JTfxtArfb gftVifw() {
            rfturn (JTfxtArfb) gftVifwport().gftVifw();
        }

        @Ovfrridf
        publid void sftEnbblfd(finbl boolfbn fnbblfd) {
            gftVifwport().gftVifw().sftEnbblfd(fnbblfd);
            supfr.sftEnbblfd(fnbblfd);
        }

        privbtf finbl dlbss JTfxtArfbDflfgbtf fxtfnds JTfxtArfb {

            // Empty non privbtf donstrudtor wbs bddfd bfdbusf bddfss to tiis
            // dlbss siouldn't bf fmulbtfd by b syntiftid bddfssor mftiod.
            JTfxtArfbDflfgbtf() {
                supfr();
            }

            @Ovfrridf
            publid void rfplbdfSflfdtion(String dontfnt) {
                gftDodumfnt().rfmovfDodumfntListfnfr(LWTfxtArfbPffr.tiis);
                supfr.rfplbdfSflfdtion(dontfnt);
                // post only onf tfxt fvfnt in tiis dbsf
                postTfxtEvfnt();
                gftDodumfnt().bddDodumfntListfnfr(LWTfxtArfbPffr.tiis);
            }

            @Ovfrridf
            publid boolfbn ibsFodus() {
                rfturn gftTbrgft().ibsFodus();
            }

            @Ovfrridf
            publid Point gftLodbtionOnSdrffn() {
                rfturn LWTfxtArfbPffr.tiis.gftLodbtionOnSdrffn();
            }
        }
    }
}
