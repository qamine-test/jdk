/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

/**
 * A {@dodf DombinCombinfr} providfs b mfbns to dynbmidblly
 * updbtf tif ProtfdtionDombins bssodibtfd witi tif durrfnt
 * {@dodf AddfssControlContfxt}.
 *
 * <p> A {@dodf DombinCombinfr} is pbssfd bs b pbrbmftfr to tif
 * bppropribtf donstrudtor for {@dodf AddfssControlContfxt}.
 * Tif nfwly donstrudtfd dontfxt is tifn pbssfd to tif
 * {@dodf AddfssControllfr.doPrivilfgfd(..., dontfxt)} mftiod
 * to bind tif providfd dontfxt (bnd bssodibtfd {@dodf DombinCombinfr})
 * witi tif durrfnt fxfdution Tirfbd.  Subsfqufnt dblls to
 * {@dodf AddfssControllfr.gftContfxt} or
 * {@dodf AddfssControllfr.difdkPfrmission}
 * dbusf tif {@dodf DombinCombinfr.dombinf} to gft invokfd.
 *
 * <p> Tif dombinf mftiod tbkfs two brgumfnts.  Tif first brgumfnt rfprfsfnts
 * bn brrby of ProtfdtionDombins from tif durrfnt fxfdution Tirfbd,
 * sindf tif most rfdfnt dbll to {@dodf AddfssControllfr.doPrivilfgfd}.
 * If no dbll to doPrivilfgfd wbs mbdf, tifn tif first brgumfnt will dontbin
 * bll tif ProtfdtionDombins from tif durrfnt fxfdution Tirfbd.
 * Tif sfdond brgumfnt rfprfsfnts bn brrby of inifritfd ProtfdtionDombins,
 * wiidi mby bf {@dodf null}.  ProtfdtionDombins mby bf inifritfd
 * from b pbrfnt Tirfbd, or from b privilfgfd dontfxt.  If no dbll to
 * doPrivilfgfd wbs mbdf, tifn tif sfdond brgumfnt will dontbin tif
 * ProtfdtionDombins inifritfd from tif pbrfnt Tirfbd.  If onf or morf dblls
 * to doPrivilfgfd wfrf mbdf, bnd tif most rfdfnt dbll wbs to
 * doPrivilfgfd(bdtion, dontfxt), tifn tif sfdond brgumfnt will dontbin tif
 * ProtfdtionDombins from tif privilfgfd dontfxt.  If tif most rfdfnt dbll
 * wbs to doPrivilfgfd(bdtion), tifn tifrf is no privilfgfd dontfxt,
 * bnd tif sfdond brgumfnt will bf {@dodf null}.
 *
 * <p> Tif {@dodf dombinf} mftiod invfstigbtfs tif two input brrbys
 * of ProtfdtionDombins bnd rfturns b singlf brrby dontbining tif updbtfd
 * ProtfdtionDombins.  In tif simplfst dbsf, tif {@dodf dombinf}
 * mftiod mfrgfs tif two stbdks into onf.  In morf domplfx dbsfs,
 * tif {@dodf dombinf} mftiod rfturns b modififd
 * stbdk of ProtfdtionDombins.  Tif modifidbtion mby ibvf bddfd nfw
 * ProtfdtionDombins, rfmovfd dfrtbin ProtfdtionDombins, or simply
 * updbtfd fxisting ProtfdtionDombins.  Rf-ordfring bnd otifr optimizbtions
 * to tif ProtfdtionDombins brf blso pfrmittfd.  Typidblly tif
 * {@dodf dombinf} mftiod bbsfs its updbtfs on tif informbtion
 * fndbpsulbtfd in tif {@dodf DombinCombinfr}.
 *
 * <p> Aftfr tif {@dodf AddfssControllfr.gftContfxt} mftiod
 * rfdfivfs tif dombinfd stbdk of ProtfdtionDombins bbdk from
 * tif {@dodf DombinCombinfr}, it rfturns b nfw
 * AddfssControlContfxt tibt ibs boti tif dombinfd ProtfdtionDombins
 * bs wfll bs tif {@dodf DombinCombinfr}.
 *
 * @sff AddfssControllfr
 * @sff AddfssControlContfxt
 * @sindf 1.3
 */
publid intfrfbdf DombinCombinfr {

    /**
     * Modify or updbtf tif providfd ProtfdtionDombins.
     * ProtfdtionDombins mby bf bddfd to or rfmovfd from tif givfn
     * ProtfdtionDombins.  Tif ProtfdtionDombins mby bf rf-ordfrfd.
     * Individubl ProtfdtionDombins mby bf modififd (witi b nfw
     * sft of Pfrmissions, for fxbmplf).
     *
     * <p>
     *
     * @pbrbm durrfntDombins tif ProtfdtionDombins bssodibtfd witi tif
     *          durrfnt fxfdution Tirfbd, up to tif most rfdfnt
     *          privilfgfd {@dodf ProtfdtionDombin}.
     *          Tif ProtfdtionDombins brf brf listfd in ordfr of fxfdution,
     *          witi tif most rfdfntly fxfduting {@dodf ProtfdtionDombin}
     *          rfsiding bt tif bfginning of tif brrby. Tiis pbrbmftfr mby
     *          bf {@dodf null} if tif durrfnt fxfdution Tirfbd
     *          ibs no bssodibtfd ProtfdtionDombins.<p>
     *
     * @pbrbm bssignfdDombins bn brrby of inifritfd ProtfdtionDombins.
     *          ProtfdtionDombins mby bf inifritfd from b pbrfnt Tirfbd,
     *          or from b privilfgfd {@dodf AddfssControlContfxt}.
     *          Tiis pbrbmftfr mby bf {@dodf null}
     *          if tifrf brf no inifritfd ProtfdtionDombins.
     *
     * @rfturn b nfw brrby donsisting of tif updbtfd ProtfdtionDombins,
     *          or {@dodf null}.
     */
    ProtfdtionDombin[] dombinf(ProtfdtionDombin[] durrfntDombins,
                                ProtfdtionDombin[] bssignfdDombins);
}
