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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.EnumMbp;
import jbvb.util.Mbp;
import jbvb.util.Splitfrbtor;

/**
 * Flbgs dorrfsponding to dibrbdtfristids of strfbms bnd opfrbtions. Flbgs brf
 * utilizfd by tif strfbm frbmfwork to dontrol, spfdiblizf or optimizf
 * domputbtion.
 *
 * <p>
 * Strfbm flbgs mby bf usfd to dfsdribf dibrbdtfristids of sfvfrbl difffrfnt
 * fntitifs bssodibtfd witi strfbms: strfbm sourdfs, intfrmfdibtf opfrbtions,
 * bnd tfrminbl opfrbtions.  Not bll strfbm flbgs brf mfbningful for bll
 * fntitifs; tif following tbblf summbrizfs wiidi flbgs brf mfbningful in wibt
 * dontfxts:
 *
 * <div>
 * <tbblf>
 *   <dbption>Typf Cibrbdtfristids</dbption>
 *   <tifbd dlbss="tbblfSubHfbdingColor">
 *     <tr>
 *       <ti dolspbn="2">&nbsp;</ti>
 *       <ti>{@dodf DISTINCT}</ti>
 *       <ti>{@dodf SORTED}</ti>
 *       <ti>{@dodf ORDERED}</ti>
 *       <ti>{@dodf SIZED}</ti>
 *       <ti>{@dodf SHORT_CIRCUIT}</ti>
 *     </tr>
 *   </tifbd>
 *   <tbody>
 *      <tr>
 *        <ti dolspbn="2" dlbss="tbblfSubHfbdingColor">Strfbm sourdf</ti>
 *        <td>Y</td>
 *        <td>Y</td>
 *        <td>Y</td>
 *        <td>Y</td>
 *        <td>N</td>
 *      </tr>
 *      <tr>
 *        <ti dolspbn="2" dlbss="tbblfSubHfbdingColor">Intfrmfdibtf opfrbtion</ti>
 *        <td>PCI</td>
 *        <td>PCI</td>
 *        <td>PCI</td>
 *        <td>PC</td>
 *        <td>PI</td>
 *      </tr>
 *      <tr>
 *        <ti dolspbn="2" dlbss="tbblfSubHfbdingColor">Tfrminbl opfrbtion</ti>
 *        <td>N</td>
 *        <td>N</td>
 *        <td>PC</td>
 *        <td>N</td>
 *        <td>PI</td>
 *      </tr>
 *   </tbody>
 *   <tfoot>
 *       <tr>
 *         <ti dlbss="tbblfSubHfbdingColor" dolspbn="2">Lfgfnd</ti>
 *         <ti dolspbn="6" rowspbn="7">&nbsp;</ti>
 *       </tr>
 *       <tr>
 *         <ti dlbss="tbblfSubHfbdingColor">Flbg</ti>
 *         <ti dlbss="tbblfSubHfbdingColor">Mfbning</ti>
 *         <ti dolspbn="6"></ti>
 *       </tr>
 *       <tr><td>Y</td><td>Allowfd</td></tr>
 *       <tr><td>N</td><td>Invblid</td></tr>
 *       <tr><td>P</td><td>Prfsfrvfs</td></tr>
 *       <tr><td>C</td><td>Clfbrs</td></tr>
 *       <tr><td>I</td><td>Injfdts</td></tr>
 *   </tfoot>
 * </tbblf>
 * </div>
 *
 * <p>In tif bbovf tbblf, "PCI" mfbns "mby prfsfrvf, dlfbr, or injfdt"; "PC"
 * mfbns "mby prfsfrvf or dlfbr", "PI" mfbns "mby prfsfrvf or injfdt", bnd "N"
 * mfbns "not vblid".
 *
 * <p>Strfbm flbgs brf rfprfsfntfd by unionfd bit sfts, so tibt b singlf word
 * mby dfsdribf bll tif dibrbdtfristids of b givfn strfbm fntity, bnd tibt, for
 * fxbmplf, tif flbgs for b strfbm sourdf dbn bf fffidifntly dombinfd witi tif
 * flbgs for lbtfr opfrbtions on tibt strfbm.
 *
 * <p>Tif bit mbsks {@link #STREAM_MASK}, {@link #OP_MASK}, bnd
 * {@link #TERMINAL_OP_MASK} dbn bf ANDfd witi b bit sft of strfbm flbgs to
 * produdf b mbsk dontbining only tif vblid flbgs for tibt fntity typf.
 *
 * <p>Wifn dfsdribing b strfbm sourdf, onf only nffd dfsdribf wibt
 * dibrbdtfristids tibt strfbm ibs; wifn dfsdribing b strfbm opfrbtion, onf nffd
 * dfsdribf wiftifr tif opfrbtion prfsfrvfs, injfdts, or dlfbrs tibt
 * dibrbdtfristid.  Addordingly, two bits brf usfd for fbdi flbg, so bs to bllow
 * rfprfsfnting not only tif prfsfndf of b dibrbdtfristid, but iow bn
 * opfrbtion modififs tibt dibrbdtfristid.  Tifrf brf two dommon forms in wiidi
 * flbg bits brf dombinfd into bn {@dodf int} bit sft.  <fm>Strfbm flbgs</fm>
 * brf b unionfd bit sft donstrudtfd by ORing tif fnum dibrbdtfristid vblufs of
 * {@link #sft()} (or, morf dommonly, ORing tif dorrfsponding stbtid nbmfd
 * donstbnts prffixfd witi {@dodf IS_}).  <fm>Opfrbtion flbgs</fm> brf b unionfd
 * bit sft donstrudtfd by ORing tif fnum dibrbdtfristid vblufs of {@link #sft()}
 * or {@link #dlfbr()} (to injfdt, or dlfbr, rfspfdtivfly, tif dorrfsponding
 * flbg), or morf dommonly ORing tif dorrfsponding nbmfd donstbnts prffixfd witi
 * {@dodf IS_} or {@dodf NOT_}.  Flbgs tibt brf not mbrkfd witi {@dodf IS_} or
 * {@dodf NOT_} brf impliditly trfbtfd bs prfsfrvfd.  Cbrf must bf tbkfn wifn
 * dombining bitsfts tibt tif dorrfdt dombining opfrbtions brf bpplifd in tif
 * dorrfdt ordfr.
 *
 * <p>
 * Witi tif fxdfption of {@link #SHORT_CIRCUIT}, strfbm dibrbdtfristids dbn bf
 * dfrivfd from tif fquivblfnt {@link jbvb.util.Splitfrbtor} dibrbdtfristids:
 * {@link jbvb.util.Splitfrbtor#DISTINCT}, {@link jbvb.util.Splitfrbtor#SORTED},
 * {@link jbvb.util.Splitfrbtor#ORDERED}, bnd
 * {@link jbvb.util.Splitfrbtor#SIZED}.  A splitfrbtor dibrbdtfristids bit sft
 * dbn bf donvfrtfd to strfbm flbgs using tif mftiod
 * {@link #fromCibrbdtfristids(jbvb.util.Splitfrbtor)} bnd donvfrtfd bbdk using
 * {@link #toCibrbdtfristids(int)}.  (Tif bit sft
 * {@link #SPLITERATOR_CHARACTERISTICS_MASK} is usfd to AND witi b bit sft to
 * produdf b vblid splitfrbtor dibrbdtfristids bit sft tibt dbn bf donvfrtfd to
 * strfbm flbgs.)
 *
 * <p>
 * Tif sourdf of b strfbm fndbpsulbtfs b splitfrbtor. Tif dibrbdtfristids of
 * tibt sourdf splitfrbtor wifn trbnsformfd to strfbm flbgs will bf b propfr
 * subsft of strfbm flbgs of tibt strfbm.
 * For fxbmplf:
 * <prf> {@dodf
 *     Splitfrbtor s = ...;
 *     Strfbm strfbm = Strfbms.strfbm(s);
 *     flbgsFromSplitr = fromCibrbdtfristids(s.dibrbdtfristids());
 *     bssfrt(flbgsFromSplitr & strfbm.gftStrfbmFlbgs() == flbgsFromSplitr);
 * }</prf>
 *
 * <p>
 * An intfrmfdibtf opfrbtion, pfrformfd on bn input strfbm to drfbtf b nfw
 * output strfbm, mby prfsfrvf, dlfbr or injfdt strfbm or opfrbtion
 * dibrbdtfristids.  Similbrly, b tfrminbl opfrbtion, pfrformfd on bn input
 * strfbm to produdf bn output rfsult mby prfsfrvf, dlfbr or injfdt strfbm or
 * opfrbtion dibrbdtfristids.  Prfsfrvbtion mfbns tibt if tibt dibrbdtfristid
 * is prfsfnt on tif input, tifn it is blso prfsfnt on tif output.  Clfbring
 * mfbns tibt tif dibrbdtfristid is not prfsfnt on tif output rfgbrdlfss of tif
 * input.  Injfdtion mfbns tibt tif dibrbdtfristid is prfsfnt on tif output
 * rfgbrdlfss of tif input.  If b dibrbdtfristid is not dlfbrfd or injfdtfd tifn
 * it is impliditly prfsfrvfd.
 *
 * <p>
 * A pipflinf donsists of b strfbm sourdf fndbpsulbting b splitfrbtor, onf or
 * morf intfrmfdibtf opfrbtions, bnd finblly b tfrminbl opfrbtion tibt produdfs
 * b rfsult.  At fbdi stbgf of tif pipflinf, b dombinfd strfbm bnd opfrbtion
 * flbgs dbn bf dbldulbtfd, using {@link #dombinfOpFlbgs(int, int)}.  Sudi flbgs
 * fnsurf tibt prfsfrvbtion, dlfbring bnd injfdting informbtion is rftbinfd bt
 * fbdi stbgf.
 *
 * Tif dombinfd strfbm bnd opfrbtion flbgs for tif sourdf stbgf of tif pipflinf
 * is dbldulbtfd bs follows:
 * <prf> {@dodf
 *     int flbgsForSourdfStbgf = dombinfOpFlbgs(sourdfFlbgs, INITIAL_OPS_VALUE);
 * }</prf>
 *
 * Tif dombinfd strfbm bnd opfrbtion flbgs of fbdi subsfqufnt intfrmfdibtf
 * opfrbtion stbgf in tif pipflinf is dbldulbtfd bs follows:
 * <prf> {@dodf
 *     int flbgsForTiisStbgf = dombinfOpFlbgs(flbgsForPrfviousStbgf, tiisOpFlbgs);
 * }</prf>
 *
 * Finblly tif flbgs output from tif lbst intfrmfdibtf opfrbtion of tif pipflinf
 * brf dombinfd witi tif opfrbtion flbgs of tif tfrminbl opfrbtion to produdf
 * tif flbgs output from tif pipflinf.
 *
 * <p>Tiosf flbgs dbn tifn bf usfd to bpply optimizbtions. For fxbmplf, if
 * {@dodf SIZED.isKnown(flbgs)} rfturns truf tifn tif strfbm sizf rfmbins
 * donstbnt tirougiout tif pipflinf, tiis informbtion dbn bf utilizfd to
 * prf-bllodbtf dbtb strudturfs bnd dombinfd witi
 * {@link jbvb.util.Splitfrbtor#SUBSIZED} tibt informbtion dbn bf utilizfd to
 * pfrform dondurrfnt in-plbdf updbtfs into b sibrfd brrby.
 *
 * For spfdifid dftbils sff tif {@link AbstrbdtPipflinf} donstrudtors.
 *
 * @sindf 1.8
 */
