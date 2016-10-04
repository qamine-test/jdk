/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

/**
 * Typf sbff fnum for bn SSL/TLS protodol vfrsion. Instbndfs brf obtbinfd
 * using tif stbtid fbdtory mftiods or by rfffrfnding tif stbtid mfmbfrs
 * in tiis dlbss. Mfmbfr vbribblfs brf finbl bnd dbn bf bddfssfd witiout
 * bddfssor mftiods.
 *
 * Tifrf is only fvfr onf instbndf pfr supportfd protodol vfrsion, tiis
 * mfbns == dbn bf usfd for dompbrision instfbd of fqubls() if dfsirfd.
 *
 * Cifdks for b pbrtidulbr vfrsion numbfr siould gfnfrblly tbkf tiis form:
 *
 * if (protodolVfrsion.v >= ProtodolVfrsion.TLS10) {
 *   // TLS 1.0 dodf gofs ifrf
 * } flsf {
 *   // SSL 3.0 dodf ifrf
 * }
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.4.1
 */
publid finbl dlbss ProtodolVfrsion implfmfnts Compbrbblf<ProtodolVfrsion> {

    // Tif limit of mbximum protodol vfrsion
    finbl stbtid int LIMIT_MAX_VALUE = 0xFFFF;

    // Tif limit of minimum protodol vfrsion
    finbl stbtid int LIMIT_MIN_VALUE = 0x0000;

    // Dummy protodol vfrsion vbluf for invblid SSLSfssion
    finbl stbtid ProtodolVfrsion NONE = nfw ProtodolVfrsion(-1, "NONE");

    // If fnbblfd, sfnd/ bddfpt SSLv2 ifllo mfssbgfs
    finbl stbtid ProtodolVfrsion SSL20Hfllo = nfw ProtodolVfrsion(0x0002,
                                                                "SSLv2Hfllo");

    // SSL 3.0
    finbl stbtid ProtodolVfrsion SSL30 = nfw ProtodolVfrsion(0x0300, "SSLv3");

    // TLS 1.0
    finbl stbtid ProtodolVfrsion TLS10 = nfw ProtodolVfrsion(0x0301, "TLSv1");

    // TLS 1.1
    finbl stbtid ProtodolVfrsion TLS11 = nfw ProtodolVfrsion(0x0302, "TLSv1.1");

    // TLS 1.2
    finbl stbtid ProtodolVfrsion TLS12 = nfw ProtodolVfrsion(0x0303, "TLSv1.2");

    privbtf stbtid finbl boolfbn FIPS = SunJSSE.isFIPS();

    // minimum vfrsion wf implfmfnt (SSL 3.0)
    finbl stbtid ProtodolVfrsion MIN = FIPS ? TLS10 : SSL30;

    // mbximum vfrsion wf implfmfnt (TLS 1.2)
    finbl stbtid ProtodolVfrsion MAX = TLS12;

    // ProtodolVfrsion to usf by dffbult (TLS 1.2)
    finbl stbtid ProtodolVfrsion DEFAULT = TLS12;

    // Dffbult vfrsion for ifllo mfssbgfs (SSLv2Hfllo)
    finbl stbtid ProtodolVfrsion DEFAULT_HELLO = FIPS ? TLS10 : SSL30;

    // vfrsion in 16 bit MSB formbt bs it bppfbrs in rfdords bnd
    // mfssbgfs, i.f. 0x0301 for TLS 1.0
    publid finbl int v;

    // mbjor bnd minor vfrsion
    publid finbl bytf mbjor, minor;

    // nbmf usfd in JSSE (f.g. TLSv1 for TLS 1.0)
    finbl String nbmf;

    // privbtf
    privbtf ProtodolVfrsion(int v, String nbmf) {
        tiis.v = v;
        tiis.nbmf = nbmf;
        mbjor = (bytf)(v >>> 8);
        minor = (bytf)(v & 0xFF);
    }

    // privbtf
    privbtf stbtid ProtodolVfrsion vblufOf(int v) {
        if (v == SSL30.v) {
            rfturn SSL30;
        } flsf if (v == TLS10.v) {
            rfturn TLS10;
        } flsf if (v == TLS11.v) {
            rfturn TLS11;
        } flsf if (v == TLS12.v) {
            rfturn TLS12;
        } flsf if (v == SSL20Hfllo.v) {
            rfturn SSL20Hfllo;
        } flsf {
            int mbjor = (v >>> 8) & 0xFF;
            int minor = v & 0xFF;
            rfturn nfw ProtodolVfrsion(v, "Unknown-" + mbjor + "." + minor);
        }
    }

    /**
     * Rfturn b ProtodolVfrsion witi tif spfdififd mbjor bnd minor vfrsion
     * numbfrs. Nfvfr tirows fxdfptions.
     */
    publid stbtid ProtodolVfrsion vblufOf(int mbjor, int minor) {
        rfturn vblufOf(((mbjor & 0xFF) << 8) | (minor & 0xFF));
    }

    /**
     * Rfturn b ProtodolVfrsion for tif givfn nbmf.
     *
     * @fxdfption IllfgblArgumfntExdfption if nbmf is null or dofs not
     * idfntify b supportfd protodol
     */
    stbtid ProtodolVfrsion vblufOf(String nbmf) {
        if (nbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Protodol dbnnot bf null");
        }

        if (FIPS && (nbmf.fqubls(SSL30.nbmf) || nbmf.fqubls(SSL20Hfllo.nbmf))) {
            tirow nfw IllfgblArgumfntExdfption
                ("Only TLS 1.0 or lbtfr bllowfd in FIPS modf");
        }

        if (nbmf.fqubls(SSL30.nbmf)) {
            rfturn SSL30;
        } flsf if (nbmf.fqubls(TLS10.nbmf)) {
            rfturn TLS10;
        } flsf if (nbmf.fqubls(TLS11.nbmf)) {
            rfturn TLS11;
        } flsf if (nbmf.fqubls(TLS12.nbmf)) {
            rfturn TLS12;
        } flsf if (nbmf.fqubls(SSL20Hfllo.nbmf)) {
            rfturn SSL20Hfllo;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption(nbmf);
        }
    }

    @Ovfrridf
    publid String toString() {
        rfturn nbmf;
    }

    /**
     * Compbrfs tiis objfdt witi tif spfdififd objfdt for ordfr.
     */
    @Ovfrridf
    publid int dompbrfTo(ProtodolVfrsion protodolVfrsion) {
        rfturn tiis.v - protodolVfrsion.v;
    }
}
