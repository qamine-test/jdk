/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdonsolf;

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.SwingWorkfr;

/**
 * A JConsolf plugin dlbss.  JConsolf usfs tif
 * <b irff="{@dodRoot}/../../../../bpi/jbvb/util/SfrvidfLobdfr.itml">
 * sfrvidf providfr</b> mfdibnism to sfbrdi tif JConsolf plugins.
 * Usfrs dbn providf tifir JConsolf plugins in b jbr filf
 * dontbining b filf nbmfd
 *
 * <blodkquotf><prf>
 * META-INF/sfrvidfs/dom.sun.tools.jdonsolf.JConsolfPlugin</prf></blodkquotf>
 *
 * <p> Tiis filf dontbins onf linf for fbdi plugin, for fxbmplf,
 *
 * <blodkquotf><prf>
 * dom.sun.fxbmplf.JTop</prf></blodkquotf>
 * <p> wiidi is tif fully qublififd dlbss nbmf of tif dlbss implfmfnting
 * {@dodf JConsolfPlugin}.
 *
 * <p> To lobd tif JConsolf plugins in JConsolf, run:
 *
 * <blodkquotf><prf>
 * jdonsolf -pluginpbti &lt;plugin-pbti&gt; </prf></blodkquotf>
 *
 * <p> wifrf <tt>&lt;plugin-pbti&gt;</tt> spfdififs tif pbtis of JConsolf
 * plugins to look up wiidi dbn bf b dirfdtory or b jbr filf. Multiplf
 * pbtis brf sfpbrbtfd by tif pbti sfpbrbtor dibrbdtfr of tif plbtform.
 *
 * <p> Wifn b nfw JConsolf window is drfbtfd for b donnfdtion,
 * bn instbndf of fbdi {@dodf JConsolfPlugin} will bf drfbtfd.
 * Tif {@dodf JConsolfContfxt} objfdt is not bvbilbblf bt its
 * donstrudtion timf.
 * JConsolf will sft tif {@link JConsolfContfxt} objfdt for
 * b plugin bftfr tif plugin objfdt is drfbtfd.  It will tifn
 * dbll its {@link #gftTbbs gftTbbs} mftiod bnd bdd tif rfturnfd
 * tbbs to tif JConsolf window.
 *
 * @sff <b irff="{@dodRoot}/../../../../bpi/jbvb/util/SfrvidfLobdfr.itml">
 * jbvb.util.SfrvidfLobdfr</b>
 *
 * @sindf 1.6
 */
