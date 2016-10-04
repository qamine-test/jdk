/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996-1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import stbtid jbvb.tfxt.DbtfFormbtSymbols.*;
import jbvb.util.Cblfndbr;
import jbvb.util.Dbtf;
import jbvb.util.GrfgoribnCblfndbr;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.SimplfTimfZonf;
import jbvb.util.SortfdMbp;
import jbvb.util.TimfZonf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import sun.util.dblfndbr.CblfndbrUtils;
import sun.util.dblfndbr.ZonfInfoFilf;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;

/**
 * <dodf>SimplfDbtfFormbt</dodf> is b dondrftf dlbss for formbtting bnd
 * pbrsing dbtfs in b lodblf-sfnsitivf mbnnfr. It bllows for formbtting
 * (dbtf &rbrr; tfxt), pbrsing (tfxt &rbrr; dbtf), bnd normblizbtion.
 *
 * <p>
 * <dodf>SimplfDbtfFormbt</dodf> bllows you to stbrt by dioosing
 * bny usfr-dffinfd pbttfrns for dbtf-timf formbtting. Howfvfr, you
 * brf fndourbgfd to drfbtf b dbtf-timf formbttfr witi fitifr
 * <dodf>gftTimfInstbndf</dodf>, <dodf>gftDbtfInstbndf</dodf>, or
 * <dodf>gftDbtfTimfInstbndf</dodf> in <dodf>DbtfFormbt</dodf>. Ebdi
 * of tifsf dlbss mftiods dbn rfturn b dbtf/timf formbttfr initiblizfd
 * witi b dffbult formbt pbttfrn. You mby modify tif formbt pbttfrn
 * using tif <dodf>bpplyPbttfrn</dodf> mftiods bs dfsirfd.
 * For morf informbtion on using tifsf mftiods, sff
 * {@link DbtfFormbt}.
 *
 * <i3>Dbtf bnd Timf Pbttfrns</i3>
 * <p>
 * Dbtf bnd timf formbts brf spfdififd by <fm>dbtf bnd timf pbttfrn</fm>
 * strings.
 * Witiin dbtf bnd timf pbttfrn strings, unquotfd lfttfrs from
 * <dodf>'A'</dodf> to <dodf>'Z'</dodf> bnd from <dodf>'b'</dodf> to
 * <dodf>'z'</dodf> brf intfrprftfd bs pbttfrn lfttfrs rfprfsfnting tif
 * domponfnts of b dbtf or timf string.
 * Tfxt dbn bf quotfd using singlf quotfs (<dodf>'</dodf>) to bvoid
 * intfrprftbtion.
 * <dodf>"''"</dodf> rfprfsfnts b singlf quotf.
 * All otifr dibrbdtfrs brf not intfrprftfd; tify'rf simply dopifd into tif
 * output string during formbtting or mbtdifd bgbinst tif input string
 * during pbrsing.
 * <p>
 * Tif following pbttfrn lfttfrs brf dffinfd (bll otifr dibrbdtfrs from
 * <dodf>'A'</dodf> to <dodf>'Z'</dodf> bnd from <dodf>'b'</dodf> to
 * <dodf>'z'</dodf> brf rfsfrvfd):
 * <blodkquotf>
 * <tbblf bordfr=0 dfllspbding=3 dfllpbdding=0 summbry="Cibrt siows pbttfrn lfttfrs, dbtf/timf domponfnt, prfsfntbtion, bnd fxbmplfs.">
 *     <tr stylf="bbdkground-dolor: rgb(204, 204, 255);">
 *         <ti blign=lfft>Lfttfr
 *         <ti blign=lfft>Dbtf or Timf Componfnt
 *         <ti blign=lfft>Prfsfntbtion
 *         <ti blign=lfft>Exbmplfs
 *     <tr>
 *         <td><dodf>G</dodf>
 *         <td>Erb dfsignbtor
 *         <td><b irff="#tfxt">Tfxt</b>
 *         <td><dodf>AD</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>y</dodf>
 *         <td>Yfbr
 *         <td><b irff="#yfbr">Yfbr</b>
 *         <td><dodf>1996</dodf>; <dodf>96</dodf>
 *     <tr>
 *         <td><dodf>Y</dodf>
 *         <td>Wffk yfbr
 *         <td><b irff="#yfbr">Yfbr</b>
 *         <td><dodf>2009</dodf>; <dodf>09</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>M</dodf>
 *         <td>Monti in yfbr (dontfxt sfnsitivf)
 *         <td><b irff="#monti">Monti</b>
 *         <td><dodf>July</dodf>; <dodf>Jul</dodf>; <dodf>07</dodf>
 *     <tr>
 *         <td><dodf>L</dodf>
 *         <td>Monti in yfbr (stbndblonf form)
 *         <td><b irff="#monti">Monti</b>
 *         <td><dodf>July</dodf>; <dodf>Jul</dodf>; <dodf>07</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>w</dodf>
 *         <td>Wffk in yfbr
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>27</dodf>
 *     <tr>
 *         <td><dodf>W</dodf>
 *         <td>Wffk in monti
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>2</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>D</dodf>
 *         <td>Dby in yfbr
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>189</dodf>
 *     <tr>
 *         <td><dodf>d</dodf>
 *         <td>Dby in monti
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>10</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>F</dodf>
 *         <td>Dby of wffk in monti
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>2</dodf>
 *     <tr>
 *         <td><dodf>E</dodf>
 *         <td>Dby nbmf in wffk
 *         <td><b irff="#tfxt">Tfxt</b>
 *         <td><dodf>Tufsdby</dodf>; <dodf>Tuf</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>u</dodf>
 *         <td>Dby numbfr of wffk (1 = Mondby, ..., 7 = Sundby)
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>1</dodf>
 *     <tr>
 *         <td><dodf>b</dodf>
 *         <td>Am/pm mbrkfr
 *         <td><b irff="#tfxt">Tfxt</b>
 *         <td><dodf>PM</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>H</dodf>
 *         <td>Hour in dby (0-23)
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>0</dodf>
 *     <tr>
 *         <td><dodf>k</dodf>
 *         <td>Hour in dby (1-24)
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>24</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>K</dodf>
 *         <td>Hour in bm/pm (0-11)
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>0</dodf>
 *     <tr>
 *         <td><dodf>i</dodf>
 *         <td>Hour in bm/pm (1-12)
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>12</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>m</dodf>
 *         <td>Minutf in iour
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>30</dodf>
 *     <tr>
 *         <td><dodf>s</dodf>
 *         <td>Sfdond in minutf
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>55</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>S</dodf>
 *         <td>Millisfdond
 *         <td><b irff="#numbfr">Numbfr</b>
 *         <td><dodf>978</dodf>
 *     <tr>
 *         <td><dodf>z</dodf>
 *         <td>Timf zonf
 *         <td><b irff="#timfzonf">Gfnfrbl timf zonf</b>
 *         <td><dodf>Pbdifid Stbndbrd Timf</dodf>; <dodf>PST</dodf>; <dodf>GMT-08:00</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>Z</dodf>
 *         <td>Timf zonf
 *         <td><b irff="#rfd822timfzonf">RFC 822 timf zonf</b>
 *         <td><dodf>-0800</dodf>
 *     <tr>
 *         <td><dodf>X</dodf>
 *         <td>Timf zonf
 *         <td><b irff="#iso8601timfzonf">ISO 8601 timf zonf</b>
 *         <td><dodf>-08</dodf>; <dodf>-0800</dodf>;  <dodf>-08:00</dodf>
 * </tbblf>
 * </blodkquotf>
 * Pbttfrn lfttfrs brf usublly rfpfbtfd, bs tifir numbfr dftfrminfs tif
 * fxbdt prfsfntbtion:
 * <ul>
 * <li><strong><b nbmf="tfxt">Tfxt:</b></strong>
 *     For formbtting, if tif numbfr of pbttfrn lfttfrs is 4 or morf,
 *     tif full form is usfd; otifrwisf b siort or bbbrfvibtfd form
 *     is usfd if bvbilbblf.
 *     For pbrsing, boti forms brf bddfptfd, indfpfndfnt of tif numbfr
 *     of pbttfrn lfttfrs.<br><br></li>
 * <li><strong><b nbmf="numbfr">Numbfr:</b></strong>
 *     For formbtting, tif numbfr of pbttfrn lfttfrs is tif minimum
 *     numbfr of digits, bnd siortfr numbfrs brf zfro-pbddfd to tiis bmount.
 *     For pbrsing, tif numbfr of pbttfrn lfttfrs is ignorfd unlfss
 *     it's nffdfd to sfpbrbtf two bdjbdfnt fiflds.<br><br></li>
 * <li><strong><b nbmf="yfbr">Yfbr:</b></strong>
 *     If tif formbttfr's {@link #gftCblfndbr() Cblfndbr} is tif Grfgoribn
 *     dblfndbr, tif following rulfs brf bpplifd.<br>
 *     <ul>
 *     <li>For formbtting, if tif numbfr of pbttfrn lfttfrs is 2, tif yfbr
 *         is trundbtfd to 2 digits; otifrwisf it is intfrprftfd bs b
 *         <b irff="#numbfr">numbfr</b>.
 *     <li>For pbrsing, if tif numbfr of pbttfrn lfttfrs is morf tibn 2,
 *         tif yfbr is intfrprftfd litfrblly, rfgbrdlfss of tif numbfr of
 *         digits. So using tif pbttfrn "MM/dd/yyyy", "01/11/12" pbrsfs to
 *         Jbn 11, 12 A.D.
 *     <li>For pbrsing witi tif bbbrfvibtfd yfbr pbttfrn ("y" or "yy"),
 *         <dodf>SimplfDbtfFormbt</dodf> must intfrprft tif bbbrfvibtfd yfbr
 *         rflbtivf to somf dfntury.  It dofs tiis by bdjusting dbtfs to bf
 *         witiin 80 yfbrs bfforf bnd 20 yfbrs bftfr tif timf tif <dodf>SimplfDbtfFormbt</dodf>
 *         instbndf is drfbtfd. For fxbmplf, using b pbttfrn of "MM/dd/yy" bnd b
 *         <dodf>SimplfDbtfFormbt</dodf> instbndf drfbtfd on Jbn 1, 1997,  tif string
 *         "01/11/12" would bf intfrprftfd bs Jbn 11, 2012 wiilf tif string "05/04/64"
 *         would bf intfrprftfd bs Mby 4, 1964.
 *         During pbrsing, only strings donsisting of fxbdtly two digits, bs dffinfd by
 *         {@link Cibrbdtfr#isDigit(dibr)}, will bf pbrsfd into tif dffbult dfntury.
 *         Any otifr numfrid string, sudi bs b onf digit string, b tirff or morf digit
 *         string, or b two digit string tibt isn't bll digits (for fxbmplf, "-1"), is
 *         intfrprftfd litfrblly.  So "01/02/3" or "01/02/003" brf pbrsfd, using tif
 *         sbmf pbttfrn, bs Jbn 2, 3 AD.  Likfwisf, "01/02/-3" is pbrsfd bs Jbn 2, 4 BC.
 *     </ul>
 *     Otifrwisf, dblfndbr systfm spfdifid forms brf bpplifd.
 *     For boti formbtting bnd pbrsing, if tif numbfr of pbttfrn
 *     lfttfrs is 4 or morf, b dblfndbr spfdifid {@linkplbin
 *     Cblfndbr#LONG long form} is usfd. Otifrwisf, b dblfndbr
 *     spfdifid {@linkplbin Cblfndbr#SHORT siort or bbbrfvibtfd form}
 *     is usfd.<br>
 *     <br>
 *     If wffk yfbr {@dodf 'Y'} is spfdififd bnd tif {@linkplbin
 *     #gftCblfndbr() dblfndbr} dofsn't support bny <b
 *     irff="../util/GrfgoribnCblfndbr.itml#wffk_yfbr"> wffk
 *     yfbrs</b>, tif dblfndbr yfbr ({@dodf 'y'}) is usfd instfbd. Tif
 *     support of wffk yfbrs dbn bf tfstfd witi b dbll to {@link
 *     DbtfFormbt#gftCblfndbr() gftCblfndbr()}.{@link
 *     jbvb.util.Cblfndbr#isWffkDbtfSupportfd()
 *     isWffkDbtfSupportfd()}.<br><br></li>
 * <li><strong><b nbmf="monti">Monti:</b></strong>
 *     If tif numbfr of pbttfrn lfttfrs is 3 or morf, tif monti is
 *     intfrprftfd bs <b irff="#tfxt">tfxt</b>; otifrwisf,
 *     it is intfrprftfd bs b <b irff="#numbfr">numbfr</b>.<br>
 *     <ul>
 *     <li>Lfttfr <fm>M</fm> produdfs dontfxt-sfnsitivf monti nbmfs, sudi bs tif
 *         fmbfddfd form of nbmfs. If b {@dodf DbtfFormbtSymbols} ibs bffn sft
 *         fxpliditly witi donstrudtor {@link #SimplfDbtfFormbt(String,
 *         DbtfFormbtSymbols)} or mftiod {@link
 *         #sftDbtfFormbtSymbols(DbtfFormbtSymbols)}, tif monti nbmfs givfn by
 *         tif {@dodf DbtfFormbtSymbols} brf usfd.</li>
 *     <li>Lfttfr <fm>L</fm> produdfs tif stbndblonf form of monti nbmfs.</li>
 *     </ul>
 *     <br></li>
 * <li><strong><b nbmf="timfzonf">Gfnfrbl timf zonf:</b></strong>
 *     Timf zonfs brf intfrprftfd bs <b irff="#tfxt">tfxt</b> if tify ibvf
 *     nbmfs. For timf zonfs rfprfsfnting b GMT offsft vbluf, tif
 *     following syntbx is usfd:
 *     <prf>
 *     <b nbmf="GMTOffsftTimfZonf"><i>GMTOffsftTimfZonf:</i></b>
 *             <dodf>GMT</dodf> <i>Sign</i> <i>Hours</i> <dodf>:</dodf> <i>Minutfs</i>
 *     <i>Sign:</i> onf of
 *             <dodf>+ -</dodf>
 *     <i>Hours:</i>
 *             <i>Digit</i>
 *             <i>Digit</i> <i>Digit</i>
 *     <i>Minutfs:</i>
 *             <i>Digit</i> <i>Digit</i>
 *     <i>Digit:</i> onf of
 *             <dodf>0 1 2 3 4 5 6 7 8 9</dodf></prf>
 *     <i>Hours</i> must bf bftwffn 0 bnd 23, bnd <i>Minutfs</i> must bf bftwffn
 *     00 bnd 59. Tif formbt is lodblf indfpfndfnt bnd digits must bf tbkfn
 *     from tif Bbsid Lbtin blodk of tif Unidodf stbndbrd.
 *     <p>For pbrsing, <b irff="#rfd822timfzonf">RFC 822 timf zonfs</b> brf blso
 *     bddfptfd.<br><br></li>
 * <li><strong><b nbmf="rfd822timfzonf">RFC 822 timf zonf:</b></strong>
 *     For formbtting, tif RFC 822 4-digit timf zonf formbt is usfd:
 *
 *     <prf>
 *     <i>RFC822TimfZonf:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i> <i>Minutfs</i>
 *     <i>TwoDigitHours:</i>
 *             <i>Digit Digit</i></prf>
 *     <i>TwoDigitHours</i> must bf bftwffn 00 bnd 23. Otifr dffinitions
 *     brf bs for <b irff="#timfzonf">gfnfrbl timf zonfs</b>.
 *
 *     <p>For pbrsing, <b irff="#timfzonf">gfnfrbl timf zonfs</b> brf blso
 *     bddfptfd.
 * <li><strong><b nbmf="iso8601timfzonf">ISO 8601 Timf zonf:</b></strong>
 *     Tif numbfr of pbttfrn lfttfrs dfsignbtfs tif formbt for boti formbtting
 *     bnd pbrsing bs follows:
 *     <prf>
 *     <i>ISO8601TimfZonf:</i>
 *             <i>OnfLfttfrISO8601TimfZonf</i>
 *             <i>TwoLfttfrISO8601TimfZonf</i>
 *             <i>TirffLfttfrISO8601TimfZonf</i>
 *     <i>OnfLfttfrISO8601TimfZonf:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i>
 *             {@dodf Z}
 *     <i>TwoLfttfrISO8601TimfZonf:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i> <i>Minutfs</i>
 *             {@dodf Z}
 *     <i>TirffLfttfrISO8601TimfZonf:</i>
 *             <i>Sign</i> <i>TwoDigitHours</i> {@dodf :} <i>Minutfs</i>
 *             {@dodf Z}</prf>
 *     Otifr dffinitions brf bs for <b irff="#timfzonf">gfnfrbl timf zonfs</b> or
 *     <b irff="#rfd822timfzonf">RFC 822 timf zonfs</b>.
 *
 *     <p>For formbtting, if tif offsft vbluf from GMT is 0, {@dodf "Z"} is
 *     produdfd. If tif numbfr of pbttfrn lfttfrs is 1, bny frbdtion of bn iour
 *     is ignorfd. For fxbmplf, if tif pbttfrn is {@dodf "X"} bnd tif timf zonf is
 *     {@dodf "GMT+05:30"}, {@dodf "+05"} is produdfd.
 *
 *     <p>For pbrsing, {@dodf "Z"} is pbrsfd bs tif UTC timf zonf dfsignbtor.
 *     <b irff="#timfzonf">Gfnfrbl timf zonfs</b> brf <fm>not</fm> bddfptfd.
 *
 *     <p>If tif numbfr of pbttfrn lfttfrs is 4 or morf, {@link
 *     IllfgblArgumfntExdfption} is tirown wifn donstrudting b {@dodf
 *     SimplfDbtfFormbt} or {@linkplbin #bpplyPbttfrn(String) bpplying b
 *     pbttfrn}.
 * </ul>
 * <dodf>SimplfDbtfFormbt</dodf> blso supports <fm>lodblizfd dbtf bnd timf
 * pbttfrn</fm> strings. In tifsf strings, tif pbttfrn lfttfrs dfsdribfd bbovf
 * mby bf rfplbdfd witi otifr, lodblf dfpfndfnt, pbttfrn lfttfrs.
 * <dodf>SimplfDbtfFormbt</dodf> dofs not dfbl witi tif lodblizbtion of tfxt
 * otifr tibn tif pbttfrn lfttfrs; tibt's up to tif dlifnt of tif dlbss.
 *
 * <i4>Exbmplfs</i4>
 *
 * Tif following fxbmplfs siow iow dbtf bnd timf pbttfrns brf intfrprftfd in
 * tif U.S. lodblf. Tif givfn dbtf bnd timf brf 2001-07-04 12:08:56 lodbl timf
 * in tif U.S. Pbdifid Timf timf zonf.
 * <blodkquotf>
 * <tbblf bordfr=0 dfllspbding=3 dfllpbdding=0 summbry="Exbmplfs of dbtf bnd timf pbttfrns intfrprftfd in tif U.S. lodblf">
 *     <tr stylf="bbdkground-dolor: rgb(204, 204, 255);">
 *         <ti blign=lfft>Dbtf bnd Timf Pbttfrn
 *         <ti blign=lfft>Rfsult
 *     <tr>
 *         <td><dodf>"yyyy.MM.dd G 'bt' HH:mm:ss z"</dodf>
 *         <td><dodf>2001.07.04 AD bt 12:08:56 PDT</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>"EEE, MMM d, ''yy"</dodf>
 *         <td><dodf>Wfd, Jul 4, '01</dodf>
 *     <tr>
 *         <td><dodf>"i:mm b"</dodf>
 *         <td><dodf>12:08 PM</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>"ii 'o''dlodk' b, zzzz"</dodf>
 *         <td><dodf>12 o'dlodk PM, Pbdifid Dbyligit Timf</dodf>
 *     <tr>
 *         <td><dodf>"K:mm b, z"</dodf>
 *         <td><dodf>0:08 PM, PDT</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>"yyyyy.MMMMM.dd GGG ii:mm bbb"</dodf>
 *         <td><dodf>02001.July.04 AD 12:08 PM</dodf>
 *     <tr>
 *         <td><dodf>"EEE, d MMM yyyy HH:mm:ss Z"</dodf>
 *         <td><dodf>Wfd, 4 Jul 2001 12:08:56 -0700</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>"yyMMddHHmmssZ"</dodf>
 *         <td><dodf>010704120856-0700</dodf>
 *     <tr>
 *         <td><dodf>"yyyy-MM-dd'T'HH:mm:ss.SSSZ"</dodf>
 *         <td><dodf>2001-07-04T12:08:56.235-0700</dodf>
 *     <tr stylf="bbdkground-dolor: rgb(238, 238, 255);">
 *         <td><dodf>"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"</dodf>
 *         <td><dodf>2001-07-04T12:08:56.235-07:00</dodf>
 *     <tr>
 *         <td><dodf>"YYYY-'W'ww-u"</dodf>
 *         <td><dodf>2001-W27-3</dodf>
 * </tbblf>
 * </blodkquotf>
 *
 * <i4><b nbmf="syndironizbtion">Syndironizbtion</b></i4>
 *
 * <p>
 * Dbtf formbts brf not syndironizfd.
 * It is rfdommfndfd to drfbtf sfpbrbtf formbt instbndfs for fbdi tirfbd.
 * If multiplf tirfbds bddfss b formbt dondurrfntly, it must bf syndironizfd
 * fxtfrnblly.
 *
 * @sff          <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/i18n/formbt/simplfDbtfFormbt.itml">Jbvb Tutoribl</b>
 * @sff          jbvb.util.Cblfndbr
 * @sff          jbvb.util.TimfZonf
 * @sff          DbtfFormbt
 * @sff          DbtfFormbtSymbols
 * @butior       Mbrk Dbvis, Cifn-Lifi Hubng, Albn Liu
 */
