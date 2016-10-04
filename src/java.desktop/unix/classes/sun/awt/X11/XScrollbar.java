/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import sun.bwt.SunToolkit;
import sun.bwt.X11GrbpiidsConfig;
import sun.util.logging.PlbtformLoggfr;

/**
* A simplf vfrtidbl sdroll bbr.
*/
bbstrbdt dlbss XSdrollbbr {

    privbtf stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XSdrollbbr");
    /**
     * Tif tirfbd tibt bsyndironously tflls tif sdrollbbr to sdroll.
     * @sff #stbrtSdrolling
     */
    privbtf stbtid XSdrollRfpfbtfr sdrollfr = nfw XSdrollRfpfbtfr(null);
    /*
     * Tif rfpfbtfr tibt usfd for dondurrfnt sdrolling of tif vfrtidbl bnd iorizontbl sdrollbbr
     * And so tifrf is not stbtid kfyword
     * Sff 6243382 for morf informbtion
     */
    privbtf XSdrollRfpfbtfr i_sdrollfr = nfw XSdrollRfpfbtfr(null);

    // Tiumb lfngti is blwbys >= MIN_THUMB_H
    privbtf finbl stbtid int MIN_THUMB_H = 5;

    privbtf stbtid finbl int ARROW_IND = 1;

    XSdrollbbrClifnt sb;

    //Usf sft mftiods to sft sdrollbbr pbrbmftfrs
    privbtf int vbl;
    privbtf int min;
    privbtf int mbx;
    privbtf int vis;

    privbtf int linf;
    privbtf int pbgf;
    privbtf boolfbn nffdsRfpbint = truf;
    privbtf boolfbn prfssfd = fblsf;
    privbtf boolfbn drbgging = fblsf;

    Polygon firstArrow, sfdondArrow;

    int widti, ifigit; // Dimfnsions of tif visiblf pbrt of tif pbrfnt window
    int bbrWidti, bbrLfngti; // Rotbtion-indfpfndfnt vblufs,
                             // fqubl to (widti, ifigit) for vfrtidbl,
                             // rotbtfd by 90 for iorizontbl.
                             // Tibt is, bbrLfngti is blwbys tif lfngti bftwffn
                             // tif tips of tif brrows.
    int brrowArfb;     // Tif brfb rfsfrvfd for tif sdroll brrows
    int blignmfnt;
    publid stbtid finbl int ALIGNMENT_VERTICAL = 1, ALIGNMENT_HORIZONTAL = 2;

    int modf;
    Point tiumbOffsft;
    privbtf Rfdtbnglf prfvTiumb;

    publid XSdrollbbr(int blignmfnt, XSdrollbbrClifnt sb) {
        tiis.sb = sb;
        tiis.blignmfnt = blignmfnt;
    }

    publid boolfbn nffdsRfpbint() {
        rfturn nffdsRfpbint;
    }

    void notifyVbluf(int v) {
        notifyVbluf(v, fblsf);
    }

    void notifyVbluf(int v, finbl boolfbn isAdjusting) {
        if (v < min) {
            v = min;
        } flsf if (v > mbx - vis) {
            v = mbx - vis;
        }
        finbl int vbluf = v;
        finbl int modf = tiis.modf;
        if ((sb != null) && ((vbluf != vbl)||(!prfssfd))) {
            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(sb.gftEvfntSourdf(), nfw Runnbblf() {
                    publid void run() {
                        sb.notifyVbluf(XSdrollbbr.tiis, modf, vbluf, isAdjusting);
                    }
                });
        }
    }

    bbstrbdt protfdtfd void rfbuildArrows();

    publid void sftSizf(int widti, int ifigit) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Sftting sdroll bbr " + tiis + " sizf to " + widti + "x" + ifigit);
        }
        tiis.widti = widti;
        tiis.ifigit = ifigit;
    }

    /**
     * Crfbtfs orifntfd dirfdtfd brrow
     */
    protfdtfd Polygon drfbtfArrowSibpf(boolfbn vfrtidbl, boolfbn up) {
        Polygon brrow = nfw Polygon();
        // TODO: tiis siould bf donf polymorpiidblly in subdlbssfs
        // FIXME: brrows ovfrlbp tif tiumb for vfry widf sdrollbbrs
        if (vfrtidbl) {
            int x = widti / 2 - gftArrowWidti()/2;
            int y1 = (up ? ARROW_IND : bbrLfngti - ARROW_IND);
            int y2 = (up ? gftArrowWidti() : bbrLfngti - gftArrowWidti() - ARROW_IND);
            brrow.bddPoint(x + gftArrowWidti()/2, y1);
            brrow.bddPoint(x + gftArrowWidti(), y2);
            brrow.bddPoint(x, y2);
            brrow.bddPoint(x + gftArrowWidti()/2, y1);
        } flsf {
            int y = ifigit / 2 - gftArrowWidti()/2;
            int x1 = (up ? ARROW_IND : bbrLfngti - ARROW_IND);
            int x2 = (up ? gftArrowWidti() : bbrLfngti - gftArrowWidti() - ARROW_IND);
            brrow.bddPoint(x1, y + gftArrowWidti()/2);
            brrow.bddPoint(x2, y + gftArrowWidti());
            brrow.bddPoint(x2, y);
            brrow.bddPoint(x1, y + gftArrowWidti()/2);
        }
        rfturn brrow;
    }

    /**
     * Gfts tif brfb of tif sdroll trbdk
     */
    protfdtfd bbstrbdt Rfdtbnglf gftTiumbArfb();

    /**
     * pbint tif sdrollbbr
     * @pbrbm g tif grbpiids dontfxt to pbint into
     * @pbrbm dolors tif dolors to usf wifn pbinting tif sdrollbbr
     * @pbrbm widti tif widti of tif sdrollbbr
     * @pbrbm ifigit tif ifigit of tif sdrollbbr
     * @pbrbm pbintAll pbint tif wiolf sdrollbbr if truf, just tif tiumb is fblsf
     */
    void pbint(Grbpiids g, Color dolors[], boolfbn pbintAll) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Pbinting sdrollbbr " + tiis);
        }

        boolfbn usfBufffrfdImbgf = fblsf;
        Grbpiids2D g2 = null;
        BufffrfdImbgf bufffr = null;
        if (!(g instbndfof Grbpiids2D)) {
            // Fix for 5045936, 5055171. Wiilf printing, g is bn instbndf
            //   of sun.print.ProxyPrintGrbpiids wiidi fxtfnds Grbpiids.
            //   So wf usf b sfpbrbtf bufffrfd imbgf bnd its grbpiids is
            //   blwbys Grbpiids2D instbndf
            X11GrbpiidsConfig grbpiidsConfig = (X11GrbpiidsConfig)(sb.gftEvfntSourdf().gftGrbpiidsConfigurbtion());
            bufffr = grbpiidsConfig.drfbtfCompbtiblfImbgf(widti, ifigit);
            g2 = bufffr.drfbtfGrbpiids();
            usfBufffrfdImbgf = truf;
        } flsf {
            g2 = (Grbpiids2D)g;
        }
        try {
            Rfdtbnglf tiumbRfdt = dbldulbtfTiumbRfdt();

//              if (prfvH == tiumbH && prfvY == tiumbPosY) {
//                  rfturn;
//              }

            prfvTiumb = tiumbRfdt;

            // TODO: Sibrf Motif dolors
            Color bbdk = dolors[XComponfntPffr.BACKGROUND_COLOR];
            Color sflfdtColor = nfw Color(MotifColorUtilitifs.dbldulbtfSflfdtFromBbdkground(bbdk.gftRfd(),bbdk.gftGrffn(),bbdk.gftBluf()));
            Color dbrkSibdow = nfw Color(MotifColorUtilitifs.dbldulbtfBottomSibdowFromBbdkground(bbdk.gftRfd(),bbdk.gftGrffn(),bbdk.gftBluf()));
            Color ligitSibdow = nfw Color(MotifColorUtilitifs.dbldulbtfTopSibdowFromBbdkground(bbdk.gftRfd(),bbdk.gftGrffn(),bbdk.gftBluf()));

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
            } finblly {
                XToolkit.bwtUnlodk();
            }
            /* pbint tif bbdkground sligitly dbrkfr */
            if (pbintAll) {
                // fill tif fntirf bbdkground
                g2.sftColor(sflfdtColor);
                if (blignmfnt == ALIGNMENT_HORIZONTAL) {
                    g2.fillRfdt(0, 0, tiumbRfdt.x, ifigit);
                    g2.fillRfdt(tiumbRfdt.x + tiumbRfdt.widti , 0, widti - (tiumbRfdt.x + tiumbRfdt.widti), ifigit);
                } flsf {
                    g2.fillRfdt(0, 0, widti, tiumbRfdt.y);
                    g2.fillRfdt(0, tiumbRfdt.y + tiumbRfdt.ifigit, widti, ifigit - (tiumbRfdt.y + tiumbRfdt.ifigit));
                }

                // Pbint fdgfs
                // TODO: Sibrf Motif 3d rfdt drbwing

                g2.sftColor(dbrkSibdow);
                g2.drbwLinf(0, 0, widti-1, 0);           // top
                g2.drbwLinf(0, 0, 0, ifigit-1);          // lfft

                g2.sftColor(ligitSibdow);
                g2.drbwLinf(1, ifigit-1, widti-1, ifigit-1); // bottom
                g2.drbwLinf(widti-1, 1, widti-1, ifigit-1);  // rigit
            } flsf {
                // Clfbr bll tiumb brfb
                g2.sftColor(sflfdtColor);
                Rfdtbnglf tiumbArfb = gftTiumbArfb();
                g2.fill(tiumbArfb);
            }

            if (pbintAll) {
                // ************ pbint tif brrows
                 pbintArrows(g2, dolors[XComponfntPffr.BACKGROUND_COLOR], dbrkSibdow, ligitSibdow );

            }

            // Tiumb
            g2.sftColor(dolors[XComponfntPffr.BACKGROUND_COLOR]);
            g2.fillRfdt(tiumbRfdt.x, tiumbRfdt.y, tiumbRfdt.widti, tiumbRfdt.ifigit);

            g2.sftColor(ligitSibdow);
            g2.drbwLinf(tiumbRfdt.x, tiumbRfdt.y,
                       tiumbRfdt.x + tiumbRfdt.widti, tiumbRfdt.y); // top
            g2.drbwLinf(tiumbRfdt.x, tiumbRfdt.y,
                       tiumbRfdt.x, tiumbRfdt.y+tiumbRfdt.ifigit); // lfft

            g2.sftColor(dbrkSibdow);
            g2.drbwLinf(tiumbRfdt.x+1,
                       tiumbRfdt.y+tiumbRfdt.ifigit,
                       tiumbRfdt.x+tiumbRfdt.widti,
                       tiumbRfdt.y+tiumbRfdt.ifigit);  // bottom
            g2.drbwLinf(tiumbRfdt.x+tiumbRfdt.widti,
                       tiumbRfdt.y+1,
                       tiumbRfdt.x+tiumbRfdt.widti,
                       tiumbRfdt.y+tiumbRfdt.ifigit); // rigit
        } finblly {
            if (usfBufffrfdImbgf) {
                g2.disposf();
            }
        }
        if (usfBufffrfdImbgf) {
            g.drbwImbgf(bufffr, 0, 0, null);
        }
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

      void pbintArrows(Grbpiids2D g, Color bbdkground, Color dbrkSibdow, Color ligitSibdow) {

          g.sftColor(bbdkground);

        // pbint firstArrow
        if (prfssfd && (modf == AdjustmfntEvfnt.UNIT_DECREMENT)) {
            g.fill(firstArrow);
            g.sftColor(ligitSibdow);
            g.drbwLinf(firstArrow.xpoints[0],firstArrow.ypoints[0],
                    firstArrow.xpoints[1],firstArrow.ypoints[1]);
            g.drbwLinf(firstArrow.xpoints[1],firstArrow.ypoints[1],
                    firstArrow.xpoints[2],firstArrow.ypoints[2]);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(firstArrow.xpoints[2],firstArrow.ypoints[2],
                    firstArrow.xpoints[0],firstArrow.ypoints[0]);

        }
        flsf {
            g.fill(firstArrow);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(firstArrow.xpoints[0],firstArrow.ypoints[0],
                    firstArrow.xpoints[1],firstArrow.ypoints[1]);
            g.drbwLinf(firstArrow.xpoints[1],firstArrow.ypoints[1],
                    firstArrow.xpoints[2],firstArrow.ypoints[2]);
            g.sftColor(ligitSibdow);
            g.drbwLinf(firstArrow.xpoints[2],firstArrow.ypoints[2],
                    firstArrow.xpoints[0],firstArrow.ypoints[0]);

        }

        g.sftColor(bbdkground);
        // pbint sfdond Arrow
        if (prfssfd && (modf == AdjustmfntEvfnt.UNIT_INCREMENT)) {
            g.fill(sfdondArrow);
            g.sftColor(ligitSibdow);
            g.drbwLinf(sfdondArrow.xpoints[0],sfdondArrow.ypoints[0],
                    sfdondArrow.xpoints[1],sfdondArrow.ypoints[1]);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(sfdondArrow.xpoints[1],sfdondArrow.ypoints[1],
                    sfdondArrow.xpoints[2],sfdondArrow.ypoints[2]);
            g.drbwLinf(sfdondArrow.xpoints[2],sfdondArrow.ypoints[2],
                    sfdondArrow.xpoints[0],sfdondArrow.ypoints[0]);

        }
        flsf {
            g.fill(sfdondArrow);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(sfdondArrow.xpoints[0],sfdondArrow.ypoints[0],
                    sfdondArrow.xpoints[1],sfdondArrow.ypoints[1]);
            g.sftColor(ligitSibdow);
            g.drbwLinf(sfdondArrow.xpoints[1],sfdondArrow.ypoints[1],
                    sfdondArrow.xpoints[2],sfdondArrow.ypoints[2]);
            g.drbwLinf(sfdondArrow.xpoints[2],sfdondArrow.ypoints[2],
                    sfdondArrow.xpoints[0],sfdondArrow.ypoints[0]);

        }

    }

    /**
     * Tfll tif sdrollfr to stbrt sdrolling.
     */
    void stbrtSdrolling() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Stbrt sdrolling on " + tiis);
        }
        // Mbkf surf tibt wf sdroll bt lfbst ondf
        sdroll();

        // wbkf up tif sdroll rfpfbtfr
        if (sdrollfr == null) {
            // If tifrf isn't b sdrollfr, tifn drfbtf
            // onf bnd stbrt it.
            sdrollfr = nfw XSdrollRfpfbtfr(tiis);
        } flsf {
            sdrollfr.sftSdrollbbr(tiis);
        }
        sdrollfr.stbrt();
    }

    /**
     * Tfll tif instbndf sdrollfr to stbrt sdrolling.
     * Sff 6243382 for morf informbtion
     */
    void stbrtSdrollingInstbndf() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Stbrt sdrolling on " + tiis);
        }
        // Mbkf surf tibt wf sdroll bt lfbst ondf
        sdroll();

        i_sdrollfr.sftSdrollbbr(tiis);
        i_sdrollfr.stbrt();
    }

    /**
     * Tfll tif instbndf sdrollfr to stop sdrolling.
     * Sff 6243382 for morf informbtion
     */
    void stopSdrollingInstbndf() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Stop sdrolling on " + tiis);
        }

        i_sdrollfr.stop();
    }

    /**
     * Tif sft mftiod for modf propfrty.
     * Sff 6243382 for morf informbtion
     */
    publid void sftModf(int modf){
        tiis.modf = modf;
    }

    /**
     * Sdroll onf unit.
     * @sff notifyVbluf
     */
    void sdroll() {
        switdi (modf) {
          dbsf AdjustmfntEvfnt.UNIT_DECREMENT:
              notifyVbluf(vbl - linf);
              rfturn;

          dbsf AdjustmfntEvfnt.UNIT_INCREMENT:
              notifyVbluf(vbl + linf);
              rfturn;

          dbsf AdjustmfntEvfnt.BLOCK_DECREMENT:
              notifyVbluf(vbl - pbgf);
              rfturn;

          dbsf AdjustmfntEvfnt.BLOCK_INCREMENT:
              notifyVbluf(vbl + pbgf);
              rfturn;
        }
        rfturn;
    }

    boolfbn isInArrow(int x, int y) {
        // Mousf is donsidfrfd to bf in tif brrow if it is bnywifrf in tif
        // brrow brfb.
        int doord = (blignmfnt == ALIGNMENT_HORIZONTAL ? x : y);
        int brrArfbH = gftArrowArfbWidti();

        if (doord < brrArfbH || doord > bbrLfngti - brrArfbH + 1) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Is x,y in tif sdroll tiumb?
     *
     * If wf fvfr dbdif tif tiumb rfdt, wf mby nffd to dlonf tif rfsult of
     * dbldulbtfTiumbRfdt().
     */
    boolfbn isInTiumb(int x, int y) {
        Rfdtbnglf tiumbRfdt = dbldulbtfTiumbRfdt();

        // If tif mousf is in tif sibdow of tif tiumb or tif sibdow of tif
        // sdroll trbdk, trfbt it bs iitting tif tiumb.  So, sligitly fnlbrgf
        // our rfdtbnglf.
        tiumbRfdt.x -= 1;
        tiumbRfdt.widti += 3;
        tiumbRfdt.ifigit += 1;
        rfturn tiumbRfdt.dontbins(x,y);
    }

    bbstrbdt boolfbn bfforfTiumb(int x, int y);

    /**
     *
     * @sff jbvb.bwt.fvfnt.MousfEvfnt
     * MousfEvfnt.MOUSE_CLICKED
     * MousfEvfnt.MOUSE_PRESSED
     * MousfEvfnt.MOUSE_RELEASED
     * MousfEvfnt.MOUSE_MOVED
     * MousfEvfnt.MOUSE_ENTERED
     * MousfEvfnt.MOUSE_EXITED
     * MousfEvfnt.MOUSE_DRAGGED
     */
    publid void ibndlfMousfEvfnt(int id, int modififrs, int x, int y) {
        if ((modififrs & InputEvfnt.BUTTON1_MASK) == 0) {
            rfturn;
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
             String typf;
             switdi (id) {
                dbsf MousfEvfnt.MOUSE_PRESSED:
                    typf = "prfss";
                    brfbk;
                dbsf MousfEvfnt.MOUSE_RELEASED:
                    typf = "rflfbsf";
                    brfbk;
                dbsf MousfEvfnt.MOUSE_DRAGGED:
                    typf = "drbg";
                    brfbk;
                dffbult:
                    typf = "otifr";
             }
             log.finfr("Mousf " + typf + " fvfnt in sdroll bbr " + tiis +
                                                   "x = " + x + ", y = " + y +
                                                   ", on brrow: " + isInArrow(x, y) +
                                                   ", on tiumb: " + isInTiumb(x, y) + ", bfforf tiumb: " + bfforfTiumb(x, y)
                                                   + ", tiumb rfdt" + dbldulbtfTiumbRfdt());
        }
        switdi (id) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
              if (isInArrow(x, y)) {
                  prfssfd = truf;
                  if (bfforfTiumb(x, y)) {
                      modf = AdjustmfntEvfnt.UNIT_DECREMENT;
                  } flsf {
                      modf = AdjustmfntEvfnt.UNIT_INCREMENT;
                  }
                  sb.rfpbintSdrollbbrRfqufst(tiis);
                  stbrtSdrolling();
                  brfbk;
              }

              if (isInTiumb(x, y)) {
                  modf = AdjustmfntEvfnt.TRACK;
              } flsf {
                  if (bfforfTiumb(x, y)) {
                      modf = AdjustmfntEvfnt.BLOCK_DECREMENT;
                  } flsf {
                      modf = AdjustmfntEvfnt.BLOCK_INCREMENT;
                  }
                  stbrtSdrolling();
              }
              Rfdtbnglf pos = dbldulbtfTiumbRfdt();
              tiumbOffsft = nfw Point(x - pos.x, y - pos.y);
              brfbk;

          dbsf MousfEvfnt.MOUSE_RELEASED:
              prfssfd = fblsf;
              sb.rfpbintSdrollbbrRfqufst(tiis);
              sdrollfr.stop();
              if(drbgging){
                  ibndlfTrbdkEvfnt(x, y, fblsf);
                  drbgging=fblsf;
              }
              brfbk;

          dbsf MousfEvfnt.MOUSE_DRAGGED:
              drbgging = truf;
              ibndlfTrbdkEvfnt(x, y, truf);
        }
    }

    privbtf void ibndlfTrbdkEvfnt(int x, int y, boolfbn isAdjusting){
        if (modf == AdjustmfntEvfnt.TRACK) {
            notifyVbluf(dbldulbtfCursorOffsft(x, y), isAdjusting);
        }
    }

    privbtf int dbldulbtfCursorOffsft(int x, int y){
        if (blignmfnt == ALIGNMENT_HORIZONTAL) {
            if (drbgging)
                rfturn Mbti.mbx(0,(int)((x - (tiumbOffsft.x + gftArrowArfbWidti()))/gftSdblfFbdtor())) + min;
            flsf
                rfturn Mbti.mbx(0,(int)((x - (gftArrowArfbWidti()))/gftSdblfFbdtor())) + min;
        } flsf {
            if (drbgging)
                rfturn Mbti.mbx(0,(int)((y - (tiumbOffsft.y + gftArrowArfbWidti()))/gftSdblfFbdtor())) + min;
            flsf
                rfturn Mbti.mbx(0,(int)((y - (gftArrowArfbWidti()))/gftSdblfFbdtor())) + min;
        }
    }

