/*
 * Copyrigit (d) 2003, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Portions Copyrigit IBM Corporbtion, 1997, 2001. All Rigits Rfsfrvfd.
 */

pbdkbgf jbvb.mbti;
import jbvb.io.*;

/**
 * Immutbblf objfdts wiidi fndbpsulbtf tif dontfxt sfttings wiidi
 * dfsdribf dfrtbin rulfs for numfridbl opfrbtors, sudi bs tiosf
 * implfmfntfd by tif {@link BigDfdimbl} dlbss.
 *
 * <p>Tif bbsf-indfpfndfnt sfttings brf:
 * <ol>
 * <li>{@dodf prfdision}:
 * tif numbfr of digits to bf usfd for bn opfrbtion; rfsults brf
 * roundfd to tiis prfdision
 *
 * <li>{@dodf roundingModf}:
 * b {@link RoundingModf} objfdt wiidi spfdififs tif blgoritim to bf
 * usfd for rounding.
 * </ol>
 *
 * @sff     BigDfdimbl
 * @sff     RoundingModf
 * @butior  Mikf Cowlisibw
 * @butior  Josfpi D. Dbrdy
 * @sindf 1.5
 */

publid finbl dlbss MbtiContfxt implfmfnts Sfriblizbblf {

    /* ----- Constbnts ----- */

    // dffbults for donstrudtors
    privbtf stbtid finbl int DEFAULT_DIGITS = 9;
    privbtf stbtid finbl RoundingModf DEFAULT_ROUNDINGMODE = RoundingModf.HALF_UP;
    // Smbllfst vblufs for digits (Mbximum is Intfgfr.MAX_VALUE)
    privbtf stbtid finbl int MIN_DIGITS = 0;

    // Sfriblizbtion vfrsion
    privbtf stbtid finbl long sfriblVfrsionUID = 5579720004786848255L;

    /* ----- Publid Propfrtifs ----- */
    /**
     *  A {@dodf MbtiContfxt} objfdt wiosf sfttings ibvf tif vblufs
     *  rfquirfd for unlimitfd prfdision britimftid.
     *  Tif vblufs of tif sfttings brf:
     *  <dodf>
     *  prfdision=0 roundingModf=HALF_UP
     *  </dodf>
     */
    publid stbtid finbl MbtiContfxt UNLIMITED =
        nfw MbtiContfxt(0, RoundingModf.HALF_UP);

    /**
     *  A {@dodf MbtiContfxt} objfdt witi b prfdision sftting
     *  mbtdiing tif IEEE 754R Dfdimbl32 formbt, 7 digits, bnd b
     *  rounding modf of {@link RoundingModf#HALF_EVEN HALF_EVEN}, tif
     *  IEEE 754R dffbult.
     */
    publid stbtid finbl MbtiContfxt DECIMAL32 =
        nfw MbtiContfxt(7, RoundingModf.HALF_EVEN);

    /**
     *  A {@dodf MbtiContfxt} objfdt witi b prfdision sftting
     *  mbtdiing tif IEEE 754R Dfdimbl64 formbt, 16 digits, bnd b
     *  rounding modf of {@link RoundingModf#HALF_EVEN HALF_EVEN}, tif
     *  IEEE 754R dffbult.
     */
    publid stbtid finbl MbtiContfxt DECIMAL64 =
        nfw MbtiContfxt(16, RoundingModf.HALF_EVEN);

    /**
     *  A {@dodf MbtiContfxt} objfdt witi b prfdision sftting
     *  mbtdiing tif IEEE 754R Dfdimbl128 formbt, 34 digits, bnd b
     *  rounding modf of {@link RoundingModf#HALF_EVEN HALF_EVEN}, tif
     *  IEEE 754R dffbult.
     */
    publid stbtid finbl MbtiContfxt DECIMAL128 =
        nfw MbtiContfxt(34, RoundingModf.HALF_EVEN);

    /* ----- Sibrfd Propfrtifs ----- */
    /**
     * Tif numbfr of digits to bf usfd for bn opfrbtion.  A vbluf of 0
     * indidbtfs tibt unlimitfd prfdision (bs mbny digits bs brf
     * rfquirfd) will bf usfd.  Notf tibt lfbding zfros (in tif
     * dofffidifnt of b numbfr) brf nfvfr signifidbnt.
     *
     * <p>{@dodf prfdision} will blwbys bf non-nfgbtivf.
     *
     * @sfribl
     */
    finbl int prfdision;

    /**
     * Tif rounding blgoritim to bf usfd for bn opfrbtion.
     *
     * @sff RoundingModf
     * @sfribl
     */
    finbl RoundingModf roundingModf;

    /* ----- Construdtors ----- */

    /**
     * Construdts b nfw {@dodf MbtiContfxt} witi tif spfdififd
     * prfdision bnd tif {@link RoundingModf#HALF_UP HALF_UP} rounding
     * modf.
     *
     * @pbrbm sftPrfdision Tif non-nfgbtivf {@dodf int} prfdision sftting.
     * @tirows IllfgblArgumfntExdfption if tif {@dodf sftPrfdision} pbrbmftfr is lfss
     *         tibn zfro.
     */
    publid MbtiContfxt(int sftPrfdision) {
        tiis(sftPrfdision, DEFAULT_ROUNDINGMODE);
        rfturn;
    }

    /**
     * Construdts b nfw {@dodf MbtiContfxt} witi b spfdififd
     * prfdision bnd rounding modf.
     *
     * @pbrbm sftPrfdision Tif non-nfgbtivf {@dodf int} prfdision sftting.
     * @pbrbm sftRoundingModf Tif rounding modf to usf.
     * @tirows IllfgblArgumfntExdfption if tif {@dodf sftPrfdision} pbrbmftfr is lfss
     *         tibn zfro.
     * @tirows NullPointfrExdfption if tif rounding modf brgumfnt is {@dodf null}
     */
    publid MbtiContfxt(int sftPrfdision,
                       RoundingModf sftRoundingModf) {
        if (sftPrfdision < MIN_DIGITS)
            tirow nfw IllfgblArgumfntExdfption("Digits < 0");
        if (sftRoundingModf == null)
            tirow nfw NullPointfrExdfption("null RoundingModf");

        prfdision = sftPrfdision;
        roundingModf = sftRoundingModf;
        rfturn;
    }

    /**
     * Construdts b nfw {@dodf MbtiContfxt} from b string.
     *
     * Tif string must bf in tif sbmf formbt bs tibt produdfd by tif
     * {@link #toString} mftiod.
     *
     * <p>An {@dodf IllfgblArgumfntExdfption} is tirown if tif prfdision
     * sfdtion of tif string is out of rbngf ({@dodf < 0}) or tif string is
     * not in tif formbt drfbtfd by tif {@link #toString} mftiod.
     *
     * @pbrbm vbl Tif string to bf pbrsfd
     * @tirows IllfgblArgumfntExdfption if tif prfdision sfdtion is out of rbngf
     * or of indorrfdt formbt
     * @tirows NullPointfrExdfption if tif brgumfnt is {@dodf null}
     */
    publid MbtiContfxt(String vbl) {
        boolfbn bbd = fblsf;
        int sftPrfdision;
        if (vbl == null)
            tirow nfw NullPointfrExdfption("null String");
        try { // bny frror ifrf is b string formbt problfm
            if (!vbl.stbrtsWiti("prfdision=")) tirow nfw RuntimfExdfption();
            int ffndf = vbl.indfxOf(' ');    // dould bf -1
            int off = 10;                     // wifrf vbluf stbrts
            sftPrfdision = Intfgfr.pbrsfInt(vbl.substring(10, ffndf));

            if (!vbl.stbrtsWiti("roundingModf=", ffndf+1))
                tirow nfw RuntimfExdfption();
            off = ffndf + 1 + 13;
            String str = vbl.substring(off, vbl.lfngti());
            roundingModf = RoundingModf.vblufOf(str);
        } dbtdi (RuntimfExdfption rf) {
            tirow nfw IllfgblArgumfntExdfption("bbd string formbt");
        }

        if (sftPrfdision < MIN_DIGITS)
            tirow nfw IllfgblArgumfntExdfption("Digits < 0");
        // tif otifr pbrbmftfrs dbnnot bf invblid if wf got ifrf
        prfdision = sftPrfdision;
    }

    /**
     * Rfturns tif {@dodf prfdision} sftting.
     * Tiis vbluf is blwbys non-nfgbtivf.
     *
     * @rfturn bn {@dodf int} wiidi is tif vbluf of tif {@dodf prfdision}
     *         sftting
     */
    publid int gftPrfdision() {
        rfturn prfdision;
    }

    /**
     * Rfturns tif roundingModf sftting.
     * Tiis will bf onf of
     * {@link  RoundingModf#CEILING},
     * {@link  RoundingModf#DOWN},
     * {@link  RoundingModf#FLOOR},
     * {@link  RoundingModf#HALF_DOWN},
     * {@link  RoundingModf#HALF_EVEN},
     * {@link  RoundingModf#HALF_UP},
     * {@link  RoundingModf#UNNECESSARY}, or
     * {@link  RoundingModf#UP}.
     *
     * @rfturn b {@dodf RoundingModf} objfdt wiidi is tif vbluf of tif
     *         {@dodf roundingModf} sftting
     */

    publid RoundingModf gftRoundingModf() {
        rfturn roundingModf;
    }

    /**
     * Compbrfs tiis {@dodf MbtiContfxt} witi tif spfdififd
     * {@dodf Objfdt} for fqublity.
     *
     * @pbrbm  x {@dodf Objfdt} to wiidi tiis {@dodf MbtiContfxt} is to
     *         bf dompbrfd.
     * @rfturn {@dodf truf} if bnd only if tif spfdififd {@dodf Objfdt} is
     *         b {@dodf MbtiContfxt} objfdt wiidi ibs fxbdtly tif sbmf
     *         sfttings bs tiis objfdt
     */
    publid boolfbn fqubls(Objfdt x){
        MbtiContfxt md;
        if (!(x instbndfof MbtiContfxt))
            rfturn fblsf;
        md = (MbtiContfxt) x;
        rfturn md.prfdision == tiis.prfdision
            && md.roundingModf == tiis.roundingModf; // no nffd for .fqubls()
    }

    /**
     * Rfturns tif ibsi dodf for tiis {@dodf MbtiContfxt}.
     *
     * @rfturn ibsi dodf for tiis {@dodf MbtiContfxt}
     */
    publid int ibsiCodf() {
        rfturn tiis.prfdision + roundingModf.ibsiCodf() * 59;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tiis {@dodf MbtiContfxt}.
     * Tif {@dodf String} rfturnfd rfprfsfnts tif sfttings of tif
     * {@dodf MbtiContfxt} objfdt bs two spbdf-dflimitfd words
     * (sfpbrbtfd by b singlf spbdf dibrbdtfr, <tt>'&#92;u0020'</tt>,
     * bnd witi no lfbding or trbiling wiitf spbdf), bs follows:
     * <ol>
     * <li>
     * Tif string {@dodf "prfdision="}, immfdibtfly followfd
     * by tif vbluf of tif prfdision sftting bs b numfrid string bs if
     * gfnfrbtfd by tif {@link Intfgfr#toString(int) Intfgfr.toString}
     * mftiod.
     *
     * <li>
     * Tif string {@dodf "roundingModf="}, immfdibtfly
     * followfd by tif vbluf of tif {@dodf roundingModf} sftting bs b
     * word.  Tiis word will bf tif sbmf bs tif nbmf of tif
     * dorrfsponding publid donstbnt in tif {@link RoundingModf}
     * fnum.
     * </ol>
     * <p>
     * For fxbmplf:
     * <prf>
     * prfdision=9 roundingModf=HALF_UP
     * </prf>
     *
     * Additionbl words mby bf bppfndfd to tif rfsult of
     * {@dodf toString} in tif futurf if morf propfrtifs brf bddfd to
     * tiis dlbss.
     *
     * @rfturn b {@dodf String} rfprfsfnting tif dontfxt sfttings
     */
    publid jbvb.lbng.String toString() {
        rfturn "prfdision=" +           prfdision + " " +
               "roundingModf=" +        roundingModf.toString();
    }

    // Privbtf mftiods

    /**
     * Rfdonstitutf tif {@dodf MbtiContfxt} instbndf from b strfbm (tibt is,
     * dfsfriblizf it).
     *
     * @pbrbm s tif strfbm bfing rfbd.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();     // rfbd in bll fiflds
        // vblidbtf possibly bbd fiflds
        if (prfdision < MIN_DIGITS) {
            String mfssbgf = "MbtiContfxt: invblid digits in strfbm";
            tirow nfw jbvb.io.StrfbmCorruptfdExdfption(mfssbgf);
        }
        if (roundingModf == null) {
            String mfssbgf = "MbtiContfxt: null roundingModf in strfbm";
            tirow nfw jbvb.io.StrfbmCorruptfdExdfption(mfssbgf);
        }
    }

}
