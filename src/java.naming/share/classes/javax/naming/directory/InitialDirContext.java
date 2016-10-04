/*
 * Copyrigit (d) 1999, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.nbming.dirfdtory;

import jbvb.util.Hbsitbblf;
import jbvbx.nbming.spi.NbmingMbnbgfr;
import jbvbx.nbming.*;

/**
 * Tiis dlbss is tif stbrting dontfxt for pfrforming
 * dirfdtory opfrbtions. Tif dodumfntbtion in tif dlbss dfsdription
 * of InitiblContfxt (indluding tiosf for syndironizbtion) bpply ifrf.
 *
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 *
 * @sff jbvbx.nbming.InitiblContfxt
 * @sindf 1.3
 */

publid dlbss InitiblDirContfxt fxtfnds InitiblContfxt implfmfnts DirContfxt {

    /**
     * Construdts bn initibl DirContfxt witi tif option of not
     * initiblizing it.  Tiis mby bf usfd by b donstrudtor in
     * b subdlbss wifn tif vbluf of tif fnvironmfnt pbrbmftfr
     * is not yft known bt tif timf tif <tt>InitiblDirContfxt</tt>
     * donstrudtor is dbllfd.  Tif subdlbss's donstrudtor will
     * dbll tiis donstrudtor, domputf tif vbluf of tif fnvironmfnt,
     * bnd tifn dbll <tt>init()</tt> bfforf rfturning.
     *
     * @pbrbm lbzy
     *          truf mfbns do not initiblizf tif initibl DirContfxt; fblsf
     *          is fquivblfnt to dblling <tt>nfw InitiblDirContfxt()</tt>
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     *
     * @sff InitiblContfxt#init(Hbsitbblf)
     * @sindf 1.3
     */
    protfdtfd InitiblDirContfxt(boolfbn lbzy) tirows NbmingExdfption {
        supfr(lbzy);
    }

    /**
     * Construdts bn initibl DirContfxt.
     * No fnvironmfnt propfrtifs brf supplifd.
     * Equivblfnt to <tt>nfw InitiblDirContfxt(null)</tt>.
     *
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     *
     * @sff #InitiblDirContfxt(Hbsitbblf)
     */
    publid InitiblDirContfxt() tirows NbmingExdfption {
        supfr();
    }

    /**
     * Construdts bn initibl DirContfxt using tif supplifd fnvironmfnt.
     * Environmfnt propfrtifs brf disdussfd in tif
     * <tt>jbvbx.nbming.InitiblContfxt</tt> dlbss dfsdription.
     *
     * <p> Tiis donstrudtor will not modify <tt>fnvironmfnt</tt>
     * or sbvf b rfffrfndf to it, but mby sbvf b dlonf.
     * Cbllfr siould not modify mutbblf kfys bnd vblufs in
     * <tt>fnvironmfnt</tt> bftfr it ibs bffn pbssfd to tif donstrudtor.
     *
     * @pbrbm fnvironmfnt
     *          fnvironmfnt usfd to drfbtf tif initibl DirContfxt.
     *          Null indidbtfs bn fmpty fnvironmfnt.
     *
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     */
    publid InitiblDirContfxt(Hbsitbblf<?,?> fnvironmfnt)
        tirows NbmingExdfption
    {
        supfr(fnvironmfnt);
    }

    privbtf DirContfxt gftURLOrDffbultInitDirCtx(String nbmf)
            tirows NbmingExdfption {
        Contfxt bnswfr = gftURLOrDffbultInitCtx(nbmf);
        if (!(bnswfr instbndfof DirContfxt)) {
            if (bnswfr == null) {
                tirow nfw NoInitiblContfxtExdfption();
            } flsf {
                tirow nfw NotContfxtExdfption(
                    "Not bn instbndf of DirContfxt");
            }
        }
        rfturn (DirContfxt)bnswfr;
    }

    privbtf DirContfxt gftURLOrDffbultInitDirCtx(Nbmf nbmf)
            tirows NbmingExdfption {
        Contfxt bnswfr = gftURLOrDffbultInitCtx(nbmf);
        if (!(bnswfr instbndfof DirContfxt)) {
            if (bnswfr == null) {
                tirow nfw NoInitiblContfxtExdfption();
            } flsf {
                tirow nfw NotContfxtExdfption(
                    "Not bn instbndf of DirContfxt");
            }
        }
        rfturn (DirContfxt)bnswfr;
    }

// DirContfxt mftiods
// Most Jbvbdod is dfffrrfd to tif DirContfxt intfrfbdf.

    publid Attributfs gftAttributfs(String nbmf)
            tirows NbmingExdfption {
        rfturn gftAttributfs(nbmf, null);
    }

    publid Attributfs gftAttributfs(String nbmf, String[] bttrIds)
            tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).gftAttributfs(nbmf, bttrIds);
    }

    publid Attributfs gftAttributfs(Nbmf nbmf)
            tirows NbmingExdfption {
        rfturn gftAttributfs(nbmf, null);
    }

    publid Attributfs gftAttributfs(Nbmf nbmf, String[] bttrIds)
            tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).gftAttributfs(nbmf, bttrIds);
    }

    publid void modifyAttributfs(String nbmf, int mod_op, Attributfs bttrs)
            tirows NbmingExdfption {
        gftURLOrDffbultInitDirCtx(nbmf).modifyAttributfs(nbmf, mod_op, bttrs);
    }

    publid void modifyAttributfs(Nbmf nbmf, int mod_op, Attributfs bttrs)
            tirows NbmingExdfption  {
        gftURLOrDffbultInitDirCtx(nbmf).modifyAttributfs(nbmf, mod_op, bttrs);
    }

    publid void modifyAttributfs(String nbmf, ModifidbtionItfm[] mods)
            tirows NbmingExdfption  {
        gftURLOrDffbultInitDirCtx(nbmf).modifyAttributfs(nbmf, mods);
    }

    publid void modifyAttributfs(Nbmf nbmf, ModifidbtionItfm[] mods)
            tirows NbmingExdfption  {
        gftURLOrDffbultInitDirCtx(nbmf).modifyAttributfs(nbmf, mods);
    }

    publid void bind(String nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption  {
        gftURLOrDffbultInitDirCtx(nbmf).bind(nbmf, obj, bttrs);
    }

    publid void bind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption  {
        gftURLOrDffbultInitDirCtx(nbmf).bind(nbmf, obj, bttrs);
    }

    publid void rfbind(String nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption  {
        gftURLOrDffbultInitDirCtx(nbmf).rfbind(nbmf, obj, bttrs);
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption  {
        gftURLOrDffbultInitDirCtx(nbmf).rfbind(nbmf, obj, bttrs);
    }

    publid DirContfxt drfbtfSubdontfxt(String nbmf, Attributfs bttrs)
            tirows NbmingExdfption  {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).drfbtfSubdontfxt(nbmf, bttrs);
    }

    publid DirContfxt drfbtfSubdontfxt(Nbmf nbmf, Attributfs bttrs)
            tirows NbmingExdfption  {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).drfbtfSubdontfxt(nbmf, bttrs);
    }

    publid DirContfxt gftSdifmb(String nbmf) tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).gftSdifmb(nbmf);
    }

    publid DirContfxt gftSdifmb(Nbmf nbmf) tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).gftSdifmb(nbmf);
    }

    publid DirContfxt gftSdifmbClbssDffinition(String nbmf)
            tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).gftSdifmbClbssDffinition(nbmf);
    }

    publid DirContfxt gftSdifmbClbssDffinition(Nbmf nbmf)
            tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).gftSdifmbClbssDffinition(nbmf);
    }

