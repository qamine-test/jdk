/*
 * Copyrigit (d) 1994, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.GfnfridArrbyTypf;
import jbvb.lbng.rfflfdt.GfnfridDfdlbrbtion;
import jbvb.lbng.rfflfdt.Mfmbfr;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Exfdutbblf;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.lbng.rfflfdt.TypfVbribblf;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.AnnotbtfdTypf;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.io.InputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiSft;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.List;
import jbvb.util.Sft;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Objfdts;
import sun.misd.Unsbff;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.ConstbntPool;
import sun.rfflfdt.Rfflfdtion;
import sun.rfflfdt.RfflfdtionFbdtory;
import sun.rfflfdt.gfnfrids.fbdtory.CorfRfflfdtionFbdtory;
import sun.rfflfdt.gfnfrids.fbdtory.GfnfridsFbdtory;
import sun.rfflfdt.gfnfrids.rfpository.ClbssRfpository;
import sun.rfflfdt.gfnfrids.rfpository.MftiodRfpository;
import sun.rfflfdt.gfnfrids.rfpository.ConstrudtorRfpository;
import sun.rfflfdt.gfnfrids.sdopf.ClbssSdopf;
import sun.sfdurity.util.SfdurityConstbnts;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.rfflfdt.Proxy;
import sun.rfflfdt.bnnotbtion.*;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Instbndfs of tif dlbss {@dodf Clbss} rfprfsfnt dlbssfs bnd
 * intfrfbdfs in b running Jbvb bpplidbtion.  An fnum is b kind of
 * dlbss bnd bn bnnotbtion is b kind of intfrfbdf.  Evfry brrby blso
 * bflongs to b dlbss tibt is rfflfdtfd bs b {@dodf Clbss} objfdt
 * tibt is sibrfd by bll brrbys witi tif sbmf flfmfnt typf bnd numbfr
 * of dimfnsions.  Tif primitivf Jbvb typfs ({@dodf boolfbn},
 * {@dodf bytf}, {@dodf dibr}, {@dodf siort},
 * {@dodf int}, {@dodf long}, {@dodf flobt}, bnd
 * {@dodf doublf}), bnd tif kfyword {@dodf void} brf blso
 * rfprfsfntfd bs {@dodf Clbss} objfdts.
 *
 * <p> {@dodf Clbss} ibs no publid donstrudtor. Instfbd {@dodf Clbss}
 * objfdts brf donstrudtfd butombtidblly by tif Jbvb Virtubl Mbdiinf bs dlbssfs
 * brf lobdfd bnd by dblls to tif {@dodf dffinfClbss} mftiod in tif dlbss
 * lobdfr.
 *
 * <p> Tif following fxbmplf usfs b {@dodf Clbss} objfdt to print tif
 * dlbss nbmf of bn objfdt:
 *
 * <blodkquotf><prf>
 *     void printClbssNbmf(Objfdt obj) {
 *         Systfm.out.println("Tif dlbss of " + obj +
 *                            " is " + obj.gftClbss().gftNbmf());
 *     }
 * </prf></blodkquotf>
 *
 * <p> It is blso possiblf to gft tif {@dodf Clbss} objfdt for b nbmfd
 * typf (or for void) using b dlbss litfrbl.  Sff Sfdtion 15.8.2 of
 * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
 * For fxbmplf:
 *
 * <blodkquotf>
 *     {@dodf Systfm.out.println("Tif nbmf of dlbss Foo is: "+Foo.dlbss.gftNbmf());}
 * </blodkquotf>
 *
 * @pbrbm <T> tif typf of tif dlbss modflfd by tiis {@dodf Clbss}
 * objfdt.  For fxbmplf, tif typf of {@dodf String.dlbss} is {@dodf
 * Clbss<String>}.  Usf {@dodf Clbss<?>} if tif dlbss bfing modflfd is
 * unknown.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.lbng.ClbssLobdfr#dffinfClbss(bytf[], int, int)
 * @sindf   1.0
 */