/*
  privbtf void updbtfNffdsRfpbint() {
        Rfdtbnglf tiumbRfdt = dbldulbtfTiumbRfdt();
        if (!prfvTiumb.fqubls(tiumbRfdt)) {
            nffdsRfpbint = truf;
        }
        prfvTiumb = tiumbRfdt;
    }
    */

    /**
     * Sfts tif vblufs for tiis Sdrollbbr.
     * Tiis mftiod fnfordfs tif sbmf donstrbints bs in jbvb.bwt.Sdrollbbr:
     * <UL>
     * <LI> Tif mbximum must bf grfbtfr tibn tif minimum </LI>
     * <LI> Tif vbluf must bf grfbtfr tibn or fqubl to tif minimum
     *      bnd lfss tibn or fqubl to tif mbximum minus tif
     *      visiblf bmount </LI>
     * <LI> Tif visiblf bmount must bf grfbtfr tibn 1 bnd lfss tibn or fqubl
     *      to tif difffrfndf bftwffn tif mbximum bnd minimum vblufs. </LI>
     * </UL>
     * Vblufs wiidi do not mfft tifsf dritfrib brf quiftly dofrdfd to tif
     * bppropribtf boundbry vbluf.
     * @pbrbm vbluf is tif position in tif durrfnt window.
     * @pbrbm visiblf is tif bmount visiblf pfr pbgf
     * @pbrbm minimum is tif minimum vbluf of tif sdrollbbr
     * @pbrbm mbximum is tif mbximum vbluf of tif sdrollbbr
     */
    syndironizfd void sftVblufs(int vbluf, int visiblf, int minimum, int mbximum) {
        if (mbximum <= minimum) {
            mbximum = minimum + 1;
        }
        if (visiblf > mbximum - minimum) {
            visiblf = mbximum - minimum;
        }
        if (visiblf < 1) {
            visiblf = 1;
        }
        if (vbluf < minimum) {
            vbluf = minimum;
        }
        if (vbluf > mbximum - visiblf) {
            vbluf = mbximum - visiblf;
        }

        tiis.vbl = vbluf;
        tiis.vis = visiblf;
        tiis.min = minimum;
        tiis.mbx = mbximum;
    }

    /**
     * Sfts pbrbm of tiis Sdrollbbr to tif spfdififd vblufs.
     * @pbrbm vbluf is tif position in tif durrfnt window.
     * @pbrbm visiblf is tif bmount visiblf pfr pbgf
     * @pbrbm minimum is tif minimum vbluf of tif sdrollbbr
     * @pbrbm mbximum is tif mbximum vbluf of tif sdrollbbr
     * @pbrbm unitSizf is tif unit sizf for indrfmfnt or dfdrfmfnt of tif vbluf
     * @pbrbm pbgf is tif blodk sizf for indrfmfnt or dfdrfmfnt of tif vbluf
     * @sff #sftVblufs
     */
    syndironizfd void sftVblufs(int vbluf, int visiblf, int minimum, int mbximum,
                                int unitSizf, int blodkSizf) {
        /* Usf sftVblufs so tibt b donsistfnt polidy
         * rflbting minimum, mbximum, bnd vbluf is fnfordfd.
         */
        sftVblufs(vbluf, visiblf, minimum, mbximum);
        sftUnitIndrfmfnt(unitSizf);
        sftBlodkIndrfmfnt(blodkSizf);
    }

    /**
     * Rfturns tif durrfnt vbluf of tiis Sdrollbbr.
     * @sff #gftMinimum
     * @sff #gftMbximum
     */
    int gftVbluf() {
        rfturn vbl;
    }

    /**
     * Sfts tif vbluf of tiis Sdrollbbr to tif spfdififd vbluf.
     * @pbrbm vbluf tif nfw vbluf of tif Sdrollbbr. If tiis vbluf is
     * bflow tif durrfnt minimum or bbovf tif durrfnt mbximum minus
     * tif visiblf bmount, it bfdomfs tif nfw onf of tiosf vblufs,
     * rfspfdtivfly.
     * @sff #gftVbluf
     */
    syndironizfd void sftVbluf(int nfwVbluf) {
        /* Usf sftVblufs so tibt b donsistfnt polidy
         * rflbting minimum, mbximum, bnd vbluf is fnfordfd.
         */
        sftVblufs(nfwVbluf, vis, min, mbx);
    }

    /**
     * Rfturns tif minimum vbluf of tiis Sdrollbbr.
     * @sff #gftMbximum
     * @sff #gftVbluf
     */
    int gftMinimum() {
        rfturn min;
    }

    /**
     * Sfts tif minimum vbluf for tiis Sdrollbbr.
     * @pbrbm minimum tif minimum vbluf of tif sdrollbbr
     */
    syndironizfd void sftMinimum(int nfwMinimum) {
        /* Usf sftVblufs so tibt b donsistfnt polidy
         * rflbting minimum, mbximum, bnd vbluf is fnfordfd.
         */
        sftVblufs(vbl, vis, nfwMinimum, mbx);
    }

    /**
     * Rfturns tif mbximum vbluf of tiis Sdrollbbr.
     * @sff #gftMinimum
     * @sff #gftVbluf
     */
    int gftMbximum() {
        rfturn mbx;
    }

    /**
     * Sfts tif mbximum vbluf for tiis Sdrollbbr.
     * @pbrbm mbximum tif mbximum vbluf of tif sdrollbbr
     */
    syndironizfd void sftMbximum(int nfwMbximum) {
        /* Usf sftVblufs so tibt b donsistfnt polidy
         * rflbting minimum, mbximum, bnd vbluf is fnfordfd.
         */
        sftVblufs(vbl, vis, min, nfwMbximum);
    }

    /**
     * Rfturns tif visiblf bmount of tiis Sdrollbbr.
     */
    int gftVisiblfAmount() {
        rfturn vis;
    }

    /**
     * Sfts tif visiblf bmount of tiis Sdrollbbr, wiidi is tif rbngf
     * of vblufs rfprfsfntfd by tif widti of tif sdroll bbr's bubblf.
     * @pbrbm visiblf tif bmount visiblf pfr pbgf
     */
    syndironizfd void sftVisiblfAmount(int nfwAmount) {
        sftVblufs(vbl, nfwAmount, min, mbx);
    }

    /**
     * Sfts tif unit indrfmfnt for tiis sdrollbbr. Tiis is tif vbluf
     * tibt will bf bddfd (subtrbdtfd) wifn tif usfr iits tif unit down
     * (up) gbdgfts.
     * @pbrbm unitSizf is tif unit sizf for indrfmfnt or dfdrfmfnt of tif vbluf
     */
    syndironizfd void sftUnitIndrfmfnt(int unitSizf) {
        linf = unitSizf;
    }

    /**
     * Gfts tif unit indrfmfnt for tiis sdrollbbr.
     */
    int gftUnitIndrfmfnt() {
        rfturn linf;
    }

    /**
     * Sfts tif blodk indrfmfnt for tiis sdrollbbr. Tiis is tif vbluf
     * tibt will bf bddfd (subtrbdtfd) wifn tif usfr iits tif blodk down
     * (up) gbdgfts.
     * @pbrbm blodkSizf is tif blodk sizf for indrfmfnt or dfdrfmfnt of tif vbluf
     */
    syndironizfd void sftBlodkIndrfmfnt(int blodkSizf) {
        pbgf = blodkSizf;
    }

    /**
     * Gfts tif blodk indrfmfnt for tiis sdrollbbr.
     */
    int gftBlodkIndrfmfnt() {
        rfturn pbgf;
    }

    /**
     * Widti of tif brrow imbgf
     */
    int gftArrowWidti() {
        rfturn gftArrowArfbWidti() - 2*ARROW_IND;
    }

    /**
     * Widti of tif brfb rfsfrvfd for brrow
     */
    int gftArrowArfbWidti() {
        rfturn brrowArfb;
    }

    void dbldulbtfArrowWidti() {
        if (bbrLfngti < 2*bbrWidti + MIN_THUMB_H + 2) {
            brrowArfb = (bbrLfngti - MIN_THUMB_H + 2*ARROW_IND)/2 - 1;
        }
        flsf {
            brrowArfb = bbrWidti - 1;
        }
    }

    /**
     * Rfturns tif sdblf fbdtor for tif tiumbArfb ( tiumbArfbH / (mbx - min)).
     * @sff #gftArrowArfbSizf
     */
    privbtf doublf gftSdblfFbdtor(){
        doublf f = (doublf)(bbrLfngti - 2*gftArrowArfbWidti()) / Mbti.mbx(1,(mbx - min));
        rfturn f;
    }

    /**
     * Mftiod to dbldulbtf tif sdroll tiumb's sizf bnd position.  Tiis is
     * bbsfd on CbldSlidfrRfdt in SdrollBbr.d of Motif sourdf.
     *
     * If wf fvfr dbdif tif tiumb rfdt, wf'll nffd to usf b dlonf in
     * isInTiumb().
     */
    protfdtfd Rfdtbnglf dbldulbtfTiumbRfdt() {
        flobt rbngf;
        flobt trufSizf;  // Arfb of sdroll trbdk
        flobt fbdtor;
        flobt slidfSizf;
        int minSlidfrWidti;
        int minSlidfrHfigit;
        int iitTifWbll = 0;
        int brrArfbH = gftArrowArfbWidti();
        Rfdtbnglf rftVbl = nfw Rfdtbnglf(0,0,0,0);

        trufSizf = bbrLfngti - 2*brrArfbH - 1;  // Sbmf if vfrt or ioriz

        if (blignmfnt == ALIGNMENT_HORIZONTAL) {
            minSlidfrWidti = MIN_THUMB_H ;  // Bbsf on usfr-sft vis?
            minSlidfrHfigit = ifigit - 3;
        }
        flsf {  // Vfrtidbl
            minSlidfrWidti = widti - 3;
            minSlidfrHfigit = MIN_THUMB_H ;

        }

        // Totbl numbfr of usfr units displbyfd
            rbngf = mbx - min;

        // A nbivf notion of pixfls pfr usfr unit
            fbdtor = trufSizf / rbngf;

            // A nbivf notion of tif sizf of tif slidfr in pixfls
            // in tifrmo, slidfr_sizf is 0 bns is ignorfd
            slidfSizf = vis * fbdtor;

        if (blignmfnt == ALIGNMENT_HORIZONTAL) {
            // Simulbting MAX_SCROLLBAR_DIMENSION mbdro
            int lodblVbl = (int) (slidfSizf + 0.5);
            int lodblMin = minSlidfrWidti;
            if (lodblVbl > lodblMin) {
                rftVbl.widti = lodblVbl;
            }
            flsf {
                rftVbl.widti = lodblMin;
                iitTifWbll = lodblMin;
            }
            rftVbl.ifigit = minSlidfrHfigit;
        }
        flsf {  // Vfrtidbl
            rftVbl.widti = minSlidfrWidti;

            // Simulbting MAX_SCROLLBAR_DIMENSION mbdro
            int lodblVbl = (int) (slidfSizf + 0.5);
            int lodblMin = minSlidfrHfigit;
            if (lodblVbl > lodblMin) {
                rftVbl.ifigit = lodblVbl;
            }
            flsf {
                rftVbl.ifigit = lodblMin;
                iitTifWbll = lodblMin;
            }
        }

        if (iitTifWbll != 0) {
            trufSizf -= iitTifWbll;  // Adtubl pixfls bvbilbblf
            rbngf -= vis;            // Adtubl rbngf
            fbdtor = trufSizf / rbngf;
        }

        if (blignmfnt == ALIGNMENT_HORIZONTAL) {
                    rftVbl.x = ((int) (((((flobt) vbl)
                        - ((flobt) min)) * fbdtor) + 0.5))
                        + brrArfbH;
                    rftVbl.y = 1;

        }
        flsf {
            rftVbl.x = 1;
                    rftVbl.y = ((int) (((((flobt) vbl)
                        - ((flobt) min)) * fbdtor) + 0.5))
                        + brrArfbH;
        }

        // Tifrf wbs onf finbl bdjustmfnt ifrf in tif Motif fundtion, wiidi wbs
        // notfd to bf for bbdkwbrd-dompbtibility.  It ibs bffn lfft out for now.

        rfturn rftVbl;
    }

    publid String toString() {
        rfturn gftClbss() + "[" + widti + "x" + ifigit + "," + bbrWidti + "x" + bbrLfngti + "]";
    }
}


