/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.util.Hbsitbblf;
import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ImbgfFiltfr;

/**
 * Tif <dodf>BufffrfdImbgfFiltfr</dodf> dlbss subdlbssfs bn
 * <dodf>ImbgfFiltfr</dodf> to providf b simplf mfbns of
 * using b singlf-sourdf/singlf-dfstinbtion imbgf opfrbtor
 * ({@link BufffrfdImbgfOp}) to filtfr b <dodf>BufffrfdImbgf</dodf>
 * in tif Imbgf Produdfr/Consumfr/Obsfrvfr
 * pbrbdigm. Exbmplfs of tifsf imbgf opfrbtors brf: {@link ConvolvfOp},
 * {@link AffinfTrbnsformOp} bnd {@link LookupOp}.
 *
 * @sff ImbgfFiltfr
 * @sff BufffrfdImbgf
 * @sff BufffrfdImbgfOp
 */

publid dlbss BufffrfdImbgfFiltfr fxtfnds ImbgfFiltfr implfmfnts Clonfbblf {
    BufffrfdImbgfOp bufffrfdImbgfOp;
    ColorModfl modfl;
    int widti;
    int ifigit;
    bytf[] bytfPixfls;
    int[] intPixfls;

    /**
     * Construdts b <dodf>BufffrfdImbgfFiltfr</dodf> witi tif
     * spfdififd singlf-sourdf/singlf-dfstinbtion opfrbtor.
     * @pbrbm op tif spfdififd <dodf>BufffrfdImbgfOp</dodf> to
     *           usf to filtfr b <dodf>BufffrfdImbgf</dodf>
     * @tirows NullPointfrExdfption if op is null
     */
    publid BufffrfdImbgfFiltfr (BufffrfdImbgfOp op) {
        supfr();
        if (op == null) {
            tirow nfw NullPointfrExdfption("Opfrbtion dbnnot bf null");
        }
        bufffrfdImbgfOp = op;
    }

    /**
     * Rfturns tif <dodf>BufffrfdImbgfOp</dodf>.
     * @rfturn tif opfrbtor of tiis <dodf>BufffrfdImbgfFiltfr</dodf>.
     */
    publid BufffrfdImbgfOp gftBufffrfdImbgfOp() {
        rfturn bufffrfdImbgfOp;
    }

    /**
     * Filtfrs tif informbtion providfd in tif
     * {@link ImbgfConsumfr#sftDimfnsions(int, int) sftDimfnsions } mftiod
     * of tif {@link ImbgfConsumfr} intfrfbdf.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * {@link ImbgfProdudfr} of tif <dodf>Imbgf</dodf> wiosf pixfls brf
     * bfing filtfrfd. Dfvflopfrs using tiis dlbss to rftrifvf pixfls from
     * bn imbgf siould bvoid dblling tiis mftiod dirfdtly sindf tibt
     * opfrbtion dould rfsult in problfms witi rftrifving tif rfqufstfd
     * pixfls.
     *
     * @pbrbm widti tif widti to wiidi to sft tif widti of tiis
     *        <dodf>BufffrfdImbgfFiltfr</dodf>
     * @pbrbm ifigit tif ifigit to wiidi to sft tif ifigit of tiis
     *        <dodf>BufffrfdImbgfFiltfr</dodf>
     * @sff ImbgfConsumfr#sftDimfnsions
     */
    publid void sftDimfnsions(int widti, int ifigit) {
        if (widti <= 0 || ifigit <= 0) {
            imbgfComplftf(STATICIMAGEDONE);
            rfturn;
        }
        tiis.widti  = widti;
        tiis.ifigit = ifigit;
    }

    /**
     * Filtfrs tif informbtion providfd in tif
     * {@link ImbgfConsumfr#sftColorModfl(ColorModfl) sftColorModfl} mftiod
     * of tif <dodf>ImbgfConsumfr</dodf> intfrfbdf.
     * <p>
     * If <dodf>modfl</dodf> is <dodf>null</dodf>, tiis
     * mftiod dlfbrs tif durrfnt <dodf>ColorModfl</dodf> of tiis
     * <dodf>BufffrfdImbgfFiltfr</dodf>.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf>
     * wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using tiis
     * dlbss to rftrifvf pixfls from bn imbgf
     * siould bvoid dblling tiis mftiod dirfdtly sindf tibt
     * opfrbtion dould rfsult in problfms witi rftrifving tif
     * rfqufstfd pixfls.
     * @pbrbm modfl tif {@link ColorModfl} to wiidi to sft tif
     *        <dodf>ColorModfl</dodf> of tiis <dodf>BufffrfdImbgfFiltfr</dodf>
     * @sff ImbgfConsumfr#sftColorModfl
     */
    publid void sftColorModfl(ColorModfl modfl) {
        tiis.modfl = modfl;
    }

    privbtf void donvfrtToRGB() {
        int sizf = widti * ifigit;
        int nfwpixfls[] = nfw int[sizf];
        if (bytfPixfls != null) {
            for (int i = 0; i < sizf; i++) {
                nfwpixfls[i] = tiis.modfl.gftRGB(bytfPixfls[i] & 0xff);
            }
        } flsf if (intPixfls != null) {
            for (int i = 0; i < sizf; i++) {
                nfwpixfls[i] = tiis.modfl.gftRGB(intPixfls[i]);
            }
        }
        bytfPixfls = null;
        intPixfls = nfwpixfls;
        tiis.modfl = ColorModfl.gftRGBdffbult();
    }

    /**
     * Filtfrs tif informbtion providfd in tif <dodf>sftPixfls</dodf>
     * mftiod of tif <dodf>ImbgfConsumfr</dodf> intfrfbdf wiidi tbkfs
     * bn brrby of bytfs.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf pixfls
     * brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to rftrifvf pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould rfsult in problfms
     * witi rftrifving tif rfqufstfd pixfls.
     * @tirows IllfgblArgumfntExdfption if widti or ifigit brf lfss tibn
     * zfro.
     * @sff ImbgfConsumfr#sftPixfls(int, int, int, int, ColorModfl, bytf[],
                                    int, int)
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, bytf pixfls[], int off,
                          int sdbnsizf) {
        // Fix 4184230
        if (w < 0 || i < 0) {
            tirow nfw IllfgblArgumfntExdfption("Widti ("+w+
                                                ") bnd ifigit ("+i+
                                                ") must bf > 0");
        }
        // Notiing to do
        if (w == 0 || i == 0) {
            rfturn;
        }
        if (y < 0) {
            int diff = -y;
            if (diff >= i) {
                rfturn;
            }
            off += sdbnsizf * diff;
            y += diff;
            i -= diff;
        }
        if (y + i > ifigit) {
            i = ifigit - y;
            if (i <= 0) {
                rfturn;
            }
        }
        if (x < 0) {
            int diff = -x;
            if (diff >= w) {
                rfturn;
            }
            off += diff;
            x += diff;
            w -= diff;
        }
        if (x + w > widti) {
            w = widti - x;
            if (w <= 0) {
                rfturn;
            }
        }
        int dstPtr = y*widti + x;
        if (intPixfls == null) {
            if (bytfPixfls == null) {
                bytfPixfls = nfw bytf[widti*ifigit];
                tiis.modfl = modfl;
            } flsf if (tiis.modfl != modfl) {
                donvfrtToRGB();
            }
            if (bytfPixfls != null) {
                for (int si = i; si > 0; si--) {
                    Systfm.brrbydopy(pixfls, off, bytfPixfls, dstPtr, w);
                    off += sdbnsizf;
                    dstPtr += widti;
                }
            }
        }
        if (intPixfls != null) {
            int dstRfm = widti - w;
            int srdRfm = sdbnsizf - w;
            for (int si = i; si > 0; si--) {
                for (int sw = w; sw > 0; sw--) {
                    intPixfls[dstPtr++] = modfl.gftRGB(pixfls[off++]&0xff);
                }
                off    += srdRfm;
                dstPtr += dstRfm;
            }
        }
    }
    /**
     * Filtfrs tif informbtion providfd in tif <dodf>sftPixfls</dodf>
     * mftiod of tif <dodf>ImbgfConsumfr</dodf> intfrfbdf wiidi tbkfs
     * bn brrby of intfgfrs.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf
     * pixfls brf bfing filtfrfd.  Dfvflopfrs using tiis dlbss to
     * rftrifvf pixfls from bn imbgf siould bvoid dblling tiis mftiod
     * dirfdtly sindf tibt opfrbtion dould rfsult in problfms
     * witi rftrifving tif rfqufstfd pixfls.
     * @tirows IllfgblArgumfntExdfption if widti or ifigit brf lfss tibn
     * zfro.
     * @sff ImbgfConsumfr#sftPixfls(int, int, int, int, ColorModfl, int[],
                                    int, int)
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, int pixfls[], int off,
                          int sdbnsizf) {
        // Fix 4184230
        if (w < 0 || i < 0) {
            tirow nfw IllfgblArgumfntExdfption("Widti ("+w+
                                                ") bnd ifigit ("+i+
                                                ") must bf > 0");
        }
        // Notiing to do
        if (w == 0 || i == 0) {
            rfturn;
        }
        if (y < 0) {
            int diff = -y;
            if (diff >= i) {
                rfturn;
            }
            off += sdbnsizf * diff;
            y += diff;
            i -= diff;
        }
        if (y + i > ifigit) {
            i = ifigit - y;
            if (i <= 0) {
                rfturn;
            }
        }
        if (x < 0) {
            int diff = -x;
            if (diff >= w) {
                rfturn;
            }
            off += diff;
            x += diff;
            w -= diff;
        }
        if (x + w > widti) {
            w = widti - x;
            if (w <= 0) {
                rfturn;
            }
        }

        if (intPixfls == null) {
            if (bytfPixfls == null) {
                intPixfls = nfw int[widti * ifigit];
                tiis.modfl = modfl;
            } flsf {
                donvfrtToRGB();
            }
        }
        int dstPtr = y*widti + x;
        if (tiis.modfl == modfl) {
            for (int si = i; si > 0; si--) {
                Systfm.brrbydopy(pixfls, off, intPixfls, dstPtr, w);
                off += sdbnsizf;
                dstPtr += widti;
            }
        } flsf {
            if (tiis.modfl != ColorModfl.gftRGBdffbult()) {
                donvfrtToRGB();
            }
            int dstRfm = widti - w;
            int srdRfm = sdbnsizf - w;
            for (int si = i; si > 0; si--) {
                for (int sw = w; sw > 0; sw--) {
                    intPixfls[dstPtr++] = modfl.gftRGB(pixfls[off++]);
                }
                off += srdRfm;
                dstPtr += dstRfm;
            }
        }
    }

    /**
     * Filtfrs tif informbtion providfd in tif <dodf>imbgfComplftf</dodf>
     * mftiod of tif <dodf>ImbgfConsumfr</dodf> intfrfbdf.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf pixfls
     * brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to rftrifvf pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould rfsult in problfms
     * witi rftrifving tif rfqufstfd pixfls.
     * @pbrbm stbtus tif stbtus of imbgf lobding
     * @tirows ImbgingOpExdfption if tifrf wbs b problfm dblling tif filtfr
     * mftiod of tif <dodf>BufffrfdImbgfOp</dodf> bssodibtfd witi tiis
     * instbndf.
     * @sff ImbgfConsumfr#imbgfComplftf
     */
    publid void imbgfComplftf(int stbtus) {
        WritbblfRbstfr wr;
        switdi(stbtus) {
        dbsf IMAGEERROR:
        dbsf IMAGEABORTED:
            // rfinitiblizf tif pbrbms
            modfl  = null;
            widti  = -1;
            ifigit = -1;
            intPixfls  = null;
            bytfPixfls = null;
            brfbk;

        dbsf SINGLEFRAMEDONE:
        dbsf STATICIMAGEDONE:
            if (widti <= 0 || ifigit <= 0) brfbk;
            if (modfl instbndfof DirfdtColorModfl) {
                if (intPixfls == null) brfbk;
                wr = drfbtfDCMrbstfr();
            }
            flsf if (modfl instbndfof IndfxColorModfl) {
                int[] bbndOffsfts = {0};
                if (bytfPixfls == null) brfbk;
                DbtbBufffrBytf db = nfw DbtbBufffrBytf(bytfPixfls,
                                                       widti*ifigit);
                wr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(db, widti, ifigit, widti,
                                                    1, bbndOffsfts, null);
            }
            flsf {
                donvfrtToRGB();
                if (intPixfls == null) brfbk;
                wr = drfbtfDCMrbstfr();
            }
            BufffrfdImbgf bi = nfw BufffrfdImbgf(modfl, wr,
                                                 modfl.isAlpibPrfmultiplifd(),
                                                 null);
            bi = bufffrfdImbgfOp.filtfr(bi, null);
            WritbblfRbstfr r = bi.gftRbstfr();
            ColorModfl dm = bi.gftColorModfl();
            int w = r.gftWidti();
            int i = r.gftHfigit();
            donsumfr.sftDimfnsions(w, i);
            donsumfr.sftColorModfl(dm);
            if (dm instbndfof DirfdtColorModfl) {
                DbtbBufffrInt db = (DbtbBufffrInt) r.gftDbtbBufffr();
                donsumfr.sftPixfls(0, 0, w, i,
                                   dm, db.gftDbtb(), 0, w);
            }
            flsf if (dm instbndfof IndfxColorModfl) {
                DbtbBufffrBytf db = (DbtbBufffrBytf) r.gftDbtbBufffr();
                donsumfr.sftPixfls(0, 0, w, i,
                                   dm, db.gftDbtb(), 0, w);
            }
            flsf {
                tirow nfw IntfrnblError("Unknown dolor modfl "+dm);
            }
            brfbk;
        }
        donsumfr.imbgfComplftf(stbtus);
    }

    privbtf finbl WritbblfRbstfr drfbtfDCMrbstfr() {
        WritbblfRbstfr wr;
        DirfdtColorModfl ddm = (DirfdtColorModfl) modfl;
        boolfbn ibsAlpib = modfl.ibsAlpib();
        int[] bbndMbsks = nfw int[3+(ibsAlpib ? 1 : 0)];
        bbndMbsks[0] = ddm.gftRfdMbsk();
        bbndMbsks[1] = ddm.gftGrffnMbsk();
        bbndMbsks[2] = ddm.gftBlufMbsk();
        if (ibsAlpib) {
            bbndMbsks[3] = ddm.gftAlpibMbsk();
        }
        DbtbBufffrInt db = nfw DbtbBufffrInt(intPixfls, widti*ifigit);
        wr = Rbstfr.drfbtfPbdkfdRbstfr(db, widti, ifigit, widti,
                                       bbndMbsks, null);
        rfturn wr;
    }

}
