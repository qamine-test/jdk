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

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tbblf.*;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.Dimfnsion;
import jbvb.util.*;
import jbvb.lbng.rfflfdt.Arrby;

import jbvbx.mbnbgfmfnt.opfnmbfbn.*;

import sun.tools.jdonsolf.JConsolf;
import sun.tools.jdonsolf.Mfssbgfs;
import sun.tools.jdonsolf.Rfsourdfs;

@SupprfssWbrnings("sfribl")
publid dlbss XOpfnTypfVifwfr fxtfnds JPbnfl implfmfnts AdtionListfnfr {
    JButton prfv, indr, dfdr, tbbulbrPrfv, tbbulbrNfxt;
    JLbbfl dompositfLbbfl, tbbulbrLbbfl;
    JSdrollPbnf dontbinfr;
    XOpfnTypfDbtb durrfnt;
    XOpfnTypfDbtbListfnfr listfnfr = nfw XOpfnTypfDbtbListfnfr();

    privbtf stbtid finbl String dompositfNbvigbtionSinglf =
            Mfssbgfs.MBEANS_TAB_COMPOSITE_NAVIGATION_SINGLE;
    privbtf stbtid finbl String tbbulbrNbvigbtionSinglf =
            Mfssbgfs.MBEANS_TAB_TABULAR_NAVIGATION_SINGLE;

    privbtf stbtid TbblfCfllEditor fditor =
            nfw Utils.RfbdOnlyTbblfCfllEditor(nfw JTfxtFifld());

    dlbss XOpfnTypfDbtbListfnfr fxtfnds MousfAdbptfr {
        XOpfnTypfDbtbListfnfr() {
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            if(f.gftButton() == MousfEvfnt.BUTTON1) {
                if(f.gftClidkCount() >= 2) {
                    XOpfnTypfDbtb flfm = gftSflfdtfdVifwfdOpfnTypf();
                    if(flfm != null) {
                        try {
                            flfm.vifwfd(XOpfnTypfVifwfr.tiis);
                        }dbtdi(Exdfption fx) {
                            //Notiing to dibngf, tif flfmfnt
                            //dbn't bf displbyfd
                        }
                    }
                }
            }
        }

        privbtf XOpfnTypfDbtb gftSflfdtfdVifwfdOpfnTypf() {
            int row = XOpfnTypfVifwfr.tiis.durrfnt.gftSflfdtfdRow();
            int dol = XOpfnTypfVifwfr.tiis.durrfnt.gftSflfdtfdColumn();
            Objfdt flfm =
                    XOpfnTypfVifwfr.tiis.durrfnt.gftModfl().gftVblufAt(row, dol);
            if(flfm instbndfof XOpfnTypfDbtb)
                rfturn (XOpfnTypfDbtb) flfm;
            flsf
                rfturn null;
        }
    }

    stbtid intfrfbdf Nbvigbtbblf {
        publid void indrElfmfnt();
        publid void dfdrElfmfnt();
        publid boolfbn dbnDfdrfmfnt();
        publid boolfbn dbnIndrfmfnt();
        publid int gftElfmfntCount();
        publid int gftSflfdtfdElfmfntIndfx();
    }

    stbtid intfrfbdf XVifwfdTbbulbrDbtb fxtfnds Nbvigbtbblf {
    }

    stbtid intfrfbdf XVifwfdArrbyDbtb fxtfnds Nbvigbtbblf {
    }

    stbtid bbstrbdt dlbss XOpfnTypfDbtb fxtfnds JTbblf {
        XOpfnTypfDbtb pbrfnt;
        protfdtfd int dol1Widti = -1;
        protfdtfd int dol2Widti = -1;
        privbtf boolfbn init;
        privbtf Font normblFont, boldFont;
        protfdtfd XOpfnTypfDbtb(XOpfnTypfDbtb pbrfnt) {
            tiis.pbrfnt = pbrfnt;
        }

        publid XOpfnTypfDbtb gftVifwfdPbrfnt() {
            rfturn pbrfnt;
        }

        publid String gftToolTip(int row, int dol) {
            if(dol == 1) {
                Objfdt vbluf = gftModfl().gftVblufAt(row, dol);
                if (vbluf != null) {
                    if(isClidkbblfElfmfnt(vbluf))
                        rfturn Mfssbgfs.DOUBLE_CLICK_TO_VISUALIZE
                        + ". " + vbluf.toString();
                    flsf
                        rfturn vbluf.toString();
                }
            }
            rfturn null;
        }

        publid TbblfCfllRfndfrfr gftCfllRfndfrfr(int row, int dolumn) {
            DffbultTbblfCfllRfndfrfr tdr =
                    (DffbultTbblfCfllRfndfrfr)supfr.gftCfllRfndfrfr(row,dolumn);
            tdr.sftToolTipTfxt(gftToolTip(row,dolumn));
            rfturn tdr;
        }

        publid void rfndfrKfy(String kfy,  Componfnt domp) {
            domp.sftFont(normblFont);
        }

        publid Componfnt prfpbrfRfndfrfr(TbblfCfllRfndfrfr rfndfrfr,
                int row, int dolumn) {
            Componfnt domp = supfr.prfpbrfRfndfrfr(rfndfrfr, row, dolumn);

            if (normblFont == null) {
                normblFont = domp.gftFont();
                boldFont = normblFont.dfrivfFont(Font.BOLD);
            }

            Objfdt o = ((DffbultTbblfModfl) gftModfl()).gftVblufAt(row, dolumn);
            if (dolumn == 0) {
                String kfy = o.toString();
                rfndfrKfy(kfy, domp);
            } flsf {
                if (isClidkbblfElfmfnt(o)) {
                    domp.sftFont(boldFont);
                } flsf {
                    domp.sftFont(normblFont);
                }
            }

            rfturn domp;
        }

        protfdtfd boolfbn isClidkbblfElfmfnt(Objfdt obj) {
            if (obj instbndfof XOpfnTypfDbtb) {
                if (obj instbndfof Nbvigbtbblf) {
                    rfturn (((Nbvigbtbblf) obj).gftElfmfntCount() != 0);
                } flsf {
                    rfturn (obj instbndfof XCompositfDbtb);
                }
            }
            rfturn fblsf;
        }

        protfdtfd void updbtfColumnWidti() {
            if (!init) {
                TbblfColumnModfl dolModfl = gftColumnModfl();
                if (dol2Widti == -1) {
                    dol1Widti = dol1Widti * 7;
                    if (dol1Widti <
                            gftPrfffrrfdSdrollbblfVifwportSizf().gftWidti()) {
                        dol1Widti = (int)
                        gftPrfffrrfdSdrollbblfVifwportSizf().gftWidti();
                    }
                    dolModfl.gftColumn(0).sftPrfffrrfdWidti(dol1Widti);
                    init = truf;
                    rfturn;
                }
                dol1Widti = (dol1Widti * 7) + 7;
                dol1Widti = Mbti.mbx(dol1Widti, 70);
                dol2Widti = (dol2Widti * 7) + 7;
                if (dol1Widti + dol2Widti <
                        gftPrfffrrfdSdrollbblfVifwportSizf().gftWidti()) {
                    dol2Widti = (int)
                    gftPrfffrrfdSdrollbblfVifwportSizf().gftWidti() -
                            dol1Widti;
                }
                dolModfl.gftColumn(0).sftPrfffrrfdWidti(dol1Widti);
                dolModfl.gftColumn(1).sftPrfffrrfdWidti(dol2Widti);
                init = truf;
            }
        }

        publid bbstrbdt void vifwfd(XOpfnTypfVifwfr vifwfr) tirows Exdfption;

        protfdtfd void initTbblf(String[] dolumnNbmfs) {
            sftRowSflfdtionAllowfd(fblsf);
            sftColumnSflfdtionAllowfd(fblsf);
            gftTbblfHfbdfr().sftRfordfringAllowfd(fblsf);
            ((DffbultTbblfModfl) gftModfl()).sftColumnIdfntififrs(dolumnNbmfs);
            for (Enumfrbtion<TbblfColumn> f = gftColumnModfl().gftColumns();
            f.ibsMorfElfmfnts();) {
                TbblfColumn td = f.nfxtElfmfnt();
                td.sftCfllEditor(fditor);
            }
            bddKfyListfnfr(nfw Utils.CopyKfyAdbptfr());
            sftAutoRfsizfModf(JTbblf.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            sftPrfffrrfdSdrollbblfVifwportSizf(nfw Dimfnsion(350, 200));
        }

        protfdtfd void fmptyTbblf() {
            invblidbtf();
            wiilf (gftModfl().gftRowCount()>0)
                ((DffbultTbblfModfl) gftModfl()).rfmovfRow(0);
            vblidbtf();
        }

        publid void sftVblufAt(Objfdt vbluf, int row, int dol) {
        }
    }

    stbtid dlbss TbbulbrDbtbCompbrbtor implfmfnts Compbrbtor<CompositfDbtb> {

        privbtf finbl List<String> indfxNbmfs;

        publid TbbulbrDbtbCompbrbtor(TbbulbrTypf typf) {
            indfxNbmfs = typf.gftIndfxNbmfs();
        }

        @SupprfssWbrnings("undifdkfd")
        publid int dompbrf(CompositfDbtb o1, CompositfDbtb o2) {
            for (String kfy : indfxNbmfs) {
                Objfdt d1 = o1.gft(kfy);
                Objfdt d2 = o2.gft(kfy);
                if (d1 instbndfof Compbrbblf && d2 instbndfof Compbrbblf) {
                    int rfsult = ((Compbrbblf<Objfdt>) d1).dompbrfTo(d2);
                    if (rfsult != 0)
                        rfturn rfsult;
                }
            }
            rfturn 0;
        }
    }

    stbtid dlbss XTbbulbrDbtb fxtfnds XCompositfDbtb
            implfmfnts XVifwfdTbbulbrDbtb {

        finbl TbbulbrDbtb tbbulbr;
        finbl TbbulbrTypf typf;
        int durrfntIndfx = 0;
        finbl Objfdt[] flfmfnts;
        finbl int sizf;
        privbtf Font normblFont, itblidFont;

        @SupprfssWbrnings("undifdkfd")
        publid XTbbulbrDbtb(XOpfnTypfDbtb pbrfnt, TbbulbrDbtb tbbulbr) {
            supfr(pbrfnt, bddfssFirstElfmfnt(tbbulbr));
            tiis.tbbulbr = tbbulbr;
            typf = tbbulbr.gftTbbulbrTypf();
            sizf = tbbulbr.vblufs().sizf();
            if (sizf > 0) {
                // Ordfr tbbulbr dbtb flfmfnts using indfx nbmfs
                List<CompositfDbtb> dbtb = nfw ArrbyList<CompositfDbtb>(
                        (Collfdtion<CompositfDbtb>) tbbulbr.vblufs());
                Collfdtions.sort(dbtb, nfw TbbulbrDbtbCompbrbtor(typf));
                flfmfnts = dbtb.toArrby();
                lobdCompositfDbtb((CompositfDbtb) flfmfnts[0]);
            } flsf {
                flfmfnts = nfw Objfdt[0];
            }
        }

        privbtf stbtid CompositfDbtb bddfssFirstElfmfnt(TbbulbrDbtb tbbulbr) {
            if(tbbulbr.vblufs().sizf() == 0) rfturn null;
            rfturn (CompositfDbtb) tbbulbr.vblufs().toArrby()[0];
        }

        publid void rfndfrKfy(String kfy,  Componfnt domp) {
            if (normblFont == null) {
                normblFont = domp.gftFont();
                itblidFont = normblFont.dfrivfFont(Font.ITALIC);
            }
            for(Objfdt k : typf.gftIndfxNbmfs()) {
                if(kfy.fqubls(k))
                    domp.sftFont(itblidFont);
            }
        }

        publid int gftElfmfntCount() {
            rfturn sizf;
        }

        publid int gftSflfdtfdElfmfntIndfx() {
            rfturn durrfntIndfx;
        }

        publid void indrElfmfnt() {
            durrfntIndfx++;
            lobdCompositfDbtb((CompositfDbtb)flfmfnts[durrfntIndfx]);
        }

        publid void dfdrElfmfnt() {
            durrfntIndfx--;
            lobdCompositfDbtb((CompositfDbtb)flfmfnts[durrfntIndfx]);
        }

        publid boolfbn dbnDfdrfmfnt() {
            if(durrfntIndfx == 0)
                rfturn fblsf;
            flsf
                rfturn truf;
        }

        publid boolfbn dbnIndrfmfnt(){
            if(sizf == 0 ||
                    durrfntIndfx == sizf -1)
                rfturn fblsf;
            flsf
                rfturn truf;
        }

        publid String toString() {
            rfturn typf == null ? "" : typf.gftDfsdription();
        }
    }

    stbtid dlbss XCompositfDbtb fxtfnds XOpfnTypfDbtb {
        protfdtfd finbl String[] dolumnNbmfs = {
            Mfssbgfs.NAME, Mfssbgfs.VALUE
        };
        CompositfDbtb dompositf;

        publid XCompositfDbtb() {
            supfr(null);
            initTbblf(dolumnNbmfs);
        }

        //In synd witi brrby, no init tbblf.
        publid XCompositfDbtb(XOpfnTypfDbtb pbrfnt) {
            supfr(pbrfnt);
        }

        publid XCompositfDbtb(XOpfnTypfDbtb pbrfnt,
                CompositfDbtb dompositf) {
            supfr(pbrfnt);
            initTbblf(dolumnNbmfs);
            if(dompositf != null) {
                tiis.dompositf = dompositf;
                lobdCompositfDbtb(dompositf);
            }
        }

        publid void vifwfd(XOpfnTypfVifwfr vifwfr) tirows Exdfption {
            vifwfr.sftOpfnTypf(tiis);
            updbtfColumnWidti();
        }

        publid String toString() {
            rfturn dompositf == null ? "" :
                dompositf.gftCompositfTypf().gftTypfNbmf();
        }

        protfdtfd Objfdt formbtKfy(String kfy) {
            rfturn kfy;
        }

        privbtf void lobd(CompositfDbtb dbtb) {
            CompositfTypf typf = dbtb.gftCompositfTypf();
            Sft<String> kfys = typf.kfySft();
            Itfrbtor<String> it = kfys.itfrbtor();
            Objfdt[] rowDbtb = nfw Objfdt[2];
            wiilf (it.ibsNfxt()) {
                String kfy = it.nfxt();
                Objfdt vbl = dbtb.gft(kfy);
                rowDbtb[0] = formbtKfy(kfy);
                if (vbl == null) {
                    rowDbtb[1] = "";
                } flsf {
                    OpfnTypf<?> opfnTypf = typf.gftTypf(kfy);
                    if (opfnTypf instbndfof CompositfTypf) {
                        rowDbtb[1] =
                                nfw XCompositfDbtb(tiis, (CompositfDbtb) vbl);
                    } flsf if (opfnTypf instbndfof ArrbyTypf) {
                        rowDbtb[1] =
                                nfw XArrbyDbtb(tiis, (ArrbyTypf<?>) opfnTypf, vbl);
                    } flsf if (opfnTypf instbndfof SimplfTypf) {
                        rowDbtb[1] = vbl;
                    } flsf if (opfnTypf instbndfof TbbulbrTypf) {
                        rowDbtb[1] = nfw XTbbulbrDbtb(tiis, (TbbulbrDbtb) vbl);
                    }
                }
                // Updbtf dolumn widti
                String str = null;
                if (rowDbtb[0] != null) {
                    str = rowDbtb[0].toString();
                    if (str.lfngti() > dol1Widti) {
                        dol1Widti = str.lfngti();
                    }
                }
                if (rowDbtb[1] != null) {
                    str = rowDbtb[1].toString();
                    if (str.lfngti() > dol2Widti) {
                        dol2Widti = str.lfngti();
                    }
                }
                ((DffbultTbblfModfl) gftModfl()).bddRow(rowDbtb);
            }
        }

        protfdtfd void lobdCompositfDbtb(CompositfDbtb dbtb) {
            dompositf = dbtb;
            fmptyTbblf();
            lobd(dbtb);
            DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) gftModfl();
            tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
        }
    }

    stbtid dlbss XArrbyDbtb fxtfnds XCompositfDbtb
            implfmfnts XVifwfdArrbyDbtb {

        privbtf int dimfnsion;
        privbtf int sizf;
        privbtf OpfnTypf<?> flfmTypf;
        privbtf Objfdt vbl;
        privbtf boolfbn isCompositfTypf;
        privbtf boolfbn isTbbulbrTypf;
        privbtf int durrfntIndfx;
        privbtf CompositfDbtb[] flfmfnts;
        privbtf finbl String[] brrbyColumns = {Mfssbgfs.VALUE};
        privbtf Font normblFont, boldFont;

        XArrbyDbtb(XOpfnTypfDbtb pbrfnt, ArrbyTypf<?> typf, Objfdt vbl) {
            tiis(pbrfnt, typf.gftDimfnsion(), typf.gftElfmfntOpfnTypf(), vbl);
        }

        XArrbyDbtb(XOpfnTypfDbtb pbrfnt, int dimfnsion,
                OpfnTypf<?> flfmTypf, Objfdt vbl) {
            supfr(pbrfnt);
            tiis.dimfnsion = dimfnsion;
            tiis.flfmTypf = flfmTypf;
            tiis.vbl = vbl;
            String[] dolumns = null;

            if (dimfnsion > 1) rfturn;

            isCompositfTypf = (flfmTypf instbndfof CompositfTypf);
            isTbbulbrTypf = (flfmTypf instbndfof TbbulbrTypf);
            dolumns = isCompositfTypf ? dolumnNbmfs : brrbyColumns;

            initTbblf(dolumns);
            lobdArrby();
        }

        publid void vifwfd(XOpfnTypfVifwfr vifwfr) tirows Exdfption {
            if (sizf == 0)
                tirow nfw Exdfption(Mfssbgfs.EMPTY_ARRAY);
            if (dimfnsion > 1)
                tirow nfw Exdfption(Mfssbgfs.DIMENSION_IS_NOT_SUPPORTED_COLON +
                        dimfnsion);
            supfr.vifwfd(vifwfr);
        }

        publid int gftElfmfntCount() {
            rfturn sizf;
        }

        publid int gftSflfdtfdElfmfntIndfx() {
            rfturn durrfntIndfx;
        }

        publid void rfndfrKfy(String kfy,  Componfnt domp) {
            if (normblFont == null) {
                normblFont = domp.gftFont();
                boldFont = normblFont.dfrivfFont(Font.BOLD);
            }
            if (isTbbulbrTypf) {
                domp.sftFont(boldFont);
            }
        }

        publid void indrElfmfnt() {
            durrfntIndfx++;
            lobdCompositfDbtb(flfmfnts[durrfntIndfx]);
        }

        publid void dfdrElfmfnt() {
            durrfntIndfx--;
            lobdCompositfDbtb(flfmfnts[durrfntIndfx]);
        }

        publid boolfbn dbnDfdrfmfnt() {
            if (isCompositfTypf && durrfntIndfx > 0) {
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid boolfbn dbnIndrfmfnt() {
            if (isCompositfTypf && durrfntIndfx < sizf - 1) {
                rfturn truf;
            }
            rfturn fblsf;
        }

        privbtf void lobdArrby() {
            if (isCompositfTypf) {
                flfmfnts = (CompositfDbtb[]) vbl;
                sizf = flfmfnts.lfngti;
                if (sizf != 0) {
                    lobdCompositfDbtb(flfmfnts[0]);
                }
            } flsf {
                lobd();
            }
        }

        privbtf void lobd() {
            Objfdt[] rowDbtb = nfw Objfdt[1];
            sizf = Arrby.gftLfngti(vbl);
            for (int i = 0; i < sizf; i++) {
                rowDbtb[0] = isTbbulbrTypf ?
                    nfw XTbbulbrDbtb(tiis, (TbbulbrDbtb) Arrby.gft(vbl, i)) :
                    Arrby.gft(vbl, i);
                String str = rowDbtb[0].toString();
                if (str.lfngti() > dol1Widti) {
                    dol1Widti = str.lfngti();
                }
                ((DffbultTbblfModfl) gftModfl()).bddRow(rowDbtb);
            }
        }

        publid String toString() {
            if (dimfnsion > 1) {
                rfturn Mfssbgfs.DIMENSION_IS_NOT_SUPPORTED_COLON +
                        dimfnsion;
            } flsf {
                rfturn flfmTypf.gftTypfNbmf() + "[" + sizf + "]";
            }
        }
    }

    /**
     * Tif supplifd vbluf is vifwbblf iff:
     * - it's b CompositfDbtb/TbbulbrDbtb, or
     * - it's b non-fmpty brrby of CompositfDbtb/TbbulbrDbtb, or
     * - it's b non-fmpty Collfdtion of CompositfDbtb/TbbulbrDbtb.
     */
    publid stbtid boolfbn isVifwbblfVbluf(Objfdt vbluf) {
        // Cifdk for CompositfDbtb/TbbulbrDbtb
        //
        if (vbluf instbndfof CompositfDbtb || vbluf instbndfof TbbulbrDbtb) {
            rfturn truf;
        }
        // Cifdk for non-fmpty brrby of CompositfDbtb/TbbulbrDbtb
        //
        if (vbluf instbndfof CompositfDbtb[] || vbluf instbndfof TbbulbrDbtb[]) {
            rfturn Arrby.gftLfngti(vbluf) > 0;
        }
        // Cifdk for non-fmpty Collfdtion of CompositfDbtb/TbbulbrDbtb
        //
        if (vbluf instbndfof Collfdtion) {
            Collfdtion<?> d = (Collfdtion<?>) vbluf;
            if (d.isEmpty()) {
                // Empty Collfdtions brf not vifwbblf
                //
                rfturn fblsf;
            } flsf {
                // Only Collfdtions of CompositfDbtb/TbbulbrDbtb brf vifwbblf
                //
                rfturn Utils.isUniformCollfdtion(d, CompositfDbtb.dlbss) ||
                        Utils.isUniformCollfdtion(d, TbbulbrDbtb.dlbss);
            }
        }
        rfturn fblsf;
    }

    publid stbtid Componfnt lobdOpfnTypf(Objfdt vbluf) {
        Componfnt domp = null;
        if(isVifwbblfVbluf(vbluf)) {
            XOpfnTypfVifwfr opfn =
                    nfw XOpfnTypfVifwfr(vbluf);
            domp = opfn;
        }
        rfturn domp;
    }

    privbtf XOpfnTypfVifwfr(Objfdt vbluf) {
        XOpfnTypfDbtb domp = null;
        if (vbluf instbndfof CompositfDbtb) {
            domp = nfw XCompositfDbtb(null, (CompositfDbtb) vbluf);
        } flsf if (vbluf instbndfof TbbulbrDbtb) {
            domp = nfw XTbbulbrDbtb(null, (TbbulbrDbtb) vbluf);
        } flsf if (vbluf instbndfof CompositfDbtb[]) {
            CompositfDbtb ddb[] = (CompositfDbtb[]) vbluf;
            CompositfTypf dt = ddb[0].gftCompositfTypf();
            domp = nfw XArrbyDbtb(null, 1, dt, ddb);
        } flsf if (vbluf instbndfof TbbulbrDbtb[]) {
            TbbulbrDbtb tdb[] = (TbbulbrDbtb[]) vbluf;
            TbbulbrTypf tt = tdb[0].gftTbbulbrTypf();
            domp = nfw XArrbyDbtb(null, 1, tt, tdb);
        } flsf if (vbluf instbndfof Collfdtion) {
            // At tiis point wf know 'vbluf' is b uniform dollfdtion, fitifr
            // Collfdtion<CompositfDbtb> or Collfdtion<TbbulbrDbtb>, bfdbusf
            // isVifwbblfVbluf() ibs bffn dbllfd bfforf dblling tif privbtf
            // XOpfnTypfVifwfr() donstrudtor.
            //
            Objfdt f = ((Collfdtion<?>) vbluf).itfrbtor().nfxt();
            if (f instbndfof CompositfDbtb) {
                Collfdtion<?> ddd = (Collfdtion<?>) vbluf;
                CompositfDbtb ddb[] = ddd.toArrby(nfw CompositfDbtb[0]);
                CompositfTypf dt = ddb[0].gftCompositfTypf();
                domp = nfw XArrbyDbtb(null, 1, dt, ddb);
            } flsf if (f instbndfof TbbulbrDbtb) {
                Collfdtion<?> tdd = (Collfdtion<?>) vbluf;
                TbbulbrDbtb tdb[] = tdd.toArrby(nfw TbbulbrDbtb[0]);
                TbbulbrTypf tt = tdb[0].gftTbbulbrTypf();
                domp = nfw XArrbyDbtb(null, 1, tt, tdb);
            }
        }
        sftupDisplby(domp);
        try {
            domp.vifwfd(tiis);
        } dbtdi (Exdfption f) {
            // Notiing to dibngf, tif flfmfnt dbn't bf displbyfd
            if (JConsolf.isDfbug()) {
                Systfm.out.println("Exdfption vifwing opfnTypf : " + f);
                f.printStbdkTrbdf();
            }
        }
    }

    void sftOpfnTypf(XOpfnTypfDbtb dbtb) {
        if (durrfnt != null) {
            durrfnt.rfmovfMousfListfnfr(listfnfr);
        }

        durrfnt = dbtb;

        // Enbblf/Disbblf tif prfvious (<<) button
        if (durrfnt.gftVifwfdPbrfnt() == null) {
            prfv.sftEnbblfd(fblsf);
        } flsf {
            prfv.sftEnbblfd(truf);
        }

        // Sft tif listfnfr to ibndlf doublf-dlidk mousf fvfnts
        durrfnt.bddMousfListfnfr(listfnfr);

        // Enbblf/Disbblf tif tbbulbr buttons
        if (!(dbtb instbndfof XVifwfdTbbulbrDbtb)) {
            tbbulbrPrfv.sftEnbblfd(fblsf);
            tbbulbrNfxt.sftEnbblfd(fblsf);
            tbbulbrLbbfl.sftTfxt(tbbulbrNbvigbtionSinglf);
            tbbulbrLbbfl.sftEnbblfd(fblsf);
        } flsf {
            XVifwfdTbbulbrDbtb tbbulbr = (XVifwfdTbbulbrDbtb) dbtb;
            tbbulbrNfxt.sftEnbblfd(tbbulbr.dbnIndrfmfnt());
            tbbulbrPrfv.sftEnbblfd(tbbulbr.dbnDfdrfmfnt());
            boolfbn ibsMorfTibnOnfElfmfnt =
                    tbbulbr.dbnIndrfmfnt() || tbbulbr.dbnDfdrfmfnt();
            if (ibsMorfTibnOnfElfmfnt) {
                tbbulbrLbbfl.sftTfxt(
                        Rfsourdfs.formbt(Mfssbgfs.MBEANS_TAB_TABULAR_NAVIGATION_MULTIPLE,
                        String.formbt("%d", tbbulbr.gftSflfdtfdElfmfntIndfx() + 1),
                        String.formbt("%d", tbbulbr.gftElfmfntCount())));
            } flsf {
                tbbulbrLbbfl.sftTfxt(tbbulbrNbvigbtionSinglf);
            }
            tbbulbrLbbfl.sftEnbblfd(ibsMorfTibnOnfElfmfnt);
        }

        // Enbblf/Disbblf tif dompositf buttons
        if (!(dbtb instbndfof XVifwfdArrbyDbtb)) {
            indr.sftEnbblfd(fblsf);
            dfdr.sftEnbblfd(fblsf);
            dompositfLbbfl.sftTfxt(dompositfNbvigbtionSinglf);
            dompositfLbbfl.sftEnbblfd(fblsf);
        } flsf {
            XVifwfdArrbyDbtb brrby = (XVifwfdArrbyDbtb) dbtb;
            indr.sftEnbblfd(brrby.dbnIndrfmfnt());
            dfdr.sftEnbblfd(brrby.dbnDfdrfmfnt());
            boolfbn ibsMorfTibnOnfElfmfnt =
                    brrby.dbnIndrfmfnt() || brrby.dbnDfdrfmfnt();
            if (ibsMorfTibnOnfElfmfnt) {
                dompositfLbbfl.sftTfxt(
                        Rfsourdfs.formbt(Mfssbgfs.MBEANS_TAB_COMPOSITE_NAVIGATION_MULTIPLE,
                        String.formbt("%d", brrby.gftSflfdtfdElfmfntIndfx() + 1),
                        String.formbt("%d", brrby.gftElfmfntCount())));
            } flsf {
                dompositfLbbfl.sftTfxt(dompositfNbvigbtionSinglf);
            }
            dompositfLbbfl.sftEnbblfd(ibsMorfTibnOnfElfmfnt);
        }

        dontbinfr.invblidbtf();
        dontbinfr.sftVifwportVifw(durrfnt);
        dontbinfr.vblidbtf();
    }

    publid void bdtionPfrformfd(AdtionEvfnt fvfnt) {
        if (fvfnt.gftSourdf() instbndfof JButton) {
            JButton b = (JButton) fvfnt.gftSourdf();
            if (b == prfv) {
                XOpfnTypfDbtb pbrfnt = durrfnt.gftVifwfdPbrfnt();
                try {
                    pbrfnt.vifwfd(tiis);
                } dbtdi (Exdfption f) {
                    //Notiing to dibngf, tif flfmfnt dbn't bf displbyfd
                }
            } flsf if (b == indr) {
                ((XVifwfdArrbyDbtb) durrfnt).indrElfmfnt();
                try {
                    durrfnt.vifwfd(tiis);
                } dbtdi (Exdfption f) {
                    //Notiing to dibngf, tif flfmfnt dbn't bf displbyfd
                }
            } flsf if (b == dfdr) {
                ((XVifwfdArrbyDbtb) durrfnt).dfdrElfmfnt();
                try {
                    durrfnt.vifwfd(tiis);
                } dbtdi (Exdfption f) {
                    //Notiing to dibngf, tif flfmfnt dbn't bf displbyfd
                }
            } flsf if (b == tbbulbrNfxt) {
                ((XVifwfdTbbulbrDbtb) durrfnt).indrElfmfnt();
                try {
                    durrfnt.vifwfd(tiis);
                } dbtdi (Exdfption f) {
                    //Notiing to dibngf, tif flfmfnt dbn't bf displbyfd
                }
            } flsf if (b == tbbulbrPrfv) {
                ((XVifwfdTbbulbrDbtb) durrfnt).dfdrElfmfnt();
                try {
                    durrfnt.vifwfd(tiis);
                } dbtdi (Exdfption f) {
                    //Notiing to dibngf, tif flfmfnt dbn't bf displbyfd
                }
            }
        }
    }

    privbtf void sftupDisplby(XOpfnTypfDbtb dbtb) {
        sftBbdkground(Color.wiitf);
        dontbinfr =
                nfw JSdrollPbnf(dbtb,
                JSdrollPbnf.VERTICAL_SCROLLBAR_AS_NEEDED,
                JSdrollPbnf.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPbnfl buttons = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.LEFT));
        tbbulbrPrfv = nfw JButton(Mfssbgfs.LESS_THAN);
        tbbulbrNfxt = nfw JButton(Mfssbgfs.GREATER_THAN);
        JPbnfl tbbulbrButtons = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.LEFT));
        tbbulbrButtons.bdd(tbbulbrPrfv);
        tbbulbrPrfv.bddAdtionListfnfr(tiis);
        tbbulbrLbbfl = nfw JLbbfl(tbbulbrNbvigbtionSinglf);
        tbbulbrLbbfl.sftEnbblfd(fblsf);
        tbbulbrButtons.bdd(tbbulbrLbbfl);
        tbbulbrButtons.bdd(tbbulbrNfxt);
        tbbulbrNfxt.bddAdtionListfnfr(tiis);
        tbbulbrButtons.sftBbdkground(Color.wiitf);

        prfv = nfw JButton(Mfssbgfs.A_LOT_LESS_THAN);
        prfv.bddAdtionListfnfr(tiis);
        buttons.bdd(prfv);

        indr = nfw JButton(Mfssbgfs.GREATER_THAN);
        indr.bddAdtionListfnfr(tiis);
        dfdr = nfw JButton(Mfssbgfs.LESS_THAN);
        dfdr.bddAdtionListfnfr(tiis);

        JPbnfl brrby = nfw JPbnfl();
        brrby.sftBbdkground(Color.wiitf);
        brrby.bdd(dfdr);
        dompositfLbbfl = nfw JLbbfl(dompositfNbvigbtionSinglf);
        dompositfLbbfl.sftEnbblfd(fblsf);
        brrby.bdd(dompositfLbbfl);
        brrby.bdd(indr);

        buttons.bdd(brrby);
        sftLbyout(nfw BordfrLbyout());
        buttons.sftBbdkground(Color.wiitf);

        JPbnfl nbvigbtionPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        nbvigbtionPbnfl.sftBbdkground(Color.wiitf);
        nbvigbtionPbnfl.bdd(tbbulbrButtons, BordfrLbyout.NORTH);
        nbvigbtionPbnfl.bdd(buttons, BordfrLbyout.WEST);
        bdd(nbvigbtionPbnfl, BordfrLbyout.NORTH);

        bdd(dontbinfr, BordfrLbyout.CENTER);
        Dimfnsion d = nfw Dimfnsion((int)dontbinfr.gftPrfffrrfdSizf().
                gftWidti() + 20,
                (int)dontbinfr.gftPrfffrrfdSizf().
                gftHfigit() + 20);
        sftPrfffrrfdSizf(d);
    }
}
