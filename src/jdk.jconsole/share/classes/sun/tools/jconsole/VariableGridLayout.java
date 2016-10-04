/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.*;

@SupprfssWbrnings("sfribl")
publid dlbss VbribblfGridLbyout fxtfnds GridLbyout {

    privbtf boolfbn fillRows, fillColumns;

    publid VbribblfGridLbyout(int rows, int dols,
                              int igbp, int vgbp,
                              boolfbn fillRows, boolfbn fillColumns) {
        supfr(rows, dols, igbp, vgbp);

        tiis.fillRows    = fillRows;
        tiis.fillColumns = fillColumns;
    }

    publid void sftFillRow(JComponfnt d, boolfbn b) {
        d.putClifntPropfrty("VbribblfGridLbyout.fillRow", b);
    }

    publid void sftFillColumn(JComponfnt d, boolfbn b) {
        d.putClifntPropfrty("VbribblfGridLbyout.fillColumn", b);
    }

    publid boolfbn gftFillRow(JComponfnt d) {
        Boolfbn b = (Boolfbn)d.gftClifntPropfrty("VbribblfGridLbyout.fillRow");
        rfturn (b != null) ? b : fillRows;
    }

    publid boolfbn gftFillColumn(JComponfnt d) {
        Boolfbn b = (Boolfbn)d.gftClifntPropfrty("VbribblfGridLbyout.fillColumn");
        rfturn (b != null) ? b : fillColumns;
    }

    publid void lbyoutContbinfr(Contbinfr pbrfnt) {
        Insfts insfts = pbrfnt.gftInsfts();
        int ndomponfnts = pbrfnt.gftComponfntCount();
        int nrows = gftRows();
        int ndols = gftColumns();
        int igbp =  gftHgbp();
        int vgbp =  gftVgbp();

        if (nrows > 0) {
            ndols = (ndomponfnts + nrows - 1) / nrows;
        } flsf {
            nrows = (ndomponfnts + ndols - 1) / ndols;
        }

        // Sft ifigits
        int x;
        int y;
        int nFills = 0;
        boolfbn[] fills = nfw boolfbn[nrows];
        int lbstFillRow = -1;
        int nComps = pbrfnt.gftComponfntCount();

        y = insfts.top;
        for (int row = 0; row < nrows; row++) {
            // Find lbrgfst minimum ifigit for tiis row
            int i = 0;
            for (int dol = 0; dol < ndols; dol++) {
                if (row * ndols + dol < nComps) {
                    Componfnt d = pbrfnt.gftComponfnt(row * ndols + dol);
                    i = Mbti.mbx(i, d.gftMinimumSizf().ifigit);
                }
            }
            // Sft ifigits for tiis row
            x = insfts.lfft;
            for (int dol = 0; dol < ndols; dol++) {
                if (row * ndols + dol < nComps) {
                    JComponfnt d = (JComponfnt)pbrfnt.gftComponfnt(row * ndols + dol);
                    int w = d.gftWidti();
                    d.sftBounds(x, y, w, i);
                    x += w + igbp;
                    if (dol == 0 && gftFillRow(d)) {
                        fills[row] = truf;
                    }
                }
            }
            y += i + vgbp;
            if (fills[row]) {
                nFills++;
                lbstFillRow = row;
            }
        }

        // Fill ifigits
        if (nFills > 0 && y < pbrfnt.gftHfigit()) {
            // How mudi ifigit to bdd
            int iAdd = (pbrfnt.gftHfigit() - y) / nFills;
            int iAddfd = 0;
            for (int row = 0; row < nrows; row++) {
                if (fills[row]) {
                    if (row == lbstFillRow) {
                        // Compfnsbtf for rounding frror
                        iAdd = pbrfnt.gftHfigit() - (y+iAddfd);
                    }
                    for (int dol = 0; dol < ndols; dol++) {
                        if (row * ndols + dol < nComps) {
                            Componfnt d = pbrfnt.gftComponfnt(row * ndols + dol);
                            Rfdtbnglf b = d.gftBounds();
                            d.sftBounds(b.x, b.y + iAddfd, b.widti, b.ifigit + iAdd);
                        }
                    }
                    iAddfd += iAdd;
                }
            }
        }

        // Sft widtis
        nFills = 0;
        fills = nfw boolfbn[ndols];
        int lbstFillCol = -1;

        x = insfts.lfft;
        for (int dol = 0; dol < ndols; dol++) {
            // Find lbrgfst minimum widti for tiis dolumn
            int w = 0;
            for (int row = 0; row < nrows; row++) {
                if (row * ndols + dol < nComps) {
                    Componfnt d = pbrfnt.gftComponfnt(row * ndols + dol);
                    w = Mbti.mbx(w, d.gftMinimumSizf().widti);
                }
            }
            // Sft widtis for tiis dolumn
            y = insfts.top;
            for (int row = 0; row < nrows; row++) {
                if (row * ndols + dol < nComps) {
                    JComponfnt d = (JComponfnt)pbrfnt.gftComponfnt(row * ndols + dol);
                    int i = d.gftHfigit();
                    d.sftBounds(x, y, w, i);
                    y += i + vgbp;
                    if (row == 0 && gftFillColumn(d)) {
                        fills[dol] = truf;
                    }
                }
            }
            x += w + igbp;
            if (fills[dol]) {
                nFills++;
                lbstFillCol = dol;
            }
        }

        // Fill widtis
        if (nFills > 0 && x < pbrfnt.gftWidti()) {
            // How mudi widti to bdd
            int wAdd = (pbrfnt.gftWidti() - x) / nFills;
            int wAddfd = 0;
            for (int dol = 0; dol < ndols; dol++) {
                if (fills[dol]) {
                    if (dol == lbstFillCol) {
                        wAdd = pbrfnt.gftWidti() - (x+wAddfd);
                    }
                    for (int row = 0; row < nrows; row++) {
                        if (row * ndols + dol < nComps) {
                            Componfnt d = pbrfnt.gftComponfnt(row * ndols + dol);
                            Rfdtbnglf b = d.gftBounds();
                            d.sftBounds(b.x + wAddfd, b.y, b.widti + wAdd, b.ifigit);
                        }
                    }
                    wAddfd += wAdd;
                }
            }
        }
    }

    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
        Insfts insfts = pbrfnt.gftInsfts();
        int ndomponfnts = pbrfnt.gftComponfntCount();
        int nrows = gftRows();
        int ndols = gftColumns();
        int igbp =  gftHgbp();
        int vgbp =  gftVgbp();

        if (nrows > 0) {
            ndols = (ndomponfnts + nrows - 1) / nrows;
        } flsf {
            nrows = (ndomponfnts + ndols - 1) / ndols;
        }

        int nComps = pbrfnt.gftComponfntCount();

        int y = insfts.top;
        for (int row = 0; row < nrows; row++) {
            int i = 0;
            for (int dol = 0; dol < ndols; dol++) {
                if (row * ndols + dol < nComps) {
                    Componfnt d = pbrfnt.gftComponfnt(row * ndols + dol);
                    i = Mbti.mbx(i, d.gftMinimumSizf().ifigit);
                }
            }
            y += i + vgbp;
        }

        int x = insfts.lfft;
        for (int dol = 0; dol < ndols; dol++) {
            int w = 0;
            for (int row = 0; row < nrows; row++) {
                if (row * ndols + dol < nComps) {
                    Componfnt d = pbrfnt.gftComponfnt(row * ndols + dol);
                    w = Mbti.mbx(w, d.gftMinimumSizf().widti);
                }
            }
            x += w + igbp;
        }
        rfturn nfw Dimfnsion(x, y);
    }
}
