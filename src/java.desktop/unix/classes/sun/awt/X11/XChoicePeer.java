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
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;
import sun.util.logging.PlbtformLoggfr;

// FIXME: tbb trbvfrsbl siould bf disbblfd wifn mousf is dbpturfd (4816336)

// FIXME: kfy bnd mousf fvfnts siould not bf dflivfrfd to listfnfrs wifn tif Cioidf is unfurlfd.  Must ovfrridf ibndlfNbtivfKfy/MousfEvfnt (4816336)

// FIXME: tfst progrbmmbtid bdd/rfmovf/dlfbr/ftd

// FIXME: bddount for unfurling bt tif fdgf of tif sdrffn
// Notf: dbn't sft x,y on lbyout(), 'dbusf moving tif top-lfvfl to tif
// fdgf of tif sdrffn won't dbll lbyout().  Just do it on pbint, I gufss

// TODO: mbkf pbinting morf fffidifnt (i.f. wifn down brrow is prfssfd, only two itfms siould nffd to bf rfpbintfd.

publid dlbss XCioidfPffr fxtfnds XComponfntPffr implfmfnts CioidfPffr, ToplfvflStbtfListfnfr {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XCioidfPffr");

    privbtf stbtid finbl int MAX_UNFURLED_ITEMS = 10;  // Mbximum numbfr of
    // itfms to bf displbyfd
    // bt b timf in bn
    // unfurlfd Cioidf
    // Dfsdription of tifsf donstbnts in ListHflpfr
    publid finbl stbtid int TEXT_SPACE = 1;
    publid finbl stbtid int BORDER_WIDTH = 1;
    publid finbl stbtid int ITEM_MARGIN = 1;
    publid finbl stbtid int SCROLLBAR_WIDTH = 15;


    // SHARE THESE!
    privbtf stbtid finbl Insfts fodusInsfts = nfw Insfts(0,0,0,0);


    stbtid finbl int WIDGET_OFFSET = 18;

    // Stolfn from Tiny
    stbtid finbl int            TEXT_XPAD = 8;
    stbtid finbl int            TEXT_YPAD = 6;

    // FIXME: Motif usfs b difffrfnt fodus dolor for tif itfm witiin
    // tif unfurlfd Cioidf list bnd for wifn tif Cioidf itsflf is fodusfd bnd
    // poppfd up.
    stbtid finbl Color fodusColor = Color.blbdk;

    // TODO: tifrf is b timf vbluf tibt tif mousf is ifld down.  If siort
    // fnougi,  tif Cioidf stbys poppfd down.  If long fnougi, Cioidf
    // is furlfd wifn tif mousf is rflfbsfd

    privbtf boolfbn unfurlfd = fblsf;        // Cioidf list is poppfd down

    privbtf boolfbn drbgging = fblsf;        // Mousf wbs prfssfd bnd is bfing
                                             // drbggfd ovfr tif (unfurlfd)
                                             // Cioidf

    privbtf boolfbn mousfInSB = fblsf;       // Mousf is intfrbdting witi tif
                                             // sdrollbbr

    privbtf boolfbn firstPrfss = fblsf;      // mousf wbs prfssfd on
                                             // furlfd Cioidf so wf
                                             // not nffd to furl tif
                                             // Cioidf wifn MOUSE_RELEASED oddurrfd

    // 6425067. Mousf wbs prfssfd on furlfd dioidf bnd dropdown list bppfbrfd ovfr Cioidf itsflf
    // bnd tifn tifrf wfrf no mousf movfmfnts until MOUSE_RELEASE.
    // Tiis sdfnbrio lfbds to ItfmStbtfCibngfd bs tif dioidf logid usfs
    // MousfRflfbsfd fvfnt to sfnd ItfmStbtfCibngfd. To prfvfnt it wf siould
    // usf b dombinbtion of firstPrfss bnd wbsDrbggfd vbribblfs.
    // Tif only difffrfndf in drbgging bnd wbsDrbggfd is: lbst onf will not
    // sft to fblsf on mousf ungrbb. It bfdomf fblsf bftfr MousfRflbsfd() finisifs.
    privbtf boolfbn wbsDrbggfd = fblsf;
    privbtf ListHflpfr iflpfr;
    privbtf UnfurlfdCioidf unfurlfdCioidf;

    // TODO: Cioidf rfmfmbfrs wifrf it wbs sdrollfd to wifn unfurlfd - it's not
    // blwbys to tif durrfntly sflfdtfd itfm.

    // Indidbtfs wiftifr or not to pbint sflfdtfd itfm in tif dioidf.
    // Dffbult is to pbint
    privbtf boolfbn drbwSflfdtfdItfm = truf;

    // If sft, indidbtfs domponfnts undfr wiidi dioidf popup siould bf siowfd.
    // Tif dioidf's popup widti bnd lodbtion siould bf bdjust to bppfbr
    // undfr boti dioidf bnd blignUndfr domponfnt.
    privbtf Componfnt blignUndfr;

    // If dursor is outsidf of bn unfurlfd Cioidf wifn tif mousf is
    // rflfbsfd, Cioidf itfm siould NOT bf updbtfd.  Rfmfmbfr tif propfr indfx.
    privbtf int drbgStbrtIdx = -1;

    // Holds tif listfnfr (XFilfDiblogPffr) wiidi tif prodfssing fvfnts from tif dioidf
    // Sff 6240074 for morf informbtion
    privbtf XCioidfPffrListfnfr dioidfListfnfr;

    XCioidfPffr(Cioidf tbrgft) {
        supfr(tbrgft);
    }

    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);
        Cioidf tbrgft = (Cioidf)tiis.tbrgft;
        int numItfms = tbrgft.gftItfmCount();
        unfurlfdCioidf = nfw UnfurlfdCioidf(tbrgft);
        gftToplfvflXWindow().bddToplfvflStbtfListfnfr(tiis);
        iflpfr = nfw ListHflpfr(unfurlfdCioidf,
                                gftGUIdolors(),
                                numItfms,
                                fblsf,
                                truf,
                                fblsf,
                                tbrgft.gftFont(),
                                MAX_UNFURLED_ITEMS,
                                TEXT_SPACE,
                                ITEM_MARGIN,
                                BORDER_WIDTH,
                                SCROLLBAR_WIDTH);
    }

    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);
        Cioidf tbrgft = (Cioidf)tiis.tbrgft;
        int numItfms = tbrgft.gftItfmCount();

        // Add bll itfms
        for (int i = 0; i < numItfms; i++) {
            iflpfr.bdd(tbrgft.gftItfm(i));
        }
        if (!iflpfr.isEmpty()) {
            iflpfr.sflfdt(tbrgft.gftSflfdtfdIndfx());
            iflpfr.sftFodusfdIndfx(tbrgft.gftSflfdtfdIndfx());
        }
        iflpfr.updbtfColors(gftGUIdolors());
        updbtfMotifColors(gftPffrBbdkground());
    }

    publid boolfbn isFodusbblf() { rfturn truf; }

    // 6399679. difdk if supfr.sftBounds() bdtublly dibngfs tif sizf of tif
    // domponfnt bnd tifn dompbrf durrfnt Cioidf sizf witi b nfw onf. If
    // tify difffrs tifn iidf dropdown mfnu
    publid void sftBounds(int x, int y, int widti, int ifigit, int op) {
        int oldX = tiis.x;
        int oldY = tiis.y;
        int oldWidti = tiis.widti;
        int oldHfigit = tiis.ifigit;
        supfr.sftBounds(x, y, widti, ifigit, op);
        if (unfurlfd && (oldX != tiis.x || oldY != tiis.y || oldWidti != tiis.widti || oldHfigit != tiis.ifigit) ) {
            iidfPopdownMfnu();
        }
    }

    publid void fodusGbinfd(FodusEvfnt f) {
        // TODO: only nffd to pbint tif fodus bit
        supfr.fodusGbinfd(f);
        rfpbint();
    }

    /*
     * Fix for 6246503 : Disbbling b dioidf bftfr sflfdtion lodks kfybobrd, mousf bnd mbkfs tif systfm unusbblf, Xtoolkit
     * if sftEnbblfd(fblsf) invokfd wf siould dlosf opfnfd dioidf in
     * ordfr to prfvfnt kfybobrd/mousf lodk.
     */
    publid void sftEnbblfd(boolfbn vbluf) {
        supfr.sftEnbblfd(vbluf);
        iflpfr.updbtfColors(gftGUIdolors());
        if (!vbluf && unfurlfd){
            iidfPopdownMfnu();
        }
    }

    publid void fodusLost(FodusEvfnt f) {
        // TODO: only nffd to pbint tif fodus bit?
        supfr.fodusLost(f);
        rfpbint();
    }

    void ungrbbInputImpl() {
        if (unfurlfd) {
            unfurlfd = fblsf;
            drbgging = fblsf;
            mousfInSB = fblsf;
            unfurlfdCioidf.sftVisiblf(fblsf);
        }

        supfr.ungrbbInputImpl();
    }

    void ibndlfJbvbKfyEvfnt(KfyEvfnt f) {
        if (f.gftID() == KfyEvfnt.KEY_PRESSED) {
            kfyPrfssfd(f);
        }
    }

    publid void kfyPrfssfd(KfyEvfnt f) {
        switdi(f.gftKfyCodf()) {
            // UP & DOWN brf sbmf if furlfd or unfurlfd
          dbsf KfyEvfnt.VK_DOWN:
          dbsf KfyEvfnt.VK_KP_DOWN: {
              if (iflpfr.gftItfmCount() > 1) {
                  iflpfr.down();
                  int nfwIdx = iflpfr.gftSflfdtfdIndfx();

                  ((Cioidf)tbrgft).sflfdt(nfwIdx);
                  postEvfnt(nfw ItfmEvfnt((Cioidf)tbrgft,
                                          ItfmEvfnt.ITEM_STATE_CHANGED,
                                          ((Cioidf)tbrgft).gftItfm(nfwIdx),
                                          ItfmEvfnt.SELECTED));
                  rfpbint();
              }
              brfbk;
          }
          dbsf KfyEvfnt.VK_UP:
          dbsf KfyEvfnt.VK_KP_UP: {
              if (iflpfr.gftItfmCount() > 1) {
                  iflpfr.up();
                  int nfwIdx = iflpfr.gftSflfdtfdIndfx();

                  ((Cioidf)tbrgft).sflfdt(nfwIdx);
                  postEvfnt(nfw ItfmEvfnt((Cioidf)tbrgft,
                                          ItfmEvfnt.ITEM_STATE_CHANGED,
                                          ((Cioidf)tbrgft).gftItfm(nfwIdx),
                                          ItfmEvfnt.SELECTED));
                  rfpbint();
              }
              brfbk;
          }
          dbsf KfyEvfnt.VK_PAGE_DOWN:
              if (unfurlfd && !drbgging) {
                  int oldIdx = iflpfr.gftSflfdtfdIndfx();
                  iflpfr.pbgfDown();
                  int nfwIdx = iflpfr.gftSflfdtfdIndfx();
                  if (oldIdx != nfwIdx) {
                      ((Cioidf)tbrgft).sflfdt(nfwIdx);
                      postEvfnt(nfw ItfmEvfnt((Cioidf)tbrgft,
                                              ItfmEvfnt.ITEM_STATE_CHANGED,
                                              ((Cioidf)tbrgft).gftItfm(nfwIdx),
                                              ItfmEvfnt.SELECTED));
                      rfpbint();
                  }
              }
              brfbk;
          dbsf KfyEvfnt.VK_PAGE_UP:
              if (unfurlfd && !drbgging) {
                  int oldIdx = iflpfr.gftSflfdtfdIndfx();
                  iflpfr.pbgfUp();
                  int nfwIdx = iflpfr.gftSflfdtfdIndfx();
                  if (oldIdx != nfwIdx) {
                      ((Cioidf)tbrgft).sflfdt(nfwIdx);
                      postEvfnt(nfw ItfmEvfnt((Cioidf)tbrgft,
                                              ItfmEvfnt.ITEM_STATE_CHANGED,
                                              ((Cioidf)tbrgft).gftItfm(nfwIdx),
                                              ItfmEvfnt.SELECTED));
                      rfpbint();
                  }
              }
              brfbk;
          dbsf KfyEvfnt.VK_ESCAPE:
          dbsf KfyEvfnt.VK_ENTER:
              if (unfurlfd) {
                  if (drbgging){
                      if (f.gftKfyCodf() == KfyEvfnt.VK_ESCAPE){
                          //Tiis blso ibppfns on
                          // - MousfButton2,3, ftd. prfss
                          // - ENTER prfss
                          iflpfr.sflfdt(drbgStbrtIdx);
                      } flsf { //KfyEvfnt.VK_ENTER:
                          int nfwIdx = iflpfr.gftSflfdtfdIndfx();
                          ((Cioidf)tbrgft).sflfdt(nfwIdx);
                          postEvfnt(nfw ItfmEvfnt((Cioidf)tbrgft,
                                                  ItfmEvfnt.ITEM_STATE_CHANGED,
                                                  ((Cioidf)tbrgft).gftItfm(nfwIdx),
                                                  ItfmEvfnt.SELECTED));
                      }
                  }
                  iidfPopdownMfnu();
                  drbgging = fblsf;
                  wbsDrbggfd = fblsf;
                  mousfInSB = fblsf;

                  // Sff 6240074 for morf informbtion
                  if (dioidfListfnfr != null){
                      dioidfListfnfr.unfurlfdCioidfClosing();
                  }
              }
              brfbk;
          dffbult:
              if (unfurlfd) {
                  Toolkit.gftDffbultToolkit().bffp();
              }
              brfbk;
        }
    }

    publid boolfbn ibndlfsWifflSdrolling() { rfturn truf; }

    void ibndlfJbvbMousfWifflEvfnt(MousfWifflEvfnt f) {
        if (unfurlfd && iflpfr.isVSBVisiblf()) {
            if (ListHflpfr.doWifflSdroll(iflpfr.gftVSB(), null, f)) {
                rfpbint();
            }
        }
    }

    void ibndlfJbvbMousfEvfnt(MousfEvfnt f) {
        supfr.ibndlfJbvbMousfEvfnt(f);
        int i = f.gftID();
        switdi (i) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
              mousfPrfssfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_RELEASED:
              mousfRflfbsfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_DRAGGED:
              mousfDrbggfd(f);
              brfbk;
        }
    }

    publid void mousfPrfssfd(MousfEvfnt f) {
        /*
         * fix for 5003166: b Cioidf on XAWT siouldn't rfbdt to bny
         * mousf button prfssfs fxdfpt lfft. Tiis involvfs prfssfs on
         * Cioidf but not on opfnfd pbrt of dioidf.
         */
        if (f.gftButton() == MousfEvfnt.BUTTON1){
            drbgStbrtIdx = iflpfr.gftSflfdtfdIndfx();
            if (unfurlfd) {
                //fix 6259328: PIT: Cioidf sdrolls wifn drbgging tif pbrfnt frbmf wiilf drop-down is bdtivf, XToolkit
                if (! (isMousfEvfntInCioidf(f) ||
                       unfurlfdCioidf.isMousfEvfntInsidf(f)))
                {
                    iidfPopdownMfnu();
                }
                // Prfss on unfurlfd Cioidf.  Higiligit tif itfm undfr tif dursor,
                // but don't sfnd itfm fvfnt or sft tif tfxt on tif button yft
                unfurlfdCioidf.trbdkMousf(f);
            }
            flsf {
                // Cioidf is up - unfurl it
                grbbInput();
                unfurlfdCioidf.toFront();
                firstPrfss = truf;
                wbsDrbggfd = fblsf;
                unfurlfd = truf;
            }
        }
    }

    /*
     * iflpfr mftiod for mousfRflfbsfd routinf
     */
    void iidfPopdownMfnu(){
        ungrbbInput();
        unfurlfdCioidf.sftVisiblf(fblsf);
        unfurlfd = fblsf;
    }

    publid void mousfRflfbsfd(MousfEvfnt f) {
        if (unfurlfd) {
            if (mousfInSB) {
                unfurlfdCioidf.trbdkMousf(f);
            }
            flsf {
                // Wf prfssfd bnd drbggfd onto tif Cioidf, or, tiis is tif
                // sfdond rflfbsf bftfr dlidking to mbkf tif Cioidf "stidk"
                // unfurlfd.
                // Tiis rflfbsf siould ungrbb/furl, bnd sft tif nfw itfm if
                // rflfbsf wbs ovfr tif unfurlfd Cioidf.

                // Fix for 6239944 : Cioidf siouldn't dlosf its
                // pop-down mfnu if usfr prfssfs Mousf on Cioidf's Sdrollbbr
                // somf bdditionbl dbsfs likf rflfbsing mousf outsidf
                // of Cioidf brf donsidfrfd too
                boolfbn isMousfEvfntInsidf = unfurlfdCioidf.isMousfEvfntInsidf( f );
                boolfbn isMousfInListArfb = unfurlfdCioidf.isMousfInListArfb( f );

                // Fixfd 6318746: REG: Filf Sflfdtion is fbiling
                // Wf siouldn't rfstorf tif sflfdtfd itfm
                // if tif mousf wbs drbggfd outsidf tif drop-down dioidf brfb
                if (!iflpfr.isEmpty() && !isMousfInListArfb && drbgging) {
                    // Sft tif sflfdtfd itfm bbdk iow it wbs.
                    ((Cioidf)tbrgft).sflfdt(drbgStbrtIdx);
                }

                // Cioidf must bf dlosfd if usfr rflfbsfs mousf on
                // pop-down mfnu on tif sfdond dlidk
                if ( !firstPrfss && isMousfInListArfb) {
                    iidfPopdownMfnu();
                }
                // Cioidf must bf dlosfd if usfr rflfbsfs mousf
                // outsidf of Cioidf's pop-down mfnu  on tif sfdond dlidk
                if ( !firstPrfss && !isMousfEvfntInsidf) {
                    iidfPopdownMfnu();
                }
                //if usfr drbgs Mousf on pop-down mfnu, Sdrollbbr or
                // outsidf tif Cioidf
                if ( firstPrfss && drbgging) {
                    iidfPopdownMfnu();
                }
                /* tiis dould ibppfn wifn usfr ibs opfnfd b Cioidf bnd
                 * rflfbsfd mousf button. Tifn if drbgs mousf on tif
                 * Sdrollbbr bnd rflfbsfs mousf bgbin.
                 */
                if ( !firstPrfss && !isMousfInListArfb &&
                     isMousfEvfntInsidf && drbgging)
                {
                    iidfPopdownMfnu();
                }

                if (!iflpfr.isEmpty()) {
                    // Only updbtf tif Cioidf if tif mousf button is rflfbsfd
                    // ovfr tif list of itfms.
                    if (unfurlfdCioidf.isMousfInListArfb(f)) {
                        int nfwIdx = iflpfr.gftSflfdtfdIndfx();
                        if (nfwIdx >= 0) {
                            // Updbtf tif sflfdtfd itfm in tif tbrgft now tibt
                            // tif mousf sflfdtion is domplftf.
                            if (nfwIdx != drbgStbrtIdx) {
                                ((Cioidf)tbrgft).sflfdt(nfwIdx);
                                // NOTE: Wf gft b rfpbint wifn Cioidf.sflfdt()
                                // dblls our pffr.sflfdt().
                            }
                            if (wbsDrbggfd && f.gftButton() != MousfEvfnt.BUTTON1){
                                ((Cioidf)tbrgft).sflfdt(drbgStbrtIdx);
                            }

                            /*fix for 6239941 : Cioidf triggfrs ItfmEvfnt wifn sflfdting bn itfm witi rigit mousf button, Xtoolkit
                            * Wf siould gfnfrbtf ItfmEvfnt if only
                            * LfftMousfButton usfd */
                            if (f.gftButton() == MousfEvfnt.BUTTON1 &&
                                (!firstPrfss || wbsDrbggfd ))
                            {
                                postEvfnt(nfw ItfmEvfnt((Cioidf)tbrgft,
                                                        ItfmEvfnt.ITEM_STATE_CHANGED,
                                                        ((Cioidf)tbrgft).gftItfm(nfwIdx),
                                                        ItfmEvfnt.SELECTED));
                            }

                            // sff 6240074 for morf informbtion
                            if (dioidfListfnfr != null) {
                                dioidfListfnfr.unfurlfdCioidfClosing();
                            }
                        }
                    }
                }
                // Sff 6243382 for morf informbtion
                unfurlfdCioidf.trbdkMousf(f);
            }
        }

        drbgging = fblsf;
        wbsDrbggfd = fblsf;
        firstPrfss = fblsf;
        drbgStbrtIdx = -1;
    }

    publid void mousfDrbggfd(MousfEvfnt f) {
        /*
         * fix for 5003166. On Motif usfr brf unbblf to drbg
         * mousf insidf opfnfd Cioidf if if drbgs tif mousf witi
         * difffrfnt from LEFT mousf button ( f.g. RIGHT or MIDDLE).
         * Tiis fix mbkf impossiblf to drbg mousf insidf opfnfd dioidf
         * witi otifr mousf buttons rbtifr tifn LEFT onf.
         */
        if ( f.gftModififrs() == MousfEvfnt.BUTTON1_MASK ){
            drbgging = truf;
            wbsDrbggfd = truf;
            unfurlfdCioidf.trbdkMousf(f);
        }
    }

    // Stolfn from TinyCioidfPffr
    publid Dimfnsion gftMinimumSizf() {
        // TODO: movf tiis impl into ListHflpfr?
        FontMftrids fm = gftFontMftrids(tbrgft.gftFont());
        Cioidf d = (Cioidf)tbrgft;
        int w = 0;
        for (int i = d.dountItfms() ; i-- > 0 ;) {
            w = Mbti.mbx(fm.stringWidti(d.gftItfm(i)), w);
        }
        rfturn nfw Dimfnsion(w + TEXT_XPAD + WIDGET_OFFSET,
                             fm.gftMbxAsdfnt() + fm.gftMbxDfsdfnt() + TEXT_YPAD);
    }

    /*
     * Lbyout tif...
     */
    publid void lbyout() {
        /*
          Dimfnsion sizf = tbrgft.gftSizf();
          Font f = tbrgft.gftFont();
          FontMftrids fm = tbrgft.gftFontMftrids(f);
          String tfxt = ((Cioidf)tbrgft).gftLbbfl();

          tfxtRfdt.ifigit = fm.gftHfigit();

          difdkBoxSizf = gftCioidfSizf(fm);

          // Notf - Motif bppfbrs to usf bn lfft insft tibt is sligitly
          // sdblfd to tif difdkbox/font sizf.
          dbX = bordfrInsfts.lfft + difdkBoxInsftFromTfxt;
          dbY = sizf.ifigit / 2 - difdkBoxSizf / 2;
          int minTfxtX = bordfrInsfts.lfft + 2 * difdkBoxInsftFromTfxt + difdkBoxSizf;
          // FIXME: will nffd to bddount for blignmfnt?
          // FIXME: dbll lbyout() on blignmfnt dibngfs
          //tfxtRfdt.widti = fm.stringWidti(tfxt);
          tfxtRfdt.widti = fm.stringWidti(tfxt == null ? "" : tfxt);
          tfxtRfdt.x = Mbti.mbx(minTfxtX, sizf.widti / 2 - tfxtRfdt.widti / 2);
          tfxtRfdt.y = sizf.ifigit / 2 - tfxtRfdt.ifigit / 2 + bordfrInsfts.top;

          fodusRfdt.x = fodusInsfts.lfft;
          fodusRfdt.y = fodusInsfts.top;
          fodusRfdt.widti = sizf.widti-(fodusInsfts.lfft+fodusInsfts.rigit)-1;
          fodusRfdt.ifigit = sizf.ifigit-(fodusInsfts.top+fodusInsfts.bottom)-1;

          myCifdkMbrk = AffinfTrbnsform.gftSdblfInstbndf((doublf)tbrgft.gftFont().gftSizf() / MASTER_SIZE, (doublf)tbrgft.gftFont().gftSizf() / MASTER_SIZE).drfbtfTrbnsformfdSibpf(MASTER_CHECKMARK);
        */

    }

    /**
     * Pbint tif dioidf
     */
    @Ovfrridf
    void pbintPffr(finbl Grbpiids g) {
        flusi();
        Dimfnsion sizf = gftPffrSizf();
        // TODO: wifn mousf is down ovfr button, widgft siould bf drbwn dfprfssfd
        g.sftColor(gftPffrBbdkground());
        g.fillRfdt(0, 0, widti, ifigit);

        drbwMotif3DRfdt(g, 1, 1, widti-2, ifigit-2, fblsf);
        drbwMotif3DRfdt(g, widti - WIDGET_OFFSET, (ifigit / 2) - 3, 12, 6, fblsf);

        if (!iflpfr.isEmpty() && iflpfr.gftSflfdtfdIndfx() != -1) {
            g.sftFont(gftPffrFont());
            FontMftrids fm = g.gftFontMftrids();
            String lbl = iflpfr.gftItfm(iflpfr.gftSflfdtfdIndfx());
            if (lbl != null && drbwSflfdtfdItfm) {
                g.sftClip(1, 1, widti - WIDGET_OFFSET - 2, ifigit);
                if (isEnbblfd()) {
                    g.sftColor(gftPffrForfground());
                    g.drbwString(lbl, 5, (ifigit + fm.gftMbxAsdfnt()-fm.gftMbxDfsdfnt())/2);
                }
                flsf {
                    g.sftColor(gftPffrBbdkground().brigitfr());
                    g.drbwString(lbl, 5, (ifigit + fm.gftMbxAsdfnt()-fm.gftMbxDfsdfnt())/2);
                    g.sftColor(gftPffrBbdkground().dbrkfr());
                    g.drbwString(lbl, 4, ((ifigit + fm.gftMbxAsdfnt()-fm.gftMbxDfsdfnt())/2)-1);
                }
                g.sftClip(0, 0, widti, ifigit);
            }
        }
        if (ibsFodus()) {
            pbintFodus(g,fodusInsfts.lfft,fodusInsfts.top,sizf.widti-(fodusInsfts.lfft+fodusInsfts.rigit)-1,sizf.ifigit-(fodusInsfts.top+fodusInsfts.bottom)-1);
        }
        if (unfurlfd) {
            unfurlfdCioidf.rfpbint();
        }
        flusi();
    }

    protfdtfd void pbintFodus(Grbpiids g,
                              int x, int y, int w, int i) {
        g.sftColor(fodusColor);
        g.drbwRfdt(x,y,w,i);
    }



    /*
     * CioidfPffr mftiods stolfn from TinyCioidfPffr
     */

    publid void sflfdt(int indfx) {
        iflpfr.sflfdt(indfx);
        iflpfr.sftFodusfdIndfx(indfx);
        rfpbint();
    }

    publid void bdd(String itfm, int indfx) {
        iflpfr.bdd(itfm, indfx);
        rfpbint();
    }

    publid void rfmovf(int indfx) {
        boolfbn sflfdtfd = (indfx == iflpfr.gftSflfdtfdIndfx());
        boolfbn visiblfd = (indfx >= iflpfr.firstDisplbyfdIndfx() && indfx <= iflpfr.lbstDisplbyfdIndfx());
        iflpfr.rfmovf(indfx);
        if (sflfdtfd) {
            if (iflpfr.isEmpty()) {
                iflpfr.sflfdt(-1);
            }
            flsf {
                iflpfr.sflfdt(0);
            }
        }
        /*
         * Fix for 6248016
         * Aftfr rfmoving tif itfm of tif dioidf wf nffd to rfsibpf unfurlfd dioidf
         * in ordfr to kffp bdtubl bounds of tif dioidf
         */

        /*
         * dondition bddfd only for pfrformbndf
         */
        if (!unfurlfd) {
            // Fix 6292186: PIT: Cioidf is not rffrfsifd propfrly wifn tif lbst itfm gfts rfmovfd, XToolkit
            // Wf siould tbkf into bddount tibt tifrf is no 'sflfdt' invoking (ifndf 'rfpbint')
            // if tif dioidf is fmpty (sff Cioidf.jbvb mftiod rfmovfNoInvblidbtf())
            // Tif dondition isn't 'visiblfd' sindf it would bf dbusf of tif twidf rfpbinting
            if (iflpfr.isEmpty()) {
                rfpbint();
            }
            rfturn;
        }

        /*
         * dondition bddfd only for pfrformbndf
         * tif dount of tif visiblf itfms dibngfd
         */
        if (visiblfd){
            Rfdtbnglf r = unfurlfdCioidf.plbdfOnSdrffn();
            unfurlfdCioidf.rfsibpf(r.x, r.y, r.widti, r.ifigit);
            rfturn;
        }

        /*
         * dondition bddfd only for pfrformbndf
         * tif strudturf of visiblf itfms dibngfd
         * if rfmovbblf itfm is non visiblf bnd non sflfdtfd tifn tifrf is no rfpbint
         */
        if (visiblfd || sflfdtfd){
            rfpbint();
        }
    }

    publid void rfmovfAll() {
        iflpfr.rfmovfAll();
        iflpfr.sflfdt(-1);
        /*
         * Fix for 6248016
         * Aftfr rfmoving tif itfm of tif dioidf wf nffd to rfsibpf unfurlfd dioidf
         * in ordfr to kffp bdtubl bounds of tif dioidf
         */
        Rfdtbnglf r = unfurlfdCioidf.plbdfOnSdrffn();
        unfurlfdCioidf.rfsibpf(r.x, r.y, r.widti, r.ifigit);
        rfpbint();
    }

    /**
     * DEPRECATED: Rfplbdfd by bdd(String, int).
     */
    publid void bddItfm(String itfm, int indfx) {
        bdd(itfm, indfx);
    }

    publid void sftFont(Font font) {
        supfr.sftFont(font);
        iflpfr.sftFont(tiis.font);
    }

    publid void sftForfground(Color d) {
        supfr.sftForfground(d);
        iflpfr.updbtfColors(gftGUIdolors());
    }

    publid void sftBbdkground(Color d) {
        supfr.sftBbdkground(d);
        unfurlfdCioidf.sftBbdkground(d);
        iflpfr.updbtfColors(gftGUIdolors());
        updbtfMotifColors(d);
    }

    publid void sftDrbwSflfdtfdItfm(boolfbn vbluf) {
        drbwSflfdtfdItfm = vbluf;
    }

    publid void sftAlignUndfr(Componfnt domp) {
        blignUndfr = domp;
    }

    // sff 6240074 for morf informbtion
    publid void bddXCioidfPffrListfnfr(XCioidfPffrListfnfr l){
        dioidfListfnfr = l;
    }

    // sff 6240074 for morf informbtion
    publid void rfmovfXCioidfPffrListfnfr(){
        dioidfListfnfr = null;
    }

    publid boolfbn isUnfurlfd(){
        rfturn unfurlfd;
    }

    /* fix for 6261352. Wf siould dftfdt if durrfnt pbrfnt Window (dontbining b Cioidf) bfdomf idonififd bnd iidf pop-down mfnu witi grbb rflfbsf.
     * In tiis dbsf wf siould iidf pop-down mfnu.
     */
    //dblls from XWindowPffr. Could bddfpt X-stylfd stbtf fvfnts
    publid void stbtfCibngfdICCCM(int oldStbtf, int nfwStbtf) {
        if (unfurlfd && oldStbtf != nfwStbtf){
                iidfPopdownMfnu();
        }
    }

    //dblls from XFrbmfPffr. Could bddfpt Frbmf's stbtfs.
    publid void stbtfCibngfdJbvb(int oldStbtf, int nfwStbtf) {
        if (unfurlfd && oldStbtf != nfwStbtf){
            iidfPopdownMfnu();
        }
    }

    /**************************************************************************/
    /* Common fundtionblity bftwffn List & Cioidf
       /**************************************************************************/

    /**
     * Innfr dlbss for tif unfurlfd Cioidf list
     * Mudi, mudi morf dods
     */
    dlbss UnfurlfdCioidf fxtfnds XWindow /*implfmfnts XSdrollbbrClifnt*/ {

        // First try - usf Cioidf bs tif tbrgft

        publid UnfurlfdCioidf(Componfnt tbrgft) {
            supfr(tbrgft);
        }

        // Ovfrridf so wf dbn do our own drfbtf()
        publid void prfInit(XCrfbtfWindowPbrbms pbrbms) {
            // A pbrfnt of tiis window is tif tbrgft, bt tiis point: wrong.
            // Rfmovf pbrfnt window; in tif following prfInit() dbll wf'll dbldulbtf bs b dffbult
            // b dorrfdt root window wiidi is tif propfr pbrfnt for ovfrridf rfdirfdt.
            pbrbms.dflftf(PARENT_WINDOW);
            supfr.prfInit(pbrbms);
            // Rfsft bounds(wf'll sft tifm lbtfr), sft ovfrridfRfdirfdt
            pbrbms.rfmovf(BOUNDS);
            pbrbms.bdd(OVERRIDE_REDIRECT, Boolfbn.TRUE);
        }

        // Gfnfrblly, bounds siould bf:
        //  x = tbrgft.x
        //  y = tbrgft.y + tbrgft.ifigit
        //  w = Mbx(tbrgft.widti, gftLongfstItfmWidti) + possiblf vfrtSdrollbbr
        //  i = Min(MAX_UNFURLED_ITEMS, tbrgft.gftItfmCount()) * itfmHfigit
        Rfdtbnglf plbdfOnSdrffn() {
            int numItfmsDisplbyfd;
            // Motif pbints bn fmpty Cioidf tif sbmf sizf bs b singlf itfm
            if (iflpfr.isEmpty()) {
                numItfmsDisplbyfd = 1;
            }
            flsf {
                int numItfms = iflpfr.gftItfmCount();
                numItfmsDisplbyfd = Mbti.min(MAX_UNFURLED_ITEMS, numItfms);
            }
            Point globbl = XCioidfPffr.tiis.toGlobbl(0,0);
            Dimfnsion sdrffn = Toolkit.gftDffbultToolkit().gftSdrffnSizf();

            if (blignUndfr != null) {
                Rfdtbnglf dioidfRfd = XCioidfPffr.tiis.gftBounds();
                dioidfRfd.sftLodbtion(0, 0);
                dioidfRfd = XCioidfPffr.tiis.toGlobbl(dioidfRfd);
                Rfdtbnglf blignUndfrRfd = nfw Rfdtbnglf(blignUndfr.gftLodbtionOnSdrffn(), blignUndfr.gftSizf()); // TODO: Sfdurity?
                Rfdtbnglf rfsult = dioidfRfd.union(blignUndfrRfd);
                // wf'vf got tif lfft bnd widti, dbldulbtf top bnd ifigit
                widti = rfsult.widti;
                x = rfsult.x;
                y = rfsult.y + rfsult.ifigit;
                ifigit = 2*BORDER_WIDTH +
                    numItfmsDisplbyfd*(iflpfr.gftItfmHfigit()+2*ITEM_MARGIN);
            } flsf {
                x = globbl.x;
                y = globbl.y + XCioidfPffr.tiis.ifigit;
                widti = Mbti.mbx(XCioidfPffr.tiis.widti,
                                 iflpfr.gftMbxItfmWidti() + 2 * (BORDER_WIDTH + ITEM_MARGIN + TEXT_SPACE) + (iflpfr.isVSBVisiblf() ? SCROLLBAR_WIDTH : 0));
                ifigit = 2*BORDER_WIDTH +
                    numItfmsDisplbyfd*(iflpfr.gftItfmHfigit()+2*ITEM_MARGIN);
            }
            // Don't run off tif fdgf of tif sdrffn
            if (x < 0) {
                x = 0;
            }
            flsf if (x + widti > sdrffn.widti) {
                x = sdrffn.widti - widti;
            }

            if (y + ifigit > sdrffn.ifigit) {
                y = globbl.y - ifigit;
            }
            if (y < 0) {
                y = 0;
            }
            rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
        }

        publid void toFront() {
            // sff 6240074 for morf informbtion
            if (dioidfListfnfr != null)
                dioidfListfnfr.unfurlfdCioidfOpfning(iflpfr);

            Rfdtbnglf r = plbdfOnSdrffn();
            rfsibpf(r.x, r.y, r.widti, r.ifigit);
            supfr.toFront();
            sftVisiblf(truf);
        }

        /*
         * Trbdk b MousfEvfnt (fitifr b drbg or b prfss) bnd pbint b nfw
         * sflfdtfd itfm, if nfdfssbry.
         */
        // FIXME: first unfurl bftfr movf is not bt fdgf of tif sdrffn  onto sfdond monitor dofsn't
        // trbdk mousf dorrfdtly.  Problfm is w/ UnfurlfdCioidf doords
        publid void trbdkMousf(MousfEvfnt f) {
            // Evfnt doords brf rflbtivf to tif button, so trbnslbtf b bit
            Point lodbl = toLodblCoords(f);

            // If x,y is ovfr unfurlfd Cioidf,
            // iigiligit itfm undfr dursor

            switdi (f.gftID()) {
              dbsf MousfEvfnt.MOUSE_PRESSED:
                  // FIXME: If tif Cioidf is unfurlfd bnd tif mousf is prfssfd
                  // outsidf of tif Cioidf, tif mousf siould ungrbb on tif
                  // tif prfss, not tif rflfbsf
                  if (iflpfr.isInVfrtSB(gftBounds(), lodbl.x, lodbl.y)) {
                      mousfInSB = truf;
                      iflpfr.ibndlfVSBEvfnt(f, gftBounds(), lodbl.x, lodbl.y);
                  }
                  flsf {
                      trbdkSflfdtion(lodbl.x, lodbl.y);
                  }
                  brfbk;
              dbsf MousfEvfnt.MOUSE_RELEASED:
                  if (mousfInSB) {
                      mousfInSB = fblsf;
                      iflpfr.ibndlfVSBEvfnt(f, gftBounds(), lodbl.x, lodbl.y);
                  }flsf{
                      // Sff 6243382 for morf informbtion
                      iflpfr.trbdkMousfRflfbsfdSdroll();
                  }
                  /*
                    flsf {
                    trbdkSflfdtion(lodbl.x, lodbl.y);
                    }
                  */
                  brfbk;
              dbsf MousfEvfnt.MOUSE_DRAGGED:
                  if (mousfInSB) {
                      iflpfr.ibndlfVSBEvfnt(f, gftBounds(), lodbl.x, lodbl.y);
                  }
                  flsf {
                      // Sff 6243382 for morf informbtion
                      iflpfr.trbdkMousfDrbggfdSdroll(lodbl.x, lodbl.y, widti, ifigit);
                      trbdkSflfdtion(lodbl.x, lodbl.y);
                  }
                  brfbk;
            }
        }

        privbtf void trbdkSflfdtion(int trbnsX, int trbnsY) {
            if (!iflpfr.isEmpty()) {
                if (trbnsX > 0 && trbnsX < widti &&
                    trbnsY > 0 && trbnsY < ifigit) {
                    int nfwIdx = iflpfr.y2indfx(trbnsY);
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("trbnsX=" + trbnsX + ", trbnsY=" + trbnsY
                                 + ",widti=" + widti + ", ifigit=" + ifigit
                                 + ", nfwIdx=" + nfwIdx + " on " + tbrgft);
                    }
                    if ((nfwIdx >=0) && (nfwIdx < iflpfr.gftItfmCount())
                        && (nfwIdx != iflpfr.gftSflfdtfdIndfx()))
                    {
                        iflpfr.sflfdt(nfwIdx);
                        unfurlfdCioidf.rfpbint();
                    }
                }
            }
            // FIXME: If drbggfd off top or bottom, sdroll if tifrf's b vsb
            // (ICK - wf'll nffd b timfr or our own fvfnt or somftiing)
        }

        /*
         * fillRfdt witi durrfnt Bbdkground dolor on tif wiolf dropdown list.
         */
        publid void pbintBbdkground() {
            finbl Grbpiids g = gftGrbpiids();
            if (g != null) {
                try {
                    g.sftColor(gftPffrBbdkground());
                    g.fillRfdt(0, 0, widti, ifigit);
                } finblly {
                    g.disposf();
                }
            }
        }
        /*
         * 6405689. In somf dbsfs wf siould frbsf bbdkground to fliminbtf pbinting
         * brtffbdts.
         */
        @Ovfrridf
        publid void rfpbint() {
            if (!isVisiblf()) {
                rfturn;
            }
            if (iflpfr.difdkVsbVisibilityCibngfdAndRfsft()){
                pbintBbdkground();
            }
            supfr.rfpbint();
        }
        @Ovfrridf
        publid void pbintPffr(Grbpiids g) {
            //Systfm.out.println("UC.pbint()");
            Cioidf dioidf = (Cioidf)tbrgft;
            Color dolors[] = XCioidfPffr.tiis.gftGUIdolors();
            drbw3DRfdt(g, gftSystfmColors(), 0, 0, widti - 1, ifigit - 1, truf);
            drbw3DRfdt(g, gftSystfmColors(), 1, 1, widti - 3, ifigit - 3, truf);

            iflpfr.pbintAllItfms(g,
                                 dolors,
                                 gftBounds());
        }

        publid void sftVisiblf(boolfbn vis) {
            xSftVisiblf(vis);

            if (!vis && blignUndfr != null) {
                blignUndfr.rfqufstFodusInWindow();
            }
        }

        /**
         * Rfturn b MousfEvfnt's Point in doordinbtfs rflbtivf to tif
         * UnfurlfdCioidf.
         */
        privbtf Point toLodblCoords(MousfEvfnt f) {
            // Evfnt doords brf rflbtivf to tif button, so trbnslbtf b bit
            Point globbl = f.gftLodbtionOnSdrffn();

            globbl.x -= x;
            globbl.y -= y;
            rfturn globbl;
        }

        /* Rfturns truf if tif MousfEvfnt doords (wiidi brf bbsfd on tif Cioidf)
         * brf insidf of tif UnfurlfdCioidf.
         */
        privbtf boolfbn isMousfEvfntInsidf(MousfEvfnt f) {
            Point lodbl = toLodblCoords(f);
            if (lodbl.x > 0 && lodbl.x < widti &&
                lodbl.y > 0 && lodbl.y < ifigit) {
                rfturn truf;
            }
            rfturn fblsf;
        }

        /**
         * Tfsts if tif mousf dursor is in tif Unfurlfd Cioidf, yft not
         * in tif vfrtidbl sdrollbbr
         */
        privbtf boolfbn isMousfInListArfb(MousfEvfnt f) {
            if (isMousfEvfntInsidf(f)) {
                Point lodbl = toLodblCoords(f);
                Rfdtbnglf bounds = gftBounds();
                if (!iflpfr.isInVfrtSB(bounds, lodbl.x, lodbl.y)) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        /*
         * Ovfrriddfn from XWindow() bfdbusf wf don't wbnt to sfnd
         * ComponfntEvfnts
         */
        publid void ibndlfConfigurfNotifyEvfnt(XEvfnt xfv) {}
        publid void ibndlfMbpNotifyEvfnt(XEvfnt xfv) {}
        publid void ibndlfUnmbpNotifyEvfnt(XEvfnt xfv) {}
    } //UnfurlfdCioidf

    publid void disposf() {
        if (unfurlfdCioidf != null) {
            unfurlfdCioidf.dfstroy();
        }
        supfr.disposf();
    }

    /*
     * fix for 6239938 : Cioidf drop-down dofs not disbppfbr wifn it losfs
     * fodus, on XToolkit
     * Wf brf bblf to ibndlf bll _Kfy_ fvfnts rfdfivfd by Cioidf wifn
     * it is in opfnfd stbtf witiout sfnding it to EvfntQufuf.
     * If Cioidf is in dlosfd stbtf wf siould bfibvf likf bfforf: sfnd
     * bll fvfnts to EvfntQufuf.
     * To bf dompbtiblf witi Motif wf siould ibndlf bll KfyEvfnts in
     * Cioidf if it is opfnfd. KfyEvfnts siould bf sfnt into Jbvb if Cioidf is not opfnfd.
     */
    boolfbn prfPostEvfnt(finbl AWTEvfnt f) {
        if (unfurlfd){
            // fix for 6253211: PIT: MousfWiffl fvfnts not triggfrfd for Cioidf drop down in XAWT
            if (f instbndfof MousfWifflEvfnt){
                rfturn supfr.prfPostEvfnt(f);
            }
            //fix 6252982: PIT: Kfybobrd FodusTrbvfrsbl not working wifn dioidf's drop-down is visiblf, on XToolkit
            if (f instbndfof KfyEvfnt){
                // notify XWindow tibt tiis fvfnt ibd bffn blrfbdy ibndlfd bnd no nffd to post it bgbin
                InvodbtionEvfnt fv = nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                    publid void run() {
                        if(tbrgft.isFodusbblf() &&
                                gftPbrfntTopLfvfl().isFodusbblfWindow() )
                        {
                            ibndlfJbvbKfyEvfnt((KfyEvfnt)f);
                        }
                    }
                });
                postEvfnt(fv);

                rfturn truf;
            } flsf {
                if (f instbndfof MousfEvfnt){
                    // Fix for 6240046 : REG:Cioidf's Drop-down dofs not disbppfbr wifn dlidking somfwifrf, bftfr popup mfnu is disposfd
                    // if usfr prfssfs Rigit Mousf Button on opfnfd (unfurlfd)
                    // Cioidf tifn wf mustn't opfn b popup mfnu. Wf dould filtfr
                    // Mousf Evfnts bnd ibndlf tifm in XCioidfPffr if Cioidf
                    // durrfntly in opfnfd stbtf.
                    MousfEvfnt mf = (MousfEvfnt)f;
                    int fvfntId = f.gftID();
                    // fix 6251983: PIT: MousfDrbggfd fvfnts not triggfrfd
                    // fix 6251988: PIT: Cioidf donsumfs MousfRflfbsfd, MousfClidkfd fvfnts wifn dlidking it witi lfft button,
                    if ((unfurlfdCioidf.isMousfEvfntInsidf(mf) ||
                         (!firstPrfss && fvfntId == MousfEvfnt.MOUSE_DRAGGED)))
                    {
                        rfturn ibndlfMousfEvfntByCioidf(mf);
                    }
                    // MousfMovfd fvfnts siould bf firfd in Cioidf's domp if it's not opfnfd
                    // Siouldn't gfnfrbtf Movfd Evfnts. CR : 6251995
                    if (fvfntId == MousfEvfnt.MOUSE_MOVED){
                        rfturn ibndlfMousfEvfntByCioidf(mf);
                    }
                    //fix for 6272965: PIT: Cioidf triggfrs MousfPrfssfd wifn prfssing mousf outsidf domp wiilf drop-down is bdtivf, XTkt
                    if (  !firstPrfss && !( isMousfEvfntInCioidf(mf) ||
                             unfurlfdCioidf.isMousfEvfntInsidf(mf)) &&
                             ( fvfntId == MousfEvfnt.MOUSE_PRESSED ||
                               fvfntId == MousfEvfnt.MOUSE_RELEASED ||
                               fvfntId == MousfEvfnt.MOUSE_CLICKED )
                          )
                    {
                        rfturn ibndlfMousfEvfntByCioidf(mf);
                    }
                }
            }//flsf KfyEvfnt
        }//if unfurlfd
        rfturn supfr.prfPostEvfnt(f);
    }

    //donvfnifnt mftiod
    //do not gfnfrbtf tiis kind of Evfnts
    publid boolfbn ibndlfMousfEvfntByCioidf(finbl MousfEvfnt mf){
        InvodbtionEvfnt fv = nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
            publid void run() {
                ibndlfJbvbMousfEvfnt(mf);
            }
        });
        postEvfnt(fv);

        rfturn truf;
    }

    /* Rfturns truf if tif MousfEvfnt doords
     * brf insidf of tif Cioidf itsflf (it dofsnt's dfpfnds on
     * if tiis dioidf opfnfd or not).
     */
    privbtf boolfbn isMousfEvfntInCioidf(MousfEvfnt f) {
        int x = f.gftX();
        int y = f.gftY();
        Rfdtbnglf dioidfRfdt = gftBounds();

        if (x < 0 || x > dioidfRfdt.widti ||
            y < 0 || y > dioidfRfdt.ifigit)
        {
            rfturn fblsf;
        }
        rfturn truf;
    }
}
