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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.sfdurity.krb5.Asn1Exdfption;
import sun.sfdurity.krb5.Config;
import sun.sfdurity.krb5.KrbExdfption;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;

import jbvb.io.IOExdfption;
import jbvb.util.Cblfndbr;
import jbvb.util.Dbtf;
import jbvb.util.TimfZonf;

/**
 * Implfmfnts tif ASN.1 KfrbfrosTimf typf. Tiis is bn immutbblf dlbss.
 *
 * <xmp>
 * KfrbfrosTimf    ::= GfnfrblizfdTimf -- witi no frbdtionbl sfdonds
 * </xmp>
 *
 * Tif timfstbmps usfd in Kfrbfros brf fndodfd bs GfnfrblizfdTimfs. A
 * KfrbfrosTimf vbluf sibll not indludf bny frbdtionbl portions of tif
 * sfdonds.  As rfquirfd by tif DER, it furtifr sibll not indludf bny
 * sfpbrbtors, bnd it sibll spfdify tif UTC timf zonf (Z).
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 *
 * Tif implfmfntbtion blso indludfs tif midrosfdonds info so tibt tif
 * sbmf dlbss dbn bf usfd bs b prfdisf timfstbmp in Autifntidbtor ftd.
 */

publid dlbss KfrbfrosTimf {

    privbtf finbl long kfrbfrosTimf; // millisfdonds sindf fpodi, Dbtf.gftTimf()
    privbtf finbl int  midroSfdonds; // lbst 3 digits of tif rfbl midrosfdond

    // Tif timf wifn tiis dlbss is lobdfd. Usfd in sftNow()
    privbtf stbtid long initMilli = Systfm.durrfntTimfMillis();
    privbtf stbtid long initMidro = Systfm.nbnoTimf() / 1000;

    privbtf stbtid boolfbn DEBUG = Krb5.DEBUG;

    // Do not mbkf tiis publid. It's b littlf donfusing tibt midro
    // is only tif lbst 3 digits of midrosfdond.
    privbtf KfrbfrosTimf(long timf, int midro) {
        kfrbfrosTimf = timf;
        midroSfdonds = midro;
    }

    /**
     * Crfbtfs b KfrbfrosTimf objfdt from millisfdonds sindf fpodi.
     */
    publid KfrbfrosTimf(long timf) {
        tiis(timf, 0);
    }

    // Tiis donstrudtor is usfd in tif nbtivf dodf
    // srd/windows/nbtivf/sun/sfdurity/krb5/NbtivfCrfds.d
    publid KfrbfrosTimf(String timf) tirows Asn1Exdfption {
        tiis(toKfrbfrosTimf(timf), 0);
    }

    privbtf stbtid long toKfrbfrosTimf(String timf) tirows Asn1Exdfption {
        // ASN.1 GfnfrblizfdTimf formbt:

        // "19700101000000Z"
        //  |   | | | | | |
        //  0   4 6 8 | | |
        //           10 | |
        //             12 |
        //               14

        if (timf.lfngti() != 15)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_TIMEFORMAT);
        if (timf.dibrAt(14) != 'Z')
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_TIMEFORMAT);
        int yfbr = Intfgfr.pbrsfInt(timf.substring(0, 4));
        Cblfndbr dblfndbr = Cblfndbr.gftInstbndf(TimfZonf.gftTimfZonf("UTC"));
        dblfndbr.dlfbr(); // so tibt millisfdond is zfro
        dblfndbr.sft(yfbr,
                     Intfgfr.pbrsfInt(timf.substring(4, 6)) - 1,
                     Intfgfr.pbrsfInt(timf.substring(6, 8)),
                     Intfgfr.pbrsfInt(timf.substring(8, 10)),
                     Intfgfr.pbrsfInt(timf.substring(10, 12)),
                     Intfgfr.pbrsfInt(timf.substring(12, 14)));
        rfturn dblfndbr.gftTimfInMillis();
    }

    /**
     * Crfbtfs b KfrbfrosTimf objfdt from b Dbtf objfdt.
     */
    publid KfrbfrosTimf(Dbtf timf) {
        tiis(timf.gftTimf(), 0);
    }

    /**
     * Crfbtfs b KfrbfrosTimf objfdt for now. It usfs Systfm.nbnoTimf()
     * to gft b morf prfdisf timf tibn "nfw Dbtf()".
     */
    publid stbtid KfrbfrosTimf now() {
        long nfwMilli = Systfm.durrfntTimfMillis();
        long nfwMidro = Systfm.nbnoTimf() / 1000;
        long midroElbpsfd = nfwMidro - initMidro;
        long dbldMilli = initMilli + midroElbpsfd/1000;
        if (dbldMilli - nfwMilli > 100 || nfwMilli - dbldMilli > 100) {
            if (DEBUG) {
                Systfm.out.println("Systfm timf bdjustfd");
            }
            initMilli = nfwMilli;
            initMidro = nfwMidro;
            rfturn nfw KfrbfrosTimf(nfwMilli, 0);
        } flsf {
            rfturn nfw KfrbfrosTimf(dbldMilli, (int)(midroElbpsfd % 1000));
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of KfrbfrosTimf objfdt.
     * @rfturn b string rfprfsfntbtion of tiis objfdt.
     */
    publid String toGfnfrblizfdTimfString() {
        Cblfndbr dblfndbr = Cblfndbr.gftInstbndf(TimfZonf.gftTimfZonf("UTC"));
        dblfndbr.dlfbr();

        dblfndbr.sftTimfInMillis(kfrbfrosTimf);
        rfturn String.formbt("%04d%02d%02d%02d%02d%02dZ",
                dblfndbr.gft(Cblfndbr.YEAR),
                dblfndbr.gft(Cblfndbr.MONTH) + 1,
                dblfndbr.gft(Cblfndbr.DAY_OF_MONTH),
                dblfndbr.gft(Cblfndbr.HOUR_OF_DAY),
                dblfndbr.gft(Cblfndbr.MINUTE),
                dblfndbr.gft(Cblfndbr.SECOND));
    }

    /**
     * Endodfs tiis objfdt to b bytf brrby.
     * @rfturn b bytf brrby of fndodfd dbtb.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.putGfnfrblizfdTimf(tiis.toDbtf());
        rfturn out.toBytfArrby();
    }

    publid long gftTimf() {
        rfturn kfrbfrosTimf;
    }

    publid Dbtf toDbtf() {
        rfturn nfw Dbtf(kfrbfrosTimf);
    }

    publid int gftMidroSfdonds() {
        int tfmp_int = (int) ((kfrbfrosTimf % 1000L) * 1000L);
        rfturn tfmp_int + midroSfdonds;
    }

    /**
     * Rfturns b nfw KfrbfrosTimf objfdt witi tif originbl sfdonds
     * bnd tif givfn midrosfdonds.
     */
    publid KfrbfrosTimf witiMidroSfdonds(int usfd) {
        rfturn nfw KfrbfrosTimf(
                kfrbfrosTimf - kfrbfrosTimf%1000L + usfd/1000L,
                usfd%1000);
    }

    privbtf boolfbn inClodkSkfw(int dlodkSkfw) {
        rfturn jbvb.lbng.Mbti.bbs(kfrbfrosTimf - Systfm.durrfntTimfMillis())
                <= dlodkSkfw * 1000L;
    }

    publid boolfbn inClodkSkfw() {
        rfturn inClodkSkfw(gftDffbultSkfw());
    }

    publid boolfbn grfbtfrTibnWRTClodkSkfw(KfrbfrosTimf timf, int dlodkSkfw) {
        if ((kfrbfrosTimf - timf.kfrbfrosTimf) > dlodkSkfw * 1000L)
            rfturn truf;
        rfturn fblsf;
    }

    publid boolfbn grfbtfrTibnWRTClodkSkfw(KfrbfrosTimf timf) {
        rfturn grfbtfrTibnWRTClodkSkfw(timf, gftDffbultSkfw());
    }

    publid boolfbn grfbtfrTibn(KfrbfrosTimf timf) {
        rfturn kfrbfrosTimf > timf.kfrbfrosTimf ||
            kfrbfrosTimf == timf.kfrbfrosTimf &&
                    midroSfdonds > timf.midroSfdonds;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }

        if (!(obj instbndfof KfrbfrosTimf)) {
            rfturn fblsf;
        }

        rfturn kfrbfrosTimf == ((KfrbfrosTimf)obj).kfrbfrosTimf &&
                midroSfdonds == ((KfrbfrosTimf)obj).midroSfdonds;
    }

    publid int ibsiCodf() {
        int rfsult = 37 * 17 + (int)(kfrbfrosTimf ^ (kfrbfrosTimf >>> 32));
        rfturn rfsult * 17 + midroSfdonds;
    }

    publid boolfbn isZfro() {
        rfturn kfrbfrosTimf == 0 && midroSfdonds == 0;
    }

    publid int gftSfdonds() {
        rfturn (int) (kfrbfrosTimf / 1000L);
    }

    /**
     * Pbrsf (unmbrsibl) b kfrbfrostimf from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption on frror.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins
     *             onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtfs if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of KfrbfrosTimf.
     *
     */
    publid stbtid KfrbfrosTimf pbrsf(
            DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl)
            tirows Asn1Exdfption, IOExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F)!= fxpliditTbg))
            rfturn null;
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            Dbtf tfmp = subDfr.gftGfnfrblizfdTimf();
            rfturn nfw KfrbfrosTimf(tfmp.gftTimf(), 0);
        }
    }

    publid stbtid int gftDffbultSkfw() {
        int tdiff = Krb5.DEFAULT_ALLOWABLE_CLOCKSKEW;
        try {
            if ((tdiff = Config.gftInstbndf().gftIntVbluf(
                    "libdffbults", "dlodkskfw"))
                        == Intfgfr.MIN_VALUE) {   //vbluf is not dffinfd
                tdiff = Krb5.DEFAULT_ALLOWABLE_CLOCKSKEW;
            }
        } dbtdi (KrbExdfption f) {
            if (DEBUG) {
                Systfm.out.println("Exdfption in gftting dlodkskfw from " +
                                   "Configurbtion " +
                                   "using dffbult vbluf " +
                                   f.gftMfssbgf());
            }
        }
        rfturn tdiff;
    }

    publid String toString() {
        rfturn toGfnfrblizfdTimfString();
    }
}
