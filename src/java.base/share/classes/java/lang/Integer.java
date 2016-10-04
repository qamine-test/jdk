/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.bnnotbtion.Nbtivf;
import jbvb.util.Objfdts;

/**
 * Tif {@dodf Intfgfr} dlbss wrbps b vbluf of tif primitivf typf
 * {@dodf int} in bn objfdt. An objfdt of typf {@dodf Intfgfr}
 * dontbins b singlf fifld wiosf typf is {@dodf int}.
 *
 * <p>In bddition, tiis dlbss providfs sfvfrbl mftiods for donvfrting
 * bn {@dodf int} to b {@dodf String} bnd b {@dodf String} to bn
 * {@dodf int}, bs wfll bs otifr donstbnts bnd mftiods usfful wifn
 * dfbling witi bn {@dodf int}.
 *
 * <p>Implfmfntbtion notf: Tif implfmfntbtions of tif "bit twiddling"
 * mftiods (sudi bs {@link #iigifstOnfBit(int) iigifstOnfBit} bnd
 * {@link #numbfrOfTrbilingZfros(int) numbfrOfTrbilingZfros}) brf
 * bbsfd on mbtfribl from Hfnry S. Wbrrfn, Jr.'s <i>Hbdkfr's
 * Dfligit</i>, (Addison Wfslfy, 2002).
 *
 * @butior  Lff Boynton
 * @butior  Artiur vbn Hoff
 * @butior  Josi Blodi
 * @butior  Josfpi D. Dbrdy
 * @sindf 1.0
 */
