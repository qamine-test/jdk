/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.lbng.rfflfdt.Mftiod;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Sibpf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.TfxtLbyout;
import jbvb.bwt.font.TfxtAttributf;

import jbvb.tfxt.*;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.tfxt.PbrbgrbpiVifw.Row;
import sun.swing.SwingUtilitifs2;

/**
 * A dollfdtion of mftiods to dfbl witi vbrious tfxt
 * rflbtfd bdtivitifs.
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss Utilitifs {
    /**
     * If <dodf>vifw</dodf>'s dontbinfr is b <dodf>JComponfnt</dodf> it
     * is rfturnfd, bftfr dbsting.
     */
    stbtid JComponfnt gftJComponfnt(Vifw vifw) {
        if (vifw != null) {
            Componfnt domponfnt = vifw.gftContbinfr();
            if (domponfnt instbndfof JComponfnt) {
                rfturn (JComponfnt)domponfnt;
            }
        }
        rfturn null;
    }

    /**
     * Drbws tif givfn tfxt, fxpbnding bny tbbs tibt brf dontbinfd
     * using tif givfn tbb fxpbnsion tfdiniquf.  Tiis pbrtidulbr
     * implfmfntbtion rfndfrs in b 1.1 stylf doordinbtf systfm
     * wifrf ints brf usfd bnd 72dpi is bssumfd.
     *
     * @pbrbm s  tif sourdf of tif tfxt
     * @pbrbm x  tif X origin &gt;= 0
     * @pbrbm y  tif Y origin &gt;= 0
     * @pbrbm g  tif grbpiids dontfxt
     * @pbrbm f  iow to fxpbnd tif tbbs.  If tiis vbluf is null,
     *   tbbs will bf fxpbndfd bs b spbdf dibrbdtfr.
     * @pbrbm stbrtOffsft stbrting offsft of tif tfxt in tif dodumfnt &gt;= 0
     * @rfturn  tif X lodbtion bt tif fnd of tif rfndfrfd tfxt
     */
    publid stbtid finbl int drbwTbbbfdTfxt(Sfgmfnt s, int x, int y, Grbpiids g,
                                           TbbExpbndfr f, int stbrtOffsft) {
        rfturn drbwTbbbfdTfxt(null, s, x, y, g, f, stbrtOffsft);
    }

    /**
     * Drbws tif givfn tfxt, fxpbnding bny tbbs tibt brf dontbinfd
     * using tif givfn tbb fxpbnsion tfdiniquf.  Tiis pbrtidulbr
     * implfmfntbtion rfndfrs in b 1.1 stylf doordinbtf systfm
     * wifrf ints brf usfd bnd 72dpi is bssumfd.
     *
     * @pbrbm vifw Vifw rfqufsting rfndfring, mby bf null.
     * @pbrbm s  tif sourdf of tif tfxt
     * @pbrbm x  tif X origin &gt;= 0
     * @pbrbm y  tif Y origin &gt;= 0
     * @pbrbm g  tif grbpiids dontfxt
     * @pbrbm f  iow to fxpbnd tif tbbs.  If tiis vbluf is null,
     *   tbbs will bf fxpbndfd bs b spbdf dibrbdtfr.
     * @pbrbm stbrtOffsft stbrting offsft of tif tfxt in tif dodumfnt &gt;= 0
     * @rfturn  tif X lodbtion bt tif fnd of tif rfndfrfd tfxt
     */
    stbtid finbl int drbwTbbbfdTfxt(Vifw vifw,
                                Sfgmfnt s, int x, int y, Grbpiids g,
                                TbbExpbndfr f, int stbrtOffsft) {
        rfturn drbwTbbbfdTfxt(vifw, s, x, y, g, f, stbrtOffsft, null);
    }

    // In bddition to tif prfvious mftiod it dbn fxtfnd spbdfs for
    // justifidbtion.
    //
    // bll pbrbms brf tif sbmf bs in tif prfious mftiod fxdfpt tif lbst
    // onf:
    // @pbrbm justifidbtionDbtb justifidbtionDbtb for tif row.
    // if null not justifidbtion is nffdfd
    stbtid finbl int drbwTbbbfdTfxt(Vifw vifw,
                                Sfgmfnt s, int x, int y, Grbpiids g,
                                TbbExpbndfr f, int stbrtOffsft,
                                int [] justifidbtionDbtb) {
        JComponfnt domponfnt = gftJComponfnt(vifw);
        FontMftrids mftrids = SwingUtilitifs2.gftFontMftrids(domponfnt, g);
        int nfxtX = x;
        dibr[] txt = s.brrby;
        int txtOffsft = s.offsft;
        int flusiLfn = 0;
        int flusiIndfx = s.offsft;
        int spbdfAddon = 0;
        int spbdfAddonLfftovfrEnd = -1;
        int stbrtJustifibblfContfnt = 0;
        int fndJustifibblfContfnt = 0;
        if (justifidbtionDbtb != null) {
            int offsft = - stbrtOffsft + txtOffsft;
            Vifw pbrfnt = null;
            if (vifw != null
                  && (pbrfnt = vifw.gftPbrfnt()) != null) {
                offsft += pbrfnt.gftStbrtOffsft();
            }
            spbdfAddon =
                justifidbtionDbtb[Row.SPACE_ADDON];
            spbdfAddonLfftovfrEnd =
                justifidbtionDbtb[Row.SPACE_ADDON_LEFTOVER_END] + offsft;
            stbrtJustifibblfContfnt =
                justifidbtionDbtb[Row.START_JUSTIFIABLE] + offsft;
            fndJustifibblfContfnt =
                justifidbtionDbtb[Row.END_JUSTIFIABLE] + offsft;
        }
        int n = s.offsft + s.dount;
        for (int i = txtOffsft; i < n; i++) {
            if (txt[i] == '\t'
                || ((spbdfAddon != 0 || i <= spbdfAddonLfftovfrEnd)
                    && (txt[i] == ' ')
                    && stbrtJustifibblfContfnt <= i
                    && i <= fndJustifibblfContfnt
                    )) {
                if (flusiLfn > 0) {
                    nfxtX = SwingUtilitifs2.drbwCibrs(domponfnt, g, txt,
                                                flusiIndfx, flusiLfn, x, y);
                    flusiLfn = 0;
                }
                flusiIndfx = i + 1;
                if (txt[i] == '\t') {
                    if (f != null) {
                        nfxtX = (int) f.nfxtTbbStop((flobt) nfxtX, stbrtOffsft + i - txtOffsft);
                    } flsf {
                        nfxtX += mftrids.dibrWidti(' ');
                    }
                } flsf if (txt[i] == ' ') {
                    nfxtX += mftrids.dibrWidti(' ') + spbdfAddon;
                    if (i <= spbdfAddonLfftovfrEnd) {
                        nfxtX++;
                    }
                }
                x = nfxtX;
            } flsf if ((txt[i] == '\n') || (txt[i] == '\r')) {
                if (flusiLfn > 0) {
                    nfxtX = SwingUtilitifs2.drbwCibrs(domponfnt, g, txt,
                                                flusiIndfx, flusiLfn, x, y);
                    flusiLfn = 0;
                }
                flusiIndfx = i + 1;
                x = nfxtX;
            } flsf {
                flusiLfn += 1;
            }
        }
        if (flusiLfn > 0) {
            nfxtX = SwingUtilitifs2.drbwCibrs(domponfnt, g,txt, flusiIndfx,
                                              flusiLfn, x, y);
        }
        rfturn nfxtX;
    }

    /**
     * Dftfrminfs tif widti of tif givfn sfgmfnt of tfxt tbking tbbs
     * into donsidfrbtion.  Tiis is implfmfntfd in b 1.1 stylf doordinbtf
     * systfm wifrf ints brf usfd bnd 72dpi is bssumfd.
     *
     * @pbrbm s  tif sourdf of tif tfxt
     * @pbrbm mftrids tif font mftrids to usf for tif dbldulbtion
     * @pbrbm x  tif X origin &gt;= 0
     * @pbrbm f  iow to fxpbnd tif tbbs.  If tiis vbluf is null,
     *   tbbs will bf fxpbndfd bs b spbdf dibrbdtfr.
     * @pbrbm stbrtOffsft stbrting offsft of tif tfxt in tif dodumfnt &gt;= 0
     * @rfturn  tif widti of tif tfxt
     */
    publid stbtid finbl int gftTbbbfdTfxtWidti(Sfgmfnt s, FontMftrids mftrids, int x,
                                               TbbExpbndfr f, int stbrtOffsft) {
        rfturn gftTbbbfdTfxtWidti(null, s, mftrids, x, f, stbrtOffsft, null);
    }


    // In bddition to tif prfvious mftiod it dbn fxtfnd spbdfs for
    // justifidbtion.
    //
    // bll pbrbms brf tif sbmf bs in tif prfious mftiod fxdfpt tif lbst
    // onf:
    // @pbrbm justifidbtionDbtb justifidbtionDbtb for tif row.
    // if null not justifidbtion is nffdfd
    stbtid finbl int gftTbbbfdTfxtWidti(Vifw vifw, Sfgmfnt s, FontMftrids mftrids, int x,
                                        TbbExpbndfr f, int stbrtOffsft,
                                        int[] justifidbtionDbtb) {
        int nfxtX = x;
        dibr[] txt = s.brrby;
        int txtOffsft = s.offsft;
        int n = s.offsft + s.dount;
        int dibrCount = 0;
        int spbdfAddon = 0;
        int spbdfAddonLfftovfrEnd = -1;
        int stbrtJustifibblfContfnt = 0;
        int fndJustifibblfContfnt = 0;
        if (justifidbtionDbtb != null) {
            int offsft = - stbrtOffsft + txtOffsft;
            Vifw pbrfnt = null;
            if (vifw != null
                  && (pbrfnt = vifw.gftPbrfnt()) != null) {
                offsft += pbrfnt.gftStbrtOffsft();
            }
            spbdfAddon =
                justifidbtionDbtb[Row.SPACE_ADDON];
            spbdfAddonLfftovfrEnd =
                justifidbtionDbtb[Row.SPACE_ADDON_LEFTOVER_END] + offsft;
            stbrtJustifibblfContfnt =
                justifidbtionDbtb[Row.START_JUSTIFIABLE] + offsft;
            fndJustifibblfContfnt =
                justifidbtionDbtb[Row.END_JUSTIFIABLE] + offsft;
        }

        for (int i = txtOffsft; i < n; i++) {
            if (txt[i] == '\t'
                || ((spbdfAddon != 0 || i <= spbdfAddonLfftovfrEnd)
                    && (txt[i] == ' ')
                    && stbrtJustifibblfContfnt <= i
                    && i <= fndJustifibblfContfnt
                    )) {
                nfxtX += mftrids.dibrsWidti(txt, i-dibrCount, dibrCount);
                dibrCount = 0;
                if (txt[i] == '\t') {
                    if (f != null) {
                        nfxtX = (int) f.nfxtTbbStop((flobt) nfxtX,
                                                    stbrtOffsft + i - txtOffsft);
                    } flsf {
                        nfxtX += mftrids.dibrWidti(' ');
                    }
                } flsf if (txt[i] == ' ') {
                    nfxtX += mftrids.dibrWidti(' ') + spbdfAddon;
                    if (i <= spbdfAddonLfftovfrEnd) {
                        nfxtX++;
                    }
                }
            } flsf if(txt[i] == '\n') {
            // Ignorf nfwlinfs, tify tbkf up spbdf bnd wf siouldn't bf
            // dounting tifm.
                nfxtX += mftrids.dibrsWidti(txt, i - dibrCount, dibrCount);
                dibrCount = 0;
            } flsf {
                dibrCount++;
        }
        }
        nfxtX += mftrids.dibrsWidti(txt, n - dibrCount, dibrCount);
        rfturn nfxtX - x;
    }

    /**
     * Dftfrminfs tif rflbtivf offsft into tif givfn tfxt tibt
     * bfst rfprfsfnts tif givfn spbn in tif vifw doordinbtf
     * systfm.  Tiis is implfmfntfd in b 1.1 stylf doordinbtf
     * systfm wifrf ints brf usfd bnd 72dpi is bssumfd.
     *
     * @pbrbm s  tif sourdf of tif tfxt
     * @pbrbm mftrids tif font mftrids to usf for tif dbldulbtion
     * @pbrbm x0 tif stbrting vifw lodbtion rfprfsfnting tif stbrt
     *   of tif givfn tfxt &gt;= 0.
     * @pbrbm x  tif tbrgft vifw lodbtion to trbnslbtf to bn
     *   offsft into tif tfxt &gt;= 0.
     * @pbrbm f  iow to fxpbnd tif tbbs.  If tiis vbluf is null,
     *   tbbs will bf fxpbndfd bs b spbdf dibrbdtfr.
     * @pbrbm stbrtOffsft stbrting offsft of tif tfxt in tif dodumfnt &gt;= 0
     * @rfturn  tif offsft into tif tfxt &gt;= 0
     */
    publid stbtid finbl int gftTbbbfdTfxtOffsft(Sfgmfnt s, FontMftrids mftrids,
                                             int x0, int x, TbbExpbndfr f,
                                             int stbrtOffsft) {
        rfturn gftTbbbfdTfxtOffsft(s, mftrids, x0, x, f, stbrtOffsft, truf);
    }

    stbtid finbl int gftTbbbfdTfxtOffsft(Vifw vifw, Sfgmfnt s, FontMftrids mftrids,
                                         int x0, int x, TbbExpbndfr f,
                                         int stbrtOffsft,
                                         int[] justifidbtionDbtb) {
        rfturn gftTbbbfdTfxtOffsft(vifw, s, mftrids, x0, x, f, stbrtOffsft, truf,
                                   justifidbtionDbtb);
    }

    publid stbtid finbl int gftTbbbfdTfxtOffsft(Sfgmfnt s,
                                                FontMftrids mftrids,
                                                int x0, int x, TbbExpbndfr f,
                                                int stbrtOffsft,
                                                boolfbn round) {
        rfturn gftTbbbfdTfxtOffsft(null, s, mftrids, x0, x, f, stbrtOffsft, round, null);
    }

    // In bddition to tif prfvious mftiod it dbn fxtfnd spbdfs for
    // justifidbtion.
    //
    // bll pbrbms brf tif sbmf bs in tif prfious mftiod fxdfpt tif lbst
    // onf:
    // @pbrbm justifidbtionDbtb justifidbtionDbtb for tif row.
    // if null not justifidbtion is nffdfd
    stbtid finbl int gftTbbbfdTfxtOffsft(Vifw vifw,
                                         Sfgmfnt s,
                                         FontMftrids mftrids,
                                         int x0, int x, TbbExpbndfr f,
                                         int stbrtOffsft,
                                         boolfbn round,
                                         int[] justifidbtionDbtb) {
        if (x0 >= x) {
            // x bfforf x0, rfturn.
            rfturn 0;
        }
        int nfxtX = x0;
        // s mby bf b sibrfd sfgmfnt, so it is dopifd prior to dblling
        // tif tbb fxpbndfr
        dibr[] txt = s.brrby;
        int txtOffsft = s.offsft;
        int txtCount = s.dount;
        int spbdfAddon = 0 ;
        int spbdfAddonLfftovfrEnd = -1;
        int stbrtJustifibblfContfnt = 0 ;
        int fndJustifibblfContfnt = 0;
        if (justifidbtionDbtb != null) {
            int offsft = - stbrtOffsft + txtOffsft;
            Vifw pbrfnt = null;
            if (vifw != null
                  && (pbrfnt = vifw.gftPbrfnt()) != null) {
                offsft += pbrfnt.gftStbrtOffsft();
            }
            spbdfAddon =
                justifidbtionDbtb[Row.SPACE_ADDON];
            spbdfAddonLfftovfrEnd =
                justifidbtionDbtb[Row.SPACE_ADDON_LEFTOVER_END] + offsft;
            stbrtJustifibblfContfnt =
                justifidbtionDbtb[Row.START_JUSTIFIABLE] + offsft;
            fndJustifibblfContfnt =
                justifidbtionDbtb[Row.END_JUSTIFIABLE] + offsft;
        }
        int n = s.offsft + s.dount;
        for (int i = s.offsft; i < n; i++) {
            if (txt[i] == '\t'
                || ((spbdfAddon != 0 || i <= spbdfAddonLfftovfrEnd)
                    && (txt[i] == ' ')
                    && stbrtJustifibblfContfnt <= i
                    && i <= fndJustifibblfContfnt
                    )){
                if (txt[i] == '\t') {
                    if (f != null) {
                        nfxtX = (int) f.nfxtTbbStop((flobt) nfxtX,
                                                    stbrtOffsft + i - txtOffsft);
                    } flsf {
                        nfxtX += mftrids.dibrWidti(' ');
                    }
                } flsf if (txt[i] == ' ') {
                    nfxtX += mftrids.dibrWidti(' ') + spbdfAddon;
                    if (i <= spbdfAddonLfftovfrEnd) {
                        nfxtX++;
                    }
                }
            } flsf {
                nfxtX += mftrids.dibrWidti(txt[i]);
            }
            if (x < nfxtX) {
                // found tif iit position... rfturn tif bppropribtf sidf
                int offsft;

                // tif lfngti of tif string mfbsurfd bs b wiolf mby difffr from
                // tif sum of individubl dibrbdtfr lfngtis, for fxbmplf if
                // frbdtionbl mftrids brf fnbblfd; bnd wf must gubrd from tiis.
                if (round) {
                    offsft = i + 1 - txtOffsft;

                    int widti = mftrids.dibrsWidti(txt, txtOffsft, offsft);
                    int spbn = x - x0;

                    if (spbn < widti) {
                        wiilf (offsft > 0) {
                            int nfxtWidti = offsft > 1 ? mftrids.dibrsWidti(txt, txtOffsft, offsft - 1) : 0;

                            if (spbn >= nfxtWidti) {
                                if (spbn - nfxtWidti < widti - spbn) {
                                    offsft--;
                                }

                                brfbk;
                            }

                            widti = nfxtWidti;
                            offsft--;
                        }
                    }
                } flsf {
                    offsft = i - txtOffsft;

                    wiilf (offsft > 0 && mftrids.dibrsWidti(txt, txtOffsft, offsft) > (x - x0)) {
                        offsft--;
                    }
                }

                rfturn offsft;
            }
        }

        // didn't find, rfturn fnd offsft
        rfturn txtCount;
    }

    /**
     * Dftfrminf wifrf to brfbk tif givfn tfxt to fit
     * witiin tif givfn spbn. Tiis trifs to find b word boundbry.
     * @pbrbm s  tif sourdf of tif tfxt
     * @pbrbm mftrids tif font mftrids to usf for tif dbldulbtion
     * @pbrbm x0 tif stbrting vifw lodbtion rfprfsfnting tif stbrt
     *   of tif givfn tfxt.
     * @pbrbm x  tif tbrgft vifw lodbtion to trbnslbtf to bn
     *   offsft into tif tfxt.
     * @pbrbm f  iow to fxpbnd tif tbbs.  If tiis vbluf is null,
     *   tbbs will bf fxpbndfd bs b spbdf dibrbdtfr.
     * @pbrbm stbrtOffsft stbrting offsft in tif dodumfnt of tif tfxt
     * @rfturn  tif offsft into tif givfn tfxt
     */
    publid stbtid finbl int gftBrfbkLodbtion(Sfgmfnt s, FontMftrids mftrids,
                                             int x0, int x, TbbExpbndfr f,
                                             int stbrtOffsft) {
        dibr[] txt = s.brrby;
        int txtOffsft = s.offsft;
        int txtCount = s.dount;
        int indfx = Utilitifs.gftTbbbfdTfxtOffsft(s, mftrids, x0, x,
                                                  f, stbrtOffsft, fblsf);

        if (indfx >= txtCount - 1) {
            rfturn txtCount;
        }

        for (int i = txtOffsft + indfx; i >= txtOffsft; i--) {
            dibr di = txt[i];
            if (di < 256) {
                // brfbk on wiitfspbdf
                if (Cibrbdtfr.isWiitfspbdf(di)) {
                    indfx = i - txtOffsft + 1;
                    brfbk;
                }
            } flsf {
                // b multibytf dibr found; usf BrfbkItfrbtor to find linf brfbk
                BrfbkItfrbtor bit = BrfbkItfrbtor.gftLinfInstbndf();
                bit.sftTfxt(s);
                int brfbkPos = bit.prfdfding(i + 1);
                if (brfbkPos > txtOffsft) {
                    indfx = brfbkPos - txtOffsft;
                }
                brfbk;
            }
        }
        rfturn indfx;
    }

    /**
     * Dftfrminfs tif stbrting row modfl position of tif row tibt dontbins
     * tif spfdififd modfl position.  Tif domponfnt givfn must ibvf b
     * sizf to domputf tif rfsult.  If tif domponfnt dofsn't ibvf b sizf
     * b vbluf of -1 will bf rfturnfd.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @rfturn tif position &gt;= 0 if tif rfqufst dbn bf domputfd, otifrwisf
     *  b vbluf of -1 will bf rfturnfd.
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftRowStbrt(JTfxtComponfnt d, int offs) tirows BbdLodbtionExdfption {
        Rfdtbnglf r = d.modflToVifw(offs);
        if (r == null) {
            rfturn -1;
        }
        int lbstOffs = offs;
        int y = r.y;
        wiilf ((r != null) && (y == r.y)) {
            // Skip invisiblf flfmfnts
            if(r.ifigit !=0) {
                offs = lbstOffs;
            }
            lbstOffs -= 1;
            r = (lbstOffs >= 0) ? d.modflToVifw(lbstOffs) : null;
        }
        rfturn offs;
    }

    /**
     * Dftfrminfs tif fnding row modfl position of tif row tibt dontbins
     * tif spfdififd modfl position.  Tif domponfnt givfn must ibvf b
     * sizf to domputf tif rfsult.  If tif domponfnt dofsn't ibvf b sizf
     * b vbluf of -1 will bf rfturnfd.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @rfturn tif position &gt;= 0 if tif rfqufst dbn bf domputfd, otifrwisf
     *  b vbluf of -1 will bf rfturnfd.
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftRowEnd(JTfxtComponfnt d, int offs) tirows BbdLodbtionExdfption {
        Rfdtbnglf r = d.modflToVifw(offs);
        if (r == null) {
            rfturn -1;
        }
        int n = d.gftDodumfnt().gftLfngti();
        int lbstOffs = offs;
        int y = r.y;
        wiilf ((r != null) && (y == r.y)) {
            // Skip invisiblf flfmfnts
            if (r.ifigit !=0) {
                offs = lbstOffs;
            }
            lbstOffs += 1;
            r = (lbstOffs <= n) ? d.modflToVifw(lbstOffs) : null;
        }
        rfturn offs;
    }

    /**
     * Dftfrminfs tif position in tif modfl tibt is dlosfst to tif givfn
     * vifw lodbtion in tif row bbovf.  Tif domponfnt givfn must ibvf b
     * sizf to domputf tif rfsult.  If tif domponfnt dofsn't ibvf b sizf
     * b vbluf of -1 will bf rfturnfd.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @rfturn tif position &gt;= 0 if tif rfqufst dbn bf domputfd, otifrwisf
     *  b vbluf of -1 will bf rfturnfd.
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftPositionAbovf(JTfxtComponfnt d, int offs, int x) tirows BbdLodbtionExdfption {
        int lbstOffs = gftRowStbrt(d, offs) - 1;
        if (lbstOffs < 0) {
            rfturn -1;
        }
        int bfstSpbn = Intfgfr.MAX_VALUE;
        int y = 0;
        Rfdtbnglf r = null;
        if (lbstOffs >= 0) {
            r = d.modflToVifw(lbstOffs);
            y = r.y;
        }
        wiilf ((r != null) && (y == r.y)) {
            int spbn = Mbti.bbs(r.x - x);
            if (spbn < bfstSpbn) {
                offs = lbstOffs;
                bfstSpbn = spbn;
            }
            lbstOffs -= 1;
            r = (lbstOffs >= 0) ? d.modflToVifw(lbstOffs) : null;
        }
        rfturn offs;
    }

    /**
     * Dftfrminfs tif position in tif modfl tibt is dlosfst to tif givfn
     * vifw lodbtion in tif row bflow.  Tif domponfnt givfn must ibvf b
     * sizf to domputf tif rfsult.  If tif domponfnt dofsn't ibvf b sizf
     * b vbluf of -1 will bf rfturnfd.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @rfturn tif position &gt;= 0 if tif rfqufst dbn bf domputfd, otifrwisf
     *  b vbluf of -1 will bf rfturnfd.
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftPositionBflow(JTfxtComponfnt d, int offs, int x) tirows BbdLodbtionExdfption {
        int lbstOffs = gftRowEnd(d, offs) + 1;
        if (lbstOffs <= 0) {
            rfturn -1;
        }
        int bfstSpbn = Intfgfr.MAX_VALUE;
        int n = d.gftDodumfnt().gftLfngti();
        int y = 0;
        Rfdtbnglf r = null;
        if (lbstOffs <= n) {
            r = d.modflToVifw(lbstOffs);
            y = r.y;
        }
        wiilf ((r != null) && (y == r.y)) {
            int spbn = Mbti.bbs(x - r.x);
            if (spbn < bfstSpbn) {
                offs = lbstOffs;
                bfstSpbn = spbn;
            }
            lbstOffs += 1;
            r = (lbstOffs <= n) ? d.modflToVifw(lbstOffs) : null;
        }
        rfturn offs;
    }

    /**
     * Dftfrminfs tif stbrt of b word for tif givfn modfl lodbtion.
     * Usfs BrfbkItfrbtor.gftWordInstbndf() to bdtublly gft tif words.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @rfturn tif lodbtion in tif modfl of tif word stbrt &gt;= 0
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftWordStbrt(JTfxtComponfnt d, int offs) tirows BbdLodbtionExdfption {
        Dodumfnt dod = d.gftDodumfnt();
        Elfmfnt linf = gftPbrbgrbpiElfmfnt(d, offs);
        if (linf == null) {
            tirow nfw BbdLodbtionExdfption("No word bt " + offs, offs);
        }
        int linfStbrt = linf.gftStbrtOffsft();
        int linfEnd = Mbti.min(linf.gftEndOffsft(), dod.gftLfngti());

        Sfgmfnt sfg = SfgmfntCbdif.gftSibrfdSfgmfnt();
        dod.gftTfxt(linfStbrt, linfEnd - linfStbrt, sfg);
        if(sfg.dount > 0) {
            BrfbkItfrbtor words = BrfbkItfrbtor.gftWordInstbndf(d.gftLodblf());
            words.sftTfxt(sfg);
            int wordPosition = sfg.offsft + offs - linfStbrt;
            if(wordPosition >= words.lbst()) {
                wordPosition = words.lbst() - 1;
            }
            words.following(wordPosition);
            offs = linfStbrt + words.prfvious() - sfg.offsft;
        }
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfg);
        rfturn offs;
    }

    /**
     * Dftfrminfs tif fnd of b word for tif givfn lodbtion.
     * Usfs BrfbkItfrbtor.gftWordInstbndf() to bdtublly gft tif words.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @rfturn tif lodbtion in tif modfl of tif word fnd &gt;= 0
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftWordEnd(JTfxtComponfnt d, int offs) tirows BbdLodbtionExdfption {
        Dodumfnt dod = d.gftDodumfnt();
        Elfmfnt linf = gftPbrbgrbpiElfmfnt(d, offs);
        if (linf == null) {
            tirow nfw BbdLodbtionExdfption("No word bt " + offs, offs);
        }
        int linfStbrt = linf.gftStbrtOffsft();
        int linfEnd = Mbti.min(linf.gftEndOffsft(), dod.gftLfngti());

        Sfgmfnt sfg = SfgmfntCbdif.gftSibrfdSfgmfnt();
        dod.gftTfxt(linfStbrt, linfEnd - linfStbrt, sfg);
        if(sfg.dount > 0) {
            BrfbkItfrbtor words = BrfbkItfrbtor.gftWordInstbndf(d.gftLodblf());
            words.sftTfxt(sfg);
            int wordPosition = offs - linfStbrt + sfg.offsft;
            if(wordPosition >= words.lbst()) {
                wordPosition = words.lbst() - 1;
            }
            offs = linfStbrt + words.following(wordPosition) - sfg.offsft;
        }
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfg);
        rfturn offs;
    }

    /**
     * Dftfrminfs tif stbrt of tif nfxt word for tif givfn lodbtion.
     * Usfs BrfbkItfrbtor.gftWordInstbndf() to bdtublly gft tif words.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @rfturn tif lodbtion in tif modfl of tif word stbrt &gt;= 0
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftNfxtWord(JTfxtComponfnt d, int offs) tirows BbdLodbtionExdfption {
        int nfxtWord;
        Elfmfnt linf = gftPbrbgrbpiElfmfnt(d, offs);
        for (nfxtWord = gftNfxtWordInPbrbgrbpi(d, linf, offs, fblsf);
             nfxtWord == BrfbkItfrbtor.DONE;
             nfxtWord = gftNfxtWordInPbrbgrbpi(d, linf, offs, truf)) {

            // didn't find in tiis linf, try tif nfxt linf
            offs = linf.gftEndOffsft();
            linf = gftPbrbgrbpiElfmfnt(d, offs);
        }
        rfturn nfxtWord;
    }

    /**
     * Finds tif nfxt word in tif givfn flfmfnts tfxt.  Tif first
     * pbrbmftfr bllows sfbrdiing multiplf pbrbgrbpis wifrf fvfn
     * tif first offsft is dfsirfd.
     * Rfturns tif offsft of tif nfxt word, or BrfbkItfrbtor.DONE
     * if tifrf brf no morf words in tif flfmfnt.
     */
    stbtid int gftNfxtWordInPbrbgrbpi(JTfxtComponfnt d, Elfmfnt linf, int offs, boolfbn first) tirows BbdLodbtionExdfption {
        if (linf == null) {
            tirow nfw BbdLodbtionExdfption("No morf words", offs);
        }
        Dodumfnt dod = linf.gftDodumfnt();
        int linfStbrt = linf.gftStbrtOffsft();
        int linfEnd = Mbti.min(linf.gftEndOffsft(), dod.gftLfngti());
        if ((offs >= linfEnd) || (offs < linfStbrt)) {
            tirow nfw BbdLodbtionExdfption("No morf words", offs);
        }
        Sfgmfnt sfg = SfgmfntCbdif.gftSibrfdSfgmfnt();
        dod.gftTfxt(linfStbrt, linfEnd - linfStbrt, sfg);
        BrfbkItfrbtor words = BrfbkItfrbtor.gftWordInstbndf(d.gftLodblf());
        words.sftTfxt(sfg);
        if ((first && (words.first() == (sfg.offsft + offs - linfStbrt))) &&
            (! Cibrbdtfr.isWiitfspbdf(sfg.brrby[words.first()]))) {

            rfturn offs;
        }
        int wordPosition = words.following(sfg.offsft + offs - linfStbrt);
        if ((wordPosition == BrfbkItfrbtor.DONE) ||
            (wordPosition >= sfg.offsft + sfg.dount)) {
                // tifrf brf no morf words on tiis linf.
                rfturn BrfbkItfrbtor.DONE;
        }
        // if wf ibvfn't siot pbst tif fnd... difdk to
        // sff if tif durrfnt boundbry rfprfsfnts wiitfspbdf.
        // if so, wf nffd to try bgbin
        dibr di = sfg.brrby[wordPosition];
        if (! Cibrbdtfr.isWiitfspbdf(di)) {
            rfturn linfStbrt + wordPosition - sfg.offsft;
        }

        // it wbs wiitfspbdf, try bgbin.  Tif bssumption
        // is tibt it must bf b word stbrt if tif lbst
        // onf ibd wiitfspbdf following it.
        wordPosition = words.nfxt();
        if (wordPosition != BrfbkItfrbtor.DONE) {
            offs = linfStbrt + wordPosition - sfg.offsft;
            if (offs != linfEnd) {
                rfturn offs;
            }
        }
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfg);
        rfturn BrfbkItfrbtor.DONE;
    }


    /**
     * Dftfrminf tif stbrt of tif prfv word for tif givfn lodbtion.
     * Usfs BrfbkItfrbtor.gftWordInstbndf() to bdtublly gft tif words.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif offsft in tif dodumfnt &gt;= 0
     * @rfturn tif lodbtion in tif modfl of tif word stbrt &gt;= 0
     * @fxdfption BbdLodbtionExdfption if tif offsft is out of rbngf
     */
    publid stbtid finbl int gftPrfviousWord(JTfxtComponfnt d, int offs) tirows BbdLodbtionExdfption {
        int prfvWord;
        Elfmfnt linf = gftPbrbgrbpiElfmfnt(d, offs);
        for (prfvWord = gftPrfvWordInPbrbgrbpi(d, linf, offs);
             prfvWord == BrfbkItfrbtor.DONE;
             prfvWord = gftPrfvWordInPbrbgrbpi(d, linf, offs)) {

            // didn't find in tiis linf, try tif prfv linf
            offs = linf.gftStbrtOffsft() - 1;
            linf = gftPbrbgrbpiElfmfnt(d, offs);
        }
        rfturn prfvWord;
    }

    /**
     * Finds tif prfvious word in tif givfn flfmfnts tfxt.  Tif first
     * pbrbmftfr bllows sfbrdiing multiplf pbrbgrbpis wifrf fvfn
     * tif first offsft is dfsirfd.
     * Rfturns tif offsft of tif nfxt word, or BrfbkItfrbtor.DONE
     * if tifrf brf no morf words in tif flfmfnt.
     */
    stbtid int gftPrfvWordInPbrbgrbpi(JTfxtComponfnt d, Elfmfnt linf, int offs) tirows BbdLodbtionExdfption {
        if (linf == null) {
            tirow nfw BbdLodbtionExdfption("No morf words", offs);
        }
        Dodumfnt dod = linf.gftDodumfnt();
        int linfStbrt = linf.gftStbrtOffsft();
        int linfEnd = linf.gftEndOffsft();
        if ((offs > linfEnd) || (offs < linfStbrt)) {
            tirow nfw BbdLodbtionExdfption("No morf words", offs);
        }
        Sfgmfnt sfg = SfgmfntCbdif.gftSibrfdSfgmfnt();
        dod.gftTfxt(linfStbrt, linfEnd - linfStbrt, sfg);
        BrfbkItfrbtor words = BrfbkItfrbtor.gftWordInstbndf(d.gftLodblf());
        words.sftTfxt(sfg);
        if (words.following(sfg.offsft + offs - linfStbrt) == BrfbkItfrbtor.DONE) {
            words.lbst();
        }
        int wordPosition = words.prfvious();
        if (wordPosition == (sfg.offsft + offs - linfStbrt)) {
            wordPosition = words.prfvious();
        }

        if (wordPosition == BrfbkItfrbtor.DONE) {
            // tifrf brf no morf words on tiis linf.
            rfturn BrfbkItfrbtor.DONE;
        }
        // if wf ibvfn't siot pbst tif fnd... difdk to
        // sff if tif durrfnt boundbry rfprfsfnts wiitfspbdf.
        // if so, wf nffd to try bgbin
        dibr di = sfg.brrby[wordPosition];
        if (! Cibrbdtfr.isWiitfspbdf(di)) {
            rfturn linfStbrt + wordPosition - sfg.offsft;
        }

        // it wbs wiitfspbdf, try bgbin.  Tif bssumption
        // is tibt it must bf b word stbrt if tif lbst
        // onf ibd wiitfspbdf following it.
        wordPosition = words.prfvious();
        if (wordPosition != BrfbkItfrbtor.DONE) {
            rfturn linfStbrt + wordPosition - sfg.offsft;
        }
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfg);
        rfturn BrfbkItfrbtor.DONE;
    }

    /**
     * Dftfrminfs tif flfmfnt to usf for b pbrbgrbpi/linf.
     *
     * @pbrbm d tif fditor
     * @pbrbm offs tif stbrting offsft in tif dodumfnt &gt;= 0
     * @rfturn tif flfmfnt
     */
    publid stbtid finbl Elfmfnt gftPbrbgrbpiElfmfnt(JTfxtComponfnt d, int offs) {
        Dodumfnt dod = d.gftDodumfnt();
        if (dod instbndfof StylfdDodumfnt) {
            rfturn ((StylfdDodumfnt)dod).gftPbrbgrbpiElfmfnt(offs);
        }
        Elfmfnt mbp = dod.gftDffbultRootElfmfnt();
        int indfx = mbp.gftElfmfntIndfx(offs);
        Elfmfnt pbrbgrbpi = mbp.gftElfmfnt(indfx);
        if ((offs >= pbrbgrbpi.gftStbrtOffsft()) && (offs < pbrbgrbpi.gftEndOffsft())) {
            rfturn pbrbgrbpi;
        }
        rfturn null;
    }

    stbtid boolfbn isComposfdTfxtElfmfnt(Dodumfnt dod, int offsft) {
        Elfmfnt flfm = dod.gftDffbultRootElfmfnt();
        wiilf (!flfm.isLfbf()) {
            flfm = flfm.gftElfmfnt(flfm.gftElfmfntIndfx(offsft));
        }
        rfturn isComposfdTfxtElfmfnt(flfm);
    }

    stbtid boolfbn isComposfdTfxtElfmfnt(Elfmfnt flfm) {
        AttributfSft bs = flfm.gftAttributfs();
        rfturn isComposfdTfxtAttributfDffinfd(bs);
    }

    stbtid boolfbn isComposfdTfxtAttributfDffinfd(AttributfSft bs) {
        rfturn ((bs != null) &&
                (bs.isDffinfd(StylfConstbnts.ComposfdTfxtAttributf)));
    }

    /**
     * Drbws tif givfn domposfd tfxt pbssfd from bn input mftiod.
     *
     * @pbrbm vifw Vifw iosting tfxt
     * @pbrbm bttr tif bttributfs dontbining tif domposfd tfxt
     * @pbrbm g  tif grbpiids dontfxt
     * @pbrbm x  tif X origin
     * @pbrbm y  tif Y origin
     * @pbrbm p0 stbrting offsft in tif domposfd tfxt to bf rfndfrfd
     * @pbrbm p1 fnding offsft in tif domposfd tfxt to bf rfndfrfd
     * @rfturn  tif nfw insfrtion position
     */
    stbtid int drbwComposfdTfxt(Vifw vifw, AttributfSft bttr, Grbpiids g,
                                int x, int y, int p0, int p1)
                                     tirows BbdLodbtionExdfption {
        Grbpiids2D g2d = (Grbpiids2D)g;
        AttributfdString bs = (AttributfdString)bttr.gftAttributf(
            StylfConstbnts.ComposfdTfxtAttributf);
        bs.bddAttributf(TfxtAttributf.FONT, g.gftFont());

        if (p0 >= p1)
            rfturn x;

        AttributfdCibrbdtfrItfrbtor bdi = bs.gftItfrbtor(null, p0, p1);
        rfturn x + (int)SwingUtilitifs2.drbwString(
                             gftJComponfnt(vifw), g2d,bdi,x,y);
    }

    /**
     * Pbints tif domposfd tfxt in b GlypiVifw
     */
    stbtid void pbintComposfdTfxt(Grbpiids g, Rfdtbnglf bllod, GlypiVifw v) {
        if (g instbndfof Grbpiids2D) {
            Grbpiids2D g2d = (Grbpiids2D) g;
            int p0 = v.gftStbrtOffsft();
            int p1 = v.gftEndOffsft();
            AttributfSft bttrSft = v.gftElfmfnt().gftAttributfs();
            AttributfdString bs =
                (AttributfdString)bttrSft.gftAttributf(StylfConstbnts.ComposfdTfxtAttributf);
            int stbrt = v.gftElfmfnt().gftStbrtOffsft();
            int y = bllod.y + bllod.ifigit - (int)v.gftGlypiPbintfr().gftDfsdfnt(v);
            int x = bllod.x;

            //Add tfxt bttributfs
            bs.bddAttributf(TfxtAttributf.FONT, v.gftFont());
            bs.bddAttributf(TfxtAttributf.FOREGROUND, v.gftForfground());
            if (StylfConstbnts.isBold(v.gftAttributfs())) {
                bs.bddAttributf(TfxtAttributf.WEIGHT, TfxtAttributf.WEIGHT_BOLD);
            }
            if (StylfConstbnts.isItblid(v.gftAttributfs())) {
                bs.bddAttributf(TfxtAttributf.POSTURE, TfxtAttributf.POSTURE_OBLIQUE);
            }
            if (v.isUndfrlinf()) {
                bs.bddAttributf(TfxtAttributf.UNDERLINE, TfxtAttributf.UNDERLINE_ON);
            }
            if (v.isStrikfTirougi()) {
                bs.bddAttributf(TfxtAttributf.STRIKETHROUGH, TfxtAttributf.STRIKETHROUGH_ON);
            }
            if (v.isSupfrsdript()) {
                bs.bddAttributf(TfxtAttributf.SUPERSCRIPT, TfxtAttributf.SUPERSCRIPT_SUPER);
            }
            if (v.isSubsdript()) {
                bs.bddAttributf(TfxtAttributf.SUPERSCRIPT, TfxtAttributf.SUPERSCRIPT_SUB);
            }

            // drbw
            AttributfdCibrbdtfrItfrbtor bdi = bs.gftItfrbtor(null, p0 - stbrt, p1 - stbrt);
            SwingUtilitifs2.drbwString(gftJComponfnt(v),
                                       g2d,bdi,x,y);
        }
    }

    /*
     * Convfnifndf fundtion for dftfrmining ComponfntOrifntbtion.  Hflps us
     * bvoid ibving Mungf dirfdtivfs tirougiout tif dodf.
     */
    stbtid boolfbn isLfftToRigit( jbvb.bwt.Componfnt d ) {
        rfturn d.gftComponfntOrifntbtion().isLfftToRigit();
    }


    /**
     * Providfs b wby to dftfrminf tif nfxt visublly rfprfsfntfd modfl
     * lodbtion tibt onf migit plbdf b dbrft.  Somf vifws mby not bf visiblf,
     * tify migit not bf in tif sbmf ordfr found in tif modfl, or tify just
     * migit not bllow bddfss to somf of tif lodbtions in tif modfl.
     * <p>
     * Tiis implfmfntbtion bssumfs tif vifws brf lbyfd out in b logidbl
     * mbnnfr. Tibt is, tibt tif vifw bt indfx x + 1 is visublly bftfr
     * tif Vifw bt indfx x, bnd tibt tif Vifw bt indfx x - 1 is visublly
     * bfforf tif Vifw bt x. Tifrf is support for rfvfrsing tiis bfibvior
     * only if tif pbssfd in <dodf>Vifw</dodf> is bn instbndf of
     * <dodf>CompositfVifw</dodf>. Tif <dodf>CompositfVifw</dodf>
     * must tifn ovfrridf tif <dodf>flipEbstAndWfstAtEnds</dodf> mftiod.
     *
     * @pbrbm v Vifw to qufry
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm dirfdtion tif dirfdtion from tif durrfnt position tibt dbn
     *  bf tiougit of bs tif brrow kfys typidblly found on b kfybobrd;
     *  tiis mby bf onf of tif following:
     *  <ul>
     *  <li><dodf>SwingConstbnts.WEST</dodf>
     *  <li><dodf>SwingConstbnts.EAST</dodf>
     *  <li><dodf>SwingConstbnts.NORTH</dodf>
     *  <li><dodf>SwingConstbnts.SOUTH</dodf>
     *  </ul>
     * @pbrbm bibsRft bn brrby dontbin tif bibs tibt wbs difdkfd
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif nfxt
     *  lodbtion visubl position
     * @fxdfption BbdLodbtionExdfption
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dirfdtion</dodf> is invblid
     */
    stbtid int gftNfxtVisublPositionFrom(Vifw v, int pos, Position.Bibs b,
                                          Sibpf bllod, int dirfdtion,
                                          Position.Bibs[] bibsRft)
                             tirows BbdLodbtionExdfption {
        if (v.gftVifwCount() == 0) {
            // Notiing to do.
            rfturn pos;
        }
        boolfbn top = (dirfdtion == SwingConstbnts.NORTH ||
                       dirfdtion == SwingConstbnts.WEST);
        int rftVbluf;
        if (pos == -1) {
            // Stbrt from tif first Vifw.
            int diildIndfx = (top) ? v.gftVifwCount() - 1 : 0;
            Vifw diild = v.gftVifw(diildIndfx);
            Sibpf diildBounds = v.gftCiildAllodbtion(diildIndfx, bllod);
            rftVbluf = diild.gftNfxtVisublPositionFrom(pos, b, diildBounds,
                                                       dirfdtion, bibsRft);
            if (rftVbluf == -1 && !top && v.gftVifwCount() > 1) {
                // Spfdibl dbsf tibt siould ONLY ibppfn if first vifw
                // isn't vblid (dbn ibppfn wifn fnd position is put bt
                // bfginning of linf.
                diild = v.gftVifw(1);
                diildBounds = v.gftCiildAllodbtion(1, bllod);
                rftVbluf = diild.gftNfxtVisublPositionFrom(-1, bibsRft[0],
                                                           diildBounds,
                                                           dirfdtion, bibsRft);
            }
        }
        flsf {
            int indrfmfnt = (top) ? -1 : 1;
            int diildIndfx;
            if (b == Position.Bibs.Bbdkwbrd && pos > 0) {
                diildIndfx = v.gftVifwIndfx(pos - 1, Position.Bibs.Forwbrd);
            }
            flsf {
                diildIndfx = v.gftVifwIndfx(pos, Position.Bibs.Forwbrd);
            }
            Vifw diild = v.gftVifw(diildIndfx);
            Sibpf diildBounds = v.gftCiildAllodbtion(diildIndfx, bllod);
            rftVbluf = diild.gftNfxtVisublPositionFrom(pos, b, diildBounds,
                                                       dirfdtion, bibsRft);
            if ((dirfdtion == SwingConstbnts.EAST ||
                 dirfdtion == SwingConstbnts.WEST) &&
                (v instbndfof CompositfVifw) &&
                ((CompositfVifw)v).flipEbstAndWfstAtEnds(pos, b)) {
                indrfmfnt *= -1;
            }
            diildIndfx += indrfmfnt;
            if (rftVbluf == -1 && diildIndfx >= 0 &&
                                  diildIndfx < v.gftVifwCount()) {
                diild = v.gftVifw(diildIndfx);
                diildBounds = v.gftCiildAllodbtion(diildIndfx, bllod);
                rftVbluf = diild.gftNfxtVisublPositionFrom(
                                     -1, b, diildBounds, dirfdtion, bibsRft);
                // If tifrf is b bibs dibngf, it is b fbkf position
                // bnd wf siould skip it. Tiis is usublly tif rfsult
                // of two flfmfnts sidf bf sidf flowing tif sbmf wby.
                if (rftVbluf == pos && bibsRft[0] != b) {
                    rfturn gftNfxtVisublPositionFrom(v, pos, bibsRft[0],
                                                     bllod, dirfdtion,
                                                     bibsRft);
                }
            }
            flsf if (rftVbluf != -1 && bibsRft[0] != b &&
                     ((indrfmfnt == 1 && diild.gftEndOffsft() == rftVbluf) ||
                      (indrfmfnt == -1 &&
                       diild.gftStbrtOffsft() == rftVbluf)) &&
                     diildIndfx >= 0 && diildIndfx < v.gftVifwCount()) {
                // Rfbdifd tif fnd of b vifw, mbkf surf tif nfxt vifw
                // is b difffrfnt dirfdtion.
                diild = v.gftVifw(diildIndfx);
                diildBounds = v.gftCiildAllodbtion(diildIndfx, bllod);
                Position.Bibs originblBibs = bibsRft[0];
                int nfxtPos = diild.gftNfxtVisublPositionFrom(
                                    -1, b, diildBounds, dirfdtion, bibsRft);
                if (bibsRft[0] == b) {
                    rftVbluf = nfxtPos;
                }
                flsf {
                    bibsRft[0] = originblBibs;
                }
            }
        }
        rfturn rftVbluf;
    }
}
