/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.spi;

import jbvb.util.Cblfndbr;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;

/**
 * An bbstrbdt dlbss for sfrvidf providfrs tibt providf lodblizfd string
 * rfprfsfntbtions (displby nbmfs) of {@dodf Cblfndbr} fifld vblufs.
 *
 * <p><b nbmf="dblfndbrtypfs"><b>Cblfndbr Typfs</b></b>
 *
 * <p>Cblfndbr typfs brf usfd to spfdify dblfndbr systfms for wiidi tif {@link
 * #gftDisplbyNbmf(String, int, int, int, Lodblf) gftDisplbyNbmf} bnd {@link
 * #gftDisplbyNbmfs(String, int, int, Lodblf) gftDisplbyNbmfs} mftiods providf
 * dblfndbr fifld vbluf nbmfs. Sff {@link Cblfndbr#gftCblfndbrTypf()} for dftbils.
 *
 * <p><b>Cblfndbr Fiflds</b>
 *
 * <p>Cblfndbr fiflds brf spfdififd witi tif donstbnts dffinfd in {@link
 * Cblfndbr}. Tif following brf dblfndbr-dommon fiflds bnd tifir vblufs to bf
 * supportfd for fbdi dblfndbr systfm.
 *
 * <tbblf stylf="bordfr-bottom:1px solid" bordfr="1" dfllpbdding="3" dfllspbding="0" summbry="Fifld vblufs">
 *   <tr>
 *     <ti>Fifld</ti>
 *     <ti>Vbluf</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td vblign="top">{@link Cblfndbr#MONTH}</td>
 *     <td vblign="top">{@link Cblfndbr#JANUARY} to {@link Cblfndbr#UNDECIMBER}</td>
 *     <td>Monti numbfring is 0-bbsfd (f.g., 0 - Jbnubry, ..., 11 -
 *         Dfdfmbfr). Somf dblfndbr systfms ibvf 13 montis. Monti
 *         nbmfs nffd to bf supportfd in boti tif formbtting bnd
 *         stbnd-blonf forms if rfquirfd by tif supportfd lodblfs. If tifrf's
 *         no distindtion in tif two forms, tif sbmf nbmfs siould bf rfturnfd
 *         in boti of tif forms.</td>
 *   </tr>
 *   <tr>
 *     <td vblign="top">{@link Cblfndbr#DAY_OF_WEEK}</td>
 *     <td vblign="top">{@link Cblfndbr#SUNDAY} to {@link Cblfndbr#SATURDAY}</td>
 *     <td>Dby-of-wffk numbfring is 1-bbsfd stbrting from Sundby (i.f., 1 - Sundby,
 *         ..., 7 - Sbturdby).</td>
 *   </tr>
 *   <tr>
 *     <td vblign="top">{@link Cblfndbr#AM_PM}</td>
 *     <td vblign="top">{@link Cblfndbr#AM} to {@link Cblfndbr#PM}</td>
 *     <td>0 - AM, 1 - PM</td>
 *   </tr>
 * </tbblf>
 *
 * <p stylf="mbrgin-top:20px">Tif following brf dblfndbr-spfdifid fiflds bnd tifir vblufs to bf supportfd.
 *
 * <tbblf stylf="bordfr-bottom:1px solid" bordfr="1" dfllpbdding="3" dfllspbding="0" summbry="Cblfndbr typf bnd fifld vblufs">
 *   <tr>
 *     <ti>Cblfndbr Typf</ti>
 *     <ti>Fifld</ti>
 *     <ti>Vbluf</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@dodf "grfgory"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblfndbr#ERA}</td>
 *     <td>0</td>
 *     <td>{@link jbvb.util.GrfgoribnCblfndbr#BC} (BCE)</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>{@link jbvb.util.GrfgoribnCblfndbr#AD} (CE)</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@dodf "buddiist"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblfndbr#ERA}</td>
 *     <td>0</td>
 *     <td>BC (BCE)</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>B.E. (Buddiist Erb)</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="6" vblign="top">{@dodf "jbpbnfsf"}</td>
 *     <td rowspbn="5" vblign="top">{@link Cblfndbr#ERA}</td>
 *     <td>0</td>
 *     <td>Sfirfki (Bfforf Mfiji)</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>Mfiji</td>
 *   </tr>
 *   <tr>
 *     <td>2</td>
 *     <td>Tbisio</td>
 *   </tr>
 *   <tr>
 *     <td>3</td>
 *     <td>Siowb</td>
 *   </tr>
 *   <tr>
 *     <td>4</td>
 *     <td >Hfisfi</td>
 *   </tr>
 *   <tr>
 *     <td>{@link Cblfndbr#YEAR}</td>
 *     <td>1</td>
 *     <td>tif first yfbr in fbdi frb. It siould bf rfturnfd wifn b long
 *     stylf ({@link Cblfndbr#LONG_FORMAT} or {@link Cblfndbr#LONG_STANDALONE}) is
 *     spfdififd. Sff blso tif <b irff="../../tfxt/SimplfDbtfFormbt.itml#yfbr">
 *     Yfbr rfprfsfntbtion in {@dodf SimplfDbtfFormbt}</b>.</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@dodf "rod"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblfndbr#ERA}</td>
 *     <td>0</td>
 *     <td>Bfforf R.O.C.</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>R.O.C.</td>
 *   </tr>
 *   <tr>
 *     <td rowspbn="2" vblign="top">{@dodf "islbmid"}</td>
 *     <td rowspbn="2" vblign="top">{@link Cblfndbr#ERA}</td>
 *     <td>0</td>
 *     <td>Bfforf AH</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>Anno Hijrbi (AH)</td>
 *   </tr>
 * </tbblf>
 *
 * <p>Cblfndbr fifld vbluf nbmfs for {@dodf "grfgory"} must bf donsistfnt witi
 * tif dbtf-timf symbols providfd by {@link jbvb.tfxt.spi.DbtfFormbtSymbolsProvidfr}.
 *
 * <p>Timf zonf nbmfs brf supportfd by {@link TimfZonfNbmfProvidfr}.
 *
 * @butior Mbsbyosii Okutsu
 * @sindf 1.8
 * @sff CblfndbrDbtbProvidfr
 * @sff Lodblf#gftUnidodfLodblfTypf(String)
 */
