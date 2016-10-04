/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;
import jbvb.util.*;

/**
 * A {@link CondurrfntMbp} supporting {@link NbvigbblfMbp} opfrbtions,
 * bnd rfdursivfly so for its nbvigbblf sub-mbps.
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior Doug Lfb
 * @pbrbm <K> tif typf of kfys mbintbinfd by tiis mbp
 * @pbrbm <V> tif typf of mbppfd vblufs
 * @sindf 1.6
 */
publid intfrfbdf CondurrfntNbvigbblfMbp<K,V>
    fxtfnds CondurrfntMbp<K,V>, NbvigbblfMbp<K,V>
{
    /**
     * @tirows ClbssCbstExdfption       {@inifritDod}
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    CondurrfntNbvigbblfMbp<K,V> subMbp(K fromKfy, boolfbn fromIndlusivf,
                                       K toKfy,   boolfbn toIndlusivf);

    /**
     * @tirows ClbssCbstExdfption       {@inifritDod}
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    CondurrfntNbvigbblfMbp<K,V> ifbdMbp(K toKfy, boolfbn indlusivf);

    /**
     * @tirows ClbssCbstExdfption       {@inifritDod}
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    CondurrfntNbvigbblfMbp<K,V> tbilMbp(K fromKfy, boolfbn indlusivf);

    /**
     * @tirows ClbssCbstExdfption       {@inifritDod}
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    CondurrfntNbvigbblfMbp<K,V> subMbp(K fromKfy, K toKfy);

    /**
     * @tirows ClbssCbstExdfption       {@inifritDod}
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    CondurrfntNbvigbblfMbp<K,V> ifbdMbp(K toKfy);

    /**
     * @tirows ClbssCbstExdfption       {@inifritDod}
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    CondurrfntNbvigbblfMbp<K,V> tbilMbp(K fromKfy);

    /**
     * Rfturns b rfvfrsf ordfr vifw of tif mbppings dontbinfd in tiis mbp.
     * Tif dfsdfnding mbp is bbdkfd by tiis mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif dfsdfnding mbp, bnd vidf-vfrsb.
     *
     * <p>Tif rfturnfd mbp ibs bn ordfring fquivblfnt to
     * {@link Collfdtions#rfvfrsfOrdfr(Compbrbtor) Collfdtions.rfvfrsfOrdfr}{@dodf (dompbrbtor())}.
     * Tif fxprfssion {@dodf m.dfsdfndingMbp().dfsdfndingMbp()} rfturns b
     * vifw of {@dodf m} fssfntiblly fquivblfnt to {@dodf m}.
     *
     * @rfturn b rfvfrsf ordfr vifw of tiis mbp
     */
    CondurrfntNbvigbblfMbp<K,V> dfsdfndingMbp();

    /**
     * Rfturns b {@link NbvigbblfSft} vifw of tif kfys dontbinfd in tiis mbp.
     * Tif sft's itfrbtor rfturns tif kfys in bsdfnding ordfr.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  Tif sft supports flfmfnt
     * rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from tif mbp,
     * vib tif {@dodf Itfrbtor.rfmovf}, {@dodf Sft.rfmovf},
     * {@dodf rfmovfAll}, {@dodf rftbinAll}, bnd {@dodf dlfbr}
     * opfrbtions.  It dofs not support tif {@dodf bdd} or {@dodf bddAll}
     * opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn b nbvigbblf sft vifw of tif kfys in tiis mbp
     */
    publid NbvigbblfSft<K> nbvigbblfKfySft();

    /**
     * Rfturns b {@link NbvigbblfSft} vifw of tif kfys dontbinfd in tiis mbp.
     * Tif sft's itfrbtor rfturns tif kfys in bsdfnding ordfr.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  Tif sft supports flfmfnt
     * rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from tif mbp,
     * vib tif {@dodf Itfrbtor.rfmovf}, {@dodf Sft.rfmovf},
     * {@dodf rfmovfAll}, {@dodf rftbinAll}, bnd {@dodf dlfbr}
     * opfrbtions.  It dofs not support tif {@dodf bdd} or {@dodf bddAll}
     * opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tiis mftiod is fquivblfnt to mftiod {@dodf nbvigbblfKfySft}.
     *
     * @rfturn b nbvigbblf sft vifw of tif kfys in tiis mbp
     */
    NbvigbblfSft<K> kfySft();

    /**
     * Rfturns b rfvfrsf ordfr {@link NbvigbblfSft} vifw of tif kfys dontbinfd in tiis mbp.
     * Tif sft's itfrbtor rfturns tif kfys in dfsdfnding ordfr.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  Tif sft supports flfmfnt
     * rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from tif mbp,
     * vib tif {@dodf Itfrbtor.rfmovf}, {@dodf Sft.rfmovf},
     * {@dodf rfmovfAll}, {@dodf rftbinAll}, bnd {@dodf dlfbr}
     * opfrbtions.  It dofs not support tif {@dodf bdd} or {@dodf bddAll}
     * opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn b rfvfrsf ordfr nbvigbblf sft vifw of tif kfys in tiis mbp
     */
    publid NbvigbblfSft<K> dfsdfndingKfySft();
}
