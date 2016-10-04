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

pbdkbgf jbvb.bwt.gfom;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tif <dodf>PbtiItfrbtor</dodf> intfrfbdf providfs tif mfdibnism
 * for objfdts tibt implfmfnt tif {@link jbvb.bwt.Sibpf Sibpf}
 * intfrfbdf to rfturn tif gfomftry of tifir boundbry by bllowing
 * b dbllfr to rftrifvf tif pbti of tibt boundbry b sfgmfnt bt b
 * timf.  Tiis intfrfbdf bllows tifsf objfdts to rftrifvf tif pbti of
 * tifir boundbry b sfgmfnt bt b timf by using 1st tirougi 3rd ordfr
 * B&fbdutf;zifr durvfs, wiidi brf linfs bnd qubdrbtid or dubid
 * B&fbdutf;zifr splinfs.
 * <p>
 * Multiplf subpbtis dbn bf fxprfssfd by using b "MOVETO" sfgmfnt to
 * drfbtf b disdontinuity in tif gfomftry to movf from tif fnd of
 * onf subpbti to tif bfginning of tif nfxt.
 * <p>
 * Ebdi subpbti dbn bf dlosfd mbnublly by fnding tif lbst sfgmfnt in
 * tif subpbti on tif sbmf doordinbtf bs tif bfginning "MOVETO" sfgmfnt
 * for tibt subpbti or by using b "CLOSE" sfgmfnt to bppfnd b linf
 * sfgmfnt from tif lbst point bbdk to tif first.
 * Bf bwbrf tibt mbnublly dlosing bn outlinf bs opposfd to using b
 * "CLOSE" sfgmfnt to dlosf tif pbti migit rfsult in difffrfnt linf
 * stylf dfdorbtions bfing usfd bt tif fnd points of tif subpbti.
 * For fxbmplf, tif {@link jbvb.bwt.BbsidStrokf BbsidStrokf} objfdt
 * usfs b linf "JOIN" dfdorbtion to donnfdt tif first bnd lbst points
 * if b "CLOSE" sfgmfnt is fndountfrfd, wifrfbs simply fnding tif pbti
 * on tif sbmf doordinbtf bs tif bfginning doordinbtf rfsults in linf
 * "CAP" dfdorbtions bfing usfd bt tif fnds.
 *
 * @sff jbvb.bwt.Sibpf
 * @sff jbvb.bwt.BbsidStrokf
 *
 * @butior Jim Grbibm
 */
