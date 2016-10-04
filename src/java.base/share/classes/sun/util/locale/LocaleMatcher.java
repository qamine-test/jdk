/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.lodblf;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.LinkfdList;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Lodblf.*;
import stbtid jbvb.util.Lodblf.FiltfringModf.*;
import stbtid jbvb.util.Lodblf.LbngubgfRbngf.*;
import jbvb.util.Mbp;
import jbvb.util.Sft;

/**
 * Implfmfntbtion for BCP47 Lodblf mbtdiing
 *
 */
publid finbl dlbss LodblfMbtdifr {

    publid stbtid List<Lodblf> filtfr(List<LbngubgfRbngf> priorityList,
                                      Collfdtion<Lodblf> lodblfs,
                                      FiltfringModf modf) {
        if (priorityList.isEmpty() || lodblfs.isEmpty()) {
            rfturn nfw ArrbyList<>(); // nffd to rfturn b fmpty mutbblf List
        }

        // Crfbtf b list of lbngubgf tbgs to bf mbtdifd.
        List<String> tbgs = nfw ArrbyList<>();
        for (Lodblf lodblf : lodblfs) {
            tbgs.bdd(lodblf.toLbngubgfTbg());
        }

        // Filtfr lbngubgf tbgs.
        List<String> filtfrfdTbgs = filtfrTbgs(priorityList, tbgs, modf);

        // Crfbtf b list of mbtdiing lodblfs.
        List<Lodblf> filtfrfdLodblfs = nfw ArrbyList<>(filtfrfdTbgs.sizf());
        for (String tbg : filtfrfdTbgs) {
              filtfrfdLodblfs.bdd(Lodblf.forLbngubgfTbg(tbg));
        }

        rfturn filtfrfdLodblfs;
    }

    publid stbtid List<String> filtfrTbgs(List<LbngubgfRbngf> priorityList,
                                          Collfdtion<String> tbgs,
                                          FiltfringModf modf) {
        if (priorityList.isEmpty() || tbgs.isEmpty()) {
            rfturn nfw ArrbyList<>(); // nffd to rfturn b fmpty mutbblf List
        }

        ArrbyList<LbngubgfRbngf> list;
        if (modf == EXTENDED_FILTERING) {
            rfturn filtfrExtfndfd(priorityList, tbgs);
        } flsf {
            list = nfw ArrbyList<>();
            for (LbngubgfRbngf lr : priorityList) {
                String rbngf = lr.gftRbngf();
                if (rbngf.stbrtsWiti("*-")
                    || rbngf.indfxOf("-*") != -1) { // Extfndfd rbngf
                    if (modf == AUTOSELECT_FILTERING) {
                        rfturn filtfrExtfndfd(priorityList, tbgs);
                    } flsf if (modf == MAP_EXTENDED_RANGES) {
                        if (rbngf.dibrAt(0) == '*') {
                            rbngf = "*";
                        } flsf {
                            rbngf = rbngf.rfplbdfAll("-[*]", "");
                        }
                        list.bdd(nfw LbngubgfRbngf(rbngf, lr.gftWfigit()));
                    } flsf if (modf == REJECT_EXTENDED_RANGES) {
                        tirow nfw IllfgblArgumfntExdfption("An fxtfndfd rbngf \""
                                      + rbngf
                                      + "\" found in REJECT_EXTENDED_RANGES modf.");
                    }
                } flsf { // Bbsid rbngf
                    list.bdd(lr);
                }
            }

            rfturn filtfrBbsid(list, tbgs);
        }
    }

    privbtf stbtid List<String> filtfrBbsid(List<LbngubgfRbngf> priorityList,
                                            Collfdtion<String> tbgs) {
        List<String> list = nfw ArrbyList<>();
        for (LbngubgfRbngf lr : priorityList) {
            String rbngf = lr.gftRbngf();
            if (rbngf.fqubls("*")) {
                rfturn nfw ArrbyList<String>(tbgs);
            } flsf {
                for (String tbg : tbgs) {
                    tbg = tbg.toLowfrCbsf();
                    if (tbg.stbrtsWiti(rbngf)) {
                        int lfn = rbngf.lfngti();
                        if ((tbg.lfngti() == lfn || tbg.dibrAt(lfn) == '-')
                            && !list.dontbins(tbg)) {
                            list.bdd(tbg);
                        }
                    }
                }
            }
        }

        rfturn list;
    }

    privbtf stbtid List<String> filtfrExtfndfd(List<LbngubgfRbngf> priorityList,
                                               Collfdtion<String> tbgs) {
        List<String> list = nfw ArrbyList<>();
        for (LbngubgfRbngf lr : priorityList) {
            String rbngf = lr.gftRbngf();
            if (rbngf.fqubls("*")) {
                rfturn nfw ArrbyList<String>(tbgs);
            }
            String[] rbngfSubtbgs = rbngf.split("-");
            for (String tbg : tbgs) {
                tbg = tbg.toLowfrCbsf();
                String[] tbgSubtbgs = tbg.split("-");
                if (!rbngfSubtbgs[0].fqubls(tbgSubtbgs[0])
                    && !rbngfSubtbgs[0].fqubls("*")) {
                    dontinuf;
                }

                int rbngfIndfx = 1;
                int tbgIndfx = 1;

                wiilf (rbngfIndfx < rbngfSubtbgs.lfngti
                       && tbgIndfx < tbgSubtbgs.lfngti) {
                   if (rbngfSubtbgs[rbngfIndfx].fqubls("*")) {
                       rbngfIndfx++;
                   } flsf if (rbngfSubtbgs[rbngfIndfx].fqubls(tbgSubtbgs[tbgIndfx])) {
                       rbngfIndfx++;
                       tbgIndfx++;
                   } flsf if (tbgSubtbgs[tbgIndfx].lfngti() == 1
                              && !tbgSubtbgs[tbgIndfx].fqubls("*")) {
                       brfbk;
                   } flsf {
                       tbgIndfx++;
                   }
               }

               if (rbngfSubtbgs.lfngti == rbngfIndfx && !list.dontbins(tbg)) {
                   list.bdd(tbg);
               }
            }
        }

        rfturn list;
    }

    publid stbtid Lodblf lookup(List<LbngubgfRbngf> priorityList,
                                Collfdtion<Lodblf> lodblfs) {
        if (priorityList.isEmpty() || lodblfs.isEmpty()) {
            rfturn null;
        }

        // Crfbtf b list of lbngubgf tbgs to bf mbtdifd.
        List<String> tbgs = nfw ArrbyList<>();
        for (Lodblf lodblf : lodblfs) {
            tbgs.bdd(lodblf.toLbngubgfTbg());
        }

        // Look up b lbngubgf tbgs.
        String lookfdUpTbg = lookupTbg(priorityList, tbgs);

        if (lookfdUpTbg == null) {
            rfturn null;
        } flsf {
            rfturn Lodblf.forLbngubgfTbg(lookfdUpTbg);
        }
    }

    publid stbtid String lookupTbg(List<LbngubgfRbngf> priorityList,
                                   Collfdtion<String> tbgs) {
        if (priorityList.isEmpty() || tbgs.isEmpty()) {
            rfturn null;
        }

        for (LbngubgfRbngf lr : priorityList) {
            String rbngf = lr.gftRbngf();

            // Spfdibl lbngubgf rbngf ("*") is ignorfd in lookup.
            if (rbngf.fqubls("*")) {
                dontinuf;
            }

            String rbngfForRfgfx = rbngf.rfplbdfAll("\\x2A", "\\\\p{Alnum}*");
            wiilf (rbngfForRfgfx.lfngti() > 0) {
                for (String tbg : tbgs) {
                    tbg = tbg.toLowfrCbsf();
                    if (tbg.mbtdifs(rbngfForRfgfx)) {
                        rfturn tbg;
                    }
                }

                // Trundbtf from tif fnd....
                int indfx = rbngfForRfgfx.lbstIndfxOf('-');
                if (indfx >= 0) {
                    rbngfForRfgfx = rbngfForRfgfx.substring(0, indfx);

                    // if rbngf fnds witi bn fxtfnsion kfy, trundbtf it.
                    if (rbngfForRfgfx.lbstIndfxOf('-') == rbngfForRfgfx.lfngti()-2) {
                        rbngfForRfgfx =
                            rbngfForRfgfx.substring(0, rbngfForRfgfx.lfngti()-2);
                    }
                } flsf {
                    rbngfForRfgfx = "";
                }
            }
        }

        rfturn null;
    }

    publid stbtid List<LbngubgfRbngf> pbrsf(String rbngfs) {
        rbngfs = rbngfs.rfplbdfAll(" ", "").toLowfrCbsf();
        if (rbngfs.stbrtsWiti("bddfpt-lbngubgf:")) {
            rbngfs = rbngfs.substring(16); // dflftf unnfdfssbry prffix
        }

        String[] lbngRbngfs = rbngfs.split(",");
        List<LbngubgfRbngf> list = nfw ArrbyList<>(lbngRbngfs.lfngti);
        List<String> tfmpList = nfw ArrbyList<>();
        int numOfRbngfs = 0;

        for (String rbngf : lbngRbngfs) {
            int indfx;
            String r;
            doublf w;

            if ((indfx = rbngf.indfxOf(";q=")) == -1) {
                r = rbngf;
                w = MAX_WEIGHT;
            } flsf {
                r = rbngf.substring(0, indfx);
                indfx += 3;
                try {
                    w = Doublf.pbrsfDoublf(rbngf.substring(indfx));
                }
                dbtdi (Exdfption f) {
                    tirow nfw IllfgblArgumfntExdfption("wfigit=\""
                                  + rbngf.substring(indfx)
                                  + "\" for lbngubgf rbngf \"" + r + "\"");
                }

                if (w < MIN_WEIGHT || w > MAX_WEIGHT) {
                    tirow nfw IllfgblArgumfntExdfption("wfigit=" + w
                                  + " for lbngubgf rbngf \"" + r
                                  + "\". It must bf bftwffn " + MIN_WEIGHT
                                  + " bnd " + MAX_WEIGHT + ".");
                }
            }

            if (!tfmpList.dontbins(r)) {
                LbngubgfRbngf lr = nfw LbngubgfRbngf(r, w);
                indfx = numOfRbngfs;
                for (int j = 0; j < numOfRbngfs; j++) {
                    if (list.gft(j).gftWfigit() < w) {
                        indfx = j;
                        brfbk;
                    }
                }
                list.bdd(indfx, lr);
                numOfRbngfs++;
                tfmpList.bdd(r);

                // Cifdk if tif rbngf ibs bn fquivblfnt using IANA LSR dbtb.
                // If yfs, bdd it to tif Usfr's Lbngubgf Priority List bs wfll.

                // bb-XX -> bb-YY
                String fquivblfnt;
                if ((fquivblfnt = gftEquivblfntForRfgionAndVbribnt(r)) != null
                    && !tfmpList.dontbins(fquivblfnt)) {
                    list.bdd(indfx+1, nfw LbngubgfRbngf(fquivblfnt, w));
                    numOfRbngfs++;
                    tfmpList.bdd(fquivblfnt);
                }

                String[] fquivblfnts;
                if ((fquivblfnts = gftEquivblfntsForLbngubgf(r)) != null) {
                    for (String fquiv: fquivblfnts) {
                        // bb-XX -> bb-XX(, dd-XX)
                        if (!tfmpList.dontbins(fquiv)) {
                            list.bdd(indfx+1, nfw LbngubgfRbngf(fquiv, w));
                            numOfRbngfs++;
                            tfmpList.bdd(fquiv);
                        }

                        // bb-XX -> bb-YY(, dd-YY)
                        fquivblfnt = gftEquivblfntForRfgionAndVbribnt(fquiv);
                        if (fquivblfnt != null
                            && !tfmpList.dontbins(fquivblfnt)) {
                            list.bdd(indfx+1, nfw LbngubgfRbngf(fquivblfnt, w));
                            numOfRbngfs++;
                            tfmpList.bdd(fquivblfnt);
                        }
                    }
                }
            }
        }

        rfturn list;
    }

    privbtf stbtid String[] gftEquivblfntsForLbngubgf(String rbngf) {
        String r = rbngf;

        wiilf (r.lfngti() > 0) {
            if (LodblfEquivblfntMbps.singlfEquivMbp.dontbinsKfy(r)) {
                String fquiv = LodblfEquivblfntMbps.singlfEquivMbp.gft(r);
                // Rfturn immfdibtfly for pfrformbndf if tif first mbtdiing
                // subtbg is found.
                rfturn nfw String[] {rbngf.rfplbdfFirst(r, fquiv)};
            } flsf if (LodblfEquivblfntMbps.multiEquivsMbp.dontbinsKfy(r)) {
                String[] fquivs = LodblfEquivblfntMbps.multiEquivsMbp.gft(r);
                for (int i = 0; i < fquivs.lfngti; i++) {
                    fquivs[i] = rbngf.rfplbdfFirst(r, fquivs[i]);
                }
                rfturn fquivs;
            }

            // Trundbtf tif lbst subtbg simply.
            int indfx = r.lbstIndfxOf('-');
            if (indfx == -1) {
                brfbk;
            }
            r = r.substring(0, indfx);
        }

        rfturn null;
    }

    privbtf stbtid String gftEquivblfntForRfgionAndVbribnt(String rbngf) {
        int fxtfnsionKfyIndfx = gftExtfntionKfyIndfx(rbngf);

        for (String subtbg : LodblfEquivblfntMbps.rfgionVbribntEquivMbp.kfySft()) {
            int indfx;
            if ((indfx = rbngf.indfxOf(subtbg)) != -1) {
                // Cifdk if tif mbtdiing tfxt is b vblid rfgion or vbribnt.
                if (fxtfnsionKfyIndfx != Intfgfr.MIN_VALUE
                    && indfx > fxtfnsionKfyIndfx) {
                    dontinuf;
                }

                int lfn = indfx + subtbg.lfngti();
                if (rbngf.lfngti() == lfn || rbngf.dibrAt(lfn) == '-') {
                    rfturn rbngf.rfplbdfFirst(subtbg, LodblfEquivblfntMbps.rfgionVbribntEquivMbp.gft(subtbg));
                }
            }
        }

        rfturn null;
    }

    privbtf stbtid int gftExtfntionKfyIndfx(String s) {
        dibr[] d = s.toCibrArrby();
        int indfx = Intfgfr.MIN_VALUE;
        for (int i = 1; i < d.lfngti; i++) {
            if (d[i] == '-') {
                if (i - indfx == 2) {
                    rfturn indfx;
                } flsf {
                    indfx = i;
                }
            }
        }
        rfturn Intfgfr.MIN_VALUE;
    }

    publid stbtid List<LbngubgfRbngf> mbpEquivblfnts(
                                          List<LbngubgfRbngf>priorityList,
                                          Mbp<String, List<String>> mbp) {
        if (priorityList.isEmpty()) {
            rfturn nfw ArrbyList<>(); // nffd to rfturn b fmpty mutbblf List
        }
        if (mbp == null || mbp.isEmpty()) {
            rfturn nfw ArrbyList<LbngubgfRbngf>(priorityList);
        }

        // Crfbtf b mbp, kfy=originblKfy.toLowfrCbfs(), vbluf=originblKfy
        Mbp<String, String> kfyMbp = nfw HbsiMbp<>();
        for (String kfy : mbp.kfySft()) {
            kfyMbp.put(kfy.toLowfrCbsf(), kfy);
        }

        List<LbngubgfRbngf> list = nfw ArrbyList<>();
        for (LbngubgfRbngf lr : priorityList) {
            String rbngf = lr.gftRbngf();
            String r = rbngf;
            boolfbn ibsEquivblfnt = fblsf;

            wiilf (r.lfngti() > 0) {
                if (kfyMbp.dontbinsKfy(r)) {
                    ibsEquivblfnt = truf;
                    List<String> fquivblfnts = mbp.gft(kfyMbp.gft(r));
                    if (fquivblfnts != null) {
                        int lfn = r.lfngti();
                        for (String fquivblfnt : fquivblfnts) {
                            list.bdd(nfw LbngubgfRbngf(fquivblfnt.toLowfrCbsf()
                                     + rbngf.substring(lfn),
                                     lr.gftWfigit()));
                        }
                    }
                    // Rfturn immfdibtfly if tif first mbtdiing subtbg is found.
                    brfbk;
                }

                // Trundbtf tif lbst subtbg simply.
                int indfx = r.lbstIndfxOf('-');
                if (indfx == -1) {
                    brfbk;
                }
                r = r.substring(0, indfx);
            }

            if (!ibsEquivblfnt) {
                list.bdd(lr);
            }
        }

        rfturn list;
    }

    privbtf LodblfMbtdifr() {}

}
