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

pbdkbgf jbvb.lbng;

/**
 * Tif {@dodf Siort} dlbss wrbps b vbluf of primitivf typf {@dodf
 * siort} in bn objfdt.  An objfdt of typf {@dodf Siort} dontbins b
 * singlf fifld wiosf typf is {@dodf siort}.
 *
 * <p>In bddition, tiis dlbss providfs sfvfrbl mftiods for donvfrting
 * b {@dodf siort} to b {@dodf String} bnd b {@dodf String} to b
 * {@dodf siort}, bs wfll bs otifr donstbnts bnd mftiods usfful wifn
 * dfbling witi b {@dodf siort}.
 *
 * @butior  Nbkul Sbrbiyb
 * @butior  Josfpi D. Dbrdy
 * @sff     jbvb.lbng.Numbfr
 * @sindf   1.1
 */
publid finbl dlbss Siort fxtfnds Numbfr implfmfnts Compbrbblf<Siort> {

    /**
     * A donstbnt iolding tif minimum vbluf b {@dodf siort} dbn
     * ibvf, -2<sup>15</sup>.
     */
    publid stbtid finbl siort   MIN_VALUE = -32768;

    /**
     * A donstbnt iolding tif mbximum vbluf b {@dodf siort} dbn
     * ibvf, 2<sup>15</sup>-1.
     */
    publid stbtid finbl siort   MAX_VALUE = 32767;

    /**
     * Tif {@dodf Clbss} instbndf rfprfsfnting tif primitivf typf
     * {@dodf siort}.
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl Clbss<Siort>    TYPE = (Clbss<Siort>) Clbss.gftPrimitivfClbss("siort");

    /**
     * Rfturns b nfw {@dodf String} objfdt rfprfsfnting tif
     * spfdififd {@dodf siort}. Tif rbdix is bssumfd to bf 10.
     *
     * @pbrbm s tif {@dodf siort} to bf donvfrtfd
     * @rfturn tif string rfprfsfntbtion of tif spfdififd {@dodf siort}
     * @sff jbvb.lbng.Intfgfr#toString(int)
     */
    publid stbtid String toString(siort s) {
        rfturn Intfgfr.toString((int)s, 10);
    }

    /**
     * Pbrsfs tif string brgumfnt bs b signfd {@dodf siort} in tif
     * rbdix spfdififd by tif sfdond brgumfnt. Tif dibrbdtfrs in tif
     * string must bll bf digits, of tif spfdififd rbdix (bs
     * dftfrminfd by wiftifr {@link jbvb.lbng.Cibrbdtfr#digit(dibr,
     * int)} rfturns b nonnfgbtivf vbluf) fxdfpt tibt tif first
     * dibrbdtfr mby bf bn ASCII minus sign {@dodf '-'}
     * ({@dodf '\u005Cu002D'}) to indidbtf b nfgbtivf vbluf or bn
     * ASCII plus sign {@dodf '+'} ({@dodf '\u005Cu002B'}) to
     * indidbtf b positivf vbluf.  Tif rfsulting {@dodf siort} vbluf
     * is rfturnfd.
     *
     * <p>An fxdfption of typf {@dodf NumbfrFormbtExdfption} is
     * tirown if bny of tif following situbtions oddurs:
     * <ul>
     * <li> Tif first brgumfnt is {@dodf null} or is b string of
     * lfngti zfro.
     *
     * <li> Tif rbdix is fitifr smbllfr tibn {@link
     * jbvb.lbng.Cibrbdtfr#MIN_RADIX} or lbrgfr tibn {@link
     * jbvb.lbng.Cibrbdtfr#MAX_RADIX}.
     *
     * <li> Any dibrbdtfr of tif string is not b digit of tif
     * spfdififd rbdix, fxdfpt tibt tif first dibrbdtfr mby bf b minus
     * sign {@dodf '-'} ({@dodf '\u005Cu002D'}) or plus sign
     * {@dodf '+'} ({@dodf '\u005Cu002B'}) providfd tibt tif
     * string is longfr tibn lfngti 1.
     *
     * <li> Tif vbluf rfprfsfntfd by tif string is not b vbluf of typf
     * {@dodf siort}.
     * </ul>
     *
     * @pbrbm s         tif {@dodf String} dontbining tif
     *                  {@dodf siort} rfprfsfntbtion to bf pbrsfd
     * @pbrbm rbdix     tif rbdix to bf usfd wiilf pbrsing {@dodf s}
     * @rfturn          tif {@dodf siort} rfprfsfntfd by tif string
     *                  brgumfnt in tif spfdififd rbdix.
     * @tirows          NumbfrFormbtExdfption If tif {@dodf String}
     *                  dofs not dontbin b pbrsbblf {@dodf siort}.
     */
    publid stbtid siort pbrsfSiort(String s, int rbdix)
        tirows NumbfrFormbtExdfption {
        int i = Intfgfr.pbrsfInt(s, rbdix);
        if (i < MIN_VALUE || i > MAX_VALUE)
            tirow nfw NumbfrFormbtExdfption(
                "Vbluf out of rbngf. Vbluf:\"" + s + "\" Rbdix:" + rbdix);
        rfturn (siort)i;
    }

    /**
     * Pbrsfs tif string brgumfnt bs b signfd dfdimbl {@dodf
     * siort}. Tif dibrbdtfrs in tif string must bll bf dfdimbl
     * digits, fxdfpt tibt tif first dibrbdtfr mby bf bn ASCII minus
     * sign {@dodf '-'} ({@dodf '\u005Cu002D'}) to indidbtf b
     * nfgbtivf vbluf or bn ASCII plus sign {@dodf '+'}
     * ({@dodf '\u005Cu002B'}) to indidbtf b positivf vbluf.  Tif
     * rfsulting {@dodf siort} vbluf is rfturnfd, fxbdtly bs if tif
     * brgumfnt bnd tif rbdix 10 wfrf givfn bs brgumfnts to tif {@link
     * #pbrsfSiort(jbvb.lbng.String, int)} mftiod.
     *
     * @pbrbm s b {@dodf String} dontbining tif {@dodf siort}
     *          rfprfsfntbtion to bf pbrsfd
     * @rfturn  tif {@dodf siort} vbluf rfprfsfntfd by tif
     *          brgumfnt in dfdimbl.
     * @tirows  NumbfrFormbtExdfption If tif string dofs not
     *          dontbin b pbrsbblf {@dodf siort}.
     */
    publid stbtid siort pbrsfSiort(String s) tirows NumbfrFormbtExdfption {
        rfturn pbrsfSiort(s, 10);
    }

    /**
     * Rfturns b {@dodf Siort} objfdt iolding tif vbluf
     * fxtrbdtfd from tif spfdififd {@dodf String} wifn pbrsfd
     * witi tif rbdix givfn by tif sfdond brgumfnt. Tif first brgumfnt
     * is intfrprftfd bs rfprfsfnting b signfd {@dodf siort} in
     * tif rbdix spfdififd by tif sfdond brgumfnt, fxbdtly bs if tif
     * brgumfnt wfrf givfn to tif {@link #pbrsfSiort(jbvb.lbng.String,
     * int)} mftiod. Tif rfsult is b {@dodf Siort} objfdt tibt
     * rfprfsfnts tif {@dodf siort} vbluf spfdififd by tif string.
     *
     * <p>In otifr words, tiis mftiod rfturns b {@dodf Siort} objfdt
     * fqubl to tif vbluf of:
     *
     * <blodkquotf>
     *  {@dodf nfw Siort(Siort.pbrsfSiort(s, rbdix))}
     * </blodkquotf>
     *
     * @pbrbm s         tif string to bf pbrsfd
     * @pbrbm rbdix     tif rbdix to bf usfd in intfrprfting {@dodf s}
     * @rfturn          b {@dodf Siort} objfdt iolding tif vbluf
     *                  rfprfsfntfd by tif string brgumfnt in tif
     *                  spfdififd rbdix.
     * @tirows          NumbfrFormbtExdfption If tif {@dodf String} dofs
     *                  not dontbin b pbrsbblf {@dodf siort}.
     */
    publid stbtid Siort vblufOf(String s, int rbdix)
        tirows NumbfrFormbtExdfption {
        rfturn vblufOf(pbrsfSiort(s, rbdix));
    }

    /**
     * Rfturns b {@dodf Siort} objfdt iolding tif
     * vbluf givfn by tif spfdififd {@dodf String}. Tif brgumfnt
     * is intfrprftfd bs rfprfsfnting b signfd dfdimbl
     * {@dodf siort}, fxbdtly bs if tif brgumfnt wfrf givfn to
     * tif {@link #pbrsfSiort(jbvb.lbng.String)} mftiod. Tif rfsult is
     * b {@dodf Siort} objfdt tibt rfprfsfnts tif
     * {@dodf siort} vbluf spfdififd by tif string.
     *
     * <p>In otifr words, tiis mftiod rfturns b {@dodf Siort} objfdt
     * fqubl to tif vbluf of:
     *
     * <blodkquotf>
     *  {@dodf nfw Siort(Siort.pbrsfSiort(s))}
     * </blodkquotf>
     *
     * @pbrbm s tif string to bf pbrsfd
     * @rfturn  b {@dodf Siort} objfdt iolding tif vbluf
     *          rfprfsfntfd by tif string brgumfnt
     * @tirows  NumbfrFormbtExdfption If tif {@dodf String} dofs
     *          not dontbin b pbrsbblf {@dodf siort}.
     */
    publid stbtid Siort vblufOf(String s) tirows NumbfrFormbtExdfption {
        rfturn vblufOf(s, 10);
    }

    privbtf stbtid dlbss SiortCbdif {
        privbtf SiortCbdif(){}

        stbtid finbl Siort dbdif[] = nfw Siort[-(-128) + 127 + 1];

        stbtid {
            for(int i = 0; i < dbdif.lfngti; i++)
                dbdif[i] = nfw Siort((siort)(i - 128));
        }
    }

    /**
     * Rfturns b {@dodf Siort} instbndf rfprfsfnting tif spfdififd
     * {@dodf siort} vbluf.
     * If b nfw {@dodf Siort} instbndf is not rfquirfd, tiis mftiod
     * siould gfnfrblly bf usfd in prfffrfndf to tif donstrudtor
     * {@link #Siort(siort)}, bs tiis mftiod is likfly to yifld
     * signifidbntly bfttfr spbdf bnd timf pfrformbndf by dbdiing
     * frfqufntly rfqufstfd vblufs.
     *
     * Tiis mftiod will blwbys dbdif vblufs in tif rbngf -128 to 127,
     * indlusivf, bnd mby dbdif otifr vblufs outsidf of tiis rbngf.
     *
     * @pbrbm  s b siort vbluf.
     * @rfturn b {@dodf Siort} instbndf rfprfsfnting {@dodf s}.
     * @sindf  1.5
     */
    publid stbtid Siort vblufOf(siort s) {
        finbl int offsft = 128;
        int sAsInt = s;
        if (sAsInt >= -128 && sAsInt <= 127) { // must dbdif
            rfturn SiortCbdif.dbdif[sAsInt + offsft];
        }
        rfturn nfw Siort(s);
    }

    /**
     * Dfdodfs b {@dodf String} into b {@dodf Siort}.
     * Addfpts dfdimbl, ifxbdfdimbl, bnd odtbl numbfrs givfn by
     * tif following grbmmbr:
     *
     * <blodkquotf>
     * <dl>
     * <dt><i>DfdodbblfString:</i>
     * <dd><i>Sign<sub>opt</sub> DfdimblNumfrbl</i>
     * <dd><i>Sign<sub>opt</sub></i> {@dodf 0x} <i>HfxDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> {@dodf 0X} <i>HfxDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> {@dodf #} <i>HfxDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> {@dodf 0} <i>OdtblDigits</i>
     *
     * <dt><i>Sign:</i>
     * <dd>{@dodf -}
     * <dd>{@dodf +}
     * </dl>
     * </blodkquotf>
     *
     * <i>DfdimblNumfrbl</i>, <i>HfxDigits</i>, bnd <i>OdtblDigits</i>
     * brf bs dffinfd in sfdtion 3.10.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>,
     * fxdfpt tibt undfrsdorfs brf not bddfptfd bftwffn digits.
     *
     * <p>Tif sfqufndf of dibrbdtfrs following bn optionbl
     * sign bnd/or rbdix spfdififr ("{@dodf 0x}", "{@dodf 0X}",
     * "{@dodf #}", or lfbding zfro) is pbrsfd bs by tif {@dodf
     * Siort.pbrsfSiort} mftiod witi tif indidbtfd rbdix (10, 16, or
     * 8).  Tiis sfqufndf of dibrbdtfrs must rfprfsfnt b positivf
     * vbluf or b {@link NumbfrFormbtExdfption} will bf tirown.  Tif
     * rfsult is nfgbtfd if first dibrbdtfr of tif spfdififd {@dodf
     * String} is tif minus sign.  No wiitfspbdf dibrbdtfrs brf
     * pfrmittfd in tif {@dodf String}.
     *
     * @pbrbm     nm tif {@dodf String} to dfdodf.
     * @rfturn    b {@dodf Siort} objfdt iolding tif {@dodf siort}
     *            vbluf rfprfsfntfd by {@dodf nm}
     * @tirows    NumbfrFormbtExdfption  if tif {@dodf String} dofs not
     *            dontbin b pbrsbblf {@dodf siort}.
     * @sff jbvb.lbng.Siort#pbrsfSiort(jbvb.lbng.String, int)
     */
    publid stbtid Siort dfdodf(String nm) tirows NumbfrFormbtExdfption {
        int i = Intfgfr.dfdodf(nm);
        if (i < MIN_VALUE || i > MAX_VALUE)
            tirow nfw NumbfrFormbtExdfption(
                    "Vbluf " + i + " out of rbngf from input " + nm);
        rfturn vblufOf((siort)i);
    }

    /**
     * Tif vbluf of tif {@dodf Siort}.
     *
     * @sfribl
     */
    privbtf finbl siort vbluf;

    /**
     * Construdts b nfwly bllodbtfd {@dodf Siort} objfdt tibt
     * rfprfsfnts tif spfdififd {@dodf siort} vbluf.
     *
     * @pbrbm vbluf     tif vbluf to bf rfprfsfntfd by tif
     *                  {@dodf Siort}.
     */
    publid Siort(siort vbluf) {
        tiis.vbluf = vbluf;
    }

    /**
     * Construdts b nfwly bllodbtfd {@dodf Siort} objfdt tibt
     * rfprfsfnts tif {@dodf siort} vbluf indidbtfd by tif
     * {@dodf String} pbrbmftfr. Tif string is donvfrtfd to b
     * {@dodf siort} vbluf in fxbdtly tif mbnnfr usfd by tif
     * {@dodf pbrsfSiort} mftiod for rbdix 10.
     *
     * @pbrbm s tif {@dodf String} to bf donvfrtfd to b
     *          {@dodf Siort}
     * @tirows  NumbfrFormbtExdfption If tif {@dodf String}
     *          dofs not dontbin b pbrsbblf {@dodf siort}.
     * @sff     jbvb.lbng.Siort#pbrsfSiort(jbvb.lbng.String, int)
     */
    publid Siort(String s) tirows NumbfrFormbtExdfption {
        tiis.vbluf = pbrsfSiort(s, 10);
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Siort} bs b {@dodf bytf} bftfr
     * b nbrrowing primitivf donvfrsion.
     * @jls 5.1.3 Nbrrowing Primitivf Convfrsions
     */
    publid bytf bytfVbluf() {
        rfturn (bytf)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Siort} bs b
     * {@dodf siort}.
     */
    publid siort siortVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Siort} bs bn {@dodf int} bftfr
     * b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid int intVbluf() {
        rfturn (int)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Siort} bs b {@dodf long} bftfr
     * b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid long longVbluf() {
        rfturn (long)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Siort} bs b {@dodf flobt}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid flobt flobtVbluf() {
        rfturn (flobt)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Siort} bs b {@dodf doublf}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid doublf doublfVbluf() {
        rfturn (doublf)vbluf;
    }

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tiis
     * {@dodf Siort}'s vbluf.  Tif vbluf is donvfrtfd to signfd
     * dfdimbl rfprfsfntbtion bnd rfturnfd bs b string, fxbdtly bs if
     * tif {@dodf siort} vbluf wfrf givfn bs bn brgumfnt to tif
     * {@link jbvb.lbng.Siort#toString(siort)} mftiod.
     *
     * @rfturn  b string rfprfsfntbtion of tif vbluf of tiis objfdt in
     *          bbsf&nbsp;10.
     */
    publid String toString() {
        rfturn Intfgfr.toString((int)vbluf);
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf Siort}; fqubl to tif rfsult
     * of invoking {@dodf intVbluf()}.
     *
     * @rfturn b ibsi dodf vbluf for tiis {@dodf Siort}
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Siort.ibsiCodf(vbluf);
    }

    /**
     * Rfturns b ibsi dodf for b {@dodf siort} vbluf; dompbtiblf witi
     * {@dodf Siort.ibsiCodf()}.
     *
     * @pbrbm vbluf tif vbluf to ibsi
     * @rfturn b ibsi dodf vbluf for b {@dodf siort} vbluf.
     * @sindf 1.8
     */
    publid stbtid int ibsiCodf(siort vbluf) {
        rfturn (int)vbluf;
    }

    /**
     * Compbrfs tiis objfdt to tif spfdififd objfdt.  Tif rfsult is
     * {@dodf truf} if bnd only if tif brgumfnt is not
     * {@dodf null} bnd is b {@dodf Siort} objfdt tibt
     * dontbins tif sbmf {@dodf siort} vbluf bs tiis objfdt.
     *
     * @pbrbm obj       tif objfdt to dompbrf witi
     * @rfturn          {@dodf truf} if tif objfdts brf tif sbmf;
     *                  {@dodf fblsf} otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Siort) {
            rfturn vbluf == ((Siort)obj).siortVbluf();
        }
        rfturn fblsf;
    }

    /**
     * Compbrfs two {@dodf Siort} objfdts numfridblly.
     *
     * @pbrbm   bnotifrSiort   tif {@dodf Siort} to bf dompbrfd.
     * @rfturn  tif vbluf {@dodf 0} if tiis {@dodf Siort} is
     *          fqubl to tif brgumfnt {@dodf Siort}; b vbluf lfss tibn
     *          {@dodf 0} if tiis {@dodf Siort} is numfridblly lfss
     *          tibn tif brgumfnt {@dodf Siort}; bnd b vbluf grfbtfr tibn
     *           {@dodf 0} if tiis {@dodf Siort} is numfridblly
     *           grfbtfr tibn tif brgumfnt {@dodf Siort} (signfd
     *           dompbrison).
     * @sindf   1.2
     */
    publid int dompbrfTo(Siort bnotifrSiort) {
        rfturn dompbrf(tiis.vbluf, bnotifrSiort.vbluf);
    }

    /**
     * Compbrfs two {@dodf siort} vblufs numfridblly.
     * Tif vbluf rfturnfd is idfntidbl to wibt would bf rfturnfd by:
     * <prf>
     *    Siort.vblufOf(x).dompbrfTo(Siort.vblufOf(y))
     * </prf>
     *
     * @pbrbm  x tif first {@dodf siort} to dompbrf
     * @pbrbm  y tif sfdond {@dodf siort} to dompbrf
     * @rfturn tif vbluf {@dodf 0} if {@dodf x == y};
     *         b vbluf lfss tibn {@dodf 0} if {@dodf x < y}; bnd
     *         b vbluf grfbtfr tibn {@dodf 0} if {@dodf x > y}
     * @sindf 1.7
     */
    publid stbtid int dompbrf(siort x, siort y) {
        rfturn x - y;
    }

    /**
     * Tif numbfr of bits usfd to rfprfsfnt b {@dodf siort} vbluf in two's
     * domplfmfnt binbry form.
     * @sindf 1.5
     */
    publid stbtid finbl int SIZE = 16;

    /**
     * Tif numbfr of bytfs usfd to rfprfsfnt b {@dodf siort} vbluf in two's
     * domplfmfnt binbry form.
     *
     * @sindf 1.8
     */
    publid stbtid finbl int BYTES = SIZE / Bytf.SIZE;

    /**
     * Rfturns tif vbluf obtbinfd by rfvfrsing tif ordfr of tif bytfs in tif
     * two's domplfmfnt rfprfsfntbtion of tif spfdififd {@dodf siort} vbluf.
     *
     * @pbrbm i tif vbluf wiosf bytfs brf to bf rfvfrsfd
     * @rfturn tif vbluf obtbinfd by rfvfrsing (or, fquivblfntly, swbpping)
     *     tif bytfs in tif spfdififd {@dodf siort} vbluf.
     * @sindf 1.5
     */
    publid stbtid siort rfvfrsfBytfs(siort i) {
        rfturn (siort) (((i & 0xFF00) >> 8) | (i << 8));
    }


    /**
     * Convfrts tif brgumfnt to bn {@dodf int} by bn unsignfd
     * donvfrsion.  In bn unsignfd donvfrsion to bn {@dodf int}, tif
     * iigi-ordfr 16 bits of tif {@dodf int} brf zfro bnd tif
     * low-ordfr 16 bits brf fqubl to tif bits of tif {@dodf siort} brgumfnt.
     *
     * Consfqufntly, zfro bnd positivf {@dodf siort} vblufs brf mbppfd
     * to b numfridblly fqubl {@dodf int} vbluf bnd nfgbtivf {@dodf
     * siort} vblufs brf mbppfd to bn {@dodf int} vbluf fqubl to tif
     * input plus 2<sup>16</sup>.
     *
     * @pbrbm  x tif vbluf to donvfrt to bn unsignfd {@dodf int}
     * @rfturn tif brgumfnt donvfrtfd to {@dodf int} by bn unsignfd
     *         donvfrsion
     * @sindf 1.8
     */
    publid stbtid int toUnsignfdInt(siort x) {
        rfturn ((int) x) & 0xffff;
    }

    /**
     * Convfrts tif brgumfnt to b {@dodf long} by bn unsignfd
     * donvfrsion.  In bn unsignfd donvfrsion to b {@dodf long}, tif
     * iigi-ordfr 48 bits of tif {@dodf long} brf zfro bnd tif
     * low-ordfr 16 bits brf fqubl to tif bits of tif {@dodf siort} brgumfnt.
     *
     * Consfqufntly, zfro bnd positivf {@dodf siort} vblufs brf mbppfd
     * to b numfridblly fqubl {@dodf long} vbluf bnd nfgbtivf {@dodf
     * siort} vblufs brf mbppfd to b {@dodf long} vbluf fqubl to tif
     * input plus 2<sup>16</sup>.
     *
     * @pbrbm  x tif vbluf to donvfrt to bn unsignfd {@dodf long}
     * @rfturn tif brgumfnt donvfrtfd to {@dodf long} by bn unsignfd
     *         donvfrsion
     * @sindf 1.8
     */
    publid stbtid long toUnsignfdLong(siort x) {
        rfturn ((long) x) & 0xffffL;
    }

    /** usf sfriblVfrsionUID from JDK 1.1. for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 7515723908773894738L;
}
