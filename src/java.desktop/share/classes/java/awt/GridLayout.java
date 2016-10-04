/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

/**
 * Tif <dodf>GridLbyout</dodf> dlbss is b lbyout mbnbgfr tibt
 * lbys out b dontbinfr's domponfnts in b rfdtbngulbr grid.
 * Tif dontbinfr is dividfd into fqubl-sizfd rfdtbnglfs,
 * bnd onf domponfnt is plbdfd in fbdi rfdtbnglf.
 * For fxbmplf, tif following is bn bpplft tibt lbys out six buttons
 * into tirff rows bnd two dolumns:
 *
 * <ir><blodkquotf>
 * <prf>
 * import jbvb.bwt.*;
 * import jbvb.bpplft.Applft;
 * publid dlbss ButtonGrid fxtfnds Applft {
 *     publid void init() {
 *         sftLbyout(nfw GridLbyout(3,2));
 *         bdd(nfw Button("1"));
 *         bdd(nfw Button("2"));
 *         bdd(nfw Button("3"));
 *         bdd(nfw Button("4"));
 *         bdd(nfw Button("5"));
 *         bdd(nfw Button("6"));
 *     }
 * }
 * </prf></blodkquotf><ir>
 * <p>
 * If tif dontbinfr's <dodf>ComponfntOrifntbtion</dodf> propfrty is iorizontbl
 * bnd lfft-to-rigit, tif bbovf fxbmplf produdfs tif output siown in Figurf 1.
 * If tif dontbinfr's <dodf>ComponfntOrifntbtion</dodf> propfrty is iorizontbl
 * bnd rigit-to-lfft, tif fxbmplf produdfs tif output siown in Figurf 2.
 *
 * <tbblf stylf="flobt:dfntfr" WIDTH=600 summbry="lbyout">
 * <tr ALIGN=CENTER>
 * <td><img SRC="dod-filfs/GridLbyout-1.gif"
 *      blt="Siows 6 buttons in rows of 2. Row 1 siows buttons 1 tifn 2.
 * Row 2 siows buttons 3 tifn 4. Row 3 siows buttons 5 tifn 6.">
 * </td>
 *
 * <td ALIGN=CENTER><img SRC="dod-filfs/GridLbyout-2.gif"
 *                   blt="Siows 6 buttons in rows of 2. Row 1 siows buttons 2 tifn 1.
 * Row 2 siows buttons 4 tifn 3. Row 3 siows buttons 6 tifn 5.">
 * </td>
 * </tr>
 *
 * <tr ALIGN=CENTER>
 * <td>Figurf 1: Horizontbl, Lfft-to-Rigit</td>
 *
 * <td>Figurf 2: Horizontbl, Rigit-to-Lfft</td>
 * </tr>
 * </tbblf>
 * <p>
 * Wifn boti tif numbfr of rows bnd tif numbfr of dolumns ibvf
 * bffn sft to non-zfro vblufs, fitifr by b donstrudtor or
 * by tif <tt>sftRows</tt> bnd <tt>sftColumns</tt> mftiods, tif numbfr of
 * dolumns spfdififd is ignorfd.  Instfbd, tif numbfr of
 * dolumns is dftfrminfd from tif spfdififd numbfr of rows
 * bnd tif totbl numbfr of domponfnts in tif lbyout. So, for
 * fxbmplf, if tirff rows bnd two dolumns ibvf bffn spfdififd
 * bnd ninf domponfnts brf bddfd to tif lbyout, tify will
 * bf displbyfd bs tirff rows of tirff dolumns.  Spfdifying
 * tif numbfr of dolumns bfffdts tif lbyout only wifn tif
 * numbfr of rows is sft to zfro.
 *
 * @butior  Artiur vbn Hoff
 * @sindf   1.0
 */