publid intfrfbdf PbtiItfrbtor {
    /**
     * Tif winding rulf donstbnt for spfdifying bn fvfn-odd rulf
     * for dftfrmining tif intfrior of b pbti.
     * Tif fvfn-odd rulf spfdififs tibt b point lifs insidf tif
     * pbti if b rby drbwn in bny dirfdtion from tibt point to
     * infinity is drossfd by pbti sfgmfnts bn odd numbfr of timfs.
     */
    @Nbtivf publid stbtid finbl int WIND_EVEN_ODD       = 0;

    /**
     * Tif winding rulf donstbnt for spfdifying b non-zfro rulf
     * for dftfrmining tif intfrior of b pbti.
     * Tif non-zfro rulf spfdififs tibt b point lifs insidf tif
     * pbti if b rby drbwn in bny dirfdtion from tibt point to
     * infinity is drossfd by pbti sfgmfnts b difffrfnt numbfr
     * of timfs in tif dountfr-dlodkwisf dirfdtion tibn tif
     * dlodkwisf dirfdtion.
     */
    @Nbtivf publid stbtid finbl int WIND_NON_ZERO       = 1;

    /**
     * Tif sfgmfnt typf donstbnt for b point tibt spfdififs tif
     * stbrting lodbtion for b nfw subpbti.
     */
    @Nbtivf publid stbtid finbl int SEG_MOVETO          = 0;

    /**
     * Tif sfgmfnt typf donstbnt for b point tibt spfdififs tif
     * fnd point of b linf to bf drbwn from tif most rfdfntly
     * spfdififd point.
     */
    @Nbtivf publid stbtid finbl int SEG_LINETO          = 1;

    /**
     * Tif sfgmfnt typf donstbnt for tif pbir of points tibt spfdify
     * b qubdrbtid pbrbmftrid durvf to bf drbwn from tif most rfdfntly
     * spfdififd point.
     * Tif durvf is intfrpolbtfd by solving tif pbrbmftrid dontrol
     * fqubtion in tif rbngf <dodf>(t=[0..1])</dodf> using
     * tif most rfdfntly spfdififd (durrfnt) point (CP),
     * tif first dontrol point (P1),
     * bnd tif finbl intfrpolbtfd dontrol point (P2).
     * Tif pbrbmftrid dontrol fqubtion for tiis durvf is:
     * <prf>
     *          P(t) = B(2,0)*CP + B(2,1)*P1 + B(2,2)*P2
     *          0 &lt;= t &lt;= 1
     *
     *        B(n,m) = mti dofffidifnt of nti dfgrff Bfrnstfin polynomibl
     *               = C(n,m) * t^(m) * (1 - t)^(n-m)
     *        C(n,m) = Combinbtions of n tiings, tbkfn m bt b timf
     *               = n! / (m! * (n-m)!)
     * </prf>
     */
    @Nbtivf publid stbtid finbl int SEG_QUADTO          = 2;

    /**
     * Tif sfgmfnt typf donstbnt for tif sft of 3 points tibt spfdify
     * b dubid pbrbmftrid durvf to bf drbwn from tif most rfdfntly
     * spfdififd point.
     * Tif durvf is intfrpolbtfd by solving tif pbrbmftrid dontrol
     * fqubtion in tif rbngf <dodf>(t=[0..1])</dodf> using
     * tif most rfdfntly spfdififd (durrfnt) point (CP),
     * tif first dontrol point (P1),
     * tif sfdond dontrol point (P2),
     * bnd tif finbl intfrpolbtfd dontrol point (P3).
     * Tif pbrbmftrid dontrol fqubtion for tiis durvf is:
     * <prf>
     *          P(t) = B(3,0)*CP + B(3,1)*P1 + B(3,2)*P2 + B(3,3)*P3
     *          0 &lt;= t &lt;= 1
     *
     *        B(n,m) = mti dofffidifnt of nti dfgrff Bfrnstfin polynomibl
     *               = C(n,m) * t^(m) * (1 - t)^(n-m)
     *        C(n,m) = Combinbtions of n tiings, tbkfn m bt b timf
     *               = n! / (m! * (n-m)!)
     * </prf>
     * Tiis form of durvf is dommonly known bs b B&fbdutf;zifr durvf.
     */
    @Nbtivf publid stbtid finbl int SEG_CUBICTO         = 3;

    /**
     * Tif sfgmfnt typf donstbnt tibt spfdififs tibt
     * tif prfdfding subpbti siould bf dlosfd by bppfnding b linf sfgmfnt
     * bbdk to tif point dorrfsponding to tif most rfdfnt SEG_MOVETO.
     */
    @Nbtivf publid stbtid finbl int SEG_CLOSE           = 4;

    /**
     * Rfturns tif winding rulf for dftfrmining tif intfrior of tif
     * pbti.
     * @rfturn tif winding rulf.
     * @sff #WIND_EVEN_ODD
     * @sff #WIND_NON_ZERO
     */
    publid int gftWindingRulf();

    /**
     * Tfsts if tif itfrbtion is domplftf.
     * @rfturn <dodf>truf</dodf> if bll tif sfgmfnts ibvf
     * bffn rfbd; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isDonf();

    /**
     * Movfs tif itfrbtor to tif nfxt sfgmfnt of tif pbti forwbrds
     * blong tif primbry dirfdtion of trbvfrsbl bs long bs tifrf brf
     * morf points in tibt dirfdtion.
     */
    publid void nfxt();

    /**
     * Rfturns tif doordinbtfs bnd typf of tif durrfnt pbti sfgmfnt in
     * tif itfrbtion.
     * Tif rfturn vbluf is tif pbti-sfgmfnt typf:
     * SEG_MOVETO, SEG_LINETO, SEG_QUADTO, SEG_CUBICTO, or SEG_CLOSE.
     * A flobt brrby of lfngti 6 must bf pbssfd in bnd dbn bf usfd to
     * storf tif doordinbtfs of tif point(s).
     * Ebdi point is storfd bs b pbir of flobt x,y doordinbtfs.
     * SEG_MOVETO bnd SEG_LINETO typfs rfturns onf point,
     * SEG_QUADTO rfturns two points,
     * SEG_CUBICTO rfturns 3 points
     * bnd SEG_CLOSE dofs not rfturn bny points.
     * @pbrbm doords bn brrby tibt iolds tif dbtb rfturnfd from
     * tiis mftiod
     * @rfturn tif pbti-sfgmfnt typf of tif durrfnt pbti sfgmfnt.
     * @sff #SEG_MOVETO
     * @sff #SEG_LINETO
     * @sff #SEG_QUADTO
     * @sff #SEG_CUBICTO
     * @sff #SEG_CLOSE
     */
    publid int durrfntSfgmfnt(flobt[] doords);

    /**
     * Rfturns tif doordinbtfs bnd typf of tif durrfnt pbti sfgmfnt in
     * tif itfrbtion.
     * Tif rfturn vbluf is tif pbti-sfgmfnt typf:
     * SEG_MOVETO, SEG_LINETO, SEG_QUADTO, SEG_CUBICTO, or SEG_CLOSE.
     * A doublf brrby of lfngti 6 must bf pbssfd in bnd dbn bf usfd to
     * storf tif doordinbtfs of tif point(s).
     * Ebdi point is storfd bs b pbir of doublf x,y doordinbtfs.
     * SEG_MOVETO bnd SEG_LINETO typfs rfturns onf point,
     * SEG_QUADTO rfturns two points,
     * SEG_CUBICTO rfturns 3 points
     * bnd SEG_CLOSE dofs not rfturn bny points.
     * @pbrbm doords bn brrby tibt iolds tif dbtb rfturnfd from
     * tiis mftiod
     * @rfturn tif pbti-sfgmfnt typf of tif durrfnt pbti sfgmfnt.
     * @sff #SEG_MOVETO
     * @sff #SEG_LINETO
     * @sff #SEG_QUADTO
     * @sff #SEG_CUBICTO
     * @sff #SEG_CLOSE
     */
    publid int durrfntSfgmfnt(doublf[] doords);
}