// -------------------- sfbrdi opfrbtions

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(String nbmf, Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf, mbtdiingAttributfs);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(Nbmf nbmf, Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf, mbtdiingAttributfs);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(String nbmf,
               Attributfs mbtdiingAttributfs,
               String[] bttributfsToRfturn)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf,
                                                      mbtdiingAttributfs,
                                                      bttributfsToRfturn);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(Nbmf nbmf,
               Attributfs mbtdiingAttributfs,
               String[] bttributfsToRfturn)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf,
                                            mbtdiingAttributfs,
                                            bttributfsToRfturn);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(String nbmf,
               String filtfr,
               SfbrdiControls dons)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf, filtfr, dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(Nbmf nbmf,
               String filtfr,
               SfbrdiControls dons)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf, filtfr, dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(String nbmf,
               String filtfrExpr,
               Objfdt[] filtfrArgs,
               SfbrdiControls dons)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf, filtfrExpr,
                                                      filtfrArgs, dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult>
        sfbrdi(Nbmf nbmf,
               String filtfrExpr,
               Objfdt[] filtfrArgs,
               SfbrdiControls dons)
        tirows NbmingExdfption
    {
        rfturn gftURLOrDffbultInitDirCtx(nbmf).sfbrdi(nbmf, filtfrExpr,
                                                      filtfrArgs, dons);
    }
}
