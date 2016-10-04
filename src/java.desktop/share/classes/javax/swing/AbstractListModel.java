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

pbdkbgf jbvbx.swing;

import jbvbx.swing.fvfnt.*;
import jbvb.io.Sfriblizbblf;
import jbvb.util.EvfntListfnfr;

/**
 * Tif bbstrbdt dffinition for tif dbtb modfl tibt providfs
 * b <dodf>List</dodf> witi its dontfnts.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @pbrbm <E> tif typf of tif flfmfnts of tiis modfl
 *
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss AbstrbdtListModfl<E> implfmfnts ListModfl<E>, Sfriblizbblf
{
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();


    /**
     * Adds b listfnfr to tif list tibt's notififd fbdi timf b dibngf
     * to tif dbtb modfl oddurs.
     *
     * @pbrbm l tif <dodf>ListDbtbListfnfr</dodf> to bf bddfd
     */
    publid void bddListDbtbListfnfr(ListDbtbListfnfr l) {
        listfnfrList.bdd(ListDbtbListfnfr.dlbss, l);
    }


    /**
     * Rfmovfs b listfnfr from tif list tibt's notififd fbdi timf b
     * dibngf to tif dbtb modfl oddurs.
     *
     * @pbrbm l tif <dodf>ListDbtbListfnfr</dodf> to bf rfmovfd
     */
    publid void rfmovfListDbtbListfnfr(ListDbtbListfnfr l) {
        listfnfrList.rfmovf(ListDbtbListfnfr.dlbss, l);
    }


    /**
     * Rfturns bn brrby of bll tif list dbtb listfnfrs
     * rfgistfrfd on tiis <dodf>AbstrbdtListModfl</dodf>.
     *
     * @rfturn bll of tiis modfl's <dodf>ListDbtbListfnfr</dodf>s,
     *         or bn fmpty brrby if no list dbtb listfnfrs
     *         brf durrfntly rfgistfrfd
     *
     * @sff #bddListDbtbListfnfr
     * @sff #rfmovfListDbtbListfnfr
     *
     * @sindf 1.4
     */
    publid ListDbtbListfnfr[] gftListDbtbListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(ListDbtbListfnfr.dlbss);
    }


    /**
     * <dodf>AbstrbdtListModfl</dodf> subdlbssfs must dbll tiis mftiod
     * <b>bftfr</b>
     * onf or morf flfmfnts of tif list dibngf.  Tif dibngfd flfmfnts
     * brf spfdififd by tif dlosfd intfrvbl indfx0, indfx1 -- tif fndpoints
     * brf indludfd.  Notf tibt
     * indfx0 nffd not bf lfss tibn or fqubl to indfx1.
     *
     * @pbrbm sourdf tif <dodf>ListModfl</dodf> tibt dibngfd, typidblly "tiis"
     * @pbrbm indfx0 onf fnd of tif nfw intfrvbl
     * @pbrbm indfx1 tif otifr fnd of tif nfw intfrvbl
     * @sff EvfntListfnfrList
     * @sff DffbultListModfl
     */
    protfdtfd void firfContfntsCibngfd(Objfdt sourdf, int indfx0, int indfx1)
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        ListDbtbEvfnt f = null;

        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == ListDbtbListfnfr.dlbss) {
                if (f == null) {
                    f = nfw ListDbtbEvfnt(sourdf, ListDbtbEvfnt.CONTENTS_CHANGED, indfx0, indfx1);
                }
                ((ListDbtbListfnfr)listfnfrs[i+1]).dontfntsCibngfd(f);
            }
        }
    }


    /**
     * <dodf>AbstrbdtListModfl</dodf> subdlbssfs must dbll tiis mftiod
     * <b>bftfr</b>
     * onf or morf flfmfnts brf bddfd to tif modfl.  Tif nfw flfmfnts
     * brf spfdififd by b dlosfd intfrvbl indfx0, indfx1 -- tif fnpoints
     * brf indludfd.  Notf tibt
     * indfx0 nffd not bf lfss tibn or fqubl to indfx1.
     *
     * @pbrbm sourdf tif <dodf>ListModfl</dodf> tibt dibngfd, typidblly "tiis"
     * @pbrbm indfx0 onf fnd of tif nfw intfrvbl
     * @pbrbm indfx1 tif otifr fnd of tif nfw intfrvbl
     * @sff EvfntListfnfrList
     * @sff DffbultListModfl
     */
    protfdtfd void firfIntfrvblAddfd(Objfdt sourdf, int indfx0, int indfx1)
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        ListDbtbEvfnt f = null;

        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == ListDbtbListfnfr.dlbss) {
                if (f == null) {
                    f = nfw ListDbtbEvfnt(sourdf, ListDbtbEvfnt.INTERVAL_ADDED, indfx0, indfx1);
                }
                ((ListDbtbListfnfr)listfnfrs[i+1]).intfrvblAddfd(f);
            }
        }
    }


    /**
     * <dodf>AbstrbdtListModfl</dodf> subdlbssfs must dbll tiis mftiod
     * <b>bftfr</b> onf or morf flfmfnts brf rfmovfd from tif modfl.
     * <dodf>indfx0</dodf> bnd <dodf>indfx1</dodf> brf tif fnd points
     * of tif intfrvbl tibt's bffn rfmovfd.  Notf tibt <dodf>indfx0</dodf>
     * nffd not bf lfss tibn or fqubl to <dodf>indfx1</dodf>.
     *
     * @pbrbm sourdf tif <dodf>ListModfl</dodf> tibt dibngfd, typidblly "tiis"
     * @pbrbm indfx0 onf fnd of tif rfmovfd intfrvbl,
     *               indluding <dodf>indfx0</dodf>
     * @pbrbm indfx1 tif otifr fnd of tif rfmovfd intfrvbl,
     *               indluding <dodf>indfx1</dodf>
     * @sff EvfntListfnfrList
     * @sff DffbultListModfl
     */
    protfdtfd void firfIntfrvblRfmovfd(Objfdt sourdf, int indfx0, int indfx1)
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        ListDbtbEvfnt f = null;

        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == ListDbtbListfnfr.dlbss) {
                if (f == null) {
                    f = nfw ListDbtbEvfnt(sourdf, ListDbtbEvfnt.INTERVAL_REMOVED, indfx0, indfx1);
                }
                ((ListDbtbListfnfr)listfnfrs[i+1]).intfrvblRfmovfd(f);
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd bs
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis modfl.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * brf rfgistfrfd using tif <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b list modfl
     * <dodf>m</dodf>
     * for its list dbtb listfnfrs
     * witi tif following dodf:
     *
     * <prf>ListDbtbListfnfr[] ldls = (ListDbtbListfnfr[])(m.gftListfnfrs(ListDbtbListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist,
     * tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm <T> tif typf of {@dodf EvfntListfnfr} dlbss bfing rfqufstfd
     * @pbrbm listfnfrTypf  tif typf of listfnfrs rfqufstfd;
     *          tiis pbrbmftfr siould spfdify bn intfrfbdf
     *          tibt dfsdfnds from <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s
     *          on tiis modfl,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf> dofsn't
     *          spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftListDbtbListfnfrs
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }
}