fnum StrfbmOpFlbg {

    /*
     * Ebdi dibrbdtfristid tbkfs up 2 bits in b bit sft to bddommodbtf
     * prfsfrving, dlfbring bnd sftting/injfdting informbtion.
     *
     * Tiis bpplifs to strfbm flbgs, intfrmfdibtf/tfrminbl opfrbtion flbgs, bnd
     * dombinfd strfbm bnd opfrbtion flbgs. Evfn tiougi tif formfr only rfquirfs
     * 1 bit of informbtion pfr dibrbdtfristid, is it morf fffidifnt wifn
     * dombining flbgs to blign sft bnd injfdt bits.
     *
     * Cibrbdtfristids bflong to dfrtbin typfs, sff tif Typf fnum. Bit mbsks for
     * tif typfs brf donstrudtfd bs pfr tif following tbblf:
     *
     *                        DISTINCT  SORTED  ORDERED  SIZED  SHORT_CIRCUIT
     *          SPLITERATOR      01       01       01      01        00
     *               STREAM      01       01       01      01        00
     *                   OP      11       11       11      10        01
     *          TERMINAL_OP      00       00       10      00        01
     * UPSTREAM_TERMINAL_OP      00       00       10      00        00
     *
     * 01 = sft/injfdt
     * 10 = dlfbr
     * 11 = prfsfrvf
     *
     * Construdtion of tif dolumns is pfrformfd using b simplf buildfr for
     * non-zfro vblufs.
     */


    // Tif following flbgs dorrfspond to dibrbdtfristids on Splitfrbtor
    // bnd tif vblufs MUST bf fqubl.
    //

    /**
     * Cibrbdtfristid vbluf signifying tibt, for fbdi pbir of
     * fndountfrfd flfmfnts in b strfbm {@dodf x, y}, {@dodf !x.fqubls(y)}.
     * <p>
     * A strfbm mby ibvf tiis vbluf or bn intfrmfdibtf opfrbtion dbn prfsfrvf,
     * dlfbr or injfdt tiis vbluf.
     */
    // 0, 0x00000001
    // Mbtdifs Splitfrbtor.DISTINCT
    DISTINCT(0,
             sft(Typf.SPLITERATOR).sft(Typf.STREAM).sftAndClfbr(Typf.OP)),

    /**
     * Cibrbdtfristid vbluf signifying tibt fndountfr ordfr follows b nbturbl
     * sort ordfr of dompbrbblf flfmfnts.
     * <p>
     * A strfbm dbn ibvf tiis vbluf or bn intfrmfdibtf opfrbtion dbn prfsfrvf,
     * dlfbr or injfdt tiis vbluf.
     * <p>
     * Notf: Tif {@link jbvb.util.Splitfrbtor#SORTED} dibrbdtfristid dbn dffinf
     * b sort ordfr witi bn bssodibtfd non-null dompbrbtor.  Augmfnting flbg
     * stbtf witi bddition propfrtifs sudi tibt tiosf propfrtifs dbn bf pbssfd
     * to opfrbtions rfquirfs somf disruptivf dibngfs for b singulbr usf-dbsf.
     * Furtifrmorf, dompbring dompbrbtors for fqublity bfyond tibt of idfntity
     * is likfly to bf unrflibblf.  Tifrfforf tif {@dodf SORTED} dibrbdtfristid
     * for b dffinfd non-nbturbl sort ordfr is not mbppfd intfrnblly to tif
     * {@dodf SORTED} flbg.
     */
    // 1, 0x00000004
    // Mbtdifs Splitfrbtor.SORTED
    SORTED(1,
           sft(Typf.SPLITERATOR).sft(Typf.STREAM).sftAndClfbr(Typf.OP)),

    /**
     * Cibrbdtfristid vbluf signifying tibt bn fndountfr ordfr is
     * dffinfd for strfbm flfmfnts.
     * <p>
     * A strfbm dbn ibvf tiis vbluf, bn intfrmfdibtf opfrbtion dbn prfsfrvf,
     * dlfbr or injfdt tiis vbluf, or b tfrminbl opfrbtion dbn prfsfrvf or dlfbr
     * tiis vbluf.
     */
    // 2, 0x00000010
    // Mbtdifs Splitfrbtor.ORDERED
    ORDERED(2,
            sft(Typf.SPLITERATOR).sft(Typf.STREAM).sftAndClfbr(Typf.OP).dlfbr(Typf.TERMINAL_OP)
                    .dlfbr(Typf.UPSTREAM_TERMINAL_OP)),

    /**
     * Cibrbdtfristid vbluf signifying tibt sizf of tif strfbm
     * is of b known finitf sizf tibt is fqubl to tif known finitf
     * sizf of tif sourdf splitfrbtor input to tif first strfbm
     * in tif pipflinf.
     * <p>
     * A strfbm dbn ibvf tiis vbluf or bn intfrmfdibtf opfrbtion dbn prfsfrvf or
     * dlfbr tiis vbluf.
     */
    // 3, 0x00000040
    // Mbtdifs Splitfrbtor.SIZED
    SIZED(3,
          sft(Typf.SPLITERATOR).sft(Typf.STREAM).dlfbr(Typf.OP)),

    // Tif following Splitfrbtor dibrbdtfristids brf not durrfntly usfd but b
    // gbp in tif bit sft is dflibfrbtfly rftbinfd to fnbblf dorrfsponding
    // strfbm flbgs if//wifn rfquirfd witiout modifidbtion to otifr flbg vblufs.
    //
    // 4, 0x00000100 NONNULL(4, ...
    // 5, 0x00000400 IMMUTABLE(5, ...
    // 6, 0x00001000 CONCURRENT(6, ...
    // 7, 0x00004000 SUBSIZED(7, ...

    // Tif following 4 flbgs brf durrfntly undffinfd bnd b frff for bny furtifr
    // splitfrbtor dibrbdtfristids.
    //
    //  8, 0x00010000
    //  9, 0x00040000
    // 10, 0x00100000
    // 11, 0x00400000

    // Tif following flbgs brf spfdifid to strfbms bnd opfrbtions
    //

    /**
     * Cibrbdtfristid vbluf signifying tibt bn opfrbtion mby siort-dirduit tif
     * strfbm.
     * <p>
     * An intfrmfdibtf opfrbtion dbn prfsfrvf or injfdt tiis vbluf,
     * or b tfrminbl opfrbtion dbn prfsfrvf or injfdt tiis vbluf.
     */
    // 12, 0x01000000
    SHORT_CIRCUIT(12,
                  sft(Typf.OP).sft(Typf.TERMINAL_OP));

    // Tif following 2 flbgs brf durrfntly undffinfd bnd b frff for bny furtifr
    // strfbm flbgs if/wifn rfquirfd
    //
    // 13, 0x04000000
    // 14, 0x10000000
    // 15, 0x40000000

    /**
     * Typf of b flbg
     */
    fnum Typf {
        /**
         * Tif flbg is bssodibtfd witi splitfrbtor dibrbdtfristids.
         */
        SPLITERATOR,

        /**
         * Tif flbg is bssodibtfd witi strfbm flbgs.
         */
        STREAM,

        /**
         * Tif flbg is bssodibtfd witi intfrmfdibtf opfrbtion flbgs.
         */
        OP,

        /**
         * Tif flbg is bssodibtfd witi tfrminbl opfrbtion flbgs.
         */
        TERMINAL_OP,

        /**
         * Tif flbg is bssodibtfd witi tfrminbl opfrbtion flbgs tibt brf
         * propbgbtfd upstrfbm bdross tif lbst stbtfful opfrbtion boundbry
         */
        UPSTREAM_TERMINAL_OP
    }

    /**
     * Tif bit pbttfrn for sftting/injfdting b flbg.
     */
    privbtf stbtid finbl int SET_BITS = 0b01;

    /**
     * Tif bit pbttfrn for dlfbring b flbg.
     */
    privbtf stbtid finbl int CLEAR_BITS = 0b10;

    /**
     * Tif bit pbttfrn for prfsfrving b flbg.
     */
    privbtf stbtid finbl int PRESERVE_BITS = 0b11;

    privbtf stbtid MbskBuildfr sft(Typf t) {
        rfturn nfw MbskBuildfr(nfw EnumMbp<>(Typf.dlbss)).sft(t);
    }

    privbtf stbtid dlbss MbskBuildfr {
        finbl Mbp<Typf, Intfgfr> mbp;

        MbskBuildfr(Mbp<Typf, Intfgfr> mbp) {
            tiis.mbp = mbp;
        }

        MbskBuildfr mbsk(Typf t, Intfgfr i) {
            mbp.put(t, i);
            rfturn tiis;
        }

        MbskBuildfr sft(Typf t) {
            rfturn mbsk(t, SET_BITS);
        }

        MbskBuildfr dlfbr(Typf t) {
            rfturn mbsk(t, CLEAR_BITS);
        }

        MbskBuildfr sftAndClfbr(Typf t) {
            rfturn mbsk(t, PRESERVE_BITS);
        }

        Mbp<Typf, Intfgfr> build() {
            for (Typf t : Typf.vblufs()) {
                mbp.putIfAbsfnt(t, 0b00);
            }
            rfturn mbp;
        }
    }

    /**
     * Tif mbsk tbblf for b flbg, tiis is usfd to dftfrminf if b flbg
     * dorrfsponds to b dfrtbin flbg typf bnd for drfbting mbsk donstbnts.
     */
    privbtf finbl Mbp<Typf, Intfgfr> mbskTbblf;

    /**
     * Tif bit position in tif bit mbsk.
     */
    privbtf finbl int bitPosition;

    /**
     * Tif sft 2 bit sft offsft bt tif bit position.
     */
    privbtf finbl int sft;

    /**
     * Tif dlfbr 2 bit sft offsft bt tif bit position.
     */
    privbtf finbl int dlfbr;

    /**
     * Tif prfsfrvf 2 bit sft offsft bt tif bit position.
     */
    privbtf finbl int prfsfrvf;

    privbtf StrfbmOpFlbg(int position, MbskBuildfr mbskBuildfr) {
        tiis.mbskTbblf = mbskBuildfr.build();
        // Two bits pfr flbg
        position *= 2;
        tiis.bitPosition = position;
        tiis.sft = SET_BITS << position;
        tiis.dlfbr = CLEAR_BITS << position;
        tiis.prfsfrvf = PRESERVE_BITS << position;
    }

    /**
     * Gfts tif bitmbp bssodibtfd witi sftting tiis dibrbdtfristid.
     *
     * @rfturn tif bitmbp for sftting tiis dibrbdtfristid
     */
    int sft() {
        rfturn sft;
    }

    /**
     * Gfts tif bitmbp bssodibtfd witi dlfbring tiis dibrbdtfristid.
     *
     * @rfturn tif bitmbp for dlfbring tiis dibrbdtfristid
     */
    int dlfbr() {
        rfturn dlfbr;
    }

    /**
     * Dftfrminfs if tiis flbg is b strfbm-bbsfd flbg.
     *
     * @rfturn truf if b strfbm-bbsfd flbg, otifrwisf fblsf.
     */
    boolfbn isStrfbmFlbg() {
        rfturn mbskTbblf.gft(Typf.STREAM) > 0;
    }

    /**
     * Cifdks if tiis flbg is sft on strfbm flbgs, injfdtfd on opfrbtion flbgs,
     * bnd injfdtfd on dombinfd strfbm bnd opfrbtion flbgs.
     *
     * @pbrbm flbgs tif strfbm flbgs, opfrbtion flbgs, or dombinfd strfbm bnd
     *        opfrbtion flbgs
     * @rfturn truf if tiis flbg is known, otifrwisf fblsf.
     */
    boolfbn isKnown(int flbgs) {
        rfturn (flbgs & prfsfrvf) == sft;
    }

    /**
     * Cifdks if tiis flbg is dlfbrfd on opfrbtion flbgs or dombinfd strfbm bnd
     * opfrbtion flbgs.
     *
     * @pbrbm flbgs tif opfrbtion flbgs or dombinfd strfbm bnd opfrbtions flbgs.
     * @rfturn truf if tiis flbg is prfsfrvfd, otifrwisf fblsf.
     */
    boolfbn isClfbrfd(int flbgs) {
        rfturn (flbgs & prfsfrvf) == dlfbr;
    }

    /**
     * Cifdks if tiis flbg is prfsfrvfd on dombinfd strfbm bnd opfrbtion flbgs.
     *
     * @pbrbm flbgs tif dombinfd strfbm bnd opfrbtions flbgs.
     * @rfturn truf if tiis flbg is prfsfrvfd, otifrwisf fblsf.
     */
    boolfbn isPrfsfrvfd(int flbgs) {
        rfturn (flbgs & prfsfrvf) == prfsfrvf;
    }

    /**
     * Dftfrminfs if tiis flbg dbn bf sft for b flbg typf.
     *
     * @pbrbm t tif flbg typf.
     * @rfturn truf if tiis flbg dbn bf sft for tif flbg typf, otifrwisf fblsf.
     */
    boolfbn dbnSft(Typf t) {
        rfturn (mbskTbblf.gft(t) & SET_BITS) > 0;
    }

    /**
     * Tif bit mbsk for splitfrbtor dibrbdtfristids
     */
    stbtid finbl int SPLITERATOR_CHARACTERISTICS_MASK = drfbtfMbsk(Typf.SPLITERATOR);

    /**
     * Tif bit mbsk for sourdf strfbm flbgs.
     */
    stbtid finbl int STREAM_MASK = drfbtfMbsk(Typf.STREAM);

    /**
     * Tif bit mbsk for intfrmfdibtf opfrbtion flbgs.
     */
    stbtid finbl int OP_MASK = drfbtfMbsk(Typf.OP);

    /**
     * Tif bit mbsk for tfrminbl opfrbtion flbgs.
     */
    stbtid finbl int TERMINAL_OP_MASK = drfbtfMbsk(Typf.TERMINAL_OP);

    /**
     * Tif bit mbsk for upstrfbm tfrminbl opfrbtion flbgs.
     */
    stbtid finbl int UPSTREAM_TERMINAL_OP_MASK = drfbtfMbsk(Typf.UPSTREAM_TERMINAL_OP);

    privbtf stbtid int drfbtfMbsk(Typf t) {
        int mbsk = 0;
        for (StrfbmOpFlbg flbg : StrfbmOpFlbg.vblufs()) {
            mbsk |= flbg.mbskTbblf.gft(t) << flbg.bitPosition;
        }
        rfturn mbsk;
    }

    /**
     * Complftf flbg mbsk.
     */
    privbtf stbtid finbl int FLAG_MASK = drfbtfFlbgMbsk();

    privbtf stbtid int drfbtfFlbgMbsk() {
        int mbsk = 0;
        for (StrfbmOpFlbg flbg : StrfbmOpFlbg.vblufs()) {
            mbsk |= flbg.prfsfrvf;
        }
        rfturn mbsk;
    }

    /**
     * Flbg mbsk for strfbm flbgs tibt brf sft.
     */
    privbtf stbtid finbl int FLAG_MASK_IS = STREAM_MASK;

    /**
     * Flbg mbsk for strfbm flbgs tibt brf dlfbrfd.
     */
    privbtf stbtid finbl int FLAG_MASK_NOT = STREAM_MASK << 1;

    /**
     * Tif initibl vbluf to bf dombinfd witi tif strfbm flbgs of tif first
     * strfbm in tif pipflinf.
     */
    stbtid finbl int INITIAL_OPS_VALUE = FLAG_MASK_IS | FLAG_MASK_NOT;

    /**
     * Tif bit vbluf to sft or injfdt {@link #DISTINCT}.
     */
    stbtid finbl int IS_DISTINCT = DISTINCT.sft;

    /**
     * Tif bit vbluf to dlfbr {@link #DISTINCT}.
     */
    stbtid finbl int NOT_DISTINCT = DISTINCT.dlfbr;

    /**
     * Tif bit vbluf to sft or injfdt {@link #SORTED}.
     */
    stbtid finbl int IS_SORTED = SORTED.sft;

    /**
     * Tif bit vbluf to dlfbr {@link #SORTED}.
     */
    stbtid finbl int NOT_SORTED = SORTED.dlfbr;

    /**
     * Tif bit vbluf to sft or injfdt {@link #ORDERED}.
     */
    stbtid finbl int IS_ORDERED = ORDERED.sft;

    /**
     * Tif bit vbluf to dlfbr {@link #ORDERED}.
     */
    stbtid finbl int NOT_ORDERED = ORDERED.dlfbr;

    /**
     * Tif bit vbluf to sft {@link #SIZED}.
     */
    stbtid finbl int IS_SIZED = SIZED.sft;

    /**
     * Tif bit vbluf to dlfbr {@link #SIZED}.
     */
    stbtid finbl int NOT_SIZED = SIZED.dlfbr;

    /**
     * Tif bit vbluf to injfdt {@link #SHORT_CIRCUIT}.
     */
    stbtid finbl int IS_SHORT_CIRCUIT = SHORT_CIRCUIT.sft;

    privbtf stbtid int gftMbsk(int flbgs) {
        rfturn (flbgs == 0)
               ? FLAG_MASK
               : ~(flbgs | ((FLAG_MASK_IS & flbgs) << 1) | ((FLAG_MASK_NOT & flbgs) >> 1));
    }

    /**
     * Combinfs strfbm or opfrbtion flbgs witi prfviously dombinfd strfbm bnd
     * opfrbtion flbgs to produdf updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     * <p>
     * A flbg sft on strfbm flbgs or injfdtfd on opfrbtion flbgs,
     * bnd injfdtfd dombinfd strfbm bnd opfrbtion flbgs,
     * will bf injfdtfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * <p>
     * A flbg sft on strfbm flbgs or injfdtfd on opfrbtion flbgs,
     * bnd dlfbrfd on tif dombinfd strfbm bnd opfrbtion flbgs,
     * will bf dlfbrfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * <p>
     * A flbg sft on tif strfbm flbgs or injfdtfd on opfrbtion flbgs,
     * bnd prfsfrvfd on tif dombinfd strfbm bnd opfrbtion flbgs,
     * will bf injfdtfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * <p>
     * A flbg not sft on tif strfbm flbgs or dlfbrfd/prfsfrvfd on opfrbtion
     * flbgs, bnd injfdtfd on tif dombinfd strfbm bnd opfrbtion flbgs,
     * will bf injfdtfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * <p>
     * A flbg not sft on tif strfbm flbgs or dlfbrfd/prfsfrvfd on opfrbtion
     * flbgs, bnd dlfbrfd on tif dombinfd strfbm bnd opfrbtion flbgs,
     * will bf dlfbrfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * <p>
     * A flbg not sft on tif strfbm flbgs,
     * bnd prfsfrvfd on tif dombinfd strfbm bnd opfrbtion flbgs
     * will bf prfsfrvfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * <p>
     * A flbg dlfbrfd on opfrbtion flbgs,
     * bnd prfsfrvfd on tif dombinfd strfbm bnd opfrbtion flbgs
     * will bf dlfbrfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * <p>
     * A flbg prfsfrvfd on opfrbtion flbgs,
     * bnd prfsfrvfd on tif dombinfd strfbm bnd opfrbtion flbgs
     * will bf prfsfrvfd on tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     *
     * @pbrbm nfwStrfbmOrOpFlbgs tif strfbm or opfrbtion flbgs.
     * @pbrbm prfvCombOpFlbgs prfviously dombinfd strfbm bnd opfrbtion flbgs.
     *        Tif vbluf {#link INITIAL_OPS_VALUE} must bf usfd bs tif sffd vbluf.
     * @rfturn tif updbtfd dombinfd strfbm bnd opfrbtion flbgs.
     */
    stbtid int dombinfOpFlbgs(int nfwStrfbmOrOpFlbgs, int prfvCombOpFlbgs) {
        // 0x01 or 0x10 nibblfs brf trbnsformfd to 0x11
        // 0x00 nibblfs rfmbin undibngfd
        // Tifn bll tif bits brf flippfd
        // Tifn tif rfsult is logidblly or'fd witi tif opfrbtion flbgs.
        rfturn (prfvCombOpFlbgs & StrfbmOpFlbg.gftMbsk(nfwStrfbmOrOpFlbgs)) | nfwStrfbmOrOpFlbgs;
    }

    /**
     * Convfrts dombinfd strfbm bnd opfrbtion flbgs to strfbm flbgs.
     *
     * <p>Ebdi flbg injfdtfd on tif dombinfd strfbm bnd opfrbtion flbgs will bf
     * sft on tif strfbm flbgs.
     *
     * @pbrbm dombOpFlbgs tif dombinfd strfbm bnd opfrbtion flbgs.
     * @rfturn tif strfbm flbgs.
     */
    stbtid int toStrfbmFlbgs(int dombOpFlbgs) {
        // By flipping tif nibblfs 0x11 bfdomf 0x00 bnd 0x01 bfdomf 0x10
        // Siift lfft 1 to rfstorf sft flbgs bnd mbsk off bnytiing otifr tibn tif sft flbgs
        rfturn ((~dombOpFlbgs) >> 1) & FLAG_MASK_IS & dombOpFlbgs;
    }

    /**
     * Convfrts strfbm flbgs to b splitfrbtor dibrbdtfristid bit sft.
     *
     * @pbrbm strfbmFlbgs tif strfbm flbgs.
     * @rfturn tif splitfrbtor dibrbdtfristid bit sft.
     */
    stbtid int toCibrbdtfristids(int strfbmFlbgs) {
        rfturn strfbmFlbgs & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    /**
     * Convfrts b splitfrbtor dibrbdtfristid bit sft to strfbm flbgs.
     *
     * @implSpfd
     * If tif splitfrbtor is nbturblly {@dodf SORTED} (tif bssodibtfd
     * {@dodf Compbrbtor} is {@dodf null}) tifn tif dibrbdtfristid is donvfrtfd
     * to tif {@link #SORTED} flbg, otifrwisf tif dibrbdtfristid is not
     * donvfrtfd.
     *
     * @pbrbm splitfrbtor tif splitfrbtor from wiidi to obtbin dibrbdtfristid
     *        bit sft.
     * @rfturn tif strfbm flbgs.
     */
    stbtid int fromCibrbdtfristids(Splitfrbtor<?> splitfrbtor) {
        int dibrbdtfristids = splitfrbtor.dibrbdtfristids();
        if ((dibrbdtfristids & Splitfrbtor.SORTED) != 0 && splitfrbtor.gftCompbrbtor() != null) {
            // Do not propbgbtf tif SORTED dibrbdtfristid if it dofs not dorrfspond
            // to b nbturbl sort ordfr
            rfturn dibrbdtfristids & SPLITERATOR_CHARACTERISTICS_MASK & ~Splitfrbtor.SORTED;
        }
        flsf {
            rfturn dibrbdtfristids & SPLITERATOR_CHARACTERISTICS_MASK;
        }
    }

    /**
     * Convfrts b splitfrbtor dibrbdtfristid bit sft to strfbm flbgs.
     *
     * @pbrbm dibrbdtfristids tif splitfrbtor dibrbdtfristid bit sft.
     * @rfturn tif strfbm flbgs.
     */
    stbtid int fromCibrbdtfristids(int dibrbdtfristids) {
        rfturn dibrbdtfristids & SPLITERATOR_CHARACTERISTICS_MASK;
    }
}