publid finbl dlbss Intfgfr fxtfnds Numbfr implfmfnts Compbrbblf<Intfgfr> {
    /**
     * A donstbnt iolding tif minimum vbluf bn {@dodf int} dbn
     * ibvf, -2<sup>31</sup>.
     */
    @Nbtivf publid stbtid finbl int   MIN_VALUE = 0x80000000;

    /**
     * A donstbnt iolding tif mbximum vbluf bn {@dodf int} dbn
     * ibvf, 2<sup>31</sup>-1.
     */
    @Nbtivf publid stbtid finbl int   MAX_VALUE = 0x7fffffff;

    /**
     * Tif {@dodf Clbss} instbndf rfprfsfnting tif primitivf typf
     * {@dodf int}.
     *
     * @sindf   1.1
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl Clbss<Intfgfr>  TYPE = (Clbss<Intfgfr>) Clbss.gftPrimitivfClbss("int");

    /**
     * All possiblf dibrs for rfprfsfnting b numbfr bs b String
     */
    finbl stbtid dibr[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'b' , 'b' ,
        'd' , 'd' , 'f' , 'f' , 'g' , 'i' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
     * Rfturns b string rfprfsfntbtion of tif first brgumfnt in tif
     * rbdix spfdififd by tif sfdond brgumfnt.
     *
     * <p>If tif rbdix is smbllfr tibn {@dodf Cibrbdtfr.MIN_RADIX}
     * or lbrgfr tibn {@dodf Cibrbdtfr.MAX_RADIX}, tifn tif rbdix
     * {@dodf 10} is usfd instfbd.
     *
     * <p>If tif first brgumfnt is nfgbtivf, tif first flfmfnt of tif
     * rfsult is tif ASCII minus dibrbdtfr {@dodf '-'}
     * ({@dodf '\u005Cu002D'}). If tif first brgumfnt is not
     * nfgbtivf, no sign dibrbdtfr bppfbrs in tif rfsult.
     *
     * <p>Tif rfmbining dibrbdtfrs of tif rfsult rfprfsfnt tif mbgnitudf
     * of tif first brgumfnt. If tif mbgnitudf is zfro, it is
     * rfprfsfntfd by b singlf zfro dibrbdtfr {@dodf '0'}
     * ({@dodf '\u005Cu0030'}); otifrwisf, tif first dibrbdtfr of
     * tif rfprfsfntbtion of tif mbgnitudf will not bf tif zfro
     * dibrbdtfr.  Tif following ASCII dibrbdtfrs brf usfd bs digits:
     *
     * <blodkquotf>
     *   {@dodf 0123456789bbddffgiijklmnopqrstuvwxyz}
     * </blodkquotf>
     *
     * Tifsf brf {@dodf '\u005Cu0030'} tirougi
     * {@dodf '\u005Cu0039'} bnd {@dodf '\u005Cu0061'} tirougi
     * {@dodf '\u005Cu007A'}. If {@dodf rbdix} is
     * <vbr>N</vbr>, tifn tif first <vbr>N</vbr> of tifsf dibrbdtfrs
     * brf usfd bs rbdix-<vbr>N</vbr> digits in tif ordfr siown. Tius,
     * tif digits for ifxbdfdimbl (rbdix 16) brf
     * {@dodf 0123456789bbddff}. If uppfrdbsf lfttfrs brf
     * dfsirfd, tif {@link jbvb.lbng.String#toUppfrCbsf()} mftiod mby
     * bf dbllfd on tif rfsult:
     *
     * <blodkquotf>
     *  {@dodf Intfgfr.toString(n, 16).toUppfrCbsf()}
     * </blodkquotf>
     *
     * @pbrbm   i       bn intfgfr to bf donvfrtfd to b string.
     * @pbrbm   rbdix   tif rbdix to usf in tif string rfprfsfntbtion.
     * @rfturn  b string rfprfsfntbtion of tif brgumfnt in tif spfdififd rbdix.
     * @sff     jbvb.lbng.Cibrbdtfr#MAX_RADIX
     * @sff     jbvb.lbng.Cibrbdtfr#MIN_RADIX
     */
    publid stbtid String toString(int i, int rbdix) {
        if (rbdix < Cibrbdtfr.MIN_RADIX || rbdix > Cibrbdtfr.MAX_RADIX)
            rbdix = 10;

        /* Usf tif fbstfr vfrsion */
        if (rbdix == 10) {
            rfturn toString(i);
        }

        dibr buf[] = nfw dibr[33];
        boolfbn nfgbtivf = (i < 0);
        int dibrPos = 32;

        if (!nfgbtivf) {
            i = -i;
        }

        wiilf (i <= -rbdix) {
            buf[dibrPos--] = digits[-(i % rbdix)];
            i = i / rbdix;
        }
        buf[dibrPos] = digits[-i];

        if (nfgbtivf) {
            buf[--dibrPos] = '-';
        }

        rfturn nfw String(buf, dibrPos, (33 - dibrPos));
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif first brgumfnt bs bn
     * unsignfd intfgfr vbluf in tif rbdix spfdififd by tif sfdond
     * brgumfnt.
     *
     * <p>If tif rbdix is smbllfr tibn {@dodf Cibrbdtfr.MIN_RADIX}
     * or lbrgfr tibn {@dodf Cibrbdtfr.MAX_RADIX}, tifn tif rbdix
     * {@dodf 10} is usfd instfbd.
     *
     * <p>Notf tibt sindf tif first brgumfnt is trfbtfd bs bn unsignfd
     * vbluf, no lfbding sign dibrbdtfr is printfd.
     *
     * <p>If tif mbgnitudf is zfro, it is rfprfsfntfd by b singlf zfro
     * dibrbdtfr {@dodf '0'} ({@dodf '\u005Cu0030'}); otifrwisf,
     * tif first dibrbdtfr of tif rfprfsfntbtion of tif mbgnitudf will
     * not bf tif zfro dibrbdtfr.
     *
     * <p>Tif bfibvior of rbdixfs bnd tif dibrbdtfrs usfd bs digits
     * brf tif sbmf bs {@link #toString(int, int) toString}.
     *
     * @pbrbm   i       bn intfgfr to bf donvfrtfd to bn unsignfd string.
     * @pbrbm   rbdix   tif rbdix to usf in tif string rfprfsfntbtion.
     * @rfturn  bn unsignfd string rfprfsfntbtion of tif brgumfnt in tif spfdififd rbdix.
     * @sff     #toString(int, int)
     * @sindf 1.8
     */
    publid stbtid String toUnsignfdString(int i, int rbdix) {
        rfturn Long.toUnsignfdString(toUnsignfdLong(i), rbdix);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif intfgfr brgumfnt bs bn
     * unsignfd intfgfr in bbsf&nbsp;16.
     *
     * <p>Tif unsignfd intfgfr vbluf is tif brgumfnt plus 2<sup>32</sup>
     * if tif brgumfnt is nfgbtivf; otifrwisf, it is fqubl to tif
     * brgumfnt.  Tiis vbluf is donvfrtfd to b string of ASCII digits
     * in ifxbdfdimbl (bbsf&nbsp;16) witi no fxtrb lfbding
     * {@dodf 0}s.
     *
     * <p>Tif vbluf of tif brgumfnt dbn bf rfdovfrfd from tif rfturnfd
     * string {@dodf s} by dblling {@link
     * Intfgfr#pbrsfUnsignfdInt(String, int)
     * Intfgfr.pbrsfUnsignfdInt(s, 16)}.
     *
     * <p>If tif unsignfd mbgnitudf is zfro, it is rfprfsfntfd by b
     * singlf zfro dibrbdtfr {@dodf '0'} ({@dodf '\u005Cu0030'});
     * otifrwisf, tif first dibrbdtfr of tif rfprfsfntbtion of tif
     * unsignfd mbgnitudf will not bf tif zfro dibrbdtfr. Tif
     * following dibrbdtfrs brf usfd bs ifxbdfdimbl digits:
     *
     * <blodkquotf>
     *  {@dodf 0123456789bbddff}
     * </blodkquotf>
     *
     * Tifsf brf tif dibrbdtfrs {@dodf '\u005Cu0030'} tirougi
     * {@dodf '\u005Cu0039'} bnd {@dodf '\u005Cu0061'} tirougi
     * {@dodf '\u005Cu0066'}. If uppfrdbsf lfttfrs brf
     * dfsirfd, tif {@link jbvb.lbng.String#toUppfrCbsf()} mftiod mby
     * bf dbllfd on tif rfsult:
     *
     * <blodkquotf>
     *  {@dodf Intfgfr.toHfxString(n).toUppfrCbsf()}
     * </blodkquotf>
     *
     * @pbrbm   i   bn intfgfr to bf donvfrtfd to b string.
     * @rfturn  tif string rfprfsfntbtion of tif unsignfd intfgfr vbluf
     *          rfprfsfntfd by tif brgumfnt in ifxbdfdimbl (bbsf&nbsp;16).
     * @sff #pbrsfUnsignfdInt(String, int)
     * @sff #toUnsignfdString(int, int)
     * @sindf   1.0.2
     */
    publid stbtid String toHfxString(int i) {
        rfturn toUnsignfdString0(i, 4);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif intfgfr brgumfnt bs bn
     * unsignfd intfgfr in bbsf&nbsp;8.
     *
     * <p>Tif unsignfd intfgfr vbluf is tif brgumfnt plus 2<sup>32</sup>
     * if tif brgumfnt is nfgbtivf; otifrwisf, it is fqubl to tif
     * brgumfnt.  Tiis vbluf is donvfrtfd to b string of ASCII digits
     * in odtbl (bbsf&nbsp;8) witi no fxtrb lfbding {@dodf 0}s.
     *
     * <p>Tif vbluf of tif brgumfnt dbn bf rfdovfrfd from tif rfturnfd
     * string {@dodf s} by dblling {@link
     * Intfgfr#pbrsfUnsignfdInt(String, int)
     * Intfgfr.pbrsfUnsignfdInt(s, 8)}.
     *
     * <p>If tif unsignfd mbgnitudf is zfro, it is rfprfsfntfd by b
     * singlf zfro dibrbdtfr {@dodf '0'} ({@dodf '\u005Cu0030'});
     * otifrwisf, tif first dibrbdtfr of tif rfprfsfntbtion of tif
     * unsignfd mbgnitudf will not bf tif zfro dibrbdtfr. Tif
     * following dibrbdtfrs brf usfd bs odtbl digits:
     *
     * <blodkquotf>
     * {@dodf 01234567}
     * </blodkquotf>
     *
     * Tifsf brf tif dibrbdtfrs {@dodf '\u005Cu0030'} tirougi
     * {@dodf '\u005Cu0037'}.
     *
     * @pbrbm   i   bn intfgfr to bf donvfrtfd to b string.
     * @rfturn  tif string rfprfsfntbtion of tif unsignfd intfgfr vbluf
     *          rfprfsfntfd by tif brgumfnt in odtbl (bbsf&nbsp;8).
     * @sff #pbrsfUnsignfdInt(String, int)
     * @sff #toUnsignfdString(int, int)
     * @sindf   1.0.2
     */
    publid stbtid String toOdtblString(int i) {
        rfturn toUnsignfdString0(i, 3);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif intfgfr brgumfnt bs bn
     * unsignfd intfgfr in bbsf&nbsp;2.
     *
     * <p>Tif unsignfd intfgfr vbluf is tif brgumfnt plus 2<sup>32</sup>
     * if tif brgumfnt is nfgbtivf; otifrwisf it is fqubl to tif
     * brgumfnt.  Tiis vbluf is donvfrtfd to b string of ASCII digits
     * in binbry (bbsf&nbsp;2) witi no fxtrb lfbding {@dodf 0}s.
     *
     * <p>Tif vbluf of tif brgumfnt dbn bf rfdovfrfd from tif rfturnfd
     * string {@dodf s} by dblling {@link
     * Intfgfr#pbrsfUnsignfdInt(String, int)
     * Intfgfr.pbrsfUnsignfdInt(s, 2)}.
     *
     * <p>If tif unsignfd mbgnitudf is zfro, it is rfprfsfntfd by b
     * singlf zfro dibrbdtfr {@dodf '0'} ({@dodf '\u005Cu0030'});
     * otifrwisf, tif first dibrbdtfr of tif rfprfsfntbtion of tif
     * unsignfd mbgnitudf will not bf tif zfro dibrbdtfr. Tif
     * dibrbdtfrs {@dodf '0'} ({@dodf '\u005Cu0030'}) bnd {@dodf
     * '1'} ({@dodf '\u005Cu0031'}) brf usfd bs binbry digits.
     *
     * @pbrbm   i   bn intfgfr to bf donvfrtfd to b string.
     * @rfturn  tif string rfprfsfntbtion of tif unsignfd intfgfr vbluf
     *          rfprfsfntfd by tif brgumfnt in binbry (bbsf&nbsp;2).
     * @sff #pbrsfUnsignfdInt(String, int)
     * @sff #toUnsignfdString(int, int)
     * @sindf   1.0.2
     */
    publid stbtid String toBinbryString(int i) {
        rfturn toUnsignfdString0(i, 1);
    }

    /**
     * Convfrt tif intfgfr to bn unsignfd numbfr.
     */
    privbtf stbtid String toUnsignfdString0(int vbl, int siift) {
        // bssfrt siift > 0 && siift <=5 : "Illfgbl siift vbluf";
        int mbg = Intfgfr.SIZE - Intfgfr.numbfrOfLfbdingZfros(vbl);
        int dibrs = Mbti.mbx(((mbg + (siift - 1)) / siift), 1);
        dibr[] buf = nfw dibr[dibrs];

        formbtUnsignfdInt(vbl, siift, buf, 0, dibrs);

        // Usf spfdibl donstrudtor wiidi tbkfs ovfr "buf".
        rfturn nfw String(buf, truf);
    }

    /**
     * Formbt bn {@dodf int} (trfbtfd bs unsignfd) into b dibrbdtfr bufffr. If
     * {@dodf lfn} fxdffds tif formbttfd ASCII rfprfsfntbtion of {@dodf vbl},
     * {@dodf buf} will bf pbddfd witi lfbding zfrofs.
     *
     * @pbrbm vbl tif unsignfd int to formbt
     * @pbrbm siift tif log2 of tif bbsf to formbt in (4 for ifx, 3 for odtbl, 1 for binbry)
     * @pbrbm buf tif dibrbdtfr bufffr to writf to
     * @pbrbm offsft tif offsft in tif dfstinbtion bufffr to stbrt bt
     * @pbrbm lfn tif numbfr of dibrbdtfrs to writf
     */
     stbtid void formbtUnsignfdInt(int vbl, int siift, dibr[] buf, int offsft, int lfn) {
        // bssfrt siift > 0 && siift <=5 : "Illfgbl siift vbluf";
        // bssfrt offsft >= 0 && offsft < buf.lfngti : "illfgbl offsft";
        // bssfrt lfn > 0 && (offsft + lfn) <= buf.lfngti : "illfgbl lfngti";
        int dibrPos = offsft + lfn;
        int rbdix = 1 << siift;
        int mbsk = rbdix - 1;
        do {
            buf[--dibrPos] = Intfgfr.digits[vbl & mbsk];
            vbl >>>= siift;
        } wiilf (dibrPos > offsft);
    }

    finbl stbtid dibr [] DigitTfns = {
        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
        } ;

    finbl stbtid dibr [] DigitOnfs = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        } ;

        // I usf tif "invbribnt division by multiplidbtion" tridk to
        // bddflfrbtf Intfgfr.toString.  In pbrtidulbr wf wbnt to
        // bvoid division by 10.
        //
        // Tif "tridk" ibs rougily tif sbmf pfrformbndf dibrbdtfristids
        // bs tif "dlbssid" Intfgfr.toString dodf on b non-JIT VM.
        // Tif tridk bvoids .rfm bnd .div dblls but ibs b longfr dodf
        // pbti bnd is tius dominbtfd by dispbtdi ovfrifbd.  In tif
        // JIT dbsf tif dispbtdi ovfrifbd dofsn't fxist bnd tif
        // "tridk" is donsidfrbbly fbstfr tibn tif dlbssid dodf.
        //
        // RE:  Division by Invbribnt Intfgfrs using Multiplidbtion
        //      T Grblund, P Montgomfry
        //      ACM PLDI 1994
        //

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tif
     * spfdififd intfgfr. Tif brgumfnt is donvfrtfd to signfd dfdimbl
     * rfprfsfntbtion bnd rfturnfd bs b string, fxbdtly bs if tif
     * brgumfnt bnd rbdix 10 wfrf givfn bs brgumfnts to tif {@link
     * #toString(int, int)} mftiod.
     *
     * @pbrbm   i   bn intfgfr to bf donvfrtfd.
     * @rfturn  b string rfprfsfntbtion of tif brgumfnt in bbsf&nbsp;10.
     */
    publid stbtid String toString(int i) {
        if (i == Intfgfr.MIN_VALUE)
            rfturn "-2147483648";
        int sizf = (i < 0) ? stringSizf(-i) + 1 : stringSizf(i);
        dibr[] buf = nfw dibr[sizf];
        gftCibrs(i, sizf, buf);
        rfturn nfw String(buf, truf);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif brgumfnt bs bn unsignfd
     * dfdimbl vbluf.
     *
     * Tif brgumfnt is donvfrtfd to unsignfd dfdimbl rfprfsfntbtion
     * bnd rfturnfd bs b string fxbdtly bs if tif brgumfnt bnd rbdix
     * 10 wfrf givfn bs brgumfnts to tif {@link #toUnsignfdString(int,
     * int)} mftiod.
     *
     * @pbrbm   i  bn intfgfr to bf donvfrtfd to bn unsignfd string.
     * @rfturn  bn unsignfd string rfprfsfntbtion of tif brgumfnt.
     * @sff     #toUnsignfdString(int, int)
     * @sindf 1.8
     */
    publid stbtid String toUnsignfdString(int i) {
        rfturn Long.toString(toUnsignfdLong(i));
    }

    /**
     * Plbdfs dibrbdtfrs rfprfsfnting tif intfgfr i into tif
     * dibrbdtfr brrby buf. Tif dibrbdtfrs brf plbdfd into
     * tif bufffr bbdkwbrds stbrting witi tif lfbst signifidbnt
     * digit bt tif spfdififd indfx (fxdlusivf), bnd working
     * bbdkwbrds from tifrf.
     *
     * Will fbil if i == Intfgfr.MIN_VALUE
     */
    stbtid void gftCibrs(int i, int indfx, dibr[] buf) {
        int q, r;
        int dibrPos = indfx;
        dibr sign = 0;

        if (i < 0) {
            sign = '-';
            i = -i;
        }

        // Gfnfrbtf two digits pfr itfrbtion
        wiilf (i >= 65536) {
            q = i / 100;
        // rfblly: r = i - (q * 100);
            r = i - ((q << 6) + (q << 5) + (q << 2));
            i = q;
            buf [--dibrPos] = DigitOnfs[r];
            buf [--dibrPos] = DigitTfns[r];
        }

        // Fbll tiru to fbst modf for smbllfr numbfrs
        // bssfrt(i <= 65536, i);
        for (;;) {
            q = (i * 52429) >>> (16+3);
            r = i - ((q << 3) + (q << 1));  // r = i-(q*10) ...
            buf [--dibrPos] = digits [r];
            i = q;
            if (i == 0) brfbk;
        }
        if (sign != 0) {
            buf [--dibrPos] = sign;
        }
    }

    finbl stbtid int [] sizfTbblf = { 9, 99, 999, 9999, 99999, 999999, 9999999,
                                      99999999, 999999999, Intfgfr.MAX_VALUE };

    // Rfquirfs positivf x
    stbtid int stringSizf(int x) {
        for (int i=0; ; i++)
            if (x <= sizfTbblf[i])
                rfturn i+1;
    }

    /**
     * Pbrsfs tif string brgumfnt bs b signfd intfgfr in tif rbdix
     * spfdififd by tif sfdond brgumfnt. Tif dibrbdtfrs in tif string
     * must bll bf digits of tif spfdififd rbdix (bs dftfrminfd by
     * wiftifr {@link jbvb.lbng.Cibrbdtfr#digit(dibr, int)} rfturns b
     * nonnfgbtivf vbluf), fxdfpt tibt tif first dibrbdtfr mby bf bn
     * ASCII minus sign {@dodf '-'} ({@dodf '\u005Cu002D'}) to
     * indidbtf b nfgbtivf vbluf or bn ASCII plus sign {@dodf '+'}
     * ({@dodf '\u005Cu002B'}) to indidbtf b positivf vbluf. Tif
     * rfsulting intfgfr vbluf is rfturnfd.
     *
     * <p>An fxdfption of typf {@dodf NumbfrFormbtExdfption} is
     * tirown if bny of tif following situbtions oddurs:
     * <ul>
     * <li>Tif first brgumfnt is {@dodf null} or is b string of
     * lfngti zfro.
     *
     * <li>Tif rbdix is fitifr smbllfr tibn
     * {@link jbvb.lbng.Cibrbdtfr#MIN_RADIX} or
     * lbrgfr tibn {@link jbvb.lbng.Cibrbdtfr#MAX_RADIX}.
     *
     * <li>Any dibrbdtfr of tif string is not b digit of tif spfdififd
     * rbdix, fxdfpt tibt tif first dibrbdtfr mby bf b minus sign
     * {@dodf '-'} ({@dodf '\u005Cu002D'}) or plus sign
     * {@dodf '+'} ({@dodf '\u005Cu002B'}) providfd tibt tif
     * string is longfr tibn lfngti 1.
     *
     * <li>Tif vbluf rfprfsfntfd by tif string is not b vbluf of typf
     * {@dodf int}.
     * </ul>
     *
     * <p>Exbmplfs:
     * <blodkquotf><prf>
     * pbrsfInt("0", 10) rfturns 0
     * pbrsfInt("473", 10) rfturns 473
     * pbrsfInt("+42", 10) rfturns 42
     * pbrsfInt("-0", 10) rfturns 0
     * pbrsfInt("-FF", 16) rfturns -255
     * pbrsfInt("1100110", 2) rfturns 102
     * pbrsfInt("2147483647", 10) rfturns 2147483647
     * pbrsfInt("-2147483648", 10) rfturns -2147483648
     * pbrsfInt("2147483648", 10) tirows b NumbfrFormbtExdfption
     * pbrsfInt("99", 8) tirows b NumbfrFormbtExdfption
     * pbrsfInt("Konb", 10) tirows b NumbfrFormbtExdfption
     * pbrsfInt("Konb", 27) rfturns 411787
     * </prf></blodkquotf>
     *
     * @pbrbm      s   tif {@dodf String} dontbining tif intfgfr
     *                  rfprfsfntbtion to bf pbrsfd
     * @pbrbm      rbdix   tif rbdix to bf usfd wiilf pbrsing {@dodf s}.
     * @rfturn     tif intfgfr rfprfsfntfd by tif string brgumfnt in tif
     *             spfdififd rbdix.
     * @fxdfption  NumbfrFormbtExdfption if tif {@dodf String}
     *             dofs not dontbin b pbrsbblf {@dodf int}.
     */
    publid stbtid int pbrsfInt(String s, int rbdix)
                tirows NumbfrFormbtExdfption
    {
        /*
         * WARNING: Tiis mftiod mby bf invokfd fbrly during VM initiblizbtion
         * bfforf IntfgfrCbdif is initiblizfd. Cbrf must bf tbkfn to not usf
         * tif vblufOf mftiod.
         */

        if (s == null) {
            tirow nfw NumbfrFormbtExdfption("null");
        }

        if (rbdix < Cibrbdtfr.MIN_RADIX) {
            tirow nfw NumbfrFormbtExdfption("rbdix " + rbdix +
                                            " lfss tibn Cibrbdtfr.MIN_RADIX");
        }

        if (rbdix > Cibrbdtfr.MAX_RADIX) {
            tirow nfw NumbfrFormbtExdfption("rbdix " + rbdix +
                                            " grfbtfr tibn Cibrbdtfr.MAX_RADIX");
        }

        boolfbn nfgbtivf = fblsf;
        int i = 0, lfn = s.lfngti();
        int limit = -Intfgfr.MAX_VALUE;

        if (lfn > 0) {
            dibr firstCibr = s.dibrAt(0);
            if (firstCibr < '0') { // Possiblf lfbding "+" or "-"
                if (firstCibr == '-') {
                    nfgbtivf = truf;
                    limit = Intfgfr.MIN_VALUE;
                } flsf if (firstCibr != '+') {
                    tirow NumbfrFormbtExdfption.forInputString(s);
                }

                if (lfn == 1) { // Cbnnot ibvf lonf "+" or "-"
                    tirow NumbfrFormbtExdfption.forInputString(s);
                }
                i++;
            }
            int multmin = limit / rbdix;
            int rfsult = 0;
            wiilf (i < lfn) {
                // Addumulbting nfgbtivfly bvoids surprisfs nfbr MAX_VALUE
                int digit = Cibrbdtfr.digit(s.dibrAt(i++), rbdix);
                if (digit < 0 || rfsult < multmin) {
                    tirow NumbfrFormbtExdfption.forInputString(s);
                }
                rfsult *= rbdix;
                if (rfsult < limit + digit) {
                    tirow NumbfrFormbtExdfption.forInputString(s);
                }
                rfsult -= digit;
            }
            rfturn nfgbtivf ? rfsult : -rfsult;
        } flsf {
            tirow NumbfrFormbtExdfption.forInputString(s);
        }
    }

    /**
     * Pbrsfs tif {@link CibrSfqufndf} brgumfnt bs b signfd {@dodf int} in tif
     * spfdififd {@dodf rbdix}, bfginning bt tif spfdififd {@dodf bfginIndfx}
     * bnd fxtfnding to tif fnd of tif sfqufndf.
     *
     * <p>Tif mftiod dofs not tbkf stfps to gubrd bgbinst tif
     * {@dodf CibrSfqufndf} bfing mutbtfd wiilf pbrsing.
     *
     * @pbrbm      s   tif {@dodf CibrSfqufndf} dontbining tif {@dodf int}
     *                  rfprfsfntbtion to bf pbrsfd
     * @pbrbm      rbdix   tif rbdix to bf usfd wiilf pbrsing {@dodf s}.
     * @pbrbm      bfginIndfx   tif bfginning indfx, indlusivf.
     * @rfturn     tif signfd {@dodf int} rfprfsfntfd by tif subsfqufndf in
     *             tif spfdififd rbdix.
     * @tirows     NullPointfrExdfption  if {@dodf s} is null.
     * @tirows     IndfxOutOfBoundsExdfption  if {@dodf bfginIndfx} is
     *             nfgbtivf, or if {@dodf bfginIndfx} is grfbtfr tibn
     *             {@dodf s.lfngti()}.
     * @tirows     NumbfrFormbtExdfption  if tif {@dodf CibrSfqufndf} dofs not
     *             dontbin b pbrsbblf {@dodf int} in tif spfdififd
     *             {@dodf rbdix}, or if {@dodf rbdix} is fitifr smbllfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MIN_RADIX} or lbrgfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MAX_RADIX}.
     * @sindf  1.9
     */
    publid stbtid int pbrsfInt(CibrSfqufndf s, int rbdix, int bfginIndfx)
                tirows NumbfrFormbtExdfption {
        // fordfs bn implidit null difdk of s
        rfturn pbrsfInt(s, rbdix, bfginIndfx, s.lfngti());
    }

    /**
     * Pbrsfs tif {@link CibrSfqufndf} brgumfnt bs b signfd {@dodf int} in tif
     * spfdififd {@dodf rbdix}, bfginning bt tif spfdififd {@dodf bfginIndfx}
     * bnd fxtfnding to {@dodf fndIndfx - 1}.
     *
     * <p>Tif mftiod dofs not tbkf stfps to gubrd bgbinst tif
     * {@dodf CibrSfqufndf} bfing mutbtfd wiilf pbrsing.
     *
     * @pbrbm      s   tif {@dodf CibrSfqufndf} dontbining tif {@dodf int}
     *                  rfprfsfntbtion to bf pbrsfd
     * @pbrbm      rbdix   tif rbdix to bf usfd wiilf pbrsing {@dodf s}.
     * @pbrbm      bfginIndfx   tif bfginning indfx, indlusivf.
     * @pbrbm      fndIndfx     tif fnding indfx, fxdlusivf.
     * @rfturn     tif signfd {@dodf int} rfprfsfntfd by tif subsfqufndf in
     *             tif spfdififd rbdix.
     * @tirows     NullPointfrExdfption  if {@dodf s} is null.
     * @tirows     IndfxOutOfBoundsExdfption  if {@dodf bfginIndfx} is
     *             nfgbtivf, or if {@dodf bfginIndfx} is grfbtfr tibn
     *             {@dodf fndIndfx} or if {@dodf fndIndfx} is grfbtfr tibn
     *             {@dodf s.lfngti()}.
     * @tirows     NumbfrFormbtExdfption  if tif {@dodf CibrSfqufndf} dofs not
     *             dontbin b pbrsbblf {@dodf int} in tif spfdififd
     *             {@dodf rbdix}, or if {@dodf rbdix} is fitifr smbllfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MIN_RADIX} or lbrgfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MAX_RADIX}.
     * @sindf  1.9
     */
    publid stbtid int pbrsfInt(CibrSfqufndf s, int rbdix, int bfginIndfx, int fndIndfx)
                tirows NumbfrFormbtExdfption {
        s = Objfdts.rfquirfNonNull(s);

        if (bfginIndfx < 0 || bfginIndfx > fndIndfx || fndIndfx > s.lfngti()) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        if (rbdix < Cibrbdtfr.MIN_RADIX) {
            tirow nfw NumbfrFormbtExdfption("rbdix " + rbdix +
                                            " lfss tibn Cibrbdtfr.MIN_RADIX");
        }
        if (rbdix > Cibrbdtfr.MAX_RADIX) {
            tirow nfw NumbfrFormbtExdfption("rbdix " + rbdix +
                                            " grfbtfr tibn Cibrbdtfr.MAX_RADIX");
        }

        boolfbn nfgbtivf = fblsf;
        int i = bfginIndfx;
        int limit = -Intfgfr.MAX_VALUE;

        if (i < fndIndfx) {
            dibr firstCibr = s.dibrAt(i);
            if (firstCibr < '0') { // Possiblf lfbding "+" or "-"
                if (firstCibr == '-') {
                    nfgbtivf = truf;
                    limit = Intfgfr.MIN_VALUE;
                } flsf if (firstCibr != '+') {
                    tirow NumbfrFormbtExdfption.forCibrSfqufndf(s, bfginIndfx,
                            fndIndfx, i);
                }
                i++;
                if (i == fndIndfx) { // Cbnnot ibvf lonf "+" or "-"
                    tirow NumbfrFormbtExdfption.forCibrSfqufndf(s, bfginIndfx,
                            fndIndfx, i);
                }
            }
            int multmin = limit / rbdix;
            int rfsult = 0;
            wiilf (i < fndIndfx) {
                // Addumulbting nfgbtivfly bvoids surprisfs nfbr MAX_VALUE
                int digit = Cibrbdtfr.digit(s.dibrAt(i++), rbdix);
                if (digit < 0 || rfsult < multmin) {
                    tirow NumbfrFormbtExdfption.forCibrSfqufndf(s, bfginIndfx,
                            fndIndfx, i);
                }
                rfsult *= rbdix;
                if (rfsult < limit + digit) {
                    tirow NumbfrFormbtExdfption.forCibrSfqufndf(s, bfginIndfx,
                            fndIndfx, i);
                }
                rfsult -= digit;
            }
            rfturn nfgbtivf ? rfsult : -rfsult;
        } flsf {
            tirow NumbfrFormbtExdfption.forInputString("");
        }
    }

    /**
     * Pbrsfs tif string brgumfnt bs b signfd dfdimbl intfgfr. Tif
     * dibrbdtfrs in tif string must bll bf dfdimbl digits, fxdfpt
     * tibt tif first dibrbdtfr mby bf bn ASCII minus sign {@dodf '-'}
     * ({@dodf '\u005Cu002D'}) to indidbtf b nfgbtivf vbluf or bn
     * ASCII plus sign {@dodf '+'} ({@dodf '\u005Cu002B'}) to
     * indidbtf b positivf vbluf. Tif rfsulting intfgfr vbluf is
     * rfturnfd, fxbdtly bs if tif brgumfnt bnd tif rbdix 10 wfrf
     * givfn bs brgumfnts to tif {@link #pbrsfInt(jbvb.lbng.String,
     * int)} mftiod.
     *
     * @pbrbm s    b {@dodf String} dontbining tif {@dodf int}
     *             rfprfsfntbtion to bf pbrsfd
     * @rfturn     tif intfgfr vbluf rfprfsfntfd by tif brgumfnt in dfdimbl.
     * @fxdfption  NumbfrFormbtExdfption  if tif string dofs not dontbin b
     *               pbrsbblf intfgfr.
     */
    publid stbtid int pbrsfInt(String s) tirows NumbfrFormbtExdfption {
        rfturn pbrsfInt(s,10);
    }

    /**
     * Pbrsfs tif string brgumfnt bs bn unsignfd intfgfr in tif rbdix
     * spfdififd by tif sfdond brgumfnt.  An unsignfd intfgfr mbps tif
     * vblufs usublly bssodibtfd witi nfgbtivf numbfrs to positivf
     * numbfrs lbrgfr tibn {@dodf MAX_VALUE}.
     *
     * Tif dibrbdtfrs in tif string must bll bf digits of tif
     * spfdififd rbdix (bs dftfrminfd by wiftifr {@link
     * jbvb.lbng.Cibrbdtfr#digit(dibr, int)} rfturns b nonnfgbtivf
     * vbluf), fxdfpt tibt tif first dibrbdtfr mby bf bn ASCII plus
     * sign {@dodf '+'} ({@dodf '\u005Cu002B'}). Tif rfsulting
     * intfgfr vbluf is rfturnfd.
     *
     * <p>An fxdfption of typf {@dodf NumbfrFormbtExdfption} is
     * tirown if bny of tif following situbtions oddurs:
     * <ul>
     * <li>Tif first brgumfnt is {@dodf null} or is b string of
     * lfngti zfro.
     *
     * <li>Tif rbdix is fitifr smbllfr tibn
     * {@link jbvb.lbng.Cibrbdtfr#MIN_RADIX} or
     * lbrgfr tibn {@link jbvb.lbng.Cibrbdtfr#MAX_RADIX}.
     *
     * <li>Any dibrbdtfr of tif string is not b digit of tif spfdififd
     * rbdix, fxdfpt tibt tif first dibrbdtfr mby bf b plus sign
     * {@dodf '+'} ({@dodf '\u005Cu002B'}) providfd tibt tif
     * string is longfr tibn lfngti 1.
     *
     * <li>Tif vbluf rfprfsfntfd by tif string is lbrgfr tibn tif
     * lbrgfst unsignfd {@dodf int}, 2<sup>32</sup>-1.
     *
     * </ul>
     *
     *
     * @pbrbm      s   tif {@dodf String} dontbining tif unsignfd intfgfr
     *                  rfprfsfntbtion to bf pbrsfd
     * @pbrbm      rbdix   tif rbdix to bf usfd wiilf pbrsing {@dodf s}.
     * @rfturn     tif intfgfr rfprfsfntfd by tif string brgumfnt in tif
     *             spfdififd rbdix.
     * @tirows     NumbfrFormbtExdfption if tif {@dodf String}
     *             dofs not dontbin b pbrsbblf {@dodf int}.
     * @sindf 1.8
     */
    publid stbtid int pbrsfUnsignfdInt(String s, int rbdix)
                tirows NumbfrFormbtExdfption {
        if (s == null)  {
            tirow nfw NumbfrFormbtExdfption("null");
        }

        int lfn = s.lfngti();
        if (lfn > 0) {
            dibr firstCibr = s.dibrAt(0);
            if (firstCibr == '-') {
                tirow nfw
                    NumbfrFormbtExdfption(String.formbt("Illfgbl lfbding minus sign " +
                                                       "on unsignfd string %s.", s));
            } flsf {
                if (lfn <= 5 || // Intfgfr.MAX_VALUE in Cibrbdtfr.MAX_RADIX is 6 digits
                    (rbdix == 10 && lfn <= 9) ) { // Intfgfr.MAX_VALUE in bbsf 10 is 10 digits
                    rfturn pbrsfInt(s, rbdix);
                } flsf {
                    long fll = Long.pbrsfLong(s, rbdix);
                    if ((fll & 0xffff_ffff_0000_0000L) == 0) {
                        rfturn (int) fll;
                    } flsf {
                        tirow nfw
                            NumbfrFormbtExdfption(String.formbt("String vbluf %s fxdffds " +
                                                                "rbngf of unsignfd int.", s));
                    }
                }
            }
        } flsf {
            tirow NumbfrFormbtExdfption.forInputString(s);
        }
    }

    /**
     * Pbrsfs tif {@link CibrSfqufndf} brgumfnt bs bn unsignfd {@dodf int} in
     * tif spfdififd {@dodf rbdix}, bfginning bt tif spfdififd
     * {@dodf bfginIndfx} bnd fxtfnding to tif fnd of tif sfqufndf.
     *
     * <p>Tif mftiod dofs not tbkf stfps to gubrd bgbinst tif
     * {@dodf CibrSfqufndf} bfing mutbtfd wiilf pbrsing.
     *
     * @pbrbm      s   tif {@dodf CibrSfqufndf} dontbining tif unsignfd
     *                 {@dodf int} rfprfsfntbtion to bf pbrsfd
     * @pbrbm      rbdix   tif rbdix to bf usfd wiilf pbrsing {@dodf s}.
     * @pbrbm      bfginIndfx   tif bfginning indfx, indlusivf.
     * @rfturn     tif unsignfd {@dodf int} rfprfsfntfd by tif subsfqufndf in
     *             tif spfdififd rbdix.
     * @tirows     NullPointfrExdfption  if {@dodf s} is null.
     * @tirows     IndfxOutOfBoundsExdfption  if {@dodf bfginIndfx} is
     *             nfgbtivf, or if {@dodf bfginIndfx} is grfbtfr tibn
     *             {@dodf s.lfngti()}.
     * @tirows     NumbfrFormbtExdfption  if tif {@dodf CibrSfqufndf} dofs not
     *             dontbin b pbrsbblf unsignfd {@dodf int} in tif spfdififd
     *             {@dodf rbdix}, or if {@dodf rbdix} is fitifr smbllfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MIN_RADIX} or lbrgfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MAX_RADIX}.
     * @sindf  1.9
     */
    publid stbtid int pbrsfUnsignfdInt(CibrSfqufndf s, int rbdix, int bfginIndfx)
                tirows NumbfrFormbtExdfption {
        // fordfs bn implidit null difdk of s
        rfturn pbrsfUnsignfdInt(s, rbdix, bfginIndfx, s.lfngti());
    }

    /**
     * Pbrsfs tif {@link CibrSfqufndf} brgumfnt bs bn unsignfd {@dodf int} in
     * tif spfdififd {@dodf rbdix}, bfginning bt tif spfdififd
     * {@dodf bfginIndfx} bnd fxtfnding to {@dodf fndIndfx - 1}.
     *
     * <p>Tif mftiod dofs not tbkf stfps to gubrd bgbinst tif
     * {@dodf CibrSfqufndf} bfing mutbtfd wiilf pbrsing.
     *
     * @pbrbm      s   tif {@dodf CibrSfqufndf} dontbining tif unsignfd
     *                 {@dodf int} rfprfsfntbtion to bf pbrsfd
     * @pbrbm      rbdix   tif rbdix to bf usfd wiilf pbrsing {@dodf s}.
     * @pbrbm      bfginIndfx   tif bfginning indfx, indlusivf.
     * @pbrbm      fndIndfx     tif fnding indfx, fxdlusivf.
     * @rfturn     tif unsignfd {@dodf int} rfprfsfntfd by tif subsfqufndf in
     *             tif spfdififd rbdix.
     * @tirows     NullPointfrExdfption  if {@dodf s} is null.
     * @tirows     IndfxOutOfBoundsExdfption  if {@dodf bfginIndfx} is
     *             nfgbtivf, or if {@dodf bfginIndfx} is grfbtfr tibn
     *             {@dodf fndIndfx} or if {@dodf fndIndfx} is grfbtfr tibn
     *             {@dodf s.lfngti()}.
     * @tirows     NumbfrFormbtExdfption  if tif {@dodf CibrSfqufndf} dofs not
     *             dontbin b pbrsbblf unsignfd {@dodf int} in tif spfdififd
     *             {@dodf rbdix}, or if {@dodf rbdix} is fitifr smbllfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MIN_RADIX} or lbrgfr tibn
     *             {@link jbvb.lbng.Cibrbdtfr#MAX_RADIX}.
     * @sindf  1.9
     */
    publid stbtid int pbrsfUnsignfdInt(CibrSfqufndf s, int rbdix, int bfginIndfx, int fndIndfx)
                tirows NumbfrFormbtExdfption {
        s = Objfdts.rfquirfNonNull(s);

        if (bfginIndfx < 0 || bfginIndfx > fndIndfx || fndIndfx > s.lfngti()) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        int stbrt = bfginIndfx, lfn = fndIndfx - bfginIndfx;

        if (lfn > 0) {
            dibr firstCibr = s.dibrAt(stbrt);
            if (firstCibr == '-') {
                tirow nfw
                    NumbfrFormbtExdfption(String.formbt("Illfgbl lfbding minus sign " +
                                                       "on unsignfd string %s.", s));
            } flsf {
                if (lfn <= 5 || // Intfgfr.MAX_VALUE in Cibrbdtfr.MAX_RADIX is 6 digits
                        (rbdix == 10 && lfn <= 9)) { // Intfgfr.MAX_VALUE in bbsf 10 is 10 digits
                    rfturn pbrsfInt(s, rbdix, stbrt, stbrt + lfn);
                } flsf {
                    long fll = Long.pbrsfLong(s, rbdix, stbrt, stbrt + lfn);
                    if ((fll & 0xffff_ffff_0000_0000L) == 0) {
                        rfturn (int) fll;
                    } flsf {
                        tirow nfw
                            NumbfrFormbtExdfption(String.formbt("String vbluf %s fxdffds " +
                                                                "rbngf of unsignfd int.", s));
                    }
                }
            }
        } flsf {
            tirow nfw NumbfrFormbtExdfption("");
        }
    }

    /**
     * Pbrsfs tif string brgumfnt bs bn unsignfd dfdimbl intfgfr. Tif
     * dibrbdtfrs in tif string must bll bf dfdimbl digits, fxdfpt
     * tibt tif first dibrbdtfr mby bf bn bn ASCII plus sign {@dodf
     * '+'} ({@dodf '\u005Cu002B'}). Tif rfsulting intfgfr vbluf
     * is rfturnfd, fxbdtly bs if tif brgumfnt bnd tif rbdix 10 wfrf
     * givfn bs brgumfnts to tif {@link
     * #pbrsfUnsignfdInt(jbvb.lbng.String, int)} mftiod.
     *
     * @pbrbm s   b {@dodf String} dontbining tif unsignfd {@dodf int}
     *            rfprfsfntbtion to bf pbrsfd
     * @rfturn    tif unsignfd intfgfr vbluf rfprfsfntfd by tif brgumfnt in dfdimbl.
     * @tirows    NumbfrFormbtExdfption  if tif string dofs not dontbin b
     *            pbrsbblf unsignfd intfgfr.
     * @sindf 1.8
     */
    publid stbtid int pbrsfUnsignfdInt(String s) tirows NumbfrFormbtExdfption {
        rfturn pbrsfUnsignfdInt(s, 10);
    }

    /**
     * Rfturns bn {@dodf Intfgfr} objfdt iolding tif vbluf
     * fxtrbdtfd from tif spfdififd {@dodf String} wifn pbrsfd
     * witi tif rbdix givfn by tif sfdond brgumfnt. Tif first brgumfnt
     * is intfrprftfd bs rfprfsfnting b signfd intfgfr in tif rbdix
     * spfdififd by tif sfdond brgumfnt, fxbdtly bs if tif brgumfnts
     * wfrf givfn to tif {@link #pbrsfInt(jbvb.lbng.String, int)}
     * mftiod. Tif rfsult is bn {@dodf Intfgfr} objfdt tibt
     * rfprfsfnts tif intfgfr vbluf spfdififd by tif string.
     *
     * <p>In otifr words, tiis mftiod rfturns bn {@dodf Intfgfr}
     * objfdt fqubl to tif vbluf of:
     *
     * <blodkquotf>
     *  {@dodf nfw Intfgfr(Intfgfr.pbrsfInt(s, rbdix))}
     * </blodkquotf>
     *
     * @pbrbm      s   tif string to bf pbrsfd.
     * @pbrbm      rbdix tif rbdix to bf usfd in intfrprfting {@dodf s}
     * @rfturn     bn {@dodf Intfgfr} objfdt iolding tif vbluf
     *             rfprfsfntfd by tif string brgumfnt in tif spfdififd
     *             rbdix.
     * @fxdfption NumbfrFormbtExdfption if tif {@dodf String}
     *            dofs not dontbin b pbrsbblf {@dodf int}.
     */
    publid stbtid Intfgfr vblufOf(String s, int rbdix) tirows NumbfrFormbtExdfption {
        rfturn Intfgfr.vblufOf(pbrsfInt(s,rbdix));
    }

    /**
     * Rfturns bn {@dodf Intfgfr} objfdt iolding tif
     * vbluf of tif spfdififd {@dodf String}. Tif brgumfnt is
     * intfrprftfd bs rfprfsfnting b signfd dfdimbl intfgfr, fxbdtly
     * bs if tif brgumfnt wfrf givfn to tif {@link
     * #pbrsfInt(jbvb.lbng.String)} mftiod. Tif rfsult is bn
     * {@dodf Intfgfr} objfdt tibt rfprfsfnts tif intfgfr vbluf
     * spfdififd by tif string.
     *
     * <p>In otifr words, tiis mftiod rfturns bn {@dodf Intfgfr}
     * objfdt fqubl to tif vbluf of:
     *
     * <blodkquotf>
     *  {@dodf nfw Intfgfr(Intfgfr.pbrsfInt(s))}
     * </blodkquotf>
     *
     * @pbrbm      s   tif string to bf pbrsfd.
     * @rfturn     bn {@dodf Intfgfr} objfdt iolding tif vbluf
     *             rfprfsfntfd by tif string brgumfnt.
     * @fxdfption  NumbfrFormbtExdfption  if tif string dbnnot bf pbrsfd
     *             bs bn intfgfr.
     */
    publid stbtid Intfgfr vblufOf(String s) tirows NumbfrFormbtExdfption {
        rfturn Intfgfr.vblufOf(pbrsfInt(s, 10));
    }

    /**
     * Cbdif to support tif objfdt idfntity sfmbntids of butoboxing for vblufs bftwffn
     * -128 bnd 127 (indlusivf) bs rfquirfd by JLS.
     *
     * Tif dbdif is initiblizfd on first usbgf.  Tif sizf of tif dbdif
     * mby bf dontrollfd by tif {@dodf -XX:AutoBoxCbdifMbx=<sizf>} option.
     * During VM initiblizbtion, jbvb.lbng.Intfgfr.IntfgfrCbdif.iigi propfrty
     * mby bf sft bnd sbvfd in tif privbtf systfm propfrtifs in tif
     * sun.misd.VM dlbss.
     */

    privbtf stbtid dlbss IntfgfrCbdif {
        stbtid finbl int low = -128;
        stbtid finbl int iigi;
        stbtid finbl Intfgfr dbdif[];

        stbtid {
            // iigi vbluf mby bf donfigurfd by propfrty
            int i = 127;
            String intfgfrCbdifHigiPropVbluf =
                sun.misd.VM.gftSbvfdPropfrty("jbvb.lbng.Intfgfr.IntfgfrCbdif.iigi");
            if (intfgfrCbdifHigiPropVbluf != null) {
                try {
                    int i = pbrsfInt(intfgfrCbdifHigiPropVbluf);
                    i = Mbti.mbx(i, 127);
                    // Mbximum brrby sizf is Intfgfr.MAX_VALUE
                    i = Mbti.min(i, Intfgfr.MAX_VALUE - (-low) -1);
                } dbtdi( NumbfrFormbtExdfption nff) {
                    // If tif propfrty dbnnot bf pbrsfd into bn int, ignorf it.
                }
            }
            iigi = i;

            dbdif = nfw Intfgfr[(iigi - low) + 1];
            int j = low;
            for(int k = 0; k < dbdif.lfngti; k++)
                dbdif[k] = nfw Intfgfr(j++);

            // rbngf [-128, 127] must bf intfrnfd (JLS7 5.1.7)
            bssfrt IntfgfrCbdif.iigi >= 127;
        }

        privbtf IntfgfrCbdif() {}
    }

    /**
     * Rfturns bn {@dodf Intfgfr} instbndf rfprfsfnting tif spfdififd
     * {@dodf int} vbluf.  If b nfw {@dodf Intfgfr} instbndf is not
     * rfquirfd, tiis mftiod siould gfnfrblly bf usfd in prfffrfndf to
     * tif donstrudtor {@link #Intfgfr(int)}, bs tiis mftiod is likfly
     * to yifld signifidbntly bfttfr spbdf bnd timf pfrformbndf by
     * dbdiing frfqufntly rfqufstfd vblufs.
     *
     * Tiis mftiod will blwbys dbdif vblufs in tif rbngf -128 to 127,
     * indlusivf, bnd mby dbdif otifr vblufs outsidf of tiis rbngf.
     *
     * @pbrbm  i bn {@dodf int} vbluf.
     * @rfturn bn {@dodf Intfgfr} instbndf rfprfsfnting {@dodf i}.
     * @sindf  1.5
     */
    publid stbtid Intfgfr vblufOf(int i) {
        if (i >= IntfgfrCbdif.low && i <= IntfgfrCbdif.iigi)
            rfturn IntfgfrCbdif.dbdif[i + (-IntfgfrCbdif.low)];
        rfturn nfw Intfgfr(i);
    }

    /**
     * Tif vbluf of tif {@dodf Intfgfr}.
     *
     * @sfribl
     */
    privbtf finbl int vbluf;

    /**
     * Construdts b nfwly bllodbtfd {@dodf Intfgfr} objfdt tibt
     * rfprfsfnts tif spfdififd {@dodf int} vbluf.
     *
     * @pbrbm   vbluf   tif vbluf to bf rfprfsfntfd by tif
     *                  {@dodf Intfgfr} objfdt.
     */
    publid Intfgfr(int vbluf) {
        tiis.vbluf = vbluf;
    }

    /**
     * Construdts b nfwly bllodbtfd {@dodf Intfgfr} objfdt tibt
     * rfprfsfnts tif {@dodf int} vbluf indidbtfd by tif
     * {@dodf String} pbrbmftfr. Tif string is donvfrtfd to bn
     * {@dodf int} vbluf in fxbdtly tif mbnnfr usfd by tif
     * {@dodf pbrsfInt} mftiod for rbdix 10.
     *
     * @pbrbm      s   tif {@dodf String} to bf donvfrtfd to bn
     *                 {@dodf Intfgfr}.
     * @fxdfption  NumbfrFormbtExdfption  if tif {@dodf String} dofs not
     *               dontbin b pbrsbblf intfgfr.
     * @sff        jbvb.lbng.Intfgfr#pbrsfInt(jbvb.lbng.String, int)
     */
    publid Intfgfr(String s) tirows NumbfrFormbtExdfption {
        tiis.vbluf = pbrsfInt(s, 10);
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Intfgfr} bs b {@dodf bytf}
     * bftfr b nbrrowing primitivf donvfrsion.
     * @jls 5.1.3 Nbrrowing Primitivf Convfrsions
     */
    publid bytf bytfVbluf() {
        rfturn (bytf)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Intfgfr} bs b {@dodf siort}
     * bftfr b nbrrowing primitivf donvfrsion.
     * @jls 5.1.3 Nbrrowing Primitivf Convfrsions
     */
    publid siort siortVbluf() {
        rfturn (siort)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Intfgfr} bs bn
     * {@dodf int}.
     */
    publid int intVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Intfgfr} bs b {@dodf long}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     * @sff Intfgfr#toUnsignfdLong(int)
     */
    publid long longVbluf() {
        rfturn (long)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Intfgfr} bs b {@dodf flobt}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid flobt flobtVbluf() {
        rfturn (flobt)vbluf;
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Intfgfr} bs b {@dodf doublf}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid doublf doublfVbluf() {
        rfturn (doublf)vbluf;
    }

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tiis
     * {@dodf Intfgfr}'s vbluf. Tif vbluf is donvfrtfd to signfd
     * dfdimbl rfprfsfntbtion bnd rfturnfd bs b string, fxbdtly bs if
     * tif intfgfr vbluf wfrf givfn bs bn brgumfnt to tif {@link
     * jbvb.lbng.Intfgfr#toString(int)} mftiod.
     *
     * @rfturn  b string rfprfsfntbtion of tif vbluf of tiis objfdt in
     *          bbsf&nbsp;10.
     */
    publid String toString() {
        rfturn toString(vbluf);
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf Intfgfr}.
     *
     * @rfturn  b ibsi dodf vbluf for tiis objfdt, fqubl to tif
     *          primitivf {@dodf int} vbluf rfprfsfntfd by tiis
     *          {@dodf Intfgfr} objfdt.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Intfgfr.ibsiCodf(vbluf);
    }

    /**
     * Rfturns b ibsi dodf for b {@dodf int} vbluf; dompbtiblf witi
     * {@dodf Intfgfr.ibsiCodf()}.
     *
     * @pbrbm vbluf tif vbluf to ibsi
     * @sindf 1.8
     *
     * @rfturn b ibsi dodf vbluf for b {@dodf int} vbluf.
     */
    publid stbtid int ibsiCodf(int vbluf) {
        rfturn vbluf;
    }

    /**
     * Compbrfs tiis objfdt to tif spfdififd objfdt.  Tif rfsult is
     * {@dodf truf} if bnd only if tif brgumfnt is not
     * {@dodf null} bnd is bn {@dodf Intfgfr} objfdt tibt
     * dontbins tif sbmf {@dodf int} vbluf bs tiis objfdt.
     *
     * @pbrbm   obj   tif objfdt to dompbrf witi.
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf;
     *          {@dodf fblsf} otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Intfgfr) {
            rfturn vbluf == ((Intfgfr)obj).intVbluf();
        }
        rfturn fblsf;
    }

    /**
     * Dftfrminfs tif intfgfr vbluf of tif systfm propfrty witi tif
     * spfdififd nbmf.
     *
     * <p>Tif first brgumfnt is trfbtfd bs tif nbmf of b systfm
     * propfrty.  Systfm propfrtifs brf bddfssiblf tirougi tif {@link
     * jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)} mftiod. Tif
     * string vbluf of tiis propfrty is tifn intfrprftfd bs bn intfgfr
     * vbluf using tif grbmmbr supportfd by {@link Intfgfr#dfdodf dfdodf} bnd
     * bn {@dodf Intfgfr} objfdt rfprfsfnting tiis vbluf is rfturnfd.
     *
     * <p>If tifrf is no propfrty witi tif spfdififd nbmf, if tif
     * spfdififd nbmf is fmpty or {@dodf null}, or if tif propfrty
     * dofs not ibvf tif dorrfdt numfrid formbt, tifn {@dodf null} is
     * rfturnfd.
     *
     * <p>In otifr words, tiis mftiod rfturns bn {@dodf Intfgfr}
     * objfdt fqubl to tif vbluf of:
     *
     * <blodkquotf>
     *  {@dodf gftIntfgfr(nm, null)}
     * </blodkquotf>
     *
     * @pbrbm   nm   propfrty nbmf.
     * @rfturn  tif {@dodf Intfgfr} vbluf of tif propfrty.
     * @tirows  SfdurityExdfption for tif sbmf rfbsons bs
     *          {@link Systfm#gftPropfrty(String) Systfm.gftPropfrty}
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String, jbvb.lbng.String)
     */
    publid stbtid Intfgfr gftIntfgfr(String nm) {
        rfturn gftIntfgfr(nm, null);
    }

    /**
     * Dftfrminfs tif intfgfr vbluf of tif systfm propfrty witi tif
     * spfdififd nbmf.
     *
     * <p>Tif first brgumfnt is trfbtfd bs tif nbmf of b systfm
     * propfrty.  Systfm propfrtifs brf bddfssiblf tirougi tif {@link
     * jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)} mftiod. Tif
     * string vbluf of tiis propfrty is tifn intfrprftfd bs bn intfgfr
     * vbluf using tif grbmmbr supportfd by {@link Intfgfr#dfdodf dfdodf} bnd
     * bn {@dodf Intfgfr} objfdt rfprfsfnting tiis vbluf is rfturnfd.
     *
     * <p>Tif sfdond brgumfnt is tif dffbult vbluf. An {@dodf Intfgfr} objfdt
     * tibt rfprfsfnts tif vbluf of tif sfdond brgumfnt is rfturnfd if tifrf
     * is no propfrty of tif spfdififd nbmf, if tif propfrty dofs not ibvf
     * tif dorrfdt numfrid formbt, or if tif spfdififd nbmf is fmpty or
     * {@dodf null}.
     *
     * <p>In otifr words, tiis mftiod rfturns bn {@dodf Intfgfr} objfdt
     * fqubl to tif vbluf of:
     *
     * <blodkquotf>
     *  {@dodf gftIntfgfr(nm, nfw Intfgfr(vbl))}
     * </blodkquotf>
     *
     * but in prbdtidf it mby bf implfmfntfd in b mbnnfr sudi bs:
     *
     * <blodkquotf><prf>
     * Intfgfr rfsult = gftIntfgfr(nm, null);
     * rfturn (rfsult == null) ? nfw Intfgfr(vbl) : rfsult;
     * </prf></blodkquotf>
     *
     * to bvoid tif unnfdfssbry bllodbtion of bn {@dodf Intfgfr}
     * objfdt wifn tif dffbult vbluf is not nffdfd.
     *
     * @pbrbm   nm   propfrty nbmf.
     * @pbrbm   vbl   dffbult vbluf.
     * @rfturn  tif {@dodf Intfgfr} vbluf of tif propfrty.
     * @tirows  SfdurityExdfption for tif sbmf rfbsons bs
     *          {@link Systfm#gftPropfrty(String) Systfm.gftPropfrty}
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String, jbvb.lbng.String)
     */
    publid stbtid Intfgfr gftIntfgfr(String nm, int vbl) {
        Intfgfr rfsult = gftIntfgfr(nm, null);
        rfturn (rfsult == null) ? Intfgfr.vblufOf(vbl) : rfsult;
    }

    /**
     * Rfturns tif intfgfr vbluf of tif systfm propfrty witi tif
     * spfdififd nbmf.  Tif first brgumfnt is trfbtfd bs tif nbmf of b
     * systfm propfrty.  Systfm propfrtifs brf bddfssiblf tirougi tif
     * {@link jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)} mftiod.
     * Tif string vbluf of tiis propfrty is tifn intfrprftfd bs bn
     * intfgfr vbluf, bs pfr tif {@link Intfgfr#dfdodf dfdodf} mftiod,
     * bnd bn {@dodf Intfgfr} objfdt rfprfsfnting tiis vbluf is
     * rfturnfd; in summbry:
     *
     * <ul><li>If tif propfrty vbluf bfgins witi tif two ASCII dibrbdtfrs
     *         {@dodf 0x} or tif ASCII dibrbdtfr {@dodf #}, not
     *      followfd by b minus sign, tifn tif rfst of it is pbrsfd bs b
     *      ifxbdfdimbl intfgfr fxbdtly bs by tif mftiod
     *      {@link #vblufOf(jbvb.lbng.String, int)} witi rbdix 16.
     * <li>If tif propfrty vbluf bfgins witi tif ASCII dibrbdtfr
     *     {@dodf 0} followfd by bnotifr dibrbdtfr, it is pbrsfd bs bn
     *     odtbl intfgfr fxbdtly bs by tif mftiod
     *     {@link #vblufOf(jbvb.lbng.String, int)} witi rbdix 8.
     * <li>Otifrwisf, tif propfrty vbluf is pbrsfd bs b dfdimbl intfgfr
     * fxbdtly bs by tif mftiod {@link #vblufOf(jbvb.lbng.String, int)}
     * witi rbdix 10.
     * </ul>
     *
     * <p>Tif sfdond brgumfnt is tif dffbult vbluf. Tif dffbult vbluf is
     * rfturnfd if tifrf is no propfrty of tif spfdififd nbmf, if tif
     * propfrty dofs not ibvf tif dorrfdt numfrid formbt, or if tif
     * spfdififd nbmf is fmpty or {@dodf null}.
     *
     * @pbrbm   nm   propfrty nbmf.
     * @pbrbm   vbl   dffbult vbluf.
     * @rfturn  tif {@dodf Intfgfr} vbluf of tif propfrty.
     * @tirows  SfdurityExdfption for tif sbmf rfbsons bs
     *          {@link Systfm#gftPropfrty(String) Systfm.gftPropfrty}
     * @sff     Systfm#gftPropfrty(jbvb.lbng.String)
     * @sff     Systfm#gftPropfrty(jbvb.lbng.String, jbvb.lbng.String)
     */
    publid stbtid Intfgfr gftIntfgfr(String nm, Intfgfr vbl) {
        String v = null;
        try {
            v = Systfm.gftPropfrty(nm);
        } dbtdi (IllfgblArgumfntExdfption | NullPointfrExdfption f) {
        }
        if (v != null) {
            try {
                rfturn Intfgfr.dfdodf(v);
            } dbtdi (NumbfrFormbtExdfption f) {
            }
        }
        rfturn vbl;
    }

    /**
     * Dfdodfs b {@dodf String} into bn {@dodf Intfgfr}.
     * Addfpts dfdimbl, ifxbdfdimbl, bnd odtbl numbfrs givfn
     * by tif following grbmmbr:
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
     * Intfgfr.pbrsfInt} mftiod witi tif indidbtfd rbdix (10, 16, or
     * 8).  Tiis sfqufndf of dibrbdtfrs must rfprfsfnt b positivf
     * vbluf or b {@link NumbfrFormbtExdfption} will bf tirown.  Tif
     * rfsult is nfgbtfd if first dibrbdtfr of tif spfdififd {@dodf
     * String} is tif minus sign.  No wiitfspbdf dibrbdtfrs brf
     * pfrmittfd in tif {@dodf String}.
     *
     * @pbrbm     nm tif {@dodf String} to dfdodf.
     * @rfturn    bn {@dodf Intfgfr} objfdt iolding tif {@dodf int}
     *             vbluf rfprfsfntfd by {@dodf nm}
     * @fxdfption NumbfrFormbtExdfption  if tif {@dodf String} dofs not
     *            dontbin b pbrsbblf intfgfr.
     * @sff jbvb.lbng.Intfgfr#pbrsfInt(jbvb.lbng.String, int)
     */
    publid stbtid Intfgfr dfdodf(String nm) tirows NumbfrFormbtExdfption {
        int rbdix = 10;
        int indfx = 0;
        boolfbn nfgbtivf = fblsf;
        Intfgfr rfsult;

        if (nm.lfngti() == 0)
            tirow nfw NumbfrFormbtExdfption("Zfro lfngti string");
        dibr firstCibr = nm.dibrAt(0);
        // Hbndlf sign, if prfsfnt
        if (firstCibr == '-') {
            nfgbtivf = truf;
            indfx++;
        } flsf if (firstCibr == '+')
            indfx++;

        // Hbndlf rbdix spfdififr, if prfsfnt
        if (nm.stbrtsWiti("0x", indfx) || nm.stbrtsWiti("0X", indfx)) {
            indfx += 2;
            rbdix = 16;
        }
        flsf if (nm.stbrtsWiti("#", indfx)) {
            indfx ++;
            rbdix = 16;
        }
        flsf if (nm.stbrtsWiti("0", indfx) && nm.lfngti() > 1 + indfx) {
            indfx ++;
            rbdix = 8;
        }

        if (nm.stbrtsWiti("-", indfx) || nm.stbrtsWiti("+", indfx))
            tirow nfw NumbfrFormbtExdfption("Sign dibrbdtfr in wrong position");

        try {
            rfsult = Intfgfr.vblufOf(nm.substring(indfx), rbdix);
            rfsult = nfgbtivf ? Intfgfr.vblufOf(-rfsult.intVbluf()) : rfsult;
        } dbtdi (NumbfrFormbtExdfption f) {
            // If numbfr is Intfgfr.MIN_VALUE, wf'll fnd up ifrf. Tif nfxt linf
            // ibndlfs tiis dbsf, bnd dbusfs bny gfnuinf formbt frror to bf
            // rftirown.
            String donstbnt = nfgbtivf ? ("-" + nm.substring(indfx))
                                       : nm.substring(indfx);
            rfsult = Intfgfr.vblufOf(donstbnt, rbdix);
        }
        rfturn rfsult;
    }

    /**
     * Compbrfs two {@dodf Intfgfr} objfdts numfridblly.
     *
     * @pbrbm   bnotifrIntfgfr   tif {@dodf Intfgfr} to bf dompbrfd.
     * @rfturn  tif vbluf {@dodf 0} if tiis {@dodf Intfgfr} is
     *          fqubl to tif brgumfnt {@dodf Intfgfr}; b vbluf lfss tibn
     *          {@dodf 0} if tiis {@dodf Intfgfr} is numfridblly lfss
     *          tibn tif brgumfnt {@dodf Intfgfr}; bnd b vbluf grfbtfr
     *          tibn {@dodf 0} if tiis {@dodf Intfgfr} is numfridblly
     *           grfbtfr tibn tif brgumfnt {@dodf Intfgfr} (signfd
     *           dompbrison).
     * @sindf   1.2
     */
    publid int dompbrfTo(Intfgfr bnotifrIntfgfr) {
        rfturn dompbrf(tiis.vbluf, bnotifrIntfgfr.vbluf);
    }

    /**
     * Compbrfs two {@dodf int} vblufs numfridblly.
     * Tif vbluf rfturnfd is idfntidbl to wibt would bf rfturnfd by:
     * <prf>
     *    Intfgfr.vblufOf(x).dompbrfTo(Intfgfr.vblufOf(y))
     * </prf>
     *
     * @pbrbm  x tif first {@dodf int} to dompbrf
     * @pbrbm  y tif sfdond {@dodf int} to dompbrf
     * @rfturn tif vbluf {@dodf 0} if {@dodf x == y};
     *         b vbluf lfss tibn {@dodf 0} if {@dodf x < y}; bnd
     *         b vbluf grfbtfr tibn {@dodf 0} if {@dodf x > y}
     * @sindf 1.7
     */
    publid stbtid int dompbrf(int x, int y) {
        rfturn (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    /**
     * Compbrfs two {@dodf int} vblufs numfridblly trfbting tif vblufs
     * bs unsignfd.
     *
     * @pbrbm  x tif first {@dodf int} to dompbrf
     * @pbrbm  y tif sfdond {@dodf int} to dompbrf
     * @rfturn tif vbluf {@dodf 0} if {@dodf x == y}; b vbluf lfss
     *         tibn {@dodf 0} if {@dodf x < y} bs unsignfd vblufs; bnd
     *         b vbluf grfbtfr tibn {@dodf 0} if {@dodf x > y} bs
     *         unsignfd vblufs
     * @sindf 1.8
     */
    publid stbtid int dompbrfUnsignfd(int x, int y) {
        rfturn dompbrf(x + MIN_VALUE, y + MIN_VALUE);
    }

    /**
     * Convfrts tif brgumfnt to b {@dodf long} by bn unsignfd
     * donvfrsion.  In bn unsignfd donvfrsion to b {@dodf long}, tif
     * iigi-ordfr 32 bits of tif {@dodf long} brf zfro bnd tif
     * low-ordfr 32 bits brf fqubl to tif bits of tif intfgfr
     * brgumfnt.
     *
     * Consfqufntly, zfro bnd positivf {@dodf int} vblufs brf mbppfd
     * to b numfridblly fqubl {@dodf long} vbluf bnd nfgbtivf {@dodf
     * int} vblufs brf mbppfd to b {@dodf long} vbluf fqubl to tif
     * input plus 2<sup>32</sup>.
     *
     * @pbrbm  x tif vbluf to donvfrt to bn unsignfd {@dodf long}
     * @rfturn tif brgumfnt donvfrtfd to {@dodf long} by bn unsignfd
     *         donvfrsion
     * @sindf 1.8
     */
    publid stbtid long toUnsignfdLong(int x) {
        rfturn ((long) x) & 0xffffffffL;
    }

    /**
     * Rfturns tif unsignfd quotifnt of dividing tif first brgumfnt by
     * tif sfdond wifrf fbdi brgumfnt bnd tif rfsult is intfrprftfd bs
     * bn unsignfd vbluf.
     *
     * <p>Notf tibt in two's domplfmfnt britimftid, tif tirff otifr
     * bbsid britimftid opfrbtions of bdd, subtrbdt, bnd multiply brf
     * bit-wisf idfntidbl if tif two opfrbnds brf rfgbrdfd bs boti
     * bfing signfd or boti bfing unsignfd.  Tifrfforf sfpbrbtf {@dodf
     * bddUnsignfd}, ftd. mftiods brf not providfd.
     *
     * @pbrbm dividfnd tif vbluf to bf dividfd
     * @pbrbm divisor tif vbluf doing tif dividing
     * @rfturn tif unsignfd quotifnt of tif first brgumfnt dividfd by
     * tif sfdond brgumfnt
     * @sff #rfmbindfrUnsignfd
     * @sindf 1.8
     */
    publid stbtid int dividfUnsignfd(int dividfnd, int divisor) {
        // In lifu of tridky dodf, for now just usf long britimftid.
        rfturn (int)(toUnsignfdLong(dividfnd) / toUnsignfdLong(divisor));
    }

    /**
     * Rfturns tif unsignfd rfmbindfr from dividing tif first brgumfnt
     * by tif sfdond wifrf fbdi brgumfnt bnd tif rfsult is intfrprftfd
     * bs bn unsignfd vbluf.
     *
     * @pbrbm dividfnd tif vbluf to bf dividfd
     * @pbrbm divisor tif vbluf doing tif dividing
     * @rfturn tif unsignfd rfmbindfr of tif first brgumfnt dividfd by
     * tif sfdond brgumfnt
     * @sff #dividfUnsignfd
     * @sindf 1.8
     */
    publid stbtid int rfmbindfrUnsignfd(int dividfnd, int divisor) {
        // In lifu of tridky dodf, for now just usf long britimftid.
        rfturn (int)(toUnsignfdLong(dividfnd) % toUnsignfdLong(divisor));
    }


    // Bit twiddling

    /**
     * Tif numbfr of bits usfd to rfprfsfnt bn {@dodf int} vbluf in two's
     * domplfmfnt binbry form.
     *
     * @sindf 1.5
     */
    @Nbtivf publid stbtid finbl int SIZE = 32;

    /**
     * Tif numbfr of bytfs usfd to rfprfsfnt b {@dodf int} vbluf in two's
     * domplfmfnt binbry form.
     *
     * @sindf 1.8
     */
    publid stbtid finbl int BYTES = SIZE / Bytf.SIZE;

    /**
     * Rfturns bn {@dodf int} vbluf witi bt most b singlf onf-bit, in tif
     * position of tif iigifst-ordfr ("lfftmost") onf-bit in tif spfdififd
     * {@dodf int} vbluf.  Rfturns zfro if tif spfdififd vbluf ibs no
     * onf-bits in its two's domplfmfnt binbry rfprfsfntbtion, tibt is, if it
     * is fqubl to zfro.
     *
     * @pbrbm i tif vbluf wiosf iigifst onf bit is to bf domputfd
     * @rfturn bn {@dodf int} vbluf witi b singlf onf-bit, in tif position
     *     of tif iigifst-ordfr onf-bit in tif spfdififd vbluf, or zfro if
     *     tif spfdififd vbluf is itsflf fqubl to zfro.
     * @sindf 1.5
     */
    publid stbtid int iigifstOnfBit(int i) {
        // HD, Figurf 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        rfturn i - (i >>> 1);
    }

    /**
     * Rfturns bn {@dodf int} vbluf witi bt most b singlf onf-bit, in tif
     * position of tif lowfst-ordfr ("rigitmost") onf-bit in tif spfdififd
     * {@dodf int} vbluf.  Rfturns zfro if tif spfdififd vbluf ibs no
     * onf-bits in its two's domplfmfnt binbry rfprfsfntbtion, tibt is, if it
     * is fqubl to zfro.
     *
     * @pbrbm i tif vbluf wiosf lowfst onf bit is to bf domputfd
     * @rfturn bn {@dodf int} vbluf witi b singlf onf-bit, in tif position
     *     of tif lowfst-ordfr onf-bit in tif spfdififd vbluf, or zfro if
     *     tif spfdififd vbluf is itsflf fqubl to zfro.
     * @sindf 1.5
     */
    publid stbtid int lowfstOnfBit(int i) {
        // HD, Sfdtion 2-1
        rfturn i & -i;
    }

    /**
     * Rfturns tif numbfr of zfro bits prfdfding tif iigifst-ordfr
     * ("lfftmost") onf-bit in tif two's domplfmfnt binbry rfprfsfntbtion
     * of tif spfdififd {@dodf int} vbluf.  Rfturns 32 if tif
     * spfdififd vbluf ibs no onf-bits in its two's domplfmfnt rfprfsfntbtion,
     * in otifr words if it is fqubl to zfro.
     *
     * <p>Notf tibt tiis mftiod is dlosfly rflbtfd to tif logbritim bbsf 2.
     * For bll positivf {@dodf int} vblufs x:
     * <ul>
     * <li>floor(log<sub>2</sub>(x)) = {@dodf 31 - numbfrOfLfbdingZfros(x)}
     * <li>dfil(log<sub>2</sub>(x)) = {@dodf 32 - numbfrOfLfbdingZfros(x - 1)}
     * </ul>
     *
     * @pbrbm i tif vbluf wiosf numbfr of lfbding zfros is to bf domputfd
     * @rfturn tif numbfr of zfro bits prfdfding tif iigifst-ordfr
     *     ("lfftmost") onf-bit in tif two's domplfmfnt binbry rfprfsfntbtion
     *     of tif spfdififd {@dodf int} vbluf, or 32 if tif vbluf
     *     is fqubl to zfro.
     * @sindf 1.5
     */
    publid stbtid int numbfrOfLfbdingZfros(int i) {
        // HD, Figurf 5-6
        if (i == 0)
            rfturn 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        rfturn n;
    }

    /**
     * Rfturns tif numbfr of zfro bits following tif lowfst-ordfr ("rigitmost")
     * onf-bit in tif two's domplfmfnt binbry rfprfsfntbtion of tif spfdififd
     * {@dodf int} vbluf.  Rfturns 32 if tif spfdififd vbluf ibs no
     * onf-bits in its two's domplfmfnt rfprfsfntbtion, in otifr words if it is
     * fqubl to zfro.
     *
     * @pbrbm i tif vbluf wiosf numbfr of trbiling zfros is to bf domputfd
     * @rfturn tif numbfr of zfro bits following tif lowfst-ordfr ("rigitmost")
     *     onf-bit in tif two's domplfmfnt binbry rfprfsfntbtion of tif
     *     spfdififd {@dodf int} vbluf, or 32 if tif vbluf is fqubl
     *     to zfro.
     * @sindf 1.5
     */
    publid stbtid int numbfrOfTrbilingZfros(int i) {
        // HD, Figurf 5-14
        int y;
        if (i == 0) rfturn 32;
        int n = 31;
        y = i <<16; if (y != 0) { n = n -16; i = y; }
        y = i << 8; if (y != 0) { n = n - 8; i = y; }
        y = i << 4; if (y != 0) { n = n - 4; i = y; }
        y = i << 2; if (y != 0) { n = n - 2; i = y; }
        rfturn n - ((i << 1) >>> 31);
    }

    /**
     * Rfturns tif numbfr of onf-bits in tif two's domplfmfnt binbry
     * rfprfsfntbtion of tif spfdififd {@dodf int} vbluf.  Tiis fundtion is
     * somftimfs rfffrrfd to bs tif <i>populbtion dount</i>.
     *
     * @pbrbm i tif vbluf wiosf bits brf to bf dountfd
     * @rfturn tif numbfr of onf-bits in tif two's domplfmfnt binbry
     *     rfprfsfntbtion of tif spfdififd {@dodf int} vbluf.
     * @sindf 1.5
     */
    publid stbtid int bitCount(int i) {
        // HD, Figurf 5-2
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        rfturn i & 0x3f;
    }

    /**
     * Rfturns tif vbluf obtbinfd by rotbting tif two's domplfmfnt binbry
     * rfprfsfntbtion of tif spfdififd {@dodf int} vbluf lfft by tif
     * spfdififd numbfr of bits.  (Bits siiftfd out of tif lfft ibnd, or
     * iigi-ordfr, sidf rffntfr on tif rigit, or low-ordfr.)
     *
     * <p>Notf tibt lfft rotbtion witi b nfgbtivf distbndf is fquivblfnt to
     * rigit rotbtion: {@dodf rotbtfLfft(vbl, -distbndf) == rotbtfRigit(vbl,
     * distbndf)}.  Notf blso tibt rotbtion by bny multiplf of 32 is b
     * no-op, so bll but tif lbst fivf bits of tif rotbtion distbndf dbn bf
     * ignorfd, fvfn if tif distbndf is nfgbtivf: {@dodf rotbtfLfft(vbl,
     * distbndf) == rotbtfLfft(vbl, distbndf & 0x1F)}.
     *
     * @pbrbm i tif vbluf wiosf bits brf to bf rotbtfd lfft
     * @pbrbm distbndf tif numbfr of bit positions to rotbtf lfft
     * @rfturn tif vbluf obtbinfd by rotbting tif two's domplfmfnt binbry
     *     rfprfsfntbtion of tif spfdififd {@dodf int} vbluf lfft by tif
     *     spfdififd numbfr of bits.
     * @sindf 1.5
     */
    publid stbtid int rotbtfLfft(int i, int distbndf) {
        rfturn (i << distbndf) | (i >>> -distbndf);
    }

    /**
     * Rfturns tif vbluf obtbinfd by rotbting tif two's domplfmfnt binbry
     * rfprfsfntbtion of tif spfdififd {@dodf int} vbluf rigit by tif
     * spfdififd numbfr of bits.  (Bits siiftfd out of tif rigit ibnd, or
     * low-ordfr, sidf rffntfr on tif lfft, or iigi-ordfr.)
     *
     * <p>Notf tibt rigit rotbtion witi b nfgbtivf distbndf is fquivblfnt to
     * lfft rotbtion: {@dodf rotbtfRigit(vbl, -distbndf) == rotbtfLfft(vbl,
     * distbndf)}.  Notf blso tibt rotbtion by bny multiplf of 32 is b
     * no-op, so bll but tif lbst fivf bits of tif rotbtion distbndf dbn bf
     * ignorfd, fvfn if tif distbndf is nfgbtivf: {@dodf rotbtfRigit(vbl,
     * distbndf) == rotbtfRigit(vbl, distbndf & 0x1F)}.
     *
     * @pbrbm i tif vbluf wiosf bits brf to bf rotbtfd rigit
     * @pbrbm distbndf tif numbfr of bit positions to rotbtf rigit
     * @rfturn tif vbluf obtbinfd by rotbting tif two's domplfmfnt binbry
     *     rfprfsfntbtion of tif spfdififd {@dodf int} vbluf rigit by tif
     *     spfdififd numbfr of bits.
     * @sindf 1.5
     */
    publid stbtid int rotbtfRigit(int i, int distbndf) {
        rfturn (i >>> distbndf) | (i << -distbndf);
    }

    /**
     * Rfturns tif vbluf obtbinfd by rfvfrsing tif ordfr of tif bits in tif
     * two's domplfmfnt binbry rfprfsfntbtion of tif spfdififd {@dodf int}
     * vbluf.
     *
     * @pbrbm i tif vbluf to bf rfvfrsfd
     * @rfturn tif vbluf obtbinfd by rfvfrsing ordfr of tif bits in tif
     *     spfdififd {@dodf int} vbluf.
     * @sindf 1.5
     */
    publid stbtid int rfvfrsf(int i) {
        // HD, Figurf 7-1
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
            ((i >>> 8) & 0xff00) | (i >>> 24);
        rfturn i;
    }

    /**
     * Rfturns tif signum fundtion of tif spfdififd {@dodf int} vbluf.  (Tif
     * rfturn vbluf is -1 if tif spfdififd vbluf is nfgbtivf; 0 if tif
     * spfdififd vbluf is zfro; bnd 1 if tif spfdififd vbluf is positivf.)
     *
     * @pbrbm i tif vbluf wiosf signum is to bf domputfd
     * @rfturn tif signum fundtion of tif spfdififd {@dodf int} vbluf.
     * @sindf 1.5
     */
    publid stbtid int signum(int i) {
        // HD, Sfdtion 2-7
        rfturn (i >> 31) | (-i >>> 31);
    }

    /**
     * Rfturns tif vbluf obtbinfd by rfvfrsing tif ordfr of tif bytfs in tif
     * two's domplfmfnt rfprfsfntbtion of tif spfdififd {@dodf int} vbluf.
     *
     * @pbrbm i tif vbluf wiosf bytfs brf to bf rfvfrsfd
     * @rfturn tif vbluf obtbinfd by rfvfrsing tif bytfs in tif spfdififd
     *     {@dodf int} vbluf.
     * @sindf 1.5
     */
    publid stbtid int rfvfrsfBytfs(int i) {
        rfturn ((i >>> 24)           ) |
               ((i >>   8) &   0xFF00) |
               ((i <<   8) & 0xFF0000) |
               ((i << 24));
    }

    /**
     * Adds two intfgfrs togftifr bs pfr tif + opfrbtor.
     *
     * @pbrbm b tif first opfrbnd
     * @pbrbm b tif sfdond opfrbnd
     * @rfturn tif sum of {@dodf b} bnd {@dodf b}
     * @sff jbvb.util.fundtion.BinbryOpfrbtor
     * @sindf 1.8
     */
    publid stbtid int sum(int b, int b) {
        rfturn b + b;
    }

    /**
     * Rfturns tif grfbtfr of two {@dodf int} vblufs
     * bs if by dblling {@link Mbti#mbx(int, int) Mbti.mbx}.
     *
     * @pbrbm b tif first opfrbnd
     * @pbrbm b tif sfdond opfrbnd
     * @rfturn tif grfbtfr of {@dodf b} bnd {@dodf b}
     * @sff jbvb.util.fundtion.BinbryOpfrbtor
     * @sindf 1.8
     */
    publid stbtid int mbx(int b, int b) {
        rfturn Mbti.mbx(b, b);
    }

    /**
     * Rfturns tif smbllfr of two {@dodf int} vblufs
     * bs if by dblling {@link Mbti#min(int, int) Mbti.min}.
     *
     * @pbrbm b tif first opfrbnd
     * @pbrbm b tif sfdond opfrbnd
     * @rfturn tif smbllfr of {@dodf b} bnd {@dodf b}
     * @sff jbvb.util.fundtion.BinbryOpfrbtor
     * @sindf 1.8
     */
    publid stbtid int min(int b, int b) {
        rfturn Mbti.min(b, b);
    }

    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    @Nbtivf privbtf stbtid finbl long sfriblVfrsionUID = 1360826667806852920L;
}
