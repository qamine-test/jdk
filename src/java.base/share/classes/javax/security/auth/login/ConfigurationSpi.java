/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.sfdurity.buti.login;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif {@dodf Configurbtion} dlbss.
 * All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * sfrvidf providfr wio wisifs to supply b Configurbtion implfmfntbtion.
 *
 * <p> Subdlbss implfmfntbtions of tiis bbstrbdt dlbss must providf
 * b publid donstrudtor tibt tbkfs b {@dodf Configurbtion.Pbrbmftfrs}
 * objfdt bs bn input pbrbmftfr.  Tiis donstrudtor blso must tirow
 * bn IllfgblArgumfntExdfption if it dofs not undfrstbnd tif
 * {@dodf Configurbtion.Pbrbmftfrs} input.
 *
 *
 * @sindf 1.6
 */

publid bbstrbdt dlbss ConfigurbtionSpi {
    /**
     * Rftrifvf tif AppConfigurbtionEntrifs for tif spfdififd <i>nbmf</i>.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf usfd to indfx tif Configurbtion.
     *
     * @rfturn bn brrby of AppConfigurbtionEntrifs for tif spfdififd
     *          <i>nbmf</i>, or null if tifrf brf no fntrifs.
     */
    protfdtfd bbstrbdt AppConfigurbtionEntry[] fnginfGftAppConfigurbtionEntry
                                                        (String nbmf);

    /**
     * Rffrfsi bnd rflobd tif Configurbtion.
     *
     * <p> Tiis mftiod dbusfs tiis Configurbtion objfdt to rffrfsi/rflobd its
     * dontfnts in bn implfmfntbtion-dfpfndfnt mbnnfr.
     * For fxbmplf, if tiis Configurbtion objfdt storfs its fntrifs in b filf,
     * dblling {@dodf rffrfsi} mby dbusf tif filf to bf rf-rfbd.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod dofs notiing.
     * Tiis mftiod siould bf ovfrriddfn if b rffrfsi opfrbtion is supportfd
     * by tif implfmfntbtion.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to rffrfsi its Configurbtion.
     */
    protfdtfd void fnginfRffrfsi() { }
}