publid finbl dlbss Clbss<T> implfmfnts jbvb.io.Sfriblizbblf,
                              GfnfridDfdlbrbtion,
                              Typf,
                              AnnotbtfdElfmfnt {
    privbtf stbtid finbl int ANNOTATION= 0x00002000;
    privbtf stbtid finbl int ENUM      = 0x00004000;
    privbtf stbtid finbl int SYNTHETIC = 0x00001000;

    privbtf stbtid nbtivf void rfgistfrNbtivfs();
    stbtid {
        rfgistfrNbtivfs();
    }

    /*
     * Privbtf donstrudtor. Only tif Jbvb Virtubl Mbdiinf drfbtfs Clbss objfdts.
     * Tiis donstrudtor is not usfd bnd prfvfnts tif dffbult donstrudtor bfing
     * gfnfrbtfd.
     */
    privbtf Clbss(ClbssLobdfr lobdfr, Clbss<?> brrbyComponfntTypf) {
        // Initiblizf finbl fifld for dlbssLobdfr.  Tif initiblizbtion vbluf of non-null
        // prfvfnts futurf JIT optimizbtions from bssuming tiis finbl fifld is null.
        dlbssLobdfr = lobdfr;
        domponfntTypf = brrbyComponfntTypf;
    }

    /**
     * Convfrts tif objfdt to b string. Tif string rfprfsfntbtion is tif
     * string "dlbss" or "intfrfbdf", followfd by b spbdf, bnd tifn by tif
     * fully qublififd nbmf of tif dlbss in tif formbt rfturnfd by
     * {@dodf gftNbmf}.  If tiis {@dodf Clbss} objfdt rfprfsfnts b
     * primitivf typf, tiis mftiod rfturns tif nbmf of tif primitivf typf.  If
     * tiis {@dodf Clbss} objfdt rfprfsfnts void tiis mftiod rfturns
     * "void".
     *
     * @rfturn b string rfprfsfntbtion of tiis dlbss objfdt.
     */
    publid String toString() {
        rfturn (isIntfrfbdf() ? "intfrfbdf " : (isPrimitivf() ? "" : "dlbss "))
            + gftNbmf();
    }

    /**
     * Rfturns b string dfsdribing tiis {@dodf Clbss}, indluding
     * informbtion bbout modififrs bnd typf pbrbmftfrs.
     *
     * Tif string is formbttfd bs b list of typf modififrs, if bny,
     * followfd by tif kind of typf (fmpty string for primitivf typfs
     * bnd {@dodf dlbss}, {@dodf fnum}, {@dodf intfrfbdf}, or
     * <dodf>&#64;</dodf>{@dodf intfrfbdf}, bs bppropribtf), followfd
     * by tif typf's nbmf, followfd by bn bnglf-brbdkftfd
     * dommb-sfpbrbtfd list of tif typf's typf pbrbmftfrs, if bny.
     *
     * A spbdf is usfd to sfpbrbtf modififrs from onf bnotifr bnd to
     * sfpbrbtf bny modififrs from tif kind of typf. Tif modififrs
     * oddur in dbnonidbl ordfr. If tifrf brf no typf pbrbmftfrs, tif
     * typf pbrbmftfr list is flidfd.
     *
     * <p>Notf tibt sindf informbtion bbout tif runtimf rfprfsfntbtion
     * of b typf is bfing gfnfrbtfd, modififrs not prfsfnt on tif
     * originbting sourdf dodf or illfgbl on tif originbting sourdf
     * dodf mby bf prfsfnt.
     *
     * @rfturn b string dfsdribing tiis {@dodf Clbss}, indluding
     * informbtion bbout modififrs bnd typf pbrbmftfrs
     *
     * @sindf 1.8
     */
    publid String toGfnfridString() {
        if (isPrimitivf()) {
            rfturn toString();
        } flsf {
            StringBuildfr sb = nfw StringBuildfr();

            // Clbss modififrs brf b supfrsft of intfrfbdf modififrs
            int modififrs = gftModififrs() & Modififr.dlbssModififrs();
            if (modififrs != 0) {
                sb.bppfnd(Modififr.toString(modififrs));
                sb.bppfnd(' ');
            }

            if (isAnnotbtion()) {
                sb.bppfnd('@');
            }
            if (isIntfrfbdf()) { // Notf: bll bnnotbtion typfs brf intfrfbdfs
                sb.bppfnd("intfrfbdf");
            } flsf {
                if (isEnum())
                    sb.bppfnd("fnum");
                flsf
                    sb.bppfnd("dlbss");
            }
            sb.bppfnd(' ');
            sb.bppfnd(gftNbmf());

            TypfVbribblf<?>[] typfpbrms = gftTypfPbrbmftfrs();
            if (typfpbrms.lfngti > 0) {
                boolfbn first = truf;
                sb.bppfnd('<');
                for(TypfVbribblf<?> typfpbrm: typfpbrms) {
                    if (!first)
                        sb.bppfnd(',');
                    sb.bppfnd(typfpbrm.gftTypfNbmf());
                    first = fblsf;
                }
                sb.bppfnd('>');
            }

            rfturn sb.toString();
        }
    }

    /**
     * Rfturns tif {@dodf Clbss} objfdt bssodibtfd witi tif dlbss or
     * intfrfbdf witi tif givfn string nbmf.  Invoking tiis mftiod is
     * fquivblfnt to:
     *
     * <blodkquotf>
     *  {@dodf Clbss.forNbmf(dlbssNbmf, truf, durrfntLobdfr)}
     * </blodkquotf>
     *
     * wifrf {@dodf durrfntLobdfr} dfnotfs tif dffining dlbss lobdfr of
     * tif durrfnt dlbss.
     *
     * <p> For fxbmplf, tif following dodf frbgmfnt rfturns tif
     * runtimf {@dodf Clbss} dfsdriptor for tif dlbss nbmfd
     * {@dodf jbvb.lbng.Tirfbd}:
     *
     * <blodkquotf>
     *   {@dodf Clbss t = Clbss.forNbmf("jbvb.lbng.Tirfbd")}
     * </blodkquotf>
     * <p>
     * A dbll to {@dodf forNbmf("X")} dbusfs tif dlbss nbmfd
     * {@dodf X} to bf initiblizfd.
     *
     * @pbrbm      dlbssNbmf   tif fully qublififd nbmf of tif dfsirfd dlbss.
     * @rfturn     tif {@dodf Clbss} objfdt for tif dlbss witi tif
     *             spfdififd nbmf.
     * @fxdfption LinkbgfError if tif linkbgf fbils
     * @fxdfption ExdfptionInInitiblizfrError if tif initiblizbtion provokfd
     *            by tiis mftiod fbils
     * @fxdfption ClbssNotFoundExdfption if tif dlbss dbnnot bf lodbtfd
     */
    @CbllfrSfnsitivf
    publid stbtid Clbss<?> forNbmf(String dlbssNbmf)
                tirows ClbssNotFoundExdfption {
        rfturn forNbmf0(dlbssNbmf, truf,
                        ClbssLobdfr.gftClbssLobdfr(Rfflfdtion.gftCbllfrClbss()));
    }


    /**
     * Rfturns tif {@dodf Clbss} objfdt bssodibtfd witi tif dlbss or
     * intfrfbdf witi tif givfn string nbmf, using tif givfn dlbss lobdfr.
     * Givfn tif fully qublififd nbmf for b dlbss or intfrfbdf (in tif sbmf
     * formbt rfturnfd by {@dodf gftNbmf}) tiis mftiod bttfmpts to
     * lodbtf, lobd, bnd link tif dlbss or intfrfbdf.  Tif spfdififd dlbss
     * lobdfr is usfd to lobd tif dlbss or intfrfbdf.  If tif pbrbmftfr
     * {@dodf lobdfr} is null, tif dlbss is lobdfd tirougi tif bootstrbp
     * dlbss lobdfr.  Tif dlbss is initiblizfd only if tif
     * {@dodf initiblizf} pbrbmftfr is {@dodf truf} bnd if it ibs
     * not bffn initiblizfd fbrlifr.
     *
     * <p> If {@dodf nbmf} dfnotfs b primitivf typf or void, bn bttfmpt
     * will bf mbdf to lodbtf b usfr-dffinfd dlbss in tif unnbmfd pbdkbgf wiosf
     * nbmf is {@dodf nbmf}. Tifrfforf, tiis mftiod dbnnot bf usfd to
     * obtbin bny of tif {@dodf Clbss} objfdts rfprfsfnting primitivf
     * typfs or void.
     *
     * <p> If {@dodf nbmf} dfnotfs bn brrby dlbss, tif domponfnt typf of
     * tif brrby dlbss is lobdfd but not initiblizfd.
     *
     * <p> For fxbmplf, in bn instbndf mftiod tif fxprfssion:
     *
     * <blodkquotf>
     *  {@dodf Clbss.forNbmf("Foo")}
     * </blodkquotf>
     *
     * is fquivblfnt to:
     *
     * <blodkquotf>
     *  {@dodf Clbss.forNbmf("Foo", truf, tiis.gftClbss().gftClbssLobdfr())}
     * </blodkquotf>
     *
     * Notf tibt tiis mftiod tirows frrors rflbtfd to lobding, linking or
     * initiblizing bs spfdififd in Sfdtions 12.2, 12.3 bnd 12.4 of <fm>Tif
     * Jbvb Lbngubgf Spfdifidbtion</fm>.
     * Notf tibt tiis mftiod dofs not difdk wiftifr tif rfqufstfd dlbss
     * is bddfssiblf to its dbllfr.
     *
     * <p> If tif {@dodf lobdfr} is {@dodf null}, bnd b sfdurity
     * mbnbgfr is prfsfnt, bnd tif dbllfr's dlbss lobdfr is not null, tifn tiis
     * mftiod dblls tif sfdurity mbnbgfr's {@dodf difdkPfrmission} mftiod
     * witi b {@dodf RuntimfPfrmission("gftClbssLobdfr")} pfrmission to
     * fnsurf it's ok to bddfss tif bootstrbp dlbss lobdfr.
     *
     * @pbrbm nbmf       fully qublififd nbmf of tif dfsirfd dlbss
     * @pbrbm initiblizf if {@dodf truf} tif dlbss will bf initiblizfd.
     *                   Sff Sfdtion 12.4 of <fm>Tif Jbvb Lbngubgf Spfdifidbtion</fm>.
     * @pbrbm lobdfr     dlbss lobdfr from wiidi tif dlbss must bf lobdfd
     * @rfturn           dlbss objfdt rfprfsfnting tif dfsirfd dlbss
     *
     * @fxdfption LinkbgfError if tif linkbgf fbils
     * @fxdfption ExdfptionInInitiblizfrError if tif initiblizbtion provokfd
     *            by tiis mftiod fbils
     * @fxdfption ClbssNotFoundExdfption if tif dlbss dbnnot bf lodbtfd by
     *            tif spfdififd dlbss lobdfr
     *
     * @sff       jbvb.lbng.Clbss#forNbmf(String)
     * @sff       jbvb.lbng.ClbssLobdfr
     * @sindf     1.2
     */
    @CbllfrSfnsitivf
    publid stbtid Clbss<?> forNbmf(String nbmf, boolfbn initiblizf,
                                   ClbssLobdfr lobdfr)
        tirows ClbssNotFoundExdfption
    {
        if (sun.misd.VM.isSystfmDombinLobdfr(lobdfr)) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                ClbssLobdfr ddl = ClbssLobdfr.gftClbssLobdfr(Rfflfdtion.gftCbllfrClbss());
                if (!sun.misd.VM.isSystfmDombinLobdfr(ddl)) {
                    sm.difdkPfrmission(
                        SfdurityConstbnts.GET_CLASSLOADER_PERMISSION);
                }
            }
        }
        rfturn forNbmf0(nbmf, initiblizf, lobdfr);
    }

    /** Cbllfd bftfr sfdurity difdks ibvf bffn mbdf. */
    privbtf stbtid nbtivf Clbss<?> forNbmf0(String nbmf, boolfbn initiblizf,
                                            ClbssLobdfr lobdfr)
        tirows ClbssNotFoundExdfption;

    /**
     * Crfbtfs b nfw instbndf of tif dlbss rfprfsfntfd by tiis {@dodf Clbss}
     * objfdt.  Tif dlbss is instbntibtfd bs if by b {@dodf nfw}
     * fxprfssion witi bn fmpty brgumfnt list.  Tif dlbss is initiblizfd if it
     * ibs not blrfbdy bffn initiblizfd.
     *
     * <p>Notf tibt tiis mftiod propbgbtfs bny fxdfption tirown by tif
     * nullbry donstrudtor, indluding b difdkfd fxdfption.  Usf of
     * tiis mftiod ffffdtivfly bypbssfs tif dompilf-timf fxdfption
     * difdking tibt would otifrwisf bf pfrformfd by tif dompilfr.
     * Tif {@link
     * jbvb.lbng.rfflfdt.Construdtor#nfwInstbndf(jbvb.lbng.Objfdt...)
     * Construdtor.nfwInstbndf} mftiod bvoids tiis problfm by wrbpping
     * bny fxdfption tirown by tif donstrudtor in b (difdkfd) {@link
     * jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption}.
     *
     * @rfturn  b nfwly bllodbtfd instbndf of tif dlbss rfprfsfntfd by tiis
     *          objfdt.
     * @tirows  IllfgblAddfssExdfption  if tif dlbss or its nullbry
     *          donstrudtor is not bddfssiblf.
     * @tirows  InstbntibtionExdfption
     *          if tiis {@dodf Clbss} rfprfsfnts bn bbstrbdt dlbss,
     *          bn intfrfbdf, bn brrby dlbss, b primitivf typf, or void;
     *          or if tif dlbss ibs no nullbry donstrudtor;
     *          or if tif instbntibtion fbils for somf otifr rfbson.
     * @tirows  ExdfptionInInitiblizfrError if tif initiblizbtion
     *          provokfd by tiis mftiod fbils.
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *          tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *          invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *          of tiis dlbss.
     */
    @CbllfrSfnsitivf
    publid T nfwInstbndf()
        tirows InstbntibtionExdfption, IllfgblAddfssExdfption
    {
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), fblsf);
        }

        // NOTE: tif following dodf mby not bf stridtly dorrfdt undfr
        // tif durrfnt Jbvb mfmory modfl.

        // Construdtor lookup
        if (dbdifdConstrudtor == null) {
            if (tiis == Clbss.dlbss) {
                tirow nfw IllfgblAddfssExdfption(
                    "Cbn not dbll nfwInstbndf() on tif Clbss for jbvb.lbng.Clbss"
                );
            }
            try {
                Clbss<?>[] fmpty = {};
                finbl Construdtor<T> d = gftConstrudtor0(fmpty, Mfmbfr.DECLARED);
                // Disbblf bddfssibility difdks on tif donstrudtor
                // sindf wf ibvf to do tif sfdurity difdk ifrf bnywby
                // (tif stbdk dfpti is wrong for tif Construdtor's
                // sfdurity difdk to work)
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                                d.sftAddfssiblf(truf);
                                rfturn null;
                            }
                        });
                dbdifdConstrudtor = d;
            } dbtdi (NoSudiMftiodExdfption f) {
                tirow (InstbntibtionExdfption)
                    nfw InstbntibtionExdfption(gftNbmf()).initCbusf(f);
            }
        }
        Construdtor<T> tmpConstrudtor = dbdifdConstrudtor;
        // Sfdurity difdk (sbmf bs in jbvb.lbng.rfflfdt.Construdtor)
        int modififrs = tmpConstrudtor.gftModififrs();
        if (!Rfflfdtion.quidkCifdkMfmbfrAddfss(tiis, modififrs)) {
            Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
            if (nfwInstbndfCbllfrCbdif != dbllfr) {
                Rfflfdtion.fnsurfMfmbfrAddfss(dbllfr, tiis, null, modififrs);
                nfwInstbndfCbllfrCbdif = dbllfr;
            }
        }
        // Run donstrudtor
        try {
            rfturn tmpConstrudtor.nfwInstbndf((Objfdt[])null);
        } dbtdi (InvodbtionTbrgftExdfption f) {
            Unsbff.gftUnsbff().tirowExdfption(f.gftTbrgftExdfption());
            // Not rfbdifd
            rfturn null;
        }
    }
    privbtf volbtilf trbnsifnt Construdtor<T> dbdifdConstrudtor;
    privbtf volbtilf trbnsifnt Clbss<?>       nfwInstbndfCbllfrCbdif;


    /**
     * Dftfrminfs if tif spfdififd {@dodf Objfdt} is bssignmfnt-dompbtiblf
     * witi tif objfdt rfprfsfntfd by tiis {@dodf Clbss}.  Tiis mftiod is
     * tif dynbmid fquivblfnt of tif Jbvb lbngubgf {@dodf instbndfof}
     * opfrbtor. Tif mftiod rfturns {@dodf truf} if tif spfdififd
     * {@dodf Objfdt} brgumfnt is non-null bnd dbn bf dbst to tif
     * rfffrfndf typf rfprfsfntfd by tiis {@dodf Clbss} objfdt witiout
     * rbising b {@dodf ClbssCbstExdfption.} It rfturns {@dodf fblsf}
     * otifrwisf.
     *
     * <p> Spfdifidblly, if tiis {@dodf Clbss} objfdt rfprfsfnts b
     * dfdlbrfd dlbss, tiis mftiod rfturns {@dodf truf} if tif spfdififd
     * {@dodf Objfdt} brgumfnt is bn instbndf of tif rfprfsfntfd dlbss (or
     * of bny of its subdlbssfs); it rfturns {@dodf fblsf} otifrwisf. If
     * tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby dlbss, tiis mftiod
     * rfturns {@dodf truf} if tif spfdififd {@dodf Objfdt} brgumfnt
     * dbn bf donvfrtfd to bn objfdt of tif brrby dlbss by bn idfntity
     * donvfrsion or by b widfning rfffrfndf donvfrsion; it rfturns
     * {@dodf fblsf} otifrwisf. If tiis {@dodf Clbss} objfdt
     * rfprfsfnts bn intfrfbdf, tiis mftiod rfturns {@dodf truf} if tif
     * dlbss or bny supfrdlbss of tif spfdififd {@dodf Objfdt} brgumfnt
     * implfmfnts tiis intfrfbdf; it rfturns {@dodf fblsf} otifrwisf. If
     * tiis {@dodf Clbss} objfdt rfprfsfnts b primitivf typf, tiis mftiod
     * rfturns {@dodf fblsf}.
     *
     * @pbrbm   obj tif objfdt to difdk
     * @rfturn  truf if {@dodf obj} is bn instbndf of tiis dlbss
     *
     * @sindf 1.1
     */
    publid nbtivf boolfbn isInstbndf(Objfdt obj);


    /**
     * Dftfrminfs if tif dlbss or intfrfbdf rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt is fitifr tif sbmf bs, or is b supfrdlbss or
     * supfrintfrfbdf of, tif dlbss or intfrfbdf rfprfsfntfd by tif spfdififd
     * {@dodf Clbss} pbrbmftfr. It rfturns {@dodf truf} if so;
     * otifrwisf it rfturns {@dodf fblsf}. If tiis {@dodf Clbss}
     * objfdt rfprfsfnts b primitivf typf, tiis mftiod rfturns
     * {@dodf truf} if tif spfdififd {@dodf Clbss} pbrbmftfr is
     * fxbdtly tiis {@dodf Clbss} objfdt; otifrwisf it rfturns
     * {@dodf fblsf}.
     *
     * <p> Spfdifidblly, tiis mftiod tfsts wiftifr tif typf rfprfsfntfd by tif
     * spfdififd {@dodf Clbss} pbrbmftfr dbn bf donvfrtfd to tif typf
     * rfprfsfntfd by tiis {@dodf Clbss} objfdt vib bn idfntity donvfrsion
     * or vib b widfning rfffrfndf donvfrsion. Sff <fm>Tif Jbvb Lbngubgf
     * Spfdifidbtion</fm>, sfdtions 5.1.1 bnd 5.1.4 , for dftbils.
     *
     * @pbrbm dls tif {@dodf Clbss} objfdt to bf difdkfd
     * @rfturn tif {@dodf boolfbn} vbluf indidbting wiftifr objfdts of tif
     * typf {@dodf dls} dbn bf bssignfd to objfdts of tiis dlbss
     * @fxdfption NullPointfrExdfption if tif spfdififd Clbss pbrbmftfr is
     *            null.
     * @sindf 1.1
     */
    publid nbtivf boolfbn isAssignbblfFrom(Clbss<?> dls);


    /**
     * Dftfrminfs if tif spfdififd {@dodf Clbss} objfdt rfprfsfnts bn
     * intfrfbdf typf.
     *
     * @rfturn  {@dodf truf} if tiis objfdt rfprfsfnts bn intfrfbdf;
     *          {@dodf fblsf} otifrwisf.
     */
    publid nbtivf boolfbn isIntfrfbdf();


    /**
     * Dftfrminfs if tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby dlbss.
     *
     * @rfturn  {@dodf truf} if tiis objfdt rfprfsfnts bn brrby dlbss;
     *          {@dodf fblsf} otifrwisf.
     * @sindf   1.1
     */
    publid nbtivf boolfbn isArrby();


    /**
     * Dftfrminfs if tif spfdififd {@dodf Clbss} objfdt rfprfsfnts b
     * primitivf typf.
     *
     * <p> Tifrf brf ninf prfdffinfd {@dodf Clbss} objfdts to rfprfsfnt
     * tif figit primitivf typfs bnd void.  Tifsf brf drfbtfd by tif Jbvb
     * Virtubl Mbdiinf, bnd ibvf tif sbmf nbmfs bs tif primitivf typfs tibt
     * tify rfprfsfnt, nbmfly {@dodf boolfbn}, {@dodf bytf},
     * {@dodf dibr}, {@dodf siort}, {@dodf int},
     * {@dodf long}, {@dodf flobt}, bnd {@dodf doublf}.
     *
     * <p> Tifsf objfdts mby only bf bddfssfd vib tif following publid stbtid
     * finbl vbribblfs, bnd brf tif only {@dodf Clbss} objfdts for wiidi
     * tiis mftiod rfturns {@dodf truf}.
     *
     * @rfturn truf if bnd only if tiis dlbss rfprfsfnts b primitivf typf
     *
     * @sff     jbvb.lbng.Boolfbn#TYPE
     * @sff     jbvb.lbng.Cibrbdtfr#TYPE
     * @sff     jbvb.lbng.Bytf#TYPE
     * @sff     jbvb.lbng.Siort#TYPE
     * @sff     jbvb.lbng.Intfgfr#TYPE
     * @sff     jbvb.lbng.Long#TYPE
     * @sff     jbvb.lbng.Flobt#TYPE
     * @sff     jbvb.lbng.Doublf#TYPE
     * @sff     jbvb.lbng.Void#TYPE
     * @sindf 1.1
     */
    publid nbtivf boolfbn isPrimitivf();

    /**
     * Rfturns truf if tiis {@dodf Clbss} objfdt rfprfsfnts bn bnnotbtion
     * typf.  Notf tibt if tiis mftiod rfturns truf, {@link #isIntfrfbdf()}
     * would blso rfturn truf, bs bll bnnotbtion typfs brf blso intfrfbdfs.
     *
     * @rfturn {@dodf truf} if tiis dlbss objfdt rfprfsfnts bn bnnotbtion
     *      typf; {@dodf fblsf} otifrwisf
     * @sindf 1.5
     */
    publid boolfbn isAnnotbtion() {
        rfturn (gftModififrs() & ANNOTATION) != 0;
    }

    /**
     * Rfturns {@dodf truf} if tiis dlbss is b syntiftid dlbss;
     * rfturns {@dodf fblsf} otifrwisf.
     * @rfturn {@dodf truf} if bnd only if tiis dlbss is b syntiftid dlbss bs
     *         dffinfd by tif Jbvb Lbngubgf Spfdifidbtion.
     * @jls 13.1 Tif Form of b Binbry
     * @sindf 1.5
     */
    publid boolfbn isSyntiftid() {
        rfturn (gftModififrs() & SYNTHETIC) != 0;
    }

    /**
     * Rfturns tif  nbmf of tif fntity (dlbss, intfrfbdf, brrby dlbss,
     * primitivf typf, or void) rfprfsfntfd by tiis {@dodf Clbss} objfdt,
     * bs b {@dodf String}.
     *
     * <p> If tiis dlbss objfdt rfprfsfnts b rfffrfndf typf tibt is not bn
     * brrby typf tifn tif binbry nbmf of tif dlbss is rfturnfd, bs spfdififd
     * by
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * <p> If tiis dlbss objfdt rfprfsfnts b primitivf typf or void, tifn tif
     * nbmf rfturnfd is b {@dodf String} fqubl to tif Jbvb lbngubgf
     * kfyword dorrfsponding to tif primitivf typf or void.
     *
     * <p> If tiis dlbss objfdt rfprfsfnts b dlbss of brrbys, tifn tif intfrnbl
     * form of tif nbmf donsists of tif nbmf of tif flfmfnt typf prfdfdfd by
     * onf or morf '{@dodf [}' dibrbdtfrs rfprfsfnting tif dfpti of tif brrby
     * nfsting.  Tif fndoding of flfmfnt typf nbmfs is bs follows:
     *
     * <blodkquotf><tbblf summbry="Elfmfnt typfs bnd fndodings">
     * <tr><ti> Elfmfnt Typf <ti> &nbsp;&nbsp;&nbsp; <ti> Endoding
     * <tr><td> boolfbn      <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> Z
     * <tr><td> bytf         <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> B
     * <tr><td> dibr         <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> C
     * <tr><td> dlbss or intfrfbdf
     *                       <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> L<i>dlbssnbmf</i>;
     * <tr><td> doublf       <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> D
     * <tr><td> flobt        <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> F
     * <tr><td> int          <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> I
     * <tr><td> long         <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> J
     * <tr><td> siort        <td> &nbsp;&nbsp;&nbsp; <td blign=dfntfr> S
     * </tbblf></blodkquotf>
     *
     * <p> Tif dlbss or intfrfbdf nbmf <i>dlbssnbmf</i> is tif binbry nbmf of
     * tif dlbss spfdififd bbovf.
     *
     * <p> Exbmplfs:
     * <blodkquotf><prf>
     * String.dlbss.gftNbmf()
     *     rfturns "jbvb.lbng.String"
     * bytf.dlbss.gftNbmf()
     *     rfturns "bytf"
     * (nfw Objfdt[3]).gftClbss().gftNbmf()
     *     rfturns "[Ljbvb.lbng.Objfdt;"
     * (nfw int[3][4][5][6][7][8][9]).gftClbss().gftNbmf()
     *     rfturns "[[[[[[[I"
     * </prf></blodkquotf>
     *
     * @rfturn  tif nbmf of tif dlbss or intfrfbdf
     *          rfprfsfntfd by tiis objfdt.
     */
    publid String gftNbmf() {
        String nbmf = tiis.nbmf;
        if (nbmf == null)
            tiis.nbmf = nbmf = gftNbmf0();
        rfturn nbmf;
    }

    // dbdif tif nbmf to rfdudf tif numbfr of dblls into tif VM
    privbtf trbnsifnt String nbmf;
    privbtf nbtivf String gftNbmf0();

    /**
     * Rfturns tif dlbss lobdfr for tif dlbss.  Somf implfmfntbtions mby usf
     * null to rfprfsfnt tif bootstrbp dlbss lobdfr. Tiis mftiod will rfturn
     * null in sudi implfmfntbtions if tiis dlbss wbs lobdfd by tif bootstrbp
     * dlbss lobdfr.
     *
     * <p> If b sfdurity mbnbgfr is prfsfnt, bnd tif dbllfr's dlbss lobdfr is
     * not null bnd tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn bndfstor of
     * tif dlbss lobdfr for tif dlbss wiosf dlbss lobdfr is rfqufstfd, tifn
     * tiis mftiod dblls tif sfdurity mbnbgfr's {@dodf difdkPfrmission}
     * mftiod witi b {@dodf RuntimfPfrmission("gftClbssLobdfr")}
     * pfrmission to fnsurf it's ok to bddfss tif dlbss lobdfr for tif dlbss.
     *
     * <p>If tiis objfdt
     * rfprfsfnts b primitivf typf or void, null is rfturnfd.
     *
     * @rfturn  tif dlbss lobdfr tibt lobdfd tif dlbss or intfrfbdf
     *          rfprfsfntfd by tiis objfdt.
     * @tirows SfdurityExdfption
     *    if b sfdurity mbnbgfr fxists bnd its
     *    {@dodf difdkPfrmission} mftiod dfnifs
     *    bddfss to tif dlbss lobdfr for tif dlbss.
     * @sff jbvb.lbng.ClbssLobdfr
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.lbng.RuntimfPfrmission
     */
    @CbllfrSfnsitivf
    publid ClbssLobdfr gftClbssLobdfr() {
        ClbssLobdfr dl = gftClbssLobdfr0();
        if (dl == null)
            rfturn null;
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            ClbssLobdfr.difdkClbssLobdfrPfrmission(dl, Rfflfdtion.gftCbllfrClbss());
        }
        rfturn dl;
    }

    // Pbdkbgf-privbtf to bllow ClbssLobdfr bddfss
    ClbssLobdfr gftClbssLobdfr0() { rfturn dlbssLobdfr; }

    // Initiblizfd in JVM not by privbtf donstrudtor
    privbtf finbl ClbssLobdfr dlbssLobdfr;

    /**
     * Rfturns bn brrby of {@dodf TypfVbribblf} objfdts tibt rfprfsfnt tif
     * typf vbribblfs dfdlbrfd by tif gfnfrid dfdlbrbtion rfprfsfntfd by tiis
     * {@dodf GfnfridDfdlbrbtion} objfdt, in dfdlbrbtion ordfr.  Rfturns bn
     * brrby of lfngti 0 if tif undfrlying gfnfrid dfdlbrbtion dfdlbrfs no typf
     * vbribblfs.
     *
     * @rfturn bn brrby of {@dodf TypfVbribblf} objfdts tibt rfprfsfnt
     *     tif typf vbribblfs dfdlbrfd by tiis gfnfrid dfdlbrbtion
     * @tirows jbvb.lbng.rfflfdt.GfnfridSignbturfFormbtError if tif gfnfrid
     *     signbturf of tiis gfnfrid dfdlbrbtion dofs not donform to
     *     tif formbt spfdififd in
     *     <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd")
    publid TypfVbribblf<Clbss<T>>[] gftTypfPbrbmftfrs() {
        ClbssRfpository info = gftGfnfridInfo();
        if (info != null)
            rfturn (TypfVbribblf<Clbss<T>>[])info.gftTypfPbrbmftfrs();
        flsf
            rfturn (TypfVbribblf<Clbss<T>>[])nfw TypfVbribblf<?>[0];
    }


    /**
     * Rfturns tif {@dodf Clbss} rfprfsfnting tif supfrdlbss of tif fntity
     * (dlbss, intfrfbdf, primitivf typf or void) rfprfsfntfd by tiis
     * {@dodf Clbss}.  If tiis {@dodf Clbss} rfprfsfnts fitifr tif
     * {@dodf Objfdt} dlbss, bn intfrfbdf, b primitivf typf, or void, tifn
     * null is rfturnfd.  If tiis objfdt rfprfsfnts bn brrby dlbss tifn tif
     * {@dodf Clbss} objfdt rfprfsfnting tif {@dodf Objfdt} dlbss is
     * rfturnfd.
     *
     * @rfturn tif supfrdlbss of tif dlbss rfprfsfntfd by tiis objfdt.
     */
    publid nbtivf Clbss<? supfr T> gftSupfrdlbss();


    /**
     * Rfturns tif {@dodf Typf} rfprfsfnting tif dirfdt supfrdlbss of
     * tif fntity (dlbss, intfrfbdf, primitivf typf or void) rfprfsfntfd by
     * tiis {@dodf Clbss}.
     *
     * <p>If tif supfrdlbss is b pbrbmftfrizfd typf, tif {@dodf Typf}
     * objfdt rfturnfd must bddurbtfly rfflfdt tif bdtubl typf
     * pbrbmftfrs usfd in tif sourdf dodf. Tif pbrbmftfrizfd typf
     * rfprfsfnting tif supfrdlbss is drfbtfd if it ibd not bffn
     * drfbtfd bfforf. Sff tif dfdlbrbtion of {@link
     * jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf PbrbmftfrizfdTypf} for tif
     * sfmbntids of tif drfbtion prodfss for pbrbmftfrizfd typfs.  If
     * tiis {@dodf Clbss} rfprfsfnts fitifr tif {@dodf Objfdt}
     * dlbss, bn intfrfbdf, b primitivf typf, or void, tifn null is
     * rfturnfd.  If tiis objfdt rfprfsfnts bn brrby dlbss tifn tif
     * {@dodf Clbss} objfdt rfprfsfnting tif {@dodf Objfdt} dlbss is
     * rfturnfd.
     *
     * @tirows jbvb.lbng.rfflfdt.GfnfridSignbturfFormbtError if tif gfnfrid
     *     dlbss signbturf dofs not donform to tif formbt spfdififd in
     *     <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>
     * @tirows TypfNotPrfsfntExdfption if tif gfnfrid supfrdlbss
     *     rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows jbvb.lbng.rfflfdt.MblformfdPbrbmftfrizfdTypfExdfption if tif
     *     gfnfrid supfrdlbss rfffrs to b pbrbmftfrizfd typf tibt dbnnot bf
     *     instbntibtfd  for bny rfbson
     * @rfturn tif supfrdlbss of tif dlbss rfprfsfntfd by tiis objfdt
     * @sindf 1.5
     */
    publid Typf gftGfnfridSupfrdlbss() {
        ClbssRfpository info = gftGfnfridInfo();
        if (info == null) {
            rfturn gftSupfrdlbss();
        }

        // Historidbl irrfgulbrity:
        // Gfnfrid signbturf mbrks intfrfbdfs witi supfrdlbss = Objfdt
        // but tiis API rfturns null for intfrfbdfs
        if (isIntfrfbdf()) {
            rfturn null;
        }

        rfturn info.gftSupfrdlbss();
    }

    /**
     * Gfts tif pbdkbgf for tiis dlbss.  Tif dlbss lobdfr of tiis dlbss is usfd
     * to find tif pbdkbgf.  If tif dlbss wbs lobdfd by tif bootstrbp dlbss
     * lobdfr tif sft of pbdkbgfs lobdfd from CLASSPATH is sfbrdifd to find tif
     * pbdkbgf of tif dlbss. Null is rfturnfd if no pbdkbgf objfdt wbs drfbtfd
     * by tif dlbss lobdfr of tiis dlbss.
     *
     * <p> Pbdkbgfs ibvf bttributfs for vfrsions bnd spfdifidbtions only if tif
     * informbtion wbs dffinfd in tif mbniffsts tibt bddompbny tif dlbssfs, bnd
     * if tif dlbss lobdfr drfbtfd tif pbdkbgf instbndf witi tif bttributfs
     * from tif mbniffst.
     *
     * @rfturn tif pbdkbgf of tif dlbss, or null if no pbdkbgf
     *         informbtion is bvbilbblf from tif brdiivf or dodfbbsf.
     */
    publid Pbdkbgf gftPbdkbgf() {
        rfturn Pbdkbgf.gftPbdkbgf(tiis);
    }


    /**
     * Dftfrminfs tif intfrfbdfs implfmfntfd by tif dlbss or intfrfbdf
     * rfprfsfntfd by tiis objfdt.
     *
     * <p> If tiis objfdt rfprfsfnts b dlbss, tif rfturn vbluf is bn brrby
     * dontbining objfdts rfprfsfnting bll intfrfbdfs implfmfntfd by tif
     * dlbss. Tif ordfr of tif intfrfbdf objfdts in tif brrby dorrfsponds to
     * tif ordfr of tif intfrfbdf nbmfs in tif {@dodf implfmfnts} dlbusf
     * of tif dfdlbrbtion of tif dlbss rfprfsfntfd by tiis objfdt. For
     * fxbmplf, givfn tif dfdlbrbtion:
     * <blodkquotf>
     * {@dodf dlbss Siimmfr implfmfnts FloorWbx, DfssfrtTopping { ... }}
     * </blodkquotf>
     * supposf tif vbluf of {@dodf s} is bn instbndf of
     * {@dodf Siimmfr}; tif vbluf of tif fxprfssion:
     * <blodkquotf>
     * {@dodf s.gftClbss().gftIntfrfbdfs()[0]}
     * </blodkquotf>
     * is tif {@dodf Clbss} objfdt tibt rfprfsfnts intfrfbdf
     * {@dodf FloorWbx}; bnd tif vbluf of:
     * <blodkquotf>
     * {@dodf s.gftClbss().gftIntfrfbdfs()[1]}
     * </blodkquotf>
     * is tif {@dodf Clbss} objfdt tibt rfprfsfnts intfrfbdf
     * {@dodf DfssfrtTopping}.
     *
     * <p> If tiis objfdt rfprfsfnts bn intfrfbdf, tif brrby dontbins objfdts
     * rfprfsfnting bll intfrfbdfs fxtfndfd by tif intfrfbdf. Tif ordfr of tif
     * intfrfbdf objfdts in tif brrby dorrfsponds to tif ordfr of tif intfrfbdf
     * nbmfs in tif {@dodf fxtfnds} dlbusf of tif dfdlbrbtion of tif
     * intfrfbdf rfprfsfntfd by tiis objfdt.
     *
     * <p> If tiis objfdt rfprfsfnts b dlbss or intfrfbdf tibt implfmfnts no
     * intfrfbdfs, tif mftiod rfturns bn brrby of lfngti 0.
     *
     * <p> If tiis objfdt rfprfsfnts b primitivf typf or void, tif mftiod
     * rfturns bn brrby of lfngti 0.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, tif
     * intfrfbdfs {@dodf Clonfbblf} bnd {@dodf jbvb.io.Sfriblizbblf} brf
     * rfturnfd in tibt ordfr.
     *
     * @rfturn bn brrby of intfrfbdfs implfmfntfd by tiis dlbss.
     */
    publid Clbss<?>[] gftIntfrfbdfs() {
        RfflfdtionDbtb<T> rd = rfflfdtionDbtb();
        if (rd == null) {
            // no dloning rfquirfd
            rfturn gftIntfrfbdfs0();
        } flsf {
            Clbss<?>[] intfrfbdfs = rd.intfrfbdfs;
            if (intfrfbdfs == null) {
                intfrfbdfs = gftIntfrfbdfs0();
                rd.intfrfbdfs = intfrfbdfs;
            }
            // dfffnsivfly dopy bfforf ibnding ovfr to usfr dodf
            rfturn intfrfbdfs.dlonf();
        }
    }

    privbtf nbtivf Clbss<?>[] gftIntfrfbdfs0();

    /**
     * Rfturns tif {@dodf Typf}s rfprfsfnting tif intfrfbdfs
     * dirfdtly implfmfntfd by tif dlbss or intfrfbdf rfprfsfntfd by
     * tiis objfdt.
     *
     * <p>If b supfrintfrfbdf is b pbrbmftfrizfd typf, tif
     * {@dodf Typf} objfdt rfturnfd for it must bddurbtfly rfflfdt
     * tif bdtubl typf pbrbmftfrs usfd in tif sourdf dodf. Tif
     * pbrbmftfrizfd typf rfprfsfnting fbdi supfrintfrfbdf is drfbtfd
     * if it ibd not bffn drfbtfd bfforf. Sff tif dfdlbrbtion of
     * {@link jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf PbrbmftfrizfdTypf}
     * for tif sfmbntids of tif drfbtion prodfss for pbrbmftfrizfd
     * typfs.
     *
     * <p> If tiis objfdt rfprfsfnts b dlbss, tif rfturn vbluf is bn
     * brrby dontbining objfdts rfprfsfnting bll intfrfbdfs
     * implfmfntfd by tif dlbss. Tif ordfr of tif intfrfbdf objfdts in
     * tif brrby dorrfsponds to tif ordfr of tif intfrfbdf nbmfs in
     * tif {@dodf implfmfnts} dlbusf of tif dfdlbrbtion of tif dlbss
     * rfprfsfntfd by tiis objfdt.  In tif dbsf of bn brrby dlbss, tif
     * intfrfbdfs {@dodf Clonfbblf} bnd {@dodf Sfriblizbblf} brf
     * rfturnfd in tibt ordfr.
     *
     * <p>If tiis objfdt rfprfsfnts bn intfrfbdf, tif brrby dontbins
     * objfdts rfprfsfnting bll intfrfbdfs dirfdtly fxtfndfd by tif
     * intfrfbdf.  Tif ordfr of tif intfrfbdf objfdts in tif brrby
     * dorrfsponds to tif ordfr of tif intfrfbdf nbmfs in tif
     * {@dodf fxtfnds} dlbusf of tif dfdlbrbtion of tif intfrfbdf
     * rfprfsfntfd by tiis objfdt.
     *
     * <p>If tiis objfdt rfprfsfnts b dlbss or intfrfbdf tibt
     * implfmfnts no intfrfbdfs, tif mftiod rfturns bn brrby of lfngti
     * 0.
     *
     * <p>If tiis objfdt rfprfsfnts b primitivf typf or void, tif
     * mftiod rfturns bn brrby of lfngti 0.
     *
     * @tirows jbvb.lbng.rfflfdt.GfnfridSignbturfFormbtError
     *     if tif gfnfrid dlbss signbturf dofs not donform to tif formbt
     *     spfdififd in
     *     <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>
     * @tirows TypfNotPrfsfntExdfption if bny of tif gfnfrid
     *     supfrintfrfbdfs rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows jbvb.lbng.rfflfdt.MblformfdPbrbmftfrizfdTypfExdfption
     *     if bny of tif gfnfrid supfrintfrfbdfs rfffr to b pbrbmftfrizfd
     *     typf tibt dbnnot bf instbntibtfd for bny rfbson
     * @rfturn bn brrby of intfrfbdfs implfmfntfd by tiis dlbss
     * @sindf 1.5
     */
    publid Typf[] gftGfnfridIntfrfbdfs() {
        ClbssRfpository info = gftGfnfridInfo();
        rfturn (info == null) ?  gftIntfrfbdfs() : info.gftSupfrIntfrfbdfs();
    }


    /**
     * Rfturns tif {@dodf Clbss} rfprfsfnting tif domponfnt typf of bn
     * brrby.  If tiis dlbss dofs not rfprfsfnt bn brrby dlbss tiis mftiod
     * rfturns null.
     *
     * @rfturn tif {@dodf Clbss} rfprfsfnting tif domponfnt typf of tiis
     * dlbss if tiis dlbss is bn brrby
     * @sff     jbvb.lbng.rfflfdt.Arrby
     * @sindf 1.1
     */
    publid Clbss<?> gftComponfntTypf() {
        // Only rfturn for brrby typfs. Storbgf mby bf rfusfd for Clbss for instbndf typfs.
        if (isArrby()) {
            rfturn domponfntTypf;
        } flsf {
            rfturn null;
        }
    }

    privbtf finbl Clbss<?> domponfntTypf;


    /**
     * Rfturns tif Jbvb lbngubgf modififrs for tiis dlbss or intfrfbdf, fndodfd
     * in bn intfgfr. Tif modififrs donsist of tif Jbvb Virtubl Mbdiinf's
     * donstbnts for {@dodf publid}, {@dodf protfdtfd},
     * {@dodf privbtf}, {@dodf finbl}, {@dodf stbtid},
     * {@dodf bbstrbdt} bnd {@dodf intfrfbdf}; tify siould bf dfdodfd
     * using tif mftiods of dlbss {@dodf Modififr}.
     *
     * <p> If tif undfrlying dlbss is bn brrby dlbss, tifn its
     * {@dodf publid}, {@dodf privbtf} bnd {@dodf protfdtfd}
     * modififrs brf tif sbmf bs tiosf of its domponfnt typf.  If tiis
     * {@dodf Clbss} rfprfsfnts b primitivf typf or void, its
     * {@dodf publid} modififr is blwbys {@dodf truf}, bnd its
     * {@dodf protfdtfd} bnd {@dodf privbtf} modififrs brf blwbys
     * {@dodf fblsf}. If tiis objfdt rfprfsfnts bn brrby dlbss, b
     * primitivf typf or void, tifn its {@dodf finbl} modififr is blwbys
     * {@dodf truf} bnd its intfrfbdf modififr is blwbys
     * {@dodf fblsf}. Tif vblufs of its otifr modififrs brf not dftfrminfd
     * by tiis spfdifidbtion.
     *
     * <p> Tif modififr fndodings brf dffinfd in <fm>Tif Jbvb Virtubl Mbdiinf
     * Spfdifidbtion</fm>, tbblf 4.1.
     *
     * @rfturn tif {@dodf int} rfprfsfnting tif modififrs for tiis dlbss
     * @sff     jbvb.lbng.rfflfdt.Modififr
     * @sindf 1.1
     */
    publid nbtivf int gftModififrs();


    /**
     * Gfts tif signfrs of tiis dlbss.
     *
     * @rfturn  tif signfrs of tiis dlbss, or null if tifrf brf no signfrs.  In
     *          pbrtidulbr, tiis mftiod rfturns null if tiis objfdt rfprfsfnts
     *          b primitivf typf or void.
     * @sindf   1.1
     */
    publid nbtivf Objfdt[] gftSignfrs();


    /**
     * Sft tif signfrs of tiis dlbss.
     */
    nbtivf void sftSignfrs(Objfdt[] signfrs);


    /**
     * If tiis {@dodf Clbss} objfdt rfprfsfnts b lodbl or bnonymous
     * dlbss witiin b mftiod, rfturns b {@link
     * jbvb.lbng.rfflfdt.Mftiod Mftiod} objfdt rfprfsfnting tif
     * immfdibtfly fndlosing mftiod of tif undfrlying dlbss. Rfturns
     * {@dodf null} otifrwisf.
     *
     * In pbrtidulbr, tiis mftiod rfturns {@dodf null} if tif undfrlying
     * dlbss is b lodbl or bnonymous dlbss immfdibtfly fndlosfd by b typf
     * dfdlbrbtion, instbndf initiblizfr or stbtid initiblizfr.
     *
     * @rfturn tif immfdibtfly fndlosing mftiod of tif undfrlying dlbss, if
     *     tibt dlbss is b lodbl or bnonymous dlbss; otifrwisf {@dodf null}.
     *
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *         following donditions is mft:
     *
     *         <ul>
     *
     *         <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *         dlbss lobdfr of tif fndlosing dlbss bnd invodbtion of
     *         {@link SfdurityMbnbgfr#difdkPfrmission
     *         s.difdkPfrmission} mftiod witi
     *         {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *         dfnifs bddfss to tif mftiods witiin tif fndlosing dlbss
     *
     *         <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif fndlosing dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tif fndlosing dlbss
     *
     *         </ul>
     * @sindf 1.5
     */
    @CbllfrSfnsitivf
    publid Mftiod gftEndlosingMftiod() tirows SfdurityExdfption {
        EndlosingMftiodInfo fndlosingInfo = gftEndlosingMftiodInfo();

        if (fndlosingInfo == null)
            rfturn null;
        flsf {
            if (!fndlosingInfo.isMftiod())
                rfturn null;

            MftiodRfpository typfInfo = MftiodRfpository.mbkf(fndlosingInfo.gftDfsdriptor(),
                                                              gftFbdtory());
            Clbss<?>   rfturnTypf       = toClbss(typfInfo.gftRfturnTypf());
            Typf []    pbrbmftfrTypfs   = typfInfo.gftPbrbmftfrTypfs();
            Clbss<?>[] pbrbmftfrClbssfs = nfw Clbss<?>[pbrbmftfrTypfs.lfngti];

            // Convfrt Typfs to Clbssfs; rfturnfd typfs *siould*
            // bf dlbss objfdts sindf tif mftiodDfsdriptor's usfd
            // don't ibvf gfnfrids informbtion
            for(int i = 0; i < pbrbmftfrClbssfs.lfngti; i++)
                pbrbmftfrClbssfs[i] = toClbss(pbrbmftfrTypfs[i]);

            // Pfrform bddfss difdk
            Clbss<?> fndlosingCbndidbtf = fndlosingInfo.gftEndlosingClbss();
            fndlosingCbndidbtf.difdkMfmbfrAddfss(Mfmbfr.DECLARED,
                                                 Rfflfdtion.gftCbllfrClbss(), truf);
            /*
             * Loop ovfr bll dfdlbrfd mftiods; mbtdi mftiod nbmf,
             * numbfr of bnd typf of pbrbmftfrs, *bnd* rfturn
             * typf.  Mbtdiing rfturn typf is blso nfdfssbry
             * bfdbusf of dovbribnt rfturns, ftd.
             */
            for(Mftiod m: fndlosingCbndidbtf.gftDfdlbrfdMftiods()) {
                if (m.gftNbmf().fqubls(fndlosingInfo.gftNbmf()) ) {
                    Clbss<?>[] dbndidbtfPbrbmClbssfs = m.gftPbrbmftfrTypfs();
                    if (dbndidbtfPbrbmClbssfs.lfngti == pbrbmftfrClbssfs.lfngti) {
                        boolfbn mbtdifs = truf;
                        for(int i = 0; i < dbndidbtfPbrbmClbssfs.lfngti; i++) {
                            if (!dbndidbtfPbrbmClbssfs[i].fqubls(pbrbmftfrClbssfs[i])) {
                                mbtdifs = fblsf;
                                brfbk;
                            }
                        }

                        if (mbtdifs) { // finblly, difdk rfturn typf
                            if (m.gftRfturnTypf().fqubls(rfturnTypf) )
                                rfturn m;
                        }
                    }
                }
            }

            tirow nfw IntfrnblError("Endlosing mftiod not found");
        }
    }

    privbtf nbtivf Objfdt[] gftEndlosingMftiod0();

    privbtf EndlosingMftiodInfo gftEndlosingMftiodInfo() {
        Objfdt[] fndlosingInfo = gftEndlosingMftiod0();
        if (fndlosingInfo == null)
            rfturn null;
        flsf {
            rfturn nfw EndlosingMftiodInfo(fndlosingInfo);
        }
    }

    privbtf finbl stbtid dlbss EndlosingMftiodInfo {
        privbtf Clbss<?> fndlosingClbss;
        privbtf String nbmf;
        privbtf String dfsdriptor;

        privbtf EndlosingMftiodInfo(Objfdt[] fndlosingInfo) {
            if (fndlosingInfo.lfngti != 3)
                tirow nfw IntfrnblError("Mblformfd fndlosing mftiod informbtion");
            try {
                // Tif brrby is fxpfdtfd to ibvf tirff flfmfnts:

                // tif immfdibtfly fndlosing dlbss
                fndlosingClbss = (Clbss<?>) fndlosingInfo[0];
                bssfrt(fndlosingClbss != null);

                // tif immfdibtfly fndlosing mftiod or donstrudtor's
                // nbmf (dbn bf null).
                nbmf            = (String)   fndlosingInfo[1];

                // tif immfdibtfly fndlosing mftiod or donstrudtor's
                // dfsdriptor (null iff nbmf is).
                dfsdriptor      = (String)   fndlosingInfo[2];
                bssfrt((nbmf != null && dfsdriptor != null) || nbmf == dfsdriptor);
            } dbtdi (ClbssCbstExdfption ddf) {
                tirow nfw IntfrnblError("Invblid typf in fndlosing mftiod informbtion", ddf);
            }
        }

        boolfbn isPbrtibl() {
            rfturn fndlosingClbss == null || nbmf == null || dfsdriptor == null;
        }

        boolfbn isConstrudtor() { rfturn !isPbrtibl() && "<init>".fqubls(nbmf); }

        boolfbn isMftiod() { rfturn !isPbrtibl() && !isConstrudtor() && !"<dlinit>".fqubls(nbmf); }

        Clbss<?> gftEndlosingClbss() { rfturn fndlosingClbss; }

        String gftNbmf() { rfturn nbmf; }

        String gftDfsdriptor() { rfturn dfsdriptor; }

    }

    privbtf stbtid Clbss<?> toClbss(Typf o) {
        if (o instbndfof GfnfridArrbyTypf)
            rfturn Arrby.nfwInstbndf(toClbss(((GfnfridArrbyTypf)o).gftGfnfridComponfntTypf()),
                                     0)
                .gftClbss();
        rfturn (Clbss<?>)o;
     }

    /**
     * If tiis {@dodf Clbss} objfdt rfprfsfnts b lodbl or bnonymous
     * dlbss witiin b donstrudtor, rfturns b {@link
     * jbvb.lbng.rfflfdt.Construdtor Construdtor} objfdt rfprfsfnting
     * tif immfdibtfly fndlosing donstrudtor of tif undfrlying
     * dlbss. Rfturns {@dodf null} otifrwisf.  In pbrtidulbr, tiis
     * mftiod rfturns {@dodf null} if tif undfrlying dlbss is b lodbl
     * or bnonymous dlbss immfdibtfly fndlosfd by b typf dfdlbrbtion,
     * instbndf initiblizfr or stbtid initiblizfr.
     *
     * @rfturn tif immfdibtfly fndlosing donstrudtor of tif undfrlying dlbss, if
     *     tibt dlbss is b lodbl or bnonymous dlbss; otifrwisf {@dodf null}.
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *         following donditions is mft:
     *
     *         <ul>
     *
     *         <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *         dlbss lobdfr of tif fndlosing dlbss bnd invodbtion of
     *         {@link SfdurityMbnbgfr#difdkPfrmission
     *         s.difdkPfrmission} mftiod witi
     *         {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *         dfnifs bddfss to tif donstrudtors witiin tif fndlosing dlbss
     *
     *         <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif fndlosing dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tif fndlosing dlbss
     *
     *         </ul>
     * @sindf 1.5
     */
    @CbllfrSfnsitivf
    publid Construdtor<?> gftEndlosingConstrudtor() tirows SfdurityExdfption {
        EndlosingMftiodInfo fndlosingInfo = gftEndlosingMftiodInfo();

        if (fndlosingInfo == null)
            rfturn null;
        flsf {
            if (!fndlosingInfo.isConstrudtor())
                rfturn null;

            ConstrudtorRfpository typfInfo = ConstrudtorRfpository.mbkf(fndlosingInfo.gftDfsdriptor(),
                                                                        gftFbdtory());
            Typf []    pbrbmftfrTypfs   = typfInfo.gftPbrbmftfrTypfs();
            Clbss<?>[] pbrbmftfrClbssfs = nfw Clbss<?>[pbrbmftfrTypfs.lfngti];

            // Convfrt Typfs to Clbssfs; rfturnfd typfs *siould*
            // bf dlbss objfdts sindf tif mftiodDfsdriptor's usfd
            // don't ibvf gfnfrids informbtion
            for(int i = 0; i < pbrbmftfrClbssfs.lfngti; i++)
                pbrbmftfrClbssfs[i] = toClbss(pbrbmftfrTypfs[i]);

            // Pfrform bddfss difdk
            Clbss<?> fndlosingCbndidbtf = fndlosingInfo.gftEndlosingClbss();
            fndlosingCbndidbtf.difdkMfmbfrAddfss(Mfmbfr.DECLARED,
                                                 Rfflfdtion.gftCbllfrClbss(), truf);
            /*
             * Loop ovfr bll dfdlbrfd donstrudtors; mbtdi numbfr
             * of bnd typf of pbrbmftfrs.
             */
            for(Construdtor<?> d: fndlosingCbndidbtf.gftDfdlbrfdConstrudtors()) {
                Clbss<?>[] dbndidbtfPbrbmClbssfs = d.gftPbrbmftfrTypfs();
                if (dbndidbtfPbrbmClbssfs.lfngti == pbrbmftfrClbssfs.lfngti) {
                    boolfbn mbtdifs = truf;
                    for(int i = 0; i < dbndidbtfPbrbmClbssfs.lfngti; i++) {
                        if (!dbndidbtfPbrbmClbssfs[i].fqubls(pbrbmftfrClbssfs[i])) {
                            mbtdifs = fblsf;
                            brfbk;
                        }
                    }

                    if (mbtdifs)
                        rfturn d;
                }
            }

            tirow nfw IntfrnblError("Endlosing donstrudtor not found");
        }
    }


    /**
     * If tif dlbss or intfrfbdf rfprfsfntfd by tiis {@dodf Clbss} objfdt
     * is b mfmbfr of bnotifr dlbss, rfturns tif {@dodf Clbss} objfdt
     * rfprfsfnting tif dlbss in wiidi it wbs dfdlbrfd.  Tiis mftiod rfturns
     * null if tiis dlbss or intfrfbdf is not b mfmbfr of bny otifr dlbss.  If
     * tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby dlbss, b primitivf
     * typf, or void,tifn tiis mftiod rfturns null.
     *
     * @rfturn tif dfdlbring dlbss for tiis dlbss
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd tif dbllfr's
     *         dlbss lobdfr is not tif sbmf bs or bn bndfstor of tif dlbss
     *         lobdfr for tif dfdlbring dlbss bnd invodbtion of {@link
     *         SfdurityMbnbgfr#difdkPbdkbgfAddfss s.difdkPbdkbgfAddfss()}
     *         dfnifs bddfss to tif pbdkbgf of tif dfdlbring dlbss
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Clbss<?> gftDfdlbringClbss() tirows SfdurityExdfption {
        finbl Clbss<?> dbndidbtf = gftDfdlbringClbss0();

        if (dbndidbtf != null)
            dbndidbtf.difdkPbdkbgfAddfss(
                    ClbssLobdfr.gftClbssLobdfr(Rfflfdtion.gftCbllfrClbss()), truf);
        rfturn dbndidbtf;
    }

    privbtf nbtivf Clbss<?> gftDfdlbringClbss0();


    /**
     * Rfturns tif immfdibtfly fndlosing dlbss of tif undfrlying
     * dlbss.  If tif undfrlying dlbss is b top lfvfl dlbss tiis
     * mftiod rfturns {@dodf null}.
     * @rfturn tif immfdibtfly fndlosing dlbss of tif undfrlying dlbss
     * @fxdfption  SfdurityExdfption
     *             If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd tif dbllfr's
     *             dlbss lobdfr is not tif sbmf bs or bn bndfstor of tif dlbss
     *             lobdfr for tif fndlosing dlbss bnd invodbtion of {@link
     *             SfdurityMbnbgfr#difdkPbdkbgfAddfss s.difdkPbdkbgfAddfss()}
     *             dfnifs bddfss to tif pbdkbgf of tif fndlosing dlbss
     * @sindf 1.5
     */
    @CbllfrSfnsitivf
    publid Clbss<?> gftEndlosingClbss() tirows SfdurityExdfption {
        // Tifrf brf fivf kinds of dlbssfs (or intfrfbdfs):
        // b) Top lfvfl dlbssfs
        // b) Nfstfd dlbssfs (stbtid mfmbfr dlbssfs)
        // d) Innfr dlbssfs (non-stbtid mfmbfr dlbssfs)
        // d) Lodbl dlbssfs (nbmfd dlbssfs dfdlbrfd witiin b mftiod)
        // f) Anonymous dlbssfs


        // JVM Spfd 4.8.6: A dlbss must ibvf bn EndlosingMftiod
        // bttributf if bnd only if it is b lodbl dlbss or bn
        // bnonymous dlbss.
        EndlosingMftiodInfo fndlosingInfo = gftEndlosingMftiodInfo();
        Clbss<?> fndlosingCbndidbtf;

        if (fndlosingInfo == null) {
            // Tiis is b top lfvfl or b nfstfd dlbss or bn innfr dlbss (b, b, or d)
            fndlosingCbndidbtf = gftDfdlbringClbss();
        } flsf {
            Clbss<?> fndlosingClbss = fndlosingInfo.gftEndlosingClbss();
            // Tiis is b lodbl dlbss or bn bnonymous dlbss (d or f)
            if (fndlosingClbss == tiis || fndlosingClbss == null)
                tirow nfw IntfrnblError("Mblformfd fndlosing mftiod informbtion");
            flsf
                fndlosingCbndidbtf = fndlosingClbss;
        }

        if (fndlosingCbndidbtf != null)
            fndlosingCbndidbtf.difdkPbdkbgfAddfss(
                    ClbssLobdfr.gftClbssLobdfr(Rfflfdtion.gftCbllfrClbss()), truf);
        rfturn fndlosingCbndidbtf;
    }

    /**
     * Rfturns tif simplf nbmf of tif undfrlying dlbss bs givfn in tif
     * sourdf dodf. Rfturns bn fmpty string if tif undfrlying dlbss is
     * bnonymous.
     *
     * <p>Tif simplf nbmf of bn brrby is tif simplf nbmf of tif
     * domponfnt typf witi "[]" bppfndfd.  In pbrtidulbr tif simplf
     * nbmf of bn brrby wiosf domponfnt typf is bnonymous is "[]".
     *
     * @rfturn tif simplf nbmf of tif undfrlying dlbss
     * @sindf 1.5
     */
    publid String gftSimplfNbmf() {
        if (isArrby())
            rfturn gftComponfntTypf().gftSimplfNbmf()+"[]";

        String simplfNbmf = gftSimplfBinbryNbmf();
        if (simplfNbmf == null) { // top lfvfl dlbss
            simplfNbmf = gftNbmf();
            rfturn simplfNbmf.substring(simplfNbmf.lbstIndfxOf('.')+1); // strip tif pbdkbgf nbmf
        }
        // Addording to JLS3 "Binbry Compbtibility" (13.1) tif binbry
        // nbmf of non-pbdkbgf dlbssfs (not top lfvfl) is tif binbry
        // nbmf of tif immfdibtfly fndlosing dlbss followfd by b '$' followfd by:
        // (for nfstfd bnd innfr dlbssfs): tif simplf nbmf.
        // (for lodbl dlbssfs): 1 or morf digits followfd by tif simplf nbmf.
        // (for bnonymous dlbssfs): 1 or morf digits.

        // Sindf gftSimplfBinbryNbmf() will strip tif binbry nbmf of
        // tif immfdibtly fndlosing dlbss, wf brf now looking bt b
        // string tibt mbtdifs tif rfgulbr fxprfssion "\$[0-9]*"
        // followfd by b simplf nbmf (donsidfring tif simplf of bn
        // bnonymous dlbss to bf tif fmpty string).

        // Rfmovf lfbding "\$[0-9]*" from tif nbmf
        int lfngti = simplfNbmf.lfngti();
        if (lfngti < 1 || simplfNbmf.dibrAt(0) != '$')
            tirow nfw IntfrnblError("Mblformfd dlbss nbmf");
        int indfx = 1;
        wiilf (indfx < lfngti && isAsdiiDigit(simplfNbmf.dibrAt(indfx)))
            indfx++;
        // Evfntublly, tiis is tif fmpty string iff tiis is bn bnonymous dlbss
        rfturn simplfNbmf.substring(indfx);
    }

    /**
     * Rfturn bn informbtivf string for tif nbmf of tiis typf.
     *
     * @rfturn bn informbtivf string for tif nbmf of tiis typf
     * @sindf 1.8
     */
    publid String gftTypfNbmf() {
        if (isArrby()) {
            try {
                Clbss<?> dl = tiis;
                int dimfnsions = 0;
                wiilf (dl.isArrby()) {
                    dimfnsions++;
                    dl = dl.gftComponfntTypf();
                }
                StringBuildfr sb = nfw StringBuildfr();
                sb.bppfnd(dl.gftNbmf());
                for (int i = 0; i < dimfnsions; i++) {
                    sb.bppfnd("[]");
                }
                rfturn sb.toString();
            } dbtdi (Tirowbblf f) { /*FALLTHRU*/ }
        }
        rfturn gftNbmf();
    }

    /**
     * Cibrbdtfr.isDigit bnswfrs {@dodf truf} to somf non-bsdii
     * digits.  Tiis onf dofs not.
     */
    privbtf stbtid boolfbn isAsdiiDigit(dibr d) {
        rfturn '0' <= d && d <= '9';
    }

    /**
     * Rfturns tif dbnonidbl nbmf of tif undfrlying dlbss bs
     * dffinfd by tif Jbvb Lbngubgf Spfdifidbtion.  Rfturns null if
     * tif undfrlying dlbss dofs not ibvf b dbnonidbl nbmf (i.f., if
     * it is b lodbl or bnonymous dlbss or bn brrby wiosf domponfnt
     * typf dofs not ibvf b dbnonidbl nbmf).
     * @rfturn tif dbnonidbl nbmf of tif undfrlying dlbss if it fxists, bnd
     * {@dodf null} otifrwisf.
     * @sindf 1.5
     */
    publid String gftCbnonidblNbmf() {
        if (isArrby()) {
            String dbnonidblNbmf = gftComponfntTypf().gftCbnonidblNbmf();
            if (dbnonidblNbmf != null)
                rfturn dbnonidblNbmf + "[]";
            flsf
                rfturn null;
        }
        if (isLodblOrAnonymousClbss())
            rfturn null;
        Clbss<?> fndlosingClbss = gftEndlosingClbss();
        if (fndlosingClbss == null) { // top lfvfl dlbss
            rfturn gftNbmf();
        } flsf {
            String fndlosingNbmf = fndlosingClbss.gftCbnonidblNbmf();
            if (fndlosingNbmf == null)
                rfturn null;
            rfturn fndlosingNbmf + "." + gftSimplfNbmf();
        }
    }

    /**
     * Rfturns {@dodf truf} if bnd only if tif undfrlying dlbss
     * is bn bnonymous dlbss.
     *
     * @rfturn {@dodf truf} if bnd only if tiis dlbss is bn bnonymous dlbss.
     * @sindf 1.5
     */
    publid boolfbn isAnonymousClbss() {
        rfturn "".fqubls(gftSimplfNbmf());
    }

    /**
     * Rfturns {@dodf truf} if bnd only if tif undfrlying dlbss
     * is b lodbl dlbss.
     *
     * @rfturn {@dodf truf} if bnd only if tiis dlbss is b lodbl dlbss.
     * @sindf 1.5
     */
    publid boolfbn isLodblClbss() {
        rfturn isLodblOrAnonymousClbss() && !isAnonymousClbss();
    }

    /**
     * Rfturns {@dodf truf} if bnd only if tif undfrlying dlbss
     * is b mfmbfr dlbss.
     *
     * @rfturn {@dodf truf} if bnd only if tiis dlbss is b mfmbfr dlbss.
     * @sindf 1.5
     */
    publid boolfbn isMfmbfrClbss() {
        rfturn gftSimplfBinbryNbmf() != null && !isLodblOrAnonymousClbss();
    }

    /**
     * Rfturns tif "simplf binbry nbmf" of tif undfrlying dlbss, i.f.,
     * tif binbry nbmf witiout tif lfbding fndlosing dlbss nbmf.
     * Rfturns {@dodf null} if tif undfrlying dlbss is b top lfvfl
     * dlbss.
     */
    privbtf String gftSimplfBinbryNbmf() {
        Clbss<?> fndlosingClbss = gftEndlosingClbss();
        if (fndlosingClbss == null) // top lfvfl dlbss
            rfturn null;
        // Otifrwisf, strip tif fndlosing dlbss' nbmf
        try {
            rfturn gftNbmf().substring(fndlosingClbss.gftNbmf().lfngti());
        } dbtdi (IndfxOutOfBoundsExdfption fx) {
            tirow nfw IntfrnblError("Mblformfd dlbss nbmf", fx);
        }
    }

    /**
     * Rfturns {@dodf truf} if tiis is b lodbl dlbss or bn bnonymous
     * dlbss.  Rfturns {@dodf fblsf} otifrwisf.
     */
    privbtf boolfbn isLodblOrAnonymousClbss() {
        // JVM Spfd 4.8.6: A dlbss must ibvf bn EndlosingMftiod
        // bttributf if bnd only if it is b lodbl dlbss or bn
        // bnonymous dlbss.
        rfturn gftEndlosingMftiodInfo() != null;
    }

    /**
     * Rfturns bn brrby dontbining {@dodf Clbss} objfdts rfprfsfnting bll
     * tif publid dlbssfs bnd intfrfbdfs tibt brf mfmbfrs of tif dlbss
     * rfprfsfntfd by tiis {@dodf Clbss} objfdt.  Tiis indludfs publid
     * dlbss bnd intfrfbdf mfmbfrs inifritfd from supfrdlbssfs bnd publid dlbss
     * bnd intfrfbdf mfmbfrs dfdlbrfd by tif dlbss.  Tiis mftiod rfturns bn
     * brrby of lfngti 0 if tiis {@dodf Clbss} objfdt ibs no publid mfmbfr
     * dlbssfs or intfrfbdfs.  Tiis mftiod blso rfturns bn brrby of lfngti 0 if
     * tiis {@dodf Clbss} objfdt rfprfsfnts b primitivf typf, bn brrby
     * dlbss, or void.
     *
     * @rfturn tif brrby of {@dodf Clbss} objfdts rfprfsfnting tif publid
     *         mfmbfrs of tiis dlbss
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *         tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss.
     *
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Clbss<?>[] gftClbssfs() {
        difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), fblsf);

        // Privilfgfd so tiis implfmfntbtion dbn look bt DECLARED dlbssfs,
        // somftiing tif dbllfr migit not ibvf privilfgf to do.  Tif dodf ifrf
        // is bllowfd to look bt DECLARED dlbssfs bfdbusf (1) it dofs not ibnd
        // out bnytiing otifr tibn publid mfmbfrs bnd (2) publid mfmbfr bddfss
        // ibs blrfbdy bffn ok'd by tif SfdurityMbnbgfr.

        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Clbss<?>[]>() {
                publid Clbss<?>[] run() {
                    List<Clbss<?>> list = nfw ArrbyList<>();
                    Clbss<?> durrfntClbss = Clbss.tiis;
                    wiilf (durrfntClbss != null) {
                        for (Clbss<?> m : durrfntClbss.gftDfdlbrfdClbssfs()) {
                            if (Modififr.isPublid(m.gftModififrs())) {
                                list.bdd(m);
                            }
                        }
                        durrfntClbss = durrfntClbss.gftSupfrdlbss();
                    }
                    rfturn list.toArrby(nfw Clbss<?>[0]);
                }
            });
    }


    /**
     * Rfturns bn brrby dontbining {@dodf Fifld} objfdts rfflfdting bll
     * tif bddfssiblf publid fiflds of tif dlbss or intfrfbdf rfprfsfntfd by
     * tiis {@dodf Clbss} objfdt.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b dlbss or intfrfbdf witi no
     * no bddfssiblf publid fiflds, tifn tiis mftiod rfturns bn brrby of lfngti
     * 0.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b dlbss, tifn tiis mftiod
     * rfturns tif publid fiflds of tif dlbss bnd of bll its supfrdlbssfs.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn intfrfbdf, tifn tiis
     * mftiod rfturns tif fiflds of tif intfrfbdf bnd of bll its
     * supfrintfrfbdfs.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, b primitivf
     * typf, or void, tifn tiis mftiod rfturns bn brrby of lfngti 0.
     *
     * <p> Tif flfmfnts in tif rfturnfd brrby brf not sortfd bnd brf not in bny
     * pbrtidulbr ordfr.
     *
     * @rfturn tif brrby of {@dodf Fifld} objfdts rfprfsfnting tif
     *         publid fiflds
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *         tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss.
     *
     * @sindf 1.1
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.3 Fifld Dfdlbrbtions
     */
    @CbllfrSfnsitivf
    publid Fifld[] gftFiflds() tirows SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn dopyFiflds(privbtfGftPublidFiflds(null));
    }


    /**
     * Rfturns bn brrby dontbining {@dodf Mftiod} objfdts rfflfdting bll tif
     * publid mftiods of tif dlbss or intfrfbdf rfprfsfntfd by tiis {@dodf
     * Clbss} objfdt, indluding tiosf dfdlbrfd by tif dlbss or intfrfbdf bnd
     * tiosf inifritfd from supfrdlbssfs bnd supfrintfrfbdfs.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b typf tibt ibs multiplf
     * publid mftiods witi tif sbmf nbmf bnd pbrbmftfr typfs, but difffrfnt
     * rfturn typfs, tifn tif rfturnfd brrby ibs b {@dodf Mftiod} objfdt for
     * fbdi sudi mftiod.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b typf witi b dlbss
     * initiblizbtion mftiod {@dodf <dlinit>}, tifn tif rfturnfd brrby dofs
     * <fm>not</fm> ibvf b dorrfsponding {@dodf Mftiod} objfdt.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, tifn tif
     * rfturnfd brrby ibs b {@dodf Mftiod} objfdt for fbdi of tif publid
     * mftiods inifritfd by tif brrby typf from {@dodf Objfdt}. It dofs not
     * dontbin b {@dodf Mftiod} objfdt for {@dodf dlonf()}.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn intfrfbdf tifn tif
     * rfturnfd brrby dofs not dontbin bny impliditly dfdlbrfd mftiods from
     * {@dodf Objfdt}. Tifrfforf, if no mftiods brf fxpliditly dfdlbrfd in
     * tiis intfrfbdf or bny of its supfrintfrfbdfs tifn tif rfturnfd brrby
     * ibs lfngti 0. (Notf tibt b {@dodf Clbss} objfdt wiidi rfprfsfnts b dlbss
     * blwbys ibs publid mftiods, inifritfd from {@dodf Objfdt}.)
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b primitivf typf or void,
     * tifn tif rfturnfd brrby ibs lfngti 0.
     *
     * <p> Stbtid mftiods dfdlbrfd in supfrintfrfbdfs of tif dlbss or intfrfbdf
     * rfprfsfntfd by tiis {@dodf Clbss} objfdt brf not donsidfrfd mfmbfrs of
     * tif dlbss or intfrfbdf.
     *
     * <p> Tif flfmfnts in tif rfturnfd brrby brf not sortfd bnd brf not in bny
     * pbrtidulbr ordfr.
     *
     * @rfturn tif brrby of {@dodf Mftiod} objfdts rfprfsfnting tif
     *         publid mftiods of tiis dlbss
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *         tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss.
     *
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.4 Mftiod Dfdlbrbtions
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Mftiod[] gftMftiods() tirows SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn dopyMftiods(privbtfGftPublidMftiods());
    }


    /**
     * Rfturns bn brrby dontbining {@dodf Construdtor} objfdts rfflfdting
     * bll tif publid donstrudtors of tif dlbss rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt.  An brrby of lfngti 0 is rfturnfd if tif
     * dlbss ibs no publid donstrudtors, or if tif dlbss is bn brrby dlbss, or
     * if tif dlbss rfflfdts b primitivf typf or void.
     *
     * Notf tibt wiilf tiis mftiod rfturns bn brrby of {@dodf
     * Construdtor<T>} objfdts (tibt is bn brrby of donstrudtors from
     * tiis dlbss), tif rfturn typf of tiis mftiod is {@dodf
     * Construdtor<?>[]} bnd <fm>not</fm> {@dodf Construdtor<T>[]} bs
     * migit bf fxpfdtfd.  Tiis lfss informbtivf rfturn typf is
     * nfdfssbry sindf bftfr bfing rfturnfd from tiis mftiod, tif
     * brrby dould bf modififd to iold {@dodf Construdtor} objfdts for
     * difffrfnt dlbssfs, wiidi would violbtf tif typf gubrbntffs of
     * {@dodf Construdtor<T>[]}.
     *
     * @rfturn tif brrby of {@dodf Construdtor} objfdts rfprfsfnting tif
     *         publid donstrudtors of tiis dlbss
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *         tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss.
     *
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Construdtor<?>[] gftConstrudtors() tirows SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn dopyConstrudtors(privbtfGftDfdlbrfdConstrudtors(truf));
    }


    /**
     * Rfturns b {@dodf Fifld} objfdt tibt rfflfdts tif spfdififd publid mfmbfr
     * fifld of tif dlbss or intfrfbdf rfprfsfntfd by tiis {@dodf Clbss}
     * objfdt. Tif {@dodf nbmf} pbrbmftfr is b {@dodf String} spfdifying tif
     * simplf nbmf of tif dfsirfd fifld.
     *
     * <p> Tif fifld to bf rfflfdtfd is dftfrminfd by tif blgoritim tibt
     * follows.  Lft C bf tif dlbss or intfrfbdf rfprfsfntfd by tiis objfdt:
     *
     * <OL>
     * <LI> If C dfdlbrfs b publid fifld witi tif nbmf spfdififd, tibt is tif
     *      fifld to bf rfflfdtfd.</LI>
     * <LI> If no fifld wbs found in stfp 1 bbovf, tiis blgoritim is bpplifd
     *      rfdursivfly to fbdi dirfdt supfrintfrfbdf of C. Tif dirfdt
     *      supfrintfrfbdfs brf sfbrdifd in tif ordfr tify wfrf dfdlbrfd.</LI>
     * <LI> If no fifld wbs found in stfps 1 bnd 2 bbovf, bnd C ibs b
     *      supfrdlbss S, tifn tiis blgoritim is invokfd rfdursivfly upon S.
     *      If C ibs no supfrdlbss, tifn b {@dodf NoSudiFifldExdfption}
     *      is tirown.</LI>
     * </OL>
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, tifn tiis
     * mftiod dofs not find tif {@dodf lfngti} fifld of tif brrby typf.
     *
     * @pbrbm nbmf tif fifld nbmf
     * @rfturn tif {@dodf Fifld} objfdt of tiis dlbss spfdififd by
     *         {@dodf nbmf}
     * @tirows NoSudiFifldExdfption if b fifld witi tif spfdififd nbmf is
     *         not found.
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *         tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss.
     *
     * @sindf 1.1
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.3 Fifld Dfdlbrbtions
     */
    @CbllfrSfnsitivf
    publid Fifld gftFifld(String nbmf)
        tirows NoSudiFifldExdfption, SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), truf);
        Fifld fifld = gftFifld0(nbmf);
        if (fifld == null) {
            tirow nfw NoSudiFifldExdfption(nbmf);
        }
        rfturn fifld;
    }


    /**
     * Rfturns b {@dodf Mftiod} objfdt tibt rfflfdts tif spfdififd publid
     * mfmbfr mftiod of tif dlbss or intfrfbdf rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt. Tif {@dodf nbmf} pbrbmftfr is b
     * {@dodf String} spfdifying tif simplf nbmf of tif dfsirfd mftiod. Tif
     * {@dodf pbrbmftfrTypfs} pbrbmftfr is bn brrby of {@dodf Clbss}
     * objfdts tibt idfntify tif mftiod's formbl pbrbmftfr typfs, in dfdlbrfd
     * ordfr. If {@dodf pbrbmftfrTypfs} is {@dodf null}, it is
     * trfbtfd bs if it wfrf bn fmpty brrby.
     *
     * <p> If tif {@dodf nbmf} is "{@dodf <init>}" or "{@dodf <dlinit>}" b
     * {@dodf NoSudiMftiodExdfption} is rbisfd. Otifrwisf, tif mftiod to
     * bf rfflfdtfd is dftfrminfd by tif blgoritim tibt follows.  Lft C bf tif
     * dlbss or intfrfbdf rfprfsfntfd by tiis objfdt:
     * <OL>
     * <LI> C is sfbrdifd for b <I>mbtdiing mftiod</I>, bs dffinfd bflow. If b
     *      mbtdiing mftiod is found, it is rfflfdtfd.</LI>
     * <LI> If no mbtdiing mftiod is found by stfp 1 tifn:
     *   <OL TYPE="b">
     *   <LI> If C is b dlbss otifr tibn {@dodf Objfdt}, tifn tiis blgoritim is
     *        invokfd rfdursivfly on tif supfrdlbss of C.</LI>
     *   <LI> If C is tif dlbss {@dodf Objfdt}, or if C is bn intfrfbdf, tifn
     *        tif supfrintfrfbdfs of C (if bny) brf sfbrdifd for b mbtdiing
     *        mftiod. If bny sudi mftiod is found, it is rfflfdtfd.</LI>
     *   </OL></LI>
     * </OL>
     *
     * <p> To find b mbtdiing mftiod in b dlbss or intfrfbdf C:&nbsp; If C
     * dfdlbrfs fxbdtly onf publid mftiod witi tif spfdififd nbmf bnd fxbdtly
     * tif sbmf formbl pbrbmftfr typfs, tibt is tif mftiod rfflfdtfd. If morf
     * tibn onf sudi mftiod is found in C, bnd onf of tifsf mftiods ibs b
     * rfturn typf tibt is morf spfdifid tibn bny of tif otifrs, tibt mftiod is
     * rfflfdtfd; otifrwisf onf of tif mftiods is diosfn brbitrbrily.
     *
     * <p>Notf tibt tifrf mby bf morf tibn onf mbtdiing mftiod in b
     * dlbss bfdbusf wiilf tif Jbvb lbngubgf forbids b dlbss to
     * dfdlbrf multiplf mftiods witi tif sbmf signbturf but difffrfnt
     * rfturn typfs, tif Jbvb virtubl mbdiinf dofs not.  Tiis
     * indrfbsfd flfxibility in tif virtubl mbdiinf dbn bf usfd to
     * implfmfnt vbrious lbngubgf ffbturfs.  For fxbmplf, dovbribnt
     * rfturns dbn bf implfmfntfd witi {@linkplbin
     * jbvb.lbng.rfflfdt.Mftiod#isBridgf bridgf mftiods}; tif bridgf
     * mftiod bnd tif mftiod bfing ovfrriddfn would ibvf tif sbmf
     * signbturf but difffrfnt rfturn typfs.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, tifn tiis
     * mftiod dofs not find tif {@dodf dlonf()} mftiod.
     *
     * <p> Stbtid mftiods dfdlbrfd in supfrintfrfbdfs of tif dlbss or intfrfbdf
     * rfprfsfntfd by tiis {@dodf Clbss} objfdt brf not donsidfrfd mfmbfrs of
     * tif dlbss or intfrfbdf.
     *
     * @pbrbm nbmf tif nbmf of tif mftiod
     * @pbrbm pbrbmftfrTypfs tif list of pbrbmftfrs
     * @rfturn tif {@dodf Mftiod} objfdt tibt mbtdifs tif spfdififd
     *         {@dodf nbmf} bnd {@dodf pbrbmftfrTypfs}
     * @tirows NoSudiMftiodExdfption if b mbtdiing mftiod is not found
     *         or if tif nbmf is "&lt;init&gt;"or "&lt;dlinit&gt;".
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *         tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss.
     *
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.4 Mftiod Dfdlbrbtions
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Mftiod gftMftiod(String nbmf, Clbss<?>... pbrbmftfrTypfs)
        tirows NoSudiMftiodExdfption, SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), truf);
        Mftiod mftiod = gftMftiod0(nbmf, pbrbmftfrTypfs, truf);
        if (mftiod == null) {
            tirow nfw NoSudiMftiodExdfption(gftNbmf() + "." + nbmf + brgumfntTypfsToString(pbrbmftfrTypfs));
        }
        rfturn mftiod;
    }


    /**
     * Rfturns b {@dodf Construdtor} objfdt tibt rfflfdts tif spfdififd
     * publid donstrudtor of tif dlbss rfprfsfntfd by tiis {@dodf Clbss}
     * objfdt. Tif {@dodf pbrbmftfrTypfs} pbrbmftfr is bn brrby of
     * {@dodf Clbss} objfdts tibt idfntify tif donstrudtor's formbl
     * pbrbmftfr typfs, in dfdlbrfd ordfr.
     *
     * If tiis {@dodf Clbss} objfdt rfprfsfnts bn innfr dlbss
     * dfdlbrfd in b non-stbtid dontfxt, tif formbl pbrbmftfr typfs
     * indludf tif fxplidit fndlosing instbndf bs tif first pbrbmftfr.
     *
     * <p> Tif donstrudtor to rfflfdt is tif publid donstrudtor of tif dlbss
     * rfprfsfntfd by tiis {@dodf Clbss} objfdt wiosf formbl pbrbmftfr
     * typfs mbtdi tiosf spfdififd by {@dodf pbrbmftfrTypfs}.
     *
     * @pbrbm pbrbmftfrTypfs tif pbrbmftfr brrby
     * @rfturn tif {@dodf Construdtor} objfdt of tif publid donstrudtor tibt
     *         mbtdifs tif spfdififd {@dodf pbrbmftfrTypfs}
     * @tirows NoSudiMftiodExdfption if b mbtdiing mftiod is not found.
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd
     *         tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss.
     *
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Construdtor<T> gftConstrudtor(Clbss<?>... pbrbmftfrTypfs)
        tirows NoSudiMftiodExdfption, SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.PUBLIC, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn gftConstrudtor0(pbrbmftfrTypfs, Mfmbfr.PUBLIC);
    }


    /**
     * Rfturns bn brrby of {@dodf Clbss} objfdts rfflfdting bll tif
     * dlbssfs bnd intfrfbdfs dfdlbrfd bs mfmbfrs of tif dlbss rfprfsfntfd by
     * tiis {@dodf Clbss} objfdt. Tiis indludfs publid, protfdtfd, dffbult
     * (pbdkbgf) bddfss, bnd privbtf dlbssfs bnd intfrfbdfs dfdlbrfd by tif
     * dlbss, but fxdludfs inifritfd dlbssfs bnd intfrfbdfs.  Tiis mftiod
     * rfturns bn brrby of lfngti 0 if tif dlbss dfdlbrfs no dlbssfs or
     * intfrfbdfs bs mfmbfrs, or if tiis {@dodf Clbss} objfdt rfprfsfnts b
     * primitivf typf, bn brrby dlbss, or void.
     *
     * @rfturn tif brrby of {@dodf Clbss} objfdts rfprfsfnting bll tif
     *         dfdlbrfd mfmbfrs of tiis dlbss
     * @tirows SfdurityExdfption
     *         If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *         following donditions is mft:
     *
     *         <ul>
     *
     *         <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *         dlbss lobdfr of tiis dlbss bnd invodbtion of
     *         {@link SfdurityMbnbgfr#difdkPfrmission
     *         s.difdkPfrmission} mftiod witi
     *         {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *         dfnifs bddfss to tif dfdlbrfd dlbssfs witiin tiis dlbss
     *
     *         <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *         bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *         invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *         s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *         of tiis dlbss
     *
     *         </ul>
     *
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Clbss<?>[] gftDfdlbrfdClbssfs() tirows SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.DECLARED, Rfflfdtion.gftCbllfrClbss(), fblsf);
        rfturn gftDfdlbrfdClbssfs0();
    }


    /**
     * Rfturns bn brrby of {@dodf Fifld} objfdts rfflfdting bll tif fiflds
     * dfdlbrfd by tif dlbss or intfrfbdf rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt. Tiis indludfs publid, protfdtfd, dffbult
     * (pbdkbgf) bddfss, bnd privbtf fiflds, but fxdludfs inifritfd fiflds.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b dlbss or intfrfbdf witi no
     * dfdlbrfd fiflds, tifn tiis mftiod rfturns bn brrby of lfngti 0.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, b primitivf
     * typf, or void, tifn tiis mftiod rfturns bn brrby of lfngti 0.
     *
     * <p> Tif flfmfnts in tif rfturnfd brrby brf not sortfd bnd brf not in bny
     * pbrtidulbr ordfr.
     *
     * @rfturn  tif brrby of {@dodf Fifld} objfdts rfprfsfnting bll tif
     *          dfdlbrfd fiflds of tiis dlbss
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *          following donditions is mft:
     *
     *          <ul>
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *          dlbss lobdfr of tiis dlbss bnd invodbtion of
     *          {@link SfdurityMbnbgfr#difdkPfrmission
     *          s.difdkPfrmission} mftiod witi
     *          {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *          dfnifs bddfss to tif dfdlbrfd fiflds witiin tiis dlbss
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *          invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *          of tiis dlbss
     *
     *          </ul>
     *
     * @sindf 1.1
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.3 Fifld Dfdlbrbtions
     */
    @CbllfrSfnsitivf
    publid Fifld[] gftDfdlbrfdFiflds() tirows SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.DECLARED, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn dopyFiflds(privbtfGftDfdlbrfdFiflds(fblsf));
    }


    /**
     *
     * Rfturns bn brrby dontbining {@dodf Mftiod} objfdts rfflfdting bll tif
     * dfdlbrfd mftiods of tif dlbss or intfrfbdf rfprfsfntfd by tiis {@dodf
     * Clbss} objfdt, indluding publid, protfdtfd, dffbult (pbdkbgf)
     * bddfss, bnd privbtf mftiods, but fxdluding inifritfd mftiods.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b typf tibt ibs multiplf
     * dfdlbrfd mftiods witi tif sbmf nbmf bnd pbrbmftfr typfs, but difffrfnt
     * rfturn typfs, tifn tif rfturnfd brrby ibs b {@dodf Mftiod} objfdt for
     * fbdi sudi mftiod.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b typf tibt ibs b dlbss
     * initiblizbtion mftiod {@dodf <dlinit>}, tifn tif rfturnfd brrby dofs
     * <fm>not</fm> ibvf b dorrfsponding {@dodf Mftiod} objfdt.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b dlbss or intfrfbdf witi no
     * dfdlbrfd mftiods, tifn tif rfturnfd brrby ibs lfngti 0.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, b primitivf
     * typf, or void, tifn tif rfturnfd brrby ibs lfngti 0.
     *
     * <p> Tif flfmfnts in tif rfturnfd brrby brf not sortfd bnd brf not in bny
     * pbrtidulbr ordfr.
     *
     * @rfturn  tif brrby of {@dodf Mftiod} objfdts rfprfsfnting bll tif
     *          dfdlbrfd mftiods of tiis dlbss
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *          following donditions is mft:
     *
     *          <ul>
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *          dlbss lobdfr of tiis dlbss bnd invodbtion of
     *          {@link SfdurityMbnbgfr#difdkPfrmission
     *          s.difdkPfrmission} mftiod witi
     *          {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *          dfnifs bddfss to tif dfdlbrfd mftiods witiin tiis dlbss
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *          invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *          of tiis dlbss
     *
     *          </ul>
     *
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.4 Mftiod Dfdlbrbtions
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Mftiod[] gftDfdlbrfdMftiods() tirows SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.DECLARED, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn dopyMftiods(privbtfGftDfdlbrfdMftiods(fblsf));
    }


    /**
     * Rfturns bn brrby of {@dodf Construdtor} objfdts rfflfdting bll tif
     * donstrudtors dfdlbrfd by tif dlbss rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt. Tifsf brf publid, protfdtfd, dffbult
     * (pbdkbgf) bddfss, bnd privbtf donstrudtors.  Tif flfmfnts in tif brrby
     * rfturnfd brf not sortfd bnd brf not in bny pbrtidulbr ordfr.  If tif
     * dlbss ibs b dffbult donstrudtor, it is indludfd in tif rfturnfd brrby.
     * Tiis mftiod rfturns bn brrby of lfngti 0 if tiis {@dodf Clbss}
     * objfdt rfprfsfnts bn intfrfbdf, b primitivf typf, bn brrby dlbss, or
     * void.
     *
     * <p> Sff <fm>Tif Jbvb Lbngubgf Spfdifidbtion</fm>, sfdtion 8.2.
     *
     * @rfturn  tif brrby of {@dodf Construdtor} objfdts rfprfsfnting bll tif
     *          dfdlbrfd donstrudtors of tiis dlbss
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *          following donditions is mft:
     *
     *          <ul>
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *          dlbss lobdfr of tiis dlbss bnd invodbtion of
     *          {@link SfdurityMbnbgfr#difdkPfrmission
     *          s.difdkPfrmission} mftiod witi
     *          {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *          dfnifs bddfss to tif dfdlbrfd donstrudtors witiin tiis dlbss
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *          invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *          of tiis dlbss
     *
     *          </ul>
     *
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Construdtor<?>[] gftDfdlbrfdConstrudtors() tirows SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.DECLARED, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn dopyConstrudtors(privbtfGftDfdlbrfdConstrudtors(fblsf));
    }


    /**
     * Rfturns b {@dodf Fifld} objfdt tibt rfflfdts tif spfdififd dfdlbrfd
     * fifld of tif dlbss or intfrfbdf rfprfsfntfd by tiis {@dodf Clbss}
     * objfdt. Tif {@dodf nbmf} pbrbmftfr is b {@dodf String} tibt spfdififs
     * tif simplf nbmf of tif dfsirfd fifld.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, tifn tiis
     * mftiod dofs not find tif {@dodf lfngti} fifld of tif brrby typf.
     *
     * @pbrbm nbmf tif nbmf of tif fifld
     * @rfturn  tif {@dodf Fifld} objfdt for tif spfdififd fifld in tiis
     *          dlbss
     * @tirows  NoSudiFifldExdfption if b fifld witi tif spfdififd nbmf is
     *          not found.
     * @tirows  NullPointfrExdfption if {@dodf nbmf} is {@dodf null}
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *          following donditions is mft:
     *
     *          <ul>
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *          dlbss lobdfr of tiis dlbss bnd invodbtion of
     *          {@link SfdurityMbnbgfr#difdkPfrmission
     *          s.difdkPfrmission} mftiod witi
     *          {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *          dfnifs bddfss to tif dfdlbrfd fifld
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *          invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *          of tiis dlbss
     *
     *          </ul>
     *
     * @sindf 1.1
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.3 Fifld Dfdlbrbtions
     */
    @CbllfrSfnsitivf
    publid Fifld gftDfdlbrfdFifld(String nbmf)
        tirows NoSudiFifldExdfption, SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.DECLARED, Rfflfdtion.gftCbllfrClbss(), truf);
        Fifld fifld = sfbrdiFiflds(privbtfGftDfdlbrfdFiflds(fblsf), nbmf);
        if (fifld == null) {
            tirow nfw NoSudiFifldExdfption(nbmf);
        }
        rfturn fifld;
    }


    /**
     * Rfturns b {@dodf Mftiod} objfdt tibt rfflfdts tif spfdififd
     * dfdlbrfd mftiod of tif dlbss or intfrfbdf rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt. Tif {@dodf nbmf} pbrbmftfr is b
     * {@dodf String} tibt spfdififs tif simplf nbmf of tif dfsirfd
     * mftiod, bnd tif {@dodf pbrbmftfrTypfs} pbrbmftfr is bn brrby of
     * {@dodf Clbss} objfdts tibt idfntify tif mftiod's formbl pbrbmftfr
     * typfs, in dfdlbrfd ordfr.  If morf tibn onf mftiod witi tif sbmf
     * pbrbmftfr typfs is dfdlbrfd in b dlbss, bnd onf of tifsf mftiods ibs b
     * rfturn typf tibt is morf spfdifid tibn bny of tif otifrs, tibt mftiod is
     * rfturnfd; otifrwisf onf of tif mftiods is diosfn brbitrbrily.  If tif
     * nbmf is "&lt;init&gt;"or "&lt;dlinit&gt;" b {@dodf NoSudiMftiodExdfption}
     * is rbisfd.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn brrby typf, tifn tiis
     * mftiod dofs not find tif {@dodf dlonf()} mftiod.
     *
     * @pbrbm nbmf tif nbmf of tif mftiod
     * @pbrbm pbrbmftfrTypfs tif pbrbmftfr brrby
     * @rfturn  tif {@dodf Mftiod} objfdt for tif mftiod of tiis dlbss
     *          mbtdiing tif spfdififd nbmf bnd pbrbmftfrs
     * @tirows  NoSudiMftiodExdfption if b mbtdiing mftiod is not found.
     * @tirows  NullPointfrExdfption if {@dodf nbmf} is {@dodf null}
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *          following donditions is mft:
     *
     *          <ul>
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *          dlbss lobdfr of tiis dlbss bnd invodbtion of
     *          {@link SfdurityMbnbgfr#difdkPfrmission
     *          s.difdkPfrmission} mftiod witi
     *          {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *          dfnifs bddfss to tif dfdlbrfd mftiod
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *          invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *          of tiis dlbss
     *
     *          </ul>
     *
     * @jls 8.2 Clbss Mfmbfrs
     * @jls 8.4 Mftiod Dfdlbrbtions
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Mftiod gftDfdlbrfdMftiod(String nbmf, Clbss<?>... pbrbmftfrTypfs)
        tirows NoSudiMftiodExdfption, SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.DECLARED, Rfflfdtion.gftCbllfrClbss(), truf);
        Mftiod mftiod = sfbrdiMftiods(privbtfGftDfdlbrfdMftiods(fblsf), nbmf, pbrbmftfrTypfs);
        if (mftiod == null) {
            tirow nfw NoSudiMftiodExdfption(gftNbmf() + "." + nbmf + brgumfntTypfsToString(pbrbmftfrTypfs));
        }
        rfturn mftiod;
    }


    /**
     * Rfturns b {@dodf Construdtor} objfdt tibt rfflfdts tif spfdififd
     * donstrudtor of tif dlbss or intfrfbdf rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt.  Tif {@dodf pbrbmftfrTypfs} pbrbmftfr is
     * bn brrby of {@dodf Clbss} objfdts tibt idfntify tif donstrudtor's
     * formbl pbrbmftfr typfs, in dfdlbrfd ordfr.
     *
     * If tiis {@dodf Clbss} objfdt rfprfsfnts bn innfr dlbss
     * dfdlbrfd in b non-stbtid dontfxt, tif formbl pbrbmftfr typfs
     * indludf tif fxplidit fndlosing instbndf bs tif first pbrbmftfr.
     *
     * @pbrbm pbrbmftfrTypfs tif pbrbmftfr brrby
     * @rfturn  Tif {@dodf Construdtor} objfdt for tif donstrudtor witi tif
     *          spfdififd pbrbmftfr list
     * @tirows  NoSudiMftiodExdfption if b mbtdiing mftiod is not found.
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr, <i>s</i>, is prfsfnt bnd bny of tif
     *          following donditions is mft:
     *
     *          <ul>
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs tif
     *          dlbss lobdfr of tiis dlbss bnd invodbtion of
     *          {@link SfdurityMbnbgfr#difdkPfrmission
     *          s.difdkPfrmission} mftiod witi
     *          {@dodf RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")}
     *          dfnifs bddfss to tif dfdlbrfd donstrudtor
     *
     *          <li> tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif durrfnt dlbss bnd
     *          invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif pbdkbgf
     *          of tiis dlbss
     *
     *          </ul>
     *
     * @sindf 1.1
     */
    @CbllfrSfnsitivf
    publid Construdtor<T> gftDfdlbrfdConstrudtor(Clbss<?>... pbrbmftfrTypfs)
        tirows NoSudiMftiodExdfption, SfdurityExdfption {
        difdkMfmbfrAddfss(Mfmbfr.DECLARED, Rfflfdtion.gftCbllfrClbss(), truf);
        rfturn gftConstrudtor0(pbrbmftfrTypfs, Mfmbfr.DECLARED);
    }

    /**
     * Finds b rfsourdf witi b givfn nbmf.  Tif rulfs for sfbrdiing rfsourdfs
     * bssodibtfd witi b givfn dlbss brf implfmfntfd by tif dffining
     * {@linkplbin ClbssLobdfr dlbss lobdfr} of tif dlbss.  Tiis mftiod
     * dflfgbtfs to tiis objfdt's dlbss lobdfr.  If tiis objfdt wbs lobdfd by
     * tif bootstrbp dlbss lobdfr, tif mftiod dflfgbtfs to {@link
     * ClbssLobdfr#gftSystfmRfsourdfAsStrfbm}.
     *
     * <p> Bfforf dflfgbtion, bn bbsolutf rfsourdf nbmf is donstrudtfd from tif
     * givfn rfsourdf nbmf using tiis blgoritim:
     *
     * <ul>
     *
     * <li> If tif {@dodf nbmf} bfgins witi b {@dodf '/'}
     * (<tt>'&#92;u002f'</tt>), tifn tif bbsolutf nbmf of tif rfsourdf is tif
     * portion of tif {@dodf nbmf} following tif {@dodf '/'}.
     *
     * <li> Otifrwisf, tif bbsolutf nbmf is of tif following form:
     *
     * <blodkquotf>
     *   {@dodf modififd_pbdkbgf_nbmf/nbmf}
     * </blodkquotf>
     *
     * <p> Wifrf tif {@dodf modififd_pbdkbgf_nbmf} is tif pbdkbgf nbmf of tiis
     * objfdt witi {@dodf '/'} substitutfd for {@dodf '.'}
     * (<tt>'&#92;u002f'</tt>).
     *
     * </ul>
     *
     * @pbrbm  nbmf nbmf of tif dfsirfd rfsourdf
     * @rfturn      A {@link jbvb.io.InputStrfbm} objfdt or {@dodf null} if
     *              no rfsourdf witi tiis nbmf is found
     * @tirows  NullPointfrExdfption If {@dodf nbmf} is {@dodf null}
     * @sindf  1.1
     */
     publid InputStrfbm gftRfsourdfAsStrfbm(String nbmf) {
        nbmf = rfsolvfNbmf(nbmf);
        ClbssLobdfr dl = gftClbssLobdfr0();
        if (dl==null) {
            // A systfm dlbss.
            rfturn ClbssLobdfr.gftSystfmRfsourdfAsStrfbm(nbmf);
        }
        rfturn dl.gftRfsourdfAsStrfbm(nbmf);
    }

    /**
     * Finds b rfsourdf witi b givfn nbmf.  Tif rulfs for sfbrdiing rfsourdfs
     * bssodibtfd witi b givfn dlbss brf implfmfntfd by tif dffining
     * {@linkplbin ClbssLobdfr dlbss lobdfr} of tif dlbss.  Tiis mftiod
     * dflfgbtfs to tiis objfdt's dlbss lobdfr.  If tiis objfdt wbs lobdfd by
     * tif bootstrbp dlbss lobdfr, tif mftiod dflfgbtfs to {@link
     * ClbssLobdfr#gftSystfmRfsourdf}.
     *
     * <p> Bfforf dflfgbtion, bn bbsolutf rfsourdf nbmf is donstrudtfd from tif
     * givfn rfsourdf nbmf using tiis blgoritim:
     *
     * <ul>
     *
     * <li> If tif {@dodf nbmf} bfgins witi b {@dodf '/'}
     * (<tt>'&#92;u002f'</tt>), tifn tif bbsolutf nbmf of tif rfsourdf is tif
     * portion of tif {@dodf nbmf} following tif {@dodf '/'}.
     *
     * <li> Otifrwisf, tif bbsolutf nbmf is of tif following form:
     *
     * <blodkquotf>
     *   {@dodf modififd_pbdkbgf_nbmf/nbmf}
     * </blodkquotf>
     *
     * <p> Wifrf tif {@dodf modififd_pbdkbgf_nbmf} is tif pbdkbgf nbmf of tiis
     * objfdt witi {@dodf '/'} substitutfd for {@dodf '.'}
     * (<tt>'&#92;u002f'</tt>).
     *
     * </ul>
     *
     * @pbrbm  nbmf nbmf of tif dfsirfd rfsourdf
     * @rfturn      A  {@link jbvb.nft.URL} objfdt or {@dodf null} if no
     *              rfsourdf witi tiis nbmf is found
     * @sindf  1.1
     */
    publid jbvb.nft.URL gftRfsourdf(String nbmf) {
        nbmf = rfsolvfNbmf(nbmf);
        ClbssLobdfr dl = gftClbssLobdfr0();
        if (dl==null) {
            // A systfm dlbss.
            rfturn ClbssLobdfr.gftSystfmRfsourdf(nbmf);
        }
        rfturn dl.gftRfsourdf(nbmf);
    }



    /** protfdtion dombin rfturnfd wifn tif intfrnbl dombin is null */
    privbtf stbtid jbvb.sfdurity.ProtfdtionDombin bllPfrmDombin;


    /**
     * Rfturns tif {@dodf ProtfdtionDombin} of tiis dlbss.  If tifrf is b
     * sfdurity mbnbgfr instbllfd, tiis mftiod first dblls tif sfdurity
     * mbnbgfr's {@dodf difdkPfrmission} mftiod witi b
     * {@dodf RuntimfPfrmission("gftProtfdtionDombin")} pfrmission to
     * fnsurf it's ok to gft tif
     * {@dodf ProtfdtionDombin}.
     *
     * @rfturn tif ProtfdtionDombin of tiis dlbss
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        gftting tif ProtfdtionDombin.
     *
     * @sff jbvb.sfdurity.ProtfdtionDombin
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.lbng.RuntimfPfrmission
     * @sindf 1.2
     */
    publid jbvb.sfdurity.ProtfdtionDombin gftProtfdtionDombin() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(SfdurityConstbnts.GET_PD_PERMISSION);
        }
        jbvb.sfdurity.ProtfdtionDombin pd = gftProtfdtionDombin0();
        if (pd == null) {
            if (bllPfrmDombin == null) {
                jbvb.sfdurity.Pfrmissions pfrms =
                    nfw jbvb.sfdurity.Pfrmissions();
                pfrms.bdd(SfdurityConstbnts.ALL_PERMISSION);
                bllPfrmDombin =
                    nfw jbvb.sfdurity.ProtfdtionDombin(null, pfrms);
            }
            pd = bllPfrmDombin;
        }
        rfturn pd;
    }


    /**
     * Rfturns tif ProtfdtionDombin of tiis dlbss.
     */
    privbtf nbtivf jbvb.sfdurity.ProtfdtionDombin gftProtfdtionDombin0();

    /*
     * Rfturn tif Virtubl Mbdiinf's Clbss objfdt for tif nbmfd
     * primitivf typf.
     */
    stbtid nbtivf Clbss<?> gftPrimitivfClbss(String nbmf);

    /*
     * Cifdk if dlifnt is bllowfd to bddfss mfmbfrs.  If bddfss is dfnifd,
     * tirow b SfdurityExdfption.
     *
     * Tiis mftiod blso fnfordfs pbdkbgf bddfss.
     *
     * <p> Dffbult polidy: bllow bll dlifnts bddfss witi normbl Jbvb bddfss
     * dontrol.
     */
    privbtf void difdkMfmbfrAddfss(int wiidi, Clbss<?> dbllfr, boolfbn difdkProxyIntfrfbdfs) {
        finbl SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
        if (s != null) {
            /* Dffbult polidy bllows bddfss to bll {@link Mfmbfr#PUBLIC} mfmbfrs,
             * bs wfll bs bddfss to dlbssfs tibt ibvf tif sbmf dlbss lobdfr bs tif dbllfr.
             * In bll otifr dbsfs, it rfquirfs RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")
             * pfrmission.
             */
            finbl ClbssLobdfr ddl = ClbssLobdfr.gftClbssLobdfr(dbllfr);
            finbl ClbssLobdfr dl = gftClbssLobdfr0();
            if (wiidi != Mfmbfr.PUBLIC) {
                if (ddl != dl) {
                    s.difdkPfrmission(SfdurityConstbnts.CHECK_MEMBER_ACCESS_PERMISSION);
                }
            }
            tiis.difdkPbdkbgfAddfss(ddl, difdkProxyIntfrfbdfs);
        }
    }

    /*
     * Cifdks if b dlifnt lobdfd in ClbssLobdfr ddl is bllowfd to bddfss tiis
     * dlbss undfr tif durrfnt pbdkbgf bddfss polidy. If bddfss is dfnifd,
     * tirow b SfdurityExdfption.
     */
    privbtf void difdkPbdkbgfAddfss(finbl ClbssLobdfr ddl, boolfbn difdkProxyIntfrfbdfs) {
        finbl SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
        if (s != null) {
            finbl ClbssLobdfr dl = gftClbssLobdfr0();

            if (RfflfdtUtil.nffdsPbdkbgfAddfssCifdk(ddl, dl)) {
                String nbmf = tiis.gftNbmf();
                int i = nbmf.lbstIndfxOf('.');
                if (i != -1) {
                    // skip tif pbdkbgf bddfss difdk on b proxy dlbss in dffbult proxy pbdkbgf
                    String pkg = nbmf.substring(0, i);
                    if (!Proxy.isProxyClbss(tiis) || RfflfdtUtil.isNonPublidProxyClbss(tiis)) {
                        s.difdkPbdkbgfAddfss(pkg);
                    }
                }
            }
            // difdk pbdkbgf bddfss on tif proxy intfrfbdfs
            if (difdkProxyIntfrfbdfs && Proxy.isProxyClbss(tiis)) {
                RfflfdtUtil.difdkProxyPbdkbgfAddfss(ddl, tiis.gftIntfrfbdfs());
            }
        }
    }

    /**
     * Add b pbdkbgf nbmf prffix if tif nbmf is not bbsolutf Rfmovf lfbding "/"
     * if nbmf is bbsolutf
     */
    privbtf String rfsolvfNbmf(String nbmf) {
        if (nbmf == null) {
            rfturn nbmf;
        }
        if (!nbmf.stbrtsWiti("/")) {
            Clbss<?> d = tiis;
            wiilf (d.isArrby()) {
                d = d.gftComponfntTypf();
            }
            String bbsfNbmf = d.gftNbmf();
            int indfx = bbsfNbmf.lbstIndfxOf('.');
            if (indfx != -1) {
                nbmf = bbsfNbmf.substring(0, indfx).rfplbdf('.', '/')
                    +"/"+nbmf;
            }
        } flsf {
            nbmf = nbmf.substring(1);
        }
        rfturn nbmf;
    }

    /**
     * Atomid opfrbtions support.
     */
    privbtf stbtid dlbss Atomid {
        // initiblizf Unsbff mbdiinfry ifrf, sindf wf nffd to dbll Clbss.dlbss instbndf mftiod
        // bnd ibvf to bvoid dblling it in tif stbtid initiblizfr of tif Clbss dlbss...
        privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
        // offsft of Clbss.rfflfdtionDbtb instbndf fifld
        privbtf stbtid finbl long rfflfdtionDbtbOffsft;
        // offsft of Clbss.bnnotbtionTypf instbndf fifld
        privbtf stbtid finbl long bnnotbtionTypfOffsft;
        // offsft of Clbss.bnnotbtionDbtb instbndf fifld
        privbtf stbtid finbl long bnnotbtionDbtbOffsft;

        stbtid {
            Fifld[] fiflds = Clbss.dlbss.gftDfdlbrfdFiflds0(fblsf); // bypbss dbdifs
            rfflfdtionDbtbOffsft = objfdtFifldOffsft(fiflds, "rfflfdtionDbtb");
            bnnotbtionTypfOffsft = objfdtFifldOffsft(fiflds, "bnnotbtionTypf");
            bnnotbtionDbtbOffsft = objfdtFifldOffsft(fiflds, "bnnotbtionDbtb");
        }

        privbtf stbtid long objfdtFifldOffsft(Fifld[] fiflds, String fifldNbmf) {
            Fifld fifld = sfbrdiFiflds(fiflds, fifldNbmf);
            if (fifld == null) {
                tirow nfw Error("No " + fifldNbmf + " fifld found in jbvb.lbng.Clbss");
            }
            rfturn unsbff.objfdtFifldOffsft(fifld);
        }

        stbtid <T> boolfbn dbsRfflfdtionDbtb(Clbss<?> dlbzz,
                                             SoftRfffrfndf<RfflfdtionDbtb<T>> oldDbtb,
                                             SoftRfffrfndf<RfflfdtionDbtb<T>> nfwDbtb) {
            rfturn unsbff.dompbrfAndSwbpObjfdt(dlbzz, rfflfdtionDbtbOffsft, oldDbtb, nfwDbtb);
        }

        stbtid <T> boolfbn dbsAnnotbtionTypf(Clbss<?> dlbzz,
                                             AnnotbtionTypf oldTypf,
                                             AnnotbtionTypf nfwTypf) {
            rfturn unsbff.dompbrfAndSwbpObjfdt(dlbzz, bnnotbtionTypfOffsft, oldTypf, nfwTypf);
        }

        stbtid <T> boolfbn dbsAnnotbtionDbtb(Clbss<?> dlbzz,
                                             AnnotbtionDbtb oldDbtb,
                                             AnnotbtionDbtb nfwDbtb) {
            rfturn unsbff.dompbrfAndSwbpObjfdt(dlbzz, bnnotbtionDbtbOffsft, oldDbtb, nfwDbtb);
        }
    }

    /**
     * Rfflfdtion support.
     */

    // Cbdifs for dfrtbin rfflfdtivf rfsults
    privbtf stbtid boolfbn usfCbdifs = truf;

    // rfflfdtion dbtb tibt migit gft invblidbtfd wifn JVM TI RfdffinfClbssfs() is dbllfd
    privbtf stbtid dlbss RfflfdtionDbtb<T> {
        volbtilf Fifld[] dfdlbrfdFiflds;
        volbtilf Fifld[] publidFiflds;
        volbtilf Mftiod[] dfdlbrfdMftiods;
        volbtilf Mftiod[] publidMftiods;
        volbtilf Construdtor<T>[] dfdlbrfdConstrudtors;
        volbtilf Construdtor<T>[] publidConstrudtors;
        // Intfrmfdibtf rfsults for gftFiflds bnd gftMftiods
        volbtilf Fifld[] dfdlbrfdPublidFiflds;
        volbtilf Mftiod[] dfdlbrfdPublidMftiods;
        volbtilf Clbss<?>[] intfrfbdfs;

        // Vbluf of dlbssRfdffinfdCount wifn wf drfbtfd tiis RfflfdtionDbtb instbndf
        finbl int rfdffinfdCount;

        RfflfdtionDbtb(int rfdffinfdCount) {
            tiis.rfdffinfdCount = rfdffinfdCount;
        }
    }

    privbtf volbtilf trbnsifnt SoftRfffrfndf<RfflfdtionDbtb<T>> rfflfdtionDbtb;

    // Indrfmfntfd by tif VM on fbdi dbll to JVM TI RfdffinfClbssfs()
    // tibt rfdffinfs tiis dlbss or b supfrdlbss.
    privbtf volbtilf trbnsifnt int dlbssRfdffinfdCount = 0;

    // Lbzily drfbtf bnd dbdif RfflfdtionDbtb
    privbtf RfflfdtionDbtb<T> rfflfdtionDbtb() {
        SoftRfffrfndf<RfflfdtionDbtb<T>> rfflfdtionDbtb = tiis.rfflfdtionDbtb;
        int dlbssRfdffinfdCount = tiis.dlbssRfdffinfdCount;
        RfflfdtionDbtb<T> rd;
        if (usfCbdifs &&
            rfflfdtionDbtb != null &&
            (rd = rfflfdtionDbtb.gft()) != null &&
            rd.rfdffinfdCount == dlbssRfdffinfdCount) {
            rfturn rd;
        }
        // flsf no SoftRfffrfndf or dlfbrfd SoftRfffrfndf or stblf RfflfdtionDbtb
        // -> drfbtf bnd rfplbdf nfw instbndf
        rfturn nfwRfflfdtionDbtb(rfflfdtionDbtb, dlbssRfdffinfdCount);
    }

    privbtf RfflfdtionDbtb<T> nfwRfflfdtionDbtb(SoftRfffrfndf<RfflfdtionDbtb<T>> oldRfflfdtionDbtb,
                                                int dlbssRfdffinfdCount) {
        if (!usfCbdifs) rfturn null;

        wiilf (truf) {
            RfflfdtionDbtb<T> rd = nfw RfflfdtionDbtb<>(dlbssRfdffinfdCount);
            // try to CAS it...
            if (Atomid.dbsRfflfdtionDbtb(tiis, oldRfflfdtionDbtb, nfw SoftRfffrfndf<>(rd))) {
                rfturn rd;
            }
            // flsf rftry
            oldRfflfdtionDbtb = tiis.rfflfdtionDbtb;
            dlbssRfdffinfdCount = tiis.dlbssRfdffinfdCount;
            if (oldRfflfdtionDbtb != null &&
                (rd = oldRfflfdtionDbtb.gft()) != null &&
                rd.rfdffinfdCount == dlbssRfdffinfdCount) {
                rfturn rd;
            }
        }
    }

    // Gfnfrid signbturf ibndling
    privbtf nbtivf String gftGfnfridSignbturf0();

    // Gfnfrid info rfpository; lbzily initiblizfd
    privbtf volbtilf trbnsifnt ClbssRfpository gfnfridInfo;

    // bddfssor for fbdtory
    privbtf GfnfridsFbdtory gftFbdtory() {
        // drfbtf sdopf bnd fbdtory
        rfturn CorfRfflfdtionFbdtory.mbkf(tiis, ClbssSdopf.mbkf(tiis));
    }

    // bddfssor for gfnfrid info rfpository;
    // gfnfrid info is lbzily initiblizfd
    privbtf ClbssRfpository gftGfnfridInfo() {
        ClbssRfpository gfnfridInfo = tiis.gfnfridInfo;
        if (gfnfridInfo == null) {
            String signbturf = gftGfnfridSignbturf0();
            if (signbturf == null) {
                gfnfridInfo = ClbssRfpository.NONE;
            } flsf {
                gfnfridInfo = ClbssRfpository.mbkf(signbturf, gftFbdtory());
            }
            tiis.gfnfridInfo = gfnfridInfo;
        }
        rfturn (gfnfridInfo != ClbssRfpository.NONE) ? gfnfridInfo : null;
    }

    // Annotbtions ibndling
    nbtivf bytf[] gftRbwAnnotbtions();
    // Sindf 1.8
    nbtivf bytf[] gftRbwTypfAnnotbtions();
    stbtid bytf[] gftExfdutbblfTypfAnnotbtionBytfs(Exfdutbblf fx) {
        rfturn gftRfflfdtionFbdtory().gftExfdutbblfTypfAnnotbtionBytfs(fx);
    }

    nbtivf ConstbntPool gftConstbntPool();

    //
    //
    // jbvb.lbng.rfflfdt.Fifld ibndling
    //
    //

    // Rfturns bn brrby of "root" fiflds. Tifsf Fifld objfdts must NOT
    // bf propbgbtfd to tif outsidf world, but must instfbd bf dopifd
    // vib RfflfdtionFbdtory.dopyFifld.
    privbtf Fifld[] privbtfGftDfdlbrfdFiflds(boolfbn publidOnly) {
        difdkInittfd();
        Fifld[] rfs;
        RfflfdtionDbtb<T> rd = rfflfdtionDbtb();
        if (rd != null) {
            rfs = publidOnly ? rd.dfdlbrfdPublidFiflds : rd.dfdlbrfdFiflds;
            if (rfs != null) rfturn rfs;
        }
        // No dbdifd vbluf bvbilbblf; rfqufst vbluf from VM
        rfs = Rfflfdtion.filtfrFiflds(tiis, gftDfdlbrfdFiflds0(publidOnly));
        if (rd != null) {
            if (publidOnly) {
                rd.dfdlbrfdPublidFiflds = rfs;
            } flsf {
                rd.dfdlbrfdFiflds = rfs;
            }
        }
        rfturn rfs;
    }

    // Rfturns bn brrby of "root" fiflds. Tifsf Fifld objfdts must NOT
    // bf propbgbtfd to tif outsidf world, but must instfbd bf dopifd
    // vib RfflfdtionFbdtory.dopyFifld.
    privbtf Fifld[] privbtfGftPublidFiflds(Sft<Clbss<?>> trbvfrsfdIntfrfbdfs) {
        difdkInittfd();
        Fifld[] rfs;
        RfflfdtionDbtb<T> rd = rfflfdtionDbtb();
        if (rd != null) {
            rfs = rd.publidFiflds;
            if (rfs != null) rfturn rfs;
        }

        // No dbdifd vbluf bvbilbblf; domputf vbluf rfdursivfly.
        // Trbvfrsf in dorrfdt ordfr for gftFifld().
        List<Fifld> fiflds = nfw ArrbyList<>();
        if (trbvfrsfdIntfrfbdfs == null) {
            trbvfrsfdIntfrfbdfs = nfw HbsiSft<>();
        }

        // Lodbl fiflds
        Fifld[] tmp = privbtfGftDfdlbrfdFiflds(truf);
        bddAll(fiflds, tmp);

        // Dirfdt supfrintfrfbdfs, rfdursivfly
        for (Clbss<?> d : gftIntfrfbdfs()) {
            if (!trbvfrsfdIntfrfbdfs.dontbins(d)) {
                trbvfrsfdIntfrfbdfs.bdd(d);
                bddAll(fiflds, d.privbtfGftPublidFiflds(trbvfrsfdIntfrfbdfs));
            }
        }

        // Dirfdt supfrdlbss, rfdursivfly
        if (!isIntfrfbdf()) {
            Clbss<?> d = gftSupfrdlbss();
            if (d != null) {
                bddAll(fiflds, d.privbtfGftPublidFiflds(trbvfrsfdIntfrfbdfs));
            }
        }

        rfs = nfw Fifld[fiflds.sizf()];
        fiflds.toArrby(rfs);
        if (rd != null) {
            rd.publidFiflds = rfs;
        }
        rfturn rfs;
    }

    privbtf stbtid void bddAll(Collfdtion<Fifld> d, Fifld[] o) {
        for (Fifld f : o) {
            d.bdd(f);
        }
    }


    //
    //
    // jbvb.lbng.rfflfdt.Construdtor ibndling
    //
    //

    // Rfturns bn brrby of "root" donstrudtors. Tifsf Construdtor
    // objfdts must NOT bf propbgbtfd to tif outsidf world, but must
    // instfbd bf dopifd vib RfflfdtionFbdtory.dopyConstrudtor.
    privbtf Construdtor<T>[] privbtfGftDfdlbrfdConstrudtors(boolfbn publidOnly) {
        difdkInittfd();
        Construdtor<T>[] rfs;
        RfflfdtionDbtb<T> rd = rfflfdtionDbtb();
        if (rd != null) {
            rfs = publidOnly ? rd.publidConstrudtors : rd.dfdlbrfdConstrudtors;
            if (rfs != null) rfturn rfs;
        }
        // No dbdifd vbluf bvbilbblf; rfqufst vbluf from VM
        if (isIntfrfbdf()) {
            @SupprfssWbrnings("undifdkfd")
            Construdtor<T>[] tfmporbryRfs = (Construdtor<T>[]) nfw Construdtor<?>[0];
            rfs = tfmporbryRfs;
        } flsf {
            rfs = gftDfdlbrfdConstrudtors0(publidOnly);
        }
        if (rd != null) {
            if (publidOnly) {
                rd.publidConstrudtors = rfs;
            } flsf {
                rd.dfdlbrfdConstrudtors = rfs;
            }
        }
        rfturn rfs;
    }

    //
    //
    // jbvb.lbng.rfflfdt.Mftiod ibndling
    //
    //

    // Rfturns bn brrby of "root" mftiods. Tifsf Mftiod objfdts must NOT
    // bf propbgbtfd to tif outsidf world, but must instfbd bf dopifd
    // vib RfflfdtionFbdtory.dopyMftiod.
    privbtf Mftiod[] privbtfGftDfdlbrfdMftiods(boolfbn publidOnly) {
        difdkInittfd();
        Mftiod[] rfs;
        RfflfdtionDbtb<T> rd = rfflfdtionDbtb();
        if (rd != null) {
            rfs = publidOnly ? rd.dfdlbrfdPublidMftiods : rd.dfdlbrfdMftiods;
            if (rfs != null) rfturn rfs;
        }
        // No dbdifd vbluf bvbilbblf; rfqufst vbluf from VM
        rfs = Rfflfdtion.filtfrMftiods(tiis, gftDfdlbrfdMftiods0(publidOnly));
        if (rd != null) {
            if (publidOnly) {
                rd.dfdlbrfdPublidMftiods = rfs;
            } flsf {
                rd.dfdlbrfdMftiods = rfs;
            }
        }
        rfturn rfs;
    }

    stbtid dlbss MftiodArrby {
        // Don't bdd or rfmovf mftiods fxdfpt by bdd() or rfmovf() dblls.
        privbtf Mftiod[] mftiods;
        privbtf int lfngti;
        privbtf int dffbults;

        MftiodArrby() {
            tiis(20);
        }

        MftiodArrby(int initiblSizf) {
            if (initiblSizf < 2)
                tirow nfw IllfgblArgumfntExdfption("Sizf siould bf 2 or morf");

            mftiods = nfw Mftiod[initiblSizf];
            lfngti = 0;
            dffbults = 0;
        }

        boolfbn ibsDffbults() {
            rfturn dffbults != 0;
        }

        void bdd(Mftiod m) {
            if (lfngti == mftiods.lfngti) {
                mftiods = Arrbys.dopyOf(mftiods, 2 * mftiods.lfngti);
            }
            mftiods[lfngti++] = m;

            if (m != null && m.isDffbult())
                dffbults++;
        }

        void bddAll(Mftiod[] mb) {
            for (Mftiod m : mb) {
                bdd(m);
            }
        }

        void bddAll(MftiodArrby mb) {
            for (int i = 0; i < mb.lfngti(); i++) {
                bdd(mb.gft(i));
            }
        }

        void bddIfNotPrfsfnt(Mftiod nfwMftiod) {
            for (int i = 0; i < lfngti; i++) {
                Mftiod m = mftiods[i];
                if (m == nfwMftiod || (m != null && m.fqubls(nfwMftiod))) {
                    rfturn;
                }
            }
            bdd(nfwMftiod);
        }

        void bddAllIfNotPrfsfnt(MftiodArrby nfwMftiods) {
            for (int i = 0; i < nfwMftiods.lfngti(); i++) {
                Mftiod m = nfwMftiods.gft(i);
                if (m != null) {
                    bddIfNotPrfsfnt(m);
                }
            }
        }

        /* Add Mftiods dfdlbrfd in bn intfrfbdf to tiis MftiodArrby.
         * Stbtid mftiods dfdlbrfd in intfrfbdfs brf not inifritfd.
         */
        void bddIntfrfbdfMftiods(Mftiod[] mftiods) {
            for (Mftiod dbndidbtf : mftiods) {
                if (!Modififr.isStbtid(dbndidbtf.gftModififrs())) {
                    bdd(dbndidbtf);
                }
            }
        }

        int lfngti() {
            rfturn lfngti;
        }

        Mftiod gft(int i) {
            rfturn mftiods[i];
        }

        Mftiod gftFirst() {
            for (Mftiod m : mftiods)
                if (m != null)
                    rfturn m;
            rfturn null;
        }

        void rfmovfByNbmfAndDfsdriptor(Mftiod toRfmovf) {
            for (int i = 0; i < lfngti; i++) {
                Mftiod m = mftiods[i];
                if (m != null && mbtdifsNbmfAndDfsdriptor(m, toRfmovf)) {
                    rfmovf(i);
                }
            }
        }

        privbtf void rfmovf(int i) {
            if (mftiods[i] != null && mftiods[i].isDffbult())
                dffbults--;
            mftiods[i] = null;
        }

        privbtf boolfbn mbtdifsNbmfAndDfsdriptor(Mftiod m1, Mftiod m2) {
            rfturn m1.gftRfturnTypf() == m2.gftRfturnTypf() &&
                   m1.gftNbmf() == m2.gftNbmf() && // nbmf is gubrbntffd to bf intfrnfd
                   brrbyContfntsEq(m1.gftPbrbmftfrTypfs(),
                           m2.gftPbrbmftfrTypfs());
        }

        void dompbdtAndTrim() {
            int nfwPos = 0;
            // Gft rid of null slots
            for (int pos = 0; pos < lfngti; pos++) {
                Mftiod m = mftiods[pos];
                if (m != null) {
                    if (pos != nfwPos) {
                        mftiods[nfwPos] = m;
                    }
                    nfwPos++;
                }
            }
            if (nfwPos != mftiods.lfngti) {
                mftiods = Arrbys.dopyOf(mftiods, nfwPos);
            }
        }

        /* Rfmovfs bll Mftiods from tiis MftiodArrby tibt ibvf b morf spfdifid
         * dffbult Mftiod in tiis MftiodArrby.
         *
         * Usfrs of MftiodArrby brf rfsponsiblf for pruning Mftiods tibt ibvf
         * b morf spfdifid <fm>dondrftf</fm> Mftiod.
         */
        void rfmovfLfssSpfdifids() {
            if (!ibsDffbults())
                rfturn;

            for (int i = 0; i < lfngti; i++) {
                Mftiod m = gft(i);
                if  (m == null || !m.isDffbult())
                    dontinuf;

                for (int j  = 0; j < lfngti; j++) {
                    if (i == j)
                        dontinuf;

                    Mftiod dbndidbtf = gft(j);
                    if (dbndidbtf == null)
                        dontinuf;

                    if (!mbtdifsNbmfAndDfsdriptor(m, dbndidbtf))
                        dontinuf;

                    if (ibsMorfSpfdifidClbss(m, dbndidbtf))
                        rfmovf(j);
                }
            }
        }

        Mftiod[] gftArrby() {
            rfturn mftiods;
        }

        // Rfturns truf if m1 is morf spfdifid tibn m2
        stbtid boolfbn ibsMorfSpfdifidClbss(Mftiod m1, Mftiod m2) {
            Clbss<?> m1Clbss = m1.gftDfdlbringClbss();
            Clbss<?> m2Clbss = m2.gftDfdlbringClbss();
            rfturn m1Clbss != m2Clbss && m2Clbss.isAssignbblfFrom(m1Clbss);
        }
    }


    // Rfturns bn brrby of "root" mftiods. Tifsf Mftiod objfdts must NOT
    // bf propbgbtfd to tif outsidf world, but must instfbd bf dopifd
    // vib RfflfdtionFbdtory.dopyMftiod.
    privbtf Mftiod[] privbtfGftPublidMftiods() {
        difdkInittfd();
        Mftiod[] rfs;
        RfflfdtionDbtb<T> rd = rfflfdtionDbtb();
        if (rd != null) {
            rfs = rd.publidMftiods;
            if (rfs != null) rfturn rfs;
        }

        // No dbdifd vbluf bvbilbblf; domputf vbluf rfdursivfly.
        // Stbrt by fftdiing publid dfdlbrfd mftiods
        MftiodArrby mftiods = nfw MftiodArrby();
        {
            Mftiod[] tmp = privbtfGftDfdlbrfdMftiods(truf);
            mftiods.bddAll(tmp);
        }
        // Now rfdur ovfr supfrdlbss bnd dirfdt supfrintfrfbdfs.
        // Go ovfr supfrintfrfbdfs first so wf dbn morf fbsily filtfr
        // out dondrftf implfmfntbtions inifritfd from supfrdlbssfs bt
        // tif fnd.
        MftiodArrby inifritfdMftiods = nfw MftiodArrby();
        for (Clbss<?> i : gftIntfrfbdfs()) {
            inifritfdMftiods.bddIntfrfbdfMftiods(i.privbtfGftPublidMftiods());
        }
        if (!isIntfrfbdf()) {
            Clbss<?> d = gftSupfrdlbss();
            if (d != null) {
                MftiodArrby supfrs = nfw MftiodArrby();
                supfrs.bddAll(d.privbtfGftPublidMftiods());
                // Filtfr out dondrftf implfmfntbtions of bny
                // intfrfbdf mftiods
                for (int i = 0; i < supfrs.lfngti(); i++) {
                    Mftiod m = supfrs.gft(i);
                    if (m != null &&
                            !Modififr.isAbstrbdt(m.gftModififrs()) &&
                            !m.isDffbult()) {
                        inifritfdMftiods.rfmovfByNbmfAndDfsdriptor(m);
                    }
                }
                // Insfrt supfrdlbss's inifritfd mftiods bfforf
                // supfrintfrfbdfs' to sbtisfy gftMftiod's sfbrdi
                // ordfr
                supfrs.bddAll(inifritfdMftiods);
                inifritfdMftiods = supfrs;
            }
        }
        // Filtfr out bll lodbl mftiods from inifritfd onfs
        for (int i = 0; i < mftiods.lfngti(); i++) {
            Mftiod m = mftiods.gft(i);
            inifritfdMftiods.rfmovfByNbmfAndDfsdriptor(m);
        }
        mftiods.bddAllIfNotPrfsfnt(inifritfdMftiods);
        mftiods.rfmovfLfssSpfdifids();
        mftiods.dompbdtAndTrim();
        rfs = mftiods.gftArrby();
        if (rd != null) {
            rd.publidMftiods = rfs;
        }
        rfturn rfs;
    }


    //
    // Hflpfrs for fftdifrs of onf fifld, mftiod, or donstrudtor
    //

    privbtf stbtid Fifld sfbrdiFiflds(Fifld[] fiflds, String nbmf) {
        String intfrnfdNbmf = nbmf.intfrn();
        for (Fifld fifld : fiflds) {
            if (fifld.gftNbmf() == intfrnfdNbmf) {
                rfturn gftRfflfdtionFbdtory().dopyFifld(fifld);
            }
        }
        rfturn null;
    }

    privbtf Fifld gftFifld0(String nbmf) tirows NoSudiFifldExdfption {
        // Notf: tif intfnt is tibt tif sfbrdi blgoritim tiis routinf
        // usfs bf fquivblfnt to tif ordfring imposfd by
        // privbtfGftPublidFiflds(). It fftdifs only tif dfdlbrfd
        // publid fiflds for fbdi dlbss, iowfvfr, to rfdudf tif numbfr
        // of Fifld objfdts wiidi ibvf to bf drfbtfd for tif dommon
        // dbsf wifrf tif fifld bfing rfqufstfd is dfdlbrfd in tif
        // dlbss wiidi is bfing qufrifd.
        Fifld rfs;
        // Sfbrdi dfdlbrfd publid fiflds
        if ((rfs = sfbrdiFiflds(privbtfGftDfdlbrfdFiflds(truf), nbmf)) != null) {
            rfturn rfs;
        }
        // Dirfdt supfrintfrfbdfs, rfdursivfly
        Clbss<?>[] intfrfbdfs = gftIntfrfbdfs();
        for (Clbss<?> d : intfrfbdfs) {
            if ((rfs = d.gftFifld0(nbmf)) != null) {
                rfturn rfs;
            }
        }
        // Dirfdt supfrdlbss, rfdursivfly
        if (!isIntfrfbdf()) {
            Clbss<?> d = gftSupfrdlbss();
            if (d != null) {
                if ((rfs = d.gftFifld0(nbmf)) != null) {
                    rfturn rfs;
                }
            }
        }
        rfturn null;
    }

    privbtf stbtid Mftiod sfbrdiMftiods(Mftiod[] mftiods,
                                        String nbmf,
                                        Clbss<?>[] pbrbmftfrTypfs)
    {
        Mftiod rfs = null;
        String intfrnfdNbmf = nbmf.intfrn();
        for (Mftiod m : mftiods) {
            if (m.gftNbmf() == intfrnfdNbmf
                && brrbyContfntsEq(pbrbmftfrTypfs, m.gftPbrbmftfrTypfs())
                && (rfs == null
                    || rfs.gftRfturnTypf().isAssignbblfFrom(m.gftRfturnTypf())))
                rfs = m;
        }

        rfturn (rfs == null ? rfs : gftRfflfdtionFbdtory().dopyMftiod(rfs));
    }

    privbtf Mftiod gftMftiod0(String nbmf, Clbss<?>[] pbrbmftfrTypfs, boolfbn indludfStbtidMftiods) {
        MftiodArrby intfrfbdfCbndidbtfs = nfw MftiodArrby(2);
        Mftiod rfs =  privbtfGftMftiodRfdursivf(nbmf, pbrbmftfrTypfs, indludfStbtidMftiods, intfrfbdfCbndidbtfs);
        if (rfs != null)
            rfturn rfs;

        // Not found on dlbss or supfrdlbss dirfdtly
        intfrfbdfCbndidbtfs.rfmovfLfssSpfdifids();
        rfturn intfrfbdfCbndidbtfs.gftFirst(); // mby bf null
    }

    privbtf Mftiod privbtfGftMftiodRfdursivf(String nbmf,
            Clbss<?>[] pbrbmftfrTypfs,
            boolfbn indludfStbtidMftiods,
            MftiodArrby bllIntfrfbdfCbndidbtfs) {
        // Notf: tif intfnt is tibt tif sfbrdi blgoritim tiis routinf
        // usfs bf fquivblfnt to tif ordfring imposfd by
        // privbtfGftPublidMftiods(). It fftdifs only tif dfdlbrfd
        // publid mftiods for fbdi dlbss, iowfvfr, to rfdudf tif
        // numbfr of Mftiod objfdts wiidi ibvf to bf drfbtfd for tif
        // dommon dbsf wifrf tif mftiod bfing rfqufstfd is dfdlbrfd in
        // tif dlbss wiidi is bfing qufrifd.
        //
        // Duf to dffbult mftiods, unlfss b mftiod is found on b supfrdlbss,
        // mftiods dfdlbrfd in bny supfrintfrfbdf nffds to bf donsidfrfd.
        // Collfdt bll dbndidbtfs dfdlbrfd in supfrintfrfbdfs in {@dodf
        // bllIntfrfbdfCbndidbtfs} bnd sflfdt tif most spfdifid if no mbtdi on
        // b supfrdlbss is found.

        // Must _not_ rfturn root mftiods
        Mftiod rfs;
        // Sfbrdi dfdlbrfd publid mftiods
        if ((rfs = sfbrdiMftiods(privbtfGftDfdlbrfdMftiods(truf),
                                 nbmf,
                                 pbrbmftfrTypfs)) != null) {
            if (indludfStbtidMftiods || !Modififr.isStbtid(rfs.gftModififrs()))
                rfturn rfs;
        }
        // Sfbrdi supfrdlbss's mftiods
        if (!isIntfrfbdf()) {
            Clbss<? supfr T> d = gftSupfrdlbss();
            if (d != null) {
                if ((rfs = d.gftMftiod0(nbmf, pbrbmftfrTypfs, truf)) != null) {
                    rfturn rfs;
                }
            }
        }
        // Sfbrdi supfrintfrfbdfs' mftiods
        Clbss<?>[] intfrfbdfs = gftIntfrfbdfs();
        for (Clbss<?> d : intfrfbdfs)
            if ((rfs = d.gftMftiod0(nbmf, pbrbmftfrTypfs, fblsf)) != null)
                bllIntfrfbdfCbndidbtfs.bdd(rfs);
        // Not found
        rfturn null;
    }

    privbtf Construdtor<T> gftConstrudtor0(Clbss<?>[] pbrbmftfrTypfs,
                                        int wiidi) tirows NoSudiMftiodExdfption
    {
        Construdtor<T>[] donstrudtors = privbtfGftDfdlbrfdConstrudtors((wiidi == Mfmbfr.PUBLIC));
        for (Construdtor<T> donstrudtor : donstrudtors) {
            if (brrbyContfntsEq(pbrbmftfrTypfs,
                                donstrudtor.gftPbrbmftfrTypfs())) {
                rfturn gftRfflfdtionFbdtory().dopyConstrudtor(donstrudtor);
            }
        }
        tirow nfw NoSudiMftiodExdfption(gftNbmf() + ".<init>" + brgumfntTypfsToString(pbrbmftfrTypfs));
    }

    //
    // Otifr iflpfrs bnd bbsf implfmfntbtion
    //

    privbtf stbtid boolfbn brrbyContfntsEq(Objfdt[] b1, Objfdt[] b2) {
        if (b1 == null) {
            rfturn b2 == null || b2.lfngti == 0;
        }

        if (b2 == null) {
            rfturn b1.lfngti == 0;
        }

        if (b1.lfngti != b2.lfngti) {
            rfturn fblsf;
        }

        for (int i = 0; i < b1.lfngti; i++) {
            if (b1[i] != b2[i]) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }

    privbtf stbtid Fifld[] dopyFiflds(Fifld[] brg) {
        Fifld[] out = nfw Fifld[brg.lfngti];
        RfflfdtionFbdtory fbdt = gftRfflfdtionFbdtory();
        for (int i = 0; i < brg.lfngti; i++) {
            out[i] = fbdt.dopyFifld(brg[i]);
        }
        rfturn out;
    }

    privbtf stbtid Mftiod[] dopyMftiods(Mftiod[] brg) {
        Mftiod[] out = nfw Mftiod[brg.lfngti];
        RfflfdtionFbdtory fbdt = gftRfflfdtionFbdtory();
        for (int i = 0; i < brg.lfngti; i++) {
            out[i] = fbdt.dopyMftiod(brg[i]);
        }
        rfturn out;
    }

    privbtf stbtid <U> Construdtor<U>[] dopyConstrudtors(Construdtor<U>[] brg) {
        Construdtor<U>[] out = brg.dlonf();
        RfflfdtionFbdtory fbdt = gftRfflfdtionFbdtory();
        for (int i = 0; i < out.lfngti; i++) {
            out[i] = fbdt.dopyConstrudtor(out[i]);
        }
        rfturn out;
    }

    privbtf nbtivf Fifld[]       gftDfdlbrfdFiflds0(boolfbn publidOnly);
    privbtf nbtivf Mftiod[]      gftDfdlbrfdMftiods0(boolfbn publidOnly);
    privbtf nbtivf Construdtor<T>[] gftDfdlbrfdConstrudtors0(boolfbn publidOnly);
    privbtf nbtivf Clbss<?>[]   gftDfdlbrfdClbssfs0();

    privbtf stbtid String        brgumfntTypfsToString(Clbss<?>[] brgTypfs) {
        StringBuildfr buf = nfw StringBuildfr();
        buf.bppfnd("(");
        if (brgTypfs != null) {
            for (int i = 0; i < brgTypfs.lfngti; i++) {
                if (i > 0) {
                    buf.bppfnd(", ");
                }
                Clbss<?> d = brgTypfs[i];
                buf.bppfnd((d == null) ? "null" : d.gftNbmf());
            }
        }
        buf.bppfnd(")");
        rfturn buf.toString();
    }

    /** usf sfriblVfrsionUID from JDK 1.1 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 3206093459760846163L;


    /**
     * Clbss Clbss is spfdibl dbsfd witiin tif Sfriblizbtion Strfbm Protodol.
     *
     * A Clbss instbndf is writtfn initiblly into bn ObjfdtOutputStrfbm in tif
     * following formbt:
     * <prf>
     *      {@dodf TC_CLASS} ClbssDfsdriptor
     *      A ClbssDfsdriptor is b spfdibl dbsfd sfriblizbtion of
     *      b {@dodf jbvb.io.ObjfdtStrfbmClbss} instbndf.
     * </prf>
     * A nfw ibndlf is gfnfrbtfd for tif initibl timf tif dlbss dfsdriptor
     * is writtfn into tif strfbm. Futurf rfffrfndfs to tif dlbss dfsdriptor
     * brf writtfn bs rfffrfndfs to tif initibl dlbss dfsdriptor instbndf.
     *
     * @sff jbvb.io.ObjfdtStrfbmClbss
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds =
        nfw ObjfdtStrfbmFifld[0];


    /**
     * Rfturns tif bssfrtion stbtus tibt would bf bssignfd to tiis
     * dlbss if it wfrf to bf initiblizfd bt tif timf tiis mftiod is invokfd.
     * If tiis dlbss ibs ibd its bssfrtion stbtus sft, tif most rfdfnt
     * sftting will bf rfturnfd; otifrwisf, if bny pbdkbgf dffbult bssfrtion
     * stbtus pfrtbins to tiis dlbss, tif most rfdfnt sftting for tif most
     * spfdifid pfrtinfnt pbdkbgf dffbult bssfrtion stbtus is rfturnfd;
     * otifrwisf, if tiis dlbss is not b systfm dlbss (i.f., it ibs b
     * dlbss lobdfr) its dlbss lobdfr's dffbult bssfrtion stbtus is rfturnfd;
     * otifrwisf, tif systfm dlbss dffbult bssfrtion stbtus is rfturnfd.
     * <p>
     * Ffw progrbmmfrs will ibvf bny nffd for tiis mftiod; it is providfd
     * for tif bfnffit of tif JRE itsflf.  (It bllows b dlbss to dftfrminf bt
     * tif timf tibt it is initiblizfd wiftifr bssfrtions siould bf fnbblfd.)
     * Notf tibt tiis mftiod is not gubrbntffd to rfturn tif bdtubl
     * bssfrtion stbtus tibt wbs (or will bf) bssodibtfd witi tif spfdififd
     * dlbss wifn it wbs (or will bf) initiblizfd.
     *
     * @rfturn tif dfsirfd bssfrtion stbtus of tif spfdififd dlbss.
     * @sff    jbvb.lbng.ClbssLobdfr#sftClbssAssfrtionStbtus
     * @sff    jbvb.lbng.ClbssLobdfr#sftPbdkbgfAssfrtionStbtus
     * @sff    jbvb.lbng.ClbssLobdfr#sftDffbultAssfrtionStbtus
     * @sindf  1.4
     */
    publid boolfbn dfsirfdAssfrtionStbtus() {
        ClbssLobdfr lobdfr = gftClbssLobdfr();
        // If tif lobdfr is null tiis is b systfm dlbss, so bsk tif VM
        if (lobdfr == null)
            rfturn dfsirfdAssfrtionStbtus0(tiis);

        // If tif dlbsslobdfr ibs bffn initiblizfd witi tif bssfrtion
        // dirfdtivfs, bsk it. Otifrwisf, bsk tif VM.
        syndironizfd(lobdfr.bssfrtionLodk) {
            if (lobdfr.dlbssAssfrtionStbtus != null) {
                rfturn lobdfr.dfsirfdAssfrtionStbtus(gftNbmf());
            }
        }
        rfturn dfsirfdAssfrtionStbtus0(tiis);
    }

    // Rftrifvfs tif dfsirfd bssfrtion stbtus of tiis dlbss from tif VM
    privbtf stbtid nbtivf boolfbn dfsirfdAssfrtionStbtus0(Clbss<?> dlbzz);

    /**
     * Rfturns truf if bnd only if tiis dlbss wbs dfdlbrfd bs bn fnum in tif
     * sourdf dodf.
     *
     * @rfturn truf if bnd only if tiis dlbss wbs dfdlbrfd bs bn fnum in tif
     *     sourdf dodf
     * @sindf 1.5
     */
    publid boolfbn isEnum() {
        // An fnum must boti dirfdtly fxtfnd jbvb.lbng.Enum bnd ibvf
        // tif ENUM bit sft; dlbssfs for spfdiblizfd fnum donstbnts
        // don't do tif formfr.
        rfturn (tiis.gftModififrs() & ENUM) != 0 &&
        tiis.gftSupfrdlbss() == jbvb.lbng.Enum.dlbss;
    }

    // Fftdifs tif fbdtory for rfflfdtivf objfdts
    privbtf stbtid RfflfdtionFbdtory gftRfflfdtionFbdtory() {
        if (rfflfdtionFbdtory == null) {
            rfflfdtionFbdtory =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                    (nfw sun.rfflfdt.RfflfdtionFbdtory.GftRfflfdtionFbdtoryAdtion());
        }
        rfturn rfflfdtionFbdtory;
    }
    privbtf stbtid RfflfdtionFbdtory rfflfdtionFbdtory;

    // To bf bblf to qufry systfm propfrtifs bs soon bs tify'rf bvbilbblf
    privbtf stbtid boolfbn inittfd = fblsf;
    privbtf stbtid void difdkInittfd() {
        if (inittfd) rfturn;
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    // Tfsts to fnsurf tif systfm propfrtifs tbblf is fully
                    // initiblizfd. Tiis is nffdfd bfdbusf rfflfdtion dodf is
                    // dbllfd vfry fbrly in tif initiblizbtion prodfss (bfforf
                    // dommbnd-linf brgumfnts ibvf bffn pbrsfd bnd tifrfforf
                    // tifsf usfr-sfttbblf propfrtifs instbllfd.) Wf bssumf tibt
                    // if Systfm.out is non-null tifn tif Systfm dlbss ibs bffn
                    // fully initiblizfd bnd tibt tif bulk of tif stbrtup dodf
                    // ibs bffn run.

                    if (Systfm.out == null) {
                        // jbvb.lbng.Systfm not yft fully initiblizfd
                        rfturn null;
                    }

                    // Dofsn't usf Boolfbn.gftBoolfbn to bvoid dlbss init.
                    String vbl =
                        Systfm.gftPropfrty("sun.rfflfdt.noCbdifs");
                    if (vbl != null && vbl.fqubls("truf")) {
                        usfCbdifs = fblsf;
                    }

                    inittfd = truf;
                    rfturn null;
                }
            });
    }

    /**
     * Rfturns tif flfmfnts of tiis fnum dlbss or null if tiis
     * Clbss objfdt dofs not rfprfsfnt bn fnum typf.
     *
     * @rfturn bn brrby dontbining tif vblufs domprising tif fnum dlbss
     *     rfprfsfntfd by tiis Clbss objfdt in tif ordfr tify'rf
     *     dfdlbrfd, or null if tiis Clbss objfdt dofs not
     *     rfprfsfnt bn fnum typf
     * @sindf 1.5
     */
    publid T[] gftEnumConstbnts() {
        T[] vblufs = gftEnumConstbntsSibrfd();
        rfturn (vblufs != null) ? vblufs.dlonf() : null;
    }

    /**
     * Rfturns tif flfmfnts of tiis fnum dlbss or null if tiis
     * Clbss objfdt dofs not rfprfsfnt bn fnum typf;
     * idfntidbl to gftEnumConstbnts fxdfpt tibt tif rfsult is
     * undlonfd, dbdifd, bnd sibrfd by bll dbllfrs.
     */
    T[] gftEnumConstbntsSibrfd() {
        if (fnumConstbnts == null) {
            if (!isEnum()) rfturn null;
            try {
                finbl Mftiod vblufs = gftMftiod("vblufs");
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                                vblufs.sftAddfssiblf(truf);
                                rfturn null;
                            }
                        });
                @SupprfssWbrnings("undifdkfd")
                T[] tfmporbryConstbnts = (T[])vblufs.invokf(null);
                fnumConstbnts = tfmporbryConstbnts;
            }
            // Tifsf dbn ibppfn wifn usfrs dondodt fnum-likf dlbssfs
            // tibt don't domply witi tif fnum spfd.
            dbtdi (InvodbtionTbrgftExdfption | NoSudiMftiodExdfption |
                   IllfgblAddfssExdfption fx) { rfturn null; }
        }
        rfturn fnumConstbnts;
    }
    privbtf volbtilf trbnsifnt T[] fnumConstbnts = null;

    /**
     * Rfturns b mbp from simplf nbmf to fnum donstbnt.  Tiis pbdkbgf-privbtf
     * mftiod is usfd intfrnblly by Enum to implfmfnt
     * {@dodf publid stbtid <T fxtfnds Enum<T>> T vblufOf(Clbss<T>, String)}
     * fffidifntly.  Notf tibt tif mbp is rfturnfd by tiis mftiod is
     * drfbtfd lbzily on first usf.  Typidblly it won't fvfr gft drfbtfd.
     */
    Mbp<String, T> fnumConstbntDirfdtory() {
        if (fnumConstbntDirfdtory == null) {
            T[] univfrsf = gftEnumConstbntsSibrfd();
            if (univfrsf == null)
                tirow nfw IllfgblArgumfntExdfption(
                    gftNbmf() + " is not bn fnum typf");
            Mbp<String, T> m = nfw HbsiMbp<>(2 * univfrsf.lfngti);
            for (T donstbnt : univfrsf)
                m.put(((Enum<?>)donstbnt).nbmf(), donstbnt);
            fnumConstbntDirfdtory = m;
        }
        rfturn fnumConstbntDirfdtory;
    }
    privbtf volbtilf trbnsifnt Mbp<String, T> fnumConstbntDirfdtory = null;

    /**
     * Cbsts bn objfdt to tif dlbss or intfrfbdf rfprfsfntfd
     * by tiis {@dodf Clbss} objfdt.
     *
     * @pbrbm obj tif objfdt to bf dbst
     * @rfturn tif objfdt bftfr dbsting, or null if obj is null
     *
     * @tirows ClbssCbstExdfption if tif objfdt is not
     * null bnd is not bssignbblf to tif typf T.
     *
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd")
    publid T dbst(Objfdt obj) {
        if (obj != null && !isInstbndf(obj))
            tirow nfw ClbssCbstExdfption(dbnnotCbstMsg(obj));
        rfturn (T) obj;
    }

    privbtf String dbnnotCbstMsg(Objfdt obj) {
        rfturn "Cbnnot dbst " + obj.gftClbss().gftNbmf() + " to " + gftNbmf();
    }

    /**
     * Cbsts tiis {@dodf Clbss} objfdt to rfprfsfnt b subdlbss of tif dlbss
     * rfprfsfntfd by tif spfdififd dlbss objfdt.  Cifdks tibt tif dbst
     * is vblid, bnd tirows b {@dodf ClbssCbstExdfption} if it is not.  If
     * tiis mftiod suddffds, it blwbys rfturns b rfffrfndf to tiis dlbss objfdt.
     *
     * <p>Tiis mftiod is usfful wifn b dlifnt nffds to "nbrrow" tif typf of
     * b {@dodf Clbss} objfdt to pbss it to bn API tibt rfstridts tif
     * {@dodf Clbss} objfdts tibt it is willing to bddfpt.  A dbst would
     * gfnfrbtf b dompilf-timf wbrning, bs tif dorrfdtnfss of tif dbst
     * dould not bf difdkfd bt runtimf (bfdbusf gfnfrid typfs brf implfmfntfd
     * by frbsurf).
     *
     * @pbrbm <U> tif typf to dbst tiis dlbss objfdt to
     * @pbrbm dlbzz tif dlbss of tif typf to dbst tiis dlbss objfdt to
     * @rfturn tiis {@dodf Clbss} objfdt, dbst to rfprfsfnt b subdlbss of
     *    tif spfdififd dlbss objfdt.
     * @tirows ClbssCbstExdfption if tiis {@dodf Clbss} objfdt dofs not
     *    rfprfsfnt b subdlbss of tif spfdififd dlbss (ifrf "subdlbss" indludfs
     *    tif dlbss itsflf).
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd")
    publid <U> Clbss<? fxtfnds U> bsSubdlbss(Clbss<U> dlbzz) {
        if (dlbzz.isAssignbblfFrom(tiis))
            rfturn (Clbss<? fxtfnds U>) tiis;
        flsf
            tirow nfw ClbssCbstExdfption(tiis.toString());
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd")
    publid <A fxtfnds Annotbtion> A gftAnnotbtion(Clbss<A> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);

        rfturn (A) bnnotbtionDbtb().bnnotbtions.gft(bnnotbtionClbss);
    }

    /**
     * {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.5
     */
    @Ovfrridf
    publid boolfbn isAnnotbtionPrfsfnt(Clbss<? fxtfnds Annotbtion> bnnotbtionClbss) {
        rfturn GfnfridDfdlbrbtion.supfr.isAnnotbtionPrfsfnt(bnnotbtionClbss);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.8
     */
    @Ovfrridf
    publid <A fxtfnds Annotbtion> A[] gftAnnotbtionsByTypf(Clbss<A> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);

        AnnotbtionDbtb bnnotbtionDbtb = bnnotbtionDbtb();
        rfturn AnnotbtionSupport.gftAssodibtfdAnnotbtions(bnnotbtionDbtb.dfdlbrfdAnnotbtions,
                                                          tiis,
                                                          bnnotbtionClbss);
    }

    /**
     * @sindf 1.5
     */
    publid Annotbtion[] gftAnnotbtions() {
        rfturn AnnotbtionPbrsfr.toArrby(bnnotbtionDbtb().bnnotbtions);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.8
     */
    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <A fxtfnds Annotbtion> A gftDfdlbrfdAnnotbtion(Clbss<A> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);

        rfturn (A) bnnotbtionDbtb().dfdlbrfdAnnotbtions.gft(bnnotbtionClbss);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.8
     */
    @Ovfrridf
    publid <A fxtfnds Annotbtion> A[] gftDfdlbrfdAnnotbtionsByTypf(Clbss<A> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);

        rfturn AnnotbtionSupport.gftDirfdtlyAndIndirfdtlyPrfsfnt(bnnotbtionDbtb().dfdlbrfdAnnotbtions,
                                                                 bnnotbtionClbss);
    }

    /**
     * @sindf 1.5
     */
    publid Annotbtion[] gftDfdlbrfdAnnotbtions()  {
        rfturn AnnotbtionPbrsfr.toArrby(bnnotbtionDbtb().dfdlbrfdAnnotbtions);
    }

    // bnnotbtion dbtb tibt migit gft invblidbtfd wifn JVM TI RfdffinfClbssfs() is dbllfd
    privbtf stbtid dlbss AnnotbtionDbtb {
        finbl Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> bnnotbtions;
        finbl Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> dfdlbrfdAnnotbtions;

        // Vbluf of dlbssRfdffinfdCount wifn wf drfbtfd tiis AnnotbtionDbtb instbndf
        finbl int rfdffinfdCount;

        AnnotbtionDbtb(Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> bnnotbtions,
                       Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> dfdlbrfdAnnotbtions,
                       int rfdffinfdCount) {
            tiis.bnnotbtions = bnnotbtions;
            tiis.dfdlbrfdAnnotbtions = dfdlbrfdAnnotbtions;
            tiis.rfdffinfdCount = rfdffinfdCount;
        }
    }

    // Annotbtions dbdif
    @SupprfssWbrnings("UnusfdDfdlbrbtion")
    privbtf volbtilf trbnsifnt AnnotbtionDbtb bnnotbtionDbtb;

    privbtf AnnotbtionDbtb bnnotbtionDbtb() {
        wiilf (truf) { // rftry loop
            AnnotbtionDbtb bnnotbtionDbtb = tiis.bnnotbtionDbtb;
            int dlbssRfdffinfdCount = tiis.dlbssRfdffinfdCount;
            if (bnnotbtionDbtb != null &&
                bnnotbtionDbtb.rfdffinfdCount == dlbssRfdffinfdCount) {
                rfturn bnnotbtionDbtb;
            }
            // null or stblf bnnotbtionDbtb -> optimistidblly drfbtf nfw instbndf
            AnnotbtionDbtb nfwAnnotbtionDbtb = drfbtfAnnotbtionDbtb(dlbssRfdffinfdCount);
            // try to instbll it
            if (Atomid.dbsAnnotbtionDbtb(tiis, bnnotbtionDbtb, nfwAnnotbtionDbtb)) {
                // suddfssfully instbllfd nfw AnnotbtionDbtb
                rfturn nfwAnnotbtionDbtb;
            }
        }
    }

    privbtf AnnotbtionDbtb drfbtfAnnotbtionDbtb(int dlbssRfdffinfdCount) {
        Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> dfdlbrfdAnnotbtions =
            AnnotbtionPbrsfr.pbrsfAnnotbtions(gftRbwAnnotbtions(), gftConstbntPool(), tiis);
        Clbss<?> supfrClbss = gftSupfrdlbss();
        Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> bnnotbtions = null;
        if (supfrClbss != null) {
            Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> supfrAnnotbtions =
                supfrClbss.bnnotbtionDbtb().bnnotbtions;
            for (Mbp.Entry<Clbss<? fxtfnds Annotbtion>, Annotbtion> f : supfrAnnotbtions.fntrySft()) {
                Clbss<? fxtfnds Annotbtion> bnnotbtionClbss = f.gftKfy();
                if (AnnotbtionTypf.gftInstbndf(bnnotbtionClbss).isInifritfd()) {
                    if (bnnotbtions == null) { // lbzy donstrudtion
                        bnnotbtions = nfw LinkfdHbsiMbp<>((Mbti.mbx(
                                dfdlbrfdAnnotbtions.sizf(),
                                Mbti.min(12, dfdlbrfdAnnotbtions.sizf() + supfrAnnotbtions.sizf())
                            ) * 4 + 2) / 3
                        );
                    }
                    bnnotbtions.put(bnnotbtionClbss, f.gftVbluf());
                }
            }
        }
        if (bnnotbtions == null) {
            // no inifritfd bnnotbtions -> sibrf tif Mbp witi dfdlbrfdAnnotbtions
            bnnotbtions = dfdlbrfdAnnotbtions;
        } flsf {
            // bt lfbst onf inifritfd bnnotbtion -> dfdlbrfd mby ovfrridf inifritfd
            bnnotbtions.putAll(dfdlbrfdAnnotbtions);
        }
        rfturn nfw AnnotbtionDbtb(bnnotbtions, dfdlbrfdAnnotbtions, dlbssRfdffinfdCount);
    }

    // Annotbtion typfs dbdif tifir intfrnbl (AnnotbtionTypf) form

    @SupprfssWbrnings("UnusfdDfdlbrbtion")
    privbtf volbtilf trbnsifnt AnnotbtionTypf bnnotbtionTypf;

    boolfbn dbsAnnotbtionTypf(AnnotbtionTypf oldTypf, AnnotbtionTypf nfwTypf) {
        rfturn Atomid.dbsAnnotbtionTypf(tiis, oldTypf, nfwTypf);
    }

    AnnotbtionTypf gftAnnotbtionTypf() {
        rfturn bnnotbtionTypf;
    }

    Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> gftDfdlbrfdAnnotbtionMbp() {
        rfturn bnnotbtionDbtb().dfdlbrfdAnnotbtions;
    }

    /* Bbdking storf of usfr-dffinfd vblufs pfrtbining to tiis dlbss.
     * Mbintbinfd by tif ClbssVbluf dlbss.
     */
    trbnsifnt ClbssVbluf.ClbssVblufMbp dlbssVblufMbp;

    /**
     * Rfturns bn {@dodf AnnotbtfdTypf} objfdt tibt rfprfsfnts tif usf of b
     * typf to spfdify tif supfrdlbss of tif fntity rfprfsfntfd by tiis {@dodf
     * Clbss} objfdt. (Tif <fm>usf</fm> of typf Foo to spfdify tif supfrdlbss
     * in '...  fxtfnds Foo' is distindt from tif <fm>dfdlbrbtion</fm> of typf
     * Foo.)
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b typf wiosf dfdlbrbtion
     * dofs not fxpliditly indidbtf bn bnnotbtfd supfrdlbss, tifn tif rfturn
     * vbluf is bn {@dodf AnnotbtfdTypf} objfdt rfprfsfnting bn flfmfnt witi no
     * bnnotbtions.
     *
     * <p> If tiis {@dodf Clbss} rfprfsfnts fitifr tif {@dodf Objfdt} dlbss, bn
     * intfrfbdf typf, bn brrby typf, b primitivf typf, or void, tif rfturn
     * vbluf is {@dodf null}.
     *
     * @rfturn bn objfdt rfprfsfnting tif supfrdlbss
     * @sindf 1.8
     */
    publid AnnotbtfdTypf gftAnnotbtfdSupfrdlbss() {
        if (tiis == Objfdt.dlbss ||
                isIntfrfbdf() ||
                isArrby() ||
                isPrimitivf() ||
                tiis == Void.TYPE) {
            rfturn null;
        }

        rfturn TypfAnnotbtionPbrsfr.buildAnnotbtfdSupfrdlbss(gftRbwTypfAnnotbtions(), gftConstbntPool(), tiis);
    }

    /**
     * Rfturns bn brrby of {@dodf AnnotbtfdTypf} objfdts tibt rfprfsfnt tif usf
     * of typfs to spfdify supfrintfrfbdfs of tif fntity rfprfsfntfd by tiis
     * {@dodf Clbss} objfdt. (Tif <fm>usf</fm> of typf Foo to spfdify b
     * supfrintfrfbdf in '... implfmfnts Foo' is distindt from tif
     * <fm>dfdlbrbtion</fm> of typf Foo.)
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b dlbss, tif rfturn vbluf is
     * bn brrby dontbining objfdts rfprfsfnting tif usfs of intfrfbdf typfs to
     * spfdify intfrfbdfs implfmfntfd by tif dlbss. Tif ordfr of tif objfdts in
     * tif brrby dorrfsponds to tif ordfr of tif intfrfbdf typfs usfd in tif
     * 'implfmfnts' dlbusf of tif dfdlbrbtion of tiis {@dodf Clbss} objfdt.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts bn intfrfbdf, tif rfturn
     * vbluf is bn brrby dontbining objfdts rfprfsfnting tif usfs of intfrfbdf
     * typfs to spfdify intfrfbdfs dirfdtly fxtfndfd by tif intfrfbdf. Tif
     * ordfr of tif objfdts in tif brrby dorrfsponds to tif ordfr of tif
     * intfrfbdf typfs usfd in tif 'fxtfnds' dlbusf of tif dfdlbrbtion of tiis
     * {@dodf Clbss} objfdt.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts b dlbss or intfrfbdf wiosf
     * dfdlbrbtion dofs not fxpliditly indidbtf bny bnnotbtfd supfrintfrfbdfs,
     * tif rfturn vbluf is bn brrby of lfngti 0.
     *
     * <p> If tiis {@dodf Clbss} objfdt rfprfsfnts fitifr tif {@dodf Objfdt}
     * dlbss, bn brrby typf, b primitivf typf, or void, tif rfturn vbluf is bn
     * brrby of lfngti 0.
     *
     * @rfturn bn brrby rfprfsfnting tif supfrintfrfbdfs
     * @sindf 1.8
     */
    publid AnnotbtfdTypf[] gftAnnotbtfdIntfrfbdfs() {
         rfturn TypfAnnotbtionPbrsfr.buildAnnotbtfdIntfrfbdfs(gftRbwTypfAnnotbtions(), gftConstbntPool(), tiis);
    }
}
