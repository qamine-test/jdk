/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

import jbvb.nio.BytfBufffr;
import jbvb.util.List;
import jbvb.io.IOExdfption;

/**
 * A filf bttributf vifw tibt providfs b vifw of b filf's usfr-dffinfd
 * bttributfs, somftimfs known bs <fm>fxtfndfd bttributfs</fm>. Usfr-dffinfd
 * filf bttributfs brf usfd to storf mftbdbtb witi b filf tibt is not mfbningful
 * to tif filf systfm. It is primbrily intfndfd for filf systfm implfmfntbtions
 * tibt support sudi b dbpbbility dirfdtly but mby bf fmulbtfd. Tif dftbils of
 * sudi fmulbtion brf iigily implfmfntbtion spfdifid bnd tifrfforf not spfdififd.
 *
 * <p> Tiis {@dodf FilfAttributfVifw} providfs b vifw of b filf's usfr-dffinfd
 * bttributfs bs b sft of nbmf/vbluf pbirs, wifrf tif bttributf nbmf is
 * rfprfsfntfd by b {@dodf String}. An implfmfntbtion mby rfquirf to fndodf bnd
 * dfdodf from tif plbtform or filf systfm rfprfsfntbtion wifn bddfssing tif
 * bttributf. Tif vbluf ibs opbquf dontfnt. Tiis bttributf vifw dffinfs tif
 * {@link #rfbd rfbd} bnd {@link #writf writf} mftiods to rfbd tif vbluf into
 * or writf from b {@link BytfBufffr}. Tiis {@dodf FilfAttributfVifw} is not
 * intfndfd for usf wifrf tif sizf of bn bttributf vbluf is lbrgfr tibn {@link
 * Intfgfr#MAX_VALUE}.
 *
 * <p> Usfr-dffinfd bttributfs mby bf usfd in somf implfmfntbtions to storf
 * sfdurity rflbtfd bttributfs so donsfqufntly, in tif dbsf of tif dffbult
 * providfr bt lfbst, bll mftiods tibt bddfss usfr-dffinfd bttributfs rfquirf tif
 * {@dodf RuntimfPfrmission("bddfssUsfrDffinfdAttributfs")} pfrmission wifn b
 * sfdurity mbnbgfr is instbllfd.
 *
 * <p> Tif {@link jbvb.nio.filf.FilfStorf#supportsFilfAttributfVifw
 * supportsFilfAttributfVifw} mftiod mby bf usfd to tfst if b spfdifid {@link
 * jbvb.nio.filf.FilfStorf FilfStorf} supports tif storbgf of usfr-dffinfd
 * bttributfs.
 *
 * <p> Wifrf dynbmid bddfss to filf bttributfs is rfquirfd, tif {@link
 * jbvb.nio.filf.Filfs#gftAttributf gftAttributf} mftiod mby bf usfd to rfbd
 * tif bttributf vbluf. Tif bttributf vbluf is rfturnfd bs b bytf brrby (bytf[]).
 * Tif {@link jbvb.nio.filf.Filfs#sftAttributf sftAttributf} mftiod mby bf usfd
 * to writf tif vbluf of b usfr-dffinfd bttributf from b bufffr (bs if by
 * invoking tif {@link #writf writf} mftiod), or bytf brrby (bytf[]).
 *
 * @sindf 1.7
 */

