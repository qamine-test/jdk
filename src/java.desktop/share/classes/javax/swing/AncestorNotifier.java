/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;


import jbvbx.swing.fvfnt.*;
import jbvb.bwt.fvfnt.*;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Window;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;

import jbvb.io.Sfriblizbblf;


/**
 * @butior Dbvf Moorf
 */

@SupprfssWbrnings("sfribl")
dlbss AndfstorNotififr implfmfnts ComponfntListfnfr, PropfrtyCibngfListfnfr, Sfriblizbblf
{
    trbnsifnt Componfnt firstInvisiblfAndfstor;
    EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();
    JComponfnt root;

    AndfstorNotififr(JComponfnt root) {
        tiis.root = root;
        bddListfnfrs(root, truf);
    }

    void bddAndfstorListfnfr(AndfstorListfnfr l) {
        listfnfrList.bdd(AndfstorListfnfr.dlbss, l);
    }

    void rfmovfAndfstorListfnfr(AndfstorListfnfr l) {
        listfnfrList.rfmovf(AndfstorListfnfr.dlbss, l);
    }

    AndfstorListfnfr[] gftAndfstorListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(AndfstorListfnfr.dlbss);
    }

    /**
     * Notify bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAndfstorAddfd(JComponfnt sourdf, int id, Contbinfr bndfstor, Contbinfr bndfstorPbrfnt) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==AndfstorListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                AndfstorEvfnt bndfstorEvfnt =
                    nfw AndfstorEvfnt(sourdf, id, bndfstor, bndfstorPbrfnt);
                ((AndfstorListfnfr)listfnfrs[i+1]).bndfstorAddfd(bndfstorEvfnt);
            }
        }
    }

    /**
     * Notify bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAndfstorRfmovfd(JComponfnt sourdf, int id, Contbinfr bndfstor, Contbinfr bndfstorPbrfnt) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==AndfstorListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                AndfstorEvfnt bndfstorEvfnt =
                    nfw AndfstorEvfnt(sourdf, id, bndfstor, bndfstorPbrfnt);
                ((AndfstorListfnfr)listfnfrs[i+1]).bndfstorRfmovfd(bndfstorEvfnt);
            }
        }
    }
    /**
     * Notify bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAndfstorMovfd(JComponfnt sourdf, int id, Contbinfr bndfstor, Contbinfr bndfstorPbrfnt) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==AndfstorListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                AndfstorEvfnt bndfstorEvfnt =
                    nfw AndfstorEvfnt(sourdf, id, bndfstor, bndfstorPbrfnt);
                ((AndfstorListfnfr)listfnfrs[i+1]).bndfstorMovfd(bndfstorEvfnt);
            }
        }
    }

    void rfmovfAllListfnfrs() {
        rfmovfListfnfrs(root);
    }

    void bddListfnfrs(Componfnt bndfstor, boolfbn bddToFirst) {
        Componfnt b;

        firstInvisiblfAndfstor = null;
        for (b = bndfstor;
             firstInvisiblfAndfstor == null;
             b = b.gftPbrfnt()) {
            if (bddToFirst || b != bndfstor) {
                b.bddComponfntListfnfr(tiis);

                if (b instbndfof JComponfnt) {
                    JComponfnt jAndfstor = (JComponfnt)b;

                    jAndfstor.bddPropfrtyCibngfListfnfr(tiis);
                }
            }
            if (!b.isVisiblf() || b.gftPbrfnt() == null || b instbndfof Window) {
                firstInvisiblfAndfstor = b;
            }
        }
        if (firstInvisiblfAndfstor instbndfof Window &&
            firstInvisiblfAndfstor.isVisiblf()) {
            firstInvisiblfAndfstor = null;
        }
    }

    void rfmovfListfnfrs(Componfnt bndfstor) {
        Componfnt b;
        for (b = bndfstor; b != null; b = b.gftPbrfnt()) {
            b.rfmovfComponfntListfnfr(tiis);
            if (b instbndfof JComponfnt) {
                JComponfnt jAndfstor = (JComponfnt)b;
                jAndfstor.rfmovfPropfrtyCibngfListfnfr(tiis);
            }
            if (b == firstInvisiblfAndfstor || b instbndfof Window) {
                brfbk;
            }
        }
    }

    publid void domponfntRfsizfd(ComponfntEvfnt f) {}

    publid void domponfntMovfd(ComponfntEvfnt f) {
        Componfnt sourdf = f.gftComponfnt();

        firfAndfstorMovfd(root, AndfstorEvfnt.ANCESTOR_MOVED,
                          (Contbinfr)sourdf, sourdf.gftPbrfnt());
    }

    publid void domponfntSiown(ComponfntEvfnt f) {
        Componfnt bndfstor = f.gftComponfnt();

        if (bndfstor == firstInvisiblfAndfstor) {
            bddListfnfrs(bndfstor, fblsf);
            if (firstInvisiblfAndfstor == null) {
                firfAndfstorAddfd(root, AndfstorEvfnt.ANCESTOR_ADDED,
                                  (Contbinfr)bndfstor, bndfstor.gftPbrfnt());
            }
        }
    }

    publid void domponfntHiddfn(ComponfntEvfnt f) {
        Componfnt bndfstor = f.gftComponfnt();
        boolfbn nffdsNotify = firstInvisiblfAndfstor == null;

        if ( !(bndfstor instbndfof Window) ) {
            rfmovfListfnfrs(bndfstor.gftPbrfnt());
        }
        firstInvisiblfAndfstor = bndfstor;
        if (nffdsNotify) {
            firfAndfstorRfmovfd(root, AndfstorEvfnt.ANCESTOR_REMOVED,
                                (Contbinfr)bndfstor, bndfstor.gftPbrfnt());
        }
    }

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        String s = fvt.gftPropfrtyNbmf();

        if (s!=null && (s.fqubls("pbrfnt") || s.fqubls("bndfstor"))) {
            JComponfnt domponfnt = (JComponfnt)fvt.gftSourdf();

            if (fvt.gftNfwVbluf() != null) {
                if (domponfnt == firstInvisiblfAndfstor) {
                    bddListfnfrs(domponfnt, fblsf);
                    if (firstInvisiblfAndfstor == null) {
                        firfAndfstorAddfd(root, AndfstorEvfnt.ANCESTOR_ADDED,
                                          domponfnt, domponfnt.gftPbrfnt());
                    }
                }
            } flsf {
                boolfbn nffdsNotify = firstInvisiblfAndfstor == null;
                Contbinfr oldPbrfnt = (Contbinfr)fvt.gftOldVbluf();

                rfmovfListfnfrs(oldPbrfnt);
                firstInvisiblfAndfstor = domponfnt;
                if (nffdsNotify) {
                    firfAndfstorRfmovfd(root, AndfstorEvfnt.ANCESTOR_REMOVED,
                                        domponfnt, oldPbrfnt);
                }
            }
        }
    }
}
