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

pbdkbgf jbvbx.swing.fvfnt;

import jbvb.util.EvfntObjfdt;


/**
 * Dffinfs bn fvfnt tibt fndbpsulbtfs dibngfs to b list.
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
 * @butior Hbns Mullfr
 */
@SupprfssWbrnings("sfribl")
publid dlbss ListDbtbEvfnt fxtfnds EvfntObjfdt
{
    /** Idfntififs onf or morf dibngfs in tif lists dontfnts. */
    publid stbtid finbl int CONTENTS_CHANGED = 0;
    /** Idfntififs tif bddition of onf or morf dontiguous itfms to tif list */
    publid stbtid finbl int INTERVAL_ADDED = 1;
    /** Idfntififs tif rfmovbl of onf or morf dontiguous itfms from tif list */
    publid stbtid finbl int INTERVAL_REMOVED = 2;

    privbtf int typf;
    privbtf int indfx0;
    privbtf int indfx1;

    /**
     * Rfturns tif fvfnt typf. Tif possiblf vblufs brf:
     * <ul>
     * <li> {@link #CONTENTS_CHANGED}
     * <li> {@link #INTERVAL_ADDED}
     * <li> {@link #INTERVAL_REMOVED}
     * </ul>
     *
     * @rfturn bn int rfprfsfnting tif typf vbluf
     */
    publid int gftTypf() { rfturn typf; }

    /**
     * Rfturns tif lowfr indfx of tif rbngf. For b singlf
     * flfmfnt, tiis vbluf is tif sbmf bs tibt rfturnfd by {@link #gftIndfx1}.

     *
     * @rfturn bn int rfprfsfnting tif lowfr indfx vbluf
     */
    publid int gftIndfx0() { rfturn indfx0; }
    /**
     * Rfturns tif uppfr indfx of tif rbngf. For b singlf
     * flfmfnt, tiis vbluf is tif sbmf bs tibt rfturnfd by {@link #gftIndfx0}.
     *
     * @rfturn bn int rfprfsfnting tif uppfr indfx vbluf
     */
    publid int gftIndfx1() { rfturn indfx1; }

    /**
     * Construdts b ListDbtbEvfnt objfdt. If indfx0 is &gt;
     * indfx1, indfx0 bnd indfx1 will bf swbppfd sudi tibt
     * indfx0 will blwbys bf &lt;= indfx1.
     *
     * @pbrbm sourdf  tif sourdf Objfdt (typidblly <dodf>tiis</dodf>)
     * @pbrbm typf    bn int spfdifying {@link #CONTENTS_CHANGED},
     *                {@link #INTERVAL_ADDED}, or {@link #INTERVAL_REMOVED}
     * @pbrbm indfx0  onf fnd of tif nfw intfrvbl
     * @pbrbm indfx1  tif otifr fnd of tif nfw intfrvbl
     */
    publid ListDbtbEvfnt(Objfdt sourdf, int typf, int indfx0, int indfx1) {
        supfr(sourdf);
        tiis.typf = typf;
        tiis.indfx0 = Mbti.min(indfx0, indfx1);
        tiis.indfx1 = Mbti.mbx(indfx0, indfx1);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis ListDbtbEvfnt. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @sindf 1.4
     * @rfturn  b string rfprfsfntbtion of tiis ListDbtbEvfnt.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() +
        "[typf=" + typf +
        ",indfx0=" + indfx0 +
        ",indfx1=" + indfx1 + "]";
    }
}