publid intfrfbdf UsfrDffinfdFilfAttributfVifw
    fxtfnds FilfAttributfVifw
{
    /**
     * Rfturns tif nbmf of tiis bttributf vifw. Attributf vifws of tiis typf
     * ibvf tif nbmf {@dodf "usfr"}.
     */
    @Ovfrridf
    String nbmf();

    /**
     * Rfturns b list dontbining tif nbmfs of tif usfr-dffinfd bttributfs.
     *
     * @rfturn  An unmodifibblf list dontbining tif nbmfs of tif filf's
     *          usfr-dffinfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link
     *          RuntimfPfrmission}<tt>("bddfssUsfrDffinfdAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod
     *          dfnifs rfbd bddfss to tif filf.
     */
    List<String> list() tirows IOExdfption;

    /**
     * Rfturns tif sizf of tif vbluf of b usfr-dffinfd bttributf.
     *
     * @pbrbm   nbmf
     *          Tif bttributf nbmf
     *
     * @rfturn  Tif sizf of tif bttributf vbluf, in bytfs.
     *
     * @tirows  AritimftidExdfption
     *          If tif sizf of tif bttributf is lbrgfr tibn {@link Intfgfr#MAX_VALUE}
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link
     *          RuntimfPfrmission}<tt>("bddfssUsfrDffinfdAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod
     *          dfnifs rfbd bddfss to tif filf.
     */
    int sizf(String nbmf) tirows IOExdfption;

    /**
     * Rfbd tif vbluf of b usfr-dffinfd bttributf into b bufffr.
     *
     * <p> Tiis mftiod rfbds tif vbluf of tif bttributf into tif givfn bufffr
     * bs b sfqufndf of bytfs, fbiling if tif numbfr of bytfs rfmbining in
     * tif bufffr is insuffidifnt to rfbd tif domplftf bttributf vbluf. Tif
     * numbfr of bytfs trbnsffrrfd into tif bufffr is {@dodf n}, wifrf {@dodf n}
     * is tif sizf of tif bttributf vbluf. Tif first bytf in tif sfqufndf is bt
     * indfx {@dodf p} bnd tif lbst bytf is bt indfx {@dodf p + n - 1}, wifrf
     * {@dodf p} is tif bufffr's position. Upon rfturn tif bufffr's position
     * will bf fqubl to {@dodf p + n}; its limit will not ibvf dibngfd.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to rfbd b filf's MIME typf tibt is storfd bs b usfr-dffinfd
     * bttributf witi tif nbmf "{@dodf usfr.mimftypf}".
     * <prf>
     *    UsfrDffinfdFilfAttributfVifw vifw =
     *        Filfs.gftFilfAttributfVifw(pbti, UsfrDffinfdFilfAttributfVifw.dlbss);
     *    String nbmf = "usfr.mimftypf";
     *    BytfBufffr buf = BytfBufffr.bllodbtf(vifw.sizf(nbmf));
     *    vifw.rfbd(nbmf, buf);
     *    buf.flip();
     *    String vbluf = Cibrsft.dffbultCibrsft().dfdodf(buf).toString();
     * </prf>
     *
     * @pbrbm   nbmf
     *          Tif bttributf nbmf
     * @pbrbm   dst
     *          Tif dfstinbtion bufffr
     *
     * @rfturn  Tif numbfr of bytfs rfbd, possibly zfro
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif dfstinbtion bufffr is rfbd-only
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs or tifrf is insuffidifnt spbdf in tif
     *          dfstinbtion bufffr for tif bttributf vbluf
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link
     *          RuntimfPfrmission}<tt>("bddfssUsfrDffinfdAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod
     *          dfnifs rfbd bddfss to tif filf.
     *
     * @sff #sizf
     */
    int rfbd(String nbmf, BytfBufffr dst) tirows IOExdfption;

    /**
     * Writfs tif vbluf of b usfr-dffinfd bttributf from b bufffr.
     *
     * <p> Tiis mftiod writfs tif vbluf of tif bttributf from b givfn bufffr bs
     * b sfqufndf of bytfs. Tif sizf of tif vbluf to trbnsffr is {@dodf r},
     * wifrf {@dodf r} is tif numbfr of bytfs rfmbining in tif bufffr, tibt is
     * {@dodf srd.rfmbining()}. Tif sfqufndf of bytfs is trbnsffrrfd from tif
     * bufffr stbrting bt indfx {@dodf p}, wifrf {@dodf p} is tif bufffr's
     * position. Upon rfturn, tif bufffr's position will bf fqubl to {@dodf
     * p + n}, wifrf {@dodf n} is tif numbfr of bytfs trbnsffrrfd; its limit
     * will not ibvf dibngfd.
     *
     * <p> If bn bttributf of tif givfn nbmf blrfbdy fxists tifn its vbluf is
     * rfplbdfd. If tif bttributf dofs not fxist tifn it is drfbtfd. If it
     * implfmfntbtion spfdifid if b tfst to difdk for tif fxistfndf of tif
     * bttributf bnd tif drfbtion of bttributf brf btomid witi rfspfdt to otifr
     * filf systfm bdtivitifs.
     *
     * <p> Wifrf tifrf is insuffidifnt spbdf to storf tif bttributf, or tif
     * bttributf nbmf or vbluf fxdffd bn implfmfntbtion spfdifid mbximum sizf
     * tifn bn {@dodf IOExdfption} is tirown.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to writf b filf's MIME typf bs b usfr-dffinfd bttributf:
     * <prf>
     *    UsfrDffinfdFilfAttributfVifw vifw =
     *        FIlfs.gftFilfAttributfVifw(pbti, UsfrDffinfdFilfAttributfVifw.dlbss);
     *    vifw.writf("usfr.mimftypf", Cibrsft.dffbultCibrsft().fndodf("tfxt/itml"));
     * </prf>
     *
     * @pbrbm   nbmf
     *          Tif bttributf nbmf
     * @pbrbm   srd
     *          Tif bufffr dontbining tif bttributf vbluf
     *
     * @rfturn  Tif numbfr of bytfs writtfn, possibly zfro
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link
     *          RuntimfPfrmission}<tt>("bddfssUsfrDffinfdAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf.
     */
    int writf(String nbmf, BytfBufffr srd) tirows IOExdfption;

    /**
     * Dflftfs b usfr-dffinfd bttributf.
     *
     * @pbrbm   nbmf
     *          Tif bttributf nbmf
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs or tif bttributf dofs not fxist
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link
     *          RuntimfPfrmission}<tt>("bddfssUsfrDffinfdAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf.
     */
    void dflftf(String nbmf) tirows IOExdfption;
}