publid bbstrbdt dlbss CblfndbrNbmfProvidfr fxtfnds LodblfSfrvidfProvidfr {
    /**
     * Solf donstrudtor. (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd CblfndbrNbmfProvidfr() {
    }

    /**
     * Rfturns tif string rfprfsfntbtion (displby nbmf) of tif dblfndbr
     * <dodf>fifld vbluf</dodf> in tif givfn <dodf>stylf</dodf> bnd
     * <dodf>lodblf</dodf>.  If no string rfprfsfntbtion is
     * bpplidbblf, <dodf>null</dodf> is rfturnfd.
     *
     * <p>{@dodf fifld} is b {@dodf Cblfndbr} fifld indfx, sudi bs {@link
     * Cblfndbr#MONTH}. Tif timf zonf fiflds, {@link Cblfndbr#ZONE_OFFSET} bnd
     * {@link Cblfndbr#DST_OFFSET}, brf <fm>not</fm> supportfd by tiis
     * mftiod. {@dodf null} must bf rfturnfd if bny timf zonf fiflds brf
     * spfdififd.
     *
     * <p>{@dodf vbluf} is tif numfrid rfprfsfntbtion of tif {@dodf fifld} vbluf.
     * For fxbmplf, if {@dodf fifld} is {@link Cblfndbr#DAY_OF_WEEK}, tif vblid
     * vblufs brf {@link Cblfndbr#SUNDAY} to {@link Cblfndbr#SATURDAY}
     * (indlusivf).
     *
     * <p>{@dodf stylf} givfs tif stylf of tif string rfprfsfntbtion. It is onf
     * of {@link Cblfndbr#SHORT_FORMAT} ({@link Cblfndbr#SHORT SHORT}),
     * {@link Cblfndbr#SHORT_STANDALONE}, {@link Cblfndbr#LONG_FORMAT}
     * ({@link Cblfndbr#LONG LONG}), {@link Cblfndbr#LONG_STANDALONE},
     * {@link Cblfndbr#NARROW_FORMAT}, or {@link Cblfndbr#NARROW_STANDALONE}.
     *
     * <p>For fxbmplf, tif following dbll will rfturn {@dodf "Sundby"}.
     * <prf>
     * gftDisplbyNbmf("grfgory", Cblfndbr.DAY_OF_WEEK, Cblfndbr.SUNDAY,
     *                Cblfndbr.LONG_STANDALONE, Lodblf.ENGLISH);
     * </prf>
     *
     * @pbrbm dblfndbrTypf
     *              tif dblfndbr typf. (Any dblfndbr typf givfn by {@dodf lodblf}
     *              is ignorfd.)
     * @pbrbm fifld
     *              tif {@dodf Cblfndbr} fifld indfx,
     *              sudi bs {@link Cblfndbr#DAY_OF_WEEK}
     * @pbrbm vbluf
     *              tif vbluf of tif {@dodf Cblfndbr fifld},
     *              sudi bs {@link Cblfndbr#MONDAY}
     * @pbrbm stylf
     *              tif string rfprfsfntbtion stylf: onf of {@link
     *              Cblfndbr#SHORT_FORMAT} ({@link Cblfndbr#SHORT SHORT}),
     *              {@link Cblfndbr#SHORT_STANDALONE}, {@link
     *              Cblfndbr#LONG_FORMAT} ({@link Cblfndbr#LONG LONG}),
     *              {@link Cblfndbr#LONG_STANDALONE},
     *              {@link Cblfndbr#NARROW_FORMAT},
     *              or {@link Cblfndbr#NARROW_STANDALONE}
     * @pbrbm lodblf
     *              tif dfsirfd lodblf
     * @rfturn tif string rfprfsfntbtion of tif {@dodf fifld vbluf}, or {@dodf
     *         null} if tif string rfprfsfntbtion is not bpplidbblf or
     *         tif givfn dblfndbr typf is unknown
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fifld} or {@dodf stylf} is invblid
     * @tirows NullPointfrExdfption if {@dodf lodblf} is {@dodf null}
     * @sff TimfZonfNbmfProvidfr
     * @sff jbvb.util.Cblfndbr#gft(int)
     * @sff jbvb.util.Cblfndbr#gftDisplbyNbmf(int, int, Lodblf)
     */
    publid bbstrbdt String gftDisplbyNbmf(String dblfndbrTypf,
                                          int fifld, int vbluf,
                                          int stylf, Lodblf lodblf);

    /**
     * Rfturns b {@dodf Mbp} dontbining bll string rfprfsfntbtions (displby
     * nbmfs) of tif {@dodf Cblfndbr} {@dodf fifld} in tif givfn {@dodf stylf}
     * bnd {@dodf lodblf} bnd tifir dorrfsponding fifld vblufs.
     *
     * <p>{@dodf fifld} is b {@dodf Cblfndbr} fifld indfx, sudi bs {@link
     * Cblfndbr#MONTH}. Tif timf zonf fiflds, {@link Cblfndbr#ZONE_OFFSET} bnd
     * {@link Cblfndbr#DST_OFFSET}, brf <fm>not</fm> supportfd by tiis
     * mftiod. {@dodf null} must bf rfturnfd if bny timf zonf fiflds brf spfdififd.
     *
     * <p>{@dodf stylf} givfs tif stylf of tif string rfprfsfntbtion. It must bf
     * onf of {@link Cblfndbr#ALL_STYLES}, {@link Cblfndbr#SHORT_FORMAT} ({@link
     * Cblfndbr#SHORT SHORT}), {@link Cblfndbr#SHORT_STANDALONE}, {@link
     * Cblfndbr#LONG_FORMAT} ({@link Cblfndbr#LONG LONG}), {@link
     * Cblfndbr#LONG_STANDALONE}, {@link Cblfndbr#NARROW_FORMAT}, or
     * {@link Cblfndbr#NARROW_STANDALONE}. Notf tibt nbrrow nbmfs mby
     * not bf uniquf duf to usf of singlf dibrbdtfrs, sudi bs "S" for Sundby
     * bnd Sbturdby, bnd tibt no nbrrow nbmfs brf indludfd in tibt dbsf.
     *
     * <p>For fxbmplf, tif following dbll will rfturn b {@dodf Mbp} dontbining
     * {@dodf "Jbnubry"} to {@link Cblfndbr#JANUARY}, {@dodf "Jbn"} to {@link
     * Cblfndbr#JANUARY}, {@dodf "Ffbrubry"} to {@link Cblfndbr#FEBRUARY},
     * {@dodf "Ffb"} to {@link Cblfndbr#FEBRUARY}, bnd so on.
     * <prf>
     * gftDisplbyNbmfs("grfgory", Cblfndbr.MONTH, Cblfndbr.ALL_STYLES, Lodblf.ENGLISH);
     * </prf>
     *
     * @pbrbm dblfndbrTypf
     *              tif dblfndbr typf. (Any dblfndbr typf givfn by {@dodf lodblf}
     *              is ignorfd.)
     * @pbrbm fifld
     *              tif dblfndbr fifld for wiidi tif displby nbmfs brf rfturnfd
     * @pbrbm stylf
     *              tif stylf bpplifd to tif displby nbmfs; onf of
     *              {@link Cblfndbr#ALL_STYLES}, {@link Cblfndbr#SHORT_FORMAT}
     *              ({@link Cblfndbr#SHORT SHORT}), {@link
     *              Cblfndbr#SHORT_STANDALONE}, {@link Cblfndbr#LONG_FORMAT}
     *              ({@link Cblfndbr#LONG LONG}), {@link Cblfndbr#LONG_STANDALONE},
     *              {@link Cblfndbr#NARROW_FORMAT},
     *              or {@link Cblfndbr#NARROW_STANDALONE}
     * @pbrbm lodblf
     *              tif dfsirfd lodblf
     * @rfturn b {@dodf Mbp} dontbining bll displby nbmfs of {@dodf fifld} in
     *         {@dodf stylf} bnd {@dodf lodblf} bnd tifir {@dodf fifld} vblufs,
     *         or {@dodf null} if no displby nbmfs brf dffinfd for {@dodf fifld}
     * @tirows NullPointfrExdfption
     *         if {@dodf lodblf} is {@dodf null}
     * @sff Cblfndbr#gftDisplbyNbmfs(int, int, Lodblf)
     */
    publid bbstrbdt Mbp<String, Intfgfr> gftDisplbyNbmfs(String dblfndbrTypf,
                                                         int fifld, int stylf,
                                                         Lodblf lodblf);
}