@jdk.Exportfd
publid bbstrbdt dlbss JConsolfPlugin {
    privbtf volbtilf JConsolfContfxt dontfxt = null;
    privbtf List<PropfrtyCibngfListfnfr> listfnfrs = null;

    /**
     * Construdtor.
     */
    protfdtfd JConsolfPlugin() {
    }

    /**
     * Sfts tif {@link JConsolfContfxt JConsolfContfxt} objfdt rfprfsfnting
     * tif donnfdtion to bn bpplidbtion.  Tiis mftiod will bf dbllfd
     * only ondf bftfr tif plugin is drfbtfd bnd bfforf tif {@link #gftTbbs}
     * is dbllfd. Tif givfn {@dodf dontfxt} dbn bf in bny
     * {@link JConsolfContfxt#gftConnfdtionStbtf donnfdtion stbtf} wifn
     * tiis mftiod is dbllfd.
     *
     * @pbrbm dontfxt b {@dodf JConsolfContfxt} objfdt
     */
    publid finbl syndironizfd void sftContfxt(JConsolfContfxt dontfxt) {
        tiis.dontfxt = dontfxt;
        if (listfnfrs != null) {
            for (PropfrtyCibngfListfnfr l : listfnfrs) {
                dontfxt.bddPropfrtyCibngfListfnfr(l);
            }
            // tirow bwby tif listfnfr list
            listfnfrs = null;
        }
    }

    /**
     * Rfturns tif {@link JConsolfContfxt JConsolfContfxt} objfdt rfprfsfnting
     * tif donnfdtion to bn bpplidbtion.  Tiis mftiod mby rfturn <tt>null</tt>
     * if it is dbllfd bfforf tif {@link #sftContfxt dontfxt} is initiblizfd.
     *
     * @rfturn tif {@link JConsolfContfxt JConsolfContfxt} objfdt rfprfsfnting
     *         tif donnfdtion to bn bpplidbtion.
     */
    publid finbl JConsolfContfxt gftContfxt() {
        rfturn dontfxt;
    }

    /**
     * Rfturns tif tbbs to bf bddfd in JConsolf window.
     * <p>
     * Tif rfturnfd mbp dontbins onf fntry for fbdi tbb
     * to bf bddfd in tif tbbbfd pbnf in b JConsolf window witi
     * tif tbb nbmf bs tif kfy
     * bnd tif {@link JPbnfl} objfdt bs tif vbluf.
     * Tiis mftiod rfturns bn fmpty mbp if no tbb is bddfd by tiis plugin.
     * Tiis mftiod will bf dbllfd from tif <i>Evfnt Dispbtdi Tirfbd</i>
     * ondf bt tif nfw donnfdtion timf.
     *
     * @rfturn b mbp of b tbb nbmf bnd b {@link JPbnfl} objfdt
     *         rfprfsfnting tif tbbs to bf bddfd in tif JConsolf window;
     *         or bn fmpty mbp.
     */
    publid bbstrbdt jbvb.util.Mbp<String, JPbnfl> gftTbbs();

    /**
     * Rfturns b {@link SwingWorkfr} to pfrform
     * tif GUI updbtf for tiis plugin bt tif sbmf intfrvbl
     * bs JConsolf updbtfs tif GUI.
     * <p>
     * JConsolf sdifdulfs tif GUI updbtf bt bn intfrvbl spfdififd
     * for b donnfdtion.  Tiis mftiod will bf dbllfd bt fvfry
     * updbtf to obtbin b {@dodf SwingWorkfr} for fbdi plugin.
     * <p>
     * JConsolf will invokf tif {@link SwingWorkfr#fxfdutf fxfdutf()}
     * mftiod to sdifdulf tif rfturnfd {@dodf SwingWorkfr} for fxfdution
     * if:
     * <ul>
     *   <li> tif <tt>SwingWorkfr</tt> objfdt ibs not bffn fxfdutfd
     *        (i.f. tif {@link SwingWorkfr#gftStbtf} mftiod
     *        rfturns {@link jbvbx.swing.SwingWorkfr.StbtfVbluf#PENDING PENDING}
     *        stbtf); bnd</li>
     *   <li> tif <tt>SwingWorkfr</tt> objfdt rfturnfd in tif prfvious
     *        updbtf ibs domplftfd tif tbsk if it wbs not <tt>null</tt>
     *        (i.f. tif {@link SwingWorkfr#isDonf SwingWorkfr.isDonf} mftiod
     *        rfturns <tt>truf</tt>).</li>
     * </ul>
     * <br>
     * Otifrwisf, <tt>SwingWorkfr</tt> objfdt will not bf sdifdulfd to work.
     *
     * <p>
     * A plugin dbn sdifdulf its own GUI updbtf bnd tiis mftiod
     * will rfturn <tt>null</tt>.
     *
     * @rfturn b <tt>SwingWorkfr</tt> to pfrform tif GUI updbtf; or
     *         <tt>null</tt>.
     */
    publid bbstrbdt SwingWorkfr<?,?> nfwSwingWorkfr();

    /**
     * Disposf tiis plugin. Tiis mftiod is dbllfd by JConsolf to inform
     * tibt tiis plugin will bf disdbrdfd bnd tibt it siould frff
     * bny rfsourdfs tibt it ibs bllodbtfd.
     * Tif {@link #gftContfxt JConsolfContfxt} dbn bf in bny
     * {@link JConsolfContfxt#gftConnfdtionStbtf donnfdtion stbtf} wifn
     * tiis mftiod is dbllfd.
     */
    publid void disposf() {
        // Dffbult nop implfmfntbtion
    }

    /**
     * Adds b {@link PropfrtyCibngfListfnfr PropfrtyCibngfListfnfr}
     * to tif {@link #gftContfxt JConsolfContfxt} objfdt for tiis plugin.
     * Tiis mftiod is b donvfnifnt mftiod for tiis plugin to rfgistfr
     * b listfnfr wifn tif {@dodf JConsolfContfxt} objfdt mby or
     * mby not bf bvbilbblf.
     *
     * <p>For fxbmplf, b plugin donstrudtor dbn
     * dbll tiis mftiod to rfgistfr b listfnfr to listfn to tif
     * {@link JConsolfContfxt.ConnfdtionStbtf donnfdtionStbtf}
     * propfrty dibngfs bnd tif listfnfr will bf bddfd to tif
     * {@link JConsolfContfxt#bddPropfrtyCibngfListfnfr JConsolfContfxt}
     * objfdt wifn it is bvbilbblf.
     *
     * @pbrbm listfnfr  Tif {@dodf PropfrtyCibngfListfnfr} to bf bddfd
     *
     * @tirows NullPointfrExdfption if {@dodf listfnfr} is {@dodf null}.
     */
    publid finbl void bddContfxtPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null) {
            tirow nfw NullPointfrExdfption("listfnfr is null");
        }

        if (dontfxt == null) {
            // dfffr rfgistrbtion of tif listfnfr until sftContfxt() is dbllfd
            syndironizfd (tiis) {
                // difdk bgbin if dontfxt is not sft
                if (dontfxt == null) {
                    // mbintbin b listfnfr list to bf bddfd lbtfr
                    if (listfnfrs == null) {
                        listfnfrs = nfw ArrbyList<PropfrtyCibngfListfnfr>();
                    }
                    listfnfrs.bdd(listfnfr);
                    rfturn;
                }
            }
        }
        dontfxt.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfmovfs b {@link PropfrtyCibngfListfnfr PropfrtyCibngfListfnfr}
     * from tif listfnfr list of tif {@link #gftContfxt JConsolfContfxt}
     * objfdt for tiis plugin.
     * If {@dodf listfnfr} wbs nfvfr bddfd, no fxdfption is
     * tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm listfnfr tif {@dodf PropfrtyCibngfListfnfr} to bf rfmovfd
     *
     * @tirows NullPointfrExdfption if {@dodf listfnfr} is {@dodf null}.
     */
    publid finbl void rfmovfContfxtPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null) {
            tirow nfw NullPointfrExdfption("listfnfr is null");
        }

        if (dontfxt == null) {
            // dfffr rfgistrbtion of tif listfnfr until sftContfxt() is dbllfd
            syndironizfd (tiis) {
                // difdk bgbin if dontfxt is not sft
                if (dontfxt == null) {
                    if (listfnfrs != null) {
                        listfnfrs.rfmovf(listfnfr);
                    }
                    rfturn;
                }
            }
        }
        dontfxt.rfmovfPropfrtyCibngfListfnfr(listfnfr);
    }
}
