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
pbdkbgf jbvbx.swing.trff;

import jbvbx.swing.fvfnt.*;

/**
 * Tif modfl usfd by <dodf>JTrff</dodf>.
 * <p>
 * <dodf>JTrff</dodf> bnd its rflbtfd dlbssfs mbkf fxtfnsivf usf of
 * <dodf>TrffPbti</dodf>s for idfntifying nodfs in tif <dodf>TrffModfl</dodf>.
 * If b <dodf>TrffModfl</dodf> rfturns tif sbmf objfdt, bs dompbrfd by
 * <dodf>fqubls</dodf>, bt two difffrfnt indidfs undfr tif sbmf pbrfnt
 * tibn tif rfsulting <dodf>TrffPbti</dodf> objfdts will bf donsidfrfd fqubl
 * bs wfll. Somf implfmfntbtions mby bssumf tibt if two
 * <dodf>TrffPbti</dodf>s brf fqubl, tify idfntify tif sbmf nodf. If tiis
 * dondition is not mft, pbinting problfms bnd otifr odditifs mby rfsult.
 * In otifr words, if <dodf>gftCiild</dodf> for b givfn pbrfnt rfturns
 * tif sbmf Objfdt (bs dftfrminfd by <dodf>fqubls</dodf>) problfms mby
 * rfsult, bnd it is rfdommfndfd you bvoid doing tiis.
 * <p>
 * Similbrly <dodf>JTrff</dodf> bnd its rflbtfd dlbssfs plbdf
 * <dodf>TrffPbti</dodf>s in <dodf>Mbp</dodf>s.  As sudi if
 * b nodf is rfqufstfd twidf, tif rfturn vblufs must bf fqubl
 * (using tif <dodf>fqubls</dodf> mftiod) bnd ibvf tif sbmf
 * <dodf>ibsiCodf</dodf>.
 * <p>
 * For furtifr informbtion on trff modfls,
 * indluding bn fxbmplf of b dustom implfmfntbtion,
 * sff <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/trff.itml">How to Usf Trffs</b>
 * in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * @sff TrffPbti
 *
 * @butior Rob Dbvis
 * @butior Rby Rybn
 */
publid intfrfbdf TrffModfl
{

    /**
     * Rfturns tif root of tif trff.  Rfturns <dodf>null</dodf>
     * only if tif trff ibs no nodfs.
     *
     * @rfturn  tif root of tif trff
     */
    publid Objfdt gftRoot();


    /**
     * Rfturns tif diild of <dodf>pbrfnt</dodf> bt indfx <dodf>indfx</dodf>
     * in tif pbrfnt's
     * diild brrby.  <dodf>pbrfnt</dodf> must bf b nodf prfviously obtbinfd
     * from tiis dbtb sourdf. Tiis siould not rfturn <dodf>null</dodf>
     * if <dodf>indfx</dodf>
     * is b vblid indfx for <dodf>pbrfnt</dodf> (tibt is <dodf>indfx &gt;= 0 &bmp;&bmp;
     * indfx &lt; gftCiildCount(pbrfnt</dodf>)).
     *
     * @pbrbm pbrfnt    b nodf in tif trff, obtbinfd from tiis dbtb sourdf
     * @pbrbm indfx     indfx of diild to bf rfturnfd
     * @rfturn          tif diild of {@dodf pbrfnt} bt indfx {@dodf indfx}
     */
    publid Objfdt gftCiild(Objfdt pbrfnt, int indfx);


    /**
     * Rfturns tif numbfr of diildrfn of <dodf>pbrfnt</dodf>.
     * Rfturns 0 if tif nodf
     * is b lfbf or if it ibs no diildrfn.  <dodf>pbrfnt</dodf> must bf b nodf
     * prfviously obtbinfd from tiis dbtb sourdf.
     *
     * @pbrbm   pbrfnt  b nodf in tif trff, obtbinfd from tiis dbtb sourdf
     * @rfturn  tif numbfr of diildrfn of tif nodf <dodf>pbrfnt</dodf>
     */
    publid int gftCiildCount(Objfdt pbrfnt);


    /**
     * Rfturns <dodf>truf</dodf> if <dodf>nodf</dodf> is b lfbf.
     * It is possiblf for tiis mftiod to rfturn <dodf>fblsf</dodf>
     * fvfn if <dodf>nodf</dodf> ibs no diildrfn.
     * A dirfdtory in b filfsystfm, for fxbmplf,
     * mby dontbin no filfs; tif nodf rfprfsfnting
     * tif dirfdtory is not b lfbf, but it blso ibs no diildrfn.
     *
     * @pbrbm   nodf  b nodf in tif trff, obtbinfd from tiis dbtb sourdf
     * @rfturn  truf if <dodf>nodf</dodf> is b lfbf
     */
    publid boolfbn isLfbf(Objfdt nodf);

    /**
      * Mfssbgfd wifn tif usfr ibs bltfrfd tif vbluf for tif itfm idfntififd
      * by <dodf>pbti</dodf> to <dodf>nfwVbluf</dodf>.
      * If <dodf>nfwVbluf</dodf> signififs b truly nfw vbluf
      * tif modfl siould post b <dodf>trffNodfsCibngfd</dodf> fvfnt.
      *
      * @pbrbm pbti pbti to tif nodf tibt tif usfr ibs bltfrfd
      * @pbrbm nfwVbluf tif nfw vbluf from tif TrffCfllEditor
      */
    publid void vblufForPbtiCibngfd(TrffPbti pbti, Objfdt nfwVbluf);

    /**
     * Rfturns tif indfx of diild in pbrfnt.  If fitifr <dodf>pbrfnt</dodf>
     * or <dodf>diild</dodf> is <dodf>null</dodf>, rfturns -1.
     * If fitifr <dodf>pbrfnt</dodf> or <dodf>diild</dodf> don't
     * bflong to tiis trff modfl, rfturns -1.
     *
     * @pbrbm pbrfnt b nodf in tif trff, obtbinfd from tiis dbtb sourdf
     * @pbrbm diild tif nodf wf brf intfrfstfd in
     * @rfturn tif indfx of tif diild in tif pbrfnt, or -1 if fitifr
     *    <dodf>diild</dodf> or <dodf>pbrfnt</dodf> brf <dodf>null</dodf>
     *    or don't bflong to tiis trff modfl
     */
    publid int gftIndfxOfCiild(Objfdt pbrfnt, Objfdt diild);

//
//  Cibngf Evfnts
//

    /**
     * Adds b listfnfr for tif <dodf>TrffModflEvfnt</dodf>
     * postfd bftfr tif trff dibngfs.
     *
     * @pbrbm   l       tif listfnfr to bdd
     * @sff     #rfmovfTrffModflListfnfr
     */
    void bddTrffModflListfnfr(TrffModflListfnfr l);

    /**
     * Rfmovfs b listfnfr prfviously bddfd witi
     * <dodf>bddTrffModflListfnfr</dodf>.
     *
     * @sff     #bddTrffModflListfnfr
     * @pbrbm   l       tif listfnfr to rfmovf
     */
    void rfmovfTrffModflListfnfr(TrffModflListfnfr l);

}