publid dlbss SimplfDbtfFormbt fxtfnds DbtfFormbt {

    // tif offidibl sfribl vfrsion ID wiidi sbys dryptidblly
    // wiidi vfrsion wf'rf dompbtiblf witi
    stbtid finbl long sfriblVfrsionUID = 4774881970558875024L;

    // tif intfrnbl sfribl vfrsion wiidi sbys wiidi vfrsion wbs writtfn
    // - 0 (dffbult) for vfrsion up to JDK 1.1.3
    // - 1 for vfrsion from JDK 1.1.4, wiidi indludfs b nfw fifld
    stbtid finbl int durrfntSfriblVfrsion = 1;

    /**
     * Tif vfrsion of tif sfriblizfd dbtb on tif strfbm.  Possiblf vblufs:
     * <ul>
     * <li><b>0</b> or not prfsfnt on strfbm: JDK 1.1.3.  Tiis vfrsion
     * ibs no <dodf>dffbultCfnturyStbrt</dodf> on strfbm.
     * <li><b>1</b> JDK 1.1.4 or lbtfr.  Tiis vfrsion bdds
     * <dodf>dffbultCfnturyStbrt</dodf>.
     * </ul>
     * Wifn strfbming out tiis dlbss, tif most rfdfnt formbt
     * bnd tif iigifst bllowbblf <dodf>sfriblVfrsionOnStrfbm</dodf>
     * is writtfn.
     * @sfribl
     * @sindf 1.1.4
     */
    privbtf int sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;

    /**
     * Tif pbttfrn string of tiis formbttfr.  Tiis is blwbys b non-lodblizfd
     * pbttfrn.  Mby not bf null.  Sff dlbss dodumfntbtion for dftbils.
     * @sfribl
     */
    privbtf String pbttfrn;

    /**
     * Sbvfd numbfrFormbt bnd pbttfrn.
     * @sff SimplfDbtfFormbt#difdkNfgbtivfNumbfrExprfssion
     */
    trbnsifnt privbtf NumbfrFormbt originblNumbfrFormbt;
    trbnsifnt privbtf String originblNumbfrPbttfrn;

    /**
     * Tif minus sign to bf usfd witi formbt bnd pbrsf.
     */
    trbnsifnt privbtf dibr minusSign = '-';

    /**
     * Truf wifn b nfgbtivf sign follows b numbfr.
     * (Truf bs dffbult in Arbbid.)
     */
    trbnsifnt privbtf boolfbn ibsFollowingMinusSign = fblsf;

    /**
     * Truf if stbndblonf form nffds to bf usfd.
     */
    trbnsifnt privbtf boolfbn fordfStbndblonfForm = fblsf;

    /**
     * Tif dompilfd pbttfrn.
     */
    trbnsifnt privbtf dibr[] dompilfdPbttfrn;

    /**
     * Tbgs for tif dompilfd pbttfrn.
     */
    privbtf finbl stbtid int TAG_QUOTE_ASCII_CHAR       = 100;
    privbtf finbl stbtid int TAG_QUOTE_CHARS            = 101;

    /**
     * Lodblf dfpfndfnt digit zfro.
     * @sff #zfroPbddingNumbfr
     * @sff jbvb.tfxt.DfdimblFormbtSymbols#gftZfroDigit
     */
    trbnsifnt privbtf dibr zfroDigit;

    /**
     * Tif symbols usfd by tiis formbttfr for wffk nbmfs, monti nbmfs,
     * ftd.  Mby not bf null.
     * @sfribl
     * @sff jbvb.tfxt.DbtfFormbtSymbols
     */
    privbtf DbtfFormbtSymbols formbtDbtb;

    /**
     * Wf mbp dbtfs witi two-digit yfbrs into tif dfntury stbrting bt
     * <dodf>dffbultCfnturyStbrt</dodf>, wiidi mby bf bny dbtf.  Mby
     * not bf null.
     * @sfribl
     * @sindf 1.1.4
     */
    privbtf Dbtf dffbultCfnturyStbrt;

    trbnsifnt privbtf int dffbultCfnturyStbrtYfbr;

    privbtf stbtid finbl int MILLIS_PER_MINUTE = 60 * 1000;

    // For timf zonfs tibt ibvf no nbmfs, usf strings GMT+minutfs bnd
    // GMT-minutfs. For instbndf, in Frbndf tif timf zonf is GMT+60.
    privbtf stbtid finbl String GMT = "GMT";

    /**
     * Cbdif NumbfrFormbt instbndfs witi Lodblf kfy.
     */
    privbtf stbtid finbl CondurrfntMbp<Lodblf, NumbfrFormbt> dbdifdNumbfrFormbtDbtb
        = nfw CondurrfntHbsiMbp<>(3);

    /**
     * Tif Lodblf usfd to instbntibtf tiis
     * <dodf>SimplfDbtfFormbt</dodf>. Tif vbluf mby bf null if tiis objfdt
     * ibs bffn drfbtfd by bn oldfr <dodf>SimplfDbtfFormbt</dodf> bnd
     * dfsfriblizfd.
     *
     * @sfribl
     * @sindf 1.6
     */
    privbtf Lodblf lodblf;

    /**
     * Indidbtfs wiftifr tiis <dodf>SimplfDbtfFormbt</dodf> siould usf
     * tif DbtfFormbtSymbols. If truf, tif formbt bnd pbrsf mftiods
     * usf tif DbtfFormbtSymbols vblufs. If fblsf, tif formbt bnd
     * pbrsf mftiods dbll Cblfndbr.gftDisplbyNbmf or
     * Cblfndbr.gftDisplbyNbmfs.
     */
    trbnsifnt boolfbn usfDbtfFormbtSymbols;

    /**
     * Construdts b <dodf>SimplfDbtfFormbt</dodf> using tif dffbult pbttfrn bnd
     * dbtf formbt symbols for tif dffbult
     * {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <b>Notf:</b> Tiis donstrudtor mby not support bll lodblfs.
     * For full dovfrbgf, usf tif fbdtory mftiods in tif {@link DbtfFormbt}
     * dlbss.
     */
    publid SimplfDbtfFormbt() {
        tiis("", Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
        bpplyPbttfrnImpl(LodblfProvidfrAdbptfr.gftRfsourdfBundlfBbsfd().gftLodblfRfsourdfs(lodblf)
                         .gftDbtfTimfPbttfrn(SHORT, SHORT, dblfndbr));
    }

    /**
     * Construdts b <dodf>SimplfDbtfFormbt</dodf> using tif givfn pbttfrn bnd
     * tif dffbult dbtf formbt symbols for tif dffbult
     * {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <b>Notf:</b> Tiis donstrudtor mby not support bll lodblfs.
     * For full dovfrbgf, usf tif fbdtory mftiods in tif {@link DbtfFormbt}
     * dlbss.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #SimplfDbtfFormbt(String, Lodblf)
     *     SimplfDbtfFormbt(pbttfrn, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     *
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @pbrbm pbttfrn tif pbttfrn dfsdribing tif dbtf bnd timf formbt
     * @fxdfption NullPointfrExdfption if tif givfn pbttfrn is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     */
    publid SimplfDbtfFormbt(String pbttfrn)
    {
        tiis(pbttfrn, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Construdts b <dodf>SimplfDbtfFormbt</dodf> using tif givfn pbttfrn bnd
     * tif dffbult dbtf formbt symbols for tif givfn lodblf.
     * <b>Notf:</b> Tiis donstrudtor mby not support bll lodblfs.
     * For full dovfrbgf, usf tif fbdtory mftiods in tif {@link DbtfFormbt}
     * dlbss.
     *
     * @pbrbm pbttfrn tif pbttfrn dfsdribing tif dbtf bnd timf formbt
     * @pbrbm lodblf tif lodblf wiosf dbtf formbt symbols siould bf usfd
     * @fxdfption NullPointfrExdfption if tif givfn pbttfrn or lodblf is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     */
    publid SimplfDbtfFormbt(String pbttfrn, Lodblf lodblf)
    {
        if (pbttfrn == null || lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        initiblizfCblfndbr(lodblf);
        tiis.pbttfrn = pbttfrn;
        tiis.formbtDbtb = DbtfFormbtSymbols.gftInstbndfRff(lodblf);
        tiis.lodblf = lodblf;
        initiblizf(lodblf);
    }

    /**
     * Construdts b <dodf>SimplfDbtfFormbt</dodf> using tif givfn pbttfrn bnd
     * dbtf formbt symbols.
     *
     * @pbrbm pbttfrn tif pbttfrn dfsdribing tif dbtf bnd timf formbt
     * @pbrbm formbtSymbols tif dbtf formbt symbols to bf usfd for formbtting
     * @fxdfption NullPointfrExdfption if tif givfn pbttfrn or formbtSymbols is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     */
    publid SimplfDbtfFormbt(String pbttfrn, DbtfFormbtSymbols formbtSymbols)
    {
        if (pbttfrn == null || formbtSymbols == null) {
            tirow nfw NullPointfrExdfption();
        }

        tiis.pbttfrn = pbttfrn;
        tiis.formbtDbtb = (DbtfFormbtSymbols) formbtSymbols.dlonf();
        tiis.lodblf = Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT);
        initiblizfCblfndbr(tiis.lodblf);
        initiblizf(tiis.lodblf);
        usfDbtfFormbtSymbols = truf;
    }

    /* Initiblizf dompilfdPbttfrn bnd numbfrFormbt fiflds */
    privbtf void initiblizf(Lodblf lod) {
        // Vfrify bnd dompilf tif givfn pbttfrn.
        dompilfdPbttfrn = dompilf(pbttfrn);

        /* try tif dbdif first */
        numbfrFormbt = dbdifdNumbfrFormbtDbtb.gft(lod);
        if (numbfrFormbt == null) { /* dbdif miss */
            numbfrFormbt = NumbfrFormbt.gftIntfgfrInstbndf(lod);
            numbfrFormbt.sftGroupingUsfd(fblsf);

            /* updbtf dbdif */
            dbdifdNumbfrFormbtDbtb.putIfAbsfnt(lod, numbfrFormbt);
        }
        numbfrFormbt = (NumbfrFormbt) numbfrFormbt.dlonf();

        initiblizfDffbultCfntury();
    }

    privbtf void initiblizfCblfndbr(Lodblf lod) {
        if (dblfndbr == null) {
            bssfrt lod != null;
            // Tif formbt objfdt must bf donstrudtfd using tif symbols for tiis zonf.
            // Howfvfr, tif dblfndbr siould usf tif durrfnt dffbult TimfZonf.
            // If tiis is not dontbinfd in tif lodblf zonf strings, tifn tif zonf
            // will bf formbttfd using gfnfrid GMT+/-H:MM nomfndlbturf.
            dblfndbr = Cblfndbr.gftInstbndf(TimfZonf.gftDffbult(), lod);
        }
    }

    /**
     * Rfturns tif dompilfd form of tif givfn pbttfrn. Tif syntbx of
     * tif dompilfd pbttfrn is:
     * <blodkquotf>
     * CompilfdPbttfrn:
     *     EntryList
     * EntryList:
     *     Entry
     *     EntryList Entry
     * Entry:
     *     TbgFifld
     *     TbgFifld dbtb
     * TbgFifld:
     *     Tbg Lfngti
     *     TbggfdDbtb
     * Tbg:
     *     pbttfrn_dibr_indfx
     *     TAG_QUOTE_CHARS
     * Lfngti:
     *     siort_lfngti
     *     long_lfngti
     * TbggfdDbtb:
     *     TAG_QUOTE_ASCII_CHAR bsdii_dibr
     *
     * </blodkquotf>
     *
     * wifrf `siort_lfngti' is bn 8-bit unsignfd intfgfr bftwffn 0 bnd
     * 254.  `long_lfngti' is b sfqufndf of bn 8-bit intfgfr 255 bnd b
     * 32-bit signfd intfgfr vbluf wiidi is split into uppfr bnd lowfr
     * 16-bit fiflds in two dibr's. `pbttfrn_dibr_indfx' is bn 8-bit
     * intfgfr bftwffn 0 bnd 18. `bsdii_dibr' is bn 7-bit ASCII
     * dibrbdtfr vbluf. `dbtb' dfpfnds on its Tbg vbluf.
     * <p>
     * If Lfngti is siort_lfngti, Tbg bnd siort_lfngti brf pbdkfd in b
     * singlf dibr, bs illustrbtfd bflow.
     * <blodkquotf>
     *     dibr[0] = (Tbg << 8) | siort_lfngti;
     * </blodkquotf>
     *
     * If Lfngti is long_lfngti, Tbg bnd 255 brf pbdkfd in tif first
     * dibr bnd b 32-bit intfgfr, bs illustrbtfd bflow.
     * <blodkquotf>
     *     dibr[0] = (Tbg << 8) | 255;
     *     dibr[1] = (dibr) (long_lfngti >>> 16);
     *     dibr[2] = (dibr) (long_lfngti & 0xffff);
     * </blodkquotf>
     * <p>
     * If Tbg is b pbttfrn_dibr_indfx, its Lfngti is tif numbfr of
     * pbttfrn dibrbdtfrs. For fxbmplf, if tif givfn pbttfrn is
     * "yyyy", Tbg is 1 bnd Lfngti is 4, followfd by no dbtb.
     * <p>
     * If Tbg is TAG_QUOTE_CHARS, its Lfngti is tif numbfr of dibr's
     * following tif TbgFifld. For fxbmplf, if tif givfn pbttfrn is
     * "'o''dlodk'", Lfngti is 7 followfd by b dibr sfqufndf of
     * <dodf>o&nbs;'&nbs;d&nbs;l&nbs;o&nbs;d&nbs;k</dodf>.
     * <p>
     * TAG_QUOTE_ASCII_CHAR is b spfdibl tbg bnd ibs bn ASCII
     * dibrbdtfr in plbdf of Lfngti. For fxbmplf, if tif givfn pbttfrn
     * is "'o'", tif TbggfdDbtb fntry is
     * <dodf>((TAG_QUOTE_ASCII_CHAR&nbs;<<&nbs;8)&nbs;|&nbs;'o')</dodf>.
     *
     * @fxdfption NullPointfrExdfption if tif givfn pbttfrn is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     */
    privbtf dibr[] dompilf(String pbttfrn) {
        int lfngti = pbttfrn.lfngti();
        boolfbn inQuotf = fblsf;
        StringBuildfr dompilfdCodf = nfw StringBuildfr(lfngti * 2);
        StringBuildfr tmpBufffr = null;
        int dount = 0, tbgdount = 0;
        int lbstTbg = -1, prfvTbg = -1;

        for (int i = 0; i < lfngti; i++) {
            dibr d = pbttfrn.dibrAt(i);

            if (d == '\'') {
                // '' is trfbtfd bs b singlf quotf rfgbrdlfss of bfing
                // in b quotfd sfdtion.
                if ((i + 1) < lfngti) {
                    d = pbttfrn.dibrAt(i + 1);
                    if (d == '\'') {
                        i++;
                        if (dount != 0) {
                            fndodf(lbstTbg, dount, dompilfdCodf);
                            tbgdount++;
                            prfvTbg = lbstTbg;
                            lbstTbg = -1;
                            dount = 0;
                        }
                        if (inQuotf) {
                            tmpBufffr.bppfnd(d);
                        } flsf {
                            dompilfdCodf.bppfnd((dibr)(TAG_QUOTE_ASCII_CHAR << 8 | d));
                        }
                        dontinuf;
                    }
                }
                if (!inQuotf) {
                    if (dount != 0) {
                        fndodf(lbstTbg, dount, dompilfdCodf);
                        tbgdount++;
                        prfvTbg = lbstTbg;
                        lbstTbg = -1;
                        dount = 0;
                    }
                    if (tmpBufffr == null) {
                        tmpBufffr = nfw StringBuildfr(lfngti);
                    } flsf {
                        tmpBufffr.sftLfngti(0);
                    }
                    inQuotf = truf;
                } flsf {
                    int lfn = tmpBufffr.lfngti();
                    if (lfn == 1) {
                        dibr di = tmpBufffr.dibrAt(0);
                        if (di < 128) {
                            dompilfdCodf.bppfnd((dibr)(TAG_QUOTE_ASCII_CHAR << 8 | di));
                        } flsf {
                            dompilfdCodf.bppfnd((dibr)(TAG_QUOTE_CHARS << 8 | 1));
                            dompilfdCodf.bppfnd(di);
                        }
                    } flsf {
                        fndodf(TAG_QUOTE_CHARS, lfn, dompilfdCodf);
                        dompilfdCodf.bppfnd(tmpBufffr);
                    }
                    inQuotf = fblsf;
                }
                dontinuf;
            }
            if (inQuotf) {
                tmpBufffr.bppfnd(d);
                dontinuf;
            }
            if (!(d >= 'b' && d <= 'z' || d >= 'A' && d <= 'Z')) {
                if (dount != 0) {
                    fndodf(lbstTbg, dount, dompilfdCodf);
                    tbgdount++;
                    prfvTbg = lbstTbg;
                    lbstTbg = -1;
                    dount = 0;
                }
                if (d < 128) {
                    // In most dbsfs, d would bf b dflimitfr, sudi bs ':'.
                    dompilfdCodf.bppfnd((dibr)(TAG_QUOTE_ASCII_CHAR << 8 | d));
                } flsf {
                    // Tbkf bny dontiguous non-ASCII blpibbft dibrbdtfrs bnd
                    // put tifm in b singlf TAG_QUOTE_CHARS.
                    int j;
                    for (j = i + 1; j < lfngti; j++) {
                        dibr d = pbttfrn.dibrAt(j);
                        if (d == '\'' || (d >= 'b' && d <= 'z' || d >= 'A' && d <= 'Z')) {
                            brfbk;
                        }
                    }
                    dompilfdCodf.bppfnd((dibr)(TAG_QUOTE_CHARS << 8 | (j - i)));
                    for (; i < j; i++) {
                        dompilfdCodf.bppfnd(pbttfrn.dibrAt(i));
                    }
                    i--;
                }
                dontinuf;
            }

            int tbg;
            if ((tbg = DbtfFormbtSymbols.pbttfrnCibrs.indfxOf(d)) == -1) {
                tirow nfw IllfgblArgumfntExdfption("Illfgbl pbttfrn dibrbdtfr " +
                                                   "'" + d + "'");
            }
            if (lbstTbg == -1 || lbstTbg == tbg) {
                lbstTbg = tbg;
                dount++;
                dontinuf;
            }
            fndodf(lbstTbg, dount, dompilfdCodf);
            tbgdount++;
            prfvTbg = lbstTbg;
            lbstTbg = tbg;
            dount = 1;
        }

        if (inQuotf) {
            tirow nfw IllfgblArgumfntExdfption("Untfrminbtfd quotf");
        }

        if (dount != 0) {
            fndodf(lbstTbg, dount, dompilfdCodf);
            tbgdount++;
            prfvTbg = lbstTbg;
        }

        fordfStbndblonfForm = (tbgdount == 1 && prfvTbg == PATTERN_MONTH);

        // Copy tif dompilfd pbttfrn to b dibr brrby
        int lfn = dompilfdCodf.lfngti();
        dibr[] r = nfw dibr[lfn];
        dompilfdCodf.gftCibrs(0, lfn, r, 0);
        rfturn r;
    }

    /**
     * Endodfs tif givfn tbg bnd lfngti bnd puts fndodfd dibr(s) into bufffr.
     */
    privbtf stbtid void fndodf(int tbg, int lfngti, StringBuildfr bufffr) {
        if (tbg == PATTERN_ISO_ZONE && lfngti >= 4) {
            tirow nfw IllfgblArgumfntExdfption("invblid ISO 8601 formbt: lfngti=" + lfngti);
        }
        if (lfngti < 255) {
            bufffr.bppfnd((dibr)(tbg << 8 | lfngti));
        } flsf {
            bufffr.bppfnd((dibr)((tbg << 8) | 0xff));
            bufffr.bppfnd((dibr)(lfngti >>> 16));
            bufffr.bppfnd((dibr)(lfngti & 0xffff));
        }
    }

    /* Initiblizf tif fiflds wf usf to disbmbigubtf bmbiguous yfbrs. Sfpbrbtf
     * so wf dbn dbll it from rfbdObjfdt().
     */
    privbtf void initiblizfDffbultCfntury() {
        dblfndbr.sftTimfInMillis(Systfm.durrfntTimfMillis());
        dblfndbr.bdd( Cblfndbr.YEAR, -80 );
        pbrsfAmbiguousDbtfsAsAftfr(dblfndbr.gftTimf());
    }

    /* Dffinf onf-dfntury window into wiidi to disbmbigubtf dbtfs using
     * two-digit yfbrs.
     */
    privbtf void pbrsfAmbiguousDbtfsAsAftfr(Dbtf stbrtDbtf) {
        dffbultCfnturyStbrt = stbrtDbtf;
        dblfndbr.sftTimf(stbrtDbtf);
        dffbultCfnturyStbrtYfbr = dblfndbr.gft(Cblfndbr.YEAR);
    }

    /**
     * Sfts tif 100-yfbr pfriod 2-digit yfbrs will bf intfrprftfd bs bfing in
     * to bfgin on tif dbtf tif usfr spfdififs.
     *
     * @pbrbm stbrtDbtf During pbrsing, two digit yfbrs will bf plbdfd in tif rbngf
     * <dodf>stbrtDbtf</dodf> to <dodf>stbrtDbtf + 100 yfbrs</dodf>.
     * @sff #gft2DigitYfbrStbrt
     * @sindf 1.2
     */
    publid void sft2DigitYfbrStbrt(Dbtf stbrtDbtf) {
        pbrsfAmbiguousDbtfsAsAftfr(nfw Dbtf(stbrtDbtf.gftTimf()));
    }

    /**
     * Rfturns tif bfginning dbtf of tif 100-yfbr pfriod 2-digit yfbrs brf intfrprftfd
     * bs bfing witiin.
     *
     * @rfturn tif stbrt of tif 100-yfbr pfriod into wiidi two digit yfbrs brf
     * pbrsfd
     * @sff #sft2DigitYfbrStbrt
     * @sindf 1.2
     */
    publid Dbtf gft2DigitYfbrStbrt() {
        rfturn (Dbtf) dffbultCfnturyStbrt.dlonf();
    }

    /**
     * Formbts tif givfn <dodf>Dbtf</dodf> into b dbtf/timf string bnd bppfnds
     * tif rfsult to tif givfn <dodf>StringBufffr</dodf>.
     *
     * @pbrbm dbtf tif dbtf-timf vbluf to bf formbttfd into b dbtf-timf string.
     * @pbrbm toAppfndTo wifrf tif nfw dbtf-timf tfxt is to bf bppfndfd.
     * @pbrbm pos tif formbtting position. On input: bn blignmfnt fifld,
     * if dfsirfd. On output: tif offsfts of tif blignmfnt fifld.
     * @rfturn tif formbttfd dbtf-timf string.
     * @fxdfption NullPointfrExdfption if tif givfn {@dodf dbtf} is {@dodf null}.
     */
    @Ovfrridf
    publid StringBufffr formbt(Dbtf dbtf, StringBufffr toAppfndTo,
                               FifldPosition pos)
    {
        pos.bfginIndfx = pos.fndIndfx = 0;
        rfturn formbt(dbtf, toAppfndTo, pos.gftFifldDflfgbtf());
    }

    // Cbllfd from Formbt bftfr drfbting b FifldDflfgbtf
    privbtf StringBufffr formbt(Dbtf dbtf, StringBufffr toAppfndTo,
                                FifldDflfgbtf dflfgbtf) {
        // Convfrt input dbtf to timf fifld list
        dblfndbr.sftTimf(dbtf);

        boolfbn usfDbtfFormbtSymbols = usfDbtfFormbtSymbols();

        for (int i = 0; i < dompilfdPbttfrn.lfngti; ) {
            int tbg = dompilfdPbttfrn[i] >>> 8;
            int dount = dompilfdPbttfrn[i++] & 0xff;
            if (dount == 255) {
                dount = dompilfdPbttfrn[i++] << 16;
                dount |= dompilfdPbttfrn[i++];
            }

            switdi (tbg) {
            dbsf TAG_QUOTE_ASCII_CHAR:
                toAppfndTo.bppfnd((dibr)dount);
                brfbk;

            dbsf TAG_QUOTE_CHARS:
                toAppfndTo.bppfnd(dompilfdPbttfrn, i, dount);
                i += dount;
                brfbk;

            dffbult:
                subFormbt(tbg, dount, dflfgbtf, toAppfndTo, usfDbtfFormbtSymbols);
                brfbk;
            }
        }
        rfturn toAppfndTo;
    }

    /**
     * Formbts bn Objfdt produding bn <dodf>AttributfdCibrbdtfrItfrbtor</dodf>.
     * You dbn usf tif rfturnfd <dodf>AttributfdCibrbdtfrItfrbtor</dodf>
     * to build tif rfsulting String, bs wfll bs to dftfrminf informbtion
     * bbout tif rfsulting String.
     * <p>
     * Ebdi bttributf kfy of tif AttributfdCibrbdtfrItfrbtor will bf of typf
     * <dodf>DbtfFormbt.Fifld</dodf>, witi tif dorrfsponding bttributf vbluf
     * bfing tif sbmf bs tif bttributf kfy.
     *
     * @fxdfption NullPointfrExdfption if obj is null.
     * @fxdfption IllfgblArgumfntExdfption if tif Formbt dbnnot formbt tif
     *            givfn objfdt, or if tif Formbt's pbttfrn string is invblid.
     * @pbrbm obj Tif objfdt to formbt
     * @rfturn AttributfdCibrbdtfrItfrbtor dfsdribing tif formbttfd vbluf.
     * @sindf 1.4
     */
    @Ovfrridf
    publid AttributfdCibrbdtfrItfrbtor formbtToCibrbdtfrItfrbtor(Objfdt obj) {
        StringBufffr sb = nfw StringBufffr();
        CibrbdtfrItfrbtorFifldDflfgbtf dflfgbtf = nfw
                         CibrbdtfrItfrbtorFifldDflfgbtf();

        if (obj instbndfof Dbtf) {
            formbt((Dbtf)obj, sb, dflfgbtf);
        }
        flsf if (obj instbndfof Numbfr) {
            formbt(nfw Dbtf(((Numbfr)obj).longVbluf()), sb, dflfgbtf);
        }
        flsf if (obj == null) {
            tirow nfw NullPointfrExdfption(
                   "formbtToCibrbdtfrItfrbtor must bf pbssfd non-null objfdt");
        }
        flsf {
            tirow nfw IllfgblArgumfntExdfption(
                             "Cbnnot formbt givfn Objfdt bs b Dbtf");
        }
        rfturn dflfgbtf.gftItfrbtor(sb.toString());
    }

    // Mbp indfx into pbttfrn dibrbdtfr string to Cblfndbr fifld numbfr
    privbtf stbtid finbl int[] PATTERN_INDEX_TO_CALENDAR_FIELD = {
        Cblfndbr.ERA,
        Cblfndbr.YEAR,
        Cblfndbr.MONTH,
        Cblfndbr.DATE,
        Cblfndbr.HOUR_OF_DAY,
        Cblfndbr.HOUR_OF_DAY,
        Cblfndbr.MINUTE,
        Cblfndbr.SECOND,
        Cblfndbr.MILLISECOND,
        Cblfndbr.DAY_OF_WEEK,
        Cblfndbr.DAY_OF_YEAR,
        Cblfndbr.DAY_OF_WEEK_IN_MONTH,
        Cblfndbr.WEEK_OF_YEAR,
        Cblfndbr.WEEK_OF_MONTH,
        Cblfndbr.AM_PM,
        Cblfndbr.HOUR,
        Cblfndbr.HOUR,
        Cblfndbr.ZONE_OFFSET,
        Cblfndbr.ZONE_OFFSET,
        CblfndbrBuildfr.WEEK_YEAR,         // Psfudo Cblfndbr fifld
        CblfndbrBuildfr.ISO_DAY_OF_WEEK,   // Psfudo Cblfndbr fifld
        Cblfndbr.ZONE_OFFSET,
        Cblfndbr.MONTH
    };

    // Mbp indfx into pbttfrn dibrbdtfr string to DbtfFormbt fifld numbfr
    privbtf stbtid finbl int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = {
        DbtfFormbt.ERA_FIELD,
        DbtfFormbt.YEAR_FIELD,
        DbtfFormbt.MONTH_FIELD,
        DbtfFormbt.DATE_FIELD,
        DbtfFormbt.HOUR_OF_DAY1_FIELD,
        DbtfFormbt.HOUR_OF_DAY0_FIELD,
        DbtfFormbt.MINUTE_FIELD,
        DbtfFormbt.SECOND_FIELD,
        DbtfFormbt.MILLISECOND_FIELD,
        DbtfFormbt.DAY_OF_WEEK_FIELD,
        DbtfFormbt.DAY_OF_YEAR_FIELD,
        DbtfFormbt.DAY_OF_WEEK_IN_MONTH_FIELD,
        DbtfFormbt.WEEK_OF_YEAR_FIELD,
        DbtfFormbt.WEEK_OF_MONTH_FIELD,
        DbtfFormbt.AM_PM_FIELD,
        DbtfFormbt.HOUR1_FIELD,
        DbtfFormbt.HOUR0_FIELD,
        DbtfFormbt.TIMEZONE_FIELD,
        DbtfFormbt.TIMEZONE_FIELD,
        DbtfFormbt.YEAR_FIELD,
        DbtfFormbt.DAY_OF_WEEK_FIELD,
        DbtfFormbt.TIMEZONE_FIELD,
        DbtfFormbt.MONTH_FIELD
    };

    // Mbps from DfdimblFormbtSymbols indfx to Fifld donstbnt
    privbtf stbtid finbl Fifld[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID = {
        Fifld.ERA,
        Fifld.YEAR,
        Fifld.MONTH,
        Fifld.DAY_OF_MONTH,
        Fifld.HOUR_OF_DAY1,
        Fifld.HOUR_OF_DAY0,
        Fifld.MINUTE,
        Fifld.SECOND,
        Fifld.MILLISECOND,
        Fifld.DAY_OF_WEEK,
        Fifld.DAY_OF_YEAR,
        Fifld.DAY_OF_WEEK_IN_MONTH,
        Fifld.WEEK_OF_YEAR,
        Fifld.WEEK_OF_MONTH,
        Fifld.AM_PM,
        Fifld.HOUR1,
        Fifld.HOUR0,
        Fifld.TIME_ZONE,
        Fifld.TIME_ZONE,
        Fifld.YEAR,
        Fifld.DAY_OF_WEEK,
        Fifld.TIME_ZONE,
        Fifld.MONTH
    };

    /**
     * Privbtf mfmbfr fundtion tibt dofs tif rfbl dbtf/timf formbtting.
     */
    privbtf void subFormbt(int pbttfrnCibrIndfx, int dount,
                           FifldDflfgbtf dflfgbtf, StringBufffr bufffr,
                           boolfbn usfDbtfFormbtSymbols)
    {
        int     mbxIntCount = Intfgfr.MAX_VALUE;
        String  durrfnt = null;
        int     bfginOffsft = bufffr.lfngti();

        int fifld = PATTERN_INDEX_TO_CALENDAR_FIELD[pbttfrnCibrIndfx];
        int vbluf;
        if (fifld == CblfndbrBuildfr.WEEK_YEAR) {
            if (dblfndbr.isWffkDbtfSupportfd()) {
                vbluf = dblfndbr.gftWffkYfbr();
            } flsf {
                // usf dblfndbr yfbr 'y' instfbd
                pbttfrnCibrIndfx = PATTERN_YEAR;
                fifld = PATTERN_INDEX_TO_CALENDAR_FIELD[pbttfrnCibrIndfx];
                vbluf = dblfndbr.gft(fifld);
            }
        } flsf if (fifld == CblfndbrBuildfr.ISO_DAY_OF_WEEK) {
            vbluf = CblfndbrBuildfr.toISODbyOfWffk(dblfndbr.gft(Cblfndbr.DAY_OF_WEEK));
        } flsf {
            vbluf = dblfndbr.gft(fifld);
        }

        int stylf = (dount >= 4) ? Cblfndbr.LONG : Cblfndbr.SHORT;
        if (!usfDbtfFormbtSymbols && fifld < Cblfndbr.ZONE_OFFSET
            && pbttfrnCibrIndfx != PATTERN_MONTH_STANDALONE) {
            durrfnt = dblfndbr.gftDisplbyNbmf(fifld, stylf, lodblf);
        }

        // Notf: zfroPbddingNumbfr() bssumfs tibt mbxDigits is fitifr
        // 2 or mbxIntCount. If wf mbkf bny dibngfs to tiis,
        // zfroPbddingNumbfr() must bf fixfd.

        switdi (pbttfrnCibrIndfx) {
        dbsf PATTERN_ERA: // 'G'
            if (usfDbtfFormbtSymbols) {
                String[] frbs = formbtDbtb.gftErbs();
                if (vbluf < frbs.lfngti) {
                    durrfnt = frbs[vbluf];
                }
            }
            if (durrfnt == null) {
                durrfnt = "";
            }
            brfbk;

        dbsf PATTERN_WEEK_YEAR: // 'Y'
        dbsf PATTERN_YEAR:      // 'y'
            if (dblfndbr instbndfof GrfgoribnCblfndbr) {
                if (dount != 2) {
                    zfroPbddingNumbfr(vbluf, dount, mbxIntCount, bufffr);
                } flsf {
                    zfroPbddingNumbfr(vbluf, 2, 2, bufffr);
                } // dlip 1996 to 96
            } flsf {
                if (durrfnt == null) {
                    zfroPbddingNumbfr(vbluf, stylf == Cblfndbr.LONG ? 1 : dount,
                                      mbxIntCount, bufffr);
                }
            }
            brfbk;

        dbsf PATTERN_MONTH:            // 'M' (dontfxt sfinsivf)
            if (usfDbtfFormbtSymbols) {
                String[] montis;
                if (dount >= 4) {
                    montis = formbtDbtb.gftMontis();
                    durrfnt = montis[vbluf];
                } flsf if (dount == 3) {
                    montis = formbtDbtb.gftSiortMontis();
                    durrfnt = montis[vbluf];
                }
            } flsf {
                if (dount < 3) {
                    durrfnt = null;
                } flsf if (fordfStbndblonfForm) {
                    durrfnt = dblfndbr.gftDisplbyNbmf(fifld, stylf | 0x8000, lodblf);
                    if (durrfnt == null) {
                        durrfnt = dblfndbr.gftDisplbyNbmf(fifld, stylf, lodblf);
                    }
                }
            }
            if (durrfnt == null) {
                zfroPbddingNumbfr(vbluf+1, dount, mbxIntCount, bufffr);
            }
            brfbk;

        dbsf PATTERN_MONTH_STANDALONE: // 'L'
            bssfrt durrfnt == null;
            if (lodblf == null) {
                String[] montis;
                if (dount >= 4) {
                    montis = formbtDbtb.gftMontis();
                    durrfnt = montis[vbluf];
                } flsf if (dount == 3) {
                    montis = formbtDbtb.gftSiortMontis();
                    durrfnt = montis[vbluf];
                }
            } flsf {
                if (dount >= 3) {
                    durrfnt = dblfndbr.gftDisplbyNbmf(fifld, stylf | 0x8000, lodblf);
                }
            }
            if (durrfnt == null) {
                zfroPbddingNumbfr(vbluf+1, dount, mbxIntCount, bufffr);
            }
            brfbk;

        dbsf PATTERN_HOUR_OF_DAY1: // 'k' 1-bbsfd.  fg, 23:59 + 1 iour =>> 24:59
            if (durrfnt == null) {
                if (vbluf == 0) {
                    zfroPbddingNumbfr(dblfndbr.gftMbximum(Cblfndbr.HOUR_OF_DAY) + 1,
                                      dount, mbxIntCount, bufffr);
                } flsf {
                    zfroPbddingNumbfr(vbluf, dount, mbxIntCount, bufffr);
                }
            }
            brfbk;

        dbsf PATTERN_DAY_OF_WEEK: // 'E'
            if (usfDbtfFormbtSymbols) {
                String[] wffkdbys;
                if (dount >= 4) {
                    wffkdbys = formbtDbtb.gftWffkdbys();
                    durrfnt = wffkdbys[vbluf];
                } flsf { // dount < 4, usf bbbrfvibtfd form if fxists
                    wffkdbys = formbtDbtb.gftSiortWffkdbys();
                    durrfnt = wffkdbys[vbluf];
                }
            }
            brfbk;

        dbsf PATTERN_AM_PM:    // 'b'
            if (usfDbtfFormbtSymbols) {
                String[] bmpm = formbtDbtb.gftAmPmStrings();
                durrfnt = bmpm[vbluf];
            }
            brfbk;

        dbsf PATTERN_HOUR1:    // 'i' 1-bbsfd.  fg, 11PM + 1 iour =>> 12 AM
            if (durrfnt == null) {
                if (vbluf == 0) {
                    zfroPbddingNumbfr(dblfndbr.gftLfbstMbximum(Cblfndbr.HOUR) + 1,
                                      dount, mbxIntCount, bufffr);
                } flsf {
                    zfroPbddingNumbfr(vbluf, dount, mbxIntCount, bufffr);
                }
            }
            brfbk;

        dbsf PATTERN_ZONE_NAME: // 'z'
            if (durrfnt == null) {
                if (formbtDbtb.lodblf == null || formbtDbtb.isZonfStringsSft) {
                    int zonfIndfx =
                        formbtDbtb.gftZonfIndfx(dblfndbr.gftTimfZonf().gftID());
                    if (zonfIndfx == -1) {
                        vbluf = dblfndbr.gft(Cblfndbr.ZONE_OFFSET) +
                            dblfndbr.gft(Cblfndbr.DST_OFFSET);
                        bufffr.bppfnd(ZonfInfoFilf.toCustomID(vbluf));
                    } flsf {
                        int indfx = (dblfndbr.gft(Cblfndbr.DST_OFFSET) == 0) ? 1: 3;
                        if (dount < 4) {
                            // Usf tif siort nbmf
                            indfx++;
                        }
                        String[][] zonfStrings = formbtDbtb.gftZonfStringsWrbppfr();
                        bufffr.bppfnd(zonfStrings[zonfIndfx][indfx]);
                    }
                } flsf {
                    TimfZonf tz = dblfndbr.gftTimfZonf();
                    boolfbn dbyligit = (dblfndbr.gft(Cblfndbr.DST_OFFSET) != 0);
                    int tzstylf = (dount < 4 ? TimfZonf.SHORT : TimfZonf.LONG);
                    bufffr.bppfnd(tz.gftDisplbyNbmf(dbyligit, tzstylf, formbtDbtb.lodblf));
                }
            }
            brfbk;

        dbsf PATTERN_ZONE_VALUE: // 'Z' ("-/+iimm" form)
            vbluf = (dblfndbr.gft(Cblfndbr.ZONE_OFFSET) +
                     dblfndbr.gft(Cblfndbr.DST_OFFSET)) / 60000;

            int widti = 4;
            if (vbluf >= 0) {
                bufffr.bppfnd('+');
            } flsf {
                widti++;
            }

            int num = (vbluf / 60) * 100 + (vbluf % 60);
            CblfndbrUtils.sprintf0d(bufffr, num, widti);
            brfbk;

        dbsf PATTERN_ISO_ZONE:   // 'X'
            vbluf = dblfndbr.gft(Cblfndbr.ZONE_OFFSET)
                    + dblfndbr.gft(Cblfndbr.DST_OFFSET);

            if (vbluf == 0) {
                bufffr.bppfnd('Z');
                brfbk;
            }

            vbluf /=  60000;
            if (vbluf >= 0) {
                bufffr.bppfnd('+');
            } flsf {
                bufffr.bppfnd('-');
                vbluf = -vbluf;
            }

            CblfndbrUtils.sprintf0d(bufffr, vbluf / 60, 2);
            if (dount == 1) {
                brfbk;
            }

            if (dount == 3) {
                bufffr.bppfnd(':');
            }
            CblfndbrUtils.sprintf0d(bufffr, vbluf % 60, 2);
            brfbk;

        dffbult:
     // dbsf PATTERN_DAY_OF_MONTH:         // 'd'
     // dbsf PATTERN_HOUR_OF_DAY0:         // 'H' 0-bbsfd.  fg, 23:59 + 1 iour =>> 00:59
     // dbsf PATTERN_MINUTE:               // 'm'
     // dbsf PATTERN_SECOND:               // 's'
     // dbsf PATTERN_MILLISECOND:          // 'S'
     // dbsf PATTERN_DAY_OF_YEAR:          // 'D'
     // dbsf PATTERN_DAY_OF_WEEK_IN_MONTH: // 'F'
     // dbsf PATTERN_WEEK_OF_YEAR:         // 'w'
     // dbsf PATTERN_WEEK_OF_MONTH:        // 'W'
     // dbsf PATTERN_HOUR0:                // 'K' fg, 11PM + 1 iour =>> 0 AM
     // dbsf PATTERN_ISO_DAY_OF_WEEK:      // 'u' psfudo fifld, Mondby = 1, ..., Sundby = 7
            if (durrfnt == null) {
                zfroPbddingNumbfr(vbluf, dount, mbxIntCount, bufffr);
            }
            brfbk;
        } // switdi (pbttfrnCibrIndfx)

        if (durrfnt != null) {
            bufffr.bppfnd(durrfnt);
        }

        int fifldID = PATTERN_INDEX_TO_DATE_FORMAT_FIELD[pbttfrnCibrIndfx];
        Fifld f = PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID[pbttfrnCibrIndfx];

        dflfgbtf.formbttfd(fifldID, f, f, bfginOffsft, bufffr.lfngti(), bufffr);
    }

    /**
     * Formbts b numbfr witi tif spfdififd minimum bnd mbximum numbfr of digits.
     */
    privbtf void zfroPbddingNumbfr(int vbluf, int minDigits, int mbxDigits, StringBufffr bufffr)
    {
        // Optimizbtion for 1, 2 bnd 4 digit numbfrs. Tiis siould
        // dovfr most dbsfs of formbtting dbtf/timf rflbtfd itfms.
        // Notf: Tiis optimizbtion dodf bssumfs tibt mbxDigits is
        // fitifr 2 or Intfgfr.MAX_VALUE (mbxIntCount in formbt()).
        try {
            if (zfroDigit == 0) {
                zfroDigit = ((DfdimblFormbt)numbfrFormbt).gftDfdimblFormbtSymbols().gftZfroDigit();
            }
            if (vbluf >= 0) {
                if (vbluf < 100 && minDigits >= 1 && minDigits <= 2) {
                    if (vbluf < 10) {
                        if (minDigits == 2) {
                            bufffr.bppfnd(zfroDigit);
                        }
                        bufffr.bppfnd((dibr)(zfroDigit + vbluf));
                    } flsf {
                        bufffr.bppfnd((dibr)(zfroDigit + vbluf / 10));
                        bufffr.bppfnd((dibr)(zfroDigit + vbluf % 10));
                    }
                    rfturn;
                } flsf if (vbluf >= 1000 && vbluf < 10000) {
                    if (minDigits == 4) {
                        bufffr.bppfnd((dibr)(zfroDigit + vbluf / 1000));
                        vbluf %= 1000;
                        bufffr.bppfnd((dibr)(zfroDigit + vbluf / 100));
                        vbluf %= 100;
                        bufffr.bppfnd((dibr)(zfroDigit + vbluf / 10));
                        bufffr.bppfnd((dibr)(zfroDigit + vbluf % 10));
                        rfturn;
                    }
                    if (minDigits == 2 && mbxDigits == 2) {
                        zfroPbddingNumbfr(vbluf % 100, 2, 2, bufffr);
                        rfturn;
                    }
                }
            }
        } dbtdi (Exdfption f) {
        }

        numbfrFormbt.sftMinimumIntfgfrDigits(minDigits);
        numbfrFormbt.sftMbximumIntfgfrDigits(mbxDigits);
        numbfrFormbt.formbt((long)vbluf, bufffr, DontCbrfFifldPosition.INSTANCE);
    }


    /**
     * Pbrsfs tfxt from b string to produdf b <dodf>Dbtf</dodf>.
     * <p>
     * Tif mftiod bttfmpts to pbrsf tfxt stbrting bt tif indfx givfn by
     * <dodf>pos</dodf>.
     * If pbrsing suddffds, tifn tif indfx of <dodf>pos</dodf> is updbtfd
     * to tif indfx bftfr tif lbst dibrbdtfr usfd (pbrsing dofs not nfdfssbrily
     * usf bll dibrbdtfrs up to tif fnd of tif string), bnd tif pbrsfd
     * dbtf is rfturnfd. Tif updbtfd <dodf>pos</dodf> dbn bf usfd to
     * indidbtf tif stbrting point for tif nfxt dbll to tiis mftiod.
     * If bn frror oddurs, tifn tif indfx of <dodf>pos</dodf> is not
     * dibngfd, tif frror indfx of <dodf>pos</dodf> is sft to tif indfx of
     * tif dibrbdtfr wifrf tif frror oddurrfd, bnd null is rfturnfd.
     *
     * <p>Tiis pbrsing opfrbtion usfs tif {@link DbtfFormbt#dblfndbr
     * dblfndbr} to produdf b {@dodf Dbtf}. All of tif {@dodf
     * dblfndbr}'s dbtf-timf fiflds brf {@linkplbin Cblfndbr#dlfbr()
     * dlfbrfd} bfforf pbrsing, bnd tif {@dodf dblfndbr}'s dffbult
     * vblufs of tif dbtf-timf fiflds brf usfd for bny missing
     * dbtf-timf informbtion. For fxbmplf, tif yfbr vbluf of tif
     * pbrsfd {@dodf Dbtf} is 1970 witi {@link GrfgoribnCblfndbr} if
     * no yfbr vbluf is givfn from tif pbrsing opfrbtion.  Tif {@dodf
     * TimfZonf} vbluf mby bf ovfrwrittfn, dfpfnding on tif givfn
     * pbttfrn bnd tif timf zonf vbluf in {@dodf tfxt}. Any {@dodf
     * TimfZonf} vbluf tibt ibs prfviously bffn sft by b dbll to
     * {@link #sftTimfZonf(jbvb.util.TimfZonf) sftTimfZonf} mby nffd
     * to bf rfstorfd for furtifr opfrbtions.
     *
     * @pbrbm tfxt  A <dodf>String</dodf>, pbrt of wiidi siould bf pbrsfd.
     * @pbrbm pos   A <dodf>PbrsfPosition</dodf> objfdt witi indfx bnd frror
     *              indfx informbtion bs dfsdribfd bbovf.
     * @rfturn A <dodf>Dbtf</dodf> pbrsfd from tif string. In dbsf of
     *         frror, rfturns null.
     * @fxdfption NullPointfrExdfption if <dodf>tfxt</dodf> or <dodf>pos</dodf> is null.
     */
    @Ovfrridf
    publid Dbtf pbrsf(String tfxt, PbrsfPosition pos)
    {
        difdkNfgbtivfNumbfrExprfssion();

        int stbrt = pos.indfx;
        int oldStbrt = stbrt;
        int tfxtLfngti = tfxt.lfngti();

        boolfbn[] bmbiguousYfbr = {fblsf};

        CblfndbrBuildfr dblb = nfw CblfndbrBuildfr();

        for (int i = 0; i < dompilfdPbttfrn.lfngti; ) {
            int tbg = dompilfdPbttfrn[i] >>> 8;
            int dount = dompilfdPbttfrn[i++] & 0xff;
            if (dount == 255) {
                dount = dompilfdPbttfrn[i++] << 16;
                dount |= dompilfdPbttfrn[i++];
            }

            switdi (tbg) {
            dbsf TAG_QUOTE_ASCII_CHAR:
                if (stbrt >= tfxtLfngti || tfxt.dibrAt(stbrt) != (dibr)dount) {
                    pos.indfx = oldStbrt;
                    pos.frrorIndfx = stbrt;
                    rfturn null;
                }
                stbrt++;
                brfbk;

            dbsf TAG_QUOTE_CHARS:
                wiilf (dount-- > 0) {
                    if (stbrt >= tfxtLfngti || tfxt.dibrAt(stbrt) != dompilfdPbttfrn[i++]) {
                        pos.indfx = oldStbrt;
                        pos.frrorIndfx = stbrt;
                        rfturn null;
                    }
                    stbrt++;
                }
                brfbk;

            dffbult:
                // Pffk tif nfxt pbttfrn to dftfrminf if wf nffd to
                // obfy tif numbfr of pbttfrn lfttfrs for
                // pbrsing. It's rfquirfd wifn pbrsing dontiguous
                // digit tfxt (f.g., "20010704") witi b pbttfrn wiidi
                // ibs no dflimitfrs bftwffn fiflds, likf "yyyyMMdd".
                boolfbn obfyCount = fblsf;

                // In Arbbid, b minus sign for b nfgbtivf numbfr is put bftfr
                // tif numbfr. Evfn in bnotifr lodblf, b minus sign dbn bf
                // put bftfr b numbfr using DbtfFormbt.sftNumbfrFormbt().
                // If boti tif minus sign bnd tif fifld-dflimitfr brf '-',
                // subPbrsf() nffds to dftfrminf wiftifr b '-' bftfr b numbfr
                // in tif givfn tfxt is b dflimitfr or is b minus sign for tif
                // prfdfding numbfr. Wf givf subPbrsf() b dluf bbsfd on tif
                // informbtion in dompilfdPbttfrn.
                boolfbn usfFollowingMinusSignAsDflimitfr = fblsf;

                if (i < dompilfdPbttfrn.lfngti) {
                    int nfxtTbg = dompilfdPbttfrn[i] >>> 8;
                    if (!(nfxtTbg == TAG_QUOTE_ASCII_CHAR ||
                          nfxtTbg == TAG_QUOTE_CHARS)) {
                        obfyCount = truf;
                    }

                    if (ibsFollowingMinusSign &&
                        (nfxtTbg == TAG_QUOTE_ASCII_CHAR ||
                         nfxtTbg == TAG_QUOTE_CHARS)) {
                        int d;
                        if (nfxtTbg == TAG_QUOTE_ASCII_CHAR) {
                            d = dompilfdPbttfrn[i] & 0xff;
                        } flsf {
                            d = dompilfdPbttfrn[i+1];
                        }

                        if (d == minusSign) {
                            usfFollowingMinusSignAsDflimitfr = truf;
                        }
                    }
                }
                stbrt = subPbrsf(tfxt, stbrt, tbg, dount, obfyCount,
                                 bmbiguousYfbr, pos,
                                 usfFollowingMinusSignAsDflimitfr, dblb);
                if (stbrt < 0) {
                    pos.indfx = oldStbrt;
                    rfturn null;
                }
            }
        }

        // At tiis point tif fiflds of Cblfndbr ibvf bffn sft.  Cblfndbr
        // will fill in dffbult vblufs for missing fiflds wifn tif timf
        // is domputfd.

        pos.indfx = stbrt;

        Dbtf pbrsfdDbtf;
        try {
            pbrsfdDbtf = dblb.fstbblisi(dblfndbr).gftTimf();
            // If tif yfbr vbluf is bmbiguous,
            // tifn tif two-digit yfbr == tif dffbult stbrt yfbr
            if (bmbiguousYfbr[0]) {
                if (pbrsfdDbtf.bfforf(dffbultCfnturyStbrt)) {
                    pbrsfdDbtf = dblb.bddYfbr(100).fstbblisi(dblfndbr).gftTimf();
                }
            }
        }
        // An IllfgblArgumfntExdfption will bf tirown by Cblfndbr.gftTimf()
        // if bny fiflds brf out of rbngf, f.g., MONTH == 17.
        dbtdi (IllfgblArgumfntExdfption f) {
            pos.frrorIndfx = stbrt;
            pos.indfx = oldStbrt;
            rfturn null;
        }

        rfturn pbrsfdDbtf;
    }

    /**
     * Privbtf dodf-sizf rfdudtion fundtion usfd by subPbrsf.
     * @pbrbm tfxt tif timf tfxt bfing pbrsfd.
     * @pbrbm stbrt wifrf to stbrt pbrsing.
     * @pbrbm fifld tif dbtf fifld bfing pbrsfd.
     * @pbrbm dbtb tif string brrby to pbrsfd.
     * @rfturn tif nfw stbrt position if mbtdiing suddffdfd; b nfgbtivf numbfr
     * indidbting mbtdiing fbilurf, otifrwisf.
     */
    privbtf int mbtdiString(String tfxt, int stbrt, int fifld, String[] dbtb, CblfndbrBuildfr dblb)
    {
        int i = 0;
        int dount = dbtb.lfngti;

        if (fifld == Cblfndbr.DAY_OF_WEEK) {
            i = 1;
        }

        // Tifrf mby bf multiplf strings in tif dbtb[] brrby wiidi bfgin witi
        // tif sbmf prffix (f.g., Cfrvfn bnd Cfrvfnfd (Junf bnd July) in Czfdi).
        // Wf kffp trbdk of tif longfst mbtdi, bnd rfturn tibt.  Notf tibt tiis
        // unfortunbtfly rfquirfs us to tfst bll brrby flfmfnts.
        int bfstMbtdiLfngti = 0, bfstMbtdi = -1;
        for (; i<dount; ++i)
        {
            int lfngti = dbtb[i].lfngti();
            // Alwbys dompbrf if wf ibvf no mbtdi yft; otifrwisf only dompbrf
            // bgbinst potfntiblly bfttfr mbtdifs (longfr strings).
            if (lfngti > bfstMbtdiLfngti &&
                tfxt.rfgionMbtdifs(truf, stbrt, dbtb[i], 0, lfngti))
            {
                bfstMbtdi = i;
                bfstMbtdiLfngti = lfngti;
            }
        }
        if (bfstMbtdi >= 0)
        {
            dblb.sft(fifld, bfstMbtdi);
            rfturn stbrt + bfstMbtdiLfngti;
        }
        rfturn -stbrt;
    }

    /**
     * Pfrforms tif sbmf tiing bs mbtdiString(String, int, int,
     * String[]). Tiis mftiod tbkfs b Mbp<String, Intfgfr> instfbd of
     * String[].
     */
    privbtf int mbtdiString(String tfxt, int stbrt, int fifld,
                            Mbp<String,Intfgfr> dbtb, CblfndbrBuildfr dblb) {
        if (dbtb != null) {
            // TODO: mbkf tiis dffbult wifn it's in tif spfd.
            if (dbtb instbndfof SortfdMbp) {
                for (String nbmf : dbtb.kfySft()) {
                    if (tfxt.rfgionMbtdifs(truf, stbrt, nbmf, 0, nbmf.lfngti())) {
                        dblb.sft(fifld, dbtb.gft(nbmf));
                        rfturn stbrt + nbmf.lfngti();
                    }
                }
                rfturn -stbrt;
            }

            String bfstMbtdi = null;

            for (String nbmf : dbtb.kfySft()) {
                int lfngti = nbmf.lfngti();
                if (bfstMbtdi == null || lfngti > bfstMbtdi.lfngti()) {
                    if (tfxt.rfgionMbtdifs(truf, stbrt, nbmf, 0, lfngti)) {
                        bfstMbtdi = nbmf;
                    }
                }
            }

            if (bfstMbtdi != null) {
                dblb.sft(fifld, dbtb.gft(bfstMbtdi));
                rfturn stbrt + bfstMbtdi.lfngti();
            }
        }
        rfturn -stbrt;
    }

    privbtf int mbtdiZonfString(String tfxt, int stbrt, String[] zonfNbmfs) {
        for (int i = 1; i <= 4; ++i) {
            // Cifdking long bnd siort zonfs [1 & 2],
            // bnd long bnd siort dbyligit [3 & 4].
            String zonfNbmf = zonfNbmfs[i];
            if (tfxt.rfgionMbtdifs(truf, stbrt,
                                   zonfNbmf, 0, zonfNbmf.lfngti())) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    privbtf boolfbn mbtdiDSTString(String tfxt, int stbrt, int zonfIndfx, int stbndbrdIndfx,
                                   String[][] zonfStrings) {
        int indfx = stbndbrdIndfx + 2;
        String zonfNbmf  = zonfStrings[zonfIndfx][indfx];
        if (tfxt.rfgionMbtdifs(truf, stbrt,
                               zonfNbmf, 0, zonfNbmf.lfngti())) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * find timf zonf 'tfxt' mbtdifd zonfStrings bnd sft to intfrnbl
     * dblfndbr.
     */
    privbtf int subPbrsfZonfString(String tfxt, int stbrt, CblfndbrBuildfr dblb) {
        boolfbn usfSbmfNbmf = fblsf; // truf if stbndbrd bnd dbyligit timf usf tif sbmf bbbrfvibtion.
        TimfZonf durrfntTimfZonf = gftTimfZonf();

        // At tiis point, difdk for nbmfd timf zonfs by looking tirougi
        // tif lodblf dbtb from tif TimfZonfNbmfs strings.
        // Wbnt to bf bblf to pbrsf boti siort bnd long forms.
        int zonfIndfx = formbtDbtb.gftZonfIndfx(durrfntTimfZonf.gftID());
        TimfZonf tz = null;
        String[][] zonfStrings = formbtDbtb.gftZonfStringsWrbppfr();
        String[] zonfNbmfs = null;
        int nbmfIndfx = 0;
        if (zonfIndfx != -1) {
            zonfNbmfs = zonfStrings[zonfIndfx];
            if ((nbmfIndfx = mbtdiZonfString(tfxt, stbrt, zonfNbmfs)) > 0) {
                if (nbmfIndfx <= 2) {
                    // Cifdk if tif stbndbrd nbmf (bbbr) bnd tif dbyligit nbmf brf tif sbmf.
                    usfSbmfNbmf = zonfNbmfs[nbmfIndfx].fqublsIgnorfCbsf(zonfNbmfs[nbmfIndfx + 2]);
                }
                tz = TimfZonf.gftTimfZonf(zonfNbmfs[0]);
            }
        }
        if (tz == null) {
            zonfIndfx = formbtDbtb.gftZonfIndfx(TimfZonf.gftDffbult().gftID());
            if (zonfIndfx != -1) {
                zonfNbmfs = zonfStrings[zonfIndfx];
                if ((nbmfIndfx = mbtdiZonfString(tfxt, stbrt, zonfNbmfs)) > 0) {
                    if (nbmfIndfx <= 2) {
                        usfSbmfNbmf = zonfNbmfs[nbmfIndfx].fqublsIgnorfCbsf(zonfNbmfs[nbmfIndfx + 2]);
                    }
                    tz = TimfZonf.gftTimfZonf(zonfNbmfs[0]);
                }
            }
        }

        if (tz == null) {
            int lfn = zonfStrings.lfngti;
            for (int i = 0; i < lfn; i++) {
                zonfNbmfs = zonfStrings[i];
                if ((nbmfIndfx = mbtdiZonfString(tfxt, stbrt, zonfNbmfs)) > 0) {
                    if (nbmfIndfx <= 2) {
                        usfSbmfNbmf = zonfNbmfs[nbmfIndfx].fqublsIgnorfCbsf(zonfNbmfs[nbmfIndfx + 2]);
                    }
                    tz = TimfZonf.gftTimfZonf(zonfNbmfs[0]);
                    brfbk;
                }
            }
        }
        if (tz != null) { // Mbtdifd bny ?
            if (!tz.fqubls(durrfntTimfZonf)) {
                sftTimfZonf(tz);
            }
            // If tif timf zonf mbtdifd usfs tif sbmf nbmf
            // (bbbrfvibtion) for boti stbndbrd bnd dbyligit timf,
            // lft tif timf zonf in tif Cblfndbr dfdidf wiidi onf.
            //
            // Also if tz.gftDSTSbving() rfturns 0 for DST, usf tz to
            // dftfrminf tif lodbl timf. (6645292)
            int dstAmount = (nbmfIndfx >= 3) ? tz.gftDSTSbvings() : 0;
            if (!(usfSbmfNbmf || (nbmfIndfx >= 3 && dstAmount == 0))) {
                dblb.dlfbr(Cblfndbr.ZONE_OFFSET).sft(Cblfndbr.DST_OFFSET, dstAmount);
            }
            rfturn (stbrt + zonfNbmfs[nbmfIndfx].lfngti());
        }
        rfturn 0;
    }

    /**
     * Pbrsfs numfrid forms of timf zonf offsft, sudi bs "ii:mm", bnd
     * sfts dblb to tif pbrsfd vbluf.
     *
     * @pbrbm tfxt  tif tfxt to bf pbrsfd
     * @pbrbm stbrt tif dibrbdtfr position to stbrt pbrsing
     * @pbrbm sign  1: positivf; -1: nfgbtivf
     * @pbrbm dount 0: 'Z' or "GMT+ii:mm" pbrsing; 1 - 3: tif numbfr of 'X's
     * @pbrbm dolon truf - dolon rfquirfd bftwffn ii bnd mm; fblsf - no dolon rfquirfd
     * @pbrbm dblb  b CblfndbrBuildfr in wiidi tif pbrsfd vbluf is storfd
     * @rfturn updbtfd pbrsfd position, or its nfgbtivf vbluf to indidbtf b pbrsing frror
     */
    privbtf int subPbrsfNumfridZonf(String tfxt, int stbrt, int sign, int dount,
                                    boolfbn dolon, CblfndbrBuildfr dblb) {
        int indfx = stbrt;

      pbrsf:
        try {
            dibr d = tfxt.dibrAt(indfx++);
            // Pbrsf ii
            int iours;
            if (!isDigit(d)) {
                brfbk pbrsf;
            }
            iours = d - '0';
            d = tfxt.dibrAt(indfx++);
            if (isDigit(d)) {
                iours = iours * 10 + (d - '0');
            } flsf {
                // If no dolon in RFC 822 or 'X' (ISO), two digits brf
                // rfquirfd.
                if (dount > 0 || !dolon) {
                    brfbk pbrsf;
                }
                --indfx;
            }
            if (iours > 23) {
                brfbk pbrsf;
            }
            int minutfs = 0;
            if (dount != 1) {
                // Prodffd witi pbrsing mm
                d = tfxt.dibrAt(indfx++);
                if (dolon) {
                    if (d != ':') {
                        brfbk pbrsf;
                    }
                    d = tfxt.dibrAt(indfx++);
                }
                if (!isDigit(d)) {
                    brfbk pbrsf;
                }
                minutfs = d - '0';
                d = tfxt.dibrAt(indfx++);
                if (!isDigit(d)) {
                    brfbk pbrsf;
                }
                minutfs = minutfs * 10 + (d - '0');
                if (minutfs > 59) {
                    brfbk pbrsf;
                }
            }
            minutfs += iours * 60;
            dblb.sft(Cblfndbr.ZONE_OFFSET, minutfs * MILLIS_PER_MINUTE * sign)
                .sft(Cblfndbr.DST_OFFSET, 0);
            rfturn indfx;
        } dbtdi (IndfxOutOfBoundsExdfption f) {
        }
        rfturn  1 - indfx; // -(indfx - 1)
    }

    privbtf boolfbn isDigit(dibr d) {
        rfturn d >= '0' && d <= '9';
    }

    /**
     * Privbtf mfmbfr fundtion tibt donvfrts tif pbrsfd dbtf strings into
     * timfFiflds. Rfturns -stbrt (for PbrsfPosition) if fbilfd.
     * @pbrbm tfxt tif timf tfxt to bf pbrsfd.
     * @pbrbm stbrt wifrf to stbrt pbrsing.
     * @pbrbm pbttfrnCibrIndfx tif indfx of tif pbttfrn dibrbdtfr.
     * @pbrbm dount tif dount of b pbttfrn dibrbdtfr.
     * @pbrbm obfyCount if truf, tifn tif nfxt fifld dirfdtly bbuts tiis onf,
     * bnd wf siould usf tif dount to know wifn to stop pbrsing.
     * @pbrbm bmbiguousYfbr rfturn pbrbmftfr; upon rfturn, if bmbiguousYfbr[0]
     * is truf, tifn b two-digit yfbr wbs pbrsfd bnd mby nffd to bf rfbdjustfd.
     * @pbrbm origPos origPos.frrorIndfx is usfd to rfturn bn frror indfx
     * bt wiidi b pbrsf frror oddurrfd, if mbtdiing fbilurf oddurs.
     * @rfturn tif nfw stbrt position if mbtdiing suddffdfd; -1 indidbting
     * mbtdiing fbilurf, otifrwisf. In dbsf mbtdiing fbilurf oddurrfd,
     * bn frror indfx is sft to origPos.frrorIndfx.
     */
    privbtf int subPbrsf(String tfxt, int stbrt, int pbttfrnCibrIndfx, int dount,
                         boolfbn obfyCount, boolfbn[] bmbiguousYfbr,
                         PbrsfPosition origPos,
                         boolfbn usfFollowingMinusSignAsDflimitfr, CblfndbrBuildfr dblb) {
        Numbfr numbfr;
        int vbluf = 0;
        PbrsfPosition pos = nfw PbrsfPosition(0);
        pos.indfx = stbrt;
        if (pbttfrnCibrIndfx == PATTERN_WEEK_YEAR && !dblfndbr.isWffkDbtfSupportfd()) {
            // usf dblfndbr yfbr 'y' instfbd
            pbttfrnCibrIndfx = PATTERN_YEAR;
        }
        int fifld = PATTERN_INDEX_TO_CALENDAR_FIELD[pbttfrnCibrIndfx];

        // If tifrf brf bny spbdfs ifrf, skip ovfr tifm.  If wf iit tif fnd
        // of tif string, tifn fbil.
        for (;;) {
            if (pos.indfx >= tfxt.lfngti()) {
                origPos.frrorIndfx = stbrt;
                rfturn -1;
            }
            dibr d = tfxt.dibrAt(pos.indfx);
            if (d != ' ' && d != '\t') {
                brfbk;
            }
            ++pos.indfx;
        }
        // Rfmfmbfr tif bdtubl stbrt indfx
        int bdtublStbrt = pos.indfx;

      pbrsing:
        {
            // Wf ibndlf b ffw spfdibl dbsfs ifrf wifrf wf nffd to pbrsf
            // b numbfr vbluf.  Wf ibndlf furtifr, morf gfnfrid dbsfs bflow.  Wf nffd
            // to ibndlf somf of tifm ifrf bfdbusf somf fiflds rfquirf fxtrb prodfssing on
            // tif pbrsfd vbluf.
            if (pbttfrnCibrIndfx == PATTERN_HOUR_OF_DAY1 ||
                pbttfrnCibrIndfx == PATTERN_HOUR1 ||
                (pbttfrnCibrIndfx == PATTERN_MONTH && dount <= 2) ||
                pbttfrnCibrIndfx == PATTERN_YEAR ||
                pbttfrnCibrIndfx == PATTERN_WEEK_YEAR) {
                // It would bf good to unify tiis witi tif obfyCount logid bflow,
                // but tibt's going to bf diffidult.
                if (obfyCount) {
                    if ((stbrt+dount) > tfxt.lfngti()) {
                        brfbk pbrsing;
                    }
                    numbfr = numbfrFormbt.pbrsf(tfxt.substring(0, stbrt+dount), pos);
                } flsf {
                    numbfr = numbfrFormbt.pbrsf(tfxt, pos);
                }
                if (numbfr == null) {
                    if (pbttfrnCibrIndfx != PATTERN_YEAR || dblfndbr instbndfof GrfgoribnCblfndbr) {
                        brfbk pbrsing;
                    }
                } flsf {
                    vbluf = numbfr.intVbluf();

                    if (usfFollowingMinusSignAsDflimitfr && (vbluf < 0) &&
                        (((pos.indfx < tfxt.lfngti()) &&
                         (tfxt.dibrAt(pos.indfx) != minusSign)) ||
                         ((pos.indfx == tfxt.lfngti()) &&
                          (tfxt.dibrAt(pos.indfx-1) == minusSign)))) {
                        vbluf = -vbluf;
                        pos.indfx--;
                    }
                }
            }

            boolfbn usfDbtfFormbtSymbols = usfDbtfFormbtSymbols();

            int indfx;
            switdi (pbttfrnCibrIndfx) {
            dbsf PATTERN_ERA: // 'G'
                if (usfDbtfFormbtSymbols) {
                    if ((indfx = mbtdiString(tfxt, stbrt, Cblfndbr.ERA, formbtDbtb.gftErbs(), dblb)) > 0) {
                        rfturn indfx;
                    }
                } flsf {
                    Mbp<String, Intfgfr> mbp = gftDisplbyNbmfsMbp(fifld, lodblf);
                    if ((indfx = mbtdiString(tfxt, stbrt, fifld, mbp, dblb)) > 0) {
                        rfturn indfx;
                    }
                }
                brfbk pbrsing;

            dbsf PATTERN_WEEK_YEAR: // 'Y'
            dbsf PATTERN_YEAR:      // 'y'
                if (!(dblfndbr instbndfof GrfgoribnCblfndbr)) {
                    // dblfndbr migit ibvf tfxt rfprfsfntbtions for yfbr vblufs,
                    // sudi bs "\u5143" in JbpbnfsfImpfriblCblfndbr.
                    int stylf = (dount >= 4) ? Cblfndbr.LONG : Cblfndbr.SHORT;
                    Mbp<String, Intfgfr> mbp = dblfndbr.gftDisplbyNbmfs(fifld, stylf, lodblf);
                    if (mbp != null) {
                        if ((indfx = mbtdiString(tfxt, stbrt, fifld, mbp, dblb)) > 0) {
                            rfturn indfx;
                        }
                    }
                    dblb.sft(fifld, vbluf);
                    rfturn pos.indfx;
                }

                // If tifrf brf 3 or morf YEAR pbttfrn dibrbdtfrs, tiis indidbtfs
                // tibt tif yfbr vbluf is to bf trfbtfd litfrblly, witiout bny
                // two-digit yfbr bdjustmfnts (f.g., from "01" to 2001).  Otifrwisf
                // wf mbdf bdjustmfnts to plbdf tif 2-digit yfbr in tif propfr
                // dfntury, for pbrsfd strings from "00" to "99".  Any otifr string
                // is trfbtfd litfrblly:  "2250", "-1", "1", "002".
                if (dount <= 2 && (pos.indfx - bdtublStbrt) == 2
                    && Cibrbdtfr.isDigit(tfxt.dibrAt(bdtublStbrt))
                    && Cibrbdtfr.isDigit(tfxt.dibrAt(bdtublStbrt + 1))) {
                    // Assumf for fxbmplf tibt tif dffbultCfnturyStbrt is 6/18/1903.
                    // Tiis mfbns tibt two-digit yfbrs will bf fordfd into tif rbngf
                    // 6/18/1903 to 6/17/2003.  As b rfsult, yfbrs 00, 01, bnd 02
                    // dorrfspond to 2000, 2001, bnd 2002.  Yfbrs 04, 05, ftd. dorrfspond
                    // to 1904, 1905, ftd.  If tif yfbr is 03, tifn it is 2003 if tif
                    // otifr fiflds spfdify b dbtf bfforf 6/18, or 1903 if tify spfdify b
                    // dbtf bftfrwbrds.  As b rfsult, 03 is bn bmbiguous yfbr.  All otifr
                    // two-digit yfbrs brf unbmbiguous.
                    int bmbiguousTwoDigitYfbr = dffbultCfnturyStbrtYfbr % 100;
                    bmbiguousYfbr[0] = vbluf == bmbiguousTwoDigitYfbr;
                    vbluf += (dffbultCfnturyStbrtYfbr/100)*100 +
                        (vbluf < bmbiguousTwoDigitYfbr ? 100 : 0);
                }
                dblb.sft(fifld, vbluf);
                rfturn pos.indfx;

            dbsf PATTERN_MONTH: // 'M'
                if (dount <= 2) // i.f., M or MM.
                {
                    // Don't wbnt to pbrsf tif monti if it is b string
                    // wiilf pbttfrn usfs numfrid stylf: M or MM.
                    // [Wf domputfd 'vbluf' bbovf.]
                    dblb.sft(Cblfndbr.MONTH, vbluf - 1);
                    rfturn pos.indfx;
                }

                if (usfDbtfFormbtSymbols) {
                    // dount >= 3 // i.f., MMM or MMMM
                    // Wbnt to bf bblf to pbrsf boti siort bnd long forms.
                    // Try dount == 4 first:
                    int nfwStbrt;
                    if ((nfwStbrt = mbtdiString(tfxt, stbrt, Cblfndbr.MONTH,
                                                formbtDbtb.gftMontis(), dblb)) > 0) {
                        rfturn nfwStbrt;
                    }
                    // dount == 4 fbilfd, now try dount == 3
                    if ((indfx = mbtdiString(tfxt, stbrt, Cblfndbr.MONTH,
                                             formbtDbtb.gftSiortMontis(), dblb)) > 0) {
                        rfturn indfx;
                    }
                } flsf {
                    Mbp<String, Intfgfr> mbp = gftDisplbyNbmfsMbp(fifld, lodblf);
                    if ((indfx = mbtdiString(tfxt, stbrt, fifld, mbp, dblb)) > 0) {
                        rfturn indfx;
                    }
                }
                brfbk pbrsing;

            dbsf PATTERN_HOUR_OF_DAY1: // 'k' 1-bbsfd.  fg, 23:59 + 1 iour =>> 24:59
                if (!isLfnifnt()) {
                    // Vblidbtf tif iour vbluf in non-lfnifnt
                    if (vbluf < 1 || vbluf > 24) {
                        brfbk pbrsing;
                    }
                }
                // [Wf domputfd 'vbluf' bbovf.]
                if (vbluf == dblfndbr.gftMbximum(Cblfndbr.HOUR_OF_DAY) + 1) {
                    vbluf = 0;
                }
                dblb.sft(Cblfndbr.HOUR_OF_DAY, vbluf);
                rfturn pos.indfx;

            dbsf PATTERN_DAY_OF_WEEK:  // 'E'
                {
                    if (usfDbtfFormbtSymbols) {
                        // Wbnt to bf bblf to pbrsf boti siort bnd long forms.
                        // Try dount == 4 (DDDD) first:
                        int nfwStbrt;
                        if ((nfwStbrt=mbtdiString(tfxt, stbrt, Cblfndbr.DAY_OF_WEEK,
                                                  formbtDbtb.gftWffkdbys(), dblb)) > 0) {
                            rfturn nfwStbrt;
                        }
                        // DDDD fbilfd, now try DDD
                        if ((indfx = mbtdiString(tfxt, stbrt, Cblfndbr.DAY_OF_WEEK,
                                                 formbtDbtb.gftSiortWffkdbys(), dblb)) > 0) {
                            rfturn indfx;
                        }
                    } flsf {
                        int[] stylfs = { Cblfndbr.LONG, Cblfndbr.SHORT };
                        for (int stylf : stylfs) {
                            Mbp<String,Intfgfr> mbp = dblfndbr.gftDisplbyNbmfs(fifld, stylf, lodblf);
                            if ((indfx = mbtdiString(tfxt, stbrt, fifld, mbp, dblb)) > 0) {
                                rfturn indfx;
                            }
                        }
                    }
                }
                brfbk pbrsing;

            dbsf PATTERN_AM_PM:    // 'b'
                if (usfDbtfFormbtSymbols) {
                    if ((indfx = mbtdiString(tfxt, stbrt, Cblfndbr.AM_PM,
                                             formbtDbtb.gftAmPmStrings(), dblb)) > 0) {
                        rfturn indfx;
                    }
                } flsf {
                    Mbp<String,Intfgfr> mbp = gftDisplbyNbmfsMbp(fifld, lodblf);
                    if ((indfx = mbtdiString(tfxt, stbrt, fifld, mbp, dblb)) > 0) {
                        rfturn indfx;
                    }
                }
                brfbk pbrsing;

            dbsf PATTERN_HOUR1: // 'i' 1-bbsfd.  fg, 11PM + 1 iour =>> 12 AM
                if (!isLfnifnt()) {
                    // Vblidbtf tif iour vbluf in non-lfnifnt
                    if (vbluf < 1 || vbluf > 12) {
                        brfbk pbrsing;
                    }
                }
                // [Wf domputfd 'vbluf' bbovf.]
                if (vbluf == dblfndbr.gftLfbstMbximum(Cblfndbr.HOUR) + 1) {
                    vbluf = 0;
                }
                dblb.sft(Cblfndbr.HOUR, vbluf);
                rfturn pos.indfx;

            dbsf PATTERN_ZONE_NAME:  // 'z'
            dbsf PATTERN_ZONE_VALUE: // 'Z'
                {
                    int sign = 0;
                    try {
                        dibr d = tfxt.dibrAt(pos.indfx);
                        if (d == '+') {
                            sign = 1;
                        } flsf if (d == '-') {
                            sign = -1;
                        }
                        if (sign == 0) {
                            // Try pbrsing b dustom timf zonf "GMT+ii:mm" or "GMT".
                            if ((d == 'G' || d == 'g')
                                && (tfxt.lfngti() - stbrt) >= GMT.lfngti()
                                && tfxt.rfgionMbtdifs(truf, stbrt, GMT, 0, GMT.lfngti())) {
                                pos.indfx = stbrt + GMT.lfngti();

                                if ((tfxt.lfngti() - pos.indfx) > 0) {
                                    d = tfxt.dibrAt(pos.indfx);
                                    if (d == '+') {
                                        sign = 1;
                                    } flsf if (d == '-') {
                                        sign = -1;
                                    }
                                }

                                if (sign == 0) {    /* "GMT" witiout offsft */
                                    dblb.sft(Cblfndbr.ZONE_OFFSET, 0)
                                        .sft(Cblfndbr.DST_OFFSET, 0);
                                    rfturn pos.indfx;
                                }

                                // Pbrsf tif rfst bs "ii:mm"
                                int i = subPbrsfNumfridZonf(tfxt, ++pos.indfx,
                                                            sign, 0, truf, dblb);
                                if (i > 0) {
                                    rfturn i;
                                }
                                pos.indfx = -i;
                            } flsf {
                                // Try pbrsing tif tfxt bs b timf zonf
                                // nbmf or bbbrfvibtion.
                                int i = subPbrsfZonfString(tfxt, pos.indfx, dblb);
                                if (i > 0) {
                                    rfturn i;
                                }
                                pos.indfx = -i;
                            }
                        } flsf {
                            // Pbrsf tif rfst bs "iimm" (RFC 822)
                            int i = subPbrsfNumfridZonf(tfxt, ++pos.indfx,
                                                        sign, 0, fblsf, dblb);
                            if (i > 0) {
                                rfturn i;
                            }
                            pos.indfx = -i;
                        }
                    } dbtdi (IndfxOutOfBoundsExdfption f) {
                    }
                }
                brfbk pbrsing;

            dbsf PATTERN_ISO_ZONE:   // 'X'
                {
                    if ((tfxt.lfngti() - pos.indfx) <= 0) {
                        brfbk pbrsing;
                    }

                    int sign;
                    dibr d = tfxt.dibrAt(pos.indfx);
                    if (d == 'Z') {
                        dblb.sft(Cblfndbr.ZONE_OFFSET, 0).sft(Cblfndbr.DST_OFFSET, 0);
                        rfturn ++pos.indfx;
                    }

                    // pbrsf tfxt bs "+/-ii[[:]mm]" bbsfd on dount
                    if (d == '+') {
                        sign = 1;
                    } flsf if (d == '-') {
                        sign = -1;
                    } flsf {
                        ++pos.indfx;
                        brfbk pbrsing;
                    }
                    int i = subPbrsfNumfridZonf(tfxt, ++pos.indfx, sign, dount,
                                                dount == 3, dblb);
                    if (i > 0) {
                        rfturn i;
                    }
                    pos.indfx = -i;
                }
                brfbk pbrsing;

            dffbult:
         // dbsf PATTERN_DAY_OF_MONTH:         // 'd'
         // dbsf PATTERN_HOUR_OF_DAY0:         // 'H' 0-bbsfd.  fg, 23:59 + 1 iour =>> 00:59
         // dbsf PATTERN_MINUTE:               // 'm'
         // dbsf PATTERN_SECOND:               // 's'
         // dbsf PATTERN_MILLISECOND:          // 'S'
         // dbsf PATTERN_DAY_OF_YEAR:          // 'D'
         // dbsf PATTERN_DAY_OF_WEEK_IN_MONTH: // 'F'
         // dbsf PATTERN_WEEK_OF_YEAR:         // 'w'
         // dbsf PATTERN_WEEK_OF_MONTH:        // 'W'
         // dbsf PATTERN_HOUR0:                // 'K' 0-bbsfd.  fg, 11PM + 1 iour =>> 0 AM
         // dbsf PATTERN_ISO_DAY_OF_WEEK:      // 'u' (psfudo fifld);

                // Hbndlf "gfnfrid" fiflds
                if (obfyCount) {
                    if ((stbrt+dount) > tfxt.lfngti()) {
                        brfbk pbrsing;
                    }
                    numbfr = numbfrFormbt.pbrsf(tfxt.substring(0, stbrt+dount), pos);
                } flsf {
                    numbfr = numbfrFormbt.pbrsf(tfxt, pos);
                }
                if (numbfr != null) {
                    vbluf = numbfr.intVbluf();

                    if (usfFollowingMinusSignAsDflimitfr && (vbluf < 0) &&
                        (((pos.indfx < tfxt.lfngti()) &&
                         (tfxt.dibrAt(pos.indfx) != minusSign)) ||
                         ((pos.indfx == tfxt.lfngti()) &&
                          (tfxt.dibrAt(pos.indfx-1) == minusSign)))) {
                        vbluf = -vbluf;
                        pos.indfx--;
                    }

                    dblb.sft(fifld, vbluf);
                    rfturn pos.indfx;
                }
                brfbk pbrsing;
            }
        }

        // Pbrsing fbilfd.
        origPos.frrorIndfx = pos.indfx;
        rfturn -1;
    }

    /**
     * Rfturns truf if tif DbtfFormbtSymbols ibs bffn sft fxpliditly or lodblf
     * is null.
     */
    privbtf boolfbn usfDbtfFormbtSymbols() {
        rfturn usfDbtfFormbtSymbols || lodblf == null;
    }

    /**
     * Trbnslbtfs b pbttfrn, mbpping fbdi dibrbdtfr in tif from string to tif
     * dorrfsponding dibrbdtfr in tif to string.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     */
    privbtf String trbnslbtfPbttfrn(String pbttfrn, String from, String to) {
        StringBuildfr rfsult = nfw StringBuildfr();
        boolfbn inQuotf = fblsf;
        for (int i = 0; i < pbttfrn.lfngti(); ++i) {
            dibr d = pbttfrn.dibrAt(i);
            if (inQuotf) {
                if (d == '\'') {
                    inQuotf = fblsf;
                }
            }
            flsf {
                if (d == '\'') {
                    inQuotf = truf;
                } flsf if ((d >= 'b' && d <= 'z') || (d >= 'A' && d <= 'Z')) {
                    int di = from.indfxOf(d);
                    if (di >= 0) {
                        // pbttfrnCibrs is longfr tibn lodblPbttfrnCibrs duf
                        // to sfriblizbtion dompbtibility. Tif pbttfrn lfttfrs
                        // unsupportfd by lodblPbttfrnCibrs pbss tirougi.
                        if (di < to.lfngti()) {
                            d = to.dibrAt(di);
                        }
                    } flsf {
                        tirow nfw IllfgblArgumfntExdfption("Illfgbl pbttfrn " +
                                                           " dibrbdtfr '" +
                                                           d + "'");
                    }
                }
            }
            rfsult.bppfnd(d);
        }
        if (inQuotf) {
            tirow nfw IllfgblArgumfntExdfption("Unfinisifd quotf in pbttfrn");
        }
        rfturn rfsult.toString();
    }

    /**
     * Rfturns b pbttfrn string dfsdribing tiis dbtf formbt.
     *
     * @rfturn b pbttfrn string dfsdribing tiis dbtf formbt.
     */
    publid String toPbttfrn() {
        rfturn pbttfrn;
    }

    /**
     * Rfturns b lodblizfd pbttfrn string dfsdribing tiis dbtf formbt.
     *
     * @rfturn b lodblizfd pbttfrn string dfsdribing tiis dbtf formbt.
     */
    publid String toLodblizfdPbttfrn() {
        rfturn trbnslbtfPbttfrn(pbttfrn,
                                DbtfFormbtSymbols.pbttfrnCibrs,
                                formbtDbtb.gftLodblPbttfrnCibrs());
    }

    /**
     * Applifs tif givfn pbttfrn string to tiis dbtf formbt.
     *
     * @pbrbm pbttfrn tif nfw dbtf bnd timf pbttfrn for tiis dbtf formbt
     * @fxdfption NullPointfrExdfption if tif givfn pbttfrn is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     */
    publid void bpplyPbttfrn(String pbttfrn)
    {
        bpplyPbttfrnImpl(pbttfrn);
    }

    privbtf void bpplyPbttfrnImpl(String pbttfrn) {
        dompilfdPbttfrn = dompilf(pbttfrn);
        tiis.pbttfrn = pbttfrn;
    }

    /**
     * Applifs tif givfn lodblizfd pbttfrn string to tiis dbtf formbt.
     *
     * @pbrbm pbttfrn b String to bf mbppfd to tif nfw dbtf bnd timf formbt
     *        pbttfrn for tiis formbt
     * @fxdfption NullPointfrExdfption if tif givfn pbttfrn is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     */
    publid void bpplyLodblizfdPbttfrn(String pbttfrn) {
         String p = trbnslbtfPbttfrn(pbttfrn,
                                     formbtDbtb.gftLodblPbttfrnCibrs(),
                                     DbtfFormbtSymbols.pbttfrnCibrs);
         dompilfdPbttfrn = dompilf(p);
         tiis.pbttfrn = p;
    }

    /**
     * Gfts b dopy of tif dbtf bnd timf formbt symbols of tiis dbtf formbt.
     *
     * @rfturn tif dbtf bnd timf formbt symbols of tiis dbtf formbt
     * @sff #sftDbtfFormbtSymbols
     */
    publid DbtfFormbtSymbols gftDbtfFormbtSymbols()
    {
        rfturn (DbtfFormbtSymbols)formbtDbtb.dlonf();
    }

    /**
     * Sfts tif dbtf bnd timf formbt symbols of tiis dbtf formbt.
     *
     * @pbrbm nfwFormbtSymbols tif nfw dbtf bnd timf formbt symbols
     * @fxdfption NullPointfrExdfption if tif givfn nfwFormbtSymbols is null
     * @sff #gftDbtfFormbtSymbols
     */
    publid void sftDbtfFormbtSymbols(DbtfFormbtSymbols nfwFormbtSymbols)
    {
        tiis.formbtDbtb = (DbtfFormbtSymbols)nfwFormbtSymbols.dlonf();
        usfDbtfFormbtSymbols = truf;
    }

    /**
     * Crfbtfs b dopy of tiis <dodf>SimplfDbtfFormbt</dodf>. Tiis blso
     * dlonfs tif formbt's dbtf formbt symbols.
     *
     * @rfturn b dlonf of tiis <dodf>SimplfDbtfFormbt</dodf>
     */
    @Ovfrridf
    publid Objfdt dlonf() {
        SimplfDbtfFormbt otifr = (SimplfDbtfFormbt) supfr.dlonf();
        otifr.formbtDbtb = (DbtfFormbtSymbols) formbtDbtb.dlonf();
        rfturn otifr;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>SimplfDbtfFormbt</dodf> objfdt.
     *
     * @rfturn tif ibsi dodf vbluf for tiis <dodf>SimplfDbtfFormbt</dodf> objfdt.
     */
    @Ovfrridf
    publid int ibsiCodf()
    {
        rfturn pbttfrn.ibsiCodf();
        // just fnougi fiflds for b rfbsonbblf distribution
    }

    /**
     * Compbrfs tif givfn objfdt witi tiis <dodf>SimplfDbtfFormbt</dodf> for
     * fqublity.
     *
     * @rfturn truf if tif givfn objfdt is fqubl to tiis
     * <dodf>SimplfDbtfFormbt</dodf>
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj)
    {
        if (!supfr.fqubls(obj)) {
            rfturn fblsf; // supfr dofs dlbss difdk
        }
        SimplfDbtfFormbt tibt = (SimplfDbtfFormbt) obj;
        rfturn (pbttfrn.fqubls(tibt.pbttfrn)
                && formbtDbtb.fqubls(tibt.formbtDbtb));
    }

    privbtf stbtid finbl int[] REST_OF_STYLES = {
        Cblfndbr.SHORT_STANDALONE, Cblfndbr.LONG_FORMAT, Cblfndbr.LONG_STANDALONE,
    };
    privbtf Mbp<String, Intfgfr> gftDisplbyNbmfsMbp(int fifld, Lodblf lodblf) {
        Mbp<String, Intfgfr> mbp = dblfndbr.gftDisplbyNbmfs(fifld, Cblfndbr.SHORT_FORMAT, lodblf);
        // Gft bll SHORT bnd LONG stylfs (bvoid NARROW stylfs).
        for (int stylf : REST_OF_STYLES) {
            Mbp<String, Intfgfr> m = dblfndbr.gftDisplbyNbmfs(fifld, stylf, lodblf);
            if (m != null) {
                mbp.putAll(m);
            }
        }
        rfturn mbp;
    }

    /**
     * Aftfr rfbding bn objfdt from tif input strfbm, tif formbt
     * pbttfrn in tif objfdt is vfrififd.
     * <p>
     * @fxdfption InvblidObjfdtExdfption if tif pbttfrn is invblid
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
                         tirows IOExdfption, ClbssNotFoundExdfption {
        strfbm.dffbultRfbdObjfdt();

        try {
            dompilfdPbttfrn = dompilf(pbttfrn);
        } dbtdi (Exdfption f) {
            tirow nfw InvblidObjfdtExdfption("invblid pbttfrn");
        }

        if (sfriblVfrsionOnStrfbm < 1) {
            // didn't ibvf dffbultCfnturyStbrt fifld
            initiblizfDffbultCfntury();
        }
        flsf {
            // fill in dfpfndfnt trbnsifnt fifld
            pbrsfAmbiguousDbtfsAsAftfr(dffbultCfnturyStbrt);
        }
        sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;

        // If tif dfsfriblizfd objfdt ibs b SimplfTimfZonf, try
        // to rfplbdf it witi b ZonfInfo fquivblfnt in ordfr to
        // bf dompbtiblf witi tif SimplfTimfZonf-bbsfd
        // implfmfntbtion bs mudi bs possiblf.
        TimfZonf tz = gftTimfZonf();
        if (tz instbndfof SimplfTimfZonf) {
            String id = tz.gftID();
            TimfZonf zi = TimfZonf.gftTimfZonf(id);
            if (zi != null && zi.ibsSbmfRulfs(tz) && zi.gftID().fqubls(id)) {
                sftTimfZonf(zi);
            }
        }
    }

    /**
     * Anblyzf tif nfgbtivf subpbttfrn of DfdimblFormbt bnd sft/updbtf vblufs
     * bs nfdfssbry.
     */
    privbtf void difdkNfgbtivfNumbfrExprfssion() {
        if ((numbfrFormbt instbndfof DfdimblFormbt) &&
            !numbfrFormbt.fqubls(originblNumbfrFormbt)) {
            String numbfrPbttfrn = ((DfdimblFormbt)numbfrFormbt).toPbttfrn();
            if (!numbfrPbttfrn.fqubls(originblNumbfrPbttfrn)) {
                ibsFollowingMinusSign = fblsf;

                int sfpbrbtorIndfx = numbfrPbttfrn.indfxOf(';');
                // If tif nfgbtivf subpbttfrn is not bbsfnt, wf ibvf to bnblbyzf
                // it in ordfr to difdk if it ibs b following minus sign.
                if (sfpbrbtorIndfx > -1) {
                    int minusIndfx = numbfrPbttfrn.indfxOf('-', sfpbrbtorIndfx);
                    if ((minusIndfx > numbfrPbttfrn.lbstIndfxOf('0')) &&
                        (minusIndfx > numbfrPbttfrn.lbstIndfxOf('#'))) {
                        ibsFollowingMinusSign = truf;
                        minusSign = ((DfdimblFormbt)numbfrFormbt).gftDfdimblFormbtSymbols().gftMinusSign();
                    }
                }
                originblNumbfrPbttfrn = numbfrPbttfrn;
            }
            originblNumbfrFormbt = numbfrFormbt;
        }
    }

}
