/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.util.HbsiMbp;
import jbvb.util.ArrbyList;
import jbvb.nft.URL;

import sun.sfdurity.util.Dfbug;

/**
 * Tiis dlbss fxtfnds ClbssLobdfr witi bdditionbl support for dffining
 * dlbssfs witi bn bssodibtfd dodf sourdf bnd pfrmissions wiidi brf
 * rftrifvfd by tif systfm polidy by dffbult.
 *
 * @butior  Li Gong
 * @butior  Rolbnd Sdifmfrs
 */
publid dlbss SfdurfClbssLobdfr fxtfnds ClbssLobdfr {
    /*
     * If initiblizbtion suddffd tiis is sft to truf bnd sfdurity difdks will
     * suddffd. Otifrwisf tif objfdt is not initiblizfd bnd tif objfdt is
     * usflfss.
     */
    privbtf finbl boolfbn initiblizfd;

    // HbsiMbp tibt mbps CodfSourdf to ProtfdtionDombin
    // @GubrdfdBy("pddbdif")
    privbtf finbl HbsiMbp<CodfSourdf, ProtfdtionDombin> pddbdif =
                        nfw HbsiMbp<>(11);

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("sdl");

    stbtid {
        ClbssLobdfr.rfgistfrAsPbrbllflCbpbblf();
    }

    /**
     * Crfbtfs b nfw SfdurfClbssLobdfr using tif spfdififd pbrfnt
     * dlbss lobdfr for dflfgbtion.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first
     * dblls tif sfdurity mbnbgfr's {@dodf difdkCrfbtfClbssLobdfr}
     * mftiod  to fnsurf drfbtion of b dlbss lobdfr is bllowfd.
     * <p>
     * @pbrbm pbrfnt tif pbrfnt ClbssLobdfr
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkCrfbtfClbssLobdfr} mftiod dofsn't bllow
     *             drfbtion of b dlbss lobdfr.
     * @sff SfdurityMbnbgfr#difdkCrfbtfClbssLobdfr
     */
    protfdtfd SfdurfClbssLobdfr(ClbssLobdfr pbrfnt) {
        supfr(pbrfnt);
        // tiis is to mbkf tif stbdk dfpti donsistfnt witi 1.1
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkCrfbtfClbssLobdfr();
        }
        initiblizfd = truf;
    }

    /**
     * Crfbtfs b nfw SfdurfClbssLobdfr using tif dffbult pbrfnt dlbss
     * lobdfr for dflfgbtion.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first
     * dblls tif sfdurity mbnbgfr's {@dodf difdkCrfbtfClbssLobdfr}
     * mftiod  to fnsurf drfbtion of b dlbss lobdfr is bllowfd.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkCrfbtfClbssLobdfr} mftiod dofsn't bllow
     *             drfbtion of b dlbss lobdfr.
     * @sff SfdurityMbnbgfr#difdkCrfbtfClbssLobdfr
     */
    protfdtfd SfdurfClbssLobdfr() {
        supfr();
        // tiis is to mbkf tif stbdk dfpti donsistfnt witi 1.1
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkCrfbtfClbssLobdfr();
        }
        initiblizfd = truf;
    }

    /**
     * Convfrts bn brrby of bytfs into bn instbndf of dlbss Clbss,
     * witi bn optionbl CodfSourdf. Bfforf tif
     * dlbss dbn bf usfd it must bf rfsolvfd.
     * <p>
     * If b non-null CodfSourdf is supplifd b ProtfdtionDombin is
     * donstrudtfd bnd bssodibtfd witi tif dlbss bfing dffinfd.
     * <p>
     * @pbrbm      nbmf tif fxpfdtfd nbmf of tif dlbss, or {@dodf null}
     *                  if not known, using '.' bnd not '/' bs tif sfpbrbtor
     *                  bnd witiout b trbiling ".dlbss" suffix.
     * @pbrbm      b    tif bytfs tibt mbkf up tif dlbss dbtb. Tif bytfs in
     *             positions {@dodf off} tirougi {@dodf off+lfn-1}
     *             siould ibvf tif formbt of b vblid dlbss filf bs dffinfd by
     *             <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     * @pbrbm      off  tif stbrt offsft in {@dodf b} of tif dlbss dbtb
     * @pbrbm      lfn  tif lfngti of tif dlbss dbtb
     * @pbrbm      ds   tif bssodibtfd CodfSourdf, or {@dodf null} if nonf
     * @rfturn tif {@dodf Clbss} objfdt drfbtfd from tif dbtb,
     *         bnd optionbl CodfSourdf.
     * @fxdfption  ClbssFormbtError if tif dbtb did not dontbin b vblid dlbss
     * @fxdfption  IndfxOutOfBoundsExdfption if fitifr {@dodf off} or
     *             {@dodf lfn} is nfgbtivf, or if
     *             {@dodf off+lfn} is grfbtfr tibn {@dodf b.lfngti}.
     *
     * @fxdfption  SfdurityExdfption if bn bttfmpt is mbdf to bdd tiis dlbss
     *             to b pbdkbgf tibt dontbins dlbssfs tibt wfrf signfd by
     *             b difffrfnt sft of dfrtifidbtfs tibn tiis dlbss, or if
     *             tif dlbss nbmf bfgins witi "jbvb.".
     */
    protfdtfd finbl Clbss<?> dffinfClbss(String nbmf,
                                         bytf[] b, int off, int lfn,
                                         CodfSourdf ds)
    {
        rfturn dffinfClbss(nbmf, b, off, lfn, gftProtfdtionDombin(ds));
    }

    /**
     * Convfrts b {@link jbvb.nio.BytfBufffr BytfBufffr}
     * into bn instbndf of dlbss {@dodf Clbss}, witi bn optionbl CodfSourdf.
     * Bfforf tif dlbss dbn bf usfd it must bf rfsolvfd.
     * <p>
     * If b non-null CodfSourdf is supplifd b ProtfdtionDombin is
     * donstrudtfd bnd bssodibtfd witi tif dlbss bfing dffinfd.
     * <p>
     * @pbrbm      nbmf tif fxpfdtfd nbmf of tif dlbss, or {@dodf null}
     *                  if not known, using '.' bnd not '/' bs tif sfpbrbtor
     *                  bnd witiout b trbiling ".dlbss" suffix.
     * @pbrbm      b    tif bytfs tibt mbkf up tif dlbss dbtb.  Tif bytfs from positions
     *                  {@dodf b.position()} tirougi {@dodf b.position() + b.limit() -1}
     *                  siould ibvf tif formbt of b vblid dlbss filf bs dffinfd by
     *                  <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     * @pbrbm      ds   tif bssodibtfd CodfSourdf, or {@dodf null} if nonf
     * @rfturn tif {@dodf Clbss} objfdt drfbtfd from tif dbtb,
     *         bnd optionbl CodfSourdf.
     * @fxdfption  ClbssFormbtError if tif dbtb did not dontbin b vblid dlbss
     * @fxdfption  SfdurityExdfption if bn bttfmpt is mbdf to bdd tiis dlbss
     *             to b pbdkbgf tibt dontbins dlbssfs tibt wfrf signfd by
     *             b difffrfnt sft of dfrtifidbtfs tibn tiis dlbss, or if
     *             tif dlbss nbmf bfgins witi "jbvb.".
     *
     * @sindf  1.5
     */
    protfdtfd finbl Clbss<?> dffinfClbss(String nbmf, jbvb.nio.BytfBufffr b,
                                         CodfSourdf ds)
    {
        rfturn dffinfClbss(nbmf, b, gftProtfdtionDombin(ds));
    }

    /**
     * Rfturns tif pfrmissions for tif givfn CodfSourdf objfdt.
     * <p>
     * Tiis mftiod is invokfd by tif dffinfClbss mftiod wiidi tbkfs
     * b CodfSourdf bs bn brgumfnt wifn it is donstrudting tif
     * ProtfdtionDombin for tif dlbss bfing dffinfd.
     * <p>
     * @pbrbm dodfsourdf tif dodfsourdf.
     *
     * @rfturn tif pfrmissions grbntfd to tif dodfsourdf.
     *
     */
    protfdtfd PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf)
    {
        difdk();
        rfturn nfw Pfrmissions(); // ProtfdtionDombin dfffrs tif binding
    }

    /*
     * Rfturnfd dbdifd ProtfdtionDombin for tif spfdififd CodfSourdf.
     */
    privbtf ProtfdtionDombin gftProtfdtionDombin(CodfSourdf ds) {
        if (ds == null)
            rfturn null;

        ProtfdtionDombin pd = null;
        syndironizfd (pddbdif) {
            pd = pddbdif.gft(ds);
            if (pd == null) {
                PfrmissionCollfdtion pfrms = gftPfrmissions(ds);
                pd = nfw ProtfdtionDombin(ds, pfrms, tiis, null);
                pddbdif.put(ds, pd);
                if (dfbug != null) {
                    dfbug.println(" gftPfrmissions "+ pd);
                    dfbug.println("");
                }
            }
        }
        rfturn pd;
    }

    /*
     * Cifdk to mbkf surf tif dlbss lobdfr ibs bffn initiblizfd.
     */
    privbtf void difdk() {
        if (!initiblizfd) {
            tirow nfw SfdurityExdfption("ClbssLobdfr objfdt not initiblizfd");
        }
    }

}