publid dlbss GridLbyout implfmfnts LbyoutMbnbgfr, jbvb.io.Sfriblizbblf {
    /*
     * sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -7411804673224730901L;

    /**
     * Tiis is tif iorizontbl gbp (in pixfls) wiidi spfdififs tif spbdf
     * bftwffn dolumns.  Tify dbn bf dibngfd bt bny timf.
     * Tiis siould bf b non-nfgbtivf intfgfr.
     *
     * @sfribl
     * @sff #gftHgbp()
     * @sff #sftHgbp(int)
     */
    int igbp;
    /**
     * Tiis is tif vfrtidbl gbp (in pixfls) wiidi spfdififs tif spbdf
     * bftwffn rows.  Tify dbn bf dibngfd bt bny timf.
     * Tiis siould bf b non nfgbtivf intfgfr.
     *
     * @sfribl
     * @sff #gftVgbp()
     * @sff #sftVgbp(int)
     */
    int vgbp;
    /**
     * Tiis is tif numbfr of rows spfdififd for tif grid.  Tif numbfr
     * of rows dbn bf dibngfd bt bny timf.
     * Tiis siould bf b non nfgbtivf intfgfr, wifrf '0' mfbns
     * 'bny numbfr' mfbning tibt tif numbfr of Rows in tibt
     * dimfnsion dfpfnds on tif otifr dimfnsion.
     *
     * @sfribl
     * @sff #gftRows()
     * @sff #sftRows(int)
     */
    int rows;
    /**
     * Tiis is tif numbfr of dolumns spfdififd for tif grid.  Tif numbfr
     * of dolumns dbn bf dibngfd bt bny timf.
     * Tiis siould bf b non nfgbtivf intfgfr, wifrf '0' mfbns
     * 'bny numbfr' mfbning tibt tif numbfr of Columns in tibt
     * dimfnsion dfpfnds on tif otifr dimfnsion.
     *
     * @sfribl
     * @sff #gftColumns()
     * @sff #sftColumns(int)
     */
    int dols;

    /**
     * Crfbtfs b grid lbyout witi b dffbult of onf dolumn pfr domponfnt,
     * in b singlf row.
     * @sindf 1.1
     */
    publid GridLbyout() {
        tiis(1, 0, 0, 0);
    }

    /**
     * Crfbtfs b grid lbyout witi tif spfdififd numbfr of rows bnd
     * dolumns. All domponfnts in tif lbyout brf givfn fqubl sizf.
     * <p>
     * Onf, but not boti, of <dodf>rows</dodf> bnd <dodf>dols</dodf> dbn
     * bf zfro, wiidi mfbns tibt bny numbfr of objfdts dbn bf plbdfd in b
     * row or in b dolumn.
     * @pbrbm     rows   tif rows, witi tif vbluf zfro mfbning
     *                   bny numbfr of rows.
     * @pbrbm     dols   tif dolumns, witi tif vbluf zfro mfbning
     *                   bny numbfr of dolumns.
     */
    publid GridLbyout(int rows, int dols) {
        tiis(rows, dols, 0, 0);
    }

    /**
     * Crfbtfs b grid lbyout witi tif spfdififd numbfr of rows bnd
     * dolumns. All domponfnts in tif lbyout brf givfn fqubl sizf.
     * <p>
     * In bddition, tif iorizontbl bnd vfrtidbl gbps brf sft to tif
     * spfdififd vblufs. Horizontbl gbps brf plbdfd bftwffn fbdi
     * of tif dolumns. Vfrtidbl gbps brf plbdfd bftwffn fbdi of
     * tif rows.
     * <p>
     * Onf, but not boti, of <dodf>rows</dodf> bnd <dodf>dols</dodf> dbn
     * bf zfro, wiidi mfbns tibt bny numbfr of objfdts dbn bf plbdfd in b
     * row or in b dolumn.
     * <p>
     * All <dodf>GridLbyout</dodf> donstrudtors dfffr to tiis onf.
     * @pbrbm     rows   tif rows, witi tif vbluf zfro mfbning
     *                   bny numbfr of rows
     * @pbrbm     dols   tif dolumns, witi tif vbluf zfro mfbning
     *                   bny numbfr of dolumns
     * @pbrbm     igbp   tif iorizontbl gbp
     * @pbrbm     vgbp   tif vfrtidbl gbp
     * @fxdfption   IllfgblArgumfntExdfption  if tif vbluf of boti
     *                  <dodf>rows</dodf> bnd <dodf>dols</dodf> is
     *                  sft to zfro
     */
    publid GridLbyout(int rows, int dols, int igbp, int vgbp) {
        if ((rows == 0) && (dols == 0)) {
            tirow nfw IllfgblArgumfntExdfption("rows bnd dols dbnnot boti bf zfro");
        }
        tiis.rows = rows;
        tiis.dols = dols;
        tiis.igbp = igbp;
        tiis.vgbp = vgbp;
    }

    /**
     * Gfts tif numbfr of rows in tiis lbyout.
     * @rfturn    tif numbfr of rows in tiis lbyout
     * @sindf     1.1
     */
    publid int gftRows() {
        rfturn rows;
    }

    /**
     * Sfts tif numbfr of rows in tiis lbyout to tif spfdififd vbluf.
     * @pbrbm        rows   tif numbfr of rows in tiis lbyout
     * @fxdfption    IllfgblArgumfntExdfption  if tif vbluf of boti
     *               <dodf>rows</dodf> bnd <dodf>dols</dodf> is sft to zfro
     * @sindf        1.1
     */
    publid void sftRows(int rows) {
        if ((rows == 0) && (tiis.dols == 0)) {
            tirow nfw IllfgblArgumfntExdfption("rows bnd dols dbnnot boti bf zfro");
        }
        tiis.rows = rows;
    }

    /**
     * Gfts tif numbfr of dolumns in tiis lbyout.
     * @rfturn     tif numbfr of dolumns in tiis lbyout
     * @sindf      1.1
     */
    publid int gftColumns() {
        rfturn dols;
    }

    /**
     * Sfts tif numbfr of dolumns in tiis lbyout to tif spfdififd vbluf.
     * Sftting tif numbfr of dolumns ibs no bfffdt on tif lbyout
     * if tif numbfr of rows spfdififd by b donstrudtor or by
     * tif <tt>sftRows</tt> mftiod is non-zfro. In tibt dbsf, tif numbfr
     * of dolumns displbyfd in tif lbyout is dftfrminfd by tif totbl
     * numbfr of domponfnts bnd tif numbfr of rows spfdififd.
     * @pbrbm        dols   tif numbfr of dolumns in tiis lbyout
     * @fxdfption    IllfgblArgumfntExdfption  if tif vbluf of boti
     *               <dodf>rows</dodf> bnd <dodf>dols</dodf> is sft to zfro
     * @sindf        1.1
     */
    publid void sftColumns(int dols) {
        if ((dols == 0) && (tiis.rows == 0)) {
            tirow nfw IllfgblArgumfntExdfption("rows bnd dols dbnnot boti bf zfro");
        }
        tiis.dols = dols;
    }

    /**
     * Gfts tif iorizontbl gbp bftwffn domponfnts.
     * @rfturn       tif iorizontbl gbp bftwffn domponfnts
     * @sindf        1.1
     */
    publid int gftHgbp() {
        rfturn igbp;
    }

    /**
     * Sfts tif iorizontbl gbp bftwffn domponfnts to tif spfdififd vbluf.
     * @pbrbm        igbp   tif iorizontbl gbp bftwffn domponfnts
     * @sindf        1.1
     */
    publid void sftHgbp(int igbp) {
        tiis.igbp = igbp;
    }

    /**
     * Gfts tif vfrtidbl gbp bftwffn domponfnts.
     * @rfturn       tif vfrtidbl gbp bftwffn domponfnts
     * @sindf        1.1
     */
    publid int gftVgbp() {
        rfturn vgbp;
    }

    /**
     * Sfts tif vfrtidbl gbp bftwffn domponfnts to tif spfdififd vbluf.
     * @pbrbm         vgbp  tif vfrtidbl gbp bftwffn domponfnts
     * @sindf        1.1
     */
    publid void sftVgbp(int vgbp) {
        tiis.vgbp = vgbp;
    }

    /**
     * Adds tif spfdififd domponfnt witi tif spfdififd nbmf to tif lbyout.
     * @pbrbm nbmf tif nbmf of tif domponfnt
     * @pbrbm domp tif domponfnt to bf bddfd
     */
    publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif lbyout.
     * @pbrbm domp tif domponfnt to bf rfmovfd
     */
    publid void rfmovfLbyoutComponfnt(Componfnt domp) {
    }

    /**
     * Dftfrminfs tif prfffrrfd sizf of tif dontbinfr brgumfnt using
     * tiis grid lbyout.
     * <p>
     * Tif prfffrrfd widti of b grid lbyout is tif lbrgfst prfffrrfd
     * widti of bll of tif domponfnts in tif dontbinfr timfs tif numbfr of
     * dolumns, plus tif iorizontbl pbdding timfs tif numbfr of dolumns
     * minus onf, plus tif lfft bnd rigit insfts of tif tbrgft dontbinfr.
     * <p>
     * Tif prfffrrfd ifigit of b grid lbyout is tif lbrgfst prfffrrfd
     * ifigit of bll of tif domponfnts in tif dontbinfr timfs tif numbfr of
     * rows, plus tif vfrtidbl pbdding timfs tif numbfr of rows minus onf,
     * plus tif top bnd bottom insfts of tif tbrgft dontbinfr.
     *
     * @pbrbm     pbrfnt   tif dontbinfr in wiidi to do tif lbyout
     * @rfturn    tif prfffrrfd dimfnsions to lby out tif
     *                      subdomponfnts of tif spfdififd dontbinfr
     * @sff       jbvb.bwt.GridLbyout#minimumLbyoutSizf
     * @sff       jbvb.bwt.Contbinfr#gftPrfffrrfdSizf()
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
      syndironizfd (pbrfnt.gftTrffLodk()) {
        Insfts insfts = pbrfnt.gftInsfts();
        int ndomponfnts = pbrfnt.gftComponfntCount();
        int nrows = rows;
        int ndols = dols;

        if (nrows > 0) {
            ndols = (ndomponfnts + nrows - 1) / nrows;
        } flsf {
            nrows = (ndomponfnts + ndols - 1) / ndols;
        }
        int w = 0;
        int i = 0;
        for (int i = 0 ; i < ndomponfnts ; i++) {
            Componfnt domp = pbrfnt.gftComponfnt(i);
            Dimfnsion d = domp.gftPrfffrrfdSizf();
            if (w < d.widti) {
                w = d.widti;
            }
            if (i < d.ifigit) {
                i = d.ifigit;
            }
        }
        rfturn nfw Dimfnsion(insfts.lfft + insfts.rigit + ndols*w + (ndols-1)*igbp,
                             insfts.top + insfts.bottom + nrows*i + (nrows-1)*vgbp);
      }
    }

    /**
     * Dftfrminfs tif minimum sizf of tif dontbinfr brgumfnt using tiis
     * grid lbyout.
     * <p>
     * Tif minimum widti of b grid lbyout is tif lbrgfst minimum widti
     * of bll of tif domponfnts in tif dontbinfr timfs tif numbfr of dolumns,
     * plus tif iorizontbl pbdding timfs tif numbfr of dolumns minus onf,
     * plus tif lfft bnd rigit insfts of tif tbrgft dontbinfr.
     * <p>
     * Tif minimum ifigit of b grid lbyout is tif lbrgfst minimum ifigit
     * of bll of tif domponfnts in tif dontbinfr timfs tif numbfr of rows,
     * plus tif vfrtidbl pbdding timfs tif numbfr of rows minus onf, plus
     * tif top bnd bottom insfts of tif tbrgft dontbinfr.
     *
     * @pbrbm       pbrfnt   tif dontbinfr in wiidi to do tif lbyout
     * @rfturn      tif minimum dimfnsions nffdfd to lby out tif
     *                      subdomponfnts of tif spfdififd dontbinfr
     * @sff         jbvb.bwt.GridLbyout#prfffrrfdLbyoutSizf
     * @sff         jbvb.bwt.Contbinfr#doLbyout
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
      syndironizfd (pbrfnt.gftTrffLodk()) {
        Insfts insfts = pbrfnt.gftInsfts();
        int ndomponfnts = pbrfnt.gftComponfntCount();
        int nrows = rows;
        int ndols = dols;

        if (nrows > 0) {
            ndols = (ndomponfnts + nrows - 1) / nrows;
        } flsf {
            nrows = (ndomponfnts + ndols - 1) / ndols;
        }
        int w = 0;
        int i = 0;
        for (int i = 0 ; i < ndomponfnts ; i++) {
            Componfnt domp = pbrfnt.gftComponfnt(i);
            Dimfnsion d = domp.gftMinimumSizf();
            if (w < d.widti) {
                w = d.widti;
            }
            if (i < d.ifigit) {
                i = d.ifigit;
            }
        }
        rfturn nfw Dimfnsion(insfts.lfft + insfts.rigit + ndols*w + (ndols-1)*igbp,
                             insfts.top + insfts.bottom + nrows*i + (nrows-1)*vgbp);
      }
    }

    /**
     * Lbys out tif spfdififd dontbinfr using tiis lbyout.
     * <p>
     * Tiis mftiod rfsibpfs tif domponfnts in tif spfdififd tbrgft
     * dontbinfr in ordfr to sbtisfy tif donstrbints of tif
     * <dodf>GridLbyout</dodf> objfdt.
     * <p>
     * Tif grid lbyout mbnbgfr dftfrminfs tif sizf of individubl
     * domponfnts by dividing tif frff spbdf in tif dontbinfr into
     * fqubl-sizfd portions bddording to tif numbfr of rows bnd dolumns
     * in tif lbyout. Tif dontbinfr's frff spbdf fqubls tif dontbinfr's
     * sizf minus bny insfts bnd bny spfdififd iorizontbl or vfrtidbl
     * gbp. All domponfnts in b grid lbyout brf givfn tif sbmf sizf.
     *
     * @pbrbm      pbrfnt   tif dontbinfr in wiidi to do tif lbyout
     * @sff        jbvb.bwt.Contbinfr
     * @sff        jbvb.bwt.Contbinfr#doLbyout
     */
    publid void lbyoutContbinfr(Contbinfr pbrfnt) {
      syndironizfd (pbrfnt.gftTrffLodk()) {
        Insfts insfts = pbrfnt.gftInsfts();
        int ndomponfnts = pbrfnt.gftComponfntCount();
        int nrows = rows;
        int ndols = dols;
        boolfbn ltr = pbrfnt.gftComponfntOrifntbtion().isLfftToRigit();

        if (ndomponfnts == 0) {
            rfturn;
        }
        if (nrows > 0) {
            ndols = (ndomponfnts + nrows - 1) / nrows;
        } flsf {
            nrows = (ndomponfnts + ndols - 1) / ndols;
        }
        // 4370316. To position domponfnts in tif dfntfr wf siould:
        // 1. gft bn bmount of fxtrb spbdf witiin Contbinfr
        // 2. indorporbtf iblf of tibt vbluf to tif lfft/top position
        // Notf tibt wf usf trbndbting division for widtiOnComponfnt
        // Tif rfmindfr gofs to fxtrbWidtiAvbilbblf
        int totblGbpsWidti = (ndols - 1) * igbp;
        int widtiWOInsfts = pbrfnt.widti - (insfts.lfft + insfts.rigit);
        int widtiOnComponfnt = (widtiWOInsfts - totblGbpsWidti) / ndols;
        int fxtrbWidtiAvbilbblf = (widtiWOInsfts - (widtiOnComponfnt * ndols + totblGbpsWidti)) / 2;

        int totblGbpsHfigit = (nrows - 1) * vgbp;
        int ifigitWOInsfts = pbrfnt.ifigit - (insfts.top + insfts.bottom);
        int ifigitOnComponfnt = (ifigitWOInsfts - totblGbpsHfigit) / nrows;
        int fxtrbHfigitAvbilbblf = (ifigitWOInsfts - (ifigitOnComponfnt * nrows + totblGbpsHfigit)) / 2;
        if (ltr) {
            for (int d = 0, x = insfts.lfft + fxtrbWidtiAvbilbblf; d < ndols ; d++, x += widtiOnComponfnt + igbp) {
                for (int r = 0, y = insfts.top + fxtrbHfigitAvbilbblf; r < nrows ; r++, y += ifigitOnComponfnt + vgbp) {
                    int i = r * ndols + d;
                    if (i < ndomponfnts) {
                        pbrfnt.gftComponfnt(i).sftBounds(x, y, widtiOnComponfnt, ifigitOnComponfnt);
                    }
                }
            }
        } flsf {
            for (int d = 0, x = (pbrfnt.widti - insfts.rigit - widtiOnComponfnt) - fxtrbWidtiAvbilbblf; d < ndols ; d++, x -= widtiOnComponfnt + igbp) {
                for (int r = 0, y = insfts.top + fxtrbHfigitAvbilbblf; r < nrows ; r++, y += ifigitOnComponfnt + vgbp) {
                    int i = r * ndols + d;
                    if (i < ndomponfnts) {
                        pbrfnt.gftComponfnt(i).sftBounds(x, y, widtiOnComponfnt, ifigitOnComponfnt);
                    }
                }
            }
        }
      }
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tiis grid lbyout's vblufs.
     * @rfturn     b string rfprfsfntbtion of tiis grid lbyout
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[igbp=" + igbp + ",vgbp=" + vgbp +
                                       ",rows=" + rows + ",dols=" + dols + "]";
    }
}