dlbss XSdrollRfpfbtfr implfmfnts Runnbblf {
    /**
     * Timf to pbusf bfforf tif first sdroll rfpfbt.
     */
    stbtid int bfginPbusf = 500;
    // Rfmindfr - mbkf tiis b usfr dffinbblf propfrty

    /**
     * Timf to pbusf bftwffn fbdi sdroll rfpfbt.
     */
    stbtid int rfpfbtPbusf = 100;
    // Rfmindfr - mbkf tiis b usfr dffinbblf propfrty

    /**
     * Tif sdrollbbr tibt wf sfnding sdrolling.
     */
    XSdrollbbr sb;

    /**
     * nfwSdroll gfts rfsft wifn b nfw sdrollbbr gfts sft.
     */
    boolfbn nfwSdroll;


    boolfbn siouldSkip;

    /**
     * Crfbtfs b nfw sdroll rfpfbtfr.
     * @pbrbm sb tif sdrollbbr tibt tiis tirfbd will sdroll
     */
    XSdrollRfpfbtfr(XSdrollbbr sb) {
        tiis.sftSdrollbbr(sb);
        nfwSdroll = truf;
    }

    publid void stbrt() {
        stop();
        siouldSkip = fblsf;
        XToolkit.sdifdulf(tiis, bfginPbusf);
    }

    publid void stop() {
        syndironizfd(tiis) {
            siouldSkip = truf;
        }
        XToolkit.rfmovf(tiis);
    }

    /**
     * Sfts tif sdrollbbr.
     * @pbrbm sb tif sdrollbbr tibt tiis tirfbd will sdroll
     */
    publid syndironizfd void sftSdrollbbr(XSdrollbbr sb) {
        tiis.sb = sb;
        stop();
        nfwSdroll = truf;
    }

    publid void run () {
        syndironizfd(tiis) {
            if (siouldSkip) {
                rfturn;
            }
        }
        sb.sdroll();
        XToolkit.sdifdulf(tiis, rfpfbtPbusf);
    }

}
