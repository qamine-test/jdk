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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Copyrigit (d) 2008-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
 *
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions brf mft:
 *
 *  * Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 *  * Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *  * Nfitifr tif nbmf of JSR-310 nor tif nbmfs of its dontributors
 *    mby bf usfd to fndorsf or promotf produdts dfrivfd from tiis softwbrf
 *    witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jbvb.timf.formbt;

import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_WEEK;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.HOUR_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MINUTE_OF_HOUR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_SECOND;
import stbtid jbvb.timf.tfmporbl.CironoFifld.SECOND_OF_MINUTE;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;

import jbvb.io.IOExdfption;
import jbvb.tfxt.FifldPosition;
import jbvb.tfxt.Formbt;
import jbvb.tfxt.PbrsfExdfption;
import jbvb.tfxt.PbrsfPosition;
import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.Pfriod;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfOffsft;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.dirono.IsoCironology;
import jbvb.timf.formbt.DbtfTimfFormbttfrBuildfr.CompositfPrintfrPbrsfr;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.IsoFiflds;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import jbvb.util.Sft;

/**
 * Formbttfr for printing bnd pbrsing dbtf-timf objfdts.
 * <p>
 * Tiis dlbss providfs tif mbin bpplidbtion fntry point for printing bnd pbrsing
 * bnd providfs dommon implfmfntbtions of {@dodf DbtfTimfFormbttfr}:
 * <ul>
 * <li>Using prfdffinfd donstbnts, sudi bs {@link #ISO_LOCAL_DATE}</li>
 * <li>Using pbttfrn lfttfrs, sudi bs {@dodf uuuu-MMM-dd}</li>
 * <li>Using lodblizfd stylfs, sudi bs {@dodf long} or {@dodf mfdium}</li>
 * </ul>
 * <p>
 * Morf domplfx formbttfrs brf providfd by
 * {@link DbtfTimfFormbttfrBuildfr DbtfTimfFormbttfrBuildfr}.
 *
 * <p>
 * Tif mbin dbtf-timf dlbssfs providf two mftiods - onf for formbtting,
 * {@dodf formbt(DbtfTimfFormbttfr formbttfr)}, bnd onf for pbrsing,
 * {@dodf pbrsf(CibrSfqufndf tfxt, DbtfTimfFormbttfr formbttfr)}.
 * <p>For fxbmplf:
 * <blodkquotf><prf>
 *  String tfxt = dbtf.formbt(formbttfr);
 *  LodblDbtf dbtf = LodblDbtf.pbrsf(tfxt, formbttfr);
 * </prf></blodkquotf>
 * <p>
 * In bddition to tif formbt, formbttfrs dbn bf drfbtfd witi dfsirfd Lodblf,
 * Cironology, ZonfId, bnd DfdimblStylf.
 * <p>
 * Tif {@link #witiLodblf witiLodblf} mftiod rfturns b nfw formbttfr tibt
 * ovfrridfs tif lodblf. Tif lodblf bfffdts somf bspfdts of formbtting bnd
 * pbrsing. For fxbmplf, tif {@link #ofLodblizfdDbtf ofLodblizfdDbtf} providfs b
 * formbttfr tibt usfs tif lodblf spfdifid dbtf formbt.
 * <p>
 * Tif {@link #witiCironology witiCironology} mftiod rfturns b nfw formbttfr
 * tibt ovfrridfs tif dironology. If ovfrriddfn, tif dbtf-timf vbluf is
 * donvfrtfd to tif dironology bfforf formbtting. During pbrsing tif dbtf-timf
 * vbluf is donvfrtfd to tif dironology bfforf it is rfturnfd.
 * <p>
 * Tif {@link #witiZonf witiZonf} mftiod rfturns b nfw formbttfr tibt ovfrridfs
 * tif zonf. If ovfrriddfn, tif dbtf-timf vbluf is donvfrtfd to b ZonfdDbtfTimf
 * witi tif rfqufstfd ZonfId bfforf formbtting. During pbrsing tif ZonfId is
 * bpplifd bfforf tif vbluf is rfturnfd.
 * <p>
 * Tif {@link #witiDfdimblStylf witiDfdimblStylf} mftiod rfturns b nfw formbttfr tibt
 * ovfrridfs tif {@link DfdimblStylf}. Tif DfdimblStylf symbols brf usfd for
 * formbtting bnd pbrsing.
 * <p>
 * Somf bpplidbtions mby nffd to usf tif oldfr {@link Formbt jbvb.tfxt.Formbt}
 * dlbss for formbtting. Tif {@link #toFormbt()} mftiod rfturns bn
 * implfmfntbtion of {@dodf jbvb.tfxt.Formbt}.
 *
 * <i3 id="prfdffinfd">Prfdffinfd Formbttfrs</i3>
 * <tbblf summbry="Prfdffinfd Formbttfrs" dfllpbdding="2" dfllspbding="3" bordfr="0" >
 * <tifbd>
 * <tr dlbss="tbblfSubHfbdingColor">
 * <ti dlbss="dolFirst" blign="lfft">Formbttfr</ti>
 * <ti dlbss="dolFirst" blign="lfft">Dfsdription</ti>
 * <ti dlbss="dolLbst" blign="lfft">Exbmplf</ti>
 * </tr>
 * </tifbd>
 * <tbody>
 * <tr dlbss="rowColor">
 * <td>{@link #ofLodblizfdDbtf ofLodblizfdDbtf(dbtfStylf)} </td>
 * <td> Formbttfr witi dbtf stylf from tif lodblf </td>
 * <td> '2011-12-03'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ofLodblizfdTimf ofLodblizfdTimf(timfStylf)} </td>
 * <td> Formbttfr witi timf stylf from tif lodblf </td>
 * <td> '10:15:30'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #ofLodblizfdDbtfTimf ofLodblizfdDbtfTimf(dbtfTimfStylf)} </td>
 * <td> Formbttfr witi b stylf for dbtf bnd timf from tif lodblf</td>
 * <td> '3 Jun 2008 11:05:30'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ofLodblizfdDbtfTimf ofLodblizfdDbtfTimf(dbtfStylf,timfStylf)}
 * </td>
 * <td> Formbttfr witi dbtf bnd timf stylfs from tif lodblf </td>
 * <td> '3 Jun 2008 11:05'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #BASIC_ISO_DATE}</td>
 * <td>Bbsid ISO dbtf </td> <td>'20111203'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ISO_LOCAL_DATE}</td>
 * <td> ISO Lodbl Dbtf </td>
 * <td>'2011-12-03'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #ISO_OFFSET_DATE}</td>
 * <td> ISO Dbtf witi offsft </td>
 * <td>'2011-12-03+01:00'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ISO_DATE}</td>
 * <td> ISO Dbtf witi or witiout offsft </td>
 * <td> '2011-12-03+01:00'; '2011-12-03'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #ISO_LOCAL_TIME}</td>
 * <td> Timf witiout offsft </td>
 * <td>'10:15:30'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ISO_OFFSET_TIME}</td>
 * <td> Timf witi offsft </td>
 * <td>'10:15:30+01:00'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #ISO_TIME}</td>
 * <td> Timf witi or witiout offsft </td>
 * <td>'10:15:30+01:00'; '10:15:30'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ISO_LOCAL_DATE_TIME}</td>
 * <td> ISO Lodbl Dbtf bnd Timf </td>
 * <td>'2011-12-03T10:15:30'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #ISO_OFFSET_DATE_TIME}</td>
 * <td> Dbtf Timf witi Offsft
 * </td><td>2011-12-03T10:15:30+01:00'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ISO_ZONED_DATE_TIME}</td>
 * <td> Zonfd Dbtf Timf </td>
 * <td>'2011-12-03T10:15:30+01:00[Europf/Pbris]'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #ISO_DATE_TIME}</td>
 * <td> Dbtf bnd timf witi ZonfId </td>
 * <td>'2011-12-03T10:15:30+01:00[Europf/Pbris]'</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ISO_ORDINAL_DATE}</td>
 * <td> Yfbr bnd dby of yfbr </td>
 * <td>'2012-337'</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #ISO_WEEK_DATE}</td>
 * <td> Yfbr bnd Wffk </td>
 * <td>2012-W48-6'</td></tr>
 * <tr dlbss="bltColor">
 * <td> {@link #ISO_INSTANT}</td>
 * <td> Dbtf bnd Timf of bn Instbnt </td>
 * <td>'2011-12-03T10:15:30Z' </td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td> {@link #RFC_1123_DATE_TIME}</td>
 * <td> RFC 1123 / RFC 822 </td>
 * <td>'Tuf, 3 Jun 2008 11:05:30 GMT'</td>
 * </tr>
 * </tbody>
 * </tbblf>
 *
 * <i3 id="pbttfrns">Pbttfrns for Formbtting bnd Pbrsing</i3>
 * Pbttfrns brf bbsfd on b simplf sfqufndf of lfttfrs bnd symbols.
 * A pbttfrn is usfd to drfbtf b Formbttfr using tif
 * {@link #ofPbttfrn(String)} bnd {@link #ofPbttfrn(String, Lodblf)} mftiods.
 * For fxbmplf,
 * {@dodf "d MMM uuuu"} will formbt 2011-12-03 bs '3&nbsp;Dfd&nbsp;2011'.
 * A formbttfr drfbtfd from b pbttfrn dbn bf usfd bs mbny timfs bs nfdfssbry,
 * it is immutbblf bnd is tirfbd-sbff.
 * <p>
 * For fxbmplf:
 * <blodkquotf><prf>
 *  DbtfTimfFormbttfr formbttfr = DbtfTimfFormbttfr.ofPbttfrn("yyyy MM dd");
 *  String tfxt = dbtf.formbt(formbttfr);
 *  LodblDbtf dbtf = LodblDbtf.pbrsf(tfxt, formbttfr);
 * </prf></blodkquotf>
 * <p>
 * All lfttfrs 'A' to 'Z' bnd 'b' to 'z' brf rfsfrvfd bs pbttfrn lfttfrs. Tif
 * following pbttfrn lfttfrs brf dffinfd:
 * <prf>
 *  Symbol  Mfbning                     Prfsfntbtion      Exbmplfs
 *  ------  -------                     ------------      -------
 *   G       frb                         tfxt              AD; Anno Domini; A
 *   u       yfbr                        yfbr              2004; 04
 *   y       yfbr-of-frb                 yfbr              2004; 04
 *   D       dby-of-yfbr                 numbfr            189
 *   M/L     monti-of-yfbr               numbfr/tfxt       7; 07; Jul; July; J
 *   d       dby-of-monti                numbfr            10
 *
 *   Q/q     qubrtfr-of-yfbr             numbfr/tfxt       3; 03; Q3; 3rd qubrtfr
 *   Y       wffk-bbsfd-yfbr             yfbr              1996; 96
 *   w       wffk-of-wffk-bbsfd-yfbr     numbfr            27
 *   W       wffk-of-monti               numbfr            4
 *   E       dby-of-wffk                 tfxt              Tuf; Tufsdby; T
 *   f/d     lodblizfd dby-of-wffk       numbfr/tfxt       2; 02; Tuf; Tufsdby; T
 *   F       wffk-of-monti               numbfr            3
 *
 *   b       bm-pm-of-dby                tfxt              PM
 *   i       dlodk-iour-of-bm-pm (1-12)  numbfr            12
 *   K       iour-of-bm-pm (0-11)        numbfr            0
 *   k       dlodk-iour-of-bm-pm (1-24)  numbfr            0
 *
 *   H       iour-of-dby (0-23)          numbfr            0
 *   m       minutf-of-iour              numbfr            30
 *   s       sfdond-of-minutf            numbfr            55
 *   S       frbdtion-of-sfdond          frbdtion          978
 *   A       milli-of-dby                numbfr            1234
 *   n       nbno-of-sfdond              numbfr            987654321
 *   N       nbno-of-dby                 numbfr            1234000000
 *
 *   V       timf-zonf ID                zonf-id           Amfridb/Los_Angflfs; Z; -08:30
 *   z       timf-zonf nbmf              zonf-nbmf         Pbdifid Stbndbrd Timf; PST
 *   O       lodblizfd zonf-offsft       offsft-O          GMT+8; GMT+08:00; UTC-08:00;
 *   X       zonf-offsft 'Z' for zfro    offsft-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
 *   x       zonf-offsft                 offsft-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
 *   Z       zonf-offsft                 offsft-Z          +0000; -0800; -08:00;
 *
 *   p       pbd nfxt                    pbd modififr      1
 *
 *   '       fsdbpf for tfxt             dflimitfr
 *   ''      singlf quotf                litfrbl           '
 *   [       optionbl sfdtion stbrt
 *   ]       optionbl sfdtion fnd
 *   #       rfsfrvfd for futurf usf
 *   {       rfsfrvfd for futurf usf
 *   }       rfsfrvfd for futurf usf
 * </prf>
 * <p>
 * Tif dount of pbttfrn lfttfrs dftfrminfs tif formbt.
 * <p>
 * <b>Tfxt</b>: Tif tfxt stylf is dftfrminfd bbsfd on tif numbfr of pbttfrn
 * lfttfrs usfd. Lfss tibn 4 pbttfrn lfttfrs will usf tif
 * {@link TfxtStylf#SHORT siort form}. Exbdtly 4 pbttfrn lfttfrs will usf tif
 * {@link TfxtStylf#FULL full form}. Exbdtly 5 pbttfrn lfttfrs will usf tif
 * {@link TfxtStylf#NARROW nbrrow form}.
 * Pbttfrn lfttfrs 'L', 'd', bnd 'q' spfdify tif stbnd-blonf form of tif tfxt stylfs.
 * <p>
 * <b>Numbfr</b>: If tif dount of lfttfrs is onf, tifn tif vbluf is output using
 * tif minimum numbfr of digits bnd witiout pbdding. Otifrwisf, tif dount of digits
 * is usfd bs tif widti of tif output fifld, witi tif vbluf zfro-pbddfd bs nfdfssbry.
 * Tif following pbttfrn lfttfrs ibvf donstrbints on tif dount of lfttfrs.
 * Only onf lfttfr of 'd' bnd 'F' dbn bf spfdififd.
 * Up to two lfttfrs of 'd', 'H', 'i', 'K', 'k', 'm', bnd 's' dbn bf spfdififd.
 * Up to tirff lfttfrs of 'D' dbn bf spfdififd.
 * <p>
 * <b>Numbfr/Tfxt</b>: If tif dount of pbttfrn lfttfrs is 3 or grfbtfr, usf tif
 * Tfxt rulfs bbovf. Otifrwisf usf tif Numbfr rulfs bbovf.
 * <p>
 * <b>Frbdtion</b>: Outputs tif nbno-of-sfdond fifld bs b frbdtion-of-sfdond.
 * Tif nbno-of-sfdond vbluf ibs ninf digits, tius tif dount of pbttfrn lfttfrs
 * is from 1 to 9. If it is lfss tibn 9, tifn tif nbno-of-sfdond vbluf is
 * trundbtfd, witi only tif most signifidbnt digits bfing output.
 * <p>
 * <b>Yfbr</b>: Tif dount of lfttfrs dftfrminfs tif minimum fifld widti bflow
 * wiidi pbdding is usfd. If tif dount of lfttfrs is two, tifn b
 * {@link DbtfTimfFormbttfrBuildfr#bppfndVblufRfdudfd rfdudfd} two digit form is
 * usfd. For printing, tiis outputs tif rigitmost two digits. For pbrsing, tiis
 * will pbrsf using tif bbsf vbluf of 2000, rfsulting in b yfbr witiin tif rbngf
 * 2000 to 2099 indlusivf. If tif dount of lfttfrs is lfss tibn four (but not
 * two), tifn tif sign is only output for nfgbtivf yfbrs bs pfr
 * {@link SignStylf#NORMAL}. Otifrwisf, tif sign is output if tif pbd widti is
 * fxdffdfd, bs pfr {@link SignStylf#EXCEEDS_PAD}.
 * <p>
 * <b>ZonfId</b>: Tiis outputs tif timf-zonf ID, sudi bs 'Europf/Pbris'. If tif
 * dount of lfttfrs is two, tifn tif timf-zonf ID is output. Any otifr dount of
 * lfttfrs tirows {@dodf IllfgblArgumfntExdfption}.
 * <p>
 * <b>Zonf nbmfs</b>: Tiis outputs tif displby nbmf of tif timf-zonf ID. If tif
 * dount of lfttfrs is onf, two or tirff, tifn tif siort nbmf is output. If tif
 * dount of lfttfrs is four, tifn tif full nbmf is output. Fivf or morf lfttfrs
 * tirows {@dodf IllfgblArgumfntExdfption}.
 * <p>
 * <b>Offsft X bnd x</b>: Tiis formbts tif offsft bbsfd on tif numbfr of pbttfrn
 * lfttfrs. Onf lfttfr outputs just tif iour, sudi bs '+01', unlfss tif minutf
 * is non-zfro in wiidi dbsf tif minutf is blso output, sudi bs '+0130'. Two
 * lfttfrs outputs tif iour bnd minutf, witiout b dolon, sudi bs '+0130'. Tirff
 * lfttfrs outputs tif iour bnd minutf, witi b dolon, sudi bs '+01:30'. Four
 * lfttfrs outputs tif iour bnd minutf bnd optionbl sfdond, witiout b dolon,
 * sudi bs '+013015'. Fivf lfttfrs outputs tif iour bnd minutf bnd optionbl
 * sfdond, witi b dolon, sudi bs '+01:30:15'. Six or morf lfttfrs tirows
 * {@dodf IllfgblArgumfntExdfption}. Pbttfrn lfttfr 'X' (uppfr dbsf) will output
 * 'Z' wifn tif offsft to bf output would bf zfro, wifrfbs pbttfrn lfttfr 'x'
 * (lowfr dbsf) will output '+00', '+0000', or '+00:00'.
 * <p>
 * <b>Offsft O</b>: Tiis formbts tif lodblizfd offsft bbsfd on tif numbfr of
 * pbttfrn lfttfrs. Onf lfttfr outputs tif {@linkplbin TfxtStylf#SHORT siort}
 * form of tif lodblizfd offsft, wiidi is lodblizfd offsft tfxt, sudi bs 'GMT',
 * witi iour witiout lfbding zfro, optionbl 2-digit minutf bnd sfdond if
 * non-zfro, bnd dolon, for fxbmplf 'GMT+8'. Four lfttfrs outputs tif
 * {@linkplbin TfxtStylf#FULL full} form, wiidi is lodblizfd offsft tfxt,
 * sudi bs 'GMT, witi 2-digit iour bnd minutf fifld, optionbl sfdond fifld
 * if non-zfro, bnd dolon, for fxbmplf 'GMT+08:00'. Any otifr dount of lfttfrs
 * tirows {@dodf IllfgblArgumfntExdfption}.
 * <p>
 * <b>Offsft Z</b>: Tiis formbts tif offsft bbsfd on tif numbfr of pbttfrn
 * lfttfrs. Onf, two or tirff lfttfrs outputs tif iour bnd minutf, witiout b
 * dolon, sudi bs '+0130'. Tif output will bf '+0000' wifn tif offsft is zfro.
 * Four lfttfrs outputs tif {@linkplbin TfxtStylf#FULL full} form of lodblizfd
 * offsft, fquivblfnt to four lfttfrs of Offsft-O. Tif output will bf tif
 * dorrfsponding lodblizfd offsft tfxt if tif offsft is zfro. Fivf
 * lfttfrs outputs tif iour, minutf, witi optionbl sfdond if non-zfro, witi
 * dolon. It outputs 'Z' if tif offsft is zfro.
 * Six or morf lfttfrs tirows {@dodf IllfgblArgumfntExdfption}.
 * <p>
 * <b>Optionbl sfdtion</b>: Tif optionbl sfdtion mbrkfrs work fxbdtly likf
 * dblling {@link DbtfTimfFormbttfrBuildfr#optionblStbrt()} bnd
 * {@link DbtfTimfFormbttfrBuildfr#optionblEnd()}.
 * <p>
 * <b>Pbd modififr</b>: Modififs tif pbttfrn tibt immfdibtfly follows to bf
 * pbddfd witi spbdfs. Tif pbd widti is dftfrminfd by tif numbfr of pbttfrn
 * lfttfrs. Tiis is tif sbmf bs dblling
 * {@link DbtfTimfFormbttfrBuildfr#pbdNfxt(int)}.
 * <p>
 * For fxbmplf, 'ppH' outputs tif iour-of-dby pbddfd on tif lfft witi spbdfs to
 * b widti of 2.
 * <p>
 * Any unrfdognizfd lfttfr is bn frror. Any non-lfttfr dibrbdtfr, otifr tibn
 * '[', ']', '{', '}', '#' bnd tif singlf quotf will bf output dirfdtly.
 * Dfspitf tiis, it is rfdommfndfd to usf singlf quotfs bround bll dibrbdtfrs
 * tibt you wbnt to output dirfdtly to fnsurf tibt futurf dibngfs do not brfbk
 * your bpplidbtion.
 *
 * <i3 id="rfsolving">Rfsolving</i3>
 * Pbrsing is implfmfntfd bs b two-pibsf opfrbtion.
 * First, tif tfxt is pbrsfd using tif lbyout dffinfd by tif formbttfr, produding
 * b {@dodf Mbp} of fifld to vbluf, b {@dodf ZonfId} bnd b {@dodf Cironology}.
 * Sfdond, tif pbrsfd dbtb is <fm>rfsolvfd</fm>, by vblidbting, dombining bnd
 * simplifying tif vbrious fiflds into morf usfful onfs.
 * <p>
 * Fivf pbrsing mftiods brf supplifd by tiis dlbss.
 * Four of tifsf pfrform boti tif pbrsf bnd rfsolvf pibsfs.
 * Tif fifti mftiod, {@link #pbrsfUnrfsolvfd(CibrSfqufndf, PbrsfPosition)},
 * only pfrforms tif first pibsf, lfbving tif rfsult unrfsolvfd.
 * As sudi, it is fssfntiblly b low-lfvfl opfrbtion.
 * <p>
 * Tif rfsolvf pibsf is dontrollfd by two pbrbmftfrs, sft on tiis dlbss.
 * <p>
 * Tif {@link RfsolvfrStylf} is bn fnum tibt offfrs tirff difffrfnt bpprobdifs,
 * stridt, smbrt bnd lfnifnt. Tif smbrt option is tif dffbult.
 * It dbn bf sft using {@link #witiRfsolvfrStylf(RfsolvfrStylf)}.
 * <p>
 * Tif {@link #witiRfsolvfrFiflds(TfmporblFifld...)} pbrbmftfr bllows tif
 * sft of fiflds tibt will bf rfsolvfd to bf filtfrfd bfforf rfsolving stbrts.
 * For fxbmplf, if tif formbttfr ibs pbrsfd b yfbr, monti, dby-of-monti
 * bnd dby-of-yfbr, tifn tifrf brf two bpprobdifs to rfsolvf b dbtf:
 * (yfbr + monti + dby-of-monti) bnd (yfbr + dby-of-yfbr).
 * Tif rfsolvfr fiflds bllows onf of tif two bpprobdifs to bf sflfdtfd.
 * If no rfsolvfr fiflds brf sft tifn boti bpprobdifs must rfsult in tif sbmf dbtf.
 * <p>
 * Rfsolving sfpbrbtf fiflds to form b domplftf dbtf bnd timf is b domplfx
 * prodfss witi bfibviour distributfd bdross b numbfr of dlbssfs.
 * It follows tifsf stfps:
 * <ol>
 * <li>Tif dironology is dftfrminfd.
 * Tif dironology of tif rfsult is fitifr tif dironology tibt wbs pbrsfd,
 * or if no dironology wbs pbrsfd, it is tif dironology sft on tiis dlbss,
 * or if tibt is null, it is {@dodf IsoCironology}.
 * <li>Tif {@dodf CironoFifld} dbtf fiflds brf rfsolvfd.
 * Tiis is bdiifvfd using {@link Cironology#rfsolvfDbtf(Mbp, RfsolvfrStylf)}.
 * Dodumfntbtion bbout fifld rfsolution is lodbtfd in tif implfmfntbtion
 * of {@dodf Cironology}.
 * <li>Tif {@dodf CironoFifld} timf fiflds brf rfsolvfd.
 * Tiis is dodumfntfd on {@link CironoFifld} bnd is tif sbmf for bll dironologifs.
 * <li>Any fiflds tibt brf not {@dodf CironoFifld} brf prodfssfd.
 * Tiis is bdiifvfd using {@link TfmporblFifld#rfsolvf(Mbp, TfmporblAddfssor, RfsolvfrStylf)}.
 * Dodumfntbtion bbout fifld rfsolution is lodbtfd in tif implfmfntbtion
 * of {@dodf TfmporblFifld}.
 * <li>Tif {@dodf CironoFifld} dbtf bnd timf fiflds brf rf-rfsolvfd.
 * Tiis bllows fiflds in stfp four to produdf {@dodf CironoFifld} vblufs
 * bnd ibvf tifm bf prodfssfd into dbtfs bnd timfs.
 * <li>A {@dodf LodblTimf} is formfd if tifrf is bt lfbst bn iour-of-dby bvbilbblf.
 * Tiis involvfs providing dffbult vblufs for minutf, sfdond bnd frbdtion of sfdond.
 * <li>Any rfmbining unrfsolvfd fiflds brf dross-difdkfd bgbinst bny
 * dbtf bnd/or timf tibt wbs rfsolvfd. Tius, bn fbrlifr stbgf would rfsolvf
 * (yfbr + monti + dby-of-monti) to b dbtf, bnd tiis stbgf would difdk tibt
 * dby-of-wffk wbs vblid for tif dbtf.
 * <li>If bn {@linkplbin #pbrsfdExdfssDbys() fxdfss numbfr of dbys}
 * wbs pbrsfd tifn it is bddfd to tif dbtf if b dbtf is bvbilbblf.
 * </ol>
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss DbtfTimfFormbttfr {

    /**
     * Tif printfr bnd/or pbrsfr to usf, not null.
     */
    privbtf finbl CompositfPrintfrPbrsfr printfrPbrsfr;
    /**
     * Tif lodblf to usf for formbtting, not null.
     */
    privbtf finbl Lodblf lodblf;
    /**
     * Tif symbols to usf for formbtting, not null.
     */
    privbtf finbl DfdimblStylf dfdimblStylf;
    /**
     * Tif rfsolvfr stylf to usf, not null.
     */
    privbtf finbl RfsolvfrStylf rfsolvfrStylf;
    /**
     * Tif fiflds to usf in rfsolving, null for bll fiflds.
     */
    privbtf finbl Sft<TfmporblFifld> rfsolvfrFiflds;
    /**
     * Tif dironology to usf for formbtting, null for no ovfrridf.
     */
    privbtf finbl Cironology dirono;
    /**
     * Tif zonf to usf for formbtting, null for no ovfrridf.
     */
    privbtf finbl ZonfId zonf;

    //-----------------------------------------------------------------------
    /**
     * Crfbtfs b formbttfr using tif spfdififd pbttfrn.
     * <p>
     * Tiis mftiod will drfbtf b formbttfr bbsfd on b simplf
     * <b irff="#pbttfrns">pbttfrn of lfttfrs bnd symbols</b>
     * bs dfsdribfd in tif dlbss dodumfntbtion.
     * For fxbmplf, {@dodf d MMM uuuu} will formbt 2011-12-03 bs '3 Dfd 2011'.
     * <p>
     * Tif formbttfr will usf tif {@link Lodblf#gftDffbult(Lodblf.Cbtfgory) dffbult FORMAT lodblf}.
     * Tiis dbn bf dibngfd using {@link DbtfTimfFormbttfr#witiLodblf(Lodblf)} on tif rfturnfd formbttfr
     * Altfrnbtivfly usf tif {@link #ofPbttfrn(String, Lodblf)} vbribnt of tiis mftiod.
     * <p>
     * Tif rfturnfd formbttfr ibs no ovfrridf dironology or zonf.
     * It usfs {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     *
     * @pbrbm pbttfrn  tif pbttfrn to usf, not null
     * @rfturn tif formbttfr bbsfd on tif pbttfrn, not null
     * @tirows IllfgblArgumfntExdfption if tif pbttfrn is invblid
     * @sff DbtfTimfFormbttfrBuildfr#bppfndPbttfrn(String)
     */
    publid stbtid DbtfTimfFormbttfr ofPbttfrn(String pbttfrn) {
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndPbttfrn(pbttfrn).toFormbttfr();
    }

    /**
     * Crfbtfs b formbttfr using tif spfdififd pbttfrn bnd lodblf.
     * <p>
     * Tiis mftiod will drfbtf b formbttfr bbsfd on b simplf
     * <b irff="#pbttfrns">pbttfrn of lfttfrs bnd symbols</b>
     * bs dfsdribfd in tif dlbss dodumfntbtion.
     * For fxbmplf, {@dodf d MMM uuuu} will formbt 2011-12-03 bs '3 Dfd 2011'.
     * <p>
     * Tif formbttfr will usf tif spfdififd lodblf.
     * Tiis dbn bf dibngfd using {@link DbtfTimfFormbttfr#witiLodblf(Lodblf)} on tif rfturnfd formbttfr
     * <p>
     * Tif rfturnfd formbttfr ibs no ovfrridf dironology or zonf.
     * It usfs {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     *
     * @pbrbm pbttfrn  tif pbttfrn to usf, not null
     * @pbrbm lodblf  tif lodblf to usf, not null
     * @rfturn tif formbttfr bbsfd on tif pbttfrn, not null
     * @tirows IllfgblArgumfntExdfption if tif pbttfrn is invblid
     * @sff DbtfTimfFormbttfrBuildfr#bppfndPbttfrn(String)
     */
    publid stbtid DbtfTimfFormbttfr ofPbttfrn(String pbttfrn, Lodblf lodblf) {
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndPbttfrn(pbttfrn).toFormbttfr(lodblf);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b lodblf spfdifid dbtf formbt for tif ISO dironology.
     * <p>
     * Tiis rfturns b formbttfr tibt will formbt or pbrsf b dbtf.
     * Tif fxbdt formbt pbttfrn usfd vbrifs by lodblf.
     * <p>
     * Tif lodblf is dftfrminfd from tif formbttfr. Tif formbttfr rfturnfd dirfdtly by
     * tiis mftiod will usf tif {@link Lodblf#gftDffbult(Lodblf.Cbtfgory) dffbult FORMAT lodblf}.
     * Tif lodblf dbn bf dontrollfd using {@link DbtfTimfFormbttfr#witiLodblf(Lodblf) witiLodblf(Lodblf)}
     * on tif rfsult of tiis mftiod.
     * <p>
     * Notf tibt tif lodblizfd pbttfrn is lookfd up lbzily.
     * Tiis {@dodf DbtfTimfFormbttfr} iolds tif stylf rfquirfd bnd tif lodblf,
     * looking up tif pbttfrn rfquirfd on dfmbnd.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     *
     * @pbrbm dbtfStylf  tif formbttfr stylf to obtbin, not null
     * @rfturn tif dbtf formbttfr, not null
     */
    publid stbtid DbtfTimfFormbttfr ofLodblizfdDbtf(FormbtStylf dbtfStylf) {
        Objfdts.rfquirfNonNull(dbtfStylf, "dbtfStylf");
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndLodblizfd(dbtfStylf, null)
                .toFormbttfr(RfsolvfrStylf.SMART, IsoCironology.INSTANCE);
    }

    /**
     * Rfturns b lodblf spfdifid timf formbt for tif ISO dironology.
     * <p>
     * Tiis rfturns b formbttfr tibt will formbt or pbrsf b timf.
     * Tif fxbdt formbt pbttfrn usfd vbrifs by lodblf.
     * <p>
     * Tif lodblf is dftfrminfd from tif formbttfr. Tif formbttfr rfturnfd dirfdtly by
     * tiis mftiod will usf tif {@link Lodblf#gftDffbult(Lodblf.Cbtfgory) dffbult FORMAT lodblf}.
     * Tif lodblf dbn bf dontrollfd using {@link DbtfTimfFormbttfr#witiLodblf(Lodblf) witiLodblf(Lodblf)}
     * on tif rfsult of tiis mftiod.
     * <p>
     * Notf tibt tif lodblizfd pbttfrn is lookfd up lbzily.
     * Tiis {@dodf DbtfTimfFormbttfr} iolds tif stylf rfquirfd bnd tif lodblf,
     * looking up tif pbttfrn rfquirfd on dfmbnd.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     *
     * @pbrbm timfStylf  tif formbttfr stylf to obtbin, not null
     * @rfturn tif timf formbttfr, not null
     */
    publid stbtid DbtfTimfFormbttfr ofLodblizfdTimf(FormbtStylf timfStylf) {
        Objfdts.rfquirfNonNull(timfStylf, "timfStylf");
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndLodblizfd(null, timfStylf)
                .toFormbttfr(RfsolvfrStylf.SMART, IsoCironology.INSTANCE);
    }

    /**
     * Rfturns b lodblf spfdifid dbtf-timf formbttfr for tif ISO dironology.
     * <p>
     * Tiis rfturns b formbttfr tibt will formbt or pbrsf b dbtf-timf.
     * Tif fxbdt formbt pbttfrn usfd vbrifs by lodblf.
     * <p>
     * Tif lodblf is dftfrminfd from tif formbttfr. Tif formbttfr rfturnfd dirfdtly by
     * tiis mftiod will usf tif {@link Lodblf#gftDffbult(Lodblf.Cbtfgory) dffbult FORMAT lodblf}.
     * Tif lodblf dbn bf dontrollfd using {@link DbtfTimfFormbttfr#witiLodblf(Lodblf) witiLodblf(Lodblf)}
     * on tif rfsult of tiis mftiod.
     * <p>
     * Notf tibt tif lodblizfd pbttfrn is lookfd up lbzily.
     * Tiis {@dodf DbtfTimfFormbttfr} iolds tif stylf rfquirfd bnd tif lodblf,
     * looking up tif pbttfrn rfquirfd on dfmbnd.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     *
     * @pbrbm dbtfTimfStylf  tif formbttfr stylf to obtbin, not null
     * @rfturn tif dbtf-timf formbttfr, not null
     */
    publid stbtid DbtfTimfFormbttfr ofLodblizfdDbtfTimf(FormbtStylf dbtfTimfStylf) {
        Objfdts.rfquirfNonNull(dbtfTimfStylf, "dbtfTimfStylf");
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndLodblizfd(dbtfTimfStylf, dbtfTimfStylf)
                .toFormbttfr(RfsolvfrStylf.SMART, IsoCironology.INSTANCE);
    }

    /**
     * Rfturns b lodblf spfdifid dbtf bnd timf formbt for tif ISO dironology.
     * <p>
     * Tiis rfturns b formbttfr tibt will formbt or pbrsf b dbtf-timf.
     * Tif fxbdt formbt pbttfrn usfd vbrifs by lodblf.
     * <p>
     * Tif lodblf is dftfrminfd from tif formbttfr. Tif formbttfr rfturnfd dirfdtly by
     * tiis mftiod will usf tif {@link Lodblf#gftDffbult() dffbult FORMAT lodblf}.
     * Tif lodblf dbn bf dontrollfd using {@link DbtfTimfFormbttfr#witiLodblf(Lodblf) witiLodblf(Lodblf)}
     * on tif rfsult of tiis mftiod.
     * <p>
     * Notf tibt tif lodblizfd pbttfrn is lookfd up lbzily.
     * Tiis {@dodf DbtfTimfFormbttfr} iolds tif stylf rfquirfd bnd tif lodblf,
     * looking up tif pbttfrn rfquirfd on dfmbnd.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     *
     * @pbrbm dbtfStylf  tif dbtf formbttfr stylf to obtbin, not null
     * @pbrbm timfStylf  tif timf formbttfr stylf to obtbin, not null
     * @rfturn tif dbtf, timf or dbtf-timf formbttfr, not null
     */
    publid stbtid DbtfTimfFormbttfr ofLodblizfdDbtfTimf(FormbtStylf dbtfStylf, FormbtStylf timfStylf) {
        Objfdts.rfquirfNonNull(dbtfStylf, "dbtfStylf");
        Objfdts.rfquirfNonNull(timfStylf, "timfStylf");
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndLodblizfd(dbtfStylf, timfStylf)
                .toFormbttfr(RfsolvfrStylf.SMART, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf formbttfr tibt formbts or pbrsfs b dbtf witiout bn
     * offsft, sudi bs '2011-12-03'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd lodbl dbtf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Four digits or morf for tif {@link CironoFifld#YEAR yfbr}.
     * Yfbrs in tif rbngf 0000 to 9999 will bf prf-pbddfd by zfro to fnsurf four digits.
     * Yfbrs outsidf tibt rbngf will ibvf b prffixfd positivf or nfgbtivf symbol.
     * <li>A dbsi
     * <li>Two digits for tif {@link CironoFifld#MONTH_OF_YEAR monti-of-yfbr}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>A dbsi
     * <li>Two digits for tif {@link CironoFifld#DAY_OF_MONTH dby-of-monti}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_LOCAL_DATE;
    stbtid {
        ISO_LOCAL_DATE = nfw DbtfTimfFormbttfrBuildfr()
                .bppfndVbluf(YEAR, 4, 10, SignStylf.EXCEEDS_PAD)
                .bppfndLitfrbl('-')
                .bppfndVbluf(MONTH_OF_YEAR, 2)
                .bppfndLitfrbl('-')
                .bppfndVbluf(DAY_OF_MONTH, 2)
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf formbttfr tibt formbts or pbrsfs b dbtf witi bn
     * offsft, sudi bs '2011-12-03+01:00'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd offsft dbtf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_LOCAL_DATE}
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_OFFSET_DATE;
    stbtid {
        ISO_OFFSET_DATE = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfnd(ISO_LOCAL_DATE)
                .bppfndOffsftId()
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf formbttfr tibt formbts or pbrsfs b dbtf witi tif
     * offsft if bvbilbblf, sudi bs '2011-12-03' or '2011-12-03+01:00'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd dbtf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_LOCAL_DATE}
     * <li>If tif offsft is not bvbilbblf tifn tif formbt is domplftf.
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * As tiis formbttfr ibs bn optionbl flfmfnt, it mby bf nfdfssbry to pbrsf using
     * {@link DbtfTimfFormbttfr#pbrsfBfst}.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_DATE;
    stbtid {
        ISO_DATE = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfnd(ISO_LOCAL_DATE)
                .optionblStbrt()
                .bppfndOffsftId()
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO timf formbttfr tibt formbts or pbrsfs b timf witiout bn
     * offsft, sudi bs '10:15' or '10:15:30'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd lodbl timf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Two digits for tif {@link CironoFifld#HOUR_OF_DAY iour-of-dby}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>A dolon
     * <li>Two digits for tif {@link CironoFifld#MINUTE_OF_HOUR minutf-of-iour}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>If tif sfdond-of-minutf is not bvbilbblf tifn tif formbt is domplftf.
     * <li>A dolon
     * <li>Two digits for tif {@link CironoFifld#SECOND_OF_MINUTE sfdond-of-minutf}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>If tif nbno-of-sfdond is zfro or not bvbilbblf tifn tif formbt is domplftf.
     * <li>A dfdimbl point
     * <li>Onf to ninf digits for tif {@link CironoFifld#NANO_OF_SECOND nbno-of-sfdond}.
     *  As mbny digits will bf output bs rfquirfd.
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs no ovfrridf dironology or zonf.
     * It usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_LOCAL_TIME;
    stbtid {
        ISO_LOCAL_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .bppfndVbluf(HOUR_OF_DAY, 2)
                .bppfndLitfrbl(':')
                .bppfndVbluf(MINUTE_OF_HOUR, 2)
                .optionblStbrt()
                .bppfndLitfrbl(':')
                .bppfndVbluf(SECOND_OF_MINUTE, 2)
                .optionblStbrt()
                .bppfndFrbdtion(NANO_OF_SECOND, 0, 9, truf)
                .toFormbttfr(RfsolvfrStylf.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO timf formbttfr tibt formbts or pbrsfs b timf witi bn
     * offsft, sudi bs '10:15+01:00' or '10:15:30+01:00'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd offsft timf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_LOCAL_TIME}
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs no ovfrridf dironology or zonf.
     * It usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_OFFSET_TIME;
    stbtid {
        ISO_OFFSET_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfnd(ISO_LOCAL_TIME)
                .bppfndOffsftId()
                .toFormbttfr(RfsolvfrStylf.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO timf formbttfr tibt formbts or pbrsfs b timf, witi tif
     * offsft if bvbilbblf, sudi bs '10:15', '10:15:30' or '10:15:30+01:00'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd offsft timf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_LOCAL_TIME}
     * <li>If tif offsft is not bvbilbblf tifn tif formbt is domplftf.
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * As tiis formbttfr ibs bn optionbl flfmfnt, it mby bf nfdfssbry to pbrsf using
     * {@link DbtfTimfFormbttfr#pbrsfBfst}.
     * <p>
     * Tif rfturnfd formbttfr ibs no ovfrridf dironology or zonf.
     * It usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_TIME;
    stbtid {
        ISO_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfnd(ISO_LOCAL_TIME)
                .optionblStbrt()
                .bppfndOffsftId()
                .toFormbttfr(RfsolvfrStylf.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf-timf formbttfr tibt formbts or pbrsfs b dbtf-timf witiout
     * bn offsft, sudi bs '2011-12-03T10:15:30'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd offsft dbtf-timf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_LOCAL_DATE}
     * <li>Tif lfttfr 'T'. Pbrsing is dbsf insfnsitivf.
     * <li>Tif {@link #ISO_LOCAL_TIME}
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_LOCAL_DATE_TIME;
    stbtid {
        ISO_LOCAL_DATE_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfnd(ISO_LOCAL_DATE)
                .bppfndLitfrbl('T')
                .bppfnd(ISO_LOCAL_TIME)
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf-timf formbttfr tibt formbts or pbrsfs b dbtf-timf witi bn
     * offsft, sudi bs '2011-12-03T10:15:30+01:00'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd offsft dbtf-timf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_LOCAL_DATE_TIME}
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_OFFSET_DATE_TIME;
    stbtid {
        ISO_OFFSET_DATE_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfnd(ISO_LOCAL_DATE_TIME)
                .bppfndOffsftId()
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO-likf dbtf-timf formbttfr tibt formbts or pbrsfs b dbtf-timf witi
     * offsft bnd zonf, sudi bs '2011-12-03T10:15:30+01:00[Europf/Pbris]'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * b formbt tibt fxtfnds tif ISO-8601 fxtfndfd offsft dbtf-timf formbt
     * to bdd tif timf-zonf.
     * Tif sfdtion in squbrf brbdkfts is not pbrt of tif ISO-8601 stbndbrd.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_OFFSET_DATE_TIME}
     * <li>If tif zonf ID is not bvbilbblf or is b {@dodf ZonfOffsft} tifn tif formbt is domplftf.
     * <li>An opfn squbrf brbdkft '['.
     * <li>Tif {@link ZonfId#gftId() zonf ID}. Tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf sfnsitivf.
     * <li>A dlosf squbrf brbdkft ']'.
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_ZONED_DATE_TIME;
    stbtid {
        ISO_ZONED_DATE_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .bppfnd(ISO_OFFSET_DATE_TIME)
                .optionblStbrt()
                .bppfndLitfrbl('[')
                .pbrsfCbsfSfnsitivf()
                .bppfndZonfRfgionId()
                .bppfndLitfrbl(']')
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO-likf dbtf-timf formbttfr tibt formbts or pbrsfs b dbtf-timf witi
     * tif offsft bnd zonf if bvbilbblf, sudi bs '2011-12-03T10:15:30',
     * '2011-12-03T10:15:30+01:00' or '2011-12-03T10:15:30+01:00[Europf/Pbris]'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd lodbl or offsft dbtf-timf formbt, bs wfll bs tif
     * fxtfndfd non-ISO form spfdifying tif timf-zonf.
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_LOCAL_DATE_TIME}
     * <li>If tif offsft is not bvbilbblf to formbt or pbrsf tifn tif formbt is domplftf.
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     * <li>If tif zonf ID is not bvbilbblf or is b {@dodf ZonfOffsft} tifn tif formbt is domplftf.
     * <li>An opfn squbrf brbdkft '['.
     * <li>Tif {@link ZonfId#gftId() zonf ID}. Tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf sfnsitivf.
     * <li>A dlosf squbrf brbdkft ']'.
     * </ul>
     * <p>
     * As tiis formbttfr ibs bn optionbl flfmfnt, it mby bf nfdfssbry to pbrsf using
     * {@link DbtfTimfFormbttfr#pbrsfBfst}.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_DATE_TIME;
    stbtid {
        ISO_DATE_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .bppfnd(ISO_LOCAL_DATE_TIME)
                .optionblStbrt()
                .bppfndOffsftId()
                .optionblStbrt()
                .bppfndLitfrbl('[')
                .pbrsfCbsfSfnsitivf()
                .bppfndZonfRfgionId()
                .bppfndLitfrbl(']')
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf formbttfr tibt formbts or pbrsfs tif ordinbl dbtf
     * witiout bn offsft, sudi bs '2012-337'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd ordinbl dbtf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Four digits or morf for tif {@link CironoFifld#YEAR yfbr}.
     * Yfbrs in tif rbngf 0000 to 9999 will bf prf-pbddfd by zfro to fnsurf four digits.
     * Yfbrs outsidf tibt rbngf will ibvf b prffixfd positivf or nfgbtivf symbol.
     * <li>A dbsi
     * <li>Tirff digits for tif {@link CironoFifld#DAY_OF_YEAR dby-of-yfbr}.
     *  Tiis is prf-pbddfd by zfro to fnsurf tirff digits.
     * <li>If tif offsft is not bvbilbblf to formbt or pbrsf tifn tif formbt is domplftf.
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * As tiis formbttfr ibs bn optionbl flfmfnt, it mby bf nfdfssbry to pbrsf using
     * {@link DbtfTimfFormbttfr#pbrsfBfst}.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_ORDINAL_DATE;
    stbtid {
        ISO_ORDINAL_DATE = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfndVbluf(YEAR, 4, 10, SignStylf.EXCEEDS_PAD)
                .bppfndLitfrbl('-')
                .bppfndVbluf(DAY_OF_YEAR, 3)
                .optionblStbrt()
                .bppfndOffsftId()
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf formbttfr tibt formbts or pbrsfs tif wffk-bbsfd dbtf
     * witiout bn offsft, sudi bs '2012-W48-6'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 fxtfndfd wffk-bbsfd dbtf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Four digits or morf for tif {@link IsoFiflds#WEEK_BASED_YEAR wffk-bbsfd-yfbr}.
     * Yfbrs in tif rbngf 0000 to 9999 will bf prf-pbddfd by zfro to fnsurf four digits.
     * Yfbrs outsidf tibt rbngf will ibvf b prffixfd positivf or nfgbtivf symbol.
     * <li>A dbsi
     * <li>Tif lfttfr 'W'. Pbrsing is dbsf insfnsitivf.
     * <li>Two digits for tif {@link IsoFiflds#WEEK_OF_WEEK_BASED_YEAR wffk-of-wffk-bbsfd-yfbr}.
     *  Tiis is prf-pbddfd by zfro to fnsurf tirff digits.
     * <li>A dbsi
     * <li>Onf digit for tif {@link CironoFifld#DAY_OF_WEEK dby-of-wffk}.
     *  Tif vbluf run from Mondby (1) to Sundby (7).
     * <li>If tif offsft is not bvbilbblf to formbt or pbrsf tifn tif formbt is domplftf.
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID}. If tif offsft ibs sfdonds tifn
     *  tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * As tiis formbttfr ibs bn optionbl flfmfnt, it mby bf nfdfssbry to pbrsf using
     * {@link DbtfTimfFormbttfr#pbrsfBfst}.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_WEEK_DATE;
    stbtid {
        ISO_WEEK_DATE = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfndVbluf(IsoFiflds.WEEK_BASED_YEAR, 4, 10, SignStylf.EXCEEDS_PAD)
                .bppfndLitfrbl("-W")
                .bppfndVbluf(IsoFiflds.WEEK_OF_WEEK_BASED_YEAR, 2)
                .bppfndLitfrbl('-')
                .bppfndVbluf(DAY_OF_WEEK, 1)
                .optionblStbrt()
                .bppfndOffsftId()
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO instbnt formbttfr tibt formbts or pbrsfs bn instbnt in UTC,
     * sudi bs '2011-12-03T10:15:30Z'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 instbnt formbt.
     * Wifn formbtting, tif sfdond-of-minutf is blwbys output.
     * Tif nbno-of-sfdond outputs zfro, tirff, six or ninf digits digits bs nfdfssbry.
     * Wifn pbrsing, timf to bt lfbst tif sfdonds fifld is rfquirfd.
     * Frbdtionbl sfdonds from zfro to ninf brf pbrsfd.
     * Tif lodblizfd dfdimbl stylf is not usfd.
     * <p>
     * Tiis is b spfdibl dbsf formbttfr intfndfd to bllow b iumbn rfbdbblf form
     * of bn {@link jbvb.timf.Instbnt}. Tif {@dodf Instbnt} dlbss is dfsignfd to
     * only rfprfsfnt b point in timf bnd intfrnblly storfs b vbluf in nbnosfdonds
     * from b fixfd fpodi of 1970-01-01Z. As sudi, bn {@dodf Instbnt} dbnnot bf
     * formbttfd bs b dbtf or timf witiout providing somf form of timf-zonf.
     * Tiis formbttfr bllows tif {@dodf Instbnt} to bf formbttfd, by providing
     * b suitbblf donvfrsion using {@dodf ZonfOffsft.UTC}.
     * <p>
     * Tif formbt donsists of:
     * <ul>
     * <li>Tif {@link #ISO_OFFSET_DATE_TIME} wifrf tif instbnt is donvfrtfd from
     *  {@link CironoFifld#INSTANT_SECONDS} bnd {@link CironoFifld#NANO_OF_SECOND}
     *  using tif {@dodf UTC} offsft. Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * Tif rfturnfd formbttfr ibs no ovfrridf dironology or zonf.
     * It usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr ISO_INSTANT;
    stbtid {
        ISO_INSTANT = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfndInstbnt()
                .toFormbttfr(RfsolvfrStylf.STRICT, null);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif ISO dbtf formbttfr tibt formbts or pbrsfs b dbtf witiout bn
     * offsft, sudi bs '20111203'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * tif ISO-8601 bbsid lodbl dbtf formbt.
     * Tif formbt donsists of:
     * <ul>
     * <li>Four digits for tif {@link CironoFifld#YEAR yfbr}.
     *  Only yfbrs in tif rbngf 0000 to 9999 brf supportfd.
     * <li>Two digits for tif {@link CironoFifld#MONTH_OF_YEAR monti-of-yfbr}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>Two digits for tif {@link CironoFifld#DAY_OF_MONTH dby-of-monti}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>If tif offsft is not bvbilbblf to formbt or pbrsf tifn tif formbt is domplftf.
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID} witiout dolons. If tif offsft ibs
     *  sfdonds tifn tify will bf ibndlfd fvfn tiougi tiis is not pbrt of tif ISO-8601 stbndbrd.
     *  Pbrsing is dbsf insfnsitivf.
     * </ul>
     * <p>
     * As tiis formbttfr ibs bn optionbl flfmfnt, it mby bf nfdfssbry to pbrsf using
     * {@link DbtfTimfFormbttfr#pbrsfBfst}.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#STRICT STRICT} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr BASIC_ISO_DATE;
    stbtid {
        BASIC_ISO_DATE = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .bppfndVbluf(YEAR, 4)
                .bppfndVbluf(MONTH_OF_YEAR, 2)
                .bppfndVbluf(DAY_OF_MONTH, 2)
                .optionblStbrt()
                .bppfndOffsft("+HHMMss", "Z")
                .toFormbttfr(RfsolvfrStylf.STRICT, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Tif RFC-1123 dbtf-timf formbttfr, sudi bs 'Tuf, 3 Jun 2008 11:05:30 GMT'.
     * <p>
     * Tiis rfturns bn immutbblf formbttfr dbpbblf of formbtting bnd pbrsing
     * most of tif RFC-1123 formbt.
     * RFC-1123 updbtfs RFC-822 dibnging tif yfbr from two digits to four.
     * Tiis implfmfntbtion rfquirfs b four digit yfbr.
     * Tiis implfmfntbtion blso dofs not ibndlf Norti Amfridbn or militbry zonf
     * nbmfs, only 'GMT' bnd offsft bmounts.
     * <p>
     * Tif formbt donsists of:
     * <ul>
     * <li>If tif dby-of-wffk is not bvbilbblf to formbt or pbrsf tifn jump to dby-of-monti.
     * <li>Tirff lfttfr {@link CironoFifld#DAY_OF_WEEK dby-of-wffk} in Englisi.
     * <li>A dommb
     * <li>A spbdf
     * <li>Onf or two digits for tif {@link CironoFifld#DAY_OF_MONTH dby-of-monti}.
     * <li>A spbdf
     * <li>Tirff lfttfr {@link CironoFifld#MONTH_OF_YEAR monti-of-yfbr} in Englisi.
     * <li>A spbdf
     * <li>Four digits for tif {@link CironoFifld#YEAR yfbr}.
     *  Only yfbrs in tif rbngf 0000 to 9999 brf supportfd.
     * <li>A spbdf
     * <li>Two digits for tif {@link CironoFifld#HOUR_OF_DAY iour-of-dby}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>A dolon
     * <li>Two digits for tif {@link CironoFifld#MINUTE_OF_HOUR minutf-of-iour}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>If tif sfdond-of-minutf is not bvbilbblf tifn jump to tif nfxt spbdf.
     * <li>A dolon
     * <li>Two digits for tif {@link CironoFifld#SECOND_OF_MINUTE sfdond-of-minutf}.
     *  Tiis is prf-pbddfd by zfro to fnsurf two digits.
     * <li>A spbdf
     * <li>Tif {@link ZonfOffsft#gftId() offsft ID} witiout dolons or sfdonds.
     *  An offsft of zfro usfs "GMT". Norti Amfridbn zonf nbmfs bnd militbry zonf nbmfs brf not ibndlfd.
     * </ul>
     * <p>
     * Pbrsing is dbsf insfnsitivf.
     * <p>
     * Tif rfturnfd formbttfr ibs b dironology of ISO sft to fnsurf dbtfs in
     * otifr dblfndbr systfms brf dorrfdtly donvfrtfd.
     * It ibs no ovfrridf zonf bnd usfs tif {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     */
    publid stbtid finbl DbtfTimfFormbttfr RFC_1123_DATE_TIME;
    stbtid {
        // mbnublly dodf mbps to fnsurf dorrfdt dbtb blwbys usfd
        // (lodblf dbtb dbn bf dibngfd by bpplidbtion dodf)
        Mbp<Long, String> dow = nfw HbsiMbp<>();
        dow.put(1L, "Mon");
        dow.put(2L, "Tuf");
        dow.put(3L, "Wfd");
        dow.put(4L, "Tiu");
        dow.put(5L, "Fri");
        dow.put(6L, "Sbt");
        dow.put(7L, "Sun");
        Mbp<Long, String> moy = nfw HbsiMbp<>();
        moy.put(1L, "Jbn");
        moy.put(2L, "Ffb");
        moy.put(3L, "Mbr");
        moy.put(4L, "Apr");
        moy.put(5L, "Mby");
        moy.put(6L, "Jun");
        moy.put(7L, "Jul");
        moy.put(8L, "Aug");
        moy.put(9L, "Sfp");
        moy.put(10L, "Odt");
        moy.put(11L, "Nov");
        moy.put(12L, "Dfd");
        RFC_1123_DATE_TIME = nfw DbtfTimfFormbttfrBuildfr()
                .pbrsfCbsfInsfnsitivf()
                .pbrsfLfnifnt()
                .optionblStbrt()
                .bppfndTfxt(DAY_OF_WEEK, dow)
                .bppfndLitfrbl(", ")
                .optionblEnd()
                .bppfndVbluf(DAY_OF_MONTH, 1, 2, SignStylf.NOT_NEGATIVE)
                .bppfndLitfrbl(' ')
                .bppfndTfxt(MONTH_OF_YEAR, moy)
                .bppfndLitfrbl(' ')
                .bppfndVbluf(YEAR, 4)  // 2 digit yfbr not ibndlfd
                .bppfndLitfrbl(' ')
                .bppfndVbluf(HOUR_OF_DAY, 2)
                .bppfndLitfrbl(':')
                .bppfndVbluf(MINUTE_OF_HOUR, 2)
                .optionblStbrt()
                .bppfndLitfrbl(':')
                .bppfndVbluf(SECOND_OF_MINUTE, 2)
                .optionblEnd()
                .bppfndLitfrbl(' ')
                .bppfndOffsft("+HHMM", "GMT")  // siould ibndlf UT/Z/EST/EDT/CST/CDT/MST/MDT/PST/MDT
                .toFormbttfr(RfsolvfrStylf.SMART, IsoCironology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * A qufry tibt providfs bddfss to tif fxdfss dbys tibt wfrf pbrsfd.
     * <p>
     * Tiis rfturns b singlfton {@linkplbin TfmporblQufry qufry} tibt providfs
     * bddfss to bdditionbl informbtion from tif pbrsf. Tif qufry blwbys rfturns
     * b non-null pfriod, witi b zfro pfriod rfturnfd instfbd of null.
     * <p>
     * Tifrf brf two situbtions wifrf tiis qufry mby rfturn b non-zfro pfriod.
     * <ul>
     * <li>If tif {@dodf RfsolvfrStylf} is {@dodf LENIENT} bnd b timf is pbrsfd
     *  witiout b dbtf, tifn tif domplftf rfsult of tif pbrsf donsists of b
     *  {@dodf LodblTimf} bnd bn fxdfss {@dodf Pfriod} in dbys.
     *
     * <li>If tif {@dodf RfsolvfrStylf} is {@dodf SMART} bnd b timf is pbrsfd
     *  witiout b dbtf wifrf tif timf is 24:00:00, tifn tif domplftf rfsult of
     *  tif pbrsf donsists of b {@dodf LodblTimf} of 00:00:00 bnd bn fxdfss
     *  {@dodf Pfriod} of onf dby.
     * </ul>
     * <p>
     * In boti dbsfs, if b domplftf {@dodf CironoLodblDbtfTimf} or {@dodf Instbnt}
     * is pbrsfd, tifn tif fxdfss dbys brf bddfd to tif dbtf pbrt.
     * As b rfsult, tiis qufry will rfturn b zfro pfriod.
     * <p>
     * Tif {@dodf SMART} bfibviour ibndlfs tif dommon "fnd of dby" 24:00 vbluf.
     * Prodfssing in {@dodf LENIENT} modf blso produdfs tif sbmf rfsult:
     * <prf>
     *  Tfxt to pbrsf        Pbrsfd objfdt                         Exdfss dbys
     *  "2012-12-03T00:00"   LodblDbtfTimf.of(2012, 12, 3, 0, 0)   ZERO
     *  "2012-12-03T24:00"   LodblDbtfTimf.of(2012, 12, 4, 0, 0)   ZERO
     *  "00:00"              LodblTimf.of(0, 0)                    ZERO
     *  "24:00"              LodblTimf.of(0, 0)                    Pfriod.ofDbys(1)
     * </prf>
     * Tif qufry dbn bf usfd bs follows:
     * <prf>
     *  TfmporblAddfssor pbrsfd = formbttfr.pbrsf(str);
     *  LodblTimf timf = pbrsfd.qufry(LodblTimf::from);
     *  Pfriod fxtrbDbys = pbrsfd.qufry(DbtfTimfFormbttfr.pbrsfdExdfssDbys());
     * </prf>
     * @rfturn b qufry tibt providfs bddfss to tif fxdfss dbys tibt wfrf pbrsfd
     */
    publid stbtid finbl TfmporblQufry<Pfriod> pbrsfdExdfssDbys() {
        rfturn PARSED_EXCESS_DAYS;
    }
    privbtf stbtid finbl TfmporblQufry<Pfriod> PARSED_EXCESS_DAYS = t -> {
        if (t instbndfof Pbrsfd) {
            rfturn ((Pbrsfd) t).fxdfssDbys;
        } flsf {
            rfturn Pfriod.ZERO;
        }
    };

    /**
     * A qufry tibt providfs bddfss to wiftifr b lfbp-sfdond wbs pbrsfd.
     * <p>
     * Tiis rfturns b singlfton {@linkplbin TfmporblQufry qufry} tibt providfs
     * bddfss to bdditionbl informbtion from tif pbrsf. Tif qufry blwbys rfturns
     * b non-null boolfbn, truf if pbrsing sbw b lfbp-sfdond, fblsf if not.
     * <p>
     * Instbnt pbrsing ibndlfs tif spfdibl "lfbp sfdond" timf of '23:59:60'.
     * Lfbp sfdonds oddur bt '23:59:60' in tif UTC timf-zonf, but bt otifr
     * lodbl timfs in difffrfnt timf-zonfs. To bvoid tiis potfntibl bmbiguity,
     * tif ibndling of lfbp-sfdonds is limitfd to
     * {@link DbtfTimfFormbttfrBuildfr#bppfndInstbnt()}, bs tibt mftiod
     * blwbys pbrsfs tif instbnt witi tif UTC zonf offsft.
     * <p>
     * If tif timf '23:59:60' is rfdfivfd, tifn b simplf donvfrsion is bpplifd,
     * rfplbding tif sfdond-of-minutf of 60 witi 59. Tiis qufry dbn bf usfd
     * on tif pbrsf rfsult to dftfrminf if tif lfbp-sfdond bdjustmfnt wbs mbdf.
     * Tif qufry will rfturn onf sfdond of fxdfss if it did bdjust to rfmovf
     * tif lfbp-sfdond, bnd zfro if not. Notf tibt bpplying b lfbp-sfdond
     * smootiing mfdibnism, sudi bs UTC-SLS, is tif rfsponsibility of tif
     * bpplidbtion, bs follows:
     * <prf>
     *  TfmporblAddfssor pbrsfd = formbttfr.pbrsf(str);
     *  Instbnt instbnt = pbrsfd.qufry(Instbnt::from);
     *  if (pbrsfd.qufry(DbtfTimfFormbttfr.pbrsfdLfbpSfdond())) {
     *    // vblidbtf lfbp-sfdond is dorrfdt bnd bpply dorrfdt smootiing
     *  }
     * </prf>
     * @rfturn b qufry tibt providfs bddfss to wiftifr b lfbp-sfdond wbs pbrsfd
     */
    publid stbtid finbl TfmporblQufry<Boolfbn> pbrsfdLfbpSfdond() {
        rfturn PARSED_LEAP_SECOND;
    }
    privbtf stbtid finbl TfmporblQufry<Boolfbn> PARSED_LEAP_SECOND = t -> {
        if (t instbndfof Pbrsfd) {
            rfturn ((Pbrsfd) t).lfbpSfdond;
        } flsf {
            rfturn Boolfbn.FALSE;
        }
    };

    //-----------------------------------------------------------------------
    /**
     * Construdtor.
     *
     * @pbrbm printfrPbrsfr  tif printfr/pbrsfr to usf, not null
     * @pbrbm lodblf  tif lodblf to usf, not null
     * @pbrbm dfdimblStylf  tif DfdimblStylf to usf, not null
     * @pbrbm rfsolvfrStylf  tif rfsolvfr stylf to usf, not null
     * @pbrbm rfsolvfrFiflds  tif fiflds to usf during rfsolving, null for bll fiflds
     * @pbrbm dirono  tif dironology to usf, null for no ovfrridf
     * @pbrbm zonf  tif zonf to usf, null for no ovfrridf
     */
    DbtfTimfFormbttfr(CompositfPrintfrPbrsfr printfrPbrsfr,
            Lodblf lodblf, DfdimblStylf dfdimblStylf,
            RfsolvfrStylf rfsolvfrStylf, Sft<TfmporblFifld> rfsolvfrFiflds,
            Cironology dirono, ZonfId zonf) {
        tiis.printfrPbrsfr = Objfdts.rfquirfNonNull(printfrPbrsfr, "printfrPbrsfr");
        tiis.rfsolvfrFiflds = rfsolvfrFiflds;
        tiis.lodblf = Objfdts.rfquirfNonNull(lodblf, "lodblf");
        tiis.dfdimblStylf = Objfdts.rfquirfNonNull(dfdimblStylf, "dfdimblStylf");
        tiis.rfsolvfrStylf = Objfdts.rfquirfNonNull(rfsolvfrStylf, "rfsolvfrStylf");
        tiis.dirono = dirono;
        tiis.zonf = zonf;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif lodblf to bf usfd during formbtting.
     * <p>
     * Tiis is usfd to lookup bny pbrt of tif formbttfr nffding spfdifid
     * lodblizbtion, sudi bs tif tfxt or lodblizfd pbttfrn.
     *
     * @rfturn tif lodblf of tiis formbttfr, not null
     */
    publid Lodblf gftLodblf() {
        rfturn lodblf;
    }

    /**
     * Rfturns b dopy of tiis formbttfr witi b nfw lodblf.
     * <p>
     * Tiis is usfd to lookup bny pbrt of tif formbttfr nffding spfdifid
     * lodblizbtion, sudi bs tif tfxt or lodblizfd pbttfrn.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm lodblf  tif nfw lodblf, not null
     * @rfturn b formbttfr bbsfd on tiis formbttfr witi tif rfqufstfd lodblf, not null
     */
    publid DbtfTimfFormbttfr witiLodblf(Lodblf lodblf) {
        if (tiis.lodblf.fqubls(lodblf)) {
            rfturn tiis;
        }
        rfturn nfw DbtfTimfFormbttfr(printfrPbrsfr, lodblf, dfdimblStylf, rfsolvfrStylf, rfsolvfrFiflds, dirono, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif DfdimblStylf to bf usfd during formbtting.
     *
     * @rfturn tif lodblf of tiis formbttfr, not null
     */
    publid DfdimblStylf gftDfdimblStylf() {
        rfturn dfdimblStylf;
    }

    /**
     * Rfturns b dopy of tiis formbttfr witi b nfw DfdimblStylf.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dfdimblStylf  tif nfw DfdimblStylf, not null
     * @rfturn b formbttfr bbsfd on tiis formbttfr witi tif rfqufstfd DfdimblStylf, not null
     */
    publid DbtfTimfFormbttfr witiDfdimblStylf(DfdimblStylf dfdimblStylf) {
        if (tiis.dfdimblStylf.fqubls(dfdimblStylf)) {
            rfturn tiis;
        }
        rfturn nfw DbtfTimfFormbttfr(printfrPbrsfr, lodblf, dfdimblStylf, rfsolvfrStylf, rfsolvfrFiflds, dirono, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif ovfrriding dironology to bf usfd during formbtting.
     * <p>
     * Tiis rfturns tif ovfrridf dironology, usfd to donvfrt dbtfs.
     * By dffbult, b formbttfr ibs no ovfrridf dironology, rfturning null.
     * Sff {@link #witiCironology(Cironology)} for morf dftbils on ovfrriding.
     *
     * @rfturn tif ovfrridf dironology of tiis formbttfr, null if no ovfrridf
     */
    publid Cironology gftCironology() {
        rfturn dirono;
    }

    /**
     * Rfturns b dopy of tiis formbttfr witi b nfw ovfrridf dironology.
     * <p>
     * Tiis rfturns b formbttfr witi similbr stbtf to tiis formbttfr but
     * witi tif ovfrridf dironology sft.
     * By dffbult, b formbttfr ibs no ovfrridf dironology, rfturning null.
     * <p>
     * If bn ovfrridf is bddfd, tifn bny dbtf tibt is formbttfd or pbrsfd will bf bfffdtfd.
     * <p>
     * Wifn formbtting, if tif tfmporbl objfdt dontbins b dbtf, tifn it will
     * bf donvfrtfd to b dbtf in tif ovfrridf dironology.
     * Wiftifr tif tfmporbl dontbins b dbtf is dftfrminfd by qufrying tif
     * {@link CironoFifld#EPOCH_DAY EPOCH_DAY} fifld.
     * Any timf or zonf will bf rftbinfd unbltfrfd unlfss ovfrriddfn.
     * <p>
     * If tif tfmporbl objfdt dofs not dontbin b dbtf, but dofs dontbin onf
     * or morf {@dodf CironoFifld} dbtf fiflds, tifn b {@dodf DbtfTimfExdfption}
     * is tirown. In bll otifr dbsfs, tif ovfrridf dironology is bddfd to tif tfmporbl,
     * rfplbding bny prfvious dironology, but witiout dibnging tif dbtf/timf.
     * <p>
     * Wifn pbrsing, tifrf brf two distindt dbsfs to donsidfr.
     * If b dironology ibs bffn pbrsfd dirfdtly from tif tfxt, pfribps bfdbusf
     * {@link DbtfTimfFormbttfrBuildfr#bppfndCironologyId()} wbs usfd, tifn
     * tiis ovfrridf dironology ibs no ffffdt.
     * If no zonf ibs bffn pbrsfd, tifn tiis ovfrridf dironology will bf usfd
     * to intfrprft tif {@dodf CironoFifld} vblufs into b dbtf bddording to tif
     * dbtf rfsolving rulfs of tif dironology.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dirono  tif nfw dironology, null if no ovfrridf
     * @rfturn b formbttfr bbsfd on tiis formbttfr witi tif rfqufstfd ovfrridf dironology, not null
     */
    publid DbtfTimfFormbttfr witiCironology(Cironology dirono) {
        if (Objfdts.fqubls(tiis.dirono, dirono)) {
            rfturn tiis;
        }
        rfturn nfw DbtfTimfFormbttfr(printfrPbrsfr, lodblf, dfdimblStylf, rfsolvfrStylf, rfsolvfrFiflds, dirono, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif ovfrriding zonf to bf usfd during formbtting.
     * <p>
     * Tiis rfturns tif ovfrridf zonf, usfd to donvfrt instbnts.
     * By dffbult, b formbttfr ibs no ovfrridf zonf, rfturning null.
     * Sff {@link #witiZonf(ZonfId)} for morf dftbils on ovfrriding.
     *
     * @rfturn tif ovfrridf zonf of tiis formbttfr, null if no ovfrridf
     */
    publid ZonfId gftZonf() {
        rfturn zonf;
    }

    /**
     * Rfturns b dopy of tiis formbttfr witi b nfw ovfrridf zonf.
     * <p>
     * Tiis rfturns b formbttfr witi similbr stbtf to tiis formbttfr but
     * witi tif ovfrridf zonf sft.
     * By dffbult, b formbttfr ibs no ovfrridf zonf, rfturning null.
     * <p>
     * If bn ovfrridf is bddfd, tifn bny instbnt tibt is formbttfd or pbrsfd will bf bfffdtfd.
     * <p>
     * Wifn formbtting, if tif tfmporbl objfdt dontbins bn instbnt, tifn it will
     * bf donvfrtfd to b zonfd dbtf-timf using tif ovfrridf zonf.
     * Wiftifr tif tfmporbl is bn instbnt is dftfrminfd by qufrying tif
     * {@link CironoFifld#INSTANT_SECONDS INSTANT_SECONDS} fifld.
     * If tif input ibs b dironology tifn it will bf rftbinfd unlfss ovfrriddfn.
     * If tif input dofs not ibvf b dironology, sudi bs {@dodf Instbnt}, tifn
     * tif ISO dironology will bf usfd.
     * <p>
     * If tif tfmporbl objfdt dofs not dontbin bn instbnt, but dofs dontbin
     * bn offsft tifn bn bdditionbl difdk is mbdf. If tif normblizfd ovfrridf
     * zonf is bn offsft tibt difffrs from tif offsft of tif tfmporbl, tifn
     * b {@dodf DbtfTimfExdfption} is tirown. In bll otifr dbsfs, tif ovfrridf
     * zonf is bddfd to tif tfmporbl, rfplbding bny prfvious zonf, but witiout
     * dibnging tif dbtf/timf.
     * <p>
     * Wifn pbrsing, tifrf brf two distindt dbsfs to donsidfr.
     * If b zonf ibs bffn pbrsfd dirfdtly from tif tfxt, pfribps bfdbusf
     * {@link DbtfTimfFormbttfrBuildfr#bppfndZonfId()} wbs usfd, tifn
     * tiis ovfrridf zonf ibs no ffffdt.
     * If no zonf ibs bffn pbrsfd, tifn tiis ovfrridf zonf will bf indludfd in
     * tif rfsult of tif pbrsf wifrf it dbn bf usfd to build instbnts bnd dbtf-timfs.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm zonf  tif nfw ovfrridf zonf, null if no ovfrridf
     * @rfturn b formbttfr bbsfd on tiis formbttfr witi tif rfqufstfd ovfrridf zonf, not null
     */
    publid DbtfTimfFormbttfr witiZonf(ZonfId zonf) {
        if (Objfdts.fqubls(tiis.zonf, zonf)) {
            rfturn tiis;
        }
        rfturn nfw DbtfTimfFormbttfr(printfrPbrsfr, lodblf, dfdimblStylf, rfsolvfrStylf, rfsolvfrFiflds, dirono, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif rfsolvfr stylf to usf during pbrsing.
     * <p>
     * Tiis rfturns tif rfsolvfr stylf, usfd during tif sfdond pibsf of pbrsing
     * wifn fiflds brf rfsolvfd into dbtfs bnd timfs.
     * By dffbult, b formbttfr ibs tif {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     * Sff {@link #witiRfsolvfrStylf(RfsolvfrStylf)} for morf dftbils.
     *
     * @rfturn tif rfsolvfr stylf of tiis formbttfr, not null
     */
    publid RfsolvfrStylf gftRfsolvfrStylf() {
        rfturn rfsolvfrStylf;
    }

    /**
     * Rfturns b dopy of tiis formbttfr witi b nfw rfsolvfr stylf.
     * <p>
     * Tiis rfturns b formbttfr witi similbr stbtf to tiis formbttfr but
     * witi tif rfsolvfr stylf sft. By dffbult, b formbttfr ibs tif
     * {@link RfsolvfrStylf#SMART SMART} rfsolvfr stylf.
     * <p>
     * Cibnging tif rfsolvfr stylf only ibs bn ffffdt during pbrsing.
     * Pbrsing b tfxt string oddurs in two pibsfs.
     * Pibsf 1 is b bbsid tfxt pbrsf bddording to tif fiflds bddfd to tif buildfr.
     * Pibsf 2 rfsolvfs tif pbrsfd fifld-vbluf pbirs into dbtf bnd/or timf objfdts.
     * Tif rfsolvfr stylf is usfd to dontrol iow pibsf 2, rfsolving, ibppfns.
     * Sff {@dodf RfsolvfrStylf} for morf informbtion on tif options bvbilbblf.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm rfsolvfrStylf  tif nfw rfsolvfr stylf, not null
     * @rfturn b formbttfr bbsfd on tiis formbttfr witi tif rfqufstfd rfsolvfr stylf, not null
     */
    publid DbtfTimfFormbttfr witiRfsolvfrStylf(RfsolvfrStylf rfsolvfrStylf) {
        Objfdts.rfquirfNonNull(rfsolvfrStylf, "rfsolvfrStylf");
        if (Objfdts.fqubls(tiis.rfsolvfrStylf, rfsolvfrStylf)) {
            rfturn tiis;
        }
        rfturn nfw DbtfTimfFormbttfr(printfrPbrsfr, lodblf, dfdimblStylf, rfsolvfrStylf, rfsolvfrFiflds, dirono, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif rfsolvfr fiflds to usf during pbrsing.
     * <p>
     * Tiis rfturns tif rfsolvfr fiflds, usfd during tif sfdond pibsf of pbrsing
     * wifn fiflds brf rfsolvfd into dbtfs bnd timfs.
     * By dffbult, b formbttfr ibs no rfsolvfr fiflds, bnd tius rfturns null.
     * Sff {@link #witiRfsolvfrFiflds(Sft)} for morf dftbils.
     *
     * @rfturn tif immutbblf sft of rfsolvfr fiflds of tiis formbttfr, null if no fiflds
     */
    publid Sft<TfmporblFifld> gftRfsolvfrFiflds() {
        rfturn rfsolvfrFiflds;
    }

    /**
     * Rfturns b dopy of tiis formbttfr witi b nfw sft of rfsolvfr fiflds.
     * <p>
     * Tiis rfturns b formbttfr witi similbr stbtf to tiis formbttfr but witi
     * tif rfsolvfr fiflds sft. By dffbult, b formbttfr ibs no rfsolvfr fiflds.
     * <p>
     * Cibnging tif rfsolvfr fiflds only ibs bn ffffdt during pbrsing.
     * Pbrsing b tfxt string oddurs in two pibsfs.
     * Pibsf 1 is b bbsid tfxt pbrsf bddording to tif fiflds bddfd to tif buildfr.
     * Pibsf 2 rfsolvfs tif pbrsfd fifld-vbluf pbirs into dbtf bnd/or timf objfdts.
     * Tif rfsolvfr fiflds brf usfd to filtfr tif fifld-vbluf pbirs bftwffn pibsf 1 bnd 2.
     * <p>
     * Tiis dbn bf usfd to sflfdt bftwffn two or morf wbys tibt b dbtf or timf migit
     * bf rfsolvfd. For fxbmplf, if tif formbttfr donsists of yfbr, monti, dby-of-monti
     * bnd dby-of-yfbr, tifn tifrf brf two wbys to rfsolvf b dbtf.
     * Cblling tiis mftiod witi tif brgumfnts {@link CironoFifld#YEAR YEAR} bnd
     * {@link CironoFifld#DAY_OF_YEAR DAY_OF_YEAR} will fnsurf tibt tif dbtf is
     * rfsolvfd using tif yfbr bnd dby-of-yfbr, ffffdtivfly mfbning tibt tif monti
     * bnd dby-of-monti brf ignorfd during tif rfsolving pibsf.
     * <p>
     * In b similbr mbnnfr, tiis mftiod dbn bf usfd to ignorf sfdondbry fiflds tibt
     * would otifrwisf bf dross-difdkfd. For fxbmplf, if tif formbttfr donsists of yfbr,
     * monti, dby-of-monti bnd dby-of-wffk, tifn tifrf is only onf wby to rfsolvf b
     * dbtf, but tif pbrsfd vbluf for dby-of-wffk will bf dross-difdkfd bgbinst tif
     * rfsolvfd dbtf. Cblling tiis mftiod witi tif brgumfnts {@link CironoFifld#YEAR YEAR},
     * {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} bnd
     * {@link CironoFifld#DAY_OF_MONTH DAY_OF_MONTH} will fnsurf tibt tif dbtf is
     * rfsolvfd dorrfdtly, but witiout bny dross-difdk for tif dby-of-wffk.
     * <p>
     * In implfmfntbtion tfrms, tiis mftiod bfibvfs bs follows. Tif rfsult of tif
     * pbrsing pibsf dbn bf donsidfrfd to bf b mbp of fifld to vbluf. Tif bfibvior
     * of tiis mftiod is to dbusf tibt mbp to bf filtfrfd bftwffn pibsf 1 bnd 2,
     * rfmoving bll fiflds otifr tibn tiosf spfdififd bs brgumfnts to tiis mftiod.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm rfsolvfrFiflds  tif nfw sft of rfsolvfr fiflds, null if no fiflds
     * @rfturn b formbttfr bbsfd on tiis formbttfr witi tif rfqufstfd rfsolvfr stylf, not null
     */
    publid DbtfTimfFormbttfr witiRfsolvfrFiflds(TfmporblFifld... rfsolvfrFiflds) {
        Sft<TfmporblFifld> fiflds = null;
        if (rfsolvfrFiflds != null) {
            fiflds = Collfdtions.unmodifibblfSft(nfw HbsiSft<>(Arrbys.bsList(rfsolvfrFiflds)));
        }
        if (Objfdts.fqubls(tiis.rfsolvfrFiflds, fiflds)) {
            rfturn tiis;
        }
        rfturn nfw DbtfTimfFormbttfr(printfrPbrsfr, lodblf, dfdimblStylf, rfsolvfrStylf, fiflds, dirono, zonf);
    }

    /**
     * Rfturns b dopy of tiis formbttfr witi b nfw sft of rfsolvfr fiflds.
     * <p>
     * Tiis rfturns b formbttfr witi similbr stbtf to tiis formbttfr but witi
     * tif rfsolvfr fiflds sft. By dffbult, b formbttfr ibs no rfsolvfr fiflds.
     * <p>
     * Cibnging tif rfsolvfr fiflds only ibs bn ffffdt during pbrsing.
     * Pbrsing b tfxt string oddurs in two pibsfs.
     * Pibsf 1 is b bbsid tfxt pbrsf bddording to tif fiflds bddfd to tif buildfr.
     * Pibsf 2 rfsolvfs tif pbrsfd fifld-vbluf pbirs into dbtf bnd/or timf objfdts.
     * Tif rfsolvfr fiflds brf usfd to filtfr tif fifld-vbluf pbirs bftwffn pibsf 1 bnd 2.
     * <p>
     * Tiis dbn bf usfd to sflfdt bftwffn two or morf wbys tibt b dbtf or timf migit
     * bf rfsolvfd. For fxbmplf, if tif formbttfr donsists of yfbr, monti, dby-of-monti
     * bnd dby-of-yfbr, tifn tifrf brf two wbys to rfsolvf b dbtf.
     * Cblling tiis mftiod witi tif brgumfnts {@link CironoFifld#YEAR YEAR} bnd
     * {@link CironoFifld#DAY_OF_YEAR DAY_OF_YEAR} will fnsurf tibt tif dbtf is
     * rfsolvfd using tif yfbr bnd dby-of-yfbr, ffffdtivfly mfbning tibt tif monti
     * bnd dby-of-monti brf ignorfd during tif rfsolving pibsf.
     * <p>
     * In b similbr mbnnfr, tiis mftiod dbn bf usfd to ignorf sfdondbry fiflds tibt
     * would otifrwisf bf dross-difdkfd. For fxbmplf, if tif formbttfr donsists of yfbr,
     * monti, dby-of-monti bnd dby-of-wffk, tifn tifrf is only onf wby to rfsolvf b
     * dbtf, but tif pbrsfd vbluf for dby-of-wffk will bf dross-difdkfd bgbinst tif
     * rfsolvfd dbtf. Cblling tiis mftiod witi tif brgumfnts {@link CironoFifld#YEAR YEAR},
     * {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} bnd
     * {@link CironoFifld#DAY_OF_MONTH DAY_OF_MONTH} will fnsurf tibt tif dbtf is
     * rfsolvfd dorrfdtly, but witiout bny dross-difdk for tif dby-of-wffk.
     * <p>
     * In implfmfntbtion tfrms, tiis mftiod bfibvfs bs follows. Tif rfsult of tif
     * pbrsing pibsf dbn bf donsidfrfd to bf b mbp of fifld to vbluf. Tif bfibvior
     * of tiis mftiod is to dbusf tibt mbp to bf filtfrfd bftwffn pibsf 1 bnd 2,
     * rfmoving bll fiflds otifr tibn tiosf spfdififd bs brgumfnts to tiis mftiod.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm rfsolvfrFiflds  tif nfw sft of rfsolvfr fiflds, null if no fiflds
     * @rfturn b formbttfr bbsfd on tiis formbttfr witi tif rfqufstfd rfsolvfr stylf, not null
     */
    publid DbtfTimfFormbttfr witiRfsolvfrFiflds(Sft<TfmporblFifld> rfsolvfrFiflds) {
        if (Objfdts.fqubls(tiis.rfsolvfrFiflds, rfsolvfrFiflds)) {
            rfturn tiis;
        }
        if (rfsolvfrFiflds != null) {
            rfsolvfrFiflds = Collfdtions.unmodifibblfSft(nfw HbsiSft<>(rfsolvfrFiflds));
        }
        rfturn nfw DbtfTimfFormbttfr(printfrPbrsfr, lodblf, dfdimblStylf, rfsolvfrStylf, rfsolvfrFiflds, dirono, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Formbts b dbtf-timf objfdt using tiis formbttfr.
     * <p>
     * Tiis formbts tif dbtf-timf to b String using tif rulfs of tif formbttfr.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to formbt, not null
     * @rfturn tif formbttfd string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during formbtting
     */
    publid String formbt(TfmporblAddfssor tfmporbl) {
        StringBuildfr buf = nfw StringBuildfr(32);
        formbtTo(tfmporbl, buf);
        rfturn buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Formbts b dbtf-timf objfdt to bn {@dodf Appfndbblf} using tiis formbttfr.
     * <p>
     * Tiis outputs tif formbttfd dbtf-timf to tif spfdififd dfstinbtion.
     * {@link Appfndbblf} is b gfnfrbl purposf intfrfbdf tibt is implfmfntfd by bll
     * kfy dibrbdtfr output dlbssfs indluding {@dodf StringBufffr}, {@dodf StringBuildfr},
     * {@dodf PrintStrfbm} bnd {@dodf Writfr}.
     * <p>
     * Altiougi {@dodf Appfndbblf} mftiods tirow bn {@dodf IOExdfption}, tiis mftiod dofs not.
     * Instfbd, bny {@dodf IOExdfption} is wrbppfd in b runtimf fxdfption.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to formbt, not null
     * @pbrbm bppfndbblf  tif bppfndbblf to formbt to, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during formbtting
     */
    publid void formbtTo(TfmporblAddfssor tfmporbl, Appfndbblf bppfndbblf) {
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        Objfdts.rfquirfNonNull(bppfndbblf, "bppfndbblf");
        try {
            DbtfTimfPrintContfxt dontfxt = nfw DbtfTimfPrintContfxt(tfmporbl, tiis);
            if (bppfndbblf instbndfof StringBuildfr) {
                printfrPbrsfr.formbt(dontfxt, (StringBuildfr) bppfndbblf);
            } flsf {
                // bufffr output to bvoid writing to bppfndbblf in dbsf of frror
                StringBuildfr buf = nfw StringBuildfr(32);
                printfrPbrsfr.formbt(dontfxt, buf);
                bppfndbblf.bppfnd(buf);
            }
        } dbtdi (IOExdfption fx) {
            tirow nfw DbtfTimfExdfption(fx.gftMfssbgf(), fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Fully pbrsfs tif tfxt produding b tfmporbl objfdt.
     * <p>
     * Tiis pbrsfs tif fntirf tfxt produding b tfmporbl objfdt.
     * It is typidblly morf usfful to usf {@link #pbrsf(CibrSfqufndf, TfmporblQufry)}.
     * Tif rfsult of tiis mftiod is {@dodf TfmporblAddfssor} wiidi ibs bffn rfsolvfd,
     * bpplying bbsid vblidbtion difdks to iflp fnsurf b vblid dbtf-timf.
     * <p>
     * If tif pbrsf domplftfs witiout rfbding tif fntirf lfngti of tif tfxt,
     * or b problfm oddurs during pbrsing or mfrging, tifn bn fxdfption is tirown.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @rfturn tif pbrsfd tfmporbl objfdt, not null
     * @tirows DbtfTimfPbrsfExdfption if unbblf to pbrsf tif rfqufstfd rfsult
     */
    publid TfmporblAddfssor pbrsf(CibrSfqufndf tfxt) {
        Objfdts.rfquirfNonNull(tfxt, "tfxt");
        try {
            rfturn pbrsfRfsolvfd0(tfxt, null);
        } dbtdi (DbtfTimfPbrsfExdfption fx) {
            tirow fx;
        } dbtdi (RuntimfExdfption fx) {
            tirow drfbtfError(tfxt, fx);
        }
    }

    /**
     * Pbrsfs tif tfxt using tiis formbttfr, providing dontrol ovfr tif tfxt position.
     * <p>
     * Tiis pbrsfs tif tfxt witiout rfquiring tif pbrsf to stbrt from tif bfginning
     * of tif string or finisi bt tif fnd.
     * Tif rfsult of tiis mftiod is {@dodf TfmporblAddfssor} wiidi ibs bffn rfsolvfd,
     * bpplying bbsid vblidbtion difdks to iflp fnsurf b vblid dbtf-timf.
     * <p>
     * Tif tfxt will bf pbrsfd from tif spfdififd stbrt {@dodf PbrsfPosition}.
     * Tif fntirf lfngti of tif tfxt dofs not ibvf to bf pbrsfd, tif {@dodf PbrsfPosition}
     * will bf updbtfd witi tif indfx bt tif fnd of pbrsing.
     * <p>
     * Tif opfrbtion of tiis mftiod is sligitly difffrfnt to similbr mftiods using
     * {@dodf PbrsfPosition} on {@dodf jbvb.tfxt.Formbt}. Tibt dlbss will rfturn
     * frrors using tif frror indfx on tif {@dodf PbrsfPosition}. By dontrbst, tiis
     * mftiod will tirow b {@link DbtfTimfPbrsfExdfption} if bn frror oddurs, witi
     * tif fxdfption dontbining tif frror indfx.
     * Tiis dibngf in bfibvior is nfdfssbry duf to tif indrfbsfd domplfxity of
     * pbrsing bnd rfsolving dbtfs/timfs in tiis API.
     * <p>
     * If tif formbttfr pbrsfs tif sbmf fifld morf tibn ondf witi difffrfnt vblufs,
     * tif rfsult will bf bn frror.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm position  tif position to pbrsf from, updbtfd witi lfngti pbrsfd
     *  bnd tif indfx of bny frror, not null
     * @rfturn tif pbrsfd tfmporbl objfdt, not null
     * @tirows DbtfTimfPbrsfExdfption if unbblf to pbrsf tif rfqufstfd rfsult
     * @tirows IndfxOutOfBoundsExdfption if tif position is invblid
     */
    publid TfmporblAddfssor pbrsf(CibrSfqufndf tfxt, PbrsfPosition position) {
        Objfdts.rfquirfNonNull(tfxt, "tfxt");
        Objfdts.rfquirfNonNull(position, "position");
        try {
            rfturn pbrsfRfsolvfd0(tfxt, position);
        } dbtdi (DbtfTimfPbrsfExdfption | IndfxOutOfBoundsExdfption fx) {
            tirow fx;
        } dbtdi (RuntimfExdfption fx) {
            tirow drfbtfError(tfxt, fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Fully pbrsfs tif tfxt produding bn objfdt of tif spfdififd typf.
     * <p>
     * Most bpplidbtions siould usf tiis mftiod for pbrsing.
     * It pbrsfs tif fntirf tfxt to produdf tif rfquirfd dbtf-timf.
     * Tif qufry is typidblly b mftiod rfffrfndf to b {@dodf from(TfmporblAddfssor)} mftiod.
     * For fxbmplf:
     * <prf>
     *  LodblDbtfTimf dt = pbrsfr.pbrsf(str, LodblDbtfTimf::from);
     * </prf>
     * If tif pbrsf domplftfs witiout rfbding tif fntirf lfngti of tif tfxt,
     * or b problfm oddurs during pbrsing or mfrging, tifn bn fxdfption is tirown.
     *
     * @pbrbm <T> tif typf of tif pbrsfd dbtf-timf
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm qufry  tif qufry dffining tif typf to pbrsf to, not null
     * @rfturn tif pbrsfd dbtf-timf, not null
     * @tirows DbtfTimfPbrsfExdfption if unbblf to pbrsf tif rfqufstfd rfsult
     */
    publid <T> T pbrsf(CibrSfqufndf tfxt, TfmporblQufry<T> qufry) {
        Objfdts.rfquirfNonNull(tfxt, "tfxt");
        Objfdts.rfquirfNonNull(qufry, "qufry");
        try {
            rfturn pbrsfRfsolvfd0(tfxt, null).qufry(qufry);
        } dbtdi (DbtfTimfPbrsfExdfption fx) {
            tirow fx;
        } dbtdi (RuntimfExdfption fx) {
            tirow drfbtfError(tfxt, fx);
        }
    }

    /**
     * Fully pbrsfs tif tfxt produding bn objfdt of onf of tif spfdififd typfs.
     * <p>
     * Tiis pbrsf mftiod is donvfnifnt for usf wifn tif pbrsfr dbn ibndlf optionbl flfmfnts.
     * For fxbmplf, b pbttfrn of 'uuuu-MM-dd HH.mm[ VV]' dbn bf fully pbrsfd to b {@dodf ZonfdDbtfTimf},
     * or pbrtiblly pbrsfd to b {@dodf LodblDbtfTimf}.
     * Tif qufrifs must bf spfdififd in ordfr, stbrting from tif bfst mbtdiing full-pbrsf option
     * bnd fnding witi tif worst mbtdiing minimbl pbrsf option.
     * Tif qufry is typidblly b mftiod rfffrfndf to b {@dodf from(TfmporblAddfssor)} mftiod.
     * <p>
     * Tif rfsult is bssodibtfd witi tif first typf tibt suddfssfully pbrsfs.
     * Normblly, bpplidbtions will usf {@dodf instbndfof} to difdk tif rfsult.
     * For fxbmplf:
     * <prf>
     *  TfmporblAddfssor dt = pbrsfr.pbrsfBfst(str, ZonfdDbtfTimf::from, LodblDbtfTimf::from);
     *  if (dt instbndfof ZonfdDbtfTimf) {
     *   ...
     *  } flsf {
     *   ...
     *  }
     * </prf>
     * If tif pbrsf domplftfs witiout rfbding tif fntirf lfngti of tif tfxt,
     * or b problfm oddurs during pbrsing or mfrging, tifn bn fxdfption is tirown.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm qufrifs  tif qufrifs dffining tif typfs to bttfmpt to pbrsf to,
     *  must implfmfnt {@dodf TfmporblAddfssor}, not null
     * @rfturn tif pbrsfd dbtf-timf, not null
     * @tirows IllfgblArgumfntExdfption if lfss tibn 2 typfs brf spfdififd
     * @tirows DbtfTimfPbrsfExdfption if unbblf to pbrsf tif rfqufstfd rfsult
     */
    publid TfmporblAddfssor pbrsfBfst(CibrSfqufndf tfxt, TfmporblQufry<?>... qufrifs) {
        Objfdts.rfquirfNonNull(tfxt, "tfxt");
        Objfdts.rfquirfNonNull(qufrifs, "qufrifs");
        if (qufrifs.lfngti < 2) {
            tirow nfw IllfgblArgumfntExdfption("At lfbst two qufrifs must bf spfdififd");
        }
        try {
            TfmporblAddfssor rfsolvfd = pbrsfRfsolvfd0(tfxt, null);
            for (TfmporblQufry<?> qufry : qufrifs) {
                try {
                    rfturn (TfmporblAddfssor) rfsolvfd.qufry(qufry);
                } dbtdi (RuntimfExdfption fx) {
                    // dontinuf
                }
            }
            tirow nfw DbtfTimfExdfption("Unbblf to donvfrt pbrsfd tfxt using bny of tif spfdififd qufrifs");
        } dbtdi (DbtfTimfPbrsfExdfption fx) {
            tirow fx;
        } dbtdi (RuntimfExdfption fx) {
            tirow drfbtfError(tfxt, fx);
        }
    }

    privbtf DbtfTimfPbrsfExdfption drfbtfError(CibrSfqufndf tfxt, RuntimfExdfption fx) {
        String bbbr;
        if (tfxt.lfngti() > 64) {
            bbbr = tfxt.subSfqufndf(0, 64).toString() + "...";
        } flsf {
            bbbr = tfxt.toString();
        }
        rfturn nfw DbtfTimfPbrsfExdfption("Tfxt '" + bbbr + "' dould not bf pbrsfd: " + fx.gftMfssbgf(), tfxt, 0, fx);
    }

    //-----------------------------------------------------------------------
    /**
     * Pbrsfs bnd rfsolvfs tif spfdififd tfxt.
     * <p>
     * Tiis pbrsfs to b {@dodf TfmporblAddfssor} fnsuring tibt tif tfxt is fully pbrsfd.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm position  tif position to pbrsf from, updbtfd witi lfngti pbrsfd
     *  bnd tif indfx of bny frror, null if pbrsing wiolf string
     * @rfturn tif rfsolvfd rfsult of tif pbrsf, not null
     * @tirows DbtfTimfPbrsfExdfption if tif pbrsf fbils
     * @tirows DbtfTimfExdfption if bn frror oddurs wiilf rfsolving tif dbtf or timf
     * @tirows IndfxOutOfBoundsExdfption if tif position is invblid
     */
    privbtf TfmporblAddfssor pbrsfRfsolvfd0(finbl CibrSfqufndf tfxt, finbl PbrsfPosition position) {
        PbrsfPosition pos = (position != null ? position : nfw PbrsfPosition(0));
        DbtfTimfPbrsfContfxt dontfxt = pbrsfUnrfsolvfd0(tfxt, pos);
        if (dontfxt == null || pos.gftErrorIndfx() >= 0 || (position == null && pos.gftIndfx() < tfxt.lfngti())) {
            String bbbr;
            if (tfxt.lfngti() > 64) {
                bbbr = tfxt.subSfqufndf(0, 64).toString() + "...";
            } flsf {
                bbbr = tfxt.toString();
            }
            if (pos.gftErrorIndfx() >= 0) {
                tirow nfw DbtfTimfPbrsfExdfption("Tfxt '" + bbbr + "' dould not bf pbrsfd bt indfx " +
                        pos.gftErrorIndfx(), tfxt, pos.gftErrorIndfx());
            } flsf {
                tirow nfw DbtfTimfPbrsfExdfption("Tfxt '" + bbbr + "' dould not bf pbrsfd, unpbrsfd tfxt found bt indfx " +
                        pos.gftIndfx(), tfxt, pos.gftIndfx());
            }
        }
        rfturn dontfxt.toRfsolvfd(rfsolvfrStylf, rfsolvfrFiflds);
    }

    /**
     * Pbrsfs tif tfxt using tiis formbttfr, witiout rfsolving tif rfsult, intfndfd
     * for bdvbndfd usf dbsfs.
     * <p>
     * Pbrsing is implfmfntfd bs b two-pibsf opfrbtion.
     * First, tif tfxt is pbrsfd using tif lbyout dffinfd by tif formbttfr, produding
     * b {@dodf Mbp} of fifld to vbluf, b {@dodf ZonfId} bnd b {@dodf Cironology}.
     * Sfdond, tif pbrsfd dbtb is <fm>rfsolvfd</fm>, by vblidbting, dombining bnd
     * simplifying tif vbrious fiflds into morf usfful onfs.
     * Tiis mftiod pfrforms tif pbrsing stbgf but not tif rfsolving stbgf.
     * <p>
     * Tif rfsult of tiis mftiod is {@dodf TfmporblAddfssor} wiidi rfprfsfnts tif
     * dbtb bs sffn in tif input. Vblufs brf not vblidbtfd, tius pbrsing b dbtf string
     * of '2012-00-65' would rfsult in b tfmporbl witi tirff fiflds - yfbr of '2012',
     * monti of '0' bnd dby-of-monti of '65'.
     * <p>
     * Tif tfxt will bf pbrsfd from tif spfdififd stbrt {@dodf PbrsfPosition}.
     * Tif fntirf lfngti of tif tfxt dofs not ibvf to bf pbrsfd, tif {@dodf PbrsfPosition}
     * will bf updbtfd witi tif indfx bt tif fnd of pbrsing.
     * <p>
     * Errors brf rfturnfd using tif frror indfx fifld of tif {@dodf PbrsfPosition}
     * instfbd of {@dodf DbtfTimfPbrsfExdfption}.
     * Tif rfturnfd frror indfx will bf sft to bn indfx indidbtivf of tif frror.
     * Cbllfrs must difdk for frrors bfforf using tif rfsult.
     * <p>
     * If tif formbttfr pbrsfs tif sbmf fifld morf tibn ondf witi difffrfnt vblufs,
     * tif rfsult will bf bn frror.
     * <p>
     * Tiis mftiod is intfndfd for bdvbndfd usf dbsfs tibt nffd bddfss to tif
     * intfrnbl stbtf during pbrsing. Typidbl bpplidbtion dodf siould usf
     * {@link #pbrsf(CibrSfqufndf, TfmporblQufry)} or tif pbrsf mftiod on tif tbrgft typf.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm position  tif position to pbrsf from, updbtfd witi lfngti pbrsfd
     *  bnd tif indfx of bny frror, not null
     * @rfturn tif pbrsfd tfxt, null if tif pbrsf rfsults in bn frror
     * @tirows DbtfTimfExdfption if somf problfm oddurs during pbrsing
     * @tirows IndfxOutOfBoundsExdfption if tif position is invblid
     */
    publid TfmporblAddfssor pbrsfUnrfsolvfd(CibrSfqufndf tfxt, PbrsfPosition position) {
        DbtfTimfPbrsfContfxt dontfxt = pbrsfUnrfsolvfd0(tfxt, position);
        if (dontfxt == null) {
            rfturn null;
        }
        rfturn dontfxt.toUnrfsolvfd();
    }

    privbtf DbtfTimfPbrsfContfxt pbrsfUnrfsolvfd0(CibrSfqufndf tfxt, PbrsfPosition position) {
        Objfdts.rfquirfNonNull(tfxt, "tfxt");
        Objfdts.rfquirfNonNull(position, "position");
        DbtfTimfPbrsfContfxt dontfxt = nfw DbtfTimfPbrsfContfxt(tiis);
        int pos = position.gftIndfx();
        pos = printfrPbrsfr.pbrsf(dontfxt, tfxt, pos);
        if (pos < 0) {
            position.sftErrorIndfx(~pos);  // indfx not updbtfd from input
            rfturn null;
        }
        position.sftIndfx(pos);  // frrorIndfx not updbtfd from input
        rfturn dontfxt;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif formbttfr bs b dompositf printfr pbrsfr.
     *
     * @pbrbm optionbl  wiftifr tif printfr/pbrsfr siould bf optionbl
     * @rfturn tif printfr/pbrsfr, not null
     */
    CompositfPrintfrPbrsfr toPrintfrPbrsfr(boolfbn optionbl) {
        rfturn printfrPbrsfr.witiOptionbl(optionbl);
    }

    /**
     * Rfturns tiis formbttfr bs b {@dodf jbvb.tfxt.Formbt} instbndf.
     * <p>
     * Tif rfturnfd {@link Formbt} instbndf will formbt bny {@link TfmporblAddfssor}
     * bnd pbrsfs to b rfsolvfd {@link TfmporblAddfssor}.
     * <p>
     * Exdfptions will follow tif dffinitions of {@dodf Formbt}, sff tiosf mftiods
     * for dftbils bbout {@dodf IllfgblArgumfntExdfption} during formbtting bnd
     * {@dodf PbrsfExdfption} or null during pbrsing.
     * Tif formbt dofs not support bttributing of tif rfturnfd formbt string.
     *
     * @rfturn tiis formbttfr bs b dlbssid formbt instbndf, not null
     */
    publid Formbt toFormbt() {
        rfturn nfw ClbssidFormbt(tiis, null);
    }

    /**
     * Rfturns tiis formbttfr bs b {@dodf jbvb.tfxt.Formbt} instbndf tibt will
     * pbrsf using tif spfdififd qufry.
     * <p>
     * Tif rfturnfd {@link Formbt} instbndf will formbt bny {@link TfmporblAddfssor}
     * bnd pbrsfs to tif typf spfdififd.
     * Tif typf must bf onf tibt is supportfd by {@link #pbrsf}.
     * <p>
     * Exdfptions will follow tif dffinitions of {@dodf Formbt}, sff tiosf mftiods
     * for dftbils bbout {@dodf IllfgblArgumfntExdfption} during formbtting bnd
     * {@dodf PbrsfExdfption} or null during pbrsing.
     * Tif formbt dofs not support bttributing of tif rfturnfd formbt string.
     *
     * @pbrbm pbrsfQufry  tif qufry dffining tif typf to pbrsf to, not null
     * @rfturn tiis formbttfr bs b dlbssid formbt instbndf, not null
     */
    publid Formbt toFormbt(TfmporblQufry<?> pbrsfQufry) {
        Objfdts.rfquirfNonNull(pbrsfQufry, "pbrsfQufry");
        rfturn nfw ClbssidFormbt(tiis, pbrsfQufry);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dfsdription of tif undfrlying formbttfrs.
     *
     * @rfturn b dfsdription of tiis formbttfr, not null
     */
    @Ovfrridf
    publid String toString() {
        String pbttfrn = printfrPbrsfr.toString();
        pbttfrn = pbttfrn.stbrtsWiti("[") ? pbttfrn : pbttfrn.substring(1, pbttfrn.lfngti() - 1);
        rfturn pbttfrn;
        // TODO: Fix tfsts to not dfpfnd on toString()
//        rfturn "DbtfTimfFormbttfr[" + lodblf +
//                (dirono != null ? "," + dirono : "") +
//                (zonf != null ? "," + zonf : "") +
//                pbttfrn + "]";
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfnts tif dlbssid Jbvb Formbt API.
     * @sfribl fxdludf
     */
    @SupprfssWbrnings("sfribl")  // not bdtublly sfriblizbblf
    stbtid dlbss ClbssidFormbt fxtfnds Formbt {
        /** Tif formbttfr. */
        privbtf finbl DbtfTimfFormbttfr formbttfr;
        /** Tif typf to bf pbrsfd. */
        privbtf finbl TfmporblQufry<?> pbrsfTypf;
        /** Construdtor. */
        publid ClbssidFormbt(DbtfTimfFormbttfr formbttfr, TfmporblQufry<?> pbrsfTypf) {
            tiis.formbttfr = formbttfr;
            tiis.pbrsfTypf = pbrsfTypf;
        }

        @Ovfrridf
        publid StringBufffr formbt(Objfdt obj, StringBufffr toAppfndTo, FifldPosition pos) {
            Objfdts.rfquirfNonNull(obj, "obj");
            Objfdts.rfquirfNonNull(toAppfndTo, "toAppfndTo");
            Objfdts.rfquirfNonNull(pos, "pos");
            if (obj instbndfof TfmporblAddfssor == fblsf) {
                tirow nfw IllfgblArgumfntExdfption("Formbt tbrgft must implfmfnt TfmporblAddfssor");
            }
            pos.sftBfginIndfx(0);
            pos.sftEndIndfx(0);
            try {
                formbttfr.formbtTo((TfmporblAddfssor) obj, toAppfndTo);
            } dbtdi (RuntimfExdfption fx) {
                tirow nfw IllfgblArgumfntExdfption(fx.gftMfssbgf(), fx);
            }
            rfturn toAppfndTo;
        }
        @Ovfrridf
        publid Objfdt pbrsfObjfdt(String tfxt) tirows PbrsfExdfption {
            Objfdts.rfquirfNonNull(tfxt, "tfxt");
            try {
                if (pbrsfTypf == null) {
                    rfturn formbttfr.pbrsfRfsolvfd0(tfxt, null);
                }
                rfturn formbttfr.pbrsf(tfxt, pbrsfTypf);
            } dbtdi (DbtfTimfPbrsfExdfption fx) {
                tirow nfw PbrsfExdfption(fx.gftMfssbgf(), fx.gftErrorIndfx());
            } dbtdi (RuntimfExdfption fx) {
                tirow (PbrsfExdfption) nfw PbrsfExdfption(fx.gftMfssbgf(), 0).initCbusf(fx);
            }
        }
        @Ovfrridf
        publid Objfdt pbrsfObjfdt(String tfxt, PbrsfPosition pos) {
            Objfdts.rfquirfNonNull(tfxt, "tfxt");
            DbtfTimfPbrsfContfxt dontfxt;
            try {
                dontfxt = formbttfr.pbrsfUnrfsolvfd0(tfxt, pos);
            } dbtdi (IndfxOutOfBoundsExdfption fx) {
                if (pos.gftErrorIndfx() < 0) {
                    pos.sftErrorIndfx(0);
                }
                rfturn null;
            }
            if (dontfxt == null) {
                if (pos.gftErrorIndfx() < 0) {
                    pos.sftErrorIndfx(0);
                }
                rfturn null;
            }
            try {
                TfmporblAddfssor rfsolvfd = dontfxt.toRfsolvfd(formbttfr.rfsolvfrStylf, formbttfr.rfsolvfrFiflds);
                if (pbrsfTypf == null) {
                    rfturn rfsolvfd;
                }
                rfturn rfsolvfd.qufry(pbrsfTypf);
            } dbtdi (RuntimfExdfption fx) {
                pos.sftErrorIndfx(0);
                rfturn null;
            }
        }
    }

}
