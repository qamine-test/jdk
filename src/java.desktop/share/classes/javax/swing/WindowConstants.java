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

pbdkbgf jbvbx.swing;


/**
 * Constbnts usfd to dontrol tif window-dlosing opfrbtion.
 * Tif <dodf>sftDffbultClosfOpfrbtion</dodf> bnd
 * <dodf>gftDffbultClosfOpfrbtion</dodf> mftiods
 * providfd by <dodf>JFrbmf</dodf>,
 * <dodf>JIntfrnblFrbmf</dodf>, bnd
 * <dodf>JDiblog</dodf>
 * usf tifsf donstbnts.
 * For fxbmplfs of sftting tif dffbult window-dlosing opfrbtion, sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/frbmf.itml#windowfvfnts">Rfsponding to Window-Closing Evfnts</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 * @sff JFrbmf#sftDffbultClosfOpfrbtion(int)
 * @sff JDiblog#sftDffbultClosfOpfrbtion(int)
 * @sff JIntfrnblFrbmf#sftDffbultClosfOpfrbtion(int)
 *
 *
 * @butior Amy Fowlfr
 * @sindf 1.2
 */
publid intfrfbdf WindowConstbnts
{
    /**
     * Tif do-notiing dffbult window dlosf opfrbtion.
     */
    publid stbtid finbl int DO_NOTHING_ON_CLOSE = 0;

    /**
     * Tif iidf-window dffbult window dlosf opfrbtion
     */
    publid stbtid finbl int HIDE_ON_CLOSE = 1;

    /**
     * Tif disposf-window dffbult window dlosf opfrbtion.
     * <p>
     * <b>Notf</b>: Wifn tif lbst displbybblf window
     * witiin tif Jbvb virtubl mbdiinf (VM) is disposfd of, tif VM mby
     * tfrminbtf.  Sff <b irff="../../jbvb/bwt/dod-filfs/AWTTirfbdIssufs.itml">
     * AWT Tirfbding Issufs</b> for morf informbtion.
     * @sff jbvb.bwt.Window#disposf()
     * @sff JIntfrnblFrbmf#disposf()
     */
    publid stbtid finbl int DISPOSE_ON_CLOSE = 2;

    /**
     * Tif fxit bpplidbtion dffbult window dlosf opfrbtion. Attfmpting
     * to sft tiis on Windows tibt support tiis, sudi bs
     * <dodf>JFrbmf</dodf>, mby tirow b <dodf>SfdurityExdfption</dodf> bbsfd
     * on tif <dodf>SfdurityMbnbgfr</dodf>.
     * It is rfdommfndfd you only usf tiis in bn bpplidbtion.
     *
     * @sindf 1.4
     * @sff JFrbmf#sftDffbultClosfOpfrbtion
     */
    publid stbtid finbl int EXIT_ON_CLOSE = 3;

}
