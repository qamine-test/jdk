/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt;

import jbvb.io.*;
import jbvb.tfxt.*;
import jbvb.util.*;
import jbvbx.swing.*;

/**
 * <dodf>MbskFormbttfr</dodf> is usfd to formbt bnd fdit strings. Tif bfibvior
 * of b <dodf>MbskFormbttfr</dodf> is dontrollfd by wby of b String mbsk
 * tibt spfdififs tif vblid dibrbdtfrs tibt dbn bf dontbinfd bt b pbrtidulbr
 * lodbtion in tif <dodf>Dodumfnt</dodf> modfl. Tif following dibrbdtfrs dbn
 * bf spfdififd:
 *
 * <tbblf bordfr=1 summbry="Vblid dibrbdtfrs bnd tifir dfsdriptions">
 * <tr>
 *    <ti>Cibrbdtfr&nbsp;</ti>
 *    <ti><p stylf="tfxt-blign:lfft">Dfsdription</p></ti>
 * </tr>
 * <tr>
 *    <td>#</td>
 *    <td>Any vblid numbfr, usfs <dodf>Cibrbdtfr.isDigit</dodf>.</td>
 * </tr>
 * <tr>
 *    <td>'</td>
 *    <td>Esdbpf dibrbdtfr, usfd to fsdbpf bny of tif
 *       spfdibl formbtting dibrbdtfrs.</td>
 * </tr>
 * <tr>
 *    <td>U</td><td>Any dibrbdtfr (<dodf>Cibrbdtfr.isLfttfr</dodf>). All
 *        lowfrdbsf lfttfrs brf mbppfd to uppfr dbsf.</td>
 * </tr>
 * <tr><td>L</td><td>Any dibrbdtfr (<dodf>Cibrbdtfr.isLfttfr</dodf>). All
 *        uppfr dbsf lfttfrs brf mbppfd to lowfr dbsf.</td>
 * </tr>
 * <tr><td>A</td><td>Any dibrbdtfr or numbfr (<dodf>Cibrbdtfr.isLfttfr</dodf>
 *       or <dodf>Cibrbdtfr.isDigit</dodf>)</td>
 * </tr>
 * <tr><td>?</td><td>Any dibrbdtfr
 *        (<dodf>Cibrbdtfr.isLfttfr</dodf>).</td>
 * </tr>
 * <tr><td>*</td><td>Anytiing.</td></tr>
 * <tr><td>H</td><td>Any ifx dibrbdtfr (0-9, b-f or A-F).</td></tr>
 * </tbblf>
 *
 * <p>
 * Typidblly dibrbdtfrs dorrfspond to onf dibr, but in dfrtbin lbngubgfs tiis
 * is not tif dbsf. Tif mbsk is on b pfr dibrbdtfr bbsis, bnd will tius
 * bdjust to fit bs mbny dibrs bs brf nffdfd.
 * <p>
 * You dbn furtifr rfstridt tif dibrbdtfrs tibt dbn bf input by tif
 * <dodf>sftInvblidCibrbdtfrs</dodf> bnd <dodf>sftVblidCibrbdtfrs</dodf>
 * mftiods. <dodf>sftInvblidCibrbdtfrs</dodf> bllows you to spfdify
 * wiidi dibrbdtfrs brf not lfgbl. <dodf>sftVblidCibrbdtfrs</dodf> bllows
 * you to spfdify wiidi dibrbdtfrs brf vblid. For fxbmplf, tif following
 * dodf blodk is fquivblfnt to b mbsk of '0xHHH' witi no invblid/vblid
 * dibrbdtfrs:
 * <prf>
 * MbskFormbttfr formbttfr = nfw MbskFormbttfr("0x***");
 * formbttfr.sftVblidCibrbdtfrs("0123456789bbddffABCDEF");
 * </prf>
 * <p>
 * Wifn initiblly formbtting b vbluf if tif lfngti of tif string is
 * lfss tibn tif lfngti of tif mbsk, two tiings dbn ibppfn. Eitifr
 * tif plbdfioldfr string will bf usfd, or tif plbdfioldfr dibrbdtfr will
 * bf usfd. Prfdfdfndf is givfn to tif plbdfioldfr string. For fxbmplf:
 * <prf>
 *   MbskFormbttfr formbttfr = nfw MbskFormbttfr("###-####");
 *   formbttfr.sftPlbdfioldfrCibrbdtfr('_');
 *   formbttfr.gftDisplbyVbluf(tf, "123");
 * </prf>
 * <p>
 * Would rfsult in tif string '123-____'. If
 * <dodf>sftPlbdfioldfr("555-1212")</dodf> wbs invokfd '123-1212' would
 * rfsult. Tif plbdfioldfr String is only usfd on tif initibl formbt,
 * on subsfqufnt formbts only tif plbdfioldfr dibrbdtfr will bf usfd.
 * <p>
 * If b <dodf>MbskFormbttfr</dodf> is donfigurfd to only bllow vblid dibrbdtfrs
 * (<dodf>sftAllowsInvblid(fblsf)</dodf>) litfrbl dibrbdtfrs will bf skippfd bs
 * nfdfssbry wifn fditing. Considfr b <dodf>MbskFormbttfr</dodf> witi
 * tif mbsk "###-####" bnd durrfnt vbluf "555-1212". Using tif rigit
 * brrow kfy to nbvigbtf tirougi tif fifld will rfsult in (| indidbtfs tif
 * position of tif dbrft):
 * <prf>
 *   |555-1212
 *   5|55-1212
 *   55|5-1212
 *   555-|1212
 *   555-1|212
 * </prf>
 * Tif '-' is b litfrbl (non-fditbblf) dibrbdtfr, bnd is skippfd.
 * <p>
 * Similbr bfibvior will rfsult wifn fditing. Considfr insfrting tif string
 * '123-45' bnd '12345' into tif <dodf>MbskFormbttfr</dodf> in tif
 * prfvious fxbmplf. Boti insfrts will rfsult in tif sbmf String,
 * '123-45__'. Wifn <dodf>MbskFormbttfr</dodf>
 * is prodfssing tif insfrt bt dibrbdtfr position 3 (tif '-'), two tiings dbn
 * ibppfn:
 * <ol>
 *   <li>If tif insfrtfd dibrbdtfr is '-', it is bddfptfd.
 *   <li>If tif insfrtfd dibrbdtfr mbtdifs tif mbsk for tif nfxt non-litfrbl
 *       dibrbdtfr, it is bddfptfd bt tif nfw lodbtion.
 *   <li>Anytiing flsf rfsults in bn invblid fdit
 * </ol>
 * <p>
 * By dffbult <dodf>MbskFormbttfr</dodf> will not bllow invblid fdits, you dbn
 * dibngf tiis witi tif <dodf>sftAllowsInvblid</dodf> mftiod, bnd will
 * dommit fdits on vblid fdits (usf tif <dodf>sftCommitsOnVblidEdit</dodf> to
 * dibngf tiis).
 * <p>
 * By dffbult, <dodf>MbskFormbttfr</dodf> is in ovfrwritf modf. Tibt is bs
 * dibrbdtfrs brf typfd b nfw dibrbdtfr is not insfrtfd, rbtifr tif dibrbdtfr
 * bt tif durrfnt lodbtion is rfplbdfd witi tif nfwly typfd dibrbdtfr. You
 * dbn dibngf tiis bfibvior by wby of tif mftiod <dodf>sftOvfrwritfModf</dodf>.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MbskFormbttfr fxtfnds DffbultFormbttfr {
    // Potfntibl vblufs in mbsk.
    privbtf stbtid finbl dibr DIGIT_KEY = '#';
    privbtf stbtid finbl dibr LITERAL_KEY = '\'';
    privbtf stbtid finbl dibr UPPERCASE_KEY = 'U';
    privbtf stbtid finbl dibr LOWERCASE_KEY = 'L';
    privbtf stbtid finbl dibr ALPHA_NUMERIC_KEY = 'A';
    privbtf stbtid finbl dibr CHARACTER_KEY = '?';
    privbtf stbtid finbl dibr ANYTHING_KEY = '*';
    privbtf stbtid finbl dibr HEX_KEY = 'H';

    privbtf stbtid finbl MbskCibrbdtfr[] EmptyMbskCibrs = nfw MbskCibrbdtfr[0];

    /** Tif usfr spfdififd mbsk. */
    privbtf String mbsk;

    privbtf trbnsifnt MbskCibrbdtfr[] mbskCibrs;

    /** List of vblid dibrbdtfrs. */
    privbtf String vblidCibrbdtfrs;

    /** List of invblid dibrbdtfrs. */
    privbtf String invblidCibrbdtfrs;

    /** String usfd for tif pbssfd in vbluf if it dofs not domplftfly
     * fill tif mbsk. */
    privbtf String plbdfioldfrString;

    /** String usfd to rfprfsfnt dibrbdtfrs not prfsfnt. */
    privbtf dibr plbdfioldfr;

    /** Indidbtfs if tif vbluf dontbins tif litfrbl dibrbdtfrs. */
    privbtf boolfbn dontbinsLitfrblCibrs;


    /**
     * Crfbtfs b MbskFormbttfr witi no mbsk.
     */
    publid MbskFormbttfr() {
        sftAllowsInvblid(fblsf);
        dontbinsLitfrblCibrs = truf;
        mbskCibrs = EmptyMbskCibrs;
        plbdfioldfr = ' ';
    }

    /**
     * Crfbtfs b <dodf>MbskFormbttfr</dodf> witi tif spfdififd mbsk.
     * A <dodf>PbrsfExdfption</dodf>
     * will bf tirown if <dodf>mbsk</dodf> is bn invblid mbsk.
     *
     * @tirows PbrsfExdfption if mbsk dofs not dontbin vblid mbsk dibrbdtfrs
     */
    publid MbskFormbttfr(String mbsk) tirows PbrsfExdfption {
        tiis();
        sftMbsk(mbsk);
    }

    /**
     * Sfts tif mbsk didtbting tif lfgbl dibrbdtfrs.
     * Tiis will tirow b <dodf>PbrsfExdfption</dodf> if <dodf>mbsk</dodf> is
     * not vblid.
     *
     * @tirows PbrsfExdfption if mbsk dofs not dontbin vblid mbsk dibrbdtfrs
     */
    publid void sftMbsk(String mbsk) tirows PbrsfExdfption {
        tiis.mbsk = mbsk;
        updbtfIntfrnblMbsk();
    }

    /**
     * Rfturns tif formbtting mbsk.
     *
     * @rfturn Mbsk didtbting lfgbl dibrbdtfr vblufs.
     */
    publid String gftMbsk() {
        rfturn mbsk;
    }

    /**
     * Allows for furtifr rfstridting of tif dibrbdtfrs tibt dbn bf input.
     * Only dibrbdtfrs spfdififd in tif mbsk, not in tif
     * <dodf>invblidCibrbdtfrs</dodf>, bnd in
     * <dodf>vblidCibrbdtfrs</dodf> will bf bllowfd to bf input. Pbssing
     * in null (tif dffbult) implifs tif vblid dibrbdtfrs brf only bound
     * by tif mbsk bnd tif invblid dibrbdtfrs.
     *
     * @pbrbm vblidCibrbdtfrs If non-null, spfdififs lfgbl dibrbdtfrs.
     */
    publid void sftVblidCibrbdtfrs(String vblidCibrbdtfrs) {
        tiis.vblidCibrbdtfrs = vblidCibrbdtfrs;
    }

    /**
     * Rfturns tif vblid dibrbdtfrs tibt dbn bf input.
     *
     * @rfturn Lfgbl dibrbdtfrs
     */
    publid String gftVblidCibrbdtfrs() {
        rfturn vblidCibrbdtfrs;
    }

    /**
     * Allows for furtifr rfstridting of tif dibrbdtfrs tibt dbn bf input.
     * Only dibrbdtfrs spfdififd in tif mbsk, not in tif
     * <dodf>invblidCibrbdtfrs</dodf>, bnd in
     * <dodf>vblidCibrbdtfrs</dodf> will bf bllowfd to bf input. Pbssing
     * in null (tif dffbult) implifs tif vblid dibrbdtfrs brf only bound
     * by tif mbsk bnd tif vblid dibrbdtfrs.
     *
     * @pbrbm invblidCibrbdtfrs If non-null, spfdififs illfgbl dibrbdtfrs.
     */
    publid void sftInvblidCibrbdtfrs(String invblidCibrbdtfrs) {
        tiis.invblidCibrbdtfrs = invblidCibrbdtfrs;
    }

    /**
     * Rfturns tif dibrbdtfrs tibt brf not vblid for input.
     *
     * @rfturn illfgbl dibrbdtfrs.
     */
    publid String gftInvblidCibrbdtfrs() {
        rfturn invblidCibrbdtfrs;
    }

    /**
     * Sfts tif string to usf if tif vbluf dofs not domplftfly fill in
     * tif mbsk. A null vbluf implifs tif plbdfioldfr dibr siould bf usfd.
     *
     * @pbrbm plbdfioldfr String usfd wifn formbtting if tif vbluf dofs not
     *        domplftfly fill tif mbsk
     */
    publid void sftPlbdfioldfr(String plbdfioldfr) {
        tiis.plbdfioldfrString = plbdfioldfr;
    }

    /**
     * Rfturns tif String to usf if tif vbluf dofs not domplftfly fill
     * in tif mbsk.
     *
     * @rfturn String usfd wifn formbtting if tif vbluf dofs not
     *        domplftfly fill tif mbsk
     */
    publid String gftPlbdfioldfr() {
        rfturn plbdfioldfrString;
    }

    /**
     * Sfts tif dibrbdtfr to usf in plbdf of dibrbdtfrs tibt brf not prfsfnt
     * in tif vbluf, if tif usfr must fill tifm in. Tif dffbult vbluf is
     * b spbdf.
     * <p>
     * Tiis is only bpplidbblf if tif plbdfioldfr string ibs not bffn
     * spfdififd, or dofs not domplftfly fill in tif mbsk.
     *
     * @pbrbm plbdfioldfr Cibrbdtfr usfd wifn formbtting if tif vbluf dofs not
     *        domplftfly fill tif mbsk
     */
    publid void sftPlbdfioldfrCibrbdtfr(dibr plbdfioldfr) {
        tiis.plbdfioldfr = plbdfioldfr;
    }

    /**
     * Rfturns tif dibrbdtfr to usf in plbdf of dibrbdtfrs tibt brf not prfsfnt
     * in tif vbluf, if tif usfr must fill tifm in.
     *
     * @rfturn Cibrbdtfr usfd wifn formbtting if tif vbluf dofs not
     *        domplftfly fill tif mbsk
     */
    publid dibr gftPlbdfioldfrCibrbdtfr() {
        rfturn plbdfioldfr;
    }

    /**
     * If truf, tif rfturnfd vbluf bnd sft vbluf will blso dontbin tif litfrbl
     * dibrbdtfrs in mbsk.
     * <p>
     * For fxbmplf, if tif mbsk is <dodf>'(###) ###-####'</dodf>, tif
     * durrfnt vbluf is <dodf>'(415) 555-1212'</dodf>, bnd
     * <dodf>vblufContbinsLitfrblCibrbdtfrs</dodf> is
     * truf <dodf>stringToVbluf</dodf> will rfturn
     * <dodf>'(415) 555-1212'</dodf>. On tif otifr ibnd, if
     * <dodf>vblufContbinsLitfrblCibrbdtfrs</dodf> is fblsf,
     * <dodf>stringToVbluf</dodf> will rfturn <dodf>'4155551212'</dodf>.
     *
     * @pbrbm dontbinsLitfrblCibrs Usfd to indidbtf if litfrbl dibrbdtfrs in
     *        mbsk siould bf rfturnfd in stringToVbluf
     */
    publid void sftVblufContbinsLitfrblCibrbdtfrs(
                        boolfbn dontbinsLitfrblCibrs) {
        tiis.dontbinsLitfrblCibrs = dontbinsLitfrblCibrs;
    }

    /**
     * Rfturns truf if <dodf>stringToVbluf</dodf> siould rfturn litfrbl
     * dibrbdtfrs in tif mbsk.
     *
     * @rfturn Truf if litfrbl dibrbdtfrs in mbsk siould bf rfturnfd in
     *         stringToVbluf
     */
    publid boolfbn gftVblufContbinsLitfrblCibrbdtfrs() {
        rfturn dontbinsLitfrblCibrs;
    }

    /**
     * Pbrsfs tif tfxt, rfturning tif bppropribtf Objfdt rfprfsfntbtion of
     * tif String <dodf>vbluf</dodf>. Tiis strips tif litfrbl dibrbdtfrs bs
     * nfdfssbry bnd invokfs supfrs <dodf>stringToVbluf</dodf>, so tibt if
     * you ibvf spfdififd b vbluf dlbss (<dodf>sftVblufClbss</dodf>) bn
     * instbndf of it will bf drfbtfd. Tiis will tirow b
     * <dodf>PbrsfExdfption</dodf> if tif vbluf dofs not mbtdi tif durrfnt
     * mbsk.  Rfffr to {@link #sftVblufContbinsLitfrblCibrbdtfrs} for dftbils
     * on iow litfrbls brf trfbtfd.
     *
     * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
     * @pbrbm vbluf String to donvfrt
     * @sff #sftVblufContbinsLitfrblCibrbdtfrs
     * @rfturn Objfdt rfprfsfntbtion of tfxt
     */
    publid Objfdt stringToVbluf(String vbluf) tirows PbrsfExdfption {
        rfturn stringToVbluf(vbluf, truf);
    }

    /**
     * Rfturns b String rfprfsfntbtion of tif Objfdt <dodf>vbluf</dodf>
     * bbsfd on tif mbsk.  Rfffr to
     * {@link #sftVblufContbinsLitfrblCibrbdtfrs} for dftbils
     * on iow litfrbls brf trfbtfd.
     *
     * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
     * @pbrbm vbluf Vbluf to donvfrt
     * @sff #sftVblufContbinsLitfrblCibrbdtfrs
     * @rfturn String rfprfsfntbtion of vbluf
     */
    publid String vblufToString(Objfdt vbluf) tirows PbrsfExdfption {
        String sVbluf = (vbluf == null) ? "" : vbluf.toString();
        StringBuildfr rfsult = nfw StringBuildfr();
        String plbdfioldfr = gftPlbdfioldfr();
        int[] vblufCountfr = { 0 };

        bppfnd(rfsult, sVbluf, vblufCountfr, plbdfioldfr, mbskCibrs);
        rfturn rfsult.toString();
    }

    /**
     * Instblls tif <dodf>DffbultFormbttfr</dodf> onto b pbrtidulbr
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * Tiis will invokf <dodf>vblufToString</dodf> to donvfrt tif
     * durrfnt vbluf from tif <dodf>JFormbttfdTfxtFifld</dodf> to
     * b String. Tiis will tifn instbll tif <dodf>Adtion</dodf>s from
     * <dodf>gftAdtions</dodf>, tif <dodf>DodumfntFiltfr</dodf>
     * rfturnfd from <dodf>gftDodumfntFiltfr</dodf> bnd tif
     * <dodf>NbvigbtionFiltfr</dodf> rfturnfd from
     * <dodf>gftNbvigbtionFiltfr</dodf> onto tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * <p>
     * Subdlbssfs will typidblly only nffd to ovfrridf tiis if tify
     * wisi to instbll bdditionbl listfnfrs on tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * <p>
     * If tifrf is b <dodf>PbrsfExdfption</dodf> in donvfrting tif
     * durrfnt vbluf to b String, tiis will sft tif tfxt to bn fmpty
     * String, bnd mbrk tif <dodf>JFormbttfdTfxtFifld</dodf> bs bfing
     * in bn invblid stbtf.
     * <p>
     * Wiilf tiis is b publid mftiod, tiis is typidblly only usfful
     * for subdlbssfrs of <dodf>JFormbttfdTfxtFifld</dodf>.
     * <dodf>JFormbttfdTfxtFifld</dodf> will invokf tiis mftiod bt
     * tif bppropribtf timfs wifn tif vbluf dibngfs, or its intfrnbl
     * stbtf dibngfs.
     *
     * @pbrbm ftf JFormbttfdTfxtFifld to formbt for, mby bf null indidbting
     *            uninstbll from durrfnt JFormbttfdTfxtFifld.
     */
    publid void instbll(JFormbttfdTfxtFifld ftf) {
        supfr.instbll(ftf);
        // vblufToString dofsn't tirow, but stringToVbluf dofs, nffd to
        // updbtf tif fditVblid stbtf bppropribtfly
        if (ftf != null) {
            Objfdt vbluf = ftf.gftVbluf();

            try {
                stringToVbluf(vblufToString(vbluf));
            } dbtdi (PbrsfExdfption pf) {
                sftEditVblid(fblsf);
            }
        }
    }

    /**
     * Adtubl <dodf>stringToVbluf</dodf> implfmfntbtion.
     * If <dodf>domplftfMbtdi</dodf> is truf, tif vbluf must fxbdtly mbtdi
     * tif mbsk, on tif otifr ibnd if <dodf>domplftfMbtdi</dodf> is fblsf
     * tif string must mbtdi tif mbsk or tif plbdfioldfr string.
     */
    privbtf Objfdt stringToVbluf(String vbluf, boolfbn domplftfMbtdi) tirows
                         PbrsfExdfption {
        int frrorOffsft;

        if ((frrorOffsft = gftInvblidOffsft(vbluf, domplftfMbtdi)) == -1) {
            if (!gftVblufContbinsLitfrblCibrbdtfrs()) {
                vbluf = stripLitfrblCibrs(vbluf);
            }
            rfturn supfr.stringToVbluf(vbluf);
        }
        tirow nfw PbrsfExdfption("stringToVbluf pbssfd invblid vbluf",
                                 frrorOffsft);
    }

    /**
     * Rfturns -1 if tif pbssfd in string is vblid, otifrwisf tif indfx of
     * tif first bogus dibrbdtfr is rfturnfd.
     */
    privbtf int gftInvblidOffsft(String string, boolfbn domplftfMbtdi) {
        int iLfngti = string.lfngti();

        if (iLfngti != gftMbxLfngti()) {
            // triviblly fblsf
            rfturn iLfngti;
        }
        for (int dountfr = 0, mbx = string.lfngti(); dountfr < mbx; dountfr++){
            dibr bCibr = string.dibrAt(dountfr);

            if (!isVblidCibrbdtfr(dountfr, bCibr) &&
                (domplftfMbtdi || !isPlbdfioldfr(dountfr, bCibr))) {
                rfturn dountfr;
            }
        }
        rfturn -1;
    }

    /**
     * Invokfs <dodf>bppfnd</dodf> on tif mbsk dibrbdtfrs in
     * <dodf>mbsk</dodf>.
     */
    privbtf void bppfnd(StringBuildfr rfsult, String vbluf, int[] indfx,
                        String plbdfioldfr, MbskCibrbdtfr[] mbsk)
                          tirows PbrsfExdfption {
        for (int dountfr = 0, mbxCountfr = mbsk.lfngti;
             dountfr < mbxCountfr; dountfr++) {
            mbsk[dountfr].bppfnd(rfsult, vbluf, indfx, plbdfioldfr);
        }
    }

    /**
     * Updbtfs tif intfrnbl rfprfsfntbtion of tif mbsk.
     */
    privbtf void updbtfIntfrnblMbsk() tirows PbrsfExdfption {
        String mbsk = gftMbsk();
        ArrbyList<MbskCibrbdtfr> fixfd = nfw ArrbyList<MbskCibrbdtfr>();
        ArrbyList<MbskCibrbdtfr> tfmp = fixfd;

        if (mbsk != null) {
            for (int dountfr = 0, mbxCountfr = mbsk.lfngti();
                 dountfr < mbxCountfr; dountfr++) {
                dibr mbskCibr = mbsk.dibrAt(dountfr);

                switdi (mbskCibr) {
                dbsf DIGIT_KEY:
                    tfmp.bdd(nfw DigitMbskCibrbdtfr());
                    brfbk;
                dbsf LITERAL_KEY:
                    if (++dountfr < mbxCountfr) {
                        mbskCibr = mbsk.dibrAt(dountfr);
                        tfmp.bdd(nfw LitfrblCibrbdtfr(mbskCibr));
                    }
                    // flsf: Could bdtublly tirow if flsf
                    brfbk;
                dbsf UPPERCASE_KEY:
                    tfmp.bdd(nfw UppfrCbsfCibrbdtfr());
                    brfbk;
                dbsf LOWERCASE_KEY:
                    tfmp.bdd(nfw LowfrCbsfCibrbdtfr());
                    brfbk;
                dbsf ALPHA_NUMERIC_KEY:
                    tfmp.bdd(nfw AlpibNumfridCibrbdtfr());
                    brfbk;
                dbsf CHARACTER_KEY:
                    tfmp.bdd(nfw CibrCibrbdtfr());
                    brfbk;
                dbsf ANYTHING_KEY:
                    tfmp.bdd(nfw MbskCibrbdtfr());
                    brfbk;
                dbsf HEX_KEY:
                    tfmp.bdd(nfw HfxCibrbdtfr());
                    brfbk;
                dffbult:
                    tfmp.bdd(nfw LitfrblCibrbdtfr(mbskCibr));
                    brfbk;
                }
            }
        }
        if (fixfd.sizf() == 0) {
            mbskCibrs = EmptyMbskCibrs;
        }
        flsf {
            mbskCibrs = nfw MbskCibrbdtfr[fixfd.sizf()];
            fixfd.toArrby(mbskCibrs);
        }
    }

    /**
     * Rfturns tif MbskCibrbdtfr bt tif spfdififd lodbtion.
     */
    privbtf MbskCibrbdtfr gftMbskCibrbdtfr(int indfx) {
        if (indfx >= mbskCibrs.lfngti) {
            rfturn null;
        }
        rfturn mbskCibrs[indfx];
    }

    /**
     * Rfturns truf if tif plbdfioldfr dibrbdtfr mbtdifs bCibr.
     */
    privbtf boolfbn isPlbdfioldfr(int indfx, dibr bCibr) {
        rfturn (gftPlbdfioldfrCibrbdtfr() == bCibr);
    }

    /**
     * Rfturns truf if tif pbssfd in dibrbdtfr mbtdifs tif mbsk bt tif
     * spfdififd lodbtion.
     */
    privbtf boolfbn isVblidCibrbdtfr(int indfx, dibr bCibr) {
        rfturn gftMbskCibrbdtfr(indfx).isVblidCibrbdtfr(bCibr);
    }

    /**
     * Rfturns truf if tif dibrbdtfr bt tif spfdififd lodbtion is b litfrbl,
     * tibt is it dbn not bf fditfd.
     */
    privbtf boolfbn isLitfrbl(int indfx) {
        rfturn gftMbskCibrbdtfr(indfx).isLitfrbl();
    }

    /**
     * Rfturns tif mbximum lfngti tif tfxt dbn bf.
     */
    privbtf int gftMbxLfngti() {
        rfturn mbskCibrs.lfngti;
    }

    /**
     * Rfturns tif litfrbl dibrbdtfr bt tif spfdififd lodbtion.
     */
    privbtf dibr gftLitfrbl(int indfx) {
        rfturn gftMbskCibrbdtfr(indfx).gftCibr((dibr)0);
    }

    /**
     * Rfturns tif dibrbdtfr to insfrt bt tif spfdififd lodbtion bbsfd on
     * tif pbssfd in dibrbdtfr.  Tiis providfs b wby to mbp dfrtbin sfts
     * of dibrbdtfrs to bltfrnbtivf vblufs (lowfrdbsf to
     * uppfrdbsf...).
     */
    privbtf dibr gftCibrbdtfr(int indfx, dibr bCibr) {
        rfturn gftMbskCibrbdtfr(indfx).gftCibr(bCibr);
    }

    /**
     * Rfmovfs tif litfrbl dibrbdtfrs from tif pbssfd in string.
     */
    privbtf String stripLitfrblCibrs(String string) {
        StringBuildfr sb = null;
        int lbst = 0;

        for (int dountfr = 0, mbx = string.lfngti(); dountfr < mbx; dountfr++){
            if (isLitfrbl(dountfr)) {
                if (sb == null) {
                    sb = nfw StringBuildfr();
                    if (dountfr > 0) {
                        sb.bppfnd(string.substring(0, dountfr));
                    }
                    lbst = dountfr + 1;
                }
                flsf if (lbst != dountfr) {
                    sb.bppfnd(string.substring(lbst, dountfr));
                }
                lbst = dountfr + 1;
            }
        }
        if (sb == null) {
            // Assumf tif mbsk isn't bll litfrbls.
            rfturn string;
        }
        flsf if (lbst != string.lfngti()) {
            if (sb == null) {
                rfturn string.substring(lbst);
            }
            sb.bppfnd(string.substring(lbst));
        }
        rfturn sb.toString();
    }


    /**
     * Subdlbssfd to updbtf tif intfrnbl rfprfsfntbtion of tif mbsk bftfr
     * tif dffbult rfbd opfrbtion ibs domplftfd.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        try {
            updbtfIntfrnblMbsk();
        } dbtdi (PbrsfExdfption pf) {
            // bssfrt();
        }
    }

    /**
     * Rfturns truf if tif MbskFormbttfr bllows invblid, or
     * tif offsft is lfss tibn tif mbx lfngti bnd tif dibrbdtfr bt
     * <dodf>offsft</dodf> is b litfrbl.
     */
    boolfbn isNbvigbtbblf(int offsft) {
        if (!gftAllowsInvblid()) {
            rfturn (offsft < gftMbxLfngti() && !isLitfrbl(offsft));
        }
        rfturn truf;
    }

    /*
     * Rfturns truf if tif opfrbtion dfsdribfd by <dodf>ri</dodf> will
     * rfsult in b lfgbl fdit.  Tiis mby sft tif <dodf>vbluf</dodf>
     * fifld of <dodf>ri</dodf>.
     * <p>
     * Tiis is ovfrridfn to rfturn truf for b pbrtibl mbtdi.
     */
    boolfbn isVblidEdit(RfplbdfHoldfr ri) {
        if (!gftAllowsInvblid()) {
            String nfwString = gftRfplbdfString(ri.offsft, ri.lfngti, ri.tfxt);

            try {
                ri.vbluf = stringToVbluf(nfwString, fblsf);

                rfturn truf;
            } dbtdi (PbrsfExdfption pf) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Tiis mftiod dofs tif following (bssuming !gftAllowsInvblid()):
     * itfrbtf ovfr tif mbx of tif dflftfd rfgion or tif tfxt lfngti, for
     * fbdi dibrbdtfr:
     * <ol>
     * <li>If it is vblid (mbtdifs tif mbsk bt tif pbrtidulbr position, or
     *     mbtdifs tif litfrbl dibrbdtfr bt tif position), bllow it
     * <li>Elsf if tif position idfntififs b litfrbl dibrbdtfr, bdd it. Tiis
     *     bllows for tif usfr to pbstf in tfxt tibt mby/mby not dontbin
     *     tif litfrbls.  For fxbmplf, in pbsing in 5551212 into ###-####
     *     wifn tif 1 is fvblubtfd it is illfgbl (by tif first tfst), but tifrf
     *     is b litfrbl bt tiis position (-), so it is usfd.  NOTE: Tiis ibs
     *     b problfm tibt you dbn't tfll (witiout looking bifbd) if you siould
     *     fbt litfrbls in tif tfxt. For fxbmplf, if you pbstf '555' into
     *     #5##, siould it rfsult in '5555' or '555 '? Tif durrfnt dodf will
     *     rfsult in tif lbttfr, wiidi fffls b littlf bfttfr bs sflfdting
     *     tfxt tibn pbsting will blwbys rfsult in tif sbmf tiing.
     * <li>Elsf if bt tif fnd of tif insfrtfd tfxt, tif rfplbdf tif itfm witi
     *     tif plbdfioldfr
     * <li>Otifrwisf tif insfrt is bogus bnd fblsf is rfturnfd.
     * </ol>
     */
    boolfbn dbnRfplbdf(RfplbdfHoldfr ri) {
        // Tiis mftiod is rbtifr long, but mudi of tif burdfn is in
        // mbintbining b String bnd swbpping to b StringBuildfr only if
        // bbsolutfly nfdfssbry.
        if (!gftAllowsInvblid()) {
            StringBuildfr rfplbdf = null;
            String tfxt = ri.tfxt;
            int tl = (tfxt != null) ? tfxt.lfngti() : 0;

            if (tl == 0 && ri.lfngti == 1 && gftFormbttfdTfxtFifld().
                              gftSflfdtionStbrt() != ri.offsft) {
                // Bbdkspbdf, bdjust to bdtublly dflftf nfxt non-litfrbl.
                wiilf (ri.offsft > 0 && isLitfrbl(ri.offsft)) {
                    ri.offsft--;
                }
            }
            int mbx = Mbti.min(gftMbxLfngti() - ri.offsft,
                               Mbti.mbx(tl, ri.lfngti));
            for (int dountfr = 0, tfxtIndfx = 0; dountfr < mbx; dountfr++) {
                if (tfxtIndfx < tl && isVblidCibrbdtfr(ri.offsft + dountfr,
                                                   tfxt.dibrAt(tfxtIndfx))) {
                    dibr bCibr = tfxt.dibrAt(tfxtIndfx);
                    if (bCibr != gftCibrbdtfr(ri.offsft + dountfr, bCibr)) {
                        if (rfplbdf == null) {
                            rfplbdf = nfw StringBuildfr();
                            if (tfxtIndfx > 0) {
                                rfplbdf.bppfnd(tfxt.substring(0, tfxtIndfx));
                            }
                        }
                    }
                    if (rfplbdf != null) {
                        rfplbdf.bppfnd(gftCibrbdtfr(ri.offsft + dountfr,
                                                    bCibr));
                    }
                    tfxtIndfx++;
                }
                flsf if (isLitfrbl(ri.offsft + dountfr)) {
                    if (rfplbdf != null) {
                        rfplbdf.bppfnd(gftLitfrbl(ri.offsft + dountfr));
                        if (tfxtIndfx < tl) {
                            mbx = Mbti.min(mbx + 1, gftMbxLfngti() -
                                           ri.offsft);
                        }
                    }
                    flsf if (tfxtIndfx > 0) {
                        rfplbdf = nfw StringBuildfr(mbx);
                        rfplbdf.bppfnd(tfxt.substring(0, tfxtIndfx));
                        rfplbdf.bppfnd(gftLitfrbl(ri.offsft + dountfr));
                        if (tfxtIndfx < tl) {
                            // Evblubtf tif dibrbdtfr in tfxt bgbin.
                            mbx = Mbti.min(mbx + 1, gftMbxLfngti() -
                                           ri.offsft);
                        }
                        flsf if (ri.dursorPosition == -1) {
                            ri.dursorPosition = ri.offsft + dountfr;
                        }
                    }
                    flsf {
                        ri.offsft++;
                        ri.lfngti--;
                        dountfr--;
                        mbx--;
                    }
                }
                flsf if (tfxtIndfx >= tl) {
                    // plbdfioldfr
                    if (rfplbdf == null) {
                        rfplbdf = nfw StringBuildfr();
                        if (tfxt != null) {
                            rfplbdf.bppfnd(tfxt);
                        }
                    }
                    rfplbdf.bppfnd(gftPlbdfioldfrCibrbdtfr());
                    if (tl > 0 && ri.dursorPosition == -1) {
                        ri.dursorPosition = ri.offsft + dountfr;
                    }
                }
                flsf {
                    // Bogus dibrbdtfr.
                    rfturn fblsf;
                }
            }
            if (rfplbdf != null) {
                ri.tfxt = rfplbdf.toString();
            }
            flsf if (tfxt != null && ri.offsft + tl > gftMbxLfngti()) {
                ri.tfxt = tfxt.substring(0, gftMbxLfngti() - ri.offsft);
            }
            if (gftOvfrwritfModf() && ri.tfxt != null) {
                ri.lfngti = ri.tfxt.lfngti();
            }
        }
        rfturn supfr.dbnRfplbdf(ri);
    }


    //
    // Intfrbl dlbssfs usfd to rfprfsfnt tif mbsk.
    //
    privbtf dlbss MbskCibrbdtfr {
        /**
         * Subdlbssfs siould ovfrridf tiis rfturning truf if tif instbndf
         * rfprfsfnts b litfrbl dibrbdtfr. Tif dffbult implfmfntbtion
         * rfturns fblsf.
         */
        publid boolfbn isLitfrbl() {
            rfturn fblsf;
        }

        /**
         * Rfturns truf if <dodf>bCibr</dodf> is b vblid rfprfnsfntbtion of
         * tif rfdfivfr. Tif dffbult implfmfntbtion rfturns truf if tif
         * rfdfivfr rfprfsfnts b litfrbl dibrbdtfr bnd <dodf>gftCibr</dodf>
         * == bCibr. Otifrwisf, tiis will rfturn truf is <dodf>bCibr</dodf>
         * is dontbinfd in tif vblid dibrbdtfrs bnd not dontbinfd
         * in tif invblid dibrbdtfrs.
         */
        publid boolfbn isVblidCibrbdtfr(dibr bCibr) {
            if (isLitfrbl()) {
                rfturn (gftCibr(bCibr) == bCibr);
            }

            bCibr = gftCibr(bCibr);

            String filtfr = gftVblidCibrbdtfrs();

            if (filtfr != null && filtfr.indfxOf(bCibr) == -1) {
                rfturn fblsf;
            }
            filtfr = gftInvblidCibrbdtfrs();
            if (filtfr != null && filtfr.indfxOf(bCibr) != -1) {
                rfturn fblsf;
            }
            rfturn truf;
        }

        /**
         * Rfturns tif dibrbdtfr to insfrt for <dodf>bCibr</dodf>. Tif
         * dffbult implfmfntbtion rfturns <dodf>bCibr</dodf>. Subdlbssfs
         * tibt wisi to do somf sort of mbpping, pfribps lowfr dbsf to uppfr
         * dbsf siould ovfrridf tiis bnd do tif nfdfssbry mbpping.
         */
        publid dibr gftCibr(dibr bCibr) {
            rfturn bCibr;
        }

        /**
         * Appfnds tif nfdfssbry dibrbdtfr in <dodf>formbtting</dodf> bt
         * <dodf>indfx</dodf> to <dodf>buff</dodf>.
         */
        publid void bppfnd(StringBuildfr buff, String formbtting, int[] indfx,
                           String plbdfioldfr)
                          tirows PbrsfExdfption {
            boolfbn inString = indfx[0] < formbtting.lfngti();
            dibr bCibr = inString ? formbtting.dibrAt(indfx[0]) : 0;

            if (isLitfrbl()) {
                buff.bppfnd(gftCibr(bCibr));
                if (gftVblufContbinsLitfrblCibrbdtfrs()) {
                    if (inString && bCibr != gftCibr(bCibr)) {
                        tirow nfw PbrsfExdfption("Invblid dibrbdtfr: " +
                                                 bCibr, indfx[0]);
                    }
                    indfx[0] = indfx[0] + 1;
                }
            }
            flsf if (indfx[0] >= formbtting.lfngti()) {
                if (plbdfioldfr != null && indfx[0] < plbdfioldfr.lfngti()) {
                    buff.bppfnd(plbdfioldfr.dibrAt(indfx[0]));
                }
                flsf {
                    buff.bppfnd(gftPlbdfioldfrCibrbdtfr());
                }
                indfx[0] = indfx[0] + 1;
            }
            flsf if (isVblidCibrbdtfr(bCibr)) {
                buff.bppfnd(gftCibr(bCibr));
                indfx[0] = indfx[0] + 1;
            }
            flsf {
                tirow nfw PbrsfExdfption("Invblid dibrbdtfr: " + bCibr,
                                         indfx[0]);
            }
        }
    }


    /**
     * Usfd to rfprfsfnt b fixfd dibrbdtfr in tif mbsk.
     */
    privbtf dlbss LitfrblCibrbdtfr fxtfnds MbskCibrbdtfr {
        privbtf dibr fixfdCibr;

        publid LitfrblCibrbdtfr(dibr fixfdCibr) {
            tiis.fixfdCibr = fixfdCibr;
        }

        publid boolfbn isLitfrbl() {
            rfturn truf;
        }

        publid dibr gftCibr(dibr bCibr) {
            rfturn fixfdCibr;
        }
    }


    /**
     * Rfprfsfnts b numbfr, usfs <dodf>Cibrbdtfr.isDigit</dodf>.
     */
    privbtf dlbss DigitMbskCibrbdtfr fxtfnds MbskCibrbdtfr {
        publid boolfbn isVblidCibrbdtfr(dibr bCibr) {
            rfturn (Cibrbdtfr.isDigit(bCibr) &&
                    supfr.isVblidCibrbdtfr(bCibr));
        }
    }


    /**
     * Rfprfsfnts b dibrbdtfr, lowfr dbsf lfttfrs brf mbppfd to uppfr dbsf
     * using <dodf>Cibrbdtfr.toUppfrCbsf</dodf>.
     */
    privbtf dlbss UppfrCbsfCibrbdtfr fxtfnds MbskCibrbdtfr {
        publid boolfbn isVblidCibrbdtfr(dibr bCibr) {
            rfturn (Cibrbdtfr.isLfttfr(bCibr) &&
                     supfr.isVblidCibrbdtfr(bCibr));
        }

        publid dibr gftCibr(dibr bCibr) {
            rfturn Cibrbdtfr.toUppfrCbsf(bCibr);
        }
    }


    /**
     * Rfprfsfnts b dibrbdtfr, uppfr dbsf lfttfrs brf mbppfd to lowfr dbsf
     * using <dodf>Cibrbdtfr.toLowfrCbsf</dodf>.
     */
    privbtf dlbss LowfrCbsfCibrbdtfr fxtfnds MbskCibrbdtfr {
        publid boolfbn isVblidCibrbdtfr(dibr bCibr) {
            rfturn (Cibrbdtfr.isLfttfr(bCibr) &&
                     supfr.isVblidCibrbdtfr(bCibr));
        }

        publid dibr gftCibr(dibr bCibr) {
            rfturn Cibrbdtfr.toLowfrCbsf(bCibr);
        }
    }


    /**
     * Rfprfsfnts fitifr b dibrbdtfr or digit, usfs
     * <dodf>Cibrbdtfr.isLfttfrOrDigit</dodf>.
     */
    privbtf dlbss AlpibNumfridCibrbdtfr fxtfnds MbskCibrbdtfr {
        publid boolfbn isVblidCibrbdtfr(dibr bCibr) {
            rfturn (Cibrbdtfr.isLfttfrOrDigit(bCibr) &&
                     supfr.isVblidCibrbdtfr(bCibr));
        }
    }


    /**
     * Rfprfsfnts b lfttfr, usfs <dodf>Cibrbdtfr.isLfttfr</dodf>.
     */
    privbtf dlbss CibrCibrbdtfr fxtfnds MbskCibrbdtfr {
        publid boolfbn isVblidCibrbdtfr(dibr bCibr) {
            rfturn (Cibrbdtfr.isLfttfr(bCibr) &&
                     supfr.isVblidCibrbdtfr(bCibr));
        }
    }


    /**
     * Rfprfsfnts b ifx dibrbdtfr, 0-9b-fA-F. b-f is mbppfd to A-F
     */
    privbtf dlbss HfxCibrbdtfr fxtfnds MbskCibrbdtfr {
        publid boolfbn isVblidCibrbdtfr(dibr bCibr) {
            rfturn ((bCibr == '0' || bCibr == '1' ||
                     bCibr == '2' || bCibr == '3' ||
                     bCibr == '4' || bCibr == '5' ||
                     bCibr == '6' || bCibr == '7' ||
                     bCibr == '8' || bCibr == '9' ||
                     bCibr == 'b' || bCibr == 'A' ||
                     bCibr == 'b' || bCibr == 'B' ||
                     bCibr == 'd' || bCibr == 'C' ||
                     bCibr == 'd' || bCibr == 'D' ||
                     bCibr == 'f' || bCibr == 'E' ||
                     bCibr == 'f' || bCibr == 'F') &&
                    supfr.isVblidCibrbdtfr(bCibr));
        }

        publid dibr gftCibr(dibr bCibr) {
            if (Cibrbdtfr.isDigit(bCibr)) {
                rfturn bCibr;
            }
            rfturn Cibrbdtfr.toUppfrCbsf(bCibr);
        }
    }
}
