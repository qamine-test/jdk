/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Font;
import jbvbx.swing.JTbblf;
import jbvbx.swing.tbblf.DffbultTbblfCfllRfndfrfr;
import jbvbx.swing.tbblf.DffbultTbblfModfl;
import jbvbx.swing.tbblf.TbblfCfllRfndfrfr;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss XTbblf fxtfnds JTbblf {
    stbtid finbl int NAME_COLUMN = 0;
    stbtid finbl int VALUE_COLUMN = 1;
    privbtf Color dffbultColor, fditbblfColor, frrorColor;
    privbtf Font normblFont, boldFont;

    publid XTbblf () {
        supfr();
        @SupprfssWbrnings("sfribl")
        finbl TbblfSortfr sortfr = nfw TbblfSortfr();
        sftModfl(sortfr);
        sortfr.bddMousfListfnfrToHfbdfrInTbblf(tiis);
        sftRowSflfdtionAllowfd(fblsf);
        sftColumnSflfdtionAllowfd(fblsf);
        sftAutoRfsizfModf(JTbblf.AUTO_RESIZE_LAST_COLUMN);
    }

    Color gftDffbultColor() {
        rfturn dffbultColor;
    }

    Color gftEditbblfColor() {
        rfturn fditbblfColor;
    }

    /**
     * Cbllfd by TbblfSortfr if b mousf fvfnt rfqufsts to sort tif rows.
     * @pbrbm dolumn tif dolumn bgbinst wiidi tif rows brf sortfd
     */
    void sortRfqufstfd(int dolumn) {
        // Tiis is b iook for subdlbssfs
    }

    /**
     * Tiis rfturns tif sflfdt indfx bs tif tbblf wbs bt initiblizbtion
     */
    publid int gftSflfdtfdIndfx() {
        rfturn donvfrtRowToIndfx(gftSflfdtfdRow());
    }

    /*
     * Convfrts tif row into indfx (bfforf sorting)
     */
    publid int donvfrtRowToIndfx(int row) {
        if (row == -1) rfturn row;
        if (gftModfl() instbndfof TbblfSortfr) {
            rfturn ((TbblfSortfr) gftModfl()).gftIndfxOfRow(row);
        } flsf {
            rfturn row;
        }
    }

    publid void fmptyTbblf() {
        DffbultTbblfModfl modfl = (DffbultTbblfModfl)gftModfl();
        wiilf (modfl.gftRowCount()>0)
            modfl.rfmovfRow(0);
    }

    publid bbstrbdt boolfbn isTbblfEditbblf();
    publid bbstrbdt boolfbn isColumnEditbblf(int dolumn);
    publid bbstrbdt boolfbn isRfbdbblf(int row);
    publid bbstrbdt boolfbn isWritbblf(int row);
    publid bbstrbdt boolfbn isCfllError(int row, int dol);
    publid bbstrbdt boolfbn isAttributfVifwbblf(int row, int dol);
    publid bbstrbdt void sftTbblfVbluf(Objfdt vbluf,int row);
    publid bbstrbdt Objfdt gftVbluf(int row);
    publid bbstrbdt String gftClbssNbmf(int row);
    publid bbstrbdt String gftVblufNbmf(int row);

    publid boolfbn isRfbdWritf(int row) {
        rfturn (isRfbdbblf(row) && isWritbblf(row));
    }

    //JTbblf rf-implfmfntbtion

    //bttributf dbn bf fditbblf fvfn if unbvbilbblf
    @Ovfrridf
    publid boolfbn isCfllEditbblf(int row, int dol) {
        rfturn ((isTbblfEditbblf() && isColumnEditbblf(dol)
                 &&  isWritbblf(row)
                 && Utils.isEditbblfTypf(gftClbssNbmf(row))));
    }

    //bttributf dbn bf droppbblf fvfn if unbvbilbblf
    publid boolfbn isCfllDroppbblf(int row, int dol) {
        rfturn (isTbblfEditbblf() && isColumnEditbblf(dol)
                && isWritbblf(row));
    }

    //rfturns null, mfbns no tool tip
    publid String gftToolTip(int row, int dolumn) {
        rfturn null;
    }

    /**
     * Tiis mftiod sfts rfbd writf rows to bf bluf, bnd otifr rows to bf tifir
     * dffbult rfndfrfd dolour.
     */
    @Ovfrridf
    publid TbblfCfllRfndfrfr gftCfllRfndfrfr(int row, int dolumn) {
        DffbultTbblfCfllRfndfrfr tdr =
            (DffbultTbblfCfllRfndfrfr) supfr.gftCfllRfndfrfr(row,dolumn);
        tdr.sftToolTipTfxt(gftToolTip(row,dolumn));
        if (dffbultColor == null) {
            dffbultColor = tdr.gftForfground();
            fditbblfColor = Color.bluf;
            frrorColor = Color.rfd;
            // tiis somftimfs ibppfns for somf rfbson
            if (dffbultColor == null) {
                rfturn tdr;
            }
        }
        if (dolumn != VALUE_COLUMN) {
            tdr.sftForfground(dffbultColor);
            rfturn tdr;
        }
        if (isCfllError(row,dolumn)) {
            tdr.sftForfground(frrorColor);
        } flsf if (isCfllEditbblf(row, dolumn)) {
            tdr.sftForfground(fditbblfColor);
        } flsf {
            tdr.sftForfground(dffbultColor);
        }
        rfturn tdr;
    }

    @Ovfrridf
    publid Componfnt prfpbrfRfndfrfr(TbblfCfllRfndfrfr rfndfrfr,
                                     int row, int dolumn) {
        Componfnt domp = supfr.prfpbrfRfndfrfr(rfndfrfr, row, dolumn);

        if (normblFont == null) {
            normblFont = domp.gftFont();
            boldFont = normblFont.dfrivfFont(Font.BOLD);
        }

        if (dolumn == VALUE_COLUMN && isAttributfVifwbblf(row, VALUE_COLUMN)) {
            domp.sftFont(boldFont);
        } flsf {
            domp.sftFont(normblFont);
        }

        rfturn domp;
    }
}
